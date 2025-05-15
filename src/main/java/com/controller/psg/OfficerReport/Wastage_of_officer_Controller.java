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

import com.controller.ExportFile.Wastage_OFFR_CauseFor;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.OfficerReport.Commandwise_M_F_ReportDAO;
import com.dao.psg.OfficerReport.Wastage_of_Officer_by_Cause_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Wastage_of_officer_Controller {
	
	@Autowired
	private Wastage_of_Officer_by_Cause_DAO wocdao;

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Wastage_of_officer_report_url", method = RequestMethod.GET)
	public ModelAndView Wastage_of_officer_report_url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
	      String roleid = sessionUserId.getAttribute("roleid").toString();
	      
				Boolean val = roledao.ScreenRedirect("Wastage_of_officer_report_url", roleid);
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
		Mmap.put("list", wocdao.Search_wastage_officer_by_cause());

		
		
		return new ModelAndView("Wastage_officer_Tiles");
	}
	
	 @RequestMapping(value = "/GetSearch_Wastage_of_officer_By_Cause", method = RequestMethod.POST) public
	  ModelAndView GetSearch_Wastage_of_officer_By_Cause(ModelMap Mmap,HttpSession session,HttpServletRequest request,
	  @RequestParam(value = "msg", required = false) String msg){
		 
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
	      String roleid = session.getAttribute("roleid").toString();
	      
			Boolean val = roledao.ScreenRedirect("Wastage_of_officer_report_url", roleid);				
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
		 Mmap.put("msg", msg);
		 Mmap.put("list", wocdao.Search_wastage_officer_by_cause());
			
	  return new ModelAndView("Wastage_officer_Tiles");
	  
	  }
	 
	 @RequestMapping(value = "/search_report_Wastage_of_officer", method = RequestMethod.POST)
		public ModelAndView search_report_Wastage_of_officer(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				
				@RequestParam(value = "level_c", required = false) String level_c,
				@RequestParam(value = "male", required = false) String male , String female , String total , String typeReport1) {
			
			ArrayList<ArrayList<String>> list = wocdao.Search_pdf();
			if (list.size() == 0) {
				
				Mmap.put("msg", "Data Not Available.");
			} else {
				Mmap.put("list", list);
				Mmap.put("list.size()", list.size());
			
				if (typeReport1 != null && typeReport1.equals("pdfL")) {
					if (list.size() > 0) {
						List<String> TH = new ArrayList<String>();

						TH.add("SR NO");
					TH.add("CAUSE OF WASTAGE");
					TH.add("NO OF OFFRS");// 0 
					
					Date date = new Date();
				
						
						String Heading ="\n WASTAGE OF OFFICERS BY CAUSE FOR THE \n  Reported On: "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						String foot = " Report Generated On "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						String username = session.getAttribute("username").toString();
						return new ModelAndView(new Wastage_OFFR_CauseFor("L", TH, Heading, username,foot), "userList", list);
					}
				}
			}
			return new ModelAndView("Commandwise_m_f_Reports_Tiles");
		}

}
