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

import org.springframework.beans.factory.annotation.Autowired;

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
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FOREIGN_COUNTRY_JCO;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;


import com.persistance.util.HibernateUtil;



@Controller

@RequestMapping(value = { "admin", "/", "user" })



public class Foreign_Contry_Jco_Controller {

	ValidationController valid = new ValidationController();

	@Autowired

	private Psg_CommonController com = new Psg_CommonController();

	private psg_jco_CommonController pjc=new psg_jco_CommonController();

	/************************

	 * ---UPDATE FOREIGN COUNTRY VISITED START----

	 *****************************/



	// GET Foreign Country DETAILS

	@RequestMapping(value = "/admin/getUPForeginCountryData_jco", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_FOREIGN_COUNTRY_JCO> getUPForeginCountryData_jco(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();


		int jco_id = Integer.parseInt(request.getParameter("jco_id"));



		String hqlUpdate = " from TB_CENSUS_FOREIGN_COUNTRY_JCO where  jco_id=:jco_id and status=0";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		List<TB_CENSUS_FOREIGN_COUNTRY_JCO> list = (List<TB_CENSUS_FOREIGN_COUNTRY_JCO>) query.list();

	

		tx.commit();

		sessionHQL.close();

		return list;

	}



	// SAVE & UPDATE Foreign Country ACTION

	@RequestMapping(value = "/admin/UPforegin_country_action_jco", method = RequestMethod.POST)

	public @ResponseBody String UPforegin_country_action_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		Date from_date = null;

		Date to_date = null;

		String username = session.getAttribute("username").toString();

		String country = request.getParameter("country");

		String ot_country = request.getParameter("other_ct");

		String period = request.getParameter("period");

		String from_dt = request.getParameter("from_dt");

		String to_dt = request.getParameter("to_dt");

		String purpose_visit = request.getParameter("purpose_visit");

		String other_purpose_visit = request.getParameter("other_purpose_visit");

		String foregin_country_ch_id = request.getParameter("foregin_country_ch_id");


		String jco_id = request.getParameter("jco_id");

		String rvalue = "";


/*
		if (!from_dt.equals("") && !from_dt.equals("DD/MM/YYYY")) {

			from_date = format.parse(from_dt);

		}

		if (!to_dt.equals("") && !to_dt.equals("DD/MM/YYYY")) {

			to_date = format.parse(to_dt);

		}*/

		if (country == null || country.equals("0")) {

			return "Please Select the Country Visited";

		}

		

		if (from_dt == null || from_dt.equals("null") || from_dt.equals("DD/MM/YYYY") || from_dt.equals("")) {

			return "Please Select the From Date";

		}
		
		if (!from_dt.trim().equals("") && !from_dt.equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(from_dt)) {
				return valid.isValidDateMSG + " of From ";
			} else {
				from_date = format.parse(from_dt);
			}
		}

		if (to_dt == null || to_dt.equals("null") || to_dt.equals("DD/MM/YYYY") || to_dt.equals("")) {

			return "Please Select the To Date";

		}
	
