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
import com.controller.notification.NotificationController;
import com.controller.psg.Transaction.Posting_Out_Controller;
import com.models.CUE_TB_MISO_WEPE_WEAPON_DET;
import com.models.CUE_TB_WEPE_link_sus_perstransweapon;
import com.models.UserLogin;
import com.models.cue_wet_pet;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class StandardAuthWeaponDAOImpl implements StandardAuthWeaponDAO {
	
	private DataSource dataSource;

	 public void setDataSource(DataSource dataSource) {
	        this.dataSource = dataSource;
	    }
	 
	 NotificationController notification = new NotificationController();
		Posting_Out_Controller post = new Posting_Out_Controller();
	
	public String setApprovedSAW(int appid, String username, String we_pe_no) {
		String Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_MISO_WEPE_WEAPON_DET c set c.status =:status, aprv_rejc_by=:aprv_rejc_by, apprv_rejc_on=:date where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).setString("aprv_rejc_by", username).setString("date", Date).executeUpdate();
		tx.commit();
		session.close();
		
		CUE_TB_WEPE_link_sus_perstransweapon notit = getSusno(we_pe_no);
		if(notit.getSus_no()!="") {
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
		String description = "New UE is updated on WE/PE No " + we_pe_no;
		Boolean d = notification.sendNotification(title, description, user_id, notit.getCreated_by());
		}
		
		}
		
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
	}
	
	public CUE_TB_WEPE_link_sus_perstransweapon getSusno(String we_pe_no) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		CUE_TB_WEPE_link_sus_perstransweapon list1 = new CUE_TB_WEPE_link_sus_perstransweapon();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_WEPE_link_sus_perstransweapon where wepe_weapon_no=:we_pe_no and status_weap='1'");
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
	
	public String setDeleteSAW(int deleteid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from CUE_TB_MISO_WEPE_WEAPON_DET where id =:id";
	    Query query = session.createQuery(hql);
	    query.setInteger("id",deleteid);
	    int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Deleted Successfully";
		}else {
			return "Deleted not Successfully";
		}
	}
	
	public CUE_TB_MISO_WEPE_WEAPON_DET getCUE_TB_MISO_WEPE_WEAPON_DETByid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_WEAPON_DET where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_WEPE_WEAPON_DET list = (CUE_TB_MISO_WEPE_WEAPON_DET) q.list().get(0);
		session.getTransaction().commit();
		session.close();		
		return list;
	}
	
	public CUE_TB_MISO_WEPE_WEAPON_DET getCUE_TB_MISO_WEPE_WEAPON_DETByidLoc(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_WEAPON_DET where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_WEPE_WEAPON_DET list = (CUE_TB_MISO_WEPE_WEAPON_DET) q.list().get(0);
		session.getTransaction().commit();
		session.close();		
		return list;
	}
	
	public String UpdateAllValue(CUE_TB_MISO_WEPE_WEAPON_DET Values,String username) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Transaction tx = session.beginTransaction();
		String hql = "update CUE_TB_MISO_WEPE_WEAPON_DET  set auth_weapon=:auth_weapon,remarks =:remarks ,item_seq_no =:item_seq_no,modified_by=:modified_by,modified_on=:modified_on,status='0',vettedby_dte1 = :vettedby_dte1, vettedby_dte2 = :vettedby_dte2 where id=:id";
	    Query query = session.createQuery(hql).setDouble("auth_weapon", Values.getAuth_weapon()).setString("remarks",Values.getRemarks()).setString("item_seq_no",Values.getItem_seq_no()).setString("modified_by",username).setString("modified_on",  modifydate).setString("vettedby_dte1", "")
	    		.setString("vettedby_dte2", "").setInteger("id",Values.getId());
	    int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Updated Successfully"; 
		}else {
			return "Updated not Successfully";
		}
	}

	public  List<Map<String, Object>> AttributeReportSearchStdAuthWpn(Double auth_weapon,String we_pe_no,String item_seq_no, String status, String roleType, String roleAccess) {
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
						qry += " and d.status = ?";
					}
				}		
					
				if(we_pe_no !="" && we_pe_no !=null) 
				{
					qry += " and  d.we_pe_no = ?";
				}
				if(item_seq_no != "" && item_seq_no !=null) {
					qry += " and d.item_seq_no = ?";
				}			
				if(qry.toString().contains("and"))
				{
					qry =qry.substring(4);
				}
					q=" select d.auth_weapon,m.item_type,d.item_seq_no,d.status,d.id,d.reject_letter_no,d.reject_remarks, "
							+ "d.created_by as cr_by,d.created_on as cr_on,d.aprv_rejc_by as app_by,d.apprv_rejc_on as app_on,"
							+ "d.modified_by as modi_by,d.modified_on as modi_on\r\n"
							+ "from cue_tb_miso_wepe_weapon_det d inner join cue_tb_miso_mms_item_mstr m" + 
							" on d.item_seq_no=m.item_code where " +qry+ " order by d.status,d.item_seq_no ";
							
			      stmt=conn.prepareStatement(q);
			      
			      int j = 1;
			       
			       if(status != ""  && status != null && status !="null" ) {	
						if(!status.equals("all")){
							stmt.setString(j, status);
							j += 1;
						}
					}			
					if(we_pe_no !="" && we_pe_no !=null) 
					{
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
		 	           String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("+rs.getObject(5)+")}else{ return false;}\"";
						String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
						
						String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("+rs.getObject(5)+");$('#rrr"+rs.getObject(5)+"').attr('data-target','#rejectModal')}else{ return false;}\"";
						String rejectButton ="<i id='rrr"+rs.getObject(5)+"' class='action_icons action_reject' "+Reject+" title='Reject Data' data-toggle='modal'  ></i>";

						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("+rs.getObject(5)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("+rs.getObject(5)+")}else{ return false;}\"";
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
		 	  				if(roleType.equals("VIEW")) {
								f += appButton;
								f += rejectButton;
								f += LogButton;
								//f += deleteButton;
								//f += updateButton;
							}
							if(roleType.equals("APP")) {
								f += appButton;
								f += rejectButton;
								f += LogButton;
							}
							if(roleType.equals("DEO")  || roleAccess.equals("Line_dte")) {
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
								f += updateButton;
								f += deleteButton;
		 	  					}
		 	  					
		 	      				
		 	  				}
		 	  			
		 	  				columns.put(metaData.getColumnLabel(5), f);
		 	           
		 	            
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
	
	
	public  List<Map<String, Object>> AttributeReportSearchStdAuthWpn1(String we_pe,String auth_weapon,String we_pe_no,String item_seq_no,String status, String roleType,String roleAccess ) {
		{	
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			   try{	  
				conn = dataSource.getConnection();	
				 PreparedStatement stmt=null;	
				
				if(!status.equals("all")){
					qry += " and (d.status = '0' or d.status = '2' or d.status = '1')  ";
				}
						
				if(we_pe_no !="" && we_pe_no !=null) 
				{
					qry += " and  d.we_pe_no = ?";
				}
				if(item_seq_no != "" && item_seq_no !=null) {
					qry += " and d.item_seq_no = ?";
				}			
				if(qry.toString().contains("and"))
				{
					qry =qry.substring(4);
				}
				
					q=" select d.auth_weapon,m.item_type,d.item_seq_no,d.status,d.id,d.reject_letter_no,d.reject_remarks from cue_tb_miso_wepe_weapon_det d inner join cue_tb_miso_mms_item_mstr m" + 
							" on d.item_seq_no=m.item_code where " +qry+ " order by d.status,d.item_seq_no ";
				
					
					
			      stmt=conn.prepareStatement(q);
			      int j=1;
			     		
					if(we_pe_no !="" && we_pe_no !=null) 
					{
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
		 	            
		 	            String appButton = "<i class='action_icons action_approved'  title='Approve Data'></i>";
						
						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(5)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(5)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
		 	  			String f = "";
		 	  			if(rs.getObject(4).equals("1")){
								
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
							
		 	  			
		 	  				columns.put(metaData.getColumnLabel(5), f);
		 	           
		 	            
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
