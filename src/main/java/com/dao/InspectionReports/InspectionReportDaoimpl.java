package com.dao.InspectionReports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.BisagN.MISO.CommanController;
import com.model.InspectionReports.TB_DETAIL_COURSES;
import com.model.InspectionReports.TB_INSP_CELL_PHONE_LAPSES;
import com.model.InspectionReports.TB_INSP_ESPIONAGE_LAPSES;
import com.model.InspectionReports.TB_INSP_FATAL_INCIDENTS;
import com.model.InspectionReports.TB_INSP_FFRS;
import com.model.InspectionReports.TB_INSP_FINANCIAL_GRANTS;
import com.model.InspectionReports.TB_INSP_FS_SECURITY_LAPSES;
import com.model.InspectionReports.TB_INSP_LOST_CDS_DVDS;
import com.model.InspectionReports.TB_INSP_LOST_ID_ECR;
import com.model.InspectionReports.TB_INSP_OUTSIDE_ATTACHMENTS;
import com.model.InspectionReports.TB_INSP_OUTSTANDING_LOSS_MT;
import com.model.InspectionReports.TB_INSP_OUTSTANDING_RENT_ALLIED;
import com.model.InspectionReports.TB_INSP_PIO_CALL_LAPSES;
import com.model.InspectionReports.TB_INSP_REGT_FUNDS;
import com.model.InspectionReports.TB_INSP_RPT_CLASSIFICATION_RANGES;
import com.model.InspectionReports.TB_INSP_SECURITY_LAPSES;
import com.model.InspectionReports.TB_INSP_SOCIAL_MEDIA_LAPSES;
import com.model.InspectionReports.TB_INSP_STANDARDS_ACHIEVED;
import com.model.InspectionReports.TB_INSP_TD_PROCEEDED;
import com.model.InspectionReports.TB_MISO_INSP_COURSES_CAT_A_B;
import com.model.InspectionReports.TB_MISO_INSP_DEFI_EQUP_EFFI;
import com.model.InspectionReports.TB_MISO_INSP_ESTABLISHMENT;
import com.model.InspectionReports.TB_MISO_INSP_MAIN_PHASE_III_TBL;
import com.model.InspectionReports.TB_MISO_INSP_MAIN_PHASE_IV_TBL;
import com.model.InspectionReports.TB_MISO_INSP_MAIN_TBL;
import com.model.InspectionReports.TB_MISO_INSP_SUMMARY_TECH_OTHER;
import com.model.InspectionReports.TB_MISO_INSP_TRAINING_AMMUNITION;
import com.model.InspectionReports.TB_MISO_INSP_UPGRADATION;
import com.model.InspectionReports.TB_MISO_INSP_USER_REMARKS;
import com.persistance.util.HibernateUtil;

public class InspectionReportDaoimpl implements InspectionReportsDao {
	private DataSource dataSource;
	
	CommanController common=new CommanController();

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	//String currenYear = yearFormat.format(new Date());

	
	
	
	


	@Override
	public ArrayList<ArrayList<String>> getwtResultOtherData(HttpSession session, HttpServletRequest request) {
		String currenYear = yearFormat.format(new Date());
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String q = "SELECT  category, total_no, marks_men, first_class,standard_shot, failed,number_exempted,number_yeto_fire,weapons"
				+ "  FROM  tb_miso_insp_wt_result_other " + "WHERE sus_no = ?  AND year = ?  ";

		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(q)) {
			stmt.setString(1, roleSusNo);		
			stmt.setString(3, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();

				while (rs.next()) {
					ArrayList<String> columns = new ArrayList<>();

					for (int i = 1; i <= columnCount; i++) {
						Object obj = rs.getObject(i);
						columns.add(obj != null ? obj.toString() : "");
					}
					list.add(columns); 
				}
			}
		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<TB_INSP_FS_SECURITY_LAPSES> getuntracebleData(String sus_no) {
		String currenYear = yearFormat.format(new Date());
		List<TB_INSP_FS_SECURITY_LAPSES> generList = new ArrayList<>();
		String sql = "SELECT id, untraceable_document, classification,originator_doc,custodian_doc, "
				+ "current_status, remarks " + "FROM TB_INSP_FS_SECURITY_LAPSES "
				+ "WHERE sus_no = ?  AND year = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_INSP_FS_SECURITY_LAPSES list = new TB_INSP_FS_SECURITY_LAPSES();
					list.setId(rs.getInt("id"));
					list.setUntraceable_document(rs.getString("untraceable_document"));
					list.setClassification(rs.getString("classification"));
					list.setRemarks(rs.getString("remarks"));
					list.setOriginator_doc(rs.getString("originator_doc"));
					list.setCustodian_doc(rs.getString("custodian_doc"));
					list.setCurrent_status(rs.getString("current_status"));

					generList.add(list);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving training ammunition data: " + e.getMessage());
			e.printStackTrace();
		}

		return generList;
	}



	@Override
	public List<TB_INSP_LOST_ID_ECR> getlostidEcrData(String sus_no) {
		String currenYear = yearFormat.format(new Date());
		List<TB_INSP_LOST_ID_ECR> generList = new ArrayList<>();
		String sql = "SELECT id, untraceable_document_token, classification,originator_doc_ecr,custodian_doc_ecr, "
				+ "current_status, remarks " + "FROM TB_INSP_LOST_ID_ECR "
				+ "WHERE sus_no = ?  AND year = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);		
			stmt.setString(2, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_INSP_LOST_ID_ECR list = new TB_INSP_LOST_ID_ECR();
					list.setId(rs.getInt("id"));
					list.setUntraceable_document_token(rs.getString("untraceable_document_token"));
					list.setClassification(rs.getString("classification"));
					list.setRemarks(rs.getString("remarks"));
					list.setOriginator_doc_ecr(rs.getString("originator_doc_ecr"));
					list.setCustodian_doc_ecr(rs.getString("custodian_doc_ecr"));
					list.setCurrent_status(rs.getString("current_status"));

					generList.add(list);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving training ammunition data: " + e.getMessage());
			e.printStackTrace();
		}

		return generList;
	}


@Override
	public ArrayList<ArrayList<String>> getEducationStandardsData(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";
			String currenYear = yearFormat.format(new Date());
			String roleSusNo = session.getAttribute("roleSusNo").toString();

			sql = "SELECT \n" + "\n" + "jco_affected,\n" + "	jco_qualified,\n" + "	jco_not_qualified,\n"
					+ "	jco_map,\n" + "	jco_education,\n" + "	jco_promotion,\n" + "\n" + "	nco_affected,\n"
					+ "	nco_qualified,\n" + "	nco_not_qualified,\n" + "	nco_map,\n" + "	nco_education,\n"
					+ "	nco_promotion,\n" + "\n" + "	or_affected,\n" + "	or_qualified,\n" + "	or_not_qualified,\n"
					+ "	or_map,\n" + "	or_education,\n" + "	or_promotion,\n" + "\n" + "	total_affected,\n"
					+ "	total_qualified,\n" + "	total_not_qualified,\n" + "	total_map,\n" + "	total_education,\n"
					+ "	total_promotion\n" + "\n"
					+ "from tb_insp_education_standards where sus_no=? and year=? order by id desc limit 1"; // TODO

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, roleSusNo);
			stmt.setString(2, currenYear);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public List<TB_MISO_INSP_COURSES_CAT_A_B> getCourseCatAandBAction(String sus_no) {
		List<TB_MISO_INSP_COURSES_CAT_A_B> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "select\n" + "	id,\n" + "	sus_no,\n" + "	course_name,\n" + "	officers_period,\n"
					+ "	jcos_period,\n" + "	ncos_period,\n" + "	or_period,\n" + "	officers_preceding,\n"
					+ "	jcos_preceding,\n" + "	ncos_preceding,\n" + "	or_preceding,\n" + "	totalofficers,\n"
					+ "	totaljcos,\n" + "	totalncos,\n" + "	totalor\n" + "from\n"
					+ "	tb_miso_insp_courses_cat_a_b\n" + "where\n" + "	sus_no=?  and year=? ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_MISO_INSP_COURSES_CAT_A_B list = new TB_MISO_INSP_COURSES_CAT_A_B();
				list.setCourse_name(rs.getString("course_name"));
				list.setOfficers_period(rs.getString("officers_period"));
				list.setJcos_period(rs.getString("jcos_period"));
				list.setNcos_period(rs.getString("ncos_period"));
				list.setOr_period(rs.getString("or_period"));
				list.setOfficers_preceding(rs.getString("officers_preceding"));
				list.setJcos_preceding(rs.getString("jcos_preceding"));
				list.setNcos_preceding(rs.getString("ncos_preceding"));
				list.setOr_preceding(rs.getString("or_preceding"));
				list.setTotalofficers(rs.getString("totalofficers"));
				list.setTotaljcos(rs.getString("totaljcos"));
				list.setTotalncos(rs.getString("totalncos"));
				list.setTotalor(rs.getString("totalor"));
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
	public List<TB_MISO_INSP_UPGRADATION> getupGradationdataAction(String sus_no) {
		List<TB_MISO_INSP_UPGRADATION> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; // Declare ResultSet outside try block
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "SELECT " + "    sus_no, " + "    id, " + "    trade, " + "    affected_up_gradation_class_iv, "
					+ "    affected_up_gradation_class_iii, " + "    affected_up_gradation_class_ii, "
					+ "    affected_up_gradation_class_i, " + "    during_up_gradation_class_iv, "
					+ "    during_up_gradation_class_iii, " + "    during_up_gradation_class_ii, "
					+ "    during_up_gradation_class_i, " + "    total_available_class_iv, "
					+ "    total_available_class_iii, " + "    total_available_class_ii, "
					+ "    total_available_class_i " + "FROM "
					+ "    tb_miso_insp_upgradation  where sus_no =? and year=? ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);
			rs = stmt.executeQuery();
			System.err.println("Query ");
			while (rs.next()) {
				TB_MISO_INSP_UPGRADATION list = new TB_MISO_INSP_UPGRADATION();
				list.setTrade(rs.getString("trade"));
				list.setAffected_up_gradation_class_iv(rs.getString("affected_up_gradation_class_iv"));
				list.setAffected_up_gradation_class_iii(rs.getString("affected_up_gradation_class_iii"));
				list.setAffected_up_gradation_class_ii(rs.getString("affected_up_gradation_class_ii"));
				list.setAffected_up_gradation_class_i(rs.getString("affected_up_gradation_class_i"));
				list.setDuring_up_gradation_class_iv(rs.getString("during_up_gradation_class_iv"));
				list.setDuring_up_gradation_class_iii(rs.getString("during_up_gradation_class_iii"));
				list.setDuring_up_gradation_class_ii(rs.getString("during_up_gradation_class_ii"));
				list.setDuring_up_gradation_class_i(rs.getString("during_up_gradation_class_i"));
				list.setTotal_available_class_iv(rs.getString("total_available_class_iv"));
				list.setTotal_available_class_iii(rs.getString("total_available_class_iii"));
				list.setTotal_available_class_ii(rs.getString("total_available_class_ii"));
				list.setTotal_available_class_i(rs.getString("total_available_class_i"));
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
	public List<Map<String, Object>> getRegLanguageExamData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String currenYear = yearFormat.format(new Date());
		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();
			String q = "select\n" + "	notyetqualified,\n" + "	numbersexempted,\n" + "	officersqualified,\n"
					+ "	qualifiedduringperiod\n" + "from\n"
					+ "	tb_miso_insp_reg_lang_exam where sus_no=?  and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());			
			stmt.setString(2, currenYear);

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
	public List<TB_MISO_INSP_TRAINING_AMMUNITION> gettrainningAmmunationData(String sus_no) {
		List<TB_MISO_INSP_TRAINING_AMMUNITION> generList = new ArrayList<>();
		String sql = "SELECT id, type_of_ammunition, au, qty_auth_full_trainning, "
				+ "recive_inclu_carried_forward, expended, balance, reason " + "FROM tb_miso_insp_training_ammunition "
				+ "WHERE sus_no = ?  AND year = ?";
		String currenYear = yearFormat.format(new Date());
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);		
			stmt.setString(2, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_MISO_INSP_TRAINING_AMMUNITION list = new TB_MISO_INSP_TRAINING_AMMUNITION();
					list.setId(rs.getInt("id"));
					list.setType_of_ammunition(rs.getString("type_of_ammunition"));
					list.setAu(rs.getString("au"));
					list.setQty_auth_full_trainning(rs.getString("qty_auth_full_trainning"));
					list.setRecive_inclu_carried_forward(rs.getString("recive_inclu_carried_forward"));
					list.setExpended(rs.getString("expended"));
					list.setBalance(rs.getString("balance"));
					list.setReason(rs.getString("reason"));

					generList.add(list);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving training ammunition data: " + e.getMessage());
			e.printStackTrace();
		}

		return generList;
	}



@Override
	public List<TB_MISO_INSP_SUMMARY_TECH_OTHER> getSummaryTechInspOtherData(String sus_no) {
		List<TB_MISO_INSP_SUMMARY_TECH_OTHER> generList = new ArrayList<>();
		String sql = "SELECT id,type_of_tech_insp, date, by_whome, result, remarks "
				+ "FROM tb_miso_insp_summary_tech_other " + "WHERE sus_no = ?  AND year = ?";
		String currenYear = yearFormat.format(new Date());
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);		
			stmt.setString(2, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_MISO_INSP_SUMMARY_TECH_OTHER list = new TB_MISO_INSP_SUMMARY_TECH_OTHER();
					list.setId(rs.getInt("id"));
					list.setType_of_tech_insp(rs.getString("type_of_tech_insp"));
					list.setDate(rs.getString("date"));
					list.setBy_whome(rs.getString("by_whome"));
					list.setResult(rs.getString("result"));
					list.setRemarks(rs.getString("remarks"));

					generList.add(list);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving summary tech other data: " + e.getMessage());
			e.printStackTrace();
		}

		return generList;
	}


@Override
	public ArrayList<ArrayList<String>> summary_tech_insp_Data(HttpSession session, HttpServletRequest request) {
	String currenYear = yearFormat.format(new Date());
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String q = "SELECT inspection_type, date_held, by_whom, result, detailed_remarks "
				+ "FROM tb_miso_insp_summary_tech " + "WHERE sus_no = ? AND year = ?   	ORDER BY \n"
				+ "    CASE inspection_type\n" + "        WHEN 'CEME' THEN 1\n" + "        WHEN 'PARS' THEN 2\n"
				+ "				   WHEN 'EMAE (SA)' THEN 3\n" + "        WHEN 'Cyber Security Audit' THEN 4\n"
				+ "        WHEN 'Training Validation' THEN 5     \n" + "        ELSE 6 \n" + "    END";

		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(q)) {
			stmt.setString(1, roleSusNo);		
			stmt.setString(2, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				System.err.println("summary_tech_insp_Data " + stmt);
				while (rs.next()) {
					ArrayList<String> columns = new ArrayList<>();

					for (int i = 1; i <= columnCount; i++) {
						Object obj = rs.getObject(i);
						columns.add(obj != null ? obj.toString() : "");
					}
					list.add(columns);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving Summary Tech Data: " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}





@Override
	public List<TB_INSP_OUTSTANDING_RENT_ALLIED> getoutstandingData(String sus_no) {
		List<TB_INSP_OUTSTANDING_RENT_ALLIED> generList = new ArrayList<>();
		String sql = "SELECT id, outstanding_year, amount_outstanding, " + "on_acc, remarks "
				+ "FROM TB_INSP_OUTSTANDING_RENT_ALLIED " + "WHERE sus_no = ? AND year = ?";
		String currenYear = yearFormat.format(new Date());
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_INSP_OUTSTANDING_RENT_ALLIED list = new TB_INSP_OUTSTANDING_RENT_ALLIED();
					list.setId(rs.getInt("id"));
					list.setAmount_outstanding(rs.getString("amount_outstanding"));
					list.setOn_acc(rs.getString("on_acc"));
					list.setRemarks(rs.getString("remarks"));
					list.setOutstanding_year(rs.getString("outstanding_year"));

					generList.add(list);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving training ammunition data: " + e.getMessage());
			e.printStackTrace();
		}

		return generList;
	}



@Override
	public List<TB_INSP_OUTSTANDING_LOSS_MT> getoutstandingLossMTData(String sus_no) {
		List<TB_INSP_OUTSTANDING_LOSS_MT> generList = new ArrayList<>();
		String sql = "SELECT id, outstanding_year, nature_of_loss, " + "value, remarks "
				+ "FROM TB_INSP_OUTSTANDING_LOSS_MT " + "WHERE sus_no = ?  AND year = ?";
		String currenYear = yearFormat.format(new Date());
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_INSP_OUTSTANDING_LOSS_MT list = new TB_INSP_OUTSTANDING_LOSS_MT();
					list.setId(rs.getInt("id"));
					list.setNature_of_loss(rs.getString("nature_of_loss"));
					list.setValue(rs.getString("value"));
					list.setRemarks(rs.getString("remarks"));
					list.setOutstanding_year(rs.getString("outstanding_year"));

					generList.add(list);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving training ammunition data: " + e.getMessage());
			e.printStackTrace();
		}

		return generList;
	}




@Override
	public List<TB_INSP_FATAL_INCIDENTS> getfatalIncidentData(String sus_no) {
		List<TB_INSP_FATAL_INCIDENTS> generList = new ArrayList<>();
		String sql = "SELECT id, date, nature_of_insident, " + "casualty, remarks " + "FROM TB_INSP_FATAL_INCIDENTS "
				+ "WHERE sus_no = ? AND year = ?";
		String currenYear = yearFormat.format(new Date());
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);		
			stmt.setString(2, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_INSP_FATAL_INCIDENTS list = new TB_INSP_FATAL_INCIDENTS();
					list.setId(rs.getInt("id"));
					list.setDate(rs.getString("date"));
					list.setNature_of_insident(rs.getString("nature_of_insident"));
					list.setRemarks(rs.getString("remarks"));
					list.setCasualty(rs.getString("casualty"));

					generList.add(list);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving training ammunition data: " + e.getMessage());
			e.printStackTrace();
		}

		return generList;
	}

@Override
	public List<TB_INSP_SECURITY_LAPSES> getsequrityLapsesData(String sus_no) {
		List<TB_INSP_SECURITY_LAPSES> generList = new ArrayList<>();
		String sql = "SELECT id, dateof, nature_of_security_lapse, " + "resulted_in, remarks "
				+ "FROM TB_INSP_SECURITY_LAPSES " + "WHERE sus_no = ? AND year = ?";
		String currenYear = yearFormat.format(new Date());
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_INSP_SECURITY_LAPSES list = new TB_INSP_SECURITY_LAPSES();
					list.setId(rs.getInt("id"));
					list.setDateof(rs.getString("dateof"));
					list.setNature_of_security_lapse(rs.getString("nature_of_security_lapse"));
					list.setRemarks(rs.getString("remarks"));
					list.setResulted_in(rs.getString("resulted_in"));

					generList.add(list);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving training ammunition data: " + e.getMessage());
			e.printStackTrace();
		}

		return generList;
	}

@Override
	public List<TB_INSP_OUTSIDE_ATTACHMENTS> getoutsideAttachmentData(String sus_no) {
		List<TB_INSP_OUTSIDE_ATTACHMENTS> generList = new ArrayList<>();
		String sql = "SELECT id, location, number_of_personnel_attached, " + "duration, remarks "
				+ "FROM TB_INSP_OUTSIDE_ATTACHMENTS " + "WHERE sus_no = ? AND year = ?";
		String currenYear = yearFormat.format(new Date());
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_INSP_OUTSIDE_ATTACHMENTS list = new TB_INSP_OUTSIDE_ATTACHMENTS();
					list.setId(rs.getInt("id"));
					list.setLocation(rs.getString("location"));
					list.setNumber_of_personnel_attached(rs.getString("number_of_personnel_attached"));
					list.setRemarks(rs.getString("remarks"));
					list.setDuration(rs.getString("duration"));

					generList.add(list);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving training ammunition data: " + e.getMessage());
			e.printStackTrace();
		}

		return generList;
	}


