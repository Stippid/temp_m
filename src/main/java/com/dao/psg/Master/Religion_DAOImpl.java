package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_RELIGION;
import com.persistance.util.HibernateUtil;

public class Religion_DAOImpl implements Religion_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> search_religion_name(String religion_name, String status) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!religion_name.equals("")) {
				qry += " and upper(c.religion_name) like upper(?) ";
			}

			if (!status.equals("0")) {
				qry += " and upper(status) = ?";
				// flag = "N";
			}

			q = "select distinct \r\n" + "c.religion_id, \r\n" + "upper(c.religion_name) as religion_name, \r\n" + "m.label as status \r\n"
					+ "from tb_psg_mstr_religion c  \r\n"
					+ "left join t_domain_value m on m.codevalue=cast(c.status as text) and m.domainid='STATUS_MSTR'\r\n"
					+ "where c.religion_id !=0" + qry;

			stmt = conn.prepareStatement(q);
			int j = 1;

			if (!religion_name.equals("")) {
				stmt.setString(j, religion_name.toUpperCase() + '%');
				j += 1;
			}
			if (!status.equals("0")) {
				stmt.setString(j, status.toUpperCase());
				j++;
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("religion_name"));

				String f = "";
				String f1 = "";
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Religion?') ){editData("
						+ rs.getString("religion_id") + ")}else{ return false;}\"";
				f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Religion?') ){deleteData("
						+ rs.getString("religion_id") + ")}else{ return false;}\"";
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

	public TB_RELIGION getreligionByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		TB_RELIGION updateid = (TB_RELIGION) sessionHQL.get(TB_RELIGION.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}

	public ArrayList<ArrayList<String>> search_religionReport() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct \r\n" + "c.religion_id, \r\n" + "c.religion_name \r\n"
					+ "from tb_psg_mstr_religion c  \r\n" + "where c.religion_id !=0";

			stmt = conn.prepareStatement(q);
			int i = 1;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				String id = String.valueOf(i++);
				list.add(id);
				list.add(rs.getString("religion_name"));

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
