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

import com.controller.ExportFile.ParentArm;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.OfficerReport.Parent_ArmDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Parent_arm_Report_Controller {
	
	@Autowired
	private Parent_ArmDAO paDao;
	
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/PARENT_ARM_URL", method = RequestMethod.GET)
	public ModelAndView PARENT_ARM_URL(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {

		
		  String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
	      String roleid = sessionUserId.getAttribute("roleid").toString();
	      
				Boolean val = roledao.ScreenRedirect("PARENT_ARM_URL", roleid);
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
		Mmap.put("list", paDao.Search_arm_desc());
		Mmap.put("list2", paDao.Search_gorkha());
		Mmap.put("list3", paDao.Search_infantry());
		
		
		return new ModelAndView("Parent_Arm_Tiles");
	}
	
	 @RequestMapping(value = "/Get_Search_Parent_ArmReport", method = RequestMethod.POST) public
	  ModelAndView Get_Search_Parent_ArmReport(ModelMap Mmap,HttpSession session,HttpServletRequest request,	  
	  @RequestParam(value = "msg", required = false) String msg){
		 
		
		  String roleid = session.getAttribute("roleid").toString();
	      
			Boolean val = roledao.ScreenRedirect("PARENT_ARM_URL", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 Mmap.put("msg", msg);
			Mmap.put("list", paDao.Search_arm_desc());
			Mmap.put("list2", paDao.Search_gorkha());
			Mmap.put("list3", paDao.Search_infantry());
			
			ArrayList<ArrayList<String>> list= paDao.Search_arm_desc() ;
			ArrayList<ArrayList<String>> list1= paDao.Search_gorkha() ;
			ArrayList<ArrayList<String>> list2= paDao.Search_infantry() ;
			int armsum=0;
			int gosum=0;
			int insum=0;
			
			
			for (int i = 0; i < list.size(); i++) {
				armsum=armsum + Integer.parseInt(list.get(i).get(2));
			}	
			for (int i = 0; i < list1.size(); i++) {
				gosum=gosum + Integer.parseInt(list1.get(i).get(2));
			}
			
			for (int i = 0; i < list2.size(); i++) {
				insum=insum + Integer.parseInt(list2.get(i).get(2));
			} 
			Mmap.put("armsum", armsum);
			Mmap.put("gosum", gosum);
			Mmap.put("insum", insum);
			
	  return new ModelAndView("Parent_Arm_Tiles");
	  
	  }
	 
	 @RequestMapping(value = "/search_pdf_parent_arm", method = RequestMethod.POST)
		public ModelAndView search_pdf_parent_arm(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,				
				@RequestParam(value = "arm_desc1", required = false) String arm_desc,
				@RequestParam(value = "str1", required = false) String str ,String typeReport1) {
			
			ArrayList<ArrayList<String>> list = paDao.Search_pdf();
			ArrayList<ArrayList<String>> list2 =paDao.Search_gorkha();
			ArrayList<ArrayList<String>> list3 =paDao.Search_infantry();
			
			if (list.size() == 0) {
				
				Mmap.put("msg", "Data Not Available.");
			} else {
				Mmap.put("list", list);
				Mmap.put("list.size()", list.size());
				List<String> TH = new ArrayList<String>();
					if (list.size() > 0) {
						TH.add("Ser No");
					TH.add("Arm Description");
					TH.add("Strength");// 0 
					}
					
					Date date = new Date();
				
						
						String Heading ="\n PARENT ARM REPORT \n  Reported On: "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						String SubHeading = "ARM DESCRIPTION";
						String SubHeading1 = "GORKHA";
						String SubHeading2 = "INFANTRY";
						String username = session.getAttribute("username").toString();
						String foot = " Report Generated On "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						return new ModelAndView(new ParentArm("L", TH, Heading, username,list2,list3,SubHeading,SubHeading1,SubHeading2,foot), "userList",list);
					
			}
			return new ModelAndView("Parent_Arm_Tiles");
		}
}
