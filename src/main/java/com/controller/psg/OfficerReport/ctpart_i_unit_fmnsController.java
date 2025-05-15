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

import com.controller.ExportFile.Held_Str_OnGround;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.OfficerReport.ctpart_i_unit_fmnsDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class ctpart_i_unit_fmnsController {

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private ctpart_i_unit_fmnsDao CTD;
	
	@RequestMapping(value = "/admin/GetSearch_ctpartiUrl", method = RequestMethod.GET)
	public ModelAndView GetSearch_ctpartiUrl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		
		String  roleid = sessionUserId.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("GetSearch_ctpartiUrl", roleid);	
		 	if(val == false) {
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
		Mmap.put("list", CTD.Search_Ctparti());
		
		return new ModelAndView("ctpart_i_unit_fmns_Tiles");
	}
	
	 @RequestMapping(value = "/GetSearch_Ctparti", method = RequestMethod.POST) public
	  ModelAndView GetSearch_Ctparti(ModelMap Mmap,HttpSession session,HttpServletRequest request,
	  
	  @RequestParam(value = "msg", required = false) String msg,
	  @RequestParam(value = "from_date1", required = false) String from_date,
	  @RequestParam(value = "to_date1", required = false) String to_date){
		 
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
		   String roleid = session.getAttribute("roleid").toString();
		      
			Boolean val = roledao.ScreenRedirect("GetSearch_ctpartiUrl", roleid);				
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	  ArrayList<ArrayList<String>>list =CTD.Search_Ctparti(); 
	  
	  if(list.size() == 0){ 
		  Mmap.put("msg", "Data Not Available."); 
	  }	  
	  else
	  { 
		  Mmap.put("list", list); 
	  }
	  return new ModelAndView("ctpart_i_unit_fmns_Tiles");
	  }
	 @RequestMapping(value = "/Search_CTparti_pdf", method = RequestMethod.POST)
		public ModelAndView Search_CTparti_pdf(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				
				@RequestParam(value = "level_c", required = false) String level_c,
				@RequestParam(value = "ctpartiReport1", required = false) String ctpartiReport1) {
			
			ArrayList<ArrayList<String>> list = CTD.Search_CTparti_pdf();
			
			if (list.size() == 0) {
				
				Mmap.put("msg", "Data Not Available.");
			} else {
				Mmap.put("list", list);
				Mmap.put("list.size()", list.size());
				if (ctpartiReport1 != null && ctpartiReport1.equals("pdfL")) {
					if (list.size() > 0) {
						List<String> TH = new ArrayList<String>();
						
					TH.add("SR NO");
					TH.add("ARM CODE");
					TH.add("ARM DESC");
					TH.add("TOTAL");
					
					Date date = new Date();
				
						
						String Heading ="\n ACTUAL STR OF OFFRS IN USER ARM UNITS AND ESTABLISHMENT OF CT PART-1 UNIT/FMNS \n  Reported On: "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						String username = session.getAttribute("username").toString();
						String foot = " Report Generated On "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						return new ModelAndView(new Held_Str_OnGround("L", TH, Heading, username,foot), "userList", list);
					}
				}
			}
			return new ModelAndView("ctpart_i_unit_fmns_Tiles");
		}
}
