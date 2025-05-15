package com.controller.fpmis;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.fpmis.FP_MIS_DashboardDAO;
import com.dao.fpmis.FP_MIS_ReportsDAO;
import com.dao.fpmis.FP_MIS_TransactionDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.Helpdesk.HD_TB_BISAG_FAQ;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class FP_MIS_Dashboard_Controller {

	@Autowired
	private FP_MIS_TransactionDAO fp_trDao;

	@Autowired
	private FP_MIS_ReportsDAO fp_rpDao;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private FP_MIS_DashboardDAO fp_dbDao;

	FP_MIS_DashboardDAO fpDash;

	@RequestMapping(value = "/admin/topCdrDashboard", method = RequestMethod.GET)
	public ModelAndView AllDashboard(ModelMap Mmap, HttpSession session, HttpServletRequest request) {
		String successValue = session.getAttribute("successValue").toString();
		if (successValue.equals("MISO")) {
			return new ModelAndView("topCdrDashboardTiles");
		} else {
			session.invalidate();
			return new ModelAndView("redirect:login");
		}

	}

	public String getHoldingStateDatass(HttpSession sessionA, String sus_no, String fin_year) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String m1_tryear = fin_year;
		ArrayList<ArrayList<String>> lists = fp_rpDao.nFundStatusHQHead(sessionA, m1_tryear, "", "", "HQ",
				"-1_X000000000_A000001", "2", "fp_dte", "CR");
		String list1 = "";
		for (int i = 0; i < lists.size(); i++) {
			list1 += ",{'colname' : '" + lists.get(i).get(1) + "' ,'colcode' : '" + lists.get(0).get(0)
					+ "' , 'allot' : " + lists.get(i).get(3) + ", 'exp' : " + lists.get(0).get(4) + ", 'cdabk' : "
					+ lists.get(i).get(10) + "}";
		}

		if (list1.length() > 0) {
			list1 = "[" + list1.substring(1, list1.length());
			list1 += "]";
		} else {
			list1 = "[]";
		}
		return list1;
	}

	@RequestMapping(value = "/fpCodeHeadChartss", method = RequestMethod.POST)
	public ArrayList<ArrayList<String>> fpCodeHeadChartss(HttpSession sessionA, String sus_no, String fin_year,
			String tr_head) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String m1_tryear = fin_year;
		ArrayList<ArrayList<String>> lists = fp_rpDao.nFundStatusHQHead(sessionA, m1_tryear, "", "", "HQ",
				"-1_X000000000_A000001", "2", "fp_dte", "CR");
		String list1 = "";
		return lists;
	}

	@RequestMapping(value = "/admin/fpmyDashboard", method = RequestMethod.GET)
	public ModelAndView fpmyDashboard(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, String nPara) {
		ArrayList<ArrayList<String>> nDbData = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> nDbData1 = new ArrayList<ArrayList<String>>();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fpmyDashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String rolefmn = session.getAttribute("roleFormationNo").toString();
		String nUsrId = session.getAttribute("userId").toString();
		String m1_tryear = "2020";
		String m1_lvl = roleSusNo;
		String m1_hdlvl = "2";
		nDbData = fp_dbDao.nFundPerfMeter(session, m1_tryear, m1_lvl, m1_hdlvl, nUsrId);
		Mmap.put("nDbData", nDbData);
		Mmap.put("nDbDataLen", nDbData.size());
		nDbData1 = fp_dbDao.nFundPerTrend(session, m1_tryear, m1_lvl, m1_hdlvl, nUsrId, "110");
		Mmap.put("nDbData1", nDbData1);
		Mmap.put("nDbData1Len", nDbData1.size());
		Mmap.put("msg", msg);
		Mmap.put("n_sel", nPara);
		return new ModelAndView("fp_my_DashTiles");
	}

	@RequestMapping(value = "/getsPerTrend", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getsPerTrend(HttpSession sessionA, String val) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String nUsrId = sessionA.getAttribute("userId").toString();
		String m1_tryear = fp_trDao.nGetAdmFinYear("CFY");
		String m1_lvl = roleSusNo;
		String m1_hdlvl = "2";
		String m1_hdlvlval = val;
		ArrayList<ArrayList<String>> nDbData = new ArrayList<ArrayList<String>>();
		nDbData = fp_dbDao.nFundPerTrend(sessionA, m1_tryear, m1_lvl, m1_hdlvl, nUsrId, m1_hdlvlval);
		return nDbData;
	}

	@RequestMapping(value = "/admin/fpTopDashbd")
	public ModelAndView fpTopDashbd(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		int userid = (Integer) sessionA.getAttribute("userId");
		String usrid = sessionA.getAttribute("username").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleType = sessionA.getAttribute("roleType").toString();

		Boolean val = roledao.ScreenRedirect("fp_fund_status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String nPara = "";
		String nUnty = null;
		String rsfmt = "CR";
		nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);
		if (Integer.parseInt(nUnty) <= 2) {
			rsfmt = "RS";
		} else {
			rsfmt = "CR";
		}

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		rolefmn = li.get(0).get(0);
		nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);

		String nParaValue = "NIL";
		String domainid = "";
		Mmap.put("n_2", nParaValue);
		Mmap.put("n_3", domainid);
		Mmap.put("n_4", nPara);
		if (!nUnty.equalsIgnoreCase("5")) {
			Mmap.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));
		} else {
			Mmap.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));
		}

		Mmap.put("n_unt", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));
		Mmap.put("n_head", fp_trDao.getHeadlist("", sessionA, "2076"));
		ArrayList<ArrayList<String>> finli = fp_trDao.FindFinYear("", sessionA, "");
		String cfinli = fp_trDao.nGetAdmFinYear("CFY");
		Mmap.put("n_finyr", finli);
		Mmap.put("n_cfinyr", cfinli);
		if (nUnty.equalsIgnoreCase("5")) {
			Mmap.put("n_1", fp_rpDao.nFundStatusHQHead(sessionA, cfinli, "", "HEADDT", "UT", nPara, "4", usrid, rsfmt));
			Mmap.put("ndb_1", fp_rpDao.nFundStatusHQHeadCDB(sessionA, cfinli, "", "HEADDT", "UT", nPara, "4", usrid));
			Mmap.put("n_sel", cfinli + ":UT:" + nPara + ":1:" + usrid);
		} else {

			if (Integer.parseInt(nUnty) <= 1) {
				Mmap.put("n_1",
						fp_rpDao.nFundStatusHQHead(sessionA, cfinli, "", "HEADDT", "HQ", nPara, "1", usrid, rsfmt));
				Mmap.put("ndb_1",
						fp_rpDao.nFundStatusHQHeadCDB(sessionA, cfinli, "", "HEADDT", "HQ", nPara, "1", usrid));
				Mmap.put("n_sel", cfinli + ":HQ:" + nPara + ":1:" + usrid);
			} else {
				Mmap.put("n_1",
						fp_rpDao.nFundStatusHQHead(sessionA, cfinli, "", "HEADDT", "HQ", nPara, "4", usrid, rsfmt));
				Mmap.put("ndb_1",
						fp_rpDao.nFundStatusHQHeadCDB(sessionA, cfinli, "", "HEADDT", "HQ", nPara, "4", usrid));
				Mmap.put("n_sel", cfinli + ":HQ:" + nPara + ":1:" + usrid);
			}
		}
		Mmap.put("msg", msg);
		/* return new ModelAndView("fp_topDashbdTiles"); */
		return new ModelAndView("fptopDashbdTiles");
	}

	@RequestMapping(value = "/admin/fpSecDashboardWpn", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardWpn_1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, String nPara) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fpSecDashboardWpn", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		nPara = "WPN";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String rolefmn = session.getAttribute("roleFormationNo").toString();
		int fy = Year.now().getValue();
		String fin_year = String.valueOf(fy - 1);
		String tr_head = "";
		String nUnty = fp_trDao.getUnitType("", session, "FMN_" + rolefmn);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}

	@RequestMapping(value = "/admin/fpSecDashboardTpt", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardTpt(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, String nPara) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fpSecDashboardTpt", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		nPara = "TPT";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String rolefmn = session.getAttribute("roleFormationNo").toString();
		int fy = Year.now().getValue();
		String fin_year = String.valueOf(fy - 1);
		String tr_head = "";
		String nUnty = fp_trDao.getUnitType("", session, "FMN_" + rolefmn);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}

	@RequestMapping(value = "/admin/fpSecDashboardFp", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, String nPara) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fpSecDashboardFp", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		nPara = "FP";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String rolefmn = session.getAttribute("roleFormationNo").toString();
		int fy = Year.now().getValue();
		String fin_year = String.valueOf(fy - 1);
		String tr_head = "";
		String nUnty = fp_trDao.getUnitType("", session, "FMN_" + rolefmn);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}

	@RequestMapping(value = "/admin/fpSecDashboardColR", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardColR(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, String nPara) {

		String roleid = session.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("fpSecDashboardColR", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 * if(request.getHeader("Referer") == null ) { msg = ""; //return new
		 * ModelAndView("redirect:bodyParameterNotAllow"); }
		 */
		String nodelid = "";
		String nodal = "";
		// System.out.println("-nPara-1-"+nPara);
		if (nPara == null) {
			nPara = "COLR";
			nodelid = session.getAttribute("username").toString();
			nodal = fp_trDao.nGetUnitNodal(nodelid);

		} else {
			nodal = nPara;
		}
		// System.out.println("-nPara-2-"+nPara);
		// System.out.println("--"+nodelid+","+nodal);
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String rolefmn = session.getAttribute("roleFormationNo").toString();
		int fy = Year.now().getValue();
		String fin_year = String.valueOf(fy - 1);
		String tr_head = "";
		String nUnty = fp_trDao.getUnitType("", session, "FMN_" + rolefmn);
		if (nodal != null) {
			ArrayList<ArrayList<String>> nDbData = new ArrayList<ArrayList<String>>();
			nDbData = fp_rpDao.fpCORBaseData(session, nodal);
			Mmap.put("nBase", nDbData);
		}
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_ColSecDashboardTiles");
	}

	@RequestMapping(value = "/admin/fpSecDashboardTest45")
	public void fpSecDashboardTpt(HttpServletRequest request, HttpServletResponse response, ModelMap m)
			throws IOException {
		response.sendRedirect("https://152.1.13.45:8443");
	}

	@RequestMapping(value = "/admin/fpSecDashboardTest72")
	public void fpSecDashboardWpn(HttpServletRequest request, HttpServletResponse response, ModelMap m)
			throws IOException {
		response.sendRedirect("https://152.1.13.72:8443");
	}

	@RequestMapping(value = "/admin/fpSecDashboardRoll", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardRoll(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, String nPara) {

		String roleid = session.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("fpSecDashboardColR", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 * if(request.getHeader("Referer") == null ) { msg = ""; //return new
		 * ModelAndView("redirect:bodyParameterNotAllow"); }
		 */
		nPara = "ROLL";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String rolefmn = session.getAttribute("roleFormationNo").toString();
		int fy = Year.now().getValue();
		String fin_year = String.valueOf(fy - 1);
		String tr_head = "";
		String nUnty = fp_trDao.getUnitType("", session, "FMN_" + rolefmn);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}

	@RequestMapping(value = "/admin/fpSecDashboardDV", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardDV(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, String nPara) {

		String roleid = session.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("fpSecDashboardColR", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 * if(request.getHeader("Referer") == null ) { msg = ""; //return new
		 * ModelAndView("redirect:bodyParameterNotAllow"); }
		 */
		nPara = "DV";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String rolefmn = session.getAttribute("roleFormationNo").toString();
		int fy = Year.now().getValue();
		String fin_year = String.valueOf(fy - 1);
		String tr_head = "";
		String nUnty = fp_trDao.getUnitType("", session, "FMN_" + rolefmn);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}

	@RequestMapping(value = "/admin/fpTopDashbdData")
	public @ResponseBody ArrayList<ArrayList<String>> fpTopDashbdData(HttpSession sessionA, String nHead) {
		int userid = (Integer) sessionA.getAttribute("userId");
		String usrid = sessionA.getAttribute("username").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String nPara = "";
		String nUnty = null;
		String rsfmt = "CR";
		nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);
		if (Integer.parseInt(nUnty) <= 2) {
			rsfmt = "RS";
		} else {
			rsfmt = "CR";
		}
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		rolefmn = li.get(0).get(0);
		nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);

		String nParaValue = "NIL";
		String domainid = "";
		ArrayList<ArrayList<String>> finli = fp_trDao.FindFinYear("", sessionA, "");
		String cfinli = fp_trDao.nGetAdmFinYear("CFY");
		ArrayList<ArrayList<String>> nDbData = new ArrayList<ArrayList<String>>();
		if (nUnty.equalsIgnoreCase("5")) {
			nDbData = fp_rpDao.nFundStatusHQHead(sessionA, cfinli, "", "HEADDT", "UT", nPara, "1", usrid, rsfmt);
		} else {
			if (Integer.parseInt(nUnty) <= 1) {
				nDbData = fp_rpDao.nFundStatusHQHead(sessionA, cfinli, "", "HEADDT", "HQ", nPara, "1", usrid, rsfmt);
			} else {
				nDbData = fp_rpDao.nFundStatusHQHead(sessionA, cfinli, "", "HEADDT", "HQ", nPara, "1", usrid, rsfmt);
			}
		}
		return nDbData;
	}

	@RequestMapping(value = "/admin/fpTopDashbdData2")
	public @ResponseBody ArrayList<ArrayList<String>> fpTopDashbdData2(HttpSession sessionA, String nHead) {
		int userid = (Integer) sessionA.getAttribute("userId");
		String usrid = sessionA.getAttribute("username").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String nPara = "";
		String nUnty = null;
		String rsfmt = "CR";
		nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);
		if (Integer.parseInt(nUnty) <= 2) {
			rsfmt = "RS";
		} else {
			rsfmt = "CR";
		}
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		rolefmn = li.get(0).get(0);
		nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);
		String nParaValue = "NIL";
		String domainid = "";
		ArrayList<ArrayList<String>> finli = fp_trDao.FindFinYear("", sessionA, "");
		String cfinli = fp_trDao.nGetAdmFinYear("CFY");
		ArrayList<ArrayList<String>> nDbData = new ArrayList<ArrayList<String>>();
		if (nUnty.equalsIgnoreCase("5")) {
			nDbData = fp_rpDao.nFundStatusHQHeadCDB(sessionA, cfinli, "", "HEADDT", "UT", nPara, "1", usrid);
		} else {
			if (Integer.parseInt(nUnty) <= 1) {
				nDbData = fp_rpDao.nFundStatusHQHeadCDB(sessionA, cfinli, "", "HEADDT", "HQ", nPara, "1", usrid);
			} else {
				nDbData = fp_rpDao.nFundStatusHQHeadCDB(sessionA, cfinli, "", "HEADDT", "HQ", nPara, "1", usrid);
			}
		}
		return nDbData;
	}

	/*
	 * @RequestMapping(value = "/whatsnewinfp", method = RequestMethod.GET) public
	 * ModelAndView whatsnewinfp(ModelMap Mmap, HttpSession session,
	 * 
	 * @RequestParam(value = "msg", required = false) String msg, String
	 * section,HttpServletRequest request) { String roleid =
	 * session.getAttribute("roleid").toString(); Boolean val =
	 * roledao.ScreenRedirect("faq", roleid); if (val == false) { return new
	 * ModelAndView("AccessTiles"); } ArrayList<ArrayList<String>> list =
	 * fp_rpDao.getNewfaqdetail1(); List<String> secList = fp_rpDao.getsecList();
	 * List<String> queList = fp_rpDao.getqueList(); String faq = ""; for (int i =
	 * 0; i < secList.size(); i++) { faq += "<div class='panel panel-default'>" +
	 * "<div class='panel-heading' role='tab' id='heading" + i + "'>" +
	 * "<h3 class='panel-title'>" +
	 * "<a class='collapsed' role='button' data-toggle='collapse' data-parent='#accordion' href='#collapse"
	 * + i + "' aria-expanded='true' aria-controls='collapse" + i + "'>" +
	 * secList.get(i) + "</a>" + "</h3>" + "</div>";
	 * 
	 * faq += "<div id='collapse" + i +
	 * "' class='panel-collapse collapse' role='tabpanel' aria-labelledby='heading"
	 * + i + "'>" + "<div class='panel-body px-3 mb-4'>";
	 * 
	 * for (int j = 0; j < list.size(); j++) { if
	 * (secList.get(i).equals(list.get(j).get(2))) { faq +=
	 * "<div class='panel-group' id='accordionab' role='tablist' aria-multiselectable='true'>"
	 * + "<div class='panel panel-default-que'>" +
	 * "<div class='panel-heading-que' role='tab' id='headingab" + j + "'>" +
	 * "<h3 class='panel-title-que'>" +
	 * "<a class='collapsed' role='button' data-toggle='collapse' data-parent='#accordionab' href='#collapseab"
	 * + j + "' aria-expanded='true' aria-controls='collapseab" + j + "'>" +
	 * list.get(j).get(0) + "</a>" + "</h3>" + "</div>"; faq +=
	 * "<div id='collapseab" + j +
	 * "' class='panel-collapse collapse' role='tabpanel' aria-labelledby='headingab"
	 * + j + "'>" + "<div class='panel-body-que px-3 mb-4'>";
	 * 
	 * for (int k = 0; k < list.size(); k++) { if
	 * (queList.get(j).equals(list.get(k).get(0))) { faq += "<p>" +
	 * list.get(k).get(1) + "</p>"; } } faq += "</div>" + "</div>" + "</div>" +
	 * "</div>"; } } faq += "</div>" + "</div>"; faq += "</div>"; }
	 * 
	 * if(request.getHeader("Referer") == null ) { msg = ""; } Mmap.put("faq", faq);
	 * Mmap.put("msg", msg); return new ModelAndView("faqTiles", "faqCmd", new
	 * HD_TB_BISAG_FAQ()); }
	 */

	@RequestMapping(value = "/admin/fplogininfo", method = RequestMethod.GET)
	public ModelAndView fplogininfo(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, String nPara) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fplogininfo", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String rolefmn = session.getAttribute("roleFormationNo").toString();
		int fy = Year.now().getValue();
		String fin_year = String.valueOf(fy - 1);
		String tr_head = "";
		String nUnty = fp_trDao.getUnitType("", session, "SUS_" + roleSusNo);
		String nPara1 = nUnty + "_" + rolefmn + "_" + roleSusNo;
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		ArrayList<ArrayList<String>> nDbData = new ArrayList<ArrayList<String>>();
		nDbData = fp_rpDao.nLoginInfoData(session, "", "", "HEADDT", "HQ", nPara1, "1", "");
		Mmap.put("nList", nDbData);
		Mmap.put("n_hq", fp_trDao.getunitBuglist("", session, "SUS_" + nPara1));
		Mmap.put("msg", msg);
		return new ModelAndView("fp_logininfoTiles");
	} 

	@RequestMapping(value = "/fplogininfodata", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> fplogininfodata(HttpSession sessionA, String nPara) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();
		String nUsrId = sessionA.getAttribute("userId").toString();
		// String nPara1=nUnty+"_"+rolefmn+"_"+nPara;
		ArrayList<ArrayList<String>> nDbData = new ArrayList<ArrayList<String>>();
		nDbData = fp_rpDao.nLoginInfoData(sessionA, "", "", "HEADDT", "HQ", nPara, "1", "");
		return nDbData;
	}

	/*
	 * @RequestMapping(value = "/fpCORBaseData", method = RequestMethod.POST)
	 * public @ResponseBody ArrayList<ArrayList<String>> fpCORBaseData(HttpSession
	 * sessionA,String nPara) { String roleAccess =
	 * sessionA.getAttribute("roleAccess").toString(); String roleSubAccess =
	 * sessionA.getAttribute("roleSubAccess").toString(); String roleFormationNo =
	 * sessionA.getAttribute("roleFormationNo").toString(); String roleSusNo =
	 * sessionA.getAttribute("roleSusNo").toString(); String
	 * rolefmn=sessionA.getAttribute("roleFormationNo").toString(); String
	 * nUsrId=sessionA.getAttribute("userId").toString(); //String
	 * nPara1=nUnty+"_"+rolefmn+"_"+nPara; nPara="SIKHLI";
	 * ArrayList<ArrayList<String>> nDbData = new ArrayList<ArrayList<String>>();
	 * nDbData=fp_rpDao.fpCORBaseData(sessionA,nPara); return nDbData; }
	 * 
	 */ /*
		 * @RequestMapping(value = "/admin/fpSecDashboardFp_t1", method =
		 * RequestMethod.GET) public ModelAndView fpSecDashboardFp_t1(ModelMap
		 * Mmap,HttpSession session,@RequestParam(value = "msg", required = false)
		 * String msg,HttpServletRequest request,String nPara) {
		 * System.out.println("fpSecDashboardFp_t1"); String roleid =
		 * session.getAttribute("roleid").toString(); Boolean val =
		 * roledao.ScreenRedirect("fpSecDashboardFp", roleid); if(val == false) { return
		 * new ModelAndView("AccessTiles"); } if(request.getHeader("Referer") == null )
		 * { msg = ""; //return new ModelAndView("redirect:bodyParameterNotAllow"); }
		 * nPara="FPT1"; String roleSusNo =
		 * session.getAttribute("roleSusNo").toString(); String
		 * rolefmn=session.getAttribute("roleFormationNo").toString(); int fy =
		 * Year.now().getValue(); String fin_year = String.valueOf(fy-1) ; String
		 * tr_head = ""; String nUnty=fp_trDao.getUnitType("",session,"FMN_"+rolefmn);
		 * Mmap.put("sus_no", roleSusNo); Mmap.put("nPara", nPara); Mmap.put("msg",
		 * msg); System.out.println("fpSecDashboardFp_t1---out"); return new
		 * ModelAndView("fp_SecDashboardTiles"); }
		 * 
		 * @RequestMapping(value = "/admin/fpSecDashboardFp_t2", method =
		 * RequestMethod.GET) public ModelAndView fpSecDashboardFp_t2(ModelMap
		 * Mmap,HttpSession session,@RequestParam(value = "msg", required = false)
		 * String msg,HttpServletRequest request,String nPara) {
		 * 
		 * String roleid = session.getAttribute("roleid").toString(); Boolean val =
		 * roledao.ScreenRedirect("fpSecDashboardFp", roleid); if(val == false) { return
		 * new ModelAndView("AccessTiles"); } if(request.getHeader("Referer") == null )
		 * { msg = ""; //return new ModelAndView("redirect:bodyParameterNotAllow"); }
		 * nPara="FPT2"; String roleSusNo =
		 * session.getAttribute("roleSusNo").toString(); String
		 * rolefmn=session.getAttribute("roleFormationNo").toString(); int fy =
		 * Year.now().getValue(); String fin_year = String.valueOf(fy-1) ; String
		 * tr_head = ""; String nUnty=fp_trDao.getUnitType("",session,"FMN_"+rolefmn);
		 * Mmap.put("sus_no", roleSusNo); Mmap.put("nPara", nPara); Mmap.put("msg",
		 * msg); return new ModelAndView("fp_SecDashboardTiles"); }
		 * 
		 * @RequestMapping(value = "/admin/fpSecDashboardFp_t3", method =
		 * RequestMethod.GET) public ModelAndView fpSecDashboardFp_t3(ModelMap
		 * Mmap,HttpSession session,@RequestParam(value = "msg", required = false)
		 * String msg,HttpServletRequest request,String nPara) {
		 * 
		 * String roleid = session.getAttribute("roleid").toString(); Boolean val =
		 * roledao.ScreenRedirect("fpSecDashboardFp", roleid); if(val == false) { return
		 * new ModelAndView("AccessTiles"); } if(request.getHeader("Referer") == null )
		 * { msg = ""; //return new ModelAndView("redirect:bodyParameterNotAllow"); }
		 * nPara="FPT3"; String roleSusNo =
		 * session.getAttribute("roleSusNo").toString(); String
		 * rolefmn=session.getAttribute("roleFormationNo").toString(); int fy =
		 * Year.now().getValue(); String fin_year = String.valueOf(fy-1) ; String
		 * tr_head = ""; String nUnty=fp_trDao.getUnitType("",session,"FMN_"+rolefmn);
		 * Mmap.put("sus_no", roleSusNo); Mmap.put("nPara", nPara); Mmap.put("msg",
		 * msg); return new ModelAndView("fp_SecDashboardTiles"); }
		 * 
		 * @RequestMapping(value = "/admin/fpSecDashboardFp_t4", method =
		 * RequestMethod.GET) public ModelAndView fpSecDashboardFp_t4(ModelMap
		 * Mmap,HttpSession session,@RequestParam(value = "msg", required = false)
		 * String msg,HttpServletRequest request,String nPara) {
		 * 
		 * String roleid = session.getAttribute("roleid").toString(); Boolean val =
		 * roledao.ScreenRedirect("fpSecDashboardFp", roleid); if(val == false) { return
		 * new ModelAndView("AccessTiles"); } if(request.getHeader("Referer") == null )
		 * { msg = ""; //return new ModelAndView("redirect:bodyParameterNotAllow"); }
		 * nPara="FPT4"; String roleSusNo =
		 * session.getAttribute("roleSusNo").toString(); String
		 * rolefmn=session.getAttribute("roleFormationNo").toString(); int fy =
		 * Year.now().getValue(); String fin_year = String.valueOf(fy-1) ; String
		 * tr_head = ""; String nUnty=fp_trDao.getUnitType("",session,"FMN_"+rolefmn);
		 * Mmap.put("sus_no", roleSusNo); Mmap.put("nPara", nPara); Mmap.put("msg",
		 * msg); return new ModelAndView("fp_SecDashboardTiles"); }
		 * 
		 * @RequestMapping(value = "/admin/fpSecDashboardFp_t5", method =
		 * RequestMethod.GET) public ModelAndView fpSecDashboardFp_t5(ModelMap
		 * Mmap,HttpSession session,@RequestParam(value = "msg", required = false)
		 * String msg,HttpServletRequest request,String nPara) {
		 * 
		 * String roleid = session.getAttribute("roleid").toString(); Boolean val =
		 * roledao.ScreenRedirect("fpSecDashboardFp", roleid); if(val == false) { return
		 * new ModelAndView("AccessTiles"); } if(request.getHeader("Referer") == null )
		 * { msg = ""; //return new ModelAndView("redirect:bodyParameterNotAllow"); }
		 * nPara="FPT5"; String roleSusNo =
		 * session.getAttribute("roleSusNo").toString(); String
		 * rolefmn=session.getAttribute("roleFormationNo").toString(); int fy =
		 * Year.now().getValue(); String fin_year = String.valueOf(fy-1) ; String
		 * tr_head = ""; String nUnty=fp_trDao.getUnitType("",session,"FMN_"+rolefmn);
		 * Mmap.put("sus_no", roleSusNo); Mmap.put("nPara", nPara); Mmap.put("msg",
		 * msg); return new ModelAndView("fp_SecDashboardTiles"); }
		 */
}
