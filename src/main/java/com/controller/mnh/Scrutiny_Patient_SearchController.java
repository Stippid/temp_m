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
import com.dao.mnh.Scrutiny_Patient_SearchDAO;




@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Scrutiny_Patient_SearchController {
	

	@Autowired
	RoleBaseMenuDAO roledao;

	
	@Autowired
	Scrutiny_Patient_SearchDAO ps_Dao;
	
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	@Autowired
	MNH_Common_DAO mnh1_Dao;
	
	
	MNH_ValidationController validation = new MNH_ValidationController();
	
	MNH_CommonController mcommon = new MNH_CommonController();
	
	//DATA SCRUTINY MODULE (5)-> (PATIENT SEARCH SCREEN START)
		@RequestMapping(value = "/admin/mnh_patient_search", method = RequestMethod.GET)
		public ModelAndView mnh_patient_search(ModelMap Mmap,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			
			Boolean val = roledao.ScreenRedirect("mnh_patient_search", session.getAttribute("roleid").toString());
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
			return new ModelAndView("mnh_patient_searchTiles");
		}
		//DATA SCRUTINY MODULE (5)-> (PATIENT SEARCH SCREEN END)
		
		//(5)-> (PATIENT SEARCH SCREEN METHODS START)
		@RequestMapping(value = "/patient_serach_ds", method = RequestMethod.POST)
		public ModelAndView patient_serach_ds(ModelMap model,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
				@ModelAttribute("sus1") String sus1,String unit1,String name1,String rk1,String andno1,String pno1,String pname1,String frm_dt1,String to_dt1){
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			
			Boolean val = roledao.ScreenRedirect("mnh_patient_search", session.getAttribute("roleid").toString());
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
		
			model.put("r_1", l1);
			
			List<Map<String, Object>> list= ps_Dao.search_patient_details_datascrutiny(sus1, name1, rk1, andno1, pno1, pname1, frm_dt1,to_dt1);
			model.put("list", list);
			model.put("size", list.size());
			
			model.put("sus1", sus1);
			model.put("unit1",unit1);
			model.put("name1",name1);
			model.put("rk1",rk1);
			model.put("andno1",andno1);
			model.put("pno1",pno1);
			model.put("pname1",pname1);
			model.put("frm_dt1",frm_dt1);
			model.put("to_dt1",to_dt1);
			
			return new ModelAndView("mnh_patient_searchTiles");	
		}
		//(5)-> (PATIENT SEARCH SCREEN METHODS END)
		
		
}
