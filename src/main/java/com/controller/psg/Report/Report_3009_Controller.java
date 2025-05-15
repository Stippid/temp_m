package com.controller.psg.Report;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.HelpDeskController.helpController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Report.ReportSearch_3009DAO;
import com.dao.psg.Report.Report_Serving_DAO;
import com.dao.psg.miso_olap.JDBCMISO_OLAPDAO;
import com.dao.psg.miso_olap.JDBCMISO_OLAPDAOImpl;
import com.models.psg.Report.TB_PSG_IAFF_3009_MAIN;
import com.models.psg.Report.TB_PSG_IAFF_3009_MAIN_DETAILS;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Report_3009_Controller {

	@Autowired
	private ReportSearch_3009DAO search_3009DAO;

	ValidationController valid = new ValidationController();
	Psg_CommonController mcommon = new Psg_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	helpController hp = new helpController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	@Autowired
	private Report_Serving_DAO RP;

	@Autowired
	private RoleBaseMenuDAO roledao;
	JDBCMISO_OLAPDAO o = new JDBCMISO_OLAPDAOImpl();

	@RequestMapping(value = "/admin/Search_Report_3009Url", method = RequestMethod.GET)
	public ModelAndView Search_Report_3009Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {

		Mmap.put("getCommandList", m.getCommandDetailsList());

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		
		/*LocalDate currentDate = LocalDate.now();
		int daysInMonth = currentDate.lengthOfMonth();
		int dayOfMonth = currentDate.getDayOfMonth();
		int month = (dayOfMonth == daysInMonth) ? currentDate.getMonthValue() : currentDate.getMonthValue() - 1;
		int year = currentDate.getYear();*/
		
		LocalDate currentDate = LocalDate.now();
		LocalDate previousMonth = currentDate.minusMonths(1);
		int month = previousMonth.getMonthValue();
		int year = previousMonth.getYear();
		
		

		
		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		if (all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getParentArmList", mcommon.getParentArmList());
		Mmap.put("month1", month);
		Mmap.put("year1", year);

		return new ModelAndView("report_3009_Tiles");
	}

	@RequestMapping(value = "/getauthdetails3009", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getauthdetails3009(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no) throws SQLException {

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
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return search_3009DAO.getauthdetails3009(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				sus_no);
	}

	@RequestMapping(value = "/getauthdetails3009Count", method = RequestMethod.POST)
	public @ResponseBody long getauthdetails3009Count(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no) throws SQLException {

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
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return search_3009DAO.getauthdetails3009CountList(Search, orderColunm, orderType, sessionUserId, sus_no);
	}

	/////////////////////////////////////////
	@RequestMapping(value = "/getauthcivTable3009", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getauthcivTable3009(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no) throws SQLException {

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
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return search_3009DAO.getauthciv3009(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				sus_no);
	}

	@RequestMapping(value = "/getauthcivTableCount", method = RequestMethod.POST)
	public @ResponseBody long getauthcivTableCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no) throws SQLException {

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}

		return search_3009DAO.getauthciv3009CountList(Search, orderColunm, orderType, sessionUserId, sus_no);
	}

	@RequestMapping(value = "/getpostcivTable3009", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getpostcivTable3009(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no) throws SQLException {

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
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return search_3009DAO.getpostciv3009(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				sus_no, FDate, LDate);
	}

	@RequestMapping(value = "/getpostcivTableCount", method = RequestMethod.POST)
	public @ResponseBody long getpostcivTableCount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no) throws SQLException {
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
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return search_3009DAO.getpostciv3009CountList(Search, orderColunm, orderType, sessionUserId, sus_no, FDate,
				LDate);
	}

	////////////////////////////////

	@RequestMapping(value = "/getpostedServing3009", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getpostedServing3009(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no) throws SQLException {

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
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return search_3009DAO.getpostedServing3009(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				sus_no, FDate, LDate);
	}

	@RequestMapping(value = "/getpostedServing3009Count", method = RequestMethod.POST)
	public @ResponseBody long getpostedServing3009Count(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no) throws SQLException {

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
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return search_3009DAO.getpostedServing3009CountList(Search, orderColunm, orderType, sessionUserId, sus_no,
				FDate, LDate);
	}

	@RequestMapping(value = "/gettradeServing3009", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> gettradeServing3009(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no) throws SQLException {

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
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return search_3009DAO.gettradeServing3009(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				sus_no, FDate, LDate);
	}

	@RequestMapping(value = "/gettradeServing3009Count", method = RequestMethod.POST)
	public @ResponseBody long gettradeServing3009Count(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no) throws SQLException {

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
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return search_3009DAO.gettradeServing3009Count(Search, orderColunm, orderType, sessionUserId, sus_no, FDate,
				LDate);
	}

	@RequestMapping(value = "/getreligious3009", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getreligious3009(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no) throws SQLException {

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
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return search_3009DAO.getreligiousServing3009(startPage, pageLength, Search, orderColunm, orderType,
				sessionUserId, sus_no, FDate, LDate);
	}

	@RequestMapping(value = "/getreligious3009Count", method = RequestMethod.POST)
	public @ResponseBody long getreligious3009Count(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no) throws SQLException {
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
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return search_3009DAO.getreligiousServing3009Count(Search, orderColunm, orderType, sessionUserId, sus_no, FDate,
				LDate);
	}

	@RequestMapping(value = "/admin/Insert_3009", method = RequestMethod.POST)
	public @ResponseBody String Insert_3009(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws SQLException {
		String msg = "";
		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String userId = session.getAttribute("userId").toString();

		// changes utk
		/// bisag v2 260822 (date parse added)
		String p_dt = request.getParameter("present_return_dt");
		String held_jco_hid = request.getParameter("held_jco_hid");

		String officerauth = request.getParameter("officerauth");
		String officerauthno = request.getParameter("officerauthno");
		
		String officermnsauth = request.getParameter("officermnsauth");
		String officermnsauthno = request.getParameter("officermnsauthno");

		String jcoauth = request.getParameter("jcoauth");
		String jcoauthno = request.getParameter("jcoauthno");

		String civauth = request.getParameter("civauth");
		String civauthno = request.getParameter("civauthno");

		String orauth = request.getParameter("orauth");
		String orauthno = request.getParameter("orauthno");
		
		String remarks = request.getParameter("remarks");
		String distlist = request.getParameter("distlist");
		

		String[] act = p_dt.split("/");

		String p_dt_act = act[2] + "/" + act[1] + "/" + act[0];

		Date date = new Date(p_dt_act);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String present_rt_date = formatter.format(date);

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

		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);

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

		List<TB_PSG_IAFF_3009_MAIN> version = search_3009DAO.Get_3009_VersionData(request.getParameter("month"),
				request.getParameter("year"), roleSusNo);

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
		if(!officerauth.equals("")) {
		if (officerauth.equals("")
				|| officerauth.equals(null)
				|| officerauth.equals("null")) {

			msg = "Please Enter Offr's Auth no .";
			return msg;
		}
		}
		
		if(!officermnsauth.equals("")) {
			if (officermnsauthno.equals("")
					|| officermnsauthno.equals(null)
					|| officermnsauthno.equals("null")) {

				msg = "Please Enter Offr's MNS Auth no .";
				return msg;
			}
			}
		
		if(!jcoauth.equals("")) {
		if (jcoauthno.equals("")
				|| jcoauthno.equals(null)
				|| jcoauthno.equals("null")) {

			msg = "Please Enter JCO'S Auth no .";
			return msg;
		}
		}
		if(!orauth.equals("")) {
		if (orauthno.equals("")
				|| orauthno.equals(null)
				|| orauthno.equals("null")) {

			msg = "Please Enter OR Auth no.";
			return msg;
		}}
		
		if(!civauth.equals("")) {
		if (civauthno.equals("")
				|| civauthno.equals(null)
				|| civauthno.equals("null")) {

			msg = "Please Enter CIV Auth no.";
			return msg;
		}
		}

		if (!request.getParameter("remarks").equals("") || !request.getParameter("remarks").equals(null)
				|| !request.getParameter("remarks").equals("null")) {
			if (!valid.isvalidLength(request.getParameter("remarks"), valid.remarkMax, valid.remarkMin)) {
				msg = "Remarks " + valid.isValidLengthMSG;
				return msg;
			}
		}
		if (!request.getParameter("distlist").equals("") || !request.getParameter("distlist").equals(null)
				|| !request.getParameter("distlist").equals("null")) {
			if (!valid.isvalidLength(request.getParameter("distlist"), valid.remarkMax, valid.remarkMin)) {
				msg = " Distribution List   " + valid.isValidLengthMSG;
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

//			 Boolean Insert = search_3009DAO.Insert_Report_3009(username, sus_no, FDate,
//			 request.getParameter("month"), request.getParameter("year"), userId,
//			 request.getParameter("present_return_no"), present_rt_date,
//			 request.getParameter("observation"), LDate);

			Boolean Insert = search_3009DAO.Insert_Report_3009(username, sus_no, FDate, request.getParameter("month"),
					request.getParameter("year"), userId, request.getParameter("present_return_no"), present_rt_date,
					request.getParameter("remarks"),request.getParameter("distlist"), LDate, request.getParameter("offf_auth_count"),
					request.getParameter("offf_posted_count"), request.getParameter("offf_defi_count"),
					request.getParameter("offf_sur_count"),officerauth,officerauthno, request.getParameter("jcos_auth_count"),
					request.getParameter("jcos_posted_count"), request.getParameter("jcodefi_count"),
					request.getParameter("jco_sur_count"),jcoauth,jcoauthno, request.getParameter("or_auth_count"),
					request.getParameter("or_posted_count"), request.getParameter("ordefi_count"),
					request.getParameter("or_sur_count"),orauth,orauthno, request.getParameter("auth_civ_count"),
					request.getParameter("civ_posted_count"), request.getParameter("civdefi_count"),
					request.getParameter("civ_sur_count"),civauth,civauthno,
					request.getParameter("offf_mns_auth_count"),request.getParameter("offf_mns_posted_count"), request.getParameter("offf_mns_defi_count"),
					request.getParameter("offf_mns_sur_count"),officermnsauth,officermnsauthno,false);

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

	// ------------------//
	public List<TB_PSG_IAFF_3009_MAIN_DETAILS> Get_3009_DetailsData1(String month, String year, String roleSusNo) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_IAFF_3009_MAIN_DETAILS where month=:month and year=:year and sus_no=:roleSusNo and status=1 order by id desc ";
		Query query = sessionHQL.createQuery(hqlUpdate).setString("month", month).setString("year", year)
				.setString("roleSusNo", roleSusNo);
		@SuppressWarnings("unchecked")
		List<TB_PSG_IAFF_3009_MAIN_DETAILS> list = (List<TB_PSG_IAFF_3009_MAIN_DETAILS>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public List<TB_PSG_IAFF_3009_MAIN_DETAILS> Get_3009_DetailsData(String month, String year, String roleSusNo,
			String version,int status) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_IAFF_3009_MAIN_DETAILS where month=:month and year=:year and sus_no=:roleSusNo and version=:version and status =:status order by id desc";
		Query query = sessionHQL.createQuery(hqlUpdate).setString("month", month).setString("year", year)
				.setString("roleSusNo", roleSusNo).setString("version", version).setInteger("status", status);
		@SuppressWarnings("unchecked")
		List<TB_PSG_IAFF_3009_MAIN_DETAILS> list = (List<TB_PSG_IAFF_3009_MAIN_DETAILS>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	// ------------------//

	// ------initial page load 3009 for approve----------------//

	@RequestMapping(value = "/admin/Search_Report_3009_Url", method = RequestMethod.GET)
	public ModelAndView Search_Report_3009_Url(ModelMap Mmap, HttpSession sessionA,
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
		return new ModelAndView("Search_Report_3009_Tiles");
	}

	// -------End page load-----------//
	// ------------------------get 3009 submitted data when click on search
	// button-------------------------------------//

	@RequestMapping(value = "/admin/GetSearch_Report_3009", method = RequestMethod.POST)
	public ModelAndView GetSearch_Report_3009(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no)
			throws ServletException, IOException, ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Report_3009_Url", roleid);
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
				return new ModelAndView("redirect:Search_Report_3009_Url");
			}
			if (!valid.YearLength(year)) {
				Mmap.put("msg", valid.YearMSG);
				return new ModelAndView("redirect:Search_Report_3009_Url");
			}
		}
		if (unit_sus_no != "") {
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				return new ModelAndView("redirect:Search_Report_3009_Url");
			}

			if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
				return new ModelAndView("redirect:Search_Report_3009_Url");
			}
		}

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> list = search_3009DAO.Search_3009_Report_Version(month, year, session, status,
				unit_sus_no);
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
		
		return new ModelAndView("Search_Report_3009_Tiles");
	}
	// -----------------------End getdata-------------------------------------//

	// -----------------delete submitted 3009 for approval---------------------//
	@RequestMapping(value = "/delete_Report_3009", method = RequestMethod.POST)
	public @ResponseBody ModelAndView delete_Report_3009(HttpServletRequest request, HttpSession session,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "month6", required = false) String month,
			@RequestParam(value = "year6", required = false) String year,
			@RequestParam(value = "version6", required = false) String version,
			@RequestParam(value = "unit_sus_no6", required = false) String unit_sus_no, HttpSession sessionA,
			Authentication authentication) throws SQLException {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Report_3009_Url", roleid);
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
		Boolean Delete = search_3009DAO.Delete_Report_3009(username, sus_no, month, year, version);

		tx.commit();
		sessionHQL.close();

		if (Delete == true) {
			liststr.add("Deleted Successfully.");
		} else {
			liststr.add("Deleted Not Successfully.");
		}
		model.put("msg", liststr.get(0));

		return new ModelAndView("redirect:Search_Report_3009_Url");
	}
	// -----------------end---------------------------------------------//

	// ----------------------load page data for approve 3009 submitted
	// form-----------------------//

	@RequestMapping(value = "/Approve_Report_3009_Url", method = RequestMethod.POST)
	public ModelAndView Approve_Report_3009_Url(@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "year5", required = false) String year,
			@RequestParam(value = "month5", required = false) String month,
			@RequestParam(value = "version5", required = false) String version,
			@RequestParam(value = "unit_sus_no5", required = false) String unit_sus_no,
			@RequestParam(value = "status5", required = false) String status, 
			@RequestParam(value = "pagename", required = false) String pagename,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no1, 
			@RequestParam(value = "hd_cmd_sus5", required = false) String hd_cmd_sus5,
			@RequestParam(value = "hd_corp_sus5", required = false) String hd_corp_sus5,
			@RequestParam(value = "hd_div_sus5", required = false) String hd_div_sus5,
			@RequestParam(value = "hd_bde_sus5", required = false) String hd_bde_sus5,
			@RequestParam(value = "line_dte5", required = false) String line_dte5,
			
			Authentication authentication,
			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		//Boolean val = roledao.ScreenRedirect("Search_Report_3009_Url", roleid);
		//if (val == false) {
		//	return new ModelAndView("AccessTiles");
		//}
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

		ArrayList<ArrayList<String>> list_date = search_3009DAO.getldate(LDate);
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

		if (roleType.equals("APP") || roleType.equals("ALL") || roleType.equals("DEO") || roleType.equals("VIEW")) {

			ArrayList<ArrayList<String>> list_sup = search_3009DAO.Search_Report_auth_str_jco_or_3009(month, year,
					session, version, sus_no);
			Mmap.put("list", list_sup);
			Mmap.put("size", list_sup.size());
			// ---column total Search_Report_auth_str_jco_or_3009---//
			int offrstotal = 0;
			int mnsoffrstotal = 0;
			int jcostotal = 0;
			int ortotal = 0;

			for (ArrayList<String> item : list_sup) {
				int offrsValue = Integer.parseInt(item.get(1));
				int mnsoffrsValue = Integer.parseInt(item.get(2));
				int jcoValue = Integer.parseInt(item.get(3));
				int orValue = Integer.parseInt(item.get(4));

				offrstotal += offrsValue;
				mnsoffrstotal += mnsoffrsValue;
				jcostotal += jcoValue;
				ortotal += orValue;
			}

			Mmap.put("offrstotal", offrstotal);
			Mmap.put("mnsoffrstotal", mnsoffrstotal);
			Mmap.put("jcostotal", jcostotal);
			Mmap.put("ortotal", ortotal);
			// --------end-----------//
			ArrayList<ArrayList<String>> auth_civ_list = search_3009DAO.Search_Report_auth_civ_3009(month, year,
					session, version, sus_no);
			Mmap.put("list9", auth_civ_list);
			Mmap.put("size9", auth_civ_list.size());

			ArrayList<ArrayList<String>> list_sup1 = search_3009DAO.Search_Report_posted_offrs_jco_or_3009(month, year,
					session, version, sus_no);
			Mmap.put("list2", list_sup1);
			Mmap.put("size2", list_sup1.size());
			// ---column total Search_Report_posted_offrs_jco_or_3009---//
			int brig_and_above_offrs = 0;
			int col_offrs = 0;
			int col_ts_offrs = 0;
			int lt_col_offrs = 0;
			int maj_offrs = 0;
			int capt_lt_offrs = 0;
			int brig_and_above_mns_offrs = 0;
			int col_mns_offrs = 0;
			int lt_col_s_mns_offrs = 0;
			int lt_col_ts_mns_offrs = 0;
			int maj_mns_offrs = 0;
			int capt_lt_mns_offrs = 0;
			int sub_major_jco = 0;
			int sub_jco = 0;
			int nb_sub_jco = 0;
			int warrant_officer_jco = 0;
			int hav_or = 0;
			int nk_or = 0;
			int lnk_sep_or = 0;
			int rects_or = 0;

			for (ArrayList<String> item : list_sup1) {
				int brig_and_above_offrsval = Integer.parseInt(item.get(1));
				int col_offrsval = Integer.parseInt(item.get(2));
				int col_ts_offrsval = Integer.parseInt(item.get(3));
				int lt_col_offrsval = Integer.parseInt(item.get(4));
				int maj_offrsval = Integer.parseInt(item.get(5));
				int capt_lt_offrsval = Integer.parseInt(item.get(6));
				int brig_and_above_mns_offrsval = Integer.parseInt(item.get(7));
				int col_mns_offrsval = Integer.parseInt(item.get(8));
				int lt_col_s_mns_offrsval = Integer.parseInt(item.get(9));
				int lt_col_ts_mns_offrsval = Integer.parseInt(item.get(10));
				int maj_mns_offrsval = Integer.parseInt(item.get(11));
				int capt_lt_mns_offrsval = Integer.parseInt(item.get(12));
				int sub_major_jcoval = Integer.parseInt(item.get(13));
				int sub_jcoval = Integer.parseInt(item.get(14));
				int nb_sub_jcoval = Integer.parseInt(item.get(15));
				int warrant_officer_jcoval = Integer.parseInt(item.get(16));
				int hav_orval = Integer.parseInt(item.get(17));
				int nk_orval = Integer.parseInt(item.get(18));
				int lnk_sep_orval = Integer.parseInt(item.get(19));
				int rects_orval = Integer.parseInt(item.get(20));

				brig_and_above_offrs += brig_and_above_offrsval;
				col_offrs += col_offrsval;
				col_ts_offrs += col_ts_offrsval;
				lt_col_offrs += lt_col_offrsval;
				maj_offrs += maj_offrsval;
				capt_lt_offrs += capt_lt_offrsval;
				brig_and_above_mns_offrs += brig_and_above_mns_offrsval;
				col_mns_offrs += col_mns_offrsval;
				lt_col_s_mns_offrs += lt_col_s_mns_offrsval;
				lt_col_ts_mns_offrs += lt_col_ts_mns_offrsval;
				maj_mns_offrs += maj_mns_offrsval;
				capt_lt_mns_offrs += capt_lt_mns_offrsval;
				sub_major_jco += sub_major_jcoval;
				sub_jco += sub_jcoval;
				nb_sub_jco += nb_sub_jcoval;
				warrant_officer_jco += warrant_officer_jcoval;
				hav_or += hav_orval;
				nk_or += nk_orval;
				lnk_sep_or += lnk_sep_orval;
				rects_or += rects_orval;
			}

			Mmap.put("brig_and_above_offrs", brig_and_above_offrs);
			Mmap.put("col_offrs", col_offrs);
			Mmap.put("col_ts_offrs", col_ts_offrs);
			Mmap.put("lt_col_offrs", lt_col_offrs);
			Mmap.put("maj_offrs", maj_offrs);
			Mmap.put("capt_lt_offrs", capt_lt_offrs);
			Mmap.put("brig_and_above_mns_offrs", brig_and_above_mns_offrs);
			Mmap.put("col_mns_offrs", col_mns_offrs);
			Mmap.put("lt_col_s_mns_offrs", lt_col_s_mns_offrs);
			Mmap.put("lt_col_ts_mns_offrs", lt_col_ts_mns_offrs);
			Mmap.put("maj_mns_offrs", maj_mns_offrs);
			Mmap.put("capt_lt_mns_offrs", capt_lt_mns_offrs);
			Mmap.put("sub_major_jco", sub_major_jco);
			Mmap.put("sub_jco", sub_jco);
			Mmap.put("nb_sub_jco", nb_sub_jco);
			Mmap.put("warrant_officer_jco", warrant_officer_jco);
			Mmap.put("hav_or", hav_or);
			Mmap.put("nk_or", nk_or);
			Mmap.put("lnk_sep_or", lnk_sep_or);
			Mmap.put("rects_or", rects_or);
			// --------end-----------//
			ArrayList<ArrayList<String>> list_sup8 = search_3009DAO.Search_Report_posted_civ_300(month, year, session,
					version, sus_no);
			Mmap.put("list8", list_sup8);
			Mmap.put("size8", list_sup8.size());

			ArrayList<ArrayList<String>> list_re_employ = search_3009DAO
					.Search_Report_rank_and_trade_wise_jco_or_3009(month, year, session, version, sus_no);
			Mmap.put("list4", list_re_employ);
			Mmap.put("size3", list_re_employ.size());

			ArrayList<ArrayList<String>> list6 = search_3009DAO.Search_Report_religious_denomination_3009(month, year,
					session, version, sus_no);
			Mmap.put("list6", list6);
			Mmap.put("size6", list6.size());
			// ---column total Search_Report_religious_denomination_3009---//
			int jcos_posted_str_incl_ere_pers = 0;
			int or_posted_str_incl_ere_pers = 0;
			int held_religious_teacher = 0;
			int auth_religious_teacher = 0;

			for (ArrayList<String> item : list6) {
				int jcos_posted_str_incl_ere_persval = Integer.parseInt(item.get(2));
				int or_posted_str_incl_ere_persval = Integer.parseInt(item.get(3));
				int auth_religious_teacherval = Integer.parseInt(item.get(4));
				int held_religious_teacherval = Integer.parseInt(item.get(5));

				jcos_posted_str_incl_ere_pers += jcos_posted_str_incl_ere_persval;
				or_posted_str_incl_ere_pers += or_posted_str_incl_ere_persval;
				held_religious_teacher += held_religious_teacherval;
				auth_religious_teacher += auth_religious_teacherval;
			}

			Mmap.put("jcos_posted_str_incl_ere_pers", jcos_posted_str_incl_ere_pers);
			Mmap.put("or_posted_str_incl_ere_pers", or_posted_str_incl_ere_pers);
			Mmap.put("held_religious_teacher", held_religious_teacher);
			Mmap.put("auth_religious_teacher", auth_religious_teacher);
			// --------end-----------//
			ArrayList<ArrayList<String>> summaryList = search_3009DAO.Search_Report_summary(month, year, session,
					version, unit_sus_no);
			Mmap.put("list7", summaryList);
			Mmap.put("size7", summaryList.size());

		}

		List<TB_PSG_IAFF_3009_MAIN_DETAILS> MainDD = Get_3009_DetailsData1(month, year, sus_no);

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

		List<TB_PSG_IAFF_3009_MAIN_DETAILS> cc = Get_3009_DetailsData(month, year, sus_no, version,Integer.parseInt(status));

		Mmap.put("msg", msg);
		Mmap.put("month5", month);
		Mmap.put("year5", year);
		Mmap.put("version5", version);
		Mmap.put("unit_sus_no5", unit_sus_no);

		if (cc.size() > 0) {
			Mmap.put("present_return_no5", cc.get(0).getPresent_return_no());
			Mmap.put("present_return_dt5", cc.get(0).getPresent_return_dt());
			Mmap.put("remarks5", cc.get(0).getRemarks());
			Mmap.put("distlist5", cc.get(0).getDistributionlist());
			Mmap.put("we_pe_no1", cc.get(0).getWe_pe_no());
		}

		Mmap.put("roleSusNo", roleSusNo);
		if (all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		Mmap.put("roleType", roleType);
		Mmap.put("status", status);
		Mmap.put("pagename", pagename);
		Mmap.put("unit_sus_no1", unit_sus_no1);
		
		
		
		Mmap.put("hd_corp_sus5", hd_corp_sus5);
		Mmap.put("hd_div_sus5", hd_div_sus5);
		Mmap.put("hd_bde_sus5", hd_bde_sus5);
		Mmap.put("hd_cmd_sus5", hd_cmd_sus5);
		Mmap.put("line_dte5", line_dte5);

		return new ModelAndView("Approve_Report_3009_Tiles");

	}
	// ----------------------end page load 3009 for approve--------------------//

	// -----------approve button ---------------------------------------//
	@RequestMapping(value = "/Approve_3009", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Approve_3009(HttpServletRequest request, HttpSession session, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year,
			@RequestParam(value = "version1", required = false) String version,
			@RequestParam(value = "unit_sus_no5", required = false) String unit_sus_no,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no1,
			@RequestParam(value = "status1", required = false) String status,
			Authentication authentication)
			throws SQLException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Report_3009_Url", roleid);
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

		Boolean Approve = search_3009DAO.Approve_Report_3009(username, sus_no, month, year, version);
		
		ArrayList<ArrayList<String>> list = search_3009DAO.Search_3009_Report_Version(month, year, session, status,
				unit_sus_no1);
		model.put("list", list);
		model.put("size", list.size());


		tx.commit();
		sessionHQL.close();

		if (Approve == true) {
			liststr.add("Approved Successfully.");
		} else {
			liststr.add("Approved Not Successfully.");
		}
		model.put("msg", liststr.get(0));

		return new ModelAndView("Search_Report_3009_Tiles");
	}

}
