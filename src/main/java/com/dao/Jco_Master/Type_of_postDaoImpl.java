package com.dao.Jco_Master;

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

import com.model.Jco_Master.TB_Type_Of_Post;
import com.models.psg.Master.TB_LANGUAGE;
import com.persistance.util.HibernateUtil;

public class Type_of_postDaoImpl implements Type_of_postDao {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public List<Map<String, Object>>search_type_of_postRepo(String type_of_post1,String status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			@SuppressWarnings("unused")
			String flag = "Y";

			
			
			if (!status.equals("0") ) {
				qry += " where status = ?";
			}

			if (!type_of_post1.equals("")) {
				qry += " and type_of_post = ?";
				flag = "N";
			}
			 
		q = "SELECT id,type_of_post FROM  tb_psg_mstr_type_of_post"+ qry;
		
		
			stmt = conn.prepareStatement(q);
			int j = 1;
			
			
			if (!status.equals("0") ) {
				stmt.setString(j, status);
				j++;
			}
			
			if (!type_of_post1.equals("")) {
				stmt.setString(j, type_of_post1);
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

				String editData = "onclick=\"  if (confirm('Are you sure you want to update This Type of Post?') ){editData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + editData + " title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete This Type of Post?') ){deleteData("
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

	
public TB_Type_Of_Post gettype_of_postByid(int id) {
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
	 	TB_Type_Of_Post updateid = (TB_Type_Of_Post) sessionHQL.get(TB_Type_Of_Post.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();		
		return updateid;
	}
	

public Long checkExitstingtype_of_post(String type_of_post1,int id1) {
	;
	String hql="select count(id) from TB_Type_Of_Post where type_of_post=:d1  and  id!=:id";
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	Query q2= session.createQuery(hql);
	q2.setParameter("d1", type_of_post1);
	
	q2.setParameter("id", id1);
	Long count_list2 =  (Long) q2.uniqueResult();	
	;
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
	

public String Update_type_of_post(TB_Type_Of_Post obj,String username){
Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
Transaction tx = sessionHQL.beginTransaction();

String msg = "";
try{
	String hql = "update TB_Type_Of_Post set type_of_post=:type_of_post,modify_by=:m1,modify_on=:m2 where id=:id";

	Query query = sessionHQL.createQuery(hql).setString("type_of_post", obj.getType_of_post())
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
	
	
}
