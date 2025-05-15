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

import com.controller.ExportFile.Actual_Str_Rank_Wise;
import com.controller.ExportFile.Str_OfRegimentalCenter;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.OfficerReport.Str_of_Regimental_OfcDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Str_of_Regimental_Ofc_Controller {
	
	@Autowired
	private Str_of_Regimental_OfcDAO RODao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/str_of_regimental_report_url", method = RequestMethod.GET)
	public ModelAndView str_of_regimental_report_url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		
		String  roleid = sessionUserId.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("str_of_regimental_report_url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
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
		Mmap.put("list", RODao.Search_str_of_regimental_ofc());
		
		return new ModelAndView("Str_of_Regimental_Ofc_Tiles");
	}
	
	 @RequestMapping(value = "/GetSearch_regimental_ofc", method = RequestMethod.POST) public
	  ModelAndView GetSearch_regimental_ofc(ModelMap Mmap,HttpSession session,
	  @RequestParam(value = "msg", required = false) String msg,
	  @RequestParam(value = "command1", required = false) String command,
	  @RequestParam(value = "from_date1", required = false) String from_date,
	  @RequestParam(value = "to_date1", required = false) String to_date){
		 
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
		   String roleid = session.getAttribute("roleid").toString();
		      
			Boolean val = roledao.ScreenRedirect("str_of_regimental_report_url", roleid);				
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
	  ArrayList<ArrayList<String>>list =RODao.Search_str_of_regimental_ofc(); 
	  
	  if(list.size() == 0){ 
		  Mmap.put("msg", "Data Not Available."); 
	  }	  
	  else
	  { 
		  Mmap.put("list", list); 
	  }
	  return new ModelAndView("Str_of_Regimental_Ofc_Tiles");
	  }
	 
	 @RequestMapping(value = "/search_report_regimental_ofc", method = RequestMethod.POST)
		public ModelAndView search_report_regimental_ofc(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				
				@RequestParam(value = "level_c", required = false) String level_c,
				@RequestParam(value = "male", required = false) String male , String female , String total , String typeReport1) {
			
			ArrayList<ArrayList<String>> list = RODao.Search_pdf();
			if (list.size() == 0) {
				
				Mmap.put("msg", "Data Not Available.");
			} else {
				Mmap.put("list", list);
				Mmap.put("list.size()", list.size());
			
				if (typeReport1 != null && typeReport1.equals("pdfL")) {
					if (list.size() > 0) {
						List<String> TH = new ArrayList<String>();

					TH.add("SR NO");
					TH.add("UNIT SUS NO");
					TH.add("UNIT NAME");
					TH.add("TOTAL");
					
					Date date = new Date();
				
						
						String Heading ="\n STR OF OFFICERS IN REGIMENTAL CENTRE \n  Reported On: "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						String username = session.getAttribute("username").toString();
						String foot = " Report Generated On "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						return new ModelAndView(new Str_OfRegimentalCenter("L", TH, Heading, username,foot), "userList", list);
					}
				}
			}
			return new ModelAndView("Str_of_Regimental_Ofc_Tiles");
		}

}
