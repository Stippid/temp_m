package com.controller.tms;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.orbat.ReportsController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Enrollment_Reports.Age_Wise_DtlsDAO;
import com.dao.tms.Discard_condition_five_yearsDAO;
import com.dao.tms.abc_vehicleDetailsDAO;
import com.dao.tms.vehicleDetailsDAO;
import com.models.Tbl_CodesForm;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Discard_condition_five_yearsController {

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private Discard_condition_five_yearsDAO discard_dao;
	

	
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	ReportsController rcont = new ReportsController();
	
	
	
	
	@RequestMapping(value = "/Discard_condition_five_years_reportUrl")
	public ModelAndView Discard_condition_five_years_reportUrl(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Qfd_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String select="<option value='0'>-- Select --</option>";
		model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
		model.put("msg", msg);
		if(roleAccess.equals("Line_dte")) {
			model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			model.put("selectLine_dte", select);
			model.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		model.put("getTypeOfUnitList", all.getTypeOfUnitList());
		return new ModelAndView("Discard_condition_five_years_report_tiles");
	}

	@RequestMapping(value = "/getSearch_discard_condition_report", method = RequestMethod.POST)
	public ModelAndView getSearch_oh_qfd_report(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type_veh1", required = false) String type_veh,
			@RequestParam(value = "CheckVal1", required = false) String checkVal,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "ddlYears1", required = false) String ddlYears) {
		if (type_veh.equals("")) {
			model.put("msg", "Please Select Veh Cat");
		}
		
//	 else if(prf_code.equals("0")){ model.put("msg","please select Veh Type"); }
//		 
		 else {
	ArrayList<ArrayList<String>> list = discard_dao.search_veh_name(type_veh, month, ddlYears,checkVal);
	model.put("list", list);
		model.put("size", list.size());
			model.put("type_veh1", type_veh);
			 model.put("CheckVal1", checkVal);
			model.put("month1", month);
			model.put("ddlYears1", ddlYears);
			model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
			model.put("msg", msg);
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
		 }
	
		return new ModelAndView("Discard_condition_five_years_report_tiles");
	}

	
	
	
	
//	@RequestMapping(value = "/anticipated_discard_tablecount", method = RequestMethod.POST)
//	public @ResponseBody long anticipated_discard_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId
//			,String type_veh,String month,String ddlYears,String mct_main) throws SQLException {
//		 String roleType = sessionUserId.getAttribute("roleType").toString();
//		
//		return dis_con.anticipated_discard_tablecount(Search, orderColunm, orderType, sessionUserId, type_veh, month, ddlYears, mct_main);
//	}
//
//
//	@RequestMapping(value = "/anticipated_discard_table", method = RequestMethod.POST)
//	public @ResponseBody List<Map<String, Object>> anticipated_discard_table(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
//			,String type_veh,String month,String ddlYears,String mct_main) throws SQLException {
//	
//		return dis_con.anticipated_discard_table(startPage,Search, pageLength,orderColunm, orderType,sessionUserId, type_veh, month, ddlYears, mct_main);
//	}
	
	
}
