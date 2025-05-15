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
import com.models.psg.update_census_data.TB_CENSUS_SECONDMENT;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Secondment_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();
	
	ValidationController valid = new ValidationController();

	// ************************************START Secondment
	// DETAIL*******************************************

	  @RequestMapping(value = "/admin/Second_mentAction", method = RequestMethod.POST)
      public @ResponseBody String Second_mentAction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
                      throws ParseException {

              DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

              Session sessionhql = HibernateUtil.getSessionFactory().openSession();
              Transaction tx = sessionhql.beginTransaction();

              Date date = new Date();
              Date dt_auth = null;
              Date dt_sec_eff = null;
              String username = session.getAttribute("username").toString();
              Date comm_date = format.parse(request.getParameter("comm_date"));
              String authority = request.getParameter("authority");
              if (!request.getParameter("date_of_authority").equals("") && !request.getParameter("date_of_authority").equals("DD/MM/YYYY")) {
      			dt_auth = format.parse(request.getParameter("date_of_authority"));
      		}
      		
      		int seconded_to = Integer.parseInt(request.getParameter("seconded_to"));
      		if (!request.getParameter("secondment_effect").equals("")
      				&& !request.getParameter("secondment_effect").equals("DD/MM/YYYY")) {
      			dt_sec_eff = format.parse(request.getParameter("secondment_effect"));
      		}

      		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
      		int census_id = Integer.parseInt(request.getParameter("census_id"));

      		int sec_id = Integer.parseInt(request.getParameter("sec_id"));
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
      		if (dt_auth == null || dt_auth.equals("null") || dt_auth.equals("DD/MM/YYYY") || dt_auth.equals("")) {
      			return "Please Select Date of Authority" ;
      		}
      		else if (!valid.isValidDate(request.getParameter("date_of_authority"))) {
      			return valid.isValidDateMSG + " of Authority";
      		}	
      		else {
      			dt_auth = format.parse(request.getParameter("date_of_authority"));
      		}		
      		
      		if (mcommon.CompareDate(dt_auth, comm_date) == 0) {
      			return "Authority Date should be Greater than Commission Date";
      		}

      		if (seconded_to == -1) {
      			return "Please Select Seconded To";
      		}
      		
      		if (dt_sec_eff == null || dt_sec_eff.equals("null") || dt_sec_eff.equals("DD/MM/YYYY") || dt_sec_eff.equals("")) {
      			return "Please Select Secondment With Effect From" ;
      		}
      		else if (!valid.isValidDate(request.getParameter("secondment_effect"))) {
      			return valid.isValidDateMSG + " of Secondment With Effect From";
      		}	
      		else {
      			dt_sec_eff = format.parse(request.getParameter("secondment_effect"));
      		}

              try {

                      if (sec_id == 0) {
                              TB_CENSUS_SECONDMENT sec = new TB_CENSUS_SECONDMENT();
                              sec.setCreated_by(username);
                              sec.setCreated_date(date);
                              sec.setAuthority(authority);
                              sec.setDate_of_authority(dt_auth);
                              sec.setSeconded_to(seconded_to);
                              sec.setSecondment_effect(dt_sec_eff);
                              sec.setComm_id(comm_id);
                              sec.setCensus_id(census_id);
                              sec.setStatus(0);
                              sec.setCreated_by(username);
                              sec.setCreated_date(date);

                              int id = (int) sessionhql.save(sec);
                              msg = Integer.toString(id);
                      } else {
                              /*--S---REJECT----*/
                              /*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
                              CC.setUpdate_ofr_status(0);
                              sessionhql.save(CC);*/
                              /*---E--REJECT----*/
                              String hql = "update TB_CENSUS_SECONDMENT set authority=:authority, date_of_authority=:date_of_authority, seconded_to=:seconded_to, "
                                              + "secondment_effect=:secondment_effect,status=:status, "
                                              + "modified_by=:modified_by, modified_date=:modified_date where  id=:id";
                              Query query = sessionhql.createQuery(hql).setInteger("status", 0).setString("authority", authority)
                                              .setTimestamp("date_of_authority", dt_auth).setInteger("seconded_to", seconded_to)
                                              .setTimestamp("secondment_effect", dt_sec_eff).setString("modified_by", username)
                                              .setTimestamp("modified_date", new Date()).setInteger("id", sec_id);

                              msg = query.executeUpdate() > 0 ? "update" : "0";
                              Mmap.put("msg", "Data Updated Successfully");
                      }

                      String approoval_status = request.getParameter("app_status");
                      if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
                             
                      }
                      else
                      {
                              mcommon.update_offr_status(census_id,username);
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
	// ************************************END Secondment
	// DETAIL*******************************************

	// ========================== Get Method =============================//

	@RequestMapping(value = "/admin/get_Secondment", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_SECONDMENT> get_Secondment(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CENSUS_SECONDMENT where census_id=:census_id and comm_id=:comm_id and status=0 order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_SECONDMENT> list = (List<TB_CENSUS_SECONDMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_CENSUS_SECONDMENT> get_Secondment1(int id, BigInteger comm_id)  {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_SECONDMENT where census_id=:census_id and comm_id=:comm_id and status='0' order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_SECONDMENT> list = (List<TB_CENSUS_SECONDMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	public String Update_Secondment(TB_CENSUS_SECONDMENT obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			String hql1 = "update TB_CENSUS_SECONDMENT set status=:status where census_id=:census_id and comm_id=:comm_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_CENSUS_SECONDMENT set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0'";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id());

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

	@RequestMapping(value = "/admin/get_SecondmentReject", method = RequestMethod.POST)
	public @ResponseBody String get_SecondmentReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_CENSUS_SECONDMENT ChangeSEC = new TB_CENSUS_SECONDMENT();
		
		ChangeSEC.setCensus_id(Integer.parseInt(request.getParameter("ch_id")));
		ChangeSEC.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ChangeSEC.setId(Integer.parseInt(request.getParameter("ch_r_id")));
		ChangeSEC.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_Change_of_SecondmentReject(ChangeSEC, username);

		return msg1;

	}

	public String Update_Change_of_SecondmentReject(TB_CENSUS_SECONDMENT obj, String username) {

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
//			msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected. ";

			String hql = "update TB_CENSUS_SECONDMENT set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
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

	public @ResponseBody List<TB_CENSUS_SECONDMENT> getChangeOfSecondment2(int id, BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_SECONDMENT where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_SECONDMENT> list = (List<TB_CENSUS_SECONDMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_Secondment3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_SECONDMENT> getChangeOfRankData3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_SECONDMENT where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_SECONDMENT> list = (List<TB_CENSUS_SECONDMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	 @RequestMapping(value = "/admin/secondment_delete_action", method = RequestMethod.POST)
		public @ResponseBody String secondment_delete_action(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				String hqlUpdate = "delete from TB_CENSUS_SECONDMENT where id=:id";
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
