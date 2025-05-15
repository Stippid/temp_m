package com.controller.psg.update_offr_data;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.models.psg.Transaction.TB_CENSUS_QUALIFICATION;
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Qualification_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();

	/*@RequestMapping(value = "/admin/QualificationUrl", method = RequestMethod.GET)
	public ModelAndView Institute(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());
		Mmap.put("getInstituteNameList", mcommon.getInstituteNameList());
		Mmap.put("getDivclass", mcommon.getDivclass());
        Mmap.put("getSubject", mcommon.getSubject());
        Mmap.put("getExamination", mcommon.getExamination());
		
		return new ModelAndView("QualificationTiles");
	}*/
	
	@RequestMapping(value = "/admin/update_census_getQualificationData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_QUALIFICATION> update_census_getQualificationData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("q_id"));
		/// bisag 241122 V2 (change comm_id int to big int)
				BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_QUALIFICATION where cen_id=:cen_id and comm_id=:comm_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_QUALIFICATION> list = (List<TB_CENSUS_QUALIFICATION>) query.list();
		tx.commit();
		sessionHQL.close();
	
		return list;
	}

	@RequestMapping(value = "/admin/update_census_qualification_action", method = RequestMethod.POST)
	public @ResponseBody String update_census_qualification_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		String username = session.getAttribute("username").toString();

		String type = request.getParameter("type");
		String examination_pass = request.getParameter("examination_pass");
		String passing_year = request.getParameter("passing_year");
		String div_class = request.getParameter("div_class");
		String subject = request.getParameter("subject");
		String institute = request.getParameter("institute");
		String qualification_ch_id = request.getParameter("qualification_ch_id");
		String q_id = request.getParameter("q_id");
		String com_id = request.getParameter("com_id");
		String qual_authority = request.getParameter("qual_authority");
		String qual_doa = request.getParameter("qual_doa");
		String msg = "";
		
		
		if(type == null || type.equals("0")){ 
			return "Please Select the Type";
		}
		if(examination_pass == null || examination_pass.equals("")){ 
			return "Please Enter the Examination Passed";
		}
		if(passing_year == null || passing_year.equals("")){ 
			return "Please Enter the yr of passing";
		}
		
		if(div_class == null || div_class.equals("")){ 
			return "Please Enter the Div/class";
		}
		if(subject == null || subject.equals("")){ 
			return "Please Enter the Subject";
		}
		if(institute == null || institute.equals("0")){ 
			return "Please Select the Institute & place";
		}
		if(qual_doa == null || qual_doa.equals("")){ 
			return "Please Enter Date of Authority";
		}


		TB_CENSUS_QUALIFICATION q = new TB_CENSUS_QUALIFICATION();

		try {
			if (Integer.parseInt(qualification_ch_id) == 0) {

				

				q.setType(Integer.parseInt(type));
				q.setExamination_pass(0);
				q.setPassing_year(Integer.parseInt(passing_year));
				q.setDiv_class(Integer.parseInt(div_class) );
				q.setSubject(subject);
				q.setInstitute(institute);
				q.setCen_id(Integer.parseInt(q_id));
				q.setComm_id(new BigInteger(com_id));
				q.setAuthority(qual_authority);
				q.setDate_of_authority(format.parse(qual_doa));
				q.setCreated_by(username);
				q.setCreated_on(date);

				int id = (int) sessionhql.save(q);
				msg = Integer.toString(id);
			
			} else {

				String hql = "update TB_CENSUS_QUALIFICATION set modify_by=:modify_by ,modify_on=:modify_on ,type=:type,examination_pass=:examination_pass,"
						+ " passing_year=:passing_year,div_class=:div_class,subject=:subject,institute=:institute,"
						+" authority=:authority,date_of_authority=:date_of_authority where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("type", Integer.parseInt(type))
						.setString("examination_pass", examination_pass)
						.setInteger("passing_year", Integer.parseInt(passing_year)).setInteger("div_class",Integer.parseInt(div_class) )
						.setString("subject", subject).setInteger("institute", Integer.parseInt(institute))
						.setInteger("id", Integer.parseInt(qualification_ch_id)).setString("modify_by", username)
						.setTimestamp("modify_on", date)
						.setTimestamp("date_of_authority", (format.parse(qual_doa)))
						.setString("authority", qual_authority);
				msg = query.executeUpdate() > 0 ? "update" : "0";

			}
			 mcommon.update_offr_status(Integer.parseInt(q_id),username);
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "0";
			} catch (RuntimeException rbe) {
				msg = "0";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}

		// sessionhql.close();

		return msg;
	}

	@RequestMapping(value = "/admin/update_census_qualification_delete_action", method = RequestMethod.POST)
	public @ResponseBody String update_census_qualification_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("qualification_ch_id"));
		try {
			String hqlUpdate = "delete from TB_CENSUS_QUALIFICATION where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();
			if (app > 0) {
				msg = "1";
			} else {
				msg = "0";
			}
		} catch (Exception e) {

		}
		return msg;
	}
	
	
	@RequestMapping(value = "/admin/getQualification", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_QUALIFICATION> getQualification(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("q_id"));
		
		/// bisag 241122 V2 (change comm_id int to big int)
				BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CENSUS_QUALIFICATION where cen_id=:cen_id and comm_id=:comm_id and status='0'";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);

		List<TB_CENSUS_QUALIFICATION> list = (List<TB_CENSUS_QUALIFICATION>) query.list();

		tx.commit();

		sessionHQL.close();

	

		return list;

	}
	
