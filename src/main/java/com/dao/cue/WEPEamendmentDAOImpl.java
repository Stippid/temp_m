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

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.controller.cue.cueContoller;
import com.models.CUE_TB_MISO_WEPE_WEAPONS_MDFS;
import com.models.UploadAamendmentToDocument;
import com.models.cue_wepe;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class WEPEamendmentDAOImpl implements WEPEamendmentDAO{
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
	public String setApprovedWEPEamendment(int appid) {
		
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update UploadAamendmentToDocument c set c.status = :status where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
	}
	
	public String setDeleteWEPEamendment(int appid) {		
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from UploadAamendmentToDocument where id = :id";
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
	
	public UploadAamendmentToDocument getUploadAamendmentToDocumentByid(int id) {		
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from UploadAamendmentToDocument where id=:id");
		q.setParameter("id", id);
		UploadAamendmentToDocument list = (UploadAamendmentToDocument) q.list().get(0);
		session.getTransaction().commit();
		session.close();		
		return list;		
	}
	
	public String UpdateWEPEamendmentValue(UploadAamendmentToDocument artAttValues) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update UploadAamendmentToDocument  set we_pe_no =:we_pe_no,remark=:remark,doc_path=:doc_path,letter_no=:letter_no,status ='0' where id=:id";
	    Query query = session.createQuery(hql).setString("we_pe_no",artAttValues.getWe_pe_no()).setString("remark",artAttValues.getRemark()).setString("doc_path",artAttValues.getDoc_path()).setString("letter_no",artAttValues.getLetter_no()).setInteger("id",artAttValues.getId());
	    int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Updated Successfully";
		}else {
			return "Updated not Successfully";
		}		
	}
	
	public List<UploadAamendmentToDocument> getAmendment_We_PeByid(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = session.beginTransaction();
       Query q = session.createQuery("from UploadAamendmentToDocument where id=:id");
       q.setParameter("id", id);
       @SuppressWarnings("unchecked")
       List<UploadAamendmentToDocument> list = (List<UploadAamendmentToDocument>) q.list();
       tx.commit();
       session.close();
       return list;        
    }

	public List<cue_wepe> getAmendmentDetails_We_PeByid(String val) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from cue_wepe where we_pe_no=:val ") ;
		q.setParameter("val", val);
		@SuppressWarnings("unchecked")
		List<cue_wepe> list = (List<cue_wepe>) q.list();
		tx.commit();
		session.close();
		return list;
	}	
	
	




	public  List<Map<String, Object>> search_uploded_amendment_to_WEPEdocument1(String we_pe_no, String status, String roleType,String from_date,String to_date, String roleAccess) {
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
						qry += " and status =?";
					}
				}
				if(we_pe_no != "") {
					qry += " and we_pe_no = ?";
				}
				if(from_date != "" && to_date != "") {
					qry += " and created_on is not null and  cast(concat(substr(created_on,7,4),'-',substr(created_on,4,2),'-',substr(created_on,1,2)) as Date) between cast(? as Date) and cast(? as Date) ";
				}
				
				if(qry != "")
				  {
					  qry=" where "+qry.substring(4,qry.length() );
				  }
				q= "select distinct id,we_pe_no,letter_no,amdt_title_we_pe,remark,status,reject_letter_no,reject_remarks,vetted_miso,created_by as cr_by,created_on as cr_on from cue_tb_miso_wepe_amdt " +qry+ " order by we_pe_no ";
				
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
					if(from_date != "" && to_date != "") {
						stmt.setString(j, from_date);
						j += 1;
						stmt.setString(j,to_date);
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
	 	             String Delete12 = "onclick=\"  if (confirm('Please Download') ){getWEPE_Amdnment_Image("+rs.getObject(1)+")}else{ return false;}\"";
				     String deleteButton2 ="<a "+Delete12+">Download</a>";

				     String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("+rs.getObject(1)+")}else{ return false;}\"";
						String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
						
						String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject1("+rs.getObject(1)+");$('#rrr"+rs.getObject(1)+"').attr('data-target','#rejectModal')}else{ return false;}\"";
						String rejectButton ="<i id='rrr"+rs.getObject(1)+"' class='action_icons action_reject' "+Reject+" title='Reject Data' data-toggle='modal'  ></i>";

						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("+rs.getObject(1)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("+rs.getObject(1)+")}else{ return false;}\"";
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
								f += LogButton;
							}
							if(roleType.equals("VIEW")) {
								f += deleteButton;
								f += updateButton;
								f += appButton;
								f += rejectButton;
								f += LogButton;
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
		 	  			    columns.put(metaData.getColumnLabel(5), deleteButton2);
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

	public  List<Map<String, Object>> searchinuploadamednment(String we_pe_no, String status, String roleType) {
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
				
				if(we_pe_no != "") {
					qry += " and we_pe_no = ?";
				}
				if(qry != "")
				  {
					  qry=" where "+qry.substring(4,qry.length() );
				  }
				q= "select distinct id,we_pe_no,letter_no,amdt_title_we_pe,remark,status,reject_letter_no,reject_remarks from cue_tb_miso_wepe_amdt " +qry+ " order by we_pe_no ";
				
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
		 	           String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(1)+")}else{ return false;}\"";
					   String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(1)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
		 	  			String f = "";
		 	  				f += deleteButton;
							f += updateButton;		
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
