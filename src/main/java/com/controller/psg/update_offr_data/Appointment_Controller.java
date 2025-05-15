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
import com.models.psg.update_census_data.TB_CHANGE_OF_APPOINTMENT;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Appointment_Controller {

	Psg_CommonController com = new Psg_CommonController();
	ValidationController validation = new ValidationController();

	// ====================================save/edit================================//

	@RequestMapping(value = "/admin/change_appointmentAction", method = RequestMethod.POST)
	public @ResponseBody String change_appointmentAction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();
		String username = session.getAttribute("username").toString();
		Date appt_date_of_authority = null;
		Date date_of_appointment = null;

		Date comm_date = format.parse(request.getParameter("comm_date"));
		Date tos_date = null;
		String appt_authority = request.getParameter("appt_authority");
		String appt_date_of_authorityStr = request.getParameter("appt_date_of_authority");
		String appointment = request.getParameter("appointment");
//		String x_sus_no = request.getParameter("x_sus_no");
//		String x_list_loc = request.getParameter("x_list_loc");
		String date_of_appointmentStr = request.getParameter("date_of_appointment");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		int census_id = Integer.parseInt(request.getParameter("census_id"));
		String parent_sus_no = request.getParameter("parent_sus_no");
		String parent_unit = request.getParameter("parent_unit");
		String x_list_loc = request.getParameter("x_list_loc");
		
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
		
		if (com.CompareDate(appt_date_of_authority,comm_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
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
		if (request.getParameter("appointment") == "9562" ||  request.getParameter("appointment") == "9565") 
		{
			if (parent_sus_no.equals("") && parent_sus_no == null && parent_unit.equals("") && parent_unit == null && x_list_loc.equals("") && x_list_loc == null) {
				return "Please Enter X-List SUS No Or  X-List Unit Name Or  X-List Location";
			
			}
		}
		try {
			Query q0 = sessionhql.createQuery("select count(id) from TB_CHANGE_OF_APPOINTMENT where date_of_appointment=:date_of_appointment and comm_id=:comm_id and  id!=:id and status!=-1").setTimestamp("date_of_appointment", date_of_appointment)
					 .setParameter("id", appoint_id).setParameter("comm_id", comm_id);
				Long c = (Long) q0.uniqueResult();
				if(c>0) {
					return "Date of Appointment Already Exists";
				}
			if (appoint_id == 0) {
				TB_CHANGE_OF_APPOINTMENT appt = new TB_CHANGE_OF_APPOINTMENT();
				appt.setCreated_by(username);
				appt.setCreated_date(date);
				appt.setAppt_authority(appt_authority);
				appt.setAppt_date_of_authority(appt_date_of_authority);
				appt.setAppointment(Integer.parseInt(appointment));
				appt.setDate_of_appointment(date_of_appointment);
//				appt.setX_list_sus_no(x_sus_no);
//				appt.setX_list_loc(x_list_loc);
				appt.setComm_id(comm_id);
				appt.setCensus_id(census_id);
				appt.setStatus(0);
				appt.setCreated_by(username);
				appt.setCreated_date(date);
				appt.setParent_sus_no(parent_sus_no);
				appt.setParent_unit(parent_unit);
				appt.setX_list_loc(x_list_loc);
				
				int id = (int) sessionhql.save(appt);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_CHANGE_OF_APPOINTMENT set appt_authority=:appt_authority, "
						+ "appt_date_of_authority=:appt_date_of_authority, appointment=:appointment, "
						+ "date_of_appointment=:date_of_appointment,status=:status,"
//						+ "x_list_sus_no=:x_list_sus_no,x_list_loc=:x_list_loc, "
                        + "parent_sus_no=:parent_sus_no,parent_unit=:parent_unit,x_list_loc=:x_list_loc, "
						+ "modified_by=:modified_by, modified_date=:modified_date where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("status", 0)
						.setString("appt_authority", appt_authority)
						.setTimestamp("appt_date_of_authority", appt_date_of_authority)
//						.setString("x_list_sus_no", x_sus_no).setString("x_list_loc", x_list_loc)
						.setString("parent_sus_no", parent_sus_no)
						.setString("parent_unit", parent_unit)
						.setString("x_list_loc", x_list_loc)
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
				com.update_offr_status(census_id,username);
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

	@RequestMapping(value = "/admin/get_Appointment", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_APPOINTMENT> get_Appointment(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT where census_id=:census_id and comm_id=:comm_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CHANGE_OF_APPOINTMENT> list = (List<TB_CHANGE_OF_APPOINTMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_CHANGE_OF_APPOINTMENT> get_Appointment1(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT where census_id=:census_id and comm_id=:comm_id and status='0' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_APPOINTMENT> list = (List<TB_CHANGE_OF_APPOINTMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	public String Update_Change_of_Appointment(TB_CHANGE_OF_APPOINTMENT obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		/*try {*/

			String hql1 = "update TB_CHANGE_OF_APPOINTMENT set status=:status where "
					//+ "census_id=:census_id and "
					+ "comm_id=:comm_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					//.setInteger("census_id", obj.getCensus_id())
					.setBigInteger("comm_id", obj.getComm_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_CHANGE_OF_APPOINTMENT set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where "
					//+ "census_id=:census_id and "
					+ "comm_id=:comm_id and status = '0'";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					//.setInteger("census_id", obj.getCensus_id())
					.setBigInteger("comm_id", obj.getComm_id());

			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			/*
			
		    
			String hql2 = "DELETE FROM TB_CHANGE_OF_APPOINTMENT "
                    + "WHERE id IN ("
                    + "  SELECT id FROM TB_CHANGE_OF_APPOINTMENT "
                    + "  WHERE comm_id = :comm_id AND appointment = 27107"
                    + ")";
                    
       Query query2 = sessionHQL.createQuery(hql2).setBigInteger("comm_id", obj.getComm_id());
       query2.executeUpdate();*/

			
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

	@RequestMapping(value = "/admin/getChangeOfAppointmentReject", method = RequestMethod.POST)
	public @ResponseBody String getChangeOfAppointmentReject(ModelMap Mmap, HttpSession session,
			HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg)
			throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_CHANGE_OF_APPOINTMENT ChngAppo = new TB_CHANGE_OF_APPOINTMENT();
		ChngAppo.setCensus_id(Integer.parseInt(request.getParameter("p_id")));
		ChngAppo.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ChngAppo.setId(Integer.parseInt(request.getParameter("appoint_id")));
		ChngAppo.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_Change_of_Appoi_Reject(ChngAppo, username);

		return msg1;

	}

	public String Update_Change_of_Appoi_Reject(TB_CHANGE_OF_APPOINTMENT obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {
//			String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//
//			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
//					.setTimestamp("modified_date", new Date()).setInteger("update_ofr_status", 3)
//					.setInteger("census_id", obj.getCensus_id()).setInteger("comm_id", obj.getComm_id());
//
//			msg1 = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			String hql = "update TB_CHANGE_OF_APPOINTMENT set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks   "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id())
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

	public @ResponseBody List<TB_CHANGE_OF_APPOINTMENT> get_Appointment2(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT where census_id=:census_id and comm_id=:comm_id and status='3' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_APPOINTMENT> list = (List<TB_CHANGE_OF_APPOINTMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_Appointment3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_APPOINTMENT> get_Appointment3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		/// bisag 241122 V2 (change comm_id int to big int)
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT where census_id=:census_id and comm_id=:comm_id and status='3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CHANGE_OF_APPOINTMENT> list = (List<TB_CHANGE_OF_APPOINTMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/appointment_delete_action", method = RequestMethod.POST)
	public @ResponseBody String appointment_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_CHANGE_OF_APPOINTMENT where id=:id";
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
