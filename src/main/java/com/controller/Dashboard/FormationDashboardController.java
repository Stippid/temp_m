package com.controller.Dashboard;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.dao.Dashboard.CueDashboardDAO;
import com.dao.Dashboard.FormationDashboardDAO;
import com.dao.Dashboard.MNHDashboardFinalDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.healthmarketscience.jackcess.expr.ParseException;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class FormationDashboardController {
	@Autowired
	CueDashboardDAO adminDash;

	@Autowired
	FormationDashboardDAO formDash;
	@Autowired
	private RoleBaseMenuDAO roledao;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	MNHDashboardFinalDAO MnhFinal;
	
	DateWithTimeStampController d = new DateWithTimeStampController();

	/*@RequestMapping(value = "/admin/formationDashboard", method = RequestMethod.GET)
	public ModelAndView formationDashboard(ModelMap Mmap, HttpSession session) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.CheckDashboard("formationDashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
	
	
		//String form_code = "";
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				form_code=String.valueOf(roleFormationNo.charAt(0));
			}
			if(roleSubAccess.equals("Corps")) {
				form_code=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
			}
			if(roleSubAccess.equals("Division")) {
				form_code=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
			}
			if(roleSubAccess.equals("Brigade")) {  	
				form_code=roleFormationNo;
			}
			
			Mmap.put("roleSubAccess111", roleSubAccess);
			List<Map<String, Object>> getVehicleListInFRM =  getVehicleListInFRM(session);
			Mmap.put("getVehicleListInFRM", getVehicleListInFRM);
			Mmap.put("getWPNHoldingStateData", getHoldingStateData(session, "WE"));
			Mmap.put("getEQPTHoldingStateData", getHoldingStateData(session, "WET"));
			
			// Logic for dashboard
			Mmap.put("maxDate", d.getFromDateByMonthCount(0));
			Mmap.put("futureDate", d.getFromDateByMonthCount(6));
			
			String  bvehProgressCount = String.format("%.2f" ,((Float.parseFloat(getVehicleListInFRM.get(0).get("b_up").toString()) * 100) / (Float.parseFloat(getVehicleListInFRM.get(0).get("b_up").toString()) + Float.parseFloat(getVehicleListInFRM.get(0).get("b_yet").toString()))));
			String  avehProgressCount = String.format("%.2f" ,((Float.parseFloat(getVehicleListInFRM.get(0).get("a_up").toString()) * 100) / (Float.parseFloat(getVehicleListInFRM.get(0).get("a_up").toString()) + Float.parseFloat(getVehicleListInFRM.get(0).get("a_yet").toString()))));
			String  cvehProgressCount = String.format("%.2f" ,((Float.parseFloat(getVehicleListInFRM.get(0).get("c_up").toString()) * 100) / (Float.parseFloat(getVehicleListInFRM.get(0).get("c_up").toString()) + Float.parseFloat(getVehicleListInFRM.get(0).get("c_yet").toString()))));
			Mmap.put("bvehProgressCount", bvehProgressCount);
			Mmap.put("avehProgressCount", avehProgressCount);
			Mmap.put("cvehProgressCount", cvehProgressCount );
			
			Mmap.put("roleSusNo", session.getAttribute("roleSusNo").toString() );
			Mmap.put("compassetCount", compassetCount(formDash.getComputerData( session )) );
			Mmap.put("peripheralCount", compassetCount(formDash.getperipheralData( session )) );
			
			
			
			
			//Mnh Chart
			//Mmap.put("Hospital_Admissions_Col_Above", formDash.Hospital_Admissions_Col_Above(form_code));
			//Mmap.put("AMC_ADC_MNS_Admissions", formDash.AMC_ADC_MNS_Admissions(form_code));
			//Mmap.put("Unusual_Occurrence", formDash.Unusual_Occurrence(form_code));
			
		}else if(roleAccess.equals("HeadQuarter")){
			Mmap.put("roleAccess", roleAccess);
			List<Map<String, Object>> getVehicleListInFRM =  getVehicleListInFRM(session);
			Mmap.put("getVehicleListInFRM", getVehicleListInFRM);
			Mmap.put("getWPNHoldingStateData", getHoldingStateData(session, "WE"));
			Mmap.put("getEQPTHoldingStateData", getHoldingStateData(session, "WET"));
			
			// Logic for dashboard
			Mmap.put("maxDate", d.getFromDateByMonthCount(0));
			Mmap.put("futureDate", d.getFromDateByMonthCount(6));
			
			String  bvehProgressCount = String.format("%.2f" ,((Float.parseFloat(getVehicleListInFRM.get(0).get("b_up").toString()) * 100) / (Float.parseFloat(getVehicleListInFRM.get(0).get("b_up").toString()) + Float.parseFloat(getVehicleListInFRM.get(0).get("b_yet").toString()))));
			String  avehProgressCount = String.format("%.2f" ,((Float.parseFloat(getVehicleListInFRM.get(0).get("a_up").toString()) * 100) / (Float.parseFloat(getVehicleListInFRM.get(0).get("a_up").toString()) + Float.parseFloat(getVehicleListInFRM.get(0).get("a_yet").toString()))));
			String  cvehProgressCount = String.format("%.2f" ,((Float.parseFloat(getVehicleListInFRM.get(0).get("c_up").toString()) * 100) / (Float.parseFloat(getVehicleListInFRM.get(0).get("c_up").toString()) + Float.parseFloat(getVehicleListInFRM.get(0).get("c_yet").toString()))));
			Mmap.put("bvehProgressCount", bvehProgressCount);
			Mmap.put("avehProgressCount", avehProgressCount);
			Mmap.put("cvehProgressCount", cvehProgressCount );
			
			
			Mmap.put("roleSusNo", session.getAttribute("roleSusNo").toString() );
			Mmap.put("compassetCount", compassetCount(formDash.getComputerData( session )) );
			Mmap.put("peripheralCount", compassetCount(formDash.getperipheralData( session )) );
			
			
			
			//Mnh Chart
			//Mmap.put("Hospital_Admissions_Col_Above", formDash.Hospital_Admissions_Col_Above(form_code));
			//Mmap.put("AMC_ADC_MNS_Admissions", formDash.AMC_ADC_MNS_Admissions(form_code));
			//Mmap.put("Unusual_Occurrence", formDash.Unusual_Occurrence(form_code));
		} else {
			return new ModelAndView("AccessTiles");
		}
		return new ModelAndView("formationDashboardTiles");
	}*/

	private long compassetCount(List<Map<String, Object>> computerData) {
	    long total = 0;
	    for (int i = 0; i < computerData.size(); i++) {
	        total += (long) computerData.get(i).get("count");
	    }
	    return total;
	}
	
	
	
	
	@RequestMapping(value = "/computerReport", method = RequestMethod.POST)
	public ModelAndView  computerReport(ModelMap Mmap ,HttpSession sessionA) {
		
		List<Map<String, Object>> list = formDash.getComputerData(sessionA);
		Mmap.put("list",list );
	return 	new ModelAndView("computerassetsTiles");
	}
	@RequestMapping(value = "/peripheralReport", method = RequestMethod.POST)
	public ModelAndView  peripheralReport(ModelMap Mmap ,HttpSession sessionA) {
		List<Map<String, Object>> list = formDash.getperipheralData(sessionA);
		Mmap.put("list",list );
	return 	new ModelAndView("peripheralTiles");
	}
	
	@RequestMapping(value = "/computerReportDatacount", method = RequestMethod.POST)
	public @ResponseBody long computerReportDatacount(String Search, String orderColunm, String orderType,
			String type_mtrls, HttpSession sessionUserId) throws SQLException {
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
		return formDash.getUnitReportWpnEqptAuthcount(Search, orderColunm, orderType, sessionUserId, type_mtrls, roleFormationNo);
	}
	
	@RequestMapping(value = "/computerReportData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> computerReportData(int startPage, String Search, int pageLength,
			String orderColunm, String orderType, String type_mtrls, HttpSession sessionUserId) throws SQLException {
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
		return formDash.getUnitReportWpnEqptAuth(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				type_mtrls, roleFormationNo);
	}
	@RequestMapping(value = "/peripheralReportDatacount", method = RequestMethod.POST)
	public @ResponseBody long peripheralReportDatacount(String Search, String orderColunm, String orderType,
			String type_mtrls, HttpSession sessionUserId) throws SQLException {
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
		return formDash.getperipheralcount(Search, orderColunm, orderType, sessionUserId, type_mtrls, roleFormationNo);
	}
	
	@RequestMapping(value = "/peripheralReportData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> peripheralReportData(int startPage, String Search, int pageLength,
			String orderColunm, String orderType, String type_mtrls, HttpSession sessionUserId) throws SQLException {
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
		return formDash.getperipheral(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				type_mtrls, roleFormationNo);
	}
	
	
	

	@RequestMapping(value = "/getCommWiseUnitMovFormation", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCommWiseUnitMovFormation(HttpSession sessionA,
			@RequestParam Date fromDate, @RequestParam Date toDate) {
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		List<Map<String, Object>> list = formDash.getCommWiseUnitMovFormation(roleFormationNo, fromDate, toDate,sessionA);
		return list;
	}

	@RequestMapping(value = "/getUnitMovReport", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnitMovReport( int startPage,String pageLength,String Search,String orderColunm,String orderType
			, String cont_comd, String cont_corps, String cont_div, String cont_bde,String to_cont_comd,
			String to_cont_corps, String to_cont_div, String to_cont_bde, String fromDate, String toDate, HttpSession sessionA)throws ParseException {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = ""; // sessionA.getAttribute("roleFormationNo").toString();
		List<Map<String, Object>> list;
		if (roleAccess.equals("Formation")) {
			roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
			if (roleSubAccess.equals("Command")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
			}
			if (roleSubAccess.equals("Corps")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
			}
			if (roleSubAccess.equals("Division")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
			}
			if (roleSubAccess.equals("Brigade")) {
				roleFormationNo = roleFormationNo.toString();
			}
		}else if(roleAccess.equals("HeadQuarter")){
			roleFormationNo ="";
		}else if(roleAccess.equals("Line_dte")){
			roleFormationNo ="";
		}else {
			list = formDash.getUnitMovReport("",startPage,pageLength,Search,orderColunm,orderType,sessionA, cont_comd, cont_corps, cont_div, cont_bde,to_cont_comd, to_cont_corps, to_cont_div, to_cont_bde,fromDate,toDate);
		}
	
		list = formDash.getUnitMovReport(roleFormationNo, startPage,pageLength,Search,orderColunm,orderType,sessionA, cont_comd, cont_corps, cont_div, cont_bde,to_cont_comd, to_cont_corps, to_cont_div, to_cont_bde,fromDate,toDate);
		return list;
	}
	@RequestMapping(value = "/getUnitMovReportcount", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>>getUnitMovReportcount( String Search
			, String cont_comd, String cont_corps, String cont_div, String cont_bde,String to_cont_comd,
			String to_cont_corps, String to_cont_div, String to_cont_bde, String fromDate, String toDate, HttpSession sessionA) throws SQLException {
		 String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		 
		return formDash.getUnitMovReportcount( roleFormationNo, Search,sessionA, cont_comd, cont_corps, cont_div, cont_bde,to_cont_comd, to_cont_corps, to_cont_div, to_cont_bde,fromDate,toDate);
	}
	// @RequestMapping(value = "/getVehicleListInFRM")
	public @ResponseBody List<Map<String, Object>> getVehicleListInFRM(HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String whr = "";
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				whr = " and substr(a.form_code_control, 1, 1) = '" + String.valueOf(roleFormationNo.charAt(0)) + "'";
			}
			if (roleSubAccess.equals("Corps")) {
				whr = " and substr(a.form_code_control,1,3) = '" + String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + "'";
			}
			if (roleSubAccess.equals("Division")) {
				whr = " and substr(a.form_code_control,1,6) = '" + String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))
						+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4))
						+ String.valueOf(roleFormationNo.charAt(5)) + "'";
			}
			if (roleSubAccess.equals("Brigade")) {
				whr = " and a.form_code_control = '" + roleFormationNo + "'";
			}
		}else if(roleAccess.equals("HeadQuarter")){
			whr = "";
		}else {
			return null;
		}
		
		String qry = "SELECT \r\n" + "	A.UPDATED as a_up,\r\n" + "	AA.YET_TO_UPDATE as a_yet ,\r\n"
				+ "	B.UPDATED as b_up,\r\n" + "	BB.YET_TO_UPDATE as b_yet,\r\n" + "	C.UPDATED as c_up,\r\n"
				+ "	CC.YET_TO_UPDATE as c_yet\r\n" + "	FROM  \r\n"
				+ "	(select  count(DISTINCT c.sus_no) as UPDATED from tb_tms_census_retrn_main c \r\n"
				+ "		JOIN tb_tms_census_retrn cr ON c.sus_no::text = cr.sus_no::text \r\n"
				+ "		join tb_miso_orbat_unt_dtl a on a.sus_no = c.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) = ltrim(to_char(c.approve_date, 'mm-yyyy'::text), '0'::text)) as A, \r\n"
				+ " \r\n"
				+ "	(select  count(DISTINCT c.sus_no) as YET_TO_UPDATE from tb_tms_census_retrn_main c \r\n"
				+ "		JOIN tb_tms_census_retrn cr ON c.sus_no::text = cr.sus_no::text \r\n"
				+ "		join tb_miso_orbat_unt_dtl a on a.sus_no = c.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) != ltrim(to_char(c.approve_date, 'mm-yyyy'::text), '0'::text)) as AA,\r\n"
				+ " \r\n"
				+ "	(select  count(DISTINCT b.sus_no) as UPDATED from tb_tms_mvcr_parta_main b  \r\n"
				+ "		JOIN tb_tms_mvcr_parta_dtl c ON b.sus_no::text = c.sus_no::text \r\n"
				+ "		join tb_miso_orbat_unt_dtl a on a.sus_no = b.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) = ltrim(to_char(b.approve_date, 'mm-yyyy'::text), '0'::text)) as B,\r\n"
				+ " \r\n"
				+ "	(select  count(DISTINCT b.sus_no) as YET_TO_UPDATE from tb_tms_mvcr_parta_main b  \r\n"
				+ "		JOIN tb_tms_mvcr_parta_dtl c ON b.sus_no::text = c.sus_no::text \r\n"
				+ "		join tb_miso_orbat_unt_dtl a on a.sus_no = b.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) != ltrim(to_char(b.approve_date, 'mm-yyyy'::text), '0'::text)) as BB,\r\n"
				+ "  \r\n"
				+ "	(select  count(DISTINCT e.sus_no) as UPDATED from tb_tms_emar_report_main e \r\n"
				+ "		JOIN tb_tms_emar_report cr ON e.sus_no::text = cr.sus_no::text \r\n"
				+ "		join tb_miso_orbat_unt_dtl a on a.sus_no = e.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) = ltrim(to_char(e.approve_date::date::timestamp with time zone, 'mm-yyyy'::text), '0'::text)) as C,\r\n"
				+ "  \r\n"
				+ "	(select  count(DISTINCT e.sus_no) as YET_TO_UPDATE from tb_tms_emar_report_main e \r\n"
				+ "		JOIN tb_tms_emar_report cr ON e.sus_no::text = cr.sus_no::text \r\n"
				+ "		join tb_miso_orbat_unt_dtl a on a.sus_no = e.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) != ltrim(to_char(e.approve_date::date::timestamp with time zone, 'mm-yyyy'::text), '0'::text)) as CC \r\n ";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	/*@RequestMapping(value = "/getTptSummaryData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getTptSummaryData(HttpSession sessionA, @RequestParam int type ) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		List<Map<String, Object>> list = null;
		list = formDash.getTptSummaryData(roleAccess, roleSubAccess, roleFormationNo, type);
		return list;
	}*/
	
	
	@RequestMapping(value = "/getTptSummaryInPRFList_nodal_dte", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getTptSummaryInPRFList_nodal_dte(HttpSession sessionA,
			@RequestParam int type,@RequestParam String line_dte_sus) {
		List<Map<String, Object>> list = formDash.getTptSummaryInPRFList_nodal_dte(type,sessionA,line_dte_sus);
		return list;
	}
	

	@RequestMapping(value = "/getTptSummaryInPRFList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getTptSummaryInPRFList(HttpSession sessionA,
			@RequestParam int type) {
		List<Map<String, Object>> list = formDash.getTptSummaryInPRFList(type,sessionA);
		return list;
	}

	@RequestMapping(value = "/getTptSummaryInPrfWithTypeVehData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getTptSummaryInPrfWithTypeVehData(HttpSession sessionA,
			@RequestParam int type, String prf_code) {
		List<Map<String, Object>> list = null;
		list = formDash.getTptSummaryInPrfWithTypeVehData(sessionA,type,prf_code);
		return list;
	}

	@RequestMapping(value = "/getVehWiseAvgKMSData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getVehWiseAvgKMSData(HttpSession sessionA, @RequestParam int type,String form_code, String prf_code){
		
		List<Map<String, Object>> list = formDash.getVehWiseAvgKMSData(type, form_code, prf_code,sessionA);
		return list;
	}

	@RequestMapping(value = "/getPRFWiseTptClassData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getPRFWiseTptClassData(HttpSession sessionA, @RequestParam int type,
			@RequestParam String prf) {
		List<Map<String, Object>> list = null;
		list = formDash.getPRFWiseTptClassData(sessionA, type, prf);
		return list;
	}

	@RequestMapping(value = "/getUEManpowerData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUEManpowerData(HttpSession sessionA) {
		
		List<Map<String, Object>> list = formDash.getUEManpowerData(sessionA);
		return list;
	}

	public String getHoldingStateData(HttpSession sessionA, String type) {
		
		List<Map<String, Object>> list = formDash.getHoldingStateData(sessionA, type);
		String list1 = "";
		for (int i = 0; i < list.size(); i++) {
			list1 += ",{'colname' : '" + list.get(i).get("prf_grp_short") + "' ,'colcode' : "
					+ list.get(i).get("prf_code") + " , 'ue' : " + list.get(i).get("ue") + ", 'uh' : "
					+ list.get(i).get("uh") + ", 'ss' : " + list.get(i).get("ss") + ", 'ls' : "
					+ list.get(i).get("ls") + ", 'ac' : " + list.get(i).get("ac") + ", 'res' : " + list.get(i).get("res") + "}";
		}
		if (list1.length() > 0) {
			list1 = "[" + list1.substring(1, list1.length());
			list1 += "]";
		}else {
			list1 = "[]";
		}
		return list1;
	}

	public List<Map<String, Object>> getMvcrUnitInFRMList() {
		String qry = "select count(*) as total from tms_bveh_mvcr_update_state_view";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getPRFInFRMList() {
		String qry = "SELECT COUNT(DISTINCT a.prf_group) as total  FROM tb_tms_mct_master a,tb_tms_banum_dirctry b,tb_tms_mvcr_parta_dtl c ,tb_miso_orbat_unt_dtl d where a.mct=b.mct and b.ba_no=c.ba_no and c.sus_no=d.sus_no and d.status_sus_no='Active'";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getMCTInFRMList() {
		String qry = "select COUNT(DISTINCT SUBSTR(CAST(a.mct AS varchar),1,4)) as total from tb_tms_banum_dirctry a, tb_tms_mvcr_parta_dtl b ,tb_miso_orbat_unt_dtl c where a.ba_no = b.ba_no and b.sus_no = c.sus_no and c.status_sus_no='Active'";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getMakeInFRMList() {
		String qry = "select COUNT(DISTINCT SUBSTR(CAST(a.mct AS varchar),1,7)) as total from tb_tms_banum_dirctry a, tb_tms_mvcr_parta_dtl b ,tb_miso_orbat_unt_dtl c where a.ba_no = b.ba_no and b.sus_no = c.sus_no and c.status_sus_no='Active'";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getModelInFRMList() {
		String qry = "select count(distinct a.mct) as total from tb_tms_banum_dirctry a, tb_tms_mvcr_parta_dtl b ,tb_miso_orbat_unt_dtl c where a.ba_no = b.ba_no and b.sus_no = c.sus_no and c.status_sus_no='Active'";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getDepotStokeInFRMList() {
		String qry = "select count(distinct ba_no) as total from tb_tms_drr_dir_dtl where typ_of_retrn <> type_of_issue";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getBVehiclesInFRMList() {
		String qry = "select count(*) as total from tms_bveh_mvcr_update_state_view";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getLoanStoreInFRMList() {
		String qry = "select sum(loan::int) as total from tms_bveh_unit_wise_issue_type_uh";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getSectorStoreInFRMList() {
		String qry = "select sum(sector::int) as total from tms_bveh_unit_wise_issue_type_uh";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getACSFPInFRMList() {
		String qry = "select sum(acsfp::int) as total from tms_bveh_unit_wise_issue_type_uh";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	@RequestMapping(value = "/admin/getDownloadDocFMNDashboard", method = RequestMethod.POST)
	public void getDownloadImageOrbat(@ModelAttribute("filename") int filename, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		File file = null;

		switch (filename) {
		case 1:
			file = new File("/srv" + File.separator + "Upload_DocumentsFRM" + File.separator
					+ "auth_esta_str_regarmy_half.pdf");
			break;
		case 2:
			file = new File("/srv" + File.separator + "Upload_DocumentsFRM" + File.separator
					+ "stati_digest_manpwr_restr_half.pdf");
			break;
		case 3:
			file = new File("/srv" + File.separator + "Upload_DocumentsFRM" + File.separator
					+ "stati_digest_a_b_veh_conf_four.pdf");
			break;
		case 4:
			file = new File("/srv" + File.separator + "Upload_DocumentsFRM" + File.separator
					+ "stati_digest_arm_equi_conf_four.pdf");
			break;
		case 5:
			file = new File("/srv" + File.separator + "Upload_DocumentsFRM" + File.separator
					+ "stati_digest_fol_ration_losses_etc_restr_half.pdf");
			break;
		case 6:
			file = new File("/srv" + File.separator + "Upload_DocumentsFRM" + File.separator
					+ "stre_indian_army_conf_annual.pdf");
			break;
		case 7:
			file = new File("/srv" + File.separator + "Upload_DocumentsFRM" + File.separator
					+ "mt_accid_army_bveh_restr_annual.pdf");
			break;
		case 8:
			file = new File("/srv" + File.separator + "Upload_DocumentsFRM" + File.separator
					+ "studrpt_court_inquiry_restr_fiveyear.pdf");
			break;
		}

		try {
			if (!file.exists()) {
				String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_doc.pdf";
				file = new File(fullPath);
			}
		} catch (Exception exception) {
			
		}

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
		response.setContentLength((int) file.length());
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (FileNotFoundException e) {
			
		}
	}

	@RequestMapping(value = "/admin/formationDashboard", method = RequestMethod.GET)
	public ModelAndView formationDashboard(ModelMap Mmap, HttpSession session) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.CheckDashboard("formationDashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
	
	
		//String form_code = "";
		if(roleAccess.equals("Formation")) {
			/*if(roleSubAccess.equals("Command")) {
				form_code=String.valueOf(roleFormationNo.charAt(0));
			}
			if(roleSubAccess.equals("Corps")) {
				form_code=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
			}
			if(roleSubAccess.equals("Division")) {
				form_code=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
			}
			if(roleSubAccess.equals("Brigade")) {  	
				form_code=roleFormationNo;
			}*/
			
			Mmap.put("roleSubAccess111", roleSubAccess);
			List<Map<String, Object>> getVehicleListInFRM =  getVehicleListInFRM(session);
			Mmap.put("getVehicleListInFRM", getVehicleListInFRM);
			Mmap.put("getWPNHoldingStateData", getHoldingStateData(session, "WE"));
			Mmap.put("getEQPTHoldingStateData", getHoldingStateData(session, "WET"));
			
			// Logic for dashboard
			Mmap.put("maxDate", d.getFromDateByMonthCount(0));
			Mmap.put("futureDate", d.getFromDateByMonthCount(6));
			
			String  bvehProgressCount = String.format("%.2f" ,((Float.parseFloat(getVehicleListInFRM.get(0).get("b_up").toString()) * 100) / (Float.parseFloat(getVehicleListInFRM.get(0).get("b_up").toString()) + Float.parseFloat(getVehicleListInFRM.get(0).get("b_yet").toString()))));
			String  avehProgressCount = String.format("%.2f" ,((Float.parseFloat(getVehicleListInFRM.get(0).get("a_up").toString()) * 100) / (Float.parseFloat(getVehicleListInFRM.get(0).get("a_up").toString()) + Float.parseFloat(getVehicleListInFRM.get(0).get("a_yet").toString()))));
			String  cvehProgressCount = String.format("%.2f" ,((Float.parseFloat(getVehicleListInFRM.get(0).get("c_up").toString()) * 100) / (Float.parseFloat(getVehicleListInFRM.get(0).get("c_up").toString()) + Float.parseFloat(getVehicleListInFRM.get(0).get("c_yet").toString()))));
			Mmap.put("bvehProgressCount", bvehProgressCount);
			Mmap.put("avehProgressCount", avehProgressCount);
			Mmap.put("cvehProgressCount", cvehProgressCount );
			
			Mmap.put("roleSusNo", session.getAttribute("roleSusNo").toString() );
			Mmap.put("compassetCount", compassetCount(formDash.getComputerData( session )) );
			Mmap.put("peripheralCount", compassetCount(formDash.getperipheralData( session )) );
		Mmap.put("getunitwithmcrobsn", formDash.getunitwithmcrobsn(roleFormationNo));
		Mmap.put("getunititobsr", formDash.getunititobsn(roleFormationNo));
			
			
			//Mnh Chart
			//Mmap.put("Hospital_Admissions_Col_Above", formDash.Hospital_Admissions_Col_Above(form_code));
			//Mmap.put("AMC_ADC_MNS_Admissions", formDash.AMC_ADC_MNS_Admissions(form_code));
			//Mmap.put("Unusual_Occurrence", formDash.Unusual_Occurrence(form_code));
			
		}else if(roleAccess.equals("HeadQuarter")){
			Mmap.put("roleAccess", roleAccess);
			List<Map<String, Object>> getVehicleListInFRM =  getVehicleListInFRM(session);
			Mmap.put("getVehicleListInFRM", getVehicleListInFRM);
			Mmap.put("getWPNHoldingStateData", getHoldingStateData(session, "WE"));
			Mmap.put("getEQPTHoldingStateData", getHoldingStateData(session, "WET"));
			
			// Logic for dashboard
			Mmap.put("maxDate", d.getFromDateByMonthCount(0));
			Mmap.put("futureDate", d.getFromDateByMonthCount(6));
			
			String  bvehProgressCount = String.format("%.2f" ,((Float.parseFloat(getVehicleListInFRM.get(0).get("b_up").toString()) * 100) / (Float.parseFloat(getVehicleListInFRM.get(0).get("b_up").toString()) + Float.parseFloat(getVehicleListInFRM.get(0).get("b_yet").toString()))));
			String  avehProgressCount = String.format("%.2f" ,((Float.parseFloat(getVehicleListInFRM.get(0).get("a_up").toString()) * 100) / (Float.parseFloat(getVehicleListInFRM.get(0).get("a_up").toString()) + Float.parseFloat(getVehicleListInFRM.get(0).get("a_yet").toString()))));
			String  cvehProgressCount = String.format("%.2f" ,((Float.parseFloat(getVehicleListInFRM.get(0).get("c_up").toString()) * 100) / (Float.parseFloat(getVehicleListInFRM.get(0).get("c_up").toString()) + Float.parseFloat(getVehicleListInFRM.get(0).get("c_yet").toString()))));
			Mmap.put("bvehProgressCount", bvehProgressCount);
			Mmap.put("avehProgressCount", avehProgressCount);
			Mmap.put("cvehProgressCount", cvehProgressCount );
			
			
			Mmap.put("roleSusNo", session.getAttribute("roleSusNo").toString() );
			Mmap.put("compassetCount", compassetCount(formDash.getComputerData( session )) );
			Mmap.put("peripheralCount", compassetCount(formDash.getperipheralData( session )) );
			Mmap.put("getunitwithmcrobsn", formDash.getunitwithmcrobsn(roleFormationNo));
			Mmap.put("getunititobsr", formDash.getunititobsn(roleFormationNo));
			
			//Mnh Chart
			//Mmap.put("Hospital_Admissions_Col_Above", formDash.Hospital_Admissions_Col_Above(form_code));
			//Mmap.put("AMC_ADC_MNS_Admissions", formDash.AMC_ADC_MNS_Admissions(form_code));
			//Mmap.put("Unusual_Occurrence", formDash.Unusual_Occurrence(form_code));
		} else {
			return new ModelAndView("AccessTiles");
		}
		return new ModelAndView("formationDashboardTiles");
	}

	@RequestMapping(value = "/samaDashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
		Mmap.put("msg", msg);
		return new ModelAndView("samaDashboardTiles");
	}



}