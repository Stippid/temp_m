package com.controller.Report;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.commonController.It_CommonController;
import com.controller.itasset.ExportFile.ExcelAuditReport;
import com.controller.itasset.ExportFile.PDF_AuditReport;
import com.dao.itasset.Report.Assests_Serviceablity_details_DAO;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class AuditReportController {

	It_CommonController common = new It_CommonController();

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	Assests_Serviceablity_details_DAO asd;

	@RequestMapping(value = "/admin/AuditReportUrl", method = RequestMethod.GET)
	public ModelAndView AuditReportUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("AuditReportUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String yyyy = new SimpleDateFormat("yyyy").format(date);
		String to_date = yyyy + "-01-01";
		Mmap.put("date", date1);
		Mmap.put("to_date", to_date);
		Mmap.put("msg", msg);
		Mmap.put("ComputingAssetList", common.getComputingAssetList());
		return new ModelAndView("AuditReportTiles");
	}

	@RequestMapping(value = "/AuditReportCount", method = RequestMethod.POST)
	public @ResponseBody long AuditReportCount(String Search,String orderColunm,String orderType,String asset_type,HttpSession sessionUserId) throws SQLException {
		return asd.getAuditReportCount(Search, orderColunm, orderType, asset_type, sessionUserId);
	}


	@RequestMapping(value = "/AuditReportData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> AuditReportData(int startPage,String Search,int pageLength,String orderColunm,
			String orderType,String asset_type,HttpSession sessionUserId) throws SQLException {
		return asd.getAuditReportData(startPage, pageLength, Search, orderColunm, orderType, asset_type, sessionUserId);
	}

	@RequestMapping(value = "/Download_AuditReport",method = RequestMethod.POST)
	public ModelAndView Download_AuditReport(ModelMap Mmap,Authentication authentication,HttpServletRequest request,HttpSession sessionEdit,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "asset_type2", required = false) String asset_type) throws ParseException {

		ArrayList<ArrayList<String>> pdfprint = asd.pdf_AuditReport(sessionEdit,asset_type);
		int total = pdfprint.size();
		String username =  sessionEdit.getAttribute("roleloginName").toString();


		Mmap.put("msg", msg);
		String Heading ="AUDIT REPORT";
		Date date = new Date();
		String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);

		List<String> TH = new ArrayList<String>();
		TH.add("Asset Type");
		TH.add("Machine Serial No.");
		TH.add("Make");
		TH.add("Model");
		TH.add("IP Address");
		TH.add("MAC Address");
		TH.add("Domain Username");
		TH.add("OS Installed");
		TH.add("Year Of Proc");
		TH.add("CD Avl.");
		TH.add("Last Cyber Audit Date");
		TH.add("Upgradation/Repair Done");
		TH.add("Component Upgraded");
		TH.add("Last Formatting Done");
		TH.add("Authority");
		TH.add("Reason For Formatting");
		TH.add("Total");

		return new ModelAndView(new PDF_AuditReport(TH,username,Heading,pdfprint,total),"userList",pdfprint);
	}




	@RequestMapping(value = "/Excel_AuditReport",method = RequestMethod.POST)
	public ModelAndView Excel_AuditReport(ModelMap Mmap,Authentication authentication,HttpServletRequest request,HttpSession sessionEdit,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "asset_type3", required = false) String asset_type) throws ParseException {

		ArrayList<ArrayList<String>> Excel = asd.excel_AuditReport(sessionEdit,asset_type);
		String username =  sessionEdit.getAttribute("username").toString();
		Mmap.put("msg", msg);
		String Heading ="AUDIT REPORT";
		Date date = new Date();
		String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);

		List<String> TH = new ArrayList<String>();
		TH.add("Asset Type");
		TH.add("Machine Serial No.");
		TH.add("Make");
		TH.add("Model");
		TH.add("IP Address");
		TH.add("MAC Address");
		TH.add("Domain Username");
		TH.add("OS Installed");
		TH.add("Year Of Proc");
		TH.add("CD Avl.");
		TH.add("Last Cyber Audit Date");
		TH.add("Upgradation/Repair Done");
		TH.add("Component Upgraded");
		TH.add("Last Formatting Done");
		TH.add("Authority");
		TH.add("Reason For Formatting");
		TH.add("Remarks");

		return new ModelAndView(new ExcelAuditReport("L", TH, Heading, username), "userList", Excel);
	}

}
