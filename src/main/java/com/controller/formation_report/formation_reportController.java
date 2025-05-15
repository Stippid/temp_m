package com.controller.formation_report;

import java.sql.SQLException;
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

import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.psg.Report.ReportSearch_3008DAO;
import com.dao.formation_report.Formation_reportsDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class formation_reportController {

	@Autowired
	private Formation_reportsDAO formation_report;

	@RequestMapping(value = "/admin/formation_report", method = RequestMethod.GET)
	public ModelAndView formation_report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("UnitDashboard", roleid); if (val ==
		 * false) { return new ModelAndView("AccessTiles"); } if
		 * (request.getHeader("Referer") == null) { msg = ""; return new
		 * ModelAndView("redirect:bodyParameterNotAllow"); }
		 */
		Mmap.put("msg", msg);

		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("formation_reportTiles");
	}
	
	@RequestMapping(value = "/getArrivalDepartureTable_count", method = RequestMethod.POST)
	public @ResponseBody long getArrivalDepartureTable_count(String Search,String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String msg ;
		Date frm_date = null;
		Date to_date = null;		
		
	 	String from_dt= fromDate;
    	String to_dt = toDate;    	
  

		return formation_report.getArrivalDepartureTable_count(Search,sessionUserId, fromDate,
				fromDate);  
	}

	@RequestMapping(value = "/getArrivalDepartureTable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getArrivalDepartureTable_list(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {

		return formation_report.getArrivalDepartureTable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
				toDate);
	}
                 /////////////////////2--------------	
	@RequestMapping(value = "/getreorg_conv_disdTable_count", method = RequestMethod.POST)
	public @ResponseBody long getreorg_conv_disdTable_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {

		return formation_report.getreorg_conv_disdTable_count(Search, sessionUserId, fromDate,
				fromDate);  
	}
	@RequestMapping(value = "/getreorg_conv_disdTable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getreorg_conv_disdTable_list(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {

		return formation_report.getreorg_conv_disdTable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
				toDate);
	}
	
    /////////////////////3--------------	
	@RequestMapping(value = "/get_revision_amdtTable_count", method = RequestMethod.POST)
	public @ResponseBody long get_revision_amdtTable_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {

		return formation_report.get_revision_amdtTable_count(Search, sessionUserId, fromDate,
				fromDate);  
	}
	@RequestMapping(value = "/get_revision_amdtTable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_revision_amdtTable_list(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {

		return formation_report.get_revision_amdtTable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
				toDate);
	}
/////////////////////4--------------	
	@RequestMapping(value = "/get_tms_new_relTable_count", method = RequestMethod.POST)
	public @ResponseBody long get_tms_new_relTable_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {

		return formation_report.get_tms_new_relTable_count(Search, sessionUserId, fromDate,
				fromDate);  
	}
	@RequestMapping(value = "/get_tms_new_relTable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_tms_new_relTable_list(int startPage, int pageLength,
		String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {

	return formation_report.get_tms_new_relTable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
		toDate);
	}

	/////////////////////5--------------	
	@RequestMapping(value = "/get_veh_meeting_discard_conTable_count", method = RequestMethod.POST)
	public @ResponseBody long get_veh_meeting_discard_conTable_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {
	
	return formation_report.get_veh_meeting_discard_conTable_count(Search, sessionUserId, fromDate,
			fromDate);  
	}
	@RequestMapping(value = "/get_veh_meeting_discard_conTable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_veh_meeting_discard_conTable_list(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {
	
	return formation_report.get_veh_meeting_discard_conTable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
			toDate);
	}
    /////////////////////6--------------	
	@RequestMapping(value = "/get_tms_iut_dtlTable_count", method = RequestMethod.POST)
	public @ResponseBody long get_tms_iut_dtlTable_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {

		return formation_report.get_tms_iut_dtlTable_count(Search, sessionUserId, fromDate,
				fromDate);  
	}
	@RequestMapping(value = "/get_tms_iut_dtlTable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_tms_iut_dtlTable_list(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {

		return formation_report.get_tms_iut_dtlTable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
				toDate);
	}
/////////////////////7--------------	
	@RequestMapping(value = "/get_tms_bkld_veh_dtlTable_count", method = RequestMethod.POST)
	public @ResponseBody long get_tms_bkld_veh_dtlTable_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {

		return formation_report.get_tms_bkld_veh_dtlTable_count(Search, sessionUserId, fromDate,
				fromDate);  
	}
	@RequestMapping(value = "/get_tms_bkld_veh_dtlTable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_tms_bkld_veh_dtlTable_list(int startPage, int pageLength,
		String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {

	return formation_report.get_tms_bkld_veh_dtlTable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
		toDate);
	}

	/////////////////////8--------------	
	@RequestMapping(value = "/get_tms_updation_stateTable_count", method = RequestMethod.POST)
	public @ResponseBody long get_tms_updation_stateTable_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {
	
	return formation_report.get_tms_updation_stateTable_count(Search, sessionUserId, fromDate,
			fromDate);  
	}
	@RequestMapping(value = "/get_tms_updation_stateTable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_tms_updation_stateTable_list(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {
	
	return formation_report.get_tms_updation_stateTable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
			toDate);
	}
    /////////////////////9--------------	
	@RequestMapping(value = "/get_tms_hld_defiTable_count", method = RequestMethod.POST)
	public @ResponseBody long get_tms_hld_defiTable_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {

		return formation_report.get_tms_hld_defiTable_count(Search, sessionUserId, fromDate,
				fromDate);  
	}
	@RequestMapping(value = "/get_tms_hld_defiTable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_tms_hld_defiTable_list(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {

		return formation_report.get_tms_hld_defiTable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
				toDate);
	}
