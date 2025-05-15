package com.controller.psg.Census;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.math.BigInteger;

import java.sql.Timestamp;

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

import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;

import org.hibernate.Query;

import org.hibernate.Session;

import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;

import com.dao.psg.Master.Psg_CommanDAO;

import com.dao.psg.Transaction.CensusAprovedDAO;

import com.dao.psg.Transaction.Census_QualificationDAO;

import java.io.OutputStream;

import com.models.psg.Transaction.TB_CENSUS_ADDRESS;

import com.models.psg.Transaction.TB_CENSUS_CADET;

import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;

import com.models.psg.Transaction.TB_CENSUS_FAMILY_DIVORCE;

import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;

import com.models.psg.Transaction.TB_CENSUS_FAMILY_SIBLINGS;

import com.models.psg.Transaction.TB_CENSUS_FOREIGN_COUNTRY;

import com.models.psg.Transaction.TB_CENSUS_IDENTITY_CARD;

import com.models.psg.Transaction.TB_CENSUS_LANGUAGE;

import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;

import com.models.psg.Transaction.TB_CENSUS_NCC_EXP;

import com.models.psg.Transaction.TB_CENSUS_NOK;

import com.models.psg.Transaction.TB_CENSUS_QUALIFICATION;

import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;

import com.models.psg.Transaction.TB_PSG_CENSUS_ALLERGICTOMEDICINE;

import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;

import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Census_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();

	@Autowired

	CensusAprovedDAO censusAprovedDAO;

	@Autowired

	Psg_CommanDAO psg_com;

	@Autowired

	Census_QualificationDAO census_QualificationDAO;

	@Autowired

	private RoleBaseMenuDAO roledao;
	ValidationController valid = new ValidationController();

	@RequestMapping(value = "/admin/form", method = RequestMethod.GET)
	public ModelAndView Institute(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, 
			@RequestParam(value = "personnel_no", required = false) String personnel_no,
			@RequestParam(value = "mlabel", required = false) String mlabel,HttpServletRequest request, HttpServletResponse response) {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("form", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getReligionList", mcommon.getReligionList());
		Mmap.put("getMarital_statusList", mcommon.getMarital_statusList());
		Mmap.put("getBloodList", mcommon.getBloodList());
		Mmap.put("getRelationList", mcommon.getRelationList());
		Mmap.put("getPreCadetStatusList", mcommon.getPreCadetStatusList());
		Mmap.put("getNCCTypeList", mcommon.getNCCTypeList());
		Mmap.put("getLanguageSTDList", mcommon.getLanguageSTDList());
		Mmap.put("getLanguagePROOFList", mcommon.getLanguagePROOFList());

		Mmap.put("getForiegnLangugeList", mcommon.getForiegnLangugeList());

		Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));

		Mmap.put("getMedStateName", mcommon.getMedStateName("", session));

		Mmap.put("getMedCountryName", mcommon.getMedCountryName("", session));

		Mmap.put("getNationalityList", mcommon.getNationalityList());

		Mmap.put("getMedCatList", mcommon.getMedCatList());

		Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());

		Mmap.put("getLanguageList", mcommon.getLanguageList());

		Mmap.put("getMothertoungeList", mcommon.getMothertoungeList());

		Mmap.put("getSpecializationList", mcommon.getSpecializationList());

		Mmap.put("getHair_ColourList", mcommon.getHair_ColourList());

		Mmap.put("getEye_ColourList", mcommon.getEye_ColourList());

		Mmap.put("getDivclass", mcommon.getDivclass());

		Mmap.put("getSubject", mcommon.getSubject());
		
		Mmap.put("getCommInstitute", mcommon.getInstitute("1"));

		Mmap.put("getTrainingInstitute", mcommon.getInstitute("2"));

		Mmap.put("getHeight", mcommon.getHeight());

		Mmap.put("getBattalionList", mcommon.getBattalionList());

		Mmap.put("getCompanyList", mcommon.getCompanyList());

		Mmap.put("getFamily_siblings", mcommon.getFamily_siblings());

		Mmap.put("getForeign_country", mcommon.getForeign_country());

		Mmap.put("getForiegnCountryList", psg_com.getforiegnCountry());

		Mmap.put("getIndianLanguageList", mcommon.getIndianLanguageList());

		Mmap.put("getForeignLanguageList", mcommon.getForeignLanguageList());

		Mmap.put("getMedCityName", mcommon.getMedCityName("", session));

		Mmap.put("getProfession", mcommon.getProfession());

		Mmap.put("getShapeStatusList", mcommon.getShapeStatusList());

		Mmap.put("getcCopeList", mcommon.getCopeValueList("C"));

		Mmap.put("getoCopeList", mcommon.getCopeValueList("O"));

		Mmap.put("getpCopeList", mcommon.getCopeValueList("P"));

		Mmap.put("geteCopeList", mcommon.getCopeValueList("E"));

		Mmap.put("getesubCopeList", mcommon.getCopeValueList("E Sub Value"));

		Mmap.put("getExservicemenList", mcommon.getExservicemenList());

		Mmap.put("msg", msg);

		LocalDate date_without_time = LocalDate.now();

		Mmap.put("date_without_time", date_without_time);
		if(personnel_no!="") {
			Mmap.put("personnel_no", personnel_no);
		}
		
		if(mlabel!="") {
			Mmap.put("mlabel", mlabel);
		}

		return new ModelAndView("formTiles");

	}
	
	@RequestMapping(value = "/admin/censusFormMns", method = RequestMethod.GET)
	public ModelAndView censusFormMns(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, 
			@RequestParam(value = "personnel_no", required = false) String personnel_no,
			@RequestParam(value = "mlabel", required = false) String mlabel,HttpServletRequest request, HttpServletResponse response) {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("censusFormMns", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getReligionList", mcommon.getReligionList());
		Mmap.put("getMarital_statusList", mcommon.getMarital_statusList());
		Mmap.put("getBloodList", mcommon.getBloodList());
		Mmap.put("getRelationList", mcommon.getRelationList());
		Mmap.put("getPreCadetStatusList", mcommon.getPreCadetStatusList());
		Mmap.put("getNCCTypeList", mcommon.getNCCTypeList());
		Mmap.put("getLanguageSTDList", mcommon.getLanguageSTDList());
		Mmap.put("getLanguagePROOFList", mcommon.getLanguagePROOFList());
		Mmap.put("getForiegnLangugeList", mcommon.getForiegnLangugeList());
		Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));
		Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
		Mmap.put("getMedCountryName", mcommon.getMedCountryName("", session));
		Mmap.put("getNationalityList", mcommon.getNationalityList());
		Mmap.put("getMedCatList", mcommon.getMedCatList());
		Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());
		Mmap.put("getLanguageList", mcommon.getLanguageList());
		Mmap.put("getMothertoungeList", mcommon.getMothertoungeList());
		Mmap.put("getSpecializationList", mcommon.getSpecializationList());
		Mmap.put("getHair_ColourList", mcommon.getHair_ColourList());
		Mmap.put("getEye_ColourList", mcommon.getEye_ColourList());
		Mmap.put("getDivclass", mcommon.getDivclass());
		Mmap.put("getSubject", mcommon.getSubject());		
		Mmap.put("getCommInstitute", mcommon.getInstitute("1"));
		Mmap.put("getTrainingInstitute", mcommon.getInstitute("2"));
		Mmap.put("getHeight", mcommon.getHeight());
		Mmap.put("getBattalionList", mcommon.getBattalionList());
		Mmap.put("getCompanyList", mcommon.getCompanyList());
		Mmap.put("getFamily_siblings", mcommon.getFamily_siblings());
		Mmap.put("getForeign_country", mcommon.getForeign_country());
		Mmap.put("getForiegnCountryList", psg_com.getforiegnCountry());
		Mmap.put("getIndianLanguageList", mcommon.getIndianLanguageList());
		Mmap.put("getForeignLanguageList", mcommon.getForeignLanguageList());
		Mmap.put("getMedCityName", mcommon.getMedCityName("", session));
		Mmap.put("getProfession", mcommon.getProfession());
		Mmap.put("getShapeStatusList", mcommon.getShapeStatusList());
		Mmap.put("getcCopeList", mcommon.getCopeValueList("C"));
		Mmap.put("getoCopeList", mcommon.getCopeValueList("O"));
		Mmap.put("getpCopeList", mcommon.getCopeValueList("P"));
		Mmap.put("geteCopeList", mcommon.getCopeValueList("E"));
		Mmap.put("getesubCopeList", mcommon.getCopeValueList("E Sub Value"));
		Mmap.put("getExservicemenList", mcommon.getExservicemenList());
		Mmap.put("msg", msg);
		LocalDate date_without_time = LocalDate.now();
		Mmap.put("date_without_time", date_without_time);
		if(personnel_no!="") {
			Mmap.put("personnel_no", personnel_no);
		}		
		if(mlabel!="") {
			Mmap.put("mlabel", mlabel);
		}
		return new ModelAndView("formCensusMnsTiles");
	}

	@RequestMapping(value = "/admin/pre_cadet_action", method = RequestMethod.POST)

	public @ResponseBody Map<String, String> pre_cadet_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		Map<String, String> data = new HashMap<>();

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		Date fr_dt = null;

		Date fr_to = null;

		String username = session.getAttribute("username").toString();

		String cadet_status = request.getParameter("cadet_status1");

		String isgazetted = null;

		String isCivil = null;

		String designation = null;

		String emp_name = null;

		String army_no = null;

		String date_form = null;

		String date_to = null;

		String total_rank = null;

		String unit_reg = null;

		String competency = "0";

		String competency_other = null;

		if (cadet_status.equals("2") || cadet_status.equals("3") || cadet_status.equals("4")) {

			if (!cadet_status.equals("2")) {

				isgazetted = request.getParameter("isgazetted1");

				isCivil = request.getParameter("isCivil1");

			} else {

				competency = request.getParameter("competency");

				if (!request.getParameter("competency_other").toString().trim().equals("")) {

					competency_other = request.getParameter("competency_other").toString();

				}

			}

			designation = request.getParameter("designation1");

			emp_name = request.getParameter("emp_name1");

		}

		if (cadet_status.equals("5") || cadet_status.equals("6") || cadet_status.equals("7") || cadet_status.equals("8")

				|| cadet_status.equals("9")) {

			army_no = request.getParameter("army_no1");

			date_form = request.getParameter("date_form1");

			date_to = request.getParameter("date_to1");

			if (format.parse(date_to).compareTo(format.parse(date_form)) < 0) {

				data.put("error", "To Date  Should Be Greater Than Or Equal To From Date");

				return data;

			}

			total_rank = request.getParameter("total_rank1");

			unit_reg = request.getParameter("sus_no");

		}

		String pre_ch_id = request.getParameter("pre_ch_id1");

		String p_id = request.getParameter("p_id");

		String rvalue = "";

		////////////////////////////// nccc

		String otu = request.getParameter("otu1");

		String ncc_ch_id = request.getParameter("ncc_ch_id1");

		
		BigInteger com_id = BigInteger.ZERO;
		
		com_id = new BigInteger(request.getParameter("com_id"));

		if (ncc_ch_id != null) {

			if (otu == null || otu.trim().equals("")) {

				data.put("error", "Please select Whether in OTU Div/Jr Div/Sr Div");

				return data;

			}

		}

		if (cadet_status == null || cadet_status.equals("0")) {

			data.put("error", "Please Select Pre-Cadet Status");

			return data;

		}

		if (cadet_status.equals("2") || cadet_status.equals("3") || cadet_status.equals("4")) {

			if (cadet_status.equals("2")) {

				if (competency == null || competency.equals("0")) {

					data.put("error", "Please Select Competency");

					return data;

				}
				if (request.getParameter("competency_other") != null
						&& !request.getParameter("competency_other").trim().equals("")) {

					if (!valid.isOnlyAlphabet(request.getParameter("competency_other"))) {
						data.put("error", " Competency Other " + valid.isOnlyAlphabetMSG);
						return data;
					}

					if (!valid.isvalidLength(request.getParameter("competency_other"), valid.nameMax, valid.nameMin)) {
						data.put("error", "Competency Other " + valid.isValidLengthMSG);
						return data;
					}
				}
			}

			if (designation == null || designation.trim().equals("")) {

				data.put("error", "Please Enter Designation");

				return data;

			}
			if (designation != null && !designation.trim().equals("")) {

				if (!valid.isOnlyAlphabet(designation)) {
					data.put("error", " Designation " + valid.isOnlyAlphabetMSG);
					return data;
				}

				if (!valid.isvalidLength(designation, valid.nameMax, valid.nameMin)) {
					data.put("error", "Designation " + valid.isValidLengthMSG);
					return data;
				}
			}
			if (emp_name == null || emp_name.trim().equals("")) {

				data.put("error", "Please Enter Name of Employer");

				return data;

			}
			if (emp_name != null && !emp_name.trim().equals("")) {

				if (!valid.isOnlyAlphabet(emp_name)) {
					data.put("error", " Name of Employer " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(emp_name, valid.authorityMax, valid.authorityMin)) {
					data.put("error", "Name of Employer " + valid.isValidLengthMSG);
					return data;
				}
			}
		}
		if (cadet_status.equals("5") || cadet_status.equals("6") || cadet_status.equals("7") || cadet_status.equals("8")

				|| cadet_status.equals("9")) {

			if (army_no == null || army_no.trim().equals("")) {

				data.put("error", "Please Enter Service No");

				return data;

			}
			if (army_no != null && !army_no.trim().equals("")) {

				if (!valid.isOnlyAlphabetNumericSpaceNot(army_no)) {
					data.put("error", " Service No " + valid.isOnlyAlphabetNumericSpaceNotMSG);
					return data;
				}

				if (!valid.isvalidLength(army_no, valid.nameMax, valid.nameMin)) {
					data.put("error", "Service No " + valid.isValidLengthMSG);
					return data;
				}
			}
			if (date_form == null || date_form.trim().equals("")) {

				data.put("error", "Please Select Form Date");

				return data;

			}
			if (date_form != null || date_form.trim().equals("") || date_form.equals("DD/MM/YYYY")) {

				if (!valid.isValidDate(date_form)) {
					data.put("error", valid.isValidDateMSG + " of From ");

					return data;
				} else {
					fr_dt = format.parse(date_form);
				}

			}

			if (date_to == null || date_to.trim().equals("")) {

				data.put("error", "Please Select To Date");

				return data;

			}
			if (date_to != null || date_to.trim().equals("") || date_to.equals("DD/MM/YYYY")) {

				if (!valid.isValidDate(date_to)) {
					data.put("error", valid.isValidDateMSG + " of To ");

					return data;
				} else {
					fr_to = format.parse(date_to);
				}

			}
			if (format.parse(date_to).compareTo(format.parse(date_form)) < 0) {

				data.put("error", "To Date  Should Be Greater Than Or Equal To From Date");

				return data;

			}

			if (total_rank == null || total_rank.trim().equals("")) {

				data.put("error", "Please Enter valid From And To Date");

				return data;

			}

			if (!total_rank.equals(mcommon.calculate_duration(format.parse(date_form), format.parse(date_to)))) {

				data.put("error", "Please Enter valid From And To Date");

				return data;

			}

			if (!cadet_status.equals("5") && !cadet_status.equals("9")) {

				unit_reg = request.getParameter("unit_reg1");

			}

			if (unit_reg == null || unit_reg.trim().equals("")) {

				data.put("error", "Please Enter Unit/Regiment ");

				return data;

			}
			if (unit_reg != null && !unit_reg.trim().equals("")) {

				if (!valid.isOnlyAlphabetNumeric(unit_reg)) {
					data.put("error", " Unit/Regiment " + valid.isOnlyAlphabetNumericMSG);
					return data;
				}

				if (!valid.isvalidLength(unit_reg, valid.authorityMax, valid.authorityMin)) {
					data.put("error", "Unit/Regiment " + valid.isValidLengthMSG);
					return data;
				}
			}
		}

		try {
int check_precadet=check_duplicate(com_id,"TB_CENSUS_CADET");
			if (Integer.parseInt(pre_ch_id) == 0) {

				TB_CENSUS_CADET pre_cadet = new TB_CENSUS_CADET();

				pre_cadet.setPre_cadet_status(Integer.parseInt(cadet_status));

				pre_cadet.setGazetted(isgazetted);

				pre_cadet.setCivil_service(isCivil);

				pre_cadet.setDesignation(designation);

				pre_cadet.setEmployee_name(emp_name);

				pre_cadet.setCompetency_other(competency_other);

				pre_cadet.setArmy_no(army_no);

				pre_cadet.setFrom_date(fr_dt);

				if (date_to != null) {

					pre_cadet.setTo_date(format.parse(date_to));

				}

				pre_cadet.setTotal_service(total_rank);

				pre_cadet.setCompetency(Integer.parseInt(competency));

				pre_cadet.setUnit_reg(unit_reg);

				pre_cadet.setCensus_id(Integer.parseInt(p_id));

				pre_cadet.setComm_id(com_id);

				pre_cadet.setCreated_by(username);

				pre_cadet.setCreated_date(date);

				int id = (int) sessionhql.save(pre_cadet);

				pre_ch_id = Integer.toString(id);

			} else {

				String hql = "update TB_CENSUS_CADET set modified_by=:modified_by ,modified_date=:modified_date , pre_cadet_status=:pre_cadet_status, designation=:designation , "

						+ "employee_name=:employee_name,gazetted=:gazetted ,civil_service=:civil_service,from_date=:from_date,to_date=:to_date,army_no=:army_no"

						+ " ,total_service=:total_service ,unit_reg=:unit_reg ,competency=:competency,competency_other=:competency_other where  id=:id";

				Query query = sessionhql.createQuery(hql).setInteger("pre_cadet_status", Integer.parseInt(cadet_status))

						.setString("designation", designation).setString("employee_name", emp_name)

						.setString("gazetted", isgazetted).setString("civil_service", isCivil)

						.setTimestamp("from_date", fr_dt).setTimestamp("to_date", fr_to).setString("army_no", army_no)

						.setString("total_service", total_rank).setString("unit_reg", unit_reg)

						.setInteger("id", Integer.parseInt(pre_ch_id))

						.setInteger("competency", Integer.parseInt(competency)).setString("modified_by", username)

						.setString("competency_other", competency_other).setTimestamp("modified_date", new Date());

				rvalue = query.executeUpdate() > 0 ? "update" : "0";

			}

			if (ncc_ch_id != null) {
int check_ncc=check_duplicate(com_id,"TB_CENSUS_NCC_EXP");

				if (check_ncc == 0) {

					TB_CENSUS_NCC_EXP ncc = new TB_CENSUS_NCC_EXP();

					ncc.setOtu(otu);

					ncc.setCen_id(Integer.parseInt(p_id));

					ncc.setComm_id(com_id);

					ncc.setCreated_by(username);

					ncc.setCreated_on(date);

					ncc.setStatus(0);

					int id = (int) sessionhql.save(ncc);

					ncc_ch_id = Integer.toString(id);

				} else {

					String hql_n = "update TB_CENSUS_NCC_EXP set modify_by=:modify_by ,modify_on=:modify_on , otu=:otu "

							+ " where  id=:id";

					Query query_n = sessionhql.createQuery(hql_n).setString("otu", otu)

							.setInteger("id", Integer.parseInt(ncc_ch_id)).setString("modify_by", username)

							.setTimestamp("modify_on", new Date());

					rvalue = query_n.executeUpdate() > 0 ? "update" : "0";

				}

			}

			tx.commit();

			data.put("pre_ch_id", pre_ch_id);

			data.put("ncc_ch_id", ncc_ch_id);

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				data.put("error", "Data Not Updated");

			} catch (RuntimeException rbe) {

				data.put("error", "Data Not Updated");

			}

		} finally {

			if (sessionhql != null) {

				sessionhql.close();

			}

		}

		return data;

	}

	@RequestMapping(value = "/admin/pre_cadet_delete_action", method = RequestMethod.POST)

	public @ResponseBody String pre_cadet_delete_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("pre_ch_id"));

		try {

			String hqlUpdate = "delete from TB_CENSUS_CADET where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();

			tx.commit();

			sessionHQL.close();

			if (app > 0) {

				msg = "1";

			} else {

				msg = "0";

			}

		} catch (Exception e) {

		}

		return msg;

	}

	@RequestMapping(value = "/admin/getPreCreditData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_CADET> getPreCreditData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String approoval_status = request.getParameter("app_status");

		int id = Integer.parseInt(request.getParameter("p_id"));

		String hqlUpdate = " from TB_CENSUS_CADET where census_id=:census_id  ";

		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {

			hqlUpdate += " and status=1 ";

		} else {

			hqlUpdate += " and status=0 ";

		}

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id);

		List<TB_CENSUS_CADET> list = (List<TB_CENSUS_CADET>) query.list();

		tx.commit();

		sessionHQL.close();

		

		return list;

	}

	@RequestMapping(value = "/GetPersonnelNoData", method = RequestMethod.POST)

	public @ResponseBody List<String> GetPersonnelNoData(String personnel_no) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		try {
//Updated on 23-06-2022 for getting non-effective data in record of View Approve Census form
			Query q1 = sessionHQL.createQuery(

					"select c.id,c.cadet_no,c.batch_no,g.gender_name,c.date_of_birth ,c.name,cu.description,ct.course_name,c.parent_arm,c.regiment,cu.code as rankcode,c.date_of_commission  FROM TB_TRANS_PROPOSED_COMM_LETTER c ,TB_GENDER g ,"

							+ " TB_COURSE ct,CUE_TB_PSG_RANK_APP_MASTER cu where upper(c.personnel_no) =:personnel_no and c.gender=g.id and "

							+ " c.course=ct.id and cu.id=c.rank and (c.status='1' or c.status='4' or c.status = '5')   order by c.personnel_no");

			q1.setParameter("personnel_no", personnel_no.toUpperCase());

			@SuppressWarnings("unchecked")

			List<String> list = (List<String>) q1.list();
			
			tx.commit();

			return list;

		} catch (RuntimeException e) {

			return null;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

	}

	@RequestMapping(value = "/CheckPersonnelNoData", method = RequestMethod.POST)

	public @ResponseBody List<String> CheckPersonnelNoData(String personnel_no) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q1 = sessionHQL.createQuery(

					"select c.id,c.status,c.update_comm_status FROM TB_TRANS_PROPOSED_COMM_LETTER c where upper(personnel_no) = :personnel_no  order by personnel_no");

			q1.setParameter("personnel_no", personnel_no.toUpperCase());

			@SuppressWarnings("unchecked")

			List<String> list = (List<String>) q1.list();
			tx.commit();

			return list;

		} catch (RuntimeException e) {

			return null;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

	}
	@RequestMapping(value = "/GetCensusData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_DETAIL_Parent> GetCensusData(BigInteger comm_id, HttpServletRequest request) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		

		Transaction tx = sessionHQL.beginTransaction();

		String approoval_status = request.getParameter("app_status");

		//try {

			String hql = "FROM TB_CENSUS_DETAIL_Parent c where comm_id = :comm_id ";

			if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {

				hql += " and status=1 ";

			} else {

				hql += " and status in (-1,0,3) ";

			}
			Query q1 = sessionHQL.createQuery(hql);

			q1.setParameter("comm_id", comm_id);

			@SuppressWarnings("unchecked")

			List<TB_CENSUS_DETAIL_Parent> list = (List<TB_CENSUS_DETAIL_Parent>) q1.list();

			

			tx.commit();

			return list;

		/*} catch (RuntimeException e) {

			return null;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}*/
		
		//sessionHQL.close();

	}

	// ************************************START PERSONNEL
	// DETAIL*******************************************


	


	@RequestMapping(value = "/admin/Personnel_Detailaction", method = RequestMethod.POST)

	public @ResponseBody Map<String, String> Personnel_DetailAction(ModelMap Mmap, HttpSession session,

			HttpServletRequest request, MultipartHttpServletRequest mul) throws IOException, ParseException {

		String fname = "";

		String extension = "";

		String allergic_radio = request.getParameter("allergic_radio");

		int allergic_count = Integer.parseInt(request.getParameter("allergic_count"));

		int allergicOld_count = Integer.parseInt(request.getParameter("allergicOld_count"));
		Map<String, String> data = new HashMap<>();

		int census_id = 0;

		if (Integer.parseInt(request.getParameter("census_id")) != 0) {

			census_id = Integer.parseInt(request.getParameter("census_id"));

		}

		int med_id = 0;

		if (Integer.parseInt(request.getParameter("med_id")) != 0) {

			med_id = Integer.parseInt(request.getParameter("med_id"));

		}

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int com_institute = 0;

		int training_institute = 0;

		int district_birth = 0;

		int state_birth = 0;

		int country_birth = 0;

		int nationality = 0;

		int religion = 0;

		int marital_status = 0;

		int blood_group = 0;

		int mother = 0;

		int mother_tongue = 0;

		int ncc_type = 0;

		int height = 0;

		BigInteger comm_id = BigInteger.ZERO;

		// BigInteger adhar_number = BigInteger.ZERO;

		String first_name = request.getParameter("first_name");

		String middle_name = request.getParameter("middle_name");

		String last_name = request.getParameter("last_name");

		String pl_birth = request.getParameter("place_birth");

		String d_birth = request.getParameter("district_birth");

		String s_birth = request.getParameter("state_birth");

		String coun_birth = request.getParameter("country_birth");

		String nat = request.getParameter("nationality");

		String reli = request.getParameter("religion");

		String blood = request.getParameter("blood_group");

		String r = request.getParameter("rh");

		String h = request.getParameter("height");

		String adhar1 = request.getParameter("adhar_number1");

		String adhar2 = request.getParameter("adhar_number2");

		String adhar3 = request.getParameter("adhar_number3");

		String co_id = request.getParameter("comm_id");

		String border_area = request.getParameter("border_area");

		String mar_status = request.getParameter("marital_status");

		String id_card_no = request.getParameter("id_card_no");

		String issue_dt = request.getParameter("issue_dt");
		String father_name = request.getParameter("father_name");
		String mother_name = request.getParameter("mother_name");
		String id_marks = request.getParameter("id_marks");

		String hair_colour = request.getParameter("hair_colour");

		String eye_colour = request.getParameter("eye_colour");

		String issue_authority = request.getParameter("issue_authority_sus");

		String mt = request.getParameter("mother_tongue");

		String ncc = request.getParameter("ncc_type");

		String com_ins = request.getParameter("com_institute");

		String com_bn_id = request.getParameter("com_bn_id");

		String com_company_id = request.getParameter("com_company_id");

		String training_ins = request.getParameter("training_institute");

		String training_bn_id = request.getParameter("training_bn_id");

		String training_company_id = request.getParameter("training_company_id");

		String country_birth_other = request.getParameter("country_birth_other");

		String state_birth_other = request.getParameter("state_birth_other");

		String district_birth_other = request.getParameter("district_birth_other");

		String nationality_other = request.getParameter("nationality_other");

		String mother_tongue_other = request.getParameter("mother_tongue_other");

		String religion_other = request.getParameter("religion_other");

		String hair_colour_other = request.getParameter("hair_colour_other");

		String eye_colour_other = request.getParameter("eye_colour_other");

		String com_platoon_id = request.getParameter("com_platoon_id");

		String pan_no = request.getParameter("pan_no");

		String org_domicile_country = request.getParameter("org_domicile_Country");

		String org_domicile_tehsil = request.getParameter("org_domicile_tehsil");

		String org_domicile_state = request.getParameter("org_domicile_state");

		String org_domicile_district = request.getParameter("org_domicile_district");
		String org_domicile_Country_other = request.getParameter("org_domicile_Country_other");

		String org_domicile_state_other = request.getParameter("org_domicile_state_other");

		String org_domicile_district_other = request.getParameter("org_domicile_district_other");

		String org_domicile_tehsil_other = request.getParameter("org_domicile_tehsil_other");

		String org_domicile_Country_otherV = request.getParameter("org_co");

		String org_domicile_state_otherV = request.getParameter("org_so");

		String org_domicile_district_otherV = request.getParameter("org_do");

		String org_domicile_tehsil_otherV = request.getParameter("org_to");

		// pre-cadet

		Date fr_dt = null;
		Date fr_to = null;

		String cadet_status = request.getParameter("cadet_status1");

		String isgazetted = null;

		String isCivil = null;

		String designation = null;

		String emp_name = null;

		String army_no = null;

		String date_form = null;

		String date_to = null;

		String total_rank = null;

		String unit_reg = null;

		String competency = "0";

		String competency_other = null;

		int medDtlFillIn3008 = Integer.parseInt(request.getParameter("medDtlFillIn3008").toString());
		// medical detail

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Boolean validData = false;

		int sShape_count = Integer.parseInt(request.getParameter("sShape_count").toString()); // total count

		int sShapeOld_count = Integer.parseInt(request.getParameter("sShapeOld_count").toString());// oldcount

		int hShape_count = Integer.parseInt(request.getParameter("hShape_count").toString()); // total count

		int hShapeOld_count = Integer.parseInt(request.getParameter("hShapeOld_count").toString());// oldcount

		int aShape_count = Integer.parseInt(request.getParameter("aShape_count").toString()); // total count

		int aShapeOld_count = Integer.parseInt(request.getParameter("aShapeOld_count").toString());// oldcount

		int pShape_count = Integer.parseInt(request.getParameter("pShape_count").toString()); // total count

		int pShapeOld_count = Integer.parseInt(request.getParameter("pShapeOld_count").toString());// oldcount

		int eShape_count = Integer.parseInt(request.getParameter("eShape_count").toString()); // total count

		int eShapeOld_count = Integer.parseInt(request.getParameter("eShapeOld_count").toString());// oldcount

		int cCope_count = Integer.parseInt(request.getParameter("cCope_count").toString()); // total count

		int cCopeOld_count = Integer.parseInt(request.getParameter("cCopeOld_count").toString());// oldcount

		int oCope_count = Integer.parseInt(request.getParameter("oCope_count").toString()); // total count

		int oCopeOld_count = Integer.parseInt(request.getParameter("oCopeOld_count").toString());// oldcount

		int pCope_count = Integer.parseInt(request.getParameter("pCope_count").toString()); // total count

		int pCopeOld_count = Integer.parseInt(request.getParameter("pCopeOld_count").toString());// oldcount

		int eCope_count = Integer.parseInt(request.getParameter("eCope_count").toString()); // total count

		int eCopeOld_count = Integer.parseInt(request.getParameter("eCopeOld_count").toString());// oldcount

		String mad_classification = request.getParameter("mad_classification_count");

		String checkbox_1bx = request.getParameter("check_1bx");

		String _1BX_from_date = request.getParameter("1bx_from_date");

		String _1BX_to_date = request.getParameter("1bx_to_date");

		String _1BX_diagnosis = request.getParameter("1bx_diagnosis_code");

		int diffrence;

		Date is_dt = null;

		if (com_ins != null && !com_ins.trim().equals("")) {

			com_institute = Integer.parseInt(com_ins);

		}

		if (training_ins != null && !training_ins.trim().equals("")) {

			training_institute = Integer.parseInt(training_ins);

		}

		if (mt != null && !mt.trim().equals("")) {

			mother_tongue = Integer.parseInt(mt);

		}

		if (ncc != null && !ncc.trim().equals("")) {

			ncc_type = Integer.parseInt(ncc);

		}

		if (d_birth != null && !d_birth.trim().equals("")) {

			district_birth = Integer.parseInt(d_birth);

		}

		if (s_birth != null && !s_birth.trim().equals("")) {

			state_birth = Integer.parseInt(s_birth);

		}

		if (coun_birth != null && !coun_birth.trim().equals("")) {

			country_birth = Integer.parseInt(coun_birth);

		}

		if (nat != null && !nat.trim().equals("")) {

			nationality = Integer.parseInt(nat);

		}

		if (reli != null && !reli.trim().equals("")) {

			religion = Integer.parseInt(reli);

		}

		if (mar_status != null && !mar_status.trim().equals("")) {

			marital_status = Integer.parseInt(mar_status);

		}

		if (blood != null && !blood.trim().equals("")) {

			blood_group = Integer.parseInt(blood);

		}

		if (h != null && !h.trim().equals("")) {

			height = Integer.parseInt(h);

		}
		String aadhar_card = "";
		String adhar_number = "";
		if (adhar1 != null && !adhar1.trim().equals("") && adhar2 != null && !adhar2.trim().equals("") && adhar3 != null

				&& !adhar3.trim().equals("")) {

			adhar_number = (adhar1 + adhar2 + adhar3);
			aadhar_card = adhar1 + adhar2 + adhar3;

		}

		if (co_id != null && !co_id.trim().equals("")) {

			comm_id = new BigInteger(co_id);

		}

		if (first_name == null || first_name.trim().equals("")) {

			data.put("error", "Please Enter The First Name  ");

			return data;

		}
		if (first_name != null && !first_name.trim().equals("")) {
			if (!valid.isOnlyAlphabet(first_name)) {
				data.put("error", " First Name " + valid.isOnlyAlphabetMSG);
				return data;
			}
			if (!valid.isvalidLength(first_name, valid.nameMax, valid.nameMin)) {
				data.put("error", "First Name " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (middle_name != null && !middle_name.trim().equals("")) {
			if (!valid.isOnlyAlphabet(middle_name)) {
				data.put("error", " Middle Name " + valid.isOnlyAlphabetMSG);
				return data;
			}
			if (!valid.isvalidLength(middle_name, valid.nameMax, valid.nameMin)) {
				data.put("error", "Middle Name " + valid.isValidLengthMSG);
				return data;
			}
		}

		if (last_name != null && !last_name.trim().equals("")) {
			if (!valid.isOnlyAlphabet(last_name)) {
				data.put("error", " Last Name " + valid.isOnlyAlphabetMSG);
				return data;
			}
			if (!valid.isvalidLength(last_name, valid.nameMax, valid.nameMin)) {
				data.put("error", "Last Name " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (coun_birth == null || coun_birth.equals("0")) {

			data.put("error", "Please Select The Country of Birth  ");

			return data;

		}
		if (country_birth_other != null && !country_birth_other.trim().equals("")) {
			if (!valid.isOnlyAlphabet(country_birth_other)) {

				data.put("error", " Country of Birth Other " + valid.isOnlyAlphabetMSG);

				return data;
			}
			if (!valid.isvalidLength(country_birth_other, valid.nameMax, valid.nameMin)) {

				data.put("error", "Country of Birth Other " + valid.isValidLengthMSG);

				return data;
			}
		}

		if (s_birth == null || s_birth.equals("0")) {

			data.put("error", "Please Select The State of Birth ");

			return data;

		}

		if (state_birth_other != null && !state_birth_other.trim().equals("")) {
			if (!valid.isOnlyAlphabet(state_birth_other)) {

				data.put("error", " State of Birth Other " + valid.isOnlyAlphabetMSG);

				return data;
			}
			if (!valid.isvalidLength(state_birth_other, valid.nameMax, valid.nameMin)) {

				data.put("error", "State of Birth Other " + valid.isValidLengthMSG);

				return data;
			}
		}
		if (d_birth == null || d_birth.equals("0")) {

			data.put("error", "Please Select The District of Birth");

			return data;

		}
		if (district_birth_other != null && !district_birth_other.trim().equals("")) {
			if (!valid.isOnlyAlphabet(district_birth_other)) {

				data.put("error", " District of Birth Other " + valid.isOnlyAlphabetMSG);

				return data;
			}
			if (!valid.isvalidLength(district_birth_other, valid.nameMax, valid.nameMin)) {

				data.put("error", "District of Birth Other " + valid.isValidLengthMSG);

				return data;
			}
		}
		if (pl_birth == null || pl_birth.trim().equals("")) {

			data.put("error", "Please Enter The Place of Birth ");

			return data;

		}

		if (pl_birth != null && !pl_birth.trim().equals("")) {
			if (!valid.isOnlyAlphabet(pl_birth)) {
				data.put("error", " Place of Birth " + valid.isOnlyAlphabetMSG);
				return data;
			}
			if (!valid.isvalidLength(pl_birth, valid.nameMax, valid.nameMin)) {
				data.put("error", "Place of Birth " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (nat == null || nat.equals("0")) {

			data.put("error", "Please Select The Nationality ");

			return data;

		}
		if (nationality_other != null && !nationality_other.trim().equals("")) {
			if (!valid.isOnlyAlphabet(nationality_other)) {
				data.put("error", " Nationality Other " + valid.isOnlyAlphabetMSG);
				return data;
			}
			if (!valid.isvalidLength(nationality_other, valid.nameMax, valid.nameMin)) {
				data.put("error", "Nationality Other " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (mt == null || mt.equals("0")) {

			data.put("error", "Please Select The Mother Tongue ");

			return data;

		}
		if (mother_tongue_other != null && !mother_tongue_other.trim().equals("")) {
			if (!valid.isOnlyAlphabet(mother_tongue_other)) {
				data.put("error", " Mother Tongue Other " + valid.isOnlyAlphabetMSG);
				return data;
			}
			if (!valid.isvalidLength(mother_tongue_other, valid.nameMax, valid.nameMin)) {
				data.put("error", "Mother Tongue Other " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (reli == null || reli.equals("0")) {

			data.put("error", "Please Select The Religion ");

			return data;

		}
		if (religion_other != null && !religion_other.trim().equals("")) {
			if (!valid.isOnlyAlphabet(religion_other)) {
				data.put("error", " Religion Other " + valid.isOnlyAlphabetMSG);
				return data;
			}
			if (!valid.isvalidLength(religion_other, valid.nameMax, valid.nameMin)) {
				data.put("error", "Religion Other " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (ncc == null || ncc.equals("0")) {

			data.put("error", "Please Select The Type of Entry ");

			return data;

		}

//			if (adhar1 == null || adhar1.trim().equals("") || adhar2 == null || adhar2.trim().equals("") || adhar3 == null
		//
//					|| adhar3.trim().equals("")) {
		//
//				data.put("error", "Please Enter Aadhaar Number ");
		//
//				return data;
		//
//			}
		if (aadhar_card != "") {
			if (!valid.isValidAadhar(aadhar_card)) {
				data.put("error", valid.isValidAadharMSG);
				return data;
			}
		}
//			if (pan_no == null || pan_no.trim().equals("")) {
		//
//				data.put("error", "Please Enter PAN Number");
		//
//				return data;
		//
//			}

		if (!pan_no.equals("") && !pan_no.equals(" ")) {
			if (!valid.isValidPan(pan_no)) {
				data.put("error", valid.isValidPanMSG);
				return data;
			}

			if (!valid.PanNoLength(pan_no)) {
				data.put("error", valid.PanNoMSG);
				return data;
			}
		}
		if (com_ins == null || com_ins.trim().equals("0")) {

			data.put("error", "Please Select Commissioning institute");

			return data;

		}

		if (mar_status == null || mar_status.equals("0")) {

			data.put("error", "Please Select The Maritial Status ");

			return data;

		}

		if (blood == null || blood.equals("0")) {

			data.put("error", "Please Select The Blood Group ");

			return data;

		}

		if (h == null || h.equals("0")) {

			data.put("error", "Please Select The Height ");

			return data;

		}

		if (allergic_radio.equals("yes")) {

			for (int al = 1; al <= allergic_count; al++) {

				if (request.getParameter("medicine" + al).trim().equals("")

						|| request.getParameter("medicine" + al) == null) {

					data.put("error", "Please Enter Medicine In " + al + " Row");

					return data;

				}

			}

		}

		if (org_domicile_country == null || org_domicile_country.equals("0")) {

			data.put("error", "Please Select The Original Domicile of Country");

			return data;

		}

		if (org_domicile_Country_otherV != null && org_domicile_Country_otherV.equals("OTHERS")) {

			if (org_domicile_Country_other == null || org_domicile_Country_other.trim().equals("")) {

				data.put("error", "Please Enter The Original Domicile of Country Other");

				return data;

			}
			if (org_domicile_Country_other != null && !org_domicile_Country_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(org_domicile_Country_other)) {
					data.put("error", " Original Domicile of Country Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(org_domicile_Country_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Original Domicile of Country Other " + valid.isValidLengthMSG);
					return data;
				}
			}

		} else {

			org_domicile_Country_other = null;

		}

		if (org_domicile_state == null || org_domicile_state.equals("0")) {

			data.put("error", "Please Select The Original Domicile of State");

			return data;

		}

		if (org_domicile_state_otherV != null && org_domicile_state_otherV.equals("OTHERS")) {

			if (org_domicile_state_other == null || org_domicile_state_other.trim().equals("")) {

				data.put("error", "Please Enter The Original Domicile of State Other");

				return data;

			}
			if (org_domicile_state_other != null && !org_domicile_state_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(org_domicile_state_other)) {
					data.put("error", "Original Domicile of State Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(org_domicile_state_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Original Domicile of State Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			org_domicile_state_other = null;

		}

		if (org_domicile_district == null || org_domicile_district.equals("0")) {

			data.put("error", "Please Select The Original Domicile of District");

			return data;

		}

		if (org_domicile_district_otherV != null && org_domicile_district_otherV.equals("OTHERS")) {

			if (org_domicile_district_other == null || org_domicile_district_other.trim().equals("")) {

				data.put("error", "Please Enter The Original Domicile of District Other");

				return data;

			}
			if (org_domicile_district_other != null && !org_domicile_district_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(org_domicile_district_other)) {
					data.put("error", "Original Domicile of District Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(org_domicile_district_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Original Domicile of District Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			org_domicile_district_other = null;

		}

		if (org_domicile_tehsil == null || org_domicile_tehsil.equals("0")) {

			data.put("error", "Please Select The Original Domicile of Tehsil");

			return data;

		}

		if (org_domicile_tehsil_otherV != null && org_domicile_tehsil_otherV.equals("OTHERS")) {

			if (org_domicile_tehsil_other == null || org_domicile_tehsil_other.trim().equals("")) {

				data.put("error", "Please Enter The Original Domicile of Tehsil Other");

				return data;

			}
			if (org_domicile_tehsil_other != null && !org_domicile_tehsil_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(org_domicile_tehsil_other)) {
					data.put("error", "Original Domicile of Tehsil Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(org_domicile_tehsil_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Original Domicile of Tehsil Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			org_domicile_tehsil_other = null;

		}

		if (cadet_status.equals("2") || cadet_status.equals("3") || cadet_status.equals("4")) {

			if (!cadet_status.equals("2")) {

				isgazetted = request.getParameter("isgazetted1");

				isCivil = request.getParameter("isCivil1");

			} else {

				competency = request.getParameter("competency");

				if (!request.getParameter("competency_other").toString().trim().equals("")) {

					competency_other = request.getParameter("competency_other").toString();

				}

			}

			designation = request.getParameter("designation1");

			emp_name = request.getParameter("emp_name1");

		}

		if (cadet_status.equals("5") || cadet_status.equals("6") || cadet_status.equals("7") || cadet_status.equals("8")

				|| cadet_status.equals("9")) {

			army_no = request.getParameter("army_no1");

			date_form = request.getParameter("date_form1");

			date_to = request.getParameter("date_to1");

			if (format.parse(date_to).compareTo(format.parse(date_form)) < 0) {

				data.put("error", "To Date  Should Be Greater Than Or Equal To From Date");

				return data;

			}

			total_rank = request.getParameter("total_rank1");

			unit_reg = request.getParameter("sus_no");

		}

		////////////////////////////// nccc

		String pre_ch_id = request.getParameter("pre_ch_id1");

		String rvalue = "";
		String otu = request.getParameter("otu1");
		String ncc_ch_id = request.getParameter("ncc_ch_id1");

		if (ncc_ch_id != null) {

			if (otu == null || otu.trim().equals("")) {

				data.put("error", "Please select Whether in OTU Div/Jr Div/Sr Div");

				return data;

			}

		}

		if (cadet_status == null || cadet_status.equals("0")) {

			data.put("error", "Please Select Pre-Cadet Status");

			return data;

		}

		if (cadet_status.equals("2") || cadet_status.equals("3") || cadet_status.equals("4")) {

			if (cadet_status.equals("2")) {

				if (competency == null || competency.equals("0")) {

					data.put("error", "Please Select Competency");

					return data;

				}
				if (request.getParameter("competency_other") != null
						&& !request.getParameter("competency_other").trim().equals("")) {

					if (!valid.isOnlyAlphabet(request.getParameter("competency_other"))) {
						data.put("error", " Competency Other " + valid.isOnlyAlphabetMSG);
						return data;
					}

					if (!valid.isvalidLength(request.getParameter("competency_other"), valid.nameMax, valid.nameMin)) {
						data.put("error", "Competency Other " + valid.isValidLengthMSG);
						return data;
					}
				}
			}

			if (designation == null || designation.trim().equals("")) {

				data.put("error", "Please Enter Designation");

				return data;

			}
			if (designation != null && !designation.trim().equals("")) {

				if (!valid.isOnlyAlphabet(designation)) {
					data.put("error", " Designation " + valid.isOnlyAlphabetMSG);
					return data;
				}

				if (!valid.isvalidLength(designation, valid.nameMax, valid.nameMin)) {
					data.put("error", "Designation " + valid.isValidLengthMSG);
					return data;
				}
			}
			if (emp_name == null || emp_name.trim().equals("")) {

				data.put("error", "Please Enter Name of Employer");

				return data;

			}
			if (emp_name != null && !emp_name.trim().equals("")) {

				if (!valid.isOnlyAlphabet(emp_name)) {
					data.put("error", " Name of Employer " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(emp_name, valid.authorityMax, valid.authorityMin)) {
					data.put("error", "Name of Employer " + valid.isValidLengthMSG);
					return data;
				}
			}
		}
		if (cadet_status.equals("5") || cadet_status.equals("6") || cadet_status.equals("7") || cadet_status.equals("8")

				|| cadet_status.equals("9")) {

			if (army_no == null || army_no.trim().equals("")) {

				data.put("error", "Please Enter Service No");

				return data;

			}
			if (army_no != null && !army_no.trim().equals("")) {

				if (!valid.isOnlyAlphabetNumericSpaceNot(army_no)) {
					data.put("error", " Service No " + valid.isOnlyAlphabetNumericSpaceNotMSG);
					return data;
				}

				if (!valid.isvalidLength(army_no, valid.nameMax, valid.nameMin)) {
					data.put("error", "Service No " + valid.isValidLengthMSG);
					return data;
				}
			}
			if (date_form == null || date_form.trim().equals("")) {

				data.put("error", "Please Select Form Date");

				return data;

			}
			if (date_form != null || date_form.trim().equals("") || date_form.equals("DD/MM/YYYY")) {

				if (!valid.isValidDate(date_form)) {
					data.put("error", valid.isValidDateMSG + " of From ");

					return data;
				} else {
					fr_dt = format.parse(date_form);
				}

			}

			if (date_to == null || date_to.trim().equals("")) {

				data.put("error", "Please Select To Date");

				return data;

			}
			if (date_to != null || date_to.trim().equals("") || date_to.equals("DD/MM/YYYY")) {

				if (!valid.isValidDate(date_to)) {
					data.put("error", valid.isValidDateMSG + " of To ");

					return data;
				} else {
					fr_to = format.parse(date_to);
				}

			}
			if (format.parse(date_to).compareTo(format.parse(date_form)) < 0) {

				data.put("error", "To Date  Should Be Greater Than Or Equal To From Date");

				return data;

			}

			if (total_rank == null || total_rank.trim().equals("")) {

				data.put("error", "Please Enter valid From And To Date");

				return data;

			}

			if (!total_rank.equals(mcommon.calculate_duration(format.parse(date_form), format.parse(date_to)))) {

				data.put("error", "Please Enter valid From And To Date");

				return data;

			}

			if (!cadet_status.equals("5") && !cadet_status.equals("9")) {

				unit_reg = request.getParameter("unit_reg1");

			}

			if (unit_reg == null || unit_reg.trim().equals("")) {

				data.put("error", "Please Enter Unit/Regiment ");

				return data;

			}
			if (unit_reg != null && !unit_reg.trim().equals("")) {

				if (!valid.isOnlyAlphabetNumeric(unit_reg)) {
					data.put("error", " Unit/Regiment " + valid.isOnlyAlphabetNumericMSG);
					return data;
				}

				if (!valid.isvalidLength(unit_reg, valid.authorityMax, valid.authorityMin)) {
					data.put("error", "Unit/Regiment " + valid.isValidLengthMSG);
					return data;
				}
			}
		}

		int i_id = Integer.parseInt(request.getParameter("i_id"));
		String comm_id_str = comm_id.toString();

		try {
			String name1 = first_name + " " + middle_name + " " + last_name;

			TB_CENSUS_DETAIL_Parent census_p = new TB_CENSUS_DETAIL_Parent();

			census_p.setFather_name(father_name);
			census_p.setMother_name(mother_name);

			census_p.setFirst_name(first_name);

			census_p.setMiddle_name(middle_name);

			census_p.setLast_name(last_name);

			census_p.setPlace_birth(pl_birth);

			census_p.setDistrict_birth(district_birth);

			census_p.setDistrict_birth_other(district_birth_other);

			census_p.setState_birth(state_birth);

			census_p.setState_birth_other(state_birth_other);

			census_p.setCountry_birth(country_birth);

			census_p.setCountry_birth_other(country_birth_other);

			census_p.setNationality(nationality);

			census_p.setNationality_other(nationality_other);

			census_p.setReligion(religion);

			census_p.setReligion_other(religion_other);

			census_p.setMarital_status(marital_status);

			census_p.setComm_id(comm_id);

			census_p.setBlood_group(blood_group);

			census_p.setHeight(height);

			census_p.setAdhar_number(adhar_number);

			// census_p.setBorder_area(border_area);

			census_p.setMother_tongue(mother_tongue);

			census_p.setMother_tongue_other(mother_tongue_other);

			census_p.setNcc_type(ncc_type);

			census_p.setCom_institute(com_institute);

			census_p.setCom_bn_id(Integer.parseInt(com_bn_id));

			census_p.setCom_company_id(Integer.parseInt(com_company_id));

			census_p.setCom_platoon_id(0);

			census_p.setTraining_institute(training_institute);

			census_p.setTraining_bn_id(Integer.parseInt(training_bn_id));

			census_p.setTraining_company_id(Integer.parseInt(training_company_id));

			census_p.setPancard_number(pan_no);

			census_p.setOrg_country(Integer.parseInt(org_domicile_country));
			census_p.setOrg_state(Integer.parseInt(org_domicile_state));
			census_p.setOrg_district(Integer.parseInt(org_domicile_district));
			census_p.setOrg_tehsil(Integer.parseInt(org_domicile_tehsil));

			census_p.setOrg_domicile_country_other(org_domicile_Country_other);
			census_p.setOrg_domicile_state_other(org_domicile_state_other);
			census_p.setOrg_domicile_district_other(org_domicile_district_other);
			census_p.setOrg_domicile_tehsil_other(org_domicile_tehsil_other);

			TB_CENSUS_CADET pre_cadet = new TB_CENSUS_CADET();
			pre_cadet.setPre_cadet_status(Integer.parseInt(cadet_status));
			pre_cadet.setGazetted(isgazetted);
			pre_cadet.setCivil_service(isCivil);
			pre_cadet.setDesignation(designation);
			pre_cadet.setEmployee_name(emp_name);
			pre_cadet.setCompetency_other(competency_other);
			pre_cadet.setArmy_no(army_no);
			pre_cadet.setFrom_date(fr_dt);
			if (date_to != null) {
				pre_cadet.setTo_date(format.parse(date_to));
			}
			pre_cadet.setTotal_service(total_rank);
			pre_cadet.setCompetency(Integer.parseInt(competency));
			pre_cadet.setUnit_reg(unit_reg);
			pre_cadet.setComm_id(comm_id);
			pre_cadet.setCreated_by(username);
			pre_cadet.setCreated_date(date);

			TB_TRANS_PROPOSED_COMM_LETTER comm1 = getdatecommdate(comm_id);
			census_id = censusAprovedDAO.getcensusid(comm_id);
			if (census_id == 0) {
				census_p.setCreated_by(username);
				census_p.setCreated_date(date);
				census_p.setStatus(0);
				census_id = (Integer) sessionHQL.save(census_p);
				census_id = census_p.getId();

			} else {

				String hql = "update TB_CENSUS_DETAIL_Parent set first_name=:first_name,middle_name=:middle_name,"

						+ "last_name=:last_name,place_birth=:place_birth,district_birth=:district_birth,district_birth_other=:district_birth_other,state_birth=:state_birth,state_birth_other=:state_birth_other,"

						+ "country_birth=:country_birth,country_birth_other=:country_birth_other,nationality=:nationality,nationality_other=:nationality_other,religion=:religion,religion_other=:religion_other,marital_status=:marital_status,blood_group=:blood_group,height=:height,"

						+ " adhar_number=pgp_sym_encrypt(:adhar_number,current_setting('miso.version')),mother_tongue=:mother_tongue,mother_tongue_other=:mother_tongue_other,"

						+ " ncc_type=:ncc_type,org_country=:org_country,org_state=:org_state,org_district=:org_district,"
						+ "org_tehsil=:org_tehsil,org_domicile_country_other=:org_domicile_country_other,org_domicile_state_other=:org_domicile_state_other,org_domicile_district_other=:org_domicile_district_other,org_domicile_tehsil_other=:org_domicile_tehsil_other,com_institute=:com_institute,status=:status,com_bn_id=:com_bn_id,com_company_id=:com_company_id,com_platoon_id=:com_platoon_id,training_institute=:training_institute,training_bn_id=:training_bn_id,training_company_id=:training_company_id,pancard_number=pgp_sym_encrypt(:pancard_number,current_setting('miso.version')),father_name=:father_name,mother_name=:mother_name where id=:id";

				Query query = sessionHQL.createQuery(hql).setString("first_name", census_p.getFirst_name())

						.setString("middle_name", census_p.getMiddle_name())
						.setString("last_name", census_p.getLast_name())
						.setString("place_birth", census_p.getPlace_birth())
						.setString("pancard_number", census_p.getPancard_number())
						.setInteger("district_birth", census_p.getDistrict_birth())
						.setString("district_birth_other", census_p.getDistrict_birth_other())
						.setInteger("state_birth", census_p.getState_birth())
						.setString("state_birth_other", census_p.getState_birth_other())
						.setInteger("country_birth", census_p.getCountry_birth())
						.setString("country_birth_other", census_p.getCountry_birth_other())
						.setInteger("nationality", census_p.getNationality())
						.setInteger("religion", census_p.getReligion())
						.setString("nationality_other", census_p.getNationality_other())
						.setString("religion_other", census_p.getReligion_other())
						.setInteger("marital_status", census_p.getMarital_status())
						.setInteger("blood_group", census_p.getBlood_group()).setInteger("height", census_p.getHeight())
						.setString("adhar_number", census_p.getAdhar_number())
						// .setString("border_area", census_p.getBorder_area())
						.setInteger("mother_tongue", census_p.getMother_tongue())
						.setString("mother_tongue_other", census_p.getMother_tongue_other())
						.setInteger("ncc_type", census_p.getNcc_type())
						.setInteger("com_institute", census_p.getCom_institute()).setInteger("id", census_id)
						.setInteger("com_bn_id", census_p.getCom_bn_id())
						.setInteger("com_company_id", census_p.getCom_company_id())
						.setInteger("training_institute", census_p.getTraining_institute())
						.setInteger("training_bn_id", census_p.getTraining_bn_id())
						.setInteger("training_company_id", census_p.getTraining_company_id())
						.setString("father_name", census_p.getFather_name())
						.setString("mother_name", census_p.getMother_name())
						.setInteger("org_country", census_p.getOrg_country())
						.setInteger("org_state", census_p.getOrg_state())
						.setInteger("org_district", census_p.getOrg_district())
						.setInteger("org_tehsil", census_p.getOrg_tehsil())
						.setString("org_domicile_country_other", census_p.getOrg_domicile_country_other())
						.setString("org_domicile_state_other", census_p.getOrg_domicile_state_other())
						.setString("org_domicile_district_other", census_p.getOrg_domicile_district_other())
						.setString("org_domicile_tehsil_other", census_p.getOrg_domicile_tehsil_other())
						.setInteger("status", 0).setInteger("com_platoon_id", census_p.getCom_platoon_id());
				msg = query.executeUpdate() > 0 ? "update" : "0";

			}

			int check_precadet = check_duplicate(comm_id, "TB_CENSUS_CADET");
			if (Integer.parseInt(pre_ch_id) == 0) {
				pre_cadet.setCensus_id(census_id);
				int id = (int) sessionHQL.save(pre_cadet);
				// pre_ch_id = Integer.toString(id);
			}

			else {

				String hql = "update TB_CENSUS_CADET set modified_by=:modified_by ,modified_date=:modified_date , pre_cadet_status=:pre_cadet_status, designation=:designation , "

						+ "employee_name=:employee_name,gazetted=:gazetted ,civil_service=:civil_service,from_date=:from_date,to_date=:to_date,army_no=:army_no"

						+ " ,total_service=:total_service ,unit_reg=:unit_reg ,competency=:competency,competency_other=:competency_other where  id=:id";

				Query query = sessionHQL.createQuery(hql).setInteger("pre_cadet_status", Integer.parseInt(cadet_status))

						.setString("designation", designation).setString("employee_name", emp_name)

						.setString("gazetted", isgazetted).setString("civil_service", isCivil)

						.setTimestamp("from_date", fr_dt).setTimestamp("to_date", fr_to).setString("army_no", army_no)

						.setString("total_service", total_rank).setString("unit_reg", unit_reg)

						.setInteger("id", Integer.parseInt(pre_ch_id))

						.setInteger("competency", Integer.parseInt(competency)).setString("modified_by", username)

						.setString("competency_other", competency_other).setTimestamp("modified_date", new Date());

				rvalue = query.executeUpdate() > 0 ? "update" : "0";
			}

			if (ncc_ch_id != null) {
				int check_ncc = check_duplicate(comm_id, "TB_CENSUS_NCC_EXP");

				if (check_ncc == 0) {

					TB_CENSUS_NCC_EXP ncc1 = new TB_CENSUS_NCC_EXP();

					ncc1.setOtu(otu);

					ncc1.setCen_id(census_id);

					ncc1.setComm_id(comm_id);

					ncc1.setCreated_by(username);

					ncc1.setCreated_on(date);

					ncc1.setStatus(0);

					int id = (int) sessionHQL.save(ncc1);

					ncc_ch_id = Integer.toString(id);

				} else {

					String hql_n = "update TB_CENSUS_NCC_EXP set modify_by=:modify_by ,modify_on=:modify_on , otu=:otu "

							+ " where  id=:id";

					Query query_n = sessionHQL.createQuery(hql_n).setString("otu", otu)

							.setInteger("id", Integer.parseInt(ncc_ch_id)).setString("modify_by", username)

							.setTimestamp("modify_on", new Date());

					rvalue = query_n.executeUpdate() > 0 ? "update" : "0";

				}

			}
			if (allergic_radio.equals("yes")) {

				for (int i = 1; i <= allergicOld_count; i++) {

					String medicine = request.getParameter("medicine" + i);

					String hql = "update TB_PSG_CENSUS_ALLERGICTOMEDICINE set modified_by=:modified_by ,modified_date=:modified_date , medicine=:medicine,status=:status where id=:c_id";

					Query query = sessionHQL.createQuery(hql).setString("medicine", medicine)

							.setInteger("c_id", Integer.parseInt(request.getParameter("allergic_ch_id" + i)))

							.setInteger("status", 0).setString("modified_by", username)

							.setTimestamp("modified_date", new Date());

					msg = query.executeUpdate() > 0 ? "1" : "Data not Updated";

					sessionHQL.flush();

					sessionHQL.clear();

				}

				diffrence = allergic_count - allergicOld_count;

				if (diffrence != 0) {

					TB_PSG_CENSUS_ALLERGICTOMEDICINE e = new TB_PSG_CENSUS_ALLERGICTOMEDICINE();

					for (int j = allergicOld_count + 1; j <= allergic_count; j++) {

						String medicine = request.getParameter("medicine" + j);

						e.setMedicine(medicine);

						e.setCen_id(census_id);

						e.setComm_id(comm_id);

						e.setCreated_by(username);

						e.setCreated_date(new Date());

						sessionHQL.save(e);

						sessionHQL.flush();

						sessionHQL.clear();

					}

				}

			} else {

				if (allergicOld_count != 0) {

					String hqlUpdate = "delete from TB_PSG_CENSUS_ALLERGICTOMEDICINE where cen_id=:id";

					int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", census_id).executeUpdate();

				}

			}

			String hql2 = "update TB_TRANS_PROPOSED_COMM_LETTER set " + "name=:name  where id=:comm_id";

			Query query2 = sessionHQL.createQuery(hql2).setString("name", comm1.getName()).setBigInteger("comm_id",

					comm_id);

			msg = query2.executeUpdate() > 0 ? "update" : "0";

			if (mad_classification == null || mad_classification.equals("0")) {

				tx.rollback();
				data.put("error", "Please Select The Medical Classification");
				return data;

			}

			if (medDtlFillIn3008 != 1) {

				if (checkbox_1bx != null && checkbox_1bx.equals("1BX")) {

					if ((_1BX_from_date == null || _1BX_from_date.trim().equals("")
							|| _1BX_from_date.equals("DD/MM/YYYY"))

							|| (_1BX_to_date == null || _1BX_to_date.trim().equals("")

									|| _1BX_to_date.equals("DD/MM/YYYY"))) {

						tx.rollback();
						data.put("error", "Please Enter From Date And To Date For Shape 1BX");
						return data;

					}
					if (_1BX_from_date != null || _1BX_from_date.trim().equals("")
							|| _1BX_from_date.equals("DD/MM/YYYY")) {
						if (!valid.isValidDate(_1BX_from_date)) {

							data.put("error", valid.isValidDateMSG + " of From ");
							return data;
						}
					}
					if (_1BX_to_date != null || _1BX_to_date.trim().equals("") || _1BX_to_date.equals("DD/MM/YYYY")) {
						if (!valid.isValidDate(_1BX_to_date)) {
							data.put("error", valid.isValidDateMSG + " of To ");
							return data;
						}
					}
					if (format.parse(_1BX_to_date).compareTo(format.parse(_1BX_from_date)) < 0) {
						data.put("error", "To Date Should Be Greater Than Or Equal To From Date For Shape 1BX");
						return data;
					}

					if (_1BX_diagnosis.trim().equals("") || _1BX_diagnosis == null) {

						tx.rollback();

						data.put("error", "Please  Enter The Diagnosis For Shape 1BX");
						return data;

					}

					if (!_1BX_diagnosis.trim().equals("")) {

						String[] arrOfStr = _1BX_diagnosis.split("-", 2);

						_1BX_diagnosis = arrOfStr[0];

					}

					String hqlUpdate = "delete from TB_CENSUS_MEDICAL_CATEGORY where cen_id=:id and status=0";

					int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", census_id).executeUpdate();

					String li[] = { "S", "H", "A", "P", "E", "C_C", "C_O", "C_P", "C_E" };

					String li_id[] = { "sShape_ch_id1", "hShape_ch_id1", "aShape_ch_id1", "pShape_ch_id1",
							"eShape_ch_id1",

							"cCope_ch_id1", "oCope_ch_id1", "pCope_ch_id1", "eCope_ch_id1" };

					for (int i = 0; i < li.length; i++) {

						String ch_id = request.getParameter(li_id[i]);

						TB_CENSUS_MEDICAL_CATEGORY Mad_cat;

						Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

						Mad_cat.setShape(li[i]);

						if (i == 5 || i == 8) {

							Mad_cat.setOther("");

						}

						if (i < 5) {

							Mad_cat.setShape_status(1);

							Mad_cat.setShape_value("1A");

						} else

							Mad_cat.setShape_value("0");

						if ((_1BX_from_date != null && !_1BX_from_date.trim().equals(""))

								&& (_1BX_to_date != null && !_1BX_to_date.trim().equals(""))) {

							Mad_cat.setFrom_date_1bx(format.parse(_1BX_from_date));

							Mad_cat.setTo_date_1bx(format.parse(_1BX_to_date));

						}

						Mad_cat.setCen_id(census_id);

						Mad_cat.setComm_id(comm_id);

						Mad_cat.setDiagnosis_1bx(_1BX_diagnosis);

						Mad_cat.setClasification("NIL");

						Mad_cat.setCreated_by(username);

						Mad_cat.setCreated_on(date);

						sessionHQL.save(Mad_cat);

					}

					sessionHQL.flush();

					sessionHQL.clear();

				} else {

					////////////////////////////// S SHAPE ////////////////////////////

					for (int i = 1; i <= sShapeOld_count; i++) {
						String shape_status = request.getParameter("s_status" + i);
						String shape_value = request.getParameter("sShape_value" + i);
						String from_date = request.getParameter("s_from_date" + i);
						String to_date = request.getParameter("s_to_date" + i);
						String diagnosis_1 = request.getParameter("_diagnosis_code1" + i);
						String shape_ch = request.getParameter("sShape_ch_id" + i);
						if (!diagnosis_1.trim().equals("")) {
							String[] arrOfStr = diagnosis_1.split("-", 2);
							diagnosis_1 = arrOfStr[0];
						}

						if (shape_status == null || shape_status.equals("0")) {
							tx.rollback();
							data.put("error", "Please Select The S-Shape Status " + i + " Row");
							return data;

						}

						if (shape_value == null || shape_value.equals("0")) {

							tx.rollback();

							data.put("error", "Please Select The S-Shape Value " + i + " Row");

							return data;

						}					
						if (!shape_status.equals("1")) {

							if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

								tx.rollback();

								data.put("error", "Please Enter The S-Shape From Date " + i + " Row");

								return data;

							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									data.put("error", valid.isValidDateMSG + "  of The S-Shape From ");

									return data;
								}
							}

							if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

								tx.rollback();

								data.put("error", "Please Enter The S-Shape To Date " + i + " Row");

								return data;

							}
							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									data.put("error", valid.isValidDateMSG + " of The S-Shape To ");

									return data;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

								data.put("error", "S-Shape To Date Should Be Greater Than Or Equal To From Date In Row "
										+ i + "");

								return data;

							}

						}
						if (i != 1) {

							for (int k = 1; k < i; k++) {
								String shape_status_pre = request.getParameter("s_status" + k);
								String shape_value_pre = request.getParameter("sShape_value" + k);
								String from_date_pre = request.getParameter("s_from_date" + k);
								String to_date_pre = request.getParameter("s_to_date" + k);
								String diagnosis_1_pre = request.getParameter("_diagnosis_code1" + k);
								
								if (!diagnosis_1_pre.trim().equals("")) {
									String[] arrOfStr = diagnosis_1_pre.split("-", 2);
									diagnosis_1_pre = arrOfStr[0];

								}
								if (shape_status.equals("1")) {
									if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
											&& from_date_pre.equals(from_date) && diagnosis_1_pre.equals(diagnosis_1)) {
										tx.rollback();
										data.put("error", " Data Already Exists S-Shape  " + i + "th row");
										return data;
									}
								} else {

									if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
											&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_1_pre.equals(diagnosis_1)) {
										tx.rollback();
										data.put("error", " Data Already Exists S-Shape  " + i + "th row");
										return data;
									}
								}
							}

						}
						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

								.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

						Mad_cat.setShape_status(Integer.parseInt(shape_status));

						Mad_cat.setShape_value(shape_value);

//							if (!shape_status.equals("1")
						//
//									|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
						//
//									|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

							Mad_cat.setFrom_date(format.parse(from_date));

							Mad_cat.setTo_date(format.parse(to_date));

						} else if (from_date != null && !from_date.trim().equals("")
								&& !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}

						Mad_cat.setFrom_date_1bx(null);

						Mad_cat.setTo_date_1bx(null);

						Mad_cat.setDiagnosis_1bx(null);

						Mad_cat.setDiagnosis(diagnosis_1);

						Mad_cat.setClasification(mad_classification);

						Mad_cat.setModify_by(username);

						Mad_cat.setModify_on(date);

						Mad_cat.setStatus(0);

						sessionHQL.update(Mad_cat);

						sessionHQL.flush();

						sessionHQL.clear();

					}

					// Logic for INSERT

					diffrence = sShape_count - sShapeOld_count;

					if (diffrence != 0) {

						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

						for (int i = sShapeOld_count + 1; i <= sShape_count; i++) {
							String shape_status = request.getParameter("s_status" + i);
							String shape_value = request.getParameter("sShape_value" + i);
							String from_date = request.getParameter("s_from_date" + i);
							String to_date = request.getParameter("s_to_date" + i);
							String diagnosis_1 = request.getParameter("_diagnosis_code1" + i);

							if (!diagnosis_1.trim().equals("")) {
								String[] arrOfStr = diagnosis_1.split("-", 2);
								diagnosis_1 = arrOfStr[0];

							}

							if (shape_status == null || shape_status.equals("0")) {
								tx.rollback();
								data.put("error", "Please Select The S-Shape Status " + i + " Row");
								return data;

							}

							if (shape_value == null || shape_value.equals("0")) {
								tx.rollback();
								data.put("error", "Please Select The S-Shape Value " + i + " Row");
								return data;

							}
							
							if (!shape_status.equals("1")) {

								if (from_date == null || from_date.trim().equals("")
										|| from_date.equals("DD/MM/YYYY")) {

									tx.rollback();
									data.put("error", "Please Enter The S-Shape From Date " + i + " Row");
									return data;

								}
								if (from_date != null || from_date.trim().equals("")
										|| from_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(from_date)) {
										data.put("error", valid.isValidDateMSG + "  of The S-Shape From ");
										return data;
									}
								}
								if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
									tx.rollback();
									data.put("error", "Please Enter The S-Shape To Date " + i + " Row");
									return data;

								}
								if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(to_date)) {
										data.put("error", valid.isValidDateMSG + " of The S-Shape To ");
										return data;
									}
								}
								if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

									data.put("error",
											"S-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i
													+ "");
									return data;
								}

							}
							if (i != 1) {
								for (int k = 1; k < i; k++) {
									String shape_status_pre = request.getParameter("s_status" + k);
									String shape_value_pre = request.getParameter("sShape_value" + k);
									String from_date_pre = request.getParameter("s_from_date" + k);
									String to_date_pre = request.getParameter("s_to_date" + k);
									String diagnosis_1_pre = request.getParameter("_diagnosis_code1" + k);
									if (!diagnosis_1_pre.trim().equals("")) {
										String[] arrOfStr = diagnosis_1_pre.split("-", 2);
										diagnosis_1_pre = arrOfStr[0];

									}

									
									if (!shape_status.equals("1")) {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date)
												&& diagnosis_1_pre.equals(diagnosis_1)) {
											tx.rollback();
											data.put("error", " Data Already Exists S-Shape  " + i + " th row");
											return data;

										}
									} else {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && diagnosis_1_pre.equals(diagnosis_1)) {
											tx.rollback();
											data.put("error", " Data Already Exists S-Shape  " + i + " th row");
											return data;
										}
									}
								}
							}
							Mad_cat.setShape("S");
							Mad_cat.setShape_status(Integer.parseInt(shape_status));

							Mad_cat.setShape_value(shape_value);
							if (!shape_status.equals("1")) {

								Mad_cat.setFrom_date(format.parse(from_date));

								Mad_cat.setTo_date(format.parse(to_date));

							} else if (from_date != null && !from_date.trim().equals("")
									&& !from_date.equals("DD/MM/YYYY")) {
								Mad_cat.setFrom_date(format.parse(from_date));
							}

							Mad_cat.setDiagnosis(diagnosis_1);
							Mad_cat.setClasification(mad_classification);
							Mad_cat.setCen_id(census_id);
							Mad_cat.setComm_id(comm_id);
							Mad_cat.setCreated_by(username);
							Mad_cat.setCreated_on(date);
							Mad_cat.setStatus(0);
							int check_s = check_shape(comm_id_str, "S");
							if (check_s == 0) {
								sessionHQL.save(Mad_cat);
								sessionHQL.flush();
								sessionHQL.clear();
							} else {
								tx.rollback();
								data.put("error", "Data Already Exists");
								return data;

							}

						}
					}

					////////////////////////////// H SHAPE ////////////////////////////

					for (int i = 1; i <= hShapeOld_count; i++) {
						String shape_status = request.getParameter("h_status" + i);
						String shape_value = request.getParameter("hShape_value" + i);
						String from_date = request.getParameter("h_from_date" + i);
						String to_date = request.getParameter("h_to_date" + i);
						String diagnosis_2 = request.getParameter("_diagnosis_code2" + i);
						String shape_ch = request.getParameter("hShape_ch_id" + i);
						if (!diagnosis_2.trim().equals("")) {
							String[] arrOfStr = diagnosis_2.split("-", 2);
							diagnosis_2 = arrOfStr[0];
						}

						if (shape_status == null || shape_status.equals("0")) {
							tx.rollback();
							data.put("error", "Please Select The H-Shape Status " + i + " Row");
							return data;
						}

						if (shape_value == null || shape_value.equals("0")) {
							tx.rollback();
							data.put("error", "Please Select The H-Shape Value " + i + " Row");
							return data;

						}
						
						if (!shape_status.equals("1")) {
							if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								tx.rollback();
								data.put("error", "Please Enter The H-Shape From Date " + i + " Row");
								return data;
							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									data.put("error", valid.isValidDateMSG + " of The H-Shape From ");

									return data;
								}
							}
							if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

								tx.rollback();

								data.put("error", "Please Enter The H-Shape To Date " + i + " Row");

								return data;

							}

							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									data.put("error", valid.isValidDateMSG + " of The H-Shape To ");

									return data;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

								data.put("error", "H-Shape To Date Should Be Greater Than Or Equal To From Date In Row "
										+ i + "");

								return data;

							}

						}
						if (i != 1) {
							for (int k = 1; k < i; k++) {
								String shape_status_pre = request.getParameter("h_status" + k);
								String shape_value_pre = request.getParameter("hShape_value" + k);
								String from_date_pre = request.getParameter("h_from_date" + k);
								String to_date_pre = request.getParameter("h_to_date" + k);
								String diagnosis_2_pre = request.getParameter("_diagnosis_code2" + k);
								if (!diagnosis_2_pre.trim().equals("")) {
									String[] arrOfStr = diagnosis_2_pre.split("-", 2);
									diagnosis_2_pre = arrOfStr[0];

								}

								if (!shape_status.equals("1")) {
									if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
											&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_2_pre.equals(diagnosis_2)) {
										tx.rollback();
										data.put("error", " Data Already Exists H-Shape  " + i + "th row");
										return data;
									}
								} else {
									if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
											&& from_date_pre.equals(from_date) && diagnosis_2_pre.equals(diagnosis_2)) {
										tx.rollback();
										data.put("error", " Data Already Exists H-Shape  " + i + "th row");
										return data;
									}
								}
							}
						}
						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

								.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

						Mad_cat.setShape_status(Integer.parseInt(shape_status));

						Mad_cat.setShape_value(shape_value);
						if (!shape_status.equals("1")) {

							Mad_cat.setFrom_date(format.parse(from_date));

							Mad_cat.setTo_date(format.parse(to_date));

						} else if (from_date != null && !from_date.trim().equals("")
								&& !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}

						Mad_cat.setFrom_date_1bx(null);
						Mad_cat.setTo_date_1bx(null);
						Mad_cat.setDiagnosis_1bx(null);
						Mad_cat.setDiagnosis(diagnosis_2);
						Mad_cat.setClasification(mad_classification);
						Mad_cat.setModify_by(username);
						Mad_cat.setModify_on(date);
						Mad_cat.setStatus(0);
						sessionHQL.update(Mad_cat);
						sessionHQL.flush();
						sessionHQL.clear();
					}

					// Logic for INSERT

					diffrence = hShape_count - hShapeOld_count;

					if (diffrence != 0) {

						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

						for (int i = hShapeOld_count + 1; i <= hShape_count; i++) {

							String shape_status = request.getParameter("h_status" + i);

							String shape_value = request.getParameter("hShape_value" + i);

							String from_date = request.getParameter("h_from_date" + i);

							String to_date = request.getParameter("h_to_date" + i);

							String diagnosis_2 = request.getParameter("_diagnosis_code2" + i);

							if (!diagnosis_2.trim().equals("")) {

								String[] arrOfStr = diagnosis_2.split("-", 2);

								diagnosis_2 = arrOfStr[0];

							}

							if (shape_status == null || shape_status.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The H-Shape Status " + i + " Row");

								return data;

							}

							if (shape_value == null || shape_value.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The H-Shape Value " + i + " Row");

								return data;

							}
//							
							if (!shape_status.equals("1")) {

								if (from_date == null || from_date.trim().equals("")
										|| from_date.equals("DD/MM/YYYY")) {
									tx.rollback();
									data.put("error", "Please Enter The H-Shape From Date " + i + " Row");
									return data;
								}
								if (from_date != null || from_date.trim().equals("")
										|| from_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(from_date)) {
										data.put("error", valid.isValidDateMSG + " of The H-Shape From ");
										return data;
									}
								}

								if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
									tx.rollback();
									data.put("error", "Please Enter The H-Shape To Date " + i + " Row");
									return data;
								}
								if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(to_date)) {
										data.put("error", valid.isValidDateMSG + " of The H-Shape To ");

										return data;
									}
								}
								if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

									data.put("error",
											"H-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i
													+ "");

									return data;

								}

							}
							if (i != 1) {
								for (int k = 1; k < i; k++) {
									String shape_status_pre = request.getParameter("h_status" + k);
									String shape_value_pre = request.getParameter("hShape_value" + k);
									String from_date_pre = request.getParameter("h_from_date" + k);
									String to_date_pre = request.getParameter("h_to_date" + k);
									String diagnosis_2_pre = request.getParameter("_diagnosis_code2" + k);
									if (!diagnosis_2_pre.trim().equals("")) {
										String[] arrOfStr = diagnosis_2_pre.split("-", 2);
										diagnosis_2_pre = arrOfStr[0];

									}									
									if (!shape_status.equals("1")) {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_2_pre.equals(diagnosis_2)) {
											tx.rollback();
											data.put("error", " Data Already Exists H-Shape  " + i + "th row");
											return data;
										}
									} else {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && diagnosis_2_pre.equals(diagnosis_2)) {
											tx.rollback();
											data.put("error", " Data Already Exists H-Shape  " + i + "th row");
											return data;
										}
									}
								}
							}
							Mad_cat.setShape("H");
							Mad_cat.setShape_status(Integer.parseInt(shape_status));
							Mad_cat.setShape_value(shape_value);
							if (!shape_status.equals("1")) {
								Mad_cat.setFrom_date(format.parse(from_date));
								Mad_cat.setTo_date(format.parse(to_date));
							} else if (from_date != null && !from_date.trim().equals("")
									&& !from_date.equals("DD/MM/YYYY")) {
								Mad_cat.setFrom_date(format.parse(from_date));
							}
							Mad_cat.setDiagnosis(diagnosis_2);
							Mad_cat.setClasification(mad_classification);
							Mad_cat.setCen_id(census_id);
							Mad_cat.setComm_id(comm_id);
							Mad_cat.setCreated_by(username);
							Mad_cat.setCreated_on(date);
							Mad_cat.setStatus(0);

							int check_s = check_shape(comm_id_str, "H");
							if (check_s == 0) {
								sessionHQL.save(Mad_cat);
								sessionHQL.flush();
								sessionHQL.clear();
							} else {
								tx.rollback();

								data.put("error", "Data Already Exists");

								return data;
							}

						}

					}

					// //////////////////////////////A SHAPE ////////////////////////////

					for (int i = 1; i <= aShapeOld_count; i++) {

						String shape_status = request.getParameter("a_status" + i);

						String shape_value = request.getParameter("aShape_value" + i);

						String from_date = request.getParameter("a_from_date" + i);

						String to_date = request.getParameter("a_to_date" + i);

						String diagnosis_3 = request.getParameter("_diagnosis_code3" + i);

						String shape_ch = request.getParameter("aShape_ch_id" + i);

						if (!diagnosis_3.trim().equals("")) {

							String[] arrOfStr = diagnosis_3.split("-", 2);

							diagnosis_3 = arrOfStr[0];

						}

						if (shape_status == null || shape_status.equals("0")) {

							tx.rollback();

							data.put("error", "Please Select The A-Shape Status " + i + " Row");

							return data;

						}

						if (shape_value == null || shape_value.equals("0")) {

							tx.rollback();

							data.put("error", "Please Select The A-Shape Value " + i + " Row");

							return data;

						}

						if (!shape_status.equals("1")) {

							if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

								tx.rollback();

								data.put("error", "Please Enter The A-Shape From Date " + i + " Row");

								return data;

							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									data.put("error", valid.isValidDateMSG + " of The A-Shape From ");

									return data;
								}
							}

							if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

								tx.rollback();

								data.put("error", "Please Enter The A-Shape To Date " + i + " Row");

								return data;

							}
							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									data.put("error", valid.isValidDateMSG + " of The A-Shape To ");

									return data;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

								data.put("error", "A-Shape To Date Should Be Greater Than Or Equal To From Date In Row "
										+ i + "");

								return data;

							}

						}
						if (i != 1) {
							for (int k = 1; k < i; k++) {
								String shape_status_pre = request.getParameter("a_status" + k);
								String shape_value_pre = request.getParameter("aShape_value" + k);
								String from_date_pre = request.getParameter("a_from_date" + k);
								String to_date_pre = request.getParameter("a_to_date" + k);
								String diagnosis_3_pre = request.getParameter("_diagnosis_code3" + k);
								if (!diagnosis_3_pre.trim().equals("")) {
									String[] arrOfStr = diagnosis_3_pre.split("-", 2);
									diagnosis_3_pre = arrOfStr[0];

								}

								if (shape_status.equals("1")) {
									if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
											&& from_date_pre.equals(from_date) && diagnosis_3_pre.equals(diagnosis_3)) {
										tx.rollback();
										data.put("error", " Data Already Exists A-Shape  " + i + "th row");
										return data;
									}
								} else {
									if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
											&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) &&diagnosis_3_pre.equals(diagnosis_3)) {
										tx.rollback();
										data.put("error", " Data Already Exists A-Shape  " + i + "th row");
										return data;
									}
								}

							}
						}
						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

								.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

						Mad_cat.setShape_status(Integer.parseInt(shape_status));

						Mad_cat.setShape_value(shape_value);

//							if (!shape_status.equals("1") || (from_date != null && !from_date.trim().equals(""))
						//
//									|| (to_date != null && !to_date.trim().equals(""))) {

						if (!shape_status.equals("1")) {
							Mad_cat.setFrom_date(format.parse(from_date));

							Mad_cat.setTo_date(format.parse(to_date));

						} else if (from_date != null && !from_date.trim().equals("")
								&& !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}

						Mad_cat.setFrom_date_1bx(null);
						Mad_cat.setTo_date_1bx(null);
						Mad_cat.setDiagnosis_1bx(null);
						Mad_cat.setDiagnosis(diagnosis_3);
						Mad_cat.setClasification(mad_classification);
						Mad_cat.setModify_by(username);
						Mad_cat.setModify_on(date);
						Mad_cat.setStatus(0);
						sessionHQL.update(Mad_cat);
						sessionHQL.flush();
						sessionHQL.clear();
					}

					// Logic for INSERT

					diffrence = aShape_count - aShapeOld_count;
					if (diffrence != 0) {
						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();
						for (int i = aShapeOld_count + 1; i <= aShape_count; i++) {
							String shape_status = request.getParameter("a_status" + i);
							String shape_value = request.getParameter("aShape_value" + i);
							String from_date = request.getParameter("a_from_date" + i);
							String to_date = request.getParameter("a_to_date" + i);
							String diagnosis_3 = request.getParameter("_diagnosis_code3" + i);
							if (!diagnosis_3.trim().equals("")) {
								String[] arrOfStr = diagnosis_3.split("-", 2);
								diagnosis_3 = arrOfStr[0];
							}
							if (shape_status == null || shape_status.equals("0")) {
								tx.rollback();
								data.put("error", "Please Select The A-Shape Status " + i + " Row");
								return data;
							}
							if (shape_value == null || shape_value.equals("0")) {
								tx.rollback();
								data.put("error", "Please Select The A-Shape Value " + i + " Row");
								return data;
							}
//								if (!shape_status.equals("1")
//										|| (from_date != null && !from_date.trim().equals("")
//												&& !from_date.equals("DD/MM/YYYY"))
//										|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
							if (!shape_status.equals("1")) {
								if (from_date == null || from_date.trim().equals("")
										|| from_date.equals("DD/MM/YYYY")) {
									tx.rollback();
									data.put("error", "Please Enter The A-Shape From Date " + i + " Row");
									return data;
								}
								if (from_date != null || from_date.trim().equals("")
										|| from_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(from_date)) {
										data.put("error", valid.isValidDateMSG + " of The A-Shape From ");

										return data;
									}
								}
								if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(to_date)) {
										data.put("error", valid.isValidDateMSG + " of The A-Shape To ");

										return data;
									}
								}
								if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The A-Shape To Date " + i + " Row");

									return data;

								}

								if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(to_date)) {
										data.put("error", valid.isValidDateMSG + " of The A-Shape To ");

										return data;
									}
								}
								if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

									data.put("error",
											"A-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i
													+ "");

									return data;

								}

							}
							if (i != 1) {
								for (int k = 1; k < i; k++) {
									String shape_status_pre = request.getParameter("a_status" + k);
									String shape_value_pre = request.getParameter("aShape_value" + k);
									String from_date_pre = request.getParameter("a_from_date" + k);
									String to_date_pre = request.getParameter("a_to_date" + k);
									String diagnosis_3_pre = request.getParameter("_diagnosis_code3" + k);
									if (!diagnosis_3_pre.trim().equals("")) {
										String[] arrOfStr = diagnosis_3_pre.split("-", 2);
										diagnosis_3_pre = arrOfStr[0];

									}

									if (shape_status.equals("1")) {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && diagnosis_3_pre.equals(diagnosis_3)) {
											tx.rollback();
											data.put("error", " Data Already Exists A-Shape  " + i + "th row");
											return data;
										}
									} else {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_3_pre.equals(diagnosis_3)) {
											tx.rollback();
											data.put("error", " Data Already Exists A-Shape  " + i + "th row");
											return data;
										}
									}

								}
							}
							Mad_cat.setShape("A");

							Mad_cat.setShape_status(Integer.parseInt(shape_status));

							Mad_cat.setShape_value(shape_value);

//								if (!shape_status.equals("1")
							//
//										|| (from_date != null && !from_date.trim().equals("")
							//
//												&& !from_date.equals("DD/MM/YYYY"))
							//
//										|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
							if (!shape_status.equals("1")) {

								Mad_cat.setFrom_date(format.parse(from_date));

								Mad_cat.setTo_date(format.parse(to_date));

							} else if (from_date != null && !from_date.trim().equals("")
									&& !from_date.equals("DD/MM/YYYY")) {
								Mad_cat.setFrom_date(format.parse(from_date));
							}

							Mad_cat.setDiagnosis(diagnosis_3);

							Mad_cat.setClasification(mad_classification);

							Mad_cat.setCen_id(census_id);

							Mad_cat.setComm_id(comm_id);

							Mad_cat.setCreated_by(username);

							Mad_cat.setCreated_on(date);

							Mad_cat.setStatus(0);

							int check_s = check_shape(comm_id_str, "A");
							if (check_s == 0) {
								sessionHQL.save(Mad_cat);
								sessionHQL.flush();
								sessionHQL.clear();
							} else {
								tx.rollback();

								data.put("error", "Data Already Exists");

								return data;
							}
//								sessionHQL.save(Mad_cat);
							//
//								sessionHQL.flush();
							//
//								sessionHQL.clear();

						}

					}

					// //////////////////////////////P SHAPE ////////////////////////////

					for (int i = 1; i <= pShapeOld_count; i++) {

						String shape_status = request.getParameter("p_status" + i);

						String shape_value = request.getParameter("pShape_value" + i);

						String from_date = request.getParameter("p_from_date" + i);

						String to_date = request.getParameter("p_to_date" + i);

						String diagnosis_4 = request.getParameter("_diagnosis_code4" + i);

						String shape_ch = request.getParameter("pShape_ch_id" + i);

						if (!diagnosis_4.trim().equals("")) {

							String[] arrOfStr = diagnosis_4.split("-", 2);

							diagnosis_4 = arrOfStr[0];

						}

						if (shape_status == null || shape_status.equals("0")) {

							tx.rollback();

							data.put("error", "Please Select The P-Shape Status " + i + " Row");

							return data;

						}

						if (shape_value == null || shape_value.equals("0")) {

							tx.rollback();

							data.put("error", "Please Select The P-Shape Value " + i + " Row");

							return data;

						}

//							if (!shape_status.equals("1")
						//
//									|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
						//
//									|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

							if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

								tx.rollback();

								data.put("error", "Please Enter The P-Shape From Date " + i + " Row");

								return data;

							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									data.put("error", valid.isValidDateMSG + " of The P-Shape From ");

									return data;
								}
							}

							if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

								tx.rollback();

								data.put("error", "Please Enter The P-Shape To Date " + i + " Row");

								return data;

							}

							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									data.put("error", valid.isValidDateMSG + " of The P-Shape To ");

									return data;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

								data.put("error", "P-Shape To Date Should Be Greater Than Or Equal To From Date In Row "
										+ i + "");

								return data;

							}

						}
						if (i != 1) {

							for (int k = 1; k < i; k++) {
								String shape_status_pre = request.getParameter("p_status" + k);
								String shape_value_pre = request.getParameter("pShape_value" + k);
								String from_date_pre = request.getParameter("p_from_date" + k);
								String to_date_pre = request.getParameter("p_to_date" + k);
								String diagnosis_4_pre = request.getParameter("_diagnosis_code4" + k);
								if (!diagnosis_4_pre.trim().equals("")) {
									String[] arrOfStr = diagnosis_4_pre.split("-", 2);
									diagnosis_4_pre = arrOfStr[0];

								}

								if (!shape_status.equals("1")) {
									if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
											&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_4_pre.equals(diagnosis_4)

									) {
										tx.rollback();
										data.put("error", " Data Already Exists P-Shape  " + i + "th row");
										return data;
									}
								} else {
									if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
											&& from_date_pre.equals(from_date) && diagnosis_4_pre.equals(diagnosis_4)

									) {
										tx.rollback();
										data.put("error", " Data Already Exists P-Shape  " + i + "th row");
										return data;
									}
								}
							}
						}
						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

								.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

						Mad_cat.setShape_status(Integer.parseInt(shape_status));

						Mad_cat.setShape_value(shape_value);

//							if (!shape_status.equals("1")
						//
//									|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
						//
//									|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

							Mad_cat.setFrom_date(format.parse(from_date));

							Mad_cat.setTo_date(format.parse(to_date));

						} else if (from_date != null && !from_date.trim().equals("")
								&& !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}

						Mad_cat.setFrom_date_1bx(null);

						Mad_cat.setTo_date_1bx(null);

						Mad_cat.setDiagnosis_1bx(null);

						Mad_cat.setDiagnosis(diagnosis_4);

						Mad_cat.setClasification(mad_classification);

						Mad_cat.setModify_by(username);

						Mad_cat.setModify_on(date);

						Mad_cat.setStatus(0);

						sessionHQL.update(Mad_cat);

						sessionHQL.flush();

						sessionHQL.clear();

					}

					diffrence = pShape_count - pShapeOld_count;

					if (diffrence != 0) {

						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

						for (int i = pShapeOld_count + 1; i <= pShape_count; i++) {

							String shape_status = request.getParameter("p_status" + i);

							String shape_value = request.getParameter("pShape_value" + i);

							String from_date = request.getParameter("p_from_date" + i);

							String to_date = request.getParameter("p_to_date" + i);

							String diagnosis_4 = request.getParameter("_diagnosis_code4" + i);

							if (!diagnosis_4.trim().equals("")) {

								String[] arrOfStr = diagnosis_4.split("-", 2);

								diagnosis_4 = arrOfStr[0];

							}

							if (shape_status == null || shape_status.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The P-Shape Status " + i + " Row");

								return data;

							}

							if (shape_value == null || shape_value.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The P-Shape Value " + i + " Row");

								return data;

							}

//								if (!shape_status.equals("1") || (from_date != null && !from_date.trim().equals("")
							//
//										&& !from_date.equals("DD/MM/YYYY"))
							//
//										|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
							if (!shape_status.equals("1")) {

								if (from_date == null || from_date.trim().equals("")
										|| from_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The P-Shape From Date " + i + " Row");

									return data;

								}
								if (from_date != null || from_date.trim().equals("")
										|| from_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(from_date)) {
										data.put("error", valid.isValidDateMSG + " of The P-Shape From ");

										return data;
									}
								}

								if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The P-Shape To Date " + i + " Row");

									return data;

								}

								if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(to_date)) {
										data.put("error", valid.isValidDateMSG + " of The P-Shape To ");

										return data;
									}
								}
								if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

									data.put("error",
											"P-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i
													+ "");

									return data;

								}

							}
							if (i != 1) {

								for (int k = 1; k < i; k++) {
									String shape_status_pre = request.getParameter("p_status" + k);
									String shape_value_pre = request.getParameter("pShape_value" + k);
									String from_date_pre = request.getParameter("p_from_date" + k);
									String to_date_pre = request.getParameter("p_to_date" + k);
									String diagnosis_4_pre = request.getParameter("_diagnosis_code4" + k);
									if (!diagnosis_4_pre.trim().equals("")) {
										String[] arrOfStr = diagnosis_4_pre.split("-", 2);
										diagnosis_4_pre = arrOfStr[0];

									}

									if (!shape_status.equals("1")) {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_4_pre.equals(diagnosis_4)

										) {
											tx.rollback();
											data.put("error", " Data Already Exists P-Shape  " + i + "th row");
											return data;
										}
									} else {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && diagnosis_4_pre.equals(diagnosis_4)

										) {
											tx.rollback();
											data.put("error", " Data Already Exists P-Shape  " + i + "th row");
											return data;
										}
									}
								}
							}
							Mad_cat.setShape("P");

							Mad_cat.setShape_status(Integer.parseInt(shape_status));

							Mad_cat.setShape_value(shape_value);

//								if (!shape_status.equals("1")
							//
//										|| (from_date != null && !from_date.trim().equals("")
							//
//												&& !from_date.equals("DD/MM/YYYY"))
							//
//										|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
							if (!shape_status.equals("1")) {

								Mad_cat.setFrom_date(format.parse(from_date));

								Mad_cat.setTo_date(format.parse(to_date));

							} else if (from_date != null && !from_date.trim().equals("")
									&& !from_date.equals("DD/MM/YYYY")) {
								Mad_cat.setFrom_date(format.parse(from_date));
							}

							Mad_cat.setDiagnosis(diagnosis_4);

							Mad_cat.setClasification(mad_classification);

							Mad_cat.setCen_id(census_id);

							Mad_cat.setComm_id(comm_id);

							Mad_cat.setCreated_by(username);

							Mad_cat.setCreated_on(date);

							Mad_cat.setStatus(0);
							int check_s = check_shape(comm_id_str, "P");
							if (check_s == 0) {
								sessionHQL.save(Mad_cat);
								sessionHQL.flush();
								sessionHQL.clear();
							} else {
								tx.rollback();

								data.put("error", "Data Already Exists");

								return data;
							}

						}

					}

					// //////////////////////////////E SHAPE ////////////////////////////

					for (int i = 1; i <= eShapeOld_count; i++) {

						String shape_status = request.getParameter("e_status" + i);

						String shape_value = request.getParameter("eShape_value" + i);

						String from_date = request.getParameter("e_from_date" + i);

						String to_date = request.getParameter("e_to_date" + i);

						String diagnosis_5 = request.getParameter("_diagnosis_code5" + i);

						String shape_ch = request.getParameter("eShape_ch_id" + i);

						if (!diagnosis_5.trim().equals("")) {

							String[] arrOfStr = diagnosis_5.split("-", 2);

							diagnosis_5 = arrOfStr[0];

						}

						if (shape_status == null || shape_status.equals("0")) {

							tx.rollback();

							data.put("error", "Please Select The E-Shape Status " + i + " Row");

							return data;

						}

						if (shape_value == null || shape_value.equals("0")) {

							tx.rollback();

							data.put("error", "Please Select The E-Shape Value " + i + " Row");

							return data;

						}

//							if (!shape_status.equals("1")
						//
//									|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
						//
//									|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

							if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

								tx.rollback();

								data.put("error", "Please Enter The E-Shape From Date " + i + " Row");

								return data;

							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									data.put("error", valid.isValidDateMSG + " of The E-Shape From ");

									return data;
								}
							}

							if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

								tx.rollback();

								data.put("error", "Please Enter The E-Shape To Date " + i + " Row");

								return data;

							}

							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									data.put("error", valid.isValidDateMSG + " of The E-Shape To ");

									return data;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

								data.put("error", "E-Shape To Date Should Be Greater Than Or Equal To From Date In Row "
										+ i + "");

								return data;

							}

						}
						if (i != 1) {

							for (int k = 1; k < i; k++) {
								String shape_status_pre = request.getParameter("e_status" + k);
								String shape_value_pre = request.getParameter("eShape_value" + k);
								String from_date_pre = request.getParameter("e_from_date" + k);
								String to_date_pre = request.getParameter("e_to_date" + k);
								String diagnosis_5_pre = request.getParameter("_diagnosis_code5" + k);
								if (!diagnosis_5_pre.trim().equals("")) {
									String[] arrOfStr = diagnosis_5_pre.split("-", 2);
									diagnosis_5_pre = arrOfStr[0];

								}

								if (!shape_status.equals("1")) {
									if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
											&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_5_pre.equals(diagnosis_5)) {
										tx.rollback();
										data.put("error", " Data Already Exists E-Shape  " + i + "th row");
										return data;
									}
								} else {
									if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
											&& from_date_pre.equals(from_date) && diagnosis_5_pre.equals(diagnosis_5)) {
										tx.rollback();
										data.put("error", " Data Already Exists E-Shape  " + i + "th row");
										return data;
									}
								}

							}
						}
						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

								.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

						Mad_cat.setShape_status(Integer.parseInt(shape_status));

						Mad_cat.setShape_value(shape_value);

//							if (!shape_status.equals("1")
						//
//									|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
						//
//									|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

							Mad_cat.setFrom_date(format.parse(from_date));

							Mad_cat.setTo_date(format.parse(to_date));

						} else if (from_date != null && !from_date.trim().equals("")
								&& !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}

						Mad_cat.setFrom_date_1bx(null);
						Mad_cat.setTo_date_1bx(null);
						Mad_cat.setDiagnosis_1bx(null);
						Mad_cat.setDiagnosis(diagnosis_5);
						Mad_cat.setClasification(mad_classification);
						Mad_cat.setModify_by(username);
						Mad_cat.setModify_on(date);
						Mad_cat.setStatus(0);

						sessionHQL.update(Mad_cat);

						sessionHQL.flush();

						sessionHQL.clear();

					}

					diffrence = eShape_count - eShapeOld_count;

					if (diffrence != 0) {

						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

						for (int i = eShapeOld_count + 1; i <= eShape_count; i++) {

							String shape_status = request.getParameter("e_status" + i);

							String shape_value = request.getParameter("eShape_value" + i);

							String from_date = request.getParameter("e_from_date" + i);

							String to_date = request.getParameter("e_to_date" + i);

							String diagnosis_5 = request.getParameter("_diagnosis_code5" + i);

							if (!diagnosis_5.trim().equals("")) {

								String[] arrOfStr = diagnosis_5.split("-", 2);

								diagnosis_5 = arrOfStr[0];

							}

							if (shape_status == null || shape_status.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The E-Shape Status " + i + " Row");

								return data;

							}

							if (shape_value == null || shape_value.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The E-Shape Value " + i + " Row");

								return data;

							}

//								if (!shape_status.equals("1")
							//
//										|| (from_date != null && !from_date.trim().equals("")
							//
//												&& !from_date.equals("DD/MM/YYYY"))
							//
//										|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
							if (!shape_status.equals("1")) {

								if (from_date == null || from_date.trim().equals("")
										|| from_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The E-Shape From Date " + i + " Row");

									return data;

								}
								if (from_date != null || from_date.trim().equals("")
										|| from_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(from_date)) {
										data.put("error", valid.isValidDateMSG + " of The E-Shape From ");

										return data;
									}
								}

								if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The E-Shape To Date " + i + " Row");

									return data;

								}

								if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(to_date)) {
										data.put("error", valid.isValidDateMSG + " of The E-Shape To ");

										return data;
									}
								}
								if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

									data.put("error",
											"E-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i
													+ "");

									return data;

								}

							}
							if (i != 1) {

								for (int k = 1; k < i; k++) {
									String shape_status_pre = request.getParameter("e_status" + k);
									String shape_value_pre = request.getParameter("eShape_value" + k);
									String from_date_pre = request.getParameter("e_from_date" + k);
									String to_date_pre = request.getParameter("e_to_date" + k);
									String diagnosis_5_pre = request.getParameter("_diagnosis_code5" + k);								

									if (!diagnosis_5_pre.trim().equals("")) {
										String[] arrOfStr = diagnosis_5_pre.split("-", 2);
										diagnosis_5_pre = arrOfStr[0];

									}
									
									if (!shape_status.equals("1")) {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_5_pre.equals(diagnosis_5)) {
											tx.rollback();
											data.put("error", " Data Already Exists E-Shape  " + i + "th row");
											return data;
										}
									} else {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && diagnosis_5_pre.equals(diagnosis_5) ) {
											tx.rollback();
											data.put("error", " Data Already Exists E-Shape  " + i + "th row");
											return data;
										}
									}

								}
							}
							Mad_cat.setShape("E");

							Mad_cat.setShape_status(Integer.parseInt(shape_status));

							Mad_cat.setShape_value(shape_value);

//								if (!shape_status.equals("1")
							//
//										|| (from_date != null && !from_date.trim().equals("")
							//
//												&& !from_date.equals("DD/MM/YYYY"))
							//
//										|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
							if (!shape_status.equals("1")) {

								Mad_cat.setFrom_date(format.parse(from_date));

								Mad_cat.setTo_date(format.parse(to_date));

							}

							Mad_cat.setDiagnosis(diagnosis_5);

							Mad_cat.setClasification(mad_classification);

							Mad_cat.setCen_id(census_id);

							Mad_cat.setComm_id(comm_id);

							Mad_cat.setCreated_by(username);

							Mad_cat.setCreated_on(date);

							Mad_cat.setStatus(0);
							int check_s = check_shape(comm_id_str, "E");
							if (check_s == 0) {
								sessionHQL.save(Mad_cat);
								sessionHQL.flush();
								sessionHQL.clear();
							} else {
								tx.rollback();

								data.put("error", "Data Already Exists");

								return data;
							}

						}

					}

					// //////////////////////////////C COPE //////////////////////////////

					for (int i = 1; i <= cCopeOld_count; i++) {

						String cope_ch = request.getParameter("cCope_ch_id" + i);

						String cope_value = request.getParameter("c_cvalue" + i);

						String cope_other = request.getParameter("c_cother" + i);

						if (cope_value.equals("2 [c]") && cope_other.trim().equals("")) {

							tx.rollback();

							data.put("error", "Please Enter Other " + i + " Row");

							return data;

						}

						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

								.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

						if (cope_value.equals("2 [c]")) {

							Mad_cat.setOther(cope_other);

						} else {

							Mad_cat.setOther("");

						}
						if (i != 1) {
							for (int k = 1; k < i; k++) {
								String cope_value_pre = request.getParameter("c_cvalue" + k);
								String cope_other_pre = request.getParameter("c_cother" + k);
								if (cope_value_pre.equals(cope_value)) {
									if (cope_other_pre != null && cope_other != null) {
										if (cope_other_pre.equals(cope_other)) {
											tx.rollback();
											data.put("error", "Data Already Exists C_C  " + i + "th row");
											return data;
										}
									} else {
										tx.rollback();
										data.put("error", "Data Already Exists C_C  " + i + "th row");
										return data;
									}

								}
							}

						}
						Mad_cat.setFrom_date_1bx(null);

						Mad_cat.setTo_date_1bx(null);

						Mad_cat.setDiagnosis_1bx(null);

						Mad_cat.setShape_value(cope_value);

						Mad_cat.setClasification(mad_classification);

						Mad_cat.setModify_by(username);

						Mad_cat.setModify_on(date);

						Mad_cat.setStatus(0);

						sessionHQL.update(Mad_cat);

						sessionHQL.flush();

						sessionHQL.clear();

					}

					diffrence = cCope_count - cCopeOld_count;

					if (diffrence != 0) {

						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

						for (int i = cCopeOld_count + 1; i <= cCope_count; i++) {

							String cope_value = request.getParameter("c_cvalue" + i);

							String cope_other = request.getParameter("c_cother" + i);

							if (cope_value.equals("2 [c]") && cope_other.trim().equals("")) {

								tx.rollback();

								data.put("error", "Please Enter C-Cope Other " + i + " Row");

								return data;

							}

							if (cope_value.equals("2 [c]")) {

								Mad_cat.setOther(cope_other);

							} else {

								Mad_cat.setOther(null);

							}
							if (i != 1) {
								for (int k = 1; k < i; k++) {
									String cope_value_pre = request.getParameter("c_cvalue" + k);
									String cope_other_pre = request.getParameter("c_cother" + k);
									if (cope_value_pre.equals(cope_value)) {
										if (cope_other_pre != null && cope_other != null) {
											if (cope_other_pre.equals(cope_other)) {
												tx.rollback();
												data.put("error", " Data Already Exists C_C  " + i + "th row");
												return data;
											}
										} else {
											tx.rollback();
											data.put("error", " Data Already Exists C_C  " + i + "th row");
											return data;
										}

									}
								}

							}

							Mad_cat.setShape("C_C");

							Mad_cat.setShape_value(cope_value);

							Mad_cat.setClasification(mad_classification);

							Mad_cat.setCen_id(census_id);

							Mad_cat.setComm_id(comm_id);

							Mad_cat.setCreated_by(username);

							Mad_cat.setCreated_on(date);

							Mad_cat.setStatus(0);
							int check_s = check_shape(comm_id_str, "C_C");
							if (check_s == 0) {
								sessionHQL.save(Mad_cat);
								sessionHQL.flush();
								sessionHQL.clear();
							} else {
								tx.rollback();

								data.put("error", "Data Already Exists");

								return data;
							}

						}

					}

					// //////////////////////////////O COPE //////////////////////////////

					for (int i = 1; i <= oCopeOld_count; i++) {

						String cope_ch = request.getParameter("oCope_ch_id" + i);

						String cope_value = request.getParameter("c_ovalue" + i);
						if (i != 1) {
							for (int k = 1; k < i; k++) {
								String cope_value_pre = request.getParameter("c_ovalue" + k);
								if (cope_value_pre.equals(cope_value)) {
									tx.rollback();
									data.put("error", "Data Already exist C_O " + i + "th row");
									return data;
								}
							}
						}

						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

								.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

						Mad_cat.setFrom_date_1bx(null);

						Mad_cat.setTo_date_1bx(null);

						Mad_cat.setDiagnosis_1bx(null);

						Mad_cat.setShape_value(cope_value);

						Mad_cat.setClasification(mad_classification);

						Mad_cat.setModify_by(username);

						Mad_cat.setModify_on(date);

						Mad_cat.setStatus(0);

						sessionHQL.update(Mad_cat);

						sessionHQL.flush();

						sessionHQL.clear();

					}

					diffrence = oCope_count - oCopeOld_count;

					if (diffrence != 0) {

						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

						for (int i = oCopeOld_count + 1; i <= oCope_count; i++) {

							String cope_value = request.getParameter("c_ovalue" + i);

							Mad_cat.setShape("C_O");

							Mad_cat.setShape_value(cope_value);

							Mad_cat.setClasification(mad_classification);

							Mad_cat.setCen_id(census_id);

							Mad_cat.setComm_id(comm_id);

							Mad_cat.setCreated_by(username);

							Mad_cat.setCreated_on(date);

							Mad_cat.setStatus(0);
							if (i != 1) {
								for (int k = 1; k < i; k++) {
									String cope_value_pre = request.getParameter("c_ovalue" + k);
									if (cope_value_pre.equals(cope_value)) {
										tx.rollback();
										data.put("error", "Data Already exist C_O " + i + "th row ");
										return data;
									}
								}
							}
							int check_s = check_shape(comm_id_str, "C_O");
							if (check_s == 0) {
								sessionHQL.save(Mad_cat);
								sessionHQL.flush();
								sessionHQL.clear();
							} else {
								tx.rollback();

								data.put("error", "Data Already Exists");

								return data;
							}
//								sessionHQL.save(Mad_cat);
							//
//								sessionHQL.flush();
							//
//								sessionHQL.clear();

						}

					}

					// //////////////////////////////P COPE //////////////////////////////

					for (int i = 1; i <= pCopeOld_count; i++) {

						String cope_ch = request.getParameter("pCope_ch_id" + i);

						String cope_value = request.getParameter("c_pvalue" + i);

						if (i != 1) {

							for (int k = 1; k < i; k++) {
								String cope_value_pre = request.getParameter("c_pvalue" + k);
								if (cope_value_pre.equals(cope_value)) {
									tx.rollback();
									data.put("error", "Data Already exist C_P " + i + "th row ");
									return data;
								}
							}
						}
						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

								.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

						Mad_cat.setFrom_date_1bx(null);

						Mad_cat.setTo_date_1bx(null);

						Mad_cat.setDiagnosis_1bx(null);

						Mad_cat.setShape_value(cope_value);

						Mad_cat.setClasification(mad_classification);

						Mad_cat.setModify_by(username);

						Mad_cat.setModify_on(date);

						Mad_cat.setStatus(0);

						sessionHQL.update(Mad_cat);

						sessionHQL.flush();

						sessionHQL.clear();

					}

					diffrence = pCope_count - pCopeOld_count;

					if (diffrence != 0) {

						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

						for (int i = pCopeOld_count + 1; i <= pCope_count; i++) {

							String cope_value = request.getParameter("c_pvalue" + i);
							if (i != 1) {
								for (int k = 1; k < i; k++) {
									String cope_value_pre = request.getParameter("c_pvalue" + k);
									if (cope_value_pre.equals(cope_value)) {
										tx.rollback();
										data.put("error", "Data Already exist C_P " + i + "th row ");
										return data;
									}
								}
							}

							Mad_cat.setShape("C_P");

							Mad_cat.setShape_value(cope_value);

							Mad_cat.setClasification(mad_classification);

							Mad_cat.setCen_id(census_id);

							Mad_cat.setComm_id(comm_id);

							Mad_cat.setCreated_by(username);

							Mad_cat.setCreated_on(date);

							Mad_cat.setStatus(0);
							int check_s = check_shape(comm_id_str, "C_P");
							if (check_s == 0) {
								sessionHQL.save(Mad_cat);
								sessionHQL.flush();
								sessionHQL.clear();
							} else {
								tx.rollback();

								data.put("error", "Data Already Exists");

								return data;
							}
//								sessionHQL.save(Mad_cat);
							//
//								sessionHQL.flush();
							//
//								sessionHQL.clear();

						}

					}

					// //////////////////////////////E COPE //////////////////////////////

					for (int i = 1; i <= eCopeOld_count; i++) {

						String cope_ch = request.getParameter("eCope_ch_id" + i);

						String cope_value = request.getParameter("c_evalue" + i);

						String cope_sub_value = request.getParameter("c_esubvalue" + i);

						String cope_other = request.getParameter("c_esubvalueother" + i);

						if (cope_value.equals("1") && cope_sub_value.equals("0")) {

							tx.rollback();

							data.put("error", "Please Select The Cope Sub Value " + i + " Row");

							return data;

						}

						if (cope_sub_value.equals("k") && cope_other.trim().equals("")) {

							tx.rollback();

							data.put("error", "Please Enter Other " + i + " Row");

							return data;

						}

						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

								.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

						if (cope_value.equals("1")) {

							Mad_cat.setShape_sub_value(cope_sub_value);

						} else {

							Mad_cat.setShape_sub_value(null);

						}

						if (cope_sub_value.equals("k")) {

							Mad_cat.setOther(cope_other);

						} else {

							Mad_cat.setOther(null);

						}

						if (i != 1) {
							for (int k = 1; k < i; k++) {
								String cope_value_pre = request.getParameter("c_evalue" + k);
								String cope_sub_value_pre = request.getParameter("c_esubvalue" + k);
								if (cope_value_pre.equals(cope_value) && cope_value != null && cope_sub_value != null) {
									if (cope_value_pre.equals(cope_value)
											&& cope_sub_value_pre.equals(cope_sub_value)) {
										tx.rollback();
										data.put("error", "Data Already Exist For E-Cope Sub Value " + i + " Row");
										return data;
									}
								}
								if (cope_value_pre.equals(cope_value) && cope_value.equals(null)
										&& cope_sub_value.equals(null)) {
									tx.rollback();
									data.put("error", "Data Already Exist For E-Cope Sub Value " + i + " Row");
									return data;
								}
							}
						}

						Mad_cat.setFrom_date_1bx(null);

						Mad_cat.setTo_date_1bx(null);

						Mad_cat.setDiagnosis_1bx(null);

						Mad_cat.setShape_value(cope_value);

						Mad_cat.setClasification(mad_classification);

						Mad_cat.setModify_by(username);

						Mad_cat.setModify_on(date);

						Mad_cat.setStatus(0);

						sessionHQL.update(Mad_cat);

						sessionHQL.flush();

						sessionHQL.clear();

					}

					diffrence = eCope_count - eCopeOld_count;

					if (diffrence != 0) {

						TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

						for (int i = eCopeOld_count + 1; i <= eCope_count; i++) {

							String cope_value = request.getParameter("c_evalue" + i);

							String cope_sub_value = request.getParameter("c_esubvalue" + i);

							String cope_other = request.getParameter("c_esubvalueother" + i);

							if (cope_value.equals("1") && cope_sub_value.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The E-Cope Sub Value " + i + " Row");

								return data;

							}

							if (cope_sub_value.equals("k") && cope_other.trim().equals("")) {

								tx.rollback();

								data.put("error", "Please Enter E-Cope Other " + i + " Row");

								return data;

							}

							if (cope_value.equals("1")) {

								Mad_cat.setShape_sub_value(cope_sub_value);

							} else {

								Mad_cat.setShape_sub_value("0");

							}

							if (cope_sub_value.equals("k")) {

								Mad_cat.setOther(cope_other);

							} else {

								Mad_cat.setOther("");

							}

							if (i != 1) {
								for (int k = 1; k < i; k++) {
									String cope_value_pre = request.getParameter("c_evalue" + k);
									String cope_sub_value_pre = request.getParameter("c_esubvalue" + k);
									if (cope_value_pre.equals(cope_value) && cope_value != null
											&& cope_sub_value != null) {
										if (cope_value_pre.equals(cope_value)
												&& cope_sub_value_pre.equals(cope_sub_value)) {
											tx.rollback();
											data.put("error", "Data Already Exist For E-Cope Sub Value " + i + " Row");
											return data;
										}
									}
									if (cope_value_pre.equals(cope_value) && cope_value.equals(null)
											&& cope_sub_value.equals(null)) {
										tx.rollback();
										data.put("error", "Data Already Exist For E-Cope Sub Value " + i + " Row");
										return data;
									}
								}
							}

							Mad_cat.setShape("C_E");

							Mad_cat.setShape_value(cope_value);

							Mad_cat.setClasification(mad_classification);

							Mad_cat.setCen_id(census_id);

							Mad_cat.setComm_id(comm_id);

							Mad_cat.setCreated_by(username);

							Mad_cat.setCreated_on(date);

							Mad_cat.setStatus(0);
							int check_s = check_shape(comm_id_str, "C_E");
							if (check_s == 0) {
								sessionHQL.save(Mad_cat);
								sessionHQL.flush();
								sessionHQL.clear();
							} else {
								tx.rollback();
								data.put("error", "Data Already Exists");
								return data;
							}
//								sessionHQL.save(Mad_cat);
							//
//								sessionHQL.flush();
							//
//								sessionHQL.clear();

						}

					}

				}
			}

			tx.commit();

			data.put("census_id", String.valueOf(census_id));

			data.put("i_id", String.valueOf(i_id));

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				data.put("error", "Data Not Updated");

			} catch (RuntimeException rbe) {

				data.put("error", "Data Not Updated");

			}

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

		return data;

	}

		


	private int check_duplicate(BigInteger comm_id,String Table) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();
int r=0;
	String querry="SELECT CASE WHEN id IS NULL THEN 0 ELSE id END AS census_id  from  "+Table+"  WHERE comm_id = :comm_id";

List<Integer> m=sessionHQL.createQuery(querry).setBigInteger("comm_id",comm_id ).list();
if(!m.isEmpty())
{
	r= m.get(0);
}
else {
	r=0;
}
	sessionHQL.getTransaction().commit();
		sessionHQL.close();

		return r;

	}

	private TB_TRANS_PROPOSED_COMM_LETTER getdatecommdate(BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		TB_TRANS_PROPOSED_COMM_LETTER id = (TB_TRANS_PROPOSED_COMM_LETTER) sessionHQL

				.get(TB_TRANS_PROPOSED_COMM_LETTER.class, comm_id);

		sessionHQL.getTransaction().commit();

		sessionHQL.close();

		return id;

	}

	// ************************************END PERSONNEL
	// DETAIL*******************************************

	@RequestMapping(value = "/admin/address_details_action", method = RequestMethod.POST)

	public @ResponseBody Map<String, String> address_details_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		Map<String, String> data = new HashMap<>();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

	
		String username = session.getAttribute("username").toString();

		String org_domicile_country = request.getParameter("org_domicile_Country");

		String org_domicile_tehsil = request.getParameter("org_domicile_tehsil");

		String pre_domicile_Country = request.getParameter("pre_domicile_Country");

		String pre_domicile_tehsil = request.getParameter("pre_domicile_tehsil");

		String per_home_addr_Country = request.getParameter("per_home_addr_Country");

		String permanent_border_area = request.getParameter("permanent_border_area");

		String org_domicile_state = request.getParameter("org_domicile_state");

		String org_domicile_district = request.getParameter("org_domicile_district");

		String pre_domicile_state = request.getParameter("pre_domicile_state");

		String pre_domicile_district = request.getParameter("pre_domicile_district");

		String per_home_addr_village = request.getParameter("per_home_addr_village");

		String per_home_addr_tehsil = request.getParameter("per_home_addr_tehsil");

		String per_home_addr_district = request.getParameter("per_home_addr_district");

		String per_home_addr_state = request.getParameter("per_home_addr_state");

		String per_home_addr_pin = request.getParameter("per_home_addr_pin");

		String per_home_addr_rail = request.getParameter("per_home_addr_rail");

		String pers_addr_village = request.getParameter("pers_addr_village");

		String pers_addr_tehsil = request.getParameter("pers_addr_tehsil");

		String pers_addr_district = request.getParameter("pers_addr_district");

		String pers_addr_state = request.getParameter("pers_addr_state");

		String pers_addr_Country = request.getParameter("pers_addr_Country");

		String pers_addr_pin = request.getParameter("pers_addr_pin");

		String pers_addr_rail = request.getParameter("pers_addr_rail");

		String spouse_addr_village = request.getParameter("spouse_addr_village");

		String spouse_addr_tehsil = request.getParameter("spouse_addr_tehsil");

		String spouse_addr_district = request.getParameter("spouse_addr_district");

		String spouse_addr_state = request.getParameter("spouse_addr_state");

		String spouse_addr_Country = request.getParameter("spouse_addr_Country");

		String spouse_addr_pin = request.getParameter("spouse_addr_pin");

		String spouse_addr_rail = request.getParameter("spouse_addr_rail");

		String Stn_HQ_sus_no = request.getParameter("Stn_HQ_sus_no");

		String check_spouse_addr = request.getParameter("check_spouse_address");

		String marital_status = request.getParameter("marital_status");

		String nok_name = request.getParameter("nok_name");

		String nok_relation = request.getParameter("nok_relation");

		String nok_relation_otherBool = request.getParameter("nok_relation_otherBool");

		String nok_relation_other = request.getParameter("nok_relation_other");

		String nok_village = request.getParameter("nok_village");

		String nok_country = request.getParameter("nok_country");

		String nok_tehsil = request.getParameter("nok_tehsil");

		String nok_district = request.getParameter("nok_district");

		String nok_state = request.getParameter("nok_state");

		String nok_pin = request.getParameter("nok_pin");

		String nok_near_railway_station = request.getParameter("nok_near_railway_station");

		String nok_mobile_no = request.getParameter("nok_mobile_no");

		int addr_ch_id = Integer.parseInt(request.getParameter("addr_ch_id"));

		int nok_ch_id = Integer.parseInt(request.getParameter("nok_ch_id"));

		int p_id = Integer.parseInt(request.getParameter("p_id"));

		BigInteger comm_id = BigInteger.ZERO;
		comm_id = new BigInteger(request.getParameter("comm_id"));

		String nok_rural_urban = request.getParameter("nok_rural_urban");

		String perm_rural_urban = request.getParameter("per_home_addr_rural_urban");

		String pers_rural_urban = request.getParameter("pers_addr_rural_urban");

		String spouse_rural_urban = request.getParameter("spouse_addr_rural_urban");

		String org_domicile_Country_other = request.getParameter("org_domicile_Country_other");

		String org_domicile_state_other = request.getParameter("org_domicile_state_other");

		String org_domicile_district_other = request.getParameter("org_domicile_district_other");

		String org_domicile_tehsil_other = request.getParameter("org_domicile_tehsil_other");

		String org_domicile_Country_otherV = request.getParameter("org_co");

		String org_domicile_state_otherV = request.getParameter("org_so");

		String org_domicile_district_otherV = request.getParameter("org_do");

		String org_domicile_tehsil_otherV = request.getParameter("org_to");

		String pre_domicile_Country_other = request.getParameter("pre_domicile_Country_other");

		String pre_domicile_state_other = request.getParameter("pre_domicile_state_other");

		String pre_domicile_district_other = request.getParameter("pre_domicile_district_other");

		String pre_domicile_tehsil_other = request.getParameter("pre_domicile_tehsil_other");

		String pre_domicile_Country_otherV = request.getParameter("pd_co");

		String pre_domicile_state_otherV = request.getParameter("pd_so");

		String pre_domicile_district_otherV = request.getParameter("pd_do");

		String pre_domicile_tehsil_otherV = request.getParameter("pd_to");

		String per_home_addr_Country_other = request.getParameter("per_home_addr_Country_other");

		String per_home_addr_state_other = request.getParameter("per_home_addr_state_other");

		String per_home_addr_district_other = request.getParameter("per_home_addr_district_other");

		String per_home_addr_tehsil_other = request.getParameter("per_home_addr_tehsil_other");

		String per_home_addr_Country_otherV = request.getParameter("per_co");

		String per_home_addr_state_otherV = request.getParameter("per_so");

		String per_home_addr_district_otherV = request.getParameter("per_do");

		String per_home_addr_tehsil_otherV = request.getParameter("per_to");

		String pers_addr_Country_other = request.getParameter("pers_addr_Country_other");

		String pers_addr_state_other = request.getParameter("pers_addr_state_other");

		String pers_addr_district_other = request.getParameter("pers_addr_district_other");

		String pers_addr_tehsil_other = request.getParameter("pers_addr_tehsil_other");

		String pers_addr_Country_otherV = request.getParameter("pre_co");

		String pers_addr_state_otherV = request.getParameter("pre_so");

		String pers_addr_district_otherV = request.getParameter("pre_do");

		String pers_addr_tehsil_otherV = request.getParameter("pre_to");

		String ctry_other = request.getParameter("ctry_other");

		String st_other = request.getParameter("st_other");

		String dist_other = request.getParameter("dist_other");

		String tehsil_other = request.getParameter("nok_tehsil_other");

		String ctry_otherV = request.getParameter("nok_co");

		String st_otherV = request.getParameter("nok_so");

		String dist_otherV = request.getParameter("nok_do");

		String tehsil_otherV = request.getParameter("nok_to");

		String spouse_addr_district_other = request.getParameter("spouse_addr_district_other");

		String spouse_addr_state_other = request.getParameter("spouse_addr_state_other");

		String spouse_addr_country_other = request.getParameter("spouse_addr_country_other");

		String spouse_addr_tehsil_other = request.getParameter("spouse_addr_tehsil_other");

		String spouse_addr_district_otherV = request.getParameter("sp_do");

		String spouse_addr_state_otherV = request.getParameter("sp_so");

		String spouse_addr_country_otherV = request.getParameter("sp_co");

		String spouse_addr_tehsil_otherV = request.getParameter("sp_to");

		String rvalue = "";

		if (org_domicile_country == null || org_domicile_country.equals("0")) {

			data.put("error", "Please Select The Original Domicile of Country");

			return data;

		}

		if (org_domicile_Country_otherV != null && org_domicile_Country_otherV.equals("OTHERS")) {

			if (org_domicile_Country_other == null || org_domicile_Country_other.trim().equals("")) {

				data.put("error", "Please Enter The Original Domicile of Country Other");

				return data;

			}
			if (org_domicile_Country_other != null && !org_domicile_Country_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(org_domicile_Country_other)) {
					data.put("error", " Original Domicile of Country Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(org_domicile_Country_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Original Domicile of Country Other " + valid.isValidLengthMSG);
					return data;
				}
			}

		} else {

			org_domicile_Country_other = null;

		}

		if (org_domicile_state == null || org_domicile_state.equals("0")) {

			data.put("error", "Please Select The Original Domicile of State");

			return data;

		}

		if (org_domicile_state_otherV != null && org_domicile_state_otherV.equals("OTHERS")) {

			if (org_domicile_state_other == null || org_domicile_state_other.trim().equals("")) {

				data.put("error", "Please Enter The Original Domicile of State Other");

				return data;

			}
			if (org_domicile_state_other != null && !org_domicile_state_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(org_domicile_state_other)) {
					data.put("error", "Original Domicile of State Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(org_domicile_state_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Original Domicile of State Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			org_domicile_state_other = null;

		}

		if (org_domicile_district == null || org_domicile_district.equals("0")) {

			data.put("error", "Please Select The Original Domicile of District");

			return data;

		}

		if (org_domicile_district_otherV != null && org_domicile_district_otherV.equals("OTHERS")) {

			if (org_domicile_district_other == null || org_domicile_district_other.trim().equals("")) {

				data.put("error", "Please Enter The Original Domicile of District Other");

				return data;

			}
			if (org_domicile_district_other != null && !org_domicile_district_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(org_domicile_district_other)) {
					data.put("error", "Original Domicile of District Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(org_domicile_district_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Original Domicile of District Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			org_domicile_district_other = null;

		}

		if (org_domicile_tehsil == null || org_domicile_tehsil.equals("0")) {

			data.put("error", "Please Select The Original Domicile of Tehsil");

			return data;

		}

		if (org_domicile_tehsil_otherV != null && org_domicile_tehsil_otherV.equals("OTHERS")) {

			if (org_domicile_tehsil_other == null || org_domicile_tehsil_other.trim().equals("")) {

				data.put("error", "Please Enter The Original Domicile of Tehsil Other");

				return data;

			}
			if (org_domicile_tehsil_other != null && !org_domicile_tehsil_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(org_domicile_tehsil_other)) {
					data.put("error", "Original Domicile of Tehsil Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(org_domicile_tehsil_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Original Domicile of Tehsil Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			org_domicile_tehsil_other = null;

		}

		if (pre_domicile_Country == null || pre_domicile_Country.equals("0")) {

			data.put("error", "Please Select The Presently Domicile of Country");

			return data;

		}

		if (pre_domicile_Country_otherV != null && pre_domicile_Country_otherV.equals("OTHERS")) {

			if (pre_domicile_Country_other == null || pre_domicile_Country_other.trim().equals("")) {

				data.put("error", "Please Enter The Original Presently Domicile of Country Other");

				return data;

			}
			if (pre_domicile_Country_other != null && !pre_domicile_Country_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(pre_domicile_Country_other)) {
					data.put("error", " Presently Domicile of Country Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(pre_domicile_Country_other, valid.nameMax, valid.nameMin)) {
					data.put("error", " Presently Domicile of Country Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			pre_domicile_Country_other = null;

		}

		if (pre_domicile_state == null || pre_domicile_state.equals("0")) {

			data.put("error", "Please Select The Presently Domicile of State");

			return data;

		}

		if (pre_domicile_state_otherV != null && pre_domicile_state_otherV.equals("OTHERS")) {

			if (pre_domicile_state_other == null || pre_domicile_state_other.trim().equals("")) {

				data.put("error", "Please Enter The Presently Domicile of State Other");

				return data;

			}
			if (pre_domicile_state_other != null && !pre_domicile_state_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(pre_domicile_state_other)) {
					data.put("error", " Presently Domicile of State Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(pre_domicile_state_other, valid.nameMax, valid.nameMin)) {
					data.put("error", " Presently Domicile of State Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			pre_domicile_state_other = null;

		}

		if (pre_domicile_district == null || pre_domicile_district.equals("0")) {

			data.put("error", "Please Select The Presently Domicile of District");

			return data;

		}

		if (pre_domicile_district_otherV != null && pre_domicile_district_otherV.equals("OTHERS")) {
			if (pre_domicile_district_other == null || pre_domicile_district_other.trim().equals("")) {
				data.put("error", "Please Enter The Presently Domicile of District Other");
				return data;
			}
			if (pre_domicile_district_other != null && !pre_domicile_district_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(pre_domicile_district_other)) {
					data.put("error", " Presently Domicile of District Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(pre_domicile_district_other, valid.nameMax, valid.nameMin)) {
					data.put("error", " Presently Domicile of District Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {
			pre_domicile_district_other = null;
		}

		if (pre_domicile_tehsil == null || pre_domicile_tehsil.equals("0")) {
			data.put("error", "Please Select The Presently Domicile of Tehsil");
			return data;
		}

		if (pre_domicile_tehsil_otherV != null && pre_domicile_tehsil_otherV.equals("OTHERS")) {
			if (pre_domicile_tehsil_other == null || pre_domicile_tehsil_other.trim().equals("")) {
				data.put("error", "Please Enter The Presently Domicile of Tehsil Other");
				return data;
			}
			if (pre_domicile_tehsil_other != null && !pre_domicile_tehsil_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(pre_domicile_tehsil_other)) {
					data.put("error", " Presently Domicile of Tehsil Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(pre_domicile_tehsil_other, valid.nameMax, valid.nameMin)) {
					data.put("error", " Presently Domicile of Tehsil Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			pre_domicile_tehsil_other = null;

		}

		if (per_home_addr_Country == null || per_home_addr_Country.equals("0")) {

			data.put("error", "Please Select The Permanent Home Address Country");

			return data;

		}

		if (per_home_addr_Country_otherV != null && per_home_addr_Country_otherV.equals("OTHERS")) {

			if (per_home_addr_Country_other == null || per_home_addr_Country_other.trim().equals("")) {

				data.put("error", "Please Enter The Permanent Home Address Country Other");

				return data;

			}
			if (per_home_addr_Country_other != null && !per_home_addr_Country_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(per_home_addr_Country_other)) {
					data.put("error", "Permanent Home Address Country Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(per_home_addr_Country_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Permanent Home Address Country Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			per_home_addr_Country_other = null;

		}

		if (per_home_addr_state == null || per_home_addr_state.equals("0")) {

			data.put("error", "Please Select Permanent Home Address The State");

		}

		if (per_home_addr_state_otherV != null && per_home_addr_state_otherV.equals("OTHERS")) {

			if (per_home_addr_state_other == null || per_home_addr_state_otherV.trim().equals("")) {

				data.put("error", "Please Enter The Permanent Home Address State Other");

				return data;

			}
			if (per_home_addr_state_other != null && !per_home_addr_state_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(per_home_addr_state_other)) {
					data.put("error", "Permanent Home Address State Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(per_home_addr_state_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Permanent Home Address State Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			per_home_addr_state_other = null;

		}

		if (per_home_addr_district == null || per_home_addr_district.equals("0")) {

			data.put("error", "Please Select The Permanent Home Address District");

			return data;

		}

		if (per_home_addr_district_otherV != null && per_home_addr_district_otherV.equals("OTHERS")) {

			if (per_home_addr_district_other == null || per_home_addr_district_other.trim().equals("")) {

				data.put("error", "Please Enter The Permanent Home Address District Other");

				return data;

			}
			if (per_home_addr_district_other != null && !per_home_addr_district_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(per_home_addr_district_other)) {
					data.put("error", "Permanent Home Address District Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(per_home_addr_district_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Permanent Home Address District Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			per_home_addr_district_other = null;

		}

		if (per_home_addr_tehsil == null || per_home_addr_tehsil.equals("0")) {

			data.put("error", "Please Select The Permanent Home Address Tehsil");

			return data;

		}

		if (per_home_addr_tehsil_otherV != null && per_home_addr_tehsil_otherV.equals("OTHERS")) {

			if (per_home_addr_tehsil_other == null || per_home_addr_tehsil_other.trim().equals("")) {

				data.put("error", "Please Enter The Permanent Home Address Tehsil Other");

				return data;

			}
			if (per_home_addr_tehsil_other != null && !per_home_addr_tehsil_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(per_home_addr_tehsil_other)) {
					data.put("error", "Permanent Home Address Tehsil Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(per_home_addr_tehsil_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Permanent Home Address Tehsil Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			per_home_addr_tehsil_other = null;

		}

		if (per_home_addr_village == null || per_home_addr_village.trim().equals("")) {

			data.put("error", "Please Enter The Permanent Home Address Village/Town/City");

			return data;

		}
		if (per_home_addr_village != null && !per_home_addr_village.trim().equals("")) {
			if (!valid.isOnlyAlphabet(per_home_addr_village)) {
				data.put("error", "Permanent Home Address Village/Town/City " + valid.isOnlyAlphabetMSG);
				return data;
			}
			if (!valid.isvalidLength(per_home_addr_village, valid.nameMax, valid.nameMin)) {
				data.put("error", "Permanent Home Address Village/Town/City " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (per_home_addr_pin == null || per_home_addr_pin.trim().equals("") || per_home_addr_pin.length() < 6) {

			data.put("error", "Please Enter Valid Permanent Home Address Pin");

			return data;

		}
		if (per_home_addr_pin != null && !per_home_addr_pin.trim().equals("")) {
			if (valid.isOnlyNumer(per_home_addr_pin) == true) {
				data.put("error", valid.isOnlyNumerMSG + " Permanent Home Address Pin");
				return data;
			}
			if (!valid.isvalidLength(per_home_addr_pin, valid.pin_noMax, valid.pin_noMin)) {
				data.put("error", "Permanent Home Address Pin " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (per_home_addr_rail == null || per_home_addr_rail.trim().equals("")) {

			data.put("error", "Please Enter The Permanent Home Address Nearest Railway Station");

			return data;

		}
		if (per_home_addr_rail != null && !per_home_addr_rail.trim().equals("")) {
			if (!valid.isOnlyAlphabetNumeric(per_home_addr_rail)) {
				data.put("error", valid.isOnlyAlphabetNumericMSG + " Permanent Home Address Nearest Railway Station");
				return data;
			}
			if (!valid.isvalidLength(per_home_addr_rail, valid.authorityMax, valid.authorityMin)) {
				data.put("error", " Permanent Home Address Nearest Railway Station " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (pers_addr_Country == null || pers_addr_Country.equals("0")) {

			data.put("error", "Please Select The Present Address Country");

			return data;

		}

		if (pers_addr_Country_otherV != null && pers_addr_Country_otherV.equals("OTHERS")) {

			if (pers_addr_Country_other == null || pers_addr_Country_other.trim().equals("")) {

				data.put("error", "Please Enter The Present  Address Country Other");

				return data;

			}
			if (pers_addr_Country_other != null && !pers_addr_Country_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(pers_addr_Country_other)) {
					data.put("error", "Present  Address Country Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(pers_addr_Country_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Present  Address Country Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			pers_addr_Country_other = null;

		}

		if (pers_addr_state == null || pers_addr_state.equals("0")) {

			data.put("error", "Please Select The Present Address State");

			return data;

		}

		if (pers_addr_state_otherV != null && pers_addr_state_otherV.equals("OTHERS")) {

			if (pers_addr_state_other == null || pers_addr_state_other.trim().equals("")) {

				data.put("error", "Please Enter The Present  Address State Other");

				return data;

			}
			if (pers_addr_state_other != null && !pers_addr_state_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(pers_addr_state_other)) {
					data.put("error", "Present  Address State Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(pers_addr_state_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Present  Address State Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			pers_addr_state_other = null;

		}

		if (pers_addr_district == null || pers_addr_district.equals("0")) {

			data.put("error", "Please Select The Present Address District");

			return data;

		}

		if (pers_addr_district_otherV != null && pers_addr_district_otherV.equals("OTHERS")) {

			if (pers_addr_district_other == null || pers_addr_district_other.trim().equals("")) {

				data.put("error", "Please Enter The Present  Address District Other");

				return data;

			}
			if (pers_addr_district_other != null && !pers_addr_district_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(pers_addr_district_other)) {
					data.put("error", "Present  Address District Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(pers_addr_district_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Present  Address District Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			pers_addr_district_other = null;

		}

		if (pers_addr_tehsil == null || pers_addr_tehsil.equals("0")) {

			data.put("error", "Please Select The Present Address Tehsil");

			return data;

		}

		if (pers_addr_tehsil_otherV != null && pers_addr_tehsil_otherV.equals("OTHERS")) {

			if (pers_addr_tehsil_other == null || pers_addr_tehsil_other.trim().equals("")) {

				data.put("error", "Please Enter The Present  Address Tehsil Other");

				return data;

			}
			if (pers_addr_tehsil_other != null && !pers_addr_tehsil_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(pers_addr_tehsil_other)) {
					data.put("error", "Present  Address Tehsil Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(pers_addr_tehsil_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Present  Address Tehsil Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			pers_addr_tehsil_other = null;

		}

		if (pers_addr_village == null || pers_addr_village.trim().equals("")) {

			data.put("error", "Please Enter The  Present Address Village/Town/City");

			return data;

		}
		if (pers_addr_village != null && !pers_addr_village.trim().equals("")) {
			if (!valid.isOnlyAlphabet(pers_addr_village)) {
				data.put("error", "Present Address Village/Town/City " + valid.isOnlyAlphabetMSG);
				return data;
			}
			if (!valid.isvalidLength(pers_addr_village, valid.nameMax, valid.nameMin)) {
				data.put("error", "Present Address Village/Town/City " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (pers_addr_pin == null || pers_addr_pin.trim().equals("") || pers_addr_pin.length() < 6) {

			data.put("error", "Please Enter Valid Present Address Pin");

			return data;

		}
		if (pers_addr_pin != null && !pers_addr_pin.trim().equals("")) {
			if (valid.isOnlyNumer(pers_addr_pin) == true) {
				data.put("error", valid.isOnlyNumerMSG + " Present Address Pin");
				return data;
			}
			if (!valid.isvalidLength(pers_addr_pin, valid.pin_noMax, valid.pin_noMin)) {
				data.put("error", "Present Address Pin " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (pers_addr_rail == null || pers_addr_rail.trim().equals("")) {

			data.put("error", "Please Enter The Present Address Nearest Railway Station");

			return data;

		}
		if (pers_addr_rail != null && !pers_addr_rail.trim().equals("")) {
			if (!valid.isOnlyAlphabetNumeric(pers_addr_rail)) {
				data.put("error", valid.isOnlyAlphabetNumericMSG + " Present Address Nearest Railway Station");
				return data;
			}
			if (!valid.isvalidLength(pers_addr_rail, valid.authorityMax, valid.authorityMin)) {
				data.put("error", "Present Address Nearest Railway Station " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (marital_status != null && marital_status.equals("8") && check_spouse_addr != null

				&& check_spouse_addr.equals("on")) {

			if (spouse_addr_Country == null || spouse_addr_Country.equals("0")) {

				data.put("error", "Please Select The SF ACCN Country");

				return data;

			}

			if (spouse_addr_country_otherV != null && spouse_addr_country_otherV.equals("OTHERS")) {

				if (spouse_addr_country_other == null || spouse_addr_country_other.trim().equals("")) {

					data.put("error", "Please Enter The  SF ACCN Country Other");

					return data;

				}
				if (spouse_addr_country_other != null && !spouse_addr_country_other.trim().equals("")) {
					if (!valid.isOnlyAlphabet(spouse_addr_country_other)) {
						data.put("error", "SF ACCN Country Other " + valid.isOnlyAlphabetMSG);
						return data;
					}
					if (!valid.isvalidLength(spouse_addr_country_other, valid.nameMax, valid.nameMin)) {
						data.put("error", "SF ACCN Country Other " + valid.isValidLengthMSG);
						return data;
					}
				}
			} else {

				spouse_addr_country_other = null;

			}

			if (spouse_addr_state == null || spouse_addr_state.equals("0")) {

				data.put("error", "Please Select The SF ACCN  State");

				return data;

			}

			if (spouse_addr_state_otherV != null && spouse_addr_state_otherV.equals("OTHERS")) {

				if (spouse_addr_state_other == null || spouse_addr_state_other.trim().equals("")) {

					data.put("error", "Please Enter The  SF ACCN State Other");

					return data;

				}
				if (spouse_addr_state_other != null && !spouse_addr_state_other.trim().equals("")) {
					if (!valid.isOnlyAlphabet(spouse_addr_state_other)) {
						data.put("error", "SF ACCN State Other " + valid.isOnlyAlphabetMSG);
						return data;
					}
					if (!valid.isvalidLength(spouse_addr_state_other, valid.nameMax, valid.nameMin)) {
						data.put("error", "SF ACCN State Other " + valid.isValidLengthMSG);
						return data;
					}
				}
			} else {

				spouse_addr_state_other = null;

			}

			if (spouse_addr_district == null || spouse_addr_district.equals("0")) {

				data.put("error", "Please Select The SF ACCN  District");

				return data;

			}

			if (spouse_addr_district_otherV != null && spouse_addr_district_otherV.equals("OTHERS")) {

				if (spouse_addr_district_other == null || spouse_addr_district_other.trim().equals("")) {

					data.put("error", "Please Enter The  SF ACCN District Other");

					return data;

				}
				if (spouse_addr_district_other != null && !spouse_addr_district_other.trim().equals("")) {
					if (!valid.isOnlyAlphabet(spouse_addr_district_other)) {
						data.put("error", "SF ACCN District Other " + valid.isOnlyAlphabetMSG);
						return data;
					}
					if (!valid.isvalidLength(spouse_addr_district_other, valid.nameMax, valid.nameMin)) {
						data.put("error", "SF ACCN District Other " + valid.isValidLengthMSG);
						return data;
					}
				}
			} else {

				spouse_addr_district_other = null;

			}

			if (spouse_addr_tehsil == null || spouse_addr_tehsil.equals("0")) {

				data.put("error", "Please Select The SF ACCN Tehsil");

				return data;

			}

			if (spouse_addr_tehsil_otherV != null && spouse_addr_tehsil_otherV.equals("OTHERS")) {

				if (spouse_addr_tehsil_other == null || spouse_addr_tehsil_other.trim().equals("")) {

					data.put("error", "Please Enter The  SF ACCN  Tehsil Other");

					return data;

				}
				if (spouse_addr_tehsil_other != null && !spouse_addr_tehsil_other.trim().equals("")) {
					if (!valid.isOnlyAlphabet(spouse_addr_tehsil_other)) {
						data.put("error", "SF ACCN Tehsil Other " + valid.isOnlyAlphabetMSG);
						return data;
					}
					if (!valid.isvalidLength(spouse_addr_tehsil_other, valid.nameMax, valid.nameMin)) {
						data.put("error", "SF ACCN Tehsil Other " + valid.isValidLengthMSG);
						return data;
					}
				}
			} else {

				spouse_addr_tehsil_other = null;

			}

			if (spouse_addr_village == null || spouse_addr_village.trim().equals("")) {

				data.put("error", "Please Enter The SF ACCN  Village/Town/City");

				return data;

			}
			if (spouse_addr_village != null && !spouse_addr_village.trim().equals("")) {
				if (!valid.isOnlyAlphabet(spouse_addr_village)) {
					data.put("error", "SF ACCN Village/Town/City " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(spouse_addr_village, valid.nameMax, valid.nameMin)) {
					data.put("error", "SF ACCN Village/Town/City " + valid.isValidLengthMSG);
					return data;
				}
			}
			if (spouse_addr_pin == null || spouse_addr_pin.trim().equals("") || spouse_addr_pin.length() < 6) {

				data.put("error", "Please Enter Valid SF ACCN Pin");

				return data;

			}
			if (spouse_addr_pin != null && !spouse_addr_pin.trim().equals("")) {
				if (valid.isOnlyNumer(spouse_addr_pin) == true) {
					data.put("error", valid.isOnlyNumerMSG + " SF ACCN Pin");
					return data;
				}
				if (!valid.isvalidLength(spouse_addr_pin, valid.pin_noMax, valid.pin_noMin)) {
					data.put("error", "SF ACCN Pin " + valid.isValidLengthMSG);
					return data;
				}
			}
			if (spouse_addr_rail == null || spouse_addr_rail.trim().equals("")) {

				data.put("error", "Please Enter The SF ACCN  Nearest Railway Station");

				return data;

			}
			if (spouse_addr_rail != null && !spouse_addr_rail.trim().equals("")) {
				if (!valid.isOnlyAlphabetNumeric(spouse_addr_rail)) {
					data.put("error", valid.isOnlyAlphabetNumericMSG + " SF ACCN  Nearest Railway Station");
					return data;
				}
				if (!valid.isvalidLength(spouse_addr_rail, valid.authorityMax, valid.authorityMin)) {
					data.put("error", "SF ACCN  Nearest Railway Station " + valid.isValidLengthMSG);
					return data;
				}
			}
			if (Stn_HQ_sus_no == null || Stn_HQ_sus_no.trim().equals("")) {

				data.put("error", "Please Enter The SF ACCN  Stn HQ SUS No");

				return data;

			}
			if (Stn_HQ_sus_no != null && !Stn_HQ_sus_no.trim().equals("")) {
				if (!valid.SusNoLength(Stn_HQ_sus_no)) {
					data.put("error", valid.SusNoMSG);
					return data;
				}

				if (!valid.isOnlyAlphabetNumericSpaceNot(Stn_HQ_sus_no)) {
					data.put("error", valid.isOnlyAlphabetNumericSpaceNotMSG + " Unit SUS No");
					return data;
				}
			}
		} else {

			spouse_addr_Country = "0";
			spouse_addr_state = "0";
			spouse_addr_district = "0";
			spouse_addr_tehsil = "0";
			spouse_addr_village = null;
			spouse_addr_pin = "0";
			spouse_addr_rail = null;
			Stn_HQ_sus_no = null;
			spouse_rural_urban = null;

		}

		if (nok_name == null || nok_name.trim().equals("")) {

			data.put("error", "Please Enter The NOK Name");

			return data;

		}
		if (nok_name != null && !nok_name.trim().equals("")) {
			if (!valid.isOnlyAlphabet(nok_name)) {
				data.put("error", "NOK Name " + valid.isOnlyAlphabetMSG);
				return data;
			}

			if (!valid.isvalidLength(nok_name, valid.nameMax, valid.nameMin)) {
				data.put("error", "NOK Name " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (nok_relation == null || nok_relation.equals("0")) {

			data.put("error", "Please Select The NOK Relation");

			return data;

		}

		if (nok_relation_otherBool != null && nok_relation_otherBool.equals("OTHERS")) {

			if (nok_relation_other == null || nok_relation_other.trim().equals("")) {

				data.put("error", "Please Enter The NOK Relation Other");

				return data;

			}
			if (nok_relation_other != null && !nok_relation_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(nok_relation_other)) {
					data.put("error", "NOK Relation Other " + valid.isOnlyAlphabetMSG);
					return data;
				}

				if (!valid.isvalidLength(nok_relation_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "NOK Relation Other " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			nok_relation_other = null;

		}

		if (nok_country == null || nok_country.equals("0")) {

			data.put("error", "Please Select The NOK Country");

			return data;

		}

		if (ctry_otherV != null && ctry_otherV.equals("OTHERS")) {

			if (ctry_other == null || ctry_other.trim().equals("")) {

				data.put("error", "Please Enter The NOK Country");

				return data;

			}
			if (ctry_other != null && !ctry_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(ctry_other)) {
					data.put("error", "NOK Country " + valid.isOnlyAlphabetMSG);
					return data;
				}

				if (!valid.isvalidLength(ctry_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "NOK Country " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			ctry_other = null;

		}

		if (nok_state == null || nok_state.equals("0")) {

			data.put("error", "Please Select The NOK State");

			return data;

		}

		if (st_otherV != null && st_otherV.equals("OTHERS")) {

			if (st_other == null || st_other.trim().equals("")) {

				data.put("error", "Please Enter The NOK State");

				return data;

			}
			if (st_other != null && !st_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(st_other)) {
					data.put("error", "NOK State " + valid.isOnlyAlphabetMSG);
					return data;
				}

				if (!valid.isvalidLength(st_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "NOK State " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			st_other = null;

		}

		if (nok_district == null || nok_district.equals("0")) {

			data.put("error", "Please Select The NOK District");

			return data;

		}

		if (dist_otherV != null && dist_otherV.equals("OTHERS")) {

			if (dist_other == null || dist_other.trim().equals("")) {

				data.put("error", "Please Enter The NOK District");

				return data;

			}
			if (dist_other != null && !dist_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(dist_other)) {
					data.put("error", "NOK District " + valid.isOnlyAlphabetMSG);
					return data;
				}

				if (!valid.isvalidLength(dist_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "NOK District " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			dist_other = null;

		}

		if (nok_tehsil == null || nok_tehsil.equals("0")) {

			data.put("error", "Please Select The NOK Tehsil");

			return data;

		}

		if (tehsil_otherV != null && tehsil_otherV.equals("OTHERS")) {

			if (tehsil_other == null || tehsil_other.trim().equals("")) {

				data.put("error", "Please Enter The NOK Tehsil");

				return data;

			}
			if (tehsil_other != null && !tehsil_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(tehsil_other)) {
					data.put("error", "NOK Tehsil " + valid.isOnlyAlphabetMSG);
					return data;
				}

				if (!valid.isvalidLength(tehsil_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "NOK Tehsil " + valid.isValidLengthMSG);
					return data;
				}
			}
		} else {

			tehsil_other = null;

		}

		if (nok_village == null || nok_village.equals("0")) {

			data.put("error", "Please Select The NOK Village/Town/City");

			return data;

		}
		if (nok_village != null && !nok_village.trim().equals("")) {
			if (!valid.isOnlyAlphabet(nok_village)) {
				data.put("error", "NOK Village/Town/City " + valid.isOnlyAlphabetMSG);
				return data;
			}

			if (!valid.isvalidLength(nok_village, valid.nameMax, valid.nameMin)) {
				data.put("error", "NOK Village/Town/City " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (nok_pin == null || nok_pin.trim().equals("") || nok_pin.length() < 6) {

			data.put("error", "Please Enter Valid NOK Pin");

			return data;

		}
		if (nok_pin != null && !nok_pin.trim().equals("")) {
			if (valid.isOnlyNumer(nok_pin) == true) {
				data.put("error", valid.isOnlyNumerMSG + " NOK Pin");
				return data;
			}
			if (!valid.isvalidLength(nok_pin, valid.pin_noMax, valid.pin_noMin)) {
				data.put("error", "NOK Pin " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (nok_near_railway_station == null || nok_near_railway_station.trim().equals("")) {

			data.put("error", "Please Enter The NOK Nearest Railway Station");

			return data;

		}
		if (nok_near_railway_station != null && !nok_near_railway_station.trim().equals("")) {
			if (!valid.isOnlyAlphabetNumeric(nok_near_railway_station)) {
				data.put("error", valid.isOnlyAlphabetNumericMSG + " NOK Nearest Railway Station");
				return data;
			}
			if (!valid.isvalidLength(nok_near_railway_station, valid.authorityMax, valid.authorityMin)) {
				data.put("error", "NOK Nearest Railway Station " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (nok_mobile_no == null || nok_mobile_no.trim().equals("") || nok_mobile_no.length() < 10) {

			data.put("error", "Please Enter Valid NOK's Mobile No");

			return data;

		}
		if (nok_mobile_no != null && !nok_mobile_no.trim().equals("")) {
			if (valid.isOnlyNumer(nok_mobile_no) == true) {
				data.put("error", valid.isOnlyNumerMSG + " NOK's Mobile No");
				return data;
			}

			if (!valid.isValidMobileNo(nok_mobile_no)) {
				data.put("error", valid.isValidMobileNoMSG);
				return data;
			}
		}

		String nok_mobile_no1 = request.getParameter("nok_mobile_no");
		TB_CENSUS_ADDRESS addr = new TB_CENSUS_ADDRESS();

		TB_CENSUS_NOK nok = new TB_CENSUS_NOK();

		try {
int check_add=check_duplicate(comm_id,"TB_CENSUS_ADDRESS");
			if (check_add == 0) {

				addr.setPre_country(Integer.parseInt(pre_domicile_Country));

				addr.setPre_tesil(Integer.parseInt(pre_domicile_tehsil));

				addr.setPermanent_country(Integer.parseInt(per_home_addr_Country));

				addr.setPresent_country(Integer.parseInt(pers_addr_Country));

				addr.setPre_state(Integer.parseInt(pre_domicile_state));

				addr.setPre_district(Integer.parseInt(pre_domicile_district));

				addr.setPermanent_district(Integer.parseInt(per_home_addr_district));

				addr.setPermanent_state(Integer.parseInt(per_home_addr_state));

				addr.setPermanent_tehsil(Integer.parseInt(per_home_addr_tehsil));

				addr.setPermanent_village(per_home_addr_village);

				addr.setPermanent_pin_code(Integer.parseInt(per_home_addr_pin));

				addr.setPermanent_near_railway_station(per_home_addr_rail);

				addr.setPresent_district(Integer.parseInt(pers_addr_district));

				addr.setPresent_state(Integer.parseInt(pers_addr_state));

				addr.setPresent_tehsil(Integer.parseInt(pers_addr_tehsil));

				addr.setPresent_village(pers_addr_village);

				addr.setPresent_pin_code(Integer.parseInt(pers_addr_pin));

				addr.setPresent_near_railway_station(pers_addr_rail);

				addr.setSpouse_country(Integer.parseInt(spouse_addr_Country));

				addr.setSpouse_district(Integer.parseInt(spouse_addr_district));

				addr.setSpouse_state(Integer.parseInt(spouse_addr_state));

				addr.setSpouse_tehsil(Integer.parseInt(spouse_addr_tehsil));

				addr.setSpouse_village(spouse_addr_village);

				addr.setSpouse_pin_code(Integer.parseInt(spouse_addr_pin));

				addr.setSpouse_near_railway_station(spouse_addr_rail);

				addr.setStn_hq_sus_no(Stn_HQ_sus_no);

				addr.setSpouse_addr_district_other(spouse_addr_district_other);

				addr.setSpouse_addr_state_other(spouse_addr_state_other);

				addr.setSpouse_addr_country_other(spouse_addr_country_other);

				addr.setSpouse_addr_tehsil_other(spouse_addr_tehsil_other);

				addr.setPermanent_border_area(permanent_border_area);

				addr.setPermanent_rural_urban_semi(perm_rural_urban);

				addr.setCen_id(p_id);

				addr.setComm_id(comm_id);

				addr.setCreated_by(username);

				addr.setCreated_date(date);

				addr.setPre_country_other(pre_domicile_Country_other);

				addr.setPre_domicile_state_other(pre_domicile_state_other);

				addr.setPre_domicile_district_other(pre_domicile_district_other);

				addr.setPre_domicile_tesil_other(pre_domicile_tehsil_other);

				addr.setPer_home_addr_country_other(per_home_addr_Country_other);

				addr.setPer_home_addr_state_other(per_home_addr_state_other);

				addr.setPer_home_addr_district_other(per_home_addr_district_other);

				addr.setPer_home_addr_tehsil_other(per_home_addr_tehsil_other);

				addr.setPers_addr_country_other(pers_addr_Country_other);

				addr.setPers_addr_state_other(pers_addr_state_other);

				addr.setPers_addr_district_other(pers_addr_district_other);

				addr.setPers_addr_tehsil_other(pers_addr_tehsil_other);

				addr.setPresent_rural_urban_semi(pers_rural_urban);

				addr.setSpouse_rural_urban_semi(spouse_rural_urban);

				addr_ch_id = (int) sessionhql.save(addr);

				sessionhql.flush();

				sessionhql.clear();

			} else {

				String hql = "update TB_CENSUS_ADDRESS set modified_by=:modified_by ,modified_date=:modified_date "

						+ ",pre_state=:pre_state,pre_district=:pre_district,permanent_state=:permanent_state,permanent_district=:permanent_district,permanent_village=:permanent_village,permanent_pin_code=:permanent_pin_code"

						+ ",permanent_near_railway_station=:permanent_near_railway_station,permanent_tehsil=:permanent_tehsil,present_state=:present_state,present_district=:present_district,present_village=:present_village,present_pin_code=:present_pin_code"

						+ ",present_near_railway_station=:present_near_railway_station,present_tehsil=:present_tehsil,"

						+ "pre_country_other=:pre_country_other,pre_domicile_state_other=:pre_domicile_state_other,pre_domicile_district_other=:pre_domicile_district_other,pre_domicile_tesil_other=:pre_domicile_tesil_other,per_home_addr_country_other=:per_home_addr_country_other,per_home_addr_state_other=:per_home_addr_state_other,"

						+ "per_home_addr_district_other=:per_home_addr_district_other,per_home_addr_tehsil_other=:per_home_addr_tehsil_other,pers_addr_country_other=:pers_addr_country_other,pers_addr_state_other=:pers_addr_state_other,pers_addr_district_other=:pers_addr_district_other,pers_addr_tehsil_other=:pers_addr_tehsil_other,"

						+ "spouse_state=:spouse_state,spouse_district=:spouse_district,spouse_village=:spouse_village,spouse_pin_code=:spouse_pin_code"

						+ ",spouse_near_railway_station=:spouse_near_railway_station, stn_hq_sus_no=:stn_hq_sus_no,spouse_tehsil=:spouse_tehsil,spouse_country=:spouse_country"

						+ ",pre_country=:pre_country,pre_tesil=:pre_tesil,permanent_country=:permanent_country,present_country=:present_country,permanent_border_area=:permanent_border_area,"

						+ "spouse_addr_district_other=:spouse_addr_district_other,spouse_addr_state_other=:spouse_addr_state_other,spouse_addr_country_other=:spouse_addr_country_other,spouse_addr_tehsil_other=:spouse_addr_tehsil_other"

						+ ",spouse_rural_urban_semi=:spouse_rural_urban_semi,present_rural_urban_semi=:present_rural_urban_semi,permanent_rural_urban_semi=:permanent_rural_urban_semi where  cen_id=:cen_id";

				Query query = sessionhql.createQuery(hql).setInteger("pre_state", Integer.parseInt(pre_domicile_state))

						.setInteger("pre_district", Integer.parseInt(pre_domicile_district))

						.setInteger("permanent_state", Integer.parseInt(per_home_addr_state))

						.setInteger("permanent_district", Integer.parseInt(per_home_addr_district))

						.setString("permanent_village", per_home_addr_village)

						.setInteger("permanent_pin_code", Integer.parseInt(per_home_addr_pin))

						.setString("permanent_near_railway_station", per_home_addr_rail)

						.setInteger("permanent_tehsil", Integer.parseInt(per_home_addr_tehsil))

						.setInteger("present_state", Integer.parseInt(pers_addr_state))

						.setInteger("present_district", Integer.parseInt(pers_addr_district))

						.setString("present_village", pers_addr_village)

						.setInteger("present_pin_code", Integer.parseInt(pers_addr_pin))

						.setString("present_near_railway_station", pers_addr_rail)

						.setInteger("present_tehsil", Integer.parseInt(pers_addr_tehsil))

						.setString("permanent_rural_urban_semi", perm_rural_urban)

						.setString("present_rural_urban_semi", pers_rural_urban)

						.setString("spouse_rural_urban_semi", spouse_rural_urban)

						.setInteger("spouse_state", Integer.parseInt(spouse_addr_state))

						.setInteger("spouse_district", Integer.parseInt(spouse_addr_district))

						.setString("spouse_village", spouse_addr_village)

						.setInteger("spouse_pin_code", Integer.parseInt(spouse_addr_pin))

						.setString("spouse_near_railway_station", spouse_addr_rail)

						.setString("stn_hq_sus_no", Stn_HQ_sus_no)

						.setInteger("spouse_tehsil", Integer.parseInt(spouse_addr_tehsil))

						.setInteger("spouse_country", Integer.parseInt(spouse_addr_Country))

						.setString("spouse_addr_district_other", spouse_addr_district_other)

						.setString("spouse_addr_state_other", spouse_addr_state_other)

						.setString("spouse_addr_country_other", spouse_addr_country_other)

						.setString("spouse_addr_tehsil_other", spouse_addr_tehsil_other)

						.setInteger("pre_country", Integer.parseInt(pre_domicile_Country))

						.setInteger("pre_tesil", Integer.parseInt(pre_domicile_tehsil))

						.setInteger("permanent_country", Integer.parseInt(per_home_addr_Country))

						.setInteger("present_country", Integer.parseInt(pers_addr_Country))

						.setString("permanent_border_area", permanent_border_area).setInteger("cen_id", p_id)

						.setString("pre_country_other", pre_domicile_Country_other)

						.setString("pre_domicile_state_other", pre_domicile_state_other)

						.setString("pre_domicile_district_other", pre_domicile_district_other)

						.setString("pre_domicile_tesil_other", pre_domicile_tehsil_other)

						.setString("per_home_addr_country_other", per_home_addr_Country_other)

						.setString("per_home_addr_state_other", per_home_addr_state_other)

						.setString("per_home_addr_district_other", per_home_addr_district_other)

						.setString("per_home_addr_tehsil_other", per_home_addr_tehsil_other)

						.setString("pers_addr_country_other", pers_addr_Country_other)

						.setString("pers_addr_state_other", pers_addr_state_other)

						.setString("pers_addr_district_other", pers_addr_district_other)

						.setString("pers_addr_tehsil_other", pers_addr_tehsil_other).setString("modified_by", username)

						.setTimestamp("modified_date", new Date());

				int update = query.executeUpdate() > 0 ? 1 : 0;

				if (update == 1)

					data.put("updated", "Data Updated Successfully");

				else

					data.put("error", "Data Not Updated");

				sessionhql.flush();

				sessionhql.clear();

			}
int check_nok=check_duplicate(comm_id,"TB_CENSUS_NOK") ;
			if (check_nok == 0) {

				nok.setNok_district(Integer.parseInt(nok_district));

				nok.setNok_mobile_no(nok_mobile_no1);

				nok.setNok_name(nok_name);

				nok.setNok_near_railway_station(nok_near_railway_station);

				nok.setNok_pin(Integer.parseInt(nok_pin));

				nok.setNok_state(Integer.parseInt(nok_state));

				nok.setNok_tehsil(Integer.parseInt(nok_tehsil));

				nok.setNok_village(nok_village);

				nok.setNok_country(Integer.parseInt(nok_country));

				nok.setNok_relation(Integer.parseInt(nok_relation));

				nok.setCensus_id(p_id);

				nok.setComm_id(comm_id);

				nok.setNok_rural_urban_semi(nok_rural_urban);

				nok.setCreated_by(username);

				nok.setCreated_date(date);

				nok.setCtry_other(ctry_other);

				nok.setSt_other(st_other);

				nok.setDist_other(dist_other);

				nok.setTehsil_other(tehsil_other);

				nok.setRelation_other(nok_relation_other);

				nok_ch_id = (int) sessionhql.save(nok);

				sessionhql.flush();

				sessionhql.clear();

			} else {

				String hql1 = "update TB_CENSUS_NOK set modified_by=:modified_by ,modified_date=:modified_date ,nok_name=:nok_name, nok_relation=:nok_relation"

						+ ",nok_country=:nok_country ,nok_state=:nok_state ,nok_district=:nok_district ,nok_tehsil=:nok_tehsil ,nok_village=:nok_village ,nok_pin=:nok_pin"

						+ ",nok_near_railway_station=:nok_near_railway_station , nok_mobile_no=pgp_sym_encrypt(:nok_mobile_no,current_setting('miso.version')) , nok_rural_urban_semi=:nok_rural_urban_semi "

						+ " ,ctry_other=:ctry_other,st_other=:st_other ,dist_other=:dist_other ,tehsil_other=:tehsil_other,relation_other=:relation_other "

						+ "where census_id=:census_id ";

				Query query1 = sessionhql.createQuery(hql1).setString("nok_name", nok_name)

						.setInteger("nok_relation", Integer.parseInt(nok_relation))

						.setInteger("nok_country", Integer.parseInt(nok_country))

						.setInteger("nok_state", Integer.parseInt(nok_state))

						.setInteger("nok_district", Integer.parseInt(nok_district))

						.setInteger("nok_tehsil", Integer.parseInt(nok_tehsil)).setString("ctry_other", ctry_other)

						.setString("st_other", st_other).setString("dist_other", dist_other)

						.setString("tehsil_other", tehsil_other).setString("nok_village", nok_village)

						.setInteger("nok_pin", Integer.parseInt(nok_pin))

						.setString("nok_near_railway_station", nok_near_railway_station)

						.setString("nok_mobile_no", nok_mobile_no1).setString("nok_rural_urban_semi", nok_rural_urban)

						.setInteger("census_id", p_id).setString("relation_other", nok_relation_other)

						.setString("modified_by", username).setTimestamp("modified_date", new Date());

				int update = query1.executeUpdate() > 0 ? 1 : 0;

				if (update == 1)

					data.put("updated", "Data Updated Successfully");

				else

					data.put("error", "Data Not Updated");

			}

			String hql = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by ,modified_date=:modified_date ,org_state=:org_state, "

					+ "org_tehsil=:org_tehsil,org_country=:org_country,org_district=:org_district,org_domicile_country_other=:org_domicile_country_other,org_domicile_state_other=:org_domicile_state_other,"

					+ "org_domicile_district_other=:org_domicile_district_other,org_domicile_tehsil_other=:org_domicile_tehsil_other"

					+ "  where  id=:id";

			Query query = sessionhql.createQuery(hql).setInteger("org_state", Integer.parseInt(org_domicile_state))

					.setInteger("org_tehsil", Integer.parseInt(org_domicile_tehsil))

					.setInteger("org_country", Integer.parseInt(org_domicile_country))

					.setString("org_domicile_country_other", org_domicile_Country_other)

					.setString("org_domicile_state_other", org_domicile_state_other)

					.setString("org_domicile_district_other", org_domicile_district_other)

					.setString("org_domicile_tehsil_other", org_domicile_tehsil_other)

					.setInteger("org_district", Integer.parseInt(org_domicile_district)).setInteger("id", p_id)

					.setString("modified_by", username).setTimestamp("modified_date", date);

			int update = query.executeUpdate() > 0 ? 1 : 0;

			if (update == 1)

				data.put("updated", "Data Updated Successfully");

			else

				data.put("error", "Data Not Updated");

			tx.commit();

			data.put("nok_ch_id", String.valueOf(nok_ch_id));

			data.put("addr_ch_id", String.valueOf(addr_ch_id));

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				data.put("error", "Data Not Updated");

			} catch (RuntimeException rbe) {

				data.put("error", "Data Not Updated");

			}

		} finally {

			if (sessionhql != null) {

				sessionhql.close();

			}

		}

		return data;

	}

	@RequestMapping(value = "/admin/address_details_GetData_census", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_ADDRESS> address_details_GetData_census(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("p_id1"));

		String approoval_status = request.getParameter("app_status");

		String hqlUpdate = " from TB_CENSUS_ADDRESS where cen_id=:census_id  ";

		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {

			hqlUpdate += " and status=1 ";

		} else {

			hqlUpdate += " and status=0 ";
		}

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id);

		List<TB_CENSUS_ADDRESS> list = (List<TB_CENSUS_ADDRESS>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	@RequestMapping(value = "/admin/Nok_details_GetData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_NOK> Nok_details_GetData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("p_id1"));

		String approoval_status = request.getParameter("app_status");

		String hqlUpdate = " from TB_CENSUS_NOK where census_id=:census_id ";

		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {

			hqlUpdate += " and status=1 ";

		} else {

			hqlUpdate += " and status=0 ";

		}

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id);

		List<TB_CENSUS_NOK> list = (List<TB_CENSUS_NOK>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	////////////////////////////// Language

	@RequestMapping(value = "/admin/language_detail_action", method = RequestMethod.POST)

	public @ResponseBody String language_detail_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		String language = request.getParameter("language");

		String lang_std = request.getParameter("lang_std");

		String lang_ch_id = request.getParameter("lang_ch_id");

		String p_id = request.getParameter("p_id");

		String other_language = request.getParameter("other_language");

		String other_lang_std = request.getParameter("other_lang_std");

		String rvalue = "";

		if (language == null || language.equals("0")) {

			return "Please Select The Language";

		}

		if (lang_std == null || lang_std.equals("0")) {

			return "Please Select The Language Standard";

		}
		if (other_language != null && !other_language.trim().equals("")) {

			if (!valid.isOnlyAlphabet(other_language)) {
				return " language Other " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(other_language, valid.nameMax, valid.nameMin)) {
				return " language Other " + valid.isValidLengthMSG;
			}
		}
		if (other_lang_std != null && !other_lang_std.trim().equals("")) {

			if (!valid.isOnlyAlphabet(other_lang_std)) {
				return " language Standard Other " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(other_lang_std, valid.nameMax, valid.nameMin)) {
				return " language Standard Other " + valid.isValidLengthMSG;
			}
		}
		try {
			TB_CENSUS_LANGUAGE lang = new TB_CENSUS_LANGUAGE();
				Query q0 = sessionhql.createQuery("select count(id) from TB_CENSUS_LANGUAGE where cen_id=:p_id and language=:language and other_language=:other_language");
				
				q0.setInteger("p_id", Integer.parseInt(p_id));  
						
				q0.setLong("language",Integer.parseInt(language));
				
				q0.setString("other_language",other_language);
					
				Long c = (Long) q0.uniqueResult();
				

				if (Integer.parseInt(lang_ch_id) == 0) {

					

					lang.setCen_id(Integer.parseInt(p_id));

					lang.setLanguage(Integer.parseInt(language));

					lang.setLang_std(Integer.parseInt(lang_std));

					lang.setF_exam_pass("");

					lang.setForeign_language(0);

					lang.setF_lang_prof(0);

					lang.setCreated_by(username);

					lang.setCreated_on(date);

					lang.setOther_language(other_language);

					lang.setOther_lang_std(other_lang_std);

					lang.setComm_id(new BigInteger(request.getParameter("com_id")));

					if(c==0) {
					
					int id = (int) sessionhql.save(lang);

					rvalue = Integer.toString(id);
					}
					else {
						rvalue = "Data Already Exists";
					}
					

				} else {
					Query q01 = sessionhql.createQuery("select count(id) from TB_CENSUS_LANGUAGE where cen_id=:p_id and language=:language and other_language=:other_language and lang_std=:lang_std");
					
					q01.setInteger("p_id", Integer.parseInt(p_id));  
							
					q01.setLong("language",Integer.parseInt(language));
					
					q01.setString("other_language",other_language);
					
					q01.setLong("lang_std",Integer.parseInt(lang_std));
					
					Long c1 = (Long) q01.uniqueResult();
					
					
					if(c1==0) {
					String hql = "update TB_CENSUS_LANGUAGE set modify_by=:modify_by ,modify_on=:modify_on ,language=:language,lang_std=:lang_std,"

							+ "other_language=:other_language,other_lang_std=:other_lang_std  where  id=:id";

					Query query = sessionhql.createQuery(hql).setInteger("language", Integer.parseInt(language))

							.setInteger("lang_std", Integer.parseInt(lang_std))

							.setInteger("id", Integer.parseInt(lang_ch_id)).setString("modify_by", username)

							.setTimestamp("modify_on", date).setString("other_language", other_language)

							.setString("other_lang_std", other_lang_std);

					rvalue = query.executeUpdate() > 0 ? "Data Updated Successfully" : "Data Not Updated";
					
					
				}
					else {
						rvalue = "Data Already Exists";
					}
				}

				tx.commit();

			} catch (RuntimeException e) {

				try {

					tx.rollback();

					rvalue = "Data Not Updated";

				} catch (RuntimeException rbe) {

					rvalue = "Data Not Updated";

				}

			} finally {

				if (sessionhql != null) {

					sessionhql.close();

				}

			}

			return rvalue;

		}

	@RequestMapping(value = "/admin/foreginlanguage_detail_action", method = RequestMethod.POST)

	public @ResponseBody String foreginlanguage_detail_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		String language = request.getParameter("language");

		String lang_prof = request.getParameter("lang_prof");

		String lang_std = request.getParameter("lang_std");

		String exam_pass = request.getParameter("exam_pass");

		String lang_ch_id = request.getParameter("lang_ch_id");

		String p_id = request.getParameter("p_id");

		String f_other_language = request.getParameter("f_other_language");

		String f_other_lang_std = request.getParameter("f_other_lang_std");

		String f_other_prof = request.getParameter("f_other_prof");


		String rvalue = "";

		int currentYear = date.getYear() + 1900;

		String dateofBirthyear = request.getParameter("dateofBirthyear");

		if (language == null || language.equals("0")) {

			return "Please Select The Language";

		}

		if (language.equals("OTHERS") && f_other_language.trim().equals("")

				|| language == "OTHERS" && f_other_language == "") {

			return "Please Enter Other Language";

		}
		if (f_other_language != null && !f_other_language.trim().equals("")) {

			if (!valid.isOnlyAlphabet(f_other_language)) {
				return " language  Other " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(f_other_language, valid.nameMax, valid.nameMin)) {
				return " language  Other " + valid.isValidLengthMSG;
			}
		}
		if (lang_std == null || lang_std.equals("0")) {

			return "Please Select The Language Standard";

		}

		if (lang_std.equals("OTHERS") && f_other_lang_std.trim().equals("")

				|| lang_std == "OTHERS" && f_other_lang_std == "") {

			return "Please Enter Other Language Standard";

		}
		if (f_other_lang_std != null && !f_other_lang_std.trim().equals("")) {

			if (!valid.isOnlyAlphabet(f_other_lang_std)) {
				return " language Standard Other" + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(f_other_lang_std, valid.nameMax, valid.nameMin)) {
				return " language Standard Other " + valid.isValidLengthMSG;
			}
		}
		if (lang_prof == null || lang_prof.equals("0")) {

			return "Please Select The Language Proficiency";

		}

		if (lang_prof.equals("OTHERS") && f_other_prof.trim().equals("")

				|| lang_prof == "OTHERS" && f_other_prof == "") {

			return "Please Enter Other Language Proficiency";

		}
		if (f_other_prof != null && !f_other_prof.trim().equals("")) {

			if (!valid.isOnlyAlphabet(f_other_prof)) {
				return " language Proficiency Other " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(f_other_prof, valid.nameMax, valid.nameMin)) {
				return " language Proficiency Other " + valid.isValidLengthMSG;
			}
		}
		if (exam_pass == null || exam_pass.trim().equals("")) {

			return "Please Enter  Exam Passed";

		}
		if (exam_pass != null && !exam_pass.trim().equals("")) {
			if (valid.isOnlyNumer(exam_pass) == true) {
				return " Year " + valid.isOnlyNumerMSG;
			}
			if (!valid.YearLength(exam_pass)) {
				return valid.YearMSG;
			}
		}
		try {
			
		      Query q0 = sessionhql.createQuery("select count(id) from TB_CENSUS_LANGUAGE where cen_id=:p_id and foreign_language=:language and f_other_language=:f_other_language");
					
					q0.setInteger("p_id", Integer.parseInt(p_id));  
							
					q0.setLong("language",Integer.parseInt(language));
					
					q0.setString("f_other_language",f_other_language);
						
					Long c = (Long) q0.uniqueResult();

					if (Integer.parseInt(lang_ch_id) == 0) {

						TB_CENSUS_LANGUAGE lang = new TB_CENSUS_LANGUAGE();

						lang.setCen_id(Integer.parseInt(p_id));

						lang.setComm_id( new BigInteger(request.getParameter("com_id")));

						lang.setLanguage(0);

						lang.setLang_std(0);

						lang.setF_exam_pass((exam_pass));

						lang.setLang_std(Integer.parseInt(lang_std));

						lang.setForeign_language(Integer.parseInt(language));

						lang.setF_lang_prof(Integer.parseInt(lang_prof));

						lang.setCreated_by(username);

						lang.setCreated_on(date);

						lang.setF_other_language(f_other_language);

						lang.setF_other_lang_std(f_other_lang_std);

						lang.setF_other_prof(f_other_prof);
						
						if(c==0) {
							int id = (int) sessionhql.save(lang);

							rvalue = Integer.toString(id);
							}
							else {
								rvalue = "Data Already Exists";
							}

						

					} else {
		   Query q01 = sessionhql.createQuery("select count(id) from TB_CENSUS_LANGUAGE where cen_id=:p_id and foreign_language=:language and lang_std=:lang_std and f_other_language=:f_other_language and f_other_prof=:f_other_prof"
		   		+ " and f_lang_prof=:lang_prof and f_other_lang_std=:f_other_lang_std and f_other_prof=:f_other_prof and f_exam_pass=:exam_pass");
						
						q01.setInteger("p_id", Integer.parseInt(p_id));  
								
						q01.setLong("language",Integer.parseInt(language));
						
						q01.setLong("lang_std",Integer.parseInt(lang_std));
						
						q01.setString("f_other_language",f_other_language);
						
						q01.setLong("lang_prof",Integer.parseInt(lang_prof));
						
						q01.setString("f_other_prof",f_other_prof);
						
						q01.setString("f_other_lang_std",f_other_lang_std);
						
						q01.setString("exam_pass",exam_pass);
										
						Long c1 = (Long) q01.uniqueResult();
						
						if(c1==0) {

						String hql = "update TB_CENSUS_LANGUAGE set modify_by=:modify_by ,modify_on=:modify_on ,foreign_language=:foreign_language,f_lang_prof=:f_lang_prof,lang_std=:lang_std"

								+ " ,f_exam_pass=:f_exam_pass,f_other_language=:f_other_language,f_other_lang_std=:f_other_lang_std,f_other_prof=:f_other_prof where  id=:id";

						Query query = sessionhql.createQuery(hql).setInteger("foreign_language", Integer.parseInt(language))

								.setInteger("lang_std", Integer.parseInt(lang_std))

								.setInteger("f_lang_prof", Integer.parseInt(lang_prof)).setString("f_exam_pass", exam_pass)

								.setInteger("id", Integer.parseInt(lang_ch_id)).setString("modify_by", username)

								.setTimestamp("modify_on", date).setString("f_other_language", f_other_language)

								.setString("f_other_lang_std", f_other_lang_std).setString("f_other_prof", f_other_prof);

						rvalue = query.executeUpdate() > 0 ? "Data Updated Successfully" : "Data Not Updated";
						}
						else {
							rvalue = "Data Already Exists";
						}
					}

					tx.commit();

				} catch (RuntimeException e) {

					try {

						tx.rollback();

						rvalue = "Data Not Updated";

					} catch (RuntimeException rbe) {

						rvalue = "Data Not Updated";

					}

				} finally {

					if (sessionhql != null) {

						sessionhql.close();

					}
				}
				return rvalue;
			}

	@RequestMapping(value = "/admin/getlanguagedetailsData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_LANGUAGE> getlanguagedetailsData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("p_id"));

		String approoval_status = request.getParameter("app_status");

		String hqlUpdate = " from TB_CENSUS_LANGUAGE where cen_id=:census_id   ";

		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {

			hqlUpdate += " and status=1 ";

		} else {

			hqlUpdate += " and status=0 ";

		}

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id);

		List<TB_CENSUS_LANGUAGE> list = (List<TB_CENSUS_LANGUAGE>) query.list();

		tx.commit();

		sessionHQL.close();
		return list;

	}

	@RequestMapping(value = "/admin/language_delete_action", method = RequestMethod.POST)

	public @ResponseBody String language_delete_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("lang_ch_id"));

		try {

			String hqlUpdate = "delete from TB_CENSUS_LANGUAGE where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();

			tx.commit();

			sessionHQL.close();

			if (app > 0) {

				msg = "1";

			} else {

				msg = "0";

			}

		} catch (Exception e) {

		}

		return msg;

	}

	////////////////////////////// End Language

	// ************************************ START FOREGIN COUNTRY
	// DETAIL*******************************************

	@RequestMapping(value = "/admin/foregin_country_action", method = RequestMethod.POST)

	public @ResponseBody String foregin_country_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		String country = request.getParameter("country");

		String ot_country = request.getParameter("other_ct");

		String period = request.getParameter("period");

		String from_dt = request.getParameter("from_dt");

		String to_dt = request.getParameter("to_dt");

		String purpose_visit = request.getParameter("purpose_visit");

		String other_purpose_visit = request.getParameter("other_purpose_visit");

		String foregin_country_ch_id = request.getParameter("foregin_country_ch_id");

		String f_id = request.getParameter("f_id");

		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

		String rvalue = "";

		if (country == null || country.equals("0")) {

			return "Please Select The Country Visited";

		}
		if (ot_country != null && !ot_country.trim().equals("")) {

			if (!valid.isOnlyAlphabet(ot_country)) {
				return " Country Visited Other " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(ot_country, valid.nameMax, valid.nameMin)) {
				return " Country Visited Other " + valid.isValidLengthMSG;
			}
		}
		if (from_dt == null || from_dt.trim().equals("") || from_dt.equals("DD/MM/YYYY")) {

			return "Please Enter The From Date";

		}
		Date fm_dt = null;
		if (!from_dt.trim().equals("") && !from_dt.equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(from_dt)) {
				return valid.isValidDateMSG + " of From ";
			} else {
				fm_dt = format.parse(from_dt);
			}
		}
		if (to_dt == null || to_dt.trim().equals("") || to_dt.equals("DD/MM/YYYY")) {

			return "Please Enter The To Date";

		}
		Date t_dt = null;
		if (!to_dt.trim().equals("") && !to_dt.equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(to_dt)) {
				return valid.isValidDateMSG + " of To ";
			} else {
				t_dt = format.parse(to_dt);
			}
		}
		if (format.parse(to_dt).compareTo(format.parse(from_dt)) < 0) {

			return "To Date  Should Be Greater Than Or Equal To From Date";

		}

		if (period == null || period.trim().equals("")) {

			return "Please Enter valid From And To Date";

		}
		if (period != null || !period.trim().equals("")) {
			if (!valid.isvalidLength(period, valid.nameMax, valid.nameMin)) {
				return " Duration " + valid.isValidLengthMSG;
			}
		}
		if (!period.equals(mcommon.calculate_duration(format.parse(from_dt), format.parse(to_dt)))) {

			return "Please Enter valid From And To Date";

		}

		if (purpose_visit == null || purpose_visit.trim().equals("0")) {

			return "Please Enter Purpose To Visit";

		}
		if (other_purpose_visit != null && !other_purpose_visit.trim().equals("")) {

			if (!valid.isOnlyAlphabet(other_purpose_visit)) {
				return "Purpose of Visit Other " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(other_purpose_visit, valid.nameMax, valid.nameMin)) {
				return "Purpose of Visit Other " + valid.isValidLengthMSG;
			}
		}
		TB_CENSUS_FOREIGN_COUNTRY foregin = new TB_CENSUS_FOREIGN_COUNTRY();

	try {
		
		
		
		
			Query q0 = sessionhql.createQuery("select count(id) from TB_CENSUS_FOREIGN_COUNTRY where comm_id=:comm_id and from_dt=:fm_dt and to_dt=:t_dt");

			q0.setBigInteger("comm_id", comm_id);
//			q0.setInteger("country", Integer.parseInt(country)); // Use setInteger for country
			q0.setTimestamp("fm_dt", fm_dt);
			q0.setTimestamp("t_dt", t_dt);

			Long c = (Long) q0.uniqueResult();

					
						
						if (Integer.parseInt(foregin_country_ch_id) == 0) {

							foregin.setCountry(Integer.parseInt(country));

							foregin.setOther_country(ot_country);

							foregin.setPeriod(period);

							foregin.setStatus(0);

							foregin.setFrom_dt(fm_dt);

							foregin.setTo_dt(t_dt);

							foregin.setPurpose_visit(Integer.parseInt(purpose_visit));

							foregin.setOther_purpose_visit(other_purpose_visit);

							foregin.setCen_id(Integer.parseInt(f_id));

							foregin.setCreated_by(username);

							foregin.setCreated_on(date);

							foregin.setComm_id(comm_id);
							
							if(c==0) {

							int id = (int) sessionhql.save(foregin);

							rvalue = Integer.toString(id);
							
						}
							else {
								rvalue = "Data Already Exists";
							}				
								

						} else {
							
							Query q01 = sessionhql.createQuery("select count(id) from TB_CENSUS_FOREIGN_COUNTRY where comm_id=:comm_id and from_dt=:fm_dt and to_dt=:t_dt ");
							
							q01.setBigInteger("comm_id",comm_id);  
							
//							q01.setInteger("country",Integer.parseInt(country));
									
							q01.setTimestamp("fm_dt",fm_dt);
							
							q01.setTimestamp("t_dt",t_dt);
							
							Long c1 = (Long) q01.uniqueResult();
							
							
							
							if(c1==0) {
							

							TB_CENSUS_FOREIGN_COUNTRY obj2 = (TB_CENSUS_FOREIGN_COUNTRY) sessionhql.get(

									TB_CENSUS_FOREIGN_COUNTRY.class,

									Integer.parseInt(request.getParameter("foregin_country_ch_id")));


							obj2.setCountry(Integer.parseInt(request.getParameter("country")));

							obj2.setPeriod(request.getParameter("period"));

							obj2.setFrom_dt(format.parse(request.getParameter("from_dt")));

							obj2.setTo_dt(format.parse(request.getParameter("to_dt")));

							obj2.setPurpose_visit(Integer.parseInt(request.getParameter("purpose_visit")));

							obj2.setOther_country(ot_country);

							obj2.setOther_purpose_visit(other_purpose_visit);

							obj2.setModified_by(username);

							obj2.setModified_date(new Date());

							sessionhql.update(obj2);

							rvalue = "Data Updated Successfully";

						}
							else {
								rvalue = "Data Already Exists";
							}				
						}
						tx.commit();
						
			} catch (RuntimeException e) {

						try {
				tx.rollback();

							rvalue = "Data Not Updated";

						} catch (RuntimeException rbe) {
							
							rvalue = "Data Not Updated";

						}

		} finally {

						if (sessionhql != null) {

							sessionhql.close();

						}
				}

					return rvalue;

				}

	@RequestMapping(value = "/admin/foregin_country_delete_action", method = RequestMethod.POST)

	public @ResponseBody String foregin_country_delete_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("foregin_country_ch_id"));

		try {

			String hqlUpdate = "delete from TB_CENSUS_FOREIGN_COUNTRY where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();

			tx.commit();

			sessionHQL.close();

			if (app > 0) {

				msg = "1";

			} else {

				msg = "0";

			}

		} catch (Exception e) {

		}

		return msg;

	}

	@RequestMapping(value = "/admin/getForeginCountryData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_FOREIGN_COUNTRY> getForeginCountryData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("f_id"));

		String approoval_status = request.getParameter("app_status");

		String hqlUpdate = " from TB_CENSUS_FOREIGN_COUNTRY where cen_id=:cen_id   ";

		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {

			hqlUpdate += " and status=1 ";

		} else {

			hqlUpdate += " and status=0 ";

		}

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id);
		List<TB_CENSUS_FOREIGN_COUNTRY> list = (List<TB_CENSUS_FOREIGN_COUNTRY>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;

	}

	// ************************************END FOREGIN COUNTRY
	// DETAIL*******************************************

	// ************************************ START QUALIFICATION
	// DETAIL*******************************************

	@RequestMapping(value = "/admin/getQualificationData", method = RequestMethod.POST)

	public @ResponseBody List<Map<String, String>> getQualificationData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("q_id"));

		int status = 0;

		String approoval_status = request.getParameter("app_status");

		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {

			status = 1;

		} else if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("3")) {

			status = 3;

		} else {

			status = 0;

		}

		return census_QualificationDAO.getQualificationData(id, status);

	}

	@RequestMapping(value = "/admin/getSpouseQualificationData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_SPOUSE_QUALIFICATION> getSpouseQualificationData(ModelMap Mmap,

			HttpSession session, HttpServletRequest request) throws ParseException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("q_id"));

		String hqlUpdate = " from TB_CENSUS_SPOUSE_QUALIFICATION where cen_id=:cen_id ";

		String approoval_status = request.getParameter("app_status");

		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {

			hqlUpdate += " and status=1 ";

		} else if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("3")) {

			hqlUpdate += " and status=3 ";

		} else {

			hqlUpdate += " and status=0 ";

		}

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id);

		List<TB_CENSUS_SPOUSE_QUALIFICATION> list = (List<TB_CENSUS_SPOUSE_QUALIFICATION>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	@RequestMapping(value = "/admin/qualification_action", method = RequestMethod.POST)

	public @ResponseBody String qualification_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		int currentYear = date.getYear() + 1900;

		String username = session.getAttribute("username").toString();

		String type = request.getParameter("type");

		String examination_pass = request.getParameter("examination_pass");

		String passing_year = request.getParameter("passing_year");

		String div_class = request.getParameter("div_class");

		String subject = request.getParameter("subject");

		String institute = request.getParameter("institute");

		String degree = request.getParameter("degree");

		String specialization = request.getParameter("specialization");

		String qualification_ch_id = request.getParameter("qualification_ch_id");

		String q_id = request.getParameter("q_id");

		BigInteger com_id = new BigInteger(request.getParameter("com_id"));

		String dateofBirthyear = request.getParameter("dateofBirthyear");

		String exam_other_qua = request.getParameter("exam_other_qua");

		String class_other_qua = request.getParameter("class_other_qua");

		String degree_other = request.getParameter("degree_other");

		String spec_other = request.getParameter("spec_other");

		String rvalue = "";

		if (exam_other_qua.trim().equals("")) {

			exam_other_qua = null;

		}

		if (class_other_qua.trim().equals("")) {

			class_other_qua = null;

		}

		if (degree_other.trim().equals("")) {

			degree_other = null;

		}

		if (spec_other.trim().equals("")) {

			spec_other = null;

		}

		if (type == null || type.equals("0")) {

			return "Please Select The Type";

		}

		if (examination_pass == null || examination_pass.equals("0")) {

			return "Please Select The Examination Passed";

		}
	
		if (degree == null || degree.equals("0")) {

			return "Please Select The Degree Acquried";

		}
	
		if (specialization == null || specialization.equals("0")) {

			return "Please Select The Specialization";

		}
		if (spec_other != null && !spec_other.trim().equals("")) {

			if (!valid.isOnlyAlphabet(spec_other)) {
				return " Specialization Other " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(spec_other, valid.nameMax, valid.nameMin)) {
				return "Specialization Other " + valid.isValidLengthMSG;
			}
		}
		if (passing_year == null || passing_year.trim().equals("")) {

			return "Please Enter The Year of passing";

		}

		if (!passing_year.trim().equals("")) {

			if (Integer.parseInt(passing_year) <= Integer.parseInt(dateofBirthyear)

					|| Integer.parseInt(passing_year) > currentYear) {

				return "Please Enter The Valid Year of Passing";

			}
			if (valid.isOnlyNumer(passing_year) == true) {
				return " Year " + valid.isOnlyNumerMSG;
			}
			if (!valid.YearLength(passing_year)) {
				return valid.YearMSG;
			}
		}

		if (div_class == null || div_class.equals("0")) {

			return "Please Enter The Div/Grade/PCT(%)";

		}

		if (div_class.equals("4")) {

			if (class_other_qua == null || class_other_qua.trim().equals("")) {

				return "Please Enter Div/Grade/PCT(%) Other";

			}
			if (class_other_qua != null && !class_other_qua.trim().equals("")) {

				if (!valid.isOnlyAlphabet(class_other_qua)) {
					return " Div/Grade/PCT(%) Other " + valid.isOnlyAlphabetMSG;
				}

				if (!valid.isvalidLength(class_other_qua, valid.nameMax, valid.nameMin)) {
					return "Div/Grade/PCT(%) Other " + valid.isValidLengthMSG;
				}
			}
		}

		if (subject == null || subject.trim().equals("")) {

			return "Please Select The Subjects";

		}

		if (institute == null || institute.trim().equals("")) {

			return "Please Enter The Institute & place";

		}
		if (institute != null && !institute.trim().equals("")) {

			if (!valid.isOnlyAlphabet(institute)) {
				return " Institute & place " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(institute, valid.authorityMax, valid.authorityMin)) {
				return "Institute & place " + valid.isValidLengthMSG;
			}
		}
		TB_CENSUS_QUALIFICATION q = new TB_CENSUS_QUALIFICATION();

		try {
			String qa="select count(id) from TB_CENSUS_QUALIFICATION where comm_id=:com_id and cen_id=:q_id  "
					+ " and examination_pass=:examination_pass and degree=:degree "
					+ " and specialization=:specialization and subject=:subject";
			
	Query q0 = sessionhql.createQuery(qa);
			
			q0.setBigInteger("com_id", com_id);  
			
			q0.setInteger("q_id", Integer.parseInt(q_id)); 
			
			q0.setInteger("examination_pass",Integer.parseInt(examination_pass));
			
			q0.setInteger("degree",Integer.parseInt(degree));
			q0.setInteger("specialization",Integer.parseInt(specialization));
			
			q0.setString("subject",subject);
			Long c = (Long) q0.uniqueResult();
			
			
//			q0.setInteger("degree",Integer.parseInt(degree));

		
			//q0.setInteger("passing_year",Integer.parseInt(passing_year));
			
//			q0.setInteger("div_class",Integer.parseInt(div_class));
//			q0.setString("institute",institute);
			
			

			if (Integer.parseInt(qualification_ch_id) == 0) {

				q.setType(Integer.parseInt(type));

				if (examination_pass != null && !examination_pass.equals("0"))

					q.setExamination_pass(Integer.parseInt(examination_pass));

				if (degree != null && !degree.equals("0"))

					q.setDegree(Integer.parseInt(degree));

				if (specialization != null && !specialization.equals("0"))

					q.setSpecialization(Integer.parseInt(specialization));

				q.setPassing_year(Integer.parseInt(passing_year));

				q.setDiv_class(Integer.parseInt(div_class));

				q.setSubject(subject);

				q.setInstitute(institute);

				q.setCen_id(Integer.parseInt(q_id));

				q.setComm_id(com_id);

				q.setCreated_by(username);

				q.setCreated_on(date);

				q.setExam_other(exam_other_qua);

				q.setClass_other(class_other_qua);

				q.setDegree_other(degree_other);

				q.setSpecialization_other(spec_other);
				
				
			if(c==0) {

				int id = (int) sessionhql.save(q);

				rvalue = Integer.toString(id);
				
			}
			
			else {
				rvalue = "Data Already Exists";
			}				
				
			
			} else {
				String qe="select count(id) from TB_CENSUS_QUALIFICATION where comm_id=:com_id and cen_id=:q_id and "
						+ " examination_pass=:examination_pass and degree=:degree and specialization=:specialization  "
						+ " and passing_year=:passing_year and div_class=:div_class and Institute=:institute and subject=:subject ";
					
			
				Query q01 = sessionhql.createQuery(qe);
				

			
				q01.setBigInteger("com_id", com_id);  
				
				q01.setInteger("q_id", Integer.parseInt(q_id)); 
				
				q01.setInteger("examination_pass",Integer.parseInt(examination_pass));
				
				q01.setInteger("degree",Integer.parseInt(degree));
				
				q01.setInteger("specialization",Integer.parseInt(specialization));
				
				q01.setInteger("passing_year",Integer.parseInt(passing_year));
				
				q01.setInteger("div_class",Integer.parseInt(div_class));
				q01.setString("institute",institute);
				q01.setString("subject",subject);
				//q01.setString("exam_other",exam_other_qua);
			//	q01.setString("degree_other",degree_other);
				
				Long c1 = (Long) q01.uniqueResult();
				
				if(c1==0) {

				String hql = "update TB_CENSUS_QUALIFICATION set modify_by=:modify_by ,modify_on=:modify_on ,type=:type,status=0,"

						+ " passing_year=:passing_year,div_class=:div_class,subject=:subject,Institute=:institute,exam_other=:exam_other,class_other=:class_other "

						+ ",specialization_other=:specialization_other ,degree_other=:degree_other";

				if (examination_pass != null && !examination_pass.equals("0"))

					hql += ",examination_pass=:examination_pass ";

				if (degree != null && !degree.equals("0"))

					hql += ",degree=:degree ";

				if (specialization != null && !specialization.equals("0"))

					hql += ",specialization=:specialization ";

				hql += " where  id=:id";

				Query query = sessionhql.createQuery(hql).setInteger("type", Integer.parseInt(type))

						.setInteger("passing_year", Integer.parseInt(passing_year))

						.setInteger("div_class", Integer.parseInt(div_class)).setString("subject", subject)

						.setString("institute", institute).setInteger("id", Integer.parseInt(qualification_ch_id))

						.setString("modify_by", username).setTimestamp("modify_on", date)

						.setString("exam_other", exam_other_qua).setString("class_other", class_other_qua)

						.setString("specialization_other", spec_other).setString("degree_other", degree_other);

				if (examination_pass != null && !examination_pass.equals("0"))

					query.setInteger("examination_pass", Integer.parseInt(examination_pass));

				if (degree != null && !degree.equals("0"))

					query.setInteger("degree", Integer.parseInt(degree));

				if (specialization != null && !specialization.equals("0"))

					query.setInteger("specialization", Integer.parseInt(specialization));

				rvalue = query.executeUpdate() > 0 ? "Data Updated Successfully" : "Data Not Updated";
				
				}
				
				else {
					rvalue = "Data Already Exists";
				}

			}

			tx.commit();

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				rvalue = "Data Not Updated";

			} catch (RuntimeException rbe) {

				rvalue = "Data Not Updated";

			}

		} finally {

			if (sessionhql != null) {

				sessionhql.close();

			}
		}
		return rvalue;
	}


	@RequestMapping(value = "/admin/qualification_delete_action", method = RequestMethod.POST)

	public @ResponseBody String qualification_delete_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("qualification_ch_id"));

		try {

			String hqlUpdate = "delete from TB_CENSUS_QUALIFICATION where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();

			tx.commit();

			sessionHQL.close();

			if (app > 0) {

				msg = "1";

			} else {

				msg = "0";

			}

		} catch (Exception e) {

		}

		return msg;

	}

	// ************************************END QUALIFICATION
	// DETAIL*******************************************

	// ************************************START FAMILY
	// DETAIL*******************************************

	////////////////// FAMILY SIBLING///////////////////

	@RequestMapping(value = "/admin/family_sibling_action", method = RequestMethod.POST)

	public @ResponseBody Map<String, String> family_sibling_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		Map<String, String> data = new HashMap<>();

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		String sib_name = request.getParameter("sib_name");


		String sib_date_of_birth = request.getParameter("sib_date_of_birth");

		String sib_relationship = request.getParameter("sib_relationship");

		String adhar_number = request.getParameter("adhar_number");

		String pan_no = request.getParameter("pan_no");

		String sib_ch_id = request.getParameter("sib_ch_id");

		String p_id = request.getParameter("p_id");

		BigInteger comm_id = new BigInteger(request.getParameter("com_id"));

		String ser_ex = request.getParameter("ser_ex");

		String other_sib_ser = request.getParameter("other_sib_ser");

		String sib_ser_text = request.getParameter("sibling");

		int sibling_service = Integer.parseInt(request.getParameter("sib_ser"));

		String sibling_personal_no = request.getParameter("sib_pers_no");

		if (sib_name.trim().equals("") || sib_name == "" || sib_name == null) {

			data.put("error", "Please Enter Sibling Name");

			return data;

		}
		if (!sib_name.trim().equals("") || sib_name != "" || sib_name != null) {
			if (!valid.isOnlyAlphabet(sib_name)) {
				data.put("error", " Sibling Name " + valid.isOnlyAlphabetMSG);
				return data;
			}

			if (!valid.isvalidLength(sib_name, valid.nameMax, valid.nameMin)) {
				data.put("error", "Sibling Name " + valid.isValidLengthMSG);
				return data;
			}
		}
		if (sib_date_of_birth == null || sib_date_of_birth.trim().equals("")) {

			data.put("error", "Please Select Sibling Date of Birth");

			return data;

		}
		Date sib_birth_dt = null;
		if (!sib_date_of_birth.trim().equals("") && !sib_date_of_birth.equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(sib_date_of_birth)) {
				data.put("error", valid.isValidDateMSG + " of Sibling Date of Birth ");
				return data;
			} else {
				sib_birth_dt = format.parse(sib_date_of_birth);
			}
		}
		if (sib_relationship == null || sib_relationship.equals("0")) {

			data.put("error", "Please Select Sibling Relation");

			return data;

		}

		if (adhar_number == null || adhar_number.trim().equals("")) {

			data.put("error", "Please Enter Aadhaar Number");

			return data;

		}
		if (adhar_number != "") {
			if (!valid.isValidAadhar(adhar_number)) {
				data.put("error", valid.isValidAadharMSG);
				return data;
			}
		}
		if (pan_no != "") {

			if (!valid.isValidPan(pan_no)) {
				data.put("error", valid.isValidPanMSG);
				return data;
			}

			if (!valid.PanNoLength(pan_no)) {
				data.put("error", valid.PanNoMSG);
				return data;
			}
		}
		if (ser_ex.equals("Yes")) {

			if (sibling_service == 0) {

				data.put("error", "Please Select Sibling Service");

				return data;

			}

			if (sibling_service == 1) {

				if (sibling_personal_no == null || sibling_personal_no.trim().equals("")) {

					data.put("error", "Please Enter Sibling Personal No");

					return data;

				}

			}

			if (!sibling_personal_no.trim().equals("")) {

				if (sibling_personal_no.trim().length() < 7 || sibling_personal_no.trim().length() > 9) {

					data.put("error", "Please Enter Valid Sibling Personal No");

					return data;

				}

			}

			if (sibling_personal_no.trim().equals("")) {

				sibling_personal_no = "";

			}

			if (sib_ser_text.equals("OTHER")) {

				if (other_sib_ser.trim().equals("")) {

					data.put("error", "Please Enter Other Sibling Service");

					return data;

				}

				if (other_sib_ser != null && !other_sib_ser.trim().equals("")) {

					if (!valid.isOnlyAlphabet(other_sib_ser)) {
						data.put("error", "Other Sibling Service " + valid.isOnlyAlphabetMSG);
						return data;
					}
					if (!valid.isvalidLength(other_sib_ser, valid.nameMax, valid.nameMin)) {
						data.put("error", "Other Sibling Service " + valid.isValidLengthMSG);
						return data;
					}

				}
			} else {

				other_sib_ser = null;

			}

		}

		try {
			
			TB_CENSUS_FAMILY_SIBLINGS fam_sib = new TB_CENSUS_FAMILY_SIBLINGS();
			
			
		Query q0 = sessionhql.createQuery("select count(id) from TB_CENSUS_FAMILY_SIBLINGS where cen_id=:p_id and name=:sib_name "
				+ "and date_of_birth=:sib_birth_dt and aadhar_no=:adhar_number  and sibling_service=:sibling_service and sibling_personal_no=:sibling_personal_no "
				+ "  and relationship=:sib_relationship");
		
		q0.setInteger("p_id", Integer.parseInt(p_id));  
				
		q0.setString("sib_name",sib_name);
		
		q0.setTimestamp("sib_birth_dt",sib_birth_dt);
		q0.setString("adhar_number",adhar_number);
		q0.setInteger("sibling_service" , sibling_service);
		
	    q0.setString("sibling_personal_no",sibling_personal_no);
	    
		q0.setInteger("sib_relationship", Integer.parseInt(sib_relationship));
		Long c = (Long) q0.uniqueResult();
		
		if (Integer.parseInt(sib_ch_id) == 0) {

		fam_sib.setName(sib_name);

		fam_sib.setDate_of_birth(sib_birth_dt);

		fam_sib.setRelationship(Integer.parseInt(sib_relationship));

		fam_sib.setCen_id(Integer.parseInt(p_id));

		fam_sib.setAadhar_no(adhar_number);

		if (pan_no != null && !pan_no.trim().equals("")) {

			fam_sib.setPan_no(pan_no);

		}

		fam_sib.setComm_id(comm_id);

		fam_sib.setIf_sibling_ser(ser_ex);

		fam_sib.setSibling_service(sibling_service);

		fam_sib.setSibling_personal_no(sibling_personal_no);

		fam_sib.setOther_sibling_ser(other_sib_ser);

		fam_sib.setCreated_by(username);

		fam_sib.setCreated_date(date);
		
		if(c==0) {

		int id = (int) sessionhql.save(fam_sib);

		data.put("sibId", String.valueOf(id));

		data.put("saved", "Data Saved Successfully");
		}
		
		else {
			data.put("updated", "Data Already Exists");
		}	

	} else {
		String query1="";
		String qry="";
		if (sibling_service == 9) {
			qry=" and other_sibling_ser=:other_sib_ser";
		}
		
		if (pan_no != null && !pan_no.trim().equals("")) {
			query1=" and pan_no=:pan_no";
		}
		
		Query q01 = sessionhql.createQuery("select count(id) from TB_CENSUS_FAMILY_SIBLINGS where cen_id=:p_id and name=:sib_name "
				+ " and date_of_birth=:sib_birth_dt and aadhar_no=:adhar_number"
				+ " and sibling_service=:sibling_service and sibling_personal_no=:sibling_personal_no"
				+ " and relationship=:sib_relationship"
				+ qry
				+ query1);
//
	q01.setInteger("p_id", Integer.parseInt(p_id));  
	
	q01.setString("sib_name",sib_name);
	
	q01.setTimestamp("sib_birth_dt",sib_birth_dt);
	
	q01.setString("adhar_number",adhar_number);
	
	q01.setInteger("sibling_service" , sibling_service);
	
    q01.setString("sibling_personal_no",sibling_personal_no);
    
	q01.setInteger("sib_relationship", Integer.parseInt(sib_relationship));
	
	if (sibling_service == 9) {
		q01.setString("other_sib_ser",other_sib_ser);
	}
	
	if (pan_no != null && !pan_no.trim().equals("")) {
		q01.setString("pan_no", pan_no);
	} 
	

	
	Long c1 = (Long) q01.uniqueResult();
	
	if(c1==0) {
		
		String hql = "update TB_CENSUS_FAMILY_SIBLINGS set modified_by=:modified_by ,modified_date=:modified_date ,name=:name, date_of_birth=:date_of_birth,"

				+ " relationship=:relationship,aadhar_no=pgp_sym_encrypt(:aadhar_no,current_setting('miso.version'))";

		hql += ",pan_no=pgp_sym_encrypt(:pan_no,current_setting('miso.version')) ";

		hql += ",sibling_service=:sib_ser,sibling_personal_no=:sib_pers_no,if_sibling_ser=:if_sib_ser,other_sibling_ser=:other_sib_ser ";

		hql += " where  id=:id";

		Query query = sessionhql.createQuery(hql).setTimestamp("date_of_birth", sib_birth_dt)

				.setString("name", sib_name).setInteger("relationship", Integer.parseInt(sib_relationship))

				.setInteger("id", Integer.parseInt(sib_ch_id)).setString("modified_by", username)

				.setTimestamp("modified_date", date).setString("aadhar_no", adhar_number);

		if (pan_no != null && !pan_no.trim().equals("")) {

			query.setString("pan_no", pan_no);

		} else

			query.setString("pan_no", null);

		query.setInteger("sib_ser", sibling_service).setString("sib_pers_no", sibling_personal_no)

				.setString("if_sib_ser", ser_ex).setString("other_sib_ser", other_sib_ser);

		int update = query.executeUpdate() > 0 ? 1 : 0;

		if (update == 1)

			data.put("updated", "Data Updated Successfully");

		else

			data.put("error", "Data Not Updated");

	}
	else {
		data.put("updated", "Data Already Exists");
	}	

	}

	tx.commit();
} catch (RuntimeException e) {

			try {

				tx.rollback();

				data.put("error", "Data Not Updated");

			} catch (RuntimeException rbe) {

				data.put("error", "Data Not Updated");

			}

		} finally {

			if (sessionhql != null) {

				sessionhql.close();

			}

		}

		return data;

	}

	@RequestMapping(value = "/admin/family_sibling_delete_action", method = RequestMethod.POST)

	public @ResponseBody String family_sibling_delete_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("sib_ch_id"));

		try {

			String hqlUpdate = "delete from TB_CENSUS_FAMILY_SIBLINGS where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();

			tx.commit();

			sessionHQL.close();

			if (app > 0) {

				msg = "1";

			} else {

				msg = "0";

			}

		} catch (Exception e) {

		}

		return msg;

	}

	@RequestMapping(value = "/admin/getfamily_siblingData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_FAMILY_SIBLINGS> getfamily_siblingData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("p_id"));

		String hqlUpdate = " from TB_CENSUS_FAMILY_SIBLINGS where cen_id=:census_id ";

		String approoval_status = request.getParameter("app_status");

		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {

			hqlUpdate += " and status=1 ";

		} else {

			hqlUpdate += " and status=0 ";

		}

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id);
		List<TB_CENSUS_FAMILY_SIBLINGS> list = (List<TB_CENSUS_FAMILY_SIBLINGS>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;

	}

	///////////////////////////////////////// MARRIAGE/////////////////////

	@RequestMapping(value = "/admin/family_marriage_action", method = RequestMethod.POST)

	public @ResponseBody String family_marriage_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		String maiden_name = request.getParameter("maiden_name");

		String marriage_date_of_birth = request.getParameter("marriage_date_of_birth");

		String separated_form_dt = request.getParameter("sep_from_date");

		String marriage_place_of_birth = request.getParameter("marriage_place_of_birth");

		String marriage_nationality = request.getParameter("marriage_nationality");

		String marriage_date = request.getParameter("marriage_date");

		String pan_number = request.getParameter("spouse_pan_number");

		String marriage_othernationality = request.getParameter("marriage_othernationality");

		String adhar_number = "";

		String adhar = request.getParameter("adhar_number");

		String spo_ex = request.getParameter("spo_ex");
	
		String spo_ser_text = request.getParameter("spouse");
		
		int spouse_service = Integer.parseInt(request.getParameter("spo_ser"));

		String spouse_personal_no = request.getParameter("spo_pers_no");

		String other_spo_ser = request.getParameter("other_spo_ser");
		
		String spouse_profession = request.getParameter("spouse_profession");

		if (maiden_name == null || maiden_name.trim().equals("")) {

			return "Please Enter  Name";

		}
		if (!maiden_name.trim().equals("") || maiden_name != "" || maiden_name != null) {
			if (!valid.isOnlyAlphabet(maiden_name)) {
				return "  Name " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(maiden_name, valid.nameMax, valid.nameMin)) {
				return " Name " + valid.isValidLengthMSG;
			}
		}
		if (marriage_date_of_birth == null || marriage_date_of_birth.trim().equals("")

				|| marriage_date_of_birth.equals("DD/MM/YYYY")) {

			return "Please Enter  Date of Birth";

		}
		Date mrg_birth_dt = null;
		if (!marriage_date_of_birth.trim().equals("") && !marriage_date_of_birth.equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(marriage_date_of_birth)) {
				return valid.isValidDateMSG + "  of Birth ";
			} else {
				mrg_birth_dt = format.parse(marriage_date_of_birth);
			}
		}
		if (marriage_place_of_birth == null || marriage_place_of_birth.trim().equals("")) {

			return "Please Enter  Place of Birth";

		}
		if (!marriage_place_of_birth.trim().equals("") || marriage_place_of_birth != ""
				|| marriage_place_of_birth != null) {
			if (!valid.isOnlyAlphabet(marriage_place_of_birth)) {
				return "  Place of Birth " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(marriage_place_of_birth, valid.nameMax, valid.nameMin)) {
				return " Place of Birth " + valid.isValidLengthMSG;
			}
		}
		if (marriage_nationality == null || marriage_nationality.equals("0")) {

			return "Please Enter Nationality";
		}
		
		if ( !spo_ex.equalsIgnoreCase("Yes") && (spouse_profession == null || spouse_profession.equals("0"))) {
			return "Please Select Spouse Profession";
		}
		
		if (!marriage_othernationality.trim().equals("") || marriage_othernationality != ""
				|| marriage_othernationality != null) {
			if (!valid.isOnlyAlphabet(marriage_othernationality)) {
				return " Nationality Other " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(marriage_othernationality, valid.nameMax, valid.nameMin)) {
				return "Nationality Other " + valid.isValidLengthMSG;
			}
		}
		if (marriage_date == null || marriage_date.trim().equals("") || marriage_date.equals("DD/MM/YYYY")) {

			return "Please Enter Marriage Date";

		}
		Date mrg_dt = null;
		if (!marriage_date.trim().equals("") && !marriage_date.equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(marriage_date)) {
				return valid.isValidDateMSG + " of Marriage ";
			} else {
				mrg_dt = format.parse(marriage_date);
			}
		}
		if (format.parse(marriage_date).compareTo(format.parse(marriage_date_of_birth)) <= 0) {

			return "Marriage Date should be Greater than Date of Birth";
		}

		if (mcommon.calculate_age(format.parse(marriage_date_of_birth), format.parse(marriage_date)) < 18) {
			return "Spouse Age Should Be Above 18";
		}

		if (adhar == null || adhar.trim().equals("")) {
			return "Please Enter Aadhaar No";
		}
		if (adhar != "" || adhar != null || !adhar.trim().equals("")) {
			if (!valid.isValidAadhar(adhar)) {
				return valid.isValidAadharMSG;
			}
		}

		if (pan_number != "") {

			if (!valid.isValidPan(pan_number)) {
				return valid.isValidPanMSG;
			}

			if (!valid.PanNoLength(pan_number)) {
				return valid.PanNoMSG;
			}
		}
		if (spo_ex.equals("Yes")) {

			if (spouse_service == 0) {

				return "Please Select Spouse Service";

			}

			//if (spouse_service == 1)
			else {

				if (spouse_personal_no == null || spouse_personal_no.trim().equals("")) {

					return "Please Enter Spouse Personal No";

				}

			}

			if (!spouse_personal_no.trim().equals("")) {

				if (spouse_personal_no.trim().length() < 7 || spouse_personal_no.trim().length() > 9) {

					return "Please Enter Valid Spouse Personal No";

				}

			}

			if (spouse_personal_no.trim().equals("")) {

				spouse_personal_no = "";

			}

			if (spo_ser_text.equals("OTHER")) {

				if (other_spo_ser.trim().equals("")) {

					return "Please Enter Other Spouse Service";

				}
				if (!other_spo_ser.trim().equals("") || other_spo_ser != "" || other_spo_ser != null) {
					if (!valid.isOnlyAlphabet(other_spo_ser)) {
						return " Other Spouse Service " + valid.isOnlyAlphabetMSG;
					}

					if (!valid.isvalidLength(other_spo_ser, valid.nameMax, valid.nameMin)) {
						return "Other Spouse Service " + valid.isValidLengthMSG;
					}
				}
			} else {

				other_spo_ser = null;

			}

		}

		String marital_status = request.getParameter("marital_status");

		if (marital_status.equals("13")) {

			if (separated_form_dt == null || separated_form_dt.trim().equals("")
					|| separated_form_dt.equals("DD/MM/YYYY")) {

				return "Please Enter From Date";

			}

			if (format.parse(separated_form_dt).compareTo(format.parse(marriage_date)) <= 0) {

				return "From Date should be Greater than Marriage Date";

			}

		}

		if (marriage_othernationality.trim().equals("")) {

			marriage_othernationality = null;

		}

		adhar_number = adhar;

		String marr_ch_id = request.getParameter("marr_ch_id");
		String p_id = request.getParameter("p_id");
		BigInteger comm_id = BigInteger.ZERO;
	     comm_id = new BigInteger(request.getParameter("com_id"));
		String rvalue = "";
		try {
			String qry1="";
			if(Integer.parseInt(marriage_nationality) == 14) {
				qry1=" and other_nationality=:marriage_othernationality";
			}
			
			Query q0 = sessionhql.createQuery("select count(id) from TB_CENSUS_FAMILY_MRG where comm_id=:com_id and cen_id=:p_id and maiden_name=:maiden_name and "
					+ "date_of_birth=:marriage_date_of_birth and place_of_birth=:marriage_place_of_birth and  nationality=:marriage_nationality and "
					+ "marriage_date=:marriage_date and spouse_profession=:spouse_profession"
					+ qry1);
	
			q0.setBigInteger("com_id", comm_id);  
			
			
			q0.setInteger("p_id", Integer.parseInt(p_id));
			
		
			q0.setString("maiden_name",maiden_name);
		
			q0.setTimestamp("marriage_date_of_birth",format.parse(marriage_date_of_birth));
			
			q0.setString("marriage_place_of_birth",marriage_place_of_birth);
			
			q0.setParameter("marriage_nationality",Integer.parseInt(marriage_nationality));
			
			q0.setTimestamp("marriage_date",format.parse(marriage_date));
			
			q0.setParameter("spouse_profession",Integer.parseInt(spouse_profession));
			
			if(Integer.parseInt(marriage_nationality) == 14) {
			q0.setString("marriage_othernationality",marriage_othernationality);
			}
			
			Long c = (Long) q0.uniqueResult();			
			
		if (Integer.parseInt(marr_ch_id) == 0) {

			TB_CENSUS_FAMILY_MRG fam_marr = new TB_CENSUS_FAMILY_MRG();

			fam_marr.setMaiden_name(maiden_name);

			fam_marr.setDate_of_birth(mrg_birth_dt);

			fam_marr.setMarriage_date(mrg_dt);

			fam_marr.setPlace_of_birth(marriage_place_of_birth);

			fam_marr.setNationality(Integer.parseInt(marriage_nationality));

			fam_marr.setAdhar_number(adhar_number);

			fam_marr.setCen_id(Integer.parseInt(p_id));

			fam_marr.setCen_id(Integer.parseInt(p_id));

			fam_marr.setMarital_status(Integer.parseInt(marital_status));

			fam_marr.setIf_spouse_ser(spo_ex);

			fam_marr.setSpouse_service(spouse_service);

			fam_marr.setSpouse_personal_no(spouse_personal_no);

			fam_marr.setOther_spouse_ser(other_spo_ser);

			fam_marr.setCreated_by(username);

			fam_marr.setOther_nationality(marriage_othernationality);

			fam_marr.setComm_id(comm_id);

			fam_marr.setCreated_date(date);
			
			fam_marr.setSpouse_profession(Integer.parseInt(spouse_profession));

			if (marital_status.equals("13")) {

				fam_marr.setSeparated_form_dt(format.parse(separated_form_dt));

			}

			if (pan_number != null && !pan_number.trim().equals(""))

				fam_marr.setPan_card(pan_number);
			
			if(c==0) {

			int id = (int) sessionhql.save(fam_marr);

			rvalue = Integer.toString(id);

			}
			
			else {
				rvalue = "Data Already Exists";
			}

		} else {
			
			String qry="";
			if(Integer.parseInt(marriage_nationality) == 14) {
				qry=" and other_nationality=:marriage_othernationality";
			}
			
					Query q01 = sessionhql.createQuery("select count(id) from TB_CENSUS_FAMILY_MRG where comm_id=:com_id and cen_id=:p_id and maiden_name=:maiden_name and "
					+ "date_of_birth=:marriage_date_of_birth and place_of_birth=:marriage_place_of_birth and  nationality=:marriage_nationality and "
					+ "marriage_date=:marriage_date and spouse_profession=:spouse_profession and adhar_number =:adhar_number and pan_card =:pan_number"
					+ qry );
			
			q01.setBigInteger("com_id", comm_id);  
			
			q01.setInteger("p_id", Integer.parseInt(p_id)); 
			
			q01.setString("maiden_name",maiden_name);
			
			q01.setTimestamp("marriage_date_of_birth",format.parse(marriage_date_of_birth));
			
			q01.setString("marriage_place_of_birth",marriage_place_of_birth);

			q01.setParameter("marriage_nationality",Integer.parseInt(marriage_nationality));
			
			q01.setTimestamp("marriage_date",format.parse(marriage_date));
			
			q01.setParameter("spouse_profession",Integer.parseInt(spouse_profession));
			
			if(Integer.parseInt(marriage_nationality) == 14) {
				
				q01.setString("marriage_othernationality",marriage_othernationality);
			}
			
			q01.setString("adhar_number",adhar_number);
			
			q01.setString("pan_number",pan_number);
			
			Long c1 = (Long) q01.uniqueResult();
			
			if(c1==0) {
			String hql = "update TB_CENSUS_FAMILY_MRG set modified_by=:modified_by ,modified_date=:modified_date ,maiden_name=:maiden_name, date_of_birth=:date_of_birth,"

					+ " other_nationality=:other_nationality,separated_form_dt=:separated_form_dt,place_of_birth=:place_of_birth,marriage_date=:marriage_date,nationality=:nationality,adhar_number=pgp_sym_encrypt(:adhar_number,current_setting('miso.version')) ,marital_status=:marital_status ";

			hql += ",pan_card=pgp_sym_encrypt(:pan_card,current_setting('miso.version')) ";

			hql += ",spouse_service=:spo_ser,spouse_personal_no=:spo_pers_no,if_spouse_ser=:if_spo_ser,other_spouse_ser=:other_spo_ser,spouse_profession=:spouse_profession";

			hql += " where  id=:id";

			Query query = sessionhql.createQuery(hql)

					.setTimestamp("date_of_birth", mrg_birth_dt)

					.setTimestamp("marriage_date", mrg_dt)

					.setString("maiden_name", maiden_name).setString("place_of_birth", marriage_place_of_birth)

					.setInteger("nationality", Integer.parseInt(marriage_nationality))

					.setInteger("marital_status", Integer.parseInt(marital_status))

					.setString("adhar_number", adhar_number).setInteger("id", Integer.parseInt(marr_ch_id))

					.setString("other_nationality", marriage_othernationality).setString("modified_by", username)

					.setTimestamp("modified_date", date);

			if (marital_status.equals("13")) {

				query.setTimestamp("separated_form_dt", format.parse(separated_form_dt));

			} else {

				query.setTimestamp("separated_form_dt", null);

			}

			if (pan_number != null && !pan_number.trim().equals(""))

				query.setString("pan_card", pan_number);

			else

				query.setString("pan_card", null);

			query.setInteger("spo_ser", spouse_service).setString("spo_pers_no", spouse_personal_no)

					.setString("if_spo_ser", spo_ex).setString("other_spo_ser", other_spo_ser)
					.setInteger("spouse_profession",Integer.parseInt(spouse_profession));

			rvalue = query.executeUpdate() > 0 ? "Data Updated Successfully" : "Data Not Updated";

		}
			else
			{
				rvalue = "Data Already Exists";
			}
			
		}
		

		tx.commit();
		} 
		catch (RuntimeException e) {
			try {
				tx.rollback();

				rvalue = "Data Not Updated";

			} catch (RuntimeException rbe) {

				rvalue = "Data Not Updated";
			}
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}

		return rvalue;

	}

	@RequestMapping(value = "/admin/family_marriage_delete_action", method = RequestMethod.POST)

	public @ResponseBody String family_marriage_delete_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("marr_ch_id"));

		try {

			String hqlUpdate = "delete from TB_CENSUS_SPOUSE_QUALIFICATION where spouse_id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();

			hqlUpdate = "delete from TB_CENSUS_FAMILY_MRG where id=:id";

			int papp = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();

			tx.commit();

			if (papp > 0) {

				msg = "1";

			} else {

				msg = "0";

			}

		} catch (Exception e) {

			tx.rollback();

		} finally {

			sessionHQL.close();

		}

		return msg;

	}

	@RequestMapping(value = "/admin/getfamily_marriageData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_FAMILY_MRG> getfamily_marriageData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("p_id"));

		int divorce = 0;

		if (request.getParameter("divorce") != null && !request.getParameter("divorce").trim().equals(""))

			divorce = Integer.parseInt(request.getParameter("divorce"));

		String hqlUpdate = " from TB_CENSUS_FAMILY_MRG where cen_id=:census_id ";

		if (divorce == 1)

			hqlUpdate += " and divorce_date is not null";

		else

			hqlUpdate += " and divorce_date is null";

		String approoval_status = request.getParameter("app_status");

		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {
			
			if (divorce == 1) {
				hqlUpdate += " and status in (1,2) ";
			}else {
				hqlUpdate += " and status=1 ";
			}

			

		} else {

			hqlUpdate += " and status=0 ";

		}

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id);

		List<TB_CENSUS_FAMILY_MRG> list = (List<TB_CENSUS_FAMILY_MRG>) query.list();

		tx.commit();

		sessionHQL.close();

		

		return list;

	}

	//////////////////////// Divorce //////////////////

	@RequestMapping(value = "/admin/family_divorce_action", method = RequestMethod.POST)

	public @ResponseBody String family_divorce_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		String spouse_name = request.getParameter("spouse_name");

		String marital_event = request.getParameter("marital_event");

		String marriage_date = request.getParameter("divorce_marriage_date");

		String divorce_date = request.getParameter("divorce_date");

		String date_of_birth = request.getParameter("marriage_date_of_birth");

		String place_of_birth = request.getParameter("marriage_place_of_birth");

		String nationality = request.getParameter("marriage_nationality");

		String adhar = request.getParameter("adhar_number");

		String pan_number = request.getParameter("divorce_spouse_pan_number");

		String other_nationality = request.getParameter("divorce_othernationality");

		String divo_ch_id = request.getParameter("divo_ch_id");

		String p_id = request.getParameter("p_id");

		BigInteger comm_id = BigInteger.ZERO;
		 comm_id = new BigInteger(request.getParameter("com_id"));

		String adhar_number = "";

		String rvalue = "";

		if (marital_event == null || marital_event.equals("0")) {

			return "Please Select Marital Event";

		}

		if (spouse_name == null || spouse_name.trim().equals("")) {

			return "Please Enter  Name";

		}
		if (spouse_name != null && !spouse_name.trim().equals("")) {

			if (!valid.isOnlyAlphabet(spouse_name)) {
				return " Name " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(spouse_name, valid.nameMax, valid.nameMin)) {
				return "Name " + valid.isValidLengthMSG;
			}
		}
		if (date_of_birth == null || date_of_birth.trim().equals("") || date_of_birth.equals("DD/MM/YYYY")) {

			return "Please Enter Date of Birth";

		}
		Date birth_dt = null;
		if (!date_of_birth.trim().equals("") && !date_of_birth.equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(date_of_birth)) {
				return valid.isValidDateMSG + " of Birth";
			} else {
				birth_dt = format.parse(date_of_birth);
			}
		}
		if (place_of_birth == null || place_of_birth.trim().equals("")) {

			return "Please Enter Place of Birth";

		}
		if (place_of_birth != null && !place_of_birth.trim().equals("")) {

			if (!valid.isOnlyAlphabet(place_of_birth)) {
				return "  Place of Birth " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(place_of_birth, valid.nameMax, valid.nameMin)) {
				return " Place of Birth " + valid.isValidLengthMSG;
			}
		}
		if (nationality == null || nationality.equals("0")) {

			return "Please Enter Nationality";

		}
		if (other_nationality != null && !other_nationality.trim().equals("")) {

			if (!valid.isOnlyAlphabet(other_nationality)) {
				return "  Nationality Other " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(other_nationality, valid.nameMax, valid.nameMin)) {
				return " Nationality Other " + valid.isValidLengthMSG;
			}
		}
		if (marriage_date == null || marriage_date.trim().equals("") || marriage_date.equals("DD/MM/YYYY")) {

			return "Please Enter Marriage Date";

		}
		Date mrg_dt = null;
		if (!marriage_date.trim().equals("") && !marriage_date.equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(marriage_date)) {
				return valid.isValidDateMSG + "of Marriage ";
			} else {
				mrg_dt = format.parse(marriage_date);
			}
		}
		if (adhar == null || adhar.trim().equals("")) {

			return "Please Enter Aadhaar No";

		}
		if (adhar != "") {
			if (!valid.isValidAadhar(adhar)) {
				return valid.isValidAadharMSG;
			}
		}
		if (pan_number != "") {

			if (!valid.isValidPan(pan_number)) {
				return valid.isValidPanMSG;
			}

			if (!valid.PanNoLength(pan_number)) {
				return valid.PanNoMSG;
			}
		}
		if (format.parse(marriage_date).compareTo(format.parse(date_of_birth)) <= 0) {

			return "Marriage Date should be Greater than Date of Birth";

		}

		if (mcommon.calculate_age(format.parse(date_of_birth), format.parse(marriage_date)) < 18) {

			return "Spouse Age Should Be Above 18";

		}

		if (divorce_date == null || divorce_date.trim().equals("") || divorce_date.equals("DD/MM/YYYY")) {

			return "Please Enter Divorce/Widow/Widower Date";

		}
		Date div_dt = null;
		if (!divorce_date.trim().equals("") && !divorce_date.equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(divorce_date)) {
				return valid.isValidDateMSG + " of Divorce/Widow/Widower ";
			} else {
				div_dt = format.parse(divorce_date);
			}
		}
		if (format.parse(divorce_date).compareTo(format.parse(date_of_birth)) <= 0) {

			return "Divorce Date should be Greater than Date of Birth";

		}

		if (other_nationality.trim().equals("")) {

			other_nationality = null;

		}

		adhar_number = adhar;


		try {
			String qry1="";
			if(Integer.parseInt(nationality) == 14) {
				qry1=" and other_nationality=:other_nationality";
			}
			
			Query q0 = sessionhql.createQuery("select count(id) from TB_CENSUS_FAMILY_MRG where comm_id=:com_id and cen_id=:p_id and maiden_name=:spouse_name and "
					+ "date_of_birth=:date_of_birth and place_of_birth=:place_of_birth and  nationality=:nationality and "
					+ "marital_status=:marital_event and "
					+ "marriage_date=:marriage_date"
					+ qry1);
			
			q0.setBigInteger("com_id", comm_id);  

			q0.setInteger("p_id", Integer.parseInt(p_id));
			
			q0.setString("spouse_name",spouse_name);

			q0.setTimestamp("date_of_birth",format.parse(date_of_birth));

			q0.setString("place_of_birth",place_of_birth);

			q0.setParameter("nationality",Integer.parseInt(nationality));
			
			q0.setParameter("marital_event",Integer.parseInt(marital_event));
			
			q0.setTimestamp("marriage_date",format.parse(marriage_date));
			
			if(Integer.parseInt(nationality) == 14) {
				q0.setString("other_nationality",other_nationality);
				}
				

			Long c = (Long) q0.uniqueResult();

			if (Integer.parseInt(divo_ch_id) == 0) {

				TB_CENSUS_FAMILY_MRG fam_marr = new TB_CENSUS_FAMILY_MRG();

				fam_marr.setMaiden_name(spouse_name);

				fam_marr.setDate_of_birth(birth_dt);

				fam_marr.setMarriage_date(mrg_dt);

				fam_marr.setDivorce_date(div_dt);

				fam_marr.setPlace_of_birth(place_of_birth);

				fam_marr.setNationality(Integer.parseInt(nationality));

				fam_marr.setAdhar_number(adhar_number);

				fam_marr.setCen_id(Integer.parseInt(p_id));

				fam_marr.setMarital_status(Integer.parseInt(marital_event));

				fam_marr.setCreated_by(username);

				fam_marr.setOther_nationality(other_nationality);

				fam_marr.setComm_id(comm_id);

				fam_marr.setCreated_date(date);

				if (pan_number != null && !pan_number.trim().equals(""))

					fam_marr.setPan_card(pan_number);
				
				if(c==0) {
					
						int id = (int) sessionhql.save(fam_marr);
		
						rvalue = Integer.toString(id);
						
				}
				else
				{
					rvalue = "Data Already Exists";
				}

			} else {
				String qry="";
				if(Integer.parseInt(nationality) == 14) {
					qry=" and other_nationality=:other_nationality";
				}
				
				Query q01 = sessionhql.createQuery("select count(id) from TB_CENSUS_FAMILY_MRG where comm_id=:com_id and cen_id=:p_id and maiden_name=:spouse_name and "
						+ "date_of_birth=:date_of_birth and place_of_birth=:place_of_birth and  nationality=:nationality and "
						+ "marital_status=:marital_event and "
						+ "marriage_date=:marriage_date"
						+ qry);
				
				q01.setBigInteger("com_id", comm_id);  
				
				q01.setInteger("p_id", Integer.parseInt(p_id)); 
				
				q01.setString("spouse_name",spouse_name);
				
				q01.setTimestamp("date_of_birth",format.parse(date_of_birth));

				q01.setString("place_of_birth",place_of_birth);
				
				q01.setParameter("nationality",Integer.parseInt(nationality));
				
				q01.setParameter("marital_event",Integer.parseInt(marital_event));

				q01.setTimestamp("marriage_date",format.parse(marriage_date));
				
				if(Integer.parseInt(nationality) == 14) {
					q01.setString("other_nationality",other_nationality);
					}
					
				
				Long c1 = (Long) q01.uniqueResult();
				
				if(c1==0) {
					
				String hql = "update TB_CENSUS_FAMILY_MRG set modified_by=:modified_by ,modified_date=:modified_date ,maiden_name=:maiden_name, date_of_birth=:date_of_birth,"

						+ "other_nationality=:other_nationality, place_of_birth=:place_of_birth,marriage_date=:marriage_date,nationality=:nationality,adhar_number=pgp_sym_encrypt(:adhar_number,current_setting('miso.version')),divorce_date=:divorce_date ,marital_status=:marital_status ";

				hql += ",pan_card=pgp_sym_encrypt(:pan_card,current_setting('miso.version'))";

				hql += " where  id=:id";

				Query query = sessionhql.createQuery(hql).setTimestamp("date_of_birth", birth_dt)

						.setTimestamp("marriage_date", mrg_dt)

						.setTimestamp("divorce_date", div_dt).setString("maiden_name", spouse_name)

						.setString("place_of_birth", place_of_birth)

						.setInteger("nationality", Integer.parseInt(nationality))

						.setInteger("marital_status", Integer.parseInt(marital_event))

						.setString("adhar_number", adhar_number).setInteger("id", Integer.parseInt(divo_ch_id))

						.setString("other_nationality", other_nationality).setString("modified_by", username)

						.setTimestamp("modified_date", date);

				if (pan_number != null && !pan_number.trim().equals(""))

					query.setString("pan_card", pan_number);

				else

					query.setString("pan_card", null);

				rvalue = query.executeUpdate() > 0 ? "Data Updated Successfully" : "Data Not Updated";
				}
				
				else {
					rvalue = "Data Already Exists";
				}
				

			}
				

			tx.commit();

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				rvalue = "Data Not Updated";

			} catch (RuntimeException rbe) {

				rvalue = "Data Not Updated";

			}

		} finally {

			if (sessionhql != null) {

				sessionhql.close();

			}

		}

		return rvalue;

	}

	@RequestMapping(value = "/admin/family_divorce_delete_action", method = RequestMethod.POST)

	public @ResponseBody String family_divorce_delete_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("divo_ch_id"));

		try {

			String hqlUpdate = "delete from TB_CENSUS_FAMILY_DIVORCE where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();

			tx.commit();

			sessionHQL.close();

			if (app > 0) {

				msg = "1";

			} else {

				msg = "0";

			}

		} catch (Exception e) {

		}

		return msg;

	}

	@RequestMapping(value = "/admin/getfamily_DivorceData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_FAMILY_DIVORCE> getfamily_DivorceData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("p_id"));

		String hqlUpdate = " from TB_CENSUS_FAMILY_DIVORCE where cen_id=:census_id";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id);

		List<TB_CENSUS_FAMILY_DIVORCE> list = (List<TB_CENSUS_FAMILY_DIVORCE>) query.list();

		tx.commit();

		sessionHQL.close();


		return list;

	}

	@RequestMapping(value = "/admin/saveFamilyDetailsAction", method = RequestMethod.POST)

	public @ResponseBody String saveFamilyDetailsAction(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		String father_name = request.getParameter("father_name");

		String father_dob = request.getParameter("father_dob");

		String father_place_birth = request.getParameter("father_place_birth");

		String father_profession = request.getParameter("father_profession");

		String mother_dob = request.getParameter("mother_dob");

		String mother_place_birth = request.getParameter("mother_place_birth");

		String mother_profession = request.getParameter("mother_profession");

		String mother_name = request.getParameter("mother_name");

		String father_pan_no = request.getParameter("father_pan_no");

		String mother_pan_no = request.getParameter("mother_pan_no");

		String father_adhar_number = request.getParameter("father_adhar_number");

		String mother_adhar_number = request.getParameter("mother_adhar_number");

		String isfatherInservice = request.getParameter("isfatherInservice");

		String ismotherInservice = request.getParameter("ismotherInservice");

		String mother_personal_no = request.getParameter("mother_personal_no");

		String father_personal_no = request.getParameter("father_personal_no");

		String mother_other = request.getParameter("mother_other");

		String father_other = request.getParameter("father_other");

		String m_otherproff = request.getParameter("m_otherproff");

		String f_otherproff = request.getParameter("f_otherproff");

		String mother_service = request.getParameter("mother_service");

		String father_service = request.getParameter("father_service");

		if (father_name == null || father_name.trim().equals("")) {

			return "Please Enter Father Name";

		}
		if (father_name != null && !father_name.trim().equals("")) {

			if (!valid.isOnlyAlphabet(father_name)) {
				return "Father Name " + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(father_name, valid.nameMax, valid.nameMin)) {
				return "Father Name " + valid.isValidLengthMSG;
			}

		}
		Date father_birth_dt = null;
		

		if (father_dob != null && !father_dob.equals("DD/MM/YYYY") && !father_dob.trim().equals(""))
		{
			if (!father_dob.trim().equals("") && !father_dob.equals("DD/MM/YYYY")) {
				if (!valid.isValidDate(father_dob)) {
					return valid.isValidDateMSG + " of Father Date of Birth";
				} else {
					father_birth_dt = format.parse(father_dob);
				}
			}
		}
		if (father_pan_no != "") {

			if (!valid.isValidPan(father_pan_no)) {
				return valid.isValidPanMSG;
			}

			if (!valid.PanNoLength(father_pan_no)) {
				return valid.PanNoMSG;
			}
		}
		
	/*	if (father_adhar_number == null || father_adhar_number.trim().equals("")) {

			return "Please Enter Father Aadhaar Number";

		}
		if (father_adhar_number != "" || father_adhar_number != null || !father_adhar_number.trim().equals("")) {
			if (!valid.isValidAadhar(father_adhar_number)) {
				return valid.isValidAadharMSG;
			}
		}

		
		if (father_place_birth == null || father_place_birth.trim().equals("")) {

			return "Please Enter Father Place of Birth";

		}
		if (father_place_birth != null && !father_place_birth.trim().equals("")) {

			if (!valid.isOnlyAlphabet(father_place_birth)) {
				return "Father Place of Birth " + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(father_place_birth, valid.nameMax, valid.nameMin)) {
				return "Father Place of Birth " + valid.isValidLengthMSG;
			}

		}
		if (isfatherInservice.equals("yes")) {

			father_profession = "0";

			if (father_service == null || father_service.trim().equals("0")) {

				return "Please Select Father Service";

			}

			if (father_personal_no.trim().equals("")) {

				father_personal_no = null;

			}

			if (father_service.equals("4")) {

				father_personal_no = null;

			} else {

				father_other = null;

			}

		} else {

			if (father_profession == null || father_profession.trim().equals("0")) {

				return "Please Select Father Profession";

			}

			father_personal_no = null;

			father_other = null;

			father_service = null;

			if (request.getParameter("f_proff").equals("OTHERS")) {

				father_other = f_otherproff;

			}

			if (f_otherproff != null && !f_otherproff.trim().equals("")) {

				if (!valid.isOnlyAlphabet(f_otherproff)) {
					return "Father Profession " + valid.isOnlyAlphabetMSG;
				}
				if (!valid.isvalidLength(f_otherproff, valid.nameMax, valid.nameMin)) {
					return "Father Profession " + valid.isValidLengthMSG;
				}

			}

		}
	 	*/
		if (mother_name == null || mother_name.trim().equals("")) {

			return "Please Enter Mother Name";

		}
		if (mother_name != null && !mother_name.trim().equals("")) {

			if (!valid.isOnlyAlphabet(mother_name)) {
				return "Mother Name " + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(mother_name, valid.nameMax, valid.nameMin)) {
				return "Mother Name " + valid.isValidLengthMSG;
			}

		}
		
		Date mother_birth_dt = null;
		if (mother_dob != null && !mother_dob.trim().equals("") && !mother_dob.equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(mother_dob)) {
				return valid.isValidDateMSG + " of Mother Date of Birth";
			} else {
				mother_birth_dt = format.parse(mother_dob);
			}
		}
		/*
		if (mother_dob == null || mother_dob.trim().equals("") || mother_dob.trim().equals("DD/MM/YYYY")) {

			return "Please Enter Mother Date of Birth";

		}
	
		if (!mother_dob.trim().equals("") && !mother_dob.equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(mother_dob)) {
				return valid.isValidDateMSG + " of Mother Date of Birth";
			} else {
				mother_birth_dt = format.parse(mother_dob);
			}
		}
		if (mother_adhar_number == null || mother_adhar_number.trim().equals("")) {

			return "Please Enter Mother Aadhaar Number";

		}
		if (mother_adhar_number != "" || mother_adhar_number != null || !mother_adhar_number.trim().equals("")) {
			if (!valid.isValidAadhar(mother_adhar_number)) {
				return valid.isValidAadharMSG;
			}
		}

		if (mother_pan_no != "") {

			if (!valid.isValidPan(mother_pan_no)) {
				return valid.isValidPanMSG;
			}

			if (!valid.PanNoLength(mother_pan_no)) {
				return valid.PanNoMSG;
			}
		}
		if (mother_place_birth == null || mother_place_birth.trim().equals("")) {

			return "Please Enter Mother Place of Birth";

		}
		if (mother_place_birth != null && !mother_place_birth.trim().equals("")) {

			if (!valid.isOnlyAlphabet(mother_place_birth)) {
				return "Mother Place of Birth " + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(mother_place_birth, valid.nameMax, valid.nameMin)) {
				return "Mother Place of Birth " + valid.isValidLengthMSG;
			}

		}
		if (ismotherInservice.equals("yes")) {

			mother_profession = "0";

			if (mother_service == null || mother_service.trim().equals("0")) {

				return "Please Select Mother Service";

			}

			if (mother_personal_no.trim().equals("")) {

				mother_personal_no = null;

			}

			if (mother_service.equals("4")) {

				mother_personal_no = null;

			} else {

				mother_other = null;

			}

		} else {

			if (mother_profession == null || mother_profession.trim().equals("0")) {

				return "Please Select Mother Profession";

			}

			mother_personal_no = null;

			mother_other = null;

			mother_service = null;

			if (request.getParameter("m_proff").equals("OTHERS")) {

				mother_other = m_otherproff;

			}

			if (m_otherproff != null && !m_otherproff.trim().equals("")) {

				if (!valid.isOnlyAlphabet(m_otherproff)) {
					return "Mother Profession " + valid.isOnlyAlphabetMSG;
				}
				if (!valid.isvalidLength(m_otherproff, valid.nameMax, valid.nameMin)) {
					return "Mother Profession " + valid.isValidLengthMSG;
				}

			}
		}
		 */
		String cen_id = request.getParameter("p_id");

		String rvalue = "";

		 try { 

		String hql = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by ,modified_date=:modified_date ,father_name=:father_name, "

				+ "father_dob=:father_dob,father_place_birth=:father_place_birth,father_profession=:father_profession,"

				+ " mother_dob=:mother_dob,mother_place_birth=:mother_place_birth,mother_profession=:mother_profession,mother_name=:mother_name,"

				+ "father_adhar_number=pgp_sym_encrypt(:father_adhar_number,current_setting('miso.version')),mother_adhar_number=pgp_sym_encrypt(:mother_adhar_number,current_setting('miso.version')) , father_pancard_number=pgp_sym_encrypt(:father_pancard_number,current_setting('miso.version')) "

				+ ", mother_pancard_number=pgp_sym_encrypt(:mother_pancard_number,current_setting('miso.version'))"
				+ ",mother_service=:mother_service,mother_other=:mother_other,"

				+ "mother_personal_no=:mother_personal_no,father_service=:father_service,father_other=:father_other,father_personal_no=:father_personal_no "

				+ " where  id=:id";

		Query query = sessionhql.createQuery(hql).setTimestamp("mother_dob", mother_birth_dt)

				.setTimestamp("father_dob", father_birth_dt).setString("father_name", father_name)

				.setString("mother_name", mother_name)

				.setInteger("mother_profession", Integer.parseInt(mother_profession))

				.setInteger("father_profession", Integer.parseInt(father_profession))

				.setString("father_place_birth", father_place_birth)

				.setString("mother_place_birth", mother_place_birth).setInteger("id", Integer.parseInt(cen_id))

				.setString("modified_by", username).setTimestamp("modified_date", date)

				.setString("father_adhar_number", father_adhar_number)

				.setString("mother_adhar_number", mother_adhar_number)

				.setString("mother_service", mother_service).setString("mother_other", mother_other)

				.setString("mother_personal_no", mother_personal_no).setString("father_service", father_service)

				.setString("father_other", father_other).setString("father_personal_no", father_personal_no);

		if (father_pan_no != null && !father_pan_no.trim().equals(""))

			query.setString("father_pancard_number", father_pan_no);

		else

			query.setString("father_pancard_number", null);

		if (father_pan_no != null && !father_pan_no.trim().equals(""))

			query.setString("mother_pancard_number", mother_pan_no);

		else

			query.setString("mother_pancard_number", null);

		rvalue = query.executeUpdate() > 0 ? "Data Saved Successfully" : "Data Not Saved";

		tx.commit();

		
		  } catch (RuntimeException e) {
		  
		  try {
		  
		  tx.rollback();
		  
		  rvalue = "Data Not Saved";
		  
		  } catch (RuntimeException rbe) {
		  
		  rvalue = "Data Not Saved";
		  
		  }
		  
		  } finally {
		  
		  if (sessionhql != null) {
		  
		  sessionhql.close();
		  
		  }
		  
		  }
		return rvalue;

	}

	// ************************************END FAMILY

	// DETAIL*******************************************

	// ************************************START NCC

	// DETAIL*******************************************

	@RequestMapping(value = "/admin/getNCCData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_NCC_EXP> getNCCData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("n_id"));

		String hqlUpdate = " from TB_CENSUS_NCC_EXP where cen_id=:cen_id";

		String approoval_status = request.getParameter("app_status");

		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {

			hqlUpdate += " and status=1 ";

		} else {

			hqlUpdate += " and status=0 ";

		}

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id);

		List<TB_CENSUS_NCC_EXP> list = (List<TB_CENSUS_NCC_EXP>) query.list();

		tx.commit();

		sessionHQL.close();


		return list;

	}

	@RequestMapping(value = "/admin/ncc_action", method = RequestMethod.POST)

	public @ResponseBody String ncc_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		String otu = request.getParameter("otu");

		String ncc_ch_id = request.getParameter("ncc_ch_id");

		String n_id = request.getParameter("n_id");

		String rvalue = "";


		if (otu == null || otu.equals("0")) {

			return "Please select Whether in OTU Div/Jr Div/Sr Div";

		}

		try {

			if (Integer.parseInt(ncc_ch_id) == 0) {

				TB_CENSUS_NCC_EXP ncc = new TB_CENSUS_NCC_EXP();

				ncc.setOtu(otu);

				ncc.setCen_id(Integer.parseInt(n_id));

				ncc.setCreated_by(username);

				ncc.setCreated_on(date);

				int id = (int) sessionhql.save(ncc);

				rvalue = Integer.toString(id);

			} else {

				String hql = "update TB_CENSUS_NCC_EXP set modify_by=:modify_by ,modify_on=:modify_on , otu=:otu "

						+ " where  id=:id";

				Query query = sessionhql.createQuery(hql).setString("otu", otu)

						.setInteger("id", Integer.parseInt(ncc_ch_id)).setString("modify_by", username)

						.setTimestamp("modify_on", new Date());

				rvalue = query.executeUpdate() > 0 ? "update" : "0";

			}

			tx.commit();

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				rvalue = "0";

			} catch (RuntimeException rbe) {

				rvalue = "0";

			}

		} finally {

			if (sessionhql != null) {

				sessionhql.close();

			}

		}

		return rvalue;

	}

	@RequestMapping(value = "/admin/ncc_delete_action", method = RequestMethod.POST)

	public @ResponseBody String ncc_delete_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("ncc_ch_id"));

		try {

			String hqlUpdate = "delete from TB_CENSUS_NCC_EXP where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();

			tx.commit();

			sessionHQL.close();

			if (app > 0) {

				msg = "1";

			} else {

				msg = "0";

			}

		} catch (Exception e) {

		}

		return msg;

	}

	// ************************************END NCC

	// DETAIL*******************************************

	@RequestMapping(value = "/admin/Submit_Approval_Data", method = RequestMethod.POST)

	public @ResponseBody String Submit_Approval_Data(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("p_id"));

		try {

			List<Map<String, Integer>> list = censusAprovedDAO.approveDetailOfcensusByid(id);


			int medcount = list.get(0).get("medcount");

		//	int idcount = list.get(0).get("idcount");

			int addcount = list.get(0).get("addcount");

			int nokcount = list.get(0).get("nokcount");

			int precount = list.get(0).get("precount");

			int ncccount = list.get(0).get("ncccount");

			int qualicount = list.get(0).get("qualicount");

			int langcount = list.get(0).get("langcount");

			int marrcount = list.get(0).get("marrcount");
			int seperateCount = list.get(0).get("seperatecount");
			int widowerCount = list.get(0).get("widowercount");
			int widowCount = list.get(0).get("widowcount");

			int addSFCount = list.get(0).get("addsfcount");

			int nokrelationCount = list.get(0).get("nokrelationcount");

			int divCount = list.get(0).get("divcount");

			int marital_status = list.get(0).get("marital_status");

			int parent_details = list.get(0).get("parent_details");

			/*if (idcount == 0) {

				return "Please Fill Up Identity Card Details";

			}*/

			if (addcount == 0) {

				return "Please Fill Up Address Details";

			}

			if (nokcount == 0) {

				return "Please Fill Up Nok Address Details";

			}

			if (addSFCount != 0 && marital_status != 8) {

				return "Please Save Address Details Again";

			}

			if (nokrelationCount != 0 && marital_status != 8) {

				return "Please Change Nok Relation In Address Details";

			}

			if (parent_details == 0) {

				return "Please Fill Up Parent Details";

			}

			if (marrcount == 0 && marital_status == 8) {

				return "Please Fill Up Marraige Details";

			}

			if (marrcount != 0 && marital_status != 8) {

				return "Please Delete  Marraige Details";

			}

			if (seperateCount == 0 && marital_status == 13) {

				return "Please Fill Up Seperated Details";

			}
			if (seperateCount != 0 && marital_status != 13) {

				return "Please Delete  Seperated Details";

			}

			if (divCount == 0 && marital_status == 9) {

				return "Please Fill Up Divorced Details";

			}
			if (widowCount == 0 && marital_status == 11) {

				return "Please Fill Up Widow Details";

			}

			if (widowerCount == 0 && marital_status == 12) {

				return "Please Fill Up Widower Details";

			}

			if ((divCount != 0 || widowCount != 0 || widowerCount != 0) && marital_status == 10) {

				return "Please Delete  Divorced/Widow/Widower Details";

			}

			if (precount == 0) {

				return "Please Fill Up Pre Cadet Details";

			}

			if (ncccount == 0) {

				return "Please Fill Up Ncc Expreience";

			}

			if (qualicount == 0) {

				return "Please Fill Up Qualification Details";

			}

			if (langcount == 0) {

				return "Please Fill Up language Details";

			}

			if (medcount == 0) {

				return "Please Fill Up Medical Details";

			}

			String hqlUpdate = "update from TB_CENSUS_DETAIL_Parent set status=:status where id=:id";


			int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 0).setInteger("id", id).executeUpdate();

			tx.commit();

			sessionHQL.close();

			if (app > 0) {

				msg = "1";

			} else {

				msg = "0";

			}

		} catch (Exception e) {

		}


		return msg;

	}

	// ******************************GET MEDICAL

	// DATA***********************************************//

	@RequestMapping(value = "/GetMedicalData", method = RequestMethod.POST)

	public @ResponseBody List<String> GetMedicalData(BigInteger comm_id, HttpServletRequest request) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = "FROM TB_CENSUS_MEDICAL_CATEGORY  where  comm_id =:comm_id ";

		String approoval_status = request.getParameter("app_status");

		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {

			hqlUpdate += " and status=1 ";

		} else {

			hqlUpdate += " and status=0 ";

		}

		Query q1 = sessionHQL.createQuery(hqlUpdate);

		q1.setParameter("comm_id", comm_id);

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx.commit();

		return list;

	}

	@RequestMapping(value = "/admin/GetIdentityData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_IDENTITY_CARD> GetIdentityData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		BigInteger id = new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CENSUS_IDENTITY_CARD where comm_id=:comm_id ";

		String approoval_status = request.getParameter("app_status");

		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {

			hqlUpdate += " and status=1 ";

		} else {

			hqlUpdate += " and status=0 ";

		}

		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", id);

		List<TB_CENSUS_IDENTITY_CARD> list = (List<TB_CENSUS_IDENTITY_CARD>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	@RequestMapping(value = "/admin/getallergicData", method = RequestMethod.POST)

	public @ResponseBody List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> getallergicData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		BigInteger id = new BigInteger((request.getParameter("comm_id")));
 
		String hqlUpdate = " from TB_PSG_CENSUS_ALLERGICTOMEDICINE where comm_id=:comm_id   ";

		String approoval_status = request.getParameter("app_status");

		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {

			hqlUpdate += " and status=1 ";

		} else {

			hqlUpdate += " and status=0 ";

		}

		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", id);

		List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> list = (List<TB_PSG_CENSUS_ALLERGICTOMEDICINE>) query.list();

		tx.commit();

		sessionHQL.close();

		

		return list;

	}

	@RequestMapping(value = "/admin/spouse_qualification_action", method = RequestMethod.POST)

	public @ResponseBody String spouse_qualification_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		int currentYear = date.getYear() + 1900;

		String username = session.getAttribute("username").toString();

		String type = request.getParameter("type");

		String examination_pass = request.getParameter("examination_pass");

		String passing_year = request.getParameter("passing_year");

		String div_class = request.getParameter("div_class");

		String subject = request.getParameter("subject");

		String institute = request.getParameter("institute");

		String degree = request.getParameter("degree");

		String specialization = request.getParameter("specialization");

		String exam_other_qua = request.getParameter("exam_other_qua");

		String class_other_qua = request.getParameter("class_other_qua");

		String degree_other = request.getParameter("degree_other");

		String spec_other = request.getParameter("spec_other");

		String qualification_ch_id = request.getParameter("qualification_ch_id");

		String q_id = request.getParameter("q_id");

		String com_id = request.getParameter("com_id");

		String dateofBirthyear = request.getParameter("dateofBirthyear");

		String spouse_id = request.getParameter("spouse_id");

		String rvalue = "";

		if (exam_other_qua.trim().equals("")) {

			exam_other_qua = null;

		}

		if (class_other_qua.trim().equals("")) {

			class_other_qua = null;

		}

		if (degree_other.trim().equals("")) {

			degree_other = null;

		}

		if (spec_other.trim().equals("")) {

			spec_other = null;

		}

		if (type == null || type.equals("0")) {

			return "Please Select The Type";

		}

		if (examination_pass == null || examination_pass.equals("0")) {

			return "Please Select The Examination Passed";

		}
		if (degree == null || degree.equals("0")) {

			return "Please Select The Degree Acquried";

		}
		if (specialization == null || specialization.equals("0")) {

			return "Please Select The Specialization";

		}
		if (spec_other != null && !spec_other.trim().equals("")) {

			if (!valid.isOnlyAlphabet(spec_other)) {
				return " Specialization Other" + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(spec_other, valid.nameMax, valid.nameMin)) {
				return "Specialization Other " + valid.isValidLengthMSG;
			}
		}
		if (passing_year == null || passing_year.trim().equals("")) {

			return "Please Enter The Year of passing";

		}

		if (!passing_year.trim().equals("")) {

			if (Integer.parseInt(passing_year) <= Integer.parseInt(dateofBirthyear)|| Integer.parseInt(passing_year) > currentYear) {
				return "Please Enter The Valid Year of Passing";
			}

		}

		if (div_class == null || div_class.equals("0")) {

			return "Please Select The Div/Grade/PCT(%)";

		}
		if (class_other_qua != null && !class_other_qua.trim().equals("")) {

			if (!valid.isOnlyAlphabet(class_other_qua)) {
				return " Div/Grade/PCT(%) Other " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(class_other_qua, valid.nameMax, valid.nameMin)) {
				return "Div/Grade/PCT(%) Other " + valid.isValidLengthMSG;
			}
		}
		if (subject == null || subject.trim().equals("")) {

			return "Please Select The Subjects";

		}

		if (institute == null || institute.trim().equals("")) {

			return "Please Enter The Institute & place";

		}
		if (institute != null && !institute.trim().equals("")) {

			if (!valid.isOnlyAlphabet(institute)) {
				return " Institute & place " + valid.isOnlyAlphabetMSG;
			}

			if (!valid.isvalidLength(institute, valid.nameMax, valid.nameMin)) {
				return "Institute & place  " + valid.isValidLengthMSG;
			}
		}
		TB_CENSUS_SPOUSE_QUALIFICATION q = new TB_CENSUS_SPOUSE_QUALIFICATION();

		try {
int check_qualification =check_duplicate(new BigInteger(com_id),"TB_CENSUS_SPOUSE_QUALIFICATION");
			if (check_qualification == 0) {
				q.setType(Integer.parseInt(type));

				if (examination_pass != null && !examination_pass.equals("0"))

					q.setExamination_pass(Integer.parseInt(examination_pass));

				if (degree != null && !degree.equals("0"))

					q.setDegree(Integer.parseInt(degree));

				if (specialization != null && !specialization.equals("0"))

					q.setSpecialization(Integer.parseInt(specialization));

				q.setPassing_year(Integer.parseInt(passing_year));

				q.setDiv_class(div_class);

				q.setSubject(subject);

				q.setInstitute(institute);

				q.setCen_id(Integer.parseInt(q_id));

				q.setComm_id(new BigInteger(com_id));

				q.setSpouse_id(Integer.parseInt(spouse_id));

				q.setCreated_by(username);

				q.setCreated_date(date);

				q.setExam_other(exam_other_qua);

				q.setClass_other(class_other_qua);

				q.setDegree_other(degree_other);

				q.setSpecialization_other(spec_other);

				int id = (int) sessionhql.save(q);

				rvalue = Integer.toString(id);

			} else {

				String hql = "update TB_CENSUS_SPOUSE_QUALIFICATION set modified_by=:modify_by ,modified_date=:modify_on ,type=:type,"

						+ " passing_year=:passing_year,div_class=:div_class,subject=:subject,institute=:institute";

				hql += ",examination_pass=:examination_pass,exam_other=:exam_other,class_other=:class_other ,specialization_other=:specialization_other ,degree_other=:degree_other ";

				hql += ",specialization=:specialization,degree=:degree,status=0 ";

				hql += " where  id=:id";

				Query query = sessionhql.createQuery(hql).setInteger("type", Integer.parseInt(type))

						.setInteger("passing_year", Integer.parseInt(passing_year)).setString("div_class", div_class)

						.setString("subject", subject).setString("institute", institute)

						.setInteger("id", Integer.parseInt(qualification_ch_id)).setString("modify_by", username)

						.setTimestamp("modify_on", date).setString("exam_other", exam_other_qua)

						.setString("class_other", class_other_qua).setString("specialization_other", spec_other)

						.setString("degree_other", degree_other);

				if (examination_pass != null && !examination_pass.equals("0"))

					query.setInteger("examination_pass", Integer.parseInt(examination_pass));

				if (degree != null && !degree.equals("0"))

					query.setInteger("degree", Integer.parseInt(degree));

				if (specialization != null && !specialization.equals("0"))

					query.setInteger("specialization", Integer.parseInt(specialization));

				rvalue = query.executeUpdate() > 0 ? "Data Updated Successfully" : "Data Not Updated";

			}

			tx.commit();

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				rvalue = "Data Not Updated";

			} catch (RuntimeException rbe) {

				rvalue = "Data Not Updated";

			}

		} finally {

			if (sessionhql != null) {

				sessionhql.close();

			}

		}

		return rvalue;

	}

	@RequestMapping(value = "/admin/census_medical_categoryAction", method = RequestMethod.POST)

	public @ResponseBody String census_medical_categoryAction(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		Boolean validData = false;

		int sShape_count = Integer.parseInt(request.getParameter("sShape_count").toString()); // total count

		int sShapeOld_count = Integer.parseInt(request.getParameter("sShapeOld_count").toString());// oldcount

		int hShape_count = Integer.parseInt(request.getParameter("hShape_count").toString()); // total count

		int hShapeOld_count = Integer.parseInt(request.getParameter("hShapeOld_count").toString());// oldcount

		int aShape_count = Integer.parseInt(request.getParameter("aShape_count").toString()); // total count

		int aShapeOld_count = Integer.parseInt(request.getParameter("aShapeOld_count").toString());// oldcount

		int pShape_count = Integer.parseInt(request.getParameter("pShape_count").toString()); // total count

		int pShapeOld_count = Integer.parseInt(request.getParameter("pShapeOld_count").toString());// oldcount

		int eShape_count = Integer.parseInt(request.getParameter("eShape_count").toString()); // total count

		int eShapeOld_count = Integer.parseInt(request.getParameter("eShapeOld_count").toString());// oldcount

		int cCope_count = Integer.parseInt(request.getParameter("cCope_count").toString()); // total count

		int cCopeOld_count = Integer.parseInt(request.getParameter("cCopeOld_count").toString());// oldcount

		int oCope_count = Integer.parseInt(request.getParameter("oCope_count").toString()); // total count

		int oCopeOld_count = Integer.parseInt(request.getParameter("oCopeOld_count").toString());// oldcount

		int pCope_count = Integer.parseInt(request.getParameter("pCope_count").toString()); // total count

		int pCopeOld_count = Integer.parseInt(request.getParameter("pCopeOld_count").toString());// oldcount

		int eCope_count = Integer.parseInt(request.getParameter("eCope_count").toString()); // total count

		int eCopeOld_count = Integer.parseInt(request.getParameter("eCopeOld_count").toString());// oldcount


		String mad_classification = request.getParameter("mad_classification_count");

		String p_id = request.getParameter("census_id");

		String comm_id = request.getParameter("comm_id");

		String checkbox_1bx = request.getParameter("check_1bx");

		String _1BX_from_date = request.getParameter("1bx_from_date");

		String _1BX_to_date = request.getParameter("1bx_to_date");

		String _1BX_diagnosis = request.getParameter("1bx_diagnosis_code");

		String msg = "";

		int diffrence;

		Session session3 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx3 = session3.beginTransaction();


		if (mad_classification == null || mad_classification.equals("0")) {

			tx3.rollback();

			msg = "Please Select The Medical Classification ";

			return msg;

		}

		try {

			if (checkbox_1bx != null && checkbox_1bx.equals("1BX")) {

				if ((_1BX_from_date == null || _1BX_from_date.trim().equals("") || _1BX_from_date.equals("DD/MM/YYYY"))

						|| (_1BX_to_date == null || _1BX_to_date.trim().equals("")

								|| _1BX_to_date.equals("DD/MM/YYYY"))) {

					tx3.rollback();

					msg = "Please Enter From Date And To Date For Shape 1BX";

					return msg;

				}
				if (_1BX_from_date != null || _1BX_from_date.trim().equals("") || _1BX_from_date.equals("DD/MM/YYYY")) {
					if (!valid.isValidDate(_1BX_from_date)) {
						msg = valid.isValidDateMSG + " of From ";

						return msg;
					}
				}
				if (_1BX_to_date != null || _1BX_to_date.trim().equals("") || _1BX_to_date.equals("DD/MM/YYYY")) {
					if (!valid.isValidDate(_1BX_to_date)) {
						msg = valid.isValidDateMSG + " of To ";

						return msg;
					}
				}
				if (format.parse(_1BX_to_date).compareTo(format.parse(_1BX_from_date)) < 0) {

					msg = "To Date Should Be Greater Than Or Equal To From Date For Shape 1BX";

					return msg;

				}

				if (_1BX_diagnosis.trim().equals("") || _1BX_diagnosis == null) {

					tx3.rollback();

					msg = "Please  Enter The Diagnosis For Shape 1BX";

					return msg;

				}

				if (!_1BX_diagnosis.trim().equals("")) {

					String[] arrOfStr = _1BX_diagnosis.split("-", 2);

					_1BX_diagnosis = arrOfStr[0];

				}

				String hqlUpdate = "delete from TB_CENSUS_MEDICAL_CATEGORY where cen_id=:id and status=0";

				int app = session3.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(p_id)).executeUpdate();

				String li[] = { "S", "H", "A", "P", "E", "C_C", "C_O", "C_P", "C_E" };

				String li_id[] = { "sShape_ch_id1", "hShape_ch_id1", "aShape_ch_id1", "pShape_ch_id1", "eShape_ch_id1",

						"cCope_ch_id1", "oCope_ch_id1", "pCope_ch_id1", "eCope_ch_id1" };

				for (int i = 0; i < li.length; i++) {

					String ch_id = request.getParameter(li_id[i]);


					TB_CENSUS_MEDICAL_CATEGORY Mad_cat;


					Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();


					Mad_cat.setShape(li[i]);

					if (i == 5 || i == 8) {

						Mad_cat.setOther("");

					}

					if (i < 5) {

						Mad_cat.setShape_status(1);

						Mad_cat.setShape_value("1A");

					} else

						Mad_cat.setShape_value("0");

					if ((_1BX_from_date != null && !_1BX_from_date.trim().equals(""))

							&& (_1BX_to_date != null && !_1BX_to_date.trim().equals(""))) {

						Mad_cat.setFrom_date_1bx(format.parse(_1BX_from_date));

						Mad_cat.setTo_date_1bx(format.parse(_1BX_to_date));

					}

					Mad_cat.setCen_id(Integer.parseInt(p_id));

					Mad_cat.setComm_id(new BigInteger(comm_id));

					Mad_cat.setDiagnosis_1bx(_1BX_diagnosis);

					Mad_cat.setClasification("NIL");


					Mad_cat.setCreated_by(username);

					Mad_cat.setCreated_on(date);

					session3.save(Mad_cat);

				}

				session3.flush();

				session3.clear();

			} else {

				////////////////////////////// S SHAPE ////////////////////////////

				for (int i = 1; i <= sShapeOld_count; i++) {

					String shape_status = request.getParameter("s_status" + i);

					String shape_value = request.getParameter("sShape_value" + i);

					String from_date = request.getParameter("s_from_date" + i);

					String to_date = request.getParameter("s_to_date" + i);

					String diagnosis_1 = request.getParameter("_diagnosis_code1" + i);

					String shape_ch = request.getParameter("sShape_ch_id" + i);


					if (!diagnosis_1.trim().equals("")) {

						String[] arrOfStr = diagnosis_1.split("-", 2);

						diagnosis_1 = arrOfStr[0];

					}

					if (shape_status == null || shape_status.equals("0")) {

						tx3.rollback();

						msg = "Please Select The S-Shape Status " + i + " Row";

						return msg;

					}

					if (shape_value == null || shape_value.equals("0")) {

						tx3.rollback();

						msg = "Please Select The S-Shape Value " + i + " Row";

						return msg;

					}
//26-01-1994
//					if (!shape_status.equals("1")
//
//							|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
//
//							|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {
					

						if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

							tx3.rollback();

							msg = "Please Enter The S-Shape From Date " + i + " Row";

							return msg;

						}
						if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(from_date)) {
								msg = valid.isValidDateMSG + "  of The S-Shape From ";

								return msg;
							}
						}

						if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

							tx3.rollback();

							msg = "Please Enter The S-Shape To Date " + i + " Row";

							return msg;

						}
						if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(to_date)) {
								msg = valid.isValidDateMSG + " of The S-Shape To ";

								return msg;
							}
						}
						if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

							msg = "S-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";

							return msg;

						}
					
					}
					if(i!=1)
					{
						
										
							for(int k=1;k<i;k++)
							{
								String shape_status_pre = request.getParameter("s_status" + k);
								String shape_value_pre = request.getParameter("sShape_value" + k);
								String from_date_pre = request.getParameter("s_from_date" +k);
								String to_date_pre = request.getParameter("s_to_date" + k);
								String diagnosis_1_pre = request.getParameter("_diagnosis_code1" + k);	
								if(shape_status.equals("1"))
								{
									if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
											from_date_pre.equals(from_date)									
											)
									{tx3.rollback();
										msg=" Data Already Exists S-Shape  "+i+"th row";
												return msg;
									}
								}
								else {
									
								
								if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
										from_date_pre.equals(from_date)&&to_date_pre.equals(to_date)									
										)
								{tx3.rollback();
									msg=" Data Already Exists S-Shape  "+i+"th row";
											return msg;
								}
								}
							}
							
								
					}
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3

							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

					Mad_cat.setShape_status(Integer.parseInt(shape_status));

					Mad_cat.setShape_value(shape_value);

//					if (!shape_status.equals("1")
//
//							|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
//
//							|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {

						Mad_cat.setFrom_date(format.parse(from_date));

						Mad_cat.setTo_date(format.parse(to_date));

					}
					else if (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY")) {
						Mad_cat.setFrom_date(format.parse(from_date));
					}

					Mad_cat.setFrom_date_1bx(null);

					Mad_cat.setTo_date_1bx(null);

					Mad_cat.setDiagnosis_1bx(null);

					Mad_cat.setDiagnosis(diagnosis_1);


					Mad_cat.setClasification(mad_classification);

					Mad_cat.setModify_by(username);

					Mad_cat.setModify_on(date);

					Mad_cat.setStatus(0);

					session3.update(Mad_cat);

					session3.flush();

					session3.clear();

				}

				// Logic for INSERT

				diffrence = sShape_count - sShapeOld_count;


				if (diffrence != 0) {

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = sShapeOld_count + 1; i <= sShape_count; i++) {

						String shape_status = request.getParameter("s_status" + i);

						String shape_value = request.getParameter("sShape_value" + i);

						String from_date = request.getParameter("s_from_date" + i);

						String to_date = request.getParameter("s_to_date" + i);

						String diagnosis_1 = request.getParameter("_diagnosis_code1" + i);

						if (!diagnosis_1.trim().equals("")) {

							String[] arrOfStr = diagnosis_1.split("-", 2);

							diagnosis_1 = arrOfStr[0];

						}

						if (shape_status == null || shape_status.equals("0")) {

							tx3.rollback();

							msg = "Please Select The S-Shape Status " + i + " Row";

							return msg;

						}

						if (shape_value == null || shape_value.equals("0")) {

							tx3.rollback();

							msg = "Please Select The S-Shape Value " + i + " Row";

							return msg;

						}
						//26-01-1994
//						if (!shape_status.equals("1")
//
//								|| (from_date != null && !from_date.trim().equals("")
//
//										&& !from_date.equals("DD/MM/YYYY"))
//
//								|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

							if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

								tx3.rollback();

								msg = "Please Enter The S-Shape From Date " + i + " Row";

								return msg;

							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									msg = valid.isValidDateMSG + "  of The S-Shape From ";

									return msg;
								}
							}
							if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

								tx3.rollback();

								msg = "Please Enter The S-Shape To Date " + i + " Row";

								return msg;

							}
							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									msg = valid.isValidDateMSG + " of The S-Shape To ";

									return msg;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

								msg = "S-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";

								return msg;

							}
						
						
						}
						if(i!=1)
						{																				
								for(int k=1;k<i;k++)
								{
									String shape_status_pre = request.getParameter("s_status" + k);
									String shape_value_pre = request.getParameter("sShape_value" + k);
									String from_date_pre = request.getParameter("s_from_date" +k);
									String to_date_pre = request.getParameter("s_to_date" + k);
									String diagnosis_1_pre = request.getParameter("_diagnosis_code1" + k);	
									if (!shape_status.equals("1")) {
									if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
											from_date_pre.equals(from_date)&&to_date_pre.equals(to_date)&&diagnosis_1_pre.equals(diagnosis_1_pre)									
											)
									{tx3.rollback();
										msg=" Data Already Exists S-Shape  "+i+"th row";
												return msg;
									}
									}
									else {
										if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
												from_date_pre.equals(from_date)									
												)
										{tx3.rollback();
											msg=" Data Already Exists S-Shape  "+i+"th row";
													return msg;
										}
									}
								}																		
						}
						Mad_cat.setShape("S");

						Mad_cat.setShape_status(Integer.parseInt(shape_status));

						Mad_cat.setShape_value(shape_value);

//						if (!shape_status.equals("1")
//
//								|| (from_date != null && !from_date.trim().equals("")
//
//										&& !from_date.equals("DD/MM/YYYY"))
//
//								|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

							Mad_cat.setFrom_date(format.parse(from_date));

							Mad_cat.setTo_date(format.parse(to_date));

						}
						else if (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}

						Mad_cat.setDiagnosis(diagnosis_1);
						Mad_cat.setClasification(mad_classification);
						Mad_cat.setCen_id(Integer.parseInt(p_id));
						Mad_cat.setComm_id(new BigInteger(comm_id));
						Mad_cat.setCreated_by(username);
						Mad_cat.setCreated_on(date);
						Mad_cat.setStatus(0);
						int check_s=check_shape(comm_id,"S");
						if(check_s==0)
						{
							session3.save(Mad_cat);
							session3.flush();
							session3.clear();
						}
						else {
							tx3.rollback();

							msg = "Data Already Exists";

							return msg;
						}
						
					}
				}

				////////////////////////////// H SHAPE ////////////////////////////

				for (int i = 1; i <= hShapeOld_count; i++) {

					String shape_status = request.getParameter("h_status" + i);
					String shape_value = request.getParameter("hShape_value" + i);
					String from_date = request.getParameter("h_from_date" + i);
					String to_date = request.getParameter("h_to_date" + i);
					String diagnosis_2 = request.getParameter("_diagnosis_code2" + i);
					String shape_ch = request.getParameter("hShape_ch_id" + i);
					if (!diagnosis_2.trim().equals("")) {

						String[] arrOfStr = diagnosis_2.split("-", 2);

						diagnosis_2 = arrOfStr[0];

					}

					if (shape_status == null || shape_status.equals("0")) {

						tx3.rollback();

						msg = "Please Select The H-Shape Status " + i + " Row";

						return msg;

					}

					if (shape_value == null || shape_value.equals("0")) {

						tx3.rollback();

						msg = "Please Select The H-Shape Value " + i + " Row";

						return msg;

					}

//					if (!shape_status.equals("1")
//
//							|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
//
//							|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {

						if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

							tx3.rollback();

							msg = "Please Enter The H-Shape From Date " + i + " Row";

							return msg;

						}
						if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(from_date)) {
								msg = valid.isValidDateMSG + " of The H-Shape From ";

								return msg;
							}
						}
						if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

							tx3.rollback();

							msg = "Please Enter The H-Shape To Date " + i + " Row";

							return msg;

						}

						if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(to_date)) {
								msg = valid.isValidDateMSG + " of The H-Shape To ";

								return msg;
							}
						}
						if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

							msg = "H-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";

							return msg;

						}
					
					}
					if(i!=1)
					{
						for(int k=1;k<i;k++)
						{
							String shape_status_pre = request.getParameter("h_status" + k);
							String shape_value_pre = request.getParameter("hShape_value" + k);
							String from_date_pre = request.getParameter("h_from_date" +k);
							String to_date_pre = request.getParameter("h_to_date" + k);
				String diagnosis_1_pre = request.getParameter("_diagnosis_code2" + k);
				if (!shape_status.equals("1")){
							if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
									from_date_pre.equals(from_date)&&to_date_pre.equals(to_date)						
									)
							{tx3.rollback();
								msg=" Data Already Exists H-Shape  "+i+"th row";
										return msg;
							}
				}else {
					if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
							from_date_pre.equals(from_date)							
							)
					{tx3.rollback();
						msg=" Data Already Exists H-Shape  "+i+"th row";
								return msg;
					}
				}					
					}
					}
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3

							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

					Mad_cat.setShape_status(Integer.parseInt(shape_status));

					Mad_cat.setShape_value(shape_value);

//					if (!shape_status.equals("1")
//
//							|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
//
//							|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {					

						Mad_cat.setFrom_date(format.parse(from_date));

						Mad_cat.setTo_date(format.parse(to_date));

					}
					else if (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY")) {
						Mad_cat.setFrom_date(format.parse(from_date));
					}

					Mad_cat.setFrom_date_1bx(null);
					Mad_cat.setTo_date_1bx(null);
					Mad_cat.setDiagnosis_1bx(null);
					Mad_cat.setDiagnosis(diagnosis_2);
					Mad_cat.setClasification(mad_classification);
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);
					Mad_cat.setStatus(0);
					session3.update(Mad_cat);
					session3.flush();
					session3.clear();
				}

				// Logic for INSERT

				diffrence = hShape_count - hShapeOld_count;

				if (diffrence != 0) {

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = hShapeOld_count + 1; i <= hShape_count; i++) {

						String shape_status = request.getParameter("h_status" + i);

						String shape_value = request.getParameter("hShape_value" + i);

						String from_date = request.getParameter("h_from_date" + i);

						String to_date = request.getParameter("h_to_date" + i);

						String diagnosis_2 = request.getParameter("_diagnosis_code2" + i);

						if (!diagnosis_2.trim().equals("")) {

							String[] arrOfStr = diagnosis_2.split("-", 2);

							diagnosis_2 = arrOfStr[0];

						}

						if (shape_status == null || shape_status.equals("0")) {

							tx3.rollback();

							msg = "Please Select The H-Shape Status " + i + " Row";

							return msg;

						}

						if (shape_value == null || shape_value.equals("0")) {

							tx3.rollback();

							msg = "Please Select The H-Shape Value " + i + " Row";

							return msg;

						}

//						if (!shape_status.equals("1")
//
//								|| (from_date != null && !from_date.trim().equals("")
//
//										&& !from_date.equals("DD/MM/YYYY"))
//
//								|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
							if (!shape_status.equals("1")) {

							if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

								tx3.rollback();

								msg = "Please Enter The H-Shape From Date " + i + " Row";

								return msg;

							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									msg = valid.isValidDateMSG + " of The H-Shape From ";

									return msg;
								}
							}

							if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

								tx3.rollback();

								msg = "Please Enter The H-Shape To Date " + i + " Row";

								return msg;

							}
							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									msg = valid.isValidDateMSG + " of The H-Shape To ";

									return msg;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

								msg = "H-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";

								return msg;

							}
						
						}
							if(i!=1)
							{
								for(int k=1;k<i;k++)
								{
									String shape_status_pre = request.getParameter("h_status" + k);
									String shape_value_pre = request.getParameter("hShape_value" + k);
									String from_date_pre = request.getParameter("h_from_date" +k);
									String to_date_pre = request.getParameter("h_to_date" + k);
						String diagnosis_1_pre = request.getParameter("_diagnosis_code2" + k);
						if (!shape_status.equals("1")){
									if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
											from_date_pre.equals(from_date)&&to_date_pre.equals(to_date)						
											)
									{tx3.rollback();
										msg=" Data Already Exists H-Shape  "+i+"th row";
												return msg;
									}
						}else {
							if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
									from_date_pre.equals(from_date)							
									)
							{tx3.rollback();
								msg=" Data Already Exists H-Shape  "+i+"th row";
										return msg;
							}
						}					
							}
							}
						Mad_cat.setShape("H");

						Mad_cat.setShape_status(Integer.parseInt(shape_status));

						Mad_cat.setShape_value(shape_value);

//						if (!shape_status.equals("1")
//
//								|| (from_date != null && !from_date.trim().equals("")
//
//										&& !from_date.equals("DD/MM/YYYY"))
//
//								|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

							Mad_cat.setFrom_date(format.parse(from_date));

							Mad_cat.setTo_date(format.parse(to_date));

						}
						else if (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}

						Mad_cat.setDiagnosis(diagnosis_2);


						Mad_cat.setClasification(mad_classification);

						Mad_cat.setCen_id(Integer.parseInt(p_id));

						Mad_cat.setComm_id(new BigInteger(comm_id));

						Mad_cat.setCreated_by(username);

						Mad_cat.setCreated_on(date);

						Mad_cat.setStatus(0);

						
						
						int check_s=check_shape(comm_id,"H");
						if(check_s==0)
						{
							session3.save(Mad_cat);
							session3.flush();
							session3.clear();
						}
						else {
							tx3.rollback();

							msg = "Data Already Exists";

							return msg;
						}
						

					}

				}

				// //////////////////////////////A SHAPE ////////////////////////////

				for (int i = 1; i <= aShapeOld_count; i++) {

					String shape_status = request.getParameter("a_status" + i);

					String shape_value = request.getParameter("aShape_value" + i);

					String from_date = request.getParameter("a_from_date" + i);

					String to_date = request.getParameter("a_to_date" + i);

					String diagnosis_3 = request.getParameter("_diagnosis_code3" + i);

					String shape_ch = request.getParameter("aShape_ch_id" + i);


					if (!diagnosis_3.trim().equals("")) {

						String[] arrOfStr = diagnosis_3.split("-", 2);

						diagnosis_3 = arrOfStr[0];

					}

					if (shape_status == null || shape_status.equals("0")) {

						tx3.rollback();

						msg = "Please Select The A-Shape Status " + i + " Row";

						return msg;

					}

					if (shape_value == null || shape_value.equals("0")) {

						tx3.rollback();

						msg = "Please Select The A-Shape Value " + i + " Row";

						return msg;

					}

//					if (!shape_status.equals("1")
//
//							|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
//
//							|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {

						if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

							tx3.rollback();

							msg = "Please Enter The A-Shape From Date " + i + " Row";

							return msg;

						}
						if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(from_date)) {
								msg = valid.isValidDateMSG + " of The A-Shape From ";

								return msg;
							}
						}

						if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

							tx3.rollback();

							msg = "Please Enter The A-Shape To Date " + i + " Row";

							return msg;

						}
						if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(to_date)) {
								msg = valid.isValidDateMSG + " of The A-Shape To ";

								return msg;
							}
						}
						if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

							msg = "A-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";

							return msg;

						}
					
						
					}
					if(i!=1)
					{
						for(int k=1;k<i;k++)
						{
							String shape_status_pre = request.getParameter("a_status" + k);
							String shape_value_pre = request.getParameter("aShape_value" + k);
							String from_date_pre = request.getParameter("a_from_date" +k);
							String to_date_pre = request.getParameter("a_to_date" + k);
					String diagnosis_3_pre = request.getParameter("_diagnosis_code3" + k);
							if(shape_status.equals("1"))
							{
								if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
										from_date_pre.equals(from_date)										
										)
								{tx3.rollback();
									msg=" Data Already Exists A-Shape  "+i+"th row";
											return msg;
								}
							}else {
							if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
									from_date_pre.equals(from_date)&&to_date_pre.equals(to_date)									
									)
							{tx3.rollback();
								msg=" Data Already Exists A-Shape  "+i+"th row";
										return msg;
							}
							}
								
					}
					}
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3

							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

					Mad_cat.setShape_status(Integer.parseInt(shape_status));

					Mad_cat.setShape_value(shape_value);

//					if (!shape_status.equals("1") || (from_date != null && !from_date.trim().equals(""))
//
//							|| (to_date != null && !to_date.trim().equals(""))) {

					if (!shape_status.equals("1")) {
						Mad_cat.setFrom_date(format.parse(from_date));

						Mad_cat.setTo_date(format.parse(to_date));

					}
					else if (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY")) {
						Mad_cat.setFrom_date(format.parse(from_date));
					}

					Mad_cat.setFrom_date_1bx(null);
					Mad_cat.setTo_date_1bx(null);
					Mad_cat.setDiagnosis_1bx(null);
					Mad_cat.setDiagnosis(diagnosis_3);
					Mad_cat.setClasification(mad_classification);
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);
					Mad_cat.setStatus(0);
					session3.update(Mad_cat);
					session3.flush();
					session3.clear();
				}

				// Logic for INSERT

				diffrence = aShape_count - aShapeOld_count;
				if (diffrence != 0) {
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();
					for (int i = aShapeOld_count + 1; i <= aShape_count; i++) {
						String shape_status = request.getParameter("a_status" + i);
						String shape_value = request.getParameter("aShape_value" + i);
						String from_date = request.getParameter("a_from_date" + i);
						String to_date = request.getParameter("a_to_date" + i);
						String diagnosis_3 = request.getParameter("_diagnosis_code3" + i);
						if (!diagnosis_3.trim().equals("")) {
							String[] arrOfStr = diagnosis_3.split("-", 2);
							diagnosis_3 = arrOfStr[0];
						}
						if (shape_status == null || shape_status.equals("0")) {
							tx3.rollback();
							msg = "Please Select The A-Shape Status " + i + " Row";
							return msg;
						}
						if (shape_value == null || shape_value.equals("0")) {
							tx3.rollback();
							msg = "Please Select The A-Shape Value " + i + " Row";
							return msg;
						}
//						if (!shape_status.equals("1")
//								|| (from_date != null && !from_date.trim().equals("")
//										&& !from_date.equals("DD/MM/YYYY"))
//								|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {
							if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								tx3.rollback();
								msg = "Please Enter The A-Shape From Date " + i + " Row";
								return msg;
							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									msg = valid.isValidDateMSG + " of The A-Shape From ";

									return msg;
								}
							}
							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									msg = valid.isValidDateMSG + " of The A-Shape To ";

									return msg;
								}
							}
							if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

								tx3.rollback();

								msg = "Please Enter The A-Shape To Date " + i + " Row";

								return msg;

							}

							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									msg = valid.isValidDateMSG + " of The A-Shape To ";

									return msg;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

								msg = "A-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";

								return msg;

							}
							
						}
						if(i!=1)
						{
							for(int k=1;k<i;k++)
							{
								String shape_status_pre = request.getParameter("a_status" + k);
								String shape_value_pre = request.getParameter("aShape_value" + k);
								String from_date_pre = request.getParameter("a_from_date" +k);
								String to_date_pre = request.getParameter("a_to_date" + k);
						String diagnosis_3_pre = request.getParameter("_diagnosis_code3" + k);
								if(shape_status.equals("1"))
								{
									if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
											from_date_pre.equals(from_date)										
											)
									{tx3.rollback();
										msg=" Data Already Exists A-Shape  "+i+"th row";
												return msg;
									}
								}else {
								if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
										from_date_pre.equals(from_date)&&to_date_pre.equals(to_date)									
										)
								{tx3.rollback();
									msg=" Data Already Exists A-Shape  "+i+"th row";
											return msg;
								}
								}
									
						}
						}
						Mad_cat.setShape("A");

						Mad_cat.setShape_status(Integer.parseInt(shape_status));

						Mad_cat.setShape_value(shape_value);

//						if (!shape_status.equals("1")
//
//								|| (from_date != null && !from_date.trim().equals("")
//
//										&& !from_date.equals("DD/MM/YYYY"))
//
//								|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

							Mad_cat.setFrom_date(format.parse(from_date));

							Mad_cat.setTo_date(format.parse(to_date));

						}
						else if (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}

						Mad_cat.setDiagnosis(diagnosis_3);

						Mad_cat.setClasification(mad_classification);

						Mad_cat.setCen_id(Integer.parseInt(p_id));

						Mad_cat.setComm_id(new BigInteger(comm_id));

						Mad_cat.setCreated_by(username);

						Mad_cat.setCreated_on(date);

						Mad_cat.setStatus(0);

						int check_s=check_shape(comm_id,"A");
						if(check_s==0)
						{
							session3.save(Mad_cat);
							session3.flush();
							session3.clear();
						}
						else {
							tx3.rollback();

							msg = "Data Already Exists";

							return msg;
						}
//						session3.save(Mad_cat);
//
//						session3.flush();
//
//						session3.clear();

					}

				}

				// //////////////////////////////P SHAPE ////////////////////////////

				for (int i = 1; i <= pShapeOld_count; i++) {

					String shape_status = request.getParameter("p_status" + i);

					String shape_value = request.getParameter("pShape_value" + i);

					String from_date = request.getParameter("p_from_date" + i);

					String to_date = request.getParameter("p_to_date" + i);

					String diagnosis_4 = request.getParameter("_diagnosis_code4" + i);

					String shape_ch = request.getParameter("pShape_ch_id" + i);

					

					if (!diagnosis_4.trim().equals("")) {

						String[] arrOfStr = diagnosis_4.split("-", 2);

						diagnosis_4 = arrOfStr[0];

					}

					if (shape_status == null || shape_status.equals("0")) {

						tx3.rollback();

						msg = "Please Select The P-Shape Status " + i + " Row";

						return msg;

					}

					if (shape_value == null || shape_value.equals("0")) {

						tx3.rollback();

						msg = "Please Select The P-Shape Value " + i + " Row";

						return msg;

					}

//					if (!shape_status.equals("1")
//
//							|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
//
//							|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {

						if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

							tx3.rollback();

							msg = "Please Enter The P-Shape From Date " + i + " Row";

							return msg;

						}
						if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(from_date)) {
								msg = valid.isValidDateMSG + " of The P-Shape From ";

								return msg;
							}
						}

						if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

							tx3.rollback();

							msg = "Please Enter The P-Shape To Date " + i + " Row";

							return msg;

						}

						if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(to_date)) {
								msg = valid.isValidDateMSG + " of The P-Shape To ";

								return msg;
							}
						}
						if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

							msg = "P-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";

							return msg;

						}
					
						
						
						

					}
					if(i!=1)
					{
						
						for(int k=1;k<i;k++)
						{
							String shape_status_pre = request.getParameter("p_status" + k);
							String shape_value_pre = request.getParameter("pShape_value" + k);
							String from_date_pre = request.getParameter("p_from_date" +k);
							String to_date_pre = request.getParameter("p_to_date" + k);
					String diagnosis_4_pre = request.getParameter("_diagnosis_code4" + k);
					if (!shape_status.equals("1")) {
							if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
									from_date_pre.equals(from_date)&&to_date_pre.equals(to_date)
									
									)
							{tx3.rollback();
								msg=" Data Already Exists P-Shape  "+i+"th row";
										return msg;
							}
						}							
						else
						{
							if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
									from_date_pre.equals(from_date)
									
									)
							{tx3.rollback();
								msg=" Data Already Exists P-Shape  "+i+"th row";
										return msg;
							}
						}
						}
					}
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3

							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

					Mad_cat.setShape_status(Integer.parseInt(shape_status));

					Mad_cat.setShape_value(shape_value);

//					if (!shape_status.equals("1")
//
//							|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
//
//							|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {

						Mad_cat.setFrom_date(format.parse(from_date));

						Mad_cat.setTo_date(format.parse(to_date));

					}
					else if (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY")) {
						Mad_cat.setFrom_date(format.parse(from_date));
					}

					Mad_cat.setFrom_date_1bx(null);

					Mad_cat.setTo_date_1bx(null);

					Mad_cat.setDiagnosis_1bx(null);

					Mad_cat.setDiagnosis(diagnosis_4);

					Mad_cat.setClasification(mad_classification);

					Mad_cat.setModify_by(username);

					Mad_cat.setModify_on(date);

					Mad_cat.setStatus(0);

					session3.update(Mad_cat);

					session3.flush();

					session3.clear();

				}

				diffrence = pShape_count - pShapeOld_count;

				

				if (diffrence != 0) {

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = pShapeOld_count + 1; i <= pShape_count; i++) {

						String shape_status = request.getParameter("p_status" + i);

						String shape_value = request.getParameter("pShape_value" + i);

						String from_date = request.getParameter("p_from_date" + i);

						String to_date = request.getParameter("p_to_date" + i);

						String diagnosis_4 = request.getParameter("_diagnosis_code4" + i);

						if (!diagnosis_4.trim().equals("")) {

							String[] arrOfStr = diagnosis_4.split("-", 2);

							diagnosis_4 = arrOfStr[0];

						}

						if (shape_status == null || shape_status.equals("0")) {

							tx3.rollback();

							msg = "Please Select The P-Shape Status " + i + " Row";

							return msg;

						}

						if (shape_value == null || shape_value.equals("0")) {

							tx3.rollback();

							msg = "Please Select The P-Shape Value " + i + " Row";

							return msg;

						}

//						if (!shape_status.equals("1") || (from_date != null && !from_date.trim().equals("")
//
//								&& !from_date.equals("DD/MM/YYYY"))
//
//								|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

							if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

								tx3.rollback();

								msg = "Please Enter The P-Shape From Date " + i + " Row";

								return msg;

							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									msg = valid.isValidDateMSG + " of The P-Shape From ";

									return msg;
								}
							}

							if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

								tx3.rollback();

								msg = "Please Enter The P-Shape To Date " + i + " Row";

								return msg;

							}

							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									msg = valid.isValidDateMSG + " of The P-Shape To ";

									return msg;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

								msg = "P-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";

								return msg;

							}
						
						}
						if(i!=1)
						{
							
							for(int k=1;k<i;k++)
							{
								String shape_status_pre = request.getParameter("p_status" + k);
								String shape_value_pre = request.getParameter("pShape_value" + k);
								String from_date_pre = request.getParameter("p_from_date" +k);
								String to_date_pre = request.getParameter("p_to_date" + k);
						String diagnosis_4_pre = request.getParameter("_diagnosis_code4" + k);
						if (!shape_status.equals("1")) {
								if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
										from_date_pre.equals(from_date)&&to_date_pre.equals(to_date)
										
										)
								{tx3.rollback();
									msg=" Data Already Exists P-Shape  "+i+"th row";
											return msg;
								}
							}							
							else
							{
								if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
										from_date_pre.equals(from_date)
										
										)
								{tx3.rollback();
									msg=" Data Already Exists P-Shape  "+i+"th row";
											return msg;
								}
							}
							}
						}
						Mad_cat.setShape("P");

						Mad_cat.setShape_status(Integer.parseInt(shape_status));

						Mad_cat.setShape_value(shape_value);

//						if (!shape_status.equals("1")
//
//								|| (from_date != null && !from_date.trim().equals("")
//
//										&& !from_date.equals("DD/MM/YYYY"))
//
//								|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

							Mad_cat.setFrom_date(format.parse(from_date));

							Mad_cat.setTo_date(format.parse(to_date));

						}
						else if (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}

						Mad_cat.setDiagnosis(diagnosis_4);

						Mad_cat.setClasification(mad_classification);

						Mad_cat.setCen_id(Integer.parseInt(p_id));

						Mad_cat.setComm_id(new BigInteger(comm_id));

						Mad_cat.setCreated_by(username);

						Mad_cat.setCreated_on(date);

						Mad_cat.setStatus(0);
						int check_s=check_shape(comm_id,"P");
						if(check_s==0)
						{
							session3.save(Mad_cat);
							session3.flush();
							session3.clear();
						}
						else {
							tx3.rollback();

							msg = "Data Already Exists";

							return msg;
						}
						

					}

				}

				// //////////////////////////////E SHAPE ////////////////////////////

				for (int i = 1; i <= eShapeOld_count; i++) {

					String shape_status = request.getParameter("e_status" + i);

					String shape_value = request.getParameter("eShape_value" + i);

					String from_date = request.getParameter("e_from_date" + i);

					String to_date = request.getParameter("e_to_date" + i);

					String diagnosis_5 = request.getParameter("_diagnosis_code5" + i);

					String shape_ch = request.getParameter("eShape_ch_id" + i);


					if (!diagnosis_5.trim().equals("")) {

						String[] arrOfStr = diagnosis_5.split("-", 2);

						diagnosis_5 = arrOfStr[0];

					}

					if (shape_status == null || shape_status.equals("0")) {

						tx3.rollback();

						msg = "Please Select The E-Shape Status " + i + " Row";

						return msg;

					}

					if (shape_value == null || shape_value.equals("0")) {

						tx3.rollback();

						msg = "Please Select The E-Shape Value " + i + " Row";

						return msg;

					}

//					if (!shape_status.equals("1")
//
//							|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
//
//							|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {

						if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

							tx3.rollback();

							msg = "Please Enter The E-Shape From Date " + i + " Row";

							return msg;

						}
						if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(from_date)) {
								msg = valid.isValidDateMSG + " of The E-Shape From ";

								return msg;
							}
						}

						if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

							tx3.rollback();

							msg = "Please Enter The E-Shape To Date " + i + " Row";

							return msg;

						}

						if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(to_date)) {
								msg = valid.isValidDateMSG + " of The E-Shape To ";

								return msg;
							}
						}
						if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

							msg = "E-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";

							return msg;

						}
						
					}
					if(i!=1)
					{
						
						for(int k=1;k<i;k++)
						{
							String shape_status_pre = request.getParameter("e_status" + k);
							String shape_value_pre = request.getParameter("eShape_value" + k);
							String from_date_pre = request.getParameter("e_from_date" +k);
							String to_date_pre = request.getParameter("e_to_date" + k);
							String diagnosis_5_pre = request.getParameter("_diagnosis_code5" + k);
							if (!shape_status.equals("1")){
							if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
									from_date_pre.equals(from_date)&&to_date_pre.equals(to_date)							
									)
							{tx3.rollback();
								msg=" Data Already Exists E-Shape  "+i+"th row";
										return msg;
							}
						}
							else {
								if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
										from_date_pre.equals(from_date)						
										)
								{tx3.rollback();
									msg=" Data Already Exists E-Shape  "+i+"th row";
											return msg;
								}
							}
						
					}
					}
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3

							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

					Mad_cat.setShape_status(Integer.parseInt(shape_status));

					Mad_cat.setShape_value(shape_value);

//					if (!shape_status.equals("1")
//
//							|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
//
//							|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

						Mad_cat.setFrom_date(format.parse(from_date));

						Mad_cat.setTo_date(format.parse(to_date));

					}
						else if (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}

					Mad_cat.setFrom_date_1bx(null);
					Mad_cat.setTo_date_1bx(null);
					Mad_cat.setDiagnosis_1bx(null);
					Mad_cat.setDiagnosis(diagnosis_5);
					Mad_cat.setClasification(mad_classification);
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);
					Mad_cat.setStatus(0);

					session3.update(Mad_cat);

					session3.flush();

					session3.clear();

				}

				diffrence = eShape_count - eShapeOld_count;


				if (diffrence != 0) {

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = eShapeOld_count + 1; i <= eShape_count; i++) {

						String shape_status = request.getParameter("e_status" + i);

						String shape_value = request.getParameter("eShape_value" + i);

						String from_date = request.getParameter("e_from_date" + i);

						String to_date = request.getParameter("e_to_date" + i);

						String diagnosis_5 = request.getParameter("_diagnosis_code5" + i);

						if (!diagnosis_5.trim().equals("")) {

							String[] arrOfStr = diagnosis_5.split("-", 2);

							diagnosis_5 = arrOfStr[0];

						}

						if (shape_status == null || shape_status.equals("0")) {

							tx3.rollback();

							msg = "Please Select The E-Shape Status " + i + " Row";

							return msg;

						}

						if (shape_value == null || shape_value.equals("0")) {

							tx3.rollback();

							msg = "Please Select The E-Shape Value " + i + " Row";

							return msg;

						}

//						if (!shape_status.equals("1")
//
//								|| (from_date != null && !from_date.trim().equals("")
//
//										&& !from_date.equals("DD/MM/YYYY"))
//
//								|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

							if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

								tx3.rollback();

								msg = "Please Enter The E-Shape From Date " + i + " Row";

								return msg;

							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									msg = valid.isValidDateMSG + " of The E-Shape From ";

									return msg;
								}
							}

							if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

								tx3.rollback();

								msg = "Please Enter The E-Shape To Date " + i + " Row";

								return msg;

							}

							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									msg = valid.isValidDateMSG + " of The E-Shape To ";

									return msg;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

								msg = "E-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";

								return msg;

							}
							

						}
						if(i!=1)
						{
							
							for(int k=1;k<i;k++)
							{
								String shape_status_pre = request.getParameter("e_status" + k);
								String shape_value_pre = request.getParameter("eShape_value" + k);
								String from_date_pre = request.getParameter("e_from_date" +k);
								String to_date_pre = request.getParameter("e_to_date" + k);
								String diagnosis_5_pre = request.getParameter("_diagnosis_code5" + k);
								if (!shape_status.equals("1")){
								if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
										from_date_pre.equals(from_date)&&to_date_pre.equals(to_date)							
										)
								{tx3.rollback();
									msg=" Data Already Exists E-Shape  "+i+"th row";
											return msg;
								}
							}
								else {
									if(shape_status_pre.equals(shape_status)&&shape_value_pre.equals(shape_value)&&
											from_date_pre.equals(from_date)						
											)
									{tx3.rollback();
										msg=" Data Already Exists E-Shape  "+i+"th row";
												return msg;
									}
								}
							
						}
						}
						Mad_cat.setShape("E");

						Mad_cat.setShape_status(Integer.parseInt(shape_status));

						Mad_cat.setShape_value(shape_value);

//						if (!shape_status.equals("1")
//
//								|| (from_date != null && !from_date.trim().equals("")
//
//										&& !from_date.equals("DD/MM/YYYY"))
//
//								|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {

							Mad_cat.setFrom_date(format.parse(from_date));

							Mad_cat.setTo_date(format.parse(to_date));

						}

						Mad_cat.setDiagnosis(diagnosis_5);


						Mad_cat.setClasification(mad_classification);

						Mad_cat.setCen_id(Integer.parseInt(p_id));

						Mad_cat.setComm_id(new BigInteger(comm_id));

						Mad_cat.setCreated_by(username);

						Mad_cat.setCreated_on(date);

						Mad_cat.setStatus(0);
						int check_s=check_shape(comm_id,"E");
						if(check_s==0)
						{
							session3.save(Mad_cat);
							session3.flush();
							session3.clear();
						}
						else {
							tx3.rollback();

							msg = "Data Already Exists";

							return msg;
						}
						

					}

				}

				// //////////////////////////////C COPE //////////////////////////////

				for (int i = 1; i <= cCopeOld_count; i++) {

					String cope_ch = request.getParameter("cCope_ch_id" + i);

					String cope_value = request.getParameter("c_cvalue" + i);

					String cope_other = request.getParameter("c_cother" + i);


					if (cope_value.equals("2 [c]") && cope_other.trim().equals("")) {

						tx3.rollback();

						msg = "Please Enter Other " + i + " Row";

						return msg;

					}

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3

							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

					if (cope_value.equals("2 [c]")) {

						Mad_cat.setOther(cope_other);

					} else {

						Mad_cat.setOther("");

					}
					if(i!=1)
					{
						for(int k=1;k<i;k++)
						{
							String cope_value_pre = request.getParameter("c_cvalue" + k);
							String cope_other_pre = request.getParameter("c_cother" + k);
							if(cope_value_pre.equals(cope_value))
							{
								if(cope_other_pre!=null&&cope_other!=null)
								{
									if(cope_other_pre.equals(cope_other))
									{
										tx3.rollback();
										msg=" Data Already Exists C_C  "+i+"th row";
												return msg;
									}
								}
								else {
									tx3.rollback();
									msg=" Data Already Exists C_C  "+i+"th row";
									return msg;
								}
								
							}														
						}
							
					}
					Mad_cat.setFrom_date_1bx(null);

					Mad_cat.setTo_date_1bx(null);

					Mad_cat.setDiagnosis_1bx(null);

					Mad_cat.setShape_value(cope_value);


					Mad_cat.setClasification(mad_classification);

					Mad_cat.setModify_by(username);

					Mad_cat.setModify_on(date);

					Mad_cat.setStatus(0);

					session3.update(Mad_cat);

					session3.flush();

					session3.clear();

				}

				diffrence = cCope_count - cCopeOld_count;


				if (diffrence != 0) {

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = cCopeOld_count + 1; i <= cCope_count; i++) {

						String cope_value = request.getParameter("c_cvalue" + i);

						String cope_other = request.getParameter("c_cother" + i);

						if (cope_value.equals("2 [c]") && cope_other.trim().equals("")) {

							tx3.rollback();

							msg = "Please Enter C-Cope Other " + i + " Row";

							return msg;

						}

						if (cope_value.equals("2 [c]")) {

							Mad_cat.setOther(cope_other);

						} else {

							Mad_cat.setOther(null);

						}
						if(i!=1)
						{
							for(int k=1;k<i;k++)
							{
								String cope_value_pre = request.getParameter("c_cvalue" + k);
								String cope_other_pre = request.getParameter("c_cother" + k);
								if(cope_value_pre.equals(cope_value))
								{
									if(cope_other_pre!=null&&cope_other!=null)
									{
										if(cope_other_pre.equals(cope_other))
										{
											tx3.rollback();
											msg=" Data Already Exists C_C  "+i+"th row";
													return msg;
										}
									}
									else {
										tx3.rollback();
										msg=" Data Already Exists C_C  "+i+"th row";
										return msg;
									}
									
								}														
							}
								
						}
						
						
						
						
						
						Mad_cat.setShape("C_C");

						Mad_cat.setShape_value(cope_value);


						Mad_cat.setClasification(mad_classification);

						Mad_cat.setCen_id(Integer.parseInt(p_id));

						Mad_cat.setComm_id(new BigInteger (comm_id));

						Mad_cat.setCreated_by(username);

						Mad_cat.setCreated_on(date);

						Mad_cat.setStatus(0);
						int check_s=check_shape(comm_id,"C_C");
						if(check_s==0)
						{
							session3.save(Mad_cat);
							session3.flush();
							session3.clear();
						}
						else {
							tx3.rollback();

							msg = "Data Already Exists";

							return msg;
						}
						

					}

				}

				// //////////////////////////////O COPE //////////////////////////////

				for (int i = 1; i <= oCopeOld_count; i++) {

					String cope_ch = request.getParameter("oCope_ch_id" + i);

					String cope_value = request.getParameter("c_ovalue" + i);
					if(i!=1)
					{												
						for (int k = 1; k < i; k++) {
							String cope_value_pre = request.getParameter("c_ovalue" + k);
						if(cope_value_pre.equals(cope_value))
						{
							tx3.rollback();
							msg="Data Already exist C_O "+i+"th row ";
							return msg;
						}
						}
					}

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3

							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

					Mad_cat.setFrom_date_1bx(null);

					Mad_cat.setTo_date_1bx(null);

					Mad_cat.setDiagnosis_1bx(null);

					Mad_cat.setShape_value(cope_value);

					Mad_cat.setClasification(mad_classification);

					Mad_cat.setModify_by(username);

					Mad_cat.setModify_on(date);

					Mad_cat.setStatus(0);

					session3.update(Mad_cat);

					session3.flush();

					session3.clear();

				}

				diffrence = oCope_count - oCopeOld_count;


				if (diffrence != 0) {

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = oCopeOld_count + 1; i <= oCope_count; i++) {

						String cope_value = request.getParameter("c_ovalue" + i);


						Mad_cat.setShape("C_O");

						Mad_cat.setShape_value(cope_value);


						Mad_cat.setClasification(mad_classification);

						Mad_cat.setCen_id(Integer.parseInt(p_id));

						Mad_cat.setComm_id(new BigInteger(comm_id));

						Mad_cat.setCreated_by(username);

						Mad_cat.setCreated_on(date);

						Mad_cat.setStatus(0);
						if(i!=1)
						{
							for (int k = 1; k < i; k++) {
								String cope_value_pre = request.getParameter("c_ovalue" + k);
							if(cope_value_pre.equals(cope_value))
							{
								tx3.rollback();
								msg="Data Already exist C_O "+i+"th row ";
								return msg;
							}
							}
						}
						int check_s=check_shape(comm_id,"C_O");
						if(check_s==0)
						{
							session3.save(Mad_cat);
							session3.flush();
							session3.clear();
						}
						else {
							tx3.rollback();

							msg = "Data Already Exists";

							return msg;
						}
//						session3.save(Mad_cat);
//
//						session3.flush();
//
//						session3.clear();

					}

				}

				// //////////////////////////////P COPE //////////////////////////////

				for (int i = 1; i <= pCopeOld_count; i++) {

					String cope_ch = request.getParameter("pCope_ch_id" + i);

					String cope_value = request.getParameter("c_pvalue" + i);

					if(i!=1)
					{
						
						for (int k = 1; k < i; k++) {
							String cope_value_pre = request.getParameter("c_pvalue" + k);
						if(cope_value_pre.equals(cope_value))
						{
							tx3.rollback();
							msg="Data Already exist C_P "+i+"th row ";
							return msg;
						}
						}
					}
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3

							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

					Mad_cat.setFrom_date_1bx(null);

					Mad_cat.setTo_date_1bx(null);

					Mad_cat.setDiagnosis_1bx(null);

					Mad_cat.setShape_value(cope_value);


					Mad_cat.setClasification(mad_classification);

					Mad_cat.setModify_by(username);

					Mad_cat.setModify_on(date);

					Mad_cat.setStatus(0);

					session3.update(Mad_cat);

					session3.flush();

					session3.clear();

				}

				diffrence = pCope_count - pCopeOld_count;

				

				if (diffrence != 0) {

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = pCopeOld_count + 1; i <= pCope_count; i++) {

						String cope_value = request.getParameter("c_pvalue" + i);
						if(i!=1)
						{
							for (int k = 1; k < i; k++) {
							String cope_value_pre = request.getParameter("c_pvalue" + k);
							if(cope_value_pre.equals(cope_value))
							{
								tx3.rollback();
								msg="Data Already exist C_P "+i+"th row ";
								return msg;
							}
							}
						}

						Mad_cat.setShape("C_P");

						Mad_cat.setShape_value(cope_value);

						Mad_cat.setClasification(mad_classification);

						Mad_cat.setCen_id(Integer.parseInt(p_id));

						Mad_cat.setComm_id(new BigInteger(comm_id));

						Mad_cat.setCreated_by(username);

						Mad_cat.setCreated_on(date);

						Mad_cat.setStatus(0);
						int check_s=check_shape(comm_id,"C_P");
						if(check_s==0)
						{
							session3.save(Mad_cat);
							session3.flush();
							session3.clear();
						}
						else {
							tx3.rollback();

							msg = "Data Already Exists";

							return msg;
						}
//						session3.save(Mad_cat);
//
//						session3.flush();
//
//						session3.clear();

					}

				}

				// //////////////////////////////E COPE //////////////////////////////

				for (int i = 1; i <= eCopeOld_count; i++) {

					String cope_ch = request.getParameter("eCope_ch_id" + i);

					String cope_value = request.getParameter("c_evalue" + i);

					String cope_sub_value = request.getParameter("c_esubvalue" + i);

					String cope_other = request.getParameter("c_esubvalueother" + i);


					if (cope_value.equals("1") && cope_sub_value.equals("0")) {

						tx3.rollback();

						msg = "Please Select The Cope Sub Value " + i + " Row";

						return msg;

					}

					if (cope_sub_value.equals("k") && cope_other.trim().equals("")) {

						tx3.rollback();

						msg = "Please Enter Other " + i + " Row";

						return msg;

					}

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3

							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

					if (cope_value.equals("1")) {

						Mad_cat.setShape_sub_value(cope_sub_value);

					} else {

						Mad_cat.setShape_sub_value(null);

					}

					if (cope_sub_value.equals("k")) {

						Mad_cat.setOther(cope_other);

					} else {

						Mad_cat.setOther(null);

					}
					
					if(  i!=1)
					{
				for(int k=1;k<i;k++)
				{
					String cope_value_pre = request.getParameter("c_evalue" + k);
					String cope_sub_value_pre = request.getParameter("c_esubvalue" + k);
					if(cope_value_pre.equals(cope_value) &&cope_value!=null&&cope_sub_value!=null)
							{						
							if(cope_value_pre.equals(cope_value) && cope_sub_value_pre.equals(cope_sub_value)) {
								tx3.rollback();
								msg = "Data Already Exist For E-Cope Sub Value " + i + " Row";
								return msg;
							}
							}			
					if(cope_value_pre.equals(cope_value) &&cope_value.equals(null)&&cope_sub_value.equals(null))
					{						
						tx3.rollback();
						msg = "Data Already Exist For E-Cope Sub Value " + i + " Row";
						return msg;					
					}
				}
					}
					

					Mad_cat.setFrom_date_1bx(null);

					Mad_cat.setTo_date_1bx(null);

					Mad_cat.setDiagnosis_1bx(null);

					Mad_cat.setShape_value(cope_value);


					Mad_cat.setClasification(mad_classification);

					Mad_cat.setModify_by(username);

					Mad_cat.setModify_on(date);

					Mad_cat.setStatus(0);

					session3.update(Mad_cat);

					session3.flush();

					session3.clear();

				}

				diffrence = eCope_count - eCopeOld_count;


				if (diffrence != 0) {

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = eCopeOld_count + 1; i <= eCope_count; i++) {

						String cope_value = request.getParameter("c_evalue" + i);

						String cope_sub_value = request.getParameter("c_esubvalue" + i);

						String cope_other = request.getParameter("c_esubvalueother" + i);

						if (cope_value.equals("1") && cope_sub_value.equals("0")) {

							tx3.rollback();

							msg = "Please Select The E-Cope Sub Value " + i + " Row";

							return msg;

						}

						if (cope_sub_value.equals("k") && cope_other.trim().equals("")) {

							tx3.rollback();

							msg = "Please Enter E-Cope Other " + i + " Row";

							return msg;

						}

						if (cope_value.equals("1")) {

							Mad_cat.setShape_sub_value(cope_sub_value);

						} else {

							Mad_cat.setShape_sub_value("0");

						}

						if (cope_sub_value.equals("k")) {

							Mad_cat.setOther(cope_other);

						} else {

							Mad_cat.setOther("");

						}

						if(  i!=1)
						{
					for(int k=1;k<i;k++)
					{
						String cope_value_pre = request.getParameter("c_evalue" + k);
						String cope_sub_value_pre = request.getParameter("c_esubvalue" + k);
						if(cope_value_pre.equals(cope_value) &&cope_value!=null&&cope_sub_value!=null)
								{						
								if(cope_value_pre.equals(cope_value) && cope_sub_value_pre.equals(cope_sub_value)) {
									tx3.rollback();
									msg = "Data Already Exist For E-Cope Sub Value " + i + " Row";
									return msg;
								}
								}			
						if(cope_value_pre.equals(cope_value) &&cope_value.equals(null)&&cope_sub_value.equals(null))
						{						
							tx3.rollback();
							msg = "Data Already Exist For E-Cope Sub Value " + i + " Row";
							return msg;					
						}
					}
						}
																												
						Mad_cat.setShape("C_E");

						Mad_cat.setShape_value(cope_value);

						Mad_cat.setClasification(mad_classification);

						Mad_cat.setCen_id(Integer.parseInt(p_id));

						Mad_cat.setComm_id(new BigInteger(comm_id));

						Mad_cat.setCreated_by(username);

						Mad_cat.setCreated_on(date);

						Mad_cat.setStatus(0);
						int check_s=check_shape(comm_id,"C_E");
						if(check_s==0)
						{
							session3.save(Mad_cat);
							session3.flush();
							session3.clear();
						}
						else {
							tx3.rollback();
							msg = "Data Already Exists";

							return msg;
						}
//						session3.save(Mad_cat);
//
//						session3.flush();
//
//						session3.clear();

					}

				}

			}

			// END LOGIC OF INSERT

			tx3.commit();

			msg = "1";

		} catch (RuntimeException e) {

			try {
				tx3.rollback();
				msg = "Data not Updated";

			} catch (RuntimeException rbe) {				
				msg = "Data not Updated";
			}

		} finally {

			if (session3 != null) {
				session3.close();
			}
		}
		return msg;

	}



	private int check_shape(String comm_id, String shape) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();
int r=0;

	String querry="SELECT CASE WHEN id IS NULL THEN 0 ELSE id END AS census_id  from TB_CENSUS_MEDICAL_CATEGORY    WHERE  comm_id = :comm_id and shape =:shape";

     List<Integer> m=sessionHQL.createQuery(querry).setBigInteger("comm_id",new BigInteger(comm_id) ).setString("shape", shape).list();
if(!m.isEmpty())
{
	r= m.get(0);
}
else {
	r=0;
}
	sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return r;
	}

	@RequestMapping(value = "/censusIdentityConvertpath", method = RequestMethod.GET)

	public void censusIdentityConvertpath(@ModelAttribute("i_id") String i_ch_id, ModelMap model,

			HttpServletRequest request, HttpServletResponse response) throws IOException {

		final int BUFFER_SIZE = 4096;

		String i_id = i_ch_id;
		

		String filePath = censusAprovedDAO.getcensusIdentityImagePath(i_id);


		model.put("filePath", filePath);

		ServletContext context = request.getSession().getServletContext();

		try {

			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

				String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_Image.jpg";

				File downloadFile = new File(fullPath);

				FileInputStream inputStream = new FileInputStream(downloadFile);

				String mimeType = context.getMimeType(fullPath);

				if (mimeType == null) {

					mimeType = "application/octet-stream";

				}

				response.setContentType(mimeType);

				response.setContentLength((int) downloadFile.length());

				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];

				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {

					outStream.write(buffer, 0, bytesRead);

				}

				inputStream.close();

				outStream.close();

			} else {

				String fullPath = filePath;

				File downloadFile = new File(fullPath);

				FileInputStream inputStream = new FileInputStream(downloadFile);

				String mimeType = context.getMimeType(fullPath);

				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}

				response.setContentType(mimeType);

				response.setContentLength((int) downloadFile.length());

				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];

				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {

					outStream.write(buffer, 0, bytesRead);

				}

				inputStream.close();

				outStream.close();

			}

		} catch (Exception ex) {


			String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_Image.jpg";

			File downloadFile = new File(fullPath);

			FileInputStream inputStream = new FileInputStream(downloadFile);

			String mimeType = context.getMimeType(fullPath);

			if (mimeType == null) {

				mimeType = "application/octet-stream";

			}

			response.setContentType(mimeType);

			response.setContentLength((int) downloadFile.length());

			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];

			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {

				outStream.write(buffer, 0, bytesRead);

			}

			inputStream.close();

			outStream.close();

		}

	}
	
	@RequestMapping(value = "/AllCopeRemove", method = RequestMethod.POST)

	public ModelAndView AllCopeRemove(ModelMap Mmap, HttpSession session, HttpServletRequest request,

			@RequestParam(value = "msg", required = false) String msg,

			@RequestParam(value = "id9", required = false) String id,

			@RequestParam(value = "personnel_no9", required = false) String personnel_no,
			@RequestParam(value = "mlabel9", required = false) String mlabel) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("search_censusUrl", roleid);

		if (val == false) {

			return new ModelAndView("AccessTiles");

		}

		if (request.getHeader("Referer") == null) {

			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");

		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();
		try {

		

		// String id = request.getParameter("id");

		String[] id_array = String.valueOf(id).split(",");

		int app = 0;

		for (int i = 0; i < id_array.length; i++) {

			String id1 = id_array[i];

			String hqlUpdate = "delete from TB_CENSUS_MEDICAL_CATEGORY where id=:id and status= 0";

			app = sessionHQL.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(id1)).executeUpdate();
		}
		tx.commit();
		}
		catch (Exception e) {
			
		}
		finally {
			sessionHQL.close();
		}
		

		

		Mmap.put("personnel_no", personnel_no);
		Mmap.put("mlabel", mlabel);

		return new ModelAndView("redirect:form");

	}
	@RequestMapping(value = "/check_perasonel_no", method = RequestMethod.POST)

	 @ResponseBody public   List<String> check_perasonel_no(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "comm_id", required = false) String comm_id) {

			List<String> r = new ArrayList<String>();
			List<List<String>> myListofLists = new ArrayList<List<String>>();

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionHQL.beginTransaction();
			try {

				String querry = "select count(census.comm_id) from TB_CENSUS_DETAIL_Parent census"
						+ " where "
						+ " census.comm_id=:comm_id ";

				List<Long> list = sessionHQL.createQuery(querry).setParameter("comm_id", new BigInteger(comm_id)).list();

				String querry2 = "  select count(nok.comm_id) from "
						+ " TB_CENSUS_ADDRESS address , TB_CENSUS_NOK nok where nok.comm_id=address.comm_id and address.comm_id=:comm_id";

				List<Long> list2 = sessionHQL.createQuery(querry2).setParameter("comm_id", new BigInteger(comm_id))
						.list();

				String querry3 = " select count(comm_id) from TB_CENSUS_DETAIL_Parent where father_name is not null and mother_name is not null and comm_id=:comm_id";

				List<Long> list3 = sessionHQL.createQuery(querry3).setParameter("comm_id", new BigInteger(comm_id))
						.list();

				String querry4 = " select count(comm_id) from TB_CENSUS_FOREIGN_COUNTRY where comm_id=:comm_id";
				List<Long> list4 = sessionHQL.createQuery(querry4).setParameter("comm_id", new BigInteger(comm_id))
						.list();

				String querry5 = " select count(comm_id) from TB_CENSUS_CADET where comm_id=:comm_id";
				List<Long> list5 = sessionHQL.createQuery(querry5).setParameter("comm_id", new BigInteger(comm_id))
						.list();

				String querry6 = " select count(comm_id) from TB_CENSUS_QUALIFICATION where comm_id=:comm_id";
				List<Long> list6 = sessionHQL.createQuery(querry6).setParameter("comm_id", new BigInteger(comm_id))
						.list();

				String querry7 = " select count(comm_id) from TB_CENSUS_LANGUAGE where comm_id=:comm_id";
				List<Long> list7 = sessionHQL.createQuery(querry7).setParameter("comm_id", new BigInteger(comm_id))
						.list();

				String querry8 = " select count(comm_id) from TB_CENSUS_MEDICAL_CATEGORY where comm_id=:comm_id";
				List<Long> list8 = sessionHQL.createQuery(querry8).setParameter("comm_id", new BigInteger(comm_id))
						.list();

if(!list.isEmpty())
{
if(list.get(0)==0)
{
	 r.add( "personal_btn");
}	 
}
if(!list2.isEmpty())
{
	 if(list2.get(0)==0)
	 {
	 	 r.add( "add_btn_re");
	 }
}
if(!list3.isEmpty())
{
	 if(list3.get(0)==0)
	 {
	 	 r.add( "family_btn");
	 }
}
//if(!list4.isEmpty())
//{
//	 if(list4.get(0)==0)
//	 {
//	 	 r.add( "foreign_btn");
//	 }
//}
if(!list5.isEmpty())
{
	 if(list5.get(0)==0)
	 {
	 	 r.add( "pre_cadet_btn");
	 }
}
if(!list6.isEmpty())
{
	 if(list6.get(0)==0)
	 {
	 	 r.add( "qualification_btn");
	 }
}
if(!list7.isEmpty())
{
	 if(list7.get(0)==0)
	 {
	 	 r.add( "language_btn");
	 }
}
if(!list8.isEmpty())
{
	 if(list8.get(0)==0)
	 {
	 	 r.add( "medical_details_bt");
	 }
}


			tx.commit();
			return r;
		} catch (Exception e) {
e.printStackTrace();
		} finally {
			sessionHQL.close();
		}

		return r;
	}
	
	
//	--------------- pranay 07.06
	
	@RequestMapping(value = "/GetModifyCensusData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_DETAIL_Parent> GetModifyCensusData(BigInteger comm_id, HttpServletRequest request) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

			String hql = "FROM TB_CENSUS_DETAIL_Parent c where comm_id = :comm_id and status=1";
			Query q1 = sessionHQL.createQuery(hql);
			q1.setParameter("comm_id", comm_id);

			@SuppressWarnings("unchecked")
			List<TB_CENSUS_DETAIL_Parent> list = (List<TB_CENSUS_DETAIL_Parent>) q1.list();

			tx.commit();
			return list;
	}
	
	
	
	
	@RequestMapping(value = "/admin/getModifyNCCData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_NCC_EXP> getModifyNCCData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("n_id"));
		String hqlUpdate = " from TB_CENSUS_NCC_EXP where cen_id=:cen_id and status=1";


		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id);
		List<TB_CENSUS_NCC_EXP> list = (List<TB_CENSUS_NCC_EXP>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	
	
	
	@RequestMapping(value = "/admin/getModifyallergicData", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> getModifyallergicData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String msg = "";
		BigInteger id = new BigInteger((request.getParameter("comm_id")));
		String hqlUpdate = " from TB_PSG_CENSUS_ALLERGICTOMEDICINE where comm_id=:comm_id and status=1 ";

		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", id);
		List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> list = (List<TB_PSG_CENSUS_ALLERGICTOMEDICINE>) query.list();

		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	
	
	
	
	
	@RequestMapping(value = "/admin/getModifyPreCadetData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_CADET> getModifyPreCadetData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		String hqlUpdate = " from TB_CENSUS_CADET where census_id=:census_id and status=1 ";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id);
		List<TB_CENSUS_CADET> list = (List<TB_CENSUS_CADET>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}


	@RequestMapping(value = "/admin/Personnel_DetailModifyaction", method = RequestMethod.POST)

		public @ResponseBody Map<String, String> Personnel_DetailModifyaction(ModelMap Mmap, HttpSession session,

				HttpServletRequest request, MultipartHttpServletRequest mul) throws IOException, ParseException {

			String fname = "";

			String extension = "";

			String allergic_radio = request.getParameter("allergic_radio");

			int allergic_count = Integer.parseInt(request.getParameter("allergic_count"));

			int allergicOld_count = Integer.parseInt(request.getParameter("allergicOld_count"));
			Map<String, String> data = new HashMap<>();

			int census_id = 0;

			if (Integer.parseInt(request.getParameter("census_id")) != 0) {

				census_id = Integer.parseInt(request.getParameter("census_id"));

			}

			int med_id = 0;

			if (Integer.parseInt(request.getParameter("med_id")) != 0) {

				med_id = Integer.parseInt(request.getParameter("med_id"));

			}

			Date date = new Date();

			String username = session.getAttribute("username").toString();

			String msg = "";

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionHQL.beginTransaction();

			int com_institute = 0;

			int training_institute = 0;

			int district_birth = 0;

			int state_birth = 0;

			int country_birth = 0;

			int nationality = 0;

			int religion = 0;

			int marital_status = 0;

			int blood_group = 0;

			int mother = 0;

			int mother_tongue = 0;

			int ncc_type = 0;

			int height = 0;

			BigInteger comm_id = BigInteger.ZERO;

			// BigInteger adhar_number = BigInteger.ZERO;

			String first_name = request.getParameter("first_name");

			String middle_name = request.getParameter("middle_name");

			String last_name = request.getParameter("last_name");

			String pl_birth = request.getParameter("place_birth");

			String d_birth = request.getParameter("district_birth");

			String s_birth = request.getParameter("state_birth");

			String coun_birth = request.getParameter("country_birth");

			String nat = request.getParameter("nationality");

			String reli = request.getParameter("religion");

			String blood = request.getParameter("blood_group");

			String r = request.getParameter("rh");

			String h = request.getParameter("height");

			String adhar1 = request.getParameter("adhar_number1");

			String adhar2 = request.getParameter("adhar_number2");

			String adhar3 = request.getParameter("adhar_number3");

			String co_id = request.getParameter("comm_id");

			String border_area = request.getParameter("border_area");

			String mar_status = request.getParameter("marital_status");

			String id_card_no = request.getParameter("id_card_no");

			String issue_dt = request.getParameter("issue_dt");
			String father_name = request.getParameter("father_name");
			String mother_name = request.getParameter("mother_name");
			String id_marks = request.getParameter("id_marks");

			String hair_colour = request.getParameter("hair_colour");

			String eye_colour = request.getParameter("eye_colour");

			String issue_authority = request.getParameter("issue_authority_sus");

			String mt = request.getParameter("mother_tongue");

			String ncc = request.getParameter("ncc_type");

			String com_ins = request.getParameter("com_institute");

			String com_bn_id = request.getParameter("com_bn_id");

			String com_company_id = request.getParameter("com_company_id");

			String training_ins = request.getParameter("training_institute");

			String training_bn_id = request.getParameter("training_bn_id");

			String training_company_id = request.getParameter("training_company_id");

			String country_birth_other = request.getParameter("country_birth_other");

			String state_birth_other = request.getParameter("state_birth_other");

			String district_birth_other = request.getParameter("district_birth_other");

			String nationality_other = request.getParameter("nationality_other");

			String mother_tongue_other = request.getParameter("mother_tongue_other");

			String religion_other = request.getParameter("religion_other");

			String hair_colour_other = request.getParameter("hair_colour_other");

			String eye_colour_other = request.getParameter("eye_colour_other");

			String com_platoon_id = request.getParameter("com_platoon_id");

			String pan_no = request.getParameter("pan_no");

			String org_domicile_country = request.getParameter("org_domicile_Country");

			String org_domicile_tehsil = request.getParameter("org_domicile_tehsil");

			String org_domicile_state = request.getParameter("org_domicile_state");

			String org_domicile_district = request.getParameter("org_domicile_district");
			String org_domicile_Country_other = request.getParameter("org_domicile_Country_other");

			String org_domicile_state_other = request.getParameter("org_domicile_state_other");

			String org_domicile_district_other = request.getParameter("org_domicile_district_other");

			String org_domicile_tehsil_other = request.getParameter("org_domicile_tehsil_other");

			String org_domicile_Country_otherV = request.getParameter("org_co");

			String org_domicile_state_otherV = request.getParameter("org_so");

			String org_domicile_district_otherV = request.getParameter("org_do");

			String org_domicile_tehsil_otherV = request.getParameter("org_to");

			// pre-cadet

			Date fr_dt = null;
			Date fr_to = null;

			String cadet_status = request.getParameter("cadet_status1");

			String isgazetted = null;

			String isCivil = null;

			String designation = null;

			String emp_name = null;

			String army_no = null;

			String date_form = null;

			String date_to = null;

			String total_rank = null;

			String unit_reg = null;

			String competency = "0";

			String competency_other = null;

			int medDtlFillIn3008 = Integer.parseInt(request.getParameter("medDtlFillIn3008").toString());
			// medical detail

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

			Boolean validData = false;

			int sShape_count = Integer.parseInt(request.getParameter("sShape_count").toString()); // total count

			int sShapeOld_count = Integer.parseInt(request.getParameter("sShapeOld_count").toString());// oldcount

			int hShape_count = Integer.parseInt(request.getParameter("hShape_count").toString()); // total count

			int hShapeOld_count = Integer.parseInt(request.getParameter("hShapeOld_count").toString());// oldcount

			int aShape_count = Integer.parseInt(request.getParameter("aShape_count").toString()); // total count

			int aShapeOld_count = Integer.parseInt(request.getParameter("aShapeOld_count").toString());// oldcount

			int pShape_count = Integer.parseInt(request.getParameter("pShape_count").toString()); // total count

			int pShapeOld_count = Integer.parseInt(request.getParameter("pShapeOld_count").toString());// oldcount

			int eShape_count = Integer.parseInt(request.getParameter("eShape_count").toString()); // total count

			int eShapeOld_count = Integer.parseInt(request.getParameter("eShapeOld_count").toString());// oldcount

			int cCope_count = Integer.parseInt(request.getParameter("cCope_count").toString()); // total count

			int cCopeOld_count = Integer.parseInt(request.getParameter("cCopeOld_count").toString());// oldcount

			int oCope_count = Integer.parseInt(request.getParameter("oCope_count").toString()); // total count

			int oCopeOld_count = Integer.parseInt(request.getParameter("oCopeOld_count").toString());// oldcount

			int pCope_count = Integer.parseInt(request.getParameter("pCope_count").toString()); // total count

			int pCopeOld_count = Integer.parseInt(request.getParameter("pCopeOld_count").toString());// oldcount

			int eCope_count = Integer.parseInt(request.getParameter("eCope_count").toString()); // total count

			int eCopeOld_count = Integer.parseInt(request.getParameter("eCopeOld_count").toString());// oldcount

			String mad_classification = request.getParameter("mad_classification_count");

			String checkbox_1bx = request.getParameter("check_1bx");

			String _1BX_from_date = request.getParameter("1bx_from_date");

			String _1BX_to_date = request.getParameter("1bx_to_date");

			String _1BX_diagnosis = request.getParameter("1bx_diagnosis_code");

			int diffrence;

			Date is_dt = null;

			if (com_ins != null && !com_ins.trim().equals("")) {

				com_institute = Integer.parseInt(com_ins);

			}

			if (training_ins != null && !training_ins.trim().equals("")) {

				training_institute = Integer.parseInt(training_ins);

			}

			if (mt != null && !mt.trim().equals("")) {

				mother_tongue = Integer.parseInt(mt);

			}

			if (ncc != null && !ncc.trim().equals("")) {

				ncc_type = Integer.parseInt(ncc);

			}

			if (d_birth != null && !d_birth.trim().equals("")) {

				district_birth = Integer.parseInt(d_birth);

			}

			if (s_birth != null && !s_birth.trim().equals("")) {

				state_birth = Integer.parseInt(s_birth);

			}

			if (coun_birth != null && !coun_birth.trim().equals("")) {

				country_birth = Integer.parseInt(coun_birth);

			}

			if (nat != null && !nat.trim().equals("")) {

				nationality = Integer.parseInt(nat);

			}

			if (reli != null && !reli.trim().equals("")) {

				religion = Integer.parseInt(reli);

			}

			if (mar_status != null && !mar_status.trim().equals("")) {

				marital_status = Integer.parseInt(mar_status);

			}

			if (blood != null && !blood.trim().equals("")) {

				blood_group = Integer.parseInt(blood);

			}

			if (h != null && !h.trim().equals("")) {

				height = Integer.parseInt(h);

			}
			String aadhar_card = "";
			String adhar_number = "";
			if (adhar1 != null && !adhar1.trim().equals("") && adhar2 != null && !adhar2.trim().equals("") && adhar3 != null

					&& !adhar3.trim().equals("")) {

				adhar_number = (adhar1 + adhar2 + adhar3);
				aadhar_card = adhar1 + adhar2 + adhar3;

			}

			if (co_id != null && !co_id.trim().equals("")) {

				comm_id = new BigInteger(co_id);

			}

			if (first_name == null || first_name.trim().equals("")) {

				data.put("error", "Please Enter The First Name  ");

				return data;

			}
			if (first_name != null && !first_name.trim().equals("")) {
				if (!valid.isOnlyAlphabet(first_name)) {
					data.put("error", " First Name " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(first_name, valid.nameMax, valid.nameMin)) {
					data.put("error", "First Name " + valid.isValidLengthMSG);
					return data;
				}
			}
			if (middle_name != null && !middle_name.trim().equals("")) {
				if (!valid.isOnlyAlphabet(middle_name)) {
					data.put("error", " Middle Name " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(middle_name, valid.nameMax, valid.nameMin)) {
					data.put("error", "Middle Name " + valid.isValidLengthMSG);
					return data;
				}
			}

			if (last_name != null && !last_name.trim().equals("")) {
				if (!valid.isOnlyAlphabet(last_name)) {
					data.put("error", " Last Name " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(last_name, valid.nameMax, valid.nameMin)) {
					data.put("error", "Last Name " + valid.isValidLengthMSG);
					return data;
				}
			}
			if (coun_birth == null || coun_birth.equals("0")) {

				data.put("error", "Please Select The Country of Birth  ");

				return data;

			}
			if (country_birth_other != null && !country_birth_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(country_birth_other)) {

					data.put("error", " Country of Birth Other " + valid.isOnlyAlphabetMSG);

					return data;
				}
				if (!valid.isvalidLength(country_birth_other, valid.nameMax, valid.nameMin)) {

					data.put("error", "Country of Birth Other " + valid.isValidLengthMSG);

					return data;
				}
			}

			if (s_birth == null || s_birth.equals("0")) {

				data.put("error", "Please Select The State of Birth ");

				return data;

			}

			if (state_birth_other != null && !state_birth_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(state_birth_other)) {

					data.put("error", " State of Birth Other " + valid.isOnlyAlphabetMSG);

					return data;
				}
				if (!valid.isvalidLength(state_birth_other, valid.nameMax, valid.nameMin)) {

					data.put("error", "State of Birth Other " + valid.isValidLengthMSG);

					return data;
				}
			}
			if (d_birth == null || d_birth.equals("0")) {

				data.put("error", "Please Select The District of Birth");

				return data;

			}
			if (district_birth_other != null && !district_birth_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(district_birth_other)) {

					data.put("error", " District of Birth Other " + valid.isOnlyAlphabetMSG);

					return data;
				}
				if (!valid.isvalidLength(district_birth_other, valid.nameMax, valid.nameMin)) {

					data.put("error", "District of Birth Other " + valid.isValidLengthMSG);

					return data;
				}
			}
			if (pl_birth == null || pl_birth.trim().equals("")) {

				data.put("error", "Please Enter The Place of Birth ");

				return data;

			}

			if (pl_birth != null && !pl_birth.trim().equals("")) {
				if (!valid.isOnlyAlphabet(pl_birth)) {
					data.put("error", " Place of Birth " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(pl_birth, valid.nameMax, valid.nameMin)) {
					data.put("error", "Place of Birth " + valid.isValidLengthMSG);
					return data;
				}
			}
			if (nat == null || nat.equals("0")) {

				data.put("error", "Please Select The Nationality ");

				return data;

			}
			if (nationality_other != null && !nationality_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(nationality_other)) {
					data.put("error", " Nationality Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(nationality_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Nationality Other " + valid.isValidLengthMSG);
					return data;
				}
			}
			if (mt == null || mt.equals("0")) {

				data.put("error", "Please Select The Mother Tongue ");

				return data;

			}
			if (mother_tongue_other != null && !mother_tongue_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(mother_tongue_other)) {
					data.put("error", " Mother Tongue Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(mother_tongue_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Mother Tongue Other " + valid.isValidLengthMSG);
					return data;
				}
			}
			if (reli == null || reli.equals("0")) {

				data.put("error", "Please Select The Religion ");

				return data;

			}
			if (religion_other != null && !religion_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(religion_other)) {
					data.put("error", " Religion Other " + valid.isOnlyAlphabetMSG);
					return data;
				}
				if (!valid.isvalidLength(religion_other, valid.nameMax, valid.nameMin)) {
					data.put("error", "Religion Other " + valid.isValidLengthMSG);
					return data;
				}
			}
			if (ncc == null || ncc.equals("0")) {

				data.put("error", "Please Select The Type of Entry ");

				return data;

			}

			if (aadhar_card != "") {
				if (!valid.isValidAadhar(aadhar_card)) {
					data.put("error", valid.isValidAadharMSG);
					return data;
				}
			}

			if (!pan_no.equals("") && !pan_no.equals(" ")) {
				if (!valid.isValidPan(pan_no)) {
					data.put("error", valid.isValidPanMSG);
					return data;
				}

				if (!valid.PanNoLength(pan_no)) {
					data.put("error", valid.PanNoMSG);
					return data;
				}
			}
			if (com_ins == null || com_ins.trim().equals("0")) {

				data.put("error", "Please Select Commissioning institute");

				return data;

			}

			if (mar_status == null || mar_status.equals("0")) {

				data.put("error", "Please Select The Maritial Status ");

				return data;

			}

			if (blood == null || blood.equals("0")) {

				data.put("error", "Please Select The Blood Group ");

				return data;

			}

			if (h == null || h.equals("0")) {

				data.put("error", "Please Select The Height ");

				return data;

			}

			if (allergic_radio.equals("yes")) {

				for (int al = 1; al <= allergic_count; al++) {

					if (request.getParameter("medicine" + al).trim().equals("")

							|| request.getParameter("medicine" + al) == null) {

						data.put("error", "Please Enter Medicine In " + al + " Row");

						return data;

					}

				}

			}

			if (org_domicile_country == null || org_domicile_country.equals("0")) {

				data.put("error", "Please Select The Original Domicile of Country");

				return data;

			}

			if (org_domicile_Country_otherV != null && org_domicile_Country_otherV.equals("OTHERS")) {

				if (org_domicile_Country_other == null || org_domicile_Country_other.trim().equals("")) {

					data.put("error", "Please Enter The Original Domicile of Country Other");

					return data;

				}
				if (org_domicile_Country_other != null && !org_domicile_Country_other.trim().equals("")) {
					if (!valid.isOnlyAlphabet(org_domicile_Country_other)) {
						data.put("error", " Original Domicile of Country Other " + valid.isOnlyAlphabetMSG);
						return data;
					}
					if (!valid.isvalidLength(org_domicile_Country_other, valid.nameMax, valid.nameMin)) {
						data.put("error", "Original Domicile of Country Other " + valid.isValidLengthMSG);
						return data;
					}
				}

			} else {

				org_domicile_Country_other = null;

			}

			if (org_domicile_state == null || org_domicile_state.equals("0")) {

				data.put("error", "Please Select The Original Domicile of State");

				return data;

			}

			if (org_domicile_state_otherV != null && org_domicile_state_otherV.equals("OTHERS")) {

				if (org_domicile_state_other == null || org_domicile_state_other.trim().equals("")) {

					data.put("error", "Please Enter The Original Domicile of State Other");

					return data;

				}
				if (org_domicile_state_other != null && !org_domicile_state_other.trim().equals("")) {
					if (!valid.isOnlyAlphabet(org_domicile_state_other)) {
						data.put("error", "Original Domicile of State Other " + valid.isOnlyAlphabetMSG);
						return data;
					}
					if (!valid.isvalidLength(org_domicile_state_other, valid.nameMax, valid.nameMin)) {
						data.put("error", "Original Domicile of State Other " + valid.isValidLengthMSG);
						return data;
					}
				}
			} else {

				org_domicile_state_other = null;

			}

			if (org_domicile_district == null || org_domicile_district.equals("0")) {

				data.put("error", "Please Select The Original Domicile of District");

				return data;

			}

			if (org_domicile_district_otherV != null && org_domicile_district_otherV.equals("OTHERS")) {

				if (org_domicile_district_other == null || org_domicile_district_other.trim().equals("")) {

					data.put("error", "Please Enter The Original Domicile of District Other");

					return data;

				}
				if (org_domicile_district_other != null && !org_domicile_district_other.trim().equals("")) {
					if (!valid.isOnlyAlphabet(org_domicile_district_other)) {
						data.put("error", "Original Domicile of District Other " + valid.isOnlyAlphabetMSG);
						return data;
					}
					if (!valid.isvalidLength(org_domicile_district_other, valid.nameMax, valid.nameMin)) {
						data.put("error", "Original Domicile of District Other " + valid.isValidLengthMSG);
						return data;
					}
				}
			} else {

				org_domicile_district_other = null;

			}

			if (org_domicile_tehsil == null || org_domicile_tehsil.equals("0")) {

				data.put("error", "Please Select The Original Domicile of Tehsil");

				return data;

			}

			if (org_domicile_tehsil_otherV != null && org_domicile_tehsil_otherV.equals("OTHERS")) {

				if (org_domicile_tehsil_other == null || org_domicile_tehsil_other.trim().equals("")) {

					data.put("error", "Please Enter The Original Domicile of Tehsil Other");

					return data;

				}
				if (org_domicile_tehsil_other != null && !org_domicile_tehsil_other.trim().equals("")) {
					if (!valid.isOnlyAlphabet(org_domicile_tehsil_other)) {
						data.put("error", "Original Domicile of Tehsil Other " + valid.isOnlyAlphabetMSG);
						return data;
					}
					if (!valid.isvalidLength(org_domicile_tehsil_other, valid.nameMax, valid.nameMin)) {
						data.put("error", "Original Domicile of Tehsil Other " + valid.isValidLengthMSG);
						return data;
					}
				}
			} else {

				org_domicile_tehsil_other = null;

			}

			if (cadet_status.equals("2") || cadet_status.equals("3") || cadet_status.equals("4")) {

				if (!cadet_status.equals("2")) {

					isgazetted = request.getParameter("isgazetted1");

					isCivil = request.getParameter("isCivil1");

				} else {

					competency = request.getParameter("competency");

					if (!request.getParameter("competency_other").toString().trim().equals("")) {

						competency_other = request.getParameter("competency_other").toString();

					}

				}

				designation = request.getParameter("designation1");

				emp_name = request.getParameter("emp_name1");

			}

			if (cadet_status.equals("5") || cadet_status.equals("6") || cadet_status.equals("7") || cadet_status.equals("8")

					|| cadet_status.equals("9")) {

				army_no = request.getParameter("army_no1");

				date_form = request.getParameter("date_form1");

				date_to = request.getParameter("date_to1");

				if (format.parse(date_to).compareTo(format.parse(date_form)) < 0) {

					data.put("error", "To Date  Should Be Greater Than Or Equal To From Date");

					return data;

				}

				total_rank = request.getParameter("total_rank1");

				unit_reg = request.getParameter("sus_no");

			}

			////////////////////////////// nccc

			String pre_ch_id = request.getParameter("pre_ch_id1");

			String rvalue = "";
			String otu = request.getParameter("otu1");
			String ncc_ch_id = request.getParameter("ncc_ch_id1");

			if (ncc_ch_id != null) {

				if (otu == null || otu.trim().equals("")) {

					data.put("error", "Please select Whether in OTU Div/Jr Div/Sr Div");

					return data;

				}

			}

			if (cadet_status == null || cadet_status.equals("0")) {

				data.put("error", "Please Select Pre-Cadet Status");

				return data;

			}

			if (cadet_status.equals("2") || cadet_status.equals("3") || cadet_status.equals("4")) {

				if (cadet_status.equals("2")) {

					if (competency == null || competency.equals("0")) {

						data.put("error", "Please Select Competency");

						return data;

					}
					if (request.getParameter("competency_other") != null
							&& !request.getParameter("competency_other").trim().equals("")) {

						if (!valid.isOnlyAlphabet(request.getParameter("competency_other"))) {
							data.put("error", " Competency Other " + valid.isOnlyAlphabetMSG);
							return data;
						}

						if (!valid.isvalidLength(request.getParameter("competency_other"), valid.nameMax, valid.nameMin)) {
							data.put("error", "Competency Other " + valid.isValidLengthMSG);
							return data;
						}
					}
				}

				if (designation == null || designation.trim().equals("")) {

					data.put("error", "Please Enter Designation");

					return data;

				}
				if (designation != null && !designation.trim().equals("")) {

					if (!valid.isOnlyAlphabet(designation)) {
						data.put("error", " Designation " + valid.isOnlyAlphabetMSG);
						return data;
					}

					if (!valid.isvalidLength(designation, valid.nameMax, valid.nameMin)) {
						data.put("error", "Designation " + valid.isValidLengthMSG);
						return data;
					}
				}
				if (emp_name == null || emp_name.trim().equals("")) {

					data.put("error", "Please Enter Name of Employer");

					return data;

				}
				if (emp_name != null && !emp_name.trim().equals("")) {

					if (!valid.isOnlyAlphabet(emp_name)) {
						data.put("error", " Name of Employer " + valid.isOnlyAlphabetMSG);
						return data;
					}
					if (!valid.isvalidLength(emp_name, valid.authorityMax, valid.authorityMin)) {
						data.put("error", "Name of Employer " + valid.isValidLengthMSG);
						return data;
					}
				}
			}
			if (cadet_status.equals("5") || cadet_status.equals("6") || cadet_status.equals("7") || cadet_status.equals("8")

					|| cadet_status.equals("9")) {

				if (army_no == null || army_no.trim().equals("")) {

					data.put("error", "Please Enter Service No");

					return data;

				}
				if (army_no != null && !army_no.trim().equals("")) {

					if (!valid.isOnlyAlphabetNumericSpaceNot(army_no)) {
						data.put("error", " Service No " + valid.isOnlyAlphabetNumericSpaceNotMSG);
						return data;
					}

					if (!valid.isvalidLength(army_no, valid.nameMax, valid.nameMin)) {
						data.put("error", "Service No " + valid.isValidLengthMSG);
						return data;
					}
				}
				if (date_form == null || date_form.trim().equals("")) {

					data.put("error", "Please Select Form Date");

					return data;

				}
				if (date_form != null || date_form.trim().equals("") || date_form.equals("DD/MM/YYYY")) {

					if (!valid.isValidDate(date_form)) {
						data.put("error", valid.isValidDateMSG + " of From ");

						return data;
					} else {
						fr_dt = format.parse(date_form);
					}

				}

				if (date_to == null || date_to.trim().equals("")) {

					data.put("error", "Please Select To Date");

					return data;

				}
				if (date_to != null || date_to.trim().equals("") || date_to.equals("DD/MM/YYYY")) {

					if (!valid.isValidDate(date_to)) {
						data.put("error", valid.isValidDateMSG + " of To ");

						return data;
					} else {
						fr_to = format.parse(date_to);
					}

				}
				if (format.parse(date_to).compareTo(format.parse(date_form)) < 0) {

					data.put("error", "To Date  Should Be Greater Than Or Equal To From Date");

					return data;

				}

				if (total_rank == null || total_rank.trim().equals("")) {

					data.put("error", "Please Enter valid From And To Date");

					return data;

				}

				if (!total_rank.equals(mcommon.calculate_duration(format.parse(date_form), format.parse(date_to)))) {

					data.put("error", "Please Enter valid From And To Date");

					return data;

				}

				if (!cadet_status.equals("5") && !cadet_status.equals("9")) {

					unit_reg = request.getParameter("unit_reg1");

				}

				if (unit_reg == null || unit_reg.trim().equals("")) {

					data.put("error", "Please Enter Unit/Regiment ");

					return data;

				}
				if (unit_reg != null && !unit_reg.trim().equals("")) {

					if (!valid.isOnlyAlphabetNumeric(unit_reg)) {
						data.put("error", " Unit/Regiment " + valid.isOnlyAlphabetNumericMSG);
						return data;
					}

					if (!valid.isvalidLength(unit_reg, valid.authorityMax, valid.authorityMin)) {
						data.put("error", "Unit/Regiment " + valid.isValidLengthMSG);
						return data;
					}
				}
			}

			int i_id = Integer.parseInt(request.getParameter("i_id"));
			String comm_id_str = comm_id.toString();

			try {
				String name1 = first_name + " " + middle_name + " " + last_name;

				TB_CENSUS_DETAIL_Parent census_p = new TB_CENSUS_DETAIL_Parent();

				census_p.setFather_name(father_name);
				census_p.setMother_name(mother_name);

				census_p.setFirst_name(first_name);

				census_p.setMiddle_name(middle_name);

				census_p.setLast_name(last_name);

				census_p.setPlace_birth(pl_birth);

				census_p.setDistrict_birth(district_birth);

				census_p.setDistrict_birth_other(district_birth_other);

				census_p.setState_birth(state_birth);

				census_p.setState_birth_other(state_birth_other);

				census_p.setCountry_birth(country_birth);

				census_p.setCountry_birth_other(country_birth_other);

				census_p.setNationality(nationality);

				census_p.setNationality_other(nationality_other);

				census_p.setReligion(religion);

				census_p.setReligion_other(religion_other);

				census_p.setMarital_status(marital_status);

				census_p.setComm_id(comm_id);

				census_p.setBlood_group(blood_group);

				census_p.setHeight(height);

				census_p.setAdhar_number(adhar_number);

				// census_p.setBorder_area(border_area);

				census_p.setMother_tongue(mother_tongue);

				census_p.setMother_tongue_other(mother_tongue_other);

				census_p.setNcc_type(ncc_type);

				census_p.setCom_institute(com_institute);

				census_p.setCom_bn_id(Integer.parseInt(com_bn_id));

				census_p.setCom_company_id(Integer.parseInt(com_company_id));

				census_p.setCom_platoon_id(0);

				census_p.setTraining_institute(training_institute);

				census_p.setTraining_bn_id(Integer.parseInt(training_bn_id));

				census_p.setTraining_company_id(Integer.parseInt(training_company_id));

				census_p.setPancard_number(pan_no);

				census_p.setOrg_country(Integer.parseInt(org_domicile_country));
				census_p.setOrg_state(Integer.parseInt(org_domicile_state));
				census_p.setOrg_district(Integer.parseInt(org_domicile_district));
				census_p.setOrg_tehsil(Integer.parseInt(org_domicile_tehsil));

				census_p.setOrg_domicile_country_other(org_domicile_Country_other);
				census_p.setOrg_domicile_state_other(org_domicile_state_other);
				census_p.setOrg_domicile_district_other(org_domicile_district_other);
				census_p.setOrg_domicile_tehsil_other(org_domicile_tehsil_other);

				TB_CENSUS_CADET pre_cadet = new TB_CENSUS_CADET();
				pre_cadet.setPre_cadet_status(Integer.parseInt(cadet_status));
				pre_cadet.setGazetted(isgazetted);
				pre_cadet.setCivil_service(isCivil);
				pre_cadet.setDesignation(designation);
				pre_cadet.setEmployee_name(emp_name);
				pre_cadet.setCompetency_other(competency_other);
				pre_cadet.setArmy_no(army_no);
				pre_cadet.setFrom_date(fr_dt);
				if (date_to != null) {
					pre_cadet.setTo_date(format.parse(date_to));
				}
				pre_cadet.setTotal_service(total_rank);
				pre_cadet.setCompetency(Integer.parseInt(competency));
				pre_cadet.setUnit_reg(unit_reg);
				pre_cadet.setComm_id(comm_id);
				pre_cadet.setCreated_by(username);
				pre_cadet.setCreated_date(date);

				TB_TRANS_PROPOSED_COMM_LETTER comm1 = getdatecommdate(comm_id);
				census_id = censusAprovedDAO.getcensusid(comm_id);
				if (census_id == 0) {
					census_p.setCreated_by(username);
					census_p.setCreated_date(date);
					census_p.setStatus(0);
					census_id = (Integer) sessionHQL.save(census_p);
					census_id = census_p.getId();

				} else {

					String hql = "update TB_CENSUS_DETAIL_Parent set first_name=:first_name,middle_name=:middle_name,"

							+ "last_name=:last_name,place_birth=:place_birth,district_birth=:district_birth,district_birth_other=:district_birth_other,state_birth=:state_birth,state_birth_other=:state_birth_other,"

							+ "country_birth=:country_birth,country_birth_other=:country_birth_other,nationality=:nationality,nationality_other=:nationality_other,religion=:religion,religion_other=:religion_other,marital_status=:marital_status,blood_group=:blood_group,height=:height,"

							+ " adhar_number=pgp_sym_encrypt(:adhar_number,current_setting('miso.version')),mother_tongue=:mother_tongue,mother_tongue_other=:mother_tongue_other,"

							+ " ncc_type=:ncc_type,org_country=:org_country,org_state=:org_state,org_district=:org_district,"
							+ "org_tehsil=:org_tehsil,org_domicile_country_other=:org_domicile_country_other,org_domicile_state_other=:org_domicile_state_other,org_domicile_district_other=:org_domicile_district_other,org_domicile_tehsil_other=:org_domicile_tehsil_other,com_institute=:com_institute,status=:status,com_bn_id=:com_bn_id,com_company_id=:com_company_id,com_platoon_id=:com_platoon_id,training_institute=:training_institute,training_bn_id=:training_bn_id,training_company_id=:training_company_id,pancard_number=pgp_sym_encrypt(:pancard_number,current_setting('miso.version')),father_name=:father_name,mother_name=:mother_name where id=:id";

					Query query = sessionHQL.createQuery(hql).setString("first_name", census_p.getFirst_name())

							.setString("middle_name", census_p.getMiddle_name())
							.setString("last_name", census_p.getLast_name())
							.setString("place_birth", census_p.getPlace_birth())
							.setString("pancard_number", census_p.getPancard_number())
							.setInteger("district_birth", census_p.getDistrict_birth())
							.setString("district_birth_other", census_p.getDistrict_birth_other())
							.setInteger("state_birth", census_p.getState_birth())
							.setString("state_birth_other", census_p.getState_birth_other())
							.setInteger("country_birth", census_p.getCountry_birth())
							.setString("country_birth_other", census_p.getCountry_birth_other())
							.setInteger("nationality", census_p.getNationality())
							.setInteger("religion", census_p.getReligion())
							.setString("nationality_other", census_p.getNationality_other())
							.setString("religion_other", census_p.getReligion_other())
							.setInteger("marital_status", census_p.getMarital_status())
							.setInteger("blood_group", census_p.getBlood_group()).setInteger("height", census_p.getHeight())
							.setString("adhar_number", census_p.getAdhar_number())
							// .setString("border_area", census_p.getBorder_area())
							.setInteger("mother_tongue", census_p.getMother_tongue())
							.setString("mother_tongue_other", census_p.getMother_tongue_other())
							.setInteger("ncc_type", census_p.getNcc_type())
							.setInteger("com_institute", census_p.getCom_institute()).setInteger("id", census_id)
							.setInteger("com_bn_id", census_p.getCom_bn_id())
							.setInteger("com_company_id", census_p.getCom_company_id())
							.setInteger("training_institute", census_p.getTraining_institute())
							.setInteger("training_bn_id", census_p.getTraining_bn_id())
							.setInteger("training_company_id", census_p.getTraining_company_id())
							.setString("father_name", census_p.getFather_name())
							.setString("mother_name", census_p.getMother_name())
							.setInteger("org_country", census_p.getOrg_country())
							.setInteger("org_state", census_p.getOrg_state())
							.setInteger("org_district", census_p.getOrg_district())
							.setInteger("org_tehsil", census_p.getOrg_tehsil())
							.setString("org_domicile_country_other", census_p.getOrg_domicile_country_other())
							.setString("org_domicile_state_other", census_p.getOrg_domicile_state_other())
							.setString("org_domicile_district_other", census_p.getOrg_domicile_district_other())
							.setString("org_domicile_tehsil_other", census_p.getOrg_domicile_tehsil_other())
							.setInteger("status", 0).setInteger("com_platoon_id", census_p.getCom_platoon_id());
					msg = query.executeUpdate() > 0 ? "update" : "0";

				}

				int check_precadet = check_duplicate(comm_id, "TB_CENSUS_CADET");
				if (Integer.parseInt(pre_ch_id) == 0) {
					pre_cadet.setCensus_id(census_id);
					int id = (int) sessionHQL.save(pre_cadet);
					// pre_ch_id = Integer.toString(id);
				}

				else {

					String hql = "update TB_CENSUS_CADET set modified_by=:modified_by ,modified_date=:modified_date , pre_cadet_status=:pre_cadet_status, designation=:designation , "

							+ "employee_name=:employee_name,gazetted=:gazetted ,civil_service=:civil_service,from_date=:from_date,to_date=:to_date,army_no=:army_no"

							+ " ,total_service=:total_service ,unit_reg=:unit_reg ,competency=:competency,competency_other=:competency_other where  id=:id";

					Query query = sessionHQL.createQuery(hql).setInteger("pre_cadet_status", Integer.parseInt(cadet_status))

							.setString("designation", designation).setString("employee_name", emp_name)

							.setString("gazetted", isgazetted).setString("civil_service", isCivil)

							.setTimestamp("from_date", fr_dt).setTimestamp("to_date", fr_to).setString("army_no", army_no)

							.setString("total_service", total_rank).setString("unit_reg", unit_reg)

							.setInteger("id", Integer.parseInt(pre_ch_id))

							.setInteger("competency", Integer.parseInt(competency)).setString("modified_by", username)

							.setString("competency_other", competency_other).setTimestamp("modified_date", new Date());

					rvalue = query.executeUpdate() > 0 ? "update" : "0";
				}

				if (ncc_ch_id != null) {
					int check_ncc = check_duplicate(comm_id, "TB_CENSUS_NCC_EXP");

					if (check_ncc == 0) {

						TB_CENSUS_NCC_EXP ncc1 = new TB_CENSUS_NCC_EXP();

						ncc1.setOtu(otu);

						ncc1.setCen_id(census_id);

						ncc1.setComm_id(comm_id);

						ncc1.setCreated_by(username);

						ncc1.setCreated_on(date);

						ncc1.setStatus(0);

						int id = (int) sessionHQL.save(ncc1);

						ncc_ch_id = Integer.toString(id);

					} else {

						String hql_n = "update TB_CENSUS_NCC_EXP set modify_by=:modify_by ,modify_on=:modify_on , otu=:otu "

								+ " where  id=:id";

						Query query_n = sessionHQL.createQuery(hql_n).setString("otu", otu)

								.setInteger("id", Integer.parseInt(ncc_ch_id)).setString("modify_by", username)

								.setTimestamp("modify_on", new Date());

						rvalue = query_n.executeUpdate() > 0 ? "update" : "0";

					}

				}
				if (allergic_radio.equals("yes")) {

					for (int i = 1; i <= allergicOld_count; i++) {

						String medicine = request.getParameter("medicine" + i);

						String hql = "update TB_PSG_CENSUS_ALLERGICTOMEDICINE set modified_by=:modified_by ,modified_date=:modified_date , medicine=:medicine,status=:status where id=:c_id";

						Query query = sessionHQL.createQuery(hql).setString("medicine", medicine)

								.setInteger("c_id", Integer.parseInt(request.getParameter("allergic_ch_id" + i)))

								.setInteger("status", 0).setString("modified_by", username)

								.setTimestamp("modified_date", new Date());

						msg = query.executeUpdate() > 0 ? "1" : "Data not Updated";

						sessionHQL.flush();

						sessionHQL.clear();

					}

					diffrence = allergic_count - allergicOld_count;

					if (diffrence != 0) {

						TB_PSG_CENSUS_ALLERGICTOMEDICINE e = new TB_PSG_CENSUS_ALLERGICTOMEDICINE();

						for (int j = allergicOld_count + 1; j <= allergic_count; j++) {

							String medicine = request.getParameter("medicine" + j);

							e.setMedicine(medicine);

							e.setCen_id(census_id);

							e.setComm_id(comm_id);

							e.setCreated_by(username);

							e.setCreated_date(new Date());

							sessionHQL.save(e);

							sessionHQL.flush();

							sessionHQL.clear();

						}

					}

				} else {

					if (allergicOld_count != 0) {

						String hqlUpdate = "delete from TB_PSG_CENSUS_ALLERGICTOMEDICINE where cen_id=:id";

						int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", census_id).executeUpdate();

					}

				}

				String hql2 = "update TB_TRANS_PROPOSED_COMM_LETTER set " + "name=:name  where id=:comm_id";

				Query query2 = sessionHQL.createQuery(hql2).setString("name", comm1.getName()).setBigInteger("comm_id",

						comm_id);

				msg = query2.executeUpdate() > 0 ? "update" : "0";

				if (mad_classification == null || mad_classification.equals("0")) {

					tx.rollback();
					data.put("error", "Please Select The Medical Classification");
					return data;

				}

				if (medDtlFillIn3008 != 1) {

					if (checkbox_1bx != null && checkbox_1bx.equals("1BX")) {

						if ((_1BX_from_date == null || _1BX_from_date.trim().equals("")
								|| _1BX_from_date.equals("DD/MM/YYYY"))

								|| (_1BX_to_date == null || _1BX_to_date.trim().equals("")

										|| _1BX_to_date.equals("DD/MM/YYYY"))) {

							tx.rollback();
							data.put("error", "Please Enter From Date And To Date For Shape 1BX");
							return data;

						}
						if (_1BX_from_date != null || _1BX_from_date.trim().equals("")
								|| _1BX_from_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(_1BX_from_date)) {

								data.put("error", valid.isValidDateMSG + " of From ");
								return data;
							}
						}
						if (_1BX_to_date != null || _1BX_to_date.trim().equals("") || _1BX_to_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(_1BX_to_date)) {
								data.put("error", valid.isValidDateMSG + " of To ");
								return data;
							}
						}
						if (format.parse(_1BX_to_date).compareTo(format.parse(_1BX_from_date)) < 0) {
							data.put("error", "To Date Should Be Greater Than Or Equal To From Date For Shape 1BX");
							return data;
						}

						if (_1BX_diagnosis.trim().equals("") || _1BX_diagnosis == null) {

							tx.rollback();

							data.put("error", "Please  Enter The Diagnosis For Shape 1BX");
							return data;

						}

						if (!_1BX_diagnosis.trim().equals("")) {

							String[] arrOfStr = _1BX_diagnosis.split("-", 2);

							_1BX_diagnosis = arrOfStr[0];

						}

						String hqlUpdate = "delete from TB_CENSUS_MEDICAL_CATEGORY where cen_id=:id and status=0";

						int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", census_id).executeUpdate();

						String li[] = { "S", "H", "A", "P", "E", "C_C", "C_O", "C_P", "C_E" };

						String li_id[] = { "sShape_ch_id1", "hShape_ch_id1", "aShape_ch_id1", "pShape_ch_id1",
								"eShape_ch_id1",

								"cCope_ch_id1", "oCope_ch_id1", "pCope_ch_id1", "eCope_ch_id1" };

						for (int i = 0; i < li.length; i++) {

							String ch_id = request.getParameter(li_id[i]);

							TB_CENSUS_MEDICAL_CATEGORY Mad_cat;

							Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

							Mad_cat.setShape(li[i]);

							if (i == 5 || i == 8) {

								Mad_cat.setOther("");

							}

							if (i < 5) {

								Mad_cat.setShape_status(1);

								Mad_cat.setShape_value("1A");

							} else

								Mad_cat.setShape_value("0");

							if ((_1BX_from_date != null && !_1BX_from_date.trim().equals(""))

									&& (_1BX_to_date != null && !_1BX_to_date.trim().equals(""))) {

								Mad_cat.setFrom_date_1bx(format.parse(_1BX_from_date));

								Mad_cat.setTo_date_1bx(format.parse(_1BX_to_date));

							}

							Mad_cat.setCen_id(census_id);

							Mad_cat.setComm_id(comm_id);

							Mad_cat.setDiagnosis_1bx(_1BX_diagnosis);

							Mad_cat.setClasification("NIL");

							Mad_cat.setCreated_by(username);

							Mad_cat.setCreated_on(date);

							sessionHQL.save(Mad_cat);

						}

						sessionHQL.flush();

						sessionHQL.clear();

					} else {

						////////////////////////////// S SHAPE ////////////////////////////

						for (int i = 1; i <= sShapeOld_count; i++) {
							String shape_status = request.getParameter("s_status" + i);
							String shape_value = request.getParameter("sShape_value" + i);
							String from_date = request.getParameter("s_from_date" + i);
							String to_date = request.getParameter("s_to_date" + i);
							String diagnosis_1 = request.getParameter("_diagnosis_code1" + i);
							String shape_ch = request.getParameter("sShape_ch_id" + i);
							if (!diagnosis_1.trim().equals("")) {
								String[] arrOfStr = diagnosis_1.split("-", 2);
								diagnosis_1 = arrOfStr[0];
							}

							if (shape_status == null || shape_status.equals("0")) {
								tx.rollback();
								data.put("error", "Please Select The S-Shape Status " + i + " Row");
								return data;

							}

							if (shape_value == null || shape_value.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The S-Shape Value " + i + " Row");

								return data;

							}					
							if (!shape_status.equals("1")) {

								if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The S-Shape From Date " + i + " Row");

									return data;

								}
								if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(from_date)) {
										data.put("error", valid.isValidDateMSG + "  of The S-Shape From ");

										return data;
									}
								}

								if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The S-Shape To Date " + i + " Row");

									return data;

								}
								if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(to_date)) {
										data.put("error", valid.isValidDateMSG + " of The S-Shape To ");

										return data;
									}
								}
								if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

									data.put("error", "S-Shape To Date Should Be Greater Than Or Equal To From Date In Row "
											+ i + "");

									return data;

								}

							}
							if (i != 1) {

								for (int k = 1; k < i; k++) {
									String shape_status_pre = request.getParameter("s_status" + k);
									String shape_value_pre = request.getParameter("sShape_value" + k);
									String from_date_pre = request.getParameter("s_from_date" + k);
									String to_date_pre = request.getParameter("s_to_date" + k);
									String diagnosis_1_pre = request.getParameter("_diagnosis_code1" + k);
									
									if (!diagnosis_1_pre.trim().equals("")) {
										String[] arrOfStr = diagnosis_1_pre.split("-", 2);
										diagnosis_1_pre = arrOfStr[0];

									}
									if (shape_status.equals("1")) {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && diagnosis_1_pre.equals(diagnosis_1)) {
											tx.rollback();
											data.put("error", " Data Already Exists S-Shape  " + i + "th row");
											return data;
										}
									} else {

										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_1_pre.equals(diagnosis_1)) {
											tx.rollback();
											data.put("error", " Data Already Exists S-Shape  " + i + "th row");
											return data;
										}
									}
								}

							}
							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

									.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

							Mad_cat.setShape_status(Integer.parseInt(shape_status));

							Mad_cat.setShape_value(shape_value);

//								if (!shape_status.equals("1")
							//
//										|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
							//
//										|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
							if (!shape_status.equals("1")) {

								Mad_cat.setFrom_date(format.parse(from_date));

								Mad_cat.setTo_date(format.parse(to_date));

							} else if (from_date != null && !from_date.trim().equals("")
									&& !from_date.equals("DD/MM/YYYY")) {
								Mad_cat.setFrom_date(format.parse(from_date));
							}

							Mad_cat.setFrom_date_1bx(null);

							Mad_cat.setTo_date_1bx(null);

							Mad_cat.setDiagnosis_1bx(null);

							Mad_cat.setDiagnosis(diagnosis_1);

							Mad_cat.setClasification(mad_classification);

							Mad_cat.setModify_by(username);

							Mad_cat.setModify_on(date);

							Mad_cat.setStatus(0);

							sessionHQL.update(Mad_cat);

							sessionHQL.flush();

							sessionHQL.clear();

						}

						// Logic for INSERT

						diffrence = sShape_count - sShapeOld_count;

						if (diffrence != 0) {

							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

							for (int i = sShapeOld_count + 1; i <= sShape_count; i++) {
								String shape_status = request.getParameter("s_status" + i);
								String shape_value = request.getParameter("sShape_value" + i);
								String from_date = request.getParameter("s_from_date" + i);
								String to_date = request.getParameter("s_to_date" + i);
								String diagnosis_1 = request.getParameter("_diagnosis_code1" + i);

								if (!diagnosis_1.trim().equals("")) {
									String[] arrOfStr = diagnosis_1.split("-", 2);
									diagnosis_1 = arrOfStr[0];

								}

								if (shape_status == null || shape_status.equals("0")) {
									tx.rollback();
									data.put("error", "Please Select The S-Shape Status " + i + " Row");
									return data;

								}

								if (shape_value == null || shape_value.equals("0")) {
									tx.rollback();
									data.put("error", "Please Select The S-Shape Value " + i + " Row");
									return data;

								}
								
								if (!shape_status.equals("1")) {

									if (from_date == null || from_date.trim().equals("")
											|| from_date.equals("DD/MM/YYYY")) {

										tx.rollback();
										data.put("error", "Please Enter The S-Shape From Date " + i + " Row");
										return data;

									}
									if (from_date != null || from_date.trim().equals("")
											|| from_date.equals("DD/MM/YYYY")) {
										if (!valid.isValidDate(from_date)) {
											data.put("error", valid.isValidDateMSG + "  of The S-Shape From ");
											return data;
										}
									}
									if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
										tx.rollback();
										data.put("error", "Please Enter The S-Shape To Date " + i + " Row");
										return data;

									}
									if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
										if (!valid.isValidDate(to_date)) {
											data.put("error", valid.isValidDateMSG + " of The S-Shape To ");
											return data;
										}
									}
									if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

										data.put("error",
												"S-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i
														+ "");
										return data;
									}

								}
								if (i != 1) {
									for (int k = 1; k < i; k++) {
										String shape_status_pre = request.getParameter("s_status" + k);
										String shape_value_pre = request.getParameter("sShape_value" + k);
										String from_date_pre = request.getParameter("s_from_date" + k);
										String to_date_pre = request.getParameter("s_to_date" + k);
										String diagnosis_1_pre = request.getParameter("_diagnosis_code1" + k);
										if (!diagnosis_1_pre.trim().equals("")) {
											String[] arrOfStr = diagnosis_1_pre.split("-", 2);
											diagnosis_1_pre = arrOfStr[0];

										}

										
										if (!shape_status.equals("1")) {
											if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
													&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date)
													&& diagnosis_1_pre.equals(diagnosis_1)) {
												tx.rollback();

											}
										} else {
											if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
													&& from_date_pre.equals(from_date) && diagnosis_1_pre.equals(diagnosis_1)) {
												tx.rollback();
												data.put("error", " Data Already Exists S-Shape  " + i + " th row");
												return data;
											}
										}
									}
								}
								Mad_cat.setShape("S");
								Mad_cat.setShape_status(Integer.parseInt(shape_status));

								Mad_cat.setShape_value(shape_value);
								if (!shape_status.equals("1")) {

									Mad_cat.setFrom_date(format.parse(from_date));

									Mad_cat.setTo_date(format.parse(to_date));

								} else if (from_date != null && !from_date.trim().equals("")
										&& !from_date.equals("DD/MM/YYYY")) {
									Mad_cat.setFrom_date(format.parse(from_date));
								}

								Mad_cat.setDiagnosis(diagnosis_1);
								Mad_cat.setClasification(mad_classification);
								Mad_cat.setCen_id(census_id);
								Mad_cat.setComm_id(comm_id);
								Mad_cat.setCreated_by(username);
								Mad_cat.setCreated_on(date);
								Mad_cat.setStatus(0);
								int check_s = check_shape(comm_id_str, "S");
								if (check_s == 0) {
									sessionHQL.save(Mad_cat);
									sessionHQL.flush();
									sessionHQL.clear();
								} else {
									tx.rollback();
									data.put("error", "Data Already Exists");
									return data;

								}

							}
						}

						////////////////////////////// H SHAPE ////////////////////////////

						for (int i = 1; i <= hShapeOld_count; i++) {
							String shape_status = request.getParameter("h_status" + i);
							String shape_value = request.getParameter("hShape_value" + i);
							String from_date = request.getParameter("h_from_date" + i);
							String to_date = request.getParameter("h_to_date" + i);
							String diagnosis_2 = request.getParameter("_diagnosis_code2" + i);
							String shape_ch = request.getParameter("hShape_ch_id" + i);
							if (!diagnosis_2.trim().equals("")) {
								String[] arrOfStr = diagnosis_2.split("-", 2);
								diagnosis_2 = arrOfStr[0];
							}

							if (shape_status == null || shape_status.equals("0")) {
								tx.rollback();
								data.put("error", "Please Select The H-Shape Status " + i + " Row");
								return data;
							}

							if (shape_value == null || shape_value.equals("0")) {
								tx.rollback();
								data.put("error", "Please Select The H-Shape Value " + i + " Row");
								return data;

							}
							
							if (!shape_status.equals("1")) {
								if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
									tx.rollback();
									data.put("error", "Please Enter The H-Shape From Date " + i + " Row");
									return data;
								}
								if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(from_date)) {
										data.put("error", valid.isValidDateMSG + " of The H-Shape From ");

										return data;
									}
								}
								if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The H-Shape To Date " + i + " Row");

									return data;

								}

								if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(to_date)) {
										data.put("error", valid.isValidDateMSG + " of The H-Shape To ");

										return data;
									}
								}
								if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

									data.put("error", "H-Shape To Date Should Be Greater Than Or Equal To From Date In Row "
											+ i + "");

									return data;

								}

							}
							if (i != 1) {
								for (int k = 1; k < i; k++) {
									String shape_status_pre = request.getParameter("h_status" + k);
									String shape_value_pre = request.getParameter("hShape_value" + k);
									String from_date_pre = request.getParameter("h_from_date" + k);
									String to_date_pre = request.getParameter("h_to_date" + k);
									String diagnosis_2_pre = request.getParameter("_diagnosis_code2" + k);
									if (!diagnosis_2_pre.trim().equals("")) {
										String[] arrOfStr = diagnosis_2_pre.split("-", 2);
										diagnosis_2_pre = arrOfStr[0];

									}

									if (!shape_status.equals("1")) {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_2_pre.equals(diagnosis_2)) {
											tx.rollback();
											data.put("error", " Data Already Exists H-Shape  " + i + "th row");
											return data;
										}
									} else {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && diagnosis_2_pre.equals(diagnosis_2)) {
											tx.rollback();
											data.put("error", " Data Already Exists H-Shape  " + i + "th row");
											return data;
										}
									}
								}
							}
							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

									.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

							Mad_cat.setShape_status(Integer.parseInt(shape_status));

							Mad_cat.setShape_value(shape_value);
							if (!shape_status.equals("1")) {

								Mad_cat.setFrom_date(format.parse(from_date));

								Mad_cat.setTo_date(format.parse(to_date));

							} else if (from_date != null && !from_date.trim().equals("")
									&& !from_date.equals("DD/MM/YYYY")) {
								Mad_cat.setFrom_date(format.parse(from_date));
							}

							Mad_cat.setFrom_date_1bx(null);
							Mad_cat.setTo_date_1bx(null);
							Mad_cat.setDiagnosis_1bx(null);
							Mad_cat.setDiagnosis(diagnosis_2);
							Mad_cat.setClasification(mad_classification);
							Mad_cat.setModify_by(username);
							Mad_cat.setModify_on(date);
							Mad_cat.setStatus(0);
							sessionHQL.update(Mad_cat);
							sessionHQL.flush();
							sessionHQL.clear();
						}

						// Logic for INSERT

						diffrence = hShape_count - hShapeOld_count;

						if (diffrence != 0) {

							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

							for (int i = hShapeOld_count + 1; i <= hShape_count; i++) {

								String shape_status = request.getParameter("h_status" + i);

								String shape_value = request.getParameter("hShape_value" + i);

								String from_date = request.getParameter("h_from_date" + i);

								String to_date = request.getParameter("h_to_date" + i);

								String diagnosis_2 = request.getParameter("_diagnosis_code2" + i);

								if (!diagnosis_2.trim().equals("")) {

									String[] arrOfStr = diagnosis_2.split("-", 2);

									diagnosis_2 = arrOfStr[0];

								}

								if (shape_status == null || shape_status.equals("0")) {

									tx.rollback();

									data.put("error", "Please Select The H-Shape Status " + i + " Row");

									return data;

								}

								if (shape_value == null || shape_value.equals("0")) {

									tx.rollback();

									data.put("error", "Please Select The H-Shape Value " + i + " Row");

									return data;

								}
//								
								if (!shape_status.equals("1")) {

									if (from_date == null || from_date.trim().equals("")
											|| from_date.equals("DD/MM/YYYY")) {
										tx.rollback();
										data.put("error", "Please Enter The H-Shape From Date " + i + " Row");
										return data;
									}
									if (from_date != null || from_date.trim().equals("")
											|| from_date.equals("DD/MM/YYYY")) {
										if (!valid.isValidDate(from_date)) {
											data.put("error", valid.isValidDateMSG + " of The H-Shape From ");
											return data;
										}
									}

									if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
										tx.rollback();
										data.put("error", "Please Enter The H-Shape To Date " + i + " Row");
										return data;
									}
									if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
										if (!valid.isValidDate(to_date)) {
											data.put("error", valid.isValidDateMSG + " of The H-Shape To ");

											return data;
										}
									}
									if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

										data.put("error",
												"H-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i
														+ "");

										return data;

									}

								}
								if (i != 1) {
									for (int k = 1; k < i; k++) {
										String shape_status_pre = request.getParameter("h_status" + k);
										String shape_value_pre = request.getParameter("hShape_value" + k);
										String from_date_pre = request.getParameter("h_from_date" + k);
										String to_date_pre = request.getParameter("h_to_date" + k);
										String diagnosis_2_pre = request.getParameter("_diagnosis_code2" + k);
										if (!diagnosis_2_pre.trim().equals("")) {
											String[] arrOfStr = diagnosis_2_pre.split("-", 2);
											diagnosis_2_pre = arrOfStr[0];

										}									
										if (!shape_status.equals("1")) {
											if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
													&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_2_pre.equals(diagnosis_2)) {
												tx.rollback();
												data.put("error", " Data Already Exists H-Shape  " + i + "th row");
												return data;
											}
										} else {
											if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
													&& from_date_pre.equals(from_date) && diagnosis_2_pre.equals(diagnosis_2)) {
												tx.rollback();
												data.put("error", " Data Already Exists H-Shape  " + i + "th row");
												return data;
											}
										}
									}
								}
								Mad_cat.setShape("H");
								Mad_cat.setShape_status(Integer.parseInt(shape_status));
								Mad_cat.setShape_value(shape_value);
								if (!shape_status.equals("1")) {
									Mad_cat.setFrom_date(format.parse(from_date));
									Mad_cat.setTo_date(format.parse(to_date));
								} else if (from_date != null && !from_date.trim().equals("")
										&& !from_date.equals("DD/MM/YYYY")) {
									Mad_cat.setFrom_date(format.parse(from_date));
								}
								Mad_cat.setDiagnosis(diagnosis_2);
								Mad_cat.setClasification(mad_classification);
								Mad_cat.setCen_id(census_id);
								Mad_cat.setComm_id(comm_id);
								Mad_cat.setCreated_by(username);
								Mad_cat.setCreated_on(date);
								Mad_cat.setStatus(0);

								int check_s = check_shape(comm_id_str, "H");
								if (check_s == 0) {
									sessionHQL.save(Mad_cat);
									sessionHQL.flush();
									sessionHQL.clear();
								} else {
									tx.rollback();

									data.put("error", "Data Already Exists");

									return data;
								}

							}

						}

						// //////////////////////////////A SHAPE ////////////////////////////

						for (int i = 1; i <= aShapeOld_count; i++) {

							String shape_status = request.getParameter("a_status" + i);

							String shape_value = request.getParameter("aShape_value" + i);

							String from_date = request.getParameter("a_from_date" + i);

							String to_date = request.getParameter("a_to_date" + i);

							String diagnosis_3 = request.getParameter("_diagnosis_code3" + i);

							String shape_ch = request.getParameter("aShape_ch_id" + i);

							if (!diagnosis_3.trim().equals("")) {

								String[] arrOfStr = diagnosis_3.split("-", 2);

								diagnosis_3 = arrOfStr[0];

							}

							if (shape_status == null || shape_status.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The A-Shape Status " + i + " Row");

								return data;

							}

							if (shape_value == null || shape_value.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The A-Shape Value " + i + " Row");

								return data;

							}

							if (!shape_status.equals("1")) {

								if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The A-Shape From Date " + i + " Row");

									return data;

								}
								if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(from_date)) {
										data.put("error", valid.isValidDateMSG + " of The A-Shape From ");

										return data;
									}
								}

								if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The A-Shape To Date " + i + " Row");

									return data;

								}
								if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(to_date)) {
										data.put("error", valid.isValidDateMSG + " of The A-Shape To ");

										return data;
									}
								}
								if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

									data.put("error", "A-Shape To Date Should Be Greater Than Or Equal To From Date In Row "
											+ i + "");

									return data;

								}

							}
							if (i != 1) {
								for (int k = 1; k < i; k++) {
									String shape_status_pre = request.getParameter("a_status" + k);
									String shape_value_pre = request.getParameter("aShape_value" + k);
									String from_date_pre = request.getParameter("a_from_date" + k);
									String to_date_pre = request.getParameter("a_to_date" + k);
									String diagnosis_3_pre = request.getParameter("_diagnosis_code3" + k);
									if (!diagnosis_3_pre.trim().equals("")) {
										String[] arrOfStr = diagnosis_3_pre.split("-", 2);
										diagnosis_3_pre = arrOfStr[0];

									}

									if (shape_status.equals("1")) {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && diagnosis_3_pre.equals(diagnosis_3)) {
											tx.rollback();
											data.put("error", " Data Already Exists A-Shape  " + i + "th row");
											return data;
										}
									} else {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) &&diagnosis_3_pre.equals(diagnosis_3)) {
											tx.rollback();
											data.put("error", " Data Already Exists A-Shape  " + i + "th row");
											return data;
										}
									}

								}
							}
							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

									.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

							Mad_cat.setShape_status(Integer.parseInt(shape_status));

							Mad_cat.setShape_value(shape_value);

//								if (!shape_status.equals("1") || (from_date != null && !from_date.trim().equals(""))
							//
//										|| (to_date != null && !to_date.trim().equals(""))) {

							if (!shape_status.equals("1")) {
								Mad_cat.setFrom_date(format.parse(from_date));

								Mad_cat.setTo_date(format.parse(to_date));

							} else if (from_date != null && !from_date.trim().equals("")
									&& !from_date.equals("DD/MM/YYYY")) {
								Mad_cat.setFrom_date(format.parse(from_date));
							}

							Mad_cat.setFrom_date_1bx(null);
							Mad_cat.setTo_date_1bx(null);
							Mad_cat.setDiagnosis_1bx(null);
							Mad_cat.setDiagnosis(diagnosis_3);
							Mad_cat.setClasification(mad_classification);
							Mad_cat.setModify_by(username);
							Mad_cat.setModify_on(date);
							Mad_cat.setStatus(0);
							sessionHQL.update(Mad_cat);
							sessionHQL.flush();
							sessionHQL.clear();
						}

						// Logic for INSERT

						diffrence = aShape_count - aShapeOld_count;
						if (diffrence != 0) {
							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();
							for (int i = aShapeOld_count + 1; i <= aShape_count; i++) {
								String shape_status = request.getParameter("a_status" + i);
								String shape_value = request.getParameter("aShape_value" + i);
								String from_date = request.getParameter("a_from_date" + i);
								String to_date = request.getParameter("a_to_date" + i);
								String diagnosis_3 = request.getParameter("_diagnosis_code3" + i);
								if (!diagnosis_3.trim().equals("")) {
									String[] arrOfStr = diagnosis_3.split("-", 2);
									diagnosis_3 = arrOfStr[0];
								}
								if (shape_status == null || shape_status.equals("0")) {
									tx.rollback();
									data.put("error", "Please Select The A-Shape Status " + i + " Row");
									return data;
								}
								if (shape_value == null || shape_value.equals("0")) {
									tx.rollback();
									data.put("error", "Please Select The A-Shape Value " + i + " Row");
									return data;
								}
//									if (!shape_status.equals("1")
//											|| (from_date != null && !from_date.trim().equals("")
//													&& !from_date.equals("DD/MM/YYYY"))
//											|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
								if (!shape_status.equals("1")) {
									if (from_date == null || from_date.trim().equals("")
											|| from_date.equals("DD/MM/YYYY")) {
										tx.rollback();
										data.put("error", "Please Enter The A-Shape From Date " + i + " Row");
										return data;
									}
									if (from_date != null || from_date.trim().equals("")
											|| from_date.equals("DD/MM/YYYY")) {
										if (!valid.isValidDate(from_date)) {
											data.put("error", valid.isValidDateMSG + " of The A-Shape From ");

											return data;
										}
									}
									if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
										if (!valid.isValidDate(to_date)) {
											data.put("error", valid.isValidDateMSG + " of The A-Shape To ");

											return data;
										}
									}
									if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

										tx.rollback();

										data.put("error", "Please Enter The A-Shape To Date " + i + " Row");

										return data;

									}

									if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
										if (!valid.isValidDate(to_date)) {
											data.put("error", valid.isValidDateMSG + " of The A-Shape To ");

											return data;
										}
									}
									if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

										data.put("error",
												"A-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i
														+ "");

										return data;

									}

								}
								if (i != 1) {
									for (int k = 1; k < i; k++) {
										String shape_status_pre = request.getParameter("a_status" + k);
										String shape_value_pre = request.getParameter("aShape_value" + k);
										String from_date_pre = request.getParameter("a_from_date" + k);
										String to_date_pre = request.getParameter("a_to_date" + k);
										String diagnosis_3_pre = request.getParameter("_diagnosis_code3" + k);
										if (!diagnosis_3_pre.trim().equals("")) {
											String[] arrOfStr = diagnosis_3_pre.split("-", 2);
											diagnosis_3_pre = arrOfStr[0];

										}

										if (shape_status.equals("1")) {
											if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
													&& from_date_pre.equals(from_date) && diagnosis_3_pre.equals(diagnosis_3)) {
												tx.rollback();
												data.put("error", " Data Already Exists A-Shape  " + i + "th row");
												return data;
											}
										} else {
											if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
													&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_3_pre.equals(diagnosis_3)) {
												tx.rollback();
												data.put("error", " Data Already Exists A-Shape  " + i + "th row");
												return data;
											}
										}

									}
								}
								Mad_cat.setShape("A");

								Mad_cat.setShape_status(Integer.parseInt(shape_status));

								Mad_cat.setShape_value(shape_value);

//									if (!shape_status.equals("1")
								//
//											|| (from_date != null && !from_date.trim().equals("")
								//
//													&& !from_date.equals("DD/MM/YYYY"))
								//
//											|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
								if (!shape_status.equals("1")) {

									Mad_cat.setFrom_date(format.parse(from_date));

									Mad_cat.setTo_date(format.parse(to_date));

								} else if (from_date != null && !from_date.trim().equals("")
										&& !from_date.equals("DD/MM/YYYY")) {
									Mad_cat.setFrom_date(format.parse(from_date));
								}

								Mad_cat.setDiagnosis(diagnosis_3);

								Mad_cat.setClasification(mad_classification);

								Mad_cat.setCen_id(census_id);

								Mad_cat.setComm_id(comm_id);

								Mad_cat.setCreated_by(username);

								Mad_cat.setCreated_on(date);

								Mad_cat.setStatus(0);

								int check_s = check_shape(comm_id_str, "A");
								if (check_s == 0) {
									sessionHQL.save(Mad_cat);
									sessionHQL.flush();
									sessionHQL.clear();
								} else {
									tx.rollback();

									data.put("error", "Data Already Exists");

									return data;
								}
//									sessionHQL.save(Mad_cat);
								//
//									sessionHQL.flush();
								//
//									sessionHQL.clear();

							}

						}

						// //////////////////////////////P SHAPE ////////////////////////////

						for (int i = 1; i <= pShapeOld_count; i++) {

							String shape_status = request.getParameter("p_status" + i);

							String shape_value = request.getParameter("pShape_value" + i);

							String from_date = request.getParameter("p_from_date" + i);

							String to_date = request.getParameter("p_to_date" + i);

							String diagnosis_4 = request.getParameter("_diagnosis_code4" + i);

							String shape_ch = request.getParameter("pShape_ch_id" + i);

							if (!diagnosis_4.trim().equals("")) {

								String[] arrOfStr = diagnosis_4.split("-", 2);

								diagnosis_4 = arrOfStr[0];

							}

							if (shape_status == null || shape_status.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The P-Shape Status " + i + " Row");

								return data;

							}

							if (shape_value == null || shape_value.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The P-Shape Value " + i + " Row");

								return data;

							}

//								if (!shape_status.equals("1")
							//
//										|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
							//
//										|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
							if (!shape_status.equals("1")) {

								if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The P-Shape From Date " + i + " Row");

									return data;

								}
								if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(from_date)) {
										data.put("error", valid.isValidDateMSG + " of The P-Shape From ");

										return data;
									}
								}

								if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The P-Shape To Date " + i + " Row");

									return data;

								}

								if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(to_date)) {
										data.put("error", valid.isValidDateMSG + " of The P-Shape To ");

										return data;
									}
								}
								if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

									data.put("error", "P-Shape To Date Should Be Greater Than Or Equal To From Date In Row "
											+ i + "");

									return data;

								}

							}
							if (i != 1) {

								for (int k = 1; k < i; k++) {
									String shape_status_pre = request.getParameter("p_status" + k);
									String shape_value_pre = request.getParameter("pShape_value" + k);
									String from_date_pre = request.getParameter("p_from_date" + k);
									String to_date_pre = request.getParameter("p_to_date" + k);
									String diagnosis_4_pre = request.getParameter("_diagnosis_code4" + k);
									if (!diagnosis_4_pre.trim().equals("")) {
										String[] arrOfStr = diagnosis_4_pre.split("-", 2);
										diagnosis_4_pre = arrOfStr[0];

									}

									if (!shape_status.equals("1")) {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_4_pre.equals(diagnosis_4)

										) {
											tx.rollback();
											data.put("error", " Data Already Exists P-Shape  " + i + "th row");
											return data;
										}
									} else {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && diagnosis_4_pre.equals(diagnosis_4)

										) {
											tx.rollback();
											data.put("error", " Data Already Exists P-Shape  " + i + "th row");
											return data;
										}
									}
								}
							}
							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

									.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

							Mad_cat.setShape_status(Integer.parseInt(shape_status));

							Mad_cat.setShape_value(shape_value);

//								if (!shape_status.equals("1")
							//
//										|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
							//
//										|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
							if (!shape_status.equals("1")) {

								Mad_cat.setFrom_date(format.parse(from_date));

								Mad_cat.setTo_date(format.parse(to_date));

							} else if (from_date != null && !from_date.trim().equals("")
									&& !from_date.equals("DD/MM/YYYY")) {
								Mad_cat.setFrom_date(format.parse(from_date));
							}

							Mad_cat.setFrom_date_1bx(null);

							Mad_cat.setTo_date_1bx(null);

							Mad_cat.setDiagnosis_1bx(null);

							Mad_cat.setDiagnosis(diagnosis_4);

							Mad_cat.setClasification(mad_classification);

							Mad_cat.setModify_by(username);

							Mad_cat.setModify_on(date);

							Mad_cat.setStatus(0);

							sessionHQL.update(Mad_cat);

							sessionHQL.flush();

							sessionHQL.clear();

						}

						diffrence = pShape_count - pShapeOld_count;

						if (diffrence != 0) {

							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

							for (int i = pShapeOld_count + 1; i <= pShape_count; i++) {

								String shape_status = request.getParameter("p_status" + i);

								String shape_value = request.getParameter("pShape_value" + i);

								String from_date = request.getParameter("p_from_date" + i);

								String to_date = request.getParameter("p_to_date" + i);

								String diagnosis_4 = request.getParameter("_diagnosis_code4" + i);

								if (!diagnosis_4.trim().equals("")) {

									String[] arrOfStr = diagnosis_4.split("-", 2);

									diagnosis_4 = arrOfStr[0];

								}

								if (shape_status == null || shape_status.equals("0")) {

									tx.rollback();

									data.put("error", "Please Select The P-Shape Status " + i + " Row");

									return data;

								}

								if (shape_value == null || shape_value.equals("0")) {

									tx.rollback();

									data.put("error", "Please Select The P-Shape Value " + i + " Row");

									return data;

								}

//									if (!shape_status.equals("1") || (from_date != null && !from_date.trim().equals("")
								//
//											&& !from_date.equals("DD/MM/YYYY"))
								//
//											|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
								if (!shape_status.equals("1")) {

									if (from_date == null || from_date.trim().equals("")
											|| from_date.equals("DD/MM/YYYY")) {

										tx.rollback();

										data.put("error", "Please Enter The P-Shape From Date " + i + " Row");

										return data;

									}
									if (from_date != null || from_date.trim().equals("")
											|| from_date.equals("DD/MM/YYYY")) {
										if (!valid.isValidDate(from_date)) {
											data.put("error", valid.isValidDateMSG + " of The P-Shape From ");

											return data;
										}
									}

									if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

										tx.rollback();

										data.put("error", "Please Enter The P-Shape To Date " + i + " Row");

										return data;

									}

									if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
										if (!valid.isValidDate(to_date)) {
											data.put("error", valid.isValidDateMSG + " of The P-Shape To ");

											return data;
										}
									}
									if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

										data.put("error",
												"P-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i
														+ "");

										return data;

									}

								}
								if (i != 1) {

									for (int k = 1; k < i; k++) {
										String shape_status_pre = request.getParameter("p_status" + k);
										String shape_value_pre = request.getParameter("pShape_value" + k);
										String from_date_pre = request.getParameter("p_from_date" + k);
										String to_date_pre = request.getParameter("p_to_date" + k);
										String diagnosis_4_pre = request.getParameter("_diagnosis_code4" + k);
										if (!diagnosis_4_pre.trim().equals("")) {
											String[] arrOfStr = diagnosis_4_pre.split("-", 2);
											diagnosis_4_pre = arrOfStr[0];

										}

										if (!shape_status.equals("1")) {
											if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
													&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_4_pre.equals(diagnosis_4)

											) {
												tx.rollback();
												data.put("error", " Data Already Exists P-Shape  " + i + "th row");
												return data;
											}
										} else {
											if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
													&& from_date_pre.equals(from_date) && diagnosis_4_pre.equals(diagnosis_4)

											) {
												tx.rollback();
												data.put("error", " Data Already Exists P-Shape  " + i + "th row");
												return data;
											}
										}
									}
								}
								Mad_cat.setShape("P");

								Mad_cat.setShape_status(Integer.parseInt(shape_status));

								Mad_cat.setShape_value(shape_value);

//									if (!shape_status.equals("1")
								//
//											|| (from_date != null && !from_date.trim().equals("")
								//
//													&& !from_date.equals("DD/MM/YYYY"))
								//
//											|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
								if (!shape_status.equals("1")) {

									Mad_cat.setFrom_date(format.parse(from_date));

									Mad_cat.setTo_date(format.parse(to_date));

								} else if (from_date != null && !from_date.trim().equals("")
										&& !from_date.equals("DD/MM/YYYY")) {
									Mad_cat.setFrom_date(format.parse(from_date));
								}

								Mad_cat.setDiagnosis(diagnosis_4);

								Mad_cat.setClasification(mad_classification);

								Mad_cat.setCen_id(census_id);

								Mad_cat.setComm_id(comm_id);

								Mad_cat.setCreated_by(username);

								Mad_cat.setCreated_on(date);

								Mad_cat.setStatus(0);
								int check_s = check_shape(comm_id_str, "P");
								if (check_s == 0) {
									sessionHQL.save(Mad_cat);
									sessionHQL.flush();
									sessionHQL.clear();
								} else {
									tx.rollback();

									data.put("error", "Data Already Exists");

									return data;
								}

							}

						}

						// //////////////////////////////E SHAPE ////////////////////////////

						for (int i = 1; i <= eShapeOld_count; i++) {

							String shape_status = request.getParameter("e_status" + i);

							String shape_value = request.getParameter("eShape_value" + i);

							String from_date = request.getParameter("e_from_date" + i);

							String to_date = request.getParameter("e_to_date" + i);

							String diagnosis_5 = request.getParameter("_diagnosis_code5" + i);

							String shape_ch = request.getParameter("eShape_ch_id" + i);

							if (!diagnosis_5.trim().equals("")) {

								String[] arrOfStr = diagnosis_5.split("-", 2);

								diagnosis_5 = arrOfStr[0];

							}

							if (shape_status == null || shape_status.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The E-Shape Status " + i + " Row");

								return data;

							}

							if (shape_value == null || shape_value.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The E-Shape Value " + i + " Row");

								return data;

							}

//								if (!shape_status.equals("1")
							//
//										|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
							//
//										|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
							if (!shape_status.equals("1")) {

								if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The E-Shape From Date " + i + " Row");

									return data;

								}
								if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(from_date)) {
										data.put("error", valid.isValidDateMSG + " of The E-Shape From ");

										return data;
									}
								}

								if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

									tx.rollback();

									data.put("error", "Please Enter The E-Shape To Date " + i + " Row");

									return data;

								}

								if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
									if (!valid.isValidDate(to_date)) {
										data.put("error", valid.isValidDateMSG + " of The E-Shape To ");

										return data;
									}
								}
								if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

									data.put("error", "E-Shape To Date Should Be Greater Than Or Equal To From Date In Row "
											+ i + "");

									return data;

								}

							}
							if (i != 1) {

								for (int k = 1; k < i; k++) {
									String shape_status_pre = request.getParameter("e_status" + k);
									String shape_value_pre = request.getParameter("eShape_value" + k);
									String from_date_pre = request.getParameter("e_from_date" + k);
									String to_date_pre = request.getParameter("e_to_date" + k);
									String diagnosis_5_pre = request.getParameter("_diagnosis_code5" + k);
									if (!diagnosis_5_pre.trim().equals("")) {
										String[] arrOfStr = diagnosis_5_pre.split("-", 2);
										diagnosis_5_pre = arrOfStr[0];

									}

									if (!shape_status.equals("1")) {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_5_pre.equals(diagnosis_5)) {
											tx.rollback();
											data.put("error", " Data Already Exists E-Shape  " + i + "th row");
											return data;
										}
									} else {
										if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
												&& from_date_pre.equals(from_date) && diagnosis_5_pre.equals(diagnosis_5)) {
											tx.rollback();
											data.put("error", " Data Already Exists E-Shape  " + i + "th row");
											return data;
										}
									}

								}
							}
							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

									.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));

							Mad_cat.setShape_status(Integer.parseInt(shape_status));

							Mad_cat.setShape_value(shape_value);

//								if (!shape_status.equals("1")
							//
//										|| (from_date != null && !from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY"))
							//
//										|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
							if (!shape_status.equals("1")) {

								Mad_cat.setFrom_date(format.parse(from_date));

								Mad_cat.setTo_date(format.parse(to_date));

							} else if (from_date != null && !from_date.trim().equals("")
									&& !from_date.equals("DD/MM/YYYY")) {
								Mad_cat.setFrom_date(format.parse(from_date));
							}

							Mad_cat.setFrom_date_1bx(null);
							Mad_cat.setTo_date_1bx(null);
							Mad_cat.setDiagnosis_1bx(null);
							Mad_cat.setDiagnosis(diagnosis_5);
							Mad_cat.setClasification(mad_classification);
							Mad_cat.setModify_by(username);
							Mad_cat.setModify_on(date);
							Mad_cat.setStatus(0);

							sessionHQL.update(Mad_cat);

							sessionHQL.flush();

							sessionHQL.clear();

						}

						diffrence = eShape_count - eShapeOld_count;

						if (diffrence != 0) {

							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

							for (int i = eShapeOld_count + 1; i <= eShape_count; i++) {

								String shape_status = request.getParameter("e_status" + i);

								String shape_value = request.getParameter("eShape_value" + i);

								String from_date = request.getParameter("e_from_date" + i);

								String to_date = request.getParameter("e_to_date" + i);

								String diagnosis_5 = request.getParameter("_diagnosis_code5" + i);

								if (!diagnosis_5.trim().equals("")) {

									String[] arrOfStr = diagnosis_5.split("-", 2);

									diagnosis_5 = arrOfStr[0];

								}

								if (shape_status == null || shape_status.equals("0")) {

									tx.rollback();

									data.put("error", "Please Select The E-Shape Status " + i + " Row");

									return data;

								}

								if (shape_value == null || shape_value.equals("0")) {

									tx.rollback();

									data.put("error", "Please Select The E-Shape Value " + i + " Row");

									return data;

								}

//									if (!shape_status.equals("1")
								//
//											|| (from_date != null && !from_date.trim().equals("")
								//
//													&& !from_date.equals("DD/MM/YYYY"))
								//
//											|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
								if (!shape_status.equals("1")) {

									if (from_date == null || from_date.trim().equals("")
											|| from_date.equals("DD/MM/YYYY")) {

										tx.rollback();

										data.put("error", "Please Enter The E-Shape From Date " + i + " Row");

										return data;

									}
									if (from_date != null || from_date.trim().equals("")
											|| from_date.equals("DD/MM/YYYY")) {
										if (!valid.isValidDate(from_date)) {
											data.put("error", valid.isValidDateMSG + " of The E-Shape From ");

											return data;
										}
									}

									if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {

										tx.rollback();

										data.put("error", "Please Enter The E-Shape To Date " + i + " Row");

										return data;

									}

									if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
										if (!valid.isValidDate(to_date)) {
											data.put("error", valid.isValidDateMSG + " of The E-Shape To ");

											return data;
										}
									}
									if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {

										data.put("error",
												"E-Shape To Date Should Be Greater Than Or Equal To From Date In Row " + i
														+ "");

										return data;

									}

								}
								if (i != 1) {

									for (int k = 1; k < i; k++) {
										String shape_status_pre = request.getParameter("e_status" + k);
										String shape_value_pre = request.getParameter("eShape_value" + k);
										String from_date_pre = request.getParameter("e_from_date" + k);
										String to_date_pre = request.getParameter("e_to_date" + k);
										String diagnosis_5_pre = request.getParameter("_diagnosis_code5" + k);								

										if (!diagnosis_5_pre.trim().equals("")) {
											String[] arrOfStr = diagnosis_5_pre.split("-", 2);
											diagnosis_5_pre = arrOfStr[0];

										}
										
										if (!shape_status.equals("1")) {
											if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
													&& from_date_pre.equals(from_date) && to_date_pre.equals(to_date) && diagnosis_5_pre.equals(diagnosis_5)) {
												tx.rollback();
												data.put("error", " Data Already Exists E-Shape  " + i + "th row");
												return data;
											}
										} else {
											if (shape_status_pre.equals(shape_status) && shape_value_pre.equals(shape_value)
													&& from_date_pre.equals(from_date) && diagnosis_5_pre.equals(diagnosis_5) ) {
												tx.rollback();
												data.put("error", " Data Already Exists E-Shape  " + i + "th row");
												return data;
											}
										}

									}
								}
								Mad_cat.setShape("E");

								Mad_cat.setShape_status(Integer.parseInt(shape_status));

								Mad_cat.setShape_value(shape_value);

//									if (!shape_status.equals("1")
								//
//											|| (from_date != null && !from_date.trim().equals("")
								//
//													&& !from_date.equals("DD/MM/YYYY"))
								//
//											|| (to_date != null && !to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY"))) {
								if (!shape_status.equals("1")) {

									Mad_cat.setFrom_date(format.parse(from_date));

									Mad_cat.setTo_date(format.parse(to_date));

								}

								Mad_cat.setDiagnosis(diagnosis_5);

								Mad_cat.setClasification(mad_classification);

								Mad_cat.setCen_id(census_id);

								Mad_cat.setComm_id(comm_id);

								Mad_cat.setCreated_by(username);

								Mad_cat.setCreated_on(date);

								Mad_cat.setStatus(0);
								int check_s = check_shape(comm_id_str, "E");
								if (check_s == 0) {
									sessionHQL.save(Mad_cat);
									sessionHQL.flush();
									sessionHQL.clear();
								} else {
									tx.rollback();

									data.put("error", "Data Already Exists");

									return data;
								}

							}

						}

						// //////////////////////////////C COPE //////////////////////////////

						for (int i = 1; i <= cCopeOld_count; i++) {

							String cope_ch = request.getParameter("cCope_ch_id" + i);

							String cope_value = request.getParameter("c_cvalue" + i);

							String cope_other = request.getParameter("c_cother" + i);

							if (cope_value.equals("2 [c]") && cope_other.trim().equals("")) {

								tx.rollback();

								data.put("error", "Please Enter Other " + i + " Row");

								return data;

							}

							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

									.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

							if (cope_value.equals("2 [c]")) {

								Mad_cat.setOther(cope_other);

							} else {

								Mad_cat.setOther("");

							}
							if (i != 1) {
								for (int k = 1; k < i; k++) {
									String cope_value_pre = request.getParameter("c_cvalue" + k);
									String cope_other_pre = request.getParameter("c_cother" + k);
									if (cope_value_pre.equals(cope_value)) {
										if (cope_other_pre != null && cope_other != null) {
											if (cope_other_pre.equals(cope_other)) {
												tx.rollback();
												data.put("error", "Data Already Exists C_C  " + i + "th row");
												return data;
											}
										} else {
											tx.rollback();
											data.put("error", "Data Already Exists C_C  " + i + "th row");
											return data;
										}

									}
								}

							}
							Mad_cat.setFrom_date_1bx(null);

							Mad_cat.setTo_date_1bx(null);

							Mad_cat.setDiagnosis_1bx(null);

							Mad_cat.setShape_value(cope_value);

							Mad_cat.setClasification(mad_classification);

							Mad_cat.setModify_by(username);

							Mad_cat.setModify_on(date);

							Mad_cat.setStatus(0);

							sessionHQL.update(Mad_cat);

							sessionHQL.flush();

							sessionHQL.clear();

						}

						diffrence = cCope_count - cCopeOld_count;

						if (diffrence != 0) {

							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

							for (int i = cCopeOld_count + 1; i <= cCope_count; i++) {

								String cope_value = request.getParameter("c_cvalue" + i);

								String cope_other = request.getParameter("c_cother" + i);

								if (cope_value.equals("2 [c]") && cope_other.trim().equals("")) {

									tx.rollback();

									data.put("error", "Please Enter C-Cope Other " + i + " Row");

									return data;

								}

								if (cope_value.equals("2 [c]")) {

									Mad_cat.setOther(cope_other);

								} else {

									Mad_cat.setOther(null);

								}
								if (i != 1) {
									for (int k = 1; k < i; k++) {
										String cope_value_pre = request.getParameter("c_cvalue" + k);
										String cope_other_pre = request.getParameter("c_cother" + k);
										if (cope_value_pre.equals(cope_value)) {
											if (cope_other_pre != null && cope_other != null) {
												if (cope_other_pre.equals(cope_other)) {
													tx.rollback();
													data.put("error", " Data Already Exists C_C  " + i + "th row");
													return data;
												}
											} else {
												tx.rollback();
												data.put("error", " Data Already Exists C_C  " + i + "th row");
												return data;
											}

										}
									}

								}

								Mad_cat.setShape("C_C");

								Mad_cat.setShape_value(cope_value);

								Mad_cat.setClasification(mad_classification);

								Mad_cat.setCen_id(census_id);

								Mad_cat.setComm_id(comm_id);

								Mad_cat.setCreated_by(username);

								Mad_cat.setCreated_on(date);

								Mad_cat.setStatus(0);
								int check_s = check_shape(comm_id_str, "C_C");
								if (check_s == 0) {
									sessionHQL.save(Mad_cat);
									sessionHQL.flush();
									sessionHQL.clear();
								} else {
									tx.rollback();

									data.put("error", "Data Already Exists");

									return data;
								}

							}

						}

						// //////////////////////////////O COPE //////////////////////////////

						for (int i = 1; i <= oCopeOld_count; i++) {

							String cope_ch = request.getParameter("oCope_ch_id" + i);

							String cope_value = request.getParameter("c_ovalue" + i);
							if (i != 1) {
								for (int k = 1; k < i; k++) {
									String cope_value_pre = request.getParameter("c_ovalue" + k);
									if (cope_value_pre.equals(cope_value)) {
										tx.rollback();
										data.put("error", "Data Already exist C_O " + i + "th row");
										return data;
									}
								}
							}

							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

									.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

							Mad_cat.setFrom_date_1bx(null);

							Mad_cat.setTo_date_1bx(null);

							Mad_cat.setDiagnosis_1bx(null);

							Mad_cat.setShape_value(cope_value);

							Mad_cat.setClasification(mad_classification);

							Mad_cat.setModify_by(username);

							Mad_cat.setModify_on(date);

							Mad_cat.setStatus(0);

							sessionHQL.update(Mad_cat);

							sessionHQL.flush();

							sessionHQL.clear();

						}

						diffrence = oCope_count - oCopeOld_count;

						if (diffrence != 0) {

							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

							for (int i = oCopeOld_count + 1; i <= oCope_count; i++) {

								String cope_value = request.getParameter("c_ovalue" + i);

								Mad_cat.setShape("C_O");

								Mad_cat.setShape_value(cope_value);

								Mad_cat.setClasification(mad_classification);

								Mad_cat.setCen_id(census_id);

								Mad_cat.setComm_id(comm_id);

								Mad_cat.setCreated_by(username);

								Mad_cat.setCreated_on(date);

								Mad_cat.setStatus(0);
								if (i != 1) {
									for (int k = 1; k < i; k++) {
										String cope_value_pre = request.getParameter("c_ovalue" + k);
										if (cope_value_pre.equals(cope_value)) {
											tx.rollback();
											data.put("error", "Data Already exist C_O " + i + "th row ");
											return data;
										}
									}
								}
								int check_s = check_shape(comm_id_str, "C_O");
								if (check_s == 0) {
									sessionHQL.save(Mad_cat);
									sessionHQL.flush();
									sessionHQL.clear();
								} else {
									tx.rollback();

									data.put("error", "Data Already Exists");

									return data;
								}
//									sessionHQL.save(Mad_cat);
								//
//									sessionHQL.flush();
								//
//									sessionHQL.clear();

							}

						}

						// //////////////////////////////P COPE //////////////////////////////

						for (int i = 1; i <= pCopeOld_count; i++) {

							String cope_ch = request.getParameter("pCope_ch_id" + i);

							String cope_value = request.getParameter("c_pvalue" + i);

							if (i != 1) {

								for (int k = 1; k < i; k++) {
									String cope_value_pre = request.getParameter("c_pvalue" + k);
									if (cope_value_pre.equals(cope_value)) {
										tx.rollback();
										data.put("error", "Data Already exist C_P " + i + "th row ");
										return data;
									}
								}
							}
							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

									.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

							Mad_cat.setFrom_date_1bx(null);

							Mad_cat.setTo_date_1bx(null);

							Mad_cat.setDiagnosis_1bx(null);

							Mad_cat.setShape_value(cope_value);

							Mad_cat.setClasification(mad_classification);

							Mad_cat.setModify_by(username);

							Mad_cat.setModify_on(date);

							Mad_cat.setStatus(0);

							sessionHQL.update(Mad_cat);

							sessionHQL.flush();

							sessionHQL.clear();

						}

						diffrence = pCope_count - pCopeOld_count;

						if (diffrence != 0) {

							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

							for (int i = pCopeOld_count + 1; i <= pCope_count; i++) {

								String cope_value = request.getParameter("c_pvalue" + i);
								if (i != 1) {
									for (int k = 1; k < i; k++) {
										String cope_value_pre = request.getParameter("c_pvalue" + k);
										if (cope_value_pre.equals(cope_value)) {
											tx.rollback();
											data.put("error", "Data Already exist C_P " + i + "th row ");
											return data;
										}
									}
								}

								Mad_cat.setShape("C_P");

								Mad_cat.setShape_value(cope_value);

								Mad_cat.setClasification(mad_classification);

								Mad_cat.setCen_id(census_id);

								Mad_cat.setComm_id(comm_id);

								Mad_cat.setCreated_by(username);

								Mad_cat.setCreated_on(date);

								Mad_cat.setStatus(0);
								int check_s = check_shape(comm_id_str, "C_P");
								if (check_s == 0) {
									sessionHQL.save(Mad_cat);
									sessionHQL.flush();
									sessionHQL.clear();
								} else {
									tx.rollback();

									data.put("error", "Data Already Exists");

									return data;
								}
//									sessionHQL.save(Mad_cat);
								//
//									sessionHQL.flush();
								//
//									sessionHQL.clear();

							}

						}

						// //////////////////////////////E COPE //////////////////////////////

						for (int i = 1; i <= eCopeOld_count; i++) {

							String cope_ch = request.getParameter("eCope_ch_id" + i);

							String cope_value = request.getParameter("c_evalue" + i);

							String cope_sub_value = request.getParameter("c_esubvalue" + i);

							String cope_other = request.getParameter("c_esubvalueother" + i);

							if (cope_value.equals("1") && cope_sub_value.equals("0")) {

								tx.rollback();

								data.put("error", "Please Select The Cope Sub Value " + i + " Row");

								return data;

							}

							if (cope_sub_value.equals("k") && cope_other.trim().equals("")) {

								tx.rollback();

								data.put("error", "Please Enter Other " + i + " Row");

								return data;

							}

							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) sessionHQL

									.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

							if (cope_value.equals("1")) {

								Mad_cat.setShape_sub_value(cope_sub_value);

							} else {

								Mad_cat.setShape_sub_value(null);

							}

							if (cope_sub_value.equals("k")) {

								Mad_cat.setOther(cope_other);

							} else {

								Mad_cat.setOther(null);

							}

							if (i != 1) {
								for (int k = 1; k < i; k++) {
									String cope_value_pre = request.getParameter("c_evalue" + k);
									String cope_sub_value_pre = request.getParameter("c_esubvalue" + k);
									if (cope_value_pre.equals(cope_value) && cope_value != null && cope_sub_value != null) {
										if (cope_value_pre.equals(cope_value)
												&& cope_sub_value_pre.equals(cope_sub_value)) {
											tx.rollback();
											data.put("error", "Data Already Exist For E-Cope Sub Value " + i + " Row");
											return data;
										}
									}
									if (cope_value_pre.equals(cope_value) && cope_value.equals(null)
											&& cope_sub_value.equals(null)) {
										tx.rollback();
										data.put("error", "Data Already Exist For E-Cope Sub Value " + i + " Row");
										return data;
									}
								}
							}

							Mad_cat.setFrom_date_1bx(null);

							Mad_cat.setTo_date_1bx(null);

							Mad_cat.setDiagnosis_1bx(null);

							Mad_cat.setShape_value(cope_value);

							Mad_cat.setClasification(mad_classification);

							Mad_cat.setModify_by(username);

							Mad_cat.setModify_on(date);

							Mad_cat.setStatus(0);

							sessionHQL.update(Mad_cat);

							sessionHQL.flush();

							sessionHQL.clear();

						}

						diffrence = eCope_count - eCopeOld_count;

						if (diffrence != 0) {

							TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

							for (int i = eCopeOld_count + 1; i <= eCope_count; i++) {

								String cope_value = request.getParameter("c_evalue" + i);

								String cope_sub_value = request.getParameter("c_esubvalue" + i);

								String cope_other = request.getParameter("c_esubvalueother" + i);

								if (cope_value.equals("1") && cope_sub_value.equals("0")) {

									tx.rollback();

									data.put("error", "Please Select The E-Cope Sub Value " + i + " Row");

									return data;

								}

								if (cope_sub_value.equals("k") && cope_other.trim().equals("")) {

									tx.rollback();

									data.put("error", "Please Enter E-Cope Other " + i + " Row");

									return data;

								}

								if (cope_value.equals("1")) {

									Mad_cat.setShape_sub_value(cope_sub_value);

								} else {

									Mad_cat.setShape_sub_value("0");

								}

								if (cope_sub_value.equals("k")) {

									Mad_cat.setOther(cope_other);

								} else {

									Mad_cat.setOther("");

								}

								if (i != 1) {
									for (int k = 1; k < i; k++) {
										String cope_value_pre = request.getParameter("c_evalue" + k);
										String cope_sub_value_pre = request.getParameter("c_esubvalue" + k);
										if (cope_value_pre.equals(cope_value) && cope_value != null
												&& cope_sub_value != null) {
											if (cope_value_pre.equals(cope_value)
													&& cope_sub_value_pre.equals(cope_sub_value)) {
												tx.rollback();
												data.put("error", "Data Already Exist For E-Cope Sub Value " + i + " Row");
												return data;
											}
										}
										if (cope_value_pre.equals(cope_value) && cope_value.equals(null)
												&& cope_sub_value.equals(null)) {
											tx.rollback();
											data.put("error", "Data Already Exist For E-Cope Sub Value " + i + " Row");
											return data;
										}
									}
								}

								Mad_cat.setShape("C_E");

								Mad_cat.setShape_value(cope_value);

								Mad_cat.setClasification(mad_classification);

								Mad_cat.setCen_id(census_id);

								Mad_cat.setComm_id(comm_id);

								Mad_cat.setCreated_by(username);

								Mad_cat.setCreated_on(date);

								Mad_cat.setStatus(0);
								int check_s = check_shape(comm_id_str, "C_E");
								if (check_s == 0) {
									sessionHQL.save(Mad_cat);
									sessionHQL.flush();
									sessionHQL.clear();
								} else {
									tx.rollback();
									data.put("error", "Data Already Exists");
									return data;
								}
//									sessionHQL.save(Mad_cat);
								//
//									sessionHQL.flush();
								//
//									sessionHQL.clear();

							}

						}

					}
				}

				tx.commit();

				data.put("census_id", String.valueOf(census_id));

				data.put("i_id", String.valueOf(i_id));

			} catch (RuntimeException e) {

				try {

					tx.rollback();

					data.put("error", "Data Not Updated");

				} catch (RuntimeException rbe) {

					data.put("error", "Data Not Updated");

				}

			} finally {

				if (sessionHQL != null) {

					sessionHQL.close();

				}

			}

			return data;

		}
	
//	----------------- end pranay 07.06
}
