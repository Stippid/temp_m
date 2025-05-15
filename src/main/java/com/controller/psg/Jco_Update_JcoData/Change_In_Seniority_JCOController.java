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
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_DATE_OF_SENIORITY_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_In_Seniority_JCOController {
	
   private Psg_CommonController com = new Psg_CommonController();
	
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	
	ValidationController valid = new ValidationController();
	
	// save and update change name
		@RequestMapping(value = "/admin/change_in_seniority_JcoOr_action", method = RequestMethod.POST)
		public @ResponseBody String change_in_seniority_JcoOr_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
				throws ParseException {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			Date date = new Date();
			Date se_authority = null;
			Date date_seniority = null;

			String username = session.getAttribute("username").toString();
			String authority = request.getParameter("se_authority");
			Date enroll_date = format.parse(request.getParameter("enroll_date"));
			Date dob_date = format.parse(request.getParameter("dob_date"));
			
			String se_date_authority = request.getParameter("se_date_authority");
			
			
			String reason_for_change = request.getParameter("reason_for_change");
			String date_of_seniority = request.getParameter("date_of_seniority");
			
			
			int jco_id = Integer.parseInt(request.getParameter("jco_id"));
			int se_id = Integer.parseInt(request.getParameter("se_id"));
			String msg = "";

			
			if (authority == null || authority.equals("")) {
				return "Please Enter Authority Date";
			}
			if (!valid.isValidAuth(authority)) {
				return valid.isValidAuthMSG + " Authority ";
			}	
			if (!valid.isvalidLength(authority,valid.authorityMax,valid.authorityMin)) {
	 			return "Authority " + valid.isValidLengthMSG;	
			}
			if (se_date_authority == null || se_date_authority.equals("null") || se_date_authority.equals("DD/MM/YYYY") || se_date_authority.equals("")) {
				return "Please Select Date of Authority";
			}
			else if (!valid.isValidDate(se_date_authority)) {
	 			return valid.isValidDateMSG + " of Authority";	
			}
			else {
				se_authority = format.parse(se_date_authority);
			}
			
			if (com.CompareDate(se_authority,enroll_date) == 0) {
				return "Authority Date should be Greater than Enrollment Date";
			}
			
			if (date_of_seniority == null || date_of_seniority.equals("null") || date_of_seniority.equals("DD/MM/YYYY") || date_of_seniority.equals("")) {
				return "Please Select New Date of Seniority ";
			}
			else if (!valid.isValidDate(date_of_seniority)) {
	 			return valid.isValidDateMSG + " of New Seniority";	
			}
			else {
				date_seniority = format.parse(date_of_seniority);
			}
			
/*			if (reason_for_change == null || reason_for_change.equals("") || reason_for_change.equals("null")) {
				return "Please Enter Reason for Change";
			}
			if (!valid.isOnlyAlphabet(reason_for_change)) {
		 		return "Reason For Change " + valid.isOnlyAlphabetMSG;	
			}
			if (!valid.isvalidLength(reason_for_change,valid.nameMax,valid.nameMin)) {
		 		return "Reason For Change " + valid.isValidLengthMSG;	
			}*/
			if (com.CompareDate(date_seniority,dob_date) == 0) {
				return "Date of Seniority should Be Greater Than Date of Birth";
			}
			

			try {

				if (se_id == 0) {
					TB_CHANGE_IN_DATE_OF_SENIORITY_JCO n = new TB_CHANGE_IN_DATE_OF_SENIORITY_JCO();
					n.setReason_for_change(reason_for_change);
					n.setDate_of_seniority(date_seniority);
					n.setSe_authority(authority);
					n.setSe_date_authority(se_authority);
					n.setInitiated_from("u");
					n.setJco_id(jco_id);
					n.setStatus(0);
					n.setCreated_by(username);
					n.setCreated_date(date);

					int id = (int) sessionhql.save(n);
					msg = Integer.toString(id);
				} else {
			
					String hql = " update TB_CHANGE_IN_DATE_OF_SENIORITY_JCO set se_authority=:se_authority ,se_date_authority=:se_date_authority"
							+ " ,initiated_from=:initiated_from,reason_for_change=:reason_for_change,date_of_seniority=:date_of_seniority,status=:status,modified_by=:modified_by,modified_date=:modified_date"
							+ " where  id=:id";
					Query query = sessionhql.createQuery(hql)

							.setString("se_authority", authority)
							.setTimestamp("se_date_authority", se_authority)
							.setTimestamp("date_of_seniority", date_seniority).setString("reason_for_change", reason_for_change)
							.setInteger("status", 0).setString("modified_by", username).setString("initiated_from","u")
							.setTimestamp("modified_date", new Date()).setInteger("id", se_id);

					msg = query.executeUpdate() > 0 ? "update" : "0";
					Mmap.put("msg", "Data Updated Successfully");
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

		
		
		@RequestMapping(value = "/admin/change_seniority_JCOGetData", method = RequestMethod.POST)
		public @ResponseBody List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> change_seniority_JCOGetData(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int jco_id = Integer.parseInt(request.getParameter("jco_id"));
			String hqlUpdate = " from TB_CHANGE_IN_DATE_OF_SENIORITY_JCO where jco_id=:jco_id and status='0' order by id desc ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> list = (List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		
		public @ResponseBody List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> Seniority_GetData1( int jco_id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_CHANGE_IN_DATE_OF_SENIORITY_JCO where  status='0' and jco_id=:jco_id ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> list = (List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		

		/*--------------------- For REJECT ----------------------------------*/

		@RequestMapping(value = "/admin/getChangeOfSeniorityJCOReject", method = RequestMethod.POST)
		public @ResponseBody String getChangeOfSeniorityJCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			String reject_remarks = request.getParameter("reject_remarks");

		
			String username = session.getAttribute("username").toString();
			TB_CHANGE_IN_DATE_OF_SENIORITY_JCO t = new TB_CHANGE_IN_DATE_OF_SENIORITY_JCO();
			t.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
			t.setId(Integer.parseInt(request.getParameter("se_id")));
			t.setReject_remarks(reject_remarks);

			String msg1 = Update_Change_of_Seniority_JCOReject(t, username);
			return msg1;

		}

		public String Update_Change_of_Seniority_JCOReject(TB_CHANGE_IN_DATE_OF_SENIORITY_JCO obj, String username) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String msg = "";
			
			try {

				String hql = "update TB_CHANGE_IN_DATE_OF_SENIORITY_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
						+ " where jco_id=:jco_id and status = '0' and id=:id ";

				Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 3)
						.setInteger("jco_id", obj.getJco_id())
						.setString("reject_remarks", obj.getReject_remarks())
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
		
		

		public String Update_SeniorityData(TB_CHANGE_IN_DATE_OF_SENIORITY_JCO obj, String username) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String msg = "";
			try {
				String hql1 = "update TB_CHANGE_IN_DATE_OF_SENIORITY_JCO set status=:status where  jco_id=:jco_id and (status != '0' and status != '-1') ";

				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
						.setInteger("jco_id", obj.getJco_id());

				msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

				String hql = "update TB_CHANGE_IN_DATE_OF_SENIORITY_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
						+ " where  jco_id=:jco_id and status = '0' ";

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
		public String Update_Seniority_JCO(TB_CENSUS_JCO_OR_PARENT obj, String username) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String msg = "";

			try {

				String hql = "update TB_CENSUS_JCO_OR_PARENT set modified_by=:modified_by,modified_date=:modified_date"
						+ ",date_of_seniority=:date_of_seniority  "
						+ " where id=:jco_id ";

				Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date", obj.getModified_date()).setInteger("jco_id", obj.getId())
						.setTimestamp("date_of_seniority", obj.getDate_of_seniority());
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
		
		public @ResponseBody List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> seniority_getDataJCO2( int jco_id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_CHANGE_IN_DATE_OF_SENIORITY_JCO where  jco_id=:jco_id and status = '3'";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> list = (List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		@RequestMapping(value = "/admin/change_seniority_JCOGetData3", method = RequestMethod.POST)
		public @ResponseBody List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> change_seniority_JCOGetData3(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("jco_id"));
			
			String hqlUpdate = " from TB_CHANGE_IN_DATE_OF_SENIORITY_JCO where jco_id=:jco_id and status='3'";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", id);
			@SuppressWarnings("unchecked")
			List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> list = (List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		@RequestMapping(value = "/admin/Change_Seniority_JCOdelete_action", method = RequestMethod.POST)
		public @ResponseBody String Change_Seniority_JCOdelete_action(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				String hqlUpdate = "delete from TB_CHANGE_IN_DATE_OF_SENIORITY_JCO where id=:id";
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
