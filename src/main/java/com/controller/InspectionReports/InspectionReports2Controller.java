package com.controller.InspectionReports;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.InspectionReports.InspectionReportPhaseIIDao;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.model.InspectionReports.TB_MISO_INSP_CRITICAL_DEFI_MANPOWER;
import com.model.InspectionReports.TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL;
import com.model.InspectionReports.TB_MISO_INSP_MAIN_PHASE_II_TBL;
import com.model.InspectionReports.TB_MISO_INSP_PHASEII_TBL;
import com.model.InspectionReports.TB_MISO_INSP_STATEOFWEAPONS_EQU;
import com.model.InspectionReports.TB_MISO_INSP_USER_REMARKS_PHASE_II;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class InspectionReports2Controller {

	@Autowired
	RoleBaseMenuDAO roledao;

	@Autowired
	InspectionReportPhaseIIDao  dao;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	String currentYear = yearFormat.format(new Date());
	@RequestMapping(value = "/admin/inspection_report_2", method = RequestMethod.GET)
	public ModelAndView inpection_report_2(ModelMap Mmap, HttpSession session, HttpServletRequest request ,@RequestParam( value="msg",required=false) String msg ) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("inspection_report_2", roleid);
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (val == false) {
			//			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		List<TB_MISO_INSP_MAIN_PHASE_II_TBL> reports = dao.getinsp_main_table_data_2(roleSusNo, currentYear);
		if (reports != null && !reports.isEmpty()) {
			TB_MISO_INSP_MAIN_PHASE_II_TBL report = reports.get(0);
			Mmap.addAttribute("report", report);
		}
		LocalDate today = LocalDate.now();
		Mmap.addAttribute("today", today.toString());
		Mmap.put("msg", msg);

		return new ModelAndView("Inspection_Report_2_tiles");
	}

	@RequestMapping(value = "/admin/inspection_app_report_2", method = RequestMethod.GET)
	public ModelAndView inspection_app_report_2(ModelMap Mmap, HttpSession session, HttpServletRequest request ,@RequestParam( value="msg",required=false) String msg ) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("inspection_app_report", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(roleSusNo != "") {
			Mmap.put("sus_no", roleSusNo);
		}
		LocalDate today = LocalDate.now();
		List<TB_MISO_INSP_MAIN_PHASE_II_TBL> reports = dao.getinsp_main_table_data_2(roleSusNo, currentYear);
		if (reports != null && !reports.isEmpty()) {
			TB_MISO_INSP_MAIN_PHASE_II_TBL report = reports.get(0);
			Mmap.addAttribute("report", report);
		}

		Mmap.addAttribute("today", today.toString());
		Mmap.put("msg", msg);

		return new ModelAndView("Inspection_app_Report_2_tiles");
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

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_MAIN_PHASE_II_TBL WHERE sus_no = :sus_no and year = :year");
			q.setParameter("sus_no", sus_no);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update TB_MISO_INSP_MAIN_PHASE_II_TBL set " + buttonContext + " =:status WHERE sus_no = :sus_no and year = :year"); // Update to 0
				q1.setParameter("sus_no", sus_no);
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				int rowsAffected = q1.executeUpdate();

				success = rowsAffected > 0;

			} else {
				// Insert new record
				TB_MISO_INSP_MAIN_PHASE_II_TBL maintbl = new TB_MISO_INSP_MAIN_PHASE_II_TBL();
				maintbl.setSus_no(sus_no);
				maintbl.setYear(currentYear);
				maintbl.setStatus(-1);  // Initial value -1

				maintbl.setOp_preparedness_item(-1);
				maintbl.setTraining_item(-1);
				maintbl.setState_weapon_item(-1);
				maintbl.setState_personnel_item(-1);
				maintbl.setAdministation_item(-1);
				maintbl.setAspect_item(-1);
				maintbl.setInterior_item(-1);

				maintbl.setMajor_achievements_item(-1);
				maintbl.setStrength_of_unit_item(-1);
				maintbl.setChallenges_item(-1);
				maintbl.setImprove_following_item(-1);
				maintbl.setAttention_of_higher_item(-1);
				maintbl.setMitigation_item(-1);
				maintbl.setPoints_discussion_item(-1);

				maintbl.setCreated_date(new Date());
				try {
					Field field = TB_MISO_INSP_MAIN_PHASE_II_TBL.class.getDeclaredField(buttonContext);
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
	@RequestMapping(value = "/formDataPhaseIISaveAction", produces = "application/json")
	@ResponseBody
	public Map<String, Object> formDataPhaseIISaveAction(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam("buttonContext") String buttonContext,
			@RequestParam(value = "inspData", required = false) String inspData) {
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		Map<String, Object> response = new HashMap<>();
		try {
			Map<String, SaveActionHandler> handlers = new HashMap<>();
			handlers.put("op_preparedness_item", () -> opSaveAction(session, request));
			handlers.put("training_item", () -> trainingSaveAction(session, request));
			handlers.put("state_weapon_item", () -> state_weaponSaveAction(session, request));
			handlers.put("state_personnel_item", () -> state_personnelSaveAction(session, request));
			handlers.put("administation_item", () -> administrationSaveAction(session, request));
			handlers.put("aspect_item", () -> aspectSaveAction(session, request));
			handlers.put("interior_item", () -> interiorSaveAction(session, request));

			handlers.put("major_achievements_item", () -> majorSaveAction(session, request));
			handlers.put("strength_of_unit_item", () -> strengthSaveAction(session, request));
			handlers.put("challenges_item", () -> challengesSaveAction(session, request));
			handlers.put("improve_following_item", () -> improveSaveAction(session, request));
			handlers.put("attention_of_higher_item", () -> attentionSaveAction(session, request));
			handlers.put("mitigation_item", () -> mitigationSaveAction(session, request));
			handlers.put("points_discussion_item", () -> pointsSaveAction(session, request));

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

			TB_MISO_INSP_USER_REMARKS_PHASE_II userremark = new TB_MISO_INSP_USER_REMARKS_PHASE_II();
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

	public boolean opSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, op_preparedness_item = :op_preparedness_item, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("op_preparedness_item", request.getParameter("op"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setOp_preparedness_item(request.getParameter("op"));
				op.setCreated_by(username);;
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}

			String remarks = request.getParameter("user_remarks1");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("op_preparedness_item", remarks,roleSusNo, username);
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

	public boolean trainingSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, training_item = :training_item, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("training_item", request.getParameter("training"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setTraining_item(request.getParameter("training"));
				op.setCreated_by(username);;
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}

			String remarks = request.getParameter("user_remarks2");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("training_item", remarks,roleSusNo, username);
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

	public boolean state_weaponSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, state_weapon_item = :state_weapon_item, state_weapon_item_ii = :state_weapon_item_ii, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("state_weapon_item", request.getParameter("weaponbInput"));
				q1.setParameter("state_weapon_item_ii", request.getParameter("weaponcInput"));//state_personnel_item
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setState_weapon_item(request.getParameter("weaponbInput"));
				op.setState_weapon_item_ii(request.getParameter("weaponcInput"));
				op.setCreated_by(username);;
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}

			String remarks = request.getParameter("user_remarks3");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("state_weapon_item", remarks,roleSusNo, username);
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

	public boolean state_personnelSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, state_personnel_item = :state_personnel_item, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("state_personnel_item", request.getParameter("personnelInput"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setState_personnel_item(request.getParameter("personnelInput"));
				op.setCreated_by(username);;
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}

			String remarks = request.getParameter("user_remarks4");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("state_personnel_item", remarks,roleSusNo, username);
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

	public boolean administrationSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, administation_item = :administation_item, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("administation_item", request.getParameter("administration"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setAdministation_item(request.getParameter("administration"));
				op.setCreated_by(username);;
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}

			String remarks = request.getParameter("user_remarks5");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("administation_item", remarks,roleSusNo, username);
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

	public boolean aspectSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, aspect_item = :aspect_item, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("aspect_item", request.getParameter("aspect"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setAspect_item(request.getParameter("aspect"));
				op.setCreated_by(username);;
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}

			String remarks = request.getParameter("user_remarks6");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("aspect_item", remarks,roleSusNo, username);
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

	public boolean interiorSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, interior_item = :interior_item, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("interior_item", request.getParameter("interior"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setInterior_item(request.getParameter("interior"));
				op.setCreated_by(username);;
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}

			String remarks = request.getParameter("user_remarks7");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("interior_item", remarks,roleSusNo, username);
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

	public boolean majorSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, major_achievements_item = :major_achievements_item, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("major_achievements_item", request.getParameter("majorAchiInput"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setMajor_achievements_item(request.getParameter("majorAchiInput"));
				op.setCreated_by(username);;
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}

			String remarks = request.getParameter("user_remarks8");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("major_achievements_item", remarks,roleSusNo, username);
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

	public boolean strengthSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, strength_of_unit_item = :strength_of_unit_item, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("strength_of_unit_item", request.getParameter("strengthUnitInput"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setStrength_of_unit_item(request.getParameter("strengthUnitInput"));
				op.setCreated_by(username);;
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}

			String remarks = request.getParameter("user_remarks9");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("strength_of_unit_item", remarks,roleSusNo, username);
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

	public boolean challengesSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, challenges_item = :challenges_item, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("challenges_item", request.getParameter("challInput"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setChallenges_item(request.getParameter("challInput"));
				op.setCreated_by(username);;
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}

			String remarks = request.getParameter("user_remarks10");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("challenges_item", remarks,roleSusNo, username);
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

	public boolean improveSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, improve_following_item = :improve_following_item, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("improve_following_item", request.getParameter("inpfollInput"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setImprove_following_item(request.getParameter("inpfollInput"));
				op.setCreated_by(username);;
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}

			String remarks = request.getParameter("user_remarks11");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("improve_following_item", remarks,roleSusNo, username);
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


	public boolean attentionSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, attention_of_higher_item = :attention_of_higher_item, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("attention_of_higher_item", request.getParameter("higherInput"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setAttention_of_higher_item(request.getParameter("higherInput"));
				op.setCreated_by(username);;
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}


			String remarks = request.getParameter("user_remarks12");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("attention_of_higher_item", remarks,roleSusNo, username);
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

	public boolean mitigationSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, mitigation_item = :mitigation_item, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("mitigation_item", request.getParameter("actionInput"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setMitigation_item(request.getParameter("actionInput"));
				op.setCreated_by(username);
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}

			String remarks = request.getParameter("user_remarks13");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("mitigation_item", remarks,roleSusNo, username);
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

	public boolean pointsSaveAction(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String roleSusNo = (String) session.getAttribute("roleSusNo");
		String roleType = session.getAttribute("roleType").toString();
		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery("SELECT COUNT(*) FROM TB_MISO_INSP_PHASEII_TBL WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update FROM TB_MISO_INSP_PHASEII_TBL \r\n"
						+ "SET sus_no = :sus_no, points_discussion_item = :points_discussion_item, year = :year, status = :status, \r\n"
						+ "created_by = :cr_by, created_date = :cr_date \r\n"
						+ "WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("points_discussion_item", request.getParameter("pointsInput"));
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				q1.setParameter("cr_by", username);
				q1.setParameter("cr_date", new Date());
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", status);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();
				success = true;
			}else {
				TB_MISO_INSP_PHASEII_TBL op = new TB_MISO_INSP_PHASEII_TBL();
				op.setSus_no(roleSusNo);
				op.setYear(currentYear);
				op.setPoints_discussion_item(request.getParameter("pointsInput"));
				op.setCreated_by(username);
				op.setCreated_date(new Date());
				op.setStatus(0);
				ses1.save(op);
				success = true;
			}

			String remarks = request.getParameter("user_remarks14");
			if (!remarks.equals("") && !remarks.isEmpty()) {
				addUserRemarks("points_discussion_item", remarks,roleSusNo, username);
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


	@RequestMapping(value = "/getPhaseIITbleAddData", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getPhaseIITbleAddData( Authentication authentication,HttpServletRequest request,HttpSession session,
			@RequestParam(value = "sus_no", required = false) String sus_no, @RequestParam(value = "tblname", required = false) String tblname) {

		Map<String, Object> response = new HashMap<>();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		try {
			String normalizedTblName = tblname.replace("Delete", "");
			Map<String, Supplier<List<?>>> dataProviders = new HashMap<>();

			//			dataProviders.put("financialGrants", () -> dao.getFinancialGrantsAction(roleSusNo));

			Supplier<List<?>> dataProvider = dataProviders.get(normalizedTblName);
			if (dataProvider != null) {
				List<?> list = dataProvider.get();
				processDataAndSetResponse(list, roleSusNo, response);
			} else {
				response.put("success", false);
				response.put("message", "Unknown table type");
				response.put("data", new ArrayList<>());
			}

		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "Something went wrong");
			response.put("data", new ArrayList<>());
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void processDataAndSetResponse(List<?> list, String roleSusNo, Map<String, Object> response) {
		if (list != null && !list.isEmpty()) {
			response.put("success", true);
			response.put("data", list);
		} else {
			response.put("success", false);
			response.put("data", new ArrayList<>());
		}
	}


	//TABLE SAVES
	@RequestMapping(value = "/reportsAddMoreActionPhaseii", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> reportsAddMoreActionPhaseii(
			Authentication authentication,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam Map<String, String> allParams) {

		Map<String, Object> response = new HashMap<>();
		Session hibernateSession = null;
		Transaction transaction = null;

		try {
			hibernateSession = HibernateUtil.getSessionFactory().openSession();
			transaction = hibernateSession.beginTransaction();

			String buttonContext = allParams.get("buttonContext");
			String roleSusNo = (String) session.getAttribute("roleSusNo");
			String username = (String) session.getAttribute("username");
			String currentYear = String.valueOf(java.time.Year.now().getValue());

			if (roleSusNo == null || username == null) {
				throw new IllegalStateException("roleSusNo or username is null in session.");
			}
			switch (buttonContext) {
			case "stateWeapons":
				saveStateOfWeaponEqu(hibernateSession, allParams, roleSusNo, username,currentYear);
				break;

			case "statepersonnel":
				saveCriticalDefiManpower(hibernateSession, allParams, roleSusNo, username,currentYear);
				break;

			case "statepersonnel1":
				saveDisciplineCourtMaritalDisposal(hibernateSession, allParams, roleSusNo, username,currentYear);
				break;

			default:
				throw new IllegalArgumentException("Invalid buttonContext: " + buttonContext);
			}

			transaction.commit();
			response.put("success", true);
			response.put("message", "Data saved successfully!");
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			response.put("success", false);
			response.put("message", "Error saving data: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {
			if (hibernateSession != null) {
				hibernateSession.close();
			}
		}
	}

	private void saveStateOfWeaponEqu(Session session, Map<String, String> allParams, String roleSusNo, String username, String currentYear) {
		TB_MISO_INSP_STATEOFWEAPONS_EQU record = new TB_MISO_INSP_STATEOFWEAPONS_EQU();

		record.setNature_deficiency(allParams.get("natureDeficiency"));
		record.setAction_deficiency(allParams.get("actionDeficiency"));
		record.setEffect_conduct(allParams.get("effectConduct"));
		record.setRemarks(allParams.get("remarksSW"));

		record.setStatus("0");
		record.setYear(currentYear);
		record.setSus_no(roleSusNo);
		record.setCreated_by(username);
		record.setCreated_date(new Date());
		session.save(record);
	}

	private void saveCriticalDefiManpower(Session session, Map<String, String> allParams, String roleSusNo, String username, String currentYear) {
		TB_MISO_INSP_CRITICAL_DEFI_MANPOWER record = new TB_MISO_INSP_CRITICAL_DEFI_MANPOWER();

		record.setManpower_deficiencyoffrs(allParams.get("manpower_deficiencyoffrs"));
		record.setManpower_deficiencyjco(allParams.get("manpower_deficiencyjco"));
		record.setManpower_deficiencyor(allParams.get("manpower_deficiencyor"));
		record.setAction_taken(allParams.get("action_taken"));
		record.setTraining_effect(allParams.get("action_taken"));
		record.setAction_taken(allParams.get("training_effect"));
		record.setRemarks(allParams.get("remarkssp"));
		record.setStatus("0");
		record.setYear(currentYear);
		record.setSus_no(roleSusNo);
		record.setCreated_by(username);
		record.setCreated_date(new Date());
		session.save(record);
	}

	private void saveDisciplineCourtMaritalDisposal(Session session, Map<String, String> allParams, String roleSusNo, String username, String currentYear) {
		TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL record = new TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL();

		record.setOngoing_count(allParams.get("ongoingCount"));
		record.setPending_cases(allParams.get("pendingCases"));
		record.setCases_current(allParams.get("casesCurrent"));
		record.setRemarks(allParams.get("remarksSW1"));
		record.setStatus("0");
		record.setYear(currentYear);
		record.setSus_no(roleSusNo);
		record.setCreated_by(username);
		record.setCreated_date(new Date());
		session.save(record);
	}

	//TABLE GET
	@RequestMapping(value = "/getTbleAddDataphaseii", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getTbleAddDataphaseii( Authentication authentication,HttpServletRequest request,HttpSession session,
			@RequestParam(value = "sus_no", required = false) String sus_no, @RequestParam(value = "tblname", required = false) String tblname) {

		Map<String, Object> response = new HashMap<>();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		try {
			String normalizedTblName = tblname.replace("Delete", "");
			Map<String, Supplier<List<?>>> dataProviders = new HashMap<>();
			dataProviders.put("stateWeapons", () -> dao.getStateOfWeaponEqu(roleSusNo));
			dataProviders.put("statepersonnel", () -> dao.getCriticalDefiManpower(roleSusNo));
			dataProviders.put("statepersonnel1", () -> dao.getDisciplineCourtMaritalDisposal(roleSusNo));

			Supplier<List<?>> dataProvider = dataProviders.get(normalizedTblName);
			if (dataProvider != null) {
				List<?> list = dataProvider.get();
				processDataAndSetResponse(list, roleSusNo, response);
			} else {
				response.put("success", false);
				response.put("message", "Unknown table type");
				response.put("data", new ArrayList<>());
			}

		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "Something went wrong");
			response.put("data", new ArrayList<>());
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	//TABLE DELETE
	@RequestMapping(value = "/deleteTbleRowphaseiiAction", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteTbleRowphaseiiAction(
			Authentication authentication,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "id", required = false) int id,
			@RequestParam(value = "tblName", required = false) String tblName) {

		Map<String, Object> response = new HashMap<>();
		Session ses1 = null;
		Transaction t2 = null;

		try {

			Map<String, String> tableNameMap = new HashMap<>();
			tableNameMap.put("stateWeaponsDelete", "TB_MISO_INSP_STATEOFWEAPONS_EQU");
			tableNameMap.put("statepersonnelDelete", "TB_MISO_INSP_CRITICAL_DEFI_MANPOWER");
			tableNameMap.put("statepersonnel1Delete", "TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL");


			String tableName = tableNameMap.get(tblName);
			if (tableName == null) {
				response.put("success", false);
				response.put("message", "Unknown table type");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

			String queryString = "delete from " + tableName + " where id = :id";

			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery(queryString);
			q.setParameter("id", id);
			int rowCount = q.executeUpdate();

			t2.commit();

			boolean isDeleted = rowCount > 0;
			response.put("success", isDeleted);
			response.put("message", isDeleted ? "Data deleted Successfully" : "Data Not deleted Successfully");

		} catch (Exception e) {
			if (t2 != null && t2.isActive()) {
				t2.rollback();
			}
			response.put("success", false);
			response.put("message", "Something went wrong");
		} finally {
			if (ses1 != null) {
				ses1.close();
			}
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@RequestMapping(value = "/Download_Form_part2", method = RequestMethod.POST)
	public ModelAndView Download_Form_part2(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "downloadid", required = false) String downloadid
			) {
		ArrayList<ArrayList<String>> list =   null;
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Map<String, String> list1 = dao.getphase2data(session,roleSusNo);
		Map<String, String> digitalSign = dao.getdataForDigitalDigitalSign(session,roleSusNo);
		List<Map<String, String>> weaponDeficiencyList = dao.weaponDeficiencyList(session,roleSusNo);
		List<Map<String, String>> getciricaldefimanpowerlist = dao.getciricaldefimanpowerlist(session,roleSusNo);
		List<Map<String, String>> discipline = dao.getDisciplineCourtMaritalDisposallist(session,roleSusNo);


		String Heading = session.getAttribute("roleloginName").toString();
		return new ModelAndView(new PDF_ANUAL_REPORT_PART_2(Heading, list1, digitalSign, weaponDeficiencyList, getciricaldefimanpowerlist, discipline));
	}

	//TEXT DATA GET
	@RequestMapping(value = "/op_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> op_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getOpData(session, request);
		return result;
	}

	@RequestMapping(value = "/training_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> training_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getTrainingData(session, request);
		return result;
	}

	@RequestMapping(value = "/weap_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> weap_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getWeapData(session, request);
		return result;
	}

	@RequestMapping(value = "/pers_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> pers_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getPersData(session, request);
		return result;
	}

	@RequestMapping(value = "/admin_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> admin_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getAdminData(session, request);
		return result;
	}

	@RequestMapping(value = "/aspect_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> aspect_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getAspectData(session, request);
		return result;
	}

	@RequestMapping(value = "/inte_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> inte_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getInteData(session, request);
		return result;
	}

	@RequestMapping(value = "/major_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> major_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getMajorData(session, request);
		return result;
	}

	@RequestMapping(value = "/strength_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> strength_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getStrengthData(session, request);
		return result;
	}

	@RequestMapping(value = "/challenge_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> challenge_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getChallengesData(session, request);
		return result;
	}

	@RequestMapping(value = "/improve_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> improve_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getImproveData(session, request);
		return result;
	}

	@RequestMapping(value = "/attention_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> attention_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getAttentionData(session, request);
		return result;
	}

	@RequestMapping(value = "/mitigation_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> mitigation_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getMitigationData(session, request);
		return result;
	}

	@RequestMapping(value = "/points_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> points_url(String unit_no, HttpSession session,
			HttpServletRequest request) {
		List<Map<String, Object>> result = dao.getPointsData(session, request);
		return result;
	}
}
