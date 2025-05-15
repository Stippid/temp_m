package com.dao.psg.Master;

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

import com.models.psg.Master.TB_SPECIALIZATION;
import com.persistance.util.HibernateUtil;

public class SpecializationDAOImpl  implements SpecializationDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public List<Map<String, Object>> search_Specialization(String specialization,String status){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			if (!specialization.equals("") ) {
				qry += " and upper(specialization) like ?";
				
			}
			
			if (!status.equals("0") ) {
				qry += " and upper(status) like ?";
				
			}
			 
		q = "SELECT id,upper(specialization) as specialization,status from  tb_psg_mstr_specialization where id !=0 "+ qry;
		
			stmt = conn.prepareStatement(q);
			int j = 1;
			if (!specialization.equals("") ) {
				stmt.setString(j, specialization.toUpperCase()+"%");
				j++;
			}
			
			if (!status.equals("0") ) {
				stmt.setString(j, status.toUpperCase());
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

				if(status.equals("inactive"))
				{
					
					columns.put(metaData.getColumnLabel(1), "");
				}
				else {
					
					String editData = "onclick=\"  if (confirm('Are you sure you want to update This Specialization?') ){editData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + editData + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete This Specialization?') ){deleteData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					String f = "";
					f += updateButton;
					f += deleteButton;
					columns.put(metaData.getColumnLabel(1), f);
				}
				
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


	@Override
	public TB_SPECIALIZATION getSpecializationByid(int id) {
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
	 	TB_SPECIALIZATION updateid = (TB_SPECIALIZATION) sessionHQL.get(TB_SPECIALIZATION.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();		
		return updateid;
	}


	@Override
	public Long checkExitstingSpecialization(String specialization1, int id1, String status1) {
		String hql="select count(id) from TB_SPECIALIZATION where specialization1=:sa1 ,status1=:s1 and  id!=:id";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q2= session.createQuery(hql);
		q2.setParameter("sa1", specialization1);
		q2.setParameter("s1", status1);
		
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


	@Override
	public String Update_Specialization(TB_SPECIALIZATION obj, String username) {
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		   Transaction tx = sessionHQL.beginTransaction();
		  
		  String msg = "";
		  try{
		  	String hql = "update TB_SPECIALIZATION set specialization=:specialization1,modified_by=:m1,modified_date=:m2 where id=:id";

				Query query = sessionHQL.createQuery(hql).setString("specialization", obj.getSpecialization())
						.setString("m1", username)
						.setTimestamp("m2", new Date()).setInteger("id", obj.getId());
				
		          msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
		          
		          tx.commit();
		  }
		  catch (Exception e) {
		          msg = "Data Not Updated.";
		          tx.rollback();
		  }
		  finally {
		          sessionHQL.close();
		  }
		  return msg;

		}
	public ArrayList<ArrayList<String>> search_SpecializationReport()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
			q="SELECT id,specialization,status from  tb_psg_mstr_specialization where id !=0 " ;
				stmt=conn.prepareStatement(q);
				int i = 1;
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					String id = String.valueOf(i++);
					list.add(id);
					list.add(rs.getString("specialization"));
					list.add(rs.getString("status"));
					
					alist.add(list);
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
		return alist;
	}
	
	}
	

