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

import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.OfficerReport.ServiceWiseDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class ServiceWise_Report {

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private ServiceWiseDao SD;
	
	@RequestMapping(value = "/admin/GetSearch_ServiceWiseUrl", method = RequestMethod.GET)
	public ModelAndView GetSearch_ServiceWiseUrl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		
		String  roleid = sessionUserId.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("GetSearch_ServiceWiseUrl", roleid);	
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
		Mmap.put("list", SD.Search_Servicewise());
		
		return new ModelAndView("ServiceWise_Tiles");
	}
	
	 @RequestMapping(value = "/GetSearch_ServiceWise", method = RequestMethod.POST) public
	  ModelAndView GetSearch_ServiceWise(ModelMap Mmap,HttpSession session,HttpServletRequest request,
	  @RequestParam(value = "msg", required = false) String msg,
	  @RequestParam(value = "from_date1", required = false) String from_date,
	  @RequestParam(value = "to_date1", required = false) String to_date){
		 
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("GetSearch_ServiceWiseUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			} 
			
	  ArrayList<ArrayList<String>>list =SD.Search_Servicewise(); 
	  
	  if(list.size() == 0){ 
		  Mmap.put("msg", "Data Not Available."); 
	  }	  
	  else
	  { 
		  Mmap.put("list", list); 
	  }
	  return new ModelAndView("ServiceWise_Tiles");
	  }
	 @RequestMapping(value = "/search_report_ServiseWise", method = RequestMethod.POST)
		public ModelAndView search_report_ServiseWise(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				
				@RequestParam(value = "level_c", required = false) String level_c,
				@RequestParam(value = "servicewiseReport1", required = false) String servicewiseReport1) {
			
			ArrayList<ArrayList<String>> list = SD.Search_Servicewise_pdf();
			
			if (list.size() == 0) {
				
				Mmap.put("msg", "Data Not Available.");
			} else {
				Mmap.put("list", list);
				Mmap.put("list.size()", list.size());
				if (servicewiseReport1 != null && servicewiseReport1.equals("pdfL")) {
					if (list.size() > 0) {
						List<String> TH = new ArrayList<String>();
						
	
					TH.add("ARM CODE");
					TH.add("ARM DESC");
					TH.add("TOTAL");
					
					Date date = new Date();
				
						
						String Heading ="\n HELD STR OF ARMY PERSONNEL IN UNITS AND ESTS OF CT \n PART -1 UNITS/FMN ON GROUND \n  Reported On: "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						String username = session.getAttribute("username").toString();
						return new ModelAndView(new DownloadPdf("L", TH, Heading, username), "userList", list);
					}
				}
			}
			return new ModelAndView("ServiceWise_Tiles");
		}
}
