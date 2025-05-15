package com.dao.mnh;

import java.math.BigDecimal;
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

import com.models.mnh.Tb_Med_Opd_Cases;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class OPDCasesDaoImpl implements OPDCasesDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> CaptureOPDCase(String sus1, String qtr1, String yr1,String ward1) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String flag = "Y";

			if (!sus1.equals("")) {
				qry += " Where r.sus_no = ?";
				flag = "N";
			}
			if (!qtr1.equals("-1")) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " Where r.qtr = ?";
					flag = "N";
				} else {
					qry += " and r.qtr = ?";
				}
			}
			if (!yr1.equals("")) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " Where r.year = ?";
					flag = "N";
				} else {
					qry += " and r.year = ?";
				}
			}
			if (!ward1.equals("-1")) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " Where r.ward = ?";
					flag = "N";
				} else {
					qry += " and r.ward = ?";
				}
			}
			q = "select distinct r.id,(select unit_name from orbat_all_details_view_mnh where sus_no = r.sus_no and status_sus_no ='Active'),"
					+ "(select sys_code_desc from tb_med_system_code where sys_code_value = r.qtr) as qtr,r.year,r.ward from tb_med_opdcases r "
					+ qry;
			stmt = conn.prepareStatement(q);
			int j = 1;
			if (!sus1.equals("")) {
				stmt.setString(j, sus1);
				j++;
			}
			if (!qtr1.equals("-1")) {
				stmt.setString(j, qtr1);
				j++;
			}
			if (!yr1.equals("")) {
				stmt.setInt(j, Integer.parseInt(yr1));
				j++;
			}
			if (!ward1.equals("-1")) {
				stmt.setString(j, ward1);
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
				String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + editData + " title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
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

	public Tb_Med_Opd_Cases getOPDCasesByid(int id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Tb_Med_Opd_Cases updateid = (Tb_Med_Opd_Cases) sessionHQL.get(Tb_Med_Opd_Cases.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}

	public String Update_OPDCases(Tb_Med_Opd_Cases obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {
			String hql = "update Tb_Med_Opd_Cases set ward=:ward,qtr=:qtr,year=:year,officer_self=:a1,officer_family=:a2,jco_ors_self=:a3,jco_ors_family=:a4,"
					+ "ex_serv_self=:a5,ex_serv_family=:a6,para_mil_pers_self=:a7,para_mil_pers_family=:a8,civilian_self=:a9,civilian_family=:a10,"
					+ "total_during_month=:t,remarks=:r,modified_by=:m1,modified_on=:m2 where id=:id";

			Query query = sessionHQL.createQuery(hql).setString("ward", obj.getWard()).setString("qtr", obj.getQtr())
					.setInteger("year", obj.getYear()).setInteger("a1", obj.getOfficer_self())
					.setInteger("a2", obj.getOfficer_family()).setInteger("a3", obj.getJco_ors_self())
					.setInteger("a4", obj.getJco_ors_family()).setInteger("a5", obj.getEx_serv_self())
					.setInteger("a6", obj.getEx_serv_family()).setInteger("a7", obj.getPara_mil_pers_self())
					.setInteger("a8", obj.getPara_mil_pers_family()).setInteger("a9", obj.getCivilian_self())
					.setInteger("a10", obj.getCivilian_family()).setInteger("t", obj.getTotal_during_month())
					.setString("r", obj.getRemarks()).setString("m1", username).setTimestamp("m2", new Date())
					.setInteger("id", obj.getId());

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

	/**/
	public String delete_input_opd_cases(int deleteid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "delete from Tb_Med_Opd_Cases where id=:id";
		int rowCount = session.createQuery(hqlUpdate).setInteger("id", deleteid).executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Deleted Successfully";
		} else {
			return "Deleted not Successfully";
		}
	}

	public Long checkExitstingopdinput(String sus_no1, String quater, int year, int id,String ward) {
		String hql = "select count(id) from Tb_Med_Opd_Cases where sus_no=:d1 and qtr=:d2 and year=:d3 and ward=:d4 and  id!=:id";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q2 = session.createQuery(hql);
		q2.setParameter("d1", sus_no1);
		q2.setParameter("d2", quater);
		q2.setParameter("d3", year);
		q2.setParameter("d4", ward);
		q2.setParameter("id", id);
		Long count_list2 = (Long) q2.uniqueResult();
		tx.commit();
		session.close();
		if (count_list2 != null) {
			return count_list2;
		} else {
			return (long) 0;
		}

	}
}