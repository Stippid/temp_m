package com.controller.psg.Jco_Update_Census;

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
import com.controller.validation.ValidationController;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_GENDER_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Gender_UpdateController_JCO {
	
	
	psg_jco_CommonController p_comm = new psg_jco_CommonController();
	
	ValidationController valid = new ValidationController();
	@RequestMapping(value = "/admin/Census_Gender_Action_JCO", method = RequestMethod.POST)
	public @ResponseBody String Census_Gender_Action_JCO(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Date Date_authority = null;
       String authority = request.getParameter("g_authority").trim();
    	String date_of_authority = request.getParameter("g_date_of_authority").trim();
  	    String gender = request.getParameter("gender").trim();
		
		String g_id = request.getParameter("g_id").trim();
		String jco_id = request.getParameter("jco_id").trim();
		String msg = "";
		if (authority == null || authority.equals("") || authority.equals("null")) {
			return "Please Enter Authority No.";
		}
		if (!valid.isValidAuth(authority)) {
			return valid.isValidAuthMSG + " Authority No";
		}	
		if (!valid.isvalidLength(authority,valid.authorityMax,valid.authorityMin)) {
 			return "Authority No " + valid.isValidLengthMSG;	
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY")
				|| date_of_authority.equals("")) {
			return "Please Select Date of Authority";
		}
		else if (!valid.isValidDate(date_of_authority)) {
 			return valid.isValidDateMSG + " of Authority";	
		}
		else {
			Date_authority = format.parse(date_of_authority);
		}
		if (gender.equals("0") || gender == null || gender.equals("null")) {
			msg = "Please Select Gender";
			return msg;
		}
	
	
		try {
			if (Integer.parseInt(g_id) == 0) {

				TB_PSG_UPDATE_CENSUS_GENDER_JCO GN = new TB_PSG_UPDATE_CENSUS_GENDER_JCO();
				GN.setJco_id(Integer.parseInt(jco_id));
				GN.setAuthority(authority);
				GN.setDate_of_authority(Date_authority);
				GN.setCreated_by(username);
				GN.setCreated_date(new Date());
				GN.setStatus(0);
				GN.setGender(Integer.parseInt(gender));

				int id = (int) sessionHQL.save(GN);
				msg = Integer.toString(id);
			} else {
			
				String hql = " update TB_PSG_UPDATE_CENSUS_GENDER_JCO set authority=:authority,date_of_authority=:date_of_authority,gender=:gender,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionHQL.createQuery(hql).setString("authority", authority).setDate("date_of_authority", Date_authority)
						.setInteger("gender", Integer.parseInt(gender))
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("g_id")));
									
				msg = query.executeUpdate() > 0 ? "update" : "0";
				Mmap.put("msg", "Data Updated Successfully");

			}
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				
			}
			else
			{
				p_comm.update_JcoOr_Census_status(Integer.parseInt(jco_id), username);
			}
			
			tx.commit();
			}catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "0";
			} catch (RuntimeException rbe) {
				msg = "0";
			}

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return msg;
	}
	
	@RequestMapping(value = "/admin/get_Gender1_JCO", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> get_Gender1_JCO(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_GENDER_JCO where jco_id=:jco_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> list = (List<TB_PSG_UPDATE_CENSUS_GENDER_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	//***************************** Start Approve***************************************//
	
	@RequestMapping(value = "/admin/getupdate_census_GenderData2", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> getupdate_census_GenderData2(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_GENDER_JCO where  status = '0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> list = (List<TB_PSG_UPDATE_CENSUS_GENDER_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public String Update_Gender_census_details_JCO(TB_CENSUS_JCO_OR_PARENT obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_CENSUS_JCO_OR_PARENT set gender=:gender,modified_by=:modified_by,modified_date=:modified_date "
					+ "where id=:jco_id and status in ('1','5')  ";
			Query query = sessionHQL.createQuery(hql).setParameter("gender", obj.getGender())
					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setInteger("jco_id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	public String Update_Gender_JCO(TB_PSG_UPDATE_CENSUS_GENDER_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql1 = "update TB_PSG_UPDATE_CENSUS_GENDER_JCO set status=:status where jco_id=:jco_id and status != '0' ";
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("jco_id", obj.getJco_id());
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql = "update TB_PSG_UPDATE_CENSUS_GENDER_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status "
					+ " where  jco_id=:jco_id and status = '0'";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("jco_id", obj.getJco_id());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	//***************************** End Approve***************************************//
	
	//***************************** Start Reject***************************************//
	@RequestMapping(value = "/admin/getgenderJCOReject", method = RequestMethod.POST)
	public @ResponseBody String getgenderJCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		String reject_remarks = request.getParameter("reject_remarks");
		
		TB_PSG_UPDATE_CENSUS_GENDER_JCO BG = new TB_PSG_UPDATE_CENSUS_GENDER_JCO();
		
		BG.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		BG.setReject_remarks(reject_remarks);
		BG.setId(Integer.parseInt(request.getParameter("g_id")));
		String msg1 = Update_Gender_Reject(BG, username);

		return msg1;
		

	}

	public String Update_Gender_Reject(TB_PSG_UPDATE_CENSUS_GENDER_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {

			String hql = "update TB_PSG_UPDATE_CENSUS_GENDER_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where jco_id=:jco_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setInteger("jco_id", obj.getJco_id())
					.setString("reject_remarks", obj.getReject_remarks())
					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}
	
	
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> getgenderDataJCO2( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_GENDER_JCO where jco_id=:jco_id and status = '3' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> list = (List<TB_PSG_UPDATE_CENSUS_GENDER_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getGenderData_JCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> getGenderData_JCO3(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_GENDER_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> list = (List<TB_PSG_UPDATE_CENSUS_GENDER_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/Gender_JCOdelete_action", method = RequestMethod.POST)
	public @ResponseBody String Gender_JCOdelete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_PSG_UPDATE_CENSUS_GENDER_JCO where id=:id";
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
	
	//***************************** END Reject***************************************//
	
	//***************************** END Reject***************************************//
		public List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> ViewgenderDataCensus( int jco_id,Date modifiedDate) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_GENDER_JCO where  status = '1' and jco_id=:jco_id and modified_date=:modified_date ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
			@SuppressWarnings("unchecked")
			List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> list = (List<TB_PSG_UPDATE_CENSUS_GENDER_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
}
