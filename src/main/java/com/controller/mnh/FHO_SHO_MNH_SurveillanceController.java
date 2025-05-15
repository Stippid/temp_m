package com.controller.mnh;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;


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

import org.springframework.web.servlet.ModelAndView;


import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.SHO_FHO_SurveillanceDao;
import com.models.mnh.Tb_Med_Serv_Data;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class FHO_SHO_MNH_SurveillanceController {
	
	@Autowired
	RoleBaseMenuDAO roledao;
	
	@Autowired
	SHO_FHO_SurveillanceDao Surve_Dao;
	
	
	MNH_CommonController mcommon = new MNH_CommonController();
	
	MNH_ValidationController validation = new MNH_ValidationController();
	
	AllMethodsControllerOrbat allorbat = new AllMethodsControllerOrbat();
	
	
	
	// SURVEILLANCE 
		@RequestMapping(value = "/admin/mnh_input_surveillance", method = RequestMethod.GET)
		public ModelAndView mnh_input_surveillance(ModelMap Mmap,HttpSession session,	HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,
				String sus,String date_from,String date_to,String type,String unit_name) {
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			
			Boolean val = roledao.ScreenRedirect("mnh_input_surveillance", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			  if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
				}

				Mmap.put("msg", msg);
			return new ModelAndView("mnh_input_surveillanceTiles");
		}
		
		

		/*search */
		@RequestMapping(value = "/admin/search_Surve", method = RequestMethod.POST)
		public ModelAndView search_Surve(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "sus1", required = false) String sus,	HttpServletRequest request,
				@RequestParam(value = "date_from1", required = false) String date_from,
				@RequestParam(value = "date_to1", required = false) String date_to,
				@RequestParam(value = "type1", required = false) String type,
				@RequestParam(value = "unit_name1", required = false) String unit_name) {
			Boolean val = roledao.ScreenRedirect("mnh_input_surveillance", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			  if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			try {
				if (unit_name == "") {
					Mmap.put("msg", "Please Enter the Unit Name");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				
				if (sus.equals("")) {
					Mmap.put("msg", "Please Enter the SUS No");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				if (date_from.equals("")) {
					Mmap.put("msg", "Please Enter the  From Date");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				if (date_to.equals("")) {
					Mmap.put("msg", "Please Enter the To Date");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				if (type.equals("")) {
					Mmap.put("msg", "Please Enter the Type");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			Mmap.put("list", Surve_Dao.search_Surve(sus,date_from,date_to,type));
			Mmap.put("msg", msg);
			Mmap.put("sus", sus);
			Mmap.put("date_from", date_from);
			Mmap.put("date_to", date_to);
			Mmap.put("type", type);
			Mmap.put("unit_name", unit_name);
			
			return new ModelAndView("mnh_input_surveillanceTiles");
			
		}
		
	
		/*save*/
		@RequestMapping(value = "/Capture_Survilance_DetailsAction", method = RequestMethod.POST)
	    public ModelAndView Capture_Survilance_DetailsAction(@ModelAttribute("Capture_Survilance_DetailsCMD") Tb_Med_Serv_Data lm,
	    		@RequestParam(value = "msg", required = false) String msg,
	    		BindingResult result,HttpServletRequest request,ModelMap Mmap,HttpSession session) throws ParseException 
		{
			Boolean val = roledao.ScreenRedirect("mnh_input_surveillance", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			  if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			String username = session.getAttribute("username").toString();
			String sus_no = request.getParameter("sus_no");
			String unit_name = request.getParameter("unit_name");
			String type = request.getParameter("type");
			
			Date date_from = null;
			DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			date_from = formatter1.parse(request.getParameter("date_from"));
			
			Date date_to = null;
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date_to = formatter.parse(request.getParameter("date_to"));

			
				if (unit_name == " ") {
					Mmap.put("msg", "Please Enter the Unit Name");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				else if(validation.DiseasemmrLength(request.getParameter("unit_name")) == false){
					Mmap.put("msg",validation.sho_fho_MSG);
                   return new ModelAndView("redirect:mnh_input_surveillance");
					}
				if (sus_no.equals("")) {
					Mmap.put("msg", "Please Enter the SUS No");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				else if(validation.sus_noLength(lm.getSus_no()) == false){
					Mmap.put("msg",validation.sus_noMSG);
                   return new ModelAndView("redirect:mnh_input_surveillance");
					}
				
				if (date_from.equals(null)) {
					Mmap.put("msg", "Please Enter the  From Date");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				if (date_to.equals(null)) {
					Mmap.put("msg", "Please Enter the To Date");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				if (type.equals("")) {
					Mmap.put("msg", "Please Enter the Type");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
			

				int count = 0;
				if (!request.getParameter("count").equals("")) {
					count = Integer.parseInt(request.getParameter("count"));
				}
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			int action=0;
			try {
			if(request.getParameter("action1") != "" & !request.getParameter("action1").equals("") & request.getParameter("action1") != null)
			{
				action = Integer.parseInt(request.getParameter("action1"));
			}
			for ( int i=1; i<=count; i++ ) {
				int test_self = Integer.parseInt(request.getParameter("test_self"+i)) ; 
				int test_wife = Integer.parseInt(request.getParameter("test_wife"+i)) ;
				int test_husband = Integer.parseInt(request.getParameter("test_husband"+i)) ;
				int test_child_less = Integer.parseInt(request.getParameter("test_child_less"+i)) ;
				int test_child_greater = Integer.parseInt(request.getParameter("test_child_greater"+i)) ;
				int test_mother = Integer.parseInt(request.getParameter("test_mother"+i)) ;
				int test_father = Integer.parseInt(request.getParameter("test_father"+i)) ;
				int test_sister = Integer.parseInt(request.getParameter("test_sister"+i)) ;
				int test_brother = Integer.parseInt(request.getParameter("test_brother"+i)) ;
				int positive_self = Integer.parseInt(request.getParameter("positive_self"+i)) ;
				int positive_wife = Integer.parseInt(request.getParameter("positive_wife"+i)) ;
				int positive_husband = Integer.parseInt(request.getParameter("positive_husband"+i)) ;
				int positive_child_less = Integer.parseInt(request.getParameter("positive_child_less"+i)) ;
				int positive_child_greater = Integer.parseInt(request.getParameter("positive_child_greater"+i)) ;
				int positive_mother = Integer.parseInt(request.getParameter("positive_mother"+i)) ;
				int positive_father = Integer.parseInt(request.getParameter("positive_father"+i)) ;
				int positive_sister = Integer.parseInt(request.getParameter("positive_sister"+i)) ;
				int positive_brother = Integer.parseInt(request.getParameter("positive_brother"+i)) ;
				int surv_master_id = Integer.parseInt(request.getParameter("hd_id"+i)) ;
				
			 
				if (test_self < positive_self) {
					Mmap.put("msg", "Positive Test Self  must be less or  equals to Test Self");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				else if (test_wife < positive_wife) {
					Mmap.put("msg", "Positive Test Of Wife  must be less or  equals to Test  Of Wife");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				else if(test_husband < positive_husband){
				 Mmap.put("msg", "Positive Test Of Husband  must be less or  equals to Test  Of Husband");
				 return new ModelAndView("redirect:mnh_input_surveillance");
				}  
				else if (test_child_less < positive_child_less) {
					Mmap.put("msg", "Positive Test Of Child >=5  must be less or  equals to Test  Of Child <=5");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				else if (test_child_greater < positive_child_greater) {
					Mmap.put("msg", "Positive Test Of Child <5  must be less or  equals to Test  Of Child >5");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				else if (test_mother < positive_mother) {
					Mmap.put("msg", "Positive Test Of Mother  must be less or  equals to Test  Of Mother");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				else if (test_father < positive_father) {
					Mmap.put("msg", "Positive Test Of Father  must be less or  equals to Test  Of Father");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				else if (test_sister < positive_sister) {
					Mmap.put("msg", "Positive Test Of Sister  must be less or  equals to Test  Of Sister");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
				else if (test_brother < positive_brother) {
					Mmap.put("msg", "Positive Test Of Brother  must be less or  equals to Test  Of Brother");
					return new ModelAndView("redirect:mnh_input_surveillance");
				}
			
				
				lm.setSurv_master_id(surv_master_id);
				lm.setTest_self(test_self);
				lm.setTest_wife(test_wife);
				lm.setTest_husband(test_husband);
				lm.setTest_child_less(test_child_less);
				lm.setTest_child_greater(test_child_greater);
				lm.setTest_mother(test_mother);
				lm.setTest_father(test_father);
				lm.setTest_sister(test_sister);
				lm.setTest_brother(test_brother);
				lm.setPositive_self(positive_self);
				lm.setPositive_wife(positive_wife);
				lm.setPositive_husband(positive_husband);
				lm.setPositive_child_less(positive_child_less);
				lm.setPositive_child_greater(positive_child_greater);
				lm.setPositive_mother(positive_mother);
				lm.setPositive_father(positive_father);
				lm.setPositive_sister(positive_sister);
				lm.setPositive_brother(positive_brother);
				lm.setCreated_by(username);
				lm.setDate_from(date_from);
				lm.setDate_to(date_to);
				sessionHQL.flush();
				sessionHQL.clear();
				if(action == 0)
				{
					lm.setCreated_by(username);
					sessionHQL.save(lm);
					sessionHQL.flush();
					sessionHQL.clear();
					if(i== count)
					{
						Mmap.put("msg", "Data saved Successfully");
					}
					
				}
				else
				{
					int id = Integer.parseInt(String.valueOf(request.getParameter("action"+i))) ;
					lm.setId(id);
					lm.setTest_self(test_self);
					sessionHQL.update(lm);
					sessionHQL.flush();
					sessionHQL.clear();
					if(i== count)
					{
						Mmap.put("msg", "Data Updated Successfully");
					}
				}
				}
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldn’t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		    return new ModelAndView("redirect:mnh_input_surveillance");
		}
		
		//SCREEN END
		
}
