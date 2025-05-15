package com.controller.cue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.Dashboard.CueDashboardController;
import com.controller.ExportFile.ExcelUserListReportCue;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.cue.ceoDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.Tbl_CodesForm;
@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class CueReportController {
	CueDashboardController formation = new CueDashboardController();

	cueContoller M = new cueContoller();

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private ceoDAO cue;


	@RequestMapping(value = "/admin/view_reportUrl", method = RequestMethod.GET)
	public ModelAndView view_reportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type", required = false) String pageType1,
			@RequestParam(value = "pdf", required = false) String pdf,
			HttpServletRequest request) {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= formation.getFormationList("Command",formation_code);
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=formation.getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);

				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=formation.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=formation.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);

				List<Tbl_CodesForm> Bn=formation.getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);

				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,"","","","",sessionA);
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=formation.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=formation.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=formation.getFormationList("Division",div);
				Mmap.put("getDivList",Bn);

				List<Tbl_CodesForm> bde=formation.getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);

				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,"","","",sessionA);
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=formation.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=formation.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=formation.getFormationList("Division",div);
				Mmap.put("getDivList",Bn);

				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = formation.getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);
				Mmap.put("list_serv", list_serv);*/
			}
		}

		if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter") || roleAccess.equals("Line_dte")) {
			List<Tbl_CodesForm> comd=m.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Line_dte")){	
			
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
			Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}

		//		pageType = pageType1;
		Mmap.put("msg", msg);
		Mmap.put("pageType", pageType1);
