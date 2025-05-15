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
import com.models.CUE_TB_MISO_WEPE_PERS_MDFS;
import com.persistance.util.HibernateUtilNA;

public class WepePersMdfsDAOImpl implements WepePersMdfsDAO{
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }    

    public List<String> updaterejectactionqrywepers(String model,String remarks,int id,String letter_no)
    {
    	Connection conn = null;
    	List<String> list2 = new ArrayList<String>();
		int out=0;
		String qry="";
		   try{	  
			conn = dataSource.getConnection(); 
				 PreparedStatement stmt=null;
				 
				 if(remarks!=null && letter_no==null)
					{
					qry = String.format("update %s set reject_remarks=?,status ='2',reject_date='"+new Date()+"' where id =? and status !='2'", model);	
					}
					else if(remarks==null && letter_no!=null)
					{
						qry = String.format("update %s set reject_letter_no=?,status ='2',reject_date='"+new Date()+"'  where id =? and status !='2'", model);
					}
					else if(remarks!=null && letter_no!=null)
					{
						qry = String.format("update %s set reject_letter_no=?,reject_remarks=?,status ='2',reject_date='"+new Date()+"'  where id =? and status !='2'", model);
					}
				 
				 stmt=conn.prepareStatement(qry);
				 if(remarks!=null && letter_no==null)
					{
					 stmt.setString(1,remarks.toUpperCase());
					stmt.setInt(2, id);
					}
				 else if(remarks==null && letter_no!=null)
					{
					 stmt.setString(1, letter_no.toUpperCase());
					 stmt.setInt(2, id);
						}
					else if(remarks!=null && letter_no!=null)
					{
						 stmt.setString(1, letter_no.toUpperCase());
						 stmt.setString(2, remarks.toUpperCase());
						 stmt.setInt(3, id);
						 }
				 
				 out = stmt.executeUpdate();  
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
		   if(out > 0) {
			   list2.add("Rejected Successfully.");				
			}else {
				list2.add("Rejection Failed !");				 
			}
			return list2;
    }
    
    public List<String> updaterejectactionqrywepers1(String model,String remarks,int id,String letter_no, String username)
    {
    	Connection conn = null;
    	List<String> list2 = new ArrayList<String>();
		int out=0;
		String qry="";
		   try{	  
			conn = dataSource.getConnection(); 
				 PreparedStatement stmt=null;
				 
				 if(remarks!=null && letter_no==null)
					{
					qry = String.format("update %s set reject_remarks=?,status ='2', approved_rejected_by=?,reject_date='"+new Date()+"' where id =? and status !='2'", model);	
					}
					else if(remarks==null && letter_no!=null)
					{
						qry = String.format("update %s set reject_letter_no=?,status ='2', approved_rejected_by=?,reject_date='"+new Date()+"'  where id =? and status !='2'", model);
					}
					else if(remarks!=null && letter_no!=null)
					{
						qry = String.format("update %s set reject_letter_no=?,reject_remarks=?,status ='2', approved_rejected_by=?,reject_date='"+new Date()+"'  where id =? and status !='2'", model);
					}
				 
				 stmt=conn.prepareStatement(qry);
				 if(remarks!=null && letter_no==null)
					{
					 stmt.setString(1,remarks.toUpperCase());
					stmt.setInt(3, id);
					 stmt.setString(2,username);
					}
				 else if(remarks==null && letter_no!=null)
					{
					 stmt.setString(1, letter_no.toUpperCase());
					 stmt.setInt(3, id);
					 stmt.setString(2,username);
						}
					else if(remarks!=null && letter_no!=null)
					{
						 stmt.setString(1, letter_no.toUpperCase());
						 stmt.setString(2, remarks.toUpperCase());
						 stmt.setInt(4, id);
						 stmt.setString(3,username);
						 }
				 
				 out = stmt.executeUpdate();  
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
		   if(out > 0) {
			   list2.add("Rejected Successfully.");				
			}else {
				list2.add("Rejection Failed !");				 
			}
			return list2;
    }
    
    
    
    public List<String> updaterejectactionqrywepers2(String model,String remarks,int id,String letter_no, String username)
    {
    	Connection conn = null;
    	List<String> list2 = new ArrayList<String>();
		int out=0;
		String qry="";
		   try{	  
			conn = dataSource.getConnection(); 
				 PreparedStatement stmt=null;
				 
				 if(remarks!=null && letter_no==null)
					{
					qry = String.format("update %s set reject_remarks=?,status ='2', aprv_rejc_by=?,reject_date='"+new Date()+"' where id =? and status !='2'", model);	
					}
					else if(remarks==null && letter_no!=null)
					{
						qry = String.format("update %s set reject_letter_no=?,status ='2', aprv_rejc_by=?,reject_date='"+new Date()+"'  where id =? and status !='2'", model);
					}
					else if(remarks!=null && letter_no!=null)
					{
						qry = String.format("update %s set reject_letter_no=?,reject_remarks=?,status ='2', aprv_rejc_by=?,reject_date='"+new Date()+"'  where id =? and status !='2'", model);
					}
				 
				 stmt=conn.prepareStatement(qry);
				 if(remarks!=null && letter_no==null)
					{
					 stmt.setString(1,remarks.toUpperCase());
					stmt.setInt(3, id);
					 stmt.setString(2,username);
					}
				 else if(remarks==null && letter_no!=null)
					{
					 stmt.setString(1, letter_no.toUpperCase());
					 stmt.setInt(3, id);
					 stmt.setString(2,username);
						}
					else if(remarks!=null && letter_no!=null)
					{
						 stmt.setString(1, letter_no.toUpperCase());
						 stmt.setString(2, remarks.toUpperCase());
						 stmt.setInt(4, id);
						 stmt.setString(3,username);
						 }
				 
				 out = stmt.executeUpdate();  
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
		   if(out > 0) {
			   list2.add("Rejected Successfully.");				
			}else {
				list2.add("Rejection Failed !");				 
			}
			return list2;
    }
    
	  public List<Map<String, Object>> getSearchWEPEReportDetail(String status,String we_pe_no,String modification,String cat_per, String rank_cat,String appt_trade,String arm_code, String rank, String roleType, String roleAccess){
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
			if(we_pe_no != "") {
				qry += " and a.we_pe_no = ?";
				
			}
			if(modification != "") {
				qry += " and a.modification = ?";
				
				}
			if(!cat_per.equals(" ") && !cat_per.equals("")) {			
				qry += " and a.cat_per = ?";
				
			}
			if(!rank_cat.equals(" ") && !rank_cat.equals("")) {
				qry += " and a.rank_cat = ?";
			
			}
			if(appt_trade != "") {
				qry += " and a.appt_trade = ?";
				
			}
				
			if(arm_code != "") {			
				if(cat_per.equals("2") )
					qry += "";
				else
					qry += " and a tbl.arm_code = ?";
					
			}	
			
			if(!rank.equals("")) {			
				qry += " and tbl.rank = ?";
				
			}
			
			if(qry !="")
				qry= " where "+qry.substring(4,qry.length());
			
			
		    q = " select  a.we_pe_no,a.modification,rk1.description app_trade,monew.arm_desc as parent_arm,a.amt_inc_dec::int,dv1.label as cat_per,rk2.description as rank,dv2.label as rank_cat,a.scenario, ba.auth_amt::int as base_amt, a.id, a.status,  \r\n" + 
		    		"concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit  ,a.reject_remarks, a.reject_letter_no ,\r\n"
		    		+ "a.created_by as cr_by,a.created_on as cr_on,a.aprv_rejc_by as app_by,a.date_of_apprv_rejc as app_on,\r\n"
		    		+ "a.modified_by as modi_by,a.modified_on as modi_on  \r\n" + 
		    		"from CUE_TB_MISO_WEPE_PERS_MDFS a \r\n" + 
		    		"left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1'  \r\n" + 
		    		"left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active' \r\n" + 
		    		"left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active' \r\n" + 
		    		"inner join tb_miso_orbat_arm_code monew on  a.arm_code::text = monew.arm_code::text \r\n" + 
		    		"inner join cue_tb_psg_rank_app_master rk1 on a.appt_trade::text = rk1.code::text \r\n" + 
		    		"inner join cue_tb_psg_rank_app_master rk2 on a.rank::text = rk2.code::text  \r\n" + 
		    		"inner join t_domain_value dv1              on dv1.domainid='PERSON_CAT' and dv1.codevalue=a.cat_per  \r\n" + 
		    		"inner join t_domain_value dv2              on dv2.domainid='RANKCATEGORY'  and dv2.codevalue=a.rank_cat  \r\n" + 
		    		"left join   cue_tb_miso_wepe_pers_det ba   on ba.app_trd_code=a.appt_trade and ba.arm_code =a.arm_code and ba.category_of_persn = cat_per and ba.rank = a.rank and ba.rank_cat = a.rank_cat and ba.we_pe_no =a.we_pe_no " + qry + " order by a.we_pe_no,a.status ";
		       stmt=conn.prepareStatement(q);
		      
		      
		      int j =1;
		      if(status != ""  && status != null && status !="null" ) {	
					if(!status.equals("all")){
						stmt.setString(j, status);
						j += 1;
					}
					
				}		
				if(we_pe_no != "") {
					stmt.setString(j, we_pe_no);
					j += 1;
					
				}
				if(modification != "") {
					stmt.setString(j, modification);
					j += 1;
					}
				if(!cat_per.equals(" ") && !cat_per.equals("")) {			
					stmt.setString(j, cat_per);
					j += 1;
				}
				if(!rank_cat.equals(" ") && !rank_cat.equals("")) {
					stmt.setString(j, rank_cat);
					j += 1;
				}
				if(appt_trade != "") {
					stmt.setString(j, appt_trade);
					j += 1;
				}
					
				if(arm_code != "") {			
					if(cat_per.equals("2") )
						{
						stmt.setString(j, arm_code);
						j += 1;
						}
				}	
				
				if(!rank.equals("")) {			
						stmt.setString(j, rank);
				}
				
				ResultSet rs = stmt.executeQuery();      
			      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      while (rs.next()) {
	 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

	 	            for (int i = 1; i <= columnCount; i++) {
	 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
	 	            }
	 	            
	 	           String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("+rs.getObject(11)+")}else{ return false;}\"";
					String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
					
					String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("+rs.getObject(11)+");$('#rrr"+rs.getObject(11)+"').attr('data-target','#rejectModal')}else{ return false;}\"";
					String rejectButton ="<i id='rrr"+rs.getObject(11)+"' class='action_icons action_reject' "+Reject+" title='Reject Data' data-toggle='modal'  ></i>";

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("+rs.getObject(11)+")}else{ return false;}\"";
					String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
					
					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("+rs.getObject(11)+")}else{ return false;}\"";
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
						if(roleType.equals("DEO") || roleAccess.equals("Line_dte")) {
							f += deleteButton;
							f += updateButton;
							//f += LogButton;
							}
						if( roleType.equals("VIEW") && roleAccess.equals("MISO") ) {
  							f += appButton;
  							f += rejectButton;
  							f += LogButton;
  							}
  					/*	if(  roleAccess.equals("Line_dte") ) {
  							f += deleteButton;
  							f += updateButton;
  							f += LogButton;
  							}	
						*/
	 	  			}else if(status.equals("1")){
	 	  				if(roleType.equals("APP") || roleType.equals("ALL")){
	 	  					//f += rejectButton;
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
	
	  
	  public List<Map<String, Object>> getSearchWEPEReportDetail1(String we_pe_no, String status,String modification,String cat_per,String rank_cat,String appt_trade,String arm_code,String rank,String roleType,String roleAccess ){
		  {			
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q="";
			String qry="";
			   try{	 
				   
				conn = dataSource.getConnection();
				PreparedStatement stmt =null;
				
				
				if(!status.equals("all")){
					qry += " and (a.status = '0' or a.status = '2' or a.status = '1')  ";
				}
				
						
				if(we_pe_no != "") {
					qry += " and a.we_pe_no = ?";
					
				}
				if(modification != "") {
					qry += " and a.modification = ?";
					
					}
				if(!cat_per.equals("")) {			
					qry += " and a.cat_per = ?";
					
				}
				if(!rank_cat.equals(" ") && !rank_cat.equals("")) {
					qry += " and a.rank_cat = ?";
					
				}
				if(appt_trade != "") {
					qry += " and a.appt_trade = ?";
					
				}
					
				if(arm_code != "") {			
					if(cat_per.equals("2") )
						qry += "";
					else
						qry += " and a.arm_code = ?";
						
				}	
				
				if(!rank.equals("")) {			
					qry += " and a.rank =?";
					
				}
				
				
				
				if(qry !="")
					qry= " where "+qry.substring(4,qry.length());
			    q = " select  a.we_pe_no,a.modification,rk1.description app_trade,monew.arm_desc as parent_arm,a.amt_inc_dec::int,dv1.label as cat_per,rk2.description as rank,dv2.label as rank_cat,a.scenario, ba.auth_amt::int as base_amt, a.id, a.status,  \r\n" + 
			    		"concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit  ,a.reject_remarks, a.reject_letter_no \r\n" + 
			    		"from CUE_TB_MISO_WEPE_PERS_MDFS a \r\n" + 
			    		"left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1'  \r\n" + 
			    		"left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active' \r\n" + 
			    		"left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active' \r\n" + 
			    		"inner join tb_miso_orbat_arm_code monew on  a.arm_code::text = monew.arm_code::text \r\n" + 
			    		"inner join cue_tb_psg_rank_app_master rk1 on a.appt_trade::text = rk1.code::text \r\n" + 
			    		"inner join cue_tb_psg_rank_app_master rk2 on a.rank::text = rk2.code::text  \r\n" + 
			    		"inner join t_domain_value dv1              on dv1.domainid='PERSON_CAT' and dv1.codevalue=a.cat_per  \r\n" + 
			    		"inner join t_domain_value dv2              on dv2.domainid='RANKCATEGORY'  and dv2.codevalue=a.rank_cat  \r\n" + 
			    		"left join   cue_tb_miso_wepe_pers_det ba   on ba.app_trd_code=a.appt_trade and ba.arm_code =a.arm_code and ba.category_of_persn = cat_per and ba.rank = a.rank and ba.rank_cat = a.rank_cat and ba.we_pe_no =a.we_pe_no " + qry + " order by a.we_pe_no,a.status ";
			    stmt=conn.prepareStatement(q);
			    int j = 1;
			 	if(we_pe_no != "") {
					stmt.setString(j, we_pe_no);
					j += 1;
				}
				if(modification != "") {
					stmt.setString(j, modification);
					j += 1;
					
					}
				if(!cat_per.equals("")) {			
					stmt.setString(j, cat_per);
					j += 1;
					
				}
				if(!rank_cat.equals(" ") && !rank_cat.equals("")) {
					stmt.setString(j, rank_cat);
					j += 1;
					
				}
				if(appt_trade != "") {
					stmt.setString(j, appt_trade);
					j += 1;
					
				}
					
				if(arm_code != "") {			
					if(cat_per.equals("2") )
						qry += "";
					else
						stmt.setString(j, arm_code);
					j += 1;
						
				}	
				
				if(!rank.equals("")) {			
					stmt.setString(j, rank);
					
					
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
								
//		 	  				if(roleType.equals("DEO") || roleAccess.equals("Line_dte"))
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
		
	public String setApprovedWepe(int appid,String username){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String hqlUpdate = "update CUE_TB_MISO_WEPE_PERS_MDFS c set c.status = :status,date_of_apprv_rejc=:date_of_apprv_rejc,aprv_rejc_by=:aprv_rejc_by where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setString( "date_of_apprv_rejc", date ).setString( "aprv_rejc_by", username ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
	}
	
	public String setDeleteWepe(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from CUE_TB_MISO_WEPE_PERS_MDFS where id = :id";
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
	
	public CUE_TB_MISO_WEPE_PERS_MDFS getCUE_TB_MISO_WEPE_PERS_MDFSid(int id) {
    	Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_PERS_MDFS where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_WEPE_PERS_MDFS list = (CUE_TB_MISO_WEPE_PERS_MDFS) q.list().get(0);
		session.getTransaction().commit();
		session.close();		
		return list;
 }
	
	public String UpdateWepeMasterValue(CUE_TB_MISO_WEPE_PERS_MDFS WepeMasterValue){
	 	Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update CUE_TB_MISO_WEPE_PERS_MDFS  set we_pe_no=:we_pe_no,modification=:modification,cat_per=:cat_per,rank_cat=:rank_cat,appt_trade=:appt_trade,arm_code=:arm_code,rank=:rank,amt_inc_dec=:amt_inc_dec,location=:location,formation=:formation,scenario_unit=:scenario_unit,scenario=:scenario, remarks=:remarks,status ='0' where id=:id";
	    Query query = session.createQuery(hql).setString("we_pe_no", WepeMasterValue.getWe_pe_no()).setString("modification", WepeMasterValue.getModification()).setString("cat_per", WepeMasterValue.getCat_per()).setString("rank_cat", WepeMasterValue.getRank_cat()).setString("appt_trade", WepeMasterValue.getAppt_trade()).setString("arm_code", WepeMasterValue.getArm_code()).setString("rank", WepeMasterValue.getRank()).setDouble("amt_inc_dec", WepeMasterValue.getAmt_inc_dec()).setString("location", WepeMasterValue.getLocation()).setString("formation", WepeMasterValue.getFormation()).setString("scenario_unit", WepeMasterValue.getScenario_unit()).setString("scenario", WepeMasterValue.getScenario()).setString("remarks", WepeMasterValue.getRemarks()).setInteger("id",WepeMasterValue.getId());
	    int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Updated Successfully";
		}else {
			return "Updated not Successfully";
		}
}	    
	
}
