package com.controller.mnh;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.Daily_morning_bed_stateDaoImpl;
import com.models.mnh.Tb_Med_Daily_Bed_Occupancy;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class dgms_input_Daily_morning_bed_stateController {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private Daily_morning_bed_stateDaoImpl mb;
	MNH_CommonController mcommon = new MNH_CommonController();

	MNH_ValidationController validation = new MNH_ValidationController();
	
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();

	// *******************Note::open url daily morning bed
	// state*****************************************//

	@RequestMapping(value = "/mnh_bed_statusDA", method = RequestMethod.GET)
	public ModelAndView mnh_bed_statusDA(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		 Boolean val = roledao.ScreenRedirect("mnh_bed_statusDA", session.getAttribute("roleid").toString());
         if(val == false) {
           return new ModelAndView("AccessTiles");
          }
		  if(request.getHeader("Referer") == null ) {
			  msg = "";
			  return new ModelAndView("redirect:bodyParameterNotAllow"); 
		  }
		  
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			
			
			if(roleAccess.equals("Unit")){
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				
				
			}
		  
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		Mmap.put("msg", msg);
		Mmap.put("date", date1);

		return new ModelAndView("mnh_bed_statusDATiles");
	}

	// *******************Note::End*****************************************//

	// *******************Note::open url search daily morning bed
	// state*****************************************//
	@RequestMapping(value = "/mnh_search_bed_statusDA", method = RequestMethod.GET)
	public ModelAndView mnh_search_bed_statusDA(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		 Boolean val = roledao.ScreenRedirect("mnh_search_bed_statusDA", session.getAttribute("roleid").toString());
         if(val == false) {
           return new ModelAndView("AccessTiles");
          }
		  if(request.getHeader("Referer") == null ) {
			  msg = ""; 
			  return new ModelAndView("redirect:bodyParameterNotAllow"); 
		  }
		  
		  String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			
			
			if(roleAccess.equals("Unit")){
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				
				
			}
		  
		      Date date = new Date();
			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String yyyy = new SimpleDateFormat("yyyy").format(date);
			String to_date = yyyy+"-01-01";
	
			Mmap.put("date", date1);
			Mmap.put("to_date",to_date );
		Mmap.put("msg", msg);
		

		return new ModelAndView("mnh_search_bed_statusDATiles");
	}

	// *******************Note::End*****************************************//

	// *******************Note::open url Edit daily morning bed
	// state*****************************************//
	@RequestMapping(value = "/edit_mnh_DA_morning_bed_status", method = RequestMethod.GET)
	public ModelAndView edit_mnh_DA_morning_bed_status(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		 Boolean val = roledao.ScreenRedirect("mnh_search_bed_statusDA", session.getAttribute("roleid").toString());
         if(val == false) {
           return new ModelAndView("AccessTiles");
          }
		  if(request.getHeader("Referer") == null ) 
		  {
			  msg = ""; 
			  return new ModelAndView("redirect:bodyParameterNotAllow"); 
		  }
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		Mmap.put("msg", msg);
		Mmap.put("date", date1);

		return new ModelAndView("edit_mnh_bed_statusDATiles");
	}

	// *******************Note::End*****************************************//

		@RequestMapping(value = "/morning_bed_stateDAAction", method = RequestMethod.POST)
		public ModelAndView morning_bed_stateDAAction(
				@ModelAttribute("morning_bed_stateDACMD") Tb_Med_Daily_Bed_Occupancy rs, HttpServletRequest request,
				ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg)
				throws ParseException {

					 Boolean val = roledao.ScreenRedirect("mnh_bed_statusDA",session.getAttribute("roleid").toString());
					
					if(val == false) {
						return new
					 ModelAndView("AccessTiles"); } 
					if(request.getHeader("Referer") == null ) {
					msg = ""; 
					return new ModelAndView("redirect:bodyParameterNotAllow"); }
					//		 

			String username = session.getAttribute("username").toString();
			int id = rs.getId() > 0 ? rs.getId() : 0;

			String unit_name1 = request.getParameter("unit_name1");

			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();

			String sus_no1 = request.getParameter("sus_no1");
			if (roleAccess.equals("Unit")) {
				model.put("sus_no", roleSusNo);
				model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
				sus_no1 = roleSusNo;

			}

			String auth_beds = request.getParameter("auth_beds");
			String beds_laid_out = request.getParameter("beds_laid_out");

			String total_no_of_patients = request.getParameter("total_no_of_patients");
			String bed_occ_per_as_per_auth_bed = request.getParameter("bed_occ_per_as_per_auth_bed");
			String bed_occ_per_as_per_laid_out_bed = request.getParameter("bed_occ_per_as_per_laid_out_bed");

			String off_army = request.getParameter("off_army");
			String off_fam_army = request.getParameter("off_fam_army");
			String jco_or_army = request.getParameter("jco_or_army");
			String jco_or_fam_army = request.getParameter("jco_or_fam_army");

			String ex_off_army = request.getParameter("ex_off_army");
			String ex_off_fam_army = request.getParameter("ex_off_fam_army");
			String ex_jco_or_army = request.getParameter("ex_jco_or_army");
			String ex_jco_or_fam_army = request.getParameter("ex_jco_or_fam_army");

			String para_mil_pers = request.getParameter("para_mil_pers");
			String para_family = request.getParameter("para_family");
			String other_ne = request.getParameter("other_ne");
			String other_family = request.getParameter("other_family");

			String cadet = request.getParameter("cadet");
			String rect_agniveer = request.getParameter("rect_agniveer");
			
			Date dat_i = null;
			DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			String dt = request.getParameter("dat1");

			String btn_hid = request.getParameter("btn_hid");
			//String btn_hid_exit = request.getParameter("btn_hid_exit");
			
			if (btn_hid.equals("1") ) {
				if (sus_no1 == null || sus_no1.equals("")) {
					model.put("msg", "Please Enter the SUS No");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (validation.sus_noLength(request.getParameter("sus_no1")) == false) {
					model.put("msg", validation.sus_noMSG);
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (auth_beds == null || auth_beds.equals("")) {
					model.put("msg", "Please Enter Auth Beds");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (dt == null || dt.equals("")) {
					model.put("msg", "Please Select the Date");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (off_army == null || off_army.equals("")) {
					model.put("msg", "Please Enter Officer Serving In Army");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (off_fam_army == null || off_fam_army.equals("")) {
					model.put("msg", "Please Enter Officer Family Serving In Army");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (jco_or_army == null || jco_or_army.equals("")) {
					model.put("msg", "Please Enter JCO/Or In Army");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (jco_or_fam_army == null || jco_or_fam_army.equals("")) {
					model.put("msg", "Please Enter JCO/Or Family In Army");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (ex_off_army == null || ex_off_army.equals("")) {
					model.put("msg", "Please Enter Officer EX-Serving In Army");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (ex_off_fam_army == null || ex_off_fam_army.equals("")) {
					model.put("msg", "Please Enter Officer Family EX-Serving In Army");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (ex_jco_or_army == null || ex_jco_or_army.equals("")) {
					model.put("msg", "Please Enter JCO/Or EX-Serving In Army");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (ex_jco_or_fam_army == null || ex_jco_or_fam_army.equals("")) {
					model.put("msg", "Please Enter JCO/Or family EX-Serving In Army");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}

				if (para_mil_pers == null || para_mil_pers.equals("")) {
					model.put("msg", "Please Enter Para Mil Pers In Others");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (para_family == null || para_family.equals("")) {
					model.put("msg", "Please Enter Para Mil Family Others");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (other_ne == null || other_ne.equals("")) {
					model.put("msg", "Please Enter Other Non In  others");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (other_family == null || other_family.equals("")) {
					model.put("msg", "Please Enter Other family In Other ");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (cadet == null || cadet.equals("")) {
					model.put("msg", "Please Enter Cadet Details");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (rect_agniveer == null || rect_agniveer.equals("")) {
					model.put("msg", "Please Enter Rect(Agniveer) Details");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}

				if (beds_laid_out.equals("")) {
					model.put("msg", "Please Enter Bed Laid out");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (total_no_of_patients.equals("")) {
					model.put("msg", "Please Enter Total No of Patients");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (bed_occ_per_as_per_laid_out_bed.equals("")) {
					model.put("msg", "Please Enter Bed occ. as per Laid out Beds");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (bed_occ_per_as_per_auth_bed.equals("")) {
					model.put("msg", "Please Enter Bed occ. as per Auth Beds");
					return new ModelAndView("redirect:mnh_bed_statusDA");
				}
				if (!dt.equals("")) { dat_i = formatter1.parse(dt); }	
				else {dat_i = formatter1.parse(dt); }
				
				

			}
			dat_i = formatter1.parse(dt); 
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			try {

				Long bedE = mb.checkExitstingbedExistlNo(sus_no1, dt, id);
				if (id == 0) {
					rs.setCreated_by(username);
					rs.setCreated_on(new Date());
					rs.setSus_no(sus_no1);
					rs.setDt(dat_i);
					if (bedE == 0) {
						session1.save(rs);
						tx.commit();
						model.put("msg", "Data Saved Successfully.");
					}
					if (bedE > 0)

					{
						model.put("msg", "Data already Exist.");
					}

			
				}
				} catch (RuntimeException e) {
				try {
					tx.rollback();
					model.put("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					model.put("msg", "Couldn�t roll back transaction " + rbe);
				}
				throw e;

			} finally {
				if (session1 != null) {
					session1.close();
				}
			}

			if (btn_hid.equals("1")) {
				return new ModelAndView("redirect:mnh_bed_statusDA");

			} else {
				return new ModelAndView("redirect:mnh_bed_statusDA");
			}
		}


	// *******************Note::END*****************************************//
		@RequestMapping(value = "/deletemorning_bed_state", method = RequestMethod.POST)
		public ModelAndView deletemorning_bed_state(@ModelAttribute("id1") int id, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session1,@RequestParam(value = "msg", required = false) String msg) {
			 Boolean val = roledao.ScreenRedirect("mnh_search_bed_statusDA", session1.getAttribute("roleid").toString());
	         if(val == false) {
	           return new ModelAndView("AccessTiles");
	          }
			  if(request.getHeader("Referer") == null ) {
				  msg = ""; 
				  return new ModelAndView("redirect:bodyParameterNotAllow"); 
			  }
			
			List<String> liststr = new ArrayList<String>();
			try {
				Session session = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				String hqlUpdate = "delete from Tb_Med_Daily_Bed_Occupancy where id=:id";
				int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
				tx.commit();
				session.close();
				if (app > 0) {
					liststr.add("Data Deleted Successfully.");
				} else {
					liststr.add("Data not Deleted.");
				}
				model.put("msg", liststr.get(0));
			} catch (Exception e) {
				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				model.put("msg", liststr.get(0));
			}
			return new ModelAndView("mnh_search_bed_statusDATiles");
		}
	// ********************Note::update daily morning bed  state*****************************************//
	@RequestMapping(value = "/updatemorning_bed_status", method = RequestMethod.POST)
	public ModelAndView updatemorning_bed_status(@ModelAttribute("updateid") String updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {
		 Boolean val = roledao.ScreenRedirect("mnh_search_bed_statusDA", sessionEdit.getAttribute("roleid").toString());
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

		Tb_Med_Daily_Bed_Occupancy mrngDetails = mb.updatemorning_bed_statusByid(Integer.parseInt(updateid));
		model.put("edit_morning_bed_stateDACMD", mrngDetails);
		model.put("msg", msg);

		return new ModelAndView("edit_mnh_bed_statusDATiles");
	}

	@RequestMapping(value = "/edit_morning_bed_stateDAAction", method = RequestMethod.POST)
	public ModelAndView edit_morning_bed_stateDAAction(@RequestParam(value = "msg", required = false) String msg,
			@ModelAttribute("edit_morning_bed_stateDACMD") Tb_Med_Daily_Bed_Occupancy lm, HttpServletRequest request,
			ModelMap model, HttpSession session) throws ParseException {
		 Boolean val = roledao.ScreenRedirect("mnh_search_bed_statusDA", session.getAttribute("roleid").toString());
         if(val == false) {
           return new ModelAndView("AccessTiles");
          }
		  if(request.getHeader("Referer") == null ) {
			  msg = ""; 
			  return new ModelAndView("redirect:bodyParameterNotAllow"); 
		  }
		model.put("updateid", lm.getId());
		Date date = new Date();
		String username = session.getAttribute("username").toString();

		  String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
		
			String sus_no1=request.getParameter("sus_no");
			if(roleAccess.equals("Unit")){
				model.put("sus_no",roleSusNo);
				model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				sus_no1 = roleSusNo;
				
			}
			
			
		String auth_beds = request.getParameter("auth_beds");
		String beds_laid_out = request.getParameter("beds_laid_out");

		String total_no_of_patients = request.getParameter("total_no_of_patients");
		String bed_occ_per_as_per_auth_bed = request.getParameter("bed_occ_per_as_per_auth_bed");
		String bed_occ_per_as_per_laid_out_bed = request.getParameter("bed_occ_per_as_per_laid_out_bed");

		String off_army = request.getParameter("off_army");
		String off_fam_army = request.getParameter("off_fam_army");
		String jco_or_army = request.getParameter("jco_or_army");
		String jco_or_fam_army = request.getParameter("jco_or_fam_army");

		String ex_off_army = request.getParameter("ex_off_army");
		String ex_off_fam_army = request.getParameter("ex_off_fam_army");
		String ex_jco_or_army = request.getParameter("ex_jco_or_army");
		String ex_jco_or_fam_army = request.getParameter("ex_jco_or_fam_army");
		
		String cadet = request.getParameter("cadet");
		String rect_agniveer=request.getParameter("rect_agniveer");
		
		String para_mil_pers = request.getParameter("para_mil_pers");
		String para_family = request.getParameter("para_family");
		String other_ne = request.getParameter("other_ne");
		String other_family = request.getParameter("other_family");

		Date dat_i = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		String dt = request.getParameter("dat1");

		if (sus_no1 == null || sus_no1.equals("")) {
			model.put("msg", "Please Enter the SUS No");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		if (validation.sus_noLength(sus_no1) == false) {
			model.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		if (auth_beds == null || auth_beds.equals("")) {
			model.put("msg", "Please Enter Auth Beds");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		if (dt == null || dt.equals("")) {
			model.put("msg", "Please Select the Date");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		if (off_army == null || off_army.equals("")) {
			model.put("msg", "Please Enter Officer Serving In Army");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		if (off_fam_army == null || off_fam_army.equals("")) {
			model.put("msg", "Please Enter Officer Family Serving In Army");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		if (jco_or_army == null || jco_or_army.equals("")) {
			model.put("msg", "Please Enter JCO/Or In Army");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		if (jco_or_fam_army == null || jco_or_fam_army.equals("")) {
			model.put("msg", "Please Enter JCO/Or Family In Army");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		if (ex_off_army == null || ex_off_army.equals("")) {
			model.put("msg", "Please Enter Officer EX-Serving In Army");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		if (ex_off_fam_army == null || ex_off_fam_army.equals("")) {
			model.put("msg", "Please Enter Officer Family EX-Serving In Army");
			return new ModelAndView("redirect:mnh_bed_statusDA");
		}
		if (ex_jco_or_army == null || ex_jco_or_army.equals("")) {
			model.put("msg", "Please Enter JCO/Or EX-Serving In Army");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		if (ex_jco_or_fam_army == null || ex_jco_or_fam_army.equals("")) {
			model.put("msg", "Please Enter JCO/Or family EX-Serving In Army");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		
		
		
		if (para_mil_pers == null || para_mil_pers.equals("")) {
			model.put("msg", "Please Enter Para Mil Pers In Others");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		if (para_family == null || para_family.equals("")) {
			model.put("msg", "Please Enter Para Mil Family Others");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		if (other_ne == null || other_ne.equals("")) {
			model.put("msg", "Please Enter Other Non In  others");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		if (other_family == null || other_family.equals("")) {
			model.put("msg", "Please Enter Other family In Other ");
			return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
		 if(beds_laid_out.equals("")){
		 model.put("msg", "Please Enter Bed Laid out");
		 return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		 }
		 if(total_no_of_patients.equals("")){
		 model.put("msg", "Please Enter Total No of Patients");
		return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		}
	    if(bed_occ_per_as_per_laid_out_bed.equals("")){
		 model.put("msg", "Please Enter Bed occ. as per Laid out Beds");
		 return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		 }
		 if(bed_occ_per_as_per_auth_bed.equals("")){
		 model.put("msg", "Please Enter Bed occ. as per Auth Beds");
		 return new ModelAndView("redirect:edit_mnh_DA_morning_bed_status");
		 }
		if (!dt.equals("")) {
			dat_i = formatter1.parse(dt);
		}
		try {

			Long bedE = mb.checkExitstingbedExistlNo(sus_no1, dt, lm.getId());
			lm.setModified_by(username);
			lm.setModified_on(date);
			lm.setDt(dat_i);
			if (bedE == 0) {

				double bed_occ_laid_out = Double.valueOf(bed_occ_per_as_per_laid_out_bed);
				lm.setBed_occ_per_as_per_laid_out_bed(bed_occ_laid_out);
				model.put("msg", mb.updatemornibedstate(lm, username));
			}
			if (bedE > 0)

			{
				model.put("msg", "Data already Exist.");
			}

		} catch (RuntimeException e) {
			try {

				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn’t roll back transaction " + rbe);
			}
			throw e;
		} finally {

		}
		return new ModelAndView("mnh_search_bed_statusDATiles");
	}

	// *******************Note::END*****************************************//
	
	
	@RequestMapping(value = "/update_bed_state_h",method = RequestMethod.POST)
	public ModelAndView update_bed_state_h(@ModelAttribute("bed_state_h") String bed_state_h, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {
		
		String  roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_search_bed_statusDA", roleid);	
		
       	
       	
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
		
		/*Tb_Med_Daily_Bed_Occupancy dailyDetails = dg.update_dailydByid(unit_name_h);
		
		model.put("edit_daily_disease_sureveCMD", dailyDetails);*/
		model.put("msg", msg);
		model.put("unit_name", bed_state_h);
		
		model.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", sessionEdit));
		model.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", sessionEdit));
		model.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", sessionEdit));
		model.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", sessionEdit));
		model.put("getMedDiseaseName", mcommon.getMedDiseaseName("","", sessionEdit));
		model.put("ml_5", mcommon.getMedrelationList("", sessionEdit));
		return new ModelAndView("mnh_bed_statusDATiles");
	}
	@RequestMapping(value = "/search_morning_bed_status" ,method = RequestMethod.POST)
	public ModelAndView search_morning_bed_status(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus1", required = false) String sus1, String frm_dt1, String to_dt1, String unit1) {
		 Boolean val = roledao.ScreenRedirect("mnh_search_bed_statusDA", session.getAttribute("roleid").toString());
         if(val == false) {
           return new ModelAndView("AccessTiles");
          }
		  if(request.getHeader("Referer") == null ) {
			  msg = ""; 
			  return new ModelAndView("redirect:bodyParameterNotAllow"); 
		  }
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		if( sus1 == null || sus1.equals("")){ 
			Mmap.put("msg", "Please Enter the SUS No");
            return new ModelAndView("redirect:mnh_search_bed_statusDA");
             }
		if (validation.DiseasemmrLength(unit1) == false) {
			Mmap.put("msg", validation.hospital_nameMSG);
			return new ModelAndView("redirect:mnh_search_bed_statusDA");
		}
		if (validation.sus_noLength(sus1) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:mnh_search_bed_statusDA");
		}
	 try {
                Date to_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(to_dt1);
                Date from_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(frm_dt1);
                if (to_date1.before(from_date1)) {
                        Mmap.put("msg", "To date must be greater than from date");
                        return new ModelAndView("redirect:mnh_search_bed_statusDA");
                }
        } catch (ParseException e) {
                // e.printStackTrace();
        }
	    
	    String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
	
		String f_sus_no=sus1;
		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			f_sus_no = roleSusNo;
			
		}
		
	 
	 
		Mmap.put("date", date1);
		Mmap.put("sus1", f_sus_no);
		Mmap.put("unit1", unit1);
		Mmap.put("frm_dt1", frm_dt1);
		Mmap.put("to_dt1", to_dt1);

		List<Map<String, Object>> list = mb.search_morning_bed_status_details(f_sus_no, frm_dt1, to_dt1);

		Mmap.put("list", list);
		Mmap.put("size", list.size());
		return new ModelAndView("mnh_search_bed_statusDATiles");
	}

}
