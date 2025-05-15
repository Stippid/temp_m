package com.controller.psg.Jco_Update_JcoData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.psg.Jco_Update_JcoData.Search_UpdatedJcoOr_DataDao;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_QUALIFICATION_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_SPOUSE_QUALIFICATION_JCO;
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Qualification_JCOController {

	Psg_CommonController mcommon = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	@Autowired
	Search_UpdatedJcoOr_DataDao UJD;

	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/admin/update_offr_qualification_JCOaction", method = RequestMethod.POST)
	public @ResponseBody String qualification_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		int currentYear = date.getYear() + 1900;
		String username = session.getAttribute("username").toString();
		String type = request.getParameter("type");
		String qual_authority = request.getParameter("qual_authority");
		String qual_doa = request.getParameter("qual_doa");
		String examination_pass = request.getParameter("examination_pass");
		String passing_year = request.getParameter("passing_year");
		String div_class = request.getParameter("div_class");
		String subject = request.getParameter("subject");
		String institute = request.getParameter("institute");
		String degree = request.getParameter("degree");
		String specialization = request.getParameter("specialization");
		String qualification_ch_id = request.getParameter("qualification_ch_id");
	
		String jco_id = request.getParameter("jco_id");
		String dateofBirthyear = request.getParameter("dateofBirthyear");
		  String 	exam_other_qua = request.getParameter("exam_other_qua");
		  String 	class_other_qua = request.getParameter("class_other_qua");  
		  String 	degree_other = request.getParameter("degree_other");
		  String 	spec_other = request.getParameter("spec_other");
		String rvalue = "";
		Date qual_dt=null;
		if(exam_other_qua.equals("")) {
			exam_other_qua=null;
		}
		if(class_other_qua.equals("")) {
			class_other_qua=null;
		}
		if(degree_other.equals("")) {
			degree_other=null;
		}
		if(spec_other.equals("")) {
			spec_other=null;
		}
		
		if (qual_authority == null || qual_authority.equals("") || qual_authority == "") {
			return "Please Enter Authority";
		}
		if (!validation.isValidAuth(qual_authority)) {
	 		return validation.isValidAuthMSG + " Authority";	
		}
 		if (!validation.isvalidLength(qual_authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority " + validation.isValidLengthMSG;	
		}
		if (qual_doa == null || qual_doa.equals("null") || qual_doa.equals("DD/MM/YYYY") || qual_doa.equals("")) {
			return "Please Select Date of Authority";
		}
		else if (!validation.isValidDate(qual_doa)) {
 			return validation.isValidDateMSG + " of Authority";	
		}
		else
		{
			qual_dt = format.parse(qual_doa);
		}
		
		if (type == null || type.equals("0")) {
			return "Please Select The Type";
		}
		if (examination_pass == null || examination_pass.equals("0")) {
			return "Please Select The Examination Passed";
		}
		if (degree == null || degree.equals("0")) {
			return "Please Select The Degree Acquried";
		}
		if (specialization == null || specialization.equals("0")) {
			return "Please Select The Specialization";
		}
		if (examination_pass.equals("others")) {
			if (exam_other_qua == null || exam_other_qua.equals("")) {
				return "Please Enter Examination Other";
			}
		}
		if (passing_year == null || passing_year.equals("")) {
			return "Please Enter The Year of passing";
		}
		if (!passing_year.equals("")) {
			if (Integer.parseInt(passing_year) <= Integer.parseInt(dateofBirthyear)
					|| Integer.parseInt(passing_year) > currentYear) {
				return "Please Enter The Valid Year of Passing";
			}
		}
		if (div_class == null || div_class.equals("0")) {
			return "Please Enter The Div/class";
		}
		if (div_class.equals("4")) {
			if (class_other_qua == null || class_other_qua.equals("")) {
				return "Please Enter Div/Grade Other";
			}
		}
		if (subject == null || subject.equals("")) {
			return "Please Select The Subjects";
		}
		if (institute == null || institute.equals("")) {
			return "Please Enter The Institute & place";
		}
		TB_CENSUS_QUALIFICATION_JCO q = new TB_CENSUS_QUALIFICATION_JCO();
		try {
			if (Integer.parseInt(qualification_ch_id) == 0) {
			
				q.setType(Integer.parseInt(type));
				if (examination_pass != null && !examination_pass.equals("0"))
					q.setExamination_pass(Integer.parseInt(examination_pass));
				if (degree != null && !degree.equals("0"))
					q.setDegree(Integer.parseInt(degree));
				if (specialization != null && !specialization.equals("0"))
					q.setSpecialization(Integer.parseInt(specialization));
				q.setPassing_year(Integer.parseInt(passing_year));
				q.setDiv_class(Integer.parseInt(div_class) );
				q.setSubject(subject);
				q.setInstitute(institute);
			
				q.setJco_id(Integer.parseInt(jco_id));
				q.setCreated_by(username);
				q.setCreated_on(date);
				q.setExam_other(exam_other_qua);
				q.setClass_other(class_other_qua);
				q.setDegree_other(degree_other);
				q.setSpecialization_other(spec_other);
				q.setAuthority(qual_authority);
				q.setDate_of_authority(qual_dt);
				q.setInitiated_from("u");
				int id = (int) sessionhql.save(q);
				rvalue = Integer.toString(id);
			
			} else {
				String hql = "update TB_CENSUS_QUALIFICATION_JCO set modify_by=:modify_by ,modify_on=:modify_on ,type=:type,authority=:authority,date_of_authority=:date_of_authority,"
						+ " passing_year=:passing_year,div_class=:div_class,subject=:subject,institute=:institute,exam_other=:exam_other,class_other=:class_other "
						+ ",specialization_other=:specialization_other ,degree_other=:degree_other,status=0 ";
				if (examination_pass != null && !examination_pass.equals("0"))
					hql += ",examination_pass=:examination_pass ";
				if (degree != null && !degree.equals("0"))
					hql += ",degree=:degree ";
				if (specialization != null && !specialization.equals("0"))
					hql += ",specialization=:specialization ";
				hql += " where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("type", Integer.parseInt(type))
						.setInteger("passing_year", Integer.parseInt(passing_year)).setInteger("div_class",Integer.parseInt(div_class) )
						.setString("subject", subject).setString("institute", institute)
						.setInteger("id", Integer.parseInt(qualification_ch_id)).setString("modify_by", username)
						.setTimestamp("modify_on", date).setString("exam_other", exam_other_qua).setString("class_other", class_other_qua)
						.setString("specialization_other", spec_other).setString("degree_other", degree_other).setString("authority", qual_authority).setTimestamp("date_of_authority", qual_dt);
				if (examination_pass != null && !examination_pass.equals("0"))
					query.setInteger("examination_pass", Integer.parseInt(examination_pass));
				if (degree != null && !degree.equals("0"))
					query.setInteger("degree", Integer.parseInt(degree));
				if (specialization != null && !specialization.equals("0"))
					query.setInteger("specialization", Integer.parseInt(specialization));
				rvalue = query.executeUpdate() > 0 ? "Data Updated Succesfully" : "Data Not Updated";
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
				rvalue = "Data Not Updated";
			} catch (RuntimeException rbe) {
				rvalue = "Data Not Updated";
			}
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return rvalue;
	}

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_CENSUS_QUALIFICATION_JCO> update_census_getQualificationData1( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_QUALIFICATION_JCO where  jco_id=:jco_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_QUALIFICATION_JCO> list = (List<TB_CENSUS_QUALIFICATION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Qualification(TB_CENSUS_QUALIFICATION_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			
			String hql = "update TB_CENSUS_QUALIFICATION_JCO set modify_by=:modified_by,modify_on=:modified_date,status=:status  "
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
		return msg;
	}
	/*--------------------- For REJECT QUALIFICATION ----------------------------------*/

	@RequestMapping(value = "/admin/getQualification_JCOReject", method = RequestMethod.POST)
	public @ResponseBody String getQualification_Reject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String reject_remarks = request.getParameter("reject_remarks");

		
		String username = session.getAttribute("username").toString();
		TB_CENSUS_QUALIFICATION_JCO ChangeQua = new TB_CENSUS_QUALIFICATION_JCO();
		
		ChangeQua.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		ChangeQua.setReject_remarks(reject_remarks);
		String msg1 = Update_Change_of_QualificationReject(ChangeQua, username);

		return msg1;

	}

	public String Update_Change_of_QualificationReject(TB_CENSUS_QUALIFICATION_JCO changeQua, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {
//			String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//					+ "update_ofr_status=:update_ofr_status where id=:census_id and jco_id=:jco_id and status = '1' and update_ofr_status = '0' ";
//
//			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
//					.setTimestamp("modified_date", new Date()).setInteger("update_ofr_status", 3)
//					.setInteger("census_id", changeQua.getCen_id()).setInteger("jco_id", changeQua.getJco_id());
//
//			msg1 = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected. ";

			String hql = "update TB_CENSUS_QUALIFICATION_JCO set modify_by=:modify_by,modify_on=:modify_on,status=:status,reject_remarks=:reject_remarks  "
					+ " where  jco_id=:jco_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modify_by", username)
					.setTimestamp("modify_on", new Date()).setInteger("status", 3).setString("reject_remarks", changeQua.getReject_remarks())
					.setInteger("jco_id", changeQua.getJco_id());

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

	public @ResponseBody List<TB_CENSUS_QUALIFICATION_JCO> getChangeOfQualification2( int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_QUALIFICATION_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_QUALIFICATION_JCO> list = (List<TB_CENSUS_QUALIFICATION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_QualificationJCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_QUALIFICATION_JCO> get_Qualification3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CENSUS_QUALIFICATION_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_QUALIFICATION_JCO> list = (List<TB_CENSUS_QUALIFICATION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getQualificationJCOData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> getQualificationData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("jco_id"));
		int status = 0;
		String approoval_status = request.getParameter("app_status");
		if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("1")) {
			status = 1;
		} else if (approoval_status != null && !approoval_status.trim().equals("") && approoval_status.equals("3")) {
			status = 3;
		} else {
			status = 0;
		}
		return UJD.getQualificationJCOData(id, status);
	}
	
	@RequestMapping(value = "/admin/qualification_delete_JCOaction", method = RequestMethod.POST)
	public @ResponseBody String qualification_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("qualification_ch_id"));
		try {
			String hqlUpdate = "delete from TB_CENSUS_QUALIFICATION_JCO where id=:id";
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
	
	
	/*--------------------- For REJECT QUALIFICATION  END----------------------------------*/
}
