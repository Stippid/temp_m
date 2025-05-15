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
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.update_census_data.TB_PSG_EXTENSION_OF_COMISSION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Extension_Of_Commision_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();
	
	ValidationController valid = new ValidationController();

	@RequestMapping(value = "/admin/geteocData", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_EXTENSION_OF_COMISSION> geteocData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("n_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_PSG_EXTENSION_OF_COMISSION where census_id=:id and comm_id=:comm_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).setBigInteger("comm_id", comm_id);
		List<TB_PSG_EXTENSION_OF_COMISSION> list = (List<TB_PSG_EXTENSION_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	  @RequestMapping(value = "/admin/eoc_action", method = RequestMethod.POST)
		public @ResponseBody String eoc_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
				throws ParseException {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			Date date = new Date();
			Date date_of_authority_dt = null;
			Date to_dt = null;
			Date from_dt = null;

			String username = session.getAttribute("username").toString();

			String authority = request.getParameter("authority");
			String census_id = request.getParameter("cen_id");
			BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

			String date_of_authority = request.getParameter("date_of_authority");
			String to = request.getParameter("to");
			String from = request.getParameter("from");
			String eoc_ch_id = request.getParameter("eoc_ch_id");

			String msg = "";
			if (authority == null || authority.equals("") || authority == "") {
				return "Please Enter Authority";
			}
			if (!valid.isvalidLength(authority, valid.authorityMax, valid.authorityMin)) {
				return " Authority " + valid.isValidLengthMSG;
			}
			if (!valid.isValidAuth(authority)) {
				return valid.isValidAuthMSG + " Authority";
			}
			if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
				return "Please Select Date of Authority";
			} 
			if (!valid.isValidDate(request.getParameter("date_of_authority"))) {
				return valid.isValidDateMSG + " of Authority";
			}
			if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
				date_of_authority_dt = format.parse(date_of_authority);
			}

			Date comm_date = format.parse(request.getParameter("comm_date"));
			if (mcommon.CompareDate(date_of_authority_dt, comm_date) == 0) {
				return "Authority Date should be Greater than Commission Date";
			}
			if (from == null || from.equals("null") || from.equals("DD/MM/YYYY") || from.equals("")) {
				return "Please Select From Date";
			} 
			if (!valid.isValidDate(from)) {
				return valid.isValidDateMSG + " of Authority";
			}
			if (!from.equals("") && !from.equals("DD/MM/YYYY")) {
				from_dt = format.parse(from);
			}
			if (to == null || to.equals("null") || to.equals("DD/MM/YYYY") || to.equals("")) {
				return "Please Select To Date";
			}
			if (!valid.isValidDate(to)) {
				return valid.isValidDateMSG + " of Authority";
			}
			if (!to.equals("") && !to.equals("DD/MM/YYYY")) {
				to_dt = format.parse(to);
			}
		

			try {
				if (Integer.parseInt(eoc_ch_id) == 0) {
					TB_PSG_EXTENSION_OF_COMISSION eoc = new TB_PSG_EXTENSION_OF_COMISSION();
					eoc.setAuthority(authority);
					eoc.setDate_of_authority(date_of_authority_dt);
					eoc.setCensus_id(Integer.parseInt(census_id));
					eoc.setComm_id(comm_id);
					eoc.setStatus(0);
					eoc.setTodt(to_dt);
					eoc.setFromdt(from_dt);
					eoc.setCreated_by(username);
					eoc.setCreated_date(date);

					int id = (int) sessionhql.save(eoc);
					msg = Integer.toString(id);
				} else {
					/*--S---REJECT----*/
					/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
					CC.setUpdate_ofr_status(0);
					sessionhql.save(CC);*/
					/*---E--REJECT----*/
					String hql = "update TB_PSG_EXTENSION_OF_COMISSION set modified_by=:modified_by ,modified_date=:modified_date, "
							+ "authority=:authority, date_of_authority=:date_of_authority,fromdt=:from, "
							+ "todt=:to,status=:status " + " where  id=:id";
					Query query = sessionhql.createQuery(hql).setString("authority", authority)
							.setParameter("date_of_authority", date_of_authority_dt).setParameter("to", to_dt)
							.setParameter("from", from_dt).setParameter("status", 0)
							.setInteger("id", Integer.parseInt(eoc_ch_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date());

					msg = query.executeUpdate() > 0 ? "update" : "0";

				}

				String approoval_status = request.getParameter("app_status");
				if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
					
				}
				else
				{
					mcommon.update_offr_status(Integer.parseInt(census_id),username);
				}

				
				tx.commit();
			} catch (RuntimeException e) {
				e.printStackTrace();
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

	/*
	 * @RequestMapping(value = "/admin/eoc_delete_action", method =
	 * RequestMethod.POST) public @ResponseBody String coc_delete_action(ModelMap
	 * Mmap, HttpSession session, HttpServletRequest request) throws ParseException
	 * { String msg = ""; Session sessionHQL =
	 * HibernateUtil.getSessionFactory().openSession(); Transaction tx =
	 * sessionHQL.beginTransaction(); int id =
	 * Integer.parseInt(request.getParameter("eoc_ch_id")); try { String hqlUpdate =
	 * "delete from TB_PSG_EXTENSION_OF_COMISSION where id=:id"; int app =
	 * sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
	 * tx.commit(); sessionHQL.close(); if (app > 0) { msg = "1"; } else { msg =
	 * "0"; } } catch (Exception e) {
	 * 
	 * } return msg; }
	 */

	/*--------------------- For Approval -----------------------------*/

	public @ResponseBody List<TB_PSG_EXTENSION_OF_COMISSION> geteocData1(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_EXTENSION_OF_COMISSION where census_id=:id and comm_id=:comm_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).setBigInteger("comm_id", comm_id);
		List<TB_PSG_EXTENSION_OF_COMISSION> list = (List<TB_PSG_EXTENSION_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_ExtensionCommision(TB_PSG_EXTENSION_OF_COMISSION obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		// String msg1 = "";
		try {
			String hql1 = "update TB_PSG_EXTENSION_OF_COMISSION set status=:status where census_id=:census_id and comm_id=:comm_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_PSG_EXTENSION_OF_COMISSION set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
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

	/*--------------------- For REJECT ----------------------------------*/

	@RequestMapping(value = "/admin/get_EocReject", method = RequestMethod.POST)
	public @ResponseBody String get_EocReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_PSG_EXTENSION_OF_COMISSION ChangeEoc = new TB_PSG_EXTENSION_OF_COMISSION();
		ChangeEoc.setCensus_id(Integer.parseInt(request.getParameter("ch_id")));
		ChangeEoc.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ChangeEoc.setId(Integer.parseInt(request.getParameter("ch_r_id")));
		ChangeEoc.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_Change_of_EocReject(ChangeEoc, username);
		return msg1;

	}

	public String Update_Change_of_EocReject(TB_PSG_EXTENSION_OF_COMISSION obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		// String msg1= "";
		try {
//			String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//
//			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
//					.setTimestamp("modified_date", new Date()).setInteger("update_ofr_status", 3)
//					.setInteger("census_id", obj.getCensus_id()).setInteger("comm_id", obj.getComm_id());
//
//			msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.11111";

			String hql = "update TB_PSG_EXTENSION_OF_COMISSION set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks   "
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

	public @ResponseBody List<TB_PSG_EXTENSION_OF_COMISSION> getChangeOfEoc2(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_EXTENSION_OF_COMISSION where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_EXTENSION_OF_COMISSION> list = (List<TB_PSG_EXTENSION_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_Eoc3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_EXTENSION_OF_COMISSION> get_Eoc3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_PSG_EXTENSION_OF_COMISSION where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_EXTENSION_OF_COMISSION> list = (List<TB_PSG_EXTENSION_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	@RequestMapping(value = "/admin/eoc_delete_action", method = RequestMethod.POST)
	public @ResponseBody String eoc_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_PSG_EXTENSION_OF_COMISSION where id=:id";
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
