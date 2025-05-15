package com.controller.psg.Jco_Update_JcoData;



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

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_ARMY_COURSE_JCO;
import com.persistance.util.HibernateUtil;

@Controller

@RequestMapping(value = { "admin", "/", "user" })

public class Army_courseJcoController {

	

	Psg_CommonController mcommon = new Psg_CommonController();

	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	
	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/admin/update_JCO_army_course_detail_action", method = RequestMethod.POST)
	public @ResponseBody String update_JCO_army_course_detail_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date army_start_date = null;
		Date army_date_of_completion = null;
		Date army_course_date_auth = null;
		String course_type_ot=null;
		String course_institue_ot=null;
		String ar_course_div_ot=null;
		String username = session.getAttribute("username").toString();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String army_course_name = request.getParameter("army_course_name");
		String army_course_abbr = request.getParameter("army_course_abbr");
		String army_course_institue = request.getParameter("army_course_institue");
		String army_course_institue_ot = request.getParameter("army_course_institue_ot");
		String army_course_institueV = request.getParameter("army_course_institueV");
		String army_course_div_grade = request.getParameter("army_course_div_grade");
		String army_course_type = request.getParameter("army_course_type");
		String army_course_start_date = request.getParameter("army_course_start_date");
		
		
		String army_course_date_of_completion = request.getParameter("army_course_date_of_completion");
		
		
		String army_course_ch_id = request.getParameter("army_course_ch_id");

		String jco_id = request.getParameter("jco_id");
		String army_course_authority = request.getParameter("army_course_authority");
		String army_course_doa = request.getParameter("army_course_doa");
		
		String msg = "";
		if (army_course_authority == null || army_course_authority.equals("")) {
			return "Please Enter Authority";
		}
		 if (!validation.isValidAuth(army_course_authority)) {
		 		return validation.isValidAuthMSG + " Authority";	
			}
	 		if (!validation.isvalidLength(army_course_authority,validation.authorityMax,validation.authorityMin)) {
	 			return "Authority " + validation.isValidLengthMSG;	
			}
		if (army_course_doa == null || army_course_doa.equals("null") || army_course_doa.equals("DD/MM/YYYY") || army_course_doa.equals("")) {	
			return "Please Enter Date of Authority";
		}
		else if (!validation.isValidDate(army_course_doa)) {
 			return validation.isValidDateMSG + " of Authority";	
		}
		else {
			army_course_date_auth = format.parse(army_course_doa);
		}
		if (army_course_name == null || army_course_name.equals("")) {
			return "Please Enter the Course Name";
		}
	
		if (army_course_abbr == null || army_course_abbr.equals("")) {
			return "Please Enter the Course Abbreviation";
		}
		
		if (army_course_institue == null || army_course_institue.equals("0")) {
			return "Please Enter the Course Institute";
		}
		if (army_course_div_grade == null || army_course_div_grade.equals("0")) {
			return "Please Select The Div/Grade";
		}
		if (army_course_type == null || army_course_type.equals("0")) {
			return "Please Select The Course Type";
		}
		if (army_course_start_date == null || army_course_start_date.equals("null") || army_course_start_date.equals("DD/MM/YYYY") || army_course_start_date.equals("")) {	
			return "Please Enter Start Date";
		}
		else if (!validation.isValidDate(army_course_start_date)) {
 			return validation.isValidDateMSG + " of Start";	
		}
		else
		{
			army_start_date = format.parse(army_course_start_date);
		}
		if (army_course_date_of_completion == null || army_course_date_of_completion.equals("null") || army_course_date_of_completion.equals("DD/MM/YYYY") || army_course_date_of_completion.equals("")) {	
			return "Please Enter Date of Completion";
		}
		else if (!validation.isValidDate(army_course_date_of_completion)) {
 			return validation.isValidDateMSG + " of Completion";	
		}
		else
		{
		army_date_of_completion = format.parse(army_course_date_of_completion);
		}
//		if(request.getParameter("course_type_ot")!=null  && !request.getParameter("course_type_ot").equals("")) {
//			course_type_ot=request.getParameter("course_type_ot");
//		}
		
		if(request.getParameter("ar_course_div_ot")!=null  && !request.getParameter("ar_course_div_ot").equals("")) {
			ar_course_div_ot=request.getParameter("ar_course_div_ot");
		}
		if(army_course_institue_ot!=null  && !army_course_institue_ot.trim().equals("")) {
			course_institue_ot=army_course_institue_ot;
		}
		
		army_course_name = army_course_name.replace("&#40;", "(");

		army_course_name = army_course_name.replace("&#41;", ")");
        
		army_course_abbr = army_course_abbr.replace("&#40;", "(");

		army_course_abbr = army_course_abbr.replace("&#41;", ")");
        
