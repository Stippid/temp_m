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
import com.models.CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES;
import com.persistance.util.HibernateUtilNA;

public class IncrementDecrementDAOImpl implements IncrementDecrementDAO {
	
	private DataSource dataSource;	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
   
    public List<Map<String, Object>> getBase_autoDetail(String val,String item_code) 
	{			
		 List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		   try{	  
			conn = dataSource.getConnection();
			
			if(val != "" && val !=null) {
				qry += " and we_pe_no = ?";
			}
			
			if(item_code != "" && item_code !=null) {
				qry += " and item_seq_no = ?";
			}
			q="select auth_weapon from cue_tb_miso_wepe_weapon_det where id!=0 "+qry;
		      PreparedStatement stmt=conn.prepareStatement(q);
		      int j=1;
		      
				if(val != "" && val !=null) {
					stmt.setString(j, val);
					j += 1;
				}
				if(item_code != "" && item_code !=null) {
					stmt.setString(j, item_code);
				}
				ResultSet rs = stmt.executeQuery();
				   ResultSetMetaData metaData = rs.getMetaData();
				   int columnCount = metaData.getColumnCount();
				      while (rs.next()) {
			 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

			 	            for (int i = 1; i <= columnCount; i++) {
			 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			 	            }
			 	            list2.add(columns);
			 	        }
     		    rs.close();
     	    	stmt.close();
     			conn.close();
     		} catch (SQLException e) {
     			e.printStackTrace();
     		}	
     		return list2;
	}
		  
   public String setApprovedIncrementDecrement(int appid,String username){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String hqlUpdate = "update CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES c set c.status = :status, aprv_rejc_by=:aprv_rejc_by, date_of_apprv_rejc=:date where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).setInteger( "id", appid ).setString("aprv_rejc_by", username).setString("date", Date).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
	}
    
