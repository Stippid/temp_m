package com.dao.psg.Civilian_Report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class held_civ_report_DaoImpl implements held_civ_report_Dao {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> Report_civ_nominalroll(String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no, String civilian_status) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String q = "";
		String qry = "";
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres",
					"postgres");
			// c =
			// DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap?user=postgres&password=postgres");

			c.setAutoCommit(false);
			q = "SELECT dblink_connect('myconn','miso_psg_oltp1')";
			stmt = c.prepareStatement(q);
			rs = stmt.executeQuery();

			

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));
				
			}
//			if (!month.equals("0")) {
//				qry += " where pn.month = ? ";
//			}
//			if (!year.equals("")) {
//				qry += " and pn.year = ? ";
//			}
//			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
//				qry += " and md.cmd_sus = ? ";
//			}
//			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
//				qry += " and md.corp_sus = ? ";
//			}
//			if (!div_sus.equals("0") && !div_sus.equals("")) {
//				qry += " and md.div_sus = ? ";
//			}
//			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
//				qry += " and md.bde_sus = ? ";
//			}
//			if (!unit_sus_no.equals("")) {
//				qry += " and pn.sus_no = ?  ";
//			}
//			if (!civilian_status.equals("")) {
//				qry += " and upper(pn.civilian_status) = ? ";
//			}
//
//			q = "SELECT DISTINCT pn.employee_no,\r\n"
//					+ "       pn.full_name,\r\n"
//					+ "       pn.designation,\r\n"
//					+ "       pn.dob,\r\n"
//					+ "       pn.classification_services,\r\n"
//					+ "       pn.civ_group,\r\n"
//					+ "       pn.civ_type,\r\n"
//					+ "       pn.joining_date_gov_service,\r\n"
//					+ "       pn.date_of_tos,\r\n"
//					+ "       pn.designation_date,\r\n"
//					+ "       pn.gender_name\r\n"
//					+ "  FROM tb_psg_nominalroll AS pn\r\n"
//					+ " INNER JOIN tb_psg_held_str_civ_main_details AS md\r\n"
//					+ "    ON pn.sus_no = md.sus_no\r\n"
//					+ "   AND pn.year = md.year\r\n"
//					+ "   AND pn.version = md.version\r\n"
//					+ "   AND pn.month = md.month\r\n"
//					+ "   AND md.status != 3"
//					+ qry;
//			
			
			
			if (!month.equals("0")) {
				qry += " where np.month = ? ";
			}
			if (!year.equals("")) {
				qry += " and np.year = ? ";
			}
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and np.cmd_sus = ? ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and np.corp_sus = ? ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and np.div_sus = ? ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and np.bde_sus = ? ";
			}
			if (!unit_sus_no.equals("")) {
				qry += " and np.sus_no = ?  ";
			}
			if (!civilian_status.equals("")) {
				qry += " and upper(np.civilian_status) = ? ";
			}

			q = "select * from (select distinct employee_no,full_name, designation,dob,classification_services,civ_group,\r\n"
					+ "              civ_type,joining_date_gov_service,date_of_tos,designation_date,gender_name,\r\n"
					+ "               month,year,cmd_sus,corp_sus,div_sus,bde_sus,sus_no ,civilian_status from \r\n"
					+ "dblink ('myconn','SELECT DISTINCT pn.employee_no,\r\n"
					+ "       pn.full_name,\r\n"
					+ "       pn.designation,\r\n"
					+ "       pn.dob,\r\n"
					+ "       pn.classification_services,\r\n"
					+ "       pn.civ_group,\r\n"
					+ "       pn.civ_type,\r\n"
					+ "       pn.joining_date_gov_service,\r\n"
					+ "       pn.date_of_tos,\r\n"
					+ "       pn.designation_date,\r\n"
					+ "       pn.gender_name,\r\n"
					+ "       pn.month,\r\n"
					+ "       pn.year,\r\n"
					+ "       md.cmd_sus,\r\n"
					+ "       md.corp_sus,\r\n"
					+ "       md.div_sus,\r\n"
					+ "       md.bde_sus,\r\n"
					+ "       pn.sus_no , pn.civilian_status\r\n"
					+ "  FROM tb_psg_held_str_civ_main AS v\r\n"
					+ " INNER JOIN tb_psg_nominalroll AS pn\r\n"
					+ "    ON v.sus_no = pn.sus_no\r\n"
					+ "   AND v.year = pn.year\r\n"
					+ "   AND v.version = pn.version\r\n"
					+ "   AND v.month = pn.month\r\n"
					+ " INNER JOIN tb_psg_held_str_civ_main_details AS md\r\n"
					+ "    ON v.sus_no = md.sus_no\r\n"
					+ "   AND v.year = md.year\r\n"
					+ "   AND v.version = md.version\r\n"
					+ "   AND v.month = md.month\r\n"
					+ " WHERE v.status != 3')\r\n"
					+ "   AS t(employee_no character varying,full_name character varying, designation character varying,dob timestamp without time zone,\r\n"
					+ "           classification_services character varying ,civ_group character varying ,\r\n"
					+ "              civ_type character varying ,joining_date_gov_service timestamp without time zone ,date_of_tos timestamp without time zone ,\r\n"
					+ "                designation_date timestamp without time zone,gender_name character varying,     month character varying,\r\n"
					+ "               year character varying,cmd_sus character varying,corp_sus character varying,\r\n"
					+ "	 div_sus character varying,bde_sus character varying,sus_no character varying,civilian_status character varying)) np  "
					+ qry;
			
			
			
			

			stmt = c.prepareStatement(q);

			if (!qry.equals("")) {

				int j = 1;
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
				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				if (!civilian_status.equals("")) {
					stmt.setString(j, civilian_status.toUpperCase());
					// j += 1;
				}
			}

			  
			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 0;
			while (rs.next()) {
				ArrayList<String> list_Search = new ArrayList<String>();
				i++;
				list_Search.add(String.valueOf(i));// 0
				list_Search.add(rs.getString("employee_no"));// 1
				list_Search.add(rs.getString("full_name"));// 2
				list_Search.add(rs.getString("designation"));// 3
				list_Search.add(rs.getString("dob"));// 4
				list_Search.add(rs.getString("classification_services"));// 5
				list_Search.add(rs.getString("civ_group"));// 6
				list_Search.add(rs.getString("civ_type"));// 7
				list_Search.add(rs.getString("joining_date_gov_service"));// 8
				list_Search.add(rs.getString("date_of_tos"));// 9
				list_Search.add(rs.getString("designation_date"));// 10
				list_Search.add(rs.getString("gender_name"));// 11
				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
		 
		return alist;
	}

	public ArrayList<ArrayList<String>> Report_auth_civ(String month, String year, String comd_sus, String corp_sus,
			String div_sus, String bde_sus, String unit_sus_no, String civilian_status) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String q = "";
		String qry = "";
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres",
					"postgres");
			c.setAutoCommit(false);
			q = "SELECT dblink_connect('myconn','miso_psg_oltp1')";
			stmt = c.prepareStatement(q);
			rs = stmt.executeQuery();

			 

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));
				 
			}
