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
import com.models.psg.update_census_data.TB_CHANGE_TA_STATUS;
import com.persistance.util.HibernateUtil;


@Controller

@RequestMapping(value = { "admin", "/", "user" })

public class TA_UpdateController {
	
	Psg_CommonController com = new Psg_CommonController();
	ValidationController validation = new ValidationController();

	/// bisag 241122 V2 (change comm_id int to big in)
	@RequestMapping(value = "/admin/ta_status_GetData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_TA_STATUS> ta_status_GetData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CHANGE_TA_STATUS where census_id=:census_id and comm_id=:comm_id and status=0 order by id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TA_STATUS> list = (List<TB_CHANGE_TA_STATUS>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}
	
	
	@RequestMapping(value = "/admin/ta_status_action", method = RequestMethod.POST)
	public @ResponseBody String ta_status_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date dt_authority = null;
		Date dt_ta = null;
		String msg = "";
		String username = session.getAttribute("username").toString();
		String ta_authority = request.getParameter("ta_authority");
		int ta_id = Integer.parseInt(request.getParameter("ta_id"));
		String date_of_authority = request.getParameter("ta_date_of_authority");
		String ta_date = request.getParameter("ta_date");
		String ta_status = request.getParameter("ta_status");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		int census_id = Integer.parseInt(request.getParameter("census_id"));
		Date comm_date = format.parse(request.getParameter("comm_date"));
		
		  if (ta_authority == null || ta_authority.equals("")) {
				return "Please Enter Authority";
			}
			if (!validation.isValidAuth(ta_authority)) {
		 		return validation.isValidAuthMSG + " Authority";	
			}
	 		if (!validation.isvalidLength(ta_authority,validation.authorityMax,validation.authorityMin)) {
	 			return "Authority " + validation.isValidLengthMSG;	
			}	
			if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
				return "Please Select Date of Authority";
			}
			if (!validation.isValidDate(date_of_authority)) {
	 			return validation.isValidDateMSG + " of Authority";	
			}
			if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
				dt_authority = format.parse(date_of_authority);
			}	
			if (!ta_date.equals("") && !ta_date.equals("DD/MM/YYYY")) {
				dt_ta = format.parse(ta_date);
			}
			if (com.CompareDate(dt_authority,comm_date) == 0) {
				return "Authority Date should be Greater than Commission Date";
			}
			if (ta_status == null || ta_status.equals("-1")) {
				return "Please Enter  Ta Status";
			}
			
		
		
		//try {

			if (ta_id == 0) {
				TB_CHANGE_TA_STATUS n = new TB_CHANGE_TA_STATUS();
				n.setCreated_by(username);
				n.setCreated_date(date);
				n.setTa_status_authority(ta_authority);
				n.setTa_status(Integer.parseInt(ta_status));
				n.setDate_of_ta_status(dt_ta);
				n.setTa_authority_date(dt_authority);
				n.setComm_id(comm_id);
				n.setCensus_id(census_id);
				n.setStatus(0);
				n.setCreated_by(username);
				n.setCreated_date(date);

				int id = (int) sessionhql.save(n);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = " update TB_CHANGE_TA_STATUS set ta_status_authority=:ta_status_authority "
						+ ",date_of_ta_status=:date_of_ta_status,ta_authority_date=:ta_authority_date "
						+ ",ta_status=:ta_status,status=:status,modified_by=:modified_by,modified_date=:modified_date"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql)
						.setString("ta_status_authority", ta_authority)
						.setTimestamp("date_of_ta_status", dt_ta)
						.setTimestamp("ta_authority_date", dt_authority)
						.setInteger("ta_status", Integer.parseInt(ta_status))
						.setInteger("status", 0)
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("id", ta_id);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				Mmap.put("msg", "Data Updated Successfully");
			}

			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				
			}
			else
			{
				com.update_offr_status(census_id,username);
			}
			tx.commit();
			/*
			 * } catch (RuntimeException e) { try { tx.rollback(); msg = "0"; } catch
			 * (RuntimeException rbe) { msg = "0"; } } finally { if (sessionhql != null) {
			 * sessionhql.close(); } }
			 */

		return msg;
	}
	
	
	
	public @ResponseBody List<TB_CHANGE_TA_STATUS> ta_status_GetData1(int id, BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_TA_STATUS where census_id=:census_id and status='0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TA_STATUS> list = (List<TB_CHANGE_TA_STATUS>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}
	
	
	
	public String Update_TA_Status(TB_CHANGE_TA_STATUS obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {
			String hql1 = "update TB_CHANGE_TA_STATUS set status=:status where census_id=:census_id and comm_id=:comm_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_CHANGE_TA_STATUS set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0'";

			Query query = sessionHQL.createQuery(hql)

					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setInteger("status", 1).setInteger("census_id", obj.getCensus_id())
					.setBigInteger("comm_id", obj.getComm_id());

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
	
	
	@RequestMapping(value = "/admin/getTAStatusReject", method = RequestMethod.POST)
	public @ResponseBody String getTAStatusReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_CHANGE_TA_STATUS ChangeTA = new TB_CHANGE_TA_STATUS();
		ChangeTA.setCensus_id(Integer.parseInt(request.getParameter("p_id")));
		ChangeTA.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ChangeTA.setId(Integer.parseInt(request.getParameter("ta_id")));
		ChangeTA.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_TA_Status_Reject(ChangeTA, username);

		return msg1;

	}
	
	public String Update_TA_Status_Reject(TB_CHANGE_TA_STATUS obj, String username) {

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
//			msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			String hql = "update TB_CHANGE_TA_STATUS set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
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
	
public @ResponseBody List<TB_CHANGE_TA_STATUS> ta_status_GetData2(int id, BigInteger comm_id) {
		

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_TA_STATUS where census_id=:census_id and status='3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TA_STATUS> list = (List<TB_CHANGE_TA_STATUS>) query.list();
		tx.commit();
		sessionHQL.close();
	
		return list;
	}

	@RequestMapping(value = "/admin/ta_status_GetData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_TA_STATUS> ta_status_GetData3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CHANGE_TA_STATUS where census_id=:census_id and comm_id=:comm_id and status='3' order by id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TA_STATUS> list = (List<TB_CHANGE_TA_STATUS>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	 @RequestMapping(value = "/admin/Change_TA_Status_delete_action", method = RequestMethod.POST)
		public @ResponseBody String Change_TA_Status_delete_action(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				String hqlUpdate = "delete from TB_CHANGE_TA_STATUS where id=:id";
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
