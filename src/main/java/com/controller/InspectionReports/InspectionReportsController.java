package com.controller.InspectionReports;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.BisagN.DateWithTimestamp.DateWithTimeStampController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.CheckFileFormatValidation;
import com.dao.InspectionReports.InspectionReportPhaseIIDao;
import com.dao.InspectionReports.InspectionReportsDao;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.model.InspectionReports.TB_DETAIL_COURSES;
import com.model.InspectionReports.TB_INSP_CELL_PHONE_LAPSES;
import com.model.InspectionReports.TB_INSP_EDUCATION_STANDARDS;
import com.model.InspectionReports.TB_INSP_EQUP_ANNUAL_METERAGE;
import com.model.InspectionReports.TB_INSP_ESPIONAGE_LAPSES;
import com.model.InspectionReports.TB_INSP_FATAL_INCIDENTS;
import com.model.InspectionReports.TB_INSP_FFRS;
import com.model.InspectionReports.TB_INSP_FINANCIAL_GRANTS;
import com.model.InspectionReports.TB_INSP_FS_SECURITY_LAPSES;
import com.model.InspectionReports.TB_INSP_LOST_CDS_DVDS;
import com.model.InspectionReports.TB_INSP_LOST_ID_ECR;
import com.model.InspectionReports.TB_INSP_OUTSIDE_ATTACHMENTS;
import com.model.InspectionReports.TB_INSP_OUTSTANDING_AUDIT;
import com.model.InspectionReports.TB_INSP_OUTSTANDING_LOSS_MT;
import com.model.InspectionReports.TB_INSP_OUTSTANDING_RENT_ALLIED;
import com.model.InspectionReports.TB_INSP_PIO_CALL_LAPSES;
import com.model.InspectionReports.TB_INSP_REGT_FUNDS;
import com.model.InspectionReports.TB_INSP_RPT_CLASSIFICATION_RANGES;
import com.model.InspectionReports.TB_INSP_SECURITY_LAPSES;
import com.model.InspectionReports.TB_INSP_SOCIAL_MEDIA_LAPSES;
import com.model.InspectionReports.TB_INSP_STANDARDS_ACHIEVED;
import com.model.InspectionReports.TB_INSP_TD_PROCEEDED;
import com.model.InspectionReports.TB_MISO_INSP_ANIMAL;
import com.model.InspectionReports.TB_MISO_INSP_BPET_RESULT;
import com.model.InspectionReports.TB_MISO_INSP_COURSES_CAT_A_B;
import com.model.InspectionReports.TB_MISO_INSP_DEFI_EQUP_EFFI;
import com.model.InspectionReports.TB_MISO_INSP_DTL_DEFFI_EXP_RESP_TO_TRAINNING;
import com.model.InspectionReports.TB_MISO_INSP_DTL_OF_EQUP_OFFROAD;
import com.model.InspectionReports.TB_MISO_INSP_DTL_OR_REQ_TRAINING;
import com.model.InspectionReports.TB_MISO_INSP_EMP_OF_UNIT_DURING_PERIOD;
import com.model.InspectionReports.TB_MISO_INSP_ESTABLISHMENT;
import com.model.InspectionReports.TB_MISO_INSP_LAND;
import com.model.InspectionReports.TB_MISO_INSP_MAIN_TBL;
import com.model.InspectionReports.TB_MISO_INSP_PPT_RESULT;
import com.model.InspectionReports.TB_MISO_INSP_PROMOTION_EXAM;
import com.model.InspectionReports.TB_MISO_INSP_REG_LANG_EXAM;
import com.model.InspectionReports.TB_MISO_INSP_STATE_FUNDS;
import com.model.InspectionReports.TB_MISO_INSP_SUMMARY_TECH;
import com.model.InspectionReports.TB_MISO_INSP_SUMMARY_TECH_OTHER;
import com.model.InspectionReports.TB_MISO_INSP_TRAINING_AMMUNITION;
import com.model.InspectionReports.TB_MISO_INSP_UPGRADATION;
import com.model.InspectionReports.TB_MISO_INSP_USER_REMARKS;
import com.model.InspectionReports.TB_MISO_INSP_WT_RESULT_OTHER;
import com.model.InspectionReports.TB_PSG_ANNUAL_REPORT;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class InspectionReportsController {

	@Autowired
	RoleBaseMenuDAO roledao;

	@Autowired
	InspectionReportsDao dao;

	@Autowired
	InspectionReportPhaseIIDao  dao2;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	String currentYear = yearFormat.format(new Date());

	@RequestMapping(value = "/admin/inspection_report", method = RequestMethod.GET)
	public ModelAndView inpection_report(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("inspection_report", roleid);
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		List<TB_MISO_INSP_MAIN_TBL> reports = dao.getinsp_main_table_data(roleSusNo, currentYear);
		if (reports != null && !reports.isEmpty()) {
			TB_MISO_INSP_MAIN_TBL report = reports.get(0);
			Mmap.addAttribute("report", report);
		}
		LocalDate today = LocalDate.now();
		Mmap.addAttribute("today", today.toString());
		Mmap.put("msg", msg);
		Mmap.put("isDownloadable",dao.checkDownload(roleSusNo, currentYear) );
		return new ModelAndView("Inspection_Report_tiles");
	}
	


	
	
	

	@RequestMapping(value = "/admin/inspection_app_report", method = RequestMethod.GET)
	public ModelAndView inspection_app_report(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("inspection_app_report", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleSusNo != "") {
			Mmap.put("sus_no", roleSusNo);
		}
		LocalDate today = LocalDate.now();
		List<TB_MISO_INSP_MAIN_TBL> reports = dao.getinsp_main_table_data(roleSusNo, currentYear);
		if (reports != null && !reports.isEmpty()) {
			TB_MISO_INSP_MAIN_TBL report = reports.get(0);
			Mmap.addAttribute("report", report);
		}
		
		Mmap.addAttribute("today", today.toString());
		Mmap.put("msg", msg);
		Mmap.put("isDownloadable",dao.checkDownload(roleSusNo, currentYear) );
		
		return new ModelAndView("Inspection_app_Report_tiles");
	}

	// ------------------------- Start establishment
	// ---------------------------------------------------------------/

	@RequestMapping(value = "/establishment_report_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> establishment_report(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getEstablishmentData(session, request);
		return result;
	}



public boolean establishmentSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_ESTABLISHMENT WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("delete FROM TB_MISO_INSP_ESTABLISHMENT WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}

			EstablishmentSurDef establishmentSurDef = calculateSurplusDeficit(request.getParameter("authoffi"), request.getParameter("postoffi"),request.getParameter("authjco") ,request.getParameter("postjco") ,
					request.getParameter("author"), request.getParameter("postor"),request.getParameter("authwarrant") ,
					request.getParameter("postwarrant"), request.getParameter("authciv"),request.getParameter("postciv") );
			TB_MISO_INSP_ESTABLISHMENT establishment = new TB_MISO_INSP_ESTABLISHMENT();
			establishment.setSus_no(roleSusNo);
			establishment.setReg_offrs_auth(request.getParameter("regular_officers_auth"));
			establishment.setReg_offrs_posted(request.getParameter("regular_officers_posted"));

			String surplusDeficiency = request.getParameter("regular_officers_surplus");
			if (surplusDeficiency != null && !surplusDeficiency.isEmpty()) {
				String[] parts = surplusDeficiency.split("/");
				establishment.setReg_offrs_sur(parts.length > 0 ? parts[0] : "");
				establishment.setReg_offrs_defi(parts.length > 1 ? parts[1] : "");
			}


			 establishment.setReg_offrs_auth(request.getParameter("regular_officers_auth"));
		     establishment.setReg_offrs_posted(request.getParameter("regular_officers_posted"));
		        //establishment.setReg_offrs_sur(request.getParameter("regular_officers_surplus")); 
		       // establishment.setReg_offrs_defi(request.getParameter("regular_officers_deficit")); 
		    establishment.setReg_offrs_medcat(request.getParameter("regular_officers_medical"));

			establishment.setReg_jco_auth(request.getParameter("regular_jcos_auth"));
			establishment.setReg_jco_posted(request.getParameter("regular_jcos_posted"));
			//establishment.setReg_jco_sur(request.getParameter("regular_jcos_surplus"));
			//establishment.setReg_jco_defi(request.getParameter("regular_jcos_deficit"));			
			establishment.setReg_jco_medcat(request.getParameter("regular_jcos_medical"));

			establishment.setReg_or_auth(request.getParameter("regular_or_auth"));
			establishment.setReg_or_posted(request.getParameter("regular_or_posted"));
			// establishment.setReg_or_sur(request.getParameter("regular_or_surplus"));
			// establishment.setReg_or_defi(request.getParameter("regular_or_deficit"));
			establishment.setReg_or_medcat(request.getParameter("regular_or_medical"));

			establishment.setReg_civ_auth(request.getParameter("regular_civilians_auth"));
			establishment.setReg_civ_posted(request.getParameter("regular_civilians_posted"));
			///  establishment.setReg_civ_sur(request.getParameter("regular_civilians_surplus"));
			//  establishment.setReg_civ_defi(request.getParameter("regular_civilians_deficit"));
			establishment.setReg_civ_medcat(request.getParameter("regular_civilians_medical"));

			establishment.setReg_warrant_auth(request.getParameter("regular_warrant_auth"));
			establishment.setReg_warrant_posted(request.getParameter("regular_warrant_posted"));
			//  establishment.setReg_warrant_sur(request.getParameter("regular_warrant_surplus"));
			// establishment.setReg_warrant_defi(request.getParameter("regular_warrant_deficit"));
			establishment.setReg_warrant_medcat(request.getParameter("regular_warrant_medical"));

			// Attached Personnel
			establishment.setAtted_offrs_auth(request.getParameter("authoffi"));
			establishment.setAtted_offrs_posted(request.getParameter("postoffi"));
			establishment.setAtted_offrs_sur(establishmentSurDef.off_sur);
			establishment.setAtted_offrs_defi(establishmentSurDef.off_def);
			establishment.setAtted_offrs_medcat(request.getParameter("medcat_offi"));

			establishment.setAtted_jco_auth(request.getParameter("authjco"));
			establishment.setAtted_jco_posted(request.getParameter("postjco"));
			establishment.setAtted_jco_sur( establishmentSurDef.jco_sur);
			establishment.setAtted_jco_defi( establishmentSurDef.jco_def);
			establishment.setAtted_jco_medcat(request.getParameter("medcat_jco"));

			establishment.setAtted_or_auth(request.getParameter("author"));
			establishment.setAtted_or_posted(request.getParameter("postor"));
			establishment.setAtted_or_sur(establishmentSurDef.or_sur);
			establishment.setAtted_or_defi(establishmentSurDef.or_def);
			establishment.setAtted_or_medcat(request.getParameter("medcat_or"));

			establishment.setAtted_civ_auth(request.getParameter("authciv"));
			establishment.setAtted_civ_posted(request.getParameter("postciv"));
			establishment.setAtted_civ_sur(establishmentSurDef.civ_sur);
			establishment.setAtted_civ_defi(establishmentSurDef.civ_def);
			establishment.setAtted_civ_medcat(request.getParameter("medcat_civ"));

			establishment.setAtted_warrant_auth(request.getParameter("authwarrant"));
			establishment.setAtted_warrant_posted(request.getParameter("postwarrant"));
			establishment.setAtted_warrant_sur(establishmentSurDef.warrant_sur);
			establishment.setAtted_warrant_defi(establishmentSurDef.warrant_def);
			establishment.setAtted_warrant_medcat(request.getParameter("medcat_warrant"));
			establishment.setYear(currentYear);
			establishment.setStatus(status);
			establishment.setCreated_by(username);
			establishment.setCreated_date(new Date());

			ses1.save(establishment);
			success = true;

			String remarks = request.getParameter("user_remarks1");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("establishment_item", remarks,roleSusNo, username);
			}

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

	private class EstablishmentSurDef {
		String off_sur;
		String off_def;
		String jco_sur;
		String jco_def;
		String or_sur;
		String or_def;
		String warrant_sur;
		String warrant_def;
		String civ_sur;
		String civ_def;
	}

	private EstablishmentSurDef calculateSurplusDeficit(String authoffi, String postoffi, String authjco,
			String postjco, String author, String postor, String authwarrant, String postwarrant, String authciv,
			String postciv) {
		EstablishmentSurDef establishmentSurDef = new EstablishmentSurDef();

		int officer = (postoffi != null && !postoffi.isEmpty() && authoffi != null && !authoffi.isEmpty())
				? Integer.parseInt(postoffi) - Integer.parseInt(authoffi)
				: 0;
		if (officer > 0) {
			establishmentSurDef.off_sur = String.valueOf(officer);
			establishmentSurDef.off_def = "0";
		} else if (officer < 0) {
			establishmentSurDef.off_def = String.valueOf(Math.abs(officer));
			establishmentSurDef.off_sur = "0";
		} else {
			establishmentSurDef.off_sur = "0";
			establishmentSurDef.off_def = "0";
		}

		int jco = (postjco != null && !postjco.isEmpty() && authjco != null && !authjco.isEmpty())
				? Integer.parseInt(postjco) - Integer.parseInt(authjco)
				: 0;
		if (jco > 0) {
			establishmentSurDef.jco_sur = String.valueOf(jco);
			establishmentSurDef.jco_def = "0";
		} else if (jco < 0) {
			establishmentSurDef.jco_def = String.valueOf(Math.abs(jco));
			establishmentSurDef.jco_sur = "0";
		} else {
			establishmentSurDef.jco_sur = "0";
			establishmentSurDef.jco_def = "0";
		}

		int or = (postor != null && !postor.isEmpty() && author != null && !author.isEmpty())
				? Integer.parseInt(postor) - Integer.parseInt(author)
				: 0;
		if (or > 0) {
			establishmentSurDef.or_sur = String.valueOf(or);
			establishmentSurDef.or_def = "0";
		} else if (or < 0) {
			establishmentSurDef.or_def = String.valueOf(Math.abs(or));
			establishmentSurDef.or_sur = "0";
		} else {
			establishmentSurDef.or_sur = "0";
			establishmentSurDef.or_def = "0";
		}

		int warrant = (postwarrant != null && !postwarrant.isEmpty() && authwarrant != null && !authwarrant.isEmpty())
				? Integer.parseInt(postwarrant) - Integer.parseInt(authwarrant)
				: 0;
		if (warrant > 0) {
			establishmentSurDef.warrant_sur = String.valueOf(warrant);
			establishmentSurDef.warrant_def = "0";
		} else if (warrant < 0) {
			establishmentSurDef.warrant_def = String.valueOf(Math.abs(warrant));
			establishmentSurDef.warrant_sur = "0";
		} else {
			establishmentSurDef.warrant_sur = "0";
			establishmentSurDef.warrant_def = "0";
		}

		int civ = (postciv != null && !postciv.isEmpty() && authciv != null && !authciv.isEmpty())
				? Integer.parseInt(postciv) - Integer.parseInt(authciv)
				: 0;
		if (civ > 0) {
			establishmentSurDef.civ_sur = String.valueOf(civ);
			establishmentSurDef.civ_def = "0";
		} else if (civ < 0) {
			establishmentSurDef.civ_def = String.valueOf(Math.abs(civ));
			establishmentSurDef.civ_sur = "0";
		} else {
			establishmentSurDef.civ_sur = "0";
			establishmentSurDef.civ_def = "0";
		}

		return establishmentSurDef;
	}

	// ----------------------------------------start
	// EQUIPMENT-------------------------------//
	@RequestMapping(value = "/EQUIPMENT_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> EQUIPMENT_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		result = dao.getEquiment_Data(session, request);
		return result;
	}

	private void saveEquipmentTbleRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) {
		TB_INSP_EQUP_ANNUAL_METERAGE record = new TB_INSP_EQUP_ANNUAL_METERAGE();
		record.setType_of_duty(allParams.get("type_of_duty"));
		record.setEqup_authorize(allParams.get("equp_authorize"));
		record.setEqup_cove(allParams.get("equp_cove"));
		record.setEqup_remark(allParams.get("equp_remark"));
		record.setCreated_by(username);
		record.setCreated_date(new Date());
		record.setSus_no(roleSusNo);
		record.setYear(currentYear);
		record.setStatus(0);
		session.save(record);
	}

	// ------------------------------- start
	// animal---------------------------------//

	@RequestMapping(value = "/Animals_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Animals_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		result = dao.getAnimal_Data(session, request);
		return result;
	}

	private boolean animalSaveAction(HttpSession session, HttpServletRequest request, String animalDataJson) {
		boolean save = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		try {
			if (animalDataJson != null && !animalDataJson.isEmpty()) {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Map<String, String>> animalDataList = objectMapper.readValue(animalDataJson,
						new TypeReference<List<Map<String, String>>>() {
						});

				for (Map<String, String> data : animalDataList) {
					String type = data.get("type");
					String hql = "FROM TB_MISO_INSP_ANIMAL " + "WHERE sus_no = :susNo " + "AND type = :type "
							+ "AND year = :year " + "AND status = 0";
					Query query = ses1.createQuery(hql);
					query.setParameter("susNo", roleSusNo);
					query.setParameter("type", type);
					query.setParameter("year", currentYear);

					List<?> results = query.list();

					TB_MISO_INSP_ANIMAL animalEntity;

					if (!results.isEmpty()) {
						animalEntity = (TB_MISO_INSP_ANIMAL) results.get(0);
					} else {
						animalEntity = new TB_MISO_INSP_ANIMAL();
						animalEntity.setSus_no(roleSusNo);
						animalEntity.setType(type);
						animalEntity.setStatus(status);
						animalEntity.setYear(currentYear);
						animalEntity.setCreated_date(new Date());
						animalEntity.setCreated_by(username);
					}
					animalEntity.setAuth(data.get("authorised"));
					animalEntity.setHeld(data.get("held"));

					String surplusDeficiency = data.get("surplusDeficiency");
					if (surplusDeficiency != null && !surplusDeficiency.isEmpty()) {
						String[] parts = surplusDeficiency.split("/");
						animalEntity.setSur(parts.length > 0 ? parts[0] : "");
						animalEntity.setDefi(parts.length > 1 ? parts[1] : "");
					}

					animalEntity.setConditon(data.get("condition"));
					animalEntity.setTeatment(data.get("treatment"));
					animalEntity.setGrooming(data.get("grooming"));
					animalEntity.setFeeding(data.get("feeding"));
					animalEntity.setWatering(data.get("watering"));
					animalEntity.setClipping(data.get("clipping"));
					animalEntity.setF_feet(data.get("feet"));
					animalEntity.setSaddlery(data.get("saddlery"));
					animalEntity.setLine_gear(data.get("line_gear"));
					animalEntity.setAccomodation(data.get("accomodation"));
					animalEntity.setRemarks(data.get("remarks"));
					ses1.saveOrUpdate(animalEntity);
				}

				String remarks = request.getParameter("user_remarks3");
				if (!remarks.equals("") && !remarks.isEmpty()) {
					addUserRemarks("animals_item", remarks, roleSusNo, username);
				}

				t2.commit();
				save = true;
			} else {
				System.out.println("No animal data received.");
			}

		} catch (Exception e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Error saving/updating animal data: " + e.getMessage());
			e.printStackTrace();
			save = false;
		} finally {
			if (ses1 != null && ses1.isOpen()) {
				ses1.close();
			}
		}
		return save;
	}

	// ------------------------- Deficiencies of Equipment/Stores including Arms/
	// Ammunition Affecting Maintenance Efficiency

	public boolean detailEqupSaveAction2(HttpSession session, HttpServletRequest request, String inspDataJson) {
		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String username = (String) session.getAttribute("username");
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		Integer status_rolewise = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery(
					"update TB_MISO_INSP_DEFI_EQUP_EFFI set status =:status_rolewise  WHERE sus_no = :sus_no and year =:year and status=:status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setInteger("status", 0);
			q1.setInteger("status_rolewise", status_rolewise);
			int rowsAffected = q1.executeUpdate();
			if (rowsAffected > 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks4");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("deficiencies_of_equipment2_item", remarks, roleSusNo, username);
			}

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

	private void saveDefOfEqupMaintenanceRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) {
		TB_MISO_INSP_DEFI_EQUP_EFFI record = new TB_MISO_INSP_DEFI_EQUP_EFFI();
		record.setNomenclature(allParams.get("nomentclature"));
		record.setA(allParams.get("au_id"));
		record.setAuth(allParams.get("qunt_auth"));
		record.setHeld(allParams.get("qunt_held"));
		record.setDefi(allParams.get("qunt_defi"));
		record.setDues_in(allParams.get("due_in"));
		record.setDues_out(allParams.get("due_out"));
		record.setRemarks(allParams.get("remark"));
		record.setSus_no(roleSusNo);
		record.setCreated_by(username);
		record.setStatus(0);
		record.setYear(currentYear);
		record.setCreated_date(new Date());
		session.save(record);
	}

	// ----------------------------- Details of Equipment Rendered Off Road for a
	// prolonged period (more than Six Months) --

	@RequestMapping(value = "/Deficiencies_of_Equipment_url", method = RequestMethod.POST)
	public       @ResponseBody   ArrayList<ArrayList<String>> Deficiencies_of_Equipment_url(String unit_no,HttpSession session,HttpServletRequest request) {
	ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>(); 
	result=dao.getDeficiencies_of_Equipment_url_Data(session,request); 
	return result;
	}

	private boolean detailEqupSaveAction(HttpSession session, HttpServletRequest request, String inspDataJson) {
		boolean save = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		try {
			if (inspDataJson != null && !inspDataJson.isEmpty()) {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Map<String, String>> inspDataList = objectMapper.readValue(inspDataJson,
						new TypeReference<List<Map<String, String>>>() {
						});

				for (Map<String, String> data : inspDataList) {
					String nomenclature = data.get("noment");
					String au = data.get("au");
					String auth = data.get("auth");
					String held = data.get("held");
					String defi = data.get("defi");

					String hql = "FROM TB_MISO_INSP_DTL_OF_EQUP_OFFROAD " + "WHERE sus_no = :susNo "
							+ "AND nomenclature = :nomenclature " + "AND au = :au " + "AND auth = :auth "
							+ "AND held = :held " + "AND defi = :defi " + "AND status = :status " + "AND year = :year ";

					Query query = ses1.createQuery(hql);
					query.setParameter("susNo", roleSusNo);
					query.setParameter("nomenclature", nomenclature);
					query.setParameter("au", au);
					query.setParameter("auth", auth);
					query.setParameter("held", held);
					query.setParameter("defi", defi);
					query.setParameter("status", 0);
					query.setParameter("year", currentYear);

					List<?> results = query.list();

					TB_MISO_INSP_DTL_OF_EQUP_OFFROAD equpEntity;

					if (!results.isEmpty()) {
						equpEntity = (TB_MISO_INSP_DTL_OF_EQUP_OFFROAD) results.get(0);
					} else {
						equpEntity = new TB_MISO_INSP_DTL_OF_EQUP_OFFROAD();
						equpEntity.setSus_no(roleSusNo);
						equpEntity.setNomenclature(nomenclature);
						equpEntity.setAu(au);
						equpEntity.setAuth(auth);
						equpEntity.setHeld(held);
						equpEntity.setDefi(defi);
						equpEntity.setYear(currentYear);
						equpEntity.setStatus(status);
						equpEntity.setCreated_date(new Date());
						equpEntity.setCreated_by(username);
					}

					equpEntity.setOffroad_reason(data.get("offRoadReson"));
					equpEntity.setAction_taken_unit(data.get("actionTakeByUnit"));
					equpEntity.setRemarks(data.get("remarks"));

					ses1.saveOrUpdate(equpEntity);
				}

				String remarks = request.getParameter("user_remarks5");
				if (!remarks.equals("") && !remarks.isEmpty()) {
					addUserRemarks("deficiencies_of_equipment_item", remarks, roleSusNo, username);
				}

				t2.commit();
				save = true;
			} else {
				System.out.println("No equipment detail data received.");
			}

		} catch (Exception e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Error saving/updating equipment detail data: " + e.getMessage());
			e.printStackTrace();
			save = false;
		} finally {
			if (ses1 != null && ses1.isOpen()) {
				ses1.close();
			}
		}
		return save;
	}

	// ----State of Sector Stores, Maintenance Works Stores, Stores purchased out of
	// SAG, ACSFP Fund and Other Funds
	@RequestMapping(value = "/State_of_Sector_Stores_url", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> stateOfSectorStoresUrl(
			@RequestParam(value = "unit_no", required = false) String unitNo, // use @RequestParam
			HttpSession session, HttpServletRequest request) {
		List<Map<String, String>> result = dao.stateOfSectorStoresGetData(session, request);
		return result;
	}

	private boolean stateOfSectorSaveAction(HttpSession session, HttpServletRequest request, String inspDataJson) {
		boolean save = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		try {
			if (inspDataJson != null && !inspDataJson.isEmpty()) {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Map<String, String>> inspDataList = objectMapper.readValue(inspDataJson,
						new TypeReference<List<Map<String, String>>>() {
						});

				for (Map<String, String> data : inspDataList) {
					String nomenclature = data.get("nomenclature");

					String hql = "FROM TB_MISO_INSP_STATE_FUNDS " + "WHERE sus_no = :susNo "
							+ "AND nomenclature = :nomenclature " + "AND year = :year" + " AND status = :status ";

					Query query = ses1.createQuery(hql);
					query.setParameter("susNo", roleSusNo);
					query.setParameter("nomenclature", nomenclature);
					query.setParameter("year", currentYear);
					query.setParameter("status", 0);

					List<?> results = query.list();

					TB_MISO_INSP_STATE_FUNDS examEntity;

					if (!results.isEmpty()) {
						examEntity = (TB_MISO_INSP_STATE_FUNDS) results.get(0);
					} else {
						examEntity = new TB_MISO_INSP_STATE_FUNDS();
						examEntity.setSus_no(roleSusNo);
						examEntity.setNomenclature(nomenclature);
						examEntity.setYear(currentYear);
						examEntity.setStatus(status);
						examEntity.setCreated_date(new Date());
						examEntity.setCreated_by(username);
					}

					examEntity.setAu(data.get("au"));
					examEntity.setAu(data.get("auth"));
					examEntity.setHeld(data.get("held"));
					examEntity.setDefi(data.get("defi"));
					examEntity.setSer_unser(data.get("ser_unser"));
					examEntity.setReason_offrd(data.get("reason_offrd"));
					examEntity.setRemarks(data.get("remarks"));

					ses1.saveOrUpdate(examEntity);
				}

				String remarks = request.getParameter("user_remarks12");
				if (!remarks.equals("") && !remarks.isEmpty()) {
					addUserRemarks("state_of_sector_stores_item", remarks, roleSusNo, username);
				}

				t2.commit();
				save = true;
			}

		} catch (Exception e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
			save = false;
		} finally {
			if (ses1 != null && ses1.isOpen()) {
				ses1.close();
			}
		}
		return save;
	}

	// ---------------------------WT RESULTS----------------------

	@RequestMapping(value = "/wt_result_insp_Data_Url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> wt_result_insp_Data_Url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		result = dao.getWTResultData(session, request);
		return result;
	}

	@RequestMapping(value = "/wt_result_insp_Data_other_Url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> wt_result_insp_Data_other_Url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		result = dao.getwtResultOtherData(session, request);
		return result;
	}

	private boolean wtResultInspSaveAction(HttpSession session, HttpServletRequest request, String inspDataJson) {
		boolean save = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();

		try {
			if (inspDataJson != null && !inspDataJson.isEmpty()) {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Map<String, String>> inspDataList = objectMapper.readValue(inspDataJson,
						new TypeReference<List<Map<String, String>>>() {
						});

				for (Map<String, String> data : inspDataList) {
					String category = data.get("category");
					String weapons = data.get("weapons");
					String hql = "FROM TB_MISO_INSP_WT_RESULT_OTHER " + "WHERE sus_no = :susNo "
							+ "AND category = :category " + "AND year = :year";

					Query query = ses1.createQuery(hql);
					query.setParameter("susNo", roleSusNo);
					query.setParameter("category", category);
					query.setParameter("year", currentYear);

					List<?> results = query.list();

					TB_MISO_INSP_WT_RESULT_OTHER wtResultEntity;

					if (!results.isEmpty()) {
						wtResultEntity = (TB_MISO_INSP_WT_RESULT_OTHER) results.get(0);
					} else {
						wtResultEntity = new TB_MISO_INSP_WT_RESULT_OTHER();
						wtResultEntity.setSus_no(roleSusNo);
						wtResultEntity.setCategory(category);
						wtResultEntity.setYear(currentYear);
						wtResultEntity.setStatus(status);
						wtResultEntity.setCreated_date(new Date());
						wtResultEntity.setCreated_by(username);
					}
					wtResultEntity.setCategory(category);
					wtResultEntity.setWeapons(weapons);
					wtResultEntity.setTotal_no(data.get("total_no"));
					wtResultEntity.setMarks_men(data.get("marks_men"));
					wtResultEntity.setFirst_class(data.get("first_class"));
					wtResultEntity.setStandard_shot(data.get("standard_shot"));
					wtResultEntity.setFailed(data.get("failed"));
					wtResultEntity.setNumber_exempted(data.get("number_exempted"));
					wtResultEntity.setNumber_yeto_fire(data.get("number_yeto_fire"));

					ses1.saveOrUpdate(wtResultEntity);
					dao.wtResultOffrsJcoOrSaveAction(session, request);
				}

				String remarks = request.getParameter("user_remarks11");
				if (!remarks.equals("") && !remarks.isEmpty()) {
					addUserRemarks("wt_results_item", remarks, roleSusNo, username);
				}

				t2.commit();
				save = true;
			} else {
				System.out.println("No Summary Tech data received.");
			}

		} catch (Exception e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Error saving/updating Summary Tech data: " + e.getMessage());
			e.printStackTrace();
			save = false;
		} finally {
			if (ses1 != null && ses1.isOpen()) {
				ses1.close();
			}
		}
		return save;
	}

	// --------------------------------Education Standards----------------
	@RequestMapping(value = "/Education_Standards_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Education_Standards_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getEducationStandardsData(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/EducationStandardsAction", method = RequestMethod.POST)
	public ModelAndView EducationStandardsAction(
			@ModelAttribute("EducationStandardsCmd") TB_INSP_EDUCATION_STANDARDS obj1, HttpServletRequest request,
			ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg)
			throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("inspection_report", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String jco_affected = request.getParameter("jco_affected");
		String jco_qualified = request.getParameter("jco_qualified");
		String jco_not_qualified = request.getParameter("jco_not_qualified");
		String jco_map = request.getParameter("jco_map");
		String jco_education = request.getParameter("jco_education");
		String jco_promotion = request.getParameter("jco_promotion");

		String nco_affected = request.getParameter("nco_affected");
		String nco_qualified = request.getParameter("nco_qualified");
		String nco_not_qualified = request.getParameter("nco_not_qualified");
		String nco_map = request.getParameter("nco_map");
		String nco_education = request.getParameter("nco_education");
		String nco_promotion = request.getParameter("nco_promotion");

		String or_affected = request.getParameter("or_affected");
		String or_qualified = request.getParameter("or_qualified");
		String or_not_qualified = request.getParameter("or_not_qualified");
		String or_map = request.getParameter("or_map");
		String or_education = request.getParameter("or_education");
		String or_promotion = request.getParameter("or_promotion");

		String total_affected = request.getParameter("total_affected");
		String total_qualified = request.getParameter("total_qualified");
		String total_not_qualified = request.getParameter("total_not_qualified");
		String total_map = request.getParameter("total_map");
		String total_education = request.getParameter("total_education");
		String total_promotion = request.getParameter("total_promotion");

		String username = session.getAttribute("username").toString();
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			TB_INSP_EDUCATION_STANDARDS obj = new TB_INSP_EDUCATION_STANDARDS();
			obj.setJco_affected(jco_affected);
			obj.setJco_qualified(jco_qualified);
			obj.setJco_not_qualified(jco_not_qualified);
			obj.setJco_map(jco_map);
			obj.setJco_education(jco_education);
			obj.setJco_promotion(jco_promotion);

			obj.setNco_affected(nco_affected);
			obj.setNco_qualified(nco_qualified);
			obj.setNco_not_qualified(nco_not_qualified);
			obj.setNco_map(nco_map);
			obj.setNco_education(nco_education);
			obj.setNco_promotion(nco_promotion);

			obj.setOr_affected(or_affected);
			obj.setOr_qualified(or_qualified);
			obj.setOr_not_qualified(or_not_qualified);
			obj.setOr_map(or_map);
			obj.setOr_education(or_education);
			obj.setOr_promotion(or_promotion);

			obj.setTotal_affected(total_affected);
			obj.setTotal_qualified(total_qualified);
			obj.setTotal_not_qualified(total_not_qualified);
			obj.setTotal_map(total_map);
			obj.setTotal_education(total_education);
			obj.setTotal_promotion(total_promotion);
			obj.setSus_no(roleSusNo);
			obj.setCreated_by(username);
			obj.setStatus(status);
			obj.setCreated_date(new Date());
			obj.setYear(currentYear);
			sessionHQL.save(obj);
			updateMainTblData("education_standards_item", roleSusNo, roleType);
			sessionHQL.flush();
			sessionHQL.clear();
			model.put("msg", "Data Saved Successfully.");

			String remarks = request.getParameter("user_remarks15");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("education_standards_item", remarks, roleSusNo, username);
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:inspection_report");
	}

	// ---------------------------------Availability of Personnel Trained on Courses
	// at Category 'A' and Category 'B' Establishments
	private void savePersonalTrainedCoursesRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) {
		TB_MISO_INSP_COURSES_CAT_A_B record = new TB_MISO_INSP_COURSES_CAT_A_B();
		record.setCourse_name(allParams.get("courseName"));
		record.setOfficers_period(allParams.get("officersTrained"));
		record.setJcos_period(allParams.get("jcosTrained"));
		record.setNcos_period(allParams.get("ncosTrained"));
		record.setOr_period(allParams.get("orTrained"));
		record.setOfficers_preceding(allParams.get("officersAvailable"));
		record.setJcos_preceding(allParams.get("jcosAvailable"));
		record.setNcos_preceding(allParams.get("ncosAvailable"));
		record.setOr_preceding(allParams.get("orAvailable"));
		record.setTotalofficers(allParams.get("totalOfficers"));
		record.setTotaljcos(allParams.get("totalJcos"));
		record.setTotalncos(allParams.get("totalNcos"));
		record.setTotalor(allParams.get("totalOr"));
		record.setSus_no(roleSusNo);
		record.setStatus(0);
		record.setYear(currentYear);
		record.setCreated_by(username);
		record.setCreated_date(new Date());
		session.save(record);
	}

	public boolean category_itemSaveAction(HttpSession session, HttpServletRequest request, String inspDataJson) {
		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		Integer status_rolewise = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery(
					"update TB_MISO_INSP_COURSES_CAT_A_B set status =:status_rolewise  WHERE sus_no = :sus_no and year =:year and status=:status"); // Update
																																					// to
																																					// 1
																																					// not
																																					// buttonContext
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setInteger("status", 0);
			q1.setInteger("status_rolewise", status_rolewise);
			int rowsAffected = q1.executeUpdate();
			if (rowsAffected > 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks16");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("category_item", remarks, roleSusNo, username);
			}

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

	// ------------------------start regimental language exam --------------

	@RequestMapping(value = "/regi_language_exam_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> regi_language_exam_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getRegLanguageExamData(session, request);
		return result;
	}

	public boolean regLanguageExamSaveAction(HttpSession session, HttpServletRequest request) {
		String username = (String) session.getAttribute("username");
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			String officersQualified = request.getParameter("officersQualified");
			String numbersExempted = request.getParameter("numbersExempted");
			String qualifiedDuringPeriod = request.getParameter("qualifiedDuringPeriod");
			String notYetQualified = request.getParameter("notYetQualified");

			String hql = "FROM TB_MISO_INSP_REG_LANG_EXAM " + "WHERE year = :year "
					+ "AND sus_no = :susNo AND status=:status";

			Query query = ses1.createQuery(hql);
			query.setParameter("year", currentYear);
			query.setParameter("susNo", roleSusNo);
			query.setParameter("status", 0);

			List<?> results = query.list();

			TB_MISO_INSP_REG_LANG_EXAM exam;
			if (!results.isEmpty()) {
				exam = (TB_MISO_INSP_REG_LANG_EXAM) results.get(0);
			} else {
				exam = new TB_MISO_INSP_REG_LANG_EXAM();
				exam.setSus_no(roleSusNo);
				exam.setYear(currentYear);
				exam.setStatus(status);
				exam.setCreated_by(username);
				exam.setCreated_date(new Date());

			}

			exam.setOfficersQualified(officersQualified);
			exam.setNumbersExempted(numbersExempted);
			exam.setQualifiedDuringPeriod(qualifiedDuringPeriod);
			exam.setNotYetQualified(notYetQualified);

			ses1.saveOrUpdate(exam);
			success = true;

			String remarks = request.getParameter("user_remarks6");
			System.err.println("user remark1 " + remarks);
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("regi_language_exam_item", remarks, roleSusNo, username);
			}

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

	// ---------------------------------------end regimental language exam
	// --------------

	// ---------------------------------start promotion
	// exma------------------------------

	@RequestMapping(value = "/Promotion_Exam_Officers_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Promotion_Exam_Officers_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		result = dao.getPromoExamData(session, request);
		return result;
	}

	@RequestMapping(value = "/classfication_save_url", method = RequestMethod.POST)
	public @ResponseBody String classfication_save_url(HttpServletRequest request, ModelMap model, HttpSession session)
			throws ParseException {

		String msg = "";
		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String number_of_ranges = request.getParameter("number_of_ranges");
		String when_used = request.getParameter("when_used");
		String difficulties_experienced = request.getParameter("difficulties_experienced");
		Date when_used_date = null;

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		if (when_used != null && !when_used.equals("") && !when_used.equals("DD/MM/YYYY")) {
			when_used_date = format.parse(when_used);
		}

		try {

			TB_INSP_RPT_CLASSIFICATION_RANGES save = new TB_INSP_RPT_CLASSIFICATION_RANGES();
			save.setDifficulties_experienced(difficulties_experienced);
			save.setNumber_of_ranges(number_of_ranges);
			save.setSus_no(roleSusNo);
			save.setWhen_used(when_used_date);
			sessionHQL.save(save);
			sessionHQL.flush();
			sessionHQL.clear();
			msg = "Data Saved Successfully.";

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "roll back transaction";
			} catch (RuntimeException rbe) {
				msg = "Couldn't roll back transaction";

			}
			throw e;

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		model.put("msg", msg);
		return msg;
	}

	private boolean pramotionExamSaveAction(HttpSession session, HttpServletRequest request, String inspDataJson) {
		boolean save = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		try {
			if (inspDataJson != null && !inspDataJson.isEmpty()) {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Map<String, String>> inspDataList = objectMapper.readValue(inspDataJson,
						new TypeReference<List<Map<String, String>>>() {
						});

				for (Map<String, String> data : inspDataList) {
					String examType = data.get("exam_type");

					String hql = "FROM TB_MISO_INSP_PROMOTION_EXAM " + "WHERE sus_no = :susNo "
							+ "AND type_of_exam = :examType " + "AND year = :year AND status = :status";

					Query query = ses1.createQuery(hql);
					query.setParameter("susNo", roleSusNo);
					query.setParameter("examType", examType);
					query.setParameter("year", currentYear);
					query.setParameter("status", 0);

					List<?> results = query.list();

					TB_MISO_INSP_PROMOTION_EXAM examEntity;

					if (!results.isEmpty()) {
						examEntity = (TB_MISO_INSP_PROMOTION_EXAM) results.get(0);
					} else {
						examEntity = new TB_MISO_INSP_PROMOTION_EXAM();
						examEntity.setSus_no(roleSusNo);
						examEntity.setType_of_exam(examType);
						examEntity.setYear(currentYear);
						examEntity.setStatus(status);
						examEntity.setCreated_date(new Date());
						examEntity.setCreated_by(username);
					}

					examEntity.setNumber_appear(data.get("number_appeared"));
					examEntity.setNumber_successful(data.get("number_successful"));
					examEntity.setNumber_yet_to_appear(data.get("number_yet_to_appear"));
					examEntity.setRemarks(data.get("remarks"));

					ses1.saveOrUpdate(examEntity);
				}

				String remarks = request.getParameter("user_remarks9");
				if (!remarks.equals("") && !remarks.isEmpty()) {
					addUserRemarks("promotion_exam_officers_item", remarks, roleSusNo, username);
				}

				t2.commit();
				save = true;
			} else {
				System.out.println("No Promotion Exam data received.");
			}

		} catch (Exception e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Error saving/updating Promotion Exam data: " + e.getMessage());
			e.printStackTrace();
			save = false;
		} finally {
			if (ses1 != null && ses1.isOpen()) {
				ses1.close();
			}
		}
		return save;
	}

	// ---------------------------end promotion exma------------------------------

	// -------------------start bpet-------------------------------
	@RequestMapping(value = "/bpet_get_data_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> bpet_get_data_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		result = dao.getBPETResultData(session, request);
		return result;
	}

	private boolean bpetSaveAction(HttpSession session, HttpServletRequest request, String inspDataJson) {
		boolean save = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		try {
			if (inspDataJson != null && !inspDataJson.isEmpty()) {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Map<String, String>> inspDataList = objectMapper.readValue(inspDataJson,
						new TypeReference<List<Map<String, String>>>() {
						});

				for (Map<String, String> data : inspDataList) {
					String personnel = data.get("personnel");

					String hql = "FROM TB_MISO_INSP_BPET_RESULT " + "WHERE sus_no = :susNo "
							+ "AND personnel = :personnel " + "AND year = :year AND status= :status ";

					Query query = ses1.createQuery(hql);
					query.setParameter("susNo", roleSusNo);
					query.setParameter("personnel", personnel);
					query.setParameter("year", currentYear);
					query.setParameter("status", 0);

					List<?> results = query.list();

					TB_MISO_INSP_BPET_RESULT bpetEntity;

					if (!results.isEmpty()) {
						bpetEntity = (TB_MISO_INSP_BPET_RESULT) results.get(0);
					} else {
						bpetEntity = new TB_MISO_INSP_BPET_RESULT();
						bpetEntity.setSus_no(roleSusNo);
						bpetEntity.setPersonnel(personnel);
						bpetEntity.setYear(currentYear);
						bpetEntity.setStatus(status);
						bpetEntity.setCreated_date(new Date());
						bpetEntity.setCreated_by(username);
					}

					bpetEntity.setTotal_no(data.get("total_no"));
					bpetEntity.setExcellent(data.get("excellent"));
					bpetEntity.setGood(data.get("good"));
					bpetEntity.setSatisfactory(data.get("satisfactory"));
					bpetEntity.setPoor(data.get("poor"));
					bpetEntity.setFail(data.get("fail"));
					bpetEntity.setNumber_yet_to_tested(data.get("number_yet_to_tested"));
					bpetEntity.setRemarks(data.get("remarks"));

					ses1.saveOrUpdate(bpetEntity);
				}

				String remarks = request.getParameter("user_remarks7");
				if (!remarks.equals("") && !remarks.isEmpty()) {
					addUserRemarks("bpet_result_item", remarks, roleSusNo, username);
				}

				t2.commit();
				save = true;
			} else {
				System.out.println("No BPET result data received.");
			}

		} catch (Exception e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Error saving/updating BPET result data: " + e.getMessage());
			e.printStackTrace();
			save = false;
		} finally {
			if (ses1 != null && ses1.isOpen()) {
				ses1.close();
			}
		}
		return save;
	}

	// ----------------------------end bpet-------------------------------

	// --- -----------------------------start ppt result------------------------
	@RequestMapping(value = "/ppt_get_data_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> ppt_get_data_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		result = dao.getPpt_Result_Data(session, request);
		return result;
	}

	private boolean pptSaveAction(HttpSession session, HttpServletRequest request, String inspDataJson) {
		boolean save = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		try {
			if (inspDataJson != null && !inspDataJson.isEmpty()) {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Map<String, String>> inspDataList = objectMapper.readValue(inspDataJson,
						new TypeReference<List<Map<String, String>>>() {
						});

				for (Map<String, String> data : inspDataList) {
					String personnel = data.get("personnel");

					String hql = "FROM TB_MISO_INSP_PPT_RESULT " + "WHERE sus_no = :susNo "
							+ "AND personnel = :personnel " + "AND year = :year AND status= :status ";

					Query query = ses1.createQuery(hql);
					query.setParameter("susNo", roleSusNo);
					query.setParameter("personnel", personnel);
					query.setParameter("year", currentYear);
					query.setParameter("status", 0);

					List<?> results = query.list();

					TB_MISO_INSP_PPT_RESULT pptEntity;

					if (!results.isEmpty()) {
						pptEntity = (TB_MISO_INSP_PPT_RESULT) results.get(0);
					} else {
						pptEntity = new TB_MISO_INSP_PPT_RESULT();
						pptEntity.setSus_no(roleSusNo);
						pptEntity.setPersonnel(personnel);
						pptEntity.setYear(currentYear);
						pptEntity.setStatus(status);
						pptEntity.setCreated_date(new Date());
						pptEntity.setCreated_by(username);
					}

					pptEntity.setTotal_no(data.get("total_no"));
					pptEntity.setExcellent(data.get("excellent"));
					pptEntity.setGood(data.get("good"));
					pptEntity.setSatisfactory(data.get("satisfactory"));
					pptEntity.setPoor(data.get("poor"));
					pptEntity.setFail(data.get("fail"));
					pptEntity.setNumber_yet_to_tested(data.get("number_yet_to_tested"));
					pptEntity.setRemarks(data.get("remarks"));

					ses1.saveOrUpdate(pptEntity);
				}

				String remarks = request.getParameter("user_remarks8");
				if (!remarks.equals("") && !remarks.isEmpty()) {
					addUserRemarks("ppt_result_item", remarks, roleSusNo, username);
				}

				t2.commit();
				save = true;
			} else {
				System.out.println("No PPT result data received.");
			}

		} catch (Exception e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Error saving/updating PPT result data: " + e.getMessage());
			e.printStackTrace();
			save = false;
		} finally {
			if (ses1 != null && ses1.isOpen()) {
				ses1.close();
			}
		}
		return save;
	}

	// -- -------------------------------end ppt result------------------------

	// ------------ -----------------------------start summary tech
	// inst------------------------
	@RequestMapping(value = "/summary_tech_insp_Data_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> summary_tech_insp_Data_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		result = dao.summary_tech_insp_Data(session, request);
		return result;
	}

	private boolean summaryTechInspSaveAction(HttpSession session, HttpServletRequest request, String inspDataJson) {
		boolean save = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		try {
			if (inspDataJson != null && !inspDataJson.isEmpty()) {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Map<String, String>> inspDataList = objectMapper.readValue(inspDataJson,
						new TypeReference<List<Map<String, String>>>() {
						});

				for (Map<String, String> data : inspDataList) {
					String inspection_type = data.get("inspection_type");

					String hql = "FROM TB_MISO_INSP_SUMMARY_TECH " + "WHERE sus_no = :susNo "
							+ "AND inspection_type = :inspection_type " + "AND year = :year";

					Query query = ses1.createQuery(hql);
					query.setParameter("susNo", roleSusNo);
					query.setParameter("inspection_type", inspection_type);
					query.setParameter("year", currentYear);

					List<?> results = query.list();

					TB_MISO_INSP_SUMMARY_TECH techEntity;

					if (!results.isEmpty()) {
						techEntity = (TB_MISO_INSP_SUMMARY_TECH) results.get(0);
					} else {
						techEntity = new TB_MISO_INSP_SUMMARY_TECH();
						techEntity.setSus_no(roleSusNo);
						techEntity.setInspection_type(inspection_type);
						techEntity.setYear(currentYear);
						techEntity.setStatus(status);
						techEntity.setCreated_date(new Date());
						techEntity.setCreated_by(username);
					}

					techEntity.setDate_held(data.get("date_held"));
					techEntity.setBy_whom(data.get("by_whom"));
					techEntity.setResult(data.get("result"));
					techEntity.setDetailed_remarks(data.get("detailed_remarks"));

					ses1.saveOrUpdate(techEntity);
				}

				if (roleType.equals("APP")) {
					ses1 = HibernateUtil.getSessionFactory().openSession();
					t2 = ses1.beginTransaction();

					Query q1 = ses1.createQuery(
							"update TB_MISO_INSP_SUMMARY_TECH_OTHER set status = 1  WHERE sus_no = :sus_no and year =:year and status=:status"); // Update
																																					// to
																																					// 1
																																					// not
																																					// buttonContext
					q1.setParameter("sus_no", roleSusNo);
					q1.setParameter("year", currentYear);
					q1.setParameter("status", 0);
					q1.executeUpdate();

				}

				String remarks = (request.getParameter("user_remarks10") == null) ? ""
						: request.getParameter("user_remarks10");

				if (!remarks.equals("") && !remarks.isEmpty()) {
					addUserRemarks("summary_item", remarks, roleSusNo, username);
				}

				t2.commit();
				save = true;
			} else {
				System.out.println("No Summary Tech data received.");
			}

		} catch (Exception e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Error saving/updating Summary Tech data: " + e.getMessage());
			e.printStackTrace();
			save = false;
		} finally {
			if (ses1 != null && ses1.isOpen()) {
				ses1.close();
			}
		}
		return save;
	}
	// ------------ -----------------------------end summary tech
	// inst------------------------

	// ------------------------------------------- regt funds
	// ----------------------------------
	// @RequestMapping(value = "/State_of_Sector_Stores_url", method =
	// RequestMethod.POST)
	// @ResponseBody
	// public List<Map<String, String>> stateOfSectorStoresUrl(
	// @RequestParam(value = "unit_no", required = false) String unitNo, // use
	// @RequestParam
	// HttpSession session,
	// HttpServletRequest request) {
	// List<Map<String, String>> result = dao.stateOfSectorStoresGetData(session,
	// request);
	// return result;
	// }

	// HERE



	@RequestMapping(value = "/up_gradation_urls", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> up_gradation_urls(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getUp_Gradation_Data(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/Courses_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Courses_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getCoursesData(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/Standards_Achieved_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Standards_Achieved_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getStandardsAchievedData(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/TDOfficers_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> TDOfficers_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getTDOfficersData(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/SML_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> SML_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getSMLData(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/PCL_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> PCL_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getPCLData(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/EL_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> EL_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getELData(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/CPCL_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> CPCL_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getCPCLData(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/Financial_Grants_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Financial_Grants_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getFinancialGrantsData(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/uploadAnnexureUrl")
	public @ResponseBody List<String> uploadAnnexureUrl(
			@RequestParam(value = "uploadAnnexure", required = false) MultipartFile uploadAnnexure,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		List<String> list = new ArrayList<>();

		// Validate Session attributes
		if (session.getAttribute("fileSizeLimit") == null || session.getAttribute("username") == null
				|| session.getAttribute("roleSusNo") == null || session.getAttribute("userId") == null
				|| session.getAttribute("psgFilePath") == null) {
			list.add("Required session attributes are missing.");
			return list;
		}

		final long fileSizeLimit;
		try {
			fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());
		} catch (NumberFormatException e) {
			list.add("Invalid file size limit in session.");
			return list;
		}

		if (uploadAnnexure == null || uploadAnnexure.isEmpty()) {
			list.add("No file uploaded.");
			return list;
		}

		if (uploadAnnexure.getSize() > fileSizeLimit) {
			list.add("File size should be less than " + fileSizeLimit / (1024 * 1024) + " MB");
			return list;
		}

		String uploadMvcrExt = FilenameUtils.getExtension(uploadAnnexure.getOriginalFilename());
		if (!"pdf".equalsIgnoreCase(uploadMvcrExt)) {
			list.add("Only *.pdf file extensions allowed");
			return list;
		}

		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String psgFilePath = session.getAttribute("psgFilePath").toString();

		String fname = "";
		DateWithTimeStampController timestamp = new DateWithTimeStampController();

		try {
			byte[] bytes = uploadAnnexure.getBytes();

			CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
			if (!fileValidation.check_PDF_File(bytes)) {
				list.add("Invalid File Format.");
				return list;
			}

			File dir = new File(psgFilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String filename = uploadAnnexure.getOriginalFilename();
			String extension = "";
			int i = filename.lastIndexOf('.');
			if (i >= 0) {
				extension = filename.substring(i + 1);
			}
			fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_" + userid
					+ "." + extension;
			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session1.beginTransaction();

				String hql = "FROM TB_PSG_ANNUAL_REPORT WHERE sus_no = :susNo AND year = :year";
				Query query = session1.createQuery(hql);
				query.setParameter("susNo", roleSusNo);
				query.setParameter("year", currentYear);
				TB_PSG_ANNUAL_REPORT upload = (TB_PSG_ANNUAL_REPORT) query.uniqueResult();

				if (upload != null) {
					upload.setDoc_path(fname);
					upload.setCreated_by(username);
					upload.setCreated_on(new Date());
					upload.setStatus(0);
					session1.update(upload);
					list.add("Document Updated Successfully");

				} else {
					upload = new TB_PSG_ANNUAL_REPORT();
					upload.setDoc_path(fname);
					upload.setCreated_by(username);
					upload.setStatus(0);
					upload.setYear(currentYear);
					upload.setCreated_on(new Date());
					upload.setSus_no(roleSusNo);
					session1.save(upload);
					list.add("Document Uploaded Successfully");
				}

				tx.commit();

			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
				list.add("An error occurred while saving to the database: " + e.getMessage());
				e.printStackTrace();
			} finally {
				session1.close();
			}

		} catch (Exception e) {
			list.add("An error occurred file saving: " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = "/upload21ReportRecTraining")
	public @ResponseBody List<String> upload21ReportRecTraining(
			@RequestParam(value = "uploadAnnexure1", required = false) MultipartFile uploadAnnexure,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		List<String> list = new ArrayList<>();

		final long fileSizeLimit;
		try {
			fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());
		} catch (NumberFormatException e) {
			list.add("Invalid file size limit in session.");
			return list;
		}

		if (uploadAnnexure == null || uploadAnnexure.isEmpty()) {
			list.add("No file uploaded.");
			return list;
		}

		if (uploadAnnexure.getSize() > fileSizeLimit) {
			list.add("File size should be less than " + fileSizeLimit / (1024 * 1024) + " MB");
			return list;
		}

		String uploadMvcrExt = FilenameUtils.getExtension(uploadAnnexure.getOriginalFilename());
		if (!"pdf".equalsIgnoreCase(uploadMvcrExt)) {
			list.add("Only *.pdf file extensions allowed");
			return list;
		}

		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String psgFilePath = session.getAttribute("psgFilePath").toString();

		String fname = "";
		DateWithTimeStampController timestamp = new DateWithTimeStampController();

		try {
			byte[] bytes = uploadAnnexure.getBytes();

			CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
			if (!fileValidation.check_PDF_File(bytes)) {
				list.add("Invalid File Format.");
				return list;
			}

			File dir = new File(psgFilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String filename = uploadAnnexure.getOriginalFilename();
			String extension = "";
			int i = filename.lastIndexOf('.');
			if (i >= 0) {
				extension = filename.substring(i + 1);
			}
			fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_" + userid
					+ "." + extension;
			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session1.beginTransaction();

				String hql = "FROM TB_MISO_INSP_DTL_OR_REQ_TRAINING WHERE sus_no = :susNo AND year = :year";
				Query query = session1.createQuery(hql);
				query.setParameter("susNo", roleSusNo);
				query.setParameter("year", currentYear);
				TB_MISO_INSP_DTL_OR_REQ_TRAINING upload = (TB_MISO_INSP_DTL_OR_REQ_TRAINING) query.uniqueResult();

				if (upload != null) {
					upload.setDoc_path(fname);
					upload.setCreated_by(username);
					upload.setCreated_on(new Date());
					upload.setStatus(0);
					session1.update(upload);
					list.add("Document Updated Successfully");

				} else {
					upload = new TB_MISO_INSP_DTL_OR_REQ_TRAINING();
					upload.setDoc_path(fname);
					upload.setCreated_by(username);
					upload.setStatus(0);
					upload.setYear(currentYear);
					upload.setCreated_on(new Date());
					upload.setSus_no(roleSusNo);
					session1.save(upload);
					list.add("Document Uploaded Successfully");
				}

				tx.commit();

			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
				list.add("An error occurred while saving to the database: " + e.getMessage());
				e.printStackTrace();
			} finally {
				session1.close();
			}

		} catch (Exception e) {
			list.add("An error occurred file saving: " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = "/upload_deffi_exp_resp_to_trainngUrl")
	public @ResponseBody List<String> upload_deffi_exp_resp_to_trainngUrl(
			@RequestParam(value = "uploadAnnexure2", required = false) MultipartFile uploadAnnexure,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		List<String> list = new ArrayList<>();

		final long fileSizeLimit;
		try {
			fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());
		} catch (NumberFormatException e) {
			list.add("Invalid file size limit in session.");
			return list;
		}

		if (uploadAnnexure == null || uploadAnnexure.isEmpty()) {
			list.add("No file uploaded.");
			return list;
		}

		if (uploadAnnexure.getSize() > fileSizeLimit) {
			list.add("File size should be less than " + fileSizeLimit / (1024 * 1024) + " MB");
			return list;
		}

		String uploadMvcrExt = FilenameUtils.getExtension(uploadAnnexure.getOriginalFilename());
		if (!"pdf".equalsIgnoreCase(uploadMvcrExt)) {
			list.add("Only *.pdf file extensions allowed");
			return list;
		}

		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String psgFilePath = session.getAttribute("psgFilePath").toString();

		String fname = "";
		DateWithTimeStampController timestamp = new DateWithTimeStampController();

		try {
			byte[] bytes = uploadAnnexure.getBytes();

			CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
			if (!fileValidation.check_PDF_File(bytes)) {
				list.add("Invalid File Format.");
				return list;
			}

			File dir = new File(psgFilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String filename = uploadAnnexure.getOriginalFilename();
			String extension = "";
			int i = filename.lastIndexOf('.');
			if (i >= 0) {
				extension = filename.substring(i + 1);
			}
			fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_" + userid
					+ "." + extension;
			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session1.beginTransaction();

				String hql = "FROM TB_MISO_INSP_DTL_DEFFI_EXP_RESP_TO_TRAINNING\r\n"
						+ " WHERE sus_no = :susNo AND year = :year";
				Query query = session1.createQuery(hql);
				query.setParameter("susNo", roleSusNo);
				query.setParameter("year", currentYear);
				TB_MISO_INSP_DTL_DEFFI_EXP_RESP_TO_TRAINNING upload = (TB_MISO_INSP_DTL_DEFFI_EXP_RESP_TO_TRAINNING) query
						.uniqueResult();

				if (upload != null) {
					upload.setDoc_path(fname);
					upload.setCreated_by(username);
					upload.setCreated_on(new Date());
					upload.setStatus(0);
					session1.update(upload);
					list.add("Document Updated Successfully");

				} else {
					upload = new TB_MISO_INSP_DTL_DEFFI_EXP_RESP_TO_TRAINNING();
					upload.setDoc_path(fname);
					upload.setCreated_by(username);
					upload.setStatus(0);
					upload.setYear(currentYear);
					upload.setCreated_on(new Date());
					upload.setSus_no(roleSusNo);
					session1.save(upload);
					list.add("Document Uploaded Successfully");
				}

				tx.commit();

			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
				list.add("An error occurred while saving to the database: " + e.getMessage());
				e.printStackTrace();
			} finally {
				session1.close();
			}

		} catch (Exception e) {
			list.add("An error occurred file saving: " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = "/emp_of_unit_during_periodUrl")
	public @ResponseBody List<String> emp_of_unit_during_periodUrl(
			@RequestParam(value = "uploadAnnexure3", required = false) MultipartFile uploadAnnexure,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		List<String> list = new ArrayList<>();

		final long fileSizeLimit;
		try {
			fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());
		} catch (NumberFormatException e) {
			list.add("Invalid file size limit in session.");
			return list;
		}

		if (uploadAnnexure == null || uploadAnnexure.isEmpty()) {
			list.add("No file uploaded.");
			return list;
		}

		if (uploadAnnexure.getSize() > fileSizeLimit) {
			list.add("File size should be less than " + fileSizeLimit / (1024 * 1024) + " MB");
			return list;
		}

		String uploadMvcrExt = FilenameUtils.getExtension(uploadAnnexure.getOriginalFilename());
		if (!"pdf".equalsIgnoreCase(uploadMvcrExt)) {
			list.add("Only *.pdf file extensions allowed");
			return list;
		}

		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String psgFilePath = session.getAttribute("psgFilePath").toString();

		String fname = "";
		DateWithTimeStampController timestamp = new DateWithTimeStampController();

		try {
			byte[] bytes = uploadAnnexure.getBytes();

			CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
			if (!fileValidation.check_PDF_File(bytes)) {
				list.add("Invalid File Format.");
				return list;
			}

			File dir = new File(psgFilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String filename = uploadAnnexure.getOriginalFilename();
			String extension = "";
			int i = filename.lastIndexOf('.');
			if (i >= 0) {
				extension = filename.substring(i + 1);
			}
			fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_" + userid
					+ "." + extension;
			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session1.beginTransaction();

				String hql = "FROM TB_MISO_INSP_EMP_OF_UNIT_DURING_PERIOD\r\n"
						+ " WHERE sus_no = :susNo AND year = :year";
				Query query = session1.createQuery(hql);
				query.setParameter("susNo", roleSusNo);
				query.setParameter("year", currentYear);
				TB_MISO_INSP_EMP_OF_UNIT_DURING_PERIOD upload = (TB_MISO_INSP_EMP_OF_UNIT_DURING_PERIOD) query
						.uniqueResult();

				if (upload != null) {
					upload.setDoc_path(fname);
					upload.setCreated_by(username);
					upload.setCreated_on(new Date());
					upload.setStatus(0);
					session1.update(upload);
					list.add("Document Updated Successfully");

				} else {
					upload = new TB_MISO_INSP_EMP_OF_UNIT_DURING_PERIOD();
					upload.setDoc_path(fname);
					upload.setCreated_by(username);
					upload.setStatus(0);
					upload.setYear(currentYear);
					upload.setCreated_on(new Date());
					upload.setSus_no(roleSusNo);
					session1.save(upload);
					list.add("Document Uploaded Successfully");
				}

				tx.commit();

			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
				list.add("An error occurred while saving to the database: " + e.getMessage());
				e.printStackTrace();
			} finally {
				session1.close();
			}

		} catch (Exception e) {
			list.add("An error occurred file saving: " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = "/admin/detail_course_action", method = RequestMethod.POST)
	public @ResponseBody String detail_course_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		String msg = "";
		Date p1 = null;
		Date p2 = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		String number_of_course = request.getParameter("numberOfCourse");
		String course_name = request.getParameter("courseName");
		String period_from = request.getParameter("periodFrom");
		String period_to = request.getParameter("periodTo");
		String total_allotted = request.getParameter("totalAllotted");
		String attended = request.getParameter("attended");
		String rtu = request.getParameter("rtu");
		String detailed_remarks = request.getParameter("detailedRemarks");
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		p1 = format.parse(period_from);
		p2 = format.parse(period_to);

		TB_DETAIL_COURSES lang = new TB_DETAIL_COURSES();
		lang.setCourse_name(course_name);
		lang.setNumber_of_course(number_of_course);
		lang.setPeriod_from(p1);
		lang.setPeriod_to(p2);
		lang.setTotal_allotted(total_allotted); // Ensure correct type
		lang.setAttended(attended);
		lang.setRtu(rtu);
		lang.setDetailed_remarks(detailed_remarks);

		int id = (int) sessionhql.save(lang);

		msg = Integer.toString(id);
		System.out.println("msg:  " + msg);

		/*
		 * String remarks = request.getParameter("user_remarks"); if
		 * (!remarks.equals("") && !remarks.isEmpty()) { addUserRemarks("courses_item",
		 * remarks,roleSusNo, username); }
		 */

		tx.commit();
		sessionhql.close();
		return msg;
	}

	@RequestMapping(value = "/detail_course_delete_action", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> detail_course_delete_action(Authentication authentication,
			HttpServletRequest request, HttpSession session, @RequestParam(value = "id", required = false) int id) {

		Map<String, Object> response = new HashMap<>();
		boolean isDeleted = false;
		Session ses1 = null;
		Transaction t2 = null;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();
			Query q = ses1.createQuery("delete from TB_DETAIL_COURSES where id = :id");
			q.setParameter("id", id);
			int rowCount = q.executeUpdate();

			t2.commit();

			if (rowCount > 0) {
				isDeleted = true;
				response.put("success", true);
				response.put("message", "Data deleted Successfully");
			} else {
				response.put("success", false);
				response.put("message", "Data Not deleted Successfully");
			}

		} catch (HibernateException e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			response.put("success", false);
			response.put("message", "Error deleting data: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			if (ses1 != null) {
				ses1.close();
			}
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/getEstablishmentTbleData", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getEstablishmentTbleData(Authentication authentication,
			HttpServletRequest request, HttpSession session) {

		Map<String, Object> response = new HashMap<>();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		try {
			List<TB_MISO_INSP_ESTABLISHMENT> list = dao.getEstablishmentAttachedAction(roleSusNo);

			if (list.isEmpty()) {
				response.put("success", false);
				response.put("message", "Data not available for roleSusNo: " + roleSusNo);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.put("success", true);
				response.put("data", list);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "Error fetching data: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/Category_url_addmore", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Category_url_addmore(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getCategory_url_Data_addmore(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/up_gradation_urls_addmore", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> up_gradation_urls_addmore(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getUp_Gradation_Data_addmore(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/OutstandingRentAlliedAction", method = RequestMethod.POST)
	public ModelAndView OutstandingRentAlliedAction(
			@ModelAttribute("OutstandingRentAlliedCmd") TB_INSP_OUTSTANDING_RENT_ALLIED obj, HttpServletRequest request,
			ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg)
			throws ParseException {

		// String roleid = session.getAttribute("roleid").toString();
		// Boolean val = roledao.ScreenRedirect("inspection_report", roleid);
		// if (val == false) {
		// return new ModelAndView("AccessTiles");
		// }
		// if (request.getHeader("Referer") == null) {
		// msg = "";
		// }

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		String username = session.getAttribute("username").toString();
		String year = request.getParameter("year");
		String amount_outstanding = request.getParameter("amount_outstanding");
		String on_acc = request.getParameter("on_acc");
		String remarks = request.getParameter("remarks");

		String roleSusNo = session.getAttribute("roleSusNo").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			obj.setYear(year);
			obj.setAmount_outstanding(amount_outstanding);
			obj.setOn_acc(on_acc);
			obj.setRemarks(remarks);
			obj.setSus_no(roleSusNo);
			obj.setCreated_on(new Date());

			sessionHQL.save(obj);
			sessionHQL.flush();
			sessionHQL.clear();
			model.put("msg", "Data Saved Successfully.");

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("Inspection_Report_tiles");
	}

	@RequestMapping(value = "/Outstanding_Audit_Objections_Observations_url", method = RequestMethod.POST)
	public @ResponseBody List<TB_INSP_OUTSTANDING_AUDIT> Outstanding_Audit_Objections_Observations_url(
			HttpServletRequest request, ModelMap model, HttpSession session) throws ParseException {

		List<TB_INSP_OUTSTANDING_AUDIT> result = null;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {

			Query q = sessionHQL
					.createQuery(
							" From TB_INSP_OUTSTANDING_AUDIT where sus_no=:sus_no and year=:year order by id desc  ")
					.setMaxResults(1).setString("sus_no", roleSusNo).setString("year", currentYear);
			@SuppressWarnings("unchecked")
			List<TB_INSP_OUTSTANDING_AUDIT> list = q.list();
			tx.commit();
			result = list;

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return result;

	}

	// AVAILABILITY SAVE

	@RequestMapping(value = "/ffrs_save_url", method = RequestMethod.POST)
	public @ResponseBody String ffrs_save_url(HttpServletRequest request, ModelMap model, HttpSession session)
			throws ParseException {

		String msg = "";
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String ffrs_id = request.getParameter("ffrs_id");
		String range_available = request.getParameter("range_available");
		String range_utilized = request.getParameter("range_utilized");
		String whenusedffrs = request.getParameter("whenusedffrs");
		String difficulties_experienced = request.getParameter("difficulties_experienced");
		String username = session.getAttribute("username").toString();

		Date when_used_date = null;

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		if (whenusedffrs != null && !whenusedffrs.equals("") && !whenusedffrs.equals("DD/MM/YYYY")) {
			when_used_date = format.parse(whenusedffrs);
		}
		try {

			TB_INSP_FFRS save = new TB_INSP_FFRS();
			save.setWhenusedffrs(when_used_date);
			save.setSus_no(roleSusNo);
			save.setDifficulties_experienced(difficulties_experienced);
			save.setRange_available(range_available);
			save.setRange_utilized(range_utilized);
			sessionHQL.save(save);
			sessionHQL.flush();
			sessionHQL.clear();
			msg = "Data Saved Successfully.";

			tx.commit();

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "roll back transaction";
			} catch (RuntimeException rbe) {
				msg = "Couldn't roll back transaction";

			}
			throw e;

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		model.put("msg", msg);

		return msg;
	}

	@RequestMapping(value = "/classification_ranges_url", method = RequestMethod.POST)
	public @ResponseBody List<TB_INSP_RPT_CLASSIFICATION_RANGES> classification_ranges_url(HttpServletRequest request,
			ModelMap model, HttpSession session) throws ParseException {

		List<TB_INSP_RPT_CLASSIFICATION_RANGES> result = null;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {

			Query q = sessionHQL.createQuery(" From TB_INSP_RPT_CLASSIFICATION_RANGES where sus_no=:sus_no ")
					.setString("sus_no", roleSusNo);
			@SuppressWarnings("unchecked")
			List<TB_INSP_RPT_CLASSIFICATION_RANGES> list = q.list();
			tx.commit();
			result = list;

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return result;

	}

	// AVAILABILITY SAVE END

	@RequestMapping(value = "/ffrs_url", method = RequestMethod.POST)
	public @ResponseBody List<TB_INSP_FFRS> ffrs_url(HttpServletRequest request, ModelMap model, HttpSession session)
			throws ParseException {

		List<TB_INSP_FFRS> result = null;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {

			Query q = sessionHQL.createQuery(" From TB_INSP_FFRS where sus_no=:sus_no ").setString("sus_no", roleSusNo);
			@SuppressWarnings("unchecked")
			List<TB_INSP_FFRS> list = q.list();
			tx.commit();
			result = list;

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return result;

	}



	@RequestMapping(value = "/deleteTbleRowClassification", method = RequestMethod.POST)
	@ResponseBody
	public String deleteTbleRowClassification(Authentication authentication, HttpServletRequest request,
			HttpSession session, ModelMap model, @RequestParam(value = "id", required = false) String id) {
		String msg = "";
		Session ses1 = null;
		Transaction t2 = null;
		int rowCount = 0;
		int id1 = 0;
		if (id != "") {
			id1 = Integer.parseInt(id);

		}
		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("delete from TB_INSP_RPT_CLASSIFICATION_RANGES where id =:id");
			q.setParameter("id", id1);
			rowCount = q.executeUpdate();

			if (rowCount > 0)

			{
				msg = "Data Deleted Successfully";

			} else {
				msg = "Data Not Deleted";
			}
			t2.commit();
		} catch (HibernateException e) {

			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}

			msg = "Data Not Delted";
		} finally {
			if (ses1 != null) {
				ses1.close();
			}
		}
		model.put("msg", msg);
		return msg;
	}

	@RequestMapping(value = "/deleteRowFfrs", method = RequestMethod.POST)
	@ResponseBody
	public String deleteRowFfrs(Authentication authentication, HttpServletRequest request, ModelMap model,
			HttpSession session, @RequestParam(value = "id", required = false) String id) {
		String msg = "";
		Session ses1 = null;
		Transaction t2 = null;
		int rowCount = 0;
		int id1 = 0;
		if (id != "") {
			id1 = Integer.parseInt(id);

		}
		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("delete from TB_INSP_FFRS where id =:id");
			q.setParameter("id", id1);
			rowCount = q.executeUpdate();

			if (rowCount > 0)

			{
				msg = "Data Delted Successfully";

			} else {
				msg = "Data Not Deleted";
			}
			t2.commit();
		} catch (HibernateException e) {

			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}

			msg = "Data Not Delted";
		} finally {
			if (ses1 != null) {
				ses1.close();
			}
		}
		model.put("msg", msg);
		return msg;
	}

	/* new code */

	// -------------------------------------------- PDF DOWNLOAD----------

	

	@RequestMapping(value = "/Download_AnualReport_extracts", method = RequestMethod.POST)
    public ModelAndView Download_AnualReport_extracts(ModelMap Mmap, HttpSession session, HttpServletRequest request,
            @RequestParam(value = "downloadid", required = false) String downloadid) throws ParseException {
        ArrayList<ArrayList<String>> list = null;
        ArrayList<ArrayList<String>> list1 = null;
        List<String> TH = new ArrayList<String>();
        String Heading = session.getAttribute("roleloginName").toString();
        String roleSusNo = (String) session.getAttribute("roleSusNo");
   
   
		ModelAndView mav = new ModelAndView(new PDF_ANNUAL_REPORT_EXTRACTS("P"), "userList", list);
        return mav;
    }
	
	
	
	@RequestMapping(value = "/Download_Form_part1", method = RequestMethod.POST)
    public ModelAndView Download_Form_part11(ModelMap Mmap, HttpSession session, HttpServletRequest request,
            @RequestParam(value = "downloadid", required = false) String downloadid) throws ParseException {
        ArrayList<ArrayList<String>> list = null;
        ArrayList<ArrayList<String>> list1 = null;
        List<String> TH = new ArrayList<String>();
        String Heading = session.getAttribute("roleloginName").toString();
        String roleSusNo = (String) session.getAttribute("roleSusNo");
   
        List<TB_MISO_INSP_ESTABLISHMENT> getEstablishmentAttachedAction = getdatamodelwise(roleSusNo,"TB_MISO_INSP_ESTABLISHMENT");
        List<TB_INSP_EQUP_ANNUAL_METERAGE> getequpAnnualMeterageData = getdatamodelwise(roleSusNo,"TB_INSP_EQUP_ANNUAL_METERAGE"); 
        ArrayList<ArrayList<String>> equimentData = dao.getTableData(session, "tb_insp_equp_a_b_c_veh"); 
       List<TB_MISO_INSP_ANIMAL> getAnimal_Data = getdatamodelwise(roleSusNo,"TB_MISO_INSP_ANIMAL");  
        List<TB_MISO_INSP_DEFI_EQUP_EFFI> defiequp = getdatamodelwise(roleSusNo,"TB_MISO_INSP_DEFI_EQUP_EFFI");  
        List<TB_MISO_INSP_DTL_OF_EQUP_OFFROAD> defiequpRnder = getdatamodelwise(roleSusNo,"TB_MISO_INSP_DTL_OF_EQUP_OFFROAD"); 
        List<TB_MISO_INSP_STATE_FUNDS> stateFund = getdatamodelwise(roleSusNo,"TB_MISO_INSP_STATE_FUNDS"); 
        List<TB_MISO_INSP_WT_RESULT_OTHER> wtResult = getdatamodelwise(roleSusNo,"TB_MISO_INSP_WT_RESULT_OTHER"); 
        List<TB_INSP_EDUCATION_STANDARDS> educationStandard = getdatamodelwise(roleSusNo,"TB_INSP_EDUCATION_STANDARDS");
        List<TB_MISO_INSP_COURSES_CAT_A_B> availabilityofPersonnel= getdatamodelwise(roleSusNo,"TB_MISO_INSP_COURSES_CAT_A_B");
    	
        List<TB_MISO_INSP_UPGRADATION> upgradation= getdatamodelwise(roleSusNo,"TB_MISO_INSP_UPGRADATION");
        List<TB_MISO_INSP_REG_LANG_EXAM> regimentaLanguage= getdatamodelwise(roleSusNo,"TB_MISO_INSP_REG_LANG_EXAM");
        List<TB_MISO_INSP_BPET_RESULT> bpetResult= getdatamodelwise(roleSusNo,"TB_MISO_INSP_BPET_RESULT");
        List<TB_MISO_INSP_PPT_RESULT> pptResult= getdatamodelwise(roleSusNo,"TB_MISO_INSP_PPT_RESULT");
        List<TB_MISO_INSP_PROMOTION_EXAM> promotionExam= getdatamodelwise(roleSusNo,"TB_MISO_INSP_PROMOTION_EXAM");
        List<TB_INSP_FINANCIAL_GRANTS> financialGrant= getdatamodelwise(roleSusNo,"TB_INSP_FINANCIAL_GRANTS");
        List<TB_INSP_REGT_FUNDS> regtFunds= getdatamodelwise(roleSusNo,"TB_INSP_REGT_FUNDS");
       List<TB_MISO_INSP_TRAINING_AMMUNITION> trainingAmmunition= getdatamodelwise(roleSusNo,"TB_MISO_INSP_TRAINING_AMMUNITION");
        
        List<TB_INSP_RPT_CLASSIFICATION_RANGES> availabilityRangesTbl1= getdatamodelwise(roleSusNo,"TB_INSP_RPT_CLASSIFICATION_RANGES");
        List<TB_INSP_FFRS> availabilityRangesTbl2= getdatamodelwise(roleSusNo,"TB_INSP_FFRS");       
        
        List<TB_INSP_OUTSTANDING_AUDIT> outstandingAudit= getdatamodelwise(roleSusNo,"TB_INSP_OUTSTANDING_AUDIT");
        
        List<TB_DETAIL_COURSES> DetailsofCoursestbl1= getdatamodelwise(roleSusNo,"TB_DETAIL_COURSES");        
        List<TB_INSP_STANDARDS_ACHIEVED> DetailsofCoursestbl2= getdatamodelwise(roleSusNo,"TB_INSP_STANDARDS_ACHIEVED");
        
        List<TB_MISO_INSP_SUMMARY_TECH> SummaryofTechnicalInspection1= getdatamodelwise(roleSusNo,"TB_MISO_INSP_SUMMARY_TECH");
        List<TB_MISO_INSP_SUMMARY_TECH_OTHER> SummaryofTechnicalInspection2= getdatamodelwise(roleSusNo,"TB_MISO_INSP_SUMMARY_TECH_OTHER");
        
        List<TB_INSP_OUTSTANDING_RENT_ALLIED> outstandingRentAllied= getdatamodelwise(roleSusNo,"TB_INSP_OUTSTANDING_RENT_ALLIED");        
        List<TB_INSP_OUTSTANDING_LOSS_MT> outstandingLossMt= getdatamodelwise(roleSusNo,"TB_INSP_OUTSTANDING_LOSS_MT");  
        List<TB_INSP_FATAL_INCIDENTS> fatalIncident= getdatamodelwise(roleSusNo,"TB_INSP_FATAL_INCIDENTS");
        List<TB_INSP_SECURITY_LAPSES> securityLapses= getdatamodelwise(roleSusNo,"TB_INSP_SECURITY_LAPSES");
        List<TB_INSP_OUTSIDE_ATTACHMENTS> outsideAttachments= getdatamodelwise(roleSusNo,"TB_INSP_OUTSIDE_ATTACHMENTS");
        List<TB_INSP_TD_PROCEEDED> tdProcceded= getdatamodelwise(roleSusNo,"TB_INSP_TD_PROCEEDED");
        List<TB_INSP_SOCIAL_MEDIA_LAPSES> socialMediaLapses= getdatamodelwise(roleSusNo,"TB_INSP_SOCIAL_MEDIA_LAPSES");
        List<TB_INSP_PIO_CALL_LAPSES > pioCallLapses= getdatamodelwise(roleSusNo,"TB_INSP_PIO_CALL_LAPSES ");        
        List<TB_INSP_ESPIONAGE_LAPSES> espionageLapses= getdatamodelwise(roleSusNo,"TB_INSP_ESPIONAGE_LAPSES");
        List<TB_INSP_CELL_PHONE_LAPSES > cellPhoneLapses= getdatamodelwise(roleSusNo,"TB_INSP_CELL_PHONE_LAPSES ");
        List<TB_INSP_FS_SECURITY_LAPSES> fsSecurityLapses= getdatamodelwise(roleSusNo,"TB_INSP_FS_SECURITY_LAPSES");
        List<TB_INSP_LOST_CDS_DVDS> lostCdsDvds= getdatamodelwise(roleSusNo,"TB_INSP_LOST_CDS_DVDS");
        List<TB_INSP_LOST_ID_ECR> lostIdEcr= getdatamodelwise(roleSusNo,"TB_INSP_LOST_ID_ECR");        
        List<TB_MISO_INSP_LAND> land= getdatamodelwise(roleSusNo,"TB_MISO_INSP_LAND");
        

		ModelAndView mav = new ModelAndView(new PDF_ANUAL_REPORT_PART_1("P", TH, Heading, list1), "userList", list);
		

		mav.addObject("UpGradationData", upgradation);

//        =================================new =======================================
        
        mav.addObject("getEstablishmentAttached", getEstablishmentAttachedAction);
        mav.addObject("getEqupAnnualMeterage", getequpAnnualMeterageData);
        mav.addObject("equipmentData", equimentData);
        mav.addObject("getAnimalData", getAnimal_Data);
        mav.addObject("defiEqup", defiequp);
        mav.addObject("defiEqupRender", defiequpRnder);
        mav.addObject("stateFund", stateFund);
        mav.addObject("wtResult", wtResult);
        mav.addObject("educationStandard", educationStandard);
        mav.addObject("availabilityOfPersonnel", availabilityofPersonnel);
        mav.addObject("regimentalLanguage", regimentaLanguage);
        mav.addObject("bpetResult", bpetResult);
       mav.addObject("pptResult", pptResult);
       mav.addObject("promotionExam", promotionExam);
        mav.addObject("financialGrant", financialGrant);
        mav.addObject("regtFunds", regtFunds);
       mav.addObject("trainingAmmunition", trainingAmmunition);
       mav.addObject("availabilityRangesTbl1", availabilityRangesTbl1);
       mav.addObject("availabilityRangesTbl2", availabilityRangesTbl2);
        mav.addObject("outstandingAudit", outstandingAudit);
       mav.addObject("detailsOfCoursesTbl1", DetailsofCoursestbl1);
        mav.addObject("detailsOfCoursesTbl2", DetailsofCoursestbl2);
        
        mav.addObject("summaryOfTechnicalInspection1", SummaryofTechnicalInspection1);
        mav.addObject("SummaryofTechnicalInspection2", SummaryofTechnicalInspection2);
        
        
        
        mav.addObject("outstandingRentAllied", outstandingRentAllied);
        mav.addObject("outstandingLossMt", outstandingLossMt);
        mav.addObject("fatalIncident", fatalIncident);
        mav.addObject("securityLapses", securityLapses);
        mav.addObject("outsideAttachments", outsideAttachments);
        mav.addObject("tdProceeded", tdProcceded);
        mav.addObject("socialMediaLapses", socialMediaLapses);
        mav.addObject("pioCallLapses", pioCallLapses);
        mav.addObject("espionageLapses", espionageLapses);
        mav.addObject("cellPhoneLapses", cellPhoneLapses);
        mav.addObject("fsSecurityLapses", fsSecurityLapses);
        mav.addObject("lostCdsDvds", lostCdsDvds);
        mav.addObject("lostIdEcr", lostIdEcr);
        mav.addObject("land", land);
        return mav;
    }
	@RequestMapping(value = "/admin/inspection_reports", method = RequestMethod.GET)
	public ModelAndView countoffheld(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String sus_no,

			HttpServletRequest request) {

		return new ModelAndView("inspection_ReportsTiles");

	}

	
	
	@RequestMapping(value = "/admin/merghesearchreport", method = RequestMethod.GET)
	public ModelAndView merghesearchreport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String sus_no,

			HttpServletRequest request) {
		
		ArrayList<ArrayList<String>>  list=dao.getDataBasedOnRoleSusNo(session,request);
	Mmap.put("list", list);
		
		
		return new ModelAndView("mergeSearchtiles");

	}
	
	
	
	@RequestMapping(value = "/Download_Form_part1_part2", method = RequestMethod.POST)
	public ModelAndView Download_Form_part1_part2(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "susno3", required = false) String roleSusNo) throws ParseException {
		ArrayList<ArrayList<String>> list = null;
		ArrayList<ArrayList<String>> list1 = null;
		   List<String> TH = new ArrayList<String>();
		   List<TB_MISO_INSP_ESTABLISHMENT> getEstablishmentAttachedAction = getdatamodelwise(roleSusNo,"TB_MISO_INSP_ESTABLISHMENT");
	        List<TB_INSP_EQUP_ANNUAL_METERAGE> getequpAnnualMeterageData = getdatamodelwise(roleSusNo,"TB_INSP_EQUP_ANNUAL_METERAGE"); 
	        ArrayList<ArrayList<String>> equimentData = dao.getTableData(session, "tb_insp_equp_a_b_c_veh"); 
	       List<TB_MISO_INSP_ANIMAL> getAnimal_Data = getdatamodelwise(roleSusNo,"TB_MISO_INSP_ANIMAL");  
	        List<TB_MISO_INSP_DEFI_EQUP_EFFI> defiequp = getdatamodelwise(roleSusNo,"TB_MISO_INSP_DEFI_EQUP_EFFI");  
	        List<TB_MISO_INSP_DTL_OF_EQUP_OFFROAD> defiequpRnder = getdatamodelwise(roleSusNo,"TB_MISO_INSP_DTL_OF_EQUP_OFFROAD"); 
	        List<TB_MISO_INSP_STATE_FUNDS> stateFund = getdatamodelwise(roleSusNo,"TB_MISO_INSP_STATE_FUNDS"); 
	        List<TB_MISO_INSP_WT_RESULT_OTHER> wtResult = getdatamodelwise(roleSusNo,"TB_MISO_INSP_WT_RESULT_OTHER"); 
	        List<TB_INSP_EDUCATION_STANDARDS> educationStandard = getdatamodelwise(roleSusNo,"TB_INSP_EDUCATION_STANDARDS");
	        List<TB_MISO_INSP_COURSES_CAT_A_B> availabilityofPersonnel= getdatamodelwise(roleSusNo,"TB_MISO_INSP_COURSES_CAT_A_B");
	    	
	        List<TB_MISO_INSP_UPGRADATION> upgradation= getdatamodelwise(roleSusNo,"TB_MISO_INSP_UPGRADATION");
	        List<TB_MISO_INSP_REG_LANG_EXAM> regimentaLanguage= getdatamodelwise(roleSusNo,"TB_MISO_INSP_REG_LANG_EXAM");
	        List<TB_MISO_INSP_BPET_RESULT> bpetResult= getdatamodelwise(roleSusNo,"TB_MISO_INSP_BPET_RESULT");
	        List<TB_MISO_INSP_PPT_RESULT> pptResult= getdatamodelwise(roleSusNo,"TB_MISO_INSP_PPT_RESULT");
	        List<TB_MISO_INSP_PROMOTION_EXAM> promotionExam= getdatamodelwise(roleSusNo,"TB_MISO_INSP_PROMOTION_EXAM");
	        List<TB_INSP_FINANCIAL_GRANTS> financialGrant= getdatamodelwise(roleSusNo,"TB_INSP_FINANCIAL_GRANTS");
	        List<TB_INSP_REGT_FUNDS> regtFunds= getdatamodelwise(roleSusNo,"TB_INSP_REGT_FUNDS");
	       List<TB_MISO_INSP_TRAINING_AMMUNITION> trainingAmmunition= getdatamodelwise(roleSusNo,"TB_MISO_INSP_TRAINING_AMMUNITION");
	        
	        List<TB_INSP_RPT_CLASSIFICATION_RANGES> availabilityRangesTbl1= getdatamodelwise(roleSusNo,"TB_INSP_RPT_CLASSIFICATION_RANGES");
	        List<TB_INSP_FFRS> availabilityRangesTbl2= getdatamodelwise(roleSusNo,"TB_INSP_FFRS");       
	        
	        List<TB_INSP_OUTSTANDING_AUDIT> outstandingAudit= getdatamodelwise(roleSusNo,"TB_INSP_OUTSTANDING_AUDIT");
	        
	        List<TB_DETAIL_COURSES> DetailsofCoursestbl1= getdatamodelwise(roleSusNo,"TB_DETAIL_COURSES");        
	        List<TB_INSP_STANDARDS_ACHIEVED> DetailsofCoursestbl2= getdatamodelwise(roleSusNo,"TB_INSP_STANDARDS_ACHIEVED");
	        
	        List<TB_MISO_INSP_SUMMARY_TECH> SummaryofTechnicalInspection1= getdatamodelwise(roleSusNo,"TB_MISO_INSP_SUMMARY_TECH");
	        List<TB_MISO_INSP_SUMMARY_TECH_OTHER> SummaryofTechnicalInspection2= getdatamodelwise(roleSusNo,"TB_MISO_INSP_SUMMARY_TECH_OTHER");
	        
	        List<TB_INSP_OUTSTANDING_RENT_ALLIED> outstandingRentAllied= getdatamodelwise(roleSusNo,"TB_INSP_OUTSTANDING_RENT_ALLIED");        
	        List<TB_INSP_OUTSTANDING_LOSS_MT> outstandingLossMt= getdatamodelwise(roleSusNo,"TB_INSP_OUTSTANDING_LOSS_MT");  
	        List<TB_INSP_FATAL_INCIDENTS> fatalIncident= getdatamodelwise(roleSusNo,"TB_INSP_FATAL_INCIDENTS");
	        List<TB_INSP_SECURITY_LAPSES> securityLapses= getdatamodelwise(roleSusNo,"TB_INSP_SECURITY_LAPSES");
	        List<TB_INSP_OUTSIDE_ATTACHMENTS> outsideAttachments= getdatamodelwise(roleSusNo,"TB_INSP_OUTSIDE_ATTACHMENTS");
	        List<TB_INSP_TD_PROCEEDED> tdProcceded= getdatamodelwise(roleSusNo,"TB_INSP_TD_PROCEEDED");
	        List<TB_INSP_SOCIAL_MEDIA_LAPSES> socialMediaLapses= getdatamodelwise(roleSusNo,"TB_INSP_SOCIAL_MEDIA_LAPSES");
	        List<TB_INSP_PIO_CALL_LAPSES > pioCallLapses= getdatamodelwise(roleSusNo,"TB_INSP_PIO_CALL_LAPSES ");        
	        List<TB_INSP_ESPIONAGE_LAPSES> espionageLapses= getdatamodelwise(roleSusNo,"TB_INSP_ESPIONAGE_LAPSES");
	        List<TB_INSP_CELL_PHONE_LAPSES > cellPhoneLapses= getdatamodelwise(roleSusNo,"TB_INSP_CELL_PHONE_LAPSES ");
	        List<TB_INSP_FS_SECURITY_LAPSES> fsSecurityLapses= getdatamodelwise(roleSusNo,"TB_INSP_FS_SECURITY_LAPSES");
	        List<TB_INSP_LOST_CDS_DVDS> lostCdsDvds= getdatamodelwise(roleSusNo,"TB_INSP_LOST_CDS_DVDS");
	        List<TB_INSP_LOST_ID_ECR> lostIdEcr= getdatamodelwise(roleSusNo,"TB_INSP_LOST_ID_ECR");        
	        List<TB_MISO_INSP_LAND> land= getdatamodelwise(roleSusNo,"TB_MISO_INSP_LAND");
	        
        Map<String, String> phase2Data = dao2.getphase2data(session, roleSusNo);
		Map<String, String> digitalSign = dao2.getdataForDigitalDigitalSign(session, roleSusNo);
		List<Map<String, String>> weaponDeficiencyList = dao2.weaponDeficiencyList(session, roleSusNo);
		List<Map<String, String>> getciricaldefimanpowerlist = dao2.getciricaldefimanpowerlist(session, roleSusNo);
		List<Map<String, String>> discipline = dao2.getDisciplineCourtMaritalDisposallist(session, roleSusNo);

		ModelAndView mav = new ModelAndView(new PDF_ANNUAL_REPORT_PART_1_2_MERGED("P", TH, phase2Data,list1,digitalSign,weaponDeficiencyList,getciricaldefimanpowerlist,discipline), "userList", list);

		mav.addObject("UpGradationData", upgradation);
        mav.addObject("getEstablishmentAttached", getEstablishmentAttachedAction);
        mav.addObject("getEqupAnnualMeterage", getequpAnnualMeterageData);
        mav.addObject("equipmentData", equimentData);
        mav.addObject("getAnimalData", getAnimal_Data);
        mav.addObject("defiEqup", defiequp);
        mav.addObject("defiEqupRender", defiequpRnder);
        mav.addObject("stateFund", stateFund);
        mav.addObject("wtResult", wtResult);
        mav.addObject("educationStandard", educationStandard);
        mav.addObject("availabilityOfPersonnel", availabilityofPersonnel);
        mav.addObject("regimentalLanguage", regimentaLanguage);
        mav.addObject("bpetResult", bpetResult);
       mav.addObject("pptResult", pptResult);
       mav.addObject("promotionExam", promotionExam);
        mav.addObject("financialGrant", financialGrant);
        mav.addObject("regtFunds", regtFunds);
       mav.addObject("trainingAmmunition", trainingAmmunition);
       mav.addObject("availabilityRangesTbl1", availabilityRangesTbl1);
       mav.addObject("availabilityRangesTbl2", availabilityRangesTbl2);
        mav.addObject("outstandingAudit", outstandingAudit);
       mav.addObject("detailsOfCoursesTbl1", DetailsofCoursestbl1);
        mav.addObject("detailsOfCoursesTbl2", DetailsofCoursestbl2);
        mav.addObject("summaryOfTechnicalInspection1", SummaryofTechnicalInspection1);
        mav.addObject("SummaryofTechnicalInspection2", SummaryofTechnicalInspection2);
        mav.addObject("outstandingRentAllied", outstandingRentAllied);
        mav.addObject("outstandingLossMt", outstandingLossMt);
        mav.addObject("fatalIncident", fatalIncident);
        mav.addObject("securityLapses", securityLapses);
        mav.addObject("outsideAttachments", outsideAttachments);
        mav.addObject("tdProceeded", tdProcceded);
        mav.addObject("socialMediaLapses", socialMediaLapses);
        mav.addObject("pioCallLapses", pioCallLapses);
        mav.addObject("espionageLapses", espionageLapses);
        mav.addObject("cellPhoneLapses", cellPhoneLapses);
        mav.addObject("fsSecurityLapses", fsSecurityLapses);
        mav.addObject("lostCdsDvds", lostCdsDvds);
        mav.addObject("lostIdEcr", lostIdEcr);
        System.out.println("----------------------/"+land.size());
        mav.addObject("land", land);
		
		return mav;
	}

	

	
	
	@RequestMapping(value = "/reportsAddMoreAction", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> reportsAddMoreAction(Authentication authentication,
			HttpServletRequest request, HttpSession session, @RequestParam Map<String, String> allParams) {

		Map<String, Object> response = new HashMap<>();
		Session hibernateSession = null;
		Transaction transaction = null;

		try {
			hibernateSession = HibernateUtil.getSessionFactory().openSession();
			transaction = hibernateSession.beginTransaction();

			String buttonContext = allParams.get("buttonContext");
			String roleSusNo = (String) session.getAttribute("roleSusNo");
			String username = (String) session.getAttribute("username");
			String currentYear = String.valueOf(java.time.Year.now().getValue());

			if (roleSusNo == null || username == null) {
				throw new IllegalStateException("roleSusNo or username is null in session.");
			}
			switch (buttonContext) {
			case "upGradation":
				saveUpgradationRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "personalTrainedCourses":
				savePersonalTrainedCoursesRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "defOfEqupMaintenance":
				saveDefOfEqupMaintenanceRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "financialGrants":
				saveFinancialGrantsRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "classSave":
				saveClassSaveRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "ffrsSave":
				saveFfrsSaveRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "equipmentTble":
				saveEquipmentTbleRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "trainingAmmunitionTble":
				saveTrainingAmmunitionTbleRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "summaryTble":
				saveSummaryTbleRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "OutstandingTble":
				saveOutstandingTbleRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "OutstandingMTTble":
				saveOutstandingMTTbleRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "fatalIncidentsTble":
				saveFatalIncidentsTbleRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "securityLapsesTble":
				saveSecurityLapsesTbleRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "outsideAttachmentsTble":
				saveOutsideAttachmentsTbleRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "coursesUndertaken":
				saveCoursesUndertakenRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "otherCourses":
				saveOtherCoursesRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "tdOfficers":
				saveTdOfficersRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "socialMediaLapses":
				saveSocialMediaLapsesRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "pioCallsLapses":
				savePioCallsLapsesRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "espionageLapses":
				saveEspionageLapsesRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "cellPhoneCompromiseLapses":
				saveCellPhoneCompromiseLapsesRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "UntraceableTble":
				saveUntraceableTbleRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "lossCdDvdTble":
				savelossCdDvdTbleRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "lostIdEcrTble":
				savelostIdEcrTbleRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			case "regtFunds":
				saveRegtFundsRecord(hibernateSession, allParams, roleSusNo, username, currentYear);
				break;
			default:
				throw new IllegalArgumentException("Invalid buttonContext: " + buttonContext);
			}

			transaction.commit();
			response.put("success", true);
			response.put("message", "Data saved successfully!");
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			response.put("success", false);
			response.put("message", "Error saving data: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {
			if (hibernateSession != null) {
				hibernateSession.close();
			}
		}
	}

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private void saveUpgradationRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) {
		TB_MISO_INSP_UPGRADATION record = new TB_MISO_INSP_UPGRADATION();

		record.setTrade(allParams.get("trade"));
		record.setAffected_up_gradation_class_iv(allParams.get("affected_up_gradation_class_iv"));
		record.setAffected_up_gradation_class_iii(allParams.get("affected_up_gradation_class_iii"));
		record.setAffected_up_gradation_class_ii(allParams.get("affected_up_gradation_class_ii"));
		record.setAffected_up_gradation_class_i(allParams.get("affected_up_gradation_class_i"));
		record.setDuring_up_gradation_class_iv(allParams.get("during_up_gradation_class_iv"));
		record.setDuring_up_gradation_class_iii(allParams.get("during_up_gradation_class_iii"));
		record.setDuring_up_gradation_class_ii(allParams.get("during_up_gradation_class_ii"));
		record.setDuring_up_gradation_class_i(allParams.get("during_up_gradation_class_i"));
		record.setTotal_available_class_iv(allParams.get("total_available_class_iv"));
		record.setTotal_available_class_iii(allParams.get("total_available_class_iii"));
		record.setTotal_available_class_ii(allParams.get("total_available_class_ii"));
		record.setTotal_available_class_i(allParams.get("total_available_class_i"));
		record.setStatus("0");
		record.setYear(currentYear);
		record.setSus_no(roleSusNo);
		record.setCreated_by(username);
		record.setCreated_date(new Date());
		session.save(record);
	}

	private void saveFinancialGrantsRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) {
		TB_INSP_FINANCIAL_GRANTS record = new TB_INSP_FINANCIAL_GRANTS();
		record.setType_of_grant(allParams.get("typeOfFund"));
		record.setAmt_auth(allParams.get("amtAuth"));
		record.setAmt_alloted(allParams.get("amtAlloted"));
		record.setReason_for_over_under(allParams.get("reasonOverUnder"));
		record.setAmt_expended(allParams.get("amtExpended"));
		record.setAmt_utilised(allParams.get("amtUnutilised"));
		record.setReason_for_non_util(allParams.get("reasonNonUtil"));
		record.setRemarks(allParams.get("detailedRemarks"));
		record.setCreated_by(username);
		record.setCreated_date(new Date());
		record.setStatus(0);
		record.setYear(currentYear);
		record.setSus_no(roleSusNo);
		session.save(record);
	}

	private void saveClassSaveRecord(Session session, Map<String, String> allParams, String roleSusNo, String username,
			String currentYear) {
		TB_INSP_RPT_CLASSIFICATION_RANGES record = new TB_INSP_RPT_CLASSIFICATION_RANGES();
		String tempString = allParams.get("whenUsed");
		Date tempDate = null;
		if (tempString != null && !tempString.isEmpty()) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			try {
				tempDate = format.parse(tempString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		record.setNumber_of_ranges(allParams.get("numberOfRanges"));
		record.setWhen_used(tempDate);
		record.setDifficulties_experienced(allParams.get("difficultiesExperienced"));

		record.setCreated_by(username);
		record.setCreated_on(new Date().toString());
		record.setStatus(0);
		record.setYear(currentYear);
		record.setSus_no(roleSusNo);
		session.save(record);
	}

	private void saveFfrsSaveRecord(Session session, Map<String, String> allParams, String roleSusNo, String username,
			String currentYear) {
		TB_INSP_FFRS record = new TB_INSP_FFRS();

		String tempString = allParams.get("whenUsedFfrs");
		Date tempDate = null;
		if (tempString != null && !tempString.isEmpty()) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			try {
				tempDate = format.parse(tempString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		record.setRange_available(allParams.get("rangeAvailable"));
		record.setRange_utilized(allParams.get("rangeUtilized"));
		record.setWhenusedffrs(tempDate);
		record.setDifficulties_experienced(allParams.get("difficultiesFfrs"));

		record.setCreated_by(username);
		record.setCreated_on(new Date());
		record.setStatus(0);
		record.setYear(currentYear);
		record.setSus_no(roleSusNo);
		session.save(record);
	}

	private void saveRegtFundsRecord(Session session, Map<String, String> allParams, String roleSusNo, String username,
			String currentYear) {
		TB_INSP_REGT_FUNDS record = new TB_INSP_REGT_FUNDS();
		record.setName_of_acct(allParams.get("nameOfAcct"));
		record.setAsset_i(allParams.get("assetI"));
		record.setAsset_ii(allParams.get("assetII"));
		record.setAsset_iii(allParams.get("assetIII"));
		record.setLiability_i(allParams.get("liabilityI"));
		record.setLiability_ii(allParams.get("liabilityII"));
		record.setLiability_iii(allParams.get("liabilityIII"));

		record.setYeari(allParams.get("yearI"));
		record.setYearii(allParams.get("yearII"));
		record.setYeariii(allParams.get("yearIII"));

		record.setIncr_decr_acct(allParams.get("incrDecrAcct"));
		record.setCreated_by(username);
		record.setCreated_date(new Date());
		record.setStatus(0);
		record.setYear(currentYear);
		record.setSus_no(roleSusNo);
		session.save(record);
	}

	private void saveTrainingAmmunitionTbleRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) {
		TB_MISO_INSP_TRAINING_AMMUNITION record = new TB_MISO_INSP_TRAINING_AMMUNITION();
		record.setType_of_ammunition(allParams.get("typeOfAmmunation"));
		record.setAu(allParams.get("tmAuth"));
		record.setQty_auth_full_trainning(allParams.get("tm_qunt_year"));
		record.setRecive_inclu_carried_forward(allParams.get("received_inc_carry_forward"));
		record.setExpended(allParams.get("tmExpended"));
		record.setBalance(allParams.get("tmBlance"));
		record.setReason(allParams.get("tmreason"));
		record.setCreated_by(username);
		record.setCreated_date(new Date());
		record.setSus_no(roleSusNo);
		record.setYear(currentYear);
		record.setStatus(0);
		session.save(record);
	}

	private void saveSummaryTbleRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) throws ParseException {
		TB_MISO_INSP_SUMMARY_TECH_OTHER record = new TB_MISO_INSP_SUMMARY_TECH_OTHER();
		record.setSus_no(roleSusNo);
		record.setType_of_tech_insp(allParams.get("typeOfTechinsp"));
		record.setDate(formatDateToDDMMYYYY(allParams.get("otherinspDate")));
		record.setBy_whome(allParams.get("byWhomOther"));
		record.setResult(allParams.get("resultOther"));
		record.setRemarks(allParams.get("remarkOther"));
		record.setCreated_by(username);
		record.setCreated_date(new Date());
		record.setYear(currentYear);
		record.setStatus(0);
		session.save(record);
	}

	private void saveOutstandingTbleRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) {
		TB_INSP_OUTSTANDING_RENT_ALLIED record = new TB_INSP_OUTSTANDING_RENT_ALLIED();
		record.setAmount_outstanding(allParams.get("amount_outstanding"));
		record.setOn_acc(allParams.get("on_acc"));
		record.setRemarks(allParams.get("remarks"));
		record.setOutstanding_year(allParams.get("year"));
		record.setCreated_by(username);
		record.setCreated_on(new Date());
		record.setSus_no(roleSusNo);
		record.setYear(currentYear);
		record.setStatus(0);
		session.save(record);
	}

	private void saveOutstandingMTTbleRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) {
		TB_INSP_OUTSTANDING_LOSS_MT record = new TB_INSP_OUTSTANDING_LOSS_MT();
		record.setNature_of_loss(allParams.get("nature_of_loss"));
		record.setValue(allParams.get("value"));
		record.setRemarks(allParams.get("remarks"));
		record.setOutstanding_year(allParams.get("year"));
		record.setCreated_by(username);
		record.setCreated_on(new Date());
		record.setSus_no(roleSusNo);
		record.setYear(currentYear);
		record.setStatus(0);
		session.save(record);
	}

	private void saveFatalIncidentsTbleRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) {
		TB_INSP_FATAL_INCIDENTS record = new TB_INSP_FATAL_INCIDENTS();
		record.setDate(allParams.get("date"));
		record.setNature_of_insident(allParams.get("nature_of_insident"));
		record.setRemarks(allParams.get("remarks"));
		record.setCasualty(allParams.get("casualty"));
		record.setCreated_by(username);
		record.setCreated_on(new Date());
		record.setSus_no(roleSusNo);
		record.setYear(currentYear);
		record.setStatus(0);
		session.save(record);
	}

	private void saveSecurityLapsesTbleRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) {
		TB_INSP_SECURITY_LAPSES record = new TB_INSP_SECURITY_LAPSES();
		record.setDateof(allParams.get("date"));
		record.setNature_of_security_lapse(allParams.get("nature_of_security_lapse"));
		record.setRemarks(allParams.get("remarks"));
		record.setResulted_in(allParams.get("resulted_in"));
		record.setCreated_by(username);
		record.setCreated_on(new Date());
		record.setSus_no(roleSusNo);
		record.setYear(currentYear);
		record.setStatus(0);
		session.save(record);
	}

	private void saveOutsideAttachmentsTbleRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) {
		TB_INSP_OUTSIDE_ATTACHMENTS record = new TB_INSP_OUTSIDE_ATTACHMENTS();
		record.setLocation(allParams.get("location"));
		record.setNumber_of_personnel_attached(allParams.get("number_of_personnel_attached"));
		record.setRemarks(allParams.get("remarks"));
		record.setDuration(allParams.get("duration"));
		record.setCreated_by(username);
		record.setCreated_on(new Date());
		record.setSus_no(roleSusNo);
		record.setYear(currentYear);
		record.setStatus(0);
		session.save(record);
	}

	private void saveCoursesUndertakenRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) throws ParseException {
		TB_DETAIL_COURSES record = new TB_DETAIL_COURSES();
		record.setCourse_name(allParams.get("courseNameUndertaken"));
		record.setNumber_of_course(allParams.get("numberOfCourse"));
		record.setPeriod_from(dateFormat.parse(allParams.get("periodFrom")));
		record.setPeriod_to(dateFormat.parse(allParams.get("periodTo")));
		record.setTotal_allotted(allParams.get("totalAllotted"));
		record.setAttended(allParams.get("attended"));
		record.setRtu(allParams.get("rtu"));
		record.setDetailed_remarks(allParams.get("detailedRemarks"));

		record.setSus_no(roleSusNo);
		record.setStatus(0);
		record.setYear(currentYear);
		record.setCreated_by(username);
		record.setCreated_on(new Date());
		session.save(record);
	}

	private void saveOtherCoursesRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) {
		TB_INSP_STANDARDS_ACHIEVED record = new TB_INSP_STANDARDS_ACHIEVED();
		record.setCourse_name(allParams.get("courseNameOther"));
		record.setTotal(allParams.get("totalOther"));
		record.setGrading(allParams.get("gradingOther"));
		record.setDa(allParams.get("daOther"));
		record.setFailed(allParams.get("failedOther"));
		record.setRtu(allParams.get("rtuOther"));
		record.setDetailed_remarks(allParams.get("detailedRemarksOther"));

		record.setSus_no(roleSusNo);
		record.setStatus(0);
		record.setYear(currentYear);
		record.setCreated_by(username);
		record.setCreated_on(new Date());
		session.save(record);
	}

	private void saveTdOfficersRecord(Session session, Map<String, String> allParams, String roleSusNo, String username,
			String currentYear) {
		TB_INSP_TD_PROCEEDED record = new TB_INSP_TD_PROCEEDED();
		record.setRank_and_name(allParams.get("rankAndName"));
		record.setNature_duty(allParams.get("natureOfDuty"));
		record.setOrdered_by(allParams.get("orderedBy"));
		record.setDetailed_remarks(allParams.get("detailedRemarksTD"));

		record.setSus_no(roleSusNo);
		record.setStatus(0);
		record.setYear(currentYear);
		record.setCreated_by(username);
		record.setCreated_on(new Date());
		session.save(record);
	}

	private void saveSocialMediaLapsesRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) throws ParseException {
		TB_INSP_SOCIAL_MEDIA_LAPSES record = new TB_INSP_SOCIAL_MEDIA_LAPSES();

		record.setDate_violation_initial(dateFormat.parse(allParams.get("SMLdateViolationReportedHQ")));
		record.setDate_violation_fmn(dateFormat.parse(allParams.get("SMLdateViolationReportedUnit")));
		record.setNumber_rank_name(allParams.get("SMLnumberRankName"));
		record.setCurr_status(allParams.get("SMLcurrentStatus"));
		record.setLoss_info(allParams.get("SMLlossOfInformation"));
		record.setRemarks(allParams.get("SMLremarks"));

		record.setSus_no(roleSusNo);
		record.setStatus(0);
		record.setYear(currentYear);
		record.setCreated_by(username);
		record.setCreated_on(new Date());
		session.save(record);
	}

	private void savePioCallsLapsesRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) throws ParseException {
		TB_INSP_PIO_CALL_LAPSES record = new TB_INSP_PIO_CALL_LAPSES();
		record.setDate_violation_initial(dateFormat.parse(allParams.get("PCLdateViolationReportedHQ")));
		record.setDate_violation_fmn(dateFormat.parse(allParams.get("PCLdateViolationReportedUnit")));
		record.setNumber_rank_name(allParams.get("PCLnumberRankName"));
		record.setCurr_status(allParams.get("PCLcurrentStatus"));
		record.setLoss_info(allParams.get("PCLlossOfInformation"));
		record.setRemarks(allParams.get("PCLremarks"));

		record.setSus_no(roleSusNo);
		record.setStatus(0);
		record.setYear(currentYear);
		record.setCreated_by(username);
		record.setCreated_on(new Date());
		session.save(record);
	}

	private void saveEspionageLapsesRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) throws ParseException {
		TB_INSP_ESPIONAGE_LAPSES record = new TB_INSP_ESPIONAGE_LAPSES();
		record.setDate_violation_initial(dateFormat.parse(allParams.get("ELdateViolationReportedHQ")));
		record.setDate_violation_fmn(dateFormat.parse(allParams.get("ELdateViolationReportedUnit")));
		record.setNumber_rank_name(allParams.get("ELnumberRankName"));
		record.setCurr_status(allParams.get("ELcurrentStatus"));
		record.setLoss_info(allParams.get("ELlossOfInformation"));
		record.setRemarks(allParams.get("ELremarks"));

		record.setSus_no(roleSusNo);
		record.setStatus(0);
		record.setYear(currentYear);
		record.setCreated_by(username);
		record.setCreated_on(new Date());
		session.save(record);
	}

	private void saveCellPhoneCompromiseLapsesRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) throws ParseException {
		TB_INSP_CELL_PHONE_LAPSES record = new TB_INSP_CELL_PHONE_LAPSES();
		record.setDate_violation_initial(dateFormat.parse(allParams.get("CPCLdateViolationReportedHQ")));
		record.setDate_violation_fmn(dateFormat.parse(allParams.get("CPCLdateViolationReportedUnit")));
		record.setNumber_rank_name(allParams.get("CPCLnumberRankName"));
		record.setCurr_status(allParams.get("CPCLcurrentStatus"));
		record.setLoss_info(allParams.get("CPCLlossOfInformation"));
		record.setRemarks(allParams.get("CPCLremarks"));

		record.setSus_no(roleSusNo);
		record.setStatus(0);
		record.setYear(currentYear);
		record.setCreated_by(username);
		record.setCreated_on(new Date());
		session.save(record);
	}

	private void saveUntraceableTbleRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) throws ParseException {
		TB_INSP_FS_SECURITY_LAPSES record = new TB_INSP_FS_SECURITY_LAPSES();
		record.setUntraceable_document(allParams.get("untraceable_document"));
		record.setClassification(allParams.get("classification"));
		record.setOriginator_doc(allParams.get("originator_doc"));
		record.setCustodian_doc(allParams.get("custodian_doc"));
		record.setCurrent_status(allParams.get("current_status"));
		record.setRemarks(allParams.get("remarks"));

		record.setCreated_by(username);
		record.setCreated_on(new Date());
		record.setSus_no(roleSusNo);
		record.setYear(currentYear);
		record.setStatus(0);
		session.save(record);
	}

	private void savelossCdDvdTbleRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) throws ParseException {
		TB_INSP_LOST_CDS_DVDS record = new TB_INSP_LOST_CDS_DVDS();
		record.setUntraceble_cd_dvd(allParams.get("untraceble_cd_dvd"));
		record.setClassification(allParams.get("classification"));
		record.setOriginator_cd(allParams.get("originator_cd"));
		record.setCustodian_cd(allParams.get("custodian_cd"));
		record.setCurrent_status(allParams.get("current_status"));
		record.setRemarks(allParams.get("remarks"));

		record.setCreated_by(username);
		record.setCreated_on(new Date());
		record.setSus_no(roleSusNo);
		record.setYear(currentYear);
		record.setStatus(0);
		session.save(record);
	}

	private void savelostIdEcrTbleRecord(Session session, Map<String, String> allParams, String roleSusNo,
			String username, String currentYear) throws ParseException {
		TB_INSP_LOST_ID_ECR record = new TB_INSP_LOST_ID_ECR();
		record.setUntraceable_document_token(allParams.get("untraceable_document_token"));
		record.setClassification(allParams.get("classification"));
		record.setOriginator_doc_ecr(allParams.get("originator_doc_ecr"));
		record.setCustodian_doc_ecr(allParams.get("custodian_doc_ecr"));
		record.setCurrent_status(allParams.get("current_status"));
		record.setRemarks(allParams.get("remarks"));

		record.setCreated_by(username);
		record.setCreated_on(new Date());
		record.setSus_no(roleSusNo);
		record.setYear(currentYear);
		record.setStatus(0);
		session.save(record);
	}

	@RequestMapping(value = "/getTbleAddData", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getTbleAddData(Authentication authentication, HttpServletRequest request,
			HttpSession session, @RequestParam(value = "sus_no", required = false) String sus_no,
			@RequestParam(value = "tblname", required = false) String tblname) {

		Map<String, Object> response = new HashMap<>();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		try {
			String normalizedTblName = tblname.replace("Delete", "");
			Map<String, Supplier<List<?>>> dataProviders = new HashMap<>();

			dataProviders.put("upGradation", () -> dao.getupGradationdataAction(roleSusNo));
			dataProviders.put("personalTrainedCourses", () -> dao.getCourseCatAandBAction(roleSusNo));
			dataProviders.put("defOfEqupMaintenance", () -> dao.getInspectionDefiEqpEffiData(roleSusNo));
			dataProviders.put("financialGrants", () -> dao.getFinancialGrantsAction(roleSusNo));
			dataProviders.put("classSave", () -> dao.getClassSaveAction(roleSusNo));
			dataProviders.put("ffrsSave", () -> dao.getFfrsSaveAction(roleSusNo));
			dataProviders.put("equipmentTble", () -> dao.getequpAnnualMeterageData(roleSusNo));
			dataProviders.put("trainingAmmunitionTble", () -> dao.gettrainningAmmunationData(roleSusNo));
			dataProviders.put("summaryTble", () -> dao.getSummaryTechInspOtherData(roleSusNo));
			dataProviders.put("OutstandingTble", () -> dao.getoutstandingData(roleSusNo));
			dataProviders.put("OutstandingMTTble", () -> dao.getoutstandingLossMTData(roleSusNo));
			dataProviders.put("fatalIncidentsTble", () -> dao.getfatalIncidentData(roleSusNo));
			dataProviders.put("securityLapsesTble", () -> dao.getsequrityLapsesData(roleSusNo));
			dataProviders.put("outsideAttachmentsTble", () -> dao.getoutsideAttachmentData(roleSusNo));
			dataProviders.put("coursesUndertaken", () -> dao.getCourseUndertakenAction(roleSusNo, session));
			dataProviders.put("otherCourses", () -> dao.getOtherCoursesAction(roleSusNo, session));
			dataProviders.put("tdOfficers", () -> dao.getTDOfficersAction(roleSusNo, session));
			dataProviders.put("socialMediaLapses", () -> dao.getSMLAction(roleSusNo, session));
			dataProviders.put("pioCallsLapses", () -> dao.getPCLAction(roleSusNo, session));
			dataProviders.put("espionageLapses", () -> dao.getELAction(roleSusNo, session));
			dataProviders.put("cellPhoneCompromiseLapses", () -> dao.getCPCLAction(roleSusNo, session));
			// dataProviders.put("outsideAttachmentsTble", () ->
			// dao.getuntracebleData(roleSusNo));
			dataProviders.put("lossCdDvdTble", () -> dao.getlostCdDvdData(roleSusNo));
			dataProviders.put("lostIdEcrTble", () -> dao.getlostidEcrData(roleSusNo));
			dataProviders.put("UntraceableTble", () -> dao.getInspFsSecurityData(roleSusNo));
			dataProviders.put("regtFunds", () -> dao.getRegtFundsAction(roleSusNo));
			Supplier<List<?>> dataProvider = dataProviders.get(normalizedTblName);
			if (dataProvider != null) {
				List<?> list = dataProvider.get();
				processDataAndSetResponse(list, roleSusNo, response);
			} else {
				response.put("success", false);
				response.put("message", "Unknown table type");
				response.put("data", new ArrayList<>());
			}

		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "Something went wrong");
			response.put("data", new ArrayList<>());
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void processDataAndSetResponse(List<?> list, String roleSusNo, Map<String, Object> response) {
		if (list != null && !list.isEmpty()) {
			response.put("success", true);
			response.put("data", list);
		} else {
			response.put("success", false);
			response.put("data", new ArrayList<>());
		}
	}

	@RequestMapping(value = "/deleteTbleRowAction", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteTbleRowAction(Authentication authentication,
			HttpServletRequest request, HttpSession session, @RequestParam(value = "id", required = false) int id,
			@RequestParam(value = "tblName", required = false) String tblName) {

		Map<String, Object> response = new HashMap<>();
		Session ses1 = null;
		Transaction t2 = null;

		try {

			Map<String, String> tableNameMap = new HashMap<>();
			tableNameMap.put("upGradationDelete", "TB_MISO_INSP_UPGRADATION");
			tableNameMap.put("personalTrainedCoursesDelete", "TB_MISO_INSP_COURSES_CAT_A_B");
			tableNameMap.put("defOfEqupMaintenanceDelete", "TB_MISO_INSP_DEFI_EQUP_EFFI");
			tableNameMap.put("financialGrantsDelete", "TB_INSP_FINANCIAL_GRANTS");
			tableNameMap.put("classSaveDelete", "TB_INSP_RPT_CLASSIFICATION_RANGES");
			tableNameMap.put("ffrsSaveDelete", "TB_INSP_FFRS");
			tableNameMap.put("equipmentTbleDelete", "TB_INSP_EQUP_ANNUAL_METERAGE");
			tableNameMap.put("trainingAmmunitionTbleDelete", "TB_MISO_INSP_TRAINING_AMMUNITION");
			tableNameMap.put("summaryTbleDelete", "TB_MISO_INSP_SUMMARY_TECH_OTHER");
			tableNameMap.put("OutstandingTbleDelete", "TB_INSP_OUTSTANDING_RENT_ALLIED");
			tableNameMap.put("OutstandingMTTbleDelete", "TB_INSP_OUTSTANDING_LOSS_MT");
			tableNameMap.put("fatalIncidentsTbleDelete", "TB_INSP_FATAL_INCIDENTS");
			tableNameMap.put("securityLapsesTbleDelete", "TB_INSP_SECURITY_LAPSES");
			tableNameMap.put("outsideAttachmentsTbleDelete", "TB_INSP_OUTSIDE_ATTACHMENTS");
			tableNameMap.put("coursesUndertakenDelete", "TB_DETAIL_COURSES");
			tableNameMap.put("otherCoursesDelete", "TB_INSP_STANDARDS_ACHIEVED");
			tableNameMap.put("tdOfficersDelete", "TB_INSP_TD_PROCEEDED");
			tableNameMap.put("socialMediaLapsesDelete", "TB_INSP_SOCIAL_MEDIA_LAPSES");
			tableNameMap.put("pioCallsLapsesDelete", "TB_INSP_PIO_CALL_LAPSES");
			tableNameMap.put("espionageLapsesDelete", "TB_INSP_ESPIONAGE_LAPSES");
			tableNameMap.put("cellPhoneCompromiseLapsesDelete", "TB_INSP_CELL_PHONE_LAPSES");
			tableNameMap.put("UntraceableTbleDelete", "TB_INSP_FS_SECURITY_LAPSES");
			tableNameMap.put("lossCdDvdTbleDelete", "TB_INSP_LOST_CDS_DVDS ");
			tableNameMap.put("lostIdEcrTbleDelete", "TB_INSP_LOST_ID_ECR ");
			tableNameMap.put("UntraceableTbleDelete", "TB_INSP_FS_SECURITY_LAPSES ");
			tableNameMap.put("regtFundsDelete", "TB_INSP_REGT_FUNDS");

			String tableName = tableNameMap.get(tblName);
			if (tableName == null) {
				response.put("success", false);
				response.put("message", "Unknown table type");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

			String queryString = "delete from " + tableName + " where id = :id";

			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery(queryString);
			q.setParameter("id", id);
			int rowCount = q.executeUpdate();

			t2.commit();

			boolean isDeleted = rowCount > 0;
			response.put("success", isDeleted);
			response.put("message", isDeleted ? "Data deleted Successfully" : "Data Not deleted Successfully");

		} catch (Exception e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			response.put("success", false);
			response.put("message", "Something went wrong");
		} finally {
			if (ses1 != null) {
				ses1.close();
			}
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/formDataSaveAction", produces = "application/json")
	@ResponseBody
	public Map<String, Object> formDataSaveAction(HttpServletRequest request, HttpSession session,
			@RequestParam("buttonContext") String buttonContext,
			@RequestParam(value = "inspData", required = false) String inspData) {
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		Map<String, Object> response = new HashMap<>();
		// manav
		try {
			Map<String, SaveActionHandler> handlers = new HashMap<>();
			// SAVE education_standards_item
			handlers.put("establishment_item", () -> establishmentSaveAction(session, request));
			handlers.put("equipment_item", () -> dao.equipmentAandBvehSaveAction(session, request));
			handlers.put("animals_item", () -> animalSaveAction(session, request, inspData));
			handlers.put("deficiencies_of_equipment2_item", () -> detailEqupSaveAction2(session, request, inspData));
			handlers.put("deficiencies_of_equipment_item", () -> detailEqupSaveAction(session, request, inspData));
			handlers.put("state_of_sector_stores_item", () -> stateOfSectorSaveAction(session, request, inspData));
			handlers.put("wt_results_item", () -> wtResultInspSaveAction(session, request, inspData));
			handlers.put("education_standards_item", () -> educationStandardsItemInspSaveAction(session, request));
			handlers.put("category_item", () -> course_categorySaveAction(session, request));
			handlers.put("up_gradation_item", () -> up_gradation_itemAction(session, request));
			handlers.put("regi_language_exam_item", () -> regLanguageExamSaveAction(session, request));
			handlers.put("bpet_result_item", () -> bpetSaveAction(session, request, inspData));
			handlers.put("ppt_result_item", () -> pptSaveAction(session, request, inspData));
			handlers.put("promotion_exam_officers_item", () -> pramotionExamSaveAction(session, request, inspData));
			handlers.put("financial_grants_item", () -> financial_grants_itemAction(session, request));
			handlers.put("training_ammunition_item", () -> training_ammunition_itemAction(session, request));
			handlers.put("outstanding_audit_objections_observations_item",
					() -> outstandingauditSaveAction(session, request));
			handlers.put("courses_item", () -> courses_itemAction(session, request));
			handlers.put("summarybtn", () -> summaryTechInspSaveAction(session, request, inspData));
			handlers.put("outstanding_item", () -> outstanding_itemAction(session, request));
			handlers.put("mt_accidents_item", () -> mt_accidents_itemAction(session, request));
			handlers.put("details_of_suicides_item", () -> details_of_suicides_itemAction(session, request));
			handlers.put("security_lapses_item", () -> security_lapses_itemAction(session, request));
			handlers.put("details_of_attachments_item", () -> details_of_attachments_itemAction(session, request));
			handlers.put("details_of_officers_item", () -> details_of_officers_itemAction(session, request));
			handlers.put("social_media_violation_item", () -> social_media_violation_itemAction(session, request));
			handlers.put("web_messenger_apps_item", () -> web_messenger_apps_itemAction(session, request));
			handlers.put("espionage_item", () -> espionage_itemAction(session, request));
			handlers.put("compromise_of_cell_phone_item", () -> compromise_of_cell_phone_itemAction(session, request));
			handlers.put("untraceable_item", () -> untraceable_itemAction(session, request));
			handlers.put("loss_of_cd_item", () -> loss_of_cd_itemAction(session, request));
			handlers.put("loss_of_identity_item", () -> loss_of_identity_itemAction(session, request));
			handlers.put("land_item", () -> landSaveAction(session, request));
			handlers.put("summary_of_report_last_five_year_item",
					() -> summary_of_report_last_five_year_itemAction(session, request));
			handlers.put("recruit_training_b_appendix_item",
					() -> recruit_training_b_appendix_itemAction(session, request));
			handlers.put("deffi_exp_resp_to_trainng_item",
					() -> deffi_exp_resp_to_trainng_itemAction(session, request));
			handlers.put("emp_of_unit_during_period_item",
					() -> emp_of_unit_during_period_itemAction(session, request));
			handlers.put("regt_funds_item", () -> regt_funds_itemAction(session, request));

			handlers.put("availability_of_ranges_item", () -> availability_of_ranges_itemAction(session, request));

			boolean save = false;
			if (handlers.containsKey(buttonContext)) {
				save = handlers.get(buttonContext).execute();
				updateMainTblData(buttonContext, roleSusNo, roleType);
			} else {
				throw new IllegalArgumentException("Unknown button context: " + buttonContext);
			}

			response.put("success", save);
			response.put("message", save ? "Data saved successfully" : "Data not saved successfully");

		} catch (Exception e) {

			response.put("success", false);
			response.put("message", "Error saving data: " + e.getMessage());
		}

		return response;
	}

	@RequestMapping(value = "/admin/getPdfForInspReport", method = RequestMethod.GET)
	public ModelAndView getPdfForInspReport(ModelMap Mmap, HttpSession session, HttpServletRequest request) {
		return new ModelAndView("insp_Formate_ReportsTiles");
	}

	@FunctionalInterface
	private interface SaveActionHandler {
		boolean execute() throws Exception;
	}

	public String formatDateToDDMMYYYY(String dateString) {
		if (dateString == null || dateString.isEmpty()) {
			return "";
		}
		String[] parts = dateString.split("-");
		if (parts.length != 3) {
			return dateString;
		}
		return parts[2] + "-" + parts[1] + "-" + parts[0];
	}

	public boolean updateMainTblData(String buttonContext, String sus_no, String roleType) {

		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1
					.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_MAIN_TBL WHERE sus_no = :sus_no and year = :year");
			q.setParameter("sus_no", sus_no);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update TB_MISO_INSP_MAIN_TBL set " + buttonContext
						+ " =:status WHERE sus_no = :sus_no and year = :year"); // Update to 0
				q1.setParameter("sus_no", sus_no);
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				int rowsAffected = q1.executeUpdate();

				success = rowsAffected > 0;

			} else {
				// Insert new record
				TB_MISO_INSP_MAIN_TBL maintbl = new TB_MISO_INSP_MAIN_TBL();
				maintbl.setSus_no(sus_no);
				maintbl.setYear(currentYear);
				maintbl.setStatus(-1); // Initial value -1
				maintbl.setEquipment_item(-1);
				maintbl.setAnimals_item(-1);
				maintbl.setDeficiencies_of_equipment2_item(-1);
				maintbl.setDeficiencies_of_equipment_item(-1);
				maintbl.setState_of_sector_stores_item(-1);
				maintbl.setWt_results_item(-1);
				maintbl.setEducation_standards_item(-1);
				maintbl.setCategory_item(-1);
				maintbl.setUp_gradation_item(-1);
				maintbl.setRegi_language_exam_item(-1);
				maintbl.setBpet_result_item(-1);
				maintbl.setPpt_result_item(-1);
				maintbl.setPromotion_exam_officers_item(-1);
				maintbl.setFinancial_grants_item(-1);
				maintbl.setTraining_ammunition_item(-1);
				maintbl.setAvailability_of_ranges_item(-1);
				maintbl.setOutstanding_audit_objections_observations_item(-1);
				maintbl.setCourses_item(-1);
				maintbl.setSummarybtn(-1);
				maintbl.setOutstanding_item(-1);
				maintbl.setMt_accidents_item(-1);
				maintbl.setDetails_of_suicides_item(-1);
				maintbl.setSecurity_lapses_item(-1);
				maintbl.setDetails_of_attachments_item(-1);
				maintbl.setDetails_of_officers_item(-1);
				maintbl.setSocial_media_violation_item(-1);
				maintbl.setWeb_messenger_apps_item(-1);
				maintbl.setEspionage_item(-1);
				maintbl.setCompromise_of_cell_phone_item(-1);
				maintbl.setUntraceable_item(-1);
				maintbl.setLoss_of_cd_item(-1);
				maintbl.setLoss_of_identity_item(-1);
				maintbl.setLand_item(-1);
				maintbl.setSummary_of_report_last_five_year_item(-1);
				maintbl.setRecruit_training_b_appendix_item(-1);
				maintbl.setDeffi_exp_resp_to_trainng_item(-1);
				maintbl.setEmp_of_unit_during_period_item(-1);
				maintbl.setRegt_funds_item(-1);
				maintbl.setCreated_date(new Date());
				try {
					Field field = TB_MISO_INSP_MAIN_TBL.class.getDeclaredField(buttonContext);
					field.setAccessible(true);
					field.set(maintbl, 0);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					System.err.println("Error setting field " + buttonContext + ": " + e.getMessage());

				}

				ses1.save(maintbl);
				success = true;
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	// PDF

	@RequestMapping(value = "/EstablishmentPdfUrl", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> EstablishmentPdfUrl(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getEstablishmentPdf(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/EquipmentUrl", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> EquipmentUrl(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getEquipmentPdf(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/AnnualMeterage_url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> AnnualMeterage_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getAnnualMeterage(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/AnimalsPdfUrl", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> AnimalsPdfUrl(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getAnimalPdf(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/EqptPdfUrl", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> EqptPdfUrl(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getEqptPdf(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/EqptPdfUrl2", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> EqptPdfUrl2(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getEqptPdf2(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/SectorStoresPdfUrl", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> SectorStoresPdfUrl(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getSectorStoresPdf(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/WtPdfUrl", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> WtPdfUrl(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getWtPdf(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/CategoryPdfUrl", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> CategoryPdfUrl(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getCategoryPdf(session, request);
		result.add(a);
		return result;
	}

	@RequestMapping(value = "/UpgradationPdfUrl", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> UpgradationPdfUrl(String unit_no, HttpSession session,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		result = dao.getUpgradationPdf(session, request);
		result.add(a);
		return result;
	}

	public boolean addUserRemarks(String buttonContext, String remakrs, String sus_no, String username) {
		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			TB_MISO_INSP_USER_REMARKS userremark = new TB_MISO_INSP_USER_REMARKS();
			userremark.setSus_no(sus_no);
			userremark.setYear(currentYear);
			userremark.setUser_remarks(remakrs);
			userremark.setReport_name(buttonContext);
			userremark.setCreated_by(username);
			;
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

	public boolean landSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_LAND WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery(
						"delete FROM TB_MISO_INSP_LAND WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}

			TB_MISO_INSP_LAND establishment = new TB_MISO_INSP_LAND();
			establishment.setSus_no(roleSusNo);
			establishment.setDefenceLandParticulars(request.getParameter("defenceLandParticulars"));
			establishment.setLandRecordRegisterMaintained(request.getParameter("landRecordRegisterMaintained"));
			establishment.setLandDemarcated(request.getParameter("landDemarcated"));
			establishment.setLandUtilized(request.getParameter("landUtilized"));
			establishment.setVacantLandDetails(request.getParameter("vacantLandDetails"));
			establishment.setSafetyMeasuresAdequate(request.getParameter("safetyMeasuresAdequate"));
			establishment.setEvictionActionDetails(request.getParameter("evictionActionDetails"));
			establishment.setRemarksSuggestions(request.getParameter("remarksSuggestions"));
			establishment.setYear(currentYear);
			establishment.setStatus(status);
			establishment.setCreated_by(username);
			establishment.setCreated_date(new Date());

			ses1.save(establishment);
			success = true;

			String remarks = request.getParameter("user_remarks13");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("land_item", remarks, roleSusNo, username);
			}

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

	private boolean outstandingauditSaveAction(HttpSession session, HttpServletRequest request) {
		boolean save = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();

		try {

			String hql = "FROM TB_INSP_OUTSTANDING_AUDIT " + "WHERE sus_no = :susNo "
					+ "AND year = :year AND status =:status";

			Query query = ses1.createQuery(hql);
			query.setParameter("susNo", roleSusNo);
			query.setParameter("year", currentYear);
			query.setParameter("status", 0);

			List<?> results = query.list();

			TB_INSP_OUTSTANDING_AUDIT wtResultEntity;

			if (!results.isEmpty()) {
				wtResultEntity = (TB_INSP_OUTSTANDING_AUDIT) results.get(0);
			} else {
				wtResultEntity = new TB_INSP_OUTSTANDING_AUDIT();
				wtResultEntity.setSus_no(roleSusNo);
				wtResultEntity.setYear(currentYear);
				wtResultEntity.setStatus(status);
				wtResultEntity.setCreated_date(new Date());
				wtResultEntity.setCreated_by(username);
			}
			wtResultEntity.setBroughtPreviousObj(request.getParameter("broughtPreviousObj"));
			wtResultEntity.setBroughtPreviousObserv(request.getParameter("broughtPreviousObserv"));
			wtResultEntity.setBroughtPreviousRemark(request.getParameter("broughtPreviousRemark"));
			wtResultEntity.setRaisedReportObj(request.getParameter("raisedReportObj"));
			wtResultEntity.setRaisedReportObserv(request.getParameter("raisedReportObserv"));
			wtResultEntity.setRaisedReportRemark(request.getParameter("raisedReportRemark"));
			wtResultEntity.setSettledDuringObj(request.getParameter("settledDuringObj"));
			wtResultEntity.setSettledDuringObserv(request.getParameter("settledDuringObserv"));
			wtResultEntity.setSettledDuringRemark(request.getParameter("settledDuringRemark"));
			wtResultEntity.setRemainingObj(request.getParameter("remainingObj"));
			wtResultEntity.setRemainingObserv(request.getParameter("remainingObserv"));
			wtResultEntity.setRemainingRemark(request.getParameter("remainingRemark"));
			wtResultEntity.setDifficultiesObj3(request.getParameter("difficultiesObj3"));
			wtResultEntity.setDifficultiesObj1(request.getParameter("difficultiesObj1"));
			wtResultEntity.setDifficultiesObserv3(request.getParameter("difficultiesObserv3"));
			wtResultEntity.setDifficultiesObserv1(request.getParameter("difficultiesObserv1"));
			wtResultEntity.setDifficultiesRemark3(request.getParameter("difficultiesRemark3"));
			wtResultEntity.setDifficultiesRemark1(request.getParameter("difficultiesRemark1"));

			ses1.saveOrUpdate(wtResultEntity);
			dao.wtResultOffrsJcoOrSaveAction(session, request);

			t2.commit();
			save = true;

			String remarks = request.getParameter("user_remarks14");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("outstanding_audit_objections_observations_item", remarks, roleSusNo, username);
			}

		} catch (Exception e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Error saving/updating Summary Tech data: " + e.getMessage());
			e.printStackTrace();
			save = false;
		} finally {
			if (ses1 != null && ses1.isOpen()) {
				ses1.close();
			}
		}
		return save;
	}

	public boolean course_categorySaveAction(HttpSession session, HttpServletRequest request) {
		String roleType = session.getAttribute("roleType").toString();
		System.err.println("Role--->" + roleType);
		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username").toString();

		Integer status_rolewise = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		try {

			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery(
					"update TB_MISO_INSP_COURSES_CAT_A_B set status =:status_rolewise  WHERE sus_no = :sus_no and year =:year and status=:status"); // Update
																																					// to
																																					// 1
																																					// not
																																					// buttonContext
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setInteger("status", 0);
			q1.setInteger("status_rolewise", status_rolewise);
			int rowsAffected = q1.executeUpdate();
			if (rowsAffected > 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks16");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("category_item", remarks, roleSusNo, username);
			}

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

	public boolean up_gradation_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = session.getAttribute("roleType").toString();
		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username").toString();
		/*
		 * String status_rolewise = roleType.equals("APP") ? 1 : (roleType.equals("DEO")
		 * ? 0 : -1);
		 */
		try {

			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery(
					"update TB_MISO_INSP_UPGRADATION set status =:status_rolewise  WHERE sus_no = :sus_no and year =:year and status=:status"); // Update
																																				// to
																																				// 1
																																				// not
																																				// buttonContext
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", "0");
			if (roleType.equals("AAP")) {
				q1.setParameter("status_rolewise", "1");
			} else {
				q1.setParameter("status_rolewise", "0");
			}
			int rowsAffected = q1.executeUpdate();
			if (rowsAffected > 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks17");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("up_gradation_item", remarks, roleSusNo, username);
			}

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

	public boolean educationStandardsItemInspSaveAction(HttpSession session, HttpServletRequest request) {
		String roleType = session.getAttribute("roleType").toString();
		if (roleType.equals("AAP")) {
			Session ses1 = null;
			Transaction t2 = null;
			boolean success = false;
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String username = (String) session.getAttribute("username").toString();
			Integer status_rolewise = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
			try {

				ses1 = HibernateUtil.getSessionFactory().openSession();
				t2 = ses1.beginTransaction();

				Query q1 = ses1.createQuery(
						"update TB_INSP_EDUCATION_STANDARDS set status =:status_rolewise  WHERE sus_no = :sus_no and year =:year and status=:status"); // Update
																																						// to
																																						// 1
																																						// not
																																						// buttonContext
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("year", currentYear);
				q1.setInteger("status", 0);
				q1.setInteger("status_rolewise", status_rolewise);
				int rowsAffected = q1.executeUpdate();
				if (rowsAffected > 0) {
					success = true;
				} else {
					success = false;
				}

				/*
				 * String remarks = request.getParameter("user_remarks17"); if
				 * (!remarks.equals("") && !remarks.isEmpty()) {
				 * addUserRemarks("education_item", remarks,roleSusNo, username); }
				 */

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
		} else {
			return true;
		}
	}

	public boolean financial_grants_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = session.getAttribute("roleType").toString();

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username").toString();
		Integer status_rolewise = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		try {

			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery(
					"update TB_INSP_FINANCIAL_GRANTS set status =:status_rolewise  WHERE sus_no = :sus_no and year =:year and status=:status"); // Update
																																				// to
																																				// 1
																																				// not
																																				// buttonContext
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setInteger("status", 0);
			q1.setInteger("status_rolewise", status_rolewise);
			int rowsAffected = q1.executeUpdate();
			if (rowsAffected > 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks18");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("financial_grants_item", remarks, roleSusNo, username);
			}

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

	public boolean training_ammunition_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = session.getAttribute("roleType").toString();

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username").toString();
		Integer status_rolewise = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		try {

			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery(
					"update TB_MISO_INSP_TRAINING_AMMUNITION set status =:status_rolewise  WHERE sus_no = :sus_no and year =:year and status=:status"); // Update
																																						// to
																																						// 1
																																						// not
																																						// buttonContext
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setInteger("status", 0);
			q1.setInteger("status_rolewise", status_rolewise);
			int rowsAffected = q1.executeUpdate();
			if (rowsAffected > 0) {
				success = true;
			} else {
				success = false;
			}
			String remarks = request.getParameter("user_remarks21");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("training_ammunition_item", remarks, roleSusNo, username);
			}

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

	public boolean courses_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");

		String username = (String) session.getAttribute("username").toString();

		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_STANDARDS_ACHIEVED set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			Query q2 = ses1.createQuery("update TB_DETAIL_COURSES set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q2.setParameter("sus_no", roleSusNo);
			q2.setParameter("year", currentYear);
			q2.setParameter("status", 0);
			q2.setParameter("status_rolewise", status_rolewise);

			int rowsAffected2 = q2.executeUpdate();

			if (rowsAffected1 >= 0 && rowsAffected2 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks22");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("courses_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean outstanding_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_OUTSTANDING_RENT_ALLIED set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks23");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("outstanding_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean mt_accidents_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");

		String username = session.getAttribute("username").toString();

		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_OUTSTANDING_LOSS_MT set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks24");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("mt_accidents_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean details_of_suicides_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_FATAL_INCIDENTS set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks25");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("details_of_suicides_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean security_lapses_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_SECURITY_LAPSES set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks26");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("security_lapses_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean details_of_attachments_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_OUTSIDE_ATTACHMENTS set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks27");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("details_of_attachments_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean details_of_officers_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_TD_PROCEEDED set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks28");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("details_of_officers_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean social_media_violation_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_SOCIAL_MEDIA_LAPSES set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();
			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks29");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("social_media_violation_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean web_messenger_apps_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_PIO_CALL_LAPSES set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks30");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("web_messenger_apps_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean espionage_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_ESPIONAGE_LAPSES set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks31");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("espionage_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean compromise_of_cell_phone_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_CELL_PHONE_LAPSES set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks32");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("compromise_of_cell_phone_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean untraceable_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_FS_SECURITY_LAPSES set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks33");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("untraceable_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean loss_of_cd_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_LOST_CDS_DVDS set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks34");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("loss_of_cd_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean loss_of_identity_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_INSP_LOST_ID_ECR set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks35");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("loss_of_identity_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean summary_of_report_last_five_year_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_PSG_ANNUAL_REPORT set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks36");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("summary_of_report_last_five_year_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean recruit_training_b_appendix_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update TB_MISO_INSP_DTL_OR_REQ_TRAINING set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks37");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("recruit_training_b_appendix_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean deffi_exp_resp_to_trainng_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery("update \r\n" + "TB_MISO_INSP_DTL_DEFFI_EXP_RESP_TO_TRAINNING\r\n"
					+ " set status = :status_rolewise "
					+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks38");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("deffi_exp_resp_to_trainng_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean emp_of_unit_during_period_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery(
					"update \r\n" + "TB_MISO_INSP_EMP_OF_UNIT_DURING_PERIOD\r\n" + " set status = :status_rolewise "
							+ "WHERE sus_no = :sus_no and year = :year and status = :status");
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setParameter("status", 0);
			q1.setParameter("status_rolewise", status_rolewise);

			int rowsAffected1 = q1.executeUpdate();

			if (rowsAffected1 >= 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks39");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("emp_of_unit_during_period_item", remarks, roleSusNo, username);
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
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

	public boolean regt_funds_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = session.getAttribute("roleType").toString();

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username").toString();

		Integer status_rolewise = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		try {

			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery(
					"update TB_INSP_REGT_FUNDS set status =:status_rolewise  WHERE sus_no = :sus_no and year =:year and status=:status"); // Update
																																			// to
																																			// 1
																																			// not
																																			// buttonContext
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setInteger("status", 0);
			q1.setInteger("status_rolewise", status_rolewise);
			int rowsAffected = q1.executeUpdate();
			if (rowsAffected > 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks19");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("regt_funds_item", remarks, roleSusNo, username);
			}

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

	public boolean availability_of_ranges_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = session.getAttribute("roleType").toString();

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = (String) session.getAttribute("username").toString();
		Integer status_rolewise = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);
		try {

			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q1 = ses1.createQuery(
					"update TB_INSP_RPT_CLASSIFICATION_RANGES set status =:status_rolewise  WHERE sus_no = :sus_no and year =:year and status=:status"); // Update
																																							// to
																																							// 1
																																							// not
																																							// buttonContext
			q1.setParameter("sus_no", roleSusNo);
			q1.setParameter("year", currentYear);
			q1.setInteger("status", 0);
			q1.setInteger("status_rolewise", status_rolewise);
			int rowsAffected1 = q1.executeUpdate();
			if (rowsAffected1 > 0) {
				success = true;
			} else {
				success = false;
			}

			Query q2 = ses1.createQuery(
					"update TB_INSP_FFRS set status =:status_rolewise  WHERE sus_no = :sus_no and year =:year and status=:status"); // Update
																																	// to
																																	// 1
																																	// not
																																	// buttonContext
			q2.setParameter("sus_no", roleSusNo);
			q2.setParameter("year", currentYear);
			q2.setInteger("status", 0);
			q2.setInteger("status_rolewise", status_rolewise);
			int rowsAffected2 = q2.executeUpdate();
			if (rowsAffected2 > 0) {
				success = true;
			} else {
				success = false;
			}

			String remarks = request.getParameter("user_remarks20");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("availability_of_ranges_item", remarks, roleSusNo, username);
			}

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

	@RequestMapping(value = "/land_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> land_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getLandData(session, request);
		return result;
	}

	// for downloading Uploaded document in insp rpt

	@RequestMapping(value = "/getpathdoc_anl_rpt_extracts", method = RequestMethod.POST)
	public @ResponseBody List<String> getpathdoc_anl_rpt_extracts(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct a.doc_path from TB_PSG_ANNUAL_REPORT a where "
				+ "  a.sus_no=:sus_no and a.year=:year ) ");
		q.setParameter("sus_no", sus_no);
		q.setParameter("year", currentYear);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();

		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getpathdoc_anl_rpt_recruit_trg", method = RequestMethod.POST)
	public @ResponseBody List<String> getpathdoc_anl_rpt_recruit_trg(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct a.doc_path from TB_MISO_INSP_DTL_OR_REQ_TRAINING a where "
				+ "  a.sus_no=:sus_no and a.year=:year ) ");
		q.setParameter("sus_no", sus_no);
		q.setParameter("year", currentYear);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();

		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getpathdoc_anl_rpt_difficulties_trg", method = RequestMethod.POST)
	public @ResponseBody List<String> getpathdoc_anl_rpt_difficulties_trg(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct a.doc_path from TB_MISO_INSP_DTL_DEFFI_EXP_RESP_TO_TRAINNING a where "
						+ "  a.sus_no=:sus_no and a.year=:year ) ");
		q.setParameter("sus_no", sus_no);
		q.setParameter("year", currentYear);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();

		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getpathdoc_anl_rpt_emp_of_unit_during_period", method = RequestMethod.POST)
	public @ResponseBody List<String> getpathdoc_anl_rpt_emp_of_unit_during_period(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct a.doc_path from TB_MISO_INSP_EMP_OF_UNIT_DURING_PERIOD a where "
				+ "  a.sus_no=:sus_no and a.year=:year ) ");
		q.setParameter("sus_no", sus_no);
		q.setParameter("year", currentYear);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();

		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/DownloadPdfInspExtracts", method = RequestMethod.POST)
	public void downloadByPath(@RequestParam("filePath") String filePath, HttpServletResponse response)
			throws IOException, DocumentException {
		System.err.println("inside DownloadPdfInspExtracts --> " + filePath);
		File file = new File(filePath);

		if (!file.exists() || file.isDirectory()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String mimeType = Files.probeContentType(file.toPath());
		File fileToDownload = file;

		/*
		 * if (mimeType != null && mimeType.equals("application/pdf")) { //
		 * fileToDownload = addWatermarkToPdf(file); }
		 */

		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileToDownload.getName() + "\"");
		response.setContentLengthLong(fileToDownload.length());

		try (InputStream in = new FileInputStream(fileToDownload); OutputStream out = response.getOutputStream()) {

			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}

		if (!fileToDownload.equals(file)) {
			fileToDownload.delete();
		}
	}

	private File addWatermarkToPdf(File originalFile, HttpSession session) throws IOException, DocumentException {
		File tempFile = File.createTempFile("watermarked_", ".pdf");
		PdfReader reader = new PdfReader(new FileInputStream(originalFile));
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(tempFile));

		String ip = session.getAttribute("ip").toString();
		int total = reader.getNumberOfPages();
		BaseFont font = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);

		for (int i = 1; i <= total; i++) {
			PdfContentByte over = stamper.getOverContent(i);
			over.beginText();
			over.setFontAndSize(font, 40);
			over.setColorFill(BaseColor.LIGHT_GRAY);
			over.showTextAligned(Element.ALIGN_CENTER, ip, 300, 400, 45);
			over.endText();
		}

		stamper.close();
		reader.close();

		return tempFile;
	}
	
	public <T> List<T> getdatamodelwise(String sus_no, String modelName) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    
	   
	    String hql = "from " + modelName + " a where a.sus_no = :sus_no and a.year = :year";
	    
	    Query q = session.createQuery(hql);
	    q.setParameter("sus_no", sus_no);
	    q.setParameter("year", currentYear);
	    
	    List<T> list = q.list();
	    tx.commit();
	    session.close();
	    return list;
	}
	
	 @RequestMapping(value = "/DownloadSummaryinsppdf", method = RequestMethod.POST)
	    public ModelAndView DownloadSummaryinsppdf(ModelMap Mmap, HttpSession session,@RequestParam(value = "pdftype", required = false) String pdftype,
	                   HttpServletResponse response) {
	           
	           try {
	                       String susNo   = (String) session.getAttribute("roleSusNo");
	                       String tblName = null;
	               String docPath = null;
	               Session sessionhql = null;
	               
	               if(pdftype.equals("summary_of_report_last_five_year")) {
	                   tblName="TB_PSG_ANNUAL_REPORT";
	               }else if(pdftype.equals("recruit_training_b_appendix")) {
	                   tblName="TB_MISO_INSP_DTL_OR_REQ_TRAINING";
	               }else if(pdftype.equals("deffi_exp_resp_to_trainng")) {
	                   tblName="TB_MISO_INSP_DTL_DEFFI_EXP_RESP_TO_TRAINNING";
	               }else if(pdftype.equals("emp_of_unit_during_period")) {
	                   tblName="TB_MISO_INSP_EMP_OF_UNIT_DURING_PERIOD";
	               }
	               
	               try {
	                      sessionhql = HibernateUtil.getSessionFactory().openSession();
	                      Transaction tx = sessionhql.beginTransaction();

	                      Query q0 = sessionhql.createQuery("select doc_path from "+tblName+" where sus_no = :sus_no and year = :year")
	                                                              .setParameter("sus_no", susNo)
	                                                              .setParameter("year", currentYear);
	                      
	                      Object result = q0.uniqueResult();
	                      if (result != null) {
	                              docPath = (String) result;
	                      }
	                      
	                      tx.commit();
	               } catch (Exception e) {                      
	                      e.printStackTrace();
	               } finally {
	                      if (sessionhql != null && sessionhql.isOpen()) {
	                              sessionhql.close();
	                      }
	               }
	                   
	             
	                   
	                   if (docPath != null) {                         
	                           File file = new File(docPath);
	                           
	                           if (file.exists()) {                                 
	                                   response.setContentType("application/pdf");
	                                   response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
	                                   response.setContentLength((int) file.length()); 
	                                     FileInputStream inputStream = new FileInputStream(file);
	                                   OutputStream outStream = response.getOutputStream();
	                                   byte[] buffer = new byte[4096];
	                                   int bytesRead = -1;                                   
	                                   while ((bytesRead = inputStream.read(buffer)) != -1) {
	                                           outStream.write(buffer, 0, bytesRead);
	                                   }                                   
	                                   inputStream.close();
	                                   outStream.flush();
	                                   outStream.close();                           
	                                   return null;
	                           }
	                   }   
	                 
	                   Mmap.addAttribute("errorMessage", "PDF file not found");
	                   return new ModelAndView("errorPage", Mmap);
	                   
	           } catch (Exception e) {
	                   e.printStackTrace();
	                   Mmap.addAttribute("errorMessage", "Error downloading PDF: " + e.getMessage());
	                   return new ModelAndView("errorPage", Mmap);
	           }
	    }


}
