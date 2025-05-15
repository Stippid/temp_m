package com.dao.cue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.controller.cue.cueContoller;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_MDFS;
import com.persistance.util.HibernateUtilNA;

public class TransportAuthorizationUnderModificationDAOImpl implements TransportAuthorizationUnderModificationDAO {
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
  
	public String setApprovedMdfs(int appid,String username){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String date_of_apprv_rejc = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
       String hqlUpdate = "update CUE_TB_MISO_WEPE_TRANSPORT_MDFS c set c.status=:status,c.aprv_rejc_by=:aprv_rejc_by,c.date_of_apprv_rejc=:date_of_apprv_rejc where c.id=:id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setString("aprv_rejc_by", username).setString("date_of_apprv_rejc", date_of_apprv_rejc).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
	}
	
	public String setDeleteMdfs(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from CUE_TB_MISO_WEPE_TRANSPORT_MDFS where id = :id";
	    Query query = session.createQuery(hql);
	    query.setInteger("id",appid);
	    int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Deleted Successfully";
		}else {
			return "Deleted not Successfully";
		}	
	}
	
	public CUE_TB_MISO_WEPE_TRANSPORT_MDFS getCUE_TB_MISO_WEPE_TRANSPORT_MDFSRid(int id) {
    	Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_TRANSPORT_MDFS where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_WEPE_TRANSPORT_MDFS list = (CUE_TB_MISO_WEPE_TRANSPORT_MDFS) q.list().get(0);
		session.getTransaction().commit();
		session.close();		
		return list;
	}	
	
	
	
	
	@Override
	public  List<Map<String, Object>> getAttributeFromTransportModification(String we_pe_no,String modification,String mct_no,String status, String roleType, String roleAccess, String roleSusNo){
		{			
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			  Connection conn = null;
				String q="";
				String qry="";
			   try{	  
				   conn = dataSource.getConnection();				    
					PreparedStatement stmt =null;			
				
					if(status != ""  && status != null && status !="null" ) {	
						if(!status.equals("all")){
							qry += " and a.status = ?";
						}
						
					}		
					if(we_pe_no != "" && we_pe_no != null ) {
						qry += " and a.we_pe_no = ?";
						}
					if(modification != "" && modification != null) {
						qry += " and a.modification = ?";
						
						}
					if(mct_no != "" && mct_no != null) {
						
						qry += " and a.mct_no = ?";
						
					}
					if(roleAccess.equals("Line_dte")) {
						
						qry += " and b1.sponsor_dire = ?";
						
					}
					
					if(qry !="") {
						qry= " where "+qry.substring(4,qry.length());
					}
					
				q=" select distinct a.we_pe_no,a.modification,a.mct_no,a.amt_inc_dec,a.remarks,a.id,m.mct_nomen as std_nomclature,a.reject_letter_no ,a.reject_remarks,a.scenario,concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit,a.status,d.auth_amt,\r\n"
						+ "a.created_by as cr_by,a.created_on as cr_on,a.aprv_rejc_by as app_by,a.date_of_apprv_rejc as app_on,\r\n"
						+ "a.modified_by as modi_by,a.modified_on as modi_on  " + 
						" from cue_tb_miso_wepe_transport_mdfs a 	inner join  cue_tb_miso_wepeconditions b1 on  a.we_pe_no=b1.we_pe_no "
						+ "left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1' " + 
						" left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active' left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active' " + 
						" inner join  tb_tms_mct_main_master m on a.mct_no = cast(m.mct_main_id as text) LEFT JOIN cue_tb_miso_wepe_transport_det d ON a.we_pe_no=d.we_pe_no and a.mct_no=d.mct_no  "+qry+" order by a.status,a.we_pe_no,a.mct_no ";
							
			       stmt=conn.prepareStatement(q);
			       System.out.println("stmt------"+stmt);
			       int j = 1;
			       if(status != ""  && status != null && status !="null" ) {	
						if(!status.equals("all")){
							stmt.setString(j, status);
							j += 1;
						}
						
					}	
			   	if(we_pe_no != "" && we_pe_no != null) {
			   		stmt.setString(j, we_pe_no);
					j += 1;
					}
				if(modification != "" && modification != null) {
					stmt.setString(j, modification);
					j += 1;
					}
				if(mct_no != "" && mct_no != null) {
					stmt.setString(j, mct_no);
					
				}
				if(roleAccess.equals("Line_dte")) {
					stmt.setString(j, roleSusNo);
					
				}
			    System.out.println("trans under modi=----------="+stmt);
			      ResultSet rs = stmt.executeQuery();      
			      ResultSetMetaData metaData = rs.getMetaData();

			      int columnCount = metaData.getColumnCount();
			      while (rs.next()) {
		 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

		 	            for (int i = 1; i <= columnCount; i++) {
		 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	            }
		 	            
		 	           String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("+rs.getObject(6)+")}else{ return false;}\"";
						String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
						
						String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("+rs.getObject(6)+");$('#rrr"+rs.getObject(6)+"').attr('data-target','#rejectModal')}else{ return false;}\"";
						String rejectButton ="<i id='rrr"+rs.getObject(6)+"' class='action_icons action_reject' "+Reject+" title='Reject Data' data-toggle='modal'  ></i>";

						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("+rs.getObject(6)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("+rs.getObject(6)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
						
						String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
						String LogButton = cueContoller.Get_button_info(metaData,rs);
		                String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
		 	  			String f = "";
		 	  			if(status.equals("0")){
		 	  				if(roleType.equals("ALL")) {
								f += appButton;
								f += rejectButton;
								f += deleteButton;
								f += updateButton;
								f += LogButton;
							}
							if(roleType.equals("APP")) {
								f += appButton;
								f += rejectButton;
								f += LogButton;
							}
							if(roleType.equals("DEO") && roleAccess.equals("Line_dte")) {
								f += deleteButton;
								f += updateButton;
								//f += LogButton;
							}
		 	      	    		
		 	  			}else if(status.equals("1")){
		 	  				if(roleType.equals("APP") || roleType.equals("ALL")){
		 	  					f += rejectButton;
		 	  					f += LogButton1;
		 	  				}
		 	  					if(roleType.equals("DEO")){
		 	  						f += appButton1;
		 	  						f += LogButton1;
		 	  					}
		 	  				
		 	  			}else if(status.equals("2")){
		 	  				if(roleType.equals("APP") || roleType.equals("ALL")){
		 	  					f += appButton;	
		 	  				}
		 	  				if(roleType.equals("DEO") || roleType.equals("ALL") || roleAccess.equals("Line_dte")){
		 	  						
		 	  					f += deleteButton;
								f += updateButton;		
		 	  					}
		 	  					
		 	      				
		 	  				}
		 	  			
		 	  				columns.put(metaData.getColumnLabel(6), f);
		 	           
		 	            
		 	            list.add(columns);
		 	        }
			      rs.close();
			      stmt.close();
			      conn.close();
			   }catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
				}
			return list;
		}
		  }
	
	public  List<Map<String, Object>> getAttributeFromTransportModification1(String we_pe_no,String modification,String mct_no, String status, String roleType){
		{			
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			   try{	  
				conn = dataSource.getConnection();				
				 PreparedStatement stmt=null;
				 
				if(!status.equals("all")){
					qry += " and (a.status = '0' or a.status = '2' or a.status = '1')  ";
				}	
				if(we_pe_no != "" && we_pe_no!=null) {
					qry += " and a.we_pe_no = ?";
					
					}
				if(modification != "" && modification != null) {
					qry += " and a.modification = ?";
					
					}
				if(mct_no != "" && mct_no != null) {
					
					qry += " and a.mct_no = ?";
					
				}
				
				if(qry !="")
					qry= " where "+qry.substring(4,qry.length());
				
				q=" select distinct a.we_pe_no,a.modification,a.mct_no,a.amt_inc_dec,a.remarks,a.id,m.mct_nomen as std_nomclature,a.reject_letter_no ,a.reject_remarks,a.scenario,concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit,a.status,d.auth_amt " + 
						" from cue_tb_miso_wepe_transport_mdfs a left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1' " + 
						" left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active' left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active' " + 
						" inner join  tb_tms_mct_main_master m on a.mct_no = cast(m.mct_main_id as text) LEFT JOIN cue_tb_miso_wepe_transport_det d ON a.we_pe_no=d.we_pe_no and a.mct_no=d.mct_no  "+qry+" order by a.status,a.we_pe_no,a.mct_no ";
							
			      stmt=conn.prepareStatement(q);
			     int j=1;
			     if(we_pe_no != "" && we_pe_no!=null) {
			  		stmt.setString(j, we_pe_no);
					j += 1;
					
					}
			     if(modification != "" && modification != null) {
					stmt.setString(j, modification);
					j += 1;
					
					}
				if(mct_no != "" && mct_no != null) {
					stmt.setString(j, mct_no);
					
				}
				  System.out.println("Transport Under Modi 1" + stmt);
			      ResultSet rs = stmt.executeQuery();      
			      ResultSetMetaData metaData = rs.getMetaData();

			      int columnCount = metaData.getColumnCount();
			      while (rs.next()) {
		 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

		 	            for (int i = 1; i <= columnCount; i++) {
		 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	            }
		 	            
		 	           String appButton = "<i class='action_icons action_approved'  title='Approve Data'></i>";
						
						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(6)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(6)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
		 	  			String f = "";

		 	  			if(rs.getObject(12).equals("1")){
								f += appButton;
							}
		 	  			else {
		 	  				f += deleteButton;
							f += updateButton;		
		 	  			}
							
		 	  			
		 	  			columns.put(metaData.getColumnLabel(6), f);
		 	           
		 	            
		 	            list.add(columns);
		 	        }
			      rs.close();
			      stmt.close();
			      conn.close();
			   }catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
				}
			return list;
		}
		  }
		

}