package com.controller.itasset.Dashboard;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import com.dao.Dashboard.CueDashboardDAO;
import com.dao.it_asset_dashboard.DashboardDao;
import com.dao.login.RoleBaseMenuDAO;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class OnHoldingDashboardController {
	@Autowired
	CueDashboardDAO adminDash;

	public String pageType = "";
	public String pageTypeU = "";

	@Autowired
	DashboardDao dashboardDao;
	
	@Autowired
	 RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/onHoldingDashboard", method = RequestMethod.GET)
	public ModelAndView onHoldingDashboard(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("onHoldingDashboard", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			//return new ModelAndView("redirect:bodyParameterNotAllow");
//		}
		
		String formation_code = "";	
		String susno="";

		
		
		
		
		if(session.getAttribute("username")!=null)
		{
			formation_code = session.getAttribute("username").toString();
		
			susno = session.getAttribute("roleSusNo").toString();
	
			formation_code = formation_code.replaceAll("0*$", "");
			if(session.getAttribute("roleSusNo")!=null)
			{
				susno=session.getAttribute("roleSusNo").toString();
				
			}
			
		}
		
		Mmap.put("msg", msg);
		Mmap.put("getcompData",dashboardDao.getComputingData(formation_code,susno));
		Mmap.put("getperiData",dashboardDao.getPeripheralData(formation_code,susno));
		Mmap.put("getperipheralData",dashboardDao.getPeripheralData(formation_code,susno));
		
		//Mmap.put("getcomputingData",dashboardDao.getComputingGraphData());
		
	
		return new ModelAndView("onHoldingDashboardTiles");
	}

	
}
