package com.dao.cue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.controller.cue.cueContoller;
import com.models.CUE_TB_MISO_PROVISION_MASTER;
import com.models.CUE_TB_MISO_PROVISION_TRANSPORT_MASTER;
import com.persistance.util.HibernateUtilNA;

public class ProvisionPolicyDAOImpl implements ProvisionPolicyDAO {
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }    

    public String setApprovedProwep(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_MISO_PROVISION_MASTER c set c.status = :status where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
	}
	
	public String setRejectProwep(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_MISO_PROVISION_MASTER c set c.status = :status where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "2" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Rejected Successfully";
		}else {
			return "Rejected not Successfully";
		}
	}
	
	public String setDeleteProwep(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String query = "update CUE_TB_MISO_PROVISION_MASTER c set c.status_delete = :status_delete where c.id = :id";
		int app = session.createQuery( query ).setString( "status_delete", "Inactive" ).setInteger( "id", appid ).executeUpdate();	   
		tx.commit();
		session.close();
		if(app > 0) {
			return "Deleted Successfully";
		}else {
			return "Deleted not Successfully";
		}		
	}
	
	public CUE_TB_MISO_PROVISION_MASTER getEditProWep(int id) {
	   	Session session = HibernateUtilNA.getSessionFactory().openSession();
			session.beginTransaction();
			Query q = session.createQuery("from CUE_TB_MISO_PROVISION_MASTER where id=:id");
			q.setParameter("id", id);
			CUE_TB_MISO_PROVISION_MASTER list = (CUE_TB_MISO_PROVISION_MASTER) q.list().get(0);
			session.getTransaction().commit();
			session.close();
			return list;
	}
	
	///////////////tras///////////	
	public String setApprovedProtra(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_MISO_PROVISION_TRANSPORT_MASTER c set status = :status where id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
	}

	public String setRejectProtra(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_MISO_PROVISION_TRANSPORT_MASTER c set c.status = :status where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "2" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Rejected Successfully";
		}else {
			return "Rejected not Successfully";
		}
	}

	public String setDeleteProtra(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String query = "update CUE_TB_MISO_PROVISION_TRANSPORT_MASTER c set c.status_delete = :status_delete where c.id = :id";
		int app = session.createQuery( query ).setString( "status_delete", "Inactive" ).setInteger( "id", appid ).executeUpdate();	   
		tx.commit();
		session.close();
		if(app > 0) {
			return "Deleted Successfully";
		}else {
			return "Deleted not Successfully";
		}
	}
	
	public CUE_TB_MISO_PROVISION_TRANSPORT_MASTER getEditProTrans(int id) {
	   	Session session = HibernateUtilNA.getSessionFactory().openSession();
			session.beginTransaction();
			Query q = session.createQuery("from CUE_TB_MISO_PROVISION_TRANSPORT_MASTER where id=:id");
			q.setParameter("id", id);
			CUE_TB_MISO_PROVISION_TRANSPORT_MASTER list = (CUE_TB_MISO_PROVISION_TRANSPORT_MASTER) q.list().get(0);
			session.getTransaction().commit();
			session.close();
			return list;
	}
	
	public  List<Map<String, Object>> AttributeReportProvision(String we_pe_no,String letter_no,String force_type,String status,String year_cal,String month_cal,String letter_date) {
		{			
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			   try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				if(!status.equals("all")){
					qry += " (status = '0' or status = '2' or status = '1')  ";
				}
				if(we_pe_no !="" && we_pe_no != null) {
					qry += " and we_pe_no=?";
				}
				if(force_type !="" && force_type != null) {
					qry += " and force_type=?";
				}
				if(year_cal !="" && year_cal != null) {
					qry += " and year_cal=?";
				}
				if(month_cal !="" && month_cal != null) {
					qry += " and month_cal=?";
				}
				
				q=" select id,month_cal,year_cal,letter_no,letter_date,we_pe_no,unit_type,force_type,no_of_units,status from cue_tb_miso_provision_transport_master  WHERE  "+qry+" and (status_delete is null or status_delete='') order by we_pe_no,id desc";			
			      stmt=conn.prepareStatement(q);
			      int j=1;
			      if(we_pe_no !="" && we_pe_no != null) {
			    	  stmt.setString(j, we_pe_no);
			    	  j+=1;
			      }
				
				if(force_type !="" && force_type != null) {
			    	  stmt.setString(j, force_type);
			    	  j+=1;
			      }
				
				if(year_cal !="" && year_cal != null) {
			    	  stmt.setString(j, year_cal);
			    	  j+=1;
			      }
				
				if(month_cal !="" && month_cal != null) {
			    	  stmt.setString(j, month_cal);
			    	  j+=1;
			      }
				
			      ResultSet rs = stmt.executeQuery();      
			      ResultSetMetaData metaData = rs.getMetaData();

			      int columnCount = metaData.getColumnCount();
			      while (rs.next()) {
		 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

		 	            for (int i = 1; i <= columnCount; i++) {
		 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	            }
		 	            
		 	          
						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(1)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(1)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
						String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
						
		 	  			String f = "";
		 	  			
		 	  			if(rs.getObject(10).equals("1")){
	 	  					f += appButton1;
	 	  				}
		 	  			else {
	 	  				f += deleteButton;
						f += updateButton;		
		 	  			}
	 	  				columns.put(metaData.getColumnLabel(1), f);	           
		 	            
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
	
	public  List<Map<String, Object>> AttributeReportSearchProvisionMaster(String status,String year_cal,String month_cal,String we_pe_no,String letter_no,String force_type,String roleType) {
	{			
		  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		   try{	  
			conn = dataSource.getConnection();	
			PreparedStatement stmt=null;
			
			String qry="";
			
			if(month_cal !="" && month_cal != null) {
				qry += "  month_cal=?";
			}
			if(!status.equals("all")){			
				qry += " and status = ?";
			}
			if(we_pe_no !="" && we_pe_no != null) {
				qry += " and we_pe_no=?";
			}
			if(letter_no !="" && letter_no != null) {
				qry += " and letter_no=?";
			}
			if(force_type !="" && force_type != null) {
				qry += " and force_type=?";
			}
			if(year_cal !="" && year_cal != null) {
				qry += " and year_cal=?";
			}
			q=" select id,month_cal,year_cal,letter_no,letter_date,we_pe_no,wet_pet_no,unit_type,force_type,no_of_units,status ,\r\n"
					+ "	created_by as cr_by,created_on as cr_on,\r\n"
					+ "	modified_by as modi_by,modified_on as modi_on  from cue_tb_miso_provision_master WHERE  "+qry+" and (status_delete is null or status_delete='') order by we_pe_no,id desc";			
			 	
		      stmt=conn.prepareStatement(q);
		      int j=1;
		      if(month_cal !="" && month_cal != null) {
		    	  stmt.setString(j, month_cal);
					 j += 1;
				}
				if(!status.equals("all")){			
					 stmt.setString(j, status);
					 j += 1;
				}
				if(we_pe_no !="" && we_pe_no != null) {
					 stmt.setString(j, we_pe_no);
					 j += 1;
				}
				if(letter_no !="" && letter_no != null) {
					 stmt.setString(j, letter_no);
					 j += 1;
				}
				if(force_type !="" && force_type != null) {
					 stmt.setString(j, force_type);
					 j += 1;
				}
				if(year_cal !="" && year_cal != null) {
					 stmt.setString(j, year_cal);
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
 	            
 	            String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("+rs.getObject(1)+")}else{ return false;}\"";
				String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
				
				String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("+rs.getObject(1)+");$('#rrr"+rs.getObject(1)+"').attr('data-target','#rejectModal')}else{ return false;}\"";
				String rejectButton ="<i id='rrr"+rs.getObject(1)+"' class='action_icons action_reject' "+Reject+" title='Reject Data' data-toggle='modal'  ></i>";
				
				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("+rs.getObject(1)+")}else{ return false;}\"";
				String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
				
				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("+rs.getObject(1)+")}else{ return false;}\"";
				String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
				
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
	      	    		
	  			}else if(status.equals("1")){
	  				if(roleType.equals("APP") || roleType.equals("ALL")){
	  					f += rejectButton;
	  				}
  					if(roleType.equals("DEO")){
  						f += appButton;
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
	  			columns.put(metaData.getColumnLabel(1), f);           
	            
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
	
	public  List<Map<String, Object>> AttributeReportProvisionMaster(String we_pe_no,String letter_no,String force_type,String status,String year_cal ,String month_cal,String roleType) {
		{			
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			   try{	  
				conn = dataSource.getConnection();	
				PreparedStatement stmt=null;

				if(!status.equals("all"))
				{
					qry += " (status = '0' or status = '2' or status = '1')  ";
				}
				
				
				if(we_pe_no !="" && we_pe_no != null) {
					qry += " and we_pe_no=? ";
				}
				
				if(force_type !="" && force_type != null) {
					qry += " and force_type=? ";
				}
				
				if(month_cal !="" && month_cal != null) {
					qry += " and month_cal=? ";
				}
				
				if(year_cal !="" && year_cal != null) {
					qry += " and year_cal=? ";
				}
				
				q=" select id,month_cal,year_cal,letter_no,letter_date,we_pe_no,wet_pet_no,unit_type,force_type,no_of_units,status from cue_tb_miso_provision_master WHERE  "+qry+" and (status_delete is null or status_delete='') order by we_pe_no,id desc";			
			      
				stmt=conn.prepareStatement(q);
			      int j=1;
			      if(we_pe_no !="" && we_pe_no != null)
					{
				  		stmt.setString(j, we_pe_no);
						j += 1;
					}
									
					if(force_type !="" && force_type != null)
					{
				  		stmt.setString(j, force_type);
						j += 1;
					}
					
					if(month_cal !="" && month_cal != null)
					{
				  		stmt.setString(j, month_cal);
						j += 1;
					}
					
					if(year_cal !="" && year_cal != null)
					{
				  		stmt.setString(j, year_cal);
					}
				
				
			      ResultSet rs = stmt.executeQuery();      
			      ResultSetMetaData metaData = rs.getMetaData();

			      int columnCount = metaData.getColumnCount();
			      while (rs.next()) {
	 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

	 	            for (int i = 1; i <= columnCount; i++) {
	 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
	 	            } 	       	 	          
	 	            
	 	            String deleteData = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(1)+")}else{ return false;}\"";
					String deletedata ="<i class='action_icons action_delete' "+deleteData+" title='Delete Data'></i>";
					
					String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(1)+")}else{ return false;}\"";
					String editdata ="<i class='action_icons action_update' "+editData+" title='Edit Data'></i>";
					String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
					String f = "";
					if(rs.getObject(11).equals("1")){
 	  					f += appButton1;
 	  				}
					else{
		  				
		      	    	f += deletedata;
		      	    	f += editdata;
		  			}
					
		  			columns.put(metaData.getColumnLabel(1), f); 
		            
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


	public  List<Map<String, Object>> AttributeReportSearchProvisionTrans(String status,String we_pe_no,String letter_no,String force_type,String year_cal,String month_cal,String roleType) {
		{			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q=null;
			   try{	  
				conn = dataSource.getConnection();				 
				PreparedStatement stmt=null;
				
				String qry="";
				
				if(month_cal !="" && month_cal != null) {
					qry += "  month_cal=?";
				}
				if(!status.equals("all")){			
					qry += " and status = ?";
				}
				if(we_pe_no !="" && we_pe_no != null) {
					qry += " and we_pe_no=?";
				}
				if(letter_no !="" && letter_no != null) {
					qry += " and letter_no=?";
				}
				if(force_type !="" && force_type != null) {
					qry += " and force_type=?";
				}		
				if(year_cal !="" && year_cal != null) {
					qry += " and year_cal=?";
				}
				
				q=" select id,month_cal,year_cal,letter_no,letter_date,we_pe_no,wet_pet_no,unit_type,force_type,no_of_units,status ,\r\n"
						+ "created_by as cr_by,created_on as cr_on,\r\n"
						+ "modified_by as modi_by,modified_on as modi_on  from cue_tb_miso_provision_transport_master WHERE  "+qry+" and (status_delete is null or status_delete='') order by we_pe_no,id desc";			
				 	
			      stmt=conn.prepareStatement(q);
			      int j=1;
			      if(month_cal !="" && month_cal != null) {
			    	  stmt.setString(j, month_cal);
						 j += 1;
					}
					if(!status.equals("all")){			
						 stmt.setString(j, status);
						 j += 1;
					}
					if(we_pe_no !="" && we_pe_no != null) {
						 stmt.setString(j, we_pe_no);
						 j += 1;
					}
					if(letter_no !="" && letter_no != null) {
						 stmt.setString(j, letter_no);
						 j += 1;
					}
					if(force_type !="" && force_type != null) {
						 stmt.setString(j, force_type);
						 j += 1;
					}		
					if(year_cal !="" && year_cal != null) {
						 stmt.setString(j, year_cal);
					}
			      
			      ResultSet rs = stmt.executeQuery();      
			      ResultSetMetaData metaData = rs.getMetaData();

			      int columnCount = metaData.getColumnCount();
			      while (rs.next()) {
	 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

	 	            for (int i = 1; i <= columnCount; i++) {
	 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
	 	            } 	            
	 	          
	 	            
	 	            String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("+rs.getObject(1)+")}else{ return false;}\"";
					String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
					
					String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("+rs.getObject(1)+");$('#rrr"+rs.getObject(1)+"').attr('data-target','#rejectModal')}else{ return false;}\"";
					String rejectButton ="<i id='rrr"+rs.getObject(1)+"' class='action_icons action_reject' "+Reject+" title='Reject Data' data-toggle='modal'  ></i>";
		
					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("+rs.getObject(1)+")}else{ return false;}\"";
					String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
					
					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("+rs.getObject(1)+")}else{ return false;}\"";
					String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
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
		      	    		
		  			}else if(status.equals("1")){
		  				if(roleType.equals("APP") || roleType.equals("ALL")){
		  					f += rejectButton;
		  					f += LogButton1;
		  				}
	  					if(roleType.equals("DEO")){
	  						f += appButton;
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
		  			columns.put(metaData.getColumnLabel(1), f);           
		            
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
