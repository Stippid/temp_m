package com.dao.psg.Civilian_Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Report.TB_PSG_APPX_A_B_CIV_MAIN;
import com.models.psg.Report.TB_PSG_CIV_MAIN;
import com.persistance.util.HibernateUtil;

public class held_str_civ_report_nomi_DaoImpl implements held_str_civ_report_nomi_Dao {
	private DataSource dataSource;



	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String GenerateQueryWhereClause_SQL(String Search) {
		String SearchValue = "";
		if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "") { // for Input Filter
			SearchValue += "  AND (  upper(a.employee_no) like ? or upper(a.full_name) like ? or  upper(b.description) like ?  or upper(ltrim(TO_CHAR(a.dob ,'DD-MON-YYYY'),'0')) like ? or upper(t.label) like ?"
					+ " or upper(a.civ_group) like ? or upper(t1.label) like ?  or upper(ltrim(TO_CHAR(a.date_of_tos,'DD-MON-YYYY'),'0')) like ? or upper(ltrim(TO_CHAR(a.designation_date ,'DD-MON-YYYY'),'0')) like ?  or upper(ltrim(TO_CHAR(a.joining_date_gov_service ,'DD-MON-YYYY'),'0')) like ? "
					+ " or upper(ltrim(TO_CHAR(a.designation_date,'DD-MON-YYYY'),'0')) like ? or upper(g.gender_name) like ?  )";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String roleAccess,
			String roleSusNo, String sus_no, String civilian_status) {

		int flag = 0;

		try {

			if (roleAccess.equals("Unit")) {
				flag += 1;
				stmt.setString(flag, roleSusNo);
				flag += 1;
				stmt.setString(flag, civilian_status);
			}
			if (roleAccess.equals("MISO")) {
				flag += 1;
				stmt.setString(flag, sus_no);
				flag += 1;
				stmt.setString(flag, civilian_status);

			}


			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}


	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt, String Search, String sus_no, String comd,String corps,
			String div,String bde) {

		int flag = 1;
		try {

			if (!sus_no.equals("0") && !sus_no.equals("")) {
				stmt.setString(flag, sus_no);
				flag += 1;
			}

			if (!comd.equals("0") && !comd.equals("")) {
				stmt.setString(flag, comd);
				flag += 1;
			}
			if (!corps.equals("0") && !corps.equals("")) {
				stmt.setString(flag, corps);
				flag += 1;
			}
			if (!div.equals("0") && !div.equals("")) {
				stmt.setString(flag, div);
				flag += 1;
			}
			if (!bde.equals("0") && !bde.equals("")) {
				stmt.setString(flag, bde);
				flag += 1;
			}

			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "") {
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}		return stmt;
	}