		try {
			if (Integer.parseInt(army_course_ch_id) == 0) {
				TB_CENSUS_ARMY_COURSE_JCO army_c = new TB_CENSUS_ARMY_COURSE_JCO();
			
				army_c.setJco_id(Integer.parseInt(jco_id));
				army_c.setCourse_name(army_course_name);
				army_c.setDiv_grade(army_course_div_grade);
				army_c.setCourse_type(army_course_type);
				army_c.setCourse_abbreviation(army_course_abbr);
				army_c.setCourse_institute(Integer.parseInt(army_course_institue));
				army_c.setCourse_institute_other(course_institue_ot);
				//if (army_course_start_date != null && !army_course_start_date.equals("")) {
					army_c.setStart_date(army_start_date);
				//}
				//if (army_course_date_of_completion != null && !army_course_date_of_completion.equals("")) {
					army_c.setDate_of_completion(army_date_of_completion);
				//}
				army_c.setStatus(0);
				army_c.setAuthority(army_course_authority);
				army_c.setDate_of_authority(army_course_date_auth);
				army_c.setCreated_by(username);
				army_c.setCreated_on(date);
				army_c.setAr_course_div_ot(ar_course_div_ot);
				army_c.setCourse_type_ot(course_type_ot);
				army_c.setInitiated_from("u");
				int id = (int) sessionhql.save(army_c);
				msg = Integer.toString(id);
			} else {
				String hql = "update TB_CENSUS_ARMY_COURSE_JCO set modify_by=:modify_by ,modify_on=:modify_on ,course_name=:course_name,div_grade=:div_grade, "
						+ " ar_course_div_ot=:ar_course_div_ot,course_abbreviation=:course_abbreviation,course_institute=:course_institute,course_institute_other=:course_institute_other,course_type=:course_type,start_date=:start_date,date_of_completion=:date_of_completion, authority=:authority,date_of_authority=:date_of_authority,status=:status  where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("course_name", army_course_name)
						.setString("div_grade", army_course_div_grade).setString("course_type", army_course_type)
						.setTimestamp("start_date", (army_start_date))
						.setTimestamp("date_of_completion", (army_date_of_completion))
						.setInteger("id", Integer.parseInt(army_course_ch_id)).setString("modify_by", username)
						.setTimestamp("modify_on", date).setString("course_institute_other", course_institue_ot).setString("ar_course_div_ot", ar_course_div_ot)
						.setTimestamp("date_of_authority", army_course_date_auth)
						.setString("course_abbreviation", army_course_abbr)
						.setString("authority", army_course_authority)
						.setInteger("course_institute", Integer.parseInt(army_course_institue))
						
						.setInteger("status", 0);
				msg = query.executeUpdate() > 0 ? "update" : "0";
			}
			
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			
			}
			else
			{
				pjc.update_JcoOr_status(Integer.parseInt(jco_id),username);
			}
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
		return msg;
	}
	
	@RequestMapping(value = "/admin/update_JCO_army_course_delete_action", method = RequestMethod.POST)
	public @ResponseBody String update_JCO_army_course_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("army_course_ch_id"));
		try {
			String hqlUpdate = "delete from TB_CENSUS_ARMY_COURSE_JCO where id=:id";
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
	

	
	@RequestMapping(value = "/admin/update_JCO_army_course_getData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_ARMY_COURSE_JCO> update_JCO_army_course_getData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CENSUS_ARMY_COURSE_JCO where  jco_id=:jco_id and status=0 order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CENSUS_ARMY_COURSE_JCO> list = (List<TB_CENSUS_ARMY_COURSE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}
	
	public @ResponseBody List<TB_CENSUS_ARMY_COURSE_JCO> update_census_army_courseData1( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		try 
		{
		String hqlUpdate = " from TB_CENSUS_ARMY_COURSE_JCO where  jco_id=:jco_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
	
		List<TB_CENSUS_ARMY_COURSE_JCO> list=  (List<TB_CENSUS_ARMY_COURSE_JCO>) query.list();
		tx.commit();
		
		return list;
		
		} catch (Exception e) {
		
			return null;
		} finally {
			sessionHQL.close();
			
		}
	}
	
	public String Update_Army_Course(TB_CENSUS_ARMY_COURSE_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			String hql = "update TB_CENSUS_ARMY_COURSE_JCO set modify_by=:modified_by,modify_on=:modified_date,status=:status  "
					+ " where  jco_id=:jco_id and status = '0'";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModify_on()).setInteger("status", 1)
					.setInteger("jco_id", obj.getJco_id());

			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg1;
	}
	
	@RequestMapping(value = "/admin/getArmyCourse_JCOReject", method = RequestMethod.POST)
	public @ResponseBody String getArmyCourse_Reject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

        String reject_remarks = request.getParameter("reject_remarks");

        

		String username = session.getAttribute("username").toString();
		TB_CENSUS_ARMY_COURSE_JCO ChangeArmy = new TB_CENSUS_ARMY_COURSE_JCO();
		
		ChangeArmy.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		ChangeArmy.setReject_remarks(reject_remarks);
		String msg1 = Update_Change_of_ArmyCourseReject(ChangeArmy, username);

		return msg1;

	}

	public String Update_Change_of_ArmyCourseReject(TB_CENSUS_ARMY_COURSE_JCO ChangeArmy, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {


			String hql = "update TB_CENSUS_ARMY_COURSE_JCO set modify_by=:modify_by,modify_on=:modify_on,status=:status,reject_remarks=:reject_remarks  "
					+ " where  jco_id=:jco_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modify_by", username)
					.setTimestamp("modify_on", new Date()).setInteger("status", 3).setString("reject_remarks", ChangeArmy.getReject_remarks())

					.setInteger("jco_id", ChangeArmy.getJco_id());

			msg = query.executeUpdate() > 0 ? "1" : "0 ";

			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	public @ResponseBody List<TB_CENSUS_ARMY_COURSE_JCO> getChangeOfArmyCourse2( int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_ARMY_COURSE_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_ARMY_COURSE_JCO> list = (List<TB_CENSUS_ARMY_COURSE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_ArmyCourseJCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_ARMY_COURSE_JCO> get_ArmyCourse3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
	
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CENSUS_ARMY_COURSE_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_ARMY_COURSE_JCO> list = (List<TB_CENSUS_ARMY_COURSE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

}

