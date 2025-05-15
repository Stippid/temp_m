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
import org.springframework.http.HttpRequest;
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

import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.SHO_FHO_EPID_MosquitoDao;
import com.models.mnh.Tb_Med_Eir;
import com.models.mnh.Tb_Med_History_Stay;
import com.models.mnh.Tb_Med_List_of_close_contacts;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class EpidEmiological_Invest_RepController {

	@Autowired
	RoleBaseMenuDAO roledao;
	@Autowired
	MNH_Common_DAO mnh1_Dao;

	@Autowired
	SHO_FHO_EPID_MosquitoDao mnhsh_Dao;

	MNH_CommonController mcommon = new MNH_CommonController();

	MNH_ValidationController validation = new MNH_ValidationController();

	// ==================================open page============================//
	@RequestMapping(value = "/admin/mnh_input_air", method = RequestMethod.GET)
	public ModelAndView mnh_input_air(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		Boolean val = roledao.ScreenRedirect("mnh_input_air", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		Mmap.put("ml_1", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		Mmap.put("ml_2", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		Mmap.put("ml_3", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("ml_6", mcommon.getMedDiseaseAIRName("", "", session));
		Mmap.put("ml_7", mcommon.getMedrelationList("", session));
		Mmap.put("FinalEpiddiag", mcommon.getMedSystemCodeQua("FINAL_EPID_DIAGNOSIS", "", session));
		Mmap.put("Epiddiag", mcommon.getMedSystemCodeQua("EPID_DIAGNOSIS", "", session));
		Mmap.put("Actionunder", mcommon.getMedSystemCodeQua("ACTION_UNDERTAKEN", "", session));
		return new ModelAndView("mnh_input_air_Tiles");
	}

	// ===============================Search====================================//
	@RequestMapping(value = "/admin/search_air_ShoInput", method = RequestMethod.GET)
	public ModelAndView search_air_ShoInput(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	
		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		Boolean val = roledao.ScreenRedirect("search_air_ShoInput", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("a_2", l1.get(0).get(2));
		Mmap.put("a_3", l1.get(0).get(3));
		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		Mmap.put("ml_1", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		Mmap.put("ml_2", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		Mmap.put("ml_3", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("ml_6", mcommon.getMedDiseaseAIRName("", "", session));
		Mmap.put("ml_7", mcommon.getMedrelationList("", session));
		Mmap.put("FinalEpiddiag", mcommon.getMedSystemCodeQua("FINAL_EPID_DIAGNOSIS", "", session));
		Mmap.put("Epiddiag", mcommon.getMedSystemCodeQua("EPID_DIAGNOSIS", "", session));
		Mmap.put("Actionunder", mcommon.getMedSystemCodeQua("ACTION_UNDERTAKEN", "", session));
		return new ModelAndView("Search_mnh_inputs_airTiles");
	}

	@RequestMapping(value = "/Search_mnh_inputs_air_get", method = RequestMethod.POST)
	public ModelAndView Search_mnh_inputs_air_get(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("sus1") String sus1,
			String unit1, String dis2,String service1, String frm_dt1, String to_dt1, HttpServletRequest request) {

		int userid = (Integer) session.getAttribute("userId");

		Boolean val = roledao.ScreenRedirect("search_air_ShoInput", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		model.put("r_1", l1);
		List<Map<String, Object>> list = mnhsh_Dao.search_mosqfecoair_ShoInput(sus1, unit1, dis2, service1, frm_dt1, to_dt1);
		model.put("list", list);
		model.put("size", list.size());
		model.put("ml_1", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		model.put("ml_2", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		model.put("ml_3", mcommon.getMedSystemCode("SERVICE", "", session));
		model.put("ml_6", mcommon.getMedDiseaseAIRName("", "", session));
		model.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", session));
		model.put("ml_7", mcommon.getMedrelationList("", session));
		model.put("FinalEpiddiag", mcommon.getMedSystemCodeQua("FINAL_EPID_DIAGNOSIS", "", session));
		model.put("Epiddiag", mcommon.getMedSystemCodeQua("EPID_DIAGNOSIS", "", session));
		model.put("Actionunder", mcommon.getMedSystemCodeQua("ACTION_UNDERTAKEN", "", session));
		model.put("sus1", sus1);
		model.put("unit1", unit1);
		model.put("dis1", dis2);
		model.put("service1", service1);
		model.put("frm_dt1", frm_dt1);
		model.put("to_dt1", to_dt1);

		return new ModelAndView("Search_mnh_inputs_airTiles");
	}

	// ===================================================save=============================//
	@RequestMapping(value = "/mnh_input_airAction", method = RequestMethod.POST)
	public ModelAndView mnh_input_airAction(@ModelAttribute("mnh_input_airCMD") Tb_Med_Eir rs, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		int h_travel_count = Integer.parseInt(request.getParameter("h_travel_count"));
		int h_travel_Old_count = Integer.parseInt(request.getParameter("h_travel_Old_count"));
		int h_list_count = Integer.parseInt(request.getParameter("h_list_count"));
		int h_list_Old_count = Integer.parseInt(request.getParameter("h_list_Old_count"));
		 Date date_i = null;
		
		Boolean val = roledao.ScreenRedirect("mnh_input_air", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		
		String sus_no1=request.getParameter("sus_no");
		String disease = request.getParameter("disease");
		String category = request.getParameter("category");
		String relationship =request.getParameter("relationship");
		String adhar = request.getParameter("adhar_card_no");
		BigInteger adhar_card_no1 = BigInteger.ZERO;
		if (!adhar.equals("")) {
			adhar_card_no1 = new BigInteger(request.getParameter("adhar_card_no"));
		}
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");

	
	   	Date date_of_birth_i = null;
		String date_of_birth = request.getParameter("date_of_birth1");
		if (!date_of_birth.equals("")) {
			date_of_birth_i = formatter1.parse(date_of_birth);
		}
		BigInteger contact_no =BigInteger.ZERO;            
        if(!request.getParameter("contact_no") .equals("")) 
       {
        	contact_no =new BigInteger(request.getParameter("contact_no"));
		     }
		String username = session.getAttribute("username").toString();
		String service = request.getParameter("service");
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
		 String datee = request.getParameter("datee1"); 
		
		Date date = new Date();
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		try {
			if (sus_no1.equals("")) {
				model.put("msg", "Please Enter the SUS No");
				return new ModelAndView("redirect:mnh_input_mosquito");
			}
			if (disease.equals("-1")) {
				model.put("msg", "Please Enter the Disease");
				return new ModelAndView("redirect:mnh_input_mosquito");
			}
			if (service.equals("-1")) {
				model.put("msg", "Please Select the Service");
				return new ModelAndView("redirect:mnh_input_mosquito");
			}
			if (persnl_no2.equals("") && !service.equals("OTHERS")) {
				model.put("msg", "Please Enter the Personnel No");
				return new ModelAndView("redirect:mnh_input_mosquito");
			}
			if (persnl_no3.equals("-1") && !service.equals("OTHERS")) {
				model.put("msg", "Please Enter the Personnel No");
				return new ModelAndView("redirect:mnh_input_mosquito");
			}
			 
			 if (request.getParameter("category").equals("-1") || request.getParameter("category") == "-1"
						|| request.getParameter("category") == null || request.getParameter("category").equals("")) {
				 model.put("msg", "Please Select the category");
					return new ModelAndView("redirect:mnh_input_mosquito");
				}	
			
			if (relationship.equals("-1")) {
				model.put("msg", "Please Select the Relationship");
				return new ModelAndView("redirect:mnh_input_mosquito");
			}
			if (rs.getEir_rank().getId() == -1) {
				model.put("msg", "Please Select the Rank");
				return new ModelAndView("redirect:mnh_input_mosquito");
			}
			
			
			//////// LENGTH VALIDATION////////
			if (validation.sus_noLength(sus_no1) == false) {
				model.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:mnh_input_mosquito");
			}
			if (!adhar_card_no1.equals("0") && !adhar_card_no1.equals("") && adhar_card_no1 != null && validation.adhar_noLength(request.getParameter("adhar_card_no")) == false) {
				model.put("msg", validation.adharnoMSG);
				return new ModelAndView("redirect:mnh_input_mosquito");
		
			}
			if (validation.persnl_noLength(request.getParameter("persnl_no2")) == false) {
				model.put("msg", validation.persnl_no2MSG);
				return new ModelAndView("redirect:mnh_input_mosquito");
			}
			if(!datee.equals("")){
		        date_i = formatter1.parse(datee);
		}
			rs.setCreated_by(username);
			rs.setCreated_on(new Date());
			rs.setPersnl_no(persnl_no);
			rs.setDate_of_birth(date_of_birth_i);
			//rs.setDatee(d1.parse(request.getParameter("datee")));
			rs.setDatee(date_i);
		    rs.setRepo_type("air");
			session1.save(rs);
			int diffrence = h_travel_count - h_travel_Old_count;
			if (diffrence != 0) {
			Tb_Med_History_Stay e = new Tb_Med_History_Stay();
			for (int j = h_travel_Old_count + 1; j <= h_travel_count; j++) {

				
				String h_time = request.getParameter("h_time" + j);
			  String h_loc = request.getParameter("h_loc" + j);
			
				Date h_datee_dt = null;
				
				String h_datee = request.getParameter("h_datee" + j);
			
				if (h_datee != null && !h_datee.trim().equals("") && !h_datee.equals("DD/MM/YYYY")) {
					h_datee_dt = formatter1.parse(h_datee);

				}
				
				e.setHistory_loc(h_loc);;
				e.setHistory_time(h_time);
				e.setHistory_date(h_datee_dt);
				e.setDisease(disease);
				e.setRepo_type("air");
				e.setCreated_by(username);
				e.setCreated_on(new Date());
				e.setEir_id(rs.getId());
				session1.save(e);
				session1.flush();
				session1.clear();
			}
			}
			int diffrencelist = h_list_count - h_list_Old_count;
			if (diffrencelist != 0) {
				Tb_Med_List_of_close_contacts lc= new Tb_Med_List_of_close_contacts();
			for (int k = h_list_Old_count + 1; k <= h_list_count; k++) {

				
				String h_datee_expo = request.getParameter("h_datee_expo" + k);
			  String h_name = request.getParameter("h_name" + k);
			
			  Date h_datee_expo_dt = null;
				
				String h_remark = request.getParameter("h_remark" + k);
			
				if (h_datee_expo != null && !h_datee_expo.trim().equals("") && !h_datee_expo.equals("DD/MM/YYYY")) {
					h_datee_expo_dt = formatter1.parse(h_datee_expo);

				}
				lc.setExposure_date(h_datee_expo_dt);
				lc.setName_of_close_contact(h_name);
				lc.setRemarks(h_remark);
				lc.setDisease(disease);
				lc.setRepo_type("air");
				lc.setCreated_by(username);
				lc.setCreated_on(new Date());
				lc.setEir_id(rs.getId());
				session1.save(lc);
				session1.flush();
				session1.clear();
			}
			}
			tx.commit();

			model.put("msg", "Data saved Successfully");
		}

		catch (RuntimeException e) {

			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn’t roll back transaction " + rbe);
			}

			throw e;
		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
	

		return new ModelAndView("redirect:mnh_input_air");
	}
	// =====================================Delete=================================//

	@RequestMapping(value = "/delete_air_ShoInput", method = RequestMethod.POST)
	public ModelAndView delete_air_ShoInput(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		List<String> liststr = new ArrayList<String>();
		model.put("ml_6", mcommon.getMedDiseaseAIRName("", "", session1));
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			String hqlUpdate = "delete from Tb_Med_Eir where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();
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
		return new ModelAndView("Search_mnh_inputs_airTiles");
	}

	// =======================================Edit open //
	// page===========================//
	@RequestMapping(value = "/edit_air_ShoInput",method = RequestMethod.POST)
	public ModelAndView edit_air_ShoInput(@ModelAttribute("id2") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {

		Boolean val = roledao.ScreenRedirect("Search_mnh_inputs_mosq_food_air",
				sessionEdit.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);

		Tb_Med_Eir eir = mnhsh_Dao.getmosquitoDetail(Integer.parseInt(updateid));
		Tb_Med_History_Stay HS = mnhsh_Dao.getmosquitoDetail2(Integer.parseInt(updateid));

		Mmap.put("Sho_input_air_editCMD", eir);
		Mmap.put("Sho_input_air_editCMD", HS);

		Mmap.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", sessionEdit));
		Mmap.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", sessionEdit));
		Mmap.put("ml_3", mcommon.getMedSystemCode("SERVICE", "", sessionEdit));
		Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", sessionEdit));
		Mmap.put("ml_7", mcommon.getMedrelationList("", sessionEdit));
		Mmap.put("ml_6", mcommon.getMedDiseaseAIRName("", "", sessionEdit));
		Mmap.put("FinalEpiddiag", mcommon.getMedSystemCodeQua("FINAL_EPID_DIAGNOSIS", "", sessionEdit));
		Mmap.put("Epiddiag", mcommon.getMedSystemCodeQua("EPID_DIAGNOSIS", "", sessionEdit));
		Mmap.put("Actionunder", mcommon.getMedSystemCodeQua("ACTION_UNDERTAKEN", "", sessionEdit));
		Mmap.put("Actionbysho", mcommon.getMedSystemCodeQua("ACTION_BY_SHO", "", sessionEdit));
		Mmap.put("Sourcecontain", mcommon.getMedSystemCodeQua("SOURCE_CONTAINMENT", "", sessionEdit));

		Mmap.put("Sho_input_air_editCMD", mnhsh_Dao.getmosquitoDetail(Integer.parseInt(updateid)));
		Mmap.put("date", date1);
		Mmap.put("msg", msg);

		return new ModelAndView("Edit_mnh_inputs_airTiles");
	}

	// ====================================Update=================================//

	@RequestMapping(value = "/Sho_input_air_editAction", method = RequestMethod.POST)
	public ModelAndView Sho_input_air_editAction(@ModelAttribute("Sho_input_air_editCMD") Tb_Med_Eir rs,
			HttpServletRequest request, ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {

			int id = Integer.parseInt(request.getParameter("id"));
			Mmap.put("updateid", request.getParameter("id"));
			Mmap.put("id2", rs.getId());
			 int h_travel_count = Integer.parseInt(request.getParameter("h_travel_count"));
				int h_travel_Old_count = Integer.parseInt(request.getParameter("h_travel_Old_count"));
				int h_list_count = Integer.parseInt(request.getParameter("h_list_count"));
				int h_list_Old_count = Integer.parseInt(request.getParameter("h_list_Old_count"));
			DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
			DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date_of_birth_i = null;

		String date_of_birth = request.getParameter("date_of_birth1");
		if (!date_of_birth.equals("")) {
			date_of_birth_i = formatter1.parse(date_of_birth);
		}
		BigInteger contact_no =BigInteger.ZERO;            
        if(!request.getParameter("contact_no") .equals("")) 
       {
        	contact_no =new BigInteger(request.getParameter("contact_no"));
	     }
		Date date = new Date();
		String adhar_card_no1 = request.getParameter("adhar_card_no");
		String sus_no1=request.getParameter("sus_no");
		String disease = request.getParameter("disease");
		String service =request.getParameter("service");
		String ip_date = request.getParameter("ip_date");
		String category = request.getParameter("category");
		String relationship =request.getParameter("relationship");
		String username = session.getAttribute("username").toString();
		
		String persnl_no = request.getParameter("persnl_no1") + request.getParameter("persnl_no2")
				+ request.getParameter("persnl_no3");

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();

		 try {
			 if (sus_no1.equals("")) {
					Mmap.put("msg", "Please Enter the SUS No");
					return new ModelAndView("redirect:edit_mosq_ShoInput");
				}
				if (disease.equals("-1")) {
					Mmap.put("msg", "Please Enter the Disease");
					return new ModelAndView("redirect:edit_mosq_ShoInput");
				}
				if (service.equals("-1")) {
					Mmap.put("msg", "Please Select the Service");
					return new ModelAndView("redirect:edit_mosq_ShoInput");
				}
				
				if (category.equals("-1")) {
					Mmap.put("msg", "Please Select the Category");
					return new ModelAndView("redirect:edit_mosq_ShoInput");
				}
				if (relationship.equals("-1")) {
					Mmap.put("msg", "Please Select the Relationship");
					return new ModelAndView("redirect:edit_mosq_ShoInput");
				}
				if (rs.getEir_rank().getId() == -1) {
					Mmap.put("msg", "Please Select the Rank");
					return new ModelAndView("redirect:edit_mosq_ShoInput");
				}
				
				
				//////// LENGTH VALIDATION////////
				if (validation.sus_noLength(sus_no1) == false) {
					Mmap.put("msg", validation.sus_noMSG);
					return new ModelAndView("redirect:edit_mosq_ShoInput");
				}
				if (!adhar_card_no1.equals("0") && !adhar_card_no1.equals("") && adhar_card_no1 != null && validation.adhar_noLength(request.getParameter("adhar_card_no")) == false) {
					Mmap.put("msg", validation.adharnoMSG);
					return new ModelAndView("redirect:edit_mosq_ShoInput");
			
				}
				if (validation.adhar_noLength(adhar_card_no1) == false) {
					Mmap.put("msg", validation.adharnoMSG);
					return new ModelAndView("redirect:edit_mosq_ShoInput");
				}
				
				if (validation.persnl_noLength(request.getParameter("persnl_no2")) == false) {
					Mmap.put("msg", validation.persnl_no2MSG);
					return new ModelAndView("redirect:edit_mosq_ShoInput");
				}
				
			//////travel history save 
							int diffrence = h_travel_count - h_travel_Old_count;
							if (diffrence != 0) {
						
							for (int j = h_travel_Old_count + 1; j <= h_travel_count; j++) {

								Tb_Med_History_Stay e = new Tb_Med_History_Stay();
								String h_time = request.getParameter("h_time" + j);
							  String h_loc = request.getParameter("h_loc" + j);
							   String h_id = request.getParameter("travel_id"+j);
						
								Date h_datee_dt = null;
								
								String h_datee = request.getParameter("h_datee" + j);
							
								if (h_datee != null && !h_datee.trim().equals("") && !h_datee.equals("DD/MM/YYYY")) {
									h_datee_dt = formatter1.parse(h_datee);

								}
								
								e.setHistory_loc(h_loc);;
								e.setHistory_time(h_time);
								e.setHistory_date(h_datee_dt);
								e.setDisease(disease);
								e.setRepo_type("air");
								e.setModified_by(username);
								e.setModified_on(date);
								e.setEir_id(rs.getId());
								e.setCreated_by(username);
								e.setCreated_on(new Date());
								session1.save(e);
								session1.flush();
								session1.clear();
							}
							}
					         
					     ////////////////////////////////update travel
							for (int j = 1; j <= h_travel_Old_count; j++) {
								Session session2 = HibernateUtil.getSessionFactory().openSession();
					  	        Transaction tx1 = session2.beginTransaction();
							      String h_time = request.getParameter("h_time" + j);
								  String h_loc = request.getParameter("h_loc" + j);
								  String h_id = request.getParameter("travel_id"+j);
								
									Date h_datee_dt = null;
									
									String h_datee = request.getParameter("h_datee" + j);
								
									if (h_datee != null && !h_datee.trim().equals("") && !h_datee.equals("DD/MM/YYYY")) {
										h_datee_dt = formatter1.parse(h_datee);

									}
									String hql1 = "update Tb_Med_History_Stay set modified_by=:modified_by ,modified_on=:modified_on , history_time=:history_time, history_loc=:history_loc, history_date=:history_date where id=:eir_id";
										Query query1 = session1.createQuery(hql1).setString("history_time", h_time)
												.setString("modified_by", username)
												.setTimestamp("modified_on", new Date()).setString("history_loc",h_loc)
												.setDate("history_date", h_datee_dt)
												.setInteger("eir_id", Integer.parseInt(request.getParameter("travel_id" + j)));
												
									msg = query1.executeUpdate() > 0 ? "1" : "Data not Updated";
										
									session2.flush();
					  				session2.clear();
					  				tx1.commit();
										
										}
							///////////////////////save list of contact
							 int diffrencelist = h_list_count - h_list_Old_count;
								if (diffrencelist != 0) {
									Tb_Med_List_of_close_contacts lc= new Tb_Med_List_of_close_contacts();
								for (int k = h_list_Old_count + 1; k <= h_list_count; k++) {

									
									String h_datee_expo = request.getParameter("h_datee_expo" + k);
								  String h_name = request.getParameter("h_name" + k);
								
								  Date h_datee_expo_dt = null;
									
									String h_remark = request.getParameter("h_remark" + k);
								
									if (h_datee_expo != null && !h_datee_expo.trim().equals("") && !h_datee_expo.equals("DD/MM/YYYY")) {
										h_datee_expo_dt = formatter1.parse(h_datee_expo);

									}
									lc.setExposure_date(h_datee_expo_dt);
									lc.setName_of_close_contact(h_name);
									lc.setRemarks(h_remark);
									lc.setDisease(disease);
									lc.setRepo_type("air");
									lc.setModified_by(username);
									lc.setModified_on(date);
									lc.setEir_id(rs.getId());
					     			lc.setCreated_by(username);
									lc.setCreated_on(new Date());
									session1.save(lc);
									session1.flush();
									session1.clear();
								}
								}
							/////////////////update list contact	
						for (int k =1; k <= h_list_Old_count; k++) {
							Session session3 = HibernateUtil.getSessionFactory().openSession();
						        Transaction tx3 = session3.beginTransaction();
									
									String h_datee_expo = request.getParameter("h_datee_expo" + k);
								  String h_name = request.getParameter("h_name" + k);
								  String list_id = request.getParameter("list_id"+k);
								  Date h_datee_expo_dt = null;
									
									String h_remark = request.getParameter("h_remark" + k);
								
									if (h_datee_expo != null && !h_datee_expo.trim().equals("") && !h_datee_expo.equals("DD/MM/YYYY")) {
										h_datee_expo_dt = formatter1.parse(h_datee_expo);

									}
									String hql1 = "update Tb_Med_List_of_close_contacts set modified_by=:modified_by ,modified_on=:modified_on , name_of_close_contact=:name_of_close_contact, remarks=:remarks, exposure_date=:exposure_date where id=:eir_id";
									Query query1 = session1.createQuery(hql1).setString("name_of_close_contact", h_name)
											.setString("modified_by", username)
											.setTimestamp("modified_on", new Date()).setString("remarks",h_remark)
											.setDate("exposure_date", h_datee_expo_dt)
											.setInteger("eir_id", Integer.parseInt(request.getParameter("list_id" + k)));
											
								msg = query1.executeUpdate() > 0 ? "1" : "Data not Updated";
									
								session3.flush();
									session3.clear();
									tx3.commit();
								}
				
				rs.setPersnl_no(persnl_no);
				rs.setDate_of_birth(date_of_birth_i);
				rs.setDatee(d1.parse(request.getParameter("datee")));
				rs.setModified_by(username);
				rs.setModified_on(date);
			    rs.setRepo_type("air");
		       Mmap.put("msg", mnhsh_Dao.update_feco(rs, username));
				
				tx.commit();
				}catch(RuntimeException e){
			  try{ 
				  tx.rollback(); 
				  Mmap.put("msg", "roll back transaction"); }
			  catch(RuntimeException rbe){ 
				  Mmap.put("msg",  "Couldn’t roll back transaction " + rbe); }
			  throw e;	  
		  }finally{ 
			  if(session1!=null){ 
				  session1.close(); } }
		return new ModelAndView("redirect:search_air_ShoInput");

	}
	
	//============ DELETE=======IN PAGE=================///
	@RequestMapping(value = "/getTblDeleteAir" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView getTblDeleteAir(@ModelAttribute("id1") int id1,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			String hqlUpdate = "delete from  Tb_Med_History_Stay where eir_id = :eir_id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("eir_id", id1).executeUpdate();
			
			model.put("id2",id1 );
		 	tx.commit();
			sessionHQL.close();
			
			Session sessionHQL2 = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx2 = sessionHQL2.beginTransaction();
			 String hqlUpdate1 = "UPDATE Tb_Med_Eir SET diff_days='0',ip_start_date = :ip_start_date,ip_end_date =:ip_end_date WHERE id = :id";
			int app2 = sessionHQL2.createQuery(hqlUpdate1).setInteger("id", id1).setTimestamp("ip_start_date", null).setTimestamp("ip_end_date", null)
					.executeUpdate();
			
			model.put("id2",id1 );
			tx2.commit();
			sessionHQL2.close();
			
		return new ModelAndView("redirect:edit_air_ShoInput");
	}
	
	
}
