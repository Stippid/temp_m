package com.dao.psg.Civilian_Master;

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

import com.models.psg.Civilian_Master.TB_PSG_MSTR_PAY_LEVEL;
import com.persistance.util.HibernateUtil;

public class Pay_Level_DAOImpl implements Pay_Level_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> search_pay_level(String pay_level, String status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!pay_level.equals("")) {
				qry += " and upper(pay_level) like ?";
			}
			if (!status.equals("0")) {
				qry += " and upper(status) like ?";
			}

			q = "SELECT id,pay_level,status FROM  tb_psg_mstr_pay_level where id !=0 " + qry;

			stmt = conn.prepareStatement(q);

			int j = 1;
			if (!pay_level.equals("")) {
				stmt.setString(j, pay_level.toUpperCase() + "%");
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

				String editData = "onclick=\"  if (confirm('Are you sure you want to update This Pay Level?') ){editData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + editData + " title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete This Pay Level?') ){deleteData("
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

	public TB_PSG_MSTR_PAY_LEVEL getPay_levelByid(int id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		TB_PSG_MSTR_PAY_LEVEL updateid = (TB_PSG_MSTR_PAY_LEVEL) sessionHQL.get(TB_PSG_MSTR_PAY_LEVEL.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}

	public String Update_pay_level(TB_PSG_MSTR_PAY_LEVEL obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {
			String hql = "update TB_PSG_MSTR_PAY_LEVEL set pay_level=:pay_level,modified_by=:m1,modified_date=:m2 where id=:id";

			Query query = sessionHQL.createQuery(hql).setString("pay_level", obj.getPay_level())
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
	
	public ArrayList<ArrayList<String>> search_Pay_LevelReport(){
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			q="SELECT id,pay_level FROM  tb_psg_mstr_pay_level where id !=0";
			
			
				stmt=conn.prepareStatement(q);
				int i =1;
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					String id = String.valueOf(i++);
					list.add(id);
					list.add(rs.getString("pay_level"));
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
