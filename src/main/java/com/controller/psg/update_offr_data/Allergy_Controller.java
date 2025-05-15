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
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_CENSUS_LANGUAGE;
import com.models.psg.Transaction.TB_PSG_CENSUS_ALLERGICTOMEDICINE;
import com.models.psg.update_census_data.TB_CENSUS_ARMY_COURSE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Allergy_Controller {

	private Psg_CommonController com = new Psg_CommonController();
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/admin/update_offr_allergic_detail_action", method = RequestMethod.POST)
	public @ResponseBody String update_offr_allergic_detail_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String allergic = request.getParameter("allergic");
		String allergic_ch_id = request.getParameter("allergic_ch_id");
		String p_id = request.getParameter("p_id");
		String com_id = request.getParameter("com_id");

		String msg = "";
		if (allergic == null || allergic.equals("")) {
			return "Please Enter the Allergy";
		}
		if (allergic != null && !allergic.trim().equals("")) {
			if (!validation.isOnlyAlphabetNumeric(allergic)) {
				return " Allergy " + validation.isOnlyAlphabetNumericMSG;
			}
		    if (!validation.isvalidLength(allergic, validation.remarkMax, validation.remarkMin)) {
		    	return " Allergy " + validation.isValidLengthMSG;
			}
		} 
		try {
			if (Integer.parseInt(allergic_ch_id) == 0) {
				TB_PSG_CENSUS_ALLERGICTOMEDICINE aller = new TB_PSG_CENSUS_ALLERGICTOMEDICINE();
				aller.setCen_id(Integer.parseInt(p_id));
				aller.setComm_id(new BigInteger(com_id));
				aller.setMedicine(allergic);
				aller.setStatus(0);
				aller.setCreated_by(username);
				aller.setCreated_date(date);
				int id = (int) sessionhql.save(aller);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_PSG_CENSUS_ALLERGICTOMEDICINE set modified_by=:modified_by ,status=:status,modified_date=:modified_date ,medicine=:medicine  where  id=:id";
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
				com.update_offr_status(Integer.parseInt(p_id),username);
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

	@RequestMapping(value = "/admin/update_offr_allergic_delete_action", method = RequestMethod.POST)
	public @ResponseBody String update_offr_allergic_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("allergic_ch_id"));
		try {
			String hqlUpdate = "delete from TB_PSG_CENSUS_ALLERGICTOMEDICINE where id=:id";
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

	@RequestMapping(value = "/admin/update_offr_allergic_getData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_LANGUAGE> update_offr_allergic_getData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =  new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_PSG_CENSUS_ALLERGICTOMEDICINE where cen_id=:cen_id and comm_id=:comm_id and status=0 order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_LANGUAGE> list = (List<TB_CENSUS_LANGUAGE>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	/*--------------------- For Approval ----------------------------------*/
	public @ResponseBody List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> update_census_allergicData1(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_CENSUS_ALLERGICTOMEDICINE where cen_id=:cen_id and comm_id=:comm_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> list = (List<TB_PSG_CENSUS_ALLERGICTOMEDICINE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Alergy(TB_PSG_CENSUS_ALLERGICTOMEDICINE obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		// String msg1= "";
		try {

			String hql = "update TB_PSG_CENSUS_ALLERGICTOMEDICINE set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where cen_id=:cen_id and comm_id=:comm_id and status = '0'";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("cen_id", obj.getCen_id()).setBigInteger("comm_id", obj.getComm_id());

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

	@RequestMapping(value = "/admin/getAllergyReject", method = RequestMethod.POST)
	public @ResponseBody String getAllergyReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_PSG_CENSUS_ALLERGICTOMEDICINE ChangeAll = new TB_PSG_CENSUS_ALLERGICTOMEDICINE();
		ChangeAll.setCen_id(Integer.parseInt(request.getParameter("ch_id")));
		ChangeAll.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ChangeAll.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_Change_of_AllergyReject(ChangeAll, username);

		return msg1;

	}

	public String Update_Change_of_AllergyReject(TB_PSG_CENSUS_ALLERGICTOMEDICINE obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {
//			String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//
//			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
//					.setTimestamp("modified_date", new Date()).setInteger("update_ofr_status", 3)
//					.setInteger("census_id", obj.getCen_id()).setInteger("comm_id", obj.getComm_id());
//
//			msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected. ";

			String hql = "update TB_PSG_CENSUS_ALLERGICTOMEDICINE set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks   "
					+ " where cen_id=:cen_id and comm_id=:comm_id and status = '0'  ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())
					.setInteger("cen_id", obj.getCen_id()).setBigInteger("comm_id", obj.getComm_id());

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

	public @ResponseBody List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> getChangeOfAllergy2(int id, BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_CENSUS_ALLERGICTOMEDICINE where cen_id=:cen_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> list = (List<TB_PSG_CENSUS_ALLERGICTOMEDICINE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_Allergy3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> get_Allergy3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		/// bisag 241122 V2 (change comm_id int to big int)
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_PSG_CENSUS_ALLERGICTOMEDICINE where cen_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> list = (List<TB_PSG_CENSUS_ALLERGICTOMEDICINE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
}
