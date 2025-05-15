package com.controller.mnh;


import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.mnh_input_mortalityDAO;
import com.models.mnh.Tb_Med_Death;
import com.models.psg.update_census_data.TB_CENSUS_BATT_PHY_CASUALITY;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class input_MortalityController {
	@Autowired
	private RoleBaseMenuDAO roledao;

	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	@Autowired
	private MNH_Common_DAO mnh1_Dao;

	@Autowired
	private mnh_input_mortalityDAO mo;
 
	 MNH_ValidationController validation = new MNH_ValidationController();

	MNH_CommonController mcommon = new MNH_CommonController();
	
	// INPUTS MODULE > (MORTALITY SCREEN)	
	
	@RequestMapping(value = "/admin/mnh_input_mortality", method = RequestMethod.GET)
	public ModelAndView mnh_input_mortality(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		 Boolean val = roledao.ScreenRedirect("mnh_input_mortality", session.getAttribute("roleid").toString());
         if(val == false) {
                 return new ModelAndView("AccessTiles");
         }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		
		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
	     
		Mmap.put("getMedSystemCode_PERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		Mmap.put("getMedSystemCode_PERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		Mmap.put("getMedSystemCode_SERVICE", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("getMedSystemCode_CATEGORY", mcommon.getMedSystemCode("CATEGORY", "", session));
		
		Mmap.put("ml_5", mcommon.getMedrelationList("", session));
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_input_mortalityTiles");
	}
	
					/*=========save===============*/
	
	@RequestMapping(value = "/mortality_details_inputAction" ,method = RequestMethod.POST)
	public ModelAndView mortality_details_inputAction(@ModelAttribute("mortality_details_inputCMD") Tb_Med_Death rs,
			BindingResult bindingResult, HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg)  throws ParseException {

		 Boolean val = roledao.ScreenRedirect("mnh_input_mortality", session.getAttribute("roleid").toString());
         if(val == false) {
           return new ModelAndView("AccessTiles");
          }
        if(request.getHeader("Referer") == null ) {
            msg = "";
            return new ModelAndView("redirect:bodyParameterNotAllow");
          }
        
        String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
	
		String sus_no1=request.getParameter("sus_no");
		if(roleAccess.equals("Unit")){
			model.put("sus_no",roleSusNo);
			model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			sus_no1 = roleSusNo;
			
		}
        String username = session.getAttribute("username").toString();
        int id = rs.getId() > 0 ? rs.getId() : 0;	
		
        DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date_of_birth_i = null;
		Date dt_mortality_i = null;
		
		String sus_no = request.getParameter("sus_no");
		String service = request.getParameter("service");
		String category = request.getParameter("category");
		String relation = request.getParameter("relation");
		 BigInteger adhar_card_no =BigInteger.ZERO;
         if(!request.getParameter("adhar_card_no") .equals("")) {
        	 adhar_card_no =new BigInteger(request.getParameter("adhar_card_no"));
		 }
         BigInteger contact_no =BigInteger.ZERO;            
         if(!request.getParameter("contact_no1") .equals("")){
         	contact_no =new BigInteger(request.getParameter("contact_no1"));
		     }
		
		
         String persnl_no1 = request.getParameter("persnl_no1");
       
			String persnl_no2 = request.getParameter("persnl_no2");
			
			String persnl_no3 = request.getParameter("persnl_no3");
			
			String persnl_no = null;
			if (persnl_no1.equalsIgnoreCase("-1") && !service.equalsIgnoreCase("OTHERS")) {
				persnl_no = persnl_no2 + persnl_no3;
			} else if (!persnl_no1.equalsIgnoreCase("-1") && !persnl_no2.equalsIgnoreCase("")
					&& !persnl_no3.equalsIgnoreCase("-1") && service.equalsIgnoreCase("ARMY")) {
				persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
			} else if (service.equalsIgnoreCase("OTHERS")) {
				persnl_no = null;
			}
			
		String icd_cd[] = request.getParameter("icd_code").split("-");
		String icd_cau[] = request.getParameter("icd_cause").split("-");
		 
			if(request.getParameter("unit_name") == null || request.getParameter("unit_name").equals(" ")){ 
				model.put("msg", "Please Enter the Hospital Name");
				return new ModelAndView("redirect:mnh_input_mortality");
			}
		
				if(request.getParameter("sus_no") == null || request.getParameter("sus_no").equals("")){ 
	            model.put("msg", "Please Enter the SUS No");
	            return new ModelAndView("redirect:mnh_input_mortality");
	             }  
				 if(validation.sus_noLength(request.getParameter("sus_no")) == false){
					 model.put("msg",validation.sus_noMSG);
		                return new ModelAndView("redirect:mnh_input_mortality");
		             }
				 
					 if (persnl_no2.equals("") && !service.equals("OTHERS")) {
					 model.put("msg", "Please Enter the Personnel No");
						return new ModelAndView("redirect:mnh_input_mortality");
					}
					if (persnl_no3.equals("-1") && !service.equals("OTHERS")) {
						model.put("msg", "Please Enter the Personnel No");
						return new ModelAndView("redirect:mnh_input_mortality");
					}
					 if(validation.persnl_noLength(request.getParameter("persnl_no2")) == false){
						 model.put("msg",validation.persnl_no2MSG);
			               return new ModelAndView("redirect:mnh_input_mortality");
			               }
		
				   
				   if (category.equals("-1") && service.equalsIgnoreCase("OTHERS")) {
						category = "";
					}
					if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
						model.put("msg", "Please Select the Category");
						return new ModelAndView("redirect:mnh_input_mortality");
					}
					/*   
				   if(rs.getDeath_rank().getId() == -1){  
	                   model.put("msg", "Please Select the rank");
	                   return new ModelAndView("redirect:mnh_input_mortality");
	                   } 
				   */
				   if(request.getParameter("gender").equals("-1") || request.getParameter("gender") == "-1" || request.getParameter("gender") == null || request.getParameter("gender").equals("")){  
	                   model.put("msg", "Please Select the Gender");
	                   return new ModelAndView("redirect:mnh_input_mortality");
	                   } 
				   if( request.getParameter("category").equals("MNS") && !request.getParameter("gender").equals("Female")) {
					   model.put("msg", "Gender should be FEMALE Here");
						return new ModelAndView("redirect:mnh_input_mortality");
					}
				   if(request.getParameter("relation").equals("-1") || request.getParameter("relation") == "-1" || request.getParameter("relation") == null || request.getParameter("relation").equals("")){  
	                   model.put("msg", "Please Select the Relation");
	                   return new ModelAndView("redirect:mnh_input_mortality");
	                   } 
				 
				   if (((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF")
							|| relation.equals("SISTEROF")) ) && (!request.getParameter("gender").toString().toLowerCase().equals("female"))) {
						model.put("msg", "Gender should be Female Here");
						return new ModelAndView("redirect:mnh_input_mortality");
					} 
				   
				   if (request.getParameter("date_of_birth1") == null || request.getParameter("date_of_birth1").equals("")) {
						model.put("msg", "Please Select the Date of  Birth");
						return new ModelAndView("redirect:mnh_input_mortality");
					} else {

						date_of_birth_i = formatter1.parse(request.getParameter("date_of_birth1"));
					}
			
						SimpleDateFormat YY = new SimpleDateFormat("yyyy");
						SimpleDateFormat MM = new SimpleDateFormat("MM");
						SimpleDateFormat dd = new SimpleDateFormat("dd");
						int year = Integer.parseInt(YY.format(date_of_birth_i));
						int MM1 = Integer.parseInt(MM.format(date_of_birth_i));
						int Dd = Integer.parseInt(dd.format(date_of_birth_i));
				
						LocalDate today = LocalDate.now(); // Today's date
						LocalDate birthday = LocalDate.of(year, MM1, Dd);// Birth date
						Period p = Period.between(birthday, today);
						Integer age_year = 0;

					if (request.getParameter("age_year") != "" && !request.getParameter("age_year").equals("")) {
						age_year = p.getYears();
					}
			
					if (relation.equals("SELF") &&  request.getParameter("date_of_birth1") != "" && request.getParameter("age_year") != "" && age_year < 17) {
						model.put("msg", "Personnel Age Year Not Allowed Below 17");
						return new ModelAndView("redirect:mnh_input_mortality");
					}
			
						String total_service_month = request.getParameter("total_service_month1");
						Integer total_service_month1 = 0;
						if (total_service_month != "" && !total_service_month.equals("")) {
							total_service_month1 = Integer.parseInt(total_service_month);
						}
						String total_service_year = request.getParameter("total_service_year1");
						Integer total_service_year1 = 0;
						if (total_service_year != "" && !total_service_year.equals("")) {
							total_service_year1 = Integer.parseInt(total_service_year);
						}
					if (total_service_year1 > age_year) {
						model.put("msg", "Service Year Should Not More Than Age Year");
						return new ModelAndView("redirect:mnh_input_mortality");
					}
				   
				   if(request.getParameter("place_of_death").equals("-1") || request.getParameter("place_of_death") == "-1" || request.getParameter("place_of_death") == null || request.getParameter("place_of_death").equals("")){  
					   model.put("msg", "Please Select the Place of Death");
	                   return new ModelAndView("redirect:mnh_input_mortality");
	                  }
				
				   if(request.getParameter("nature_of_death").equals("-1") || request.getParameter("nature_of_death") == "-1" || request.getParameter("nature_of_death") == null || request.getParameter("nature_of_death").equals("")){  
	                   model.put("msg", "Please Select the Nature of Death");
	                   return new ModelAndView("redirect:mnh_input_mortality");
	                  }
				   if (request.getParameter("dt_mortality1") == null || request.getParameter("dt_mortality1").equals("")) {
						model.put("msg", "Please Select the Date of Mortality");
						return new ModelAndView("redirect:mnh_input_mortality");
					}
					else {
						dt_mortality_i = formatter1.parse(request.getParameter("dt_mortality1"));
					}
				 
				   if(request.getParameter("icd_code") == null || request.getParameter("icd_code").equals("")){ 
			            model.put("msg", "Please Enter the ICD Code");
			            return new ModelAndView("redirect:mnh_input_mortality");
			             } 
				   if(validation.DiseasemmrLength(rs.getPersnl_name()) == false){
		                 model.put("msg",validation.persnl_nameMSG);
		                return new ModelAndView("redirect:mnh_input_mortality");
		                }
					   if(validation.DiseasemmrLength(rs.getPersnl_unit()) == false){
			                model.put("msg",validation.persnl_unitMSG);
			               return new ModelAndView("redirect:mnh_input_mortality");
			               }
					   if(validation.DiseasemmrLength(rs.getOperation()) == false){
			                model.put("msg",validation.oprationMSG);
			               return new ModelAndView("redirect:mnh_input_mortality");
			               }
					   if(validation.DiseaseFamilyLength(rs.getSector()) == false){
			                model.put("msg",validation.sectorMSG);
			               return new ModelAndView("redirect:mnh_input_mortality");
			               }
					   if(validation.DiseasemmrLength(rs.getLocation()) == false){
			                model.put("msg",validation.locationMSG);
			               return new ModelAndView("redirect:mnh_input_mortality");
			               }
					   if(validation.DiseasemmrLength(rs.getBde_div_corps_comd()) == false){
			                model.put("msg",validation.bde_div_corps_comdMSG);
			               return new ModelAndView("redirect:mnh_input_mortality");
			               }
					   if(validation.and_noLength(rs.getAnd_no()) == false){
			                model.put("msg",validation.and_noMSG);
			               return new ModelAndView("redirect:mnh_input_mortality");
			               }
					   if(validation.IcdDescriptionLength(rs.getDeath_summary()) == false){
			                model.put("msg",validation.death_summaryMSG);
			               return new ModelAndView("redirect:mnh_input_mortality");
			               }
					   if(validation.DiseasemmrLength(rs.getName_of_nok()) == false){
			                model.put("msg",validation.name_of_nokMSG);
			               return new ModelAndView("redirect:mnh_input_mortality");
			               }
					   if(validation.IcdDescriptionLength(rs.getAddress_of_nok()) == false){
			                model.put("msg",validation.address_of_nokMSG);
			               return new ModelAndView("redirect:mnh_input_mortality");
			               }
					   if(validation.IcdDescriptionLength(rs.getCause_of_death()) == false){
			                model.put("msg",validation.cause_of_deathMSG);
			               return new ModelAndView("redirect:mnh_input_mortality");
			               }
					
					   if(validation.IcdDescriptionLength(rs.getRemarks()) == false){
			                model.put("msg",validation.remarksMSG);
			               return new ModelAndView("redirect:mnh_input_mortality");
			               }
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		
		
		try {
		Long d= mo.checkExitstingMortality(sus_no,persnl_no,id);
        	if(id == 0) {
         	
         	rs.setCreated_on(new Date());
			rs.setCreated_by(username);
			rs.setPersnl_no(persnl_no);
			rs.setSus_no(sus_no);
			rs.setIcd_code(icd_cd[0]);
			rs.setIcd_desc(icd_cau[0]);
			rs.setContact_no(contact_no);
			rs.setAdhar_card_no(adhar_card_no);
			rs.setDate_of_birth(date_of_birth_i);
			rs.setTotal_service_year(total_service_year1);
			rs.setTotal_service_month(total_service_month1);
			rs.setDate_of_death(dt_mortality_i);
			  if (d == 0) {
					session1.save(rs);
					tx.commit();
					model.put("msg", "Data Saved Successfully.");
				} 
				if (d > 0) 
					
				{
					model.put("msg", "Data already Exist.");
				}
				}

          
		  }catch(RuntimeException e){
              try{
                      tx.rollback();
                      model.put("msg", "roll back transaction");
              }catch(RuntimeException rbe){
                      model.put("msg", "Couldn’t roll back transaction " + rbe);
              }
              throw e;
             
		}finally{
			if(session1!=null){
				session1.close();
			}
		}
	   	
		   			return new ModelAndView("redirect:mnh_input_mortality");
		} 
	
	
	

	 /*Search page open*/
	@RequestMapping(value = "/admin/mnh_input_search_mortality", method = RequestMethod.GET)
	public ModelAndView mnh_input_search_mortality(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String username = (String) session.getAttribute("username");
		
		Boolean val = roledao.ScreenRedirect("mnh_input_search_mortality", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
	            msg = "";
	            return new ModelAndView("redirect:bodyParameterNotAllow");
	          }
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		
		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
	     
		Mmap.put("getMedSystemCode_PERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		Mmap.put("getMedSystemCode_PERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		Mmap.put("getMedSystemCode_CATEGORY", mcommon.getMedSystemCode("CATEGORY", "", session));
		
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_input_mortality_searchTiles");
	}
	
	/*Serch Report */
	@RequestMapping(value = "/search_mortality_Input", method = RequestMethod.POST)
	public ModelAndView search_mortality_Input(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("sus1") String sus1,
			String unit1, String psn_no, String cat1,
			@ModelAttribute("from_dt1") String from_dt1, @ModelAttribute("to_dt1") String to_dt1,String adhar1,String contact1,HttpServletRequest request) {

			String username = (String) session.getAttribute("username");
		
		 Boolean val = roledao.ScreenRedirect("mnh_input_search_mortality", session.getAttribute("roleid").toString());
         if(val == false) {
                 return new ModelAndView("AccessTiles");
         }
         
         if(request.getHeader("Referer") == null ) {
             msg = "";
             return new ModelAndView("redirect:bodyParameterNotAllow");
           }
				/*if (unit1.equals("")) {
					model.put("msg", "Please Enter the Hospital Name");
					return new ModelAndView("redirect:mnh_input_search_mortality");
				}
				if (sus1.equals("")) {
					model.put("msg", "Please Enter the SUS No");
					return new ModelAndView("redirect:mnh_input_search_mortality");
				}
				if (validation.DiseasemmrLength(unit1) == false) {
					model.put("msg", validation.hospital_nameMSG);
					return new ModelAndView("redirect:mnh_input_search_mortality");
				}
				if (validation.sus_noLength(sus1) == false) {
					model.put("msg", validation.sus_noMSG);
					return new ModelAndView("redirect:mnh_input_search_mortality");
				}
				if (validation.adhar_noLength(adhar1) == false) {
					 model.put("msg", validation.adharnoMSG);
					 return new ModelAndView("redirect:mnh_input_search_mortality");
					}*/
				 String roleAccess = session.getAttribute("roleAccess").toString();
					String roleSusNo = session.getAttribute("roleSusNo").toString();
				
					String f_sus_no=sus1;
					if(roleAccess.equals("Unit")){
						model.put("sus_no",roleSusNo);
						model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
						f_sus_no = roleSusNo;
						
					}
		List<Map<String, Object>> list = mo.search_mortality_input(sus1, unit1, psn_no, cat1,from_dt1,to_dt1,adhar1,contact1);
		model.put("list", list);
		model.put("size", list.size());
		model.put("getMedSystemCode_PERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		model.put("getMedSystemCode_PERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		model.put("getMedSystemCode_CATEGORY", mcommon.getMedSystemCode("CATEGORY", "", session));
		model.put("sus1", f_sus_no);
		model.put("unit1", unit1);
		model.put("psn_no", psn_no);
		model.put("cat1", cat1);
		model.put("from_dt1", from_dt1);
		model.put("to_dt1", to_dt1);
		model.put("adhar1", adhar1);
		model.put("contact1", contact1);

		return new ModelAndView("mnh_input_mortality_searchTiles");
	}
	
	@RequestMapping(value = "/edit_mortality_Input",method = RequestMethod.POST)
	public ModelAndView edit_mortality_Input(@ModelAttribute("updateid") String updateid, ModelMap model,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {
		 
		 Boolean val = roledao.ScreenRedirect("mnh_input_search_mortality", session.getAttribute("roleid").toString());
         if(val == false) {
                 return new ModelAndView("AccessTiles");
         }
         
         if(request.getHeader("Referer") == null ) {
             msg = "";
             return new ModelAndView("redirect:bodyParameterNotAllow");
           }
		
			Date date = new Date();
			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
			model.put("date", date1);
		
		
      //TB_CENSUS_BATT_PHY_CASUALITY deathDetails = mo.getMorbalityByid(Integer.parseInt(updateid));
			Tb_Med_Death deathDetails = mo.getMorbalityByid(Integer.parseInt(updateid));
   
		model.put("edit_mortality_detailsCMD", deathDetails);
		model.put("msg", msg);
		model.put("updateid", updateid);
		//List<Map<String, Object>> list= mo.get_phy_battle_mortality_details_ds(Integer.parseInt(updateid));
		//model.put("list", list);
		//model.put("size", list.size());
		model.put("getMedSystemCode_PERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		model.put("getMedSystemCode_PERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		model.put("getMedSystemCode_SERVICE", mcommon.getMedSystemCode("SERVICE", "", session));
		model.put("getMedSystemCode_CATEGORY", mcommon.getMedSystemCode("CATEGORY", "", session));
		model.put("ml_5", mcommon.getMedrelationList("", session));
		
		return new ModelAndView("edit_mortilityTiles");
	}
	
	
	/*edit*/
	
	@RequestMapping(value = "/edit_mortality_detailsAction", method = RequestMethod.POST)
	public ModelAndView edit_mortality_detailsAction(@ModelAttribute("edit_mortality_detailsCMD") Tb_Med_Death rs,
			HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
	
		
		
		 Boolean val = roledao.ScreenRedirect("mnh_input_search_mortality", session.getAttribute("roleid").toString());
         if(val == false) {
                 return new ModelAndView("AccessTiles");
         }
         
         if(request.getHeader("Referer") == null ) {
             msg = "";
             return new ModelAndView("redirect:bodyParameterNotAllow");
           }
		
		String username = session.getAttribute("username").toString();
	      
	        model.put("updateid", rs.getId());
	         String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
			
				String sus_no1=request.getParameter("sus_no");
				if(roleAccess.equals("Unit")){
					model.put("sus_no",roleSusNo);
					model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
					sus_no1 = roleSusNo;
					
				}
	    	
				 BigInteger contact_no =BigInteger.ZERO;            
		            if(!request.getParameter("contact_no1") .equals("")) 
		           {
		            	contact_no =new BigInteger(request.getParameter("contact_no1"));
				     }
		        	BigInteger adhar_card_no = BigInteger.ZERO;
					if (!request.getParameter("adhar_card_no").equals("")) {
						adhar_card_no = new BigInteger(request.getParameter("adhar_card_no"));
					}
	    	
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date curr_date = new Date();
			Date date_of_birth_i = null;
			Date dt_mortality_i = null;
			DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			String date_of_birth = request.getParameter("date_of_birth2");
			String dt_mortality = request.getParameter("dt_mortality2");

			String sus_no = request.getParameter("sus_no");
			String service = request.getParameter("service");
			
			String relation = request.getParameter("relation");
		
			
			
			
			String persnl_no1 = request.getParameter("persnl_no1");
			String persnl_no2 = request.getParameter("persnl_no2");
			String persnl_no3 = request.getParameter("persnl_no3");
			String persnl_no = null;
			if (persnl_no1.equalsIgnoreCase("-1") && !service.equalsIgnoreCase("OTHERS")) {
				persnl_no = persnl_no2 + persnl_no3;
			} else if (!persnl_no1.equalsIgnoreCase("-1") && !persnl_no2.equalsIgnoreCase("")
					&& !persnl_no3.equalsIgnoreCase("-1") && service.equalsIgnoreCase("ARMY")) {
				persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
			} else if (service.equalsIgnoreCase("OTHERS")) {
				persnl_no = null;
			}
			
			String category = request.getParameter("category");
			String icd_cd[] = request.getParameter("icd_code").split("-");
			String icd_cau[] = request.getParameter("icd_cause").split("-");

			if(request.getParameter("unit_name") == null || request.getParameter("unit_name").equals(" ")){ 
				model.put("msg", "Please Enter the Hospital Name");
				return new ModelAndView("redirect:edit_mortality_Input");
			}
		
		/*		if(request.getParameter("sus_no") == null || request.getParameter("sus_no").equals("")){ 
	            model.put("msg", "Please Enter the SUS No");
	            return new ModelAndView("redirect:edit_mortality_Input");
	             }  
				 if(validation.sus_noLength(request.getParameter("sus_no")) == false){
					 model.put("msg",validation.sus_noMSG);
		                return new ModelAndView("redirect:edit_mortality_Input");
		             }*/
			
					 if((service.equalsIgnoreCase("ARMY")) && (persnl_no1.equalsIgnoreCase("-1") || persnl_no2 == null || persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1")))
		                {
		            	model.put("msg", "Please Select the Personnel No.");
		                    return new ModelAndView("redirect:edit_mortality_Input");
		                }				
						if(service.equalsIgnoreCase("ARMY"))
		                {
		               	 persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
		                }
						else if(service.equalsIgnoreCase("OTHERS"))
		                {
		       		       persnl_no = null;
		                }
						 if(validation.persnl_noLength(request.getParameter("persnl_no2")) == false){
				                model.put("msg",validation.persnl_no2MSG);
				               return new ModelAndView("redirect:edit_mortality_Input");
				               }	 
					
							if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
								model.put("msg", "Please Select the Category");
								return new ModelAndView("redirect:edit_mortality_Input");
							}
					  /* if(rs.getDeath_rank().getId() == -1){  
		                   model.put("msg", "Please Select the rank");
		                   return new ModelAndView("redirect:edit_mortality_Input");
		                   } */
				
				   if(request.getParameter("gender").equals("-1") || request.getParameter("gender") == "-1" || request.getParameter("gender") == null || request.getParameter("gender").equals("")){  
	                   model.put("msg", "Please Select the Gender");
	                   return new ModelAndView("redirect:edit_mortality_Input");
	                   } 
				   if(request.getParameter("category").equals("MNS") && !request.getParameter("gender").equals("FEMALE")  ) {
					   model.put("msg", "Gender should be FEMALE Here");
						return new ModelAndView("redirect:edit_mortality_Input");
					}
				   if(request.getParameter("relation").equals("-1") || request.getParameter("relation") == "-1" || request.getParameter("relation") == null || request.getParameter("relation").equals("")){  
	                   model.put("msg", "Please Select the Relation");
	                   return new ModelAndView("redirect:edit_mortality_Input");
	                   } 
				 
				   if (((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF")
							|| relation.equals("SISTEROF")) ) && (!request.getParameter("gender").toString().toLowerCase().equals("female"))) {
						model.put("msg", "Gender should be Female Here");
						return new ModelAndView("redirect:edit_mortality_Input");
					} 
			
				 if (request.getParameter("date_of_birth2") == null || request.getParameter("date_of_birth2").equals("")) {
						model.put("msg", "Please Select the Date of  Birth");
						return new ModelAndView("redirect:edit_mortality_Input");
					} else {

						date_of_birth_i = formatter1.parse(request.getParameter("date_of_birth2"));
					}
				
				   if(request.getParameter("place_of_death").equals("-1") || request.getParameter("place_of_death") == "-1" || request.getParameter("place_of_death") == null || request.getParameter("place_of_death").equals("")){  
	                   model.put("msg", "Please Select the Place of Death");
	                   return new ModelAndView("redirect:edit_mortality_Input");
	                  }
				   
				   SimpleDateFormat YY = new SimpleDateFormat("yyyy");
					SimpleDateFormat MM = new SimpleDateFormat("MM");
					SimpleDateFormat dd = new SimpleDateFormat("dd");
					int year = Integer.parseInt(YY.format(date_of_birth_i));
					int MM1 = Integer.parseInt(MM.format(date_of_birth_i));
					int Dd = Integer.parseInt(dd.format(date_of_birth_i));
			
					LocalDate today = LocalDate.now(); // Today's date
					LocalDate birthday = LocalDate.of(year, MM1, Dd);// Birth date
					Period p = Period.between(birthday, today);
					
					Integer age_year = 0;

					if (request.getParameter("age_year") != "" && !request.getParameter("age_year").equals("")) {
						age_year = p.getYears();
					}
				if (relation.equals("SELF") &&  request.getParameter("date_of_birth2") != "" && request.getParameter("age_year") != "" && age_year < 17) {
					model.put("msg", "Personnel Age Year Not Allowed Below 17");
					return new ModelAndView("redirect:edit_mortality_Input");
				}
			
				String total_service_month = request.getParameter("total_service_month1");
				Integer total_service_month1 = 0;
				if (total_service_month != "" && !total_service_month.equals("")) {
					total_service_month1 = Integer.parseInt(total_service_month);
				}
				String total_service_year = request.getParameter("total_service_year1");
				Integer total_service_year1 = 0;
				if (total_service_year != "" && !total_service_year.equals("")) {
					total_service_year1 = Integer.parseInt(total_service_year);
				}
			if (total_service_year1 > age_year) {
				model.put("msg", "Service Year Should Not More Than Age Year");
				return new ModelAndView("redirect:edit_mortality_Input");
			}
		   
			   if(request.getParameter("place_of_death").equals("-1") || request.getParameter("place_of_death") == "-1" || request.getParameter("place_of_death") == null || request.getParameter("place_of_death").equals("")){  
				   model.put("msg", "Please Select the Place of Death");
                  return new ModelAndView("redirect:edit_mortality_Input");
                 }
			     
				   if(request.getParameter("nature_of_death").equals("-1") || request.getParameter("nature_of_death") == "-1" || request.getParameter("nature_of_death") == null || request.getParameter("nature_of_death").equals("")){  
	                   model.put("msg", "Please Select the Nature of Death");
	                   return new ModelAndView("redirect:edit_mortality_Input");
	                  }
				   if (request.getParameter("dt_mortality2") == null || request.getParameter("dt_mortality2").equals("")) {
						model.put("msg", "Please Select the Date of Mortality");
						return new ModelAndView("redirect:edit_mortality_Input");
					}
					else {
						dt_mortality_i = formatter1.parse(request.getParameter("dt_mortality2"));
					}
				   
				   if(request.getParameter("icd_code") == null || request.getParameter("icd_code").equals("")){ 
			            model.put("msg", "Please Enter the ICD Code");
			            return new ModelAndView("redirect:edit_mortality_Input");
			             } 
			
				 /*  if(validation.DiseasemmrLength(rs.getPersnl_name()) == false){
		                 model.put("msg",validation.persnl_nameMSG);
		                return new ModelAndView("redirect:edit_mortality_Input");
		                }
					   if(validation.DiseasemmrLength(rs.getPersnl_unit()) == false){
			                model.put("msg",validation.persnl_unitMSG);
			               return new ModelAndView("redirect:edit_mortality_Input");
			               }
					   if(validation.DiseasemmrLength(rs.getOperation()) == false){
			                model.put("msg",validation.oprationMSG);
			               return new ModelAndView("redirect:edit_mortality_Input");
			               }
					   if(validation.DiseaseFamilyLength(rs.getSector()) == false){
			                model.put("msg",validation.sectorMSG);
			               return new ModelAndView("redirect:edit_mortality_Input");
			               }
					   if(validation.DiseasemmrLength(rs.getLocation()) == false){
			                model.put("msg",validation.locationMSG);
			               return new ModelAndView("redirect:edit_mortality_Input");
			               }
					   if(validation.DiseasemmrLength(rs.getBde_div_corps_comd()) == false){
			                model.put("msg",validation.bde_div_corps_comdMSG);
			               return new ModelAndView("redirect:edit_mortality_Input");
			               }
					   if(validation.and_noLength(rs.getAnd_no()) == false){
			                model.put("msg",validation.and_noMSG);
			               return new ModelAndView("redirect:edit_mortality_Input");
			               }
					   if(validation.IcdDescriptionLength(rs.getDeath_summary()) == false){
			                model.put("msg",validation.death_summaryMSG);
			               return new ModelAndView("redirect:edit_mortality_Input");
			               }
					   if(validation.DiseasemmrLength(rs.getName_of_nok()) == false){
			                model.put("msg",validation.name_of_nokMSG);
			               return new ModelAndView("redirect:edit_mortality_Input");
			               }
					   if(validation.IcdDescriptionLength(rs.getAddress_of_nok()) == false){
			                model.put("msg",validation.address_of_nokMSG);
			               return new ModelAndView("redirect:edit_mortality_Input");
			               }
					   if(validation.IcdDescriptionLength(rs.getCause_of_death()) == false){
			                model.put("msg",validation.cause_of_deathMSG);
			               return new ModelAndView("redirect:edit_mortality_Input");
			               }
					
					   if(validation.IcdDescriptionLength(rs.getRemarks()) == false){
			                model.put("msg",validation.remarksMSG);
			               return new ModelAndView("redirect:edit_mortality_Input");
			               }*/
			Session session1 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			 try {
	            	
				  Long d= mo.checkExitstingMortality(sus_no,persnl_no,rs.getId());
                    rs.setModified_by(username);
                    rs.setModified_on(curr_date);
                    rs.setPersnl_no(persnl_no);
                    rs.setDate_of_death(dt_mortality_i);
                    rs.setDate_of_birth(date_of_birth_i);
	               	rs.setTotal_service_year(total_service_year1);
	    			rs.setTotal_service_month(total_service_month1);
	    			rs.setContact_no(contact_no);
					rs.setAdhar_card_no(adhar_card_no);
					rs.setIcd_code(icd_cd[0]);
					rs.setIcd_desc(icd_cau[0]);
       			
       			if (d == 0) {
  					
					  model.put("msg", mo.updatemortality(rs, username));
				} 
 				if (d > 0) 
 					
 				{
 					model.put("msg", "Data already Exist.");
				}
 			   
			  }catch(RuntimeException e){
	              try{
	                      tx.rollback();
	                      model.put("msg", "roll back transaction");
	              }catch(RuntimeException rbe){
	                      model.put("msg", "Couldn’t roll back transaction " + rbe);
	              }
	              throw e;
	             
			}finally{
				if(session1!=null){
					session1.close();
				}
			}
		return new ModelAndView("redirect:mnh_input_search_mortality");
	}
	
	@RequestMapping(value = "/delete_mortality" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView delete_mortality(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		 Boolean val = roledao.ScreenRedirect("mnh_input_search_mortality", sessionA.getAttribute("roleid").toString());
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
			 
			String hqlUpdate = "delete from Tb_Med_Death where id=:id";
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
		return new ModelAndView("mnh_input_mortality_searchTiles");
	}
	
	
}