		if (!to_dt.trim().equals("") && !to_dt.equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(to_dt)) {
				return valid.isValidDateMSG + " of To ";
			} else {
				to_date = format.parse(to_dt);
			}
		}
		if (period == null || period.equals("")) {

			return "Please Enter valid From And To Date";

		}

		if (!period.equals(com.calculate_duration(format.parse(from_dt),format.parse(to_dt)))) {



			return "Please Enter valid From And To Date";



		}

		if (purpose_visit == null || purpose_visit.equals("")) {

			return "Please Enter Purpose To Visit";

		}



		TB_CENSUS_FOREIGN_COUNTRY_JCO foregin = new TB_CENSUS_FOREIGN_COUNTRY_JCO();



		try {

			if (Integer.parseInt(foregin_country_ch_id) == 0) {

				// save

				foregin.setCountry(Integer.parseInt(country));

				foregin.setPeriod(period);

				foregin.setOther_country(ot_country);

				foregin.setFrom_dt(from_date);

				foregin.setTo_dt(to_date);

				foregin.setPurpose_visit(Integer.parseInt(purpose_visit));

				foregin.setOther_purpose_visit(other_purpose_visit);


				foregin.setJco_id(Integer.parseInt(jco_id));

				foregin.setCreated_by(username);

				foregin.setCreated_on(date);
				foregin.setInitiated_from("u");

				int id = (int) sessionhql.save(foregin);

				rvalue = Integer.toString(id);



			} else {

				// update

				/*--S---REJECT----*/

				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();

				CC.setUpdate_ofr_status(0);

				sessionhql.save(CC);*/

				/*---E--REJECT----*/



				String hql = " update TB_CENSUS_FOREIGN_COUNTRY_JCO set country=:country ,other_country=:other_country ,jco_id=:jco_id ,period=:period,from_dt=:from_dt,to_dt=:to_dt,"

						+ "purpose_visit=:purpose_visit,other_purpose_visit=:other_purpose_visit,modified_by=:modified_by,modified_date=:modified_date,status=:status"

						+ " where  id=:id";

				Query query = sessionhql.createQuery(hql)



						.setInteger("country", Integer.parseInt(request.getParameter("country")))

						.setString("other_country", ot_country)

						.setInteger("jco_id", Integer.parseInt(request.getParameter("jco_id")))

						.setString("period", period).setTimestamp("from_dt", from_date)

						.setTimestamp("to_dt", to_date)

						.setInteger("purpose_visit", Integer.parseInt(purpose_visit))

						.setString("other_purpose_visit", other_purpose_visit)

						.setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0)

						.setInteger("id", Integer.parseInt(request.getParameter("foregin_country_ch_id")));



				rvalue = query.executeUpdate() > 0 ? "update" : "0";

				Mmap.put("msg", "Data Updated Successfully");

			}

			String approoval_status = request.getParameter("app_status");

			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {

			

			}

			else

			{

				pjc.update_JcoOr_status(Integer.parseInt(jco_id),username);

			}

			

			tx.commit();

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				rvalue = "0";

			} catch (RuntimeException rbe) {

				rvalue = "0";

			}

		} finally {

			if (sessionhql != null) {

				sessionhql.close();

			}

		}

		return rvalue;

	}



	// DELETE Foreign Country

	@RequestMapping(value = "/admin/UPforegin_country_delete_action_jco", method = RequestMethod.POST)

	public @ResponseBody String UPforegin_country_delete_action_jco(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("foregin_country_ch_id"));

		try {

			String hqlUpdate = "delete from TB_CENSUS_FOREIGN_COUNTRY_JCO where id=:id";

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



	/************************

	 * ---UPDATE FOREIGN COUNTRY VISITED END----

	 *****************************/



	/*--------------------- For Approval ----------------------------------*/



	public @ResponseBody List<TB_CENSUS_FOREIGN_COUNTRY_JCO> getUPForeginCountryData1( int jco_id) {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_CENSUS_FOREIGN_COUNTRY_JCO where  jco_id=:jco_id and status='0'";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		@SuppressWarnings("unchecked")

		List<TB_CENSUS_FOREIGN_COUNTRY_JCO> list = (List<TB_CENSUS_FOREIGN_COUNTRY_JCO>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}



	public String Update_ForeignContry(TB_CENSUS_FOREIGN_COUNTRY_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();



		String msg = "";

		String msg1 = "";

		try {

			/*

			 * String hql1 =

			 * "update TB_CENSUS_FOREIGN_COUNTRY_JCO set status=:status where cen_id=:census_id and jco_id=:jco_id and status != '0' "

			 * ;

			 * 

			 * Query query1 = sessionHQL.createQuery(hql1).setInteger("status",

			 * 2).setInteger("census_id", obj.getCen_id())

			 * .setInteger("jco_id",obj.getjco_id());

			 * 

			 * msg1 = query1.executeUpdate() > 0 ? "Data Updated Successfully."

			 * :"Data Not Updated.";

			 */



			String hql = "update TB_CENSUS_FOREIGN_COUNTRY_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "

					+ " where  jco_id=:jco_id and status = '0'";



			Query query = sessionHQL.createQuery(hql)



					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())

					.setInteger("status", 1)

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



	/*--------------------- For REJECT ----------------------------------*/



	@RequestMapping(value = "/admin/getforeginCountry_Reject_jco", method = RequestMethod.POST)

	public @ResponseBody String getforeginCountry_Reject_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request,

			@RequestParam(value = "msg", required = false) String msg) throws ParseException {




 
        String reject_remarks = request.getParameter("reject_remarks");


		String username = session.getAttribute("username").toString();

		TB_CENSUS_FOREIGN_COUNTRY_JCO ChangeFore = new TB_CENSUS_FOREIGN_COUNTRY_JCO();


		ChangeFore.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		ChangeFore.setReject_remarks(reject_remarks);


		String msg1 = Update_Change_of_ForeignReject(ChangeFore, username);



		return msg1;



	}



	public String Update_Change_of_ForeignReject(TB_CENSUS_FOREIGN_COUNTRY_JCO obj, String username) {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();



		String msg = "";

		// String msg1= "";

		try {

			String hql = "update TB_CENSUS_FOREIGN_COUNTRY_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "

					+ " where  jco_id=:jco_id and status = '0' ";



			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)

					.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())

					.setInteger("jco_id", obj.getJco_id());



			msg = query.executeUpdate() > 0 ? "1" : "0 ";




			tx.commit();



		} catch (Exception e) {

			msg = "Data Not Updated.";

			tx.rollback();

		} finally {

			sessionHQL.close();

		}

		return msg;



	}



	public @ResponseBody List<TB_CENSUS_FOREIGN_COUNTRY_JCO> getChangeOfForeignCountry2(int jco_id) {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_CENSUS_FOREIGN_COUNTRY_JCO where  status = '3' and jco_id=:jco_id ";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		@SuppressWarnings("unchecked")

		List<TB_CENSUS_FOREIGN_COUNTRY_JCO> list = (List<TB_CENSUS_FOREIGN_COUNTRY_JCO>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}



	@RequestMapping(value = "/admin/get_ForeignCountry3_jco", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_FOREIGN_COUNTRY_JCO> get_ForeignCountry3_jco(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();


		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_CENSUS_FOREIGN_COUNTRY_JCO where  status = '3' and jco_id=:jco_id ";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		@SuppressWarnings("unchecked")

		List<TB_CENSUS_FOREIGN_COUNTRY_JCO> list = (List<TB_CENSUS_FOREIGN_COUNTRY_JCO>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

}

