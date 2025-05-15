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
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.OfficerReport.Actual_Str_Rank_WiseDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Actual_Str_Rank_Wise_Controller {
	
	@Autowired
	private Actual_Str_Rank_WiseDAO srDao;
	

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/rank_wise_report_url", method = RequestMethod.GET)
	public ModelAndView rank_wise_report_url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		
//		String  roleid = sessionUserId.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("rank_wise_report_url", roleid);	//actual_str_rank_wise
//		 	if(val == false) {
//				return new ModelAndView("AccessTiles");
//			}
			/*if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			} */
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String yyyy = new SimpleDateFormat("yyyy").format(date);
		String to_date = yyyy+"-01-01";
		Mmap.put("date", date1);
		Mmap.put("to_date",to_date );
		Mmap.put("msg", msg);
		Mmap.put("list", srDao.Search_actual_str_rank_wise());
		
		return new ModelAndView("Actual_Str_Rank_Wise_Tiles");
	}
	
	 @RequestMapping(value = "/GetSearch_Str_Rank_Wise", method = RequestMethod.POST) public
	  ModelAndView GetSearch_Str_Rank_Wise(ModelMap Mmap,HttpSession session,HttpServletRequest request,
	  @RequestParam(value = "msg", required = false) String msg,
	  @RequestParam(value = "from_date1", required = false) String from_date,
	  @RequestParam(value = "to_date1", required = false) String to_date){
		 
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
		   String roleid = session.getAttribute("roleid").toString();
		      
			Boolean val = roledao.ScreenRedirect("rank_wise_report_url", roleid);				
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			} 
	  ArrayList<ArrayList<String>>list =srDao.Search_actual_str_rank_wise(); 
	  
	  if(list.size() == 0){ 
		  Mmap.put("msg", "Data Not Available."); 
	  }	  
	  else
	  { 
		  Mmap.put("list", list); 
	  }
	  return new ModelAndView("Actual_Str_Rank_Wise_Tiles");
	  }
	 
	 @RequestMapping(value = "/search_report_Str_Rank_Wise", method = RequestMethod.POST)
		public ModelAndView search_report_Str_Rank_Wise(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				
				@RequestParam(value = "level_c", required = false) String level_c,
				@RequestParam(value = "typeReport1", required = false) String typeReport1) {
			
			ArrayList<ArrayList<String>> list = srDao.Search_pdf();
			
			if (list.size() == 0) {
				
				Mmap.put("msg", "Data Not Available.");
			} else {
				Mmap.put("list", list);
				Mmap.put("list.size()", list.size());
				
				if (typeReport1 != null && typeReport1.equals("pdfL")) {
					if (list.size() > 0) {
						List<String> TH = new ArrayList<String>();
						
						TH.add("SR NO");//0
					TH.add("PARENT ARM");//1
					TH.add("ARM DESC");//2
					TH.add("FM");//3
					TH.add("GEN");//4
					TH.add("LT");//5
					TH.add("MAJGEN");//6
					TH.add("BRIG");//7
					TH.add("COL");//8
					TH.add("COLTS");//9
					TH.add("LTCOL");//10
					TH.add("MAJ");//11
					TH.add("CAPT");//12
					TH.add("LT");//13
					TH.add("TOTAL");//14
					TH.add("PERCENTAGE");//15
					Date date = new Date();
				
						
						String Heading ="\n ACTUAL STR OF OFFRS BY  ARM/SERVICES AND RANK WISE \n  Reported On: "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						String username = session.getAttribute("username").toString();
						String foot = " Report Generated On "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						return new ModelAndView(new Actual_Str_Rank_Wise("L", TH, Heading, username,foot), "userList", list);
					}
				}
			}
			return new ModelAndView("Actual_Str_Rank_Wise_Tiles");
		}

}
