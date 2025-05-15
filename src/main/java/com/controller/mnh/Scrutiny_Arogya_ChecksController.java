package com.controller.mnh;


import java.util.ArrayList;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.controller.orbat.AllMethodsControllerOrbat;

import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.Scrutiny_Arogya_ChecksDAO;
import com.persistance.util.HibernateUtil;



@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Scrutiny_Arogya_ChecksController {

	@Autowired
	RoleBaseMenuDAO roledao;
	
	
	
	@Autowired
	Scrutiny_Arogya_ChecksDAO arc_dao;
	
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	@Autowired
	 MNH_Common_DAO mnh1_Dao;
	
	MNH_CommonController mcommon = new MNH_CommonController();

	//DATA SCRUTINY MODULE (7)-> (AROGYA CHECKS SCREEN START)
			@RequestMapping(value = "/admin/mnh_arogya_check", method = RequestMethod.GET)
			public ModelAndView mnh_arogya_check(ModelMap Mmap,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
				
				String username=(String) session.getAttribute("username");
				int roleid = (Integer)session.getAttribute("roleid");
				int userid = (Integer)session.getAttribute("userId");
				String roleType = (String) session.getAttribute("roleType");
				String accsLvl = (String) session.getAttribute("roleAccess");
				String accssubLvl=(String) session.getAttribute("roleAccess");
				
				Boolean val = roledao.ScreenRedirect("mnh_arogya_check", session.getAttribute("roleid").toString());
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
				Mmap.put("list_AROGYA", mcommon.getMedSystemCodeQua("AROGYA", "", session));
			
				
				Mmap.put("msg", msg);
				
				return new ModelAndView("mnh_arogya_chkTiles");
			}
			//DATA SCRUTINY MODULE (7)-> (AROGYA CHECKS SCREEN END)
			
			//(7)-> (AROGYA CHECKS SCREEN METHODS START)
			@RequestMapping(value = "/search_arogya_scrutiny", method = RequestMethod.POST)
			public ModelAndView search_arogya_scrutiny(ModelMap model,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
					@ModelAttribute("check1") String check1,String yr1){
				
				String username=(String) session.getAttribute("username");
				int roleid = (Integer)session.getAttribute("roleid");
				int userid = (Integer)session.getAttribute("userId");
				String roleType = (String) session.getAttribute("roleType");
				String accsLvl = (String) session.getAttribute("roleAccess");
				String accssubLvl=(String) session.getAttribute("roleAccess");
				
				Boolean val = roledao.ScreenRedirect("mnh_arogya_check", session.getAttribute("roleid").toString());
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				 if(request.getParameter("check1").equals("-1") || request.getParameter("check1") == "-1" || request.getParameter("check1") == null || request.getParameter("check1").equals("")){  
	                   model.put("msg", "Please Select the Check Value for Search");
	                   return new ModelAndView("redirect:mnh_arogya_check");
	                   } 
				
				ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
				accssubLvl = l1.get(0).get(1);
				accsLvl = l1.get(0).get(7);
				roleType = l1.get(0).get(8);
			
				model.put("r_1", l1);
				
				List<Map<String, Object>> list= arc_dao.search_arogya_scrutiny(check1, yr1);
				model.put("list", list);
				model.put("size", list.size());
				
				model.put("check1", check1);
				model.put("yr1",yr1);
				model.put("list_AROGYA", mcommon.getMedSystemCodeQua("AROGYA","",session));
				return new ModelAndView("mnh_arogya_chkTiles");	
			}
			
			

			@RequestMapping(value = "/delete_arogya" , method = RequestMethod.POST)
			public @ResponseBody ModelAndView delete_arogya(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				Boolean val = roledao.ScreenRedirect("mnh_arogya_check", sessionA.getAttribute("roleid").toString());
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				List<String> liststr = new ArrayList<String>();
				try {
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					String hqlUpdate = "delete from Scrutiny_Search_Model where id=:id";
					int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
					tx.commit();
					sessionHQL.close();

					if (app > 0) {
						liststr.add("Delete Successfully.");
					} else {
						liststr.add("Delete UNSuccessfully.");
					}
					model.put("msg",liststr.get(0));

				} catch (Exception e) {
					liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
					model.put("msg",liststr.get(0));
				}
				model.put("list_AROGYA", mcommon.getMedSystemCodeQua("AROGYA", "", sessionA));
				return new ModelAndView("mnh_arogya_chkTiles");
			}
			//(7)-> (AROGYA CHECKS SCREEN METHODS END)
}
