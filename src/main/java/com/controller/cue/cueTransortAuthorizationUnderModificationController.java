package com.controller.cue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import com.dao.cue.TransportAuthorizationUnderModificationDAO;
import com.dao.cue.TransportAuthorizationUnderModificationDAOImpl;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_MDFS;
import com.models.CUE_TB_MISO_WEPE_WEAPONS_MDFS;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class cueTransortAuthorizationUnderModificationController {
	@Autowired
	private TransportAuthorizationUnderModificationDAO transportDAO;

	@Autowired
	private RoleBaseMenuDAO roledao ;
	@Autowired
	private Cue_wepe_conditionDAO vetting;
	
	weapController m = new weapController();
	
	@Autowired
	private WepePersMdfsDAO wepepersmdfs;	
	ValidationController validation = new ValidationController();
	@RequestMapping(value = "/transportAuthorizationUnderModification", method = RequestMethod.GET)
	public ModelAndView transportAuthorizationUnderModification(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("transportAuthorizationUnderModification", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getLocation1", m.getLocation1());
//		Mmap.put("getFormation1", m.getFormation1());
		Mmap.put("getUnitUrl1", m.getUnitUrl1());
		return new ModelAndView("transportAuthorizationUnderModificationTiles","transportAuthorizationUnderModificationCMD", new CUE_TB_MISO_WEPE_TRANSPORT_MDFS());
	}
	@RequestMapping(value = "/admin/transportAuthorizationUnderModification1", method = RequestMethod.POST)
	public ModelAndView transportAuthorizationUnderModification1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "mct_no1", required = false) String mct_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "we_pe1", required = false) String we_pe,
			@RequestParam(value = "modification1", required = false) String modification,
			@RequestParam(value = "scenario1", required = false) String scenario,
			@RequestParam(value = "location1", required = false) String location,
			@RequestParam(value = "formation1", required = false) String formation,

			@RequestParam(value = "location1_hid", required = false) String location_code,
			@RequestParam(value = "formation1_hid", required = false) String formation_code,
			@RequestParam(value = "unit1_hid", required = false) String unit_code,
			@RequestParam(value = "unit1", required = false) String unit){
			String roleType = session.getAttribute("roleType").toString();
			
			Mmap.put("we_pe1", we_pe);
			Mmap.put("scenario1", scenario);
			Mmap.put("location1", location);
			Mmap.put("formation1", formation);
			Mmap.put("unit1", unit);
			Mmap.put("location1_hid", location_code);
			Mmap.put("formation1_hid", formation_code);
			Mmap.put("unit1_hid", unit_code);
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("modification1", modification);
			Mmap.put("mct_no1", mct_no);
		
			 List<Map<String, Object>> list= transportDAO.getAttributeFromTransportModification1(we_pe_no,modification,mct_no,status,roleType);
			
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("getLocation1", m.getLocation1());
//			Mmap.put("getFormation1", m.getFormation1());
			Mmap.put("getUnitUrl1", m.getUnitUrl1());
			
		Mmap.put("roleType", roleType);		
		return new ModelAndView("transportAuthorizationUnderModificationTiles");
	}
	
	@RequestMapping(value = "/transportAuthorizationUnderModificationAction", method = RequestMethod.POST)
	public ModelAndView addItemEntryForm(@ModelAttribute("transportAuthorizationUnderModificationCMD") CUE_TB_MISO_WEPE_TRANSPORT_MDFS rs,
			HttpServletRequest request,ModelMap model,HttpSession session) {
		String r = request.getParameter("inc_dec");
		String we_pe = request.getParameter("we_pe");
		String we_pe_no= request.getParameter("we_pe_no");
		String location_code = request.getParameter("Location_a");
		String formation_code = request.getParameter("formation");
		String unit_code = request.getParameter("Unit_a");
		String remarks = request.getParameter("remarks");
		String amt_inc_dec = request.getParameter("amt_inc_dec");
		
		String mod = request.getParameter("modification");
		String std_nomnclature = request.getParameter("std_nomclature");
		String mct_no = request.getParameter("mct_no");
		String table_title=request.getParameter("table_title");
		int a=0;
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx0 = sessionHQL.beginTransaction();
			
		try
		{
		 if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) )
		{
			model.put("msg", "Please Select Document");
			return new ModelAndView("redirect:transportAuthorizationUnderModification");
		}
		 if(rs.getWe_pe_no() == "")
			{
				model.put("msg", "Please Enter WE/PE No");
				return new ModelAndView("redirect:transportAuthorizationUnderModification");
			}
		 if(validation.checkWepeLength(rs.getWe_pe_no())  == false){
		 		model.put("msg",validation.wepenoMSG);
				return new ModelAndView("redirect:transportAuthorizationUnderModification");
			}
			if(validation.checkMctLength(rs.getMct_no())  == false){
		 		model.put("msg",validation.mctnoMSG);
				return new ModelAndView("redirect:transportAuthorizationUnderModification");
			}
			if(validation.checkScenarioLength(rs.getScenario())  == false){
		 		model.put("msg",validation.senarioMSG);
				return new ModelAndView("redirect:transportAuthorizationUnderModification");
			}
			if(validation.checkRemarksLength(rs.getRemarks())  == false){
				model.put("msg",validation.remarksMSG);
				return new ModelAndView("redirect:transportAuthorizationUnderModification");
			}
			if(validation.checkWepetabletittleLength(rs.getStd_nomclature())  == false){
				model.put("msg",validation.nomenMSG);
				return new ModelAndView("redirect:transportAuthorizationUnderModification");
			}
			if(validation.checkModificationLength(rs.getModification())  == false){
				model.put("msg",validation.modMSG);
				return new ModelAndView("redirect:transportAuthorizationUnderModification");
			}
			if(validation.checkIncDecLength(rs.getInc_dec())  == false){
				model.put("msg",validation.incdecMSG);
				return new ModelAndView("redirect:transportAuthorizationUnderModification");
			}
			 
				 int lenval=0;
				  if(request.getParameter("inc_dec").equals("I"))
					  lenval = 8;
				  else if(request.getParameter("inc_dec").equals("D"))
					  lenval = 9;
				  
				  String amt_inc_dec_valid =  Double.toString(rs.getAmt_inc_dec());
				  if(validation.checkAmt_inc_decLength(amt_inc_dec_valid,lenval)  == false){
						model.put("msg",validation.amt_inc_decMSG);
						return new ModelAndView("redirect:transportAuthorizationUnderModification");
					}
			if(rs.getScenario() == "")
			{
				model.put("msg", "Please Select Scenario");
				return new ModelAndView("redirect:transportAuthorizationUnderModification");
			}
			String scenario2 = rs.getScenario();
			if(scenario2.equals("Location"))
			{
				if(location_code.equals(""))
				{
					model.put("msg", "Please Enter Location");
					return new ModelAndView("redirect:transportAuthorizationUnderModification");
				
				}
//				if(validation.checkLocationLength(rs.getLocation())  == false){
//					model.put("msg",validation.locMSG);
//					return new ModelAndView("redirect:transportAuthorizationUnderModification");
//				}
				
			}
			if(scenario2.equals("Formation"))
			{
				if(rs.getFormation().equals(""))
				{
					model.put("msg", "Please Enter Formation");
					return new ModelAndView("redirect:transportAuthorizationUnderModification");
				
				}
				if(validation.checkFormationLength(rs.getFormation())  == false){
					model.put("msg",validation.formMSG);
					return new ModelAndView("redirect:transportAuthorizationUnderModification");
				}
			}
			if(scenario2.equals("Unit"))
			{
				if(unit_code.equals(""))
				{
					model.put("msg", "Please Enter Unit");
					return new ModelAndView("redirect:transportAuthorizationUnderModification");
				
				}
//				if(validation.sus_noLength(rs.getScenario_unit())  == false){
//					model.put("msg",validation.unitMSG);
//					return new ModelAndView("redirect:transportAuthorizationUnderModification");
//				}
			}
			if(rs.getModification() == "")
			{
				model.put("msg", "Please Enter Modification");
				return new ModelAndView("redirect:transportAuthorizationUnderModification");
			}
			if(rs.getMct_no() == "")
			{
				model.put("msg", "Please Enter MCT No");
				return new ModelAndView("redirect:transportAuthorizationUnderModification");
			}
			if(rs.getStd_nomclature() == "")
			{
				model.put("msg", "Please Enter Standard Nomenclature");
				return new ModelAndView("redirect:transportAuthorizationUnderModification");
			}
			 if(r == ""  || r==null || r=="null" || r.equals(null) )
				{
					model.put("msg", "Please Select Increment/Decrement");
					return new ModelAndView("redirect:transportAuthorizationUnderModification");
				}
			  if(rs.getAmt_inc_dec() == 0.0 )
				{
					model.put("msg", "Please Enter Amount of Increment/Decrement");
					return new ModelAndView("redirect:transportAuthorizationUnderModification");
				}
				int roleid = (Integer)session.getAttribute("roleid");			
					
				
				String username = session.getAttribute("username").toString();
				String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				String modifieddate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				
				int amt ;	
				if(request.getParameter("amt_inc_dec") == null)
				{
					amt = 0;
				}
				else
				{
					amt = Integer.parseInt(request.getParameter("amt_inc_dec"));	
				}
				
				
				///------ checking of existence of pair we_pe_no and mct_no------------------------------------------------------------------------------//
				Session session2 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx3 = session2.beginTransaction();		
				Query q = session2.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANSPORT_DET where we_pe_no=:we_pe_no and mct_no=:mct_no");
				q.setParameter("we_pe_no", we_pe_no);
				q.setParameter("mct_no", mct_no);
				@SuppressWarnings("unchecked")
				Long count1 = (Long)q.uniqueResult();
				model.put("count", count1);
				tx3.commit();
				session2.close();
				
				if(count1 == 0)
				{
					CUE_TB_MISO_WEPE_TRANSPORT_DET rs1 = new CUE_TB_MISO_WEPE_TRANSPORT_DET();		
					
					
					int auth_amt = rs1.getAuth_amt();
					rs1.setAuth_amt(0);
					rs1.setWe_pe_no(request.getParameter("we_pe_no"));
					rs1.setCreated_by(username);
					rs1.setCreated_on(creadtedate);
					rs1.setMct_no(request.getParameter("mct_no"));		
					rs1.setRemarks(request.getParameter("remarks"));
					rs1.setAuth_amt(auth_amt);
					rs1.setEntity("UNIT");
					rs1.setStatus("1");
					
					String scenario = rs.getScenario();
					if(scenario.equals("Location")) {
						String location_b []= location_code.split(",");
						for(String locationA:location_b)
						{
						Session sessionHQL4 = HibernateUtil.getSessionFactory().openSession();
						sessionHQL4.beginTransaction();
						
						CUE_TB_MISO_WEPE_TRANSPORT_MDFS rsl = new CUE_TB_MISO_WEPE_TRANSPORT_MDFS();		
						rsl.setLocation(locationA);
						rsl.setFormation(null);
						rsl.setScenario_unit(null);
						rsl.setScenario(scenario);
						rsl.setCreated_by(username);
						rsl.setCreated_on(creadtedate);
						rsl.setModified_by(username);
						rsl.setModified_on(modifieddate);
						rsl.setAprv_rejc_by(username);
						rsl.setDate_of_apprv_rejc(creadtedate);
						rsl.setMct_no(mct_no);
						rsl.setModification(mod);
						rsl.setStatus("0");
						rsl.setRemarks(remarks);
						rsl.setRoleid(roleid);
						rsl.setStd_nomclature(std_nomnclature);
						rsl.setInc_dec(r);
						rsl.setVersion_no("0");
						rsl.setWe_pe_no(we_pe_no);
						rsl.setTable_title(table_title);
						
						amt = Integer.parseInt(amt_inc_dec) ;
						if(r.equals("D"))
						{
							String sum = "-" + amt;	
							
							rsl.setAmt_inc_dec(Integer.parseInt(sum));
						}
						else {
							
							rsl.setAmt_inc_dec(amt);
						}	
						
						int did = (Integer) sessionHQL4.save(rsl);
						sessionHQL4.getTransaction().commit();
						sessionHQL4.close();
					}
					}else if(scenario.equals("Formation")) {
//						String formation_b []= formation_code.split(",");
//						for(String formationA:formation_b) {
						Boolean e1 = isformationWepe_transauth_exits(we_pe_no,mct_no, mod);
						if (e1.equals(false)) {
						Session sessionHQL4 = HibernateUtil.getSessionFactory().openSession();
						sessionHQL4.beginTransaction();
						
						CUE_TB_MISO_WEPE_TRANSPORT_MDFS rsf = new CUE_TB_MISO_WEPE_TRANSPORT_MDFS();
						rsf.setFormation(formation_code);
						rsf.setLocation(null);
						rsf.setScenario_unit(null);
						rsf.setScenario(scenario);						
						rsf.setCreated_by(username);
						rsf.setCreated_on(creadtedate);
						rsf.setModified_by(username);
						rsf.setModified_on(modifieddate);
						rsf.setAprv_rejc_by(username);
						rsf.setDate_of_apprv_rejc(creadtedate);
						rsf.setMct_no(mct_no);
						rsf.setModification(mod);
						rsf.setStatus("0");
						rsf.setRemarks(remarks);
						rsf.setRoleid(roleid);
						rsf.setStd_nomclature(std_nomnclature);
						rsf.setInc_dec(r);
						rsf.setVersion_no("0");
						rsf.setWe_pe_no(we_pe_no);
						rsf.setTable_title(table_title);
						
						amt = Integer.parseInt(amt_inc_dec) ;
						if(r.equals("D"))
						{
							String sum = "-" + amt;	
							
							rsf.setAmt_inc_dec(Integer.parseInt(sum));
						}
						else {
							
							rsf.setAmt_inc_dec(amt);
						}	
						
						int did = (Integer) sessionHQL4.save(rsf);
						sessionHQL4.getTransaction().commit();
						sessionHQL4.close();
//						}
					}else {
						a++;
						model.put("msg", "Data Already Exits!");
					}
					}else if(scenario.equals("Unit")) {
						String unit_b []= unit_code.split(",");
						for (String unitA:unit_b) {
						
						Session sessionHQL4 = HibernateUtil.getSessionFactory().openSession();
						sessionHQL4.beginTransaction();
						
						CUE_TB_MISO_WEPE_TRANSPORT_MDFS rsu = new CUE_TB_MISO_WEPE_TRANSPORT_MDFS();
						rsu.setFormation(null);
						rsu.setLocation(null);
						rsu.setScenario_unit(unitA);
						rsu.setScenario(scenario);	
						rsu.setCreated_by(username);
						rsu.setCreated_on(creadtedate);
						rsu.setModified_by(username);
						rsu.setModified_on(modifieddate);
						rsu.setAprv_rejc_by(username);
						rsu.setDate_of_apprv_rejc(creadtedate);
						rsu.setMct_no(mct_no);
						rsu.setModification(mod);
						rsu.setStatus("0");
						rsu.setRemarks(remarks);
						rsu.setRoleid(roleid);
						rsu.setStd_nomclature(std_nomnclature);
						rsu.setInc_dec(r);
						rsu.setVersion_no("0");
						rsu.setWe_pe_no(we_pe_no);
						rsu.setTable_title(table_title);
						
						amt = Integer.parseInt(amt_inc_dec) ;
						if(r.equals("D"))
						{
							String sum = "-" + amt;	
							
							rsu.setAmt_inc_dec(Integer.parseInt(sum));
						}
						else {
							
							rsu.setAmt_inc_dec(amt);
						}	
						
						int did = (Integer) sessionHQL4.save(rsu);
						sessionHQL4.getTransaction().commit();
						sessionHQL4.close();
						}
					
					}else if(scenario.equals("Others")) {
						Session sessionHQL4 = HibernateUtil.getSessionFactory().openSession();
						sessionHQL4.beginTransaction();
						
						CUE_TB_MISO_WEPE_TRANSPORT_MDFS rso = new CUE_TB_MISO_WEPE_TRANSPORT_MDFS();
						rso.setFormation(null);
						rso.setLocation(null);
						rso.setScenario_unit(null);
						rso.setScenario("Others");
						rso.setCreated_by(username);
						rso.setCreated_on(creadtedate);
						rso.setModified_by(username);
						rso.setModified_on(modifieddate);
						rso.setAprv_rejc_by(username);
						rso.setDate_of_apprv_rejc(creadtedate);
						rso.setMct_no(mct_no);
						rso.setModification(mod);
						rso.setStatus("0");
						rso.setRemarks(remarks);
						rso.setRoleid(roleid);
						rso.setStd_nomclature(std_nomnclature);
						rso.setInc_dec(r);
						rso.setVersion_no("0");
						rso.setWe_pe_no(we_pe_no);
						rso.setTable_title(table_title);
						
						amt = Integer.parseInt(amt_inc_dec) ;
						if(r.equals("D"))
						{
							String sum = "-" + amt;	
							
							rso.setAmt_inc_dec(Integer.parseInt(sum));
						}
						else {
							
							rso.setAmt_inc_dec(amt);
						}	
						
						int did = (Integer) sessionHQL4.save(rso);
						sessionHQL4.getTransaction().commit();
						sessionHQL4.close();
					}
					else{
						rs.setScenario(null);
						rs.setFormation(null);
						rs.setLocation(null);
						rs.setScenario_unit(null);
						rs.setVersion_no("0");
					}			
					Session sessionHQL4 = HibernateUtil.getSessionFactory().openSession();
					sessionHQL4.beginTransaction();
					
//					int did1 = (Integer) sessionHQL4.save(rs);
					int did = (Integer) sessionHQL4.save(rs1);
					sessionHQL4.getTransaction().commit();
					sessionHQL4.close();
					if (a == 0) {
					model.put("msg", "Data saved Successfully");	
					}else {
						model.put("msg", "Data Already Exits!");
					}
				}
				else
				{		
					Boolean e = isdetailWepe_weapauth_exits_trans(we_pe_no,mct_no, mod,location_code,formation_code,unit_code);
					if (e.equals(false)) {
//						amt = Integer.parseInt(request.getParameter("amt_inc_dec")) ;
//						if(r.equals("D"))
//						{
//							String sum = "-" + amt;				
//							rs.setAmt_inc_dec(Integer.parseInt(sum));
//						}
//						else {
//							
//							rs.setAmt_inc_dec(amt);
//						}				
					
//						rs.setMct_no(mct_no);				
//						rs.setAprv_rejc_by(username);				
//						rs.setCreated_by(username);
//						rs.setCreated_on(creadtedate);
//						rs.setDate_of_apprv_rejc(creadtedate);			
//						rs.setModified_by(username);
//						rs.setModified_on(modifieddate);			
//						rs.setStatus("0");				
//						rs.setRemarks(request.getParameter("remarks"));			
						
						String scenario = rs.getScenario();
						if(scenario.equals("Location")) {
							String location_bl []= location_code.split(",");
							for(String locationAl:location_bl)
							{
								
							Session sessionHQL4 = HibernateUtil.getSessionFactory().openSession();
							sessionHQL4.beginTransaction();
							
							CUE_TB_MISO_WEPE_TRANSPORT_MDFS rsl1 = new CUE_TB_MISO_WEPE_TRANSPORT_MDFS();		
							rsl1.setLocation(locationAl);
							rsl1.setFormation(null);
							rsl1.setScenario_unit(null);
							rsl1.setScenario(scenario);
							rsl1.setCreated_by(username);
							rsl1.setCreated_on(creadtedate);
							rsl1.setModified_by(username);
							rsl1.setModified_on(modifieddate);
							rsl1.setAprv_rejc_by(username);
							rsl1.setDate_of_apprv_rejc(creadtedate);
							rsl1.setMct_no(mct_no);
							rsl1.setModification(mod);
							rsl1.setStatus("0");
							rsl1.setRemarks(remarks);
							rsl1.setRoleid(roleid);
							rsl1.setStd_nomclature(std_nomnclature);
							rsl1.setInc_dec(r);
							rsl1.setVersion_no(String.valueOf("1"));
							rsl1.setWe_pe_no(we_pe_no);
							rsl1.setTable_title(table_title);
							
							amt = Integer.parseInt(amt_inc_dec) ;
							if(r.equals("D"))
							{
								String sum = "-" + amt;	
								
								rsl1.setAmt_inc_dec(Integer.parseInt(sum));
							}
							else {
								
								rsl1.setAmt_inc_dec(amt);
							}	
						
							int did = (Integer) sessionHQL4.save(rsl1);
							sessionHQL4.getTransaction().commit();
							sessionHQL4.close();
							}
						}else if(scenario.equals("Formation")) {
//							String formation_bf []= formation_code.split(",");
//							for(String formationAf:formation_bf) {
							Boolean e1 = isformationWepe_transauth_exits(we_pe_no,mct_no, mod);
							if (e1.equals(false)) {
							Session sessionHQL4 = HibernateUtil.getSessionFactory().openSession();
							sessionHQL4.beginTransaction();
							
							CUE_TB_MISO_WEPE_TRANSPORT_MDFS rsf1 = new CUE_TB_MISO_WEPE_TRANSPORT_MDFS();
							rsf1.setFormation(formation_code);
							rsf1.setLocation(null);
							rsf1.setScenario_unit(null);
							rsf1.setScenario(scenario);						
							rsf1.setCreated_by(username);
							rsf1.setCreated_on(creadtedate);
							rsf1.setModified_by(username);
							rsf1.setModified_on(modifieddate);
							rsf1.setAprv_rejc_by(username);
							rsf1.setDate_of_apprv_rejc(creadtedate);
							rsf1.setMct_no(mct_no);
							rsf1.setModification(mod);
							rsf1.setStatus("0");
							rsf1.setRemarks(remarks);
							rsf1.setRoleid(roleid);
							rsf1.setStd_nomclature(std_nomnclature);
							rsf1.setInc_dec(r);
							rsf1.setVersion_no(String.valueOf("1"));
							rsf1.setWe_pe_no(we_pe_no);
							rsf1.setTable_title(table_title);
							
							amt = Integer.parseInt(amt_inc_dec) ;
							if(r.equals("D"))
							{
								String sum = "-" + amt;	
								
								rsf1.setAmt_inc_dec(Integer.parseInt(sum));
							}
							else {
								
								rsf1.setAmt_inc_dec(amt);
							}	
							
							int did = (Integer) sessionHQL4.save(rsf1);
							sessionHQL4.getTransaction().commit();
							sessionHQL4.close();
							}else {
								a++;
								model.put("msg", "Data Already Exits!");
						
//							}
							}
						}else if(scenario.equals("Unit")) {
							String unit_bu []= unit_code.split(",");
							for (String unitAu:unit_bu) {
								
							Session sessionHQL4 = HibernateUtil.getSessionFactory().openSession();
							sessionHQL4.beginTransaction();
							
							CUE_TB_MISO_WEPE_TRANSPORT_MDFS rsu1 = new CUE_TB_MISO_WEPE_TRANSPORT_MDFS();
							rsu1.setFormation(null);
							rsu1.setLocation(null);
							rsu1.setScenario_unit(unitAu);
							rsu1.setScenario(scenario);	
							rsu1.setCreated_by(username);
							rsu1.setCreated_on(creadtedate);
							rsu1.setModified_by(username);
							rsu1.setModified_on(modifieddate);
							rsu1.setAprv_rejc_by(username);
							rsu1.setDate_of_apprv_rejc(creadtedate);
							rsu1.setMct_no(mct_no);
							rsu1.setModification(mod);
							rsu1.setStatus("0");
							rsu1.setRemarks(remarks);
							rsu1.setRoleid(roleid);
							rsu1.setStd_nomclature(std_nomnclature);
							rsu1.setInc_dec(r);
							rsu1.setVersion_no(String.valueOf("1"));
							rsu1.setWe_pe_no(we_pe_no);
							rsu1.setTable_title(table_title);
							
							amt = Integer.parseInt(amt_inc_dec) ;
							if(r.equals("D"))
							{
								String sum = "-" + amt;	
								
								rsu1.setAmt_inc_dec(Integer.parseInt(sum));
							}
							else {
								
								rsu1.setAmt_inc_dec(amt);
							}	
							
							int did = (Integer) sessionHQL4.save(rsu1);
							sessionHQL4.getTransaction().commit();
							sessionHQL4.close();
							}
						
						}else if(scenario.equals("Others")) {
							Session sessionHQL4 = HibernateUtil.getSessionFactory().openSession();
							sessionHQL4.beginTransaction();
							
							CUE_TB_MISO_WEPE_TRANSPORT_MDFS rso1 = new CUE_TB_MISO_WEPE_TRANSPORT_MDFS();
							rso1.setFormation(null);
							rso1.setLocation(null);
							rso1.setScenario_unit(null);
							rso1.setScenario("Others");
							rso1.setCreated_by(username);
							rso1.setCreated_on(creadtedate);
							rso1.setModified_by(username);
							rso1.setModified_on(modifieddate);
							rso1.setAprv_rejc_by(username);
							rso1.setDate_of_apprv_rejc(creadtedate);
							rso1.setMct_no(mct_no);
							rso1.setModification(mod);
							rso1.setStatus("0");
							rso1.setRemarks(remarks);
							rso1.setRoleid(roleid);
							rso1.setStd_nomclature(std_nomnclature);
							rso1.setInc_dec(r);
							rso1.setVersion_no(String.valueOf("1"));
							rso1.setWe_pe_no(we_pe_no);
							rso1.setTable_title(table_title);
							
							amt = Integer.parseInt(amt_inc_dec) ;
							if(r.equals("D"))
							{
								String sum = "-" + amt;	
								
								rso1.setAmt_inc_dec(Integer.parseInt(sum));
							}
							else {
								
								rso1.setAmt_inc_dec(amt);
							}	
							
							int did = (Integer) sessionHQL4.save(rso1);
							sessionHQL4.getTransaction().commit();
							sessionHQL4.close();
						}
						else{
							rs.setScenario(null);
							rs.setFormation(null);
							rs.setLocation(null);
							rs.setScenario_unit(null);
							rs.setVersion_no(String.valueOf("1"));
						}
						
						
						Session sessionhql = HibernateUtil.getSessionFactory().openSession();
						sessionhql.beginTransaction();
//						sessionhql.save(rs);
						sessionhql.getTransaction().commit();
						sessionhql.close();
						if(a==0) {
						model.put("msg", "Data saved Successfully");
						}else {
							model.put("msg", "Data Already Exits!");
						}
					}
					else {
						model.put("msg", "Data already exist!");
					}					
						
				}
				
			}	
			catch (Exception e) {
				
				sessionHQL.getTransaction().rollback();
			}
			finally{
				
			}
		
		List<Map<String, Object>> list= transportDAO.getAttributeFromTransportModification1(we_pe_no,"","","","");
		model.put("list", list);
		model.put("list.size()", list.size());
		model.put("we_pe1", we_pe);
		model.put("scenario1", request.getParameter("scenario"));
		model.put("location1", request.getParameter("location_name"));
		model.put("formation1", request.getParameter("formation_name"));
		model.put("unit1", request.getParameter("scenario_unit_name"));
		model.put("we_pe_no1", we_pe_no);
		model.put("location1_hid", location_code);
		model.put("formation1_hid", formation_code);
		model.put("unit1_hid", unit_code);
		model.put("getLocation1", m.getLocation1());
		model.put("getFormation1", m.getFormation1());
		model.put("getUnitUrl1", m.getUnitUrl1());
		
		return new ModelAndView("transportAuthorizationUnderModificationTiles");	
	}		
	@SuppressWarnings("unchecked")
	public Boolean isdetailWepe_weapauth_exits_trans(String we_pe_no,String mct_no, String mod, String location_code,String formation_code,String unit_code) {

	Session sessioncount = HibernateUtil.getSessionFactory().openSession();
	Transaction transaction = sessioncount.beginTransaction();
	
	String loc = "";
	String form = "";
	String unit = "";
	String location_b []= location_code.split(",");
	int i =0;
	for(String locationA:location_b) {
		if(i==0) {
			loc+= ":location"+i;
		}
		else {
			loc+= ",:location"+i;
		}
		i++;
	}
	String formation_b []= formation_code.split(",");
	int k =0;
	for(String formationA:formation_b) {
		if(k==0) {
			form+= ":formation"+k;
		}
		else {
			form+= ",:formation"+k;
		}
		k++;
	}
	
	String unit_b []= unit_code.split(",");
	int p =0;
	for(String unitA:unit_b) {
		if(p==0) {
			unit+= ":scenario_unit"+p;
		}
		else {
			unit+= ",:scenario_unit"+p;
		}
		p++;
	}
	Query q1 = sessioncount.createQuery("FROM CUE_TB_MISO_WEPE_TRANSPORT_MDFS where we_pe_no=:we_pe_no and mct_no=:mct_no and modification=:modification"
			+ " and (location in("+loc+" ) or  location is null) "
			+ " and (formation in("+form+" ) or  formation is null) "
			+ " and (scenario_unit in("+unit+" ) or  scenario_unit is null)");
	List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS> users = null;
	try {
	q1.setParameter("we_pe_no", we_pe_no);
	q1.setParameter("mct_no", mct_no);
	q1.setParameter("modification", mod);
	String locationx []= location_code.split(",");
	int j =0;
	for(String locationA:locationx) {
		q1.setParameter("location"+j, locationA);
		j++;
	}
	
	String formationx []= formation_code.split(",");
	int l =0;
	for(String formationA:formationx) {
		q1.setParameter("formation"+l, formationA);
		l++;
	}
	
	String unitx []= unit_code.split(",");
	int n =0;
	for(String unitA:unitx) {
		q1.setParameter("scenario_unit"+n, unitA);
		n++;
	}
	
	users = (List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS>) q1.list();
	transaction.commit();
	sessioncount.close();	
	} catch (Exception e) {
		sessioncount.getTransaction().rollback();
		
		return null;
	}
	if (users.size() > 0) {
		return true;
	}
	return false;
}
	
	@SuppressWarnings("unchecked")
	public Boolean isformationWepe_transauth_exits(String we_pe_no, String mct_no,String mod) {
		
	
		String hql = "FROM CUE_TB_MISO_WEPE_TRANSPORT_MDFS  where scenario=:scenario and we_pe_no=:we_pe_no and mct_no=:mct_no and modification=:modification";
		List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("we_pe_no", we_pe_no);
			query.setParameter("mct_no", mct_no);
			query.setParameter("modification", mod);	
			query.setParameter("scenario", "Formation");	
			users = (List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}
	
	
	
	@Autowired
	TransportAuthorizationUnderModificationDAO transportmdfs = new TransportAuthorizationUnderModificationDAOImpl();

	@RequestMapping(value = "/admin/search_transport_mdfs", method = RequestMethod.GET)
	public ModelAndView search_item_master(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_transport_mdfs", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_transport_mdfsTile");
	}	
