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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Transaction.TB_CENSUS_FOREIGN_COUNTRY;
import com.persistance.util.HibernateUtil;



@Controller

@RequestMapping(value = { "admin", "/", "user" })



public class Foreign_ContryController {

	ValidationController validation = new ValidationController();

	@Autowired

	private Psg_CommonController com = new Psg_CommonController();



	/************************

	 * ---UPDATE FOREIGN COUNTRY VISITED START----

	 *****************************/



	// GET Foreign Country DETAILS

	@RequestMapping(value = "/admin/getUPForeginCountryData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_FOREIGN_COUNTRY> getUPForeginCountryData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("f_id"));

		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));



		String hqlUpdate = " from TB_CENSUS_FOREIGN_COUNTRY where cen_id=:cen_id and comm_id=:comm_id and status=0";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);

		List<TB_CENSUS_FOREIGN_COUNTRY> list = (List<TB_CENSUS_FOREIGN_COUNTRY>) query.list();

	

		tx.commit();

		sessionHQL.close();

		return list;

	}



	// SAVE & UPDATE Foreign Country ACTION

	@RequestMapping(value = "/admin/UPforegin_country_action", method = RequestMethod.POST)

	public @ResponseBody String UPforegin_country_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)

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

		String f_id = request.getParameter("f_id");

		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		String rvalue = "";

		if (country == null || country.equals("0")) {

			return "Please Select the Country Visited";

		}
	/*	if (country_sel.equals("Other") && !country_sel.equals("")){
			if (ot_country == null || ot_country.equals("")) {
	
				return "Please Enter Country Visited Other.";
	
			}*/
			if (ot_country != null && !ot_country.trim().equals("")) {
				if (!validation.isOnlyAlphabet(ot_country)) {
					return " Country Visited Other " + validation.isOnlyAlphabetMSG;
				}
			    if (!validation.isvalidLength(ot_country, validation.nameMax, validation.nameMin)) {
			    	return " Country Visited Other " + validation.isValidLengthMSG;
				}
			} 
		///}
		if (from_dt == null || from_dt.equals("null") || from_dt.equals("DD/MM/YYYY") || from_dt.equals("")) {

			return "Please Select the From Date";

		}
		if (from_dt != null || from_dt.trim().equals("") || from_dt.equals("DD/MM/YYYY")) {
			if (!validation.isValidDate(from_dt)) {
				return validation.isValidDateMSG + " of From ";

			} 
		}
		
		if (!from_dt.equals("") && !from_dt.equals("DD/MM/YYYY")) {

			from_date = format.parse(from_dt);

		}
		if (to_dt == null || to_dt.equals("null") || to_dt.equals("DD/MM/YYYY") || to_dt.equals("")) {

			return "Please Select the To Date";

		}
	
		if (to_dt != null || to_dt.trim().equals("") || to_dt.equals("DD/MM/YYYY")) {
			if (!validation.isValidDate(to_dt)) {
				return validation.isValidDateMSG + " of To ";
			}
		}
		if (!to_dt.equals("") && !to_dt.equals("DD/MM/YYYY")) {

			to_date = format.parse(to_dt);

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
	/*	if ( pup_sel.equals("OTHER")&& !pup_sel.equals("")){
			if (  other_purpose_visit == null || other_purpose_visit.equals("")) {
	
				return "Please Enter Purpose To Visit Other.";
	
			}*/
			if (other_purpose_visit != null && !other_purpose_visit.trim().equals("")) {
				if (!validation.isOnlyAlphabet(other_purpose_visit)) {
					return " Purpose To Visit Other " + validation.isOnlyAlphabetMSG;
				}
			    if (!validation.isvalidLength(other_purpose_visit, validation.nameMax, validation.nameMin)) {
			    	return " Purpose To Visit Other " + validation.isValidLengthMSG;
				}
			} 
		//}

		TB_CENSUS_FOREIGN_COUNTRY foregin = new TB_CENSUS_FOREIGN_COUNTRY();



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

				foregin.setCen_id(Integer.parseInt(f_id));

				foregin.setComm_id(comm_id);

				foregin.setCreated_by(username);

				foregin.setCreated_on(date);

				int id = (int) sessionhql.save(foregin);

				rvalue = Integer.toString(id);



			} else {

				// update

				/*--S---REJECT----*/

				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();

				CC.setUpdate_ofr_status(0);

				sessionhql.save(CC);*/

				/*---E--REJECT----*/



				String hql = " update TB_CENSUS_FOREIGN_COUNTRY set country=:country ,other_country=:other_country ,comm_id=:comm_id ,period=:period,from_dt=:from_dt,to_dt=:to_dt,"

						+ "purpose_visit=:purpose_visit,other_purpose_visit=:other_purpose_visit,modified_by=:modified_by,modified_date=:modified_date,status=:status"

						+ " where  id=:id";

				Query query = sessionhql.createQuery(hql)



						.setInteger("country", Integer.parseInt(request.getParameter("country")))

						.setString("other_country", ot_country)

						.setBigInteger("comm_id",new BigInteger(request.getParameter("comm_id")))

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

				com.update_offr_status(Integer.parseInt(f_id),username);

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

	@RequestMapping(value = "/admin/UPforegin_country_delete_action", method = RequestMethod.POST)

	public @ResponseBody String UPforegin_country_delete_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("foregin_country_ch_id"));

		try {

			String hqlUpdate = "delete from TB_CENSUS_FOREIGN_COUNTRY where id=:id";

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



	public @ResponseBody List<TB_CENSUS_FOREIGN_COUNTRY> getUPForeginCountryData1(int id, BigInteger comm_id) {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_CENSUS_FOREIGN_COUNTRY where cen_id=:cen_id and comm_id=:comm_id and status='0'";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);

		@SuppressWarnings("unchecked")

		List<TB_CENSUS_FOREIGN_COUNTRY> list = (List<TB_CENSUS_FOREIGN_COUNTRY>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}



	public String Update_ForeignContry(TB_CENSUS_FOREIGN_COUNTRY obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();



		String msg = "";

		String msg1 = "";

		try {

			/*

			 * String hql1 =

			 * "update TB_CENSUS_FOREIGN_COUNTRY set status=:status where cen_id=:census_id and comm_id=:comm_id and status != '0' "

			 * ;

			 * 

			 * Query query1 = sessionHQL.createQuery(hql1).setInteger("status",

			 * 2).setInteger("census_id", obj.getCen_id())

			 * .setInteger("comm_id",obj.getComm_id());

			 * 

			 * msg1 = query1.executeUpdate() > 0 ? "Data Updated Successfully."

			 * :"Data Not Updated.";

			 */



			String hql = "update TB_CENSUS_FOREIGN_COUNTRY set modified_by=:modified_by,modified_date=:modified_date,status=:status  "

					+ " where cen_id=:census_id and comm_id=:comm_id and status = '0'";



			Query query = sessionHQL.createQuery(hql)



					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())

					.setInteger("status", 1).setInteger("census_id", obj.getCen_id())

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



	/*--------------------- For REJECT ----------------------------------*/



	@RequestMapping(value = "/admin/getforeginCountry_Reject", method = RequestMethod.POST)

	public @ResponseBody String getforeginCountry_Reject(ModelMap Mmap, HttpSession session, HttpServletRequest request,

			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_CENSUS_FOREIGN_COUNTRY ChangeFore = new TB_CENSUS_FOREIGN_COUNTRY();

		ChangeFore.setCen_id(Integer.parseInt(request.getParameter("ch_id")));
		ChangeFore.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ChangeFore.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_Change_of_ForeignReject(ChangeFore, username);
		return msg1;
	}



	public String Update_Change_of_ForeignReject(TB_CENSUS_FOREIGN_COUNTRY obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";

		try {
//			String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//
//			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
//					.setTimestamp("modified_date", new Date()).setInteger("update_ofr_status", 3)
//					.setInteger("census_id", obj.getCen_id()).setInteger("comm_id", obj.getComm_id());
//			msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected. ";

			String hql = "update TB_CENSUS_FOREIGN_COUNTRY set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where cen_id=:cen_id and comm_id=:comm_id and status = '0' ";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())
					.setInteger("cen_id", obj.getCen_id()).setBigInteger("comm_id", obj.getComm_id());



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



	public @ResponseBody List<TB_CENSUS_FOREIGN_COUNTRY> getChangeOfForeignCountry2(int id, BigInteger comm_id) {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_CENSUS_FOREIGN_COUNTRY where cen_id=:cen_id and status = '3' and comm_id=:comm_id ";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);

		@SuppressWarnings("unchecked")

		List<TB_CENSUS_FOREIGN_COUNTRY> list = (List<TB_CENSUS_FOREIGN_COUNTRY>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}



	@RequestMapping(value = "/admin/get_ForeignCountry3", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_FOREIGN_COUNTRY> get_Award3(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("p_id"));

		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CENSUS_FOREIGN_COUNTRY where cen_id=:cen_id and status = '3' and comm_id=:comm_id ";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);

		@SuppressWarnings("unchecked")

		List<TB_CENSUS_FOREIGN_COUNTRY> list = (List<TB_CENSUS_FOREIGN_COUNTRY>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

}

