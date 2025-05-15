package com.dao.mnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.mnh.TB_Med_Authorisation_All;
import com.models.mnh.Tb_Med_Death;
import com.persistance.util.HibernateUtil;

public class mstr_bed_authoDAOIMPL implements mstr_bed_authoDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	 public Long checkExitstingbauth(String sus_no1,int id1) {
		
			String hql="select count(id) from TB_Med_Authorisation_All where sus_no=:d1 and id!=:id";
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q2= session.createQuery(hql);
			q2.setParameter("d1", sus_no1);
			
			q2.setParameter("id", id1);
			Long count_list2 =  (Long) q2.uniqueResult();	
		
			tx.commit();
			session.close();
			if(count_list2 != null)
			{
				return count_list2;
			}
			else
			{
				return (long) 0;
			}
			
		}
	 
		public List<Map<String, Object>> getSearchbauthoMaster(String sus1, String total_all1) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";

			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				String flag = "Y";

				if (!sus1.equals("")) {
					qry += "  b.sus_no = ?";
					
				}
			
				/*q="select distinct b.id,c.unit_name,b.total_all  from tb_miso_orbat_unt_dtl c left join  tb_med_authorisation_all b"
						+ " on c.sus_no = b.sus_no and status_sus_no ='Active'  where " +qry;*/
				
				// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
				q="select distinct b.id,c.unit_name,b.total_all  from orbat_all_details_view_mnh c left join  tb_med_authorisation_all b"
						+ " on c.sus_no = b.sus_no and status_sus_no ='Active'  where " +qry;
				
				stmt = conn.prepareStatement(q);
				int j = 1;
				if (!sus1.equals("")) {
					stmt.setString(j, sus1);
					j++;
				}
				
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + editData + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					String f = "";
					f += updateButton;
					f += deleteButton;
					columns.put(metaData.getColumnLabel(1), f);
					list.add(columns);
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
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
		
		
		
		 public TB_Med_Authorisation_All getbedauthByid(int id) {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 	Transaction tx = sessionHQL.beginTransaction();
			 	TB_Med_Authorisation_All updateid = (TB_Med_Authorisation_All) sessionHQL.get(TB_Med_Authorisation_All.class, id);
				sessionHQL.getTransaction().commit();
				sessionHQL.close();		
				return updateid;
			}
		
		 public String updatebedauth(TB_Med_Authorisation_All obj,String username,int id){
			 
			
	         Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	         Transaction tx = sessionHQL.beginTransaction();
	        
	        String msg = "";
	        
	     try{
	        String hql = "update TB_Med_Authorisation_All set command=:a1,off_auth=:a2,jco_or_auth=:a3,off_fam_auth=:a4,jco_or_fam_auth=:a5,"
					+ "total_all=:a6,laid_out=:a7,others=:a8,sus_no=:a9,modified_by=:modified_by,modified_on=:modified_on"
					+ " where id=:id";
             
                                   
    	  Query query = sessionHQL.createQuery(hql)
    			  .setString("a1",obj.getCommand())
    			  .setInteger("a2", obj.getOff_auth())
					.setInteger("a3", obj.getJco_or_auth() )
					.setInteger("a4",obj.getOff_fam_auth())
					.setInteger("a5",obj.getJco_or_fam_auth())
					.setInteger("a6",obj.getTotal_all())
					.setInteger("a7", obj.getLaid_out())  
					.setInteger("a8", obj.getOthers())	
					.setString("a9", obj.getSus_no())
					.setString("modified_by",obj.getModified_by())
					.setTimestamp("modified_on",obj.getModified_on())
					.setInteger("id",id);
                   
	                    msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
	                    tx.commit(); 
	         }catch (Exception e) {
	               msg = "Data Not Updated.";
	                tx.rollback();
	        }
	       finally {
	              sessionHQL.close();
	        }
	        return msg;
	}
	
}