public ArrayList<ArrayList<String>> getPpt_Result_Data(HttpSession session, HttpServletRequest request) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String q = "SELECT personnel, total_no, excellent, good, satisfactory, poor, fail, number_yet_to_tested, remarks "
				+ "FROM tb_miso_insp_ppt_result " + "WHERE sus_no = ?  AND year = ?";
		String currenYear = yearFormat.format(new Date());
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(q)) {
			stmt.setString(1, roleSusNo);			
			stmt.setString(2, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();

				while (rs.next()) {
					ArrayList<String> columns = new ArrayList<>();

					for (int i = 1; i <= columnCount; i++) {
						Object obj = rs.getObject(i);
						columns.add(obj != null ? obj.toString() : "");
					}
					list.add(columns);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving PPT Result Data: " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}


@Override
	public ArrayList<ArrayList<String>> getCoursesData(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
        	String sus_no = session.getAttribute("roleSusNo").toString();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";
			String currenYear = yearFormat.format(new Date());
			sql = "SELECT\r\n"
					+ "    COALESCE(COURSE_NAME, 'NIL') AS COURSE_NAME,\r\n"
					+ "    COALESCE(CAST(NUMBER_OF_COURSE AS VARCHAR(255)), 'NIL') AS NUMBER_OF_COURSE,\r\n"
					+ "    COALESCE(CAST(PERIOD_FROM AS VARCHAR(255)), 'NIL') AS PERIOD_FROM,\r\n"
					+ "    COALESCE(CAST(PERIOD_TO AS VARCHAR(255)), 'NIL') AS PERIOD_TO,\r\n"
					+ "    COALESCE(CAST(TOTAL_ALLOTTED AS VARCHAR(255)), 'NIL') AS TOTAL_ALLOTTED,\r\n"
					+ "    COALESCE(CAST(ATTENDED AS VARCHAR(255)), 'NIL') AS ATTENDED,\r\n"
					+ "    COALESCE(CAST(RTU AS VARCHAR(255)), 'NIL') AS RTU,\r\n"
					+ "    COALESCE(DETAILED_REMARKS, 'NIL') AS DETAILED_REMARKS,\r\n"
					+ "    ID\r\n"
					+ "FROM\r\n"
					+ "    TB_DETAIL_COURSES where sus_no =? and year=?\r\n"
					+ "ORDER BY\r\n"
					+ "    ID"; // TODO

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Courses --> " + stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getStandardsAchievedData(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String currenYear = yearFormat.format(new Date());
		String sus_no = session.getAttribute("roleSusNo").toString();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\n" + "	course_name,\n" + "	total,\n" + "	grading,\n" + "	da,\n" + "	failed,\n"
					+ "	rtu,\n" + "	detailed_remarks,\n" + " ID\n" + " FROM\n"
					+ "	TB_INSP_STANDARDS_ACHIEVED where sus_no = ? and year=? ORDER BY ID"; // TODO

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public List<TB_INSP_TD_PROCEEDED> getTDOfficersAction(String sus_no, HttpSession session) {
		String username = session.getAttribute("username").toString();
		List<TB_INSP_TD_PROCEEDED> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "SELECT\n" + "	rank_and_name,\n" + "	nature_duty,\n" + "	ordered_by,\n" + "	detailed_remarks,\n"
					+ " ID\n" + " FROM\n" + "	TB_INSP_TD_PROCEEDED \n" + "where\n"
					+ "	sus_no=?  and year=? ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_INSP_TD_PROCEEDED list = new TB_INSP_TD_PROCEEDED();
				list.setRank_and_name(rs.getString("rank_and_name"));
				list.setNature_duty(rs.getString("nature_duty"));
				list.setOrdered_by(rs.getString("ordered_by"));
				list.setDetailed_remarks(rs.getString("detailed_remarks"));
				list.setSus_no(sus_no);
				list.setStatus(0);
				list.setYear(currenYear);
				list.setCreated_by(username);
				list.setCreated_on(new Date());
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
	public List<TB_INSP_PIO_CALL_LAPSES> getPCLAction(String sus_no, HttpSession session) {
		String username = session.getAttribute("username").toString();
		List<TB_INSP_PIO_CALL_LAPSES> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "SELECT\n" + "	date_violation_initial,\n" + "	date_violation_fmn,\n" + "	number_rank_name,\n"
					+ "	curr_status,\n" + "	loss_info,\n" + "	remarks,\n" + " ID\n" + " FROM\n"
					+ "	TB_INSP_PIO_CALL_LAPSES \n" + "where\n"
					+ "	sus_no=?  and year=? ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_INSP_PIO_CALL_LAPSES list = new TB_INSP_PIO_CALL_LAPSES();
				list.setDate_violation_initial(rs.getDate("date_violation_initial"));
				list.setDate_violation_fmn(rs.getDate("date_violation_fmn"));
				list.setNumber_rank_name(rs.getString("number_rank_name"));
				list.setCurr_status(rs.getString("curr_status"));
				list.setLoss_info(rs.getString("loss_info"));
				list.setRemarks(rs.getString("remarks"));
				list.setSus_no(sus_no);
				list.setStatus(0);
				list.setYear(currenYear);
				list.setCreated_by(username);
				list.setCreated_on(new Date());
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
	public List<TB_INSP_LOST_CDS_DVDS> getlostCdDvdData(String sus_no) {
		List<TB_INSP_LOST_CDS_DVDS> generList = new ArrayList<>();
		String sql = "SELECT id, untraceble_cd_dvd, classification,originator_cd,custodian_cd, "
				+ "current_status, remarks " + "FROM TB_INSP_LOST_CDS_DVDS "
				+ "WHERE sus_no = ?  AND year = ?";
		String currenYear = yearFormat.format(new Date());
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_INSP_LOST_CDS_DVDS list = new TB_INSP_LOST_CDS_DVDS();
					list.setId(rs.getInt("id"));
					list.setUntraceble_cd_dvd(rs.getString("untraceble_cd_dvd"));
					list.setClassification(rs.getString("classification"));
					list.setRemarks(rs.getString("remarks"));
					list.setOriginator_cd(rs.getString("originator_cd"));
					list.setCustodian_cd(rs.getString("custodian_cd"));
					list.setCurrent_status(rs.getString("current_status"));

					generList.add(list);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving training ammunition data: " + e.getMessage());
			e.printStackTrace();
		}

		return generList;
	}



@Override
	public List<Map<String, Object>> getLandData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String currenYear = yearFormat.format(new Date());
		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();//HERE
			String q = "select defenceLandParticulars, landRecordRegisterMaintained, landDemarcated,\n"
					+ "	landUtilized, vacantLandDetails, safetyMeasuresAdequate, evictionActionDetails, remarksSuggestions from \n"
					+ "	tb_miso_insp_land where sus_no=?  and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());			
			stmt.setString(2, currenYear);

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
	public List<TB_INSP_FS_SECURITY_LAPSES> getInspFsSecurityData(String sus_no) {
		List<TB_INSP_FS_SECURITY_LAPSES> generList = new ArrayList<>();
		String sql = "SELECT id, untraceable_document, classification,originator_doc,custodian_doc, "
				+ "current_status, remarks " + "FROM TB_INSP_FS_SECURITY_LAPSES "
				+ "WHERE sus_no = ? AND year = ?";
		String currenYear = yearFormat.format(new Date());
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_INSP_FS_SECURITY_LAPSES list = new TB_INSP_FS_SECURITY_LAPSES();
					list.setId(rs.getInt("id"));
					list.setUntraceable_document(rs.getString("untraceable_document"));
					list.setClassification(rs.getString("classification"));
					list.setOriginator_doc(rs.getString("originator_doc"));
					list.setCustodian_doc(rs.getString("custodian_doc"));
					list.setCurrent_status(rs.getString("current_status"));
					list.setRemarks(rs.getString("remarks"));

					generList.add(list);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving training ammunition data: " + e.getMessage());
			e.printStackTrace();
		}

		return generList;
	}


@Override
	public List<Map<String, Object>> getequpAnnualMeterageData(String sus_no) {
		List<Map<String, Object>> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "select\n" + "	id,\n" + "	sus_no,\n" + "	type_of_duty,\n" + "	equp_authorize,\n"
					+ "	equp_cove,\n" + "	equp_remark  from\n"
					+ "	tb_insp_equp_annual_meterage  where sus_no = ? and year =? \n";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> list = new HashMap<>();
				list.put("sus_no", rs.getString("sus_no"));
				list.put("type_of_duty", rs.getString("type_of_duty"));
				list.put("equp_authorize", rs.getString("equp_authorize"));
				list.put("equp_cove", rs.getString("equp_cove"));
				list.put("equp_remark", rs.getString("equp_remark"));
				list.put("id", rs.getInt("id"));

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
	public ArrayList<ArrayList<String>> getTDOfficersData(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sus_no = session.getAttribute("roleSusNo").toString();
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\n" + "	rank_and_name,\n" + "	nature_duty,\n" + "	ordered_by,\n" + "	detailed_remarks,\n"
					+ " ID\n" + " FROM\n" + "	TB_INSP_TD_PROCEEDED where sus_no =? and year=? ORDER BY ID";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getSMLData(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sus_no = session.getAttribute("roleSusNo").toString();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";
			String currenYear = yearFormat.format(new Date());
			sql = "SELECT\n" + "	date_violation_initial,\n" + "	date_violation_fmn,\n" + "	number_rank_name,\n"
					+ "	curr_status,\n" + "	loss_info,\n" + "	remarks,\n" + " ID\n" + " FROM\n"
					+ "	TB_INSP_SOCIAL_MEDIA_LAPSES where sus_no=? and year=? ORDER BY ID";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getPCLData(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sus_no = session.getAttribute("roleSusNo").toString();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";
			String currenYear = yearFormat.format(new Date());
			sql = "SELECT\n" + "	date_violation_initial,\n" + "	date_violation_fmn,\n" + "	number_rank_name,\n"
					+ "	curr_status,\n" + "	loss_info,\n" + "	remarks,\n" + " ID\n" + " FROM\n"
					+ "	TB_INSP_PIO_CALL_LAPSES where sus_no=? and year =? ORDER BY ID";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getELData(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sus_no = session.getAttribute("roleSusNo").toString();
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\n" + "	date_violation_initial,\n" + "	date_violation_fmn,\n" + "	number_rank_name,\n"
					+ "	curr_status,\n" + "	loss_info,\n" + "	remarks,\n" + " ID\n" + " FROM\n"
					+ "	TB_INSP_ESPIONAGE_LAPSES where sus_no=? and year=? ORDER BY ID";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					};
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
	public ArrayList<ArrayList<String>> getCPCLData(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sus_no = session.getAttribute("roleSusNo").toString();
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\n" + "	date_violation_initial,\n" + "	date_violation_fmn,\n" + "	number_rank_name,\n"
					+ "	curr_status,\n" + "	loss_info,\n" + "	remarks,\n" + " ID\n" + " FROM\n"
					+ "	TB_INSP_CELL_PHONE_LAPSES where sus_no =? and year=? ORDER BY ID";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);			
			stmt.setString(2, currenYear);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public List<Map<String, Object>> getEstablishmentData(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String roleSusNo = (String) session.getAttribute("roleSusNo"); // Avoid potential NullPointerException

		try {
			conn = dataSource.getConnection();
			String q = "select \n" + "a.sus_no,\n" + "sum(a.off_regu_held) as off_regu_held,\n"
					+ "sum(a.off_regu_auth) as off_regu_auth,\n" + "CASE \n"
					+ "    WHEN SUM(a.off_regu_held) - SUM(a.off_regu_auth) > 0 THEN SUM(a.off_regu_held) - SUM(a.off_regu_auth)\n"
					+ "    ELSE 0\n" + "END AS off_regu_sur,\n" + "CASE \n"
					+ "    WHEN SUM(a.off_regu_auth) - SUM(a.off_regu_held) > 0 THEN SUM(a.off_regu_auth) - SUM(a.off_regu_held)\n"
					+ "    ELSE 0\n" + "END AS off_regu_defi,\n" + "\n" + "sum(a.jco_regu_held) as jco_regu_held,\n"
					+ "sum(a.jco_regu_auth) as jco_regu_auth,\n" + "CASE \n"
					+ "    WHEN SUM(a.jco_regu_held) - SUM(a.jco_regu_auth) > 0 THEN SUM(a.jco_regu_held) - SUM(a.jco_regu_auth)\n"
					+ "    ELSE 0\n" + "END AS jco_regu_sur,\n" + "CASE \n"
					+ "    WHEN SUM(a.jco_regu_auth) - SUM(a.jco_regu_held) > 0 THEN SUM(a.jco_regu_auth) - SUM(a.jco_regu_held)\n"
					+ "    ELSE 0\n" + "END AS jco_regu_defi,\n" + "sum(a.or_regu_held) as or_regu_held,\n"
					+ "sum(a.or_regu_auth) as or_regu_auth,\n" + "CASE \n"
					+ "    WHEN SUM(a.or_regu_held) - SUM(a.or_regu_auth) > 0 THEN SUM(a.or_regu_held) - SUM(a.or_regu_auth)\n"
					+ "    ELSE 0\n" + "END AS or_regu_sur,\n" + "CASE \n"
					+ "    WHEN SUM(a.or_regu_auth) - SUM(a.or_regu_held) > 0 THEN SUM(a.or_regu_auth) - SUM(a.or_regu_held)\n"
					+ "    ELSE 0\n" + "END AS or_regu_defi,\n" + "sum(a.civ_regu_held) as civ_regu_held,\n"
					+ "sum(a.civ_regu_auth) as civ_regu_auth,\n" + "CASE \n"
					+ "    WHEN SUM(a.civ_regu_held) - SUM(a.civ_regu_auth) > 0 THEN SUM(a.civ_regu_held) - SUM(a.civ_regu_auth)\n"
					+ "    ELSE 0\n" + "END AS civ_regu_sur,\n" + "CASE \n"
					+ "    WHEN SUM(a.civ_regu_auth) - SUM(a.civ_regu_held) > 0 THEN SUM(a.civ_regu_auth) - SUM(a.civ_regu_held)\n"
					+ "    ELSE 0\n" + "END AS civ_regu_defi,\n"
					+ "sum(a.warrantoff_regu_held) as warrantoff_regu_held,\n"
					+ "sum(a.warrantoff_regu_auth) as warrantoff_regu_auth,\n" + "CASE \n"
					+ "    WHEN SUM(a.warrantoff_regu_held) - SUM(a.warrantoff_regu_auth) > 0 THEN SUM(a.warrantoff_regu_held) - SUM(a.warrantoff_regu_auth)\n"
					+ "    ELSE 0\n" + "END AS warrantoff_regu_sur,\n" + "CASE \n"
					+ "    WHEN SUM(a.warrantoff_regu_auth) - SUM(a.warrantoff_regu_held) > 0 THEN SUM(a.warrantoff_regu_auth) - SUM(a.warrantoff_regu_held)\n"
					+ "    ELSE 0\n" + "END AS warrantoff_regu_defi\n" + "\n" + "from (\n" + "SELECT\n" + "n1.sus_no,\n"
					+ "COALESCE(SUM(n1.brig_above) + SUM(n1.col) + SUM(n1.col_ts) + SUM(n1.ltcol) + SUM(n1.maj) + SUM(n1.capt_lt),0) as off_regu_held,\n"
					+ "COALESCE( ( SELECT \n"
					+ "	     SUM(a.base_auth + a.mod_auth + a.foot_auth) as off_regu_auth	\n"
					+ "             FROM sus_pers_stdauth a \n"
					+ "             LEFT JOIN orbat_all_details_view c ON c.sus_no = a.sus_no \n"
					+ "             WHERE c.status_sus_no = 'Active' \n"
					+ "                     AND a.rank_cat = '0' and c.sus_no = n1.sus_no),0) as off_regu_auth,\n"
					+ "	0 as jco_regu_held,\n" + "	0 as jco_regu_auth,\n" + "	0 as or_regu_held,\n"
					+ "	0 as or_regu_auth,\n" + "	0 as civ_regu_held,\n" + "	0 as civ_regu_auth,\n"
					+ "	0 as warrantoff_regu_held,\n" + "	0 as warrantoff_regu_auth\n" + "FROM\n" + "(\n"
					+ "    SELECT\n" + "      e.arm_code, c.unit_sus_no AS sus_no,\n"
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
					+ "    WHERE   LEFT(c.personnel_no, 2) NOT IN ('NR', 'NS') \n"
					+ "    GROUP BY 1, 2 ) n1  group by 1\n" + "\n" + "\n" + "union all \n" + "\n" + "\n"
					+ "      SELECT\n" + "n2.unit_sus_no as sus_no ,\n" + "  0 as off_regu_held,\n"
					+ "  0 as off_regu_auth,\n" + "	COALESCE(	SUM(n2.jco),	0) as jco_regu_held,\n"
					+ "		    COALESCE (( SELECT \n"
					+ "                             SUM(a.base_auth + a.mod_auth + a.foot_auth) AS auth\n"
					+ "             FROM sus_pers_stdauth a \n"
					+ "             LEFT JOIN orbat_all_details_view b ON b.sus_no = a.sus_no \n"
					+ "             WHERE b.status_sus_no = 'Active' \n"
					+ "                     AND a.rank_cat = '1' and b.sus_no = n2.unit_sus_no ),0) as jco_regu_auth,\n"
					+ "	0 as or_regu_held,\n" + "	0 as or_regu_auth,\n" + "	0 as civ_regu_held,\n"
					+ "	0 as civ_regu_auth,\n" + "	0 as warrantoff_regu_held,\n" + "	0 as warrantoff_regu_auth\n"
					+ "FROM (\n" + "SELECT\n" + "	e.arm_code,\n" + "	c.unit_sus_no,\n" + "	COUNT(c.id) AS jco\n"
					+ "FROM\n" + "	tb_psg_census_jco_or_p c\n" + "	INNER JOIN tb_psg_mstr_rank_jco r ON c.rank=r.id\n"
					+ "	AND c.status='1'\n" + "	LEFT JOIN tb_miso_orbat_arm_code e ON e.arm_code=c.arm_service\n"
					+ "	LEFT JOIN orbat_all_details_view b ON b.sus_no=c.unit_sus_no\n"
					+ "	AND b.status_sus_no='Active'\n" + "WHERE\n"
					+ "	c.category='JCO'  GROUP BY	1,	2 ) n2 group by 1 \n" + "\n" + "\n" + "	union all \n" + "\n"
					+ "	SELECT\n" + "	c.unit_sus_no as sus_no,\n" + "   0 as off_regu_held,\n"
					+ "  0 as off_regu_auth,\n" + "	0 as jco_regu_held,\n" + "  0 as jco_regu_auth,\n"
					+ "	COUNT(c.id) as or_regu_held,\n"
					+ " 	COALESCE((SELECT  SUM(a.base_auth + a.mod_auth + a.foot_auth) AS auth\n"
					+ "             FROM sus_pers_stdauth a \n"
					+ "             LEFT JOIN orbat_all_details_view b ON b.sus_no = a.sus_no \n"
					+ "             WHERE b.status_sus_no = 'Active' \n"
					+ "                     AND a.rank_cat = '3' and b.sus_no =c.unit_sus_no ),0) as or_regu_auth,\n"
					+ "	0 as civ_regu_held,\n" + "	0 as civ_regu_auth,\n" + "	0 as warrantoff_regu_held,\n"
					+ "	0 as warrantoff_regu_auth	\n" + "\n" + "FROM\n" + "	tb_psg_census_jco_or_p c\n"
					+ "	INNER JOIN tb_psg_mstr_rank_jco r ON c.rank=r.id\n" + "	AND c.status='1'\n"
					+ "	LEFT JOIN tb_miso_orbat_arm_code e ON e.arm_code=c.arm_service\n"
					+ "	LEFT JOIN orbat_all_details_view b ON b.sus_no=c.unit_sus_no\n"
					+ "	AND b.status_sus_no='Active'\n" + "where\n" + "	c.category='OR'\n" + "GROUP BY 1\n" + "\n"
					+ "union all\n" + "\n" + "SELECT\n" + "a.sus_no as sus_no,\n" + " 0 as off_regu_held,\n"
					+ "  0 as off_regu_auth,\n" + "	0 as jco_regu_held,\n" + "  0 as jco_regu_auth,\n"
					+ "	0 as or_regu_held,\n" + "  0 as or_regu_auth,\n" + "	COUNT(A.id) as civ_regu_held,\n"
					+ "	COALESCE ((SELECT  SUM(a.base_auth + a.mod_auth + a.foot_auth) AS auth\n"
					+ "             FROM sus_pers_stdauth a \n"
					+ "             inner JOIN orbat_all_details_view b ON b.sus_no = a.sus_no \n"
					+ "             WHERE b.status_sus_no = 'Active' \n"
					+ "              AND a.rank_cat in ('4', '5', '6', '7', '8', '9', '10', '11') and b.sus_no =a.sus_no),0) as civ_regu_auth,\n"
					+ "\n" + "	0 as warrantoff_regu_held,\n" + "	0 as warrantoff_regu_auth	\n" + "\n" + "FROM\n"
					+ " tb_psg_civilian_dtl a\n" + " INNER JOIN t_domain_value t1\n"
					+ "    ON t1.codevalue = a.civ_type\n" + "   AND t1.domainid = 'CIVILIAN_TYPE'\n"
					+ " INNER JOIN tb_psg_mstr_gender g\n" + "    ON g.id = a.gender\n"
					+ " INNER JOIN t_domain_value t\n" + "    ON t.codevalue = a.classification_services\n"
					+ "   AND t.domainid = 'CLASSIFICATION_OF_SERVICES'\n"
					+ " INNER JOIN cue_tb_psg_rank_app_master b\n" + "    ON a.designation = b.id\n"
					+ "   AND upper(b.level_in_hierarchy) = upper('Appointment')\n"
					+ "   AND b.parent_code IN ('4','5','6','7','8','9','10','11')\n"
					+ "   AND b.status_active = 'Active'\n" + "   AND a.status = 1\n" + "  group by 1\n" + "\n"
					+ " union all\n" + "\n" + " \n" + "			SELECT	\n" + "			c.unit_sus_no as sus_no,\n"
					+ "			0 as off_regu_held,\n" + "      0 as off_regu_auth,\n" + "	    0 as jco_regu_held,\n"
					+ "      0 as jco_regu_auth,\n" + "	    0 as or_regu_held,\n" + "      0 as or_regu_auth,\n"
					+ "	    0 as civ_regu_held,\n" + "     0 as civ_regu_auth,\n"
					+ "		COUNT(c.id) as warrantoff_regu_held,\n" + "  ( SELECT\n"
					+ "COALESCE(SUM(a.base_auth+a.mod_auth+a.foot_auth),0) AS auth\n" + "FROM\n"
					+ "	sus_pers_stdauth a\n" + "	inner JOIN orbat_all_details_view b ON b.sus_no=a.sus_no\n"
					+ "WHERE\n" + "	b.status_sus_no='Active'\n" + "	AND a.rank_cat='1'\n" + "	and rank_code='NWRK1'\n"
					+ "	and b.sus_no =c.unit_sus_no)  as warrantoff_regu_auth		\n" + "		FROM\n"
					+ "			tb_psg_census_jco_or_p c\n"
					+ "			INNER JOIN tb_psg_mstr_rank_jco r ON c.rank=r.id\n" + "			AND c.status='1'\n"
					+ "			and r.rank='WARRANT OFFICER'		\n" + "		inner join tb_miso_orbat_unt_dtl unt \n"
					+ "		on unt.sus_no = c.unit_sus_no\n" + "		 and unt.status_sus_no = 'Active'\n"
					+ "		GROUP BY 1)a	where a.sus_no = ?	group by 1";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());

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
	public ArrayList<ArrayList<String>> getEquiment_Data(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String roleSusNo;

		roleSusNo = session.getAttribute("roleSusNo").toString();

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String currenYear = yearFormat.format(new Date());
			String q1 = "\r\n" + 
					"\r\n" + 
					"WITH\r\n" + 
					"  VehiclePercentagesA AS (\r\n" + 
					"      SELECT\r\n" + 
					"          ROUND(SUM(CASE WHEN vehcl_classfctn = '1' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class1,\r\n" + 
					" 		ROUND(SUM(CASE WHEN vehcl_classfctn = '2' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class2,\r\n" + 
					"	ROUND(SUM(CASE WHEN vehcl_classfctn = '3' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class3,\r\n" + 
					"	ROUND(SUM(CASE WHEN vehcl_classfctn = '4' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class4,\r\n" + 
					"	ROUND(SUM(CASE WHEN vehcl_classfctn = '5' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class5,\r\n" + 
					"	ROUND(SUM(CASE WHEN vehcl_classfctn = '6' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class6,\r\n" + 
					"	ROUND(SUM(CASE WHEN vehcl_classfctn = '7' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class7\r\n" + 
					"      FROM\r\n" + 
					"          tb_tms_census_retrn where sus_no = ? \r\n" + 
					"  )\r\n" + 
					"\r\n" + 
					"SELECT\r\n" + 
					"	t1.type,\r\n" + 
					"	SUM(t1.ue) AS ue,\r\n" + 
					"	SUM(t1.total_uh) AS uh,\r\n" + 
					"	CASE WHEN SUM(t1.total_uh) > SUM(t1.ue) THEN SUM(t1.total_uh) - SUM(t1.ue) ELSE 0 END AS surplus,\r\n" + 
					"	CASE WHEN SUM(t1.ue) > SUM(t1.total_uh) THEN SUM(t1.ue) - SUM(t1.total_uh) ELSE 0 END AS deficiency,\r\n" + 
					"	vpA.class1,\r\n" + 
					"	vpA.class2,\r\n" + 
					"	vpA.class3,\r\n" + 
					"	vpA.class4,\r\n" + 
					"	vpA.class5,\r\n" + 
					"	vpA.class6,\r\n" + 
					"	vpA.class7,\r\n" + 
					"	(select remarks from tb_insp_equp_a_b_c_veh where status=0 and type_of_veh ='A' AND YEAR=? and sus_no = ?)  as remarks \r\n" + 
					"FROM tms_vehicle_status_a_b_c_with_ue_uh t1\r\n" + 
					" cross JOIN VehiclePercentagesA vpA\r\n" + 
					"WHERE t1.type IN ('A') AND t1.sus_no = ? \r\n" + 
					"GROUP BY t1.type,\r\n" + 
					"vpA.class1,\r\n" + 
					"	vpA.class2,\r\n" + 
					"	vpA.class3,\r\n" + 
					"	vpA.class4,\r\n" + 
					"	vpA.class5,\r\n" + 
					"	vpA.class6,\r\n" + 
					"	vpA.class7 order by t1.type\r\n" + 
					"";

			stmt = conn.prepareStatement(q1);
			stmt.setString(1, roleSusNo);
			stmt.setString(2, currenYear);
			stmt.setString(3, roleSusNo);
			stmt.setString(4, roleSusNo);
			ResultSet rs1 = stmt.executeQuery();
			System.err.println("getEquiment_Data 1 --> \n " +stmt);
			ResultSetMetaData metaData1 = rs1.getMetaData();
			int columnCount1 = metaData1.getColumnCount();

			if (!rs1.isBeforeFirst()) {
				ArrayList<String> nilColumns = new ArrayList<>();
				for (int i = 1; i <= columnCount1; i++) {
					nilColumns.add("NIL");
				}
				nilColumns.set(0, "A");
				list.add(nilColumns);
			} else {
				if (rs1.next()) {
					ArrayList<String> columns = new ArrayList<>();
					for (int i = 1; i <= columnCount1; i++) {
						Object obj = rs1.getObject(i);
						String value = (obj != null) ? obj.toString() : "NIL";
						columns.add(value);
					}
					list.add(columns);
				}
			}
			rs1.close();

			String q2 = "WITH\n" + "  VehiclePercentagesB AS (\n" + "      SELECT\n"
					+ "          ROUND(SUM(CASE WHEN classification = '1' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class1,\n"
					+ "          ROUND(SUM(CASE WHEN classification = '2' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class2,\n"
					+ "          ROUND(SUM(CASE WHEN classification = '3' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class3,\n"
					+ "          ROUND(SUM(CASE WHEN classification = '4' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class4,\n"
					+ "          ROUND(SUM(CASE WHEN classification = '5' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class5,\n"
					+ "          ROUND(SUM(CASE WHEN classification = '6' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class6,\n"
					+ "          ROUND(SUM(CASE WHEN classification = '7' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class7\n"
					+ "      FROM\n" + "          tb_tms_mvcr_parta_dtl where sus_no = ? \n" + "  ),\n" + "  VehiclePercentagesC AS (\n"
					+ "      SELECT\n"
					+ "          ROUND(SUM(CASE WHEN classification = '1' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class1,\n"
					+ "          ROUND(SUM(CASE WHEN classification = '2' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class2,\n"
					+ "          ROUND(SUM(CASE WHEN classification = '3' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class3,\n"
					+ "          ROUND(SUM(CASE WHEN classification = '4' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class4,\n"
					+ "          ROUND(SUM(CASE WHEN classification = '5' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class5,\n"
					+ "          ROUND(SUM(CASE WHEN classification = '6' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class6,\n"
					+ "          ROUND(SUM(CASE WHEN classification = '7' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS class7\n"
					+ "      FROM\n" + "          tb_tms_emar_report where sus_no = ?  \n" + "  )\n" + "SELECT\n" + "  t1.type,\n"
					+ "  SUM(t1.ue) AS ue,\n" + "  SUM(t1.total_uh) AS uh,\n" + "  CASE\n"
					+ "      WHEN SUM(t1.total_uh) > SUM(t1.ue) THEN SUM(t1.total_uh) - SUM(t1.ue)\n" + "      ELSE 0\n"
					+ "  END AS surplus,\n" + "  CASE\n"
					+ "      WHEN SUM(t1.ue) > SUM(t1.total_uh) THEN SUM(t1.ue) - SUM(t1.total_uh)\n" + "      ELSE 0\n"
					+ "  END AS deficiency,\n" + "  CASE\n" + "      WHEN t1.type = 'B' THEN vpB.class1\n"
					+ "      ELSE vpC.class1\n" + "  END AS class1,\n" + "  CASE\n"
					+ "      WHEN t1.type = 'B' THEN vpB.class2\n" + "      ELSE vpC.class2\n" + "  END AS class2,\n"
					+ "  CASE\n" + "      WHEN t1.type = 'B' THEN vpB.class3\n" + "      ELSE vpC.class3\n"
					+ "  END AS class3,\n" + "  CASE\n" + "      WHEN t1.type = 'B' THEN vpB.class4\n"
					+ "      ELSE vpC.class4\n" + "  END AS class4,\n" + "  CASE\n"
					+ "      WHEN t1.type = 'B' THEN vpB.class5\n" + "      ELSE vpC.class5\n" + "  END AS class5,\n"
					+ "  CASE\n" + "      WHEN t1.type = 'B' THEN vpB.class6\n" + "      ELSE vpC.class6\n"
					+ "  END AS class6,\n" + "  CASE\n" + "      WHEN t1.type = 'B' THEN vpB.class7\n"
					+ "      ELSE vpC.class7\n"
					+ "  END AS class7,(select remarks from tb_insp_equp_a_b_c_veh where status=0 and type_of_veh = t1.type AND YEAR=? and sus_no = ?)  as remarks\n"
					+ "FROM\n" + "  tms_vehicle_status_a_b_c_with_ue_uh t1\n" + "  CROSS JOIN VehiclePercentagesB vpB\n"
					+ "  CROSS JOIN VehiclePercentagesC vpC\n" + "WHERE\n"
					+ "  t1.type IN ('B', 'C')  and t1.sus_no =?\n" + "GROUP BY\n" + "  t1.type,\n" + "  vpB.class1,\n"
					+ "  vpB.class2,\n" + "  vpB.class3,\n" + "  vpB.class4,\n" + "  vpB.class5,\n" + "  vpB.class6,\n"
					+ "  vpB.class7,\n" + "  vpC.class1,\n" + "  vpC.class2,\n" + "  vpC.class3,\n" + "  vpC.class4,\n"
					+ "  vpC.class5,\n" + "  vpC.class6,\n" + "  vpC.class7\n" + "ORDER BY\n" + "  t1.type";

			stmt = conn.prepareStatement(q2);
			stmt.setString(1, roleSusNo);
			stmt.setString(2, roleSusNo);
			stmt.setString(3, currenYear);
			stmt.setString(4, roleSusNo);
			stmt.setString(5, roleSusNo);
			ResultSet rs2 = stmt.executeQuery();
			System.err.println("getEquiment_Data 2 --> \n" +stmt);
			ResultSetMetaData metaData2 = rs2.getMetaData();
			int columnCount2 = metaData2.getColumnCount();

			String[] expectedTypes = { "B", "C" };
			int typeIndex = 0;

			while (rs2.next() && typeIndex < expectedTypes.length) {
				ArrayList<String> columns = new ArrayList<>();
				for (int i = 1; i <= columnCount2; i++) {
					Object obj = rs2.getObject(i);
					String value = (obj != null) ? obj.toString() : "NIL";
					columns.add(value);
				}
				if (columns.size() > 0) {
					columns.set(0, expectedTypes[typeIndex]);
				}
				list.add(columns);
				typeIndex++;
			}

			while (list.size() < 3) {
				ArrayList<String> nilColumns = new ArrayList<>();
				for (int i = 1; i <= columnCount2; i++) {
					nilColumns.add("NIL");
				}
				if (list.size() == 1) {
					nilColumns.set(0, "B");
				} else if (list.size() == 2) {
					nilColumns.set(0, "C");
				}

				list.add(nilColumns);
			}

			rs2.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.err.println("Error closing connection: " + e.getMessage());
				}
			}
		}

		return list;
	}

	@Override
	public ArrayList<ArrayList<String>> getAnimal_Data(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String roleSusNo = session.getAttribute("roleSusNo").toString();
			if (!roleSusNo.equals("")) {
				qry = "where sus_no in (?)";
			}

			q = "WITH\n" + "  AnimalCounts AS (\n" + "      SELECT\n" + "          anml_type,\n"
					+ "          COUNT(specialization) AS uh\n" + "      FROM\n"
					+ "          tb_tms_animal_details_tbl " + qry + "\n" + "      GROUP BY\n" + "          anml_type\n"
					+ "  ),\n" + "  DogUE AS (\n" + "      SELECT\n" + "          COALESCE(\n" + "              SUM(\n"
					+ "                  CASE\n" + "                      WHEN ue_of_dogs = '' THEN NULL\n"
					+ "                      ELSE ue_of_dogs\n" + "                  END :: INTEGER\n"
					+ "              ),\n" + "              0\n" + "          ) AS dog_ue\n" + "      FROM\n"
					+ "          tb_tms_animals_ue_master " + qry + "\n" + "  ),\n" + "  EquineUE AS (\n"
					+ "      SELECT\n" + "          COALESCE(\n" + "              SUM(\n" + "                  CASE\n"
					+ "                      WHEN ue_of_equins = '' THEN NULL\n"
					+ "                      ELSE ue_of_equins\n" + "                  END :: INTEGER\n"
					+ "              ),\n" + "              0\n" + "          ) AS equine_ue\n" + "      FROM\n"
					+ "          tb_tms_animals_ue_master " + qry + "\n" + "  ), AnimalInspections AS (\n"
					+ "    SELECT\n" + "        type,\n" + "        conditon,\n" + "        teatment,\n"
					+ "        grooming,\n" + "        feeding,\n" + "        watering,\n" + "        clipping,\n"
					+ "        f_feet,\n" + "        saddlery,\n" + "        line_gear,\n" + "        accomodation,\n"
					+ "        remarks\n" + "    FROM\n" + "        tb_miso_insp_animal\n" + "    WHERE\n"
					+ "        sus_no =?\n" + "        AND status = '0'\n" + "        AND year =?\n" + ")\n"
					+ "SELECT\n" + "  ac.anml_type,\n" + "  CASE\n"
					+ "      WHEN ac.anml_type = 'Army Dog' THEN du.dog_ue\n"
					+ "      WHEN ac.anml_type = 'Army Equines' THEN eu.equine_ue\n" + "      ELSE 0\n"
					+ "  END AS ue,\n" + "  ac.uh,\n" + "  CASE\n"
					+ "      WHEN ac.anml_type = 'Army Dog' and du.dog_ue < ac.uh THEN ac.uh - du.dog_ue\n"
					+ "      WHEN ac.anml_type = 'Army Equines' and eu.equine_ue < ac.uh THEN ac.uh - eu.equine_ue\n"
					+ "      ELSE 0\n" + "  END AS sur,\n" + "  CASE\n"
					+ "      WHEN ac.anml_type = 'Army Dog' and du.dog_ue > ac.uh THEN du.dog_ue - ac.uh\n"
					+ "      WHEN ac.anml_type = 'Army Equines' and eu.equine_ue > ac.uh THEN eu.equine_ue - ac.uh\n"
					+ "      ELSE 0\n" + "  END AS defi,\n" + "  ai.conditon,\n" + "  ai.teatment,\n"
					+ "  ai.grooming,\n" + "  ai.feeding,\n" + "  ai.watering,\n" + "  ai.clipping,\n"
					+ "  ai.f_feet,\n" + "  ai.saddlery,\n" + "  ai.line_gear,\n" + "  ai.accomodation,\n"
					+ "  ai.remarks\n" + "FROM\n" + "  AnimalCounts AS ac\n" + "  CROSS JOIN DogUE AS du\n"
					+ "  CROSS JOIN EquineUE AS eu\n" + "  LEFT JOIN AnimalInspections AS ai ON ac.anml_type = ai.type "
					+ "  WHERE ac.anml_type IN ('Army Dog', 'Army Equines')";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setString(2, roleSusNo.toUpperCase());
			stmt.setString(3, roleSusNo.toUpperCase());
			stmt.setString(4, roleSusNo.toUpperCase());
			stmt.setString(5, currenYear);
System.out.println("------------"+stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> nilColumns = new ArrayList<>();
				for (int i = 1; i <= columnCount; i++) {
					nilColumns.add("NIL");
				}
				list.add(nilColumns);
			} else {
				while (rs.next()) {
					ArrayList<String> columns = new ArrayList<>();
					for (int i = 1; i <= columnCount; i++) {
						Object obj = rs.getObject(i);
						columns.add(obj != null ? obj.toString() : "NIL");
					}
					list.add(columns);
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

	@Override
	public ArrayList<ArrayList<String>> getDeficiencies_of_Equipment2_Data(HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "WITH HeldCount AS (\n" + "    SELECT \n"
					+ "        COUNT(p.id) FILTER (WHERE p.type_of_comm_granted <> '20') AS Total,p.unit_sus_no\n"
					+ "    FROM tb_psg_trans_proposed_comm_letter p\n"
					+ "    LEFT JOIN orbat_all_details_view v ON v.sus_no = p.unit_sus_no \n"
					+ "    WHERE p.status IN ('1', '5') \n" + "      AND p.type_of_comm_granted <> '20'\n"
					+ "      AND v.status_sus_no = 'Active'\n"
					+ "      AND p.id NOT IN (SELECT comm_id FROM tb_psg_re_employment WHERE status = 1 AND re_emp_select = '2')\n"
					+ "      AND p.id NOT IN (SELECT comm_id FROM tb_psg_census_secondment WHERE seconded_to IN ('0', '1', '2', '3', '4', '5', '6'))  group by p.unit_sus_no\n"
					+ "),\n" + "AuthSum AS (\n" + "    SELECT \n"
					+ "        SUM(a.base_auth + a.mod_auth + a.foot_auth) AS auth,a.sus_no as unit_sus_no\n"
					+ "    FROM sus_pers_stdauth a \n"
					+ "    LEFT JOIN orbat_all_details_view b ON b.sus_no = a.sus_no \n"
					+ "    WHERE b.status_sus_no = 'Active' \n" + "      AND a.rank_cat = '0'  group by a.sus_no\n"
					+ ")\n" + "\n" + "SELECT 'Officiers' AS personnel, \n" + "       h.Total AS HELD,\n"
					+ "       a.auth AS auth,\n" + "       CASE \n"
					+ "           WHEN a.auth > h.Total THEN a.auth - h.Total\n" + "           ELSE 0 \n"
					+ "       END AS Surplus,\n" + "       CASE \n"
					+ "           WHEN a.auth < h.Total THEN h.Total - a.auth\n" + "           ELSE 0 \n"
					+ "       END AS Deficiency\n"
					+ "FROM HeldCount h, AuthSum a   where h.unit_sus_no=?    and a.unit_sus_no=?  ";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo);
			stmt.setString(2, roleSusNo);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getDeficiencies_of_Equipment_url_Data(HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String roleSusNo = session.getAttribute("roleSusNo").toString();
			if (!roleSusNo.equals("")) {
				qry = "and u.sus_no in (?)";
			}

			q = "WITH\n" + "  CombinedData AS (\n" + "    SELECT\n" + "      p.prf_code,\n" + "      p.item_code,\n"
					+ "      'A0' AS type_of_hldg,\n" + "      p.type_of_eqpt,\n" + "      SUM(ueqty) AS totue,\n"
					+ "      SUM(uhqty) AS totuh,\n" + "      MAX(upd_date) AS upd_date\n" + "    FROM (\n"
					+ "      SELECT\n" + "        b.prf_code,\n" + "        b.item_code,\n" + "        a.census_no,\n"
					+ "        a.type_of_eqpt,\n" + "        0 AS ueqty,\n" + "        a.tothol AS uhqty,\n"
					+ "        a.data_app_date AS upd_date\n" + "      FROM (\n" + "        SELECT\n"
					+ "          a.census_no,\n" + "          a.type_of_eqpt,\n" + "          1 AS tothol,\n"
					+ "          data_app_date\n" + "        FROM mms_tb_regn_mstr_detl a\n"
					+ "        LEFT JOIN tb_miso_orbat_unt_dtl u ON a.sus_no::text = u.sus_no::text\n"
					+ "          AND u.status_sus_no::text = 'Active'::text\n" + "        WHERE\n"
					+ "          a.op_status = '1'\n" + "          AND a.type_of_hldg = 'A0'\n"
					+ "          AND a.sus_no IN (?)\n" + "        UNION ALL\n" + "        SELECT\n"
					+ "          a.census_no,\n" + "          a.type_of_eqpt,\n" + "          1 AS tothol,\n"
					+ "          data_app_date\n" + "        FROM mms_tb_depot_regn_mstr_detl a\n"
					+ "        LEFT JOIN tb_miso_orbat_unt_dtl u ON a.sus_no::text = u.sus_no::text\n"
					+ "          AND u.status_sus_no::text = 'Active'::text\n" + "        WHERE\n"
					+ "          a.op_status = '1'\n" + "          AND a.type_of_hldg = 'A0'\n"
					+ "          AND a.sus_no IN (?)\n" + "        UNION ALL\n" + "        SELECT\n"
					+ "          a.census_no,\n" + "          a.type_of_eqpt,\n" + "          1 AS uh,\n"
					+ "          data_app_date\n" + "        FROM mms_tb_regn_oth_mstr a\n"
					+ "        LEFT JOIN tb_miso_orbat_unt_dtl u ON a.to_sus_no::text = u.sus_no::text\n"
					+ "          AND u.status_sus_no::text = 'Active'::text\n" + "        WHERE\n"
					+ "          a.op_status = '1'\n" + "          AND a.type_of_hldg = 'A0'\n"
					+ "          AND a.to_sus_no IN (?)\n" + "      ) AS a\n"
					+ "      INNER JOIN mms_tb_mlccs_mstr_detl b ON a.census_no = b.census_no\n" + "      UNION ALL\n"
					+ "      SELECT\n" + "        prf_group_code AS prf_code,\n" + "        item_code,\n"
					+ "        '' AS census_no,\n" + "        (\n" + "          CASE\n"
					+ "            WHEN UPPER(type) = 'CES' THEN '2'\n" + "            ELSE '1'\n" + "          END\n"
					+ "        ) AS type_of_eqpt,\n" + "        total_ue_qty AS ueqty,\n" + "        0 AS uhqty,\n"
					+ "        NULL AS upd_date\n" + "      FROM mms_ue_mview WHERE sus_no IN (?)\n" + "    ) AS p\n" + "    GROUP BY\n"
					+ "      p.prf_code,\n" + "      p.item_code,\n" + "      p.type_of_eqpt\n" + "  ),\n"
					+ "  InspectionData AS (\n" + "    SELECT\n" + "      nomenclature,\n" + "      au,\n"
					+ "      offroad_reason,\n" + "      action_taken_unit,\n" + "      remarks\n"
					+ "    FROM tb_miso_insp_dtl_of_equp_offroad\n" + "    WHERE\n" + "      sus_no = ?\n"
					+ "      AND year = ?\n" + "      AND status = '0'\n" + "  )\n" + "SELECT\n"
					+ "  i.item_type AS item_nomen,\n" + "  t1.label AS type_of_hldg_n,\n"
					+ "  t2.label AS type_of_eqpt_n,\n" + "  SUM(r.totue) AS total_totue,\n"
					+ "  SUM(r.totuh) AS total_totuh,\n" + "  COALESCE(SUM(r.totue) - SUM(r.totuh), 0) AS defi,\n"
					+ "  MAX(id.offroad_reason) AS offroad_reason,\n"
					+ "  MAX(id.action_taken_unit) AS action_taken_unit,\n" + "  MAX(id.remarks) AS remarks\n"
					+ "FROM\n" + "  CombinedData AS r\n"
					+ "  LEFT JOIN cue_tb_miso_mms_item_mstr i ON r.item_code = i.item_code\n"
					+ "  LEFT JOIN cue_tb_miso_prf_group_mst pr ON r.prf_code = pr.prf_group_code\n"
					+ "  LEFT JOIN mms_domain_values t1 ON r.type_of_hldg = t1.codevalue\n"
					+ "  AND t1.domainid = 'TYPEOFHOLDING'\n"
					+ "  LEFT JOIN t_domain_value t2 ON i.acc_units = t2.codevalue\n"
					+ "  AND t2.domainid = 'ACCOUNTINGUNITS'\n"
					+ "  LEFT JOIN InspectionData AS id ON i.item_type = id.nomenclature AND t2.label = id.au\n"
					+ "GROUP BY\n" + "i.prf_group,  i.item_type,\n" + "  t1.label,\n" + "  t2.label\n" + "ORDER BY\n"
					+ " i.prf_group, i.item_type,\n" + "  t1.label,\n" + "  t2.label";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setString(2, roleSusNo.toUpperCase());
			stmt.setString(3, roleSusNo.toUpperCase());
			stmt.setString(4, roleSusNo.toUpperCase());
			stmt.setString(5, roleSusNo.toUpperCase());
			stmt.setString(6, currenYear);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			System.out.println("637 ---------------------------" + stmt);

			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if (rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					if (rs.getObject(i) == null) {
						columns.add("");
					}
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
	/////////// -------------------------------------------

	@Override
	public ArrayList<ArrayList<String>> getWTResultData(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";
			String currenYear = yearFormat.format(new Date());
			String roleSusNo = session.getAttribute("roleSusNo").toString();

			sql = "  SELECT\n" + "           'Officers' AS category,\n" + "           COUNT(*) AS total,\n"
					+ "           COUNT(CASE WHEN firing_grade = '1' THEN 1 END) AS HPS,\n"
					+ "           COUNT(CASE WHEN firing_grade = '2' THEN 1 END) AS Marksman,\n"
					+ "           COUNT(CASE WHEN firing_grade = '3' THEN 1 END) AS firstclass,\n"
					+ "           COUNT(CASE WHEN firing_grade = '4' THEN 1 END) AS std_shot,\n"
					+ "           COUNT(CASE WHEN firing_grade = '5' THEN 1 END) AS others,\n"
					+ "           COUNT(CASE WHEN firing_grade = '11' THEN 1 END) AS dna\n" + "       FROM\n"
					+ "           tb_psg_census_firing_standard d\n"
					+ "       inner join tb_psg_trans_proposed_comm_letter a on a.id=d.comm_id and a.status='1'\n"
					+ "       where d.status ='1'\n" + "         and FIRING_UNIT_SUS_NO in (?)\n" + "   union all\n"
					+ "       SELECT\n" + "           ' JCOs' AS category,\n" + "           COUNT(*) AS total,\n"
					+ "           COUNT(CASE WHEN firing_grade = '1' THEN 1 END) AS HPS,\n"
					+ "           COUNT(CASE WHEN firing_grade = '2' THEN 1 END) AS Marksman, \n"
					+ "           COUNT(CASE WHEN firing_grade = '3' THEN 1 END) AS firstclass,\n"
					+ "           COUNT(CASE WHEN firing_grade = '4' THEN 1 END) AS std_shot,\n"
					+ "           COUNT(CASE WHEN firing_grade = '5' THEN 1 END) AS others,\n"
					+ "           COUNT(CASE WHEN firing_grade = '11' THEN 1 END) AS dna\n" + "       FROM\n"
					+ "           tb_psg_census_firing_standard_jco d\n"
					+ "       inner join tb_psg_census_jco_or_p a on a.id=d.jco_id and a.status='1'\n"
					+ "       WHERE d.status ='1' and a.category = 'JCO'\n"
					+ "       and   FIRING_UNIT_SUS_NO in (?) \n" + "\n" + "\n" + "			   union all\n"
					+ "       SELECT\n" + "           'OR' AS category,\n" + "           COUNT(*) AS total,\n"
					+ "           COUNT(CASE WHEN firing_grade = '1' THEN 1 END) AS HPS,\n"
					+ "           COUNT(CASE WHEN firing_grade = '2' THEN 1 END) AS Marksman, \n"
					+ "           COUNT(CASE WHEN firing_grade = '3' THEN 1 END) AS firstclass,\n"
					+ "           COUNT(CASE WHEN firing_grade = '4' THEN 1 END) AS std_shot,\n"
					+ "           COUNT(CASE WHEN firing_grade = '5' THEN 1 END) AS others,\n"
					+ "           COUNT(CASE WHEN firing_grade = '11' THEN 1 END) AS dna\n" + "       FROM\n"
					+ "           tb_psg_census_firing_standard_jco d\n"
					+ "       inner join tb_psg_census_jco_or_p a on a.id=d.jco_id and a.status='1'\n"
					+ "       WHERE d.status ='1' and a.category = 'OR'\n" + "       and   FIRING_UNIT_SUS_NO in (?) ";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, roleSusNo);
			stmt.setString(2, roleSusNo);
			stmt.setString(3, roleSusNo);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getBPETResultData(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";
			String currenYear = yearFormat.format(new Date());
			String roleSusNo = session.getAttribute("roleSusNo").toString();

			sql = "select a.name,a.total,a.excellent,a.good,a.satisfactory,a.poor,a.fail,re.number_yet_to_tested,re.remarks from ( SELECT  'Officers' as name,\n"
					+ "	COUNT(*) AS total,\n" + "	COUNT(CASE WHEN bpet_result = '2' THEN 1 END) AS excellent,\n"
					+ "	COUNT(CASE WHEN bpet_result = '4' THEN 1 END) AS good,\n"
					+ "	COUNT(CASE WHEN bpet_result = '7' THEN 1 END) AS satisfactory,\n"
					+ "	COUNT(CASE WHEN bpet_result = '6' THEN 1 END) AS poor,\n"
					+ "	COUNT(CASE WHEN bpet_result = '3' THEN 1 END) AS fail,\n"
					+ "    COUNT(CASE WHEN bpet_result = '1' THEN 1 END) AS dna_did_not_appear,\n"
					+ "    COUNT(CASE WHEN bpet_result = '5' THEN 1 END) AS others\n" + "FROM\n"
					+ "    tb_psg_census_bpet where unit_sus_no in (?)\n" + "\n" + "\n" + "union all\n" + "\n" + "\n"
					+ "		SELECT 'JCOs' as name,\n" + "	COUNT(*) AS total,\n"
					+ "	COUNT(CASE WHEN bpet_result = '2' THEN 1 END) AS excellent,\n"
					+ "	COUNT(CASE WHEN bpet_result = '4' THEN 1 END) AS good,\n"
					+ "	COUNT(CASE WHEN bpet_result = '7' THEN 1 END) AS satisfactory,\n"
					+ "	COUNT(CASE WHEN bpet_result = '6' THEN 1 END) AS poor,\n"
					+ "	COUNT(CASE WHEN bpet_result = '3' THEN 1 END) AS fail,\n"
					+ "    COUNT(CASE WHEN bpet_result = '1' THEN 1 END) AS dna_did_not_appear,\n"
					+ "    COUNT(CASE WHEN bpet_result = '5' THEN 1 END) AS others\n" + "FROM\n"
					+ "    tb_psg_census_bpet_jco jo\n" + "   inner join tb_psg_census_jco_or_p m on jo.jco_id = m.id\n"
					+ "where category = 'JCO' and jo.unit_sus_no in (?)\n" + "\n" + "union all\n" + "\n"
					+ "		SELECT 'OR' as name,\n" + "COUNT(*) AS total,\n"
					+ "COUNT(CASE WHEN bpet_result = '2' THEN 1 END) AS excellent,\n"
					+ "COUNT(CASE WHEN bpet_result = '4' THEN 1 END) AS good,\n"
					+ "COUNT(CASE WHEN bpet_result = '7' THEN 1 END) AS satisfactory,\n"
					+ "COUNT(CASE WHEN bpet_result = '6' THEN 1 END) AS poor,\n"
					+ "COUNT(CASE WHEN bpet_result = '3' THEN 1 END) AS fail,\n"
					+ "COUNT(CASE WHEN bpet_result = '1' THEN 1 END) AS dna_did_not_appear,\n"
					+ "COUNT(CASE WHEN bpet_result = '5' THEN 1 END) AS others\n" + "FROM\n"
					+ "tb_psg_census_bpet_jco jo\n" + "inner join tb_psg_census_jco_or_p m on jo.jco_id = m.id\n"
					+ "where category = 'OR' and jo.unit_sus_no in (?) ) a\n"
					+ "left join  tb_miso_insp_bpet_result re\n" + "on re.personnel = a.name\n" + "and re.sus_no=? \n"
					+ "and re.year = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, roleSusNo.toUpperCase());
			stmt.setString(2, roleSusNo.toUpperCase());
			stmt.setString(3, roleSusNo.toUpperCase());
			stmt.setString(4, roleSusNo.toUpperCase());
			stmt.setString(5, currenYear);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					Object obj = rs.getObject(i);
					columns.add(obj != null ? obj.toString() : "");
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
	public ArrayList<ArrayList<String>> State_of_Sector_Stores_url_Data(HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT DISTINCT\n" + "	I.ITEM_TYPE AS ITEM_NOMEN,\n" + "	Z.TOTUE,\n" + "	Z.TOTUH,\n"
					+ "	COALESCE(SUM(Z.TOTUE) - SUM(Z.TOTUH), 0) AS DEFI\n" + "FROM\n" + "	(\n" + "		SELECT\n"
					+ "			(\n" + "				CASE\n"
					+ "					WHEN P.SUS_NO IS NULL THEN Q.SUS_NO\n" + "					ELSE P.SUS_NO\n"
					+ "				END\n" + "			) AS SUS_NO,\n" + "			(\n" + "				CASE\n"
					+ "					WHEN P.PRF_CODE IS NULL THEN Q.PRF_CODE\n"
					+ "					ELSE P.PRF_CODE\n" + "				END\n" + "			) AS PRF_CODE,\n"
					+ "			(\n" + "				CASE\n"
					+ "					WHEN P.ITEM_CODE IS NULL THEN Q.ITEM_CODE\n"
					+ "					ELSE P.ITEM_CODE\n" + "				END\n" + "			) AS ITEM_CODE,\n"
					+ "			(\n" + "				CASE\n"
					+ "					WHEN P.TYPE_OF_EQPT IS NULL THEN Q.TYPE_OF_EQPT\n"
					+ "					ELSE P.TYPE_OF_EQPT\n" + "				END\n"
					+ "			) AS TYPE_OF_EQPT,\n" + "			P.CENSUS_NO,\n"
					+ "			COALESCE(Q.TOTUE, 0) AS TOTUE,\n" + "			COALESCE(P.UH, 0) AS TOTUH,\n"
					+ "			COALESCE(P.SS, 0) AS TOTSS,\n" + "			COALESCE(P.LS, 0) AS TOTLS,\n"
					+ "			COALESCE(P.AC, 0) AS TOTAC,\n" + "			COALESCE(P.UN, 0) AS TOTUN,\n"
					+ "			COALESCE(P.UWWR, 0) AS TOTUWWR,\n" + "			COALESCE(P.TRSS, 0) AS TOTTRSS,\n"
					+ "			COALESCE(P.ETSR, 0) AS TOTETSR,\n" + "			COALESCE(P.OTHR, 0) AS TOTOTHR\n"
					+ "		FROM\n" + "			(\n" + "				SELECT\n" + "					P.SUS_NO,\n"
					+ "					M.PRF_CODE,\n" + "					M.ITEM_CODE,\n"
					+ "					P.CENSUS_NO,\n" + "					M.NOMEN,\n"
					+ "					P.TYPE_OF_EQPT,\n" + "					P.UH,\n" + "					P.SS,\n"
					+ "					P.LS,\n" + "					P.AC,\n" + "					P.UN,\n"
					+ "					P.UWWR,\n" + "					P.TRSS,\n" + "					P.ETSR,\n"
					+ "					P.OTHR\n" + "				FROM\n" + "					(\n"
					+ "						SELECT\n" + "							B.SUS_NO,\n"
					+ "							B.CENSUS_NO,\n" + "							B.TYPE_OF_EQPT,\n"
					+ "							SUM(B.UH) AS UH,\n" + "							SUM(B.SS) AS SS,\n"
					+ "							SUM(B.LS) AS LS,\n" + "							SUM(B.AC) AS AC,\n"
					+ "							SUM(B.UN) AS UN,\n" + "							SUM(B.UWWR) AS UWWR,\n"
					+ "							SUM(B.TRSS) AS TRSS,\n"
					+ "							SUM(B.ETSR) AS ETSR,\n"
					+ "							SUM(B.OTHR) AS OTHR\n" + "						FROM\n"
					+ "							(\n" + "								SELECT\n"
					+ "									A.CENSUS_NO,\n" + "									A.SUS_NO,\n"
					+ "									A.TYPE_OF_EQPT,\n" + "									(\n"
					+ "										CASE\n"
					+ "											WHEN A.TYPE_OF_HLDG = 'A0' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS UH,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'A5' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS SS,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'A14' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS LS,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'A16' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS AC,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'A17' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS UN,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'R0' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS UWWR,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'R4' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS TRSS,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'R3' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS ETSR,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'R5' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS OTHR\n"
					+ "								FROM\n"
					+ "									MMS_TB_REGN_MSTR_DETL A\n"
					+ "									LEFT JOIN TB_MISO_ORBAT_UNT_DTL U ON A.SUS_NO::TEXT = U.SUS_NO::TEXT\n"
					+ "									AND U.STATUS_SUS_NO::TEXT = 'Active'::TEXT\n"
					+ "								WHERE\n" + "									A.OP_STATUS = '1'\n"
					+ "								UNION ALL\n" + "								SELECT\n"
					+ "									A.CENSUS_NO,\n" + "									A.SUS_NO,\n"
					+ "									A.TYPE_OF_EQPT,\n" + "									(\n"
					+ "										CASE\n"
					+ "											WHEN A.TYPE_OF_HLDG = 'A0' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS UH,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'A5' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS SS,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'A14' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS LS,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'A16' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS AC,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'A17' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS UN,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'R0' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS UWWR,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'R4' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS TRSS,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'R3' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS ETSR,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'R5' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS OTHR\n"
					+ "								FROM\n"
					+ "									MMS_TB_DEPOT_REGN_MSTR_DETL A\n"
					+ "									LEFT JOIN TB_MISO_ORBAT_UNT_DTL U ON A.SUS_NO::TEXT = U.SUS_NO::TEXT\n"
					+ "									AND U.STATUS_SUS_NO::TEXT = 'Active'::TEXT\n"
					+ "								WHERE\n" + "									A.OP_STATUS = '1'\n"
					+ "								UNION ALL\n" + "								SELECT\n"
					+ "									A.CENSUS_NO,\n"
					+ "									A.TO_SUS_NO,\n"
					+ "									A.TYPE_OF_EQPT,\n" + "									(\n"
					+ "										CASE\n"
					+ "											WHEN A.TYPE_OF_HLDG = 'A0' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS UH,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'A5' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS SS,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'A14' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS LS,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'A16' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS AC,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'A17' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS UN,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'R0' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS UWWR,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'R4' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS TRSS,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'R3' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS ETSR,\n"
					+ "									(\n"
					+ "										CASE A.TYPE_OF_HLDG\n"
					+ "											WHEN 'R5' THEN 1\n"
					+ "											ELSE 0\n"
					+ "										END\n" + "									) AS OTHR\n"
					+ "								FROM\n"
					+ "									MMS_TB_REGN_OTH_MSTR A\n"
					+ "									LEFT JOIN TB_MISO_ORBAT_UNT_DTL U ON A.TO_SUS_NO::TEXT = U.SUS_NO::TEXT\n"
					+ "									AND U.STATUS_SUS_NO::TEXT = 'Active'::TEXT\n"
					+ "								WHERE\n" + "									A.OP_STATUS = '1'\n"
					+ "							) AS B\n" + "						GROUP BY\n"
					+ "							1,\n" + "							2,\n"
					+ "							3\n" + "					) P\n"
					+ "					LEFT JOIN MMS_TB_MLCCS_MSTR_DETL M ON P.CENSUS_NO = M.CENSUS_NO\n"
					+ "			) P\n" + "			FULL OUTER JOIN (\n" + "				SELECT\n"
					+ "					P1.SUS_NO,\n" + "					P1.PRF_CODE,\n"
					+ "					P1.ITEM_CODE,\n" + "					'' AS CENSUS_NO,\n"
					+ "					'' AS NOMEN,\n" + "					P1.WE_PE AS TYPE_OF_EQPT,\n"
					+ "					SUM(P1.TOTUE) AS TOTUE\n" + "				FROM\n" + "					(\n"
					+ "						SELECT\n" + "							SUS_NO,\n"
					+ "							PRF_GROUP_CODE AS PRF_CODE,\n"
					+ "							ITEM_CODE,\n" + "							(\n"
					+ "								CASE\n"
					+ "									WHEN UPPER(TYPE) = 'CES' THEN '2'\n"
					+ "									ELSE '1'\n" + "								END\n"
					+ "							) AS WE_PE,\n" + "							TOTAL_UE_QTY AS TOTUE\n"
					+ "						FROM\n" + "							MMS_UE_MVIEW\n"
					+ "						WHERE\n" + "							SUS_NO = '0418045W'\n"
					+ "					) P1\n" + "				GROUP BY\n" + "					P1.SUS_NO,\n"
					+ "					P1.PRF_CODE,\n" + "					P1.ITEM_CODE,\n"
					+ "					P1.WE_PE\n" + "			) Q ON P.SUS_NO = Q.SUS_NO\n"
					+ "			AND P.PRF_CODE = Q.PRF_CODE\n" + "			AND P.ITEM_CODE = Q.ITEM_CODE\n"
					+ "		WHERE\n" + "			NOT (\n" + "				Q.TOTUE = 0\n"
					+ "				AND P.UH = 0\n" + "				AND P.SS = 0\n" + "				AND P.LS = 0\n"
					+ "				AND P.AC = 0\n" + "				AND P.UN = 0\n" + "				AND P.UWWR = 0\n"
					+ "				AND P.TRSS = 0\n" + "				AND P.ETSR = 0\n"
					+ "				AND P.OTHR = 0\n" + "			)\n" + "	) Z\n"
					+ "	LEFT JOIN CUE_TB_MISO_PRF_GROUP_MST F ON Z.PRF_CODE = F.PRF_GROUP_CODE\n"
					+ "	LEFT JOIN MMS_TB_MLCCS_MSTR_DETL M ON Z.CENSUS_NO = M.CENSUS_NO\n"
					+ "	LEFT JOIN CUE_TB_MISO_MMS_ITEM_MSTR I ON Z.ITEM_CODE = I.ITEM_CODE\n" + "GROUP BY\n"
					+ "	I.ITEM_TYPE,\n" + "	Z.TOTUE,\n" + "	Z.TOTUH\n" + "ORDER BY\n" + "	I.ITEM_TYPE ";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getCategory_url_Data(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			String roleSusNo = session.getAttribute("roleSusNo").toString();

			sql = "SELECT\n" + "COALESCE(\n"
					+ "OFFICER_COURSES.COURSE_NAME, JCO_COURSES.COURSE_NAME, OR_COURSES.COURSE_NAME, OFFICER_TWO_COURSES.COURSE_NAME\n"
					+ ") AS COURSE_NAME,\n" + "COALESCE(OFFICER_COURSES.OFFICER_USER_COUNT, 0) AS OFFICER_USER_COUNT,\n"
					+ "COALESCE(JCO_COURSES.JCO_USER_COUNT, 0) AS JCO_USER_COUNT,\n"
					+ "COALESCE(OR_COURSES.OR_USER_COUNT, 0) AS OR_USER_COUNT,\n"
					+ "COALESCE(OFFICER_TWO_COURSES.OFFICER_TWO_USER_COUNT, 0) AS OFFICER_TWO_USER_COUNT,\n"
					+ "COALESCE(JCO_TWO_COURSES.JCO_TWO_USER_COUNT, 0) AS JCO_TWO_USER_COUNT,\n"
					+ "COALESCE(OR_TWO_COURSES.OR_TWO_USER_COUNT, 0) AS OR_TWO_USER_COUNT,\n"
					+ "COALESCE(OFFICER_TOTAL_COURSES.OFFICER_TOTAL_USER_COUNT, 0) AS OFFICER_TOTAL_USER_COUNT,\n"
					+ "COALESCE(JCO_TOTAL_COURSES.JCO_TOTAL_USER_COUNT, 0) AS JCO_TOTAL_USER_COUNT,\n"
					+ "COALESCE(OR_TOTAL_COURSES.OR_TOTAL_USER_COUNT, 0) AS OR_TOTAL_USER_COUNT\n" + "FROM\n" + "(\n"
					+ "SELECT\n" + "COURSE_NAME,\n" + "COUNT(*) AS OFFICER_USER_COUNT\n" + "FROM\n"
					+ "TB_PSG_CENSUS_ARMY_COURSE D\n"
					+ "INNER JOIN TB_PSG_TRANS_PROPOSED_COMM_LETTER A ON A.ID=D.COMM_ID WHERE A.STATUS='1' AND A.UNIT_SUS_NO= ?\n"
					+ "GROUP BY\n" + "COURSE_NAME\n" + ") AS OFFICER_COURSES\n" + "FULL OUTER JOIN (\n" + "SELECT\n"
					+ "B.COURSE_NAME,\n" + "COUNT(B.*) AS JCO_USER_COUNT\n" + "FROM\n"
					+ "TB_PSG_CENSUS_ARMY_COURSE_JCO B\n" + "INNER JOIN TB_PSG_CENSUS_JCO_OR_P A ON B.JCO_ID = A.ID\n"
					+ "WHERE\n" + "A.CATEGORY = 'JCO' and a.unit_sus_no in ('0418046Y')\n" + "GROUP BY\n"
					+ "B.COURSE_NAME\n" + ") AS JCO_COURSES ON OFFICER_COURSES.COURSE_NAME = JCO_COURSES.COURSE_NAME\n"
					+ "FULL OUTER JOIN (\n" + "SELECT\n" + "B.COURSE_NAME,\n" + "COUNT(B.*) AS OR_USER_COUNT\n"
					+ "FROM\n" + "TB_PSG_CENSUS_ARMY_COURSE_JCO B\n"
					+ "INNER JOIN TB_PSG_CENSUS_JCO_OR_P A ON B.JCO_ID = A.ID\n" + "WHERE \n"
					+ "A.CATEGORY = 'OR' and a.unit_sus_no in (?)\n" + "GROUP BY\n" + "B.COURSE_NAME\n"
					+ ") AS OR_COURSES ON COALESCE(\n" + "OFFICER_COURSES.COURSE_NAME,\n" + "JCO_COURSES.COURSE_NAME\n"
					+ ") = OR_COURSES.COURSE_NAME\n" + "FULL OUTER JOIN (\n" + "SELECT\n" + "COURSE_NAME,\n"
					+ "COUNT(*) AS OFFICER_TWO_USER_COUNT\n" + "FROM\n" + "TB_PSG_CENSUS_ARMY_COURSE D\n"
					+ "INNER JOIN TB_PSG_TRANS_PROPOSED_COMM_LETTER A ON A.ID=D.COMM_ID WHERE A.STATUS='1' AND A.UNIT_SUS_NO= ?\n"
					+ "AND\n" + "START_DATE BETWEEN CURRENT_DATE - INTERVAL '2 years' AND CURRENT_DATE\n"
					+ "OR DATE_OF_COMPLETION BETWEEN CURRENT_DATE - INTERVAL '2 years' AND CURRENT_DATE\n"
					+ "GROUP BY\n" + "COURSE_NAME\n" + ") AS OFFICER_TWO_COURSES ON COALESCE(\n"
					+ "OFFICER_COURSES.COURSE_NAME,\n" + "JCO_COURSES.COURSE_NAME,\n" + "OR_COURSES.COURSE_NAME\n"
					+ ") = OFFICER_TWO_COURSES.COURSE_NAME\n" + "FULL OUTER JOIN (\n" + "SELECT\n" + "B.COURSE_NAME,\n"
					+ "COUNT(*) AS JCO_TWO_USER_COUNT\n" + "FROM\n" + "TB_PSG_CENSUS_ARMY_COURSE_JCO B\n"
					+ "INNER JOIN TB_PSG_CENSUS_JCO_OR_P A ON B.JCO_ID = A.ID\n" + "WHERE\n" + "(\n"
					+ "B.START_DATE BETWEEN CURRENT_DATE - INTERVAL '2 years' AND CURRENT_DATE\n"
					+ "OR B.DATE_OF_COMPLETION BETWEEN CURRENT_DATE - INTERVAL '2 years' AND CURRENT_DATE\n" + ")\n"
					+ "AND A.CATEGORY = 'JCO'  and a.unit_sus_no in ( ?)\n" + "GROUP BY\n" + "B.COURSE_NAME\n"
					+ ") AS JCO_TWO_COURSES ON COALESCE(\n" + "OFFICER_COURSES.COURSE_NAME,\n"
					+ "JCO_COURSES.COURSE_NAME,\n" + "OR_COURSES.COURSE_NAME,\n" + "OFFICER_TWO_COURSES.COURSE_NAME\n"
					+ ") = JCO_TWO_COURSES.COURSE_NAME\n" + "FULL OUTER JOIN (\n" + "SELECT\n" + "B.COURSE_NAME,\n"
					+ "COUNT(*) AS OR_TWO_USER_COUNT\n" + "FROM\n" + "TB_PSG_CENSUS_ARMY_COURSE_JCO B\n"
					+ "INNER JOIN TB_PSG_CENSUS_JCO_OR_P A ON B.JCO_ID = A.ID\n" + "WHERE\n" + "(\n"
					+ "B.START_DATE BETWEEN CURRENT_DATE - INTERVAL '2 years' AND CURRENT_DATE\n"
					+ "OR B.DATE_OF_COMPLETION BETWEEN CURRENT_DATE - INTERVAL '2 years' AND CURRENT_DATE\n" + ")\n"
					+ "AND A.CATEGORY = 'OR'and a.unit_sus_no in ( ?)\n" + "GROUP BY\n" + "B.COURSE_NAME\n"
					+ ") AS OR_TWO_COURSES ON COALESCE(\n" + "OFFICER_COURSES.COURSE_NAME,\n"
					+ "JCO_COURSES.COURSE_NAME,\n" + "OR_COURSES.COURSE_NAME,\n" + "OFFICER_TWO_COURSES.COURSE_NAME,\n"
					+ "JCO_TWO_COURSES.COURSE_NAME\n" + ") = OR_TWO_COURSES.COURSE_NAME\n" + "FULL OUTER JOIN (\n"
					+ "SELECT\n" + "COURSE_NAME,\n" + "COUNT(*) AS OFFICER_TOTAL_USER_COUNT\n" + "FROM\n"
					+ "TB_PSG_CENSUS_ARMY_COURSE D\n"
					+ "INNER JOIN TB_PSG_TRANS_PROPOSED_COMM_LETTER A ON A.ID=D.COMM_ID WHERE A.STATUS='1' AND A.UNIT_SUS_NO= ?\n"
					+ "GROUP BY\n" + "COURSE_NAME\n" + ") AS OFFICER_TOTAL_COURSES ON COALESCE(\n"
					+ "OFFICER_COURSES.COURSE_NAME,\n" + "JCO_COURSES.COURSE_NAME,\n" + "OR_COURSES.COURSE_NAME,\n"
					+ "OFFICER_TWO_COURSES.COURSE_NAME,\n" + "JCO_TWO_COURSES.COURSE_NAME\n"
					+ ") = OFFICER_TOTAL_COURSES.COURSE_NAME\n" + "FULL OUTER JOIN (\n" + "SELECT\n"
					+ "B.COURSE_NAME,\n" + "COUNT(*) AS JCO_TOTAL_USER_COUNT\n" + "FROM\n"
					+ "TB_PSG_CENSUS_ARMY_COURSE_JCO B\n" + "INNER JOIN TB_PSG_CENSUS_JCO_OR_P A ON B.JCO_ID = A.ID\n"
					+ "AND A.CATEGORY = 'JCO' and a.unit_sus_no in ( ?)\n" + "GROUP BY\n" + "B.COURSE_NAME\n"
					+ ") AS JCO_TOTAL_COURSES ON COALESCE(\n" + "OFFICER_COURSES.COURSE_NAME,\n"
					+ "JCO_COURSES.COURSE_NAME,\n" + "OR_COURSES.COURSE_NAME,\n" + "OFFICER_TWO_COURSES.COURSE_NAME,\n"
					+ "JCO_TWO_COURSES.COURSE_NAME\n" + ") = JCO_TOTAL_COURSES.COURSE_NAME\n" + "FULL OUTER JOIN (\n"
					+ "SELECT\n" + "B.COURSE_NAME,\n" + "COUNT(*) AS OR_TOTAL_USER_COUNT\n" + "FROM\n"
					+ "TB_PSG_CENSUS_ARMY_COURSE_JCO B\n" + "INNER JOIN TB_PSG_CENSUS_JCO_OR_P A ON B.JCO_ID = A.ID\n"
					+ "AND A.CATEGORY = 'OR' and a.unit_sus_no in ( ?)\n" + "GROUP BY\n" + "B.COURSE_NAME\n"
					+ ") AS OR_TOTAL_COURSES ON COALESCE(\n" + "OFFICER_COURSES.COURSE_NAME,\n"
					+ "JCO_COURSES.COURSE_NAME,\n" + "OR_COURSES.COURSE_NAME,\n" + "OFFICER_TWO_COURSES.COURSE_NAME,\n"
					+ "JCO_TWO_COURSES.COURSE_NAME,\n" + "OFFICER_TOTAL_COURSES.COURSE_NAME,\n"
					+ "JCO_TOTAL_COURSES.COURSE_NAME\n" + ") = OR_TOTAL_COURSES.COURSE_NAME";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, roleSusNo);
			stmt.setString(2, roleSusNo);
			stmt.setString(3, roleSusNo);
			stmt.setString(4, roleSusNo);
			stmt.setString(5, roleSusNo);
			stmt.setString(6, roleSusNo);
			stmt.setString(7, roleSusNo);
			stmt.setString(8, roleSusNo);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getCategory_url_Data_addmore(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			String roleSusNo = session.getAttribute("roleSusNo").toString();

			sql = "SELECT sus_no,course_name,officers_period,jcos_period,ncos_period,or_period,officers_preceding,"
					+ "jcos_preceding,ncos_preceding,or_preceding,totalofficers,totaljcos,"
					+ " totalncos,totalor FROM tb_miso_inspection_courses_cat_a_b where sus_no in (?) ";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, roleSusNo);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getUp_Gradation_Data_addmore(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			String roleSusNo = session.getAttribute("roleSusNo").toString();

			sql = "SELECT sus_no,trade,affected_up_gradation_class_iv,affected_up_gradation_class_iii,affected_up_gradation_class_ii,affected_up_gradation_class_i,during_up_gradation_class_iv,"
					+ "during_up_gradation_class_iii,during_up_gradation_class_ii,during_up_gradation_class_i,total_available_class_iv,total_available_class_iii,"
					+ " total_available_class_ii,total_available_class_i FROM tb_miso_inspection_upgradation where sus_no in (?) ";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, roleSusNo);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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

	

	/////// -

	

	@Override
	public ArrayList<ArrayList<String>> getUp_Gradation_Data(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = " select \n" + "affected_up_gradation_class_iv,\n" + "affected_up_gradation_class_iii,\n"
					+ "affected_up_gradation_class_ii,\n" + "affected_up_gradation_class_i,\n"
					+ "during_up_gradation_class_iv,\n" + "during_up_gradation_class_iii,\n"
					+ "during_up_gradation_class_ii,\n" + "during_up_gradation_class_i,\n"
					+ "total_available_class_iv,\n" + "total_available_class_iii,\n" + "total_available_class_ii,\n"
					+ "total_available_class_i\n" + "from tb_insp_up_gradation_report";

			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public List<TB_MISO_INSP_ESTABLISHMENT> getEstablishmentAttachedAction(String sus_no) {
		List<TB_MISO_INSP_ESTABLISHMENT> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "select\n" + "    atted_civ_auth ,\n" + "    atted_civ_defi ,\n" + "    atted_civ_medcat ,\n"
					+ "    atted_civ_posted ,\n" + "    atted_civ_sur ,\n" + "    atted_jco_auth ,\n"
					+ "    atted_jco_defi ,\n" + "    atted_jco_medcat ,\n" + "    atted_jco_posted ,\n"
					+ "    atted_jco_sur ,\n" + "    atted_offrs_auth ,atted_warrant_sur,\n"
					+ "    atted_offrs_defi ,\n" + "    atted_offrs_medcat ,\n" + "    atted_offrs_posted ,\n"
					+ "    atted_offrs_sur ,\n" + "    atted_or_auth ,\n" + "    atted_or_defi ,\n"
					+ "    atted_or_medcat ,\n" + "    atted_or_posted ,\n" + "    atted_or_sur ,\n"
					+ "    atted_warrant_auth ,		atted_warrant_posted,\n" + "    atted_warrant_defi ,\n"
					+ "    atted_warrant_medcat ,  \n" + "    reg_civ_medcat ,   \n" + "    reg_jco_medcat ,   \n"
					+ "    reg_offrs_medcat ,   \n" + "    reg_or_medcat ,   \n" + "    reg_warrant_medcat \n"
					+ "   from tb_miso_insp_establishment  where sus_no = ?   and year =?\n" + "   \n"
					+ "   " + "  ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_MISO_INSP_ESTABLISHMENT list = new TB_MISO_INSP_ESTABLISHMENT();
				list.setAtted_civ_auth(rs.getString("atted_civ_auth"));
				list.setAtted_jco_auth(rs.getString("atted_jco_auth"));
				list.setAtted_offrs_auth(rs.getString("atted_offrs_auth"));
				list.setAtted_or_auth(rs.getString("atted_or_auth"));
				;
				list.setAtted_warrant_auth(rs.getString("atted_warrant_auth"));

				list.setAtted_civ_medcat(rs.getString("atted_civ_medcat"));
				list.setAtted_jco_medcat(rs.getString("atted_jco_medcat"));
				list.setAtted_offrs_medcat(rs.getString("atted_offrs_medcat"));
				list.setAtted_or_medcat(rs.getString("atted_or_medcat"));
				list.setAtted_warrant_medcat(rs.getString("atted_warrant_medcat"));

				list.setAtted_civ_posted(rs.getString("atted_civ_posted"));
				list.setAtted_jco_posted(rs.getString("atted_jco_posted"));
				list.setAtted_offrs_posted(rs.getString("atted_offrs_posted"));
				list.setAtted_or_posted(rs.getString("atted_or_posted"));
				list.setAtted_warrant_posted(rs.getString("atted_warrant_posted"));

				list.setAtted_offrs_sur(rs.getString("atted_offrs_sur"));
				list.setAtted_jco_sur(rs.getString("atted_jco_sur"));
				list.setAtted_or_sur(rs.getString("atted_or_sur"));
				list.setAtted_warrant_sur(rs.getString("atted_warrant_sur"));
				list.setAtted_civ_sur(rs.getString("atted_civ_sur"));

				list.setAtted_offrs_defi(rs.getString("atted_offrs_defi"));
				list.setAtted_jco_defi(rs.getString("atted_jco_defi"));
				list.setAtted_or_defi(rs.getString("atted_or_defi"));
				list.setAtted_warrant_defi(rs.getString("atted_warrant_defi"));
				list.setAtted_civ_defi(rs.getString("atted_civ_defi"));

				list.setReg_civ_medcat(rs.getString("reg_civ_medcat"));
				list.setReg_jco_medcat(rs.getString("reg_jco_medcat"));
				list.setReg_or_medcat(rs.getString("reg_or_medcat"));
				list.setReg_offrs_medcat(rs.getString("reg_offrs_medcat"));
				list.setReg_warrant_medcat(rs.getString("reg_warrant_medcat"));

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
	public List<TB_MISO_INSP_DEFI_EQUP_EFFI> getInspectionDefiEqpEffiData(String sus_no) {
		List<TB_MISO_INSP_DEFI_EQUP_EFFI> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "select\n" + "	id,a,\n" + "	nomenclature,\n" + "	auth,\n" + "	held,\n" + "	defi,\n"
					+ "	dues_in,\n" + "	dues_out,\n" + "	remarks\n" + "from\n" + "	tb_miso_insp_defi_equp_effi "
					+ " where sus_no=?  and year=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_MISO_INSP_DEFI_EQUP_EFFI list = new TB_MISO_INSP_DEFI_EQUP_EFFI();
				list.setNomenclature(rs.getString("nomenclature"));
				list.setA(rs.getString("a"));
				list.setAuth(rs.getString("auth"));
				list.setHeld(rs.getString("held"));
				list.setDefi(rs.getString("defi"));
				list.setDues_in(rs.getString("dues_in"));
				list.setDues_out(rs.getString("dues_out"));
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
	public ArrayList<ArrayList<String>> getFinancialGrantsData(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = " select \n" + "type_of_grant,\n" + "amt_auth,\n" + "amt_alloted,\n" + "reason_for_over_under,\n"
					+ "amt_expended,\n" + "amt_utilised,\n" + "reason_for_non_util,\n" + "remarks\n"
					+ "from tb_insp_financial_grants";

			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public List<TB_INSP_FINANCIAL_GRANTS> getFinancialGrantsAction(String sus_no) {
		List<TB_INSP_FINANCIAL_GRANTS> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "select\n" + "	id,\n" + "	sus_no,\n" + "	type_of_grant,\n" + "	amt_auth,\n"
					+ "	amt_alloted,\n" + "	reason_for_over_under,\n" + "	amt_expended,\n" + "	amt_utilised,\n"
					+ "	reason_for_non_util,\n" + "	remarks\n" + "from\n"
					+ "	tb_insp_financial_grants where sus_no =? and year=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear);

			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_INSP_FINANCIAL_GRANTS list = new TB_INSP_FINANCIAL_GRANTS();
				list.setSus_no(rs.getString("sus_no"));

				list.setType_of_grant(rs.getString("type_of_grant"));
				list.setAmt_auth(rs.getString("amt_auth"));
				list.setAmt_alloted(rs.getString("amt_alloted"));
				list.setReason_for_over_under(rs.getString("reason_for_over_under"));
				list.setAmt_expended(rs.getString("amt_expended"));
				list.setAmt_utilised(rs.getString("amt_utilised"));
				list.setReason_for_non_util(rs.getString("reason_for_non_util"));
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
	public List<TB_INSP_RPT_CLASSIFICATION_RANGES> getClassSaveAction(String sus_no) {
		List<TB_INSP_RPT_CLASSIFICATION_RANGES> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "select id, sus_no, number_of_ranges, when_used,\n"
					+ "	difficulties_experienced from\n"
					+ "	tb_insp_rpt_classification_ranges where sus_no =? and year=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear);

			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_INSP_RPT_CLASSIFICATION_RANGES list = new TB_INSP_RPT_CLASSIFICATION_RANGES();
				list.setSus_no(rs.getString("sus_no"));

				list.setNumber_of_ranges(rs.getString("number_of_ranges"));
				list.setWhen_used(rs.getDate("when_used"));
				list.setDifficulties_experienced(rs.getString("difficulties_experienced"));

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
	public List<TB_INSP_FFRS> getFfrsSaveAction(String sus_no) {
		List<TB_INSP_FFRS> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "select id, sus_no, range_available, range_utilized,\n"
					+ "	whenusedffrs, difficulties_experienced from\n"
					+ "	tb_insp_ffrs where sus_no =? and year=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear);

			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_INSP_FFRS list = new TB_INSP_FFRS();
				list.setSus_no(rs.getString("sus_no"));

				list.setRange_available(rs.getString("range_available"));
				list.setRange_utilized(rs.getString("range_utilized"));
				list.setWhenusedffrs(rs.getDate("whenusedffrs"));
				list.setDifficulties_experienced(rs.getString("difficulties_experienced"));

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
	public List<TB_DETAIL_COURSES> getCourseUndertakenAction(String sus_no, HttpSession session) {
		String username = session.getAttribute("username").toString();
		List<TB_DETAIL_COURSES> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "SELECT\n" + "	COURSE_NAME,\n" + "	NUMBER_OF_COURSE,\n" + "	PERIOD_FROM,\n" + "	PERIOD_TO,\n"
					+ "	TOTAL_ALLOTTED,\n" + "	ATTENDED,\n" + "	RTU,\n" + "	DETAILED_REMARKS,\n" + " ID\n"
					+ " FROM\n" + "	TB_DETAIL_COURSES \n" + "where\n"
					+ "	sus_no=? and CAST(status as character varying) = ? and year=? ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, "0");
			stmt.setString(3, currenYear);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_DETAIL_COURSES list = new TB_DETAIL_COURSES();
				list.setCourse_name(rs.getString("course_name"));
				list.setNumber_of_course(rs.getString("number_of_course"));
				list.setPeriod_from(rs.getDate("period_from"));
				list.setPeriod_to(rs.getDate("period_to"));
				list.setTotal_allotted(rs.getString("total_allotted"));
				list.setAttended(rs.getString("attended"));
				list.setRtu(rs.getString("rtu"));
				list.setDetailed_remarks(rs.getString("detailed_remarks"));
				list.setSus_no(sus_no);
				list.setStatus(0);
				list.setYear(currenYear);
				list.setCreated_by(username);
				list.setCreated_on(new Date());
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
	public List<TB_INSP_STANDARDS_ACHIEVED> getOtherCoursesAction(String sus_no, HttpSession session) {
		String username = session.getAttribute("username").toString();
		List<TB_INSP_STANDARDS_ACHIEVED> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "SELECT\n" + "	course_name,\n" + "	total,\n" + "	grading,\n" + "	da,\n" + "	failed,\n"
					+ "	rtu,\n" + "	detailed_remarks,\n" + " ID\n" + " FROM\n" + "	TB_INSP_STANDARDS_ACHIEVED \n"
					+ "where\n" + "	sus_no=? and CAST(status as character varying) = ? and year=? ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, "0");
			stmt.setString(3, currenYear);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_INSP_STANDARDS_ACHIEVED list = new TB_INSP_STANDARDS_ACHIEVED();
				list.setCourse_name(rs.getString("course_name"));
				list.setTotal(rs.getString("total"));
				list.setGrading(rs.getString("grading"));
				list.setDa(rs.getString("da"));
				list.setFailed(rs.getString("failed"));
				list.setRtu(rs.getString("rtu"));
				list.setDetailed_remarks(rs.getString("detailed_remarks"));
				list.setSus_no(sus_no);
				list.setStatus(0);
				list.setYear(currenYear);
				list.setCreated_by(username);
				list.setCreated_on(new Date());
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
	public List<TB_INSP_SOCIAL_MEDIA_LAPSES> getSMLAction(String sus_no, HttpSession session) {
		String username = session.getAttribute("username").toString();
		List<TB_INSP_SOCIAL_MEDIA_LAPSES> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "SELECT\n" + "	date_violation_initial,\n" + "	date_violation_fmn,\n" + "	number_rank_name,\n"
					+ "	curr_status,\n" + "	loss_info,\n" + "	remarks,\n" + " ID\n" + " FROM\n"
					+ "	TB_INSP_SOCIAL_MEDIA_LAPSES \n" + "where\n"
					+ "	sus_no=? and CAST(status as character varying) = ? and year=? ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, "0");
			stmt.setString(3, currenYear);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_INSP_SOCIAL_MEDIA_LAPSES list = new TB_INSP_SOCIAL_MEDIA_LAPSES();
				list.setDate_violation_initial(rs.getDate("date_violation_initial"));
				list.setDate_violation_fmn(rs.getDate("date_violation_fmn"));
				list.setNumber_rank_name(rs.getString("number_rank_name"));
				list.setCurr_status(rs.getString("curr_status"));
				list.setLoss_info(rs.getString("loss_info"));
				list.setRemarks(rs.getString("remarks"));
				list.setSus_no(sus_no);
				list.setStatus(0);
				list.setYear(currenYear);
				list.setCreated_by(username);
				list.setCreated_on(new Date());
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
	public List<TB_INSP_ESPIONAGE_LAPSES> getELAction(String sus_no, HttpSession session) {
		String username = session.getAttribute("username").toString();
		List<TB_INSP_ESPIONAGE_LAPSES> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "SELECT\n" + "	date_violation_initial,\n" + "	date_violation_fmn,\n" + "	number_rank_name,\n"
					+ "	curr_status,\n" + "	loss_info,\n" + "	remarks,\n" + " ID\n" + " FROM\n"
					+ "	TB_INSP_ESPIONAGE_LAPSES \n" + "where\n"
					+ "	sus_no=? and CAST(status as character varying) = ? and year=? ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, "0");
			stmt.setString(3, currenYear);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_INSP_ESPIONAGE_LAPSES list = new TB_INSP_ESPIONAGE_LAPSES();
				list.setDate_violation_initial(rs.getDate("date_violation_initial"));
				list.setDate_violation_fmn(rs.getDate("date_violation_fmn"));
				list.setNumber_rank_name(rs.getString("number_rank_name"));
				list.setCurr_status(rs.getString("curr_status"));
				list.setLoss_info(rs.getString("loss_info"));
				list.setRemarks(rs.getString("remarks"));
				list.setSus_no(sus_no);
				list.setStatus(0);
				list.setYear(currenYear);
				list.setCreated_by(username);
				list.setCreated_on(new Date());
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
	public List<TB_INSP_CELL_PHONE_LAPSES> getCPCLAction(String sus_no, HttpSession session) {
		String username = session.getAttribute("username").toString();
		List<TB_INSP_CELL_PHONE_LAPSES> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();

			sql = "SELECT\n" + "	date_violation_initial,\n" + "	date_violation_fmn,\n" + "	number_rank_name,\n"
					+ "	curr_status,\n" + "	loss_info,\n" + "	remarks,\n" + " ID\n" + " FROM\n"
					+ "	TB_INSP_CELL_PHONE_LAPSES \n" + "where\n"
					+ "	sus_no=? and CAST(status as character varying) = ? and year=? ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, "0");
			stmt.setString(3, currenYear);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_INSP_CELL_PHONE_LAPSES list = new TB_INSP_CELL_PHONE_LAPSES();
				list.setDate_violation_initial(rs.getDate("date_violation_initial"));
				list.setDate_violation_fmn(rs.getDate("date_violation_fmn"));
				list.setNumber_rank_name(rs.getString("number_rank_name"));
				list.setCurr_status(rs.getString("curr_status"));
				list.setLoss_info(rs.getString("loss_info"));
				list.setRemarks(rs.getString("remarks"));
				list.setSus_no(sus_no);
				list.setStatus(0);
				list.setYear(currenYear);
				list.setCreated_by(username);
				list.setCreated_on(new Date());
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
	public boolean equipmentAandBvehSaveAction(HttpSession session, HttpServletRequest request) {
		String currenYear = yearFormat.format(new Date());
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		String a_remark = request.getParameter("a_remarks");
		String b_remark = request.getParameter("b_remarks");
		String c_remark = request.getParameter("c_remarks");
		String usr_remarks = request.getParameter("user_remarks2");
		ArrayList<ArrayList<String>> equipmentData = getEquiment_Data(session, request);
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement deletePs = null;

		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);

			String deleteSQL = "DELETE FROM tb_insp_equp_a_b_c_veh WHERE sus_no = ? AND year = ? AND status = ?";
			deletePs = conn.prepareStatement(deleteSQL);
			deletePs.setString(1, roleSusNo);
			deletePs.setString(2, currenYear);
			deletePs.setInt(3, 0);
			deletePs.executeUpdate();

			String insertSQL = "INSERT INTO tb_insp_equp_a_b_c_veh ("
					+ "sus_no, status, year, type_of_veh, authorized, held, sur, defi, "
					+ "mission_relibality_i, mission_relibality_ii, mission_relibality_iii, "
					+ "mission_relibality_iv, mission_relibality_v_vi, arm_quat_cat, comm_equp, "
					+ "night_vision_device, remarks, created_by) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			ps = conn.prepareStatement(insertSQL);

			for (ArrayList<String> rowData : equipmentData) {

				String type = rowData.get(0);
				String authorized = rowData.get(1);
				String held = rowData.get(2);
				String sur = rowData.get(3);
				String defi = rowData.get(4);
				String mission_relibality_i = rowData.get(5);
				String mission_relibality_ii = rowData.get(6);
				String mission_relibality_iii = rowData.get(7);
				String mission_relibality_iv = rowData.get(8);

				String v_vi = rowData.get(9) + "&" + rowData.get(10);

				String mission_relibality_v_vi = v_vi;
				String arm_quat_cat = "";
				String comm_equp = "";
				String night_vision_device = "";

				String remarks = "";
				if ("A".equals(type)) {
					remarks = a_remark;
				} else if ("B".equals(type)) {
					remarks = b_remark;
				} else if ("C".equals(type)) {
					remarks = c_remark;
				}

				ps.setString(1, roleSusNo);
				ps.setInt(2, status);
				ps.setString(3, currenYear);
				ps.setString(4, type);
				ps.setString(5, authorized);
				ps.setString(6, held);
				ps.setString(7, sur);
				ps.setString(8, defi);
				ps.setString(9, mission_relibality_i);
				ps.setString(10, mission_relibality_ii);
				ps.setString(11, mission_relibality_iii);
				ps.setString(12, mission_relibality_iv);
				ps.setString(13, mission_relibality_v_vi);
				ps.setString(14, arm_quat_cat);
				ps.setString(15, comm_equp);
				ps.setString(16, night_vision_device);
				ps.setString(17, remarks);
				ps.setString(18, username);
				/* ps.setTimestamp(19, (Timestamp) new Date()); */
				ps.addBatch();

			}

			int[] batchResult = ps.executeBatch();
			if (!usr_remarks.equals("") && !usr_remarks.isEmpty()) {

				addUserRemarks("equipment_item", usr_remarks, roleSusNo, username);
			}
			conn.commit();

			for (int result : batchResult) {
				if (result == PreparedStatement.EXECUTE_FAILED) {
					conn.rollback();
					return false;
				}
			}
			return true;

		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();

			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					System.err.println("Rollback failed: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
			return false;

		} finally {
			try {
				if (deletePs != null) {
					deletePs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}



	

	
	@Override
	public boolean wtResultOffrsJcoOrSaveAction(HttpSession session, HttpServletRequest request) {

		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> equipmentData = getWTResultData(session, request);
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement deletePs = null;

		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			String currenYear = yearFormat.format(new Date());
			int status = 0;
			String deleteSQL = "DELETE FROM tb_miso_insp_wt_result_off_jco_or WHERE sus_no = ? AND year = ? AND status = ?";
			deletePs = conn.prepareStatement(deleteSQL);
			deletePs.setString(1, roleSusNo);
			deletePs.setString(2, currenYear);
			deletePs.setInt(3, status);
			deletePs.executeUpdate();

			String insertSQL = "INSERT INTO tb_miso_insp_wt_result_off_jco_or ("
					+ "sus_no, status, year, category, total_no, marks_men, first_class, "
					+ "standard_shot, failed, number_exempted, number_yeto_fire, created_by, created_date) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			ps = conn.prepareStatement(insertSQL);

			for (ArrayList<String> rowData : equipmentData) {

				String category = rowData.get(0);
				String total_no = rowData.get(1);
				String marksmen = rowData.get(2);
				String first_class = rowData.get(3);
				String stander_shot = rowData.get(4);
				String failed = "0";
				String number_exmpted = "0";
				String number_yet = "0";
				java.sql.Timestamp createdDate = new java.sql.Timestamp(System.currentTimeMillis());

				ps.setString(1, roleSusNo);
				ps.setInt(2, status);
				ps.setString(3, currenYear);
				ps.setString(4, category);
				ps.setString(5, total_no);
				ps.setString(6, marksmen);
				ps.setString(7, first_class);
				ps.setString(8, stander_shot);
				ps.setString(9, failed);
				ps.setString(10, number_exmpted);
				ps.setString(11, number_yet);
				ps.setString(12, username);
				ps.setTimestamp(13, createdDate);

				ps.addBatch();

			}

			int[] batchResult = ps.executeBatch();
			conn.commit();

			for (int result : batchResult) {
				if (result == PreparedStatement.EXECUTE_FAILED) {
					conn.rollback();
					return false;
				}
			}
			return true;

		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();

			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					System.err.println("Rollback failed: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
			return false;

		} finally {
			try {
				if (deletePs != null) {
					deletePs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}



	

	@Override
	public List<Map<String, String>> stateOfSectorStoresGetData(HttpSession session, HttpServletRequest request) {
		List<Map<String, String>> resultList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT\n" + "    b.mct_main_id,\n" + "    b.mct_nomen,\n" + "    SUM(a.ue) AS ue,\n"
					+ "    SUM(a.sec_store_s) + SUM(a.acsfp_u) AS total_uh,\n" + "    CASE\n"
					+ "        WHEN (SUM(a.ue) - SUM(a.total_uh)) < 0 THEN 0\n"
					+ "        ELSE (SUM(a.ue) - SUM(a.total_uh))\n" + "    END AS defi,\n" + "    a.type,\n"
					+ "    a.sus_no,\n" + "    CASE\n" + "        WHEN a.type = 'A' THEN (\n" + "            SELECT\n"
					+ "                COUNT(*)\n" + "            FROM\n" + "                tb_tms_census_retrn part\n"
					+ "            INNER JOIN\n" + "                tb_tms_banum_dirctry bd ON part.ba_no = bd.ba_no\n"
					+ "            WHERE\n" + "                part.sus_no = a.sus_no AND part.ser_status = '0'\n"
					+ "        )\n" + "        WHEN a.type = 'B' THEN (\n" + "            SELECT\n"
					+ "               COUNT(*)\n" + "            FROM\n"
					+ "                tb_tms_mvcr_parta_dtl part\n" + "            INNER JOIN\n"
					+ "                tb_tms_banum_dirctry bd ON part.ba_no = bd.ba_no\n" + "            WHERE\n"
					+ "                part.sus_no = a.sus_no AND (part.ser_status IS NULL OR part.ser_status = '0')\n"
					+ "        )\n" + "        WHEN a.type = 'C' THEN (\n" + "            SELECT\n"
					+ "                COUNT(*)\n" + "            FROM\n" + "                tb_tms_emar_report part\n"
					+ "            INNER JOIN\n" + "                tb_tms_banum_dirctry bd ON part.em_no = bd.ba_no\n"
					+ "            WHERE\n"
					+ "                part.sus_no = a.sus_no AND (part.seviceable IS NULL OR part.seviceable = '0')\n"
					+ "        )\n" + "        ELSE NULL\n" + "    END AS count_serv,\n" + "		CASE\n"
					+ "        WHEN a.type = 'A' THEN (\n" + "            SELECT\n" + "                COUNT(*)\n"
					+ "            FROM\n" + "                tb_tms_census_retrn part\n" + "            INNER JOIN\n"
					+ "                tb_tms_banum_dirctry bd ON part.ba_no = bd.ba_no\n" + "            WHERE\n"
					+ "                part.sus_no = a.sus_no AND (part.ser_status IS not NULL AND part.ser_status != '0')\n" // Corrected
					// the
					// NOT
					// NULL
					// condition
					+ "        )\n" + "        WHEN a.type = 'B' THEN (\n" + "            SELECT\n"
					+ "               COUNT(*)\n" + "            FROM\n"
					+ "                tb_tms_mvcr_parta_dtl part\n" + "            INNER JOIN\n"
					+ "                tb_tms_banum_dirctry bd ON part.ba_no = bd.ba_no\n" + "            WHERE\n"
					+ "                part.sus_no = a.sus_no AND (part.ser_status IS not NULL AND part.ser_status != '0')\n" // Corrected
					// the
					// NOT
					// NULL
					// condition
					+ "        )\n" + "        WHEN a.type = 'C' THEN (\n" + "            SELECT\n"
					+ "                COUNT(*)\n" + "            FROM\n" + "                tb_tms_emar_report part\n"
					+ "            INNER JOIN\n" + "                tb_tms_banum_dirctry bd ON part.em_no = bd.ba_no\n"
					+ "            WHERE\n"
					+ "                part.sus_no = a.sus_no AND (part.seviceable IS not NULL AND part.seviceable != '0')\n" // Corrected
					// the
					// NOT
					// NULL
					// condition
					+ "        )\n" + "        ELSE NULL\n" + "    END AS count_unserv,"
					+ "(select reason_offrd  from tb_miso_insp_state_funds where nomenclature = b.mct_nomen and year=? and sus_no=?)reason_offrd,\n"
					+ "		(select remarks  from tb_miso_insp_state_funds where nomenclature = b.mct_nomen and year=? and sus_no=?)remarks  \n"
					+ " FROM\n" + "    tms_vehicle_status_a_b_c_with_ue_uh a\n" + "LEFT JOIN\n"
					+ "    tb_tms_mct_main_master b ON b.mct_main_id = a.mct_main_id\n";

			String roleSusNo = (String) session.getAttribute("roleSusNo");

			if (roleSusNo != null && !roleSusNo.isEmpty()) {
				sql += " WHERE a.sus_no = ? AND (a.sec_store_s >0 or a.sec_store_u > 0 or a.acsfp_u > 0 or a.acsfp_s > 0 )";
			}

			sql += " GROUP BY\n" + "    b.mct_main_id,\n" + "    b.mct_nomen,\n" + "    a.type,\n" + "    a.sus_no";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, currenYear);
			stmt.setString(2, roleSusNo);
			stmt.setString(3, currenYear);
			stmt.setString(4, roleSusNo);
			stmt.setString(5, roleSusNo);

			rs = stmt.executeQuery();
			System.err.println("3600 stateOfSectorStoresGetData " + stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, String> rowMap = new LinkedHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object value = rs.getObject(i);
					rowMap.put(columnName, (value != null) ? value.toString() : null);
				}
				resultList.add(rowMap);
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
		System.err.println("3716 list " + resultList);
		return resultList;
	}

	// PDF

	@Override
	public ArrayList<ArrayList<String>> getEstablishmentPdf(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\r\n"
					+ "    CASE WHEN reg_offrs_auth IS NULL THEN 'NIL' ELSE CAST(reg_offrs_auth AS VARCHAR(255)) END AS reg_offrs_auth,\r\n"
					+ "    CASE WHEN reg_offrs_posted IS NULL THEN 'NIL' ELSE CAST(reg_offrs_posted AS VARCHAR(255)) END AS reg_offrs_posted,\r\n"
					+ "    CASE WHEN reg_offrs_sur IS NULL THEN 'NIL' ELSE CAST(reg_offrs_sur AS VARCHAR(255)) END AS reg_offrs_sur,\r\n"
					+ "    CASE WHEN reg_offrs_defi IS NULL THEN 'NIL' ELSE CAST(reg_offrs_defi AS VARCHAR(255)) END AS reg_offrs_defi,\r\n"
					+ "    CASE WHEN reg_offrs_medcat IS NULL THEN 'NIL' ELSE CAST(reg_offrs_medcat AS VARCHAR(255)) END AS reg_offrs_medcat,\r\n"
					+ "    CASE WHEN reg_jco_auth IS NULL THEN 'NIL' ELSE CAST(reg_jco_auth AS VARCHAR(255)) END AS reg_jco_auth,\r\n"
					+ "    CASE WHEN reg_jco_posted IS NULL THEN 'NIL' ELSE CAST(reg_jco_posted AS VARCHAR(255)) END AS reg_jco_posted,\r\n"
					+ "    CASE WHEN reg_jco_sur IS NULL THEN 'NIL' ELSE CAST(reg_jco_sur AS VARCHAR(255)) END AS reg_jco_sur,\r\n"
					+ "    CASE WHEN reg_jco_defi IS NULL THEN 'NIL' ELSE CAST(reg_jco_defi AS VARCHAR(255)) END AS reg_jco_defi,\r\n"
					+ "    CASE WHEN reg_jco_medcat IS NULL THEN 'NIL' ELSE CAST(reg_jco_medcat AS VARCHAR(255)) END AS reg_jco_medcat,\r\n"
					+ "    CASE WHEN reg_or_auth IS NULL THEN 'NIL' ELSE CAST(reg_or_auth AS VARCHAR(255)) END AS reg_or_auth,\r\n"
					+ "    CASE WHEN reg_or_posted IS NULL THEN 'NIL' ELSE CAST(reg_or_posted AS VARCHAR(255)) END AS reg_or_posted,\r\n"
					+ "    CASE WHEN reg_or_sur IS NULL THEN 'NIL' ELSE CAST(reg_or_sur AS VARCHAR(255)) END AS reg_or_sur,\r\n"
					+ "    CASE WHEN reg_or_defi IS NULL THEN 'NIL' ELSE CAST(reg_or_defi AS VARCHAR(255)) END AS reg_or_defi,\r\n"
					+ "    CASE WHEN reg_or_medcat IS NULL THEN 'NIL' ELSE CAST(reg_or_medcat AS VARCHAR(255)) END AS reg_or_medcat,\r\n"
					+ "    CASE WHEN reg_civ_auth IS NULL THEN 'NIL' ELSE CAST(reg_civ_auth AS VARCHAR(255)) END AS reg_civ_auth,\r\n"
					+ "    CASE WHEN reg_civ_posted IS NULL THEN 'NIL' ELSE CAST(reg_civ_posted AS VARCHAR(255)) END AS reg_civ_posted,\r\n"
					+ "    CASE WHEN reg_civ_sur IS NULL THEN 'NIL' ELSE CAST(reg_civ_sur AS VARCHAR(255)) END AS reg_civ_sur,\r\n"
					+ "    CASE WHEN reg_civ_defi IS NULL THEN 'NIL' ELSE CAST(reg_civ_defi AS VARCHAR(255)) END AS reg_civ_defi,\r\n"
					+ "    CASE WHEN reg_civ_medcat IS NULL THEN 'NIL' ELSE CAST(reg_civ_medcat AS VARCHAR(255)) END AS reg_civ_medcat,\r\n"
					+ "    CASE WHEN reg_warrant_auth IS NULL THEN 'NIL' ELSE CAST(reg_warrant_auth AS VARCHAR(255)) END AS reg_warrant_auth,\r\n"
					+ "    CASE WHEN reg_warrant_posted IS NULL THEN 'NIL' ELSE CAST(reg_warrant_posted AS VARCHAR(255)) END AS reg_warrant_posted,\r\n"
					+ "    CASE WHEN reg_warrant_sur IS NULL THEN 'NIL' ELSE CAST(reg_warrant_sur AS VARCHAR(255)) END AS reg_warrant_sur,\r\n"
					+ "    CASE WHEN reg_warrant_defi IS NULL THEN 'NIL' ELSE CAST(reg_warrant_defi AS VARCHAR(255)) END AS reg_warrant_defi,\r\n"
					+ "    CASE WHEN reg_warrant_medcat IS NULL THEN 'NIL' ELSE CAST(reg_warrant_medcat AS VARCHAR(255)) END AS reg_warrant_medcat,\r\n"
					+ "    CASE WHEN atted_offrs_auth IS NULL THEN 'NIL' ELSE CAST(atted_offrs_auth AS VARCHAR(255)) END AS atted_offrs_auth,\r\n"
					+ "    CASE WHEN atted_offrs_posted IS NULL THEN 'NIL' ELSE CAST(atted_offrs_posted AS VARCHAR(255)) END AS atted_offrs_posted,\r\n"
					+ "    CASE WHEN atted_offrs_sur IS NULL THEN 'NIL' ELSE CAST(atted_offrs_sur AS VARCHAR(255)) END AS atted_offrs_sur,\r\n"
					+ "    CASE WHEN atted_offrs_defi IS NULL THEN 'NIL' ELSE CAST(atted_offrs_defi AS VARCHAR(255)) END AS atted_offrs_defi,\r\n"
					+ "    CASE WHEN atted_offrs_medcat IS NULL THEN 'NIL' ELSE CAST(atted_offrs_medcat AS VARCHAR(255)) END AS atted_offrs_medcat,\r\n"
					+ "    CASE WHEN atted_jco_auth IS NULL THEN 'NIL' ELSE CAST(atted_jco_auth AS VARCHAR(255)) END AS atted_jco_auth,\r\n"
					+ "    CASE WHEN atted_jco_posted IS NULL THEN 'NIL' ELSE CAST(atted_jco_posted AS VARCHAR(255)) END AS atted_jco_posted,\r\n"
					+ "    CASE WHEN atted_jco_sur IS NULL THEN 'NIL' ELSE CAST(atted_jco_sur AS VARCHAR(255)) END AS atted_jco_sur,\r\n"
					+ "    CASE WHEN atted_jco_defi IS NULL THEN 'NIL' ELSE CAST(atted_jco_defi AS VARCHAR(255)) END AS atted_jco_defi,\r\n"
					+ "    CASE WHEN atted_jco_medcat IS NULL THEN 'NIL' ELSE CAST(atted_jco_medcat AS VARCHAR(255)) END AS atted_jco_medcat,\r\n"
					+ "    CASE WHEN atted_or_auth IS NULL THEN 'NIL' ELSE CAST(atted_or_auth AS VARCHAR(255)) END AS atted_or_auth,\r\n"
					+ "    CASE WHEN atted_or_posted IS NULL THEN 'NIL' ELSE CAST(atted_or_posted AS VARCHAR(255)) END AS atted_or_posted,\r\n"
					+ "    CASE WHEN atted_or_sur IS NULL THEN 'NIL' ELSE CAST(atted_or_sur AS VARCHAR(255)) END AS atted_or_sur,\r\n"
					+ "    CASE WHEN atted_or_defi IS NULL THEN 'NIL' ELSE CAST(atted_or_defi AS VARCHAR(255)) END AS atted_or_defi,\r\n"
					+ "    CASE WHEN atted_or_medcat IS NULL THEN 'NIL' ELSE CAST(atted_or_medcat AS VARCHAR(255)) END AS atted_or_medcat,\r\n"
					+ "    CASE WHEN atted_civ_auth IS NULL THEN 'NIL' ELSE CAST(atted_civ_auth AS VARCHAR(255)) END AS atted_civ_auth,\r\n"
					+ "    CASE WHEN atted_civ_posted IS NULL THEN 'NIL' ELSE CAST(atted_civ_posted AS VARCHAR(255)) END AS atted_civ_posted,\r\n"
					+ "    CASE WHEN atted_civ_sur IS NULL THEN 'NIL' ELSE CAST(atted_civ_sur AS VARCHAR(255)) END AS atted_civ_sur,\r\n"
					+ "    CASE WHEN atted_civ_defi IS NULL THEN 'NIL' ELSE CAST(atted_civ_defi AS VARCHAR(255)) END AS atted_civ_defi,\r\n"
					+ "    CASE WHEN atted_civ_medcat IS NULL THEN 'NIL' ELSE CAST(atted_civ_medcat AS VARCHAR(255)) END AS atted_civ_medcat,\r\n"
					+ "    CASE WHEN atted_warrant_auth IS NULL THEN 'NIL' ELSE CAST(atted_warrant_auth AS VARCHAR(255)) END AS atted_warrant_auth,\r\n"
					+ "    CASE WHEN atted_warrant_posted IS NULL THEN 'NIL' ELSE CAST(atted_warrant_posted AS VARCHAR(255)) END AS atted_warrant_posted,\r\n"
					+ "    CASE WHEN atted_warrant_sur IS NULL THEN 'NIL' ELSE CAST(atted_warrant_sur AS VARCHAR(255)) END AS atted_warrant_sur,\r\n"
					+ "    CASE WHEN atted_warrant_defi IS NULL THEN 'NIL' ELSE CAST(atted_warrant_defi AS VARCHAR(255)) END AS atted_warrant_defi,\r\n"
					+ "    CASE WHEN atted_warrant_medcat IS NULL THEN 'NIL' ELSE CAST(atted_warrant_medcat AS VARCHAR(255)) END AS atted_warrant_medcat,\r\n"
					+ "    id\r\n"
					+ "FROM\r\n"
					+ "    TB_MISO_INSP_ESTABLISHMENT\r\n"
					+ "ORDER BY\r\n"
					+ "    ID";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getEquipmentPdf(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "select \n" + "type_of_veh,\n" + "authorized,\n" + "held,\n" + "sur,\n" + "defi,\n"
					+ "mission_relibality_i,\n" + "mission_relibality_ii,\n" + "mission_relibality_iii,\n"
					+ "mission_relibality_iv,\n" + "mission_relibality_v_vi,\n" + "arm_quat_cat,\n" + "comm_equp,\n"
					+ "night_vision_device,\n" + "remarks\n" + "from tb_insp_equp_a_b_c_veh ORDER BY ID";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getAnnualMeterage(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\n" + "	type_of_duty,\n" + "	equp_authorize,\n" + "	equp_cove,\n" + "	equp_remark,\n"
					+ " id\n" + " FROM\n" + "	TB_INSP_EQUP_ANNUAL_METERAGE ORDER BY ID";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getAnimalPdf(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\n" + "    type,\n" + "    auth,\n" + "    held,\n" + "    sur,\n" + "    defi,\n"
					+ "    conditon,\n" + "    teatment,\n" + "    grooming,\n" + "    feeding,\n" + "    watering,\n"
					+ "    clipping,\n" + "    f_feet,\n" + "    saddlery,\n" + "    line_gear,\n"
					+ "    accomodation,\n" + "    remarks,\n" + "    id\n" + "FROM\n" + "    TB_MISO_INSP_ANIMAL\n"
					+ "ORDER BY id";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getEqptPdf(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\r\n"
					+ "    COALESCE(nomenclature, 'NIL') AS nomenclature,\r\n"
					+ "    COALESCE(CAST(auth AS VARCHAR(255)), 'NIL') AS auth,\r\n"
					+ "    COALESCE(CAST(held AS VARCHAR(255)), 'NIL') AS held,\r\n"
					+ "    COALESCE(CAST(defi AS VARCHAR(255)), 'NIL') AS defi,\r\n"
					+ "    COALESCE(CAST(a AS VARCHAR(255)), 'NIL') AS a,\r\n"
					+ "    COALESCE(CAST(u AS VARCHAR(255)), 'NIL') AS u,\r\n"
					+ "    COALESCE(CAST(dues_in AS VARCHAR(255)), 'NIL') AS dues_in,\r\n"
					+ "    COALESCE(CAST(dues_out AS VARCHAR(255)), 'NIL') AS dues_out,\r\n"
					+ "    COALESCE(remarks, 'NIL') AS remarks,\r\n"
					+ "    id\r\n"
					+ "FROM\r\n"
					+ "    TB_MISO_INSP_DEFI_EQUP_EFFI\r\n"
					+ "ORDER BY\r\n"
					+ "    ID";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			System.out.println("Eqpt --> " + stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getEqptPdf2(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\n" + "    nomenclature,\n" + "    au,\n" + "    auth,\n" + "    held,\n" + "    defi,\n"
					+ "    offroad_reason,\n" + "    action_taken_unit,\n" + "    remarks,\n" + "    id\n" + "FROM\n"
					+ "    TB_MISO_INSP_DTL_OF_EQUP_OFFROAD ORDER BY ID";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getSectorStoresPdf(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\r\n"
					+ "    COALESCE(nomenclature, 'NIL') AS nomenclature,\r\n"
					+ "    COALESCE(CAST(au AS VARCHAR(255)), 'NIL') AS au,\r\n"
					+ "    COALESCE(CAST(auth AS VARCHAR(255)), 'NIL') AS auth,\r\n"
					+ "    COALESCE(CAST(held AS VARCHAR(255)), 'NIL') AS held,\r\n"
					+ "    COALESCE(CAST(defi AS VARCHAR(255)), 'NIL') AS defi,\r\n"
					+ "    COALESCE(ser_unser, 'NIL') AS ser_unser,\r\n"
					+ "    COALESCE(reason_offrd, 'NIL') AS reason_offrd,\r\n"
					+ "    COALESCE(remarks, 'NIL') AS remarks\r\n"
					+ "FROM\r\n"
					+ "    TB_MISO_INSP_STATE_FUNDS\r\n"
					+ "ORDER BY\r\n"
					+ "    id";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getWtPdf(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\n"
					// + " weapons,\n"
					+ "	category,\n" + "	total_no,\n" + "	marks_men,\n" + "	first_class,\n"
					+ "	standard_shot,\n" + "	failed,\n" + "	number_exempted,\n" + "	number_yeto_fire,\n" + " id\n"
					+ " FROM\n" + "	TB_MISO_INSP_WT_RESULT_OFF_JCO_OR ORDER BY ID LIMIT 3";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getCategoryPdf(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\n" + " course_name,\n" + " officers_period,\n" + " jcos_period,\n" + " ncos_period,\n"
					+ " or_period,\n" + " officers_preceding,\n" + " jcos_preceding,\n" + " ncos_preceding,\n"
					+ " or_preceding,\n" + " totalofficers,\n" + " totaljcos,\n" + " totalncos,\n" + " totalor,\n"
					+ " id\n" + " FROM\n" + " TB_MISO_INSP_COURSES_CAT_A_B ORDER BY ID";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public ArrayList<ArrayList<String>> getUpgradationPdf(HttpSession session, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\n" + "	SUS_NO,\n" + "	TRADE,\n" + "	AFFECTED_UP_GRADATION_CLASS_IV,\n"
					+ "	AFFECTED_UP_GRADATION_CLASS_III,\n" + "	AFFECTED_UP_GRADATION_CLASS_II,\n"
					+ "	AFFECTED_UP_GRADATION_CLASS_I,\n" + "	DURING_UP_GRADATION_CLASS_IV,\n"
					+ "	DURING_UP_GRADATION_CLASS_III,\n" + "	DURING_UP_GRADATION_CLASS_II,\n"
					+ "	DURING_UP_GRADATION_CLASS_I,\n" + "	TOTAL_AVAILABLE_CLASS_IV,\n"
					+ "	TOTAL_AVAILABLE_CLASS_III,\n" + "	TOTAL_AVAILABLE_CLASS_II,\n"
					+ "	TOTAL_AVAILABLE_CLASS_I\n" + "FROM\n" + "	TB_MISO_INSP_UPGRADATION ORDER BY ID";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> columns = new ArrayList<String>();

				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						columns.add(rs.getObject(i).toString());
					}
					else {
						columns.add("");
					}
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
	public List<TB_MISO_INSP_MAIN_TBL> getinsp_main_table_data(String sus_no, String currenYear) {
		List<TB_MISO_INSP_MAIN_TBL> generList = new ArrayList<>();
		String sql = "SELECT * " + "FROM tb_miso_insp_main_tbl " + "WHERE sus_no = ? AND year = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear); // Use the parameter value, not a class field

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_MISO_INSP_MAIN_TBL inspMainTbl = new TB_MISO_INSP_MAIN_TBL();
					inspMainTbl.setId(rs.getInt("id")); // Replace "id" with the actual column name
					inspMainTbl.setSus_no(rs.getString("sus_no"));
					inspMainTbl.setEstablishment_item(rs.getInt("establishment_item"));
					inspMainTbl.setEquipment_item(rs.getInt("equipment_item"));
					inspMainTbl.setAnimals_item(rs.getInt("animals_item"));
					inspMainTbl.setDeficiencies_of_equipment2_item(rs.getInt("deficiencies_of_equipment2_item"));
					inspMainTbl.setDeficiencies_of_equipment_item(rs.getInt("deficiencies_of_equipment_item"));
					inspMainTbl.setState_of_sector_stores_item(rs.getInt("state_of_sector_stores_item"));
					inspMainTbl.setWt_results_item(rs.getInt("wt_results_item"));
					inspMainTbl.setEducation_standards_item(rs.getInt("education_standards_item"));
					inspMainTbl.setCategory_item(rs.getInt("category_item"));
					inspMainTbl.setUp_gradation_item(rs.getInt("up_gradation_item"));
					inspMainTbl.setRegi_language_exam_item(rs.getInt("regi_language_exam_item"));
					inspMainTbl.setBpet_result_item(rs.getInt("bpet_result_item"));
					inspMainTbl.setPpt_result_item(rs.getInt("ppt_result_item"));
					inspMainTbl.setPromotion_exam_officers_item(rs.getInt("promotion_exam_officers_item"));
					inspMainTbl.setFinancial_grants_item(rs.getInt("financial_grants_item"));
					inspMainTbl.setTraining_ammunition_item(rs.getInt("training_ammunition_item"));
					inspMainTbl.setAvailability_of_ranges_item(rs.getInt("availability_of_ranges_item"));
					inspMainTbl.setOutstanding_audit_objections_observations_item(
							rs.getInt("outstanding_audit_objections_observations_item"));
					inspMainTbl.setCourses_item(rs.getInt("courses_item"));
					inspMainTbl.setSummarybtn(rs.getInt("summarybtn"));
					inspMainTbl.setOutstanding_item(rs.getInt("outstanding_item"));
					inspMainTbl.setMt_accidents_item(rs.getInt("mt_accidents_item"));
					inspMainTbl.setDetails_of_suicides_item(rs.getInt("details_of_suicides_item"));
					inspMainTbl.setSecurity_lapses_item(rs.getInt("security_lapses_item"));
					inspMainTbl.setDetails_of_attachments_item(rs.getInt("details_of_attachments_item"));
					inspMainTbl.setDetails_of_officers_item(rs.getInt("details_of_officers_item"));
					inspMainTbl.setSocial_media_violation_item(rs.getInt("social_media_violation_item"));
					inspMainTbl.setWeb_messenger_apps_item(rs.getInt("web_messenger_apps_item"));
					inspMainTbl.setEspionage_item(rs.getInt("espionage_item"));
					inspMainTbl.setCompromise_of_cell_phone_item(rs.getInt("compromise_of_cell_phone_item"));
					inspMainTbl.setUntraceable_item(rs.getInt("untraceable_item"));
					inspMainTbl.setLoss_of_cd_item(rs.getInt("loss_of_cd_item"));
					inspMainTbl.setLoss_of_identity_item(rs.getInt("loss_of_identity_item"));
					inspMainTbl.setLand_item(rs.getInt("land_item"));
					inspMainTbl.setSummary_of_report_last_five_year_item(
							rs.getInt("summary_of_report_last_five_year_item"));
					inspMainTbl.setRecruit_training_b_appendix_item(rs.getInt("recruit_training_b_appendix_item"));
					inspMainTbl.setDeffi_exp_resp_to_trainng_item(rs.getInt("deffi_exp_resp_to_trainng_item"));
					inspMainTbl.setEmp_of_unit_during_period_item(rs.getInt("emp_of_unit_during_period_item"));
					inspMainTbl.setRegt_funds_item(rs.getInt("regt_funds_item"));
					inspMainTbl.setStatus(rs.getInt("status"));
					inspMainTbl.setYear(rs.getString("year"));

					// Extract data from the ResultSet and populate the TB_MISO_INSP_MAIN_TBL object
					generList.add(inspMainTbl);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving summary tech other data: " + e.getMessage());
			e.printStackTrace();
		}

		return generList;
	}

	public boolean addUserRemarks(String buttonContext, String remakrs, String sus_no, String username) {
		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String currenYear = yearFormat.format(new Date());
		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			TB_MISO_INSP_USER_REMARKS userremark = new TB_MISO_INSP_USER_REMARKS();
			userremark.setSus_no(sus_no);
			userremark.setYear(currenYear);
			userremark.setUser_remarks(remakrs);
			userremark.setReport_name(buttonContext);
			userremark.setCreated_by(username);
			userremark.setCreated_date(new Date());

			ses1.save(userremark);
			success = true;

			if (success) {
				t2.commit();
			}

		} catch (HibernateException e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Hibernate error: " + e.getMessage());
			e.printStackTrace();
			success = false;
		} finally {
			if (ses1 != null) {
				ses1.close();
			}
		}
		return success;
	}









	@Override
	public List<TB_INSP_REGT_FUNDS> getRegtFundsAction(String sus_no) {
		List<TB_INSP_REGT_FUNDS> generList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;String currenYear = yearFormat.format(new Date());

		try {
			conn = dataSource.getConnection();

			sql = "select\n" + "	id,\n" + "	sus_no,\n" + "	name_of_acct,\n" + "	asset_i,\n"
					+ "	liability_i,\n" + "	asset_ii,\n" + "	liability_ii,\n" + "	asset_iii,\n"
					+ "	liability_iii, incr_decr_acct,\n" + "	remarks\n" + "from\n"
					+ "	tb_insp_regt_funds where sus_no =? and year=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear);

			rs = stmt.executeQuery();

			while (rs.next()) {
				TB_INSP_REGT_FUNDS list = new TB_INSP_REGT_FUNDS();
				list.setSus_no(rs.getString("sus_no"));

				list.setName_of_acct(rs.getString("name_of_acct"));
				list.setAsset_i(rs.getString("asset_i"));
				list.setAsset_ii(rs.getString("asset_ii"));
				list.setAsset_iii(rs.getString("asset_iii"));

				list.setLiability_i(rs.getString("liability_i"));
				list.setLiability_ii(rs.getString("liability_ii"));
				list.setLiability_iii(rs.getString("liability_iii"));

				list.setIncr_decr_acct(rs.getString("incr_decr_acct"));

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
	public ArrayList<ArrayList<String>> getPromoExamData(HttpSession session, HttpServletRequest request) {
	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String qry = "";
	try {
	conn = dataSource.getConnection();
	PreparedStatement stmt = null;
	String sql = "";

	String roleSusNo = session.getAttribute("roleSusNo").toString();
	  int currentYear = LocalDate.now().getYear();
	      

	sql = "SELECT \n" + "UPPER(a.promo_exam) AS promo_exam,\n" + "COUNT(DISTINCT e.comm_id) AS total_count,\n"
	+ "COUNT(DISTINCT CASE WHEN TO_DATE(e.date_of_passing, 'YYYY-MM-DD') < CURRENT_DATE THEN e.comm_id END) AS passed,\n"
	+ "COUNT(DISTINCT CASE WHEN TO_DATE(e.date_of_passing, 'YYYY-MM-DD') > CURRENT_DATE THEN e.comm_id END) AS yet_to_pass,"
	+ "(select remarks from   tb_miso_insp_promotion_exam where sus_no = unit_sus_no and cast(year as integer) = ?   and type_of_exam = a.promo_exam) as remarks\n"
	+ "FROM tb_psg_census_promo_exam e\n"
	+ "INNER JOIN tb_psg_mstr_promotional_exam a ON a.id::text = e.exam\n"
	+ "left join tb_psg_trans_proposed_comm_letter comm on comm.id = e.comm_id\n"
	+ " where comm.unit_sus_no in (?)\n" + "GROUP BY a.promo_exam,unit_sus_no";

	stmt = conn.prepareStatement(sql);
	stmt.setInt(1, currentYear);
	stmt.setString(2, roleSusNo.toUpperCase());

	ResultSet rs = stmt.executeQuery();

	ResultSetMetaData metaData = rs.getMetaData();
	int columnCount = metaData.getColumnCount();
	while (rs.next()) {
	ArrayList<String> columns = new ArrayList<String>();

	for (int i = 1; i <= columnCount; i++) {
	if(rs.getObject(i) != null) {
	columns.add(rs.getObject(i).toString());
	}
	else {
	columns.add("");
	}
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
	public ArrayList<ArrayList<String>> getDataBasedOnRoleSusNo(HttpSession session, HttpServletRequest request) {
		
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleAccess = session.getAttribute("roleAccess").toString();

		String roleSubAccess = session.getAttribute("roleSubAccess").toString();

		//
		String formation_code ="";
		
		
		
		List<String>roleFormationNoList= common.getformationfromSus_NOList(roleSusNo);
		
		String roleFormationNo = roleFormationNoList.size()>0?roleFormationNoList.get(0):"";
	
		System.out.println(roleFormationNoList.get(0)+"------------------"+roleSusNo);
		
		if(roleFormationNo!=null && roleFormationNo!="")
		{
			
		
		if(roleSubAccess.equals("Command")) {
			formation_code = String.valueOf(roleFormationNo.charAt(0));
		}

		if(roleSubAccess.equals("Corps")) {
			formation_code = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
		
		}

		if(roleSubAccess.equals("Division")) {
			formation_code = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
	}

		if(roleSubAccess.equals("Brigade")) {
			formation_code = roleFormationNo;
	}
		 if(roleAccess.equals("Unit"))
		 {
			 formation_code = roleFormationNo; 
		 }
		
		try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String sql = "";


		  int currentYear = LocalDate.now().getYear();
		      
sql="SELECT\n"
		+ "    A.SUS_NO\n"
		+ "	,(SELECT CASE \n"
		+ "         WHEN EXISTS (\n"
		+ "           SELECT 1\n"
		+ "           FROM TB_MISO_INSP_MAIN_PHASE_III_TBL\n"
		+ "           WHERE sus_no = A.SUS_NO\n"
		+ "             AND year = A.YEAR\n"
		+ "             or \n"
		+ "               individual_training = 0\n"
		+ "               OR collective_training = 0\n"
		+ "               OR management_training = 0\n"
		+ "               OR personnel_management = 0\n"
		+ "               OR interior_economy = 0\n"
		+ "               OR morale_motivation = 0\n"
		+ "               OR material_management = 0\n"
		+ "               OR other_miscellaneous_aspects = 0\n"
		+ "               OR measures_core_values = 0\n"
		+ "	              OR human_resource_developement = 0\n"
		+ "               OR additional_information = 0\n"
		+ "               OR comments_insp_offr = 0\n"
		+ "             \n"
		+ "         ) THEN TRUE\n"
		+ "         ELSE FALSE\n"
		+ "       END AS Approve3a\n"
		+ "	 ),\n"
		+ "	 (SELECT CASE \n"
		+ "         WHEN EXISTS (\n"
		+ "           SELECT 1\n"
		+ "           FROM TB_MISO_INSP_MAIN_PHASE_III_TBL\n"
		+ "           WHERE sus_no = A.SUS_NO\n"
		+ "             AND year = A.YEAR\n"
		+ "			  AND individual_training = 1\n"
		+ "               AND collective_training = 1\n"
		+ "               AND management_training = 1\n"
		+ "               AND personnel_management = 1\n"
		+ "               AND interior_economy= 1\n"
		+ "               AND morale_motivation = 1\n"
		+ "               AND material_management = 1\n"
		+ "               AND other_miscellaneous_aspects = 1\n"
		+ "               AND measures_core_values = 1\n"
		+ "	         	AND human_resource_developement = 1\n"
		+ "               AND additional_information = 1\n"
		+ "               AND comments_insp_offr = 1\n"
		+ "             And overall_strg_chall =0\n"
		+ "             \n"
		+ "         ) THEN TRUE\n"
		+ "         ELSE FALSE\n"
		+ "       END AS Approve3b\n"
		+ "	 )\n"
		+ "	 \n"
		+ "	 ,(SELECT CASE \n"
		+ "         WHEN EXISTS (\n"
		+ "           SELECT 1\n"
		+ "           FROM TB_MISO_INSP_MAIN_PHASE_III_TBL\n"
		+ "           WHERE sus_no = A.SUS_NO\n"
		+ "             AND year = A.YEAR\n"
		+ "             AND \n"
		+ "               individual_training = 1\n"
		+ "               AND collective_training = 1\n"
		+ "               AND management_training = 1\n"
		+ "               AND personnel_management = 1\n"
		+ "               AND interior_economy= 1\n"
		+ "               AND morale_motivation = 1\n"
		+ "               AND material_management = 1\n"
		+ "               AND other_miscellaneous_aspects = 1\n"
		+ "               AND measures_core_values = 1\n"
		+ "	          AND human_resource_developement = 1\n"
		+ "               AND additional_information = 1\n"
		+ "               AND comments_insp_offr = 1\n"
		+ "             \n"
		+ "         ) THEN TRUE\n"
		+ "         ELSE FALSE\n"
		+ "       END AS download3a),\n"
		+ "	   (SELECT CASE \n"
		+ "         WHEN EXISTS (\n"
		+ "           SELECT 1\n"
		+ "           FROM TB_MISO_INSP_MAIN_PHASE_III_TBL\n"
		+ "           WHERE sus_no = A.SUS_NO\n"
		+ "             AND year = A.YEAR\n"
		+ "             AND \n"
		+ "               individual_training =1\n"
		+ "               AND collective_training =1\n"
		+ "               AND management_training =1\n"
		+ "               AND personnel_management =1\n"
		+ "               AND interior_economy =1\n"
		+ "               AND morale_motivation =1\n"
		+ "               AND material_management =1\n"
		+ "               AND other_miscellaneous_aspects=1\n"
		+ "               AND measures_core_values =1\n"
		+ "	          AND human_resource_developement =1\n"
		+ "               AND additional_information =1\n"
		+ "               AND comments_insp_offr = 1\n"
		+ "			    And  overall_strg_chall =1\n"
		+ "             \n"
		+ "         ) THEN TRUE\n"
		+ "         ELSE FALSE\n"
		+ "       END AS download3b)\n"
		+ "	 \n"
		+ "FROM\n"
		+ "    TB_MISO_INSP_MAIN_TBL A\n"
		+ "    INNER JOIN TB_MISO_INSP_MAIN_PHASE_II_TBL B\n"
		+ "        ON A.SUS_NO = B.SUS_NO  \n"
		+ "WHERE\n"
		+ "    A.SUS_NO IN (\n"
		+ "        SELECT\n"
		+ "            ORB.SUS_NO\n"
		+ "        FROM\n"
		+ "            TB_MISO_ORBAT_UNT_DTL ORB\n"
		+ "            INNER JOIN TB_MISO_ORBAT_CODESFORM C\n"
		+ "                ON ORB.SUS_NO = C.SUS_NO\n"
		+ "                AND ORB.STATUS_SUS_NO = 'Active'\n"
		+ "                AND LEVEL_IN_HIERARCHY = 'Unit'\n"
		+ "        WHERE\n"
		+ "            ORB.FORM_CODE_CONTROL LIKE ? \n"
		+ "            AND ORB.STATUS_SUS_NO = 'Active'\n"
		+ "    ) \n"
		+ "    AND ESTABLISHMENT_ITEM = 1\n"
		+ "    AND EQUIPMENT_ITEM = 1\n"
		+ "    AND ANIMALS_ITEM = 1\n"
		+ "    AND DEFICIENCIES_OF_EQUIPMENT2_ITEM = 1\n"
		+ "    AND DEFICIENCIES_OF_EQUIPMENT_ITEM = 1\n"
		+ "    AND STATE_OF_SECTOR_STORES_ITEM = 1\n"
		+ "    AND WT_RESULTS_ITEM = 1\n"
		+ "    AND EDUCATION_STANDARDS_ITEM = 1\n"
		+ "    AND CATEGORY_ITEM = 1\n"
		+ "    AND UP_GRADATION_ITEM = 1\n"
		+ "    AND REGI_LANGUAGE_EXAM_ITEM = 1\n"
		+ "    AND BPET_RESULT_ITEM = 1\n"
		+ "    AND PPT_RESULT_ITEM = 1\n"
		+ "    AND PROMOTION_EXAM_OFFICERS_ITEM = 1\n"
		+ "    AND FINANCIAL_GRANTS_ITEM = 1\n"
		+ "    AND TRAINING_AMMUNITION_ITEM = 1\n"
		+ "    AND AVAILABILITY_OF_RANGES_ITEM = 1\n"
		+ "    AND OUTSTANDING_AUDIT_OBJECTIONS_OBSERVATIONS_ITEM = 1\n"
		+ "    AND COURSES_ITEM = 1\n"
		+ "    AND SUMMARYBTN = 1\n"
		+ "    AND OUTSTANDING_ITEM = 1\n"
		+ "    AND MT_ACCIDENTS_ITEM = 1\n"
		+ "    AND DETAILS_OF_SUICIDES_ITEM = 1\n"
		+ "    AND SECURITY_LAPSES_ITEM = 1\n"
		+ "    AND DETAILS_OF_ATTACHMENTS_ITEM = 1\n"
		+ "    AND DETAILS_OF_OFFICERS_ITEM = 1\n"
		+ "    AND SOCIAL_MEDIA_VIOLATION_ITEM = 1\n"
		+ "    AND WEB_MESSENGER_APPS_ITEM = 1\n"
		+ "    AND ESPIONAGE_ITEM = 1\n"
		+ "    AND COMPROMISE_OF_CELL_PHONE_ITEM = 1\n"
		+ "    AND UNTRACEABLE_ITEM = 1\n"
		+ "    AND LOSS_OF_CD_ITEM = 1\n"
		+ "    AND LOSS_OF_IDENTITY_ITEM = 1\n"
		+ "    AND LAND_ITEM = 1\n"
		+ "    AND SUMMARY_OF_REPORT_LAST_FIVE_YEAR_ITEM = 1\n"
		+ "    AND RECRUIT_TRAINING_B_APPENDIX_ITEM = 1\n"
		+ "    AND DEFFI_EXP_RESP_TO_TRAINNG_ITEM = 1\n"
		+ "    AND OP_PREPAREDNESS_ITEM = 1\n"
		+ "    AND TRAINING_ITEM = 1\n"
		+ "    AND STATE_WEAPON_ITEM = 1\n"
		+ "    AND STATE_PERSONNEL_ITEM = 1\n"
		+ "    AND ADMINISTATION_ITEM = 1\n"
		+ "    AND ASPECT_ITEM = 1\n"
		+ "    AND INTERIOR_ITEM = 1\n"
		+ "    AND MAJOR_ACHIEVEMENTS_ITEM = 1\n"
		+ "    AND STRENGTH_OF_UNIT_ITEM = 1\n"
		+ "    AND CHALLENGES_ITEM = 1\n"
		+ "    AND IMPROVE_FOLLOWING_ITEM = 1\n"
		+ "    AND ATTENTION_OF_HIGHER_ITEM = 1\n"
		+ "    AND MITIGATION_ITEM = 1\n"
		+ "    AND POINTS_DISCUSSION_ITEM = 1     AND a.year= ? ";
			
System.out.println("-//////////////////////////////////////////////"+sql);
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, formation_code+"%");
		stmt.setString(2, String.valueOf(currentYear));
		
System.out.println("-///////////////////////////////////------------------------///////////"+stmt);

		ResultSet rs = stmt.executeQuery();

		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next()) {
		ArrayList<String> columns = new ArrayList<String>();

		for (int i = 1; i <= columnCount; i++) {
		if(rs.getObject(i) != null) {
		columns.add(rs.getObject(i).toString());
		}
		else {
		columns.add("");
		}
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
		}
		return list;

	}
	@Override
	public ArrayList<ArrayList<String>> getTableData(HttpSession session,String tableName) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		String currenYear = yearFormat.format(new Date());
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String roleSusNo = session.getAttribute("roleSusNo").toString();
			if (!roleSusNo.equals("")) {
				qry = "where sus_no in (?)";
			}

			String eq="";
			if(tableName.equals("tb_insp_equp_a_b_c_veh"))
			{
				eq="order by type_of_veh";
			}
			
			q = " select distinct * from "+tableName+"   where sus_no=? and year=? "+eq;

			stmt = conn.prepareStatement(q);

			stmt.setString(1, roleSusNo.toUpperCase());			
			stmt.setString(2, currenYear);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> nilColumns = new ArrayList<>();
				for (int i = 1; i <= columnCount; i++) {
					nilColumns.add("NIL");
				}
				list.add(nilColumns);
			} else {
				while (rs.next()) {
					ArrayList<String> columns = new ArrayList<>();
					for (int i = 1; i <= columnCount; i++) {
						Object obj = rs.getObject(i);
						columns.add(obj != null ? obj.toString() : "NIL");
					}
					list.add(columns);
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
//-------------------------------------------------

	// pahse 3A

	@Override
	public List<TB_MISO_INSP_MAIN_PHASE_III_TBL> getinsp_main_table_data_phaseiii(String sus_no, String currenYear) {
		List<TB_MISO_INSP_MAIN_PHASE_III_TBL> generList = new ArrayList<>();
		String sql = "SELECT * " + "FROM TB_MISO_INSP_MAIN_PHASE_III_TBL " + "WHERE sus_no = ? AND year = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear); // Use the parameter value, not a class field

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_MISO_INSP_MAIN_PHASE_III_TBL inspMainTbl = new TB_MISO_INSP_MAIN_PHASE_III_TBL();
					inspMainTbl.setId(rs.getInt("id")); // Replace "id" with the actual column name
					inspMainTbl.setSus_no(rs.getString("sus_no"));
					inspMainTbl.setIndividual_training(rs.getInt("individual_training"));
					;
					inspMainTbl.setCollective_training(rs.getInt("collective_training"));
					inspMainTbl.setManagement_training(rs.getInt("management_training"));
					inspMainTbl.setPersonnel_management(rs.getInt("personnel_management"));
					inspMainTbl.setInterior_economy(rs.getInt("interior_economy"));
					inspMainTbl.setMorale_motivation(rs.getInt("morale_motivation"));
					inspMainTbl.setMaterial_management(rs.getInt("material_management"));

					inspMainTbl.setOther_miscellaneous_aspects(rs.getInt("other_miscellaneous_aspects"));
					inspMainTbl.setMeasures_core_values(rs.getInt("measures_core_values"));
					inspMainTbl.setHuman_resource_developement(rs.getInt("human_resource_developement"));
					inspMainTbl.setAdditional_information(rs.getInt("additional_information"));
					inspMainTbl.setComments_insp_offr(rs.getInt("comments_insp_offr"));
					inspMainTbl.setStatus(rs.getInt("status"));
					inspMainTbl.setYear(rs.getString("year"));
					inspMainTbl.setOverall_strg_chall(rs.getInt("overall_strg_chall"));
// Extract data from the ResultSet and populate the TB_MISO_INSP_MAIN_TBL object
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
	public List<Map<String, Object>> getOverall_Str_and_Challengesdataurl(HttpSession session,
			HttpServletRequest request, String roleSusNo ) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String currenYear = yearFormat.format(new Date());
		

		try {
			conn = dataSource.getConnection();// HERE
			String q = "select A.strengths, A.challenges, A.advisories from tb_miso_insp_str_challenges A WHERE A.sus_no = ? AND A.year = ? ";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo);
			stmt.setString(2, currenYear);

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
	public Boolean checkDownload(String roleSusNo, String currentYear) {
		boolean result = false;

		Connection conn = null;
		
		String formation_code = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT\n" + "    A.Id \n" + "FROM\n" + "    TB_MISO_INSP_MAIN_TBL A\n" + "WHERE\n"
					+ "    A.SUS_NO = ?  \n"

					+ "    AND ESTABLISHMENT_ITEM = 1\n" + "    AND EQUIPMENT_ITEM = 1\n" + "    AND ANIMALS_ITEM = 1\n"
					+ "    AND DEFICIENCIES_OF_EQUIPMENT2_ITEM = 1\n" + "    AND DEFICIENCIES_OF_EQUIPMENT_ITEM = 1\n"
					+ "    AND STATE_OF_SECTOR_STORES_ITEM = 1\n" + "    AND WT_RESULTS_ITEM = 1\n"
					+ "    AND EDUCATION_STANDARDS_ITEM = 1\n" + "    AND CATEGORY_ITEM = 1\n"
					+ "    AND UP_GRADATION_ITEM = 1\n" + "    AND REGI_LANGUAGE_EXAM_ITEM = 1\n"
					+ "    AND BPET_RESULT_ITEM = 1\n" + "    AND PPT_RESULT_ITEM = 1\n"
					+ "    AND PROMOTION_EXAM_OFFICERS_ITEM = 1\n" + "    AND FINANCIAL_GRANTS_ITEM = 1\n"
					+ "    AND TRAINING_AMMUNITION_ITEM = 1\n" + "    AND AVAILABILITY_OF_RANGES_ITEM = 1\n"
					+ "    AND OUTSTANDING_AUDIT_OBJECTIONS_OBSERVATIONS_ITEM = 1\n" + "    AND COURSES_ITEM = 1\n"
					+ "    AND SUMMARYBTN = 1\n" + "    AND OUTSTANDING_ITEM = 1\n" + "    AND MT_ACCIDENTS_ITEM = 1\n"
					+ "    AND DETAILS_OF_SUICIDES_ITEM = 1\n" + "    AND SECURITY_LAPSES_ITEM = 1\n"
					+ "    AND DETAILS_OF_ATTACHMENTS_ITEM = 1\n" + "    AND DETAILS_OF_OFFICERS_ITEM = 1\n"
					+ "    AND SOCIAL_MEDIA_VIOLATION_ITEM = 1\n" + "    AND WEB_MESSENGER_APPS_ITEM = 1\n"
					+ "    AND ESPIONAGE_ITEM = 1\n" + "    AND COMPROMISE_OF_CELL_PHONE_ITEM = 1\n"
					+ "    AND UNTRACEABLE_ITEM = 1\n" + "    AND LOSS_OF_CD_ITEM = 1\n"
					+ "    AND LOSS_OF_IDENTITY_ITEM = 1\n" + "    AND LAND_ITEM = 1\n"
//					+ "    AND SUMMARY_OF_REPORT_LAST_FIVE_YEAR_ITEM = 1\n"
					+ "    AND RECRUIT_TRAINING_B_APPENDIX_ITEM = 1\n" + "    AND DEFFI_EXP_RESP_TO_TRAINNG_ITEM = 1 \n"
					+ "    AND regt_funds_item = 1 \n" + " AND a.year= ? ";

			System.out.println("-////////////////8555555555555555555//" + sql);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roleSusNo);
			stmt.setString(2, String.valueOf(currentYear));

			System.out.println("-///////////////////////////6666666666666666666666////////------------------------///////////" + stmt);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result=true;
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

		return result;
	}
	
	
	
//	PHASE 4


	@Override
	public List<TB_MISO_INSP_MAIN_PHASE_IV_TBL> getinsp_main_table_data_4(String sus_no, String currenYear) {
		List<TB_MISO_INSP_MAIN_PHASE_IV_TBL> generList = new ArrayList<>();
		String sql = "SELECT * FROM tb_miso_insp_main_phase_iv_tbl WHERE sus_no = ? AND year = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, sus_no);
			stmt.setString(2, currenYear); // Use the parameter value, not a class field

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TB_MISO_INSP_MAIN_PHASE_IV_TBL inspMainTbl = new TB_MISO_INSP_MAIN_PHASE_IV_TBL();
					inspMainTbl.setId(rs.getInt("id")); // Replace "id" with the actual column name
					inspMainTbl.setSus_no(rs.getString("sus_no"));
					inspMainTbl.setUnit_fitness_items(rs.getInt("unit_fitness_items"));
					inspMainTbl.setUnit_data_items(rs.getInt("unit_data_items"));
					inspMainTbl.setCritical_issues_items(rs.getInt("critical_issues_items"));

					inspMainTbl.setStatus(rs.getInt("status"));
					inspMainTbl.setYear(rs.getString("year"));

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
	public List<Map<String, Object>> getfitness_for_war_or_designated_role_url(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String currenYear = yearFormat.format(new Date());
		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();//HERE
			String q = "select A.training_and_operational, A.equipment_profile, A.administration from tb_miso_fitness_for_war_or_designated_role A where sus_no=? and year=?";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, roleSusNo.toUpperCase());
			//			stmt.setInt(2, 0);
			stmt.setString(2, currenYear);

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
	public List<Map<String, Object>> getfitness_for_war_or_designated_role_unit_url(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String currenYear = yearFormat.format(new Date());
		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();//HERE
			String q = "select A.fully_fit_for_war, A.fit_or_unfit, A.recommendations from tb_miso_fitness_for_war_or_designated_role_unit A where sus_no=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			//			stmt.setInt(2, 0);
			stmt.setString(2, currenYear);

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
	public List<Map<String, Object>> getfitness_for_war_or_designated_role_critical_url(HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String currenYear = yearFormat.format(new Date());
		String roleSusNo = (String) session.getAttribute("roleSusNo");

		try {
			conn = dataSource.getConnection();//HERE
			String q = "select A.critical_issues from tb_miso_fitness_for_war_or_designated_role_critical_issues A where sus_no=? and year=?";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo.toUpperCase());
			//			stmt.setInt(2, 0);
			stmt.setString(2, currenYear);

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
	public Map<String, String> ufiList(HttpSession session) {
		Map<String, String> resultMap = new HashMap<>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			String roleSusNo = session.getAttribute("roleSusNo").toString();

			sql = " select\r\n"
					+ "    id,\r\n"
					+ "    training_and_operational,\r\n"
					+ "    equipment_profile,\r\n"
					+ "    administration,\r\n"
					+ "    created_date,\r\n"
					+ "    created_by,\r\n"
					+ "    year,\r\n"
					+ "    sus_no,\r\n"
					+ "    status\r\n"
					+ "from\r\n"
					+ "    TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE";

			stmt = conn.prepareStatement(sql);
			// stmt.setString(1, roleSusNo);

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
	public Map<String, String> udiList(HttpSession session) {
		Map<String, String> resultMap = new HashMap<>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			String roleSusNo = session.getAttribute("roleSusNo").toString();

			sql = " select\r\n"
					+ "    id,\r\n"
					+ "    fully_fit_for_war,\r\n"
					+ "    fit_or_unfit,\r\n"
					+ "    recommendations,\r\n"
					+ "    created_date,\r\n"
					+ "    created_by,\r\n"
					+ "    year,\r\n"
					+ "    sus_no,\r\n"
					+ "    status\r\n"
					+ "from\r\n"
					+ "    TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE_UNIT";

			stmt = conn.prepareStatement(sql);
			// stmt.setString(1, roleSusNo);

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
	public Map<String, String> ciiList(HttpSession session) {
		Map<String, String> resultMap = new HashMap<>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			String roleSusNo = session.getAttribute("roleSusNo").toString();

			sql = " select\r\n"
					+ "    id,\r\n"
					+ "    critical_issues,\r\n"
					+ "    created_date,\r\n"
					+ "    created_by,\r\n"
					+ "    year,\r\n"
					+ "    sus_no,\r\n"
					+ "    status\r\n"
					+ "from\r\n"
					+ "    TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE_CRITICAL_ISSUES";

			stmt = conn.prepareStatement(sql);
			// stmt.setString(1, roleSusNo);

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

}
