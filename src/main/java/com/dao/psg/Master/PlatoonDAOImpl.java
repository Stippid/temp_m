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

import com.models.psg.Master.TB_PLATOON;

import com.persistance.util.HibernateUtil;

public class PlatoonDAOImpl implements PlatoonDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> search_Platoon(String platoon_name,String status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!platoon_name.equals("")) {
				qry += " and upper(platoon_name) like ?";
			}
			
			if (!status.equals("0") ) {
				qry += " and upper(status) like ?";
			}

			q = "select id,upper(platoon_name) as platoon_name,status FROM  tb_psg_mstr_platoon where id != 0" + qry;
			stmt = conn.prepareStatement(q);
			int j = 1;
			if (!platoon_name.equals("")) {
				stmt.setString(j, platoon_name.toUpperCase() + "%");
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
					
					String editData = "onclick=\"  if (confirm('Are you sure you want to update This Platoon?') ){editData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + editData + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete This Platoon?') ){deleteData("
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

	public TB_PLATOON getPlatoonByid(int id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		TB_PLATOON updateid = (TB_PLATOON) sessionHQL.get(TB_PLATOON.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}

	public Long checkExitstingPlatoon(String platoon_name1, int id1,String status1) {
		String hql = "select count(id) from TB_PLATOON where platoon_name1=:pl1  and  id!=:id";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q2 = session.createQuery(hql);
		q2.setParameter("pl1", platoon_name1);
		q2.setParameter("s1", status1);

		q2.setParameter("id", id1);
		Long count_list2 = (Long) q2.uniqueResult();
		tx.commit();
		session.close();
		if (count_list2 != null) {
			return count_list2;
		} else {
			return (long) 0;
		}

	}

	public String Update_Platoon(TB_PLATOON obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {
			String hql = "update TB_PLATOON set platoon_name=:platoon_name,modify_by=:m1,modify_date=:m2 where id=:id";

			Query query = sessionHQL.createQuery(hql).setString("platoon_name", obj.getPlatoon_name())
					.setString("m1", username).setTimestamp("m2", new Date()).setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";

			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	
	public ArrayList<ArrayList<String>> search_PlatoonReport()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
			q="select id,platoon_name FROM  tb_psg_mstr_platoon where id != 0" ;
				stmt=conn.prepareStatement(q);
				int i = 1;
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					String id = String.valueOf(i++);
					list.add(id);
					list.add(rs.getString("platoon_name"));
					
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