//			if (!month.equals("0")) {
//				qry += " where auth.month = ? ";
//			}
//			if (!year.equals("")) {
//				qry += " and auth.year = ? ";
//			}
//			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
//				qry += " and md.cmd_sus = ? ";
//			}
//			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
//				qry += " and md.corp_sus = ? ";
//			}
//			if (!div_sus.equals("0") && !div_sus.equals("")) {
//				qry += " and md.div_sus = ? ";
//			}
//			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
//				qry += " and md.bde_sus = ? ";
//			}
//			if (!unit_sus_no.equals("")) {
//				qry += " and auth.sus_no = ?  ";
//			}
//			if (!civilian_status.equals("")) {
//				qry += " and upper(md.civilian_status) = ? ";
//			}
//
//			q = "SELECT DISTINCT COALESCE (sum(auth.group_a_gaz),'0') AS gp_a_gazetted,\r\n"
//					+ "       COALESCE (sum(auth.group_b_gaz),'0') AS gp_b_gazetted,\r\n"
//					+ "       COALESCE (sum(auth.group_b_non_gaz_non_ind),'0') AS gp_b_non_gazetted_non_ind,\r\n"
//					+ "       COALESCE (sum(auth.group_c_non_gaz_non_ind),'0') AS gp_c_non_gazetted_non_ind,\r\n"
//					+ "       COALESCE (sum(auth.group_b_non_gaz_ind),'0') AS gp_b_non_gazetted_ind,\r\n"
//					+ "       COALESCE (sum(auth.group_c_non_gaz_ind),'0') AS gp_c_non_gazetted_ind,\r\n"
//					+ "       coalesce(sum(auth.group_a_gaz + auth.group_b_gaz + auth.group_b_non_gaz_non_ind + auth.group_c_non_gaz_non_ind + auth.group_b_non_gaz_ind + auth.group_c_non_gaz_ind),0) AS total\r\n"
//					+ "  FROM tb_psg_auth_civ AS auth\r\n"
//					+ " INNER JOIN tb_psg_held_str_civ_main_details AS md\r\n"
//					+ "    ON auth.sus_no = md.sus_no\r\n"
//					+ "   AND auth.year = md.year\r\n"
//					+ "   AND auth.version = md.version\r\n"
//					+ "   AND auth.month = md.month\r\n"
//					+ "   AND md.status != 3"
//					+ qry;
			
			
			if (!month.equals("0")) {
				qry += " where np.month = ? ";
			}
			if (!year.equals("")) {
				qry += " and np.year = ? ";
			}
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and np.cmd_sus = ? ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and np.corp_sus = ? ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and np.div_sus = ? ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and np.bde_sus = ? ";
			}
			if (!unit_sus_no.equals("")) {
				qry += " and np.sus_no = ?  ";
			}
			if (!civilian_status.equals("")) {
				qry += " and upper(np.civilian_status) = ? ";
			}

			q = "SELECT *\r\n"
					+ "  FROM (\r\n"
					+ "        SELECT DISTINCT gp_a_gazetted,\r\n"
					+ "               gp_b_gazetted,\r\n"
					+ "               gp_b_non_gazetted_non_ind,\r\n"
					+ "               gp_c_non_gazetted_non_ind,\r\n"
					+ "               gp_b_non_gazetted_ind,\r\n"
					+ "               gp_c_non_gazetted_ind,\r\n"
					+ "               total, MONTH,\r\n"
					+ "               YEAR,\r\n"
					+ "               cmd_sus,\r\n"
					+ "               corp_sus,\r\n"
					+ "               div_sus,\r\n"
					+ "               bde_sus,\r\n"
					+ "               sus_no,\r\n"
					+ "               civilian_status\r\n"
					+ "          FROM dblink ('myconn','SELECT DISTINCT COALESCE (auth.group_a_gaz) AS gp_a_gazetted,\r\n"
					+ "       COALESCE (auth.group_b_gaz) AS gp_b_gazetted,\r\n"
					+ "       COALESCE (auth.group_b_non_gaz_non_ind) AS gp_b_non_gazetted_non_ind,\r\n"
					+ "       COALESCE (auth.group_c_non_gaz_non_ind) AS gp_c_non_gazetted_non_ind,\r\n"
					+ "       COALESCE(auth.group_b_non_gaz_ind) AS gp_b_non_gazetted_ind,\r\n"
					+ "       COALESCE (auth.group_c_non_gaz_ind) AS gp_c_non_gazetted_ind,\r\n"
					+ "       coalesce(sum(auth.group_a_gaz + auth.group_b_gaz + auth.group_b_non_gaz_non_ind + auth.group_c_non_gaz_non_ind + auth.group_b_non_gaz_ind + auth.group_c_non_gaz_ind),0) AS total,\r\n"
					+ "       auth.month,\r\n"
					+ "       auth.year,       \r\n"
					+ "       md.cmd_sus,\r\n"
					+ "       md.corp_sus,\r\n"
					+ "       md.div_sus,\r\n"
					+ "       md.bde_sus,\r\n"
					+ "       md.sus_no,\r\n"
					+ "       md.civilian_status\r\n"
					+ "  FROM tb_psg_held_str_civ_main AS v\r\n"
					+ " INNER JOIN tb_psg_auth_civ AS auth\r\n"
					+ "    ON v.sus_no = auth.sus_no\r\n"
					+ "   AND v.year = auth.year\r\n"
					+ "   AND v.version = auth.version\r\n"
					+ "   AND v.month = auth.month\r\n"
					+ " INNER JOIN tb_psg_held_str_civ_main_details AS md\r\n"
					+ "    ON v.sus_no = md.sus_no\r\n"
					+ "   AND v.year = md.year\r\n"
					+ "   AND v.version = md.version\r\n"
					+ "   AND v.month = md.month\r\n"
					+ " WHERE v.status != 3\r\n"
					+ " group by 1,2,3,4,5,6,8,9,10,11,12,13,14,15') \r\n"
					+ "    AS t(gp_a_gazetted integer,gp_b_gazetted integer,gp_b_non_gazetted_non_ind integer, gp_c_non_gazetted_non_ind integer,\r\n"
					+ "         gp_b_non_gazetted_ind integer, gp_c_non_gazetted_ind integer,total integer, MONTH CHARACTER varying,\r\n"
					+ "         YEAR CHARACTER varying,cmd_sus CHARACTER varying,corp_sus CHARACTER varying, div_sus CHARACTER varying,\r\n"
					+ "         bde_sus CHARACTER varying,sus_no CHARACTER varying,civilian_status CHARACTER varying)\r\n"
					+ "       ) np"
					+ qry;

