package com.controller.mnh;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.StillinHospDetailsDao;
import com.models.mnh.Scrutiny_Search_Model;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class input_StillinHospDetailsController {

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private StillinHospDetailsDao SH;
	
    MNH_CommonController mcommon = new MNH_CommonController();
	
	MNH_ValidationController validation = new MNH_ValidationController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	

/////////////////////////////STILL_HOSPITAL_DTLS///////////////////////////////////////	

	@RequestMapping(value = "/admin/STILL_HOSPITAL_DTLS", method = RequestMethod.GET)
	public ModelAndView STILL_HOSPITAL_DTLS(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		Boolean val = roledao.ScreenRedirect("STILL_HOSPITAL_DTLS", session.getAttribute("roleid").toString());
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
		return new ModelAndView("Still_in_hospital_detailsTiles");
	
	}

	@RequestMapping(value = "/search_Still_Hospdtls",method = RequestMethod.POST)
	public ModelAndView search_Still_Hospdtls(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "sus1", required = false) String sus_no,
		@RequestParam(value = "frm_dt1", required = false) String frm_dt1,
		@RequestParam(value = "to_dt1", required = false) String to_dt1,
		@RequestParam(value = "unit1", required = false) String unit1,
		@RequestParam(value = "persnl_no1", required = false) String persnl_no1,
		@RequestParam(value = "and_no1", required = false) String and_no1){
		
     String date1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
       
	 		Boolean val = roledao.ScreenRedirect("STILL_HOSPITAL_DTLS", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
	 	
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			/*if (sus_no.equals("")) {
				Mmap.put("msg", "Please Enter SUS No");
				return new ModelAndView("redirect:STILL_HOSPITAL_DTLS");
			}*/
		  /*  if (frm_dt1.equals("") || frm_dt1 == "") {
				Mmap.put("msg", "Please Select From Date");
				return new ModelAndView("redirect:STILL_HOSPITAL_DTLS");
			}
			 if (to_dt1.equals("") || to_dt1 == "") {
				Mmap.put("msg", "Please Select To Date");
				return new ModelAndView("redirect:STILL_HOSPITAL_DTLS");
			}
	    	if(validation.DiseasemmrLength(unit1) == false){
				Mmap.put("msg",validation.hospital_nameMSG);
			    return new ModelAndView("redirect:STILL_HOSPITAL_DTLS");
		     }
			 if(validation.sus_noLength(sus_no) == false){
				 Mmap.put("msg",validation.sus_noMSG);
				 return new ModelAndView("redirect:STILL_HOSPITAL_DTLS");
			 }
			 */
			 
			 
			 String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
			
				String f_sus_no=sus_no;
				if(roleAccess.equals("Unit")){
					Mmap.put("sus_no",roleSusNo);
					Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
					f_sus_no = roleSusNo;
					
				}
			try {
				Date to_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(to_dt1);
				Date from_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(frm_dt1);
		

			/*	if (to_date1.before(from_date1)) {
					Mmap.put("msg", "To date must be greater than from dateaaaaa");
					return new ModelAndView("redirect:STILL_HOSPITAL_DTLS");
				}*/
			} catch (ParseException e) {
				
			}
			
		Mmap.put("ml_1", mcommon.getMedDepCode("", session));
		Mmap.put("DISPOSAL_list", mcommon.getMedSystemCode("DISPOSAL", "", session));	
		Mmap.put("date", date1);
	 	Mmap.put("sus1", f_sus_no);
	 	Mmap.put("unit1", unit1);
	 	Mmap.put("frm_dt1", frm_dt1);
	 	Mmap.put("to_dt1", to_dt1);
	 
	 	List<Map<String, Object>> list = SH.Searchstillrpt(sus_no, frm_dt1, to_dt1,persnl_no1,and_no1); 	
	 	Mmap.put("list", list);
		if (list.size() > 0) {
			Mmap.put("size", list.size());
		} else {
			Mmap.put("size", "0");
		}	
		return new ModelAndView("Still_in_hospital_detailsTiles");

	}
	
	@RequestMapping(value = "/edit_still_hosdtl",method = RequestMethod.POST)
	public ModelAndView edit_still_hosdtl(@ModelAttribute("hdid") int id,
			@RequestParam("hddschrg_date") String dschrg_date, @RequestParam("hddisposal") String disposal,
			@RequestParam("hdicd_remarks_d") String icd_remarks_d, @RequestParam("a_rem") String a_rem,
			@RequestParam("d_rem") String d_rem, @RequestParam("sus2") String sus1,
			@ModelAttribute("from_date2") String from_date1, @RequestParam("to_date2") String to_date1,
			@RequestParam("hddischarge_remarks") String discharge_remarks, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session, HttpServletRequest request) throws ParseException {

		Date dschrg_date1 = null;

		Boolean val = roledao.ScreenRedirect("STILL_HOSPITAL_DTLS", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
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

		String a_rem1 = request.getParameter("a_rem");
		String icd_code2[] = null;
		if (a_rem1.contains("-")) {
			icd_code2 = a_rem1.split("-");
		}

		 String b_1[] = null;
	     String b_2="";
	   /*  if(a_rem1 != null  && !a_rem1.equals(""))
	     {*/
	    	
			 b_1 = a_rem1.split("-");
			 b_2 = b_1[0].substring(0,1).toUpperCase();
			
	     //}
		
		
		a_rem1 = b_2;
	
		
		String d_rem1 = request.getParameter("d_rem");
		String icd_cause2[] = null;
		
		if (!d_rem1.equals("")  || !d_rem1.equals(null)) {

		if (d_rem1.contains("-")) {
			icd_cause2 = d_rem1.split("-");
		
		}
		}
		
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();

		if (!dschrg_date.equals("")) {
			dschrg_date1 = formatter1.parse(dschrg_date);
		}
		//try {
			if (dschrg_date == "") {
				model.put("msg", "Please Select Discharge Date");
				return new ModelAndView("redirect:STILL_HOSPITAL_DTLS");
			}
			if (disposal.equals("0")) {
				model.put("msg", "Please Select Disposal");
				return new ModelAndView("redirect:STILL_HOSPITAL_DTLS");
			}
			if (icd_remarks_d.equals("")) {
				model.put("msg", "Please Enter Discharge Diagnosis");
				return new ModelAndView("redirect:STILL_HOSPITAL_DTLS");
			}

			if (a_rem == "") {
				model.put("msg", "Please Select Discharge ICD Code");
				return new ModelAndView("redirect:STILL_HOSPITAL_DTLS");
			}
			/*if (d_rem.equals("")) {
				model.put("msg", "Please Select Discharge ICD Cause");
				return new ModelAndView("redirect:STILL_HOSPITAL_DTLS");
			}*/
			if (discharge_remarks.equals("")) {
				model.put("msg", "Please Enter Discharge Remarks");
				return new ModelAndView("redirect:STILL_HOSPITAL_DTLS");
			}
			
			
			Query query = null;
			
			
			if(!a_rem1.equals("S") || a_rem1.equals("T") || a_rem1.equals("X") || a_rem1.equals("V") || a_rem1.equals("W") || a_rem1.equals("Y")) {
				
				
				String hql = "update Scrutiny_Search_Model set dschrg_date=:dschrg_date , disposal=:disposal , diagnosis_code1d =:a_rem, discharge_remarks=:discharge_remarks where id=:id";
				
				
				query = session1.createQuery(hql).setDate("dschrg_date", dschrg_date1).setString("disposal", disposal)
						.setString("a_rem", icd_code2[0])
						.setString("discharge_remarks", discharge_remarks).setInteger("id", id);

				
				
			}
			
			
			else
			{
				
		  String hql = "update Scrutiny_Search_Model set dschrg_date=:dschrg_date , disposal=:disposal , diagnosis_code1d =:a_rem , icd_cause_code1d=:d_rem , discharge_remarks=:discharge_remarks where id=:id";
				
			query = session1.createQuery(hql).setDate("dschrg_date", dschrg_date1).setString("disposal", disposal)
					.setString("a_rem", icd_code2[0]).setString("d_rem", icd_cause2[0])
					.setString("discharge_remarks", discharge_remarks).setInteger("id", id);

			
			
			}
			int rowCount = query.executeUpdate();
			tx.commit();
			
			if (rowCount > 0) {
				model.put("msg", "Data Updated Successfully");
			} else {
				model.put("msg", "Data Not Updated Successfully");
			}
/*		}

		catch (Exception e) {
			session1.getTransaction().rollback();
			return null;
		} finally {
			session1.close();
		}*/
			
		return new ModelAndView("Still_in_hospital_detailsTiles");
	}
}
