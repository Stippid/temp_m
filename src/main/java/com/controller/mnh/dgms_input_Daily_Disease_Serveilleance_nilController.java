package com.controller.mnh;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.Daily_Disease_serveillenceDaoImpl;
import com.models.mnh.Tb_Med_Daily_Disease_Surv_Details;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class dgms_input_Daily_Disease_Serveilleance_nilController {

	
	@Autowired 
	private Daily_Disease_serveillenceDaoImpl dg;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	MNH_CommonController mcommon = new MNH_CommonController();

	
	MNH_ValidationController validation = new MNH_ValidationController();
	 
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	// *******************Note::open url daily disease*****************************************//
	
	@RequestMapping(value = "/mnh_daily_disease_nil", method = RequestMethod.GET)
	public ModelAndView mnh_daily_disease(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 Boolean val = roledao.ScreenRedirect("mnh_daily_disease", session.getAttribute("roleid").toString());
       /*  if(val == false) {
           return new ModelAndView("AccessTiles");
          }
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}*/
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
		Mmap.put("ml_5", mcommon.getMedrelationList("", session));
		Mmap.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		Mmap.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		Mmap.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("getMedDiseaseName", mcommon.getMedDiseaseName("","", session));		
		return new ModelAndView("mnh_daily_disease_nilTiles");
	}	
	
	// *******************Note::End*****************************************//
	
	
	// *******************Note::save daily disease*****************************************//
	@RequestMapping(value = "/daily_disease_sureve_nilAction", method = RequestMethod.POST)
    public ModelAndView daily_disease_sureve_nilAction(@ModelAttribute("daily_disease_sureveCMD") Tb_Med_Daily_Disease_Surv_Details rs,
                    HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			       Boolean val = roledao.ScreenRedirect("mnh_daily_disease", session.getAttribute("roleid").toString());
		               if(val == false) {
		                 return new ModelAndView("AccessTiles");
		                }
		              if(request.getHeader("Referer") == null ) {
		                  msg = "";
		                  return new ModelAndView("redirect:bodyParameterNotAllow");
		                }
            
            String username = session.getAttribute("username").toString();
            int id = rs.getId() > 0 ? rs.getId() : 0;	
    		Date date = new Date();
            Date date_time_incident_i = null;
            Date date_time_admission_i = null;
            Date date_time_report_i = null;
            Date date_of_birth_i = null;
            DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
           
            DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            
            DateFormat formattern = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
          
           String unit_name1 = request.getParameter("unit_name1");
           String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
		
			String sus_no1=request.getParameter("sus_no1");
			if(roleAccess.equals("Unit")){
				model.put("sus_no",roleSusNo);
				model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				sus_no1 = roleSusNo;
				
			}
            String service = request.getParameter("service");
          
            String type = request.getParameter("type");
            String dt_report = request.getParameter("dt_report1");
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
        
            String persnl_name = request.getParameter("persnl_name");
            String relation = request.getParameter("relation");
            String sex = request.getParameter("sex");
            String appointment = request.getParameter("appointment");
            String date_of_birth = request.getParameter("date_of_birth1"); 
           
            String age_year1 = request.getParameter("age_year");
            String persnl_unit = request.getParameter("persnl_unit");            
            String dt_admission = request.getParameter("dt_admission");
            String dt_incident = request.getParameter("dt_incident");            
        
            String remarks = request.getParameter("remarks");
           
            BigInteger contact_no =BigInteger.ZERO;            
            if(!request.getParameter("contact_no1") .equals("")) 
           {
            	contact_no =new BigInteger(request.getParameter("contact_no1"));
		     }

    		
            BigInteger adhar_card_no =BigInteger.ZERO;            
             if(!request.getParameter("adhar_card_no1") .equals("")) 
            {
            	 adhar_card_no =new BigInteger(request.getParameter("adhar_card_no1"));
		     }

			if(!dt_incident.equals("")){
			                date_time_incident_i = formattern.parse(dt_incident);
			        }
			 if(!dt_admission.equals("")){
			        date_time_admission_i = formatter1.parse(dt_admission);
			}
			if(!dt_report.equals("")){
			        date_time_report_i = formatter2.parse(dt_report);
			}
			if(!date_of_birth.equals("")){
			        date_of_birth_i = formatter2.parse(date_of_birth);
			}

			Integer age_year = 0;
			if (!date_of_birth.equals("") && date_of_birth != null) {
				SimpleDateFormat YY = new SimpleDateFormat("yyyy");
				SimpleDateFormat MM = new SimpleDateFormat("MM");
				SimpleDateFormat dd = new SimpleDateFormat("dd");
				int year = Integer.parseInt(YY.format(date_of_birth_i));
				int MM1 = Integer.parseInt(MM.format(date_of_birth_i));
				int Dd = Integer.parseInt(dd.format(date_of_birth_i));
				LocalDate today = LocalDate.now(); // Today's date
				LocalDate birthday = LocalDate.of(year, MM1, Dd);// Birth date
				Period p = Period.between(birthday, today);
				
				if (age_year1 != "" && !age_year1.equals("")) 
				{
					age_year = p.getYears();
				}
			}

			
			if (sus_no1 == null || sus_no1.equals("")) {
				model.put("msg", "Please Enter the SUS No");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
		/*	if (service == null || service.equals("") || service.equals("-1") || service == "-1") {
				model.put("msg", "Please Select the Service");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
		
			if (type.equals("-1") || type == "-1" || type.equals("") || type == null) {
				model.put("msg", "Please Enter the Service Status");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (dt_report == null || dt_report.equals("")) {
				model.put("msg", "Please Select the Date of Report");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			
			if ((!service.equalsIgnoreCase("ARMY") && !service.equalsIgnoreCase("OTHERS"))
					&& (persnl_no2 == null || persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1"))) {
		
				model.put("msg", "Please Select the Personnel No.");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			
			
			if (category.equals("-1") && service.equalsIgnoreCase("OTHERS")) {
				category = "";
			}
			if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
				model.put("msg", "Please Select the Category");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			
			if (persnl_name == null || persnl_name.equals("")) {
				model.put("msg", "Please Select the  Personnel Name");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (persnl_name.equals("") && !service.equalsIgnoreCase("OTHERS")) {
				model.put("msg", "Please Enter the Personnal Name");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
		
			if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
				model.put("msg", "Please Select the Category");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (sex.equals("-1") || sex == "-1" || sex.equals("") || sex == null) {
				model.put("msg", "Please Enter the Gender");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (relation.equals("-1") || relation == "-1" || relation.equals("") || relation == null) {
				model.put("msg", "Please Enter the Relation");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF") || 
				relation.equals("SISTEROF")) && !sex.equalsIgnoreCase("Female")) {
				 model.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:mnh_daily_disease");
				}
			
			if((relation.equals("BROTHEROF") || relation.equals("HUSBANDOF") || relation.equals("FATHEROF") || 
			  relation.equals("SONOF")) && !sex.equalsIgnoreCase("Male")) {
				model.put("msg", "Gender should be Male Here");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (category.equals("MNS") && !sex.equalsIgnoreCase("Female")) {
				model.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:mnh_daily_disease");
			}

		
			if (dt_admission == null || dt_admission.equals("")) {
				model.put("msg", "Please Select the Date of Admission");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
		
			if (rs.getDiagnosis().getId()==- 1)  {
				model.put("msg", "Please Select the Diagnosis");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			
			
               
			if (validation.sus_noLength(sus_no1) == false) {
				model.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			 String adhar_card_no1 = request.getParameter("adhar_card_no1");
			if (validation.adhar_noLength(adhar_card_no1) == false) {
				model.put("msg", validation.adharnoMSG);
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (validation.persnl_noLength(persnl_no2) == false) {
				model.put("msg", validation.persnl_no2MSG);
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (validation.DiseasemmrLength(persnl_name) == false) {
				model.put("msg", validation.persnl_nameMSG);
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (validation.DiseaseFamilyLength(appointment) == false) {
				model.put("msg", validation.appointmentMSG);
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (validation.MessageLength(persnl_unit) == false) {
				model.put("msg", validation.persl_unitMSG);
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (validation.MessageLength(remarks) == false) {
				model.put("msg", validation.remarkMSG);
				return new ModelAndView("redirect:mnh_daily_disease");
			}      	*/		
			
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			//try {
				//Long d = dg.checkExitstingSURExistlNo_nil(sus_no1, id);
	
				if (id == 0) {
					rs.setCreated_by(username);
					rs.setCreated_on(new Date());
					rs.setPersnl_no(persnl_no);
					rs.setCategory(category);
					rs.setSus_no(sus_no1);
					rs.setType(type);
					rs.setAge_year(age_year);
					rs.setAdhar_card_no(adhar_card_no);
					rs.setDate_time_incident(date_time_incident_i);
					rs.setDate_time_admission(date_time_admission_i);
					rs.setDt_report(date_time_report_i);
					rs.setDate_of_birth(date_of_birth_i);
					rs.setContact_no(contact_no);
					//if (d == 0) {
						session1.save(rs);
						tx.commit();
						model.put("msg", "Data Saved Successfully.");
					//}
					//if (d > 0)
	
					//{
						//model.put("msg", "Data already Exist.");
					//}
				}
	
/*			} catch (RuntimeException e) {
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
			}*/
	
			return new ModelAndView("redirect:mnh_daily_disease_nil");
		} 
	
}