//
//			
			stmt = c.prepareStatement(q);

			if (!qry.equals("")) {

				int j = 1;
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
				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				if (!civilian_status.equals("")) {
					stmt.setString(j, civilian_status.toUpperCase());
					// j += 1;
				}
			}

			  
			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 0;
			while (rs.next()) {
				ArrayList<String> list_Search = new ArrayList<String>();
				i++;

				list_Search.add(rs.getString("gp_a_gazetted"));// 0
				list_Search.add(rs.getString("gp_b_gazetted"));// 1
				list_Search.add(rs.getString("gp_b_non_gazetted_non_ind"));// 2
				list_Search.add(rs.getString("gp_c_non_gazetted_non_ind"));// 3
				list_Search.add(rs.getString("gp_b_non_gazetted_ind"));// 4
				list_Search.add(rs.getString("gp_c_non_gazetted_ind"));// 5
				list_Search.add(rs.getString("total"));// 5
				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
		 
		return alist;
	}

	public ArrayList<ArrayList<String>> Report_posted_civ(String month, String year, String comd_sus, String corp_sus,
			String div_sus, String bde_sus, String unit_sus_no, String civilian_status) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String q = "";
		String qry = "";

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres",
					"postgres");
			c.setAutoCommit(false);
			q = "SELECT dblink_connect('myconn','miso_psg_oltp1')";
			stmt = c.prepareStatement(q);
			rs = stmt.executeQuery();

			 

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));
				 
			}
