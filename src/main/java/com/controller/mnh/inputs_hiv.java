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
import com.dao.mnh.mnh_hivDAOImpl;
import com.models.mnh.Tb_Med_HIV;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class inputs_hiv {
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private mnh_hivDAOImpl mnh4_Dao;

	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	MNH_CommonController mcommon = new MNH_CommonController();
	
	MNH_ValidationController validation = new MNH_ValidationController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	
	// INPUTS MODULE -> (HIV SEARCH SCREEN START)
	
	@RequestMapping(value = "/admin/mnh_input_hiv", method = RequestMethod.GET)
	public ModelAndView mnh_input_hiv(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		Boolean val = roledao.ScreenRedirect("mnh_input_hiv", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
         if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
       
        
        
		Mmap.put("getMedSystemCode_PERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		Mmap.put("getMedSystemCode_PERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		Mmap.put("getMedSystemCode_SERVICE", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("getMedSystemCode_CATEGORY", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("getMedSystemCode_SEX", mcommon.getMedSystemCode("SEX", "", session));
		Mmap.put("ml_6", mcommon.getMedrelationList("", session));
		Mmap.put("getMedSystemCode_MRTLSTAT", mcommon.getMedSystemCode("MRTLSTAT", "", session));
		Mmap.put("getMedSystemCode_CONFRMTEST", mcommon.getMedSystemCode("CONFRMTEST", "", session));
		Mmap.put("getMedSystemCode_TYPE", mcommon.getMedSystemCode("TYPE", "", session));
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_input_hivTiles");
	}
	
	
	/*-------------save-------------------*/
	
	@RequestMapping(value = "/Hiv_aids_DetailsAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView Hiv_aids_DetailsAction(@ModelAttribute("Hiv_aids_DetailsCMD") Tb_Med_HIV rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

	 		
		Boolean val = roledao.ScreenRedirect("mnh_input_hiv", session.getAttribute("roleid").toString());

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		String username = session.getAttribute("username").toString();
		String id = request.getParameter("id_hid");
		String type = request.getParameter("type");
		String sus_no = request.getParameter("sus_no");
		String service = request.getParameter("service");
		String relation = request.getParameter("relation");
        String sex = request.getParameter("sex");
        String category = request.getParameter("category");
		 
		 BigInteger adhar_card_no =BigInteger.ZERO;
         if(!request.getParameter("adhar_card_no") .equals("")) {
        	 adhar_card_no =new BigInteger(request.getParameter("adhar_card_no"));
		 }
         BigInteger contact_no =BigInteger.ZERO;            
         if(!request.getParameter("contact_no1") .equals("")){
         	contact_no =new BigInteger(request.getParameter("contact_no1"));
		     }
         
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date_of_birth_i = null;
		Date r_date_i = null;
		Date rr_date_i = null;
		
		Date possible_date_siwsw = null;
		String p_date = request.getParameter("p_date");
		if (!p_date.equals("")) {
			possible_date_siwsw = formatter1.parse(p_date);
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
		if (request.getParameter("unit_name") == null || request.getParameter("unit_name").equals("")) {
			model.put("msg", "Please Enter the Confirming Center");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		if (request.getParameter("sus_no") == null || request.getParameter("sus_no").equals("")) {
			model.put("msg", "Please Enter the SUS No");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		
		if (request.getParameter("accession_no") == null || request.getParameter("accession_no").equals("")) {
			model.put("msg", "Please Enter the Accession No");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		
		if (request.getParameter("type") == null || request.getParameter("type").equals("-1")) {
			model.put("msg", "Please Select Type");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		if (request.getParameter("service") == null || request.getParameter("service").equals("-1")) {
			model.put("msg", "Please Select the Service");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		 

		if (request.getParameter("sex") == null || request.getParameter("sex").equals("-1")) {
			model.put("msg", "Please Select the Gender");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		if(!request.getParameter("sex").equals("FEMALE") && request.getParameter("category").equals("MNS")) {
			model.put("msg", "Gender should be FEMALE Here");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		if (persnl_no2.equals("") && !service.equals("OTHERS")) {
			model.put("msg", "Please Enter the Personnel No");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		if (persnl_no3.equals("-1") && !service.equals("OTHERS")) {
			model.put("msg", "Please Enter the Personnel No");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		if (request.getParameter("category") == null || request.getParameter("category").equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
			model.put("msg", "Please Select the Category");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		/*if (rs.getHivaid_rank().getId() == -1  && !service.equalsIgnoreCase("OTHERS")) {
			model.put("msg", "Please Select the Rank");
			return new ModelAndView("redirect:mnh_input_hiv");
		}*/
		if (request.getParameter("persnl_name") == null || request.getParameter("persnl_name").equals("")) {
			model.put("msg", "Please Enter the Personnal Name");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		if (request.getParameter("relation") == null || request.getParameter("relation").equals("-1")) {
			model.put("msg", "Please Select the Relation");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		
		if (sex.equals("-1") || sex == "-1" || sex.equals("") || sex == null) {
			model.put("msg", "Please Enter the Gender");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		if (relation.equals("-1") || relation == "-1" || relation.equals("") || relation == null) {
			model.put("msg", "Please Enter the Relation");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		if((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF") || 
			relation.equals("SISTEROF")) && !sex.equalsIgnoreCase("Female")) {
			 model.put("msg", "Gender should be Female Here");
			return new ModelAndView("redirect:mnh_input_hiv");
			}
		
		if((relation.equals("BROTHEROF") || relation.equals("HUSBANDOF") || relation.equals("FATHEROF") || 
		  relation.equals("SONOF")) && !sex.equalsIgnoreCase("Male")) {
			model.put("msg", "Gender should be Male Here");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		if (category.equals("MNS") && !sex.equalsIgnoreCase("Female")) {
			model.put("msg", "Gender should be Female Here");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		
		
		if (request.getParameter("date_of_birth1") == null || request.getParameter("date_of_birth1").equals("")) {
			model.put("msg", "Please Select the Date of  Birth");
			return new ModelAndView("redirect:mnh_input_hiv");
		} else {

			date_of_birth_i = formatter1.parse(request.getParameter("date_of_birth1"));
		}
		
		

		 SimpleDateFormat YY = new SimpleDateFormat("yyyy");
	        SimpleDateFormat MM = new SimpleDateFormat("MM");
	        SimpleDateFormat dd = new SimpleDateFormat("dd");
	        int year = Integer.parseInt(YY.format(date_of_birth_i));   	     
	        int MM1 = Integer.parseInt(MM.format(date_of_birth_i));
	        int Dd = Integer.parseInt(dd.format(date_of_birth_i));
	        
	        int total_service = Integer.parseInt(request.getParameter("total_service"));
          
	         LocalDate today = LocalDate.now();	 //Today's date
	         LocalDate birthday = LocalDate.of(year,MM1,Dd);//Birth date
	         Period p = Period.between(birthday, today);
	           Integer age_year = 0;
	    		if (request.getParameter("age_year") != "" && !request.getParameter("age_year").equals("")) {
	    			age_year = p.getYears();	    			
	    		}
	    	 
	    		if (relation.equals("SELF") &&  request.getParameter("date_of_birth1") != "" && request.getParameter("age_year") != "" && age_year < 17) {
					model.put("msg", "Personnel Age Year Not Allowed Below 17");
					return new ModelAndView("redirect:mnh_input_hiv");
				}
		
	          if (total_service > age_year) {
	                 model.put("msg", "Service Year Should Not More Than Age Year");
	                 return new ModelAndView("redirect:mnh_input_hiv");
	         }
	          
	         if (request.getParameter("reasons_elisa_screening") == null) {
	  			model.put("msg", "Please Select At least One Reasons for ELISA Screening");
	  			return new ModelAndView("redirect:mnh_input_hiv");
	  		} 
	         
	         if (request.getParameter("source_infection") == null) {
	 			model.put("msg", "Please Select At least One Possible Source of infection");
	 			return new ModelAndView("redirect:mnh_input_hiv");
	 		}
		if (request.getParameter("name_confirmatory_test") == null || request.getParameter("name_confirmatory_test").equals("-1")) {
			model.put("msg", "Please Select the Confirming Test");
			return new ModelAndView("redirect:mnh_input_hiv");
		}
		
		if (request.getParameter("r_date1") == null || request.getParameter("r_date1").equals("")) {
			model.put("msg", "Please Select the Date of  Reporting");
			return new ModelAndView("redirect:mnh_input_hiv");
		} else {

			r_date_i = formatter1.parse(request.getParameter("r_date1"));
		}
		if (request.getParameter("rr_date1") == null || request.getParameter("rr_date1").equals("")) {
			model.put("msg", "Please Select the Date of Receving");
			return new ModelAndView("redirect:mnh_input_hiv");
		} else {
			rr_date_i = formatter1.parse(request.getParameter("rr_date1"));
		}
	 
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {

			Long hivExist_cond = mnh4_Dao.checkExitstinghivinput(sus_no, type, persnl_no, id);
			if (hivExist_cond != (long) 0) {
				model.put("msg", "sus no  already exists against Type,Personal No.");
				return new ModelAndView("redirect:mnh_input_hiv");
			}

			rs.setCreated_on(new Date());
			rs.setCreated_by(username);
			rs.setPersnl_no(persnl_no);
			rs.setSex(request.getParameter("sex"));
			rs.setContact_no(contact_no);
			rs.setAdhar_card_no(adhar_card_no);
			rs.setOther_details_elisa_screening(request.getParameter("other_details_elisa_screening"));
			rs.setOther_details_source_infection(request.getParameter("other_details_source_infection"));
			rs.setPossible_place_siwsw(request.getParameter("possible_place_siwsw"));
			rs.setOther_details_source_infection(request.getParameter("other_details_source_infection"));
			rs.setDate_of_birth(date_of_birth_i);
			rs.setReport_date(r_date_i);
			rs.setReport_received_on(rr_date_i);
			rs.setPossible_date_siwsw(possible_date_siwsw);
			sessionHQL.save(rs);
			sessionHQL.flush();
			sessionHQL.clear();
			model.put("msg", "Data saved Successfully");

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn’t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:mnh_input_hiv");
	}
	
	 
		/*--------SEARCH page open--------*/
	
	@RequestMapping(value = "/admin/mnh_input_hiv_search", method = RequestMethod.GET)
	public ModelAndView mnh_input_hiv_search(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		Boolean val = roledao.ScreenRedirect("mnh_input_hiv_search", session.getAttribute("roleid").toString());
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
		
		Mmap.put("ml_6", mcommon.getMedrelationList("", session));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_input_hiv_searchTiles");
	}
	
	/*Search report */
	@RequestMapping(value = "/search_hiv_Input",method = RequestMethod.POST)
	public ModelAndView search_hiv_Input(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("sus1") String sus1,
			String unit1, String psn_no, String frm_dt1, String to_dt1,String adhar1,String contact1,String relation1,HttpServletRequest request) {

		int userid = (Integer) session.getAttribute("userId");


		Boolean val = roledao.ScreenRedirect("mnh_input_hiv_search", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		if (unit1.equals("") || unit1 == null) {
			model.put("msg", "Please Enter the Unit Name");
			return new ModelAndView("redirect:mnh_input_hiv_search");
		}
		if (sus1.equals("") || sus1 == null) {
			model.put("msg", "Please Enter the SUS No");
			return new ModelAndView("redirect:mnh_input_hiv_search");
		}
		 if (frm_dt1.equals("") || frm_dt1 == "") {
			 model.put("msg", "Please Select From Date");
				return new ModelAndView("redirect:mnh_input_hiv_search");
			}
		
		 if (to_dt1.equals("") || to_dt1 == "") {
			 model.put("msg", "Please Select To Date");
				return new ModelAndView("redirect:mnh_input_hiv_search");
			}
		 if (validation.adhar_noLength(adhar1) == false) {
			 model.put("msg", validation.adharnoMSG);
				return new ModelAndView("redirect:mnh_input_hiv_search");
			}
		 if(validation.DiseasemmrLength(unit1) == false){
				model.put("msg",validation.hospital_nameMSG);
				return new ModelAndView("redirect:mnh_input_hiv_search");
			 }
			 if(validation.sus_noLength(sus1) == false){
				model.put("msg",validation.sus_noMSG);
				return new ModelAndView("redirect:mnh_input_hiv_search");
			 }
		if(validation.RankCodeLength(frm_dt1)  == false){
			model.put("msg",validation.from_dateMSG);
	             return new ModelAndView("redirect:mnh_input_hiv_search");
	         }
		 	
	 	 if(validation.RankCodeLength(to_dt1)  == false){
	 		model.put("msg",validation.to_dateMSG);
	             return new ModelAndView("redirect:mnh_input_hiv_search");
	         }
			
		
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);

		model.put("r_1", l1);
		
		
		 String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
		
			String f_sus_no=sus1;
			if(roleAccess.equals("Unit")){
				model.put("sus_no",roleSusNo);
				model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				f_sus_no = roleSusNo;
				
			}
		
		
		List<Map<String, Object>> list = mnh4_Dao.search_hiv_Input(sus1,unit1, psn_no, frm_dt1, to_dt1,adhar1,contact1,relation1);
		model.put("list", list);
		model.put("size", list.size());
		
		model.put("getMedSystemCode_PERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		model.put("getMedSystemCode_PERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		model.put("ml_6", mcommon.getMedrelationList("", session));
		
		model.put("sus1", f_sus_no);
		model.put("unit1", unit1);
		model.put("psn_no", psn_no);
		model.put("frm_dt1", frm_dt1);
		model.put("to_dt1", to_dt1);
		model.put("adhar1", adhar1);
		model.put("contact1", contact1);
		model.put("relation1", relation1);
		
		return new ModelAndView("mnh_input_hiv_searchTiles");
	}

	/*delete*/
	@RequestMapping(value = "/delete_hiv_Input", method = RequestMethod.POST )
	public ModelAndView delete_hiv_Input(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			ModelMap model, HttpSession session1) {
		List<String> liststr = new ArrayList<String>();
	
		Boolean val = roledao.ScreenRedirect("mnh_input_hiv_search", session1.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		try {
		
			String hqlUpdate = "delete from Tb_Med_HIV where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			model.put("msg",liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:mnh_input_hiv_search");
	}
	
	/*--------Edit Page Open---------*/
		
		
		@RequestMapping(value = "/edit_hiv_Input")
		public ModelAndView edit_hiv_Input(@ModelAttribute("id2") String updateid, ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) {
			
			 Boolean val = roledao.ScreenRedirect("mnh_input_hiv_search", sessionEdit.getAttribute("roleid").toString());
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				
				

				Date date = new Date();
				String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
				Mmap.put("date", date1);
				
				Tb_Med_HIV hiv_Details = mnh4_Dao.gethivDetail(Integer.parseInt(updateid));
			
				Mmap.put("Hiv_aids_edit_DetailsCMD", hiv_Details);
				
				Mmap.put("getMedSystemCode_PERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", sessionEdit));
				Mmap.put("getMedSystemCode_PERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", sessionEdit));
				Mmap.put("getMedSystemCode_SERVICE", mcommon.getMedSystemCode("SERVICE", "", sessionEdit));
				Mmap.put("getMedSystemCode_CATEGORY", mcommon.getMedSystemCode("CATEGORY", "", sessionEdit));
				Mmap.put("getMedSystemCode_SEX", mcommon.getMedSystemCode("SEX", "", sessionEdit));
				Mmap.put("ml_6", mcommon.getMedrelationList("", sessionEdit));
				Mmap.put("getMedSystemCode_MRTLSTAT", mcommon.getMedSystemCode("MRTLSTAT", "", sessionEdit));
				Mmap.put("getMedSystemCode_CONFRMTEST", mcommon.getMedSystemCode("CONFRMTEST", "", sessionEdit));
				Mmap.put("getMedSystemCode_TYPE", mcommon.getMedSystemCode("TYPE", "", sessionEdit));
				Mmap.put("date", date1);
				Mmap.put("msg", msg);
				
				 
				
			return new ModelAndView("edit_hiv_InputTiles");
		}	
		
		/*--------Edit ---------*/
		
		@RequestMapping(value = "/Hiv_aids_edit_DetailsAction", method = RequestMethod.POST )
		public ModelAndView Hiv_aids_edit_DetailsAction(@ModelAttribute("Hiv_aids_edit_DetailsCMD") Tb_Med_HIV  lm,
				@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {
			
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("mnh_input_hiv_search", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
           return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			
			int id = lm.getId() > 0 ? lm.getId() : 0;			
				Mmap.put("id2", lm.getId());
				
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				String roleType = session.getAttribute("roleType").toString();
				
				if(roleAccess.equals("Unit")){
					Mmap.put("sus_no",roleSusNo);
					Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
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
		        Date date = new Date();
				String username = session.getAttribute("username").toString();
				
				
				Date r_date_i = null;
				Date rr_date_i = null;
				Date date_of_birth_i = null;
				
		        DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		        DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
		        
		        Date possible_date_siwsw = null;
				String p_date = request.getParameter("p_date");
				if (!p_date.equals("")) {
					possible_date_siwsw = formatter1.parse(p_date);
				}       
		        String sus_no1 = request.getParameter("sus_no1");		       
		        String accession_no = request.getParameter("accession_no");
		        String service = request.getParameter("service");
		        String type = request.getParameter("type");
		        String sex = request.getParameter("sex");	        
		        
		        String persnl_no1 = request.getParameter("persnl_no1");
		        String persnl_no2 = request.getParameter("persnl_no2");
		        String persnl_no3 = request.getParameter("persnl_no3");
		        String persnl_no = null;
		        String category = request.getParameter("category");
		       
		        String persnl_unit = request.getParameter("persnl_unit");
		        String persnl_name = request.getParameter("persnl_name");
		       String age_year1 = request.getParameter("age_year");
		       String date_of_birth = request.getParameter("date_of_birth1");
		       
				String relation = request.getParameter("relation");
				String reasons_elisa_screening[] = request.getParameterValues("reasons_elisa_screening");
				String source_infection[] = request.getParameterValues("source_infection");
				String possible_place_siwsw = request.getParameter("possible_place_siwsw");
				String name_confirmatory_test = request.getParameter("name_confirmatory_test");				
				String r_date = request.getParameter("r_date");
				String rr_date = request.getParameter("rr_date");
				int total_service = Integer.parseInt(request.getParameter("total_service"));
				
				if (!date_of_birth.equals("")) {
					date_of_birth_i = formatter1.parse(date_of_birth);
				}
				SimpleDateFormat YY = new SimpleDateFormat("yyyy");
				SimpleDateFormat MM = new SimpleDateFormat("MM");
				SimpleDateFormat dd = new SimpleDateFormat("dd");
				int year = Integer.parseInt(YY.format(date_of_birth_i));
				int MM1 = Integer.parseInt(MM.format(date_of_birth_i));
				int Dd = Integer.parseInt(dd.format(date_of_birth_i));
		    
				LocalDate today = LocalDate.now(); // Today's date
				LocalDate birthday1 = LocalDate.of(year, MM1, Dd);// Birth date
				Period p = Period.between(birthday1, today);
				Integer age_year = 0;
				if (age_year1 != "" && !age_year1.equals("")) {
					age_year = p.getYears();
				}
				
			try {
				if (sus_no1 == null || sus_no1.equals("")) {
					Mmap.put("msg", "Please Enter the SUS No");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if (service.equals("-1") || service.equals("")) {
					Mmap.put("msg", "Please Select the Service");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if (accession_no == null || accession_no.equals("")) {
					Mmap.put("msg", "Please Select the Accession_no");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if (type.equals("-1")|| type.equals("")) {
					Mmap.put("msg", "Please Select the Type");
					return new ModelAndView("redirect:edit_hiv_Input");
				}

				/*if ((service.equalsIgnoreCase("ARMY")) && (persnl_no1.equalsIgnoreCase("-1") || persnl_no2 == null
						|| persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1"))) {
					Mmap.put("msg", "Please Select the Personnel No.");
					return new ModelAndView("redirect:edit_hiv_Input");
				}*/
				if ((!service.equalsIgnoreCase("ARMY") && !service.equalsIgnoreCase("OTHERS"))
						&& (persnl_no2 == null || persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1"))) {

					Mmap.put("msg", "Please Select the Personnel No896.");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if (service.equalsIgnoreCase("ARMY")) {
					persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
				} else if (service.equalsIgnoreCase("OTHERS")) {
					persnl_no = null;
				} else {
					persnl_no = persnl_no2 + persnl_no3;
				}
				if (category.equals("-1") && service.equalsIgnoreCase("OTHERS")) {
					category = "";
				}				
				
			
				if (sex.equals("-1") || sex == "-1" || sex.equals("") || sex == null) {
					Mmap.put("msg", "Please Select the Gender");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
			 
				if (request.getParameter("category") == null || request.getParameter("category").equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
					Mmap.put("msg", "Please Select the Category");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
					Mmap.put("msg", "Please Select the Category");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if(!request.getParameter("sex").equals("FEMALE") && request.getParameter("category").equals("MNS")) {
					Mmap.put("msg", "Gender should be FEMALE Here");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
			
			/*	if  (lm.getHivaid_rank().getId() == -1  && !service.equalsIgnoreCase("OTHERS")) {
					Mmap.put("msg", "Please Select the Rank");
					return new ModelAndView("redirect:edit_hiv_Input");
				}*/
				if (persnl_name == null || persnl_name.equals("")) {
					Mmap.put("msg", "Please Select the  Personnel Name");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if (persnl_name.equals("") && !service.equalsIgnoreCase("OTHERS")) {
					Mmap.put("msg", "Please Enter the Personnal Name");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				
					
					if (relation.equals("-1") || relation == "-1" || relation.equals("") || relation == null) {
						Mmap.put("msg", "Please Enter the Relation");
						return new ModelAndView("redirect:edit_hiv_Input");
					}
					if((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF") || 
						relation.equals("SISTEROF")) && !sex.equalsIgnoreCase("Female")) {
						Mmap.put("msg", "Gender should be Female Here");
						return new ModelAndView("redirect:edit_hiv_Input");
						}
					
					if((relation.equals("BROTHEROF") || relation.equals("HUSBANDOF") || relation.equals("FATHEROF") || 
					  relation.equals("SONOF")) && !sex.equalsIgnoreCase("Male")) {
						Mmap.put("msg", "Gender should be Male Here");
						return new ModelAndView("redirect:edit_hiv_Input");
					}
					if (category.equals("MNS") && !sex.equalsIgnoreCase("Female")) {
						Mmap.put("msg", "Gender should be Female Here");
						return new ModelAndView("redirect:edit_hiv_Input");
					}
						
				
				if (request.getParameter("date_of_birth1") == null || request.getParameter("date_of_birth1").equals("")) {
					Mmap.put("msg", "Please Select the Date of  Birth");
					return new ModelAndView("redirect:edit_hiv_Input");
				} else {
	
					date_of_birth_i = formatter1.parse(request.getParameter("date_of_birth1"));
				}
			 
				if (relation.equals("SELF") &&  date_of_birth != "" && age_year1 != "" && age_year < 17) {
					Mmap.put("msg", "Personnel Age Year Not Allowed Below 17");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
			
				if (total_service > age_year) {
					Mmap.put("msg", "Service Year Should Not More Than Age Year");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if (!p_date.equals("")) {
					possible_date_siwsw = formatter1.parse(p_date);
				}
				 
				if (name_confirmatory_test.equals("-1") && !name_confirmatory_test.equals("")) {
					Mmap.put("msg", "Please Enter the name_confirmatory_test");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if (request.getParameter("r_date1") == null || request.getParameter("r_date1").equals("")) {
					Mmap.put("msg", "Please Select the Date of  Reporting");
					return new ModelAndView("redirect:edit_hiv_Input");
				} else {

					r_date_i = formatter1.parse(request.getParameter("r_date1"));
				}
				if (request.getParameter("rr_date1") == null || request.getParameter("rr_date1").equals("")) {
					Mmap.put("msg", "Please Select the Date of Receving");
					return new ModelAndView("redirect:edit_hiv_Input");
				} else {

					rr_date_i = formatter1.parse(request.getParameter("rr_date1"));
				}				
			
				if (request.getParameter("reasons_elisa_screening") == null || request.getParameter("reasons_elisa_screening").length() == 0
						|| request.getParameter("reasons_elisa_screening").equals(""))
				{
					Mmap.put("msg", "Please Select the Reasons Elisa Screening");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if (request.getParameter("source_infection") == null || request.getParameter("source_infection").length() == 0
						|| request.getParameter("source_infection").equals(""))
				{
					Mmap.put("msg", "Please Select the Source Infection");
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				
				
				//////length validation////////
				

				
				if (validation.sus_noLength(sus_no1) == false) {
					Mmap.put("msg", validation.sus_noMSG);
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if (validation.accession_noLength(accession_no) == false) {
					Mmap.put("msg", validation.accession_noMSG);
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if (validation.persnl_noLength(persnl_no2) == false) {
					Mmap.put("msg", validation.persnl_no2MSG);
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if (validation.DiseasemmrLength(persnl_name) == false) {
					Mmap.put("msg", validation.persnl_nameMSG);
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				if (validation.MessageLength(persnl_unit) == false) {
					Mmap.put("msg", validation.persl_unitMSG);
					return new ModelAndView("redirect:edit_hiv_Input");
				}
				 
				if(validation.DiseaseFamilyLength(lm.getPossible_place_siwsw()) == false){
		        	Mmap.put("msg",validation.possible_place_siwswMSG);
	               return new ModelAndView("redirect:edit_hiv_Input");
	               }
		        if(validation.MessageLength(lm.getOther_details_elisa_screening()) == false){
		        	Mmap.put("msg",validation.other_details_elisa_screeningMSG);
	               return new ModelAndView("redirect:edit_hiv_Input");
	               }
		        if(validation.MessageLength(lm.getOther_details_source_infection()) == false){
		        	Mmap.put("msg",validation.other_details_source_infectionMSG);
	               return new ModelAndView("redirect:edit_hiv_Input");
	               }
				

				Long hivExist_cond= mnh4_Dao.checkExitstinghivinput(sus_no1,type,persnl_no,String.valueOf(lm.getId()));
		   	    if(hivExist_cond != (long)0) {
		   	    	Mmap.put("msg", "sus no  already exists against Type,Personal No.");
		   	    	return new ModelAndView("redirect:edit_hiv_Input");
		   	    }
		   	    
				lm.setModified_by(username);
				lm.setModified_on(date);
				lm.setPersnl_no(persnl_no);
				
				lm.setSex(sex);
				lm.setReasons_elisa_screening(String.join(",", reasons_elisa_screening));
				lm.setSource_infection(String.join(",", source_infection));				
				lm.setPossible_date_siwsw(possible_date_siwsw);
				lm.setReport_date(r_date_i);
				lm.setReport_received_on(rr_date_i);
				lm.setContact_no(contact_no);
				lm.setAdhar_card_no(adhar_card_no);
				

				if (hivExist_cond == 0) {

					Mmap.put("msg", mnh4_Dao.update_hiv(lm, username));
				}
				if (hivExist_cond > 0)

				{
					Mmap.put("msg", "Data already Exist.");
				}

			} catch (RuntimeException e) {
				try {

					Mmap.put("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					Mmap.put("msg", "Couldn’t roll back transaction " + rbe);
				}
				throw e;
			} finally {

			}
			return new ModelAndView("redirect:mnh_input_hiv_search");
		}
	
	
		
}
