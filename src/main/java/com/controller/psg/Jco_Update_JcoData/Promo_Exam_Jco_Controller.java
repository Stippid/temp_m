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
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_PROMO_EXAM_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Promo_Exam_Jco_Controller {

	Psg_CommonController com = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();

	@RequestMapping(value = "/admin/update_offr_promo_exam_detail_action_jco", method = RequestMethod.POST)
	public @ResponseBody String update_offr_promo_exam_detail_action_jco(ModelMap Mmap, HttpSession session,
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
		String jco_id = request.getParameter("jco_id");
		String promo_exam_authority = request.getParameter("promo_exam_authority");
		String promo_exam_doa = request.getParameter("promo_exam_doa");
		String e_ot = request.getParameter("e_ot");
		String msg = "";

		ValidationController validation = new ValidationController();

		if (promo_exam_dop == null || promo_exam_dop.equals("")) {
			return "Please Enter the Date of Passing";
		}
		if (promo_exam_authority == null || promo_exam_authority.equals("")) {
			return "Please Enter Authority";
		}
		 if (!validation.isValidAuth(promo_exam_authority)) {
		 		return validation.isValidAuthMSG + " Authority";	
		}
	 		if (!validation.isvalidLength(promo_exam_authority,validation.authorityMax,validation.authorityMin)) {
	 			return "Authority " + validation.isValidLengthMSG;	
			}
		if (promo_exam_doa == null || promo_exam_doa.equals("null") || promo_exam_doa.equals("DD/MM/YYYY") || promo_exam_doa.equals("")) {
			return "Please Select Date of Authority";
		}
		else if (!validation.isValidDate(promo_exam_doa)) {
	 			return validation.isValidDateMSG + " of Authority";	
			}
		else
		{
			promo_exam_dt = format.parse(promo_exam_doa);
		}
		try {
			if(exam_other_select!=null && exam_other_select.equals("OTHERS") && !e_ot.equals("")) {
				
				 Query q0 = sessionhql.createQuery("select count(id) from TB_CENSUS_PROMO_EXAM_JCO where upper(exam_other)=:exam_other and jco_id=:jco_id and id!=:id and status!=-1").setParameter("exam_other", e_ot.toUpperCase())
						 .setParameter("id", Integer.parseInt(promo_exam_ch_id)).setParameter("jco_id", Integer.parseInt(jco_id));
					Long c = (Long) q0.uniqueResult();
					if(c>0) {
						return "Data For This Exam Already Entered";
					}
		}
		else {
			 Query q0 = sessionhql.createQuery("select count(id) from TB_CENSUS_PROMO_EXAM_JCO where exam=:exam and jco_id=:jco_id and  id!=:id and status!=-1").setParameter("exam", promo_exam)
					 .setParameter("id", Integer.parseInt(promo_exam_ch_id)).setParameter("jco_id", Integer.parseInt(jco_id));
				Long c = (Long) q0.uniqueResult();
				if(c>0) {
					return "Data For This Exam Already Entered";
				}
		}
			if (Integer.parseInt(promo_exam_ch_id) == 0) {
				
				
				TB_CENSUS_PROMO_EXAM_JCO promo_ = new TB_CENSUS_PROMO_EXAM_JCO();
				promo_.setJco_id(Integer.parseInt(jco_id));
				promo_.setExam(promo_exam);
				promo_.setDate_of_passing(promo_exam_dop);
				promo_.setStatus(0);
				promo_.setAuthority(promo_exam_authority);
				promo_.setDate_of_authority(promo_exam_dt);
				promo_.setCreated_by(username);
				promo_.setCreated_on(date);
				promo_.setExam_other(e_ot);
				promo_.setInitiated_from("u");
				int id = (int) sessionhql.save(promo_);
				msg = Integer.toString(id);
			} else {
				
				
				String hql = "update TB_CENSUS_PROMO_EXAM_JCO set modify_by=:modify_by ,modify_on=:modify_on ,exam=:exam,date_of_passing=:date_of_passing, "
						+ " authority=:authority,date_of_authority=:date_of_authority,exam_other=:exam_other,status=0  where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("exam", promo_exam)
						.setString("date_of_passing", promo_exam_dop)
						.setInteger("id", Integer.parseInt(promo_exam_ch_id)).setString("modify_by", username)
						.setTimestamp("modify_on", date)
						.setTimestamp("date_of_authority", promo_exam_dt)
						.setString("authority", promo_exam_authority).setString("exam_other", e_ot);
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


	

	@RequestMapping(value = "/admin/update_offr_promo_exam_delete_action_jco", method = RequestMethod.POST)
	public @ResponseBody String update_offr_promo_exam_delete_action_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("promo_exam_ch_id"));
		try {
			String hqlUpdate = "delete from TB_CENSUS_PROMO_EXAM_JCO where id=:id";
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


	@RequestMapping(value = "/admin/update_offr_promo_exam_getData_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_PROMO_EXAM_JCO> update_offr_promo_exam_getData_jco(ModelMap Mmap,
			HttpSession session, HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CENSUS_PROMO_EXAM_JCO where jco_id=:jco_id and status=0 order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CENSUS_PROMO_EXAM_JCO> list = (List<TB_CENSUS_PROMO_EXAM_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
	
		return list;
	}


	/*--------------------- For Approval ----------------------------------*/
	public @ResponseBody List<TB_CENSUS_PROMO_EXAM_JCO> update_census_promo_examData1( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_PROMO_EXAM_JCO where jco_id=:jco_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_PROMO_EXAM_JCO> list = (List<TB_CENSUS_PROMO_EXAM_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}


	public String Update_Prop_Exam(TB_CENSUS_PROMO_EXAM_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			String hql = "update TB_CENSUS_PROMO_EXAM_JCO set modify_by=:modified_by,modify_on=:modified_date,status=:status  "
					+ " where jco_id=:jco_id and status = '0'";

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


	/*--------------------- For Approval ----------------------------------*/


	/*--------------------- For REJECT Promotion Exam----------------------------------*/

	@RequestMapping(value = "/admin/getPromotionExamReject_jco", method = RequestMethod.POST)
	public @ResponseBody String getPromotionExamReject_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String reject_remarks = request.getParameter("reject_remarks");

		
		String username = session.getAttribute("username").toString();
		TB_CENSUS_PROMO_EXAM_JCO ChangeProExam = new TB_CENSUS_PROMO_EXAM_JCO();
		ChangeProExam.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		ChangeProExam.setReject_remarks(reject_remarks);
		String msg1 = Update_Change_of_PromoExamReject(ChangeProExam, username);

		return msg1;

	}

	public String Update_Change_of_PromoExamReject(TB_CENSUS_PROMO_EXAM_JCO changeQua, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {


			String hql = "update TB_CENSUS_PROMO_EXAM_JCO set modify_by=:modify_by,modify_on=:modify_on,status=:status,reject_remarks=:reject_remarks  "
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

	public @ResponseBody List<TB_CENSUS_PROMO_EXAM_JCO> getChangeOfPromotionExam2( int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_PROMO_EXAM_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_PROMO_EXAM_JCO> list = (List<TB_CENSUS_PROMO_EXAM_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_PromotionExam3_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_PROMO_EXAM_JCO> get_PromotionExam3_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CENSUS_PROMO_EXAM_JCO where   status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_PROMO_EXAM_JCO> list = (List<TB_CENSUS_PROMO_EXAM_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	/*--------------------- For REJECT Promotion Exam  END----------------------------------*/

}
