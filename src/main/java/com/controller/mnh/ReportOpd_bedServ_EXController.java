package com.controller.mnh;

import java.util.ArrayList;
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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.MNH_ReportsDAO;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class ReportOpd_bedServ_EXController {
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	
	@Autowired
	private MNH_ReportsDAO mnh7_Dao;
	
	@Autowired
	private MNH_CommonController mcommon;
	
	MNH_ValidationController validation = new MNH_ValidationController();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	//REPORTS   OPD REPORT  
		@RequestMapping(value = "/admin/mnh_opd_report", method = RequestMethod.GET)
		public ModelAndView mnh_opd_report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			
			Boolean val = roledao.ScreenRedirect("mnh_opd_report", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	       
	        
			ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			
			Mmap.put("r_1", l1);
			Mmap.put("getCommandList", all.getCommandDetailsList());


			//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
			Mmap.put("msg", msg);
			return new ModelAndView("mnh_opd_reportTiles");
		}
	
		//   BED SERVING 
		@RequestMapping(value = "/admin/mnh_bed_serving", method = RequestMethod.GET)
		public ModelAndView mnh_bed_serving(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			
			Boolean val = roledao.ScreenRedirect("mnh_bed_serving", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	       
	        
			ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			
			Mmap.put("r_1", l1);
			Mmap.put("getCommandList", all.getCommandDetailsList());

			//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
			Mmap.put("msg", msg);
			return new ModelAndView("mnh_bed_servingTiles");
		}
		
		//   BED EX-SERVE  
		@RequestMapping(value = "/admin/mnh_bed_exserve", method = RequestMethod.GET)
		public ModelAndView mnh_bed_exserve(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			
			Boolean val = roledao.ScreenRedirect("mnh_bed_exserve", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	       
	        
			ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			
			Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
			Mmap.put("r_1", l1);
			Mmap.put("getCommandList", all.getCommandDetailsList());

			//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("msg", msg);
			return new ModelAndView("mnh_bed_exserveTiles");
		}
		//REPORTS  STRENGTH REPORT 
		@RequestMapping(value = "/admin/mnh_strength_report", method = RequestMethod.GET)
		public ModelAndView mnh_strength_report(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			
			Boolean val = roledao.ScreenRedirect("mnh_strength_report", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	       
	        
			ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			
			Mmap.put("r_1", l1);
			Mmap.put("getCommandList", all.getCommandDetailsList());
        	//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
			Mmap.put("msg", msg);
			return new ModelAndView("mnh_strength_reportTiles");
		}
		
		// ----------------SEARCH------opd--bed surv-- ex surv-------------------//
		@RequestMapping(value = "/search_opd_bed_strength_report", method = RequestMethod.POST)
		public ModelAndView search_opd_bed_strength_report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "cmd1", required = false) String cmd1,String qtr1,String yr1,String para1,HttpServletRequest request){
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
		 	
			if(para1.equalsIgnoreCase("R1")){
				Boolean val = roledao.ScreenRedirect("mnh_opd_report", session.getAttribute("roleid").toString());
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
		 	}
		 	
		 	if(para1.equalsIgnoreCase("R2")){
		 		Boolean val = roledao.ScreenRedirect("mnh_bed_serving", session.getAttribute("roleid").toString());
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
		 	}
		 	
		 	if(para1.equalsIgnoreCase("R3")){
		 		Boolean val = roledao.ScreenRedirect("mnh_bed_exserve", session.getAttribute("roleid").toString());
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
		 	}
		 	
		 	if(para1.equalsIgnoreCase("R4")){
		 		Boolean val = roledao.ScreenRedirect("mnh_strength_report", session.getAttribute("roleid").toString());
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
		 	}
		 	if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	       
	        
		 	ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			if(para1.equalsIgnoreCase("R1")){
						if( qtr1 == null || qtr1.equals("-1")){ 
							Mmap.put("msg", "Please Select the Quarter");
							return new ModelAndView("redirect:mnh_opd_report");
						}
						if (yr1.equals("") || yr1 == null) {
							Mmap.put("msg", "Please Enter the Year");
							return new ModelAndView("redirect:mnh_opd_report");
						}
						if (request.getParameter("yr1").length() < 4) {
							Mmap.put("msg", "Please Enter the Valid Year");
							return new ModelAndView("redirect:mnh_opd_report");
						}
						if (validation.check_future_quarter(qtr1) == false) {
							Mmap.put("msg", validation.check_future_quarter);
							return new ModelAndView("redirect:mnh_opd_report");
						}
						if (validation.checkYearLength(yr1) == false) {
							Mmap.put("msg", validation.yearMSG);
							return new ModelAndView("redirect:mnh_opd_report");
						}
		
			}
			
			
			if(para1.equalsIgnoreCase("R2")){
				
				if( qtr1 == null || qtr1.equals("-1")){ 
					Mmap.put("msg", "Please Select the Quarter");
					return new ModelAndView("redirect:mnh_bed_serving");
				}
				if (yr1.equals("") || yr1 == null) {
					Mmap.put("msg", "Please Enter the Year");
					return new ModelAndView("redirect:mnh_bed_serving");
				}
				if (request.getParameter("yr1").length() < 4) {
					Mmap.put("msg", "Please Enter the Valid Year");
					return new ModelAndView("redirect:mnh_bed_serving");
				}
				if (validation.check_future_quarter(qtr1) == false) {
					Mmap.put("msg", validation.check_future_quarter);
					return new ModelAndView("redirect:mnh_bed_serving");
				}
				if (validation.checkYearLength(yr1) == false) {
					Mmap.put("msg", validation.yearMSG);
					return new ModelAndView("redirect:mnh_bed_serving");
				}

				}
			
			if(para1.equalsIgnoreCase("R3")){
				if( qtr1 == null || qtr1.equals("-1")){ 
					Mmap.put("msg", "Please Select the Quarter");
					return new ModelAndView("redirect:mnh_bed_exserve");
				}
				if (yr1.equals("") || yr1 == null) {
					Mmap.put("msg", "Please Enter the Year");
					return new ModelAndView("redirect:mnh_bed_exserve");
				}
				if (request.getParameter("yr1").length() < 4) {
					Mmap.put("msg", "Please Enter the Valid Year");
					return new ModelAndView("redirect:mnh_bed_exserve");
				}
				if (validation.check_future_quarter(qtr1) == false) {
					Mmap.put("msg", validation.check_future_quarter);
					return new ModelAndView("redirect:mnh_bed_exserve");
				}
				if (validation.checkYearLength(yr1) == false) {
					Mmap.put("msg", validation.yearMSG);
					return new ModelAndView("redirect:mnh_bed_exserve");
				}
				
				}
			
			if(para1.equalsIgnoreCase("R4")){
				if( qtr1 == null || qtr1.equals("-1")){ 
					Mmap.put("msg", "Please Select the Quarter");
					return new ModelAndView("redirect:mnh_strength_report");
				}
				if (yr1.equals("") || yr1 == null) {
					Mmap.put("msg", "Please Enter the Year");
					return new ModelAndView("redirect:mnh_strength_report");
				}
				if (request.getParameter("yr1").length() < 4) {
					Mmap.put("msg", "Please Enter the Valid Year");
					return new ModelAndView("redirect:mnh_strength_report");
				}
				if (validation.check_future_quarter(qtr1) == false) {
					Mmap.put("msg", validation.check_future_quarter);
					return new ModelAndView("redirect:mnh_strength_report");
				}
				if (validation.checkYearLength(yr1) == false) {
					Mmap.put("msg", validation.yearMSG);
					return new ModelAndView("redirect:mnh_strength_report");
				}
				
				}
			
			Mmap.put("r_1", l1);
		 	
		 	Mmap.put("cmd1", cmd1);
		 	Mmap.put("qtr1", qtr1);
		 	Mmap.put("yr1", yr1);
		 	
		 	List<Map<String, Object>> list = mnh7_Dao.search_opd_bed_strength_report(cmd1, qtr1, yr1, para1); 
		 	
			Mmap.put("list", list);
			Mmap.put("size", list.size());
			Mmap.put("getCommandList", all.getCommandDetailsList());
			//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		
			if(para1.equalsIgnoreCase("R1")){
				return new ModelAndView("mnh_opd_reportTiles");
			}
			if(para1.equalsIgnoreCase("R2")){
				return new ModelAndView("mnh_bed_servingTiles");
			}
			if(para1.equalsIgnoreCase("R3")){
				return new ModelAndView("mnh_bed_exserveTiles");
			}
	        if(para1.equalsIgnoreCase("R4")){
	        	return new ModelAndView("mnh_strength_reportTiles");
			}    
	        return null;
		}
		
	
	
	
}
