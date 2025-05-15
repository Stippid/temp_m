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

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO;

import com.persistance.util.HibernateUtil;



@Controller

@RequestMapping(value = {"admin","/","user"})

public class Enrollment_Class_Update_Controller {



	psg_jco_CommonController p_comm = new psg_jco_CommonController();

	

	

	ValidationController valid = new ValidationController();

	

	@RequestMapping(value = "/admin/Enroll_Class_Action_JCO", method = RequestMethod.POST)

	public @ResponseBody String Enroll_Class_Action_JCO(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {



		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		String username = session.getAttribute("username").toString();

		

		Date Date_authority = null;

		String ce_authority = request.getParameter("ce_authority").trim();

     	String ce_date_of_authority = request.getParameter("ce_date_of_authority").trim();

     	String class_enroll = request.getParameter("class_enroll");

     	String domicile = request.getParameter("domicile");

     	String ce_id = request.getParameter("ce_id");

		String jco_id = request.getParameter("jco_id");

		String msg = "";

		if(ce_authority == null || ce_authority.equals("null") || ce_authority.equals("")) {

			 msg = "Please Enter Authority ";

			 return msg;

		 }
		if (!valid.isValidAuth(ce_authority)) {
			return valid.isValidAuthMSG + " Authority No";
		}	
		if (!valid.isvalidLength(ce_authority,valid.authorityMax,valid.authorityMin)) {
 			return "Authority No " + valid.isValidLengthMSG;	
		}

		if (ce_date_of_authority == null || ce_date_of_authority.equals("") || ce_date_of_authority.equals("DD/MM/YYYY") || ce_date_of_authority.equals("")) {

			msg = "Please Enter Date of Authority ";

			return msg;

		} 
		else if (!valid.isValidDate(ce_date_of_authority)) {
 			return valid.isValidDateMSG + " of Authority";	
		} 
		else {

			Date_authority = format.parse(ce_date_of_authority);

		}

		if (class_enroll.equals("0") || class_enroll == null || class_enroll.equals("null")) {

			msg = "Please Select Class For Enrollment";

			return msg;

		}

		if(class_enroll.equals("10")) {

			if (domicile == null || domicile.equals("0") || domicile.equals("")) {

				msg = "Please Select Domicile";

				return msg;

			}

		}

		try {

			if (Integer.parseInt(ce_id) == 0) {



				TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO per = new TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO();

				per.setJco_id(Integer.parseInt(jco_id));

				per.setAuthority(ce_authority);

				per.setDate_of_authority(Date_authority);

				per.setClass_enroll(Integer.parseInt(class_enroll));

                per.setCreated_by(username);

                per.setCreated_date(new Date());

				per.setStatus(0);

				per.setDomicile(Integer.parseInt(domicile));

				int id = (int) sessionhql.save(per);

				msg = Integer.toString(id);

			} else {

			

				String hql = " update TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO set authority=:authority,date_of_authority=:date_of_authority,"

						+ "class_enroll=:class_enroll,"

						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status,domicile=:domicile"

						+ " where  id=:id";

				Query query = sessionhql.createQuery(hql).setString("authority", ce_authority).setDate("date_of_authority", Date_authority)

						.setInteger("class_enroll", Integer.parseInt(class_enroll))

						.setString("modified_by", username)

						.setTimestamp("modified_date", new Date())

						.setInteger("status", 0)

						.setInteger("domicile", Integer.parseInt(domicile))

						.setInteger("id",Integer.parseInt(request.getParameter("ce_id")));

									

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

	

	

	@RequestMapping(value = "/admin/get_Enroll_Class1_JCO", method = RequestMethod.POST)

	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> get_Enroll_Class1_JCO(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		

		int jco_id = Integer.parseInt(request.getParameter("jco_id"));



		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO where jco_id=:jco_id and status='0'";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		@SuppressWarnings("unchecked")

		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> list = (List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	

	@RequestMapping(value = "/admin/getupdate_census_EnrollClassData2", method = RequestMethod.POST)

	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> getupdate_census_EnrollClassData2(int jco_id) throws ParseException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO where  status = '0' and jco_id=:jco_id ";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		@SuppressWarnings("unchecked")

		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> list = (List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	

	public String Update_EnrollClass_details_JCO(TB_CENSUS_JCO_OR_PARENT obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_CENSUS_JCO_OR_PARENT set class_enroll=:class_enroll,domicile=:domicile,modified_by=:modified_by,modified_date=:modified_date "

					+ "where id=:jco_id and status in('1','5')  ";

			Query query = sessionHQL.createQuery(hql).setParameter("class_enroll", obj.getClass_enroll()).setParameter("domicile", obj.getDomicile())

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

	public String Update_EnrollClassJCO(TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql1 = "update TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO set status=:status where jco_id=:jco_id and status != '0' ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("jco_id", obj.getJco_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status "

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

	@RequestMapping(value = "/admin/getEnroll_Class_JCOReject", method = RequestMethod.POST)
	public @ResponseBody String getEnroll_Class_JCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		String reject_remarks = request.getParameter("reject_remarks");
		
		
		TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO BG = new TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO();
		BG.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		BG.setId(Integer.parseInt(request.getParameter("ce_id")));
		BG.setReject_remarks(reject_remarks);
		String msg1 = Update_Class_Enroll_Reject(BG, username);

		return msg1;
	}

	public String Update_Class_Enroll_Reject(TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";

		try {
			String hql = "update TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
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

	

	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> getClass_EnrollDataJCO2( int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO where jco_id=:jco_id and status = '3' ";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		@SuppressWarnings("unchecked")

		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> list = (List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

		

		

	@RequestMapping(value = "/admin/getEnrollClassData_JCO3", method = RequestMethod.POST)

	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> getEnrollClassData_JCO3(int jco_id) throws ParseException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO where  status = '3' and jco_id=:jco_id ";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		@SuppressWarnings("unchecked")

		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> list = (List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	

	@RequestMapping(value = "/admin/Class_Enro_JCOdelete_action", method = RequestMethod.POST)

	public @ResponseBody String Class_Enro_JCOdelete_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("id"));

		try {

			String hqlUpdate = "delete from TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO where id=:id";

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

	public List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> View_Class_Enrol_DataCensus( int jco_id,Date modifiedDate) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO where  status = '1' and jco_id=:jco_id and modified_date=:modified_date ";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);

		@SuppressWarnings("unchecked")

		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> list = (List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	/*******************************************Approve view End*********************************************************/

	

}