//			if (!month.equals("0")) {
//				qry += " where auth.month = ? ";
//			}
//			if (!year.equals("")) {
//				qry += " and auth.year = ? ";
//			}
//			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
//				qry += " and md.cmd_sus = ? ";
//			}
//			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
//				qry += " and md.corp_sus = ? ";
//			}
//			if (!div_sus.equals("0") && !div_sus.equals("")) {
//				qry += " and md.div_sus = ? ";
//			}
//			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
//				qry += " and md.bde_sus = ? ";
//			}
//			if (!unit_sus_no.equals("")) {
//				qry += " and auth.sus_no = ?  ";
//			}
//			if (!civilian_status.equals("")) {
//				qry += " and upper(md.civilian_status) = ? ";
//			}
//
//			q = "SELECT DISTINCT COALESCE (sum(auth.group_a_gaz),'0') AS gp_a_gazetted,\r\n"
//					+ "       COALESCE (sum(auth.group_b_gaz),'0') AS gp_b_gazetted,\r\n"
//					+ "       COALESCE (sum(auth.group_b_non_gaz_non_ind),'0') AS gp_b_non_gazetted_non_ind,\r\n"
//					+ "       COALESCE (sum(auth.group_c_non_gaz_non_ind),'0') AS gp_c_non_gazetted_non_ind,\r\n"
//					+ "       COALESCE (sum(auth.group_b_non_gaz_ind),'0') AS gp_b_non_gazetted_ind,\r\n"
//					+ "       COALESCE (sum(auth.group_c_non_gaz_ind),'0') AS gp_c_non_gazetted_ind,\r\n"
//					+ "       coalesce(sum(auth.group_a_gaz + auth.group_b_gaz + auth.group_b_non_gaz_non_ind + auth.group_c_non_gaz_non_ind + auth.group_b_non_gaz_ind + auth.group_c_non_gaz_ind),0) AS total\r\n"
//					+ "  FROM tb_psg_posted_civ AS auth\r\n"
//					+ " INNER JOIN tb_psg_held_str_civ_main_details AS md\r\n"
//					+ "    ON auth.sus_no = md.sus_no\r\n"
//					+ "   AND auth.year = md.year\r\n"
//					+ "   AND auth.version = md.version\r\n"
//					+ "   AND auth.month = md.month\r\n"
//					+ "   AND md.status != 3"
//					+ qry;
			
			
			
			if (!month.equals("0")) {
				qry += " where np.month = ? ";
			}
			if (!year.equals("")) {
				qry += " and np.year = ? ";
			}
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and np.cmd_sus = ? ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and np.corp_sus = ? ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and np.div_sus = ? ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and np.bde_sus = ? ";
			}
			if (!unit_sus_no.equals("")) {
				qry += " and np.sus_no = ?  ";
			}
			if (!civilian_status.equals("")) {
				qry += " and upper(np.civilian_status) = ? ";
			}

			q = "SELECT *\r\n"
					+ "  FROM (\r\n"
					+ "        SELECT DISTINCT gp_a_gazetted,\r\n"
					+ "               gp_b_gazetted,\r\n"
					+ "               gp_b_non_gazetted_non_ind,\r\n"
					+ "               gp_c_non_gazetted_non_ind,\r\n"
					+ "               gp_b_non_gazetted_ind,\r\n"
					+ "               gp_c_non_gazetted_ind,\r\n"
					+ "               total, MONTH,\r\n"
					+ "               YEAR,\r\n"
					+ "               cmd_sus,\r\n"
					+ "               corp_sus,\r\n"
					+ "               div_sus,\r\n"
					+ "               bde_sus,\r\n"
					+ "               sus_no,\r\n"
					+ "               civilian_status\r\n"
					+ "          FROM dblink ('myconn','SELECT DISTINCT COALESCE (auth.group_a_gaz)AS gp_a_gazetted,\r\n"
					+ "       COALESCE (auth.group_b_gaz) AS gp_b_gazetted,\r\n"
					+ "       COALESCE (auth.group_b_non_gaz_non_ind) AS gp_b_non_gazetted_non_ind,\r\n"
					+ "       COALESCE (auth.group_c_non_gaz_non_ind) AS gp_c_non_gazetted_non_ind,\r\n"
					+ "       COALESCE (auth.group_b_non_gaz_ind) AS gp_b_non_gazetted_ind,\r\n"
					+ "       COALESCE (auth.group_c_non_gaz_ind) AS gp_c_non_gazetted_ind,\r\n"
					+ "       coalesce(sum(auth.group_a_gaz + auth.group_b_gaz + auth.group_b_non_gaz_non_ind + auth.group_c_non_gaz_non_ind + auth.group_b_non_gaz_ind + auth.group_c_non_gaz_ind),0) AS total,\r\n"
					+ "       auth.month,\r\n"
					+ "       auth.year,       \r\n"
					+ "       md.cmd_sus,\r\n"
					+ "       md.corp_sus,\r\n"
					+ "       md.div_sus,\r\n"
					+ "       md.bde_sus,\r\n"
					+ "       auth.sus_no,\r\n"
					+ "       v.civilian_status\r\n"
					+ "  FROM tb_psg_held_str_civ_main AS v\r\n"
					+ " INNER JOIN tb_psg_posted_civ AS auth\r\n"
					+ "    ON v.sus_no = auth.sus_no\r\n"
					+ "   AND v.year = auth.year\r\n"
					+ "   AND v.version = auth.version\r\n"
					+ "   AND v.month = auth.month\r\n"
					+ " INNER JOIN tb_psg_held_str_civ_main_details AS md\r\n"
					+ "    ON v.sus_no = md.sus_no\r\n"
					+ "   AND v.year = md.year\r\n"
					+ "   AND v.version = md.version\r\n"
					+ "   AND v.month = md.month\r\n"
					+ " WHERE v.status != 3\r\n"
					+ " group by 1,2,3,4,5,6,8,9,10,11,12,13,14,15') \r\n"
					+ "    AS t(gp_a_gazetted integer,gp_b_gazetted integer,gp_b_non_gazetted_non_ind integer, gp_c_non_gazetted_non_ind integer,\r\n"
					+ "         gp_b_non_gazetted_ind integer, gp_c_non_gazetted_ind integer,total integer, MONTH CHARACTER varying,\r\n"
					+ "         YEAR CHARACTER varying,cmd_sus CHARACTER varying,corp_sus CHARACTER varying, div_sus CHARACTER varying,\r\n"
					+ "         bde_sus CHARACTER varying,sus_no CHARACTER varying,civilian_status CHARACTER varying)\r\n"
					+ "       ) np"
					+ qry;

			stmt = c.prepareStatement(q);

			if (!qry.equals("")) {

				int j = 1;
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
				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				if (!civilian_status.equals("")) {
					stmt.setString(j, civilian_status.toUpperCase());
					// j += 1;
				}
			}

			  
			rs = stmt.executeQuery();
			

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 0;
			while (rs.next()) {
				ArrayList<String> list_Search = new ArrayList<String>();
				i++;
				// list_Search.add(String.valueOf(i));// 0
				list_Search.add(rs.getString("gp_a_gazetted"));// 0
				list_Search.add(rs.getString("gp_b_gazetted"));// 1
				list_Search.add(rs.getString("gp_b_non_gazetted_non_ind"));// 2
				list_Search.add(rs.getString("gp_c_non_gazetted_non_ind"));// 3
				list_Search.add(rs.getString("gp_b_non_gazetted_ind"));// 4
				list_Search.add(rs.getString("gp_c_non_gazetted_ind"));// 5
				list_Search.add(rs.getString("total"));// 5

				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
		 
		return alist;

	}

	// ---------------------MAIN DETAILS

	public ArrayList<ArrayList<String>> Report_MainDetails_civ(String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (!month.equals("0")) {
				qry += " where month = ? ";
			}
			if (!year.equals("")) {
				qry += " and year = ? ";
			}

			if (!unit_sus_no.equals("")) {
				qry += " and sus_no = ?  ";
			}
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and cmd_sus = ? ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and corp_sus = ? ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and div_sus = ? ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and bde_sus = ? ";
			}

			q = "select distinct present_return_no,TO_CHAR(present_return_dt,'DD-MON-YYYY') as present_return_dt,we_pe_no,auth,held,\r\n"
					+ "						   TO_CHAR(TO_TIMESTAMP(month,'MM'),'MON') as month,year,cmd_sus,corp_sus,div_sus,bde_sus,sus_no\r\n"
					+ "					from tb_psg_held_str_civ_main_details m\r\n" + qry;

			stmt = conn.prepareStatement(q);
			if (!qry.equals("")) {

				int j = 1;
				if (!month.equals("0")) {
					stmt.setString(j, month);
					j += 1;
				}
				if (year != "") {
					stmt.setString(j, year);
					j += 1;
				}

				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
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

			}

			int i = 0;
			  
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list_Search = new ArrayList<String>();
				list_Search.add(String.valueOf(i));// 0
				list_Search.add(rs.getString("present_return_no"));// 1
				list_Search.add(rs.getString("present_return_dt"));// 2
				list_Search.add(rs.getString("we_pe_no"));// 3
				list_Search.add(rs.getString("auth"));// 4
				list_Search.add(rs.getString("held"));// 5
				list_Search.add(rs.getString("month"));// 6
				list_Search.add(rs.getString("year"));// 7
				list_Search.add(rs.getString("sus_no"));// 8

				alist.add(list_Search);
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

	public ArrayList<ArrayList<String>> Report_summary_civ(String month, String year, String comd_sus, String corp_sus,
			String div_sus, String bde_sus, String unit_sus_no,String civilian_status)

	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String q = "";
		String qry = "";

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres",
					"postgres");
			c.setAutoCommit(false);
			q = "SELECT dblink_connect('myconn','miso_psg_oltp1')";
			stmt = c.prepareStatement(q);
			rs = stmt.executeQuery();

			 

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));
				 
			}
