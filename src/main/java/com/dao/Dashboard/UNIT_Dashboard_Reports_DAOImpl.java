package com.dao.Dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.persistance.util.HibernateUtil;

public class UNIT_Dashboard_Reports_DAOImpl implements UNIT_Dashboard_Reports_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Override
	public ArrayList<ArrayList<String>> getUnitReportPersOffrs(String roleSusNo,String formationtype) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				q = "SELECT DISTINCT appmst.description,\r\n"
						+ "\r\n"
						+ "       c.personnel_no AS ic_no,\r\n"
						+ "       c.name AS name,\r\n"
						+ "       ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,\r\n"
						+ "       ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,\r\n"
						+ "       ltrim(to_char(c.date_of_commission,'DD-Mon-YYYY'),'0') AS doc,\r\n"
						+ "       ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\r\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') AS dom,\r\n"
						+ "       course.army_courses,\r\n"
						+ "       ma.maiden_name AS spouse_name,\r\n"
						+ "       degree.civil_qualification,\r\n"
						+ "       rwd.rank AS rank,\r\n"
						+ "       rwd.rank_code,\r\n"
						+ "       med.med_cat,\r\n"
						+ "       med.cope,\r\n"
						+ "		  unt.unit_name\r\n"
						+ "  FROM tb_psg_trans_proposed_comm_letter c\r\n"
						+ " INNER JOIN cue_tb_psg_rank_app_master r\r\n"
						+ "    ON c.rank = r.id\r\n"
						+ "   AND c.status IN ('1', '5')\r\n"
						+ "   AND upper(r.level_in_hierarchy) = 'RANK'\r\n"
						+ " INNER JOIN tb_psg_iaff_3008_rank_wise_data rwd\r\n"
						+ "    ON r.code = rwd.rank_code\r\n"
						+ " Inner join tb_miso_orbat_unt_dtl unt\r\n"
						+ "   on unt.sus_no = c.unit_sus_no\r\n"
						+ "	 and unt.status_sus_no ='Active'\r\n"
						+ "  LEFT JOIN tb_psg_change_of_appointment apoint\r\n"
						+ "    ON apoint.comm_id = c.id\r\n"
						+ "   AND apoint.status = 1\r\n"
						+ "  LEFT JOIN cue_tb_psg_rank_app_master appmst\r\n"
						+ "    ON appmst.id = apoint.appointment\r\n"
						+ "  LEFT JOIN tb_psg_census_family_married ma\r\n"
						+ "    ON ma.comm_id = c.id\r\n"
						+ "   AND ma.status = 1\r\n"
						+ "  LEFT JOIN tb_miso_orbat_arm_code e\r\n"
						+ "    ON e.arm_code = c.parent_arm\r\n"
						+ "  LEFT JOIN (\r\n"
						+ "        SELECT string_agg(DISTINCT deg.degree, ', ') AS civil_qualification,\r\n"
						+ "               qua.comm_id\r\n"
						+ "          FROM tb_psg_trans_proposed_comm_letter c\r\n"
						+ "         INNER JOIN tb_psg_census_qualification qua\r\n"
						+ "            ON qua.comm_id = c.id\r\n"
						+ "           AND qua.status = 1\r\n"
						+ "         INNER JOIN tb_psg_mstr_degree deg\r\n"
						+ "            ON deg.id = qua.degree\r\n"
						+ "           AND deg.status = 'active'\r\n"
						+ "         WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\r\n"
						+ "         GROUP BY qua.comm_id\r\n"
						+ "       ) degree\r\n"
						+ "    ON degree.comm_id = c.id\r\n"
						+ "  LEFT JOIN (\r\n"
						+ "        SELECT DISTINCT 'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\r\n"
						+ "               'C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || ';O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') AS cope,\r\n"
						+ "               med_main.comm_id\r\n"
						+ "          FROM tb_psg_medical_category med_main\r\n"
						+ "         INNER JOIN tb_psg_trans_proposed_comm_letter c\r\n"
						+ "            ON c.id = med_main.comm_id\r\n"
						+ "         WHERE med_main.status = '1'\r\n"
						+ "           AND c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\r\n"
						+ "         GROUP BY med_main.comm_id\r\n"
						+ "       ) med\r\n"
						+ "    ON c.id = med.comm_id\r\n"
						+ "  LEFT JOIN (\r\n"
						+ "        SELECT string_agg(DISTINCT cour.course_name, ', ') AS army_courses,\r\n"
						+ "               cour.comm_id\r\n"
						+ "          FROM tb_psg_census_army_course cour\r\n"
						+ "         INNER JOIN tb_psg_trans_proposed_comm_letter c\r\n"
						+ "            ON c.id = cour.comm_id\r\n"
						+ "         WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\r\n"
						+ "         GROUP BY cour.comm_id\r\n"
						+ "       ) course\r\n"
						+ "    ON course.comm_id = c.id\r\n"
						+ "  LEFT JOIN (\r\n"
						+ "        SELECT max(dt_of_tos) AS dt_of_tos,\r\n"
						+ "               comm_id AS comm_id\r\n"
						+ "          FROM tb_psg_posting_in_out\r\n"
						+ "         GROUP BY comm_id\r\n"
						+ "       ) tos\r\n"
						+ "    ON tos.comm_id = c.id\r\n"
						+ " WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\r\n"
						+ " ORDER BY rwd.rank_code ASC";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
				stmt.setString(2, from_code_control);
				stmt.setString(3, from_code_control);
				stmt.setString(4, from_code_control);



			}else{

				q = "SELECT DISTINCT appmst.description,\r\n"
						+ "       c.personnel_no AS ic_no,\r\n"
						+ "       c.name AS name,\r\n"
						+ "       ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,\r\n"
						+ "       ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,\r\n"
						+ "       ltrim(to_char(c.date_of_commission,'DD-Mon-YYYY'),'0') AS doc,\r\n"
						+ "       ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\r\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') AS dom,\r\n"
						+ "       course.army_courses,\r\n"
						+ "       ma.maiden_name AS spouse_name,\r\n"
						+ "       degree.civil_qualification,\r\n"
						+ "       rwd.rank AS rank,\r\n"
						+ "       rwd.rank_code,\r\n"
						+ "       med.med_cat,\r\n"
						+ "       med.cope,\r\n"
						+ "		  unt.unit_name\r\n"
						+ "  FROM tb_psg_trans_proposed_comm_letter c\r\n"
						+ " INNER JOIN cue_tb_psg_rank_app_master r\r\n"
						+ "    ON c.rank = r.id\r\n"
						+ "   AND c.status IN ('1', '5')\r\n"
						+ "   AND upper(r.level_in_hierarchy) = 'RANK'\r\n"
						+ " INNER JOIN tb_psg_iaff_3008_rank_wise_data rwd\r\n"
						+ "    ON r.code = rwd.rank_code\r\n"
						+ "Inner join tb_miso_orbat_unt_dtl unt\r\n"
						+ "   on unt.sus_no = c.unit_sus_no\r\n"
						+ "	 and unt.status_sus_no ='Active'\r\n"
						+ "  LEFT JOIN tb_psg_change_of_appointment apoint\r\n"
						+ "    ON apoint.comm_id = c.id\r\n"
						+ "   AND apoint.status = 1\r\n"
						+ "  LEFT JOIN cue_tb_psg_rank_app_master appmst\r\n"
						+ "    ON appmst.id = apoint.appointment\r\n"
						+ "  LEFT JOIN tb_psg_census_family_married ma\r\n"
						+ "    ON ma.comm_id = c.id\r\n"
						+ "   AND ma.status = 1\r\n"
						+ "  LEFT JOIN tb_miso_orbat_arm_code e\r\n"
						+ "    ON e.arm_code = c.parent_arm\r\n"
						+ "  LEFT JOIN (\r\n"
						+ "        SELECT string_agg(DISTINCT deg.degree, ', ') AS civil_qualification,\r\n"
						+ "               qua.comm_id\r\n"
						+ "          FROM tb_psg_trans_proposed_comm_letter c\r\n"
						+ "         INNER JOIN tb_psg_census_qualification qua\r\n"
						+ "            ON qua.comm_id = c.id\r\n"
						+ "           AND qua.status = 1\r\n"
						+ "         INNER JOIN tb_psg_mstr_degree deg\r\n"
						+ "            ON deg.id = qua.degree\r\n"
						+ "           AND deg.status = 'active'\r\n"
						+ "         WHERE c.unit_sus_no IN (?)\r\n"
						+ "         GROUP BY qua.comm_id\r\n"
						+ "       ) degree\r\n"
						+ "    ON degree.comm_id = c.id\r\n"
						+ "  LEFT JOIN (\r\n"
						+ "        SELECT DISTINCT 'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\r\n"
						+ "               'C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || ';O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') AS cope,\r\n"
						+ "               med_main.comm_id\r\n"
						+ "          FROM tb_psg_medical_category med_main\r\n"
						+ "         INNER JOIN tb_psg_trans_proposed_comm_letter c\r\n"
						+ "            ON c.id = med_main.comm_id\r\n"
						+ "         WHERE med_main.status = '1'\r\n"
						+ "           AND c.unit_sus_no IN (?)\r\n"
						+ "         GROUP BY med_main.comm_id\r\n"
						+ "       ) med\r\n"
						+ "    ON c.id = med.comm_id\r\n"
						+ "  LEFT JOIN (\r\n"
						+ "        SELECT string_agg(DISTINCT cour.course_name, ', ') AS army_courses,\r\n"
						+ "               cour.comm_id\r\n"
						+ "          FROM tb_psg_census_army_course cour\r\n"
						+ "         INNER JOIN tb_psg_trans_proposed_comm_letter c\r\n"
						+ "            ON c.id = cour.comm_id\r\n"
						+ "         WHERE c.unit_sus_no IN (?)\r\n"
						+ "         GROUP BY cour.comm_id\r\n"
						+ "       ) course\r\n"
						+ "    ON course.comm_id = c.id\r\n"
						+ "  LEFT JOIN (\r\n"
						+ "        SELECT max(dt_of_tos) AS dt_of_tos,\r\n"
						+ "               comm_id AS comm_id\r\n"
						+ "          FROM tb_psg_posting_in_out\r\n"
						+ "         GROUP BY comm_id\r\n"
						+ "       ) tos\r\n"
						+ "    ON tos.comm_id = c.id\r\n"
						+ " WHERE c.unit_sus_no in (?)\r\n"
						+ " ORDER BY rwd.rank_code ASC";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
				stmt.setString(2, roleSusNo);
				stmt.setString(3, roleSusNo);
				stmt.setString(4, roleSusNo);


			}



			ResultSet rs = stmt.executeQuery();
			System.out.println("Offr Held PDF Qry --> " + stmt);
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("ic_no"));// 1
				list.add(rs.getString("rank"));// 2
				list.add(rs.getString("name"));// 3
				list.add(rs.getString("description"));// 4
				list.add(rs.getString("dob"));// 5
				list.add(rs.getString("dos"));// 6
				list.add(rs.getString("doc"));// 7
				list.add(rs.getString("tos"));// 8
				list.add(rs.getString("med_cat"));// 9
				list.add(rs.getString("cope"));// 10
				list.add(rs.getString("army_courses"));// 11
				list.add(rs.getString("civil_qualification"));// 12
				list.add(rs.getString("spouse_name"));// 13
				list.add(rs.getString("dom"));// 14

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
	public ArrayList<ArrayList<String>> getUnitReportPersOffrsAuth(String roleSusNo,String formationtype) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;


			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				q = " SELECT coalesce(sum(n.base_auth),'0') AS base_auth,\n"
						+ "       coalesce(sum(n.mod_auth),'0') AS mod_auth,\n"
						+ "       coalesce(sum(n.foot_auth),'0') AS foot_auth,\n"
						+ "       coalesce(sum(base_auth + mod_auth + foot_auth),'0') AS total_auth,\n"
						+ "       coalesce(description,'0') AS rank,\n" + "       r.id AS rank_id,\n"
						+ "       b.arm_desc\n" + "  FROM sus_pers_stdauth n\n"
						+ " INNER JOIN cue_tb_psg_rank_app_master r\n" + "    ON r.code = n.rank_code\n"
						+ "   AND upper(r.level_in_hierarchy) = upper('Rank')\n" + "  LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "    ON b.arm_code = n.arm\n" + " WHERE parent_code = '0'\n" + "   AND sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')" + " \n"
						+ " GROUP BY rank_id,\n" + "          b.arm_desc";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);


			}else{


				q = " SELECT coalesce(sum(n.base_auth),'0') AS base_auth,\n"
						+ "       coalesce(sum(n.mod_auth),'0') AS mod_auth,\n"
						+ "       coalesce(sum(n.foot_auth),'0') AS foot_auth,\n"
						+ "       coalesce(sum(base_auth + mod_auth + foot_auth),'0') AS total_auth,\n"
						+ "       coalesce(description,'0') AS rank,\n" + "       r.id AS rank_id,\n"
						+ "       b.arm_desc\n" + "  FROM sus_pers_stdauth n\n"
						+ " INNER JOIN cue_tb_psg_rank_app_master r\n" + "    ON r.code = n.rank_code\n"
						+ "   AND upper(r.level_in_hierarchy) = upper('Rank')\n" + "  LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "    ON b.arm_code = n.arm\n" + " WHERE parent_code = '0'\n" + "   AND sus_no = ?" + " \n"
						+ " GROUP BY rank_id,\n" + "          b.arm_desc";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
			}



			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("arm_desc"));// 1
				list.add(rs.getString("rank"));// 2
				list.add(rs.getString("base_auth"));// 3
				list.add(rs.getString("mod_auth"));// 4
				list.add(rs.getString("foot_auth"));// 5
				list.add(rs.getString("total_auth"));// 6

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
	public ArrayList<ArrayList<String>> getUnitReportPersJCo(String roleSusNo,String formationtype) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;


			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				q = "  SELECT  c.army_no,\n"
						+ "       r.rank AS rk,\n"
						+ "       c.full_name AS name,\n"
						+ "       t.trade AS trade,\n"
						+ "       ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,\n"
						+ "       ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,\n"
						+ "       ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0') AS doe,\n"
						+ "       ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\n"
						+ "       'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\n"
						+ "         'C_E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') || 'C_O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || 'C_C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || 'C_P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') AS cope,\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY')) AS dom,\n"
						+ "       string_agg(distinct cour.course_name, ', ') army_courses,\n"
						+ "       ma.maiden_name AS spousename,\n"
						+ "		  orb.unit_name as unit_name\r\n"
						+ "  FROM tb_psg_census_jco_or_p c\n"
						+ " INNER JOIN tb_psg_mstr_rank_jco r\n"
						+ "    ON c.rank = r.id\n"
						+ "   AND c.status = '1'\n"
						+ " INNER JOIN tb_psg_mstr_trade_jco t\n"
						+ "    ON t.id = c.trade\n"
						+ "INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "	ON c.unit_sus_no = orb.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "  LEFT JOIN tb_miso_orbat_arm_code e\n"
						+ "    ON e.arm_code = c.arm_service\n"
						+ "  LEFT JOIN tb_psg_medical_category_jco med_main\n"
						+ "    ON med_main.jco_id = c.id\n"
						+ "   AND med_main.status IN ('1','2')\n"
						+ "  LEFT JOIN tb_psg_census_army_course_jco cour\n"
						+ "    ON cour.jco_id = c.id\n"
						+ "  LEFT JOIN tb_psg_census_family_married_jco ma\n"
						+ "    ON ma.jco_id = c.id\n"
						+ " WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ " AND  c.category='JCO' "
						+  "  GROUP BY 1,2, 3, 4,5,6,7,8,\n" + "   ma.marriage_date,\n"
						+ "          ma.maiden_name, orb.unit_name" ;

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);


			}else{

				q = "  SELECT  c.army_no,\n"
						+ "       r.rank AS rk,\n"
						+ "       c.full_name AS name,\n"
						+ "       t.trade AS trade,\n"
						+ "       ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,\n"
						+ "       ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,\n"
						+ "       ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0') AS doe,\n"
						+ "       ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\n"
						+ "       'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\n"
						+ "         'C_E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') || 'C_O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || 'C_C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || 'C_P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') AS cope,\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY')) AS dom,\n"
						+ "       string_agg(distinct cour.course_name, ', ') army_courses,\n"
						+ "       ma.maiden_name AS spousename,\n"
						+ "		  orb.unit_name as unit_name\r\n"
						+ "  FROM tb_psg_census_jco_or_p c\n"
						+ " INNER JOIN tb_psg_mstr_rank_jco r\n"
						+ "    ON c.rank = r.id\n"
						+ "   AND c.status = '1'\n"
						+ " INNER JOIN tb_psg_mstr_trade_jco t\n"
						+ "    ON t.id = c.trade\n"
						+ "INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "	ON c.unit_sus_no = orb.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "  LEFT JOIN tb_miso_orbat_arm_code e\n"
						+ "    ON e.arm_code = c.arm_service\n"
						+ "  LEFT JOIN tb_psg_medical_category_jco med_main\n"
						+ "    ON med_main.jco_id = c.id\n"
						+ "   AND med_main.status IN ('1','2')\n"
						+ "  LEFT JOIN tb_psg_census_army_course_jco cour\n"
						+ "    ON cour.jco_id = c.id\n"
						+ "  LEFT JOIN tb_psg_census_family_married_jco ma\n"
						+ "    ON ma.jco_id = c.id\n"
						+ " WHERE c.unit_sus_no = ?\n"
						+ " AND  c.category='JCO' "
						+  "  GROUP BY 1,2, 3, 4,5,6,7,8,\n" + "   ma.marriage_date,\n"
						+ "          ma.maiden_name, orb.unit_name" ;

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}

			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("army_no"));// 2
				list.add(rs.getString("rk"));// 3
				list.add(rs.getString("name"));// 4
				list.add(rs.getString("trade"));// 5
				list.add(rs.getString("dob"));// 6
				list.add(rs.getString("dos"));// 7
				list.add(rs.getString("doe"));// 8
				list.add(rs.getString("tos"));// 9
				list.add(rs.getString("med_cat"));// 10
				list.add(rs.getString("army_courses"));// 11
				list.add(rs.getString("spousename"));// 12
				list.add(rs.getString("dom"));// 13

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
	public ArrayList<ArrayList<String>> getUnitReportPersCivAuth(String roleSusNo,String formationtype) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;


			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				q = "SELECT coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '4'),0) AS gp_a_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '5'),0) AS gp_b_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('6','9')),0) AS gp_b_non_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('7','8','10','11','12')),0) AS gp_c_non_gaz\n"
						+ "  FROM sus_pers_stdauth a\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ "   AND a.cat_id IN ('1','2')";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);



			}else{

				q = "SELECT coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '4'),0) AS gp_a_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '5'),0) AS gp_b_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('6','9')),0) AS gp_b_non_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('7','8','10','11','12')),0) AS gp_c_non_gaz\n"
						+ "  FROM sus_pers_stdauth a\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no = ?\n"
						+ "   AND a.cat_id IN ('1','2')";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}


			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("gp_a_gaz"));// 1
				list.add(rs.getString("gp_b_gaz"));// 2
				list.add(rs.getString("gp_b_non_gaz"));// 3
				list.add(rs.getString("gp_c_non_gaz"));// 4
				list.add("");

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
	public ArrayList<ArrayList<String>> getUnitReportPersCivs(String roleSusNo,String formationtype) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;


			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				q = "SELECT c.employee_no,\n"
						+ "       c.first_name || ' ' || c.middle_name || ' ' || c.last_name AS emp_name,\n"
						+ "       ltrim(to_char(c.dob,'DD-MON-YYYY'),'0') AS dob,\n"
						+ "       t.label AS classification_services,\n"
						+ "       c.civ_group,\n"
						+ "       t1.label AS civ_type,\n"
						+ "       b.description as designation,\n"
						+ "       ltrim(to_char(c.joining_date_gov_service,'DD-Mon-YYYY'),'0') AS joining_date_gov_service,\n"
						+ "       ltrim(to_char(c.designation_date,'DD-Mon-YYYY'),'0') AS designation_date,\n"
						+ "       g.gender_name, orb.unit_name as unit_name\n"
						+ "  FROM tb_psg_civilian_dtl c\n"
						+ " INNER JOIN tb_miso_orbat_unt_dtl orb\n"
						+ "    ON orb.sus_no = c.sus_no\n"
						+ "   AND orb.status_sus_no = 'Active'\n"
						+ " INNER JOIN t_domain_value t1\n"
						+ "    ON t1.codevalue = c.civ_type\n"
						+ "   AND t1.domainid = 'CIVILIAN_TYPE'\n"
						+ " INNER JOIN tb_psg_mstr_gender g\n"
						+ "    ON g.id = c.gender\n"
						+ " INNER JOIN t_domain_value t\n"
						+ "    ON t.codevalue = c.classification_services\n"
						+ "   AND t.domainid = 'CLASSIFICATION_OF_SERVICES'\n"
						+ "    INNER JOIN cue_tb_psg_rank_app_master b\n"
						+ "    ON c.designation = b.id\n"
						+ "   AND upper(b.level_in_hierarchy) = upper('Appointment')\n"
						+ "   AND b.parent_code IN ('4','5','6','7','8','9','10','11')\n"
						+ "   AND b.status_active = 'Active'\n"
						+ " WHERE c.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')   AND c.status = 1   AND civilian_status = 'R' \n";


				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);


			}else{


				q = "SELECT c.employee_no,\n"
						+ "       c.first_name || ' ' || c.middle_name || ' ' || c.last_name AS emp_name,\n"
						+ "       ltrim(to_char(c.dob,'DD-MON-YYYY'),'0') AS dob,\n"
						+ "       t.label AS classification_services,\n"
						+ "       c.civ_group,\n"
						+ "       t1.label AS civ_type,\n"
						+ "       b.description as designation,\n"
						+ "       ltrim(to_char(c.joining_date_gov_service,'DD-Mon-YYYY'),'0') AS joining_date_gov_service,\n"
						+ "       ltrim(to_char(c.designation_date,'DD-Mon-YYYY'),'0') AS designation_date,\n"
						+ "       g.gender_name, orb.unit_name as unit_name\n"
						+ "  FROM tb_psg_civilian_dtl c\n"
						+ " INNER JOIN tb_miso_orbat_unt_dtl orb\n"
						+ "    ON orb.sus_no = c.sus_no\n"
						+ "   AND orb.status_sus_no = 'Active'\n"
						+ " INNER JOIN t_domain_value t1\n"
						+ "    ON t1.codevalue = c.civ_type\n"
						+ "   AND t1.domainid = 'CIVILIAN_TYPE'\n"
						+ " INNER JOIN tb_psg_mstr_gender g\n"
						+ "    ON g.id = c.gender\n"
						+ " INNER JOIN t_domain_value t\n"
						+ "    ON t.codevalue = c.classification_services\n"
						+ "   AND t.domainid = 'CLASSIFICATION_OF_SERVICES'\n"
						+ "    INNER JOIN cue_tb_psg_rank_app_master b\n"
						+ "    ON c.designation = b.id\n"
						+ "   AND upper(b.level_in_hierarchy) = upper('Appointment')\n"
						+ "   AND b.parent_code IN ('4','5','6','7','8','9','10','11')\n"
						+ "   AND b.status_active = 'Active'\n"
						+ " WHERE c.sus_no = ?   AND c.status = 1   AND civilian_status = 'R' \n";


				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
			}



			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("employee_no"));// 2
				list.add("designation");// 3
				list.add(rs.getString("emp_name"));// 4
				list.add(rs.getString("classification_services"));// 5
				list.add(rs.getString("civ_type"));// 6
				list.add(rs.getString("dob"));// 7
				list.add(rs.getString("designation_date"));// 8
				list.add(rs.getString("joining_date_gov_service"));// 9
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
	public ArrayList<ArrayList<String>> getUnitReportPersJcoAuth(String roleSusNo,String formationtype) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;


			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				q = "SELECT a.sus_no,\n" + "       a.cat_id,\n" + "       c.label AS per_category,\n" + "       a.arm,\n"
						+ "       b.arm_desc,\n" + "       coalesce(description,'0') AS rank,\n"
						+ "       r.id AS rank_id,\n" + "       coalesce(sum(a.base_auth),'0') AS base_auth,\n"
						+ "       coalesce(sum(a.mod_auth),'0') AS mod_auth,\n"
						+ "       coalesce(sum(a.foot_auth),'0') AS foot_auth,\n"
						+ "       coalesce(sum(base_auth + mod_auth + foot_auth),'0') AS total_auth\n"
						+ "  FROM sus_pers_stdauth a\n" + " INNER JOIN cue_tb_psg_rank_app_master r\n"
						+ "    ON r.code = a.rank_code\n" + "  LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "    ON b.arm_code = a.arm\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ "   AND a.rank_cat = '1' \n"
						+ " GROUP BY a.sus_no,a.cat_id, c.label,a.arm, b.arm_desc, description,r.id\n";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);




			}else{


				q = "SELECT a.sus_no,\n" + "       a.cat_id,\n" + "       c.label AS per_category,\n" + "       a.arm,\n"
						+ "       b.arm_desc,\n" + "       coalesce(description,'0') AS rank,\n"
						+ "       r.id AS rank_id,\n" + "       coalesce(sum(a.base_auth),'0') AS base_auth,\n"
						+ "       coalesce(sum(a.mod_auth),'0') AS mod_auth,\n"
						+ "       coalesce(sum(a.foot_auth),'0') AS foot_auth,\n"
						+ "       coalesce(sum(base_auth + mod_auth + foot_auth),'0') AS total_auth\n"
						+ "  FROM sus_pers_stdauth a\n" + " INNER JOIN cue_tb_psg_rank_app_master r\n"
						+ "    ON r.code = a.rank_code\n" + "  LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "    ON b.arm_code = a.arm\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no = ?\n"
						+ "   AND a.rank_cat = '1' \n"
						+ " GROUP BY a.sus_no,a.cat_id, c.label,a.arm, b.arm_desc, description,r.id\n";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}


			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("arm_desc"));// 1
				list.add(rs.getString("rank"));// 2
				list.add(rs.getString("base_auth"));// 3
				list.add(rs.getString("mod_auth"));// 4
				list.add(rs.getString("foot_auth"));// 5
				list.add(rs.getString("total_auth"));// 6

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
	public ArrayList<ArrayList<String>> getUnitReportPersOR(String roleSusNo,String formationtype) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);


				q = "SELECT c.army_no,\n"
						+ "             r.rank AS rk,\n"
						+ "             c.full_name AS name,\n"
						+ "             t.trade AS trade,         \n"
						+ "               ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,     \n"
						+ "               ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,   \n"
						+ "               ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0') AS doe,\n"
						+ "                 ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\n"
						+ "             'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\n"
						+ "            'C_E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') || 'C_O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || 'C_C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || 'C_P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') AS cope,\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY')) AS dom,\n"
						+ "             string_agg(distinct cour.course_name, ', ') army_courses,\n"
						+ "             ma.maiden_name AS spousename,\n"
						+ "			 orb.unit_name as unit_name\r\n"
						+ "   FROM tb_psg_census_jco_or_p c\n"
						+ " INNER JOIN tb_psg_mstr_rank_jco r\n"
						+ "       ON c.rank = r.id\n"
						+ "     AND c.status = '1'\n"
						+ " INNER JOIN tb_psg_mstr_trade_jco t\n"
						+ "       ON t.id = c.trade\n"
						+ "INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "	ON c.unit_sus_no = orb.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "   LEFT JOIN tb_miso_orbat_arm_code e\n"
						+ "       ON e.arm_code = c.arm_service\n"
						+ "   LEFT JOIN tb_psg_medical_category_jco med_main\n"
						+ "       ON med_main.jco_id = c.id\n"
						+ "     AND med_main.status IN ('1','2')\n"
						+ "   LEFT JOIN tb_psg_census_army_course_jco cour\n"
						+ "       ON cour.jco_id = c.id\n"
						+ "   LEFT JOIN tb_psg_census_family_married_jco ma\n"
						+ "       ON ma.jco_id = c.id\n"
						+ " WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ " AND c.category='OR'  "
						+ " GROUP BY 1,2,3,4, 5, 6,7,8,\n" + "                   ma.marriage_date,\n"
						+ "                   ma.maiden_name, orb.unit_name\n" ;

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);



			}else{



				q = "SELECT c.army_no,\n"
						+ "             r.rank AS rk,\n"
						+ "             c.full_name AS name,\n"
						+ "             t.trade AS trade,         \n"
						+ "               ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,     \n"
						+ "               ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,   \n"
						+ "               ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0') AS doe,\n"
						+ "                 ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\n"
						+ "             'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\n"
						+ "            'C_E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') || 'C_O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || 'C_C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || 'C_P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') AS cope,\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY')) AS dom,\n"
						+ "             string_agg(distinct cour.course_name, ', ') army_courses,\n"
						+ "             ma.maiden_name AS spousename,\n"
						+ "			 orb.unit_name as unit_name\r\n"
						+ "   FROM tb_psg_census_jco_or_p c\n"
						+ " INNER JOIN tb_psg_mstr_rank_jco r\n"
						+ "       ON c.rank = r.id\n"
						+ "     AND c.status = '1'\n"
						+ " INNER JOIN tb_psg_mstr_trade_jco t\n"
						+ "       ON t.id = c.trade\n"
						+ "INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "	ON c.unit_sus_no = orb.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "   LEFT JOIN tb_miso_orbat_arm_code e\n"
						+ "       ON e.arm_code = c.arm_service\n"
						+ "   LEFT JOIN tb_psg_medical_category_jco med_main\n"
						+ "       ON med_main.jco_id = c.id\n"
						+ "     AND med_main.status IN ('1','2')\n"
						+ "   LEFT JOIN tb_psg_census_army_course_jco cour\n"
						+ "       ON cour.jco_id = c.id\n"
						+ "   LEFT JOIN tb_psg_census_family_married_jco ma\n"
						+ "       ON ma.jco_id = c.id\n"
						+ " WHERE c.unit_sus_no = ?\n"
						+ " AND c.category='OR'  "
						+ " GROUP BY 1,2,3,4, 5, 6,7,8,\n" + "                   ma.marriage_date,\n"
						+ "                   ma.maiden_name, orb.unit_name\n" ;

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
			}


			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("army_no"));// 2
				list.add(rs.getString("rk"));// 3
				list.add(rs.getString("name"));// 4
				list.add(rs.getString("trade"));// 5
				list.add(rs.getString("dob"));// 6
				list.add(rs.getString("dos"));// 7
				list.add(rs.getString("doe"));// 8
				list.add(rs.getString("tos"));// 9
				list.add(rs.getString("med_cat"));// 10
				//list.add(rs.getString("cope"));// 11
				list.add(rs.getString("army_courses"));// 12
				list.add(rs.getString("spousename"));// 13
				list.add(rs.getString("dom"));// 14

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
	public ArrayList<ArrayList<String>> getUnitReportPersOrAuth(String roleSusNo,String formationtype) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;


			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);
				q = "SELECT a.sus_no,\n" + "       a.cat_id,\n" + "       c.label AS per_category,\n" + "       a.arm,\n"
						+ "       b.arm_desc,\n" + "       coalesce(description,'0') AS rank,\n"
						+ "       r.id AS rank_id,\n" + "       coalesce(sum(a.base_auth),'0') AS base_auth,\n"
						+ "       coalesce(sum(a.mod_auth),'0') AS mod_auth,\n"
						+ "       coalesce(sum(a.foot_auth),'0') AS foot_auth,\n"
						+ "       coalesce(sum(base_auth + mod_auth + foot_auth),'0') AS total_auth\n"
						+ "  FROM sus_pers_stdauth a\n" + " INNER JOIN cue_tb_psg_rank_app_master r\n"
						+ "    ON r.code = a.rank_code\n" + "  LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "    ON b.arm_code = a.arm\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ "   AND a.rank_cat IN ('2','3') \n"
						+ " GROUP BY a.sus_no, a.cat_id,c.label, a.arm,b.arm_desc,description,r.id\n";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);




			}else{

				q = "SELECT a.sus_no,\n" + "       a.cat_id,\n" + "       c.label AS per_category,\n" + "       a.arm,\n"
						+ "       b.arm_desc,\n" + "       coalesce(description,'0') AS rank,\n"
						+ "       r.id AS rank_id,\n" + "       coalesce(sum(a.base_auth),'0') AS base_auth,\n"
						+ "       coalesce(sum(a.mod_auth),'0') AS mod_auth,\n"
						+ "       coalesce(sum(a.foot_auth),'0') AS foot_auth,\n"
						+ "       coalesce(sum(base_auth + mod_auth + foot_auth),'0') AS total_auth\n"
						+ "  FROM sus_pers_stdauth a\n" + " INNER JOIN cue_tb_psg_rank_app_master r\n"
						+ "    ON r.code = a.rank_code\n" + "  LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "    ON b.arm_code = a.arm\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no = ?\n"
						+ "   AND a.rank_cat IN ('2','3') \n"
						+ " GROUP BY a.sus_no, a.cat_id,c.label, a.arm,b.arm_desc,description,r.id\n";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}


			//
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("arm_desc"));// 1
				list.add(rs.getString("rank"));// 2
				list.add(rs.getString("base_auth"));// 3
				list.add(rs.getString("mod_auth"));// 4
				list.add(rs.getString("foot_auth"));// 5
				list.add(rs.getString("total_auth"));// 6

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
	public ArrayList<ArrayList<String>> getUnitReportVehAuth(String type_veh, String roleSusNo,String formationtype) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String SearchValue = "";


			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				if (type_veh.equals("A")) {

					q = " SELECT DISTINCT m.mct_nomen,\n" + "       a.mct_no,\n" + "       a.base::int,\n"
							+ "       a.mod::int AS modification,\n" + "       a.gennotes::int,\n"
							+ "       a.inlieu::int,\n" + "       a.reducedueinlieu::int,\n" + "       a.total::int\n"
							+ "  FROM (\n" + "        SELECT a.mct_no,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'BASEAUTH'::text THEN a.auth_amt ELSE 0::bigint END) AS base,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'MOD'::text THEN a.auth_amt ELSE 0::bigint END) AS MOD,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'INCDEC'::text THEN a.auth_amt ELSE 0::bigint END) AS gennotes,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'INLIEU'::text THEN a.auth_amt ELSE 0::bigint END) AS inlieu,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'REDUCEDUEINLIEU'::text THEN a.auth_amt ELSE 0::bigint END) AS reducedueinlieu,\n"
							+ "               sum(a.auth_amt) AS total\n" + "          FROM (\n"
							+ "                SELECT a_1.mct_no,\n" + "                       a_1.auth_amt,\n"
							+ "                       ' '::text AS\n" + "             CONDITION,\n"
							+ "                       'BASEAUTH'::text AS typeofauth\n"
							+ "                  FROM cue_tb_miso_wepe_transport_det a_1\n"
							+ "                 INNER JOIN cue_tb_wepe_link_sus_perstransweapon b_1\n"
							+ "                    ON a_1.we_pe_no::text = b_1.wepe_trans_no::text\n"
							+ "                   AND b_1.status_trans::text = '1'::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                   AND b_1.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       sum(b_1.amt_inc_dec) AS SUM,\n"
							+ "                       ''::text AS\n" + "             CONDITION,\n"
							+ "                       'MOD'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_mdfs a_1\n"
							+ "                 INNER JOIN cue_tb_miso_wepe_transport_mdfs b_1\n"
							+ "                    ON a_1.modification::text = b_1.modification::text\n"
							+ "                   AND a_1.we_pe_no::text = b_1.we_pe_no::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                 WHERE a_1.status::text = '1'::text\n"
							+ "                   AND a_1.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 GROUP BY a_1.sus_no,\n"
							+ "                          a_1.we_pe_no,\n" + "                          b_1.mct_no\n"
							+ "                 UNION SELECT b_1.in_lieu_mct,\n"
							+ "                       b_1.actual_inlieu_auth,\n" + "                       b_1.condition,\n"
							+ "                       'INLIEU'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '0'::text\n"
							+ "                  JOIN cue_tb_miso_wepe_transport_det c\n"
							+ "                    ON b_1.mct_no::text = c.mct_no::text\n"
							+ "                   AND b_1.we_pe_no::text = c.we_pe_no::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       b_1.amt_inc_dec,\n" + "                       b_1.condition,\n"
							+ "                       'INCDEC'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '1'::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       sum(b_1.actual_inlieu_auth) * '-1'::integer,\n"
							+ "                       ' '::text AS\n" + "             CONDITION,\n"
							+ "                       'REDUCEDUEINLIEU'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '0'::text\n"
							+ "                  JOIN cue_tb_miso_wepe_transport_det c\n"
							+ "                    ON b_1.mct_no::text = c.mct_no::text\n"
							+ "                   AND b_1.we_pe_no::text = c.we_pe_no::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 GROUP BY a_1.sus_no,\n"
							+ "                          b_1.we_pe_no,\n" + "                          b_1.mct_no\n"
							+ "               ) a\n" + "          JOIN tb_tms_mct_main_master b\n"
							+ "            ON a.mct_no::text = b.mct_main_id::text\n"
							+ "           AND b.type_of_veh::text = 'A'::text\n" + "         GROUP BY a.mct_no\n"
							+ "       ) AS a\n" + " INNER JOIN tb_tms_mct_main_master m\n"
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'A'::text\n" + "";

				}
				if (type_veh.equals("B")) {
					q = " SELECT DISTINCT m.mct_nomen,\n" + "       a.mct_no,\n" + "       a.base::int,\n"
							+ "       a.mod::int AS modification,\n" + "       a.gennotes::int,\n"
							+ "       a.inlieu::int,\n" + "       a.reducedueinlieu::int,\n" + "       a.total::int\n"
							+ "  FROM (\n" + "        SELECT a.mct_no,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'BASEAUTH'::text THEN a.auth_amt ELSE 0::bigint END) AS base,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'MOD'::text THEN a.auth_amt ELSE 0::bigint END) AS MOD,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'INCDEC'::text THEN a.auth_amt ELSE 0::bigint END) AS gennotes,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'INLIEU'::text THEN a.auth_amt ELSE 0::bigint END) AS inlieu,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'REDUCEDUEINLIEU'::text THEN a.auth_amt ELSE 0::bigint END) AS reducedueinlieu,\n"
							+ "               sum(a.auth_amt) AS total\n" + "          FROM (\n"
							+ "                SELECT a_1.mct_no,\n" + "                       a_1.auth_amt,\n"
							+ "                       ' '::text AS\n" + "             CONDITION,\n"
							+ "                       'BASEAUTH'::text AS typeofauth\n"
							+ "                  FROM cue_tb_miso_wepe_transport_det a_1\n"
							+ "                 INNER JOIN cue_tb_wepe_link_sus_perstransweapon b_1\n"
							+ "                    ON a_1.we_pe_no::text = b_1.wepe_trans_no::text\n"
							+ "                   AND b_1.status_trans::text = '1'::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                   AND b_1.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       sum(b_1.amt_inc_dec) AS SUM,\n"
							+ "                       ''::text AS\n" + "             CONDITION,\n"
							+ "                       'MOD'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_mdfs a_1\n"
							+ "                 INNER JOIN cue_tb_miso_wepe_transport_mdfs b_1\n"
							+ "                    ON a_1.modification::text = b_1.modification::text\n"
							+ "                   AND a_1.we_pe_no::text = b_1.we_pe_no::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                 WHERE a_1.status::text = '1'::text\n"
							+ "                   AND a_1.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 GROUP BY a_1.sus_no,\n"
							+ "                          a_1.we_pe_no,\n" + "                          b_1.mct_no\n"
							+ "                 UNION SELECT b_1.in_lieu_mct,\n"
							+ "                       b_1.actual_inlieu_auth,\n" + "                       b_1.condition,\n"
							+ "                       'INLIEU'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '0'::text\n"
							+ "                  JOIN cue_tb_miso_wepe_transport_det c\n"
							+ "                    ON b_1.mct_no::text = c.mct_no::text\n"
							+ "                   AND b_1.we_pe_no::text = c.we_pe_no::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       b_1.amt_inc_dec,\n" + "                       b_1.condition,\n"
							+ "                       'INCDEC'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '1'::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       sum(b_1.actual_inlieu_auth) * '-1'::integer,\n"
							+ "                       ' '::text AS\n" + "             CONDITION,\n"
							+ "                       'REDUCEDUEINLIEU'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '0'::text\n"
							+ "                  JOIN cue_tb_miso_wepe_transport_det c\n"
							+ "                    ON b_1.mct_no::text = c.mct_no::text\n"
							+ "                   AND b_1.we_pe_no::text = c.we_pe_no::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 GROUP BY a_1.sus_no,\n"
							+ "                          b_1.we_pe_no,\n" + "                          b_1.mct_no\n"
							+ "               ) a\n" + "          JOIN tb_tms_mct_main_master b\n"
							+ "            ON a.mct_no::text = b.mct_main_id::text\n"
							+ "           AND b.type_of_veh::text = 'B'::text\n" + "         GROUP BY a.mct_no\n"
							+ "       ) AS a\n" + " INNER JOIN tb_tms_mct_main_master m\n"
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'B'::text ";

				}
				if (type_veh.equals("C")) {
					q = "SELECT DISTINCT m.mct_nomen,\n" + "       a.mct_no,\n" + "       a.base::int,\n"
							+ "       a.mod::int AS modification,\n" + "       a.gennotes::int,\n"
							+ "       a.inlieu::int,\n" + "       a.reducedueinlieu::int,\n" + "       a.total::int\n"
							+ "  FROM (\n" + "        SELECT a.mct_no,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'BASEAUTH'::text THEN a.auth_amt ELSE 0::bigint END) AS base,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'MOD'::text THEN a.auth_amt ELSE 0::bigint END) AS MOD,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'INCDEC'::text THEN a.auth_amt ELSE 0::bigint END) AS gennotes,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'INLIEU'::text THEN a.auth_amt ELSE 0::bigint END) AS inlieu,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'REDUCEDUEINLIEU'::text THEN a.auth_amt ELSE 0::bigint END) AS reducedueinlieu,\n"
							+ "               sum(a.auth_amt) AS total\n" + "          FROM (\n"
							+ "                SELECT a_1.mct_no,\n" + "                       a_1.auth_amt,\n"
							+ "                       ' '::text AS\n" + "             CONDITION,\n"
							+ "                       'BASEAUTH'::text AS typeofauth\n"
							+ "                  FROM cue_tb_miso_wepe_transport_det a_1\n"
							+ "                 INNER JOIN cue_tb_wepe_link_sus_perstransweapon b_1\n"
							+ "                    ON a_1.we_pe_no::text = b_1.wepe_trans_no::text\n"
							+ "                   AND b_1.status_trans::text = '1'::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                   AND b_1.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       sum(b_1.amt_inc_dec) AS SUM,\n"
							+ "                       ''::text AS\n" + "             CONDITION,\n"
							+ "                       'MOD'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_mdfs a_1\n"
							+ "                 INNER JOIN cue_tb_miso_wepe_transport_mdfs b_1\n"
							+ "                    ON a_1.modification::text = b_1.modification::text\n"
							+ "                   AND a_1.we_pe_no::text = b_1.we_pe_no::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                 WHERE a_1.status::text = '1'::text\n"
							+ "                   AND a_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 GROUP BY a_1.sus_no,\n"
							+ "                          a_1.we_pe_no,\n" + "                          b_1.mct_no\n"
							+ "                 UNION SELECT b_1.in_lieu_mct,\n"
							+ "                       b_1.actual_inlieu_auth,\n" + "                       b_1.condition,\n"
							+ "                       'INLIEU'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '0'::text\n"
							+ "                  JOIN cue_tb_miso_wepe_transport_det c\n"
							+ "                    ON b_1.mct_no::text = c.mct_no::text\n"
							+ "                   AND b_1.we_pe_no::text = c.we_pe_no::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       b_1.amt_inc_dec,\n" + "                       b_1.condition,\n"
							+ "                       'INCDEC'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '1'::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       sum(b_1.actual_inlieu_auth) * '-1'::integer,\n"
							+ "                       ' '::text AS\n" + "             CONDITION,\n"
							+ "                       'REDUCEDUEINLIEU'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '0'::text\n"
							+ "                  JOIN cue_tb_miso_wepe_transport_det c\n"
							+ "                    ON b_1.mct_no::text = c.mct_no::text\n"
							+ "                   AND b_1.we_pe_no::text = c.we_pe_no::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 GROUP BY a_1.sus_no,\n"
							+ "                          b_1.we_pe_no,\n" + "                          b_1.mct_no\n"
							+ "               ) a\n" + "          JOIN tb_tms_mct_main_master b\n"
							+ "            ON a.mct_no::text = b.mct_main_id::text\n"
							+ "           AND b.type_of_veh::text = 'C'::text\n" + "         GROUP BY a.mct_no\n"
							+ "       ) AS a\n" + " INNER JOIN tb_tms_mct_main_master m\n"
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'C'::text ";

				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
				stmt.setString(2, from_code_control);
				stmt.setString(3, from_code_control);
				stmt.setString(4, from_code_control);
				stmt.setString(5, from_code_control);



			}else{

				if (type_veh.equals("A")) {

					q = " SELECT DISTINCT m.mct_nomen,\n" + "       a.mct_no,\n" + "       a.base::int,\n"
							+ "       a.mod::int AS modification,\n" + "       a.gennotes::int,\n"
							+ "       a.inlieu::int,\n" + "       a.reducedueinlieu::int,\n" + "       a.total::int\n"
							+ "  FROM (\n" + "        SELECT a.mct_no,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'BASEAUTH'::text THEN a.auth_amt ELSE 0::bigint END) AS base,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'MOD'::text THEN a.auth_amt ELSE 0::bigint END) AS MOD,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'INCDEC'::text THEN a.auth_amt ELSE 0::bigint END) AS gennotes,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'INLIEU'::text THEN a.auth_amt ELSE 0::bigint END) AS inlieu,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'REDUCEDUEINLIEU'::text THEN a.auth_amt ELSE 0::bigint END) AS reducedueinlieu,\n"
							+ "               sum(a.auth_amt) AS total\n" + "          FROM (\n"
							+ "                SELECT a_1.mct_no,\n" + "                       a_1.auth_amt,\n"
							+ "                       ' '::text AS\n" + "             CONDITION,\n"
							+ "                       'BASEAUTH'::text AS typeofauth\n"
							+ "                  FROM cue_tb_miso_wepe_transport_det a_1\n"
							+ "                 INNER JOIN cue_tb_wepe_link_sus_perstransweapon b_1\n"
							+ "                    ON a_1.we_pe_no::text = b_1.wepe_trans_no::text\n"
							+ "                   AND b_1.status_trans::text = '1'::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                   AND b_1.sus_no = ?\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       sum(b_1.amt_inc_dec) AS SUM,\n"
							+ "                       ''::text AS\n" + "             CONDITION,\n"
							+ "                       'MOD'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_mdfs a_1\n"
							+ "                 INNER JOIN cue_tb_miso_wepe_transport_mdfs b_1\n"
							+ "                    ON a_1.modification::text = b_1.modification::text\n"
							+ "                   AND a_1.we_pe_no::text = b_1.we_pe_no::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                 WHERE a_1.status::text = '1'::text\n"
							+ "                   AND a_1.sus_no = ?\n" + "                 GROUP BY a_1.sus_no,\n"
							+ "                          a_1.we_pe_no,\n" + "                          b_1.mct_no\n"
							+ "                 UNION SELECT b_1.in_lieu_mct,\n"
							+ "                       b_1.actual_inlieu_auth,\n" + "                       b_1.condition,\n"
							+ "                       'INLIEU'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '0'::text\n"
							+ "                  JOIN cue_tb_miso_wepe_transport_det c\n"
							+ "                    ON b_1.mct_no::text = c.mct_no::text\n"
							+ "                   AND b_1.we_pe_no::text = c.we_pe_no::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no = ?\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       b_1.amt_inc_dec,\n" + "                       b_1.condition,\n"
							+ "                       'INCDEC'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '1'::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no = ?\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       sum(b_1.actual_inlieu_auth) * '-1'::integer,\n"
							+ "                       ' '::text AS\n" + "             CONDITION,\n"
							+ "                       'REDUCEDUEINLIEU'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '0'::text\n"
							+ "                  JOIN cue_tb_miso_wepe_transport_det c\n"
							+ "                    ON b_1.mct_no::text = c.mct_no::text\n"
							+ "                   AND b_1.we_pe_no::text = c.we_pe_no::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no = ?\n" + "                 GROUP BY a_1.sus_no,\n"
							+ "                          b_1.we_pe_no,\n" + "                          b_1.mct_no\n"
							+ "               ) a\n" + "          JOIN tb_tms_mct_main_master b\n"
							+ "            ON a.mct_no::text = b.mct_main_id::text\n"
							+ "           AND b.type_of_veh::text = 'A'::text\n" + "         GROUP BY a.mct_no\n"
							+ "       ) AS a\n" + " INNER JOIN tb_tms_mct_main_master m\n"
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'A'::text\n" + "";

				}
				if (type_veh.equals("B")) {
					q = " SELECT DISTINCT m.mct_nomen,\n" + "       a.mct_no,\n" + "       a.base::int,\n"
							+ "       a.mod::int AS modification,\n" + "       a.gennotes::int,\n"
							+ "       a.inlieu::int,\n" + "       a.reducedueinlieu::int,\n" + "       a.total::int\n"
							+ "  FROM (\n" + "        SELECT a.mct_no,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'BASEAUTH'::text THEN a.auth_amt ELSE 0::bigint END) AS base,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'MOD'::text THEN a.auth_amt ELSE 0::bigint END) AS MOD,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'INCDEC'::text THEN a.auth_amt ELSE 0::bigint END) AS gennotes,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'INLIEU'::text THEN a.auth_amt ELSE 0::bigint END) AS inlieu,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'REDUCEDUEINLIEU'::text THEN a.auth_amt ELSE 0::bigint END) AS reducedueinlieu,\n"
							+ "               sum(a.auth_amt) AS total\n" + "          FROM (\n"
							+ "                SELECT a_1.mct_no,\n" + "                       a_1.auth_amt,\n"
							+ "                       ' '::text AS\n" + "             CONDITION,\n"
							+ "                       'BASEAUTH'::text AS typeofauth\n"
							+ "                  FROM cue_tb_miso_wepe_transport_det a_1\n"
							+ "                 INNER JOIN cue_tb_wepe_link_sus_perstransweapon b_1\n"
							+ "                    ON a_1.we_pe_no::text = b_1.wepe_trans_no::text\n"
							+ "                   AND b_1.status_trans::text = '1'::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                   AND b_1.sus_no = ?\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       sum(b_1.amt_inc_dec) AS SUM,\n"
							+ "                       ''::text AS\n" + "             CONDITION,\n"
							+ "                       'MOD'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_mdfs a_1\n"
							+ "                 INNER JOIN cue_tb_miso_wepe_transport_mdfs b_1\n"
							+ "                    ON a_1.modification::text = b_1.modification::text\n"
							+ "                   AND a_1.we_pe_no::text = b_1.we_pe_no::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                 WHERE a_1.status::text = '1'::text\n"
							+ "                   AND a_1.sus_no = ?\n" + "                 GROUP BY a_1.sus_no,\n"
							+ "                          a_1.we_pe_no,\n" + "                          b_1.mct_no\n"
							+ "                 UNION SELECT b_1.in_lieu_mct,\n"
							+ "                       b_1.actual_inlieu_auth,\n" + "                       b_1.condition,\n"
							+ "                       'INLIEU'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '0'::text\n"
							+ "                  JOIN cue_tb_miso_wepe_transport_det c\n"
							+ "                    ON b_1.mct_no::text = c.mct_no::text\n"
							+ "                   AND b_1.we_pe_no::text = c.we_pe_no::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no = ?\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       b_1.amt_inc_dec,\n" + "                       b_1.condition,\n"
							+ "                       'INCDEC'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '1'::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no = ?\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       sum(b_1.actual_inlieu_auth) * '-1'::integer,\n"
							+ "                       ' '::text AS\n" + "             CONDITION,\n"
							+ "                       'REDUCEDUEINLIEU'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '0'::text\n"
							+ "                  JOIN cue_tb_miso_wepe_transport_det c\n"
							+ "                    ON b_1.mct_no::text = c.mct_no::text\n"
							+ "                   AND b_1.we_pe_no::text = c.we_pe_no::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no = ?\n" + "                 GROUP BY a_1.sus_no,\n"
							+ "                          b_1.we_pe_no,\n" + "                          b_1.mct_no\n"
							+ "               ) a\n" + "          JOIN tb_tms_mct_main_master b\n"
							+ "            ON a.mct_no::text = b.mct_main_id::text\n"
							+ "           AND b.type_of_veh::text = 'B'::text\n" + "         GROUP BY a.mct_no\n"
							+ "       ) AS a\n" + " INNER JOIN tb_tms_mct_main_master m\n"
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'B'::text ";

				}
				if (type_veh.equals("C")) {
					q = "SELECT DISTINCT m.mct_nomen,\n" + "       a.mct_no,\n" + "       a.base::int,\n"
							+ "       a.mod::int AS modification,\n" + "       a.gennotes::int,\n"
							+ "       a.inlieu::int,\n" + "       a.reducedueinlieu::int,\n" + "       a.total::int\n"
							+ "  FROM (\n" + "        SELECT a.mct_no,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'BASEAUTH'::text THEN a.auth_amt ELSE 0::bigint END) AS base,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'MOD'::text THEN a.auth_amt ELSE 0::bigint END) AS MOD,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'INCDEC'::text THEN a.auth_amt ELSE 0::bigint END) AS gennotes,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'INLIEU'::text THEN a.auth_amt ELSE 0::bigint END) AS inlieu,\n"
							+ "               sum(CASE WHEN a.typeofauth = 'REDUCEDUEINLIEU'::text THEN a.auth_amt ELSE 0::bigint END) AS reducedueinlieu,\n"
							+ "               sum(a.auth_amt) AS total\n" + "          FROM (\n"
							+ "                SELECT a_1.mct_no,\n" + "                       a_1.auth_amt,\n"
							+ "                       ' '::text AS\n" + "             CONDITION,\n"
							+ "                       'BASEAUTH'::text AS typeofauth\n"
							+ "                  FROM cue_tb_miso_wepe_transport_det a_1\n"
							+ "                 INNER JOIN cue_tb_wepe_link_sus_perstransweapon b_1\n"
							+ "                    ON a_1.we_pe_no::text = b_1.wepe_trans_no::text\n"
							+ "                   AND b_1.status_trans::text = '1'::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                   AND b_1.sus_no = ?\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       sum(b_1.amt_inc_dec) AS SUM,\n"
							+ "                       ''::text AS\n" + "             CONDITION,\n"
							+ "                       'MOD'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_mdfs a_1\n"
							+ "                 INNER JOIN cue_tb_miso_wepe_transport_mdfs b_1\n"
							+ "                    ON a_1.modification::text = b_1.modification::text\n"
							+ "                   AND a_1.we_pe_no::text = b_1.we_pe_no::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                 WHERE a_1.status::text = '1'::text\n"
							+ "                   AND a_1.sus_no = ?\n" + "                 GROUP BY a_1.sus_no,\n"
							+ "                          a_1.we_pe_no,\n" + "                          b_1.mct_no\n"
							+ "                 UNION SELECT b_1.in_lieu_mct,\n"
							+ "                       b_1.actual_inlieu_auth,\n" + "                       b_1.condition,\n"
							+ "                       'INLIEU'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '0'::text\n"
							+ "                  JOIN cue_tb_miso_wepe_transport_det c\n"
							+ "                    ON b_1.mct_no::text = c.mct_no::text\n"
							+ "                   AND b_1.we_pe_no::text = c.we_pe_no::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no = ?\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       b_1.amt_inc_dec,\n" + "                       b_1.condition,\n"
							+ "                       'INCDEC'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '1'::text\n"
							+ "                   AND b_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no = ?\n" + "                 UNION SELECT b_1.mct_no,\n"
							+ "                       sum(b_1.actual_inlieu_auth) * '-1'::integer,\n"
							+ "                       ' '::text AS\n" + "             CONDITION,\n"
							+ "                       'REDUCEDUEINLIEU'::text AS typeofauth\n"
							+ "                  FROM cue_tb_wepe_link_sus_trans_footnotes a_1\n"
							+ "                  JOIN cue_tb_miso_wepe_trans_footnotes b_1\n"
							+ "                    ON a_1.fk_trans_foot = b_1.id\n"
							+ "                   AND b_1.type_of_footnote::text = '0'::text\n"
							+ "                  JOIN cue_tb_miso_wepe_transport_det c\n"
							+ "                    ON b_1.mct_no::text = c.mct_no::text\n"
							+ "                   AND b_1.we_pe_no::text = c.we_pe_no::text\n"
							+ "                   AND a_1.status::text = '1'::text\n"
							+ "                  JOIN cue_tb_wepe_link_sus_perstransweapon LINK\n"
							+ "                    ON a_1.we_pe_no::text = link.wepe_trans_no::text\n"
							+ "                   AND link.status_trans::text = '1'::text\n"
							+ "                 WHERE a_1.sus_no = ?\n" + "                 GROUP BY a_1.sus_no,\n"
							+ "                          b_1.we_pe_no,\n" + "                          b_1.mct_no\n"
							+ "               ) a\n" + "          JOIN tb_tms_mct_main_master b\n"
							+ "            ON a.mct_no::text = b.mct_main_id::text\n"
							+ "           AND b.type_of_veh::text = 'C'::text\n" + "         GROUP BY a.mct_no\n"
							+ "       ) AS a\n" + " INNER JOIN tb_tms_mct_main_master m\n"
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'C'::text ";

				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
				stmt.setString(2, roleSusNo);
				stmt.setString(3, roleSusNo);
				stmt.setString(4, roleSusNo);
				stmt.setString(5, roleSusNo);

			}


			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("mct_no"));// 1
				list.add(rs.getString("mct_nomen"));// 2
				list.add(rs.getString("base"));// 3
				list.add(rs.getString("modification"));// 4
				list.add(rs.getString("gennotes"));// 5
				list.add(rs.getString("inlieu"));// 6
				list.add(rs.getString("reducedueinlieu"));// 7
				list.add(rs.getString("total"));// 8

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
	public ArrayList<ArrayList<String>> getUnitReportVeh(String type_veh, String roleSusNo,String formationtype) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);
				if (type_veh.equals("A")) {

					q = " SELECT DISTINCT\n" + "        ON (part.ba_no) part.ba_no,\n"
							+ "              b.mct_nomen AS veh_type,\n" + "              u.unit_name,\n"
							+ "              bd.veh_cat,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS broadcat,\n"
							/*+ "              CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                        ELSE 'UNSV'\n" + "  "*/
									+ "CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\n"
											+ "                        ELSE 'UNSV'\n" 
									+ "                        END AS serv_unsv,\n"
							+ "              part.vehcl_classfctn\r\n" +
							" AS classification,\n"
							+ "              round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "    FROM tb_tms_census_retrn part\n" + "  INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "        ON part.ba_no = bd.ba_no\n" + "  INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "        ON u.status_sus_no = 'Active'\n" + "      AND part.sus_no = u.sus_no\n"
							+ "  INNER JOIN tb_tms_mct_main_master b\n"
							+ "        ON substr(bd.mct::varchar,1,4) = b.mct_main_id  AND b.type_of_veh::text = 'A'::text\n"
							+ "    LEFT JOIN tb_tms_mct_slot_master s\n" + "        ON s.prf_code = b.prf_code\n"
							+ " WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')"
							+ "      AND bd.veh_cat = 'A'   ";

				}
				if (type_veh.equals("B")) {

					q = " SELECT distinct on (part.ba_no) part.ba_no,\n" + "              b.mct_nomen AS veh_type,\n"
							+ "              bd.veh_cat,\n" + "              u.unit_name,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS broadcat,\n"
							+ "              part.classification AS classification,\n"
							+ "             CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\n"
							+ "     ELSE 'UNSV'\n" + "END AS serv_unsv,"
							+ "              round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "\n" + "    FROM tb_tms_mvcr_parta_dtl part\n" + "  INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "        ON part.ba_no = bd.ba_no\n" + "  INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "        ON u.status_sus_no = 'Active'\n" + "      AND part.sus_no = u.sus_no\n"
							+ "  INNER JOIN tb_tms_mct_main_master b\n"
							+ "        ON substr(mct::varchar,1,4) = b.mct_main_id\n"
							+ "        AND b.type_of_veh::text = 'B'::text\n" + "    LEFT JOIN tb_tms_mct_slot_master s\n"
							+ "        ON s.prf_code = b.prf_code\n"
							+ "      LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a\n"
							+ "        ON a.mct_main_id = b.mct_main_id\n" + "  WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n"
							+ "      AND bd.veh_cat = 'B'   \n";

				}
				if (type_veh.equals("C")) {
					q = "SELECT DISTINCT\n" + "        ON (part.em_no) part.em_no AS ba_no,\n"
							+ "              b.mct_nomen AS veh_type,\n" + "                bd.veh_cat,\n"
							+ "              u.unit_name,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS broadcat,\n"
							+ "                CASE WHEN part.seviceable is null or part.seviceable = 'No' THEN 'SERV'\n"
							+ "                        ELSE 'UNSV'\n" + "                           END AS serv_unsv,\n"
							+ "              round(part.current_km_run + ((part.current_km_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\n"
							+ "              part.classification AS classification\n" + "    FROM tb_tms_emar_report part\n"
							+ "  INNER JOIN tb_tms_banum_dirctry bd\n" + "        ON part.em_no = bd.ba_no\n"
							+ "  INNER JOIN tb_miso_orbat_unt_dtl u\n" + "        ON u.status_sus_no = 'Active'\n"
							+ "      AND part.sus_no = u.sus_no\n" + "  INNER JOIN tb_tms_mct_main_master b\n"
							+ "        ON substr(mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'C'::text\n"
							+ "            LEFT JOIN tb_tms_mct_slot_master s\n" + "        ON s.prf_code = b.prf_code\n"
							+ "  WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') ";
				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);



			}else{

				if (type_veh.equals("A")) {

					q = " SELECT DISTINCT\n" + "        ON (part.ba_no) part.ba_no,\n"
							+ "              b.mct_nomen AS veh_type,\n" + "              u.unit_name,\n"
							+ "              bd.veh_cat,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS broadcat,\n"
							+ "              CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\n"
							+ "                        ELSE 'UNSV'\n" + "                          END AS serv_unsv,\n"
							+ "              part.vehcl_classfctn\r\n" +
							" AS classification,\n"
							+ "              round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "    FROM tb_tms_census_retrn part\n" + "  INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "        ON part.ba_no = bd.ba_no\n" + "  INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "        ON u.status_sus_no = 'Active'\n" + "      AND part.sus_no = u.sus_no\n"
							+ "  INNER JOIN tb_tms_mct_main_master b\n"
							+ "        ON substr(bd.mct::varchar,1,4) = b.mct_main_id  AND b.type_of_veh::text = 'A'::text\n"
							+ "    LEFT JOIN tb_tms_mct_slot_master s\n" + "        ON s.prf_code = b.prf_code\n"
							+ " WHERE part.sus_no = ?"
							+ "      AND bd.veh_cat = 'A'   ";

				}
				if (type_veh.equals("B")) {

					q = " SELECT distinct on (part.ba_no) part.ba_no,\n" + "              b.mct_nomen AS veh_type,\n"
							+ "              bd.veh_cat,\n" + "              u.unit_name,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS broadcat,\n"
							+ "              part.classification AS classification,\n"
							+ "             CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\n"
							+ "     ELSE 'UNSV'\n" + "END AS serv_unsv,"
							+ "              round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "\n" + "    FROM tb_tms_mvcr_parta_dtl part\n" + "  INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "        ON part.ba_no = bd.ba_no\n" + "  INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "        ON u.status_sus_no = 'Active'\n" + "      AND part.sus_no = u.sus_no\n"
							+ "  INNER JOIN tb_tms_mct_main_master b\n"
							+ "        ON substr(mct::varchar,1,4) = b.mct_main_id\n"
							+ "        AND b.type_of_veh::text = 'B'::text\n" + "    LEFT JOIN tb_tms_mct_slot_master s\n"
							+ "        ON s.prf_code = b.prf_code\n"
							+ "      LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a\n"
							+ "        ON a.mct_main_id = b.mct_main_id\n" + "  WHERE part.sus_no = ? \n"
							+ "      AND bd.veh_cat = 'B'   \n";

				}
				if (type_veh.equals("C")) {
					q = "SELECT DISTINCT\n" + "        ON (part.em_no) part.em_no AS ba_no,\n"
							+ "              b.mct_nomen AS veh_type,\n" + "                bd.veh_cat,\n"
							+ "              u.unit_name,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS broadcat,\n"
							+ "                CASE WHEN part.seviceable is null or part.seviceable = 'No' THEN 'SERV'\n"
							+ "                        ELSE 'UNSV'\n" + "                          END AS serv_unsv,\n"
							+ "              round(part.current_km_run + ((part.current_km_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\n"
							+ "              part.classification AS classification\n" + "    FROM tb_tms_emar_report part\n"
							+ "  INNER JOIN tb_tms_banum_dirctry bd\n" + "        ON part.em_no = bd.ba_no\n"
							+ "  INNER JOIN tb_miso_orbat_unt_dtl u\n" + "        ON u.status_sus_no = 'Active'\n"
							+ "      AND part.sus_no = u.sus_no\n" + "  INNER JOIN tb_tms_mct_main_master b\n"
							+ "        ON substr(mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'C'::text\n"
							+ "            LEFT JOIN tb_tms_mct_slot_master s\n" + "        ON s.prf_code = b.prf_code\n"
							+ "  WHERE part.sus_no = ? ";
				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}
			System.err.println("veh print data \n\n"+stmt);
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("ba_no"));// 2
				list.add(rs.getString("veh_type"));// 3
				list.add(rs.getString("broadcat"));// 4
				list.add(rs.getString("kms_run"));// 5
				list.add(rs.getString("classification"));// 6
				list.add(rs.getString("vintage"));// 7
				list.add(rs.getString("serv_unsv"));// 8

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
	public ArrayList<ArrayList<String>> getUnitReportWpnEqptAuth(String type_mtrls, String roleSusNo,String formationtype) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				if (type_mtrls.equals("wpn")) {

					q = "SELECT a.sus_no,\r\n"
							+ "       a.item_code,\r\n"
							+ "       i.item_type,\r\n"
							+ "       a.type,\r\n"
							+ "       a.total_ue_qty\r\n"
							+ "  FROM mms_ue_mview a\r\n"
							+ "  JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON i.item_code = a.item_code\r\n"
							+ "   AND left(i.cos_sec_no, 1) IN ('B','M','N','O','C')\r\n"
							+ " WHERE a.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\r\n"
							+ "   AND a.total_ue_qty != 0";

				}
				if (type_mtrls.equals("eqi")) {

					q = "SELECT a.sus_no,\r\n"
							+ "       a.item_code,\r\n"
							+ "       i.item_type,\r\n"
							+ "       a.type,\r\n"
							+ "       a.total_ue_qty\r\n"
							+ "  FROM mms_ue_mview a\r\n"
							+ "  JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON i.item_code = a.item_code\r\n"
							+ "   AND left(i.cos_sec_no, 1) NOT IN ('B','M','N','O','C','G')\r\n"
							+ " WHERE a.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\r\n"
							+ "   AND a.total_ue_qty != 0";
					;

				}

				stmt = conn.prepareStatement(q);

				stmt.setString(1, from_code_control);



			}else{

				if (type_mtrls.equals("wpn")) {

					q = "SELECT a.sus_no,\r\n"
							+ "       a.item_code,\r\n"
							+ "       i.item_type,\r\n"
							+ "       a.type,\r\n"
							+ "       a.total_ue_qty\r\n"
							+ "  FROM mms_ue_mview a\r\n"
							+ "  JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON i.item_code = a.item_code\r\n"
							+ "   AND left(i.cos_sec_no, 1) IN ('B','M','N','O','C')\r\n"
							+ " WHERE a.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\r\n"
							+ "   AND a.total_ue_qty != 0";

				}
				if (type_mtrls.equals("eqi")) {

					q = "SELECT a.sus_no,\r\n"
							+ "       a.item_code,\r\n"
							+ "       i.item_type,\r\n"
							+ "       a.type,\r\n"
							+ "       a.total_ue_qty\r\n"
							+ "  FROM mms_ue_mview a\r\n"
							+ "  JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON i.item_code = a.item_code\r\n"
							+ "   AND left(i.cos_sec_no, 1) NOT IN ('B','M','N','O','C','G')\r\n"
							+ " WHERE a.sus_no  in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\r\n"
							+ "   AND a.total_ue_qty != 0";
					;

				}

				stmt = conn.prepareStatement(q);

				stmt.setString(1, roleSusNo);

			}


			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("item_code"));// 1
				list.add(rs.getString("item_type"));// 2
				list.add(rs.getString("type"));// 3
				list.add(rs.getString("total_ue_qty"));// 4

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
	public ArrayList<ArrayList<String>> getUnitReportWpnEqpt(String type_mtrls, String roleSusNo,String formationtype) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				if (type_mtrls.equals("wpn")) {
					q = "  SELECT \r\n"
							+ " a.eqpt_regn_no,\r\n"
							+ " f.prf_group,\r\n"
							+ " a.census_no,\r\n"
							+ "  m.nomen,\r\n"
							+ "	   CASE WHEN a.service_status = '0' THEN 'UNSV'\r\n"
							+ "                        ELSE 'SER'\r\n"
							+ "                          END AS ser_unsv,\r\n"
							+ "	 t1.label as type_of_hldg_n,\r\n"
							+ " i.item_group, orb.unit_name as unit_name \r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "	INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C')   \n"
							+ "     order by f.prf_group,m.nomen";
				}
				if (type_mtrls.equals("eqi")) {
					q = " SELECT \r\n"
							+ " a.eqpt_regn_no,\r\n"
							+ " f.prf_group,\r\n"
							+ " a.census_no,\r\n"
							+ "  m.nomen,\r\n"
							+ "	   CASE WHEN a.service_status = '0' THEN 'UNSV'\r\n"
							+ "                        ELSE 'SER'\r\n"
							+ "                          END AS ser_unsv,\r\n"
							+ "	 t1.label as type_of_hldg_n,\r\n"
							+ " i.item_group , orb.unit_name as unit_name\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "	INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) Not IN ('B','M','N','O','C','G')  \n"
							+ "     order by f.prf_group,m.nomen";
				}

				stmt = conn.prepareStatement(q);

				stmt.setString(1, from_code_control);



			}else{

				if (type_mtrls.equals("wpn")) {
					q = "  SELECT \r\n"
							+ " a.eqpt_regn_no,\r\n"
							+ " f.prf_group,\r\n"
							+ " a.census_no,\r\n"
							+ "  m.nomen,\r\n"
							+ "	   CASE WHEN a.service_status = '0' THEN 'UNSV'\r\n"
							+ "                        ELSE 'SER'\r\n"
							+ "                          END AS ser_unsv,\r\n"
							+ "	 t1.label as type_of_hldg_n,\r\n"
							+ " i.item_group , orb.unit_name as unit_name\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "	INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no=? \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C')   \n"
							+ "     order by f.prf_group,m.nomen";
				}
				if (type_mtrls.equals("eqi")) {
					q = "  SELECT \r\n"
							+ " a.eqpt_regn_no,\r\n"
							+ " f.prf_group,\r\n"
							+ " a.census_no,\r\n"
							+ "  m.nomen,\r\n"
							+ "	   CASE WHEN a.service_status = '0' THEN 'UNSV'\r\n"
							+ "                        ELSE 'SER'\r\n"
							+ "                          END AS ser_unsv,\r\n"
							+ "	 t1.label as type_of_hldg_n,\r\n"
							+ " i.item_group, orb.unit_name as unit_name \r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "	INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no=?  \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) Not IN ('B','M','N','O','C','G')  \n"
							+ "     order by f.prf_group,m.nomen";
				}

				stmt = conn.prepareStatement(q);

				stmt.setString(1, roleSusNo);

			}


			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("item_group"));// 2
				list.add(rs.getString("prf_group"));// 3
				list.add(rs.getString("nomen"));// 4
				list.add(rs.getString("census_no"));// 5
				list.add(rs.getString("eqpt_regn_no"));// 6
				list.add(rs.getString("type_of_hldg_n"));// 7
				list.add(rs.getString("ser_unsv"));// 8

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
	public ArrayList<ArrayList<String>> getUnitReportIT_Assets(String roleSusNo,String formationtype) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;


			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);
				q = "SELECT * FROM ( SELECT\n" + "              'computing' AS assets_type,\n"
						+ "              b.assets_name,\n" + "              a.machine_number,\n"
						+ "              c.office,\n" + "              o.os,\n"
						+ "              CAST(EXTRACT(YEAR FROM proc_date) AS VARCHAR) AS year_of_proc, -- Cast to character varying\n"
						+ "              n.processor_type,\n" + " d.dply_env, orb.unit_name as unit_name\n"
						+ "      FROM tb_asset_main a\n"
						+ "              INNER JOIN tb_mstr_assets b ON a.asset_type = b.id AND b.category = 1\n"
						+ "              INNER JOIN tb_mstr_os o ON o.id = a.operating_system\n"
						+ "              INNER JOIN tb_mstr_processor_type n ON n.id = a.processor_type\n"
						+ "              INNER JOIN tb_mstr_dply_env d ON d.id = a.dply_envt\n"
						+ "				 INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "              LEFT JOIN tb_mstr_office c ON c.id = a.office \n"
						+ "    where a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')   UNION ALL \n" + "      SELECT\n"
						+ "              'peripherals' AS assets_type,\n" + "              b.assets_name,\n"
						+ "              a.machine_no AS machine_number,\n" + "              '' AS office,\n"
						+ "              '' AS os,\n" + "              a.year_of_proc,\n"
						+ "              '' AS processor_type,\n" + "              '' AS dply_env, orb.unit_name as unit_name\n" + "      FROM\n"
						+ "              it_asset_peripherals a\n"
						+ "				 INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "              INNER JOIN tb_mstr_assets b ON a.assets_type = b.id AND b.category = 2\n"
						+ "			  and a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') )  a \n";
				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
				stmt.setString(2, from_code_control);




			}else{

				q = "SELECT * FROM ( SELECT\n" + "              'computing' AS assets_type,\n"
						+ "              b.assets_name,\n" + "              a.machine_number,\n"
						+ "              c.office,\n" + "              o.os,\n"
						+ "              CAST(EXTRACT(YEAR FROM proc_date) AS VARCHAR) AS year_of_proc, -- Cast to character varying\n"
						+ "              n.processor_type,\n" + "              d.dply_env, orb.unit_name as unit_name\n"
						+ "      FROM tb_asset_main a\n"
						+ "              INNER JOIN tb_mstr_assets b ON a.asset_type = b.id AND b.category = 1\n"
						+ "              INNER JOIN tb_mstr_os o ON o.id = a.operating_system\n"
						+ "              INNER JOIN tb_mstr_processor_type n ON n.id = a.processor_type\n"
						+ "              INNER JOIN tb_mstr_dply_env d ON d.id = a.dply_envt\n"
						+ "				 INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "              LEFT JOIN tb_mstr_office c ON c.id = a.office \n"
						+ "    where a.sus_no= ?   UNION ALL \n" + "      SELECT\n"
						+ "              'peripherals' AS assets_type,\n" + "              b.assets_name,\n"
						+ "              a.machine_no AS machine_number,\n" + "              '' AS office,\n"
						+ "              '' AS os,\n" + "              a.year_of_proc,\n"
						+ "              '' AS processor_type,\n" + "              '' AS dply_env, orb.unit_name as unit_name\n" + "      FROM\n"
						+ "              it_asset_peripherals a\n"
						+ "				 INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "              INNER JOIN tb_mstr_assets b ON a.assets_type = b.id AND b.category = 2\n"
						+ "			  and a.sus_no= ? )  a \n";
				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
				stmt.setString(2, roleSusNo);

			}



			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("assets_type"));// 2
				list.add(rs.getString("assets_name"));// 3
				list.add(rs.getString("machine_number"));// 4
				list.add(rs.getString("os"));// 5
				list.add(rs.getString("office"));// 6
				list.add(rs.getString("year_of_proc"));// 7
				list.add(rs.getString("processor_type"));// 8
				list.add(rs.getString("dply_env"));// 9

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
	public ArrayList<ArrayList<String>> getUnitReportWpnEqptSummary(String type_mtrls, String roleSusNo,String formationtype) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {


			conn = dataSource.getConnection();
			PreparedStatement stmt = null;


			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				if (type_mtrls.equals("wpn")) {
					q = "  SELECT \r\n"
							+ " f.prf_group,count(f.prf_group) as total \r\n"
							+ "\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C')   \n"
							+ "     group by f.prf_group";
				}
				if (type_mtrls.equals("eqi")) {
					q = "  SELECT \r\n"
							+ " f.prf_group,count(f.prf_group) as total \r\n"
							+ "\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) Not IN ('B','M','N','O','C','G')  \n"
							+ "     group by f.prf_group";
				}

				stmt = conn.prepareStatement(q);

				stmt.setString(1, from_code_control);



			}else{

				if (type_mtrls.equals("wpn")) {
					q = "  SELECT \r\n"
							+ " f.prf_group,count(f.prf_group) as total\r\n"
							+ "\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no=? \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C')   \n"
							+ "     group by f.prf_group";
				}
				if (type_mtrls.equals("eqi")) {
					q = "  SELECT \r\n"
							+ " f.prf_group,count(f.prf_group) as total \r\n"
							+ "\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no=? \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) Not IN ('B','M','N','O','C','G')  \n"
							+ "     group by f.prf_group";
				}

				stmt = conn.prepareStatement(q);

				stmt.setString(1, roleSusNo);

			}


			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("prf_group"));// 1
				list.add(rs.getString("total"));// 2


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


	public String formation_code(String sus_no) {
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		String form_code_control="";
		try {
			Query q = ses1.createQuery(
					"select distinct form_code_control from Miso_Orbat_Unt_Dtl where sus_no=:sus_no  and status_sus_no='Active'");
			q.setParameter("sus_no", sus_no);
			form_code_control = (String) q.uniqueResult();

			t2.commit();
		} catch (Exception e) {
			if (t2 != null) {
				t2.rollback();
			}
			e.printStackTrace();
		} finally {
			ses1.close();
		}
		return form_code_control;
	}

	//	 pranay 15.07 bde dashboard


	@Override
	public ArrayList<ArrayList<String>> get3008CurrentMonthReport(String roleSusNo) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String from_code_control= formation_code(roleSusNo);
			q = " SELECT\r\n"
					+ "	b.sus_no,\r\n"
					+ "	b.unit_name,\r\n"
					+ "	COALESCE(\r\n"
					+ "		(\r\n"
					+ "			SELECT\r\n"
					+ "				CASE\r\n"
					+ "					WHEN c.status=0\r\n"
					+ "					OR c.status IS NULL THEN 'yet to update'\r\n"
					+ "					WHEN c.status=1 THEN 'updated'\r\n"
					+ "					ELSE 'yet to update'\r\n"
					+ "				END\r\n"
					+ "			FROM\r\n"
					+ "				tb_psg_iaff_3008_main c\r\n"
					+ "			WHERE\r\n"
					+ "				c.sus_no=b.sus_no\r\n"
					+ "				AND c.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
					+ "				AND c.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"
					+ "		),\r\n"
					+ "		'yet to update'\r\n"
					+ "	) AS status\r\n"
					+ "FROM\r\n"
					+ "	tb_miso_orbat_unt_dtl b\r\n"
					+ "WHERE\r\n"
					+ "	b.status_sus_no='Active'\r\n"
					+ "	AND b.form_code_control=? ";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);


			//System.err.println("3008 print:\n "+ stmt);
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("status"));// 2
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
	public ArrayList<ArrayList<String>> get3009CurrentMonthReport(String roleSusNo) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String from_code_control= formation_code(roleSusNo);
			q = " SELECT\r\n"
					+ "	b.sus_no,\r\n"
					+ "	b.unit_name,\r\n"
					+ "	COALESCE(\r\n"
					+ "		(\r\n"
					+ "			SELECT\r\n"
					+ "				CASE\r\n"
					+ "					WHEN c.status=0\r\n"
					+ "					OR c.status IS NULL THEN 'yet to update'\r\n"
					+ "					WHEN c.status=1 THEN 'updated'\r\n"
					+ "					ELSE 'yet to update'\r\n"
					+ "				END\r\n"
					+ "			FROM\r\n"
					+ "				tb_psg_iaff_3009_main c\r\n"
					+ "			WHERE\r\n"
					+ "				c.sus_no=b.sus_no\r\n"
					+ "				AND c.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
					+ "				AND c.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"
					+ "		),\r\n"
					+ "		'yet to update'\r\n"
					+ "	) AS status\r\n"
					+ "FROM\r\n"
					+ "	tb_miso_orbat_unt_dtl b\r\n"
					+ "WHERE\r\n"
					+ "	b.status_sus_no='Active'\r\n"
					+ "	AND b.form_code_control=? ";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);


			//System.err.println("3009 print:\n "+ stmt);
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("status"));// 2
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
	public ArrayList<ArrayList<String>> getMcrCurrentMonthReport(String roleSusNo) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String from_code_control= formation_code(roleSusNo);
			q = "SELECT DISTINCT b.sus_no,b.unit_name,\r\n"
					+ "       CASE WHEN app.unit_status = 'DEF' THEN 'yet to update'\r\n"
					+ "            WHEN app.unit_status = 'APP' THEN 'updated'\r\n"
					+ "            WHEN app.unit_status IS NULL THEN 'yet to update'\r\n"
					+ "             END AS status\r\n"
					+ "  FROM tb_miso_orbat_unt_dtl b\r\n"
					+ "  LEFT JOIN (\r\n"
					+ "        SELECT DISTINCT o.sus_no,\r\n"
					+ "               CASE WHEN p.status = 'APP' THEN p.status\r\n"
					+ "                    ELSE 'DEF'\r\n"
					+ "                     END AS unit_status\r\n"
					+ "          FROM tb_miso_orbat_unt_dtl o\r\n"
					+ "         INNER JOIN sus_weapon_wepe_with_wetpet c\r\n"
					+ "            ON c.sus_no = o.sus_no\r\n"
					+ "          LEFT JOIN all_fmn_view fmn\r\n"
					+ "            ON o.form_code_control = fmn.form_code_control\r\n"
					+ "          LEFT JOIN baseunits b\r\n"
					+ "            ON o.sus_no = b.b_sus_no\r\n"
					+ "          LEFT JOIN (\r\n"
					+ "                SELECT DISTINCT sus_no,\r\n"
					+ "                       'APP' AS status\r\n"
					+ "                  FROM mms_tb_unit_upload_document\r\n"
					+ "                 WHERE to_char(created_on,'YYYY-MM') = ltrim(to_char(now(),'YYYY-MM'),'0')\r\n"
					+ "                   AND doc IS NULL\r\n"
					+ "                 GROUP BY 1\r\n"
					+ "               ) p\r\n"
					+ "            ON o.sus_no = p.sus_no\r\n"
					+ "          LEFT JOIN (\r\n"
					+ "                SELECT sus_no,\r\n"
					+ "                       to_char(max(created_on),'dd-mm-yyyy') AS upd_date\r\n"
					+ "                  FROM mms_tb_unit_upload_document\r\n"
					+ "                 GROUP BY 1\r\n"
					+ "               ) last_update\r\n"
					+ "            ON o.sus_no = last_update.sus_no\r\n"
					+ "         WHERE o.status_sus_no = 'Active'\r\n"
					+ "       ) app\r\n"
					+ "    ON app.sus_no = b.sus_no\r\n"
					+ " WHERE b.sus_no IN (\r\n"
					+ "        SELECT sus_no\r\n"
					+ "          FROM tb_miso_orbat_unt_dtl\r\n"
					+ "         WHERE status_sus_no = 'Active'\r\n"
					+ "           AND form_code_control = ?\r\n"
					+ "       )\r\n"
					+ "			 and b.status_sus_no='Active'";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);



			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("status"));// 2
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
	public ArrayList<ArrayList<String>> getUnitWiseItAssetReport(String roleSusNo) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String from_code_control= formation_code(roleSusNo);
			q = " SELECT\r\n"
					+ "	a.sus_no,\r\n"
					+ "	a.unit_name,\r\n"
					+ "	SUM(a.computing) AS computing,\r\n"
					+ "	SUM(a.peripheral) AS peripheral\r\n"
					+ "FROM\r\n"
					+ "	(\r\n"
					+ "		SELECT\n"
					+ "			b.sus_no,\n"
					+ "			b.unit_name,\n"
					+ "				(SELECT COUNT(	a.sus_no	)\n"
					+ "				FROM\n"
					+ "					it_asset_peripherals a \n"


					//+ "              INNER JOIN tb_mstr_assets bb ON a.assets_type = bb.id AND bb.category = 2\n"
					+ "				 inner join  tb_mstr_make mm on  mm.id=a.make_name \n"
					+ "              inner join  tb_mstr_model m on  m.id=a.model_name \n"

					+ "				WHERE\n"
					+ "					a.status=1 and a.s_state='1'\n"
					+ "					AND a.sus_no=b.sus_no\n"
					+ "			) AS peripheral,\n"
					+ "			0 AS computing\n"
					+ "		FROM\n"
					+ "			tb_miso_orbat_unt_dtl b\n"
					+ "		WHERE\n"
					+ "			b.status_sus_no='Active'\n"
					+ "			AND b.form_code_control=?\n"
					+ "		UNION ALL\n"
					+ "		SELECT\n"
					+ "			b.sus_no,\n"
					+ "			b.unit_name,\n"
					+ "			0 AS peripheral,\n"
					+ "				(SELECT COUNT(	a.sus_no)\n"
					+ "				FROM\n"
					+ "					tb_asset_main a \n"

					//+ "              INNER JOIN tb_mstr_assets bb ON a.asset_type = bb.id AND bb.category = 1\n"
					//+ "              INNER JOIN tb_mstr_os o ON o.id = a.operating_system\n"
					//+ "              INNER JOIN tb_mstr_processor_type n ON n.id = a.processor_type\n"
					//+ "              INNER JOIN tb_mstr_dply_env d ON d.id = a.dply_envt\n"
					//+ "              LEFT JOIN tb_mstr_office c ON c.id = a.office \n"
					+ "				 inner join  tb_mstr_make mm on  mm.id=a.make_name \n"
					+ "				 inner join  tb_mstr_model m on  m.id=a.model_name  \n"
					+ "				 inner join  tb_mstr_budget y on  y.id::text=a.b_code\n"

					+ "				WHERE\n"
					+ "					a.status=1 and a.s_state='1'\n"
					+ "					AND a.sus_no = b.sus_no\n"
					+ "			) AS computing\n"
					+ "		FROM\r\n"
					+ "			tb_miso_orbat_unt_dtl b\r\n"
					+ "		WHERE\r\n"
					+ "			b.status_sus_no='Active'\r\n"
					+ "			AND b.form_code_control=?\r\n"
					+ "	) a   group by a.sus_no ,a.unit_name";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);
			stmt.setString(2, from_code_control);



			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("computing"));// 2
				list.add(rs.getString("peripheral"));// 3
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
	public ArrayList<ArrayList<String>> getAvehCurrentMonthReport(String roleSusNo) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String from_code_control= formation_code(roleSusNo);
			q = "SELECT b.sus_no,\r\n"
					+ "       b.unit_name,\r\n"
					+ "       CASE \r\n"
					+ "           WHEN NOT EXISTS (\r\n"
					+ "               SELECT 1 \r\n"
					+ "               FROM tb_tms_census_retrn c \r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "           ) THEN 'NA'\r\n"
					+ "           ELSE COALESCE ( (\r\n"
					+ "               SELECT \r\n"
					+ "                   CASE \r\n"
					+ "                       WHEN c.status = '1' THEN 'updated'\r\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\r\n"
					+ "                   END\r\n"
					+ "               FROM tb_tms_census_retrn_main c\r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "                AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(cast(c.approve_date AS date),'mm-yyyy'),'0')),'yet to update'\r\n"
					+ "           )\r\n"
					+ "       END AS status\r\n"
					+ "FROM tb_miso_orbat_unt_dtl b\r\n"
					+ "WHERE b.status_sus_no = 'Active'\r\n"
					+ "  AND b.form_code_control = ?";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);



			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("status"));// 2
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
	public ArrayList<ArrayList<String>> getBvehCurrentMonthReport(String roleSusNo) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String from_code_control= formation_code(roleSusNo);
			q = "\r\n"
					+ "SELECT b.sus_no,\r\n"
					+ "       b.unit_name,\r\n"
					+ "       CASE \r\n"
					+ "           WHEN NOT EXISTS (\r\n"
					+ "               SELECT 1 \r\n"
					+ "               FROM tb_tms_mvcr_parta_dtl c \r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "           ) THEN 'NA'\r\n"
					+ "           ELSE COALESCE ( (\r\n"
					+ "               SELECT \r\n"
					+ "                   CASE \r\n"
					+ "                       WHEN c.status = '1' THEN 'updated'\r\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\r\n"
					+ "                   END\r\n"
					+ "               FROM tb_tms_mvcr_parta_main c\r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "                AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(cast(c.approve_date AS date),'mm-yyyy'),'0')),'yet to update'\r\n"
					+ "           )\r\n"
					+ "       END AS status\r\n"
					+ "FROM tb_miso_orbat_unt_dtl b\r\n"
					+ "WHERE b.status_sus_no = 'Active'\r\n"
					+ "  AND b.form_code_control =?";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);



			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("status"));// 2
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
	public ArrayList<ArrayList<String>> getCvehCurrentMonthReport(String roleSusNo) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String from_code_control= formation_code(roleSusNo);
			q = "SELECT b.sus_no,\r\n"
					+ "       b.unit_name,\r\n"
					+ "       CASE \r\n"
					+ "           WHEN NOT EXISTS (\r\n"
					+ "               SELECT 1 \r\n"
					+ "               FROM tb_tms_emar_report c \r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "           ) THEN 'NA'\r\n"
					+ "           ELSE COALESCE ( (\r\n"
					+ "               SELECT \r\n"
					+ "                   CASE \r\n"
					+ "                       WHEN c.status = '1' THEN 'updated'\r\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\r\n"
					+ "                   END\r\n"
					+ "               FROM tb_tms_emar_report_main c\r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "                AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(cast(c.approve_date AS date),'mm-yyyy'),'0')),'yet to update'\r\n"
					+ "           )\r\n"
					+ "       END AS status\r\n"
					+ "FROM tb_miso_orbat_unt_dtl b\r\n"
					+ "WHERE b.status_sus_no = 'Active'\r\n"
					+ "  AND b.form_code_control = ?";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);



			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("status"));// 2
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
	public ArrayList<ArrayList<String>> getAllCasualtyCurrentMonth(String roleSusNo) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String from_code_control= formation_code(roleSusNo);
			q = "SELECT\r\n"
					+ "	b.sus_no,\r\n"
					+ "	b.unit_name,\r\n"
					+ "	COALESCE(\r\n"
					+ "		(\r\n"
					+ "			SELECT\r\n"
					+ "				CASE\r\n"
					+ "					WHEN c.status=0\r\n"
					+ "					OR c.status IS NULL THEN 'yet to update'\r\n"
					+ "					WHEN c.status=1 THEN 'updated'\r\n"
					+ "					ELSE 'yet to update'\r\n"
					+ "				END\r\n"
					+ "			FROM\r\n"
					+ "				tb_psg_iaff_3008_main c\r\n"
					+ "			WHERE\r\n"
					+ "				c.sus_no=b.sus_no\r\n"
					+ "				AND c.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
					+ "				AND c.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"
					+ "		),\r\n"
					+ "		'yet to update'\r\n"
					+ "	) AS status_3008,\r\n"
					+ "		COALESCE(\r\n"
					+ "		(\r\n"
					+ "			SELECT\r\n"
					+ "				CASE\r\n"
					+ "					WHEN c.status=0\r\n"
					+ "					OR c.status IS NULL THEN 'yet to update'\r\n"
					+ "					WHEN c.status=1 THEN 'updated'\r\n"
					+ "					ELSE 'yet to update'\r\n"
					+ "				END\r\n"
					+ "			FROM\r\n"
					+ "				tb_psg_iaff_3009_main c\r\n"
					+ "			WHERE\r\n"
					+ "				c.sus_no=b.sus_no\r\n"
					+ "				AND c.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
					+ "				AND c.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"
					+ "		),\r\n"
					+ "		'yet to update'\r\n"
					+ "	) AS status_3009,\r\n"
					+ "	    CASE \r\n"
					+ "           WHEN NOT EXISTS (\r\n"
					+ "               SELECT 1 \r\n"
					+ "               FROM tb_tms_census_retrn c \r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "           ) THEN 'NA'\r\n"
					+ "           ELSE COALESCE ( (\r\n"
					+ "               SELECT \r\n"
					+ "                   CASE \r\n"
					+ "                       WHEN c.status = '1' THEN 'updated'\r\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\r\n"
					+ "                   END\r\n"
					+ "               FROM tb_tms_census_retrn_main c\r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "               AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(c.approve_date,'mm-yyyy'),'0')),'yet to update'\r\n"
					+ "           )\r\n"
					+ "       END AS aveh_status,\r\n"
					+ "			    CASE \r\n"
					+ "           WHEN NOT EXISTS (\r\n"
					+ "               SELECT 1 \r\n"
					+ "               FROM tb_tms_mvcr_parta_dtl c \r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "           ) THEN 'NA'\r\n"
					+ "           ELSE COALESCE ( (\r\n"
					+ "               SELECT \r\n"
					+ "                   CASE \r\n"
					+ "                       WHEN c.status = '1' THEN 'updated'\r\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\r\n"
					+ "                   END\r\n"
					+ "               FROM tb_tms_mvcr_parta_main c\r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "               AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(c.approve_date,'mm-yyyy'),'0')),'yet to update'\r\n"
					+ "           )\r\n"
					+ "       END AS bveh_status,\r\n"
					+ "			    CASE \r\n"
					+ "           WHEN NOT EXISTS (\r\n"
					+ "               SELECT 1 \r\n"
					+ "               FROM tb_tms_emar_report c \r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "           ) THEN 'NA'\r\n"
					+ "           ELSE COALESCE ( (\r\n"
					+ "               SELECT \r\n"
					+ "                   CASE \r\n"
					+ "                       WHEN c.status = '1' THEN 'updated'\r\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\r\n"
					+ "                   END\r\n"
					+ "               FROM tb_tms_emar_report_main c\r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "                AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(cast(c.approve_date AS date),'mm-yyyy'),'0')),'yet to update'\r\n"
					+ "           )\r\n"
					+ "       END AS cveh_status,\r\n"
					+ "			 (\r\n"
					+ "				SELECT COUNT(	a.sus_no	)\n"
					+ "				FROM\n"
					+ "					it_asset_peripherals a \n"


					//+ "              INNER JOIN tb_mstr_assets bb ON a.assets_type = bb.id AND bb.category = 2\n"
					+ "				 inner join  tb_mstr_make mm on  mm.id=a.make_name \n"
					+ "              inner join  tb_mstr_model m on  m.id=a.model_name \n"

					+ "				WHERE\n"
					+ "					a.status=1 and a.s_state='1'\n"
					+ "					AND a.sus_no=b.sus_no\n"
					+ "			) AS peripheral,\n"
					+ "				(\n"
					+ "				SELECT COUNT(	a.sus_no)\n"
					+ "				FROM\n"
					+ "					tb_asset_main a \n"

					//+ "              INNER JOIN tb_mstr_assets bb ON a.asset_type = bb.id AND bb.category = 1\n"
					//+ "              INNER JOIN tb_mstr_os o ON o.id = a.operating_system\n"
					//+ "              INNER JOIN tb_mstr_processor_type n ON n.id = a.processor_type\n"
					//+ "              INNER JOIN tb_mstr_dply_env d ON d.id = a.dply_envt\n"
					//+ "              LEFT JOIN tb_mstr_office c ON c.id = a.office \n"
					+ "				 inner join  tb_mstr_make mm on  mm.id=a.make_name \n"
					+ "				 inner join  tb_mstr_model m on  m.id=a.model_name  \n"
					+ "				 inner join  tb_mstr_budget y on  y.id::text=a.b_code\n"

					+ "				WHERE\n"
					+ "					a.status=1 and a.s_state='1'\n"
					+ "					AND a.sus_no = b.sus_no\n"
					+ "			) AS computing ,\n"
					+ "			   (SELECT\r\n"
					+ "        CASE \r\n"
					+ "            WHEN MAX(app.unit_status) = 'DEF' THEN 'yet to update'\r\n"
					+ "            WHEN MAX(app.unit_status) = 'APP' THEN 'updated'\r\n"
					+ "            WHEN MAX(app.unit_status) IS NULL THEN 'yet to update'\r\n"
					+ "        END AS status\r\n"
					+ "    FROM tb_miso_orbat_unt_dtl c\r\n"
					+ "    LEFT JOIN (\r\n"
					+ "        SELECT DISTINCT o.sus_no,\r\n"
					+ "               CASE WHEN p.status = 'APP' THEN p.status\r\n"
					+ "                    ELSE 'DEF'\r\n"
					+ "               END AS unit_status\r\n"
					+ "        FROM tb_miso_orbat_unt_dtl o\r\n"
					+ "        INNER JOIN sus_weapon_wepe_with_wetpet c ON c.sus_no = o.sus_no\r\n"
					+ "        LEFT JOIN all_fmn_view fmn ON o.form_code_control = fmn.form_code_control\r\n"
					+ "        LEFT JOIN baseunits b ON o.sus_no = b.b_sus_no\r\n"
					+ "        LEFT JOIN (\r\n"
					+ "            SELECT DISTINCT sus_no,\r\n"
					+ "                   'APP' AS status\r\n"
					+ "            FROM mms_tb_unit_upload_document\r\n"
					+ "            WHERE to_char(created_on,'YYYY-MM') = ltrim(to_char(now(),'yyyy-MM'),'0')\r\n"
					+ "              AND doc IS NULL\r\n"
					+ "            GROUP BY 1\r\n"
					+ "        ) p ON o.sus_no = p.sus_no\r\n"
					+ "        LEFT JOIN (\r\n"
					+ "            SELECT sus_no,\r\n"
					+ "                   to_char(max(created_on),'dd-mm-yyyy') AS upd_date\r\n"
					+ "            FROM mms_tb_unit_upload_document\r\n"
					+ "            GROUP BY 1\r\n"
					+ "        ) last_update ON o.sus_no = last_update.sus_no\r\n"
					+ "        WHERE o.status_sus_no = 'Active'\r\n"
					+ "    ) app ON app.sus_no = c.sus_no\r\n"
					+ "    WHERE c.sus_no = b.sus_no\r\n"
					+ "    GROUP BY c.sus_no) AS mcr_status\r\n"
					+ "		\r\n"
					+ "FROM\r\n"
					+ "	tb_miso_orbat_unt_dtl b\r\n"
					+ "WHERE\r\n"
					+ "	b.status_sus_no='Active'\r\n"
					+ "	AND b.form_code_control=? " ;
			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);


			//System.err.println("pdf all combine \n"+stmt);
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("status_3008"));// 2
				list.add(rs.getString("status_3009"));// 3
				list.add(rs.getString("aveh_status"));// 4
				list.add(rs.getString("bveh_status"));// 5
				list.add(rs.getString("cveh_status"));// 6
				list.add(rs.getString("mcr_status"));// 7
				list.add(rs.getString("computing"));// 8
				list.add(rs.getString("peripheral"));// 9


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


	//	 end pranay 15.07 bde dashboard

}
