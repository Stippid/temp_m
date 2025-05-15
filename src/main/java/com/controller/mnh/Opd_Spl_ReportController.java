package com.controller.mnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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

public class Opd_Spl_ReportController {

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
	
	//REPORTS OPD Spl Proc 
	@RequestMapping(value = "/admin/opd_spl_proc", method = RequestMethod.GET)
	public ModelAndView opd_spl_proc(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("opd_spl_proc", session.getAttribute("roleid").toString());
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
		return new ModelAndView("opd_spl_procTiles");
	}
	
	//Search opd spl 
	@RequestMapping(value = "/search_opd_spl_report", method = RequestMethod.POST)
	public ModelAndView search_opd_spl_report(ModelMap model,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,
			@ModelAttribute("cmd1") String cmd1,String qtr1,String yr1){
	
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("opd_spl_proc", session.getAttribute("roleid").toString());
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
		/*if( cmd1 == null || cmd1.equals("ALL")){ 
			model.put("msg", "Please Select the Command");
			return new ModelAndView("redirect:opd_spl_proc");
		}*/
		
		if( qtr1 == null || qtr1.equals("-1")){ 
			model.put("msg", "Please Select the Quarter");
			return new ModelAndView("redirect:opd_spl_proc");
		}
		if (yr1.equals("") || yr1 == null) {
			model.put("msg", "Please Enter the Year");
			return new ModelAndView("redirect:opd_spl_proc");
		}
		if (yr1.length() < 4) {
			model.put("msg", "Please Enter the Valid Year");
			return new ModelAndView("redirect:opd_spl_proc");
		}
		/*if (validation.check_future_quarter(qtr1) == false) {
			model.put("msg", validation.check_future_quarter);
			return new ModelAndView("redirect:opd_spl_proc");
		}*/
		if (validation.checkYearLength(yr1) == false) {
			model.put("msg", validation.yearMSG);
			return new ModelAndView("redirect:opd_spl_proc");
		}
		 
		model.put("r_1", l1);
	 	model.put("cmd1", cmd1);
		model.put("qtr1", qtr1);
		model.put("yr1", yr1);
	 	List<Map<String, Object>> list = mnh7_Dao.search_opd_spl_proc_report(cmd1, qtr1, yr1); 
	
		int sum=0;
		for (int i = 0; i < list.size(); i++) {
		   sum=sum + (int) (long) list.get(i).get("opd_count");
		}
		model.put("total1",sum);
	 	model.put("list", list);
	 	model.put("size", list.size());
	 	model.put("getCommandList", all.getCommandDetailsList());
	 	//model.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
	 	model.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		return new ModelAndView("opd_spl_procTiles");

	}


	
}
