package com.controller.mnh;

import java.nio.file.Paths;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.models.mnh.Tb_Med_RankCode;
import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.MsAccessDataUploadDAO;
import com.models.mnh.Scrutiny_Search_Model;
import com.models.mnh.Tb_Med_BedOccupancy;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class MsAccessDataUploadController {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private MsAccessDataUploadDAO msAccess;

	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();

	@RequestMapping(value = "/admin/MsAccessDataUpload", method = RequestMethod.GET)
	public ModelAndView exportAccessData(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		Boolean val = roledao.ScreenRedirect("MsAccessDataUpload", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			if (!roleSusNo.equals("")) {
				Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			}
		}
		Mmap.put("msg", msg);
		return new ModelAndView("MsAccessDataUploadTiles");
	}

	DateWithTimeStampController timestamp = new DateWithTimeStampController();

	// bug fixing by Mitesh(11-11-24)
	@RequestMapping(value = "/MsAccessDataUploadAction", method = RequestMethod.POST)
	public ModelAndView addItemEntryForm(
			@RequestParam(value = "file_browser", required = false) MultipartFile file_browser,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws Exception {

		Boolean val = roledao.ScreenRedirect("MsAccessDataUpload", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String username = session.getAttribute("username").toString();
		String msAccDB = "";
		String sus_no = request.getParameter("sus_no1");
		String frm_dt = request.getParameter("frm_dt");
		String to_dt = request.getParameter("to_dt");

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		if (sus_no.equals("")) {
			model.put("msg", "Please select Sus No.");
			return new ModelAndView("redirect:MsAccessDataUpload");
		}
		if (frm_dt.equals("")) {
			model.put("msg", "Please select From Date.");
			return new ModelAndView("redirect:MsAccessDataUpload");
		}
		if (to_dt.equals("")) {
			model.put("msg", "Please select To Date.");
			return new ModelAndView("redirect:MsAccessDataUpload");
		}
		if (!file_browser.isEmpty()) {
			String file_browserExt = FilenameUtils.getExtension(file_browser.getOriginalFilename());
			if (!file_browserExt.toUpperCase().equals("mdb".toUpperCase())
					&& !file_browserExt.equals("mde".toUpperCase()) && !file_browserExt.equals("accdb".toUpperCase())) {
				model.put("msg", "Only *.mdb, *.mde and *.accdb file extensions allowed");
				return new ModelAndView("redirect:MsAccessDataUpload");
			} else {
				String mnhFilePath = session.getAttribute("mnhFilePath").toString();
				byte[] bytes = file_browser.getBytes();
				File dir = new File(mnhFilePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				String filename = file_browser.getOriginalFilename();
				String extension = "";
				int i = filename.lastIndexOf('.');
				if (i >= 0) {
					extension = filename.substring(i + 1);
				}
				msAccDB = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_"
						+ sus_no + "_MNH_AROGYA." + extension;
				File serverFile = new File(msAccDB);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
			}
		}

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		ResultSet resultSet2 = null;
		String sql1 = "";
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException cnfex) {
			
		}
		// try {
		// String msAccDB = "C:\\Users\\Medical\\Desktop\\Arogya.mdb";
		String dbURL = "jdbc:ucanaccess://" + msAccDB;
		connection = DriverManager.getConnection(dbURL);
		statement = connection.createStatement();
		String[] sp1 = frm_dt.split("-");
		String sp2 = sp1[0];
		String sp3 = sp1[1];
		String sp4 = sp1[2];

		String[] spp1 = to_dt.split("-");
		String spp2 = spp1[0];
		String spp3 = spp1[1];
		String spp4 = spp1[2];

		// String dd1 = sp3 + "/" + sp4 + "/" + sp2;
		// String dd2 = spp3 + "/" + spp4 + "/" + spp2;

		String dd1 = sp4 + "-" + sp3 + "-" + sp2;
		
		String dd2 = spp4 + "-" + spp3 + "-" + spp2;
		

		String sqlv = "SELECT count(*) as c FROM TB_MED_ADMISSION_DISCHARGE WHERE MEDICAL_UNIT='" + sus_no
				+ "' AND (DateValue(Format(ADMSN_DATE, 'MM-dd-yyyy')) BETWEEN DateValue('" + dd1 + "') and DateValue('" + dd2+ "')"
						+ " or DateValue(Format(DSCHRG_DATE, 'MM-dd-yyyy')) BETWEEN DateValue('" + dd1 + "') and DateValue('" + dd2 + "'))";

		
		ResultSet resultSetv = statement.executeQuery(sqlv);

		resultSetv.next();
		
		int a = resultSetv.getInt("c");
		if (a == 0) {
			model.put("msg", "SUS No/Hospital Name Unmatched Data");
		}else {
			sql1 = "SELECT * FROM TB_MED_ADMISSION_DISCHARGE WHERE MEDICAL_UNIT='" + sus_no
					+ "' AND (DateValue(Format(ADMSN_DATE, 'MM-dd-yyyy')) BETWEEN DateValue('" + dd1 + "') and DateValue('" + dd2+ "')"
							+ " or DateValue(Format(DSCHRG_DATE, 'MM-dd-yyyy')) BETWEEN DateValue('" + dd1 + "') and DateValue('" + dd2+ "'))"
									+ " ORDER BY MEDICAL_UNIT, AND_NO";

			
			resultSet = statement.executeQuery(sql1);
			// Dup Checks
			int insertCount = 0;
			int modifyCount = 0;
			int bedOccuInsertCount = 0;
			Session sessionHQL = null;
			Transaction tx = null;
			// try {
			sessionHQL = HibernateUtil.getSessionFactory().openSession();
			tx = sessionHQL.beginTransaction();
			int flag = 0;
			while (resultSet.next()) {
				flag++;
				
				Scrutiny_Search_Model tb_med_patient = new Scrutiny_Search_Model();
				List<Scrutiny_Search_Model> tb_med_patientDetais = msAccess.getCheckIfExits(resultSet.getString("AND_NO"), sus_no);
				if (tb_med_patientDetais.size() == 0) {
				
					tb_med_patient.setMedical_unit(sus_no);
					tb_med_patient.setAnd_no(resultSet.getString("AND_NO"));
					if (resultSet.getString("DIET") != "" && resultSet.getString("DIET") != null) {
						tb_med_patient.setDiet(getCleanValue(resultSet.getString("DIET"), 30));
					} else {
						tb_med_patient.setDiet("SVEG");
					}

					tb_med_patient.setAdmsn_date(convertDateFromate(resultSet.getString("ADMSN_DATE")));
					if (resultSet.getString("WARD") != "" && resultSet.getString("WARD") != null) {
						tb_med_patient.setWard(getCleanValue(resultSet.getString("WARD"), 30));
					} else {
						tb_med_patient.setWard("MED");
					}

					if (resultSet.getString("NAME") != "" && resultSet.getString("NAME") != null) {
						tb_med_patient.setName(getCleanValue(resultSet.getString("NAME"), 100));
					}

					tb_med_patient.setAge_year(resultSet.getInt("AGE_YEAR"));
					tb_med_patient.setAge_month(resultSet.getInt("AGE_MONTH"));
					tb_med_patient.setAge_days(resultSet.getInt("AGE_DAYS"));

					if (resultSet.getString("SEX") != "" && resultSet.getString("SEX") != null) {
						tb_med_patient.setSex(resultSet.getString("SEX"));
					} else {
						tb_med_patient.setSex(resultSet.getString("M"));
					}

					if (resultSet.getString("MARITAL_STATUS") != "" && resultSet.getString("MARITAL_STATUS") != null) {
						tb_med_patient.setMarital_status(getCleanValue(resultSet.getString("MARITAL_STATUS"), 30));
					} else {
						tb_med_patient.setMarital_status("SINGLE");
					}

					if (resultSet.getString("RELATIONSHIP") != "" && resultSet.getString("RELATIONSHIP") != null) {
						tb_med_patient.setRelationship(getCleanValue(resultSet.getString("RELATIONSHIP"), 30));
					} else {
						tb_med_patient.setRelationship("SELF");
					}

					if (resultSet.getString("NBB") != "" && resultSet.getString("NBB") != null) {
						tb_med_patient.setNbb(resultSet.getString("NBB"));
					} else {
						tb_med_patient.setNbb("N");
					}
					if (resultSet.getString("NBB_WEIGHT") != "" && resultSet.getString("NBB_WEIGHT") != null) {
						tb_med_patient
								.setNbb_weight(Double.parseDouble(String.valueOf(resultSet.getInt("NBB_WEIGHT"))));
					} else {
						tb_med_patient.setNbb_weight(0.0);
					}
					if (resultSet.getString("PERSNL_NO") != "" && resultSet.getString("PERSNL_NO") != null) {
						tb_med_patient.setPersnl_no(getCleanValue(resultSet.getString("PERSNL_NO"), 20));
					} else {
						tb_med_patient.setPersnl_no("");
					}
					if (resultSet.getString("CATEGORY") != "" && resultSet.getString("CATEGORY") != null) {
						tb_med_patient.setCategory(getCleanValue(resultSet.getString("CATEGORY"), 30));
					}
					if (resultSet.getString("RANK") != "" && resultSet.getString("RANK") != null) {
						tb_med_patient.setRank(getCleanValue(resultSet.getString("RANK"), 20));
					}
					if (resultSet.getString("PERSNL_NAME") != "" && resultSet.getString("PERSNL_NAME") != null) {
						tb_med_patient.setPersnl_name(getCleanValue(resultSet.getString("PERSNL_NAME"), 100));
					}
					tb_med_patient.setPersnl_age_year(resultSet.getInt("PERSNL_AGE_YEAR"));
					tb_med_patient.setPersnl_age_month(resultSet.getInt("PERSNL_AGE_MONTH"));
					if (resultSet.getString("PERSNL_SEX") != "" && resultSet.getString("PERSNL_SEX") != null) {
						tb_med_patient.setPersnl_sex(resultSet.getString("PERSNL_SEX"));
					} else {
						tb_med_patient.setPersnl_sex("M");
					}
					tb_med_patient.setService_years(resultSet.getInt("SERVICE_YEARS"));
					tb_med_patient.setService_months(resultSet.getInt("SERVICE_MONTHS"));
					if (resultSet.getString("PERSNL_UNIT") != "" && resultSet.getString("PERSNL_UNIT") != null) {
						tb_med_patient.setPersnl_unit(getCleanValue(resultSet.getString("PERSNL_UNIT"), 100));
					}
					if (resultSet.getString("PERSNL_UNIT_DESC") != ""
							&& resultSet.getString("PERSNL_UNIT_DESC") != null) {
						tb_med_patient.setPersnl_unit_desc(getCleanValue(resultSet.getString("PERSNL_UNIT_DESC"), 100));
					}
					if (resultSet.getString("STATION") != "" && resultSet.getString("STATION") != null) {
						tb_med_patient.setStation(getCleanValue(resultSet.getString("STATION"), 150));
					}
					if (resultSet.getString("FORMATION") != "" && resultSet.getString("FORMATION") != null) {
						tb_med_patient.setFormation(getCleanValue(resultSet.getString("FORMATION"), 100));
					}
					if (resultSet.getString("ARM_CORPS") != "" && resultSet.getString("ARM_CORPS") != null) {
						tb_med_patient.setArm_corps(getCleanValue(resultSet.getString("ARM_CORPS"), 50));
					}
					if (resultSet.getString("TRADE") != "" && resultSet.getString("TRADE") != null) {
						tb_med_patient.setTrade(getCleanValue(resultSet.getString("TRADE"), 30));
					}
					if (resultSet.getString("RELIGION") != "" && resultSet.getString("RELIGION") != null) {
						tb_med_patient.setReligion(getCleanValue(resultSet.getString("RELIGION"), 30));
					} else {
						tb_med_patient.setReligion("HINDU");
					}
					if (resultSet.getString("CLASS") != "" && resultSet.getString("CLASS") != null) {
						tb_med_patient.setClass1(getCleanValue(resultSet.getString("CLASS"), 30));
					}
					if (resultSet.getString("PERSNL_MARITAL_STATUS") != ""
							&& resultSet.getString("PERSNL_MARITAL_STATUS") != null) {
						tb_med_patient.setPersnl_marital_status(
								getCleanValue(resultSet.getString("PERSNL_MARITAL_STATUS"), 30));
					} else {
						tb_med_patient.setPersnl_marital_status("SINGLE");
					}
					if (resultSet.getString("DIST_ORIGIN") != "" && resultSet.getString("DIST_ORIGIN") != null) {
						tb_med_patient.setDist_origin(getCleanValue(resultSet.getString("DIST_ORIGIN"), 150));
					}
					if (resultSet.getString("STATE_ORIGIN") != "" && resultSet.getString("STATE_ORIGIN") != null) {
						tb_med_patient.setState_origin(getCleanValue(resultSet.getString("STATE_ORIGIN"), 150));
					}
					if (resultSet.getString("RECORDS_OFFICE") != "" && resultSet.getString("RECORDS_OFFICE") != null) {
						tb_med_patient.setRecords_office(getCleanValue(resultSet.getString("RECORDS_OFFICE"), 250));
					}
					if (resultSet.getString("ARRIVAL_TIME") != "" && resultSet.getString("ARRIVAL_TIME") != null) {
						tb_med_patient.setArrival_time(getCleanValue(resultSet.getString("ARRIVAL_TIME"), 15));
					}
					if (resultSet.getString("ADMSN_TYPE") != "" && resultSet.getString("ADMSN_TYPE") != null) {
						tb_med_patient.setAdmsn_type(getCleanValue(resultSet.getString("ADMSN_TYPE"), 30));
					} else {
						tb_med_patient.setAdmsn_type("FRESH");
					}

					if (resultSet.getString("TRNSFRD_FROM") != "" && resultSet.getString("TRNSFRD_FROM") != null) {
						tb_med_patient.setTrnsfrd_from(getCleanValue(resultSet.getString("TRNSFRD_FROM"), 100));
					}

					if (resultSet.getString("TRNSFRD_FROM_DESC") != ""
							&& resultSet.getString("TRNSFRD_FROM_DESC") != null) {
						tb_med_patient
								.setTrnsfrd_from_desc(getCleanValue(resultSet.getString("TRNSFRD_FROM_DESC"), 100));
					}

					if (resultSet.getString("TRNSFRD_AND_NO") != "" && resultSet.getString("TRNSFRD_AND_NO") != null) {
						tb_med_patient.setTrnsfrd_and_no(getCleanValue(resultSet.getString("TRNSFRD_AND_NO"), 30));
					}

					if (resultSet.getString("ON_LIST") != "" && resultSet.getString("ON_LIST") != null) {
						tb_med_patient.setOn_list(getCleanValue(resultSet.getString("ON_LIST"), 30));
					} else {
						tb_med_patient.setOn_list("NONE");
					}

					if (resultSet.getString("CONDITION") != "" && resultSet.getString("CONDITION") != null) {
						tb_med_patient.setCondition1(getCleanValue(resultSet.getString("CONDITION"), 30));
					} else {
						tb_med_patient.setCondition1("WALKING");
					}

					if (resultSet.getString("MLC") != "" && resultSet.getString("MLC") != null) {
						tb_med_patient.setMlc(resultSet.getString("MLC"));
					} else {
						tb_med_patient.setMlc("N");
					}

					if (resultSet.getString("MLC_DATE") != "" && resultSet.getString("MLC_DATE") != null) {
						if (resultSet.getString("MLC_DATE").length() <= 30) {
							tb_med_patient.setMlc_date(resultSet.getString("MLC_DATE"));
						} else {
							tb_med_patient.setMlc_date(substring(resultSet.getString("MLC_DATE"), 0, 29));
						}
					}

					if (resultSet.getString("INJRY_RPT") != "" && resultSet.getString("INJRY_RPT") != null) {
						tb_med_patient.setInjry_rpt(resultSet.getString("INJRY_RPT"));
					} else {
						tb_med_patient.setInjry_rpt("N");
					}
					/*
					 * if (!resultSet.getString("INJRY_RPT_DATE").equals("") &&
					 * resultSet.getString("INJRY_RPT_DATE") != null) {
					 * tb_med_patient.setInjry_rpt_date(convertDateFromate(resultSet.getString(
					 * "INJRY_RPT_DATE"))); }
					 */
					if (!resultSet.getString("INJRY_RPT_DATE").equals("")
							&& resultSet.getString("INJRY_RPT_DATE") != null) {
						tb_med_patient.setInjry_rpt_date(convertDateFromate(resultSet.getString("INJRY_RPT_DATE")));
					}
					if (resultSet.getString("MED_CTGRY_S") != "" && resultSet.getString("MED_CTGRY_S") != null) {
						tb_med_patient.setMed_ctgry_s(getCleanValue(resultSet.getString("MED_CTGRY_S"), 6));
					} else {
						tb_med_patient.setMed_ctgry_s("1A");
					}

					if (resultSet.getString("MED_CTGRY_H") != "" && resultSet.getString("MED_CTGRY_H") != null) {
						tb_med_patient.setMed_ctgry_h(getCleanValue(resultSet.getString("MED_CTGRY_H"), 6));
					} else {
						tb_med_patient.setMed_ctgry_h("1A");
					}

					if (resultSet.getString("MED_CTGRY_A") != "" && resultSet.getString("MED_CTGRY_A") != null) {
						tb_med_patient.setMed_ctgry_a(getCleanValue(resultSet.getString("MED_CTGRY_A"), 6));
					} else {
						tb_med_patient.setMed_ctgry_a("1A");
					}

					if (resultSet.getString("MED_CTGRY_P") != "" && resultSet.getString("MED_CTGRY_P") != null) {
						tb_med_patient.setMed_ctgry_p(getCleanValue(resultSet.getString("MED_CTGRY_P"), 6));
					} else {
						tb_med_patient.setMed_ctgry_p("1A");
					}

					if (resultSet.getString("MED_CTGRY_E") != "" && resultSet.getString("MED_CTGRY_E") != null) {
						tb_med_patient.setMed_ctgry_e(getCleanValue(resultSet.getString("MED_CTGRY_E"), 6));
					} else {
						tb_med_patient.setMed_ctgry_e("1A");
					}

					if (resultSet.getString("DSCHRG_DATE") != "" && resultSet.getString("DSCHRG_DATE") != null
							&& resultSet.getString("DSCHRG_DATE").length() != 0) {
						tb_med_patient.setDschrg_date(convertDateFromate(resultSet.getString("DSCHRG_DATE")));
					}
					if (resultSet.getString("DISPOSAL") != "" && resultSet.getString("DISPOSAL") != null) {
						tb_med_patient.setDisposal(getCleanValue(resultSet.getString("DISPOSAL"), 20));
					}
					if (resultSet.getString("TRNSFRD_TO") != "" && resultSet.getString("TRNSFRD_TO") != null) {
						tb_med_patient.setTrnsfrd_to(getCleanValue(resultSet.getString("TRNSFRD_TO"), 100));
					}
					if (resultSet.getString("TRNSFRD_TO_DESC") != ""
							&& resultSet.getString("TRNSFRD_TO_DESC") != null) {
						tb_med_patient.setTrnsfrd_to_desc(getCleanValue(resultSet.getString("TRNSFRD_TO_DESC"), 100));
					}
					if (resultSet.getString("ADMSN_STATUS") != "" && resultSet.getString("ADMSN_STATUS") != null) {
						tb_med_patient.setAdmsn_status(getCleanValue(resultSet.getString("ADMSN_STATUS"), 30));
					}
					if (resultSet.getString("DSCHRG_STATUS") != "" && resultSet.getString("DSCHRG_STATUS") != null) {
						tb_med_patient.setDschrg_status(getCleanValue(resultSet.getString("DSCHRG_STATUS"), 30));
					}

					tb_med_patient.setCreated_on(new Date());
					tb_med_patient.setCreated_by(username);
					tb_med_patient.setModified_on(null);
					tb_med_patient.setModified_by("");
					tb_med_patient.setApprvd_at_unit_on(null);
					tb_med_patient.setApprvr_at_unit_by("");
					tb_med_patient.setApprvd_at_miso_on(null);
					tb_med_patient.setApprvr_at_miso_by("");
					tb_med_patient.setRejection_reason("");
					tb_med_patient.setPatient_status("PNDUNIT");

					if (resultSet.getString("TIME_STAMP") != "" && resultSet.getString("TIME_STAMP") != null) {
						tb_med_patient.setTime_stamp(convertDateFromate(resultSet.getString("TIME_STAMP")));
					}
					if (resultSet.getString("NOK_NAME") != "" && resultSet.getString("NOK_NAME") != null) {
						tb_med_patient.setNok_name(getCleanValue(resultSet.getString("NOK_NAME"), 100));
					}
					if (resultSet.getString("NOK_RELATION") != "" && resultSet.getString("NOK_RELATION") != null) {
						tb_med_patient.setNok_relation(getCleanValue(resultSet.getString("NOK_RELATION"), 30));
					}
					if (resultSet.getString("NOK_ADDRESS") != "" && resultSet.getString("NOK_ADDRESS") != null) {
						tb_med_patient.setNok_address(getCleanValue(resultSet.getString("NOK_ADDRESS"), 300));
					}
					if (resultSet.getString("CDA_NO") != "" && resultSet.getString("CDA_NO") != null) {
						tb_med_patient.setCda_no(getCleanValue(resultSet.getString("CDA_NO"), 50));
					}
					if (resultSet.getString("TYPE_INJURY") != "" && resultSet.getString("TYPE_INJURY") != null) {
						tb_med_patient.setType_injury(getCleanValue(resultSet.getString("TYPE_INJURY"), 30));
					}
					if (resultSet.getString("DETAILS_SICKLEAVE") != ""
							&& resultSet.getString("DETAILS_SICKLEAVE") != null) {
						tb_med_patient
								.setDetails_sickleave(getCleanValue(resultSet.getString("DETAILS_SICKLEAVE"), 150));
					}
					if (resultSet.getString("INJURY_REPORT_A") != ""
							&& resultSet.getString("INJURY_REPORT_A") != null) {
						tb_med_patient.setInjury_report_a(getCleanValue(resultSet.getString("INJURY_REPORT_A"), 6));
					}
					if (resultSet.getString("DISCHARGE_WARD") != "" && resultSet.getString("DISCHARGE_WARD") != null) {
						tb_med_patient.setDischarge_ward(getCleanValue(resultSet.getString("DISCHARGE_WARD"), 30));
					}
					if (resultSet.getString("TRANSPORT_MODE") != "" && resultSet.getString("TRANSPORT_MODE") != null) {
						tb_med_patient.setTransport_mode(getCleanValue(resultSet.getString("TRANSPORT_MODE"), 20));
					}
					if (resultSet.getString("OTHER_DETAILS") != "" && resultSet.getString("OTHER_DETAILS") != null) {
						tb_med_patient.setOther_details(getCleanValue(resultSet.getString("OTHER_DETAILS"), 30));
					}
					if (resultSet.getString("WARD_NO_A") != "" && resultSet.getString("WARD_NO_A") != null) {
						tb_med_patient.setWard_no_a(getCleanValue(resultSet.getString("WARD_NO_A"), 6));
					}
					if (resultSet.getString("WARD_NO_D") != "" && resultSet.getString("WARD_NO_D") != null) {
						tb_med_patient.setWard_no_d(getCleanValue(resultSet.getString("WARD_NO_D"), 6));
					}
					if (resultSet.getString("NOT_PATIENT") != "" && resultSet.getString("NOT_PATIENT") != null) {
						tb_med_patient.setNot_patient(resultSet.getString("NOT_PATIENT"));
					}
					if (resultSet.getString("DEPENDENT_DETAILS") != ""
							&& resultSet.getString("DEPENDENT_DETAILS") != null) {
						tb_med_patient
								.setDependent_details(getCleanValue(resultSet.getString("DEPENDENT_DETAILS"), 30));
					}
					if (resultSet.getString("DISCHARGE_REMARKS") != ""
							&& resultSet.getString("DISCHARGE_REMARKS") != null) {
						tb_med_patient
								.setDischarge_remarks(getCleanValue(resultSet.getString("DISCHARGE_REMARKS"), 300));
					}
					if (resultSet.getString("AB64") != "" && resultSet.getString("AB64") != null) {
						tb_med_patient.setAb64(getCleanValue(resultSet.getString("AB64"), 30));
					}
					if (resultSet.getString("ICD_REMARKS_A") != "" && resultSet.getString("ICD_REMARKS_A") != null) {
						tb_med_patient.setIcd_remarks_a(getCleanValue(resultSet.getString("ICD_REMARKS_A"), 300));
					}
					if (resultSet.getString("ICD_REMARKS_D") != "" && resultSet.getString("ICD_REMARKS_D") != null) {
						tb_med_patient
								.setIcd_remarks_d(getCleanValue(resultSet.getString("ICD_REMARKS_D").toString(), 300));
					}
					if (resultSet.getString("ID_CARD") != "" && resultSet.getString("ID_CARD") != null) {
						tb_med_patient.setId_card(getCleanValue(resultSet.getString("ID_CARD"), 150));
					}
					//// End First Table

					//// Second Table
					if (!tb_med_patient.getMedical_unit().equals("") && !tb_med_patient.getAnd_no().equals("")) {
						
						String sql2 = "SELECT * FROM TB_MED_ADMISSIONDIAGNOSIS where medical_unit='"
								+ tb_med_patient.getMedical_unit() + "' and AND_NO='" + tb_med_patient.getAnd_no()
								+ "' order by ADMSN_DSCHRG_FLAG";
						resultSet1 = statement.executeQuery(sql2);
						while (resultSet1.next()) {
							
							String ADMSN_DSCHRG_FLAG = resultSet1.getString("ADMSN_DSCHRG_FLAG");
							int PRIORITY_INDEX = resultSet1.getInt("PRIORITY_INDEX");
							// Adminssion
							if ((ADMSN_DSCHRG_FLAG.equals("A")) && (PRIORITY_INDEX == 1)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code1a(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								} else {
									tb_med_patient.setDiagnosis_code1a("Z09");
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code1a(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setAdmsn_dschrg_flag_a(ADMSN_DSCHRG_FLAG);
								tb_med_patient.setPriority_index1a(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("A")) && (PRIORITY_INDEX == 2)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code2a(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code2a(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setAdmsn_dschrg_flag_a(ADMSN_DSCHRG_FLAG);
								tb_med_patient.setPriority_index2a(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("A")) && (PRIORITY_INDEX == 3)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code3a(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code3a(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setAdmsn_dschrg_flag_a(ADMSN_DSCHRG_FLAG);
								tb_med_patient.setPriority_index3a(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("A")) && (PRIORITY_INDEX == 4)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code4a(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code4a(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setAdmsn_dschrg_flag_a(ADMSN_DSCHRG_FLAG);
								tb_med_patient.setPriority_index4a(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("A")) && (PRIORITY_INDEX == 5)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code5a(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code5a(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setAdmsn_dschrg_flag_a(ADMSN_DSCHRG_FLAG);
								tb_med_patient.setPriority_index5a(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("A")) && (PRIORITY_INDEX == 6)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code6a(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code6a(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setAdmsn_dschrg_flag_a(ADMSN_DSCHRG_FLAG);
								tb_med_patient.setPriority_index6a(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("A")) && (PRIORITY_INDEX == 7)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code7a(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code7a(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setAdmsn_dschrg_flag_a(ADMSN_DSCHRG_FLAG);
								tb_med_patient.setPriority_index7a(PRIORITY_INDEX);
							}

							// Discharge
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 1)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
								
									tb_med_patient.setDiagnosis_code1d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));

									
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code1d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setAdmsn_dschrg_flag_d(ADMSN_DSCHRG_FLAG);
								tb_med_patient.setPriority_index1d(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 2)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code2d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code2d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setPriority_index2d(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 3)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code3d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code3d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setPriority_index3d(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 4)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code4d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code4d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setPriority_index4d(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 5)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code5d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code5d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setPriority_index5d(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 6)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code6d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code6d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setPriority_index6d(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 7)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code7d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code7d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setPriority_index7d(PRIORITY_INDEX);
							}
						}
					}
					insertCount += 1;
					sessionHQL.save(tb_med_patient); // Save patient Details
					sessionHQL.flush();
					sessionHQL.clear();
				} else {
					// if(tb_med_patientDetais.get(0).getDschrg_date() == null &&
					// !resultSet.getString("DSCHRG_DATE").equals("") &&
					// resultSet.getString("DSCHRG_DATE") != null) {
					
					tb_med_patient = tb_med_patientDetais.get(0);
					tb_med_patient.setModified_on(new Date());
					tb_med_patient.setModified_by(username);

					if (resultSet.getString("MED_CTGRY_S") != "" && resultSet.getString("MED_CTGRY_S") != null) {
						tb_med_patient.setMed_ctgry_s(getCleanValue(resultSet.getString("MED_CTGRY_S"), 6));
					} else {
						tb_med_patient.setMed_ctgry_s("1A");
					}
					if (resultSet.getString("MED_CTGRY_H") != "" && resultSet.getString("MED_CTGRY_H") != null) {
						tb_med_patient.setMed_ctgry_h(getCleanValue(resultSet.getString("MED_CTGRY_H"), 6));
					} else {
						tb_med_patient.setMed_ctgry_h("1A");
					}
					if (resultSet.getString("MED_CTGRY_A") != "" && resultSet.getString("MED_CTGRY_A") != null) {
						tb_med_patient.setMed_ctgry_a(getCleanValue(resultSet.getString("MED_CTGRY_A"), 6));
					} else {
						tb_med_patient.setMed_ctgry_a("1A");
					}

					if (resultSet.getString("MED_CTGRY_P") != "" && resultSet.getString("MED_CTGRY_P") != null) {
						tb_med_patient.setMed_ctgry_p(getCleanValue(resultSet.getString("MED_CTGRY_P"), 6));
					} else {
						tb_med_patient.setMed_ctgry_p("1A");
					}

					if (resultSet.getString("MED_CTGRY_E") != "" && resultSet.getString("MED_CTGRY_E") != null) {
						tb_med_patient.setMed_ctgry_e(getCleanValue(resultSet.getString("MED_CTGRY_E"), 6));
					} else {
						tb_med_patient.setMed_ctgry_e("1A");
					}

					if (resultSet.getString("DSCHRG_DATE") != "" && resultSet.getString("DSCHRG_DATE") != null) {
						if (resultSet.getString("DSCHRG_DATE").length() != 0) {
							tb_med_patient.setDschrg_date(convertDateFromate(resultSet.getString("DSCHRG_DATE")));
						} else {
							tb_med_patient.setDschrg_date(null);
						}
					} else {
						tb_med_patient.setDschrg_date(null);
					}

					if (resultSet.getString("DISPOSAL") != "" && resultSet.getString("DISPOSAL") != null) {
						tb_med_patient.setDisposal(getCleanValue(resultSet.getString("DISPOSAL"), 20));
					} else {
						tb_med_patient.setDisposal("");
					}

					if (resultSet.getString("TRNSFRD_TO") != "" && resultSet.getString("TRNSFRD_TO") != null) {
						tb_med_patient.setTrnsfrd_to(getCleanValue(resultSet.getString("TRNSFRD_TO"), 100));
					} else {
						tb_med_patient.setTrnsfrd_to("");
					}
					if (resultSet.getString("TRNSFRD_TO_DESC") != ""
							&& resultSet.getString("TRNSFRD_TO_DESC") != null) {
						tb_med_patient.setTrnsfrd_to_desc(getCleanValue(resultSet.getString("TRNSFRD_TO_DESC"), 100));
					} else {
						tb_med_patient.setTrnsfrd_to_desc("");
					}

					if (resultSet.getString("DSCHRG_STATUS") != "" && resultSet.getString("DSCHRG_STATUS") != null) {
						tb_med_patient.setDschrg_status(getCleanValue(resultSet.getString("DSCHRG_STATUS"), 30));
					} else {
						tb_med_patient.setDschrg_status("");
					}

					if (resultSet.getString("DISCHARGE_WARD") != "" && resultSet.getString("DISCHARGE_WARD") != null) {
						tb_med_patient.setDischarge_ward(getCleanValue(resultSet.getString("DISCHARGE_WARD"), 30));
					}
					if (resultSet.getString("WARD_NO_D") != "" && resultSet.getString("WARD_NO_D") != null) {
						tb_med_patient.setWard_no_d(getCleanValue(resultSet.getString("WARD_NO_D"), 6));
					}

					if (resultSet.getString("DISCHARGE_REMARKS") != ""
							&& resultSet.getString("DISCHARGE_REMARKS") != null) {
						tb_med_patient
								.setDischarge_remarks(getCleanValue(resultSet.getString("DISCHARGE_REMARKS"), 300));
					}
					if (resultSet.getString("ICD_REMARKS_D") != "" && resultSet.getString("ICD_REMARKS_D") != null) {
						tb_med_patient.setIcd_remarks_d(getCleanValue(resultSet.getString("ICD_REMARKS_D"), 300));
					}

					if (!tb_med_patient.getMedical_unit().equals("") && !tb_med_patient.getAnd_no().equals("")) {
						String sql2 = "SELECT * FROM TB_MED_ADMISSIONDIAGNOSIS where medical_unit='"
								+ tb_med_patient.getMedical_unit() + "' and AND_NO='" + tb_med_patient.getAnd_no()
								+ "' and ADMSN_DSCHRG_FLAG ='D' order by ADMSN_DSCHRG_FLAG";
						resultSet1 = statement.executeQuery(sql2);
						while (resultSet1.next()) {
							String ADMSN_DSCHRG_FLAG = "D";
							int PRIORITY_INDEX = resultSet1.getInt("PRIORITY_INDEX");
							// Discharge
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 1)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code1d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code1d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setAdmsn_dschrg_flag_d(ADMSN_DSCHRG_FLAG);
								tb_med_patient.setPriority_index1d(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 2)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code2d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code2d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setPriority_index2d(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 3)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code3d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code3d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setPriority_index3d(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 4)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code4d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code4d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setPriority_index4d(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 5)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code5d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code5d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setPriority_index5d(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 6)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code6d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code6d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setPriority_index6d(PRIORITY_INDEX);
							}
							if ((ADMSN_DSCHRG_FLAG.equals("D")) && (PRIORITY_INDEX == 7)) {
								if (resultSet1.getString("DIAGNOSIS_CODE") != ""
										&& resultSet1.getString("DIAGNOSIS_CODE") != null) {
									tb_med_patient.setDiagnosis_code7d(
											getCleanValue(resultSet1.getString("DIAGNOSIS_CODE"), 30));
								}
								if (resultSet1.getString("ICD_CAUSE_CODE") != ""
										&& resultSet1.getString("ICD_CAUSE_CODE") != null) {
									tb_med_patient.setIcd_cause_code7d(
											getCleanValue(resultSet1.getString("ICD_CAUSE_CODE"), 30));
								}
								tb_med_patient.setPriority_index7d(PRIORITY_INDEX);
							}
						}
						// }
						modifyCount += 1;
						sessionHQL.update(tb_med_patient);
						sessionHQL.flush();
						sessionHQL.clear();
					}
				}
			}

			
			// Add TB_MED_BEDOCCUPANCY Data
			String sql3 = "SELECT * FROM TB_MED_BEDOCCUPANCY WHERE MEDICAL_UNIT='" + sus_no + "' ";
			resultSet2 = statement.executeQuery(sql3);
		
			while (resultSet2.next()) {
				Date bo_date = convertDateFormat_YYYY_DD_MM_to_DD_MM_YYYY(resultSet2.getString("BO_DATE"));
				String category = getCleanValue(resultSet2.getString("CATEGORY"), 30);
				String ward = getCleanValue(resultSet2.getString("WARD"), 30);
				
				List<Tb_Med_BedOccupancy> tb_med_bedOcc = msAccess.getCheckIfExitsBedOccup(sus_no,bo_date,category,ward);
				// as
				if (tb_med_bedOcc.size() == 0) {
				
					Date CREATED_ON = convertDateFormat_YYYY_DD_MM_to_DD_MM_YYYY(resultSet2.getString("CREATED_ON"));
					Date MODIFIED_ON = convertDateFormat_YYYY_DD_MM_to_DD_MM_YYYY(resultSet2.getString("MODIFIED_ON"));
					Date APPRVD_AT_UNIT_ON = convertDateFormat_YYYY_DD_MM_to_DD_MM_YYYY(
							resultSet2.getString("APPRVD_AT_UNIT_ON"));
					Date APPRVD_AT_MISO_ON = convertDateFormat_YYYY_DD_MM_to_DD_MM_YYYY(
							resultSet2.getString("APPRVD_AT_MISO_ON"));
					Date TIME_STAMP = convertDateFormat_YYYY_DD_MM_to_DD_MM_YYYY(resultSet2.getString("TIME_STAMP"));
					Tb_Med_BedOccupancy bedocc = new Tb_Med_BedOccupancy();
					if (resultSet2.getString("MEDICAL_UNIT") != "" && resultSet2.getString("MEDICAL_UNIT") != null) {
						bedocc.setSus_no(getCleanValue(resultSet2.getString("MEDICAL_UNIT"), 100));
					}
					if (bo_date != null) {
						bedocc.setBo_date(bo_date);
					} else {
						bedocc.setBo_date(null);
					}
					if (category != "" && category != null) {
						bedocc.setCategory(category);
					}
					if (ward != "" && ward != null) {
						bedocc.setWard(ward);
					}
					if (resultSet2.getString("BED_OCCUPIED") != "" && resultSet2.getString("BED_OCCUPIED") != null) {
						bedocc.setBed_occupied(getCleanValue(resultSet2.getString("BED_OCCUPIED"), 20));
					}
					if (resultSet2.getString("NEEDY_ADMISSION") != ""
							&& resultSet2.getString("NEEDY_ADMISSION") != null) {
						bedocc.setNeedy_admission(getCleanValue(resultSet2.getString("NEEDY_ADMISSION"), 20));
					}
					if (resultSet2.getString("NUMBER_REFUSED") != ""
							&& resultSet2.getString("NUMBER_REFUSED") != null) {
						bedocc.setNumber_refused(getCleanValue(resultSet2.getString("NUMBER_REFUSED"), 20));
					}
					if (resultSet2.getString("REFUSAL_REASON") != ""
							&& resultSet2.getString("REFUSAL_REASON") != null) {
						bedocc.setRefusal_reason(getCleanValue(resultSet2.getString("REFUSAL_REASON"), 20));
					}
					if (resultSet2.getString("REJECTION_REASON") != ""
							&& resultSet2.getString("REJECTION_REASON") != null) {
						bedocc.setRejection_reason(getCleanValue(resultSet2.getString("REFUSAL_REASON"), 20));
					}
					if (resultSet2.getString("RECORD_STATUS") != "" && resultSet2.getString("RECORD_STATUS") != null) {
						bedocc.setRecord_status(getCleanValue(resultSet2.getString("RECORD_STATUS"), 20));
					}
					if (CREATED_ON != null) {
						bedocc.setCreated_on(CREATED_ON);
					} else {
						bedocc.setCreated_on(null);
					}
					if (resultSet2.getString("CREATED_BY") != "" && resultSet2.getString("CREATED_BY") != null) {
						bedocc.setCreated_by(getCleanValue(resultSet2.getString("CREATED_BY"), 20));
					}
					if (MODIFIED_ON != null) {
						bedocc.setModified_on(MODIFIED_ON);
					} else {
						bedocc.setModified_on(null);
					}
					if (resultSet2.getString("MODIFIED_BY") != "" && resultSet2.getString("MODIFIED_BY") != null) {
						bedocc.setModified_by(getCleanValue(resultSet2.getString("MODIFIED_BY"), 20));
					}

					if (APPRVD_AT_UNIT_ON != null) {
						bedocc.setApprvd_at_unit_on(APPRVD_AT_UNIT_ON);
					} else {
						bedocc.setApprvd_at_unit_on(null);
					}
					if (resultSet2.getString("APPRVR_AT_UNIT_BY") != ""
							&& resultSet2.getString("APPRVR_AT_UNIT_BY") != null) {
						bedocc.setApprvr_at_unit_by(getCleanValue(resultSet2.getString("APPRVR_AT_UNIT_BY"), 20));
					}

					if (APPRVD_AT_MISO_ON != null) {
						bedocc.setApprvd_at_miso_on(APPRVD_AT_MISO_ON);
					} else {
						bedocc.setApprvd_at_miso_on(null);
					}

					if (resultSet2.getString("APPRVR_AT_MISO_BY") != ""
							&& resultSet2.getString("APPRVR_AT_MISO_BY") != null) {
						bedocc.setApprvr_at_miso_by(getCleanValue(resultSet2.getString("APPRVR_AT_MISO_BY"), 20));
					}

					if (TIME_STAMP != null) {
						bedocc.setTime_stamp(TIME_STAMP);
					} else {
						bedocc.setTime_stamp(null);
					}
					bedOccuInsertCount += 1;
					sessionHQL.save(bedocc);
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}
			tx.commit();
			model.put("msg", insertCount + " Data Saved and " + modifyCount + " Data still in Updated and "
					+ bedOccuInsertCount + " Bed Occupancy Saved for the Period " + frm_dt + " to " + to_dt);

		}

		// }catch(RuntimeException e){
		// tx.rollback();
		// 
		// model.put("msg", "Somthing error occure Data not Saved");
		// }finally{
		// sessionHQL.close();
		// }
		// } catch (SQLException sqlex){
		// model.put("msg", "Somthing error occure Data not Saved :" +
		// sqlex.getMessage());
		// }finally {
		statement.close();
		connection.close();
		// }
		return new ModelAndView("redirect:MsAccessDataUpload");
	}

	private String substring(String string, int i, int j) {
		return string.substring(i, j);
	}

	
	private static SimpleDateFormat inSDF = new SimpleDateFormat("MM-dd-yyyy");

	private Date convertDateFromate(String inDate) {
		
		inDate = inDate.replaceAll("/", "-");
		Date date = null;
		if (inDate != null) {
			try {
				date = inSDF.parse(inDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return date;
	}

	private Date convertDateFormat_YYYY_DD_MM_to_DD_MM_YYYY(String old_date) {
		Date new_date = null;
		if (old_date != null & !old_date.equals("")) {
			String[] d_array = substring(old_date, 0, 10).split("-");
			if (Integer.parseInt(d_array[2]) > 12) { // Date if Greter then 12
				old_date = d_array[0] + "-" + d_array[2] + "-" + d_array[1];
				
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
				
				try {
					new_date = formatter.parse(old_date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
			if (Integer.parseInt(d_array[2]) <= 12) { // Date if Greter then 12
				old_date = d_array[0] + "-" + d_array[2] + "-" + d_array[1];
				
			
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				
				try {
					new_date = formatter.parse(old_date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			
			}
		}
		
		return new_date;
	}

	private String getCleanValue(String value, int size) {
		String cleanValue = "NA";

		
		if (!value.equals("")) {
			cleanValue = value.toString().replace("'", " ").toString().replace(";", " ").toString().replace(":", " ")
					.toString().replace("<DIV>", " ").toString().replace("</DIV>", " ").toString().replace("<div>", " ")
					.toString().replace("</div>", " ").toString().replace("<u>", " ").toString().replace("</u>", " ")
					.toString().replace("<li>", " ").toString().replace("</li>", " ").toString().replace("&nbsp;", " ")
					.toString().replace("<br>", " ").toString().replace("</br>", " ").toString()
					.replace("<script>", " ").toString().replace("</script>", " ").toString().replace("<", " ")
					.toString().replace(">", " ").toString().replace("\n", " ").toString().replace("\\n", " ")
					.toString().replace("  ", " ").trim();

			if (size < cleanValue.length()) {
				cleanValue = substring(cleanValue, 0, size - 1);
			}
		}
		
		
		return cleanValue;
	}
}
