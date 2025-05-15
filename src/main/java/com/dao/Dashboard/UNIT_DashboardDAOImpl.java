package com.dao.Dashboard;

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

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.persistance.util.HibernateUtil;

public class UNIT_DashboardDAOImpl implements UNIT_DashboardDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Override
	public List<Map<String, Object>> Getcount_offHeld_data(String sus_no,String from_code_control,String formationtype) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if(formationtype.equals("Brigade")) {
				q = " SELECT\n"
						+ " COALESCE(SUM(n1.brig_above) + SUM(n1.col) + SUM(n1.col_ts) + SUM(n1.ltcol) + SUM(n1.maj) + SUM(n1.capt_lt),0) AS offrsPosted  \n"
						+ "FROM\n" + "  tb_miso_orbat_arm_code n0\n" + "LEFT JOIN\n" + "  (\n" + "    SELECT\n"
						+ "      e.arm_code, c.unit_sus_no AS sus_no,\n"
						+ "      COUNT(*) FILTER (WHERE r.description IN ('GEN', 'LT GEN', 'MAJ GEN', 'BRIG')) AS brig_above,\n"
						+ "      COUNT(*) FILTER (WHERE r.description = 'COL') AS col,\n"
						+ "      COUNT(*) FILTER (WHERE r.description = 'COL [TS]') AS col_ts,\n"
						+ "      COUNT(*) FILTER (WHERE r.description = 'LT COL') AS ltcol,\n"
						+ "      COUNT(*) FILTER (WHERE r.description = 'MAJ') AS maj,\n"
						+ "      COUNT(*) FILTER (WHERE r.description IN ('CAPT', 'LT')) AS capt_lt\n"
						+ "    FROM tb_psg_trans_proposed_comm_letter c\n"
						+ "    INNER JOIN cue_tb_psg_rank_app_master r ON c.rank = r.id AND c.status IN ('1', '5') AND UPPER(r.level_in_hierarchy) = 'RANK'\n"
						+ "    LEFT JOIN tb_miso_orbat_arm_code e ON e.arm_code = c.parent_arm\n"
						+ "     left join (select max(dt_of_tos) as dt_of_tos,comm_id as comm_id from tb_psg_posting_in_out group by comm_id ) tos on tos.comm_id = c.id\n"
						+ "    WHERE  c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')   AND LEFT(c.personnel_no, 2) NOT IN ('NR', 'NS')\n" + "    GROUP BY 1, 2\n" + "  ) n1 ON n0.arm_code = n1.arm_code";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
			}else {
				q = " SELECT\n"
						+ " COALESCE(SUM(n1.brig_above) + SUM(n1.col) + SUM(n1.col_ts) + SUM(n1.ltcol) + SUM(n1.maj) + SUM(n1.capt_lt),0) AS offrsPosted  \n"
						+ "FROM\n" + "  tb_miso_orbat_arm_code n0\n" + "LEFT JOIN\n" + "  (\n" + "    SELECT\n"
						+ "      e.arm_code, c.unit_sus_no AS sus_no,\n"
						+ "      COUNT(*) FILTER (WHERE r.description IN ('GEN', 'LT GEN', 'MAJ GEN', 'BRIG')) AS brig_above,\n"
						+ "      COUNT(*) FILTER (WHERE r.description = 'COL') AS col,\n"
						+ "      COUNT(*) FILTER (WHERE r.description = 'COL [TS]') AS col_ts,\n"
						+ "      COUNT(*) FILTER (WHERE r.description = 'LT COL') AS ltcol,\n"
						+ "      COUNT(*) FILTER (WHERE r.description = 'MAJ') AS maj,\n"
						+ "      COUNT(*) FILTER (WHERE r.description IN ('CAPT', 'LT')) AS capt_lt\n"
						+ "    FROM tb_psg_trans_proposed_comm_letter c\n"
						+ "    INNER JOIN cue_tb_psg_rank_app_master r ON c.rank = r.id AND c.status IN ('1', '5') AND UPPER(r.level_in_hierarchy) = 'RANK'\n"
						+ "    LEFT JOIN tb_miso_orbat_arm_code e ON e.arm_code = c.parent_arm\n"
						+ "     left join (select max(dt_of_tos) as dt_of_tos,comm_id as comm_id from tb_psg_posting_in_out group by comm_id ) tos on tos.comm_id = c.id\n"
						+ "    WHERE  c.unit_sus_no =?   AND LEFT(c.personnel_no, 2) NOT IN ('NR', 'NS') \n" + "    GROUP BY 1, 2\n" + "  ) n1 ON n0.arm_code = n1.arm_code";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);
			}

			//System.err.println("dash held offrs count: \n" +stmt);
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
	public List<Map<String, Object>> Getcount_jcoHeld_data(String sus_no,String from_code_control,String formationtype) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if(formationtype.equals("Brigade")) {
				q = " SELECT\n" + " COALESCE(SUM(n2.sub_maj) + SUM(n2.sub) + SUM(n2.nb_sub) + SUM(n2.warrant_off),0) AS jcosPosted\n"
						+ "FROM\n" + "  tb_miso_orbat_arm_code n0\n" + "LEFT JOIN\n" + "  (\n"
						+ "    SELECT   e.arm_code,  c.unit_sus_no,\n"
						+ "      COUNT(*) FILTER (WHERE r.rank = 'SUB MAJ / EQUIVALENT') AS sub_maj,\n"
						+ "      COUNT(*) FILTER (WHERE r.rank = 'SUB / EQUIVALENT') AS sub,\n"
						+ "      COUNT(*) FILTER (WHERE r.rank = 'NB SUB / EQUIVALENT') AS nb_sub,\n"
						+ "      COUNT(*) FILTER (WHERE r.rank = 'WARRANT OFFICER') AS warrant_off\n" + "  \n"
						+ "    FROM tb_psg_census_jco_or_p c\n"
						+ "    INNER JOIN tb_psg_mstr_rank_jco r ON c.rank = r.id AND c.status = '1'\n"
						+ "    LEFT JOIN tb_miso_orbat_arm_code e ON e.arm_code = c.arm_service\n"
						+ "    WHERE  c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "    GROUP BY  1, 2\n" + "  ) n2 ON n0.arm_code = n2.arm_code";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
			}else {
				q = " SELECT\n" + " COALESCE(SUM(n2.sub_maj) + SUM(n2.sub) + SUM(n2.nb_sub) + SUM(n2.warrant_off),0) AS jcosPosted\n"
						+ "FROM\n" + "  tb_miso_orbat_arm_code n0\n" + "LEFT JOIN\n" + "  (\n"
						+ "    SELECT   e.arm_code,  c.unit_sus_no,\n"
						+ "      COUNT(*) FILTER (WHERE r.rank = 'SUB MAJ / EQUIVALENT') AS sub_maj,\n"
						+ "      COUNT(*) FILTER (WHERE r.rank = 'SUB / EQUIVALENT') AS sub,\n"
						+ "      COUNT(*) FILTER (WHERE r.rank = 'NB SUB / EQUIVALENT') AS nb_sub,\n"
						+ "      COUNT(*) FILTER (WHERE r.rank = 'WARRANT OFFICER') AS warrant_off\n" + "  \n"
						+ "    FROM tb_psg_census_jco_or_p c\n"
						+ "    INNER JOIN tb_psg_mstr_rank_jco r ON c.rank = r.id AND c.status = '1'\n"
						+ "    LEFT JOIN tb_miso_orbat_arm_code e ON e.arm_code = c.arm_service\n"
						+ "    WHERE  c.unit_sus_no = ?\n" + "    GROUP BY  1, 2\n" + "  ) n2 ON n0.arm_code = n2.arm_code";
				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);

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

	@Override
	public List<Map<String, Object>> Getcount_orHeld_data(String sus_no,String from_code_control,String formationtype) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if(formationtype.equals("Brigade")) {
				q = "  SELECT \n" + " COALESCE(SUM(n2.hav) + SUM(n2.nk) + SUM(n2.sep) + SUM(n2.rect),0) AS orPosted\n" + "FROM\n"
						+ "    tb_miso_orbat_arm_code n0\n" + "LEFT JOIN\n" + "    (\n"
						+ "        SELECT     e.arm_code,   c.unit_sus_no,     \n"
						+ "            COUNT(*) FILTER (WHERE r.rank = 'HAV / EQUIVALENT') AS hav,\n"
						+ "            COUNT(*) FILTER (WHERE r.rank = 'NK / EQUIVALENT') AS nk,\n"
						+ "            COUNT(*) FILTER (WHERE r.rank = 'SEP / EQUIVALENT') AS sep,\n"
						+ "            COUNT(*) FILTER (WHERE r.rank = 'RECTS') AS rect\n"
						+ "        FROM tb_psg_census_jco_or_p c\n"
						+ "        INNER JOIN tb_psg_mstr_rank_jco r ON c.rank = r.id AND c.status = '1'\n"
						+ "        LEFT JOIN tb_miso_orbat_arm_code e ON e.arm_code = c.arm_service\n"
						+ "        WHERE   c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')    \n" + "        GROUP BY   1, 2\n"
						+ "    ) n2 ON n0.arm_code = n2.arm_code";


				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
			}else {
				q = "  SELECT \n" + " COALESCE(SUM(n2.hav) + SUM(n2.nk) + SUM(n2.sep) + SUM(n2.rect),0) AS orPosted\n" + "FROM\n"
						+ "    tb_miso_orbat_arm_code n0\n" + "LEFT JOIN\n" + "    (\n"
						+ "        SELECT     e.arm_code,   c.unit_sus_no,     \n"
						+ "            COUNT(*) FILTER (WHERE r.rank = 'HAV / EQUIVALENT') AS hav,\n"
						+ "            COUNT(*) FILTER (WHERE r.rank = 'NK / EQUIVALENT') AS nk,\n"
						+ "            COUNT(*) FILTER (WHERE r.rank = 'SEP / EQUIVALENT') AS sep,\n"
						+ "            COUNT(*) FILTER (WHERE r.rank = 'RECTS') AS rect\n"
						+ "        FROM tb_psg_census_jco_or_p c\n"
						+ "        INNER JOIN tb_psg_mstr_rank_jco r ON c.rank = r.id AND c.status = '1'\n"
						+ "        LEFT JOIN tb_miso_orbat_arm_code e ON e.arm_code = c.arm_service\n"
						+ "        WHERE   c.unit_sus_no = ?    \n" + "        GROUP BY   1, 2\n"
						+ "    ) n2 ON n0.arm_code = n2.arm_code"

				;

				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);

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

	@Override
	public List<Map<String, Object>> Getcount_civHeld_data(String sus_no,String from_code_control,String formationtype) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if(formationtype.equals("Brigade")) {
				q = "SELECT DISTINCT count(*)  as civheldcount  \n" + "  FROM tb_psg_civilian_dtl_main a\n"
						+ " INNER JOIN t_domain_value t1\n" + "    ON t1.codevalue = a.civ_type\n"
						+ "   AND t1.domainid = 'CIVILIAN_TYPE'\n" + " INNER JOIN tb_psg_mstr_gender g\n"
						+ "    ON g.id = a.gender\n" + " INNER JOIN t_domain_value t\n"
						+ "    ON t.codevalue = a.classification_services\n"
						+ "   AND t.domainid = 'CLASSIFICATION_OF_SERVICES'\n"
						+ " INNER JOIN cue_tb_psg_rank_app_master b\n" + "    ON a.designation = b.id\n"
						+ "   AND upper(b.level_in_hierarchy) = upper('Appointment')\n"
						+ "   AND b.parent_code IN ('4','5','6','7','8','9','10','11')\n"
						+ "   AND b.status_active = 'Active'\n" + "   AND a.status = 1\n" + " WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
			}else {

				q = "SELECT DISTINCT count(*)  as civheldcount  \n" + "  FROM tb_psg_civilian_dtl a\n"
						+ " INNER JOIN t_domain_value t1\n" + "    ON t1.codevalue = a.civ_type\n"
						+ "   AND t1.domainid = 'CIVILIAN_TYPE'\n" + " INNER JOIN tb_psg_mstr_gender g\n"
						+ "    ON g.id = a.gender\n" + " INNER JOIN t_domain_value t\n"
						+ "    ON t.codevalue = a.classification_services\n"
						+ "   AND t.domainid = 'CLASSIFICATION_OF_SERVICES'\n"
						+ " INNER JOIN cue_tb_psg_rank_app_master b\n" + "    ON a.designation = b.id\n"
						+ "   AND upper(b.level_in_hierarchy) = upper('Appointment')\n"
						+ "   AND b.parent_code IN ('4','5','6','7','8','9','10','11')\n"
						+ "   AND b.status_active = 'Active'\n" + "   AND a.status = 1\n" + " WHERE a.sus_no = ? ";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);
			}

			//System.err.println("civilian count dashboard: \n"+stmt);
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
	public List<Map<String, Object>> Getcount_offAuth_data(String sus_no,String from_code_control,String formationtype) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(formationtype.equals("Brigade")) {
				q = "SELECT         COALESCE(SUM(COALESCE(auth.offr, '0')), 0) AS offrauth\n" + "        FROM (\n"
						+ "                                SELECT a.sus_no,\n"
						+ "                                                            a.cat_id,\n"
						+ "                                                            c.label AS per_category,\n"
						+ "                                                            a.arm,\n"
						+ "                                                            b.arm_desc,\n"
						+ "                                                            sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '0') AS offr\n"
						+ "                                        FROM sus_pers_stdauth a\n"
						+ "                                        LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "                                                ON b.arm_code = a.arm\n"
						+ "                                        LEFT JOIN t_domain_value c\n"
						+ "                                                ON c.codevalue = a.cat_id\n"
						+ "                                            AND c.domainid = 'PERSON_CAT'\n"
						+ "                                    WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n"
						+ "                                            AND a.cat_id = '2'\n"
						+ "                                    GROUP BY a.sus_no,\n"
						+ "                                                                        a.cat_id,\n"
						+ "                                                                        c.label,\n"
						+ "                                                                        a.arm,\n"
						+ "                                                                        b.arm_desc\n"
						+ "                    UNION ALL SELECT a.sus_no,\n"
						+ "                                                            a.cat_id,\n"
						+ "                                                            c.label AS per_category,\n"
						+ "                                                            a.arm,\n"
						+ "                                                            b.arm_desc,\n"
						+ "                                                            sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '0') AS offr\n"
						+ "                                        FROM sus_pers_stdauth a\n"
						+ "                                        LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "                                                ON b.arm_code = a.arm\n"
						+ "                                        LEFT JOIN t_domain_value c\n"
						+ "                                                ON c.codevalue = a.cat_id\n"
						+ "                                            AND c.domainid = 'PERSON_CAT'\n"
						+ "                                    WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ "                                            AND a.cat_id = '1'\n"
						+ "                                    GROUP BY a.sus_no,\n"
						+ "                                                                        a.cat_id,\n"
						+ "                                                                        c.label,\n"
						+ "                                                                        a.arm,\n"
						+ "                                                                        b.arm_desc\n"
						+ "                                    ORDER BY 1 ASC                         \n"
						+ "                        \n" + "                            ) auth";




				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
				stmt.setString(2, from_code_control);
			}else {
				q = "SELECT         COALESCE(SUM(COALESCE(auth.offr, '0')), 0) AS offrauth\n" + "        FROM (\n"
						+ "                                SELECT a.sus_no,\n"
						+ "                                                            a.cat_id,\n"
						+ "                                                            c.label AS per_category,\n"
						+ "                                                            a.arm,\n"
						+ "                                                            b.arm_desc,\n"
						+ "                                                            sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '0') AS offr\n"
						+ "                                        FROM sus_pers_stdauth a\n"
						+ "                                        LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "                                                ON b.arm_code = a.arm\n"
						+ "                                        LEFT JOIN t_domain_value c\n"
						+ "                                                ON c.codevalue = a.cat_id\n"
						+ "                                            AND c.domainid = 'PERSON_CAT'\n"
						+ "                                    WHERE a.sus_no = ? \n"
						+ "                                            AND a.cat_id = '2'\n"
						+ "                                    GROUP BY a.sus_no,\n"
						+ "                                                                        a.cat_id,\n"
						+ "                                                                        c.label,\n"
						+ "                                                                        a.arm,\n"
						+ "                                                                        b.arm_desc\n"
						+ "                    UNION ALL SELECT a.sus_no,\n"
						+ "                                                            a.cat_id,\n"
						+ "                                                            c.label AS per_category,\n"
						+ "                                                            a.arm,\n"
						+ "                                                            b.arm_desc,\n"
						+ "                                                            sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '0') AS offr\n"
						+ "                                        FROM sus_pers_stdauth a\n"
						+ "                                        LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "                                                ON b.arm_code = a.arm\n"
						+ "                                        LEFT JOIN t_domain_value c\n"
						+ "                                                ON c.codevalue = a.cat_id\n"
						+ "                                            AND c.domainid = 'PERSON_CAT'\n"
						+ "                                    WHERE a.sus_no =?\n"
						+ "                                            AND a.cat_id = '1'\n"
						+ "                                    GROUP BY a.sus_no,\n"
						+ "                                                                        a.cat_id,\n"
						+ "                                                                        c.label,\n"
						+ "                                                                        a.arm,\n"
						+ "                                                                        b.arm_desc\n"
						+ "                                    ORDER BY 1 ASC                         \n"
						+ "                        \n" + "                            ) auth";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);
				stmt.setString(2, sus_no);

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

	@Override
	public List<Map<String, Object>> Getcount_jcoAuth_data(String sus_no,String from_code_control,String formationtype) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(formationtype.equals("Brigade")) {
				q = "SELECT  COALESCE(SUM(COALESCE(auth.JCO, '0')), 0) AS jcoauth\n" + "  FROM (\n"
						+ "        SELECT a.sus_no,\n" + "               a.cat_id,\n"
						+ "               c.label AS per_category,\n" + "               a.arm,\n"
						+ "               b.arm_desc,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '1') AS JCO\n"
						+ "          FROM sus_pers_stdauth a\n" + "          LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "            ON b.arm_code = a.arm\n" + "          LEFT JOIN t_domain_value c\n"
						+ "            ON c.codevalue = a.cat_id\n" + "           AND c.domainid = 'PERSON_CAT'\n"
						+ "         WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n" + "           AND a.cat_id = '2'\n"
						+ "         GROUP BY a.sus_no,\n" + "                  a.cat_id,\n" + "                  c.label,\n"
						+ "                  a.arm,\n" + "                  b.arm_desc\n"
						+ "     UNION ALL SELECT a.sus_no,\n" + "               a.cat_id,\n"
						+ "               c.label AS per_category,\n" + "               a.arm,\n"
						+ "               b.arm_desc,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '1') AS JCO\n"
						+ "          FROM sus_pers_stdauth a\n" + "          LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "            ON b.arm_code = a.arm\n" + "          LEFT JOIN t_domain_value c\n"
						+ "            ON c.codevalue = a.cat_id\n" + "           AND c.domainid = 'PERSON_CAT'\n"
						+ "         WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "           AND a.cat_id = '1'\n"
						+ "         GROUP BY a.sus_no,\n" + "                  a.cat_id,\n" + "                  c.label,\n"
						+ "                  a.arm,\n" + "                  b.arm_desc\n"
						+ "         ORDER BY 1 ASC       \n" + "      \n" + "       ) auth ";


				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
				stmt.setString(2, from_code_control);
			}else {

				q = "SELECT  COALESCE(SUM(COALESCE(auth.JCO, '0')), 0) AS jcoauth\n" + "  FROM (\n"
						+ "        SELECT a.sus_no,\n" + "               a.cat_id,\n"
						+ "               c.label AS per_category,\n" + "               a.arm,\n"
						+ "               b.arm_desc,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '1') AS JCO\n"
						+ "          FROM sus_pers_stdauth a\n" + "          LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "            ON b.arm_code = a.arm\n" + "          LEFT JOIN t_domain_value c\n"
						+ "            ON c.codevalue = a.cat_id\n" + "           AND c.domainid = 'PERSON_CAT'\n"
						+ "         WHERE a.sus_no = ? \n" + "           AND a.cat_id = '2'\n"
						+ "         GROUP BY a.sus_no,\n" + "                  a.cat_id,\n" + "                  c.label,\n"
						+ "                  a.arm,\n" + "                  b.arm_desc\n"
						+ "     UNION ALL SELECT a.sus_no,\n" + "               a.cat_id,\n"
						+ "               c.label AS per_category,\n" + "               a.arm,\n"
						+ "               b.arm_desc,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '1') AS JCO\n"
						+ "          FROM sus_pers_stdauth a\n" + "          LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "            ON b.arm_code = a.arm\n" + "          LEFT JOIN t_domain_value c\n"
						+ "            ON c.codevalue = a.cat_id\n" + "           AND c.domainid = 'PERSON_CAT'\n"
						+ "         WHERE a.sus_no =?\n" + "           AND a.cat_id = '1'\n"
						+ "         GROUP BY a.sus_no,\n" + "                  a.cat_id,\n" + "                  c.label,\n"
						+ "                  a.arm,\n" + "                  b.arm_desc\n"
						+ "         ORDER BY 1 ASC       \n" + "      \n" + "       ) auth ";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);
				stmt.setString(2, sus_no);
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

	@Override
	public List<Map<String, Object>> Getcount_civAuth_data(String sus_no,String from_code_control,String formationtype) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(formationtype.equals("Brigade")) {
				q = "SELECT sum(COALESCE (auth.gp_a_gaz,'0') + COALESCE (auth.gp_b_gaz,'0') + COALESCE (auth.gp_b_non_gaz,'0') + COALESCE (auth.gp_c_non_gaz,'0')) AS civiliancount\n"
						+ "  FROM (\n"
						+ "        SELECT sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '4') AS gp_a_gaz,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '5') AS gp_b_gaz,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('6','9')) AS gp_b_non_gaz,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('7','8','10','11','12')) AS gp_c_non_gaz\n"
						+ "          FROM sus_pers_stdauth a\n" + "          LEFT JOIN t_domain_value c\n"
						+ "            ON c.codevalue = a.cat_id\n" + "           AND c.domainid = 'PERSON_CAT'\n"
						+ "         WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n" + "           AND a.cat_id IN ('1','2')\n" + "       ) auth ";




				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
			}else {

				q = "SELECT sum(COALESCE (auth.gp_a_gaz,'0') + COALESCE (auth.gp_b_gaz,'0') + COALESCE (auth.gp_b_non_gaz,'0') + COALESCE (auth.gp_c_non_gaz,'0')) AS civiliancount\n"
						+ "  FROM (\n"
						+ "        SELECT sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '4') AS gp_a_gaz,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '5') AS gp_b_gaz,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('6','9')) AS gp_b_non_gaz,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('7','8','10','11','12')) AS gp_c_non_gaz\n"
						+ "          FROM sus_pers_stdauth a\n" + "          LEFT JOIN t_domain_value c\n"
						+ "            ON c.codevalue = a.cat_id\n" + "           AND c.domainid = 'PERSON_CAT'\n"
						+ "         WHERE a.sus_no = ? \n" + "           AND a.cat_id IN ('1','2')\n" + "       ) auth ";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);
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

	@Override
	public List<Map<String, Object>> Getcount_orAuth_data(String sus_no,String from_code_control,String formationtype) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(formationtype.equals("Brigade")) {
				q = "SELECT COALESCE(SUM(COALESCE(auth.ors, '0')), 0) AS ors \n" + "  FROM (\n"
						+ "        SELECT a.sus_no,\n" + "               a.cat_id,\n"
						+ "               c.label AS per_category,\n" + "               a.arm,\n"
						+ "               b.arm_desc,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('2','3')) AS ors\n"
						+ "          FROM sus_pers_stdauth a\n" + "          LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "            ON b.arm_code = a.arm\n" + "          LEFT JOIN t_domain_value c\n"
						+ "            ON c.codevalue = a.cat_id\n" + "           AND c.domainid = 'PERSON_CAT'\n"
						+ "         WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n" + "           AND a.cat_id = '2'\n"
						+ "         GROUP BY a.sus_no,\n" + "                  a.cat_id,\n" + "                  c.label,\n"
						+ "                  a.arm,\n" + "                  b.arm_desc\n"
						+ "     UNION ALL SELECT a.sus_no,\n" + "               a.cat_id,\n"
						+ "               c.label AS per_category,\n" + "               a.arm,\n"
						+ "               b.arm_desc,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('2','3')) AS ors\n"
						+ "          FROM sus_pers_stdauth a\n" + "          LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "            ON b.arm_code = a.arm\n" + "          LEFT JOIN t_domain_value c\n"
						+ "            ON c.codevalue = a.cat_id\n" + "           AND c.domainid = 'PERSON_CAT'\n"
						+ "         WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "           AND a.cat_id = '1'\n"
						+ "         GROUP BY a.sus_no,\n" + "                  a.cat_id,\n" + "                  c.label,\n"
						+ "                  a.arm,\n" + "                  b.arm_desc\n" + "       ) auth";


				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
				stmt.setString(2, from_code_control);
			}else {

				q = "SELECT COALESCE(SUM(COALESCE(auth.ors, '0')), 0) AS ors \n" + "  FROM (\n"
						+ "        SELECT a.sus_no,\n" + "               a.cat_id,\n"
						+ "               c.label AS per_category,\n" + "               a.arm,\n"
						+ "               b.arm_desc,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('2','3')) AS ors\n"
						+ "          FROM sus_pers_stdauth a\n" + "          LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "            ON b.arm_code = a.arm\n" + "          LEFT JOIN t_domain_value c\n"
						+ "            ON c.codevalue = a.cat_id\n" + "           AND c.domainid = 'PERSON_CAT'\n"
						+ "         WHERE a.sus_no = ? \n" + "           AND a.cat_id = '2'\n"
						+ "         GROUP BY a.sus_no,\n" + "                  a.cat_id,\n" + "                  c.label,\n"
						+ "                  a.arm,\n" + "                  b.arm_desc\n"
						+ "     UNION ALL SELECT a.sus_no,\n" + "               a.cat_id,\n"
						+ "               c.label AS per_category,\n" + "               a.arm,\n"
						+ "               b.arm_desc,\n"
						+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('2','3')) AS ors\n"
						+ "          FROM sus_pers_stdauth a\n" + "          LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "            ON b.arm_code = a.arm\n" + "          LEFT JOIN t_domain_value c\n"
						+ "            ON c.codevalue = a.cat_id\n" + "           AND c.domainid = 'PERSON_CAT'\n"
						+ "         WHERE a.sus_no = ?\n" + "           AND a.cat_id = '1'\n"
						+ "         GROUP BY a.sus_no,\n" + "                  a.cat_id,\n" + "                  c.label,\n"
						+ "                  a.arm,\n" + "                  b.arm_desc\n" + "       ) auth";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);
				stmt.setString(2, sus_no);
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

	/////////////////////////////////////////////

	// OFFRS

	public String GenerateQueryWhereClause_SQLoffr(String search) {

		String SearchValue = "";
		if (!search.equals("")) { // for Input Filter
			SearchValue = " and   ";

			SearchValue += "(upper(appmst.description) like ?   or upper(c.personnel_no) like ? or upper(r.description) like ? or upper( c.name) like ?"
					+ " or   upper(ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0')) like ? "
					+ "or   upper(ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0')) like ?"
					+ " or upper(ltrim(to_char(c.date_of_commission,'DD-Mon-YYYY'),'0')) like ? "
					+ "or upper(ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0'))   like ?"
					//	                    + "or upper(med_cat) like ?"
					+ " or   upper(ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') )like ? or upper(course.army_courses) like ?"
					+ " or upper(degree.civil_qualification) like ? or upper(ma.maiden_name) like ? )";
		}
		return SearchValue;
	}


	public PreparedStatement setQueryWhereClause_SQLoffr(PreparedStatement stmt, String search) {
		int flag = 4;
		try {
			if (!search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
			}
		} catch (Exception e) {
		}

		return stmt;

	}


	@Override
	public List<Map<String, Object>> getUnitReportPersOffrs(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String roleSusNo, String Access_by_susno) {
		String SearchValue = GenerateQueryWhereClause_SQLoffr(search);
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
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

			if(formationtype.equals("Brigade") && Access_by_susno != "Access") {

				String from_code_control= formation_code(roleSusNo);


				q = "SELECT DISTINCT appmst.description,\n" + "       c.personnel_no AS ic_no,\n"
						+ "       c.name AS name,\n" + "       ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,\n"
						+ "       ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,\n"
						+ "       ltrim(to_char(c.date_of_commission,'DD-Mon-YYYY'),'0') AS doc,\n"
						+ "       ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') AS dom,\n"
						+ "       course.army_courses,\n" + "       ma.maiden_name AS spouse_name,\n"
						+ "       degree.civil_qualification,\n" + "       rwd.rank AS rank,\n" + "       rwd.rank_code,\n"
						+ "       med.med_cat,\n" + "       med.cope   , 	unt.unit_name \n" + "  FROM tb_psg_trans_proposed_comm_letter c\n"
						+ " INNER JOIN cue_tb_psg_rank_app_master r\n" + "    ON c.rank = r.id\n"
						+ "   AND c.status IN ('1', '5')\n" + "   AND upper(r.level_in_hierarchy) = 'RANK'\n"
						+ " INNER JOIN tb_psg_iaff_3008_rank_wise_data rwd\n" + "    ON r.code = rwd.rank_code\n"
						+ "  Inner join tb_miso_orbat_unt_dtl unt\r\n"
						+ "   on unt.sus_no = c.unit_sus_no\r\n"
						+ "	 and unt.status_sus_no ='Active' "
						+ "  LEFT JOIN tb_psg_change_of_appointment apoint\n" + "    ON apoint.comm_id = c.id\n"
						+ "   AND apoint.status = 1\n" + "  LEFT JOIN cue_tb_psg_rank_app_master appmst\n"
						+ "    ON appmst.id = apoint.appointment\n" + "  LEFT JOIN tb_psg_census_family_married ma\n"
						+ "    ON ma.comm_id = c.id\n" + "   AND ma.status = 1\n" + "  LEFT JOIN tb_miso_orbat_arm_code e\n"
						+ "    ON e.arm_code = c.parent_arm\n" + "  LEFT JOIN (\n"
						+ "        SELECT string_agg(DISTINCT deg.degree, ', ') AS civil_qualification,\n"
						+ "               qua.comm_id\n" + "          FROM tb_psg_trans_proposed_comm_letter c\n"
						+ "         INNER JOIN tb_psg_census_qualification qua\n" + "            ON qua.comm_id = c.id\n"
						+ "           AND qua.status = 1\n" + "         INNER JOIN tb_psg_mstr_degree deg\n"
						+ "            ON deg.id = qua.degree\n" + "           AND deg.status = 'active'\n"
						+ "         WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "         GROUP BY qua.comm_id\n"
						+ "       ) degree\n" + "    ON degree.comm_id = c.id\n" + "  LEFT JOIN (\n"
						+ "        SELECT DISTINCT 'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\n"
						+ "               'C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || ';O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') AS cope,\n"
						+ "               med_main.comm_id\n" + "          FROM tb_psg_medical_category med_main\n"
						+ "         INNER JOIN tb_psg_trans_proposed_comm_letter c\n"
						+ "            ON c.id = med_main.comm_id\n" + "         WHERE med_main.status = '1'\n"
						+ "           AND c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "         GROUP BY med_main.comm_id\n"
						+ "       ) med\n" + "    ON c.id = med.comm_id\n" + "  LEFT JOIN (\n"
						+ "        SELECT string_agg(DISTINCT cour.course_name, ', ') AS army_courses,\n"
						+ "               cour.comm_id\n" + "          FROM tb_psg_census_army_course cour\n"
						+ "         INNER JOIN tb_psg_trans_proposed_comm_letter c\n"
						+ "            ON c.id = cour.comm_id\n" + "         WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ "         GROUP BY cour.comm_id\n" + "       ) course\n" + "    ON course.comm_id = c.id\n"
						+ "  LEFT JOIN (\n" + "        SELECT max(dt_of_tos) AS dt_of_tos,\n"
						+ "               comm_id AS comm_id\n" + "          FROM tb_psg_posting_in_out\n"
						+ "         GROUP BY comm_id\n" + "       ) tos\n" + "    ON tos.comm_id = c.id "
						+ " WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')  AND LEFT(c.personnel_no, 2) NOT IN ('NR', 'NS') " + SearchValue + "  \n"
						+ "   order by rwd.rank_code asc" + " limit " + pageL
						+ " OFFSET " + startPage + "";


				stmt = conn.prepareStatement(q);
			System.err.println("---------------"+ stmt);
				stmt.setString(1, from_code_control);
				stmt.setString(2, from_code_control);
				stmt.setString(3, from_code_control);
				stmt.setString(4, from_code_control);
			}else{
				q = "SELECT DISTINCT appmst.description,\n" + "       c.personnel_no AS ic_no,\n"
						+ "       c.name AS name,\n" + "       ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,\n"
						+ "       ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,\n"
						+ "       ltrim(to_char(c.date_of_commission,'DD-Mon-YYYY'),'0') AS doc,\n"
						+ "       ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') AS dom,\n"
						+ "       course.army_courses,\n" + "       ma.maiden_name AS spouse_name,\n"
						+ "       degree.civil_qualification,\n" + "       rwd.rank AS rank,\n" + "       rwd.rank_code,\n"
						+ "       med.med_cat,\n" + "       med.cope , 	unt.unit_name\n" + "  FROM tb_psg_trans_proposed_comm_letter c\n"
						+ " INNER JOIN cue_tb_psg_rank_app_master r\n" + "    ON c.rank = r.id\n"
						+ "   AND c.status IN ('1', '5')\n" + "   AND upper(r.level_in_hierarchy) = 'RANK'\n"
						+ " INNER JOIN tb_psg_iaff_3008_rank_wise_data rwd\n" + "    ON r.code = rwd.rank_code \n"
						+ " Inner join tb_miso_orbat_unt_dtl unt\r\n"
						+ "   on unt.sus_no = c.unit_sus_no\r\n"
						+ "	 and unt.status_sus_no ='Active' \r\n"
						+ "  LEFT JOIN tb_psg_change_of_appointment apoint\n" + "    ON apoint.comm_id = c.id\n"
						+ "   AND apoint.status = 1\n" + "  LEFT JOIN cue_tb_psg_rank_app_master appmst\n"
						+ "    ON appmst.id = apoint.appointment\n" + "  LEFT JOIN tb_psg_census_family_married ma\n"
						+ "    ON ma.comm_id = c.id\n" + "   AND ma.status = 1\n" + "  LEFT JOIN tb_miso_orbat_arm_code e\n"
						+ "    ON e.arm_code = c.parent_arm\n" + "  LEFT JOIN (\n"
						+ "        SELECT string_agg(DISTINCT deg.degree, ', ') AS civil_qualification,\n"
						+ "               qua.comm_id\n" + "          FROM tb_psg_trans_proposed_comm_letter c\n"
						+ "         INNER JOIN tb_psg_census_qualification qua\n" + "            ON qua.comm_id = c.id\n"
						+ "           AND qua.status = 1\n" + "         INNER JOIN tb_psg_mstr_degree deg\n"
						+ "            ON deg.id = qua.degree\n" + "           AND deg.status = 'active'\n"
						+ "         WHERE c.unit_sus_no IN (?)\n" + "         GROUP BY qua.comm_id\n"
						+ "       ) degree\n" + "    ON degree.comm_id = c.id\n" + "  LEFT JOIN (\n"
						+ "        SELECT DISTINCT 'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\n"
						+ "               'C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || ';O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') AS cope,\n"
						+ "               med_main.comm_id\n" + "          FROM tb_psg_medical_category med_main\n"
						+ "         INNER JOIN tb_psg_trans_proposed_comm_letter c\n"
						+ "            ON c.id = med_main.comm_id\n" + "         WHERE med_main.status = '1'\n"
						+ "           AND c.unit_sus_no IN (?)\n" + "         GROUP BY med_main.comm_id\n"
						+ "       ) med\n" + "    ON c.id = med.comm_id\n" + "  LEFT JOIN (\n"
						+ "        SELECT string_agg(DISTINCT cour.course_name, ', ') AS army_courses,\n"
						+ "               cour.comm_id\n" + "          FROM tb_psg_census_army_course cour\n"
						+ "         INNER JOIN tb_psg_trans_proposed_comm_letter c\n"
						+ "            ON c.id = cour.comm_id\n" + "         WHERE c.unit_sus_no IN (?)\n"
						+ "         GROUP BY cour.comm_id\n" + "       ) course\n" + "    ON course.comm_id = c.id\n"
						+ "  LEFT JOIN (\n" + "        SELECT max(dt_of_tos) AS dt_of_tos,\n"
						+ "               comm_id AS comm_id\n" + "          FROM tb_psg_posting_in_out\n"
						+ "         GROUP BY comm_id\n" + "       ) tos\n" + "    ON tos.comm_id = c.id "
						+ " WHERE c.unit_sus_no in (?)     AND LEFT(c.personnel_no, 2) NOT IN ('NR', 'NS') " + SearchValue + "  \n"
						+ "   order by rwd.rank_code asc" + " limit " + pageL
						+ " OFFSET " + startPage + "";

				stmt = conn.prepareStatement(q);
				System.err.println("---------------"+ stmt);
				stmt.setString(1, roleSusNo);
				stmt.setString(2, roleSusNo);
				stmt.setString(3, roleSusNo);
				stmt.setString(4, roleSusNo);
			}



			//			stmt = setQueryWhereClause_SQLPERS(stmt,search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
			stmt = setQueryWhereClause_SQLoffr(stmt, search);

			//System.err.println("dash off held details: \n"+stmt);
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
	public long getUnitReportPersOffrscount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo, String Access_by_susno) {
		//		String SearchValue = GenerateQueryWhereClause_SQLPERS(Search,  appt_trade, rank, rank_cat, cont_comd, cont_corps,  cont_div,  cont_bde, radio1);
		String SearchValue = GenerateQueryWhereClause_SQLoffr(Search);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt;
			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			
			if(formationtype.equals("Brigade") && Access_by_susno != "Access") {
				String from_code_control= formation_code(roleSusNo);
				q = "select count(app.*) from (SELECT DISTINCT appmst.description,\n"
						+ "       c.personnel_no AS ic_no,\n"
						+ "       c.name AS name,\n"
						+ "       ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,\n"
						+ "       ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,\n"
						+ "       ltrim(to_char(c.date_of_commission,'DD-Mon-YYYY'),'0') AS doc,\n"
						+ "       ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') AS dom,\n"
						+ "       course.army_courses,\n"
						+ "       ma.maiden_name AS spouse_name,\n"
						+ "       degree.civil_qualification,\n"
						+ "       rwd.rank AS rank,\n"
						+ "       rwd.rank_code,\n"
						+ "       med.med_cat,\n"
						+ "       med.cope\n"
						+ "  FROM tb_psg_trans_proposed_comm_letter c\n"
						+ " INNER JOIN cue_tb_psg_rank_app_master r\n"
						+ "    ON c.rank = r.id\n"
						+ "   AND c.status IN ('1','5')\n"
						+ "   AND upper(r.level_in_hierarchy) = 'RANK'\n"
						+ " INNER JOIN tb_psg_iaff_3008_rank_wise_data rwd\n"
						+ "    ON r.code = rwd.rank_code\n"
						+ " Inner join tb_miso_orbat_unt_dtl unt\r\n"
						+ "   on unt.sus_no = c.unit_sus_no\r\n"
						+ "	 and unt.status_sus_no ='Active' \r\n"
						+ "  LEFT JOIN tb_psg_change_of_appointment apoint\n"
						+ "    ON apoint.comm_id = c.id\n"
						+ "   AND apoint.status = 1\n"
						+ "  LEFT JOIN cue_tb_psg_rank_app_master appmst\n"
						+ "    ON appmst.id = apoint.appointment\n"
						+ "  LEFT JOIN tb_psg_census_family_married ma\n"
						+ "    ON ma.comm_id = c.id\n"
						+ "   AND ma.status = 1\n"
						+ "  LEFT JOIN tb_miso_orbat_arm_code e\n"
						+ "    ON e.arm_code = c.parent_arm\n"
						+ "  LEFT JOIN (\n"
						+ "        SELECT string_agg(DISTINCT deg.degree, ', ') AS civil_qualification,\n"
						+ "               qua.comm_id\n"
						+ "          FROM tb_psg_trans_proposed_comm_letter c\n"
						+ "         INNER JOIN tb_psg_census_qualification qua\n"
						+ "            ON qua.comm_id = c.id\n"
						+ "           AND qua.status = 1\n"
						+ "         INNER JOIN tb_psg_mstr_degree deg\n"
						+ "            ON deg.id = qua.degree\n"
						+ "           AND deg.status = 'active'\n"
						+ "         WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n"
						+ "         GROUP BY qua.comm_id\n"
						+ "       ) degree\n"
						+ "    ON degree.comm_id = c.id\n"
						+ "  LEFT JOIN (\n"
						+ "        SELECT DISTINCT 'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\n"
						+ "               'C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || ';O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') AS cope,\n"
						+ "               med_main.comm_id\n"
						+ "          FROM tb_psg_medical_category med_main\n"
						+ "         INNER JOIN tb_psg_trans_proposed_comm_letter c\n"
						+ "            ON c.id = med_main.comm_id\n"
						+ "         WHERE med_main.status = '1'\n"
						+ "           AND c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n"
						+ "         GROUP BY med_main.comm_id\n"
						+ "       ) med\n"
						+ "    ON c.id = med.comm_id\n"
						+ "  LEFT JOIN (\n"
						+ "        SELECT string_agg(DISTINCT cour.course_name, ', ') AS army_courses,\n"
						+ "               cour.comm_id\n"
						+ "          FROM tb_psg_census_army_course cour\n"
						+ "         INNER JOIN tb_psg_trans_proposed_comm_letter c\n"
						+ "            ON c.id = cour.comm_id\n"
						+ "         WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n"
						+ "         GROUP BY cour.comm_id\n"
						+ "       ) course\n"
						+ "    ON course.comm_id = c.id\n"
						+ "  LEFT JOIN (\n"
						+ "        SELECT max(dt_of_tos) AS dt_of_tos,\n"
						+ "               comm_id AS comm_id\n"
						+ "          FROM tb_psg_posting_in_out\n"
						+ "         GROUP BY comm_id\n"
						+ "       ) tos\n"
						+ "    ON tos.comm_id = c.id  WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')  AND LEFT(c.personnel_no, 2) NOT IN ('NR', 'NS')\r\n"
						+ "  "+SearchValue+" ) app";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
				stmt.setString(2, from_code_control);
				stmt.setString(3, from_code_control);
				stmt.setString(4, from_code_control);


			}else{

				q = "select count(app.*) from (SELECT DISTINCT appmst.description,\n"
						+ "       c.personnel_no AS ic_no,\n"
						+ "       c.name AS name,\n"
						+ "       ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,\n"
						+ "       ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,\n"
						+ "       ltrim(to_char(c.date_of_commission,'DD-Mon-YYYY'),'0') AS doc,\n"
						+ "       ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') AS dom,\n"
						+ "       course.army_courses,\n"
						+ "       ma.maiden_name AS spouse_name,\n"
						+ "       degree.civil_qualification,\n"
						+ "       rwd.rank AS rank,\n"
						+ "       rwd.rank_code,\n"
						+ "       med.med_cat,\n"
						+ "       med.cope\n"
						+ "  FROM tb_psg_trans_proposed_comm_letter c\n"
						+ " INNER JOIN cue_tb_psg_rank_app_master r\n"
						+ "    ON c.rank = r.id\n"
						+ "   AND c.status IN ('1','5')\n"
						+ "   AND upper(r.level_in_hierarchy) = 'RANK'\n"
						+ " INNER JOIN tb_psg_iaff_3008_rank_wise_data rwd\n"
						+ "    ON r.code = rwd.rank_code\n"
						+ "  LEFT JOIN tb_psg_change_of_appointment apoint\n"
						+ "    ON apoint.comm_id = c.id\n"
						+ "   AND apoint.status = 1\n"
						+ "  LEFT JOIN cue_tb_psg_rank_app_master appmst\n"
						+ "    ON appmst.id = apoint.appointment\n"
						+ "  LEFT JOIN tb_psg_census_family_married ma\n"
						+ "    ON ma.comm_id = c.id\n"
						+ "   AND ma.status = 1\n"
						+ "  LEFT JOIN tb_miso_orbat_arm_code e\n"
						+ "    ON e.arm_code = c.parent_arm\n"
						+ "  LEFT JOIN (\n"
						+ "        SELECT string_agg(DISTINCT deg.degree, ', ') AS civil_qualification,\n"
						+ "               qua.comm_id\n"
						+ "          FROM tb_psg_trans_proposed_comm_letter c\n"
						+ "         INNER JOIN tb_psg_census_qualification qua\n"
						+ "            ON qua.comm_id = c.id\n"
						+ "           AND qua.status = 1\n"
						+ "         INNER JOIN tb_psg_mstr_degree deg\n"
						+ "            ON deg.id = qua.degree\n"
						+ "           AND deg.status = 'active'\n"
						+ "         WHERE c.unit_sus_no IN (?)\n"
						+ "         GROUP BY qua.comm_id\n"
						+ "       ) degree\n"
						+ "    ON degree.comm_id = c.id\n"
						+ "  LEFT JOIN (\n"
						+ "        SELECT DISTINCT 'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\n"
						+ "               'C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || ';O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') AS cope,\n"
						+ "               med_main.comm_id\n"
						+ "          FROM tb_psg_medical_category med_main\n"
						+ "         INNER JOIN tb_psg_trans_proposed_comm_letter c\n"
						+ "            ON c.id = med_main.comm_id\n"
						+ "         WHERE med_main.status = '1'\n"
						+ "           AND c.unit_sus_no IN (?)\n"
						+ "         GROUP BY med_main.comm_id\n"
						+ "       ) med\n"
						+ "    ON c.id = med.comm_id\n"
						+ "  LEFT JOIN (\n"
						+ "        SELECT string_agg(DISTINCT cour.course_name, ', ') AS army_courses,\n"
						+ "               cour.comm_id\n"
						+ "          FROM tb_psg_census_army_course cour\n"
						+ "         INNER JOIN tb_psg_trans_proposed_comm_letter c\n"
						+ "            ON c.id = cour.comm_id\n"
						+ "         WHERE c.unit_sus_no IN (?)\n"
						+ "         GROUP BY cour.comm_id\n"
						+ "       ) course\n"
						+ "    ON course.comm_id = c.id\n"
						+ "  LEFT JOIN (\n"
						+ "        SELECT max(dt_of_tos) AS dt_of_tos,\n"
						+ "               comm_id AS comm_id\n"
						+ "          FROM tb_psg_posting_in_out\n"
						+ "         GROUP BY comm_id\n"
						+ "       ) tos\n"
						+ "    ON tos.comm_id = c.id  WHERE c.unit_sus_no in (?) AND LEFT(c.personnel_no, 2) NOT IN ('NR', 'NS')\r\n"
						+ "  "+SearchValue+" ) app";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
				stmt.setString(2, roleSusNo);
				stmt.setString(3, roleSusNo);
				stmt.setString(4, roleSusNo);

			}



			stmt = setQueryWhereClause_SQLoffr(stmt, Search);
			System.out.println("-------"+stmt);
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



	// JCoOR
	@Override
	public List<Map<String, Object>> getUnitReportPersJCo(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String roleSusNo, String name, String rank,
			String armyNo, String trade, String Access_by_susno) {
		//	 String SearchValue = GenerateQueryWhereClause_SQLPERS(search,  appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
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
			String SearchValue = "";
			String qry = "";
			if (name != "" && name != null) {
				qry += " and  upper(c.full_name) like ?";
			}
			if (rank != "" && rank != null) {
				qry += " and c.rank = ?";
			}

			if (trade != "" && trade != null) {
				qry += " and t.trade = ?";
			}
			if (armyNo != "" && armyNo != null) {
				qry += " and c.army_no = ?";
			}

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "(upper(c.army_no) like ?   " + "or upper(r.rank) like ? " + "or upper(t.trade) like ? "
						+ "or upper( c.full_name) like ?"
						+ " or   upper(ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0')) like ? "
						+ "or   upper(ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0')) like ?"
						+ " or upper(ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0')) like ? "
						+ "or upper(ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0'))   like ?"
						+ " or   upper(ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') )like ? "
						+ "or upper(cour.course_name) like ?" + " or upper(ma.maiden_name) like ? or upper(orb.unit_name) like ?)";
			}


			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade") && Access_by_susno != "Access") {
				String from_code_control= formation_code(roleSusNo);
				q = "  SELECT  c.army_no,\r\n"
						+ "       r.rank AS rk,\r\n"
						+ "       c.full_name AS name,\r\n"
						+ "       t.trade AS trade,\r\n"
						+ "       ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,\r\n"
						+ "       ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,\r\n"
						+ "       ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0') AS doe,\r\n"
						+ "       ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\r\n"
						+ "       'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\r\n"
						+ "         'C_E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') || 'C_O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || 'C_C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || 'C_P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') AS cope,\r\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY')) AS dom,\r\n"
						+ "       string_agg(distinct cour.course_name, ', ') army_courses,\r\n"
						+ "       ma.maiden_name AS spousename,\r\n"
						+ "	   orb.unit_name as unit_name\r\n"
						+ "  FROM tb_psg_census_jco_or_p c\r\n"
						+ " INNER JOIN tb_psg_mstr_rank_jco r\r\n"
						+ "    ON c.rank = r.id\r\n"
						+ "   AND c.status = '1'\r\n"
						+ " INNER JOIN tb_psg_mstr_trade_jco t\r\n"
						+ "    ON t.id = c.trade\r\n"
						+ "INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "	ON c.unit_sus_no = orb.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "  LEFT JOIN tb_miso_orbat_arm_code e\r\n"
						+ "    ON e.arm_code = c.arm_service\r\n"
						+ "  LEFT JOIN tb_psg_medical_category_jco med_main\r\n"
						+ "    ON med_main.jco_id = c.id\r\n"
						+ "   AND med_main.status IN ('1','2')\r\n"
						+ "  LEFT JOIN tb_psg_census_army_course_jco cour\r\n"
						+ "    ON cour.jco_id = c.id\r\n"
						+ "  LEFT JOIN tb_psg_census_family_married_jco ma\r\n"
						+ "    ON ma.jco_id = c.id\r\n"
						+ " WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\r\n"
						+ "   AND  c.category='JCO'  "
						+ qry + " \n" + SearchValue + "  GROUP BY 1,2, 3, 4,5,6,7,8,\n" + "          ma.marriage_date,\n"
						+ "          ma.maiden_name, orb.unit_name" + "  limit " + pageL + " OFFSET " + startPage + "";



				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
			}else{


				q = "  SELECT  c.army_no,\r\n"
						+ "       r.rank AS rk,\r\n"
						+ "       c.full_name AS name,\r\n"
						+ "       t.trade AS trade,\r\n"
						+ "       ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,\r\n"
						+ "       ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,\r\n"
						+ "       ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0') AS doe,\r\n"
						+ "       ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\r\n"
						+ "       'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\r\n"
						+ "         'C_E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') || 'C_O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || 'C_C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || 'C_P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') AS cope,\r\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY')) AS dom,\r\n"
						+ "       string_agg(distinct cour.course_name, ', ') army_courses,\r\n"
						+ "       ma.maiden_name AS spousename,\r\n"
						+ "	   orb.unit_name as unit_name\r\n"
						+ "  FROM tb_psg_census_jco_or_p c\r\n"
						+ " INNER JOIN tb_psg_mstr_rank_jco r\r\n"
						+ "    ON c.rank = r.id\r\n"
						+ "   AND c.status = '1'\r\n"
						+ " INNER JOIN tb_psg_mstr_trade_jco t\r\n"
						+ "    ON t.id = c.trade\r\n"
						+ "INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "	ON c.unit_sus_no = orb.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "  LEFT JOIN tb_miso_orbat_arm_code e\r\n"
						+ "    ON e.arm_code = c.arm_service\r\n"
						+ "  LEFT JOIN tb_psg_medical_category_jco med_main\r\n"
						+ "    ON med_main.jco_id = c.id\r\n"
						+ "   AND med_main.status IN ('1','2')\r\n"
						+ "  LEFT JOIN tb_psg_census_army_course_jco cour\r\n"
						+ "    ON cour.jco_id = c.id\r\n"
						+ "  LEFT JOIN tb_psg_census_family_married_jco ma\r\n"
						+ "    ON ma.jco_id = c.id\r\n"
						+ " WHERE c.unit_sus_no = ?\r\n"
						+ "   AND  c.category='JCO'  "
						+ qry + " \n" + SearchValue + "  GROUP BY 1,2, 3, 4,5,6,7,8,\n" + "          ma.marriage_date,\n"
						+ "          ma.maiden_name,orb.unit_name " + "  limit " + pageL + " OFFSET " + startPage + "";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
			}

			int j = 2;
			if (name != "" && name != null) {
				stmt.setString(j,"%" + name.toUpperCase() + "%");
				j += 1;
			}
			if (rank != "" && rank != null) {
				stmt.setInt(j,Integer.parseInt(rank));
				j += 1;
			}
			if (trade != "" && trade != null) {
				stmt.setString(j, trade);
				j += 1;
			}
			if (armyNo != "" && armyNo != null) {
				stmt.setString(j, armyNo);
			}

			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag,"%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" +Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  Search.toUpperCase() + "%");
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
				stmt.setString(flag,"%" +  Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  Search.toUpperCase() + "%");
			}
			ResultSet rs = stmt.executeQuery();
			System.out.println("jco_held_list-------------->  " +stmt);

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
	public long getUnitReportPersJCocount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo, String name, String rank, String armyNo, String trade, String Access_by_susno) {
		//		String SearchValue = GenerateQueryWhereClause_SQLPERS(Search,  appt_trade, rank, rank_cat, cont_comd, cont_corps,  cont_div,  cont_bde, radio1);

		int total = 0;
		String q = null;
		Connection conn = null;
		PreparedStatement stmt =null;
		try {
			conn = dataSource.getConnection();
			String SearchValue = "";

			String qry = "";
			if (name != "" && name != null) {
				qry += " and  upper(c.full_name) like ?";
			}
			if (rank != "" && rank != null) {
				qry += " and c.rank = ?";
			}

			if (trade != "" && trade != null) {
				qry += " and t.trade = ?";
			}
			if (armyNo != "" && armyNo != null) {
				qry += " and c.army_no = ?";
			}
			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "(upper(c.army_no) like ?   " + "or upper(r.rank) like ? " + "or upper(t.trade) like ? "
						+ "or upper( c.full_name) like ?"
						+ " or   upper(ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0')) like ? "
						+ "or   upper(ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0')) like ?"
						+ " or upper(ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0')) like ? "
						+ "or upper(ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0'))   like ?"
						+ " or   upper(ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') )like ? "
						+ "or upper(cour.course_name) like ?" + " or upper(ma.maiden_name) like ? or upper(orb.unit_name) like ? )";
			}


			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade") && Access_by_susno != "Access") {
				String from_code_control= formation_code(roleSusNo);
				q = "select count (app.*) from(SELECT  c.army_no,\r\n"
						+ "       r.rank AS rk,\r\n"
						+ "       c.full_name AS name,\r\n"
						+ "       t.trade AS trade,\r\n"
						+ "       ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,\r\n"
						+ "       ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,\r\n"
						+ "       ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0') AS doe,\r\n"
						+ "       ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\r\n"
						+ "       'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\r\n"
						+ "         'C_E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') || 'C_O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || 'C_C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || 'C_P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') AS cope,\r\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY')) AS dom,\r\n"
						+ "       string_agg(distinct cour.course_name, ', ') army_courses,\r\n"
						+ "       ma.maiden_name AS spousename,\r\n"
						+ "	   orb.unit_name as unit_name\r\n"
						+ "  FROM tb_psg_census_jco_or_p c\r\n"
						+ " INNER JOIN tb_psg_mstr_rank_jco r\r\n"
						+ "    ON c.rank = r.id\r\n"
						+ "   AND c.status = '1'\r\n"
						+ " INNER JOIN tb_psg_mstr_trade_jco t\r\n"
						+ "    ON t.id = c.trade\r\n"
						+ "INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "	ON c.unit_sus_no = orb.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "  LEFT JOIN tb_miso_orbat_arm_code e\r\n"
						+ "    ON e.arm_code = c.arm_service\r\n"
						+ "  LEFT JOIN tb_psg_medical_category_jco med_main\r\n"
						+ "    ON med_main.jco_id = c.id\r\n"
						+ "   AND med_main.status IN ('1','2')\r\n"
						+ "  LEFT JOIN tb_psg_census_army_course_jco cour\r\n"
						+ "    ON cour.jco_id = c.id\r\n"
						+ "  LEFT JOIN tb_psg_census_family_married_jco ma\r\n"
						+ "    ON ma.jco_id = c.id\r\n"
						+ " WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\r\n"
						+ "   AND  c.category='JCO'  "
						+ qry + "\n" + SearchValue + " GROUP BY 1,2, 3, 4,5,6,7,8,\n" + "          ma.marriage_date,\n"
						+ "          ma.maiden_name, orb.unit_name"

			  					+ " ) app ";


				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
			}else{

				q = "select count (app.*) from(SELECT  c.army_no,\r\n"
						+ "       r.rank AS rk,\r\n"
						+ "       c.full_name AS name,\r\n"
						+ "       t.trade AS trade,\r\n"
						+ "       ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,\r\n"
						+ "       ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,\r\n"
						+ "       ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0') AS doe,\r\n"
						+ "       ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\r\n"
						+ "       'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\r\n"
						+ "         'C_E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') || 'C_O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || 'C_C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || 'C_P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') AS cope,\r\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY')) AS dom,\r\n"
						+ "       string_agg(distinct cour.course_name, ', ') army_courses,\r\n"
						+ "       ma.maiden_name AS spousename,\r\n"
						+ "	   orb.unit_name as unit_name\r\n"
						+ "  FROM tb_psg_census_jco_or_p c\r\n"
						+ " INNER JOIN tb_psg_mstr_rank_jco r\r\n"
						+ "    ON c.rank = r.id\r\n"
						+ "   AND c.status = '1'\r\n"
						+ " INNER JOIN tb_psg_mstr_trade_jco t\r\n"
						+ "    ON t.id = c.trade\r\n"
						+ "INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "	ON c.unit_sus_no = orb.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "  LEFT JOIN tb_miso_orbat_arm_code e\r\n"
						+ "    ON e.arm_code = c.arm_service\r\n"
						+ "  LEFT JOIN tb_psg_medical_category_jco med_main\r\n"
						+ "    ON med_main.jco_id = c.id\r\n"
						+ "   AND med_main.status IN ('1','2')\r\n"
						+ "  LEFT JOIN tb_psg_census_army_course_jco cour\r\n"
						+ "    ON cour.jco_id = c.id\r\n"
						+ "  LEFT JOIN tb_psg_census_family_married_jco ma\r\n"
						+ "    ON ma.jco_id = c.id\n" + " WHERE c.unit_sus_no = ? \n" + SearchValue
						+ "   AND  c.category='JCO'  "
						+ qry + "\n" + " GROUP BY 1,2, 3, 4,5,6,7,8,\n" + "          ma.marriage_date,\n"
						+ "          ma.maiden_name,orb.unit_name"

									+ " ) app ";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}



			int j = 2;
			if (name != "" && name != null) {
				stmt.setString(j,"%" + name.toUpperCase() + "%");
				j += 1;
			}
			if (rank != "" && rank != null) {
				stmt.setInt(j, Integer.parseInt(rank));
				j += 1;
			}
			if (trade != "" && trade != null) {
				stmt.setString(j, trade);
				j += 1;
			}
			if (armyNo != "" && armyNo != null) {
				stmt.setString(j, armyNo);
			}
			int flag = 1;
			if (Search != "") {
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

			//				stmt = setQueryWhereClause_SQLPERS(stmt,Search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);

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


	// CIVS
	@Override
	public List<Map<String, Object>> getUnitReportPersCivs(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId) {
		//	 String SearchValue = GenerateQueryWhereClause_SQLPERS(search,  appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
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

			q = "  limit " + pageL + " OFFSET " + startPage + "";

			stmt = conn.prepareStatement(q);

			//			stmt = setQueryWhereClause_SQLPERS(stmt,search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);

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
	public long getUnitReportPersCivscount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId) {
		//		String SearchValue = GenerateQueryWhereClause_SQLPERS(Search,  appt_trade, rank, rank_cat, cont_comd, cont_corps,  cont_div,  cont_bde, radio1);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count (app.*) from( ) app ";

			PreparedStatement stmt = conn.prepareStatement(q);
			//				stmt = setQueryWhereClause_SQLPERS(stmt,Search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);

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

	// changes by Mitesh(06-12-24)
	@Override
	public List<Map<String, Object>> getUnitReportVeh(int startPage, int pageLength, String Search, String orderColunm,
			String orderType, HttpSession sessionUserId, String type_veh, String roleSusNo,String 	ba_no,String
			broad_cat,String veh_cat, String formationtype) {
		//			 String SearchValue = GenerateQueryWhereClause_SQLPERS(search,  appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
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
			String SearchValue = "";
			String s = "";
			if (type_veh.equals("A")) {
				s = " or   upper(pa.classification )like ? ";
			} else {
				s = " or   upper(part.classification )like ? ";
			}
			if (!ba_no.equals("")) {
				if (type_veh.equals("C")) {
					SearchValue += " and   part.em_no =? ";
				}else {
					SearchValue += " and   part.ba_no =? ";

				}

			}
			if (!broad_cat.equals("")) {
				SearchValue += " and   b.mct_nomen =? ";
			}
			if (!veh_cat.equals("")) {
				SearchValue += " and   s.group_name =? ";
			}

			
			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);
				if (type_veh.equals("A")) {
					if (Search != "") {
						SearchValue += " and   ";
						SearchValue += "(upper(part.ba_no) like ?   " + "or upper( b.mct_nomen) like ? "
								+ "or upper( u.unit_name) like ? " + "or upper( bd.veh_cat) like ?" + s
								+ "or upper(u.sus_no) like ?" + " or upper( s.group_name) like ? "
								+ "or upper( b.vehicle_class_code) like ? " + ")"

									;
					}
					q = " SELECT DISTINCT\n" + "        ON (part.ba_no) part.ba_no,\n"
							+ "              b.mct_nomen AS broad_cat,\n" + "              u.unit_name,\n"
							+ "              bd.veh_cat,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS veh_type,\n"
							+ "              CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\n"
							+ "                        ELSE 'UNSV'\n" + "                          END AS serv_unsv,\n"
							+ "              part.vehcl_classfctn\n" +
							" AS classification,\n"
							+ "              round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "    FROM tb_tms_census_retrn part\n" + "  INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "        ON part.ba_no = bd.ba_no\n" + "  INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "        ON u.status_sus_no = 'Active'\n" + "      AND part.sus_no = u.sus_no\n"
							+ "  INNER JOIN tb_tms_mct_main_master b\n"
							+ "        ON substr(bd.mct::varchar,1,4) = b.mct_main_id  AND b.type_of_veh::text = 'A'::text\n"
							+ "    LEFT JOIN tb_tms_mct_slot_master s\n" + "        ON s.prf_code = b.prf_code\n"
							+ "   left JOIN tb_tms_mvcr_parta_dtl AS pa\n" + "		ON pa.ba_no = bd.ba_no\n"
							+ "  WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n" + "      AND bd.veh_cat = 'A'   " + SearchValue + "  limit "
							+ pageL + " OFFSET " + startPage + "";
				}
				if (type_veh.equals("B")) {
					if (Search != "") {
						SearchValue += " and   ";
						SearchValue += "(upper(part.ba_no) like ?   " + "or upper( b.mct_nomen) like ? "
								+ "or upper( u.unit_name) like ? " + "or upper( bd.veh_cat) like ?" + s
								+ "or upper(u.sus_no) like ?" + " or upper( s.group_name) like ? "
								+ "or upper( b.vehicle_class_code) like ? " + ")"

									;
					}
					q = " SELECT distinct on (part.ba_no) part.ba_no,\n" + "              b.mct_nomen AS broad_cat,\n"
							+ "              bd.veh_cat,\n" + "              u.unit_name,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS veh_type,\n"
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
							+ "      AND bd.veh_cat = 'B'   \n" + SearchValue + "  GROUP BY 1,2,3,4,5,6,7, 8,9     "
							+ "  limit " + pageL + " OFFSET " + startPage + "";
				}
				if (type_veh.equals("C")) {
					if (Search != "") {
						SearchValue += " and   ";
						SearchValue += "(upper(part.em_no) like ?   " + "or upper( b.mct_nomen) like ? "
								+ "or upper( u.unit_name) like ? " + "or upper( bd.veh_cat) like ?" + s
								+ "or upper(u.sus_no) like ?" + " or upper( s.group_name) like ? "
								+ "or upper( b.vehicle_class_code) like ?   " + ")"

									;
					}
					q = "SELECT DISTINCT\n" + "        ON (part.em_no) part.em_no AS ba_no,\n"
							+ "              b.mct_nomen AS broad_cat,\n" + "                bd.veh_cat,\n"
							+ "              u.unit_name,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS veh_type,\n"
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
							+ "  WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') " + SearchValue + "  limit " + pageL + " OFFSET " + startPage + "";
				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);




			}else{

				if (type_veh.equals("A")) {
					if (Search != "") {
						SearchValue += " and   ";
						SearchValue += "(upper(part.ba_no) like ?   " + "or upper( b.mct_nomen) like ? "
								+ "or upper( u.unit_name) like ? " + "or upper( bd.veh_cat) like ?" + s
								+ "or upper(u.sus_no) like ?" + " or upper( s.group_name) like ? "
								+ "or upper( b.vehicle_class_code) like ? " + ")"

										;
					}
					q = " SELECT DISTINCT\n" + "        ON (part.ba_no) part.ba_no,\n"
							+ "              b.mct_nomen AS broad_cat,\n" + "              u.unit_name,\n"
							+ "              bd.veh_cat,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS veh_type,\n"
							+ "              CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\n"
							+ "                        ELSE 'UNSV'\n" + "                          END AS serv_unsv,\n"
							+ "              part.vehcl_classfctn\n" +
							" AS classification,\n"
							+ "              round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "    FROM tb_tms_census_retrn part\n" + "  INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "        ON part.ba_no = bd.ba_no\n" + "  INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "        ON u.status_sus_no = 'Active'\n" + "      AND part.sus_no = u.sus_no\n"
							+ "  INNER JOIN tb_tms_mct_main_master b\n"
							+ "        ON substr(bd.mct::varchar,1,4) = b.mct_main_id  AND b.type_of_veh::text = 'A'::text\n"
							+ "    LEFT JOIN tb_tms_mct_slot_master s\n" + "        ON s.prf_code = b.prf_code\n"
							+ "   left JOIN tb_tms_mvcr_parta_dtl AS pa\n" + "		ON pa.ba_no = bd.ba_no\n"
							+ "  WHERE part.sus_no = ? \n" + "      AND bd.veh_cat = 'A'   " + SearchValue + "  limit "
							+ pageL + " OFFSET " + startPage + "";
				}
				if (type_veh.equals("B")) {
					if (Search != "") {
						SearchValue += " and   ";
						SearchValue += "(upper(part.ba_no) like ?   " + "or upper( b.mct_nomen) like ? "
								+ "or upper( u.unit_name) like ? " + "or upper( bd.veh_cat) like ?" + s
								+ "or upper(u.sus_no) like ?" + " or upper( s.group_name) like ? "
								+ "or upper( b.vehicle_class_code) like ? " + ")"

										;
					}
					q = " SELECT distinct on (part.ba_no) part.ba_no,\n" + "              b.mct_nomen AS broad_cat,\n"
							+ "              bd.veh_cat,\n" + "              u.unit_name,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS veh_type,\n"
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
							+ "      AND bd.veh_cat = 'B'   \n" + SearchValue + "  GROUP BY 1,2,3,4,5,6,7, 8,9     "
							+ "  limit " + pageL + " OFFSET " + startPage + "";
				}
				if (type_veh.equals("C")) {
					if (Search != "") {
						SearchValue += " and   ";
						SearchValue += "(upper(part.em_no) like ?   " + "or upper( b.mct_nomen) like ? "
								+ "or upper( u.unit_name) like ? " + "or upper( bd.veh_cat) like ?" + s
								+ "or upper(u.sus_no) like ?" + " or upper( s.group_name) like ? "
								+ "or upper( b.vehicle_class_code) like ?   " + ")"

										;
					}
					q = "SELECT DISTINCT\n" + "        ON (part.em_no) part.em_no AS ba_no,\n"
							+ "              b.mct_nomen AS broad_cat,\n" + "                bd.veh_cat,\n"
							+ "              u.unit_name,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS veh_type,\n"
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
							+ "  WHERE part.sus_no = ? " + SearchValue + "  limit " + pageL + " OFFSET " + startPage + "";
				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}


			int flag = 1;
			if (!ba_no.equals("")) {
				flag += 1;
				stmt.setString(flag,ba_no );
			}
			if (!broad_cat.equals("")) {
				flag += 1;
				stmt.setString(flag,broad_cat );
			}
			if (!veh_cat.equals("")) {
				flag += 1;
				stmt.setString(flag,veh_cat );
			}
			if (Search != "") {
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

			//					stmt = setQueryWhereClause_SQLPERS(stmt,search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);

			ResultSet rs = stmt.executeQuery();
			System.out.println("QUERY A --> " + stmt);
			System.out.println(stmt);
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
	public long getUnitReportVehcount(String Search, String orderColunm, String orderType, String type_veh,
			String roleSusNo,String 	ba_no,String
			broad_cat,String veh_cat, HttpSession sessionUserId, String formationtype) {
		//			String SearchValue = GenerateQueryWhereClause_SQLPERS(Search,  appt_trade, rank, rank_cat, cont_comd, cont_corps,  cont_div,  cont_bde, radio1);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String SearchValue = "";
			String s = "";
			if (type_veh.equals("A")) {
				s = " or   upper(pa.classification )like ? ";
			} else {
				s = " or   upper(part.classification )like ? ";
			}
			if (!ba_no.equals("")) {
				if (type_veh.equals("C")) {
					SearchValue += " and   part.em_no =? ";
				}else {
					SearchValue += " and   part.ba_no =? ";
				}


			}
			if (!broad_cat.equals("")) {
				SearchValue += " and   b.mct_nomen =? ";
			}
			if (!veh_cat.equals("")) {
				SearchValue += " and   s.group_name =? ";
			}

			PreparedStatement stmt;
			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				if (type_veh.equals("A")) {
					if (Search != "") {
						SearchValue += " and   ";
						SearchValue += "(upper(part.ba_no) like ?   " + "or upper( b.mct_nomen) like ? "
								+ "or upper( u.unit_name) like ? " + "or upper( bd.veh_cat) like ?" + s
								+ "or upper(u.sus_no) like ?" + " or upper( s.group_name) like ? "
								+ "or upper( b.vehicle_class_code) like ? " + ")"

			  					;
					}

					q = "select count (app.*) from(SELECT DISTINCT\n" + "        ON (part.ba_no) part.ba_no,\n"
							+ "              b.mct_nomen AS broad_cat,\n" + "              u.unit_name,\n"
							+ "              bd.veh_cat,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS broad_cat,\n"
							+ "              CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                        ELSE 'UNSV'\n" + "                          END AS serv_unsv,\n"
							+ "              part.vehcl_classfctn\n" +
							" AS classification,\n"
							+ "              round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "    FROM tb_tms_census_retrn part\n" + "  INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "        ON part.ba_no = bd.ba_no\n" + "  INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "        ON u.status_sus_no = 'Active'\n" + "      AND part.sus_no = u.sus_no\n"
							+ "  INNER JOIN tb_tms_mct_main_master b\n"
							+ "        ON substr(bd.mct::varchar,1,4) = b.mct_main_id    AND b.type_of_veh::text = 'A'::text \n"
							+ "    LEFT JOIN tb_tms_mct_slot_master s\n" + "        ON s.prf_code = b.prf_code\n"
							+ "   left JOIN tb_tms_mvcr_parta_dtl AS pa\n" + "		ON pa.ba_no = bd.ba_no\n"
							+ "  WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "      AND bd.veh_cat = 'A' \n" + SearchValue + " ) app ";
				}
				if (type_veh.equals("B")) {
					if (Search != "") {
						SearchValue += " and   ";
						SearchValue += "(upper(part.ba_no) like ?   " + "or upper( b.mct_nomen) like ? "
								+ "or upper( u.unit_name) like ? " + "or upper( bd.veh_cat) like ?" + s
								+ "or upper(u.sus_no) like ?" + " or upper( s.group_name) like ? "
								+ "or upper( b.vehicle_class_code) like ? " + ")"

			  					;
					}

					q = "select count (app.*) from( SELECT distinct on (part.ba_no) part.ba_no,\n"
							+ "              b.mct_nomen AS broad_cat,\n" + "              bd.veh_cat,\n"
							+ "              u.unit_name,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS broad_cat,\n"
							+ "              part.classification AS classification,\n"
							+ "              CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                        ELSE 'UNSV'\n" + "                          END AS serv_unsv,\n"
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
							+ "        ON a.mct_main_id = b.mct_main_id\n" + "  WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
							+ "      AND bd.veh_cat = 'B'\n" + SearchValue + "  GROUP BY 1,2,3,4,5,6,7, 8,9     ) app ";
				}
				if (type_veh.equals("C")) {
					if (Search != "") {
						SearchValue += " and   ";
						SearchValue += "(upper(part.em_no) like ?   " + "or upper( b.mct_nomen) like ? "
								+ "or upper( u.unit_name) like ? " + "or upper( bd.veh_cat) like ?" + s
								+ "or upper(u.sus_no) like ?" + " or upper( s.group_name) like ? "
								+ "or upper( b.vehicle_class_code) like ? " + ")"

			  					;
					}
					q = "select count (app.*) from( SELECT DISTINCT\n" + "        ON (part.em_no) part.em_no AS ba_no,\n"
							+ "              b.mct_nomen AS broad_cat,\n" + "                bd.veh_cat,\n"
							+ "              u.unit_name,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS broad_cat,\n"
							+ "                CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                        ELSE 'UNSV'\n" + "                          END AS serv_unsv,\n"
							+ "              round(part.current_km_run + ((part.current_km_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\n"
							+ "              part.classification AS classification\n" + "    FROM tb_tms_emar_report part\n"
							+ "  INNER JOIN tb_tms_banum_dirctry bd\n" + "        ON part.em_no = bd.ba_no\n"
							+ "  INNER JOIN tb_miso_orbat_unt_dtl u\n" + "        ON u.status_sus_no = 'Active'\n"
							+ "      AND part.sus_no = u.sus_no\n" + "  INNER JOIN tb_tms_mct_main_master b\n"
							+ "        ON substr(mct::varchar,1,4) = b.mct_main_id     AND b.type_of_veh::text = 'C'::text\n"
							+ "            LEFT JOIN tb_tms_mct_slot_master s\n" + "        ON s.prf_code = b.prf_code\n"
							+ "  WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')" + SearchValue + "	) app ";
				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);



			}else{


				if (type_veh.equals("A")) {
					if (Search != "") {
						SearchValue += " and   ";
						SearchValue += "(upper(part.ba_no) like ?   " + "or upper( b.mct_nomen) like ? "
								+ "or upper( u.unit_name) like ? " + "or upper( bd.veh_cat) like ?" + s
								+ "or upper(u.sus_no) like ?" + " or upper( s.group_name) like ? "
								+ "or upper( b.vehicle_class_code) like ? " + ")"

									;
					}

					q = "select count (app.*) from(SELECT DISTINCT\n" + "        ON (part.ba_no) part.ba_no,\n"
							+ "              b.mct_nomen AS broad_cat,\n" + "              u.unit_name,\n"
							+ "              bd.veh_cat,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS broad_cat,\n"
							+ "              CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                        ELSE 'UNSV'\n" + "                          END AS serv_unsv,\n"
							+ "              part.vehcl_classfctn\n" +
							" AS classification,\n"
							+ "              round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "    FROM tb_tms_census_retrn part\n" + "  INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "        ON part.ba_no = bd.ba_no\n" + "  INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "        ON u.status_sus_no = 'Active'\n" + "      AND part.sus_no = u.sus_no\n"
							+ "  INNER JOIN tb_tms_mct_main_master b\n"
							+ "        ON substr(bd.mct::varchar,1,4) = b.mct_main_id    AND b.type_of_veh::text = 'A'::text \n"
							+ "    LEFT JOIN tb_tms_mct_slot_master s\n" + "        ON s.prf_code = b.prf_code\n"
							+ "   left JOIN tb_tms_mvcr_parta_dtl AS pa\n" + "		ON pa.ba_no = bd.ba_no\n"
							+ "  WHERE part.sus_no = ?\n" + "      AND bd.veh_cat = 'A' \n" + SearchValue + " ) app ";
				}
				if (type_veh.equals("B")) {
					if (Search != "") {
						SearchValue += " and   ";
						SearchValue += "(upper(part.ba_no) like ?   " + "or upper( b.mct_nomen) like ? "
								+ "or upper( u.unit_name) like ? " + "or upper( bd.veh_cat) like ?" + s
								+ "or upper(u.sus_no) like ?" + " or upper( s.group_name) like ? "
								+ "or upper( b.vehicle_class_code) like ? " + ")"

									;
					}

					q = "select count (app.*) from( SELECT distinct on (part.ba_no) part.ba_no,\n"
							+ "              b.mct_nomen AS broad_cat,\n" + "              bd.veh_cat,\n"
							+ "              u.unit_name,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS broad_cat,\n"
							+ "              part.classification AS classification,\n"
							+ "              CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                        ELSE 'UNSV'\n" + "                          END AS serv_unsv,\n"
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
							+ "        ON a.mct_main_id = b.mct_main_id\n" + "  WHERE part.sus_no = ?\n"
							+ "      AND bd.veh_cat = 'B'\n" + SearchValue + "  GROUP BY 1,2,3,4,5,6,7, 8,9     ) app ";
				}
				if (type_veh.equals("C")) {
					if (Search != "") {
						SearchValue += " and   ";
						SearchValue += "(upper(part.em_no) like ?   " + "or upper( b.mct_nomen) like ? "
								+ "or upper( u.unit_name) like ? " + "or upper( bd.veh_cat) like ?" + s
								+ "or upper(u.sus_no) like ?" + " or upper( s.group_name) like ? "
								+ "or upper( b.vehicle_class_code) like ? " + ")"

									;
					}
					q = "select count (app.*) from( SELECT DISTINCT\n" + "        ON (part.em_no) part.em_no AS ba_no,\n"
							+ "              b.mct_nomen AS broad_cat,\n" + "                bd.veh_cat,\n"
							+ "              u.unit_name,\n" + "              u.sus_no,\n"
							+ "              s.group_name AS broad_cat,\n"
							+ "                CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                        ELSE 'UNSV'\n" + "                          END AS serv_unsv,\n"
							+ "              round(part.current_km_run + ((part.current_km_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\n"
							+ "              part.classification AS classification\n" + "    FROM tb_tms_emar_report part\n"
							+ "  INNER JOIN tb_tms_banum_dirctry bd\n" + "        ON part.em_no = bd.ba_no\n"
							+ "  INNER JOIN tb_miso_orbat_unt_dtl u\n" + "        ON u.status_sus_no = 'Active'\n"
							+ "      AND part.sus_no = u.sus_no\n" + "  INNER JOIN tb_tms_mct_main_master b\n"
							+ "        ON substr(mct::varchar,1,4) = b.mct_main_id     AND b.type_of_veh::text = 'C'::text\n"
							+ "            LEFT JOIN tb_tms_mct_slot_master s\n" + "        ON s.prf_code = b.prf_code\n"
							+ "  WHERE part.sus_no =?" + SearchValue + "	) app ";
				}

				stmt = conn.prepareStatement(q);
				//									stmt = setQueryWhereClause_SQLPERS(stmt,Search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
				stmt.setString(1, roleSusNo);

			}


			int flag = 1;

			if (!ba_no.equals("")) {
				flag += 1;
				stmt.setString(flag,ba_no );
			}
			if (!broad_cat.equals("")) {
				flag += 1;
				stmt.setString(flag,broad_cat );
			}
			if (!veh_cat.equals("")) {
				flag += 1;
				stmt.setString(flag,veh_cat );
			}
			if (Search != "") {
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
				stmt.setString(flag,"%" +  Search.toUpperCase() + "%");

			}
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

	// WPN AND EQPT
	@Override
	public List<Map<String, Object>> getUnitReportWpnEqpt(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String type_mtrls, String roleSusNo,
			String prfgp, String nomenclature, String census_no, String service_status,String broadCat, String formationtype) {
		//		 String SearchValue = GenerateQueryWhereClause_SQLPERS(search,  appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
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
			String SearchValue = "";

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "(upper(a.eqpt_regn_no) like ?   "

						+ "or upper( f.prf_group) like ?" + "or upper( a.census_no) like ?"
						+ " or upper( m.nomen) like ? "
						+ "or upper( t1.label) like ? or upper(orb.unit_name) like ? "+ ")"

				;
			}

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	
			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);
				if (type_mtrls.equals("wpn")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
					q = "  SELECT \r\n"
							+ " a.eqpt_regn_no,\r\n"
							+ " f.prf_group,\r\n"
							+ " a.census_no,\r\n"
							+ "  m.nomen,\r\n"
							+ "	   CASE WHEN a.service_status = '0' THEN 'UNSV'\r\n"
							+ "                        ELSE 'SER'\r\n"
							+ "                          END AS ser_unsv,\r\n"
							+ "	 t1.label as type_of_hldg_n,\r\n"
							+ " i.item_group, orb.unit_name as unit_name\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "	INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C') " + SearchValue
							+ "     order by f.prf_group,m.nomen" + "  limit " + pageL + " OFFSET " + startPage + "";
				}
				if (type_mtrls.equals("eqi")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
					q = "  SELECT \r\n"
							+ " a.eqpt_regn_no,\r\n"
							+ " f.prf_group,\r\n"
							+ " a.census_no,\r\n"
							+ "  m.nomen,\r\n"
							+ "	   CASE WHEN a.service_status = '0' THEN 'UNSV'\r\n"
							+ "                        ELSE 'SER'\r\n"
							+ "                          END AS ser_unsv,\r\n"
							+ "	 t1.label as type_of_hldg_n,\r\n"
							+ " i.item_group, orb.unit_name as unit_name\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "	INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) NOT in ('B','M','N','O','C','G') \n" + SearchValue
							+ "     order by f.prf_group,m.nomen" + "  limit " + pageL + " OFFSET " + startPage + "";
				}

				stmt = conn.prepareStatement(q);

				stmt.setString(1, from_code_control);



			}else{

				if (type_mtrls.equals("wpn")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
					q = " SELECT \r\n"
							+ " a.eqpt_regn_no,\r\n"
							+ " f.prf_group,\r\n"
							+ " a.census_no,\r\n"
							+ "  m.nomen,\r\n"
							+ "	   CASE WHEN a.service_status = '0' THEN 'UNSV'\r\n"
							+ "                        ELSE 'SER'\r\n"
							+ "                          END AS ser_unsv,\r\n"
							+ "	 t1.label as type_of_hldg_n,\r\n"
							+ " i.item_group, orb.unit_name as unit_name\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "	INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no =? \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C') \r\n"

										 +  SearchValue
										 + "     order by f.prf_group,m.nomen" + "  limit " + pageL + " OFFSET " + startPage + "";
				}
				if (type_mtrls.equals("eqi")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
					q = "  SELECT \r\n"
							+ " a.eqpt_regn_no,\r\n"
							+ " f.prf_group,\r\n"
							+ " a.census_no,\r\n"
							+ "  m.nomen,\r\n"
							+ "	   CASE WHEN a.service_status = '0' THEN 'UNSV'\r\n"
							+ "                        ELSE 'SER'\r\n"
							+ "                          END AS ser_unsv,\r\n"
							+ "	 t1.label as type_of_hldg_n,\r\n"
							+ " i.item_group, orb.unit_name as unit_name\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		 INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
							+ "		where  a.sus_no =? \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) NOT IN ('B','M','N','O','C','G') \n" + SearchValue
							+ "     order by f.prf_group,m.nomen" + "  limit " + pageL + " OFFSET " + startPage + "";
				}

				stmt = conn.prepareStatement(q);

				stmt.setString(1, roleSusNo);

			}


			int flag = 1;
			if (Search != "") {
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
			if (type_mtrls.equals("eqi")) {

				if(!prfgp.equals("")) {
					flag += 1;
					stmt.setString(flag, prfgp.toUpperCase()+"%");
				}

				if(!nomenclature.equals("")) {
					flag += 1;
					stmt.setString(flag, nomenclature.toUpperCase()+"%");
				}

				if(!census_no.equals("")) {
					flag += 1;
					stmt.setString(flag, census_no.toUpperCase()+"%");
				}

				if(!service_status.equals("")) {
					flag += 1;
					stmt.setString(flag, service_status.toUpperCase());
				}

				if(!broadCat.equals("")) {
					flag += 1;
					stmt.setString(flag, broadCat.toUpperCase());
				}
			}

			if (type_mtrls.equals("wpn")) {

				if(!prfgp.equals("")) {
					flag += 1;
					stmt.setString(flag, prfgp.toUpperCase()+"%");
				}

				if(!nomenclature.equals("")) {
					flag += 1;
					stmt.setString(flag, nomenclature.toUpperCase()+"%");
				}

				if(!census_no.equals("")) {
					flag += 1;
					stmt.setString(flag, census_no.toUpperCase()+"%");
				}

				if(!service_status.equals("")) {
					flag += 1;
					stmt.setString(flag, service_status.toUpperCase());
				}
				if(!broadCat.equals("")) {
					flag += 1;
					stmt.setString(flag, broadCat.toUpperCase());
				}
			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("QUERY WPN --> " + stmt);
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
	public long getUnitReportWpnEqptcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String type_mtrls, String roleSusNo, String  prfgp,String nomenclature,String census_no,String service_status,String broadCat, String formationtype) {
		//			String SearchValue = GenerateQueryWhereClause_SQLPERS(Search,  appt_trade, rank, rank_cat, cont_comd, cont_corps,  cont_div,  cont_bde, radio1);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String SearchValue = "";

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "(upper(a.eqpt_regn_no) like ?   "
						+ "or upper( f.prf_group) like ?" + "or upper( a.census_no) like ?"
						+ " or upper( m.nomen) like ? "
						+ "or upper( t1.label) like ? or upper(orb.unit_name) like ? "+ ")"

				;
			}

			PreparedStatement stmt=null;
			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);
				if (type_mtrls.equals("wpn")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
					q = "select count (app.*) from( SELECT \r\n"
							+ " a.eqpt_regn_no,\r\n"
							+ " f.prf_group,\r\n"
							+ " a.census_no,\r\n"
							+ "  m.nomen,\r\n"
							+ "	   CASE WHEN a.service_status = '0' THEN 'UNSV'\r\n"
							+ "                        ELSE 'SER'\r\n"
							+ "                          END AS ser_unsv,\r\n"
							+ "	 t1.label as type_of_hldg_n,\r\n"
							+ " i.item_group, orb.unit_name as unit_name\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "	INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C')    \n" + SearchValue
							+ "     order by f.prf_group,m.nomen) app ";
				}

				if (type_mtrls.equals("eqi")) {


					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
					q = "select count (app.*) from(SELECT \r\n"
							+ " a.eqpt_regn_no,\r\n"
							+ " f.prf_group,\r\n"
							+ " a.census_no,\r\n"
							+ "  m.nomen,\r\n"
							+ "	   CASE WHEN a.service_status = '0' THEN 'UNSV'\r\n"
							+ "                        ELSE 'SER'\r\n"
							+ "                          END AS ser_unsv,\r\n"
							+ "	 t1.label as type_of_hldg_n,\r\n"
							+ " i.item_group, orb.unit_name as unit_name\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "	INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) NOT IN ('B','M','N','O','C','G')   \n" + SearchValue
							+ "     order by f.prf_group,m.nomen ) app ";

				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);


			}else{


				if (type_mtrls.equals("wpn")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
					q = "select count (app.*) from( SELECT \r\n"
							+ " a.eqpt_regn_no,\r\n"
							+ " f.prf_group,\r\n"
							+ " a.census_no,\r\n"
							+ "  m.nomen,\r\n"
							+ "	   CASE WHEN a.service_status = '0' THEN 'UNSV'\r\n"
							+ "                        ELSE 'SER'\r\n"
							+ "                          END AS ser_unsv,\r\n"
							+ "	 t1.label as type_of_hldg_n,\r\n"
							+ " i.item_group, orb.unit_name as unit_name\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "	INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no =? \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C')   \n" + SearchValue
							+ "     order by f.prf_group,m.nomen) app ";
				}

				if (type_mtrls.equals("eqi")) {


					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
					q = "select count (app.*) from(SELECT \r\n"
							+ " a.eqpt_regn_no,\r\n"
							+ " f.prf_group,\r\n"
							+ " a.census_no,\r\n"
							+ "  m.nomen,\r\n"
							+ "	   CASE WHEN a.service_status = '0' THEN 'UNSV'\r\n"
							+ "                        ELSE 'SER'\r\n"
							+ "                          END AS ser_unsv,\r\n"
							+ "	 t1.label as type_of_hldg_n,\r\n"
							+ " i.item_group, orb.unit_name as unit_name\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		 INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
							+ "		where  a.sus_no =?  \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) NOT IN ('B','M','N','O','C','G')   \n" + SearchValue
							+ "     order by f.prf_group,m.nomen ) app ";

				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
			}


			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  Search.toUpperCase() + "%");


			}
			if (type_mtrls.equals("eqi")) {

				if(!prfgp.equals("")) {
					flag += 1;
					stmt.setString(flag, prfgp.toUpperCase()+"%");
				}

				if(!nomenclature.equals("")) {
					flag += 1;
					stmt.setString(flag, nomenclature.toUpperCase()+"%");
				}

				if(!census_no.equals("")) {
					flag += 1;
					stmt.setString(flag, census_no.toUpperCase()+"%");
				}

				if(!service_status.equals("")) {
					flag += 1;
					stmt.setString(flag, service_status.toUpperCase());
				}
				if(!broadCat.equals("")) {
					flag += 1;
					stmt.setString(flag, broadCat.toUpperCase());
				}
			}

			if (type_mtrls.equals("wpn")) {

				if(!prfgp.equals("")) {
					flag += 1;
					stmt.setString(flag, prfgp.toUpperCase()+"%");
				}

				if(!nomenclature.equals("")) {
					flag += 1;
					stmt.setString(flag, nomenclature.toUpperCase()+"%");
				}

				if(!census_no.equals("")) {
					flag += 1;
					stmt.setString(flag, census_no.toUpperCase()+"%");
				}

				if(!service_status.equals("")) {
					flag += 1;
					stmt.setString(flag, service_status.toUpperCase());
				}
				if(!broadCat.equals("")) {
					flag += 1;
					stmt.setString(flag, broadCat.toUpperCase());
				}
			}
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

	// IT Asset
	@Override
	public List<Map<String, Object>> getUnitReportIT_Assets(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String roleSusNo, String formationtype) {
		//		 String SearchValue = GenerateQueryWhereClause_SQLPERS(search,  appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
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

			String SearchValue = "";

			if (Search != "") {
				SearchValue = " where    ";
				SearchValue += "(upper(assets_type) like ?   " + "or upper(assets_name) like ? "
						+ "or upper( machine_number) like ?" + "or upper( office) like ?" + " or upper( os) like ? "
						+ "or upper( processor_type) like ? " + "or upper( dply_env) like ? or upper(unit_name) like ? )"

				;
			}

			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				q = "SELECT * FROM (SELECT\r\n"
						+ "              'computing' AS assets_type,\r\n"
						+ "              b.assets_name,\r\n"
						+ "              a.machine_number,\r\n"
						+ "              c.office,\r\n"
						+ "              o.os,\r\n"
						+ "              CAST(EXTRACT(YEAR FROM proc_date) AS VARCHAR) AS year_of_proc, \r\n"
						+ "              n.processor_type,\r\n"
						+ "              d.dply_env, \r\n"
						+ "			  orb.unit_name as unit_name\r\n"
						+ "      FROM tb_asset_main a\r\n"
						+ "              INNER JOIN tb_mstr_assets b ON a.asset_type = b.id AND b.category = 1\r\n"
						+ "              INNER JOIN tb_mstr_os o ON o.id = a.operating_system\r\n"
						+ "              INNER JOIN tb_mstr_processor_type n ON n.id = a.processor_type\r\n"
						+ "              INNER JOIN tb_mstr_dply_env d ON d.id = a.dply_envt\r\n"
						+ "              LEFT JOIN tb_mstr_office c ON c.id = a.office \r\n"
						+ "				 inner join  tb_mstr_make mm on  mm.id=a.make_name \r\n"
						+ "				 inner join  tb_mstr_model m on  m.id=a.model_name  \r\n"
						+ "				 inner join  tb_mstr_budget y on  y.id::text=a.b_code\r\n"
						+ "				 INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "    where a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active' ) and a.s_state='1' and a.status='1' UNION ALL \n"
						+ "      SELECT\r\n"
						+ "              'peripherals' AS assets_type,\r\n"
						+ "              b.assets_name,\r\n"
						+ "              a.machine_no AS machine_number,\r\n"
						+ "              '' AS office,\r\n"
						+ "              '' AS os,\r\n"
						+ "              a.year_of_proc,\r\n"
						+ "              '' AS processor_type,\r\n"
						+ "              '' AS dply_env,\r\n"
						+ "			  orb.unit_name as unit_name\r\n"
						+ "      FROM\r\n"
						+ "              it_asset_peripherals a\r\n"
						+ "              INNER JOIN tb_mstr_assets b ON a.assets_type = b.id AND b.category = 2\r\n"
						+ "				 inner join  tb_mstr_make mm on  mm.id=a.make_name \r\n"
						+ "              inner join  tb_mstr_model m on  m.id=a.model_name \r\n"
						+ "			  INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "			  and a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active' )and a.s_state='1' and a.status='1' )  a \n" + SearchValue + " limit " + pageL + " OFFSET " + startPage
						+ "";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
				stmt.setString(2, from_code_control);


			}else{
				q = "SELECT * FROM ( SELECT\r\n"
						+ "              'computing' AS assets_type,\r\n"
						+ "              b.assets_name,\r\n"
						+ "              a.machine_number,\r\n"
						+ "              c.office,\r\n"
						+ "              o.os,\r\n"
						+ "              CAST(EXTRACT(YEAR FROM proc_date) AS VARCHAR) AS year_of_proc, -- Cast to character varying\r\n"
						+ "              n.processor_type,\r\n"
						+ "              d.dply_env,\r\n"
						+ "			  orb.unit_name as unit_name\r\n"
						+ "      FROM tb_asset_main a\r\n"
						+ "              INNER JOIN tb_mstr_assets b ON a.asset_type = b.id AND b.category = 1\r\n"
						+ "              INNER JOIN tb_mstr_os o ON o.id = a.operating_system\r\n"
						+ "              INNER JOIN tb_mstr_processor_type n ON n.id = a.processor_type\r\n"
						+ "              INNER JOIN tb_mstr_dply_env d ON d.id = a.dply_envt\r\n"
						+ "			  INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "              LEFT JOIN tb_mstr_office c ON c.id = a.office  \n" + "    where a.sus_no= ? and s_state='1'   UNION ALL \n"
						+ "     SELECT\r\n"
						+ "              'peripherals' AS assets_type,\r\n"
						+ "              b.assets_name,\r\n"
						+ "              a.machine_no AS machine_number,\r\n"
						+ "              '' AS office,\r\n"
						+ "              '' AS os,\r\n"
						+ "              a.year_of_proc,\r\n"
						+ "              '' AS processor_type,\r\n"
						+ "              '' AS dply_env,\r\n"
						+ "			  orb.unit_name as unit_name\r\n"
						+ "      FROM\r\n"
						+ "              it_asset_peripherals a\r\n"
						+ "			  INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "              INNER JOIN tb_mstr_assets b ON a.assets_type = b.id AND b.category = 2\n"
						+ "			  and a.sus_no= ? and s_state='1' )  a \n" + SearchValue + " limit " + pageL + " OFFSET " + startPage
						+ "";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
				stmt.setString(2, roleSusNo);



			}

			int flag = 2;
			if (Search != "") {
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
				stmt.setString(flag,"%" +  Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			//				stmt = setQueryWhereClause_SQLPERS(stmt,search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);

			System.err.println("it uh datalist: \n"+stmt);
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
	public long getUnitReportIT_Assetscount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo, String formationtype) {
		//			String SearchValue = GenerateQueryWhereClause_SQLPERS(Search,  appt_trade, rank, rank_cat, cont_comd, cont_corps,  cont_div,  cont_bde, radio1);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String SearchValue = "";

			if (Search != "") {
				SearchValue = " where    ";
				SearchValue += "(upper(assets_type) like ?   " + "or upper(assets_name) like ? "
						+ "or upper( machine_number) like ?" + "or upper( office) like ?" + " or upper( os) like ? "
						+ "or upper( processor_type) like ? " + "or upper( dply_env) like ? or upper(unit_name) like ? )"

				;
			}

			PreparedStatement stmt=null;
			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				q = "select count (app.*) from(SELECT * FROM ( SELECT\r\n"
						+ "              'computing' AS assets_type,\r\n"
						+ "              b.assets_name,\r\n"
						+ "              a.machine_number,\r\n"
						+ "              c.office,\r\n"
						+ "              o.os,\r\n"
						+ "              CAST(EXTRACT(YEAR FROM proc_date) AS VARCHAR) AS year_of_proc, -- Cast to character varying\r\n"
						+ "              n.processor_type,\r\n"
						+ "              d.dply_env, \r\n"
						+ "			  orb.unit_name as unit_name\r\n"
						+ "      FROM tb_asset_main a\r\n"
						+ "              INNER JOIN tb_mstr_assets b ON a.asset_type = b.id AND b.category = 1\r\n"
						+ "              INNER JOIN tb_mstr_os o ON o.id = a.operating_system\r\n"
						+ "              INNER JOIN tb_mstr_processor_type n ON n.id = a.processor_type\r\n"
						+ "              INNER JOIN tb_mstr_dply_env d ON d.id = a.dply_envt\r\n"
						+ "              LEFT JOIN tb_mstr_office c ON c.id = a.office \r\n"
						+ "				 inner join  tb_mstr_make mm on  mm.id=a.make_name \r\n"
						+ "				 inner join  tb_mstr_model m on  m.id=a.model_name  \r\n"
						+ "				 inner join  tb_mstr_budget y on  y.id::text=a.b_code\r\n"
						+ "				 INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "    where a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')and a.s_state='1' and a.status='1'     UNION ALL \n"
						+ "      SELECT\r\n"
						+ "              'peripherals' AS assets_type,\r\n"
						+ "              b.assets_name,\r\n"
						+ "              a.machine_no AS machine_number,\r\n"
						+ "              '' AS office,\r\n"
						+ "              '' AS os,\r\n"
						+ "              a.year_of_proc,\r\n"
						+ "              '' AS processor_type,\r\n"
						+ "              '' AS dply_env,\r\n"
						+ "			  orb.unit_name as unit_name\r\n"
						+ "      FROM\r\n"
						+ "              it_asset_peripherals a\r\n"
						+ "              INNER JOIN tb_mstr_assets b ON a.assets_type = b.id AND b.category = 2\r\n"
						+ "				 inner join  tb_mstr_make mm on  mm.id=a.make_name \r\n"
						+ "              inner join  tb_mstr_model m on  m.id=a.model_name \r\n"
						+ "			  INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "			  and a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') and a.s_state='1' and a.status='1' )a  " + SearchValue + ") app ";

				stmt = conn.prepareStatement(q);

				stmt.setString(1, from_code_control);
				stmt.setString(2, from_code_control);

			}else{

				q = "select count (app.*) from(SELECT * FROM ( SELECT\r\n"
						+ "              'computing' AS assets_type,\r\n"
						+ "              b.assets_name,\r\n"
						+ "              a.machine_number,\r\n"
						+ "              c.office,\r\n"
						+ "              o.os,\r\n"
						+ "              CAST(EXTRACT(YEAR FROM proc_date) AS VARCHAR) AS year_of_proc, -- Cast to character varying\r\n"
						+ "              n.processor_type,\r\n"
						+ "              d.dply_env,\r\n"
						+ "			  orb.unit_name as unit_name\r\n"
						+ "      FROM tb_asset_main a\r\n"
						+ "              INNER JOIN tb_mstr_assets b ON a.asset_type = b.id AND b.category = 1\r\n"
						+ "              INNER JOIN tb_mstr_os o ON o.id = a.operating_system\r\n"
						+ "              INNER JOIN tb_mstr_processor_type n ON n.id = a.processor_type\r\n"
						+ "              INNER JOIN tb_mstr_dply_env d ON d.id = a.dply_envt\r\n"
						+ "			  INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "              LEFT JOIN tb_mstr_office c ON c.id = a.office  \n" + "  where a.sus_no= ? and s_state='1'     UNION ALL \n"
						+ "      SELECT\r\n"
						+ "              'peripherals' AS assets_type,\r\n"
						+ "              b.assets_name,\r\n"
						+ "              a.machine_no AS machine_number,\r\n"
						+ "              '' AS office,\r\n"
						+ "              '' AS os,\r\n"
						+ "              a.year_of_proc,\r\n"
						+ "              '' AS processor_type,\r\n"
						+ "              '' AS dply_env,\r\n"
						+ "			  orb.unit_name as unit_name\r\n"
						+ "      FROM\r\n"
						+ "              it_asset_peripherals a\r\n"
						+ "			  INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = a.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "              INNER JOIN tb_mstr_assets b ON a.assets_type = b.id AND b.category = 2\n"
						+ "			  and a.sus_no= ? and s_state='1' )a  " + SearchValue + ") app ";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
				stmt.setString(2, roleSusNo);

			}


			int flag = 2;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
			//System.err.println("\n\nIT held details count: \n"+stmt);
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
	public List<Map<String, Object>> Getcount_aAuth_data(String sus_no,String from_code_control,String formationtype){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(formationtype.equals("Brigade")) {
				q = "SELECT\n" + "              coalesce(sum(ue.total), 0::numeric) AS ue,\n"
						+ "              coalesce(sum(uh.total_uh), 0::numeric) AS total_uh\n" + "    FROM (\n"
						+ "                SELECT p.sus_no,\n" + "                              p.mct_main_id,\n"
						+ "                              m.prf_code,\n"
						+ "                              u.form_code_control\n" + "                    FROM (\n"
						+ "                                SELECT part.sus_no,\n"
						+ "                                              m_1.mct_main_id\n"
						+ "                                    FROM tb_tms_census_retrn part\n"
						+ "                                    JOIN tb_tms_banum_dirctry d\n"
						+ "                                        ON d.ba_no::text = part.ba_no::text\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u_1\n"
						+ "                                        ON u_1.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND part.sus_no::text = u_1.sus_no::text\n"
						+ "                                    JOIN tb_tms_mct_main_master m_1\n"
						+ "                                        ON m_1.mct_main_id = \"substring\"(d.mct::CHARACTER varying::text, 1, 4)\n"
						+ "                                      AND m_1.type_of_veh::text = 'A'::text\n"
						+ "                                  GROUP BY part.sus_no,\n"
						+ "                                                    m_1.mct_main_id,\n"
						+ "                                                    m_1.prf_code,\n"
						+ "                                                    u_1.form_code_control\n"
						+ "                          UNION ALL SELECT ue_1.sus_no,\n"
						+ "                                              ue_1.mct_no AS mct_main_id\n"
						+ "                                    FROM cue_transport_ue_final ue_1\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u_1\n"
						+ "                                        ON u_1.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND ue_1.sus_no = u_1.sus_no::text\n"
						+ "                                    JOIN tb_tms_mct_main_master m_1\n"
						+ "                                        ON m_1.mct_main_id = ue_1.mct_no::text\n"
						+ "                                      AND m_1.type_of_veh::text = 'A'::text\n"
						+ "                                  GROUP BY ue_1.sus_no,\n"
						+ "                                                    ue_1.mct_no,\n"
						+ "                                                    m_1.prf_code,\n"
						+ "                                                    u_1.form_code_control\n"
						+ "                                  ORDER BY 1,\n"
						+ "                                                    2\n" + "                              ) p\n"
						+ "                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                      AND p.sus_no::text = u.sus_no::text\n"
						+ "                    LEFT JOIN tb_tms_mct_main_master m\n"
						+ "                        ON m.mct_main_id = p.mct_main_id\n"
						+ "                  GROUP BY p.sus_no,\n" + "                                    p.mct_main_id,\n"
						+ "                                    m.prf_code,\n"
						+ "                                    u.form_code_control\n" + "              ) ms\n"
						+ "    LEFT JOIN (\n" + "                SELECT part.sus_no,\n"
						+ "                              m.mct_main_id,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'LOAN'::text AND part.ser_status::text = '0'::text) AS loan_s,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'LOAN'::text AND part.ser_status::text = '1'::text) AS loan_u,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'SECTOR'::text AND part.ser_status::text = '0'::text) AS sec_store_s,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'SECTOR'::text AND part.ser_status::text = '1'::text) AS sec_store_u,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'OP'::text AND part.ser_status::text = '0'::text) AS op_s,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'OP'::text AND part.ser_status::text = '1'::text) AS op_u,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'TRG'::text AND part.ser_status::text = '0'::text) AS trg_s,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'TRG'::text AND part.ser_status::text = '1'::text) AS trg_u,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'WWR'::text AND part.ser_status::text = '0'::text) AS wwr_s,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'WWR'::text AND part.ser_status::text = '1'::text) AS wwr_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.ser_status::text = '0'::text) AS total_uh_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.ser_status::text = '1'::text) AS total_uh_u,\n"
						+ "                              count(part.ba_no) AS total_uh\n"
						+ "                    FROM tb_tms_census_retrn part\n"
						+ "                    JOIN tb_tms_banum_dirctry d\n"
						+ "                        ON d.ba_no::text = part.ba_no::text\n"
						+ "                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                      AND part.sus_no::text = u.sus_no::text\n"
						+ "                    JOIN tb_tms_mct_main_master m\n"
						+ "                        ON m.mct_main_id = \"substring\"(d.mct::CHARACTER varying::text, 1, 4)\n"
						+ "                      AND m.type_of_veh::text = 'A'::text\n"
						+ "                  GROUP BY part.sus_no,\n"
						+ "                                    m.mct_main_id\n" + "                  ORDER BY part.sus_no\n"
						+ "              ) uh\n" + "        ON ms.sus_no::text = uh.sus_no::text\n"
						+ "      AND ms.mct_main_id = uh.mct_main_id\n" + "    LEFT JOIN (\n"
						+ "                SELECT ue_1.sus_no,\n" + "                              ue_1.mct_no,\n"
						+ "                              sum(ue_1.total) AS total\n"
						+ "                    FROM cue_transport_ue_final ue_1\n"
						+ "                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                      AND ue_1.sus_no = u.sus_no::text\n"
						+ "                    JOIN tb_tms_mct_main_master m\n"
						+ "                        ON m.mct_main_id = ue_1.mct_no::text\n"
						+ "                      AND m.type_of_veh::text = 'A'::text\n"
						+ "                  GROUP BY ue_1.sus_no,\n" + "                                    ue_1.mct_no\n"
						+ "                  ORDER BY ue_1.sus_no\n" + "              ) ue\n"
						+ "        ON ms.sus_no::text = ue.sus_no\n" + "      AND ms.mct_main_id = ue.mct_no::text\n"
						+ "    where ms.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') ";



				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
			}else{
				q = "SELECT\n" + "              coalesce(sum(ue.total), 0::numeric) AS ue,\n"
						+ "              coalesce(sum(uh.total_uh), 0::numeric) AS total_uh\n" + "    FROM (\n"
						+ "                SELECT p.sus_no,\n" + "                              p.mct_main_id,\n"
						+ "                              m.prf_code,\n"
						+ "                              u.form_code_control\n" + "                    FROM (\n"
						+ "                                SELECT part.sus_no,\n"
						+ "                                              m_1.mct_main_id\n"
						+ "                                    FROM tb_tms_census_retrn part\n"
						+ "                                    JOIN tb_tms_banum_dirctry d\n"
						+ "                                        ON d.ba_no::text = part.ba_no::text\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u_1\n"
						+ "                                        ON u_1.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND part.sus_no::text = u_1.sus_no::text\n"
						+ "                                    JOIN tb_tms_mct_main_master m_1\n"
						+ "                                        ON m_1.mct_main_id = \"substring\"(d.mct::CHARACTER varying::text, 1, 4)\n"
						+ "                                      AND m_1.type_of_veh::text = 'A'::text\n"
						+ "                                  GROUP BY part.sus_no,\n"
						+ "                                                    m_1.mct_main_id,\n"
						+ "                                                    m_1.prf_code,\n"
						+ "                                                    u_1.form_code_control\n"
						+ "                          UNION ALL SELECT ue_1.sus_no,\n"
						+ "                                              ue_1.mct_no AS mct_main_id\n"
						+ "                                    FROM cue_transport_ue_final ue_1\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u_1\n"
						+ "                                        ON u_1.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND ue_1.sus_no = u_1.sus_no::text\n"
						+ "                                    JOIN tb_tms_mct_main_master m_1\n"
						+ "                                        ON m_1.mct_main_id = ue_1.mct_no::text\n"
						+ "                                      AND m_1.type_of_veh::text = 'A'::text\n"
						+ "                                  GROUP BY ue_1.sus_no,\n"
						+ "                                                    ue_1.mct_no,\n"
						+ "                                                    m_1.prf_code,\n"
						+ "                                                    u_1.form_code_control\n"
						+ "                                  ORDER BY 1,\n"
						+ "                                                    2\n" + "                              ) p\n"
						+ "                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                      AND p.sus_no::text = u.sus_no::text\n"
						+ "                    LEFT JOIN tb_tms_mct_main_master m\n"
						+ "                        ON m.mct_main_id = p.mct_main_id\n"
						+ "                  GROUP BY p.sus_no,\n" + "                                    p.mct_main_id,\n"
						+ "                                    m.prf_code,\n"
						+ "                                    u.form_code_control\n" + "              ) ms\n"
						+ "    LEFT JOIN (\n" + "                SELECT part.sus_no,\n"
						+ "                              m.mct_main_id,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'LOAN'::text AND part.ser_status::text = '0'::text) AS loan_s,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'LOAN'::text AND part.ser_status::text = '1'::text) AS loan_u,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'SECTOR'::text AND part.ser_status::text = '0'::text) AS sec_store_s,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'SECTOR'::text AND part.ser_status::text = '1'::text) AS sec_store_u,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'OP'::text AND part.ser_status::text = '0'::text) AS op_s,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'OP'::text AND part.ser_status::text = '1'::text) AS op_u,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'TRG'::text AND part.ser_status::text = '0'::text) AS trg_s,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'TRG'::text AND part.ser_status::text = '1'::text) AS trg_u,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'WWR'::text AND part.ser_status::text = '0'::text) AS wwr_s,\n"
						+ "                              count(part.ba_no) filter (WHERE upper(part.issued_type::text) = 'WWR'::text AND part.ser_status::text = '1'::text) AS wwr_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.ser_status::text = '0'::text) AS total_uh_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.ser_status::text = '1'::text) AS total_uh_u,\n"
						+ "                              count(part.ba_no) AS total_uh\n"
						+ "                    FROM tb_tms_census_retrn part\n"
						+ "                    JOIN tb_tms_banum_dirctry d\n"
						+ "                        ON d.ba_no::text = part.ba_no::text\n"
						+ "                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                      AND part.sus_no::text = u.sus_no::text\n"
						+ "                    JOIN tb_tms_mct_main_master m\n"
						+ "                        ON m.mct_main_id = \"substring\"(d.mct::CHARACTER varying::text, 1, 4)\n"
						+ "                      AND m.type_of_veh::text = 'A'::text\n"
						+ "                  GROUP BY part.sus_no,\n"
						+ "                                    m.mct_main_id\n" + "                  ORDER BY part.sus_no\n"
						+ "              ) uh\n" + "        ON ms.sus_no::text = uh.sus_no::text\n"
						+ "      AND ms.mct_main_id = uh.mct_main_id\n" + "    LEFT JOIN (\n"
						+ "                SELECT ue_1.sus_no,\n" + "                              ue_1.mct_no,\n"
						+ "                              sum(ue_1.total) AS total\n"
						+ "                    FROM cue_transport_ue_final ue_1\n"
						+ "                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                      AND ue_1.sus_no = u.sus_no::text\n"
						+ "                    JOIN tb_tms_mct_main_master m\n"
						+ "                        ON m.mct_main_id = ue_1.mct_no::text\n"
						+ "                      AND m.type_of_veh::text = 'A'::text\n"
						+ "                  GROUP BY ue_1.sus_no,\n" + "                                    ue_1.mct_no\n"
						+ "                  ORDER BY ue_1.sus_no\n" + "              ) ue\n"
						+ "        ON ms.sus_no::text = ue.sus_no\n" + "      AND ms.mct_main_id = ue.mct_no::text\n"
						+ "    where ms.sus_no=? ";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);
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

	@Override
	public List<Map<String, Object>> Getcount_bAuth_data(String sus_no,String from_code_control,String formationtype) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(formationtype.equals("Brigade")) {

				q = " SELECT coalesce(sum(ue.total), 0::numeric) AS ue,\n"
						+ "              coalesce(sum(uh.total_uh), 0::numeric) AS total_uh\n" + "    FROM (\n"
						+ "                SELECT p.sus_no,\n" + "                              p.mct_main_id,\n"
						+ "                              m.prf_code,\n"
						+ "                              u.form_code_control\n" + "                    FROM (\n"
						+ "                                SELECT part.sus_no,\n"
						+ "                                              m_1.mct_main_id\n"
						+ "                                    FROM tb_tms_mvcr_parta_dtl part\n"
						+ "                                    JOIN tb_tms_banum_dirctry d\n"
						+ "                                        ON d.ba_no::text = part.ba_no::text\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u_1\n"
						+ "                                        ON u_1.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND part.sus_no::text = u_1.sus_no::text\n"
						+ "                                    JOIN tb_tms_mct_main_master m_1\n"
						+ "                                        ON m_1.mct_main_id = \"substring\"(d.mct::CHARACTER varying::text, 1, 4)\n"
						+ "                                      AND m_1.type_of_veh::text = 'B'::text\n"
						+ "                                  GROUP BY part.sus_no,\n"
						+ "                                                    m_1.mct_main_id,\n"
						+ "                                                    m_1.prf_code,\n"
						+ "                                                    u_1.form_code_control\n"
						+ "                          UNION ALL SELECT ue_1.sus_no,\n"
						+ "                                              ue_1.mct_no AS mct_main_id\n"
						+ "                                    FROM cue_transport_ue_final ue_1\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u_1\n"
						+ "                                        ON u_1.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND ue_1.sus_no = u_1.sus_no::text\n"
						+ "                                    JOIN tb_tms_mct_main_master m_1\n"
						+ "                                        ON m_1.mct_main_id = ue_1.mct_no::text\n"
						+ "                                      AND m_1.type_of_veh::text = 'B'::text\n"
						+ "                                  GROUP BY ue_1.sus_no,\n"
						+ "                                                    ue_1.mct_no,\n"
						+ "                                                    m_1.prf_code,\n"
						+ "                                                    u_1.form_code_control\n"
						+ "                                  ORDER BY 1,\n"
						+ "                                                    2\n" + "                              ) p\n"
						+ "                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                      AND p.sus_no::text = u.sus_no::text\n"
						+ "                    LEFT JOIN tb_tms_mct_main_master m\n"
						+ "                        ON m.mct_main_id = p.mct_main_id\n"
						+ "                  GROUP BY p.sus_no,\n" + "                                    p.mct_main_id,\n"
						+ "                                    m.prf_code,\n"
						+ "                                    u.form_code_control\n" + "              ) ms\n"
						+ "    LEFT JOIN (\n" + "                SELECT part.sus_no,\n"
						+ "                              m.mct_main_id,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '5'::text AND part.ser_status::text = '0'::text) AS against_ue_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '5'::text AND part.ser_status::text = '1'::text) AS against_ue_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '3'::text AND part.ser_status::text = '0'::text) AS over_ue_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '3'::text AND part.ser_status::text = '1'::text) AS over_ue_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '0'::text AND part.ser_status::text = '0'::text) AS loan_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '0'::text AND part.ser_status::text = '1'::text) AS loan_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '9'::text AND part.ser_status::text = '0'::text) AS sec_store_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '9'::text AND part.ser_status::text = '1'::text) AS sec_store_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '10'::text AND part.ser_status::text = '0'::text) AS acsfp_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '10'::text AND part.ser_status::text = '1'::text) AS acsfp_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '1'::text AND part.ser_status::text = '0'::text) AS pbd_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '1'::text AND part.ser_status::text = '1'::text) AS pbd_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = 'F'::text) AS fresh_release,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '4'::text AND part.ser_status::text = '0'::text) AS gift_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '4'::text AND part.ser_status::text = '1'::text) AS gift_u,\n"
						+ "                              0 AS op_s,\n" + "                              0 AS op_u,\n"
						+ "                              0 AS wwr_s,\n" + "                              0 AS wwr_u,\n"
						+ "                              0 AS trg_s,\n" + "                              0 AS trg_u,\n"
						+ "                              0 AS opwks_s,\n" + "                              0 AS opwks_u,\n"
						+ "                              0 AS other_s,\n" + "                              0 AS other_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.ser_status::text = '0'::text AND (part.issue_type::text = '5'::text OR part.issue_type::text = '3'::text OR part.issue_type::text = '0'::text OR part.issue_type::text = '9'::text OR part.issue_type::text = '10'::text OR part.issue_type::text = '1'::text OR part.issue_type::text = 'F'::text)) AS total_uh_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.ser_status::text = '1'::text AND (part.issue_type::text = '5'::text OR part.issue_type::text = '3'::text OR part.issue_type::text = '0'::text OR part.issue_type::text = '9'::text OR part.issue_type::text = '10'::text OR part.issue_type::text = '1'::text OR part.issue_type::text = 'F'::text)) AS total_uh_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '5'::text OR part.issue_type::text = '3'::text OR part.issue_type::text = '0'::text OR part.issue_type::text = '9'::text OR part.issue_type::text = '10'::text OR part.issue_type::text = '1'::text OR part.issue_type::text = 'F'::text) AS total_uh\n"
						+ "                    FROM tb_tms_mvcr_parta_dtl part\n"
						+ "                    JOIN tb_tms_banum_dirctry d\n"
						+ "                        ON d.ba_no::text = part.ba_no::text\n"
						+ "                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                      AND part.sus_no::text = u.sus_no::text\n"
						+ "                    JOIN tb_tms_mct_main_master m\n"
						+ "                        ON m.mct_main_id = \"substring\"(d.mct::CHARACTER varying::text, 1, 4)\n"
						+ "                      AND m.type_of_veh::text = 'B'::text\n"
						+ "                  GROUP BY part.sus_no,\n"
						+ "                                    m.mct_main_id\n" + "                  ORDER BY part.sus_no\n"
						+ "              ) uh\n" + "        ON ms.sus_no::text = uh.sus_no::text\n"
						+ "      AND ms.mct_main_id = uh.mct_main_id\n" + "    LEFT JOIN (\n"
						+ "                SELECT ue_1.sus_no,\n" + "                              ue_1.mct_no,\n"
						+ "                              sum(ue_1.total) AS total\n"
						+ "                    FROM cue_transport_ue_final ue_1\n"
						+ "                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                      AND ue_1.sus_no = u.sus_no::text\n"
						+ "                    JOIN tb_tms_mct_main_master m\n"
						+ "                        ON m.mct_main_id = ue_1.mct_no::text\n"
						+ "                      AND m.type_of_veh::text = 'B'::text\n"
						+ "                  GROUP BY ue_1.sus_no,\n" + "                                    ue_1.mct_no\n"
						+ "                  ORDER BY ue_1.sus_no\n" + "              ) ue\n"
						+ "        ON ms.sus_no::text = ue.sus_no\n" + "      AND ms.mct_main_id = ue.mct_no::text\n"
						+ "  WHERE ms.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') ";



				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
			}else{
				q = " SELECT coalesce(sum(ue.total), 0::numeric) AS ue,\n"
						+ "              coalesce(sum(uh.total_uh), 0::numeric) AS total_uh\n" + "    FROM (\n"
						+ "                SELECT p.sus_no,\n" + "                              p.mct_main_id,\n"
						+ "                              m.prf_code,\n"
						+ "                              u.form_code_control\n" + "                    FROM (\n"
						+ "                                SELECT part.sus_no,\n"
						+ "                                              m_1.mct_main_id\n"
						+ "                                    FROM tb_tms_mvcr_parta_dtl part\n"
						+ "                                    JOIN tb_tms_banum_dirctry d\n"
						+ "                                        ON d.ba_no::text = part.ba_no::text\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u_1\n"
						+ "                                        ON u_1.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND part.sus_no::text = u_1.sus_no::text\n"
						+ "                                    JOIN tb_tms_mct_main_master m_1\n"
						+ "                                        ON m_1.mct_main_id = \"substring\"(d.mct::CHARACTER varying::text, 1, 4)\n"
						+ "                                      AND m_1.type_of_veh::text = 'B'::text\n"
						+ "                                  GROUP BY part.sus_no,\n"
						+ "                                                    m_1.mct_main_id,\n"
						+ "                                                    m_1.prf_code,\n"
						+ "                                                    u_1.form_code_control\n"
						+ "                          UNION ALL SELECT ue_1.sus_no,\n"
						+ "                                              ue_1.mct_no AS mct_main_id\n"
						+ "                                    FROM cue_transport_ue_final ue_1\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u_1\n"
						+ "                                        ON u_1.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND ue_1.sus_no = u_1.sus_no::text\n"
						+ "                                    JOIN tb_tms_mct_main_master m_1\n"
						+ "                                        ON m_1.mct_main_id = ue_1.mct_no::text\n"
						+ "                                      AND m_1.type_of_veh::text = 'B'::text\n"
						+ "                                  GROUP BY ue_1.sus_no,\n"
						+ "                                                    ue_1.mct_no,\n"
						+ "                                                    m_1.prf_code,\n"
						+ "                                                    u_1.form_code_control\n"
						+ "                                  ORDER BY 1,\n"
						+ "                                                    2\n" + "                              ) p\n"
						+ "                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                      AND p.sus_no::text = u.sus_no::text\n"
						+ "                    LEFT JOIN tb_tms_mct_main_master m\n"
						+ "                        ON m.mct_main_id = p.mct_main_id\n"
						+ "                  GROUP BY p.sus_no,\n" + "                                    p.mct_main_id,\n"
						+ "                                    m.prf_code,\n"
						+ "                                    u.form_code_control\n" + "              ) ms\n"
						+ "    LEFT JOIN (\n" + "                SELECT part.sus_no,\n"
						+ "                              m.mct_main_id,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '5'::text AND part.ser_status::text = '0'::text) AS against_ue_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '5'::text AND part.ser_status::text = '1'::text) AS against_ue_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '3'::text AND part.ser_status::text = '0'::text) AS over_ue_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '3'::text AND part.ser_status::text = '1'::text) AS over_ue_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '0'::text AND part.ser_status::text = '0'::text) AS loan_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '0'::text AND part.ser_status::text = '1'::text) AS loan_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '9'::text AND part.ser_status::text = '0'::text) AS sec_store_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '9'::text AND part.ser_status::text = '1'::text) AS sec_store_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '10'::text AND part.ser_status::text = '0'::text) AS acsfp_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '10'::text AND part.ser_status::text = '1'::text) AS acsfp_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '1'::text AND part.ser_status::text = '0'::text) AS pbd_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '1'::text AND part.ser_status::text = '1'::text) AS pbd_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = 'F'::text) AS fresh_release,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '4'::text AND part.ser_status::text = '0'::text) AS gift_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '4'::text AND part.ser_status::text = '1'::text) AS gift_u,\n"
						+ "                              0 AS op_s,\n" + "                              0 AS op_u,\n"
						+ "                              0 AS wwr_s,\n" + "                              0 AS wwr_u,\n"
						+ "                              0 AS trg_s,\n" + "                              0 AS trg_u,\n"
						+ "                              0 AS opwks_s,\n" + "                              0 AS opwks_u,\n"
						+ "                              0 AS other_s,\n" + "                              0 AS other_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.ser_status::text = '0'::text AND (part.issue_type::text = '5'::text OR part.issue_type::text = '3'::text OR part.issue_type::text = '0'::text OR part.issue_type::text = '9'::text OR part.issue_type::text = '10'::text OR part.issue_type::text = '1'::text OR part.issue_type::text = 'F'::text)) AS total_uh_s,\n"
						+ "                              count(part.ba_no) filter (WHERE part.ser_status::text = '1'::text AND (part.issue_type::text = '5'::text OR part.issue_type::text = '3'::text OR part.issue_type::text = '0'::text OR part.issue_type::text = '9'::text OR part.issue_type::text = '10'::text OR part.issue_type::text = '1'::text OR part.issue_type::text = 'F'::text)) AS total_uh_u,\n"
						+ "                              count(part.ba_no) filter (WHERE part.issue_type::text = '5'::text OR part.issue_type::text = '3'::text OR part.issue_type::text = '0'::text OR part.issue_type::text = '9'::text OR part.issue_type::text = '10'::text OR part.issue_type::text = '1'::text OR part.issue_type::text = 'F'::text) AS total_uh\n"
						+ "                    FROM tb_tms_mvcr_parta_dtl part\n"
						+ "                    JOIN tb_tms_banum_dirctry d\n"
						+ "                        ON d.ba_no::text = part.ba_no::text\n"
						+ "                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                      AND part.sus_no::text = u.sus_no::text\n"
						+ "                    JOIN tb_tms_mct_main_master m\n"
						+ "                        ON m.mct_main_id = \"substring\"(d.mct::CHARACTER varying::text, 1, 4)\n"
						+ "                      AND m.type_of_veh::text = 'B'::text\n"
						+ "                  GROUP BY part.sus_no,\n"
						+ "                                    m.mct_main_id\n" + "                  ORDER BY part.sus_no\n"
						+ "              ) uh\n" + "        ON ms.sus_no::text = uh.sus_no::text\n"
						+ "      AND ms.mct_main_id = uh.mct_main_id\n" + "    LEFT JOIN (\n"
						+ "                SELECT ue_1.sus_no,\n" + "                              ue_1.mct_no,\n"
						+ "                              sum(ue_1.total) AS total\n"
						+ "                    FROM cue_transport_ue_final ue_1\n"
						+ "                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                      AND ue_1.sus_no = u.sus_no::text\n"
						+ "                    JOIN tb_tms_mct_main_master m\n"
						+ "                        ON m.mct_main_id = ue_1.mct_no::text\n"
						+ "                      AND m.type_of_veh::text = 'B'::text\n"
						+ "                  GROUP BY ue_1.sus_no,\n" + "                                    ue_1.mct_no\n"
						+ "                  ORDER BY ue_1.sus_no\n" + "              ) ue\n"
						+ "        ON ms.sus_no::text = ue.sus_no\n" + "      AND ms.mct_main_id = ue.mct_no::text\n"
						+ "  WHERE ms.sus_no = ? ";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);
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

	@Override
	public List<Map<String, Object>> Getcount_cAuth_data(String sus_no,String from_code_control,String formationtype){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(formationtype.equals("Brigade")) {
				q = "  SELECT coalesce(sum(ue.total), 0::numeric) AS ue,\n"
						+ "                              coalesce(sum(uh.total_uh), 0::numeric) AS total_uh\n"
						+ "                    FROM (\n" + "                                SELECT p.sus_no,\n"
						+ "                                              p.mct_main_id,\n"
						+ "                                              m.prf_code,\n"
						+ "                                              u.form_code_control\n"
						+ "                                    FROM (\n"
						+ "                                                SELECT part.sus_no,\n"
						+ "                                                              m_1.mct_main_id\n"
						+ "                                                    FROM tb_tms_emar_report part\n"
						+ "                                                    JOIN tb_tms_banum_dirctry d\n"
						+ "                                                        ON d.ba_no::text = part.em_no::text\n"
						+ "                                                    JOIN tb_miso_orbat_unt_dtl u_1\n"
						+ "                                                        ON u_1.status_sus_no::text = 'Active'::text\n"
						+ "                                                      AND part.sus_no::text = u_1.sus_no::text\n"
						+ "                                                    JOIN tb_tms_mct_main_master m_1\n"
						+ "                                                        ON m_1.mct_main_id = \"substring\"(d.mct::CHARACTER varying::text, 1, 4)\n"
						+ "                                                      AND m_1.type_of_veh::text = 'C'::text\n"
						+ "                                                  GROUP BY part.sus_no,\n"
						+ "                                                                    m_1.mct_main_id,\n"
						+ "                                                                    m_1.prf_code,\n"
						+ "                                                                    u_1.form_code_control\n"
						+ "                                          UNION ALL SELECT ue_1.sus_no,\n"
						+ "                                                              ue_1.mct_no AS mct_main_id\n"
						+ "                                                    FROM cue_transport_ue_final ue_1\n"
						+ "                                                    JOIN tb_miso_orbat_unt_dtl u_1\n"
						+ "                                                        ON u_1.status_sus_no::text = 'Active'::text\n"
						+ "                                                      AND ue_1.sus_no = u_1.sus_no::text\n"
						+ "                                                    JOIN tb_tms_mct_main_master m_1\n"
						+ "                                                        ON m_1.mct_main_id = ue_1.mct_no::text\n"
						+ "                                                      AND m_1.type_of_veh::text = 'C'::text\n"
						+ "                                                  GROUP BY ue_1.sus_no,\n"
						+ "                                                                    ue_1.mct_no,\n"
						+ "                                                                    m_1.prf_code,\n"
						+ "                                                                    u_1.form_code_control\n"
						+ "                                                  ORDER BY 1,\n"
						+ "                                                                    2\n"
						+ "                                              ) p\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND p.sus_no::text = u.sus_no::text\n"
						+ "                                    LEFT JOIN tb_tms_mct_main_master m\n"
						+ "                                        ON m.mct_main_id = p.mct_main_id\n"
						+ "                                  GROUP BY p.sus_no,\n"
						+ "                                                    p.mct_main_id,\n"
						+ "                                                    m.prf_code,\n"
						+ "                                                    u.form_code_control\n"
						+ "                              ) ms\n" + "                    LEFT JOIN (\n"
						+ "                                SELECT part.sus_no,\n"
						+ "                                              m.mct_main_id,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'WE'::text AND part.seviceable::text = 'No'::text) AS against_ue_s,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'WE'::text AND part.seviceable::text = 'Yes'::text) AS against_ue_u,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'ACSFP'::text AND part.seviceable::text = 'No'::text) AS acsfp_s,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'ACSFP'::text AND part.seviceable::text = 'Yes'::text) AS acsfp_u,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'OPWKS'::text AND part.seviceable::text = 'No'::text) AS opwks_s,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'OPWKS'::text AND part.seviceable::text = 'Yes'::text) AS opwks_u,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'OTHERS'::text AND part.seviceable::text = 'No'::text) AS other_s,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'OTHERS'::text AND part.seviceable::text = 'Yes'::text) AS other_u,\n"
						+ "                                              count(part.em_no) filter (WHERE part.seviceable::text = 'No'::text) AS total_uh_s,\n"
						+ "                                              count(part.em_no) filter (WHERE part.seviceable::text = 'Yes'::text) AS total_uh_u,\n"
						+ "                                              count(part.em_no) AS total_uh\n"
						+ "                                    FROM tb_tms_emar_report part\n"
						+ "                                    JOIN tb_tms_banum_dirctry d\n"
						+ "                                        ON d.ba_no::text = part.em_no::text\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND part.sus_no::text = u.sus_no::text\n"
						+ "                                    JOIN tb_tms_mct_main_master m\n"
						+ "                                        ON m.mct_main_id = \"substring\"(d.mct::CHARACTER varying::text, 1, 4)\n"
						+ "                                      AND m.type_of_veh::text = 'C'::text\n"
						+ "                                  GROUP BY part.sus_no,\n"
						+ "                                                    m.mct_main_id\n"
						+ "                                  ORDER BY part.sus_no\n"
						+ "                              ) uh\n"
						+ "                        ON ms.sus_no::text = uh.sus_no::text\n"
						+ "                      AND ms.mct_main_id = uh.mct_main_id\n"
						+ "                    LEFT JOIN (\n" + "                                SELECT ue_1.sus_no,\n"
						+ "                                              ue_1.mct_no,\n"
						+ "                                              sum(ue_1.total) AS total\n"
						+ "                                    FROM cue_transport_ue_final ue_1\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND ue_1.sus_no = u.sus_no::text\n"
						+ "                                    JOIN tb_tms_mct_main_master m\n"
						+ "                                        ON m.mct_main_id = ue_1.mct_no::text\n"
						+ "                                      AND m.type_of_veh::text = 'C'::text\n"
						+ "                                  GROUP BY ue_1.sus_no,\n"
						+ "                                                    ue_1.mct_no\n"
						+ "                                  ORDER BY ue_1.sus_no\n"
						+ "                              ) ue\n"
						+ "                        ON ms.sus_no::text = ue.sus_no\n"
						+ "                      AND ms.mct_main_id = ue.mct_no::text\n"
						+ "                  WHERE ms.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + " ";



				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
			}else{
				q = "  SELECT coalesce(sum(ue.total), 0::numeric) AS ue,\n"
						+ "                              coalesce(sum(uh.total_uh), 0::numeric) AS total_uh\n"
						+ "                    FROM (\n" + "                                SELECT p.sus_no,\n"
						+ "                                              p.mct_main_id,\n"
						+ "                                              m.prf_code,\n"
						+ "                                              u.form_code_control\n"
						+ "                                    FROM (\n"
						+ "                                                SELECT part.sus_no,\n"
						+ "                                                              m_1.mct_main_id\n"
						+ "                                                    FROM tb_tms_emar_report part\n"
						+ "                                                    JOIN tb_tms_banum_dirctry d\n"
						+ "                                                        ON d.ba_no::text = part.em_no::text\n"
						+ "                                                    JOIN tb_miso_orbat_unt_dtl u_1\n"
						+ "                                                        ON u_1.status_sus_no::text = 'Active'::text\n"
						+ "                                                      AND part.sus_no::text = u_1.sus_no::text\n"
						+ "                                                    JOIN tb_tms_mct_main_master m_1\n"
						+ "                                                        ON m_1.mct_main_id = \"substring\"(d.mct::CHARACTER varying::text, 1, 4)\n"
						+ "                                                      AND m_1.type_of_veh::text = 'C'::text\n"
						+ "                                                  GROUP BY part.sus_no,\n"
						+ "                                                                    m_1.mct_main_id,\n"
						+ "                                                                    m_1.prf_code,\n"
						+ "                                                                    u_1.form_code_control\n"
						+ "                                          UNION ALL SELECT ue_1.sus_no,\n"
						+ "                                                              ue_1.mct_no AS mct_main_id\n"
						+ "                                                    FROM cue_transport_ue_final ue_1\n"
						+ "                                                    JOIN tb_miso_orbat_unt_dtl u_1\n"
						+ "                                                        ON u_1.status_sus_no::text = 'Active'::text\n"
						+ "                                                      AND ue_1.sus_no = u_1.sus_no::text\n"
						+ "                                                    JOIN tb_tms_mct_main_master m_1\n"
						+ "                                                        ON m_1.mct_main_id = ue_1.mct_no::text\n"
						+ "                                                      AND m_1.type_of_veh::text = 'C'::text\n"
						+ "                                                  GROUP BY ue_1.sus_no,\n"
						+ "                                                                    ue_1.mct_no,\n"
						+ "                                                                    m_1.prf_code,\n"
						+ "                                                                    u_1.form_code_control\n"
						+ "                                                  ORDER BY 1,\n"
						+ "                                                                    2\n"
						+ "                                              ) p\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND p.sus_no::text = u.sus_no::text\n"
						+ "                                    LEFT JOIN tb_tms_mct_main_master m\n"
						+ "                                        ON m.mct_main_id = p.mct_main_id\n"
						+ "                                  GROUP BY p.sus_no,\n"
						+ "                                                    p.mct_main_id,\n"
						+ "                                                    m.prf_code,\n"
						+ "                                                    u.form_code_control\n"
						+ "                              ) ms\n" + "                    LEFT JOIN (\n"
						+ "                                SELECT part.sus_no,\n"
						+ "                                              m.mct_main_id,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'WE'::text AND part.seviceable::text = 'No'::text) AS against_ue_s,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'WE'::text AND part.seviceable::text = 'Yes'::text) AS against_ue_u,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'ACSFP'::text AND part.seviceable::text = 'No'::text) AS acsfp_s,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'ACSFP'::text AND part.seviceable::text = 'Yes'::text) AS acsfp_u,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'OPWKS'::text AND part.seviceable::text = 'No'::text) AS opwks_s,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'OPWKS'::text AND part.seviceable::text = 'Yes'::text) AS opwks_u,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'OTHERS'::text AND part.seviceable::text = 'No'::text) AS other_s,\n"
						+ "                                              count(part.em_no) filter (WHERE upper(part.proc_from::text) = 'OTHERS'::text AND part.seviceable::text = 'Yes'::text) AS other_u,\n"
						+ "                                              count(part.em_no) filter (WHERE part.seviceable::text = 'No'::text) AS total_uh_s,\n"
						+ "                                              count(part.em_no) filter (WHERE part.seviceable::text = 'Yes'::text) AS total_uh_u,\n"
						+ "                                              count(part.em_no) AS total_uh\n"
						+ "                                    FROM tb_tms_emar_report part\n"
						+ "                                    JOIN tb_tms_banum_dirctry d\n"
						+ "                                        ON d.ba_no::text = part.em_no::text\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND part.sus_no::text = u.sus_no::text\n"
						+ "                                    JOIN tb_tms_mct_main_master m\n"
						+ "                                        ON m.mct_main_id = \"substring\"(d.mct::CHARACTER varying::text, 1, 4)\n"
						+ "                                      AND m.type_of_veh::text = 'C'::text\n"
						+ "                                  GROUP BY part.sus_no,\n"
						+ "                                                    m.mct_main_id\n"
						+ "                                  ORDER BY part.sus_no\n"
						+ "                              ) uh\n"
						+ "                        ON ms.sus_no::text = uh.sus_no::text\n"
						+ "                      AND ms.mct_main_id = uh.mct_main_id\n"
						+ "                    LEFT JOIN (\n" + "                                SELECT ue_1.sus_no,\n"
						+ "                                              ue_1.mct_no,\n"
						+ "                                              sum(ue_1.total) AS total\n"
						+ "                                    FROM cue_transport_ue_final ue_1\n"
						+ "                                    JOIN tb_miso_orbat_unt_dtl u\n"
						+ "                                        ON u.status_sus_no::text = 'Active'::text\n"
						+ "                                      AND ue_1.sus_no = u.sus_no::text\n"
						+ "                                    JOIN tb_tms_mct_main_master m\n"
						+ "                                        ON m.mct_main_id = ue_1.mct_no::text\n"
						+ "                                      AND m.type_of_veh::text = 'C'::text\n"
						+ "                                  GROUP BY ue_1.sus_no,\n"
						+ "                                                    ue_1.mct_no\n"
						+ "                                  ORDER BY ue_1.sus_no\n"
						+ "                              ) ue\n"
						+ "                        ON ms.sus_no::text = ue.sus_no\n"
						+ "                      AND ms.mct_main_id = ue.mct_no::text\n"
						+ "                  WHERE ms.sus_no = ?\n" + " ";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);
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

	// mtrls

	@Override
	public List<Map<String, Object>> Getcount_equi_held(String sus_no,String from_code_control,String formationtype){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if(formationtype.equals("Brigade")) {
				q = " select\r\n"
						+ " round(coalesce(sum(r.ue), 0::numeric)::decimal,0) AS ue,\r\n"
						+ " coalesce(sum(r.uh), 0::numeric) AS uh\r\n"
						+ "from (\r\n"
						+ " SELECT \r\n"
						+ "    count(a.regn_seq_no) AS uh  ,\r\n"
						+ "		 0 AS ue\r\n"
						+ "   FROM mms_tb_regn_mstr_detl a\r\n"
						+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
						+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
						+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
						+ "    ON m.item_code::text = i.item_code::text\r\n"
						+ "		where  a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \r\n"
						+ "     and LEFT(i.cos_sec_no, 1) NOT in ('B','M','N','O','C','G') \r\n"
						+ "\r\n"
						+ "		union all\r\n"
						+ "		\r\n"
						+ "      SELECT \r\n"
						+ "           0 as uh,\r\n"
						+ "        sum(total_ue_qty) as ue                                         \r\n"
						+ "                  FROM mms_ue_mview a \r\n"
						+ "									 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
						+ "    ON a.item_code::text = i.item_code::text\r\n"
						+ "										where  a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\r\n"
						+ "									and LEFT(i.cos_sec_no, 1) NOT in ('B','M','N','O','C','G') ) r  ";


				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
				stmt.setString(2, from_code_control);
			}else{

				q = " select\r\n"
						+ " round(coalesce(sum(r.ue), 0::numeric)::decimal,0) AS ue,\r\n"
						+ " coalesce(sum(r.uh), 0::numeric) AS uh\r\n"
						+ "from (\r\n"
						+ " SELECT \r\n"
						+ "    count(a.regn_seq_no) AS uh  ,\r\n"
						+ "		 0 AS ue\r\n"
						+ "   FROM mms_tb_regn_mstr_detl a\r\n"
						+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
						+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
						+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
						+ "    ON m.item_code::text = i.item_code::text\r\n"
						+ "		where  a.sus_no =? \r\n"
						+ "     and  LEFT(i.cos_sec_no, 1) NOT in ('B','M','N','O','C','G') \r\n"
						+ "\r\n"
						+ "		union all\r\n"
						+ "		\r\n"
						+ "      SELECT \r\n"
						+ "           0 as uh,\r\n"
						+ "        sum(total_ue_qty) as ue                                         \r\n"
						+ "                  FROM mms_ue_mview a \r\n"
						+ "									 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
						+ "    ON a.item_code::text = i.item_code::text\r\n"
						+ "										where  a.sus_no =? \r\n"
						+ "										and LEFT(i.cos_sec_no, 1) NOT in ('B','M','N','O','C','G') ) r  ";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);
				stmt.setString(2, sus_no);
			}

			//System.err.println("count of dash eqp \n" +stmt);
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
	public List<Map<String, Object>> Getcount_wpns_held(String sus_no,String from_code_control,String formationtype) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if(formationtype.equals("Brigade")) {
				q = " select\r\n"
						+ " round(coalesce(sum(r.ue), 0::numeric)::decimal,0) AS ue,\r\n"
						+ " coalesce(sum(r.uh), 0::numeric) AS uh\r\n"
						+ "from (\r\n"
						+ " SELECT \r\n"
						+ "    count(a.regn_seq_no) AS uh  ,\r\n"
						+ "		 0 AS ue\r\n"
						+ "   FROM mms_tb_regn_mstr_detl a\r\n"
						+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
						+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
						+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
						+ "    ON m.item_code::text = i.item_code::text\r\n"
						+ "		where  a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \r\n"
						+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C') \r\n"
						+ "\r\n"
						+ "		union all\r\n"
						+ "		\r\n"
						+ "      SELECT \r\n"
						+ "           0 as uh,\r\n"
						+ "        sum(total_ue_qty) as ue                                         \r\n"
						+ "                  FROM mms_ue_mview a \r\n"
						+ "									 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
						+ "    ON a.item_code::text = i.item_code::text\r\n"
						+ "										where  a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\r\n"
						+ "										and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C') ) r  ";


				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);
				stmt.setString(2, from_code_control);
			}else{
				q = " select\r\n"
						+ " round(coalesce(sum(r.ue), 0::numeric)::decimal,0) AS ue,\r\n"
						+ " coalesce(sum(r.uh), 0::numeric) AS uh\r\n"
						+ "from (\r\n"
						+ " SELECT \r\n"
						+ "    count(a.regn_seq_no) AS uh  ,\r\n"
						+ "		 0 AS ue\r\n"
						+ "   FROM mms_tb_regn_mstr_detl a\r\n"
						+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
						+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
						+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
						+ "    ON m.item_code::text = i.item_code::text\r\n"
						+ "		where  a.sus_no =? \r\n"
						+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C') \r\n"
						+ "\r\n"
						+ "		union all\r\n"
						+ "		\r\n"
						+ "      SELECT \r\n"
						+ "           0 as uh,\r\n"
						+ "        sum(total_ue_qty) as ue                                         \r\n"
						+ "                  FROM mms_ue_mview a \r\n"
						+ "									 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
						+ "    ON a.item_code::text = i.item_code::text\r\n"
						+ "										where  a.sus_no =? \r\n"
						+ "										and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C') ) r  ";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);
				stmt.setString(2, sus_no);
			}

			//System.err.println("count of dash wpn \n" +stmt);
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

	// CIVS
	@Override
	public List<Map<String, Object>> getUnitReportPersCivs(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String roleSusNo, String Access_by_susno) {
		String SearchValue = GenerateQueryWhereClause_SQLcivs(search);
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


			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade") && Access_by_susno != "Access") {
				String from_code_control= formation_code(roleSusNo);

				q = "SELECT c.employee_no,\r\n"
						+ "       c.first_name || ' ' || c.middle_name || ' ' || c.last_name AS emp_name,\r\n"
						+ "       ltrim(to_char(c.dob,'DD-MON-YYYY'),'0') AS dob,\r\n"
						+ "       t.label AS classification_services,\r\n"
						+ "       c.civ_group,\r\n"
						+ "       t1.label AS civ_type,\r\n"
						+ "       b.description as designation,\r\n"
						+ "       ltrim(to_char(c.joining_date_gov_service,'DD-Mon-YYYY'),'0') AS joining_date_gov_service,\r\n"
						+ "       ltrim(to_char(c.designation_date,'DD-Mon-YYYY'),'0') AS designation_date,\r\n"
						+ "       g.gender_name, orb.unit_name as unit_name\r\n"
						+ "  FROM tb_psg_civilian_dtl c\r\n"
						+ " INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "    ON orb.sus_no = c.sus_no\r\n"
						+ "   AND orb.status_sus_no = 'Active'\r\n"
						+ " INNER JOIN t_domain_value t1\r\n"
						+ "    ON t1.codevalue = c.civ_type\r\n"
						+ "   AND t1.domainid = 'CIVILIAN_TYPE'\r\n"
						+ " INNER JOIN tb_psg_mstr_gender g\r\n"
						+ "    ON g.id = c.gender\r\n"
						+ " INNER JOIN t_domain_value t\r\n"
						+ "    ON t.codevalue = c.classification_services\r\n"
						+ "   AND t.domainid = 'CLASSIFICATION_OF_SERVICES'\r\n"
						+ "    INNER JOIN cue_tb_psg_rank_app_master b\r\n"
						+ "    ON c.designation = b.id\r\n"
						+ "   AND upper(b.level_in_hierarchy) = upper('Appointment')\r\n"
						+ "   AND b.parent_code IN ('4','5','6','7','8','9','10','11')\r\n"
						+ "   AND b.status_active = 'Active'\r\n"
						+ " WHERE c.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')   AND c.status = 1   AND civilian_status = 'R' \n"
						+ SearchValue + " \n" + "   limit " + pageL + " OFFSET " + startPage + "";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);


			}else{

				q = "SELECT c.employee_no,\r\n"
						+ "       c.first_name || ' ' || c.middle_name || ' ' || c.last_name AS emp_name,\r\n"
						+ "       ltrim(to_char(c.dob,'DD-MON-YYYY'),'0') AS dob,\r\n"
						+ "       t.label AS classification_services,\r\n"
						+ "       c.civ_group,\r\n"
						+ "       t1.label AS civ_type,\r\n"
						+ "       b.description as designation,\r\n"
						+ "       ltrim(to_char(c.joining_date_gov_service,'DD-Mon-YYYY'),'0') AS joining_date_gov_service,\r\n"
						+ "       ltrim(to_char(c.designation_date,'DD-Mon-YYYY'),'0') AS designation_date,\r\n"
						+ "       g.gender_name, orb.unit_name as unit_name\r\n"
						+ "  FROM tb_psg_civilian_dtl c\r\n"
						+ " INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "    ON orb.sus_no = c.sus_no\r\n"
						+ "   AND orb.status_sus_no = 'Active'\r\n"
						+ " INNER JOIN t_domain_value t1\r\n"
						+ "    ON t1.codevalue = c.civ_type\r\n"
						+ "   AND t1.domainid = 'CIVILIAN_TYPE'\r\n"
						+ " INNER JOIN tb_psg_mstr_gender g\r\n"
						+ "    ON g.id = c.gender\r\n"
						+ " INNER JOIN t_domain_value t\r\n"
						+ "    ON t.codevalue = c.classification_services\r\n"
						+ "   AND t.domainid = 'CLASSIFICATION_OF_SERVICES'\r\n"
						+ "    INNER JOIN cue_tb_psg_rank_app_master b\r\n"
						+ "    ON c.designation = b.id\r\n"
						+ "   AND upper(b.level_in_hierarchy) = upper('Appointment')\r\n"
						+ "   AND b.parent_code IN ('4','5','6','7','8','9','10','11')\r\n"
						+ "   AND b.status_active = 'Active'\n"
						+ " WHERE c.sus_no = ?   AND c.status = 1   AND civilian_status = 'R' \n"
						+ SearchValue + " \n" + "   limit " + pageL + " OFFSET " + startPage + "";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}



			stmt = setQueryWhereClause_SQLcivs(stmt, search);

			ResultSet rs = stmt.executeQuery();
			System.out.println("QUERY CIV --> " + stmt);
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

	public String GenerateQueryWhereClause_SQLcivs(String search) {

		String SearchValue = "";
		if (!search.equals("")) { // for Input Filter
			SearchValue = " and     ";

			SearchValue += "(upper(c.employee_no) like ? or upper(b.description) like ?  or upper(c.first_name) like ? or upper(c.middle_name ) like ? or upper(c.last_name) like ? or upper(t1.label) like ? or upper(t.label) like ? or upper(c.civ_group) like ?"
					+ " or     upper(ltrim(to_char(c.dob,'DD-Mon-YYYY'),'0')) like ? or upper(ltrim(to_char(c.designation_date,'DD-Mon-YYYY'),'0')) like ?"
					+ "or     upper(ltrim(to_char(c.joining_date_gov_service,'DD-Mon-YYYY'),'0')) like ? or upper(orb.unit_name) like ?)";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQLcivs(PreparedStatement stmt, String search) {
		int flag = 1;
		try {
			if (!search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  search.toUpperCase() + "%");
			}
		} catch (Exception e) {
		}

		return stmt;

	}


	@Override
	public long getUnitReportPersCivscount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo, String Access_by_susno) {
		String SearchValue = GenerateQueryWhereClause_SQLcivs(Search);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement stmt=null;
			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade") && Access_by_susno != "Access") {
				String from_code_control= formation_code(roleSusNo);
				q = "select count(app.*) from (SELECT c.employee_no,\r\n"
						+ "       c.first_name || ' ' || c.middle_name || ' ' || c.last_name AS emp_name,\r\n"
						+ "       ltrim(to_char(c.dob,'DD-MON-YYYY'),'0') AS dob,\r\n"
						+ "       t.label AS classification_services,\r\n"
						+ "       c.civ_group,\r\n"
						+ "       t1.label AS civ_type,\r\n"
						+ "       b.description as designation,\r\n"
						+ "       ltrim(to_char(c.joining_date_gov_service,'DD-Mon-YYYY'),'0') AS joining_date_gov_service,\r\n"
						+ "       ltrim(to_char(c.designation_date,'DD-Mon-YYYY'),'0') AS designation_date,\r\n"
						+ "       g.gender_name, orb.unit_name as unit_name\r\n"
						+ "  FROM tb_psg_civilian_dtl c\r\n"
						+ " INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "    ON orb.sus_no = c.sus_no\r\n"
						+ "   AND orb.status_sus_no = 'Active'\r\n"
						+ " INNER JOIN t_domain_value t1\r\n"
						+ "    ON t1.codevalue = c.civ_type\r\n"
						+ "   AND t1.domainid = 'CIVILIAN_TYPE'\r\n"
						+ " INNER JOIN tb_psg_mstr_gender g\r\n"
						+ "    ON g.id = c.gender\r\n"
						+ " INNER JOIN t_domain_value t\r\n"
						+ "    ON t.codevalue = c.classification_services\r\n"
						+ "   AND t.domainid = 'CLASSIFICATION_OF_SERVICES'\r\n"
						+ "    INNER JOIN cue_tb_psg_rank_app_master b\r\n"
						+ "    ON c.designation = b.id\r\n"
						+ "   AND upper(b.level_in_hierarchy) = upper('Appointment')\r\n"
						+ "   AND b.parent_code IN ('4','5','6','7','8','9','10','11')\r\n"
						+ "   AND b.status_active = 'Active'\r\n"
						+ " WHERE c.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ "   AND c.status = 1\n"
						+ "   AND civilian_status = 'R' "+SearchValue+") app";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);



			}else{

				q = "select count(app.*) from (SELECT c.employee_no,\r\n"
						+ "       c.first_name || ' ' || c.middle_name || ' ' || c.last_name AS emp_name,\r\n"
						+ "       ltrim(to_char(c.dob,'DD-MON-YYYY'),'0') AS dob,\r\n"
						+ "       t.label AS classification_services,\r\n"
						+ "       c.civ_group,\r\n"
						+ "       t1.label AS civ_type,\r\n"
						+ "       b.description as designation,\r\n"
						+ "       ltrim(to_char(c.joining_date_gov_service,'DD-Mon-YYYY'),'0') AS joining_date_gov_service,\r\n"
						+ "       ltrim(to_char(c.designation_date,'DD-Mon-YYYY'),'0') AS designation_date,\r\n"
						+ "       g.gender_name, orb.unit_name as unit_name\r\n"
						+ "  FROM tb_psg_civilian_dtl c\r\n"
						+ " INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "    ON orb.sus_no = c.sus_no\r\n"
						+ "   AND orb.status_sus_no = 'Active'\r\n"
						+ " INNER JOIN t_domain_value t1\r\n"
						+ "    ON t1.codevalue = c.civ_type\r\n"
						+ "   AND t1.domainid = 'CIVILIAN_TYPE'\r\n"
						+ " INNER JOIN tb_psg_mstr_gender g\r\n"
						+ "    ON g.id = c.gender\r\n"
						+ " INNER JOIN t_domain_value t\r\n"
						+ "    ON t.codevalue = c.classification_services\r\n"
						+ "   AND t.domainid = 'CLASSIFICATION_OF_SERVICES'\r\n"
						+ "    INNER JOIN cue_tb_psg_rank_app_master b\r\n"
						+ "    ON c.designation = b.id\r\n"
						+ "   AND upper(b.level_in_hierarchy) = upper('Appointment')\r\n"
						+ "   AND b.parent_code IN ('4','5','6','7','8','9','10','11')\r\n"
						+ "   AND b.status_active = 'Active'\n"
						+ " WHERE c.sus_no = ?\n"
						+ "   AND c.status = 1\n"
						+ "   AND civilian_status = 'R' "+SearchValue+") app";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}


			stmt = setQueryWhereClause_SQLcivs(stmt, Search);

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
	// OR
	@Override
	public List<Map<String, Object>> getUnitReportPersOR(int startPage, int pageLength, String search,
			String orderColunm, String orderType,String rank,String name,String trade,String armyNo, HttpSession sessionUserId, String roleSusNo, String Access_by_susno) {
		// String SearchValue = GenerateQueryWhereClause_SQLPERS(search, appt_trade,
		// rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
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
			String SearchValue = "";
			String qry = "";
			if (name != "" && name != null) {
				qry += " and  upper(c.full_name) like ?";
			}
			if (rank != "" && rank != null) {
				qry += " and c.rank = ?";
			}

			if (trade != "" && trade != null) {
				qry += " and t.trade = ?";
			}
			if (armyNo != "" && armyNo != null) {
				qry += " and c.army_no = ?";
			}


			if (!search.equals("")) { // for Input Filter
				SearchValue = " and     ";

				SearchValue += "(upper(c.army_no) like ?   "
						+ "  or upper(r.rank ) like ? or upper(c.full_name) like ? or upper(t.trade) like ?"
						+ " or     upper(ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0')) like ? "
						+ " or     upper(ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0')) like ? "
						+ " or     upper(ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0')) like ? "
						+ " or     upper(ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0')) like ? "
						+ "  or upper(cour.course_name) like ? or upper(ma.maiden_name) like ?"
						+ "or     upper(ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0')) like ?"
						+ "or upper(orb.unit_name) like ?)";
			}

			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade") && Access_by_susno != "Access") {
				String from_code_control= formation_code(roleSusNo);
				q = "SELECT c.army_no,\r\n"
						+ "             r.rank AS rk,\r\n"
						+ "             c.full_name AS name,\r\n"
						+ "             t.trade AS trade,         \r\n"
						+ "               ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,     \r\n"
						+ "               ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,   \r\n"
						+ "               ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0') AS doe,\r\n"
						+ "                 ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\r\n"
						+ "             'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\r\n"
						+ "            'C_E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') || 'C_O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || 'C_C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || 'C_P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') AS cope,\r\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY')) AS dom,\r\n"
						+ "             string_agg(distinct cour.course_name, ', ') army_courses,\r\n"
						+ "             ma.maiden_name AS spousename,\r\n"
						+ "			 orb.unit_name as unit_name\r\n"
						+ "   FROM tb_psg_census_jco_or_p c\r\n"
						+ " INNER JOIN tb_psg_mstr_rank_jco r\r\n"
						+ "       ON c.rank = r.id\r\n"
						+ "     AND c.status = '1'\r\n"
						+ " INNER JOIN tb_psg_mstr_trade_jco t\r\n"
						+ "       ON t.id = c.trade\r\n"
						+ "INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "	ON c.unit_sus_no = orb.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "   LEFT JOIN tb_miso_orbat_arm_code e\r\n"
						+ "       ON e.arm_code = c.arm_service\r\n"
						+ "   LEFT JOIN tb_psg_medical_category_jco med_main\r\n"
						+ "       ON med_main.jco_id = c.id\r\n"
						+ "     AND med_main.status IN ('1','2')\r\n"
						+ "   LEFT JOIN tb_psg_census_army_course_jco cour\r\n"
						+ "       ON cour.jco_id = c.id\r\n"
						+ "   LEFT JOIN tb_psg_census_family_married_jco ma\r\n"
						+ "       ON ma.jco_id = c.id\r\n"
						+ " WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ "    AND c.category='OR'  "
						+ qry + " \n" + SearchValue + " GROUP BY 1,2,3,4, 5, 6,7,8,\n" + "                   ma.marriage_date,\n"
						+ "                   ma.maiden_name,orb.unit_name\n" + "   limit " + pageL + " OFFSET " + startPage + "";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);



			}else{

				q = "SELECT c.army_no,\r\n"
						+ "             r.rank AS rk,\r\n"
						+ "             c.full_name AS name,\r\n"
						+ "             t.trade AS trade,         \r\n"
						+ "               ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,     \r\n"
						+ "               ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,   \r\n"
						+ "               ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0') AS doe,\r\n"
						+ "                 ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\r\n"
						+ "             'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\r\n"
						+ "            'C_E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') || 'C_O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || 'C_C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || 'C_P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') AS cope,\r\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY')) AS dom,\r\n"
						+ "             string_agg(distinct cour.course_name, ', ') army_courses,\r\n"
						+ "             ma.maiden_name AS spousename,\r\n"
						+ "			 orb.unit_name as unit_name\r\n"
						+ "   FROM tb_psg_census_jco_or_p c\r\n"
						+ " INNER JOIN tb_psg_mstr_rank_jco r\r\n"
						+ "       ON c.rank = r.id\r\n"
						+ "     AND c.status = '1'\r\n"
						+ " INNER JOIN tb_psg_mstr_trade_jco t\r\n"
						+ "       ON t.id = c.trade\r\n"
						+ "INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "	ON c.unit_sus_no = orb.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "   LEFT JOIN tb_miso_orbat_arm_code e\r\n"
						+ "       ON e.arm_code = c.arm_service\r\n"
						+ "   LEFT JOIN tb_psg_medical_category_jco med_main\r\n"
						+ "       ON med_main.jco_id = c.id\r\n"
						+ "     AND med_main.status IN ('1','2')\r\n"
						+ "   LEFT JOIN tb_psg_census_army_course_jco cour\r\n"
						+ "       ON cour.jco_id = c.id\r\n"
						+ "   LEFT JOIN tb_psg_census_family_married_jco ma\r\n"
						+ "       ON ma.jco_id = c.id\n"
						+ " WHERE c.unit_sus_no = ?\n"
						+ "    AND c.category='OR'  "
						+ qry + " \n" + SearchValue + " GROUP BY 1,2,3,4, 5, 6,7,8,\n" + "                   ma.marriage_date,\n"
						+ "                   ma.maiden_name,orb.unit_name\n" + "   limit " + pageL + " OFFSET " + startPage + "";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}



			int j = 2;
			if (name != "" && name != null) {
				stmt.setString(j,"%" + name.toUpperCase() + "%");
				j += 1;
			}
			if (rank != "" && rank != null) {
				stmt.setInt(j,Integer.parseInt(rank));
				j += 1;
			}
			if (trade != "" && trade != null) {
				stmt.setString(j, trade);
				j += 1;
			}
			if (armyNo != "" && armyNo != null) {
				stmt.setString(j, armyNo);
			}

			int flag = 1;
			if (!search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  search.toUpperCase() + "%");

			}
			// stmt = setQueryWhereClause_SQLPERS(stmt,search, appt_trade, rank,
			// rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);

			ResultSet rs = stmt.executeQuery();
			System.out.println("Query OR --> " + stmt);
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
	public long getUnitReportPersORcount(String search, String orderColunm, String orderType,String rank,String name,String trade,String armyNo, HttpSession sessionUserId,
			String roleSusNo, String Access_by_susno) {
		// String SearchValue = GenerateQueryWhereClause_SQLPERS(Search, appt_trade,
		// rank, rank_cat, cont_comd, cont_corps, cont_div, cont_bde, radio1);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String SearchValue = "";
			String qry = "";
			if (name != "" && name != null) {
				qry += " and  upper(c.full_name) like ?";
			}
			if (rank != "" && rank != null) {
				qry += " and c.rank = ?";
			}

			if (trade != "" && trade != null) {
				qry += " and t.trade = ?";
			}
			if (armyNo != "" && armyNo != null) {
				qry += " and c.army_no = ?";
			}


			if (!search.equals("")) { // for Input Filter
				SearchValue = " and     ";

				SearchValue += "(upper(c.army_no) like ?   "
						+ "  or upper(r.rank ) like ? or upper(c.full_name) like ? or upper(t.trade) like ?"
						+ " or     upper(ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0')) like ? "
						+ " or     upper(ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0')) like ? "
						+ " or     upper(ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0')) like ? "
						+ "   or     upper(ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0')) like ?  or upper(cour.course_name) like ? or upper(ma.maiden_name) like ?"
						+ "or     upper(ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0')) like ? or upper(orb.unit_name) like ?)";
			}

			PreparedStatement stmt=null;
			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade") && !Access_by_susno.equals("Access")) {
				String from_code_control= formation_code(roleSusNo);
				q = "select count (app.*) from(SELECT c.army_no,\r\n"
						+ "             r.rank AS rk,\r\n"
						+ "             c.full_name AS name,\r\n"
						+ "             t.trade AS trade,         \r\n"
						+ "               ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,     \r\n"
						+ "               ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,   \r\n"
						+ "               ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0') AS doe,\r\n"
						+ "                 ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\r\n"
						+ "             'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\r\n"
						+ "            'C_E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') || 'C_O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || 'C_C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || 'C_P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') AS cope,\r\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY')) AS dom,\r\n"
						+ "             string_agg(distinct cour.course_name, ', ') army_courses,\r\n"
						+ "             ma.maiden_name AS spousename,\r\n"
						+ "			 orb.unit_name as unit_name\r\n"
						+ "   FROM tb_psg_census_jco_or_p c\r\n"
						+ " INNER JOIN tb_psg_mstr_rank_jco r\r\n"
						+ "       ON c.rank = r.id\r\n"
						+ "     AND c.status = '1'\r\n"
						+ " INNER JOIN tb_psg_mstr_trade_jco t\r\n"
						+ "       ON t.id = c.trade\r\n"
						+ "INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "	ON c.unit_sus_no = orb.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "   LEFT JOIN tb_miso_orbat_arm_code e\r\n"
						+ "       ON e.arm_code = c.arm_service\r\n"
						+ "   LEFT JOIN tb_psg_medical_category_jco med_main\r\n"
						+ "       ON med_main.jco_id = c.id\r\n"
						+ "     AND med_main.status IN ('1','2')\r\n"
						+ "   LEFT JOIN tb_psg_census_army_course_jco cour\r\n"
						+ "       ON cour.jco_id = c.id\r\n"
						+ "   LEFT JOIN tb_psg_census_family_married_jco ma\r\n"
						+ "       ON ma.jco_id = c.id\r\n"
						+ " WHERE c.unit_sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n"
						+ "    AND c.category='OR' \n"
						+ qry + " \n" + SearchValue + " GROUP BY 1,2,3,4, 5, 6,7,8,\n" + "                   ma.marriage_date,\n"
						+ "                   ma.maiden_name,orb.unit_name ) app ";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);



			}else{

				q = "select count (app.*) from(SELECT c.army_no,\r\n"
						+ "             r.rank AS rk,\r\n"
						+ "             c.full_name AS name,\r\n"
						+ "             t.trade AS trade,         \r\n"
						+ "               ltrim(to_char(c.date_of_birth,'DD-Mon-YYYY'),'0') AS dob,     \r\n"
						+ "               ltrim(to_char(c.date_of_seniority,'DD-Mon-YYYY'),'0') AS dos,   \r\n"
						+ "               ltrim(to_char(c.enroll_dt,'DD-Mon-YYYY'),'0') AS doe,\r\n"
						+ "                 ltrim(to_char(c.date_of_tos,'DD-Mon-YYYY'),'0') AS tos,\r\n"
						+ "             'S' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'S') || ';H' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'H') || ';A' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'A') || ';P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'P') || ';E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'E') AS med_cat,\r\n"
						+ "            'C_E' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_E') || 'C_O' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_O') || 'C_C' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_C') || 'C_P' || string_agg(med_main.shape_value,',') filter (WHERE med_main.shape = 'C_P') AS cope,\r\n"
						+ "       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY')) AS dom,\r\n"
						+ "             string_agg(distinct cour.course_name, ', ') army_courses,\r\n"
						+ "             ma.maiden_name AS spousename,\r\n"
						+ "			 orb.unit_name as unit_name\r\n"
						+ "   FROM tb_psg_census_jco_or_p c\r\n"
						+ " INNER JOIN tb_psg_mstr_rank_jco r\r\n"
						+ "       ON c.rank = r.id\r\n"
						+ "     AND c.status = '1'\r\n"
						+ " INNER JOIN tb_psg_mstr_trade_jco t\r\n"
						+ "       ON t.id = c.trade\r\n"
						+ "INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
						+ "	ON c.unit_sus_no = orb.sus_no and orb.status_sus_no ='Active'\r\n"
						+ "   LEFT JOIN tb_miso_orbat_arm_code e\r\n"
						+ "       ON e.arm_code = c.arm_service\r\n"
						+ "   LEFT JOIN tb_psg_medical_category_jco med_main\r\n"
						+ "       ON med_main.jco_id = c.id\r\n"
						+ "     AND med_main.status IN ('1','2')\r\n"
						+ "   LEFT JOIN tb_psg_census_army_course_jco cour\r\n"
						+ "       ON cour.jco_id = c.id\r\n"
						+ "   LEFT JOIN tb_psg_census_family_married_jco ma\r\n"
						+ "       ON ma.jco_id = c.id\n" + " WHERE c.unit_sus_no = ? \n"
						+ "    AND c.category='OR' \n"
						+ qry + " \n" + SearchValue + " GROUP BY 1,2,3,4, 5, 6,7,8,\n" + "                   ma.marriage_date,\n"
						+ "                   ma.maiden_name,orb.unit_name ) app ";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}


			int j = 2;
			if (name != "" && name != null) {
				stmt.setString(j,"%" + name.toUpperCase() + "%");
				j += 1;
			}
			if (rank != "" && rank != null) {
				stmt.setInt(j,Integer.parseInt(rank));
				j += 1;
			}
			if (trade != "" && trade != null) {
				stmt.setString(j, trade);
				j += 1;
			}
			if (armyNo != "" && armyNo != null) {
				stmt.setString(j, armyNo);
			}
			int flag = 1;
			if (!search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  search.toUpperCase() + "%");

			}
			// stmt = setQueryWhereClause_SQLPERS(stmt,Search, appt_trade, rank,
			// rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);

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
	public long getUnitReportPersOffrsAuthcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo, String Access_by_susno) {
		//			String SearchValue = GenerateQueryWhereClause_SQLPERS(Search,  appt_trade, rank, rank_cat, cont_comd, cont_corps,  cont_div,  cont_bde, radio1);

		int total = 0;
		String q = null;
		Connection conn = null;
		String SearchValue = "";

		if (search != "") {
			SearchValue = " and   ";
			SearchValue += "(upper(b.arm_desc) like ?   " + "or upper(description) like ? " + ")"

			;
		}

		try {
			conn = dataSource.getConnection();

			PreparedStatement stmt =null;
			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade") && Access_by_susno != "Access") {
				String from_code_control= formation_code(roleSusNo);

				q = "select count (app.*) from (SELECT coalesce(sum(n.base_auth),'0') AS base_auth,\n"
						+ "       coalesce(sum(n.mod_auth),'0') AS mod_auth,\n"
						+ "       coalesce(sum(n.foot_auth),'0') AS foot_auth,\n"
						+ "       coalesce(sum(base_auth + mod_auth + foot_auth),'0') AS total_auth,\n"
						+ "       coalesce(description,'0') AS rank,\n" + "       r.id AS rank_id,\n"
						+ "       b.arm_desc\n" + "  FROM sus_pers_stdauth n\n"
						+ " INNER JOIN cue_tb_psg_rank_app_master r\n" + "    ON r.code = n.rank_code\n"
						+ "   AND upper(r.level_in_hierarchy) = upper('Rank')\n" + "  LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "    ON b.arm_code = n.arm\n" + " WHERE parent_code = '0'\n" + "   AND sus_no 	in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') " + SearchValue
						+ "\n" + " GROUP BY rank_id,\n" + "          b.arm_desc)app";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);

			}else{

				q = "select count (app.*) from (SELECT coalesce(sum(n.base_auth),'0') AS base_auth,\n"
						+ "       coalesce(sum(n.mod_auth),'0') AS mod_auth,\n"
						+ "       coalesce(sum(n.foot_auth),'0') AS foot_auth,\n"
						+ "       coalesce(sum(base_auth + mod_auth + foot_auth),'0') AS total_auth,\n"
						+ "       coalesce(description,'0') AS rank,\n" + "       r.id AS rank_id,\n"
						+ "       b.arm_desc\n" + "  FROM sus_pers_stdauth n\n"
						+ " INNER JOIN cue_tb_psg_rank_app_master r\n" + "    ON r.code = n.rank_code\n"
						+ "   AND upper(r.level_in_hierarchy) = upper('Rank')\n" + "  LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "    ON b.arm_code = n.arm\n" + " WHERE parent_code = '0'\n" + "   AND sus_no = ? " + SearchValue
						+ "\n" + " GROUP BY rank_id,\n" + "          b.arm_desc)app";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}


			int flag = 1;
			if (search != "") {
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");

			}
			//					stmt = setQueryWhereClause_SQLPERS(stmt,Search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);

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
	public List<Map<String, Object>> getUnitReportPersOffrsAuth(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String roleSusNo, String Access_by_susno) {

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

			String SearchValue = "";

			if (search != "") {
				SearchValue = " and   ";
				SearchValue += "(upper(b.arm_desc) like ?   " + "or upper(description) like ? " + ")"

				;
			}

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;


			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade")  && Access_by_susno != "Access") {
				String from_code_control= formation_code(roleSusNo);
				q = " SELECT coalesce(sum(n.base_auth),'0') AS base_auth,\n"
						+ "       coalesce(sum(n.mod_auth),'0') AS mod_auth,\n"
						+ "       coalesce(sum(n.foot_auth),'0') AS foot_auth,\n"
						+ "       coalesce(sum(base_auth + mod_auth + foot_auth),'0') AS total_auth,\n"
						+ "       coalesce(description,'0') AS rank,\n" + "       r.id AS rank_id,\n"
						+ "       b.arm_desc\n" + "  FROM sus_pers_stdauth n\n"
						+ " INNER JOIN cue_tb_psg_rank_app_master r\n" + "    ON r.code = n.rank_code\n"
						+ "   AND upper(r.level_in_hierarchy) = upper('Rank')\n" + "  LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "    ON b.arm_code = n.arm\n" + " WHERE parent_code = '0'\n" + "   AND sus_no 	in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')" + SearchValue
						+ " \n" + " GROUP BY rank_id,\n" + "          b.arm_desc" + " limit " + pageL + " OFFSET "
						+ startPage + "";

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
						+ "    ON b.arm_code = n.arm\n" + " WHERE parent_code = '0'\n" + "   AND sus_no = ?" + SearchValue
						+ " \n" + " GROUP BY rank_id,\n" + "          b.arm_desc" + " limit " + pageL + " OFFSET "
						+ startPage + "";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}


			int flag = 1;
			if (search != "") {
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");

			}
			//
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
	public long getUnitReportPersJcoAuthcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo, String Access_by_susno) {
		int total = 0;
		String q = null;
		Connection conn = null;
		String SearchValue = "";

		if (search != "") {
			SearchValue = " and   ";
			SearchValue += "(upper(b.arm_desc) like ?   " + "or upper(description) like ? " + ")"

			;
		}

		try {
			conn = dataSource.getConnection();

			PreparedStatement stmt=null;
			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade") && Access_by_susno != "Access") {
				String from_code_control= formation_code(roleSusNo);

				q = "select count(app.*) from (SELECT a.sus_no,\n" + "       a.cat_id,\n"
						+ "       c.label AS per_category,\n" + "       a.arm,\n" + "       b.arm_desc,\n"
						+ "       coalesce(description,'0') AS rank,\n" + "       r.id AS rank_id,\n"
						+ "       coalesce(sum(a.base_auth),'0') AS base_auth,\n"
						+ "       coalesce(sum(a.mod_auth),'0') AS mod_auth,\n"
						+ "       coalesce(sum(a.foot_auth),'0') AS foot_auth,\n"
						+ "       coalesce(sum(base_auth + mod_auth + foot_auth),'0') AS total_auth\n"
						+ "  FROM sus_pers_stdauth a\n" + " INNER JOIN cue_tb_psg_rank_app_master r\n"
						+ "    ON r.code = a.rank_code\n" + "  LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "    ON b.arm_code = a.arm\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ "   AND a.rank_cat = '1'  " + SearchValue + "\n" + " GROUP BY a.sus_no,\n"
						+ "          a.cat_id,\n" + "          c.label,\n" + "          a.arm,\n"
						+ "          b.arm_desc,\n" + "          description,\n" + "          r.id)app";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);


			}else{


				q = "select count(app.*) from (SELECT a.sus_no,\n" + "       a.cat_id,\n"
						+ "       c.label AS per_category,\n" + "       a.arm,\n" + "       b.arm_desc,\n"
						+ "       coalesce(description,'0') AS rank,\n" + "       r.id AS rank_id,\n"
						+ "       coalesce(sum(a.base_auth),'0') AS base_auth,\n"
						+ "       coalesce(sum(a.mod_auth),'0') AS mod_auth,\n"
						+ "       coalesce(sum(a.foot_auth),'0') AS foot_auth,\n"
						+ "       coalesce(sum(base_auth + mod_auth + foot_auth),'0') AS total_auth\n"
						+ "  FROM sus_pers_stdauth a\n" + " INNER JOIN cue_tb_psg_rank_app_master r\n"
						+ "    ON r.code = a.rank_code\n" + "  LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "    ON b.arm_code = a.arm\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no = ?\n"
						+ "   AND a.rank_cat = '1'  " + SearchValue + "\n" + " GROUP BY a.sus_no,\n"
						+ "          a.cat_id,\n" + "          c.label,\n" + "          a.arm,\n"
						+ "          b.arm_desc,\n" + "          description,\n" + "          r.id)app";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
			}


			int flag = 1;
			if (search != "") {
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");

			}

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
	public List<Map<String, Object>> getUnitReportPersJcoAuth(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String roleSusNo, String Access_by_susno) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String SearchValue = "";

		if (search != "") {
			SearchValue = " and   ";
			SearchValue += "(upper(b.arm_desc) like ?   " + "or upper(description) like ? " + ")"

			;
		}
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;


			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade") && Access_by_susno != "Access") {
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
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no 	in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ "   AND a.rank_cat = '1'" + SearchValue + " \n"
						+ " GROUP BY a.sus_no,a.cat_id, c.label,a.arm, b.arm_desc, description,r.id\n" + " limit " + pageL
						+ " OFFSET " + startPage + "";

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
						+ "   AND a.rank_cat = '1'" + SearchValue + " \n"
						+ " GROUP BY a.sus_no,a.cat_id, c.label,a.arm, b.arm_desc, description,r.id\n" + " limit " + pageL
						+ " OFFSET " + startPage + "";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}



			int flag = 1;
			if (search != "") {
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");

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

	// ---------OR AUTHCOUNT AND LIST QUERY---//
	@Override
	public long getUnitReportPersOrAuthcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo, String Access_by_susno) {
		//			String SearchValue = GenerateQueryWhereClause_SQLPERS(Search,  appt_trade, rank, rank_cat, cont_comd, cont_corps,  cont_div,  cont_bde, radio1);
		//		String SearchValue = GenerateQueryWhereClause_SQLoffr(Search);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String SearchValue = "";
			if (!search.equals("")) { // for Input Filter
				SearchValue = " and   ";

				SearchValue += "(upper(c.label) like ?    or upper(  a.arm) like ?"

						+ " or upper( b.arm_desc) like ? or upper(description) like ? )";
			}

			PreparedStatement stmt;
			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade")  && Access_by_susno != "Access") {
				String from_code_control= formation_code(roleSusNo);

				q = "select count(app.*) from (  SELECT a.sus_no,\n" + "       a.cat_id,\n"
						+ "       c.label AS per_category,\n" + "       a.arm,\n" + "       b.arm_desc,\n"
						+ "       coalesce(description,'0') AS rank,\n" + "       r.id AS rank_id,\n"
						+ "       coalesce(sum(a.base_auth),'0') AS base_auth,\n"
						+ "       coalesce(sum(a.mod_auth),'0') AS mod_auth,\n"
						+ "       coalesce(sum(a.foot_auth),'0') AS foot_auth,\n"
						+ "       coalesce(sum(base_auth + mod_auth + foot_auth),'0') AS total_auth\n"
						+ "  FROM sus_pers_stdauth a\n" + " INNER JOIN cue_tb_psg_rank_app_master r\n"
						+ "    ON r.code = a.rank_code\n" + "  LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "    ON b.arm_code = a.arm\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ "  AND a.rank_cat IN ('2','3')\n" + SearchValue+


						" GROUP BY a.sus_no,\n" + "          a.cat_id,\n"
						+ "          c.label,\n" + "          a.arm,\n" + "          b.arm_desc,\n"
						+ "          description,\n" + "          r.id       )app";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);


			}else{

				q = "select count(app.*) from (  SELECT a.sus_no,\n" + "       a.cat_id,\n"
						+ "       c.label AS per_category,\n" + "       a.arm,\n" + "       b.arm_desc,\n"
						+ "       coalesce(description,'0') AS rank,\n" + "       r.id AS rank_id,\n"
						+ "       coalesce(sum(a.base_auth),'0') AS base_auth,\n"
						+ "       coalesce(sum(a.mod_auth),'0') AS mod_auth,\n"
						+ "       coalesce(sum(a.foot_auth),'0') AS foot_auth,\n"
						+ "       coalesce(sum(base_auth + mod_auth + foot_auth),'0') AS total_auth\n"
						+ "  FROM sus_pers_stdauth a\n" + " INNER JOIN cue_tb_psg_rank_app_master r\n"
						+ "    ON r.code = a.rank_code\n" + "  LEFT JOIN tb_miso_orbat_arm_code b\n"
						+ "    ON b.arm_code = a.arm\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no = ?\n"
						+ "  AND a.rank_cat IN ('2','3')\n" + SearchValue+


						" GROUP BY a.sus_no,\n" + "          a.cat_id,\n"
						+ "          c.label,\n" + "          a.arm,\n" + "          b.arm_desc,\n"
						+ "          description,\n" + "          r.id       )app";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}

			int flag = 1;

			if (!search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");

			}
			//					stmt = setQueryWhereClause_SQLPERS(stmt,Search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
			//			stmt = setQueryWhereClause_SQLoffr(stmt, Search);
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
	public List<Map<String, Object>> getUnitReportPersOrAuth(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String roleSusNo, String Access_by_susno) {


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


			String SearchValue = "";
			if (!search.equals("")) { // for Input Filter
				SearchValue = " and   ";

				SearchValue += "(upper(c.label) like ?    or upper(  a.arm) like ?"

						+ " or upper( b.arm_desc) like ? or upper(description) like ? )";
			}


			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade") && Access_by_susno != "Access") {
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
						+ "   AND a.rank_cat IN ('2','3')" + SearchValue + " \n"
						+ " GROUP BY a.sus_no, a.cat_id,c.label, a.arm,b.arm_desc,description,r.id\n" + " limit " + pageL
						+ " OFFSET " + startPage + "";

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
						+ "   AND a.rank_cat IN ('2','3')" + SearchValue + " \n"
						+ " GROUP BY a.sus_no, a.cat_id,c.label, a.arm,b.arm_desc,description,r.id\n" + " limit " + pageL
						+ " OFFSET " + startPage + "";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
			}


			int flag = 1;

			if (!search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");

			}
			//				stmt = setQueryWhereClause_SQLPERS(stmt,search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
			//			stmt = setQueryWhereClause_SQLoffr(stmt, search);
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

	// ------------CIV AUTH COUNT AND LIST QUERY----//

	@Override
	public long getUnitReportPersCivAuthcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo, String Access_by_susno) {
		//			String SearchValue = GenerateQueryWhereClause_SQLPERS(Search,  appt_trade, rank, rank_cat, cont_comd, cont_corps,  cont_div,  cont_bde, radio1);
		String SearchValue = GenerateQueryWhereClause_SQLoffr(Search);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt=null;
			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade") && Access_by_susno != "Access") {
				String from_code_control= formation_code(roleSusNo);

				q = "\n" + "select count(app.*) from (\n"
						+ "SELECT coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '4'),0) AS gp_a_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '5'),0) AS gp_b_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('6','9')),0) AS gp_b_non_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('7','8','10','11','12')),0) AS gp_c_non_gaz\n"
						+ "  FROM sus_pers_stdauth a\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ "   AND a.cat_id IN ('1','2'))app";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);



			}else{

				q = "\n" + "select count(app.*) from (\n"
						+ "SELECT coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '4'),0) AS gp_a_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '5'),0) AS gp_b_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('6','9')),0) AS gp_b_non_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('7','8','10','11','12')),0) AS gp_c_non_gaz\n"
						+ "  FROM sus_pers_stdauth a\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no = ?\n"
						+ "   AND a.cat_id IN ('1','2'))app";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}


			//					stmt = setQueryWhereClause_SQLPERS(stmt,Search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
			stmt = setQueryWhereClause_SQLoffr(stmt, Search);
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
	public List<Map<String, Object>> getUnitReportPersCivAuth(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String roleSusNo, String Access_by_susno) {
		String SearchValue = GenerateQueryWhereClause_SQLoffr(search);

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
			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade") && Access_by_susno != "Access") {
				String from_code_control= formation_code(roleSusNo);

				q = "SELECT coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '4'),0) AS gp_a_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '5'),0) AS gp_b_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('6','9')),0) AS gp_b_non_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('7','8','10','11','12')),0) AS gp_c_non_gaz\n"
						+ "  FROM sus_pers_stdauth a\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
						+ "   AND a.cat_id IN ('1','2')" + " limit " + pageL + " OFFSET " + startPage + "";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);


			}else{

				q = "SELECT coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '4'),0) AS gp_a_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat = '5'),0) AS gp_b_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('6','9')),0) AS gp_b_non_gaz,\n"
						+ "       coalesce(sum(a.base_auth + a.mod_auth + a.foot_auth) filter (WHERE a.rank_cat IN ('7','8','10','11','12')),0) AS gp_c_non_gaz\n"
						+ "  FROM sus_pers_stdauth a\n" + "  LEFT JOIN t_domain_value c\n"
						+ "    ON c.codevalue = a.cat_id\n" + "   AND c.domainid = 'PERSON_CAT'\n" + " WHERE a.sus_no = ?\n"
						+ "   AND a.cat_id IN ('1','2')" + " limit " + pageL + " OFFSET " + startPage + "";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}



			//				stmt = setQueryWhereClause_SQLPERS(stmt,search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
			stmt = setQueryWhereClause_SQLoffr(stmt, search);
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

	// ----- TMS AUTH COUNT AND LIST DATA -----//

	@Override
	public long getUnitReportVehAuthcount(String Search, String orderColunm, String orderType, String type_veh,
			String roleSusNo, HttpSession sessionUserId, String Access_by_susno) {
		//			String SearchValue = GenerateQueryWhereClause_SQLPERS(Search,  appt_trade, rank, rank_cat, cont_comd, cont_corps,  cont_div,  cont_bde, radio1);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String SearchValue = "";
			String s = "";

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += " ( upper(m.mct_nomen) like ?   " + "or upper(a.mct_no) like ? )"
						;
			}
			PreparedStatement stmt=null;
			String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
			if(formationtype.equals("Brigade")  && Access_by_susno != "Access") {
				String from_code_control= formation_code(roleSusNo);

				if (type_veh.equals("A")) {
					q = "SELECT count(app.*) from  (SELECT m.mct_nomen,\n" + "       a.mct_no,\n" + "       a.base::int,\n"
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
							+ "                   AND b_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
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
							+ "                 WHERE a_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
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
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'A'::text" + SearchValue
							+ " ) app ";
				}
				if (type_veh.equals("B")) {
					q = "select count (app.*) from (SELECT m.mct_nomen,\n" + "       a.mct_no,\n" + "       a.base::int,\n"
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
							+ "                   AND b_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
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
							+ "                 WHERE a_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
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
							+ "           AND b.type_of_veh::text = 'B'::text\n" + "         GROUP BY a.mct_no\n"
							+ "       ) AS a\n" + " INNER JOIN tb_tms_mct_main_master m\n"
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'B'::text\n" + SearchValue
							+ "  ORDER BY a.mct_no)app";
				}
				if (type_veh.equals("C")) {
					q = "select count(app.*) from (SELECT m.mct_nomen,\n" + "       a.mct_no,\n" + "       a.base::int,\n"
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
							+ "                   AND b_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
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
							+ "                 WHERE a_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
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
							+ "           AND b.type_of_veh::text = 'C'::text\n" + "         GROUP BY a.mct_no\n"
							+ "       ) AS a\n" + " INNER JOIN tb_tms_mct_main_master m\n"
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'C'::text" + SearchValue
							+ "	) app ";
				}

				stmt = conn.prepareStatement(q);
				//			  					stmt = setQueryWhereClause_SQLPERS(stmt,Search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
				stmt.setString(1, from_code_control);
				stmt.setString(2, from_code_control);
				stmt.setString(3, from_code_control);
				stmt.setString(4, from_code_control);
				stmt.setString(5, from_code_control);



			}else{


				if (type_veh.equals("A")) {
					q = "SELECT count(app.*) from  (SELECT m.mct_nomen,\n" + "       a.mct_no,\n" + "       a.base::int,\n"
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
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'A'::text" + SearchValue
							+ " ) app ";
				}
				if (type_veh.equals("B")) {
					q = "select count (app.*) from (SELECT m.mct_nomen,\n" + "       a.mct_no,\n" + "       a.base::int,\n"
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
							+ "                   AND a_1.sus_no = ? \n" + "                 GROUP BY a_1.sus_no,\n"
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
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'B'::text\n" + SearchValue
							+ "  ORDER BY a.mct_no)app";
				}
				if (type_veh.equals("C")) {
					q = "select count(app.*) from (SELECT m.mct_nomen,\n" + "       a.mct_no,\n" + "       a.base::int,\n"
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
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'C'::text" + SearchValue
							+ "	) app ";
				}

				stmt = conn.prepareStatement(q);
				//									stmt = setQueryWhereClause_SQLPERS(stmt,Search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
				stmt.setString(1, roleSusNo);
				stmt.setString(2, roleSusNo);
				stmt.setString(3, roleSusNo);
				stmt.setString(4, roleSusNo);
				stmt.setString(5, roleSusNo);

			}


			int flag = 5;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag,"%" +  Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,"%" +  Search.toUpperCase() + "%");


			}



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
	public List<Map<String, Object>> getUnitReportVehAuth(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String type_veh, String roleSusNo, String formationtype) {
		//		 String SearchValue = GenerateQueryWhereClause_SQLPERS(search,  appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
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
			String SearchValue = "";


			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += " ( upper(m.mct_nomen) like ?   " + "or upper(a.mct_no) like ? )"

				;
			}

			
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
							+ "                   AND b_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
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
							+ "                 WHERE a_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
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
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'A'::text\n" + ""
							+ SearchValue + "  limit " + pageL + " OFFSET " + startPage + "";
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
							+ "                   AND b_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
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
							+ "                 WHERE a_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
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
							+ "           AND b.type_of_veh::text = 'B'::text\n" + "         GROUP BY a.mct_no\n"
							+ "       ) AS a\n" + " INNER JOIN tb_tms_mct_main_master m\n"
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'B'::text " + SearchValue
							+ "  " + "  limit " + pageL + " OFFSET " + startPage + "";
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
							+ "                   AND b_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
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
							+ "                 WHERE a_1.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n" + "                 UNION SELECT b_1.mct_no,\n"
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
							+ "           AND b.type_of_veh::text = 'C'::text\n" + "         GROUP BY a.mct_no\n"
							+ "       ) AS a\n" + " INNER JOIN tb_tms_mct_main_master m\n"
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'C'::text " + SearchValue
							+ "  limit " + pageL + " OFFSET " + startPage + "";
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
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'A'::text\n" + ""
							+ SearchValue + "  limit " + pageL + " OFFSET " + startPage + "";
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
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'B'::text " + SearchValue
							+ "  " + "  limit " + pageL + " OFFSET " + startPage + "";
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
							+ "    ON a.mct_no = m.mct_main_id\n" + "   AND m.type_of_veh::text = 'C'::text " + SearchValue
							+ "  limit " + pageL + " OFFSET " + startPage + "";
				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);
				stmt.setString(2, roleSusNo);
				stmt.setString(3, roleSusNo);
				stmt.setString(4, roleSusNo);
				stmt.setString(5, roleSusNo);

			}

			int flag = 5;
			if (!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			//				stmt = setQueryWhereClause_SQLPERS(stmt,search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);

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

	// -----EQPTS AUTH COUNT AND LIST-----//

	@Override
	public long getUnitReportWpnEqptAuthcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String type_mtrls, String roleSusNo, String formationtype) {
		//			String SearchValue = GenerateQueryWhereClause_SQLPERS(Search,  appt_trade, rank, rank_cat, cont_comd, cont_corps,  cont_div,  cont_bde, radio1);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String SearchValue = "";
			PreparedStatement stmt = null;

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "(upper(a.item_code) like ?   " + "or upper(i.item_type) like ? "
						+ "or upper(a.type) like ?" + ")"

				;
			}

			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				if (type_mtrls.equals("wpn")) {


					q = "SELECT COUNT(app.*) from (SELECT a.sus_no,\n"
							+ "       a.item_code,\n"
							+ "       i.item_type,\n"
							+ "       a.type,\n"
							+ "       a.total_ue_qty\n"
							+ "  FROM mms_ue_mview a\n"
							+ "  JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "    ON i.item_code = a.item_code\n"
							+ "   AND left(i.cos_sec_no, 1) IN ('B','M','N','O','C')\n"
							+ " WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
							+ "   AND a.total_ue_qty != 0" + SearchValue
							+ ")app";



				}

				if (type_mtrls.equals("eqi")) {


					q = "select count(app.*) from (SELECT a.sus_no,\n"
							+ "       a.item_code,\n"
							+ "       i.item_type,\n"
							+ "       a.type,\n"
							+ "       a.total_ue_qty\n"
							+ "  FROM mms_ue_mview a\n"
							+ "  JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "    ON i.item_code = a.item_code\n"
							+ "   AND left(i.cos_sec_no, 1) NOT IN ('B','M','N','O','C','G')\n"
							+ " WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
							+ "   AND a.total_ue_qty != 0 "
							+ SearchValue + ")app ";


				}
				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);


			}else{
				if (type_mtrls.equals("wpn")) {


					q = "SELECT COUNT(app.*) from (SELECT a.sus_no,\n"
							+ "       a.item_code,\n"
							+ "       i.item_type,\n"
							+ "       a.type,\n"
							+ "       a.total_ue_qty\n"
							+ "  FROM mms_ue_mview a\n"
							+ "  JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "    ON i.item_code = a.item_code\n"
							+ "   AND left(i.cos_sec_no, 1) IN ('B','M','N','O','C')\n"
							+ " WHERE a.sus_no = ?\n"
							+ "   AND a.total_ue_qty != 0" + SearchValue
							+ ")app";



				}

				if (type_mtrls.equals("eqi")) {


					q = "select count(app.*) from (SELECT a.sus_no,\n"
							+ "       a.item_code,\n"
							+ "       i.item_type,\n"
							+ "       a.type,\n"
							+ "       a.total_ue_qty\n"
							+ "  FROM mms_ue_mview a\n"
							+ "  JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "    ON i.item_code = a.item_code\n"
							+ "   AND left(i.cos_sec_no, 1) NOT IN ('B','M','N','O','C','G')\n"
							+ " WHERE a.sus_no = ?\n"
							+ "   AND a.total_ue_qty != 0 "
							+ SearchValue + ")app ";


				}
				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}

			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
			// stmt = setQueryWhereClause_SQLPERS(stmt,Search, appt_trade, rank,
			// rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);

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


	// WPN AND EQPT
	@Override
	public List<Map<String, Object>> getUnitReportWpnEqptAuth(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String type_mtrls, String roleSusNo, String formationtype) {
		//	 		 String SearchValue = GenerateQueryWhereClause_SQLPERS(search,  appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
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
			String SearchValue = "";

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "(upper(a.item_code) like ?   " + "or upper(i.item_type) like ? "
						+ "or upper(a.type) like ?" + ")"

				;
			}

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				if (type_mtrls.equals("wpn")) {

					q = " SELECT a.sus_no,\n"
							+ "       a.item_code,\n"
							+ "       i.item_type,\n"
							+ "       a.type,\n"
							+ "       a.total_ue_qty\n"
							+ "  FROM mms_ue_mview a\n"
							+ "  JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "    ON i.item_code = a.item_code\n"
							+ "   AND left(i.cos_sec_no, 1) IN ('B','M','N','O','C')\n"
							+ " WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
							+ "   AND a.total_ue_qty != 0" + SearchValue
							+ "  limit " + pageL + " OFFSET " + startPage + "";



				}
				if (type_mtrls.equals("eqi")) {



					q = "SELECT a.sus_no,\n"
							+ "       a.item_code,\n"
							+ "       i.item_type,\n"
							+ "       a.type,\n"
							+ "       a.total_ue_qty\n"
							+ "  FROM mms_ue_mview a\n"
							+ "  JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "    ON i.item_code = a.item_code\n"
							+ "   AND left(i.cos_sec_no, 1) NOT IN ('B','M','N','O','C','G')\n"
							+ " WHERE a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')\n"
							+ "   AND a.total_ue_qty != 0"
							+ SearchValue + "  limit " + pageL + " OFFSET " + startPage + "";


				}

				stmt = conn.prepareStatement(q);

				stmt.setString(1, from_code_control);


			}else{

				if (type_mtrls.equals("wpn")) {

					q = " SELECT a.sus_no,\n"
							+ "       a.item_code,\n"
							+ "       i.item_type,\n"
							+ "       a.type,\n"
							+ "       a.total_ue_qty\n"
							+ "  FROM mms_ue_mview a\n"
							+ "  JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "    ON i.item_code = a.item_code\n"
							+ "   AND left(i.cos_sec_no, 1) IN ('B','M','N','O','C')\n"
							+ " WHERE a.sus_no = ?\n"
							+ "   AND a.total_ue_qty != 0" + SearchValue
							+ "  limit " + pageL + " OFFSET " + startPage + "";



				}
				if (type_mtrls.equals("eqi")) {



					q = "SELECT a.sus_no,\n"
							+ "       a.item_code,\n"
							+ "       i.item_type,\n"
							+ "       a.type,\n"
							+ "       a.total_ue_qty\n"
							+ "  FROM mms_ue_mview a\n"
							+ "  JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "    ON i.item_code = a.item_code\n"
							+ "   AND left(i.cos_sec_no, 1) NOT IN ('B','M','N','O','C','G')\n"
							+ " WHERE a.sus_no = ?\n"
							+ "   AND a.total_ue_qty != 0"
							+ SearchValue + "  limit " + pageL + " OFFSET " + startPage + "";


				}

				stmt = conn.prepareStatement(q);

				stmt.setString(1, roleSusNo);

			}


			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

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


	@Override
	public List<Map<String, Object>> getUnitReportWpnEqptSummary(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String type_mtrls, String roleSusNo,String  prfgp,String nomenclature,String census_no,String service_status,String broadCat, String formationtype) {
		//		 String SearchValue = GenerateQueryWhereClause_SQLPERS(search,  appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
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
			String SearchValue = "";

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "(upper(f.prf_group) like ?   " + ")";
			}

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			
			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				if (type_mtrls.equals("wpn")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}


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
							+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C')   \n" + SearchValue
							+ "     group by f.prf_group" + "  limit " + pageL + " OFFSET " + startPage + "";
				}
				if (type_mtrls.equals("eqi")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
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
							+ "     and LEFT(i.cos_sec_no, 1) NOT IN ('B','M','N','O','C','G')  \n" + SearchValue
							+ "     group by f.prf_group" + "  limit " + pageL + " OFFSET " + startPage + "";
				}

				stmt = conn.prepareStatement(q);

				stmt.setString(1, from_code_control);


			}else{


				if (type_mtrls.equals("wpn")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}


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
							+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C')   \n" + SearchValue
							+ "     group by f.prf_group" + "  limit " + pageL + " OFFSET " + startPage + "";
				}
				if (type_mtrls.equals("eqi")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
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
							+ "     and LEFT(i.cos_sec_no, 1) Not IN ('B','M','N','O','C','G')  \n" + SearchValue
							+ "     group by f.prf_group" + "  limit " + pageL + " OFFSET " + startPage + "";
				}

				stmt = conn.prepareStatement(q);

				stmt.setString(1, roleSusNo);
			}



			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase() + "%");

			}

			if (type_mtrls.equals("eqi")) {

				if(!prfgp.equals("")) {
					flag += 1;
					stmt.setString(flag, prfgp.toUpperCase()+"%");
				}

				if(!nomenclature.equals("")) {
					flag += 1;
					stmt.setString(flag, nomenclature.toUpperCase()+"%");
				}

				if(!census_no.equals("")) {
					flag += 1;
					stmt.setString(flag, census_no.toUpperCase()+"%");
				}

				if(!service_status.equals("")) {
					flag += 1;
					stmt.setString(flag, service_status.toUpperCase());
				}
				if(!broadCat.equals("")) {
					flag += 1;
					stmt.setString(flag, broadCat.toUpperCase());
				}
			}

			if (type_mtrls.equals("wpn")) {

				if(!prfgp.equals("")) {
					flag += 1;
					stmt.setString(flag, prfgp.toUpperCase()+"%");
				}

				if(!nomenclature.equals("")) {
					flag += 1;
					stmt.setString(flag, nomenclature.toUpperCase()+"%");
				}

				if(!census_no.equals("")) {
					flag += 1;
					stmt.setString(flag, census_no.toUpperCase()+"%");
				}

				if(!service_status.equals("")) {
					flag += 1;
					stmt.setString(flag, service_status.toUpperCase());
				}
				if(!broadCat.equals("")) {
					flag += 1;
					stmt.setString(flag, broadCat.toUpperCase());
				}
			}
			
			//System.err.println("wpneqpt data:\n\n" + stmt);
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
	public long getUnitReportWpnEqptcountSummary(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String type_mtrls, String roleSusNo, String  prfgp,String nomenclature,String census_no,String service_status,String broadCat, String formationtype) {

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String SearchValue = "";

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "(upper( f.prf_group) like ?" + ")"

				;
			}


			PreparedStatement stmt=null;
			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);
				if (type_mtrls.equals("wpn")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
					q = "select count (app.*) from(  SELECT \r\n"
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
							+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C')   \n" + SearchValue
							+ "     group by f.prf_group) app ";
				}

				if (type_mtrls.equals("eqi")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
					q = "select count (app.*) from( SELECT \r\n"
							+ " f.prf_group,count(f.prf_group) as total\r\n"
							+ "\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) Not IN ('B','M','N','O','C','G')  \n" + SearchValue
							+ "     group by f.prf_group ) app ";

				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);



			}else{

				if (type_mtrls.equals("wpn")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
					q = "select count (app.*) from(  SELECT \r\n"
							+ " f.prf_group,count(f.prf_group) as total \r\n"
							+ "\r\n"
							+ "   FROM mms_tb_regn_mstr_detl a\r\n"
							+ "	  inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
							+ "   INNER  JOIN mms_domain_values b ON a.service_status::text = b.codevalue::text AND b.domainid::text = 'SERVICIABILITY'::text\r\n"
							+ "	  inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
							+ "	 INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
							+ "    ON m.item_code::text = i.item_code::text\r\n"
							+ "		 left join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
							+ "		where  a.sus_no=?  \r\n"
							+ "     and LEFT(i.cos_sec_no, 1) IN ('B','M','N','O','C')   \n" + SearchValue
							+ "     group by f.prf_group) app ";
				}

				if (type_mtrls.equals("eqi")) {

					if( !prfgp.equals("")) {
						SearchValue += " and upper(f.prf_group) like ?  ";
					}

					if( !nomenclature.equals("")) {
						SearchValue += " and upper(m.nomen) like ?  ";
					}

					if( !census_no.equals("")) {
						SearchValue += " and  a.census_no like ?  ";
					}
					if( !service_status.equals("")) {
						SearchValue += " and  a.service_status like ?  ";
					}
					if( !broadCat.equals("")) {
						SearchValue += " and  i.item_group like ?  ";
					}
					q = "select count (app.*) from( SELECT \r\n"
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
							+ "     and LEFT(i.cos_sec_no, 1) Not IN ('B','M','N','O','C','G')  \n" + SearchValue
							+ "     group by f.prf_group ) app ";

				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}


			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase() + "%");

			}

			if (type_mtrls.equals("eqi")) {

				if(!prfgp.equals("")) {
					flag += 1;
					stmt.setString(flag, prfgp.toUpperCase()+"%");
				}

				if(!nomenclature.equals("")) {
					flag += 1;
					stmt.setString(flag, nomenclature.toUpperCase()+"%");
				}

				if(!census_no.equals("")) {
					flag += 1;
					stmt.setString(flag, census_no.toUpperCase()+"%");
				}

				if(!service_status.equals("")) {
					flag += 1;
					stmt.setString(flag, service_status.toUpperCase());
				}
				if(!broadCat.equals("")) {
					flag += 1;
					stmt.setString(flag, broadCat.toUpperCase());
				}
			}

			if (type_mtrls.equals("wpn")) {

				if(!prfgp.equals("")) {
					flag += 1;
					stmt.setString(flag, prfgp.toUpperCase()+"%");
				}

				if(!nomenclature.equals("")) {
					flag += 1;
					stmt.setString(flag, nomenclature.toUpperCase()+"%");
				}

				if(!census_no.equals("")) {
					flag += 1;
					stmt.setString(flag, census_no.toUpperCase()+"%");
				}

				if(!service_status.equals("")) {
					flag += 1;
					stmt.setString(flag, service_status.toUpperCase());
				}
				if(!broadCat.equals("")) {
					flag += 1;
					stmt.setString(flag, broadCat.toUpperCase());
				}
			}
			//System.err.println("wpneqpt data count:\n\n" + stmt);
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
	public List<String> getbroadCatUnitReort(String broadCat,String type_mtrls, String roleSusNo,String formationtype) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			PreparedStatement stmt=null;
			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				if (type_mtrls.equals("wpn")) {
					sq1 = "select \n"
							+ "distinct\n"
							+ "   i.item_group\n"
							+ "\n"
							+ "    from \n"
							+ "    (select \n"
							+ "     a.sus_no,\n"
							+ "     a.census_no,\n"
							+ "     a.type_of_hldg,\n"
							+ "     a.type_of_eqpt,\n"
							+ "     a.tothol,\n"
							+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
							+ "                        ELSE 'SER'\n"
							+ "                          END AS ser_unsv\n"
							+ "     from mms_tv_regn_mstr a \n"
							+ "        where a.sus_no 	in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n"
							+ "    ) a     \n"
							+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
							+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
							+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
							+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
							+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
							+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "         ON m.item_code::text = i.item_code::text\n"
							+ "         where LEFT(i.cos_sec_no, 1) in ('B','M','N','O','C') and i.item_group like ? ";

				}

				if (type_mtrls.equals("eqi")) {
					sq1 = "select distinct\n"
							+ "    i.item_group\n"
							+ "    from \n"
							+ "    (select \n"
							+ "     a.sus_no,\n"
							+ "     a.census_no,\n"
							+ "     a.type_of_hldg,\n"
							+ "     a.type_of_eqpt,\n"
							+ "     a.tothol,\n"
							+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
							+ "         ELSE 'SER'\n"
							+ "          END AS ser_unsv\n"
							+ "     from mms_tv_regn_mstr a \n"
							+ "        where a.sus_no 	in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')) a     \n"
							+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
							+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
							+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
							+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
							+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
							+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "         ON m.item_code::text = i.item_code::text\n"
							+ "         where LEFT(i.cos_sec_no, 1) NOT in ('B','M','N','O','C','G') and i.item_group like ? ";
				}
				stmt = conn.prepareStatement(sq1);
				stmt.setString(1, from_code_control);


			}else{

				if (type_mtrls.equals("wpn")) {
					sq1 = "select \n"
							+ "distinct\n"
							+ "   i.item_group\n"
							+ "\n"
							+ "    from \n"
							+ "    (select \n"
							+ "     a.sus_no,\n"
							+ "     a.census_no,\n"
							+ "     a.type_of_hldg,\n"
							+ "     a.type_of_eqpt,\n"
							+ "     a.tothol,\n"
							+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
							+ "                        ELSE 'SER'\n"
							+ "                          END AS ser_unsv\n"
							+ "     from mms_tv_regn_mstr a \n"
							+ "        where a.sus_no = ? \n"
							+ "    ) a     \n"
							+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
							+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
							+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
							+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
							+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
							+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "         ON m.item_code::text = i.item_code::text\n"
							+ "         where LEFT(i.cos_sec_no, 1) in ('B','M','N','O','C') and i.item_group like ? ";

				}

				if (type_mtrls.equals("eqi")) {
					sq1 = "select distinct\n"
							+ "    i.item_group\n"
							+ "    from \n"
							+ "    (select \n"
							+ "     a.sus_no,\n"
							+ "     a.census_no,\n"
							+ "     a.type_of_hldg,\n"
							+ "     a.type_of_eqpt,\n"
							+ "     a.tothol,\n"
							+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
							+ "         ELSE 'SER'\n"
							+ "          END AS ser_unsv\n"
							+ "     from mms_tv_regn_mstr a \n"
							+ "        where a.sus_no = ?) a     \n"
							+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
							+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
							+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
							+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
							+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
							+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "         ON m.item_code::text = i.item_code::text\n"
							+ "         where LEFT(i.cos_sec_no, 1) NOT in ('B','M','N','O','C','G') and i.item_group like ? ";
				}
				stmt = conn.prepareStatement(sq1);
				stmt.setString(1, roleSusNo);

			}

			stmt.setString(2, broadCat + "%");
			ResultSet rs = stmt.executeQuery();


			while (rs.next()) {
				String item_group = rs.getString("item_group");
				l.add(item_group);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	@Override
	public List<String> getPRF_Grp(String prfgp,String type_mtrls, String roleSusNo,String formationtype) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			PreparedStatement stmt=null;

			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);
				if (type_mtrls.equals("wpn")) {
					sq1 = "select \n"
							+ "distinct\n"
							+ "  f.prf_group\n"
							+ "\n"
							+ "    from \n"
							+ "    (select \n"
							+ "     a.sus_no,\n"
							+ "     a.census_no,\n"
							+ "     a.type_of_hldg,\n"
							+ "     a.type_of_eqpt,\n"
							+ "     a.tothol,\n"
							+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
							+ "                        ELSE 'SER'\n"
							+ "                          END AS ser_unsv\n"
							+ "     from mms_tv_regn_mstr a \n"
							+ "        where a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n"
							+ "    ) a     \n"
							+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
							+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
							+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
							+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
							+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
							+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "         ON m.item_code::text = i.item_code::text\n"
							+ "         where LEFT(i.cos_sec_no, 1) in ('B','M','N','O','C') and upper(f.prf_group) like upper(?) \n "
							+ "     order by f.prf_group";

				}

				if (type_mtrls.equals("eqi")) {
					sq1 = "select distinct\n"
							+ "    f.prf_group\n"
							+ "    from \n"
							+ "    (select \n"
							+ "     a.sus_no,\n"
							+ "     a.census_no,\n"
							+ "     a.type_of_hldg,\n"
							+ "     a.type_of_eqpt,\n"
							+ "     a.tothol,\n"
							+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
							+ "         ELSE 'SER'\n"
							+ "          END AS ser_unsv\n"
							+ "     from mms_tv_regn_mstr a \n"
							+ "        where a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')) a     \n"
							+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
							+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
							+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
							+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
							+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
							+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "         ON m.item_code::text = i.item_code::text\n"
							+ "         where LEFT(i.cos_sec_no, 1) NOT in ('B','M','N','O','C','G') and upper(f.prf_group) like ? \n"
							+ "     order by f.prf_group";
				}
				stmt = conn.prepareStatement(sq1);
				stmt.setString(1, from_code_control);



			}else{

				if (type_mtrls.equals("wpn")) {
					sq1 = "select \n"
							+ "distinct\n"
							+ "  f.prf_group\n"
							+ "\n"
							+ "    from \n"
							+ "    (select \n"
							+ "     a.sus_no,\n"
							+ "     a.census_no,\n"
							+ "     a.type_of_hldg,\n"
							+ "     a.type_of_eqpt,\n"
							+ "     a.tothol,\n"
							+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
							+ "                        ELSE 'SER'\n"
							+ "                          END AS ser_unsv\n"
							+ "     from mms_tv_regn_mstr a \n"
							+ "        where a.sus_no = ? \n"
							+ "    ) a     \n"
							+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
							+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
							+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
							+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
							+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
							+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "         ON m.item_code::text = i.item_code::text\n"
							+ "         where LEFT(i.cos_sec_no, 1) in ('B','M','N','O','C') and upper(f.prf_group) like upper(?) \n "
							+ "     order by f.prf_group";

				}

				if (type_mtrls.equals("eqi")) {
					sq1 = "select distinct\n"
							+ "    f.prf_group\n"
							+ "    from \n"
							+ "    (select \n"
							+ "     a.sus_no,\n"
							+ "     a.census_no,\n"
							+ "     a.type_of_hldg,\n"
							+ "     a.type_of_eqpt,\n"
							+ "     a.tothol,\n"
							+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
							+ "         ELSE 'SER'\n"
							+ "          END AS ser_unsv\n"
							+ "     from mms_tv_regn_mstr a \n"
							+ "        where a.sus_no = ?) a     \n"
							+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
							+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
							+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
							+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
							+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
							+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "         ON m.item_code::text = i.item_code::text\n"
							+ "         where LEFT(i.cos_sec_no, 1) NOT in ('B','M','N','O','C','G') and upper(f.prf_group) like ? \n"
							+ "     order by f.prf_group";
				}
				stmt = conn.prepareStatement(sq1);
				stmt.setString(1, roleSusNo);

			}

			stmt.setString(2, prfgp.toUpperCase() + "%");
			ResultSet rs = stmt.executeQuery();


			while (rs.next()) {
				String prfGroup = rs.getString("prf_group");
				l.add(prfGroup);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<String> getnomeNclatureforUnitreport(String nomenclature,String type_mtrls, String roleSusNo,String formationtype) {


		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";

			PreparedStatement stmt=null;
			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				if (type_mtrls.equals("wpn")) {
					sq1 = "select \n"
							+ "distinct\n"
							+ "   m.nomen\n"
							+ "\n"
							+ "    from \n"
							+ "    (select \n"
							+ "     a.sus_no,\n"
							+ "     a.census_no,\n"
							+ "     a.type_of_hldg,\n"
							+ "     a.type_of_eqpt,\n"
							+ "     a.tothol,\n"
							+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
							+ "                        ELSE 'SER'\n"
							+ "                          END AS ser_unsv\n"
							+ "     from mms_tv_regn_mstr a \n"
							+ "        where a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n"
							+ "    ) a     \n"
							+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
							+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
							+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
							+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
							+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
							+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "         ON m.item_code::text = i.item_code::text\n"
							+ "         where LEFT(i.cos_sec_no, 1) in ('B','M','N','O','C') and upper(m.nomen) like upper(?) \n "
							+ "     order by  m.nomen";

				}

				if (type_mtrls.equals("eqi")) {
					sq1 = "select distinct\n"
							+ "     m.nomen\n"
							+ "    from \n"
							+ "    (select \n"
							+ "     a.sus_no,\n"
							+ "     a.census_no,\n"
							+ "     a.type_of_hldg,\n"
							+ "     a.type_of_eqpt,\n"
							+ "     a.tothol,\n"
							+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
							+ "         ELSE 'SER'\n"
							+ "          END AS ser_unsv\n"
							+ "     from mms_tv_regn_mstr a \n"
							+ "        where a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')) a     \n"
							+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
							+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
							+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
							+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
							+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
							+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "         ON m.item_code::text = i.item_code::text\n"
							+ "         where LEFT(i.cos_sec_no, 1) NOT in ('B','M','N','O','C','G') and upper(m.nomen) like ? \n"
							+ "     order by  m.nomen";
				}
				stmt = conn.prepareStatement(sq1);
				stmt.setString(1, from_code_control);



			}else{

				if (type_mtrls.equals("wpn")) {
					sq1 = "select \n"
							+ "distinct\n"
							+ "   m.nomen\n"
							+ "\n"
							+ "    from \n"
							+ "    (select \n"
							+ "     a.sus_no,\n"
							+ "     a.census_no,\n"
							+ "     a.type_of_hldg,\n"
							+ "     a.type_of_eqpt,\n"
							+ "     a.tothol,\n"
							+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
							+ "                        ELSE 'SER'\n"
							+ "                          END AS ser_unsv\n"
							+ "     from mms_tv_regn_mstr a \n"
							+ "        where a.sus_no = ? \n"
							+ "    ) a     \n"
							+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
							+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
							+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
							+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
							+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
							+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "         ON m.item_code::text = i.item_code::text\n"
							+ "         where LEFT(i.cos_sec_no, 1) in ('B','M','N','O','C') and upper(m.nomen) like upper(?) \n "
							+ "     order by  m.nomen";

				}

				if (type_mtrls.equals("eqi")) {
					sq1 = "select distinct\n"
							+ "     m.nomen\n"
							+ "    from \n"
							+ "    (select \n"
							+ "     a.sus_no,\n"
							+ "     a.census_no,\n"
							+ "     a.type_of_hldg,\n"
							+ "     a.type_of_eqpt,\n"
							+ "     a.tothol,\n"
							+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
							+ "         ELSE 'SER'\n"
							+ "          END AS ser_unsv\n"
							+ "     from mms_tv_regn_mstr a \n"
							+ "        where a.sus_no = ?) a     \n"
							+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
							+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
							+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
							+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
							+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
							+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
							+ "         ON m.item_code::text = i.item_code::text\n"
							+ "         where LEFT(i.cos_sec_no, 1) NOT in ('B','M','N','O','C','G') and upper(m.nomen) like ? \n"
							+ "     order by  m.nomen";
				}
				stmt = conn.prepareStatement(sq1);
				stmt.setString(1, roleSusNo);

			}


			stmt.setString(2, "%"+ nomenclature.toUpperCase() + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String nomen = rs.getString("nomen");
				l.add(nomen);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}


	@Override
	public List<String> getnomeCensusnoforUnitreport(String census_no,String type_mtrls, String roleSusNo,String formationtype) {


		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {	conn = dataSource.getConnection();
		String sq1 = "";
		PreparedStatement stmt=null;

		if(formationtype.equals("Brigade")) {
			String from_code_control= formation_code(roleSusNo);

			if (type_mtrls.equals("wpn")) {
				sq1 = "select \n"
						+ "distinct\n"
						+ "    a.census_no\n"
						+ "\n"
						+ "    from \n"
						+ "    (select \n"
						+ "     a.sus_no,\n"
						+ "     a.census_no,\n"
						+ "     a.type_of_hldg,\n"
						+ "     a.type_of_eqpt,\n"
						+ "     a.tothol,\n"
						+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
						+ "                        ELSE 'SER'\n"
						+ "                          END AS ser_unsv\n"
						+ "     from mms_tv_regn_mstr a \n"
						+ "        where a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n"
						+ "    ) a     \n"
						+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
						+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
						+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
						+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
						+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
						+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
						+ "         ON m.item_code::text = i.item_code::text\n"
						+ "         where LEFT(i.cos_sec_no, 1) in ('B','M','N','O','C') and  a.census_no like ? ";
			}

			if (type_mtrls.equals("eqi")) {

				sq1 = "select distinct\n"
						+ "    a.census_no\n"
						+ "    from \n"
						+ "    (select \n"
						+ "     a.sus_no,\n"
						+ "     a.census_no,\n"
						+ "     a.type_of_hldg,\n"
						+ "     a.type_of_eqpt,\n"
						+ "     a.tothol,\n"
						+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
						+ "         ELSE 'SER'\n"
						+ "          END AS ser_unsv\n"
						+ "     from mms_tv_regn_mstr a \n"
						+ "        where a.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')) a     \n"
						+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
						+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
						+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
						+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
						+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
						+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
						+ "         ON m.item_code::text = i.item_code::text\n"
						+ "         where LEFT(i.cos_sec_no, 1) NOT in ('B','M','N','O','C','G') and  a.census_no like ?\n" ;
			}
			stmt = conn.prepareStatement(sq1);
			stmt.setString(1, from_code_control);



		}else{

			if (type_mtrls.equals("wpn")) {
				sq1 = "select \n"
						+ "distinct\n"
						+ "    a.census_no\n"
						+ "\n"
						+ "    from \n"
						+ "    (select \n"
						+ "     a.sus_no,\n"
						+ "     a.census_no,\n"
						+ "     a.type_of_hldg,\n"
						+ "     a.type_of_eqpt,\n"
						+ "     a.tothol,\n"
						+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
						+ "                        ELSE 'SER'\n"
						+ "                          END AS ser_unsv\n"
						+ "     from mms_tv_regn_mstr a \n"
						+ "        where a.sus_no = ? \n"
						+ "    ) a     \n"
						+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
						+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
						+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
						+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
						+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
						+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
						+ "         ON m.item_code::text = i.item_code::text\n"
						+ "         where LEFT(i.cos_sec_no, 1) in ('B','M','N','O','C') and  a.census_no like ? ";
			}

			if (type_mtrls.equals("eqi")) {

				sq1 = "select distinct\n"
						+ "    a.census_no\n"
						+ "    from \n"
						+ "    (select \n"
						+ "     a.sus_no,\n"
						+ "     a.census_no,\n"
						+ "     a.type_of_hldg,\n"
						+ "     a.type_of_eqpt,\n"
						+ "     a.tothol,\n"
						+ "        CASE WHEN a.service_status = '0' THEN 'UNSV'\n"
						+ "         ELSE 'SER'\n"
						+ "          END AS ser_unsv\n"
						+ "     from mms_tv_regn_mstr a \n"
						+ "        where a.sus_no = ?) a     \n"
						+ "     inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \n"
						+ "     inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \n"
						+ "     inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n"
						+ "     inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \n"
						+ "      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.sus_no and dt.census_no= a.census_no and dt.type_of_hldg=a.type_of_hldg\n"
						+ "      LEFT JOIN cue_tb_miso_mms_item_mstr i\n"
						+ "         ON m.item_code::text = i.item_code::text\n"
						+ "         where LEFT(i.cos_sec_no, 1) NOT in ('B','M','N','O','C','G') and  a.census_no like ?\n" ;
			}
			stmt = conn.prepareStatement(sq1);
			stmt.setString(1, roleSusNo);

		}

		stmt.setString(2, census_no + "%");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			String censusno = rs.getString("census_no");
			l.add(censusno);
		}
		rs.close();
		stmt.close();
		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<String> tmsBa_No( String roleSusNo,String type_of_veh,String broadcat,String type,String formationtype) {
		List<String> FinalList = new ArrayList<String>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;



			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				if (type_of_veh.equals("A")) {


					q = " SELECT DISTINCT\n" + "               ON (part.ba_no) part.ba_no,\n"
							+ "                           b.mct_nomen AS broad_cat,\n" + "                           u.unit_name,\n"
							+ "                           bd.veh_cat,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                           CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                                               ELSE 'UNSV'\n" + "                                                   END AS serv_unsv,\n"
							+ "                           pa.classification AS classification,\n"
							+ "                           round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "       FROM tb_tms_census_retrn part\n" + "   INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "               ON part.ba_no = bd.ba_no\n" + "   INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "               ON u.status_sus_no = 'Active'\n" + "           AND part.sus_no = u.sus_no\n"
							+ "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(bd.mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'A'::text\n"
							+ "   left JOIN tb_tms_mvcr_parta_dtl AS pa\n" + "               ON pa.ba_no = bd.ba_no\n"
							+ "       LEFT JOIN tb_tms_mct_slot_master s\n" + "               ON s.prf_code = b.prf_code\n"
							+ "   WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n" + "           AND bd.veh_cat = 'A'   and part.ba_no   like ?   limit 10"
							;
				}
				if (type_of_veh.equals("B")) {

					q = " SELECT distinct on (part.ba_no) part.ba_no,\n" + "                           b.mct_nomen AS broad_cat,\n"
							+ "                           bd.veh_cat,\n" + "                           u.unit_name,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                           part.classification AS classification,\n"
							+ "                         CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\n"
							+ "         ELSE 'UNSV'\n" + "END AS serv_unsv,"
							+ "                           round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "\n" + "       FROM tb_tms_mvcr_parta_dtl part\n" + "   INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "               ON part.ba_no = bd.ba_no\n" + "   INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "               ON u.status_sus_no = 'Active'\n" + "           AND part.sus_no = u.sus_no\n"
							+ "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(mct::varchar,1,4) = b.mct_main_id\n"
							+ "               AND b.type_of_veh::text = 'B'::text\n" + "       LEFT JOIN tb_tms_mct_slot_master s\n"
							+ "               ON s.prf_code = b.prf_code\n"
							+ "           LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a\n"
							+ "               ON a.mct_main_id = b.mct_main_id\n" + "   WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n"
							+ "           AND bd.veh_cat = 'B'   and part.ba_no   like ?   GROUP BY 1,2,3,4,5,6,7, 8,9         "
							+ "   limit 10" ;
				}
				if (type_of_veh.equals("C")) {

					q = "SELECT DISTINCT\n" + "               ON (part.em_no) part.em_no AS ba_no,\n"
							+ "                           b.mct_nomen AS broad_cat,\n" + "                               bd.veh_cat,\n"
							+ "                           u.unit_name,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                               CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                                               ELSE 'UNSV'\n" + "                                                   END AS serv_unsv,\n"
							+ "                           round(part.current_km_run + ((part.current_km_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\n"
							+ "                           part.classification AS classification\n" + "       FROM tb_tms_emar_report part\n"
							+ "   INNER JOIN tb_tms_banum_dirctry bd\n" + "               ON part.em_no = bd.ba_no\n"
							+ "   INNER JOIN tb_miso_orbat_unt_dtl u\n" + "               ON u.status_sus_no = 'Active'\n"
							+ "           AND part.sus_no = u.sus_no\n" + "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(mct::varchar,1,4) = b.mct_main_id     AND b.type_of_veh::text = 'C'::text\n"
							+ "                       LEFT JOIN tb_tms_mct_slot_master s\n" + "               ON s.prf_code = b.prf_code\n"
							+ "   WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') and upper(part.em_no)   like ?   limit 10" ;
				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);



			}else{

				if (type_of_veh.equals("A")) {


					q = " SELECT DISTINCT\n" + "               ON (part.ba_no) part.ba_no,\n"
							+ "                           b.mct_nomen AS broad_cat,\n" + "                           u.unit_name,\n"
							+ "                           bd.veh_cat,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                           CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                                               ELSE 'UNSV'\n" + "                                                   END AS serv_unsv,\n"
							+ "                           pa.classification AS classification,\n"
							+ "                           round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "       FROM tb_tms_census_retrn part\n" + "   INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "               ON part.ba_no = bd.ba_no\n" + "   INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "               ON u.status_sus_no = 'Active'\n" + "           AND part.sus_no = u.sus_no\n"
							+ "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(bd.mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'A'::text\n"
							+ "   left JOIN tb_tms_mvcr_parta_dtl AS pa\n" + "               ON pa.ba_no = bd.ba_no\n"
							+ "       LEFT JOIN tb_tms_mct_slot_master s\n" + "               ON s.prf_code = b.prf_code\n"
							+ "   WHERE part.sus_no = ? \n" + "           AND bd.veh_cat = 'A'   and part.ba_no   like ?   limit 10"
							;
				}
				if (type_of_veh.equals("B")) {

					q = " SELECT distinct on (part.ba_no) part.ba_no,\n" + "                           b.mct_nomen AS broad_cat,\n"
							+ "                           bd.veh_cat,\n" + "                           u.unit_name,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                           part.classification AS classification,\n"
							+ "                         CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\n"
							+ "         ELSE 'UNSV'\n" + "END AS serv_unsv,"
							+ "                           round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "\n" + "       FROM tb_tms_mvcr_parta_dtl part\n" + "   INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "               ON part.ba_no = bd.ba_no\n" + "   INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "               ON u.status_sus_no = 'Active'\n" + "           AND part.sus_no = u.sus_no\n"
							+ "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(mct::varchar,1,4) = b.mct_main_id\n"
							+ "               AND b.type_of_veh::text = 'B'::text\n" + "       LEFT JOIN tb_tms_mct_slot_master s\n"
							+ "               ON s.prf_code = b.prf_code\n"
							+ "           LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a\n"
							+ "               ON a.mct_main_id = b.mct_main_id\n" + "   WHERE part.sus_no = ? \n"
							+ "           AND bd.veh_cat = 'B'   and part.ba_no   like ?   GROUP BY 1,2,3,4,5,6,7, 8,9         "
							+ "   limit 10" ;
				}
				if (type_of_veh.equals("C")) {

					q = "SELECT DISTINCT\n" + "               ON (part.em_no) part.em_no AS ba_no,\n"
							+ "                           b.mct_nomen AS broad_cat,\n" + "                               bd.veh_cat,\n"
							+ "                           u.unit_name,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                               CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                                               ELSE 'UNSV'\n" + "                                                   END AS serv_unsv,\n"
							+ "                           round(part.current_km_run + ((part.current_km_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\n"
							+ "                           part.classification AS classification\n" + "       FROM tb_tms_emar_report part\n"
							+ "   INNER JOIN tb_tms_banum_dirctry bd\n" + "               ON part.em_no = bd.ba_no\n"
							+ "   INNER JOIN tb_miso_orbat_unt_dtl u\n" + "               ON u.status_sus_no = 'Active'\n"
							+ "           AND part.sus_no = u.sus_no\n" + "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(mct::varchar,1,4) = b.mct_main_id     AND b.type_of_veh::text = 'C'::text\n"
							+ "                       LEFT JOIN tb_tms_mct_slot_master s\n" + "               ON s.prf_code = b.prf_code\n"
							+ "   WHERE part.sus_no = ? and upper(part.em_no)   like ?   limit 10" ;
				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}

			stmt.setString(2, "%"+broadcat.toUpperCase()+"%");

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			while (rs.next()) {
				FinalList.add(rs.getString(type));
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
		return FinalList;
	}




	@Override
	public List<String> tmsBrodecat( String roleSusNo,String type_of_veh,String broadcat,String type,String formationtype) {
		List<String> FinalList = new ArrayList<String>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;



			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);
				if (type_of_veh.equals("A")) {


					q = " SELECT DISTINCT\n" + "               ON (b.mct_nomen) b.mct_nomen AS broad_cat, part.ba_no,\n"
							+ "                             \n" + "                           u.unit_name,\n"
							+ "                           bd.veh_cat,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                           CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                                               ELSE 'UNSV'\n" + "                                                   END AS serv_unsv,\n"
							+ "                           pa.classification AS classification,\n"
							+ "                           round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "       FROM tb_tms_census_retrn part\n" + "   INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "               ON part.ba_no = bd.ba_no\n" + "   INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "               ON u.status_sus_no = 'Active'\n" + "           AND part.sus_no = u.sus_no\n"
							+ "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(bd.mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'A'::text\n"
							+ "   left JOIN tb_tms_mvcr_parta_dtl AS pa\n" + "               ON pa.ba_no = bd.ba_no\n"
							+ "       LEFT JOIN tb_tms_mct_slot_master s\n" + "               ON s.prf_code = b.prf_code\n"
							+ "   WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n" + "           AND bd.veh_cat = 'A' and       UPPER(s.group_name) like ?   limit 10"
							;
				}
				if (type_of_veh.equals("B")) {

					q = " SELECT distinct on (b.mct_nomen) b.mct_nomen AS broad_cat,part.ba_no,\n"
							+ "                           bd.veh_cat,\n" + "                           u.unit_name,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                           part.classification AS classification,\n"
							+ "                         CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\n"
							+ "         ELSE 'UNSV'\n" + "END AS serv_unsv,"
							+ "                           round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "\n" + "       FROM tb_tms_mvcr_parta_dtl part\n" + "   INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "               ON part.ba_no = bd.ba_no\n" + "   INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "               ON u.status_sus_no = 'Active'\n" + "           AND part.sus_no = u.sus_no\n"
							+ "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(mct::varchar,1,4) = b.mct_main_id\n"
							+ "               AND b.type_of_veh::text = 'B'::text\n" + "       LEFT JOIN tb_tms_mct_slot_master s\n"
							+ "               ON s.prf_code = b.prf_code\n"
							+ "           LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a\n"
							+ "               ON a.mct_main_id = b.mct_main_id\n" + "   WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n"
							+ "           AND bd.veh_cat = 'B' AND UPPER(s.group_name) like ?   GROUP BY 1,2,3,4,5,6,7, 8,9         "
							+ "   limit 10" ;
				}
				if (type_of_veh.equals("C")) {

					q = "SELECT DISTINCT\n" + "               ON (b.mct_nomen)   b.mct_nomen AS broad_cat,part.em_no AS ba_no,\n"
							+ "                         \n" + "                               bd.veh_cat,\n"
							+ "                           u.unit_name,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                               CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                                               ELSE 'UNSV'\n" + "                                                   END AS serv_unsv,\n"
							+ "                           round(part.current_km_run + ((part.current_km_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\n"
							+ "                           part.classification AS classification\n" + "       FROM tb_tms_emar_report part\n"
							+ "   INNER JOIN tb_tms_banum_dirctry bd\n" + "               ON part.em_no = bd.ba_no\n"
							+ "   INNER JOIN tb_miso_orbat_unt_dtl u\n" + "               ON u.status_sus_no = 'Active'\n"
							+ "           AND part.sus_no = u.sus_no\n" + "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(mct::varchar,1,4) = b.mct_main_id     AND b.type_of_veh::text = 'C'::text\n"
							+ "                       LEFT JOIN tb_tms_mct_slot_master s\n" + "               ON s.prf_code = b.prf_code\n"
							+ "   WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') AND   UPPER(s.group_name) like ?   limit 10" ;
				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);




			}else{

				if (type_of_veh.equals("A")) {


					q = " SELECT DISTINCT\n" + "               ON (b.mct_nomen) b.mct_nomen AS broad_cat, part.ba_no,\n"
							+ "                             \n" + "                           u.unit_name,\n"
							+ "                           bd.veh_cat,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                           CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                                               ELSE 'UNSV'\n" + "                                                   END AS serv_unsv,\n"
							+ "                           pa.classification AS classification,\n"
							+ "                           round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "       FROM tb_tms_census_retrn part\n" + "   INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "               ON part.ba_no = bd.ba_no\n" + "   INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "               ON u.status_sus_no = 'Active'\n" + "           AND part.sus_no = u.sus_no\n"
							+ "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(bd.mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'A'::text\n"
							+ "   left JOIN tb_tms_mvcr_parta_dtl AS pa\n" + "               ON pa.ba_no = bd.ba_no\n"
							+ "       LEFT JOIN tb_tms_mct_slot_master s\n" + "               ON s.prf_code = b.prf_code\n"
							+ "   WHERE part.sus_no = ? \n" + "           AND bd.veh_cat = 'A' and       UPPER(s.group_name) like ?   limit 10"
							;
				}
				if (type_of_veh.equals("B")) {

					q = " SELECT distinct on (b.mct_nomen) b.mct_nomen AS broad_cat,part.ba_no,\n"
							+ "                           bd.veh_cat,\n" + "                           u.unit_name,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                           part.classification AS classification,\n"
							+ "                         CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\n"
							+ "         ELSE 'UNSV'\n" + "END AS serv_unsv,"
							+ "                           round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "\n" + "       FROM tb_tms_mvcr_parta_dtl part\n" + "   INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "               ON part.ba_no = bd.ba_no\n" + "   INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "               ON u.status_sus_no = 'Active'\n" + "           AND part.sus_no = u.sus_no\n"
							+ "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(mct::varchar,1,4) = b.mct_main_id\n"
							+ "               AND b.type_of_veh::text = 'B'::text\n" + "       LEFT JOIN tb_tms_mct_slot_master s\n"
							+ "               ON s.prf_code = b.prf_code\n"
							+ "           LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a\n"
							+ "               ON a.mct_main_id = b.mct_main_id\n" + "   WHERE part.sus_no = ? \n"
							+ "           AND bd.veh_cat = 'B' AND UPPER(s.group_name) like ?   GROUP BY 1,2,3,4,5,6,7, 8,9         "
							+ "   limit 10" ;
				}
				if (type_of_veh.equals("C")) {

					q = "SELECT DISTINCT\n" + "               ON (b.mct_nomen)   b.mct_nomen AS broad_cat,part.em_no AS ba_no,\n"
							+ "                         \n" + "                               bd.veh_cat,\n"
							+ "                           u.unit_name,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                               CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                                               ELSE 'UNSV'\n" + "                                                   END AS serv_unsv,\n"
							+ "                           round(part.current_km_run + ((part.current_km_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\n"
							+ "                           part.classification AS classification\n" + "       FROM tb_tms_emar_report part\n"
							+ "   INNER JOIN tb_tms_banum_dirctry bd\n" + "               ON part.em_no = bd.ba_no\n"
							+ "   INNER JOIN tb_miso_orbat_unt_dtl u\n" + "               ON u.status_sus_no = 'Active'\n"
							+ "           AND part.sus_no = u.sus_no\n" + "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(mct::varchar,1,4) = b.mct_main_id     AND b.type_of_veh::text = 'C'::text\n"
							+ "                       LEFT JOIN tb_tms_mct_slot_master s\n" + "               ON s.prf_code = b.prf_code\n"
							+ "   WHERE part.sus_no = ? AND   UPPER(s.group_name) like ?   limit 10" ;
				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}


			stmt.setString(2, "%"+broadcat.toUpperCase()+"%");

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			while (rs.next()) {
				FinalList.add(rs.getString(type));
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
		return FinalList;
	}



	@Override
	public List<String> tmsveh_type( String roleSusNo,String type_of_veh,String broadcat,String type,String formationtype) {
		List<String> FinalList = new ArrayList<String>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;


			if(formationtype.equals("Brigade")) {
				String from_code_control= formation_code(roleSusNo);

				if (type_of_veh.equals("A")) {


					q = " SELECT DISTINCT\n" + "               ON (b.mct_nomen) b.mct_nomen AS broad_cat, part.ba_no,\n"
							+ "                             \n" + "                           u.unit_name,\n"
							+ "                           bd.veh_cat,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                           CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                                               ELSE 'UNSV'\n" + "                                                   END AS serv_unsv,\n"
							+ "                           pa.classification AS classification,\n"
							+ "                           round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "       FROM tb_tms_census_retrn part\n" + "   INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "               ON part.ba_no = bd.ba_no\n" + "   INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "               ON u.status_sus_no = 'Active'\n" + "           AND part.sus_no = u.sus_no\n"
							+ "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(bd.mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'A'::text\n"
							+ "   left JOIN tb_tms_mvcr_parta_dtl AS pa\n" + "               ON pa.ba_no = bd.ba_no\n"
							+ "       LEFT JOIN tb_tms_mct_slot_master s\n" + "               ON s.prf_code = b.prf_code\n"
							+ "   WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n" + "           AND bd.veh_cat = 'A'       and upper(b.mct_nomen) like ?   limit 10"
							;
				}
				if (type_of_veh.equals("B")) {

					q = " SELECT distinct on (b.mct_nomen) b.mct_nomen AS broad_cat,part.ba_no,\n"
							+ "                           bd.veh_cat,\n" + "                           u.unit_name,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                           part.classification AS classification,\n"
							+ "                         CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\n"
							+ "         ELSE 'UNSV'\n" + "END AS serv_unsv,"
							+ "                           round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "\n" + "       FROM tb_tms_mvcr_parta_dtl part\n" + "   INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "               ON part.ba_no = bd.ba_no\n" + "   INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "               ON u.status_sus_no = 'Active'\n" + "           AND part.sus_no = u.sus_no\n"
							+ "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(mct::varchar,1,4) = b.mct_main_id\n"
							+ "               AND b.type_of_veh::text = 'B'::text\n" + "       LEFT JOIN tb_tms_mct_slot_master s\n"
							+ "               ON s.prf_code = b.prf_code\n"
							+ "           LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a\n"
							+ "               ON a.mct_main_id = b.mct_main_id\n" + "   WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active') \n"
							+ "           AND bd.veh_cat = 'B'  and upper(b.mct_nomen) like ?   GROUP BY 1,2,3,4,5,6,7, 8,9         "
							+ "   limit 10" ;
				}
				if (type_of_veh.equals("C")) {

					q = "SELECT DISTINCT\n" + "               ON (b.mct_nomen)   b.mct_nomen AS broad_cat,part.em_no AS ba_no,\n"
							+ "                         \n" + "                               bd.veh_cat,\n"
							+ "                           u.unit_name,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                               CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                                               ELSE 'UNSV'\n" + "                                                   END AS serv_unsv,\n"
							+ "                           round(part.current_km_run + ((part.current_km_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\n"
							+ "                           part.classification AS classification\n" + "       FROM tb_tms_emar_report part\n"
							+ "   INNER JOIN tb_tms_banum_dirctry bd\n" + "               ON part.em_no = bd.ba_no\n"
							+ "   INNER JOIN tb_miso_orbat_unt_dtl u\n" + "               ON u.status_sus_no = 'Active'\n"
							+ "           AND part.sus_no = u.sus_no\n" + "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(mct::varchar,1,4) = b.mct_main_id     AND b.type_of_veh::text = 'C'::text\n"
							+ "                       LEFT JOIN tb_tms_mct_slot_master s\n" + "               ON s.prf_code = b.prf_code\n"
							+ "   WHERE part.sus_no in ( select sus_no from tb_miso_orbat_unt_dtl where form_code_control=? AND status_sus_no = 'Active')    and upper(b.mct_nomen) like ?   limit 10" ;
				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, from_code_control);


			}else{

				if (type_of_veh.equals("A")) {


					q = " SELECT DISTINCT\n" + "               ON (b.mct_nomen) b.mct_nomen AS broad_cat, part.ba_no,\n"
							+ "                             \n" + "                           u.unit_name,\n"
							+ "                           bd.veh_cat,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                           CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                                               ELSE 'UNSV'\n" + "                                                   END AS serv_unsv,\n"
							+ "                           pa.classification AS classification,\n"
							+ "                           round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "       FROM tb_tms_census_retrn part\n" + "   INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "               ON part.ba_no = bd.ba_no\n" + "   INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "               ON u.status_sus_no = 'Active'\n" + "           AND part.sus_no = u.sus_no\n"
							+ "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(bd.mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'A'::text\n"
							+ "   left JOIN tb_tms_mvcr_parta_dtl AS pa\n" + "               ON pa.ba_no = bd.ba_no\n"
							+ "       LEFT JOIN tb_tms_mct_slot_master s\n" + "               ON s.prf_code = b.prf_code\n"
							+ "   WHERE part.sus_no = ? \n" + "           AND bd.veh_cat = 'A'       and upper(b.mct_nomen) like ?   limit 10"
							;
				}
				if (type_of_veh.equals("B")) {

					q = " SELECT distinct on (b.mct_nomen) b.mct_nomen AS broad_cat,part.ba_no,\n"
							+ "                           bd.veh_cat,\n" + "                           u.unit_name,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                           part.classification AS classification,\n"
							+ "                         CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\n"
							+ "         ELSE 'UNSV'\n" + "END AS serv_unsv,"
							+ "                           round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage\n"
							+ "\n" + "       FROM tb_tms_mvcr_parta_dtl part\n" + "   INNER JOIN tb_tms_banum_dirctry bd\n"
							+ "               ON part.ba_no = bd.ba_no\n" + "   INNER JOIN tb_miso_orbat_unt_dtl u\n"
							+ "               ON u.status_sus_no = 'Active'\n" + "           AND part.sus_no = u.sus_no\n"
							+ "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(mct::varchar,1,4) = b.mct_main_id\n"
							+ "               AND b.type_of_veh::text = 'B'::text\n" + "       LEFT JOIN tb_tms_mct_slot_master s\n"
							+ "               ON s.prf_code = b.prf_code\n"
							+ "           LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a\n"
							+ "               ON a.mct_main_id = b.mct_main_id\n" + "   WHERE part.sus_no = ? \n"
							+ "           AND bd.veh_cat = 'B'  and upper(b.mct_nomen) like ?   GROUP BY 1,2,3,4,5,6,7, 8,9         "
							+ "   limit 10" ;
				}
				if (type_of_veh.equals("C")) {

					q = "SELECT DISTINCT\n" + "               ON (b.mct_nomen)   b.mct_nomen AS broad_cat,part.em_no AS ba_no,\n"
							+ "                         \n" + "                               bd.veh_cat,\n"
							+ "                           u.unit_name,\n" + "                           u.sus_no,\n"
							+ "                           s.group_name AS veh_type,\n"
							+ "                               CASE WHEN b.vehicle_class_code = 'S' THEN 'SERV'\n"
							+ "                                               ELSE 'UNSV'\n" + "                                                   END AS serv_unsv,\n"
							+ "                           round(part.current_km_run + ((part.current_km_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\n"
							+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.em_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\n"
							+ "                           part.classification AS classification\n" + "       FROM tb_tms_emar_report part\n"
							+ "   INNER JOIN tb_tms_banum_dirctry bd\n" + "               ON part.em_no = bd.ba_no\n"
							+ "   INNER JOIN tb_miso_orbat_unt_dtl u\n" + "               ON u.status_sus_no = 'Active'\n"
							+ "           AND part.sus_no = u.sus_no\n" + "   INNER JOIN tb_tms_mct_main_master b\n"
							+ "               ON substr(mct::varchar,1,4) = b.mct_main_id     AND b.type_of_veh::text = 'C'::text\n"
							+ "                       LEFT JOIN tb_tms_mct_slot_master s\n" + "               ON s.prf_code = b.prf_code\n"
							+ "   WHERE part.sus_no = ?    and upper(b.mct_nomen) like ?   limit 10" ;
				}

				stmt = conn.prepareStatement(q);
				stmt.setString(1, roleSusNo);

			}

			stmt.setString(2, "%"+broadcat.toUpperCase()+"%");

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			while (rs.next()) {
				FinalList.add(rs.getString(type));
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
		return FinalList;
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
	public long Get3008monthlyavg(String sus_no,HttpSession sessionUserId) {

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement stmt=null;


			String from_code_control= formation_code(sus_no);

			q = "SELECT round((sum(cnt.iaff3008) * 100) / nullif(sum(cnt.total_count), 0), 0) :: int AS avg_of3008\n"
					+ "  FROM (\n"
					+ "        SELECT 0 AS iaff3008,\n"
					+ "               count(id) AS total_count\n"
					+ "          FROM tb_miso_orbat_unt_dtl b\n"
					+ "         WHERE b.status_sus_no = 'Active'\n"
					+ "           AND b.form_code_control = ?\n"
					+ "     UNION ALL\n"
					+ "    SELECT DISTINCT count(id) AS iaff3008,\n"
					+ "               0 AS total_count\n"
					+ "          FROM tb_psg_iaff_3008_main dt\n"
					/*+ "         WHERE dt.month = to_char(CURRENT_DATE, 'FMMM')\n"
			          			+ "           AND dt.year = to_char(CURRENT_DATE, 'yyyy')\n"*/
			          			+ "			  WHERE dt.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
			          			+ "			  AND dt.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"
			          			+ "           AND status = 1\n"
			          			+ "           AND dt.sus_no IN (\n"
			          			+ "                SELECT sus_no\n"
			          			+ "                  FROM tb_miso_orbat_unt_dtl b\n"
			          			+ "                 WHERE b.status_sus_no = 'Active'\n"
			          			+ "                   AND b.form_code_control = ?\n"
			          			+ "               )\n"
			          			+ "       ) cnt";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, from_code_control);
			stmt.setString(2, from_code_control);



			//System.err.println("avg 3008 dash:\n "+ stmt);
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
	public long Get3009monthlyavg(String sus_no,HttpSession sessionUserId) {

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement stmt=null;


			String from_code_control= formation_code(sus_no);

			q = "SELECT round((sum(cnt.iaff3009) * 100) / nullif(sum(cnt.total_count), 0), 0) :: int AS avg_of3009\n"
					+ "  FROM (\n"
					+ "        SELECT 0 AS iaff3009,\n"
					+ "               count(id) AS total_count\n"
					+ "          FROM tb_miso_orbat_unt_dtl b\n"
					+ "         WHERE b.status_sus_no = 'Active'\n"
					+ "           AND b.form_code_control = ?\n"
					+ "     UNION ALL SELECT DISTINCT count(id) AS iaff3009,\n"
					+ "               0 AS total_count\n"
					+ "          FROM tb_psg_iaff_3009_main dt\n"
					/*+ "         WHERE dt.month = to_char(CURRENT_DATE, 'FMMM')\n"
			          			+ "           AND dt.year = to_char(CURRENT_DATE, 'YYYY')\n"*/
			          			+ "			  WHERE dt.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
			          			+ "			  AND dt.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"
			          			+ "           AND status = 1\n"
			          			+ "           AND dt.sus_no IN (\n"
			          			+ "                SELECT sus_no\n"
			          			+ "                  FROM tb_miso_orbat_unt_dtl b\n"
			          			+ "                 WHERE b.status_sus_no = 'Active'\n"
			          			+ "                   AND b.form_code_control = ?\n"
			          			+ "               )\n"
			          			+ "       ) cnt";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, from_code_control);
			stmt.setString(2, from_code_control);



			//System.err.println("avg 3009 dash:\n "+ stmt);
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
	public long itassetcount(String sus_no,HttpSession sessionUserId) {

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement stmt=null;


			String from_code_control= formation_code(sus_no);

			q = "  SELECT COALESCE(sum(a.computing),0) as it_asset\n"
					+ "  FROM (\n"
					+ "        SELECT count(id) AS computing\n"
					+ "          FROM it_asset_peripherals dt\n"
					+ "         WHERE to_char(dt.created_date, 'MM-YYYY') = to_char(CURRENT_DATE, 'MM-YYYY')\n"
					+ "           AND status = 1\n"
					+ "           AND dt.sus_no IN (\n"
					+ "                SELECT sus_no\n"
					+ "                  FROM tb_miso_orbat_unt_dtl b\n"
					+ "                 WHERE b.status_sus_no = 'Active'\n"
					+ "                   AND b.form_code_control = ?\n"
					+ "               )\n"
					+ "         GROUP BY dt.sus_no\n"
					+ "     UNION ALL SELECT count(id) AS computing\n"
					+ "          FROM tb_asset_main dt\n"
					+ "         WHERE to_char(dt.created_date, 'MM-YYYY') = to_char(CURRENT_DATE, 'MM-YYYY')\n"
					+ "           AND status = 1\n"
					+ "           AND dt.sus_no IN (\n"
					+ "                SELECT sus_no\n"
					+ "                  FROM tb_miso_orbat_unt_dtl b\n"
					+ "                 WHERE b.status_sus_no = 'Active'\n"
					+ "                   AND b.form_code_control = ?\n"
					+ "               )\n"
					+ "         GROUP BY dt.sus_no\n"
					+ "       )a";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, from_code_control);
			stmt.setString(2, from_code_control);

			//System.err.println("\n it count:9002 \n" + stmt);
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
	public long mcravg(String sus_no,HttpSession sessionUserId) {

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement stmt=null;


			String from_code_control= formation_code(sus_no);

			q = "  SELECT round(coalesce((sum(a.mcr_count) * 100) / nullif(sum(a.mcr_total_count), 0), 0), 0) AS wpn_eqpt_percentage\n"
					+ "  FROM (\n"
					+ " SELECT DISTINCT 0 AS mcr_count,\n"
					+ "               count(sus_no) AS mcr_total_count  \n"
					+ "              \n"
					+ "                  FROM tb_miso_orbat_unt_dtl b\n"
					+ "                 WHERE b.status_sus_no = 'Active'\n"
					+ "                   AND b.form_code_control = ?\n"
					+ "union all \n"
					+ "SELECT \n"
					+ "count(app.*) :: int as mcr_count,\n"
					+ "0 as mcr_total_count\n"
					+ "  FROM tb_miso_orbat_unt_dtl b\n"
					+ "  LEFT JOIN (\n"
					+ "        SELECT DISTINCT o.sus_no,\n"
					+ "               CASE WHEN p.status = 'APP' THEN p.status\n"
					+ "                    ELSE 'DEF'\n"
					+ "                     END AS unit_status\n"
					+ "          FROM tb_miso_orbat_unt_dtl o\n"
					+ "         INNER JOIN sus_weapon_wepe_with_wetpet c\n"
					+ "            ON c.sus_no = o.sus_no\n"
					+ "          LEFT JOIN all_fmn_view fmn\n"
					+ "            ON o.form_code_control = fmn.form_code_control\n"
					+ "          LEFT JOIN baseunits b\n"
					+ "            ON o.sus_no = b.b_sus_no\n"
					+ "          LEFT JOIN (\n"
					+ "                SELECT DISTINCT sus_no,\n"
					+ "                       'APP' AS status\n"
					+ "                  FROM mms_tb_unit_upload_document\n"
					+ "                 WHERE to_char(created_on,'YYYY-MM') = ltrim(to_char(now(),'YYYY-MM'),'0')\n"
					+ "                   AND doc IS NULL\n"
					+ "                 GROUP BY 1\n"
					+ "               ) p\n"
					+ "            ON o.sus_no = p.sus_no\n"
					+ "          LEFT JOIN (\n"
					+ "                SELECT sus_no,\n"
					+ "                       to_char(max(created_on),'dd-mm-yyyy') AS upd_date\n"
					+ "                  FROM mms_tb_unit_upload_document\n"
					+ "                 GROUP BY 1\n"
					+ "               ) last_update\n"
					+ "            ON o.sus_no = last_update.sus_no\n"
					+ "         WHERE o.status_sus_no = 'Active'\n"
					+ "       ) app\n"
					+ "    ON app.sus_no = b.sus_no\n"
					+ " WHERE b.sus_no IN (\n"
					+ "        SELECT sus_no\n"
					+ "          FROM tb_miso_orbat_unt_dtl\n"
					+ "         WHERE status_sus_no = 'Active'\n"
					+ "           AND form_code_control = ?\n"
					+ "       ) and app.unit_status = 'APP' and b.status_sus_no = 'Active')a\n"
					+ "";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, from_code_control);
			stmt.setString(2, from_code_control);


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
	public long GetAvehmonthlyavg(String sus_no,HttpSession sessionUserId) {

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement stmt=null;


			String from_code_control= formation_code(sus_no);

			q = "SELECT round((sum(cnt.Aveh) * 100) / nullif(sum(cnt.total_count), 0), 0) :: int AS avg_Aveg\n"
					+ "  FROM (\n"
					+ "        SELECT 0 AS Aveh,\n"
					+ "               count(distinct b.unit_name) AS total_count\n"
					+ "          FROM tb_miso_orbat_unt_dtl b\n"
					+ "inner join tb_tms_census_retrn dt on b.sus_no=dt.sus_no \n"
					+ "         WHERE b.status_sus_no = 'Active'\n"
					+ "           AND b.form_code_control = ?\n"
					+ "     UNION ALL SELECT DISTINCT count(distinct sus_no) AS Aveh,\n"
					+ "               0 AS total_count\n"
					+ "          FROM tb_tms_census_retrn dt\n"
					+ "         WHERE  ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(dt.approve_date,'mm-yyyy'),'0')\n"
					+ "           AND dt.status = '1'\n"
					+ "           AND dt.sus_no IN (\n"
					+ "                SELECT sus_no\n"
					+ "                  FROM tb_miso_orbat_unt_dtl b\n"
					+ "                 WHERE b.status_sus_no = 'Active'\n"
					+ "                   AND b.form_code_control = ?\n"
					+ "               )\n"
					+ "       ) cnt";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, from_code_control);
			stmt.setString(2, from_code_control);



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
	public long GetBvehmonthlyavg(String sus_no,HttpSession sessionUserId) {

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement stmt=null;


			String from_code_control= formation_code(sus_no);

			q = "SELECT round((sum(cnt.Bveh) * 100) / nullif(sum(cnt.total_count), 0), 0) :: int AS avg_Bveg\n"
					+ "  FROM (\n"
					+ "        SELECT 0 AS Bveh,\n"
					+ "               count(distinct b.unit_name) AS total_count\n"
					+ "          FROM tb_miso_orbat_unt_dtl b\n"
					+ "inner join tb_tms_mvcr_parta_dtl dt on b.sus_no=dt.sus_no \n"
					+ "         WHERE b.status_sus_no = 'Active'\n"
					+ "           AND b.form_code_control = ?\n"
					+ "     UNION ALL SELECT DISTINCT count(distinct sus_no) AS Bveh,\n"
					+ "               0 AS total_count\n"
					+ "          FROM tb_tms_mvcr_parta_dtl dt\n"
					+ "         WHERE  ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(dt.approve_date,'mm-yyyy'),'0')\n"
					+ "           AND dt.status = '1'\n"
					+ "           AND dt.sus_no IN (\n"
					+ "                SELECT sus_no\n"
					+ "                  FROM tb_miso_orbat_unt_dtl b\n"
					+ "                 WHERE b.status_sus_no = 'Active'\n"
					+ "                   AND b.form_code_control = ?\n"
					+ "               )\n"
					+ "       ) cnt";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, from_code_control);
			stmt.setString(2, from_code_control);



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
	public long GetCvehmonthlyavg(String sus_no,HttpSession sessionUserId) {

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement stmt=null;


			String from_code_control= formation_code(sus_no);

			q = "SELECT round((sum(cnt.Cveh) * 100) / nullif(sum(cnt.total_count), 0), 0) :: int AS avg_Cveg\n"
					+ "  FROM (\n"
					+ "        SELECT 0 AS Cveh,\n"
					+ "               count(distinct b.unit_name) AS total_count\n"
					+ "          FROM tb_miso_orbat_unt_dtl b\n"
					+ "inner join tb_tms_emar_report dt on b.sus_no=dt.sus_no \n"
					+ "         WHERE b.status_sus_no = 'Active'\n"
					+ "           AND b.form_code_control = ?\n"
					+ "     UNION ALL SELECT DISTINCT count(distinct sus_no) AS Cveh,\n"
					+ "               0 AS total_count\n"
					+ "          FROM tb_tms_emar_report dt\n"
					+ "         WHERE  ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(cast(dt.approve_date AS date),'mm-yyyy'),'0')\n"
					+ "           AND dt.status = '1'\n"
					+ "           AND dt.sus_no IN (\n"
					+ "                SELECT sus_no\n"
					+ "                  FROM tb_miso_orbat_unt_dtl b\n"
					+ "                 WHERE b.status_sus_no = 'Active'\n"
					+ "                   AND b.form_code_control = ?\n"
					+ "               )\n"
					+ "       ) cnt";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, from_code_control);
			stmt.setString(2, from_code_control);



			System.err.println("c veh " + stmt);
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



	//3008 current month
	@Override
	public long get3008CurrentMonthlistCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo) {


		int total = 0;
		String q = null;
		Connection conn = null;
		PreparedStatement stmt =null;
		try {
			conn = dataSource.getConnection();
			String SearchValue = "";

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "upper(b.unit_name) like ?   " ;
			}



			String from_code_control= formation_code(roleSusNo);
			q = "select count(app.*) from ( SELECT\n"
					+ "	b.sus_no,\n"
					+ "	b.unit_name,\n"
					+ "	COALESCE(\n"
					+ "		(\n"
					+ "			SELECT\n"
					+ "				CASE\n"
					+ "					WHEN c.status=0\n"
					+ "					OR c.status IS NULL THEN 'yet to update'\n"
					+ "					WHEN c.status=1 THEN 'updated'\n"
					+ "					ELSE 'yet to update'\n"
					+ "				END\n"
					+ "			FROM\n"
					+ "				tb_psg_iaff_3008_main c\n"
					+ "			WHERE\n"
					+ "				c.sus_no=b.sus_no\n"
					/*+ "				AND c.month=TO_CHAR(\n"
			              		+ "					CURRENT_DATE,\n"
			              		+ "					'FMMM'\n"
			              		+ "				) AND c.year=to_CHAR(CURRENT_DATE,'yyyy')\n"*/
			              		+ "				AND c.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
			              		+ "				AND c.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"

			              		+ "		),\n"
			              		+ "		'yet to update'\n"
			              		+ "	) AS status\n"
			              		+ "FROM\n"
			              		+ "	tb_miso_orbat_unt_dtl b\n"
			              		+ "WHERE\n"
			              		+ "	b.status_sus_no='Active'\n"
			              		+ "	AND b.form_code_control=?  "+SearchValue+" )as app\n"
			              		+ "\n"
			              		+ "";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);


			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
			//System.err.println("3008 datalist count\n "+ stmt);
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
	public List<Map<String, Object>> get3008CurrentMonthlist(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo) {
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
			String SearchValue = "";
			String qry = "";


			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "upper(B.UNIT_NAME) like ? ";
			}

			String from_code_control= formation_code(roleSusNo);


			q = "SELECT\n"
					+ "	b.sus_no,\n"
					+ "	b.unit_name,\n"
					+ "	COALESCE(\n"
					+ "		(\n"
					+ "			SELECT\n"
					+ "				CASE\n"
					+ "					WHEN c.status=0\n"
					+ "					OR c.status IS NULL THEN 'yet to update'\n"
					+ "					WHEN c.status=1 THEN 'updated'\n"
					+ "					ELSE 'yet to update'\n"
					+ "				END\n"
					+ "			FROM\n"
					+ "				tb_psg_iaff_3008_main c\n"
					+ "			WHERE\n"
					+ "				c.sus_no=b.sus_no\n"
					+ "				AND c.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
					+ "				AND c.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"
					+ "		),\n"
					+ "		'yet to update'\n"
					+ "	) AS status\n"
					+ "FROM\n"
					+ "	tb_miso_orbat_unt_dtl b\n"
					+ "WHERE\n"
					+ "	b.status_sus_no='Active'\n"
					+ "	AND b.form_code_control=? "+SearchValue+" "
					+ "limit " + pageL + " OFFSET " + startPage + "";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);

			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag,"%" + Search.toUpperCase() + "%");
			}
			//System.err.println("3008 datalist \n "+ stmt);
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



	//3009 current month
	@Override
	public long get3009CurrentMonthlistCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo) {


		int total = 0;
		String q = null;
		Connection conn = null;
		PreparedStatement stmt =null;
		try {
			conn = dataSource.getConnection();
			String SearchValue = "";

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "upper(b.unit_name) like ?   " ;
			}



			String from_code_control= formation_code(roleSusNo);
			q = "select count(app.*) from ( SELECT\n"
					+ "	b.sus_no,\n"
					+ "	b.unit_name,\n"
					+ "	COALESCE(\n"
					+ "		(\n"
					+ "			SELECT\n"
					+ "				CASE\n"
					+ "					WHEN c.status=0\n"
					+ "					OR c.status IS NULL THEN 'yet to update'\n"
					+ "					WHEN c.status=1 THEN 'updated'\n"
					+ "					ELSE 'yet to update'\n"
					+ "				END\n"
					+ "			FROM\n"
					+ "				tb_psg_iaff_3009_main c\n"
					+ "			WHERE\n"
					+ "				c.sus_no=b.sus_no\n"
					+ "				AND c.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
					+ "				AND c.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"
					+ "		),\n"
					+ "		'yet to update'\n"
					+ "	) AS status\n"
					+ "FROM\n"
					+ "	tb_miso_orbat_unt_dtl b\n"
					+ "WHERE\n"
					+ "	b.status_sus_no='Active'\n"
					+ "	AND b.form_code_control=?  "+SearchValue+" )as app\n"
					+ "\n"
					+ "";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);


			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
			//System.err.println("3009 datalist count\n "+ stmt);
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
	public List<Map<String, Object>> get3009CurrentMonthlist(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo) {
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
			String SearchValue = "";
			String qry = "";


			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "upper(B.UNIT_NAME) like ? ";
			}

			String from_code_control= formation_code(roleSusNo);


			q = "SELECT\n"
					+ "	b.sus_no,\n"
					+ "	b.unit_name,\n"
					+ "	COALESCE(\n"
					+ "		(\n"
					+ "			SELECT\n"
					+ "				CASE\n"
					+ "					WHEN c.status=0\n"
					+ "					OR c.status IS NULL THEN 'yet to update'\n"
					+ "					WHEN c.status=1 THEN 'updated'\n"
					+ "					ELSE 'yet to update'\n"
					+ "				END\n"
					+ "			FROM\n"
					+ "				tb_psg_iaff_3009_main c\n"
					+ "			WHERE\n"
					+ "				c.sus_no=b.sus_no\n"
					+ "				AND c.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
					+ "				AND c.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"
					+ "		),\n"
					+ "		'yet to update'\n"
					+ "	) AS status\n"
					+ "FROM\n"
					+ "	tb_miso_orbat_unt_dtl b\n"
					+ "WHERE\n"
					+ "	b.status_sus_no='Active'\n"
					+ "	AND b.form_code_control=? "+SearchValue+" "
					+ "limit " + pageL + " OFFSET " + startPage + "";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);

			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag,"%" + Search.toUpperCase() + "%");
			}

			//System.err.println("3009 datalist \n "+ stmt);
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



	//Mcr current month
	@Override
	public long getMcrCurrentMonthlistCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo) {


		int total = 0;
		String q = null;
		Connection conn = null;
		PreparedStatement stmt =null;
		try {
			conn = dataSource.getConnection();
			String SearchValue = "";

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "upper(b.unit_name) like ?   " ;
			}



			String from_code_control= formation_code(roleSusNo);
			q = " select count(app.*) from ( SELECT DISTINCT b.sus_no,\n"
					+ " b.unit_name,\n"
					+ "       CASE WHEN app.unit_status = 'DEF' THEN 'yet to update'\n"
					+ "            WHEN app.unit_status = 'APP' THEN 'updated'\n"
					+ "            WHEN app.unit_status IS NULL THEN 'yet to update'\n"
					+ "             END AS status\n"
					+ "  FROM tb_miso_orbat_unt_dtl b\n"
					+ "  LEFT JOIN (\n"
					+ "        SELECT DISTINCT o.sus_no,\n"
					+ "               CASE WHEN p.status = 'APP' THEN p.status\n"
					+ "                    ELSE 'DEF'\n"
					+ "                     END AS unit_status\n"
					+ "          FROM tb_miso_orbat_unt_dtl o\n"
					+ "         INNER JOIN sus_weapon_wepe_with_wetpet c\n"
					+ "            ON c.sus_no = o.sus_no\n"
					+ "          LEFT JOIN all_fmn_view fmn\n"
					+ "            ON o.form_code_control = fmn.form_code_control\n"
					+ "          LEFT JOIN baseunits b\n"
					+ "            ON o.sus_no = b.b_sus_no\n"
					+ "          LEFT JOIN (\n"
					+ "                SELECT DISTINCT sus_no,\n"
					+ "                       'APP' AS status\n"
					+ "                  FROM mms_tb_unit_upload_document\n"
					+ "                 WHERE to_char(created_on,'YYYY-MM') = ltrim(to_char(now(),'YYYY-MM'),'0')\n"
					+ "                   AND doc IS NULL\n"
					+ "                 GROUP BY 1\n"
					+ "               ) p\n"
					+ "            ON o.sus_no = p.sus_no\n"
					+ "          LEFT JOIN (\n"
					+ "                SELECT sus_no,\n"
					+ "                       to_char(max(created_on),'dd-mm-yyyy') AS upd_date\n"
					+ "                  FROM mms_tb_unit_upload_document\n"
					+ "                 GROUP BY 1\n"
					+ "               ) last_update\n"
					+ "            ON o.sus_no = last_update.sus_no\n"
					+ "         WHERE o.status_sus_no = 'Active'\n"
					+ "       ) app\n"
					+ "    ON app.sus_no = b.sus_no\n"
					+ " WHERE b.sus_no IN (\n"
					+ "        SELECT sus_no\n"
					+ "          FROM tb_miso_orbat_unt_dtl\n"
					+ "         WHERE status_sus_no = 'Active'\n"
					+ "           AND form_code_control = ?\n"
					+ "       )  and b.status_sus_no='Active' "+SearchValue+ ") as app";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);


			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
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
	public List<Map<String, Object>> getMcrCurrentMonthlist(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo) {
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
			String SearchValue = "";
			String qry = "";


			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "upper(B.UNIT_NAME) like ? ";
			}

			String from_code_control= formation_code(roleSusNo);


			q = " SELECT DISTINCT b.sus_no,\n"
					+ " b.unit_name,\n"
					+ "       CASE WHEN app.unit_status = 'DEF' THEN 'yet to update'\n"
					+ "            WHEN app.unit_status = 'APP' THEN 'updated'\n"
					+ "            WHEN app.unit_status IS NULL THEN 'yet to update'\n"
					+ "             END AS status\n"
					+ "  FROM tb_miso_orbat_unt_dtl b\n"
					+ "  LEFT JOIN (\n"
					+ "        SELECT DISTINCT o.sus_no,\n"
					+ "               CASE WHEN p.status = 'APP' THEN p.status\n"
					+ "                    ELSE 'DEF'\n"
					+ "                     END AS unit_status\n"
					+ "          FROM tb_miso_orbat_unt_dtl o\n"
					+ "         INNER JOIN sus_weapon_wepe_with_wetpet c\n"
					+ "            ON c.sus_no = o.sus_no\n"
					+ "          LEFT JOIN all_fmn_view fmn\n"
					+ "            ON o.form_code_control = fmn.form_code_control\n"
					+ "          LEFT JOIN baseunits b\n"
					+ "            ON o.sus_no = b.b_sus_no\n"
					+ "          LEFT JOIN (\n"
					+ "                SELECT DISTINCT sus_no,\n"
					+ "                       'APP' AS status\n"
					+ "                  FROM mms_tb_unit_upload_document\n"
					+ "                 WHERE to_char(created_on,'YYYY-MM') = ltrim(to_char(now(),'YYYY-MM'),'0')\n"
					+ "                   AND doc IS NULL\n"
					+ "                 GROUP BY 1\n"
					+ "               ) p\n"
					+ "            ON o.sus_no = p.sus_no\n"
					+ "          LEFT JOIN (\n"
					+ "                SELECT sus_no,\n"
					+ "                       to_char(max(created_on),'dd-mm-yyyy') AS upd_date\n"
					+ "                  FROM mms_tb_unit_upload_document\n"
					+ "                 GROUP BY 1\n"
					+ "               ) last_update\n"
					+ "            ON o.sus_no = last_update.sus_no\n"
					+ "         WHERE o.status_sus_no = 'Active'\n"
					+ "       ) app\n"
					+ "    ON app.sus_no = b.sus_no\n"
					+ " WHERE b.sus_no IN (\n"
					+ "        SELECT sus_no\n"
					+ "          FROM tb_miso_orbat_unt_dtl\n"
					+ "         WHERE status_sus_no = 'Active'\n"
					+ "           AND form_code_control =?\n"
					+ "       )  and b.status_sus_no='Active' "+SearchValue+""
					+ "limit " + pageL + " OFFSET " + startPage + "";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);

			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag,"%" + Search.toUpperCase() + "%");
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


	//IT Assets(holding as on date)
	@Override
	public long IT_Assets_holding_as_on_dateCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo) {


		int total = 0;
		String q = null;
		Connection conn = null;
		PreparedStatement stmt =null;
		try {
			conn = dataSource.getConnection();
			String SearchValue = "";

			if (Search != "") {
				SearchValue = " where   ";
				SearchValue += "upper(a.unit_name) like ?   " ;
			}



			String from_code_control= formation_code(roleSusNo);
			q = " select count(app.*) from (\n"
					+ "SELECT\n"
					+ "	a.sus_no,\n"
					+ "	a.unit_name,\n"
					+ "	SUM(a.computing) AS computing,\n"
					+ "	SUM(a.peripheral) AS peripheral\n"
					+ "FROM\n"
					+ "	(\n"
					+ "		SELECT\n"
					+ "			b.sus_no,\n"
					+ "			b.unit_name,\n"
					+ "				(SELECT COUNT(	a.sus_no	)\n"
					+ "				FROM\n"
					+ "					it_asset_peripherals a \n"


									//+ "              INNER JOIN tb_mstr_assets bb ON a.assets_type = bb.id AND bb.category = 2\n"
									//+ "				 inner join  tb_mstr_make mm on  mm.id=a.make_name \n"
									//+ "              inner join  tb_mstr_model m on  m.id=a.model_name \n"

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
									//+ "				 inner join  tb_mstr_make mm on  mm.id=a.make_name \n"
									//+ "				 inner join  tb_mstr_model m on  m.id=a.model_name  \n"
									//+ "				 inner join  tb_mstr_budget y on  y.id::text=a.b_code\n"

									+ "				WHERE\n"
									+ "					a.status=1 and a.s_state='1'\n"
									+ "					AND a.sus_no = b.sus_no\n"
									+ "			) AS computing \n"
									+ "		FROM\n"
									+ "			tb_miso_orbat_unt_dtl b\n"
									+ "		WHERE\n"
									+ "			b.status_sus_no='Active'\n"
									+ "			AND b.form_code_control=?\n"
									+ "	) a  "+SearchValue+" group by a.sus_no ,a.unit_name)app";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);
			stmt.setString(2, from_code_control);


			int flag = 2;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
			//System.err.println("\n it datalist count:9748 \n" + stmt);
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
	public List<Map<String, Object>> IT_Assets_holding_as_on_dateList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo) {
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
			String SearchValue = "";
			String qry = "";


			if (Search != "") {
				SearchValue = " where ";
				SearchValue += "upper(a.UNIT_NAME) like ? ";
			}

			String from_code_control= formation_code(roleSusNo);


			q = " SELECT\n"
					+ "	a.sus_no,\n"
					+ "	a.unit_name,\n"
					+ "	SUM(a.computing) AS computing,\n"
					+ "	SUM(a.peripheral) AS peripheral\n"
					+ "FROM\n"
					+ "	(\n"
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
										+ "		FROM\n"
										+ "			tb_miso_orbat_unt_dtl b\n"
										+ "		WHERE\n"
										+ "			b.status_sus_no='Active'\n"
										+ "			AND b.form_code_control=?\n"
										+ "	) a "+SearchValue+"  group by a.sus_no ,a.unit_name \n"
										+ " limit " + pageL + " OFFSET " + startPage + "";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);
			stmt.setString(2, from_code_control);

			int flag = 2;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag,"%" + Search.toUpperCase() + "%");
			}
			//System.err.println("\n it datalist :9856 \n" + stmt);
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


	//A veh current month
	@Override
	public long getAvehcurrentMonthCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo) {


		int total = 0;
		String q = null;
		Connection conn = null;
		PreparedStatement stmt =null;
		try {
			conn = dataSource.getConnection();
			String SearchValue = "";

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "upper(b.unit_name) like ?   " ;
			}



			String from_code_control= formation_code(roleSusNo);
			q = " select count(app.*) from (SELECT b.sus_no,\n"
					+ "       b.unit_name,\n"
					+ "       CASE \n"
					+ "           WHEN NOT EXISTS (\n"
					+ "               SELECT 1 \n"
					+ "               FROM tb_tms_census_retrn_main c \n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "           ) THEN 'NA'\n"
					+ "           ELSE COALESCE ( (\n"
					+ "               SELECT \n"
					+ "                   CASE \n"
					+ "                       WHEN c.status = '1' THEN 'updated'\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\n"
					+ "                   END\n"
					+ "               FROM tb_tms_census_retrn_main c\n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "               AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(c.approve_date,'mm-yyyy'),'0')),'yet to update'\n"
					+ "           )\n"
					+ "       END AS status\n"
					+ "FROM tb_miso_orbat_unt_dtl b\n"
					+ "WHERE b.status_sus_no = 'Active'\n"
					+ "  AND b.form_code_control = ?  "+SearchValue+")app";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);



			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
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
	public List<Map<String, Object>> getAvehcurrentMonthList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo) {
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
			String SearchValue = "";
			String qry = "";


			if (Search != "") {
				SearchValue = " and ";
				SearchValue += "upper(b.UNIT_NAME) like ? ";
			}

			String from_code_control= formation_code(roleSusNo);


			q = " SELECT b.sus_no,\n"
					+ "       b.unit_name,\n"
					+ "       CASE \n"
					+ "           WHEN NOT EXISTS (\n"
					+ "               SELECT 1 \n"
					+ "               FROM tb_tms_census_retrn d  \n"
					+ "               WHERE d.sus_no = b.sus_no\n"
					+ "           ) THEN 'NA'\n"
					+ "           ELSE COALESCE ( (\n"
					+ "               SELECT \n"
					+ "                   CASE \n"
					+ "                       WHEN c.status = '1' THEN 'updated'\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\n"
					+ "                   END\n"
					+ "               FROM tb_tms_census_retrn_main c\n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "               AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(c.approve_date,'mm-yyyy'),'0')),'yet to update'\n"
					+ "           )\n"
					+ "       END AS status\n"
					+ "FROM tb_miso_orbat_unt_dtl b\n"
					+ "WHERE b.status_sus_no = 'Active'\n"
					+ "  AND b.form_code_control = ?  "+SearchValue+" \n"
					+ " limit " + pageL + " OFFSET " + startPage + "";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);


			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag,"%" + Search.toUpperCase() + "%");
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

	//b veh current month
	@Override
	public long getBvehcurrentMonthCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo) {


		int total = 0;
		String q = null;
		Connection conn = null;
		PreparedStatement stmt =null;
		try {
			conn = dataSource.getConnection();
			String SearchValue = "";

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "upper(b.unit_name) like ?   " ;
			}



			String from_code_control= formation_code(roleSusNo);
			q = " select count(app.*) from (SELECT b.sus_no,\n"
					+ "       b.unit_name,\n"
					+ "       CASE \n"
					+ "           WHEN NOT EXISTS (\n"
					+ "               SELECT 1 \n"
					+ "               FROM tb_tms_mvcr_parta_main c \n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "           ) THEN 'NA'\n"
					+ "           ELSE COALESCE ( (\n"
					+ "               SELECT \n"
					+ "                   CASE \n"
					+ "                       WHEN c.status = '1' THEN 'updated'\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\n"
					+ "                   END\n"
					+ "               FROM tb_tms_mvcr_parta_main c\n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "               AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(c.approve_date,'mm-yyyy'),'0')),'yet to update'\n"
					+ "           )\n"
					+ "       END AS status\n"
					+ "FROM tb_miso_orbat_unt_dtl b\n"
					+ "WHERE b.status_sus_no = 'Active'\n"
					+ "  AND b.form_code_control = ?  "+SearchValue+")app";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);



			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
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
	public List<Map<String, Object>> getBvehcurrentMonthList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo) {
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
			String SearchValue = "";
			String qry = "";


			if (Search != "") {
				SearchValue = " and ";
				SearchValue += "upper(b.UNIT_NAME) like ? ";
			}

			String from_code_control= formation_code(roleSusNo);


			q = " SELECT b.sus_no,\n"
					+ "       b.unit_name,\n"
					+ "       CASE \n"
					+ "           WHEN NOT EXISTS (\n"
					+ "               SELECT 1 \n"
					+ "               FROM tb_tms_mvcr_parta_dtl d \n"
					+ "               WHERE d.sus_no = b.sus_no\n"
					+ "           ) THEN 'NA'\n"
					+ "           ELSE COALESCE ( (\n"
					+ "               SELECT \n"
					+ "                   CASE \n"
					+ "                       WHEN c.status = '1' THEN 'updated'\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\n"
					+ "                   END\n"
					+ "               FROM tb_tms_mvcr_parta_main c\n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "               AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(c.approve_date,'mm-yyyy'),'0')),'yet to update'\n"
					+ "           )\n"
					+ "       END AS status\n"
					+ "FROM tb_miso_orbat_unt_dtl b\n"
					+ "WHERE b.status_sus_no = 'Active'\n"
					+ "  AND b.form_code_control = ?  "+SearchValue+" \n"
					+ " limit " + pageL + " OFFSET " + startPage + "";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);


			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag,"%" + Search.toUpperCase() + "%");
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



	//c veh current month
	@Override
	public long getCvehcurrentMonthCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo) {


		int total = 0;
		String q = null;
		Connection conn = null;
		PreparedStatement stmt =null;
		try {
			conn = dataSource.getConnection();
			String SearchValue = "";

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "upper(b.unit_name) like ?   " ;
			}



			String from_code_control= formation_code(roleSusNo);
			q = " select count(app.*) from (SELECT b.sus_no,\n"
					+ "       b.unit_name,\n"
					+ "       CASE \n"
					+ "           WHEN NOT EXISTS (\n"
					+ "               SELECT 1 \n"
					+ "               FROM tb_tms_emar_report_main c \n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "           ) THEN 'NA'\n"
					+ "           ELSE COALESCE ( (\n"
					+ "               SELECT \n"
					+ "                   CASE \n"
					+ "                       WHEN c.status = '1' THEN 'updated'\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\n"
					+ "                   END\n"
					+ "               FROM tb_tms_emar_report_main c\n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "                 AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(cast(c.approve_date AS date),'mm-yyyy'),'0')),'yet to update'\n"
					+ "           )\n"
					+ "       END AS status\n"
					+ "FROM tb_miso_orbat_unt_dtl b\n"
					+ "WHERE b.status_sus_no = 'Active'\n"
					+ "  AND b.form_code_control = ?  "+SearchValue+")app";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);



			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
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
	public List<Map<String, Object>> getCvehcurrentMonthList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo) {
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
			String SearchValue = "";
			String qry = "";


			if (Search != "") {
				SearchValue = " and ";
				SearchValue += "upper(b.UNIT_NAME) like ? ";
			}

			String from_code_control= formation_code(roleSusNo);


			q = " SELECT b.sus_no,\n"
					+ "       b.unit_name,\n"
					+ "       CASE \n"
					+ "           WHEN NOT EXISTS (\n"
					+ "               SELECT 1 \n"
					+ "               FROM tb_tms_emar_report d \n"
					+ "               WHERE d.sus_no = b.sus_no\n"
					+ "           ) THEN 'NA'\n"
					+ "           ELSE COALESCE ( (\n"
					+ "               SELECT \n"
					+ "                   CASE \n"
					+ "                       WHEN c.status = '1' THEN 'updated'\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\n"
					+ "                   END\n"
					+ "               FROM tb_tms_emar_report_main c\n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "                AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(cast(c.approve_date AS date),'mm-yyyy'),'0')),'yet to update'\n"
					+ "           )\n"
					+ "       END AS status\n"
					+ "FROM tb_miso_orbat_unt_dtl b\n"
					+ "WHERE b.status_sus_no = 'Active'\n"
					+ "  AND b.form_code_control =? "+SearchValue+""
					+ " limit " + pageL + " OFFSET " + startPage + "";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);


			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag,"%" + Search.toUpperCase() + "%");
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


	//View All current month
	@Override
	public long getAllCasualtyCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String roleSusNo) {


		int total = 0;
		String q = null;
		Connection conn = null;
		PreparedStatement stmt =null;
		try {
			conn = dataSource.getConnection();
			String SearchValue = "";

			if (Search != "") {
				SearchValue = " and   ";
				SearchValue += "upper(b.unit_name) like ?   " ;
			}



			String from_code_control= formation_code(roleSusNo);
			q = " select count(app.*) from (SELECT\n"
					+ "	b.sus_no,\n"
					+ "	b.unit_name,\n"
					+ "	COALESCE(\n"
					+ "		(\n"
					+ "			SELECT\n"
					+ "				CASE\n"
					+ "					WHEN c.status=0\n"
					+ "					OR c.status IS NULL THEN 'yet to update'\n"
					+ "					WHEN c.status=1 THEN 'updated'\n"
					+ "					ELSE 'yet to update'\n"
					+ "				END\n"
					+ "			FROM\n"
					+ "				tb_psg_iaff_3008_main c\n"
					+ "			WHERE\n"
					+ "				c.sus_no=b.sus_no\n"
					+ "				AND c.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
					+ "				AND c.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"
					+ "		),\n"
					+ "		'yet to update'\n"
					+ "	) AS status_3008,\n"
					+ "		COALESCE(\n"
					+ "		(\n"
					+ "			SELECT\n"
					+ "				CASE\n"
					+ "					WHEN c.status=0\n"
					+ "					OR c.status IS NULL THEN 'yet to update'\n"
					+ "					WHEN c.status=1 THEN 'updated'\n"
					+ "					ELSE 'yet to update'\n"
					+ "				END\n"
					+ "			FROM\n"
					+ "				tb_psg_iaff_3009_main c\n"
					+ "			WHERE\n"
					+ "				c.sus_no=b.sus_no\n"
					+ "				AND c.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
					+ "				AND c.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"
					+ "		),\n"
					+ "		'yet to update'\n"
					+ "	) AS status_3009,\n"
					+ "	    CASE \n"
					+ "           WHEN NOT EXISTS (\n"
					+ "               SELECT 1 \n"
					+ "               FROM tb_tms_census_retrn_main c \n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "           ) THEN 'NA'\n"
					+ "           ELSE COALESCE ( (\n"
					+ "               SELECT \n"
					+ "                   CASE \n"
					+ "                       WHEN c.status = '1' THEN 'updated'\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\n"
					+ "                   END\n"
					+ "               FROM tb_tms_census_retrn_main c\n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "               AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(c.approve_date,'mm-yyyy'),'0')),'yet to update'\n"
					+ "           )\n"
					+ "       END AS aveh_status,\n"
					+ "			    CASE \n"
					+ "           WHEN NOT EXISTS (\n"
					+ "               SELECT 1 \n"
					+ "               FROM tb_tms_mvcr_parta_main c \n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "           ) THEN 'NA'\n"
					+ "           ELSE COALESCE ( (\n"
					+ "               SELECT \n"
					+ "                   CASE \n"
					+ "                       WHEN c.status = '1' THEN 'updated'\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\n"
					+ "                   END\n"
					+ "               FROM tb_tms_mvcr_parta_main c\n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "               AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(c.approve_date,'mm-yyyy'),'0')),'yet to update'\n"
					+ "           )\n"
					+ "       END AS bveh_status,\n"
					+ "			    CASE \n"
					+ "           WHEN NOT EXISTS (\n"
					+ "               SELECT 1 \n"
					+ "               FROM tb_tms_emar_report_main c \n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "           ) THEN 'NA'\n"
					+ "           ELSE COALESCE ( (\n"
					+ "               SELECT \n"
					+ "                   CASE \n"
					+ "                       WHEN c.status = '1' THEN 'updated'\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\n"
					+ "                   END\n"
					+ "               FROM tb_tms_emar_report_main c\n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "                AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(cast(c.approve_date AS date),'mm-yyyy'),'0')),'yet to update'\n"
					+ "           )\n"
					+ "       END AS cveh_status,\n"
					+ "			 (\n"
					+ "				SELECT COUNT(	sus_no	)\n"
					+ "				FROM\n"
					+ "					it_asset_peripherals\n"
					+ "				WHERE\n"
					+ "					status=1\n"
					+ "					AND sus_no=b.sus_no\n"
					+ "			) AS peripheral ,\n"
					+ "				(\n"
					+ "				SELECT COUNT(	sus_no)\n"
					+ "				FROM\n"
					+ "					tb_asset_main\n"
					+ "				WHERE\n"
					+ "					status=1\n"
					+ "					AND sus_no=b.sus_no\n"
					+ "			) AS computing,\n"
					+ "			   (SELECT\n"
					+ "        CASE \n"
					+ "            WHEN MAX(app.unit_status) = 'DEF' THEN 'yet to update'\n"
					+ "            WHEN MAX(app.unit_status) = 'APP' THEN 'updated'\n"
					+ "            WHEN MAX(app.unit_status) IS NULL THEN 'yet to update'\n"
					+ "        END AS status\n"
					+ "    FROM tb_miso_orbat_unt_dtl c\n"
					+ "    LEFT JOIN (\n"
					+ "        SELECT DISTINCT o.sus_no,\n"
					+ "               CASE WHEN p.status = 'APP' THEN p.status\n"
					+ "                    ELSE 'DEF'\n"
					+ "               END AS unit_status\n"
					+ "        FROM tb_miso_orbat_unt_dtl o\n"
					+ "        INNER JOIN sus_weapon_wepe_with_wetpet c ON c.sus_no = o.sus_no\n"
					+ "        LEFT JOIN all_fmn_view fmn ON o.form_code_control = fmn.form_code_control\n"
					+ "        LEFT JOIN baseunits b ON o.sus_no = b.b_sus_no\n"
					+ "        LEFT JOIN (\n"
					+ "            SELECT DISTINCT sus_no,\n"
					+ "                   'APP' AS status\n"
					+ "            FROM mms_tb_unit_upload_document\n"
					+ "            WHERE to_char(created_on,'YYYY-MM') = ltrim(to_char(now(),'YYYY-MM'),'0')\n"
					+ "              AND doc IS NULL\n"
					+ "            GROUP BY 1\n"
					+ "        ) p ON o.sus_no = p.sus_no\n"
					+ "        LEFT JOIN (\n"
					+ "            SELECT sus_no,\n"
					+ "                   to_char(max(created_on),'dd-mm-yyyy') AS upd_date\n"
					+ "            FROM mms_tb_unit_upload_document\n"
					+ "            GROUP BY 1\n"
					+ "        ) last_update ON o.sus_no = last_update.sus_no\n"
					+ "        WHERE o.status_sus_no = 'Active'\n"
					+ "    ) app ON app.sus_no = c.sus_no\n"
					+ "    WHERE c.sus_no = b.sus_no\n"
					+ "    GROUP BY c.sus_no) AS mcr_status\n"
					+ "		\n"
					+ "FROM\n"
					+ "	tb_miso_orbat_unt_dtl b\n"
					+ "WHERE\n"
					+ "	b.status_sus_no='Active'\n"
					+ "	AND b.form_code_control=? "+SearchValue+")app";


			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);



			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			//System.err.println(" combine count:\n "+ stmt);
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
	public List<Map<String, Object>> getAllCasualtyList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo) {
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
			String SearchValue = "";
			String qry = "";


			if (Search != "") {
				SearchValue = " and ";
				SearchValue += "upper(b.UNIT_NAME) like ? ";
			}

			String from_code_control= formation_code(roleSusNo);


			q = " SELECT\n"
					+ "	b.sus_no,\n"
					+ "	b.unit_name,\n"
					+ "	COALESCE(\n"
					+ "		(\n"
					+ "			SELECT\n"
					+ "				CASE\n"
					+ "					WHEN c.status=0\n"
					+ "					OR c.status IS NULL THEN 'yet to update'\n"
					+ "					WHEN c.status=1 THEN 'updated'\n"
					+ "					ELSE 'yet to update'\n"
					+ "				END\n"
					+ "			FROM\n"
					+ "				tb_psg_iaff_3008_main c\n"
					+ "			WHERE\n"
					+ "				c.sus_no=b.sus_no\n"
					+ "				AND c.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
					+ "				AND c.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"
					+ "		),\n"
					+ "		'yet to update'\n"
					+ "	) AS status_3008,\n"
					+ "		COALESCE(\n"
					+ "		(\n"
					+ "			SELECT\n"
					+ "				CASE\n"
					+ "					WHEN c.status=0\n"
					+ "					OR c.status IS NULL THEN 'yet to update'\n"
					+ "					WHEN c.status=1 THEN 'updated'\n"
					+ "					ELSE 'yet to update'\n"
					+ "				END\n"
					+ "			FROM\n"
					+ "				tb_psg_iaff_3009_main c\n"
					+ "			WHERE\n"
					+ "				c.sus_no=b.sus_no\n"
					+ "				AND c.month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'FMMM')\n"
					+ "				AND c.year = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month', 'yyyy')\n"
					+ "		),\n"
					+ "		'yet to update'\n"
					+ "	) AS status_3009,\n"
					+ "	    CASE \n"
					+ "           WHEN NOT EXISTS (\n"
					+ "               SELECT 1 \n"
					+ "               FROM tb_tms_census_retrn_main c \n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "           ) THEN 'NA'\n"
					+ "           ELSE COALESCE ( (\n"
					+ "               SELECT \n"
					+ "                   CASE \n"
					+ "                       WHEN c.status = '1' THEN 'updated'\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\n"
					+ "                   END\n"
					+ "               FROM tb_tms_census_retrn_main c\n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "               AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(c.approve_date,'mm-yyyy'),'0')),'yet to update'\n"
					+ "           )\n"
					+ "       END AS aveh_status,\n"
					+ "			    CASE \n"
					+ "           WHEN NOT EXISTS (\n"
					+ "               SELECT 1 \n"
					+ "               FROM tb_tms_mvcr_parta_main c \n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "           ) THEN 'NA'\n"
					+ "           ELSE COALESCE ( (\n"
					+ "               SELECT \n"
					+ "                   CASE \n"
					+ "                       WHEN c.status = '1' THEN 'updated'\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\n"
					+ "                   END\n"
					+ "               FROM tb_tms_mvcr_parta_main c\n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "               AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(c.approve_date,'mm-yyyy'),'0')),'yet to update'\n"
					+ "           )\n"
					+ "       END AS bveh_status,\n"
					+ "			    CASE \n"
					+ "           WHEN NOT EXISTS (\n"
					+ "               SELECT 1 \n"
					+ "               FROM tb_tms_emar_report_main c \n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "           ) THEN 'NA'\n"
					+ "           ELSE COALESCE ( (\n"
					+ "               SELECT \n"
					+ "                   CASE \n"
					+ "                       WHEN c.status = '1' THEN 'updated'\n"
					+ "                       WHEN c.status = '0' OR c.status IS NULL THEN 'yet to update'\n"
					+ "                   END\n"
					+ "               FROM tb_tms_emar_report_main c\n"
					+ "               WHERE c.sus_no = b.sus_no\n"
					+ "                AND ltrim(to_char(now(),'mm-yyyy'),'0') = ltrim(to_char(cast(c.approve_date AS date),'mm-yyyy'),'0')),'yet to update'\n"
					+ "           )\n"
					+ "       END AS cveh_status,\n"
					+ "			 (\n"
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
												+ "			   (SELECT\n"
												+ "        CASE \n"
												+ "            WHEN MAX(app.unit_status) = 'DEF' THEN 'yet to update'\n"
												+ "            WHEN MAX(app.unit_status) = 'APP' THEN 'updated'\n"
												+ "            WHEN MAX(app.unit_status) IS NULL THEN 'yet to update'\n"
												+ "        END AS status\n"
												+ "    FROM tb_miso_orbat_unt_dtl c\n"
												+ "    LEFT JOIN (\n"
												+ "        SELECT DISTINCT o.sus_no,\n"
												+ "               CASE WHEN p.status = 'APP' THEN p.status\n"
												+ "                    ELSE 'DEF'\n"
												+ "               END AS unit_status\n"
												+ "        FROM tb_miso_orbat_unt_dtl o\n"
												+ "        INNER JOIN sus_weapon_wepe_with_wetpet c ON c.sus_no = o.sus_no\n"
												+ "        LEFT JOIN all_fmn_view fmn ON o.form_code_control = fmn.form_code_control\n"
												+ "        LEFT JOIN baseunits b ON o.sus_no = b.b_sus_no\n"
												+ "        LEFT JOIN (\n"
												+ "            SELECT DISTINCT sus_no,\n"
												+ "                   'APP' AS status\n"
												+ "            FROM mms_tb_unit_upload_document\n"
												+ "            WHERE to_char(created_on,'YYYY-MM') = ltrim(to_char(now(),'YYYY-MM'),'0')\n"
												+ "              AND doc IS NULL\n"
												+ "            GROUP BY 1\n"
												+ "        ) p ON o.sus_no = p.sus_no\n"
												+ "        LEFT JOIN (\n"
												+ "            SELECT sus_no,\n"
												+ "                   to_char(max(created_on),'dd-mm-yyyy') AS upd_date\n"
												+ "            FROM mms_tb_unit_upload_document\n"
												+ "            GROUP BY 1\n"
												+ "        ) last_update ON o.sus_no = last_update.sus_no\n"
												+ "        WHERE o.status_sus_no = 'Active'\n"
												+ "    ) app ON app.sus_no = c.sus_no\n"
												+ "    WHERE c.sus_no = b.sus_no\n"
												+ "    GROUP BY c.sus_no) AS mcr_status\n"
												+ "		\n"
												+ "FROM\n"
												+ "	tb_miso_orbat_unt_dtl b\n"
												+ "WHERE\n"
												+ "	b.status_sus_no='Active'\n"
												+ "	AND b.form_code_control=?  "+SearchValue+""
												+ " limit " + pageL + " OFFSET " + startPage + "";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, from_code_control);


			int flag = 1;
			if (Search != "") {
				flag += 1;
				stmt.setString(flag,"%" + Search.toUpperCase() + "%");
			}

			//System.err.println(" combine list:\n "+ stmt);
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

	//			end	 pranay 15.07 bde dashboard
	
	
	public List<Map<String, Object>> getLastUpdateDate(HttpSession session,String roleSusNo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String from_code_control="";
		String roleAccess = session.getAttribute("roleAccess").toString();
		
		Connection conn = null;
		String q = "";
		String qry=null;
		
		if(roleAccess.equals("Unit")) {
			qry = "AND b.sus_no=?";
		}else {
			from_code_control= formation_code(roleSusNo);
			qry = "AND b.form_code_control=?";
		}

		try {		conn = dataSource.getConnection();
			PreparedStatement stmt = null;	


			 
			q = " SELECT\r\n"
					+ "	b.sus_no,\r\n"
					+ "	b.unit_name,\r\n"
					+ "(\r\n"
					+ "	select\r\n"
					+ "		case\r\n"
					+ "			when modified_date is null then to_char(created_date,'dd-mm-yyyy')\r\n"
					+ "			else to_char(modified_date,'dd-mm-yyyy')\r\n"
					+ "		end\r\n"
					+ "	from\r\n"
					+ "		tb_psg_iaff_3009_main m\r\n"
					+ "	where\r\n"
					+ "		status=1\r\n"
					+ "		and m.sus_no=b.sus_no\r\n"
					+ "		order by id desc\r\n"
					+ "        FETCH FIRST 1 ROW ONLY \r\n"
					+ ") as date_3009,\r\n"
					+ "\r\n"
					+ "(\r\n"
					+ "	select\r\n"
					+ "		case\r\n"
					+ "			when modified_date is null then to_char(created_date,'dd-mm-yyyy')\r\n"
					+ "			else to_char(modified_date,'dd-mm-yyyy')\r\n"
					+ "		end\r\n"
					+ "	from\r\n"
					+ "		tb_psg_iaff_3008_main m\r\n"
					+ "	where\r\n"
					+ "		status=1\r\n"
					+ "		and m.sus_no=b.sus_no\r\n"
					+ "		order by id desc\r\n"
					+ "        FETCH FIRST 1 ROW ONLY \r\n"
					+ ") as date_3008,\r\n"
					+ "	CASE \r\n"
					+ "    WHEN NOT EXISTS (\r\n"
					+ "        SELECT 1 \r\n"
					+ "        FROM tb_tms_census_retrn c \r\n"
					+ "        WHERE c.sus_no = b.sus_no\r\n"
					+ "    ) THEN 'NA'\r\n"
					+ "    ELSE (\r\n"
					+ "        SELECT \r\n"
					+ "            to_char(c.approve_date, 'dd-mm-yyyy')\r\n"
					+ "        FROM tb_tms_census_retrn c\r\n"
					+ "        WHERE c.sus_no = b.sus_no\r\n"
					+ "				order by id desc\r\n"
					+ "        FETCH FIRST 1 ROW ONLY  \r\n"
					+ "    )\r\n"
					+ "     END AS aveh_date,\r\n"
					+ "			    CASE \r\n"
					+ "           WHEN NOT EXISTS (\r\n"
					+ "               SELECT 1 \r\n"
					+ "               FROM tb_tms_mvcr_parta_dtl c \r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "           ) THEN 'NA'\r\n"
					+ "           ELSE  (\r\n"
					+ "               SELECT \r\n"
					+ "                    to_char(c.approve_date, 'dd-mm-yyyy')\r\n"
					+ "               FROM tb_tms_mvcr_parta_dtl c\r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "             order by id desc\r\n"
					+ "        FETCH FIRST 1 ROW ONLY \r\n"
					+ "           )\r\n"
					+ "       END AS bveh_date,\r\n"
					+ "			    CASE \r\n"
					+ "           WHEN NOT EXISTS (\r\n"
					+ "               SELECT 1 \r\n"
					+ "               FROM tb_tms_emar_report c \r\n"
					+ "               WHERE c.sus_no = b.sus_no\r\n"
					+ "           ) THEN 'NA'\r\n"
					+ "           ELSE (\r\n"
					+ "               SELECT \r\n"
					+ "                  to_char(cast(c.approve_date AS date),'dd-mm-yyyy')\r\n"
					+ "               FROM tb_tms_emar_report c\r\n"
					+ "               WHERE c.sus_no = b.sus_no"
					+ "					order by id desc\r\n"  
					+ "        FETCH FIRST 1 ROW ONLY \r\n"
					+ "           )\r\n"
					+ "       END AS cveh_date,\r\n"
					+ "			 ( select to_char(max(a.date),'dd-mm-yyyy') from (\r\n"
					+ "				SELECT a.created_date as date\r\n"
					+ "				FROM\r\n"
					+ "					it_asset_peripherals a \r\n"
					+ "				 inner join  tb_mstr_make mm on  mm.id=a.make_name \r\n"
					+ "        inner join  tb_mstr_model m on  m.id=a.model_name \r\n"
					+ "				WHERE\r\n"
					+ "					a.status=1 and a.s_state='1'\r\n"
					+ "					AND a.sus_no=b.sus_no\r\n"
					+ "\r\n"
					+ "					union \r\n"
					+ "         SELECT a.created_date as date\r\n"
					+ "				FROM\r\n"
					+ "					tb_asset_main a \r\n"
					+ "				 inner join  tb_mstr_make mm on  mm.id=a.make_name \r\n"
					+ "				 inner join  tb_mstr_model m on  m.id=a.model_name  \r\n"
					+ "				 inner join  tb_mstr_budget y on  y.id::text=a.b_code\r\n"
					+ "				WHERE\r\n"
					+ "					a.status=1 and a.s_state='1'\r\n"
					+ "					AND a.sus_no = b.sus_no) a\r\n"
					+ "					\r\n"
					+ "			) AS it_asset_date,\r\n"
					+ "			\r\n"
					+ "			   ( SELECT DISTINCT \r\n"
					+ "    to_char(max(m.created_on), 'dd-mm-yyyy'::text)AS upd_date\r\n"
					+ "   FROM orbat_all_units_view o\r\n"
					+ "     RIGHT JOIN sus_weapon_wepe_with_wetpet c ON c.sus_no::text = o.unit_sus::text AND o.status_sus_no::text = 'Active'::text\r\n"
					+ "     LEFT JOIN mms_tb_unit_upload_document m ON m.sus_no::text = o.unit_sus::text\r\n"
					+ "     LEFT JOIN mms_summary_ue_uh muh ON muh.unit_sus::text = m.sus_no::text where   o.unit_sus =b.sus_no\r\n"
					+ "  GROUP BY o.unit_sus, muh.uh) AS mcr_date\r\n"
					+ "		\r\n"
					+ "FROM\r\n"
					+ "	tb_miso_orbat_unt_dtl b\r\n"
					+ "WHERE\r\n"
					+ "	b.status_sus_no='Active'\r\n"
					+ "	"+qry+"  ";

			stmt = conn.prepareStatement(q);
			
			if(roleAccess.equals("Unit")) {
				stmt.setString(1, roleSusNo);
			}else {
				stmt.setString(1, from_code_control);
			}
			
			System.err.println("date last upadte:\n"+stmt);
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
	
	public int getPostCount(HttpSession session, String formCodeControl) {
		int postCount = 0; // Initialize the count
		String from_code_control = "";
		String roleAccess = session.getAttribute("roleAccess").toString();

		Connection conn = null;
		String q = "";
		String qry = null;

//		from_code_control = formation_code(formCodeControl);
		qry = "orb.form_code_control LIKE ?";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "SELECT COUNT(*) AS post_out FROM tb_psg_posting_in_out WHERE from_sus_no IN ("
					+ "SELECT orb.sus_no FROM tb_miso_orbat_unt_dtl orb "
					+ "INNER JOIN tb_miso_orbat_codesform c ON orb.sus_no = c.sus_no "
					+ "WHERE "+ qry + " AND orb.status_sus_no = 'Active') "
					+ "AND TO_CHAR(created_date, 'MM-YYYY') = TO_CHAR(CURRENT_DATE, 'MM-YYYY') ";

			stmt = conn.prepareStatement(q);
			  stmt.setString(1, formCodeControl + "%");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				postCount = rs.getInt("post_out");
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
		return postCount; // Return the count
	}
	
	public int getPostCount_in(HttpSession session, String formCodeControl) {
		int postCount = 0; // Initialize the count
		String from_code_control = "";
		String roleAccess = session.getAttribute("roleAccess").toString();

		Connection conn = null;
		String q = "";
		String qry = null;

//		from_code_control = formation_code(formCodeControl);
		qry = "orb.form_code_control LIKE ?";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "SELECT COUNT(*) AS in_out FROM tb_psg_posting_in_out WHERE to_sus_no IN ("
					+ "SELECT orb.sus_no FROM tb_miso_orbat_unt_dtl orb "
					+ "INNER JOIN tb_miso_orbat_codesform c ON orb.sus_no = c.sus_no "
					+ "WHERE "+ qry + " AND orb.status_sus_no = 'Active') "
					+ "AND TO_CHAR(created_date, 'MM-YYYY') = TO_CHAR(CURRENT_DATE, 'MM-YYYY') ";

			stmt = conn.prepareStatement(q);
			  stmt.setString(1, formCodeControl + "%");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				postCount = rs.getInt("in_out");
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
		return postCount; // Return the count
	}
	
	public int getPostCount_in_jco(HttpSession session, String formCodeControl) {
		int postCount = 0; // Initialize the count
		String from_code_control = "";
		String roleAccess = session.getAttribute("roleAccess").toString();

		Connection conn = null;
		String q = "";
		String qry = null;

//		from_code_control = formation_code(formCodeControl);
		qry = "orb.form_code_control LIKE ?";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "SELECT COUNT(*) AS in_jco FROM tb_psg_posting_in_out_jco WHERE to_sus_no IN ("
					+ "SELECT orb.sus_no FROM tb_miso_orbat_unt_dtl orb "
					+ "INNER JOIN tb_miso_orbat_codesform c ON orb.sus_no = c.sus_no "
					+ "WHERE "+ qry + " AND orb.status_sus_no = 'Active') "
					+ "AND TO_CHAR(created_date, 'MM-YYYY') = TO_CHAR(CURRENT_DATE, 'MM-YYYY') ";

			stmt = conn.prepareStatement(q);
			  stmt.setString(1, formCodeControl + "%");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				postCount = rs.getInt("in_jco");
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
		return postCount; // Return the count
	}
	
	public int getPostCount_out_jco(HttpSession session, String formCodeControl) {
		int postCount = 0; // Initialize the count
		String from_code_control = "";
		String roleAccess = session.getAttribute("roleAccess").toString();

		Connection conn = null;
		String q = "";
		String qry = null;

//		from_code_control = formation_code(formCodeControl);
		qry = "orb.form_code_control LIKE ?";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "SELECT COUNT(*) AS out_jco FROM tb_psg_posting_in_out_jco WHERE from_sus_no IN ("
					+ "SELECT orb.sus_no FROM tb_miso_orbat_unt_dtl orb "
					+ "INNER JOIN tb_miso_orbat_codesform c ON orb.sus_no = c.sus_no "
					+ "WHERE "+ qry + " AND orb.status_sus_no = 'Active') "
					+ "AND TO_CHAR(created_date, 'MM-YYYY') = TO_CHAR(CURRENT_DATE, 'MM-YYYY') ";

			stmt = conn.prepareStatement(q);
			  stmt.setString(1, formCodeControl + "%");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				postCount = rs.getInt("out_jco");
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
		return postCount; // Return the count
	}
	
	public int getPostCount_in_civ(HttpSession session, String formCodeControl) {
		int postCount = 0; // Initialize the count
		String from_code_control = "";
		String roleAccess = session.getAttribute("roleAccess").toString();

		Connection conn = null;
		String q = "";
		String qry = null;

//		from_code_control = formation_code(formCodeControl);
		qry = "orb.form_code_control LIKE ?";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "SELECT COUNT(*) AS in_civ FROM tb_psg_posting_in_out_civilian WHERE to_sus_no IN ("
					+ "SELECT orb.sus_no FROM tb_miso_orbat_unt_dtl orb "
					+ "INNER JOIN tb_miso_orbat_codesform c ON orb.sus_no = c.sus_no "
					+ "WHERE "+ qry + " AND orb.status_sus_no = 'Active') "
					+ "AND TO_CHAR(created_date, 'MM-YYYY') = TO_CHAR(CURRENT_DATE, 'MM-YYYY') ";

			stmt = conn.prepareStatement(q);
			  stmt.setString(1, formCodeControl + "%");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				postCount = rs.getInt("in_civ");
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
		return postCount; // Return the count
	}
	
	public int getPostCount_out_civ(HttpSession session, String formCodeControl) {
		int postCount = 0; // Initialize the count
		String from_code_control = "";
		String roleAccess = session.getAttribute("roleAccess").toString();

		Connection conn = null;
		String q = "";
		String qry = null;

//		from_code_control = formation_code(formCodeControl);
		qry = "orb.form_code_control LIKE ?";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "SELECT COUNT(*) AS out_civ FROM tb_psg_posting_in_out_civilian WHERE from_sus_no IN ("
					+ "SELECT orb.sus_no FROM tb_miso_orbat_unt_dtl orb "
					+ "INNER JOIN tb_miso_orbat_codesform c ON orb.sus_no = c.sus_no "
					+ "WHERE "+ qry + " AND orb.status_sus_no = 'Active') "
					+ "AND TO_CHAR(created_date, 'MM-YYYY') = TO_CHAR(CURRENT_DATE, 'MM-YYYY') ";

			stmt = conn.prepareStatement(q);
			  stmt.setString(1, formCodeControl + "%");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				postCount = rs.getInt("out_civ");
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
		return postCount; // Return the count
	}


}
