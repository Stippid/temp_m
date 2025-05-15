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

import com.models.psg.Master.TB_LANGUAGE;
import com.persistance.util.HibernateUtil;

public class LanguageDaoImpl implements LanguageDao {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public List<Map<String, Object>>search_LanguageRepo(String language1) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			@SuppressWarnings("unused")
			String flag = "Y";

			if (!language1.equals("")) {
				qry += " Where language like ?";
				flag = "N";
			}

			 
		q = "SELECT id,upper(language) as language FROM  tb_psg_mstr_language"+ qry;
			stmt = conn.prepareStatement(q);
			int j = 1;
			if (!language1.equals("")) {
				stmt.setString(j, language1.toUpperCase() + '%');
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

				String editData = "onclick=\"  if (confirm('Are you sure you want to update This Language?') ){editData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + editData + " title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete This Language?') ){deleteData("
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
	
public TB_LANGUAGE getLanguageByid(int id) {
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
	 	TB_LANGUAGE updateid = (TB_LANGUAGE) sessionHQL.get(TB_LANGUAGE.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();		
		return updateid;
	}
	

public Long checkExitstingLanguage(String language1,int id1) {
	String hql="select count(id) from TB_LANGUAGE where language=:d1  and  id!=:id";
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	Query q2= session.createQuery(hql);
	q2.setParameter("d1", language1);
	
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
	

public String Update_Language(TB_LANGUAGE obj,String username){
Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
Transaction tx = sessionHQL.beginTransaction();

String msg = "";
try{
	String hql = "update TB_LANGUAGE set language=:language,modify_by=:m1,modify_on=:m2 where id=:id";

	Query query = sessionHQL.createQuery(hql).setString("language", obj.getLanguage())
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
