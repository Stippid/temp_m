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

//import com.itextpdf.text.log.SysoCounter;
import com.models.UploadWetPetAamendmentToDocument;
import com.models.cue_wepe;
import com.models.cue_wet_pet;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class Cue_Wet_Pet_Amdt_EditDAOImpl implements Cue_Wet_Pet_Amdt_EditDAO{
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	public String setApprovedWetPetAdmtEdit(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update UploadWetPetAamendmentToDocument c set c.status = :status where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
		}

	public String setDeleteWetPetAdmtEdit(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from UploadWetPetAamendmentToDocument where id = :id";
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

	public UploadWetPetAamendmentToDocument getUploadWetPetAamendmentToDocumentByid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from UploadWetPetAamendmentToDocument where id=:id");
		q.setParameter("id", id);
		UploadWetPetAamendmentToDocument list = (UploadWetPetAamendmentToDocument) q.list().get(0);
		session.getTransaction().commit();
		session.close();		
		return list;
	}

	public String UpdateWetPetAdmtEdit(UploadWetPetAamendmentToDocument artAttValues,String username,String modifydate) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update UploadWetPetAamendmentToDocument  set wet_pet_no =:wet_pet_no,doc_path=:doc_path,remark=:remark,letter_no=:letter_no,status ='0' where id=:id";
	    Query query = session.createQuery(hql).setString("wet_pet_no",artAttValues.getWet_pet_no()).setString("doc_path",artAttValues.getDoc_path()).setString("remark",artAttValues.getRemark()).setString("letter_no",artAttValues.getLetter_no()).setInteger("id",artAttValues.getId());
	    int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Updated Successfully";
		}else {
			return "Updated not Successfully";
		}		
	}	
	
	public List<UploadWetPetAamendmentToDocument> getWet_Pet_DownloadByid(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = session.beginTransaction();
       Query q = session.createQuery("from UploadWetPetAamendmentToDocument where id=:id");
       q.setParameter("id", id);
       @SuppressWarnings("unchecked")
       List<UploadWetPetAamendmentToDocument> list = (List<UploadWetPetAamendmentToDocument>) q.list();
       tx.commit();
       session.close();
       return list;        
    }	

	//////////////dwnld img/////////////////////////////
	public List<cue_wet_pet> getEdit_Wet_Pet_DownloadByid(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = session.beginTransaction();
       Query q = session.createQuery("from cue_wet_pet where id=:id");
       q.setParameter("id", id);
       @SuppressWarnings("unchecked")
       List<cue_wet_pet> list = (List<cue_wet_pet>) q.list();
       tx.commit();
       session.close();
       return list;        
    }	
	
	//////////to save table title
	public List<cue_wet_pet> getAmendmentDetails_Wet_PetByid(String val) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from cue_wet_pet where wet_pet_no=:val ") ;
		q.setParameter("val", val);
		@SuppressWarnings("unchecked")
		List<cue_wet_pet> list = (List<cue_wet_pet>) q.list();
		tx.commit();
		session.close();
		return list;
	}	
	
	public  List<Map<String, Object>> getAttributeFromCategorySearchWetPetAmendment( String status,String wet_pet_no,String roleType) {
		{			
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q="";
			String qry="";
			   try{	  
				conn = dataSource.getConnection();
				PreparedStatement stmt= null;
				if(!status.equals("all")){
					qry += " (status = '0' or status = '2' or status = '1')  ";
				}
				
			
				if(wet_pet_no != "") {
					qry += " and wet_pet_no = ?";
					
				}	
				q= " select distinct wet_pet_no,doc_path,letter_no,remark,status,id,reject_remarks,reject_letter_no from cue_tb_miso_wet_pet_amdt where "+qry+" order by wet_pet_no  ";
						
			      stmt=conn.prepareStatement(q);
			     
			       if(wet_pet_no != "" && wet_pet_no !=null) {
						stmt.setString(1, wet_pet_no);
					}
					
			      ResultSet rs = stmt.executeQuery();      
			      ResultSetMetaData metaData = rs.getMetaData();

			      int columnCount = metaData.getColumnCount();
			      while (rs.next()) {
		 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	           for (int i = 1; i <= columnCount; i++) {
		 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	            }
		 	          
						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(6)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(6)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
						
		 	          
		 	  			String f = "";
		 	  			f += deleteButton;
						f += updateButton;	
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