//----------------------------------------------------------SEARCH CODE-------------------------------------------------------------------------------
	
	
	@RequestMapping(value = "/admin/search_transport_mdfs1", method = RequestMethod.POST)
	public ModelAndView search_transport_mdfs1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "mct_no1", required = false) String mct_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "we_pe01", required = false) String we_pe,
			@RequestParam(value = "modification1", required = false) String modification){
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			String  roleid = session.getAttribute("roleid").toString();
			Mmap.put("status1", status);
			Mmap.put("we_pe01", we_pe);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("modification1", modification);
			Mmap.put("mct_no1", mct_no);
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			List<Map<String, Object>> list= transportDAO.getAttributeFromTransportModification(we_pe_no,modification,mct_no,status,roleType,roleAccess,roleSusNo);
				
		Mmap.put("list", list);
		
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("search_transport_mdfsTile");
	}
		
	@RequestMapping(value = "/admin/ApprovedMdfs", method = RequestMethod.POST)
	public ModelAndView ApprovedMdfs(@ModelAttribute("appid") int appid,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession session,
		@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
		@RequestParam(value = "mct_no2", required = false) String mct_no,
		@RequestParam(value = "status2", required = false) String status,
		@RequestParam(value = "we_pe2", required = false) String we_pe,
		@RequestParam(value = "modification2", required = false) String modification){
		
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			String  roleid = session.getAttribute("roleid").toString();
			String  username = session.getAttribute("username").toString();
			
			String mst = transportmdfs.setApprovedMdfs(appid,username);
			if(mst.equals("Approved Successfully")) {
				vetting.updateVettingDtl( we_pe_no, "2");
			}
			Mmap.put("msg", mst);	
			Mmap.put("status1", status);
			Mmap.put("we_pe01", we_pe);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("modification1", modification);
			Mmap.put("mct_no1", mct_no);
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			 List<Map<String, Object>> list= transportDAO.getAttributeFromTransportModification(we_pe_no,modification,mct_no,status, roleType,roleAccess,roleSusNo);
			Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("search_transport_mdfsTile");
	}
	
	@RequestMapping(value = "/DeleteMdfsAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> DeleteMdfsAdd(int deleteid) {			
		List<String> list2 = new ArrayList<String>();
		list2.add(transportmdfs.setDeleteMdfs(deleteid));
		return list2;
	}
	
	@RequestMapping(value = "/admin/UpdateMdfs")
	public ModelAndView UpdateMdfs(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionEdit){
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("edittransportmdfsCmd", transportmdfs.getCUE_TB_MISO_WEPE_TRANSPORT_MDFSRid(updateid));
		model.put("msg",msg);
		return new ModelAndView("editTransportMdfsrTile");
	}
	
	@RequestMapping(value = "/editTransportMdfsAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView editTransportMdfsAction(@ModelAttribute("edittransportmdfsCmd") CUE_TB_MISO_WEPE_TRANSPORT_MDFS updateid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session11,HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		Session sessioncount=null ;
		Session session = null;
		
		Transaction transaction=null;
		Transaction tx=null;
		try {
		
		String username = session11.getAttribute("username").toString();
		String location = request.getParameter("location");
		String formation = request.getParameter("formation");
		String scenario1 = request.getParameter("scenario");
		String modification = request.getParameter("modification");
		
		if(updateid.getScenario() == "" || updateid.getScenario() ==null ||  updateid.getScenario().equals(null) ||  updateid.getScenario().isEmpty())
		{
			model.put("updateid",updateid.getId() );
			model.put("msg", "Please Select Scenario");
			return new ModelAndView("redirect:UpdateMdfs");
		}
		if(validation.checkScenarioLength(updateid.getScenario())  == false){
	 		model.put("msg",validation.senarioMSG);
			return new ModelAndView("redirect:transportAuthorizationUnderModification");
		}
	
		if(scenario1 != "") {
			if(scenario1.equals("Location"))
			{
				if(updateid.getLocation().equals("") || updateid.getLocation() ==null ||  updateid.getLocation().equals(null) ||  updateid.getLocation().isEmpty())
				{
					model.put("updateid",updateid.getId() );
					model.put("msg", "Please Enter Location");
					return new ModelAndView("redirect:UpdateMdfs");
				
				}
				if(validation.checkLocationLength(updateid.getLocation())  == false){
					model.put("msg",validation.locMSG);
					return new ModelAndView("redirect:transportAuthorizationUnderModification");
				}
			}
			if(scenario1.equals("Formation"))
			{
				
				if(formation.equals("") || formation == null ||  formation.equals(null) ||  formation.isEmpty())
				{
					model.put("updateid",updateid.getId() );
					model.put("msg", "Please Enter Formation");
					return new ModelAndView("redirect:UpdateMdfs");
				}
				if(validation.checkFormationLength(updateid.getFormation())  == false){
					model.put("msg",validation.formMSG);
					return new ModelAndView("redirect:transportAuthorizationUnderModification");
				}
			}
			if(scenario1.equals("Unit"))
			{
				if(updateid.getScenario_unit().equals("") || updateid.getScenario_unit() ==null ||  updateid.getScenario_unit().equals(null) ||  updateid.getScenario_unit().isEmpty())
				{
					model.put("updateid",updateid.getId() );
					model.put("msg", "Please Enter Unit");
					return new ModelAndView("redirect:UpdateMdfs");
				}
				if(validation.sus_noLength(updateid.getScenario_unit())  == false){
					model.put("msg",validation.unitMSG);
					return new ModelAndView("redirect:transportAuthorizationUnderModification");
				}
			}
	}
		if(updateid.getModification().equals("") || updateid.getModification() ==null ||  updateid.getModification().equals(null) ||  updateid.getModification().isEmpty())
		{
			model.put("updateid",updateid.getId() );
			model.put("msg", "Please Enter Modification");
			return new ModelAndView("redirect:UpdateMdfs");
		
		}
		if(validation.checkModificationLength(updateid.getModification())  == false){
			model.put("msg",validation.modMSG);
			return new ModelAndView("redirect:transportAuthorizationUnderModification");
		}
		String amt_inc_dec1 = request.getParameter("amt_inc_dec");
		String inc_dec = request.getParameter("inc_dec");
		String base = request.getParameter("auth_amt");
        float f_base = Float.parseFloat(base);
        float f_amt_inc_dec = Float.parseFloat(amt_inc_dec1);
    	if(validation.checkIncDecLength(updateid.getInc_dec())  == false){
			model.put("msg","Please Enter Valid Length Of Increment/Decrement");
			return new ModelAndView("redirect:transportAuthorizationUnderModification");
		}
		if(amt_inc_dec1 == "0"  || amt_inc_dec1==null || amt_inc_dec1=="null" || amt_inc_dec1.equals("0") || amt_inc_dec1.equals("undefined") )
		{
			model.put("updateid",updateid.getId());
			model.put("msg", "Please Enter Amount of Increment or Decrement");
			return new ModelAndView("redirect:UpdateMdfs");
		}
		else {
		
			if(inc_dec == "D" || inc_dec.equals("D")) {
				if(f_amt_inc_dec > f_base) {
					model.put("updateid",updateid.getId());
					model.put("msg", "Please Check Amount of Inc/Dec");
					return new ModelAndView("redirect:UpdateMdfs");
				}
			}
		}	
		 int lenval=0;
		  if(request.getParameter("inc_dec").equals("I"))
			  lenval = 8;
		  else if(request.getParameter("inc_dec").equals("D"))
			  lenval = 9;
		  
		  String amt_inc_dec_valid =  Double.toString(updateid.getAmt_inc_dec());
		  if(validation.checkAmt_inc_decLength(amt_inc_dec_valid,lenval)  == false){
				model.put("msg",validation.amt_inc_decMSG);
				return new ModelAndView("redirect:transportAuthorizationUnderModification");
			}
			sessioncount = HibernateUtil.getSessionFactory().openSession();
			transaction = sessioncount.beginTransaction();		
			Query q1 = sessioncount.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANSPORT_MDFS where we_pe_no=:we_pe_no and mct_no=:mct_no and modification=:modification and id !=:id");
			q1.setParameter("we_pe_no", updateid.getWe_pe_no());
			q1.setParameter("mct_no", updateid.getMct_no());
			q1.setParameter("modification", updateid.getModification());
			q1.setParameter("id", updateid.getId());
			@SuppressWarnings("unchecked")
			Long count2 = (Long)q1.uniqueResult();
			model.put("count", count2);
			transaction.commit();
			sessioncount.close();			
			
			String scenario_unit = request.getParameter("scenario_unit");
			int amt_inc_dec =  Integer.parseInt(request.getParameter("amt_inc_dec"));
			String remarks = request.getParameter("remarks");
			if(count2 == 0) {
				String scenario = request.getParameter("scenario");
				if(scenario.equals("Location")) {
					scenario1=scenario;
					location =request.getParameter("location");
					formation="";
					scenario_unit="";
				}
				else if(scenario.equals("Formation")) {
					scenario1=scenario;
					location ="";
					formation=request.getParameter("formation");
					scenario_unit="";
				}
				else if(scenario.equals("Unit")) {
					scenario1=scenario;
					location ="";
					formation="";
					scenario_unit=request.getParameter("scenario_unit");
				}
				else if(scenario.equals("Others")){
					scenario1=scenario;
					location ="";
					formation="";
					scenario_unit="";
				}
				else {
					scenario1="";
					location ="";
					formation="";
					scenario_unit="";
				}
				
				session = HibernateUtilNA.getSessionFactory().openSession();
				tx = session.beginTransaction();
				String r = request.getParameter("inc_dec");
				int amt = Integer.parseInt(request.getParameter("amt_inc_dec")) ;
				if(r.equals("D"))
				{
					String sum = "-" + amt;	
					amt_inc_dec=  Integer.parseInt(sum);					
				}
				else {
					 amt_inc_dec =  Integer.parseInt(request.getParameter("amt_inc_dec"));
				}
				
				String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				
				String hql = "update CUE_TB_MISO_WEPE_TRANSPORT_MDFS  set location=:location,scenario_unit=:scenario_unit, formation=:formation ,modification =:modification , scenario=:scenario1,  amt_inc_dec=:amt_inc_dec,modified_by=:modified_by,modified_on=:modified_on, remarks =:remarks,vettedby_dte1 = :vettedby_dte1, vettedby_dte2 = :vettedby_dte2 where id=:id";
			    Query query = session.createQuery(hql).setString("scenario_unit", scenario_unit).setString("location", location).setString("formation", formation).setString("modification",modification).setString("scenario1",scenario1).setInteger("amt_inc_dec",amt_inc_dec).setString("remarks",remarks).setString("modified_by", username).setString("modified_on", modifydate)
			    		.setString("vettedby_dte1", "").setString("vettedby_dte2", "").setInteger("id",updateid.getId());
			    int rowCount = query.executeUpdate();
				tx.commit();
				session.close();
				if(rowCount > 0) {
					model.put("msg", "Updated Successfully");
				}
				else {
					model.put("msg", "Updated not Successfully");
				}
			}
			
			else {
			   model.put("msg", "Data already exist !");
			   model.put("edittransportmdfsCmd", transportmdfs.getCUE_TB_MISO_WEPE_TRANSPORT_MDFSRid(updateid.getId()));	
				return new ModelAndView("editTransportMdfsrTile");
			}
			
		}	
		catch (Exception e) {
			session.getTransaction().rollback();
			sessioncount.getTransaction().rollback();
			
		}
		finally{
			
		
		}
		return new ModelAndView("redirect:transportAuthorizationUnderModification");
	}
		
	@RequestMapping(value = "/updaterejectactionmdfs", method = RequestMethod.POST)	 
	public @ResponseBody List<String> updaterejectactionmdfs(String remarks,String letter_no,int id) {
		List<String> list2  =wepepersmdfs.updaterejectactionqrywepers("CUE_TB_MISO_WEPE_TRANSPORT_MDFS",remarks,id,letter_no);
		return list2;
	}
	
}