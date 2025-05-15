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

import com.models.psg.Master.TB_BATTALION;




import com.persistance.util.HibernateUtil;



public class BattalionDAOImpl implements BattalionDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
	public List<Map<String, Object>> search_Battalion(String battalion_name, String status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			if (!battalion_name.equals("") ) {
				qry += " and upper(battalion_name) like ?";
			}
			
			if (!status.equals("0") ) {
				qry += " and upper(status) like ?";
			}

			 
		q = "SELECT id,battalion_name,status FROM  tb_psg_mstr_battalion where id !=0 "+ qry;
			stmt = conn.prepareStatement(q);
			int j = 1;
			if (!battalion_name.equals("") ) {
				stmt.setString(j, battalion_name.toUpperCase()+"%");
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
					
					String editData = "onclick=\"  if (confirm('Are you sure you want to update This Battalion?') ){editData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + editData + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete This Battalion?') ){deleteData("
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
	
	
@SuppressWarnings("unused")
public TB_BATTALION getBattalionByid(int id) {
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	Transaction tx = sessionHQL.beginTransaction();
	 	TB_BATTALION updateid = (TB_BATTALION) sessionHQL.get(TB_BATTALION.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();		
		return updateid;
	}
	
	
public Long checkExitstingBattalion(String battalion_name1,int id1,String status1) {
	String hql="select count(id) from TB_BATTALION where battalion_name1=:ba1 ,status1=:s1 and  id!=:id";
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	Query q2= session.createQuery(hql);
	q2.setParameter("ba1", battalion_name1);
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




public String Update_Battalion(TB_BATTALION obj,String username){
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
   Transaction tx = sessionHQL.beginTransaction();
  
  String msg = "";
  try{
  	String hql = "update TB_BATTALION set battalion_name=:battalion_name,modify_by=:m1,modify_date=:m2 where id=:id";

		Query query = sessionHQL.createQuery(hql).setString("battalion_name", obj.getBattalion_name())
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

public ArrayList<ArrayList<String>> search_BattalionReport()
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
	
		q="SELECT id,battalion_name FROM  tb_psg_mstr_battalion where id !=0 " ;
			stmt=conn.prepareStatement(q);
			int i = 1;
			ResultSet rs = stmt.executeQuery();      
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				String id = String.valueOf(i++);
				list.add(id);
				list.add(rs.getString("battalion_name"));
				
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