	public PreparedStatement setQueryWhereClause_SQLauth(PreparedStatement stmt, String Search, String roleAccess,
			String roleSusNo, String sus_no, String civilian_status) {

		int flag = 0;

		try {

			if (roleAccess.equals("Unit")) {
				flag += 1;
				stmt.setString(flag, roleSusNo);

			}
			if (roleAccess.equals("MISO")) {
				flag += 1;
				stmt.setString(flag, sus_no);


			}


			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	public PreparedStatement setQueryWhereClause_SQLciv(PreparedStatement stmt, String Search, String roleAccess,
			String roleSusNo, String sus_no, String FDate, String LDate, String civilian_status) {
		int flag = 0;
		try {
			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
				stmt.setString(2, civilian_status);
				if(civilian_status.equals("R")) {
					stmt.setString(3, FDate);
				}
			}
			if (roleAccess.equals("MISO")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, civilian_status);
				if(civilian_status.equals("R")) {
					stmt.setString(3, FDate);
				}

			}
			flag = 1;
			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	@Override
	public long Getsearch_nominrollcount_civ(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String sus_no, String civilian_status) throws SQLException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(select distinct a.employee_no,a.full_name as emp_name,b.description,ltrim(TO_CHAR(a.dob ,'DD-Mon-YYYY'),'0') as dob"
					+ ",t.label as classification_services,\r\n"
					+ "a.civ_group,t1.label as civ_type, ltrim(TO_CHAR(a.joining_date_gov_service ,'DD-Mon-YYYY'),'0') as joining_date_gov_service, "
					+ " ltrim(TO_CHAR(a.date_of_tos ,'DD-Mon-YYYY'),'0') as date_of_tos,ltrim(TO_CHAR(a.designation_date ,'DD-Mon-YYYY'),'0') as designation_date,g.gender_name from tb_psg_civilian_dtl_main a \r\n"
					+ "inner join t_domain_value t1 on t1.codevalue = a.civ_type and t1.domainid='CIVILIAN_TYPE'\r\n"
					+ "inner join tb_psg_mstr_gender g on g.id = a.gender\r\n"
					+ "inner join t_domain_value t on t.codevalue = a.classification_services and t.domainid='CLASSIFICATION_OF_SERVICES' \r\n"
					+ "inner join cue_tb_psg_rank_app_master b on a.designation =b.id\r\n"
					+ " and  upper(b.level_in_hierarchy) = upper('Appointment') \r\n"
					+ " and b.parent_code in ('4','5','6','7','8','9','10','11')    \r\n"
					+ " and b.status_active = 'Active' and a.status=1  where   a.sus_No = ?  and a.civilian_status =?"
					+ SearchValue + ") app ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, roleAccess, roleSusNo, sus_no, civilian_status);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}

	@Override
	public List<Map<String, Object>> Getsearch_search_nominrollciv(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String sus_no, String civilian_status)
					throws SQLException {

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			/*if(civilian_status.equals("R")) {*/

			q = "select distinct a.employee_no,a.full_name as emp_name,b.description,ltrim(TO_CHAR(a.dob ,'DD-Mon-YYYY'),'0') as dob,t.label as classification_services,\r\n"
					+ "a.civ_group,t1.label as civ_type, ltrim(TO_CHAR(a.joining_date_gov_service ,'DD-Mon-YYYY'),'0') as joining_date_gov_service, ltrim(TO_CHAR(a.date_of_tos ,'DD-Mon-YYYY'),'0') as date_of_tos,ltrim(TO_CHAR(a.designation_date ,'DD-Mon-YYYY'),'0') as designation_date"
					+ ",g.gender_name from tb_psg_civilian_dtl_main a \r\n"
					+ "inner join t_domain_value t1 on t1.codevalue = a.civ_type and t1.domainid='CIVILIAN_TYPE'\r\n"
					+ "inner join tb_psg_mstr_gender g on g.id = a.gender\r\n"
					+ "inner join t_domain_value t on t.codevalue = a.classification_services and t.domainid='CLASSIFICATION_OF_SERVICES' \r\n"
					+ "inner join cue_tb_psg_rank_app_master b on a.designation =b.id\r\n"
					+ " and  upper(b.level_in_hierarchy) = upper('Appointment') \r\n"
					+ " and b.parent_code in ('4','5','6','7','8','9','10','11')    \r\n"
					+ " and b.status_active = 'Active' and a.status=1 where   a.sus_No = ?  and a.civilian_status =?  "
					+ SearchValue + " limit " + pageL + " OFFSET " + startPage + "";

			//}
			//			else {
			//
			//				q = "select distinct a.employee_no,a.full_name as emp_name,b.description,ltrim(TO_CHAR(a.dob ,'DD-Mon-YYYY'),'0') as dob,\r\n"
			//						+ "t1.label as civ_type, ltrim(TO_CHAR(a.joining_date_gov_service ,'DD-Mon-YYYY'),'0') as joining_date_gov_service, "
			//						+ "g.gender_name from tb_psg_civilian_dtl_main a \r\n"
			//						+ "inner join t_domain_value t1 on t1.codevalue = a.civ_type and t1.domainid='CIVILIAN_TYPE'\r\n"
			//						+ "inner join tb_psg_mstr_gender g on g.id = a.gender\r\n"
			//						+ "left join t_domain_value t on t.codevalue = a.classification_services and t.domainid='CLASSIFICATION_OF_SERVICES' \r\n"
			//						+ "left join cue_tb_psg_rank_app_master b on a.designation =b.id\r\n"
			//						+ " and  upper(b.level_in_hierarchy) = upper('Appointment') \r\n"
			//						+ " and b.parent_code in ('4','5','6','7','8','9','10','11')    \r\n"
			//						+ " and b.status_active = 'Active' and a.status=1 where   a.sus_No = ?  and a.civilian_status =?  "
			//						+ SearchValue + " limit " + pageL + " OFFSET " + startPage + "";
			//			}

			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, roleAccess, roleSusNo, sus_no, civilian_status);

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

	@Override
	public long Getsearch_authcountciv(String Search, String orderColunm, String orderType, HttpSession sessionUserId,
			String sus_no, String civilian_status) throws SQLException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(select distinct\r\n" + "COALESCE (auth.group_a_gaz,'0') as group_a_gaz,\r\n"
					+ "COALESCE (auth.group_b_gaz,'0') as group_b_gaz,\r\n"
					+ "COALESCE (auth.group_b_non_gaz_Non_ind,'0') as group_b_non_gaz_Non_ind,\r\n"
					+ "COALESCE (auth.group_c_non_gaz_Non_ind,'0') as group_c_non_gaz_Non_ind,\r\n"
					+ "COALESCE (auth.group_b_non_gaz_ind,'0') as group_b_non_gaz_ind,\r\n"
					+ "COALESCE (auth.group_c_non_gaz_ind,'0') as group_c_non_gaz_ind ,"
					+ "COALESCE (auth.total,'0') as total from (select \r\n" + "\r\n"
					+ "sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '4') as group_a_gaz,\r\n"
					+ "sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '5') as group_b_gaz,\r\n"
					+ "sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '6') as group_b_non_gaz_Non_ind,\r\n"
					+ "sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '7') as group_c_non_gaz_Non_ind,\r\n"
					+ "sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '9') as group_b_non_gaz_ind,\r\n"
					+ "sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '10') as group_c_non_gaz_ind , "
					+ "sum(a1.base_auth +a1.mod_auth+a1.foot_auth)  filter (where a1.rank_cat IN ('4','5','6','7','9','10')) as total	 \r\n"
					+ "from sus_pers_stdauth a1 \r\n"
					+ " inner join   tb_psg_civilian_dtl_main a on a.sus_no = a1.sus_no left join t_domain_value c on c.codevalue = a1.cat_id and c.domainid = 'PERSON_CAT'\r\n"
					+ " where a1.sus_no = ?  and a.civilian_status =? and a1.cat_id in ('1','2') ) auth ) app ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, roleAccess, roleSusNo, sus_no, civilian_status);
			//
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}

	@Override
	public List<Map<String, Object>> Getsearch_search_authciv(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String sus_no, String civilian_status)
					throws SQLException {

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct\r\n" +
					"COALESCE (auth.group_a_gaz,'0') as group_a_gaz,\r\n" +
					"COALESCE (auth.group_b_gaz,'0') as group_b_gaz,\r\n" +
					"COALESCE (auth.group_b_non_gaz_Non_ind,'0') as group_b_non_gaz_Non_ind,\r\n" +
					"COALESCE (auth.group_c_non_gaz_Non_ind,'0') as group_c_non_gaz_Non_ind,\r\n" +
					"COALESCE (auth.group_b_non_gaz_ind,'0') as group_b_non_gaz_ind,\r\n" +
					"COALESCE (auth.group_c_non_gaz_ind,'0') as group_c_non_gaz_ind,COALESCE (auth.total,'0') as total from (select \r\n" +
					"\r\n" +
					"sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '4') as group_a_gaz,\r\n" +
					"sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '5') as group_b_gaz,\r\n" +
					"sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '6') as group_b_non_gaz_Non_ind,\r\n" +
					"sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '7') as group_c_non_gaz_Non_ind,\r\n" +
					"sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '9') as group_b_non_gaz_ind,\r\n" +
					"sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat in( '8','10','12','11')) as group_c_non_gaz_ind,sum(a1.base_auth +a1.mod_auth+a1.foot_auth) \r\n" +
					"filter (where a1.rank_cat IN ('4','5','6','7','9','8','10','12','11')) as total	  \r\n" +
					"from sus_pers_stdauth a1 \r\n" +
					" --inner join   tb_psg_civilian_dtl_main a on a.sus_no = a1.sus_no \r\n" +
					"left join t_domain_value c on c.codevalue = a1.cat_id and c.domainid = 'PERSON_CAT'"
					+ " where a1.sus_no = ?   AND a1.cat_id in ('1','2') " + SearchValue
					+ " ) auth" + " limit " + pageL + " OFFSET " + startPage + "";

			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQLauth(stmt, Search, roleAccess, roleSusNo, sus_no, civilian_status);
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

	@Override
	public long Getsearch_heldstrcivcountciv(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String sus_no, String FDate, String LDate, String civilian_status)
					throws SQLException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app1.*) from(select distinct app.sus_no,\r\n"
					+ "       app.groupa_gaz,app.groupb_gaz,app.groupb_nongaz_nonin,app.groupc_nongaz_nonin,app.groupb_nongaz_in,app.groupc_nongaz_in"
					+ ",\r\n"
					+ "	   sum(app.groupa_gaz + app.groupb_gaz + app.groupb_nongaz_nonin + app.groupc_nongaz_nonin + app.groupb_nongaz_in + app.groupc_nongaz_in) as total\r\n"
					+ "from (\r\n" + "	select distinct \r\n" + "   a.sus_no,\r\n"
					+ "   count(*) filter (where a.civ_group = 'A' and a.classification_services = '1') as groupa_gaz,\r\n"
					+ "   count(*) filter (where a.civ_group = 'B' and a.classification_services = '1') as groupb_gaz,\r\n"
					+ "   count(*) filter (where a.civ_group = 'B' and a.classification_services = '2' and a.civ_type = '2') as groupb_nongaz_nonin,\r\n"
					+ "   count(*) filter (where a.civ_group = 'C' and a.classification_services = '2' and a.civ_type = '2') as groupc_nongaz_nonin,\r\n"
					+ "   count(*) filter (where a.civ_group = 'B' and a.classification_services = '2' and a.civ_type = '1') as groupb_nongaz_in,\r\n"
					+ "   count(*) filter (where a.civ_group = 'C' and a.classification_services = '2' and a.civ_type = '1') as groupc_nongaz_in\r\n"
					+ "from tb_psg_civilian_dtl_main a \r\n"
					+ "inner join tb_miso_orbat_unt_dtl ah on a.sus_no = ah.sus_no  and ah.status_sus_no = 'Active' where a.status = '1' and a.sus_no = ?  and a.civilian_status = ? and to_date(to_char(a.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')"
					+ SearchValue + " group by a.sus_no) app group by 1,2,3,4,5,6,7  ) app1 ";
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQLciv(stmt, Search, roleAccess, roleSusNo, sus_no, FDate, LDate,
					civilian_status);

			ResultSet rs = stmt.executeQuery();
			//
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}

	@Override
	public List<Map<String, Object>> Getsearch_heldstrcivciv(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String sus_no, String FDate, String LDate,
			String civilian_status) throws SQLException {

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = " select distinct app.sus_no,\r\n"
					+ "       app.groupa_gaz,app.groupb_gaz,app.groupb_nongaz_nonin,app.groupc_nongaz_nonin,app.groupb_nongaz_in,app.groupc_nongaz_in"
					+ ",\r\n"
					+ "	   sum(app.groupa_gaz + app.groupb_gaz + app.groupb_nongaz_nonin + app.groupc_nongaz_nonin + app.groupb_nongaz_in + app.groupc_nongaz_in) as total\r\n"
					+ "from (\r\n" + "	select distinct \r\n" + "   a.sus_no,\r\n"
					+ "   count(*) filter (where a.civ_group = 'A' and a.classification_services = '1') as groupa_gaz,\r\n"
					+ "   count(*) filter (where a.civ_group = 'B' and a.classification_services = '1') as groupb_gaz,\r\n"
					+ "   count(*) filter (where a.civ_group = 'B' and a.classification_services = '2' and a.civ_type = '2') as groupb_nongaz_nonin,\r\n"
					+ "   count(*) filter (where a.civ_group = 'C' and a.classification_services = '2' and a.civ_type = '2') as groupc_nongaz_nonin,\r\n"
					+ "   count(*) filter (where a.civ_group = 'B' and a.classification_services = '2' and a.civ_type = '1') as groupb_nongaz_in,\r\n"
					+ "   count(*) filter (where a.civ_group = 'C' and a.classification_services = '2' and a.civ_type = '1') as groupc_nongaz_in\r\n"
					+ "from tb_psg_civilian_dtl_main a \r\n"
					+ "inner join tb_miso_orbat_unt_dtl ah on a.sus_no = ah.sus_no  and ah.status_sus_no = 'Active' where a.status = '1' and a.sus_no = ?  and a.civilian_status = ?  and to_date(to_char(a.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') "
					+ SearchValue + " group by a.sus_no) app group by 1,2,3,4,5,6,7  " + " limit " + pageL + " OFFSET "
					+ startPage + "";
			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQLciv(stmt, Search, roleAccess, roleSusNo, sus_no, FDate, LDate,
					civilian_status);

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

	@Override
	public ArrayList<ArrayList<String>> getldate(String LDate)

	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "SELECT (date_trunc('MONTH', (?||'01')::date) + INTERVAL '1 MONTH - 1 day')::DATE as lastdate  ";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, LDate);

			int i = 0;
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("lastdate"));
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

	@Override
	public List<TB_PSG_CIV_MAIN> Get_civ_VersionData(String month, String year, String roleSusNo,String civilian_status) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_CIV_MAIN where month=:month and year=:year and sus_no=:roleSusNo and civilian_status=:civilian_status";
		Query query = sessionHQL.createQuery(hqlUpdate).setString("month", month).setString("year", year)
				.setString("roleSusNo", roleSusNo).setString("civilian_status", civilian_status);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CIV_MAIN> list = query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}




	public String Insert_Report_Civ_Version() {
		return "INSERT INTO tb_psg_held_str_civ_main \r\n"
				+ "(sus_no,month,year,version,status,created_by,created_date,civilian_status) VALUES (?,?,?,?,?,?,now()::timestamp,?) ";

	}

	public String Insert_Report_Civ_Main_Details() {

		return "INSERT INTO tb_psg_held_str_civ_main_details \r\n"
				+ "(sus_no,month,year,version,cmd_sus,corp_sus,div_sus,bde_sus,present_return_no,present_return_dt,"
				+ "cmd_unit,corp_unit,div_unit,bde_unit,created_by,created_date,observation,civilian_status,last_return_no,last_return_dt) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,CAST(? as Date),?,?,?,?,?,now()::timestamp,?,?,?,CAST(? as Date) ) ";

	}

	public String Update_Report_Civ_Version() {
		return "update tb_psg_held_str_civ_main set month= ?, year= ?, version= ?,status='0',civilian_status=?,\r\n"
				+ "modified_by= ?, modified_date= now()::timestamp where id=?";
	}

	public String Insert_Report_nominalroll() {

		return "insert into tb_psg_nominalroll (employee_no,full_name ,designation,dob ,classification_services,\r\n"
				+ "    civ_group ,civ_type,joining_date_gov_service ,date_of_tos ,designation_date ,gender_name,month,year,version,sus_no,civilian_status)\r\n"
				+ "select distinct a.employee_no,a.full_name as emp_name,b.description,ltrim(TO_CHAR(a.dob ,'DD-Mon-YYYY'),'0') as dob,t.label as classification_services,\r\n"
				+ "a.civ_group,t1.label as civ_type, ltrim(TO_CHAR(a.joining_date_gov_service ,'DD-Mon-YYYY'),'0') as joining_date_gov_service, ltrim(TO_CHAR(a.date_of_tos ,'DD-Mon-YYYY'),'0') as date_of_tos,ltrim(TO_CHAR(a.designation_date ,'DD-Mon-YYYY'),'0') as designation_date,\r\n"
				+ "g.gender_name ,?,?,?,?,?\r\n" + "from tb_psg_civilian_dtl_main a \r\n"
				+ "inner join t_domain_value t1 on t1.codevalue = a.civ_type and t1.domainid='CIVILIAN_TYPE'\r\n"
				+ "inner join tb_psg_mstr_gender g on g.id = a.gender\r\n"
				+ "inner join cue_tb_psg_rank_app_master b on a.designation =b.id\r\n"
				+ "left join t_domain_value t on t.codevalue = a.classification_services and t.domainid='CLASSIFICATION_OF_SERVICES' \r\n"
				+ " and  upper(b.level_in_hierarchy) = upper('Appointment') \r\n"
				+ " and b.parent_code in ('4','5','6','7','8','9','10','11')    \r\n"
				+ " and b.status_active = 'Active' and a.status=1 \r\n" + " where a.sus_no =? and a.civilian_status =?";

	}

	public String Insert_Report_auth_civ() {

		return "insert into tb_psg_auth_civ(sus_no,month,year,version,group_a_gaz,group_b_gaz,group_b_non_gaz_Non_ind,group_c_non_gaz_Non_ind,\r\n"
				+ "										group_b_non_gaz_ind,group_c_non_gaz_ind,total)\r\n"
				+ "\r\n"
				+ "select               ? as sus_no,? as month,? as year ,? as version, \r\n"
				+ "                     COALESCE( sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '4'),0) as group_a_gaz,\r\n"
				+ "					COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '5'),0) as group_b_gaz,\r\n"
				+ "					COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '6'),0) as group_b_non_gaz_Non_ind,\r\n"
				+ "					COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '7'),0) as group_c_non_gaz_Non_ind,\r\n"
				+ "					COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '9'),0) as group_b_non_gaz_ind,\r\n"
				+ "					COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '10'),0) as group_c_non_gaz_ind,\r\n"
				+ "					COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth)  filter (where a.rank_cat IN ('4','5','6','7','9','10')),0) as total	\r\n"
				+ "					 from sus_pers_stdauth a\r\n"
				+ "					--- inner join  tb_psg_civilian_dtl_main c on c.sus_no = a.sus_no \r\n"
				+ "					 left join   t_domain_value d on d.codevalue = a.cat_id and d.domainid = 'PERSON_CAT'					\r\n"
				+ "					 where a.sus_No =?\r\n"
				+ "					 and a.cat_id in ('1','2') ";

	}

	public String Insert_Report_posted_str_civ() {

		return "insert into tb_psg_posted_civ(sus_no,group_a_gaz,group_b_gaz,group_b_non_gaz_Non_ind,group_c_non_gaz_Non_ind,\r\n"
				+ "							group_b_non_gaz_ind,group_c_non_gaz_ind,total,month,year,version)\r\n"
				+ "select distinct app.sus_no,\r\n"
				+ "       app.groupa_gaz,app.groupb_gaz,app.groupb_nongaz_nonin,app.groupc_nongaz_nonin,app.groupb_nongaz_in,app.groupc_nongaz_in,\r\n"
				+ "	   sum(app.groupa_gaz + app.groupb_gaz + app.groupb_nongaz_nonin + app.groupc_nongaz_nonin + app.groupb_nongaz_in + app.groupc_nongaz_in) as total,\r\n"
				+ "	   ? as month, ? as year,? as version\r\n" + "from (\r\n" + "	select distinct \r\n"
				+ "   a.sus_no,\r\n"
				+ "   count(*) filter (where a.civ_group = 'A' and a.classification_services = '1') as groupa_gaz,\r\n"
				+ "   count(*) filter (where a.civ_group = 'B' and a.classification_services = '1') as groupb_gaz,\r\n"
				+ "   count(*) filter (where a.civ_group = 'B' and a.classification_services = '2' and a.civ_type = '2') as groupb_nongaz_nonin,\r\n"
				+ "   count(*) filter (where a.civ_group = 'C' and a.classification_services = '2' and a.civ_type = '2') as groupc_nongaz_nonin,\r\n"
				+ "   count(*) filter (where a.civ_group = 'B' and a.classification_services = '2' and a.civ_type = '1') as groupb_nongaz_in,\r\n"
				+ "   count(*) filter (where a.civ_group = 'C' and a.classification_services = '2' and a.civ_type = '1') as groupc_nongaz_in,\r\n"
				+ "	'0' as month,'0' as year ,'0' as version \r\n" + "from tb_psg_civilian_dtl_main a \r\n"
				+ "inner join tb_miso_orbat_unt_dtl ah on \r\n" + "	a.sus_no = ah.sus_no and ah.status_sus_no = 'Active'\r\n"
				+ "	where  a.status = '1'  and a.sus_no =?  and a.civilian_status=? group by a.sus_no) app group by 1,2,3,4,5,6,7";

	}

	public String Insert_Report_Civ_Summary() {

		return "INSERT INTO TB_PSG_SUMMARY_CIV (SUS_NO,MONTH,YEAR,VERSION,CIV_AUTH,CIV_POSTED,CIV_SUR,CIV_DEFI)\r\n"
				+ "SELECT ? AS SUS_NO,? AS MONTH,? AS YEAR,? AS VERSION, SUM(AUTHCIV) AS CIV_AUTH,SUM(POSTCIV) AS CIV_POSTED,\r\n"
				+ "	CASE\r\n"
				+ "					WHEN (SUM(POSTCIV) - SUM(AUTHCIV)) >= 0 THEN (SUM(POSTCIV) - SUM(AUTHCIV))\r\n"
				+ "					ELSE 0\r\n"
				+ "	END AS CIV_SUR,\r\n"
				+ "	CASE\r\n"
				+ "					WHEN (SUM(POSTCIV) - SUM(AUTHCIV)) < 0 THEN (SUM(POSTCIV) - SUM(AUTHCIV))\r\n"
				+ "					ELSE 0\r\n"
				+ "	END AS CIV_DEFI\r\n"
				+ "FROM\r\n"
				+ "	(SELECT COALESCE(SUM(A.BASE_AUTH + A.MOD_AUTH + A.FOOT_AUTH) FILTER (WHERE A.RANK_CAT IN ('4','5','6','7','9','10')),0) AS AUTHCIV,\r\n"
				+ "			0 AS POSTCIV\r\n"
				+ "		FROM SUS_PERS_STDAUTH A\r\n"
				+ "		---INNER JOIN TB_PSG_CIVILIAN_DTL_MAIN C ON C.SUS_NO = A.SUS_NO\r\n"
				+ "		LEFT JOIN T_DOMAIN_VALUE D ON D.CODEVALUE = A.CAT_ID\r\n"
				+ "		AND D.DOMAINID = 'PERSON_CAT'\r\n"
				+ "		WHERE A.CAT_ID in ('1','2')\r\n"
				+ "			AND A.SUS_NO = ?\r\n"
				+ "			--AND C.CIVILIAN_STATUS = ?\r\n"
				+ "		UNION SELECT 0 AS AUTHCIV,\r\n"
				+ "			COALESCE(SUM(P.GROUPA_GAZ + P.GROUPB_GAZ + P.GROUPB_NONGAZ_NONIN + P.GROUPC_NONGAZ_NONIN + P.GROUPB_NONGAZ_IN + P.GROUPC_NONGAZ_IN),\r\n"
				+ "				0) AS POSTCIV\r\n"
				+ "		FROM\r\n"
				+ "			(SELECT COUNT(*) FILTER (WHERE A.CIV_GROUP = 'A'AND A.CLASSIFICATION_SERVICES = '1') AS GROUPA_GAZ,\r\n"
				+ "					COUNT(*) FILTER (WHERE A.CIV_GROUP = 'B'AND A.CLASSIFICATION_SERVICES = '1') AS GROUPB_GAZ,\r\n"
				+ "					COUNT(*) FILTER (WHERE A.CIV_GROUP = 'B'AND A.CLASSIFICATION_SERVICES = '2'AND A.CIV_TYPE = '2') AS GROUPB_NONGAZ_NONIN,\r\n"
				+ "					COUNT(*) FILTER (WHERE A.CIV_GROUP = 'C'AND A.CLASSIFICATION_SERVICES = '2'AND A.CIV_TYPE = '2') AS GROUPC_NONGAZ_NONIN,\r\n"
				+ "					COUNT(*) FILTER (WHERE A.CIV_GROUP = 'B'AND A.CLASSIFICATION_SERVICES = '2'AND A.CIV_TYPE = '1') AS GROUPB_NONGAZ_IN,\r\n"
				+ "					COUNT(*) FILTER (WHERE A.CIV_GROUP = 'C'AND A.CLASSIFICATION_SERVICES = '2'AND A.CIV_TYPE = '1') AS GROUPC_NONGAZ_IN\r\n"
				+ "				FROM TB_PSG_CIVILIAN_DTL_MAIN A\r\n"
				+ "				INNER JOIN TB_MISO_ORBAT_UNT_DTL SUB ON A.SUS_NO = SUB.SUS_NO\r\n"
				+ "				AND SUB.STATUS_SUS_NO = 'Active'\r\n"
				+ "				WHERE A.STATUS = '1'\r\n"
				+ "					AND A.SUS_NO = ?\r\n"
				+ "					AND TO_DATE(TO_CHAR(A.DATE_OF_TOS,'Mon YYYY'),'Mon YYYY') <= TO_DATE(?,'YYYY/MM/DD')\r\n"
				+ "					AND A.CIVILIAN_STATUS = ? ) P) SUMMARY";

	}

	public String Insert_Report_Non_regular_civ() {

		return "insert into tb_psg_non_regular_civ(sus_no,industrial,non_industrial,total,month,year,version)\r\n"
				+ "select distinct app.sus_no,\r\n"
				+ "       app.industrial,app.non_industrial,sum(app.industrial + app.non_industrial) as total,\r\n"
				+ "	   ? as month, ? as year,? as version\r\n" + "from (\r\n" + "	select distinct \r\n"
				+ "   a.sus_no,\r\n"
				+ "   count(*) filter (where a.civ_type = '1') as industrial,\r\n"
				+ "   count(*) filter (where a.civ_type = '2') as non_industrial,\r\n"
				+ "	'0' as month,'0' as year ,'0' as version \r\n" + "from tb_psg_civilian_dtl_main a \r\n"
				+ "inner join tb_miso_orbat_unt_dtl ah on \r\n" + "	a.sus_no = ah.sus_no and ah.status_sus_no = 'Active'\r\n"
				+ "	where  a.status = '1'  and a.sus_no =?  and a.civilian_status=? group by a.sus_no) app group by 1,2,3";


	}

	@Override
	public Boolean Insert_Report_civ(String username, String roleSusNo, String FDate, String month, String year,
			String userId, String present_return_no,String last_return_no, String present_return_dt,String last_return_dt, String observation, String LDate,String civilian_status)
					throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = dataSource.getConnection();
		//				  try {
		conn.setAutoCommit(false);
		int val = 0;
		int count = 0;

		ArrayList<ArrayList<String>> orbatlist = getcommand(roleSusNo);

		List<TB_PSG_CIV_MAIN> version = Get_civ_VersionData(month, year, roleSusNo,civilian_status);
		if (version.size() == 0) {

			val = 1;

			pstmt = conn.prepareStatement(Insert_Report_Civ_Version());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, String.valueOf(val));
			pstmt.setInt(5, 0);
			pstmt.setString(6, username);
			pstmt.setString(7, civilian_status);

			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Insert_Report_Civ_Main_Details());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, String.valueOf(val));

			pstmt.setString(5, orbatlist.get(0).get(1));// cmd sus
			pstmt.setString(6, orbatlist.get(0).get(2));// corp sus
			pstmt.setString(7, orbatlist.get(0).get(3));// div sus
			pstmt.setString(8, orbatlist.get(0).get(4));// bde sus

			// pstmt.setString(9, String.valueOf(sum_auth));// auth
			// pstmt.setString(10, String.valueOf(sum_held));// held
			pstmt.setString(9, present_return_no);
			pstmt.setString(10, present_return_dt);

			pstmt.setString(11, orbatlist.get(0).get(5));// cmd unit
			pstmt.setString(12, orbatlist.get(0).get(6));// corp unit
			pstmt.setString(13, orbatlist.get(0).get(7));// div unit
			pstmt.setString(14, orbatlist.get(0).get(8));// bde unit
			pstmt.setString(15, username);

			pstmt.setString(16, observation);
			pstmt.setString(17, civilian_status);
			pstmt.setString(18, last_return_no);
			pstmt.setString(19, last_return_dt);

			pstmt.executeUpdate();

		} else {
			count = Integer.parseInt(version.get(0).getVersion());
			val = count + 1;

			pstmt = conn.prepareStatement(Update_Report_Civ_Version());
			pstmt.setString(1, month);
			pstmt.setString(2, year);
			pstmt.setString(3, String.valueOf(val));
			pstmt.setString(4, civilian_status);
			pstmt.setString(5, username);
			pstmt.setInt(6, version.get(0).getId());


			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Insert_Report_Civ_Main_Details());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, String.valueOf(val));

			pstmt.setString(5, orbatlist.get(0).get(1));// cmd sus
			pstmt.setString(6, orbatlist.get(0).get(2));// corp sus
			pstmt.setString(7, orbatlist.get(0).get(3));// div sus
			pstmt.setString(8, orbatlist.get(0).get(4));// bde sus

			// pstmt.setString(9, String.valueOf(sum_auth));// auth
			// pstmt.setString(10, String.valueOf(sum_held));// held
			pstmt.setString(9, present_return_no);
			pstmt.setString(10, present_return_dt);
			// pstmt.setString(13, we_pe_no);// we_pe_no

			pstmt.setString(11, orbatlist.get(0).get(5));// cmd unit
			pstmt.setString(12, orbatlist.get(0).get(6));// corp unit
			pstmt.setString(13, orbatlist.get(0).get(7));// div unit
			pstmt.setString(14, orbatlist.get(0).get(8));// bde unit
			pstmt.setString(15, username);
			pstmt.setString(16, observation);
			pstmt.setString(17, civilian_status);
			pstmt.setString(18, last_return_no);
			pstmt.setString(19, last_return_dt);

			pstmt.executeUpdate();

		}

		pstmt = conn.prepareStatement(Insert_Report_nominalroll());
		pstmt.setString(1, month);
		pstmt.setString(2, year);
		pstmt.setString(3, String.valueOf(val));
		pstmt.setString(4, roleSusNo);
		pstmt.setString(5, civilian_status);
		pstmt.setString(6, roleSusNo);
		pstmt.setString(7, civilian_status);
		pstmt.executeUpdate();

		if(civilian_status.equals("R")) {

			pstmt = conn.prepareStatement(Insert_Report_auth_civ());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, String.valueOf(val));
			pstmt.setString(5, roleSusNo);
			//	pstmt.setString(6, civilian_status);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Insert_Report_posted_str_civ());
			// pstmt.setString(1, roleSusNo);
			pstmt.setString(1, month);
			pstmt.setString(2, year);
			pstmt.setString(3, String.valueOf(val));
			pstmt.setString(4, roleSusNo);
			pstmt.setString(5, civilian_status);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Insert_Report_Civ_Summary());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, String.valueOf(val));

			pstmt.setString(5, roleSusNo);
			//pstmt.setString(6, civilian_status);
			pstmt.setString(6, roleSusNo);
			pstmt.setString(7, FDate);
			pstmt.setString(8, civilian_status);

			pstmt.executeUpdate();

		}

		else {
			pstmt = conn.prepareStatement(Insert_Report_Non_regular_civ());
			// pstmt.setString(1, roleSusNo);
			pstmt.setString(1, month);
			pstmt.setString(2, year);
			pstmt.setString(3, String.valueOf(val));
			pstmt.setString(4, roleSusNo);
			pstmt.setString(5, civilian_status);
			pstmt.executeUpdate();

		}

		pstmt.close();
		conn.commit();
		conn.close();
		//					} catch (Exception e1)
		//					{
		//
		//						conn.rollback();
		//						return false;
		//					}
		return true;
	}

	// ---------------- search civ submitted from------------//
	@Override
	public ArrayList<ArrayList<String>> Search_civ_Report_Version(String month, String year, HttpSession session,
			String status, String unit_sus_no) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (roleAccess.equals("Unit")) {
				if (!roleSusNo.equals("")) {
					qry += " and ms.sus_no = ? ";
				}
			}
			if (roleAccess.equals("MISO")) {
				if(!unit_sus_no.equals("")) {
					qry += " and ms.sus_no = ? ";
				}
			}
			if (!month.equals("0")) {
				qry += " and m.month = ? ";
			}
			if (!year.equals("")) {
				qry += " and m.year = ? ";
			}
			if (!status.equals("")) {
				qry += "and m.status= cast(? as integer)";
			}
			q = "select distinct m.sus_no,ms.unit_name,TO_CHAR( TO_DATE (m.month::text, 'MM'), 'Month') AS month_name,"
					+ "m.month,m.year,m.version,m.civilian_status from tb_psg_held_str_civ_main m \r\n"
					+ " inner join tb_miso_orbat_unt_dtl ms on ms.sus_no = m.sus_no and ms.status_sus_no='Active' "
					+ " where m.id!= 0  " + qry;

			stmt = conn.prepareStatement(q);

			if (!qry.equals("")) {

				int j = 1;

				if (roleAccess.equals("Unit")) {
					if (roleSusNo != "") {
						stmt.setString(j, roleSusNo);
						j += 1;
					}
				}
				if (roleAccess.equals("MISO")) {
					if (unit_sus_no != "") {
						stmt.setString(j, unit_sus_no);
						j += 1;
					}
				}
				if (!month.equals("0")) {
					stmt.setString(j, month);
					j += 1;
				}
				if (year != "") {
					stmt.setString(j, year);
					j += 1;
				}
				if (status != "") {
					stmt.setString(j, status);
					// j += 1;
				}
			}

			int i = 0;

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("unit_name"));// 0
				list.add(rs.getString("month_name"));// 1
				list.add(rs.getString("year"));// 2
				list.add(rs.getString("version"));// 3
				list.add(rs.getString("civilian_status"));// 4

				String f1 = "";
				String f4 = "";
				if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("0")){

					String View = "onclick=\"if (confirm('Are You Sure You Want to Approve This ?')) { editData('"
							+ rs.getString("month") + "','" + rs.getString("year") + "','" + rs.getString("version")
							+ "','" + rs.getString("sus_no") + "','" + rs.getString("civilian_status") + "'); } else { return false; }\"";
					f4 = "<i class='fa fa-eye' " + View + " title='Approve/View Data'></i>";

					String Delete1 = "onclick=\"if (confirm('Are You Sure You Want to Delete This ?')) { deleteData('"
							+ rs.getString("month") + "','" + rs.getString("year") + "','" + rs.getString("version")
							+ "','" + rs.getString("sus_no") + "','" + rs.getString("civilian_status") + "'); } else { return false; }\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Dustbin'></i>";


				}
				list.add(f4);
				list.add(f1);
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



	@Override
	public ArrayList<ArrayList<String>> Search_Report_civnominalroll(String month, String year,
			HttpSession session, String version, String unit_sus_no,String civilian_status) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct pn.employee_no,pn.full_name,pn.designation,pn.dob,pn.classification_services,pn.civ_group,pn.civ_type,\r\n"
					+ "                pn.joining_date_gov_service,pn.date_of_tos,pn.designation_date,pn.gender_name  from tb_psg_held_str_civ_main as p \r\n"
					+ "						 inner join tb_psg_nominalroll as pn \r\n"
					+ "						 on p.sus_no = pn.sus_no and p.year = pn.year and p.version=pn.version \r\n"
					+ "						 where pn.sus_no = ? and pn.status='0' and pn.year = ? and pn.month = ? and pn.version = ? and pn.civilian_status=?";

			stmt = conn.prepareStatement(q);

			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO")) {
				stmt.setString(1, unit_sus_no);
			}

			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, version);
			stmt.setString(5, civilian_status);

			int i = 0;

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i));// 0
				list.add(rs.getString("employee_no"));// 1
				list.add(rs.getString("full_name"));// 2
				list.add(rs.getString("designation"));// 3
				list.add(rs.getString("dob"));// 4
				list.add(rs.getString("classification_services"));// 5
				list.add(rs.getString("civ_group"));// 6
				list.add(rs.getString("civ_type"));// 7
				list.add(rs.getString("joining_date_gov_service"));// 8
				list.add(rs.getString("date_of_tos"));// 9
				list.add(rs.getString("designation_date"));// 10
				list.add(rs.getString("gender_name"));// 11

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

	@Override
	public ArrayList<ArrayList<String>> Search_Report_auth_civ(String month, String year, HttpSession session,
			String version, String unit_sus_no) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct  COALESCE (auth.group_a_gaz,'0') as gp_a_gazetted,\r\n"
					+ "  COALESCE (auth.group_b_gaz,'0') as gp_b_gazetted,\r\n"
					+ "COALESCE (auth.group_b_non_gaz_non_ind,'0') as gp_b_non_gazetted_non_ind,\r\n"
					+ "  COALESCE (auth.group_c_non_gaz_non_ind,'0') as gp_c_non_gazetted_non_ind,\r\n"
					+ "  COALESCE (auth.group_b_non_gaz_ind,'0') as gp_b_non_gazetted_ind,\r\n"
					+ "  COALESCE (auth.group_c_non_gaz_ind,'0') as gp_c_non_gazetted_ind,\r\n"
					+ " COALESCE(SUM(auth.group_a_gaz +auth.group_b_gaz + auth.group_b_non_gaz_non_ind + auth.group_c_non_gaz_non_ind + auth.group_b_non_gaz_ind\r\n"
					+ "	 + auth.group_c_non_gaz_ind),0) AS total \r\n"
					+ "from tb_psg_held_str_civ_main as p \r\n"
					+ "inner join tb_psg_auth_civ as auth \r\n"
					+ "on p.sus_no = auth.sus_no and p.year = auth.year and p.version=auth.version \r\n"
					+ "where auth.sus_no = ? and auth.status='0' and auth.year = ? and auth.month = ? and auth.version = ?\r\n"
					+ "group by gp_a_gazetted,gp_b_gazetted,gp_b_non_gazetted_non_ind,gp_c_non_gazetted_non_ind,gp_b_non_gazetted_ind,gp_c_non_gazetted_ind";

			stmt = conn.prepareStatement(q);

			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO")) {
				stmt.setString(1, unit_sus_no);
			}

			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, version);

			int i = 0;

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("gp_a_gazetted"));// 0
				list.add(rs.getString("gp_b_gazetted"));// 1
				list.add(rs.getString("gp_b_non_gazetted_non_ind"));// 2
				list.add(rs.getString("gp_c_non_gazetted_non_ind"));// 3
				list.add(rs.getString("gp_b_non_gazetted_ind"));// 4
				list.add(rs.getString("gp_c_non_gazetted_ind"));// 5
				list.add(rs.getString("total"));// 5

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



	@Override
	public ArrayList<ArrayList<String>> Search_Report_posted_civ(String month, String year, HttpSession session,
			String version, String unit_sus_no) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct  COALESCE (auth.group_a_gaz,'0') as gp_a_gazetted,\r\n"
					+ "  COALESCE (auth.group_b_gaz,'0') as gp_b_gazetted,\r\n"
					+ "COALESCE (auth.group_b_non_gaz_non_ind,'0') as gp_b_non_gazetted_non_ind,\r\n"
					+ "  COALESCE (auth.group_c_non_gaz_non_ind,'0') as gp_c_non_gazetted_non_ind,\r\n"
					+ "  COALESCE (auth.group_b_non_gaz_ind,'0') as gp_b_non_gazetted_ind,\r\n"
					+ "  COALESCE (auth.group_c_non_gaz_ind,'0') as gp_c_non_gazetted_ind,\r\n"
					+ " COALESCE(SUM(auth.group_a_gaz +auth.group_b_gaz + auth.group_b_non_gaz_non_ind + auth.group_c_non_gaz_non_ind + auth.group_b_non_gaz_ind\r\n"
					+ "	 + auth.group_c_non_gaz_ind),0) AS total \r\n"
					+ "from tb_psg_held_str_civ_main as p \r\n"
					+ "inner join tb_psg_posted_civ as auth \r\n"
					+ "on p.sus_no = auth.sus_no and p.year = auth.year and p.version=auth.version \r\n"
					+ "where auth.sus_no = ? and auth.status='0' and auth.year = ? and auth.month = ? and auth.version = ?\r\n"
					+ "group by gp_a_gazetted,gp_b_gazetted,gp_b_non_gazetted_non_ind,gp_c_non_gazetted_non_ind,gp_b_non_gazetted_ind,gp_c_non_gazetted_ind";

			stmt = conn.prepareStatement(q);

			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO")) {
				stmt.setString(1, unit_sus_no);
			}

			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, version);

			int i = 0;

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("gp_a_gazetted"));// 0
				list.add(rs.getString("gp_b_gazetted"));// 1
				list.add(rs.getString("gp_b_non_gazetted_non_ind"));// 2
				list.add(rs.getString("gp_c_non_gazetted_non_ind"));// 3
				list.add(rs.getString("gp_b_non_gazetted_ind"));// 4
				list.add(rs.getString("gp_c_non_gazetted_ind"));// 5
				list.add(rs.getString("total"));//6

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


	@Override
	public ArrayList<ArrayList<String>> Search_Report_summary_civ(String month, String year,
			HttpSession session, String version, String unit_sus_no) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct civ_auth,civ_posted,civ_sur,civ_defi\r\n"
					+ "					from  tb_psg_held_str_civ_main as p \r\n"
					+ "					inner join tb_psg_summary_civ as sm\r\n"
					+ "					on p.sus_no = sm.sus_no and p.year = sm.year and p.version=sm.version \r\n"
					+ "					where sm.sus_no = ? and sm.status='0' and sm.year = ? and sm.month = ? and sm.version = ?";

			stmt = conn.prepareStatement(q);

			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO")) {
				stmt.setString(1, unit_sus_no);
			}

			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, version);

			int i = 0;

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("civ_auth"));// 0
				list.add(rs.getString("civ_posted"));// 1
				list.add(rs.getString("civ_sur"));// 2
				list.add(rs.getString("civ_defi"));// 3


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

	@Override
	public ArrayList<ArrayList<String>> non_regular_civlist(String month, String year,
			HttpSession session, String version, String unit_sus_no) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;



			q = "		select distinct industrial,non_industrial,total\r\n"
					+ "				from  tb_psg_held_str_civ_main as p \r\n"
					+ "				inner join tb_psg_non_regular_civ as sm\r\n"
					+ "				on p.sus_no = sm.sus_no and p.year = sm.year and p.version=sm.version \r\n"
					+ "					where sm.sus_no = ? and sm.status='0' and sm.year = ? and sm.month = ? and sm.version = ?";

			stmt = conn.prepareStatement(q);

			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO")) {
				stmt.setString(1, unit_sus_no);
			}

			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, version);

			int i = 0;

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("industrial"));// 0
				list.add(rs.getString("non_industrial"));// 1
				list.add(rs.getString("total"));// 2


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

	// ----------------APPROVE civ---------------------------------------------//
	@Override
	public Boolean Approve_Report_civ(String username, String roleSusNo, String month, String year, String version,String civilian_status)
			throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = dataSource.getConnection();
		try {
			conn.setAutoCommit(false);

			List<TB_PSG_CIV_MAIN> version0 = Get_civ_VersionData(month, year, roleSusNo,civilian_status);

			pstmt = conn.prepareStatement(Approve_Report_civ_Version());
			pstmt.setString(1, username);
			pstmt.setInt(2, version0.get(0).getId());
			pstmt.setString(3, roleSusNo);
			pstmt.setString(4, month);
			pstmt.setString(5, year);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Approve_Report_civ_Main());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();


			pstmt = conn.prepareStatement(Approve_Report_nominalroll_civ());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();

			if(civilian_status.equals("R")) {
				pstmt = conn.prepareStatement(Approve_Report_auth_civ());
				pstmt.setString(1, username);
				pstmt.setString(2, roleSusNo);
				pstmt.setString(3, month);
				pstmt.setString(4, year);
				pstmt.setString(5, version);
				pstmt.executeUpdate();


				pstmt = conn.prepareStatement(Approve_Report_posted_civ());
				pstmt.setString(1, username);
				pstmt.setString(2, roleSusNo);
				pstmt.setString(3, month);
				pstmt.setString(4, year);
				pstmt.setString(5, version);
				pstmt.executeUpdate();


				pstmt = conn.prepareStatement(Approve_Report_summary_civ());
				pstmt.setString(1, username);
				pstmt.setString(2, roleSusNo);
				pstmt.setString(3, month);
				pstmt.setString(4, year);
				pstmt.setString(5, version);
				pstmt.executeUpdate();
			}
			else {
				pstmt = conn.prepareStatement(Approve_Report_non_regular_civ());
				pstmt.setString(1, username);
				pstmt.setString(2, roleSusNo);
				pstmt.setString(3, month);
				pstmt.setString(4, year);
				pstmt.setString(5, version);
				pstmt.executeUpdate();
			}

			pstmt.close();
			conn.commit();
			conn.close();
		} catch (Exception e1) {
			conn.rollback();
			return false;
		}
		return true;
	}

	public String Approve_Report_civ_Version() {
		return "update tb_psg_held_str_civ_main set status='1', modified_by= ?, modified_date= now()::timestamp \r\n"
				+ " where id=? and sus_no=? and month=? and year=? ";
	}

	public String Approve_Report_civ_Main() {
		return "update tb_psg_held_str_civ_main_details set status='1', modified_by= ?, modified_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Approve_Report_nominalroll_civ() {
		return "update tb_psg_nominalroll set status='1', approved_by = ? , approved_date = now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}
	public String Approve_Report_auth_civ() {
		return "update tb_psg_auth_civ set status='1', approved_by = ? , approved_date = now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Approve_Report_posted_civ() {
		return "update tb_psg_posted_civ set status='1', approved_by = ? , approved_date = now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Approve_Report_summary_civ() {
		return "update tb_psg_summary_civ set status='1', approved_by = ? , approved_date = now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}
	public String Approve_Report_non_regular_civ() {
		return "update tb_psg_non_regular_civ set status='1', approved_by = ? , approved_date = now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	// ---------------END APPROVE 3009 -----------------------------------------//

	// ----------------delete submitted 3009-------------------------------------//
	@Override
	public Boolean Delete_Report_civ(String username, String roleSusNo, String month, String year, String version,String civilian_status)
			throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = dataSource.getConnection();
		try {
			conn.setAutoCommit(false);

			List<TB_PSG_CIV_MAIN> version0 = Get_civ_VersionData(month, year, roleSusNo,civilian_status);

			pstmt = conn.prepareStatement(Delete_Report_civ_Version());
			pstmt.setString(1, username);
			pstmt.setInt(2, version0.get(0).getId());
			pstmt.setString(3, roleSusNo);
			pstmt.setString(4, month);
			pstmt.setString(5, year);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Delete_Report_civ_Main_Details());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();


			pstmt = conn.prepareStatement(Delete_Report_nominalroll_civ());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, version);
			pstmt.executeUpdate();

			if(civilian_status.equals("R")) {
				pstmt = conn.prepareStatement(Delete_Report_civ_auth_civ());
				pstmt.setString(1, roleSusNo);
				pstmt.setString(2, month);
				pstmt.setString(3, year);
				pstmt.setString(4, version);
				pstmt.executeUpdate();


				pstmt = conn.prepareStatement(Delete_Report_civ_posted_str_civ());
				pstmt.setString(1, roleSusNo);
				pstmt.setString(2, month);
				pstmt.setString(3, year);
				pstmt.setString(4, version);
				pstmt.executeUpdate();



				pstmt = conn.prepareStatement(Delete_Report_civ_summary());
				pstmt.setString(1, roleSusNo);
				pstmt.setString(2, month);
				pstmt.setString(3, year);
				pstmt.setString(4, version);
				pstmt.executeUpdate();
			}

			else {
				pstmt = conn.prepareStatement(Delete_Report_non_regular_civ());
				pstmt.setString(1, roleSusNo);
				pstmt.setString(2, month);
				pstmt.setString(3, year);
				pstmt.setString(4, version);
				pstmt.executeUpdate();

			}
			pstmt.close();
			conn.commit();
			conn.close();
		} catch (Exception e1) {
			conn.rollback();
			return false;
		}
		return true;
	}

	public String Delete_Report_civ_Version() {
		return "update tb_psg_held_str_civ_main set status='3', modified_by= ?, modified_date= now()::timestamp \r\n"
				+ " where id=? and sus_no=? and month=? and year=? ";
	}

	public String Delete_Report_civ_Main_Details() {
		return "update tb_psg_held_str_civ_main_details set status='3', modified_by= ?, modified_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}
	public String Delete_Report_nominalroll_civ() {
		return "update tb_psg_nominalroll set status='3' \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_Report_civ_auth_civ() {
		return "update tb_psg_auth_civ set status='3' \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_Report_civ_posted_str_civ() {
		return "update tb_psg_posted_civ set status='3' \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_Report_civ_summary() {
		return "update tb_psg_summary_civ set status='3' \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}
	public String Delete_Report_non_regular_civ() {
		return "update tb_psg_non_regular_civ set status='3' \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	// -----------------end delete submitted 3009-------------------------------//

	@Override
	public ArrayList<String> getSusNoListForciv( )
	{
		ArrayList<String> alist = new ArrayList<String>();
		Connection conn = null;
		String q="";
		try{
			conn = dataSource.getConnection();
			PreparedStatement stmt=null;

			q="select distinct sus_no from tb_miso_orbat_unt_dtl ms \r\n"
					+ "							 inner join tb_psg_trans_proposed_comm_letter com on ms.sus_no = com.unit_sus_no \r\n"
					+ "							 where ms.status_sus_no='Active'  and com.status not in ('0','3') and sus_no not in (\r\n"
					+ "							\r\n"
					+ "							select distinct sus_no from tb_psg_civ_main where month=to_char( date_trunc('month'::text, CURRENT_DATE::timestamp with time zone) ,'MM') and\r\n"
					+ "							 year=to_char( date_trunc('year'::text, CURRENT_DATE::timestamp with time zone) ,'YYYY'))" ;

			stmt=conn.prepareStatement(q);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				alist.add(rs.getString("sus_no"));//0
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

	@Override
	public String getCivilianStatus(String sus_no) {
		String civilianStatus = "";
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "SELECT DISTINCT civilian_status FROM tb_psg_civilian_dtl_main WHERE sus_no = ?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, sus_no);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				civilianStatus = rs.getString("civilian_status");
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
					e.printStackTrace();
				}
			}
		}

		return civilianStatus;
	}


	@Override
	public long Get_total_nonregular_civCount(int startPage, int pageLength, String Search, String orderColunm,
			String orderType, HttpSession sessionUserId, String sus_no, String FDate, String LDate,
			String civilian_status)
					throws SQLException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = " select count(app1.*) from (select distinct app.sus_no,\r\n"
					+ "       app.industrial,app.non_industrial,\r\n"
					+ "	   sum(app.industrial + app.non_industrial) as total\r\n"
					+ "   from (\r\n"
					+ "	select distinct \r\n"
					+ "   a.sus_no,\r\n"
					+ "  count(*) filter (where a.civ_type = '2') as industrial,\r\n"
					+ "   count(*) filter (where  a.civ_type = '2') as non_industrial\r\n"
					+ "from tb_psg_civilian_dtl_main a \r\n"
					+ "inner join tb_miso_orbat_unt_dtl ah on a.sus_no = ah.sus_no  and ah.status_sus_no = 'Active' where a.status = '1' and a.sus_no = ? and a.civilian_status = ?   "
					+ SearchValue + " group by a.sus_no) app group by 1,2,3)app1";
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQLciv(stmt, Search, roleAccess, roleSusNo, sus_no, FDate, LDate,
					civilian_status);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}

	@Override
	public List<Map<String, Object>> Get_total_nonregular_civ(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String sus_no, String FDate, String LDate,
			String civilian_status) throws SQLException {

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = " select distinct app.sus_no,\r\n"
					+ "       app.industrial,app.non_industrial,\r\n"
					+ "	   sum(app.industrial + app.non_industrial) as total\r\n"
					+ "   from (\r\n"
					+ "	select distinct \r\n"
					+ "   a.sus_no,\r\n"
					+ "  count(*) filter (where a.civ_type = '1') as industrial,\r\n"
					+ "   count(*) filter (where  a.civ_type = '2') as non_industrial\r\n"
					+ "from tb_psg_civilian_dtl_main a \r\n"
					+ "inner join tb_miso_orbat_unt_dtl ah on a.sus_no = ah.sus_no  and ah.status_sus_no = 'Active' where a.status = '1' and a.sus_no = ? and a.civilian_status = ?   "
					+ SearchValue + " group by a.sus_no) app group by 1,2,3  " + " limit " + pageL + " OFFSET "
					+ startPage + "";
			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQLciv(stmt, Search, roleAccess, roleSusNo, sus_no, FDate, LDate,
					civilian_status);
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


	public ArrayList<ArrayList<String>> getcommand(String roleSusNo) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String qry = "";

			qry = "select distinct c.id,\r\n" +
					"fv.sus_no as command,\r\n" +
					"fvm.sus_no as corps,\r\n" +
					"div.sus_no as division,\r\n" +
					"bde.sus_no as brigade, " +
					"fv.unit_name as cmd_unit,\r\n" +
					"fvm.unit_name as corp_unit,\r\n" +
					"div.unit_name as div_unit,\r\n" +
					"bde.unit_name as bde_unit \r\n" +
					"from tb_psg_trans_proposed_comm_letter c \r\n" +
					"left join tb_psg_census_detail_p cd on c.id = cd.comm_id \r\n" +
					"left join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' \r\n" +
					"left join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active'\r\n" +
					"left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" +
					"left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" +
					"left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" +
					"left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" +
					"where c.unit_sus_no = ?";
			stmt = conn.prepareStatement(qry);
			stmt.setString(1,roleSusNo);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));
				list.add(rs.getString("command"));
				list.add(rs.getString("corps"));
				list.add(rs.getString("division"));
				list.add(rs.getString("brigade"));
				list.add(rs.getString("cmd_unit"));
				list.add(rs.getString("corp_unit"));
				list.add(rs.getString("div_unit"));
				list.add(rs.getString("bde_unit"));
				alist.add(list);


			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}



	@Override
	public long GetHeldStrCivNominalrollDataCount(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String sus_no,  String comd,String corps,String div,String bde)
					throws SQLException {

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		String qry="";

		if (!sus_no.equals("0") && !sus_no.equals("")) {
			qry += " and a.sus_no = ?";
		}

		if (!comd.equals("0") && !comd.equals("")) {
			qry += " and substr(unt.form_code_control,1,1) =substr(?,1,1) ";
		}
		if (!corps.equals("0") && !corps.equals("")) {
			qry += " and substr(unt.form_code_control,2,2) = substr(?,2,2) ";
		}
		if (!div.equals("0") && !div.equals("")) {
			qry += " and substr(unt.form_code_control,4,3) =substr(?,4,3) ";
		}
		if (!bde.equals("0") && !bde.equals("")) {
			qry += " and substr(unt.form_code_control,7,4) =substr(?,7,4) ";
		}
		int total = 0;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select count(a.*) from  ( select\r\n"
					+ "	a.employee_no,\r\n"
					+ "	a.full_name as emp_name,\r\n"
					+ "	b.description,\r\n"
					+ "	LTRIM(TO_CHAR(a.dob,'DD-Mon-YYYY'),'0') as dob,\r\n"
					+ "	t.label as classification_services,\r\n"
					+ "	a.civ_group,t1.label as civ_type,\r\n"
					+ "	ltrim(TO_CHAR(a.joining_date_gov_service ,'DD-Mon-YYYY'),'0') as joining_date_gov_service,\r\n"
					+ "	ltrim(TO_CHAR(a.date_of_tos ,'DD-Mon-YYYY'),'0') as date_of_tos,\r\n"
					+ "ltrim(TO_CHAR(a.designation_date ,'DD-Mon-YYYY'),'0') as designation_date\r\n"
					+ ",g.gender_name \r\n"
					+ "from\r\n"
					+ "	tb_psg_civilian_dtl a\r\n"
					+ "		inner join tb_miso_orbat_unt_dtl unt on unt.sus_no=a.sus_no\r\n"
					+ "	and unt.status_sus_no='Active'\r\n"
					+ "	inner join tb_psg_mstr_gender g on g.id = a.gender\r\n"
					+ "	left join cue_tb_psg_rank_app_master b on a.designation=b.id\r\n"
					+ "		and UPPER(	b.level_in_hierarchy	)=UPPER(	'Appointment'\r\n"
					+ "	)\r\n"
					+ "	and b.parent_code in (\r\n"
					+ "		'4',\r\n"
					+ "		'5',\r\n"
					+ "		'6',\r\n"
					+ "		'7',\r\n"
					+ "		'8',\r\n"
					+ "		'9',\r\n"
					+ "		'10',\r\n"
					+ "		'11'\r\n"
					+ "	)\r\n"
					+ "	and b.status_active='Active'\r\n"
					+ "	left join t_domain_value t on t.codevalue = a.classification_services and t.domainid='CLASSIFICATION_OF_SERVICES' \r\n"
					+ "	left join t_domain_value t1 on t1.codevalue = a.civ_type and t1.domainid='CIVILIAN_TYPE'\r\n"
					+ "\r\n"
					+ "where\r\n"
					+ "	a.civilian_status='R'\r\n"
					+ "	and a.status=1 " +qry+ " "
					+ SearchValue + ") a  limit " + pageL + " OFFSET " + startPage + "";




			stmt = conn.prepareStatement(q);
			stmt=setQueryWhereClause_SQL1(stmt,Search,sus_no,comd,corps,div,bde);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}



	@Override
	public List<Map<String, Object>> GetHeldStrCivNominalrollDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String sus_no,  String comd,String corps,String div,String bde)
					throws SQLException {

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		String qry="";

		if (!sus_no.equals("0") && !sus_no.equals("")) {
			qry += " and a.sus_no = ?";
		}

		if (!comd.equals("0") && !comd.equals("")) {
			qry += " and substr(unt.form_code_control,1,1) =substr(?,1,1) ";
		}
		if (!corps.equals("0") && !corps.equals("")) {
			qry += " and substr(unt.form_code_control,2,2) = substr(?,2,2) ";
		}
		if (!div.equals("0") && !div.equals("")) {
			qry += " and substr(unt.form_code_control,4,3) =substr(?,4,3) ";
		}
		if (!bde.equals("0") && !bde.equals("")) {
			qry += " and substr(unt.form_code_control,7,4) =substr(?,7,4) ";
		}

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;


			q = "select\r\n"
					+ "	a.employee_no,\r\n"
					+ "	a.full_name as emp_name,\r\n"
					+ "	b.description,\r\n"
					+ "	LTRIM(TO_CHAR(a.dob,'DD-Mon-YYYY'),'0') as dob,\r\n"
					+ "	t.label as classification_services,\r\n"
					+ "	a.civ_group,t1.label as civ_type,\r\n"
					+ "	ltrim(TO_CHAR(a.joining_date_gov_service ,'DD-Mon-YYYY'),'0') as joining_date_gov_service,\r\n"
					+ "	ltrim(TO_CHAR(a.date_of_tos ,'DD-Mon-YYYY'),'0') as date_of_tos,\r\n"
					+ "ltrim(TO_CHAR(a.designation_date ,'DD-Mon-YYYY'),'0') as designation_date\r\n"
					+ ",g.gender_name \r\n"
					+ "from\r\n"
					+ "	tb_psg_civilian_dtl a\r\n"
					+ "		inner join tb_miso_orbat_unt_dtl unt on unt.sus_no=a.sus_no\r\n"
					+ "	and unt.status_sus_no='Active'\r\n"
					+ "	inner join tb_psg_mstr_gender g on g.id = a.gender\r\n"
					+ "	left join cue_tb_psg_rank_app_master b on a.designation=b.id\r\n"
					+ "		and UPPER(	b.level_in_hierarchy	)=UPPER(	'Appointment'\r\n"
					+ "	)\r\n"
					+ "	and b.parent_code in (\r\n"
					+ "		'4',\r\n"
					+ "		'5',\r\n"
					+ "		'6',\r\n"
					+ "		'7',\r\n"
					+ "		'8',\r\n"
					+ "		'9',\r\n"
					+ "		'10',\r\n"
					+ "		'11'\r\n"
					+ "	)\r\n"
					+ "	and b.status_active='Active'\r\n"
					+ "	left join t_domain_value t on t.codevalue = a.classification_services and t.domainid='CLASSIFICATION_OF_SERVICES' \r\n"
					+ "	left join t_domain_value t1 on t1.codevalue = a.civ_type and t1.domainid='CIVILIAN_TYPE'\r\n"
					+ "\r\n"
					+ "where\r\n"
					+ "	a.civilian_status='R'\r\n"
					+ "	and a.status=1 " +qry+ " "
					+ SearchValue + " limit " + pageL + " OFFSET " + startPage + "";



			stmt = conn.prepareStatement(q);
			stmt=setQueryWhereClause_SQL1(stmt,Search,sus_no,comd,corps,div,bde);



			//System.err.println("QUERY FOR HELD data: \n\n"+stmt);
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


	@Override
	public ArrayList<ArrayList<String>> pdf_Download_held_str_civ(String comd, String corps,
			String div, String bde,String sus_no)

	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		String qry = "";

		if (!sus_no.equals("0") && !sus_no.equals("")) {
			qry += " and a.sus_no = ?";
		}

		if (!comd.equals("0") && !comd.equals("")) {
			qry += " and substr(unt.form_code_control,1,1) =substr(?,1,1) ";
		}
		if (!corps.equals("0") && !corps.equals("")) {
			qry += " and substr(unt.form_code_control,2,2) = substr(?,2,2) ";
		}
		if (!div.equals("0") && !div.equals("")) {
			qry += " and substr(unt.form_code_control,4,3) =substr(?,4,3) ";
		}
		if (!bde.equals("0") && !bde.equals("")) {
			qry += " and substr(unt.form_code_control,7,4) =substr(?,7,4) ";
		}

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;



			q = "select distinct a.employee_no,a.full_name as emp_name,b.description,ltrim(TO_CHAR(a.dob ,'DD-Mon-YYYY'),'0') as dob,"
					+ "t.label as classification_services,\r\n"
					+ "a.civ_group,t1.label as civ_type, ltrim(TO_CHAR(a.joining_date_gov_service ,'DD-Mon-YYYY'),'0') as joining_date_gov_service, ltrim(TO_CHAR(a.date_of_tos ,'DD-Mon-YYYY'),'0') as date_of_tos,ltrim(TO_CHAR(a.designation_date ,'DD-Mon-YYYY'),'0') as designation_date"
					+ ",g.gender_name from tb_psg_civilian_dtl a \r\n"
					+ "left join t_domain_value t1 on t1.codevalue = a.civ_type and t1.domainid='CIVILIAN_TYPE'\r\n"
					+ "inner join tb_psg_mstr_gender g on g.id = a.gender\r\n"
					+ "left join t_domain_value t on t.codevalue = a.classification_services and t.domainid='CLASSIFICATION_OF_SERVICES' \r\n"
					+ "left join cue_tb_psg_rank_app_master b on a.designation =b.id\r\n"
					+ " and  upper(b.level_in_hierarchy) = upper('Appointment') \r\n"
					+ " and b.parent_code in ('4','5','6','7','8','9','10','11')    \r\n"
					+ " and b.status_active = 'Active' and a.status=1 "
					+ "inner join tb_miso_orbat_unt_dtl unt\r\n"
					+ "	on unt.sus_no = a.sus_no\r\n"
					+ "	and unt.status_sus_no='Active'\r\n"
					+ "where\r\n"
					+ "	a.civilian_status='R'\r\n"
					+ "	and a.status=1  "+qry+"";

			stmt = conn.prepareStatement(q);
			stmt=setQueryWhereClause_SQL1(stmt,"",sus_no,comd,corps,div,bde);

			//System.err.println("QUERY FOR HELD PRINT: \n\n"+stmt);
			ResultSet rs = stmt.executeQuery();

			int i = 1;
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("employee_no"));// 1
				list.add(rs.getString("emp_name"));// 2
				list.add(rs.getString("description"));// 3
				list.add(rs.getString("dob"));// 4
				list.add(rs.getString("classification_services"));// 5
				list.add(rs.getString("civ_group"));// 6
				list.add(rs.getString("civ_type"));// 7
				list.add(rs.getString("joining_date_gov_service"));// 8
				list.add(rs.getString("date_of_tos"));// 9
				list.add(rs.getString("designation_date"));// 10
				list.add(rs.getString("gender_name"));//11

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










	//		==================================APPX A AND B  start==========================================

	@Override
	public ArrayList<ArrayList<String>> getCiviliadnAuth(String unit_sus_no) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select\r\n"
					+ "					COALESCE(sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '4'), 0) as group_a_gaz,\r\n"
					+ "					COALESCE(sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '5'), 0) as group_b_gaz,\r\n"
					+ "					COALESCE(sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '6'), 0) as group_b_non_gaz_Non_ind, \r\n"
					+ "					COALESCE(sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '7'), 0) as group_c_non_gaz_Non_ind,\r\n"
					+ "					COALESCE(sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat = '9'), 0) as group_b_non_gaz_ind, \r\n"
					+ "					COALESCE(sum(a1.base_auth +a1.mod_auth+a1.foot_auth) filter (where a1.rank_cat in( '8','10','12','11')), 0) as group_c_non_gaz_ind,\r\n"
					+ "					COALESCE(sum(a1.base_auth +a1.mod_auth+a1.foot_auth)filter (where a1.rank_cat IN ('4','5','6','7','9','8','10','12','11')), 0) as total	   \r\n"
					+ "					from sus_pers_stdauth a1				\r\n"
					+ "					left join t_domain_value c on c.codevalue = a1.cat_id and c.domainid = 'PERSON_CAT'\r\n"
					+ "					 where a1.sus_no = ?  AND a1.cat_id in ('1','2')";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, unit_sus_no);

			//System.err.println("auth a and b: \n"+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("group_a_gaz"));// 0
				list.add(rs.getString("group_b_gaz"));// 1
				list.add(rs.getString("group_b_non_gaz_Non_ind"));// 2
				list.add(rs.getString("group_c_non_gaz_Non_ind"));// 3
				list.add(rs.getString("group_b_non_gaz_ind"));// 4
				list.add(rs.getString("group_c_non_gaz_ind"));// 5
				list.add(rs.getString("total"));//6

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



	@Override
	public Map<String, String> getRegEstAppxAData(String unit_sus_no, String year, String month) {
		Map<String, String> dataMap = new HashMap<>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "SELECT DISTINCT\r\n"
					+ "    a.sus_no,\r\n"
					+ "    -- GAZ A Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'A' AND a.classification_services = '1' AND a.civ_type = '1' AND a.gender = 6) AS a_gaz_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'A' AND a.classification_services = '1' AND a.civ_type = '1' AND a.gender = 7) AS a_gaz_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'A' AND a.classification_services = '1' AND a.civ_type = '1') AS a_gaz_total_ind,\r\n"
					+ "    -- GAZ A Non-Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'A' AND a.classification_services = '1' AND a.civ_type = '2' AND a.gender = 6) AS a_gaz_man_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'A' AND a.classification_services = '1' AND a.civ_type = '2' AND a.gender = 7) AS a_gaz_female_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'A' AND a.classification_services = '1' AND a.civ_type = '2') AS a_gaz_total_nonind,\r\n"
					+ "    -- GAZ B Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '1' AND a.civ_type = '1' AND a.gender = 6) AS b_gaz_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '1' AND a.civ_type = '1' AND a.gender = 7) AS b_gaz_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '1' AND a.civ_type = '1') AS b_gaz_total_ind,\r\n"
					+ "    -- GAZ B Non-Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '1' AND a.civ_type = '2' AND a.gender = 6) AS b_gaz_man_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '1' AND a.civ_type = '2' AND a.gender = 7) AS b_gaz_female_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '1' AND a.civ_type = '2') AS b_gaz_total_nonind,\r\n"
					+ "    -- non gaz\r\n"
					+ "    -- Ministerial B Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '1' AND a.gender = 6) AS b_nongaz_m_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '1' AND a.gender = 7) AS b_nongaz_m_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '1') AS b_nongaz_m_total_ind,\r\n"
					+ "    -- Ministerial B Non-Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '2' AND a.gender = 6) AS b_nongaz_m_man_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '2' AND a.gender = 7) AS b_nongaz_m_female_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '2') AS b_nongaz_m_total_nonind,\r\n"
					+ "    -- Ministerial C Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '1' AND a.gender = 6) AS c_nongaz_m_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '1' AND a.gender = 7) AS c_nongaz_m_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '1') AS c_nongaz_m_total_ind,\r\n"
					+ "    -- Ministerial Non-Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '2' AND a.gender = 6) AS c_nongaz_m_man_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '2' AND a.gender = 7) AS c_nongaz_m_female_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '2') AS c_nongaz_m_total_nonind,\r\n"
					+ "    -- Executive B Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '1' AND a.gender = 6) AS b_nongaz_e_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '1' AND a.gender = 7) AS b_nongaz_e_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '1') AS b_nongaz_e_total_ind,\r\n"
					+ "    -- Executive B Non-Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '2' AND a.gender = 6) AS b_nongaz_e_man_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '2' AND a.gender = 7) AS b_nongaz_e_female_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '2') AS b_nongaz_e_total_nonind,\r\n"
					+ "    -- Executive C Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '1' AND a.gender = 6) AS c_nongaz_e_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '1' AND a.gender = 7) AS c_nongaz_e_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '1') AS c_nongaz_e_total_ind,\r\n"
					+ "    -- Executive B Non-Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '2' AND a.gender = 6) AS c_nongaz_e_man_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '2' AND a.gender = 7) AS c_nongaz_e_femalenonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '2') AS c_nongaz_e_totalnonind,\r\n"
					+ "    -- Technical B Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '1' AND a.gender = 6) AS b_nongaz_t_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '1' AND a.gender = 7) AS b_nongaz_t_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '1') AS b_nongaz_t_total_ind,\r\n"
					+ "    -- Technical B Non-Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '2' AND a.gender = 6) AS b_nongaz_t_man_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '2' AND a.gender = 7) AS b_nongaz_t_female_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '2') AS b_nongaz_t_total_nonind,\r\n"
					+ "    -- Technical C Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '1' AND a.gender = 6) AS c_nongaz_t_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '1' AND a.gender = 7) AS c_nongaz_t_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '1') AS c_nongaz_t_total_ind,\r\n"
					+ "    -- Technical C Non-Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '2' AND a.gender = 6) AS c_nongaz_t_man_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '2' AND a.gender = 7) AS c_nongaz_t_female_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '2') AS c_nongaz_t_total_nonind,\r\n"
					+ "    --nongaz ncsu\r\n"
					+ "    -- Office Worker C Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 3 AND a.civ_type = '1' AND a.gender = 6) AS c_nongaz_o_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 3 AND a.civ_type = '1' AND a.gender = 7) AS c_nongaz_o_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 3 AND a.civ_type = '1') AS c_nongaz_o_total_ind,\r\n"
					+ "    -- Office Worker C Non-Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 3 AND a.civ_type = '2' AND a.gender = 6) AS c_nongaz_o_man_nonind_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 3 AND a.civ_type = '2' AND a.gender = 7) AS c_nongaz_o_female_nonind_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 3 AND a.civ_type = '2') AS c_nongaz_o_total_nonind_nonind,\r\n"
					+ "    -- Semi-Skilled C Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 4 AND a.civ_type = '1' AND a.gender = 6) AS c_nongaz_s_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 4 AND a.civ_type = '1' AND a.gender = 7) AS c_nongaz_s_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 4 AND a.civ_type = '1') AS c_nongaz_s_total_ind,\r\n"
					+ "    -- Semi-Skilled C Non-Industrial\r\n "
					+ "	     COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 4 AND a.civ_type = '2' AND a.gender = 6) AS c_nongaz_s_man_nonind,"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 4 AND a.civ_type = '2' AND a.gender = 7) AS c_nongaz_s_female_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 4 AND a.civ_type = '2') AS c_nongaz_s_total_nonind,\r\n"
					+ "    -- Other C Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 5 AND a.civ_type = '1' AND a.gender = 6) AS c_nongaz_other_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 5 AND a.civ_type = '1' AND a.gender = 7) AS c_nongaz_other_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 5 AND a.civ_type = '1') AS c_nongaz_other_total_ind,\r\n"
					+ "    -- Other C Non-Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 5 AND a.civ_type = '2' AND a.gender = 6) AS c_nongaz_other_man_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 5 AND a.civ_type = '2' AND a.gender = 7) AS c_nongaz_other_female_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 5 AND a.civ_type = '2') AS c_nongaz_other_total_nonind,\r\n"
					+ "	\r\n"
					+ "	--Auth Columns\r\n"
					+ " COALESCE((select SUM(a1.base_auth + a1.mod_auth + a1.foot_auth) from  sus_pers_stdauth a1 where a1.sus_no = a.sus_no and a1.rank_cat = '4'),0) as a_auth_gaz,\r\n"
					+ "	 COALESCE((select SUM(a1.base_auth + a1.mod_auth + a1.foot_auth) from  sus_pers_stdauth a1 where a1.sus_no = a.sus_no and a1.rank_cat = '5'),0) as b_auth_gaz,\r\n"
					+ "	 COALESCE((select SUM(a1.base_auth + a1.mod_auth + a1.foot_auth) from  sus_pers_stdauth a1 where a1.sus_no = a.sus_no and a1.rank_cat in  ('6','9')),0) as b_non_gaz_auth,\r\n"
					+ "	 COALESCE((select SUM(a1.base_auth + a1.mod_auth + a1.foot_auth) from  sus_pers_stdauth a1 where a1.sus_no = a.sus_no and a1.rank_cat in ('7','8', '10', '12', '11')),0) as c_non_gaz_auth \r\n"
					+ "	\r\n"
					+ "FROM\r\n"
					+ "    tb_psg_civilian_dtl a\r\n"
					+ "INNER JOIN\r\n"
					+ "    tb_miso_orbat_unt_dtl ah ON a.sus_no = ah.sus_no AND ah.status_sus_no = 'Active'\r\n"
					+ " where a.status = '1' and a.sus_no=? \r\n"
					+ "and a.civilian_status = 'R' \r\n"
					+ "and to_date(to_char(a.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') \r\n"
					+ "group by a.sus_no";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, unit_sus_no);
			String dateString = String.format("%s/%s/01", year, month);
			stmt.setString(2, dateString);

			ResultSet rs = stmt.executeQuery();
			//System.out.print("appx a and b ====>" + stmt);
			if (rs.next()) {
				dataMap.put("a_gaz_man_ind", rs.getString("a_gaz_man_ind"));
				dataMap.put("a_gaz_female_ind", rs.getString("a_gaz_female_ind"));
				dataMap.put("a_gaz_total_ind", rs.getString("a_gaz_total_ind"));
				dataMap.put("a_gaz_man_nonind", rs.getString("a_gaz_man_nonind"));
				dataMap.put("a_gaz_female_nonind", rs.getString("a_gaz_female_nonind"));
				dataMap.put("a_gaz_total_nonind", rs.getString("a_gaz_total_nonind"));
				dataMap.put("b_gaz_man_ind", rs.getString("b_gaz_man_ind"));
				dataMap.put("b_gaz_female_ind", rs.getString("b_gaz_female_ind"));
				dataMap.put("b_gaz_total_ind", rs.getString("b_gaz_total_ind"));
				dataMap.put("b_gaz_man_nonind", rs.getString("b_gaz_man_nonind"));
				dataMap.put("b_gaz_female_nonind", rs.getString("b_gaz_female_nonind"));
				dataMap.put("b_gaz_total_nonind", rs.getString("b_gaz_total_nonind"));
				dataMap.put("b_nongaz_m_man_ind", rs.getString("b_nongaz_m_man_ind"));
				dataMap.put("b_nongaz_m_female_ind", rs.getString("b_nongaz_m_female_ind"));
				dataMap.put("b_nongaz_m_total_ind", rs.getString("b_nongaz_m_total_ind"));
				dataMap.put("b_nongaz_m_man_nonind", rs.getString("b_nongaz_m_man_nonind"));
				dataMap.put("b_nongaz_m_female_nonind", rs.getString("b_nongaz_m_female_nonind"));
				dataMap.put("b_nongaz_m_total_nonind", rs.getString("b_nongaz_m_total_nonind"));
				dataMap.put("c_nongaz_m_man_ind", rs.getString("c_nongaz_m_man_ind"));
				dataMap.put("c_nongaz_m_female_ind", rs.getString("c_nongaz_m_female_ind"));
				dataMap.put("c_nongaz_m_total_ind", rs.getString("c_nongaz_m_total_ind"));
				dataMap.put("c_nongaz_m_man_nonind", rs.getString("c_nongaz_m_man_nonind"));
				dataMap.put("c_nongaz_m_female_nonind", rs.getString("c_nongaz_m_female_nonind"));
				dataMap.put("c_nongaz_m_total_nonind", rs.getString("c_nongaz_m_total_nonind"));
				dataMap.put("b_nongaz_e_man_ind", rs.getString("b_nongaz_e_man_ind"));
				dataMap.put("b_nongaz_e_female_ind", rs.getString("b_nongaz_e_female_ind"));
				dataMap.put("b_nongaz_e_total_ind", rs.getString("b_nongaz_e_total_ind"));
				dataMap.put("b_nongaz_e_man_nonind", rs.getString("b_nongaz_e_man_nonind"));
				dataMap.put("b_nongaz_e_female_nonind", rs.getString("b_nongaz_e_female_nonind"));
				dataMap.put("b_nongaz_e_total_nonind", rs.getString("b_nongaz_e_total_nonind"));
				dataMap.put("c_nongaz_e_man_ind", rs.getString("c_nongaz_e_man_ind"));
				dataMap.put("c_nongaz_e_female_ind", rs.getString("c_nongaz_e_female_ind"));
				dataMap.put("c_nongaz_e_total_ind", rs.getString("c_nongaz_e_total_ind"));
				dataMap.put("c_nongaz_e_man_nonind", rs.getString("c_nongaz_e_man_nonind"));
				dataMap.put("c_nongaz_e_femalenonind", rs.getString("c_nongaz_e_femalenonind"));
				dataMap.put("c_nongaz_e_totalnonind", rs.getString("c_nongaz_e_totalnonind"));
				dataMap.put("b_nongaz_t_man_ind", rs.getString("b_nongaz_t_man_ind"));
				dataMap.put("b_nongaz_t_female_ind", rs.getString("b_nongaz_t_female_ind"));
				dataMap.put("b_nongaz_t_total_ind", rs.getString("b_nongaz_t_total_ind"));
				dataMap.put("b_nongaz_t_man_nonind", rs.getString("b_nongaz_t_man_nonind"));
				dataMap.put("b_nongaz_t_female_nonind", rs.getString("b_nongaz_t_female_nonind"));
				dataMap.put("b_nongaz_t_total_nonind", rs.getString("b_nongaz_t_total_nonind"));
				dataMap.put("c_nongaz_t_man_ind", rs.getString("c_nongaz_t_man_ind"));
				dataMap.put("c_nongaz_t_female_ind", rs.getString("c_nongaz_t_female_ind"));
				dataMap.put("c_nongaz_t_total_ind", rs.getString("c_nongaz_t_total_ind"));
				dataMap.put("c_nongaz_t_man_nonind", rs.getString("c_nongaz_t_man_nonind"));
				dataMap.put("c_nongaz_t_female_nonind", rs.getString("c_nongaz_t_female_nonind"));
				dataMap.put("c_nongaz_t_total_nonind", rs.getString("c_nongaz_t_total_nonind"));
				dataMap.put("c_nongaz_o_man_ind", rs.getString("c_nongaz_o_man_ind"));
				dataMap.put("c_nongaz_o_female_ind", rs.getString("c_nongaz_o_female_ind"));
				dataMap.put("c_nongaz_o_total_ind", rs.getString("c_nongaz_o_total_ind"));
				dataMap.put("c_nongaz_o_man_nonind_nonind",
						rs.getString("c_nongaz_o_man_nonind_nonind"));
				dataMap.put("c_nongaz_o_female_nonind_nonind",
						rs.getString("c_nongaz_o_female_nonind_nonind"));
				dataMap.put("c_nongaz_o_total_nonind_nonind",
						rs.getString("c_nongaz_o_total_nonind_nonind"));
				dataMap.put("c_nongaz_s_man_ind", rs.getString("c_nongaz_s_man_ind"));
				dataMap.put("c_nongaz_s_female_ind", rs.getString("c_nongaz_s_female_ind"));
				dataMap.put("c_nongaz_s_total_ind", rs.getString("c_nongaz_s_total_ind"));
				dataMap.put("c_nongaz_s_man_nonind", rs.getString("c_nongaz_s_man_nonind"));
				dataMap.put("c_nongaz_s_female_nonind", rs.getString("c_nongaz_s_female_nonind"));
				dataMap.put("c_nongaz_s_total_nonind", rs.getString("c_nongaz_s_total_nonind"));
				dataMap.put("c_nongaz_other_man_ind", rs.getString("c_nongaz_other_man_ind"));
				dataMap.put("c_nongaz_other_female_ind", rs.getString("c_nongaz_other_female_ind"));
				dataMap.put("c_nongaz_other_total_ind", rs.getString("c_nongaz_other_total_ind"));
				dataMap.put("c_nongaz_other_man_nonind", rs.getString("c_nongaz_other_man_nonind"));
				dataMap.put("c_nongaz_other_female_nonind",
						rs.getString("c_nongaz_other_female_nonind"));
				dataMap.put("c_nongaz_other_total_nonind", rs.getString("c_nongaz_other_total_nonind"));
				dataMap.put("a_auth_gaz", rs.getString("a_auth_gaz"));
				dataMap.put("b_auth_gaz", rs.getString("b_auth_gaz"));
				dataMap.put("b_non_gaz_auth", rs.getString("b_non_gaz_auth"));
				dataMap.put("c_non_gaz_auth", rs.getString("c_non_gaz_auth"));
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
		return dataMap;
	}




	@Override
	public Map<String, String> getNonRegularCivList(String unit_sus_no, String year, String month) {
		Map<String, String> dataMap = new HashMap<>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "SELECT DISTINCT\r\n"
					+ "    a.sus_no,   \r\n"
					+ "    -- PERSON PAID FROM CONTINGENCIES Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 1 AND a.civ_type = '1' AND a.gender = 6) AS c_nonreg_c_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 1 AND a.civ_type = '1' AND a.gender = 7) AS c_nonreg_c_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 1 AND a.civ_type = '1') AS c_nonreg_c_total_ind,\r\n"
					+ "    -- PERSON PAID FROM CONTINGENCIES Non-Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 1 AND a.civ_type = '2' AND a.gender = 6) AS c_nonreg_c_man_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 1 AND a.civ_type = '2' AND a.gender = 7) AS c_nonreg_c_female_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 1 AND a.civ_type = '2') AS c_nonreg_c_total_nonind,\r\n"
					+ "		\r\n"
					+ "    --WORK CHARGES PERSONNEL Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 2 AND a.civ_type = '1' AND a.gender = 6) AS c_nonreg_p_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 2 AND a.civ_type = '1' AND a.gender = 7) AS c_nonreg_p_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 2 AND a.civ_type = '1') AS c_nonreg_p_total_ind,\r\n"
					+ "    --WORK CHARGES PERSONNEL Non-Industrial\r\n"
					+ " 	     COUNT(*) FILTER (WHERE   a.pay_level = 2 AND a.civ_type = '2' AND a.gender = 6) AS c_nonreg_p_man_nonind, \r\n"
					+ "				COUNT(*) FILTER (WHERE   a.pay_level = 2 AND a.civ_type = '2' AND a.gender = 7) AS c_nonreg_p_female_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 2 AND a.civ_type = '2') AS c_nonreg_p_total_nonind,\r\n"
					+ "		\r\n"
					+ "    -- PERSON PAID FROM REGULAR PAY HEAD Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 3 AND a.civ_type = '1' AND a.gender = 6) AS c_nonreg_r_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 3 AND a.civ_type = '1' AND a.gender = 7) AS c_nonreg_r_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 3 AND a.civ_type = '1') AS c_nonreg_r_total_ind,\r\n"
					+ "    -- PERSON PAID FROM REGULAR PAY HEAD Non-Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 3 AND a.civ_type = '2' AND a.gender = 6) AS c_nonreg_r_man_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 3 AND a.civ_type = '2' AND a.gender = 7) AS c_nonreg_r_female_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 3 AND a.civ_type = '2') AS c_nonreg_r_total_nonind,\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "		  -- otehr Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 4 AND a.civ_type = '1' AND a.gender = 6) AS c_nonreg_other_man_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 4 AND a.civ_type = '1' AND a.gender = 7) AS c_nonreg_other_female_ind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 4 AND a.civ_type = '1') AS c_nonreg_other_total_ind,\r\n"
					+ "    -- otehr Non-Industrial\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 4 AND a.civ_type = '2' AND a.gender = 6) AS c_nonreg_other_man_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 4 AND a.civ_type = '2' AND a.gender = 7) AS c_nonreg_other_female_nonind,\r\n"
					+ "    COUNT(*) FILTER (WHERE   a.pay_level = 4 AND a.civ_type = '2') AS c_nonreg_other_total_nonind\r\n"
					+ "	\r\n"
					+ "	\r\n"
					+ "FROM\r\n"
					+ "    tb_psg_civilian_dtl a\r\n"
					+ "INNER JOIN\r\n"
					+ "    tb_miso_orbat_unt_dtl ah ON a.sus_no = ah.sus_no AND ah.status_sus_no = 'Active'\r\n"
					+ "		where a.status = '1'\r\n"
					+ "and a.sus_no=? \r\n"
					+ "and a.civilian_status = 'NR' \r\n"
					+ "and to_date(to_char(a.created_date,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') \r\n"
					+ "group by a.sus_no";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, unit_sus_no);
			String dateString = String.format("%s/%s/01", year, month);
			stmt.setString(2, dateString);

			ResultSet rs = stmt.executeQuery();
			//System.out.print("appx a and b ====>" + stmt);
			if (rs.next()) {
				dataMap.put("c_nonreg_c_man_ind", rs.getString("c_nonreg_c_man_ind"));
				dataMap.put("c_nonreg_c_female_ind", rs.getString("c_nonreg_c_female_ind"));
				dataMap.put("c_nonreg_c_total_ind", rs.getString("c_nonreg_c_total_ind"));
				dataMap.put("c_nonreg_c_man_nonind", rs.getString("c_nonreg_c_man_nonind"));
				dataMap.put("c_nonreg_c_female_nonind", rs.getString("c_nonreg_c_female_nonind"));
				dataMap.put("c_nonreg_c_total_nonind", rs.getString("c_nonreg_c_total_nonind"));
				dataMap.put("c_nonreg_p_man_ind", rs.getString("c_nonreg_p_man_ind"));
				dataMap.put("c_nonreg_p_female_ind", rs.getString("c_nonreg_p_female_ind"));
				dataMap.put("c_nonreg_p_total_ind", rs.getString("c_nonreg_p_total_ind"));
				dataMap.put("c_nonreg_p_man_nonind", rs.getString("c_nonreg_p_man_nonind"));
				dataMap.put("c_nonreg_p_female_nonind", rs.getString("c_nonreg_p_female_nonind"));
				dataMap.put("c_nonreg_p_total_nonind", rs.getString("c_nonreg_p_total_nonind"));
				dataMap.put("c_nonreg_r_man_ind", rs.getString("c_nonreg_r_man_ind"));
				dataMap.put("c_nonreg_r_female_ind", rs.getString("c_nonreg_r_female_ind"));
				dataMap.put("c_nonreg_r_total_ind", rs.getString("c_nonreg_r_total_ind"));
				dataMap.put("c_nonreg_r_man_nonind", rs.getString("c_nonreg_r_man_nonind"));
				dataMap.put("c_nonreg_r_female_nonind", rs.getString("c_nonreg_r_female_nonind"));
				dataMap.put("c_nonreg_r_total_nonind", rs.getString("c_nonreg_r_total_nonind"));
				dataMap.put("c_nonreg_other_man_ind", rs.getString("c_nonreg_other_man_ind"));
				dataMap.put("c_nonreg_other_female_ind", rs.getString("c_nonreg_other_female_ind"));
				dataMap.put("c_nonreg_other_total_ind", rs.getString("c_nonreg_other_total_ind"));
				dataMap.put("c_nonreg_other_man_nonind", rs.getString("c_nonreg_other_man_nonind"));
				dataMap.put("c_nonreg_other_female_nonind", rs.getString("c_nonreg_other_female_nonind"));
				dataMap.put("c_nonreg_other_total_nonind", rs.getString("c_nonreg_other_total_nonind"));

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
		return dataMap;
	}



	@Override
	public Map<String, String> getCivSummaryList(String unit_sus_no, String year, String month) {
		Map<String, String> dataMap = new HashMap<>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select  sum(a.held) as held_total,sum(a.auth) as auth_total,\r\n"
					+ " SUM(a.held) - SUM(a.auth) AS total_difference,\r\n"
					+ "    CASE \r\n"
					+ "        WHEN SUM(a.held) - SUM(a.auth) < 0 \r\n"
					+ "        THEN ABS(SUM(a.held) - SUM(a.auth)) \r\n"
					+ "        ELSE 0 \r\n"
					+ "    END AS defi,\r\n"
					+ "    CASE \r\n"
					+ "        WHEN SUM(a.held) - SUM(a.auth) > 0 \r\n"
					+ "        THEN SUM(a.held) - SUM(a.auth) \r\n"
					+ "        ELSE 0 \r\n"
					+ "    END AS sur\r\n"
					+ "		\r\n"
					+ "from (\r\n"
					+ "select\r\n"
					+ "	0 as held,\r\n"
					+ "	COALESCE(	SUM(a1.base_auth+a1.mod_auth+a1.foot_auth) filter (where	a1.rank_cat IN ('4','5','6','7','9',	'8','10','12','11')),0) as auth\r\n"
					+ "from\r\n"
					+ "	sus_pers_stdauth a1\r\n"
					+ "	left join t_domain_value c on c.codevalue=a1.cat_id\r\n"
					+ "	and c.domainid='PERSON_CAT'\r\n"
					+ "where\r\n"
					+ "	a1.sus_no=?\r\n"
					+ "	AND a1.cat_id in ('1', '2')\r\n"
					+ "union all\r\n"
					+ "SELECT DISTINCT\r\n"
					+ "	COUNT(a.id) as held,\r\n"
					+ "	0 as auth\r\n"
					+ "FROM\r\n"
					+ "	tb_psg_civilian_dtl a\r\n"
					+ "	INNER JOIN tb_miso_orbat_unt_dtl ah ON a.sus_no=ah.sus_no\r\n"
					+ "	AND ah.status_sus_no='Active'\r\n"
					+ "where\r\n"
					+ "	a.status='1'\r\n"
					+ "	and a.sus_no=?\r\n"
					+ "	and a.civilian_status='R'\r\n"
					+ "	and TO_DATE(TO_CHAR(a.date_of_tos,'Mon YYYY'),'Mon YYYY')<=to_dATE(?,'YYYY/MM/DD')) a";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, unit_sus_no);
			stmt.setString(2, unit_sus_no);
			String dateString = String.format("%s/%s/01", year, month);
			stmt.setString(3, dateString);

			System.err.println("auth a and b summry: \n"+stmt);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				dataMap.put("held_total", rs.getString("held_total"));
				dataMap.put("auth_total", rs.getString("auth_total"));
				dataMap.put("defi", rs.getString("defi"));
				dataMap.put("sur", rs.getString("sur"));

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
		return dataMap;
	}


	@Override
	public String getCommandName(String sus_no) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String comdName = "";

		try {
			conn = dataSource.getConnection();

			String q = "select"
					+ " ab.cmd_name\r\n"
					+ "from\r\n"
					+ " tb_miso_orbat_unt_dtl a\r\n"
					+ " inner JOIN orbat_view_cmd ab ON SUBSTR(a.form_code_control::TEXT,1, 1)=ab.cmd_code::TEXT\r\n"
					+ " where\r\n"
					+ " a.status_sus_no='Active'\r\n"
					+ " and a.sus_no=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, sus_no);

			rs = stmt.executeQuery();

			if (rs.next()) {
				comdName = rs.getString("cmd_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return comdName;
	}





	@Override
	public Boolean insertAppxAandBCiv(String username, String roleSusNo, String month,
			String year, String observation) throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		int val = 0;
		int count = 0;

		ArrayList<ArrayList<String>> orbatlist = getcommand(roleSusNo);

		List<TB_PSG_APPX_A_B_CIV_MAIN> version = Get_appx_a_bciv_VersionData(month, year, roleSusNo);
		try {


			if (version.size() == 0) {

				val = 1;

				pstmt = conn.prepareStatement(Insert_Report_AppxCiv_Version());
				pstmt.setString(1, roleSusNo);
				pstmt.setString(2, month);
				pstmt.setString(3, year);
				pstmt.setString(4, String.valueOf(val));
				pstmt.setInt(5, 0);
				pstmt.setString(6, username);
				pstmt.executeUpdate();


				pstmt = conn.prepareStatement(Insert_Report_AppxCiv_Main_Details());
				pstmt.setString(1, roleSusNo);
				pstmt.setString(2, month);
				pstmt.setString(3, year);
				pstmt.setString(4, String.valueOf(val));
				pstmt.setString(5, orbatlist.get(0).get(1));// cmd sus
				pstmt.setString(6, orbatlist.get(0).get(5));// cmd unit
				pstmt.setString(7, orbatlist.get(0).get(2));// corp sus
				pstmt.setString(8, orbatlist.get(0).get(6));// corp unit
				pstmt.setString(9, orbatlist.get(0).get(3));// div sus
				pstmt.setString(10, orbatlist.get(0).get(7));// div unit
				pstmt.setString(11, orbatlist.get(0).get(4));// bde sus
				pstmt.setString(12, orbatlist.get(0).get(8));// bde unit
				pstmt.setString(13, username);
				pstmt.setString(14, observation);

				pstmt.executeUpdate();

			} else {
				count = Integer.parseInt(version.get(0).getVersion());
				val = count + 1;

				pstmt = conn.prepareStatement(Update_Report_appxCiv_Version());
				pstmt.setString(1, month);
				pstmt.setString(2, year);
				pstmt.setString(3, String.valueOf(val));
				pstmt.setString(4, username);
				pstmt.setInt(5, version.get(0).getId());

				pstmt.executeUpdate();

				pstmt = conn.prepareStatement(Insert_Report_AppxCiv_Main_Details());
				pstmt.setString(1, roleSusNo);
				pstmt.setString(2, month);
				pstmt.setString(3, year);
				pstmt.setString(4, String.valueOf(val));
				pstmt.setString(5, orbatlist.get(0).get(1));// cmd sus
				pstmt.setString(6, orbatlist.get(0).get(5));// cmd unit
				pstmt.setString(7, orbatlist.get(0).get(2));// corp sus
				pstmt.setString(8, orbatlist.get(0).get(6));// corp unit
				pstmt.setString(9, orbatlist.get(0).get(3));// div sus
				pstmt.setString(10, orbatlist.get(0).get(7));// div unit
				pstmt.setString(11, orbatlist.get(0).get(4));// bde sus
				pstmt.setString(12, orbatlist.get(0).get(8));// bde unit
				pstmt.setString(13, username);
				pstmt.setString(14, observation);

				pstmt.executeUpdate();

			}

			pstmt = conn.prepareStatement(Insert_Report_appxauth_civ());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, String.valueOf(val));
			pstmt.setString(5, roleSusNo);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Insert_reg_est_appx_a_b_civ());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, String.valueOf(val));
			pstmt.setString(5, roleSusNo);
			pstmt.setString(6, String.format("%s/%s/01", year, month));
			pstmt.executeUpdate();


			pstmt = conn.prepareStatement(Insert__non_reg_est_appx_a_b_Civ());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, String.valueOf(val));
			pstmt.setString(5, roleSusNo);
			pstmt.setString(6, String.format("%s/%s/01", year, month));
			pstmt.executeUpdate();



			pstmt = conn.prepareStatement(Insert_Report_appxCiv_Summary());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, String.valueOf(val));
			pstmt.setString(5, roleSusNo);
			pstmt.setString(6, roleSusNo);
			String dateString = String.format("%s/%s/01", year, month);
			pstmt.setString(7, dateString);
			int vsdfal= pstmt.executeUpdate();

			pstmt.close();
			conn.commit();
			conn.close();
		} catch (Exception e) {

		}


		return true;
	}





	@Override
	public List<TB_PSG_APPX_A_B_CIV_MAIN> Get_appx_a_bciv_VersionData(String month, String year, String roleSusNo) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_APPX_A_B_CIV_MAIN where month=:month and year=:year and sus_no=:roleSusNo";
		Query query = sessionHQL.createQuery(hqlUpdate).setString("month", month).setString("year", year)
				.setString("roleSusNo", roleSusNo);
		@SuppressWarnings("unchecked")
		List<TB_PSG_APPX_A_B_CIV_MAIN> list = query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}



	//		================================= insert appx a and b =====================================================
	public String Insert_Report_AppxCiv_Version() {
		return "INSERT INTO tb_psg_appx_a_b_civ_main \r\n"
				+ "(sus_no,month,year,version,status,created_by,created_date) VALUES (?,?,?,?,?,?,now()::timestamp) ";

	}

	public String Insert_Report_AppxCiv_Main_Details() {
		return "INSERT INTO tb_psg_appx_a_b_civ_main_details \r\n"
				+ "(sus_no,month,year,version,cmd_sus,cmd_unit,corp_sus,corp_unit,div_sus,div_unit,bde_sus,"
				+ "bde_unit,created_by,created_date,observation) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,now()::timestamp,?) ";
	}

	public String Update_Report_appxCiv_Version() {
		return "update tb_psg_appx_a_b_civ_main set month= ?, year= ?, version= ?,status='0',\r\n"
				+ "modified_by= ?, modified_date= now()::timestamp where id=?";
	}

	public String Insert_Report_appxauth_civ() {

		return "insert into tb_psg_auth_appx_a_b_civ(sus_no,month,year,version,group_a_gaz,group_b_gaz,group_b_non_gaz_Non_ind,group_c_non_gaz_Non_ind,\r\n"
				+ "										group_b_non_gaz_ind,group_c_non_gaz_ind,total)\r\n"
				+ "\r\n"
				+ "select               ? as sus_no,? as month,? as year ,? as version, \r\n"
				+ "                     COALESCE( sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '4'),0) as group_a_gaz,\r\n"
				+ "					COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '5'),0) as group_b_gaz,\r\n"
				+ "					COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '6'),0) as group_b_non_gaz_Non_ind,\r\n"
				+ "					COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '7'),0) as group_c_non_gaz_Non_ind,\r\n"
				+ "					COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '9'),0) as group_b_non_gaz_ind,\r\n"
				+ "					COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat in( '8','10','12','11')),0) as group_c_non_gaz_ind,\r\n"
				+ "					COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth)  filter (where a.rank_cat IN ('4','5','6','7','9','8','10','12','11')),0) as total	\r\n"
				+ "					 from sus_pers_stdauth a\r\n"
				+ "					 left join   t_domain_value d on d.codevalue = a.cat_id and d.domainid = 'PERSON_CAT'					\r\n"
				+ "					 where a.sus_No =?\r\n"
				+ "					 and a.cat_id in ('1','2') ";

	}


	public String Insert_reg_est_appx_a_b_civ() {

		return "insert into  tb_psg_reg_est_appx_a_b_civ (\r\n"
				+ "		status,\r\n"
				+ "    sus_no ,\r\n"
				+ "    month ,\r\n"
				+ "    year ,\r\n"
				+ "    version, \r\n"
				+ "    a_gaz_man_ind ,\r\n"
				+ "    a_gaz_female_ind ,\r\n"
				+ "    a_gaz_total_ind ,\r\n"
				+ "    a_gaz_man_nonind ,\r\n"
				+ "    a_gaz_female_nonind ,\r\n"
				+ "    a_gaz_total_nonind ,\r\n"
				+ "    b_gaz_man_ind ,\r\n"
				+ "    b_gaz_female_ind ,\r\n"
				+ "	  b_gaz_total_ind ,\r\n"
				+ "    b_gaz_man_nonind ,\r\n"
				+ "    b_gaz_female_nonind ,\r\n"
				+ "    b_gaz_total_nonind ,\r\n"
				+ "    b_nongaz_m_man_ind ,\r\n"
				+ "    b_nongaz_m_female_ind ,\r\n"
				+ "    b_nongaz_m_total_ind ,\r\n"
				+ "    b_nongaz_m_man_nonind ,\r\n"
				+ "		b_nongaz_m_female_nonind ,\r\n"
				+ "    b_nongaz_m_total_nonind ,\r\n"
				+ "    c_nongaz_m_man_ind ,\r\n"
				+ "    c_nongaz_m_female_ind ,\r\n"
				+ "    c_nongaz_m_total_ind ,\r\n"
				+ "    c_nongaz_m_man_nonind ,\r\n"
				+ "    c_nongaz_m_female_nonind ,\r\n"
				+ "    c_nongaz_m_total_nonind ,\r\n"
				+ "	  b_nongaz_e_man_ind ,\r\n"
				+ "    b_nongaz_e_female_ind ,\r\n"
				+ "    b_nongaz_e_total_ind ,\r\n"
				+ "    b_nongaz_e_man_nonind ,\r\n"
				+ "    b_nongaz_e_female_nonind ,\r\n"
				+ "    b_nongaz_e_total_nonind ,\r\n"
				+ "    c_nongaz_e_man_ind ,\r\n"
				+ "    c_nongaz_e_female_ind ,\r\n"
				+ "		c_nongaz_e_total_ind ,\r\n"
				+ "    c_nongaz_e_man_nonind ,\r\n"
				+ "    c_nongaz_e_femalenonind ,\r\n"
				+ "    c_nongaz_e_totalnonind ,\r\n"
				+ "    b_nongaz_t_man_ind ,\r\n"
				+ "    b_nongaz_t_female_ind ,\r\n"
				+ "    b_nongaz_t_total_ind ,\r\n"
				+ "    b_nongaz_t_man_nonind ,\r\n"
				+ "	  b_nongaz_t_female_nonind ,\r\n"
				+ "    b_nongaz_t_total_nonind ,\r\n"
				+ "    c_nongaz_t_man_ind ,\r\n"
				+ "    c_nongaz_t_female_ind ,\r\n"
				+ "    c_nongaz_t_total_ind ,\r\n"
				+ "    c_nongaz_t_man_nonind ,\r\n"
				+ "    c_nongaz_t_female_nonind ,\r\n"
				+ "    c_nongaz_t_total_nonind ,\r\n"
				+ "		c_nongaz_o_man_ind ,\r\n"
				+ "    c_nongaz_o_female_ind ,\r\n"
				+ "    c_nongaz_o_total_ind ,\r\n"
				+ "    c_nongaz_o_man_nonind_nonind ,\r\n"
				+ "    c_nongaz_o_female_nonind_nonind ,\r\n"
				+ "    c_nongaz_o_total_nonind_nonind ,\r\n"
				+ "    c_nongaz_s_man_ind ,\r\n"
				+ "    c_nongaz_s_female_ind ,\r\n"
				+ "	  c_nongaz_s_total_ind ,\r\n"
				+ "    c_nongaz_s_man_nonind ,\r\n"
				+ "    c_nongaz_s_female_nonind ,\r\n"
				+ "    c_nongaz_s_total_nonind ,\r\n"
				+ "    c_nongaz_other_man_ind ,\r\n"
				+ "    c_nongaz_other_female_ind ,\r\n"
				+ "    c_nongaz_other_total_ind ,\r\n"
				+ "    c_nongaz_other_man_nonind ,\r\n"
				+ "		c_nongaz_other_female_nonind ,\r\n"
				+ "    c_nongaz_other_total_nonind ,\r\n"
				+ "    a_auth_gaz ,\r\n"
				+ "    b_auth_gaz ,\r\n"
				+ "    b_non_gaz_auth ,\r\n"
				+ "    c_non_gaz_auth )"
				+ "\r\n"
				+   " SELECT DISTINCT 0,? as sus_no , ? as month, ? as year,? as version\r\n"
				+ "    ,\r\n"
				+ "    -- GAZ A Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'A' AND a.classification_services = '1' AND a.civ_type = '1' AND a.gender = 6) AS a_gaz_man_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'A' AND a.classification_services = '1' AND a.civ_type = '1' AND a.gender = 7) AS a_gaz_female_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'A' AND a.classification_services = '1' AND a.civ_type = '1') AS a_gaz_total_ind,\r\n"
				+ "    -- GAZ A Non-Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'A' AND a.classification_services = '1' AND a.civ_type = '2' AND a.gender = 6) AS a_gaz_man_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'A' AND a.classification_services = '1' AND a.civ_type = '2' AND a.gender = 7) AS a_gaz_female_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'A' AND a.classification_services = '1' AND a.civ_type = '2') AS a_gaz_total_nonind,\r\n"
				+ "    -- GAZ B Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '1' AND a.civ_type = '1' AND a.gender = 6) AS b_gaz_man_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '1' AND a.civ_type = '1' AND a.gender = 7) AS b_gaz_female_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '1' AND a.civ_type = '1') AS b_gaz_total_ind,\r\n"
				+ "    -- GAZ B Non-Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '1' AND a.civ_type = '2' AND a.gender = 6) AS b_gaz_man_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '1' AND a.civ_type = '2' AND a.gender = 7) AS b_gaz_female_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '1' AND a.civ_type = '2') AS b_gaz_total_nonind,\r\n"
				+ "    -- non gaz\r\n"
				+ "    -- Ministerial B Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '1' AND a.gender = 6) AS b_nongaz_m_man_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '1' AND a.gender = 7) AS b_nongaz_m_female_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '1') AS b_nongaz_m_total_ind,\r\n"
				+ "    -- Ministerial B Non-Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '2' AND a.gender = 6) AS b_nongaz_m_man_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '2' AND a.gender = 7) AS b_nongaz_m_female_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '2') AS b_nongaz_m_total_nonind,\r\n"
				+ "    -- Ministerial C Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '1' AND a.gender = 6) AS c_nongaz_m_man_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '1' AND a.gender = 7) AS c_nongaz_m_female_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '1') AS c_nongaz_m_total_ind,\r\n"
				+ "    -- Ministerial Non-Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '2' AND a.gender = 6) AS c_nongaz_m_man_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '2' AND a.gender = 7) AS c_nongaz_m_female_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 6 AND a.civ_type = '2') AS c_nongaz_m_total_nonind,\r\n"
				+ "    -- Executive B Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '1' AND a.gender = 6) AS b_nongaz_e_man_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '1' AND a.gender = 7) AS b_nongaz_e_female_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '1') AS b_nongaz_e_total_ind,\r\n"
				+ "    -- Executive B Non-Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '2' AND a.gender = 6) AS b_nongaz_e_man_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '2' AND a.gender = 7) AS b_nongaz_e_female_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '2') AS b_nongaz_e_total_nonind,\r\n"
				+ "    -- Executive C Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '1' AND a.gender = 6) AS c_nongaz_e_man_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '1' AND a.gender = 7) AS c_nongaz_e_female_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '1') AS c_nongaz_e_total_ind,\r\n"
				+ "    -- Executive B Non-Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '2' AND a.gender = 6) AS c_nongaz_e_man_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '2' AND a.gender = 7) AS c_nongaz_e_femalenonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 1 AND a.civ_type = '2') AS c_nongaz_e_totalnonind,\r\n"
				+ "    -- Technical B Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '1' AND a.gender = 6) AS b_nongaz_t_man_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '1' AND a.gender = 7) AS b_nongaz_t_female_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '1') AS b_nongaz_t_total_ind,\r\n"
				+ "    -- Technical B Non-Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '2' AND a.gender = 6) AS b_nongaz_t_man_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '2' AND a.gender = 7) AS b_nongaz_t_female_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'B' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '2') AS b_nongaz_t_total_nonind,\r\n"
				+ "    -- Technical C Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '1' AND a.gender = 6) AS c_nongaz_t_man_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '1' AND a.gender = 7) AS c_nongaz_t_female_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '1') AS c_nongaz_t_total_ind,\r\n"
				+ "    -- Technical C Non-Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '2' AND a.gender = 6) AS c_nongaz_t_man_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '2' AND a.gender = 7) AS c_nongaz_t_female_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 2 AND a.civ_type = '2') AS c_nongaz_t_total_nonind,\r\n"
				+ "    --nongaz ncsu\r\n"
				+ "    -- Office Worker C Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 3 AND a.civ_type = '1' AND a.gender = 6) AS c_nongaz_o_man_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 3 AND a.civ_type = '1' AND a.gender = 7) AS c_nongaz_o_female_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 3 AND a.civ_type = '1') AS c_nongaz_o_total_ind,\r\n"
				+ "    -- Office Worker C Non-Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 3 AND a.civ_type = '2' AND a.gender = 6) AS c_nongaz_o_man_nonind_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 3 AND a.civ_type = '2' AND a.gender = 7) AS c_nongaz_o_female_nonind_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 3 AND a.civ_type = '2') AS c_nongaz_o_total_nonind_nonind,\r\n"
				+ "    -- Semi-Skilled C Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 4 AND a.civ_type = '1' AND a.gender = 6) AS c_nongaz_s_man_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 4 AND a.civ_type = '1' AND a.gender = 7) AS c_nongaz_s_female_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 4 AND a.civ_type = '1') AS c_nongaz_s_total_ind,\r\n"
				+ "    -- Semi-Skilled C Non-Industrial\r\n "
				+ "	     COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 4 AND a.civ_type = '2' AND a.gender = 6) AS c_nongaz_s_man_nonind,"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 4 AND a.civ_type = '2' AND a.gender = 7) AS c_nongaz_s_female_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 4 AND a.civ_type = '2') AS c_nongaz_s_total_nonind,\r\n"
				+ "    -- Other C Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 5 AND a.civ_type = '1' AND a.gender = 6) AS c_nongaz_other_man_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 5 AND a.civ_type = '1' AND a.gender = 7) AS c_nongaz_other_female_ind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 5 AND a.civ_type = '1') AS c_nongaz_other_total_ind,\r\n"
				+ "    -- Other C Non-Industrial\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 5 AND a.civ_type = '2' AND a.gender = 6) AS c_nongaz_other_man_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 5 AND a.civ_type = '2' AND a.gender = 7) AS c_nongaz_other_female_nonind,\r\n"
				+ "    COUNT(*) FILTER (WHERE a.civ_group = 'C' AND a.classification_services = '2' AND a.classification_trade = 5 AND a.civ_type = '2') AS c_nongaz_other_total_nonind,\r\n"
				+ "	\r\n"
				+ "	--Auth Columns\r\n"
				+ "  COALESCE((select SUM(a1.base_auth + a1.mod_auth + a1.foot_auth) from  sus_pers_stdauth a1 where a1.sus_no = a.sus_no and a1.rank_cat = '4'),0) as a_auth_gaz,\r\n"
				+ "	 COALESCE((select SUM(a1.base_auth + a1.mod_auth + a1.foot_auth) from  sus_pers_stdauth a1 where a1.sus_no = a.sus_no and a1.rank_cat = '5'),0) as b_auth_gaz,\r\n"
				+ "	 COALESCE((select SUM(a1.base_auth + a1.mod_auth + a1.foot_auth) from  sus_pers_stdauth a1 where a1.sus_no = a.sus_no and a1.rank_cat in  ('6','9')),0) as b_non_gaz_auth,\r\n"
				+ "	 COALESCE((select SUM(a1.base_auth + a1.mod_auth + a1.foot_auth) from  sus_pers_stdauth a1 where a1.sus_no = a.sus_no and a1.rank_cat in ('7','8', '10', '12', '11')),0) as c_non_gaz_auth"
				+ "	\r\n"
				+ "FROM\r\n"
				+ "    tb_psg_civilian_dtl a\r\n"
				+ "INNER JOIN\r\n"
				+ "    tb_miso_orbat_unt_dtl ah ON a.sus_no = ah.sus_no AND ah.status_sus_no = 'Active'\r\n"
				+ "where a.status = '1' and a.sus_no=? \r\n"
				+ "and a.civilian_status = 'R' \r\n"
				+ "and to_date(to_char(a.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') \r\n"
				+ "group by a.sus_no";

	}


	public String Insert__non_reg_est_appx_a_b_Civ() {

		return "insert into tb_psg_non_reg_est_appx_a_b_civ( \r\n"
				+ "    status,\r\n"
				+ "    sus_no ,\r\n"
				+ "    month ,\r\n"
				+ "    year ,\r\n"
				+ "    version ,\r\n"
				+ "    c_nonreg_c_man_ind,\r\n"
				+ "    c_nonreg_c_female_ind,\r\n"
				+ "    c_nonreg_c_total_ind,\r\n"
				+ "    c_nonreg_c_man_nonind,\r\n"
				+ "    c_nonreg_c_female_nonind,\r\n"
				+ "    c_nonreg_c_total_nonind,\r\n"
				+ "    c_nonreg_p_man_ind,\r\n"
				+ "    c_nonreg_p_female_ind,\r\n"
				+ "	  c_nonreg_p_total_ind,\r\n"
				+ "    c_nonreg_p_man_nonind,\r\n"
				+ "    c_nonreg_p_female_nonind,\r\n"
				+ "    c_nonreg_p_total_nonind,\r\n"
				+ "    c_nonreg_r_man_ind,\r\n"
				+ "    c_nonreg_r_female_ind,\r\n"
				+ "    c_nonreg_r_total_ind,\r\n"
				+ "    c_nonreg_r_man_nonind,\r\n"
				+ "		c_nonreg_r_female_nonind,\r\n"
				+ "    c_nonreg_r_total_nonind,\r\n"
				+ "	  c_nonreg_other_man_ind,\r\n"
				+ "    c_nonreg_other_female_ind,\r\n"
				+ "    c_nonreg_other_total_ind,\r\n"
				+ "    c_nonreg_other_man_nonind,\r\n"
				+ "		c_nonreg_other_female_nonind,\r\n"
				+ "    c_nonreg_other_total_nonind\r\n"
				+ ")"


								+ "     SELECT DISTINCT  0 , ? as sus_no,? as month,? as year,? as version, \r\n"
								+ "      \r\n"
								+ "    -- PERSON PAID FROM CONTINGENCIES Industrial\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 1 AND a.civ_type = '1' AND a.gender = 6) AS c_nonreg_c_man_ind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 1 AND a.civ_type = '1' AND a.gender = 7) AS c_nonreg_c_female_ind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 1 AND a.civ_type = '1') AS c_nonreg_c_total_ind,\r\n"
								+ "    -- PERSON PAID FROM CONTINGENCIES Non-Industrial\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 1 AND a.civ_type = '2' AND a.gender = 6) AS c_nonreg_c_man_nonind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 1 AND a.civ_type = '2' AND a.gender = 7) AS c_nonreg_c_female_nonind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 1 AND a.civ_type = '2') AS c_nonreg_c_total_nonind,\r\n"
								+ "		\r\n"
								+ "    --WORK CHARGES PERSONNEL Industrial\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 2 AND a.civ_type = '1' AND a.gender = 6) AS c_nonreg_p_man_ind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 2 AND a.civ_type = '1' AND a.gender = 7) AS c_nonreg_p_female_ind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 2 AND a.civ_type = '1') AS c_nonreg_p_total_ind,\r\n"
								+ "    --WORK CHARGES PERSONNEL Non-Industrial\r\n"
								+ " 	     COUNT(*) FILTER (WHERE   a.pay_level = 2 AND a.civ_type = '2' AND a.gender = 6) AS c_nonreg_p_man_nonind, \r\n"
								+ "				COUNT(*) FILTER (WHERE   a.pay_level = 2 AND a.civ_type = '2' AND a.gender = 7) AS c_nonreg_p_female_nonind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 2 AND a.civ_type = '2') AS c_nonreg_p_total_nonind,\r\n"
								+ "		\r\n"
								+ "    -- PERSON PAID FROM REGULAR PAY HEAD Industrial\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 3 AND a.civ_type = '1' AND a.gender = 6) AS c_nonreg_r_man_ind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 3 AND a.civ_type = '1' AND a.gender = 7) AS c_nonreg_r_female_ind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 3 AND a.civ_type = '1') AS c_nonreg_r_total_ind,\r\n"
								+ "    -- PERSON PAID FROM REGULAR PAY HEAD Non-Industrial\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 3 AND a.civ_type = '2' AND a.gender = 6) AS c_nonreg_r_man_nonind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 3 AND a.civ_type = '2' AND a.gender = 7) AS c_nonreg_r_female_nonind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 3 AND a.civ_type = '2') AS c_nonreg_r_total_nonind,\r\n"
								+ "\r\n"
								+ "\r\n"
								+ "		  -- otehr Industrial\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 4 AND a.civ_type = '1' AND a.gender = 6) AS c_nonreg_other_man_ind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 4 AND a.civ_type = '1' AND a.gender = 7) AS c_nonreg_other_female_ind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 4 AND a.civ_type = '1') AS c_nonreg_other_total_ind,\r\n"
								+ "    -- otehr Non-Industrial\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 4 AND a.civ_type = '2' AND a.gender = 6) AS c_nonreg_other_man_nonind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 4 AND a.civ_type = '2' AND a.gender = 7) AS c_nonreg_other_female_nonind,\r\n"
								+ "    COUNT(*) FILTER (WHERE   a.pay_level = 4 AND a.civ_type = '2') AS c_nonreg_other_total_nonind\r\n"
								+ "	\r\n"
								+ "	\r\n"
								+ "FROM\r\n"
								+ "    tb_psg_civilian_dtl a\r\n"
								+ "INNER JOIN\r\n"
								+ "    tb_miso_orbat_unt_dtl ah ON a.sus_no = ah.sus_no AND ah.status_sus_no = 'Active'\r\n"
								+ "		where a.status = '1'\r\n"
								+ "and a.sus_no=? \r\n"
								+ "and a.civilian_status = 'NR' \r\n"
								+ "and to_date(to_char(a.created_date,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') \r\n"
								+ "group by a.sus_no";

	}



	public String Insert_Report_appxCiv_Summary() {

		return "INSERT INTO tb_psg_summary_appx_a_b_civ (SUS_NO,MONTH,YEAR,VERSION,CIV_AUTH,CIV_POSTED,CIV_DEFI,CIV_SUR)\r\n"
				+ "select  ? as sus_no,? as month,? as year,? as version,sum(a.auth) as auth_total, sum(a.held) as held_total,\r\n"
				+ "    CASE \r\n"
				+ "        WHEN SUM(a.held) - SUM(a.auth) < 0 \r\n"
				+ "        THEN ABS(SUM(a.held) - SUM(a.auth)) \r\n"
				+ "        ELSE 0 \r\n"
				+ "    END AS defi,\r\n"
				+ "    CASE \r\n"
				+ "        WHEN SUM(a.held) - SUM(a.auth) > 0 \r\n"
				+ "        THEN SUM(a.held) - SUM(a.auth) \r\n"
				+ "        ELSE 0 \r\n"
				+ "    END AS sur\r\n"
				+ "		\r\n"
				+ "from (\r\n"
				+ "select\r\n"
				+ "	0 as held,\r\n"
				+ "	COALESCE(	SUM(a1.base_auth+a1.mod_auth+a1.foot_auth) filter (where	a1.rank_cat IN ('4','5','6','7','9',	'8','10','12','11')),0) as auth\r\n"
				+ "from\r\n"
				+ "	sus_pers_stdauth a1\r\n"
				+ "	left join t_domain_value c on c.codevalue=a1.cat_id\r\n"
				+ "	and c.domainid='PERSON_CAT'\r\n"
				+ "where\r\n"
				+ "	a1.sus_no=?\r\n"
				+ "	AND a1.cat_id in ('1', '2')\r\n"
				+ "union all\r\n"
				+ "SELECT DISTINCT\r\n"
				+ "	COUNT(a.id) as held,\r\n"
				+ "	0 as auth\r\n"
				+ "FROM\r\n"
				+ "	tb_psg_civilian_dtl a\r\n"
				+ "	INNER JOIN tb_miso_orbat_unt_dtl ah ON a.sus_no=ah.sus_no\r\n"
				+ "	AND ah.status_sus_no='Active'\r\n"
				+ "where\r\n"
				+ "	a.status='1'\r\n"
				+ "	and a.sus_no=?\r\n"
				+ "	and a.civilian_status='R'\r\n"
				+ "	and TO_DATE(TO_CHAR(a.date_of_tos,'Mon YYYY'),'Mon YYYY')<=to_dATE(?,'YYYY/MM/DD')) a";

	}





	// ---------------- search appx a and b civ submitted from------------//
	@Override
	public ArrayList<ArrayList<String>> Search_civ_appxAandB_Version(String month, String year, HttpSession session,
			String status, String unit_sus_no) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (roleAccess.equals("Unit")) {
				if (!roleSusNo.equals("")) {
					qry += " and ms.sus_no = ? ";
				}
			}
			if (roleAccess.equals("MISO")) {
				if(!unit_sus_no.equals("")) {
					qry += " and ms.sus_no = ? ";
				}
			}
			if (!month.equals("0")) {
				qry += " and m.month = ? ";
			}
			if (!year.equals("")) {
				qry += " and m.year = ? ";
			}
			if (!status.equals("")) {
				qry += "and m.status= cast(? as integer)";
			}
			q = "select distinct m.sus_no,ms.unit_name,TO_CHAR( TO_DATE (m.month::text, 'MM'), 'Month') AS month_name,"
					+ "m.month,m.year,m.version from tb_psg_appx_a_b_civ_main m \r\n"
					+ " inner join tb_miso_orbat_unt_dtl ms on ms.sus_no = m.sus_no and ms.status_sus_no='Active' "
					+ " where m.id!= 0  " + qry;

			stmt = conn.prepareStatement(q);

			if (!qry.equals("")) {

				int j = 1;

				if (roleAccess.equals("Unit")) {
					if (roleSusNo != "") {
						stmt.setString(j, roleSusNo);
						j += 1;
					}
				}
				if (roleAccess.equals("MISO")) {
					if (unit_sus_no != "") {
						stmt.setString(j, unit_sus_no);
						j += 1;
					}
				}
				if (!month.equals("0")) {
					stmt.setString(j, month);
					j += 1;
				}
				if (year != "") {
					stmt.setString(j, year);
					j += 1;
				}
				if (status != "") {
					stmt.setString(j, status);
					// j += 1;
				}
			}

			int i = 0;

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("unit_name"));// 0
				list.add(rs.getString("month_name"));// 1
				list.add(rs.getString("year"));// 2
				list.add(rs.getString("version"));// 3


				String f1 = "";
				String f4 = "";
				if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("0")){

					String View = "onclick=\"if (confirm('Are You Sure You Want to Approve This ?')) { editData('"
							+ rs.getString("month") + "','" + rs.getString("year") + "','" + rs.getString("version")
							+ "','" + rs.getString("sus_no") + "'); } else { return false; }\"";
					f4 = "<i class='fa fa-eye' " + View + " title='Approve/View Data'></i>";

					String Delete1 = "onclick=\"if (confirm('Are You Sure You Want to Delete This ?')) { deleteData('"
							+ rs.getString("month") + "','" + rs.getString("year") + "','" + rs.getString("version")
							+ "','" + rs.getString("sus_no") + "'); } else { return false; }\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Dustbin'></i>";


				}

				if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("1")){

					String View = "onclick=\"if (confirm('Are You Sure You Want to Approve This ?')) { editData('"
							+ rs.getString("month") + "','" + rs.getString("year") + "','" + rs.getString("version")
							+ "','" + rs.getString("sus_no") + "'); } else { return false; }\"";
					f4 = "<i class='fa fa-eye' " + View + " title='Approve/View Data'></i>";

					/*String Download = "onclick=\"if (confirm('Are You Sure You Want to Approve This ?')) { downloadData('"
							+ rs.getString("month") + "','" + rs.getString("year") + "','" + rs.getString("version")
							+ "','" + rs.getString("sus_no") + "'); } else { return false; }\"";

					f1 = "<i class='action_icons action_download' " + Download + " title='Download Data'></i>";*/

				}

				list.add(f4);
				/*list.add(f1);*/
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




	// ----- appxAandB get data
	@Override
	public ArrayList<ArrayList<String>> Search_appxAandB_auth_civ(String month, String year,String version, String unit_sus_no, String status) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct  COALESCE (auth.group_a_gaz,'0') as gp_a_gazetted,\r\n"
					+ "  COALESCE (auth.group_b_gaz,'0') as gp_b_gazetted,\r\n"
					+ "COALESCE (auth.group_b_non_gaz_non_ind,'0') as gp_b_non_gazetted_non_ind,\r\n"
					+ "  COALESCE (auth.group_c_non_gaz_non_ind,'0') as gp_c_non_gazetted_non_ind,\r\n"
					+ "  COALESCE (auth.group_b_non_gaz_ind,'0') as gp_b_non_gazetted_ind,\r\n"
					+ "  COALESCE (auth.group_c_non_gaz_ind,'0') as gp_c_non_gazetted_ind,\r\n"
					+ " COALESCE(SUM(auth.group_a_gaz +auth.group_b_gaz + auth.group_b_non_gaz_non_ind + auth.group_c_non_gaz_non_ind + auth.group_b_non_gaz_ind\r\n"
					+ "	 + auth.group_c_non_gaz_ind),0) AS total \r\n"
					+ "from tb_psg_appx_a_b_civ_main as p \r\n"
					+ "inner join tb_psg_auth_appx_a_b_civ as auth \r\n"
					+ "on p.sus_no = auth.sus_no and p.year = auth.year and p.version=auth.version \r\n"
					+ "where auth.sus_no = ?  and auth.year = ? and auth.month = ? and auth.version = ? and auth.status=?\r\n"
					+ "group by gp_a_gazetted,gp_b_gazetted,gp_b_non_gazetted_non_ind,gp_c_non_gazetted_non_ind,gp_b_non_gazetted_ind,gp_c_non_gazetted_ind";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, unit_sus_no);
			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, version);
			stmt.setInt(5, Integer.parseInt(status));

			int i = 0;

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("gp_a_gazetted"));// 0
				list.add(rs.getString("gp_b_gazetted"));// 1
				list.add(rs.getString("gp_b_non_gazetted_non_ind"));// 2
				list.add(rs.getString("gp_c_non_gazetted_non_ind"));// 3
				list.add(rs.getString("gp_b_non_gazetted_ind"));// 4
				list.add(rs.getString("gp_c_non_gazetted_ind"));// 5
				list.add(rs.getString("total"));// 5

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

	@Override
	public Map<String, String> Search_appxAandB_summary_civ(String month, String year, String version, String unit_sus_no,String status) {
		Map<String, String> dataMap = new HashMap<>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct civ_auth as auth_total,civ_posted as held_total,civ_sur as sur,civ_defi as defi\r\n"
					+ "					from  tb_psg_appx_a_b_civ_main as p \r\n"
					+ "					inner join tb_psg_summary_appx_a_b_civ as sm\r\n"
					+ "					on p.sus_no = sm.sus_no and p.year = sm.year and p.version=sm.version \r\n"
					+ "					where sm.sus_no = ? and sm.year = ? and sm.month = ? and sm.version = ? and sm.status=?";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, unit_sus_no);
			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, version);
			stmt.setInt(5, Integer.parseInt(status));

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				dataMap.put("held_total", rs.getString("held_total"));
				dataMap.put("auth_total", rs.getString("auth_total"));
				dataMap.put("defi", rs.getString("defi"));
				dataMap.put("sur", rs.getString("sur"));

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
		return dataMap;
	}



	@Override
	public Map<String, String> Search_appxAandB_sectionB_civ(String month, String year, String version, String unit_sus_no,String status) {
		Map<String, String> dataMap = new HashMap<>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select  distinct *\r\n"
					+ "					from  tb_psg_appx_a_b_civ_main as p \r\n"
					+ "					inner join tb_psg_reg_est_appx_a_b_civ as sm\r\n"
					+ "					on p.sus_no = sm.sus_no and p.year = sm.year and p.version=sm.version \r\n"
					+ "					where sm.sus_no = ?  and sm.year = ? and sm.month = ? and sm.version = ? and sm.status=?";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, unit_sus_no);
			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, version);
			stmt.setInt(5, Integer.parseInt(status));

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				dataMap.put("a_gaz_man_ind", rs.getString("a_gaz_man_ind"));
				dataMap.put("a_gaz_female_ind", rs.getString("a_gaz_female_ind"));
				dataMap.put("a_gaz_total_ind", rs.getString("a_gaz_total_ind"));
				dataMap.put("a_gaz_man_nonind", rs.getString("a_gaz_man_nonind"));
				dataMap.put("a_gaz_female_nonind", rs.getString("a_gaz_female_nonind"));
				dataMap.put("a_gaz_total_nonind", rs.getString("a_gaz_total_nonind"));
				dataMap.put("b_gaz_man_ind", rs.getString("b_gaz_man_ind"));
				dataMap.put("b_gaz_female_ind", rs.getString("b_gaz_female_ind"));
				dataMap.put("b_gaz_total_ind", rs.getString("b_gaz_total_ind"));
				dataMap.put("b_gaz_man_nonind", rs.getString("b_gaz_man_nonind"));
				dataMap.put("b_gaz_female_nonind", rs.getString("b_gaz_female_nonind"));
				dataMap.put("b_gaz_total_nonind", rs.getString("b_gaz_total_nonind"));
				dataMap.put("b_nongaz_m_man_ind", rs.getString("b_nongaz_m_man_ind"));
				dataMap.put("b_nongaz_m_female_ind", rs.getString("b_nongaz_m_female_ind"));
				dataMap.put("b_nongaz_m_total_ind", rs.getString("b_nongaz_m_total_ind"));
				dataMap.put("b_nongaz_m_man_nonind", rs.getString("b_nongaz_m_man_nonind"));
				dataMap.put("b_nongaz_m_female_nonind", rs.getString("b_nongaz_m_female_nonind"));
				dataMap.put("b_nongaz_m_total_nonind", rs.getString("b_nongaz_m_total_nonind"));
				dataMap.put("c_nongaz_m_man_ind", rs.getString("c_nongaz_m_man_ind"));
				dataMap.put("c_nongaz_m_female_ind", rs.getString("c_nongaz_m_female_ind"));
				dataMap.put("c_nongaz_m_total_ind", rs.getString("c_nongaz_m_total_ind"));
				dataMap.put("c_nongaz_m_man_nonind", rs.getString("c_nongaz_m_man_nonind"));
				dataMap.put("c_nongaz_m_female_nonind", rs.getString("c_nongaz_m_female_nonind"));
				dataMap.put("c_nongaz_m_total_nonind", rs.getString("c_nongaz_m_total_nonind"));
				dataMap.put("b_nongaz_e_man_ind", rs.getString("b_nongaz_e_man_ind"));
				dataMap.put("b_nongaz_e_female_ind", rs.getString("b_nongaz_e_female_ind"));
				dataMap.put("b_nongaz_e_total_ind", rs.getString("b_nongaz_e_total_ind"));
				dataMap.put("b_nongaz_e_man_nonind", rs.getString("b_nongaz_e_man_nonind"));
				dataMap.put("b_nongaz_e_female_nonind", rs.getString("b_nongaz_e_female_nonind"));
				dataMap.put("b_nongaz_e_total_nonind", rs.getString("b_nongaz_e_total_nonind"));
				dataMap.put("c_nongaz_e_man_ind", rs.getString("c_nongaz_e_man_ind"));
				dataMap.put("c_nongaz_e_female_ind", rs.getString("c_nongaz_e_female_ind"));
				dataMap.put("c_nongaz_e_total_ind", rs.getString("c_nongaz_e_total_ind"));
				dataMap.put("c_nongaz_e_man_nonind", rs.getString("c_nongaz_e_man_nonind"));
				dataMap.put("c_nongaz_e_femalenonind", rs.getString("c_nongaz_e_femalenonind"));
				dataMap.put("c_nongaz_e_totalnonind", rs.getString("c_nongaz_e_totalnonind"));
				dataMap.put("b_nongaz_t_man_ind", rs.getString("b_nongaz_t_man_ind"));
				dataMap.put("b_nongaz_t_female_ind", rs.getString("b_nongaz_t_female_ind"));
				dataMap.put("b_nongaz_t_total_ind", rs.getString("b_nongaz_t_total_ind"));
				dataMap.put("b_nongaz_t_man_nonind", rs.getString("b_nongaz_t_man_nonind"));
				dataMap.put("b_nongaz_t_female_nonind", rs.getString("b_nongaz_t_female_nonind"));
				dataMap.put("b_nongaz_t_total_nonind", rs.getString("b_nongaz_t_total_nonind"));
				dataMap.put("c_nongaz_t_man_ind", rs.getString("c_nongaz_t_man_ind"));
				dataMap.put("c_nongaz_t_female_ind", rs.getString("c_nongaz_t_female_ind"));
				dataMap.put("c_nongaz_t_total_ind", rs.getString("c_nongaz_t_total_ind"));
				dataMap.put("c_nongaz_t_man_nonind", rs.getString("c_nongaz_t_man_nonind"));
				dataMap.put("c_nongaz_t_female_nonind", rs.getString("c_nongaz_t_female_nonind"));
				dataMap.put("c_nongaz_t_total_nonind", rs.getString("c_nongaz_t_total_nonind"));
				dataMap.put("c_nongaz_o_man_ind", rs.getString("c_nongaz_o_man_ind"));
				dataMap.put("c_nongaz_o_female_ind", rs.getString("c_nongaz_o_female_ind"));
				dataMap.put("c_nongaz_o_total_ind", rs.getString("c_nongaz_o_total_ind"));
				dataMap.put("c_nongaz_o_man_nonind_nonind",
						rs.getString("c_nongaz_o_man_nonind_nonind"));
				dataMap.put("c_nongaz_o_female_nonind_nonind",
						rs.getString("c_nongaz_o_female_nonind_nonind"));
				dataMap.put("c_nongaz_o_total_nonind_nonind",
						rs.getString("c_nongaz_o_total_nonind_nonind"));
				dataMap.put("c_nongaz_s_man_ind", rs.getString("c_nongaz_s_man_ind"));
				dataMap.put("c_nongaz_s_female_ind", rs.getString("c_nongaz_s_female_ind"));
				dataMap.put("c_nongaz_s_total_ind", rs.getString("c_nongaz_s_total_ind"));
				dataMap.put("c_nongaz_s_man_nonind", rs.getString("c_nongaz_s_man_nonind"));
				dataMap.put("c_nongaz_s_female_nonind", rs.getString("c_nongaz_s_female_nonind"));
				dataMap.put("c_nongaz_s_total_nonind", rs.getString("c_nongaz_s_total_nonind"));
				dataMap.put("c_nongaz_other_man_ind", rs.getString("c_nongaz_other_man_ind"));
				dataMap.put("c_nongaz_other_female_ind", rs.getString("c_nongaz_other_female_ind"));
				dataMap.put("c_nongaz_other_total_ind", rs.getString("c_nongaz_other_total_ind"));
				dataMap.put("c_nongaz_other_man_nonind", rs.getString("c_nongaz_other_man_nonind"));
				dataMap.put("c_nongaz_other_female_nonind",
						rs.getString("c_nongaz_other_female_nonind"));
				dataMap.put("c_nongaz_other_total_nonind", rs.getString("c_nongaz_other_total_nonind"));
				dataMap.put("a_auth_gaz", rs.getString("a_auth_gaz"));
				dataMap.put("b_auth_gaz", rs.getString("b_auth_gaz"));
				dataMap.put("b_non_gaz_auth", rs.getString("b_non_gaz_auth"));
				dataMap.put("c_non_gaz_auth", rs.getString("c_non_gaz_auth"));

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
		return dataMap;
	}


	@Override
	public Map<String, String> Search_appxAandB_sectionC_civ(String month, String year, String version, String unit_sus_no,String status) {
		Map<String, String> dataMap = new HashMap<>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct * \r\n"
					+ "					from  tb_psg_appx_a_b_civ_main as p \r\n"
					+ "					inner join tb_psg_non_reg_est_appx_a_b_civ as sm\r\n"
					+ "					on p.sus_no = sm.sus_no and p.year = sm.year and p.version=sm.version \r\n"
					+ "					where sm.sus_no = ?  and sm.year = ? and sm.month = ? and sm.version = ? and sm.status=?";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, unit_sus_no);
			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, version);
			stmt.setInt(5,Integer.parseInt(status));

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				dataMap.put("c_nonreg_c_man_ind", rs.getString("c_nonreg_c_man_ind"));
				dataMap.put("c_nonreg_c_female_ind", rs.getString("c_nonreg_c_female_ind"));
				dataMap.put("c_nonreg_c_total_ind", rs.getString("c_nonreg_c_total_ind"));
				dataMap.put("c_nonreg_c_man_nonind", rs.getString("c_nonreg_c_man_nonind"));
				dataMap.put("c_nonreg_c_female_nonind", rs.getString("c_nonreg_c_female_nonind"));
				dataMap.put("c_nonreg_c_total_nonind", rs.getString("c_nonreg_c_total_nonind"));
				dataMap.put("c_nonreg_p_man_ind", rs.getString("c_nonreg_p_man_ind"));
				dataMap.put("c_nonreg_p_female_ind", rs.getString("c_nonreg_p_female_ind"));
				dataMap.put("c_nonreg_p_total_ind", rs.getString("c_nonreg_p_total_ind"));
				dataMap.put("c_nonreg_p_man_nonind", rs.getString("c_nonreg_p_man_nonind"));
				dataMap.put("c_nonreg_p_female_nonind", rs.getString("c_nonreg_p_female_nonind"));
				dataMap.put("c_nonreg_p_total_nonind", rs.getString("c_nonreg_p_total_nonind"));
				dataMap.put("c_nonreg_r_man_ind", rs.getString("c_nonreg_r_man_ind"));
				dataMap.put("c_nonreg_r_female_ind", rs.getString("c_nonreg_r_female_ind"));
				dataMap.put("c_nonreg_r_total_ind", rs.getString("c_nonreg_r_total_ind"));
				dataMap.put("c_nonreg_r_man_nonind", rs.getString("c_nonreg_r_man_nonind"));
				dataMap.put("c_nonreg_r_female_nonind", rs.getString("c_nonreg_r_female_nonind"));
				dataMap.put("c_nonreg_r_total_nonind", rs.getString("c_nonreg_r_total_nonind"));
				dataMap.put("c_nonreg_other_man_ind", rs.getString("c_nonreg_other_man_ind"));
				dataMap.put("c_nonreg_other_female_ind", rs.getString("c_nonreg_other_female_ind"));
				dataMap.put("c_nonreg_other_total_ind", rs.getString("c_nonreg_other_total_ind"));
				dataMap.put("c_nonreg_other_man_nonind", rs.getString("c_nonreg_other_man_nonind"));
				dataMap.put("c_nonreg_other_female_nonind", rs.getString("c_nonreg_other_female_nonind"));
				dataMap.put("c_nonreg_other_total_nonind", rs.getString("c_nonreg_other_total_nonind"));

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
		return dataMap;
	}


	@Override
	public String Search_appxAandB_observation_civ(String month, String year, String version, String unit_sus_no,String status) {
		Connection conn = null;
		String observation = "";
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct observation \r\n"
					+ "from tb_psg_appx_a_b_civ_main as p \r\n"
					+ "inner join tb_psg_appx_a_b_civ_main_details as auth \r\n"
					+ "on p.sus_no = auth.sus_no and p.year = auth.year and p.version=auth.version \r\n"
					+ "where auth.sus_no = ?  and auth.year = ? and auth.month = ? and auth.version = ? and auth.status=?\r\n";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, unit_sus_no);
			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, version);
			stmt.setInt(5,Integer.parseInt(status));

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				observation = rs.getString("observation"); // Get observation value
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return observation;
	}



	// ---- end AppxAandB get data



	// ----------------delete -------------------------------------//
	@Override
	public Boolean Delete_Report_appxAandB_civ(String username, String roleSusNo, String month, String year, String version)
			throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = dataSource.getConnection();
		try {
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(Delete_appxAandB_civ_Version());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Delete_appxAandB_civ_Main_Details());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Delete_auth_appxAandB_civ());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();


			pstmt = conn.prepareStatement(Delete_reg_est_appxAandB_civ());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();


			pstmt = conn.prepareStatement(Delete_non_reg_est_appxAandB_civ());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Delete_summary_appxAandB_civ());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();


			pstmt.close();
			conn.commit();
			conn.close();
		} catch (Exception e1) {
			conn.rollback();
			return false;
		}
		return true;
	}

	public String Delete_appxAandB_civ_Version() {
		return "update tb_psg_appx_a_b_civ_main set status='3', deleted_by= ?, deleted_date= now()::timestamp \r\n"
				+ " where  sus_no=? and month=? and year=?  and version=? ";
	}

	public String Delete_appxAandB_civ_Main_Details() {
		return "update tb_psg_appx_a_b_civ_main_details set status=3, deleted_by= ?, deleted_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_auth_appxAandB_civ () {
		return "update tb_psg_auth_appx_a_b_civ set status=3, deleted_by= ?, deleted_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_reg_est_appxAandB_civ () {
		return "update tb_psg_reg_est_appx_a_b_civ set status=3, deleted_by= ?, deleted_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_non_reg_est_appxAandB_civ () {
		return "update tb_psg_non_reg_est_appx_a_b_civ set status=3, deleted_by= ?, deleted_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_summary_appxAandB_civ () {
		return "update tb_psg_summary_appx_a_b_civ set status=3, deleted_by= ?, deleted_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}



	// -----------------end delete-------------------------------//



	// ----------------Approve Functionality -------------------------------------//
	@Override
	public Boolean approve_Report_appxAandB_civ(String username, String roleSusNo, String month, String year, String version)
			throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = dataSource.getConnection();
		try {
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(approve_appxAandB_civ_Version());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(approve_appxAandB_civ_Main_Details());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(approve_auth_appxAandB_civ());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();


			pstmt = conn.prepareStatement(approve_reg_est_appxAandB_civ());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();


			pstmt = conn.prepareStatement(approve_non_reg_est_appxAandB_civ());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(approve_summary_appxAandB_civ());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();


			pstmt.close();
			conn.commit();
			conn.close();
		} catch (Exception e1) {
			conn.rollback();
			return false;
		}
		return true;
	}

	public String approve_appxAandB_civ_Version() {
		return "update tb_psg_appx_a_b_civ_main set status='1', approved_by= ?, approved_date= now()::timestamp \r\n"
				+ " where  sus_no=? and month=? and year=?  and version=? ";
	}

	public String approve_appxAandB_civ_Main_Details() {
		return "update tb_psg_appx_a_b_civ_main_details set status=1, approved_by= ?, approved_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String approve_auth_appxAandB_civ () {
		return "update tb_psg_auth_appx_a_b_civ set status=1, approved_by= ?, approved_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String approve_reg_est_appxAandB_civ () {
		return "update tb_psg_reg_est_appx_a_b_civ set status=1, approved_by= ?, approved_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String approve_non_reg_est_appxAandB_civ () {
		return "update tb_psg_non_reg_est_appx_a_b_civ set status=1, approved_by= ?, approved_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String approve_summary_appxAandB_civ () {
		return "update tb_psg_summary_appx_a_b_civ set status=1, approved_by= ?, approved_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}


	// -----------------end Approve------------------------------//





	@Override
	public ArrayList<ArrayList<String>> getappxAandBList(String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no,String line_dte_sus) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry="";


			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and substr(b.form_code_control,1,1) =substr(?,1,1) ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and substr(b.form_code_control,2,2) = substr(?,2,2) ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and substr(b.form_code_control,4,3) =substr(?,4,3) ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and substr(b.form_code_control,7,4) =substr(?,7,4) ";
			}
			if (!unit_sus_no.equals("")) {
				qry += " and b.sus_no = ?  ";
			}
			if(!line_dte_sus.equals("") && !line_dte_sus.equals("0")) {
				qry += " and b.arm_code in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus =?)  ";
			}


			q = " SELECT distinct \r\n"
					+ "	b.sus_no,\r\n"
					+ "	b.unit_name,\r\n"
					+ "   COALESCE(\r\n"
					+ "        (SELECT a.status \r\n"
					+ "         FROM tb_psg_appx_a_b_civ_main a \r\n"
					+ "         WHERE a.sus_no = b.sus_no \r\n"
					+ "           AND a.month =?\r\n"
					+ "           AND a.year = ?\r\n"
					+ "        ),\r\n"
					+ "        -1\r\n"
					+ "    ) AS status,\r\n"
					+ " COALESCE((select a.version from tb_psg_appx_a_b_civ_main a where a.sus_no = b.sus_no 	AND a.month=? AND a.year=?),'0') as version\r\n"
					+ "	\r\n"
					+ "FROM\r\n"
					+ "	tb_miso_orbat_unt_dtl b\r\n"
					+ "	inner join tb_miso_orbat_codesform c\r\n"
					+ "	on c.sus_no = b.sus_no\r\n"
					+ "	and level_in_hierarchy = 'Unit'\r\n"
					+ "WHERE\r\n"
					+ "	b.status_sus_no='Active' "+qry+" ";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, month);
			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, year);

			if (!qry.equals("")) {

				int j = 5;

				if (!comd_sus.equals("0") && !comd_sus.equals("")) {
					stmt.setString(j, comd_sus);
					j += 1;
				}
				if (!corp_sus.equals("0") && !corp_sus.equals("")) {
					stmt.setString(j, corp_sus);
					j += 1;
				}
				if (!div_sus.equals("0") && !div_sus.equals("")) {
					stmt.setString(j, div_sus);
					j += 1;
				}
				if (!bde_sus.equals("0") && !bde_sus.equals("")) {
					stmt.setString(j, bde_sus);
					j += 1;
				}
				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}

				if(!line_dte_sus.equals("") && !line_dte_sus.equals("0")) {
					stmt.setString(j, line_dte_sus);
					j += 1;
				}

			}



			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(month);// 2
				list.add(year);// 3
				list.add(rs.getString("version"));// 4

				int status=Integer.parseInt(rs.getString("status"));
				String f1 = "";
				String f4 = "";
				String value="";
				if(status== -1) {
					value="NOT YET UPDT";
				}else if(status == 0) {
					value="PENDING";
				}else if(status ==1) {

					String View = "onclick=\"  if (confirm('Are You Sure You Want to View This ?') ){editData("
							+ month + ",'" + year + "' ,'" + rs.getString("version")
							+ "' ,'" + rs.getString("sus_no") + "','"+ status +"')}else{ return false;}\"";
					f4 = "<i class='fa fa-eye'  " + View + " title='Approve/View Data'></i>";



					String Printreport = "onclick=\"  if (confirm('Are You Sure You Want to Download This Report ?') ){downloaddata("
							+ month+ ",'" + year + "' ,'" + rs.getString("version")
							+ "' ,'" + rs.getString("sus_no") + "')}else{ return false;}\"";
					f1 = "<i class='fa fa-download'  " + Printreport + " title='Download Report'></i>";

					value = f4 + f1 ;

				}
				list.add(value);
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

	//================================== end  A AND B start==========================================



	//			APPX A AND B

	@Override
	public HashMap<String,ArrayList<ArrayList<String>>> pdf_AppxABReport(String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no, String rank, String version) {
		HashMap<String,ArrayList<ArrayList<String>>> reportData = new HashMap<>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			// SECTION A Data Query (Replace with actual query based on your data structure)
			ArrayList<ArrayList<String>> sectionAData = fetchSectionAData(conn,month,year,comd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,rank,version);
			reportData.put("SECTION_A", sectionAData);

			// SECTION B Data Query (Replace with actual query based on your data structure)
			ArrayList<ArrayList<String>> sectionBData = fetchSectionBData(conn,month,year,comd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,rank,version);
			reportData.put("SECTION_B", sectionBData);
			// SECTION C Data Query (Replace with actual query based on your data structure)
			ArrayList<ArrayList<String>> sectionCData = fetchSectionCData(conn,month,year,comd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,rank,version);
			reportData.put("SECTION_C", sectionCData);

			// SECTION D Data Query (Replace with actual query based on your data structure)
			ArrayList<ArrayList<String>> sectionDData = fetchSectionDData(conn,month,year,comd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,rank,version);
			reportData.put("SECTION_D", sectionDData);

			ArrayList<ArrayList<String>> signData = fetchSignData(conn,month,year,comd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,rank);
			reportData.put("SECTION_S", signData);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return reportData;
	}


	//HERE
	private  ArrayList<ArrayList<String>> fetchSectionAData(Connection conn,String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no, String rank, String version) throws SQLException {
		ArrayList<ArrayList<String>> alist = new ArrayList<>();
		String q1 ="";
		if (!unit_sus_no.equals("")) {
			q1 += "and ah.sus_no=? ";
		}

		if (!month.equals("")) {
			q1 += "and ah.month=? ";
		}

		if (!year.equals("")) {
			q1 += "and ah.year=? ";
		}

		if (!comd_sus.equals("0") && !comd_sus.equals("")) {
			q1 += " and ah.cmd_sus = ? ";
		}
		if (!corp_sus.equals("0") && !corp_sus.equals("")) {
			q1 += " and ah.corp_sus = ? ";
		}
		if (!div_sus.equals("0") && !div_sus.equals("")) {
			q1 += " and ah.div_sus = ? ";
		}
		if (!bde_sus.equals("0") && !bde_sus.equals("")) {
			q1 += " and ah.bde_sus = ? ";
		}
		//		if (!rank.equals("")) {
		//			qry += " and upper(np.rank) = ? ";
		//		}

		String q = "select distinct group_a_gaz, group_b_gaz, group_b_non_gaz_Non_ind, group_c_non_gaz_Non_ind, group_b_non_gaz_ind, group_c_non_gaz_ind, total	   \r\n"
				+ " from tb_psg_auth_appx_a_b_civ a \r\n"
				+ "	inner join tb_psg_appx_a_b_civ_main_details ah ON  a.sus_no = ah.sus_no  and a.year =ah.year and a.month =ah.month and a.version = ah.version \r\n"
				+ "	where a.status = '1' and a.version = ? " + q1 ;
		PreparedStatement stmt = conn.prepareStatement(q);

		int j = 1;

		stmt.setString(j, version);
		j += 1;

		if (!unit_sus_no.equals("")) {
			stmt.setString(j, unit_sus_no);
			j += 1;
		}
		if (!month.equals("0")) {
			stmt.setString(j, month);
			j += 1;
		}
		if (year != "") {
			stmt.setString(j, year);
			j += 1;
		}
		if (!comd_sus.equals("0") && !comd_sus.equals("")) {
			stmt.setString(j, comd_sus);
			j += 1;
		}
		if (!corp_sus.equals("0") && !corp_sus.equals("")) {
			stmt.setString(j, corp_sus);
			j += 1;
		}
		if (!div_sus.equals("0") && !div_sus.equals("")) {
			stmt.setString(j, div_sus);
			j += 1;
		}
		if (!bde_sus.equals("0") && !bde_sus.equals("")) {
			stmt.setString(j, bde_sus);
			j += 1;
		}

		//		if (!rank.equals("")) {
		//			stmt.setString(j, rank.toUpperCase());
		//			// j += 1;
		//		}

		ResultSet rs = stmt.executeQuery();
		System.out.println("SECTION A --> " + stmt);
		while (rs.next()) {
			ArrayList<String> list = new ArrayList<>();
			list.add(rs.getString("group_a_gaz"));// 0
			list.add(rs.getString("group_b_gaz"));// 1
			list.add(rs.getString("group_b_non_gaz_Non_ind"));// 2
			list.add(rs.getString("group_c_non_gaz_Non_ind"));// 3
			list.add(rs.getString("group_b_non_gaz_ind"));// 4
			list.add(rs.getString("group_c_non_gaz_ind"));// 5
			list.add(rs.getString("total"));//6
			alist.add(list);
		}
		rs.close();
		stmt.close();
		return alist;
	}

	private ArrayList<ArrayList<String>> fetchSectionBData(Connection conn,String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no, String rank, String version) throws SQLException {
		ArrayList<ArrayList<String>> alist = new ArrayList<>();
		String q1 ="";
		if (!unit_sus_no.equals("")) {
			q1 += "and ah.sus_no=? ";
		}

		if (!month.equals("")) {
			q1 += "and ah.month=? ";
		}

		if (!year.equals("")) {
			q1 += "and ah.year=? ";
		}

		if (!comd_sus.equals("0") && !comd_sus.equals("")) {
			q1 += " and ah.cmd_sus = ? ";
		}
		if (!corp_sus.equals("0") && !corp_sus.equals("")) {
			q1 += " and ah.corp_sus = ? ";
		}
		if (!div_sus.equals("0") && !div_sus.equals("")) {
			q1 += " and ah.div_sus = ? ";
		}
		if (!bde_sus.equals("0") && !bde_sus.equals("")) {
			q1 += " and ah.bde_sus = ? ";
		}

		String q = "SELECT DISTINCT\r\n"
				+ "				     a.sus_no,\r\n"
				+ "				     -- GAZ A Industrial\r\n"
				+ "				     a_gaz_man_ind, a_gaz_female_ind, a_gaz_total_ind,\r\n"
				+ "				     -- GAZ A Non-Industrial\r\n"
				+ "				     a_gaz_man_nonind, a_gaz_female_nonind, a_gaz_total_nonind,\r\n"
				+ "				     -- GAZ B Industrial\r\n"
				+ "					 b_gaz_man_ind, b_gaz_female_ind, b_gaz_total_ind,\r\n"
				+ "				     -- GAZ B Non-Industrial\r\n"
				+ "					 b_gaz_man_nonind, b_gaz_female_nonind, b_gaz_total_nonind,\r\n"
				+ "				     -- non gaz\r\n"
				+ "				     -- Ministerial B Industrial\r\n"
				+ "					 b_nongaz_m_man_ind, b_nongaz_m_female_ind, b_nongaz_m_total_ind,\r\n"
				+ "				     -- Ministerial B Non-Industrial\r\n"
				+ "					 b_nongaz_m_man_nonind, b_nongaz_m_female_nonind, b_nongaz_m_total_nonind,\r\n"
				+ "				     -- Ministerial C Industrial\r\n"
				+ "					 c_nongaz_m_man_ind, c_nongaz_m_female_ind, c_nongaz_m_total_ind,\r\n"
				+ "				     -- Ministerial Non-Industrial\r\n"
				+ "					 c_nongaz_m_man_nonind, c_nongaz_m_female_nonind, c_nongaz_m_total_nonind,\r\n"
				+ "				     -- Executive B Industrial\r\n"
				+ "					 b_nongaz_e_man_ind, b_nongaz_e_female_ind, b_nongaz_e_total_ind,\r\n"
				+ "				     -- Executive B Non-Industrial\r\n"
				+ "					 b_nongaz_e_man_nonind, b_nongaz_e_female_nonind, b_nongaz_e_total_nonind,\r\n"
				+ "				     -- Executive C Industrial\r\n"
				+ "					 c_nongaz_e_man_ind, c_nongaz_e_female_ind, c_nongaz_e_total_ind,\r\n"
				+ "				     -- Executive B Non-Industrial\r\n"
				+ "					 c_nongaz_e_man_nonind, c_nongaz_e_femalenonind, c_nongaz_e_totalnonind,\r\n"
				+ "				     -- Technical B Industrial\r\n"
				+ "					 b_nongaz_t_man_ind, b_nongaz_t_female_ind, b_nongaz_t_total_ind,\r\n"
				+ "				     -- Technical B Non-Industrial\r\n"
				+ "					 b_nongaz_t_man_nonind, b_nongaz_t_female_nonind, b_nongaz_t_total_nonind,\r\n"
				+ "				     -- Technical C Industrial\r\n"
				+ "					 c_nongaz_t_man_ind, c_nongaz_t_female_ind, c_nongaz_t_total_ind,\r\n"
				+ "				     -- Technical C Non-Industrial\r\n"
				+ "					 c_nongaz_t_man_nonind, c_nongaz_t_female_nonind, c_nongaz_t_total_nonind,\r\n"
				+ "				     --nongaz ncsu\r\n"
				+ "				     -- Office Worker C Industrial\r\n"
				+ "					 c_nongaz_o_man_ind, c_nongaz_o_female_ind, c_nongaz_o_total_ind,\r\n"
				+ "				     -- Office Worker C Non-Industrial\r\n"
				+ "					 c_nongaz_o_man_nonind_nonind, c_nongaz_o_female_nonind_nonind, c_nongaz_o_total_nonind_nonind,\r\n"
				+ "				     -- Semi-Skilled C Industrial\r\n"
				+ "					 c_nongaz_s_man_ind, c_nongaz_s_female_ind, c_nongaz_s_total_ind,\r\n"
				+ "				     -- Semi-Skilled C Non-Industrial \r\n"
				+ "					 c_nongaz_s_man_nonind, c_nongaz_s_female_nonind, c_nongaz_s_total_nonind,\r\n"
				+ "				     -- Other C Industrial\r\n"
				+ "					 c_nongaz_other_man_ind, c_nongaz_other_female_ind, c_nongaz_other_total_ind,\r\n"
				+ "				     -- Other C Non-Industrial\r\n"
				+ "					 c_nongaz_other_man_nonind, c_nongaz_other_female_nonind, c_nongaz_other_total_nonind,\r\n"
				+ "				 	\r\n"
				+ "				 	--Auth Columns\r\n"
				+ "					 a_auth_gaz, b_auth_gaz, b_non_gaz_auth, c_non_gaz_auth \r\n"
				+ "				 	\r\n"
				+ "				 FROM\r\n"
				+ "				     tb_psg_reg_est_appx_a_b_civ a\r\n"
				+ "				 INNER JOIN\r\n"
				+ "				     tb_psg_appx_a_b_civ_main_details ah ON  a.sus_no = ah.sus_no  and a.year =ah.year and a.month =ah.month and a.version = ah.version \r\n"
				+ "				 		where a.status = '1' and a.version = ? " + q1 ;

		PreparedStatement stmt = conn.prepareStatement(q);

		int j = 1;

		stmt.setString(j, version);
		j += 1;

		if (!unit_sus_no.equals("")) {
			stmt.setString(j, unit_sus_no);
			j += 1;
		}
		if (!month.equals("0")) {
			stmt.setString(j, month);
			j += 1;
		}
		if (year != "") {
			stmt.setString(j, year);
			j += 1;
		}
		if (!comd_sus.equals("0") && !comd_sus.equals("")) {
			stmt.setString(j, comd_sus);
			j += 1;
		}
		if (!corp_sus.equals("0") && !corp_sus.equals("")) {
			stmt.setString(j, corp_sus);
			j += 1;
		}
		if (!div_sus.equals("0") && !div_sus.equals("")) {
			stmt.setString(j, div_sus);
			j += 1;
		}
		if (!bde_sus.equals("0") && !bde_sus.equals("")) {
			stmt.setString(j, bde_sus);
			j += 1;
		}

		ResultSet rs = stmt.executeQuery();
		System.out.println("SECTION B --> " + stmt);
		while (rs.next()) {
			ArrayList<String> list = new ArrayList<>();
			list.add(rs.getString("a_gaz_man_ind"));
			list.add(rs.getString("a_gaz_female_ind"));
			list.add(rs.getString("a_gaz_total_ind"));
			list.add(rs.getString("a_gaz_man_nonind"));
			list.add(rs.getString("a_gaz_female_nonind"));
			list.add(rs.getString("a_gaz_total_nonind"));
			list.add(rs.getString("b_gaz_man_ind"));
			list.add(rs.getString("b_gaz_female_ind"));
			list.add(rs.getString("b_gaz_total_ind"));
			list.add(rs.getString("b_gaz_man_nonind"));
			list.add(rs.getString("b_gaz_female_nonind"));
			list.add(rs.getString("b_gaz_total_nonind"));
			list.add(rs.getString("b_nongaz_m_man_ind"));
			list.add(rs.getString("b_nongaz_m_female_ind"));
			list.add(rs.getString("b_nongaz_m_total_ind"));
			list.add(rs.getString("b_nongaz_m_man_nonind"));
			list.add(rs.getString("b_nongaz_m_female_nonind"));
			list.add(rs.getString("b_nongaz_m_total_nonind"));
			list.add(rs.getString("c_nongaz_m_man_ind"));
			list.add(rs.getString("c_nongaz_m_female_ind"));
			list.add(rs.getString("c_nongaz_m_total_ind"));
			list.add(rs.getString("c_nongaz_m_man_nonind"));
			list.add(rs.getString("c_nongaz_m_female_nonind"));
			list.add(rs.getString("c_nongaz_m_total_nonind"));
			list.add(rs.getString("b_nongaz_e_man_ind"));
			list.add(rs.getString("b_nongaz_e_female_ind"));
			list.add(rs.getString("b_nongaz_e_total_ind"));
			list.add(rs.getString("b_nongaz_e_man_nonind"));
			list.add(rs.getString("b_nongaz_e_female_nonind"));
			list.add(rs.getString("b_nongaz_e_total_nonind"));
			list.add(rs.getString("c_nongaz_e_man_ind"));
			list.add(rs.getString("c_nongaz_e_female_ind"));
			list.add(rs.getString("c_nongaz_e_total_ind"));
			list.add(rs.getString("c_nongaz_e_man_nonind"));
			list.add(rs.getString("c_nongaz_e_femalenonind"));
			list.add(rs.getString("c_nongaz_e_totalnonind"));
			list.add(rs.getString("b_nongaz_t_man_ind"));
			list.add(rs.getString("b_nongaz_t_female_ind"));
			list.add(rs.getString("b_nongaz_t_total_ind"));
			list.add(rs.getString("b_nongaz_t_man_nonind"));
			list.add(rs.getString("b_nongaz_t_female_nonind"));
			list.add(rs.getString("b_nongaz_t_total_nonind"));
			list.add(rs.getString("c_nongaz_t_man_ind"));
			list.add(rs.getString("c_nongaz_t_female_ind"));
			list.add(rs.getString("c_nongaz_t_total_ind"));
			list.add(rs.getString("c_nongaz_t_man_nonind"));
			list.add(rs.getString("c_nongaz_t_female_nonind"));
			list.add(rs.getString("c_nongaz_t_total_nonind"));
			list.add(rs.getString("c_nongaz_o_man_ind"));
			list.add(rs.getString("c_nongaz_o_female_ind"));
			list.add(rs.getString("c_nongaz_o_total_ind"));
			list.add(rs.getString("c_nongaz_o_man_nonind_nonind"));
			list.add(rs.getString("c_nongaz_o_female_nonind_nonind"));
			list.add(rs.getString("c_nongaz_o_total_nonind_nonind"));
			list.add(rs.getString("c_nongaz_s_man_ind"));
			list.add(rs.getString("c_nongaz_s_female_ind"));
			list.add(rs.getString("c_nongaz_s_total_ind"));
			list.add(rs.getString("c_nongaz_s_man_nonind"));
			list.add(rs.getString("c_nongaz_s_female_nonind"));
			list.add(rs.getString("c_nongaz_s_total_nonind"));
			list.add(rs.getString("c_nongaz_other_man_ind"));
			list.add(rs.getString("c_nongaz_other_female_ind"));
			list.add(rs.getString("c_nongaz_other_total_ind"));
			list.add(rs.getString("c_nongaz_other_man_nonind"));
			list.add(rs.getString("c_nongaz_other_female_nonind"));
			list.add(rs.getString("c_nongaz_other_total_nonind"));
			list.add(rs.getString("a_auth_gaz"));
			list.add(rs.getString("b_auth_gaz"));
			list.add(rs.getString("b_non_gaz_auth"));
			list.add(rs.getString("c_non_gaz_auth"));
			alist.add(list);
		}
		rs.close();
		stmt.close();
		return alist;
	}

	private  ArrayList<ArrayList<String>> fetchSectionCData(Connection conn,String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no, String rank, String version) throws SQLException {
		ArrayList<ArrayList<String>> alist = new ArrayList<>();
		String q1 ="";
		if (!unit_sus_no.equals("")) {
			q1 += "and ah.sus_no=? ";
		}

		if (!month.equals("")) {
			q1 += "and ah.month=? ";
		}

		if (!year.equals("")) {
			q1 += "and ah.year=? ";
		}

		if (!comd_sus.equals("0") && !comd_sus.equals("")) {
			q1 += " and ah.cmd_sus = ? ";
		}
		if (!corp_sus.equals("0") && !corp_sus.equals("")) {
			q1 += " and ah.corp_sus = ? ";
		}
		if (!div_sus.equals("0") && !div_sus.equals("")) {
			q1 += " and ah.div_sus = ? ";
		}
		if (!bde_sus.equals("0") && !bde_sus.equals("")) {
			q1 += " and ah.bde_sus = ? ";
		}

		String q = "SELECT DISTINCT\r\n"
				+ "				     a.sus_no,   \r\n"
				+ "				     -- PERSON PAID FROM CONTINGENCIES Industrial\r\n"
				+ "					 c_nonreg_c_man_ind, c_nonreg_c_female_ind, c_nonreg_c_total_ind,\r\n"
				+ "				     -- PERSON PAID FROM CONTINGENCIES Non-Industrial\r\n"
				+ "					 c_nonreg_c_man_nonind, c_nonreg_c_female_nonind, c_nonreg_c_total_nonind,\r\n"
				+ "				 		\r\n"
				+ "				     --WORK CHARGES PERSONNEL Industrial\r\n"
				+ "					 c_nonreg_p_man_ind, c_nonreg_p_female_ind, c_nonreg_p_total_ind,\r\n"
				+ "				     --WORK CHARGES PERSONNEL Non-Industrial\r\n"
				+ "					 c_nonreg_p_man_nonind, c_nonreg_p_female_nonind, c_nonreg_p_total_nonind,\r\n"
				+ "				 		\r\n"
				+ "				     -- PERSON PAID FROM REGULAR PAY HEAD Industrial\r\n"
				+ "					 c_nonreg_r_man_ind, c_nonreg_r_female_ind, c_nonreg_r_total_ind,\r\n"
				+ "				     -- PERSON PAID FROM REGULAR PAY HEAD Non-Industrial\r\n"
				+ "					 c_nonreg_r_man_nonind, c_nonreg_r_female_nonind, c_nonreg_r_total_nonind,\r\n"
				+ "				 \r\n"
				+ "				 \r\n"
				+ "				 		  -- otehr Industrial\r\n"
				+ "						   c_nonreg_other_man_ind, c_nonreg_other_female_ind, c_nonreg_other_total_ind,\r\n"
				+ "				     -- otehr Non-Industrial\r\n"
				+ "					 c_nonreg_other_man_nonind, c_nonreg_other_female_nonind, c_nonreg_other_total_nonind\r\n"
				+ "				 	\r\n"
				+ "				 	\r\n"
				+ "				 FROM\r\n"
				+ "				     tb_psg_non_reg_est_appx_a_b_civ a\r\n"
				+ "				 INNER JOIN\r\n"
				+ "				     tb_psg_appx_a_b_civ_main_details ah ON  a.sus_no = ah.sus_no  and a.year =ah.year and a.month =ah.month and a.version = ah.version \r\n"
				+ "				 		where a.status = '1' and a.version = ? " + q1 ;

		PreparedStatement stmt = conn.prepareStatement(q);

		int j = 1;

		stmt.setString(j, version);
		j += 1;

		if (!unit_sus_no.equals("")) {
			stmt.setString(j, unit_sus_no);
			j += 1;
		}
		if (!month.equals("0")) {
			stmt.setString(j, month);
			j += 1;
		}
		if (year != "") {
			stmt.setString(j, year);
			j += 1;
		}
		if (!comd_sus.equals("0") && !comd_sus.equals("")) {
			stmt.setString(j, comd_sus);
			j += 1;
		}
		if (!corp_sus.equals("0") && !corp_sus.equals("")) {
			stmt.setString(j, corp_sus);
			j += 1;
		}
		if (!div_sus.equals("0") && !div_sus.equals("")) {
			stmt.setString(j, div_sus);
			j += 1;
		}
		if (!bde_sus.equals("0") && !bde_sus.equals("")) {
			stmt.setString(j, bde_sus);
			j += 1;
		}

		ResultSet rs = stmt.executeQuery();
		System.out.println("SECTION C --> " + stmt);
		while (rs.next()) {
			ArrayList<String> list = new ArrayList<>();
			list.add(rs.getString("c_nonreg_c_man_ind"));
			list.add(rs.getString("c_nonreg_c_female_ind"));
			list.add(rs.getString("c_nonreg_c_total_ind"));
			list.add(rs.getString("c_nonreg_c_man_nonind"));
			list.add(rs.getString("c_nonreg_c_female_nonind"));
			list.add(rs.getString("c_nonreg_c_total_nonind"));
			list.add(rs.getString("c_nonreg_p_man_ind"));
			list.add(rs.getString("c_nonreg_p_female_ind"));
			list.add(rs.getString("c_nonreg_p_total_ind"));
			list.add(rs.getString("c_nonreg_p_man_nonind"));
			list.add(rs.getString("c_nonreg_p_female_nonind"));
			list.add(rs.getString("c_nonreg_p_total_nonind"));
			list.add(rs.getString("c_nonreg_r_man_ind"));
			list.add(rs.getString("c_nonreg_r_female_ind"));
			list.add(rs.getString("c_nonreg_r_total_ind"));
			list.add(rs.getString("c_nonreg_r_man_nonind"));
			list.add(rs.getString("c_nonreg_r_female_nonind"));
			list.add(rs.getString("c_nonreg_r_total_nonind"));
			list.add(rs.getString("c_nonreg_other_man_ind"));
			list.add(rs.getString("c_nonreg_other_female_ind"));
			list.add(rs.getString("c_nonreg_other_total_ind"));
			list.add(rs.getString("c_nonreg_other_man_nonind"));
			list.add(rs.getString("c_nonreg_other_female_nonind"));
			list.add(rs.getString("c_nonreg_other_total_nonind"));
			alist.add(list);
		}
		rs.close();
		stmt.close();
		return alist;
	}

	private  ArrayList<ArrayList<String>> fetchSectionDData(Connection conn,String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no, String rank, String version) throws SQLException {
		ArrayList<ArrayList<String>> alist = new ArrayList<>();

		String q1 ="";
		if (!unit_sus_no.equals("")) {
			q1 += "and ah.sus_no=? ";
		}

		if (!month.equals("")) {
			q1 += "and ah.month=? ";
		}

		if (!year.equals("")) {
			q1 += "and ah.year=? ";
		}

		if (!comd_sus.equals("0") && !comd_sus.equals("")) {
			q1 += " and ah.cmd_sus = ? ";
		}
		if (!corp_sus.equals("0") && !corp_sus.equals("")) {
			q1 += " and ah.corp_sus = ? ";
		}
		if (!div_sus.equals("0") && !div_sus.equals("")) {
			q1 += " and ah.div_sus = ? ";
		}
		if (!bde_sus.equals("0") && !bde_sus.equals("")) {
			q1 += " and ah.bde_sus = ? ";
		}

		String q = "select distinct a.civ_auth as auth_total, a.civ_posted as held_total, a.civ_defi as defi, a.civ_sur as sur\r\n"
				+ "				 FROM\r\n"
				+ "				 	tb_psg_summary_appx_a_b_civ a\r\n"
				+ "				 	INNER JOIN "
				+ "				     tb_psg_appx_a_b_civ_main_details ah ON  a.sus_no = ah.sus_no  and a.year =ah.year and a.month =ah.month and a.version = ah.version \r\n"
				+ "				 		where a.status = '1' and a.version = ? " + q1 ;

		PreparedStatement stmt = conn.prepareStatement(q);

		int j = 1;

		stmt.setString(j, version);
		j += 1;

		if (!unit_sus_no.equals("")) {
			stmt.setString(j, unit_sus_no);
			j += 1;
		}
		if (!month.equals("0")) {
			stmt.setString(j, month);
			j += 1;
		}
		if (year != "") {
			stmt.setString(j, year);
			j += 1;
		}
		if (!comd_sus.equals("0") && !comd_sus.equals("")) {
			stmt.setString(j, comd_sus);
			j += 1;
		}
		if (!corp_sus.equals("0") && !corp_sus.equals("")) {
			stmt.setString(j, corp_sus);
			j += 1;
		}
		if (!div_sus.equals("0") && !div_sus.equals("")) {
			stmt.setString(j, div_sus);
			j += 1;
		}
		if (!bde_sus.equals("0") && !bde_sus.equals("")) {
			stmt.setString(j, bde_sus);
			j += 1;
		}


		ResultSet rs = stmt.executeQuery();
		System.out.println("SECTION D --> " + stmt);
		while (rs.next()) {
			ArrayList<String> list = new ArrayList<>();
			list.add(rs.getString("auth_total"));
			list.add(rs.getString("held_total"));
			list.add(rs.getString("sur"));
			list.add(rs.getString("defi"));
			alist.add(list);
		}
		rs.close();
		stmt.close();
		return alist;
	}




	private  ArrayList<ArrayList<String>> fetchSignData(Connection conn,String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no, String rank) throws SQLException {
		ArrayList<ArrayList<String>> alist = new ArrayList<>();
		String q1 ="";
		String q = "select distinct\r\n"
				+ "fv.unit_name as cmd_unit,\r\n"
				+ "fvm.unit_name as corp_unit,\r\n"
				+ "div.unit_name as div_unit,\r\n"
				+ "bde.unit_name as bde_unit,\r\n"
				+ " orb.unit_name as unit_name \r\n"
				+ "from tb_psg_trans_proposed_comm_letter comm\r\n"
				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no\r\n"
				+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no \r\n"
				+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
				+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no \r\n"
				+ "and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n"
				+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no \r\n"
				+ "and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n"
				+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no \r\n"
				+ "and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n"
				+ "WHERE orb.sus_no = ?";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setString(1, unit_sus_no);

		ResultSet rs = stmt.executeQuery();
		System.out.println("SECTION S --> " + stmt);
		while (rs.next()) {
			ArrayList<String> list = new ArrayList<>();
			list.add(rs.getString("cmd_unit"));
			list.add(rs.getString("corp_unit"));
			list.add(rs.getString("div_unit"));
			list.add(rs.getString("bde_unit"));
			list.add(rs.getString("unit_name"));
			alist.add(list);
		}
		rs.close();
		stmt.close();
		return alist;
	}
}
