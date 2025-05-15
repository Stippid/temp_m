package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_NATIONALITY;
import com.persistance.util.HibernateUtil;

public class Nationality_DAOImpl implements Nationality_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> search_nationlity(int country_id, String nationality_name, String status) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (country_id != 0) {
				qry += " and c.country_id = ? ";
			}

			if (!nationality_name.equals("")) {
				qry += " and upper(c.nationality_name) like upper(?) ";
			}

			if (!status.equals("0")) {
				qry += " and c.status = ?";
				// flag = "N";
			}

			q = "select distinct \r\n" + "c.nationality_id,b.name,c.nationality_name,\r\n" + "m.label as status\r\n"
					+ "from tb_psg_mstr_nationality c \r\n"
					+ "left join tb_psg_mstr_country b on b.id = c.country_id \r\n"
					+ "left join t_domain_value m on m.codevalue=cast(c.status as text) and m.domainid='STATUS_MSTR' \r\n"
					+ "where c.nationality_id !=0" + qry;

			stmt = conn.prepareStatement(q);
			int j = 1;

			if (country_id != 0) {
				stmt.setInt(j, country_id);
				j += 1;
			}

			if (!nationality_name.equals("")) {
				stmt.setString(j, nationality_name.toUpperCase() + '%');
				j += 1;
			}
			if (!status.equals("0")) {
				stmt.setString(j, status);
				j++;
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("name"));
				list.add(rs.getString("nationality_name"));

				String f = "";
				String f1 = "";
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Nationality?') ){editData("
						+ rs.getString("nationality_id") + ")}else{ return false;}\"";
				f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Nationality?') ){deleteData("
						+ rs.getString("nationality_id") + ")}else{ return false;}\"";
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

	public TB_NATIONALITY getnationalityByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		TB_NATIONALITY updateid = (TB_NATIONALITY) sessionHQL.get(TB_NATIONALITY.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}

	public ArrayList<ArrayList<String>> search_nationlityReport() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct \r\n" + "c.nationality_id,b.name,c.nationality_name\r\n"
					+ "from tb_psg_mstr_nationality c \r\n"
					+ "left join tb_psg_mstr_country b on b.id = c.country_id \r\n" + "where c.nationality_id !=0";

			stmt = conn.prepareStatement(q);
			int i = 1;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				String id = String.valueOf(i++);
				list.add(id);
				list.add(rs.getString("name"));
				list.add(rs.getString("nationality_name"));

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
