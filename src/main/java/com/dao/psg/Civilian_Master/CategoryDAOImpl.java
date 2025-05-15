package com.dao.psg.Civilian_Master;

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

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Civilian_Master.TB_CATEGORY;
import com.persistance.util.HibernateUtil;

public class CategoryDAOImpl implements CategoryDAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Map<String, Object>> search_category(String category,String status){
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!category.equals("")) {
				qry += " and upper(category) like ?";
			}
			if (!status.equals("0")) {
				qry += " and upper(status) like ?";
			}

			q = "select id,category,status FROM  tb_psg_mstr_category where id !=0 " + qry;

			stmt = conn.prepareStatement(q);

			int j = 1;
			if (!category.equals("")) {
				stmt.setString(j, category.toUpperCase() + "%");
				j++;
			}
			if (!status.equals("0")) {
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

				String editData = "onclick=\"  if (confirm('Are you sure you want to update This Category?') ){editData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + editData + " title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete This Category?') ){deleteData("
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
	
	public TB_CATEGORY getCategortByid(int id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		TB_CATEGORY updateid = (TB_CATEGORY) sessionHQL.get(TB_CATEGORY.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}
	
public ArrayList<ArrayList<String>> search_CategoryReport(){
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			q="SELECT id,category FROM  tb_psg_mstr_category where id !=0";
			
			
				stmt=conn.prepareStatement(q);
				int i =1;
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					String id = String.valueOf(i++);
					list.add(id);
					list.add(rs.getString("category"));
					alist.add(list);
	 	        }
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch (SQLException e) {
				//throw new RuntimeException(e);
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
