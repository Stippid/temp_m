package com.controller.psg.Civilian;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import com.controller.ExportFile.Download_non_regular;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Civilian.Civilian_dtlDAO;
import com.dao.psg.Civilian_Report.Arm_Service_Wise_RegularEst_Dao;
import com.models.psg.Civilian.TB_CIVILIAN_DETAILS;
import com.models.psg.Civilian.TB_CIVILIAN_MAIN;
import com.models.psg.Civilian.TB_POSTING_IN_OUT_CIVILIAN;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Non_Regular_Controller {

	
	ValidationController valid = new ValidationController();
	//CommanController all = new CommanController();
	
	Psg_CommonController mcommon = new Psg_CommonController();
	PsgDashboardController das = new PsgDashboardController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();

	@Autowired
	private Civilian_dtlDAO cdao;
	
	@Autowired
	private Arm_Service_Wise_RegularEst_Dao regdao; 
	
//	@Autowired
//	private Report_3008DAO report_3008DAO;
//	@Autowired
//	private Search_PostOutDao pod;
	@Autowired
	private RoleBaseMenuDAO roledao;
	Pattern pattern = Pattern.compile(".*[^0-9].*");

	@RequestMapping(value = "/admin/Search_civilian_non_regular", method = RequestMethod.GET)
	public ModelAndView Search_civilian_non_regular(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();

		String roleType = session.getAttribute("roleType").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_non_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}

		Mmap.put("list", cdao.Search_Civilian_non_regular(roleSusNo, roleType, "", "", "", 0,"","","", session));
		Mmap.put("status", "0");
		Mmap.put("msg", msg);
		Mmap.put("roleType", roleType);
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		return new ModelAndView("non_regular_civilian_searchlTiles");
	}

	@RequestMapping(value = "/admin/getSearch_non_regular", method = RequestMethod.POST)
	public ModelAndView getSearch_non_regular(ModelMap Mmap, HttpSession session, HttpServletRequest request,
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
					return new ModelAndView("redirect:Search_civilian_non_regular");
				}
			}
		}
		if (!roleAccess.equals("Unit")) {
			if (unit_name != "") {
				if (!valid.isUnit(unit_name)) {
					Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
					return new ModelAndView("redirect:Search_civilian_non_regular");
				}

//				if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//					Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//					return new ModelAndView("redirect:Search_civilian_non_regular");
//				}
			}
		}
		
		if (!valid.isOnlyAlphabet(first_name)) {
			Mmap.put("msg", " Name " + valid.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:Search_civilian_non_regular");
		}
		
		if (!valid.isvalidLength(first_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Name " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:Search_civilian_non_regular");
		}
		
		Boolean val = roledao.ScreenRedirect("Search_civilian_non_regular", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}

		Mmap.put("list", cdao.Search_Civilian_non_regular(roleSusNo, roleType, unit_sus_no, unit_name, first_name,
				status,personnel_no,cr_by,cr_date, session));
		Mmap.put("unit_sus_no", unit_sus_no);
		Mmap.put("unit_name", unit_name);
		Mmap.put("first_name", first_name);
		Mmap.put("status", status);
		Mmap.put("personnel_no", personnel_no);
		Mmap.put("roleType", roleType);
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		Mmap.put("cr_date1", cr_date);
		Mmap.put("cr_by1", cr_by);
		return new ModelAndView("non_regular_civilian_searchlTiles");
	}

	@RequestMapping(value = "/admin/non_regular_civilian_personnel_url", method = RequestMethod.GET)
	public ModelAndView non_regular_civilian_personnel_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("non_regular_civilian_personnel_url", roleid);

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

		Mmap.put("getGenderList", mcommon.getGenderList());
		Mmap.put("getNationalityList", mcommon.getNationalityList());
		Mmap.put("getNonRegNonEff", mcommon.getNonRegNonEff());
		Mmap.put("getReligionList", mcommon.getReligionList());
		Mmap.put("getMothertoungeList", mcommon.getMothertoungeList());
		Mmap.put("getCategoryList", mcommon.getCategoryList());
		Mmap.put("getClassificationOfTradesList", mcommon.getClassificationOfTradesList());
		Mmap.put("getCountryList", mcommon.getCountryList());
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getPayHeadList", mcommon.getPayHeadList());
		return new ModelAndView("non_regular_civilian_personnelTiles");
	}

	@RequestMapping(value = "/admin/non_regular_civilian_act", method = RequestMethod.POST)
	public ModelAndView non_regular_civilian_act(@ModelAttribute("civilian_details_cmd") TB_CIVILIAN_DETAILS obj,
			BindingResult result, ModelMap Mmap, HttpSession session, HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg1,MultipartHttpServletRequest mul)
					throws ParseException, IOException {


		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("non_regular_civilian_personnel_url", roleid);

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
		Date date = new Date();
		Date dob_dt = null;
		Date joining_date_gov_service_dt = null;
		Date designation_date_dt = null;

		Date date_non_effective_dt = null;
		Date dt_authority = null;
		String fname = "";
		String extension = "";
        String mobile_no = request.getParameter("mobile_no");
		String username = session.getAttribute("username").toString();
		String pan_no = request.getParameter("pan_no");
		String emp_no = request.getParameter("employee_no");
		String authority = request.getParameter("authority");
		String dt_of_authority = request.getParameter("dt_of_authority");
		String unit_sus_no = request.getParameter("unit_sus_no");
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String name1 = first_name + " " + middle_name + " " + last_name;
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String gender_hid = request.getParameter("gender_hid");
		String gender_other = request.getParameter("gender_other");
		String category_belongs = request.getParameter("category_belongs");
		String type = request.getParameter("civ_type");
		String joining_date_gov_service = request.getParameter("joining_date_gov_service");
		String pay_level = request.getParameter("pay_level");
		String pay_head_hid = request.getParameter("pay_head_hid");
		String pay_level_other = request.getParameter("pay_head_other");
		String country_original = request.getParameter("country_original");
		String country_original_hid = request.getParameter("country_original_hid");
		String state_original = request.getParameter("state_original");
		String state_original_hid = request.getParameter("original_state_hid");
		String district_original = request.getParameter("district_original");
		String district_original_hid = request.getParameter("district_original_hid");
		String civ_group = request.getParameter("civ_group");
		String tehsil_origin = request.getParameter("tehsil_origin");
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
		String original_country_other = request.getParameter("original_country_other");
		String original_state_other = request.getParameter("original_state_other");
		String original_district_other = request.getParameter("original_district_other");
		String original_tehshil_other = request.getParameter("original_tehshil_other");
		String present_country_other = request.getParameter("present_country_other");
		String present_state_other = request.getParameter("present_state_other");
		String present_district_other = request.getParameter("present_district_other");
		String present_tehshil_other = request.getParameter("present_tehshil_other");
		String religion_other = request.getParameter("religion_other");
		String mother_tongue_other = request.getParameter("mother_tongue_other");
		String nationality_other = request.getParameter("nationality_other");
		String coc_ch_id = request.getParameter("id");
		String non_effective = request.getParameter("non_effective");
		String date_non_effective = request.getParameter("date_non_effective");
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		String msg = "";


		String aadhar_card = "";
		if (!request.getParameter("pers_aadhar_no11").equals("") & !request.getParameter("pers_aadhar_no2").equals("")
				|| !request.getParameter("pers_aadhar_no3").equals("")) {
			String pers_aadhar_no11 = request.getParameter("pers_aadhar_no11");
			String pers_aadhar_no2 = request.getParameter("pers_aadhar_no2");
			String pers_aadhar_no3 = request.getParameter("pers_aadhar_no3");
			aadhar_card = pers_aadhar_no11 + pers_aadhar_no2 + pers_aadhar_no3;

			if (!pattern.matcher(aadhar_card).matches() == false) {
				Mmap.put("msg", "Please Enter Valid  Aadhaar Card No.");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		
		if (authority == null || authority.equals("")) {
			Mmap.put("msg", "Please Enter Authority");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		if (!valid.isValidAuth(authority)) {
			Mmap.put("msg", valid.isValidAuthMSG + "Authority");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}

		if (!valid.isvalidLength(authority, valid.authorityMax, valid.authorityMin)) {
			Mmap.put("msg", "Authority " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		
		if (dt_of_authority == null || dt_of_authority.equals("null") || dt_of_authority.equals("DD/MM/YYYY") || dt_of_authority.equals("")) {
			Mmap.put("msg", "Please Select Date of Authority");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
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
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				roleSusNo = unit_sus_no;
			}
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}

		if (unit_sus_no != "") {
			
			if (!valid.isOnlyAlphabetNumeric(unit_sus_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}

		if (emp_no.equals("") || emp_no.equals("null") || emp_no.equals(null)) {
			Mmap.put("msg", "Please Enter Employee Number");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		if (!valid.isValidAuth(emp_no)) {
			Mmap.put("msg", valid.isValidAuthMSG + "Employee No");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		if (!valid.isvalidLength(emp_no, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Employee No " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		
		if (mobile_no == null || mobile_no.trim().equals("")) {
			Mmap.put("msg", "Please Enter Mobile No.");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}

		
		if (first_name.equals("") || first_name.equals("null") || first_name.equals(null)) {
			Mmap.put("msg", "Please Enter First Name");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		if (!valid.isOnlyAlphabet(first_name)) {
			Mmap.put("msg", " First Name " + valid.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}

		if (!valid.isvalidLength(first_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "First Name " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}

		if (!valid.isOnlyAlphabet(middle_name)) {
			Mmap.put("msg", " Middle Name " + valid.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}

		if (!valid.isvalidLength(middle_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Middle Name " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}

		if (!valid.isOnlyAlphabet(last_name)) {
			Mmap.put("msg", " Last Name " + valid.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}

		if (!valid.isvalidLength(last_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Last Name " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		
		if (!valid.isValidDate(dob)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Birth");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		
		if (dob == null || dob.equals("null") || dob.equals("DD/MM/YYYY") || dob.equals("")) {
			Mmap.put("msg", "Please Select Date of Birth");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		} else {
			dob_dt = format.parse(dob);
		}
		
		if (mcommon.calculate_age(format.parse(dob), date) < 18
				|| mcommon.calculate_age(format.parse(dob), date) >= 60) {
			Mmap.put("msg", "Please Enter Age Above 18");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		if (obj.getGender() == 0) {
			Mmap.put("msg", "Please Select Gender");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		if (gender_hid.toUpperCase().equals("OTHER") || gender_hid.toUpperCase() == "OTHER") {
			if (gender_other == null || gender_other.trim().equals("") || gender_other.equals("null")) {
				Mmap.put("msg", "Please Enter Gender Other");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				obj.setGender_other(gender_other);
			}

			if (!valid.isOnlyAlphabet(gender_other)) {
				Mmap.put("msg", " Gender Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(gender_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Gender Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setGender_other("");
		}
		
		if (obj.getCategory_belongs() == 0) {
			Mmap.put("msg", "Please Select Category");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		if (type == null) {
			Mmap.put("msg", "Please Select Type");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		if (pay_head_hid.toUpperCase().equals("OTHER") || pay_head_hid.toUpperCase() == "OTHER") {
			if (pay_level_other == null || pay_level_other.equals("") || pay_level_other.equals("null")) {
				Mmap.put("msg", "Please Enter Pay Head Other");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				obj.setPay_level_other(pay_level_other.trim());
			}
			if (!valid.isOnlyAlphabet(pay_level_other)) {
				Mmap.put("msg", " Pay Head Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(pay_level_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Pay Head Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setPay_level_other("");
		}

		if (obj.getPay_level() == 0) {
			Mmap.put("msg", "Please Select Pay Level");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		
		if (!valid.isValidDate(joining_date_gov_service)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Joining Govt Service");
			return new ModelAndView("redirect:civilianRegularUrl");
		}
		
		if (joining_date_gov_service == null || joining_date_gov_service.equals("null")
				|| joining_date_gov_service.equals("DD/MM/YYYY") || dob.equals("")) {
			Mmap.put("msg", "Please Select Date of Joining");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		} else {
			joining_date_gov_service_dt = format.parse(joining_date_gov_service);
		}
		if (mcommon.CompareDate(joining_date_gov_service_dt, dob_dt) == 0) {
			Mmap.put("msg", "Date of Joining should Be Greater Than Date of Birth");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}

		
		if (religion_hid.toUpperCase().equals("OTHERS") || religion_hid.toUpperCase() == "OTHERS") {
			if (religion_other == null || religion_other.equals("") || religion_other.equals("null")) {
				Mmap.put("msg", "Please Enter Religion Other");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				obj.setReligion_other(religion_other);
			}
			if (!valid.isOnlyAlphabet(religion_other)) {
				Mmap.put("msg", " Religion Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(religion_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Religion Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setReligion_other("");
		}

		if (mother_tongue_hid.toUpperCase().equals("OTHERS") || mother_tongue_hid.toUpperCase() == "OTHERS") {
			if (mother_tongue_other == null || mother_tongue_other.equals("") || mother_tongue_other.equals("null")) {
				Mmap.put("msg", "Please Enter Mother Tongue Other");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				obj.setMother_tongue_other(mother_tongue_other.trim());
			}
			if (!valid.isOnlyAlphabet(mother_tongue_other)) {
				Mmap.put("msg", " Mother Tongue Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(mother_tongue_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Mother Tongue Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setMother_tongue_other("");
		}

		if (country_original == "0" || country_original == null || country_original.equals("null")) {
			Mmap.put("msg", "Please Select Original Country");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}

		if (country_original_hid.toUpperCase().equals("OTHERS") || country_original_hid.toUpperCase() == "OTHERS") {
			if (original_country_other == null || original_country_other.trim().equals("")
					|| original_country_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of Country Other");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				obj.setOriginal_country_other(original_country_other.trim());
			}
			if (!valid.isOnlyAlphabet(original_country_other)) {
				Mmap.put("msg", " Country Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(original_country_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Country Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setOriginal_country_other("");
		}
		
		if (state_original == "0" || state_original == null || state_original.equals("null")) {
			Mmap.put("msg", "Please Select Original State");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}

		if (state_original_hid.toUpperCase().equals("OTHERS") || state_original_hid.toUpperCase() == "OTHERS") {
			if (original_state_other == null || original_state_other.equals("")
					|| original_state_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of State Other");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				obj.setOriginal_state_other(original_state_other.trim());
			}
			if (!valid.isOnlyAlphabet(original_state_other)) {
				Mmap.put("msg", " State Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(original_state_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " State Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setOriginal_state_other("");
		}
		
		if (district_original_hid.toUpperCase().equals("OTHERS") || district_original_hid.toUpperCase() == "OTHERS") {
			if (original_district_other == null || original_district_other.equals("")
					|| original_district_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of District Other");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				obj.setOriginal_district_other(original_district_other.trim());
			}
			if (!valid.isOnlyAlphabet(original_district_other)) {
				Mmap.put("msg", " District Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(original_district_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " District Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setOriginal_district_other("");
		}

		if (tehsil_origin_hid.toUpperCase().equals("OTHERS") || tehsil_origin_hid.toUpperCase() == "OTHERS") {
			if (original_tehshil_other == null || original_tehshil_other.equals("")
					|| original_tehshil_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of Tehsil Other");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				obj.setOriginal_tehshil_other(original_tehshil_other.trim());
			}
			if (!valid.isOnlyAlphabet(original_tehshil_other)) {
				Mmap.put("msg", " Tehsil Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(original_tehshil_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Tehsil Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setOriginal_tehshil_other("");
		}
		// start present
		if (country_present == "0" || country_present == null || country_present.equals("null")) {
			Mmap.put("msg", "Please Select Present Country");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}
		if (country_present_hid.toUpperCase().equals("OTHERS") || country_present_hid.toUpperCase() == "OTHERS") {

			if (present_country_other == null || present_country_other.equals("")
					|| present_country_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of Country Other");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				obj.setPresent_country_other(present_country_other.trim());
			}
			if (!valid.isOnlyAlphabet(present_country_other)) {
				Mmap.put("msg", " Country Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(present_country_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Country Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setPresent_country_other("");
		}

		if (state_present == "0" || state_present == null || state_present.equals("null")) {
			Mmap.put("msg", "Please Select Present State");
			return new ModelAndView("redirect:non_regular_civilian_personnel_url");
		}

		if (state_present_hid.toUpperCase().equals("OTHERS") || state_present_hid.toUpperCase() == "OTHERS") {
			if (present_state_other == null || present_state_other.equals("") || present_state_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of State Other");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				obj.setPresent_state_other(present_state_other.trim());
			}
			if (!valid.isOnlyAlphabet(present_state_other)) {
				Mmap.put("msg", " State Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(present_state_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " State Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setPresent_state_other("");
		}
		
		if (district_present_hid.toUpperCase().equals("OTHERS") || district_present_hid.toUpperCase() == "OTHERS") {
			if (present_district_other == null || present_district_other.equals("")
					|| present_district_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of District Other");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				obj.setPresent_district_other(present_district_other.trim());
			}
			if (!valid.isOnlyAlphabet(present_district_other)) {
				Mmap.put("msg", " District Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(present_district_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " District Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setPresent_district_other("");
		}
		
		if (tehsil_present_hid.toUpperCase().equals("OTHERS") || tehsil_present_hid.toUpperCase() == "OTHERS") {
			if (present_tehshil_other == null || present_tehshil_other.equals("")
					|| present_tehshil_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of Tehsil Other");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				obj.setPresent_tehshil_other(present_tehshil_other.trim());
			}
			if (!valid.isOnlyAlphabet(present_tehshil_other)) {
				Mmap.put("msg", " Tehsil Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(present_tehshil_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Tehsil Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setPresent_tehshil_other("");
		}
		if (nationality_hid.toUpperCase().equals("OTHERS") || nationality_hid.toUpperCase() == "OTHERS") {
			if (nationality_other == null || nationality_other.equals("") || nationality_other.equals("null")) {
				Mmap.put("msg", "Please Enter Nationality Other");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				obj.setNationality_other(nationality_other.trim());
			}
			if (!valid.isOnlyAlphabet(nationality_other)) {
				Mmap.put("msg", " Nationality Other " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(nationality_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Nationality Other " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setNationality_other("");
		}

		if (eff.equals("yes")) {

			if (Integer.parseInt(non_effective) == 0) {
				Mmap.put("msg", "Please Select Non Effective");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}
			
			if (!valid.isValidDate(date_non_effective)) {
				Mmap.put("msg", valid.isValidDateMSG + "  Non Effective ");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			if (date_non_effective == null || date_non_effective.equals("null")
					|| date_non_effective.equals("DD/MM/YYYY") || date_non_effective.equals("")) {
				Mmap.put("msg", "Please Select Date of Non Effective");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			} else {
				date_non_effective_dt = format.parse(date_non_effective);
			}

			if (mcommon.CompareDate(date_non_effective_dt, joining_date_gov_service_dt) == 0) {
				Mmap.put("msg", "Date of Non Effective should Be Greater Than  Date of Joining Govt Service");
				return new ModelAndView("redirect:non_regular_civilian_personnel_url");
			}

			obj.setNon_effective(Integer.parseInt(non_effective));
			obj.setDate_non_effective(date_non_effective_dt);
		} else {
			obj.setNon_effective(0);
			obj.setDate_non_effective(null);
		}

				obj.setEmployee_no(emp_no);
		try {

			Query q0 = sessionhql
					.createQuery("select count(id) from TB_CIVILIAN_DETAILS where employee_no=:employee_no");
			q0.setParameter("employee_no", emp_no);

			Long c = (Long) q0.uniqueResult();
			if (Integer.parseInt(coc_ch_id) == 0) {
				if (c > 0) {
					Mmap.put("msg", "Employee No Already Exist.");
				}
				
				
				MultipartFile file = mul.getFile("identity_image");
				if (!file.getOriginalFilename().isEmpty()) {
						
						String upload_imgEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();	
				    	if(!upload_imgEXt.equals("jpg") && !upload_imgEXt.equals("jpeg") && !upload_imgEXt.equals("png")) {
				    		msg= "Only *.jpg, *.jpeg and *.png file extensions allowed";					
				    		Mmap.put("msg", msg);	
				    		return new ModelAndView("redirect:non_regular_civilian_personnel_url");
						}
				    	
				    	long fileSizeInBytes = file.getSize();
				    	if (fileSizeInBytes > 2 * 1024 * 1024) { // 2MB in bytes
				    	    msg = "Image Size Should Be 2MB Or Less";
				    	    Mmap.put("msg", msg);	
				    	    return new ModelAndView("redirect:non_regular_civilian_personnel_url");
				    	}
				    
						byte[] bytes = file.getBytes();
						String mnhFilePath = session.getAttribute("psgFilePath").toString() + "/Civilian/NonRegularIdentity";
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


				obj.setPan_no(pan_no.trim());
				obj.setAuthority(authority.trim());
				obj.setDt_of_authority(dt_authority);
				obj.setSus_no(roleSusNo.trim());
				obj.setFirst_name(first_name.trim());
				obj.setMiddle_name(middle_name.trim());
				obj.setLast_name(last_name.trim());
				obj.setFull_name(name1.trim());
				obj.setDob(dob_dt);
				obj.setGender(Integer.parseInt(gender));
				obj.setCiv_group(civ_group);
				obj.setCategory_belongs(Integer.parseInt(category_belongs));
				obj.setCiv_type(type);
				obj.setCivilian_status("NR");
				obj.setJoining_date_gov_service(joining_date_gov_service_dt);
				obj.setPay_level(Integer.parseInt(pay_level));
				if (pay_level_other != "" || pay_level_other != null) {
					obj.setPay_level_other(pay_level_other.trim());
				}
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
				if (gender_other != "" || gender_other != null) {
					obj.setGender_other(gender_other);
				}
				obj.setDesignation_date(designation_date_dt);
				obj.setCreated_by(username);
				obj.setCreated_date(date);
				obj.setStatus(0);
				obj.setIdentity_image(fname);
				obj.setMobile_no(mobile_no);

				int id = (int) sessionhql.save(obj);
				msg = Integer.toString(id);
				Mmap.put("msg", "Data Saved Successfully.");
			}

			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				tx.rollback();
				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldnï¿½t roll back transaction " + rbe);
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}

		return new ModelAndView("redirect:non_regular_civilian_personnel_url");
	}

	@RequestMapping(value = "/Edit_non_regular_civilian_personnel_url")
	public ModelAndView Edit_non_regular_civilian_personnel_url(@ModelAttribute("id2") String id, ModelMap Mmap,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			 Authentication authentication, HttpSession session) {
		
		int cid = 18;

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_non_regular", roleid);

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
		TB_CIVILIAN_DETAILS authDetails = cdao.getTB_CIVILIAN_DETAILSByid(Integer.parseInt(id));
		Mmap.put("civilian_details_cmd", authDetails);
		Mmap.put("msg", msg);
		Mmap.put("getGenderList", mcommon.getGenderList());
		Mmap.put("getNationalityList", mcommon.getNationalityList());
		Mmap.put("getNonRegNonEff", mcommon.getNonRegNonEff());
		Mmap.put("getReligionList", mcommon.getReligionList());
		Mmap.put("getMothertoungeList", mcommon.getMothertoungeList());
		Mmap.put("getCategoryList", mcommon.getCategoryList());
		Mmap.put("getClassificationOfTradesList", mcommon.getClassificationOfTradesList());
		Mmap.put("getCountryList", mcommon.getCountryList());
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getPayHeadList", mcommon.getPayHeadList());
	
		return new ModelAndView("edit_non_regular_civilian_personnelTiles");
	}

	@RequestMapping(value = "/Edit_non_regular_civilian_personnel_Action", method = RequestMethod.POST)
	public ModelAndView Edit_non_regular_civilian_personnel_Action(HttpServletRequest request, ModelMap model,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,MultipartHttpServletRequest mul)
					throws ParseException, IOException {


		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id"));

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_civilian_non_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		TB_CIVILIAN_DETAILS obj2 = (TB_CIVILIAN_DETAILS) session1.get(TB_CIVILIAN_DETAILS.class, id);
		session1.evict(obj2);
		Date date = new Date();
		Date dob_dt = null;
		Date joining_date_gov_service_dt = null;
		Date non_effective_date = null;
		Date date_of_authority = null;
		String fname = "";
		String extension = "";
        String mobile_no = request.getParameter("mobile_no");
		String authority = request.getParameter("authority");
		String dt_of_authority = request.getParameter("dt_of_authority");
		String unit_sus_no = request.getParameter("unit_sus_no");
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String emp_no = request.getParameter("employee_no");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String gender_other = request.getParameter("gender_other");
		String category_belongs = request.getParameter("category_belongs");
		String type = request.getParameter("civ_type");
		String pay_level = request.getParameter("pay_level");
		String joining_dt_gov_service = request.getParameter("date_of_joining");
		String religion = request.getParameter("religion");
		String mother_tongue = request.getParameter("mother_tongue");
		String religion_other = request.getParameter("religion_other");
		String mother_tongue_other = request.getParameter("mother_tongue_other");
		String aadhar_card = "";

		String pan_no = request.getParameter("pan_no");
		String country_original = request.getParameter("country_original");
		String state_original = request.getParameter("state_original");
		String district_original = request.getParameter("district_original");
		String tehsil_original = request.getParameter("tehsil_original");
		String original_country_other = request.getParameter("original_country_other");
		String original_state_other = request.getParameter("original_state_other");
		String original_dist_other = request.getParameter("original_district_other");
		String original_tehsil_other = request.getParameter("original_tehshil_other");
		String country_present = request.getParameter("country_present");
		String state_present = request.getParameter("state_present");
		String district_present = request.getParameter("district_present");
		String tehsil_present = request.getParameter("tehsil_present");
		String nationality = request.getParameter("nationality");
		String present_country_other = request.getParameter("present_country_other");
		String present_state_other = request.getParameter("present_state_other");
		String present_dist_other = request.getParameter("present_district_other");
		String present_tehsil_other = request.getParameter("present_tehshil_other");
		String nationality_other = request.getParameter("nationality_other");
		String country_original_hid = request.getParameter("country_original_hid");
		String state_original_hid = request.getParameter("original_state_hid");
		String district_original_hid = request.getParameter("district_original_hid");
		String tehsil_origin_hid = request.getParameter("original_tehsil_hid");
		String country_present_hid = request.getParameter("country1_hid");
		String state_present_hid = request.getParameter("present_state_hid");
		String district_present_hid = request.getParameter("present_district_hid");
		String pay_head_hid = request.getParameter("pay_head_hid");
		String tehsil_present_hid = request.getParameter("tehsil_present_hid");
		String nationality_hid = request.getParameter("nationality_hid");
		String religion_hid = request.getParameter("religion_hid");
		String mother_tongue_hid = request.getParameter("mother_tongue_hid");
		String classification_trade_hid = request.getParameter("classification_of_trade_hid");
		String gender_hid = request.getParameter("gender_hid");
		String pay_level_other = request.getParameter("pay_level_other");
		String eff = request.getParameter("eff");
		String non_effective = request.getParameter("non_effective");
		String non_effective_dt = request.getParameter("non_effective_date");
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		if (!roleAccess.equals("Unit")) {
			if (unit_sus_no.equals("")) {
				model.put("msg", "Please Enter Unit Sus No.");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				roleSusNo = unit_sus_no;
			}
			if (!valid.SusNoLength(unit_sus_no)) {
				model.put("msg", valid.SusNoMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}

		if (!request.getParameter("pers_aadhar_no11").equals("") & !request.getParameter("pers_aadhar_no2").equals("")
				|| !request.getParameter("pers_aadhar_no3").equals("")) {
			String pers_aadhar_no11 = request.getParameter("pers_aadhar_no11");
			String pers_aadhar_no2 = request.getParameter("pers_aadhar_no2");
			String pers_aadhar_no3 = request.getParameter("pers_aadhar_no3");
			aadhar_card = pers_aadhar_no11 + pers_aadhar_no2 + pers_aadhar_no3;

			if (!pattern.matcher(aadhar_card).matches() == false) {
				model.put("msg", "Please Enter Valid  Aadhaar Card No.");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}

		if (non_effective_dt != null && !non_effective_dt.trim().equals("") && !non_effective_dt.equals("DD/MM/YYYY")) {
			non_effective_date = format.parse(non_effective_dt);
		}
		
		if (authority == null || authority.equals("")) {
			model.put("msg", "Please Enter Authority");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}
		if (!valid.isValidAuth(authority)) {
			model.put("msg", valid.isValidAuthMSG + "Authority");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (!valid.isvalidLength(authority, valid.authorityMax, valid.authorityMin)) {
			model.put("msg", "Authority " + valid.isValidLengthMSG);
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}
		
		if (dt_of_authority == null || dt_of_authority.equals("null") || dt_of_authority.equals("DD/MM/YYYY") || dt_of_authority.equals("")) {
			model.put("msg", "Please Select Date of Authority");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (!dt_of_authority.equals("") || dt_of_authority != "") {

			if (!valid.isValidDate(dt_of_authority)) {
				model.put("msg", valid.isValidDateMSG + " of Authority");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				date_of_authority = format.parse(dt_of_authority);
			}
		}
		
		if (unit_sus_no != "") {
			
			if (!valid.isOnlyAlphabetNumeric(unit_sus_no)) {
				model.put("msg", valid.isOnlyAlphabetNumericMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
			
			if (!valid.SusNoLength(unit_sus_no)) {
				model.put("msg", valid.SusNoMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}

		if (emp_no.equals("") || emp_no.equals("null") || emp_no.equals(null)) {
			model.put("msg", "Please Enter Employee Number");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}
		
		if (!valid.isValidAuth(emp_no)) {
			model.put("msg", valid.isValidAuthMSG + "Employee No");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}
		
		if (!valid.isvalidLength(emp_no, valid.nameMax, valid.nameMin)) {
			model.put("msg", "Employee No " + valid.isValidLengthMSG);
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}
		
		if (mobile_no == null || mobile_no.trim().equals("")) {
			model.put("msg", "Please Enter Mobile No.");
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		
		if (first_name.equals("") || first_name.equals("null") || first_name.equals(null)) {
			model.put("msg", "Please Enter First Name");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}
		if (!valid.isOnlyAlphabet(first_name)) {
			model.put("msg", " First Name " + valid.isOnlyAlphabetMSG);
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (!valid.isvalidLength(first_name, valid.nameMax, valid.nameMin)) {
			model.put("msg", "First Name " + valid.isValidLengthMSG);
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (!valid.isOnlyAlphabet(middle_name)) {
			model.put("msg", " Middle Name " + valid.isOnlyAlphabetMSG);
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (!valid.isvalidLength(middle_name, valid.nameMax, valid.nameMin)) {
			model.put("msg", "Middle Name " + valid.isValidLengthMSG);
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (!valid.isOnlyAlphabet(last_name)) {
			model.put("msg", " Last Name " + valid.isOnlyAlphabetMSG);
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (!valid.isvalidLength(last_name, valid.nameMax, valid.nameMin)) {
			model.put("msg", "Last Name " + valid.isValidLengthMSG);
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}
		
		if (!valid.isValidDate(dob)) {
			model.put("msg", valid.isValidDateMSG + " of Birth");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}
		
		if (dob == null || dob.equals("null") || dob.equals("DD/MM/YYYY") || dob.equals("")) {
			model.put("msg", "Please Select Date of Birth");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		} else {
			dob_dt = format.parse(dob);
		}

		
				/*if (mcommon.calculate_age(format.parse(dob), date) < 18
						|| mcommon.calculate_age(format.parse(dob), date) >= 60) {
					model.put("msg", "Please Enter Age Above 18");
					model.put("id2", id);
					return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
				}*/
		if(eff.equals("no")) {
			if (mcommon.calculate_age(format.parse(dob), date) < 18
					|| mcommon.calculate_age(format.parse(dob), date) >= 60) {
				model.put("msg", "Please enter age above 18 Years and below 60 Years");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}

		if (gender == "0") {
			model.put("msg", "Please Select Gender");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (gender_hid.toUpperCase().equals("OTHER") || gender_hid.toUpperCase() == "OTHER") {
			if (gender_other == null || gender_other.trim().equals("") || gender_other.equals("null")) {
				model.put("msg", "Please Enter Gender Other");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				obj2.setGender_other(gender_other);
			}

			if (!valid.isOnlyAlphabet(gender_other)) {
				model.put("msg", " Gender Other " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(gender_other, valid.nameMax, valid.nameMin)) {
				model.put("msg", " Gender Other " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj2.setGender_other("");
		}

		if (category_belongs == "0") {
			model.put("msg", "Please Select Category");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (type == null) {
			model.put("msg", "Please Select Type");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (pay_level == "0") {
			model.put("msg", "Please Select Pay Level");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (pay_head_hid.toUpperCase().equals("OTHER") || pay_head_hid.toUpperCase() == "OTHER") {
			if (pay_level_other == null || pay_level_other.equals("") || pay_level_other.equals("null")) {
				model.put("msg", "Please Enter Pay Head Other");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				obj2.setPay_level_other(pay_level_other.trim());
			}
			if (!valid.isOnlyAlphabet(pay_level_other)) {
				model.put("msg", " Pay Head Other " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(pay_level_other, valid.nameMax, valid.nameMin)) {
				model.put("msg", " Pay Head Other " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj2.setPay_level_other("");
		}

		if (!valid.isValidDate(joining_dt_gov_service)) {
			model.put("msg", valid.isValidDateMSG + " of Joining Govt Service");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}
		
		if (joining_dt_gov_service == null || joining_dt_gov_service.equals("null")
				|| joining_dt_gov_service.equals("DD/MM/YYYY") || joining_dt_gov_service.equals("")) {
			model.put("msg", "Please Select Date of Joining");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		} else {
			joining_date_gov_service_dt = format.parse(joining_dt_gov_service);
		}

		if (mcommon.CompareDate(joining_date_gov_service_dt, dob_dt) == 0) {
			model.put("msg", "Date of Joining should Be Greater Than Date of Birth");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (religion_hid.toUpperCase().equals("OTHERS") || religion_hid.toUpperCase() == "OTHERS") {
			if (religion_other == null || religion_other.equals("") || religion_other.equals("null")) {
				model.put("msg", "Please Enter Religion Other");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				obj2.setReligion_other(religion_other);
			}

			if (!valid.isOnlyAlphabet(religion_other)) {
				model.put("msg", " Religion Other " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(religion_other, valid.nameMax, valid.nameMin)) {
				model.put("msg", " Religion Other " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj2.setReligion_other("");
		}

		if (mother_tongue_hid.toUpperCase().equals("OTHERS") || mother_tongue_hid.toUpperCase() == "OTHERS") {
			if (mother_tongue_other == null || mother_tongue_other.equals("") || mother_tongue_other.equals("null")) {
				model.put("msg", "Please Enter Mother Tongue Other");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				obj2.setMother_tongue_other(mother_tongue_other);
			}

			if (!valid.isOnlyAlphabet(mother_tongue_other)) {
				model.put("msg", " Mother Tongue Other " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(mother_tongue_other, valid.nameMax, valid.nameMin)) {
				model.put("msg", " Mother Tongue Other " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj2.setMother_tongue_other("");
		}


		if (country_original == "0" || country_original == null || country_original.equals("null")) {
			model.put("msg", "Please Select Original Country");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}
		
		if (country_original_hid.toUpperCase().equals("OTHERS") || country_original_hid.toUpperCase() == "OTHERS") {
			if (original_country_other == null || original_country_other.equals("")
					|| original_country_other.equals("null")) {
				model.put("msg", "Please Enter The Original Domicile of Country Other");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
			else {
				obj2.setOriginal_country_other(original_country_other);
			}

			
			if (!valid.isOnlyAlphabet(original_country_other)) {
				model.put("msg", " Country Other " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(original_country_other, valid.nameMax, valid.nameMin)) {
				model.put("msg", " Country Other " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj2.setOriginal_country_other("");
		}

		if (state_original == "0" || state_original == null || state_original.equals("null")) {
			model.put("msg", "Please Select Original State");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (state_original_hid.toUpperCase().equals("OTHERS") || state_original_hid.toUpperCase() == "OTHERS") {
			if (original_state_other == null || original_state_other.equals("")
					|| original_state_other.equals("null")) {
				model.put("msg", "Please Enter The Original Domicile of State Other");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				obj2.setOriginal_state_other(original_state_other);
			}

			if (!valid.isOnlyAlphabet(original_state_other)) {
				model.put("msg", " State Other " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(original_state_other, valid.nameMax, valid.nameMin)) {
				model.put("msg", " State Other " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj2.setOriginal_state_other("");
		}

		if (district_original_hid.toUpperCase().equals("OTHERS") || district_original_hid.toUpperCase() == "OTHERS") {
			if (original_dist_other == null || original_dist_other.equals("") || original_dist_other.equals("null")) {
				model.put("msg", "Please Enter The Original Domicile of District Other");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				obj2.setOriginal_district_other(original_dist_other);
			}

			if (!valid.isOnlyAlphabet(original_dist_other)) {
				model.put("msg", " District Other " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(original_dist_other, valid.nameMax, valid.nameMin)) {
				model.put("msg", " District Other " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj2.setOriginal_district_other("");
		}
		
		if (tehsil_origin_hid.toUpperCase().equals("OTHERS") || tehsil_origin_hid.toUpperCase() == "OTHERS") {
			if (original_tehsil_other == null || original_tehsil_other.equals("")
					|| original_tehsil_other.equals("null")) {
				model.put("msg", "Please Enter The Original Domicile of Tehsil Other");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				obj2.setOriginal_tehshil_other(original_tehsil_other);
			}

			if (!valid.isOnlyAlphabet(original_tehsil_other)) {
				model.put("msg", " Tehsil Other " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(original_tehsil_other, valid.nameMax, valid.nameMin)) {
				model.put("msg", " Tehsil Other " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj2.setOriginal_tehshil_other("");
		}
		// start present
		if (country_present == "0" || country_present == null || country_present.equals("null")) {
			model.put("msg", "Please Select Present Country");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}
		if (country_present_hid.toUpperCase().equals("OTHERS") || country_present_hid.toUpperCase() == "OTHERS") {

			if (present_country_other == null || present_country_other.equals("")
					|| present_country_other.equals("null")) {
				model.put("msg", "Please Enter The Present Domicile of Country Other");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				obj2.setPresent_country_other(present_country_other);
			}

			if (!valid.isOnlyAlphabet(present_country_other)) {
				model.put("msg", " Country Other " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(present_country_other, valid.nameMax, valid.nameMin)) {
				model.put("msg", " Country Other " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj2.setPresent_country_other("");
		}

		if (state_present == "0" || state_present == null || state_present.equals("null")) {
			model.put("msg", "Please Select Present State");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
		}

		if (state_present_hid.toUpperCase().equals("OTHERS") || state_present_hid.toUpperCase() == "OTHERS") {
			if (present_state_other == null || present_state_other.equals("") || present_state_other.equals("null")) {
				model.put("msg", "Please Enter The Present Domicile of State Other");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				obj2.setPresent_state_other(present_state_other);
			}

			if (!valid.isOnlyAlphabet(present_state_other)) {
				model.put("msg", " State Other " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(present_state_other, valid.nameMax, valid.nameMin)) {
				model.put("msg", " State Other " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj2.setPresent_state_other("");
		}
		
		if (district_present_hid.toUpperCase().equals("OTHERS") || district_present_hid.toUpperCase() == "OTHERS") {
			if (present_dist_other == null || present_dist_other.equals("") || present_dist_other.equals("null")) {
				model.put("msg", "Please Enter The Present Domicile of District Other");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				obj2.setPresent_district_other(present_dist_other);
			}

			if (!valid.isOnlyAlphabet(present_dist_other)) {
				model.put("msg", " District Other " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(present_dist_other, valid.nameMax, valid.nameMin)) {
				model.put("msg", " District Other " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj2.setPresent_district_other("");
		}
		if (tehsil_present_hid.toUpperCase().equals("OTHERS") || tehsil_present_hid.toUpperCase() == "OTHERS") {
			if (present_tehsil_other == null || present_tehsil_other.equals("")
					|| present_tehsil_other.equals("null")) {
				model.put("msg", "Please Enter The Present Domicile of Tehsil Other");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				obj2.setPresent_tehshil_other(present_tehsil_other);
			}

			if (!valid.isOnlyAlphabet(present_tehsil_other)) {
				model.put("msg", " Tehsil Other " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(present_tehsil_other, valid.nameMax, valid.nameMin)) {
				model.put("msg", " Tehsil Other " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj2.setPresent_tehshil_other("");
		}
		if (nationality_hid.toUpperCase().equals("OTHERS") || nationality_hid.toUpperCase() == "OTHERS") {
			if (nationality_other == null || nationality_other.equals("") || nationality_other.equals("null")) {
				model.put("msg", "Please Enter Nationality Other");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				obj2.setNationality_other(nationality_other);
			}

			if (!valid.isOnlyAlphabet(nationality_other)) {
				model.put("msg", " Nationality Other " + valid.isOnlyAlphabetMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(nationality_other, valid.nameMax, valid.nameMin)) {
				model.put("msg", " Nationality Other " + valid.isValidLengthMSG);
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj2.setNationality_other("");
		}

		if (eff.equals("yes")) {

			if (Integer.parseInt(non_effective) == 0) {
				model.put("msg", "Please Select Non Effective");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}
			
			
			if (!valid.isValidDate(non_effective_dt)) {
				model.put("msg", valid.isValidDateMSG + "  Non Effective ");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			if (non_effective_dt == null || non_effective_dt.equals("null") || non_effective_dt.equals("DD/MM/YYYY")
					|| non_effective_dt.equals("")) {
				model.put("msg", "Please Select Date of Non Effective");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			} else {
				non_effective_date = format.parse(non_effective_dt);
			}

			

			if (mcommon.CompareDate(non_effective_date, joining_date_gov_service_dt) == 0) {
				model.put("msg", "Date of Non Effective should Be Greater Than  Date of Joining Govt Service");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
			}

			obj2.setNon_effective(Integer.parseInt(non_effective));
			obj2.setDate_non_effective(non_effective_date);
		} else {
			obj2.setNon_effective(0);
			obj2.setDate_non_effective(null);
		}


		try {
			

			MultipartFile file = mul.getFile("identity_image");
				if (!file.getOriginalFilename().isEmpty()) {
						
						String upload_imgEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();	
				    	if(!upload_imgEXt.equals("jpg") && !upload_imgEXt.equals("jpeg") && !upload_imgEXt.equals("png")) {
				    		msg= "Only *.jpg, *.jpeg and *.png file extensions allowed";					
				    		model.put("msg", msg);	
				    		return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
						}
				    	
				    	long fileSizeInBytes = file.getSize();
				    	if (fileSizeInBytes > 2 * 1024 * 1024) { // 2MB in bytes
				    	    msg = "Image Size Should Be 2MB Or Less";
				    	    model.put("msg", msg);	
				    	    return new ModelAndView("redirect:Edit_non_regular_civilian_personnel_url");
				    	}
				    
						byte[] bytes = file.getBytes();
						String mnhFilePath = session.getAttribute("psgFilePath").toString() + "/Civilian/NonRegularIdentity";
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


			
			
			obj2.setAuthority(authority);
			obj2.setDt_of_authority(date_of_authority);
			obj2.setSus_no(roleSusNo);
			obj2.setEmployee_no(emp_no);
			obj2.setFirst_name(first_name);
			obj2.setMiddle_name(middle_name);
			obj2.setLast_name(last_name);

			obj2.setDob(dob_dt);
			obj2.setGender(Integer.parseInt(gender));
			obj2.setCategory_belongs(Integer.parseInt(category_belongs));
			obj2.setCiv_type(type);
			obj2.setPay_level(Integer.parseInt(pay_level));
			obj2.setJoining_date_gov_service(joining_date_gov_service_dt);
			obj2.setReligion(Integer.parseInt(religion));
			obj2.setMother_tongue(Integer.parseInt(mother_tongue));
			obj2.setAadhar_card(aadhar_card);
			obj2.setPan_no(pan_no);
			obj2.setCountry_original(Integer.parseInt(country_original));
			obj2.setState_original(Integer.parseInt(state_original));
			obj2.setDistrict_original(Integer.parseInt(district_original));
			obj2.setTehsil_origin(Integer.parseInt(tehsil_original));
			obj2.setCountry_present(Integer.parseInt(country_present));
			obj2.setState_present(Integer.parseInt(state_present));
			obj2.setDistrict_present(Integer.parseInt(district_present));
			obj2.setTehsil_present(Integer.parseInt(tehsil_present));
			obj2.setNationality(Integer.parseInt(nationality));
			obj2.setModified_by(username);
			obj2.setModified_date(date);
			obj2.setStatus(0);
			obj2.setMobile_no(mobile_no);
			if (StringUtils.hasText(fname)) { // this check notnull,notempty,no space
			    obj2.setIdentity_image(fname);
			}
			session1.update(obj2);
			tx.commit();

			model.put("msg", "Data Updated Successfully.");
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				e.printStackTrace();
				model.put("msg", "Couldn t roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}

		return new ModelAndView("redirect:Search_civilian_non_regular");
	}

	@RequestMapping(value = "/admin/deleteEduUrl", method = RequestMethod.POST)
	public ModelAndView deleteEduUrl(@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request, HttpSession session, ModelMap model) {
		try {

			String roleid = session.getAttribute("roleid").toString();

			Boolean val = roledao.ScreenRedirect("Search_civilian_non_regular", roleid);

			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
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
		return new ModelAndView("redirect:Search_civilian_non_regular");
	}

	@RequestMapping(value = "/admin/non_regular_report", method = RequestMethod.GET)
	public ModelAndView non_regular_report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String unit_sus_no = request.getParameter("roleSusNo");
		String first_name = request.getParameter("first_name1");

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("non_regular_report", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("list", cdao.Report_Civilian_regular(unit_sus_no,first_name));
		// Mmap.put("msg", msg);
		return new ModelAndView("non_regular_civilian_ReportlTiles");
	}
	
	@RequestMapping(value = "/admin/non_regular_report_search", method = RequestMethod.POST)
	public ModelAndView non_regular_report_search(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String unit_sus_no = request.getParameter("unit_sus_no1");
		String first_name = request.getParameter("first_name1");
		String unit_posted_to = request.getParameter("unit_posted_to1");
		

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("non_regular_report", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("list", cdao.Report_Civilian_regular(unit_sus_no,first_name));
		Mmap.put("unit_sus_no", unit_sus_no);
		Mmap.put("first_name", first_name);
		Mmap.put("unit_posted_to", unit_posted_to);
		// Mmap.put("msg", msg);
		return new ModelAndView("non_regular_civilian_ReportlTiles");
	}

	// ===================================view===========================================//

	@RequestMapping(value = "/view_non_regular_civilian_personnel_url", method = RequestMethod.POST)
	public ModelAndView view_non_regular_civilian_personnel_url(@ModelAttribute("id3") String id, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "unit_sus_no2", required = false) String unit_sus_no,
			@RequestParam(value = "unit_name2", required = false) String unit_name,
			@RequestParam(value = "first_name2", required = false) String first_name,
			@RequestParam(value = "status2", required = false) String status, Authentication authentication,HttpServletRequest request,
			HttpSession session) {
		int cid = 18;

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_non_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}


		TB_CIVILIAN_DETAILS authDetails = cdao.getTB_CIVILIAN_DETAILSByid(Integer.parseInt(id));

		List<Map<String, Object>> list = cdao.getApproveDataForviewnonregular(Integer.parseInt(id));

		Mmap.put("listfull", list);

		Mmap.put("civilian_details_cmd", authDetails);
		Mmap.put("msg", msg);
		Mmap.put("getGenderList", mcommon.getGenderList());
		Mmap.put("getNationalityList", mcommon.getNationalityList());
		Mmap.put("getNonRegNonEff", mcommon.getNonRegNonEff());
		Mmap.put("getReligionList", mcommon.getReligionList());
		Mmap.put("getMothertoungeList", mcommon.getMothertoungeList());
		Mmap.put("getClassificationOfTradesList", mcommon.getClassificationOfTradesList());
		Mmap.put("getCountryList", mcommon.getCountryList());
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getPayHeadList", mcommon.getPayHeadList());
		Mmap.put("unit_sus_no2", unit_sus_no);
		Mmap.put("unit_name2", unit_name);
		Mmap.put("first_name2", first_name);
		Mmap.put("status2", status);
		return new ModelAndView("view_non_regular_civilianTiles");
	}

	@RequestMapping(value = "/ApproveviewcivilianNONRegularUrl",method = RequestMethod.POST)
	public ModelAndView ApproveviewcivilianNONRegularUrl(@ModelAttribute("id4") String id, ModelMap Mmap,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session) {
		
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_non_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
		List<Map<String, Object>> list = cdao.getApproveDataForviewnonregular(Integer.parseInt(id));
		Mmap.put("listfull", list);
		TB_CIVILIAN_DETAILS authDetails = cdao.getTB_CIVILIAN_DETAILSByid(Integer.parseInt(id));
		Mmap.put("civilian_details_cmd", authDetails); 
		Mmap.put("msg", msg);
		Mmap.put("getGenderList", mcommon.getGenderList());
		Mmap.put("getMothertoungeList", mcommon.getMothertoungeList());
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getPayLevelList", mcommon.getPayLevelList());
		Mmap.put("getReligionList", mcommon.getReligionList());
		Mmap.put("getNationalityList", mcommon.getNationalityList());
		Mmap.put("getRegNonEff", mcommon.getRegNonEff());
		Mmap.put("getCadre", mcommon.getCadre());
		Mmap.put("getCategoryList", mcommon.getCategoryList());
		Mmap.put("getClassificationOfTradesList", mcommon.getClassificationOfTradesList());
		Mmap.put("getCountryList", mcommon.getCountryList());
		Mmap.put("getDesignationList", mcommon.getDesignationList());
		Mmap.put("getExservicemenList", mcommon.getExservicemenList());
		Mmap.put("getCivilianTypeList", mcommon.getCivilianTypeList());
		Mmap.put("getServiceStatusList", mcommon.getServiceStatusList());
		Mmap.put("getPersonWithDisabilityList", mcommon.getPersonWithDisabilityList());
		Mmap.put("getClassificationOfServiceList", mcommon.getClassificationOfServiceList());
		Mmap.put("getCadre", mcommon.getCadre());
		return new ModelAndView("ApproveviewcivilianNonRegularTiles");
	}

	@RequestMapping(value = "/viewcivilianNonRegularAction", method = RequestMethod.POST)
	public ModelAndView viewcivilianNonRegularAction(@ModelAttribute("civilian_details_cmd") TB_CIVILIAN_DETAILS c,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_civilian_non_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
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
				session1.save(m);

				TB_POSTING_IN_OUT_CIVILIAN p = new TB_POSTING_IN_OUT_CIVILIAN();

				p.setDt_of_tos(obj.getJoining_date_gov_service());
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
				obj2.setFull_name(c.getFull_name());
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
				if (obj.getNon_effective() != 0) {
					obj2.setStatus(4);
				}
				else {
					obj2.setStatus(1);
				}
//				obj2.setStatus(obj.getStatus());
				obj2.setEmployee_no(obj.getEmployee_no());
				obj2.setCivilian_status(civi_status);
				obj2.setModified_by(username);
				obj2.setModified_date(date);

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
				model.put("msg", "Couldnï¿½t roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}

		return new ModelAndView("redirect:Search_civilian_non_regular");
	}

	@RequestMapping(value = "/Reject_Non_Civilian", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Reject_Non_Civilian(@ModelAttribute("id4") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "reject_remarks4", required = false) String reject_remarks, Authentication authentication) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String username = session.getAttribute("username").toString();

		try {

			String hql = "update TB_CIVILIAN_DETAILS set reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("reject_remarks", reject_remarks).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3).setInteger("id", id);
			msg = query.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			model.put("msg", msg);
			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return new ModelAndView("redirect:Search_civilian_non_regular");
	}

	@RequestMapping(value = "/App_Edit_non_regular_civilian_personnel_url")
	public ModelAndView App_Edit_non_regular_civilian_personnel_url(@ModelAttribute("id5") String id, ModelMap Mmap,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,

			Authentication authentication, HttpSession session) {

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Mmap.put("roleSusNo", roleSusNo);
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_civilian_non_regular", roleid);

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
		Mmap.put("getGenderList", mcommon.getGenderList());
		Mmap.put("getNationalityList", mcommon.getNationalityList());
		Mmap.put("getNonRegNonEff", mcommon.getNonRegNonEff());
		Mmap.put("getReligionList", mcommon.getReligionList());
		Mmap.put("getMothertoungeList", mcommon.getMothertoungeList());
		Mmap.put("getCategoryList", mcommon.getCategoryList());
		Mmap.put("getClassificationOfTradesList", mcommon.getClassificationOfTradesList());
		Mmap.put("getCountryList", mcommon.getCountryList());
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getPayHeadList", mcommon.getPayHeadList());
		return new ModelAndView("app_edit_non_regular_civilian_personnelTiles");
	}

	///////////// edit//

	@RequestMapping(value = "/admin/app_edit_non_regular_civilian_act", method = RequestMethod.POST)
	public ModelAndView app_edit_non_regular_civilian_act(
			 ModelMap Mmap,
			HttpSession session, HttpServletRequest request,MultipartHttpServletRequest mul)
					throws ParseException, IOException {


		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("non_regular_civilian_personnel_url", roleid);

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

		String fname = "";
		String extension = "";
		String mobile_no = request.getParameter("mobile_no");
		int id = Integer.parseInt(request.getParameter("id"));
		int status = Integer.parseInt(request.getParameter("status"));
		String username = session.getAttribute("username").toString();
		String pan_no = request.getParameter("pan_no");
		String emp_no = request.getParameter("employee_no");
		String authority = request.getParameter("authority");
		String dt_of_authority = request.getParameter("dt_of_authority");
		String unit_sus_no = request.getParameter("unit_sus_no");
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String name1 = first_name + " " + middle_name + " " + last_name;
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String gender_hid = request.getParameter("gender_hid");
		String gender_other = request.getParameter("gender_other");
		String category_belongs = request.getParameter("category_belongs");
		String type = request.getParameter("civ_type");
		String joining_date_gov_service = request.getParameter("date_of_joining");
		String pay_level = request.getParameter("pay_level");
		String country_original = request.getParameter("country_original");
		String country_original_hid = request.getParameter("country_original_hid");
		String state_original = request.getParameter("state_original");
		String state_original_hid = request.getParameter("original_state_hid");
		String district_original = request.getParameter("district_original");
		String district_original_hid = request.getParameter("district_original_hid");
		String civ_group = request.getParameter("civ_group");
		String tehsil_origin = request.getParameter("tehsil_original");
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
		String original_country_other = request.getParameter("original_country_other");
		String original_state_other = request.getParameter("original_state_other");
		String original_district_other = request.getParameter("original_district_other");
		String original_tehshil_other = request.getParameter("original_tehshil_other");
		String present_country_other = request.getParameter("present_country_other");
		String present_state_other = request.getParameter("present_state_other");
		String present_district_other = request.getParameter("present_district_other");
		String present_tehshil_other = request.getParameter("present_tehshil_other");
		String religion_other = request.getParameter("religion_other");
		String mother_tongue_other = request.getParameter("mother_tongue_other");
		String nationality_other = request.getParameter("nationality_other");
		String non_effective = request.getParameter("non_effective");
		String date_non_effective = request.getParameter("non_effective_date");
		String main_id = request.getParameter("main_id");
		String coc_ch_id = request.getParameter("id");
		String pay_head_hid = request.getParameter("pay_head_hid");
		String pay_level_other = request.getParameter("pay_level_other");
		String aadhar_card = "";
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		if (!roleAccess.equals("Unit")) {
			if (unit_sus_no.equals("")) {
				Mmap.put("msg", "Please Enter Unit Sus No.");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				roleSusNo = unit_sus_no;
			}
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		
		TB_CIVILIAN_DETAILS obj =  new TB_CIVILIAN_DETAILS();
		
		if (!request.getParameter("pers_aadhar_no11").equals("") & !request.getParameter("pers_aadhar_no2").equals("")
				|| !request.getParameter("pers_aadhar_no3").equals("")) {
			String pers_aadhar_no11 = request.getParameter("pers_aadhar_no11");
			String pers_aadhar_no2 = request.getParameter("pers_aadhar_no2");
			String pers_aadhar_no3 = request.getParameter("pers_aadhar_no3");
			aadhar_card = pers_aadhar_no11 + pers_aadhar_no2 + pers_aadhar_no3;

			if (!pattern.matcher(aadhar_card).matches() == false) {
				Mmap.put("msg", "Please Enter Valid  Aadhaar Card No.");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		
		if (authority == null || authority.equals("")) {
			Mmap.put("msg", "Please Enter Authority");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		if (!valid.isValidAuth(authority)) {
			Mmap.put("msg", valid.isValidAuthMSG + "Authority");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}

		if (!valid.isvalidLength(authority, valid.authorityMax, valid.authorityMin)) {
			Mmap.put("msg", "Authority " + valid.isValidLengthMSG);
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		
		if (dt_of_authority == null || dt_of_authority.equals("null") || dt_of_authority.equals("DD/MM/YYYY") || dt_of_authority.equals("")) {
			Mmap.put("msg", "Please Select Date of Authority");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}

		if (!dt_of_authority.equals("") || dt_of_authority != "") {

			if (!valid.isValidDate(dt_of_authority)) {
				Mmap.put("msg", valid.isValidDateMSG + " of Authority");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				dt_authority = format.parse(dt_of_authority);
			}
		}
		
		if (unit_sus_no != "") {
			
			if (!valid.isOnlyAlphabetNumeric(unit_sus_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
			
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}

		if (emp_no.equals("") || emp_no.equals("null") || emp_no.equals(null)) {
			Mmap.put("msg", "Please Enter Employee Number");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		if (!valid.isValidAuth(emp_no)) {
			Mmap.put("msg", valid.isValidAuthMSG + "Employee No");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		if (!valid.isvalidLength(emp_no, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Employee No " + valid.isValidLengthMSG);
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		
		if (mobile_no == null || mobile_no.trim().equals("")) {
			Mmap.put("msg", "Please Enter Mobile No.");
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}

		
		if (first_name.equals("") || first_name.equals("null") || first_name.equals(null)) {
			Mmap.put("msg", "Please Enter First Name");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		
		if (!valid.isOnlyAlphabet(first_name)) {
			Mmap.put("msg", " First Name " + valid.isOnlyAlphabetMSG);
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}

		if (!valid.isvalidLength(first_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "First Name " + valid.isValidLengthMSG);
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		
		if (!valid.isOnlyAlphabet(middle_name)) {
			Mmap.put("msg", " Middle Name " + valid.isOnlyAlphabetMSG);
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}

		if (!valid.isvalidLength(middle_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Middle Name " + valid.isValidLengthMSG);
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}

		if (!valid.isOnlyAlphabet(last_name)) {
			Mmap.put("msg", " Last Name " + valid.isOnlyAlphabetMSG);
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}

		if (!valid.isvalidLength(last_name, valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Last Name " + valid.isValidLengthMSG);
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		
		if (!valid.isValidDate(dob)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Birth");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		
		
		if (dob == null || dob.equals("null") || dob.equals("DD/MM/YYYY") || dob.equals("")) {
			Mmap.put("msg", "Please Select Date of Birth");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		} else {
			dob_dt = format.parse(dob);
		}
		
		

		if (mcommon.calculate_age(format.parse(dob), date) < 18
				|| mcommon.calculate_age(format.parse(dob), date) >= 60) {
			Mmap.put("msg", "Please Enter Age Above 18");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		
		if (gender == "0") {
			Mmap.put("msg", "Please Select Gender");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		
		if (gender_hid.toUpperCase().equals("OTHER") || gender_hid.toUpperCase() == "OTHER") {
			if (gender_other == null || gender_other.trim().equals("") || gender_other.equals("null")) {
				Mmap.put("msg", "Please Enter Gender Other");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				obj.setGender_other(gender_other.trim());
			}
			
			if (!valid.isOnlyAlphabet(gender_other)) {
				Mmap.put("msg", " Gender Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(gender_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Gender Other " + valid.isValidLengthMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setGender_other("");
		}
		
		
		if (category_belongs == "0") {
			Mmap.put("msg", "Please Select Category");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}

		if (type == null) {
			Mmap.put("msg", "Please Select Type");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}

		if (pay_level == "0") {
			Mmap.put("msg", "Please Select Pay Level");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		
		if (pay_head_hid.toUpperCase().equals("OTHER") || pay_head_hid.toUpperCase() == "OTHER") {
			if (pay_level_other == null || pay_level_other.equals("") || pay_level_other.equals("null")) {
				Mmap.put("msg", "Please Enter Pay Head Other");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				obj.setPay_level_other(pay_level_other.trim());
			}
			if (!valid.isOnlyAlphabet(pay_level_other)) {
				Mmap.put("msg", " Pay Head Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(pay_level_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Pay Head Other " + valid.isValidLengthMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setPay_level_other("");
		}
		
		if (!valid.isValidDate(joining_date_gov_service)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Joining Govt Service");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		
		if (joining_date_gov_service == null || joining_date_gov_service.equals("null")
				|| joining_date_gov_service.equals("DD/MM/YYYY") || joining_date_gov_service.equals("")) {
			Mmap.put("msg", "Please Select Date of Joining");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		} else {
			joining_date_gov_service_dt = format.parse(joining_date_gov_service);
		}
		if (mcommon.CompareDate(joining_date_gov_service_dt, dob_dt) == 0) {
			Mmap.put("msg", "Date of Joining should Be Greater Than Date of Birth");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		
		if (religion_hid.toUpperCase().equals("OTHERS") || religion_hid.toUpperCase() == "OTHERS") {
			if (religion_other == null || religion_other.equals("") || religion_other.equals("null")) {
				Mmap.put("msg", "Please Enter Religion Other");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				obj.setReligion_other(religion_other.trim());
			}
			
			if (!valid.isOnlyAlphabet(religion_other)) {
				Mmap.put("msg", " Religion Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(religion_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Religion Other " + valid.isValidLengthMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setReligion_other("");
		}
		
		
		if (mother_tongue_hid.toUpperCase().equals("OTHERS") || mother_tongue_hid.toUpperCase() == "OTHERS") {
			if (mother_tongue_other == null || mother_tongue_other.equals("") || mother_tongue_other.equals("null")) {
				Mmap.put("msg", "Please Enter Mother Tongue Other");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				obj.setMother_tongue_other(mother_tongue_other.trim());
			}
			
			if (!valid.isOnlyAlphabet(mother_tongue_other)) {
				Mmap.put("msg", " Mother Tongue Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(mother_tongue_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Mother Tongue Other " + valid.isValidLengthMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setMother_tongue_other("");
		}
		

		if (country_original == "0" || country_original == null || country_original.equals("null")) {
			Mmap.put("msg", "Please Select Original Country");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		if (country_original_hid.toUpperCase().equals("OTHERS") || country_original_hid.toUpperCase() == "OTHERS") {
			if (original_country_other == null || original_country_other.equals("")
					|| original_country_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of Country Other");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				obj.setOriginal_country_other(original_country_other.trim());
			}
			
			if (!valid.isOnlyAlphabet(original_country_other)) {
				Mmap.put("msg", " Country Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(original_country_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Country Other " + valid.isValidLengthMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setOriginal_country_other("");
		}

		if (state_original == "0" || state_original == null || state_original.equals("null")) {
			Mmap.put("msg", "Please Select Original State");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}

		if (state_original_hid.toUpperCase().equals("OTHERS") || state_original_hid.toUpperCase() == "OTHERS") {
			if (original_state_other == null || original_state_other.equals("")
					|| original_state_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of State Other");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				obj.setOriginal_state_other(original_state_other.trim());
			}
			
			
			if (!valid.isOnlyAlphabet(original_state_other)) {
				Mmap.put("msg", " State Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(original_state_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " State Other " + valid.isValidLengthMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
			
		}
		else {
			obj.setOriginal_state_other("");
		}
		
		if (district_original_hid.toUpperCase().equals("OTHERS") || district_original_hid.toUpperCase() == "OTHERS") {
			if (original_district_other == null || original_district_other.equals("")
					|| original_district_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of District Other");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				obj.setOriginal_district_other(original_district_other.trim());
			}
			
			if (!valid.isOnlyAlphabet(original_district_other)) {
				Mmap.put("msg", " State Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(original_district_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " State Other " + valid.isValidLengthMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setOriginal_district_other("");
		}

		if (tehsil_origin_hid.toUpperCase().equals("OTHERS") || tehsil_origin_hid.toUpperCase() == "OTHERS") {
			if (original_tehshil_other == null || original_tehshil_other.equals("")
					|| original_tehshil_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Original Domicile of Tehsil Other");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				obj.setOriginal_tehshil_other(original_tehshil_other.trim());
			}
			
			if (!valid.isOnlyAlphabet(original_tehshil_other)) {
				Mmap.put("msg", " Tehsil Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(original_tehshil_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Tehsil Other " + valid.isValidLengthMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setOriginal_tehshil_other("");
		}
		
		// start present
		if (country_present == "0" || country_present == null || country_present.equals("null")) {
			Mmap.put("msg", "Please Select Present Country");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}
		if (country_present_hid.toUpperCase().equals("OTHERS") || country_present_hid.toUpperCase() == "OTHERS") {

			if (present_country_other == null || present_country_other.equals("")
					|| present_country_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of Country Other");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				obj.setPresent_country_other(present_country_other.trim());
			}
			
			if (!valid.isOnlyAlphabet(present_country_other)) {
				Mmap.put("msg", " Country Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(present_country_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Country Other " + valid.isValidLengthMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setPresent_country_other("");
		}

		if (state_present == "0" || state_present == null || state_present.equals("null")) {
			Mmap.put("msg", "Please Select Present State");
			Mmap.put("id5", id);
			return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
		}

		if (state_present_hid.toUpperCase().equals("OTHERS") || state_present_hid.toUpperCase() == "OTHERS") {
			if (present_state_other == null || present_state_other.equals("") || present_state_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of State Other");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				obj.setPresent_state_other(present_state_other.trim());
			}
			
			if (!valid.isOnlyAlphabet(present_state_other)) {
				Mmap.put("msg", " State Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(present_state_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " State Other " + valid.isValidLengthMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setPresent_state_other("");
		}
		
		if (district_present_hid.toUpperCase().equals("OTHERS") || district_present_hid.toUpperCase() == "OTHERS") {
			if (present_district_other == null || present_district_other.equals("")
					|| present_district_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of District Other");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				obj.setPresent_district_other(present_district_other.trim());
			}
			
			if (!valid.isOnlyAlphabet(present_district_other)) {
				Mmap.put("msg", " District Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(present_district_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " District Other " + valid.isValidLengthMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setPresent_district_other("");
		}
		
		if (tehsil_present_hid.toUpperCase().equals("OTHERS") || tehsil_present_hid.toUpperCase() == "OTHERS") {
			if (present_tehshil_other == null || present_tehshil_other.equals("")
					|| present_tehshil_other.equals("null")) {
				Mmap.put("msg", "Please Enter The Present Domicile of Tehsil Other");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				obj.setPresent_tehshil_other(present_tehshil_other.trim());
			}
			
			if (!valid.isOnlyAlphabet(present_tehshil_other)) {
				Mmap.put("msg", " Tehsil Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(present_tehshil_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Tehsil Other " + valid.isValidLengthMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setPresent_tehshil_other("");
		}
		
		if (nationality_hid.toUpperCase().equals("OTHERS") || nationality_hid.toUpperCase() == "OTHERS") {
			if (nationality_other == null || nationality_other.equals("") || nationality_other.equals("null")) {
				Mmap.put("msg", "Please Enter Nationality Other");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				obj.setNationality_other(nationality_other.trim());
			}
			
			if (!valid.isOnlyAlphabet(nationality_other)) {
				Mmap.put("msg", " Nationality Other " + valid.isOnlyAlphabetMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isvalidLength(nationality_other, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", " Nationality Other " + valid.isValidLengthMSG);
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
		}
		else {
			obj.setNationality_other("");
		}
		
		if (eff.equals("yes")) {
			if (Integer.parseInt(non_effective) == 0) {
				Mmap.put("msg", "Please Select Non Effective");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}

			if (!valid.isValidDate(date_non_effective)) {
				Mmap.put("msg", valid.isValidDateMSG + "  Non Effective ");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
			
			if (date_non_effective == null || date_non_effective.equals("null")
					|| date_non_effective.equals("DD/MM/YYYY") || date_non_effective.equals("")) {
				Mmap.put("msg", "Please Select Date of Non Effective");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			} else {
				date_non_effective_dt = format.parse(date_non_effective);
			}
			
			if (mcommon.CompareDate(date_non_effective_dt, joining_date_gov_service_dt) == 0) {
				Mmap.put("msg", "Date of Non Effective should Be Greater Than  Date of Joining Govt Service");
				Mmap.put("id5", id);
				return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			}
			obj.setNon_effective(Integer.parseInt(non_effective));
			obj.setDate_non_effective(date_non_effective_dt);
		} else {
			obj.setNon_effective(0);
			obj.setDate_non_effective(null);
		}
		String msg = "";
		
		try {
			
			MultipartFile file = mul.getFile("identity_image");
			if (!file.getOriginalFilename().isEmpty()) {
					
					String upload_imgEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();	
			    	if(!upload_imgEXt.equals("jpg") && !upload_imgEXt.equals("jpeg") && !upload_imgEXt.equals("png")) {
			    		msg= "Only *.jpg, *.jpeg and *.png file extensions allowed";					
			    		Mmap.put("msg", msg);	
			    		return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
					}
			    	
			    	long fileSizeInBytes = file.getSize();
			    	if (fileSizeInBytes > 2 * 1024 * 1024) { // 2MB in bytes
			    	    msg = "Image Size Should Be 2MB Or Less";
			    	    Mmap.put("msg", msg);	
			    	    return new ModelAndView("redirect:App_Edit_non_regular_civilian_personnel_url");
			    	}
			    
					byte[] bytes = file.getBytes();
					String mnhFilePath = session.getAttribute("psgFilePath").toString() + "/Civilian/NonRegularIdentity";
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


			
			
			obj.setEmployee_no(emp_no.trim());
			obj.setPan_no(pan_no.trim());
			obj.setAuthority(authority.trim());
			obj.setDt_of_authority(dt_authority);
			obj.setSus_no(roleSusNo);
			obj.setFirst_name(first_name.trim());
			obj.setMiddle_name(middle_name.trim());
			obj.setLast_name(last_name.trim());
			obj.setFull_name(name1.trim());
			obj.setDob(dob_dt);
			obj.setGender(Integer.parseInt(gender));
			obj.setCiv_group(civ_group);
			obj.setCategory_belongs(Integer.parseInt(category_belongs));
			obj.setCiv_type(type);
			obj.setCivilian_status("NR");
			obj.setJoining_date_gov_service(joining_date_gov_service_dt);
			obj.setPay_level(Integer.parseInt(pay_level));
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
			obj.setAadhar_card(aadhar_card);
			obj.setMain_id(Integer.parseInt(main_id));
			obj.setDesignation_date(designation_date_dt);
			obj.setMobile_no(mobile_no);
			if (StringUtils.hasText(fname)) { // this check notnull,notempty,no space
				obj.setIdentity_image(fname);
			}else {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();		
				TB_CIVILIAN_DETAILS civilian = (TB_CIVILIAN_DETAILS) sessionHQL.get(TB_CIVILIAN_DETAILS.class, id);
				String identityImage = civilian.getIdentity_image();
				obj.setIdentity_image(identityImage);
			}

			Query q0 = sessionhql
					.createQuery("select count(id) from TB_CIVILIAN_DETAILS where employee_no=:employee_no and status='0'");
			q0.setParameter("employee_no", emp_no);

			Long c = (Long) q0.uniqueResult();
			
			if(c == 0  ) {
				obj.setModified_by(username);
				obj.setModified_date(date);
				obj.setStatus(0);
				sessionhql.save(obj);
				///parth
				if (status==3) {
					String hql1 = "update TB_CIVILIAN_DETAILS set status=:status where  main_id=:main_id and status = '3' ";

					Query query1 = sessionhql.createQuery(hql1).setInteger("status", 2).setInteger("main_id",Integer.parseInt(main_id));

					msg = query1.executeUpdate() > 0 ? "1" : "0";
					sessionhql.flush();
					sessionhql.clear();
				}
				Mmap.put("msg", "Data Updated Successfully.");
			}
			else {
				Mmap.put("msg", "Data Already in Pending");
			}

			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				tx.rollback();
				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldn't �t roll back transaction " + rbe);
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return new ModelAndView("redirect:Search_civilian_non_regular");
	}

	@RequestMapping(value = "/CancelNonUrl", method = RequestMethod.POST)
	public @ResponseBody ModelAndView CancelNonUrl(@ModelAttribute("id7") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_civilian_non_regular", roleid);

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
		return new ModelAndView("redirect:Search_civilian_non_regular");
	}

	@RequestMapping(value = "/AppNonCancelUrl", method = RequestMethod.POST)
	public @ResponseBody ModelAndView AppNonCancelUrl(@ModelAttribute("id8") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
//KAJAL 1111
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_civilian_non_regular", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String username = session.getAttribute("username").toString();

//		

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
				String hql1 = "update TB_CIVILIAN_MAIN set modified_by=:modified_by ,modified_date=:modified_date,cancel_status=:cancel_status,status=:status,date_non_effective=:date_non_effective"
						+ " where id=:id";

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
					cmain_obj.setStatus(1);
					sessionHQL.update(cmain_obj);
					sessionHQL.flush();
					sessionHQL.clear();
				
				}
			}
			

			model.put("msg", msg);
			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Approved.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}

		return new ModelAndView("redirect:Search_civilian_non_regular");
	}


	@RequestMapping(value = "/RejNonCancelUrl", method = RequestMethod.POST)
	public @ResponseBody ModelAndView RejNonCancelUrl(@ModelAttribute("id9") int id, BindingResult result,
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
		return new ModelAndView("redirect:Search_civilian_non_regular");
	}
	
	@RequestMapping(value = "/Print_non_regular", method = RequestMethod.POST)
	public ModelAndView Print_non_regular( ModelMap Mmap,
			
			@ModelAttribute("id1") String id,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session, HttpServletRequest request) throws NumberFormatException, ParseException {

		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		List<String> TH = new ArrayList<String>();
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		

		List<Map<String, Object>> lista = cdao.getApproveDataForviewnonregular(Integer.parseInt(id));
		
	
		String Heading = "\nRecord Service";
		String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-YYYY").format(new Date());
		return new ModelAndView(new Download_non_regular("L", Heading, username,foot, lista), "userList", lista);
	}
}
