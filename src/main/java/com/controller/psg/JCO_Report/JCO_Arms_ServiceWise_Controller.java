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
import com.controller.JCO_ExportFile.PDF_Arms_ServiceWise_query_JCO;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.JCO_Report.JCO_Arms_ServiceWise_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class JCO_Arms_ServiceWise_Controller {
	
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	Psg_CommonController mcommon = new Psg_CommonController();
	
	
	@Autowired
	JCO_Arms_ServiceWise_DAO serv;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/ArmServices_Wise_JCOUrl", method = RequestMethod.GET)
	public ModelAndView ArmServices_Wise_JCOUrl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ArmServices_Wise_JCOUrl", roleid);
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
		return new ModelAndView("ArmServices_Wise_JCO_Tiles");
	}
	
	

	@RequestMapping(value = "/getArmServices_Wise_Report_JCODataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getArmServices_Wise_Report_JCODataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
			
		 return serv.getArmServices_Wise_JCOReportDataListDetails(startPage,pageLength,Search,orderColunm,orderType,sessionUserId); 
	}
	 
	 @RequestMapping(value = "/getArmServices_Wise_JCO_TotalCount", method = RequestMethod.POST)
	 public @ResponseBody long getArmServices_Wise_JCO_TotalCount(HttpSession sessionUserId,String Search){
	 	return serv.getArmServices_Wise_JCOTotalCountDtl(Search);
	 }
	 
	 
	 @RequestMapping(value = "/Download_ArmServices_Wise_JCO_query",method = RequestMethod.POST)
		public ModelAndView Download_ArmServices_Wise_JCO_query(ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) throws ParseException {	
		
			ArrayList<ArrayList<String>> list = serv.pdf_getArmServices_Wise_JCO_ReportDataList();
			
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			
				List<String> TH = new ArrayList<String>();
				TH.add("Ser No");
				TH.add("Parent Arm");
				TH.add("Arm Code");
				TH.add("Month");
				TH.add("Jan");
				TH.add("Feb");
				TH.add("Mar");				
				TH.add("Apr");
				TH.add("May");
				TH.add("Jun");			
				TH.add("Jul");
				TH.add("Aug");
				TH.add("Sep");				
				TH.add("Oct");
				TH.add("Nov");
				TH.add("Dec");
				TH.add("Total");				
		
			return new ModelAndView(new PDF_Arms_ServiceWise_query_JCO(Heading,TH,foot,username),"userList",list);
		}

	 @RequestMapping(value = "/Excel_ArmServices_Wise_query_JCO", method = RequestMethod.POST)
		public ModelAndView Excel_ArmServices_Wise_query_JCO(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {
		 
		 ArrayList<ArrayList<String>> Excel = serv.pdf_getArmServices_Wise_JCO_ReportDataList();
			
			List<String> TH = new ArrayList<String>();
			TH.add("Ser No");
			TH.add("Parent Arm");
			TH.add("Arm Code");
			TH.add("Jan");
			TH.add("Feb");
			TH.add("Mar");				
			TH.add("Apr");
			TH.add("May");
			TH.add("Jun");			
			TH.add("Jul");
			TH.add("Aug");
			TH.add("Sep");				
			TH.add("Oct");
			TH.add("Nov");
			TH.add("Dec");
			TH.add("Total");
			
			String Heading = "\nHELD STR OF OFFRs ARM/ SERVICE WISE";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", Excel);
		}
}