//			if (!month.equals("0")) {
//				qry += " where ds.month = ? ";
//			}
//			if (!year.equals("")) {
//				qry += " and ds.year = ? ";
//			}
//			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
//				qry += " and md.cmd_sus = ? ";
//			}
//			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
//				qry += " and md.corp_sus = ? ";
//			}
//			if (!div_sus.equals("0") && !div_sus.equals("")) {
//				qry += " and md.div_sus = ? ";
//			}
//			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
//				qry += " and md.bde_sus = ? ";
//			}
//			if (!unit_sus_no.equals("")) {
//				qry += " and ds.sus_no = ?  ";
//			}
//			if (!civilian_status.equals("")) {
//				qry += " and upper(md.civilian_status) = ? ";
//			}
//
//			q = "SELECT DISTINCT sum(cast(ds.civ_auth AS numeric)) AS civ_auth,\r\n"
//					+ "       sum(cast(ds.civ_posted AS numeric)) AS civ_posted,\r\n"
//					+ "       sum(cast(ds.civ_sur AS numeric)) AS civ_sur,\r\n"
//					+ "       sum(cast(ds.civ_defi AS numeric)) AS civ_defi\r\n"
//					+ "  FROM tb_psg_summary_civ AS ds\r\n"
//					+ " INNER JOIN tb_psg_held_str_civ_main_details AS md\r\n"
//					+ "    ON ds.sus_no = md.sus_no\r\n"
//					+ "   AND ds.year = md.year\r\n"
//					+ "   AND ds.version = md.version\r\n"
//					+ "   AND ds.month = md.month\r\n"
//					+ "   AND md.status != 3"
//					+ qry;
			
			if (!month.equals("0")) {
				qry += " where np.month = ? ";
			}
			if (!year.equals("")) {
				qry += " and np.year = ? ";
			}
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and np.cmd_sus = ? ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and np.corp_sus = ? ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and np.div_sus = ? ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and np.bde_sus = ? ";
			}
			if (!unit_sus_no.equals("")) {
				qry += " and np.sus_no = ?  ";
			}
			if (!civilian_status.equals("")) {
				qry += " and upper(np.civilian_status) = ? ";
			}

			q = "SELECT *\r\n"
					+ "  FROM (\r\n"
					+ "        SELECT DISTINCT civ_auth,\r\n"
					+ "               civ_posted,\r\n"
					+ "               civ_sur,\r\n"
					+ "               civ_defi,           \r\n"
					+ "               MONTH,\r\n"
					+ "               YEAR,\r\n"
					+ "               cmd_sus,\r\n"
					+ "               corp_sus,\r\n"
					+ "               div_sus,\r\n"
					+ "               bde_sus,\r\n"
					+ "               sus_no,\r\n"
					+ "               civilian_status\r\n"
					+ "          FROM dblink('myconn','SELECT DISTINCT cast(ds.civ_auth AS numeric)AS civ_auth,\r\n"
					+ "       cast(ds.civ_posted AS numeric)AS civ_posted,\r\n"
					+ "       cast(ds.civ_sur AS numeric)AS civ_sur,\r\n"
					+ "       cast(ds.civ_defi AS numeric)AS civ_defi,\r\n"
					+ "       ds.month,\r\n"
					+ "       ds.year,\r\n"
					+ "       md.cmd_sus,\r\n"
					+ "       md.corp_sus,\r\n"
					+ "       md.div_sus,\r\n"
					+ "       md.bde_sus,\r\n"
					+ "       md.sus_no,\r\n"
					+ "       md.civilian_status\r\n"
					+ "  FROM tb_psg_held_str_civ_main AS v\r\n"
					+ " INNER JOIN tb_psg_summary_civ AS ds\r\n"
					+ "    ON v.sus_no = ds.sus_no\r\n"
					+ "   AND v.year = ds.year\r\n"
					+ "   AND v.version = ds.version\r\n"
					+ "   AND v.month = ds.month\r\n"
					+ " INNER JOIN tb_psg_held_str_civ_main_details AS md\r\n"
					+ "    ON v.sus_no = md.sus_no\r\n"
					+ "   AND v.year = md.year\r\n"
					+ "   AND v.version = md.version\r\n"
					+ "   AND v.month = md.month\r\n"
					+ " WHERE v.status != 3') AS t(civ_auth integer,civ_posted integer,civ_sur integer, civ_defi integer\r\n"
					+ "                            , MONTH CHARACTER varying, YEAR CHARACTER varying,cmd_sus CHARACTER varying,\r\n"
					+ "                            corp_sus CHARACTER varying, div_sus CHARACTER varying, bde_sus CHARACTER varying\r\n"
					+ "                            ,sus_no CHARACTER varying,civilian_status CHARACTER varying)\r\n"
					+ "       ) np"
					+ qry;

			stmt = c.prepareStatement(q);

			if (!qry.equals("")) {

				int j = 1;
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
				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				if (!civilian_status.equals("")) {
					stmt.setString(j, civilian_status.toUpperCase());
					// j += 1;
				}

			}

			rs = stmt.executeQuery();
			

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 0;
			while (rs.next()) {
				ArrayList<String> list_Search = new ArrayList<String>();
				i++;

				// list.add(String.valueOf(i));
				list_Search.add(rs.getString("civ_auth"));// 0
				list_Search.add(rs.getString("civ_posted"));// 1
				list_Search.add(rs.getString("civ_sur"));// 2
				list_Search.add(rs.getString("civ_defi"));// 3

				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
		 
		return alist;

	}
	
	public ArrayList<ArrayList<String>> Report_non_regular_civ(String month, String year, String comd_sus, String corp_sus,
			String div_sus, String bde_sus, String unit_sus_no,String civilian_status)

	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String q = "";
		String qry = "";

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres",
					"postgres");
			c.setAutoCommit(false);
			q = "SELECT dblink_connect('myconn','miso_psg_oltp1')";
			stmt = c.prepareStatement(q);
			rs = stmt.executeQuery();

			

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));
				 
			}
