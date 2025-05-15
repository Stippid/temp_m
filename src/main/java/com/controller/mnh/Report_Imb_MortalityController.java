package com.controller.mnh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.MNH_ReportsDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Report_Imb_MortalityController {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private MNH_Common_DAO mnh1_Dao;

	@Autowired
	private MNH_ReportsDAO mnh7_Dao;

	@Autowired
	private MNH_CommonController mcommon;

	MNH_ValidationController validation = new MNH_ValidationController();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	// REPORTS IMB REPORT

	@RequestMapping(value = "/admin/imb_report", method = RequestMethod.GET)
	public ModelAndView imb_report(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		Boolean val = roledao.ScreenRedirect("imb_report", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	       
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		Mmap.put("date", date1);
		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		Mmap.put("getCommandList", all.getCommandDetailsList());
		//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_3", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("ml_6", mcommon.getMedrelationList("", session));
		Mmap.put("ml_9", mcommon.getDis_type("", "", session));
		Mmap.put("ml_10", mcommon.getDis_subtype("", "", session));
		Mmap.put("ml_5", mcommon.getPrincipalCause("", "", session));
		Mmap.put("ml_8", mcommon.getBlockDis("", "", session));
		return new ModelAndView("Imb_reportsTiles");
	}

	// Search Imb
	@RequestMapping(value = "/search_imb_reports", method = RequestMethod.POST)
	public ModelAndView search_imb_reports(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			@RequestParam(value = "cmd1", required = false) String cmd1, String category1, String from_date1,
			String to_date1, String disease_principal1, String disease_type1, String disease_subtype1,
			String block_description1) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("imb_report", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		if (from_date1.equals("") || from_date1 == "") {
			Mmap.put("msg", "Please Select From Date");
			return new ModelAndView("redirect:imb_report");
		}

		if (to_date1.equals("") || to_date1 == "") {
			Mmap.put("msg", "Please Select To Date");
			return new ModelAndView("redirect:imb_report");
		}

		if (validation.RankCodeLength(from_date1) == false) {
			Mmap.put("msg", validation.from_dateMSG);
			return new ModelAndView("redirect:imb_report");
		}

		if (validation.RankCodeLength(to_date1) == false) {
			Mmap.put("msg", validation.to_dateMSG);
			return new ModelAndView("redirect:imb_report");
		}
		if (from_date1 != "" && to_date1 != "") {
			if (mcommon.CompareDate(from_date1, to_date1) == 0) {
				Mmap.put("msg", "To Date should not be less than From Date");
				return new ModelAndView("redirect:imb_report");
			}
		}
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		Mmap.put("r_1", l1);

		Mmap.put("cmd1", cmd1);
		Mmap.put("category1", category1);
		Mmap.put("from_date1", from_date1);
		Mmap.put("to_date1", to_date1);
		Mmap.put("disease_principal1", disease_principal1);
		Mmap.put("disease_type1", disease_type1);
		Mmap.put("disease_subtype1", disease_subtype1);
		Mmap.put("block_description1", block_description1);

		List<Map<String, Object>> list = mnh7_Dao.search_imb_reports(cmd1, category1, from_date1, to_date1,
				disease_principal1, disease_type1, disease_subtype1, block_description1);
		// List<Map<String, Object>> list=
		// mnh7_Dao.search_imb_reports(command,category,from_date,to_date);
		Mmap.put("date", date1);
		Mmap.put("list", list);
		Mmap.put("size", list.size());
		Mmap.put("getCommandList", all.getCommandDetailsList());
		//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("ml_6", mcommon.getMedrelationList("", session));
		Mmap.put("ml_9", mcommon.getDis_type("", "", session));
		Mmap.put("ml_10", mcommon.getDis_subtype("", "", session));
		Mmap.put("ml_5", mcommon.getPrincipalCause("", "", session));
		Mmap.put("ml_8", mcommon.getBlockDis("", "", session));
		return new ModelAndView("Imb_reportsTiles");
	}

	// REPORTS MODULE (7)-> (IMB REPORT SCREEN END)

	///// MORTALITY REPORT

	@RequestMapping(value = "/admin/mortality_report", method = RequestMethod.GET)
	public ModelAndView mortality_report(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		Boolean val = roledao.ScreenRedirect("mortality_report", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	       
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		Mmap.put("date", date1);
		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		Mmap.put("getCommandList", all.getCommandDetailsList());

		//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("ml_6", mcommon.getMedrelationList("", session));
		Mmap.put("ml_9", mcommon.getDis_type("", "", session));
		Mmap.put("ml_10", mcommon.getDis_subtype("", "", session));
		Mmap.put("ml_5", mcommon.getPrincipalCause("", "", session));
		Mmap.put("ml_8", mcommon.getBlockDis("", "", session));
		return new ModelAndView("mortality_reportsTiles");
	}

	// search mortality
	@RequestMapping(value = "/search_mortality_reports", method = RequestMethod.POST)
	public ModelAndView search_mortality_reports(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			@RequestParam(value = "cmd1", required = false) String cmd1, String category1, String from_date1,
			String to_date1, String disease_principal1, String disease_type1, String disease_subtype1,
			String block_description1) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Boolean val = roledao.ScreenRedirect("mortality_report", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		if (from_date1.equals("") || from_date1 == "") {
			Mmap.put("msg", "Please Select From Date");
			return new ModelAndView("redirect:mortality_report");
		}

		if (to_date1.equals("") || to_date1 == "") {
			Mmap.put("msg", "Please Select To Date");
			return new ModelAndView("redirect:mortality_report");
		}

		if (validation.RankCodeLength(from_date1) == false) {
			Mmap.put("msg", validation.from_dateMSG);
			return new ModelAndView("redirect:mortality_report");
		}

		if (validation.RankCodeLength(to_date1) == false) {
			Mmap.put("msg", validation.to_dateMSG);
			return new ModelAndView("redirect:mortality_report");
		}
		if (from_date1 != "" && to_date1 != "") {
			if (mcommon.CompareDate(from_date1, to_date1) == 0) {
				Mmap.put("msg", "To Date should not be less than From Date");
				return new ModelAndView("redirect:mortality_report");
			}
		}
		Mmap.put("r_1", l1);
		Mmap.put("date", date1);
		Mmap.put("cmd1", cmd1);
		Mmap.put("category1", category1);
		Mmap.put("from_date1", from_date1);
		Mmap.put("to_date1", to_date1);
		Mmap.put("disease_principal1", disease_principal1);
		Mmap.put("disease_type1", disease_type1);
		Mmap.put("disease_subtype1", disease_subtype1);
		Mmap.put("block_description1", block_description1);
		List<Map<String, Object>> list = mnh7_Dao.search_mortality_reports(cmd1, category1, from_date1, to_date1,
				disease_principal1, disease_type1, disease_subtype1, block_description1);

		Mmap.put("list", list);
		Mmap.put("size", list.size());
		Mmap.put("getCommandList", all.getCommandDetailsList());
		//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("ml_6", mcommon.getMedrelationList("", session));
		Mmap.put("ml_9", mcommon.getDis_type("", "", session));
		Mmap.put("ml_10", mcommon.getDis_subtype("", "", session));
		Mmap.put("ml_5", mcommon.getPrincipalCause("", "", session));
		Mmap.put("ml_8", mcommon.getBlockDis("", "", session));
		return new ModelAndView("mortality_reportsTiles");
	}
}