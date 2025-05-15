package com.controller.psg.Queries;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.ExcelUserListReportView;
import com.controller.ExportFile.Excel_To_Export_2_Table_Report;
import com.controller.ExportFile.PDF_User_ParentArms_query;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Queries.User_ParentArms_DAO;
import com.models.Tbl_CodesForm;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class User_Parent_Arms_Service_Controller {
	
	
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	Psg_CommonController mcommon = new Psg_CommonController();
	
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	User_ParentArms_DAO up;
	
	@RequestMapping(value = "/admin/User_Parent_Arms_Url", method = RequestMethod.GET)
	public ModelAndView User_Parent_Arms_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("User_Parent_Arms_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		Mmap.put("date", date1);		
		Mmap.put("msg", msg);
		return new ModelAndView("User_ParentArms_Tiles");
	}
	
	
	
	@RequestMapping(value = "/getUser_ParentArms_ReportDataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getUser_ParentArms_ReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		 
		 return up.getUser_ParentArms_ReportDataListDetails(startPage,pageLength,Search,orderColunm,orderType,
				 sessionUserId);
	}
	 
	 @RequestMapping(value = "/getUser_ParentArms_TotalCount", method = RequestMethod.POST)
	 public @ResponseBody long getUser_ParentArms_TotalCount(HttpSession sessionUserId,String Search){
	 	return up.getUser_ParentArms_TotalCountDtl(Search);
	 }
	 
	 @RequestMapping(value = "/getUser_ParentArms_reporttbl_ctpart2", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getUser_ParentArms_reporttbl_ctpart2(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		 
		 return up.getUser_ParentArms_reporttbl_ctpart2Details(startPage,pageLength,Search,orderColunm,orderType,
				 sessionUserId);
	}
	 
	 @RequestMapping(value = "/getUser_ParentArms_reporttbl_ctpart2_TotalCount", method = RequestMethod.POST)
	 public @ResponseBody long getUser_ParentArms_reporttbl_ctpart2_TotalCount(HttpSession sessionUserId,String Search){
	 	return up.getUser_ParentArms_reporttbl_ctpart2_TotalCountDtl(Search);
	 }
	 
	 
	 @RequestMapping(value = "/Download_User_ParentArms_query",method = RequestMethod.POST)
		public ModelAndView Download_User_ParentArms_query(
				ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) throws ParseException {
		 
		   String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("User_Parent_Arms_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
			ArrayList<ArrayList<String>> pdfprint = up.pdf_getUser_ParentArms_ReportDataList();
			ArrayList<ArrayList<String>> pdfprint1 = up.pdf_getUser_ParentArms_ReportDataListctpart2();
			
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			
			//if(pdfprint.size() > 0) {
				List<String> TH = new ArrayList<String>();
				TH.add("Ser No");
				TH.add("CTPART_I");
				TH.add("ARM");
				TH.add("ARM_DECS");
				TH.add("AC");
				TH.add("ARTY");
				TH.add("AAD");
				TH.add("AVN");
				TH.add("ENG");
				TH.add("SIG");
				TH.add("GR");
				TH.add("INF");
				TH.add("MECH");
				TH.add("ASC");
				TH.add("AOC");
				TH.add("EME");
				TH.add("APS");
				TH.add("AEC");
				TH.add("INT");
				TH.add("JAG");
				TH.add("APTC");
				TH.add("SL_RO");
				TH.add("AMC");
				TH.add("ADC");
				TH.add("RVC");
				TH.add("MF");
				TH.add("TOTAL");
				
				List<String> TH1 = new ArrayList<String>();
				TH1.add("Ser No");
				TH1.add("CTPART_II");
				TH1.add("ARM");
				TH1.add("ARM_DECS");
				TH1.add("AC");
				TH1.add("ARTY");
				TH1.add("AAD");
				TH1.add("AVN");
				TH1.add("ENG");
				TH1.add("SIG");
				TH1.add("GR");
				TH1.add("INF");
				TH1.add("MECH");
				TH1.add("ASC");
				TH1.add("AOC");
				TH1.add("EME");
				TH1.add("APS");
				TH1.add("AEC");
				TH1.add("INT");
				TH1.add("JAG");
				TH1.add("APTC");
				TH1.add("SL_RO");
				TH1.add("AMC");
				TH1.add("ADC");
				TH1.add("RVC");
				TH1.add("MF");
				TH1.add("TOTAL");
									
			return new ModelAndView(new PDF_User_ParentArms_query(Heading,TH,TH1,foot,username,pdfprint,pdfprint1),"userList",pdfprint);
		}
	 
	 @RequestMapping(value = "/Excel_User_ParentArms_query", method = RequestMethod.POST)
		public ModelAndView Excel_User_ParentArms_query(HttpServletRequest request,ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,HttpSession session,String typeReport1) {
		 
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("User_Parent_Arms_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 ArrayList<ArrayList<String>> Excel = up.pdf_getUser_ParentArms_ReportDataList();
		 ArrayList<ArrayList<String>> Excel1 = up.pdf_getUser_ParentArms_ReportDataListctpart2();
			
		 List<String> TH = new ArrayList<String>();
			TH.add("Ser No");
			TH.add("CTPART_I");
			TH.add("ARM");
			TH.add("ARM_DECS");
			TH.add("AC");
			TH.add("ARTY");
			TH.add("AAD");
			TH.add("AVN");
			TH.add("ENG");
			TH.add("SIG");
			TH.add("GR");
			TH.add("INF");
			TH.add("MECH");
			TH.add("ASC");
			TH.add("AOC");
			TH.add("EME");
			TH.add("APS");
			TH.add("AEC");
			TH.add("INT");
			TH.add("JAG");
			TH.add("APTC");
			TH.add("SL_RO");
			TH.add("AMC");
			TH.add("ADC");
			TH.add("RVC");
			TH.add("MF");
			TH.add("TOTAL");
			
			List<String> TH1 = new ArrayList<String>();
			TH1.add("Ser No");
			TH1.add("CTPART_II");
			TH1.add("ARM");
			TH1.add("ARM_DECS");
			TH1.add("AC");
			TH1.add("ARTY");
			TH1.add("AAD");
			TH1.add("AVN");
			TH1.add("ENG");
			TH1.add("SIG");
			TH1.add("GR");
			TH1.add("INF");
			TH1.add("MECH");
			TH1.add("ASC");
			TH1.add("AOC");
			TH1.add("EME");
			TH1.add("APS");
			TH1.add("AEC");
			TH1.add("INT");
			TH1.add("JAG");
			TH1.add("APTC");
			TH1.add("SL_RO");
			TH1.add("AMC");
			TH1.add("ADC");
			TH1.add("RVC");
			TH1.add("MF");
			TH1.add("TOTAL");
			
			String Heading = "\nHELD STR OF OFFRS BY USER ARMS/ FMN AND PARENT ARM/SERVICES";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_To_Export_2_Table_Report("L", TH,TH1, Heading, username,Excel,Excel1), "userList", Excel );
		}

}
