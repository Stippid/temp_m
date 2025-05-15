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

public class Reports_HIVController {

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
	// REPORTS HIV/AIDS REPORT
	@RequestMapping(value = "/admin/mnh_hivaids_report", method = RequestMethod.GET)
	public ModelAndView mnh_hivaids_report(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		Boolean val = roledao.ScreenRedirect("mnh_hivaids_report", session.getAttribute("roleid").toString());
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
		Mmap.put("ml_9", mcommon.getDis_type("", "", session));
		Mmap.put("ml_10", mcommon.getDis_subtype("", "", session));
		Mmap.put("ml_5", mcommon.getPrincipalCause("", "", session));
		Mmap.put("ml_8", mcommon.getBlockDis("", "", session));
		Mmap.put("ml_6", mcommon.getMedrelationList("", session));
		return new ModelAndView("mnh_hivaids_reportTiles");
	}

	// search
	@RequestMapping(value = "/search_hiv_report", method = RequestMethod.POST)
	public ModelAndView search_hiv_report(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "command12", required = false) String command12, String category1, String service1,
			String relation1, String disease_principal1, String disease_type1, String disease_subtype1,
			String block_description1, String from_dt1, String to_dt1) {

		Boolean val = roledao.ScreenRedirect("mnh_hivaids_report", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		  if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	       
		String username = (String) session.getAttribute("username");
		
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		if (from_dt1.equals("") || from_dt1 == "") {
			Mmap.put("msg", "Please Select From Date");
			return new ModelAndView("redirect:mnh_hivaids_report");
		}

		if (to_dt1.equals("") || to_dt1 == "") {
			Mmap.put("msg", "Please Select To Date");
			return new ModelAndView("redirect:mnh_hivaids_report");
		}
		if (validation.RankCodeLength(from_dt1) == false) {
			Mmap.put("msg", validation.from_dateMSG);
			return new ModelAndView("redirect:mnh_hivaids_report");
		}

		if (validation.RankCodeLength(to_dt1) == false) {
			Mmap.put("msg", validation.to_dateMSG);
			return new ModelAndView("redirect:mnh_hivaids_report");
		}
		if (from_dt1 != "" && to_dt1 != "") {
			if (mcommon.CompareDate(from_dt1, to_dt1) == 0) {
				Mmap.put("msg", "To Date should not be less than From Date");
				return new ModelAndView("redirect:mnh_hivaids_report");
			}
		}
		Mmap.put("r_1", l1);

		Mmap.put("command12", command12);
		Mmap.put("category1", category1);
		Mmap.put("service1", service1);
		Mmap.put("relation1", relation1);

		Mmap.put("disease_principal1", disease_principal1);
		Mmap.put("disease_type1", disease_type1);
		Mmap.put("disease_subtype1", disease_subtype1);
		Mmap.put("block_description1", block_description1);
		Mmap.put("date", date1);

		Mmap.put("from_dt1", from_dt1);
		Mmap.put("to_dt1", to_dt1);

		List<Map<String, Object>> list = mnh7_Dao.getsearch_hiv_aids_rep(command12, category1, service1, relation1,
				disease_principal1, disease_type1, disease_subtype1, block_description1, from_dt1, to_dt1);
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum = sum + (int) (long) list.get(i).get("total");

		}
		Mmap.put("total", sum);

		Mmap.put("list", list);
		Mmap.put("size", list.size());
		Mmap.put("getCommandList", all.getCommandDetailsList());
		//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_3", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("ml_6", mcommon.getMedrelationList("", session));
		Mmap.put("ml_5", mcommon.getPrincipalCause("", "", session));
		Mmap.put("ml_8", mcommon.getBlockDis("", "", session));
		Mmap.put("ml_9", mcommon.getDis_type("", "", session));
		Mmap.put("ml_10", mcommon.getDis_subtype("", "", session));
		return new ModelAndView("mnh_hivaids_reportTiles");
	}
}
