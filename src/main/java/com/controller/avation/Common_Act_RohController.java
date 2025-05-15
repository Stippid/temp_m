package com.controller.avation;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.ValidationController;
import com.dao.avation.ActSlotDAO;
import com.dao.avation.DACRDAO;
import com.dao.avation.DACRDAOImpl;
import com.dao.avation.TailNoDAO;
import com.dao.avation.TailNoDAOImpl;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;

import com.models.TB_AVIATION_ROHAGENCY_MASTER;

import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Common_Act_RohController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private ActSlotDAO act_dao;
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	Common_Act_slotController_avation M = new Common_Act_slotController_avation();
	Common_ActNoController m1=new Common_ActNoController();
	DACRDAO dacrDAO = new DACRDAOImpl();
	ValidationController validation = new ValidationController();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	@RequestMapping(value = "/admin/ROHagancy_masterUrl")
	public ModelAndView Add_ROHagancy(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg" ,required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Mmap.put("msg", msg);
		 Mmap.put("ml_1", m1.getaircrafttypetList1(session));
		 Mmap.put("getlocList", all.getLOCList());
		return new ModelAndView("ROHagancy_masterTiles");
	}
	
	@RequestMapping(value = "/admin/ROHagancy_masterRpas")
	public ModelAndView Add_ROHagancyRpas(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg" ,required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Mmap.put("msg", msg);
		 Mmap.put("ml_1", m1.getaircrafttypetRPASList(session));
		 Mmap.put("getlocList", all.getLOCList());
		return new ModelAndView("ROHagancy_masterRPASTiles");
	}
	
	@RequestMapping(value = "/ROHagency_masterAction", method = RequestMethod.POST)
	public ModelAndView Add_ROHagencyAction(HttpServletRequest request, ModelMap model, HttpSession session) {
		String roleid = session.getAttribute("roleid").toString();
	    
	    String username = session.getAttribute("username").toString();		
	    String std_nomen = request.getParameter("std_nomen").toString();

	    String[] agencies = request.getParameterValues("roh_agency[]");
	    String[] locations = request.getParameterValues("location[]");
	    

	    if (std_nomen == null || std_nomen.trim().isEmpty()) {
	        model.put("msg", "Please select Type of Aircraft.");
	        return new ModelAndView("redirect:ROHagancy_masterUrl");
	    }
	    if (agencies == null || agencies.length == 0 || locations == null || locations.length == 0) {
	        model.put("msg", "Please select at least one ROH Agency and Location.");
	        return new ModelAndView("redirect:ROHagancy_masterUrl");
	    }

	    Session sessionHql = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = null;

	    try {
	        tx = sessionHql.beginTransaction();
	        Date date = new Date();

	        for (int i = 0; i < agencies.length; i++) {
	            if (agencies[i] != null && !agencies[i].trim().isEmpty() &&
	                locations[i] != null && !locations[i].trim().isEmpty()) {

	                TB_AVIATION_ROHAGENCY_MASTER newEntry = new TB_AVIATION_ROHAGENCY_MASTER();
	                newEntry.setStd_nomclature(std_nomen);
	                newEntry.setCreated_by(username);
	                newEntry.setCreated_on(date);
	                newEntry.setAgency(agencies[i]);
	                newEntry.setLocation(locations[i]);
	                newEntry.setAgency_name(agencies[i]+"-"+locations[i]);

	                sessionHql.save(newEntry);
	            }
	        }

	        tx.commit();
	        model.put("msg", "ROH Agencies and Locations added successfully.");
	        return new ModelAndView("redirect:ROHagancy_masterUrl");

	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	        model.put("msg", "Error while saving the data.");
	        return new ModelAndView("redirect:ROHagancy_masterUrl");
	    } finally {
	        sessionHql.close();
	    }
	}

	@RequestMapping(value = "/ROHagency_RpasAction", method = RequestMethod.POST)
	public ModelAndView Add_ROHagencyRPASAction(HttpServletRequest request, ModelMap model, HttpSession session) {
		String roleid = session.getAttribute("roleid").toString();
	    
	    String username = session.getAttribute("username").toString();		
	    String std_nomen = request.getParameter("std_nomen").toString();

	    String[] agencies = request.getParameterValues("roh_agency[]");
	    String[] locations = request.getParameterValues("location[]");
	    

	    if (std_nomen == null || std_nomen.trim().isEmpty()) {
	        model.put("msg", "Please select Type of Aircraft.");
	        return new ModelAndView("redirect:ROHagancy_masterRpas");
	    }
	    if (agencies == null || agencies.length == 0 || locations == null || locations.length == 0) {
	        model.put("msg", "Please select at least one ROH Agency and Location.");
	        return new ModelAndView("redirect:ROHagancy_masterRpas");
	    }

	    Session sessionHql = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = null;

	    try {
	        tx = sessionHql.beginTransaction();
	        Date date = new Date();

	        for (int i = 0; i < agencies.length; i++) {
	            if (agencies[i] != null && !agencies[i].trim().isEmpty() &&
	                locations[i] != null && !locations[i].trim().isEmpty()) {

	                TB_AVIATION_ROHAGENCY_MASTER newEntry = new TB_AVIATION_ROHAGENCY_MASTER();
	                newEntry.setStd_nomclature(std_nomen);
	                newEntry.setCreated_by(username);
	                newEntry.setCreated_on(date);
	                newEntry.setAgency(agencies[i]);
	                newEntry.setLocation(locations[i]);
	                newEntry.setAgency_name(agencies[i]+"-"+locations[i]);

	                sessionHql.save(newEntry);
	            }
	        }

	        tx.commit();
	        model.put("msg", "ROH Agencies and Locations added successfully.");
	        return new ModelAndView("redirect:ROHagancy_masterRpas");

	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	        model.put("msg", "Error while saving the data.");
	        return new ModelAndView("redirect:ROHagancy_masterRpas");
	    } finally {
	        sessionHql.close();
	    }
	}

}