/*--------------------- For Approval ----------------------------------*/
	
	/// bisag 241122 V2 (change comm_id int to big int)
			
	public @ResponseBody List<TB_CENSUS_QUALIFICATION> update_census_getQualificationData1(int id,BigInteger comm_id){
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_QUALIFICATION where cen_id=:cen_id and comm_id=:comm_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_QUALIFICATION> list = (List<TB_CENSUS_QUALIFICATION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getSpouseAppQualificationData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_SPOUSE_QUALIFICATION> getSpouseQualificationData(ModelMap Mmap,
			HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("q_id"));
		String hqlUpdate = " from TB_CENSUS_SPOUSE_QUALIFICATION where spouse_id=:spouse_id ";
		String approoval_status = request.getParameter("app_status");
		if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("1")) {
			hqlUpdate += " and status=1 ";
		}
		else if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			hqlUpdate += " and status=3 ";
		}
		else {
			hqlUpdate += " and status=0 ";
		}
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("spouse_id", id);
		List<TB_CENSUS_SPOUSE_QUALIFICATION> list = (List<TB_CENSUS_SPOUSE_QUALIFICATION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public String Update_Qualification(TB_CENSUS_QUALIFICATION obj,String username){
  	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
  	   Transaction tx = sessionHQL.beginTransaction();
  	  
  		  String msg = "";
  		  String msg1= "";
  		  try{
  			  
  			    /* String hql1 = "update TB_CENSUS_QUALIFICATION set status=:status where cen_id=:census_id and comm_id=:comm_id and status != '0' ";
  			   
  				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("census_id", obj.getCen_id())
  						.setInteger("comm_id",obj.getComm_id());
  						
  				 msg1 = query1.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";*/
  				 
  		    
  			    String hql = "update TB_CENSUS_QUALIFICATION set modify_by=:modified_by,modify_on=:modified_date,status=:status  "
  						+ " where cen_id=:census_id and comm_id=:comm_id and status = '0'";
  			  
  				Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setTimestamp("modified_date", new Date())
  						.setInteger("status", 1).setInteger("census_id", obj.getCen_id()).setBigInteger("comm_id",obj.getComm_id());
  			
  		          msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
  		      
  		          tx.commit();
  		  }
  		  catch (Exception e) {
  		          msg = "Data Not Updated.";
  		          tx.rollback();
  		  }
  		  finally {
  		          sessionHQL.close();
  		  }
  		  return msg1;
  	}
}
