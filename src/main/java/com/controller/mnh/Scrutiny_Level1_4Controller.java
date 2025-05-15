package com.controller.mnh;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hsqldb.persist.RowInsertInterface.modes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.notification.NotificationController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.Scrutiny_Level1_4DAO;
import com.models.mnh.Scrutiny_Search_Model;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Scrutiny_Level1_4Controller {

	@Autowired
	RoleBaseMenuDAO roledao;

	@Autowired
	Scrutiny_Level1_4DAO lvl_dao;

	/// bisag v2 2508022(notification)
	NotificationController notification = new NotificationController();

	MNH_ValidationController validation = new MNH_ValidationController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	AllMethodsControllerTMS at = new AllMethodsControllerTMS();
	@Autowired
	MNH_Common_DAO mnh1_Dao;

	MNH_CommonController mcommon = new MNH_CommonController();

	// DATA SCRUTINY MODULE (1)-> (UNIT APPROVAL SCREEN START)
	@RequestMapping(value = "/admin/mnh_scrutiny_unit_approval", method = RequestMethod.GET)
	public ModelAndView mnh_scrutiny_unit_approval(ModelMap Mmap, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mnh_scrutiny_unit_approval", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();

		if (accsLvl.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			Mmap.put("roleAccess", accsLvl);

		}

		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_unit_appTiles");
	}
	// DATA SCRUTINY MODULE (1)-> (UNIT APPROVAL SCREEN END)

	// DATA SCRUTINY MODULE (2)-> (FMN APPROVAL SCREEN START)
	@RequestMapping(value = "/admin/mnh_scrutiny_fmn_approval", method = RequestMethod.GET)
	public ModelAndView mnh_scrutiny_fmn_approval(ModelMap Mmap, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mnh_scrutiny_fmn_approval", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		Mmap.put("r_1", l1);
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_fmn_appTiles");
	}
	// DATA SCRUTINY MODULE (2)-> (FMN APPROVAL SCREEN END)

	// DATA SCRUTINY MODULE (3)-> (LEVEL-1 APPROVAL SCREEN START)
	@RequestMapping(value = "/admin/mnh_level1_approval", method = RequestMethod.GET)
	public ModelAndView mnh_level1_approval(ModelMap Mmap, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");
		String role = session.getAttribute("role").toString();
		
		Boolean val = roledao.ScreenRedirect("mnh_level1_approval", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		List<String> list123 = at.getTargetUnitsNameActiveList(session, "");
	
		Mmap.put("list123", list123);
		Mmap.put("size123", list123.size());
		Mmap.put("r_1", l1);
		Mmap.put("ml_6", mcommon.getMedrelationList("", session));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_level1Tiles");
	}
	// DATA SCRUTINY MODULE (3)-> (LEVEL-1 APPROVAL SCREEN END)

	// DATA SCRUTINY MODULE (4)-> (LEVEL-2 APPROVAL SCREEN START)
	@RequestMapping(value = "/admin/mnh_level2_approval", method = RequestMethod.GET)
	public ModelAndView mnh_level2_approval(ModelMap Mmap, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mnh_level2_approval", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		Mmap.put("r_1", l1);
		Mmap.put("ml_5", mcommon.getMedAllPrincipleList("", session));
		Mmap.put("ml_6", mcommon.getMedrelationList("", session));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_level2Tiles");
	}
	// DATA SCRUTINY MODULE (4)-> (LEVEL-2 APPROVAL SCREEN END)

	// (1),(2)-> (UNIT & FMN APPROVAL SCREEN METHODS START)
	@RequestMapping(value = "/DSunitList", method = RequestMethod.POST)
	public ModelAndView DSunitList(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("sus1") String sus1,
			String unit1, String cmd1, String frm_dt1, String to_dt1, String stat1, String para1, String p_diag1,
			String icd_remarks_a1, String icd_remarks_d1, String relation1) throws ParseException {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		if (para1.equalsIgnoreCase("UNIT")) {

			Boolean val = roledao.ScreenRedirect("mnh_scrutiny_unit_approval",
					session.getAttribute("roleid").toString());
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			if (unit1 == null || unit1.equals("")) {
				model.put("msg", "Please Enter the Unit Name");
				return new ModelAndView("redirect:mnh_scrutiny_unit_approval");
			}
			if (sus1 == null || sus1.equals("")) {
				model.put("msg", "Please Enter the SUS No");
				return new ModelAndView("redirect:mnh_scrutiny_unit_approval");
			}

			if (validation.DiseasemmrLength(unit1) == false) {
				model.put("msg", validation.hospital_nameMSG);
				return new ModelAndView("redirect:mnh_scrutiny_unit_approval");

			}
			if (validation.sus_noLength(sus1) == false) {
				model.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:mnh_scrutiny_unit_approval");

			}
		}

		if (para1.equalsIgnoreCase("L1")) {

			Boolean val = roledao.ScreenRedirect("mnh_level1_approval", session.getAttribute("roleid").toString());
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		}
		if (para1.equalsIgnoreCase("L2")) {

			Boolean val = roledao.ScreenRedirect("mnh_level2_approval", session.getAttribute("roleid").toString());
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		}

		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		model.put("r_1", l1);

		List<Map<String, Object>> list = lvl_dao.search_unit_fmn_datascrutiny(sus1, cmd1, frm_dt1, to_dt1, stat1, para1,
				p_diag1, icd_remarks_a1, icd_remarks_d1, relation1);
		model.put("list", list);
		model.put("size", list.size());

		model.put("sus1", sus1);
		model.put("unit1", unit1);
		model.put("frm_dt1", frm_dt1);
		model.put("to_dt1", to_dt1);
		model.put("stat1", stat1);
		model.put("p_diag1", p_diag1);
		model.put("icd_remarks_a1", icd_remarks_a1);
		model.put("icd_remarks_d1", icd_remarks_d1);
		model.put("ml_6", mcommon.getMedrelationList("", session));
		model.put("relation1", relation1);
		if (para1.equalsIgnoreCase("UNIT")) {
			return new ModelAndView("mnh_unit_appTiles");
		}

		if (para1.equalsIgnoreCase("L1")) {
			return new ModelAndView("mnh_level1Tiles");
		}
		if (para1.equalsIgnoreCase("L2")) {
			model.put("ml_5", mcommon.getMedAllPrincipleList("", session));
			return new ModelAndView("mnh_level2Tiles");
		}
		return null;
	}

	/*
	 * @RequestMapping(value = "/DSunitApproved" , method = RequestMethod.POST)
	 * public @ResponseBody List<String> DSunitApproved(String a,String
	 * para,HttpSession session) { int userid =
	 * Integer.parseInt(session.getAttribute("userId").toString());
	 * 
	 * String username = session.getAttribute("username").toString(); List<String>
	 * list2 = new ArrayList<String>();
	 * list2.add(lvl_dao.approve_unit_fmn_datascrutiny(a,para,username)); ///bisag
	 * v2 2508022(notification) List<String> listS = CheckMedical_unitNoData(a);
	 * String user_id = ""; for (int i = 0; i < listS.size(); i++) { if(i==0) {
	 * user_id += listS.get(i).toString(); }
	 * 
	 * else { user_id += ","+listS.get(i).toString(); }
	 * 
	 * }
	 * 
	 * String title = "DISCHARGE" ; String description = "DISCHARGE";
	 * 
	 * Boolean d = notification.sendNotification(title, description,user_id,
	 * username);
	 * 
	 * 
	 * return list2; }
	 */

	@RequestMapping(value = "/DSunitRejected", method = RequestMethod.POST)
	public @ResponseBody List<String> DSunitRejected(String a, String r, HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		list2.add(lvl_dao.reject_unit_fmn_datascrutiny(a, r, username));
		return list2;
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/Scrutiny_approval_action", method = RequestMethod.POST)
	public ModelAndView Scrutiny_approval_action(@ModelAttribute("Scrutiny_approvalcmd") Scrutiny_Search_Model rs,
			BindingResult bindingResult, HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");
		String update_icd = request.getParameter("update_icd");

	
		Boolean val = roledao.ScreenRedirect("mnh_level2_approval", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		model.put("r_1", l1);

		String a = request.getParameter("CheckVal");

		String[] id_list = a.split(":");

		for (int i = 0; i < id_list.length; i++) {
			ArrayList<String> data = new ArrayList<String>();

			String id = request.getParameter("id" + id_list[i]);
			data.add(id);

			String a_rem = request.getParameter("a_rem" + id_list[i]);
		
			String d_rem = request.getParameter("d_rem" + id_list[i]);
			String[] a_s = a_rem.split("-");
			String[] d_s = d_rem.split("-");
		
			data.add(a_s[0]);
			data.add(d_s[0]);

			model.put("msg", lvl_dao.update_patient_discharge_details_ds(data));
		}
		if (update_icd.equals("1")) {
			return new ModelAndView("redirect:mnh_level1_approval");
		} else {
			return new ModelAndView("redirect:mnh_level2_approval");
		}

	}

	
	// (1),(2)-> (UNIT & FMN APPROVAL SCREEN METHODS END)

	// (3),(4)-> (LEVEL-1 & LEVEL-2 APPROVAL SCREEN METHODS START)
	@RequestMapping(value = "/edit_patientDetails_datascrutiny", method = RequestMethod.POST)
	public ModelAndView edit_patientDetails_datascrutiny(ModelMap model, HttpServletRequest request,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			@ModelAttribute("id2") Integer id2, String para2, String sus2, String unit2, String cmd2, String frm_dt2,
			String to_dt2, String stat2, String p_diag2, String diagnosis_code1d1, String icd_remarks_a2,
			String icd_remarks_d2, String check2) {
		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		if (para2.equalsIgnoreCase("UNIT")) {

			Boolean val = roledao.ScreenRedirect("mnh_scrutiny_unit_approval",
					session.getAttribute("roleid").toString());
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		}

		if (para2.equalsIgnoreCase("L1")) {
			Boolean val = roledao.ScreenRedirect("mnh_level1_approval", session.getAttribute("roleid").toString());
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			if (diagnosis_code1d1.equals("Z37.0")) {

			}

		}
		if (para2.equalsIgnoreCase("L2")) {
			Boolean val = roledao.ScreenRedirect("mnh_level2_approval", session.getAttribute("roleid").toString());
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		}
		if (para2.equalsIgnoreCase("PS")) {
			Boolean val = roledao.ScreenRedirect("mnh_patient_search", session.getAttribute("roleid").toString());
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		}
		if (para2.equalsIgnoreCase("AC")) {
			Boolean val = roledao.ScreenRedirect("mnh_arogya_check", session.getAttribute("roleid").toString());
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		}

		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		model.put("r_1", l1);
		model.put("id2", id2);
		model.put("sus2", sus2);
		model.put("unit2", unit2);
		model.put("cmd2", cmd2);
		model.put("frm_dt2", frm_dt2);
		model.put("to_dt2", to_dt2);
		model.put("stat2", stat2);
		model.put("para2", para2);
		model.put("p_diag2", p_diag2);
		model.put("icd_remarks_a2", icd_remarks_a2);
		model.put("icd_remarks_d2", icd_remarks_d2);
		model.put("check2", check2);
		List<Map<String, Object>> list = lvl_dao.get_patient_details_ds(id2);
		model.put("list", list);
		model.put("size", list.size());
		model.put("ml_1", mcommon.getMedrelationList("", session));
		model.put("ml_2", mcommon.getMedSystemCode("SEX", "", session));
		model.put("ml_3", mcommon.getMedSystemCode("NBB", "", session));
		model.put("GETSYSSERVICECODE", mcommon.getMedSystemCode("SERVICE", "", session));
		model.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", session));
		model.put("ml_5", mcommon.getMedSystemCode("MRTLSTAT", "", session));
		model.put("ml_6", mcommon.getMedSystemCode("ADMSNTYPE", "", session));
		model.put("ml_7", mcommon.getMedSystemCode("DISPOSAL", "", session));

		String service = null;
		String andno[] = list.get(0).get("and_no").toString().split("/");

		if (andno[0].equalsIgnoreCase("AR") || andno[0].equalsIgnoreCase("XR") || andno[0].equalsIgnoreCase("XE")
				|| andno[0].equalsIgnoreCase("DA") || andno[0].equalsIgnoreCase("RR") || andno[0].equalsIgnoreCase("DS")
				|| andno[0].equalsIgnoreCase("MC") || andno[0].equalsIgnoreCase("MA")) {
			service = "ARMY";
		}
		if (andno[0].equalsIgnoreCase("AF") || andno[0].equalsIgnoreCase("XF") || andno[0].equalsIgnoreCase("XH")) {
			service = "AIRFORCE";
		}
		if (andno[0].equalsIgnoreCase("IN") || andno[0].equalsIgnoreCase("XN") || andno[0].equalsIgnoreCase("XS")) {
			service = "NAVY";
		}
		if (andno[0].equalsIgnoreCase("M1") || andno[0].equalsIgnoreCase("M2") || andno[0].equalsIgnoreCase("M3")
				|| andno[0].equalsIgnoreCase("C4") || andno[0].equalsIgnoreCase("UE") || andno[0].equalsIgnoreCase("FN")
				|| andno[0].equalsIgnoreCase("CR") || andno[0].equalsIgnoreCase("CI") || andno[0].equalsIgnoreCase("IT")
				|| andno[0].equalsIgnoreCase("NC") || andno[0].equalsIgnoreCase("MS")
				|| andno[0].equalsIgnoreCase("N4")) {
			service = "OTHERS";
		}
		model.put("ml_8", mcommon.getMedDistinctRankList("", service, list.get(0).get("category").toString(), session));
		model.put("ser", service);
		return new ModelAndView("mnh_scrutiny_editTiles");

	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/scrutiny_edit_dsAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView scrutiny_edit_dsAction(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException, SQLException {

		String username = session.getAttribute("username").toString();
		String id = request.getParameter("id_hid");
		String p_para = request.getParameter("p_para");
		String check1 = request.getParameter("check2");
		String sus2 = request.getParameter("sus2");
		String unit2 = request.getParameter("unit2");
		String cmd2 = request.getParameter("cmd2");
		String frm_dt2 = request.getParameter("frm_dt2");
		String to_dt2 = request.getParameter("to_dt2");
		String stat2 = request.getParameter("stat2");
		String p_diag2 = request.getParameter("p_diag2");
		String icd_remarks_a2 = request.getParameter("icd_remarks_a2");
		String icd_remarks_d2 = request.getParameter("icd_remarks_d2");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date curr_date = new Date();

		String and_no = request.getParameter("and_no");
		String nok_name = request.getParameter("nok_name");
		String nok_relation = request.getParameter("nok_relation");

		String name = request.getParameter("name");
		String ad_dt = request.getParameter("ad_dt");
		String di_dt = request.getParameter("di_dt");
		String ward = request.getParameter("ward");
		String relation = request.getParameter("relation");
		String gender = request.getParameter("gender");
		String nbb = request.getParameter("nbb");
		String nbb_weight = request.getParameter("nbb_weight");
		String age_year = request.getParameter("age_year");
		String age_month = request.getParameter("age_month");
		String age_days = request.getParameter("age_days");
		String persnl_no = request.getParameter("persnl_no");
		String persnl_name = request.getParameter("persnl_name");
		String persnl_age_year = request.getParameter("persnl_age_year");
		String persnl_age_month = request.getParameter("persnl_age_month");
		String category = request.getParameter("category");
		String rank = request.getParameter("rank");
		String service_years = request.getParameter("service_years");
		String service_months = request.getParameter("service_months");
		String persnl_sex = request.getParameter("persnl_sex");
		String marital_status = request.getParameter("marital_status");
		String persnl_marital_status = request.getParameter("persnl_marital_status");
		String diagnosis_code1a = request.getParameter("diagnosis_code1a");
		String icd_cause_code1a = request.getParameter("icd_cause_code1a");
		String icd_remarks_a = request.getParameter("icd_remarks_a");
		String icd_remarks_d = request.getParameter("icd_remarks_d");
		String discharge_remarks = request.getParameter("discharge_remarks");
		String admsn_type = request.getParameter("admsn_type");
		String disposal = request.getParameter("disposal");
		String diagnosis_code1d = request.getParameter("diagnosis_code1d");
		String icd_cause_code1d = request.getParameter("icd_cause_code1d");
		String and_no_p[] = null;
		and_no_p = and_no.split("/");
		String b = and_no_p[0].substring(0, 2);
		String d_code_new = "";
		String d_code_p = "";
		String b_1[] = null;
		String b_2 = "";
		if (diagnosis_code1d != null && !diagnosis_code1d.equals("")) {
			d_code_new = diagnosis_code1d.substring(0, 1).toUpperCase();
			d_code_p = diagnosis_code1d.substring(0, 3).toUpperCase();

			b_1 = diagnosis_code1d.split("-");
			b_2 = b_1[0].substring(0, 1).toUpperCase();
		}

		String d_code_all[] = null;
		String icd_cause_all[] = null;
		String d_code_all1[] = null;
		String icd_cause_all1[] = null;
		if (diagnosis_code1d == null) {
			diagnosis_code1d = "";
		}

		if (icd_cause_code1d == null) {
			icd_cause_code1d = "";
		}

		if (!diagnosis_code1d.equals("")) {
			d_code_all = diagnosis_code1d.split("-");

		}

		if (!icd_cause_code1d.equals("")) {
			icd_cause_all = icd_cause_code1d.split("-");

		}

		if (diagnosis_code1a == null) {
			diagnosis_code1a = "";
		}

		if (icd_cause_code1a == null) {
			icd_cause_code1a = "";
		}

		if (!diagnosis_code1a.equals("")) {
			d_code_all1 = diagnosis_code1a.split("-");
		}

		if (!icd_cause_code1a.equals("")) {
			icd_cause_all1 = icd_cause_code1a.split("-");

		}

		int age = 0;
		Date dt1 = null;
		Date dt2 = null;
		try {
			dt1 = df.parse(ad_dt);
			dt2 = df.parse(di_dt);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			if (ad_dt.equals("")) {
				model.put("msg", "Please Select Admission Date");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if (dt1.after(curr_date)) {
				model.put("msg", "Can't select the Future Admission Date");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}

			if (di_dt.equals("")) {
				model.put("msg", "Please Select Discharge Date");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if (dt2.after(curr_date)) {
				model.put("msg", "Can't select the Future Discharge Date");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if (dt1.after(dt2) && !dt1.equals(dt2)) {

				model.put("msg", "Discharge Date is greater than or equal Admission Date Require");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if ((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF")
					|| relation.equals("SISTEROF")) && !gender.equals("FEMALE")) {
				model.put("msg", "Gender should be Female Here");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if ((relation.equals("BROTHEROF") || relation.equals("HUSBANDOF") || relation.equals("FATHEROF")
					|| relation.equals("SONOF") || relation.equals("PSO")) && !gender.equals("MALE")) {
				model.put("msg", "Gender should be Male Here");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if ((!relation.equals("SONOF") && !relation.equals("DAUGHTEROF")) && nbb.equals("Y")) {
				model.put("msg", "NBB Value is Y here. So Relation Should be SONOF or DAUGHTEROF Allowed Here");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if (nbb.equals("Y") && nbb_weight.equals("0")) {
				model.put("msg", "NBB Weight Zero Not Allowed");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}

			if (Integer.parseInt(age_year) == 0 && Integer.parseInt(age_month) == 0
					&& Integer.parseInt(age_days) == 0) {

				model.put("msg", "Patient Age Year,Age Month and Age Days 0 Not Allowed");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if (nbb.equals("Y") && Integer.parseInt(age_year) != 0) {

				model.put("msg", "NBB Age Only in Days Allowed");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if (Integer.parseInt(persnl_age_year) == 0 && Integer.parseInt(persnl_age_month) == 0) {
				model.put("msg", "Personal Age Year and Age Month Not Allowed");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if (Integer.parseInt(persnl_age_year) < Integer.parseInt(service_years)) {
				model.put("msg", "Personnel Age Should be greater than Service");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if (relation.equals("SELF") && gender.equals("FEMALE")
					&& (!category.equals("MNS") && !category.equals("OFFICER")) && b.equals("AR")) {
				model.put("msg", "Category should be MNS or OFFICER Here");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if ((!category.equals("") && rank.equals("-1")) && (b.equals("AR") || b.equals("XR") || b.equals("DS"))) {
				model.put("msg", "Mention A&D No  Enter rank ");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if (relation.equals("SELF") && gender.equals("MALE") && category.equals("MNS")) {
				model.put("msg", "Category should not be MNS Here");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if ((category.equals("JCO") || category.equals("OR")) && !persnl_sex.equals("MALE")) {
				model.put("msg", "Personnel Gender Should be MALE Here");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}

			if (b.equals("NE") && relation.equals("SELF")) {

			} else {
				if (!persnl_age_year.equals("") && Integer.parseInt(persnl_age_year) < 17) {
					model.put("msg", "Personnel Age Year Not Allowed Below 17");
					if (p_para.equals("L1")) {
						return new ModelAndView("redirect:mnh_level1_approval");
					}
					if (p_para.equals("L2")) {
						return new ModelAndView("redirect:mnh_level2_approval");
					}
					if (p_para.equals("PS")) {
						return new ModelAndView("redirect:mnh_patient_search");
					}
					if (p_para.equals("AC")) {
						return new ModelAndView("redirect:mnh_arogya_check");
					}
				}
			}
			if (disposal.equals("-1")) {
				model.put("msg", "Please Select Disposal");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if ((disposal.equals("DEATH") || disposal.equals("FOUNDDEAD") || disposal.equals("S/BIRTH"))
					&& !diagnosis_code1d.equals("")) {
				model.put("msg", "Discharge Diagnosis is not Allowed Under DEATH or FOUNDDEAD or S/BIRTH");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}

			if (d_code_p.equals("AAA")) {
				model.put("msg", "Entered Discharge Diagnosis value not Allowed");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if ((d_code_p.equals("O80") || d_code_p.equals("O82"))
					&& (!relation.equals("WIFEOF") && !relation.equals("SELF"))
					&& (!category.equals("MNS") && !category.equals("OFFICER"))) {
				model.put("msg",
						"Mentioned Discharge Diagnosis Allowed For Relation WIFEOF and SELF Only and Category should be MNS or OFFICER");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if ((d_code_p.equals("Z37") || d_code_p.equals("Z38"))
					&& (!relation.equals("SONOF") && !relation.equals("DAUGHTEROF")) && !nbb.equals("Y")) {
				model.put("msg",
						"Mentioned Discharge Diagnosis Allowed For Relation SONOF or DAUGHTEROF Only and NBB Value is Y Here");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if ((d_code_p.equals("O00") || d_code_p.equals("2099"))
					&& (!relation.equals("WIFEOF") && !relation.equals("MOTHEROF") && !relation.equals("DAUGHTEROF")
							&& !relation.equals("SISTEROF") && !relation.equals("SELF"))) {
				model.put("msg",
						"Mentioned Discharge Diagnosis Allowed For Relation WIFEOF or MOTHEROF or DAUGHTEROF or SISTEROF or SELF Only");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if ((d_code_p.equals("O00") || d_code_p.equals("2099")) && relation.equals("SELF")
					&& (category.equals("JCO") || category.equals("OR"))) {
				model.put("msg", "Category JCO or OR is not Allowed under Mentioned Discharge Diagnosis");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}

			if (d_code_new.equals("O") && relation.equals("SELF")
					&& (category.equals("JCO") || category.equals("OR"))) {
				model.put("msg", "Category JCO or OR is not Allowed under Mentioned ICD Code");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if (d_code_new.equals("P") && (!relation.equals("SONOF") && !relation.equals("DAUGHTEROF"))) {
				model.put("msg", "Mentioned ICD Code Allowed For Relation SONOF or DAUGHTEROF Only");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}
			if ((b_2.equals("S") || b_2.equals("T") || b_2.equals("X") || b_2.equals("V") || b_2.equals("W")
					|| b_2.equals("Y")) && (icd_cause_code1d.equals(""))) {
				model.put("msg", "Please Enter ICD Cause11111");
				if (p_para.equals("L1")) {
					return new ModelAndView("redirect:mnh_level1_approval");
				}
				if (p_para.equals("L2")) {
					return new ModelAndView("redirect:mnh_level2_approval");
				}
				if (p_para.equals("PS")) {
					return new ModelAndView("redirect:mnh_patient_search");
				}
				if (p_para.equals("AC")) {
					return new ModelAndView("redirect:mnh_arogya_check");
				}
			}

		} catch (Exception e) {
		}

		ArrayList<String> data = new ArrayList<String>();
		data.add(id);
		data.add(p_para);
		data.add(and_no);
		data.add(name);
		data.add(ad_dt);
		data.add(di_dt);
		data.add(ward);
		data.add(relation);
		if (gender.equals("MALE")) {
			gender = "M";
		}
		if (gender.equals("FEMALE")) {
			gender = "F";
		}
		if (gender.equals("TRANSGENDER")) {
			gender = "T";
		}
		data.add(gender);
		data.add(nbb);
		data.add(nbb_weight);
		data.add(age_year);
		data.add(age_month);
		data.add(age_days);
		data.add(persnl_no);
		data.add(persnl_name);
		data.add(persnl_age_year);
		data.add(persnl_age_month);
		data.add(category);
		data.add(rank);
		data.add(service_years);
		data.add(service_months);
		if (persnl_sex.equals("MALE")) {
			persnl_sex = "M";
		}
		if (persnl_sex.equals("FEMALE")) {
			persnl_sex = "F";
		}
		if (persnl_sex.equals("TRANSGENDER")) {
			persnl_sex = "T";
		}
		data.add(persnl_sex);
		data.add(marital_status);
		data.add(persnl_marital_status);
		if (!diagnosis_code1a.equals("")) {
			data.add(d_code_all1[0]);
		} else {
			data.add(diagnosis_code1a);
		}
		if (!icd_cause_code1a.equals("")) {
			data.add(icd_cause_all1[0]);
		} else {
			data.add(icd_cause_code1a);
		}
		data.add(icd_remarks_a);
		data.add(icd_remarks_d);
		data.add(discharge_remarks);
		data.add(admsn_type);
		data.add(disposal);

		if (!diagnosis_code1d.equals("")) {
			data.add(d_code_all[0]);
		} else {
			data.add(diagnosis_code1d);
		}

		if (!icd_cause_code1d.equals("")) {
			data.add(icd_cause_all[0]);

		} else {

			data.add(icd_cause_code1d);
		}
		data.add(nok_name);
		data.add(nok_relation);
		List<String> list2 = new ArrayList<String>();
		// Note:21-09-2020 This logic is for pass button when

		String pass = request.getParameter("hd_pass");

		if (pass.equals("test")) {
			username = "test";
		}

		list2.add(lvl_dao.update_patient_details_ds(data, username, stat2));

		model.put("msg", list2);
		model.put("sus3", sus2);
		model.put("unit3", unit2);
		model.put("cmd3", cmd2);
		model.put("frm_dt3", frm_dt2);
		model.put("to_dt3", to_dt2);
		model.put("stat3", stat2);
		model.put("para3", p_para);
		model.put("p_diag3", p_diag2);
		model.put("icd_remarks_a3", icd_remarks_a2);
		model.put("icd_remarks_d3", icd_remarks_d2);
		try {
			if (p_para.equals("UNIT")) {
				return new ModelAndView("mnh_unit_appTiles");
			}

			if (p_para.equals("L1")) {
			
				return new ModelAndView("mnh_level1Tiles");
			}
			if (p_para.equals("L2")) {
				
				return new ModelAndView("mnh_level2Tiles");
			}
			if (p_para.equals("PS")) {
				return new ModelAndView("mnh_patient_searchTiles");
			}
			if (p_para.equals("AC")) {

				model.put("list_AROGYA", mcommon.getMedSystemCodeQua("AROGYA", "", session));
				return new ModelAndView("mnh_arogya_chkTiles");

			}
		} catch (Exception e) {

		}

		return null;
	}

	/// bisag v2 2508022(notification)

	/*
	 * @SuppressWarnings("null") public @ResponseBody List<String>
	 * CheckMedical_unitNoData(String a) {
	 * 
	 * Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 * 
	 * Transaction tx = sessionHQL.beginTransaction();
	 * 
	 * try {
	 * 
	 * List<String> list = new ArrayList<String>();
	 * 
	 * String[] id_list = a.split(":"); for (int i = 0; i < id_list.length; i++) {
	 * 
	 * int id = Integer.parseInt(id_list[i]);
	 * 
	 * Query q1 = sessionHQL.
	 * createSQLQuery("select distinct cast(l.userId as character varying ) from tb_med_patient_details p ,logininformation l where "
	 * + " l.user_sus_no = p.medical_unit " +
	 * " and cast(p.id as character varying) = cast(:a as character varying) ");
	 * 
	 * q1.setParameter("a", id); List<String> listN = (List<String>) q1.list();
	 * 
	 * for (int j = 0; j < listN.size(); j++) { list.add(listN.get(j)); }
	 * 
	 * }
	 * 
	 * 
	 * tx.commit();
	 * 
	 * return list;
	 * 
	 * } catch (RuntimeException e) {
	 * 
	 * return null;
	 * 
	 * } finally {
	 * 
	 * if (sessionHQL != null) {
	 * 
	 * sessionHQL.close();
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 */

	@RequestMapping(value = "/DSunitApproved", method = RequestMethod.POST)
	public @ResponseBody List<String> DSunitApproved(String a, String para, HttpSession session) {
		
		String username = session.getAttribute("username").toString();

		String[] id_a = a.split(":");
		for (int k = 0; k < id_a.length; k++) {
			String ida = (id_a[k]);

			List<String> listU = getMedicalUnitdescList(ida);

			List<String> listS = CheckMedical_unitNoData(ida);

			String user_id = "";
			for (int i = 0; i < listS.size(); i++) {
				if (i == 0) {

					user_id += listS.get(i).toString();
				}

				else {
					user_id += "," + listS.get(i).toString();

				}

			}
			String desc = "";
			for (int i = 0; i < listU.size(); i++) {
				if (i == 0) {

					desc += listU.get(i).toString();
				}

				else {
					desc += "," + listU.get(i).toString();

				}

			}

			String title = "DISCHARGE";

			Boolean d = notification.sendNotification(title, desc, user_id, username);

		}
		List<String> list2 = new ArrayList<String>();
		list2.add(lvl_dao.approve_unit_fmn_datascrutiny(a, para, username));
		return list2;

	}

	@RequestMapping(value = "/DSunitUpdate", method = RequestMethod.POST)
	public @ResponseBody List<String> DSunitUpdate(String a, String icdcode, String icdcause, String para,
			HttpSession session) {
		String username = session.getAttribute("username").toString();

		List<String> list2 = new ArrayList<String>();
		list2.add(lvl_dao.update_unit_fmn_datascrutiny(a, icdcode, icdcause, para, username));
		return list2;

	}

	@SuppressWarnings("null")
	public @ResponseBody List<String> CheckMedical_unitNoData(String a) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		try {

			List<String> list = new ArrayList<String>();

			String[] id_list = a.split(":");

			for (int i = 0; i < id_list.length; i++) {

				int id = Integer.parseInt(id_list[i]);

				Query q1 = sessionHQL.createSQLQuery(
						"select distinct cast(l.userId as character varying ) from tb_med_patient_details p ,logininformation l where "
								+ " l.user_sus_no = p.medical_unit "
								+ " and cast(p.id as character varying) = cast(:a as character varying) ");

				q1.setParameter("a", id);
				List<String> listN = (List<String>) q1.list();

				for (int j = 0; j < listN.size(); j++) {
					list.add(listN.get(j));
				}

			}

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

	public @ResponseBody List<String> getMedicalUnitdescList(String a) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		List<String> list = new ArrayList<String>();
		String[] id_list = a.split(":");
		for (int i = 0; i < id_list.length; i++) {

			int id = Integer.parseInt(id_list[i]);

			Query q = session.createSQLQuery(
					"select distinct m.unit_name from tb_med_patient_details p ,tb_miso_orbat_unt_dtl m where "
							+ " m.sus_no = p.medical_unit "
							+ " and cast(p.id as character varying) = cast(:a as character varying) ");

			q.setParameter("a", id);
			
			@SuppressWarnings("unchecked")

			List<String> listV = (List<String>) q.list();
			
			for (int j = 0; j < listV.size(); j++) {
				list.add(listV.get(j));
			}

		}

		tx.commit();
		session.close();
		return list;
	}
	// (3),(4)-> (LEVEL-1 & LEVEL-2 APPROVAL SCREEN METHODS END)

}
