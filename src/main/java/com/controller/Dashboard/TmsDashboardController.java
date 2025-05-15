package com.controller.Dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.ExportCSVFileController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.Dashboard.TmsDashboardDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.View_TMS_BVeh_Command_Wise_TrnsportUE_UH;
import com.models.View_TMS_Bveh_Mvcr_Update_Status_Details;
import com.models.View_TMS_Cveh_Mvcr_Update_Status_Details;
import com.models.View_Tms_BVEH_Unit_Wise_Issue_Type_UH;
import com.models.Prf_Wise_TrnsportUE_UH;
import com.models.View_TMS_Aveh_Mvcr_Update_Status_Details;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class TmsDashboardController {
	@Autowired
	TmsDashboardDAO tmsDash;
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/tmsDashboard", method = RequestMethod.GET)
	public ModelAndView tmsDashboard(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.CheckDashboard("tmsDashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("gettypeOfVehicleList", tmsDash.gettypeOfVehicleList());
		//Mmap.put("getmvcrunitList", tmsDash.getmvcrunitList());
		Mmap.put("getNoOfPrfList", tmsDash.getNoOfPrfList());
		Mmap.put("getMCTdesList", tmsDash.getMCTdesList());
		Mmap.put("getMAKEList", tmsDash.getMAKEList());
		Mmap.put("getMODELList", tmsDash.getMODELList());

		// Mmap.put("getAvgKMs", tmsDash.getAvgKMs());
		// Mmap.put("getAvgYears", tmsDash.getAvgYears());

		long unitlist = tmsDash.getUNITList();
		Mmap.put("getUNITList", unitlist);
		long depotlist = tmsDash.getDEPOTList();
		Mmap.put("getDEPOTList", depotlist);
		long total = unitlist + depotlist;
		Mmap.put("totalTotal", total);

		// Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("getCommandWiseUE_UH_B_VEH_List", tmsDash.getCommandWiseUE_UH_B_VEH_List());
		Mmap.put("getCommandWiseUE_UH_A_VEH_List", tmsDash.getCommandWiseUE_UH_A_VEH_List());
		Mmap.put("getCommandWiseUE_UH_C_VEH_List", tmsDash.getCommandWiseUE_UH_C_VEH_List());

		Mmap.put("loanStoreTotal", tmsDash.loanStoreTotal());
		Mmap.put("sectoreStoreTotal", tmsDash.sectoreStoreTotal());
		Mmap.put("acsfpTotal", tmsDash.acsfpTotal());
		Mmap.put("overUeTotal", tmsDash.overUeTotal());

		return new ModelAndView("tmsDashboardTiles");
	}

	@RequestMapping(value = "/admin/Formationwiseueuh", method = RequestMethod.GET)
	public ModelAndView Formationwiseueuh(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request)
	
	{
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("FormationwiseueuhTiles");
	}

	@RequestMapping(value = "/admin/getformationwiseueuhrpt", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<View_TMS_BVeh_Command_Wise_TrnsportUE_UH> getformationwiseueuhrpt(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		DataSet<View_TMS_BVeh_Command_Wise_TrnsportUE_UH> dataSet = tmsDash.DatatablesCriteriasFormationWiseueuh(criterias);
		return DatatablesResponse.build(dataSet, criterias);
	}

	@RequestMapping(value = "/admin/Prfwiseueuh", method = RequestMethod.GET)
	public ModelAndView Prfwiseueuh(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("PrfwiseueuhwiseueuhTiles");
	}

	@RequestMapping(value = "/admin/getprfwiseueuhrpt", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<Prf_Wise_TrnsportUE_UH> getprfwiseueuhrpt(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		DataSet<Prf_Wise_TrnsportUE_UH> dataSet = tmsDash.DatatablesCriteriasPrfWiseueuh(criterias);
		return DatatablesResponse.build(dataSet, criterias);
	}

	@RequestMapping(value = "/admin/MvcrUpdateReport", method = RequestMethod.GET)
	public ModelAndView MvcrUpdateReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("MvcrUpdateReportTiles");
	}

	@RequestMapping(value = "/admin/getMvcrDetaisrpt", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<Map<String, Object>> getMvcrDetaisrpt(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String whr = "";

		if (roleAccess.equals("Formation")){
			if (roleSubAccess.equals("Command")){
				whr = " where substr(a.form_code_control,1,1) = '" + String.valueOf(roleFormationNo.charAt(0)) + "'";
			}
			if (roleSubAccess.equals("Corps")){
				whr = " where substr(a.form_code_control,1,3) = '" + String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + "'";
			}
			if (roleSubAccess.equals("Division")){
				whr = " where substr(a.form_code_control,1,6) = '" + String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))
						+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4))
						+ String.valueOf(roleFormationNo.charAt(5)) + "'";
			}
			if (roleSubAccess.equals("Brigade")){
				whr = " where a.form_code_control = '" + roleFormationNo + "'";
			}
		}
		if(roleAccess.equals("Line_dte")){
			whr = " inner join tb_miso_orbat_unt_dtl u on a.sus_no = u.sus_no and upper(u.status_sus_no) = 'ACTIVE' and u.arm_code in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus= '"+roleSusNo+"' ) where a.sus_no is not null ";
		}
		DataSet<Map<String, Object>> dataSet = tmsDash.DatatablesCriteriasMvcrstatus(criterias,whr);
		return DatatablesResponse.build(dataSet, criterias);
	}

	@RequestMapping(value = "/admin/AMvcrUpdateReport", method = RequestMethod.GET)
	public ModelAndView AMvcrUpdateReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("AMvcrUpdateReportTiles");
	}

	@RequestMapping(value = "/admin/getAMvcrDetaisrpt", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<View_TMS_Aveh_Mvcr_Update_Status_Details> getAMvcrDetaisrpt(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String whr = "";

		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				whr = " where substr(form_code_control,1,1) = '" + String.valueOf(roleFormationNo.charAt(0)) + "'";
			}
			if (roleSubAccess.equals("Corps")) {
				whr = " where substr(form_code_control,1,3) = '" + String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + "'";
			}
			if (roleSubAccess.equals("Division")) {
				whr = " where substr(form_code_control,1,6) = '" + String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))
						+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4))
						+ String.valueOf(roleFormationNo.charAt(5)) + "'";
			}
			if (roleSubAccess.equals("Brigade")) {
				whr = " where form_code_control = '" + roleFormationNo + "'";
			}
		}
		if(roleAccess.equals("Line_dte")){
			whr = " , Miso_Orbat_Unt_Dtl u where d.sus_no = u.sus_no and upper(u.status_sus_no) = 'ACTIVE' and u.arm_code in (select arm_code from Tb_Miso_Orbat_Line_Dte where line_dte_sus= '"+roleSusNo+"' ) ";
		}
		DataSet<View_TMS_Aveh_Mvcr_Update_Status_Details> dataSet = tmsDash.DatatablesCriteriasAMvcrstatus(criterias,whr);
		return DatatablesResponse.build(dataSet, criterias);
	}

	@RequestMapping(value = "/admin/CMvcrUpdateReport", method = RequestMethod.GET)
	public ModelAndView CMvcrUpdateReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("CMvcrUpdateReportTiles");
	}

	@RequestMapping(value = "/admin/getCMvcrDetaisrpt", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<View_TMS_Cveh_Mvcr_Update_Status_Details> getCMvcrDetaisrpt(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		String whr = "";

		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				whr = " where substr(form_code_control,1,1) = '" + String.valueOf(roleFormationNo.charAt(0)) + "'";
			}
			if (roleSubAccess.equals("Corps")) {
				whr = " where substr(form_code_control,1,3) = '" + String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + "'";
			}
			if (roleSubAccess.equals("Division")) {
				whr = " where substr(form_code_control,1,6) = '" + String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))
						+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4))
						+ String.valueOf(roleFormationNo.charAt(5)) + "'";
			}
			if (roleSubAccess.equals("Brigade")) {
				whr = " where form_code_control = '" + roleFormationNo + "'";
			}
		}
		if(roleAccess.equals("Line_dte")){
			whr = " , Miso_Orbat_Unt_Dtl u where d.sus_no = u.sus_no and upper(u.status_sus_no) = 'ACTIVE' and u.arm_code in (select arm_code from Tb_Miso_Orbat_Line_Dte where line_dte_sus= '"+roleSusNo+"' ) ";
		}

		DataSet<View_TMS_Cveh_Mvcr_Update_Status_Details> dataSet = tmsDash.DatatablesCriteriasCMvcrstatus(criterias,
				whr);
		return DatatablesResponse.build(dataSet, criterias);
	}

	@RequestMapping(value = "/admin/loanStoreReport", method = RequestMethod.GET)
	public ModelAndView loanStoreReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("loanStoreReportTiles");
	}

	@RequestMapping(value = "/admin/getloanStoreReportRpt", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> getloanStoreReportRpt(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> dataSet = tmsDash.DatatablesCriteriasLoanStore(criterias);
		return DatatablesResponse.build(dataSet, criterias);
	}

	@RequestMapping(value = "/admin/SectorStoreReport", method = RequestMethod.GET)
	public ModelAndView SectorStoreReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("SectorStoreReportTiles");
	}

	@RequestMapping(value = "/admin/getSectorStoreReportRpt", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> getSectorStoreReportRpt(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> dataSet = tmsDash.DatatablesCriteriasSectorStore(criterias);
		return DatatablesResponse.build(dataSet, criterias);
	}

	@RequestMapping(value = "/admin/ACSFPStoreReport", method = RequestMethod.GET)
	public ModelAndView ACSFPStoreReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("ACSFPStoreReportTiles");
	}

	@RequestMapping(value = "/admin/getACSFPStoreReportRpt", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> getACSFPStoreReportRpt(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> dataSet = tmsDash.DatatablesCriteriasACSFPStore(criterias);
		return DatatablesResponse.build(dataSet, criterias);
	}

	@RequestMapping(value = "/admin/OverUeStoreReport", method = RequestMethod.GET)
	public ModelAndView OverUeStoreReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("OVERUEStoreReportTiles");
	}

	@RequestMapping(value = "/admin/getOverUeStoreReportRpt", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> getOverUeStoreReportRpt(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> dataSet = tmsDash.DatatablesCriteriasOverUeStore(criterias);
		return DatatablesResponse.build(dataSet, criterias);
	}

	@RequestMapping(value = "/getPendingApprovedStatusList", method = RequestMethod.POST)
	public @ResponseBody List<String> getPendingApprovedStatusList(HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		return tmsDash.getPendingApprovedStatusList();
	}

	@RequestMapping(value = "/getPendingApprovedRoRioStatusList", method = RequestMethod.POST)
	public @ResponseBody List<List<String>> getPendingApprovedRoRioStatusList(HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		return tmsDash.getPendingApprovedRoRioStatusList();
	}

	@RequestMapping(value = "/getCorpsWiseUE_UH_B_VEH_List", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getCorpsWiseUE_UH_B_VEH_List(String comnd, HttpSession session) {
		return tmsDash.getCorpsWiseUE_UH_B_VEH_List(comnd);
	}

	@RequestMapping(value = "/getDivWiseUE_UH_B_VEH_List", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getDivWiseUE_UH_B_VEH_List(String Corps, HttpSession session) {
		return tmsDash.getDivWiseUE_UH_B_VEH_List(Corps);
	}

	@RequestMapping(value = "/getBDEWiseUE_UH_B_VEH_List", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getBDEWiseUE_UH_B_VEH_List(String Div, HttpSession session) {
		return tmsDash.getBDEWiseUE_UH_B_VEH_List(Div);
	}

	@RequestMapping(value = "/getCorpsWiseUE_UH_A_VEH_List", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getCorpsWiseUE_UH_A_VEH_List(String comnd, HttpSession session) {
		return tmsDash.getCorpsWiseUE_UH_A_VEH_List(comnd);
	}

	@RequestMapping(value = "/getDivWiseUE_UH_A_VEH_List", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getDivWiseUE_UH_A_VEH_List(String Corps, HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		return tmsDash.getDivWiseUE_UH_A_VEH_List(Corps);
	}

	@RequestMapping(value = "/getBDEWiseUE_UH_A_VEH_List", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getBDEWiseUE_UH_A_VEH_List(String Div, HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		return tmsDash.getBDEWiseUE_UH_A_VEH_List(Div);
	}

	@RequestMapping(value = "/getCorpsWiseUE_UH_C_VEH_List", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getCorpsWiseUE_UH_C_VEH_List(String comnd, HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		return tmsDash.getCorpsWiseUE_UH_C_VEH_List(comnd);
	}

	@RequestMapping(value = "/getDivWiseUE_UH_C_VEH_List", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getDivWiseUE_UH_C_VEH_List(String Corps, HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		return tmsDash.getDivWiseUE_UH_C_VEH_List(Corps);
	}

	@RequestMapping(value = "/getBDEWiseUE_UH_C_VEH_List", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getBDEWiseUE_UH_C_VEH_List(String Div, HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		return tmsDash.getBDEWiseUE_UH_C_VEH_List(Div);
	}
	
	private ExportCSVFileController csv = new ExportCSVFileController();
	@RequestMapping(value = "/getCsvExport", method = RequestMethod.POST)
	public void getCsvExport(HttpServletResponse response,HttpSession session,String type) {
		
		@SuppressWarnings("unused")
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String whr = "";

		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				whr = " where substr(form_code_control,1,1) = '" + String.valueOf(roleFormationNo.charAt(0)) + "'";
			}
			if (roleSubAccess.equals("Corps")) {
				whr = " where substr(form_code_control,1,3) = '" + String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + "'";
			}
			if (roleSubAccess.equals("Division")) {
				whr = " where substr(form_code_control,1,6) = '" + String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))
						+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4))
						+ String.valueOf(roleFormationNo.charAt(5)) + "'";
			}
			if (roleSubAccess.equals("Brigade")) {
				whr = " where form_code_control = '" + roleFormationNo + "'";
			}
		}
		
		List<Map<String, Object>> list1 = null;
		if(type.equals("A")) {
			list1 = tmsDash.exportDashCSV_A(0,"ALL","","1","asc", whr);
		}
		if(type.equals("B")) {
			list1 = tmsDash.exportDashCSV_B(0,"ALL","","1","asc", whr);
			
		}
		if(type.equals("C")) {
			list1 = tmsDash.exportDashCSV_C(0,"ALL","","1","asc", whr);
			
		}
		String[] csvHeader1 = {"SUS NO", "COMMAND", "CORPS", "DIV", "BDE", "SUS NAME", "STATUS", "DATE", "APPROVED BY"};
		String[] nameMapping = {"sus_no", "cmd_name", "coprs_name","div_name","bde_name","unit_name","status","app_date","approved_by"};
		csv.exportToCSV_Map_String(response,list1,csvHeader1,nameMapping);
	}
}
