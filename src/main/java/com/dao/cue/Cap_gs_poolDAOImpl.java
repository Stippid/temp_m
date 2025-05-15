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

import com.models.CUE_GS_POOL;
import com.persistance.util.HibernateUtilNA;

public class Cap_gs_poolDAOImpl implements Cap_gs_poolDAO{	
	private DataSource dataSource;

	 public void setDataSource(DataSource dataSource) {
	        this.dataSource = dataSource;
	    }		

	public String setApprovedGsPool(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_GS_POOL c set c.status = :status where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}		
	}
	
	public String setDeleteGsPool(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from CUE_GS_POOL where id = :id";
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
	
	public CUE_GS_POOL getCUE_GS_POOLByid(int id) {
    	Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_GS_POOL where id=:id");
    	q.setParameter("id",id);
    	CUE_GS_POOL upid = (CUE_GS_POOL) q.list().get(0);
		session.getTransaction().commit();
		session.close();		
		return upid;
	}
	
	 public String UpdateGsPoolValue(CUE_GS_POOL gsPoolValues){
		 	Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hql = "update CUE_GS_POOL  set auth_amt=:scale,status='0' where id=:id";
		    Query query = session.createQuery(hql).setDouble("scale",gsPoolValues.getAuth_amt()).setInteger("id",gsPoolValues.getId());
		    int rowCount = query.executeUpdate();
			tx.commit();
			session.close();
			if(rowCount > 0) {
				return "Updated Successfully";
			}else {
				return "Updated not Successfully";
			}
	}	 
	 public List<Map<String, Object>> AttributeReportSearchRankCategory(String status,String month,String year,String arm,String rank_cat,String rank,String roleType ){
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
						qry += " and tbl.status = ?";
					}
				}			
				if(month != "") {
					qry += " and tbl.month = ?";
				}
				if(year != "") {
					qry += " and tbl.year = ?";
					}
			
				if(!arm.equals("0") && arm !="" && arm != null) {
					qry += " and tbl.arm = ?";
				}
				if(rank_cat!="" && rank_cat != null) {
					qry += " and tbl.rank_cat = ?";
				}
				if(!rank.equals(" ") && rank != null && rank != "null" && !rank.equals("")) {
					qry += " and tbl.rank = ?";
				}
				
				
				
											
				if(qry == "")
				{
					q="select tbl.month,tbl.year,tbl.arm_desc,tbl.label,r.description,tbl.status,tbl.id,tbl.arm,tbl.rank_cat,tbl.scale,tbl.reject_letter_no,tbl.reject_remarks from " +
						" (select  p.month,p.year,p.arm_desc,td.label,p.rank,p.status,p.id,p.arm,p.rank_cat,p.scale,p.reject_letter_no,p.reject_remarks from " + 
						" (select    wcon.month,wcon.year,a.arm_desc,wcon.rank_cat,wcon.rank,wcon.status,wcon.id,wcon.arm,wcon.scale,wcon.reject_letter_no,wcon.reject_remarks "+
						" from CUE_GS_POOL wcon,tb_miso_orbat_arm_code a where  wcon.arm  =a.arm_code ) p,"+
						" t_domain_value td WHERE td.codevalue  =  p.rank_cat  and td.domainid = 'RANKCATEGORY') tbl ,cue_tb_psg_rank_app_master r where upper(r.level_in_hierarchy) ='RANK' and r.code = tbl.rank order by tbl.status";
					}					
				else
				{					 
					q="select tbl.month,tbl.year,tbl.arm_desc,tbl.label,r.description,tbl.status,tbl.id,tbl.arm,tbl.rank_cat,tbl.auth_amt,tbl.reject_letter_no,tbl.reject_remarks from " +
						" (select  p.month,p.year,p.arm_desc,td.label,p.rank,p.status,p.id,p.arm,p.rank_cat,p.auth_amt,p.reject_letter_no,p.reject_remarks from " + 
						"  CUE_GS_POOL  p,"+
						" t_domain_value td WHERE td.codevalue  =  p.rank_cat  and td.domainid = 'RANKCATEGORY') tbl ,cue_tb_psg_rank_app_master r where upper(r.level_in_hierarchy) ='RANK' and r.code = tbl.rank " + qry + " order by tbl.status ";
					}
			      stmt=conn.prepareStatement(q);
			      
			      int j = 1;
			      
			      if(status != ""  && status != null && status !="null" ) {	
						if(!status.equals("all")){
							stmt.setString(j, status);
							j += 1;
						}
					}			
					if(month != "") {
						stmt.setString(j, month);
						j += 1;
					}
					if(year != "") {
						stmt.setString(j, year);
						j += 1;
						}
				
					if(!arm.equals("0") && arm !="" && arm != null) {
						stmt.setString(j, arm);
						j += 1;
					}
					if(rank_cat!="" && rank_cat != null) {
						stmt.setString(j, rank_cat);
						j += 1;
					}
					if(!rank.equals(" ") && rank != null && rank != "null" && !rank.equals("")) {
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
		 	            
		 	           String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approve("+rs.getObject(7)+")}else{ return false;}\"";
						String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
						
						String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("+rs.getObject(7)+");$('#rrr"+rs.getObject(7)+"').attr('data-target','#rejectModal')}else{ return false;}\"";
						String rejectButton ="<i id='rrr"+rs.getObject(7)+"' class='action_icons action_reject' "+Reject+" title='Reject Data' data-toggle='modal'  ></i>";

						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("+rs.getObject(7)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("+rs.getObject(7)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
						 String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
							
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
		 	  			
		 	  				columns.put(metaData.getColumnLabel(7), f);
		 	           
		 	            
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
	  
	  public List<Map<String, Object>> AttributeReportSearchRankCategory1(String month, String status,String year,String arm,String rank_cat,String rank,String scale,String roleType ){
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
					
						
				if(month != "") {
					qry += " and tbl.month = ?";
				}
				if(year != "") {
					qry += " and tbl.year = ?";
					}
			
				if(!arm.equals("0") && arm !="" && arm != null) {
					qry += " and tbl.arm = ?";
					
				}
				if(rank_cat!="" && rank_cat != null) {
					qry += " and tbl.rank_cat = ?";
					
				}
				if(!rank.equals(" ") && rank != null && rank != "null" && !rank.equals("")) {
					qry += " and tbl.rank = ?";
					
				}
					q="select tbl.month,tbl.year,tbl.arm_desc,tbl.label,r.description,tbl.status,tbl.id,tbl.arm,tbl.rank_cat,tbl.auth_amt,tbl.reject_letter_no,tbl.reject_remarks from " +
						" (select  p.month,p.year,p.arm_desc,td.label,p.rank,p.status,p.id,p.arm,p.rank_cat,p.auth_amt,p.reject_letter_no,p.reject_remarks from " + 
						"  CUE_GS_POOL p,"+
						" t_domain_value td WHERE td.codevalue  =  p.rank_cat  and td.domainid = 'RANKCATEGORY') tbl ,cue_tb_psg_rank_app_master r where upper(r.level_in_hierarchy) ='RANK' and r.code = tbl.rank " + qry + " order by tbl.status ";
			      stmt=conn.prepareStatement(q);
			      int j=1;
			      if(month != "") {
			    	  stmt.setString(j, month);
						j += 1;	
					}
					if(year != "") {
						stmt.setString(j, year);
						j += 1;	
					}
				
					if(!arm.equals("0") && arm !="" && arm != null) {
						stmt.setString(j, arm);
						j += 1;	
					}
					if(rank_cat!="" && rank_cat != null) {
						stmt.setString(j, rank_cat);
						j += 1;	
					}
					if(!rank.equals(" ") && rank != null && rank != "null" && !rank.equals("")) {
						stmt.setString(j, rank);
					}
					
			      System.out.println("sssss: " + stmt);
			      ResultSet rs = stmt.executeQuery();      
			      ResultSetMetaData metaData = rs.getMetaData();

			      int columnCount = metaData.getColumnCount();
			      while (rs.next()) {
		 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

		 	            for (int i = 1; i <= columnCount; i++) {
		 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	            }
		 	            
		 	           String appButton = "<i class='action_icons action_approved'  title='Approve Data'></i>";	
						
						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(7)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(7)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
		 	  			String f = "";
		 	  			if(rs.getObject(6).equals("1")){
								f += appButton;
							}
		 	  			else {
		 	  				f += deleteButton;
							f += updateButton;		
		 	  			}
						columns.put(metaData.getColumnLabel(7), f);
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
