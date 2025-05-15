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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.ExcelUserListReportView;
import com.controller.ExportFile.Excel_Age_Service_Wise_Report;
import com.controller.ExportFile.Excel_Cause_of_Wastage_Report;
import com.controller.ExportFile.PDF_Cause_of_Wastage;
import com.controller.ExportFile.PDF_Cause_of_Wastage_off;
import com.controller.ExportFile.PDF_Command_Wise_STR;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Queries.Cause_of_wastage_DAO;


@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Cause_of_Wastage_Controller {
	
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	Psg_CommonController mcommon = new Psg_CommonController();
	
	@Autowired
	Cause_of_wastage_DAO wastage;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	@RequestMapping(value = "/admin/Cause_of_Wastage_Url", method = RequestMethod.GET)
	public ModelAndView Cause_of_Wastage_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Cause_of_Wastage_Url", roleid);
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
		/* ArrayList<ArrayList<String>> list =wastage.getCause_of_wastage(); 
		  if(list.size() == 0){ 
			  Mmap.put("msg", "Data Not Available."); 
		  }	  
		  else
		  { 
			  Mmap.put("list", list); 
		  }*/
		return new ModelAndView("Cause_of_Wastage_Tiles");
	}
	
	@RequestMapping(value = "/getwestegeReportDataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getwestegeReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
		HttpSession sessionUserId) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		 	
		 
		 return wastage.getwestegeReportDataListDetails(startPage,pageLength,Search,orderColunm,orderType,sessionUserId);
	}
	 
	 @RequestMapping(value = "/getwestegeReportDataListcount", method = RequestMethod.POST)
	 public @ResponseBody long getwestegeReportDataListcount(HttpSession sessionUserId,String Search){
	 	return wastage.getwestegeReportDataListcountDtl(Search);
	 }
	
	

	 @RequestMapping(value = "/Download_Cause_of_wastage_query",method = RequestMethod.POST)
		public ModelAndView Download_Cause_of_wastage_query(
				ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, 
				Authentication authentication,HttpServletRequest request,HttpSession sessionEdit) throws ParseException {
		 
		 String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Cause_of_Wastage_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
		 	ArrayList<ArrayList<String>> pdfprint = wastage.getCause_of_wastage();
		 	String Heading ="";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			//if(pdfprint.size() > 0) {
				List<String> TH = new ArrayList<String>();
				TH.add("Cause of Wastage");
				TH.add("No of Offers");
				
		
			return new ModelAndView(new PDF_Cause_of_Wastage_off(Heading,TH,foot,username),"userList",pdfprint);
		}

	 @RequestMapping(value = "/Excel_Cause_of_wastage_query", method = RequestMethod.POST)
		public ModelAndView Excel_Cause_of_wastage_query(HttpServletRequest request,ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,HttpSession session,String typeReport1) {

		 
		    String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Cause_of_Wastage_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 ArrayList<ArrayList<String>> CauseExcel = wastage.getCause_of_wastage();
			
			List<String> TH = new ArrayList<String>();
			TH.add("Cause of Wastage");
			TH.add("No of Offers");
					
			List<String> CauseList = new ArrayList<String>();
			CauseList.add("Died");
			CauseList.add("Medical Invalidment");
			CauseList.add("Premature Retirement");
			CauseList.add("Relinquished Commission/Release SSC");
			CauseList.add("Retired");
			CauseList.add("Total");
			
						
			String Heading = "\nWASTAGE OF OFFICERS BY CAUSE FOR THE";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CauseExcel);
		}
}
