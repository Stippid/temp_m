package com.controller.psg.Jco_Update_JcoData;

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

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_OF_APPOINTMENT_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_TA_STATUS_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class TA_Status_JCOController {
	
	private Psg_CommonController com = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	ValidationController validation = new ValidationController();

	/// bisag 241122 V2 (change comm_id int to big in)
	@RequestMapping(value = "/admin/ta_status_jco_GetData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_TA_STATUS_JCO> ta_status_jco_GetData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_CHANGE_TA_STATUS_JCO where jco_id=:jco_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CHANGE_TA_STATUS_JCO> list = (List<TB_CHANGE_TA_STATUS_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}
	
	
	@RequestMapping(value = "/admin/ta_status_jco_action", method = RequestMethod.POST)
	public @ResponseBody String ta_status_jco_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();
		String username = session.getAttribute("username").toString();
		Date dt_authority = null;
		Date dt_ta = null;
		String ta_authority = request.getParameter("ta_authority");
		String ta_authority_dateStr = request.getParameter("ta_date_of_authority");
		String ta_status = request.getParameter("ta_status");
		String date_of_ta_statusStr = request.getParameter("ta_date");
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		int ta_id = Integer.parseInt(request.getParameter("ta_id"));
		String msg = "";


		if (ta_authority.equals("") || ta_authority == null) {
			msg = "Please Enter Authority";
			return msg;

		}	
		if (!validation.isValidAuth(ta_authority)) {
	 		return validation.isValidAuthMSG + " Authority";	
		}
 		if (!validation.isvalidLength(ta_authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority " + validation.isValidLengthMSG;	
		}
 		
		if (ta_authority_dateStr == null || ta_authority_dateStr.equals("null")
				|| ta_authority_dateStr.equals("DD/MM/YYYY") || ta_authority_dateStr.equals("")) {
			msg = "Please Enter Date of Authority ";
			return msg;
		
		} 
		else if (!validation.isValidDate(ta_authority_dateStr)) {
 			return validation.isValidDateMSG + " of Authority";	
		}
		else {
			dt_authority = format.parse(ta_authority_dateStr);
		}
		
		if (ta_status.equals("NA") || ta_status == null) {
			msg = "Please Select TA STATUS";
			return msg;
		}
			if (date_of_ta_statusStr == null || date_of_ta_statusStr.equals("null")
					|| date_of_ta_statusStr.equals("DD/MM/YYYY") || date_of_ta_statusStr.equals("")) {	
			msg = "Please Enter Date of TA Status ";
			return msg;

		} 
			else if (!validation.isValidDate(date_of_ta_statusStr)) {
	 			return validation.isValidDateMSG + " of Appointment";	
			}
			
			else {
				dt_ta = format.parse(date_of_ta_statusStr);
		  }
			
			
		try {
			Query q0 = sessionhql.createQuery("select count(id) from TB_CHANGE_TA_STATUS_JCO where date_of_ta_status=:date_of_ta_status and jco_id=:jco_id and  id!=:id and status!=-1").setTimestamp("date_of_ta_status", dt_ta)
					 .setParameter("id", ta_id).setParameter("jco_id", jco_id);
				Long c = (Long) q0.uniqueResult();
				if(c>0) {
					return "Date of TA Status already exists!!";
				}
			if (ta_id == 0) {
				TB_CHANGE_TA_STATUS_JCO appt = new TB_CHANGE_TA_STATUS_JCO();
				appt.setCreated_by(username);
				appt.setCreated_date(date);
				appt.setTa_status_authority(ta_authority);
				appt.setTa_authority_date(dt_authority);
				appt.setTa_status(Integer.parseInt(ta_status));
				appt.setDate_of_ta_status(dt_ta);
			
				appt.setJco_id(jco_id);
	            appt.setInitiated_from("u");
				appt.setStatus(0);
				appt.setCreated_by(username);
				appt.setCreated_date(date);

				int id = (int) sessionhql.save(appt);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_CHANGE_TA_STATUS_JCO set ta_status_authority=:ta_status_authority, "
						+ "ta_authority_date=:ta_authority_date, ta_status=:ta_status, "
						+ "date_of_ta_status=:date_of_ta_status,status=:status, "
						+ "modified_by=:modified_by, modified_date=:modified_date where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("status", 0)
						.setString("ta_status_authority", ta_authority)
						.setTimestamp("ta_authority_date", dt_authority)
						.setInteger("ta_status", Integer.parseInt(ta_status))
						.setTimestamp("date_of_ta_status", dt_ta).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("id", ta_id);

				msg = query.executeUpdate() > 0 ? "update" : "0";

			}


			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			
			}
			else
			{
				pjc.update_JcoOr_status(jco_id,username);
			}
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Data Not Updated Or Saved";
			} catch (RuntimeException rbe) {
				msg = "Data Not Updated Or Saved";
			}
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		// sessionhql.close();
		return msg;
	}
	
	public @ResponseBody List<TB_CHANGE_TA_STATUS_JCO> ta_status_jco_GetData1( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_TA_STATUS_JCO where  jco_id=:jco_id and status='0' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TA_STATUS_JCO> list = (List<TB_CHANGE_TA_STATUS_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	public String Update_TA_Status_jco(TB_CHANGE_TA_STATUS_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		/*try {*/

			String hql1 = "update TB_CHANGE_TA_STATUS_JCO set status=:status where  jco_id=:jco_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setInteger("jco_id", obj.getJco_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_CHANGE_TA_STATUS_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  jco_id=:jco_id and status = '0'";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("jco_id", obj.getJco_id());

			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			tx.commit();

		/*} catch (Exception e) {
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}*/
			sessionHQL.close();
		return msg;

	}

	/*--------------------- For REJECT ----------------------------------*/

	@RequestMapping(value = "/admin/getTAStatusJCOReject", method = RequestMethod.POST)
	public @ResponseBody String getTAStatusJCOReject(ModelMap Mmap, HttpSession session,
			HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg)
			throws ParseException {
		String reject_remarks = request.getParameter("reject_remarks");
		String username = session.getAttribute("username").toString();
		TB_CHANGE_TA_STATUS_JCO ChngTA = new TB_CHANGE_TA_STATUS_JCO();

		ChngTA.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		ChngTA.setId(Integer.parseInt(request.getParameter("ta_id")));
		ChngTA.setReject_remarks(reject_remarks);
		String msg1 = Update_TA_Status_jco_Reject(ChngTA, username);

		return msg1;

	}

	public String Update_TA_Status_jco_Reject(TB_CHANGE_TA_STATUS_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {


			String hql = "update TB_CHANGE_TA_STATUS_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where  jco_id=:jco_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setString("reject_remarks", obj.getReject_remarks())
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setInteger("jco_id", obj.getJco_id())
					.setInteger("id", obj.getId());

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

	public @ResponseBody List<TB_CHANGE_TA_STATUS_JCO> ta_status_jco_GetData2(int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_TA_STATUS_JCO where  jco_id=:jco_id and status='3' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TA_STATUS_JCO> list = (List<TB_CHANGE_TA_STATUS_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/ta_status_jco_GetData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_TA_STATUS_JCO> ta_status_jco_GetData3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
	
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_CHANGE_TA_STATUS_JCO where  jco_id=:jco_id and status='3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CHANGE_TA_STATUS_JCO> list = (List<TB_CHANGE_TA_STATUS_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/Change_TA_Status_jco_delete_action", method = RequestMethod.POST)
	public @ResponseBody String Change_TA_Status_jco_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_CHANGE_TA_STATUS_JCO where id=:id";
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
	
	
}
