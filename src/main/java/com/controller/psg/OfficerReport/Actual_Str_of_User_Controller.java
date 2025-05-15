package com.controller.psg.OfficerReport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.ActualStr_OFFR;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.OfficerReport.Actual_Str_UserDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Actual_Str_of_User_Controller {
	
	@Autowired
	private Actual_Str_UserDAO asuDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Actual_str_of_user_report_url", method = RequestMethod.GET)
	public ModelAndView Actual_str_of_user_report_url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		
		
		 String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
	      String roleid = sessionUserId.getAttribute("roleid").toString();
	      
				Boolean val = roledao.ScreenRedirect("Actual_str_of_user_report_url", roleid);
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
		String to_date = yyyy+"-01-01";
		Mmap.put("date", date1);
		Mmap.put("to_date",to_date );
		Mmap.put("msg", msg);
		Mmap.put("list", asuDao.Search_actual_str_user());
		
		return new ModelAndView("Actual_Str_of_User_Tiles");
	}
	
	 @RequestMapping(value = "/GetSearch_actual_user", method = RequestMethod.POST) public
	  ModelAndView search_report_actual_str_of_user(ModelMap Mmap,HttpSession session,HttpServletRequest request,
	  @RequestParam(value = "msg", required = false) String msg,
	  @RequestParam(value = "command1", required = false) String command,
	  @RequestParam(value = "from_date1", required = false) String from_date,
	  @RequestParam(value = "to_date1", required = false) String to_date){
		 
	   String roleSusNo = session.getAttribute("roleSusNo").toString();
	   String roleid = session.getAttribute("roleid").toString();
	      
		Boolean val = roledao.ScreenRedirect("Actual_str_of_user_report_url", roleid);				
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  ArrayList<ArrayList<String>>list =asuDao.Search_actual_str_user(); 
	  
	  if(list.size() == 0){ 
		  Mmap.put("msg", "Data Not Available."); 
	  }	  
	  else
	  { 
		  Mmap.put("list", list); 
	  }
	  return new ModelAndView("Actual_Str_of_User_Tiles");
	  }
	 
	 @RequestMapping(value = "/search_report_actual_str_of_user", method = RequestMethod.POST)
		public ModelAndView search_report_print(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				
				@RequestParam(value = "level_c", required = false) String level_c,
				@RequestParam(value = "male", required = false) String male , String female , String total , String typeReport1) {
			
			ArrayList<ArrayList<String>> list = asuDao.Search_pdf();
			if (list.size() == 0) {
				
				Mmap.put("msg", "Data Not Available.");
			} else {
				Mmap.put("list", list);
				Mmap.put("list.size()", list.size());
			
				if (typeReport1 != null && typeReport1.equals("pdfL")) {
					if (list.size() > 0) {
						List<String> TH = new ArrayList<String>();

						TH.add("SR NO");
					TH.add("CT PART I & II");
					TH.add("Arm Code");
					TH.add("Arm Desc");
					TH.add("AC");
					TH.add("ARTY");
					TH.add("AAD");
					TH.add("AVN");
					TH.add("ENG");
					TH.add("SNG");
					TH.add("GR");
					TH.add("INF");
					TH.add("MECH");
					TH.add("ASC");
					TH.add("EME");
					TH.add("APS");
					TH.add("AEC");
					TH.add("INT");
					TH.add("APTC");
					TH.add("SLRO");
					TH.add("AMC");
					TH.add("ADC");
					TH.add("RVC");
					TH.add("MF");
					TH.add("TOTAL");
					
					Date date = new Date();
				
						
						String Heading ="\n ACTUAL STR OF OFFRS BY USER ARMS/ FMN AND PARENT ARM/SERVICES \n  Reported On: "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						String username = session.getAttribute("username").toString();
						String foot = " Report Generated On "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						return new ModelAndView(new ActualStr_OFFR("L", TH, Heading, username,foot), "userList", list);
					}
				}
			}
			return new ModelAndView("Actual_Str_of_User_Tiles");
		}
}
