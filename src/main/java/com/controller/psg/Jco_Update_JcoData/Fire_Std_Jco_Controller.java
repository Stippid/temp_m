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
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FIRING_STANDARD_JCO;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Fire_Std_Jco_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	ValidationController valid = new ValidationController();
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

	@RequestMapping(value = "/admin/fire_std_action_jco", method = RequestMethod.POST)
	public @ResponseBody String fire_std_action_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request)
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
		String jco_id = request.getParameter("jco_id");

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
		if (firing_event_qe == null || firing_event_qe.equals("-1")) {
			return "Please Select Firing Event Qe";
		}
		if (year == null || year.equals("")) {
			return "Please Enter Year";
		}
		if (year != null || !year.trim().equals("")) {
			 if (valid.isOnlyNumer(year) == true) {
					return " Year " + valid.isOnlyNumerMSG ;
				}
				if (!valid.YearLength(year)) {
					return valid.YearMSG;
				}
			}
		if (Integer.parseInt(year) > year2) {
			return "Future Year cannot be allowed";
		}
		
		if(firing_unit_sus_no == null || firing_unit_sus_no.equals("") || firing_unit_sus_no == ""){ 
			return "Please Enter Conducted at Unit";
		}
		if (firing_unit_sus_no != "") {
			if (!valid.isUnit(firing_unit_sus_no)) {
				return "Conducted at Unit " + valid.isUnitMSG;

			}
			if (!valid.isvalidLength(firing_unit_sus_no, valid.nameMax, valid.nameMin)) {
				return " Conducted at Unit " + valid.isValidLengthMSG;

			}
		}
		String msg = "";

		try {

			if (Integer.parseInt(fire_id) == 0) {
				TB_CENSUS_FIRING_STANDARD_JCO fire = new TB_CENSUS_FIRING_STANDARD_JCO();
				fire.setFiring_grade(firing_grade);
				fire.setFiring_event_qe(firing_event_qe);
				fire.setYear(Integer.parseInt(year));
				fire.setFiring_unit_sus_no(firing_unit_sus_no);
				fire.setJco_id(Integer.parseInt(jco_id));
				fire.setCreated_by(username);
				fire.setCreated_date(date);
				fire.setOt_firing_grade(ot_firing_grade);
				fire.setInitiated_from("u");

				int id = (int) sessionhql.save(fire);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_CENSUS_FIRING_STANDARD_JCO set firing_grade=:firing_grade, firing_event_qe=:firing_event_qe,status =:status, "
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
				pjc.update_JcoOr_status(Integer.parseInt(jco_id),username);
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
	@RequestMapping(value = "/admin/getfire_detailsData_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_FIRING_STANDARD_JCO> getfire_detailsData_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String firing_grade = request.getParameter("firing_grade");

		String hqlUpdate = "from TB_CENSUS_FIRING_STANDARD_JCO where jco_id=:jco_id and status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		@SuppressWarnings("unchecked")
		List<TB_CENSUS_FIRING_STANDARD_JCO> list = (List<TB_CENSUS_FIRING_STANDARD_JCO>) query.list();
		tx.commit();
		sessionHQL.close();

		return list;
	}

	@RequestMapping(value = "/admin/fire_delete_action_jco", method = RequestMethod.POST)
	public @ResponseBody String fire_delete_action_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("fire_id"));
		try {
			String hqlUpdate = "delete from TB_CENSUS_FIRING_STANDARD_JCO where id=:id";
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

	public @ResponseBody List<TB_CENSUS_FIRING_STANDARD_JCO> getfire_detailsData1( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "from TB_CENSUS_FIRING_STANDARD_JCO where  jco_id=:jco_id and status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_FIRING_STANDARD_JCO> list = (List<TB_CENSUS_FIRING_STANDARD_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_FiringStandard(TB_CENSUS_FIRING_STANDARD_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		// String msg1 = "";
		try {
			/*
			 * String hql1 =
			 * "update TB_CENSUS_FIRING_STANDARD_JCO set status=:status where  jco_id=:jco_id and status != '0' "
			 * ;
			 * 
			 * Query query1 = sessionHQL.createQuery(hql1).setInteger("status",
			 * 2).setInteger("census_id", obj.getCensus_id())
			 * .setInteger("jco_id",obj.getComm_id());
			 * 
			 * msg1 = query1.executeUpdate() > 0 ? "Data Updated Successfully."
			 * :"Data Not Updated.";
			 */

			String hql = "update TB_CENSUS_FIRING_STANDARD_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
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

	@RequestMapping(value = "/admin/getfirStan_Reject_jco", method = RequestMethod.POST)
	public @ResponseBody String getfirStan_Reject_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String reject_remarks = request.getParameter("reject_remarks");

		

		String username = session.getAttribute("username").toString();
		TB_CENSUS_FIRING_STANDARD_JCO Fir = new TB_CENSUS_FIRING_STANDARD_JCO();
		Fir.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		Fir.setReject_remarks(reject_remarks);
		String msg1 = FIRING_STANDARD_Reject(Fir, username);

		return msg1;

	}

	public String FIRING_STANDARD_Reject(TB_CENSUS_FIRING_STANDARD_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {


			String hql = "update TB_CENSUS_FIRING_STANDARD_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where  jco_id=:jco_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())
					.setInteger("jco_id", obj.getJco_id());

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

	public @ResponseBody List<TB_CENSUS_FIRING_STANDARD_JCO> getfire_detailsData2( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "from TB_CENSUS_FIRING_STANDARD_JCO where  jco_id=:jco_id and status = '3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_FIRING_STANDARD_JCO> list = (List<TB_CENSUS_FIRING_STANDARD_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/getfire_detailsData3_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_FIRING_STANDARD_JCO> getfire_detailsData3_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String firing_grade = request.getParameter("firing_grade");

		String hqlUpdate = "from TB_CENSUS_FIRING_STANDARD_JCO where  jco_id=:jco_id and status = '3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		@SuppressWarnings("unchecked")
		List<TB_CENSUS_FIRING_STANDARD_JCO> list = (List<TB_CENSUS_FIRING_STANDARD_JCO>) query.list();
		tx.commit();
		sessionHQL.close();

		return list;
	}
}
