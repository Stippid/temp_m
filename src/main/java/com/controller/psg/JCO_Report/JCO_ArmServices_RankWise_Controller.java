package com.controller.psg.JCO_Report;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.ExcelUserListReportView;
import com.controller.JCO_ExportFile.PDF_ArmServices_RankWise_query_JCO;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.JCO_Report.JCO_ArmServices_RankWise_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class JCO_ArmServices_RankWise_Controller {
	
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	Psg_CommonController mcommon = new Psg_CommonController();
	
	
	@Autowired
	JCO_ArmServices_RankWise_DAO arm;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/ArmServices_RankWise_JCO_Url", method = RequestMethod.GET)
	public ModelAndView ArmServices_RankWise_JCO_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ArmServices_RankWise_JCO_Url", roleid);
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
    	
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		
		Mmap.put("msg", msg);
		return new ModelAndView("ArmServices_RankWise__JCOTiles");
	}
	
	
	@RequestMapping(value = "/getArmServices_RankWise_Report_JCODataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getArmServices_RankWise_Report_JCODataList(int startPage,String pageLength,String Search,String orderColunm,
			 String orderType,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, 
	 InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
				 
		 
		 
		 return arm.getArmServices_RankWise_Report_JCODataListDetails(startPage,pageLength,Search,orderColunm,orderType,sessionUserId);
	}
	 
	 @RequestMapping(value = "/getArmServices_RankWise_JCOTotalCount", method = RequestMethod.POST)
	 public @ResponseBody long getArmServices_RankWise_JCOTotalCount(HttpSession sessionUserId,String Search){
	 	return arm.getArmServices_RankWise_JCOTotalCountDtl(Search);
	 }

	 
	 @RequestMapping(value = "/Download_ArmServices_RankWise_JCOquery",method = RequestMethod.POST)
		public ModelAndView Download_ArmServices_RankWise_JCOquery(ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) throws ParseException {	
		
			ArrayList<ArrayList<String>> pdfprint = arm.pdf_getArmServices_RankWise_JCO_ReportDataList();
			
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			
			//if(pdfprint.size() > 0) {
				List<String> TH = new ArrayList<String>();
				TH.add("Ser No");
				TH.add("PARENT ARM");
				TH.add("ARM DESC");
				
				TH.add("WARRANT OFFICER");
				TH.add("SUB MAJ");
				TH.add("SUB");
				
				TH.add("NB SUB");
				TH.add("HAV");
				TH.add("NK");
				
				TH.add("SEP");
				TH.add("RECTS");
				
				TH.add("TOTAL");
				TH.add("PERCENTAGE");
				
			return new ModelAndView(new PDF_ArmServices_RankWise_query_JCO(Heading,TH,foot,username),"userList",pdfprint);
		}
	 
	 @RequestMapping(value = "/Excel_ArmServices_RankWise_query_JCO", method = RequestMethod.POST)
		public ModelAndView Excel_ArmServices_RankWise_query_JCO(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {
		 
		 ArrayList<ArrayList<String>> Excel = arm.pdf_getArmServices_RankWise_JCO_ReportDataList();
			
			List<String> TH = new ArrayList<String>();
			TH.add("Ser No");
			TH.add("PARENT ARM");
			TH.add("ARM DESC");		
			
			TH.add("WARRANT OFFICER");
			TH.add("SUB MAJ");
			TH.add("SUB");
			
			TH.add("NB SUB");
			TH.add("HAV");
			TH.add("NK");
			
			TH.add("SEP");
			TH.add("RECTS");	
			
			TH.add("TOTAL");
			TH.add("PERCENTAGE");
			
			String Heading = "\nHELD STR OF OFFRS BY ARM/SERVICES AND RANK WISE";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", Excel);
		}
}
