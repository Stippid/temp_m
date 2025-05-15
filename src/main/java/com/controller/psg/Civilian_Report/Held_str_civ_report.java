package com.controller.psg.Civilian_Report;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.controller.ExportFile.AppxAB_PDF_Report;
import com.controller.ExportFile.PDF_Held_Str_Civ;
import com.controller.JCO_ExportFile.Excel_To_Export_Download_Search_Report_jco_Table_Report;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.controller.tms.IUT_Controller;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Civilian_Report.held_str_civ_report_nomi_Dao;
import com.dao.psg.miso_olap.JDBCMISO_OLAPDAO;
import com.dao.psg.miso_olap.JDBCMISO_OLAPDAOImpl;
import com.models.Tbl_CodesForm;
import com.models.psg.Report.TB_PSG_APPX_A_B_CIV_MAIN;
import com.models.psg.Report.TB_PSG_CIV_MAIN;
import com.models.psg.Report.TB_PSG_CIV_MAIN_DETAILS;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Held_str_civ_report {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private held_str_civ_report_nomi_Dao held;
	ValidationController valid = new ValidationController();
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	Psg_CommonController mcommon = new Psg_CommonController();
	IUT_Controller iut = new IUT_Controller();
	JDBCMISO_OLAPDAO o = new JDBCMISO_OLAPDAOImpl();



	@RequestMapping(value = "/admin/appxAandB_civ_Url", method = RequestMethod.GET)
	public ModelAndView appxAandB_civ_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("appxAandB_civ_Url", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		LocalDate currentDate = LocalDate.now();
		LocalDate previousMonth = currentDate.minusMonths(1);
		int month = previousMonth.getMonthValue();
		int year = previousMonth.getYear();


		String formattedMonth = String.format("%02d",month);
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		if(roleAccess.equals("Unit")) {
			if (all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).size() > 0) {
				Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
				String comdName= held.getCommandName(roleSusNo);
				Mmap.put("cmdname", comdName);
			}

			ArrayList<ArrayList<String>> authciv = held.getCiviliadnAuth(roleSusNo);
			Mmap.put("authciv", authciv);

			Map<String, String> regEstAppxA = held.getRegEstAppxAData(roleSusNo,String.valueOf(year),formattedMonth);
			Mmap.put("data", regEstAppxA);

			Map<String, String> nonRegularCiv = held.getNonRegularCivList(roleSusNo, String.valueOf(year),formattedMonth);
			Mmap.put("nonRegularCiv", nonRegularCiv);

			Map<String, String> summaryreport = held.getCivSummaryList(roleSusNo,String.valueOf(year),formattedMonth);
			Mmap.put("summaryreport", summaryreport);

		}

		Mmap.put("msg", msg);
		Mmap.put("roleType", roleType);
		Mmap.put("month1", month);
		Mmap.put("year1", year);
		Mmap.put("role", roleAccess);

		return new ModelAndView("appxAandB_civilian_Tiles");
	}




	@RequestMapping(value = "/Getsearch_nominrollcount", method = RequestMethod.POST)
	public @ResponseBody long Getsearch_nominrollcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no, String civilian_status)
					throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		return held.Getsearch_nominrollcount_civ(Search, orderColunm, orderType, sessionUserId, sus_no,
				civilian_status);
	}

	@RequestMapping(value = "/Getsearch_search_nominroll", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getsearch_search_nominroll(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no, String civilian_status) throws SQLException {
		String Month1 = "00";
		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";
		String LDate = year + Month1;
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = held.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}
		return held.Getsearch_search_nominrollciv(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				sus_no, civilian_status);
	}



	@RequestMapping(value = "/Getsearch_authcount", method = RequestMethod.POST)
	public @ResponseBody long Getsearch_authcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no, String civilian_status)
					throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		return held.Getsearch_authcountciv(Search, orderColunm, orderType, sessionUserId, sus_no, civilian_status);
	}



	@RequestMapping(value = "/Getsearch_search_auth", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getsearch_search_auth(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no, String civilian_status) throws SQLException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		return held.Getsearch_search_authciv(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				sus_no, civilian_status);
	}



	@RequestMapping(value = "/Getsearch_heldstrcivcount", method = RequestMethod.POST)
	public @ResponseBody long Getsearch_heldstrcivcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no, String civilian_status)
					throws SQLException {
		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = held.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return held.Getsearch_heldstrcivcountciv(Search, orderColunm, orderType, sessionUserId, sus_no, FDate, LDate,
				civilian_status);
	}



	@RequestMapping(value = "/Getsearch_heldstrciv", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getsearch_heldstrciv(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no, String civilian_status) throws SQLException {

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = held.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return held.Getsearch_heldstrcivciv(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				sus_no, FDate, LDate, civilian_status);
	}
	//-- get total non _regular civilian count-----//

	@RequestMapping(value = "/Get_total_nonregular_civCount", method = RequestMethod.POST)
	public @ResponseBody long  Get_total_nonregular_civCount(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no, String civilian_status) throws SQLException {

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = held.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return held.Get_total_nonregular_civCount(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				sus_no, FDate, LDate, civilian_status);
	}

	//--------Get total non_regular civilian detail-------//
	@RequestMapping(value = "/Get_total_nonregular_civ", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Get_total_nonregular_civ(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no, String civilian_status) throws SQLException {

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = held.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return held.Get_total_nonregular_civ(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				sus_no, FDate, LDate, civilian_status);
	}

	//--------insert civilian data in table----------------

	@RequestMapping(value = "/admin/Insert_civ", method = RequestMethod.POST)
	public @ResponseBody String Insert_civ(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws SQLException {
		String msg = "";
		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String userId = session.getAttribute("userId").toString();

		// changes utk
		/// bisag v2 260822 (date parse added)
		String p_dt = request.getParameter("present_return_dt");
		String l_dt = request.getParameter("last_return_dt");
		String held_jco_hid = request.getParameter("held_jco_hid");

		String[] act = p_dt.split("/");

		String p_dt_act = act[2] + "/" + act[1] + "/" + act[0];

		Date date = new Date(p_dt_act);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String present_rt_date = formatter.format(date);
		String last_rt_date="";
		if (l_dt != null&& !l_dt.trim().isEmpty()) {
			String[] acts = l_dt.split("/");
			String l_dt_act = acts[2] + "/" + acts[1] + "/" + acts[0];
			Date dates = new Date(l_dt_act);
			last_rt_date = formatter.format(dates);
		}else {
			last_rt_date=null;
		}


		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		String Month1 = "00";

		if (Integer.parseInt(request.getParameter("month")) > 9) {
			Month1 = request.getParameter("month");
		} else {
			Month1 = "0" + request.getParameter("month");
		}

		if (request.getParameter("year") != "") {
			if (valid.isOnlyNumer(request.getParameter("year")) == true) {
				msg = " Year " + valid.isOnlyNumerMSG;
				return msg;
			}
			if (!valid.YearLength(request.getParameter("year"))) {
				msg = valid.YearMSG;
				return msg;
			}
		}

		String FDate = request.getParameter("year") + "/" + Month1 + "/" + "01";
		String LDate = request.getParameter("year") + Month1;

		ArrayList<ArrayList<String>> list_date = held.getldate(LDate);

		Mmap.put("list", list_date);
		Mmap.put("size", list_date.size());
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}
		String sus_no = "";
		if (request.getParameter("unit_sus_no") == null || request.getParameter("unit_sus_no").equals("")
				|| request.getParameter("unit_sus_no").equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = request.getParameter("unit_sus_no");
		}

		List<TB_PSG_CIV_MAIN> version = held.Get_civ_VersionData(request.getParameter("month"),
				request.getParameter("year"), roleSusNo,request.getParameter("civillian_status"));

		if (request.getParameter("present_return_no").equals("")
				|| request.getParameter("present_return_no").equals(null)
				|| request.getParameter("present_return_no").equals("null")) {

			msg = "Please Enter Present Return No.";
			return msg;
		}
		if (!request.getParameter("present_return_no").equals("")
				|| !request.getParameter("present_return_no").equals(null)
				|| !request.getParameter("present_return_no").equals("null")) {
			if (!valid.isvalidLength(request.getParameter("present_return_no"), valid.id_card_noMax,
					valid.id_card_noMin)) {
				msg = "Present Return No " + valid.isValidLengthMSG;
				return msg;
			}
		}
		if (request.getParameter("present_return_dt").equals("")
				|| request.getParameter("present_return_dt").equals(null)
				|| request.getParameter("present_return_dt").equals("null")
				|| request.getParameter("present_return_dt").equals("DD/MM/YYYY")) {
			msg = "Please Enter Present Return Dt.";
			return msg;
		}

		if (!request.getParameter("present_return_dt").equals("")
				|| !request.getParameter("present_return_dt").equals(null)
				|| !request.getParameter("present_return_dt").equals("null")
				|| !request.getParameter("present_return_dt").equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(request.getParameter("present_return_dt"))) {
				msg = valid.isValidDateMSG + " of Present Return No.";
				return msg;
			}
		}

		if (!request.getParameter("observation").equals("") || !request.getParameter("observation").equals(null)
				|| !request.getParameter("observation").equals("null")) {
			if (!valid.isvalidLength(request.getParameter("observation"), valid.remarkMax, valid.remarkMin)) {
				msg = "Observation " + valid.isValidLengthMSG;
				return msg;
			}
		}
		if (version.size() != 0) {
			int Status = version.get(0).getStatus();
			if (Status == 0) {
				msg = "Update Offcr Data are still in Pending for Approval.Pl Notify the Approval to Approve all the Pending Record of Update Offcr Form.";
				return msg;
			}
		}
		try {

			Boolean Insert = held.Insert_Report_civ(username, sus_no, FDate, request.getParameter("month"),
					request.getParameter("year"), userId, request.getParameter("present_return_no"), request.getParameter("last_return_no"), present_rt_date,last_rt_date,
					request.getParameter("observation"), LDate, request.getParameter("civilian_status"));
			tx.commit();
			if (Insert == true) {
				msg = "Data Save/Updated Successfully";
				return msg;
			} else {
				msg = "Data Not Updated Or Saved";
				return msg;
			}
			// msg = "Data Save/Updated Successfully";
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Data Not Updated Or Saved";
			} catch (RuntimeException rbe) {
				msg = "Data Not Updated Or Saved";
			}
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}

	public List<TB_PSG_CIV_MAIN_DETAILS> Get_CIV_DetailsData1(String month, String year, String roleSusNo) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_CIV_MAIN_DETAILS where month=:month and year=:year and sus_no=:roleSusNo and status=1 order by id desc ";
		Query query = sessionHQL.createQuery(hqlUpdate).setString("month", month).setString("year", year)
				.setString("roleSusNo", roleSusNo);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CIV_MAIN_DETAILS> list = query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public List<TB_PSG_CIV_MAIN_DETAILS> Get_CIV_DetailsData(String month, String year, String roleSusNo,
			String version) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_CIV_MAIN_DETAILS where month=:month and year=:year and sus_no=:roleSusNo and version=:version and status = 0 order by id desc";
		Query query = sessionHQL.createQuery(hqlUpdate).setString("month", month).setString("year", year)
				.setString("roleSusNo", roleSusNo).setString("version", version);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CIV_MAIN_DETAILS> list = query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	// ------initial page load civ for approve----------------//

	@RequestMapping(value = "/admin/Search_Report_Civ_Url", method = RequestMethod.GET)
	public ModelAndView Search_Report_Civ_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Report_3009_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		if (all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		Mmap.put("roleType", roleType);
		return new ModelAndView("search_civilian_report_Tiles");
	}

	// -------End page load-----------//

	// ------------------------get civ submitted data when click on search
	// button-------------------------------------//

	@RequestMapping(value = "/admin/GetSearch_Report_Civ", method = RequestMethod.POST)
	public ModelAndView GetSearch_Report_Civ(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no)
					throws ServletException, IOException, ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Report_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		if (year != "") {
			if (valid.isOnlyNumer(year) == true) {
				Mmap.put("msg", " Year " + valid.isOnlyNumerMSG);
				return new ModelAndView("redirect:Search_Report_Civ_Url");
			}
			if (!valid.YearLength(year)) {
				Mmap.put("msg", valid.YearMSG);
				return new ModelAndView("redirect:Search_Report_Civ_Url");
			}
		}
		if (unit_sus_no != "") {
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				return new ModelAndView("redirect:Search_Report_Civ_Url");
			}

			if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
				return new ModelAndView("redirect:Search_Report_Civ_Url");
			}
		}

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> list = held.Search_civ_Report_Version(month, year, session, status, unit_sus_no);
		Mmap.put("list", list);
		Mmap.put("size", list.size());

		if (all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}

		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("month1", month);
		Mmap.put("year1", year);
		Mmap.put("status1", status);
		Mmap.put("unit_sus_no1", unit_sus_no);
		return new ModelAndView("search_civilian_report_Tiles");
	}

	// -----------------------End getdata-------------------------------------//

	public List<String> getActiveUnitNameFromSusNo_Without_Enc(String sus_no, HttpSession sessionA) {
		/*
		 * String roleAccess = sessionA.getAttribute("roleAccess").toString(); String
		 * roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		 */
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = q.list();
		tx.commit();
		session.close();
		return list;
	}

	// ----------------------load page data for approve civ submitted
	// form-----------------------//
	@RequestMapping(value = "/Approve_Report_Civ_Url", method = RequestMethod.POST)
	public ModelAndView Approve_Report_Civ_Url(@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "year5", required = false) String year,
			@RequestParam(value = "month5", required = false) String month,
			@RequestParam(value = "version5", required = false) String version,
			@RequestParam(value = "civilian_status5", required = false) String civilian_status,
			@RequestParam(value = "unit_sus_no5", required = false) String unit_sus_no, Authentication authentication,
			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Report_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		ArrayList<ArrayList<String>> list_date = held.getldate(LDate);

		Mmap.put("list", list_date);
		Mmap.put("size", list_date.size());
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}

		if (roleType.equals("APP") || roleType.equals("ALL")) {

			ArrayList<ArrayList<String>> list_sup = held.Search_Report_civnominalroll(month, year, session, version,
					sus_no,civilian_status);
			Mmap.put("list", list_sup);
			Mmap.put("size", list_sup.size());

			if(civilian_status.equals("R")) {
				ArrayList<ArrayList<String>> auth_civ_list = held.Search_Report_auth_civ(month, year, session, version,
						sus_no);
				Mmap.put("list9", auth_civ_list);
				Mmap.put("size9", auth_civ_list.size());

				ArrayList<ArrayList<String>> list_sup8 = held.Search_Report_posted_civ(month, year, session, version,
						sus_no);
				Mmap.put("list8", list_sup8);
				Mmap.put("size8", list_sup8.size());

				ArrayList<ArrayList<String>> summaryList = held.Search_Report_summary_civ(month, year, session, version,
						unit_sus_no);
				Mmap.put("list7", summaryList);
				Mmap.put("size7", summaryList.size());

			}else {

				ArrayList<ArrayList<String>> non_regular_civlist = held.non_regular_civlist(month, year, session, version,
						unit_sus_no);
				Mmap.put("list6", non_regular_civlist);
				Mmap.put("size6", non_regular_civlist.size());

			}
		}

		List<TB_PSG_CIV_MAIN_DETAILS> MainDD = Get_CIV_DetailsData1(month, year, sus_no);

		if (MainDD.size() > 0 && !MainDD.get(0).getVersion().equals("1")) {
			Mmap.put("last_return_no1", MainDD.get(0).getPresent_return_no());
			Mmap.put("last_return_dt1", MainDD.get(0).getPresent_return_dt());
		}
		if (MainDD.size() > 0 && String.valueOf(MainDD.get(0).getStatus()).equals("1")
				&& MainDD.get(0).getVersion().equals("1")) {
			Mmap.put("last_return_no1", MainDD.get(0).getPresent_return_no());
			Mmap.put("last_return_dt1", MainDD.get(0).getPresent_return_dt());
		}
		if (MainDD.size() > 0 && String.valueOf(MainDD.get(0).getStatus()).equals("0")
				&& MainDD.get(0).getVersion().equals("1")) {
			Mmap.put("last_return_no1", "");
			Mmap.put("last_return_dt1", "");
		}

		List<TB_PSG_CIV_MAIN_DETAILS> cc = Get_CIV_DetailsData(month, year, sus_no, version);

		Mmap.put("msg", msg);
		Mmap.put("month5", month);
		Mmap.put("year5", year);
		Mmap.put("version5", version);
		Mmap.put("unit_sus_no5", unit_sus_no);
		Mmap.put("civilian_status5", civilian_status);

		if (cc.size() > 0) {
			Mmap.put("present_return_no5", cc.get(0).getPresent_return_no());
			Mmap.put("present_return_dt5", cc.get(0).getPresent_return_dt());
			Mmap.put("observation5", cc.get(0).getObservation());
			Mmap.put("civilianstatus", cc.get(0).getCivilian_status());
		}

		Mmap.put("roleSusNo", roleSusNo);
		if (all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		Mmap.put("roleType", roleType);

		return new ModelAndView("Approve_Report_Civ_Tiles");

	}
	// ----------------------end page load civ for approve--------------------//

	// -----------approve button ---------------------------------------//
	@RequestMapping(value = "/Approve_civ", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Approve_civ(HttpServletRequest request, HttpSession session, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year,
			@RequestParam(value = "version1", required = false) String version,
			@RequestParam(value = "civilian_status5", required = false) String civilian_status,
			@RequestParam(value = "unit_sus_no5", required = false) String unit_sus_no, Authentication authentication)
					throws SQLException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Report_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		List<String> liststr = new ArrayList<String>();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		String username = session.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Boolean Approve = held.Approve_Report_civ(username, sus_no, month, year, version,civilian_status);

		tx.commit();
		sessionHQL.close();

		if (Approve == true) {
			liststr.add("Approved Successfully.");
		} else {
			liststr.add("Approved Not Successfully.");
		}
		model.put("msg", liststr.get(0));

		return new ModelAndView("redirect:Search_Report_Civ_Url");
	}

	// -----------------delete submitted civ for approval---------------------//
	@RequestMapping(value = "/delete_Report_civ", method = RequestMethod.POST)
	public @ResponseBody ModelAndView delete_Report_civ(HttpServletRequest request, HttpSession session, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "month6", required = false) String month,
			@RequestParam(value = "year6", required = false) String year,
			@RequestParam(value = "version6", required = false) String version,
			@RequestParam(value = "civilian_status6", required = false) String civilian_status,
			@RequestParam(value = "unit_sus_no6", required = false) String unit_sus_no, HttpSession sessionA,
			Authentication authentication) throws SQLException {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Report_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		String userId = session.getAttribute("userId").toString();

		List<String> liststr = new ArrayList<String>();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		Boolean Delete = held.Delete_Report_civ(username, sus_no, month, year, version,civilian_status);

		tx.commit();
		sessionHQL.close();

		if (Delete == true) {
			liststr.add("Deleted Successfully.");
		} else {
			liststr.add("Deleted Not Successfully.");
		}
		model.put("msg", liststr.get(0));

		return new ModelAndView("redirect:Search_Report_Civ_Url");
	}
	// -----------------end---------------------------------------------//


	@RequestMapping(value = "/GetHeldStrCivNominalrollDataCount", method = RequestMethod.POST)
	public @ResponseBody long GetHeldStrCivNominalrollDataCount(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no, String comd,String corps,String div,String bde) throws SQLException {

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}

		return held.GetHeldStrCivNominalrollDataCount(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				sus_no, comd,corps,div,bde);
	}

	@RequestMapping(value = "/GetHeldStrCivNominalrollDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> GetHeldStrCivNominalrollDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no, String comd,String corps,String div,String bde) throws SQLException {

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}

		return held.GetHeldStrCivNominalrollDataList(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				sus_no, comd,corps,div,bde);
	}


	//	=========================== held str civ new as per provided list====================

	@RequestMapping(value = "/admin/held_str_civilian_Url", method = RequestMethod.GET)
	public ModelAndView held_str_civilian_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String unit_sus_no = sessionA.getAttribute("roleSusNo").toString();

		Boolean val = roledao.ScreenRedirect("held_str_civilian_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);

		if(request.getHeader("Referer") == null ) { msg = ""; }

		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();

		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
		}


		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();

		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);

				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);

				List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);

				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);

				List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);

				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);

				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
			}
		}
		if(roleAccess.equals("Unit")){
			Mmap.put("unit_sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));

			List<String> formation =mcommon.getformationfromSus_NOList(roleSusNo);
			roleFormationNo = formation.get(0);

			String command = String.valueOf(roleFormationNo.charAt(0));
			List<Tbl_CodesForm> comd=getFormationList("Command",command);
			Mmap.put("getCommandList",comd);

			String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
			List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
			Mmap.put("getCorpsList",corps);

			String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
			List<Tbl_CodesForm> Bn=getFormationList("Division",div);
			Mmap.put("getDivList",Bn);

			String bde_code = roleFormationNo;
			List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
			Mmap.put("getBdeList",bde);

		}
		if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd=all.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
		}
		Mmap.put("msg", msg);

		Mmap.put("date", date1);
		Mmap.put("msg", msg);

		return new ModelAndView("held_str_civ_Tiles");
	}

	public List<Tbl_CodesForm> getFormationList(String level_in_hierarchy,String fcode) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = null;

		if(level_in_hierarchy.equals("Command")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,1),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Command') and SUBSTR(form_code_control,1,1) =:formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode);
		}
		if(level_in_hierarchy.equals("Corps")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,3),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Corps') and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Division")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,6),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Division' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Brigade")) {
			q = sessionHQL.createQuery("select form_code_control,unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Brigade' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}

		@SuppressWarnings("unchecked")
		List<Tbl_CodesForm> list = q.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}


	@RequestMapping(value = "/downloadHeldStrCiv",method = RequestMethod.POST)
	public ModelAndView downloadHeldStrCiv(
			@ModelAttribute("cont_comd_dn") String cont_comd,@ModelAttribute("cont_corps_dn") String cont_corps,@ModelAttribute("cont_div_dn") String cont_div,
			@ModelAttribute("cont_bde_dn") String cont_bde,
			@ModelAttribute("cont_comd_tx") String cont_comd_tx,@ModelAttribute("cont_corps_tx") String cont_corps_tx,@ModelAttribute("cont_div_tx") String cont_div_tx,
			@ModelAttribute("cont_bde_tx") String cont_bde_tx,
			@ModelAttribute("unit_name_dn") String unit_name,@ModelAttribute("sus_no_dn") String sus_no,
			ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Mon_str_jcos_or_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}



		ArrayList<ArrayList<String>> pdfprint = held.pdf_Download_held_str_civ(cont_comd,cont_corps,cont_div,cont_bde,sus_no);


		String username =  sessionEdit.getAttribute("username").toString();
		Mmap.put("msg", msg);
		String Heading ="";
		Date date = new Date();
		String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);

		List<String> TH = new ArrayList<String>();

		TH.add("Ser No");
		TH.add("Employee No");
		TH.add("Employee Name");
		TH.add("Designation");
		TH.add("DOB");
		TH.add("Gazeeted/Non Gazzeted");
		TH.add("Group");
		TH.add("Non Industrial/Industrial");
		TH.add("Joining in Govt Service Date");
		TH.add("Tos Date");
		TH.add("Designation Date");
		TH.add("Gender");


		if(cont_comd_tx.equals("--Select--")) {
			cont_comd_tx = "";
		}
		if(cont_corps_tx.equals("--Select--")) {
			cont_corps_tx = "";
		}
		if(cont_div_tx.equals("--Select--")) {
			cont_div_tx = "";
		}
		if(cont_bde_tx.equals("--Select--")) {
			cont_bde_tx = "";
		}

		return new ModelAndView(new PDF_Held_Str_Civ(Heading,TH,foot,username,pdfprint,cont_comd_tx,cont_corps_tx,cont_div_tx,cont_bde_tx,unit_name,sus_no),"userList",pdfprint);
	}


	@RequestMapping(value = "/excelHeldStrCiv", method = RequestMethod.POST)
	public ModelAndView excelHeldStrCiv(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,
			@ModelAttribute("cont_comd_ex") String cont_comd,@ModelAttribute("cont_corps_ex") String cont_corps,@ModelAttribute("cont_div_ex") String cont_div,
			@ModelAttribute("cont_bde_ex") String cont_bde,@ModelAttribute("blood_group_txt") String blood_group_txt,
			@ModelAttribute("cont_comd_txt") String cont_comd_txt,@ModelAttribute("cont_corps_txt") String cont_corps_txt,@ModelAttribute("cont_div_txt") String cont_div_txt,
			@ModelAttribute("cont_bde_txt") String cont_bde_txt,
			@ModelAttribute("unit_name_ex") String unit_name,@ModelAttribute("sus_no_ex") String sus_no,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Mon_str_jcos_or_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		ArrayList<ArrayList<String>> Excel = held.pdf_Download_held_str_civ(cont_comd,cont_corps,cont_div,cont_bde,sus_no);

		List<String> TH = new ArrayList<String>();
		TH.add("Ser No");
		TH.add("Employee No");
		TH.add("Employee Name");
		TH.add("Designation");
		TH.add("DOB");
		TH.add("Gazeeted/Non Gazzeted");
		TH.add("Group");
		TH.add("Non Industrial/Industrial");
		TH.add("Joining in Govt Service Date");
		TH.add("Tos Date");
		TH.add("Designation Date");
		TH.add("Gender");
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Excel_To_Export_Download_Search_Report_jco_Table_Report("L", TH, username,Excel), "userList", Excel );
	}


	@RequestMapping(value = "/getCommandName", method = RequestMethod.POST)
	public @ResponseBody String getCommandName(String sus_no) {
		String comdName =held.getCommandName(sus_no);
		return comdName;
	}



	//--------------------------- appx a and b start---------------------------------

	@RequestMapping(value = "/admin/getAppxAandBList", method = RequestMethod.POST)
	public ModelAndView getAppxAandBList(ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "unit_sus_no1", required = false) String sus_no,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year) {
		String roleType = session.getAttribute("roleType").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		if(!sus_no.equals("") && sus_no != null) {
			if (all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).size() > 0) {
				Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
				String comdName= held.getCommandName(sus_no);
				Mmap.put("cmdname", comdName);
				Mmap.put("susno", sus_no);
			}
		}

		ArrayList<ArrayList<String>> authciv = held.getCiviliadnAuth(sus_no);
		Mmap.put("authciv", authciv);

		Map<String, String> regEstAppxA = held.getRegEstAppxAData(sus_no,year,month);
		Mmap.put("data", regEstAppxA);

		Map<String, String> nonRegularCiv = held.getNonRegularCivList(sus_no,year,month);
		Mmap.put("nonRegularCiv", nonRegularCiv);

		Map<String, String> summaryreport = held.getCivSummaryList(sus_no,year,month);
		Mmap.put("summaryreport", summaryreport);

		Mmap.put("msg", msg);
		Mmap.put("roleType", roleType);
		Mmap.put("month1", month);
		Mmap.put("year1", year);
		Mmap.put("role", roleAccess);
		return new ModelAndView("appxAandB_civilian_Tiles");

	}


	// ------initial page load civ for approve----------------//

	@RequestMapping(value = "/admin/Search_AppxAandB_Civ_Url", method = RequestMethod.GET)
	public ModelAndView Search_AppxAandB_Civ_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_AppxAandB_Civ_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		if (all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		Mmap.put("roleType", roleType);
		return new ModelAndView("search_civ_appxAandB_Tiles");
	}

	// -------End page load-----------//


	@RequestMapping(value = "/admin/Insert_appxAandB_Action", method = RequestMethod.POST)
	public @ResponseBody String insertAppxAandB(ModelMap model, HttpSession session,
			@RequestParam(value = "susno", required = false) String susno,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "observation", required = false) String observation) throws SQLException {
		String msg = "";
		String username = (String) session.getAttribute("username");
		String roleAccess = session.getAttribute("roleAccess").toString();

		if (susno == null || susno.trim().isEmpty()) {
			return "Please Enter sus no";
		}

		if (month == null || month.trim().isEmpty() || "0".equals(month) || "00".equals(month)) {
			return "Please select a month";
		}

		if (year == null || year.trim().isEmpty()){
			return "Please Enter Year";
		}
		if (!year.isEmpty()) {
			if (!valid.YearLength(year)) {
				msg = valid.YearMSG;
				return msg;
			}
		}

		//String formattedMonth = String.format("%02d", Integer.parseInt(month));

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = null;
		try {
			tx = sessionhql.beginTransaction();
			if(roleAccess.equals("Unit")) {
				List<TB_PSG_APPX_A_B_CIV_MAIN> version = held.Get_appx_a_bciv_VersionData(month,year,susno);
				if (version.size() != 0) {
					int Status = version.get(0).getStatus();
					if (Status == 0) {
						msg = "APPX 'A' & APPX 'B' TO IAFF-3009 : CIVILIANS are still in Pending for Approval.Pl Notify the Approval to Approve all the Pending Record of APPX 'A' & APPX 'B'.";
						return msg;
					}
				}
			}


			boolean insert = held.insertAppxAandBCiv(username, susno, month, year, observation);
			tx.commit();
			msg = insert ? "True" : "Data Not Updated Or Saved";

		} catch (RuntimeException e) {
			if(tx != null){
				tx.rollback();
			}
			msg = "Data Not Updated Or Saved";
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}



	// -----To get data submitted in the  appxA and B
	@RequestMapping(value = "/admin/GetSearch_appxAndB_Civ", method = RequestMethod.POST)
	public ModelAndView GetSearch_appxAndB_Civ(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no)
					throws ServletException, IOException, ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("appxAandB_Query", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		if (year != "") {
			if (valid.isOnlyNumer(year) == true) {
				Mmap.put("msg", " Year " + valid.isOnlyNumerMSG);
				return new ModelAndView("redirect:Search_AppxAandB_Civ_Url");
			}
			if (!valid.YearLength(year)) {
				Mmap.put("msg", valid.YearMSG);
				return new ModelAndView("redirect:Search_AppxAandB_Civ_Url");
			}
		}
		if (unit_sus_no != "") {
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				return new ModelAndView("redirect:Search_AppxAandB_Civ_Url");
			}

			if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
				return new ModelAndView("redirect:Search_AppxAandB_Civ_Url");
			}
		}

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> list = held.Search_civ_appxAandB_Version(month, year, session, status, unit_sus_no);
		Mmap.put("list", list);
		Mmap.put("size", list.size());

		if (all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}

		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("month1", month);
		Mmap.put("year1", year);
		Mmap.put("status1", status);
		Mmap.put("unit_sus_no1", unit_sus_no);
		return new ModelAndView("search_civ_appxAandB_Tiles");
	}
	// -----To get data submitted in the  appxA and B


	@RequestMapping(value = "/GetDataAppxAandBforApprove", method = RequestMethod.POST)
	public ModelAndView GetDataAppxAandBforApprove(@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "year5", required = false) String year,
			@RequestParam(value = "month5", required = false) String month,
			@RequestParam(value = "version5", required = false) String version,
			@RequestParam(value = "unit_sus_no5", required = false) String unit_sus_no,
			@RequestParam(value = "status5", required = false) String status,
			@RequestParam(value = "pagename5", required = false) String pagename,
			Authentication authentication,
			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("appxAandB_Query", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();



		if (roleType.equals("APP") || roleType.equals("ALL") || roleType.equals("DEO") || roleType.equals("VIEW")) {

			ArrayList<ArrayList<String>> authciv = held.Search_appxAandB_auth_civ(month, year, version,unit_sus_no,status);
			Mmap.put("authciv", authciv);

			Map<String, String> regEstAppxA = held.Search_appxAandB_sectionB_civ(month, year, version, unit_sus_no,status);
			Mmap.put("data", regEstAppxA);

			Map<String, String> nonRegularCiv = held.Search_appxAandB_sectionC_civ(month, year, version, unit_sus_no,status);
			Mmap.put("nonRegularCiv", nonRegularCiv);

			Map<String, String> summaryreport = held.Search_appxAandB_summary_civ(month, year, version,unit_sus_no,status);
			Mmap.put("summaryreport", summaryreport);

		}

		String observation = held.Search_appxAandB_observation_civ(month, year, version, unit_sus_no,status);


		if (all.getActiveUnitNameFromSusNo_Without_Enc(unit_sus_no, session).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(unit_sus_no, session).get(0));
			String comdName= held.getCommandName(unit_sus_no);
			Mmap.put("cmdname", comdName);
		}

		Mmap.put("observation", observation);
		Mmap.put("roleSusNo", unit_sus_no);
		Mmap.put("msg", msg);
		Mmap.put("roleType", roleType);
		Mmap.put("month1", month);
		Mmap.put("year1", year);
		Mmap.put("role", roleAccess);
		Mmap.put("version", version);
		Mmap.put("selected_status", status);
		Mmap.put("pagename",pagename);


		return new ModelAndView("Approve_appxAandB_civilian_Tiles");

	}
	// ----------------------end page load civ for approve--------------------//


	// -----------------delete submitted appx a and b data---------------------//
	@RequestMapping(value = "/delete_Report_appxAandB_civ", method = RequestMethod.POST)
	public @ResponseBody ModelAndView delete_Report_appxAandB_civ(HttpServletRequest request, HttpSession session, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "month6", required = false) String month,
			@RequestParam(value = "year6", required = false) String year,
			@RequestParam(value = "version6", required = false) String version,
			@RequestParam(value = "unit_sus_no6", required = false) String unit_sus_no, HttpSession sessionA,
			Authentication authentication) throws SQLException {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Report_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		String userId = session.getAttribute("userId").toString();

		List<String> liststr = new ArrayList<String>();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();


		Boolean Delete = held.Delete_Report_appxAandB_civ(username, unit_sus_no, month, year, version);

		tx.commit();
		sessionHQL.close();

		if (Delete == true) {
			liststr.add("Deleted Successfully.");
		} else {
			liststr.add("Deleted Not Successfully.");
		}
		model.put("msg", liststr.get(0));

		return new ModelAndView("redirect:Search_AppxAandB_Civ_Url");
	}
	// -----------------end---------------------------------------------//


	@RequestMapping(value = "/admin/approve_appxAandB_Action", method = RequestMethod.POST)
	public @ResponseBody String approve_appxAandB_Action(
			ModelMap model,
			HttpSession session,
			@RequestParam(value = "susno", required = false) String susno,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "version", required = false) String version) throws SQLException {

		String msg = "";
		String username = (String) session.getAttribute("username");

		//String formattedMonth = String.format("%02d", Integer.parseInt(month));

		Session sessionhql = null;
		Transaction tx = null;

		try {
			sessionhql = HibernateUtil.getSessionFactory().openSession();
			tx = sessionhql.beginTransaction();

			boolean approve = held.approve_Report_appxAandB_civ(username, susno, month, year, version);
			tx.commit();
			msg = approve ? "True" : "Data Not Approved";

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			msg = "Data Not Approved";
			e.printStackTrace();

		} finally {
			if (sessionhql != null && sessionhql.isOpen()) {
				sessionhql.close();
			}
		}

		return msg;
	}





	@RequestMapping(value = "/admin/appxAandB_Query", method = RequestMethod.GET)
	public ModelAndView appxAandB_Query(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("appxAandB_Query", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getCommandList", all.getCommandDetailsList());

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();

		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));

				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", formation_code);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", formation_code);

				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", formation_code);
				Mmap.put("getCorpsList", corps);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectcorps", select);
				Mmap.put("selectDiv", select);
				Mmap.put("selectBde", select);
			}
			if (roleSubAccess.equals("Corps")) {

				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", command);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
				+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);
				Mmap.put("corp_sus", cor);

				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", cor);
				Mmap.put("getDivList", Bn);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectDiv", select);
				Mmap.put("selectBde", select);
			}
			if (roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", command);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
				+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);
				Mmap.put("corp_sus", cor);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
				+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
				+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
				Mmap.put("getDivList", Bn);
				Mmap.put("div_sus", div);

				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", div);
				Mmap.put("getBdeList", bde);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectBde", select);
			}
			if (roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", command);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
				+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);
				Mmap.put("corp_sus", cor);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
				+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
				+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
				Mmap.put("getDivList", Bn);
				Mmap.put("div_sus", div);

				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", bde_code);
				Mmap.put("getBdeList", bde);
				Mmap.put("bde_sus", bde_code);
			}
		}

		if (roleAccess.equals("Unit")) {
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));

			List<String> formation = mcommon.getformationfromSus_NOList(roleSusNo);
			roleFormationNo = formation.get(0);

			String command = String.valueOf(roleFormationNo.charAt(0));
			List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
			Mmap.put("getCommandList", comd);
			Mmap.put("cmd_sus", command);

			String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
			+ String.valueOf(roleFormationNo.charAt(2));
			List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
			Mmap.put("getCorpsList", corps);
			Mmap.put("corp_sus", cor);

			String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
			+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
			+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
			List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
			Mmap.put("getDivList", Bn);
			Mmap.put("div_sus", div);

			String bde_code = roleFormationNo;
			List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", bde_code);
			Mmap.put("getBdeList", bde);
			Mmap.put("bde_sus", bde_code);

		}
		if (roleAccess.equals("Line_dte")) {
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			Mmap.put("line_dte_sus_no", roleSusNo);
		}
		if (roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd = all.getCommandDetailsList();
			Mmap.put("getCommandList", comd);
			String selectComd = "<option value='0'>--Select--</option>";
			String select = "<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps", select);
			Mmap.put("selectDiv", select);
			Mmap.put("selectBde", select);
		}
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getParentArmList", mcommon.getParentArmList());
		Mmap.put("getLineDteList", iut.getLineDteList());
		return new ModelAndView("appxAandB_Query_Tiles");
	}




	@RequestMapping(value = "/admin/GetappxAandB_CivReports", method = RequestMethod.POST)
	public ModelAndView GetappxAandB_CivReports(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year,
			@RequestParam(value = "hd_cmd_sus1", required = false) String hd_cmd_sus,
			@RequestParam(value = "hd_corp_sus1", required = false) String hd_corp_sus,
			@RequestParam(value = "hd_div_sus1", required = false) String hd_div_sus,
			@RequestParam(value = "hd_bde_sus1", required = false) String hd_bde_sus,
			@RequestParam(value = "unit_sus_no2", required = false) String unit_sus_no,
			@RequestParam(value = "line_dte1", required = false) String line_dte1,
			@RequestParam(value = "rank1", required = false) String rank)
					throws ServletException, IOException, ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("appxAandB_Query", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();

		String for_code = "";
		String cmd_sus = "";
		String corp_sus = "";
		String div_sus = "";
		String bde_sus = "";
		String rank_des = "";
		if (!hd_cmd_sus.equals("0") && !hd_cmd_sus.equals("")) {
			for_code = hd_cmd_sus;
			List<String> comd = getFormationListSus_no("Command", for_code);
			if (comd.size() > 0) {
				cmd_sus = comd.get(0);
			}
		}
		if (!hd_corp_sus.equals("0") && !hd_corp_sus.equals("")) {
			for_code = hd_corp_sus;
			List<String> cor = getFormationListSus_no("Corps", for_code);
			if (cor.size() > 0) {
				corp_sus = cor.get(0);
			}
		}
		if (!hd_div_sus.equals("0") && !hd_div_sus.equals("")) {
			for_code = hd_div_sus;
			List<String> div = getFormationListSus_no("Division", for_code);
			if (div.size() > 0) {
				div_sus = div.get(0);
			}
		}
		if (!hd_bde_sus.equals("0") && !hd_bde_sus.equals("")) {
			for_code = hd_bde_sus;
			List<String> bde = getFormationListSus_no("Brigade", for_code);
			if (bde.size() > 0) {
				bde_sus = bde.get(0);
			}
		}

		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

		ArrayList<ArrayList<String>> list_serving = held.getappxAandBList(month, year, hd_cmd_sus, hd_corp_sus,
				hd_div_sus, hd_bde_sus, unit_sus_no,line_dte1);
		Mmap.put("list", list_serving);

		Mmap.put("month1", month);
		Mmap.put("year1", year);
		Mmap.put("getCommandList", all.getCommandDetailsList());



		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));

				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", formation_code);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", formation_code);
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", formation_code);
				Mmap.put("getCorpsList", corps);
				// Mmap.put("getCommandList",comd);
				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectcorps", select);
				Mmap.put("selectDiv", select);
				Mmap.put("selectBde", select);
			}
			if (roleSubAccess.equals("Corps")) {

				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", command);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
				+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);
				Mmap.put("corp_sus", cor);

				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", cor);
				Mmap.put("getDivList", Bn);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectDiv", select);
				Mmap.put("selectBde", select);
			}
			if (roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", command);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
				+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);
				Mmap.put("corp_sus", cor);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
				+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
				+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
				Mmap.put("getDivList", Bn);
				Mmap.put("div_sus", div);

				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", div);
				Mmap.put("getBdeList", bde);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectBde", select);
			}
			if (roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", command);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
				+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);
				Mmap.put("corp_sus", cor);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
				+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
				+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
				Mmap.put("getDivList", Bn);
				Mmap.put("div_sus", div);

				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", bde_code);
				Mmap.put("getBdeList", bde);
				Mmap.put("bde_sus", bde_code);
			}
		}

		if (roleAccess.equals("Unit")) {
			roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionUserId).get(0));

			List<String> formation = mcommon.getformationfromSus_NOList(roleSusNo);
			roleFormationNo = formation.get(0);

			String command = String.valueOf(roleFormationNo.charAt(0));
			List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
			Mmap.put("getCommandList", comd);
			Mmap.put("cmd_sus", command);
			String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
			+ String.valueOf(roleFormationNo.charAt(2));
			List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
			Mmap.put("getCorpsList", corps);
			Mmap.put("corp_sus", cor);
			String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
			+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
			+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
			List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
			Mmap.put("getDivList", Bn);
			Mmap.put("div_sus", div);
			String bde_code = roleFormationNo;
			List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", bde_code);
			Mmap.put("getBdeList", bde);
			Mmap.put("bde_sus", bde_code);

		}

		if (roleAccess.equals("Line_dte")) {
			roleSusNo = session.getAttribute("roleSusNo").toString();
			Mmap.put("line_dte_sus_no", roleSusNo);
		}
		if (!line_dte1.equals("") && !line_dte1.equals("0")) {
			Mmap.put("line_dte_sus_no", line_dte1);
		}
		if (roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter") || roleAccess.equals("DGMS")) {
			List<Tbl_CodesForm> comd = all.getCommandDetailsList();
			Mmap.put("getCommandList", comd);
			String selectComd = "<option value='0'>--Select--</option>";
			String select = "<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps", select);
			Mmap.put("selectDiv", select);
			Mmap.put("selectBde", select);
		}
		Mmap.put("sus_no", unit_sus_no);
		Mmap.put("cmd_sus", hd_cmd_sus);
		Mmap.put("corp_sus", hd_corp_sus);
		Mmap.put("div_sus", hd_div_sus);
		Mmap.put("bde_sus", hd_bde_sus);
		Mmap.put("getLineDteList", iut.getLineDteList());
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		return new ModelAndView("appxAandB_Query_Tiles");
	}

	public List<String> getFormationListSus_no(String level_in_hierarchy,String fcode) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = null;

		if(level_in_hierarchy.equals("Command")) {
			q = sessionHQL.createQuery("select sus_no from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Command') and SUBSTR(form_code_control,1,1) =:formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode);

		}
		if(level_in_hierarchy.equals("Corps")) {
			q = sessionHQL.createQuery("select sus_no from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Corps') and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");

		}
		if(level_in_hierarchy.equals("Division")) {
			q = sessionHQL.createQuery("select sus_no from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Division' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");

		}
		if(level_in_hierarchy.equals("Brigade")) {
			q = sessionHQL.createQuery("select sus_no from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Brigade' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");

		}

		@SuppressWarnings("unchecked")
		List<String> list = q.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	// ---------------------------End appx a and b ---------------------------------

	@RequestMapping(value = "/Download_AppxABReport",method = RequestMethod.POST)
	public ModelAndView Download_AppxABReport(ModelMap Mmap,Authentication authentication,HttpServletRequest request,HttpSession sessionEdit,
			@ModelAttribute("cont_comd_tx") String cont_comd_tx,
			@ModelAttribute("cont_corps_tx") String cont_corps_tx,
			@ModelAttribute("cont_div_tx") String cont_div_tx,
			@ModelAttribute("cont_bde_tx") String cont_bde_tx,
			@RequestParam(value = "month2", required = false) String month,
			@RequestParam(value = "year2", required = false) String year,
			@RequestParam(value = "hd_cmd_sus2", required = false) String hd_cmd_sus,
			@RequestParam(value = "hd_corp_sus2", required = false) String hd_corp_sus,
			@RequestParam(value = "hd_div_sus2", required = false) String hd_div_sus,
			@RequestParam(value = "hd_bde_sus2", required = false) String hd_bde_sus,
			@RequestParam(value = "unit_sus_no3", required = false) String unit_sus_no,
			@RequestParam(value = "rank2", required = false) String rank,
			@RequestParam(value = "version1", required = false) String version,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String for_code = "";
		String cmd_sus = "";
		String corp_sus = "";
		String div_sus = "";
		String bde_sus = "";
		String rank_des = "";

		if(!hd_cmd_sus.equals(null) && !hd_cmd_sus.equals("0") && !hd_cmd_sus.equals("") && !hd_cmd_sus.equals("null")){
			for_code = hd_cmd_sus ;
			List<String> comd= getFormationListSus_no("Command",for_code);
			if(comd.size() > 0) {
				cmd_sus = comd.get(0);
			}
		}
		if(!hd_corp_sus.equals(null) && !hd_corp_sus.equals("0") && !hd_corp_sus.equals("") && !hd_corp_sus.equals("null")){
			for_code = hd_corp_sus ;
			List<String> cor= getFormationListSus_no("Corps",for_code);
			if(cor.size() > 0) {
				corp_sus = cor.get(0);
			}
		}
		if(!hd_div_sus.equals(null) && !hd_div_sus.equals("0") && !hd_div_sus.equals("") && !hd_div_sus.equals("null")){
			for_code = hd_div_sus ;
			List<String> div= getFormationListSus_no("Division",for_code);
			if(div.size() > 0) {
				div_sus = div.get(0);
			}
		}
		if(!hd_bde_sus.equals(null) && !hd_bde_sus.equals("0") && !hd_bde_sus.equals("") && !hd_bde_sus.equals("null")){
			for_code = hd_bde_sus ;
			List<String> bde= getFormationListSus_no("Brigade",for_code);
			if(bde.size() > 0) {
				bde_sus = bde.get(0);
			}
		}
		if(!rank.equals(null) && !rank.equals("0") && !rank.equals("")  && !rank.equals("null")){
			List<String> rank_val  = getRankDescription(Integer.parseInt(rank));
			rank_des = rank_val.get(0);

		}

		// Data extraction logic, you must modify your asd.pdf_AuditReport method to return this four tables
		HashMap<String,ArrayList<ArrayList<String>>> pdfprint = held.pdf_AppxABReport(month,year,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,rank_des,version);
		//		ArrayList<ArrayList<String>> list_serving = IDAO.iaff3008_Report_Serving(month,year,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,rank_des);

		String username =  sessionEdit.getAttribute("roleloginName").toString();

		Mmap.put("msg", msg);
		String Heading ="CIVILIAN REPORT";
		Date date = new Date();
		String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);


		// SECTION A Headings
		List<String> TH_A = new ArrayList<String>();
		TH_A.add("GP 'A'");
		TH_A.add("GP 'B'");
		TH_A.add("NON INDUSTRIAL");
		TH_A.add("GP 'B'");
		TH_A.add("GP 'C'");
		TH_A.add("TOTAL");


		// SECTION B Headings
		List<String> TH_B = new ArrayList<String>();
		TH_B.add("SER NO");
		TH_B.add("CAT");
		TH_B.add("GROUP");
		TH_B.add("AUTH/HELD");
		TH_B.add("GENDER");
		TH_B.add("INDUSTRIAL");
		TH_B.add("NON INDUSTRIAL");


		// SECTION C Headings
		List<String> TH_C = new ArrayList<String>();
		TH_C.add("SER NO");
		TH_C.add("CAT");
		TH_C.add("GROUP");
		TH_C.add("AUTH/HELD");
		TH_C.add("GENDER");
		TH_C.add("INDUSTRIAL");
		TH_C.add("NON INDUSTRIAL");


		// SECTION D Headings
		List<String> TH_D = new ArrayList<String>();
		TH_D.add("Auth Strength");
		TH_D.add("Holding Strength");
		TH_D.add("Surplus");
		TH_D.add("Deficiency");

		// Retrieve data from the HashMap
		List<ArrayList<String>> dataA = pdfprint.get("SECTION_A");
		List<ArrayList<String>> dataB = pdfprint.get("SECTION_B");
		List<ArrayList<String>> dataC = pdfprint.get("SECTION_C");
		List<ArrayList<String>> dataD = pdfprint.get("SECTION_D");
		List<ArrayList<String>> dataS = pdfprint.get("SECTION_S");


		/*return new ModelAndView(new AppxAB_PDF_Report(TH_A,TH_B,TH_C,TH_D,username,Heading,dataA,dataB,dataC,dataD,dataS),"userList",pdfprint);*/
		return new ModelAndView(new AppxAB_PDF_Report(TH_A,TH_B,TH_C,TH_D,username,Heading,dataA,dataB,dataC,dataD,dataS,unit_sus_no,month,year),"userList",pdfprint);
	}


	@RequestMapping(value = "/Download_SearchAppxABReport",method = RequestMethod.POST)
	public ModelAndView Download_SearchAppxABReport(ModelMap Mmap,Authentication authentication,HttpServletRequest request,HttpSession sessionEdit,
			@ModelAttribute("id") String id,
			@RequestParam(value = "month2", required = false) String month,
			@RequestParam(value = "year2", required = false) String year,
			@RequestParam(value = "unit_sus_no3", required = false) String unit_sus_no,
			@RequestParam(value = "version1", required = false) String version,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String for_code = "";
		String cmd_sus = "";
		String corp_sus = "";
		String div_sus = "";
		String bde_sus = "";
		String rank_des = "";

		HashMap<String,ArrayList<ArrayList<String>>> pdfprint = held.pdf_AppxABReport(month,year,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,rank_des,version);
		String username =  sessionEdit.getAttribute("roleloginName").toString();

		Mmap.put("msg", msg);
		String Heading ="CIVILIAN REPORT";
		Date date = new Date();
		String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);


		// SECTION A Headings
		List<String> TH_A = new ArrayList<String>();
		TH_A.add("GP 'A'");
		TH_A.add("GP 'B'");
		TH_A.add("NON INDUSTRIAL");
		TH_A.add("GP 'B'");
		TH_A.add("GP 'C'");
		TH_A.add("TOTAL");


		// SECTION B Headings
		List<String> TH_B = new ArrayList<String>();
		TH_B.add("SER NO");
		TH_B.add("CAT");
		TH_B.add("GROUP");
		TH_B.add("AUTH/HELD");
		TH_B.add("GENDER");
		TH_B.add("INDUSTRIAL");
		TH_B.add("NON INDUSTRIAL");


		// SECTION C Headings
		List<String> TH_C = new ArrayList<String>();
		TH_C.add("SER NO");
		TH_C.add("CAT");
		TH_C.add("GROUP");
		TH_C.add("AUTH/HELD");
		TH_C.add("GENDER");
		TH_C.add("INDUSTRIAL");
		TH_C.add("NON INDUSTRIAL");


		// SECTION D Headings
		List<String> TH_D = new ArrayList<String>();
		TH_D.add("Auth Strength");
		TH_D.add("Holding Strength");
		TH_D.add("Surplus");
		TH_D.add("Deficiency");

		// Retrieve data from the HashMap
		List<ArrayList<String>> dataA = pdfprint.get("SECTION_A");
		List<ArrayList<String>> dataB = pdfprint.get("SECTION_B");
		List<ArrayList<String>> dataC = pdfprint.get("SECTION_C");
		List<ArrayList<String>> dataD = pdfprint.get("SECTION_D");
		List<ArrayList<String>> dataS = pdfprint.get("SECTION_S");


		/*return new ModelAndView(new AppxAB_PDF_Report(TH_A,TH_B,TH_C,TH_D,username,Heading,dataA,dataB,dataC,dataD,dataS),"userList",pdfprint);*/
		return new ModelAndView(new AppxAB_PDF_Report(TH_A,TH_B,TH_C,TH_D,username,Heading,dataA,dataB,dataC,dataD,dataS,unit_sus_no,month,year),"userList",pdfprint);
	}


	public @ResponseBody List<String> getRankDescription(int id) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = session.createQuery("select distinct description from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = 'RANK' and parent_code ='0'\r\n" +
				"and code not in ('R009','R099','R400','R110','R203','R207','R307','R1001','R1002','TQ284','210','R200','R115','R205','R116','R117',\r\n" +
				"				 'R201','R208','R202','R128','R129','R306','R204','R114') and status_active = 'Active' and id =:id ");

		q.setParameter("id", id);


		@SuppressWarnings("unchecked")

		List<String> list = q.list();

		tx.commit();

		session.close();

		return list;

	}
}