//			if (!month.equals("0")) {
//				qry += " where ds.month = ? ";
//			}
//			if (!year.equals("")) {
//				qry += " and ds.year = ? ";
//			}
//			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
//				qry += " and md.cmd_sus = ? ";
//			}
//			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
//				qry += " and md.corp_sus = ? ";
//			}
//			if (!div_sus.equals("0") && !div_sus.equals("")) {
//				qry += " and md.div_sus = ? ";
//			}
//			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
//				qry += " and md.bde_sus = ? ";
//			}
//			if (!unit_sus_no.equals("")) {
//				qry += " and ds.sus_no = ?  ";
//			}
//			if (!civilian_status.equals("")) {
//				qry += " and upper(md.civilian_status) = ? ";
//			}
//
//			q = "SELECT DISTINCT ds.industrial,\r\n"
//					+ "       ds.non_industrial,\r\n"
//					+ "       ds.total\r\n"
//					+ "  FROM tb_psg_non_regular_civ AS ds\r\n"
//					+ " INNER JOIN tb_psg_held_str_civ_main_details AS md\r\n"
//					+ "    ON ds.sus_no = md.sus_no\r\n"
//					+ "   AND ds.year = md.year\r\n"
//					+ "   AND ds.version = md.version\r\n"
//					+ "   AND ds.month = md.month\r\n"
//					+ "   AND md.status != 3"
//					+ qry;
			
			if (!month.equals("0")) {
				qry += " where np.month = ? ";
			}
			if (!year.equals("")) {
				qry += " and np.year = ? ";
			}
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and np.cmd_sus = ? ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and np.corp_sus = ? ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and np.div_sus = ? ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and np.bde_sus = ? ";
			}
			if (!unit_sus_no.equals("")) {
				qry += " and np.sus_no = ?  ";
			}
			if (!civilian_status.equals("")) {
				qry += " and upper(np.civilian_status) = ? ";
			}

			q = "SELECT *\r\n"
					+ "  FROM (\r\n"
					+ "        SELECT DISTINCT industrial,\r\n"
					+ "               non_industrial,\r\n"
					+ "               total,\r\n"
					+ "               MONTH,\r\n"
					+ "               YEAR,\r\n"
					+ "               cmd_sus,\r\n"
					+ "               corp_sus,\r\n"
					+ "               div_sus,\r\n"
					+ "               bde_sus,\r\n"
					+ "               sus_no,\r\n"
					+ "               civilian_status\r\n"
					+ "          FROM dblink ('myconn','SELECT DISTINCT ds.industrial,\r\n"
					+ "       ds.non_industrial,\r\n"
					+ "       ds.total,\r\n"
					+ "       ds.month,\r\n"
					+ "       ds.year,       \r\n"
					+ "       md.cmd_sus,\r\n"
					+ "       md.corp_sus,\r\n"
					+ "       md.div_sus,\r\n"
					+ "       md.bde_sus,\r\n"
					+ "       md.sus_no,\r\n"
					+ "       md.civilian_status\r\n"
					+ "  FROM tb_psg_held_str_civ_main AS v\r\n"
					+ " INNER JOIN tb_psg_non_regular_civ AS ds\r\n"
					+ "    ON v.sus_no = ds.sus_no\r\n"
					+ "   AND v.year = ds.year\r\n"
					+ "   AND v.version = ds.version\r\n"
					+ "   AND v.month = ds.month\r\n"
					+ " INNER JOIN tb_psg_held_str_civ_main_details AS md\r\n"
					+ "    ON v.sus_no = md.sus_no\r\n"
					+ "   AND v.year = md.year\r\n"
					+ "   AND v.version = md.version\r\n"
					+ "   AND v.month = md.month\r\n"
					+ " WHERE v.status != 3') \r\n"
					+ "    AS t(industrial integer,non_industrial integer,total integer, MONTH CHARACTER varying,\r\n"
					+ "         YEAR CHARACTER varying,cmd_sus CHARACTER varying,corp_sus CHARACTER varying, div_sus CHARACTER varying,\r\n"
					+ "         bde_sus CHARACTER varying,sus_no CHARACTER varying,civilian_status CHARACTER varying)\r\n"
					+ "       ) np"
					+ qry;

			stmt = c.prepareStatement(q);

			if (!qry.equals("")) {

				int j = 1;
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
				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				if (!civilian_status.equals("")) {
					stmt.setString(j, civilian_status.toUpperCase());
					// j += 1;
				}

			}

			rs = stmt.executeQuery();
			

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 0;
			while (rs.next()) {
				ArrayList<String> list_Search = new ArrayList<String>();
				i++;

				// list.add(String.valueOf(i));
				list_Search.add(rs.getString("industrial"));// 0
				list_Search.add(rs.getString("non_industrial"));// 1
				list_Search.add(rs.getString("total"));// 2				
				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
		 
		return alist;

	}

}
