package com.controller.mnh;


import java.util.ArrayList;



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

import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;



@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Scrutiny_ICD_ManualController {
	

	@Autowired
	RoleBaseMenuDAO roledao;
	

	
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	@Autowired
	MNH_Common_DAO mnh1_Dao;
	
	MNH_CommonController mcommon = new MNH_CommonController();

	//DATA SCRUTINY MODULE (6)-> (ICD BOOK SCREEN START)
			@RequestMapping(value = "/admin/mnh_icd_book", method = RequestMethod.GET)
			public ModelAndView mnh_icd_book(ModelMap Mmap,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
				
				String username=(String) session.getAttribute("username");
				int roleid = (Integer)session.getAttribute("roleid");
				int userid = (Integer)session.getAttribute("userId");
				String roleType = (String) session.getAttribute("roleType");
				String accsLvl = (String) session.getAttribute("roleAccess");
				String accssubLvl=(String) session.getAttribute("roleAccess");
				
				Boolean val = roledao.ScreenRedirect("mnh_icd_book", session.getAttribute("roleid").toString());
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
				Mmap.put("msg", msg);
				return new ModelAndView("mnh_icd_bookTiles");
			}
			//DATA SCRUTINY MODULE (6)-> (ICD BOOK SCREEN END)
}
