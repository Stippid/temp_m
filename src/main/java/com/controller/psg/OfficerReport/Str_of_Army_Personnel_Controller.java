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
import com.controller.ExportFile.Held_Str_OnGround;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.OfficerReport.Str_of_Army_PersonnelDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Str_of_Army_Personnel_Controller {
	
	@Autowired
	private Str_of_Army_PersonnelDAO apDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/str_of_army_personnel_report_url", method = RequestMethod.GET)
	public ModelAndView str_of_army_personnel_report_url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		
		String  roleid = sessionUserId.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("str_of_army_personnel_report_url", roleid);	
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
		Mmap.put("list", apDao.Search_str_of_army_personnel());
		
		return new ModelAndView("Str_of_Army_Personnel_Tiles");
	}
	
	 @RequestMapping(value = "/GetSearch_Army_Personnel", method = RequestMethod.POST) public
	  ModelAndView GetSearch_Army_Personnel(ModelMap Mmap,HttpSession session,HttpServletRequest request,
	  
	  @RequestParam(value = "msg", required = false) String msg,
	  @RequestParam(value = "from_date1", required = false) String from_date,
	  @RequestParam(value = "to_date1", required = false) String to_date){
		 
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("str_of_army_personnel_report_url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			} 
	  ArrayList<ArrayList<String>>list =apDao.Search_str_of_army_personnel(); 
	  
	  if(list.size() == 0){ 
		  Mmap.put("msg", "Data Not Available."); 
	  }	  
	  else
	  { 
		  Mmap.put("list", list); 
	  }
	  return new ModelAndView("Str_of_Army_Personnel_Tiles");
	  }
	 
	 @RequestMapping(value = "/search_report_Army_Personnel", method = RequestMethod.POST)
		public ModelAndView search_report_Army_Personnel(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				
				@RequestParam(value = "level_c", required = false) String level_c,
				@RequestParam(value = "typeReport1", required = false) String typeReport1) {
			
			ArrayList<ArrayList<String>> list = apDao.Search_pdf();
			
			if (list.size() == 0) {
				
				Mmap.put("msg", "Data Not Available.");
			} else {
				Mmap.put("list", list);
				Mmap.put("list.size()", list.size());
			
				if (typeReport1 != null && typeReport1.equals("pdfL")) {
					if (list.size() > 0) {
						List<String> TH = new ArrayList<String>();
						
	
					TH.add("SR NO");
					TH.add("ARM CODE");
					TH.add("ARM DESC");
					TH.add("TOTAL");
					
					Date date = new Date();
				
						
						String Heading ="\n HELD STR OF ARMY PERSONNEL IN UNITS AND ESTS OF CT \n PART -1 UNITS/FMN ON GROUND \n  Reported On: "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						String username = session.getAttribute("username").toString();
						String foot = " Report Generated On "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						return new ModelAndView(new Held_Str_OnGround("L", TH, Heading, username,foot), "userList", list);
					}
				}
			}
			return new ModelAndView("Str_of_Army_Personnel_Tiles");
		}
}
