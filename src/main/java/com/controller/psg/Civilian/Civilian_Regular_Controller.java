package com.controller.psg.Civilian;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.SysexMessage;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.util.RLEDecompressingInputStream;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.controller.Dashboard.PsgDashboardController;
import com.controller.ExportFile.Download_Record_Service;
import com.controller.ExportFile.Download__Civilian_regular;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Jco_Update_Census.Pan_No_UpdateController_JCO;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Civilian.Civilian_dtlDAO;
import com.dao.psg.Civilian_Report.Arm_Service_Wise_RegularEst_Dao;
import com.models.psg.Civilian.TB_CIVILIAN_DETAILS;
import com.models.psg.Civilian.TB_CIVILIAN_MAIN;
import com.models.psg.Civilian.TB_POSTING_IN_OUT_CIVILIAN;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Civilian_Regular_Controller {

	Psg_CommonController p_comm = new Psg_CommonController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();

	ValidationController valid = new ValidationController();

	PsgDashboardController das = new PsgDashboardController();
	
	 
	@Autowired
	private Civilian_dtlDAO cdao;
	
	@Autowired
	private Arm_Service_Wise_RegularEst_Dao regdao; 

//	@Autowired
//	private Report_3008DAO report_3008DAO;

	@Autowired
	private RoleBaseMenuDAO roledao;

//	@Autowired
//	private Search_PostOutDao pod;

	Pattern pattern = Pattern.compile(".*[^0-9].*");

	@RequestMapping(value = "/admin/Search_civilian_regular", method = RequestMethod.GET)
	public ModelAndView Search_civilian_regular(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		String roleType = session.getAttribute("roleType").toString();
		Boolean val = roledao.ScreenRedirect("Search_civilian_regular", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}

		Mmap.put("list", cdao.Search_Civilian_regular(roleSusNo, roleType, "", "", "",0, "","","", session));
		Mmap.put("status", "0");

		Mmap.put("msg", msg);
		Mmap.put("roleType", roleType);
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		return new ModelAndView("Civilian_Regular_SearchlTiles");
	}

	@RequestMapping(value = "/admin/getSearch_regular", method = RequestMethod.POST)
	public ModelAndView getSearch_regular(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "first_name1", required = false) String first_name,
			@RequestParam(value = "personnel_no1", required = false) String personnel_no,
			@RequestParam(value = "status1", required = false) int status,
			@RequestParam(value = "cr_by1", required = false) String cr_by,
		    @RequestParam(value = "cr_date1", required = false) String cr_date) {

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		String roleType = session.getAttribute("roleType").toString();
		String roleid = session.getAttribute("roleid").toString();

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		if (!roleAccess.equals("Unit")) {
			roleSusNo = unit_sus_no;
			if (unit_sus_no != "") {
				if (!valid.SusNoLength(unit_sus_no)) {
					Mmap.put("msg", valid.SusNoMSG);
					return new ModelAndView("redirect:Search_civilian_regular");
				}
			}
		}
		if (!roleAccess.equals("Unit")) {
			if (unit_name != "") {
				if (!valid.isUnit(unit_name)) {
					Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
					return new ModelAndView("redirect:Search_civilian_regular");
				}

//				if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//					Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//					return new ModelAndView("redirect:Search_civilian_regular");
//				}
			}
		}
		if (!valid.isOnlyAlphabet(first_name)) {
			Mmap.put("msg", " Name " + valid.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:Search_civilian_regular");
		}
		if (!valid.isvalidLength(first_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Name " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:Search_civilian_regular");
		}

		Boolean val = roledao.ScreenRedirect("Search_civilian_regular", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		Mmap.put("list",
				cdao.Search_Civilian_regular(roleSusNo, roleType, unit_sus_no, unit_name, first_name, status,personnel_no,cr_by,cr_date, session));
		Mmap.put("unit_sus_no", unit_sus_no);
		Mmap.put("unit_name", unit_name);
		Mmap.put("first_name", first_name);
		Mmap.put("emp_no", personnel_no);
		Mmap.put("status", status);
		Mmap.put("roleType", roleType);
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		Mmap.put("cr_date1", cr_date);
		Mmap.put("cr_by1", cr_by);
		return new ModelAndView("Civilian_Regular_SearchlTiles");
	}

	@RequestMapping(value = "/admin/civilianRegularUrl", method = RequestMethod.GET)
	public ModelAndView civilianRegularUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Mmap.put("roleSusNo", roleSusNo);
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("civilianRegularUrl", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));

		Mmap.put("msg", msg);
		Mmap.put("getGenderList", p_comm.getGenderList());
		Mmap.put("getMothertoungeList", p_comm.getMothertoungeList());
		Mmap.put("getPayLevelList", p_comm.getPayLevelList());
		Mmap.put("getReligionList", p_comm.getReligionList());
		Mmap.put("getNationalityList", p_comm.getNationalityList());
		Mmap.put("getRegNonEff", p_comm.getRegNonEff());
		Mmap.put("getCadre", p_comm.getCadre());
		Mmap.put("getCategoryList", p_comm.getCategoryList());
		Mmap.put("getClassificationOfTradesList", p_comm.getClassificationOfTradesList());
		Mmap.put("getCountryList", p_comm.getCountryList());
		Mmap.put("getDesignationList", p_comm.getDesignationList());
		Mmap.put("getExservicemenList", p_comm.getExservicemenList());
		Mmap.put("getCivilianTypeList", p_comm.getCivilianTypeList());
		Mmap.put("getServiceStatusList", p_comm.getServiceStatusList());
		Mmap.put("getPersonWithDisabilityList", p_comm.getPersonWithDisabilityList());
		Mmap.put("getClassificationOfServiceList", p_comm.getClassificationOfServiceList());
		Mmap.put("getCivHigh_Qualification", p_comm.getCivHigh_Qualification());

		return new ModelAndView("civilianRegularTiles");
	}

	@RequestMapping(value = "/admin/civilianRegularAction", method = RequestMethod.POST)
	public ModelAndView civilianRegularAction(@ModelAttribute("civilian_details_cmd") TB_CIVILIAN_DETAILS obj,
			BindingResult result, ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg1,MultipartHttpServletRequest mul)
			throws ParseException, IOException {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("civilianRegularUrl", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg1 = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		String fname = "";
		String extension = "";
		Date date = new Date();
		Date dob_dt = null;
		Date joining_date_gov_service_dt = null;
		Date designation_date_dt = null;
		Date date_non_effective_dt = null;
		Date dt_authority = null;
		Date date_tos = null;

		String username = session.getAttribute("username").toString();
		String unit_sus_no = request.getParameter("unit_sus_no");
		String emp_no = request.getParameter("employee_no");	
		String authority = request.getParameter("authority");
		String dt_of_authority = request.getParameter("dt_of_authority");
		String pan_no = request.getParameter("pan_no");
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String name1 = first_name + " " + middle_name + " " + last_name;
		String gender = request.getParameter("gender");
		String gender_hid = request.getParameter("gender_hid");
		String classification_services = request.getParameter("classification_services");
		String group = request.getParameter("group");
		String category_belongs = request.getParameter("category_belongs");
		String service_status = request.getParameter("service_status");
		String classification_trade = request.getParameter("classification_trade");
		String classification_trade_hid = request.getParameter("classification_of_trade_hid");
		String whether_ex_serviceman_hid = request.getParameter("whether_ex_serviceman_hid");
		String type = request.getParameter("type");
		String wes = request.getParameter("wes");
		String whether_ex_serviceman = request.getParameter("whether_ex_serviceman");
		String wpwd = request.getParameter("wpwd");
		String whether_person_disability = request.getParameter("whether_person_disability");
		String post_initialy_appointed = request.getParameter("post_initialy_appointed");
		String joining_date_gov_service = request.getParameter("joining_date_gov_service");
		String designation = request.getParameter("designation");
		String designation_date = request.getParameter("designation_date");
		String pay_level = request.getParameter("pay_level");
		String father_name = request.getParameter("father_name");
		String mother_name = request.getParameter("mother_name");
		String country_original = request.getParameter("country_original");
		String country_original_hid = request.getParameter("country_original_hid");
		String state_original = request.getParameter("original_state");
		String state_original_hid = request.getParameter("original_state_hid");
		String district_original = request.getParameter("district_original");
		String district_original_hid = request.getParameter("district_original_hid");
		String tehsil_origin = request.getParameter("original_tehsil");
		String tehsil_origin_hid = request.getParameter("original_tehsil_hid");
		String country_present = request.getParameter("country_present");
		String country_present_hid = request.getParameter("country1_hid");
		String state_present = request.getParameter("state_present");
		String state_present_hid = request.getParameter("present_state_hid");
		String district_present = request.getParameter("district_present");
		String district_present_hid = request.getParameter("present_district_hid");
		String tehsil_present = request.getParameter("tehsil_present");
		String tehsil_present_hid = request.getParameter("tehsil_present_hid");
		String nationality = request.getParameter("nationality");
		String nationality_hid = request.getParameter("nationality_hid");
		String religion = request.getParameter("religion");
		String religion_hid = request.getParameter("religion_hid");
		String mother_tongue = request.getParameter("mother_tongue");
		String mother_tongue_hid = request.getParameter("mother_tongue_hid");
		String eff = request.getParameter("eff");
		String gender_other = request.getParameter("gender_other");
		String cadre = request.getParameter("cadre");
		String original_country_other = request.getParameter("original_country_other");
		String original_state_other = request.getParameter("original_state_other");
		String original_district_other = request.getParameter("original_district_other");
		String original_tehshil_other = request.getParameter("original_tehshil_other");
		String present_country_other = request.getParameter("present_country_other");
		String present_state_other = request.getParameter("present_state_other");
		String present_district_other = request.getParameter("present_district_other");
		String present_tehshil_other = request.getParameter("present_tehshil_other");
		String classification_trade_other = request.getParameter("classification_of_trade_other");
		String religion_other = request.getParameter("religion_other");
		String mother_tongue_other = request.getParameter("mother_tongue_other");
		String nationality_other = request.getParameter("nationality_other");
		String aadhar_card = "";
		String service_other = request.getParameter("service_other");
		String date_of_tos = request.getParameter("date_of_tos");
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String coc_ch_id = request.getParameter("id");
		String highest_qualification = request.getParameter("high_qual");
		String mobile_no = request.getParameter("mobile_no");

		if (!request.getParameter("pers_aadhar_no11").equals("") & !request.getParameter("pers_aadhar_no2").equals("")
				|| !request.getParameter("pers_aadhar_no3").equals("")) {
			String pers_aadhar_no11 = request.getParameter("pers_aadhar_no11");
			String pers_aadhar_no2 = request.getParameter("pers_aadhar_no2");
			String pers_aadhar_no3 = request.getParameter("pers_aadhar_no3");
			aadhar_card = pers_aadhar_no11 + pers_aadhar_no2 + pers_aadhar_no3;
		}

		String non_effective = request.getParameter("non_effective");
		String date_non_effective = request.getParameter("date_non_effective");
		String dob = request.getParameter("dob");

		String status = "R";
		
		if (authority == null || authority.equals("")) {
			Mmap.put("msg", "Please Enter Authority");
			return new ModelAndView("redirect:civilianRegularUrl");
		}
		if (!valid.isValidAuth(authority)) {
			Mmap.put("msg", valid.isValidAuthMSG + "Authority");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isvalidLength(authority, valid.authorityMax, valid.authorityMin)) {
			Mmap.put("msg", "Authority " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:civilianRegularUrl");
		}
		
		if (dt_of_authority == null || dt_of_authority.equals("null") || dt_of_authority.equals("DD/MM/YYYY") || dt_of_authority.equals("")) {
			Mmap.put("msg", "Please Select Date of Authority");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!dt_of_authority.equals("") || dt_of_authority != "") {

			if (!valid.isValidDate(dt_of_authority)) {
				Mmap.put("msg", valid.isValidDateMSG + " of Authority");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				dt_authority = format.parse(dt_of_authority);
			}
		}

		if (!roleAccess.equals("Unit")) {
			if (unit_sus_no.equals("")) {
				Mmap.put("msg", "Please Enter Unit Sus No.");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				roleSusNo = unit_sus_no;
			}
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		}
		if (emp_no == null || emp_no.trim().equals("")) {
			Mmap.put("msg", "Please Enter Employee No.");
			return new ModelAndView("redirect:civilianRegularUrl");
		}
		

		if (!valid.isValidAuth(emp_no)) {
			Mmap.put("msg", valid.isValidAuthMSG + "Employee No");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isvalidLength(emp_no, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Employee No " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:civilianRegularUrl");
		}
		
		if (mobile_no == null || mobile_no.trim().equals("")) {
			Mmap.put("msg", "Please Enter Mobile No.");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (first_name == null || first_name.trim().equals("") || first_name.equals(null)) {
			Mmap.put("msg", "Please Enter First Name");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isOnlyAlphabet(first_name)) {
			Mmap.put("msg", " First Name " + valid.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isvalidLength(first_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "First Name " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isOnlyAlphabet(middle_name)) {
			Mmap.put("msg", " Middle Name " + valid.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isvalidLength(middle_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Middle Name " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isOnlyAlphabet(last_name)) {
			Mmap.put("msg", " Last Name " + valid.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isvalidLength(last_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Last Name " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isValidDate(dob)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Birth");
			return new ModelAndView("redirect:civilianRegularUrl");
		}
		if (dob == null || dob.equals("null") || dob.equals("DD/MM/YYYY") || dob.equals("")) {
			Mmap.put("msg", "Please Select Date of Birth");
			return new ModelAndView("redirect:civilianRegularUrl");

		} else {
			dob_dt = format.parse(dob);
		}

		if (p_comm.calculate_age(format.parse(dob), date) < 18
				|| p_comm.calculate_age(format.parse(dob), date) >= 60) {
			Mmap.put("msg", "Please Enter Age Above 18");
			return new ModelAndView("redirect:civilianRegularUrl");
		}
		if (obj.getGender() == 0) {
			Mmap.put("msg", "Please Select Gender");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (gender_hid.toUpperCase().equals("OTHER") || gender_hid.toUpperCase() == "OTHER") {
			if (gender_other == null || gender_other.trim().equals("") || gender_other.equals("null")) {
				Mmap.put("msg", "Please Enter Gender Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setGender_other(gender_other);
			}

			if (!valid.isOnlyAlphabet(gender_other)) {
				Mmap.put("msg", " Gender Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(gender_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Gender Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setGender_other("");
		}

		if (classification_services == null) {
			Mmap.put("msg", "Please Select Classification of Services");
			return new ModelAndView("redirect:civilianRegularUrl");
		}
		if (group == null) {
			Mmap.put("msg", "Please Select Group");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (obj.getCategory_belongs() == 0) {
			Mmap.put("msg", "Please Select Category");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (service_status == null) {
			Mmap.put("msg", "Please Select Service Status");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (classification_services == "1") {
			if (obj.getClassification_trade() == 0) {
				Mmap.put("msg", "Please Select Classification of Trade");
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		}
		if (classification_trade_hid.toUpperCase().equals("OTHERS")
				|| classification_trade_hid.toUpperCase() == "OTHERS") {
			if (classification_trade_other == null || classification_trade_other.equals("")
					|| classification_trade_other.equals("null")) {
				Mmap.put("msg", "Please Enter Classification of Trade Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setClassification_trade_other(classification_trade_other);
			}

			if (!valid.isOnlyAlphabet(classification_trade_other)) {
				Mmap.put("msg", " Classification of Trade Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(classification_trade_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Classification of Trade Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setClassification_trade_other("");
		}
		if (type == null) {
			Mmap.put("msg", "Please Select Type");
			return new ModelAndView("redirect:civilianRegularUrl");
		}
		if (wes.equals("yes")) {
			if (whether_ex_serviceman == "0") {
				Mmap.put("msg", "Please Select Whether Ex-Serviceman");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setWhether_ex_serviceman(whether_ex_serviceman);
			}
		} else {
			obj.setWhether_ex_serviceman("");
		}

		if (whether_ex_serviceman_hid.toUpperCase().equals("OTHER")
				|| whether_ex_serviceman_hid.toUpperCase() == "OTHER") {

			if (service_other == null || service_other.equals("") || service_other.equals("null")) {
				Mmap.put("msg", "Please Enter Service Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setService_other(service_other);
			}

			if (!valid.isOnlyAlphabet(service_other)) {
				Mmap.put("msg", " Service Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(service_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Service Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setService_other("");
		}

		if (wpwd.equals("yes")) {
			if (whether_person_disability == "0") {
				Mmap.put("msg", "Please Select Whether Person With Disability");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setWhether_person_disability(whether_person_disability);
			}
		}

		else {
			obj.setWhether_person_disability("");
		}

		if (!valid.isValidDate(joining_date_gov_service)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Joining Govt Service");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (joining_date_gov_service == null || joining_date_gov_service.equals("null")
				|| joining_date_gov_service.equals("DD/MM/YYYY") || joining_date_gov_service.equals("")) {
			Mmap.put("msg", "Please Select Date of Joining Govt Service");
			return new ModelAndView("redirect:civilianRegularUrl");
		} else {
			joining_date_gov_service_dt = format.parse(joining_date_gov_service);
		}

		if (p_comm.CompareDate(joining_date_gov_service_dt, dob_dt) == 0) {
			Mmap.put("msg", "Date of Joining should Be Greater Than Date of Birth");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isValidDate(designation_date)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Designation");
			return new ModelAndView("redirect:civilianRegularUrl");
		}
		
		if (designation.equals("0")) {
			Mmap.put("msg", "Please Select Designation");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (designation_date == null || designation_date.equals("null") || designation_date.equals("DD/MM/YYYY")
				|| designation_date.equals("")) {
			Mmap.put("msg", "Please Select Date of Designation");
			return new ModelAndView("redirect:civilianRegularUrl");
		} else {
			designation_date_dt = format.parse(designation_date);
		}

		if (p_comm.CompareDate(designation_date_dt, dob_dt) == 0) {
			Mmap.put("msg", "Date of Designation  should Be Greater Than Date of Birth");
			return new ModelAndView("redirect:civilianRegularUrl");
		}
		if (p_comm.CompareDate(designation_date_dt, joining_date_gov_service_dt) == 0) {
			Mmap.put("msg", "Date of Designation  should Be Greater Than Date of Joining");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (obj.getPay_level() == 0) {
			Mmap.put("msg", "Please Select Pay Level");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isValidDate(date_of_tos)) {
			Mmap.put("msg", valid.isValidDateMSG + " of TOS");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (date_of_tos == null || date_of_tos.equals("null") || date_of_tos.equals("DD/MM/YYYY")
				|| date_of_tos.equals("")) {
			Mmap.put("msg", "Please Select Date of TOS");
			return new ModelAndView("redirect:civilianRegularUrl");
		} else {
			date_tos = format.parse(date_of_tos);
		}

		if (!valid.isOnlyAlphabet(father_name)) {
			Mmap.put("msg", " Father Name " + valid.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isvalidLength(father_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Father Name " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isOnlyAlphabet(mother_name)) {
			Mmap.put("msg", " Mother Name " + valid.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (!valid.isvalidLength(mother_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Mother Name " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (religion_hid.toUpperCase().equals("OTHERS") || religion_hid.toUpperCase() == "OTHERS") {
			if (religion_other == null || religion_other.equals("") || religion_other.equals("null")) {
				Mmap.put("msg", "Please Enter Religion Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setReligion_other(religion_other);
			}

			if (!valid.isOnlyAlphabet(religion_other)) {
				Mmap.put("msg", " Religion Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(religion_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Religion Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setReligion_other("");
		}

		if (mother_tongue_hid.toUpperCase().equals("OTHERS") || mother_tongue_hid.toUpperCase() == "OTHERS") {
			if (mother_tongue_other == null || mother_tongue_other.equals("") || mother_tongue_other.equals("null")) {
				Mmap.put("msg", "Please Enter Mother Tongue Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setMother_tongue_other(mother_tongue_other);
			}

			if (!valid.isOnlyAlphabet(mother_tongue_other)) {
				Mmap.put("msg", " Mother Tongue Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(mother_tongue_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Mother Tongue Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setMother_tongue_other("");
		}

		if (valid.isOnlyNumer(aadhar_card) == true) {
			Mmap.put("msg", " Aadhaar Card No  " + valid.isOnlyNumerMSG);
			return new ModelAndView("redirect:civilianRegularUrl");
		}
		if (aadhar_card != "") {
			if (!valid.isValidAadhar(aadhar_card)) {
				Mmap.put("msg", valid.isValidAadharMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		}

		if (pan_no != null && !pan_no.isEmpty()) {

			if (!valid.isValidPan(pan_no)) {
				Mmap.put("msg", valid.isValidPanMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.PanNoLength(pan_no)) {
				Mmap.put("msg", valid.PanNoMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		}

		if (country_original == "0" || country_original == null || country_original.equals("null")) {
			Mmap.put("msg", "Please Select Original Country");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (country_original_hid.toUpperCase().equals("OTHERS") || country_original_hid.toUpperCase() == "OTHERS") {

			if (original_country_other == null || original_country_other.equals("")
					|| original_country_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of Country Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setOriginal_country_other(original_country_other);
			}

			if (!valid.isOnlyAlphabet(original_country_other)) {
				Mmap.put("msg", " Original Country Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(original_country_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Original Country Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setOriginal_country_other("");
		}

		if (state_original == "0" || state_original == null || state_original.equals("null")) {
			Mmap.put("msg", "Please Select Original State");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (state_original_hid.toUpperCase().equals("OTHERS") || state_original_hid.toUpperCase() == "OTHERS") {
			if (original_state_other == null || original_state_other.equals("")
					|| original_state_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of State Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setOriginal_state_other(original_state_other);
			}

			if (!valid.isOnlyAlphabet(original_state_other)) {
				Mmap.put("msg", " Original State Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(original_state_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Original State Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setOriginal_state_other("");
		}

		if (district_original_hid.toUpperCase().equals("OTHERS") || district_original_hid.toUpperCase() == "OTHERS") {
			if (original_district_other == null || original_district_other.equals("")
					|| original_district_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of District Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setOriginal_district_other(original_district_other);
			}

			if (!valid.isOnlyAlphabet(original_district_other)) {
				Mmap.put("msg", " Original District Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(original_district_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Original District Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setOriginal_district_other("");
		}

		if (tehsil_origin_hid.toUpperCase().equals("OTHERS") || tehsil_origin_hid.toUpperCase() == "OTHERS") {
			if (original_tehshil_other == null || original_tehshil_other.equals("")
					|| original_tehshil_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of Tehsil Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setOriginal_tehshil_other(original_tehshil_other);
			}

			if (!valid.isOnlyAlphabet(original_tehshil_other)) {
				Mmap.put("msg", " Original Tehsil Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(original_tehshil_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Original Tehsil Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setOriginal_tehshil_other("");
		}
		// start present
		if (country_present == "0" || country_present == null || country_present.equals("null")) {
			Mmap.put("msg", "Please Select Present Country");
			return new ModelAndView("redirect:civilianRegularUrl");
		}

		if (country_present_hid.toUpperCase().equals("OTHERS") || country_present_hid.toUpperCase() == "OTHERS") {

			if (present_country_other == null || present_country_other.equals("")
					|| present_country_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of Country Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setPresent_country_other(present_country_other);
			}

			if (!valid.isOnlyAlphabet(present_country_other)) {
				Mmap.put("msg", " Present Country Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(present_country_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Present Country Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setPresent_country_other("");
		}

		if (state_present == "0" || state_present == null || state_present.equals("null")) {
			Mmap.put("msg", "Please Select Present State");
			return new ModelAndView("redirect:civilianRegularUrl");
		}
		if (state_present_hid.toUpperCase().equals("OTHERS") || state_present_hid.toUpperCase() == "OTHERS") {
			if (present_state_other == null || present_state_other.equals("") || present_state_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of State Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setPresent_state_other(present_state_other);
			}

			if (!valid.isOnlyAlphabet(present_state_other)) {
				Mmap.put("msg", " Present State Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(present_state_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Present State Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setPresent_state_other("");
		}
		if (district_present_hid.toUpperCase().equals("OTHERS") || district_present_hid.toUpperCase() == "OTHERS") {

			if (present_district_other == null || present_district_other.equals("")
					|| present_district_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of District Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setPresent_district_other(present_district_other);
			}

			if (!valid.isOnlyAlphabet(present_district_other)) {
				Mmap.put("msg", " Present District Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(present_district_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Present District Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setPresent_district_other("");
		}
		if (tehsil_present_hid.toUpperCase().equals("OTHERS") || tehsil_present_hid.toUpperCase() == "OTHERS") {
			if (present_tehshil_other == null || present_tehshil_other.equals("")
					|| present_tehshil_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of Tehsil Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setPresent_tehshil_other(present_tehshil_other);
			}

			if (!valid.isOnlyAlphabet(present_tehshil_other)) {
				Mmap.put("msg", " Present Tehsil Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(present_tehshil_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Present Tehsil Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setPresent_tehshil_other("");
		}
		if (nationality_hid.toUpperCase().equals("OTHERS") || nationality_hid.toUpperCase() == "OTHERS") {
			if (nationality_other == null || nationality_other.equals("") || nationality_other.equals("null")) {
				Mmap.put("msg", "Please Enter Nationality Other");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				obj.setNationality_other(nationality_other);
			}

			if (!valid.isOnlyAlphabet(nationality_other)) {
				Mmap.put("msg", " Nationality Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isvalidLength(nationality_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Nationality Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:civilianRegularUrl");
			}
		} else {
			obj.setNationality_other("");
		}

		if (eff.equals("yes")) {
			if (Integer.parseInt(non_effective) == 0) {
				Mmap.put("msg", "Please Select Non Effective");
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (!valid.isValidDate(date_non_effective)) {
				Mmap.put("msg", valid.isValidDateMSG + "  Non Effective ");
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (date_non_effective == null || date_non_effective.equals("null")
					|| date_non_effective.equals("DD/MM/YYYY") || date_non_effective.equals("")) {
				Mmap.put("msg", "Please Select Date of Non Effective");
				return new ModelAndView("redirect:civilianRegularUrl");
			} else {
				date_non_effective_dt = format.parse(date_non_effective);
			}

			if (p_comm.CompareDate(date_non_effective_dt, dob_dt) == 0) {
				Mmap.put("msg", "Date of Non Effective should Be Greater Than  Date of Birth");
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			if (p_comm.CompareDate(date_non_effective_dt, joining_date_gov_service_dt) == 0) {
				Mmap.put("msg", "Date of Non Effective should Be Greater Than  Date of Joining Govt Service");
				return new ModelAndView("redirect:civilianRegularUrl");
			}
			if (p_comm.CompareDate(date_non_effective_dt, designation_date_dt) == 0) {
				Mmap.put("msg", "Date of Non Effective should Be Greater Than  Date of Designation");
				return new ModelAndView("redirect:civilianRegularUrl");
			}

			obj.setNon_effective(Integer.parseInt(non_effective));
			obj.setDate_non_effective(date_non_effective_dt);
		} else {
			obj.setNon_effective(0);
			obj.setDate_non_effective(null);
		}
		String msg = "";
		
		MultipartFile file = mul.getFile("identity_image");
		if (!file.getOriginalFilename().isEmpty()) {
				
				String upload_imgEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();	
		    	if(!upload_imgEXt.equals("jpg") && !upload_imgEXt.equals("jpeg") && !upload_imgEXt.equals("png")) {
		    		msg= "Only *.jpg, *.jpeg and *.png file extensions allowed";					
		    		Mmap.put("msg", msg);	
		    		return new ModelAndView("redirect:civilianRegularUrl");
				}
		    	
		    	long fileSizeInBytes = file.getSize();
		    	if (fileSizeInBytes > 2 * 1024 * 1024) { // 2MB in bytes
		    	    msg = "Image Size Should Be 2MB Or Less";
		    	    Mmap.put("msg", msg);	
		    	    return new ModelAndView("redirect:civilianRegularUrl");
		    	}
		    
				byte[] bytes = file.getBytes();
				String mnhFilePath = session.getAttribute("psgFilePath").toString() + "/Civilian/RegularIdentity";
				File dir = new File(mnhFilePath);
				if (!dir.exists())
					dir.mkdirs();
				String filename = file.getOriginalFilename();

				int j = filename.lastIndexOf('.');
				if (j >= 0) {
					extension = filename.substring(j + 1);
				}
				java.util.Date date1 = new java.util.Date();
				fname = dir.getAbsolutePath() + File.separator
						+ (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".")
								.toString().replace(" ", "").toString().replace("-", "").toString()
						+ "PSG_Doc." + extension;
				File serverFile = new File(fname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

			}

		obj.setAuthority(authority.trim());
		obj.setPan_no(pan_no.trim());
		obj.setDt_of_authority(dt_authority);
		obj.setSus_no(roleSusNo.trim());
		obj.setFirst_name(first_name.trim());
		obj.setMiddle_name(middle_name.trim());
		obj.setLast_name(last_name.trim());
		obj.setFull_name(name1.trim());
		obj.setDob(dob_dt);
		obj.setGender(Integer.parseInt(gender));
		obj.setClassification_services(classification_services);
		obj.setCiv_group(group);
		obj.setCategory_belongs(Integer.parseInt(category_belongs));
		obj.setService_status(service_status);
		obj.setClassification_trade(Integer.parseInt(classification_trade));
		if (classification_trade_other != "" || classification_trade_other != null) {
			obj.setClassification_trade_other(classification_trade_other);
		}
		obj.setCiv_type(type.trim());
		obj.setCadre(Integer.parseInt(cadre));
		obj.setPost_initialy_appointed(post_initialy_appointed);
		obj.setJoining_date_gov_service(joining_date_gov_service_dt);
		obj.setDesignation(Integer.parseInt(designation));
		obj.setPay_level(Integer.parseInt(pay_level));
		obj.setFather_name(father_name.trim());
		obj.setMother_name(mother_name.trim());
		obj.setState_original(Integer.parseInt(state_original));
		obj.setDistrict_original(Integer.parseInt(district_original));
		obj.setTehsil_origin(Integer.parseInt(tehsil_origin));
		obj.setState_present(Integer.parseInt(state_present));
		obj.setDistrict_present(Integer.parseInt(district_present));
		obj.setTehsil_present(Integer.parseInt(tehsil_present));
		obj.setNationality(Integer.parseInt(nationality));
		obj.setReligion(Integer.parseInt(religion));
		obj.setMother_tongue(Integer.parseInt(mother_tongue));
		obj.setAadhar_card(aadhar_card);
		obj.setService_other(service_other.trim());
		obj.setDesignation_date(designation_date_dt);
		obj.setCivilian_status(status);
		obj.setEmployee_no(emp_no.trim());
		obj.setDate_of_tos(date_tos);
		obj.setQualification(Integer.parseInt(highest_qualification));
		obj.setIdentity_image(fname);
		obj.setMobile_no(mobile_no);

		try {

			Query q0 = sessionhql
					.createQuery("select count(id) from TB_CIVILIAN_DETAILS where employee_no=:employee_no");
			q0.setParameter("employee_no", emp_no);

			Long c = (Long) q0.uniqueResult();
			if (Integer.parseInt(coc_ch_id) == 0) {	
				if (c > 0) {
					Mmap.put("msg", "Employee No Already Exist.");
					return new ModelAndView("redirect:civilianRegularUrl");
				}
				obj.setCreated_by(username);
				obj.setCreated_date(date);
				obj.setStatus(0);
				int id = (int) sessionhql.save(obj);
				msg = Integer.toString(id);
				Mmap.put("msg", "Data Saved Successfully.");
			}
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				tx.rollback();
				msg = "Data Not Saved";
			} catch (RuntimeException rbe) {
				msg = "Data Not Saved";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return new ModelAndView("redirect:civilianRegularUrl");
	}

	@RequestMapping(value = "/editcivilianRegularUrl")
	public ModelAndView editcivilianRegularUrl(@ModelAttribute("id2") String id, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession session) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_regular", roleid);

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		//chnages by jatin bisag
		Mmap.put("roleSusNo", roleSusNo);
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		TB_CIVILIAN_DETAILS authDetails = cdao.getTB_CIVILIAN_DETAILSByid(Integer.parseInt(id));

		Mmap.put("civilian_details_cmd", authDetails);
		Mmap.put("msg", msg);
		Mmap.put("getGenderList", p_comm.getGenderList());
		Mmap.put("getMothertoungeList", p_comm.getMothertoungeList());
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getPayLevelList", p_comm.getPayLevelList());
		Mmap.put("getReligionList", p_comm.getReligionList());
		Mmap.put("getNationalityList", p_comm.getNationalityList());
		Mmap.put("getRegNonEff", p_comm.getRegNonEff());
		Mmap.put("getCadre", p_comm.getCadre());
		Mmap.put("getCategoryList", p_comm.getCategoryList());
		Mmap.put("getClassificationOfTradesList", p_comm.getClassificationOfTradesList());
		Mmap.put("getCountryList", p_comm.getCountryList());
		Mmap.put("getDesignationList", p_comm.getDesignationList());
		Mmap.put("getExservicemenList", p_comm.getExservicemenList());
		Mmap.put("getCivilianTypeList", p_comm.getCivilianTypeList());
		Mmap.put("getServiceStatusList", p_comm.getServiceStatusList());
		Mmap.put("getPersonWithDisabilityList", p_comm.getPersonWithDisabilityList());
		Mmap.put("getClassificationOfServiceList", p_comm.getClassificationOfServiceList());
		Mmap.put("getCivHigh_Qualification", p_comm.getCivHigh_Qualification());
		Mmap.put("filePath",authDetails.getIdentity_image());
		

		return new ModelAndView("editcivilianRegularTiles");
	}

	@RequestMapping(value = "/editcivilianRegularAction", method = RequestMethod.POST)
	public ModelAndView editcivilianRegularAction(@ModelAttribute("civilian_details_cmd") TB_CIVILIAN_DETAILS obj,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,MultipartHttpServletRequest mul)
					throws ParseException, IOException {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();		
		String username = session.getAttribute("username").toString();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String fname = "";
		String extension = "";
		Date date = new Date();
		Date date_authority = null;
		Date date_of_birth = null;
		Date joining_date_gov_service1 = null;
		Date designation_date1 = null;
		Date date_tos = null;

		String authority = request.getParameter("authority");
		String date_of_authority = request.getParameter("doa");
		String unit_sus_no = request.getParameter("unit_sus_no");
		String employee_no = request.getParameter("employee_no");
		String employee_no_old = request.getParameter("employee_no_old");
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String gender_other = request.getParameter("gender_other");
		String classification_services = request.getParameter("classification_services");
		String group = request.getParameter("civ_group");
		String cadre = request.getParameter("cadre");
		String category_belongs = request.getParameter("category_belongs");
		String service_status = request.getParameter("service_status");
		String classification_trade = request.getParameter("classification_trade");
		String classification_of_trade_other = request.getParameter("classification_of_trade_other");
		String civ_type = request.getParameter("civ_type");
		String wes = request.getParameter("wes");
		String whether_ex_serviceman = request.getParameter("whether_ex_serviceman");
		String wpwd = request.getParameter("wpwd");
		String whether_person_disability = request.getParameter("whether_person_disability");
		String post_initialy_appointed = request.getParameter("post_initialy_appointed");
		String joining_date_gov_service = request.getParameter("joining_date_gov_service");
		String pan_no = request.getParameter("pan_no");
		String designation = request.getParameter("designation");
		String designation_date = request.getParameter("designation_date");
		String pay_level = request.getParameter("pay_level");
		String father_name = request.getParameter("father_name");
		String mother_name = request.getParameter("mother_name");
		String state_original = request.getParameter("state_original");
		String country_original = request.getParameter("country_original");
		String district_original = request.getParameter("district_original");
		String tehsil_origin = request.getParameter("tehsil_origin");
		String original_state_other = request.getParameter("original_state_other");
		String original_country_other = request.getParameter("original_country_other");
		String original_dist_other = request.getParameter("original_dist_other");
		String original_tehsil_other = request.getParameter("original_tehsil_other");
		String country_present = request.getParameter("country_present");
		String state_present = request.getParameter("state_present");
		String district_present = request.getParameter("district_present");
		String tehsil_present = request.getParameter("tehsil_present");
		String nationality = request.getParameter("nationality");
		String present_country_other = request.getParameter("present_country_other");
		String present_state_other = request.getParameter("present_state_other");
		String present_dist_other = request.getParameter("present_dist_other");
		String present_tehsil_other = request.getParameter("present_tehsil_other");
		String nationality_other = request.getParameter("nationality_other");
		String religion = request.getParameter("religion");
		String religion_other = request.getParameter("religion_other");
		String mother_tongue = request.getParameter("mother_tongue");
		String mother_tongue_other = request.getParameter("mother_tongue_other");
		String non_effective = request.getParameter("non_effective");
		String date_non_effective = request.getParameter("date_non_effective");
		String eff = request.getParameter("eff");
		String country_original_hid = request.getParameter("country_original_hid");
		String state_original_hid = request.getParameter("original_state_hid");
		String district_original_hid = request.getParameter("district_original_hid");
		String tehsil_origin_hid = request.getParameter("original_tehsil_hid");
		String country_present_hid = request.getParameter("country1_hid");
		String state_present_hid = request.getParameter("present_state_hid");
		String district_present_hid = request.getParameter("present_district_hid");
		String tehsil_present_hid = request.getParameter("tehsil_present_hid");
		String nationality_hid = request.getParameter("nationality_hid");
		String religion_hid = request.getParameter("religion_hid");
		String mother_tongue_hid = request.getParameter("mother_tongue_hid");
		String classification_trade_hid = request.getParameter("classification_of_trade_hid");
		String whether_ex_serviceman_hid = request.getParameter("whether_ex_serviceman_hid");
		String service_other = request.getParameter("service_other");
		String gender_hid = request.getParameter("gender_hid");
		String date_of_tos = request.getParameter("date_of_tos");
		String aadhar_card = "";
		String highest_qualification = request.getParameter("high_qual");
		String mobile_no = request.getParameter("mobile_no");
		
		int id = 0;
		if (Integer.parseInt(request.getParameter("id")) != 0) {
			id = Integer.parseInt(request.getParameter("id"));
		}

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		if (!roleAccess.equals("Unit")) {
			if (unit_sus_no.equals("")) {
				model.put("msg", "Please Enter Unit Sus No.");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			} else {
				roleSusNo = unit_sus_no;
			}
			if (!valid.SusNoLength(unit_sus_no)) {
				model.put("msg", valid.SusNoMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
		}

		try {
			TB_CIVILIAN_DETAILS obj2 = (TB_CIVILIAN_DETAILS) session1.get(TB_CIVILIAN_DETAILS.class, obj.getId());
			session1.evict(obj2);

			if (!request.getParameter("pers_aadhar_no11").equals("")
					& !request.getParameter("pers_aadhar_no2").equals("")
					|| !request.getParameter("pers_aadhar_no3").equals("")) {
				String pers_aadhar_no11 = request.getParameter("pers_aadhar_no11");
				String pers_aadhar_no2 = request.getParameter("pers_aadhar_no2");
				String pers_aadhar_no3 = request.getParameter("pers_aadhar_no3");
				aadhar_card = pers_aadhar_no11 + pers_aadhar_no2 + pers_aadhar_no3;
			}
			
			if (authority == null || authority.equals("")) {
				model.put("msg", "Please Enter Authority");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
			if (!valid.isValidAuth(authority)) {
				model.put("msg", valid.isValidAuthMSG + "Authority");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isvalidLength(authority, valid.authorityMax, valid.authorityMin)) {
				model.put("msg", "Authority " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
			
			if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
				model.put("msg", "Please Select Date of Authority");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!date_of_authority.equals("") || date_of_authority != "") {

				if (!valid.isValidDate(date_of_authority)) {
					model.put("msg", valid.isValidDateMSG + " of Authority");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					date_authority = format.parse(date_of_authority);
				}
			}

			if (unit_sus_no != "") {
				if (!valid.SusNoLength(unit_sus_no)) {
					model.put("msg", valid.SusNoMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			}

			if (employee_no == null || employee_no.trim().equals("")) {
				model.put("msg", "Please Enter Employee No.");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isValidAuth(employee_no)) {
				model.put("msg", valid.isValidAuthMSG + "Employee No");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isvalidLength(employee_no, valid.nameMax, valid.nameMin)) {
				model.put("msg", "Employee No " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
			
			
			if(!employee_no.equals(employee_no_old)) {
				Query q0 = sessionhql
						.createQuery("select count(id) from TB_CIVILIAN_DETAILS where employee_no=:employee_no");
				q0.setParameter("employee_no", employee_no);
				Long c = (Long) q0.uniqueResult();
					if (c > 0) {
						model.put("msg", "Employee No Already Exist.");	
						model.put("id2", id);
						return new ModelAndView("redirect:editcivilianRegularUrl");
					}
				}

			
			if (mobile_no == null || mobile_no.trim().equals("")) {
				model.put("msg", "Please Enter Mobile No.");
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (first_name == null || first_name.trim().equals("") || first_name.equals(null)) {
				model.put("msg", "Please Enter First Name");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isOnlyAlphabet(first_name)) {
				model.put("msg", " First Name " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isvalidLength(first_name, valid.nameMax, valid.nameMin)) {
				model.put("msg", "First Name " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isOnlyAlphabet(middle_name)) {
				model.put("msg", " Middle Name " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isvalidLength(middle_name, valid.nameMax, valid.nameMin)) {
				model.put("msg", "Middle Name " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isOnlyAlphabet(last_name)) {
				model.put("msg", " Last Name " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isvalidLength(last_name, valid.nameMax, valid.nameMin)) {
				model.put("msg", "Last Name " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isValidDate(dob)) {
				model.put("msg", valid.isValidDateMSG + " of Birth");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (dob == null || dob.equals("null") || dob.equals("DD/MM/YYYY") || dob.equals("")) {
				model.put("msg", "Please Select Date of Birth");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			} else {
				date_of_birth = format.parse(dob);
			}

			if (dob == null || dob.equals("null") || dob.equals("DD/MM/YYYY") || dob.equals("")) {
				model.put("msg", "Please Select Date of Birth");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			
			if(eff.equals("no")) {
				if (p_comm.calculate_age(format.parse(dob), date) < 18
						|| p_comm.calculate_age(format.parse(dob), date) >= 60) {
					model.put("msg", "Please enter age above 18 Years and below 60 Years");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			}
			
			if (obj.getGender() == 0) {
				model.put("msg", "Please Select Gender");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (gender_hid.toUpperCase().equals("OTHER") || gender_hid.toUpperCase() == "OTHER") {
				if (gender_other == null || gender_other.trim().equals("") || gender_other.equals("null")) {
					model.put("msg", "Please Enter Gender Other");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setGender_other(gender_other);
				}

				if (!valid.isOnlyAlphabet(gender_other)) {
					model.put("msg", " Gender Other " + valid.isOnlyAlphabetMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isvalidLength(gender_other, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Gender Other " + valid.isValidLengthMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			} else {
				obj2.setGender_other("");
			}

			if (classification_services == null) {
				model.put("msg", "Please Select Classification of Services");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
			if (group == null) {
				model.put("msg", "Please Select Group");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (obj.getCategory_belongs() == 0) {
				model.put("msg", "Please Select Category");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
			if (service_status == null) {
				model.put("msg", "Please Select Service Status");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
			if (classification_services == "1") {
				if (obj.getClassification_trade() == 0) {
					model.put("msg", "Please Select Classification of Trade");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			}

			if (classification_trade_hid.toUpperCase().equals("OTHERS")
					|| classification_trade_hid.toUpperCase() == "OTHERS") {
				if (classification_of_trade_other == null || classification_of_trade_other.equals("")
						|| classification_of_trade_other.equals("null")) {
					model.put("msg", "Please Enter Classification of Trade Other");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setClassification_trade_other(classification_of_trade_other);
				}

				if (!valid.isOnlyAlphabet(classification_of_trade_other)) {
					model.put("msg", " Classification of Trade Other " + valid.isOnlyAlphabetMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isvalidLength(classification_of_trade_other, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Classification of Trade Other " + valid.isValidLengthMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			} else {
				obj2.setClassification_trade_other("");
			}

			if (civ_type == null) {
				model.put("msg", "Please Select Type");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (wes.equals("yes")) {
				if (whether_ex_serviceman == "0") {
					model.put("msg", "Please Select Whether Ex-Serviceman");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setWhether_ex_serviceman(whether_ex_serviceman);
				}
				if (whether_ex_serviceman_hid.toUpperCase().equals("OTHER")
						|| whether_ex_serviceman_hid.toUpperCase() == "OTHER") {
					if (service_other == null || service_other.equals("") || service_other.equals("null")) {
						model.put("msg", "Please Enter Service Other");
						model.put("id2", id);
						return new ModelAndView("redirect:editcivilianRegularUrl");
					} else {
						obj2.setService_other(service_other);
					}

					if (!valid.isOnlyAlphabet(service_other)) {
						model.put("msg", " Service Other " + valid.isOnlyAlphabetMSG);
						model.put("id2", id);
						return new ModelAndView("redirect:editcivilianRegularUrl");
					}

					if (!valid.isvalidLength(service_other, valid.nameMax, valid.nameMin)) {
						model.put("msg", "Service Other " + valid.isValidLengthMSG);
						model.put("id2", id);
						return new ModelAndView("redirect:editcivilianRegularUrl");
					}
				} else {
					obj2.setService_other("");
				}
			} else {
				obj2.setWhether_ex_serviceman("");
			}
			if (wpwd.equals("yes")) {
				if (whether_person_disability == "0") {
					model.put("msg", "Please Select Whether Person With Disability");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setWhether_person_disability(whether_person_disability);
				}
			} else {
				obj2.setWhether_person_disability("");
			}

			if (!valid.isValidDate(joining_date_gov_service)) {
				model.put("msg", valid.isValidDateMSG + " of Joining Govt Service");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (joining_date_gov_service == null || joining_date_gov_service.equals("null")
					|| joining_date_gov_service.equals("DD/MM/YYYY") || joining_date_gov_service.equals("")) {
				model.put("msg", "Please Select Date of Joining Govt Service");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			} else {
				joining_date_gov_service1 = format.parse(joining_date_gov_service);
			}

			if (p_comm.CompareDate(joining_date_gov_service1, date_of_birth) == 0) {
				model.put("msg", "Date of Joining should Be Greater Than Date of Birth");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isValidDate(designation_date)) {
				model.put("msg", valid.isValidDateMSG + " of Designation");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
			if (designation.equals("0")) {
				model.put("msg", "Please Select Designation");
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
			if (designation_date == null || designation_date.equals("null") || designation_date.equals("DD/MM/YYYY")
					|| designation_date.equals("")) {
				model.put("msg", "Please Select Date of Designation");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			} else {
				designation_date1 = format.parse(designation_date);
			}

			if (p_comm.CompareDate(designation_date1, date_of_birth) == 0) {
				model.put("msg", "Date of Designation  should Be Greater Than Date of Birth");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
			if (p_comm.CompareDate(designation_date1, joining_date_gov_service1) == 0) {
				model.put("msg", "Date of Designation  should Be Greater Than Date of Joining");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (obj.getPay_level() == 0) {
				model.put("msg", "Please Select Pay Level");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isValidDate(date_of_tos)) {
				model.put("msg", valid.isValidDateMSG + " of TOS");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (date_of_tos == null || date_of_tos.equals("null") || date_of_tos.equals("DD/MM/YYYY")
					|| date_of_tos.equals("")) {
				model.put("msg", "Please Select Date of TOS");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			} else {
				date_tos = format.parse(date_of_tos);
			}

			if (p_comm.CompareDate(date_tos, date_of_birth) == 0) {
				model.put("msg", "Date of TOS  should Be Greater Than Date of Birth");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
			if (p_comm.CompareDate(date_tos, joining_date_gov_service1) == 0) {
				model.put("msg", "Date of TOS  should Be Greater Than Date of Joining");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isOnlyAlphabet(father_name)) {
				model.put("msg", " Father Name " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isvalidLength(father_name, valid.nameMax, valid.nameMin)) {
				model.put("msg", "Father Name " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isOnlyAlphabet(mother_name)) {
				model.put("msg", " Mother Name " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (!valid.isvalidLength(mother_name, valid.nameMax, valid.nameMin)) {
				model.put("msg", "Mother Name " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (religion_hid.toUpperCase().equals("OTHERS") || religion_hid.toUpperCase() == "OTHERS") {
				if (religion_other == null || religion_other.equals("") || religion_other.equals("null")) {
					model.put("msg", "Please Enter Religion Other");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setReligion_other(religion_other);
				}

				if (!valid.isOnlyAlphabet(religion_other)) {
					model.put("msg", " Religion Other " + valid.isOnlyAlphabetMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isvalidLength(religion_other, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Religion Other " + valid.isValidLengthMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			} else {
				obj2.setReligion_other("");
			}

			if (mother_tongue_hid.toUpperCase().equals("OTHERS") || mother_tongue_hid.toUpperCase() == "OTHERS") {
				if (mother_tongue_other == null || mother_tongue_other.equals("")
						|| mother_tongue_other.equals("null")) {
					model.put("msg", "Please Enter Mother Tongue Other");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setMother_tongue_other(mother_tongue_other);
				}

				if (!valid.isOnlyAlphabet(mother_tongue_other)) {
					model.put("msg", " Mother Tongue Other " + valid.isOnlyAlphabetMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isvalidLength(mother_tongue_other, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Mother Tongue Other " + valid.isValidLengthMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			} else {
				obj2.setMother_tongue_other("");
			}

			if (valid.isOnlyNumer(aadhar_card) == true) {
				model.put("msg", " Aadhaar Card No  " + valid.isOnlyNumerMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
			if (aadhar_card != "") {
				if (!valid.isValidAadhar(aadhar_card)) {
					model.put("msg", valid.isValidAadharMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			}

			if (pan_no != null && !pan_no.isEmpty()) {
				if (!valid.isValidPan(pan_no)) {
					model.put("msg", valid.isValidPanMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.PanNoLength(pan_no)) {
					model.put("msg", valid.PanNoMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			}

			if (country_original == "0" || country_original == null || country_original.equals("null")) {
				model.put("msg", "Please Select Original Country");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
			if (country_original_hid.toUpperCase().equals("OTHERS") || country_original_hid.toUpperCase() == "OTHERS") {

				if (original_country_other == null || original_country_other.equals("")
						|| original_country_other.equals("null")) {
					model.put("msg", "Please Enter The Original Domicile of Country Other");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setOriginal_country_other(original_country_other.trim());
				}

				if (!valid.isOnlyAlphabet(original_country_other)) {
					model.put("msg", " Original Country Other " + valid.isOnlyAlphabetMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isvalidLength(original_country_other, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Original Country Other " + valid.isValidLengthMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			} else {
				obj2.setOriginal_country_other("");
			}

			if (state_original == "0" || state_original == null || state_original.equals("null")) {
				model.put("msg", "Please Select Original State");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (state_original_hid.toUpperCase().equals("OTHERS") || state_original_hid.toUpperCase() == "OTHERS") {
				if (original_state_other == null || original_state_other.equals("")
						|| original_state_other.equals("null")) {
					model.put("msg", "Please Enter The Original Domicile of State Other");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setOriginal_state_other(original_state_other.trim());
				}

				if (!valid.isOnlyAlphabet(original_state_other)) {
					model.put("msg", " Original State Other " + valid.isOnlyAlphabetMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isvalidLength(original_state_other, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Original State Other " + valid.isValidLengthMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			} else {
				obj2.setOriginal_state_other("");
			}

			if (district_original_hid.toUpperCase().equals("OTHERS")
					|| district_original_hid.toUpperCase() == "OTHERS") {
				if (original_dist_other == null || original_dist_other.equals("")
						|| original_dist_other.equals("null")) {
					model.put("msg", "Please Enter The Original Domicile of District Other");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setOriginal_district_other(original_dist_other.trim());
				}

				if (!valid.isOnlyAlphabet(original_dist_other)) {
					model.put("msg", " Original District Other " + valid.isOnlyAlphabetMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isvalidLength(original_dist_other, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Original District Other " + valid.isValidLengthMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			} else {
				obj2.setOriginal_district_other("");
			}

			if (tehsil_origin_hid.toUpperCase().equals("OTHERS") || tehsil_origin_hid.toUpperCase() == "OTHERS") {
				if (original_tehsil_other == null || original_tehsil_other.equals("")
						|| original_tehsil_other.equals("null")) {
					model.put("msg", "Please Enter The Original Domicile of Tehsil Other");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setOriginal_tehshil_other(original_tehsil_other.trim());
				}

				if (!valid.isOnlyAlphabet(original_tehsil_other)) {
					model.put("msg", " Original Tehsil Other " + valid.isOnlyAlphabetMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isvalidLength(original_tehsil_other, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Original Tehsil Other " + valid.isValidLengthMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			} else {
				obj2.setOriginal_tehshil_other("");
			}
			// start present
			if (country_present == "0" || country_original == null || country_original.equals("null")) {
				model.put("msg", "Please Select Present Country");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}

			if (country_present_hid.toUpperCase().equals("OTHERS") || country_present_hid.toUpperCase() == "OTHERS") {

				if (present_country_other == null || present_country_other.equals("")
						|| present_country_other.equals("null")) {
					model.put("msg", "Please Enter The Present Domicile of Country Other");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setPresent_country_other(present_country_other.trim());
				}

				if (!valid.isOnlyAlphabet(present_country_other)) {
					model.put("msg", " Present Country Other " + valid.isOnlyAlphabetMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isvalidLength(present_country_other, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Present Country Other " + valid.isValidLengthMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			} else {
				obj2.setPresent_country_other("");
			}

			if (state_present == "0" || state_present == null || state_present.equals("null")) {
				model.put("msg", "Please Select Present State");
				model.put("id2", id);
				return new ModelAndView("redirect:editcivilianRegularUrl");
			}
			if (state_present_hid.toUpperCase().equals("OTHERS") || state_present_hid.toUpperCase() == "OTHERS") {
				if (present_state_other == null || present_state_other.equals("")
						|| present_state_other.equals("null")) {
					model.put("msg", "Please Enter The Present Domicile of State Other");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setPresent_state_other(present_state_other.trim());
				}

				if (!valid.isOnlyAlphabet(present_state_other)) {
					model.put("msg", " Present State Other " + valid.isOnlyAlphabetMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isvalidLength(present_state_other, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Present State Other " + valid.isValidLengthMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			} else {
				obj2.setPresent_state_other("");
			}
			if (district_present_hid.toUpperCase().equals("OTHERS") || district_present_hid.toUpperCase() == "OTHERS") {
				if (present_dist_other == null || present_dist_other.equals("") || present_dist_other.equals("null")) {
					model.put("msg", "Please Enter The Present Domicile of District Other");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setPresent_district_other(present_dist_other.trim());
				}

				if (!valid.isOnlyAlphabet(present_dist_other)) {
					model.put("msg", " Present District Other " + valid.isOnlyAlphabetMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isvalidLength(present_dist_other, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Present District Other " + valid.isValidLengthMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			} else {
				obj2.setPresent_district_other("");
			}
			if (tehsil_present_hid.toUpperCase().equals("OTHERS") || tehsil_present_hid.toUpperCase() == "OTHERS") {
				if (present_tehsil_other == null || present_tehsil_other.equals("")
						|| present_tehsil_other.equals("null")) {
					model.put("msg", "Please Enter The Present Domicile of Tehsil Other");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setPresent_tehshil_other(present_tehsil_other.trim());
				}

				if (!valid.isOnlyAlphabet(present_tehsil_other)) {
					model.put("msg", " Present Tehsil Other " + valid.isOnlyAlphabetMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isvalidLength(present_tehsil_other, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Present Tehsil Other " + valid.isValidLengthMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			} else {
				obj2.setPresent_tehshil_other("");
			}
			if (nationality_hid.toUpperCase().equals("OTHERS") || nationality_hid.toUpperCase() == "OTHERS") {
				if (nationality_other == null || nationality_other.equals("") || nationality_other.equals("null")) {
					model.put("msg", "Please Enter Nationality Other");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					obj2.setNationality_other(nationality_other.trim());
				}

				if (!valid.isOnlyAlphabet(nationality_other)) {
					model.put("msg", " Nationality Other " + valid.isOnlyAlphabetMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isvalidLength(nationality_other, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Nationality Other " + valid.isValidLengthMSG);
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
			} else {
				obj2.setNationality_other("");
			}

			Date date_non_effective_dt = null;

			if (eff.equals("yes")) {

				if (Integer.parseInt(non_effective) == 0) {
					model.put("msg", "Please Select Non Effective");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (!valid.isValidDate(date_non_effective)) {
					model.put("msg", valid.isValidDateMSG + "  Non Effective ");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (date_non_effective == null || date_non_effective.equals("null")
						|| date_non_effective.equals("DD/MM/YYYY") || date_non_effective.equals("")) {
					model.put("msg", "Please Select Date of Non Effective");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				} else {
					date_non_effective_dt = format.parse(date_non_effective);
					obj2.setDate_non_effective(date_non_effective_dt);
				}

				if (p_comm.CompareDate(date_non_effective_dt, date_of_birth) == 0) {
					model.put("msg", "Date of Non Effective should Be Greater Than  Date of Birth");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				if (p_comm.CompareDate(date_non_effective_dt, joining_date_gov_service1) == 0) {
					model.put("msg", "Date of Non Effective should Be Greater Than  Date of Joining Govt Service");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}
				if (p_comm.CompareDate(date_non_effective_dt, designation_date1) == 0) {
					model.put("msg", "Date of Non Effective should Be Greater Than  Date of Designation");
					model.put("id2", id);
					return new ModelAndView("redirect:editcivilianRegularUrl");
				}

				obj2.setNon_effective(Integer.parseInt(non_effective));

			} else {
				obj2.setNon_effective(0);
				obj2.setDate_non_effective(null);
			}
			if (date_of_authority != "") {
				date_authority = format.parse(date_of_authority);
			}
			
			MultipartFile file = mul.getFile("identity_image");
			if (!file.getOriginalFilename().isEmpty()) {
					
					String upload_imgEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();	
			    	if(!upload_imgEXt.equals("jpg") && !upload_imgEXt.equals("jpeg") && !upload_imgEXt.equals("png")) {
			    		msg= "Only *.jpg, *.jpeg and *.png file extensions allowed";					
			    		model.put("msg", msg);	
			    		return new ModelAndView("redirect:editcivilianRegularUrl");
			    	
					}
			    	
			    	long fileSizeInBytes = file.getSize();
			    	if (fileSizeInBytes > 2 * 1024 * 1024) { // 2MB in bytes
			    	    msg = "Image Size Should Be 2MB Or Less";
			    	    model.put("msg", msg);
			    	    return new ModelAndView("redirect:editcivilianRegularUrl");
			    	}
			    
					byte[] bytes = file.getBytes();
					String mnhFilePath = session.getAttribute("psgFilePath").toString() + "/Civilian/RegularIdentity";
					File dir = new File(mnhFilePath);
					if (!dir.exists())
						dir.mkdirs();
					String filename = file.getOriginalFilename();

					int j = filename.lastIndexOf('.');
					if (j >= 0) {
						extension = filename.substring(j + 1);
					}
					java.util.Date date1 = new java.util.Date();
					fname = dir.getAbsolutePath() + File.separator
							+ (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".")
									.toString().replace(" ", "").toString().replace("-", "").toString()
							+ "PSG_Doc." + extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

				}
			obj2.setAuthority(authority.trim());
			obj2.setDt_of_authority(date_authority);
			obj2.setSus_no(roleSusNo.trim());
			obj2.setEmployee_no(employee_no.trim());
			obj2.setFirst_name(first_name.trim());
			obj2.setMiddle_name(middle_name.trim());
			obj2.setLast_name(last_name.trim());
			obj2.setDob(date_of_birth);
			obj2.setGender(Integer.parseInt(gender));
			obj2.setClassification_services(classification_services);
			obj2.setCiv_group(group);
			obj2.setCadre(Integer.parseInt(cadre));
			obj2.setCategory_belongs(Integer.parseInt(category_belongs));
			obj2.setService_status(service_status);
			obj2.setClassification_trade(Integer.parseInt(classification_trade));
			if (classification_of_trade_other != "0") {
				obj2.setClassification_trade_other(classification_of_trade_other);
			}
			obj2.setCiv_type(civ_type);
			obj2.setPan_no(pan_no.trim());
			obj2.setPost_initialy_appointed(post_initialy_appointed);
			obj2.setJoining_date_gov_service(joining_date_gov_service1);
			obj2.setDesignation(Integer.parseInt(designation));
			obj2.setDesignation_date(designation_date1);
			obj2.setPay_level(Integer.parseInt(pay_level));
			obj2.setFather_name(father_name.trim());
			obj2.setMother_name(mother_name.trim());
			obj2.setReligion(Integer.parseInt(religion));
			obj2.setMother_tongue(Integer.parseInt(mother_tongue));
			obj2.setAadhar_card(aadhar_card);
			obj2.setCountry_original(Integer.parseInt(country_original));
			obj2.setState_original(Integer.parseInt(state_original));
			obj2.setDistrict_original(Integer.parseInt(district_original));
			obj2.setTehsil_origin(Integer.parseInt(tehsil_origin));
			obj2.setCountry_present(Integer.parseInt(country_present));
			obj2.setState_present(Integer.parseInt(state_present));
			obj2.setDistrict_present(Integer.parseInt(district_present));
			obj2.setTehsil_present(Integer.parseInt(tehsil_present));
			obj2.setNationality(Integer.parseInt(nationality));
			obj2.setModified_by(username);
			obj2.setModified_date(date);
			obj2.setStatus(0);
			obj2.setDate_of_tos(date_tos);
			obj2.setMobile_no(mobile_no);
			if (StringUtils.hasText(fname)) { // this check notnull,notempty,no space
			    obj2.setIdentity_image(fname);
			}
			obj2.setQualification(Integer.parseInt(highest_qualification));
			session1.update(obj2);
			tx.commit();

			model.put("msg", "Data Updated Successfully.");

		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				rbe.printStackTrace();
				model.put("msg", "Couldn t roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return new ModelAndView("redirect:Search_civilian_regular");
	}

	@RequestMapping(value = "/admin/deleteEduUrl_regular", method = RequestMethod.POST)
	public ModelAndView deleteEduUrl(HttpServletRequest request, HttpSession session, ModelMap model) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		try {

			int deleteid = Integer.parseInt(request.getParameter("deleteid"));
			Session session1 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			String hql = "delete from TB_CIVILIAN_DETAILS where id=:id";
			Query q = session1.createQuery(hql).setInteger("id", deleteid);
			int rowCount = q.executeUpdate();
			tx.commit();
			session1.close();

			if (rowCount > 0) {
				model.put("msg", "Deleted Successfully");
			} else {
				model.put("msg", "Deleted Not Successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:Search_civilian_regular");
	}

	@RequestMapping(value = "/admin/Report_civilian_regular", method = RequestMethod.GET)
	public ModelAndView Report_civilian_regular(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		return new ModelAndView("Civilian_Regular_ReportTiles");
	}

	// ===================================view===========================================//

	@RequestMapping(value = "/viewcivilianRegularUrl")
	public ModelAndView viewcivilianRegularUrl(@ModelAttribute("id3") String id, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "unit_sus_no2", required = false) String unit_sus_no,
			@RequestParam(value = "unit_name2", required = false) String unit_name,
			@RequestParam(value = "first_name2", required = false) String first_name,HttpServletRequest request,
			@RequestParam(value = "status2", required = false) String status, HttpSession session) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}


		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Mmap.put("roleSusNo", roleSusNo);
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));

		TB_CIVILIAN_DETAILS authDetails = cdao.getTB_CIVILIAN_DETAILSByid(Integer.parseInt(id));
		List<Map<String, Object>> list = cdao.getAllAppDataForviewregular(Integer.parseInt(id));
		Mmap.put("listfull", list);
		Mmap.put("civilian_details_cmd", authDetails);
		Mmap.put("msg", msg);
		Mmap.put("getGenderList", p_comm.getGenderList());
		Mmap.put("getMothertoungeList", p_comm.getMothertoungeList());
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getPayLevelList", p_comm.getPayLevelList());
		Mmap.put("getReligionList", p_comm.getReligionList());
		Mmap.put("getNationalityList", p_comm.getNationalityList());
		Mmap.put("getRegNonEff", p_comm.getRegNonEff());
		Mmap.put("getCadre", p_comm.getCadre());
		Mmap.put("getCategoryList", p_comm.getCategoryList());
		Mmap.put("getClassificationOfTradesList", p_comm.getClassificationOfTradesList());
		Mmap.put("getMedCountryName", p_comm.getMedCountryName("", session));
		Mmap.put("getDesignationList", p_comm.getDesignationList());
		Mmap.put("getExservicemenList", p_comm.getExservicemenList());
		Mmap.put("getCivilianTypeList", p_comm.getCivilianTypeList());
		Mmap.put("getServiceStatusList", p_comm.getServiceStatusList());
		Mmap.put("getPersonWithDisabilityList", p_comm.getPersonWithDisabilityList());
		Mmap.put("getClassificationOfServiceList", p_comm.getClassificationOfServiceList());
		Mmap.put("getCadre", p_comm.getCadre());
		Mmap.put("unit_sus_no2", unit_sus_no);
		Mmap.put("unit_name2", unit_name);
		Mmap.put("first_name2", first_name);
		Mmap.put("status2", status);
		Mmap.put("getCivHigh_Qualification", p_comm.getCivHigh_Qualification());

		return new ModelAndView("viewcivilianRegularTiles");
	}

	@RequestMapping(value = "/viewcivilianRegularAction", method = RequestMethod.POST)
	public ModelAndView viewcivilianRegularAction(@ModelAttribute("civilian_details_cmd") TB_CIVILIAN_DETAILS c,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}


		String username = session.getAttribute("username").toString();
		Date date = new Date();

		int id = c.getId();
		int main_id = c.getMain_id();
		String civi_status = c.getCivilian_status();

		TB_CIVILIAN_DETAILS obj = cdao.getTB_CIVILIAN_DETAILSByid(id);

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();

		try {

			TB_CIVILIAN_MAIN m = new TB_CIVILIAN_MAIN();

			if (main_id == 0) {

				m.setAuthority(obj.getAuthority());
				m.setDt_of_authority(obj.getDt_of_authority());
				m.setSus_no(obj.getSus_no());
				m.setFull_name(obj.getFull_name());
				m.setFirst_name(obj.getFirst_name());
				m.setMiddle_name(obj.getMiddle_name());
				m.setLast_name(obj.getLast_name());
				m.setDob(obj.getDob());
				m.setGender(obj.getGender());
				if (obj.getGender_other() != "") {
					m.setGender_other(obj.getGender_other());
				}

				m.setClassification_services(obj.getClassification_services());
				m.setCiv_group(obj.getCiv_group());
				m.setCadre(obj.getCadre());
				m.setCategory_belongs(obj.getCategory_belongs());
				m.setService_status(obj.getService_status());
				m.setClassification_trade(obj.getClassification_trade());
				m.setCiv_type(obj.getCiv_type());
				if (obj.getWhether_ex_serviceman() != "0") {
					m.setWhether_ex_serviceman(obj.getWhether_ex_serviceman());
				}
				if (obj.getWhether_person_disability() != "0") {
					m.setWhether_person_disability(obj.getWhether_person_disability());
				}
				m.setPost_initialy_appointed(obj.getPost_initialy_appointed());
				m.setJoining_date_gov_service(obj.getJoining_date_gov_service());
				m.setDesignation(obj.getDesignation());
				m.setDesignation_date(obj.getDesignation_date());
				m.setPay_level(obj.getPay_level());
				m.setFather_name(obj.getFather_name());
				m.setMother_name(obj.getMother_name());
				m.setReligion(obj.getReligion());
				m.setMother_tongue(obj.getMother_tongue());
				m.setAadhar_card(obj.getAadhar_card());
				m.setPan_no(obj.getPan_no());
				m.setCountry_original(obj.getCountry_original());
				m.setState_original(obj.getState_original());
				m.setDistrict_original(obj.getDistrict_original());
				m.setTehsil_origin(obj.getTehsil_origin());
				if (obj.getOriginal_country_other() != "") {
					m.setOriginal_country_other(obj.getOriginal_country_other());
				}
				if (obj.getOriginal_state_other() != "") {
					m.setOriginal_state_other(obj.getOriginal_state_other());
				}

				if (obj.getOriginal_district_other() != "") {
					m.setOriginal_district_other(obj.getOriginal_district_other());
				}

				if (obj.getOriginal_tehshil_other() != "") {
					m.setOriginal_tehshil_other(obj.getOriginal_tehshil_other());
				}

				m.setCountry_present(obj.getCountry_present());
				m.setState_present(obj.getState_present());
				m.setDistrict_present(obj.getDistrict_present());
				m.setTehsil_present(obj.getTehsil_present());

				if (obj.getPresent_country_other() != "") {
					m.setPresent_country_other(obj.getPresent_country_other());
				}
				if (obj.getPresent_state_other() != "") {
					m.setPresent_state_other(obj.getPresent_state_other());
				}

				if (obj.getPresent_district_other() != "") {
					m.setPresent_district_other(obj.getPresent_district_other());
				}

				if (obj.getPresent_tehshil_other() != "") {
					m.setPresent_tehshil_other(obj.getPresent_tehshil_other());
				}

				if (obj.getService_other() != "") {
					m.setService_other(obj.getService_other());
				}

				m.setNationality(obj.getNationality());
				if (obj.getNon_effective() != 0) {
					m.setNon_effective(obj.getNon_effective());
					m.setDate_non_effective(obj.getDate_non_effective());
					m.setStatus(4);
				} else {
					m.setStatus(1);
				}
				m.setEmployee_no(obj.getEmployee_no());
				m.setCivilian_status(civi_status);
				m.setCreated_by(username);
				m.setCreated_date(date);
				m.setCancel_by(obj.getCancel_by());
				m.setCancel_date(obj.getCancel_date());
				m.setCancel_status(obj.getCancel_status());
				m.setDate_of_tos(obj.getDate_of_tos());
				session1.save(m);

				TB_POSTING_IN_OUT_CIVILIAN p = new TB_POSTING_IN_OUT_CIVILIAN();

				p.setDt_of_tos(obj.getDate_of_tos());
				p.setDt_of_sos(obj.getJoining_date_gov_service());
				p.setTo_sus_no(obj.getSus_no());
				p.setCiv_id(m.getId());
				p.setRank(obj.getDesignation());
				p.setTo_sus_no(obj.getSus_no());
				p.setStatus(1);
				p.setNotification_status(0);
				p.setCreated_by(username);
				p.setCreated_date(date);
				p.setIn_auth(obj.getAuthority());
				p.setIn_auth_dt(obj.getDt_of_authority());

				ArrayList<ArrayList<String>> orbatlist = regdao.getCivcommand(obj.getSus_no());
				ArrayList<ArrayList<String>> Location_Trnlist = regdao.getLocation_Trn(obj.getSus_no());

				if (orbatlist.size() > 0) {
					p.setCmd_sus(orbatlist.get(0).get(1));
					p.setCorps_sus(orbatlist.get(0).get(2));
					p.setDiv_sus(orbatlist.get(0).get(3));
					p.setBde_sus(orbatlist.get(0).get(4));
				}
				if (Location_Trnlist.size() > 0) {
					p.setLocation(Location_Trnlist.get(0).get(0));
					p.setTrn_type(Location_Trnlist.get(0).get(1));
				}
				session1.save(p);

				int m_id = m.getId();

				Query query;
				String hql = "update TB_CIVILIAN_DETAILS set modified_by=:modified_by ,modified_date=:modified_date,main_id=:main_id, "
						+ " status=:status" + " where  id=:id and status = '0'";

				if (obj.getNon_effective() != 0) {
					query = session1.createQuery(hql).setParameter("status", 4).setString("modified_by", username)
							.setDate("modified_date", new Date()).setInteger("main_id", m_id).setInteger("id", id);
				} else {
					query = session1.createQuery(hql).setParameter("status", 1).setString("modified_by", username)
							.setDate("modified_date", new Date()).setInteger("main_id", m_id).setInteger("id", id);
				}

				msg = query.executeUpdate() > 0 ? "1" : "0";
			} else {

				TB_CIVILIAN_MAIN obj2 = (TB_CIVILIAN_MAIN) session1.get(TB_CIVILIAN_MAIN.class, c.getMain_id());
				obj2.setAuthority(obj.getAuthority());
				obj2.setDt_of_authority(obj.getDt_of_authority());
				obj2.setSus_no(obj.getSus_no());
				obj2.setFirst_name(obj.getFirst_name());
				obj2.setMiddle_name(obj.getMiddle_name());
				obj2.setLast_name(obj.getLast_name());
				obj2.setFull_name(obj.getFull_name());
				obj2.setDob(obj.getDob());
				obj2.setGender(obj.getGender());

				if (obj.getGender_other() != "") {
					obj2.setGender_other(obj.getGender_other());
				}
				obj2.setClassification_services(obj.getClassification_services());
				obj2.setCiv_group(obj.getCiv_group());
				obj2.setCadre(obj.getCadre());
				obj2.setCategory_belongs(obj.getCategory_belongs());
				obj2.setService_status(obj.getService_status());
				obj2.setClassification_trade(obj.getClassification_trade());
				obj2.setCiv_type(obj.getCiv_type());
				if (obj.getWhether_ex_serviceman() != "0") {
					obj2.setWhether_ex_serviceman(obj.getWhether_ex_serviceman());
					if (obj.getService_other() != "") {
						obj2.setService_other(obj.getService_other());
					}
				}

				if (obj.getWhether_person_disability() != "0") {
					obj2.setWhether_person_disability(obj.getWhether_person_disability());
				}

				obj2.setPost_initialy_appointed(obj.getPost_initialy_appointed());
				obj2.setJoining_date_gov_service(obj.getJoining_date_gov_service());
				obj2.setDesignation(obj.getDesignation());
				obj2.setDesignation_date(obj.getDesignation_date());
				obj2.setPay_level(obj.getPay_level());
				obj2.setFather_name(obj.getFather_name());
				obj2.setMother_name(obj.getMother_name());
				obj2.setReligion(obj.getReligion());
				obj2.setMother_tongue(obj.getMother_tongue());
				obj2.setAadhar_card(obj.getAadhar_card());
				obj2.setPan_no(obj.getPan_no());
				obj2.setCountry_original(obj.getCountry_original());
				obj2.setState_original(obj.getState_original());
				obj2.setDistrict_original(obj.getDistrict_original());
				obj2.setTehsil_origin(obj.getTehsil_origin());

				if (obj.getOriginal_country_other() != "") {
					obj2.setOriginal_country_other(obj.getOriginal_country_other());
				}
				if (obj.getOriginal_state_other() != "") {
					obj2.setOriginal_state_other(obj.getOriginal_state_other());
				}

				if (obj.getOriginal_district_other() != "") {
					obj2.setOriginal_district_other(obj.getOriginal_district_other());
				}

				if (obj.getOriginal_tehshil_other() != "") {
					obj2.setOriginal_tehshil_other(obj.getOriginal_tehshil_other());
				}

				obj2.setCountry_present(obj.getCountry_present());
				obj2.setState_present(obj.getState_present());
				obj2.setDistrict_present(obj.getDistrict_present());
				obj2.setTehsil_present(obj.getTehsil_present());

				if (obj.getPresent_country_other() != "") {
					obj2.setPresent_country_other(obj.getPresent_country_other());
				}
				if (obj.getPresent_state_other() != "") {
					obj2.setPresent_state_other(obj.getPresent_state_other());
				}
				if (obj.getPresent_district_other() != "") {
					obj2.setPresent_district_other(obj.getPresent_district_other());
				}
				if (obj.getPresent_tehshil_other() != "") {
					obj2.setPresent_tehshil_other(obj.getPresent_tehshil_other());
				}
				obj2.setNationality(obj.getNationality());
				if (obj.getNon_effective() != 0) {
					obj2.setNon_effective(obj.getNon_effective());
					obj2.setDate_non_effective(obj.getDate_non_effective());
				}
				////updated on 28-04-22 parth
				if (obj.getNon_effective() != 0) {
					obj2.setStatus(4);
				}
				else {
					obj2.setStatus(1);
				}
				obj2.setEmployee_no(obj.getEmployee_no());
				obj2.setCivilian_status(civi_status);
				obj2.setModified_by(username);
				obj2.setModified_date(date);
				obj2.setDate_of_tos(obj.getDate_of_tos());

				session1.update(obj2);
				session1.flush();
				session1.clear();

				msg = "Data Approved Successfully.";

				String hql1 = "update TB_CIVILIAN_DETAILS set status=:status where  main_id=:main_id and (status != '0') ";

				Query query1 = session1.createQuery(hql1).setInteger("status", 2).setInteger("main_id",
						obj.getMain_id());

				msg = query1.executeUpdate() > 0 ? "1" : "0";
				session1.flush();
				session1.clear();

				String hql = "update TB_CIVILIAN_DETAILS set status=:status where  main_id=:main_id and status = '0' ";

				Query query = session1.createQuery(hql).setInteger("status", 1).setInteger("main_id", obj.getMain_id());

				msg = query.executeUpdate() > 0 ? "1" : "0";
				session1.flush();
				session1.clear();
				
				Query query3;
				String hql3 = "update TB_CIVILIAN_DETAILS set modified_by=:modified_by ,modified_date=:modified_date,main_id=:main_id, "
						+ " status=:status" + " where  id=:id and status = '1'";
				if (obj.getNon_effective() != 0) {
					query3 = session1.createQuery(hql3).setParameter("status", 4).setString("modified_by", username)
							.setDate("modified_date", new Date()).setInteger("main_id", obj.getMain_id()).setInteger("id", id);
				} else {
					query3 = session1.createQuery(hql3).setParameter("status", 1).setString("modified_by", username)
							.setDate("modified_date", new Date()).setInteger("main_id", obj.getMain_id()).setInteger("id", id);
				}

				msg = query3.executeUpdate() > 0 ? "1" : "0";
				session1.flush();
				session1.clear();
			}
			if (msg == "1") {
				model.put("msg", "Data Approved Successfully.");
			} else {
				model.put("msg", "Data Not Approved.");
			}

			tx.commit();

		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				rbe.printStackTrace();
				model.put("msg",
						"CouldnÃƒÂ¯Ã‚Â¿Ã‚Â½t roll back transaction "
								+ rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return new ModelAndView("redirect:Search_civilian_regular");
	}

	@RequestMapping(value = "/Reject_Civilian", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Reject_Civilian(@ModelAttribute("id4") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "reject_remarks4", required = false) String reject_remarks,
			Authentication authentication) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String username = session.getAttribute("username").toString();

		try {

			String hql = "update TB_CIVILIAN_DETAILS set reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("reject_remarks", reject_remarks)
					.setString("modified_by", username).setTimestamp("modified_date", new Date())
					.setInteger("status", 3).setInteger("id", id);
			msg = query.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			model.put("msg", msg);
			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}

		return new ModelAndView("redirect:Search_civilian_regular");
	}

	@RequestMapping(value = "/appcivilianRegularUrl", method = RequestMethod.POST)
	public ModelAndView appcivilianRegularUrl(@ModelAttribute("id5") String id, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Mmap.put("roleSusNo", roleSusNo);
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));
		TB_CIVILIAN_DETAILS authDetails = cdao.getTB_CIVILIAN_DETAILSByid(Integer.parseInt(id));
		List<Map<String, Object>> list = cdao.getAllAppDataForviewregular(Integer.parseInt(id));
		Mmap.put("listfull", list);
		Mmap.put("civilian_details_cmd", authDetails);
		Mmap.put("msg", msg);
		Mmap.put("getGenderList", p_comm.getGenderList());
		Mmap.put("getMothertoungeList", p_comm.getMothertoungeList());
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getPayLevelList", p_comm.getPayLevelList());
		Mmap.put("getReligionList", p_comm.getReligionList());
		Mmap.put("getNationalityList", p_comm.getNationalityList());
		Mmap.put("getRegNonEff", p_comm.getRegNonEff());
		Mmap.put("getCadre", p_comm.getCadre());
		Mmap.put("getCategoryList", p_comm.getCategoryList());
		Mmap.put("getClassificationOfTradesList", p_comm.getClassificationOfTradesList());
		Mmap.put("getMedCountryName", p_comm.getMedCountryName("", session));
		Mmap.put("getDesignationList", p_comm.getDesignationList());
		Mmap.put("getExservicemenList", p_comm.getExservicemenList());
		Mmap.put("getCivilianTypeList", p_comm.getCivilianTypeList());
		Mmap.put("getServiceStatusList", p_comm.getServiceStatusList());
		Mmap.put("getPersonWithDisabilityList", p_comm.getPersonWithDisabilityList());
		Mmap.put("getClassificationOfServiceList", p_comm.getClassificationOfServiceList());
		Mmap.put("getCadre", p_comm.getCadre());

		return new ModelAndView("appcivilianRegularTiles");
	}

	@RequestMapping(value = "/admin/AppEditUrl")
	public ModelAndView AppEditUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "id6", required = false) String id, HttpServletRequest request) {

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Mmap.put("roleSusNo", roleSusNo);
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));
		TB_CIVILIAN_DETAILS Details = cdao.getTB_CIVILIAN_DETAILSByid(Integer.parseInt(id));
		Mmap.put("Details", Details);
		Mmap.put("msg", msg);
		Mmap.put("getGenderList", p_comm.getGenderList());
		Mmap.put("getMothertoungeList", p_comm.getMothertoungeList());
		Mmap.put("getPayLevelList", p_comm.getPayLevelList());
		Mmap.put("getReligionList", p_comm.getReligionList());
		Mmap.put("getNationalityList", p_comm.getNationalityList());
		Mmap.put("getRegNonEff", p_comm.getRegNonEff());
		Mmap.put("getCadre", p_comm.getCadre());
		Mmap.put("getCategoryList", p_comm.getCategoryList());
		Mmap.put("getClassificationOfTradesList", p_comm.getClassificationOfTradesList());
		Mmap.put("getCountryList", p_comm.getCountryList());
		Mmap.put("getDesignationList", p_comm.getDesignationList());
		Mmap.put("getExservicemenList", p_comm.getExservicemenList());
		Mmap.put("getCivilianTypeList", p_comm.getCivilianTypeList());
		Mmap.put("getServiceStatusList", p_comm.getServiceStatusList());
		Mmap.put("getPersonWithDisabilityList", p_comm.getPersonWithDisabilityList());
		Mmap.put("getClassificationOfServiceList", p_comm.getClassificationOfServiceList());
		Mmap.put("getCivHigh_Qualification", p_comm.getCivHigh_Qualification());
		return new ModelAndView("AppEditcivilianRegularTiles");
	}

	@RequestMapping(value = "/admin/AppEditcivilianRegularAction", method = RequestMethod.POST)
	public ModelAndView AppEditcivilianRegularAction(ModelMap Mmap, HttpSession session, HttpServletRequest request,MultipartHttpServletRequest mul)
			throws ParseException, IOException {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date dob_dt = null;
		Date joining_date_gov_service_dt = null;
		Date designation_date_dt = null;
		Date date_non_effective_dt = null;
		Date dt_authority = null;
		Date date_tos = null;

		int id = Integer.parseInt(request.getParameter("id"));
		int astatus = Integer.parseInt(request.getParameter("status"));
		String fname = "";
		String extension = "";
		String mobile_no = request.getParameter("mobile_no");
		String username = session.getAttribute("username").toString();
		String unit_sus_no = request.getParameter("unit_sus_no");
		String emp_no = request.getParameter("employee_no");
		String emp_no_old = request.getParameter("employee_no_old");
		String authority = request.getParameter("authority");
		String dt_of_authority = request.getParameter("dt_of_authority");
		String pan_no = request.getParameter("pan_no");
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String name1 = first_name + " " + middle_name + " " + last_name;
		String gender = request.getParameter("gender");
		String gender_hid = request.getParameter("gender_hid");
		String classification_services = request.getParameter("classification_services");
		String group = request.getParameter("group");
		String category_belongs = request.getParameter("category_belongs");
		String service_status = request.getParameter("service_status");
		String classification_trade = request.getParameter("classification_trade");
		String classification_trade_hid = request.getParameter("classification_of_trade_hid");
		String type = request.getParameter("type");
		String wes = request.getParameter("wes");
		String whether_ex_serviceman = request.getParameter("whether_ex_serviceman");
		String wpwd = request.getParameter("wpwd");
		String whether_person_disability = request.getParameter("whether_person_disability");
		String post_initialy_appointed = request.getParameter("post_initialy_appointed");
		String joining_date_gov_service = request.getParameter("joining_date_gov_service");
		String designation = request.getParameter("designation");
		String designation_date = request.getParameter("designation_date");
		String pay_level = request.getParameter("pay_level");
		String father_name = request.getParameter("father_name");
		String mother_name = request.getParameter("mother_name");
		String country_original = request.getParameter("country_original");
		String country_original_hid = request.getParameter("country_original_hid");
		String state_original = request.getParameter("original_state");
		String state_original_hid = request.getParameter("original_state_hid");
		String district_original = request.getParameter("district_original");
		String district_original_hid = request.getParameter("district_original_hid");
		String tehsil_origin = request.getParameter("original_tehsil");
		String tehsil_origin_hid = request.getParameter("original_tehsil_hid");
		String country_present = request.getParameter("country_present");
		String country_present_hid = request.getParameter("country1_hid");
		String state_present = request.getParameter("state_present");
		String state_present_hid = request.getParameter("present_state_hid");
		String district_present = request.getParameter("district_present");
		String district_present_hid = request.getParameter("present_district_hid");
		String tehsil_present = request.getParameter("tehsil_present");
		String tehsil_present_hid = request.getParameter("tehsil_present_hid");
		String nationality = request.getParameter("nationality");
		String nationality_hid = request.getParameter("nationality_hid");
		String religion = request.getParameter("religion");
		String religion_hid = request.getParameter("religion_hid");
		String mother_tongue = request.getParameter("mother_tongue");
		String mother_tongue_hid = request.getParameter("mother_tongue_hid");
		String eff = request.getParameter("eff");
		String gender_other = request.getParameter("gender_other");
		String cadre = request.getParameter("cadre");
		String original_country_other = request.getParameter("original_country_other");
		String original_state_other = request.getParameter("original_state_other");
		String original_district_other = request.getParameter("original_district_other");
		String original_tehshil_other = request.getParameter("original_tehshil_other");
		String present_country_other = request.getParameter("present_country_other");
		String present_state_other = request.getParameter("present_state_other");
		String present_district_other = request.getParameter("present_district_other");
		String present_tehshil_other = request.getParameter("present_tehsil_other");
		String classification_trade_other = request.getParameter("classification_of_trade_other");
		String whether_ex_serviceman_hid = request.getParameter("whether_ex_serviceman_hid");
		String religion_other = request.getParameter("religion_other");
		String mother_tongue_other = request.getParameter("mother_tongue_other");
		String nationality_other = request.getParameter("nationality_other");
		String service_other = request.getParameter("service_other");
		String aadhar_card = "";
		String highest_qualification = request.getParameter("high_qual");
		int main_id = Integer.parseInt(request.getParameter("main_id"));

		String radiob_non_effective = request.getParameter("eff");
		String non_effective = request.getParameter("non_effective");
		String date_non_effective = request.getParameter("date_non_effective");
		String dob = request.getParameter("dob");
		String date_of_tos = request.getParameter("date_of_tos");

		String status = "R";

		if (!request.getParameter("pers_aadhar_no11").equals("") & !request.getParameter("pers_aadhar_no2").equals("")
				|| !request.getParameter("pers_aadhar_no3").equals("")) {
			String pers_aadhar_no11 = request.getParameter("pers_aadhar_no11");
			String pers_aadhar_no2 = request.getParameter("pers_aadhar_no2");
			String pers_aadhar_no3 = request.getParameter("pers_aadhar_no3");
			aadhar_card = pers_aadhar_no11 + pers_aadhar_no2 + pers_aadhar_no3;

		}

		TB_CIVILIAN_DETAILS obj = new TB_CIVILIAN_DETAILS();

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		if (!roleAccess.equals("Unit")) {
			if (unit_sus_no.equals("")) {
				Mmap.put("msg", "Please Enter Unit Sus No.");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				roleSusNo = unit_sus_no;
			}
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		}

		if (!roleAccess.equals("Unit")) {
			if (unit_sus_no.equals("")) {
				Mmap.put("msg", "Please Enter Unit Sus No.");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				roleSusNo = unit_sus_no;
			}
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		}

		if (authority == null || authority.equals("")) {
			Mmap.put("msg", "Please Enter Authority");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}
		if (!valid.isValidAuth(authority)) {
			Mmap.put("msg", valid.isValidAuthMSG + "Authority");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isvalidLength(authority, valid.authorityMax, valid.authorityMin)) {
			Mmap.put("msg", "Authority " + valid.isValidLengthMSG);
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (dt_of_authority == null || dt_of_authority.equals("null") || dt_of_authority.equals("DD/MM/YYYY")
				|| dt_of_authority.equals("")) {
			Mmap.put("msg", "Please Select Date of Authority");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!dt_of_authority.equals("") || dt_of_authority != "") {

			if (!valid.isValidDate(dt_of_authority)) {
				Mmap.put("msg", valid.isValidDateMSG + " of Authority");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				dt_authority = format.parse(dt_of_authority);
			}
		}

		if (unit_sus_no != "") {
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		}

		if (emp_no == null || emp_no.trim().equals("")) {
			Mmap.put("msg", "Please Enter Employee No.");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}
		
		if(!emp_no.equals(emp_no_old)) {
		Query q0 = sessionhql
				.createQuery("select count(id) from TB_CIVILIAN_DETAILS where employee_no=:employee_no");
		q0.setParameter("employee_no", emp_no);
		Long c = (Long) q0.uniqueResult();
			if (c > 0) {
				Mmap.put("msg", "Employee No Already Exist.");	
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		}
		

		if (!valid.isValidAuth(emp_no)) {
			Mmap.put("msg", valid.isValidAuthMSG + "Employee No");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isvalidLength(emp_no, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Employee No " + valid.isValidLengthMSG);
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (first_name == null || first_name.trim().equals("") || first_name.equals(null)) {
			Mmap.put("msg", "Please Enter First Name");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isOnlyAlphabet(first_name)) {
			Mmap.put("msg", " First Name " + valid.isOnlyAlphabetMSG);
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isvalidLength(first_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "First Name " + valid.isValidLengthMSG);
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isOnlyAlphabet(middle_name)) {
			Mmap.put("msg", " Middle Name " + valid.isOnlyAlphabetMSG);
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isvalidLength(middle_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Middle Name " + valid.isValidLengthMSG);
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isOnlyAlphabet(last_name)) {
			Mmap.put("msg", " Last Name " + valid.isOnlyAlphabetMSG);
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isvalidLength(last_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Last Name " + valid.isValidLengthMSG);
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isValidDate(dob)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Birth");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (dob == null || dob.equals("null") || dob.equals("DD/MM/YYYY") || dob.equals("")) {
			Mmap.put("msg", "Please Select Date of Birth");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (dob != "") {
			dob_dt = format.parse(dob);
		}

		/*
		 * if(radiob_non_effective.equals("no")) { if
		 * (p_comm.calculate_age(format.parse(dob), date) < 18 ||
		 * p_comm.calculate_age(format.parse(dob), date) >= 60) { Mmap.put("msg",
		 * "Please Enter Age Above 18"); Mmap.put("id6", id); return new
		 * ModelAndView("redirect:AppEditUrl"); }
		 */

		if (radiob_non_effective.equals("no")) {
			int age = p_comm.calculate_age(format.parse(dob), date);

			if (age < 18) {
				Mmap.put("msg", "Please Enter Age Above 18");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else if (age >= 60) {
				Mmap.put("msg", "Please Enter Age Below 60");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

		}

		if (gender == "0") {
			Mmap.put("msg", "Please Select Gender");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (gender_hid.toUpperCase().equals("OTHER") || gender_hid.toUpperCase() == "OTHER") {
			if (gender_other == null || gender_other.trim().equals("") || gender_other.equals("null")) {
				Mmap.put("msg", "Please Enter Gender Other");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setGender_other(gender_other);
			}

			if (!valid.isOnlyAlphabet(gender_other)) {
				Mmap.put("msg", " Gender Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isvalidLength(gender_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Gender Other " + valid.isValidLengthMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		} else {
			obj.setGender_other("");
		}

		if (classification_services == null) {
			Mmap.put("msg", "Please Select Classification of Services");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}
		if (group == null) {
			Mmap.put("msg", "Please Select Group");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}
		if (category_belongs == "0") {
			Mmap.put("msg", "Please Select Category");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (service_status == null) {
			Mmap.put("msg", "Please Select Service Status");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (classification_services == "1") {
			if (obj.getClassification_trade() == 0) {
				Mmap.put("msg", "Please Select Classification of Trade");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		}

		if (classification_trade_hid.toUpperCase().equals("OTHERS")
				|| classification_trade_hid.toUpperCase() == "OTHERS") {
			if (classification_trade_other == null || classification_trade_other.equals("")
					|| classification_trade_other.equals("null")) {
				Mmap.put("msg", "Please Enter Classification of Trade Other");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setClassification_trade_other(classification_trade_other);
			}

			if (!valid.isOnlyAlphabet(classification_trade_other)) {
				Mmap.put("msg", " Classification of Trade Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isvalidLength(classification_trade_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Classification of Trade Other " + valid.isValidLengthMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		} else {
			obj.setClassification_trade_other("");
		}

		if (type == null) {
			Mmap.put("msg", "Please Select Type");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (wes.equals("yes")) {
			if (whether_ex_serviceman == "0") {
				Mmap.put("msg", "Please Select Whether Ex-Serviceman");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setWhether_ex_serviceman(whether_ex_serviceman);
			}

			if (whether_ex_serviceman_hid.toUpperCase().equals("OTHER")
					|| whether_ex_serviceman_hid.toUpperCase() == "OTHER") {

				if (service_other == null || service_other.equals("") || service_other.equals("null")) {
					Mmap.put("msg", "Please Enter Service Other");
					Mmap.put("id2", id);
					return new ModelAndView("redirect:AppEditUrl");
				} else {
					obj.setService_other(service_other);
				}

				if (!valid.isOnlyAlphabet(service_other)) {
					Mmap.put("msg", " Service Other " + valid.isOnlyAlphabetMSG);
					Mmap.put("id6", id);
					return new ModelAndView("redirect:AppEditUrl");
				}

				if (!valid.isvalidLength(service_other, valid.nameMax, valid.nameMin)) {
					Mmap.put("msg", "Service Other " + valid.isValidLengthMSG);
					Mmap.put("id6", id);
					return new ModelAndView("redirect:AppEditUrl");
				}
			} else {
				obj.setService_other("");
			}
		}

		else {
			obj.setWhether_ex_serviceman("");
		}

		if (wpwd.equals("yes")) {
			if (whether_person_disability == "0") {
				Mmap.put("msg", "Please Select Whether Person With Disability");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setWhether_person_disability(whether_person_disability);
			}
		} else {
			obj.setWhether_person_disability("");
		}

		if (!valid.isValidDate(joining_date_gov_service)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Joining Govt Service");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (joining_date_gov_service == null || joining_date_gov_service.equals("null")
				|| joining_date_gov_service.equals("DD/MM/YYYY") || joining_date_gov_service.equals("")) {
			Mmap.put("msg", "Please Select Date of Joining Govt Service");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		} else {
			joining_date_gov_service_dt = format.parse(joining_date_gov_service);
		}

		if (p_comm.CompareDate(joining_date_gov_service_dt, dob_dt) == 0) {
			Mmap.put("msg", "Date of Joining should Be Greater Than Date of Birth");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isValidDate(designation_date)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Designation");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (designation_date == null || designation_date.equals("null") || designation_date.equals("DD/MM/YYYY")
				|| designation_date.equals("")) {
			Mmap.put("msg", "Please Select Date of Designation");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		} else {
			designation_date_dt = format.parse(designation_date);
		}

		if (p_comm.CompareDate(designation_date_dt, dob_dt) == 0) {
			Mmap.put("msg", "Date of Designation  should Be Greater Than Date of Birth");
			Mmap.put("id2", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (p_comm.CompareDate(designation_date_dt, joining_date_gov_service_dt) == 0) {
			Mmap.put("msg", "Date of Designation  should Be Greater Than Date of Joining");
			Mmap.put("id2", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (p_comm.CompareDate(designation_date_dt, dob_dt) == 0) {
			Mmap.put("msg", "Date of Designation  should Be Greater Than Date of Birth");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (pay_level == "0") {
			Mmap.put("msg", "Please Select Pay Level");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isValidDate(date_of_tos)) {
			Mmap.put("msg", valid.isValidDateMSG + " of TOS");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (date_of_tos == null || date_of_tos.equals("null") || date_of_tos.equals("DD/MM/YYYY")
				|| date_of_tos.equals("")) {
			Mmap.put("msg", "Please Select Date of Joining Govt Service");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		} else {
			date_tos = format.parse(date_of_tos);
		}

		if (p_comm.CompareDate(date_tos, dob_dt) == 0) {
			Mmap.put("msg", "Date of TOS should Be Greater Than Date of Birth");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (p_comm.CompareDate(date_tos, joining_date_gov_service_dt) == 0) {
			Mmap.put("msg", "Date of TOS  should Be Greater Than Date of Joining");
			Mmap.put("id2", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isOnlyAlphabet(father_name)) {
			Mmap.put("msg", " Father Name " + valid.isOnlyAlphabetMSG);
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isvalidLength(father_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Father Name " + valid.isValidLengthMSG);
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isOnlyAlphabet(mother_name)) {
			Mmap.put("msg", " Mother Name " + valid.isOnlyAlphabetMSG);
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (!valid.isvalidLength(mother_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Mother Name " + valid.isValidLengthMSG);
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (religion_hid.toUpperCase().equals("OTHERS") || religion_hid.toUpperCase() == "OTHERS") {
			if (religion_other == null || religion_other.equals("") || religion_other.equals("null")) {
				Mmap.put("msg", "Please Enter Religion Other");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setReligion_other(religion_other);
			}

			if (!valid.isOnlyAlphabet(religion_other)) {
				Mmap.put("msg", " Religion Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isvalidLength(religion_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Religion Other " + valid.isValidLengthMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		} else {
			obj.setReligion_other("");
		}
		if (mother_tongue_hid.toUpperCase().equals("OTHERS") || mother_tongue_hid.toUpperCase() == "OTHERS") {
			if (mother_tongue_other == null || mother_tongue_other.equals("") || mother_tongue_other.equals("null")) {
				Mmap.put("msg", "Please Enter Mother Tongue Other");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setMother_tongue_other(mother_tongue_other);
			}

			if (!valid.isOnlyAlphabet(mother_tongue_other)) {
				Mmap.put("msg", " Mother Tongue Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isvalidLength(mother_tongue_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Mother Tongue Other " + valid.isValidLengthMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		} else {
			obj.setMother_tongue_other("");
		}

		if (valid.isOnlyNumer(aadhar_card) == true) {
			Mmap.put("msg", " Aadhaar Card No  " + valid.isOnlyNumerMSG);
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}
		if (aadhar_card != "") {
			if (!valid.isValidAadhar(aadhar_card)) {
				Mmap.put("msg", valid.isValidAadharMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		}

		if (StringUtils.hasText(pan_no)) {

			if (!valid.isValidPan(pan_no)) {
				Mmap.put("msg", valid.isValidPanMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.PanNoLength(pan_no)) {
				Mmap.put("msg", valid.PanNoMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		}

		if (country_original == "0" || country_original == null || country_original.equals("null")) {
			Mmap.put("msg", "Please Select Original Country");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (country_original_hid.toUpperCase().equals("OTHERS") || country_original_hid.toUpperCase() == "OTHERS") {

			if (original_country_other == null || original_country_other.equals("")
					|| original_country_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of Country Other");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setOriginal_country_other(original_country_other);
			}

			if (!valid.isOnlyAlphabet(original_country_other)) {
				Mmap.put("msg", " Original Country Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isvalidLength(original_country_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Original Country Other " + valid.isValidLengthMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		} else {
			obj.setOriginal_country_other("");
		}

		if (state_original == "0" || state_original == null || state_original.equals("null")) {
			Mmap.put("msg", "Please Select Original State");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}

		if (state_original_hid.toUpperCase().equals("OTHERS") || state_original_hid.toUpperCase() == "OTHERS") {
			if (original_state_other == null || original_state_other.equals("")
					|| original_state_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of State Other");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setOriginal_state_other(original_state_other);
			}

			if (!valid.isOnlyAlphabet(original_state_other)) {
				Mmap.put("msg", " Original State Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isvalidLength(original_state_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Original State Other " + valid.isValidLengthMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		} else {
			obj.setOriginal_state_other("");
		}

		if (district_original_hid.toUpperCase().equals("OTHERS") || district_original_hid.toUpperCase() == "OTHERS") {
			if (original_district_other == null || original_district_other.equals("")
					|| original_district_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of District Other");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setOriginal_district_other(original_district_other);
			}

			if (!valid.isOnlyAlphabet(original_district_other)) {
				Mmap.put("msg", " Original District Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isvalidLength(original_district_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Original District Other " + valid.isValidLengthMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		} else {
			obj.setOriginal_district_other("");
		}

		if (tehsil_origin_hid.toUpperCase().equals("OTHERS") || tehsil_origin_hid.toUpperCase() == "OTHERS") {
			if (original_tehshil_other == null || original_tehshil_other.equals("")
					|| original_tehshil_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of Tehsil Other");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setOriginal_tehshil_other(original_tehshil_other);
			}

			if (!valid.isOnlyAlphabet(original_tehshil_other)) {
				Mmap.put("msg", " Original Tehsil Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isvalidLength(original_tehshil_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Original Tehsil Other " + valid.isValidLengthMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		} else {
			obj.setOriginal_tehshil_other("");
		}
		// start present
		if (country_present == "0" || country_present == null || country_present.equals("null")) {
			Mmap.put("msg", "Please Select Present Country");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}
		if (country_present_hid.toUpperCase().equals("OTHERS") || country_present_hid.toUpperCase() == "OTHERS") {

			if (present_country_other == null || present_country_other.equals("")
					|| present_country_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of Country Other");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setPresent_country_other(present_country_other);
			}

			if (!valid.isOnlyAlphabet(present_country_other)) {
				Mmap.put("msg", " Present Country Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isvalidLength(present_country_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Present Country Other " + valid.isValidLengthMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		} else {
			obj.setPresent_country_other("");
		}

		if (state_present == "0" || state_present == null || state_present.equals("null")) {
			Mmap.put("msg", "Please Select Present State");
			Mmap.put("id6", id);
			return new ModelAndView("redirect:AppEditUrl");
		}
		if (state_present_hid.toUpperCase().equals("OTHERS") || state_present_hid.toUpperCase() == "OTHERS") {
			if (present_state_other == null || present_state_other.equals("") || present_state_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of State Other");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setPresent_state_other(present_state_other);
			}

			if (!valid.isOnlyAlphabet(present_state_other)) {
				Mmap.put("msg", " Present State Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isvalidLength(present_state_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Present State Other " + valid.isValidLengthMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		} else {
			obj.setPresent_state_other("");
		}

		if (district_present_hid.toUpperCase().equals("OTHERS") || district_present_hid.toUpperCase() == "OTHERS") {
			if (present_district_other == null || present_district_other.equals("")
					|| present_district_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of District Other");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setPresent_district_other(present_district_other);
			}

			if (!valid.isOnlyAlphabet(present_district_other)) {
				Mmap.put("msg", " Present District Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isvalidLength(present_district_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Present District Other " + valid.isValidLengthMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		} else {
			obj.setPresent_district_other("");
		}

		if (tehsil_present_hid.toUpperCase().equals("OTHERS") || tehsil_present_hid.toUpperCase() == "OTHERS") {
			if (present_tehshil_other == null || present_tehshil_other.equals("")
					|| present_tehshil_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of Tehsil Other");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setPresent_tehshil_other(present_tehshil_other);
			}

			if (!valid.isOnlyAlphabet(present_tehshil_other)) {
				Mmap.put("msg", " Present Tehsil Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isvalidLength(present_tehshil_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Present Tehsil Other " + valid.isValidLengthMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		} else {
			obj.setPresent_tehshil_other("");
		}

		if (nationality_hid.toUpperCase().equals("OTHERS") || nationality_hid.toUpperCase() == "OTHERS") {
			if (nationality_other == null || nationality_other.equals("") || nationality_other.equals("null")) {
				Mmap.put("msg", "Please Enter Nationality Other");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				obj.setNationality_other(nationality_other);
			}

			if (!valid.isOnlyAlphabet(nationality_other)) {
				Mmap.put("msg", " Nationality Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isvalidLength(nationality_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Nationality Other " + valid.isValidLengthMSG);
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
		} else {
			obj.setNationality_other("");
		}

		if (eff.equals("yes")) {
			if (Integer.parseInt(non_effective) == 0) {
				Mmap.put("msg", "Please Select Non Effective");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (!valid.isValidDate(date_non_effective)) {
				Mmap.put("msg", valid.isValidDateMSG + "  Non Effective ");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (date_non_effective == null || date_non_effective.equals("null")
					|| date_non_effective.equals("DD/MM/YYYY") || date_non_effective.equals("")) {
				Mmap.put("msg", "Please Select Date of Non Effective");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			} else {
				date_non_effective_dt = format.parse(date_non_effective);
			}

			if (p_comm.CompareDate(date_non_effective_dt, dob_dt) == 0) {
				Mmap.put("msg", "Date of Non Effective should Be Greater Than  Date of Birth");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}

			if (p_comm.CompareDate(date_non_effective_dt, joining_date_gov_service_dt) == 0) {
				Mmap.put("msg", "Date of Non Effective should Be Greater Than  Date of Joining Govt Service");
				Mmap.put("id6", id);
				return new ModelAndView("redirect:AppEditUrl");
			}
			obj.setNon_effective(Integer.parseInt(non_effective));
			obj.setDate_non_effective(date_non_effective_dt);
		} else {
			obj.setNon_effective(0);
			obj.setDate_non_effective(null);
		}
		String msg = "";
		
		MultipartFile file = mul.getFile("identity_image");
		if (!file.getOriginalFilename().isEmpty()) {
				
				String upload_imgEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();	
		    	if(!upload_imgEXt.equals("jpg") && !upload_imgEXt.equals("jpeg") && !upload_imgEXt.equals("png")) {
		    		msg= "Only *.jpg, *.jpeg and *.png file extensions allowed";					
		    		Mmap.put("msg", msg);	
		    		return new ModelAndView("redirect:AppEditUrl");
				}
		    	
		    	long fileSizeInBytes = file.getSize();
		    	if (fileSizeInBytes > 2 * 1024 * 1024) { // 2MB in bytes
		    	    msg = "Image Size Should Be 2MB Or Less";
		    	    Mmap.put("msg", msg);	
		    	    return new ModelAndView("redirect:AppEditUrl");
		    	}
		    
				byte[] bytes = file.getBytes();
				String mnhFilePath = session.getAttribute("psgFilePath").toString() + "/Civilian/RegularIdentity";
				File dir = new File(mnhFilePath);
				if (!dir.exists())
					dir.mkdirs();
				String filename = file.getOriginalFilename();

				int j = filename.lastIndexOf('.');
				if (j >= 0) {
					extension = filename.substring(j + 1);
				}
				java.util.Date date1 = new java.util.Date();
				fname = dir.getAbsolutePath() + File.separator
						+ (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".")
								.toString().replace(" ", "").toString().replace("-", "").toString()
						+ "PSG_Doc." + extension;
				File serverFile = new File(fname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

			}

		obj.setMain_id(main_id);
		obj.setAuthority(authority.trim());
		obj.setPan_no(pan_no.trim());
		obj.setDt_of_authority(dt_authority);
		obj.setSus_no(roleSusNo.trim());
		obj.setFirst_name(first_name.trim());
		obj.setMiddle_name(middle_name.trim());
		obj.setLast_name(last_name.trim());
		obj.setFull_name(name1.trim());
		obj.setDob(dob_dt);
		obj.setGender(Integer.parseInt(gender));
		obj.setClassification_services(classification_services);
		obj.setCiv_group(group);
		obj.setCategory_belongs(Integer.parseInt(category_belongs));
		obj.setService_status(service_status);
		obj.setClassification_trade(Integer.parseInt(classification_trade));
		if (classification_trade_other != "" || classification_trade_other != null) {
			obj.setClassification_trade_other(classification_trade_other);
		}

		if (present_tehshil_other != "" || present_tehshil_other != null) {
			obj.setPresent_tehshil_other(present_tehshil_other);
		}
		obj.setCiv_type(type);
		obj.setCadre(Integer.parseInt(cadre));
		obj.setPost_initialy_appointed(post_initialy_appointed);
		obj.setJoining_date_gov_service(joining_date_gov_service_dt);
		obj.setDesignation(Integer.parseInt(designation));
		obj.setPay_level(Integer.parseInt(pay_level));
		obj.setFather_name(father_name.trim());
		obj.setMother_name(mother_name.trim());
		obj.setCountry_original(Integer.parseInt(country_original));
		obj.setState_original(Integer.parseInt(state_original));
		obj.setDistrict_original(Integer.parseInt(district_original));
		obj.setTehsil_origin(Integer.parseInt(tehsil_origin));
		obj.setCountry_present(Integer.parseInt(country_present));
		obj.setState_present(Integer.parseInt(state_present));
		obj.setDistrict_present(Integer.parseInt(district_present));
		obj.setTehsil_present(Integer.parseInt(tehsil_present));
		obj.setNationality(Integer.parseInt(nationality));
		obj.setReligion(Integer.parseInt(religion));
		obj.setMother_tongue(Integer.parseInt(mother_tongue));
		obj.setAadhar_card(aadhar_card.trim());
		obj.setDesignation_date(designation_date_dt);
		obj.setCivilian_status(status);
		obj.setEmployee_no(emp_no.trim());
		obj.setDate_of_tos(date_tos);
		obj.setMobile_no(mobile_no);
		obj.setQualification(Integer.parseInt(highest_qualification));
		if (StringUtils.hasText(fname)) { // this check notnull,notempty,no space
			obj.setIdentity_image(fname);
		}else {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();		
			TB_CIVILIAN_DETAILS civilian = (TB_CIVILIAN_DETAILS) sessionHQL.get(TB_CIVILIAN_DETAILS.class, id);
			String identityImage = civilian.getIdentity_image();
			obj.setIdentity_image(identityImage);
		}
	

		try {

			Query q0 = sessionhql.createQuery(
					"select count(id) from TB_CIVILIAN_DETAILS where employee_no=:employee_no and status='0'");
			q0.setParameter("employee_no", emp_no);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				obj.setModified_by(username);
				obj.setModified_date(date);
				obj.setStatus(0);
				sessionhql.save(obj);
				Mmap.put("msg", "Data Updated Successfully.");
			} else {
				Mmap.put("msg", "Data Already in Pending");
			}

			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				tx.rollback();
				msg = "Data Not Updated";
			} catch (RuntimeException rbe) {
				msg = "Data Not Updated";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}

		return new ModelAndView("redirect:Search_civilian_regular");
	}

	@RequestMapping(value = "/CancelUrl", method = RequestMethod.POST)
	public @ResponseBody ModelAndView CancelUrl(@ModelAttribute("id7") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String username = session.getAttribute("username").toString();

		try {

			String hql = "update TB_CIVILIAN_DETAILS set cancel_by=:cancel_by,cancel_date=:cancel_date,cancel_status=:cancel_status  "
					+ " where id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("cancel_by", username)
					.setTimestamp("cancel_date", new Date()).setInteger("cancel_status", 0).setInteger("id", id);

			msg = query.executeUpdate() > 0 ? "Data Canceled Successfully." : "Data Not Canceled.";

			model.put("msg", msg);
			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Canceled.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}

		return new ModelAndView("redirect:Search_civilian_regular");
	}

	@RequestMapping(value = "/getEmplNo", method = RequestMethod.POST)
	public @ResponseBody String getEmplNo(HttpServletRequest request, HttpSession sessionA, String employee_no)
			throws HibernateException, ParseException {

		return cdao.getEmpNoAlreadyExits(employee_no);

	}

	@RequestMapping(value = "/AppCancelUrl", method = RequestMethod.POST)
    public @ResponseBody ModelAndView AppCancelUrl(@ModelAttribute("id8") int id, BindingResult result,
                    HttpServletRequest request, HttpSession sessionA, ModelMap model, HttpSession session,
                    @RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

            String roleid = session.getAttribute("roleid").toString();
            Boolean val = roledao.ScreenRedirect("Search_civilian_regular", roleid);

            if (val == false) {
                    return new ModelAndView("AccessTiles");
            }

            Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = sessionHQL.beginTransaction();
            String username = session.getAttribute("username").toString();

            try {

                    String hql = "update TB_CIVILIAN_DETAILS set modified_by=:modified_by ,modified_date=:modified_date,cancel_status=:cancel_status,status=:status  "
                                    + " where id=:id ";

                    Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
                                    .setTimestamp("modified_date", new Date()).setInteger("cancel_status", 1).setInteger("status", -1)
                                    .setInteger("id", id);

                    msg = query.executeUpdate() > 0 ? "Data Approved Successfully." : "Data Not Approved.";

                    TB_CIVILIAN_DETAILS obj = cdao.getTB_CIVILIAN_DETAILSByid(id);
                    int main_id = obj.getMain_id();

                    Query q0 = sessionHQL.createQuery(
                                    "select count(id) from TB_CIVILIAN_DETAILS where main_id=:main_id ");
                    q0.setParameter("main_id", main_id);

                    Long c = (Long) q0.uniqueResult();
                    if (c<=1) {
                            //kajal
                            String hql1 = "update TB_CIVILIAN_MAIN set modified_by=:modified_by ,modified_date=:modified_date,cancel_status=:cancel_status,status=:status,"
                                            + "date_non_effective=null where id=:id";

                            Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
                                            .setTimestamp("modified_date", new Date()).setInteger("cancel_status", 1).setInteger("status", -1)
                                            .setTimestamp("date_non_effective",null).setInteger("id", main_id);

                            msg = query1.executeUpdate() > 0 ? "Data Approved Successfully." : "Data Not Approved.";
                    }
                    else {
                            String hqlUpdate="select id from TB_CIVILIAN_DETAILS where main_id=:main_id and status=2 and id!=:id order by id desc ";
                            Query query2 = sessionHQL.createQuery(hqlUpdate).setParameter("main_id", main_id).setParameter("id", id).setMaxResults(1);
                            Integer c1 = (Integer)  query2.uniqueResult();
                            if(c1!=null && c1>0) {
                                    int chang_id=c1.intValue();        
                                    TB_CIVILIAN_DETAILS name_obj = (TB_CIVILIAN_DETAILS) sessionHQL.get(TB_CIVILIAN_DETAILS.class, chang_id);
                                    name_obj.setModified_by(username);
                                    name_obj.setModified_date(new Date());
                                    name_obj.setStatus(1);
                                    sessionHQL.update(name_obj);
                                    TB_CIVILIAN_MAIN cmain_obj = (TB_CIVILIAN_MAIN) sessionHQL.get(TB_CIVILIAN_MAIN.class, main_id);
                                    cmain_obj.setModified_by(username);
                                    cmain_obj.setModified_date(new Date());
                                    cmain_obj.setDate_non_effective(null); 
                                    cmain_obj.setStatus(1);
                                    sessionHQL.update(cmain_obj);
                                    sessionHQL.flush();
                                    sessionHQL.clear();
                            
                            }
                    }
                    

                    model.put("msg", msg);
                    tx.commit();

            
              } catch (Exception e) { msg = "Data Not Approved."; tx.rollback(); } finally
              { sessionHQL.close(); }
             

            return new ModelAndView("redirect:Search_civilian_regular");
    }

	@RequestMapping(value = "/RejCancelUrl", method = RequestMethod.POST)
	public @ResponseBody ModelAndView RejCancelUrl(@ModelAttribute("id9") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String username = session.getAttribute("username").toString();

		try {

			String hql = "update TB_CIVILIAN_DETAILS set modified_by=:modified_by ,modified_date=:modified_date,cancel_status=:cancel_status  "
					+ " where id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("cancel_status", 2).setInteger("id", id);

			msg = query.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			TB_CIVILIAN_DETAILS obj = cdao.getTB_CIVILIAN_DETAILSByid(id);
			int main_id = obj.getMain_id();

			String hql1 = "update TB_CIVILIAN_MAIN set modified_by=:modified_by ,modified_date=:modified_date,cancel_status=:cancel_status"
					+ " where id=:id";

			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("cancel_status", 2).setInteger("id", main_id);
			msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			model.put("msg", msg);
			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return new ModelAndView("redirect:Search_civilian_regular");
	}
	
	
	
	
	
	
	//////////PDF/////////////
	
	
	
	@RequestMapping(value = "/Print_Civilan_regular", method = RequestMethod.POST)
	public ModelAndView Print_Civilan_regular(@ModelAttribute("id1") String id, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session, HttpServletRequest request) throws NumberFormatException, ParseException {

		String username = session.getAttribute("username").toString();

	

		
		List<Map<String, Object>> listp = cdao.getAllAppDataForviewregular(Integer.parseInt(id));



		String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-YYYY").format(new Date());
		return new ModelAndView(new Download__Civilian_regular("L", username, foot,listp));
	}
	
	@RequestMapping(value = "/getEmpNoAlreadyExist", method = RequestMethod.POST)
	public @ResponseBody Boolean getArmyNoAlreadyExist(HttpServletRequest request, HttpSession sessionA, String emp_no)
			throws HibernateException, ParseException {		
		
		return cdao.getEmpNoAlreadyExist(emp_no);
	}
	
	
	@RequestMapping(value = "/regularCivIdentityConvertpath", method = RequestMethod.GET)
	public void censusIdentityConvertpath(@ModelAttribute("i_id") String i_ch_id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		final int BUFFER_SIZE = 4096;
		String i_id = i_ch_id;		

		String filePath = cdao.getRegularIdentityImagePath(i_id);
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


}
