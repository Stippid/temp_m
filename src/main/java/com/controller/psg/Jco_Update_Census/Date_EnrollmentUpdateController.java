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
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Date_EnrollmentUpdateController {

	psg_jco_CommonController p_comm = new psg_jco_CommonController();
	ValidationController valid = new ValidationController();
	
	@RequestMapping(value = "/admin/Enroll_Date_Action_JCO", method = RequestMethod.POST)
	public @ResponseBody String Enroll_Date_Action_JCO(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Date Date_authority = null;
		Date Date_enroll = null;
		String doe_authority = request.getParameter("doe_authority").trim();
     	String doe_date_of_authority = request.getParameter("doe_date_of_authority").trim();
     	String enroll_dt = request.getParameter("enroll_dt").trim();
     	String doe_id = request.getParameter("doe_id");
     	String hid_enroll_min = request.getParameter("min_date_enroll");
		String jco_id = request.getParameter("jco_id");
		String msg = "";
		if(doe_authority == null || doe_authority.equals("null") || doe_authority.equals("")) {
			 msg = "Please Enter Authority ";
			 return msg;
		 }
		if (!valid.isValidAuth(doe_authority)) {
			return valid.isValidAuthMSG + " Authority No";
		}	
		if (!valid.isvalidLength(doe_authority,valid.authorityMax,valid.authorityMin)) {
 			return "Authority No " + valid.isValidLengthMSG;	
		}
		if (doe_date_of_authority == null || doe_date_of_authority.equals("") || doe_date_of_authority.equals("DD/MM/YYYY") || doe_date_of_authority.equals("")) {
			msg = "Please Enter Date of Authority ";
			return msg;
		} 
		else if (!valid.isValidDate(doe_date_of_authority)) {
 			return valid.isValidDateMSG + " of Authority";	
		}
		else {
			Date_authority = format.parse(doe_date_of_authority);
		}
		if (enroll_dt == null || enroll_dt.equals("") || enroll_dt.equals("DD/MM/YYYY") || enroll_dt.equals("")) {
			msg = "Please Enter Date of Enrollment ";
			return msg;
		} else{
			if(format.parse(enroll_dt).before(format.parse(hid_enroll_min))) {
			Date_enroll = format.parse(enroll_dt);
			}else {
				msg = "Please Select Date of Enrollment Grater than Date of Rank and Date of TOS";
				return msg;

			}
		}
		
		
		
		
		try {
			if (Integer.parseInt(doe_id) == 0) {

				TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO per = new TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO();
				per.setJco_id(Integer.parseInt(jco_id));
				per.setAuthority(doe_authority);
				per.setDate_of_authority(Date_authority);
				per.setDate_of_enrollment(Date_enroll);
				//per.setRank(Integer.parseInt(rank));
                per.setCreated_by(username);
                per.setCreated_date(new Date());
				per.setStatus(0);

				int id = (int) sessionhql.save(per);
				msg = Integer.toString(id);
			} else {
			
				String hql = " update TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO set authority=:authority,date_of_authority=:date_of_authority,"
						+ "date_of_enrollment=:date_of_enrollment,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", doe_authority)
						.setDate("date_of_authority", Date_authority)
						.setDate("date_of_enrollment", Date_enroll)
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("doe_id")));
									
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
	
  
	@RequestMapping(value = "/admin/get_Enroll_Date1_JCO", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> get_Enroll_Date1_JCO(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO where jco_id=:jco_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> list = (List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getupdate_census_Dt_Of_EnrolmentData2", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> getupdate_census_Dt_Of_EnrolmentData2(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO where  status = '0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> list = (List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public String Update_Dt_of_Enrolment_details_JCO(TB_CENSUS_JCO_OR_PARENT obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_CENSUS_JCO_OR_PARENT set enroll_dt=:enroll_dt,modified_by=:modified_by,modified_date=:modified_date "
					+ "where id=:jco_id and status in('1','5')  ";
			Query query = sessionHQL.createQuery(hql).setParameter("enroll_dt", obj.getEnroll_dt())
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
	public String Update_Dt_Of_EnrolmentJCO(TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql1 = "update TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO set status=:status where jco_id=:jco_id and status != '0' ";
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("jco_id", obj.getJco_id());
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql = "update TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status "
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
	
	
	
	//***************************** Start Reject***************************************//
	@RequestMapping(value = "/admin/getDate_OF_Enrol_JCOReject", method = RequestMethod.POST)
	public @ResponseBody String getDate_OF_Enrol_JCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		String reject_remarks = request.getParameter("reject_remarks");
		
		TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO BG = new TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO();
		
		BG.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		BG.setId(Integer.parseInt(request.getParameter("doe_id")));
		BG.setReject_remarks(reject_remarks);
		String msg1 = Update_Dt_Of_Enroll_Reject(BG, username);

		return msg1;
		

	}

	public String Update_Dt_Of_Enroll_Reject(TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {

			String hql = "update TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where jco_id=:jco_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setString("reject_remarks", obj.getReject_remarks())
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setInteger("jco_id", obj.getJco_id())
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
	
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> getDt_of_EnrollDataJCO2( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO where jco_id=:jco_id and status = '3' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> list = (List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	
	
	@RequestMapping(value = "/admin/getDt_of_EnrolmentData_JCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> getDt_of_EnrolmentData_JCO3(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> list = (List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	
	@RequestMapping(value = "/admin/Dt_Of_Enroll_JCOdelete_action", method = RequestMethod.POST)
	public @ResponseBody String Dt_Of_Enroll_JCOdelete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO where id=:id";
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
	public List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> View_Date_Enrollment_DataCensus( int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO where  status = '1' and jco_id=:jco_id and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> list = (List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	/*******************************************Approve view End*********************************************************/
	
}
