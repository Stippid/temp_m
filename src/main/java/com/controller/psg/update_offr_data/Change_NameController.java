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
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Change_NameController {

	Psg_CommonController com = new Psg_CommonController();
	ValidationController validation = new ValidationController();
	
	// ***************************Change of name
	// start**********************************//

	// GET change name

	@RequestMapping(value = "/admin/change_of_name_GetData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_NAME> change_of_name_GetData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CHANGE_NAME where census_id=:census_id and status='0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME> list = (List<TB_CHANGE_NAME>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	// save and update change name
	@RequestMapping(value = "/admin/change_of_nameaction", method = RequestMethod.POST)
	public @ResponseBody String change_of_nameaction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date dt_authority = null;

		String username = session.getAttribute("username").toString();
		String authority = request.getParameter("authority");
		Date comm_date = format.parse(request.getParameter("comm_date"));
		Date change_in_name_dt =null;

		String change_in_name_date = request.getParameter("change_in_name_date");

		
		String date_of_authority = request.getParameter("date_of_authority");
		
		
		String name = request.getParameter("name");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		int census_id = Integer.parseInt(request.getParameter("census_id"));
		int name_id = Integer.parseInt(request.getParameter("name_id"));
		String msg = "";

		if (authority == null || authority.equals("")) {
			return "Please Enter Authority ";
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
		if (com.CompareDate(dt_authority,comm_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
		}
		if (name == null || name.equals("")) {
			return "Please Enter Name";
		}
		if (!validation.isOnlyAlphabet(name)) {
	 		return validation.isOnlyAlphabetMSG + " Name";	
		}
		if (!validation.isvalidLength(name,validation.nameMax,validation.nameMin)) {
	 		return "Name " + validation.isValidLengthMSG;	
		}
		if (change_in_name_date == null || change_in_name_date.equals("null") || change_in_name_date.equals("DD/MM/YYYY") || change_in_name_date.equals("")) {
			return "Please Select Date";
		}
		if (!change_in_name_date.equals("") && !change_in_name_date.equals("DD/MM/YYYY")) {
			change_in_name_dt = format.parse(change_in_name_date);
		}
		
		
	

		try {

			if (name_id == 0) {
				TB_CHANGE_NAME n = new TB_CHANGE_NAME();
				n.setCreated_by(username);
				n.setCreated_date(date);
				n.setAuthority(authority);
				n.setName(name);
				n.setDate_of_authority(dt_authority);
				n.setComm_id(comm_id);
				n.setCensus_id(census_id);
				n.setStatus(0);
				n.setCreated_by(username);
				n.setCreated_date(date);
				n.setChange_in_name_date(change_in_name_dt);
				
				
				
				
			
				int id = (int) sessionhql.save(n);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = " update TB_CHANGE_NAME set authority=:authority ,date_of_authority=:date_of_authority"
						+ " ,name=:name,status=:status,modified_by=:modified_by,modified_date=:modified_date,"
						+ " change_in_name_date=:change_in_name_date"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql)

						.setString("authority", authority)
						.setTimestamp("date_of_authority", dt_authority).setString("name", name)
						.setInteger("status", 0).setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
                        .setTimestamp("change_in_name_date",change_in_name_dt).setInteger("id", name_id);

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
			
			//sessionhql.close();

		return msg;
	}
	// ************************************ END Change name
	// *******************************************

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_CHANGE_NAME> getChangeOfNameData1(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_NAME where census_id=:census_id and status='0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME> list = (List<TB_CHANGE_NAME>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Change_of_Name(TB_CHANGE_NAME obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		//String msg1 = "";
		try {
			String hql1 = "update TB_CHANGE_NAME set status=:status where  comm_id=:comm_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setBigInteger("comm_id", obj.getComm_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_CHANGE_NAME set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
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

//	------------------- update by pranay 25.04
	
	public String Update_Comm_Name(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,name=:name "
					+ " where id=:comm_id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setBigInteger("comm_id", obj.getId())
					.setString("name", obj.getName());			
		
			
			Query query1 = sessionHQL.createQuery("update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by, modified_date=:modified_date, first_name=:name, last_name=:last_name, middle_name=:middle_name "
                    + "where comm_id=:comm_id")
                    .setString("modified_by", username)
                    .setTimestamp("modified_date", obj.getModified_date())
                    .setBigInteger("comm_id", obj.getId())
                    .setString("name", obj.getName())
                    .setString("last_name", "")
                    .setString("middle_name", "");
			query1.executeUpdate();

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

	@RequestMapping(value = "/admin/getChangeOfNameReject", method = RequestMethod.POST)
	public @ResponseBody String getChangeOfNameReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_CHANGE_NAME ChangeName = new TB_CHANGE_NAME();
		ChangeName.setCensus_id(Integer.parseInt(request.getParameter("p_id")));
		ChangeName.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ChangeName.setId(Integer.parseInt(request.getParameter("name_id")));
		ChangeName.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_Change_of_Name_Reject(ChangeName, username);

		return msg1;

	}

	public String Update_Change_of_Name_Reject(TB_CHANGE_NAME obj, String username) {

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

			String hql = "update TB_CHANGE_NAME set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks   "
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

	public @ResponseBody List<TB_CHANGE_NAME> getChangeOfNameData2(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_NAME where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME> list = (List<TB_CHANGE_NAME>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/change_of_name_GetData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_NAME> change_of_name_GetData3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CHANGE_NAME where census_id=:census_id and status='3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME> list = (List<TB_CHANGE_NAME>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/Change_of_name_delete_action", method = RequestMethod.POST)
	public @ResponseBody String Change_of_name_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_CHANGE_NAME where id=:id";
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
