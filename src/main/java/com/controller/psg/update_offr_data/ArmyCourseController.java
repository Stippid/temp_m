package com.controller.psg.update_offr_data;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.Dashboard.PsgDashboardController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.cancellation.ViewHistory_updateOffrDataDao;
import com.dao.psg.update_census_data.Search_Army_CourseDAO;
import com.models.psg.Transaction.TB_CENSUS_ADDRESS;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;
import com.models.psg.Transaction.TB_CENSUS_FOREIGN_COUNTRY;
import com.models.psg.Transaction.TB_CENSUS_IDENTITY_CARD;
import com.models.psg.Transaction.TB_CENSUS_LANGUAGE;
import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;
import com.models.psg.Transaction.TB_CENSUS_NOK;
import com.models.psg.Transaction.TB_CENSUS_QUALIFICATION;
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;
import com.models.psg.Transaction.TB_PSG_CANTEEN_CARD_DETAIL1;
import com.models.psg.Transaction.TB_PSG_CENSUS_ALLERGICTOMEDICINE;
import com.models.psg.update_census_data.TB_CENSUS_ARMY_COURSE;
import com.models.psg.update_census_data.TB_CENSUS_AWARDSNMEDAL;
import com.models.psg.update_census_data.TB_CENSUS_BATT_PHY_CASUALITY;
import com.models.psg.update_census_data.TB_CENSUS_BPET;
import com.models.psg.update_census_data.TB_CENSUS_CDA_ACCOUNT_NO;
import com.models.psg.update_census_data.TB_CENSUS_CHILDREN;
import com.models.psg.update_census_data.TB_CENSUS_DISCIPLINE;
import com.models.psg.update_census_data.TB_CENSUS_FIRING_STANDARD;
import com.models.psg.update_census_data.TB_CENSUS_PROMOTIONAL_EXAM;
import com.models.psg.update_census_data.TB_CENSUS_SECONDMENT;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.models.psg.update_census_data.TB_CHANGE_OF_APPOINTMENT;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.models.psg.update_census_data.TB_CHANGE_RELIGION;
import com.models.psg.update_census_data.TB_CHANGE_TA_STATUS;
import com.models.psg.update_census_data.TB_DESERTER;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_NON_EFFECTIVE;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_EXTENSION_OF_COMISSION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class ArmyCourseController {

	ValidationController valid = new ValidationController();

	Psg_CommonController com = new Psg_CommonController();

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	Update_Qualification_Controller UP_QUA = new Update_Qualification_Controller();

	PsgDashboardController das = new PsgDashboardController();

	@Autowired
	ViewHistory_updateOffrDataDao hisDao;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	Search_Army_CourseDAO AC;

	@RequestMapping(value = "/admin/armyCourseUrl", method = RequestMethod.GET)
	public ModelAndView armyCourseUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request) {

		Mmap.put("getArmyCourseInstituteList", com.getArmyCourseInstitute("1"));
		Mmap.put("getDivclassList", com.getDivclass());
		Mmap.put("getCourseType", com.getCourseTypeList());

		return new ModelAndView("army_Course_tiles");
	}

	@RequestMapping(value = "/armyCourseActionOut", method = RequestMethod.POST)

	public @ResponseBody String armyCourseActionOut(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date army_start_date = null;
		Date army_date_of_completion = null;
		Date army_course_date_auth = null;
		String course_type_ot = null;
		String course_institue_ot = null;
		String ar_course_div_ot = null;
		String username = session.getAttribute("username").toString();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String army_course_name = request.getParameter("army_course_name");
		String army_course_abbr = request.getParameter("army_course_abbr");
		String army_course_institue = request.getParameter("army_course_institue");
		String army_course_institue_ot = request.getParameter("army_course_institue_ot");
		String army_course_institueV = request.getParameter("army_course_institueV");
		String army_course_div_grade = request.getParameter("army_course_div_grade");
		String army_course_type = request.getParameter("army_course_type");
		String army_course_start_date = request.getParameter("army_course_start_date");

		String army_course_date_of_completion = request.getParameter("army_course_date_of_completion");
		String personnel_no = request.getParameter("personnel_no");
		String army_course_ch_id = request.getParameter("army_course_ch_id");
		String p_id = request.getParameter("p_id");
		String com_id = request.getParameter("com_id");
		String army_course_authority = request.getParameter("army_course_authority");
		String army_course_doa = request.getParameter("army_course_doa");
		String census_id = "";
		String msg = "";
		if (p_id == null || p_id.equals("")) {
			census_id = "0";
		} else {
			census_id = request.getParameter("p_id");
		}

		if (personnel_no == null || personnel_no.equals("")) {
			return "Please Select Personel  No.";
		}

		if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
			return valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ";
		}

		if (personnel_no.length() < 7 || personnel_no.length() > 9) {
			return "Personal No Should Contain Maximum 9 Character";
		}

		if (army_course_authority == null || army_course_authority.equals("")) {
			return "Please Enter Authority";
		}
		if (!valid.isValidAuth(army_course_authority)) {
			return valid.isValidAuthMSG + " Authority";
		}
		if (!valid.isvalidLength(army_course_authority, valid.authorityMax, valid.authorityMin)) {
			return "Authority " + valid.isValidLengthMSG;
		}
		if (army_course_doa == null || army_course_doa.equals("null") || army_course_doa.equals("DD/MM/YYYY")
				|| army_course_doa.equals("")) {
			return "Please Enter Date of Authority";
		}
		if (!valid.isValidDate(army_course_doa)) {
			return valid.isValidDateMSG + " of Authority";
		}
		if (!army_course_doa.equals("") && !army_course_doa.equals("DD/MM/YYYY")) {
			army_course_date_auth = format.parse(army_course_doa);
		}
		if (army_course_name == null || army_course_name.equals("")) {
			return "Please Enter Course Name";
		}
		if (army_course_abbr == null || army_course_abbr.equals("")) {
			return "Please Enter Course Abbreviation";
		}
		if (army_course_institue == null || army_course_institue.equals("0")) {
			return "Please Enter Course Institute";
		}
		if (army_course_institue_ot != null && !army_course_institue_ot.trim().equals("")) {
			if (!valid.isOnlyAlphabet(army_course_institue_ot)) {
				return " Course Institute Other " + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(army_course_institue_ot, valid.nameMax, valid.nameMin)) {
				return " Course Institute Other " + valid.isValidLengthMSG;
			}
		}
		if (army_course_div_grade == null || army_course_div_grade.equals("0")) {
			return "Please Select Div/Grade";
		}
		if (request.getParameter("ar_course_div_ot") != null
				&& !request.getParameter("ar_course_div_ot").trim().equals("")) {
			if (!valid.isOnlyAlphabet(request.getParameter("ar_course_div_ot"))) {
				return " Div/Grade Other " + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(request.getParameter("ar_course_div_ot"), valid.nameMax, valid.nameMin)) {
				return " Div/Grade Other " + valid.isValidLengthMSG;
			}
		}
		if (army_course_type == null || army_course_type.equals("0")) {
			return "Please Select Course Type";
		}
		if (army_course_start_date == null || army_course_start_date.equals("null")
				|| army_course_start_date.equals("DD/MM/YYYY") || army_course_start_date.equals("")) {
			return "Please Enter Start Date";
		}
		if (!valid.isValidDate(army_course_start_date)) {
			return valid.isValidDateMSG + " of Start";
		}
		if (!army_course_start_date.equals("") && !army_course_start_date.equals("DD/MM/YYYY")) {
			army_start_date = format.parse(army_course_start_date);
		}
		if (army_course_date_of_completion == null || army_course_date_of_completion.equals("null")
				|| army_course_date_of_completion.equals("DD/MM/YYYY") || army_course_date_of_completion.equals("")) {
			return "Please Enter Date of Completion";
		}
		if (!valid.isValidDate(army_course_date_of_completion)) {
			return valid.isValidDateMSG + " of Completion";
		}
		if (!army_course_date_of_completion.equals("") && !army_course_date_of_completion.equals("DD/MM/YYYY")) {
			army_date_of_completion = format.parse(army_course_date_of_completion);
		}

		if (request.getParameter("ar_course_div_ot") != null && !request.getParameter("ar_course_div_ot").equals("")) {
			ar_course_div_ot = request.getParameter("ar_course_div_ot");
		}
		if (army_course_institue_ot != null && !army_course_institue_ot.trim().equals("")) {
			course_institue_ot = army_course_institue_ot;
		}

		army_course_name = army_course_name.replace("&#40;", "(");

		army_course_name = army_course_name.replace("&#41;", ")");

		army_course_abbr = army_course_abbr.replace("&#40;", "(");

		army_course_abbr = army_course_abbr.replace("&#41;", ")");
		BigInteger comm_id = BigInteger.ZERO;
		if (new BigInteger(request.getParameter("com_id")) != BigInteger.ZERO) {
			comm_id = new BigInteger(request.getParameter("com_id"));
		}
		try {
			String qry = null;
			if(army_course_institue_ot == null || army_course_institue_ot == "" || army_course_institue_ot.trim() == "" || army_course_institue_ot.trim().equals("")) {
				//qry = "and course_institute_other = ?" ;
				qry = "and course_institute_other is null" ;
			}else{
				qry = "and course_institute_other =:course_institute_other " ;
			}
			if (Integer.parseInt(army_course_ch_id) == 0) {
				Query q0 = sessionhql.createQuery(
						"select count(id) from TB_CENSUS_ARMY_COURSE where comm_id=:comm_id and authority=:authority "
						+ "and date_of_authority=:date_of_authority and course_name=:course_name "
						+ "and course_abbreviation=:course_abbreviation and course_institute=:course_institute "
						+ " and div_grade=:div_grade "
						+ "and course_type=:course_type and start_date=:start_date and date_of_completion=:date_of_completion " + qry);
				q0.setParameter("comm_id", comm_id);
				q0.setParameter("authority", army_course_authority);
				q0.setParameter("date_of_authority", army_course_date_auth);

				
				q0.setParameter("course_name", army_course_name);
				q0.setParameter("course_abbreviation", army_course_abbr);
				q0.setParameter("course_institute", Integer.parseInt(army_course_institue));
				if(army_course_institue_ot != null && !army_course_institue_ot.trim().equals("")) {
					q0.setParameter("course_institute_other", army_course_institue_ot);
				} 			
				q0.setParameter("div_grade", army_course_div_grade);
				q0.setParameter("course_type", army_course_type);
				q0.setParameter("start_date", army_start_date);
				q0.setParameter("date_of_completion", army_date_of_completion);

				Long c1 = (Long) q0.uniqueResult();
				if (c1 > 0) {
					return "Data already Exist.";
				}
				TB_CENSUS_ARMY_COURSE army_c = new TB_CENSUS_ARMY_COURSE();
				army_c.setCensus_id(Integer.parseInt(census_id));
				army_c.setComm_id(new BigInteger(com_id));
				army_c.setCourse_name(army_course_name);
				army_c.setDiv_grade(army_course_div_grade);
				army_c.setCourse_type(army_course_type);
				army_c.setCourse_abbreviation(army_course_abbr);
				army_c.setCourse_institute(Integer.parseInt(army_course_institue));
				army_c.setCourse_institute_other(course_institue_ot);
				army_c.setStart_date(army_start_date);
				army_c.setDate_of_completion(army_date_of_completion);
				army_c.setStatus(0);
				army_c.setAuthority(army_course_authority);
				army_c.setDate_of_authority(army_course_date_auth);
				army_c.setCreated_by(username);
				army_c.setCreated_on(date);
				army_c.setAr_course_div_ot(ar_course_div_ot);
				army_c.setCourse_type_ot(course_type_ot);
				int id = (int) sessionhql.save(army_c);
				msg = Integer.toString(id);

				String hql1 = "update TB_CENSUS_ARMY_COURSE set authority=:authority ,date_of_authority=:date_of_authority where comm_id=:comm_id"
						+ " and status =:status";
				Query query1 = sessionhql.createQuery(hql1).setString("authority", army_course_authority)
						.setTimestamp("date_of_authority", army_course_date_auth).setParameter("comm_id", comm_id)
						.setInteger("status", 0);
				query1.executeUpdate();

			} else {
				String hql = "update TB_CENSUS_ARMY_COURSE set modify_by=:modify_by ,modify_on=:modify_on ,course_name=:course_name,div_grade=:div_grade, "
						+ " ar_course_div_ot=:ar_course_div_ot,course_abbreviation=:course_abbreviation,course_institute=:course_institute,course_institute_other=:course_institute_other,course_type=:course_type,start_date=:start_date,date_of_completion=:date_of_completion, authority=:authority,date_of_authority=:date_of_authority,status=:status  where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("course_name", army_course_name)
						.setString("div_grade", army_course_div_grade).setString("course_type", army_course_type)
						.setTimestamp("start_date", (army_start_date))
						.setTimestamp("date_of_completion", (army_date_of_completion))
						.setInteger("id", Integer.parseInt(army_course_ch_id)).setString("modify_by", username)
						.setTimestamp("modify_on", date).setString("course_institute_other", course_institue_ot)
						.setString("ar_course_div_ot", ar_course_div_ot)
						.setTimestamp("date_of_authority", army_course_date_auth)
						.setString("course_abbreviation", army_course_abbr)
						.setString("authority", army_course_authority)
						.setInteger("course_institute", Integer.parseInt(army_course_institue)).setInteger("status", 0);

				String hql1 = "update TB_CENSUS_ARMY_COURSE set authority=:authority ,date_of_authority=:date_of_authority where comm_id=:comm_id"
						+ " and status =:status";
				Query query1 = sessionhql.createQuery(hql1).setString("authority", army_course_authority)
						.setTimestamp("date_of_authority", army_course_date_auth).setParameter("comm_id", comm_id)
						.setInteger("status", 0);
				query1.executeUpdate();

				msg = query.executeUpdate() > 0 ? "update" : "0";
			}

			/*
			 * String approoval_status = request.getParameter("app_status"); if
			 * (approoval_status != null && !approoval_status.equals("") &&
			 * approoval_status.equals("3")) { } else {
			 * com.update_offr_status(Integer.parseInt(p_id), username); }
			 */

			com.update_miso_role_hdr_status(comm_id, 0, username, "army_course_status");
			tx.commit();

		} catch (

		RuntimeException e) {
			try {
				tx.rollback();
				msg = "0";
			} catch (RuntimeException rbe) {
				msg = "0";
			}
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}

		return msg;
	}

	@RequestMapping(value = "/admin/searchArmyCourseUrl", method = RequestMethod.GET)
	public ModelAndView searchArmyCourseUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchArmyCourseUrl", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			return new ModelAndView("redirect:bodyParameterNotAllow");
