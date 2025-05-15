package com.controller.avation;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.cue.ProvisionPolicyController;
import com.controller.validation.ValidationController;
import com.dao.avation.AddActNoDAO;
import com.dao.avation.AddActNoDAOImpl;
import com.dao.avation.TailNoDAO;
import com.dao.avation.TailNoDAOImpl;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;

import com.models.TB_AVIATION_ACT_MASTER;
import com.models.TB_AVIATION_CHTL_DAILY_BASIS;
import com.models.TB_AVIATION_CHTL_TAILNO_DTL;
import com.models.TB_AVIATION_DAILY_BASIS;
import com.models.TB_AVIATION_DRR_DTL;
import com.models.TB_AVIATION_RPAS_DAILY_BASIS;
import com.models.TB_AVIATION_RPAS_TAILNO_DTL;
import com.models.TB_AVIATION_TAILNO_DTL;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

// Created By Mitesh  (20-11-2024)

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Common_TailNOController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	TailNoDAO addactDAO = new TailNoDAOImpl();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	ValidationController validation = new ValidationController();
	ProvisionPolicyController discard= new ProvisionPolicyController();
	
	Common_ActNoController m1=new Common_ActNoController();
	
	@RequestMapping(value = "/admin/add_tail_no")
	public ModelAndView Add_tail_no(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg" ,required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Mmap.put("msg", msg);
		 Mmap.put("ml_1", m1.getaircrafttypetList(session));
		 Mmap.put("GetCountry",addactDAO.Getallcountry());
		return new ModelAndView("add_tail_noTiles");
	}
	
	@RequestMapping(value = "/admin/add_tail_noRPAS")
	public ModelAndView Add_tail_noRPAS(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg" ,required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Mmap.put("msg", msg);
		 Mmap.put("ml_1", m1.getaircrafttypetRPASList(session));
		 Mmap.put("GetCountry",addactDAO.Getallcountry());
		return new ModelAndView("add_tail_noRPASTiles");
	}

	@RequestMapping(value = "/admin/add_tail_noCHTL")
	public ModelAndView Add_tail_noCHTL(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg" ,required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Mmap.put("msg", msg);
		 Mmap.put("ml_1", m1.getaircrafttypetCHTLList(session));
		 Mmap.put("GetCountry",addactDAO.Getallcountry());
		return new ModelAndView("add_tail_noCHTLTiles");
	}
   @RequestMapping(value = "/add_tail_noAction", method = RequestMethod.POST)
	public ModelAndView Add_act_noAction(@ModelAttribute("add_tail_noCMD") TB_AVIATION_TAILNO_DTL rs, HttpServletRequest request, ModelMap model, HttpSession session) {
	    String roleid = session.getAttribute("roleid").toString();
	    
	    String username = session.getAttribute("username").toString();
	    String std_nomen = request.getParameter("std_nomen").toString();

	    if (std_nomen.equals("") || std_nomen.equals("null") || std_nomen.equals(null)) {
	        model.put("msg", "Please select type of aircraft.");
	        return new ModelAndView("redirect:add_tail_no");
	    } else {
	        Session sessionHql = HibernateUtil.getSessionFactory().openSession();
	        try {
	            Date date = new Date(System.currentTimeMillis());
	            
	            
	            String tail_no = request.getParameter("tail_no").trim();
	            String sus_no = request.getParameter("sus_no").trim();
	            String unit_name = request.getParameter("unit_name").trim();
	            String eng_type = request.getParameter("eng_type").trim();
	            String lh_eng_ser_no = request.getParameter("lh_eng_ser_no").trim();
	            String rh_eng_ser_no = request.getParameter("rh_eng_ser_no").trim();
	            String insp = request.getParameter("next_insp").trim();
	            String remarks = request.getParameter("remarks").trim();
	            String stateofaircraft = request.getParameter("aircraft_type").trim();
	            String countrycode = request.getParameter("country_isocode").trim();
	            String purchasecost = request.getParameter("purchase_cost").trim();
	            
	         
	            String lh_eng_hrs = request.getParameter("lh_eng_hrs"); 
	            String lh_eng_minutes = request.getParameter("lh_eng_minutes"); 
	            System.out.println("Engine Hours: " + lh_eng_hrs + ", Engine Minutes: " + lh_eng_minutes);
	            
	            if (lh_eng_minutes.length() == 1) {
	                lh_eng_minutes = "0" + lh_eng_minutes;  
	            }
	            if (lh_eng_hrs.length() == 1) {
	            	lh_eng_hrs = "0" + lh_eng_hrs;  
	            }
	            
	            String lhcombinedEngineTime = lh_eng_hrs + ":" + lh_eng_minutes;
	            rs.setLh_eng_hrs(lhcombinedEngineTime);
	            
		        
	            String rh_eng_hrs = request.getParameter("rh_eng_hrs");
	            String rh_eng_minutes = request.getParameter("rh_eng_minutes"); 
	            
	            if (rh_eng_minutes.length() == 1) {
	            	rh_eng_minutes = "0" + rh_eng_minutes; 
	            }
	            if (rh_eng_hrs.length() == 1) {
	            	rh_eng_hrs = "0" + rh_eng_hrs;  
	            }
	            
	            String rhcombinedEngineTime = rh_eng_hrs + ":" + rh_eng_minutes;
	            rs.setRh_eng_hrs(rhcombinedEngineTime);
	            
	           
	            String lh_eng_installed_date_str = request.getParameter("lh_eng_date");
	            String rh_eng_installed_date_str = request.getParameter("rh_eng_date");
	            String date_of_aos_str = request.getParameter("date_of_aos");
	            
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date lh_eng_installed_date = null;
	            Date rh_eng_installed_date = null;
	            Date date_of_acceptance_osft = null;

	            
	            if (lh_eng_installed_date_str != null && !lh_eng_installed_date_str.isEmpty()) {
	                lh_eng_installed_date = new Date(dateFormat.parse(lh_eng_installed_date_str).getTime());
	            }

	            if (rh_eng_installed_date_str != null && !rh_eng_installed_date_str.isEmpty()) {
	                rh_eng_installed_date = new Date(dateFormat.parse(rh_eng_installed_date_str).getTime());
	            }

	            if (date_of_aos_str != null && !date_of_aos_str.isEmpty()) {
	                date_of_acceptance_osft = new Date(dateFormat.parse(date_of_aos_str).getTime());
	            }
	            
	         
	            String af_hrs = request.getParameter("af_hrs"); 
	            String af_minutes = request.getParameter("af_minutes"); 
	            System.out.println("Engine Hours: " + af_hrs + ", Engine Minutes: " + af_minutes);
	            
	            if (af_minutes.length() == 1) {
	            	af_minutes = "0" + af_minutes; 
	            }
	            if (af_hrs.length() == 1) {
	            	af_hrs = "0" + af_hrs;  
	            }
	           
	            String afTime = af_hrs + ":" + af_minutes;
	            
	           
	            String hrs = request.getParameter("hrs_left"); 
	            String minutes = request.getParameter("minutes_left"); 
	           
	            
	            if (minutes.length() == 1) {
	            	minutes = "0" + minutes; 
	            }
	            if (hrs.length() == 1) {
	            	hrs = "0" + hrs;  
	            }	
	           
	            String hrsTime = hrs + ":" + minutes;
	            
	           
	            String mon_hrs = request.getParameter("mon_hrs"); 
	            String mon_minutes = request.getParameter("mon_minutes"); 
	           
	            
	            if (mon_minutes.length() == 1) {
	            	mon_minutes = "0" + mon_minutes;  
	            }
	            if (mon_hrs.length() == 1) {
	            	mon_hrs = "0" + mon_hrs;  
	            }
	           
	            String monTime = mon_hrs + ":" + mon_minutes;
	            
	            
	         
	            String qtrly_hrs = request.getParameter("qtrly_hrs"); 
	            String qtrly_minutes = request.getParameter("qtrly_minutes"); 
	           
	            
	            if (qtrly_minutes.length() == 1) {
	            	qtrly_minutes = "0" + qtrly_minutes; 
	            }
	            if (qtrly_hrs.length() == 1) {
	            	qtrly_hrs = "0" + qtrly_hrs;  
	            }
	            
	            String qtrlyTime = qtrly_hrs + ":" + qtrly_minutes;
	            
	            
	            
	            String hyrly_hrs = request.getParameter("hyrly_hrs"); 
	            String hyrly_minutes = request.getParameter("hyrly_minutes"); 
	           
	            
	            if (hyrly_minutes.length() == 1) {
	            	hyrly_minutes = "0" + hyrly_minutes;  
	            }
	            if (hyrly_hrs.length() == 1) {
	            	hyrly_hrs = "0" + hyrly_hrs;  
	            }
	           
	            String hyrlyTime = hyrly_hrs + ":" + hyrly_minutes;

	           
	            String yrly_hrs = request.getParameter("yrly_hrs"); 
	            String yrly_minutes = request.getParameter("yrly_minutes"); 
	           
	            
	            if (yrly_minutes.length() == 1) {
	            	yrly_minutes = "0" + yrly_minutes;  
	            }
	            if (yrly_hrs.length() == 1) {
	            	yrly_hrs = "0" + yrly_hrs;  
	            }
	           
	            String yrlyTime = yrly_hrs + ":" + yrly_minutes;

	            

	            
	            rs.setTail_no(tail_no);
	            rs.setSus_no(sus_no);
	            rs.setUnit_name(unit_name);
	            rs.setEng_name(eng_type);
	            rs.setLh_eng_ser_no(lh_eng_ser_no);
	            rs.setRh_eng_ser_no(rh_eng_ser_no);
	            rs.setLh_eng_installed_date(lh_eng_installed_date);
	            rs.setRh_eng_installed_date(rh_eng_installed_date);
	            rs.setDate_of_acceptance_osft(date_of_acceptance_osft);
	            rs.setStd_nomclature(std_nomen);
	            rs.setCreated_by(username);
	            rs.setCreated_on(date);
	            rs.setCountry_isocode(countrycode);
	            rs.setPurchase_cost(purchasecost);
	            rs.setClassifications("1");
	            
	            
	            TB_AVIATION_DAILY_BASIS dacr = new TB_AVIATION_DAILY_BASIS();
	            
	            dacr.setAcc_no(tail_no);
	            dacr.setSus_no(sus_no);
	            dacr.setEng_hrs_left(lhcombinedEngineTime);
	            dacr.setEng_hrs_rigth(rhcombinedEngineTime);
	            dacr.setDate_goi_letter(date);
	            dacr.setAf_hrs(afTime);
	            dacr.setFalf_hrs_day("00:00");
	            dacr.setFalf_hrs_night("00:00");
	            dacr.setBal_hrs("00:00");
	            dacr.setG_run("00:00");
	            dacr.setHrs_left(hrsTime);
	            dacr.setHrs_monthly(monTime);
	            dacr.setHrs_qtrly(qtrlyTime);
	            dacr.setHrs_half_year(hyrlyTime);
	            dacr.setHrs_qtrly_flow(yrlyTime);
	            dacr.setNext_insp(insp);
	            dacr.setRemarks(remarks);
	            dacr.setLh_ser_no(lh_eng_ser_no);
	            dacr.setRh_ser_no(rh_eng_ser_no);
	            dacr.setAircraft_state(stateofaircraft);
	            

	           
	            if (addactDAO.ifExistActNo(tail_no)) {
	                model.put("msg", "Tail No Already Exists.");
	                return new ModelAndView("redirect:add_tail_no");
	            } else {
	                
	                sessionHql.beginTransaction();
	                sessionHql.save(rs);
	                sessionHql.save(dacr);
	                sessionHql.getTransaction().commit();
	                model.put("msg", "Tail No added successfully.");
	                return new ModelAndView("redirect:add_tail_no");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            sessionHql.getTransaction().rollback();
	            model.put("msg", "Error while saving the data.");
	            return new ModelAndView("redirect:add_tail_no");
	        } finally {
	            sessionHql.close();
	        }
	    }
	}
	
    //changes by mitesh-12-12-24
	@RequestMapping(value = "/add_tail_noRPASAction", method = RequestMethod.POST)
	public ModelAndView Add_act_noRPASAction(@ModelAttribute("add_tail_noRPASCMD") TB_AVIATION_RPAS_TAILNO_DTL rs,
	                                          HttpServletRequest request, ModelMap model, HttpSession session) {
	    String roleid = session.getAttribute("roleid").toString();
	    String username = session.getAttribute("username").toString();
	    String std_nomen = request.getParameter("std_nomen").toString();

	    if (std_nomen.equals("") || std_nomen.equals("null") || std_nomen.equals(null)) {
	        model.put("msg", "Please select type of aircraft.");
	        return new ModelAndView("redirect:add_tail_noRPAS");
	    } else {
	        Session sessionHql = HibernateUtil.getSessionFactory().openSession();
	        try {
	            Date date = new Date(System.currentTimeMillis());

	            String tail_no = request.getParameter("tail_no").trim();
	            String sus_no = request.getParameter("sus_no").trim();
	            String unit_name = request.getParameter("unit_name").trim();
	            String eng_type = request.getParameter("eng_type").trim();
	            String eng_ser_no = request.getParameter("eng_ser_no").trim();
	            String insp = request.getParameter("next_insp").trim();
	            String remarks = request.getParameter("remarks").trim();
	            String countrycode = request.getParameter("country_isocode").trim();
	            String purchasecost = request.getParameter("purchase_cost").trim();
	            
	            
	            String eng_hrs = request.getParameter("eng_hrs"); 
	            String eng_minutes = request.getParameter("eng_minutes"); 
	            System.out.println("Engine Hours: " + eng_hrs + ", Engine Minutes: " + eng_minutes);

	            
	            if (eng_minutes.length() == 1) {
	                eng_minutes = "0" + eng_minutes;  
	            }
	            if (eng_hrs.length() == 1) {
	            	eng_hrs = "0" + eng_hrs; 
	            }
	            
	            String combinedEngineTime = eng_hrs + ":" + eng_minutes;;
	            System.out.println("Combined Engine Hours: " + combinedEngineTime);
	            rs.setEng_hrs(combinedEngineTime);

	           
	            String eng_installed_date_str = request.getParameter("eng_date");
	            String date_of_aos_str = request.getParameter("date_of_aos");
	            
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date eng_installed_date = null;
	            Date date_of_acceptance_osft = null;

	            if (eng_installed_date_str != null && !eng_installed_date_str.isEmpty()) {
	                eng_installed_date = new Date(dateFormat.parse(eng_installed_date_str).getTime());
	            }
	            if (date_of_aos_str != null && !date_of_aos_str.isEmpty()) {
	                date_of_acceptance_osft = new Date(dateFormat.parse(date_of_aos_str).getTime());
	            }
	            
	            
	            String af_hrs = request.getParameter("af_hrs"); 
	            String af_minutes = request.getParameter("af_minutes"); 
	            System.out.println("Engine Hours: " + af_hrs + ", Engine Minutes: " + af_minutes);
	            
	            if (af_minutes.length() == 1) {
	            	af_minutes = "0" + af_minutes;  
	            }
	            if (af_hrs.length() == 1) {
	            	af_hrs = "0" + af_hrs; 
	            }
	           
	            String afTime = af_hrs + ":" + af_minutes;
	            
	           
	            String hrs = request.getParameter("hrs_left"); 
	            String minutes = request.getParameter("minutes_left");
	           
	            
	            if (minutes.length() == 1) {
	            	minutes = "0" + minutes; 
	            }
	            if (hrs.length() == 1) {
	            	hrs = "0" + hrs; 
	            }
	           
	            String hrsTime = hrs + ":" + minutes;
	            
	            
	            String mon_hrs = request.getParameter("mon_hrs"); 
	            String mon_minutes = request.getParameter("mon_minutes"); 
	           
	            
	            if (mon_minutes.length() == 1) {
	            	mon_minutes = "0" + mon_minutes;  
	            }
	            if (mon_hrs.length() == 1) {
	            	mon_hrs = "0" + mon_hrs;  
	            }
	           
	            String monTime = mon_hrs + ":" + mon_minutes;
	            
	            
	         
	            String qtrly_hrs = request.getParameter("qtrly_hrs"); 
	            String qtrly_minutes = request.getParameter("qtrly_minutes"); 
	           
	            
	            if (qtrly_minutes.length() == 1) {
	            	qtrly_minutes = "0" + qtrly_minutes;  
	            }
	            if (qtrly_hrs.length() == 1) {
	            	qtrly_hrs = "0" + qtrly_hrs;  
	            }
	           
	            String qtrlyTime = qtrly_hrs + ":" + qtrly_minutes;
	            
	            
	           
	            String hyrly_hrs = request.getParameter("hyrly_hrs"); 
	            String hyrly_minutes = request.getParameter("hyrly_minutes"); 
	           
	            
	            if (hyrly_minutes.length() == 1) {
	            	hyrly_minutes = "0" + hyrly_minutes;  
	            }
	            if (hyrly_hrs.length() == 1) {
	            	hyrly_hrs = "0" + hyrly_hrs;  
	            }
	            
	            String hyrlyTime = hyrly_hrs + ":" + hyrly_minutes;

	           
	            String yrly_hrs = request.getParameter("yrly_hrs"); 
	            String yrly_minutes = request.getParameter("yrly_minutes"); 
	           
	            
	            if (yrly_minutes.length() == 1) {
	            	yrly_minutes = "0" + yrly_minutes;  
	            }
	            if (yrly_hrs.length() == 1) {
	            	yrly_hrs = "0" + yrly_hrs;  
	            }
	           
	            String yrlyTime = yrly_hrs + ":" + yrly_minutes;

	            rs.setTail_no(tail_no);
	            rs.setSus_no(sus_no);
	            rs.setUnit_name(unit_name);
	            rs.setEng_name(eng_type);
	            rs.setEng_ser_no(eng_ser_no);
	            rs.setEng_installation_date(eng_installed_date);
	            rs.setDate_of_acceptance_osft(date_of_acceptance_osft);
	            rs.setStd_nomclature(std_nomen);
	            rs.setCreated_by(username);
	            rs.setCreated_on(date);
	            rs.setCountry_isocode(countrycode);
	            rs.setPurchase_cost(purchasecost);
	            rs.setClassifications("1");
	            
                TB_AVIATION_RPAS_DAILY_BASIS dacr = new TB_AVIATION_RPAS_DAILY_BASIS();
	            
	            dacr.setAcc_no(tail_no);
	            dacr.setSus_no(sus_no);
	            dacr.setDate_goi_letter(date);
	            dacr.setAf_hrs(afTime);
	            dacr.setFalf_hrs_day("00:00");
	            dacr.setFalf_hrs_night("00:00");
	            dacr.setBal_hrs("00:00");
	            dacr.setG_run("00:00");
	            dacr.setHrs_left(hrsTime);
	            dacr.setHrs_monthly(monTime);
	            dacr.setHrs_qtrly(qtrlyTime);
	            dacr.setHrs_half_year(hyrlyTime);
	            dacr.setHrs_qtrly_flow(yrlyTime);
	            dacr.setNext_insp(insp);
	            dacr.setRemarks(remarks);
	            dacr.setEng_hrs(combinedEngineTime);
	            dacr.setEng_ser_no(eng_ser_no);
	            dacr.setAson_date(date);
	            dacr.setDays_left("0");

	            // Check if Tail No exists
	            if (addactDAO.ifExistRPASActNo(tail_no)) {
	                model.put("msg", "Tail No Already Exists.");
	                return new ModelAndView("redirect:add_tail_noRPAS");
	            } else {
	                sessionHql.beginTransaction();
	                sessionHql.save(rs);
	                sessionHql.save(dacr);
	                sessionHql.getTransaction().commit();
	                model.put("msg", "Tail No added successfully.");
	                return new ModelAndView("redirect:add_tail_noRPAS");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            sessionHql.getTransaction().rollback();
	            model.put("msg", "Error while saving the data.");
	            return new ModelAndView("redirect:add_tail_noRPAS");
	        } finally {
	            sessionHql.close();
	        }
	    }
	}
	//changes by Mitesh(03-12-2012)
	@RequestMapping(value = "/add_tail_noCHTLAction", method = RequestMethod.POST)
	public ModelAndView Add_act_noCHTLAction(@ModelAttribute("add_tail_noCHTLCMD") TB_AVIATION_CHTL_TAILNO_DTL rs,
	                                          HttpServletRequest request, ModelMap model, HttpSession session) {
	    String roleid = session.getAttribute("roleid").toString();
	    String username = session.getAttribute("username").toString();
	    String std_nomen = request.getParameter("std_nomen").toString();

	    if (std_nomen.equals("") || std_nomen.equals("null") || std_nomen.equals(null)) {
	        model.put("msg", "Please select type of aircraft.");
	        return new ModelAndView("redirect:add_tail_noCHTL");
	    } else {
	        Session sessionHql = HibernateUtil.getSessionFactory().openSession();
	        try {
	            Date date = new Date(System.currentTimeMillis());

	            String tail_no = request.getParameter("tail_no").trim();
	            String sus_no = request.getParameter("sus_no").trim();
	            String unit_name = request.getParameter("unit_name").trim();
	            String eng_type = request.getParameter("eng_type").trim();
	            String eng_ser_no = request.getParameter("eng_ser_no").trim();
	            String insp = request.getParameter("next_insp").trim();
	            String remarks = request.getParameter("remarks").trim();
	            String countrycode = request.getParameter("country_isocode").trim();
	            String purchasecost = request.getParameter("purchase_cost").trim();
	            
	           
	            String eng_hrs = request.getParameter("eng_hrs"); 
	            String eng_minutes = request.getParameter("eng_minutes"); 
	            System.out.println("Engine Hours: " + eng_hrs + ", Engine Minutes: " + eng_minutes);
	            
	            if (eng_minutes.length() == 1) {
	                eng_minutes = "0" + eng_minutes; 
	            }
	            if (eng_hrs.length() == 1) {
	            	eng_hrs = "0" + eng_hrs; 
	            }
	            
	            String combinedEngineTime = eng_hrs + ":" + eng_minutes;;
	            System.out.println("Combined Engine Hours: " + combinedEngineTime);

	           
	            rs.setEng_hrs(combinedEngineTime);

	           
	            String eng_installed_date_str = request.getParameter("eng_date");
	            String date_of_aos_str = request.getParameter("date_of_aos");
	            
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date eng_installed_date = null;
	            Date date_of_acceptance_osft = null;

	            if (eng_installed_date_str != null && !eng_installed_date_str.isEmpty()) {
	                eng_installed_date = new Date(dateFormat.parse(eng_installed_date_str).getTime());
	            }
	            if (date_of_aos_str != null && !date_of_aos_str.isEmpty()) {
	                date_of_acceptance_osft = new Date(dateFormat.parse(date_of_aos_str).getTime());
	            }
	            
	          
	            String af_hrs = request.getParameter("af_hrs"); 
	            String af_minutes = request.getParameter("af_minutes"); 
	            System.out.println("Engine Hours: " + af_hrs + ", Engine Minutes: " + af_minutes);
	            
	            if (af_minutes.length() == 1) {
	            	af_minutes = "0" + af_minutes;  
	            }
	            if (af_hrs.length() == 1) {
	            	af_hrs = "0" + af_hrs;  
	            }
	            
	            String afTime = af_hrs + ":" + af_minutes;
	            
	            
	            String hrs = request.getParameter("hrs_left"); 
	            String minutes = request.getParameter("minutes_left"); 
	           
	            
	            if (minutes.length() == 1) {
	            	minutes = "0" + minutes;  
	            }
	            if (hrs.length() == 1) {
	            	hrs = "0" + hrs;  
	            }
	            
	            String hrsTime = hrs + ":" + minutes;
	            
	            
	            String mon_hrs = request.getParameter("mon_hrs"); 
	            String mon_minutes = request.getParameter("mon_minutes"); 
	           
	            
	            if (mon_minutes.length() == 1) {
	            	mon_minutes = "0" + mon_minutes;  
	            }
	            if (mon_hrs.length() == 1) {
	            	mon_hrs = "0" + mon_hrs;  
	            }
	           
	            String monTime = mon_hrs + ":" + mon_minutes;
	            
	            
	         
	            String qtrly_hrs = request.getParameter("qtrly_hrs"); 
	            String qtrly_minutes = request.getParameter("qtrly_minutes"); 
	           
	            
	            if (qtrly_minutes.length() == 1) {
	            	qtrly_minutes = "0" + qtrly_minutes;  
	            }
	            if (qtrly_hrs.length() == 1) {
	            	qtrly_hrs = "0" + qtrly_hrs;  
	            }
	            
	            String qtrlyTime = qtrly_hrs + ":" + qtrly_minutes;
	            
	            
	            
	            String hyrly_hrs = request.getParameter("hyrly_hrs"); 
	            String hyrly_minutes = request.getParameter("hyrly_minutes"); 
	           
	            
	            if (hyrly_minutes.length() == 1) {
	            	hyrly_minutes = "0" + hyrly_minutes;  
	            }
	            if (hyrly_hrs.length() == 1) {
	            	hyrly_hrs = "0" + hyrly_hrs;  
	            }
	            
	            String hyrlyTime = hyrly_hrs + ":" + hyrly_minutes;

	            
	            String yrly_hrs = request.getParameter("yrly_hrs"); 
	            String yrly_minutes = request.getParameter("yrly_minutes"); 
	           
	            
	            if (yrly_minutes.length() == 1) {
	            	yrly_minutes = "0" + yrly_minutes;  
	            }
	            if (yrly_hrs.length() == 1) {
	            	yrly_hrs = "0" + yrly_hrs;  
	            }
	            
	            String yrlyTime = yrly_hrs + ":" + yrly_minutes;

	            
	            rs.setTail_no(tail_no);
	            rs.setSus_no(sus_no);
	            rs.setUnit_name(unit_name);
	            rs.setEng_name(eng_type);
	            rs.setEng_ser_no(eng_ser_no);
	            rs.setEng_installation_date(eng_installed_date);
	            rs.setDate_of_acceptance_osft(date_of_acceptance_osft);
	            rs.setStd_nomclature(std_nomen);
	            rs.setCreated_by(username);
	            rs.setCreated_on(date);
	            rs.setCountry_isocode(countrycode);
	            rs.setPurchase_cost(purchasecost);
	            rs.setClassifications("1");
	            
                TB_AVIATION_CHTL_DAILY_BASIS dacr = new TB_AVIATION_CHTL_DAILY_BASIS();
	            
	            dacr.setAcc_no(tail_no);
	            dacr.setSus_no(sus_no);
	            dacr.setDate_goi_letter(date);
	            dacr.setAf_hrs(afTime);
	            dacr.setFalf_hrs_day("00:00");
	            dacr.setFalf_hrs_night("00:00");
	            dacr.setBal_hrs("00:00");
	            dacr.setG_run("00:00");
	            dacr.setHrs_left(hrsTime);
	            dacr.setHrs_monthly(monTime);
	            dacr.setHrs_qtrly(qtrlyTime);
	            dacr.setHrs_half_year(hyrlyTime);
	            dacr.setHrs_qtrly_flow(yrlyTime);
	            dacr.setNext_insp(insp);
	            dacr.setRemarks(remarks);
	            dacr.setEng_hrs(combinedEngineTime);
	            dacr.setEng_ser_no(eng_ser_no);
	            dacr.setAson_date(date);
	            dacr.setDays_left("0");
	            
	            // Check if Tail No exists
	            if (addactDAO.ifExistCHTLActNo(tail_no)) {
	                model.put("msg", "Tail No Already Exists.");
	                return new ModelAndView("redirect:add_tail_noCHTL");
	            } else {
	                sessionHql.beginTransaction();
	                sessionHql.save(rs);
	                sessionHql.save(dacr);
	                sessionHql.getTransaction().commit();
	                model.put("msg", "Tail No added successfully.");
	                return new ModelAndView("redirect:add_tail_noCHTL");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            sessionHql.getTransaction().rollback();
	            model.put("msg", "Error while saving the data.");
	            return new ModelAndView("redirect:add_tail_noCHTL");
	        } finally {
	            sessionHql.close();
	        }
	    }
	}

	
	private String combineTime(String hours, String minutes) {
	    try {
	        
	        int engHrs = Integer.parseInt(hours);
	        int engMinutes = Integer.parseInt(minutes);

	        
	        if (engMinutes < 0 || engMinutes >= 60) {
	            return "00:00"; 
	        }

	        
	        int totalMinutes = (engHrs * 60) + engMinutes;

	        
	        int years = totalMinutes / (365 * 24 * 60);  
	        int remainingMinutesAfterYears = totalMinutes % (365 * 24 * 60);  

	        int days = remainingMinutesAfterYears / (24 * 60);  
	        int remainingMinutesAfterDays = remainingMinutesAfterYears % (24 * 60);  

	        int hoursForDisplay = remainingMinutesAfterDays / 60;  
	        int minutesForDisplay = remainingMinutesAfterDays % 60;  

	        
	        if (years > 0) {
	            
	            return String.format("%d:%02d:%02d:%02d", years, days, hoursForDisplay, minutesForDisplay);
	        } else {
	            
	            return String.format("%02d:%02d:%02d", days, hoursForDisplay, minutesForDisplay);
	        }
	    } catch (NumberFormatException e) {
	        
	        return "00:00";
	    }
	}
	
	
	@RequestMapping(value = "/Search_tail_no", method = RequestMethod.GET)
	public ModelAndView Search_tail_no(ModelMap Mmap, HttpSession session, 
	                                   @RequestParam(value = "msg", required = false) String msg,
	                                   HttpServletRequest request) {
	    
	    String roleid = session.getAttribute("roleid").toString();
	    Boolean val = roledao.ScreenRedirect("Search_tail_no", roleid);    
	    if (val == false) {
	        return new ModelAndView("AccessTiles");
	    }

	    
	    if (request.getHeader("Referer") == null) {
	        msg = "No referrer detected.";
	    }

	    Mmap.put("msg", msg); 
	    Mmap.put("getTailNoCurrentStatus", new ArrayList<ArrayList<String>>()); 
	    Mmap.put("getCHTLTailNoCurrentStatus", new ArrayList<ArrayList<String>>());
	    Mmap.put("getRPASTailNoCurrentStatus", new ArrayList<ArrayList<String>>());
	    return new ModelAndView("Search_tailnoTiles");
	}

	@RequestMapping(value = "/gettailNo", method = RequestMethod.POST)
	public @ResponseBody List<String> gettailNo(HttpSession sessionA, String tail_no) {
	    Session session = null;
	    Transaction tx = null;
	    List<String> finalList = new ArrayList<>();
	    
	    try {
	        
	        session = HibernateUtil.getSessionFactory().openSession();
	        tx = session.beginTransaction();

	        
	        String[] tableNames = {
	            "TB_AVIATION_TAILNO_DTL",
	            "TB_AVIATION_CHTL_TAILNO_DTL",
	            "TB_AVIATION_RPAS_TAILNO_DTL"
	        };

	        List<String> combinedList = new ArrayList<>();

	        
	        for (String tableName : tableNames) {
	            String queryStr = "select tail_no from " + tableName + 
	                              " where upper(tail_no) like :tail_no order by tail_no asc";
	            Query query = session.createQuery(queryStr).setMaxResults(10);
	            query.setParameter("tail_no", tail_no.toUpperCase() + "%");

	            @SuppressWarnings("unchecked")
	            List<String> list = (List<String>) query.list();
	            combinedList.addAll(list);
	        }

	       
	        tx.commit();

	       
	        String enckey = hex_asciiDao.getAlphaNumericString();
	        Cipher cipher = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);

	        
	        for (String tailNo : combinedList) {
	            try {
	                byte[] encCode = cipher.doFinal(tailNo.getBytes());
	                String base64EncodedEncryptedCode = new String(Base64.encode(encCode));
	                finalList.add(base64EncodedEncryptedCode);
	            } catch (IllegalBlockSizeException | BadPaddingException e) {
	                e.printStackTrace();
	            }
	        }

	        
	        finalList.add(enckey + "4bsjyg==");

	    } catch (Exception e) {
	        if (tx != null) tx.rollback();  
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();  
	        }
	    }

	    return finalList;
	}

	@RequestMapping(value = "/admin/Search_tailno_details", method = RequestMethod.POST)
	public ModelAndView Search_tailno_details(ModelMap Mmap, HttpSession session, 
	                                           @RequestParam(value = "msg", required = false) String msg,
	                                           @RequestParam(value = "tail_no1", required = false) String tail_no) { 
		
		String roleType = session.getAttribute("roleType").toString();

	    
	    if (tail_no == null || tail_no.isEmpty()) {
	        Mmap.put("msg", "Please Enter BA No.");
	    } else {
	    	
	    	Mmap.put("getTailNoCurrentStatus",addactDAO.getTailNoCurrentStatus(tail_no,roleType));
	    	Mmap.put("getCHTLTailNoCurrentStatus",addactDAO.getCHTLTailNoCurrentStatus(tail_no,roleType));
	    	Mmap.put("getRPASTailNoCurrentStatus",addactDAO.getRPASTailNoCurrentStatus(tail_no,roleType));
	         }
	    ArrayList<ArrayList<String>> list = new  ArrayList<ArrayList<String>>();
	    list = addactDAO.getRPASTailNoCurrentStatus(tail_no,roleType);
	    System.err.println("size---"+list.size());
	    Mmap.put("tail_no", tail_no);
	    return new ModelAndView("Search_tailnoTiles");
	}
	
	 @RequestMapping(value = "/admin/tail_noUpatation")
		public ModelAndView tail_noUpatation(@ModelAttribute("updatetail_no") String updatetail_no, ModelMap model,
				Authentication authentication, HttpSession sessionA) {
			String roleType = sessionA.getAttribute("roleType").toString();
			if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			session.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from TB_AVIATION_TAILNO_DTL where tail_no=:tail_no");
			q.setParameter("tail_no", updatetail_no);
			TB_AVIATION_TAILNO_DTL upid = (TB_AVIATION_TAILNO_DTL) q.list().get(0);
			tx.commit();

			model.put("edit_tail_noCMD", upid);
			return new ModelAndView("edit_tail_noTiles");
		}
	 
	 @RequestMapping(value = "/gettailNODetails", method = RequestMethod.POST)
	 public @ResponseBody ArrayList<List<String>> gettailNODetails(String tail_no, HttpSession sessionUserId) throws Exception {
	     int userId = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
	     ArrayList<List<String>> finalList = new ArrayList<>();
	     
	     String encKey = hex_asciiDao.getAlphaNumericString();
	     Cipher cipher = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, encKey);

	     Session session = null;
	     Transaction tx = null;

	     try {
	         session = HibernateUtil.getSessionFactory().openSession();
	         tx = session.beginTransaction();

	         
	         String queryStr = "select a.lh_eng_ser_no, a.rh_eng_ser_no, a.lh_eng_hrs, "
	                         + "a.rh_eng_hrs, a.std_nomclature, a.eng_name, "
	                         + "ltrim(TO_CHAR(a.date_of_acceptance_osft,'dd-mm-yyyy'),'') as date_of_acceptance_osft, "
	                         + "ltrim(TO_CHAR(a.lh_eng_installed_date,'dd-mm-yyyy'),'') as lh_eng_installed_date, "
	                         + "ltrim(TO_CHAR(a.rh_eng_installed_date,'dd-mm-yyyy'),'') as rh_eng_installed_date, "
	                         + "b.af_hrs, b.hrs_left, b.hrs_monthly, b.hrs_qtrly, b.hrs_half_year, b.hrs_qtrly_flow, "
	                         + "b.next_insp,b.remarks,b.sus_no "
	                         + "from tb_aviation_tail_no_dtl a "
	                         + "left join tb_aviation_daily_basis b on a.tail_no= b.acc_no "
	                         + "where tail_no = :tail_no";

	      
	         Query q = session.createSQLQuery(queryStr);  
	         q.setParameter("tail_no", tail_no);
	         List<Object[]> results = q.list();

	         if (!results.isEmpty()) {
	           
	             for (Object[] record : results) {
	                 List<String> encryptedData = new ArrayList<>();
	                 int columnsToAdd = 18 - record.length;
	                 for (int i = 0; i < columnsToAdd; i++) {
	                     encryptedData.add(""); 
	                 }

	                 
	                 for (Object value : record) {
	                     String encryptedValue = encryptData(value != null ? value.toString() : "", cipher);
	                     encryptedData.add(encryptedValue);
	                 }

	                 finalList.add(encryptedData);
	             }
	         }

	         
	         if (!results.isEmpty()) {
	             List<String> encKeyList = new ArrayList<>();
	             encKeyList.add(encKey + "YbFjyB==");
	             encKeyList.add(encKey + "HNTrgS==");
	             finalList.add(encKeyList);
	         }

	         tx.commit();

	     } catch (Exception e) {
	         if (tx != null) {
	             tx.rollback(); 
	         }
	         e.printStackTrace();
	         throw new RuntimeException("Error while processing aircraft details", e);
	     } finally {
	         if (session != null) {
	             session.close(); 
	         }
	     }

	     return finalList; 
	 }

	 // Helper method for encryption
	 private String encryptData(String data, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException {
	     byte[] encryptedData = cipher.doFinal(data.getBytes());
	     return new String(Base64.encode(encryptedData));
	 }

	 @RequestMapping(value = "/updatetailNoData", method = RequestMethod.POST)
	 public @ResponseBody String updateTailNoData(
	         @RequestParam String std_nomen,
	         @RequestParam String tail_no,
	         @RequestParam String unit_sus_no,
	         @RequestParam String unit_name,
	         @RequestParam String aircraft_type,
	         @RequestParam String eng_type,
	         @RequestParam String date_of_aos,
	         @RequestParam String lh_eng_ser_no,
	         @RequestParam String rh_eng_ser_no,
	         @RequestParam String lh_eng_hrs,
	         @RequestParam String lh_eng_minutes,
	         @RequestParam String rh_eng_hrs,
	         @RequestParam String rh_eng_minutes,
	         @RequestParam String lh_eng_date,
	         @RequestParam String rh_eng_date,
	         @RequestParam String af_hrs,
	         @RequestParam String af_minutes,
	         @RequestParam String hrs_left,
	         @RequestParam String minutes_left,
	         @RequestParam String mon_hrs,
	         @RequestParam String mon_minutes,
	         @RequestParam String qtrly_hrs,
	         @RequestParam String qtrly_minutes,
	         @RequestParam String hyrly_hrs,
	         @RequestParam String hyrly_minutes,
	         @RequestParam String yrly_hrs,
	         @RequestParam String yrly_minutes,
	         @RequestParam String next_insp,
	         @RequestParam String remarks,
	         ModelMap Mmap, HttpSession sessionA) {

	     Session sessionGet = null;
	     Transaction tx = null;
	     try {
	         // Start a session
	         sessionGet = HibernateUtilNA.getSessionFactory().openSession();
	         tx = sessionGet.beginTransaction();
	         
	         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	         Date parsedDate = dateFormat.parse(date_of_aos);  // Convert the string to Date
	         Date dateOfAcceptance = dateFormat.parse(date_of_aos);
	         Date lhEngDate = dateFormat.parse(lh_eng_date);
	         Date rhEngDate = dateFormat.parse(rh_eng_date);


	         String afHrs = af_hrs + ":" + af_minutes;
	         String lhHrs = lh_eng_hrs + ":" + lh_eng_minutes;
	         String rhHrs = rh_eng_hrs + ":" + rh_eng_minutes;
	         String Hrsleft = hrs_left + ":" + minutes_left;
	         String monHrs = mon_hrs + ":" + mon_minutes;
	         String qHrs = qtrly_hrs + ":" + qtrly_minutes;
	         String hyHrs = hyrly_hrs + ":" + hyrly_minutes;
	         String yHrs = yrly_hrs + ":" + yrly_minutes;

	         // Step 1: Check if the record exists
	         Query checkExistQuery = sessionGet.createQuery(
	                 "FROM TB_AVIATION_DAILY_BASIS WHERE acc_no = :tail_no");
	         checkExistQuery.setParameter("tail_no", tail_no);
	         TB_AVIATION_DAILY_BASIS existingRecord = (TB_AVIATION_DAILY_BASIS) checkExistQuery.uniqueResult();

	         if (existingRecord != null) {
	             // Record exists, perform update

	             Query query2 = sessionGet.createQuery(
	                     "UPDATE TB_AVIATION_DAILY_BASIS SET " +
	                             "af_hrs = :af_hrs, " +
	                             "hrs_left = :hrs_left, " +
	                             "hrs_monthly = :hrs_monthly, " +
	                             "hrs_qtrly = :hrs_qtrly, " +
	                             "hrs_half_year = :hrs_half_year, " +
	                             "hrs_qtrly_flow = :hrs_qtrly_flow, " +
	                             "next_insp = :next_insp, " +
	                             "remarks = :remarks, " +
	                             "aircraft_state = :aircraft_state, " +
	                             "sus_no = :sus_no " +
	                             "WHERE acc_no = :tail_no");
	             query2.setParameter("af_hrs", afHrs);
	             query2.setParameter("hrs_left", Hrsleft);
	             query2.setParameter("hrs_monthly", monHrs);
	             query2.setParameter("hrs_qtrly", qHrs);
	             query2.setParameter("hrs_half_year", hyHrs);
	             query2.setParameter("hrs_qtrly_flow", yHrs);
	             query2.setParameter("next_insp", next_insp);
	             query2.setParameter("remarks", remarks);
	             query2.setParameter("aircraft_state", aircraft_type);
	             query2.setParameter("sus_no", unit_sus_no);
	             query2.setParameter("tail_no", tail_no);
	             query2.executeUpdate();
	         } else {
	             // Record does not exist, perform insert
	             TB_AVIATION_DAILY_BASIS dacr = new TB_AVIATION_DAILY_BASIS();
	             dacr.setAcc_no(tail_no);
	             dacr.setSus_no(unit_sus_no);
	             dacr.setEng_hrs_left(lhHrs);
	             dacr.setEng_hrs_rigth(rhHrs);
	             dacr.setDate_goi_letter(parsedDate);
	             dacr.setAf_hrs(afHrs);
	             dacr.setFalf_hrs_day("00:00");
	             dacr.setFalf_hrs_night("00:00");
	             dacr.setBal_hrs("00:00");
	             dacr.setG_run("00:00");
	             dacr.setHrs_left(Hrsleft);
	             dacr.setHrs_monthly(monHrs);
	             dacr.setHrs_qtrly(qHrs);
	             dacr.setHrs_half_year(hyHrs);
	             dacr.setHrs_qtrly_flow(yHrs);
	             dacr.setNext_insp(next_insp);
	             dacr.setRemarks(remarks);
	             dacr.setLh_ser_no(lh_eng_ser_no);
	             dacr.setRh_ser_no(rh_eng_ser_no);
	             dacr.setAircraft_state(aircraft_type);

	             sessionGet.save(dacr);
	         }

	         // Step 2: Update other tables if needed (e.g., TB_AVIATION_TAIL_NO_DTL)
	         Query query1 = sessionGet.createQuery(
	                 "UPDATE TB_AVIATION_TAILNO_DTL SET " +
	                         "lh_eng_ser_no = :lh_eng_ser_no, " +
	                         "rh_eng_ser_no = :rh_eng_ser_no, " +
	                         "lh_eng_hrs = :lh_eng_hrs, " +
	                         "rh_eng_hrs = :rh_eng_hrs, " +
	                         "std_nomclature = :std_nomclature, " +
	                         "eng_name = :eng_name, " +
	                         "unit_name = :unit_name, " +
	                         "sus_no = :sus_no, " +
	                         "date_of_acceptance_osft = :date_of_acceptance_osft, " +
	                         "lh_eng_installed_date = :lh_eng_installed_date, " +
	                         "rh_eng_installed_date = :rh_eng_installed_date, " +
	                         "classifications = :classifications " +
	                         "WHERE tail_no = :tail_no");
	         query1.setParameter("lh_eng_ser_no", lh_eng_ser_no);
	         query1.setParameter("rh_eng_ser_no", rh_eng_ser_no);
	         query1.setParameter("lh_eng_hrs", lhHrs);
	         query1.setParameter("rh_eng_hrs", rhHrs);
	         query1.setParameter("std_nomclature", std_nomen);
	         query1.setParameter("eng_name", eng_type);
	         query1.setParameter("unit_name", unit_name);
	         query1.setParameter("sus_no", unit_sus_no);
	         query1.setParameter("date_of_acceptance_osft", new java.sql.Date(dateOfAcceptance.getTime()));  // Use java.sql.Date
	         query1.setParameter("lh_eng_installed_date", new java.sql.Date(lhEngDate.getTime()));  // Use java.sql.Date
	         query1.setParameter("rh_eng_installed_date", new java.sql.Date(rhEngDate.getTime()));  // Use java.sql.Date
	         query1.setParameter("classifications", "1");
	         query1.setParameter("tail_no", tail_no);
	         query1.executeUpdate();

	         
	         tx.commit();
	         return "Data Updated Successfully.";

	     } catch (Exception e) {
	         if (tx != null) {
	             tx.rollback();
	         }
	         e.printStackTrace();
	         return "Error updating data: " + e.getMessage();
	     } finally {
	         if (sessionGet != null) {
	             sessionGet.close();
	         }
	     }
	 }
	 
	 @RequestMapping(value = "/admin/CHTLtail_noUpatation")
	 public ModelAndView CHTLtail_noUpatation(@ModelAttribute("updatetail_no1") String updatetail_no, ModelMap model,
	         Authentication authentication, HttpSession sessionA) {
	     String roleType = sessionA.getAttribute("roleType").toString();
	     if (!roleType.equals("ALL") && !roleType.equals("DEO")) {
	         return new ModelAndView("AccessTiles");
	     }

	     Session session = HibernateUtilNA.getSessionFactory().openSession();
	     session.setFlushMode(FlushMode.ALWAYS);
	     Transaction tx = session.beginTransaction();
	     
	     
	     Query q = session.createQuery("from TB_AVIATION_CHTL_TAILNO_DTL where tail_no=:tail_no");
	     q.setParameter("tail_no", updatetail_no);
	     List<TB_AVIATION_CHTL_TAILNO_DTL> resultList = q.list();
	     tx.commit();

	     
	     if (resultList.isEmpty()) {
	         
	         model.put("errorMessage", "No records found for the provided tail_no.");
	         return new ModelAndView("errorPage");
	     }
	     
	     
	     TB_AVIATION_CHTL_TAILNO_DTL upid = resultList.get(0);

	     model.put("edit_CHTLtail_noCMD", upid);
	     return new ModelAndView("edit_CHTLtail_noTiles");
	 }

	 
	 @RequestMapping(value = "/getCHTLtailNODetails", method = RequestMethod.POST)
	 public @ResponseBody ArrayList<List<String>> getCHTLtailNODetails(String tail_no, HttpSession sessionUserId) throws Exception {
	     int userId = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
	     ArrayList<List<String>> finalList = new ArrayList<>();
	     
	     String encKey = hex_asciiDao.getAlphaNumericString();
	     Cipher cipher = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, encKey);

	     Session session = null;
	     Transaction tx = null;

	     try {
	         session = HibernateUtil.getSessionFactory().openSession();
	         tx = session.beginTransaction();

	         
	         String queryStr = "select a.eng_ser_no, a.eng_hrs,\r\n" + 
	         		"a.std_nomclature, a.eng_name, \r\n" + 
	         		"ltrim(TO_CHAR(a.date_of_acceptance_osft,'dd-mm-yyyy'),'') as date_of_acceptance_osft, \r\n" + 
	         		"ltrim(TO_CHAR(a.eng_installation_date,'dd-mm-yyyy'),'') as eng_installation_date, \r\n" + 
	         		"b.af_hrs, b.hrs_left, b.hrs_monthly, b.hrs_qtrly, b.hrs_half_year, b.hrs_qtrly_flow, \r\n" + 
	         		"b.next_insp,b.remarks,b.sus_no \r\n" + 
	         		"from tb_aviation_chtl_tail_no_dtl a \r\n" + 
	         		"left join tb_aviation_chtl_daily_basis b on a.tail_no= b.acc_no \r\n" + 
	         		"where a.tail_no = :tail_no";

	         
	         Query q = session.createSQLQuery(queryStr);  // Use createSQLQuery for SQL
	         q.setParameter("tail_no", tail_no);
	         List<Object[]> results = q.list();

	         if (!results.isEmpty()) {
	             
	             for (Object[] record : results) {
	                 List<String> encryptedData = new ArrayList<>();
	                 int columnsToAdd = 15 - record.length; 
	                 for (int i = 0; i < columnsToAdd; i++) {
	                     encryptedData.add(""); 
	                 }

	                 
	                 for (Object value : record) {
	                     String encryptedValue = encryptData(value != null ? value.toString() : "", cipher);
	                     encryptedData.add(encryptedValue);
	                 }

	                 finalList.add(encryptedData);
	             }
	         }

	        
	         if (!results.isEmpty()) {
	             List<String> encKeyList = new ArrayList<>();
	             encKeyList.add(encKey + "YbFjyB==");
	             encKeyList.add(encKey + "HNTrgS==");
	             finalList.add(encKeyList);
	         }

	         tx.commit();

	     } catch (Exception e) {
	         if (tx != null) {
	             tx.rollback(); 
	         }
	         e.printStackTrace();
	         throw new RuntimeException("Error while processing aircraft details", e);
	     } finally {
	         if (session != null) {
	             session.close(); 
	         }
	     }

	     return finalList; 
	 }
	 
	 @RequestMapping(value = "/updateCHTLtailNoData", method = RequestMethod.POST)
	 public @ResponseBody String updateCHTLtailNoData(
	         @RequestParam String std_nomen,
	         @RequestParam String tail_no,
	         @RequestParam String unit_sus_no,
	         @RequestParam String unit_name,
	         @RequestParam String eng_type,
	         @RequestParam String eng_ser_no,
	         @RequestParam String eng_hrs,
	         @RequestParam String eng_minutes,
	         @RequestParam String eng_date,
	         @RequestParam String date_of_aos,
	         @RequestParam String af_hrs,
	         @RequestParam String af_minutes,
	         @RequestParam String hrs_left,
	         @RequestParam String minutes_left,
	         @RequestParam String mon_hrs,
	         @RequestParam String mon_minutes,
	         @RequestParam String qtrly_hrs,
	         @RequestParam String qtrly_minutes,
	         @RequestParam String hyrly_hrs,
	         @RequestParam String hyrly_minutes,
	         @RequestParam String yrly_hrs,
	         @RequestParam String yrly_minutes,
	         @RequestParam String next_insp,
	         @RequestParam String remarks ) {
	     
	     Session sessionGet = null;
	     Transaction tx = null;
	     Date dt = new Date();
	     try {
	         
	         sessionGet = HibernateUtilNA.getSessionFactory().openSession();
	         tx = sessionGet.beginTransaction();
	         
	         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	         Date parsedDate = dateFormat.parse(date_of_aos); 
	         Date engDate = dateFormat.parse(eng_date);
	         
	         
	         String afHrs = af_hrs + ":" + af_minutes;
	         String engHrs = eng_hrs + ":" + eng_minutes;
	         String HrsLeft = hrs_left + ":" + minutes_left;
	         String monHrs = mon_hrs + ":" + mon_minutes;
	         String qtrlyHrs = qtrly_hrs + ":" + qtrly_minutes;
	         String hyrlyHrs = hyrly_hrs + ":" + hyrly_minutes;
	         String yrlyHrs = yrly_hrs + ":" + yrly_minutes;

	        
	         Query checkExistQuery = sessionGet.createQuery("FROM TB_AVIATION_CHTL_DAILY_BASIS WHERE acc_no = :tail_no");
	         checkExistQuery.setParameter("tail_no", tail_no);
	         TB_AVIATION_CHTL_DAILY_BASIS existingRecord = (TB_AVIATION_CHTL_DAILY_BASIS) checkExistQuery.uniqueResult();
	         
	         if (existingRecord != null) {
	             
	             Query updateQuery = sessionGet.createQuery("UPDATE TB_AVIATION_CHTL_DAILY_BASIS SET " +
	                     "af_hrs = :af_hrs, " +
	                     "hrs_left = :hrs_left, " +
	                     "hrs_monthly = :hrs_monthly, " +
	                     "hrs_qtrly = :hrs_qtrly, " +
	                     "hrs_half_year = :hrs_half_year, " +
	                     "hrs_qtrly_flow = :hrs_qtrly_flow, " +
	                     "next_insp = :next_insp, " +
	                     "remarks = :remarks, " +
	                     "eng_hrs = :eng_hrs, " +
	                     "eng_ser_no = :eng_ser_no, " +
	                     "sus_no = :sus_no " +
	                     "WHERE acc_no = :tail_no");
	             updateQuery.setParameter("af_hrs", afHrs);
	             updateQuery.setParameter("hrs_left", HrsLeft);
	             updateQuery.setParameter("hrs_monthly", monHrs);
	             updateQuery.setParameter("hrs_qtrly", qtrlyHrs);
	             updateQuery.setParameter("hrs_half_year", hyrlyHrs);
	             updateQuery.setParameter("hrs_qtrly_flow", yrlyHrs);
	             updateQuery.setParameter("next_insp", next_insp);
	             updateQuery.setParameter("remarks", remarks);
	             updateQuery.setParameter("eng_hrs", engHrs);
	             updateQuery.setParameter("eng_ser_no", eng_ser_no);
	             updateQuery.setParameter("sus_no", unit_sus_no);
	             updateQuery.setParameter("tail_no", tail_no);
	             updateQuery.executeUpdate();
	             
	         } else {
	             
	        	 TB_AVIATION_CHTL_DAILY_BASIS dacr = new TB_AVIATION_CHTL_DAILY_BASIS();
		            
		            dacr.setAcc_no(tail_no);
		            dacr.setSus_no(unit_sus_no);
		            dacr.setDate_goi_letter(dt);
		            dacr.setAf_hrs(afHrs);
		            dacr.setFalf_hrs_day("00:00");
		            dacr.setFalf_hrs_night("00:00");
		            dacr.setBal_hrs("00:00");
		            dacr.setG_run("00:00");
		            dacr.setHrs_left(HrsLeft);
		            dacr.setHrs_monthly(monHrs);
		            dacr.setHrs_qtrly(qtrlyHrs);
		            dacr.setHrs_half_year(hyrlyHrs);
		            dacr.setHrs_qtrly_flow(yrlyHrs);
		            dacr.setNext_insp(next_insp);
		            dacr.setRemarks(remarks);
		            dacr.setEng_hrs(engHrs);
		            dacr.setEng_ser_no(eng_ser_no);
		            dacr.setAson_date(dt);
		            dacr.setDays_left("0");
	             
	             sessionGet.save(dacr);
	         }
	         
	     
	         Query query1 = sessionGet.createQuery(
	                 "UPDATE TB_AVIATION_CHTL_TAILNO_DTL SET " +
	                         "eng_ser_no = :eng_ser_no, " +
	                         "eng_hrs = :eng_hrs, " +
	                         "std_nomclature = :std_nomclature, " +
	                         "eng_name = :eng_name, " +
	                         "unit_name = :unit_name, " +
	                         "sus_no = :sus_no, " +
	                         "date_of_acceptance_osft = :date_of_acceptance_osft, " +
	                         "eng_installation_date = :eng_installation_date, " +
	                         "classifications = :classifications " +
	                         "WHERE tail_no = :tail_no");
	         query1.setParameter("eng_ser_no", eng_ser_no);
	         query1.setParameter("eng_hrs", eng_hrs);
	         query1.setParameter("std_nomclature", std_nomen);
	         query1.setParameter("eng_name", eng_type);
	         query1.setParameter("unit_name", unit_name);
	         query1.setParameter("sus_no", unit_sus_no);
	         query1.setParameter("date_of_acceptance_osft", new java.sql.Date(parsedDate.getTime()));  // Use java.sql.Date
	         query1.setParameter("eng_installation_date", new java.sql.Date(engDate.getTime()));  // Use java.sql.Date
	         query1.setParameter("classifications", "1");
	         query1.setParameter("tail_no", tail_no);
	         query1.executeUpdate();

	         
	         tx.commit();
	         return "Data Updated Successfully.";

	     } catch (Exception e) {
	         if (tx != null) {
	             tx.rollback();
	         }
	         e.printStackTrace();
	         return "Error updating data: " + e.getMessage();
	     } finally {
	         if (sessionGet != null) {
	             sessionGet.close();
	         }
	     }
	 }

	 @RequestMapping(value = "/admin/RPAStail_noUpatation")
	 public ModelAndView RPAStail_noUpatation(@ModelAttribute("updatetail_no2") String updatetail_no, ModelMap model,
	         Authentication authentication, HttpSession sessionA) {
	     String roleType = sessionA.getAttribute("roleType").toString();
	     if (!roleType.equals("ALL") && !roleType.equals("DEO")) {
	         return new ModelAndView("AccessTiles");
	     }

	     Session session = HibernateUtilNA.getSessionFactory().openSession();
	     session.setFlushMode(FlushMode.ALWAYS);
	     Transaction tx = session.beginTransaction();
	     
	    
	     Query q = session.createQuery("from TB_AVIATION_RPAS_TAILNO_DTL where tail_no=:tail_no");
	     q.setParameter("tail_no", updatetail_no);
	     List<TB_AVIATION_RPAS_TAILNO_DTL> resultList = q.list();
	     tx.commit();

	     
	     if (resultList.isEmpty()) {
	         
	         model.put("errorMessage", "No records found for the provided tail_no.");
	         return new ModelAndView("errorPage");
	     }
	     
	     
	     TB_AVIATION_RPAS_TAILNO_DTL upid = resultList.get(0);

	     model.put("edit_RPAStail_noCMD", upid);
	     return new ModelAndView("edit_RPAStail_noTiles");
	 }
	 
	 
	 @RequestMapping(value = "/getRPAStailNODetails", method = RequestMethod.POST)
	 public @ResponseBody ArrayList<List<String>> getRPAStailNODetails(String tail_no, HttpSession sessionUserId) throws Exception {
	     int userId = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
	     ArrayList<List<String>> finalList = new ArrayList<>();
	     
	     String encKey = hex_asciiDao.getAlphaNumericString();
	     Cipher cipher = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, encKey);

	     Session session = null;
	     Transaction tx = null;

	     try {
	         session = HibernateUtil.getSessionFactory().openSession();
	         tx = session.beginTransaction();

	        
	         String queryStr = "select a.eng_ser_no, a.eng_hrs,\r\n" + 
	         		"a.std_nomclature, a.eng_name, \r\n" + 
	         		"ltrim(TO_CHAR(a.date_of_acceptance_osft,'dd-mm-yyyy'),'') as date_of_acceptance_osft, \r\n" + 
	         		"ltrim(TO_CHAR(a.eng_installation_date,'dd-mm-yyyy'),'') as eng_installation_date, \r\n" + 
	         		"b.af_hrs, b.hrs_left, b.hrs_monthly, b.hrs_qtrly, b.hrs_half_year, b.hrs_qtrly_flow, \r\n" + 
	         		"b.next_insp,b.remarks,b.sus_no \r\n" + 
	         		"from tb_aviation_rpas_tail_no_dtl a \r\n" + 
	         		"left join tb_aviation_rpas_daily_basis b on a.tail_no= b.acc_no \r\n" + 
	         		"where a.tail_no = :tail_no";

	         
	         Query q = session.createSQLQuery(queryStr);  
	         q.setParameter("tail_no", tail_no);
	         List<Object[]> results = q.list();

	         if (!results.isEmpty()) {
	             
	             for (Object[] record : results) {
	                 List<String> encryptedData = new ArrayList<>();
	                 int columnsToAdd = 15 - record.length; 
	                 for (int i = 0; i < columnsToAdd; i++) {
	                     encryptedData.add(""); 
	                 }

	                
	                 for (Object value : record) {
	                     String encryptedValue = encryptData(value != null ? value.toString() : "", cipher);
	                     encryptedData.add(encryptedValue);
	                 }

	                 finalList.add(encryptedData);
	             }
	         }

	        
	         if (!results.isEmpty()) {
	             List<String> encKeyList = new ArrayList<>();
	             encKeyList.add(encKey + "YbFjyB==");
	             encKeyList.add(encKey + "HNTrgS==");
	             finalList.add(encKeyList);
	         }

	         tx.commit();

	     } catch (Exception e) {
	         if (tx != null) {
	             tx.rollback(); 
	         }
	         e.printStackTrace();
	         throw new RuntimeException("Error while processing aircraft details", e);
	     } finally {
	         if (session != null) {
	             session.close(); 
	         }
	     }

	     return finalList; 
	 }
	 
	 @RequestMapping(value = "/updateRPAStailNoData", method = RequestMethod.POST)
	 public @ResponseBody String updateRPAStailNoData(
	         @RequestParam String std_nomen,
	         @RequestParam String tail_no,
	         @RequestParam String unit_sus_no,
	         @RequestParam String unit_name,
	         @RequestParam String eng_type,
	         @RequestParam String eng_ser_no,
	         @RequestParam String eng_hrs,
	         @RequestParam String eng_minutes,
	         @RequestParam String eng_date,
	         @RequestParam String date_of_aos,
	         @RequestParam String af_hrs,
	         @RequestParam String af_minutes,
	         @RequestParam String hrs_left,
	         @RequestParam String minutes_left,
	         @RequestParam String mon_hrs,
	         @RequestParam String mon_minutes,
	         @RequestParam String qtrly_hrs,
	         @RequestParam String qtrly_minutes,
	         @RequestParam String hyrly_hrs,
	         @RequestParam String hyrly_minutes,
	         @RequestParam String yrly_hrs,
	         @RequestParam String yrly_minutes,
	         @RequestParam String next_insp,
	         @RequestParam String remarks ) {
	     
	     Session sessionGet = null;
	     Transaction tx = null;
	     Date dt = new Date();
	     try {
	         
	         sessionGet = HibernateUtilNA.getSessionFactory().openSession();
	         tx = sessionGet.beginTransaction();
	         
	         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	         Date parsedDate = dateFormat.parse(date_of_aos);  
	         Date engDate = dateFormat.parse(eng_date);
	         
	         
	         String afHrs = af_hrs + ":" + af_minutes;
	         String engHrs = eng_hrs + ":" + eng_minutes;
	         String HrsLeft = hrs_left + ":" + minutes_left;
	         String monHrs = mon_hrs + ":" + mon_minutes;
	         String qtrlyHrs = qtrly_hrs + ":" + qtrly_minutes;
	         String hyrlyHrs = hyrly_hrs + ":" + hyrly_minutes;
	         String yrlyHrs = yrly_hrs + ":" + yrly_minutes;

	        
	         Query checkExistQuery = sessionGet.createQuery("FROM TB_AVIATION_RPAS_DAILY_BASIS WHERE acc_no = :tail_no");
	         checkExistQuery.setParameter("tail_no", tail_no);
	         TB_AVIATION_RPAS_DAILY_BASIS existingRecord = (TB_AVIATION_RPAS_DAILY_BASIS) checkExistQuery.uniqueResult();
	         
	         if (existingRecord != null) {
	             
	             Query updateQuery = sessionGet.createQuery("UPDATE TB_AVIATION_RPAS_DAILY_BASIS SET " +
	                     "af_hrs = :af_hrs, " +
	                     "hrs_left = :hrs_left, " +
	                     "hrs_monthly = :hrs_monthly, " +
	                     "hrs_qtrly = :hrs_qtrly, " +
	                     "hrs_half_year = :hrs_half_year, " +
	                     "hrs_qtrly_flow = :hrs_qtrly_flow, " +
	                     "next_insp = :next_insp, " +
	                     "remarks = :remarks, " +
	                     "eng_hrs = :eng_hrs, " +
	                     "eng_ser_no = :eng_ser_no, " +
	                     "sus_no = :sus_no " +
	                     "WHERE acc_no = :tail_no");
	             updateQuery.setParameter("af_hrs", afHrs);
	             updateQuery.setParameter("hrs_left", HrsLeft);
	             updateQuery.setParameter("hrs_monthly", monHrs);
	             updateQuery.setParameter("hrs_qtrly", qtrlyHrs);
	             updateQuery.setParameter("hrs_half_year", hyrlyHrs);
	             updateQuery.setParameter("hrs_qtrly_flow", yrlyHrs);
	             updateQuery.setParameter("next_insp", next_insp);
	             updateQuery.setParameter("remarks", remarks);
	             updateQuery.setParameter("eng_hrs", engHrs);
	             updateQuery.setParameter("eng_ser_no", eng_ser_no);
	             updateQuery.setParameter("sus_no", unit_sus_no);
	             updateQuery.setParameter("tail_no", tail_no);
	             updateQuery.executeUpdate();
	             
	         } else {
	             // Record does not exist, perform insert
	        	 TB_AVIATION_RPAS_DAILY_BASIS dacr = new TB_AVIATION_RPAS_DAILY_BASIS();
		            
		            dacr.setAcc_no(tail_no);
		            dacr.setSus_no(unit_sus_no);
		            dacr.setDate_goi_letter(dt);
		            dacr.setAf_hrs(afHrs);
		            dacr.setFalf_hrs_day("00:00");
		            dacr.setFalf_hrs_night("00:00");
		            dacr.setBal_hrs("00:00");
		            dacr.setG_run("00:00");
		            dacr.setHrs_left(HrsLeft);
		            dacr.setHrs_monthly(monHrs);
		            dacr.setHrs_qtrly(qtrlyHrs);
		            dacr.setHrs_half_year(hyrlyHrs);
		            dacr.setHrs_qtrly_flow(yrlyHrs);
		            dacr.setNext_insp(next_insp);
		            dacr.setRemarks(remarks);
		            dacr.setEng_hrs(engHrs);
		            dacr.setEng_ser_no(eng_ser_no);
		            dacr.setAson_date(dt);
		            dacr.setDays_left("0");
	             
	             sessionGet.save(dacr);
	         }
	         
	    
	         Query query1 = sessionGet.createQuery(
	                 "UPDATE TB_AVIATION_RPAS_TAILNO_DTL SET " +
	                         "eng_ser_no = :eng_ser_no, " +
	                         "eng_hrs = :eng_hrs, " +
	                         "std_nomclature = :std_nomclature, " +
	                         "eng_name = :eng_name, " +
	                         "unit_name = :unit_name, " +
	                         "sus_no = :sus_no, " +
	                         "date_of_acceptance_osft = :date_of_acceptance_osft, " +
	                         "eng_installation_date = :eng_installation_date, " +
	                         "classifications = :classifications " +
	                         "WHERE tail_no = :tail_no");
	         query1.setParameter("eng_ser_no", eng_ser_no);
	         query1.setParameter("eng_hrs", eng_hrs);
	         query1.setParameter("std_nomclature", std_nomen);
	         query1.setParameter("eng_name", eng_type);
	         query1.setParameter("unit_name", unit_name);
	         query1.setParameter("sus_no", unit_sus_no);
	         query1.setParameter("date_of_acceptance_osft", new java.sql.Date(parsedDate.getTime()));  // Use java.sql.Date
	         query1.setParameter("eng_installation_date", new java.sql.Date(engDate.getTime()));  // Use java.sql.Date
	         query1.setParameter("classifications", "1");
	         query1.setParameter("tail_no", tail_no);
	         query1.executeUpdate();

	         
	         tx.commit();
	         return "Data Updated Successfully.";

	     } catch (Exception e) {
	         if (tx != null) {
	             tx.rollback();
	         }
	         e.printStackTrace();
	         return "Error updating data: " + e.getMessage();
	     } finally {
	         if (sessionGet != null) {
	             sessionGet.close();
	         }
	     }
	 }


}
