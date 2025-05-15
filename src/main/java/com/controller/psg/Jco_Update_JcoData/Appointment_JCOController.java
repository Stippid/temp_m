package com.controller.psg.Jco_Update_JcoData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_OF_APPOINTMENT_JCO;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;

import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Appointment_JCOController {

	private Psg_CommonController com = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	ValidationController validation = new ValidationController();
	// ====================================save/edit================================//

	@RequestMapping(value = "/admin/change_appointmentJCOAction", method = RequestMethod.POST)
	public @ResponseBody String change_appointmentJCOAction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();
		String username = session.getAttribute("username").toString();
		Date appt_date_of_authority = null;
		Date date_of_appointment = null;

		Date enroll_dt = format.parse(request.getParameter("enroll_date"));
		Date tos_date = null;
		String appt_authority = request.getParameter("appt_authority");
		String appt_date_of_authorityStr = request.getParameter("appt_date_of_authority");
		String appointment = request.getParameter("appointment");
		String x_sus_no = request.getParameter("x_sus_no");
		String x_list_loc = request.getParameter("x_list_loc");
		String date_of_appointmentStr = request.getParameter("date_of_appointment");
	
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		int appoint_id = Integer.parseInt(request.getParameter("appoint_id"));
		String msg = "";
		
		if (!request.getParameter("tos_date").equals("") && !request.getParameter("tos_date").equals("DD/MM/YYYY")) {
			tos_date = format.parse(request.getParameter("tos_date"));
		}

		if (appt_authority.equals("") || appt_authority == null) {
			msg = "Please Enter Authority";
			return msg;

		}	
		if (!validation.isValidAuth(appt_authority)) {
	 		return validation.isValidAuthMSG + " Authority";	
		}
 		if (!validation.isvalidLength(appt_authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority " + validation.isValidLengthMSG;	
		}
 		
		if (appt_date_of_authorityStr == null || appt_date_of_authorityStr.equals("null")
				|| appt_date_of_authorityStr.equals("DD/MM/YYYY") || appt_date_of_authorityStr.equals("")) {
			msg = "Please Enter Date of Authority ";
			return msg;
		
		} 
		else if (!validation.isValidDate(appt_date_of_authorityStr)) {
 			return validation.isValidDateMSG + " of Authority";	
		}
		else {
			appt_date_of_authority = format.parse(appt_date_of_authorityStr);
		}
		
		if (com.CompareDate(appt_date_of_authority,enroll_dt) == 0) {
			return "Authority Date should be Greater than Enrollment Date";
		}
		if (appointment.equals("0") || appointment == null) {
			msg = "Please Select Appointment";
			return msg;
		}
			if (date_of_appointmentStr == null || date_of_appointmentStr.equals("null")
					|| date_of_appointmentStr.equals("DD/MM/YYYY") || date_of_appointmentStr.equals("")) {	
			msg = "Please Enter Date of Appointment ";
			return msg;

		} 
			else if (!validation.isValidDate(date_of_appointmentStr)) {
	 			return validation.isValidDateMSG + " of Appointment";	
			}
			
			else {
			date_of_appointment = format.parse(date_of_appointmentStr);
		  }
			
			if(date_of_appointmentStr!="" && !request.getParameter("tos_date").equals("")) {
				
			
			if (com.CompareDate(date_of_appointment,tos_date) == 0) {
				return "Appointment Date should be Greater than Tos Date";
			}
			}
		try {
			Query q0 = sessionhql.createQuery("select count(id) from TB_CHANGE_OF_APPOINTMENT_JCO where date_of_appointment=:date_of_appointment and jco_id=:jco_id and  id!=:id and status!=-1").setTimestamp("date_of_appointment", date_of_appointment)
					 .setParameter("id", appoint_id).setParameter("jco_id", jco_id);
				Long c = (Long) q0.uniqueResult();
				if(c>0) {
					return "Date of Appointment Already Exists";
				}
			if (appoint_id == 0) {
				TB_CHANGE_OF_APPOINTMENT_JCO appt = new TB_CHANGE_OF_APPOINTMENT_JCO();
				appt.setCreated_by(username);
				appt.setCreated_date(date);
				appt.setAppt_authority(appt_authority);
				appt.setAppt_date_of_authority(appt_date_of_authority);
				appt.setAppointment(Integer.parseInt(appointment));
				appt.setDate_of_appointment(date_of_appointment);
			
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
				String hql = "update TB_CHANGE_OF_APPOINTMENT_JCO set appt_authority=:appt_authority, "
						+ "appt_date_of_authority=:appt_date_of_authority, appointment=:appointment, "
						+ "date_of_appointment=:date_of_appointment,status=:status, "
						+ "modified_by=:modified_by, modified_date=:modified_date where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("status", 0)
						.setString("appt_authority", appt_authority)
						.setTimestamp("appt_date_of_authority", appt_date_of_authority)
						.setInteger("appointment", Integer.parseInt(appointment))
						.setTimestamp("date_of_appointment", date_of_appointment).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("id", appoint_id);

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

	// ========================== Get Method =============================//

	@RequestMapping(value = "/admin/get_AppointmentJCO", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_APPOINTMENT_JCO> get_AppointmentJCO(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT_JCO where jco_id=:jco_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CHANGE_OF_APPOINTMENT_JCO> list = (List<TB_CHANGE_OF_APPOINTMENT_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_CHANGE_OF_APPOINTMENT_JCO> get_AppointmentJCO1( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT_JCO where  jco_id=:jco_id and status='0' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_APPOINTMENT_JCO> list = (List<TB_CHANGE_OF_APPOINTMENT_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	public String Update_Change_of_Appointment(TB_CHANGE_OF_APPOINTMENT_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		/*try {*/

			String hql1 = "update TB_CHANGE_OF_APPOINTMENT_JCO set status=:status where  jco_id=:jco_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setInteger("jco_id", obj.getJco_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_CHANGE_OF_APPOINTMENT_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
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

	@RequestMapping(value = "/admin/getChangeOfAppointmentJCOReject", method = RequestMethod.POST)
	public @ResponseBody String getChangeOfAppointmentReject(ModelMap Mmap, HttpSession session,
			HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg)
			throws ParseException {
		String reject_remarks = request.getParameter("reject_remarks");
		String username = session.getAttribute("username").toString();
		TB_CHANGE_OF_APPOINTMENT_JCO ChngAppo = new TB_CHANGE_OF_APPOINTMENT_JCO();

		ChngAppo.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		ChngAppo.setId(Integer.parseInt(request.getParameter("appoint_id")));
		ChngAppo.setReject_remarks(reject_remarks);
		String msg1 = Update_Change_of_Appoi_Reject(ChngAppo, username);

		return msg1;

	}

	public String Update_Change_of_Appoi_Reject(TB_CHANGE_OF_APPOINTMENT_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {


			String hql = "update TB_CHANGE_OF_APPOINTMENT_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
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

	public @ResponseBody List<TB_CHANGE_OF_APPOINTMENT_JCO> get_AppointmentJCO2(int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT_JCO where  jco_id=:jco_id and status='3' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_APPOINTMENT_JCO> list = (List<TB_CHANGE_OF_APPOINTMENT_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_AppointmentJCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_APPOINTMENT_JCO> get_AppointmentJCO3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
	
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT_JCO where  jco_id=:jco_id and status='3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CHANGE_OF_APPOINTMENT_JCO> list = (List<TB_CHANGE_OF_APPOINTMENT_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/appointment_delete_JCOaction", method = RequestMethod.POST)
	public @ResponseBody String appointment_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_CHANGE_OF_APPOINTMENT_JCO where id=:id";
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
