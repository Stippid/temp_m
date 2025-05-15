package com.controller.Dashboard;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.Held_Offrs_Download_report;
import com.controller.ExportFile.Unit_Dashboard_eqpt_held_report;
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.dao.Dashboard.UNIT_DashboardDAO;
import com.dao.Dashboard.UNIT_Dashboard_Reports_DAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class UnitDashboardController {
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private UNIT_DashboardDAO unit_dao;

	psg_jco_CommonController jcom = new psg_jco_CommonController();

	@Autowired
	private UNIT_Dashboard_Reports_DAO report_dao;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@RequestMapping(value = "/admin/UnitDashboard", method = RequestMethod.GET)
	public ModelAndView UnitDashboard(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String unitName = "";
		String formCodeControl = "";
		Boolean val = roledao.CheckDashboard("UnitDashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("form_code", "");

		Mmap.put("msg", msg);
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		Query q = ses1.createQuery(
				"select distinct unit_name,form_code_control from Miso_Orbat_Unt_Dtl where sus_no=:sus_no   and status_sus_no='Active'");
		q.setParameter("sus_no", roleSusNo);
		// String Unit_name = (String) q.uniqueResult();

		List<Object[]> results = q.list();
		for (Object[] result : results) {
			unitName = (String) result[0];
			formCodeControl = (String) result[1];

		}

		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("unit_name", unitName);
		Mmap.put("formCodeControl", "");
		List<Map<String, Object>> lastupdateList = unit_dao.getLastUpdateDate(session, roleSusNo);
		Mmap.put("lastupdateList", lastupdateList);
		return new ModelAndView("unitDashboardTiles");
	}

	@RequestMapping(value = "/admin/bdeDashboard", method = RequestMethod.GET)
	public ModelAndView bdeDashboard(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String unitName = "";
		String formCodeControl = "";
		Boolean val = roledao.CheckDashboard("bdeDashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		Mmap.put("form_code", "Brigade");

		Mmap.put("msg", msg);
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		Query q = ses1.createQuery(
				"select distinct unit_name,form_code_control from Miso_Orbat_Unt_Dtl where sus_no=:sus_no  and status_sus_no='Active'");
		q.setParameter("sus_no", roleSusNo);
		// String Unit_name = (String) q.uniqueResult();

		List<Object[]> results = q.list();
		for (Object[] result : results) {
			unitName = (String) result[0];
			formCodeControl = (String) result[1];

		}

		Query countAQuery = ses1.createQuery(
				"SELECT COUNT(*) AS record_count_a_veh FROM TB_TMS_CENSUS_RETURN a WHERE TO_CHAR(a.creation_date, 'MM-YYYY') = TO_CHAR(CURRENT_DATE, 'MM-YYYY')");
		Long countA = (Long) countAQuery.uniqueResult();

		Query countBQuery = ses1.createQuery(
				"SELECT COUNT(*) AS record_count_b_veh FROM TB_TMS_MVCR_PARTA_DTL b WHERE TO_CHAR(b.creation_date, 'MM-YYYY') = TO_CHAR(CURRENT_DATE, 'MM-YYYY')");
		Long countB = (Long) countBQuery.uniqueResult();

		Query countCQuery = ses1.createQuery(
				"SELECT COUNT(*) AS record_count_c_veh FROM TB_TMS_EMAR_REPORT c WHERE TO_CHAR(c.creation_date, 'MM-YYYY') = TO_CHAR(CURRENT_DATE, 'MM-YYYY')");
		Long countC = (Long) countCQuery.uniqueResult();

		Query countComputingAssetsQuery = ses1.createQuery(
				"SELECT COUNT(*) AS record_count_com FROM Assets_Main com WHERE TO_CHAR(com.created_date, 'MM-YYYY') = TO_CHAR(CURRENT_DATE, 'MM-YYYY')");
		Long countComputingAssets = (Long) countComputingAssetsQuery.uniqueResult();

		Query countPeripheralAssetsQuery = ses1.createQuery(
				"SELECT COUNT(*) AS record_count_peri FROM It_Asset_Peripherals peri WHERE TO_CHAR(peri.created_date, 'MM-YYYY') = TO_CHAR(CURRENT_DATE, 'MM-YYYY')");
		Long countPeripheralAssets = (Long) countPeripheralAssetsQuery.uniqueResult();

		int count_out = unit_dao.getPostCount(session, formCodeControl);
		int count_in = unit_dao.getPostCount_in(session, formCodeControl);
		int count_in_jco = unit_dao.getPostCount_in_jco(session, formCodeControl);
		int count_out_jco = unit_dao.getPostCount_in_jco(session, formCodeControl);
		int count_in_civ = unit_dao.getPostCount_in_civ(session, formCodeControl);
		int count_out_civ = unit_dao.getPostCount_out_civ(session, formCodeControl);

		Mmap.put("countPostIn", count_in);
		Mmap.put("countPostOut", count_out);
		Mmap.put("countPostIn_jco", count_in_jco);
		Mmap.put("countPostOut_jco", count_out_jco);
		Mmap.put("countPostIn_civ", count_in_civ);
		Mmap.put("countPostOut_civ", count_out_civ);

		Mmap.put("countComputingAssets", countComputingAssets);
		Mmap.put("countPeripheralAssets", countPeripheralAssets);
		Mmap.put("countA", countA);
		Mmap.put("countB", countB);
		Mmap.put("countC", countC);

		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("unit_name", unitName);
		Mmap.put("formCodeControl", formCodeControl);
		List<Map<String, Object>> lastupdateList = unit_dao.getLastUpdateDate(session, roleSusNo);
		Mmap.put("lastupdateList", lastupdateList);
		return new ModelAndView("bdeDashboardTiles");
	}

	@RequestMapping(value = "/Getcount_offHeld", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_offHeld(String sus_no, String formCodeControl,
			String formation_type, HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list_nom = unit_dao.Getcount_offHeld_data(sus_no, formCodeControl, formation_type);
		return list_nom;
	}

	@RequestMapping(value = "/Getcount_JCOHeld", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_JCOHeld(String sus_no, String formCodeControl,
			String formation_type, HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list_nom = unit_dao.Getcount_jcoHeld_data(sus_no, formCodeControl, formation_type);
		return list_nom;
	}

	@RequestMapping(value = "/Getcount_ORHeld", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_ORHeld(String sus_no, String formCodeControl,
			String formation_type, HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list_nom = unit_dao.Getcount_orHeld_data(sus_no, formCodeControl, formation_type);
		return list_nom;
	}

	@RequestMapping(value = "/Getcount_CIVHeld", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_CIVHeld(String sus_no, String formCodeControl,
			String formation_type, HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list_nom = unit_dao.Getcount_civHeld_data(sus_no, formCodeControl, formation_type);
		return list_nom;
	}

	@RequestMapping(value = "/Getcount_offauth", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_offauth(String sus_no, String formCodeControl,
			String formation_type, HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list_nom = unit_dao.Getcount_offAuth_data(sus_no, formCodeControl, formation_type);
		return list_nom;
	}

	@RequestMapping(value = "/Getcount_JCOAuth", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_JCOAuth(String sus_no, String formCodeControl,
			String formation_type, HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list_nom = unit_dao.Getcount_jcoAuth_data(sus_no, formCodeControl, formation_type);
		return list_nom;
	}

	@RequestMapping(value = "/Getcount_CIVAuth", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_CIVAuth(String sus_no, String formCodeControl,
			String formation_type, HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list_nom = unit_dao.Getcount_civAuth_data(sus_no, formCodeControl, formation_type);
		return list_nom;
	}

	@RequestMapping(value = "/Getcount_ORAuth", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_ORAuth(String sus_no, String formCodeControl,
			String formation_type, HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list_nom = unit_dao.Getcount_orAuth_data(sus_no, formCodeControl, formation_type);
		return list_nom;
	}

	// @RequestMapping(value = "/countoffauth", method = RequestMethod.POST)
	// public ModelAndView countoffauth(ModelMap Mmap, HttpSession session,
	// @RequestParam(value = "msg", required = false) String sus_no ,
	// HttpServletRequest request) {
	//
	// return new ModelAndView("Unit_ReportPersOffrsTiles");
	// }
	@RequestMapping(value = "/Getcount_aauth_held", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_aauth_held(String sus_no, String formCodeControl,
			String formation_type, HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list_nom = unit_dao.Getcount_aAuth_data(sus_no, formCodeControl, formation_type);

		return list_nom;
	}

	@RequestMapping(value = "/Getcount_bAuth_held", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_bAuth_held(String sus_no, String formCodeControl,
			String formation_type, HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list_nom = unit_dao.Getcount_bAuth_data(sus_no, formCodeControl, formation_type);
		return list_nom;
	}

	@RequestMapping(value = "/Getcount_cAuth_held", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_cAuth_held(String sus_no, String formCodeControl,
			String formation_type, HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list_nom = unit_dao.Getcount_cAuth_data(sus_no, formCodeControl, formation_type);
		return list_nom;
	}

	@RequestMapping(value = "/admin/countoffheld", method = RequestMethod.GET)
	public ModelAndView countoffheld(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String sus_no,

			HttpServletRequest request) {

		Mmap.put("sus", request.getParameter("sus_no"));
		return new ModelAndView("Unit_ReportPersOffrsTiles");

	}
//	
//	@RequestMapping(value = "/admin/countoffheld", method = RequestMethod.GET)
//	public ModelAndView countoffheld(ModelMap Mmap, HttpSession session,
//	        @RequestParam(value = "sus_no", required = false) String sus_no,
//	        HttpServletRequest request) {
//
//	    // You can now use the sus_no parameter in your method
//	    System.out.println("Received sus_no: " + sus_no);
//
//	    // Return the ModelAndView
//	    return new ModelAndView("Unit_ReportPersOffrsTiles");
//	}

	@RequestMapping(value = "/admin/countjcoheld", method = RequestMethod.GET)
	public ModelAndView countjcoheld(ModelMap Mmap, HttpSession session, String sus_no, HttpSession sessionUserId,
			HttpServletRequest request) {

		String unit_sus_no = session.getAttribute("roleSusNo").toString();
		String formationtype = session.getAttribute("roleSubAccess").toString();
		String formCodeControl = "";

		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		Query q = ses1.createQuery(
				"select distinct form_code_control from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active'");
		q.setParameter("sus_no", unit_sus_no);

		List<String> results = q.list();
		for (String result : results) {
			formCodeControl = result;
		}

		Mmap.put("getTypeofRankList_jCO",
				jcom.getRankjcoListfordashboard(unit_sus_no, "JCO", formationtype, formCodeControl));
		Mmap.put("sus", sus_no);
		return new ModelAndView("Unit_ReportPersJCoORTiles");

	}

	@RequestMapping(value = "/admin/countcivheld", method = RequestMethod.GET)
	public ModelAndView countcivheld(ModelMap Mmap, HttpSession session, String sus_no, HttpServletRequest request) {

		Mmap.put("sus", sus_no);
		return new ModelAndView("Unit_ReportPersCivsTiles");

	}

	@RequestMapping(value = "/admin/countorheld", method = RequestMethod.GET)
	public ModelAndView countorheld(ModelMap Mmap, HttpSession session, String sus_no, HttpServletRequest request) {
		String unit_sus_no = session.getAttribute("roleSusNo").toString();
		String formationtype = session.getAttribute("roleSubAccess").toString();
		String formCodeControl = "";

		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		Query q = ses1.createQuery(
				"select distinct form_code_control from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active'");
		q.setParameter("sus_no", unit_sus_no);

		List<String> results = q.list();
		for (String result : results) {
			formCodeControl = result;
		}

		Mmap.put("getTypeofRankList_jCO",
				jcom.getRankjcoListfordashboard(unit_sus_no, "OR", formationtype, formCodeControl));
		Mmap.put("sus", sus_no);
		return new ModelAndView("Unit_ReportPersORTiles");

	}

	@RequestMapping(value = "/Getcount_equi_held", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_equi_held(String sus_no, String formCodeControl,
			String formation_type, HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list_nom = unit_dao.Getcount_equi_held(sus_no, formCodeControl, formation_type);
		return list_nom;
	}

	@RequestMapping(value = "/Getcount_wpns_held", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_wpns_held(String sus_no, String formCodeControl,
			String formation_type, HttpSession session, HttpServletRequest request) {

		List<Map<String, Object>> list_nom = unit_dao.Getcount_wpns_held(sus_no, formCodeControl, formation_type);

		return list_nom;
	}

	@RequestMapping(value = "/data_table_tms", method = RequestMethod.POST)
	public ModelAndView data_table_tms(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "sus_no_veh1", required = false) String sus_no_veh1,
			@RequestParam(value = "type_veh", required = false) String type_veh, HttpServletRequest request) {

		Mmap.put("type_veh", type_veh);
		Mmap.put("sus", sus_no_veh1);
		return new ModelAndView("Unit_ReportVehTiles");

	}

	@RequestMapping(value = "/data_table_mtrls", method = RequestMethod.POST)
	public ModelAndView data_table_mtrls(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "sus_no_mtrls1", required = false) String sus_no_mtrls1,
			@RequestParam(value = "type_mtrls", required = false) String type_mtrls, HttpServletRequest request) {

		Mmap.put("type_mtrls", type_mtrls);
		Mmap.put("sus", sus_no_mtrls1);
		return new ModelAndView("Unit_ReportWpnEqptsTiles");

	}

	@RequestMapping(value = "/admin/countitassetheld", method = RequestMethod.GET)
	public ModelAndView countitassetheld(ModelMap Mmap, HttpSession session,
			String sus_no_itassetheld,

			HttpServletRequest request) {
		Mmap.put("sus", sus_no_itassetheld);
		return new ModelAndView("Unit_Report_ITAssetsTiles");

	}

	@RequestMapping(value = "/Getcount_itAssetHeld", method = RequestMethod.POST)
	public @ResponseBody long Getcount_itAssetHeld(String sus_no, HttpSession session, HttpServletRequest request,HttpSession sessionUserId) {

		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		long list_nom = unit_dao.getUnitReportIT_Assetscount("", "", "", session, roleSusNo,formationtype);

		return list_nom;
	}
	
	@RequestMapping(value = "/Getcount_itAssetHeld1", method = RequestMethod.POST)
	public @ResponseBody long Getcount_itAssetHeld1(String sus_no, HttpSession session, HttpServletRequest request,HttpSession sessionUserId) {
	
		String formationtype = "";
		long list_nom = unit_dao.getUnitReportIT_Assetscount("", "", "", session, sus_no,formationtype);

		return list_nom;
	}
	// @RequestMapping(value = "/admin/Getcount_aauth_held", method =
	// RequestMethod.GET)
	// public ModelAndView Getcount_aauth_held(ModelMap Mmap, HttpSession session,
	// @RequestParam(value = "msg", required = false) String sus_no ,
	// HttpServletRequest request) {
	//
	// return new ModelAndView("Unit_ReportPersCivsTiles");
	//
	// }

	//////////////////////////////////////////////
	@RequestMapping(value = "/getUnitReportPersOffrscount", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportPersOffrscount(String Search, String orderColunm, String orderType,
			String sus_no, HttpSession sessionUserId) throws SQLException {

		String roleSusNo = "";
		String Access_by_susno = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			Access_by_susno = "Access";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}

		return unit_dao.getUnitReportPersOffrscount(Search, orderColunm, orderType, sessionUserId, roleSusNo,
				Access_by_susno);
	}

	@RequestMapping(value = "/getUnitReportPersOffrs", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportPersOffrs(int startPage, String Search, int pageLength,
			String sus_no, String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {

		String roleSusNo = "";
		String Access_by_susno = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			Access_by_susno = "Access";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}

		return unit_dao.getUnitReportPersOffrs(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo, Access_by_susno);
	}

	@RequestMapping(value = "/getUnitReportPersJCocount", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportPersJCoORcount(String Search, String orderColunm, String orderType,
			String rank, String name, String trade, String armyNo, String sus_no, HttpSession sessionUserId)
			throws SQLException {

		String roleSusNo = "";
		String Access_by_susno = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			Access_by_susno = "Access";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}
		return unit_dao.getUnitReportPersJCocount(Search, orderColunm, orderType, sessionUserId, roleSusNo, name, rank,
				armyNo, trade, Access_by_susno);
	}

	@RequestMapping(value = "/getUnitReportPersJCo", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportPersJCoOR(int startPage, String Search, int pageLength,
			String sus_no, String orderColunm, String orderType, String rank, String name, String trade, String armyNo,
			HttpSession sessionUserId) throws SQLException {

		String roleSusNo = "";
		String Access_by_susno = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			Access_by_susno = "Access";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}

		return unit_dao.getUnitReportPersJCo(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo, name, rank, armyNo, trade, Access_by_susno);
	}

	// @RequestMapping(value = "/getUnitReportPersCivscount", method =
	// RequestMethod.POST)
	// public @ResponseBody long getUnitReportPerspersCivscount(String Search,String
	// orderColunm,String orderType,HttpSession sessionUserId
	// ) throws SQLException {
	// return unit_dao.getUnitReportPersCivscount(Search, orderColunm, orderType,
	// sessionUserId);
	// }

	// @RequestMapping(value = "/getUnitReportPersCivs", method =
	// RequestMethod.POST)
	// public @ResponseBody List<Map<String, Object>> getUnitReportPerspersCivs(int
	// startPage,String Search,int pageLength,String orderColunm,String
	// orderType,HttpSession sessionUserId )
	// throws SQLException {
	// return unit_dao.getUnitReportPersCivs( startPage, pageLength, Search,
	// orderColunm, orderType,sessionUserId);
	// }
	//

	@RequestMapping(value = "/getUnitReportVehcount", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportVehcount(String Search, String orderColunm, String orderType, String sus_no,
			String type_veh, String ba_no, String broad_cat, String veh_cat, HttpSession sessionUserId)
			throws SQLException {
		
		String roleSusNo = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			formationtype = "";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		}
		
		return unit_dao.getUnitReportVehcount(Search, orderColunm, orderType, type_veh, roleSusNo, ba_no, broad_cat,
				veh_cat, sessionUserId, formationtype);
	}

	@RequestMapping(value = "/getUnitReportVeh", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportVeh(int startPage, String Search, int pageLength,
			String orderColunm, String orderType, String type_veh, String ba_no, String broad_cat, String veh_cat, String sus_no,
			HttpSession sessionUserId) throws SQLException {
		
		String roleSusNo = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			formationtype = "";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		}
		
		return unit_dao.getUnitReportVeh(startPage, pageLength, Search, orderColunm, orderType, sessionUserId, type_veh,
				roleSusNo, ba_no, broad_cat, veh_cat,formationtype);
	}

	@RequestMapping(value = "/getUnitReportWpnEqptcount", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportWpnEqptcount(String Search, String orderColunm, String orderType,
			String type_mtrls, HttpSession sessionUserId, String prfgp, String nomenclature, String census_no, String sus_no,
			String service_status, String broadCat) throws SQLException {
		
		String roleSusNo = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			formationtype = "";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		}
	
		return unit_dao.getUnitReportWpnEqptcount(Search, orderColunm, orderType, sessionUserId, type_mtrls, roleSusNo,
				prfgp, nomenclature, census_no, service_status, broadCat,formationtype);
	}

	@RequestMapping(value = "/getUnitReportWpnEqpt", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportWpnEqpt(int startPage, String Search, int pageLength, String sus_no,
			String orderColunm, String orderType, String type_mtrls, HttpSession sessionUserId, ModelMap Mmap,
			String prfgp, String nomenclature, String census_no, String service_status, String broadCat)
			throws SQLException {
	
		String roleSusNo = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			formationtype = "";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		}


		return unit_dao.getUnitReportWpnEqpt(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				type_mtrls, roleSusNo, prfgp, nomenclature, census_no, service_status, broadCat,formationtype);
	}

	@RequestMapping(value = "/getUnitReportIT_Assetscount", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportIT_Assetscount(String Search, String orderColunm, String orderType, String sus_no,
			HttpSession sessionUserId) throws SQLException {

		String roleSusNo = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			formationtype = "";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		}
		
		return unit_dao.getUnitReportIT_Assetscount(Search, orderColunm, orderType, sessionUserId, roleSusNo, formationtype);
	}

	@RequestMapping(value = "/getUnitReportIT_Assets", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportIT_Assets(int startPage, String Search, int pageLength, String sus_no,
			String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {
		
		String roleSusNo = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			formationtype = "";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		}
		
		
		return unit_dao.getUnitReportIT_Assets(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo,formationtype);
	}

	////////
	@RequestMapping(value = "/getUnitReportPersORcount", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportPersORcount(String Search, String orderColunm, String orderType, String rank,
			String name, String trade, String armyNo, String sus_no, HttpSession sessionUserId) throws SQLException {

		String roleSusNo = "";
		String Access_by_susno = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			Access_by_susno = "Access";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}

		return unit_dao.getUnitReportPersORcount(Search, orderColunm, orderType, rank, name, trade, armyNo,
				sessionUserId, roleSusNo, Access_by_susno);
	}

	@RequestMapping(value = "/getUnitReportPersOR", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportPersOR(int startPage, String Search, int pageLength,
			String sus_no, String orderColunm, String orderType, String rank, String name, String trade, String armyNo,
			HttpSession sessionUserId) throws SQLException {

		String roleSusNo = "";
		String Access_by_susno = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			Access_by_susno = "Access";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}

		return unit_dao.getUnitReportPersOR(startPage, pageLength, Search, orderColunm, orderType, rank, name, trade,
				armyNo, sessionUserId, roleSusNo, Access_by_susno);
	}

	@RequestMapping(value = "/getUnitReportPersCivscount", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportPerspersCivscount(String Search, String orderColunm, String orderType,
			String sus_no, HttpSession sessionUserId) throws SQLException {

		String roleSusNo = "";
		String Access_by_susno = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			Access_by_susno = "Access";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}

		return unit_dao.getUnitReportPersCivscount(Search, orderColunm, orderType, sessionUserId, roleSusNo,
				Access_by_susno);
	}

	@RequestMapping(value = "/getUnitReportPersCivs", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportPerspersCivs(int startPage, String Search,
			String sus_no, int pageLength, String orderColunm, String orderType, HttpSession sessionUserId)
			throws SQLException {

		String roleSusNo = "";
		String Access_by_susno = "";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo = sus_no;
			Access_by_susno = "Access";
		} else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}

		return unit_dao.getUnitReportPersCivs(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo, Access_by_susno);
	}

	// pars auth offrs

	@RequestMapping(value = "/admin/countoffauth", method = RequestMethod.GET)
	public ModelAndView countoffauth(ModelMap Mmap, HttpSession session, String sus_no,

			HttpServletRequest request) {
		Mmap.put("sus", sus_no);
		return new ModelAndView("Unit_ReportPersOffrsAuthTiles");

	}

	@RequestMapping(value = "/getUnitReportPersOffrsAuthcount", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportPersOffrsAuthcount(String Search, String orderColunm, String orderType, String sus_no,
			HttpSession sessionUserId) throws SQLException {
		
		String roleSusNo ="";
		String Access_by_susno ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			Access_by_susno = "Access";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}
	
		return unit_dao.getUnitReportPersOffrsAuthcount(Search, orderColunm, orderType, sessionUserId, roleSusNo, Access_by_susno);
	}

	@RequestMapping(value = "/getUnitReportPersOffrsAuth", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportPersOffrsAuth(int startPage, String Search, String sus_no,
			int pageLength, String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {
		
		String roleSusNo ="";
		String Access_by_susno ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			Access_by_susno = "Access";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}

		return unit_dao.getUnitReportPersOffrsAuth(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo,Access_by_susno);
	}

	// jco auth

	@RequestMapping(value = "/admin/countjcoauth", method = RequestMethod.GET)
	public ModelAndView countjcoauth(ModelMap Mmap, HttpSession session,
			String sus_no,

			HttpServletRequest request) {

		Mmap.put("sus", sus_no);
		return new ModelAndView("Unit_ReportPersJcoAuthTiles");

	}

	@RequestMapping(value = "/getUnitReportPersJcoAuthcount", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportPersJcoAuthcount(String Search, String orderColunm, String orderType, String sus_no,
			HttpSession sessionUserId) throws SQLException {
		
		String roleSusNo ="";
		String Access_by_susno ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			Access_by_susno = "Access";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}
	
		return unit_dao.getUnitReportPersJcoAuthcount(Search, orderColunm, orderType, sessionUserId, roleSusNo, Access_by_susno);
	}

	@RequestMapping(value = "/getUnitReportPersJcoAuth", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportPersJcoAuth(int startPage, String Search, String sus_no,
			int pageLength, String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {
		
		String roleSusNo ="";
		String Access_by_susno ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			Access_by_susno = "Access";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}
		
		return unit_dao.getUnitReportPersJcoAuth(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo,Access_by_susno);
	}

	// or auth

	@RequestMapping(value = "/admin/countorauth", method = RequestMethod.GET)
	public ModelAndView countorauth(ModelMap Mmap, HttpSession session,
			String sus_no,

			HttpServletRequest request) {
		
		Mmap.put("sus", sus_no);

		return new ModelAndView("Unit_ReportPersOrAuthTiles");

	}

	@RequestMapping(value = "/getUnitReportPersOrAuthcount", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportPersOrAuthcount(String Search, String orderColunm, String orderType, String sus_no,
			HttpSession sessionUserId) throws SQLException {
		
		String roleSusNo ="";
		String Access_by_susno ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			Access_by_susno = "Access";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}
		
		
		return unit_dao.getUnitReportPersOrAuthcount(Search, orderColunm, orderType, sessionUserId, roleSusNo, Access_by_susno);
	}

	@RequestMapping(value = "/getUnitReportPersOrAuth", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportPersOrAuth(int startPage, String Search, int pageLength, String sus_no,
			String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {
		
		String roleSusNo ="";
		String Access_by_susno ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			Access_by_susno = "Access";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}
		
		
		
		return unit_dao.getUnitReportPersOrAuth(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo,Access_by_susno);
	}

	// or auth

	@RequestMapping(value = "/admin/countcivauth", method = RequestMethod.GET)
	public ModelAndView countcivauth(ModelMap Mmap, HttpSession session,
			String sus_no,

			HttpServletRequest request) {
		
		Mmap.put("sus", sus_no);

		return new ModelAndView("Unit_ReportPersCivAuthTiles");

	}

	@RequestMapping(value = "/getUnitReportPersCivAuthcount", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportPersCivAuthcount(String Search, String orderColunm, String orderType, String sus_no,
			HttpSession sessionUserId) throws SQLException {
		
		String roleSusNo ="";
		String Access_by_susno ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			Access_by_susno = "Access";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}
		
		return unit_dao.getUnitReportPersCivAuthcount(Search, orderColunm, orderType, sessionUserId, roleSusNo, Access_by_susno);
	}

	@RequestMapping(value = "/getUnitReportPersCivAuth", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportPersCivAuth(int startPage, String Search, String sus_no,
			int pageLength, String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {
		
		String roleSusNo ="";
		String Access_by_susno ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			Access_by_susno = "Access";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}
		
		return unit_dao.getUnitReportPersCivAuth(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo, Access_by_susno);
	}

	@RequestMapping(value = "/data_table_Auth_tms", method = RequestMethod.POST)
	public ModelAndView data_table_Auth_tms(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "sus_no_veh12", required = false) String sus_no_veh12,
			@RequestParam(value = "type_veh1", required = false) String type_veh1, HttpServletRequest request) {

		Mmap.put("type_veh", type_veh1);
		Mmap.put("sus", sus_no_veh12);
		return new ModelAndView("Unit_ReportVehAuthTiles");

	}

	@RequestMapping(value = "/getUnitReportVehAuth", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportVehAuth(int startPage, String Search, int pageLength, String sus_no,
			String orderColunm, String orderType, String type_veh, HttpSession sessionUserId) throws SQLException {
		
		String roleSusNo ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			formationtype ="";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();			
		}
		
		
		return unit_dao.getUnitReportVehAuth(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				type_veh, roleSusNo,formationtype);
	}

	@RequestMapping(value = "/getUnitReportVehAuthcount", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportVehAuthcount(String Search, String orderColunm, String orderType, String sus_no,
			String type_veh, HttpSession sessionUserId) throws SQLException {
		
		
		String roleSusNo ="";
		String Access_by_susno ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			Access_by_susno = "Access";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Access_by_susno = "";
		}
		
		return unit_dao.getUnitReportVehAuthcount(Search, orderColunm, orderType, type_veh, roleSusNo, sessionUserId, Access_by_susno);
	}

	@RequestMapping(value = "/data_table_mtrls_auth", method = RequestMethod.POST)
	public ModelAndView data_table_mtrls_auth(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "sus_no_mtrls12", required = false) String sus_no_mtrls12,
			@RequestParam(value = "type_mtrls1", required = false) String type_mtrls, HttpServletRequest request) {

		Mmap.put("type_mtrls", type_mtrls);
		Mmap.put("sus", sus_no_mtrls12);
		return new ModelAndView("Unit_ReportWpnEqptsAuthTiles");

	}

	@RequestMapping(value = "/getUnitReportWpnEqptAuthcount", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportWpnEqptAuthcount(String Search, String orderColunm, String orderType, String sus_no,
			String type_mtrls, HttpSession sessionUserId) throws SQLException {
		
		String roleSusNo ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			formationtype = "";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		}
		
		return unit_dao.getUnitReportWpnEqptAuthcount(Search, orderColunm, orderType, sessionUserId, type_mtrls,
				roleSusNo,formationtype);
	}

	@RequestMapping(value = "/getUnitReportWpnAuthEqpt", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportWpnAuthEqpt(int startPage, String Search, String sus_no,
			int pageLength, String orderColunm, String orderType, String type_mtrls, HttpSession sessionUserId)
			throws SQLException {
		
		String roleSusNo ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			formationtype = "";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			
		}
	
		return unit_dao.getUnitReportWpnEqptAuth(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				type_mtrls, roleSusNo,formationtype);
	}

	@RequestMapping(value = "/getUnitReportWpnEqpt_summary", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitReportWpnEqpt_summary(int startPage, String Search, String sus_no,
			int pageLength, String orderColunm, String orderType, String type_mtrls, HttpSession sessionUserId,
			String prfgp, String nomenclature, String census_no, String service_status, String broadCat)
			throws SQLException {
		
		String roleSusNo ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			formationtype = "";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			
		}
		
		
		return unit_dao.getUnitReportWpnEqptSummary(startPage, pageLength, Search, orderColunm, orderType,
				sessionUserId, type_mtrls, roleSusNo, prfgp, nomenclature, census_no, service_status, broadCat,formationtype);
	}

	@RequestMapping(value = "/getUnitReportWpnEqptcountsummary", method = RequestMethod.POST)
	public @ResponseBody long getUnitReportWpnEqptcountsummary(String Search, String orderColunm, String orderType, String sus_no,
			String type_mtrls, HttpSession sessionUserId, String prfgp, String nomenclature, String census_no,
			String service_status, String broadCat) throws SQLException {
		
		String roleSusNo ="";
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString(); 
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		if (roleAccess.equals("Formation") && !sus_no.equals("")) {
			roleSusNo =sus_no;
			formationtype = "";
		}else {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			
		}
		
		return unit_dao.getUnitReportWpnEqptcountSummary(Search, orderColunm, orderType, sessionUserId, type_mtrls,
				roleSusNo, prfgp, nomenclature, census_no, service_status, broadCat,formationtype);
	}

	@RequestMapping(value = "/getbroadCatUnitReort", method = RequestMethod.POST)
	public @ResponseBody List<String> getbroadCatUnitReort(String broadCat, String type_mtrls,
			HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		Transaction tx = sessionHQL.beginTransaction();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

		List<String> list = unit_dao.getbroadCatUnitReort(broadCat, type_mtrls, roleSusNo, formationtype);
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/getPRF_Grp", method = RequestMethod.POST)
	public @ResponseBody List<String> getPRF_Grp(String prfgp, String type_mtrls, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();

		List<String> list = unit_dao.getPRF_Grp(prfgp, type_mtrls, roleSusNo, formationtype);
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/getnomeNclatureforUnitreport", method = RequestMethod.POST)
	public @ResponseBody List<String> getnomeNclatureforUnitreport(String nomenclature, String type_mtrls,
			HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		List<String> list = unit_dao.getnomeNclatureforUnitreport(nomenclature, type_mtrls, roleSusNo, formationtype);

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/getnomeCensusnoforUnitreport", method = RequestMethod.POST)
	public @ResponseBody List<String> getnomeCensusnoforUnitreport(String census_no, String type_mtrls,
			HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();

		List<String> list = unit_dao.getnomeCensusnoforUnitreport(census_no, type_mtrls, roleSusNo, formationtype);

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	// HERE
	@RequestMapping(value = "/Download_unit_dashboard_Report", method = RequestMethod.POST)
	public ModelAndView Download_unit_dashboard_Report(@ModelAttribute("type_of_report") String type_of_report,
			@ModelAttribute("type") String type, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		// Boolean val = roledao.ScreenRedirect("Auth_and_Posted_Strength", roleid);
		String roleSusNo = sessionEdit.getAttribute("roleSusNo").toString();
		String formationtype = sessionEdit.getAttribute("roleSubAccess").toString();

		// if (val == false) {
		// return new ModelAndView("AccessTiles");
		// }
		// if (request.getHeader("Referer") == null) {
		// msg = "";
		// return new ModelAndView("redirect:bodyParameterNotAllow");
		// }

		ArrayList<ArrayList<String>> pdfprint = new ArrayList<ArrayList<String>>();

		String Heading = "";
		String username = sessionEdit.getAttribute("username").toString();
		Mmap.put("msg", msg);
		Date date = new Date();
		String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-yyyy").format(date);

		int total_column = 0;
		if (type_of_report.equals("offrs_held")) {
			Heading = "HELD OFFRS LIST";
			pdfprint = report_dao.getUnitReportPersOffrs(roleSusNo, formationtype);
			total_column = 16;
		} else if (type_of_report.equals("offrs_auth")) {
			Heading = "Pers Auth(Offrs)";
			total_column = 7;
			pdfprint = report_dao.getUnitReportPersOffrsAuth(roleSusNo, formationtype);
		} else if (type_of_report.equals("jco_held")) {
			Heading = "JCO HELD LIST";
			total_column = 14;
			pdfprint = report_dao.getUnitReportPersJCo(roleSusNo, formationtype);
		} else if (type_of_report.equals("jco_auth")) {
			Heading = "Pers Auth(Jco)";
			total_column = 7;
			pdfprint = report_dao.getUnitReportPersJcoAuth(roleSusNo, formationtype);
		} else if (type_of_report.equals("civ_auth")) {
			Heading = "Pers Auth(Civ)";
			total_column = 5;
			pdfprint = report_dao.getUnitReportPersCivAuth(roleSusNo, formationtype);
		} else if (type_of_report.equals("civ_held")) {
			Heading = "Pers Held(Civs)";
			total_column = 10;
			pdfprint = report_dao.getUnitReportPersCivs(roleSusNo, formationtype);
		} else if (type_of_report.equals("or_held")) {
			Heading = "Pers Held(OR)";
			total_column = 14;
			pdfprint = report_dao.getUnitReportPersOR(roleSusNo, formationtype);
		} else if (type_of_report.equals("or_auth")) {
			Heading = "Pers Auth(OR)";
			total_column = 7;
			pdfprint = report_dao.getUnitReportPersOrAuth(roleSusNo, formationtype);
		} else if (type_of_report.equals("veh_auth")) {
			Heading = "Veh Auth";
			total_column = 9;
			pdfprint = report_dao.getUnitReportVehAuth(type, roleSusNo, formationtype);
		} else if (type_of_report.equals("veh_held")) {
			Heading = "Veh Held";
			total_column = 9;
			pdfprint = report_dao.getUnitReportVeh(type, roleSusNo, formationtype);
		} else if (type_of_report.equals("eqpts_auth")) {
			Heading = "Wpn & Eqpts Auth";
			total_column = 5;
			pdfprint = report_dao.getUnitReportWpnEqptAuth(type, roleSusNo, formationtype);
		} else if (type_of_report.equals("eqpts_held")) {
			Heading = "Wpn & Eqpts Held";
			total_column = 9;
			pdfprint = report_dao.getUnitReportWpnEqpt(type, roleSusNo, formationtype);
		} else if (type_of_report.equals("itasset_held")) {
			Heading = "IT Asset";
			total_column = 10;
			pdfprint = report_dao.getUnitReportIT_Assets(roleSusNo, formationtype);
		} else if (type_of_report.equals("CurrentMonthReport3008")) {
			Heading = "3008 Current Month Report";
			total_column = 3;
			pdfprint = report_dao.get3008CurrentMonthReport(roleSusNo);
		} else if (type_of_report.equals("CurrentMonthReport3009")) {
			Heading = "3009 Current Month Report";
			total_column = 3;
			pdfprint = report_dao.get3009CurrentMonthReport(roleSusNo);
		} else if (type_of_report.equals("CurrentMonthReportMcr")) {
			Heading = "Mcr Current Month Report";
			total_column = 3;
			pdfprint = report_dao.getMcrCurrentMonthReport(roleSusNo);
		} else if (type_of_report.equals("IT_Assets_holding_as_on_date")) {
			Heading = "IT Assets(holding as on date)";
			total_column = 4;
			pdfprint = report_dao.getUnitWiseItAssetReport(roleSusNo);
		} else if (type_of_report.equals("AvehCurrentMonthReport")) {
			Heading = "A VEH ";
			total_column = 3;
			pdfprint = report_dao.getAvehCurrentMonthReport(roleSusNo);
		} else if (type_of_report.equals("BvehCurrentMonthReport")) {
			Heading = "B VEH ";
			total_column = 3;
			pdfprint = report_dao.getBvehCurrentMonthReport(roleSusNo);
		} else if (type_of_report.equals("CvehCurrentMonthReport")) {
			Heading = "C VEH ";
			total_column = 3;
			pdfprint = report_dao.getCvehCurrentMonthReport(roleSusNo);
		} else if (type_of_report.equals("AllCasualtyCurrentMonthReport")) {
			Heading = "Current Month Report ";
			total_column = 10;
			pdfprint = report_dao.getAllCasualtyCurrentMonth(roleSusNo);
		}

		// if(pdfprint.size() > 0) {
		List<String> TH = new ArrayList<String>();
		if (type_of_report.equals("offrs_held")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("IC NO");
			TH.add("RK");
			TH.add("Name");
			// TH.add("description");
			TH.add("Appt");
			TH.add("DOB");
			TH.add("DOS");
			TH.add("DOC");
			TH.add("TOS");
			TH.add("Med Cat");
			TH.add("COPE");
			TH.add("Army Course");
			TH.add("Civil Qualification");
			TH.add("Spouse Name");
			TH.add("DoM");
		} else if (type_of_report.equals("offrs_auth")) {
			TH.add("Ser No");
			TH.add("Arms_Services");
			TH.add("RK");
			TH.add("Base Auth");
			TH.add("Mod Auth");
			TH.add("Foot Auth");
			TH.add("Total Auth");

		} else if (type_of_report.equals("jco_held")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("Army No");
			TH.add("RK");
			TH.add("Name");
			TH.add("Trade");
			TH.add("DOB");
			TH.add("DOS");
			TH.add("DOE");
			TH.add("TOS");
			TH.add("Med Cat");

			TH.add("Army Course");
			TH.add("Spouse Name");
			TH.add("DoM");
		} else if (type_of_report.equals("jco_auth")) {
			TH.add("Ser No");
			TH.add("Arms_Services");
			TH.add("RK");
			TH.add("Base Auth");
			TH.add("Mod Auth");
			TH.add("Foot Auth");
			TH.add("Total Auth");

		} else if (type_of_report.equals("civ_auth")) {
			TH.add("Gazetted A");
			TH.add("Gazetted B");
			TH.add("Non Gazetted B");
			TH.add("Non Gazetted C");
			TH.add("Remarks");
		} else if (type_of_report.equals("civ_held")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("Emp No");
			TH.add("Designation");
			TH.add("Name");
			TH.add("Gaz/ Non Gazeted");
			TH.add("Industrial/ Non Industrial");
			TH.add("DoB");
			TH.add("DoS");
			TH.add("DoE");

		} else if (type_of_report.equals("or_held")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("Army No");
			TH.add("RK");
			TH.add("Name");
			TH.add("Trade");
			TH.add("DOB");
			TH.add("DOS");
			TH.add("DOE");
			TH.add("TOS");
			TH.add("Med Cat");
			TH.add("Army Course");
			TH.add("Spouse Name");
			TH.add("DoM");

		} else if (type_of_report.equals("or_auth")) {
			TH.add("Ser No");
			TH.add("Arms_Services");
			TH.add("RK");
			TH.add("Base Auth");
			TH.add("Mod Auth");
			TH.add("Foot Auth");
			TH.add("Total Auth");
		} else if (type_of_report.equals("veh_auth")) {
			TH.add("Ser No");
			TH.add("MCT No");
			TH.add("Nomenclature");
			TH.add("Base Authorisation");
			TH.add("Modification Inc/Dec");
			TH.add("General Notes Inc/Dec");
			TH.add("In Lieu Notes Inc/Dec");
			TH.add("Reduced Amount(Due to Inlieu)");
			TH.add("Total");
		} else if (type_of_report.equals("veh_held")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("BA No");
			TH.add("Veh Type");
			TH.add("Broad Cat");
			TH.add("kms Run");
			TH.add("Classification");
			TH.add("Vintage");
			TH.add("Ser/ Unsv");
		} else if (type_of_report.equals("eqpts_auth")) {
			TH.add("Ser No");
			TH.add("Item Code");
			TH.add("Nomenclature");
			TH.add("Type Of EQPTS");
			TH.add("Total");
		} else if (type_of_report.equals("eqpts_held")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("Broad Cat");
			TH.add("PRF");
			TH.add("Nomenclautre");
			TH.add("Census No");
			TH.add("Registration No");
			TH.add("Type Of Holding");
			TH.add("Ser/ Unsv");
		} else if (type_of_report.equals("itasset_held")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("Asswts Types(Coumputing/Peripheral)");
			TH.add("Asset Name");
			TH.add("Machine No/ Ser No");
			TH.add("OS");
			TH.add("Office Software");
			TH.add("Yr of Procurement/ Vintage");
			TH.add("Processer Type");
			TH.add("Dply enmvt");
		} else if (type_of_report.equals("CurrentMonthReport3008")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("Status");
		} else if (type_of_report.equals("CurrentMonthReport3009")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("Status");
		} else if (type_of_report.equals("CurrentMonthReportMcr")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("Status");
		} else if (type_of_report.equals("IT_Assets_holding_as_on_date")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("Computing");
			TH.add("Peripheral");
		} else if (type_of_report.equals("AvehCurrentMonthReport")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("Status");
		} else if (type_of_report.equals("BvehCurrentMonthReport")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("Status");
		} else if (type_of_report.equals("CvehCurrentMonthReport")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("Status");

		} else if (type_of_report.equals("AllCasualtyCurrentMonthReport")) {
			TH.add("Ser No");
			TH.add("Unit Name");
			TH.add("3008");
			TH.add("3009");
			TH.add("A Veh");
			TH.add("B Veh");
			TH.add("C Veh");
			TH.add("MCR");
			TH.add("Computing");
			TH.add("Peripheral");
		}
		return new ModelAndView(new Held_Offrs_Download_report(Heading, TH, foot, username, pdfprint, total_column),
				"userList", pdfprint);
	}

	@RequestMapping(value = "/Download_unit_eqpts_held_dashboard__Report", method = RequestMethod.POST)
	public ModelAndView Download_unit_eqpts_held_dashboard__Report(
			@ModelAttribute("type_of_report") String type_of_report, @ModelAttribute("type") String type, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		String formationtype = sessionEdit.getAttribute("roleSubAccess").toString();
		// Boolean val = roledao.ScreenRedirect("Auth_and_Posted_Strength", roleid);
		String roleSusNo = sessionEdit.getAttribute("roleSusNo").toString();
		// if (val == false) {
		// return new ModelAndView("AccessTiles");
		// }
		// if (request.getHeader("Referer") == null) {
		// msg = "";
		// return new ModelAndView("redirect:bodyParameterNotAllow");
		// }

		ArrayList<ArrayList<String>> pdfprint = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> pdf_summary = new ArrayList<ArrayList<String>>();

		String Heading = "";
		String username = sessionEdit.getAttribute("username").toString();
		Mmap.put("msg", msg);
		Date date = new Date();
		String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-yyyy").format(date);

		Heading = "Wpn & Eqpts Held";
		pdfprint = report_dao.getUnitReportWpnEqpt(type, roleSusNo, formationtype);
		pdf_summary = report_dao.getUnitReportWpnEqptSummary(type, roleSusNo, formationtype);

		// if(pdfprint.size() > 0) {
		// HERE
		List<String> TH = new ArrayList<String>();
		TH.add("Ser No");
		TH.add("Unit Name");
		TH.add("Broad Cat");
		TH.add("PRF");
		TH.add("Nomenclautre");
		TH.add("Census No");
		TH.add("Registration No");
		TH.add("Type Of Holding");
		TH.add("Ser/ Unsv");

		List<String> TH2 = new ArrayList<String>();
		TH2.add("Ser No");
		TH2.add("prf");
		TH2.add("total");

		return new ModelAndView(new Unit_Dashboard_eqpt_held_report(TH, TH2, foot, username, pdfprint, pdf_summary),
				"userList", pdfprint);
	}

	@RequestMapping(value = "/tmsBrodcat", method = RequestMethod.POST)
	public @ResponseBody List<String> tmsBrodcat(String broadcat, String type_veh, String type,
			HttpSession sessionUserId) {
		String teString = "";
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		List<String> list = unit_dao.tmsBrodecat(roleSusNo, type_veh, broadcat, type, formationtype);
		List<String> FinalList = new ArrayList<String>();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");

		return FinalList;
	}

	@RequestMapping(value = "/tmsba_no", method = RequestMethod.POST)
	public @ResponseBody List<String> tmsba_no(String broadcat, String type_veh, String type,
			HttpSession sessionUserId) {
		String teString = "";
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		List<String> list = unit_dao.tmsBa_No(roleSusNo, type_veh, broadcat, type, formationtype);
		List<String> FinalList = new ArrayList<String>();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");

		return FinalList;
	}

	@RequestMapping(value = "/tmsveh_type", method = RequestMethod.POST)
	public @ResponseBody List<String> tmsveh_type(String broadcat, String type_veh, String type,
			HttpSession sessionUserId) {
		String teString = "";
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
		List<String> list = unit_dao.tmsveh_type(roleSusNo, type_veh, broadcat, type, formationtype);
		List<String> FinalList = new ArrayList<String>();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");

		return FinalList;
	}
	// pranay 15.07 bde dashboard

	@RequestMapping(value = "/Get3008monthlyavg", method = RequestMethod.POST)
	public @ResponseBody long Get3008monthlyavg(String sus_no, HttpSession session, HttpServletRequest request) {

		long avg3008 = unit_dao.Get3008monthlyavg(sus_no, session);

		return avg3008;
	}

	@RequestMapping(value = "/Get3009monthlyavg", method = RequestMethod.POST)
	public @ResponseBody long Get3009monthlyavg(String sus_no, HttpSession session, HttpServletRequest request) {

		long avg3009 = unit_dao.Get3009monthlyavg(sus_no, session);

		return avg3009;
	}

	@RequestMapping(value = "/itassetcount", method = RequestMethod.POST)
	public @ResponseBody long itassetcount(String sus_no, HttpSession session, HttpServletRequest request) {

		long itassetcount = unit_dao.itassetcount(sus_no, session);

		return itassetcount;
	}

	@RequestMapping(value = "/mcravg", method = RequestMethod.POST)
	public @ResponseBody long mcravg(String sus_no, HttpSession session, HttpServletRequest request) {

		long mcravg = unit_dao.mcravg(sus_no, session);

		return mcravg;
	}

	@RequestMapping(value = "/GetAvehmonthlyavg", method = RequestMethod.POST)
	public @ResponseBody long GetAvehmonthlyavg(String sus_no, HttpSession session, HttpServletRequest request) {

		long mcravg = unit_dao.GetAvehmonthlyavg(sus_no, session);

		return mcravg;
	}

	@RequestMapping(value = "/GetBvehmonthlyavg", method = RequestMethod.POST)
	public @ResponseBody long GetBvehmonthlyavg(String sus_no, HttpSession session, HttpServletRequest request) {

		long mcravg = unit_dao.GetBvehmonthlyavg(sus_no, session);

		return mcravg;
	}

	@RequestMapping(value = "/GetCvehmonthlyavg", method = RequestMethod.POST)
	public @ResponseBody long GetCvehmonthlyavg(String sus_no, HttpSession session, HttpServletRequest request) {

		long mcravg = unit_dao.GetCvehmonthlyavg(sus_no, session);

		return mcravg;
	}

	// 3008 current month
	@RequestMapping(value = "/admin/CurrentMonthReport3008", method = RequestMethod.GET)
	public ModelAndView CurrentMonthReport3008(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String sus_no,

			HttpServletRequest request) {

		return new ModelAndView("CurrentMonthReport3008Tiles");

	}

	@RequestMapping(value = "/get3008CurrentMonthlistCount", method = RequestMethod.POST)
	public @ResponseBody long get3008CurrentMonthlistCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.get3008CurrentMonthlistCount(Search, orderColunm, orderType, sessionUserId, roleSusNo);
	}

	@RequestMapping(value = "/get3008CurrentMonthlist", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get3008CurrentMonthlist(int startPage, String Search, int pageLength,
			String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.get3008CurrentMonthlist(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo);
	}

	// 3009 current month
	@RequestMapping(value = "/admin/CurrentMonthReport3009", method = RequestMethod.GET)
	public ModelAndView CurrentMonthReport3009(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String sus_no,

			HttpServletRequest request) {

		return new ModelAndView("CurrentMonthReport3009Tiles");

	}

	@RequestMapping(value = "/get3009CurrentMonthlistCount", method = RequestMethod.POST)
	public @ResponseBody long get3009CurrentMonthlistCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.get3009CurrentMonthlistCount(Search, orderColunm, orderType, sessionUserId, roleSusNo);
	}

	@RequestMapping(value = "/get3009CurrentMonthlist", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get3009CurrentMonthlist(int startPage, String Search, int pageLength,
			String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.get3009CurrentMonthlist(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo);
	}

	// Mcr current month
	@RequestMapping(value = "/admin/CurrentMonthReportMcr", method = RequestMethod.GET)
	public ModelAndView CurrentMonthReportMcr(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String sus_no,

			HttpServletRequest request) {

		return new ModelAndView("CurrentMonthReportMcrTiles");

	}

	@RequestMapping(value = "/getMcrCurrentMonthlistCount", method = RequestMethod.POST)
	public @ResponseBody long getMcrCurrentMonthlistCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.getMcrCurrentMonthlistCount(Search, orderColunm, orderType, sessionUserId, roleSusNo);
	}

	@RequestMapping(value = "/getMcrCurrentMonthlist", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getMcrCurrentMonthlist(int startPage, String Search, int pageLength,
			String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.getMcrCurrentMonthlist(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo);
	}

	// IT Assets(holding as on date)
	@RequestMapping(value = "/admin/UnitWiseItAssetCount", method = RequestMethod.GET)
	public ModelAndView UnitWiseItAssetCount(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String sus_no,

			HttpServletRequest request) {

		return new ModelAndView("UnitWiseItAssetTiles");

	}

	@RequestMapping(value = "/IT_Assets_holding_as_on_dateCount", method = RequestMethod.POST)
	public @ResponseBody long IT_Assets_holding_as_on_dateCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.IT_Assets_holding_as_on_dateCount(Search, orderColunm, orderType, sessionUserId, roleSusNo);
	}

	@RequestMapping(value = "/IT_Assets_holding_as_on_dateList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> IT_Assets_holding_as_on_dateList(int startPage, String Search,
			int pageLength, String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.IT_Assets_holding_as_on_dateList(startPage, pageLength, Search, orderColunm, orderType,
				sessionUserId, roleSusNo);
	}

	// A veh current month
	@RequestMapping(value = "/admin/AvehCurrentMonth", method = RequestMethod.GET)
	public ModelAndView AvehCurrentMonth(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String sus_no,

			HttpServletRequest request) {

		return new ModelAndView("CurrentMonthReportAvehTiles");

	}

	@RequestMapping(value = "/getAvehcurrentMonthCount", method = RequestMethod.POST)
	public @ResponseBody long getAvehcurrentMonthCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.getAvehcurrentMonthCount(Search, orderColunm, orderType, sessionUserId, roleSusNo);
	}

	@RequestMapping(value = "/getAvehcurrentMonthList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getAvehcurrentMonthList(int startPage, String Search, int pageLength,
			String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.getAvehcurrentMonthList(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo);
	}

	// B veh current month
	@RequestMapping(value = "/admin/BvehCurrentMonth", method = RequestMethod.GET)
	public ModelAndView BvehCurrentMonth(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String sus_no,

			HttpServletRequest request) {

		return new ModelAndView("CurrentMonthReportBvehTiles");

	}

	@RequestMapping(value = "/getBvehcurrentMonthCount", method = RequestMethod.POST)
	public @ResponseBody long getBvehcurrentMonthCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.getAvehcurrentMonthCount(Search, orderColunm, orderType, sessionUserId, roleSusNo);
	}

	@RequestMapping(value = "/getBvehcurrentMonthList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getBvehcurrentMonthList(int startPage, String Search, int pageLength,
			String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.getBvehcurrentMonthList(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo);
	}

	// C veh current month
	@RequestMapping(value = "/admin/CvehCurrentMonth", method = RequestMethod.GET)
	public ModelAndView CvehCurrentMonth(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String sus_no,

			HttpServletRequest request) {

		return new ModelAndView("CurrentMonthReportCvehTiles");

	}

	@RequestMapping(value = "/getCvehcurrentMonthCount", method = RequestMethod.POST)
	public @ResponseBody long getCvehcurrentMonthCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.getCvehcurrentMonthCount(Search, orderColunm, orderType, sessionUserId, roleSusNo);
	}

	@RequestMapping(value = "/getCvehcurrentMonthList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCvehcurrentMonthList(int startPage, String Search, int pageLength,
			String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.getCvehcurrentMonthList(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo);
	}

	// C veh current month
	@RequestMapping(value = "/admin/viewallmonthlyCount", method = RequestMethod.GET)
	public ModelAndView viewallmonthlyCount(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String sus_no,

			HttpServletRequest request) {

		return new ModelAndView("AllCasualtyCurrentMonthTiles");

	}

	@RequestMapping(value = "/getAllCasualtyListCount", method = RequestMethod.POST)
	public @ResponseBody long getAllCasualtyListCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.getAllCasualtyCount(Search, orderColunm, orderType, sessionUserId, roleSusNo);
	}

	@RequestMapping(value = "/getAllCasualtyList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getAllCasualtyList(int startPage, String Search, int pageLength,
			String orderColunm, String orderType, HttpSession sessionUserId) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return unit_dao.getAllCasualtyList(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				roleSusNo);
	}

	@RequestMapping(value = "/data_table_popup_bde_data", method = RequestMethod.POST)
	public ModelAndView data_table_popup_bde_data(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "unitName", required = false) String unit_name,
			@RequestParam(value = "susno", required = false) String susno,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String unitName = "";
		String formCodeControl = "";
		Boolean val = roledao.CheckDashboard("UnitDashboard", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//		}
		Mmap.put("form_code", "");

		Mmap.put("msg", msg);
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		Query q = ses1.createQuery(
				"select distinct unit_name,form_code_control from Miso_Orbat_Unt_Dtl where sus_no=:sus_no   and status_sus_no='Active'");
		q.setParameter("sus_no", susno);
		// String Unit_name = (String) q.uniqueResult();

		List<Object[]> results = q.list();
		for (Object[] result : results) {
			unitName = (String) result[0];
			formCodeControl = (String) result[1];

		}

		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		Mmap.put("sus_no", susno);
		Mmap.put("unit_name", unit_name);
		Mmap.put("formCodeControl", formCodeControl);
		List<Map<String, Object>> lastupdateList = unit_dao.getLastUpdateDate(session, roleSusNo);
		Mmap.put("lastupdateList", lastupdateList);

		return new ModelAndView("popup_bde_Tiles");

	}

}
