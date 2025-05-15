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
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Transaction.TB_POSTING_IN_OUT_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_RELIGION_JCO;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Religion_Update_jcoOr_Controller {

	Psg_CommonController com = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	// GET religion
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/admin/religion_GetData_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_RELIGION_JCO> religion_GetData_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_CHANGE_RELIGION_JCO where  jco_id=:jco_id and status=0 order by id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CHANGE_RELIGION_JCO> list = (List<TB_CHANGE_RELIGION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	@RequestMapping(value = "/admin/religion_action_jco", method = RequestMethod.POST)
	public @ResponseBody String religion_action_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date dt_authority = null;
		String msg = "";
		String username = session.getAttribute("username").toString();
		String authority = request.getParameter("authority");

		String date_of_authority = request.getParameter("date_of_authority");
		String religion = request.getParameter("religion");
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		int rel_id = Integer.parseInt(request.getParameter("rel_id"));
		String religion_other = request.getParameter("religion_other");
		  Date enroll_date = format.parse(request.getParameter("enroll_date"));
		 
		  
		  if (authority == null || authority.equals("")) {
              
              return "Please Enter Authority Date";
      }
      if (!validation.isValidAuth(authority)) {
               return validation.isValidAuthMSG + " Authority";        
      }
       if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
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
		if (com.CompareDate(dt_authority,enroll_date) == 0) {
		      return "Authority Date should be Greater than Enroll Date";
		}
		if (religion == null || religion.equals("0")) {
		      return "Please Enter  Religion";
		}
		if (!validation.isOnlyAlphabet(religion_other)) {
	 		return validation.isOnlyAlphabetMSG + " Religion Other";	
		}
		if (!validation.isvalidLength(religion_other,validation.nameMax,validation.nameMin)) {
	 		return " Religion Other " + validation.isValidLengthMSG;	
		}
	
	
		
		try {

			if (rel_id == 0) {
				TB_CHANGE_RELIGION_JCO n = new TB_CHANGE_RELIGION_JCO();
				n.setCreated_by(username);
				n.setCreated_date(date);
				n.setAuthority(authority);
				n.setReligion(Integer.parseInt(religion));
				n.setOther(religion_other);
				
				
				n.setDate_of_authority(dt_authority);
				n.setJco_id(jco_id);
				n.setStatus(0);
				n.setCreated_by(username);
				n.setCreated_date(date);
				n.setInitiated_from("u");

				int id = (int) sessionhql.save(n);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = " update TB_CHANGE_RELIGION_JCO set authority=:authority ,date_of_authority=:date_of_authority ,religion=:religion,status=:status,other=:other,modified_by=:modified_by,modified_date=:modified_date"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql)
						.setString("authority", authority)
						.setTimestamp("date_of_authority", dt_authority)
						.setInteger("religion", Integer.parseInt(religion)).setInteger("status", 0).setString("other", religion_other).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("id", rel_id);

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

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_CHANGE_RELIGION_JCO> religion_GetData1( int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_RELIGION_JCO where  status='0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CHANGE_RELIGION_JCO> list = (List<TB_CHANGE_RELIGION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	public String Update_Religion(TB_CHANGE_RELIGION_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {
			
			String hqlUpdate = " from TB_CHANGE_RELIGION_JCO where  status='0' and jco_id=:jco_id ";
			Query query11 = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", obj.getJco_id());
			List<TB_CHANGE_RELIGION_JCO> list = (List<TB_CHANGE_RELIGION_JCO>) query11.list();
			
			String hql1 = "update TB_CHANGE_RELIGION_JCO set status=:status where jco_id=:jco_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setInteger("jco_id", obj.getJco_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_CHANGE_RELIGION_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  jco_id=:jco_id and status = '0'";

			Query query = sessionHQL.createQuery(hql)

					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setInteger("status", 1).setInteger("jco_id", obj.getJco_id());
			//kajal KAJAL CHAUHAN
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql2 = "update TB_CENSUS_JCO_OR_PARENT set  modified_by=:modified_by,modified_date=:modified_date,religion=:religion,religion_other=:religion_other "
					+ " where  id=:jco_id ";
			Query query2 = sessionHQL.createQuery(hql2)

					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date()).setString("religion_other", list.get(0).getOther())
					.setInteger("religion", list.get(0).getReligion()).setInteger("jco_id", obj.getJco_id());

			msg = query2.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	public String Update_Census_Religion(TB_CENSUS_JCO_OR_PARENT obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {
			String hql = "update TB_CENSUS_JCO_OR_PARENT set religion=:religion "
					+ " where id=:id and status in (1,5) ";

			Query query = sessionHQL.createQuery(hql).setInteger("id", obj.getId())
					.setInteger("religion", obj.getReligion());

			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";

			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	/*--------------------- For REJECT ----------------------------------*/

	@RequestMapping(value = "/admin/getReligionReject_jco", method = RequestMethod.POST)
	public @ResponseBody String getReligionReject_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

        String reject_remarks = request.getParameter("reject_remarks");
		String username = session.getAttribute("username").toString();
		TB_CHANGE_RELIGION_JCO reli = new TB_CHANGE_RELIGION_JCO();
		reli.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		reli.setId(Integer.parseInt(request.getParameter("rel_id")));
		reli.setReject_remarks(reject_remarks);
		String msg1 = Update_Religion_Reject(reli, username);

		return msg1;

	}

	public String Update_Religion_Reject(TB_CHANGE_RELIGION_JCO obj, String username) {

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

			String hql = "update TB_CHANGE_RELIGION_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
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

	public @ResponseBody List<TB_CHANGE_RELIGION_JCO> religion_GetData2( int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_RELIGION_JCO where status='3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CHANGE_RELIGION_JCO> list = (List<TB_CHANGE_RELIGION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	@RequestMapping(value = "/admin/religion_GetData3_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_RELIGION_JCO> religion_GetData3_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CHANGE_RELIGION_JCO where jco_id=:jco_id and status='3' order by id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CHANGE_RELIGION_JCO> list = (List<TB_CHANGE_RELIGION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	 @RequestMapping(value = "/admin/Change_Religion_delete_action_jco", method = RequestMethod.POST)
		public @ResponseBody String Change_Religion_delete_action_jco(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				String hqlUpdate = "delete from TB_CHANGE_RELIGION_JCO where id=:id";
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
