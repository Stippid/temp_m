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
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_TRADE_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_NAME_JCO;
import com.models.psg.Jco_Update_JcoData.TB_IDENTITY_CARD_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_In_Trade_JCOController {
	
   private Psg_CommonController com = new Psg_CommonController();
	
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	
	ValidationController valid = new ValidationController();
	
	// save and update change name
		@RequestMapping(value = "/admin/change_in_tarde_JcoOr_action", method = RequestMethod.POST)
		public @ResponseBody String change_in_tarde_JcoOr_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
				throws ParseException {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			Date date = new Date();
			Date dt_authority = null;
			Date date_trade = null;

			String username = session.getAttribute("username").toString();
			String authority = request.getParameter("td_authority");
			Date enroll_date = format.parse(request.getParameter("enroll_date"));
			
			String date_of_authority = request.getParameter("td_date_authority");
			
			
			String trade = request.getParameter("trade");
			String date_of_trade = request.getParameter("date_of_trade");
			
			int jco_id = Integer.parseInt(request.getParameter("jco_id"));
			int td_id = Integer.parseInt(request.getParameter("td_id"));
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
			if (date_of_authority == null  || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
				return "Please Select Date of Authority";
			}
			else if (!valid.isValidDate(date_of_authority)) {
	 			return valid.isValidDateMSG + " of Authority";	
			}
			else {
					dt_authority = format.parse(date_of_authority);
			}
			if (com.CompareDate(dt_authority,enroll_date) == 0) {
				return "Authority Date should be Greater than Enrollment Date";
			}
			if (trade == null || trade.equals("0") || trade.equals("null")) {
				return "Please Select Trade";
			}
			
			if (date_of_trade == null  || date_of_trade.equals("DD/MM/YYYY") || date_of_trade.equals("")) {
				return "Please Select Date of Change of Trade";
			}
			else if (!valid.isValidDate(date_of_trade)) {
	 			return valid.isValidDateMSG + " of Trade";	
			}
			else {
				date_trade = format.parse(date_of_trade);
			}
			
			if (com.CompareDate(date_trade,enroll_date) == 0) {
				return "Date of Change of Trade should be Greater than Enrollment Date";
			}
			

			try {

				if (td_id == 0) {
					TB_CHANGE_IN_TRADE_JCO n = new TB_CHANGE_IN_TRADE_JCO();
					n.setTrade(Integer.parseInt(trade));
					n.setDate_of_trade(date_trade);
					n.setTd_authority(authority);
					n.setTd_date_authority(dt_authority);
					n.setInitiated_from("u");
					n.setJco_id(jco_id);
					n.setStatus(0);
					n.setCreated_by(username);
					n.setCreated_date(date);

					int id = (int) sessionhql.save(n);
					msg = Integer.toString(id);
				} else {
			
					String hql = " update TB_CHANGE_IN_TRADE_JCO set td_authority=:td_authority ,td_date_authority=:td_date_authority"
							+ " ,initiated_from=:initiated_from,trade=:trade,date_of_trade=:date_of_trade,status=:status,modified_by=:modified_by,modified_date=:modified_date"
							+ " where  id=:id";
					Query query = sessionhql.createQuery(hql)

							.setString("td_authority", authority)
							.setTimestamp("td_date_authority", dt_authority)
							.setTimestamp("date_of_trade", date_trade).setInteger("trade", Integer.parseInt(trade))
							.setInteger("status", 0).setString("modified_by", username).setString("initiated_from","u")
							.setTimestamp("modified_date", new Date()).setInteger("id", td_id);

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

		
		
		@RequestMapping(value = "/admin/change_of_trade_JCOGetData", method = RequestMethod.POST)
		public @ResponseBody List<TB_CHANGE_IN_TRADE_JCO> change_of_trade_JCOGetData(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int jco_id = Integer.parseInt(request.getParameter("jco_id"));
			String hqlUpdate = " from TB_CHANGE_IN_TRADE_JCO where jco_id=:jco_id and status='0' order by id desc ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_CHANGE_IN_TRADE_JCO> list = (List<TB_CHANGE_IN_TRADE_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		public @ResponseBody List<TB_CHANGE_IN_TRADE_JCO> TRADE_GetData1( int jco_id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_CHANGE_IN_TRADE_JCO where  status='0' and jco_id=:jco_id ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_CHANGE_IN_TRADE_JCO> list = (List<TB_CHANGE_IN_TRADE_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		/*--------------------- For REJECT ----------------------------------*/

		@RequestMapping(value = "/admin/getChangeOfTradeJCOReject", method = RequestMethod.POST)
		public @ResponseBody String getChangeOfTradeJCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			String reject_remarks = request.getParameter("reject_remarks");

			
			String username = session.getAttribute("username").toString();
			TB_CHANGE_IN_TRADE_JCO t = new TB_CHANGE_IN_TRADE_JCO();
			t.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
			t.setId(Integer.parseInt(request.getParameter("td_id")));
			t.setReject_remarks(reject_remarks);
			String msg1 = Update_Change_of_Trade_JCOReject(t, username);
			return msg1;

		}

		public String Update_Change_of_Trade_JCOReject(TB_CHANGE_IN_TRADE_JCO obj, String username) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String msg = "";
			
			try {

				String hql = "update TB_CHANGE_IN_TRADE_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
						+ " where jco_id=:jco_id and status = '0' and id=:id ";

				Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 3)
						.setInteger("jco_id", obj.getJco_id()).setString("reject_remarks", obj.getReject_remarks())
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
		
		
		
		public String Update_TradeData(TB_CHANGE_IN_TRADE_JCO obj, String username) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String msg = "";
			try {
				String hql1 = "update TB_CHANGE_IN_TRADE_JCO set status=:status where  jco_id=:jco_id and (status != '0' and status != '-1') ";

				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
						.setInteger("jco_id", obj.getJco_id());

				msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

				String hql = "update TB_CHANGE_IN_TRADE_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
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
		
		public String Update_Trade_JCO(TB_CENSUS_JCO_OR_PARENT obj, String username) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String msg = "";

			try {

				String hql = "update TB_CENSUS_JCO_OR_PARENT set modified_by=:modified_by,modified_date=:modified_date"
						+ ",trade=:trade  "
						+ " where id=:jco_id ";

				Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date", obj.getModified_date()).setInteger("jco_id", obj.getId())
						.setInteger("trade", obj.getTrade());
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
		
		public @ResponseBody List<TB_CHANGE_IN_TRADE_JCO> Trade_getDataJCO2( int jco_id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_CHANGE_IN_TRADE_JCO where  jco_id=:jco_id and status = '3'";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_CHANGE_IN_TRADE_JCO> list = (List<TB_CHANGE_IN_TRADE_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		
		@RequestMapping(value = "/admin/change_of_trade_JCOGetData3", method = RequestMethod.POST)
		public @ResponseBody List<TB_CHANGE_IN_TRADE_JCO> change_of_trade_JCOGetData3(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("jco_id"));
			
			String hqlUpdate = " from TB_CHANGE_IN_TRADE_JCO where jco_id=:jco_id and status='3'";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", id);
			@SuppressWarnings("unchecked")
			List<TB_CHANGE_IN_TRADE_JCO> list = (List<TB_CHANGE_IN_TRADE_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		@RequestMapping(value = "/admin/Change_Trade_JCOdelete_action", method = RequestMethod.POST)
		public @ResponseBody String Change_Trade_JCOdelete_action(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				String hqlUpdate = "delete from TB_CHANGE_IN_TRADE_JCO where id=:id";
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
