package com.controller.psg.OfficerReport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.CommandGenderReport;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.OfficerReport.Commandwise_M_F_ReportDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Commandwise_m_f_Report_Controller {
	
	@Autowired
	private Commandwise_M_F_ReportDAO cmfDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Command_wise_male_fe_Url", method = RequestMethod.GET)
	public ModelAndView Command_wise_male_fe_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		
		 
	      String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
	      String roleid = sessionUserId.getAttribute("roleid").toString();
	      
				Boolean val = roledao.ScreenRedirect("Command_wise_male_fe_Url", roleid);
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
		
		ArrayList<ArrayList<String>>list =cmfDao.Search_Commandwise("","","");	
		Mmap.put("list", list);
		 int sum=0;
		int Fesum=0;
		int othsum=0;
		int tosum=0;
		for (int i = 0; i < list.size(); i++) {
		     sum=sum + Integer.parseInt(list.get(i).get(1));
		}
		for (int i = 0; i < list.size(); i++) {
			Fesum=Fesum + Integer.parseInt(list.get(i).get(2));
		}  	
		for (int i = 0; i < list.size(); i++) {
			othsum=othsum + Integer.parseInt(list.get(i).get(3));
		}
		for (int i = 0; i < list.size(); i++) {
			tosum=tosum + Integer.parseInt(list.get(i).get(4));
		} 
		 
		Mmap.put("fMale", sum);
		Mmap.put("fFemale", Fesum);
		Mmap.put("oOther", othsum);
		Mmap.put("tTotal", tosum);
		
		return new ModelAndView("Commandwise_m_f_Reports_Tiles");
	}
	
	 @RequestMapping(value = "/GetSearch_Command_M_F_Report", method = RequestMethod.POST) 
	 public ModelAndView GetSearch_Command_M_F_Report(ModelMap Mmap,HttpSession session,	  
	  @RequestParam(value = "msg", required = false) String msg,
	  @RequestParam(value = "command1", required = false) String command,
	  @RequestParam(value = "from_date1", required = false) String from_date,
	  @RequestParam(value = "to_date1", required = false) String to_date){
	  //Mmap.put("cat_name", cat_name1);
		 
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
	      String roleid = session.getAttribute("roleid").toString();
	      
			Boolean val = roledao.ScreenRedirect("Command_wise_male_fe_Url", roleid);				
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			String afom1[]=from_date.split("/");
			from_date = afom1[2]+"/"+afom1[1]+"/"+afom1[0];        
           
			String afom[]=to_date.split("/");
            to_date = afom[2]+"/"+afom[1]+"/"+afom[0];        
              
	  ArrayList<ArrayList<String>>list =cmfDao.Search_Commandwise(command,from_date,to_date);	 
	  int sum=0;
		int Fesum=0;
		int othsum=0;
		int tosum=0;
		for (int i = 0; i < list.size(); i++) {
		     sum=sum + Integer.parseInt(list.get(i).get(1));
		}
		for (int i = 0; i < list.size(); i++) {
			Fesum=Fesum + Integer.parseInt(list.get(i).get(2));
		}  	
		for (int i = 0; i < list.size(); i++) {
			othsum=othsum + Integer.parseInt(list.get(i).get(3));
		}
		for (int i = 0; i < list.size(); i++) {
			tosum=tosum + Integer.parseInt(list.get(i).get(4));
		} 
		 
		Mmap.put("fMale", sum);
		Mmap.put("fFemale", Fesum);
		Mmap.put("oOther", othsum);
		Mmap.put("tTotal", tosum);
	  
	  if(list.size() == 0){ 
		  Mmap.put("msg", "Data Not Available."); 
	  }	  
	  else
	  { 
		  Mmap.put("list", list); 
	  }
	  return new ModelAndView("Commandwise_m_f_Reports_Tiles");
	  }
	 
	 @RequestMapping(value = "/search_report_commandwise", method = RequestMethod.POST)
		public ModelAndView search_report_print(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				
				@RequestParam(value = "level_c", required = false) String level_c,
				@RequestParam(value = "male", required = false) String male , String female , String total , String typeReport1) {
			
			ArrayList<ArrayList<String>> list = cmfDao.Search_pdf(level_c,male, female,total);
			if (list.size() == 0) {
				
				Mmap.put("msg", "Data Not Available.");
			} else {
				Mmap.put("list", list);
				Mmap.put("list.size()", list.size());
			
				if (typeReport1 != null && typeReport1.equals("pdfL")) {
					if (list.size() > 0) {
						List<String> TH = new ArrayList<String>();
					TH.add("SR NO");
					TH.add("LEVEL C");
					TH.add("MALE");// 0 
					TH.add("FEMALE");//1
					TH.add("OTHER");//1
					TH.add("TOTAL");//1
					Date date = new Date();
				
					 
					int sum=0;
					int Fesum=0;
					int othsum=0;
					int tosum=0;
					for (int i = 0; i < list.size(); i++) {
					     sum=sum + Integer.parseInt(list.get(i).get(2));
					}
					for (int i = 0; i < list.size(); i++) {
						Fesum=Fesum + Integer.parseInt(list.get(i).get(3));
					}  	
					for (int i = 0; i < list.size(); i++) {
						othsum=othsum + Integer.parseInt(list.get(i).get(4));
					}
					for (int i = 0; i < list.size(); i++) {
						tosum=tosum + Integer.parseInt(list.get(i).get(5));
					} 
					ArrayList<String> tmp = new ArrayList<String>(0);
					tmp.add(String.valueOf(""));
					tmp.add(String.valueOf("Total"));
					tmp.add(String.valueOf(sum));
					tmp.add(String.valueOf(Fesum));
					tmp.add(String.valueOf(othsum));
					tmp.add(String.valueOf(tosum));
					list.add(tmp);
						
						String Heading ="\n COMMAND WISE MALE-FEMALE REPORT \n  Reported On: "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						String foot = " Report Generated On "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						String username = session.getAttribute("username").toString();
						return new ModelAndView(new CommandGenderReport("L", TH, Heading, username,foot), "userList", list);
					}
				}
			}
			return new ModelAndView("Commandwise_m_f_Reports_Tiles");
		}

}
