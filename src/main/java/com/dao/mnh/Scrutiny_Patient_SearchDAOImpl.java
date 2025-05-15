package com.dao.mnh;

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

public class Scrutiny_Patient_SearchDAOImpl implements Scrutiny_Patient_SearchDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> search_patient_details_datascrutiny(String sus1, String name1, String rk1,
			String andno1, String pno1, String pname1, String frm_dt1, String to_dt1) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!sus1.equals("")) {
				qry += " and a.medical_unit = ?";
			}

			if (!name1.equals("")) {
				qry += " and (lower(name) like ? or Upper(name) like ?)";
			}

			if (!rk1.equals("")) {
				qry += " and rank=?";
			}

			if (!andno1.equals("")) {
				qry += " and and_no=?";
			}

			if (!pno1.equals("")) {
				qry += " and (lower(persnl_no) like ? or Upper(persnl_no) like ?)";
			}

			if (!pname1.equals("")) {
				qry += " and (lower(persnl_name) like ? or Upper(persnl_name) like ?)";
			}

			if (!frm_dt1.equals("") && !frm_dt1.equals(null)) {
				qry += " and a.admsn_date BETWEEN DATE(?)";

				if (!to_dt1.equals("") && !to_dt1.equals(null)) {
					qry += " and DATE(?) + 1";
				}
				qry += " order by a.id desc";
			}

			/*
			 * q =
			 * "select a.id,b.unit_name,a.persnl_unit,a.name,a.rank,a.and_no,a.persnl_name,a.persnl_no"
			 * + " from tb_med_patient_details a " +
			 * "left join tb_miso_orbat_unt_dtl b on b.sus_no = a.medical_unit " +
			 * "where b.status_sus_no='Active' and substring(b.sus_no,?,?) in(?,?,?,?,?,?,?,?,?)"
			 * +qry;
			 */
			// Added view orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
			q = "select a.id,b.unit_name,a.persnl_unit,a.name,a.rank,a.and_no,a.persnl_name,a.persnl_no"
					+ " from tb_med_patient_details a "
					+ "left join orbat_all_details_view_mnh b on b.sus_no = a.medical_unit "
					+ "where b.status_sus_no='Active' " + qry;

			stmt = conn.prepareStatement(q);
			/*
			 * stmt.setInt(1, 1); stmt.setInt(2, 4); stmt.setString(3, "9609");
			 * stmt.setString(4, "9709"); stmt.setString(5, "3742"); stmt.setString(6,
			 * "6323"); stmt.setString(7, "3755"); stmt.setString(8, "3738");
			 * stmt.setString(9, "3735"); stmt.setString(10, "3747"); stmt.setString(11,
			 * "1234");
			 */

			int j = 1;
			if (!sus1.equals("")) {
				stmt.setString(j, sus1);
				j++;
			}

			if (!name1.equals("")) {
				stmt.setString(j, '%' + name1.toLowerCase() + '%');
				j++;
				stmt.setString(j, '%' + name1.toUpperCase() + '%');
				j++;
			}

			if (!rk1.equals("")) {
				stmt.setString(j, rk1);
				j++;
			}

			if (!andno1.equals("")) {
				stmt.setString(j, andno1);
				j++;
			}

			if (!pno1.equals("")) {
				stmt.setString(j, '%' + pno1.toLowerCase() + '%');
				j++;
				stmt.setString(j, '%' + pno1.toUpperCase() + '%');
				j++;
			}

			if (!pname1.equals("")) {
				stmt.setString(j, '%' + pname1.toLowerCase() + '%');
				j++;
				stmt.setString(j, '%' + pname1.toUpperCase() + '%');
				j++;
			}

			if (!frm_dt1.equals("") && !frm_dt1.equals(null)) {
				stmt.setString(j, frm_dt1);
				j++;
			}
			if (!to_dt1.equals("") && !to_dt1.equals(null)) {
				stmt.setString(j, to_dt1);
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
				String updateButton = "<i class='action_icons action_update' " + editData
						+ " title='Edit Scrutiny Data'></i>";
				String f = "";
				f += updateButton;

				columns.put("edit_l1", f);
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

}
