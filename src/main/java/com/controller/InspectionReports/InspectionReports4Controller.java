package com.controller.InspectionReports;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.InspectionReports.InspectionReportPhaseIIDao;
import com.dao.InspectionReports.InspectionReportsDao;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.model.InspectionReports.TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE;
import com.model.InspectionReports.TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE_CRITICAL_ISSUES;
import com.model.InspectionReports.TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE_UNIT;
import com.model.InspectionReports.TB_MISO_INSP_MAIN_PHASE_IV_TBL;
import com.model.InspectionReports.TB_MISO_INSP_USER_REMARKS_PHASE_II;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class InspectionReports4Controller {

	@Autowired
	RoleBaseMenuDAO roledao;

	@Autowired
	InspectionReportsDao dao;

	@Autowired
	InspectionReportPhaseIIDao dao1;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

	String currentYear = yearFormat.format(new Date());

	@RequestMapping(value = "/admin/inspection_report_4", method = RequestMethod.GET)
	public ModelAndView inspection_report_4(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("inspection_report_4", roleid);
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (val == false) {
			//					return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		List<TB_MISO_INSP_MAIN_PHASE_IV_TBL> reports = dao.getinsp_main_table_data_4(roleSusNo, currentYear);
		if (reports != null && !reports.isEmpty()) {
			TB_MISO_INSP_MAIN_PHASE_IV_TBL report = reports.get(0);
			Mmap.addAttribute("report", report);
		}

		LocalDate today = LocalDate.now();
		Mmap.addAttribute("today", today.toString());
		Mmap.put("msg", msg);

		return new ModelAndView("fitness_for_war_or_designated_role_tiles");
	}

	@RequestMapping(value = "/admin/inspection_app_report4", method = RequestMethod.GET)
	public ModelAndView inspection_app_report4(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("inspection_app_report4", roleid);

		if (val == false) {
			//		            return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleSusNo != "") {
			Mmap.put("sus_no", roleSusNo);
		}
		LocalDate today = LocalDate.now();
		List<TB_MISO_INSP_MAIN_PHASE_IV_TBL> reports = dao.getinsp_main_table_data_4(roleSusNo, currentYear);
		if (reports != null && !reports.isEmpty()) {
			TB_MISO_INSP_MAIN_PHASE_IV_TBL report = reports.get(0);
			Mmap.addAttribute("report", report);
		}

		Mmap.addAttribute("today", today.toString());
		Mmap.put("msg", msg);

		return new ModelAndView("fitness_for_war_or_designated_role_App_tiles");
	}

	@FunctionalInterface
	private interface SaveActionHandler {
		boolean execute() throws Exception;
	}

	public String formatDateToDDMMYYYY(String dateString) {
		if (dateString == null || dateString.isEmpty()) {
			return "";
		}
		String[] parts = dateString.split("-");
		if (parts.length != 3) {
			return dateString;
		}
		return parts[2] + "-" + parts[1] + "-" + parts[0];
	}

	public boolean updateMainTblData(String buttonContext, String sus_no,String roleType) {

		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_MAIN_PHASE_IV_TBL WHERE sus_no = :sus_no and year = :year");
			q.setParameter("sus_no", sus_no);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update TB_MISO_INSP_MAIN_PHASE_IV_TBL set " + buttonContext + " =:status WHERE sus_no = :sus_no and year = :year"); // Update to 0
				q1.setParameter("sus_no", sus_no);
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				int rowsAffected = q1.executeUpdate();

				success = rowsAffected > 0;

			} else {
				// Insert new record
				TB_MISO_INSP_MAIN_PHASE_IV_TBL maintbl = new TB_MISO_INSP_MAIN_PHASE_IV_TBL();
				maintbl.setSus_no(sus_no);
				maintbl.setYear(currentYear);
				maintbl.setStatus(-1);  // Initial value -1
				maintbl.setUnit_fitness_items(-1);
				maintbl.setUnit_data_items(-1);
				maintbl.setCritical_issues_items(-1);

				maintbl.setCreated_date(new Date());
				try {
					Field field = TB_MISO_INSP_MAIN_PHASE_IV_TBL.class.getDeclaredField(buttonContext);
					field.setAccessible(true);
					field.set(maintbl, 0);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					System.err.println("Error setting field " + buttonContext + ": " + e.getMessage());

				}

				ses1.save(maintbl);
				success = true;
			}

			if (success) {
				t2.commit();
			} else {
				t2.rollback();
			}

		} catch (HibernateException e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Hibernate error: " + e.getMessage());
			e.printStackTrace();
			success = false;
		} finally {
			if (ses1 != null) {
				ses1.close();
			}
		}
		return success;
	}

	//TEXT DATA SAVE
	@RequestMapping(value = "/formDataPhaseIVSaveAction", produces = "application/json")
	@ResponseBody
	public Map<String, Object> formDataPhaseIVSaveAction(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam("buttonContext") String buttonContext,
			@RequestParam(value = "inspData", required = false) String inspData) {
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		Map<String, Object> response = new HashMap<>();
		try {
			Map<String, SaveActionHandler> handlers = new HashMap<>();
			handlers.put("unit_fitness_items", () -> Unit_Fitness_itemsAction(session, request));
			handlers.put("unit_data_items", () -> Unit_data_itemAction(session, request));
			handlers.put("critical_issues_items", () -> Critical_Issues_itemsAction(session, request));

			boolean save = false;
			if (handlers.containsKey(buttonContext)) {
				save = handlers.get(buttonContext).execute();
				updateMainTblData(buttonContext,roleSusNo,roleType);
			} else {
				throw new IllegalArgumentException("Unknown button context: " + buttonContext);
			}

			String msg = roleType.equals("APP") ? "Data Approved Successfully" : "Data Saved Successfully";
			response.put("success", save);
			response.put("message", save ? msg : "Data not saved successfully");

		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "Error saving data: " + e.getMessage());
		}

		return response;
	}

	public boolean addUserRemarks(String buttonContext,String remakrs ,String sus_no,String username) {
		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			TB_MISO_INSP_USER_REMARKS_PHASE_II userremark = new TB_MISO_INSP_USER_REMARKS_PHASE_II(); //TODO
			userremark.setSus_no(sus_no);
			userremark.setYear(currentYear);
			userremark.setUser_remarks(remakrs);
			userremark.setReport_name(buttonContext);
			userremark.setCreated_by(username);;
			userremark.setCreated_date(new Date());

			ses1.save(userremark);
			success = true;


			if (success) {
				t2.commit();
			}

		} catch (HibernateException e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Hibernate error: " + e.getMessage());
			e.printStackTrace();
			success = false;
		} finally {
			if (ses1 != null) {
				ses1.close();
			}
		}
		return success;
	}

	@RequestMapping(value = "/get_fitness_for_war_or_designated_role_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_fitness_for_war_or_designated_role_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getfitness_for_war_or_designated_role_url(session, request);
		return result;
	}

	@RequestMapping(value = "/get_fitness_for_war_or_designated_role_unit_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_fitness_for_war_or_designated_role_unit_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getfitness_for_war_or_designated_role_unit_url(session, request);
		return result;
	}

	@RequestMapping(value = "/get_fitness_for_war_or_designated_role_critical_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_fitness_for_war_or_designated_role_critical_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getfitness_for_war_or_designated_role_critical_url(session, request);
		return result;
	}

	public boolean Unit_Fitness_itemsAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");
		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE \r\n"
						+ "SET sus_no = :sus_no, training_and_operational = :training_and_operational, equipment_profile = :equipment_profile, administration = :administration, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");

				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("training_and_operational", request.getParameter("strengths_training"));
				q1.setParameter("equipment_profile", request.getParameter("challenges_equipment"));
				q1.setParameter("administration", request.getParameter("admin_welfare"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status_rolewise);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status_rolewise);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;

			} else {
				TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE SA = new TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE();
				SA.setTraining_and_operational(request.getParameter("strengths_training"));
				SA.setEquipment_profile(request.getParameter("challenges_equipment"));
				SA.setAdministration(request.getParameter("admin_welfare"));
				SA.setCreated_by(username);
				SA.setCreated_date(new Date());
				SA.setStatus(status_rolewise);
				SA.setSus_no(roleSusNo);
				SA.setYear(currentYear);
				ses1.save(SA);
				success = true;
			}

			if (success) {
				t2.commit();
			}

		} catch (HibernateException e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Hibernate error: " + e.getMessage());
			e.printStackTrace();
			success = false;
		} finally {
			if (ses1 != null) {
				ses1.close();
			}
		}
		return success;
	}

	public boolean Unit_data_itemAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");
		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE_UNIT WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE_UNIT \r\n"
						+ "SET sus_no = :sus_no, fully_fit_for_war = :fit_for_war, fit_or_unfit = :unfit_reason, recommendations = :fitness_recommendations, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");

				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("fit_for_war", request.getParameter("fit_for_war"));
				q1.setParameter("unfit_reason", request.getParameter("unfit_reason"));
				q1.setParameter("fitness_recommendations", request.getParameter("fitness_recommendations"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status_rolewise);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status_rolewise);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;

			} else {
				TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE_UNIT SA = new TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE_UNIT();
				SA.setFully_fit_for_war(request.getParameter("fit_for_war"));
				SA.setFit_or_unfit(request.getParameter("unfit_reason"));
				SA.setRecommendations(request.getParameter("fitness_recommendations"));
				SA.setCreated_by(username);
				SA.setCreated_date(new Date());
				SA.setStatus(status_rolewise);
				SA.setSus_no(roleSusNo);
				SA.setYear(currentYear);
				ses1.save(SA);
				success = true;
			}

			if (success) {
				t2.commit();
			}

		} catch (HibernateException e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Hibernate error: " + e.getMessage());
			e.printStackTrace();
			success = false;
		} finally {
			if (ses1 != null) {
				ses1.close();
			}
		}
		return success;
	}

	public boolean Critical_Issues_itemsAction(HttpSession session, HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");
		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE_CRITICAL_ISSUES WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE_CRITICAL_ISSUES \r\n"
						+ "SET sus_no = :sus_no, critical_issues = :critical_issues, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");

				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("critical_issues", request.getParameter("critical_issues"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status_rolewise);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status_rolewise);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			} else {
				TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE_CRITICAL_ISSUES SA = new TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE_CRITICAL_ISSUES();
				SA.setCritical_issues(request.getParameter("critical_issues"));
				SA.setCreated_by(username);
				SA.setCreated_date(new Date());
				SA.setStatus(status_rolewise);
				SA.setSus_no(roleSusNo);
				SA.setYear(currentYear);
				ses1.save(SA);
				success = true;
			}

			if (success) {
				t2.commit();
			}

		} catch (HibernateException e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			System.err.println("Hibernate error: " + e.getMessage());
			e.printStackTrace();
			success = false;
		} finally {
			if (ses1 != null) {
				ses1.close();
			}
		}
		return success;
	}

	@RequestMapping(value = "/Download_Form_part4", method = RequestMethod.POST)
	public ModelAndView Download_Form_part4(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "downloadid", required = false) String downloadid) {
		ArrayList<ArrayList<String>> list = null;
		String roleSusNo = (String) session.getAttribute("roleSusNo");
		Map<String, String> digitalSign = dao1.getdataForDigitalDigitalSign(session,roleSusNo);
		Map<String, String> ufi = dao.ufiList(session);
		Map<String, String> udi = dao.udiList(session);
		Map<String, String> cii = dao.ciiList(session);

		String Heading = session.getAttribute("roleloginName").toString();
		return new ModelAndView(new PDF_ANUAL_REPORT_PART_4(Heading, digitalSign, ufi, udi, cii));
	}

}
