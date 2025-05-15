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

public class Scrutiny_Level1_4DAOImpl implements Scrutiny_Level1_4DAO {

	private static final List<Map<String, Object>> List = null;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> search_unit_fmn_datascrutiny(String sus1, String cmd1, String frm_dt1,
			String to_dt1, String stat1, String para1, String p_diag1, String icd_remarks_a1, String icd_remarks_d1,String relation1) {

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

			if (para1.equals("L1") || para1.equals("L2")) {
				if (!icd_remarks_a1.equals("")) {
					qry += " and upper(a.icd_remarks_a) like upper(?)";
				}
				if (!icd_remarks_d1.equals("")) {
					qry += " and upper(a.icd_remarks_d) like upper(?)";
				}
			}

			if (!frm_dt1.equals("") && !frm_dt1.equals(null)) {
				qry += " and a.admsn_date BETWEEN DATE(?)";

				if (!to_dt1.equals("") && !to_dt1.equals(null)) {
					qry += " and DATE(?)";
				}
			}
		
			if (para1.equals("L1") || para1.equals("L2")) {
				if (!relation1.equals("-1")){
				
				qry += "and a.relationship = ?";
			}
			}
			if (!stat1.equals("") && !stat1.equals(null)) {
				if (para1.equals("UNIT")) {
					if (stat1.equals("PNDUNIT")) {
						qry += " and (a.apprvr_at_unit_by is null or a.apprvr_at_unit_by='')";
					}
					if (stat1.equals("APPROVE")) {
						qry += " and (a.apprvr_at_unit_by is not null and a.apprvr_at_unit_by!='')";
					}

				}

				if (para1.equals("L1")) {
					qry += " and (a.apprvr_at_unit_by != null or apprvr_at_unit_by != '' )";
					if (stat1.equals("PNDUNIT")) {
						qry += " and (a.apprvr_at_miso_by is null or a.apprvr_at_miso_by='')";
					}
					if (stat1.equals("APPROVE")) {
						qry += " and (a.apprvr_at_miso_by is not null and a.apprvr_at_miso_by !='')";
					}
				}

				if (para1.equals("L2")) {
					qry += " and (a.apprvr_at_miso_by != null or a.apprvr_at_miso_by != '')";
					if (stat1.equals("PNDUNIT")) {
						qry += " and a.apprvr_at_miso_by !='test' and (a.checked_by is null  or a.checked_by='')";
					}
					if (stat1.equals("APPROVE")) {
						qry += " and a.apprvr_at_miso_by !='test' and a.checked_by is not null";
					}
					if (stat1.equals("PASS")) {
						qry += " and a.apprvr_at_miso_by = 'test' and (a.checked_by is null or a.checked_by='')";
					}
				}

			}

			if (para1.equals("L2") && !p_diag1.equals("-1")) {
				qry += " and d.disease_principal = ?";
			}

			String p_d = "";
			if (para1.equals("L2")) {
				p_d = "left join tb_med_d_disease_cause d on a.diagnosis_code1d = d.icd_code ";
			}

			if (para1.equals("UNIT") || para1.equals("L1") || para1.equals("L2")) {
				qry += " order by a.diagnosis_code1d ASC,a.id desc";

			}

			/*
			 * q =
			 * "select distinct a.id,a.and_no,a.name,a.sex,a.relationship,a.persnl_no,a.rank,a.category,a.icd_remarks_a,a.icd_remarks_d,a.diagnosis_code1d,"
			 * + "a.icd_cause_code1d,a.rejection_reason from tb_med_patient_details a " +
			 * "left join tb_miso_orbat_unt_dtl b on b.sus_no = a.medical_unit  "+p_d +
			 * "where b.status_sus_no='Active' and substring(b.sus_no,?,?) in(?,?,?,?,?,?,?,?,?) "
			 * +qry;
			 */

			// Added view orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl

			q = "select distinct a.id,a.and_no,a.name,a.sex,a.relationship,a.persnl_no,a.rank,a.category,a.icd_remarks_a,a.icd_remarks_d,a.diagnosis_code1d,"
					+ "a.icd_cause_code1d,a.rejection_reason from tb_med_patient_details a "
					+ "left join orbat_all_details_view_mnh b on b.sus_no = a.medical_unit  " + p_d
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

			if (para1.equals("L1") || para1.equals("L2")) {
				if (!icd_remarks_a1.equals("")) {
					stmt.setString(j, "%" + icd_remarks_a1 + "%");
					j++;
				}

				if (!icd_remarks_d1.equals("")) {
					stmt.setString(j, "%" + icd_remarks_d1 + "%");
					j++;
				}
			}

			if (!frm_dt1.equals("") && !frm_dt1.equals(null)) {
				stmt.setString(j, frm_dt1);
				j++;
			}
			
			if (!to_dt1.equals("") && !to_dt1.equals(null)) {
				stmt.setString(j, to_dt1);
				j++;
			}
			if (para1.equals("L1") || para1.equals("L2")) {
				
			if (!relation1.equals("-1")){		
				stmt.setString(j, relation1);
				j++;
			}
			
			}
			if (para1.equals("L2") && !p_diag1.equals("-1")) {
				stmt.setString(j, p_diag1);
				j++;
			}

			ResultSet rs = stmt.executeQuery();
			System.err.println("mnhstaff--"+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				if ((stat1.equals("PNDUNIT") || stat1.equals("PASS")) && (para1.equals("UNIT") || para1.equals("L1") || para1.equals("L2"))) {
					columns.put("checkbox", "<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)
							+ "' name='cbox' onchange='checkbox_count(this," + rs.getObject(1) + ");' />");
					columns.put("hid", "<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)
							+ "' value='" + rs.getObject(1) + "'   />");

				}

				String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + editData
						+ " title='Edit Scrutiny Data'></i>";

				String f = "";
				f += updateButton;

				String editData2 = "onclick=\"  if (confirm('Are you sure you want to update?') ){editDischarge("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String updateButton2 = "<i class='action_icons action_update' " + editData2
						+ " title='Edit Discharge Data'></i>";
				String f2 = "";
				f2 += updateButton2;

				if (para1.equals("UNIT") || para1.equals("L1") || para1.equals("L2")) {
					columns.put("edit_l1", f);
				}

				if (para1.equals("L2") ||  para1.equals("L1")) {
					columns.put("edit_l2", f2);
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

	public String approve_unit_fmn_datascrutiny(String a, String para, String user) {
		String[] id_list = a.split(":");

		Connection conn = null;
		Integer out = 0;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (para.equalsIgnoreCase("UNIT")) {
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
					stmt = conn.prepareStatement(
							"update tb_med_patient_details set apprvr_at_unit_by=?,apprvd_at_unit_on = localtimestamp where id=?");
					stmt.setString(1, user);
					stmt.setInt(2, id);
					out = stmt.executeUpdate();

				}
			}
			if (para.equalsIgnoreCase("L1")) {
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
					stmt = conn.prepareStatement(
							"update tb_med_patient_details set apprvr_at_miso_by=?,apprvd_at_miso_on = localtimestamp where id=?");
					stmt.setString(1, user);
					stmt.setInt(2, id);
					out = stmt.executeUpdate();

				}
			}
			
			if (para.equalsIgnoreCase("L2")) {
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
					stmt = conn.prepareStatement(
							"update tb_med_patient_details set checked_by=?,updated_on=localtimestamp where id=?");
					stmt.setString(1, user);
					stmt.setInt(2, id);
					out = stmt.executeUpdate();

				}
			}

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

		if (out > 0) {
			return "Approved Successfully";
		} else {
			return "Approved not Successfully";
		}
	}

