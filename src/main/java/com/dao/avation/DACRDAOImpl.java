package com.dao.avation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.sql.*;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.ResponseBody;

import com.models.TB_AVIATION_CHTL_DAILY_BASIS;
import com.models.TB_AVIATION_CHTL_DAILY_BASIS_HISTORY;
import com.models.TB_AVIATION_DAILY_BASIS;
import com.models.TB_AVIATION_DAILY_BASIS_HISTORY;
import com.models.TB_AVIATION_RPAS_DAILY_BASIS;
import com.models.TB_AVIATION_RPAS_DAILY_BASIS_HISTORY;
import com.models.TB_TMS_MVCR_PARTA_DTL;
import com.models.TB_TMS_MVCR_PARTA_DTL_HISTORY;
import com.models.TB_TMS_MVCR_PARTA_MAIN;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class DACRDAOImpl implements DACRDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public ArrayList<List<String>> UpdategetDACRReportListPending(String qry, String sus_no, String roleType,
			String roleAccess) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		List<String[]> getlocList = fetchLocList();
		try {
			conn = dataSource.getConnection();
			String sql = "select id, \r\n" + "acc_no, \r\n" + "status,\r\n" + "falf_hrs_day,\r\n"
					+ "falf_hrs_night,\r\n" + "g_run,\r\n" + "af_hrs, \r\n" + "eng_hrs_left, \r\n"
					+ "eng_hrs_rigth, \r\n" + "hrs_left, \r\n" + "next_insp, \r\n" + "hrs_monthly, \r\n" + "hrs_qtrly,"
					+ "hrs_half_year, \r\n" + "hrs_qtrly_flow, \r\n" + "remarks, \r\n" + "bal_hrs, \r\n"
					+ "ltrim(TO_CHAR(date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n"
					+ "ltrim(TO_CHAR(ason_dt,'dd-mm-yyyy'),'') as ason_dt, \r\n" + "lh_ser_no,\r\n" + "rh_ser_no, \r\n"
					+ "loc_code, \r\n" + "aircraft_state \r\n"
					+ "from tb_aviation_daily_basis where sus_no=? and (aircraft_state ='1' or aircraft_state is null) order by acc_no asc";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			System.err.println("ALHlistQuery--" + stmt);

			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id")); // 0
				list.add(rs.getString("acc_no")); // 1
				list.add("<select id='status_" + rs.getString("id") + "' class='form-control'>" + "<option value='S' "
						+ ("S".equals(rs.getString("status")) ? "selected" : "") + ">S</option>" + "<option value='I' "
						+ ("I".equals(rs.getString("status")) ? "selected" : "") + ">I</option>" + "<option value='RI' "
						+ ("RI".equals(rs.getString("status")) ? "selected" : "") + ">RI</option>"
						+ "<option value='RE' " + ("RE".equals(rs.getString("status")) ? "selected" : "")
						+ ">RE</option>" + "<option value='AOG' "
						+ ("AOG".equals(rs.getString("status")) ? "selected" : "") + ">AOG</option>"
						+ "<option value='ACCIDENTAL' "
						+ ("ACCIDENTAL".equals(rs.getString("status")) ? "selected" : "") + ">ACCI</option>"
						+ "<option value='ROH' " + ("ROH".equals(rs.getString("status")) ? "selected" : "")
						+ ">ROH</option>" + "</select>");
				// 2

				
				list.add("<input type='text' id='falf_hrs_day_" + rs.getString("id") + "' value='00:00'"
						+ formatTime(rs.getString("falf_hrs_day"))
						+ "' class='form-control  min='00:00' max='23:59' step='60' maxlength='5'  oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;''/>"); // 3
				list.add("<input type='text' id='falf_hrs_night_" + rs.getString("id") + "' value='00:00'"
						+ formatTime(rs.getString("falf_hrs_night"))
						+ "' class='form-control  min='00:00' max='23:59' step='60' maxlength='5' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;''/>"); // 4
				list.add("<input type='text' id='g_run_" + rs.getString("id") + "' value='00:00'"
						+ formatTime(rs.getString("g_run"))
						+ "' class='form-control  min='00:00' max='23:59' step='60' maxlength='5' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;''/>"); // 5

				list.add("<input type='text' id='af_hrs_" + rs.getString("id") + "' value='" + rs.getString("af_hrs")
						+ "' class='form-control' readonly/>"); // 6
				list.add("<input type='text' id='eng_hrs_left_" + rs.getString("id") + "' value='"
						+ rs.getString("eng_hrs_left") + "' class='form-control' readonly/>"); // 7
				list.add("<input type='text' id='eng_hrs_rigth_" + rs.getString("id") + "' value='"
						+ rs.getString("eng_hrs_rigth") + "' class='form-control' readonly/>"); // 8

				list.add("<input type='text' id='hrs_left_" + rs.getString("id") + "' value='"
						+ formatTime(rs.getString("hrs_left"))
						+ "' class='form-control' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;'/>"); // 9
				list.add("<input type='text' id='next_insp_" + rs.getString("id") + "' value='"
						+ rs.getString("next_insp") + "' class='form-control'/>"); // 10

				Calendar calendar = Calendar.getInstance();

				
				boolean isFirstDayOfMonth = (calendar.get(Calendar.DAY_OF_MONTH) == 1);

				
				boolean isFirstDayOfQuarter = isFirstDayOfMonth && (calendar.get(Calendar.MONTH) == Calendar.JANUARY
						|| calendar.get(Calendar.MONTH) == Calendar.APRIL
						|| calendar.get(Calendar.MONTH) == Calendar.JULY
						|| calendar.get(Calendar.MONTH) == Calendar.OCTOBER);

				
				boolean isFirstDayOfHalfYear = isFirstDayOfMonth && (calendar.get(Calendar.MONTH) == Calendar.APRIL
						|| calendar.get(Calendar.MONTH) == Calendar.OCTOBER);

				
				boolean isFirstDayOfApril = isFirstDayOfMonth && (calendar.get(Calendar.MONTH) == Calendar.APRIL);

				if (isFirstDayOfMonth) {
					list.add("<input type='text' id='hrs_monthly_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_monthly")) + "' class='form-control' readonly/>"); // 11
				} else {
					list.add("<input type='text' id='hrs_monthly_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_monthly")) + "' class='form-control' readonly/>"); // 11
				}

				if (isFirstDayOfQuarter) {
					list.add("<input type='text' id='hrs_qtrly_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_qtrly")) + "' class='form-control' readonly/>"); // 12
				} else {
					list.add("<input type='text' id='hrs_qtrly_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_qtrly")) + "' class='form-control' readonly/>"); // 12
				}

				if (isFirstDayOfHalfYear) {
					list.add("<input type='text' id='hrs_half_year_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_half_year")) + "' class='form-control' readonly/>"); // 13
				} else {
					list.add("<input type='text' id='hrs_half_year_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_half_year")) + "' class='form-control' readonly/>"); // 13
				}

				if (isFirstDayOfApril) {
					list.add("<input type='text' id='hrs_qtrly_flow_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_qtrly_flow")) + "' class='form-control' readonly/>"); // 14
				} else {
					list.add("<input type='text' id='hrs_qtrly_flow_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_qtrly_flow")) + "' class='form-control' readonly/>"); // 14
				}

				list.add("<input type='text' id='remarks_" + rs.getString("id") + "' value='" + rs.getString("remarks")
						+ "' class='form-control'/>"); // 15

				
				String dateAsFlown = "<input type='date' style='font-size: 13px;' id='date_goi_letter_"
						+ rs.getString("id") + "' value='" + rs.getString("date_goi_letter")
						+ "' class='form-control'/>";
				list.add(dateAsFlown);

				
				String update1 = "onclick='calculateHrsForRecord(" + rs.getString("id") + ")'";
				String f1 = "<button id='calId" + rs.getString("id") + "' " + update1
						+ " class='btn circular-btn calButton' style='background-color: blue; color: white;' title='Calculate Hours'><i class='action_icons action_calculate'></i> Calculate</button> &nbsp; &nbsp;";
				list.add(f1); 

				String update = "onclick=submitData('" + rs.getString("acc_no") + "','" + rs.getString("id") + "')";
				String f = "<button id='updateId" + rs.getString("id") + "' " + update
						+ " class='btn circular-btn updateButton' style='background-color: blue; color: white;' title='Update Data'>Update</button> &nbsp; &nbsp;";
				list.add(f); // 18

				list.add(rs.getString("lh_ser_no"));
				list.add(rs.getString("rh_ser_no"));

				list.add("<input type='text' id='lh_ser_no_" + rs.getString("id") + "' value='"
						+ rs.getString("lh_ser_no") + "' class='form-control' readonly/>");
				list.add("<input type='text' id='rh_ser_no_" + rs.getString("id") + "' value='"
						+ rs.getString("rh_ser_no") + "' class='form-control' readonly/>");

				
				String dateofpdc = "<input type='date' style='font-size: 13px;' id='date_of_pdc_" + rs.getString("id")
						+ "' value='" + rs.getString("date_of_pdc") + "' class='form-control'/>";
				list.add(dateofpdc);

				
				StringBuilder locCodeSelect = new StringBuilder();
				locCodeSelect.append("<select id='loc_code_").append(rs.getString("id"))
						.append("' class='form-control'>");
				locCodeSelect.append("<option value='0' ")
						.append("0".equals(rs.getString("loc_code")) ? "selected" : "").append(">--Select--</option>");
				for (String[] item : getlocList) {
					locCodeSelect.append("<option value='").append(item[0]).append("' ")
							.append(item[0].equals(rs.getString("loc_code")) ? "selected" : "").append(">")
							.append(item[1]).append("</option>");
				}
				locCodeSelect.append("</select>");
				list.add(locCodeSelect.toString()); // 24

				if (rs.isLast()) {

					list.add(rs.getString("ason_dt")); // 25
				} else {

					list.add(""); // 25
				}
				list.add(rs.getString("aircraft_state")); // 26
				alist.add(list);
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
					
				}
			}
		}
		return alist;
	}

	
	private String formatTime(String time) {
		
		if (time != null && !time.isEmpty()) {
			String[] parts = time.split(":");
			if (parts.length == 2) {
				return String.format("%02d:%02d", Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
			}
		}
		return "00:00"; 
	}

	// 05-12-24
	public ArrayList<List<String>> getDACRReportListPending(String sus_no, String roleType) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			sql = "select id, \r\n" + "acc_no, \r\n" + "status,\r\n" + "falf_hrs_day,\r\n" + "falf_hrs_night,\r\n"
					+ "g_run,\r\n" + "af_hrs, \r\n" + "eng_hrs_left, \r\n" + "eng_hrs_rigth, \r\n" + "hrs_left, \r\n"
					+ "next_insp, \r\n" + "hrs_monthly, \r\n" + "hrs_qtrly," + "hrs_half_year, \r\n"
					+ "hrs_qtrly_flow, \r\n" + "remarks, \r\n" + "bal_hrs, \r\n"
					+ "ltrim(TO_CHAR(date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n" + "lh_ser_no,\r\n"
					+ "rh_ser_no \r\n" + "from tb_aviation_daily_basis where sus_no=? order by acc_no desc";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			System.err.println("pending--" + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("acc_no"));// 1
				list.add(rs.getString("status"));// 2
				list.add(rs.getString("falf_hrs_day"));// 3
				list.add(rs.getString("falf_hrs_night")); // 4
				list.add(rs.getString("g_run")); // 5
				list.add(rs.getString("af_hrs")); // 6
				list.add(rs.getString("eng_hrs_left")); // 7
				list.add(rs.getString("eng_hrs_rigth")); // 8
				list.add(rs.getString("hrs_left")); // 9
				list.add(rs.getString("next_insp")); // 10
				list.add(rs.getString("hrs_monthly")); // 11
				list.add(rs.getString("hrs_qtrly")); // 12
				list.add(rs.getString("hrs_half_year")); // 13
				list.add(rs.getString("hrs_qtrly_flow")); // 14
				list.add(rs.getString("remarks")); // 15
				list.add(rs.getString("bal_hrs")); // 16
				list.add(rs.getString("date_goi_letter")); // 17
				list.add(rs.getString("lh_ser_no"));// 18
				list.add(rs.getString("rh_ser_no"));// 19
				list.add(rs.getString("date_of_pdc")); // 20
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

	// changes by mitesh 01-01-2025
	public ArrayList<List<String>> getSearchAttributeReportDacr(String status, String sus_no, String roleType,
			String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			String qry = "";

		
			if (!status.isEmpty()) {
				qry += " and n.status1 = ?";
			}
			
			if (!sus_no.isEmpty()) {
				qry += " and n.sus_no = ?";
			}
			
			if (!issue_date.isEmpty()) {
				qry += " and cast(n.ason_dt as date) = cast(? as date)";
			}

			

			sql = "select distinct m.sus_no, m.unit_name, ltrim(TO_CHAR(n.ason_dt,'dd-mm-yyyy'),'') as ason_dt, ltrim(TO_CHAR(n.approved_dt,'dd-mm-yyyy'),'') as approved_dt "
					+ "from tb_aviation_daily_basis_history n "
					+ "inner join tb_miso_orbat_unt_dtl m on n.sus_no = m.sus_no " + "where m.status_sus_no = 'Active'"
					+ qry;

			PreparedStatement stmt = conn.prepareStatement(sql);
			int j = 1;

			
			if (!status.isEmpty()) {
				stmt.setString(j, status);
				j++;
			}
			
			if (!sus_no.isEmpty()) {
				stmt.setString(j, sus_no);
				j++;
			}
			
			if (!issue_date.isEmpty()) {
				stmt.setString(j, issue_date);
				j++;
			}
			System.err.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("approved_dt"));
				list.add(rs.getString("ason_dt"));

				
				String View = "onclick=\"View('" + rs.getString("sus_no") + "')\"";
				String appButton = "<i class='action_icons action_approve' " + View + " title='Approve Data'></i>";

				String seen = "onclick=\"Seen('" + rs.getString("sus_no") + "')\"";
				String seenButton = "<i class='btn btn-default btn-xs' " + seen + " title='View Data'>DACR PART A</i>";

				String PartB = "onclick=\" ViewB('" + rs.getString("sus_no") + "')\"";
				String PartBButton = "<i class='btn btn-default btn-xs' " + PartB
						+ " title='View Data'>DACR PART B</i>";

				String f = "";
				
				if (status.equals("0")) {
					if (roleType.equals("ALL") || roleType.equals("APP")) {
						f += appButton;
					}
				}

				if (status.equals("1")) {
					if (roleType.equals("ALL") || roleType.equals("APP")) {
						f += seenButton;
						f += PartBButton;
					}
				}

				if (status.equals("2")) {
					if (roleType.equals("DEO") || roleType.equals("ALL")) {
						f += "No Action Required";
					}
				}
				list.add(f);
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
					e.printStackTrace();
				}
			}
		}
		return alist;
	}

	// change by mitesh 26-12-24
	public ArrayList<List<String>> getDACRReportListForApproval(String sus_no, String roleType, String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "select a.id as id, \r\n" + "a.acc_no as acc_no, \r\n" + "a.status as status,\r\n"
					+ "a.falf_hrs_day as falf_hrs_day,\r\n" + "a.falf_hrs_night as falf_hrs_night,\r\n"
					+ "a.g_run as g_run,\r\n" + "a.af_hrs as af_hrs, \r\n" + "a.eng_hrs_left as eng_hrs_left, \r\n"
					+ "a.eng_hrs_rigth as eng_hrs_rigth, \r\n" + "a.hrs_left as hrs_left, \r\n"
					+ "a.next_insp as next_insp, \r\n" + "a.hrs_monthly hrs_monthly, \r\n" + "a.hrs_qtrly as hrs_qtrly,"
					+ "a.hrs_half_year as hrs_half_year, \r\n" + "a.hrs_qtrly_flow as hrs_qtrly_flow, \r\n"
					+ "a.remarks as remarks, \r\n" + "a.bal_hrs as bal_hrs, \r\n"
					+ "ltrim(TO_CHAR(a.date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n"
					+ "ltrim(TO_CHAR(a.ason_dt,'dd-mm-yyyy'),'') as ason_dt, \r\n" + "a.lh_ser_no as lh_ser_no,\r\n"
					+ "a.rh_ser_no as rh_ser_no, \r\n" + "b.code_value as loc \r\n"
					+ "from tb_aviation_daily_basis_history a \r\n"
					+ "left join tb_miso_orbat_code b on b.code=a.loc_code \r\n" + " where a.sus_no=?";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_dt as date) = cast(? as date) group by a.id,b.code_value  order by a.acc_no asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}
			System.err.println("app--" + stmt);
			ResultSet rs = stmt.executeQuery();
			int totalMinutesDay = 0, totalMinutesNight = 0, totalMinutesM = 0, totalMinutesQ = 0, totalMinutesHY = 0,
					totalMinutesY = 0, totalDayNight = 0;
			while (rs.next()) {

				String falfHrsDay = rs.getString("falf_hrs_day");
				if (falfHrsDay != null && !falfHrsDay.isEmpty()) {
					totalMinutesDay += parseTimeToMinutes(falfHrsDay);
				}

				String falfHrsNight = rs.getString("falf_hrs_night");
				if (falfHrsNight != null && !falfHrsNight.isEmpty()) {
					totalMinutesNight += parseTimeToMinutes(falfHrsNight);
				}

				String falfHrsM = rs.getString("hrs_monthly");
				if (falfHrsM != null && !falfHrsM.isEmpty()) {
					totalMinutesM += parseTimeToMinutes(falfHrsM);
				}

				String falfHrsQ = rs.getString("hrs_qtrly");
				if (falfHrsQ != null && !falfHrsQ.isEmpty()) {
					totalMinutesQ += parseTimeToMinutes(falfHrsQ);
				}

				String falfHrsHY = rs.getString("hrs_half_year");
				if (falfHrsHY != null && !falfHrsHY.isEmpty()) {
					totalMinutesHY += parseTimeToMinutes(falfHrsHY);
				}

				String falfHrsY = rs.getString("hrs_qtrly_flow");
				if (falfHrsY != null && !falfHrsY.isEmpty()) {
					totalMinutesY += parseTimeToMinutes(falfHrsY);
				}

				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("acc_no"));// 1
				list.add(rs.getString("status"));// 2
				list.add(rs.getString("falf_hrs_day"));// 3
				list.add(rs.getString("falf_hrs_night")); // 4
				list.add(rs.getString("g_run")); // 5
				list.add(rs.getString("af_hrs")); // 6
				list.add(rs.getString("eng_hrs_left")); // 7
				list.add(rs.getString("eng_hrs_rigth")); // 8
				list.add(rs.getString("hrs_left")); // 9
				list.add(rs.getString("next_insp")); // 10
				list.add(rs.getString("hrs_monthly")); // 11
				list.add(rs.getString("hrs_qtrly")); // 12
				list.add(rs.getString("hrs_half_year")); // 13
				list.add(rs.getString("hrs_qtrly_flow")); // 14
				list.add(rs.getString("remarks")); // 15
				list.add(rs.getString("bal_hrs")); // 16
				list.add(rs.getString("date_goi_letter")); // 17
				list.add(rs.getString("lh_ser_no"));// 18
				list.add(rs.getString("rh_ser_no"));// 19
				list.add(rs.getString("date_of_pdc")); // 20
				list.add(rs.getString("loc")); // 21

				if (rs.isLast()) {
					totalDayNight += totalMinutesDay + totalMinutesNight;
					list.add(formatMinutesToTime(totalMinutesDay)); // 22
					list.add(formatMinutesToTime(totalMinutesNight)); // 23
					list.add(formatMinutesToTime(totalMinutesM)); // 24
					list.add(formatMinutesToTime(totalMinutesQ)); // 25
					list.add(formatMinutesToTime(totalMinutesHY)); // 26
					list.add(formatMinutesToTime(totalMinutesY)); // 27
					list.add(formatMinutesToTime(totalDayNight)); // 28
					list.add(rs.getString("ason_dt")); // 29
				} else {
					list.add(""); // 22
					list.add(""); // 23
					list.add(""); // 24
					list.add(""); // 25
					list.add(""); // 26
					list.add(""); // 27
					list.add(""); // 28
					list.add(""); // 29
				}

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

	// change by mitesh 26-12-24
	public ArrayList<List<String>> getDACRReportListForApproval1(String sus_no, String roleType, String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "select a.id as id, \r\n" + "a.acc_no as acc_no, \r\n" + "a.status as status,\r\n"
					+ "a.falf_hrs_day as falf_hrs_day,\r\n" + "a.falf_hrs_night as falf_hrs_night,\r\n"
					+ "a.g_run as g_run,\r\n" + "a.af_hrs as af_hrs, \r\n" + "a.eng_hrs_left as eng_hrs_left, \r\n"
					+ "a.eng_hrs_rigth as eng_hrs_rigth, \r\n" + "a.hrs_left as hrs_left, \r\n"
					+ "a.next_insp as next_insp, \r\n" + "a.hrs_monthly as hrs_monthly, \r\n"
					+ "a.hrs_qtrly as hrs_qtrly," + "a.hrs_half_year as hrs_half_year, \r\n"
					+ "a.hrs_qtrly_flow as hrs_qtrly_flow, \r\n" + "a.remarks as remarks, \r\n"
					+ "ltrim(TO_CHAR(a.date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n"
					+ "ltrim(TO_CHAR(a.ason_dt,'dd-mm-yyyy'),'') as ason_dt, \r\n" + "a.lh_ser_no as lh_ser_no,\r\n"
					+ "a.rh_ser_no as rh_ser_no, \r\n" + "b.code_value as loc \r\n"
					+ "from tb_aviation_daily_basis_history a \r\n"
					+ "left join tb_miso_orbat_code b on b.code=a.loc_code \r\n"
					+ "where a.sus_no=? and (a.aircraft_state ='1' or a.aircraft_state is null) ";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_dt as date) = cast(? as date) group by a.id,b.code_value  order by a.acc_no asc";
				// sql += " and n.approved_dt = ? ";
			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}
			System.err.println("Pending1---" + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("acc_no"));// 1
				list.add(rs.getString("status"));// 2
				list.add(rs.getString("falf_hrs_day"));// 3
				list.add(rs.getString("falf_hrs_night")); // 4
				list.add(rs.getString("g_run")); // 5
				list.add(rs.getString("af_hrs")); // 6
				list.add(rs.getString("eng_hrs_left")); // 7
				list.add(rs.getString("eng_hrs_rigth")); // 8
				list.add(rs.getString("hrs_left")); // 9
				list.add(rs.getString("next_insp")); // 10
				list.add(rs.getString("hrs_monthly")); // 11
				list.add(rs.getString("hrs_qtrly")); // 12
				list.add(rs.getString("hrs_half_year")); // 13
				list.add(rs.getString("hrs_qtrly_flow")); // 14
				list.add(rs.getString("remarks")); // 15
				list.add(rs.getString("date_goi_letter")); // 16
				list.add(rs.getString("lh_ser_no"));// 17
				list.add(rs.getString("rh_ser_no"));// 18
				list.add(rs.getString("date_of_pdc")); // 19
				list.add(rs.getString("loc")); // 20
				list.add(rs.getString("ason_dt")); // 21

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

	public String setApproveddacr(String sus_no, String username, String issue_date) {
		Session session = null;
		Transaction tx = null;
		int app = 0;
		Date dt = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date asonDate = null;

		
		String timeZone = "Asia/Kolkata"; 

		try {
			
			asonDate = parseDateWithTimeZone(issue_date, "dd-MM-yyyy", timeZone);
			System.err.println(asonDate);
			if (asonDate == null) {
				return "DACR not Approved due to invalid date format.";
			}

			
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			
			Query qDacr = session
					.createQuery("from TB_AVIATION_DAILY_BASIS where sus_no = :sus_no order by acc_no asc");
			qDacr.setParameter("sus_no", sus_no);

			@SuppressWarnings("unchecked")
			List<TB_AVIATION_DAILY_BASIS> ListDacr = (List<TB_AVIATION_DAILY_BASIS>) qDacr.list();

			
			if (ListDacr.isEmpty()) {
				System.out.println("No records found for sus_no: " + sus_no);
				return "DACR not Approved: No matching records found";
			}

			
			String checkQuery = "from TB_AVIATION_DAILY_BASIS_HISTORY where sus_no = :sus_no and ason_dt = :ason_dt";
			Query qCheckHistory = session.createQuery(checkQuery);
			qCheckHistory.setParameter("sus_no", sus_no);
			qCheckHistory.setParameter("ason_dt", asonDate);

			if (qCheckHistory.list().isEmpty()) {
				return "DACR not Approved: No matching records found in TB_AVIATION_DAILY_BASIS_HISTORY";
			}

			
			String hqlMAIN1 = "update TB_AVIATION_DAILY_BASIS_HISTORY c " + "set c.status1 = :status1, "
					+ "c.approved_dt = :approved_dt, " + "c.approved_by = :approved_by, "
					+ "c.modified_date = :modified_date, " + "c.modified_by = :modified_by "
					+ "WHERE c.sus_no = :sus_no and c.ason_dt = :ason_dt";

			Query queryMain1 = session.createQuery(hqlMAIN1);
			queryMain1.setParameter("status1", "1");
			queryMain1.setParameter("approved_dt", dt);
			queryMain1.setParameter("approved_by", username);
			queryMain1.setParameter("modified_date", dt);
			queryMain1.setParameter("modified_by", username);
			queryMain1.setParameter("sus_no", sus_no);
			queryMain1.setParameter("ason_dt", asonDate);
			int rowsAffectedHistory = queryMain1.executeUpdate();
			System.out.println("Rows affected in TB_AVIATION_DAILY_BASIS_HISTORY: " + rowsAffectedHistory);

			// Update TB_AVIATION_DAILY_BASIS
			String hqlMAIN = "update TB_AVIATION_DAILY_BASIS c " + "set c.status1 = :status1, "
					+ "c.approved_dt = :approved_dt, " + "c.approved_by = :approved_by, "
					+ "c.modified_date = :modified_date, " + "c.modified_by = :modified_by "
					+ "WHERE c.sus_no = :sus_no";

			Query queryMain = session.createQuery(hqlMAIN);
			queryMain.setParameter("status1", "1");
			queryMain.setParameter("approved_dt", dt);
			queryMain.setParameter("approved_by", username);
			queryMain.setParameter("modified_date", dt);
			queryMain.setParameter("modified_by", username);
			queryMain.setParameter("sus_no", sus_no);
			app = queryMain.executeUpdate();
			System.out.println("Rows affected in TB_AVIATION_DAILY_BASIS: " + app);

			
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback(); 
			e.printStackTrace(); 
			return "DACR not Approved due to error: " + e.getMessage();
		} finally {
			if (session != null)
				session.close(); 
		}

		
		if (app > 0) {
			return "DACR Approved Successfully.";
		} else {
			return "DACR not Approved";
		}
	}

	public String setRejectdacr(String sus_no, String username) {
		Date dt = new Date();
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		int app = 0;
		try {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();

			String hqlMAIN = "UPDATE TB_AVIATION_DAILY_BASIS set status1=:status1 , modified_date=:modified_date, modified_by=:modified_by WHERE sus_no = :sus_no";
			Query queryMain = session.createQuery(hqlMAIN);
			queryMain.setParameter("status1", "2");
			queryMain.setParameter("modified_by", username);
			queryMain.setParameter("modified_date", dt);
			queryMain.setParameter("sus_no", sus_no);
			app = queryMain.executeUpdate();

			tx.commit();
		} catch (Exception e) {
			tx1.rollback();

		} finally {
			session1.close();
		}
		if (app > 0) {
			return "DACR Rejected Successfully.";
		} else {
			return "DACR not Rejected.";
		}
	}

	public @ResponseBody List<TB_AVIATION_DAILY_BASIS_HISTORY> getApproveDate(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_AVIATION_DAILY_BASIS_HISTORY where sus_no=:sus_no");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<TB_AVIATION_DAILY_BASIS_HISTORY> list = (List<TB_AVIATION_DAILY_BASIS_HISTORY>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<Map<String, Object>> getFormationDetailsFromSusNo(String sus_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select o.unit_name, coalesce(o.cmd_name,'-') as cmd_name,\r\n"
					+ "	coalesce(o.coprs_name,'-') as coprs_name,coalesce(o.div_name,'-') as div_name,\r\n"
					+ "	coalesce(o.bde_name,'-') as bde_name ,\r\n"
					+ "	coalesce(string_agg(c.modification,','),'NA') as mod, \r\n"
					+ "	coalesce(o.location,'-') as loc,\r\n" + "	coalesce(o.nrs,'-') as nrs"
					+ "	from orbat_all_details_view o\r\n"
					+ "	left join cue_tb_wepe_link_sus_trans_mdfs c on o.sus_no = c.sus_no\r\n"
					+ "	where status_sus_no='Active' and o.sus_no = ? \r\n" + "group by 1,2,3,4,5,7,8";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, sus_no);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString());
				}
				list.add(columns);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("error", "An Error Occure" + e.getMessage());
			list.add(columns);
			return list;
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

	public @ResponseBody List<String> getwepeno(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select wepe_trans_no from CUE_TB_WEPE_link_sus_perstransweapon where sus_no =:sus_no");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// NEW ADDED BY MITESH 03-12-2024 changed by mitesh 26-12-2024
	public ArrayList<List<String>> UpdategetDACRReportchtlListPending(String qry, String sus_no, String roleType,
			String roleAccess) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		List<String[]> getlocList = fetchLocList();
		try {
			conn = dataSource.getConnection();
			String sql = "select id, \r\n" + "acc_no, \r\n" + "status,\r\n" + "falf_hrs_day,\r\n"
					+ "falf_hrs_night,\r\n" + "g_run,\r\n" + "af_hrs, \r\n" + "eng_hrs, \r\n" + "hrs_left, \r\n"
					+ "days_left, \r\n" + "next_insp, \r\n" + "hrs_monthly, \r\n" + "hrs_qtrly," + "hrs_half_year, \r\n"
					+ "hrs_qtrly_flow, \r\n" + "remarks, \r\n" + "bal_hrs, \r\n"
					+ "ltrim(TO_CHAR(date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n"
					+ "ltrim(TO_CHAR(ason_date,'dd-mm-yyyy'),'') as ason_date, \r\n" + "eng_ser_no, \r\n"
					+ "loc_code \r\n" + "from tb_aviation_chtl_daily_basis where sus_no=? order by acc_no asc";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			System.err.println("chtlReport--" + stmt);
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id")); // 0
				list.add(rs.getString("acc_no")); // 1
				list.add("<select id='status_" + rs.getString("id") + "' class='form-control'>" + "<option value='S' "
						+ ("S".equals(rs.getString("status")) ? "selected" : "") + ">S</option>" + "<option value='I' "
						+ ("I".equals(rs.getString("status")) ? "selected" : "") + ">I</option>" + "<option value='RI' "
						+ ("RI".equals(rs.getString("status")) ? "selected" : "") + ">RI</option>"
						+ "<option value='RE' " + ("RE".equals(rs.getString("status")) ? "selected" : "")
						+ ">RE</option>" + "<option value='AOG' "
						+ ("AOG".equals(rs.getString("status")) ? "selected" : "") + ">AOG</option>"
						+ "<option value='ACCIDENTAL' "
						+ ("ACCIDENTAL".equals(rs.getString("status")) ? "selected" : "") + ">ACCI</option>"
						+ "<option value='ROH' " + ("ROH".equals(rs.getString("status")) ? "selected" : "")
						+ ">ROH</option>" + "</select>");
				// 2

				
				list.add("<input type='text' id='falf_hrs_day_" + rs.getString("id") + "' value='00:00'"
						+ formatTime(rs.getString("falf_hrs_day"))
						+ "' class='form-control  min='00:00' max='23:59' step='60' maxlength='5' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;''/>"); // 3
				list.add("<input type='text' id='falf_hrs_night_" + rs.getString("id") + "' value='00:00'"
						+ formatTime(rs.getString("falf_hrs_night"))
						+ "' class='form-control  min='00:00' max='23:59' step='60' maxlength='5' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;''/>"); // 4
				list.add("<input type='text' id='g_run_" + rs.getString("id") + "' value='00:00'"
						+ formatTime(rs.getString("g_run"))
						+ "' class='form-control  min='00:00' max='23:59' step='60' maxlength='5' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;''/>"); // 5

				list.add("<input type='text' id='af_hrs_" + rs.getString("id") + "' value='" + rs.getString("af_hrs")
						+ "' class='form-control' readonly/>"); // 6
				list.add("<input type='text' id='eng_hrs_" + rs.getString("id") + "' value='" + rs.getString("eng_hrs")
						+ "' class='form-control' readonly/>"); // 7
				list.add("<input type='text' id='days_left_" + rs.getString("id") + "' value='"
						+ formatTime(rs.getString("days_left")) + "' class='form-control'/>"); // 8
				list.add("<input type='text' id='hrs_left_" + rs.getString("id") + "' value='"
						+ formatTime(rs.getString("hrs_left"))
						+ "' class='form-control' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;' />"); // 9
				list.add("<input type='text' id='next_insp_" + rs.getString("id") + "' value='"
						+ rs.getString("next_insp") + "' class='form-control'/>"); // 10

				Calendar calendar = Calendar.getInstance();

				
				boolean isFirstDayOfMonth = (calendar.get(Calendar.DAY_OF_MONTH) == 1);

				
				boolean isFirstDayOfQuarter = isFirstDayOfMonth && (calendar.get(Calendar.MONTH) == Calendar.JANUARY
						|| calendar.get(Calendar.MONTH) == Calendar.APRIL
						|| calendar.get(Calendar.MONTH) == Calendar.JULY
						|| calendar.get(Calendar.MONTH) == Calendar.OCTOBER);

				
				boolean isFirstDayOfHalfYear = isFirstDayOfMonth && (calendar.get(Calendar.MONTH) == Calendar.APRIL
						|| calendar.get(Calendar.MONTH) == Calendar.OCTOBER);

				
				boolean isFirstDayOfApril = isFirstDayOfMonth && (calendar.get(Calendar.MONTH) == Calendar.APRIL);

				if (isFirstDayOfMonth) {
					list.add("<input type='text' id='hrs_monthly_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_monthly")) + "' class='form-control' readonly/>"); // 11
				} else {
					list.add("<input type='text' id='hrs_monthly_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_monthly")) + "' class='form-control' readonly/>"); // 11
				}

				if (isFirstDayOfQuarter) {
					list.add("<input type='text' id='hrs_qtrly_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_qtrly")) + "' class='form-control' readonly/>"); // 12
				} else {
					list.add("<input type='text' id='hrs_qtrly_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_qtrly")) + "' class='form-control' readonly/>"); // 12
				}

				if (isFirstDayOfHalfYear) {
					list.add("<input type='text' id='hrs_half_year_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_half_year")) + "' class='form-control' readonly/>"); // 13
				} else {
					list.add("<input type='text' id='hrs_half_year_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_half_year")) + "' class='form-control' readonly/>"); // 13
				}

				if (isFirstDayOfApril) {
					list.add("<input type='text' id='hrs_qtrly_flow_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_qtrly_flow")) + "' class='form-control' readonly/>"); // 14
				} else {
					list.add("<input type='text' id='hrs_qtrly_flow_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_qtrly_flow")) + "' class='form-control' readonly/>"); // 14
				}

				list.add("<input type='text' id='remarks_" + rs.getString("id") + "' value='" + rs.getString("remarks")
						+ "' class='form-control'/>"); // 15

				
				String dateAsFlown = "<input type='date' style='font-size: 13px;' id='date_goi_letter_"
						+ rs.getString("id") + "' value='" + rs.getString("date_goi_letter")
						+ "' class='form-control'/>";
				list.add(dateAsFlown); // 16

				
				String update1 = "onclick='calculateHrsForRecord(" + rs.getString("id") + ")'";
				String f1 = "<button id='calId" + rs.getString("id") + "' " + update1
						+ " class='btn circular-btn calButton' style='background-color: blue; color: white;' title='Calculate Hours'><i class='action_icons action_calculate'></i> Calculate</button> &nbsp; &nbsp;";
				list.add(f1); //  17

				String update = "onclick=submitData('" + rs.getString("acc_no") + "','" + rs.getString("id") + "')";
				String f = "<button id='updateId" + rs.getString("id") + "' " + update
						+ " class='btn circular-btn updateButton' style='background-color: blue; color: white;' title='Update Data'>Update</button> &nbsp; &nbsp;";
				list.add(f); // 18

				list.add(rs.getString("eng_ser_no"));// 19

				String asonDateString = rs.getString("ason_date");
				LocalDate asonDate = LocalDate.parse(asonDateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				LocalDate currentDate = LocalDate.now();

				long daysLeft = ChronoUnit.DAYS.between(currentDate, asonDate);
				
				String existingDaysLeftString = rs.getString("days_left");

				
				long existingDaysLeft = 0;
				if (existingDaysLeftString != null && !existingDaysLeftString.isEmpty()) {
					try {
						existingDaysLeft = Long.parseLong(existingDaysLeftString);
					} catch (NumberFormatException e) {
						existingDaysLeft = 0;
					}
				}

				if (existingDaysLeftString != null && !existingDaysLeftString.isEmpty()) {
					
					long totalDaysLeft = existingDaysLeft + daysLeft;

					list.add("<input type='number' id='days_left_" + rs.getString("id") + "' value='" + totalDaysLeft
							+ "' class='form-control'/>");// 20

				} else {
					list.add("<input type='number' id='days_left_" + rs.getString("id") + "' value='"
							+ existingDaysLeftString + "' class='form-control'/>");// 20
				}
				
				String dateofpdc = "<input type='date' style='font-size: 13px;' id='date_of_pdc_" + rs.getString("id")
						+ "' value='" + rs.getString("date_of_pdc") + "' class='form-control'/>";
				list.add(dateofpdc);// 21

				
				StringBuilder locCodeSelect = new StringBuilder();
				locCodeSelect.append("<select id='loc_code_").append(rs.getString("id"))
						.append("' class='form-control'>");
				locCodeSelect.append("<option value='0' ")
						.append("0".equals(rs.getString("loc_code")) ? "selected" : "").append(">--Select--</option>");
				for (String[] item : getlocList) {
					locCodeSelect.append("<option value='").append(item[0]).append("' ")
							.append(item[0].equals(rs.getString("loc_code")) ? "selected" : "").append(">")
							.append(item[1]).append("</option>");
				}
				locCodeSelect.append("</select>");
				list.add(locCodeSelect.toString()); // 22

				if (rs.isLast()) {

					list.add(rs.getString("ason_date")); // 23
				} else {

					list.add(""); // 23
				}

				alist.add(list);
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
					
				}
			}
		}
		return alist;
	}

	// New Added By Mitesh 04-12-2024 && changes done by mitesh 26-12-2024
	public ArrayList<List<String>> getSearchChtlReportDacr(String status, String sus_no, String roleType,
			String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			String qry = "";

			
			if (!status.isEmpty()) {
				qry += " and n.status1 = ?";
			}
			
			if (!sus_no.isEmpty()) {
				qry += " and n.sus_no = ?";
			}
			
			if (!issue_date.isEmpty()) {
				qry += " and cast(n.ason_date as date) = cast(? as date)";
			}

			sql = "select distinct m.sus_no, m.unit_name,ltrim(TO_CHAR(n.ason_date,'dd-mm-yyyy'),'') as ason_date ,ltrim(TO_CHAR(n.approved_dt,'dd-mm-yyyy'),'') as approved_dt "
					+ "from tb_aviation_chtl_daily_basis_history n "
					+ "inner join tb_miso_orbat_unt_dtl m on n.sus_no = m.sus_no " + "where m.status_sus_no = 'Active'"
					+ qry;

			PreparedStatement stmt = conn.prepareStatement(sql);
			int j = 1;

			
			if (!status.isEmpty()) {
				stmt.setString(j, status);
				j++;
			}
			
			if (!sus_no.isEmpty()) {
				stmt.setString(j, sus_no);
				j++;
			}
			
			if (!issue_date.isEmpty()) {
				stmt.setString(j, issue_date);
				j++;
			}
			System.err.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));// 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("approved_dt"));// 2
				list.add(rs.getString("ason_date"));// 3

				
				String View = "onclick=\"View('" + rs.getString("sus_no") + "')\"";
				String appButton = "<i class='action_icons action_approve' " + View + " title='Approve Data'></i>";

				String seen = "onclick=\"Seen('" + rs.getString("sus_no") + "')\"";
				String seenButton = "<i class='btn btn-default btn-xs' " + seen + " title='View Data'>DACR PART A</i>";

				String PartB = "onclick=\" ViewB('" + rs.getString("sus_no") + "')\"";
				String PartBButton = "<i class='btn btn-default btn-xs' " + PartB
						+ " title='View Data'>DACR PART B</i>";

				String f = "";
				
				if (status.equals("0")) {
					if (roleType.equals("ALL") || roleType.equals("APP")) {
						f += appButton;
					}
				}

				if (status.equals("1")) {
					if (roleType.equals("ALL") || roleType.equals("APP")) {
						f += seenButton;
						f += PartBButton;
					}
				}

				if (status.equals("2")) {
					if (roleType.equals("DEO") || roleType.equals("ALL")) {
						f += "No Action Required";
					}
				}
				list.add(f);
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
					e.printStackTrace();
				}
			}
		}
		return alist;
	}

	// changes by mitesh 26-12-2024
	public ArrayList<List<String>> getChtlDACRReportListForApproval1(String sus_no, String roleType,
			String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "select a.id, \r\n" + "a.acc_no, \r\n" + "a.status,\r\n" + "a.falf_hrs_day,\r\n"
					+ "a.falf_hrs_night,\r\n" + "a.g_run,\r\n" + "a.af_hrs, \r\n" + "a.eng_hrs, \r\n"
					+ "a.hrs_left, \r\n" + "a.next_insp, \r\n" + "a.hrs_monthly, \r\n" + "a.hrs_qtrly,"
					+ "a.hrs_half_year, \r\n" + "a.hrs_qtrly_flow, \r\n" + "a.remarks, \r\n"
					+ "ltrim(TO_CHAR(a.date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n"
					+ "ltrim(TO_CHAR(a.ason_date,'dd-mm-yyyy'),'') as ason_date, \r\n" + "a.days_left,\r\n"
					+ "a.eng_ser_no, \r\n" + "b.code_value as loc \r\n"
					+ "from tb_aviation_chtl_daily_basis_history a \r\n"
					+ "left join tb_miso_orbat_code b on b.code=a.loc_code \r\n" + "where a.sus_no=?";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) group by a.id,b.code_value  order by a.acc_no asc";
				// sql += " and n.approved_dt = ? ";
			}
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}
			System.err.println("Pendingchtl1---" + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("acc_no"));// 1
				list.add(rs.getString("status"));// 2
				list.add(rs.getString("falf_hrs_day"));// 3
				list.add(rs.getString("falf_hrs_night")); // 4
				list.add(rs.getString("g_run")); // 5
				list.add(rs.getString("af_hrs")); // 6
				list.add(rs.getString("eng_hrs")); // 7
				list.add(rs.getString("hrs_left")); // 8
				list.add(rs.getString("next_insp")); // 9
				list.add(rs.getString("hrs_monthly")); // 10
				list.add(rs.getString("hrs_qtrly")); // 11
				list.add(rs.getString("hrs_half_year")); // 12
				list.add(rs.getString("hrs_qtrly_flow")); // 13
				list.add(rs.getString("remarks")); // 14
				list.add(rs.getString("date_goi_letter")); // 15
				list.add(rs.getString("eng_ser_no"));// 16
				list.add(rs.getString("days_left"));// 17
				list.add(rs.getString("date_of_pdc")); // 18
				list.add(rs.getString("loc")); // 19
				list.add(rs.getString("ason_date")); // 20
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

	public String setApprovedchtldacr(String sus_no, String username, String issue_date) {
		Date dt = new Date();
		Session session = null;
		Transaction tx = null;
		int app = 0;
		Date asonDate = null;

		
		String timeZone = "Asia/Kolkata"; 

		try {

			
			asonDate = parseDateWithTimeZone(issue_date, "dd-MM-yyyy", timeZone);
			System.err.println(asonDate);
			if (asonDate == null) {
				return "DACR not Approved due to invalid date format.";
			}

			
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			
			Query qDacr = session
					.createQuery("from TB_AVIATION_CHTL_DAILY_BASIS where sus_no=:sus_no  order by acc_no asc");
			qDacr.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<TB_AVIATION_CHTL_DAILY_BASIS> ListDacr = (List<TB_AVIATION_CHTL_DAILY_BASIS>) qDacr.list();

			
			if (ListDacr.isEmpty()) {
				System.out.println("No records found for sus_no: " + sus_no);
				return "DACR not Approved: No matching records found";
			}

			
			String checkQuery = "from TB_AVIATION_CHTL_DAILY_BASIS_HISTORY where sus_no = :sus_no and ason_date = :ason_date";
			Query qCheckHistory = session.createQuery(checkQuery);
			qCheckHistory.setParameter("sus_no", sus_no);
			qCheckHistory.setParameter("ason_date", asonDate);

			if (qCheckHistory.list().isEmpty()) {
				return "DACR not Approved: No matching records found in TB_AVIATION_CHTL_DAILY_BASIS_HISTORY";
			}

			
			String hqlMAIN1 = "update TB_AVIATION_CHTL_DAILY_BASIS_HISTORY c " + "set c.status1 = :status1, "
					+ "c.approved_dt = :approved_dt, " + "c.approved_by = :approved_by, "
					+ "c.modified_date = :modified_date, " + "c.modified_by = :modified_by "
					+ "WHERE c.sus_no = :sus_no and c.ason_date = :ason_date";

			Query queryMain1 = session.createQuery(hqlMAIN1);
			queryMain1.setParameter("status1", "1");
			queryMain1.setParameter("approved_dt", dt);
			queryMain1.setParameter("approved_by", username);
			queryMain1.setParameter("modified_date", dt);
			queryMain1.setParameter("modified_by", username);
			queryMain1.setParameter("sus_no", sus_no);
			queryMain1.setParameter("ason_date", asonDate);
			int rowsAffectedHistory = queryMain1.executeUpdate();
			System.out.println("Rows affected in TB_AVIATION_CHTL_DAILY_BASIS_HISTORY: " + rowsAffectedHistory);

			
			String hqlMAIN = "update TB_AVIATION_CHTL_DAILY_BASIS c set c.status1=:status1, c.approved_dt=:approved_dt, c.approved_by=:approved_by, c.modified_date=:modified_date, c.modified_by=:modified_by  WHERE c.sus_no = :sus_no";
			Query queryMain = session.createQuery(hqlMAIN);
			queryMain.setParameter("status1", "1");
			queryMain.setParameter("modified_by", username);
			queryMain.setParameter("modified_date", dt);
			queryMain.setParameter("approved_dt", dt);
			queryMain.setParameter("approved_by", username);
			queryMain.setParameter("sus_no", sus_no);
			app = queryMain.executeUpdate();

			
			if (app > 0) {
				System.out.println("Update successful for sus_no: " + sus_no);
			} else {
				System.out.println("No rows updated for sus_no: " + sus_no);
			}

			
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback(); 
			e.printStackTrace(); 
			return "DACR not Approved due to error: " + e.getMessage();
		} finally {
			if (session != null)
				session.close(); 
		}

		
		if (app > 0) {
			return "DACR Approved Successfully.";
		} else {
			return "DACR not Approved";
		}
	}

	public String setRejectchtldacr(String sus_no, String username) {
		Date dt = new Date();
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		int app = 0;
		try {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();

			String hqlMAIN = "UPDATE TB_AVIATION_CHTL_DAILY_BASIS set status1=:status1 , modified_date=:modified_date, modified_by=:modified_by WHERE sus_no = :sus_no";
			Query queryMain = session.createQuery(hqlMAIN);
			queryMain.setParameter("status1", "2");
			queryMain.setParameter("modified_by", username);
			queryMain.setParameter("modified_date", dt);
			queryMain.setParameter("sus_no", sus_no);
			app = queryMain.executeUpdate();

			tx.commit();
		} catch (Exception e) {
			tx1.rollback();

		} finally {
			session1.close();
		}
		if (app > 0) {
			return "DACR Rejected Successfully.";
		} else {
			return "DACR not Rejected.";
		}
	}

	public ArrayList<List<String>> getchtlDACRReportListForApproval(String sus_no, String roleType, String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "select a.id, \r\n" + "a.acc_no, \r\n" + "a.status,\r\n" + "a.falf_hrs_day,\r\n"
					+ "a.falf_hrs_night,\r\n" + "a.g_run,\r\n" + "a.af_hrs, \r\n" + "a.eng_hrs, \r\n"
					+ "a.hrs_left, \r\n" + "a.next_insp, \r\n" + "a.hrs_monthly, \r\n" + "a.hrs_qtrly,"
					+ "a.hrs_half_year, \r\n" + "a.hrs_qtrly_flow, \r\n" + "a.remarks, \r\n" + "a.bal_hrs, \r\n"
					+ "ltrim(TO_CHAR(a.date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n"
					+ "ltrim(TO_CHAR(a.ason_date,'dd-mm-yyyy'),'') as ason_date, \r\n" + "a.days_left,\r\n"
					+ "a.eng_ser_no, \r\n" + "b.code_value as loc \r\n"
					+ "from tb_aviation_chtl_daily_basis_history a \r\n"
					+ "left join tb_miso_orbat_code b on b.code=a.loc_code \r\n" + "where a.sus_no=?";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) group by a.id,b.code_value  order by a.acc_no asc";
			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}
			System.err.println("appchtl--" + stmt);
			ResultSet rs = stmt.executeQuery();

			int totalMinutesDay = 0, totalMinutesNight = 0, totalMinutesM = 0, totalMinutesQ = 0, totalMinutesHY = 0,
					totalMinutesY = 0, totalDayNight = 0;
			while (rs.next()) {

				String falfHrsDay = rs.getString("falf_hrs_day");
				if (falfHrsDay != null && !falfHrsDay.isEmpty()) {
					totalMinutesDay += parseTimeToMinutes(falfHrsDay);
				}

				String falfHrsNight = rs.getString("falf_hrs_night");
				if (falfHrsNight != null && !falfHrsNight.isEmpty()) {
					totalMinutesNight += parseTimeToMinutes(falfHrsNight);
				}

				String falfHrsM = rs.getString("hrs_monthly");
				if (falfHrsM != null && !falfHrsM.isEmpty()) {
					totalMinutesM += parseTimeToMinutes(falfHrsM);
				}

				String falfHrsQ = rs.getString("hrs_qtrly");
				if (falfHrsQ != null && !falfHrsQ.isEmpty()) {
					totalMinutesQ += parseTimeToMinutes(falfHrsQ);
				}

				String falfHrsHY = rs.getString("hrs_half_year");
				if (falfHrsHY != null && !falfHrsHY.isEmpty()) {
					totalMinutesHY += parseTimeToMinutes(falfHrsHY);
				}

				String falfHrsY = rs.getString("hrs_qtrly_flow");
				if (falfHrsY != null && !falfHrsY.isEmpty()) {
					totalMinutesY += parseTimeToMinutes(falfHrsY);
				}

				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("acc_no"));// 1
				list.add(rs.getString("status"));// 2
				list.add(rs.getString("falf_hrs_day"));// 3
				list.add(rs.getString("falf_hrs_night")); // 4
				list.add(rs.getString("g_run")); // 5
				list.add(rs.getString("af_hrs")); // 6
				list.add(rs.getString("eng_hrs")); // 7
				list.add(rs.getString("days_left")); // 8
				list.add(rs.getString("hrs_left")); // 9
				list.add(rs.getString("next_insp")); // 10
				list.add(rs.getString("hrs_monthly")); // 11
				list.add(rs.getString("hrs_qtrly")); // 12
				list.add(rs.getString("hrs_half_year")); // 13
				list.add(rs.getString("hrs_qtrly_flow")); // 14
				list.add(rs.getString("remarks")); // 15
				list.add(rs.getString("bal_hrs")); // 16
				list.add(rs.getString("date_goi_letter")); // 17
				list.add(rs.getString("eng_ser_no"));// 18
				list.add(rs.getString("date_of_pdc")); // 19
				list.add(rs.getString("loc")); // 20

				if (rs.isLast()) {
					totalDayNight += totalMinutesDay + totalMinutesNight;
					list.add(formatMinutesToTime(totalMinutesDay)); // 21
					list.add(formatMinutesToTime(totalMinutesNight)); // 22
					list.add(formatMinutesToTime(totalMinutesM)); // 23
					list.add(formatMinutesToTime(totalMinutesQ)); // 24
					list.add(formatMinutesToTime(totalMinutesHY)); // 25
					list.add(formatMinutesToTime(totalMinutesY)); // 26
					list.add(formatMinutesToTime(totalDayNight)); // 27
					list.add(rs.getString("ason_date")); // 28
				} else {
					list.add(""); // 21
					list.add(""); // 22
					list.add(""); // 23
					list.add(""); // 24
					list.add(""); // 25
					list.add(""); // 26
					list.add(""); // 27
					list.add(""); // 28
				}

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

	// NEW ADDED BY MITESH 12-12-2024 && changes by mitesh 26-12-2024
	
	public ArrayList<List<String>> UpdategetDACRReportrpasListPending(String qry, String sus_no, String roleType,
			String roleAccess) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		List<String[]> getlocList = fetchLocList();
		try {
			conn = dataSource.getConnection();
			String sql = "select id, \r\n" + "acc_no, \r\n" + "status,\r\n" + "falf_hrs_day,\r\n"
					+ "falf_hrs_night,\r\n" + "g_run,\r\n" + "af_hrs, \r\n" + "eng_hrs, \r\n" + "hrs_left, \r\n"
					+ "days_left, \r\n" + "next_insp, \r\n" + "hrs_monthly, \r\n" + "hrs_qtrly," + "hrs_half_year, \r\n"
					+ "hrs_qtrly_flow, \r\n" + "remarks, \r\n" + "bal_hrs, \r\n"
					+ "ltrim(TO_CHAR(date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n"
					+ "ltrim(TO_CHAR(ason_date,'dd-mm-yyyy'),'') as ason_date, \r\n" + "ason_date,\r\n"
					+ "eng_ser_no, \r\n" + "loc_code \r\n"
					+ "from tb_aviation_rpas_daily_basis where sus_no=? order by acc_no asc";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			System.err.println("RPASReport--" + stmt);
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id")); // 0
				list.add(rs.getString("acc_no")); // 1
				list.add("<select id='status_" + rs.getString("id") + "' class='form-control'>" + "<option value='S' "
						+ ("S".equals(rs.getString("status")) ? "selected" : "") + ">S</option>" + "<option value='I' "
						+ ("I".equals(rs.getString("status")) ? "selected" : "") + ">I</option>" + "<option value='RI' "
						+ ("RI".equals(rs.getString("status")) ? "selected" : "") + ">RI</option>"
						+ "<option value='RE' " + ("RE".equals(rs.getString("status")) ? "selected" : "")
						+ ">RE</option>" + "<option value='AOG' "
						+ ("AOG".equals(rs.getString("status")) ? "selected" : "") + ">AOG</option>"
						+ "<option value='ACCIDENTAL' "
						+ ("ACCIDENTAL".equals(rs.getString("status")) ? "selected" : "") + ">ACCI</option>"
						+ "<option value='ROH' " + ("ROH".equals(rs.getString("status")) ? "selected" : "")
						+ ">ROH</option>" + "</select>");
				// 2

				
				list.add("<input type='text' id='falf_hrs_day_" + rs.getString("id") + "' value='00:00'"
						+ formatTime(rs.getString("falf_hrs_day"))
						+ "' class='form-control  min='00:00' max='23:59' step='60' maxlength='5' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;''/>"); // 3
				list.add("<input type='text' id='falf_hrs_night_" + rs.getString("id") + "' value='00:00'"
						+ formatTime(rs.getString("falf_hrs_night"))
						+ "' class='form-control  min='00:00' max='23:59' step='60' maxlength='5' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;''/>"); // 4
				list.add("<input type='text' id='g_run_" + rs.getString("id") + "' value='00:00'"
						+ formatTime(rs.getString("g_run"))
						+ "' class='form-control  min='00:00' max='23:59' step='60' maxlength='5' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;''/>"); // 5

				list.add("<input type='text' id='af_hrs_" + rs.getString("id") + "' value='" + rs.getString("af_hrs")
						+ "' class='form-control' readonly/>"); // 6
				list.add("<input type='text' id='eng_hrs_" + rs.getString("id") + "' value='" + rs.getString("eng_hrs")
						+ "' class='form-control' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;'/>"); // 7
				list.add("<input type='text' id='days_left_" + rs.getString("id") + "' value='"
						+ formatTime(rs.getString("days_left")) + "' class='form-control'/>"); // 8
				list.add("<input type='text' id='hrs_left_" + rs.getString("id") + "' value='"
						+ formatTime(rs.getString("hrs_left"))
						+ "' class='form-control' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;'/>"); // 9
				list.add("<input type='text' id='next_insp_" + rs.getString("id") + "' value='"
						+ rs.getString("next_insp") + "' class='form-control'/>"); // 10

				Calendar calendar = Calendar.getInstance();

				
				boolean isFirstDayOfMonth = (calendar.get(Calendar.DAY_OF_MONTH) == 1);

				boolean isFirstDayOfQuarter = isFirstDayOfMonth && (calendar.get(Calendar.MONTH) == Calendar.JANUARY
						|| calendar.get(Calendar.MONTH) == Calendar.APRIL
						|| calendar.get(Calendar.MONTH) == Calendar.JULY
						|| calendar.get(Calendar.MONTH) == Calendar.OCTOBER);

				boolean isFirstDayOfHalfYear = isFirstDayOfMonth && (calendar.get(Calendar.MONTH) == Calendar.APRIL
						|| calendar.get(Calendar.MONTH) == Calendar.OCTOBER);

	
				boolean isFirstDayOfApril = isFirstDayOfMonth && (calendar.get(Calendar.MONTH) == Calendar.APRIL);

				if (isFirstDayOfMonth) {
					list.add("<input type='text' id='hrs_monthly_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_monthly")) + "' class='form-control' readonly/>"); // 11
				} else {
					list.add("<input type='text' id='hrs_monthly_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_monthly")) + "' class='form-control' readonly/>"); // 11
				}

				if (isFirstDayOfQuarter) {

					list.add("<input type='text' id='hrs_qtrly_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_qtrly")) + "' class='form-control' readonly/>"); // 12
				} else {

					list.add("<input type='text' id='hrs_qtrly_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_qtrly")) + "' class='form-control' readonly/>"); // 12
				}

				if (isFirstDayOfHalfYear) {
					list.add("<input type='text' id='hrs_half_year_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_half_year")) + "' class='form-control' readonly/>"); // 13
				} else {
					list.add("<input type='text' id='hrs_half_year_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_half_year")) + "' class='form-control' readonly/>"); // 13
				}

				if (isFirstDayOfApril) {
					list.add("<input type='text' id='hrs_qtrly_flow_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_qtrly_flow")) + "' class='form-control' readonly/>"); // 14
				} else {
					list.add("<input type='text' id='hrs_qtrly_flow_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_qtrly_flow")) + "' class='form-control' readonly/>"); // 14
				}

				list.add("<input type='text' id='remarks_" + rs.getString("id") + "' value='" + rs.getString("remarks")
						+ "' class='form-control'/>"); // 15

				
				String dateAsFlown = "<input type='date' style='font-size: 13px;' id='date_goi_letter_"
						+ rs.getString("id") + "' value='" + rs.getString("date_goi_letter")
						+ "' class='form-control'/>";
				list.add(dateAsFlown); // 16

				
				String update1 = "onclick='calculateHrsForRecord(" + rs.getString("id") + ")'";
				String f1 = "<button id='calId" + rs.getString("id") + "' " + update1
						+ " class='btn circular-btn calButton' style='background-color: blue; color: white;' title='Calculate Hours'><i class='action_icons action_calculate'></i> Calculate</button> &nbsp; &nbsp;";
				list.add(f1); //  17

				String update = "onclick=submitData('" + rs.getString("acc_no") + "','" + rs.getString("id") + "')";
				String f = "<button id='updateId" + rs.getString("id") + "' " + update
						+ " class='btn circular-btn updateButton' style='background-color: blue; color: white;' title='Update Data'>Update</button> &nbsp; &nbsp;";
				list.add(f); // 18

				list.add(rs.getString("eng_ser_no"));// 19

				String asonDateString = rs.getString("ason_date");
				LocalDate asonDate = LocalDate.parse(asonDateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				LocalDate currentDate = LocalDate.now();

				long daysLeft = ChronoUnit.DAYS.between(currentDate, asonDate);
				
				String existingDaysLeftString = rs.getString("days_left");

				
				long existingDaysLeft = 0;
				if (existingDaysLeftString != null && !existingDaysLeftString.isEmpty()) {
					try {
						existingDaysLeft = Long.parseLong(existingDaysLeftString);
					} catch (NumberFormatException e) {
						existingDaysLeft = 0;
					}
				}

				if (existingDaysLeftString != null && !existingDaysLeftString.isEmpty()) {
				
					long totalDaysLeft = existingDaysLeft + daysLeft;

					list.add("<input type='number' id='days_left_" + rs.getString("id") + "' value='" + totalDaysLeft
							+ "' class='form-control'/>");// 20

				} else {
					list.add("<input type='number' id='days_left_" + rs.getString("id") + "' value='"
							+ existingDaysLeftString + "' class='form-control'/>");// 20
				}
				
				String dateofpdc = "<input type='date' style='font-size: 13px;' id='date_of_pdc_" + rs.getString("id")
						+ "' value='" + rs.getString("date_of_pdc") + "' class='form-control'/>";
				list.add(dateofpdc);// 21

				StringBuilder locCodeSelect = new StringBuilder();
				locCodeSelect.append("<select id='loc_code_").append(rs.getString("id"))
						.append("' class='form-control'>");
				locCodeSelect.append("<option value='0' ")
						.append("0".equals(rs.getString("loc_code")) ? "selected" : "").append(">--Select--</option>");
				for (String[] item : getlocList) {
					locCodeSelect.append("<option value='").append(item[0]).append("' ")
							.append(item[0].equals(rs.getString("loc_code")) ? "selected" : "").append(">")
							.append(item[1]).append("</option>");
				}
				locCodeSelect.append("</select>");
				list.add(locCodeSelect.toString()); // 22

				if (rs.isLast()) {

					list.add(rs.getString("ason_date")); // 23
				} else {

					list.add(""); // 23
				}

				alist.add(list);
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
					
				}
			}
		}
		return alist;
	}

	// New Added By Mitesh 16-12-2024
	public ArrayList<List<String>> getSearchRpasReportDacr(String status, String sus_no, String roleType,
			String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			String qry = "";

			if (!status.isEmpty()) {
				qry += " and n.status1 = ?";
			}
			
			if (!sus_no.isEmpty()) {
				qry += " and n.sus_no = ?";
			}
			
			if (!issue_date.isEmpty()) {
				qry += " and cast(n.ason_date as date) = cast(? as date)";
			}


			sql = "select distinct m.sus_no, m.unit_name,ltrim(TO_CHAR(n.ason_date,'dd-mm-yyyy'),'') as ason_date, ltrim(TO_CHAR(n.approved_dt,'dd-mm-yyyy'),'0') as approved_dt "
					+ "from tb_aviation_rpas_daily_basis_history n "
					+ "inner join tb_miso_orbat_unt_dtl m on n.sus_no = m.sus_no " + "where m.status_sus_no = 'Active'"
					+ qry;

			PreparedStatement stmt = conn.prepareStatement(sql);
			int j = 1;

			
			if (!status.isEmpty()) {
				stmt.setString(j, status);
				j++;
			}
	
			if (!sus_no.isEmpty()) {
				stmt.setString(j, sus_no);
				j++;
			}
			
			if (!issue_date.isEmpty()) {
				stmt.setString(j, issue_date);
				j++;
			}
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("approved_dt"));
				list.add(rs.getString("ason_date"));// 3

				
				String View = "onclick=\"View('" + rs.getString("sus_no") + "')\"";
				String appButton = "<i class='action_icons action_approve' " + View + " title='Approve Data'></i>";

				String seen = "onclick=\"Seen('" + rs.getString("sus_no") + "')\"";
				String seenButton = "<i class='btn btn-default btn-xs' " + seen + " title='View Data'>DACR PART A</i>";

				String PartB = "onclick=\" ViewB('" + rs.getString("sus_no") + "')\"";
				String PartBButton = "<i class='btn btn-default btn-xs' " + PartB
						+ " title='View Data'>DACR PART B</i>";
				String f = "";
				
				if (status.equals("0")) {
					if (roleType.equals("ALL") || roleType.equals("APP")) {
						f += appButton;
					}
				}

				if (status.equals("1")) {
					if (roleType.equals("ALL") || roleType.equals("APP")) {
						f += seenButton;
						f += PartBButton;
					}
				}

				if (status.equals("2")) {
					if (roleType.equals("DEO") || roleType.equals("ALL")) {
						f += "No Action Required";
					}
				}
				list.add(f);
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
					e.printStackTrace();
				}
			}
		}
		return alist;
	}

	// changes by Mitesh 26-12-2024
	public ArrayList<List<String>> getRpasDACRReportListForApproval1(String sus_no, String roleType,
			String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "select a.id, \r\n" + "a.acc_no, \r\n" + "a.status,\r\n" + "a.falf_hrs_day,\r\n"
					+ "a.falf_hrs_night,\r\n" + "a.g_run,\r\n" + "a.af_hrs, \r\n" + "a.eng_hrs, \r\n"
					+ "a.hrs_left, \r\n" + "a.next_insp, \r\n" + "a.hrs_monthly, \r\n" + "a.hrs_qtrly,"
					+ "a.hrs_half_year, \r\n" + "a.hrs_qtrly_flow, \r\n" + "a.remarks, \r\n"
					+ "ltrim(TO_CHAR(a.date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n"
					+ "ltrim(TO_CHAR(a.ason_date,'dd-mm-yyyy'),'') as ason_date, \r\n" + "a.days_left,\r\n"
					+ "a.eng_ser_no, \r\n" + "b.code_value as loc \r\n"
					+ "from tb_aviation_rpas_daily_basis_history a \r\n"
					+ "left join tb_miso_orbat_code b on b.code=a.loc_code \r\n" + "where a.sus_no=? ";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) group by a.id,b.code_value  order by a.acc_no asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}
			System.err.println("Pendingrpas1---" + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("acc_no"));// 1
				list.add(rs.getString("status"));// 2
				list.add(rs.getString("falf_hrs_day"));// 3
				list.add(rs.getString("falf_hrs_night")); // 4
				list.add(rs.getString("g_run")); // 5
				list.add(rs.getString("af_hrs")); // 6
				list.add(rs.getString("eng_hrs")); // 7
				list.add(rs.getString("hrs_left")); // 8
				list.add(rs.getString("next_insp")); // 9
				list.add(rs.getString("hrs_monthly")); // 10
				list.add(rs.getString("hrs_qtrly")); // 11
				list.add(rs.getString("hrs_half_year")); // 12
				list.add(rs.getString("hrs_qtrly_flow")); // 13
				list.add(rs.getString("remarks")); // 14
				list.add(rs.getString("date_goi_letter")); // 15
				list.add(rs.getString("eng_ser_no"));// 16
				list.add(rs.getString("days_left"));// 17
				list.add(rs.getString("date_of_pdc")); // 18
				list.add(rs.getString("loc")); // 19
				list.add(rs.getString("ason_date")); // 20
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

	public String setApprovedrpasdacr(String sus_no, String username, String issue_date) {
		Date dt = new Date();
		Session session = null;
		Transaction tx = null;
		int app = 0;
		Date asonDate = null;

		
		String timeZone = "Asia/Kolkata"; 

		try {

			
			asonDate = parseDateWithTimeZone(issue_date, "dd-MM-yyyy", timeZone);
			System.err.println(asonDate);
			if (asonDate == null) {
				return "DACR not Approved due to invalid date format.";
			}
			
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			
			Query qDacr = session
					.createQuery("from TB_AVIATION_RPAS_DAILY_BASIS where sus_no=:sus_no  order by acc_no asc");
			qDacr.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<TB_AVIATION_RPAS_DAILY_BASIS> ListDacr = (List<TB_AVIATION_RPAS_DAILY_BASIS>) qDacr.list();

			
			if (ListDacr.isEmpty()) {
				System.out.println("No records found for sus_no: " + sus_no);
				return "DACR not Approved: No matching records found";
			}

			
			String checkQuery = "from TB_AVIATION_RPAS_DAILY_BASIS_HISTORY where sus_no = :sus_no and ason_date = :ason_date";
			Query qCheckHistory = session.createQuery(checkQuery);
			qCheckHistory.setParameter("sus_no", sus_no);
			qCheckHistory.setParameter("ason_date", asonDate);

			if (qCheckHistory.list().isEmpty()) {
				return "DACR not Approved: No matching records found";
			}

			
			String hqlMAIN1 = "update TB_AVIATION_RPAS_DAILY_BASIS_HISTORY c " + "set c.status1 = :status1, "
					+ "c.approved_dt = :approved_dt, " + "c.approved_by = :approved_by, "
					+ "c.modified_date = :modified_date, " + "c.modified_by = :modified_by "
					+ "WHERE c.sus_no = :sus_no and c.ason_date = :ason_date";

			Query queryMain1 = session.createQuery(hqlMAIN1);
			queryMain1.setParameter("status1", "1");
			queryMain1.setParameter("approved_dt", dt);
			queryMain1.setParameter("approved_by", username);
			queryMain1.setParameter("modified_date", dt);
			queryMain1.setParameter("modified_by", username);
			queryMain1.setParameter("sus_no", sus_no);
			queryMain1.setParameter("ason_date", asonDate);
			int rowsAffectedHistory = queryMain1.executeUpdate();
			System.out.println("Rows affected in TB_AVIATION_DAILY_BASIS_HISTORY: " + rowsAffectedHistory);

			
			String hqlMAIN = "update TB_AVIATION_RPAS_DAILY_BASIS c set c.status1=:status1, c.approved_dt=:approved_dt, c.approved_by=:approved_by, c.modified_date=:modified_date, c.modified_by=:modified_by  WHERE c.sus_no = :sus_no";
			Query queryMain = session.createQuery(hqlMAIN);
			queryMain.setParameter("status1", "1");
			queryMain.setParameter("modified_by", username);
			queryMain.setParameter("modified_date", dt);
			queryMain.setParameter("approved_dt", dt);
			queryMain.setParameter("approved_by", username);
			queryMain.setParameter("sus_no", sus_no);
			app = queryMain.executeUpdate();

			
			if (app > 0) {
				System.out.println("Update successful for sus_no: " + sus_no);
			} else {
				System.out.println("No rows updated for sus_no: " + sus_no);
			}

			
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback(); 
			e.printStackTrace(); 
			return "DACR not Approved due to error: " + e.getMessage();
		} finally {
			if (session != null)
				session.close(); 
		}

		
		if (app > 0) {
			return "DACR Approved Successfully.";
		} else {
			return "DACR not Approved";
		}
	}

	public String setRejectrpasdacr(String sus_no, String username) {
		Date dt = new Date();
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		int app = 0;
		try {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();

			String hqlMAIN = "UPDATE TB_AVIATION_RPAS_DAILY_BASIS set status1=:status1 , modified_date=:modified_date, modified_by=:modified_by WHERE sus_no = :sus_no";
			Query queryMain = session.createQuery(hqlMAIN);
			queryMain.setParameter("status1", "2");
			queryMain.setParameter("modified_by", username);
			queryMain.setParameter("modified_date", dt);
			queryMain.setParameter("sus_no", sus_no);
			app = queryMain.executeUpdate();

			tx.commit();
		} catch (Exception e) {
			tx1.rollback();

		} finally {
			session1.close();
		}
		if (app > 0) {
			return "DACR Rejected Successfully.";
		} else {
			return "DACR not Rejected.";
		}
	}

	public ArrayList<List<String>> getrpasDACRReportListForApproval(String sus_no, String roleType, String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			/*
			 * if(!issue_date.equals("")) { sql =
			 * "select * from TB_AVIATION_DAILY_BASIS_HISTORY where sus_no=? "; }
			 */
			System.out.println("val of issue date in daoimpl dacr" + issue_date);
			sql = "select a.id, \r\n" + "a.acc_no, \r\n" + "a.status,\r\n" + "a.falf_hrs_day,\r\n"
					+ "a.falf_hrs_night,\r\n" + "a.g_run,\r\n" + "a.af_hrs, \r\n" + "a.eng_hrs, \r\n"
					+ "a.hrs_left, \r\n" + "a.days_left, \r\n" + "a.next_insp, \r\n" + "a.hrs_monthly, \r\n"
					+ "a.hrs_qtrly," + "a.hrs_half_year, \r\n" + "a.hrs_qtrly_flow, \r\n" + "a.remarks, \r\n"
					+ "a.bal_hrs, \r\n" + "ltrim(TO_CHAR(a.date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n"
					+ "ltrim(TO_CHAR(a.ason_date,'dd-mm-yyyy'),'') as ason_date, \r\n" + "a.eng_ser_no, \r\n"
					+ "b.code_value as loc \r\n" + "from tb_aviation_rpas_daily_basis_history a \r\n"
					+ "left join tb_miso_orbat_code b on b.code=a.loc_code \r\n" + "where sus_no=? ";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) group by a.id,b.code_value  order by a.acc_no asc";
			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}
			System.err.println("apprpas--" + stmt);
			ResultSet rs = stmt.executeQuery();

			int totalMinutesDay = 0, totalMinutesNight = 0, totalMinutesM = 0, totalMinutesQ = 0, totalMinutesHY = 0,
					totalMinutesY = 0, totalDayNight = 0;

			while (rs.next()) {

				String falfHrsDay = rs.getString("falf_hrs_day");
				if (falfHrsDay != null && !falfHrsDay.isEmpty()) {
					totalMinutesDay += parseTimeToMinutes(falfHrsDay);
				}

				String falfHrsNight = rs.getString("falf_hrs_night");
				if (falfHrsNight != null && !falfHrsNight.isEmpty()) {
					totalMinutesNight += parseTimeToMinutes(falfHrsNight);
				}

				String falfHrsM = rs.getString("hrs_monthly");
				if (falfHrsM != null && !falfHrsM.isEmpty()) {
					totalMinutesM += parseTimeToMinutes(falfHrsM);
				}

				String falfHrsQ = rs.getString("hrs_qtrly");
				if (falfHrsQ != null && !falfHrsQ.isEmpty()) {
					totalMinutesQ += parseTimeToMinutes(falfHrsQ);
				}

				String falfHrsHY = rs.getString("hrs_half_year");
				if (falfHrsHY != null && !falfHrsHY.isEmpty()) {
					totalMinutesHY += parseTimeToMinutes(falfHrsHY);
				}

				String falfHrsY = rs.getString("hrs_qtrly_flow");
				if (falfHrsY != null && !falfHrsY.isEmpty()) {
					totalMinutesY += parseTimeToMinutes(falfHrsY);
				}

				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("acc_no"));// 1
				list.add(rs.getString("status"));// 2
				list.add(rs.getString("falf_hrs_day"));// 3
				list.add(rs.getString("falf_hrs_night")); // 4
				list.add(rs.getString("g_run")); // 5
				list.add(rs.getString("af_hrs")); // 6
				list.add(rs.getString("eng_hrs")); // 7
				list.add(rs.getString("days_left")); // 8
				list.add(rs.getString("hrs_left")); // 9
				list.add(rs.getString("next_insp")); // 10
				list.add(rs.getString("hrs_monthly")); // 11
				list.add(rs.getString("hrs_qtrly")); // 12
				list.add(rs.getString("hrs_half_year")); // 13
				list.add(rs.getString("hrs_qtrly_flow")); // 14
				list.add(rs.getString("remarks")); // 15
				list.add(rs.getString("bal_hrs")); // 16
				list.add(rs.getString("date_goi_letter")); // 17
				list.add(rs.getString("eng_ser_no"));// 18
				list.add(rs.getString("date_of_pdc")); // 19
				list.add(rs.getString("loc")); // 20

				if (rs.isLast()) {
					totalDayNight += totalMinutesDay + totalMinutesNight;
					list.add(formatMinutesToTime(totalMinutesDay)); // 21
					list.add(formatMinutesToTime(totalMinutesNight)); // 22
					list.add(formatMinutesToTime(totalMinutesM)); // 23
					list.add(formatMinutesToTime(totalMinutesQ)); // 24
					list.add(formatMinutesToTime(totalMinutesHY)); // 25
					list.add(formatMinutesToTime(totalMinutesY)); // 26
					list.add(formatMinutesToTime(totalDayNight)); // 27
					list.add(rs.getString("ason_date")); // 28
				} else {
					list.add(""); // 21
					list.add(""); // 22
					list.add(""); // 23
					list.add(""); // 24
					list.add(""); // 25
					list.add(""); // 26
					list.add(""); // 27
					list.add(""); // 28
				}
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

	// Method to fetch the location list 26-12-24
	public List<String[]> fetchLocList() {
		List<String[]> locList = new ArrayList<>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select code,code_value from tb_miso_orbat_code where code_type='Location' and status_record='1' order by code_value"; 
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				locList.add(new String[] { rs.getString("code"), rs.getString("code_value") });
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
		return locList;
	}

	
	private int parseTimeToMinutes(String time) {
		String[] parts = time.split(":");
		int hours = Integer.parseInt(parts[0]);
		int minutes = Integer.parseInt(parts[1]);
		return (hours * 60) + minutes;
	}

	private String formatMinutesToTime(int totalMinutes) {
		int hours = totalMinutes / 60;
		int minutes = totalMinutes % 60;
		return String.format("%02d:%02d", hours, minutes);
	}

	
	private Date parseDateWithTimeZone(String dateString, String format, String timeZone) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone)); 
			return dateFormat.parse(dateString); 
		} catch (ParseException e) {
			e.printStackTrace();
			return null; 
		}
	}

	// For MR & SOW STATE Added By Mitesh 08-01-2025
	public ArrayList<List<String>> UpdategetDACRReportList(String qry1, String sus_no, String roleType,
			String roleAccess) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		List<String[]> getlocList = fetchLocList();
		try {
			conn = dataSource.getConnection();
			String sql = "select id, \r\n" + "acc_no, \r\n" + "status,\r\n" + "falf_hrs_day,\r\n"
					+ "falf_hrs_night,\r\n" + "g_run,\r\n" + "af_hrs, \r\n" + "eng_hrs_left, \r\n"
					+ "eng_hrs_rigth, \r\n" + "hrs_left, \r\n" + "next_insp, \r\n" + "hrs_monthly, \r\n" + "hrs_qtrly,"
					+ "hrs_half_year, \r\n" + "hrs_qtrly_flow, \r\n" + "remarks, \r\n" + "bal_hrs, \r\n"
					+ "ltrim(TO_CHAR(date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n"
					+ "ltrim(TO_CHAR(ason_dt,'dd-mm-yyyy'),'') as ason_dt, \r\n" + "lh_ser_no,\r\n" + "rh_ser_no, \r\n"
					+ "loc_code, \r\n" + "aircraft_state \r\n"
					+ "from tb_aviation_daily_basis where sus_no=? and aircraft_state ='0' order by acc_no asc";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			

			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id")); // 0
				list.add(rs.getString("acc_no")); // 1
				list.add("<select id='status_" + rs.getString("id") + "' class='form-control'>" + "<option value='S' "
						+ ("S".equals(rs.getString("status")) ? "selected" : "") + ">S</option>" + "<option value='I' "
						+ ("I".equals(rs.getString("status")) ? "selected" : "") + ">I</option>" + "<option value='RI' "
						+ ("RI".equals(rs.getString("status")) ? "selected" : "") + ">RI</option>"
						+ "<option value='RE' " + ("RE".equals(rs.getString("status")) ? "selected" : "")
						+ ">RE</option>" + "<option value='AOG' "
						+ ("AOG".equals(rs.getString("status")) ? "selected" : "") + ">AOG</option>"
						+ "<option value='ACCIDENTAL' "
						+ ("ACCIDENTAL".equals(rs.getString("status")) ? "selected" : "") + ">ACCI</option>"
						+ "<option value='ROH' " + ("ROH".equals(rs.getString("status")) ? "selected" : "")
						+ ">ROH</option>" + "</select>");
				// 2

				
				list.add("<input type='text' id='falf_hrs_day_" + rs.getString("id") + "' value='00:00'"
						+ formatTime(rs.getString("falf_hrs_day"))
						+ "' class='form-control  min='00:00' max='23:59' step='60' maxlength='5'  oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;''/>"); // 3
				list.add("<input type='text' id='falf_hrs_night_" + rs.getString("id") + "' value='00:00'"
						+ formatTime(rs.getString("falf_hrs_night"))
						+ "' class='form-control  min='00:00' max='23:59' step='60' maxlength='5' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;''/>"); // 4
				list.add("<input type='text' id='g_run_" + rs.getString("id") + "' value='00:00'"
						+ formatTime(rs.getString("g_run"))
						+ "' class='form-control  min='00:00' max='23:59' step='60' maxlength='5' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;''/>"); // 5

				list.add("<input type='text' id='af_hrs_" + rs.getString("id") + "' value='" + rs.getString("af_hrs")
						+ "' class='form-control' readonly/>"); // 6
				list.add("<input type='text' id='eng_hrs_left_" + rs.getString("id") + "' value='"
						+ rs.getString("eng_hrs_left") + "' class='form-control' readonly/>"); // 7
				list.add("<input type='text' id='eng_hrs_rigth_" + rs.getString("id") + "' value='"
						+ rs.getString("eng_hrs_rigth") + "' class='form-control' readonly/>"); // 8

				list.add("<input type='text' id='hrs_left_" + rs.getString("id") + "' value='"
						+ formatTime(rs.getString("hrs_left"))
						+ "' class='form-control' oninput='maintainColonFormat(event)'  onkeydown='return blockColon(event)' onfocus='ensureLeadingZero(event)'  onselectstart='return false;'/>"); // 9
				list.add("<input type='text' id='next_insp_" + rs.getString("id") + "' value='"
						+ rs.getString("next_insp") + "' class='form-control'/>"); // 10

				Calendar calendar = Calendar.getInstance();

				
				boolean isFirstDayOfMonth = (calendar.get(Calendar.DAY_OF_MONTH) == 1);

				
				boolean isFirstDayOfQuarter = isFirstDayOfMonth && (calendar.get(Calendar.MONTH) == Calendar.JANUARY
						|| calendar.get(Calendar.MONTH) == Calendar.APRIL
						|| calendar.get(Calendar.MONTH) == Calendar.JULY
						|| calendar.get(Calendar.MONTH) == Calendar.OCTOBER);

				
				boolean isFirstDayOfHalfYear = isFirstDayOfMonth && (calendar.get(Calendar.MONTH) == Calendar.APRIL
						|| calendar.get(Calendar.MONTH) == Calendar.OCTOBER);

				
				boolean isFirstDayOfApril = isFirstDayOfMonth && (calendar.get(Calendar.MONTH) == Calendar.APRIL);

				if (isFirstDayOfMonth) {
					list.add("<input type='text' id='hrs_monthly_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_monthly")) + "' class='form-control' readonly/>"); // 11
				} else {
					list.add("<input type='text' id='hrs_monthly_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_monthly")) + "' class='form-control' readonly/>"); // 11
				}

				if (isFirstDayOfQuarter) {
					list.add("<input type='text' id='hrs_qtrly_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_qtrly")) + "' class='form-control' readonly/>"); // 12
				} else {
					list.add("<input type='text' id='hrs_qtrly_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_qtrly")) + "' class='form-control' readonly/>"); // 12
				}

				if (isFirstDayOfHalfYear) {
					list.add("<input type='text' id='hrs_half_year_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_half_year")) + "' class='form-control' readonly/>"); // 13
				} else {
					list.add("<input type='text' id='hrs_half_year_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_half_year")) + "' class='form-control' readonly/>"); // 13
				}

				if (isFirstDayOfApril) {
					list.add("<input type='text' id='hrs_qtrly_flow_" + rs.getString("id") + "' value='00:00'"
							+ formatTime(rs.getString("hrs_qtrly_flow")) + "' class='form-control' readonly/>"); // 14
				} else {
					list.add("<input type='text' id='hrs_qtrly_flow_" + rs.getString("id") + "' value='"
							+ formatTime(rs.getString("hrs_qtrly_flow")) + "' class='form-control' readonly/>"); // 14
				}

				list.add("<input type='text' id='remarks_" + rs.getString("id") + "' value='" + rs.getString("remarks")
						+ "' class='form-control'/>"); // 15

				
				String dateAsFlown = "<input type='date' style='font-size: 13px;' id='date_goi_letter_"
						+ rs.getString("id") + "' value='" + rs.getString("date_goi_letter")
						+ "' class='form-control'/>";
				list.add(dateAsFlown);

				
				String update1 = "onclick='calculateHrsForRecord(" + rs.getString("id") + ")'";
				String f1 = "<button id='calId" + rs.getString("id") + "' " + update1
						+ " class='btn circular-btn calButton' style='background-color: blue; color: white;' title='Calculate Hours'><i class='action_icons action_calculate'></i> Calculate</button> &nbsp; &nbsp;";
				list.add(f1); // 17

				String update = "onclick=submitData('" + rs.getString("acc_no") + "','" + rs.getString("id") + "')";
				String f = "<button id='updateId" + rs.getString("id") + "' " + update
						+ " class='btn circular-btn updateButton'  style='background-color: blue; color: white;' title='Update Data'>Update</button> &nbsp; &nbsp;";
				list.add(f); // 18

				list.add(rs.getString("lh_ser_no"));
				list.add(rs.getString("rh_ser_no"));

				list.add("<input type='text' id='lh_ser_no_" + rs.getString("id") + "' value='"
						+ rs.getString("lh_ser_no") + "' class='form-control' readonly/>");
				list.add("<input type='text' id='rh_ser_no_" + rs.getString("id") + "' value='"
						+ rs.getString("rh_ser_no") + "' class='form-control' readonly/>");

				
				String dateofpdc = "<input type='date' style='font-size: 13px;' id='date_of_pdc_" + rs.getString("id")
						+ "' value='" + rs.getString("date_of_pdc") + "' class='form-control'/>";
				list.add(dateofpdc);

				
				StringBuilder locCodeSelect = new StringBuilder();
				locCodeSelect.append("<select id='loc_code_").append(rs.getString("id"))
						.append("' class='form-control'>");
				locCodeSelect.append("<option value='0' ")
						.append("0".equals(rs.getString("loc_code")) ? "selected" : "").append(">--Select--</option>");
				for (String[] item : getlocList) {
					locCodeSelect.append("<option value='").append(item[0]).append("' ")
							.append(item[0].equals(rs.getString("loc_code")) ? "selected" : "").append(">")
							.append(item[1]).append("</option>");
				}
				locCodeSelect.append("</select>");
				list.add(locCodeSelect.toString()); // 24

				if (rs.isLast()) {

					list.add(rs.getString("ason_dt")); // 25
				} else {

					list.add(""); // 25
				}

				list.add(rs.getString("aircraft_state")); // 26

				alist.add(list);
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
					
				}
			}
		}
		return alist;
	}

	public ArrayList<List<String>> getDACRReportMRList(String sus_no, String roleType, String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "select a.id as id, \r\n" + "a.acc_no as acc_no, \r\n" + "a.status as status,\r\n"
					+ "a.falf_hrs_day as falf_hrs_day,\r\n" + "a.falf_hrs_night as falf_hrs_night,\r\n"
					+ "a.g_run as g_run,\r\n" + "a.af_hrs as af_hrs, \r\n" + "a.eng_hrs_left as eng_hrs_left, \r\n"
					+ "a.eng_hrs_rigth as eng_hrs_rigth, \r\n" + "a.hrs_left as hrs_left, \r\n"
					+ "a.next_insp as next_insp, \r\n" + "a.hrs_monthly as hrs_monthly, \r\n"
					+ "a.hrs_qtrly as hrs_qtrly," + "a.hrs_half_year as hrs_half_year, \r\n"
					+ "a.hrs_qtrly_flow as hrs_qtrly_flow, \r\n" + "a.remarks as remarks, \r\n"
					+ "ltrim(TO_CHAR(a.date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n"
					+ "ltrim(TO_CHAR(a.ason_dt,'dd-mm-yyyy'),'') as ason_dt, \r\n" + "a.lh_ser_no as lh_ser_no,\r\n"
					+ "a.rh_ser_no as rh_ser_no, \r\n" + "b.code_value as loc \r\n"
					+ "from tb_aviation_daily_basis_history a \r\n"
					+ "left join tb_miso_orbat_code b on b.code=a.loc_code \r\n"
					+ "where a.sus_no=? and a.aircraft_state ='0'";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_dt as date) = cast(? as date) group by a.id,b.code_value  order by a.acc_no asc";
				// sql += " and n.approved_dt = ? ";
			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("acc_no"));// 1
				list.add(rs.getString("status"));// 2
				list.add(rs.getString("falf_hrs_day"));// 3
				list.add(rs.getString("falf_hrs_night")); // 4
				list.add(rs.getString("g_run")); // 5
				list.add(rs.getString("af_hrs")); // 6
				list.add(rs.getString("eng_hrs_left")); // 7
				list.add(rs.getString("eng_hrs_rigth")); // 8
				list.add(rs.getString("hrs_left")); // 9
				list.add(rs.getString("next_insp")); // 10
				list.add(rs.getString("hrs_monthly")); // 11
				list.add(rs.getString("hrs_qtrly")); // 12
				list.add(rs.getString("hrs_half_year")); // 13
				list.add(rs.getString("hrs_qtrly_flow")); // 14
				list.add(rs.getString("remarks")); // 15
				list.add(rs.getString("date_goi_letter")); // 16
				list.add(rs.getString("lh_ser_no"));// 17
				list.add(rs.getString("rh_ser_no"));// 18
				list.add(rs.getString("date_of_pdc")); // 19
				list.add(rs.getString("loc")); // 20
				list.add(rs.getString("ason_dt")); // 21

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

	public ArrayList<List<String>> getDACRReportOTHERListForApproval(String sus_no, String roleType,
			String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "select a.id as id, \r\n" + "a.acc_no as acc_no, \r\n" + "a.status as status,\r\n"
					+ "a.falf_hrs_day as falf_hrs_day,\r\n" + "a.falf_hrs_night as falf_hrs_night,\r\n"
					+ "a.g_run as g_run,\r\n" + "a.af_hrs as af_hrs, \r\n" + "a.eng_hrs_left as eng_hrs_left, \r\n"
					+ "a.eng_hrs_rigth as eng_hrs_rigth, \r\n" + "a.hrs_left as hrs_left, \r\n"
					+ "a.next_insp as next_insp, \r\n" + "a.hrs_monthly hrs_monthly, \r\n" + "a.hrs_qtrly as hrs_qtrly,"
					+ "a.hrs_half_year as hrs_half_year, \r\n" + "a.hrs_qtrly_flow as hrs_qtrly_flow, \r\n"
					+ "a.remarks as remarks, \r\n" + "a.bal_hrs as bal_hrs, \r\n"
					+ "ltrim(TO_CHAR(a.date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n"
					+ "ltrim(TO_CHAR(a.ason_dt,'dd-mm-yyyy'),'') as ason_dt, \r\n" + "a.lh_ser_no as lh_ser_no,\r\n"
					+ "a.rh_ser_no as rh_ser_no, \r\n" + "b.code_value as loc \r\n"
					+ "from tb_aviation_daily_basis_history a \r\n"
					+ "left join tb_miso_orbat_code b on b.code=a.loc_code \r\n"
					+ " where a.sus_no=? and (a.aircraft_state ='1' or a.aircraft_state is null)";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_dt as date) = cast(? as date) group by a.id,b.code_value  order by a.acc_no asc";
				// sql += " and n.approved_dt = ? ";
			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}
			System.err.println("appOther--" + stmt);
			ResultSet rs = stmt.executeQuery();
			int totalMinutesDay = 0, totalMinutesNight = 0, totalMinutesM = 0, totalMinutesQ = 0, totalMinutesHY = 0,
					totalMinutesY = 0, totalDayNight = 0;
			while (rs.next()) {

				String falfHrsDay = rs.getString("falf_hrs_day");
				if (falfHrsDay != null && !falfHrsDay.isEmpty()) {
					totalMinutesDay += parseTimeToMinutes(falfHrsDay);
				}

				String falfHrsNight = rs.getString("falf_hrs_night");
				if (falfHrsNight != null && !falfHrsNight.isEmpty()) {
					totalMinutesNight += parseTimeToMinutes(falfHrsNight);
				}

				String falfHrsM = rs.getString("hrs_monthly");
				if (falfHrsM != null && !falfHrsM.isEmpty()) {
					totalMinutesM += parseTimeToMinutes(falfHrsM);
				}

				String falfHrsQ = rs.getString("hrs_qtrly");
				if (falfHrsQ != null && !falfHrsQ.isEmpty()) {
					totalMinutesQ += parseTimeToMinutes(falfHrsQ);
				}

				String falfHrsHY = rs.getString("hrs_half_year");
				if (falfHrsHY != null && !falfHrsHY.isEmpty()) {
					totalMinutesHY += parseTimeToMinutes(falfHrsHY);
				}

				String falfHrsY = rs.getString("hrs_qtrly_flow");
				if (falfHrsY != null && !falfHrsY.isEmpty()) {
					totalMinutesY += parseTimeToMinutes(falfHrsY);
				}

				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("acc_no"));// 1
				list.add(rs.getString("status"));// 2
				list.add(rs.getString("falf_hrs_day"));// 3
				list.add(rs.getString("falf_hrs_night")); // 4
				list.add(rs.getString("g_run")); // 5
				list.add(rs.getString("af_hrs")); // 6
				list.add(rs.getString("eng_hrs_left")); // 7
				list.add(rs.getString("eng_hrs_rigth")); // 8
				list.add(rs.getString("hrs_left")); // 9
				list.add(rs.getString("next_insp")); // 10
				list.add(rs.getString("hrs_monthly")); // 11
				list.add(rs.getString("hrs_qtrly")); // 12
				list.add(rs.getString("hrs_half_year")); // 13
				list.add(rs.getString("hrs_qtrly_flow")); // 14
				list.add(rs.getString("remarks")); // 15
				list.add(rs.getString("bal_hrs")); // 16
				list.add(rs.getString("date_goi_letter")); // 17
				list.add(rs.getString("lh_ser_no"));// 18
				list.add(rs.getString("rh_ser_no"));// 19
				list.add(rs.getString("date_of_pdc")); // 20
				list.add(rs.getString("loc")); // 21

				if (rs.isLast()) {
					totalDayNight += totalMinutesDay + totalMinutesNight;
					list.add(formatMinutesToTime(totalMinutesDay)); // 22
					list.add(formatMinutesToTime(totalMinutesNight)); // 23
					list.add(formatMinutesToTime(totalMinutesM)); // 24
					list.add(formatMinutesToTime(totalMinutesQ)); // 25
					list.add(formatMinutesToTime(totalMinutesHY)); // 26
					list.add(formatMinutesToTime(totalMinutesY)); // 27
					list.add(formatMinutesToTime(totalDayNight)); // 28
					list.add(rs.getString("ason_dt")); // 29
				} else {
					list.add(""); // 22
					list.add(""); // 23
					list.add(""); // 24
					list.add(""); // 25
					list.add(""); // 26
					list.add(""); // 27
					list.add(""); // 28
					list.add(""); // 29
				}

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

	public ArrayList<List<String>> getDACRReportMRListForApproval(String sus_no, String roleType, String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "select a.id as id, \r\n" + "a.acc_no as acc_no, \r\n" + "a.status as status,\r\n"
					+ "a.falf_hrs_day as falf_hrs_day,\r\n" + "a.falf_hrs_night as falf_hrs_night,\r\n"
					+ "a.g_run as g_run,\r\n" + "a.af_hrs as af_hrs, \r\n" + "a.eng_hrs_left as eng_hrs_left, \r\n"
					+ "a.eng_hrs_rigth as eng_hrs_rigth, \r\n" + "a.hrs_left as hrs_left, \r\n"
					+ "a.next_insp as next_insp, \r\n" + "a.hrs_monthly hrs_monthly, \r\n" + "a.hrs_qtrly as hrs_qtrly,"
					+ "a.hrs_half_year as hrs_half_year, \r\n" + "a.hrs_qtrly_flow as hrs_qtrly_flow, \r\n"
					+ "a.remarks as remarks, \r\n" + "a.bal_hrs as bal_hrs, \r\n"
					+ "ltrim(TO_CHAR(a.date_goi_letter,'dd-mm-yyyy'),'') as date_goi_letter, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_pdc,'dd-mm-yyyy'),'') as date_of_pdc, \r\n"
					+ "ltrim(TO_CHAR(a.ason_dt,'dd-mm-yyyy'),'') as ason_dt, \r\n" + "a.lh_ser_no as lh_ser_no,\r\n"
					+ "a.rh_ser_no as rh_ser_no, \r\n" + "b.code_value as loc \r\n"
					+ "from tb_aviation_daily_basis_history a \r\n"
					+ "left join tb_miso_orbat_code b on b.code=a.loc_code \r\n"
					+ " where a.sus_no=? and a.aircraft_state ='0' ";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_dt as date) = cast(? as date) group by a.id,b.code_value  order by a.acc_no asc";
				// sql += " and n.approved_dt = ? ";
			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}
			
			ResultSet rs = stmt.executeQuery();
			int totalMinutesDay = 0, totalMinutesNight = 0, totalMinutesM = 0, totalMinutesQ = 0, totalMinutesHY = 0,
					totalMinutesY = 0, totalDayNight = 0;
			while (rs.next()) {

				String falfHrsDay = rs.getString("falf_hrs_day");
				if (falfHrsDay != null && !falfHrsDay.isEmpty()) {
					totalMinutesDay += parseTimeToMinutes(falfHrsDay);
				}

				String falfHrsNight = rs.getString("falf_hrs_night");
				if (falfHrsNight != null && !falfHrsNight.isEmpty()) {
					totalMinutesNight += parseTimeToMinutes(falfHrsNight);
				}

				String falfHrsM = rs.getString("hrs_monthly");
				if (falfHrsM != null && !falfHrsM.isEmpty()) {
					totalMinutesM += parseTimeToMinutes(falfHrsM);
				}

				String falfHrsQ = rs.getString("hrs_qtrly");
				if (falfHrsQ != null && !falfHrsQ.isEmpty()) {
					totalMinutesQ += parseTimeToMinutes(falfHrsQ);
				}

				String falfHrsHY = rs.getString("hrs_half_year");
				if (falfHrsHY != null && !falfHrsHY.isEmpty()) {
					totalMinutesHY += parseTimeToMinutes(falfHrsHY);
				}

				String falfHrsY = rs.getString("hrs_qtrly_flow");
				if (falfHrsY != null && !falfHrsY.isEmpty()) {
					totalMinutesY += parseTimeToMinutes(falfHrsY);
				}

				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("acc_no"));// 1
				list.add(rs.getString("status"));// 2
				list.add(rs.getString("falf_hrs_day"));// 3
				list.add(rs.getString("falf_hrs_night")); // 4
				list.add(rs.getString("g_run")); // 5
				list.add(rs.getString("af_hrs")); // 6
				list.add(rs.getString("eng_hrs_left")); // 7
				list.add(rs.getString("eng_hrs_rigth")); // 8
				list.add(rs.getString("hrs_left")); // 9
				list.add(rs.getString("next_insp")); // 10
				list.add(rs.getString("hrs_monthly")); // 11
				list.add(rs.getString("hrs_qtrly")); // 12
				list.add(rs.getString("hrs_half_year")); // 13
				list.add(rs.getString("hrs_qtrly_flow")); // 14
				list.add(rs.getString("remarks")); // 15
				list.add(rs.getString("bal_hrs")); // 16
				list.add(rs.getString("date_goi_letter")); // 17
				list.add(rs.getString("lh_ser_no"));// 18
				list.add(rs.getString("rh_ser_no"));// 19
				list.add(rs.getString("date_of_pdc")); // 20
				list.add(rs.getString("loc")); // 21

				if (rs.isLast()) {
					totalDayNight += totalMinutesDay + totalMinutesNight;
					list.add(formatMinutesToTime(totalMinutesDay)); // 22
					list.add(formatMinutesToTime(totalMinutesNight)); // 23
					list.add(formatMinutesToTime(totalMinutesM)); // 24
					list.add(formatMinutesToTime(totalMinutesQ)); // 25
					list.add(formatMinutesToTime(totalMinutesHY)); // 26
					list.add(formatMinutesToTime(totalMinutesY)); // 27
					list.add(formatMinutesToTime(totalDayNight)); // 28
					list.add(rs.getString("ason_dt")); // 29
				} else {
					list.add(""); // 22
					list.add(""); // 23
					list.add(""); // 24
					list.add(""); // 25
					list.add(""); // 26
					list.add(""); // 27
					list.add(""); // 28
					list.add(""); // 29
				}

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

	// New added by mitesh 10-01-2025
	public ArrayList<ArrayList<String>> getdetails_ue_uhDacrlist(String sus_no, String issue_date) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "SELECT ms.prf_group, m.item_code, d.act_main_id, " + "d.act_nomen, " + "m.total_ue_qty AS UE, "
					+ "COUNT(a.acc_no) AS UH, " + "COUNT(a.acc_no) FILTER (WHERE a.status='S') AS totalS, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='I') AS totalI, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='RI') AS totalRI, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='RE') AS totalRE, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='AOG') AS totalAOG, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='ACCIDENTAL') AS totalACCI, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='ROH') AS totalOH "
					+ "FROM tb_aviation_daily_basis_history a "
					+ "LEFT JOIN mms_ue_mview m on m.sus_no = a.sus_no and m.item_code in ('1501014E','1501017Y')"
					+ "LEFT JOIN cue_tb_miso_prf_group_mst ms on ms.prf_group_code = m.prf_group_code "
					+ "LEFT JOIN tb_aviation_tail_no_dtl b ON a.acc_no = b.tail_no "
					+ "LEFT JOIN tb_aviation_act_master c ON b.std_nomclature = c.std_nomclature "
					+ "LEFT JOIN tb_aviation_act_main_master d ON SUBSTRING(CAST(c.act AS CHARACTER VARYING), 1, 4) = d.act_main_id "
					+ "WHERE a.sus_no=?";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_dt as date) = cast(? as date) GROUP BY ms.prf_group, m.item_code, d.act_main_id, d.act_nomen, m.total_ue_qty";
			}
			PreparedStatement stmt = conn.prepareStatement(sql);
			
		
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}

			ResultSet rs = stmt.executeQuery();
			System.err.println("11--"+stmt);
			int sur = 0;
			int defi = 0;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("act_main_id"));// 0
				list.add(rs.getString("act_nomen"));// 1
				int uh = 0;
				int ue = 0;
				if (rs.getString("UH") == null) {
					uh = 0;
				} else {
					uh = Integer.parseInt(rs.getString("UH"));
				}
				if (rs.getString("UE") == null) {
					ue = 0;
				} else {
					ue = Integer.parseInt(rs.getString("UE"));
				}
				list.add(String.valueOf(ue));// 2
				list.add(rs.getString("totalS"));// 3
				list.add(rs.getString("totalI"));// 4
				list.add(rs.getString("totalRI"));// 5
				list.add(rs.getString("totalRE"));// 6
				list.add(rs.getString("totalAOG"));// 7
				list.add(rs.getString("totalACCI"));// 8
				list.add(rs.getString("totalOH"));// 9

				list.add(String.valueOf(uh));// 10
				if (ue >= 0 && uh >= 0) {
					int diff = uh - ue;
					if (diff >= 0) {
						sur = diff;
						defi = 0;
					} else {
						defi = diff;
						sur = 0;
					}
				}
				list.add(String.valueOf(Math.abs(sur)));// 11
				list.add(String.valueOf(Math.abs(defi)));// 12
				list.add(rs.getString("prf_group"));// 13
				list.add(rs.getString("item_code"));// 14
				aList.add(list);
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
		return aList;
	}

	// New added by mitesh 10-01-2025
	public ArrayList<ArrayList<String>> getdetails_ue_uhchtlDacrlist(String sus_no, String issue_date) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "SELECT ms.prf_group, m.item_code, d.act_main_id, " + "d.act_nomen, " + "m.total_ue_qty AS UE, "
					+ "COUNT(a.acc_no) AS UH, " + "COUNT(a.acc_no) FILTER (WHERE a.status='S') AS totalS, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='I') AS totalI, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='RI') AS totalRI, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='RE') AS totalRE, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='AOG') AS totalAOG, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='ACCIDENTAL') AS totalACCI, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='ROH') AS totalOH "
					+ "FROM tb_aviation_chtl_daily_basis_history a "
					+ "LEFT JOIN mms_ue_mview m on m.sus_no = a.sus_no and m.item_code in ('1501011K')"
					+ "LEFT JOIN cue_tb_miso_prf_group_mst ms on ms.prf_group_code = m.prf_group_code "
					+ "LEFT JOIN tb_aviation_chtl_tail_no_dtl b ON a.acc_no = b.tail_no "
					+ "LEFT JOIN tb_aviation_act_master c ON b.std_nomclature = c.std_nomclature "
					+ "LEFT JOIN tb_aviation_act_main_master d ON SUBSTRING(CAST(c.act AS CHARACTER VARYING), 1, 4) = d.act_main_id "
					+ "WHERE a.sus_no=?";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) GROUP BY ms.prf_group, m.item_code, d.act_main_id, d.act_nomen, m.total_ue_qty";
			}
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}

			ResultSet rs = stmt.executeQuery();
			System.err.println("12--"+stmt);
			int sur = 0;
			int defi = 0;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("act_main_id"));// 0
				list.add(rs.getString("act_nomen"));// 1
				int uh = 0;
				int ue = 0;
				if (rs.getString("UH") == null) {
					uh = 0;
				} else {
					uh = Integer.parseInt(rs.getString("UH"));
				}
				if (rs.getString("UE") == null) {
					ue = 0;
				} else {
					ue = Integer.parseInt(rs.getString("UE"));
				}
				list.add(String.valueOf(ue));// 2
				list.add(rs.getString("totalS"));// 3
				list.add(rs.getString("totalI"));// 4
				list.add(rs.getString("totalRI"));// 5
				list.add(rs.getString("totalRE"));// 6
				list.add(rs.getString("totalAOG"));// 7
				list.add(rs.getString("totalACCI"));// 8
				list.add(rs.getString("totalOH"));// 9

				list.add(String.valueOf(uh));// 10
				if (ue >= 0 && uh >= 0) {
					int diff = uh - ue;
					if (diff >= 0) {
						sur = diff;
						defi = 0;
					} else {
						defi = diff;
						sur = 0;
					}
				}
				list.add(String.valueOf(Math.abs(sur)));// 11
				list.add(String.valueOf(Math.abs(defi)));// 12
				list.add(rs.getString("prf_group"));// 13
				list.add(rs.getString("item_code"));// 14
				aList.add(list);
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
		return aList;
	}

	// New added by mitesh 10-01-2025
	public ArrayList<ArrayList<String>> getdetails_ue_uhrpasDacrlist(String sus_no, String issue_date) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "SELECT ms.prf_group, m.item_code, d.act_main_id, " + "d.act_nomen, " + "m.total_ue_qty AS UE, "
					+ "COUNT(a.acc_no) AS UH, " + "COUNT(a.acc_no) FILTER (WHERE a.status='S') AS totalS, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='I') AS totalI, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='RI') AS totalRI, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='RE') AS totalRE, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='AOG') AS totalAOG, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='ACCIDENTAL') AS totalACCI, "
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='ROH') AS totalOH "
					+ "FROM tb_aviation_rpas_daily_basis_history a "
					+ "LEFT JOIN mms_ue_mview m on m.sus_no = a.sus_no and m.item_code in ('2603239X')"
					+ "LEFT JOIN cue_tb_miso_prf_group_mst ms on ms.prf_group_code = m.prf_group_code "
					+ "LEFT JOIN tb_aviation_rpas_tail_no_dtl b ON a.acc_no = b.tail_no "
					+ "LEFT JOIN tb_aviation_rpas_act_master c ON b.std_nomclature = c.std_nomclature "
					+ "LEFT JOIN tb_aviation_act_main_master d ON SUBSTRING(CAST(c.act AS CHARACTER VARYING), 1, 4) = d.act_main_id "
					+ "WHERE a.sus_no=?";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) GROUP BY ms.prf_group, m.item_code, d.act_main_id, d.act_nomen, m.total_ue_qty";
			}
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}

			ResultSet rs = stmt.executeQuery();
			System.err.println("13--"+stmt);
			int sur = 0;
			int defi = 0;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("act_main_id"));// 0
				list.add(rs.getString("act_nomen"));// 1
				int uh = 0;
				int ue = 0;
				if (rs.getString("UH") == null) {
					uh = 0;
				} else {
					uh = Integer.parseInt(rs.getString("UH"));
				}
				if (rs.getString("UE") == null) {
					ue = 0;
				} else {
					ue = Integer.parseInt(rs.getString("UE"));
				}
				list.add(String.valueOf(ue));// 2
				list.add(rs.getString("totalS"));// 3
				list.add(rs.getString("totalI"));// 4
				list.add(rs.getString("totalRI"));// 5
				list.add(rs.getString("totalRE"));// 6
				list.add(rs.getString("totalAOG"));// 7
				list.add(rs.getString("totalACCI"));// 8
				list.add(rs.getString("totalOH"));// 9

				list.add(String.valueOf(uh));// 10
				if (ue >= 0 && uh >= 0) {
					int diff = uh - ue;
					if (diff >= 0) {
						sur = diff;
						defi = 0;
					} else {
						defi = diff;
						sur = 0;
					}
				}
				list.add(String.valueOf(Math.abs(sur)));// 11
				list.add(String.valueOf(Math.abs(defi)));// 12
				list.add(rs.getString("prf_group"));// 13
				list.add(rs.getString("item_code"));// 14
				aList.add(list);
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
		return aList;
	}

	public List<String> gettail_noFromStatus(String sus_no, String asonDate1, String issue_states) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			if (issue_states.equals("-1")) {
				sql += "select distinct a.acc_no, b.std_nomclature from tb_aviation_daily_basis_history a left join tb_aviation_tail_no_dtl b on a.acc_no = b.tail_no  where a.sus_no = ? and cast(a.ason_dt as date)= cast(? as date) ";
			} else {
				sql += "select distinct a.acc_no, b.std_nomclature from tb_aviation_daily_basis_history a left join tb_aviation_tail_no_dtl b on a.acc_no = b.tail_no  where a.sus_no =? and cast(a.ason_dt as date)= cast(? as date) and a.status=? ";
			}
			PreparedStatement stmt = conn.prepareStatement(sql);
			if (issue_states.equals("-1")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate1);

			} else if (issue_states.equals("0")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate1);
				stmt.setString(3, "S");
			} else if (issue_states.equals("1")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate1);
				stmt.setString(3, "I");
			} else if (issue_states.equals("2")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate1);
				stmt.setString(3, "RI");
			} else if (issue_states.equals("3")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate1);
				stmt.setString(3, "RE");
			} else if (issue_states.equals("4")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate1);
				stmt.setString(3, "AOG");
			} else if (issue_states.equals("5")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate1);
				stmt.setString(3, "ACCI");
			} else if (issue_states.equals("6")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate1);
				stmt.setString(3, "ROH");
			}
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i < columnCount + 1; i++) {
					String name = rs.getString(i);
					list.add(name);
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
				}
			}
		}
		return list;
	}

	public List<String> getchtltail_no(String sus_no, String asonDate, String issue_type) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			if (issue_type.equals("-1")) {
				sql += "select a.acc_no, b.std_nomclature from tb_aviation_chtl_daily_basis_history a left join tb_aviation_chtl_tail_no_dtl b on a.acc_no=b.tail_no \r\n"
						+ " where a.sus_no= ? and  a.ason_date = cast (? as date) ";
			} else {
				sql += "select a.acc_no, b.std_nomclature from tb_aviation_chtl_daily_basis_history a left join tb_aviation_chtl_tail_no_dtl b on a.acc_no=b.tail_no \r\n"
						+ " where a.sus_no= ? and  a.ason_date = cast (? as date) and a.status = ? ";
			}
			PreparedStatement stmt = conn.prepareStatement(sql);
			if (issue_type.equals("-1")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);

			} else if (issue_type.equals("0")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "S");
			} else if (issue_type.equals("1")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "I");
			} else if (issue_type.equals("2")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "RI");
			} else if (issue_type.equals("3")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "RE");
			} else if (issue_type.equals("4")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "AOG");
			} else if (issue_type.equals("5")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "ACCI");
			} else if (issue_type.equals("6")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "ROH");
			}
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i < columnCount + 1; i++) {
					String name = rs.getString(i);
					list.add(name);
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
				}
			}
		}
		return list;
	}

	public List<String> getrpastail_no(String sus_no, String asonDate, String issue_type) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			if (issue_type.equals("-1")) {
				sql += "select a.acc_no, b.std_nomclature from tb_aviation_rpas_daily_basis_history a left join tb_aviation_rpas_tail_no_dtl b on a.acc_no=b.tail_no \r\n"
						+ " where a.sus_no= ? and  a.ason_date = cast (? as date) ";
			} else {
				sql += "select a.acc_no, b.std_nomclature from tb_aviation_rpas_daily_basis_history a left join tb_aviation_rpas_tail_no_dtl b on a.acc_no=b.tail_no \r\n"
						+ " where a.sus_no= ? and  a.ason_date = cast (? as date) and a.status = ? ";
			}
			PreparedStatement stmt = conn.prepareStatement(sql);
			if (issue_type.equals("-1")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);

			} else if (issue_type.equals("0")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "S");
			} else if (issue_type.equals("1")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "I");
			} else if (issue_type.equals("2")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "RI");
			} else if (issue_type.equals("3")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "RE");
			} else if (issue_type.equals("4")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "AOG");
			} else if (issue_type.equals("5")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "ACCI");
			} else if (issue_type.equals("6")) {
				stmt.setString(1, sus_no);
				stmt.setString(2, asonDate);
				stmt.setString(3, "ROH");
			}
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i < columnCount + 1; i++) {
					String name = rs.getString(i);
					list.add(name);
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
				}
			}
		}
		return list;
	}

	public List<Map<String, Object>> getTptSummaryInABCList(int type, HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String qry = "", type_of_aircraft = "";
			if (type == 0) {
				type_of_aircraft = "ROTARY WING";
			}
			if (type == 1) {
				type_of_aircraft = "RPAS";
			}
			qry = "select abc_code , act_nomen from tb_aviation_act_main_master where type_of_aircraft= ?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, type_of_aircraft);
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

	public List<Map<String, Object>> getactMain_from_Type_of_aircraft(String abc_code, HttpSession session) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String abccode = abc_code.replaceAll(":", "','");

		try {
			conn = dataSource.getConnection();
			String qry = "";

			qry = "select s.act as act, s.std_nomclature as std_nomclature from tb_aviation_act_master s \r\n"
					+ "left join tb_aviation_act_main_master m on substr(s.act::varchar,1,4) = m.act_main_id \r\n";
			qry += "where s.act is not null and s.std_nomclature is not null \r\n ";

			if (!abccode.equalsIgnoreCase("XNR")) {
				qry += "and s.abc_group in ('" + abccode + "') \r\n";
			}
			qry += " order by s.std_nomclature";

			PreparedStatement stmt = conn.prepareStatement(qry);

			System.err.println("actlist" + stmt);
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
	public ArrayList<ArrayList<String>> avn_assetstatusDetails_linedte(String cmd, String act_main_list,
			String abc_code, String sus_no, String line_dte_sus1, String status, String ason_date) {
		String whr = "";

		if (!abc_code.equals("0")) {
			String abccode = abc_code.replaceAll(",", "','");
			if (abc_code.toUpperCase().indexOf("XNR") <= -1) {
				whr += " bd.abc_group::varchar in ('" + abccode + "') ";
			}
		}

		if (!act_main_list.equals("")) {
			String[] act_main_array = act_main_list.split(":");
			if (act_main_array.length > 0) {
				whr += " and ( ";
			}
			for (int i = 0; i < act_main_array.length; i++) {
				if (i == 0) {
					whr += " bd.act::varchar = ? ";
				} else {
					whr += " or bd.act::varchar = ? ";
				}
			}
			if (act_main_array.length > 0) {
				whr += " ) ";
			}
		}

		if (!sus_no.equals("")) {
			whr += " and u.sus_no = ? ";
		} else {
			if (!cmd.equals("")) {
				whr += " and u.form_code_control like ? ";
			}
		}

		if (!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")) {
			whr += " and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
		}

		if (!status.equals("") & !status.equals("0")) {
			whr += " and part.status = ? ";
		}

		if (!ason_date.equals("")) {
			whr += " and cast(part.ason_dt as date) = cast(? as date) ";
		}

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			sql = "select DISTINCT \r\n" + " 	part.acc_no,\r\n" + " 	part.status,\r\n" + " 	part.remarks,\r\n"
					+ " 	a.std_nomclature,\r\n" + "	c.short_form as comd,\r\n" + "	corps.coprs_name as corps,\r\n"
					+ "	div.div_name as div,\r\n" + "	bde.bde_name as bde,\r\n" + " 	u.unit_name,\r\n"
					+ "	u.sus_no,\r\n" + " 	ltrim(TO_CHAR(part.ason_dt,'dd-mm-yyyy'),'') as date_of_ason \r\n"
					+ " from tb_aviation_daily_basis_history part \r\n"
					+ " inner join tb_aviation_tail_no_dtl a on part.acc_no::varchar = a.tail_no::varchar \r\n"
					+ " inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and part.sus_no::varchar = u.sus_no::varchar \r\n"
					+ " left join tb_aviation_act_master bd on a.std_nomclature::varchar = bd.std_nomclature::varchar \r\n"
					+ " left join tb_aviation_act_main_master b on substr(bd.act::varchar,1,4) = b.act_main_id::varchar \r\n"
					+ " left join orbat_view_cmd c on substr(u.form_code_control::varchar,1,1) = c.cmd_code::varchar \r\n"
					+ "left join orbat_view_corps corps on substr(u.form_code_control::varchar,2,2) = corps.corps_code::varchar \r\n"
					+ "left join orbat_view_div div on substr(u.form_code_control::varchar,4,3) = div.div_code::varchar \r\n"
					+ "left join orbat_view_bde bde on substr(u.form_code_control::varchar,7,4) = bde.bde_code::varchar  where "
					+ whr + "  ";

			stmt = conn.prepareStatement(sql);

			int flag = 0;
			if (!act_main_list.equals("")) {
				String[] act_main_array = act_main_list.split(":");
				for (int i = 0; i < act_main_array.length; i++) {
					flag = flag + 1;
					stmt.setString(flag, act_main_array[i]);
				}
			}
			if (!sus_no.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, sus_no);
			} else {
				if (!cmd.equals("")) {
					flag = flag + 1;
					stmt.setString(flag, cmd + "%");
				}
			}
			if (!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, line_dte_sus1);
			}
			if (!status.equals("") & !status.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, status);
			}

			if (!ason_date.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, ason_date);
			}

			

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("comd"));// 0
				list1.add(rs.getString("corps"));// 1
				list1.add(rs.getString("div"));// 2
				list1.add(rs.getString("bde"));// 3
				list1.add(rs.getString("unit_name"));// 4
				list1.add(rs.getString("sus_no"));// 5
				list1.add(rs.getString("std_nomclature"));// 6
				list1.add(rs.getString("acc_no"));// 7
				list1.add(rs.getString("status"));// 8
				list1.add(rs.getString("remarks"));// 9
				list1.add(rs.getString("date_of_ason"));// 10
				list.add(list1);
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
	public ArrayList<ArrayList<String>> avn_assetstatusDetails_linedtechtl(String cmd, String act_main_list,
			String abc_code, String sus_no, String line_dte_sus1, String status, String ason_date) {
		String whr = "";

		if (!abc_code.equals("0")) {
			String abccode = abc_code.replaceAll(",", "','");
			if (abc_code.toUpperCase().indexOf("XNR") <= -1) {
				whr += " bd.abc_group::varchar in ('" + abccode + "') ";
			}
		}

		if (!act_main_list.equals("")) {
			String[] act_main_array = act_main_list.split(":");
			if (act_main_array.length > 0) {
				whr += " and ( ";
			}
			for (int i = 0; i < act_main_array.length; i++) {
				if (i == 0) {
					whr += " bd.act::varchar = ? ";
				} else {
					whr += " or bd.act::varchar = ? ";
				}
			}
			if (act_main_array.length > 0) {
				whr += " ) ";
			}
		}

		if (!sus_no.equals("")) {
			whr += " and u.sus_no = ? ";
		} else {
			if (!cmd.equals("")) {
				whr += " and u.form_code_control like ? ";
			}
		}

		if (!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")) {
			whr += " and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
		}

		if (!status.equals("") & !status.equals("0")) {
			whr += " and part.status = ? ";
		}

		if (!ason_date.equals("")) {
			whr += " and cast(part.ason_date as date) = cast(? as date) ";
		}

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			sql = "select DISTINCT \r\n" + " 	part.acc_no,\r\n" + " 	part.status,\r\n" + " 	part.remarks,\r\n"
					+ " 	a.std_nomclature,\r\n" + "	c.short_form as comd,\r\n" + "	corps.coprs_name as corps,\r\n"
					+ "	div.div_name as div,\r\n" + "	bde.bde_name as bde,\r\n" + " 	u.unit_name,\r\n"
					+ "	u.sus_no,\r\n" + " 	ltrim(TO_CHAR(part.ason_date,'dd-mm-yyyy'),'') as date_of_ason \r\n"
					+ " from tb_aviation_chtl_daily_basis_history part \r\n"
					+ " inner join tb_aviation_chtl_tail_no_dtl a on part.acc_no::varchar = a.tail_no::varchar \r\n"
					+ " inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and part.sus_no::varchar = u.sus_no::varchar \r\n"
					+ " left join tb_aviation_act_master bd on a.std_nomclature::varchar = bd.std_nomclature::varchar \r\n"
					+ " left join tb_aviation_act_main_master b on substr(bd.act::varchar,1,4) = b.act_main_id::varchar \r\n"
					+ " left join orbat_view_cmd c on substr(u.form_code_control::varchar,1,1) = c.cmd_code::varchar \r\n"
					+ "left join orbat_view_corps corps on substr(u.form_code_control::varchar,2,2) = corps.corps_code::varchar \r\n"
					+ "left join orbat_view_div div on substr(u.form_code_control::varchar,4,3) = div.div_code::varchar \r\n"
					+ "left join orbat_view_bde bde on substr(u.form_code_control::varchar,7,4) = bde.bde_code::varchar  where "
					+ whr + "  ";

			stmt = conn.prepareStatement(sql);

			int flag = 0;
			if (!act_main_list.equals("")) {
				String[] act_main_array = act_main_list.split(":");
				for (int i = 0; i < act_main_array.length; i++) {
					flag = flag + 1;
					stmt.setString(flag, act_main_array[i]);
				}
			}
			if (!sus_no.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, sus_no);
			} else {
				if (!cmd.equals("")) {
					flag = flag + 1;
					stmt.setString(flag, cmd + "%");
				}
			}
			if (!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, line_dte_sus1);
			}
			if (!status.equals("") & !status.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, status);
			}

			if (!ason_date.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, ason_date);
			}

			

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("comd"));// 0
				list1.add(rs.getString("corps"));// 1
				list1.add(rs.getString("div"));// 2
				list1.add(rs.getString("bde"));// 3
				list1.add(rs.getString("unit_name"));// 4
				list1.add(rs.getString("sus_no"));// 5
				list1.add(rs.getString("std_nomclature"));// 6
				list1.add(rs.getString("acc_no"));// 7
				list1.add(rs.getString("status"));// 8
				list1.add(rs.getString("remarks"));// 9
				list1.add(rs.getString("date_of_ason"));// 10
				list.add(list1);
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
	public ArrayList<ArrayList<String>> avn_assetstatusDetails_linedterpas(String cmd, String act_main_list,
			String abc_code, String sus_no, String line_dte_sus1, String status, String ason_date) {
		String whr = "";

		if (!abc_code.equals("0")) {
			String abccode = abc_code.replaceAll(",", "','");
			if (abc_code.toUpperCase().indexOf("XNR") <= -1) {
				whr += " bd.abc_group::varchar in ('" + abccode + "') ";
			}
		}

		if (!act_main_list.equals("")) {
			String[] act_main_array = act_main_list.split(":");
			if (act_main_array.length > 0) {
				whr += " and ( ";
			}
			for (int i = 0; i < act_main_array.length; i++) {
				if (i == 0) {
					whr += " bd.act::varchar = ? ";
				} else {
					whr += " or bd.act::varchar = ? ";
				}
			}
			if (act_main_array.length > 0) {
				whr += " ) ";
			}
		}

		if (!sus_no.equals("")) {
			whr += " and u.sus_no = ? ";
		} else {
			if (!cmd.equals("")) {
				whr += " and u.form_code_control like ? ";
			}
		}

		if (!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")) {
			whr += " and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
		}

		if (!status.equals("") & !status.equals("0")) {
			whr += " and part.status = ? ";
		}

		if (!ason_date.equals("")) {
			whr += " and cast(part.ason_date as date) = cast(? as date) ";
		}

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			sql = "select DISTINCT \r\n" + " 	part.acc_no,\r\n" + " 	part.status,\r\n" + " 	part.remarks,\r\n"
					+ " 	a.std_nomclature,\r\n" + "	c.short_form as comd,\r\n" + "	corps.coprs_name as corps,\r\n"
					+ "	div.div_name as div,\r\n" + "	bde.bde_name as bde,\r\n" + " 	u.unit_name,\r\n"
					+ "	u.sus_no,\r\n" + " 	ltrim(TO_CHAR(part.ason_date,'dd-mm-yyyy'),'') as date_of_ason \r\n"
					+ " from tb_aviation_rpas_daily_basis_history part \r\n"
					+ " inner join tb_aviation_rpas_tail_no_dtl a on part.acc_no::varchar = a.tail_no::varchar \r\n"
					+ " inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and part.sus_no::varchar = u.sus_no::varchar \r\n"
					+ " left join tb_aviation_rpas_act_master bd on a.std_nomclature::varchar = bd.std_nomclature::varchar \r\n"
					+ " left join tb_aviation_act_main_master b on substr(bd.act::varchar,1,4) = b.act_main_id::varchar \r\n"
					+ " left join orbat_view_cmd c on substr(u.form_code_control::varchar,1,1) = c.cmd_code::varchar \r\n"
					+ "left join orbat_view_corps corps on substr(u.form_code_control::varchar,2,2) = corps.corps_code::varchar \r\n"
					+ "left join orbat_view_div div on substr(u.form_code_control::varchar,4,3) = div.div_code::varchar \r\n"
					+ "left join orbat_view_bde bde on substr(u.form_code_control::varchar,7,4) = bde.bde_code::varchar  where "
					+ whr + "  ";

			stmt = conn.prepareStatement(sql);

			int flag = 0;
			if (!act_main_list.equals("")) {
				String[] act_main_array = act_main_list.split(":");
				for (int i = 0; i < act_main_array.length; i++) {
					flag = flag + 1;
					stmt.setString(flag, act_main_array[i]);
				}
			}
			if (!sus_no.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, sus_no);
			} else {
				if (!cmd.equals("")) {
					flag = flag + 1;
					stmt.setString(flag, cmd + "%");
				}
			}
			if (!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, line_dte_sus1);
			}
			if (!status.equals("") & !status.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, status);
			}

			if (!ason_date.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, ason_date);
			}

			

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("comd"));// 0
				list1.add(rs.getString("corps"));// 1
				list1.add(rs.getString("div"));// 2
				list1.add(rs.getString("bde"));// 3
				list1.add(rs.getString("unit_name"));// 4
				list1.add(rs.getString("sus_no"));// 5
				list1.add(rs.getString("std_nomclature"));// 6
				list1.add(rs.getString("acc_no"));// 7
				list1.add(rs.getString("status"));// 8
				list1.add(rs.getString("remarks"));// 9
				list1.add(rs.getString("date_of_ason"));// 10
				list.add(list1);
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

	public List<Map<String, Object>> getactMain_from_Type_of_aircraftRPAS(String abc_code, HttpSession session) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String abccode = abc_code.replaceAll(":", "','");

		try {
			conn = dataSource.getConnection();
			String qry = "";

			qry = "select s.act as act, s.std_nomclature as std_nomclature from tb_aviation_rpas_act_master s \r\n"
					+ "left join tb_aviation_act_main_master m on substr(s.act::varchar,1,4) = m.act_main_id \r\n";
			qry += "where s.act is not null and s.std_nomclature is not null \r\n ";

			if (!abccode.equalsIgnoreCase("XNR")) {
				qry += "and s.abc_group in ('" + abccode + "') \r\n";
			}
			qry += " order by s.std_nomclature";

			PreparedStatement stmt = conn.prepareStatement(qry);

			System.err.println("actlist" + stmt);
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

	// added by mitesh 17-01-2025
	@Override
	public ArrayList<ArrayList<String>> avn_assetstatus_linedte(String cmd, String act_main_list, String abc_code,
			String sus_no, String line_dte_sus1, String ason_date) {
		String whr = "";

		if (!abc_code.equals("0")) {
			String abccode = abc_code.replaceAll(",", "','");
			if (abc_code.toUpperCase().indexOf("XNR") <= -1) {
				whr += " c.abc_group::varchar in ('" + abccode + "') ";
			}
		}

		if (!act_main_list.equals("")) {
			String[] act_main_array = act_main_list.split(":");
			if (act_main_array.length > 0) {
				whr += " and ( ";
			}
			for (int i = 0; i < act_main_array.length; i++) {
				if (i == 0) {
					whr += " c.act::varchar = ? ";
				} else {
					whr += " or c.act::varchar = ? ";
				}
			}
			if (act_main_array.length > 0) {
				whr += " ) ";
			}
		}

		if (!sus_no.equals("")) {
			whr += " and u.sus_no = ? ";
		} else {
			if (!cmd.equals("")) {
				whr += " and u.form_code_control like ? ";
			}
		}

		if (!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")) {
			whr += " and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
		}

		if (!ason_date.equals("")) {
			whr += " and cast(a.ason_dt as date) = cast(? as date) ";
		}

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			sql = "SELECT  o.short_form as comd,\r\n" + "		corps.coprs_name as corps,\r\n"
					+ "		div.div_name as div,\r\n" + "		bde.bde_name as bde,\r\n" + "		u.unit_name,\r\n"
					+ "		u.sus_no,\r\n" + " ltrim(TO_CHAR(a.ason_dt,'dd-mm-yyyy'),'') as date_of_ason,\r\n"
					+ " c.act, c.std_nomclature, m.total_ue_qty AS UE,COUNT(a.acc_no) AS UH,\r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='S') AS totalS, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='I') AS totalI, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='RI') AS totalRI, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='RE') AS totalRE, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='AOG') AS totalAOG, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='ACCIDENTAL') AS totalACCI, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='ROH') AS totalOH FROM tb_aviation_daily_basis_history a \r\n"
					+ "LEFT JOIN mms_ue_mview m on m.sus_no = a.sus_no and m.item_code in ('1501014E','1501017Y')\r\n"
					+ "LEFT JOIN cue_tb_miso_prf_group_mst ms on ms.prf_group_code = m.prf_group_code \r\n"
					+ "LEFT JOIN tb_aviation_tail_no_dtl b ON a.acc_no = b.tail_no \r\n"
					+ "LEFT JOIN tb_aviation_act_master c ON b.std_nomclature = c.std_nomclature \r\n"
					+ "LEFT JOIN tb_aviation_act_main_master d ON SUBSTRING(CAST(c.act AS CHARACTER VARYING), 1, 4) = d.act_main_id\r\n"
					+ "inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no::varchar = u.sus_no::varchar\r\n"
					+ "left join orbat_view_cmd o on substr(u.form_code_control::varchar,1,1) = o.cmd_code::varchar\r\n"
					+ "left join orbat_view_corps corps on substr(u.form_code_control::varchar,2,2) = corps.corps_code::varchar\r\n"
					+ "left join orbat_view_div div on substr(u.form_code_control::varchar,4,3) = div.div_code::varchar\r\n"
					+ "left join orbat_view_bde bde on substr(u.form_code_control::varchar,7,4) = bde.bde_code::varchar\r\n"
					+ "where " + whr + "group by 1,2,3,4,5,6,7,8,9,10";

			stmt = conn.prepareStatement(sql);

			int flag = 0;
			if (!act_main_list.equals("")) {
				String[] act_main_array = act_main_list.split(":");
				for (int i = 0; i < act_main_array.length; i++) {
					flag = flag + 1;
					stmt.setString(flag, act_main_array[i]);
				}
			}
			if (!sus_no.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, sus_no);
			} else {
				if (!cmd.equals("")) {
					flag = flag + 1;
					stmt.setString(flag, cmd + "%");
				}
			}
			if (!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, line_dte_sus1);
			}

			if (!ason_date.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, ason_date);
			}

			System.out.println("avnStatus1-----" + stmt);

			ResultSet rs = stmt.executeQuery();
			int sur = 0;
			int defi = 0;
			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("comd"));// 0
				list1.add(rs.getString("corps"));// 1
				list1.add(rs.getString("div"));// 2
				list1.add(rs.getString("bde"));// 3
				list1.add(rs.getString("unit_name"));// 4
				list1.add(rs.getString("sus_no"));// 5
				list1.add(rs.getString("date_of_ason"));// 6
				list1.add(rs.getString("act"));// 7
				list1.add(rs.getString("std_nomclature"));// 8
				int uh = 0;
				int ue = 0;
				if (rs.getString("UH") == null) {
					uh = 0;
				} else {
					uh = Integer.parseInt(rs.getString("UH"));
				}
				if (rs.getString("UE") == null) {
					ue = 0;
				} else {
					ue = Integer.parseInt(rs.getString("UE"));
				}
				list1.add(String.valueOf(ue));// 9
				list1.add(String.valueOf(uh));// 10
				if (ue >= 0 && uh >= 0) {
					int diff = uh - ue;
					if (diff >= 0) {
						sur = diff;
						defi = 0;
					} else {
						defi = diff;
						sur = 0;
					}
				}
				list1.add(rs.getString("totalS"));// 11
				list1.add(rs.getString("totalI"));// 12
				list1.add(rs.getString("totalRI"));// 13
				list1.add(rs.getString("totalRE"));// 14
				list1.add(rs.getString("totalAOG"));// 15
				list1.add(rs.getString("totalACCI"));// 16
				list1.add(rs.getString("totalOH"));// 17
				list1.add(String.valueOf(Math.abs(sur)));// 18
				list1.add(String.valueOf(Math.abs(defi)));// 19
				list.add(list1);
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
	public ArrayList<ArrayList<String>> avn_assetstatus_linedtechtl(String cmd, String act_main_list, String abc_code,
			String sus_no, String line_dte_sus1, String ason_date) {
		String whr = "";

		if (!abc_code.equals("0")) {
			String abccode = abc_code.replaceAll(",", "','");
			if (abc_code.toUpperCase().indexOf("XNR") <= -1) {
				whr += " c.abc_group::varchar in ('" + abccode + "') ";
			}
		}

		if (!act_main_list.equals("")) {
			String[] act_main_array = act_main_list.split(":");
			if (act_main_array.length > 0) {
				whr += " and ( ";
			}
			for (int i = 0; i < act_main_array.length; i++) {
				if (i == 0) {
					whr += " c.act::varchar = ? ";
				} else {
					whr += " or c.act::varchar = ? ";
				}
			}
			if (act_main_array.length > 0) {
				whr += " ) ";
			}
		}

		if (!sus_no.equals("")) {
			whr += " and u.sus_no = ? ";
		} else {
			if (!cmd.equals("")) {
				whr += " and u.form_code_control like ? ";
			}
		}

		if (!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")) {
			whr += " and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
		}

		if (!ason_date.equals("")) {
			whr += " and cast(a.ason_date as date) = cast(? as date) ";
		}

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			sql = "SELECT  o.short_form as comd,\r\n" + "		corps.coprs_name as corps,\r\n"
					+ "		div.div_name as div,\r\n" + "		bde.bde_name as bde,\r\n" + "		u.unit_name,\r\n"
					+ "		u.sus_no,\r\n" + " ltrim(TO_CHAR(a.ason_date,'dd-mm-yyyy'),'') as date_of_ason,\r\n"
					+ " c.act, c.std_nomclature, m.total_ue_qty AS UE,COUNT(a.acc_no) AS UH,\r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='S') AS totalS, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='I') AS totalI, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='RI') AS totalRI, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='RE') AS totalRE, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='AOG') AS totalAOG, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='ACCIDENTAL') AS totalACCI, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='ROH') AS totalOH FROM tb_aviation_chtl_daily_basis_history a \r\n"
					+ "LEFT JOIN mms_ue_mview m on m.sus_no = a.sus_no and m.item_code in ('1501011K')\r\n"
					+ "LEFT JOIN cue_tb_miso_prf_group_mst ms on ms.prf_group_code = m.prf_group_code \r\n"
					+ "LEFT JOIN tb_aviation_chtl_tail_no_dtl b ON a.acc_no = b.tail_no \r\n"
					+ "LEFT JOIN tb_aviation_act_master c ON b.std_nomclature = c.std_nomclature \r\n"
					+ "LEFT JOIN tb_aviation_act_main_master d ON SUBSTRING(CAST(c.act AS CHARACTER VARYING), 1, 4) = d.act_main_id\r\n"
					+ "inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no::varchar = u.sus_no::varchar\r\n"
					+ "left join orbat_view_cmd o on substr(u.form_code_control::varchar,1,1) = o.cmd_code::varchar\r\n"
					+ "left join orbat_view_corps corps on substr(u.form_code_control::varchar,2,2) = corps.corps_code::varchar\r\n"
					+ "left join orbat_view_div div on substr(u.form_code_control::varchar,4,3) = div.div_code::varchar\r\n"
					+ "left join orbat_view_bde bde on substr(u.form_code_control::varchar,7,4) = bde.bde_code::varchar\r\n"
					+ "where " + whr + "group by 1,2,3,4,5,6,7,8,9,10";

			stmt = conn.prepareStatement(sql);

			int flag = 0;
			if (!act_main_list.equals("")) {
				String[] act_main_array = act_main_list.split(":");
				for (int i = 0; i < act_main_array.length; i++) {
					flag = flag + 1;
					stmt.setString(flag, act_main_array[i]);
				}
			}
			if (!sus_no.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, sus_no);
			} else {
				if (!cmd.equals("")) {
					flag = flag + 1;
					stmt.setString(flag, cmd + "%");
				}
			}
			if (!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, line_dte_sus1);
			}

			if (!ason_date.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, ason_date);
			}

			System.out.println("avnStatus1-----" + stmt);

			ResultSet rs = stmt.executeQuery();
			int sur = 0;
			int defi = 0;
			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("comd"));// 0
				list1.add(rs.getString("corps"));// 1
				list1.add(rs.getString("div"));// 2
				list1.add(rs.getString("bde"));// 3
				list1.add(rs.getString("unit_name"));// 4
				list1.add(rs.getString("sus_no"));// 5
				list1.add(rs.getString("date_of_ason"));// 6
				list1.add(rs.getString("act"));// 7
				list1.add(rs.getString("std_nomclature"));// 8
				int uh = 0;
				int ue = 0;
				if (rs.getString("UH") == null) {
					uh = 0;
				} else {
					uh = Integer.parseInt(rs.getString("UH"));
				}
				if (rs.getString("UE") == null) {
					ue = 0;
				} else {
					ue = Integer.parseInt(rs.getString("UE"));
				}
				list1.add(String.valueOf(ue));// 9
				list1.add(String.valueOf(uh));// 10
				if (ue >= 0 && uh >= 0) {
					int diff = uh - ue;
					if (diff >= 0) {
						sur = diff;
						defi = 0;
					} else {
						defi = diff;
						sur = 0;
					}
				}
				list1.add(rs.getString("totalS"));// 11
				list1.add(rs.getString("totalI"));// 12
				list1.add(rs.getString("totalRI"));// 13
				list1.add(rs.getString("totalRE"));// 14
				list1.add(rs.getString("totalAOG"));// 15
				list1.add(rs.getString("totalACCI"));// 16
				list1.add(rs.getString("totalOH"));// 17
				list1.add(String.valueOf(Math.abs(sur)));// 18
				list1.add(String.valueOf(Math.abs(defi)));// 19
				list.add(list1);
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
	public ArrayList<ArrayList<String>> avn_assetstatus_linedterpas(String cmd, String act_main_list, String abc_code,
			String sus_no, String line_dte_sus1, String ason_date) {
		String whr = "";

		if (!abc_code.equals("0")) {
			String abccode = abc_code.replaceAll(",", "','");
			if (abc_code.toUpperCase().indexOf("XNR") <= -1) {
				whr += " c.abc_group::varchar in ('" + abccode + "') ";
			}
		}

		if (!act_main_list.equals("")) {
			String[] act_main_array = act_main_list.split(":");
			if (act_main_array.length > 0) {
				whr += " and ( ";
			}
			for (int i = 0; i < act_main_array.length; i++) {
				if (i == 0) {
					whr += " c.act::varchar = ? ";
				} else {
					whr += " or c.act::varchar = ? ";
				}
			}
			if (act_main_array.length > 0) {
				whr += " ) ";
			}
		}

		if (!sus_no.equals("")) {
			whr += " and u.sus_no = ? ";
		} else {
			if (!cmd.equals("")) {
				whr += " and u.form_code_control like ? ";
			}
		}

		if (!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")) {
			whr += " and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
		}

		if (!ason_date.equals("")) {
			whr += " and cast(a.ason_date as date) = cast(? as date) ";
		}

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			sql = "SELECT  o.short_form as comd,\r\n" + "		corps.coprs_name as corps,\r\n"
					+ "		div.div_name as div,\r\n" + "		bde.bde_name as bde,\r\n" + "		u.unit_name,\r\n"
					+ "		u.sus_no,\r\n" + " ltrim(TO_CHAR(a.ason_date,'dd-mm-yyyy'),'') as date_of_ason,\r\n"
					+ " c.act, c.std_nomclature, m.total_ue_qty AS UE,COUNT(a.acc_no) AS UH,\r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='S') AS totalS, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='I') AS totalI, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='RI') AS totalRI, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='RE') AS totalRE, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='AOG') AS totalAOG, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='ACCIDENTAL') AS totalACCI, \r\n"
					+ "COUNT(a.acc_no) FILTER (WHERE a.status='ROH') AS totalOH FROM tb_aviation_rpas_daily_basis_history a \r\n"
					+ "LEFT JOIN mms_ue_mview m on m.sus_no = a.sus_no and m.item_code in ('2603239X')\r\n"
					+ "LEFT JOIN cue_tb_miso_prf_group_mst ms on ms.prf_group_code = m.prf_group_code \r\n"
					+ "LEFT JOIN tb_aviation_rpas_tail_no_dtl b ON a.acc_no = b.tail_no \r\n"
					+ "LEFT JOIN tb_aviation_rpas_act_master c ON b.std_nomclature = c.std_nomclature \r\n"
					+ "LEFT JOIN tb_aviation_act_main_master d ON SUBSTRING(CAST(c.act AS CHARACTER VARYING), 1, 4) = d.act_main_id\r\n"
					+ "inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no::varchar = u.sus_no::varchar\r\n"
					+ "left join orbat_view_cmd o on substr(u.form_code_control::varchar,1,1) = o.cmd_code::varchar\r\n"
					+ "left join orbat_view_corps corps on substr(u.form_code_control::varchar,2,2) = corps.corps_code::varchar\r\n"
					+ "left join orbat_view_div div on substr(u.form_code_control::varchar,4,3) = div.div_code::varchar\r\n"
					+ "left join orbat_view_bde bde on substr(u.form_code_control::varchar,7,4) = bde.bde_code::varchar\r\n"
					+ "where " + whr + "group by 1,2,3,4,5,6,7,8,9,10";

			stmt = conn.prepareStatement(sql);

			int flag = 0;
			if (!act_main_list.equals("")) {
				String[] act_main_array = act_main_list.split(":");
				for (int i = 0; i < act_main_array.length; i++) {
					flag = flag + 1;
					stmt.setString(flag, act_main_array[i]);
				}
			}
			if (!sus_no.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, sus_no);
			} else {
				if (!cmd.equals("")) {
					flag = flag + 1;
					stmt.setString(flag, cmd + "%");
				}
			}
			if (!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, line_dte_sus1);
			}

			if (!ason_date.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, ason_date);
			}

			System.out.println("avnStatus2-----" + stmt);

			ResultSet rs = stmt.executeQuery();
			int sur = 0;
			int defi = 0;
			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("comd"));// 0
				list1.add(rs.getString("corps"));// 1
				list1.add(rs.getString("div"));// 2
				list1.add(rs.getString("bde"));// 3
				list1.add(rs.getString("unit_name"));// 4
				list1.add(rs.getString("sus_no"));// 5
				list1.add(rs.getString("date_of_ason"));// 6
				list1.add(rs.getString("act"));// 7
				list1.add(rs.getString("std_nomclature"));// 8
				int uh = 0;
				int ue = 0;
				if (rs.getString("UH") == null) {
					uh = 0;
				} else {
					uh = Integer.parseInt(rs.getString("UH"));
				}
				if (rs.getString("UE") == null) {
					ue = 0;
				} else {
					ue = Integer.parseInt(rs.getString("UE"));
				}
				list1.add(String.valueOf(ue));// 9
				list1.add(String.valueOf(uh));// 10
				if (ue >= 0 && uh >= 0) {
					int diff = uh - ue;
					if (diff >= 0) {
						sur = diff;
						defi = 0;
					} else {
						defi = diff;
						sur = 0;
					}
				}
				list1.add(rs.getString("totalS"));// 11
				list1.add(rs.getString("totalI"));// 12
				list1.add(rs.getString("totalRI"));// 13
				list1.add(rs.getString("totalRE"));// 14
				list1.add(rs.getString("totalAOG"));// 15
				list1.add(rs.getString("totalACCI"));// 16
				list1.add(rs.getString("totalOH"));// 17
				list1.add(String.valueOf(Math.abs(sur)));// 18
				list1.add(String.valueOf(Math.abs(defi)));// 19
				list.add(list1);
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