//		}
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", com.getTypeofRankList());
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		Mmap.put("list", AC.Search_ArmyCourseList("", "0", "", "", "", "", "", roleSusNo, roleType));
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());

		return new ModelAndView("search_army_Course_tiles");
	}

	@RequestMapping(value = "/admin/GetSearchArmyCourse", method = RequestMethod.POST)
	public ModelAndView GetSearchArmyCourse(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no1", required = false) String personnel_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "rank1", required = false) String rank,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "cr_by1", required = false) String cr_by,
			@RequestParam(value = "cr_date1", required = false) String cr_date) {

//		String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("searchArmyCourseUrl", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			return new ModelAndView("redirect:bodyParameterNotAllow");
//		}
		String roleType = session.getAttribute("roleType").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		if (unit_sus_no != "") {
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				return new ModelAndView("redirect:searchArmyCourseUrl");
			}

			if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
				return new ModelAndView("redirect:searchArmyCourseUrl");
			}
		}

		if (unit_name != "") {
			if (!valid.isOnlyAlphabetNumeric(unit_name)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericMSG + " Unit Name ");
				return new ModelAndView("redirect:searchArmyCourseUrl");
			}

			if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:searchArmyCourseUrl");
			}
		}

		if (personnel_no != "") {
			if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
				return new ModelAndView("redirect:searchArmyCourseUrl");
			}

			if (personnel_no.length() < 7 || personnel_no.length() > 9) {
				Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
				return new ModelAndView("redirect:searchArmyCourseUrl");
			}
		}

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		ArrayList<ArrayList<String>> list = AC.Search_ArmyCourseList(personnel_no, status, unit_sus_no, unit_name, rank,
				cr_by, cr_date, roleSusNo, roleType);
		Mmap.put("list", list);
		Mmap.put("size", list.size());
		Mmap.put("personnel_no1", personnel_no);
		Mmap.put("status1", status);
		Mmap.put("rank1", rank);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("unit_sus_no1", unit_sus_no);
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());

		Mmap.put("cr_date1", cr_date);
		Mmap.put("cr_by1", cr_by);
		Mmap.put("getTypeofRankList", com.getTypeofRankList());

		return new ModelAndView("search_army_Course_tiles");
	}

	@RequestMapping(value = "/Edit_ArmyCourse_Url", method = RequestMethod.POST)
	public ModelAndView Edit_ArmyCourse_Url(@ModelAttribute("id2") String updateid,
			@ModelAttribute("personnel_no_e") String personnel_no, ModelMap Mmap,
			@ModelAttribute("status5") String status,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchArmyCourseUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		TB_CENSUS_ARMY_COURSE authDetails = AC.getseracharmyid(Integer.parseInt(updateid));
		Mmap.put("misoConverUntDtlCMD", updateid);
		Mmap.put("personnel_no_e", personnel_no);
		Mmap.put("status5", status);
		Mmap.put("getArmyCourseInstituteList", com.getArmyCourseInstitute("1"));
		Mmap.put("getDivclassList", com.getDivclass());
		Mmap.put("getCourseType", com.getCourseTypeList());
		
		return new ModelAndView("army_Course_tiles");
	}

	@RequestMapping(value = "/Approve_ArmyCourse_url", method = RequestMethod.POST)

	public ModelAndView Approve_ArmyCourse_url(@ModelAttribute("idA") String updateid,

			@ModelAttribute("personnel_no_a") String personnel_no2,

			@ModelAttribute("comm_id") String comm_id,

			@RequestParam(value = "msg", required = false) String msg,

			Authentication authentication, HttpServletRequest request, ModelMap Mmap, HttpSession session)

			throws ParseException {

		System.out.println(personnel_no2 + " " + comm_id + " " + updateid);

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", roleid);

		if (val == false) {

			return new ModelAndView("AccessTiles");

		}

		if (request.getHeader("Referer") == null) {

			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");

		}

		List<TB_CENSUS_ARMY_COURSE> Army_Course = getdataapprove_army_course(Integer.parseInt(updateid),
				new BigInteger(comm_id));

		Mmap.put("msg", msg);

		Mmap.put("idA", updateid);

		Mmap.put("personnel_no2", personnel_no2);

		Mmap.put("comm_id", comm_id);

		Mmap.put("Army_Course", Army_Course.size());

		Mmap.put("getCourseName", com.getCourseName());
		Mmap.put("getDivclass", com.getDivclass());
		Mmap.put("getCourseTypeList", com.getCourseTypeList());

		Mmap.put("getArmyCourseInstitute", com.getArmyCourseInstitute("1"));

		return new ModelAndView("App_Update_ArmyCourseTiles");

	}

	

	@RequestMapping(value = "/Reject_Army_CourseUrl", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Reject_Army_CourseUrl(@ModelAttribute("idR") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "rej_remarksR", required = false) String reject_remarks,
			Authentication authentication) {

//		String roleid = sessionA.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("searchArmyCourseUrl", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			return new ModelAndView("redirect:bodyParameterNotAllow");
//		}
		List<String> liststr = new ArrayList<String>();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String username = sessionA.getAttribute("username").toString();
		String hqlUpdate = "update TB_CENSUS_ARMY_COURSE set status=:status,reject_remarks=:reject_remarks,modify_by=:modify_by,modify_on=:modify_on  where comm_id=:id";

		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setString("reject_remarks", reject_remarks)
				.setString("modify_by", username).setDate("modify_on", new Date()).setInteger("id", id).executeUpdate();
//		TB_CENSUS_ARMY_COURSE comm = (TB_CENSUS_ARMY_COURSE) sessionHQL.get(TB_CENSUS_ARMY_COURSE.class, id);
		BigInteger bigId = BigInteger.valueOf(id);
		com.update_miso_role_hdr_status(bigId, 0, username, "army_course_status");
		if (app > 0) {
			liststr.add("Reject Successfully.");
		} else {
			liststr.add("Reject UNSuccessfully.");
		}
		model.put("msg", liststr.get(0));
		tx.commit();
		sessionHQL.close();
		return new ModelAndView("redirect:searchArmyCourseUrl");
	}



	@RequestMapping(value = "/getArmyCourseHistoryData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getArmyCourseHistoryData(HttpServletRequest request,
			HttpSession sessionA, Authentication authentication) {
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		int status = Integer.parseInt(request.getParameter("status"));

		return AC.getHisChangeOfArmyCourse(comm_id, status, sessionA);
	}

	@RequestMapping(value = "/admin/CancelArmyCourseData", method = RequestMethod.POST)
	public @ResponseBody String CancelArmyCourseData(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id").toString());
		int status = Integer.parseInt(request.getParameter("set_status").toString());
		try {
			String hql_n = "update TB_CENSUS_ARMY_COURSE set modify_on=:modify_on ,modify_by=:modify_by , cancel_status=:status "
					+ " where  id=:id";
			Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status).setInteger("id", id)
					.setString("modify_by", username).setTimestamp("modify_on", new Date());
			msg = query_n.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			return "0";
		}
		return msg;
	}

	public @ResponseBody List<TB_CENSUS_ARMY_COURSE> getdataapprove_army_course(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			String hqlUpdate = " from TB_CENSUS_ARMY_COURSE where  comm_id=:comm_id and status='0'";
			Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);

			List<TB_CENSUS_ARMY_COURSE> list = (List<TB_CENSUS_ARMY_COURSE>) query.list();
			tx.commit();

			return list;

		} catch (Exception e) {

			return null;
		} finally {
			sessionHQL.close();

		}
	}

	@RequestMapping(value = "/Approve_army_course_DataAction", method = RequestMethod.POST)

	public ModelAndView Approve_army_course_DataAction(
			@ModelAttribute("Approve_army_course_DataCMD") TB_CENSUS_ARMY_COURSE P,

			BindingResult result, @RequestParam(value = "msg", required = false) String msg,

			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {

		String roleid = session.getAttribute("roleid").toString();

		/*
		 * Boolean val = roledao.ScreenRedirect("searchArmyCourseUrl", roleid);
		 * 
		 * if (val == false) {
		 * 
		 * return new ModelAndView("AccessTiles");
		 * 
		 * }
		 * 
		 * if (request.getHeader("Referer") == null) {
		 * 
		 * msg = ""; return new ModelAndView("redirect:bodyParameterNotAllow");
		 * 
		 * }
		 */

		String username = session.getAttribute("username").toString();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		Date modified_date = new Date();

		try {

			TB_CENSUS_ARMY_COURSE army = new TB_CENSUS_ARMY_COURSE();

			army.setComm_id(new BigInteger(request.getParameter("comm_id")));

			army.setModify_on(modified_date);

			Mmap.put("msg", Update_Army_Course(army, username));

			com.update_miso_role_hdr_status(new BigInteger(request.getParameter("comm_id")), 1, username,
					"army_course_status");

			tx.commit();

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				Mmap.put("msg", "roll back transaction");

			} catch (RuntimeException rbe) {

				Mmap.put("msg", "Couldn?t roll back transaction " + rbe);

			}

			throw e;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

		return new ModelAndView("redirect:searchArmyCourseUrl");

	}

	public String Update_Army_Course(TB_CENSUS_ARMY_COURSE obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			String hql = "update TB_CENSUS_ARMY_COURSE set modify_by=:modified_by,modify_on=:modified_date,status=:status  "
					+ " where comm_id=:comm_id and status = '0'";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModify_on()).setInteger("status", 1)
					.setBigInteger("comm_id", obj.getComm_id());

			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}


