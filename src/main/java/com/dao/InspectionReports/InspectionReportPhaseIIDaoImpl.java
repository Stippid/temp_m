package com.dao.InspectionReports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.model.InspectionReports.TB_MISO_INSP_CRITICAL_DEFI_MANPOWER;
import com.model.InspectionReports.TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL;
import com.model.InspectionReports.TB_MISO_INSP_MAIN_PHASE_II_TBL;
import com.model.InspectionReports.TB_MISO_INSP_STATEOFWEAPONS_EQU;

public class InspectionReportPhaseIIDaoImpl implements InspectionReportPhaseIIDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	String currenYear = yearFormat.format(new Date());

	@Override
	public List<TB_MISO_INSP_MAIN_PHASE_II_TBL> getinsp_main_table_data_2(String sus_no, String currenYear) {
		List<TB_MISO_INSP_MAIN_PHASE_II_TBL> generList = new ArrayList<>();
		String sql = "SELECT * " + "FROM tb_miso_insp_main_phase_ii_tbl " + "WHERE sus_no = ? AND year = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear); // Use the parameter value, not a class field

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_MISO_INSP_MAIN_PHASE_II_TBL inspMainTbl = new TB_MISO_INSP_MAIN_PHASE_II_TBL();
					inspMainTbl.setId(rs.getInt("id")); // Replace "id" with the actual column name
					inspMainTbl.setSus_no(rs.getString("sus_no"));
					inspMainTbl.setOp_preparedness_item(rs.getInt("op_preparedness_item"));
					inspMainTbl.setTraining_item(rs.getInt("training_item"));
					inspMainTbl.setState_weapon_item(rs.getInt("state_weapon_item"));
					inspMainTbl.setState_personnel_item(rs.getInt("state_personnel_item"));
					inspMainTbl.setAdministation_item(rs.getInt("administation_item"));
					inspMainTbl.setAspect_item(rs.getInt("aspect_item"));
					inspMainTbl.setInterior_item(rs.getInt("interior_item"));

					inspMainTbl.setMajor_achievements_item(rs.getInt("major_achievements_item"));
					inspMainTbl.setStrength_of_unit_item(rs.getInt("strength_of_unit_item"));
					inspMainTbl.setChallenges_item(rs.getInt("challenges_item"));
					inspMainTbl.setImprove_following_item(rs.getInt("improve_following_item"));
					inspMainTbl.setAttention_of_higher_item(rs.getInt("attention_of_higher_item"));
					inspMainTbl.setMitigation_item(rs.getInt("mitigation_item"));
					inspMainTbl.setPoints_discussion_item(rs.getInt("points_discussion_item"));

					inspMainTbl.setStatus(rs.getInt("status"));
					inspMainTbl.setYear(rs.getString("year"));

					// Extract data from the ResultSet and populate the TB_MISO_INSP_MAIN_PHASE_II_TBL object
					generList.add(inspMainTbl);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving summary tech other data: " + e.getMessage());
			e.printStackTrace();
		}

		return generList;
	}

	@Override
	public List<TB_MISO_INSP_STATEOFWEAPONS_EQU> getStateOfWeaponEqu(String sus_no) {
		List<TB_MISO_INSP_STATEOFWEAPONS_EQU> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			conn = dataSource.getConnection();

			sql = "select id,nature_deficiency,action_deficiency,effect_conduct,remarks from\n" + "	tb_miso_insp_stateofweapons_equ  "
					+ " where sus_no=?  and year=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_MISO_INSP_STATEOFWEAPONS_EQU list = new TB_MISO_INSP_STATEOFWEAPONS_EQU();
				list.setNature_deficiency(rs.getString("nature_deficiency"));
				list.setAction_deficiency(rs.getString("action_deficiency"));
				list.setEffect_conduct(rs.getString("effect_conduct"));
				list.setRemarks(rs.getString("remarks"));
				list.setId(rs.getInt("id"));

				generList.add(list);
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

		return generList;
	}

	@Override
	public List<TB_MISO_INSP_CRITICAL_DEFI_MANPOWER> getCriticalDefiManpower(String sus_no) {
		List<TB_MISO_INSP_CRITICAL_DEFI_MANPOWER> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			conn = dataSource.getConnection();

			sql = "select id,manpower_deficiencyoffrs,manpower_deficiencyjco,manpower_deficiencyor,action_taken,"
					+ "training_effect,remarks from\n" + "	TB_MISO_INSP_CRITICAL_DEFI_MANPOWER "
					+ " where sus_no=?  and year=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_MISO_INSP_CRITICAL_DEFI_MANPOWER list = new TB_MISO_INSP_CRITICAL_DEFI_MANPOWER();
				list.setManpower_deficiencyoffrs(rs.getString("manpower_deficiencyoffrs"));
				list.setManpower_deficiencyjco(rs.getString("manpower_deficiencyjco"));
				list.setManpower_deficiencyor(rs.getString("manpower_deficiencyor"));
				list.setAction_taken(rs.getString("action_taken"));
				list.setTraining_effect(rs.getString("training_effect"));
				list.setRemarks(rs.getString("remarks"));
				list.setId(rs.getInt("id"));

				generList.add(list);
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

		return generList;
	}

	@Override
	public List<TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL> getDisciplineCourtMaritalDisposal(String sus_no) {
		List<TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			conn = dataSource.getConnection();

			sql = "select id,ongoing_count,pending_cases,cases_current,remarks from\n" + "	TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL "
					+ " where sus_no=?  and year=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL list = new TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL();
				list.setOngoing_count(rs.getString("ongoing_count"));
				list.setPending_cases(rs.getString("pending_cases"));
				list.setCases_current(rs.getString("cases_current"));
				list.setRemarks(rs.getString("remarks"));
				list.setId(rs.getInt("id"));

				generList.add(list);
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

		return generList;
	}


	@Override
	public Map<String, String> getphase2data(HttpSession session,String  roleSusNo) {
		Map<String, String> resultMap = new HashMap<>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

		//	String roleSusNo = session.getAttribute("roleSusNo").toString();

			sql = " select\r\n"
					+ "    id,\r\n"
					+ "    op_preparedness_item,\r\n"
					+ "    training_item,\r\n"
					+ "    state_weapon_item,\r\n"
					+ "	   state_weapon_item_ii,\r\n"
					+ "    state_personnel_item,\r\n"
					+ "    administation_item,\r\n"
					+ "    aspect_item,\r\n"
					+ "    interior_item,\r\n"
					+ "    major_achievements_item,\r\n"
					+ "    strength_of_unit_item,\r\n"
					+ "    challenges_item,\r\n"
					+ "    improve_following_item,\r\n"
					+ "    attention_of_higher_item,\r\n"
					+ "    mitigation_item,\r\n"
					+ "    points_discussion_item,\r\n"
					+ "    created_date,\r\n"
					+ "    created_by,\r\n"
					+ "    year,\r\n"
					+ "    sus_no,\r\n"
					+ "    status\r\n"
					+ "from\r\n"
					+ "    tb_miso_insp_phaseii_tbl where sus_no=? and year=? ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roleSusNo);
			stmt.setString(2, currenYear);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				// Add your mapping logic here
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					String value = rs.getObject(i) != null ? rs.getObject(i).toString() : "";
					resultMap.put(columnName, value);
				}
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
					// Handle exception silently
				}
			}
		}
		return resultMap;
	}


	@Override
	public Map<String, String> getdataForDigitalDigitalSign(HttpSession session,String  roleSusNo) {
		Map<String, String> resultMap = new HashMap<>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			//String roleSusNo = session.getAttribute("roleSusNo").toString();
			String username = (String) session.getAttribute("username");

			sql = " select distinct\r\n"
					+ "	        		 	tr.personnel_no,\r\n"
					+ "	        		 	tr.name,\r\n"
					+ "	        		 	rk.description as rank,UPPER(\r\n"
					+ "	        		 		am.description\r\n"
					+ "	        		 	) as appointment\r\n"
					+ "	        		 from\r\n"
					+ "	        		 	logininformation l\r\n"
					+ "	        		 	inner join tb_psg_trans_proposed_comm_letter tr on tr.personnel_no=l.army_no\r\n"
					+ "	        		 	INNER JOIN cue_tb_psg_rank_app_master rk ON rk.id=tr.rank\r\n"
					+ "	        		 	and UPPER(\r\n"
					+ "	        		 		rk.level_in_hierarchy\r\n"
					+ "	        		 	)='RANK'\r\n"
					+ "	        		 	left join tb_psg_change_of_appointment ap\r\n"
					+ "	        		 	on ap.comm_id = tr.id\r\n"
					+ "	        		 	left join cue_tb_psg_rank_app_master am \r\n"
					+ "	        		 	on am.id = ap.appointment\r\n"
					+ "	        		 	and UPPER(\r\n"
					+ "	        		 		am.level_in_hierarchy\r\n"
					+ "	        		 	)='APPOINTMENT'\r\n"
					+ "	        		 	and am.parent_code='0'\r\n"
					+ "	        		 	and am.status_active='Active'\r\n"
					+ "	        		 where\r\n"
					+ "	        		 l.username = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();
			System.out.println("Digital Sign --> " + stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				// Add your mapping logic here
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					String value = rs.getObject(i) != null ? rs.getObject(i).toString() : "";
					resultMap.put(columnName, value);
				}
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
					// Handle exception silently
				}
			}
		}
		return resultMap;
	}


	@Override
	public List<Map<String, String>> weaponDeficiencyList(HttpSession session,String  roleSusNo) {
		List<Map<String, String>> resultList = new ArrayList<>(); 		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			//String roleSusNo = session.getAttribute("roleSusNo").toString();
			String username = (String) session.getAttribute("username");

			sql = " select id,nature_deficiency,action_deficiency,effect_conduct,remarks from TB_MISO_INSP_STATEOFWEAPONS_EQU where sus_no=?  and year=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roleSusNo);
			stmt.setString(2, currenYear);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, String> rowMap = new HashMap<>();


				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					String value = rs.getObject(i) != null ? rs.getObject(i).toString() : "";
					rowMap.put(columnName, value);
				}

				resultList.add(rowMap);
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
					// Handle exception silently
				}
			}
		}
		return resultList;
	}

	@Override
	public List<Map<String, String>> getciricaldefimanpowerlist(HttpSession session,String  roleSusNo) {
		List<Map<String, String>> resultList = new ArrayList<>(); 		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

		//	String roleSusNo = session.getAttribute("roleSusNo").toString();
			String username = (String) session.getAttribute("username");

			sql = "select id,manpower_deficiencyoffrs,manpower_deficiencyjco,manpower_deficiencyor,action_taken,"
					+ "training_effect,remarks from\n" + "	TB_MISO_INSP_CRITICAL_DEFI_MANPOWER "
					+ " where sus_no=?  and year=?";


			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roleSusNo);
			stmt.setString(2, currenYear);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, String> rowMap = new HashMap<>();


				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					String value = rs.getObject(i) != null ? rs.getObject(i).toString() : "";
					rowMap.put(columnName, value);
				}

				resultList.add(rowMap);
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
					// Handle exception silently
				}
			}
		}
		return resultList;
	}

	@Override
	public List<Map<String, String>> getDisciplineCourtMaritalDisposallist(HttpSession session,String  roleSusNo) {
		List<Map<String, String>> resultList = new ArrayList<>(); 		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			//String roleSusNo = session.getAttribute("roleSusNo").toString();
			String username = (String) session.getAttribute("username");

			sql = "select id,ongoing_count,pending_cases,cases_current,remarks from\n" + "	TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL "
					+ " where sus_no=?  and year=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roleSusNo);
			stmt.setString(2, currenYear);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, String> rowMap = new HashMap<>();


				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					String value = rs.getObject(i) != null ? rs.getObject(i).toString() : "";
					rowMap.put(columnName, value);
				}

				resultList.add(rowMap);
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
					// Handle exception silently
				}
			}
		}
		return resultList;
	}


	//	GET DATA

	@Override
	public List<Map<String, Object>> getOpData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select op_preparedness_item from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getTrainingData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select training_item from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getWeapData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select state_weapon_item, state_weapon_item_ii from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getPersData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select state_personnel_item from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getAdminData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select administation_item from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getAspectData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select aspect_item from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getInteData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select interior_item from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getMajorData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select major_achievements_item from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getStrengthData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select strength_of_unit_item from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getChallengesData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select challenges_item from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getImproveData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select improve_following_item from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getAttentionData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select attention_of_higher_item from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getMitigationData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select mitigation_item from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getPointsData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select points_discussion_item from TB_MISO_INSP_PHASEII_TBL where sus_no=? and status=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setInt(2, 0);
			stmt.setString(3, currenYear);

			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = rs.getObject(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

}
