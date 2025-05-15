package com.controller.psg.update_3008;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Transaction.CensusAprovedDAO;
import com.dao.psg.popup_history.Change_Appointment_History_DAO;
import com.models.psg.update_census_data.TB_CHANGE_OF_APPOINTMENT;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_of_Appointment {

	@Autowired
	private Change_Appointment_History_DAO changeAppointment;

	@Autowired
	private RoleBaseMenuDAO roledao;

	Psg_CommonController com = new Psg_CommonController();
	ValidationController validation = new ValidationController();

	@Autowired
	CensusAprovedDAO censusAprovedDAO;

	@RequestMapping(value = "/admin/get_Appointment_3008", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_APPOINTMENT> get_Appointment_3008(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT where  comm_id=:comm_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		List<TB_CHANGE_OF_APPOINTMENT> list = (List<TB_CHANGE_OF_APPOINTMENT>) query.list();
		tx.commit();
		sessionHQL.close();

		return list;
	}

	@RequestMapping(value = "/Change_In_Appointment_3008Url", method = RequestMethod.POST)
	public ModelAndView Change_In_Appointment_3008Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam(value = "appointment_comm_id", required = false) BigInteger appointment_comm_id) {

		Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<ArrayList<String>> list = changeAppointment.change_Appointment_3008(appointment_comm_id);
		Mmap.put("list", list);

		Mmap.put("msg", msg);

		return new ModelAndView("appointment_history_tiles");
	}

	@RequestMapping(value = "/admin/change_appointment_3008_Action", method = RequestMethod.POST)
	public @ResponseBody String change_appointment_3008_Action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

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
//			String x_sus_no = request.getParameter("x_sus_no");
//			String x_list_loc = request.getParameter("x_list_loc");
		String date_of_appointmentStr = request.getParameter("date_of_appointment");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		//int census_id = Integer.parseInt(request.getParameter("census_id"));
		int census_id=0;
		String censusIdParameter = request.getParameter("census_id");
		if (censusIdParameter != null && !censusIdParameter.isEmpty()) {
		    census_id = Integer.parseInt(censusIdParameter);		    
		}
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
		if (!validation.isvalidLength(appt_authority, validation.authorityMax, validation.authorityMin)) {
			return "Authority " + validation.isValidLengthMSG;
		}
		if (appt_date_of_authorityStr == null || appt_date_of_authorityStr.equals("null")
				|| appt_date_of_authorityStr.equals("DD/MM/YYYY") || appt_date_of_authorityStr.equals("")) {
			msg = "Please Enter Date of Authority ";
			return msg;
		} else if (!validation.isValidDate(appt_date_of_authorityStr)) {
			return validation.isValidDateMSG + " of Authority";
		} else {
			appt_date_of_authority = format.parse(appt_date_of_authorityStr);
		}

		if (com.CompareDate(appt_date_of_authority, comm_date) == 0) {
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
		} else if (!validation.isValidDate(date_of_appointmentStr)) {
			return validation.isValidDateMSG + " of Appointment";
		} else {
			date_of_appointment = format.parse(date_of_appointmentStr);
		}
		if (date_of_appointmentStr != "" && !request.getParameter("tos_date").equals("")) {

			if (com.CompareDate(date_of_appointment, tos_date) == 0) {
				return "Appointment Date should be Greater than Tos Date";
			}

		}
		if (request.getParameter("appointment") == "9562" || request.getParameter("appointment") == "9565") {
			if (parent_sus_no.equals("") && parent_sus_no == null && parent_unit.equals("") && parent_unit == null
					&& x_list_loc.equals("") && x_list_loc == null) {
				return "Please Enter X-List SUS No Or  X-List Unit Name Or  X-List Location";

			}
		}
		try {

			Query q0 = sessionhql.createQuery(
					"select count(id) from TB_CHANGE_OF_APPOINTMENT where date_of_appointment=:date_of_appointment and comm_id=:comm_id and  id!=:id and status!=-1")
					.setTimestamp("date_of_appointment", date_of_appointment).setParameter("id", appoint_id)
					.setParameter("comm_id", comm_id);
			Long c = (Long) q0.uniqueResult();
			if (c > 0) {
				return "Date of Appointment Already Exists";
			}

			String hql1 = "update TB_CHANGE_OF_APPOINTMENT set status=:status where  comm_id=:comm_id and (status != '0' and status != '-1') ";
			Query query1 = sessionhql.createQuery(hql1).setInteger("status", 2).setBigInteger("comm_id", comm_id);
			query1.executeUpdate();

			if (appoint_id == 0) {
				TB_CHANGE_OF_APPOINTMENT appt = new TB_CHANGE_OF_APPOINTMENT();
				appt.setCreated_by(username);
				appt.setCreated_date(date);
				appt.setAppt_authority(appt_authority);
				appt.setAppt_date_of_authority(appt_date_of_authority);
				appt.setAppointment(Integer.parseInt(appointment));
				appt.setDate_of_appointment(date_of_appointment);
				appt.setComm_id(comm_id);
				appt.setCensus_id(census_id);
				appt.setStatus(1);
				appt.setCreated_by(username);
				appt.setCreated_date(date);
				appt.setParent_sus_no(parent_sus_no);
				appt.setParent_unit(parent_unit);
				appt.setX_list_loc(x_list_loc);
				appt.setModified_by(username);
				appt.setModified_date(new Date());
				int id = (int) sessionhql.save(appt);
				msg = Integer.toString(id);
			} else {
				String hql = "update TB_CHANGE_OF_APPOINTMENT set appt_authority=:appt_authority, "
						+ "appt_date_of_authority=:appt_date_of_authority, appointment=:appointment, "
						+ "date_of_appointment=:date_of_appointment,status=:status,"
						+ "parent_sus_no=:parent_sus_no,parent_unit=:parent_unit,x_list_loc=:x_list_loc, "
						+ "modified_by=:modified_by, modified_date=:modified_date where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("status", 1)
						.setString("appt_authority", appt_authority)
						.setTimestamp("appt_date_of_authority", appt_date_of_authority)//
						.setString("parent_sus_no", parent_sus_no).setString("parent_unit", parent_unit)
						.setString("x_list_loc", x_list_loc).setInteger("appointment", Integer.parseInt(appointment))
						.setTimestamp("date_of_appointment", date_of_appointment).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("id", appoint_id);
				msg = query.executeUpdate() > 0 ? "update" : "0";

			}

			if (census_id > 0) {
				int count = censusAprovedDAO.checkdatapendingintable(comm_id, "appointment");
				if (count == 0) {

					String hql = "update TB_CENSUS_DETAIL_Parent set update_ofr_status=:update_ofr_status"
							+ " where comm_id=:comm_id ";
					Query query = sessionhql.createQuery(hql).setBigInteger("comm_id", comm_id)
							.setInteger("update_ofr_status", 1);
					msg = query.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully";
				}
			}
			
			int count1 = censusAprovedDAO.checkdatapendingintablecomm(comm_id, "rank");			
			if (count1 == 0) {

				String hql2 = "update TB_TRANS_PROPOSED_COMM_LETTER set update_comm_status =:update_comm_status  "
						+ " where id=:comm_id ";		

				Query query = sessionhql.createQuery(hql2).setBigInteger("comm_id", comm_id)						
						.setInteger("update_comm_status", 1);

				msg = query.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully";
			} 

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Data Not Approved Successfully";
			} catch (RuntimeException rbe) {
				msg = "Data Not Approved Successfully";
			}
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		// sessionhql.close();
		return msg;
	}

}