@RequestMapping(value = "/viewHistory_Army_Course_UpadteOfficerDataUrl", method = RequestMethod.POST)
		public ModelAndView viewHistory_Army_Course_UpadteOfficerDataUrl(ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication, HttpServletRequest request,
				HttpSession sessionEdit,HttpSession session
				) throws NumberFormatException, ParseException  {
	
	      BigInteger comm_id6 = new BigInteger(request.getParameter("can_comm_id"));
         
	      String personnel_no = request.getParameter("can_personnel_no");
	      String roleSusNo = session.getAttribute("roleSusNo").toString();
	    
	      String roleid = session.getAttribute("roleid").toString();
			
			  Boolean val = roledao.ScreenRedirect("searchArmyCourseUrl", roleid);
			  
			  if (val == false) {
			  
			  return new ModelAndView("AccessTiles");
			  
			  }
			  
			  if (request.getHeader("Referer") == null) {
			  
			  msg = ""; return new ModelAndView("redirect:bodyParameterNotAllow");
			  
			  }
			 
	     
	      List<Map<String, Object>> Army_Course = hisDao.getHisArmyCourse(comm_id6,-1,session);
	      Mmap.put("getCourseName", com.getCourseName());

			Mmap.put("getCourseTypeList", com.getCourseTypeList());

			Mmap.put("getArmyCourseInstitute", com.getArmyCourseInstitute("1"));
			Mmap.put("getDivclass", com.getDivclass());
		   Mmap.put("Army_Course", Army_Course.size());
		
			Mmap.put("msg", msg);
			Date date = new Date();
			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
			Mmap.put("date", date1);
			Mmap.put("comm_id", comm_id6);
			Mmap.put("personnel_no", personnel_no);
			
		return new ModelAndView("Change_Of_Army_Course_History_Tiles");
	 }

    @RequestMapping(value = "/view_onlyarmycourseDataUrl", method = RequestMethod.POST)
          public ModelAndView view_onlyarmycourseDataUrl(ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, 
        		  Authentication authentication,HttpServletRequest request, HttpSession sessionEdit, HttpSession session,
        		@ModelAttribute("ap_comm_id") String comm_id51,	@ModelAttribute("ap_Personnel_no") String personnel_no5
        		)throws NumberFormatException, ParseException {
		BigInteger comm_id5 = BigInteger.ZERO;
	
	
			String roleSusNo = session.getAttribute("roleSusNo").toString();
		    String roleid = session.getAttribute("roleid").toString();
		    Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", roleid);
		
		    Mmap.put("personnel_no", personnel_no5);
			Mmap.put("comm_id", comm_id51);
      	List<TB_CENSUS_ARMY_COURSE> Army_Course = update_census_army_courseData(new BigInteger(comm_id51));
          Mmap.put("Army_Course", Army_Course);
         
          Mmap.put("getCourseName", com.getCourseName());

          Mmap.put("getCourseTypeList", com.getCourseTypeList());
  		Mmap.put("getDivclass", com.getDivclass());
  		Mmap.put("getArmyCourseInstitute", com.getArmyCourseInstitute("1"));
        Mmap.put("msg", msg);
       return new ModelAndView("view_approve_armycourse_Tiles");



}
	public List<TB_CENSUS_ARMY_COURSE> update_census_army_courseData( BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_ARMY_COURSE where comm_id=:comm_id and status='1' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_ARMY_COURSE> list = (List<TB_CENSUS_ARMY_COURSE>) query.list();
		tx.commit();
		sessionHQL.close();
	
		return list;
	}

	@RequestMapping(value = "/Approve_viewHistory_armycourseDataUrl", method = RequestMethod.POST)
	public ModelAndView Approve_viewHistory_armycourseDataUrl(@ModelAttribute("idcanhisV") String updateid,ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication, HttpServletRequest request,
			HttpSession sessionEdit,HttpSession session,
			@RequestParam(value = "comm_idcanhis", required = false) String comm_id,
			@RequestParam(value = "personnel_nocanhis", required = false) String personnel_no) throws NumberFormatException, ParseException  {
		
      BigInteger comm_id6 = new BigInteger(request.getParameter("comm_idcanhis"));
      String roleSusNo = session.getAttribute("roleSusNo").toString();
      String roleid = session.getAttribute("roleid").toString();
      Boolean val = roledao.ScreenRedirect("searchArmyCourseUrl", session.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
       List<Map<String, Object>> Army_Course = hisDao.getHisArmyCourse(comm_id6,0,session);
    
		
	   Mmap.put("Army_Course", Army_Course.size());
	   Mmap.put("Army_Course", Army_Course);
       Mmap.put("getCourseName", com.getCourseName());
       Mmap.put("getCourseTypeList", com.getCourseTypeList());
  	   Mmap.put("getDivclass", com.getDivclass());
  	   Mmap.put("getArmyCourseInstitute", com.getArmyCourseInstitute("1"));
		
		// END
		Mmap.put("msg", msg);
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("personnel_no", personnel_no);
		Mmap.put("comm_id", comm_id);
	
	return new ModelAndView("ApproveViewHistory_armycourseTiles");
 }
	
	@RequestMapping(value = "/Approve_armycourse_DataAction", method = RequestMethod.POST)
	public ModelAndView Approve_armycourse_DataAction( 
			
			HttpServletRequest request, ModelMap Mmap, HttpSession session,	@RequestParam(value = "msg", required = false) String msg1) throws ParseException {
		 String roleid = session.getAttribute("roleid").toString();
	     Boolean val = roledao.ScreenRedirect("searchArmyCourseUrl", session.getAttribute("roleid").toString());
	       if(val == false) {
	               return new ModelAndView("AccessTiles");
	       }

			if(request.getHeader("Referer") == null ) {
				msg1 = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		String username = session.getAttribute("username").toString();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		BigInteger comm_id6=new BigInteger(request.getParameter("comm_id"));
		
		 
	     List<Map<String, Object>> Army_Course = hisDao.getHisArmyCourse(comm_id6,0,session);
	    
			Date modified_date=new Date();
			String msg="1";
		
		try{
				  
	    	 msg=approveHisArmyCourse(comm_id6, Army_Course, username, modified_date);	
  
	     if(msg.equals("1")) {
	    	 Mmap.put("msg", "Data Approved Successfully");
	    	
	     }
	     else {
	    	 Mmap.put("msg", "Data Not Approved");
	     }
	    
			
		}catch (RuntimeException e) { 
			
				Mmap.put("msg", "Couldn?t roll back transaction " + e);
			
		} finally {
			
		}
		return new ModelAndView("redirect:searchArmyCourseUrl");
	}

	public String approveHisArmyCourse(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
			
				String hql_n = "update TB_CENSUS_ARMY_COURSE set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
			
			
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	
	
	// pranay 25.09 (history of army courses)
	@RequestMapping(value = "/Popup_ArmycourseUrl", method = RequestMethod.POST)
	public ModelAndView Popup_CDAUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam(value = "comm_id1", required = false) BigInteger comm_id) {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_CDAUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}


		
		List<ArrayList<String>> list = AC.Popup_Change_of_armycourse(comm_id);
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		return new ModelAndView("Popup_Armycourse_tiles");
	}
	
	
	// Raj 
	@RequestMapping(value = "/army_courese_check", method = RequestMethod.POST)

	public @ResponseBody List<String> army_courese_check(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		String personnel_number = request.getParameter("personnel_no");
		String course_name = request.getParameter("army_course");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		
		
		
		Query q = session.createQuery("select id,comm_id from TB_CENSUS_ARMY_COURSE where course_name=:course_name and comm_id=:comm_id");

		q.setParameter("course_name",course_name);
		q.setParameter("comm_id",comm_id);
		

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		
		
		tx.commit();

		session.close();

		return list;

	}
}
