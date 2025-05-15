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
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.update_census_data.TB_CENSUS_FIRING_STANDARD;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Fire_Std_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();
	ValidationController validation = new ValidationController();
	
	/*
	 * @RequestMapping(value = "/admin/fire_stdUrl") public ModelAndView
	 * fire_stdUrl(ModelMap Mmap, HttpSession session,
	 * 
	 * @RequestParam(value = "msg", required = false) String msg,HttpServletRequest
	 * request) { Mmap.put("msg", msg); Mmap.put("getFiring_event_qe",
	 * mcommon.getFiring_event_qe()); Mmap.put("getFiring_grade",
	 * mcommon.getFiring_grade()); return new ModelAndView("fire_stdTiles"); }
	 */

	public String yearMSG = "Year Should Contain Maximum 4 Characters";

	public Boolean checkYearLength(String yr1) {
		if (yr1.length() > 4) {
			return false;
		} else {
			return true;
		}
	}

	@RequestMapping(value = "/admin/fire_std_action", method = RequestMethod.POST)
	public @ResponseBody String fire_std_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		String username = session.getAttribute("username").toString();

		String firing_grade = request.getParameter("firing_grade");
		String ot_firing_grade = request.getParameter("ot_firing_grade");
		String firing_event_qe = request.getParameter("firing_event_qe");
		String year = request.getParameter("year");
		String firing_unit_sus_no = request.getParameter("firing_unit_sus_no");
		String unit_name = request.getParameter("firing_unit_name");
		String status = request.getParameter("status");

		String fire_id = request.getParameter("fire_id");
		String census_id = request.getParameter("census_id");
		String com_id = request.getParameter("com_id");

		Date date_year_i = null;
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		date_year_i = formatter2.parse(date1);
		SimpleDateFormat YY = new SimpleDateFormat("yyyy");
		int year2 = Integer.parseInt(YY.format(date_year_i));

		if (firing_grade == null || firing_grade.equals("-1")) {
			return "Please Select Firing Grade";
		}
		if (firing_grade.equals("OTHERS") && ot_firing_grade.equals("") || firing_grade == "OTHERS" && ot_firing_grade == "") {
			return "Please Enter Others Firing Grade";
		}
		if (ot_firing_grade != null && !ot_firing_grade.trim().equals("")) {
			if (!validation.isOnlyAlphabet(ot_firing_grade)) {
				return " Others Firing Grade " + validation.isOnlyAlphabetMSG;
			}
		    if (!validation.isvalidLength(ot_firing_grade, validation.nameMax, validation.nameMin)) {
		    	return " Others Firing Grade " + validation.isValidLengthMSG;
			}
		} 
		if (firing_event_qe == null || firing_event_qe.equals("-1")) {
			return "Please Select Firing Event Qe";
		}
		if (year == null || year.equals("")) {
			return "Please Enter Year";
		}
		 if (year != "" && year != null ) {
			 if (validation.isOnlyNumer(year) == true) {
				 return " Year " + validation.isOnlyNumerMSG;
			}
			if (!validation.YearLength(year)) {
				return validation.YearMSG ;
			}
		}
		if (Integer.parseInt(year) > year2) {
			return "Future Year cannot be allowed";
		}
		if(firing_unit_sus_no == null || firing_unit_sus_no.equals("") || firing_unit_sus_no == ""){ 
			return "Please Enter Conducted at Unit";
		}

		String msg = "";

		try {

			if (Integer.parseInt(fire_id) == 0) {
				TB_CENSUS_FIRING_STANDARD fire = new TB_CENSUS_FIRING_STANDARD();
				fire.setFiring_grade(firing_grade);
				fire.setFiring_event_qe(firing_event_qe);
				fire.setYear(Integer.parseInt(year));
				fire.setFiring_unit_sus_no(firing_unit_sus_no);
				fire.setCensus_id(Integer.parseInt(census_id));
				fire.setComm_id(new BigInteger(com_id));
				fire.setCreated_by(username);
				fire.setCreated_date(date);
				fire.setOt_firing_grade(ot_firing_grade);

				int id = (int) sessionhql.save(fire);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_CENSUS_FIRING_STANDARD set firing_grade=:firing_grade, firing_event_qe=:firing_event_qe,status =:status, "
						+ " year=:year,firing_unit_sus_no=:firing_unit_sus_no,modified_by=:modified_by,modified_date=:modified_date,ot_firing_grade=:ot_firing_grade where  id=:id ";
				Query query = sessionhql.createQuery(hql).setString("firing_grade", firing_grade)
						.setString("firing_event_qe", firing_event_qe).setInteger("year", Integer.parseInt(year))
						.setString("firing_unit_sus_no", firing_unit_sus_no).setString("modified_by", username)
						.setTimestamp("modified_date", date)

						.setInteger("id", Integer.parseInt(fire_id)).setInteger("status", 0)
						.setString("ot_firing_grade", ot_firing_grade);

				msg = query.executeUpdate() > 0 ? "update" : "0";

			}
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				
			}
			else
			{
				mcommon.update_offr_status(Integer.parseInt(census_id),username);
			}
			
			tx.commit();

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Data Not Updated Or Saved";
			} catch (RuntimeException rbe) {
				msg = "Data Not Updated Or Saved";
			}
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		// sessionhql.close();
		return msg;
	}

	// fire Details GET
	@RequestMapping(value = "/admin/getfire_detailsData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_FIRING_STANDARD> getfire_detailsData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("census_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String firing_grade = request.getParameter("firing_grade");

		String hqlUpdate = "from TB_CENSUS_FIRING_STANDARD where census_id=:census_id and comm_id=:comm_id and status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);

		@SuppressWarnings("unchecked")
		List<TB_CENSUS_FIRING_STANDARD> list = (List<TB_CENSUS_FIRING_STANDARD>) query.list();
		tx.commit();
		sessionHQL.close();

		return list;
	}

	@RequestMapping(value = "/admin/fire_delete_action", method = RequestMethod.POST)
	public @ResponseBody String fire_delete_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("fire_id"));
		try {
			String hqlUpdate = "delete from TB_CENSUS_FIRING_STANDARD where id=:id";
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

	/*--------------------- For Approval ---------------------------------*/

	public @ResponseBody List<TB_CENSUS_FIRING_STANDARD> getfire_detailsData1(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "from TB_CENSUS_FIRING_STANDARD where census_id=:census_id and comm_id=:comm_id and status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_FIRING_STANDARD> list = (List<TB_CENSUS_FIRING_STANDARD>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_FiringStandard(TB_CENSUS_FIRING_STANDARD obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		// String msg1 = "";
		try {
			/*
			 * String hql1 =
			 * "update TB_CENSUS_FIRING_STANDARD set status=:status where census_id=:census_id and comm_id=:comm_id and status != '0' "
			 * ;
			 * 
			 * Query query1 = sessionHQL.createQuery(hql1).setInteger("status",
			 * 2).setInteger("census_id", obj.getCensus_id())
			 * .setInteger("comm_id",obj.getComm_id());
			 * 
			 * msg1 = query1.executeUpdate() > 0 ? "Data Updated Successfully."
			 * :"Data Not Updated.";
			 */

			String hql = "update TB_CENSUS_FIRING_STANDARD set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
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
	/*--------------------- For REJECT ----------------------------------*/

	@RequestMapping(value = "/admin/getfirStan_Reject", method = RequestMethod.POST)
	public @ResponseBody String getfirStan_Reject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_CENSUS_FIRING_STANDARD Fir = new TB_CENSUS_FIRING_STANDARD();
		Fir.setCensus_id(Integer.parseInt(request.getParameter("ch_id")));
		Fir.setComm_id(new BigInteger(request.getParameter("comm_id")));
		Fir.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = FIRING_STANDARD_Reject(Fir, username);

		return msg1;

	}

	public String FIRING_STANDARD_Reject(TB_CENSUS_FIRING_STANDARD obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {
//			String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//
//			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
//					.setTimestamp("modified_date", new Date()).setInteger("update_ofr_status", 3)
//					.setInteger("census_id", obj.getCensus_id()).setInteger("comm_id", obj.getComm_id());
//
//			msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			String hql = "update TB_CENSUS_FIRING_STANDARD set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks   "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id());

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

	public @ResponseBody List<TB_CENSUS_FIRING_STANDARD> getfire_detailsData2(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "from TB_CENSUS_FIRING_STANDARD where census_id=:census_id and comm_id=:comm_id and status = '3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_FIRING_STANDARD> list = (List<TB_CENSUS_FIRING_STANDARD>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/getfire_detailsData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_FIRING_STANDARD> getfire_detailsData3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("census_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String firing_grade = request.getParameter("firing_grade");

		String hqlUpdate = "from TB_CENSUS_FIRING_STANDARD where census_id=:census_id and comm_id=:comm_id and status = '3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);

		@SuppressWarnings("unchecked")
		List<TB_CENSUS_FIRING_STANDARD> list = (List<TB_CENSUS_FIRING_STANDARD>) query.list();
		tx.commit();
		sessionHQL.close();

		return list;
	}
}
