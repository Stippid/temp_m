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
import com.models.psg.Jco_Update_JcoData.TB_ALLERGIC_TO_MEDICINE_JCO;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_CENSUS_LANGUAGE;
import com.models.psg.update_census_data.TB_CENSUS_ARMY_COURSE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Allergy_Jco_Controller {

	private Psg_CommonController com = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	ValidationController valid = new ValidationController();
	@RequestMapping(value = "/admin/update_offr_allergic_detail_action_jco", method = RequestMethod.POST)
	public @ResponseBody String update_offr_allergic_detail_action_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String allergic = request.getParameter("allergic");
		String allergic_ch_id = request.getParameter("allergic_ch_id");
		String jco_id = request.getParameter("jco_id");

		String msg = "";
		if (allergic == null || allergic.equals("")) {
			return "Please Enter the Allergy";
		}
		  if (!valid.isOnlyAlphabet(allergic)) {
				return " Allergy " + valid.isOnlyAlphabetMSG ;
			}
	  
		 if (!valid.isvalidLength(allergic, valid.authorityMax, valid.authorityMin)) {
	            return " Allergy " + valid.isValidLengthMSG;
	       
	         }
		try {
			if (Integer.parseInt(allergic_ch_id) == 0) {
				TB_ALLERGIC_TO_MEDICINE_JCO aller = new TB_ALLERGIC_TO_MEDICINE_JCO();
				aller.setJco_id(Integer.parseInt(jco_id));
				aller.setMedicine(allergic);
				aller.setStatus(0);
				aller.setCreated_by(username);
				aller.setCreated_date(date);
				aller.setInitiated_from("u");
				int id = (int) sessionhql.save(aller);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_ALLERGIC_TO_MEDICINE_JCO set modified_by=:modified_by ,status=:status,modified_date=:modified_date ,medicine=:medicine  where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("medicine", allergic).setInteger("status", 0)
						.setInteger("id", Integer.parseInt(allergic_ch_id)).setString("modified_by", username)
						.setTimestamp("modified_date", date);
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

	@RequestMapping(value = "/admin/update_offr_allergic_delete_action_jco", method = RequestMethod.POST)
	public @ResponseBody String update_offr_allergic_delete_action_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("allergic_ch_id"));
		try {
			String hqlUpdate = "delete from TB_ALLERGIC_TO_MEDICINE_JCO where id=:id";
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

	@RequestMapping(value = "/admin/update_offr_allergic_getData_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_LANGUAGE> update_offr_allergic_getData_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_ALLERGIC_TO_MEDICINE_JCO where  jco_id=:jco_id and status=0 order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CENSUS_LANGUAGE> list = (List<TB_CENSUS_LANGUAGE>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	/*--------------------- For Approval ----------------------------------*/
	public @ResponseBody List<TB_ALLERGIC_TO_MEDICINE_JCO> update_census_allergicData1( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_ALLERGIC_TO_MEDICINE_JCO where  jco_id=:jco_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_ALLERGIC_TO_MEDICINE_JCO> list = (List<TB_ALLERGIC_TO_MEDICINE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Alergy(TB_ALLERGIC_TO_MEDICINE_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		// String msg1= "";
		try {

			String hql = "update TB_ALLERGIC_TO_MEDICINE_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  jco_id=:jco_id and status = '0'";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
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

	/*--------------------- For REJECT ----------------------------------*/

	@RequestMapping(value = "/admin/getAllergyReject_jco", method = RequestMethod.POST)
	public @ResponseBody String getAllergyReject_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String reject_remarks = request.getParameter("reject_remarks");

		

		String username = session.getAttribute("username").toString();
		TB_ALLERGIC_TO_MEDICINE_JCO ChangeAll = new TB_ALLERGIC_TO_MEDICINE_JCO();
		ChangeAll.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		ChangeAll.setReject_remarks(reject_remarks);
		String msg1 = Update_Change_of_AllergyReject(ChangeAll, username);

		return msg1;

	}

	public String Update_Change_of_AllergyReject(TB_ALLERGIC_TO_MEDICINE_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {


			String hql = "update TB_ALLERGIC_TO_MEDICINE_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " ,reject_remarks=:reject_remarks where  jco_id=:jco_id and status = '0'  ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())
					.setInteger("jco_id", obj.getJco_id());

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

	public @ResponseBody List<TB_ALLERGIC_TO_MEDICINE_JCO> getChangeOfAllergy2( int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_ALLERGIC_TO_MEDICINE_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_ALLERGIC_TO_MEDICINE_JCO> list = (List<TB_ALLERGIC_TO_MEDICINE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_Allergy3_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_ALLERGIC_TO_MEDICINE_JCO> get_Allergy3_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_ALLERGIC_TO_MEDICINE_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_ALLERGIC_TO_MEDICINE_JCO> list = (List<TB_ALLERGIC_TO_MEDICINE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
}
