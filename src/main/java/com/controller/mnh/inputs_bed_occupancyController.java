package com.controller.mnh;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.mnh_inputs_bed_occupancyDAO;
import com.models.mnh.Tb_Med_Bed_Ex_Servicemen;
import com.models.mnh.Tb_Med_Bed_Serving;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class inputs_bed_occupancyController {

	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private mnh_inputs_bed_occupancyDAO MB_Dao;
	MNH_CommonController mcommon = new MNH_CommonController();
	MNH_ValidationController validation = new MNH_ValidationController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	
	// *******************Note::Open Url Bed Occupancy*****************************************//
	
	@RequestMapping(value = "/admin/mnh_input_bed_occupancy", method = RequestMethod.GET)
	public ModelAndView mnh_input_bed_occupancy(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String username = (String) session.getAttribute("username");
		int userid = (Integer) session.getAttribute("userId");		

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_input_bed_occupancy", roleid);
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
		
		
		Mmap.put("msg", msg);		
		Mmap.put("getMedSystemCodeQuarter", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		Mmap.put("service_type", mcommon.getMedSystemCodeQua("Service_Type", "", session));
		return new ModelAndView("mnh_input_bed_occupTiles");
	}
	


	// *******************Note::Save Bed Occupancy*****************************************//
	
	@RequestMapping(value = "/bed_occ_servAct", method = RequestMethod.POST)
	public ModelAndView bed_occ_servAct(@ModelAttribute("bed_occ_servCmd") Tb_Med_Bed_Serving rs,
			HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_input_bed_occupancy", roleid);
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
			model.put("sus_no",roleSusNo);
			model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
		Tb_Med_Bed_Ex_Servicemen rs2 = new Tb_Med_Bed_Ex_Servicemen();

		String username = session.getAttribute("username").toString();
		String type = request.getParameter("type");
		String sus_no1 = request.getParameter("sus_no1");
		String unit_name1 = request.getParameter("unit_name1");
		String command = request.getParameter("command");
		String quater = request.getParameter("quater");
		String remarks = request.getParameter("remarks");
		int id = rs.getId() > 0 ? rs.getId() : 0;	
		int yr = Integer.parseInt(request.getParameter("yr"));
		 
        Date date_year_i = null;
		Date date = new Date();
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
        DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        date_year_i = formatter2.parse(date1);
	    SimpleDateFormat YY = new SimpleDateFormat("yyyy");
		int year2 = Integer.parseInt(YY.format(date_year_i));
		String ofcr_max = request.getParameter("ofcr_max1");
		String ofcr_tot = request.getParameter("ofcr_tot1");
		String ofcr_avg = request.getParameter("ofcr_avg1");
		String jco_max = request.getParameter("jco_max1");
		String jco_tot = request.getParameter("jco_tot1");
		String jco_avg = request.getParameter("jco_avg1");
		String ofcr_fmly_max = request.getParameter("ofcr_fmly_max1");
		String ofcr_fmly_tot = request.getParameter("ofcr_fmly_tot1");
		String ofcr_fmly_avg = request.getParameter("ofcr_fmly_avg1");
		String jco_fmly_max = request.getParameter("jco_fmly_max1");
		String jco_fmly_tot = request.getParameter("jco_fmly_tot1");
		String jco_fmly_avg = request.getParameter("jco_fmly_avg1");
		
		String need_exms_adm = request.getParameter("need_exms_adm1");
		String need_exms_bed_days = request.getParameter("need_exms_bed_days1");
		String no_of_fmly_ref_adm = request.getParameter("no_of_fmly_ref_adm1");
		 
		Integer ofcr_max1 = 0;
		if (ofcr_max != "" && !ofcr_max.equals("")) {
			ofcr_max1 = Integer.parseInt(ofcr_max);
		}
		Integer ofcr_tot1 = 0;
		if (ofcr_tot != "" && !ofcr_tot.equals("")) {
			ofcr_tot1 = Integer.parseInt(ofcr_tot);
		}
	    
		Double ofcr_avg1 = (double) 0;
		if (ofcr_avg != "" && !ofcr_avg.equals("")) {
			ofcr_avg1 = Double.parseDouble(ofcr_avg);
		}
		Integer jco_max1 = 0;
		if (jco_max != "" && !jco_max.equals("")) {
			jco_max1 = Integer.parseInt(jco_max);
		}
		Integer jco_tot1 = 0;
		if (jco_tot != "" && !jco_tot.equals("")) {
			jco_tot1 = Integer.parseInt(jco_tot);
		}
		
		Double jco_avg1 = (double) 0;
		if (jco_avg != "" && !jco_avg.equals("")) {
			jco_avg1 = Double.parseDouble(jco_avg);
		}
		Integer ofcr_fmly_max1 = 0;
		if (ofcr_fmly_max != "" && !ofcr_fmly_max.equals("")) {
			ofcr_fmly_max1 = Integer.parseInt(ofcr_fmly_max);
		}
		Integer ofcr_fmly_tot1 = 0;
		if (ofcr_fmly_tot != "" && !ofcr_fmly_tot.equals("")) {
			ofcr_fmly_tot1 = Integer.parseInt(ofcr_fmly_tot);
		}
		Double ofcr_fmly_avg1 = (double) 0;
		if (ofcr_fmly_avg != "" && !ofcr_fmly_avg.equals("")) {
			ofcr_fmly_avg1 = Double.parseDouble(ofcr_fmly_avg);
		}
		Integer jco_fmly_max1 = 0;
		if (jco_fmly_max != "" && !jco_fmly_max.equals("")) {
			jco_fmly_max1 = Integer.parseInt(jco_fmly_max);
		}
		Integer jco_fmly_tot1 = 0;
		if (jco_fmly_tot != "" && !jco_fmly_tot.equals("")) {
			jco_fmly_tot1 = Integer.parseInt(jco_fmly_tot);
		}
		Double jco_fmly_avg1 = (double) 0;
		if (jco_fmly_avg != "" && !jco_fmly_avg.equals("")) {
			jco_fmly_avg1 = Double.parseDouble(jco_fmly_avg);
		}
		Integer need_exms_adm1 = 0;
		if (need_exms_adm != "" && !need_exms_adm.equals("")) {
			need_exms_adm1 = Integer.parseInt(need_exms_adm);
		}
		Integer need_exms_bed_days1 = 0;
		if (need_exms_bed_days != "" && !need_exms_bed_days.equals("")) {
			need_exms_bed_days1 = Integer.parseInt(need_exms_bed_days);
		}
		Integer no_of_fmly_ref_adm1 = 0;
		if (no_of_fmly_ref_adm != "" && !no_of_fmly_ref_adm.equals("")) {
			no_of_fmly_ref_adm1 = Integer.parseInt(no_of_fmly_ref_adm);
		}	
		     if( type == null || type.equals("-1")){ 
             model.put("msg", "Please Enter the Service Status");
             return new ModelAndView("redirect:mnh_input_bed_occupancy");
              } 
			 if( sus_no1 == null || sus_no1.equals("")){ 
                 model.put("msg", "Please Enter the SUS No");
                 return new ModelAndView("redirect:mnh_input_bed_occupancy");
                  } 
			 if(validation.sus_noLength(sus_no1) == false){
		        model.put("msg",validation.sus_noMSG);
		        return new ModelAndView("redirect:mnh_input_bed_occupancy");
		            }
		       if( command == null || command.equals("")){ 
				model.put("msg", "Command Name Should not be Null");
				return new ModelAndView("redirect:mnh_input_bed_occupancy");
			}
			if( quater == null || quater.equals("-1")){ 
				model.put("msg", "Please Select the Quarter");
				return new ModelAndView("redirect:mnh_input_bed_occupancy");
			}
			if(validation.check_future_quarter(request.getParameter("quater"))  == false){
				 model.put("msg",validation.check_future_quarter);
		         return new ModelAndView("redirect:mnh_input_bed_occupancy");
		     }
			if (request.getParameter("yr").equals("") || request.getParameter("yr") == null) {
				model.put("msg", "Please Enter the Year");
				return new ModelAndView("redirect:mnh_input_bed_occupancy");
			}			
			if (request.getParameter("yr").length() < 4) {
				model.put("msg", "Please Enter the Valid Year");
				return new ModelAndView("redirect:mnh_input_bed_occupancy");
			}
			if (yr > year2) {
				model.put("msg", "Future Year cannot be allowed");
				return new ModelAndView("redirect:mnh_input_bed_occupancy");
			}
			 if(validation.checkYearLength(request.getParameter("yr"))  == false){
				 model.put("msg",validation.yearMSG);
		         return new ModelAndView("redirect:mnh_input_bed_occupancy");
		     }
			if(validation.MessageLength(rs.getRemarks()) == false){
                model.put("msg",validation.remarkMSG);
                return new ModelAndView("redirect:mnh_input_bed_occupancy");
           }	
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			 
			
	try {
	  
		if (type.equalsIgnoreCase("1")) {
			Long x = MB_Dao.checkExitstingbedocc(sus_no1,quater,rs.getId(),yr);
			 if(id == 0) {
			rs.setCreated_by(username);
			rs.setCreated_on(new Date());
			rs.setSus_no(sus_no1);
			rs.setQtr(quater);
			rs.setYear(yr);
			rs.setOfcr_max(ofcr_max1);
			rs.setOfcr_tot(ofcr_tot1);
			rs.setOfcr_avg(ofcr_avg1);
			rs.setJco_max(jco_max1);
			rs.setJco_tot(jco_tot1);
			rs.setJco_avg(jco_avg1);
			rs.setOfcr_fmly_max(ofcr_fmly_max1);
			rs.setOfcr_fmly_tot(ofcr_fmly_tot1);
			rs.setOfcr_fmly_avg(ofcr_fmly_avg1);
			rs.setJco_fmly_max(jco_fmly_max1);
			rs.setJco_fmly_tot(jco_fmly_tot1);
			rs.setJco_fmly_avg(jco_fmly_avg1);
		    rs.setRemarks(remarks);
			
		     if (x == 0) {
		    	 sessionHQL.save(rs);
					tx.commit();
					model.put("msg", "Data Saved Successfully.");
				} 
				if (x > 0) 
					
				{
					model.put("msg", "Data already Exist.");
				}
				}
		}

		if (type.equalsIgnoreCase("2")) {
			Long ex = MB_Dao.checkExitstingbedocexserv(sus_no1,quater,rs2.getId(),yr);
			if(id == 0) {
			rs2.setCreated_by(username);
			rs2.setCreated_on(new Date());
			rs2.setSus_no(sus_no1);
			rs2.setQtr(quater);
			rs2.setYear(yr);
			
			rs2.setOfcr_max(ofcr_max1);
			rs2.setOfcr_tot(ofcr_tot1);
			rs2.setOfcr_avg(ofcr_avg1);
			rs2.setJco_max(jco_max1);
			rs2.setJco_tot(jco_tot1);
			rs2.setJco_avg(jco_avg1);
			rs2.setOfcr_fmly_max(ofcr_fmly_max1);
			rs2.setOfcr_fmly_tot(ofcr_fmly_tot1);
			rs2.setOfcr_fmly_avg(ofcr_fmly_avg1);
			rs2.setJco_fmly_max(jco_fmly_max1);
			rs2.setJco_fmly_tot(jco_fmly_tot1);
			rs2.setJco_fmly_avg(jco_fmly_avg1);
			
			rs2.setNeed_exms_adm(need_exms_adm1);
			rs2.setNeed_exms_bed_days(need_exms_bed_days1);
			rs2.setNo_of_fmly_ref_adm(no_of_fmly_ref_adm1);
			rs2.setRemarks(remarks);
			
			
			 if (ex == 0) {
		    	 sessionHQL.save(rs2);
					tx.commit();
					model.put("msg", "Data Saved Successfully.");
				} 
				if (ex > 0) 
					
				{
					model.put("msg", "Data already Exist.");
				}
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
			if(sessionHQL!=null){
				sessionHQL.close();
			}
		}
	   		
		return new ModelAndView("redirect:mnh_input_bed_occupancy");

	}

	// *******************Note::Search Open Url Bed Occupancy*****************************************//
	
	@RequestMapping(value = "/admin/mnh_input_search_bed_occupancy", method = RequestMethod.GET)
	public ModelAndView mnh_input_search_bed_occupancy(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String username = (String) session.getAttribute("username");
		int userid = (Integer) session.getAttribute("userId");

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_input_search_bed_occupancy", roleid);
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
		Mmap.put("msg", msg);		
		Mmap.put("getMedSystemCodeQuarter", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		Mmap.put("service_type", mcommon.getMedSystemCodeQua("Service_Type", "", session));
		return new ModelAndView("mnh_input_bed_occup_searchTiles");
	}

	// *******************Note::Search Method Bed Occupancy*****************************************//
	
	@RequestMapping(value = "/search_bed_occupancy_Input", method = RequestMethod.POST)
	public ModelAndView search_bed_occupancy_Input(ModelMap model, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("sus1") String sus1,
			String unit1, String type1, String qtr1, String yr1) {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("mnh_input_search_bed_occupancy", roleid);
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		if (unit1.equals("")) {
			model.put("msg", "Please Enter the Unit Name");
			return new ModelAndView("redirect:mnh_input_search_bed_occupancy");
		}
		if (sus1.equals("")) {
			model.put("msg", "Please Enter the SUS No");
			return new ModelAndView("redirect:mnh_input_search_bed_occupancy");
		}
		 if( type1 == null || type1.equals("-1")){ 
             model.put("msg", "Please Enter the Service Status");
             return new ModelAndView("redirect:mnh_input_search_bed_occupancy");
              } 
		if(validation.DiseasemmrLength(unit1) == false){
			model.put("msg",validation.hospital_nameMSG);
			return new ModelAndView("redirect:mnh_input_search_bed_occupancy");
		 }
		 if(validation.sus_noLength(sus1) == false){
			model.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:mnh_input_search_bed_occupancy");
		 }

			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
		
			String f_sus_no=sus1;
			if(roleAccess.equals("Unit")){
				model.put("sus_no",roleSusNo);
				model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				f_sus_no = roleSusNo;
			}
			
		List<Map<String, Object>> list = MB_Dao.search_bed_occupancy_Input(sus1, type1, qtr1, yr1);
		model.put("list", list);
		model.put("size", list.size());
		model.put("service_type", mcommon.getMedSystemCodeQua("Service_Type", "", session));
		model.put("sus1", f_sus_no);
		model.put("unit1", unit1);
		model.put("type1", type1);
		model.put("qtr1", qtr1);
		model.put("yr1", yr1);
		model.put("getMedSystemCodeQuarter", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		return new ModelAndView("mnh_input_bed_occup_searchTiles");
	}
	
	// *******************Note::Edit Open Url Bed Occupancy*****************************************//
	
	@RequestMapping(value = "/edit_bed_occupancy_Input", method = RequestMethod.POST)
	public ModelAndView edit_bed_occupancy_Input(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute(value = "id2") int id2,
			String type2,HttpServletRequest request) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_input_search_bed_occupancy", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		
	     
		Mmap.put("type2", type2);
		if (type2.equals("1")) {
			
			Tb_Med_Bed_Serving Details1 = MB_Dao.getservDetail(id2);
			
			Mmap.put("bed_occ_serv_editCMD", Details1);
			Mmap.put("need_exms_adm", "");
			Mmap.put("need_exms_bed_days", "");
			Mmap.put("no_of_fmly_ref_adm", "");
		}
		if (type2.equals("2")) {
			
			Tb_Med_Bed_Ex_Servicemen Details2 = MB_Dao.getexservDetail(id2);
			
			Mmap.put("bed_occ_serv_editCMD", Details2);
			Mmap.put("need_exms_adm", Details2.getNeed_exms_adm());
			Mmap.put("need_exms_bed_days", Details2.getNeed_exms_bed_days());
			Mmap.put("no_of_fmly_ref_adm", Details2.getNo_of_fmly_ref_adm());
			
		}
		
		Mmap.put("msg", msg);				
		Mmap.put("getMedSystemCodeQuarter", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		Mmap.put("service_type", mcommon.getMedSystemCodeQua("Service_Type", "", session));
		return new ModelAndView("mnh_input_bed_occup_editTiles");
	}
	
	// *******************Note::Update Method Bed Occupancy*****************************************//
	
	
	@RequestMapping(value = "/bed_occ_serv_editAction", method = RequestMethod.POST)
	public ModelAndView bed_occ_serv_editAction(@ModelAttribute("bed_occ_serv_editCMD") Tb_Med_Bed_Serving lm ,
			HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_input_search_bed_occupancy", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}	
		model.put("id2", lm.getId());
		
		  String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
		
			String sus_no1=request.getParameter("sus_no1");
			if(roleAccess.equals("Unit")){
				model.put("sus_no",roleSusNo);
				model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				sus_no1 = roleSusNo;
				
			}
		
		
		Tb_Med_Bed_Ex_Servicemen Exs = new Tb_Med_Bed_Ex_Servicemen();
		String username = session.getAttribute("username").toString();
		String type = request.getParameter("type");
		String unit_name1 = request.getParameter("unit_name1");
		String command = request.getParameter("command");
		String quater = request.getParameter("quater");
		
		String remarks = request.getParameter("remarks");
		 int yr = Integer.parseInt(request.getParameter("yr"));

		 
		  
			Date date_year_i = null;
			Date date = new Date();
	        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
	        DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
	        date_year_i = formatter2.parse(date1);
	        
			SimpleDateFormat YY = new SimpleDateFormat("yyyy");
			int year2 = Integer.parseInt(YY.format(date_year_i));
			

		 
	    String ofcr_max = request.getParameter("ofcr_max1");
		String ofcr_tot = request.getParameter("ofcr_tot1");
		String ofcr_avg = request.getParameter("ofcr_avg1");
		String jco_max = request.getParameter("jco_max1");
		String jco_tot = request.getParameter("jco_tot1");
		String jco_avg = request.getParameter("jco_avg1");
		String ofcr_fmly_max = request.getParameter("ofcr_fmly_max1");
		String ofcr_fmly_tot = request.getParameter("ofcr_fmly_tot1");
		String ofcr_fmly_avg = request.getParameter("ofcr_fmly_avg1");
		String jco_fmly_max = request.getParameter("jco_fmly_max1");
		String jco_fmly_tot = request.getParameter("jco_fmly_tot1");
		String jco_fmly_avg = request.getParameter("jco_fmly_avg1");
	
			String need_exms_adm = request.getParameter("need_exms_adm1");
			String need_exms_bed_days = request.getParameter("need_exms_bed_days1");
			String no_of_fmly_ref_adm = request.getParameter("no_of_fmly_ref_adm1");
	
						Integer ofcr_max1 = 0;
						if (ofcr_max != "" && !ofcr_max.equals("")) {
							ofcr_max1 = Integer.parseInt(ofcr_max);
						}
						Integer ofcr_tot1 = 0;
						if (ofcr_tot != "" && !ofcr_tot.equals("")) {
							ofcr_tot1 = Integer.parseInt(ofcr_tot);
						}
					    
						Double ofcr_avg1 = (double) 0;
						if (ofcr_avg != "" && !ofcr_avg.equals("")) {
							ofcr_avg1 = Double.parseDouble(ofcr_avg);
						}
						Integer jco_max1 = 0;
						if (jco_max != "" && !jco_max.equals("")) {
							jco_max1 = Integer.parseInt(jco_max);
						}
						Integer jco_tot1 = 0;
						if (jco_tot != "" && !jco_tot.equals("")) {
							jco_tot1 = Integer.parseInt(jco_tot);
						}
						
						Double jco_avg1 = (double) 0;
						if (jco_avg != "" && !jco_avg.equals("")) {
							jco_avg1 = Double.parseDouble(jco_avg);
						}
						Integer ofcr_fmly_max1 = 0;
						if (ofcr_fmly_max != "" && !ofcr_fmly_max.equals("")) {
							ofcr_fmly_max1 = Integer.parseInt(ofcr_fmly_max);
						}
						Integer ofcr_fmly_tot1 = 0;
						if (ofcr_fmly_tot != "" && !ofcr_fmly_tot.equals("")) {
							ofcr_fmly_tot1 = Integer.parseInt(ofcr_fmly_tot);
						}
						Double ofcr_fmly_avg1 = (double) 0;
						if (ofcr_fmly_avg != "" && !ofcr_fmly_avg.equals("")) {
							ofcr_fmly_avg1 = Double.parseDouble(ofcr_fmly_avg);
						}
						Integer jco_fmly_max1 = 0;
						if (jco_fmly_max != "" && !jco_fmly_max.equals("")) {
							jco_fmly_max1 = Integer.parseInt(jco_fmly_max);
						}
						Integer jco_fmly_tot1 = 0;
						if (jco_fmly_tot != "" && !jco_fmly_tot.equals("")) {
							jco_fmly_tot1 = Integer.parseInt(jco_fmly_tot);
						}
						Double jco_fmly_avg1 = (double) 0;
						if (jco_fmly_avg != "" && !jco_fmly_avg.equals("")) {
							jco_fmly_avg1 = Double.parseDouble(jco_fmly_avg);
						}
						Integer need_exms_adm1 = 0;
					if (need_exms_adm != "" && !need_exms_adm.equals("")) {
						need_exms_adm1 = Integer.parseInt(need_exms_adm);
					}
					Integer need_exms_bed_days1 = 0;
					if (need_exms_bed_days != "" && !need_exms_bed_days.equals("")) {
						need_exms_bed_days1 = Integer.parseInt(need_exms_bed_days);
					}
					Integer no_of_fmly_ref_adm1 = 0;
					if (no_of_fmly_ref_adm != "" && !no_of_fmly_ref_adm.equals("")) {
						no_of_fmly_ref_adm1 = Integer.parseInt(no_of_fmly_ref_adm);
					}
		 if( type == null || type.equals("-1")){ 
             model.put("msg", "Please Enter the Service Status");
             return new ModelAndView("redirect:edit_bed_occupancy_Input");
              } 
			 if( sus_no1 == null || sus_no1.equals("")){ 
                 model.put("msg", "Please Enter the SUS No");
                 return new ModelAndView("redirect:edit_bed_occupancy_Input");
                  } 
			 if(validation.sus_noLength(sus_no1) == false){
		        model.put("msg",validation.sus_noMSG);
		        return new ModelAndView("redirect:edit_bed_occupancy_Input");
		            }
		       if( command == null || command.equals("")){ 
				model.put("msg", "Command Name Should not be Null");
				return new ModelAndView("redirect:edit_bed_occupancy_Input");
			}
			if( quater == null || quater.equals("-1")){ 
				model.put("msg", "Please Select the Quarter");
				return new ModelAndView("redirect:edit_bed_occupancy_Input");
			}
			if (request.getParameter("yr").equals("") || request.getParameter("yr") == null) {
				model.put("msg", "Please Enter the Year");
				return new ModelAndView("redirect:edit_bed_occupancy_Input");
			}			
			if (request.getParameter("yr").length() < 4) {
				model.put("msg", "Please Enter the Valid Year");
				return new ModelAndView("redirect:edit_bed_occupancy_Input");
			}
			if (yr > year2) {
				model.put("msg", "Future Year cannot be allowed");
				return new ModelAndView("redirect:edit_bed_occupancy_Input");
			}
			 if(validation.checkYearLength(request.getParameter("yr"))  == false){
				 model.put("msg",validation.yearMSG);
		         return new ModelAndView("redirect:edit_bed_occupancy_Input");
		     }
						
			if(validation.MessageLength(remarks) == false){
                model.put("msg",validation.remarkMSG);
                return new ModelAndView("redirect:edit_bed_occupancy_Input");
           }

			try {
				
				
			if (type.equalsIgnoreCase("1")) {
					
				Long x= MB_Dao.checkExitstingbedocc(sus_no1,quater,lm.getId(),yr);	
			lm.setModified_by(username);
			lm.setModified_on(new Date());
			lm.setSus_no(sus_no1);
			lm.setQtr(quater);
			lm.setYear(yr);
			lm.setOfcr_max(ofcr_max1);
			lm.setOfcr_tot(ofcr_tot1);
			lm.setOfcr_avg(ofcr_avg1);
			lm.setJco_max(jco_max1);
			lm.setJco_tot(jco_tot1);
			lm.setJco_avg(jco_avg1);
			lm.setOfcr_fmly_max(ofcr_fmly_max1);
			lm.setOfcr_fmly_tot(ofcr_fmly_tot1);
			lm.setOfcr_fmly_avg(ofcr_fmly_avg1);
			lm.setJco_fmly_max(jco_fmly_max1);
			lm.setJco_fmly_tot(jco_fmly_tot1);
			lm.setJco_fmly_avg(jco_fmly_avg1);
			lm.setRemarks(remarks);
			  if (x == 0) 
			   {
					
				  model.put("msg", MB_Dao.updatebedoccupancyserv(lm, username));
				} 
 				if (x > 0) 
 				{
 					model.put("msg", "Data already Exist.");
				}		
		}

		if (type.equalsIgnoreCase("2")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			Exs.setId(id);
			Exs.setModified_by(username);
			Exs.setModified_on(new Date());
			Exs.setSus_no(sus_no1);
			Exs.setQtr(quater);
			Exs.setYear(yr);
			Exs.setOfcr_max(ofcr_max1);
			Exs.setOfcr_tot(ofcr_tot1);
			Exs.setOfcr_avg(ofcr_avg1);
			Exs.setJco_max(jco_max1);
			Exs.setJco_tot(jco_tot1);
			Exs.setJco_avg(jco_avg1);
			Exs.setOfcr_fmly_max(ofcr_fmly_max1);
			Exs.setOfcr_fmly_tot(ofcr_fmly_tot1);
			Exs.setOfcr_fmly_avg(ofcr_fmly_avg1);
			Exs.setJco_fmly_max(jco_fmly_max1);
			Exs.setJco_fmly_tot(jco_fmly_tot1);
			Exs.setJco_fmly_avg(jco_fmly_avg1);
			Exs.setNeed_exms_adm(need_exms_adm1);
			Exs.setNeed_exms_bed_days(need_exms_bed_days1);
			Exs.setNo_of_fmly_ref_adm(no_of_fmly_ref_adm1);
			Exs.setRemarks(remarks);
			Long ex= MB_Dao.checkExitstingbedocexserv(sus_no1,quater,Exs.getId(),yr);
			 if (ex == 0) 
			   {
					
				 model.put("msg", MB_Dao.updatebedoccupancyxserv(Exs, username));
				} 
				if (ex > 0) 
				{
					model.put("msg", "Data already Exist.");
				}	
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
		return new ModelAndView("redirect:mnh_input_search_bed_occupancy");

	}

	@RequestMapping(value = "/delete_bed_occupancy_Input", method = RequestMethod.POST)
	public @ResponseBody List<String> delete_bed_occupancy_Input(int deleteid, String type) {
		List<String> list2 = new ArrayList<String>();
		list2.add(MB_Dao.delete_bed_occupancy_Input(deleteid, type));
		return list2;
	}
	
	@RequestMapping(value = "/getquaterAlreadyExist", method = RequestMethod.POST)
	public @ResponseBody Boolean getquaterAlreadyExist(HttpServletRequest request, HttpSession sessionA,
			String qtr,String sus,String type,Integer yr) throws HibernateException, ParseException {
		
		String id = request.getParameter("id");
		return MB_Dao.getquaterAlreadyExist(qtr,sus,id,type,yr);

	}
	
}
