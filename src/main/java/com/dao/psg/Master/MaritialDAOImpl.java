package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_MARITAL_STATUS;
import com.persistance.util.HibernateUtil;

public class MaritialDAOImpl implements MaritialDAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> search_maritial_name(String marital_code, String marital_name, String status) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!marital_code.equals("")) {
				qry += " and upper(c.marital_code) like upper(?) ";
			}
			if (!marital_name.equals("")) {
				qry += " and upper(c.marital_name) like upper(?) ";
			}

			if (!status.equals("0")) {
				qry += " and upper(c.status) like ?";
				// flag = "N";
			}

			q = "select distinct c.marital_id,upper(c.marital_code) as marital_code,upper(c.marital_name) as marital_name,m.label as status from tb_psg_mstr_marital_status c \r\n"
					+ "                                        left join t_domain_value m on m.codevalue=cast(c.status as text) and m.domainid='STATUS_MSTR'\r\n"
					+ "					 where c.marital_id  !=0  " + qry;

			stmt = conn.prepareStatement(q);
			int j = 1;

			if (!marital_code.equals("")) {
				stmt.setString(j, marital_code.toUpperCase() + '%');
				j += 1;
			}
			if (!marital_name.equals("")) {
				stmt.setString(j, marital_name.toUpperCase()+ '%');
				j += 1;
			}
			if (!status.equals("0")) {
				stmt.setString(j, status.toUpperCase());
				j++;
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("marital_code"));
				list.add(rs.getString("marital_name"));

				String f = "";
				String f1 = "";
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Maritial Status?') ){editData("
						+ rs.getString("marital_id") + ")}else{ return false;}\"";
				f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Maritial Status?') ){deleteData("
						+ rs.getString("marital_id") + ")}else{ return false;}\"";
				f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				if (status.equals("inactive")) {

					list.add("");
					list.add("");

				} else {
					list.add(f);
					list.add(f1);
				}

				alist.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
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

	public TB_MARITAL_STATUS getmaritialByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		TB_MARITAL_STATUS updateid = (TB_MARITAL_STATUS) sessionHQL.get(TB_MARITAL_STATUS.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}

	public ArrayList<ArrayList<String>> search_maritialReport() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct c.marital_id,c.marital_code,c.marital_name from tb_psg_mstr_marital_status c \r\n"
					+ "					 where c.marital_id  !=0  ";

			stmt = conn.prepareStatement(q);
			int i = 1;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				String id = String.valueOf(i++);
				list.add(id);
				list.add(rs.getString("marital_code"));
				list.add(rs.getString("marital_name"));

				alist.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
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
