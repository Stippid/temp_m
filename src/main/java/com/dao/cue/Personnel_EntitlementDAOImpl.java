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

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.controller.cue.cueContoller;
import com.controller.notification.NotificationController;
import com.controller.psg.Transaction.Posting_Out_Controller;
import com.models.CUE_TB_MISO_WEPE_PERS_DET;
import com.models.CUE_TB_WEPE_link_sus_perstransweapon;
import com.models.UserLogin;
import com.persistance.util.HibernateUtilNA;

public class Personnel_EntitlementDAOImpl implements Personnel_EntitlementDAO {

	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    NotificationController notification = new NotificationController();
	Posting_Out_Controller post = new Posting_Out_Controller();
   
	public  List<Map<String, Object>> getSearchPersonnelEntitlementReport(String we_pe_no,String status, String roleType ,String category_of_persn,String rank,String roleAccess) {
		{			
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			   try{	  
				conn = dataSource.getConnection();
				 PreparedStatement stmt=null;
				if(!status.equals("all")){
					qry += " and (tbl.status = '0' or tbl.status = '2' or tbl.status = '1')  ";
				}
				
				if(we_pe_no != "") {
					qry += " and tbl.we_pe_no = ?";
					
				}
				
				if(!category_of_persn.equals("")) {			
					qry += " and tbl.category_of_persn = ?";
				} 
					
				if(rank != "" && rank != null) {			
					qry += " and tbl.description = ?";
				}		
				
				q=	"SELECT tbl.we_pe_no,monew.arm_desc AS arm_code,tbl.appt_trade,tbl.description as rank,tbl.label AS rank_cat,tbl.auth_amt,tdnew.label AS person_cat, tbl.status,tbl.id,tbl.reject_letter_no, tbl.reject_remarks " + 
				   	    " FROM ( SELECT DISTINCT  mst.status,mst.id,mst.arm_code,mst.we_pe_no, mst.app_trd_code,app.description as appt_trade,mst.rank,rk.description,td.label,mst.rank_cat,mst.auth_amt, mst.category_of_persn,mst.reject_letter_no, mst.reject_remarks,td.codevalue " + 
				   	    " FROM cue_tb_miso_wepe_pers_det mst, t_domain_value td , tb_miso_orbat_arm_code mo,cue_tb_psg_rank_app_master rk, cue_tb_psg_rank_app_master app WHERE td.codevalue::text = mst.rank_cat::text and td.domainid::text = 'RANKCATEGORY'::text " + 
				   	    " and mo.arm_code::text = mst.arm_code and upper(rk.level_in_hierarchy) ='RANK' and rk.code=mst.rank and upper(app.level_in_hierarchy) ='APPOINTMENT' and app.code=mst.app_trd_code) tbl,t_domain_value tdnew,tb_miso_orbat_arm_code monew WHERE tbl.category_of_persn::text = tdnew.codevalue::text and tdnew.domainid::text = 'PERSON_CAT'::text " + 
				   	    " and tbl.arm_code::text = monew.arm_code " +qry+ " order by we_pe_no,tbl.codevalue,tdnew.label,tbl.description,tbl.appt_trade,status	 ";
				  stmt=conn.prepareStatement(q);
				  int j=1;
				  if(we_pe_no != "") {
						stmt.setString(j, we_pe_no);
						j += 1;	
					}
					
					if(!category_of_persn.equals("") ) {			
						stmt.setString(j, category_of_persn);
						j += 1;
					}
						
					if(rank != "" && rank != null) {			
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
						
						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(9)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(9)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
		 	  			String f = "";
		 	  			
		 	  			
		 	  			
		 	  			if(rs.getObject(8).toString().equals("1")){
								
					//	if(roleType.equals("DEO") || roleAccess.equals("Line_dte")) {
		 	  				if(roleType.equals("ALL")) {
								f += appButton;
								f += deleteButton;
								f += updateButton;
								
							}
							if(roleType.equals("APP")) {
								f += appButton;
								//f += deleteButton;
								f += updateButton;
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
		 	  			
		 	  			columns.put(metaData.getColumnLabel(9), f);
		 	  			
		 	            
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
    public  List<Map<String, Object>> getSearchPersonnelEntitlementReport1(String we_pe_no,String category_of_persn,String rank,String rank_cat,String status, String roleType,String roleAccess) {
  		{			
  			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  			Connection conn = null;
  			String qry="";
  			String q="";
  			
  			   try{	  
  				conn = dataSource.getConnection();
  				 PreparedStatement stmt =null;
  				 
  				if(status != ""  && status != null && status !="null" ) {	
  					if(!status.equals("all")){
  						qry += " and tbl.status = ?";
  					}
  				}		
  				if(we_pe_no != "") {
  					qry += " and tbl.we_pe_no = ?";
  				}
  				if(!category_of_persn.equals("") ) {			
  					qry += " and tbl.category_of_persn = ?";
  				}
  				if(rank != "" && rank != null) {			
  					qry += " and tbl.description =?";
  				}	
  				if(!rank_cat.equals("") && !rank_cat.equals(" ") && !rank_cat.equals("") && !rank_cat.equals(null)) {			
  					qry += " and tbl.codevalue =?";
  				}

  		    	
  				q=	"SELECT tbl.we_pe_no,monew.arm_desc AS arm_code,tbl.appt_trade,tbl.description as rank,tbl.label AS rank_cat,tbl.codevalue,tbl.auth_amt::int,tdnew.label AS person_cat, tbl.status,tbl.id,tbl.reject_letter_no, tbl.reject_remarks,\r\n"
  						+ "tbl.cr_by,tbl.cr_on,tbl.app_by,tbl.app_on,\r\n"
  						+ "tbl.modi_by,tbl.modi_on  " + 
  				   	    " FROM ( SELECT DISTINCT  mst.status,mst.id,mst.arm_code,mst.we_pe_no, mst.app_trd_code,app.description as appt_trade,mst.rank,rk.description,td.label,mst.rank_cat,mst.auth_amt, mst.category_of_persn,mst.reject_letter_no, mst.reject_remarks,td.codevalue,\r\n"
  				   	    + "mst.created_by as cr_by,mst.created_on as cr_on,mst.aprv_rejc_by as app_by,mst.apprv_rejc_on as app_on,\r\n"
  				   	    + "mst.modified_by as modi_by,mst.modified_on as modi_on  " + 
  				   	    " FROM cue_tb_miso_wepe_pers_det mst, t_domain_value td , tb_miso_orbat_arm_code mo,cue_tb_psg_rank_app_master rk, cue_tb_psg_rank_app_master app WHERE td.codevalue::text = mst.rank_cat::text and td.domainid::text = 'RANKCATEGORY'::text " + 
  				   	    " and mo.arm_code::text = mst.arm_code and upper(rk.level_in_hierarchy) ='RANK' and rk.code=mst.rank and upper(app.level_in_hierarchy) ='APPOINTMENT' and app.code=mst.app_trd_code) tbl,t_domain_value tdnew,tb_miso_orbat_arm_code monew WHERE tbl.category_of_persn::text = tdnew.codevalue::text and tdnew.domainid::text = 'PERSON_CAT'::text " + 
  				   	    " and tbl.arm_code::text = monew.arm_code " +qry+ " order by we_pe_no,tbl.codevalue,tdnew.label,tbl.description,tbl.appt_trade,status	 ";
  			
  				  stmt=conn.prepareStatement(q);
  				int j = 1;
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
  				
  				if(!category_of_persn.equals("") ) {			
  					stmt.setString(j, category_of_persn);
					j += 1;
  				}
  						

  				if(rank != "" && rank != null) {			
  					stmt.setString(j, rank);
  					j += 1;
  				}	
  				if(!rank_cat.equals("") && !rank_cat.equals(" ") && !rank_cat.equals("") && !rank_cat.equals(null)) {				
  					stmt.setString(j, rank_cat);
  				}

  				System.out.println("Search Personal" + stmt);
  			      ResultSet rs = stmt.executeQuery();      
  			      ResultSetMetaData metaData = rs.getMetaData();

  			    int columnCount = metaData.getColumnCount();
  		      while (rs.next()) {
  	 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

  	 	            for (int i = 1; i <= columnCount; i++) {
  	 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
  	 	            }

  	 	            String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("+rs.getObject(10)+")}else{ return false;}\"";
  					String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
  					
  					String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("+rs.getObject(10)+");$('#rrr"+rs.getObject(10)+"').attr('data-target','#rejectModal')}else{ return false;}\"";
					String rejectButton ="<i id='rrr"+rs.getObject(10)+"' class='action_icons action_reject' "+Reject+" title='Reject Data' data-toggle='modal'  ></i>";

  					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("+rs.getObject(10)+")}else{ return false;}\"";
  					String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
  					
  					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("+rs.getObject(10)+")}else{ return false;}\"";
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
  						if(roleType.equals("DEO")) {
  							f += deleteButton;
  							f += updateButton;
  							f += LogButton;
  							}
  						if( roleType.equals("VIEW") && roleAccess.equals("MISO") ) {
  							f += appButton;
  							f += rejectButton;
  							f += LogButton;
  							}
  						if(  roleAccess.equals("Line_dte") ) {
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
  	 	  				if(roleType.equals("DEO") || roleType.equals("ALL")){
  	 	  						
  	 	  					f += deleteButton;
  							f += updateButton;		
  	 	  					}
  	 	  					
  	 	      				
  	 	  				}
  	 	  			
  	 	  				columns.put(metaData.getColumnLabel(10), f);
  	 	           
  	 	            
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
  	
	public String setApprovedPersonal_EntitalARM(int appid,String username, String we_pe_no){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		//Date date= new Date();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String hqlUpdate = "update CUE_TB_MISO_WEPE_PERS_DET c set c.status = :status,apprv_rejc_on=:apprv_rejc_on,aprv_rejc_by=:aprv_rejc_by where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setString("apprv_rejc_on", date).setString("aprv_rejc_by", username).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		
		CUE_TB_WEPE_link_sus_perstransweapon notit = getSusnopers(we_pe_no);
		if(notit.getSus_no()!="")
		{	
		List<UserLogin> userlist = post.getPostIN_outuseridlist(notit.getSus_no());
		
		String user_id = "";
		for (int i = 0; i < userlist.size(); i++) {
			if (i == 0) {
				user_id += String.valueOf(userlist.get(i).getUserId());
			}

			else {
				user_id += "," + String.valueOf(userlist.get(i).getUserId());
				;
			}

		}
		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
//		String we_pe_no1 = notit.getCreated_by_wepon();
		String title = "UE is updated";
		String description = "New UE is updated On WE/PE No " + we_pe_no;
		Boolean d = notification.sendNotification(title, description, user_id, notit.getCreated_by());
		}
		}
		
		
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
	}
	
	public CUE_TB_WEPE_link_sus_perstransweapon getSusnopers(String we_pe_no) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		CUE_TB_WEPE_link_sus_perstransweapon list1 = new CUE_TB_WEPE_link_sus_perstransweapon();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_WEPE_link_sus_perstransweapon where wepe_pers_no=:we_pe_no and status_pers='1'");
		q.setParameter("we_pe_no",we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_WEPE_link_sus_perstransweapon> list = (List<CUE_TB_WEPE_link_sus_perstransweapon>) q.list();	
		session.getTransaction().commit();
		session.close();			
		if(!list.isEmpty())
		{	
		return list.get(0);	
		}
		return list1;
	}
	
	public String setDeletePersonal_EntitalARM(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from CUE_TB_MISO_WEPE_PERS_DET where id = :id";
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
	
	public CUE_TB_MISO_WEPE_PERS_DET getCUE_TB_MISO_WEPE_PERS_DETByid(int id) {
	    Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_PERS_DET where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_WEPE_PERS_DET list = (CUE_TB_MISO_WEPE_PERS_DET) q.list().get(0);
		session.getTransaction().commit();
		session.close();			
		return list;
	}

	public String setUpdatePersonal_EntitalARM(CUE_TB_MISO_WEPE_PERS_DET ac,String username){
	 	Session session = HibernateUtilNA.getSessionFactory().openSession();
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Transaction tx = session.beginTransaction();
		String msg = "";
		try
			{
		String hql = "update CUE_TB_MISO_WEPE_PERS_DET  set auth_amt=:auth_amt,status=:status,modified_by=:modified_by,modified_on=:modified_on,vettedby_dte1 = :vettedby_dte1, vettedby_dte2 = :vettedby_dte2 where id=:id";
	    Query query = session.createQuery(hql).setDouble("auth_amt",ac.getAuth_amt()).setString("status","0").setString("modified_by",username).setString("modified_on", modifydate).setString("vettedby_dte1", "")
		        .setString("vettedby_dte2", "").setInteger("id",ac.getId());
	    int rowCount = query.executeUpdate();
		if(rowCount > 0) {
			msg = "Updated Successfully";
		}
		}	
		catch (Exception e) {
			session.getTransaction().rollback();
		}
		finally{
			tx.commit();
			session.close();
		}
		return msg;
	}
	
	////////////////////
	 public  List<Map<String, Object>> getSummaryPersonnelEntitlementReport(String we_pe_no,String status) {
			{			
				  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Connection conn = null;
				String q="";
				String qry1="";
				   try{	  
					conn = dataSource.getConnection();
					 PreparedStatement stmt =null;
					 if(status != ""  && status != null && status !="null" ) {	
		  					if(!status.equals("all")){
		  						qry1 += " and tbl.status = ?";
		  					}
		  				}
						if(we_pe_no != "") {
							qry1 += " and tbl.we_pe_no = ?";
						}
			 
						q = " select a.rank_cat,  sum(case when person_cat='Regimental' then auth_amt::int else 0 end) as regt,  sum(case when person_cat='ERE' then auth_amt::int else 0 end) as ere,\r\n" + 
								"  sum(case when person_cat='ERE' then auth_amt::int else 0 end) +  sum(case when person_cat='Regimental' then auth_amt::int else 0 end) as total from  \r\n" + 
								"  (  SELECT tbl.we_pe_no,monew.arm_desc AS arm_code,tbl.appt_trade,tbl.description as rank,tbl.label AS rank_cat,\r\n" + 
								"  tbl.auth_amt,tdnew.label AS person_cat, tbl.codevalue,\r\n" + 
								"  tbl.status,tbl.id,tbl.reject_letter_no, tbl.reject_remarks  FROM ( SELECT DISTINCT  mst.status,mst.id,mst.arm_code,mst.we_pe_no, mst.app_trd_code,app.description as appt_trade,mst.rank,rk.description,\r\n" + 
								"  td.label,mst.rank_cat,mst.auth_amt, mst.category_of_persn,mst.reject_letter_no, mst.reject_remarks,codevalue  \r\n" + 
								"  FROM cue_tb_miso_wepe_pers_det mst, t_domain_value td , tb_miso_orbat_arm_code mo,cue_tb_psg_rank_app_master rk,\r\n" + 
								"   cue_tb_psg_rank_app_master app WHERE td.codevalue::text = mst.rank_cat::text and td.domainid::text = 'RANKCATEGORY'::text  \r\n" + 
								"   and mo.arm_code::text = mst.arm_code and upper(rk.level_in_hierarchy)='RANK' and rk.code=mst.rank \r\n" + 
								"   and upper(app.level_in_hierarchy) ='APPOINTMENT' and app.code=mst.app_trd_code order by td.codevalue) tbl,\r\n" + 
								"   t_domain_value tdnew,tb_miso_orbat_arm_code monew WHERE tbl.category_of_persn::text = tdnew.codevalue::text and tdnew.domainid::text \r\n" + 
								"   = 'PERSON_CAT'::text  and tbl.arm_code::text = monew.arm_code "+qry1+"  order by tbl.codevalue  ) " + 
								"  a  group by a.rank_cat,a.codevalue order by a.codevalue  ";
					 stmt=conn.prepareStatement(q);
					 int j = 1;
					 if(status != ""  && status != null && status !="null" ) {	
		  					if(!status.equals("all")){
		  						 stmt.setString(j, status);
		  						 j=j+1;
		  					}
		  				}
					 if(we_pe_no != "") {
						 stmt.setString(j, we_pe_no);
						 
					}
					  
					 
					  System.out.println("Summary Personal" + stmt);
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

	 
	 
	 public  List<Map<String, Object>> getSummaryPersonnelEntitlementReport1(String we_pe_no,String status) {
			{			
				  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Connection conn = null;
				String q="";
				String qry1="";
				   try{	  
					conn = dataSource.getConnection();
					PreparedStatement stmt=null;
					
					if(!status.equals("all")){
						qry1 += " and (tbl.status = '0' or tbl.status = '2' or tbl.status = '1')  ";
					}
					
					if(we_pe_no != "") {
						qry1 +=" and tbl.we_pe_no =?";
						
					}
					//260194
					q = " select a.rank_cat,  sum(case when person_cat='Regimental' then auth_amt else 0 end) as regt,  sum(case when person_cat='ERE' then auth_amt else 0 end) as ere,\r\n" + 
							"  sum(case when person_cat='ERE' then auth_amt else 0 end) +  sum(case when person_cat='Regimental' then auth_amt else 0 end) as total  from  \r\n" + 
							"  (  SELECT tbl.we_pe_no,monew.arm_desc AS arm_code,tbl.appt_trade,tbl.description as rank,tbl.label AS rank_cat,\r\n" + 
							"  tbl.auth_amt,tdnew.label AS person_cat, tbl.codevalue,\r\n" + 
							"  tbl.status,tbl.id,tbl.reject_letter_no, tbl.reject_remarks  FROM ( SELECT DISTINCT  mst.status,mst.id,mst.arm_code,mst.we_pe_no, mst.app_trd_code,app.description as appt_trade,mst.rank,rk.description,\r\n" + 
							"  td.label,mst.rank_cat,mst.auth_amt, mst.category_of_persn,mst.reject_letter_no, mst.reject_remarks,codevalue  \r\n" + 
							"  FROM cue_tb_miso_wepe_pers_det mst, t_domain_value td , tb_miso_orbat_arm_code mo,cue_tb_psg_rank_app_master rk,\r\n" + 
							"   cue_tb_psg_rank_app_master app WHERE td.codevalue::text = mst.rank_cat::text and td.domainid::text = 'RANKCATEGORY'::text  \r\n" + 
							"   and mo.arm_code::text = mst.arm_code "
							+ " and upper(rk.level_in_hierarchy) ='RANK' "
							+ " and rk.code=mst.rank \r\n" + 
							"   and upper(app.level_in_hierarchy) ='APPOINTMENT' and app.code=mst.app_trd_code order by td.codevalue) tbl,\r\n" + 
							"   t_domain_value tdnew,tb_miso_orbat_arm_code monew WHERE tbl.category_of_persn::text = tdnew.codevalue::text and tdnew.domainid::text \r\n" + 
							"   = 'PERSON_CAT'::text  and tbl.arm_code::text = monew.arm_code "+qry1+"  order by tbl.codevalue   ) " + 
							"  a  group by a.rank_cat,a.codevalue order by a.codevalue  ";
					
					 stmt=conn.prepareStatement(q);
					 if(we_pe_no != "") {
						 stmt.setString(1, we_pe_no);
							
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
