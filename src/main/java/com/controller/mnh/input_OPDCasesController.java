package com.controller.mnh;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.dao.mnh.OPDCasesDao;
import com.models.mnh.Tb_Med_Opd_Cases;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class input_OPDCasesController {
	@Autowired
	private RoleBaseMenuDAO roledao;
	
   MNH_CommonController mcommon = new MNH_CommonController();
	
	MNH_ValidationController validation = new MNH_ValidationController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	@Autowired
	private OPDCasesDao OPD;

	@RequestMapping(value = "/admin/mnh_input_opd_cases", method = RequestMethod.GET)
	public ModelAndView mnh_input_opd_cases(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		Boolean val = roledao.ScreenRedirect("mnh_input_opd_cases", session.getAttribute("roleid").toString());
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
		Mmap.put("msg", msg);
		Mmap.put("getMedSystemCodeQuaQUARTER", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		Mmap.put("m12", mcommon.getMedSystemCode("WARD", "", session));
		return new ModelAndView("mnh_input_opd_casesTiles");
	}
	
	@RequestMapping(value = "/ODPcasesAction" , method = RequestMethod.POST)
	public ModelAndView ODPcasesAction(@ModelAttribute("ODPcasesCMD") Tb_Med_Opd_Cases rs,@RequestParam(value = "msg", required = false) String msg,
			BindingResult result,HttpServletRequest request, ModelMap model, HttpSession session) throws ParseException {

	
		
		   int id = rs.getId() > 0 ? rs.getId() : 0;
		Boolean val = roledao.ScreenRedirect("mnh_input_opd_cases", session.getAttribute("roleid").toString());
		if (val == false) {
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
		String ward = request.getParameter("ward"); 
		String quater = request.getParameter("quater");
		int year = Integer.parseInt(request.getParameter("year"));
		String command = request.getParameter("command"); 
		Date date_year_i = null;
		Date date = new Date();
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
        DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        date_year_i = formatter2.parse(date1);
        String remarks = request.getParameter("remarks");
		SimpleDateFormat YY = new SimpleDateFormat("yyyy");
		int year2 = Integer.parseInt(YY.format(date_year_i));
		
	/*	    if( sus_no1 == null || sus_no1.equals("")){ 
				model.put("msg", "Please Enter the SUS No");
				return new ModelAndView("redirect:mnh_input_opd_cases");
			}
		    if(validation.sus_noLength(request.getParameter("sus_no")) == false){
				model.put("msg",validation.sus_noMSG);
				return new ModelAndView("redirect:mnh_input_opd_cases");
		    }
		    if( command == null || command.equals("")){ 
				model.put("msg", "Command Name Should not be Null");
				return new ModelAndView("redirect:mnh_input_opd_cases");
			}
		    if( quater == null || quater.equals("-1")){ 
				model.put("msg", "Please Select the Quarter");
				return new ModelAndView("redirect:mnh_input_opd_cases");
			}
		    if(validation.check_future_quarter(request.getParameter("quater"))  == false){
				 model.put("msg",validation.check_future_quarter);
				 return new ModelAndView("redirect:mnh_input_opd_cases");
		     }
			if (request.getParameter("year").equals("") || request.getParameter("year") == null) {
				model.put("msg", "Please Enter the Year");
				return new ModelAndView("redirect:mnh_input_opd_cases");
			}
			if (request.getParameter("year").length() < 4) {
				model.put("msg", "Please Enter the Valid Year");
				return new ModelAndView("redirect:mnh_input_opd_cases");
			}
			if (year > year2) {
				model.put("msg", "Future Year cannot be allowed");
				return new ModelAndView("redirect:mnh_input_opd_cases");
			}
			 if(validation.checkYearLength(request.getParameter("year"))  == false){
				 model.put("msg",validation.yearMSG);
		         return new ModelAndView("redirect:mnh_input_opd_cases");
		     }
			if(validation.MessageLength(remarks) == false){
                 model.put("msg",validation.remarkMSG);
                 return new ModelAndView("redirect:mnh_input_opd_cases");
            }*/
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		//try {
			
			Long op= OPD.checkExitstingopdinput(sus_no1,quater,year,id,ward);
				rs.setCreated_on(new Date());
				rs.setCreated_by(username);
				rs.setQtr(quater);
				rs.setYear(year2);
				rs.setWard(ward);
				if (op == 0) {
			    	 sessionHQL.save(rs);
						tx.commit();
						model.put("msg", "Data Saved Successfully.");
					} 
					if (op > 0) 
						
					{
						model.put("msg", "Data already Exist.");
					}
/*		 }catch(RuntimeException e){
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
		}*/
	   	return new ModelAndView("redirect:mnh_input_opd_cases");
	}
	
	@RequestMapping(value = "/admin/mnh_input_search_opd_cases", method = RequestMethod.GET)
	public ModelAndView mnh_input_search_opd_cases(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		Boolean val = roledao.ScreenRedirect("mnh_input_search_opd_cases", session.getAttribute("roleid").toString());
		if (val == false) {
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
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				sus_no1 = roleSusNo;
				
			}
	     
			Mmap.put("msg", msg);		
			Mmap.put("getMedSystemCodeQuarter", mcommon.getMedSystemCodeQua("QUARTER", "", session));
			Mmap.put("service_type", mcommon.getMedSystemCodeQua("Service_Type", "", session));
			Mmap.put("m12", mcommon.getMedSystemCode("WARD", "", session));
		return new ModelAndView("mnh_input_opd_cases_searchTiles");
	}
	
	@RequestMapping(value = "/search_capture_opd_cases_input" , method = RequestMethod.POST)
	public ModelAndView search_capture_opd_cases_input(ModelMap model, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("sus1") String sus1,
			String unit1,String qtr1, String yr1,String ward1) {

		Boolean val = roledao.ScreenRedirect("mnh_input_search_opd_cases", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		if (unit1.equals("")) {
			model.put("msg", "Please Enter the Unit Name");
			return new ModelAndView("redirect:mnh_input_search_opd_cases");
		}
		if (sus1.equals("")) {
			model.put("msg", "Please Enter the SUS No");
			return new ModelAndView("redirect:mnh_input_search_opd_cases");
		}
			
		if(validation.DiseasemmrLength(unit1) == false){
			model.put("msg",validation.hospital_nameMSG);
			return new ModelAndView("redirect:mnh_input_search_opd_cases");
		 }
		 if(validation.sus_noLength(sus1) == false){
			model.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:mnh_input_search_opd_cases");
		 }
		 String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
		
			String f_sus_no=sus1;
			if(roleAccess.equals("Unit")){
				model.put("sus_no",roleSusNo);
				model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				f_sus_no = roleSusNo;
				
			}
		List<Map<String, Object>> list = OPD.CaptureOPDCase(sus1,qtr1, yr1,ward1);
		model.put("list", list);
		model.put("size", list.size());
		model.put("getMedSystemCodeQuaQUARTER", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		model.put("getMedSystemCodeQuarter", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		model.put("service_type", mcommon.getMedSystemCodeQua("Service_Type", "", session));
		model.put("m12", mcommon.getMedSystemCode("WARD", "", session));
		model.put("sus1", f_sus_no);
		model.put("unit1", unit1);
		model.put("qtr1", qtr1);
		model.put("yr1", yr1);
		model.put("ward1", ward1);
		model.put("getMedSystemCodeQuarter", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		
		return new ModelAndView("mnh_input_opd_cases_searchTiles");
	}

	@RequestMapping(value = "/mnh_inputs_opd_cases_edit",method = RequestMethod.POST )
	public ModelAndView mnh_inputs_opd_cases_edit(@ModelAttribute("updateid") String updateid, ModelMap model, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpSession sessionEdit) {
		Boolean val = roledao.ScreenRedirect("mnh_input_search_opd_cases", sessionEdit.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Tb_Med_Opd_Cases OpdDetails = OPD.getOPDCasesByid(Integer.parseInt(updateid));
		model.put("EditODPcasesCMD", OpdDetails);
		
		model.put("getMedSystemCodeQuaQUARTER", mcommon.getMedSystemCodeQua("QUARTER", "", sessionEdit));
		model.put("msg", msg);
		model.put("m12", mcommon.getMedSystemCode("WARD", "", sessionEdit));
		return new ModelAndView("mnh_inputs_opd_cases_editTiles");
	}
	
	@RequestMapping(value = "/EditODPcasesAction", method = RequestMethod.POST)
	public ModelAndView EditODPcasesAction(@ModelAttribute("EditODPcasesCMD") Tb_Med_Opd_Cases lm, HttpServletRequest request,
			BindingResult result,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		Boolean val = roledao.ScreenRedirect("mnh_input_search_opd_cases", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("updateid", lm.getId());
		  String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
		
			String sus_no1=request.getParameter("sus_no");
			if(roleAccess.equals("Unit")){
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				sus_no1 = roleSusNo;
				
			}
	     
		
		 String username = session.getAttribute("username").toString();
		 String quater = request.getParameter("quater");
		 int year = Integer.parseInt(request.getParameter("year"));
		 String command = request.getParameter("command"); 
		 String ward = request.getParameter("ward"); 
		 String remarks = request.getParameter("remarks");
		 Date date_year_i = null;
		 Date date = new Date();
         String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
         DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
         date_year_i = formatter2.parse(date1);
        
		SimpleDateFormat YY = new SimpleDateFormat("yyyy");
		int year2 = Integer.parseInt(YY.format(date_year_i));
		
	
		if( sus_no1 == null || sus_no1.equals("")){ 
			Mmap.put("msg", "Please Enter the SUS No");
			return new ModelAndView("redirect:mnh_inputs_opd_cases_edit");
		}
	    if(validation.sus_noLength(request.getParameter("sus_no")) == false){
	    	Mmap.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:mnh_inputs_opd_cases_edit");
	    }
	    if( command == null || command.equals("")){ 
	    	Mmap.put("msg", "Command Name Should not be Null");
			return new ModelAndView("redirect:mnh_inputs_opd_cases_edit");
		}
	    if( quater == null || quater.equals("-1")){ 
	    	Mmap.put("msg", "Please Select the Quarter");
			return new ModelAndView("redirect:mnh_inputs_opd_cases_edit");
		}
	    if(validation.check_future_quarter(request.getParameter("quater"))  == false){
	    	Mmap.put("msg",validation.check_future_quarter);
	    	return new ModelAndView("redirect:mnh_inputs_opd_cases_edit");
	     }
		if (request.getParameter("year").equals("") || request.getParameter("year") == null) {
			Mmap.put("msg", "Please Enter the Year");
			return new ModelAndView("redirect:mnh_inputs_opd_cases_edit");
		}
		if (request.getParameter("year").length() < 4) {
			Mmap.put("msg", "Please Enter the Valid Year");
			return new ModelAndView("redirect:mnh_inputs_opd_cases_edit");
		}
		if (year > year2) {
			Mmap.put("msg", "Future Year cannot be allowed");
			return new ModelAndView("redirect:mnh_inputs_opd_cases_edit");
		}
		 if(validation.checkYearLength(request.getParameter("year"))  == false){
			 Mmap.put("msg",validation.yearMSG);
	         return new ModelAndView("redirect:mnh_inputs_opd_cases_edit");
	     }
		if(validation.MessageLength(remarks) == false){
			Mmap.put("msg",validation.remarkMSG);
             return new ModelAndView("redirect:mnh_inputs_opd_cases_edit");
        }
	       		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	       		Transaction tx = sessionHQL.beginTransaction();
		try {
			Long op= OPD.checkExitstingopdinput(sus_no1,quater,year,lm.getId(),ward);
	   	   
			
			 lm.setModified_by(username);
			 lm.setModified_on(new Date());
		     lm.setQtr(quater);
		     lm.setYear(year2);
			
			 if (op == 0) 
			   {
					
				 Mmap.put("msg", OPD.Update_OPDCases(lm, username));
				} 
				if (op > 0) 
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
		return new ModelAndView("redirect:mnh_input_search_opd_cases");
	}
	@RequestMapping(value = "/mnh_inputs_opd_cases_delete" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView mnh_inputs_opd_cases_delete(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		try {
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			String hqlUpdate = "delete from Tb_Med_Opd_Cases where id=:id";
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
		return new ModelAndView("redirect:mnh_input_search_opd_cases");
	}
}
