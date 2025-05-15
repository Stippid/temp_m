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
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_NAME_JCO;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Change_Name_JCOController {

	private Psg_CommonController com = new Psg_CommonController();
	ValidationController validation = new ValidationController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	
	// ***************************Change of name
	// start**********************************//

	// GET change name

	@RequestMapping(value = "/admin/change_of_name_JCOGetData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_NAME_JCO> change_of_name_GetData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CHANGE_NAME_JCO where jco_id=:jco_id and status='0'  ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME_JCO> list = (List<TB_CHANGE_NAME_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	// save and update change name
	@RequestMapping(value = "/admin/change_of_name_JcoOr_action", method = RequestMethod.POST)
	public @ResponseBody String change_of_nameaction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date dt_authority = null;

		String username = session.getAttribute("username").toString();
		String authority = request.getParameter("authority");
		Date enroll_date = format.parse(request.getParameter("enroll_date"));
		
		String date_of_authority = request.getParameter("date_of_authority");
		
	
		String fname = request.getParameter("first_name");
		String mname = request.getParameter("middle_name");
		String lname = request.getParameter("last_name");
	String fullName="";
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		int name_id = Integer.parseInt(request.getParameter("name_id"));
		String msg = "";

		
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
		else {
				dt_authority = format.parse(date_of_authority);
		}
		if (com.CompareDate(dt_authority,enroll_date) == 0) {
			return "Authority Date should be Greater than Enrollment Date";
		}
		if (fname == null || fname.trim().equals("")) {
			return "Please Enter First Name";
		}
		if (!validation.isOnlyAlphabet(fname)) {
	 		return " First Name " + validation.isOnlyAlphabetMSG ;	
		}
		if (!validation.isvalidLength(fname,validation.nameMax,validation.nameMin)) {
	 		return "First Name " + validation.isValidLengthMSG;	
		}
		if (!validation.isOnlyAlphabet(mname)) {
	 		return " Middle Name " + validation.isOnlyAlphabetMSG ;	
		}
		if (!validation.isvalidLength(mname,validation.nameMax,validation.nameMin)) {
	 		return "Middle Name " + validation.isValidLengthMSG;	
		}
		if (!validation.isOnlyAlphabet(lname)) {
	 		return "Last Name " + validation.isOnlyAlphabetMSG;	
		}
		if (!validation.isvalidLength(lname,validation.nameMax,validation.nameMin)) {
	 		return "Last Name " + validation.isValidLengthMSG;	
		}
		else {
			fullName=fname.trim()+" "+mname.trim()+" "+lname.trim();
		}

		try {

			if (name_id == 0) {
				TB_CHANGE_NAME_JCO n = new TB_CHANGE_NAME_JCO();
				n.setCreated_by(username);
				n.setCreated_date(date);
				n.setAuthority(authority);
				n.setFirst_name(fname.trim());
				n.setMiddle_name(mname.trim());
				n.setLast_name(lname.trim());
				n.setFull_name(fullName.trim());
				n.setDate_of_authority(dt_authority);
				n.setInitiated_from("u");
				n.setJco_id(jco_id);
				n.setStatus(0);
				n.setCreated_by(username);
				n.setCreated_date(date);

				int id = (int) sessionhql.save(n);
				msg = Integer.toString(id);
			} else {
		
				String hql = " update TB_CHANGE_NAME_JCO set authority=:authority ,date_of_authority=:date_of_authority"
						+ " ,initiated_from=:initiated_from,first_name=:first_name,middle_name=:middle_name,last_name=:last_name,full_name=:full_name,status=:status,modified_by=:modified_by,modified_date=:modified_date"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql)

						.setString("authority", authority)
						.setTimestamp("date_of_authority", dt_authority).setString("first_name", fname.trim()).setString("middle_name", mname.trim()).setString("last_name", lname.trim()).setString("full_name", fullName.trim())
						.setInteger("status", 0).setString("modified_by", username).setString("initiated_from","u")
						.setTimestamp("modified_date", new Date()).setInteger("id", name_id);

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

	public @ResponseBody List<TB_CHANGE_NAME_JCO> getChangeOfNameJCOData1( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_NAME_JCO where  status='0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME_JCO> list = (List<TB_CHANGE_NAME_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Change_of_NameJCO(TB_CHANGE_NAME_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		//String msg1 = "";
		try {
			String hql1 = "update TB_CHANGE_NAME_JCO set status=:status where  jco_id=:jco_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setInteger("jco_id", obj.getJco_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_CHANGE_NAME_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
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

	public String Update_Comm_NameJCO(TB_CENSUS_JCO_OR_PARENT obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_CENSUS_JCO_OR_PARENT set modified_by=:modified_by,modified_date=:modified_date"
					+ ",full_name=:full_name,first_name=:first_name,last_name=:last_name,middle_name=:middle_name  "
					+ " where id=:jco_id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("jco_id", obj.getId())
					.setString("full_name", obj.getFull_name()).setString("first_name", obj.getFirst_name()).setString("middle_name", obj.getMiddle_name())
					.setString("last_name", obj.getLast_name());
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

	@RequestMapping(value = "/admin/getChangeOfNameJCOReject", method = RequestMethod.POST)
	public @ResponseBody String getChangeOfNameReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String reject_remarks = request.getParameter("reject_remarks");
		String username = session.getAttribute("username").toString();
		TB_CHANGE_NAME_JCO ChangeName = new TB_CHANGE_NAME_JCO();
		ChangeName.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		ChangeName.setId(Integer.parseInt(request.getParameter("name_id")));
		ChangeName.setReject_remarks(reject_remarks);
		String msg1 = Update_Change_of_Name_JCOReject(ChangeName, username);

		return msg1;

	}

	public String Update_Change_of_Name_JCOReject(TB_CHANGE_NAME_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			String hql = "update TB_CHANGE_NAME_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
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

	public @ResponseBody List<TB_CHANGE_NAME_JCO> getChangeOfNameJCOData2( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_NAME_JCO where jco_id=:jco_id and status = '3' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME_JCO> list = (List<TB_CHANGE_NAME_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/change_of_name_GetJCOData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_NAME_JCO> change_of_name_GetData3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("jco_id"));
		
		String hqlUpdate = " from TB_CHANGE_NAME_JCO where jco_id=:jco_id and status='3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME_JCO> list = (List<TB_CHANGE_NAME_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/Change_of_name_JCOdelete_action", method = RequestMethod.POST)
	public @ResponseBody String Change_of_name_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_CHANGE_NAME_JCO where id=:id";
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