	public String reject_unit_fmn_datascrutiny(String a, String r, String user) {
		Connection conn = null;
		String q = "";
		String qry = "";
		Integer out = 0;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "update tb_med_patient_details set rejection_reason=?,apprvr_at_unit_by=?,apprvd_at_unit_on=? where id=?";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, r);
			stmt.setString(2, "");
			stmt.setTimestamp(3, null);
			stmt.setInt(4, Integer.parseInt(a));
			out = stmt.executeUpdate();

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

		if (out > 0) {
			return "Rejected Successfully";
		} else {
			return "Rejected not Successfully";
		}
	}

	public List<Map<String, Object>> get_patient_details_ds(Integer id2) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select and_no,admsn_date,dschrg_date,ward,name,relationship,sex,nbb,nbb_weight,age_year,age_month,age_days,"
					+ "persnl_no,persnl_name,persnl_age_year,persnl_age_month,category,rank,service_years,service_months,persnl_sex,"
					+ "marital_status,persnl_marital_status,diagnosis_code1a,icd_cause_code1a,icd_remarks_a,icd_remarks_d,discharge_remarks,"
					+ "admsn_type,disposal,diagnosis_code1d,icd_cause_code1d,discharge_ward,nok_name,nok_relation from tb_med_patient_details where id=?";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id2);

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

	public String update_patient_details_ds(ArrayList<String> data, String user, String status) throws SQLException {

		Connection conn = null;
		Integer out = 0;
		String q = "";
		String qry = "";

		// try{
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		if (data.get(1).equals("UNIT")) {
			qry += ",apprvr_at_unit_by = ?,apprvd_at_unit_on = localtimestamp";
		}

		if (data.get(1).equals("L1")) { // Level 3
			qry = ",apprvr_at_miso_by=?,apprvd_at_miso_on=localtimestamp";
		}

		if (data.get(1).equals("L2")) {

			if (status.equals("PASS")) {
				qry = ",apprvr_at_miso_by=?,apprvd_at_miso_on=localtimestamp";
				qry += ",checked_by=?,updated_on=localtimestamp";
			} else {
				qry = ",checked_by=?,updated_on=localtimestamp";
			}

		}

		q = "update tb_med_patient_details set admsn_date=?,dschrg_date=?,relationship=?,sex=?,nbb=?,nbb_weight=?,age_year=?,age_month=?,"
				+ "age_days=?,persnl_age_year=?,persnl_age_month=?,category=?,rank=?,persnl_sex=?,marital_status=?,persnl_marital_status=?,"
				+ "admsn_type=?,disposal=?,diagnosis_code1d=?,icd_cause_code1d=?,and_no=?,diagnosis_code1a=?,icd_cause_code1a=?,service_years=?,service_months=?,nok_name=?,nok_relation=?,ward=?"
				+ qry + " where id=?";

		stmt = conn.prepareStatement(q);
		stmt.setDate(1, java.sql.Date.valueOf(data.get(4)));
		stmt.setDate(2, java.sql.Date.valueOf(data.get(5)));
		stmt.setString(3, data.get(7));
		stmt.setString(4, data.get(8));
		stmt.setString(5, data.get(9));
		stmt.setDouble(6, Double.parseDouble(data.get(10)));
		stmt.setInt(7, Integer.parseInt(data.get(11)));
		stmt.setInt(8, Integer.parseInt(data.get(12)));
		stmt.setInt(9, Integer.parseInt(data.get(13)));
		stmt.setInt(10, Integer.parseInt(data.get(16)));
		stmt.setInt(11, Integer.parseInt(data.get(17)));
		stmt.setString(12, data.get(18));
		stmt.setString(13, data.get(19));
		stmt.setString(14, data.get(22));
		stmt.setString(15, data.get(23));
		stmt.setString(16, data.get(24));
		stmt.setString(17, data.get(30));
		stmt.setString(18, data.get(31));
		stmt.setString(19, data.get(32));
		stmt.setString(20, data.get(33));
		stmt.setString(21, data.get(2));
		stmt.setString(22, data.get(25));
		stmt.setString(23, data.get(26));
		stmt.setInt(24, Integer.parseInt(data.get(20)));
		stmt.setInt(25, Integer.parseInt(data.get(21)));
		stmt.setString(26, data.get(34));
		stmt.setString(27, data.get(35));
		stmt.setString(28, data.get(6));
		int j = 29;
		if (data.get(1).equals("UNIT") || data.get(1).equals("L1") || data.get(1).equals("L2")) {

			if (status.equals("PASS")) {
				stmt.setString(j, user);
				j++;
				stmt.setString(j, user);
				j++;
			} else {
				stmt.setString(j, user);
				j++;
			}
			
		}
/*		if (data.get(1).equals("UNIT")) {
			stmt.setString(j, "");
			j++;
		}*/

		stmt.setInt(j, Integer.parseInt(data.get(0)));
		out = stmt.executeUpdate();
		conn.close();
		if (out > 0) {
			return "Data Updated Successfully";
		} else {
			return "Data Not Updated Successfully";
		}
	}

	public String update_patient_discharge_details_ds(ArrayList<String> data) {
		Connection conn = null;
		Integer out = 0;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "update tb_med_patient_details set diagnosis_code1d=?,icd_cause_code1d=?,updated_on=localtimestamp where id=?";
			
			stmt = conn.prepareStatement(q);
			stmt.setString(1, data.get(1));
			stmt.setString(2, data.get(2));
			stmt.setInt(3, Integer.parseInt(data.get(0)));
			out = stmt.executeUpdate();
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

		if (out > 0) {
			return "Data Updated Successfully";
		} else {
			return "Data Not Updated Successfully";
		}
	}
	
	
	

	public String update_unit_fmn_datascrutiny(String a, String icdcode, String icdcause, String para,
			String username) {
		String[] id_list = a.split(":");
		String[] icd_code = icdcode.split(":");
		String[] icd_cause = icdcause.split(":");

//		if (icdcause.endsWith(":"))
//		{
//			 String[] newIcdCause = new String[icd_cause.length + 1];
//			    System.arraycopy(icd_cause, 0, newIcdCause, 0, icd_cause.length);
//			    newIcdCause[icd_cause.length] = null;
//			    icd_cause = newIcdCause;
//		}

		Connection conn = null;
		Integer out = 0;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			for (int i = 0; i < id_list.length; i++) {
				int id = Integer.parseInt(id_list[i]);
				String icdcodev = String.valueOf(icd_code[i]);

				String icdcausev = String.valueOf(icd_cause[i]);
				// String icdcausev = (String.valueOf(icd_cause[i]).isEmpty()) ?
				// "":String.valueOf(icd_cause[i]) ;
				// String icdcausev = String.valueOf(icd_cause[i]);

				stmt = conn.prepareStatement(
						"update tb_med_patient_details set diagnosis_code1d=?,icd_cause_code1d=?,updated_on=localtimestamp where id=?");
				if (icdcodev.equals("$icdcode/#")) {
					stmt.setString(1, "");
				} else {
					stmt.setString(1, icdcodev);
				}
				if (icdcausev.equals("$icdcause/#")) {
					stmt.setString(2, "");
				}

				else {
					stmt.setString(2, icdcausev);
				}
				stmt.setInt(3, id);
				out = stmt.executeUpdate();

			}

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

		if (out > 0) {
			return "Data Updated Successfully";
		} else {
			return "Data Not Updated Successfully";
		}
	}


}
