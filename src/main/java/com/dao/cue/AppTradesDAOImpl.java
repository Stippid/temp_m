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

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES;
import com.models.CUE_TB_PSG_RANK_APP_MASTER;
import com.models.TB_TMS_CENSUS_DRR_DIR_DTL;
import com.persistance.util.HibernateUtilNA;

public class AppTradesDAOImpl implements AppTradesDAO {

	private DataSource dataSource;

	 public void setDataSource(DataSource dataSource) {
	        this.dataSource = dataSource;
	    }
	
	public String setApprovedAPPT(int appid,String username) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String approve_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String hqlUpdate = "update CUE_TB_PSG_RANK_APP_MASTER c set c.status =:status,approve_by=:approve_by,approve_date=:approve_date where c.id=:id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setString("approve_by", username).setString("approve_date", approve_date).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
	}
	
	public String setDeleteAPPT(int deleteid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_PSG_RANK_APP_MASTER c set c.status_active =:status where c.id = :id";
		int rowCount = session.createQuery( hqlUpdate ).setString( "status", "Inactive" ).setInteger( "id", deleteid ).executeUpdate();
	 	tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Deleted Successfully";
		}else {
			return "Deleted not Successfully";
		}
	}
	public CUE_TB_PSG_RANK_APP_MASTER getCUE_TB_PSG_RANK_APP_MASTERByid(int id) {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
    	session.setFlushMode(FlushMode.ALWAYS);
    	Transaction tx = session.beginTransaction();	
    	Query q = session.createQuery("from CUE_TB_PSG_RANK_APP_MASTER where id=:id");
    	q.setParameter("id", id);
    	CUE_TB_PSG_RANK_APP_MASTER upid = (CUE_TB_PSG_RANK_APP_MASTER) q.list().get(0);
    	tx.commit();
    	return upid;
	}
	
	public String UpdateAPTValue(CUE_TB_PSG_RANK_APP_MASTER APTValue, String username){
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update CUE_TB_PSG_RANK_APP_MASTER  set level_in_hierarchy=:level_in_hierarchy ,description=:description,modify_by=:modify_by,modify_date=:modify_date,status='0' where id=:id";
		Query query = session.createQuery(hql).setString("level_in_hierarchy", APTValue.getLevel_in_hierarchy()).setString("description",APTValue.getDescription()).setString("modify_by", username).setString("modify_date", modifydate).setInteger("id",APTValue.getId());
	    int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Updated Successfully";
		}else {
			return "Updated not Successfully";
		}
}
	
	public  List<Map<String, Object>> AttributeReportSearchRANKCategory(String status,String parent_code,String level_in_hierarchy,String description,String  roleType) {
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
						qry += " and status = ?";
					}
				}		
				if(parent_code != "" && parent_code !=null) {
					qry += " and parent_code =?";
				}
				if(level_in_hierarchy !="" && level_in_hierarchy !=null) {
					qry += " and level_in_hierarchy =?";
					}
				if(description != "" && description !=null) {
					qry += " and description =?";
					
				}
				
				
				if(qry == "")
				{					
					q="select td.label,p.level_in_hierarchy,p.description,p.status,p.id,p.reject_letter_no,p.reject_remarks from" + 
						" (select    wcon.parent_code,wcon.level_in_hierarchy,wcon.description,wcon.status,wcon.id,wcon.reject_letter_no,wcon.reject_remarks " + 
						" from CUE_TB_PSG_RANK_APP_MASTER wcon WHERE status_active !='Inactive') p,"+
						" t_domain_value td WHERE td.codevalue  =  p.parent_code  and td.domainid = 'RANKCATEGORY'" ;
				}					
				else
				{	
				 	q="select  td.label,p.level_in_hierarchy,p.description,p.status,p.id,p.reject_letter_no,p.reject_remarks from " +
				 			" (select    wcon.parent_code,wcon.level_in_hierarchy,wcon.description,wcon.status,wcon.id,wcon.reject_letter_no,wcon.reject_remarks " + 
				 			" from CUE_TB_PSG_RANK_APP_MASTER wcon WHERE status_active !='Inactive') p," + 
							" t_domain_value td WHERE td.codevalue  =  p.parent_code  and td.domainid = 'RANKCATEGORY' "+qry+" order by p.status " ;  
				}
					
			       stmt=conn.prepareStatement(q);
			       
			       		int j = 1;
				       if(status != ""  && status != null && status !="null" ) {	
							if(!status.equals("all")){
								stmt.setString(j, status);
								j += 1;
							}
							
						}		
						if(parent_code != "" && parent_code !=null) {
							stmt.setString(j, parent_code);
							j += 1;
						}
						if(level_in_hierarchy !="" && level_in_hierarchy !=null) {
							 stmt.setString(j, level_in_hierarchy);
							 j += 1;
						}
						if(description != "" && description !=null) {
							stmt.setString(j, description);
							
						}
			      
			      ResultSet rs = stmt.executeQuery();      
			      ResultSetMetaData metaData = rs.getMetaData();
			      int columnCount = metaData.getColumnCount();

			      while (rs.next()) {
		 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

		 	            for (int i = 1; i <= columnCount; i++) {
		 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	            }
		 	          
		 	           String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
						
		 	           
		 	           String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("+rs.getObject(5)+")}else{ return false;}\"";
						String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
						
						String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("+rs.getObject(5)+");$('#rrr"+rs.getObject(5)+"').attr('data-target','#rejectModal')}else{ return false;}\"";
						String rejectButton ="<i id='rrr"+rs.getObject(5)+"' class='action_icons action_reject' "+Reject+" title='Reject Data' data-toggle='modal'  ></i>";
						
						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("+rs.getObject(5)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("+rs.getObject(5)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
						
		 	          
		 	  			String f = "";
		 	  			if(status.equals("0")){
		 	  				if(roleType.equals("ALL")) {
								f += appButton;
								f += rejectButton;
								f += deleteButton;
								f += updateButton;
							}
							if(roleType.equals("APP")) {
								f += appButton;
								f += rejectButton;
							}
							if(roleType.equals("DEO")) {
								f += deleteButton;
								f += updateButton;
							}
		 	      	    		
		 	  			}else if(status.equals("1")){
		 	  				if(roleType.equals("APP") || roleType.equals("ALL")){
		 	  					f += rejectButton;
		 	  				}
		 	  					if(roleType.equals("DEO")){
		 	  					f += appButton1;
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
	
	public  List<Map<String, Object>> AttributeReportSearchRANKCategory1(String status,String parent_code,String level_in_hierarchy,String description,String roleType) {
		{			
			
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			   try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
	
				if(!status.equals("all")){
					qry += " and (status = '0' or status = '2' or status = '1')  ";
				}
				
			if(parent_code != "" && parent_code !=null) {
					qry += " and parent_code = ?";
				}
				if(level_in_hierarchy !="" && level_in_hierarchy !=null) {
					qry += " and level_in_hierarchy = ?";
					}
				if(description != "" && description !=null) {
					qry += " and description = ?";
				}
				
				q="select  td.label,p.level_in_hierarchy,p.description,p.status,p.id,p.reject_letter_no,p.reject_remarks from " +
					"(select    wcon.parent_code,wcon.level_in_hierarchy,wcon.description,wcon.status,wcon.id,wcon.reject_letter_no,wcon.reject_remarks " + 
					"from CUE_TB_PSG_RANK_APP_MASTER wcon WHERE status_active !='Inactive') p," + 
					" t_domain_value td WHERE td.codevalue  =  p.parent_code  and td.domainid = 'RANKCATEGORY' " + qry + " order by p.status " ;
				
			       stmt=conn.prepareStatement(q);
			      
			        int j=1;
					if(parent_code != "" && parent_code !=null) {
						  stmt.setString(j, parent_code);
							j += 1;
					}
					if(level_in_hierarchy !="" && level_in_hierarchy !=null) {
						  stmt.setString(j, level_in_hierarchy);
							j += 1;
						}
					if(description != "" && description !=null) {
						  stmt.setString(j, description);
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
		 	            
		 	           String appButton = "<i class='action_icons action_approved'  title='Approve Data'></i>";
						
						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(5)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(5)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
		 	  			String f = "";
		 	  			if(rs.getObject(4).equals("1")){
								f += appButton;
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