  	public String setDeleteIncrement(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES where id = :id";
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
	
  	public CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES getCUE_TB_MISO_WEPE_WEAPON_FOOTNOTESByid(int id) {
    	Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES list = (CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES) q.list().get(0);
		session.getTransaction().commit();
		session.close();		
		return list;
	}
	
	public String UpdateWepeMasterValue(CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES WepeMasterValue){
		 	Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hql = "update CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES  set item_seq_no=:item_seq_no,scenario=:scenario,location=:location,formation=:formation,amt_inc_dec=:amt_inc_dec,condition=:condition,remarks=:remarks, status ='0' where id=:id";
		    Query query = session.createQuery(hql).setString("item_seq_no", WepeMasterValue.getItem_seq_no()).setString("scenario", WepeMasterValue.getScenario()).setString("location", WepeMasterValue.getLocation()).setString("formation", WepeMasterValue.getFormation()).setDouble("amt_inc_dec", WepeMasterValue.getAmt_inc_dec()).setString("condition", WepeMasterValue.getCondition()).setString("remarks", WepeMasterValue.getRemarks()).setInteger("id",WepeMasterValue.getId());
		    int rowCount = query.executeUpdate();
			tx.commit();
			session.close();
			if(rowCount > 0) {
				return "Updated Successfully";
			}else {
				return "Updated not Successfully";
			}
	}
	
	public  List<Map<String, Object>> AttributeReportSearchfootnote(String we_pe_no,String item_seq_no,String  status,String  roleType, String roleAccess) {
		{			
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			   try{	  
				conn = dataSource.getConnection();			
				
				
				if(status != ""  && status != null && status !="null" ) {	
					if(!status.equals("all")){
						qry += " and a.status = ?";
					}
					
				}
				if(we_pe_no != "" && we_pe_no !=null) {
					qry += " and a.we_pe_no = ?";
					
				}
				
				if(item_seq_no != "" && item_seq_no !=null) {
					qry += " and a.item_seq_no = ?";
					
				}
				
				 
				  if(qry != "")
				  {
					  qry=" where "+qry.substring(4,qry.length() );
				  }
				  
				q="select a.we_pe_no,a.item_seq_no,m.item_type,(CASE" + 
						"	 WHEN n.auth_weapon IS NULL THEN 0 " + 
						"	  ELSE n.auth_weapon " + 
						"		END) as baseauth,a.amt_inc_dec,a.condition,a.scenario,concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit ,a.reject_letter_no,a.reject_remarks,a.id,a.status,a.remarks,concat(a.formation,a.scenario_unit,a.location) as loc_for_unit_code, "
						+ " a.created_by as cr_by,a.created_on as cr_on,a.aprv_rejc_by as app_by,a.date_of_apprv_rejc as app_on,\r\n"
						+ "	a.modified_by as modi_by,a.modified_on as modi_on "
						+ "from cue_tb_miso_wepe_weapon_footnotes a " + 
						" left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1' left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active' left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active'" + 
						" inner join cue_tb_miso_mms_item_mstr m on a.item_seq_no=m.item_code left outer join cue_tb_miso_wepe_weapon_det n on a.we_pe_no=n.we_pe_no and a.item_seq_no=n.item_seq_no "+ 
						"  "+qry+" order by a.status,a.item_seq_no";
				
			      PreparedStatement stmt=conn.prepareStatement(q);
			      int j=1;
			      if(status != ""  && status != null && status !="null" ) {	
						if(!status.equals("all")){
							stmt.setString(j, status);
							j += 1;
						}
						
					}
					if(we_pe_no != "" && we_pe_no !=null) {
						stmt.setString(j, we_pe_no);
						j += 1;
						
					}
					
					if(item_seq_no != "" && item_seq_no !=null) {
						stmt.setString(j, item_seq_no);
					
					}
					
			      ResultSet rs = stmt.executeQuery();      
			      ResultSetMetaData metaData = rs.getMetaData();

			      int columnCount = metaData.getColumnCount();
			      while (rs.next()) {
		 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

		 	            for (int i = 1; i <= columnCount; i++) {
		 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	            }
		 	           String LogButton = cueContoller.Get_button_info(metaData,rs);
		               String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
		 	           String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("+rs.getObject(11)+")}else{ return false;}\"";
						String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
						
						String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject?') ){Reject("+rs.getObject(11)+");$('#rrr"+rs.getObject(11)+"').attr('data-target','#rejectModal')}else{ return false;}\"";
						String rejectButton ="<i id='rrr"+rs.getObject(11)+"' class='action_icons action_reject' "+Reject+" title='Reject Data' data-toggle='modal'  ></i>";

						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("+rs.getObject(11)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("+rs.getObject(11)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
		 	          
						String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
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
							if(roleType.equals("DEO") || roleAccess.equals("Line_dte")) {
								f += deleteButton;
								f += updateButton;
							//	f += LogButton;
							}
							if( roleType.equals("VIEW") && roleAccess.equals("MISO") ) {
	  							f += appButton;
	  							f += rejectButton;
	  							f += LogButton;
	  						}
//	  						if(  roleAccess.equals("Line_dte") ) {
//	  							f += deleteButton;
//	  							f += updateButton;
//	  							f += LogButton;
//	  						}
	
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
		 	  			
		 	  				columns.put(metaData.getColumnLabel(11), f);
		 	           
		 	            
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
	
	public  List<Map<String, Object>> AttributeReportSearchfootnote1(String  we_pe_no,String  amt_inc_dec,String  item_seq_no,String  status,String  roleType,String roleAccess) {
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
				
				if(we_pe_no != "" && we_pe_no !=null) {
					qry += " and a.we_pe_no = ?";
				}
				
				if(item_seq_no != "" && item_seq_no !=null) {
					qry += " and a.item_seq_no =?";
					
				}
				if(amt_inc_dec != "" && amt_inc_dec !=null && amt_inc_dec !="0" && !amt_inc_dec.equals("0")) {
					qry += " and a.amt_inc_dec = ?";
					
				}
				 
				  if(qry != "")
				  {
					  qry=" where "+qry.substring(4,qry.length() );
				  }
				/*q="select a.we_pe_no,a.item_seq_no,m.item_type,n.auth_weapon as baseauth,a.amt_inc_dec,a.condition,a.scenario,concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit ,a.reject_letter_no,a.reject_remarks,a.id,a.status,a.remarks,concat(a.formation,a.scenario_unit,a.location) as loc_for_unit_code from cue_tb_miso_wepe_weapon_footnotes a " + 
						" left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1' left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active' left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active'" + 
						" inner join cue_tb_miso_mms_item_mstr m on a.item_seq_no=m.item_code left outer join cue_tb_miso_wepe_weapon_det n on a.we_pe_no=n.we_pe_no and a.item_seq_no=n.item_seq_no "+ 
						"  "+qry+" order by a.status,a.item_seq_no";
				*/
				  
				  q="select a.we_pe_no,a.item_seq_no,m.item_type,(CASE\r\n"
							+ "    WHEN n.auth_weapon IS NULL THEN 0\r\n"
							+ "    ELSE n.auth_weapon\r\n"
							+ "END) as baseauth,a.amt_inc_dec,a.condition,a.scenario,concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit ,a.reject_letter_no,a.reject_remarks,a.id,a.status,a.remarks,concat(a.formation,a.scenario_unit,a.location) as loc_for_unit_code from cue_tb_miso_wepe_weapon_footnotes a " + 
							" left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1' left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active' left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active'" + 
							" inner join cue_tb_miso_mms_item_mstr m on a.item_seq_no=m.item_code left outer join cue_tb_miso_wepe_weapon_det n on a.we_pe_no=n.we_pe_no and a.item_seq_no=n.item_seq_no "+ 
							"  "+qry+" order by a.status,a.item_seq_no";
				  
			       stmt=conn.prepareStatement(q);
			      int j=1;
			      if(we_pe_no != "" && we_pe_no !=null) {
						stmt.setString(j, we_pe_no);
						j+=1;
						
					}
			      if(item_seq_no != "" && item_seq_no !=null) {
						stmt.setString(j, item_seq_no);
						j+=1;
						
					}
			      if(amt_inc_dec != "" && amt_inc_dec !=null && amt_inc_dec !="0" && !amt_inc_dec.equals("0")) {
						stmt.setString(j, amt_inc_dec);
						
						
					}
			      ResultSet rs = stmt.executeQuery();      
			      ResultSetMetaData metaData = rs.getMetaData();

			      int columnCount = metaData.getColumnCount();
			      while (rs.next()) {
		 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

		 	            for (int i = 1; i <= columnCount; i++) {
		 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	            }
		 	           String appButton = "<i class='action_icons action_approved'  title='Approve Data'></i>";	
						
						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(11)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(11)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
		 	  			String f = "";
		 	  			if(rs.getObject(12).equals("1")){
//								f += appButton;
//								f += updateButton;
		 	  				
		 	  				//if(roleType.equals("DEO") || roleAccess.equals("Line_dte"))
			 	  				if(roleType.equals("ALL")) {
									f += appButton;
									f += deleteButton;
									f += updateButton;
									
								}
								if(roleType.equals("APP")) {
									f += appButton;
									f += deleteButton;
									//f += updateButton;
									}
								if(roleType.equals("DEO") || roleAccess.equals("Line_dte")) {
									f += appButton;
									}
								if( roleType.equals("VIEW") && roleAccess.equals("MISO") ) {
		  							f += appButton;
		  							
		  							}
							}
		 	  			else {
		 	  				f += deleteButton;
							f += updateButton;		
		 	  			}
							
		 	  			
		 	  				columns.put(metaData.getColumnLabel(11), f);
		 	           
		 	            
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
	public  List<Map<String, Object>> AttributeReportSearchfootnote24(String we_pe_no,String status) {
		{			
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String qry="";
			String q=null;
			   try{	  
				   
				conn = dataSource.getConnection();			
				PreparedStatement stmt=null;
				if(status != ""  && status != null && status !="null" ) {	
					if(!status.equals("all")){
						qry += " and a.status = ?";
					}
					
				}
				if(we_pe_no != "" && we_pe_no !=null) {
					qry += " and a.we_pe_no = ?";
					
				}
			
				if(qry != "")
				{
					qry=" where "+qry.substring(4,qry.length() );
				}
					
				q="select a.we_pe_no,a.item_seq_no,m.item_type,n.auth_weapon as baseauth,a.amt_inc_dec,a.condition,a.scenario,concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit ,a.reject_letter_no,a.reject_remarks,a.id,a.status,a.remarks,concat(a.formation,a.scenario_unit,a.location) as loc_for_unit_code from cue_tb_miso_wepe_weapon_footnotes a " + 
					" left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1' left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active' left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active'" + 
					" inner join cue_tb_miso_mms_item_mstr m on a.item_seq_no=m.item_code left outer join cue_tb_miso_wepe_weapon_det n on a.we_pe_no=n.we_pe_no and a.item_seq_no=n.item_seq_no "+ 
					"  "+qry+" order by a.status,a.item_seq_no";
				
			      stmt=conn.prepareStatement(q);
			      
			      int j=1;
			      if(status != ""  && status != null && status !="null" ) {	
						if(!status.equals("all")){
							stmt.setString(j, status);
							j += 1;
						}
						
					}
					if(we_pe_no != "" && we_pe_no !=null) {
						stmt.setString(j, we_pe_no);
						j += 1;
						
					}
				
			      
			      
			      ResultSet rs = stmt.executeQuery();      
			      ResultSetMetaData metaData = rs.getMetaData();

			      int columnCount = metaData.getColumnCount();
			      while (rs.next()) {
		 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

		 	            for (int i = 1; i <= columnCount; i++) {
		 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	            }
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