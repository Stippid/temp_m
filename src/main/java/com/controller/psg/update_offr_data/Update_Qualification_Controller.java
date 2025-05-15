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
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Transaction.TB_CENSUS_QUALIFICATION;
import com.models.psg.update_census_data.TB_CENSUS_ARMY_COURSE;
import com.models.psg.update_census_data.TB_CENSUS_PROMOTIONAL_EXAM;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Update_Qualification_Controller {
	
	ValidationController valid = new ValidationController();

	Psg_CommonController com = new Psg_CommonController();

	@RequestMapping(value = "/admin/update_offr_promo_exam_detail_action", method = RequestMethod.POST)
	public @ResponseBody String update_offr_promo_exam_detail_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date promo_exam_dt = null;
		String username = session.getAttribute("username").toString();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String promo_exam = request.getParameter("promo_exam");
		
		String exam_other_select = request.getParameter("exam_other_select");
		String promo_exam_dop = request.getParameter("promo_exam_dop");
		String promo_exam_ch_id = request.getParameter("promo_exam_ch_id");
		String p_id = request.getParameter("p_id");
		BigInteger com_id = new BigInteger(request.getParameter("com_id"));
		String promo_exam_authority = request.getParameter("promo_exam_authority");
		String promo_exam_doa = request.getParameter("promo_exam_doa");
		String e_ot = request.getParameter("e_ot");
		String msg = "";

		if (promo_exam_authority == null || promo_exam_authority.equals("")) {
			return "Please Enter Authority";
		}
		if (!valid.isValidAuth(promo_exam_authority)) {
			return valid.isValidAuthMSG + " Authority";
		}
		if (!valid.isvalidLength(promo_exam_authority, valid.authorityMax, valid.authorityMin)) {
			return "Authority " + valid.isValidLengthMSG;
		}
		if (promo_exam_doa == null || promo_exam_doa.equals("null") || promo_exam_doa.equals("DD/MM/YYYY")
				|| promo_exam_doa.equals("")) {
			return "Please Select Date of Authority";
		}
		if (!valid.isValidDate(promo_exam_doa)) {
			return valid.isValidDateMSG + " of Authority";
		}
		if (!promo_exam_doa.equals("") && !promo_exam_doa.equals("DD/MM/YYYY")) {
			promo_exam_dt = format.parse(promo_exam_doa);
		}
		if (promo_exam == null || promo_exam.equals("0")) {
			return "Please Enter Exam";
		}
		if (e_ot != null && !e_ot.trim().equals("")) {
			if (!valid.isOnlyAlphabet(e_ot)) {
				return " Exam Other " + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(e_ot, valid.nameMax, valid.nameMin)) {
				return " Exam Other " + valid.isValidLengthMSG;
			}
		}
		if (promo_exam_dop == null || promo_exam_dop.equals("")) {
			return "Please Enter Date of Passing";
		}
		/*try {*/
			if (exam_other_select != null && exam_other_select.equals("OTHERS") && !e_ot.equals("")) {

				Query q0 = sessionhql.createQuery(
						"select count(id) from TB_CENSUS_PROMOTIONAL_EXAM where upper(exam_other)=:exam_other and comm_id=:comm_id and id!=:id and status!=-1")
						.setParameter("exam_other", e_ot.toUpperCase())
						.setParameter("id", Integer.parseInt(promo_exam_ch_id))
						.setParameter("comm_id", com_id);
				Long c = (Long) q0.uniqueResult();
				if (c > 0) {
					return "Data For This Exam Already Entered";
				}
			} else {
				Query q0 = sessionhql.createQuery(
						"select count(id) from TB_CENSUS_PROMOTIONAL_EXAM where exam=:exam and comm_id=:comm_id and  id!=:id and status!=-1")
						.setParameter("exam", promo_exam).setParameter("id", Integer.parseInt(promo_exam_ch_id))
						.setParameter("comm_id", com_id);
				Long c = (Long) q0.uniqueResult();
				if (c > 0) {
					return "Data For This Exam Already Entered";
				}
			}
			if (Integer.parseInt(promo_exam_ch_id) == 0) {

				TB_CENSUS_PROMOTIONAL_EXAM promo_ = new TB_CENSUS_PROMOTIONAL_EXAM();
				promo_.setCensus_id(Integer.parseInt(p_id));
				promo_.setComm_id(com_id);
				promo_.setExam(promo_exam);
				promo_.setDate_of_passing(promo_exam_dop);
				promo_.setStatus(0);
				promo_.setAuthority(promo_exam_authority);
				promo_.setDate_of_authority(promo_exam_dt);
				promo_.setCreated_by(username);
				promo_.setCreated_on(date);
				promo_.setExam_other(e_ot);
				int id = (int) sessionhql.save(promo_);
				msg = Integer.toString(id);
			} else {

				String hql = "update TB_CENSUS_PROMOTIONAL_EXAM set modify_by=:modify_by ,modify_on=:modify_on ,exam=:exam,date_of_passing=:date_of_passing, "
						+ " authority=:authority,date_of_authority=:date_of_authority,exam_other=:exam_other,status=0  where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("exam", promo_exam)
						.setString("date_of_passing", promo_exam_dop)
						.setInteger("id", Integer.parseInt(promo_exam_ch_id)).setString("modify_by", username)
						.setTimestamp("modify_on", date).setTimestamp("date_of_authority", promo_exam_dt)
						.setString("authority", promo_exam_authority).setString("exam_other", e_ot);
				msg = query.executeUpdate() > 0 ? "update" : "0";
			}

			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			} else {
				com.update_offr_status(Integer.parseInt(p_id), username);
			}

			tx.commit();
		/*} catch (RuntimeException e) {
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
		}*/
			sessionhql.close();
		return msg;
	}

	@RequestMapping(value = "/admin/update_offr_army_course_detail_action", method = RequestMethod.POST)
	public @ResponseBody String update_offr_army_course_detail_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date army_start_date = null;
		Date army_date_of_completion = null;
		Date army_course_date_auth = null;
		String course_type_ot = null;
		String course_institue_ot = null;
		String ar_course_div_ot = null;
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
		String p_id = request.getParameter("p_id");
		String com_id = request.getParameter("com_id");
		String army_course_authority = request.getParameter("army_course_authority");
		String army_course_doa = request.getParameter("army_course_doa");

		String msg = "";
		if (army_course_authority == null || army_course_authority.equals("")) {
			return "Please Enter Authority";
		}
		if (!valid.isValidAuth(army_course_authority)) {
			return valid.isValidAuthMSG + " Authority";
		}
		if (!valid.isvalidLength(army_course_authority, valid.authorityMax, valid.authorityMin)) {
			return "Authority " + valid.isValidLengthMSG;
		}
		if (army_course_doa == null || army_course_doa.equals("null") || army_course_doa.equals("DD/MM/YYYY")
				|| army_course_doa.equals("")) {
			return "Please Enter Date of Authority";
		}
		if (!valid.isValidDate(army_course_doa)) {
			return valid.isValidDateMSG + " of Authority";
		}
		if (!army_course_doa.equals("") && !army_course_doa.equals("DD/MM/YYYY")) {
			army_course_date_auth = format.parse(army_course_doa);
		}
		if (army_course_name == null || army_course_name.equals("")) {
			return "Please Enter Course Name";
		}
		if (army_course_abbr == null || army_course_abbr.equals("")) {
			return "Please Enter Course Abbreviation";
		}
		if (army_course_institue == null || army_course_institue.equals("0")) {
			return "Please Enter Course Institute";
		}
		if (army_course_institue_ot != null && !army_course_institue_ot.trim().equals("")) {
			if (!valid.isOnlyAlphabet(army_course_institue_ot)) {
				return " Course Institute Other " + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(army_course_institue_ot, valid.nameMax, valid.nameMin)) {
				return " Course Institute Other " + valid.isValidLengthMSG;
			}
		}
		if (army_course_div_grade == null || army_course_div_grade.equals("0")) {
			return "Please Select Div/Grade";
		}
		if (request.getParameter("ar_course_div_ot") != null
				&& !request.getParameter("ar_course_div_ot").trim().equals("")) {
			if (!valid.isOnlyAlphabet(request.getParameter("ar_course_div_ot"))) {
				return " Div/Grade Other " + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(request.getParameter("ar_course_div_ot"), valid.nameMax,
					valid.nameMin)) {
				return " Div/Grade Other " + valid.isValidLengthMSG;
			}
		}
		if (army_course_type == null || army_course_type.equals("0")) {
			return "Please Select Course Type";
		}
		if (army_course_start_date == null || army_course_start_date.equals("null")
				|| army_course_start_date.equals("DD/MM/YYYY") || army_course_start_date.equals("")) {
			return "Please Enter Start Date";
		}
		if (!valid.isValidDate(army_course_start_date)) {
			return valid.isValidDateMSG + " of Start";
		}
		if (!army_course_start_date.equals("") && !army_course_start_date.equals("DD/MM/YYYY")) {
			army_start_date = format.parse(army_course_start_date);
		}
		if (army_course_date_of_completion == null || army_course_date_of_completion.equals("null")
				|| army_course_date_of_completion.equals("DD/MM/YYYY") || army_course_date_of_completion.equals("")) {
			return "Please Enter Date of Completion";
		}
		if (!valid.isValidDate(army_course_date_of_completion)) {
			return valid.isValidDateMSG + " of Completion";
		}
		if (!army_course_date_of_completion.equals("") && !army_course_date_of_completion.equals("DD/MM/YYYY")) {
			army_date_of_completion = format.parse(army_course_date_of_completion);
		}

		if (request.getParameter("ar_course_div_ot") != null && !request.getParameter("ar_course_div_ot").equals("")) {
			ar_course_div_ot = request.getParameter("ar_course_div_ot");
		}
		if (army_course_institue_ot != null && !army_course_institue_ot.trim().equals("")) {
			course_institue_ot = army_course_institue_ot;
		}

		army_course_name = army_course_name.replace("&#40;", "(");

		army_course_name = army_course_name.replace("&#41;", ")");

		army_course_abbr = army_course_abbr.replace("&#40;", "(");

		army_course_abbr = army_course_abbr.replace("&#41;", ")");

		try {
			if (Integer.parseInt(army_course_ch_id) == 0) {
				TB_CENSUS_ARMY_COURSE army_c = new TB_CENSUS_ARMY_COURSE();
				army_c.setCensus_id(Integer.parseInt(p_id));
				army_c.setComm_id(new BigInteger(com_id));
				army_c.setCourse_name(army_course_name);
				army_c.setDiv_grade(army_course_div_grade);
				army_c.setCourse_type(army_course_type);
				army_c.setCourse_abbreviation(army_course_abbr);
				army_c.setCourse_institute(Integer.parseInt(army_course_institue));
				army_c.setCourse_institute_other(course_institue_ot);
				army_c.setStart_date(army_start_date);
				army_c.setDate_of_completion(army_date_of_completion);
				army_c.setStatus(0);
				army_c.setAuthority(army_course_authority);
				army_c.setDate_of_authority(army_course_date_auth);
				army_c.setCreated_by(username);
				army_c.setCreated_on(date);
				army_c.setAr_course_div_ot(ar_course_div_ot);
				army_c.setCourse_type_ot(course_type_ot);
				int id = (int) sessionhql.save(army_c);
				msg = Integer.toString(id);
			} else {
				String hql = "update TB_CENSUS_ARMY_COURSE set modify_by=:modify_by ,modify_on=:modify_on ,course_name=:course_name,div_grade=:div_grade, "
						+ " ar_course_div_ot=:ar_course_div_ot,course_abbreviation=:course_abbreviation,course_institute=:course_institute,course_institute_other=:course_institute_other,course_type=:course_type,start_date=:start_date,date_of_completion=:date_of_completion, authority=:authority,date_of_authority=:date_of_authority,status=:status  where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("course_name", army_course_name)
						.setString("div_grade", army_course_div_grade).setString("course_type", army_course_type)
						.setTimestamp("start_date", (army_start_date))
						.setTimestamp("date_of_completion", (army_date_of_completion))
						.setInteger("id", Integer.parseInt(army_course_ch_id)).setString("modify_by", username)
						.setTimestamp("modify_on", date).setString("course_institute_other", course_institue_ot)
						.setString("ar_course_div_ot", ar_course_div_ot)
						.setTimestamp("date_of_authority", army_course_date_auth)
						.setString("course_abbreviation", army_course_abbr)
						.setString("authority", army_course_authority)
						.setInteger("course_institute", Integer.parseInt(army_course_institue))

						.setInteger("status", 0);
				msg = query.executeUpdate() > 0 ? "update" : "0";
			}

			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			} else {
				com.update_offr_status(Integer.parseInt(p_id), username);
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

	@RequestMapping(value = "/admin/update_offr_promo_exam_delete_action", method = RequestMethod.POST)
	public @ResponseBody String update_offr_promo_exam_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("promo_exam_ch_id"));
		try {
			String hqlUpdate = "delete from TB_CENSUS_PROMOTIONAL_EXAM where id=:id";
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

	@RequestMapping(value = "/admin/update_offr_army_course_delete_action", method = RequestMethod.POST)
	public @ResponseBody String update_offr_army_course_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("army_course_ch_id"));
		try {
			String hqlUpdate = "delete from TB_CENSUS_ARMY_COURSE where id=:id";
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

	@RequestMapping(value = "/admin/update_offr_promo_exam_getData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_PROMOTIONAL_EXAM> update_offr_promo_exam_getData(ModelMap Mmap,
			HttpSession session, HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_PROMOTIONAL_EXAM where census_id=:census_id and comm_id=:comm_id and status=0 order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_PROMOTIONAL_EXAM> list = (List<TB_CENSUS_PROMOTIONAL_EXAM>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/update_offr_army_course_getData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_ARMY_COURSE> update_offr_army_course_getData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
//		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_ARMY_COURSE where  comm_id=:comm_id and status=0 order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_ARMY_COURSE> list = (List<TB_CENSUS_ARMY_COURSE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	/////////////////// Qualification
	
	@RequestMapping(value = "/admin/update_offr_qualification_action", method = RequestMethod.POST)
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
		String q_id = request.getParameter("q_id");
		String com_id = request.getParameter("com_id");
		String dateofBirthyear = request.getParameter("dateofBirthyear");
		String exam_other_qua = request.getParameter("exam_other_qua");
		String class_other_qua = request.getParameter("class_other_qua");
		String degree_other = request.getParameter("degree_other");
		String spec_other = request.getParameter("spec_other");
		String rvalue = "";
		
		Date qual_dt = null;
		if (exam_other_qua.equals("")) {
			exam_other_qua = null;
		}
		if (class_other_qua.equals("")) {
			class_other_qua = null;
		}
		if (degree_other.equals("")) {
			degree_other = null;
		}
		if (spec_other.equals("")) {
			spec_other = null;
		}
		
		if (qual_authority == null || qual_authority.equals("") || qual_authority == "") {
			return "Please Enter Authority";
		}
		if (!valid.isvalidLength(qual_authority, valid.authorityMax, valid.authorityMin)) {
			return " Authority " + valid.isValidLengthMSG;
		}
		if (!valid.isValidAuth(qual_authority)) {
			return valid.isValidAuthMSG + " Authority";
		}
		
		if (qual_doa == null || qual_doa.equals("null") || qual_doa.equals("DD/MM/YYYY") || qual_doa.equals("")) {
			return "Please Select Date of Authority";
		} 
		else if (!valid.isValidDate(request.getParameter("qual_doa"))) {
			return valid.isValidDateMSG + " of Authority1";
		} else {
			qual_dt = format.parse(qual_doa);
		}
		if (type == null || type.equals("0")) {
			return "Please Select Type";
		}
		if (examination_pass == null || examination_pass.equals("0")) {
			return "Please Select Examination Passed";
		}
		if (degree == null || degree.equals("0")) {
			return "Please Select Degree Acquried";
		}
		if (specialization == null || specialization.equals("0")) {
			return "Please Select Specialization";
		}
		if (spec_other != null && !spec_other.trim().equals("")) {
			if (!valid.isOnlyAlphabet(spec_other)) {
				return " Specialization Other " + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(spec_other, valid.nameMax, valid.nameMin)) {
				return "Specialization Other " + valid.isValidLengthMSG;
			}
		}
		if (passing_year == null || passing_year.trim().equals("")) {
			return "Please Enter Year of passing";
		}

		if (examination_pass.equals("others")) {
			if (exam_other_qua == null || exam_other_qua.equals("")) {
				return "Please Enter Examination Other";
			}
		}
		if (passing_year == null || passing_year.equals("")) {
			return "Please Enter Year of passing";
		}
		if (!passing_year.equals("")) {
			if (Integer.parseInt(passing_year) <= Integer.parseInt(dateofBirthyear)
					|| Integer.parseInt(passing_year) > currentYear) {
				return "Please Enter Valid Year of Passing";
			}
		}
		if (div_class == null || div_class.equals("0")) {
			return "Please Enter Div/class";
		}
		if (div_class.equals("4")) {
			if (class_other_qua == null || class_other_qua.equals("")) {
				return "Please Enter Div/Grade Other";
			}
		}
		if (subject == null || subject.equals("")) {
			return "Please Select Subjects";
		}
		if (institute == null || institute.equals("")) {
			return "Please Enter Institute & place";
		}
		TB_CENSUS_QUALIFICATION q = new TB_CENSUS_QUALIFICATION();
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
				q.setDiv_class(Integer.parseInt(div_class));
				q.setSubject(subject);
				q.setInstitute(institute);
				q.setCen_id(Integer.parseInt(q_id));
				q.setComm_id(new BigInteger(com_id));
				q.setCreated_by(username);
				q.setCreated_on(date);
				q.setExam_other(exam_other_qua);
				q.setClass_other(class_other_qua);
				q.setDegree_other(degree_other);
				q.setSpecialization_other(spec_other);
				q.setAuthority(qual_authority);
				q.setDate_of_authority(qual_dt);
				int id = (int) sessionhql.save(q);
				rvalue = Integer.toString(id);
			} else {
				String hql = "update TB_CENSUS_QUALIFICATION set modify_by=:modify_by ,modify_on=:modify_on ,type=:type,authority=:authority,date_of_authority=:date_of_authority,"
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
						.setInteger("passing_year", Integer.parseInt(passing_year))
						.setInteger("div_class", Integer.parseInt(div_class)).setString("subject", subject)
						.setString("institute", institute).setInteger("id", Integer.parseInt(qualification_ch_id))
						.setString("modify_by", username).setTimestamp("modify_on", date)
						.setString("exam_other", exam_other_qua).setString("class_other", class_other_qua)
						.setString("specialization_other", spec_other).setString("degree_other", degree_other)
						.setString("authority", qual_authority).setTimestamp("date_of_authority", qual_dt);
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
			} else {
				com.update_offr_status(Integer.parseInt(q_id), username);
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
	public @ResponseBody List<TB_CENSUS_PROMOTIONAL_EXAM> update_census_promo_examData1(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_PROMOTIONAL_EXAM where census_id=:cen_id and comm_id=:comm_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_PROMOTIONAL_EXAM> list = (List<TB_CENSUS_PROMOTIONAL_EXAM>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public @ResponseBody List<TB_CENSUS_ARMY_COURSE> update_census_army_courseData1(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			String hqlUpdate = " from TB_CENSUS_ARMY_COURSE where census_id=:cen_id and comm_id=:comm_id and status='0'";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);

			List<TB_CENSUS_ARMY_COURSE> list = (List<TB_CENSUS_ARMY_COURSE>) query.list();
			tx.commit();

			return list;

		} catch (Exception e) {

			return null;
		} finally {
			sessionHQL.close();

		}
	}

	public String Update_Prop_Exam(TB_CENSUS_PROMOTIONAL_EXAM obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			String hql = "update TB_CENSUS_PROMOTIONAL_EXAM set modify_by=:modified_by,modify_on=:modified_date,status=:status  "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0'";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModify_on()).setInteger("status", 1)
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id());

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

	public String Update_Army_Course(TB_CENSUS_ARMY_COURSE obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			String hql = "update TB_CENSUS_ARMY_COURSE set modify_by=:modified_by,modify_on=:modified_date,status=:status  "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0'";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModify_on()).setInteger("status", 1)
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id());

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

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_CENSUS_QUALIFICATION> update_census_getQualificationData1(int id, BigInteger comm_id) {
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

	public String Update_Qualification(TB_CENSUS_QUALIFICATION obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			String hql = "update TB_CENSUS_QUALIFICATION set modify_by=:modified_by,modify_on=:modified_date,status=:status  "
					+ " where cen_id=:census_id and comm_id=:comm_id and status = '0'";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModify_on()).setInteger("status", 1)
					.setInteger("census_id", obj.getCen_id()).setBigInteger("comm_id", obj.getComm_id());

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

	@RequestMapping(value = "/admin/getQualification_Reject", method = RequestMethod.POST)
	public @ResponseBody String getQualification_Reject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_CENSUS_QUALIFICATION ChangeQua = new TB_CENSUS_QUALIFICATION();
		ChangeQua.setCen_id(Integer.parseInt(request.getParameter("ch_id")));
		ChangeQua.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ChangeQua.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_Change_of_QualificationReject(ChangeQua, username);

		return msg1;

	}

	public String Update_Change_of_QualificationReject(TB_CENSUS_QUALIFICATION changeQua, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
	
		try {

			String hql = "update TB_CENSUS_QUALIFICATION set modify_by=:modify_by,modify_on=:modify_on,status=:status,reject_remarks=:reject_remarks   "
					+ " where cen_id=:cen_id and comm_id=:comm_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modify_by", username)
					.setTimestamp("modify_on", new Date()).setInteger("status", 3)
					.setString("reject_remarks", changeQua.getReject_remarks())
					.setInteger("cen_id", changeQua.getCen_id()).setBigInteger("comm_id", changeQua.getComm_id());

			msg = query.executeUpdate() > 0 ? "1" : "0";

			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	public @ResponseBody List<TB_CENSUS_QUALIFICATION> getChangeOfQualification2(int id, BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_QUALIFICATION where cen_id=:cen_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_QUALIFICATION> list = (List<TB_CENSUS_QUALIFICATION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_Qualification3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_QUALIFICATION> get_Qualification3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("q_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_QUALIFICATION where cen_id=:cen_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_QUALIFICATION> list = (List<TB_CENSUS_QUALIFICATION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	/*--------------------- For REJECT QUALIFICATION  END----------------------------------*/
	/*--------------------- For REJECT Promotion Exam----------------------------------*/

	@RequestMapping(value = "/admin/getPromotionExamReject", method = RequestMethod.POST)
	public @ResponseBody String getPromotionExamReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_CENSUS_PROMOTIONAL_EXAM ChangeProExam = new TB_CENSUS_PROMOTIONAL_EXAM();
		ChangeProExam.setCensus_id(Integer.parseInt(request.getParameter("ch_id")));
		ChangeProExam.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ChangeProExam.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_Change_of_PromoExamReject(ChangeProExam, username);

		return msg1;

	}

	public String Update_Change_of_PromoExamReject(TB_CENSUS_PROMOTIONAL_EXAM changeQua, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {

			String hql = "update TB_CENSUS_PROMOTIONAL_EXAM set modify_by=:modify_by,modify_on=:modify_on,status=:status,reject_remarks=:reject_remarks   "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modify_by", username)
					.setTimestamp("modify_on", new Date()).setInteger("status", 3)
					.setString("reject_remarks", changeQua.getReject_remarks())
					.setInteger("census_id", changeQua.getCensus_id()).setBigInteger("comm_id", changeQua.getComm_id());

			msg = query.executeUpdate() > 0 ? "1" : "0";

			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	public @ResponseBody List<TB_CENSUS_PROMOTIONAL_EXAM> getChangeOfPromotionExam2(int id, BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_PROMOTIONAL_EXAM where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_PROMOTIONAL_EXAM> list = (List<TB_CENSUS_PROMOTIONAL_EXAM>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_PromotionExam3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_PROMOTIONAL_EXAM> get_PromotionExam3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_PROMOTIONAL_EXAM where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_PROMOTIONAL_EXAM> list = (List<TB_CENSUS_PROMOTIONAL_EXAM>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	/*--------------------- For REJECT Promotion Exam  END----------------------------------*/
	/*--------------------- For REJECT Army Course----------------------------------*/

	@RequestMapping(value = "/admin/getArmyCourse_Reject", method = RequestMethod.POST)
	public @ResponseBody String getArmyCourse_Reject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_CENSUS_ARMY_COURSE ChangeArmy = new TB_CENSUS_ARMY_COURSE();
		//ChangeArmy.setCensus_id(Integer.parseInt(request.getParameter("ch_id")));
		ChangeArmy.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ChangeArmy.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_Change_of_ArmyCourseReject(ChangeArmy, username);

		return msg1;

	}

	public String Update_Change_of_ArmyCourseReject(TB_CENSUS_ARMY_COURSE ChangeArmy, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {

			String hql = "update TB_CENSUS_ARMY_COURSE set modify_by=:modify_by,modify_on=:modify_on,status=:status,reject_remarks=:reject_remarks   "
					+ " where comm_id=:comm_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modify_by", username)
					.setTimestamp("modify_on", new Date()).setInteger("status", 3)
					.setString("reject_remarks", ChangeArmy.getReject_remarks())
					.setBigInteger("comm_id", ChangeArmy.getComm_id());

			msg = query.executeUpdate() > 0 ? "1" : "0";

			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	public @ResponseBody List<TB_CENSUS_ARMY_COURSE> getChangeOfArmyCourse2(int id, BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_ARMY_COURSE where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_ARMY_COURSE> list = (List<TB_CENSUS_ARMY_COURSE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_ArmyCourse3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_ARMY_COURSE> get_ArmyCourse3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_ARMY_COURSE where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_ARMY_COURSE> list = (List<TB_CENSUS_ARMY_COURSE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	/*--------------------- For REJECT Army Course  END----------------------------------*/

}
