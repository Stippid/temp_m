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
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_NAME_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_TYPE_OF_POSTING_JCO;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Change_postingJCOController {

	private Psg_CommonController com = new Psg_CommonController();
	ValidationController validation = new ValidationController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	
	// ***************************Change of name
	// start**********************************//

	// GET change name

	@RequestMapping(value = "/admin/change_of_posting_JCOGetData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_TYPE_OF_POSTING_JCO> change_of_posting_JCOGetData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CHANGE_TYPE_OF_POSTING_JCO where jco_id=:jco_id and status='0'  ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TYPE_OF_POSTING_JCO> list = (List<TB_CHANGE_TYPE_OF_POSTING_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	// save and update change name
	@RequestMapping(value = "/admin/change_postingJCOAction", method = RequestMethod.POST)
	public @ResponseBody String change_postingJCOAction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		

		String username = session.getAttribute("username").toString();
		String type_of_post = request.getParameter("type_of_posting");
		
		
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		int ch_pp_id = Integer.parseInt(request.getParameter("ch_pp_id"));
		String msg = "";

		
		if (type_of_post == null || type_of_post.equals("-1")) {
			return "Please select Type of Post";
		}
		
		
		

		try {

			if (ch_pp_id == 0) {
				TB_CHANGE_TYPE_OF_POSTING_JCO n = new TB_CHANGE_TYPE_OF_POSTING_JCO();
				n.setCreated_by(username);
				n.setCreated_date(date);
				n.setType_of_post(Integer.parseInt(type_of_post));
			
				n.setJco_id(jco_id);
				n.setStatus(0);
				n.setCreated_by(username);
				n.setCreated_date(date);

				int id = (int) sessionhql.save(n);
				msg = Integer.toString(id);
			} else {
		
				String hql = " update TB_CHANGE_TYPE_OF_POSTING_JCO set type_of_post=:type_of_post ,status=:status,modified_by=:modified_by,modified_date=:modified_date"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql)

						.setInteger("type_of_post",Integer.parseInt(type_of_post))
						.setInteger("status", 0).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("id", ch_pp_id);

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
	// ************************************ END Change name
	// *******************************************

	/*--------------------- For Approval ----------------------------------*/

 public @ResponseBody List<TB_CHANGE_TYPE_OF_POSTING_JCO> getChangeOfPostingJCOData1( int jco_id) {
	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	String hqlUpdate = " from TB_CHANGE_TYPE_OF_POSTING_JCO where status='0' and jco_id=:jco_id ";
	Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
	 @SuppressWarnings("unchecked")
	 List<TB_CHANGE_TYPE_OF_POSTING_JCO> list = (List<TB_CHANGE_TYPE_OF_POSTING_JCO>) query.list();
 tx.commit();
	 sessionHQL.close();
	 return list;
	}
	
	public String Update_Change_of_postingJCO(TB_CHANGE_TYPE_OF_POSTING_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		//String msg1 = "";
		try {
			String hql1 = "update TB_CHANGE_TYPE_OF_POSTING_JCO set status=:status where  jco_id=:jco_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setInteger("jco_id", obj.getJco_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_CHANGE_TYPE_OF_POSTING_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where jco_id=:jco_id and status = '0'";

			Query query = sessionHQL.createQuery(hql)

					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setInteger("status", 1).setInteger("jco_id", obj.getJco_id());

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
	public @ResponseBody List<TB_CHANGE_TYPE_OF_POSTING_JCO> getChangeOfpostJCOData2( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_TYPE_OF_POSTING_JCO where jco_id=:jco_id and status = '3' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TYPE_OF_POSTING_JCO> list = (List<TB_CHANGE_TYPE_OF_POSTING_JCO>) query.list();
	
		tx.commit();
		sessionHQL.close();
		return list;
	}

	/*--------------------- For REJECT ----------------------------------*/

	@RequestMapping(value = "/admin/getChangeOfpostJCOReject", method = RequestMethod.POST)
	public @ResponseBody String getChangeOfpostJCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String reject_remarks = request.getParameter("reject_remarks");
		String username = session.getAttribute("username").toString();
		TB_CHANGE_TYPE_OF_POSTING_JCO Changepost = new TB_CHANGE_TYPE_OF_POSTING_JCO();
		Changepost.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		Changepost.setId(Integer.parseInt(request.getParameter("ch_pp_id")));
		Changepost.setReject_remarks(reject_remarks);
		String msg1 = Update_Change_of_post_JCOReject(Changepost, username);

		return msg1;

	}

	public String Update_Change_of_post_JCOReject(TB_CHANGE_TYPE_OF_POSTING_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			String hql = "update TB_CHANGE_TYPE_OF_POSTING_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where jco_id=:jco_id and status = '0' and id=:id ";

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

	

	@RequestMapping(value = "/admin/change_of_post_GetJCOData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_TYPE_OF_POSTING_JCO> change_of_post_GetJCOData3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("jco_id"));
		
		String hqlUpdate = " from TB_CHANGE_TYPE_OF_POSTING_JCO where jco_id=:jco_id and status='3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TYPE_OF_POSTING_JCO> list = (List<TB_CHANGE_TYPE_OF_POSTING_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/Change_of_post_JCOdelete_action", method = RequestMethod.POST)
	public @ResponseBody String Change_of_post_JCOdelete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_CHANGE_TYPE_OF_POSTING_JCO where id=:id";
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
	
	/*******************************************Approve view Start*********************************************************/
	public List<TB_CHANGE_TYPE_OF_POSTING_JCO> View_posting_Details_DataCensus( int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_TYPE_OF_POSTING_JCO where  status = '1' and jco_id=:jco_id and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TYPE_OF_POSTING_JCO> list = (List<TB_CHANGE_TYPE_OF_POSTING_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	/*******************************************Approve view End*********************************************************/
}
