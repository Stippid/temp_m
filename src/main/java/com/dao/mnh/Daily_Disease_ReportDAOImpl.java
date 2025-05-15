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

public class Daily_Disease_ReportDAOImpl implements Daily_Disease_ReportDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> getsearch_Daily_desease_Report(String sus1, String unit1, String cmd1,
			String diag1, String frm_dt1, String to_dt1, String serv1, String cat1) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!sus1.equals("")) {
				qry += " and r.sus_no=?";
			} else {
				if (!cmd1.equals("ALL")) {
					qry += " and substr(a.form_code_control,?,?)=?";
				}
			}

			if (!diag1.equals("")) {
				qry += " and r.diagnosis=?";
			}

			if (!frm_dt1.equals("") && !to_dt1.equals("")) {
				String col = null;

				col = "dt_report";

				qry += " and to_char(" + col + ", 'YYYY-MM-DD') >= ? and to_char(" + col + ", 'YYYY-MM-DD') <= ?";
			}

			if (!serv1.equals("ALL")) {
				qry += " and r.service=?";
			}

			if (!cat1.equals("ALL")) {
				qry += " and r.category=?";
			}

			/*
			 * q = "select a.unit_name,d.disease_name,count(r.diagnosis) as count_diag\r\n"
			 * + "from tb_med_daily_disease_surv_details  r\r\n" +
			 * "inner join tb_med_daily_surv_disease_mstr d on  r.diagnosis=d.id\r\n" +
			 * "left join tb_miso_orbat_unt_dtl a on a.sus_no = r.sus_no \r\n" +
			 * "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control \r\n"
			 * + "where a.status_sus_no='Active' \r\n" +
			 * "and substring(r.sus_no,?,?) in(?,?,?,?,?,?,?,?,?) " +qry+ "\r\n" +
			 * "group by d.disease_name,a.unit_name";
			 */

			// Added view orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
			q = "select a.unit_name,d.disease_name,count(r.diagnosis) as count_diag\r\n"
					+ "from tb_med_daily_disease_surv_details  r\r\n"
					+ "inner join tb_med_daily_surv_disease_mstr d on  r.diagnosis=d.id\r\n"
					+ "left join orbat_all_details_view_mnh a on a.sus_no = r.sus_no \r\n"
					+ "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control \r\n"
					+ "where a.status_sus_no='Active'  " + qry + "\r\n" + "group by d.disease_name,a.unit_name";

			stmt = conn.prepareStatement(q);

			stmt.setInt(1, 1);
			stmt.setInt(2, 1);
			stmt.setString(3, "000000000");
			/*
			 * stmt.setInt(4, 1); stmt.setInt(5, 4); stmt.setString(6, "9609");
			 * stmt.setString(7, "9709"); stmt.setString(8, "3742"); stmt.setString(9,
			 * "6323"); stmt.setString(10, "3755"); stmt.setString(11, "3738");
			 * stmt.setString(12, "3735"); stmt.setString(13, "3747"); stmt.setString(14,
			 * "1234");
			 */

			int j = 4;
			if (!sus1.equals("")) {
				stmt.setString(j, sus1);
				j++;
			} else {
				if (!cmd1.equals("ALL")) {
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 1);
					j++;
					stmt.setString(j, cmd1.substring(0, 1));
					j++;
				}
			}
			if (!diag1.equals("")) {
				stmt.setInt(j, Integer.parseInt(diag1));
				j++;
			}
			if (!frm_dt1.equals("") && !to_dt1.equals("")) {
				stmt.setString(j, frm_dt1);
				j++;
				stmt.setString(j, to_dt1);
				j++;
			}
			if (!serv1.equals("ALL")) {
				stmt.setString(j, serv1);
				j++;
			}
			if (!cat1.equals("ALL")) {
				stmt.setString(j, cat1);
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