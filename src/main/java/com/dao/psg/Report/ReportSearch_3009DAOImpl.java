package com.dao.psg.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.models.psg.Report.TB_IAFF_3009_MAIN;
import com.models.psg.Report.TB_PSG_IAFF_3008_MAIN;
import com.models.psg.Report.TB_PSG_IAFF_3009_MAIN;
import com.persistance.util.HibernateUtil;

public class ReportSearch_3009DAOImpl implements ReportSearch_3009DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	private Report_3008DAO report_3008DAO;

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

	public PreparedStatement setQueryWhereClause_SQLheld_off_jco(PreparedStatement stmt, String Search,
			String roleAccess, String roleSusNo, String sus_no, String FDate, String LDate) {

		int flag = 0;

		try {

			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
				stmt.setString(2, LDate);
				stmt.setString(3, roleSusNo);
				stmt.setString(4, LDate);
				stmt.setString(5, roleSusNo);
				stmt.setString(6, LDate);

			}
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, LDate);
				stmt.setString(3, sus_no);
				stmt.setString(4, LDate);
				stmt.setString(5, sus_no);
				stmt.setString(6, LDate);
			}

			flag = 2;
			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "") {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	// ------------------------ section 2 poste str civilian
	// employee-----------------------------------------//
	public long getpostciv3009CountList(String Search, String orderColunm, String orderType, HttpSession sessionUserId,
			String sus_no, String FDate, String LDate) throws SQLException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(select \r\n"
					+ "COALESCE(n3.gp_a_gaz,'0') as gp_a_gaz,COALESCE(n3.gp_b_gaz,'0') as gp_b_gaz,\r\n"
					+ "COALESCE(n3.gp_b_non_gaz,'0') as gp_b_non_gaz,COALESCE(n3.gp_c_non_gaz,'0') as gp_c_non_gaz\r\n"
					+ "from \r\n"
					+ "(select  count (*) filter (where civ.civ_group='A' and civ.classification_services = '1') as gp_a_gaz, \r\n"
					+ "	count (*) filter (where civ.civ_group= 'B' and civ.classification_services = '1') as gp_b_gaz,  \r\n"
					+ "	count (*) filter (where civ.civ_group='B' and civ.classification_services = '2') as gp_b_non_gaz, \r\n"
					+ "	count (*) filter (where civ.civ_group='C' and civ.classification_services = '2' ) as gp_c_non_gaz \r\n"
					+ "	from tb_psg_civilian_dtl_main civ\r\n"
					+ "	inner join tb_miso_orbat_unt_dtl sub on civ.sus_no = sub.sus_no  and  sub.status_sus_no = 'Active' \r\n"
					+ "	\r\n" + "	where \r\n"
					+ "	 civ.status = '1' and civ.sus_No = ? and to_date(to_char(civ.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))n3    \r\n"
					+ "   " + SearchValue + " ) app";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQLciv(stmt, Search, roleAccess, roleSusNo, sus_no, FDate, LDate);

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
		return (long) total;
	}

	public List<Map<String, Object>> getpostciv3009(int startPage, int pageLength, String Search, String orderColunm,
			String orderType, HttpSession sessionUserId, String sus_no, String FDate, String LDate)
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

			q = "\r\n" + "select \r\n"
					+ "COALESCE(n3.gp_a_gaz,'0') as gp_a_gaz,COALESCE(n3.gp_b_gaz,'0') as gp_b_gaz,\r\n"
					+ "COALESCE(n3.gp_b_non_gaz,'0') as gp_b_non_gaz,COALESCE(n3.gp_c_non_gaz,'0') as gp_c_non_gaz\r\n"
					+ "from \r\n"
					+ "(select  count (*) filter (where civ.civ_group='A' and civ.classification_services = '1') as gp_a_gaz, \r\n"
					+ "	count (*) filter (where civ.civ_group= 'B' and civ.classification_services = '1') as gp_b_gaz,  \r\n"
					+ "	count (*) filter (where civ.civ_group='B' and civ.classification_services = '2') as gp_b_non_gaz, \r\n"
					+ "	count (*) filter (where civ.civ_group='C' and civ.classification_services = '2' ) as gp_c_non_gaz \r\n"
					+ "	from tb_psg_civilian_dtl_main civ\r\n"
					+ "	inner join tb_miso_orbat_unt_dtl sub on civ.sus_no = sub.sus_no and  sub.status_sus_no = 'Active'\r\n"
					+ "	\r\n" + "	where \r\n"
					+ "	 civ.status = '1' and civ.sus_No = ?  and to_date(to_char(civ.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') "
					+ "  limit  " + pageL + " OFFSET " + startPage + " )n3  " + SearchValue;
			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQLciv(stmt, Search, roleAccess, roleSusNo, sus_no, FDate, LDate);
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

	// ----------------------------section 1 auth str civilian
	// employee-------------------------------//

	public long getauthciv3009CountList(String Search, String orderColunm, String orderType, HttpSession sessionUserId,
			String sus_no) throws SQLException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(\r\n" + "select \r\n" + "COALESCE (auth.gp_a_gaz,'0') as gp_a_gaz,\r\n"
					+ "COALESCE (auth.gp_b_gaz,'0') as gp_b_gaz,\r\n"
					+ "COALESCE (auth.gp_b_non_gaz,'0') as gp_b_non_gaz,\r\n"
					+ "COALESCE (auth.gp_c_non_gaz,'0') as gp_c_non_gaz\r\n" + "from ( select \r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '4') as gp_a_gaz,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '5') as gp_b_gaz,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ( '6','9')) as gp_b_non_gaz,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ( '7','8','10','11','12')) as gp_c_non_gaz\r\n"
					+ "from sus_pers_stdauth a "
					+ " inner join   tb_psg_civilian_dtl_main p on p.sus_no = a.sus_no left join t_domain_value c on c.codevalue = a.cat_id and c.domainid = 'PERSON_CAT'\r\n"
					+ " where   a.sus_No = ? and a.cat_id in ('1','2')\r\n" + " ) auth     " + SearchValue + " ) app";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, roleAccess, roleSusNo, sus_no);

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
		return (long) total;
	}

	public List<Map<String, Object>> getauthciv3009(int startPage, int pageLength, String Search, String orderColunm,
			String orderType, HttpSession sessionUserId, String sus_no) throws SQLException {

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

			q = "\r\n" + "select \r\n" + "COALESCE (auth.gp_a_gaz,'0') as gp_a_gaz,\r\n"
					+ "COALESCE (auth.gp_b_gaz,'0') as gp_b_gaz,\r\n"
					+ "COALESCE (auth.gp_b_non_gaz,'0') as gp_b_non_gaz,\r\n"
					+ "COALESCE (auth.gp_c_non_gaz,'0') as gp_c_non_gaz\r\n" + "from ( select \r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '4') as gp_a_gaz,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '5') as gp_b_gaz,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ( '6','9')) as gp_b_non_gaz,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ( '7','8','10','11','12')) as gp_c_non_gaz\r\n"
					+ "from sus_pers_stdauth a "
					+ "  left join t_domain_value c on c.codevalue = a.cat_id and c.domainid = 'PERSON_CAT'\r\n"
					+ "where a.sus_No = ?  and a.cat_id in ('1','2')" + "  limit  " + pageL + " OFFSET " + startPage
					+ " ) auth  " + SearchValue;

			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, roleAccess, roleSusNo, sus_no);
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

	// ---------------- section 1 auth str ------------------------------------//
	public List<Map<String, Object>> getauthdetails3009(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String sus_no) throws SQLException {

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

			q = "select auth.sus_no,auth.arm_desc,\r\n"
					+ "COALESCE (auth.offr,'0') as offr,\r\n"
					+ "COALESCE (auth.mns_off,'0') as mns_off,\r\n"
					+ "COALESCE (auth.jco,'0') as jco,\r\n"
					+ "COALESCE (auth.ors,'0') as ors \r\n"
					+ "from (\r\n"
					+ "select a.sus_no, a.cat_id, c.label as per_category, a.arm, b.arm_desc, \r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='0') as offr,\r\n"
					+ "0 as mns_off,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='1') as jco,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ('2','3')) as ors \r\n"
					+ "from sus_pers_stdauth a \r\n"
					+ "left join tb_miso_orbat_arm_code b on b.arm_code = a.arm\r\n"
					+ "left join t_domain_value c on c.codevalue = a.cat_id and c.domainid = 'PERSON_CAT'\r\n"
					+ "where a.sus_No = ? and  b.arm_desc !='MILITARY NURSING SERVICE [MNS]' \r\n"
					+ "AND a.cat_id in ('1','2') \r\n"
					+ "group by  a.sus_no,  a.cat_id, c.label, a.arm, b.arm_desc\r\n"
					+ "\r\n"
					+ "union all\r\n"
					+ "\r\n"
					+ "select a.sus_no, a.cat_id, c.label as per_category, a.arm, b.arm_desc, \r\n"
					+ "0 as offr,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='0') as mns_off,\r\n"
					+ "0 as jco,\r\n"
					+ "0 as ors \r\n"
					+ "from sus_pers_stdauth a \r\n"
					+ "left join tb_miso_orbat_arm_code b on b.arm_code = a.arm\r\n"
					+ "left join t_domain_value c on c.codevalue = a.cat_id and c.domainid = 'PERSON_CAT'\r\n"
					+ "where a.sus_No = ? and  b.arm_desc ='MILITARY NURSING SERVICE [MNS]' \r\n"
					+ "AND a.cat_id in ('1','2') \r\n"
					+ "group by  a.sus_no,  a.cat_id, c.label, a.arm, b.arm_desc\r\n"
					+ "order by 1  " + " asc limit  "
					+ pageL + " OFFSET " + startPage + " ) auth  " + SearchValue;

			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQLtrad(stmt, Search, roleAccess, roleSusNo, sus_no);

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

	public String GenerateQueryWhereClause_SQL(String Search) {
		String SearchValue = "";
		if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "") { // for Input Filter
			SearchValue += " where upper(subq.appt) like ? or upper(subq.rank) like ? or  upper(subq.name) like ?  or upper(subq.personnel_no) like ? or upper(subq.cda_acc_no) like ?"
					+ " or upper(subq.parent_arm) like ? or upper(subq.med_cat) like ? or upper(subq.dt_of_tos) like ? or upper(subq.date_of_birth) like ? or upper(subq.date_of_commission) like ?"
					+ " or upper(subq.date_of_seniority) like ? or upper(subq.date_of_appointment) like ? ";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String roleAccess,
			String roleSusNo, String sus_no) {

		int flag = 0;

		try {

			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
				stmt.setString(1, sus_no);

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

	public PreparedStatement setQueryWhereClause_SQLrel(PreparedStatement stmt, String Search, String roleAccess,
			String roleSusNo, String sus_no, String FDate, String LDate) {

		int flag = 0;

		try {

			if (roleAccess.equals("Unit")) {
				stmt.setString(1, LDate);
				stmt.setString(2, roleSusNo);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
				stmt.setString(1, LDate);
				stmt.setString(2, sus_no);

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

	public PreparedStatement setQueryWhereClause_SQLheld(PreparedStatement stmt, String Search, String roleAccess,
			String roleSusNo, String sus_no, String FDate, String LDate) {

		int flag = 0;

		try {

			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
				stmt.setString(2, roleSusNo);
				stmt.setString(3, roleSusNo);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, sus_no);
				stmt.setString(3, sus_no);
			}

			flag = 3;
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

	public PreparedStatement setQueryWhereClause_SQLtrad(PreparedStatement stmt, String Search, String roleAccess,
			String roleSusNo, String sus_no) {

		int flag = 0;

		try {

			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
				stmt.setString(2, roleSusNo);
				stmt.setString(2, roleSusNo);

			}
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, sus_no);

			}

			flag = 2;
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

	public long getauthdetails3009CountList(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String sus_no) throws SQLException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(select auth.sus_no,auth.arm_desc,\r\n"
					+ "COALESCE (auth.offr,'0') as offr,\r\n" + "COALESCE (auth.jco,'0') as jco,\r\n"
					+ "COALESCE (auth.ors,'0') as ors\r\n"
					+ "from (select a.sus_no, a.cat_id, c.label as per_category, a.arm, b.arm_desc, \r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='0') as offr,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='1') as jco,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ('2','3')) as ors \r\n"
					+ "from sus_pers_stdauth a \r\n" + "left join tb_miso_orbat_arm_code b on b.arm_code = a.arm\r\n"
					+ "left join t_domain_value c on c.codevalue = a.cat_id and c.domainid = 'PERSON_CAT'\r\n"
					+ "where a.sus_No = ? \r\n" + "AND a.cat_id = '2'\r\n"
					+ "group by  a.sus_no,  a.cat_id, c.label, a.arm, b.arm_desc\r\n" + "\r\n" + "UNION ALL\r\n"
					+ "select a.sus_no, a.cat_id, c.label as per_category, a.arm, b.arm_desc, \r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='0') as offr,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='1') as jco,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ('2','3')) as ors \r\n"
					+ "from sus_pers_stdauth a \r\n" + "left join tb_miso_orbat_arm_code b on b.arm_code = a.arm\r\n"
					+ "left join t_domain_value c on c.codevalue = a.cat_id and c.domainid = 'PERSON_CAT'\r\n"
					+ "where a.sus_No = ? \r\n" + "AND a.cat_id = '1'\r\n"
					+ "group by  a.sus_no,  a.cat_id, c.label, a.arm, b.arm_desc" + ") auth  " + SearchValue + " ) app";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQLtrad(stmt, Search, roleAccess, roleSusNo, sus_no);

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
		return (long) total;
	}

	//////////////////////////////////////// 2/////////////////////////////////////////////////\

	public long getpostedServing3009CountList(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String sus_no, String FDate, String LDate) throws SQLException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(select n0.arm_desc,COALESCE(n1.brig_above,'0') as brig_above ,COALESCE(n1.col,'0') as col ,COALESCE(n1.col_ts ,'0') as col_ts, COALESCE(n1.ltcol,'0') as ltcol ,\r\n"
					+ "COALESCE(n1.maj,'0') as maj,COALESCE(n1.capt_lt,'0') as capt_lt,COALESCE(mns.brig_above_mns,'0') as brig_above_mns ,COALESCE(mns.col_mns,'0') as col_mns ,COALESCE(mns.ltcol_s_mns ,'0') as ltcol_s_mns, COALESCE(mns.ltcol_ts_mns,'0') as ltcol_ts_mns ,\r\n"
					+ "COALESCE(mns.maj_mns,'0') as maj_mns,COALESCE(mns.capt_lt_mns,'0') as capt_lt_mns,  \r\n"
					+ "COALESCE(n2.sub_maj,'0') as sub_maj,COALESCE(n2.sub,'0') as sub,COALESCE(n2.nb_sub,'0') as nb_sub,COALESCE(n2.warrant_off,'0') as warrant_off,COALESCE(n2.hav,'0') as hav,\r\n"
					+ "COALESCE(n2.nk,'0') as nk,COALESCE(n2.sep,'0') as sep,COALESCE(n2.rect,'0') as rect\r\n"
					+ "from\r\n" + "tb_miso_orbat_arm_code n0 left join \r\n" + "(\r\n"
					+ "select e.arm_code,c.unit_sus_no as sus_no,\r\n"
					+ "		count(*) filter (where r.description IN ('GEN','LT GEN','MAJ GEN','BRIG')) as brig_above,\r\n"
					+ "		count(*) filter (where r.description = 'COL') as col,\r\n"
					+ "		count(*) filter (where r.description = 'COL [TS]') as col_ts,\r\n"
					+ "		count(*) filter (where r.description = 'LT COL') as ltcol,\r\n"
					+ "		count(*) filter (where r.description = 'MAJ') as maj,\r\n"
					+ "		count(*) filter (where r.description IN ('CAPT','LT')) as capt_lt\r\n"
					+ "	    from tb_psg_trans_proposed_comm_letter c\r\n"
					+ "		inner join cue_tb_psg_rank_app_master r on c.rank=r.id and c.status in ('1','5') and upper(r.level_in_hierarchy) ='RANK'\r\n"
					+ "		left join tb_miso_orbat_arm_code e on e.arm_code = c.parent_arm where c.unit_sus_no = ? and \r\n "
					+ "   to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')  AND c.personnel_no !~ '^(NR|NS)' \r\n"
					+ "	group by 1 ,2\r\n" + "\r\n" + ") n1 on n0.arm_code=n1.arm_code\r\n" + "left join \r\n"
							+ "(\r\n"
							+ "select e.arm_code,c.unit_sus_no as sus_no,\r\n"
							+ "		count(*) filter (where r.description IN ('GEN','LT GEN','MAJ GEN','BRIG')) as brig_above_mns,\r\n"
							+ "		count(*) filter (where r.description = 'COL') as col_mns,	\r\n"
							+ "		count(*) filter (where r.description = 'LT COL {S}') as ltcol_s_mns,\r\n"
							+ "			count(*) filter (where r.description = 'LT COL {TS}') as ltcol_ts_mns,\r\n"
							+ "		count(*) filter (where r.description = 'MAJ') as maj_mns,\r\n"
							+ "		count(*) filter (where r.description IN ('CAPT','LT')) as capt_lt_mns\r\n"
							+ "	    from tb_psg_trans_proposed_comm_letter c\r\n"
							+ "		inner join cue_tb_psg_rank_app_master r on c.rank=r.id and c.status in ('1','5') and upper(r.level_in_hierarchy) ='RANK'\r\n"
							+ "		left join tb_miso_orbat_arm_code e on e.arm_code = c.parent_arm \r\n"
							+ "         left join (select max(dt_of_tos) as dt_of_tos,comm_id as comm_id from tb_psg_posting_in_out group by comm_id ) tos on tos.comm_id = c.id\r\n"
							+ " where c.unit_sus_no = ?   AND c.personnel_no ~ '^(NR|NS)' and  to_date(to_char(tos.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') \r\n"
							+ "	group by 1 ,2\r\n"
							+ "\r\n"
							+ ") mns on n0.arm_code=mns.arm_code		"
							+ "	left join \r\n"
					+ "		( select  e.arm_code,c.unit_sus_no,\r\n"
					+ "		count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sub_maj,\r\n"
					+ "		count(*) filter (where r.rank = 'SUB / EQUIVALENT') as sub,\r\n"
					+ "		count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub, \r\n"
					+ "       count(*) filter (where r.rank = 'WARRANT OFFICER') as warrant_off,\r\n"
					+ "		count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav,	 \r\n"
					+ "		count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,	 \r\n"
					+ "		count(*) filter (where r.rank = 'SEP / EQUIVALENT') as SEP,		 \r\n"
					+ "		count(*) filter (where r.rank = 'RECTS') as rect			\r\n"
					+ "	from tb_psg_census_jco_or_p c\r\n"
					+ "	inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status  = '1'\r\n"
					+ "	left join tb_miso_orbat_arm_code e on e.arm_code = c.arm_service where  c.unit_sus_no = ? and \r\n"
					+ "   to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(? ,'YYYY/MM/DD') \r\n "
					+ "   group by 1 ,2\r\n" + "		\r\n" + "		)n2 on n0.arm_code = n2.arm_code\r\n"
					+ "    where  (n1.brig_above IS NOT NULL OR n1.col IS NOT NULL or n1.ltcol IS NOT NULL OR n1.ltcol IS NOT NULL OR n1.maj IS NOT NULL OR\r\n"
					+ "		   n1.capt_lt IS NOT NULL\r\n"
					+ " or mns.brig_above_mns IS NOT NULL OR mns.col_mns IS NOT NULL or mns.ltcol_s_mns IS NOT NULL OR mns.ltcol_ts_mns IS NOT NULL OR mns.maj_mns IS NOT NULL OR\r\n"
					+ "		   mns.capt_lt_mns IS NOT NULL"
					+ " OR n2.sub_maj IS NOT NULL OR n2.sub IS NOT NULL OR n2.nb_sub IS NOT NULL OR n2.warrant_off IS NOT NULL OR\r\n"
					+ "		  n2.hav IS NOT NULL OR n2.nk IS NOT NULL OR n2.sep IS NOT NULL OR n2.rect IS NOT NULL )\r\n"
					+ " order by 1 asc  limit 100 OFFSET 0\r\n" + "	 " + SearchValue + " ) app";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQLheld_off_jco(stmt, Search, roleAccess, roleSusNo, sus_no, FDate, LDate);

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
		return (long) total;
	}

	public List<Map<String, Object>> getpostedServing3009(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String sus_no, String FDate, String LDate)
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

			q = "select n0.arm_desc,COALESCE(n1.brig_above,'0') as brig_above ,COALESCE(n1.col,'0') as col ,COALESCE(n1.col_ts ,'0') as col_ts, COALESCE(n1.ltcol,'0') as ltcol ,\r\n"
					+ "COALESCE(n1.maj,'0') as maj,COALESCE(n1.capt_lt,'0') as capt_lt,"
					+ "COALESCE(mns.brig_above_mns,'0') as brig_above_mns ,\r\n"
					+ "COALESCE(mns.col_mns,'0') as col_mns ,COALESCE(mns.ltcol_s_mns ,'0') as ltcol_s_mns, COALESCE(mns.ltcol_ts_mns,'0') as ltcol_ts_mns ,\r\n"
					+ "COALESCE(mns.maj_mns,'0') as maj_mns,COALESCE(mns.capt_lt_mns,'0') as capt_lt_mns,  \r\n"
					+ "COALESCE(n2.sub_maj,'0') as sub_maj,COALESCE(n2.sub,'0') as sub,COALESCE(n2.nb_sub,'0') as nb_sub,COALESCE(n2.warrant_off,'0') as warrant_off,COALESCE(n2.hav,'0') as hav,\r\n"
					+ "COALESCE(n2.nk,'0') as nk,COALESCE(n2.sep,'0') as sep,COALESCE(n2.rect,'0') as rect\r\n"
					+ "from\r\n" + "tb_miso_orbat_arm_code n0 left join \r\n" + "(\r\n"
					+ "select e.arm_code,c.unit_sus_no as sus_no,\r\n"
					+ "		count(*) filter (where r.description IN ('GEN','LT GEN','MAJ GEN','BRIG')) as brig_above,\r\n"
					+ "		count(*) filter (where r.description = 'COL') as col,\r\n"
					+ "		count(*) filter (where r.description = 'COL [TS]') as col_ts,\r\n"
					+ "		count(*) filter (where r.description = 'LT COL') as ltcol,\r\n"
					+ "		count(*) filter (where r.description = 'MAJ') as maj,\r\n"
					+ "		count(*) filter (where r.description IN ('CAPT','LT')) as capt_lt\r\n"
					+ "	    from tb_psg_trans_proposed_comm_letter c\r\n"
					+ "		inner join cue_tb_psg_rank_app_master r on c.rank=r.id and c.status in ('1','5') and upper(r.level_in_hierarchy) ='RANK'\r\n"
					+ "		left join tb_miso_orbat_arm_code e on e.arm_code = c.parent_arm \r\n "
					+ "        left join (select max(dt_of_tos) as dt_of_tos,comm_id as comm_id from tb_psg_posting_in_out group by comm_id ) tos on tos.comm_id = c.id\r\n"
					+ " where c.unit_sus_no = ?  AND c.personnel_no !~ '^(NR|NS)' and  to_date(to_char(tos.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') \r\n"
					+ "	group by 1 ,2\r\n" + "\r\n" + ") n1 on n0.arm_code=n1.arm_code\r\n" + "left join \r\n"
							+ "(\r\n"
							+ "select e.arm_code,c.unit_sus_no as sus_no,\r\n"
							+ "		count(*) filter (where r.description IN ('GEN','LT GEN','MAJ GEN','BRIG')) as brig_above_mns,\r\n"
							+ "		count(*) filter (where r.description = 'COL') as col_mns,	\r\n"
							+ "		count(*) filter (where r.description = 'LT COL {S}') as ltcol_s_mns,\r\n"
							+ "			count(*) filter (where r.description = 'LT COL {TS}') as ltcol_ts_mns,\r\n"
							+ "		count(*) filter (where r.description = 'MAJ') as maj_mns,\r\n"
							+ "		count(*) filter (where r.description IN ('CAPT','LT')) as capt_lt_mns\r\n"
							+ "	    from tb_psg_trans_proposed_comm_letter c\r\n"
							+ "		inner join cue_tb_psg_rank_app_master r on c.rank=r.id and c.status in ('1','5') and upper(r.level_in_hierarchy) ='RANK'\r\n"
							+ "		left join tb_miso_orbat_arm_code e on e.arm_code = c.parent_arm \r\n"
							+ "         left join (select max(dt_of_tos) as dt_of_tos,comm_id as comm_id from tb_psg_posting_in_out group by comm_id ) tos on tos.comm_id = c.id\r\n"
							+ " where c.unit_sus_no = ?   AND c.personnel_no ~ '^(NR|NS)' and  to_date(to_char(tos.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') \r\n"
							+ "	group by 1 ,2\r\n"
							+ "\r\n"
							+ ") mns on n0.arm_code=mns.arm_code	"
							+ "	left join \r\n"
					+ "		( select  e.arm_code,c.unit_sus_no,\r\n"
					+ "		count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sub_maj,\r\n"
					+ "		count(*) filter (where r.rank = 'SUB / EQUIVALENT') as sub,\r\n"
					+ "		count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub, \r\n"
					+ "       count(*) filter (where r.rank = 'WARRANT OFFICER') as warrant_off,\r\n"
					+ "		count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav,	 \r\n"
					+ "		count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,	 \r\n"
					+ "		count(*) filter (where r.rank = 'SEP / EQUIVALENT') as SEP,		 \r\n"
					+ "		count(*) filter (where r.rank = 'RECTS') as rect			\r\n"
					+ "	from tb_psg_census_jco_or_p c\r\n"
					+ "	inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status  = '1'\r\n"
					+ "	left join tb_miso_orbat_arm_code e on e.arm_code = c.arm_service where  c.unit_sus_no = ? and \r\n"
					+ " to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
					+ "   group by 1 ,2\r\n" + "		\r\n" + "		)n2 on n0.arm_code = n2.arm_code\r\n"
					+ "    where  (n1.brig_above IS NOT NULL OR n1.col IS NOT NULL or n1.ltcol IS NOT NULL OR n1.ltcol IS NOT NULL OR n1.maj IS NOT NULL OR\r\n"
					+ "		   n1.capt_lt IS NOT NULL \r\n"
					+ " or mns.brig_above_mns IS NOT NULL OR mns.col_mns IS NOT NULL or mns.ltcol_s_mns IS NOT NULL OR mns.ltcol_ts_mns IS NOT NULL OR mns.maj_mns IS NOT NULL OR\r\n"
					+ "		   mns.capt_lt_mns IS NOT NULL"
					+ " OR n2.sub_maj IS NOT NULL OR n2.sub IS NOT NULL OR n2.nb_sub IS NOT NULL OR n2.warrant_off IS NOT NULL OR\r\n"
					+ "		  n2.hav IS NOT NULL OR n2.nk IS NOT NULL OR n2.sep IS NOT NULL OR n2.rect IS NOT NULL )\r\n"
					+ "" + SearchValue + " order by 1 asc  limit " + pageL + " OFFSET " + startPage;

			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQLheld_off_jco(stmt, Search, roleAccess, roleSusNo, sus_no, FDate, LDate);

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

	//////////////////////////////////////// 4/////////////////////////////////////////////////\

	public long gettradeServing3009Count(String Search, String orderColunm, String orderType, HttpSession sessionUserId,
			String sus_no, String FDate, String LDate) throws SQLException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from (select auth.arm_desc, auth.trade,\r\n"
					+ "auth.sub_maj,auth.sub,auth.nb_sub,\r\n"
					+ "auth.warrnt_off,auth.hav,auth.nk,auth.sep,auth.rects,\r\n" + "coalesce(\r\n"
					+ "auth.sub_maj + auth.sub + auth.nb_sub +\r\n"
					+ "auth.warrnt_off + auth.hav + auth.nk + auth.sep + auth.rects)as to\r\n" + "\r\n"
					+ "from (select e.arm_desc,t.trade,\r\n"
					+ "	count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sub_maj,\r\n"
					+ "	count(*) filter (where r.rank = 'SUB / EQUIVALENT') as sub,\r\n"
					+ "	count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub, \r\n"
					+ "	count(*) filter (where r.rank = 'WARRANT OFFICER') as warrnt_off,\r\n"
					+ "	count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav,	 \r\n"
					+ "	count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,	 \r\n"
					+ "	count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep,		 \r\n"
					+ "	count(*) filter (where r.rank = 'RECTS') as rects \r\n"
					+ " 	from tb_psg_census_jco_or_p c\r\n"
					+ "	inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status  = '1'\r\n"
					+ "	left join tb_miso_orbat_arm_code e on e.arm_code = c.arm_service\r\n"
					+ "	left join tb_psg_mstr_trade_jco t on  t.id = c.trade\r\n"
					+ "	where c.unit_sus_no = ?  and to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
					+ "	 " + SearchValue + " 	group by 1,2)auth) app";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQLciv(stmt, Search, roleAccess, roleSusNo, sus_no, FDate, LDate);

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
		return (long) total;
	}

	public List<Map<String, Object>> gettradeServing3009(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String sus_no, String FDate, String LDate)
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

			q = "select auth.arm_desc, auth.trade,\r\n" + "auth.sub_maj,auth.sub,auth.nb_sub,\r\n"
					+ "auth.warrnt_off,auth.hav,auth.nk,auth.sep,auth.rects,\r\n" + "coalesce(\r\n"
					+ "auth.sub_maj + auth.sub + auth.nb_sub +\r\n"
					+ "auth.warrnt_off + auth.hav + auth.nk + auth.sep + auth.rects)as total\r\n" + "\r\n"
					+ "from (select e.arm_desc,t.trade,\r\n"
					+ "	count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sub_maj,\r\n"
					+ "	count(*) filter (where r.rank = 'SUB / EQUIVALENT') as sub,\r\n"
					+ "	count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub, \r\n"
					+ "	count(*) filter (where r.rank = 'WARRANT OFFICER') as warrnt_off,\r\n"
					+ "	count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav,	 \r\n"
					+ "	count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,	 \r\n"
					+ "	count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep,		 \r\n"
					+ "	count(*) filter (where r.rank = 'RECTS') as rects \r\n"
					+ " 	from tb_psg_census_jco_or_p c\r\n"
					+ "	inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status  = '1'\r\n"
					+ "	left join tb_miso_orbat_arm_code e on e.arm_code = c.arm_service\r\n"
					+ "	left join tb_psg_mstr_trade_jco t on  t.id = c.trade\r\n"
					+ "	where c.unit_sus_no = ? and to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
					+ "" + SearchValue + " 	group by 1,2 ) auth order by 1 asc  limit " + pageL + " OFFSET "
					+ startPage;

			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQLciv(stmt, Search, roleAccess, roleSusNo, sus_no, FDate, LDate);

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

////////////////////////////////////////4/////////////////////////////////////////////////\

	public long getreligiousServing3009Count(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String sus_no, String FDate, String LDate) throws SQLException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(select n1.unit_sus_no,n1.arm_desc,n1.religion_name,n1.jcos,n1.ors,n1.held_religious_teacher,COALESCE(n3.auth,'0') as auth from (select  c.unit_sus_no,  c.arm_service , e.arm_desc, c.religion, s.religion_name,\r\n"
					+ "	count(*) filter (where r.rank IN ( 'WARRANT OFFICER','SUB MAJ / EQUIVALENT','SUB / EQUIVALENT','NB SUB / EQUIVALENT')) as jcos,\r\n"
					+ "	count(*) filter (where r.rank IN ( 'HAV / EQUIVALENT','NK / EQUIVALENT','SEP / EQUIVALENT','RECTS')) as ors	, \r\n"
					+ "	count(*)  filter (where c.trade = '100') as held_religious_teacher\r\n"
					+ "	from tb_psg_census_jco_or_p c\r\n"
					+ "	inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status  = '1'\r\n"
					+ "	left join tb_miso_orbat_arm_code e on e.arm_code = c.arm_service\r\n"
					+ "	left join tb_psg_mstr_religion s on s.religion_id = c.religion \r\n"
					+ "where to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')	\r\n"
					+ "	group by 1,2,3,4,5   \r\n" + ") n1 left join  	\r\n"
					+ "(select a.sus_no,  sum(a.base_auth+a.mod_auth+a.foot_auth) AS auth \r\n"
					+ "	from sus_pers_stdauth a  \r\n"
					+ "	left join cue_tb_psg_rank_app_master b on b.code = a.app_trd_code  and a.rank_cat in ('1')\r\n"
					+ "	where  upper(b.level_in_hierarchy) = upper('Appointment') and b.description ilike 'religious%' \r\n"
					+ "	group by 1 )  n3 on n3.sus_no=n1.unit_sus_no \r\n" + " where n1.unit_sus_no = ? \r\n " + "	 "
					+ SearchValue + " ) app";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQLrel(stmt, Search, roleAccess, roleSusNo, sus_no, FDate, LDate);

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
		return (long) total;
	}

	public List<Map<String, Object>> getreligiousServing3009(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String sus_no, String FDate, String LDate)
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

			q = " select n1.unit_sus_no,n1.arm_desc,n1.religion_name,n1.jcos,n1.ors,n1.held_religious_teacher,COALESCE(n3.auth,'0') as auth from (select  c.unit_sus_no,  c.arm_service , e.arm_desc, c.religion, s.religion_name,\r\n"
					+ "	count(*) filter (where r.rank IN ( 'WARRANT OFFICER','SUB MAJ / EQUIVALENT','SUB / EQUIVALENT','NB SUB / EQUIVALENT')) as jcos,\r\n"
					+ "	count(*) filter (where r.rank IN ( 'HAV / EQUIVALENT','NK / EQUIVALENT','SEP / EQUIVALENT','RECTS')) as ors	, \r\n"
					+ "	count(*)  filter (where c.trade = '100') as held_religious_teacher\r\n"
					+ "	from tb_psg_census_jco_or_p c\r\n"
					+ "	inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status  = '1'\r\n"
					+ "	left join tb_miso_orbat_arm_code e on e.arm_code = c.arm_service\r\n"
					+ "	left join tb_psg_mstr_religion s on s.religion_id = c.religion\r\n"
					+ "where to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
					+ "	  group by 1,2,3,4,5   \r\n" + ") n1 left join  	\r\n"
					+ "(select a.sus_no,  sum(a.base_auth+a.mod_auth+a.foot_auth) AS auth \r\n"
					+ "	from sus_pers_stdauth a  \r\n"
					+ "	left join cue_tb_psg_rank_app_master b on b.code = a.app_trd_code  and a.rank_cat in ('1')\r\n"
					+ "	where  upper(b.level_in_hierarchy) = upper('Appointment') and b.description ilike 'religious%' \r\n"
					+ "	group by 1 )  n3 on n3.sus_no=n1.unit_sus_no\r\n" + " where n1.unit_sus_no = ? \r\n " + ""
					+ SearchValue + " order by 1 asc  limit " + pageL + " OFFSET " + startPage;

			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQLrel(stmt, Search, roleAccess, roleSusNo, sus_no, FDate, LDate);
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

	public List<TB_PSG_IAFF_3009_MAIN> Get_3009_VersionData(String month, String year, String roleSusNo) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_IAFF_3009_MAIN where month=:month and year=:year and sus_no=:roleSusNo ";
		Query query = sessionHQL.createQuery(hqlUpdate).setString("month", month).setString("year", year)
				.setString("roleSusNo", roleSusNo);

		@SuppressWarnings("unchecked")
		List<TB_PSG_IAFF_3009_MAIN> list = (List<TB_PSG_IAFF_3009_MAIN>) query.list();
		tx.commit();
		sessionHQL.close();

		return list;
	}
////-------------INSERT 3009

	// @Autowired
	// private Report_Serving_DAO report_3008DAO;

	// @Autowired
	// private Report_3008DAO report_off3008DAO;

	public ArrayList<ArrayList<String>> Search_Report_3009_Auth(String roleSusNo, String FDate, String LDate)

	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select auth.sus_no,auth.arm_desc,\r\n" + "COALESCE (auth.offr,'0') as offr,\r\n"
					+ "COALESCE (auth.jco,'0') as jco,\r\n" + "COALESCE (auth.ors,'0') as ors \r\n"
					+ "from (select a.sus_no, a.cat_id, c.label as per_category, a.arm, b.arm_desc, \r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='0') as offr,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='1') as jco,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ('2','3')) as ors \r\n"
					+ "from sus_pers_stdauth a \r\n" + "left join tb_miso_orbat_arm_code b on b.arm_code = a.arm\r\n"
					+ "left join t_domain_value c on c.codevalue = a.cat_id and c.domainid = 'PERSON_CAT'\r\n"
					+ "where a.sus_No = ? \r\n" + "AND a.cat_id = '2'\r\n"
					+ "group by  a.sus_no,  a.cat_id, c.label, a.arm, b.arm_desc\r\n" + "\r\n" + "UNION ALL\r\n"
					+ "select a.sus_no, a.cat_id, c.label as per_category, a.arm, b.arm_desc, \r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='0') as offr,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='1') as jco,\r\n"
					+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ('2','3')) as ors \r\n"
					+ "from sus_pers_stdauth a \r\n" + "left join tb_miso_orbat_arm_code b on b.arm_code = a.arm\r\n"
					+ "left join t_domain_value c on c.codevalue = a.cat_id and c.domainid = 'PERSON_CAT'\r\n"
					+ "where a.sus_No =  ? \r\n" + "AND a.cat_id = '1'\r\n"
					+ "group by  a.sus_no,  a.cat_id, c.label, a.arm, b.arm_desc\r\n"
					+ "order by 1  asc limit  10 OFFSET 0 ) auth ";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo);
			stmt.setString(2, roleSusNo);

			int i = 0;

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("arm_desc"));
				list.add(rs.getString("offr"));
				list.add(rs.getString("jco"));
				list.add(rs.getString("ors"));
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

///////////////////////////////////////////////////////////////////

	public ArrayList<ArrayList<String>> Search_Report_3009_held(String roleSusNo, String FDate, String LDate)

	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select n0.arm_desc,COALESCE(n1.brig_above,'0') as brig_above ,COALESCE(n1.col,'0') as col ,COALESCE(n1.col_ts ,'0') as col_ts, COALESCE(n1.ltcol,'0') as ltcol ,\r\n"
					+ "COALESCE(n1.maj,'0') as maj,COALESCE(n1.capt_lt,'0') as capt_lt, \r\n"
					+ "COALESCE(n2.sub_maj,'0') as sub_maj,COALESCE(n2.sub,'0') as sub,COALESCE(n2.nb_sub,'0') as nb_sub,COALESCE(n2.warrant_off,'0') as warrant_off,COALESCE(n2.hav,'0') as hav,\r\n"
					+ "COALESCE(n2.nk,'0') as nk,COALESCE(n2.sep,'0') as sep,COALESCE(n2.rect,'0') as rect\r\n"
					+ "from\r\n" + "tb_miso_orbat_arm_code n0 left join \r\n" + "(\r\n"
					+ "select e.arm_code,c.unit_sus_no as sus_no,\r\n"
					+ "		count(*) filter (where r.description IN ('GEN','LT GEN','MAJ GEN','BRIG')) as brig_above,\r\n"
					+ "		count(*) filter (where r.description = 'COL') as col,\r\n"
					+ "		count(*) filter (where r.description = 'COL [TS]') as col_ts,\r\n"
					+ "		count(*) filter (where r.description = 'LT COL') as ltcol,\r\n"
					+ "		count(*) filter (where r.description = 'MAJ') as maj,\r\n"
					+ "		count(*) filter (where r.description IN ('CAPT','LT')) as capt_lt\r\n"
					+ "	    from tb_psg_trans_proposed_comm_letter c\r\n"
					+ "		inner join cue_tb_psg_rank_app_master r on c.rank=r.id and c.status in ('1','5') and upper(r.level_in_hierarchy) ='RANK'\r\n"
					+ "		left join tb_miso_orbat_arm_code e on e.arm_code = c.parent_arm where c.unit_sus_no = ? \r\n"
					+ "	group by 1 ,2\r\n" + "\r\n" + ") n1 on n0.arm_code=n1.arm_code\r\n" + "		left join \r\n"
					+ "		( select  e.arm_code,c.unit_sus_no,\r\n"
					+ "		count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sub_maj,\r\n"
					+ "		count(*) filter (where r.rank = 'SUB / EQUIVALENT') as sub,\r\n"
					+ "		count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub, \r\n"
					+ "       count(*) filter (where r.rank = 'WARRANT OFFICER') as warrant_off,\r\n"
					+ "		count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav,	 \r\n"
					+ "		count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,	 \r\n"
					+ "		count(*) filter (where r.rank = 'SEP / EQUIVALENT') as SEP,		 \r\n"
					+ "		count(*) filter (where r.rank = 'RECTS') as rect			\r\n"
					+ "	from tb_psg_census_jco_or_p c\r\n"
					+ "	inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status  = '1'\r\n"
					+ "	left join tb_miso_orbat_arm_code e on e.arm_code = c.arm_service where  c.unit_sus_no = ? \r\n"
					+ "   group by 1 ,2\r\n" + "		\r\n" + "		)n2 on n0.arm_code = n2.arm_code\r\n"
					+ "    where  (n1.brig_above IS NOT NULL OR n1.col IS NOT NULL or n1.ltcol IS NOT NULL OR n1.ltcol IS NOT NULL OR n1.maj IS NOT NULL OR\r\n"
					+ "		   n1.capt_lt IS NOT NULL OR n2.sub_maj IS NOT NULL OR n2.sub IS NOT NULL OR n2.nb_sub IS NOT NULL OR n2.warrant_off IS NOT NULL OR\r\n"
					+ "		  n2.hav IS NOT NULL OR n2.nk IS NOT NULL OR n2.sep IS NOT NULL OR n2.rect IS NOT NULL )";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo);
			stmt.setString(2, roleSusNo);

			int i = 0;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("arm_desc"));
				list.add(rs.getString("brig_above"));
				list.add(rs.getString("col"));
				list.add(rs.getString("col_ts"));
				list.add(rs.getString("ltcol"));
				list.add(rs.getString("maj"));
				list.add(rs.getString("capt_lt"));
				list.add(rs.getString("sub_maj"));
				list.add(rs.getString("sub"));
				list.add(rs.getString("nb_sub"));
				list.add(rs.getString("warrant_off"));
				list.add(rs.getString("nb_sub"));
				list.add(rs.getString("hav"));
				list.add(rs.getString("nk"));
				list.add(rs.getString("sep"));
				list.add(rs.getString("rect"));

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

	public String Insert_Report_3009_Version() {
		return "INSERT INTO tb_psg_iaff_3009_main \r\n"
				+ "(sus_no,month,year,version,status,created_by,created_date) VALUES (?,?,?,?,?,?,now()::timestamp) ";

	}

	public String Insert_Report_3009_Main_Details() {

		return "INSERT INTO tb_psg_iaff_3009_main_details \r\n"
				+ "(sus_no,month,year,version,cmd_sus,corp_sus,div_sus,bde_sus,auth,held,present_return_no,present_return_dt,we_pe_no,"
				+ "cmd_unit,corp_unit,div_unit,bde_unit,created_by,created_date,remarks,distributionlist,status) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,CAST(? as Date),?,?,?,?,?,?,now()::timestamp,?,?,0) ";

	}

	public String Update_Report_3009_Version() {
		return "update tb_psg_iaff_3009_main set month= ?, year= ?, version= ?,status='0',\r\n"
				+ "modified_by= ?, modified_date= now()::timestamp where id=?";
	}

	public String Insert_Report_3009_auth_off_jco() {
//		return "INSERT INTO tb_psg_auth_str_jco_or_3009 \r\n"
//				+ "(sus_no,month,year,version,arm_services,offrs,mns_offrs,jcos,ors)\r\n"
//				+ "select DISTINCT ?,?,?,?,arm_desc,COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='0'),0) as offrs,0 as mns_offrs, \r\n"
//				+ " COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='1'),0) as jcos, \r\n"
//				+ "COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ('2','3')),0) as ors \r\n"
//				+ "from sus_pers_stdauth a \r\n " + "left join tb_miso_orbat_arm_code b on b.arm_code = a.arm \r\n"
//				+ "left join t_domain_value c on c.codevalue = a.cat_id and c.domainid = 'PERSON_CAT' \r\n"
//				+ "where sus_no =? \r\n" + "group by  a.sus_no,  a.cat_id, c.label, a.arm, b.arm_desc\r\n";

		return "insert into tb_psg_auth_str_jco_or_3009(sus_no,month,year,version,arm_services,offrs,mns_offrs,jcos,ors)\r\n"
				+ "select ?,?,?,?,auth.arm_desc,\r\n"
				+ "COALESCE (auth.offr,'0') as offr,\r\n"
				+ "COALESCE (auth.mns_off,'0') as mns_off,\r\n"
				+ "COALESCE (auth.jco,'0') as jco,\r\n"
				+ "COALESCE (auth.ors,'0') as ors \r\n"
				+ "from (\r\n"
				+ "select a.sus_no, a.cat_id, c.label as per_category, a.arm, b.arm_desc, \r\n"
				+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='0') as offr,\r\n"
				+ "0 as mns_off,\r\n"
				+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='1') as jco,\r\n"
				+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ('2','3')) as ors \r\n"
				+ "from sus_pers_stdauth a \r\n"
				+ "left join tb_miso_orbat_arm_code b on b.arm_code = a.arm\r\n"
				+ "left join t_domain_value c on c.codevalue = a.cat_id and c.domainid = 'PERSON_CAT'\r\n"
				+ "where a.sus_No = ? and  b.arm_desc !='MILITARY NURSING SERVICE [MNS]' \r\n"
				+ "AND a.cat_id in ('1','2') \r\n"
				+ "group by  a.sus_no,  a.cat_id, c.label, a.arm, b.arm_desc\r\n"
				+ "\r\n"
				+ "union all\r\n"
				+ "\r\n"
				+ "select a.sus_no, a.cat_id, c.label as per_category, a.arm, b.arm_desc, \r\n"
				+ "0 as offr,\r\n"
				+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat ='0') as mns_off,\r\n"
				+ "0 as jco,\r\n"
				+ "0 as ors \r\n"
				+ "from sus_pers_stdauth a \r\n"
				+ "left join tb_miso_orbat_arm_code b on b.arm_code = a.arm\r\n"
				+ "left join t_domain_value c on c.codevalue = a.cat_id and c.domainid = 'PERSON_CAT'\r\n"
				+ "where a.sus_No = ? and  b.arm_desc ='MILITARY NURSING SERVICE [MNS]' \r\n"
				+ "AND a.cat_id in ('1','2') \r\n"
				+ "group by  a.sus_no,  a.cat_id, c.label, a.arm, b.arm_desc\r\n"
				+ "order by 1 \r\n"
				+ ") auth   ";

	}

	public String Insert_Report_3009_auth_civ() {
		return "insert into tb_psg_auth_civ_3009(gp_a_gazetted,gp_b_gazetted,gp_b_non_gazetted,gp_c_non_gazetted,sus_no,month,year,version)\r\n"
				+ "select \r\n"
				+ "COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '4'),0) as gp_a_gazetted, \r\n"
				+ "COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '5'),0) as gp_b_gazetted,\r\n"
				+ "COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ( '6','9')),0) as gp_b_non_gazetted, \r\n"
				+ "	COALESCE(sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ( '7','8','10','11','12')),0) as gp_c_non_gazetted,?,?,?,?\r\n"
				+ "		from sus_pers_stdauth a "
				+ "  left join t_domain_value c on c.codevalue = a.cat_id and c.domainid = 'PERSON_CAT'\r\n"
				+ " where   a.sus_No =?   and a.cat_id in ('1','2')\r\n";

	}

	public String Insert_Report_3009_posted_offrs_jco_or() {
//		return "insert into tb_psg_posted_offrs_jco_or_3009 \r\n"
//				+ "(sus_no,month,year,version,arms_services,brig_and_above_offrs,col_offrs,col_ts_offrs,lt_col_offrs,maj_offrs,	\r\n"
//				+ " capt_lt_offrs,brig_and_above_mns_offrs,col_mns_offrs,lt_col_mns_offrs,maj_mns_offrs,capt_lt_mns_offrs,sub_major_jco,sub_jco,nb_sub_jco,warrant_officer_jco,hav_or,nk_or,lnk_sep_or,rects_or) \r\n"
//				+ " select ?,?,?,?,n0.arm_desc,COALESCE(n1.brig_above,'0') as brig_and_above_offrs ,COALESCE(n1.col,'0') as col_offrs ,  \r\n"
//				+ " COALESCE(n1.col_ts ,'0') as col_ts_offrs, COALESCE(n1.ltcol,'0') as lt_col_offrs , \r\n"
//				+ "COALESCE(n1.maj,'0') as maj_offrs,COALESCE(n1.capt_lt,'0') as capt_lt_offrs,0,0,0,0,0,  \r\n"
//				+ "COALESCE(n2.sub_maj,'0') as sub_major_jco,COALESCE(n2.sub,'0') as sub_jco,COALESCE(n2.nb_sub,'0') as nb_sub_jco, \r\n"
//				+ "COALESCE(n2.warrant_off,'0') as warrant_officer_jco,COALESCE(n2.hav,'0') as hav_or, \r\n"
//				+ "COALESCE(n2.nk,'0') as nk_or,COALESCE(n2.sep,'0') as lnk_sep_or,COALESCE(n2.rect,'0') as rects_or  from tb_miso_orbat_arm_code n0 left join  \r\n"
//				+ "(select e.arm_code,c.unit_sus_no as sus_no, \r\n"
//				+ "count(*) filter (where r.description IN ('GEN','LT GEN','MAJ GEN','BRIG')) as brig_above, \r\n"
//				+ "count(*) filter (where r.description = 'COL') as col, \r\n"
//				+ "count(*) filter (where r.description = 'COL [TS]') as col_ts, \r\n"
//				+ "count(*) filter (where r.description = 'LT COL') as ltcol, \r\n"
//				+ "count(*) filter (where r.description = 'MAJ') as maj, \r\n"
//				+ "count(*) filter (where r.description IN ('CAPT','LT')) as capt_lt  \r\n"
//				+ "from tb_psg_trans_proposed_comm_letter c  \r\n"
//				+ "inner join cue_tb_psg_rank_app_master r on c.rank=r.id and c.status in ('1','5') and upper(r.level_in_hierarchy) ='RANK'   \r\n"
//				+ "left join tb_miso_orbat_arm_code e on e.arm_code = c.parent_arm where c.unit_sus_no = ? and  to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') \r\n"
//				+ "group by 1 ,2 ) n1 on n0.arm_code=n1.arm_code	left join  \r\n"
//				+ "( select  e.arm_code,c.unit_sus_no, \r\n"
//				+ "count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sub_maj, \r\n"
//				+ "count(*) filter (where r.rank = 'SUB / EQUIVALENT') as sub, \r\n"
//				+ "count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub, \r\n"
//				+ "count(*) filter (where r.rank = 'WARRANT OFFICER') as warrant_off, \r\n"
//				+ "count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav, \r\n"
//				+ "count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,	 \r\n"
//				+ "count(*) filter (where r.rank = 'SEP / EQUIVALENT') as SEP, \r\n"
//				+ "count(*) filter (where r.rank = 'RECTS') as rect  \r\n" + "from tb_psg_census_jco_or_p c  \r\n"
//				+ "inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status  = '1'  \r\n"
//				+ "left join tb_miso_orbat_arm_code e on e.arm_code = c.arm_service where  c.unit_sus_no = ? and to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')  \r\n"
//				+ "group by 1 ,2)n2 on n0.arm_code = n2.arm_code  \r\n"
//				+ "where  (n1.brig_above IS NOT NULL OR n1.col IS NOT NULL or n1.ltcol IS NOT NULL OR n1.ltcol IS NOT NULL OR n1.maj IS NOT NULL OR  \r\n"
//				+ "n1.capt_lt IS NOT NULL OR n2.sub_maj IS NOT NULL OR n2.sub IS NOT NULL OR n2.nb_sub IS NOT NULL OR n2.warrant_off IS NOT NULL OR  \r\n"
//				+ "n2.hav IS NOT NULL OR n2.nk IS NOT NULL OR n2.sep IS NOT NULL OR n2.rect IS NOT NULL )  \r\n"
//				+ "order by 1 asc  limit 100 OFFSET 0  \r\n";

		return "insert into tb_psg_posted_offrs_jco_or_3009(sus_no,month,year,version,arms_services,brig_and_above_offrs,col_offrs,col_ts_offrs,lt_col_offrs,maj_offrs,\r\n"
				+ "										   capt_lt_offrs,brig_and_above_mns_offrs,col_mns_offrs,lt_col_s_mns_offrs,lt_col_ts_mns_offrs,maj_mns_offrs,capt_lt_mns_offrs,sub_major_jco,sub_jco,nb_sub_jco,warrant_officer_jco,hav_or,nk_or,lnk_sep_or,rects_or)\r\n"
				+ "select ?,?,?,?,n0.arm_desc as arms_services,COALESCE(n1.brig_above,'0') as brig_and_above_offrs ,COALESCE(n1.col,'0') as col_offrs ,COALESCE(n1.col_ts ,'0') as col_ts_offrs, COALESCE(n1.ltcol,'0') as lt_col_offrs ,\r\n"
				+ "COALESCE(n1.maj,'0') as maj_offrs,COALESCE(n1.capt_lt,'0') as capt_lt_offrs, COALESCE(mns.brig_above_mns,'0') as brig_and_above_mns_offrs,COALESCE(mns.col_mns,'0') as col_mns_offrs,"
				+ "COALESCE(mns.ltcol_s_mns ,'0') as lt_col_s_mns_offrs, COALESCE(mns.ltcol_ts_mns,'0') as lt_col_ts_mns_offrs,COALESCE(mns.maj_mns,'0') as maj_mns_offrs,\r\n"
				+ "COALESCE(mns.capt_lt_mns,'0') as capt_lt_mns_offrs,\r\n"
				+ "COALESCE(n2.sub_maj,'0') as sub_major_jco,COALESCE(n2.sub,'0') as sub_jco,COALESCE(n2.nb_sub,'0') as nb_sub_jco,COALESCE(n2.warrant_off,'0') as warrant_officer_jco,COALESCE(n2.hav,'0') as hav_or,\r\n"
				+ "COALESCE(n2.nk,'0') as nk_or,COALESCE(n2.sep,'0') as lnk_sep_or,COALESCE(n2.rect,'0') as rects_or\r\n"
				+ "from\r\n" + "tb_miso_orbat_arm_code n0 left join \r\n" + "(\r\n"
				+ "select e.arm_code,c.unit_sus_no as sus_no,\r\n"
				+ "		count(*) filter (where r.description IN ('GEN','LT GEN','MAJ GEN','BRIG')) as brig_above,\r\n"
				+ "		count(*) filter (where r.description = 'COL') as col,\r\n"
				+ "		count(*) filter (where r.description = 'COL [TS]') as col_ts,\r\n"
				+ "		count(*) filter (where r.description = 'LT COL') as ltcol,\r\n"
				+ "		count(*) filter (where r.description = 'MAJ') as maj,\r\n"
				+ "		count(*) filter (where r.description IN ('CAPT','LT')) as capt_lt\r\n"
				+ "	    from tb_psg_trans_proposed_comm_letter c\r\n"
				+ "		inner join cue_tb_psg_rank_app_master r on c.rank=r.id and c.status in ('1','5') and upper(r.level_in_hierarchy) ='RANK'\r\n"
				+ "		left join tb_miso_orbat_arm_code e on e.arm_code = c.parent_arm "
				+ "       left join (select max(dt_of_tos) as dt_of_tos,comm_id as comm_id from tb_psg_posting_in_out group by comm_id ) tos on tos.comm_id = c.id"
				+ " where c.unit_sus_no = ? AND c.personnel_no !~ '^(NR|NS)' \r\n"
				+ "  and  to_date(to_char(tos.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') \r\n"
				+ "	group by 1 ,2\r\n" + "\r\n" + ") n1 on n0.arm_code=n1.arm_code\r\n" + "	"
						+ "left join \r\n"
						+ "(\r\n"
						+ "select e.arm_code,c.unit_sus_no as sus_no,\r\n"
						+ "		count(*) filter (where r.description IN ('GEN','LT GEN','MAJ GEN','BRIG')) as brig_above_mns,\r\n"
						+ "		count(*) filter (where r.description = 'COL') as col_mns,	\r\n"
						+ "		count(*) filter (where r.description = 'LT COL {S}') as ltcol_s_mns,\r\n"
						+ "			count(*) filter (where r.description = 'LT COL {TS}') as ltcol_ts_mns,\r\n"
						+ "		count(*) filter (where r.description = 'MAJ') as maj_mns,\r\n"
						+ "		count(*) filter (where r.description IN ('CAPT','LT')) as capt_lt_mns\r\n"
						+ "	    from tb_psg_trans_proposed_comm_letter c\r\n"
						+ "		inner join cue_tb_psg_rank_app_master r on c.rank=r.id and c.status in ('1','5') and upper(r.level_in_hierarchy) ='RANK'\r\n"
						+ "		left join tb_miso_orbat_arm_code e on e.arm_code = c.parent_arm \r\n"
						+ "         left join (select max(dt_of_tos) as dt_of_tos,comm_id as comm_id from tb_psg_posting_in_out group by comm_id ) tos on tos.comm_id = c.id\r\n"
						+ " where c.unit_sus_no = ?   AND c.personnel_no ~ '^(NR|NS)' and  to_date(to_char(tos.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') \r\n"
						+ "	group by 1 ,2\r\n"
						+ "\r\n"
						+ ") mns on n0.arm_code=mns.arm_code	\r\n"
						+ "	left join \r\n"
				+ "		( select  e.arm_code,c.unit_sus_no,\r\n"
				+ "		count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sub_maj,\r\n"
				+ "		count(*) filter (where r.rank = 'SUB / EQUIVALENT') as sub,\r\n"
				+ "		count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub, \r\n"
				+ "       count(*) filter (where r.rank = 'WARRANT OFFICER') as warrant_off,\r\n"
				+ "		count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav,	 \r\n"
				+ "		count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,	 \r\n"
				+ "		count(*) filter (where r.rank = 'SEP / EQUIVALENT') as SEP,		 \r\n"
				+ "		count(*) filter (where r.rank = 'RECTS') as rect			\r\n"
				+ "	from tb_psg_census_jco_or_p c\r\n"
				+ "	inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status  = '1'\r\n"
				+ "	left join tb_miso_orbat_arm_code e on e.arm_code = c.arm_service where  c.unit_sus_no = ? and \r\n"
				+ " to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
				+ "   group by 1 ,2\r\n" + "		\r\n" + "		)n2 on n0.arm_code = n2.arm_code\r\n"
				+ "    where  (n1.brig_above IS NOT NULL OR n1.col IS NOT NULL or n1.ltcol IS NOT NULL OR n1.ltcol IS NOT NULL OR n1.maj IS NOT NULL OR\r\n"
				+ "		   n1.capt_lt IS NOT NULL \r\n"
				+ " or mns.brig_above_mns IS NOT NULL OR mns.col_mns IS NOT NULL or mns.ltcol_s_mns IS NOT NULL OR mns.ltcol_ts_mns IS NOT NULL OR mns.maj_mns IS NOT NULL OR\r\n"
				+ "		   mns.capt_lt_mns IS NOT NULL OR n2.sub_maj IS NOT NULL OR n2.sub IS NOT NULL OR n2.nb_sub IS NOT NULL OR n2.warrant_off IS NOT NULL OR\r\n"
				+ "		  n2.hav IS NOT NULL OR n2.nk IS NOT NULL OR n2.sep IS NOT NULL OR n2.rect IS NOT NULL  )\r\n"
				+ " order by 1 asc ";
	}

	public String Insert_Report_3009_posted_str_civ() {

		return " INSERT INTO tb_psg_posted_civ_3009(gp_a_gazetted,gp_b_gazetted,gp_b_non_gazetted,gp_c_non_gazetted,sus_no,month,year,version) \r\n"
				+ "select  count (*) filter (where civ.civ_group='A' and civ.classification_services = '1') as gp_a_gazetted, \r\n"
				+ "count (*) filter (where civ.civ_group= 'B' and civ.classification_services = '1') as gp_b_gazetted, \r\n"
				+ "count (*) filter (where civ.civ_group='B' and civ.classification_services = '2') as gp_b_non_gazetted,\r\n"
				+ "count (*) filter (where civ.civ_group='C' and civ.classification_services = '2' ) as gp_c_non_gazetted,?,?,?,? \r\n"
				+ "from tb_psg_civilian_dtl_main civ\r\n"
				+ "inner join tb_miso_orbat_unt_dtl sub on civ.sus_no = sub.sus_no  and  sub.status_sus_no = 'Active'\r\n"
				+ "where  civ.status = '1' and civ.sus_No =? and to_date(to_char(civ.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n";
	}

	public String Insert_Report_3009_rank_and_trade_wise_jco_or() {

//		return " insert into tb_psg_rank_and_trade_wise_jco_or_3009(sus_no,month,year,version,arms_services,trade,sub_maj_jco,sub_jco,nb_sub_jco,warrant_officer_jco,\r\n"
//				+ " hav_or,nk_or,lnk_sep_or,rects_or,total) \r\n"
//				+ "select ?,?,?,?,e.arm_desc as arms_services,t.trade,\r\n"
//				+ "count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sub_maj_jco,\r\n"
//				+ "count(*) filter (where r.rank = 'SUB / EQUIVALENT') as sub_jco,\r\n"
//				+ "count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub_jco,\r\n"
//				+ "count(*) filter (where r.rank = 'WARRANT OFFICER') as warrant_officer_jco, \r\n"
//				+ "count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav_or,	 \r\n"
//				+ "count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk_or,	\r\n"
//				+ " count(*) filter (where r.rank = 'SEP / EQUIVALENT') as lnk_sep_or,\r\n"
//				+ "count(*) filter (where r.rank = 'RECTS') as rects_or,\r\n"
//				+ "count(*) filter (where r.rank in ('SUB MAJ / EQUIVALENT','SUB / EQUIVALENT' ,'NB SUB / EQUIVALENT','WARRANT OFFICER','HAV / EQUIVALENT','NK / EQUIVALENT','SEP / EQUIVALENT'\r\n"
//				+ "'RECTS')) as total\r\n" + "from tb_psg_census_jco_or_p c\r\n"
//				+ "inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status  = '1'\r\n"
//				+ "left join tb_miso_orbat_arm_code e on e.arm_code = c.arm_service  \r\n"
//				+ "left join tb_psg_mstr_trade_jco t on  t.id = c.trade  \r\n"
//				+ "where c.unit_sus_no =?  and to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') \r\n"
//				+ "group by 1,2,e.arm_desc,t.trade \r\n";

		return "insert into tb_psg_rank_and_trade_wise_jco_or_3009(sus_no,month,year,version,arms_services,trade,sub_maj_jco,sub_jco,nb_sub_jco,warrant_officer_jco,\r\n"
				+ "												   hav_or,nk_or,lnk_sep_or,rects_or,total)\r\n" + "\r\n"
				+ "select  ?,?,?,?,auth.arm_desc as arms_services, auth.trade,\r\n"
				+ "auth.sub_maj as sub_maj_jco,auth.sub as sub_jco,auth.nb_sub as nb_sub_jco,\r\n"
				+ "auth.warrnt_off as warrant_officer_jco,auth.hav as hav_or,auth.nk as nk_or,auth.sep as lnk_sep_or,auth.rects as rects_or,\r\n"
				+ "coalesce(auth.sub_maj + auth.sub + auth.nb_sub + auth.warrnt_off + auth.hav + auth.nk + auth.sep + auth.rects)as total\r\n"
				+ "from (select e.arm_desc,t.trade,\r\n"
				+ "	count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sub_maj,\r\n"
				+ "	count(*) filter (where r.rank = 'SUB / EQUIVALENT') as sub,\r\n"
				+ "	count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub, \r\n"
				+ "	count(*) filter (where r.rank = 'WARRANT OFFICER') as warrnt_off,\r\n"
				+ "	count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav,	 \r\n"
				+ "	count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,	 \r\n"
				+ "	count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep,		 \r\n"
				+ "	count(*) filter (where r.rank = 'RECTS') as rects \r\n" + " 	from tb_psg_census_jco_or_p c\r\n"
				+ "	inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status  = '1'\r\n"
				+ "	left join tb_miso_orbat_arm_code e on e.arm_code = c.arm_service\r\n"
				+ "	left join tb_psg_mstr_trade_jco t on  t.id = c.trade\r\n"
				+ "	where c.unit_sus_no = ? and to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
				+ " 	group by 1,2 ) auth ";

	}

	public String Insert_Report_3009_religious_denomination() {

//		return "insert into tb_psg_religious_denomination_3009 (sus_no,month,year,version,arms_services,religion,jcos_posted_str_incl_ere_pers,or_posted_str_incl_ere_pers,held_religious_teacher,auth_religious_teacher)\r\n"
//				+ "select ?,?,?,?,n1.arm_desc as arms_services,n1.religion_name as religion,n1.jcos as jcos_posted_str_incl_ere_pers,n1.ors as or_posted_str_incl_ere_pers,\r\n"
//				+ "n1.held_religious_teacher as held_religious_teacher,COALESCE(n3.auth,'0') as auth_religious_teacher from (select  c.unit_sus_no,  c.arm_service ,\r\n"
//				+ "e.arm_desc, c.religion, s.religion_name,\r\n"
//				+ "			count(*) filter (where r.rank IN ( 'WARRANT OFFICER','SUB MAJ / EQUIVALENT','SUB / EQUIVALENT','NB SUB / EQUIVALENT')) as jcos,\r\n"
//				+ "		count(*) filter (where r.rank IN ( 'HAV / EQUIVALENT','NK / EQUIVALENT','SEP / EQUIVALENT','RECTS')) as ors	,\r\n"
//				+ "		count(*)  filter (where c.trade = '100') as held_religious_teacher \r\n"
//				+ "		from tb_psg_census_jco_or_p c \r\n"
//				+ "		inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status  = '1' \r\n"
//				+ "		left join tb_miso_orbat_arm_code e on e.arm_code = c.arm_service \r\n"
//				+ "		left join tb_psg_mstr_religion s on s.religion_id = c.religion   \r\n"
//				+ "where to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
//				+ "		group by 1,2,3,4,5   \r\n" + "	) n1 left join  	\r\n"
//				+ "	(select a.sus_no,  sum(a.base_auth+a.mod_auth+a.foot_auth) AS auth \r\n"
//				+ "		from sus_pers_stdauth a  \r\n"
//				+ "		left join cue_tb_psg_rank_app_master b on b.code = a.app_trd_code  and a.rank_cat in ('1')\r\n"
//				+ "		where  upper(b.level_in_hierarchy) = upper('Appointment') and b.description ilike 'religious%'  \r\n"
//				+ "		group by 1 )  n3 on n3.sus_no=n1.unit_sus_no \r\n" + "	 where n1.unit_sus_no =?\r\n";

		return "insert into tb_psg_religious_denomination_3009 (sus_no,month,year,version,arms_services,religion,jcos_posted_str_incl_ere_pers,or_posted_str_incl_ere_pers,held_religious_teacher,auth_religious_teacher)\r\n"
				+ "select  ?,?,?,?,n1.arm_desc as arms_services,n1.religion_name as religion,n1.jcos as jcos_posted_str_incl_ere_pers,n1.ors as or_posted_str_incl_ere_pers,n1.held_religious_teacher as held_religious_teacher,COALESCE(n3.auth,'0') as auth_religious_teacher from (select  c.unit_sus_no,  c.arm_service , e.arm_desc, c.religion, s.religion_name,\r\n"
				+ "	count(*) filter (where r.rank IN ( 'WARRANT OFFICER','SUB MAJ / EQUIVALENT','SUB / EQUIVALENT','NB SUB / EQUIVALENT')) as jcos,\r\n"
				+ "	count(*) filter (where r.rank IN ( 'HAV / EQUIVALENT','NK / EQUIVALENT','SEP / EQUIVALENT','RECTS')) as ors	, \r\n"
				+ "	count(*)  filter (where c.trade = '100') as held_religious_teacher\r\n"
				+ "	from tb_psg_census_jco_or_p c\r\n"
				+ "	inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status  = '1'\r\n"
				+ "	left join tb_miso_orbat_arm_code e on e.arm_code = c.arm_service\r\n"
				+ "	left join tb_psg_mstr_religion s on s.religion_id = c.religion\r\n"
				+ "where to_date(to_char(c.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
				+ "	  group by 1,2,3,4,5   \r\n" + ") n1 left join  	\r\n"
				+ "(select a.sus_no,  sum(a.base_auth+a.mod_auth+a.foot_auth) AS auth \r\n"
				+ "	from sus_pers_stdauth a  \r\n"
				+ "	left join cue_tb_psg_rank_app_master b on b.code = a.app_trd_code  and a.rank_cat in ('1')\r\n"
				+ "	where  upper(b.level_in_hierarchy) = upper('Appointment') and b.description ilike 'religious%' \r\n"
				+ "	group by 1 )  n3 on n3.sus_no=n1.unit_sus_no\r\n" + " where n1.unit_sus_no = ? \r\n"
				+ "  order by 1 asc ";

	}

	public String Insert_Report_3009_Summary() {

		return " insert into tb_psg_summary_3009 (sus_no,month,year,version,offrs_auth,offrs_posted,offrs_sur,offrs_defi,offrs_authover_above,offrs_authno,jco_auth,jco_posted,jco_sur,jco_defi,jco_authover_above,jco_authno,\r\n"
				+ " 								or_auth,or_posted,or_sur,or_defi,or_authover_above,or_authno,civ_auth,civ_posted,civ_sur,civ_defi,civ_authover_above,civ_authno,"
				+ "offrs_mns_auth,offrs_mns_posted,offrs_mns_sur,offrs_mns_defi,offrs_mns_authover_above,offrs_mns_authno)\r\n"
				+ "select\r\n" + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?\r\n";
	}

	public String Insert_Report_3009_Summary_scheduler() {

		return " insert into tb_psg_summary_3009 (sus_no,month,year,offrs_auth,offrs_posted,offrs_sur,offrs_defi,offrs_mns_auth,offrs_mns_posted,offrs_mns_sur,offrs_mns_defi,"
				+ "jco_auth,jco_posted,jco_sur,jco_defi,\r\n"
				+ " 								or_auth,or_posted,or_sur,or_defi,civ_auth,civ_posted,civ_sur,civ_defi,version)\r\n"
				+ "select\r\n"
				+ "?,?,?,\r\n"
				+ "sum (auth.offr) as offrs_auth,\r\n"
				+ "sum (auth.offrsPosted) as offrs_posted,\r\n"
				+ "CASE WHEN (SUM(auth.offrsPosted) - SUM(auth.offr)) >= 0 THEN (SUM(auth.offrsPosted) - SUM(auth.offr)) ELSE 0 END AS offrs_sur,\r\n"
				+ "CASE WHEN (SUM(auth.offrsPosted) - SUM(auth.offr)) < 0 THEN (SUM(auth.offrsPosted) - SUM(auth.offr)) ELSE 0 END AS offrs_defi,\r\n"
				+ "\r\n"
				+ "sum (auth.mns_off) as offrs_mns_auth,\r\n"
				+ "sum (auth.offrsmnsPosted) as offrs_mns_posted,\r\n"
				+ "CASE WHEN (SUM(auth.offrsmnsPosted) - SUM(auth.mns_off)) >= 0 THEN (SUM(auth.offrsmnsPosted) - SUM(auth.mns_off)) ELSE 0 END AS offrs_mns_sur,\r\n"
				+ "CASE WHEN (SUM(auth.offrsmnsPosted) - SUM(auth.mns_off)) < 0 THEN (SUM(auth.offrsmnsPosted) - SUM(auth.mns_off)) ELSE 0 END AS offrs_mns_defi,\r\n"
				+ "\r\n"
				+ "sum(auth.jco) as jco_auth,\r\n"
				+ "sum(auth.jcosPosted) as jco_posted, \r\n"
				+ "CASE WHEN (SUM(auth.jcosPosted) - SUM(auth.jco)) >= 0 THEN (SUM(auth.jcosPosted) - SUM(auth.jco)) ELSE 0 END AS jco_sur,\r\n"
				+ "CASE WHEN (SUM(auth.jcosPosted) - SUM(auth.jco)) < 0 THEN (SUM(auth.jcosPosted) - SUM(auth.jco)) ELSE 0 END AS jco_defi, \r\n"
				+ "sum (auth.ors) as or_auth,\r\n"
				+ "sum(auth.orPosted) as or_posted ,\r\n"
				+ "CASE WHEN (SUM(auth.orPosted) - SUM(auth.ors)) >= 0 THEN (SUM(auth.orPosted) - SUM(auth.ors)) ELSE 0 END AS or_sur,\r\n"
				+ "CASE WHEN (SUM(auth.orPosted) - SUM(auth.ors)) < 0 THEN (SUM(auth.orPosted) - SUM(auth.ors)) ELSE 0 END AS or_defi,\r\n"
				+ "sum(auth.civauth)as civ_auth,\r\n"
				+ "sum(auth.civposted) as civ_posted,\r\n"
				+ " CASE WHEN (SUM(auth.civposted) - SUM(auth.civauth)) >= 0 THEN (SUM(auth.civposted) - SUM(auth.civauth)) ELSE 0 END AS civ_sur,\r\n"
				+ " CASE WHEN (SUM(auth.civposted) - SUM(auth.civauth)) < 0 THEN (SUM(auth.civposted) - SUM(auth.civauth)) ELSE 0 END AS civ_defi,?\r\n"
				+ "\r\n"
				+ "from (SELECT sum(COALESCE (auths.offr,'0')) AS offr,\r\n"
				+ "             sum(COALESCE (auths.mns_off,'0')) AS mns_off,\r\n"
				+ "              sum(COALESCE (auths.jco,'0')) AS jco,\r\n"
				+ "             sum(COALESCE (auths.ors,'0')) AS ors,\r\n"
				+ "               0 AS offrsposted,\r\n"
				+ "							 0 as offrsmnsPosted,\r\n"
				+ "               0 AS jcosposted,\r\n"
				+ "               0 AS orposted,\r\n"
				+ "               0 AS civauth,\r\n"
				+ "               0 AS civposted\r\n"
				+ "         FROM (\r\n"
				+ "        SELECT a.sus_no,\r\n"
				+ "               a.cat_id,\r\n"
				+ "               c.label AS per_category,\r\n"
				+ "               a.arm,\r\n"
				+ "               b.arm_desc,\r\n"
				+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '0') AS offr,\r\n"
				+ "							 0 as mns_off,\r\n"
				+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '1') AS jco,\r\n"
				+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('2','3')) AS ors\r\n"
				+ "          FROM sus_pers_stdauth a\r\n"
				+ "          LEFT JOIN tb_miso_orbat_arm_code b\r\n"
				+ "            ON b.arm_code = a.arm\r\n"
				+ "          LEFT JOIN t_domain_value c\r\n"
				+ "            ON c.codevalue = a.cat_id\r\n"
				+ "           AND c.domainid = 'PERSON_CAT'\r\n"
				+ "         WHERE a.sus_no = ?\r\n"
				+ "           AND a.cat_id in ('1','2')\r\n"
				+ "				 and b.arm_desc !='MILITARY NURSING SERVICE [MNS]'\r\n"
				+ "         GROUP BY a.sus_no,\r\n"
				+ "                  a.cat_id,\r\n"
				+ "                  c.label,\r\n"
				+ "                  a.arm,\r\n"
				+ "                  b.arm_desc\r\n"
				+ "     UNION ALL SELECT a.sus_no,\r\n"
				+ "               a.cat_id,\r\n"
				+ "               c.label AS per_category,\r\n"
				+ "               a.arm,\r\n"
				+ "               b.arm_desc,\r\n"
				+ "							 0 as offr,\r\n"
				+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '0') AS mns_off,\r\n"
				+ "               0 AS jco,\r\n"
				+ "               0 AS ors\r\n"
				+ "          FROM sus_pers_stdauth a\r\n"
				+ "          LEFT JOIN tb_miso_orbat_arm_code b\r\n"
				+ "            ON b.arm_code = a.arm\r\n"
				+ "          LEFT JOIN t_domain_value c\r\n"
				+ "            ON c.codevalue = a.cat_id\r\n"
				+ "           AND c.domainid = 'PERSON_CAT'\r\n"
				+ "         WHERE a.sus_no = ?\r\n"
				+ "           AND a.cat_id in('1','2')\r\n"
				+ "					 and b.arm_desc ='MILITARY NURSING SERVICE [MNS]'\r\n"
				+ "         GROUP BY a.sus_no,\r\n"
				+ "                  a.cat_id,\r\n"
				+ "                  c.label,\r\n"
				+ "                  a.arm,\r\n"
				+ "                  b.arm_desc\r\n"
				+ "         ORDER BY 1 ASC\r\n"
				+ "         LIMIT ALL\r\n"
				+ "        OFFSET 0\r\n"
				+ "       ) auths\r\n"
				+ "			 union \r\n"
				+ "\r\n"
				+ "SELECT\r\n"
				+ "  0 AS offr,0 as mns_off, 0 AS jco,0 AS ors, SUM(n1.brig_above) + SUM(n1.col) + SUM(n1.col_ts) + SUM(n1.ltcol) + SUM(n1.maj) + SUM(n1.capt_lt) AS offrsPosted,\r\n"
				+ "  SUM(mns.brig_above_mns) + SUM(mns.col_mns) + SUM(mns.ltcol_s_mns) + SUM(mns.ltcol_ts_mns) + SUM(mns.maj_mns) + SUM(mns.capt_lt_mns) AS offrsmnsPosted,\r\n"
				+ "	SUM(n2.sub_maj) + SUM(n2.sub) + SUM(n2.nb_sub) + SUM(n2.warrant_off) AS jcosPosted,\r\n"
				+ "  SUM(n2.hav) + SUM(n2.nk) + SUM(n2.sep) + SUM(n2.rect) AS orPosted,0 as civauth,0 as civposted\r\n"
				+ "FROM\r\n"
				+ "  tb_miso_orbat_arm_code n0\r\n"
				+ "LEFT JOIN\r\n"
				+ "  (\r\n"
				+ "    SELECT\r\n"
				+ "      e.arm_code, c.unit_sus_no AS sus_no,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.description IN ('GEN', 'LT GEN', 'MAJ GEN', 'BRIG')) AS brig_above,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.description = 'COL') AS col,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.description = 'COL [TS]') AS col_ts,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.description = 'LT COL') AS ltcol,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.description = 'MAJ') AS maj,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.description IN ('CAPT', 'LT')) AS capt_lt\r\n"
				+ "    FROM tb_psg_trans_proposed_comm_letter c\r\n"
				+ "    INNER JOIN cue_tb_psg_rank_app_master r ON c.rank = r.id AND c.status IN ('1', '5') AND UPPER(r.level_in_hierarchy) = 'RANK'\r\n"
				+ "    LEFT JOIN tb_miso_orbat_arm_code e ON e.arm_code = c.parent_arm\r\n"
				+ "     left join (select max(dt_of_tos) as dt_of_tos,comm_id as comm_id from tb_psg_posting_in_out group by comm_id ) tos on tos.comm_id = c.id\r\n"
				+ "    WHERE  c.unit_sus_no = ? and  c.personnel_no !~ '^(NR|NS)'\r\n"
				+ "      AND TO_DATE(TO_CHAR(tos.dt_of_tos, 'Mon YYYY'), 'Mon YYYY') <= TO_DATE(?, 'YYYY/MM/DD')\r\n"
				+ "    GROUP BY 1, 2\r\n"
				+ "  ) n1 ON n0.arm_code = n1.arm_code\r\n"
				+ "	LEFT JOIN\r\n"
				+ "  (\r\n"
				+ "    SELECT\r\n"
				+ "      e.arm_code, c.unit_sus_no AS sus_no,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.description IN ('GEN', 'LT GEN', 'MAJ GEN', 'BRIG')) AS brig_above_mns,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.description = 'COL') AS col_mns,\r\n"
				+ "      count(*) filter (where r.description = 'LT COL {S}') as ltcol_s_mns,\r\n"
				+ "		count(*) filter (where r.description = 'LT COL {TS}') as ltcol_ts_mns,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.description = 'MAJ') AS maj_mns,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.description IN ('CAPT', 'LT')) AS capt_lt_mns\r\n"
				+ "    FROM tb_psg_trans_proposed_comm_letter c\r\n"
				+ "    INNER JOIN cue_tb_psg_rank_app_master r ON c.rank = r.id AND c.status IN ('1', '5') AND UPPER(r.level_in_hierarchy) = 'RANK'\r\n"
				+ "    LEFT JOIN tb_miso_orbat_arm_code e ON e.arm_code = c.parent_arm\r\n"
				+ "     left join (select max(dt_of_tos) as dt_of_tos,comm_id as comm_id from tb_psg_posting_in_out group by comm_id ) tos on tos.comm_id = c.id\r\n"
				+ "    WHERE  c.unit_sus_no = ? AND c.personnel_no ~ '^(NR|NS)'\r\n"
				+ "      AND TO_DATE(TO_CHAR(tos.dt_of_tos, 'Mon YYYY'), 'Mon YYYY') <= TO_DATE(?, 'YYYY/MM/DD')\r\n"
				+ "    GROUP BY 1, 2\r\n"
				+ "  ) mns ON n0.arm_code = mns.arm_code\r\n"
				+ "LEFT JOIN\r\n"
				+ "  (\r\n"
				+ "    SELECT   e.arm_code,  c.unit_sus_no,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.rank = 'SUB MAJ / EQUIVALENT') AS sub_maj,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.rank = 'SUB / EQUIVALENT') AS sub,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.rank = 'NB SUB / EQUIVALENT') AS nb_sub,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.rank = 'WARRANT OFFICER') AS warrant_off,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.rank = 'HAV / EQUIVALENT') AS hav,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.rank = 'NK / EQUIVALENT') AS nk,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.rank = 'SEP / EQUIVALENT') AS sep,\r\n"
				+ "      COUNT(*) FILTER (WHERE r.rank = 'RECTS') AS rect\r\n"
				+ "    FROM tb_psg_census_jco_or_p c\r\n"
				+ "    INNER JOIN tb_psg_mstr_rank_jco r ON c.rank = r.id AND c.status = '1'\r\n"
				+ "    LEFT JOIN tb_miso_orbat_arm_code e ON e.arm_code = c.arm_service\r\n"
				+ "    WHERE  c.unit_sus_no = ?\r\n"
				+ "      AND TO_DATE(TO_CHAR(c.date_of_tos, 'Mon YYYY'), 'Mon YYYY') <= TO_DATE(?, 'YYYY/MM/DD')\r\n"
				+ "    GROUP BY  1, 2\r\n"
				+ "  ) n2 ON n0.arm_code = n2.arm_code\r\n"
				+ "WHERE\r\n"
				+ "  (n1.brig_above IS NOT NULL OR n1.col IS NOT NULL or n1.ltcol IS NOT NULL OR n1.ltcol IS NOT NULL OR n1.maj IS NOT NULL OR\r\n"
				+ "						   n1.capt_lt IS NOT NULL \r\n"
				+ "				 or mns.brig_above_mns IS NOT NULL OR mns.col_mns IS NOT NULL or mns.ltcol_s_mns IS NOT NULL OR mns.ltcol_ts_mns IS NOT NULL OR mns.maj_mns IS NOT NULL OR\r\n"
				+ "						   mns.capt_lt_mns IS NOT NULL OR n2.sub_maj IS NOT NULL OR n2.sub IS NOT NULL OR n2.nb_sub IS NOT NULL OR n2.warrant_off IS NOT NULL OR\r\n"
				+ "						  n2.hav IS NOT NULL OR n2.nk IS NOT NULL OR n2.sep IS NOT NULL OR n2.rect IS NOT NULL )\r\n"
				+ "	  \r\n"
				+ "	  union\r\n"
				+ "select\r\n"
				+ "      0 AS offr,0 as mns_off,0 AS jco,0 AS ors,0 AS offrsPosted,0 as offrsmnsPosted,0 AS jcosPosted, 0 AS orPosted,\r\n"
				+ "COALESCE(sum(authciv.gp_a_gaz), 0) + COALESCE(sum(authciv.gp_b_gaz), 0) + COALESCE(sum(authciv.gp_b_non_gaz), 0) \r\n"
				+ "	  + COALESCE(sum(authciv.gp_c_non_gaz), 0) as civauth,0 as civposted\r\n"
				+ "from ( select \r\n"
				+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '4') as gp_a_gaz,\r\n"
				+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat = '5') as gp_b_gaz,\r\n"
				+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ( '6','9')) as gp_b_non_gaz,\r\n"
				+ "sum(a.base_auth +a.mod_auth+a.foot_auth) filter (where a.rank_cat IN ( '7','8','10','11','12')) as gp_c_non_gaz\r\n"
				+ "from sus_pers_stdauth a  left join t_domain_value c on c.codevalue = a.cat_id and c.domainid = 'PERSON_CAT'\r\n"
				+ " where a.sus_No = ? and a.cat_id in ('1','2')) authciv \r\n"
				+ "	 union\r\n"
				+ "	  select \r\n"
				+ " 0 AS offr,0 as mns_off ,0 AS jco,0 AS ors,0 AS offrsPosted,0 as offrsmnsPosted,0 AS jcosPosted, 0 AS orPosted,0 as civauth,\r\n"
				+ "COALESCE(sum(n3.gp_a_gaz), 0) + COALESCE(sum(n3.gp_b_gaz), 0) + COALESCE(sum(n3.gp_b_non_gaz), 0) \r\n"
				+ "+ COALESCE(sum(n3.gp_c_non_gaz), 0) as civposted\r\n"
				+ "from \r\n"
				+ "(select  count (*) filter (where civ.civ_group='A' and civ.classification_services = '1') as gp_a_gaz, \r\n"
				+ "	count (*) filter (where civ.civ_group= 'B' and civ.classification_services = '1') as gp_b_gaz,  \r\n"
				+ "	count (*) filter (where civ.civ_group='B' and civ.classification_services = '2') as gp_b_non_gaz, \r\n"
				+ "	count (*) filter (where civ.civ_group='C' and civ.classification_services = '2' ) as gp_c_non_gaz \r\n"
				+ "	from tb_psg_civilian_dtl_main civ\r\n"
				+ "	inner join tb_miso_orbat_unt_dtl sub on civ.sus_no = sub.sus_no and sub.status_sus_no = 'Active' 	\r\n"
				+ "	where \r\n"
				+ "	 civ.status = '1' and civ.sus_No = '?' \r\n"
				+ " and to_date(to_char(civ.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')  )n3  ) auth ";
	}

	public PreparedStatement setQueryWhereClause_SQLciv(PreparedStatement stmt, String Search, String roleAccess,
			String roleSusNo, String sus_no, String FDate, String LDate) {
		int flag = 0;
		try {
			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
				stmt.setString(2, FDate);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, FDate);
			}
			flag = 1;
			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "") {
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	public Boolean Insert_Report_3009(String username, String roleSusNo, String FDate, String month, String year,
			String userId, String present_return_no, String present_return_dt, String remarks, String distlist,
			String LDate, String offf_auth_count, String offf_posted_count, String offf_defi_count,
			String offf_sur_count, String officerauth, String officerauthno, String jco_auth_count,
			String jco_posted_count, String jco_defi_count, String jco_sur_count, String jcoauth, String jcoauthno,
			String or_auth_count, String or_posted_count, String or_defi_count, String or_sur_count, String orauth,
			String orauthno, String civ_auth_count, String civ_posted_count, String civ_defi_count,
			String civ_sur_count, String civauth, String civauthno,String offf_mns_auth_count, String offf_mns_posted_count, String offf_mns_defi_count,
			String offf_mns_sur_count, String officermnsauth, String officermnsauthno,boolean iscronscheduling) throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = dataSource.getConnection();
//			  try {
		conn.setAutoCommit(false);
		int val = 0;
		int count = 0;
		ArrayList<ArrayList<String>> list_auth = Search_Report_3009_Auth(roleSusNo, FDate, LDate);
		ArrayList<ArrayList<String>> list_held = Search_Report_3009_held(roleSusNo, FDate, LDate);
		int totalAuth = 0;
		int totalHeld = 0;
		int sum_held = 0;
		int sum_auth = 0;

		for (int i = 0; i < list_auth.size(); i++) {
			String totalAuth1 = list_auth.get(i).get(4);

			if (totalAuth1 == null || totalAuth1.equals(null)) {
				totalAuth = 0;
			} else {
				totalAuth = Integer.parseInt(totalAuth1);
			}
			sum_auth += totalAuth;
		}
		for (int i = 0; i < list_held.size(); i++) {
			String totalHeld1 = list_held.get(i).get(1);

			if (totalHeld1 == null || totalHeld1.equals(null)) {
				totalHeld = 0;
			} else {
				totalHeld = Integer.parseInt(totalHeld1);
			}
			sum_held += totalHeld;
		}

		ArrayList<ArrayList<String>> wepe = getEstablishNo(roleSusNo);

		String we_pe_no = "";
		if (wepe.size() > 0) {
			we_pe_no = wepe.get(0).get(0);
		} else {
			we_pe_no = "";
		}

		ArrayList<ArrayList<String>> orbatlist = getcommand(roleSusNo);

		List<TB_PSG_IAFF_3009_MAIN> version = Get_3009_VersionData(month, year, roleSusNo);
		if (version.size() == 0) {

			val = 1;

			pstmt = conn.prepareStatement(Insert_Report_3009_Version());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, String.valueOf(val));
			pstmt.setInt(5, 0);
			pstmt.setString(6, username);

			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Insert_Report_3009_Main_Details());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, String.valueOf(val));

			pstmt.setString(5, orbatlist.get(0).get(0));// cmd sus
			pstmt.setString(6, orbatlist.get(0).get(1));// corp sus
			pstmt.setString(7, orbatlist.get(0).get(2));// div sus
			pstmt.setString(8, orbatlist.get(0).get(3));// bde sus

			pstmt.setString(9, String.valueOf(sum_auth));// auth
			pstmt.setString(10, String.valueOf(sum_held));// held
			pstmt.setString(11, present_return_no);
			pstmt.setString(12, present_return_dt);
			pstmt.setString(13, we_pe_no);// we_pe_no

			pstmt.setString(14, orbatlist.get(0).get(4));// cmd unit
			pstmt.setString(15, orbatlist.get(0).get(5));// corp unit
			pstmt.setString(16, orbatlist.get(0).get(6));// div unit
			pstmt.setString(17, orbatlist.get(0).get(7));// bde unit
			pstmt.setString(18, username);

			pstmt.setString(19, remarks);
			pstmt.setString(20, distlist);

			pstmt.executeUpdate();

		} else {
			count = Integer.parseInt(version.get(0).getVersion());
			val = count + 1;

			pstmt = conn.prepareStatement(Update_Report_3009_Version());
			pstmt.setString(1, month);
			pstmt.setString(2, year);
			pstmt.setString(3, String.valueOf(val));
			pstmt.setString(4, username);
			pstmt.setInt(5, version.get(0).getId());

			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Insert_Report_3009_Main_Details());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, String.valueOf(val));

			pstmt.setString(5, orbatlist.get(0).get(0));// cmd sus
			pstmt.setString(6, orbatlist.get(0).get(1));// corp sus
			pstmt.setString(7, orbatlist.get(0).get(2));// div sus
			pstmt.setString(8, orbatlist.get(0).get(3));// bde sus

			pstmt.setString(9, String.valueOf(sum_auth));// auth
			pstmt.setString(10, String.valueOf(sum_held));// held
			pstmt.setString(11, present_return_no);
			pstmt.setString(12, present_return_dt);
			pstmt.setString(13, we_pe_no);// we_pe_no

			pstmt.setString(14, orbatlist.get(0).get(4));// cmd unit
			pstmt.setString(15, orbatlist.get(0).get(5));// corp unit
			pstmt.setString(16, orbatlist.get(0).get(6));// div unit
			pstmt.setString(17, orbatlist.get(0).get(7));// bde unit
			pstmt.setString(18, username);
			pstmt.setString(19, remarks);
			pstmt.setString(20, distlist);
			pstmt.executeUpdate();

		}

		pstmt = conn.prepareStatement(Insert_Report_3009_auth_off_jco());
		pstmt.setString(1, roleSusNo);
		pstmt.setString(2, month);
		pstmt.setString(3, year);
		pstmt.setString(4, String.valueOf(val));
		pstmt.setString(5, roleSusNo);
		pstmt.setString(6, roleSusNo);
		pstmt.executeUpdate();

		pstmt = conn.prepareStatement(Insert_Report_3009_auth_civ());
		pstmt.setString(1, roleSusNo);
		pstmt.setString(2, month);
		pstmt.setString(3, year);
		pstmt.setString(4, String.valueOf(val));
		pstmt.setString(5, roleSusNo);
		pstmt.executeUpdate();

		pstmt = conn.prepareStatement(Insert_Report_3009_posted_offrs_jco_or());
		pstmt.setString(1, roleSusNo);
		pstmt.setString(2, month);
		pstmt.setString(3, year);
		pstmt.setString(4, String.valueOf(val));

		pstmt.setString(5, roleSusNo);
		pstmt.setString(6, FDate);
		pstmt.setString(7, roleSusNo);
		pstmt.setString(8, FDate);
		pstmt.setString(9, roleSusNo);
		pstmt.setString(10, FDate);
		pstmt.executeUpdate();

		pstmt = conn.prepareStatement(Insert_Report_3009_posted_str_civ());
		pstmt.setString(1, roleSusNo);
		pstmt.setString(2, month);
		pstmt.setString(3, year);
		pstmt.setString(4, String.valueOf(val));
		pstmt.setString(5, roleSusNo);
		pstmt.setString(6, FDate);
		pstmt.executeUpdate();

		pstmt = conn.prepareStatement(Insert_Report_3009_rank_and_trade_wise_jco_or());
		pstmt.setString(1, roleSusNo);
		pstmt.setString(2, month);
		pstmt.setString(3, year);
		pstmt.setString(4, String.valueOf(val));

		pstmt.setString(5, roleSusNo);
		pstmt.setString(6, FDate);
		pstmt.executeUpdate();

		pstmt = conn.prepareStatement(Insert_Report_3009_religious_denomination());
		pstmt.setString(1, roleSusNo);
		pstmt.setString(2, month);
		pstmt.setString(3, year);
		pstmt.setString(4, String.valueOf(val));

		pstmt.setString(5, FDate);
		pstmt.setString(6, roleSusNo);
		pstmt.executeUpdate();
		
		if(iscronscheduling == true) {
			  pstmt = conn.prepareStatement(Insert_Report_3009_Summary_scheduler());
			 pstmt.setString(1, roleSusNo);	 
			 pstmt.setString(2, month);
			 pstmt.setString(3, year);
			 pstmt.setString(4, String.valueOf(val));
			 
			 pstmt.setString(5, roleSusNo);	 
			 pstmt.setString(6, roleSusNo);	 
			 pstmt.setString(7, roleSusNo);	 
			 pstmt.setString(8, FDate);
			 pstmt.setString(9, roleSusNo);	 
			 pstmt.setString(10, FDate);
			 pstmt.setString(11, roleSusNo);
			 pstmt.setString(12, FDate);
			 pstmt.setString(13, roleSusNo);	 
			 pstmt.setString(14, roleSusNo);	 
			 pstmt.setString(15, FDate);
			 pstmt.executeUpdate();
		}else {
			pstmt = conn.prepareStatement(Insert_Report_3009_Summary());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, String.valueOf(val));
			pstmt.setString(5, offf_auth_count);
			pstmt.setString(6, offf_posted_count);
			pstmt.setString(7, offf_sur_count);
			pstmt.setString(8, offf_defi_count);
			pstmt.setString(9, officerauth);
			pstmt.setString(10, officerauthno);
			pstmt.setString(11, jco_auth_count);
			pstmt.setString(12, jco_posted_count);
			pstmt.setString(13, jco_sur_count);
			pstmt.setString(14, jco_defi_count);
			pstmt.setString(15, jcoauth);
			pstmt.setString(16, jcoauthno);
			pstmt.setString(17, or_auth_count);
			pstmt.setString(18, or_posted_count);
			pstmt.setString(19, or_sur_count);
			pstmt.setString(20, or_defi_count);
			pstmt.setString(21, orauth);
			pstmt.setString(22, orauthno);
			pstmt.setString(23, civ_auth_count);
			pstmt.setString(24, civ_posted_count);
			pstmt.setString(25, civ_sur_count);
			pstmt.setString(26, civ_defi_count);
			pstmt.setString(27, civauth);
			pstmt.setString(28, civauthno);
			
			pstmt.setString(29, offf_mns_auth_count);
			pstmt.setString(30, offf_mns_posted_count);
			pstmt.setString(31, offf_mns_sur_count);
			pstmt.setString(32, offf_mns_defi_count);
			pstmt.setString(33, officermnsauth);
			pstmt.setString(34, officermnsauthno);

			pstmt.executeUpdate();

		}

	

		
		pstmt.close();
		conn.commit();
		conn.close();

		return true;
	}

	// ---------------- search 3009 submitted from------------//
	public ArrayList<ArrayList<String>> Search_3009_Report_Version(String month, String year, HttpSession session,
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
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
				if (!unit_sus_no.equals("")) {
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
					+ " m.month,m.year,m.version from tb_psg_iaff_3009_main m \r\n"
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
				if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
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
				if ((roleType.equals("ALL") || roleType.equals("APP")) && status.equals("0")) {

					String View = "onclick=\"  if (confirm('Are You Sure You Want to Approve This ?') ){editData("
							+ rs.getString("month") + ",'" + rs.getString("year") + "' ,'" + rs.getString("version")
							+ "' ,'" + rs.getString("sus_no") + "','" + status + "')}else{ return false;}\"";
					f4 = "<i class='fa fa-eye'  " + View + " title='Approve/View Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This ?') ){deleteData("
							+ rs.getString("month") + ",'" + rs.getString("year") + "' ,'" + rs.getString("version")
							+ "' ,'" + rs.getString("sus_no") + "')}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Dustbin'></i>";
					
					list.add(f4);
					list.add(f1);

				}
				
				
				if ((roleType.equals("ALL") || roleType.equals("APP") || roleType.equals("DEO")) && status.equals("1")) {

					String View = "onclick=\"  if (confirm('Are You Sure You Want to View This ?') ){editData("
							+ rs.getString("month") + ",'" + rs.getString("year") + "' ,'" + rs.getString("version")
							+ "' ,'" + rs.getString("sus_no") + "','" + status + "')}else{ return false;}\"";
					f4 = "<i class='fa fa-eye'  " + View + " title='Approve/View Data'></i>";

					
					list.add(f4);
			

				}

				/*list.add(f4);
				list.add(f1);*/
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

	// ----------------delete submitted 3009-------------------------------------//
	public Boolean Delete_Report_3009(String username, String roleSusNo, String month, String year, String version)
			throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = dataSource.getConnection();
		try {
			conn.setAutoCommit(false);

			List<TB_PSG_IAFF_3009_MAIN> version0 = Get_3009_VersionData(month, year, roleSusNo);

			pstmt = conn.prepareStatement(Delete_Report_3009_Version());
			pstmt.setString(1, username);
			pstmt.setInt(2, version0.get(0).getId());
			pstmt.setString(3, roleSusNo);
			pstmt.setString(4, month);
			pstmt.setString(5, year);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Delete_Report_3009_Main_Details());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Delete_Report_3009_auth_off_jco());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Delete_Report_3009_auth_civ());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Delete_Report_3009_posted_offrs_jco_or());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Delete_Report_3009_posted_str_civ());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Delete_Report_3009_rank_and_trade_wise_jco_or());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Delete_Report_3009_religious_denomination());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Delete_Report_3009_summary());
			pstmt.setString(1, roleSusNo);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setString(4, version);
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

	public String Delete_Report_3009_Version() {
		return "update tb_psg_iaff_3009_main set status='3', modified_by= ?, modified_date= now()::timestamp \r\n"
				+ " where id=? and sus_no=? and month=? and year=? ";
	}

	public String Delete_Report_3009_Main_Details() {
		return "update tb_psg_iaff_3009_main_details set status='3', modified_by= ?, modified_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_Report_3009_auth_off_jco() {
		return "update tb_psg_auth_str_jco_or_3009 set status='3' \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_Report_3009_auth_civ() {
		return "update tb_psg_auth_civ_3009 set status='3' \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_Report_3009_posted_offrs_jco_or() {
		return "update tb_psg_posted_offrs_jco_or_3009 set status='3' \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_Report_3009_posted_str_civ() {
		return "update tb_psg_posted_civ_3009 set status='3' \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_Report_3009_rank_and_trade_wise_jco_or() {
		return "update tb_psg_rank_and_trade_wise_jco_or_3009 set status='3' \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_Report_3009_religious_denomination() {
		return "update tb_psg_religious_denomination_3009 set status='3' \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Delete_Report_3009_summary() {
		return "update tb_psg_summary_3009 set status='3' \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	// -----------------end delete submitted 3009-------------------------------//
	// ----------------APPROVE 3009---------------------------------------------//
	public Boolean Approve_Report_3009(String username, String roleSusNo, String month, String year, String version)
			throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = dataSource.getConnection();
		try {
			conn.setAutoCommit(false);

			List<TB_PSG_IAFF_3009_MAIN> version0 = Get_3009_VersionData(month, year, roleSusNo);

			pstmt = conn.prepareStatement(Approve_Report_3009_Version());
			pstmt.setString(1, username);
			pstmt.setInt(2, version0.get(0).getId());
			pstmt.setString(3, roleSusNo);
			pstmt.setString(4, month);
			pstmt.setString(5, year);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Approve_Report_3009_Main());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Approve_auth_str_jco_or_3009());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Approve_Report_auth_civ_3009());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Approve_Report_posted_offrs_jco_or_3009());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Approve_Report_posted_civ_3009());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(Approve_Report_rank_and_trade_wise_jco_or_3009());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();
			// bisag v2 290822 (split auth and held)
			pstmt = conn.prepareStatement(Approve_Report_religious_denomination_3009());
			pstmt.setString(1, username);
			pstmt.setString(2, roleSusNo);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, version);
			pstmt.executeUpdate();
			// bisag v2 290822 (split auth and held) end
			pstmt = conn.prepareStatement(Approve_Report_summary_3009());
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

	public String Approve_Report_3009_Version() {
		return "update tb_psg_iaff_3009_main set status='1', modified_by= ?, modified_date= now()::timestamp \r\n"
				+ " where id=? and sus_no=? and month=? and year=? ";
	}

	public String Approve_Report_3009_Main() {
		return "update tb_psg_iaff_3009_main_details set status='1', modified_by= ?, modified_date= now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Approve_auth_str_jco_or_3009() {
		return "update tb_psg_auth_str_jco_or_3009 set status='1' , approved_by = ? , approved_date = now()::timestamp  \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Approve_Report_auth_civ_3009() {
		return "update tb_psg_auth_civ_3009 set status='1', approved_by = ? , approved_date = now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Approve_Report_posted_offrs_jco_or_3009() {
		return "update tb_psg_posted_offrs_jco_or_3009 set status='1' , approved_by = ? , approved_date = now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Approve_Report_posted_civ_3009() {
		return "update tb_psg_posted_civ_3009 set status='1', approved_by = ? , approved_date = now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Approve_Report_rank_and_trade_wise_jco_or_3009() {
		return "update tb_psg_rank_and_trade_wise_jco_or_3009 set status='1', approved_by = ? , approved_date = now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Approve_Report_religious_denomination_3009() {
		return "update tb_psg_religious_denomination_3009 set status='1', approved_by = ? , approved_date = now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	public String Approve_Report_summary_3009() {
		return "update tb_psg_summary_3009 set status='1', approved_by = ? , approved_date = now()::timestamp \r\n"
				+ " where sus_no=? and month=? and year=? and version=?";
	}

	// ---------------END APPROVE 3009 -----------------------------------------//
	public ArrayList<ArrayList<String>> Search_Report_auth_str_jco_or_3009(String month, String year,
			HttpSession session, String version, String unit_sus_no) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct auth.arm_services,COALESCE (auth.offrs,'0') as offrs,\r\n"
					+ " COALESCE (auth.mns_offrs,'0') as mns_offrs,\r\n" + "COALESCE (auth.jcos,'0') as jcos,\r\n"
					+ "COALESCE (auth.ors,'0') as ors \r\n" + "from tb_psg_iaff_3009_main as p \r\n"
					+ "inner join tb_psg_auth_str_jco_or_3009 as auth \r\n"
					+ "on p.sus_no = auth.sus_no and p.year = auth.year and p.version=auth.version \r\n"
					+ "where auth.sus_no = ?  and auth.year = ? and auth.month = ? and auth.version = ?\r\n";

			stmt = conn.prepareStatement(q);

			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
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
				list.add(rs.getString("arm_services"));// 0
				list.add(rs.getString("offrs"));// 1
				list.add(rs.getString("mns_offrs"));// 2
				list.add(rs.getString("jcos"));// 3
				list.add(rs.getString("ors"));// 4

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

	public ArrayList<ArrayList<String>> Search_Report_auth_civ_3009(String month, String year, HttpSession session,
			String version, String unit_sus_no) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct  COALESCE (auth.gp_a_gazetted,'0') as gp_a_gazetted,\r\n"
					+ "  COALESCE (auth.gp_b_gazetted,'0') as gp_b_gazetted,\r\n"
					+ "COALESCE (auth.gp_b_non_gazetted,'0') as gp_b_non_gazetted,\r\n"
					+ "  COALESCE (auth.gp_c_non_gazetted,'0') as gp_c_non_gazetted,auth.remarks \r\n"
					+ "from tb_psg_iaff_3009_main as p \r\n" + "inner join tb_psg_auth_civ_3009 as auth \r\n"
					+ "on p.sus_no = auth.sus_no and p.year = auth.year and p.version=auth.version \r\n"
					+ "where auth.sus_no = ?  and auth.year = ? and auth.month = ? and auth.version = ?\r\n";

			stmt = conn.prepareStatement(q);

			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
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
				list.add(rs.getString("gp_b_non_gazetted"));// 2
				list.add(rs.getString("gp_c_non_gazetted"));// 3
				list.add(rs.getString("remarks"));// 4

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

	public ArrayList<ArrayList<String>> Search_Report_posted_offrs_jco_or_3009(String month, String year,
			HttpSession session, String version, String unit_sus_no) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = " select distinct post.arms_services,post.brig_and_above_offrs,post.col_offrs,post.col_ts_offrs,post.lt_col_offrs,\r\n"
					+ "	    post.maj_offrs,post.capt_lt_offrs,post.brig_and_above_mns_offrs,\r\n"
					+ "		post.col_mns_offrs,post.lt_col_s_mns_offrs,post.lt_col_ts_mns_offrs,post.maj_mns_offrs,\r\n"
					+ "		post.capt_lt_mns_offrs,post.sub_major_jco,post.sub_jco,\r\n"
					+ "		post.nb_sub_jco,post.warrant_officer_jco,post.hav_or,post.nk_or,post.lnk_sep_or,post.rects_or\r\n"
					+ "       from tb_psg_iaff_3009_main as p\r\n"
					+ "       inner join tb_psg_posted_offrs_jco_or_3009 as post\r\n"
					+ "  on p.sus_no = post.sus_no and p.year = post.year and p.version=post.version\r\n"
					+ "where post.sus_no = ?  and post.year = ? and post.month = ? and post.version = ?\r\n";

			stmt = conn.prepareStatement(q);
			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
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
				list.add(rs.getString("arms_services")); // 0
				list.add(rs.getString("brig_and_above_offrs")); // 1
				list.add(rs.getString("col_offrs")); // 2
				list.add(rs.getString("col_ts_offrs")); // 3
				list.add(rs.getString("lt_col_offrs")); // 4
				list.add(rs.getString("maj_offrs")); // 5
				list.add(rs.getString("capt_lt_offrs")); // 6
				list.add(rs.getString("brig_and_above_mns_offrs")); // 7
				list.add(rs.getString("col_mns_offrs")); // 8
				list.add(rs.getString("lt_col_s_mns_offrs")); // 9
				list.add(rs.getString("lt_col_ts_mns_offrs")); // 10
				list.add(rs.getString("maj_mns_offrs")); // 11
				list.add(rs.getString("capt_lt_mns_offrs")); // 12
				list.add(rs.getString("sub_major_jco")); // 13
				list.add(rs.getString("sub_jco")); // 14
				list.add(rs.getString("nb_sub_jco")); // 15
				list.add(rs.getString("warrant_officer_jco")); // 16
				list.add(rs.getString("hav_or")); // 17
				list.add(rs.getString("nk_or")); // 18
				list.add(rs.getString("lnk_sep_or")); // 19
				list.add(rs.getString("rects_or")); // 20

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

	public ArrayList<ArrayList<String>> Search_Report_posted_civ_300(String month, String year, HttpSession session,
			String version, String unit_sus_no) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct  auth.gp_a_gazetted,auth.gp_b_gazetted,auth.gp_b_non_gazetted,auth.gp_c_non_gazetted,auth.remarks\r\n"
					+ "from tb_psg_iaff_3009_main as p \r\n" + "inner join tb_psg_posted_civ_3009 as auth \r\n"
					+ "on p.sus_no = auth.sus_no and p.year = auth.year and p.version=auth.version \r\n"
					+ "where auth.sus_no = ? and auth.year = ? and auth.month = ? and auth.version = ?\r\n";

			stmt = conn.prepareStatement(q);

			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
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
				list.add(rs.getString("gp_b_non_gazetted"));// 2
				list.add(rs.getString("gp_c_non_gazetted"));// 3
				list.add(rs.getString("remarks"));// 4

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

	public ArrayList<ArrayList<String>> Search_Report_rank_and_trade_wise_jco_or_3009(String month, String year,
			HttpSession session, String version, String unit_sus_no) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct rt.arms_services,rt.trade,rt.sub_maj_jco,rt.sub_jco,\r\n"
					+ "  rt.nb_sub_jco,rt.warrant_officer_jco,rt.hav_or,\r\n"
					+ " rt.nk_or,rt.lnk_sep_or,rt.rects_or,rt.total \r\n" + "  from  tb_psg_iaff_3009_main as p\r\n"
					+ " inner join tb_psg_rank_and_trade_wise_jco_or_3009  as rt\r\n"
					+ "on p.sus_no = rt.sus_no and p.year = rt.year and p.version=rt.version\r\n"
					+ "where rt.sus_no = ? and rt.year = ? and rt.month = ? and rt.version = ?\r\n";

			stmt = conn.prepareStatement(q);
			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
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
				list.add(rs.getString("arms_services"));// 0
				list.add(rs.getString("trade"));// 1
				list.add(rs.getString("sub_maj_jco"));// 2
				list.add(rs.getString("sub_jco"));// 3
				list.add(rs.getString("nb_sub_jco"));// 4
				list.add(rs.getString("warrant_officer_jco"));// 5
				list.add(rs.getString("hav_or"));// 6
				list.add(rs.getString("nk_or"));// 7
				list.add(rs.getString("lnk_sep_or"));// 8
				list.add(rs.getString("rects_or"));// 9
				list.add(rs.getString("total"));// 10

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

	public ArrayList<ArrayList<String>> Search_Report_religious_denomination_3009(String month, String year,
			HttpSession session, String version, String unit_sus_no) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct rd.arms_services,rd.religion,rd.jcos_posted_str_incl_ere_pers,rd.or_posted_str_incl_ere_pers,\r\n"
					+ "rd.held_religious_teacher,rd.auth_religious_teacher \r\n"
					+ "from  tb_psg_iaff_3009_main as p \r\n"
					+ "inner join tb_psg_religious_denomination_3009 as rd \r\n"
					+ "on p.sus_no = rd.sus_no and p.year = rd.year and p.version=rd.version \r\n"
					+ "where rd.sus_no = ? and rd.year = ? and rd.month = ? and rd.version = ?\r\n";

			stmt = conn.prepareStatement(q);
			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
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
				list.add(rs.getString("arms_services"));// 0
				list.add(rs.getString("religion"));// 1
				list.add(rs.getString("jcos_posted_str_incl_ere_pers"));// 2
				list.add(rs.getString("or_posted_str_incl_ere_pers"));// 3
				list.add(rs.getString("auth_religious_teacher"));// 5
				list.add(rs.getString("held_religious_teacher"));// 4
				

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

	public ArrayList<ArrayList<String>> Search_Report_summary(String month, String year, HttpSession session,
			String version, String unit_sus_no) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct offrs_auth , offrs_posted ,offrs_sur , offrs_defi ,offrs_mns_auth , offrs_mns_posted ,offrs_mns_sur , offrs_mns_defi , jco_auth ,jco_posted , jco_sur,jco_defi, or_auth,\r\n"
					+ "    or_posted,or_sur,or_defi,civ_auth,civ_posted,civ_sur,civ_defi,offrs_authover_above,offrs_authno,offrs_mns_authover_above,offrs_mns_authno,jco_authover_above,jco_authno,"
					+ "or_authover_above,or_authno,civ_authover_above,civ_authno\r\n"
					+ "					from  tb_psg_iaff_3009_main as p \r\n"
					+ "					inner join tb_psg_summary_3009 as sm\r\n"
					+ "					on p.sus_no = sm.sus_no and p.year = sm.year and p.version=sm.version \r\n"
					+ "					where sm.sus_no = ? and sm.year = ? and sm.month = ? and sm.version = ?\r\n";

			stmt = conn.prepareStatement(q);
			if (roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
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
				list.add(rs.getString("offrs_auth")); // 0
				list.add(rs.getString("offrs_authover_above")); // 1
				list.add(rs.getString("offrs_posted")); // 2
				list.add(rs.getString("offrs_sur")); // 3
				list.add(rs.getString("offrs_defi")); // 4
				list.add(rs.getString("offrs_authno")); // 5
				list.add(rs.getString("offrs_mns_auth")); // 6
				list.add(rs.getString("offrs_mns_authover_above")); // 7
				list.add(rs.getString("offrs_mns_posted")); // 8
				list.add(rs.getString("offrs_mns_sur")); // 9
				list.add(rs.getString("offrs_mns_defi")); // 10
				list.add(rs.getString("offrs_mns_authno")); // 11
				list.add(rs.getString("jco_auth")); // 12
				list.add(rs.getString("jco_authover_above")); // 13
				list.add(rs.getString("jco_posted")); // 14
				list.add(rs.getString("jco_sur")); // 15
				list.add(rs.getString("jco_defi")); // 16
				list.add(rs.getString("jco_authno")); // 17
				list.add(rs.getString("or_auth")); // 18
				list.add(rs.getString("or_authover_above")); // 19
				list.add(rs.getString("or_posted")); // 20
				list.add(rs.getString("or_sur")); // 21
				list.add(rs.getString("or_defi")); // 22
				list.add(rs.getString("or_authno")); // 23
				list.add(rs.getString("civ_auth")); // 24
				list.add(rs.getString("civ_authover_above")); // 25
				list.add(rs.getString("civ_posted")); // 26
				list.add(rs.getString("civ_sur")); // 27
				list.add(rs.getString("civ_defi")); // 28
				list.add(rs.getString("civ_authno")); // 29

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

	public ArrayList<String> getSusNoListForIAFF3009() {
		ArrayList<String> alist = new ArrayList<String>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct sus_no from tb_miso_orbat_unt_dtl ms "
					+ " inner join tb_psg_trans_proposed_comm_letter com on ms.sus_no = com.unit_sus_no "
					+ " where ms.status_sus_no='Active'  and com.status not in ('0','3') and sus_no not in (\r\n"
					+ "\r\n"
					+ "select distinct sus_no from tb_psg_iaff_3009_main where month=to_char( date_trunc('month'::text, CURRENT_DATE::timestamp with time zone) ,'MM') and"
					+ " year=to_char( date_trunc('year'::text, CURRENT_DATE::timestamp with time zone) ,'YYYY')) ";

			stmt = conn.prepareStatement(q);
//				stmt.setString(1, month);
//				stmt.setString(2, year);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				alist.add(rs.getString("sus_no"));// 0
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

	public Boolean Insert_summary_3009(String sus_no, String month, String year, String offr_auth, String offrs_posted,
			String offrs_defi, String offrs_sur, String jco_auth, String jco_posted, String jco_defi, String jco_sur,
			String or_auth, String or_posted, String or_defi, String or_sur, String civ_auth, String civ_posted,
			String civ_defi, String civ_sur) throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = dataSource.getConnection();
//				  try {
		conn.setAutoCommit(false);
		int val = 0;
		int count = 0;
		String q = "";

		q = "INSERT INTO tb_psg_summary_3009 \r\n"
				+ "				(sus_no,month,year,offrs_auth,offrs_posted,offrs_sur,offrs_defi,jco_auth,jco_posted,jco_sur,jco_defi,\r\n"
				+ "				or_auth,or_posted,or_sur,or_defi,civ_auth,civ_posted,civ_sur,civ_defi) \r\n"
				+ "				VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) \r\n";

		pstmt = conn.prepareStatement(q);
		pstmt.setString(1, sus_no);
		pstmt.setString(2, month);
		pstmt.setString(3, year);
		pstmt.setString(4, offr_auth);
		pstmt.setString(5, offrs_posted);
		pstmt.setString(6, offrs_sur);
		pstmt.setString(7, offrs_defi);
		pstmt.setString(8, jco_auth);
		pstmt.setString(9, jco_posted);
		pstmt.setString(10, jco_sur);
		pstmt.setString(11, jco_defi);
		pstmt.setString(12, or_auth);
		pstmt.setString(13, or_posted);
		pstmt.setString(14, or_sur);
		pstmt.setString(15, or_defi);
		pstmt.setString(16, civ_auth);
		pstmt.setString(17, civ_posted);
		pstmt.setString(18, civ_sur);
		pstmt.setString(19, civ_defi);
		pstmt.executeUpdate();

		pstmt.close();
		conn.commit();
		conn.close();

		return true;
	}

	public ArrayList<ArrayList<String>> getcommand(String roleSusNo) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String qry = "";

			qry = "select distinct ab.sus_no as command,\r\n" + "             ac.sus_no as corps,\r\n"
					+ "             ad.sus_no as division,\r\n" + "             ae.sus_no as brigade,\r\n"
					+ "             ab.cmd_name as cmd_unit,\r\n" + "             ac.coprs_name as corp_unit,\r\n"
					+ "             ad.div_name as div_unit,\r\n" + "             ae.bde_name as bde_unit\r\n"
					+ "   from tb_miso_orbat_unt_dtl a\r\n" + "   left join orbat_view_corps ac\r\n"
					+ "       on substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
					+ "   left join orbat_view_cmd ab\r\n"
					+ "       on substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
					+ "   left join orbat_view_div ad\r\n"
					+ "       on substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
					+ "   left join orbat_view_bde ae\r\n"
					+ "       on substr(a.form_code_control::text, 7, 4) = ae.bde_code::text\r\n"
					+ " where status_sus_no = 'Active'\r\n" + "     and a.sus_no= ? ";
			stmt = conn.prepareStatement(qry);
			stmt.setString(1, roleSusNo);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
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

	public ArrayList<ArrayList<String>> getEstablishNo(String roleSusNo) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String qry = "";

			qry = "select distinct COALESCE(s.we_pe_no,'') as we_pe_no \r\n" + "from sus_pers_stdauth s\r\n"
					+ "left join logininformation l on s.sus_no = l.user_sus_no\r\n" + "where s.sus_no = ? ";
			stmt = conn.prepareStatement(qry);
			stmt.setString(1, roleSusNo);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("we_pe_no"));
				alist.add(list);

			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

}
