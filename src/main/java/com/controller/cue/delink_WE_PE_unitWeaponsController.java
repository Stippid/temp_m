package com.controller.cue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_WEPE_link_sus_perstransweapon;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class delink_WE_PE_unitWeaponsController {

	String  wepe_weap_no="";
	String unit_sus_no = "";
	int id;
	
	@RequestMapping(value = "/delink_WE_PE_unitWeapons", method = RequestMethod.GET)
	public ModelAndView delink_WE_PE_unitWeapons(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("delink_WE_PE_unitWeaponsTiles","delink_WE_PE_unitWeaponsCmd",new CUE_TB_WEPE_link_sus_perstransweapon());
	}
	
	@RequestMapping(value = "/delink_WE_PE_unitWeaponsAction", method = RequestMethod.POST)
	public ModelAndView delink_WE_PE_unitWeaponsAction(@ModelAttribute("delink_WE_PE_unitWeaponsCmd") CUE_TB_WEPE_link_sus_perstransweapon rs,
			HttpServletRequest request,ModelMap model,HttpSession session) {
			
		unit_sus_no = request.getParameter("sus_no");
		wepe_weap_no = request.getParameter("wepe_weap_no");
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();
		
		try
		{
		 if(rs.getSus_no()== "")
			{
				model.put("msg", "Please Enter SUS No");
				return new ModelAndView("redirect:delink_WE_PE_unitWeapons");
			}
		// ---------------------------------- main link table -------------------------------------------------------------
				
		String hql4="update CUE_TB_WEPE_link_sus_perstransweapon set status_weap='3' where sus_no=:unit_sus_no and status_weap='1'";
			Query que4= sessionHQL.createQuery(hql4);
			que4.setParameter("unit_sus_no", unit_sus_no);
			int rowmain = que4.executeUpdate();
			tx0.commit();
			sessionHQL.close();
		
		model.put("msg", "Delink Successfully");
		}	
		catch (Exception e) {
			sessionHQL.getTransaction().rollback();
		}
	
		return new ModelAndView("redirect:delink_WE_PE_unitWeapons");
	}

}
