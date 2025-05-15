package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_GENDER;
import com.persistance.util.HibernateUtil;

public class GenderDAOImpl implements GenderDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> search_gender_details(String gender_name, String status) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!gender_name.equals("")) {
				qry += " and upper(c.gender_name) like ? ";
			}
			if (!status.equals("0")) {
				qry += " and upper(c.status) =  ? ";
			}

			q = "select distinct \r\n" + "					c.id,\r\n" + "					upper(c.gender_name) as gender_name,\r\n"
					+ "					m.label as status\r\n"
					+ "					FROM tb_psg_mstr_gender c		\r\n"
					+ "					left join t_domain_value m on m.codevalue=cast(c.status as text) and m.domainid='STATUS_MSTR'\r\n"
					+ "					where c.id !=0 " + qry;
			stmt = conn.prepareStatement(q);
			int j = 1;

			if (!gender_name.equals("")) {
				stmt.setString(j, gender_name.toUpperCase() + '%');
				j += 1;
			}

			if (!status.equals("0")) {
				stmt.setString(j, status.toUpperCase());
				j++;
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("gender_name"));

				String f = "";
				String f1 = "";
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Gender?') ){editData("
						+ rs.getString("id") + ")}else{ return false;}\"";
				f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Gender?') ){deleteData("
						+ rs.getString("id") + ")}else{ return false;}\"";
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

	public TB_GENDER getgenderdtlByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		TB_GENDER updateid = (TB_GENDER) sessionHQL.get(TB_GENDER.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}

	/*
	 * public String updategender(TB_GENDER obj,String username,int id){
	 * 
	 * Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 * Transaction tx = sessionHQL.beginTransaction(); String msg = "";
	 * 
	 * try{ String hql =
	 * "update TB_GENDER set gender_name=:gender_name,modified_by=:modified_by,modified_date=:modified_date"
	 * + " where id=:id";
	 * 
	 * Query query = sessionHQL.createQuery(hql)
	 * .setString("gender_name",obj.getGender_name()) .setString("modified_by",
	 * username).setDate("modified_date", new Date()) .setInteger("id",id); msg =
	 * query.executeUpdate() > 0 ? "Data Updated Successfully."
	 * :"Data Not Updated."; tx.commit();
	 * 
	 * }catch (Exception e) { msg = "Data Not Updated."; tx.rollback(); } finally {
	 * sessionHQL.close(); } return msg; }
	 */

	public ArrayList<ArrayList<String>> search_genderReport() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct \r\n" + "					c.id,\r\n" + "					upper(c.gender_name) as gender_name\r\n"
					+ "					FROM tb_psg_mstr_gender c		\r\n" + "					where c.id !=0 ";
			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				String id = String.valueOf(i++);
				list.add(id);
				list.add(rs.getString("gender_name"));

				alist.add(list);
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
		return alist;
	}
}
