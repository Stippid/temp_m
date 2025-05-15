package com.controller.Dashboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.Dashboard.OrbatDashboardDAO;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class OrbatDashboardController {	
	@Autowired
	OrbatDashboardDAO orbatDash;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	@RequestMapping(value = "/admin/commonDashboard", method = RequestMethod.GET)
	public ModelAndView AllDashboard(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false)String msg, HttpServletRequest request) {
		String successValue = session.getAttribute("successValue").toString();
		String role = session.getAttribute("role").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String sus = session.getAttribute("roleSusNo").toString();
		String nZBase="";
		
		if(role.equalsIgnoreCase("sama")) {
			return new ModelAndView("redirect:/admin/samaDashboard");				
		}
		
		if(roleAccess.equals("Unit")) {
			return new ModelAndView("redirect:UnitDashboard");	
		}
		
		if(role.equalsIgnoreCase("bde")) {
			return new ModelAndView("redirect:bdeDashboard");	
		}
		
		if (successValue.equals("MISO")) {
			 Mmap.put("msg", msg);
			Mmap.put("nBrd",roledao.nMgtSct(session).get(0));
			if(role.equalsIgnoreCase("COR")) {
				return new ModelAndView("redirect:fpSecDashboardColR");				
			} 
			
			else {
				
				if (role.equalsIgnoreCase("COMMAND")) {
					nZBase="%22.%22comd_sus%22%3D'"+sus+"'";
				} else if (role.equalsIgnoreCase("CORPS")) {
					nZBase="%22.%22corps_sus%22%3D'"+sus+"'";
				} else if (role.equalsIgnoreCase("DIV")) {
					nZBase="%22.%22div_sus%22%3D'"+sus+"'";
				} 
				
			/*	else if (role.equalsIgnoreCase("BDE")) {
					nZBase="%22.%22bde_sus%22%3D'"+sus+"'";
				}*/ 
				
				else if (role.equalsIgnoreCase("LINEDTE")) {
					nZBase="%22.%22linedte_sus%22%3D'"+sus+"'";
					
				} else {
					nZBase="%22.%22n%22%3D'"+sus+"'";
				}
				Mmap.put("nZBase", nZBase);
				
				return new ModelAndView("commanDashboardTiles");
			}
		} else {
			
			Mmap.put("msg", msg);
			session.invalidate();
			return new ModelAndView("redirect:login");
		}
	   
	}
	
	@RequestMapping(value = "/admin/superDashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.CheckDashboard("superDashboard", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("superDashboardTiles");
	}
}