/////////////////////10--------------	
	@RequestMapping(value = "/get_tms_oh_mr_due_stateTable_count", method = RequestMethod.POST)
	public @ResponseBody long get_tms_oh_mr_due_stateTable_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {

		return formation_report.get_tms_oh_mr_due_stateTable_count(Search, sessionUserId, fromDate,
				fromDate);  
	}
	@RequestMapping(value = "/get_tms_oh_mr_due_stateTable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>>get_tms_oh_mr_due_stateTable_list(int startPage, int pageLength,
		String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {

	return formation_report.get_tms_oh_mr_due_stateTable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
		toDate);
	}

	/////////////////////11--------------	
	@RequestMapping(value = "/get_mms_new_reltable_count", method = RequestMethod.POST)
	public @ResponseBody long get_mms_new_reltable_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {
	
	return formation_report.get_mms_new_reltable_count(Search, sessionUserId, fromDate,
			fromDate);  
	}
	@RequestMapping(value = "/get_mms_new_reltable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_mms_new_reltable_list(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {
	
	return formation_report.get_mms_new_reltable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
			toDate);
	}
    /////////////////////12-------------	
	@RequestMapping(value = "/get_cont_store_srvc_conTable_count", method = RequestMethod.POST)
	public @ResponseBody long get_cont_store_srvc_conTable_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {

		return formation_report.get_cont_store_srvc_conTable_count(Search, sessionUserId, fromDate,
				fromDate);  
	}
	@RequestMapping(value = "/get_cont_store_srvc_conTable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_cont_store_srvc_conTable_list(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {

		return formation_report.get_cont_store_srvc_conTable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
				toDate);
	}
/////////////////////13--------------	
	@RequestMapping(value = "/get_mms_iut_dtlTbale_count", method = RequestMethod.POST)
	public @ResponseBody long get_mms_iut_dtlTbale_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {

		return formation_report.get_mms_iut_dtlTbale_count(Search, sessionUserId, fromDate,
				fromDate);  
	}
	@RequestMapping(value = "/get_mms_iut_dtlTbale_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_mms_iut_dtlTbale_list(int startPage, int pageLength,
		String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {

	return formation_report.get_mms_iut_dtlTbale_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
		toDate);
	}

	/////////////////////14-------------	
	@RequestMapping(value = "/get_mms_bkld_dtlTbale_count", method = RequestMethod.POST)
	public @ResponseBody long get_mms_bkld_dtlTbale_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {
	
	return formation_report.get_mms_bkld_dtlTbale_count(Search, sessionUserId, fromDate,
			fromDate);  
	}
	@RequestMapping(value = "/get_mms_bkld_dtlTbale_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_mms_bkld_dtlTbale_list(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {
	
	return formation_report.get_mms_bkld_dtlTbale_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
			toDate);
	}
    /////////////////////15--------------	
	@RequestMapping(value = "/get_mms_updation_stateTable_count", method = RequestMethod.POST)
	public @ResponseBody long get_mms_updation_stateTable_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {

		return formation_report.get_mms_updation_stateTable_count(Search, sessionUserId, fromDate,
				fromDate);  
	}
	@RequestMapping(value = "/get_mms_updation_stateTable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_mms_updation_stateTable_list(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {

		return formation_report.get_mms_updation_stateTable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
				toDate);
	}
/////////////////////16--------------	
	@RequestMapping(value = "/get_mms_defi_stateTable_count", method = RequestMethod.POST)
	public @ResponseBody long get_mms_defi_stateTable_count(
			String Search, String fromDate,String toDate ,HttpSession sessionUserId) throws SQLException, ParseException {

		return formation_report.get_mms_defi_stateTable_count(Search, sessionUserId, fromDate,
				fromDate);  
	}
	@RequestMapping(value = "/get_mms_defi_stateTable_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_mms_defi_stateTable_list(int startPage, int pageLength,
		String Search, String orderColunm, String orderType,String fromDate,String toDate , HttpSession sessionUserId) throws SQLException {

	return formation_report.get_mms_defi_stateTable_list(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,fromDate,
		toDate);
	}


}