//		String select = "<option value='0'>--Select--</option>";
//		Mmap.put("selectLine_dte", select);
//		Mmap.put("selectArmNameList", select);
//		Mmap.put("getArmNameList", M.getArmNameList());
//		Mmap.put("getLine_DteList", roledao.getLine_DteList(""));
		Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
		Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
		Mmap.put("getTypeOfUnitList", m.getTypeOfUnitList());

		return new ModelAndView("View_ReportTiles");

	}

	public String pageType = "";

	@RequestMapping(value = "/getPersReportcountTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_Active_Unit_DocTypeDetais_Report(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
			,String appt_trade,String rank,String rank_cat,String cont_comd, String cont_corps,String cont_div,String cont_bde,String sus_no, String we_pe_no,String eff_frm_date,String eff_to_date,String user_arm,String category_of_persn,String sponsor_dire,
			String training_capacity,String unit_category, String radio1, String type_force ,String parent_arm1, String weperadio) throws SQLException {
		return cue.getPersReportcountTable(startPage, pageLength, Search, orderColunm, orderType,sessionUserId, appt_trade,
				rank, rank_cat,cont_comd,  cont_corps, cont_div, cont_bde, sus_no,  we_pe_no, eff_frm_date, eff_to_date, user_arm, category_of_persn, sponsor_dire,
				training_capacity, unit_category,  radio1,type_force,  parent_arm1 ,weperadio);
	}

	@RequestMapping(value = "/getPersReportcount", method = RequestMethod.POST)
	public @ResponseBody long getPersReportcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String msg,String appt_trade,String rank,String rank_cat,String cont_comd, String cont_corps,String cont_div,String cont_bde,
			String sus_no, String we_pe_no,String eff_frm_date,String eff_to_date,String user_arm,String category_of_persn,String sponsor_dire,
			String training_capacity,String unit_category, String radio1, String type_force,String parent_arm1 ,String weperadio) throws SQLException {

		return cue.getPersReportcount(Search, orderColunm, orderType, sessionUserId,  appt_trade,
				rank,rank_cat, cont_comd,  cont_corps, cont_div, cont_bde, sus_no,  we_pe_no, eff_frm_date, eff_to_date, user_arm, category_of_persn, sponsor_dire,
				training_capacity, unit_category,  radio1,type_force,parent_arm1 ,weperadio);
	}

	@RequestMapping(value = "/Excel_report_query", method = RequestMethod.POST)
	public ModelAndView Excel_report_query(HttpServletRequest request,ModelMap model,
			@ModelAttribute("cont_comd1") String cont_comd,@ModelAttribute("cont_corps1") String cont_corps,
			@ModelAttribute("cont_div1") String cont_div,@ModelAttribute("cont_bde1") String cont_bde,
			@ModelAttribute("sus_no1") String sus_no,@ModelAttribute("we_pe_no1") String we_pe_no,
			@ModelAttribute("eff_frm_date1") String eff_frm_date,@ModelAttribute("eff_to_date1") String eff_to_date,
			@ModelAttribute("user_arm1") String user_arm,@ModelAttribute("category_of_persn1") String category_of_persn,
			@ModelAttribute("parent_arm11") String parent_arm,@ModelAttribute("rank_cat1") String rank_cat,
			@ModelAttribute("appt_trade1") String appt_trade,@ModelAttribute("rank1") String rank,
			@ModelAttribute("sponsor_dire1") String sponsor_dire,@ModelAttribute("type_force1") String type_force,
			@ModelAttribute("ct_part_i_ii1") String ct_part_i_ii,@ModelAttribute("training_capacity1") String training_capacity,
			@ModelAttribute("unit_category1") String unit_category,@ModelAttribute("weperadio") String weperadio,
			@RequestParam(value = "msg", required = false) String msg,HttpSession session,String typeReport1) {
	 
	   String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ArmServices_Wise_Url", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}		
    	
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<ArrayList<String>> Excel = cue.getPersExcelReportTable( appt_trade,
				rank, cont_comd,  cont_corps, cont_div, cont_bde, sus_no,  we_pe_no, eff_frm_date, eff_to_date, user_arm, category_of_persn, sponsor_dire,
				 training_capacity, unit_category,  ct_part_i_ii,type_force,  parent_arm,weperadio,rank_cat);
		
		List<String> TH = new ArrayList<String>();

		TH.add("Ser No");
		TH.add("Unit Name");
		TH.add("Sus No");
		TH.add("WE/PE No");
		TH.add("Table Title");
		TH.add("Eff From Date");				
		TH.add("Eff To Date");
		TH.add("User Arm");
		TH.add("Training Capacity");			
		TH.add("CMD");
		TH.add("CORPS");
		TH.add("DIV");				
		TH.add("BDE");
		TH.add("CT Type");
		TH.add("Parent Arm");
		TH.add("Directorate");
		TH.add("FF/NFF");
		TH.add("Category Of Pers");
		TH.add("Category");
		TH.add("Appt/Trade");
		TH.add("Rank");
		TH.add("Unit Category");
		TH.add("Base Auth");
		TH.add("Mod Auth");
		TH.add("Footnote Auth");
		TH.add("Total");
		
		
		System.err.println("list of wxcel data  -----   " + Excel);
		
		String Heading = "\nVIEW PERS REPORT";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportCue("L", TH, Heading, username), "userList", Excel);
	}


	@RequestMapping(value = "/Excel_report_query_all_report", method = RequestMethod.POST)
	public ModelAndView Excel_report_query_all_report(HttpServletRequest request,ModelMap model,
			@ModelAttribute("sus_no1") String sus_no,@ModelAttribute("we_pe_no1") String we_pe_no,
			@ModelAttribute("user_arm1") String user_arm,@ModelAttribute("category_of_persn1") String category_of_persn,
			@ModelAttribute("parent_arm11") String parent_arm,@ModelAttribute("rank_cat1") String rank_cat,
			@ModelAttribute("appt_trade1") String appt_trade,@ModelAttribute("rank1") String rank,
			@ModelAttribute("report1") String report,
			@RequestParam(value = "msg", required = false) String msg,HttpSession session,String typeReport1) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ArmServices_Wise_Url", roleid);
		//		if (val == false) {
		//			return new ModelAndView("AccessTiles");
		//		}
		System.out.println(report + " : gffffffffffffffffff");
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> TH =new ArrayList<String>();
		ArrayList<ArrayList<String>> excelData = null;
		if (report.equals("RankWiseStateofIA")) {
			excelData  = cue.rankWiseStateStrReportTableExcel( appt_trade, rank, sus_no,  we_pe_no,user_arm, category_of_persn, parent_arm);
			TH.add("Ser No");
			TH.add("Arm/Service");
			TH.add("offr Gen CT i");
			TH.add("offr Gen CT ii");
			TH.add("offr LT Gen CT i");
			TH.add("offr LT Gen CT ii");
			TH.add("Unsp Rks");
			TH.add("JCO");
			TH.add("OR");
			TH.add("First Line Reinforcement");
			TH.add("Foot Notes");
			TH.add("Grand Total");
			TH.add("Remarks");
		}
		if (report.equals("ArmWiseSanctionedStrofIA")) {
			excelData  = cue.armWiseStateStrReportTableExcel(category_of_persn, parent_arm);
			TH.add("Ser No");
			TH.add("Arm/Service");
			TH.add("offr CT i");
			TH.add("offr CT ii");
			TH.add("offr Total");
			TH.add("Jco CT i");
			TH.add("Jco CT ii");
			TH.add("Jco Total");
			TH.add("Or CT i");
			TH.add("Or CT ii");
			TH.add("Or Total");
			TH.add("FLR");
			TH.add("FN");
			TH.add("Grand Total");
		}
		if (report.equals("SdFormat1")) {
			excelData  = cue.sdFormatOneReportTableExcel(category_of_persn, parent_arm);
			TH.add("EST");
			TH.add("offr GEN");
			TH.add("offr LT FEN");
			TH.add("offr MAJ GEN");
			TH.add("offr BRIG");
			TH.add("offr COL");
			TH.add("offr Unspecified Rank");
			TH.add("Total");
			TH.add("Jco");
			TH.add("Or");
			TH.add("First Line Rainforcement");
			TH.add("Foot Notes");
			TH.add("Grand Total");
		}
		if (report.equals("ArmWiseStrCTI")) {
			excelData  = cue.armWiseStrCTiReportTableExcel(category_of_persn, parent_arm);
			TH.add("Ser No");
			TH.add("Arm/Service");
			TH.add("offrs");
			TH.add("Jco");
			TH.add("Or");
			TH.add("Total Incl First Line Rainforcement");
			TH.add("Foot Notes");
			TH.add("Total Str As on");
			TH.add("Allocation of First Line Rainforcement");
		}
		if (report.equals("ArmWiseStrCTII")) {
			excelData  = cue.armWiseStrCTiiReportTableExcel(category_of_persn, parent_arm);
			TH.add("Ser No");
			TH.add("Arm/Service");
			TH.add("offrs");
			TH.add("Jco");
			TH.add("Or");
			TH.add("Total Incl First Line Rainforcement");
			TH.add("Foot Notes");
			TH.add("Total Str As on");
			TH.add("Allocation of First Line Rainforcement");
		}
		if (report.equals("DteWiseStr")) {
			excelData  = cue.detWiseStrReportExcel(category_of_persn, parent_arm);
			TH.add("Ser No");
			TH.add("DTES");
			TH.add("GEN");
			TH.add("LT GEN");
			TH.add("MAJ GEN");
			TH.add("BRIG");
			TH.add("COL");
			TH.add("LT COL");
			TH.add("MAJ");
			TH.add("CAPT");
			TH.add("LT");
			TH.add("FD MARSHAL");
			TH.add("CIV GAZ");
			TH.add("JCO");
			TH.add("OR");
			TH.add("NON GZ CIV");
			TH.add("TOTAL");
			TH.add("REMARK");
		}
		if (report.equals("DtlsOfAllUnits")) {
			excelData  = cue.dtlsOfAllUnitsReportExcel(category_of_persn, parent_arm);
			TH.add("Sr No");
			TH.add("Type of Unit/Fmn/HQ/Est");
			TH.add("Manpower Auth(Officers/JCOs/OR)");
			TH.add("No of Unit/Fmn/HQ/Est");
			TH.add("Remarks");
		}
		if (report.equals("DesignCapacityofTrgCen")) {
			excelData  = cue.designCapReportExcel(category_of_persn, parent_arm);
			TH.add("Name of TRG Center");
			TH.add("Design Capacity");
		}

		if (report.equals("AuthStrofManpower")) {
			excelData  = cue.authStrOfManpowerReportExcel(category_of_persn, parent_arm);
			TH.add("Name of ESTB");
			TH.add("Officers");
			TH.add("JCO");
			TH.add("OR");
			TH.add("CIV GAZ");
			TH.add("CIV NON GAZ");
			TH.add("Total");
		}
		if (report.equals("AuthStrOfClrkInIA")) {
			excelData  = cue.authStrOfClrkInIAReportExcel(category_of_persn, parent_arm);
			TH.add("Sr No.");
			TH.add("Name of Recoard Office");
			TH.add("Combat Ants");
			TH.add("CIV Clerks");
			TH.add("Total");
			TH.add("Remarks");
		}
		if (report.equals("AuthStrOfManpwrInIA")) {
			excelData  = cue.authStrOfManpwerInIAReportExcel(category_of_persn, parent_arm);
			TH.add("User Arm");
			TH.add("Parent Arm");
			TH.add("Officer");
			TH.add("JCO");
			TH.add("OR");
			TH.add("Total");
			TH.add("Civ Gaz");
			TH.add("Civ NG Non Industrial");
			TH.add("Civ NG Industrial");
			TH.add("NCSU");
			TH.add("Grand Total");
		}
		
		if (report.equals("FormationWiseTtlStrInIA")) {
			excelData  = cue.formationWiseStrInIAReportExcel(category_of_persn, parent_arm);
			TH.add("Command");
			TH.add("Corps");
			TH.add("Div");
			TH.add("Bde");
			TH.add("Unit Name");
			TH.add("Officer");
			TH.add("JCO");
			TH.add("OR");
			TH.add("Civ");
			TH.add("Total");
		}
		
		if (report.equals("AuthStrOfOffrByRank")) {
			excelData  = cue.authStrOfOffrByRkReportExcel(category_of_persn, parent_arm);
			TH.add("Parent Arm");
			TH.add("Gen");
			TH.add("Lt Gen");
			TH.add("Maj Gen");
			TH.add("Brig");
			TH.add("Col");
			TH.add("Lt Col/Col");
			TH.add("Lt Col");
			TH.add("Maj");
			TH.add("Capt");
			TH.add("Lt");
			TH.add("Total");
		}
		
		if (report.equals("AuthStrinIA")) {
			excelData  = cue.authStrInIAReportExcel(category_of_persn, parent_arm);
			
			TH.add("Arm");
			TH.add("WE/PE/ No");
			TH.add("WE/PE Title");
			TH.add("Date Effective from");
			TH.add("Date Effective To");
			TH.add("No of Unit");
			TH.add("Regtl/ERE");
			TH.add("Parent Arm");
			TH.add("Officers");
			TH.add("JCO");
			TH.add("OR");
			TH.add("Civs");
			TH.add("Total");
		}
		if (report.equals("MajMinUnits")) {
			excelData  = cue.majMinUnitsReportExcel(category_of_persn, parent_arm);
			
			TH.add("Arm");
			TH.add("Unit Name");
			TH.add("Major/Minor Units");
			TH.add("Highest Rank");
			TH.add("Appt");
		}
		if (report.equals("AuthStrOfTrade")) {
			excelData  = cue.authStrOfTradeReportExcel(category_of_persn, parent_arm);
			
			TH.add("Trade");
			TH.add("Arms/Service");
			TH.add("WE/PE/GSL No");
			TH.add("Total No");
		}
		if (report.equals("AuthStrinIADtl")) {
			excelData  = cue.authStrinIADtlReportExcel(category_of_persn, parent_arm);
			
			TH.add("Unit SUS No");
			TH.add("Unit Name");
			TH.add("User Arm");
			TH.add("Comd");
			TH.add("Corps");
			TH.add("Div");
			TH.add("Bde");
			TH.add("Trade");
			TH.add("Arms/Service");
			TH.add("WE/PE/GSL No");
			TH.add("CT-i/CT-ii");
			TH.add("Unit Category");
			TH.add("FF/NFF");
			TH.add("Officers");
			TH.add("JCO");
			TH.add("OR");
			TH.add("Civ Gaz");
			TH.add("Civ NG Non Industrial");
			TH.add("Civ NG Industrial");
			TH.add("NCSU");
			TH.add("Total No");
			TH.add("Design Capacity");
		}
		
		if (report.equals("AuthStrNonReg")) {
			excelData  = cue.authStrNonRegReportExcel(category_of_persn, parent_arm);
			
			TH.add("Arm");
			TH.add("Officer");
			TH.add("JCO");
			TH.add("OR");
			TH.add("Total");
		}
		if (report.equals("AuthStrOfMnsOffr")) {
			excelData  = cue.authStrOfMnsOffrReportExcel(category_of_persn, parent_arm);
			
			TH.add("Arm");
			TH.add("Officer");
		}
		String Heading = "\n" +report;
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportCue("L", TH, Heading, username), "userList", excelData);
	}

	
	@RequestMapping(value = "/admin/all_reportsUrl", method = RequestMethod.GET)
	public ModelAndView all_reportsUrl(ModelMap Mmap, HttpSession session,
	@RequestParam(value = "msg", required = false) String msg,
	@RequestParam(value = "type", required = false) String pageType1,
	@RequestParam(value = "pdf", required = false) String pdf,
	HttpServletRequest request) {
	if (request.getHeader("Referer") == null) {
	msg = "";
	}
	ArrayList<ReportData> list6 = cue.armyAirDefenceReportTable(session);
	        Mmap.put("list6", list6);

	Set<String> allArms = new LinkedHashSet<>();
	        for (ReportData reportData : list6) {
	            allArms.addAll(reportData.getArmData().keySet());
	        }
	        Mmap.put("allArms", allArms);
	        Mmap.put("size6", list6.size());

	Mmap.put("msg", msg);
	Mmap.put("pageType", pageType1);
	String select = "<option value='0'>--Select--</option>";
	Mmap.put("selectLine_dte", select);
	Mmap.put("selectArmNameList", select);
	Mmap.put("getArmNameList", M.getArmNameList());
	Mmap.put("getLine_DteList", roledao.getLine_DteList(""));
	Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
	Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
	Mmap.put("getTypeOfUnitList", m.getTypeOfUnitList());

	return new ModelAndView("all_ReportsTiles");

	}

	@RequestMapping(value = "/rankWiseStateStrReportCountTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> rankWiseStateStrReportCountTable(int startPage, String Search,int pageLength,
			String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade,String rank, String rank_cat, String sus_no,
			String we_pe_no, String user_arm, String category_of_persn,String parent_arm1) throws SQLException {
		return cue.rankWiseStateStrReportCountTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				appt_trade, rank, rank_cat, sus_no, we_pe_no, user_arm, category_of_persn, parent_arm1);
	}
	@RequestMapping(value = "/rankWiseStateStrReportcount", method = RequestMethod.POST)
	public @ResponseBody long rankWiseStateStrReportcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String msg, String appt_trade, String rank, String rank_cat, String sus_no,
			String we_pe_no, String user_arm, String category_of_persn, String parent_arm1) throws SQLException {
		return cue.rankWiseStateStrReportcount(Search, orderColunm, orderType, sessionUserId, appt_trade, rank, rank_cat, sus_no,
				we_pe_no, user_arm, category_of_persn, parent_arm1);
	}


	@RequestMapping(value = "/armWiseStateStrReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> armWiseStateStrReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId, String appt_trade,String rank, String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn,String parent_arm1) throws SQLException {
		return cue.armWiseStateStrReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				appt_trade, rank, rank_cat, sus_no, we_pe_no, user_arm, category_of_persn, parent_arm1);
	}
	@RequestMapping(value = "/armWiseStateStrReportcount", method = RequestMethod.POST)
	public @ResponseBody long armWiseStateStrReportcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String msg, String appt_trade, String rank, String rank_cat, String sus_no,
			String we_pe_no, String user_arm, String category_of_persn, String parent_arm1) throws SQLException {
		return cue.armWiseStateStrReportcount(Search, orderColunm, orderType, sessionUserId, appt_trade, rank, rank_cat, sus_no,
				we_pe_no, user_arm, category_of_persn, parent_arm1);
	}

	// ARMY AIR DEFENCE REPORT

