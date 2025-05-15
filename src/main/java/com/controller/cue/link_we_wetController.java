package com.controller.cue;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.cue.Cue_wepe_conditionDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.cue_wepe;
import com.models.cue_wet_pet;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 

public class link_we_wetController {
	@Autowired
	private Cue_wepe_conditionDAO wepelink;
	@Autowired
	private RoleBaseMenuDAO roledao ;
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/admin/link_we_wet", method = RequestMethod.GET)
	public ModelAndView link_we_wet(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("link_we_wet", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("link_we_wetTiles", "link_we_wetActionCMD", new cue_wepe());
	}
	
	@RequestMapping(value = "/admin/link_we_wet1", method = RequestMethod.POST)
	public ModelAndView link_we_wet1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe02", required = false) String we_pe,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "wet_link_status1", required = false) String wet_link_status,
			@RequestParam(value = "table_title1", required = false) String table_title){
			String roleType = session.getAttribute("roleType").toString();
			Mmap.put("wet_link_status1", wet_link_status);
			Mmap.put("table_title1", table_title);
			Mmap.put("we_pe02", we_pe);
			Mmap.put("we_pe_no1", we_pe_no);
		
			 List<Map<String, Object>> list =wepelink.getWEPElinkwithWETPET(we_pe,we_pe_no,wet_link_status,  roleType);
		
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("roleType", roleType);
		return new ModelAndView("link_we_wetTiles");
	}
	
	@RequestMapping(value = "/getsuperseededvalue2",method = RequestMethod.POST)
	public @ResponseBody List<cue_wet_pet> getsuperseededvalue2(String val,HttpSession sessionUserId) 
	{	
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from cue_wet_pet where wet_pet_no=:wet_pet_no") ;
		q.setParameter("wet_pet_no", val);
		@SuppressWarnings("unchecked")
		List<cue_wet_pet> list = (List<cue_wet_pet>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/link_we_wetAction",method = RequestMethod.POST)
	public ModelAndView link_we_wetAction(@ModelAttribute("link_we_wetActionCMD") cue_wepe rs,HttpServletRequest request,ModelMap model,HttpSession session) {
		String we_pe = request.getParameter("we_pe");
		String we_pe_no = request.getParameter("we_pe_no");
		String wet_pet_no = request.getParameter("wet_pet_no");
		String unit_visible = request.getParameter("unit_visible");
		String table_title =request.getParameter("table_title");
		Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL1.beginTransaction();		
		try
		{
		
		 if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) || we_pe.equals("undefined") )
		{
			model.put("msg", "Please Select WE/PE Document");
			return new ModelAndView("redirect:link_we_wet");
		}
		 if(validation.checkParent_codeLength(rs.getWe_pe())  == false)
			{
				model.put("msg",validation.wetypeMSG);
				return new ModelAndView("redirect:link_we_wet");					
			}
		 if(rs.getWe_pe_no() == "" || rs.getWe_pe_no() ==null || rs.getWe_pe_no().equals(null) || rs.getWe_pe_no().isEmpty())
		{
			model.put("msg", "Please Enter WE/PE No");
			return new ModelAndView("redirect:link_we_wet");
		}
		 if(validation.checkWepeLength(rs.getWe_pe_no())  == false)
		{
			model.put("msg",validation.wepenoMSG);
			return new ModelAndView("redirect:link_we_wet");
		}		 
		if(validation.checkWepetabletittleLength(rs.getTable_title())  == false)
		{
		 	model.put("msg",validation.wepetitleMSG);
			return new ModelAndView("redirect:link_we_wet");
		}		
		 String wet_pet = request.getParameter("wet_pet");
		 if(wet_pet == ""  || wet_pet==null || wet_pet=="null" || wet_pet.equals(null) || wet_pet.equals("undefined") )
		{
			model.put("msg", "Please Select WET/PET/FET Document");
			return new ModelAndView("redirect:link_we_wet");
		}	
		 
		if(validation.checkWETypeLength(wet_pet)  == false)
		{
		 	model.put("msg",validation.wetpetTypeMSG);
			return new ModelAndView("redirect:link_we_wet");
		}
		 
		 if(rs.getWet_pet_no() == "" || rs.getWet_pet_no() ==null ||rs.getWet_pet_no().equals(null) || rs.getWet_pet_no().isEmpty())
		{
			model.put("msg", "Please Enter WE/PE No");
			return new ModelAndView("redirect:link_we_wet");
		}
		if(validation.checkWetPetLength(rs.getWet_pet_no())  == false)
		{
		 	model.put("msg",validation.wetpetnoMSG);
			return new ModelAndView("redirect:link_we_wet");
		}
		if(unit_visible == ""  || unit_visible==null || unit_visible=="null" || unit_visible.equals(null) ||unit_visible.equals("undefined"))
		{
			model.put("msg", "Please Select Visibility to Unit or Not");
			return new ModelAndView("redirect:link_we_wet");
		}
		if(validation.checkParent_codeLength(rs.getUnit_visible())  == false)
		{
			model.put("msg",validation.visibMSG);
			return new ModelAndView("redirect:link_we_wet");					
		}	
		if(validation.checkRemarksLength(rs.getRemarks())  == false)
		{
			model.put("msg",validation.remarksMSG);
			return new ModelAndView("redirect:link_we_wet");					
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "update cue_wepe c set c.wet_pet_no = :wet_pet_no, unit_visible= :unit_visible,wet_link_status='0'   where c.we_pe_no = :we_pe_no) ";
		int up_id = sessionHQL.createQuery( hqlUpdate ).setString( "wet_pet_no", wet_pet_no ).setString( "unit_visible", unit_visible ).setString( "we_pe_no", we_pe_no ).executeUpdate();
		tx.commit();
		sessionHQL.close();
		model.put("msg", "Linked WET/PET/FET No Successfully");
	}	
	catch (Exception e) {
		sessionHQL1.getTransaction().rollback();
		
	}
		 List<Map<String, Object>> list =wepelink.getWEPElinkwithWETPET(we_pe,"","",  "");
		 model.put("we_pe02", we_pe);
		 model.put("we_pe_no1", we_pe_no);
		 model.put("table_title1", table_title);
		 model.put("list", list);
		 model.put("list.size()", list.size());
		return new ModelAndView("link_we_wetTiles");
	}
 
}
