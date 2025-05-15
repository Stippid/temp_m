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
import com.controller.ExportFile.PDF_Arms_ServiceWise_query;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Queries.Arms_ServiceWise_DAO;
import com.models.Tbl_CodesForm;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Arms_ServiceWise_Controller {
	
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	Psg_CommonController mcommon = new Psg_CommonController();
	
	
	@Autowired
	Arms_ServiceWise_DAO serv;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/ArmServices_Wise_Url", method = RequestMethod.GET)
	public ModelAndView ArmServices_Wise_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ArmServices_Wise_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}		
    	
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("ArmServices_Wise_Tiles");
	}
	
	

	@RequestMapping(value = "/getArmServices_Wise_ReportDataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getArmServices_Wise_ReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
			
		 return serv.getArmServices_Wise_ReportDataListDetails(startPage,pageLength,Search,orderColunm,orderType,sessionUserId); 
	}
	 
	 @RequestMapping(value = "/getArmServices_Wise_TotalCount", method = RequestMethod.POST)
	 public @ResponseBody long getArmServices_Wise_TotalCount(HttpSession sessionUserId,String Search){
	 	return serv.getArmServices_Wise_TotalCountDtl(Search);
	 }
	 
	 
	 @RequestMapping(value = "/Download_ArmServices_Wise_query",method = RequestMethod.POST)
		public ModelAndView Download_ArmServices_Wise_query(ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) throws ParseException {	
		 
		    String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("ArmServices_Wise_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}		
	    	
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
			ArrayList<ArrayList<String>> list = serv.pdf_getArmServices_Wise_ReportDataList();
			
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
					
			return new ModelAndView(new PDF_Arms_ServiceWise_query(Heading,TH,foot,username),"userList",list);
		}

	 @RequestMapping(value = "/Excel_ArmServices_Wise_query", method = RequestMethod.POST)
		public ModelAndView Excel_ArmServices_Wise_query(HttpServletRequest request,ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,HttpSession session,String typeReport1) {
		 
		   String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("ArmServices_Wise_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}		
	    	
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 ArrayList<ArrayList<String>> Excel = serv.pdf_getArmServices_Wise_ReportDataList();
			
			List<String> TH = new ArrayList<String>();
			TH.add("Ser No");
			TH.add("Arm Code");
			TH.add("Parent Arm");
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