//	@RequestMapping(value = "/armyAirDefenceReportTable", method = RequestMethod.POST)
//	public @ResponseBody List<Map<String, Object>> armyAirDefenceReportTable(int startPage, String Search,
//			int pageLength, String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade,
//			String rank, String rank_cat, String sus_no, String we_pe_no, String user_arm, String category_of_persn,
//			String parent_arm1) throws SQLException {
//		return cue.armyAirDefenceReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
//				appt_trade, rank, rank_cat, sus_no, we_pe_no, user_arm, category_of_persn, parent_arm1);
//	}
//
//	@RequestMapping(value = "/armyAirDefenceReportCount", method = RequestMethod.POST)
//	public @ResponseBody long armyAirDefenceReportCount(String Search, String orderColunm, String orderType,
//			HttpSession sessionUserId, String msg, String appt_trade, String rank, String rank_cat, String sus_no,
//			String we_pe_no, String user_arm, String category_of_persn, String parent_arm1) throws SQLException {
//
//		return cue.armyAirDefenceReportCount(Search, orderColunm, orderType, sessionUserId, appt_trade, rank, rank_cat, sus_no,
//				we_pe_no, user_arm, category_of_persn, parent_arm1);
//	}

	@RequestMapping(value = "/estStrReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> estStrReportTable(int startPage, String Search,
			int pageLength, String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade,
			String rank, String rank_cat, String sus_no, String we_pe_no, String user_arm, String category_of_persn,
			String parent_arm1) throws SQLException {
		return cue.estStrReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				appt_trade, rank, rank_cat, sus_no, we_pe_no, user_arm, category_of_persn, parent_arm1);
	}
	@RequestMapping(value = "/estStrReportcount", method = RequestMethod.POST)
	public @ResponseBody long estStrReportcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String msg, String appt_trade, String rank, String rank_cat, String sus_no,
			String we_pe_no, String user_arm, String category_of_persn, String parent_arm1) throws SQLException {
		return cue.estStrReportcount(Search, orderColunm, orderType, sessionUserId, appt_trade, rank, rank_cat, sus_no,
				we_pe_no, user_arm, category_of_persn, parent_arm1);
	}


	@RequestMapping(value = "/armWiseStrCTiReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> armWiseStrCTiReportTable(int startPage, String Search,
			int pageLength, String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade,
			String rank, String rank_cat, String sus_no, String we_pe_no, String user_arm, String category_of_persn,
			String parent_arm1) throws SQLException {
		return cue.armWiseStrCTiReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				appt_trade, rank, rank_cat, sus_no, we_pe_no, user_arm, category_of_persn, parent_arm1);
	}
	@RequestMapping(value = "/armWiseStrCTiReportcount", method = RequestMethod.POST)
	public @ResponseBody long armWiseStrCTiReportcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String msg, String appt_trade, String rank, String rank_cat, String sus_no,
			String we_pe_no, String user_arm, String category_of_persn, String parent_arm1) throws SQLException {
		return cue.armWiseStrCTiReportcount(Search, orderColunm, orderType, sessionUserId, appt_trade, rank, rank_cat, sus_no,
				we_pe_no, user_arm, category_of_persn, parent_arm1);
	}


	@RequestMapping(value = "/armWiseStrCTiiReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> armWiseStrCTiiReportTable(int startPage, String Search,
			int pageLength, String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade,
			String rank, String rank_cat, String sus_no, String we_pe_no, String user_arm, String category_of_persn,
			String parent_arm1) throws SQLException {
		return cue.armWiseStrCTiiReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				appt_trade, rank, rank_cat, sus_no, we_pe_no, user_arm, category_of_persn, parent_arm1);
	}
	@RequestMapping(value = "/armWiseStrCTiiReportcount", method = RequestMethod.POST)
	public @ResponseBody long armWiseStrCTiiReportcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String msg, String appt_trade, String rank, String rank_cat, String sus_no,
			String we_pe_no, String user_arm, String category_of_persn, String parent_arm1) throws SQLException {
		return cue.armWiseStrCTiiReportcount(Search, orderColunm, orderType, sessionUserId, appt_trade, rank, rank_cat, sus_no,
				we_pe_no, user_arm, category_of_persn, parent_arm1);
	}


	@RequestMapping(value = "/dteWiseStrReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> dteWiseStrReportTable(int startPage, String Search,
			int pageLength, String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade,
			String rank, String rank_cat, String sus_no, String we_pe_no, String user_arm, String category_of_persn,
			String parent_arm1) throws SQLException {
		return cue.dteWiseStrReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				appt_trade, rank, rank_cat, sus_no, we_pe_no, user_arm, category_of_persn, parent_arm1);
	}
	@RequestMapping(value = "/dteWiseStrReportcount", method = RequestMethod.POST)
	public @ResponseBody long dteWiseStrReportcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String msg, String appt_trade, String rank, String rank_cat, String sus_no,
			String we_pe_no, String user_arm, String category_of_persn, String parent_arm1) throws SQLException {
		return cue.dteWiseStrReportcount(Search, orderColunm, orderType, sessionUserId, appt_trade, rank, rank_cat, sus_no,
				we_pe_no, user_arm, category_of_persn, parent_arm1);
	}


	@RequestMapping(value = "/dtlsOfAllUnitsReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> dtlsOfAllUnitsReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId) throws SQLException {
		return cue.dtlsOfAllUnitsReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}
	@RequestMapping(value = "/dtlsOfAllUnitsReportcount", method = RequestMethod.POST)
	public @ResponseBody long dtlsOfAllUnitsReportcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String msg) throws SQLException {

		return cue.dtlsOfAllUnitsReportcount(Search, orderColunm, orderType, sessionUserId);
	}


	@RequestMapping(value = "/designCapReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> designCapReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId) throws SQLException {
		return cue.designCapReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}
	@RequestMapping(value = "/designCapReportcount", method = RequestMethod.POST)
	public @ResponseBody long designCapReportcount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String msg) throws SQLException {

		return cue.designCapReportcount(Search, orderColunm, orderType, sessionUserId);
	}


	@RequestMapping(value = "/authStrOfManpowerReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> authStrOfManpowerReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId) throws SQLException {
		return cue.authStrOfManpowerReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}
	@RequestMapping(value = "/authStrOfManpowerReportcount", method = RequestMethod.POST)
	public @ResponseBody long authStrOfManpowerReportcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String msg) throws SQLException {
		return cue.authStrOfManpowerReportcount(Search, orderColunm, orderType, sessionUserId);
	}


	@RequestMapping(value = "/authStrOfClrkInIAReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> authStrOfClrkInIAReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId) throws SQLException {
		return cue.authStrOfClrkInIAReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}
	@RequestMapping(value = "/authStrOfClrkInIAReportcount", method = RequestMethod.POST)
	public @ResponseBody long authStrOfClrkInIAReportcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String msg) throws SQLException {
		return cue.authStrOfClrkInIAReportcount(Search, orderColunm, orderType, sessionUserId);
	}

	@RequestMapping(value = "/authStrOfManpwerInIAReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> authStrOfManpwerInIAReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId) throws SQLException {
		return cue.authStrOfManpwerInIAReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}
	@RequestMapping(value = "/authStrOfManpwerInIAReportcount", method = RequestMethod.POST)
	public @ResponseBody long authStrOfManpwerInIAReportcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String msg) throws SQLException {
		return cue.authStrOfManpwerInIAReportcount(Search, orderColunm, orderType, sessionUserId);
	}
	
	@RequestMapping(value = "/formationWiseStrInIAReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> formationWiseStrInIAReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId) throws SQLException {
		return cue.formationWiseStrInIAReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}
	@RequestMapping(value = "/formationWiseStrInIAReportcount", method = RequestMethod.POST)
	public @ResponseBody long formationWiseStrInIAReportcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String msg) throws SQLException {
		return cue.formationWiseStrInIAReportcount(Search, orderColunm, orderType, sessionUserId);
	}
	
	@RequestMapping(value = "/authStrOfOffrByRkReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> authStrOfOffrByRkReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId) throws SQLException {
		return cue.authStrOfOffrByRkReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}
	@RequestMapping(value = "/authStrOfOffrByRkReportcount", method = RequestMethod.POST)
	public @ResponseBody long authStrOfOffrByRkReportcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String msg) throws SQLException {
		return cue.authStrOfOffrByRkReportcount(Search, orderColunm, orderType, sessionUserId);
	}
	
	
	@RequestMapping(value = "/authStrInIAReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> authStrInIAReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId) throws SQLException {
		return cue.authStrInIAReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}
	@RequestMapping(value = "/authStrInIAReportcount", method = RequestMethod.POST)
	public @ResponseBody long authStrInIAReportcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String msg) throws SQLException {
		return cue.authStrInIAReportcount(Search, orderColunm, orderType, sessionUserId);
	}
	
	@RequestMapping(value = "/majMinUnitsReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> majMinUnitsReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId) throws SQLException {
		return cue.majMinUnitsReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}
	@RequestMapping(value = "/majMinUnitsReportcount", method = RequestMethod.POST)
	public @ResponseBody long majMinUnitsReportcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String msg) throws SQLException {
		return cue.majMinUnitsReportcount(Search, orderColunm, orderType, sessionUserId);
	}
	
	@RequestMapping(value = "/authStrOfTradeReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> authStrOfTradeReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId) throws SQLException {
		return cue.authStrOfTradeReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}
	@RequestMapping(value = "/authStrOfTradeReportcount", method = RequestMethod.POST)
	public @ResponseBody long authStrOfTradeReportcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String msg) throws SQLException {
		return cue.authStrOfTradeReportcount(Search, orderColunm, orderType, sessionUserId);
	}
	
	@RequestMapping(value = "/authStrinIADtlReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> authStrinIADtlReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId) throws SQLException {
		return cue.authStrinIADtlReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}
	@RequestMapping(value = "/authStrinIADtlReportcount", method = RequestMethod.POST)
	public @ResponseBody long authStrinIADtlReportcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String msg) throws SQLException {
		return cue.authStrinIADtlReportcount(Search, orderColunm, orderType, sessionUserId);
	}
	
	@RequestMapping(value = "/authStrNonRegReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> authStrNonRegReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId) throws SQLException {
		return cue.authStrNonRegReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}
	@RequestMapping(value = "/authStrNonRegReportcount", method = RequestMethod.POST)
	public @ResponseBody long authStrNonRegReportcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String msg) throws SQLException {
		return cue.authStrNonRegReportcount(Search, orderColunm, orderType, sessionUserId);
	}
	
	@RequestMapping(value = "/authStrOfMnsOffrReportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> authStrOfMnsOffrReportTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType, HttpSession sessionUserId) throws SQLException {
		return cue.authStrOfMnsOffrReportTable(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}
	@RequestMapping(value = "/authStrOfMnsOffrReportcount", method = RequestMethod.POST)
	public @ResponseBody long authStrOfMnsOffrReportcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String msg) throws SQLException {
		return cue.authStrOfMnsOffrReportcount(Search, orderColunm, orderType, sessionUserId);
	}
}
