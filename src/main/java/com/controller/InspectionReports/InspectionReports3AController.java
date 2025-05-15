package com.controller.InspectionReports;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.ParseException;
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
import org.hibernate.SQLQuery;
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
import com.dao.InspectionReports.InspectionReportsDao;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.model.InspectionReports.TB_MISO_INSP_ADDITIONAL_INFORMATION;
import com.model.InspectionReports.TB_MISO_INSP_ARMY_CORE_VALUES;
import com.model.InspectionReports.TB_MISO_INSP_COLLECTIVE_TRAINING;
import com.model.InspectionReports.TB_MISO_INSP_COMMENTS_ON_PARTII;
import com.model.InspectionReports.TB_MISO_INSP_HUMAN_RESOURCE_DEVELOPEMENT;
import com.model.InspectionReports.TB_MISO_INSP_INDIVIDUAL_TRAINING;
import com.model.InspectionReports.TB_MISO_INSP_INTERIOR_ECONOMY;
import com.model.InspectionReports.TB_MISO_INSP_MAIN_PHASE_III_TBL;
import com.model.InspectionReports.TB_MISO_INSP_MAIN_TBL;
import com.model.InspectionReports.TB_MISO_INSP_MANAGEMENT_TRAINING;
import com.model.InspectionReports.TB_MISO_INSP_MATERIAL_MANAGEMENT;
import com.model.InspectionReports.TB_MISO_INSP_MORALE_MOTIVATION;
import com.model.InspectionReports.TB_MISO_INSP_OTHER_MISCELLANEOUS_ASPECTS;
import com.model.InspectionReports.TB_MISO_INSP_PERSONNEL_MANAGEMENT;
import com.model.InspectionReports.TB_MISO_INSP_STR_CHALLENGES;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class InspectionReports3AController {

	@Autowired
	RoleBaseMenuDAO roledao;

	@Autowired
	InspectionReportsDao dao;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	String currentYear = yearFormat.format(new Date());

//	@RequestMapping(value = "/admin/inspection_report3A", method = RequestMethod.GET)
//	public ModelAndView inspection_report3A(ModelMap Mmap, HttpSession session, HttpServletRequest request,
//			@RequestParam(value = "msg", required = false) String msg) {
//		String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("inspection_report3A", roleid);
//		String roleSusNo = session.getAttribute("roleSusNo").toString();
////		if (val == false) {
////			return new ModelAndView("AccessTiles");
////		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//		}
//
//		List<TB_MISO_INSP_MAIN_PHASE_III_TBL> reports = dao.getinsp_main_table_data_phaseiii(roleSusNo, currentYear);
//		if (reports != null && !reports.isEmpty()) {
//			TB_MISO_INSP_MAIN_PHASE_III_TBL report = reports.get(0);
//			Mmap.addAttribute("report", report);
//		}
//		LocalDate today = LocalDate.now();
//		Mmap.addAttribute("today", today.toString());
//		Mmap.put("msg", msg);
//
//		return new ModelAndView("Inspection_Report3A_tiles");
//	}

	@RequestMapping(value = "/admin/inspection_report3A", method = RequestMethod.POST)
	public ModelAndView inspection_report3APost(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "susno1", required = false) String roleSusNo) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("inspection_report3A", roleid);
		//String roleSusNo = session.getAttribute("roleSusNo").toString();
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		List<TB_MISO_INSP_MAIN_PHASE_III_TBL> reports = dao.getinsp_main_table_data_phaseiii(roleSusNo, currentYear);
		if (reports != null && !reports.isEmpty()) {
			TB_MISO_INSP_MAIN_PHASE_III_TBL report = reports.get(0);
			Mmap.addAttribute("report", report);
		}
		LocalDate today = LocalDate.now();
		Mmap.addAttribute("today", today.toString());
		Mmap.put("msg", msg);
		Mmap.put("sus_no", roleSusNo);

		return new ModelAndView("Inspection_Report3A_tiles");
	}
	
	
	
	public boolean updateMainTblPaheiiData(String buttonContext, String sus_no, String roleType) {

		Integer status = roleType.equals("APP") ? 1 : (roleType.equals("DEO") ? 0 : -1);

		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_MAIN_PHASE_III_TBL WHERE sus_no = :sus_no and year = :year");
			q.setParameter("sus_no", sus_no);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery("update TB_MISO_INSP_MAIN_PHASE_III_TBL set " + buttonContext
						+ " =:status WHERE sus_no = :sus_no and year = :year"); // Update to 0
				q1.setParameter("sus_no", sus_no);
				q1.setParameter("year", currentYear);
				q1.setParameter("status", status);
				int rowsAffected = q1.executeUpdate();

				success = rowsAffected > 0;

			} else {
				// Insert new record
				TB_MISO_INSP_MAIN_PHASE_III_TBL maintbl = new TB_MISO_INSP_MAIN_PHASE_III_TBL();
				maintbl.setSus_no(sus_no);
				maintbl.setYear(currentYear);
				maintbl.setStatus(-1); // Initial value -1

				maintbl.setIndividual_training(-1);
				;
				maintbl.setCollective_training(-1);
				maintbl.setManagement_training(-1);
				maintbl.setPersonnel_management(-1);
				maintbl.setInterior_economy(-1);
				maintbl.setMorale_motivation(-1);
				maintbl.setMaterial_management(-1);

				maintbl.setOther_miscellaneous_aspects(-1);
				maintbl.setMeasures_core_values(-1);
				maintbl.setHuman_resource_developement(-1);
				maintbl.setAdditional_information(-1);
				maintbl.setComments_insp_offr(-1);
				maintbl.setOverall_strg_chall(-1);

				maintbl.setCreated_date(new Date());
				try {
					Field field = TB_MISO_INSP_MAIN_PHASE_III_TBL.class.getDeclaredField(buttonContext);
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

////////////////////////////   Individual Training ///////////////

	@RequestMapping(value = "/admin/Change_of_Individual_training", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Individual_training(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		Date date = new Date();

		String username = session.getAttribute("username").toString();
		String roleSusNo = request.getParameter("sus_no");
		String roleType = session.getAttribute("roleType").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String weaponTrainingRating = request.getParameter("weaponTrainingRating");
		String physicalTrainingRating = request.getParameter("physicalTrainingRating");
		String weaponTrainingResultsRating = request.getParameter("weaponTrainingResultsRating");
		String nightTrainingRating = request.getParameter("nightTrainingRating");
		String specialistTrainingRating = request.getParameter("specialistTrainingRating");
		String yosTrainingRating = request.getParameter("yosTrainingRating");
		String officersTrainingRating = request.getParameter("officersTrainingRating");
		String jcoNcoTrainingRating = request.getParameter("jcoNcoTrainingRating");
		String accCommissionTrainingRating = request.getParameter("accCommissionTrainingRating");
//			String ind_training_remarks = request.getParameter("ind_training_remarks");

		String msg = "";
		try {
			Query q0 = sessionhql.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_INDIVIDUAL_TRAINING WHERE sus_no = :sus_no and status =:status and year =:year")
					.setParameter("sus_no", roleSusNo).setParameter("status", 0).setParameter("year", currentYear);
			Long count = (Long) q0.uniqueResult();

			if (count > 0) {
				Query q1 = sessionhql.createQuery(
						"delete FROM TB_MISO_INSP_INDIVIDUAL_TRAINING WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}
			TB_MISO_INSP_INDIVIDUAL_TRAINING cr = new TB_MISO_INSP_INDIVIDUAL_TRAINING();

			cr.setYear(currentYear);

			cr.setPhysical_training(physicalTrainingRating);
			cr.setWeapon_training(weaponTrainingRating);
			cr.setWeapon_training_results(weaponTrainingResultsRating);
			cr.setNight_training(nightTrainingRating);
			cr.setSpecialist_training(specialistTrainingRating);
			cr.setYos_training(yosTrainingRating);
			cr.setOfficers_training(officersTrainingRating);
			cr.setTraining_jco_nco(jcoNcoTrainingRating);
			cr.setTraining_acc_sco(accCommissionTrainingRating);

			cr.setCreated_by(username);
			cr.setSus_no(roleSusNo);
			cr.setCreated_date(date);
			cr.setStatus(0);

			int id = (int) sessionhql.save(cr);
			if (roleType.equals("APP")) {
				msg = "approve";
			}

			updateMainTblPaheiiData("individual_training", roleSusNo, roleType);
			msg = Integer.toString(id);
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Error: " + e.getMessage(); // More descriptive error message
			} catch (RuntimeException rbe) {
				msg = "Error during rollback";
			}
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}

////////////////////////////  Collective Training  //////////////////////////////

	@RequestMapping(value = "/admin/Change_of_Collective_training", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Collective_training(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		Date date = new Date();

		String username = session.getAttribute("username").toString();
		String roleSusNo = request.getParameter("sus_no");
		String roleType = session.getAttribute("roleType").toString();
	
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String exerciseTrainingRating = request.getParameter("exerciseTrainingRating");
		String fieldfiringRating = request.getParameter("fieldfiringRating");
		String mobilisationRating = request.getParameter("mobilisationRating");

		String msg = "";

		try {
			Query q0 = sessionhql.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_COLLECTIVE_TRAINING WHERE sus_no = :sus_no and status =:status and year =:year")
					.setParameter("sus_no", roleSusNo).setParameter("status", 0).setParameter("year", currentYear);
			Long count = (Long) q0.uniqueResult();

			if (count > 0) {
				Query q1 = sessionhql.createQuery(
						"delete FROM TB_MISO_INSP_COLLECTIVE_TRAINING WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}
			TB_MISO_INSP_COLLECTIVE_TRAINING cr = new TB_MISO_INSP_COLLECTIVE_TRAINING();

			int currentYear = LocalDate.now().getYear();
			String currentYearString = String.valueOf(currentYear);
			cr.setYear(currentYearString);

			cr.setTraining_exercise(exerciseTrainingRating);
			cr.setField_firing(fieldfiringRating);
			cr.setMobilisation(mobilisationRating);

			cr.setCreated_by(username);
			cr.setSus_no(roleSusNo);
			cr.setCreated_date(date);
			cr.setStatus(0);

			int id = (int) sessionhql.save(cr);
			updateMainTblPaheiiData("collective_training", roleSusNo, roleType);
			msg = Integer.toString(id);
			if (roleType.equals("APP")) {
				msg = "approve";
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Error: " + e.getMessage(); // More descriptive error message
			} catch (RuntimeException rbe) {
				msg = "Error during rollback";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}

////////////////////////////  Management of Training  //////////////////////////////

	@RequestMapping(value = "/admin/Change_of_Management_Training", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Management_Training(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		Date date = new Date();

		String username = session.getAttribute("username").toString();
		String roleSusNo = request.getParameter("sus_no");
		String roleType = session.getAttribute("roleType").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String trainingAidsRating = request.getParameter("trainingAidsRating");
		String special_points = request.getParameter("special_points");

		String msg = "";
		try {
			Query q0 = sessionhql.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_MANAGEMENT_TRAINING WHERE sus_no = :sus_no and status =:status and year =:year")
					.setParameter("sus_no", roleSusNo).setParameter("status", 0).setParameter("year", currentYear);
			Long count = (Long) q0.uniqueResult();

			if (count > 0) {
				Query q1 = sessionhql.createQuery(
						"delete FROM TB_MISO_INSP_MANAGEMENT_TRAINING WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}
			TB_MISO_INSP_MANAGEMENT_TRAINING cr = new TB_MISO_INSP_MANAGEMENT_TRAINING();

			int currentYear = LocalDate.now().getYear();
			String currentYearString = String.valueOf(currentYear);
			cr.setYear(currentYearString);

			cr.setTraining_infrastructure(trainingAidsRating);
			cr.setSpecial_points(special_points);

			cr.setCreated_by(username);
			cr.setSus_no(roleSusNo);
			cr.setCreated_date(date);
			cr.setStatus(0);

			int id = (int) sessionhql.save(cr);
			updateMainTblPaheiiData("management_training", roleSusNo, roleType);
			msg = Integer.toString(id);
			if (roleType.equals("APP")) {
				msg = "approve";
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Error: " + e.getMessage(); // More descriptive error message
			} catch (RuntimeException rbe) {
				msg = "Error during rollback";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}

////////////////////////////   Personnel Management  //////////////////////////////

	@RequestMapping(value = "/admin/Change_of_Personnel_Management", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Personnel_Management(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		Date date = new Date();

		String username = session.getAttribute("username").toString();
		String roleSusNo = request.getParameter("sus_no");
		String roleType = session.getAttribute("roleType").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		String disciplineRating = request.getParameter("disciplineRating");
		String HealthTroopsRating = request.getParameter("HealthTroopsRating");
		String personalDocumentationRating = request.getParameter("personalDocumentationRating");

		String msg = "";
		try {
			Query q0 = sessionhql.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_PERSONNEL_MANAGEMENT WHERE sus_no = :sus_no and status =:status and year =:year")
					.setParameter("sus_no", roleSusNo).setParameter("status", 0).setParameter("year", currentYear);
			Long count = (Long) q0.uniqueResult();

			if (count > 0) {
				Query q1 = sessionhql.createQuery(
						"delete FROM TB_MISO_INSP_PERSONNEL_MANAGEMENT WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}
			TB_MISO_INSP_PERSONNEL_MANAGEMENT cr = new TB_MISO_INSP_PERSONNEL_MANAGEMENT();

			int currentYear = LocalDate.now().getYear();
			String currentYearString = String.valueOf(currentYear);
			cr.setYear(currentYearString);

			cr.setDiscipline(disciplineRating);
			cr.setHealth_troops(HealthTroopsRating);
			cr.setPersonal_documentation(personalDocumentationRating);

			cr.setCreated_by(username);
			cr.setSus_no(roleSusNo);
			cr.setCreated_date(date);
			cr.setStatus(0);

			int id = (int) sessionhql.save(cr);
			updateMainTblPaheiiData("personnel_management", roleSusNo, roleType);
			msg = Integer.toString(id);
			if (roleType.equals("APP")) {
				msg = "approve";
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Error: " + e.getMessage(); // More descriptive error message
			} catch (RuntimeException rbe) {
				msg = "Error during rollback";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}

//////////////////////////// Interior Economy  //////////////////////////////

	@RequestMapping(value = "/admin/Change_of_Interior_Economy", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Interior_Economy(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		String roleSusNo = request.getParameter("sus_no");
		String roleType = session.getAttribute("roleType").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		String livingConditionRating = request.getParameter("livingConditionRating");
		String personalClothingRating = request.getParameter("personalClothingRating");
		String livingHabitatRating = request.getParameter("livingHabitatRating");
		String facilitiesRating = request.getParameter("facilitiesRating");
		String modernEquipmentRating = request.getParameter("modernEquipmentRating");
		String EquipmentRating = request.getParameter("EquipmentRating");
		String timelyCorrectRating = request.getParameter("timelyCorrectRating");
		String individualDocumentationRating = request.getParameter("individualDocumentationRating");

		String msg = "";
		try {
			Query q0 = sessionhql.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_INTERIOR_ECONOMY WHERE sus_no = :sus_no and status =:status and year =:year")
					.setParameter("sus_no", roleSusNo).setParameter("status", 0).setParameter("year", currentYear);
			Long count = (Long) q0.uniqueResult();

			if (count > 0) {
				Query q1 = sessionhql.createQuery(
						"delete FROM TB_MISO_INSP_INTERIOR_ECONOMY WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}
			TB_MISO_INSP_INTERIOR_ECONOMY cr = new TB_MISO_INSP_INTERIOR_ECONOMY();

			int currentYear = LocalDate.now().getYear();
			String currentYearString = String.valueOf(currentYear);
			cr.setYear(currentYearString);

			cr.setLiving_condition(livingConditionRating);
			cr.setState_personal_clothing(personalClothingRating);
			cr.setInitiative_units(livingHabitatRating);
			cr.setFacilities_living_area(facilitiesRating);
			cr.setModern_equipment_Company(modernEquipmentRating);
			cr.setEquipment_procured_manpower(EquipmentRating);
			cr.setTimely_correct_Publication(timelyCorrectRating);
			cr.setState_documentation(individualDocumentationRating);

			cr.setCreated_by(username);
			cr.setSus_no(roleSusNo);
			cr.setCreated_date(date);
			cr.setStatus(0);

			int id = (int) sessionhql.save(cr);
			updateMainTblPaheiiData("interior_economy", roleSusNo, roleType);
			msg = Integer.toString(id);
			if (roleType.equals("APP")) {
				msg = "approve";
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Error: " + e.getMessage(); // More descriptive error message
			} catch (RuntimeException rbe) {
				msg = "Error during rollback";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}
//////////////////////////////////////  Morale and Motivation  ///////////////////////////////////

	@RequestMapping(value = "/admin/Change_of_Morale_Motivation", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Morale_Motivation(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		String roleSusNo = request.getParameter("sus_no");
		String roleType = session.getAttribute("roleType").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		String stateLeaveRating = request.getParameter("stateLeaveRating");
		String disciplineStateRating = request.getParameter("disciplineStateRating");
		String stateSickReportRating = request.getParameter("stateSickReportRating");
		String PerformanceTrgRating = request.getParameter("PerformanceTrgRating");
		String performanceCourseRating = request.getParameter("performanceCourseRating");
		String performanceSportsRating = request.getParameter("performanceSportsRating");
		String stateEqptMaintRating = request.getParameter("stateEqptMaintRating");
		String interiorEconomyRating = request.getParameter("interiorEconomyRating");
		String documentationRating = request.getParameter("documentationRating");
		String involvementRating = request.getParameter("involvementRating");
		String regimentalRating = request.getParameter("regimentalRating");
		String persDisciplineRating = request.getParameter("persDisciplineRating");

		String msg = "";
		try {
			Query q0 = sessionhql.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_MORALE_MOTIVATION WHERE sus_no = :sus_no and status =:status and year =:year")
					.setParameter("sus_no", roleSusNo).setParameter("status", 0).setParameter("year", currentYear);
			Long count = (Long) q0.uniqueResult();

			if (count > 0) {
				Query q1 = sessionhql.createQuery(
						"delete FROM TB_MISO_INSP_MORALE_MOTIVATION WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}
			TB_MISO_INSP_MORALE_MOTIVATION cr = new TB_MISO_INSP_MORALE_MOTIVATION();

			int currentYear = LocalDate.now().getYear();
			String currentYearString = String.valueOf(currentYear);
			cr.setYear(currentYearString);

			cr.setState_leave(stateLeaveRating);
			cr.setDiscipline_state(disciplineStateRating);
			cr.setSick_report(stateSickReportRating);
			cr.setPerformance_trg_professional(PerformanceTrgRating);
			cr.setPerformance_course(performanceCourseRating);
			cr.setPerformance_sports(performanceSportsRating);
			cr.setState_eqpt_maint(stateEqptMaintRating);
			cr.setInterior_economy(interiorEconomyRating);
			cr.setDocumentation(documentationRating);
			cr.setInvolvement_junior_leader(involvementRating);
			cr.setState_regimental(regimentalRating);
			cr.setPers_discipline(persDisciplineRating);

			cr.setCreated_by(username);
			cr.setSus_no(roleSusNo);
			cr.setCreated_date(date);
			cr.setStatus(0);

			int id = (int) sessionhql.save(cr);
			updateMainTblPaheiiData("morale_motivation", roleSusNo, roleType);
			msg = Integer.toString(id);
			if (roleType.equals("APP")) {
				msg = "approve";
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Error: " + e.getMessage(); // More descriptive error message
			} catch (RuntimeException rbe) {
				msg = "Error during rollback";
			}
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}

//////////////////////////////Material Management ///////////////////////////////			

	@RequestMapping(value = "/admin/Change_of_Material_Management", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Material_Management(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		Date date = new Date();

		String username = session.getAttribute("username").toString();
		String roleSusNo = request.getParameter("sus_no");
		String roleType = session.getAttribute("roleType").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		String VehiclesRating = request.getParameter("VehiclesRating");
		String eqptIncludingRating = request.getParameter("eqptIncludingRating");
		String maintenanceArmsRating = request.getParameter("maintenanceArmsRating");
		String maintenanceAmnRating = request.getParameter("maintenanceAmnRating");
		String maintenanceOrdnanceStoresRating = request.getParameter("maintenanceOrdnanceStoresRating");
		String PublicFundsRating = request.getParameter("PublicFundsRating");

		String msg = "";
		try {
			Query q0 = sessionhql.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_MATERIAL_MANAGEMENT WHERE sus_no = :sus_no and status =:status and year =:year")
					.setParameter("sus_no", roleSusNo).setParameter("status", 0).setParameter("year", currentYear);
			Long count = (Long) q0.uniqueResult();

			if (count > 0) {
				Query q1 = sessionhql.createQuery(
						"delete FROM TB_MISO_INSP_MATERIAL_MANAGEMENT WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}
			TB_MISO_INSP_MATERIAL_MANAGEMENT cr = new TB_MISO_INSP_MATERIAL_MANAGEMENT();

			int currentYear = LocalDate.now().getYear();
			String currentYearString = String.valueOf(currentYear);
			cr.setYear(currentYearString);

			cr.setVehicles(VehiclesRating);
			cr.setEqpt_six_months(eqptIncludingRating);
			cr.setMaintenance_arms(maintenanceArmsRating);
			cr.setMaintenance_amn(maintenanceAmnRating);
			cr.setMaintenance_ordnance_stores(maintenanceOrdnanceStoresRating);
			cr.setManagement_public_funds(PublicFundsRating);

			cr.setCreated_by(username);
			cr.setSus_no(roleSusNo);
			cr.setCreated_date(date);
			cr.setStatus(0);

			int id = (int) sessionhql.save(cr);
			updateMainTblPaheiiData("material_management", roleSusNo, roleType);
			msg = Integer.toString(id);
			if (roleType.equals("APP")) {
				msg = "approve";
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Error: " + e.getMessage(); // More descriptive error message
			} catch (RuntimeException rbe) {
				msg = "Error during rollback";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}

//////////////////////////////Other Miscellaneous Aspects ///////////////////////////////	

	@RequestMapping(value = "/admin/Change_of_Other_Miscellaneous_Aspects", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Other_Miscellaneous_Aspects(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		Date date = new Date();

		String username = session.getAttribute("username").toString();
		String roleSusNo = request.getParameter("sus_no");
		String roleType = session.getAttribute("roleType").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		String SecurityRating = request.getParameter("SecurityRating");

		String msg = "";
		try {
			Query q0 = sessionhql.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_OTHER_MISCELLANEOUS_ASPECTS WHERE sus_no = :sus_no and status =:status and year =:year")
					.setParameter("sus_no", roleSusNo).setParameter("status", 0).setParameter("year", currentYear);
			Long count = (Long) q0.uniqueResult();

			if (count > 0) {
				Query q1 = sessionhql.createQuery(
						"delete FROM TB_MISO_INSP_OTHER_MISCELLANEOUS_ASPECTS WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}
			TB_MISO_INSP_OTHER_MISCELLANEOUS_ASPECTS cr = new TB_MISO_INSP_OTHER_MISCELLANEOUS_ASPECTS();

			int currentYear = LocalDate.now().getYear();
			String currentYearString = String.valueOf(currentYear);
			cr.setYear(currentYearString);
			cr.setSecurity(SecurityRating);
			cr.setCreated_by(username);
			cr.setSus_no(roleSusNo);
			cr.setCreated_date(date);
			cr.setStatus(0);

			int id = (int) sessionhql.save(cr);
			updateMainTblPaheiiData("other_miscellaneous_aspects", roleSusNo, roleType);
			msg = Integer.toString(id);
			if (roleType.equals("APP")) {
				msg = "approve";
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Error: " + e.getMessage(); // More descriptive error message
			} catch (RuntimeException rbe) {
				msg = "Error during rollback";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}

////////////////////////////// Measures taken to imbibe the following Indian Army Core Values ///////////////////////////////	

	@RequestMapping(value = "/admin/Change_of_Measures_Taken", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Measures_Taken(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Date date = new Date();

		String username = session.getAttribute("username").toString();
		String roleSusNo = request.getParameter("sus_no");
		String roleType = session.getAttribute("roleType").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		String integrityRating = request.getParameter("integrityRating");
		String loyaltyRating = request.getParameter("loyaltyRating");
		String dutyRating = request.getParameter("dutyRating");
		String respectRating = request.getParameter("respectRating");
		String selflessRating = request.getParameter("selflessRating");
		String courageRating = request.getParameter("courageRating");
		String honourRating = request.getParameter("honourRating");

		String msg = "";
		try {
			Query q0 = sessionhql.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_ARMY_CORE_VALUES WHERE sus_no = :sus_no and status =:status and year =:year")
					.setParameter("sus_no", roleSusNo).setParameter("status", 0).setParameter("year", currentYear);
			Long count = (Long) q0.uniqueResult();

			if (count > 0) {
				Query q1 = sessionhql.createQuery(
						"delete FROM TB_MISO_INSP_ARMY_CORE_VALUES WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}
			TB_MISO_INSP_ARMY_CORE_VALUES cr = new TB_MISO_INSP_ARMY_CORE_VALUES();

			int currentYear = LocalDate.now().getYear();
			String currentYearString = String.valueOf(currentYear);
			cr.setYear(currentYearString);

			cr.setIntegrity(integrityRating);
			cr.setLoyalty(loyaltyRating);
			cr.setDuty(dutyRating);
			cr.setRespect(respectRating);
			cr.setSelfless_service(selflessRating);
			cr.setCourage(courageRating);
			cr.setHonour(honourRating);

			cr.setCreated_by(username);
			cr.setSus_no(roleSusNo);
			cr.setCreated_date(date);
			cr.setStatus(0);

			int id = (int) sessionhql.save(cr);
			updateMainTblPaheiiData("measures_core_values", roleSusNo, roleType);
			msg = Integer.toString(id);
			if (roleType.equals("APP")) {
				msg = "approve";
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Error: " + e.getMessage(); // More descriptive error message
			} catch (RuntimeException rbe) {
				msg = "Error during rollback";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}

////////////////////////////// Human Resource Developement ///////////////////////////////	

	@RequestMapping(value = "/admin/Change_of_Human_Resource", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Human_Resource(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Date date = new Date();

		String username = session.getAttribute("username").toString();
		String roleSusNo = request.getParameter("sus_no");
		String roleType = session.getAttribute("roleType").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		String juniorRating = request.getParameter("juniorRating");
		String welfareRating = request.getParameter("welfareRating");
		String measuresRating = request.getParameter("measuresRating");
		String enhancementRating = request.getParameter("enhancementRating");
		String trainingRating = request.getParameter("trainingRating");
		String serviceLevelRating = request.getParameter("serviceLevelRating");
		String auditRating = request.getParameter("auditRating");
		String complaintsRating = request.getParameter("complaintsRating");
		String relatedRating = request.getParameter("relatedRating");
		String miscellaneousRating = request.getParameter("miscellaneousRating");

		String msg = "";
		try {
			Query q0 = sessionhql.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_HUMAN_RESOURCE_DEVELOPEMENT WHERE sus_no = :sus_no and status =:status and year =:year")
					.setParameter("sus_no", roleSusNo).setParameter("status", 0).setParameter("year", currentYear);
			Long count = (Long) q0.uniqueResult();

			if (count > 0) {
				Query q1 = sessionhql.createQuery(
						"delete FROM TB_MISO_INSP_HUMAN_RESOURCE_DEVELOPEMENT WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}
			TB_MISO_INSP_HUMAN_RESOURCE_DEVELOPEMENT cr = new TB_MISO_INSP_HUMAN_RESOURCE_DEVELOPEMENT();

			int currentYear = LocalDate.now().getYear();
			String currentYearString = String.valueOf(currentYear);
			cr.setYear(currentYearString);

			cr.setJunior_leader(juniorRating);
			cr.setWelfare_troops(welfareRating);
			cr.setMeasures_resolution(measuresRating);
			cr.setEnhancement_education(enhancementRating);
			cr.setTraining_provided(trainingRating);
			cr.setTraining_career(serviceLevelRating);
			cr.setAudit_objection(auditRating);
			cr.setState_complaints(complaintsRating);
			cr.setArc(relatedRating);
			cr.setMiscellaneous(miscellaneousRating);

			cr.setCreated_by(username);
			cr.setSus_no(roleSusNo);
			cr.setCreated_date(date);
			cr.setStatus(0);

			int id = (int) sessionhql.save(cr);
			updateMainTblPaheiiData("human_resource_developement", roleSusNo, roleType);
			msg = Integer.toString(id);
			if (roleType.equals("APP")) {
				msg = "approve";
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Error: " + e.getMessage(); // More descriptive error message
			} catch (RuntimeException rbe) {
				msg = "Error during rollback";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}

////////////////////////////// Additional Information ///////////////////////////////	

	@RequestMapping(value = "/admin/Change_of_Additional_Information", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Additional_Information(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		Date date = new Date();

		String username = session.getAttribute("username").toString();
		String roleSusNo = request.getParameter("sus_no");
		String roleType = session.getAttribute("roleType").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		String publicFundsRating = request.getParameter("publicFundsRating");
		String stateDiscpRating = request.getParameter("stateDiscpRating");
		String stateComplaintsPetitionsRating = request.getParameter("stateComplaintsPetitionsRating");
		String stateWorkRating = request.getParameter("stateWorkRating");
		String anyPeculiarRating = request.getParameter("anyPeculiarRating");

		String msg = "";
		try {
			Query q0 = sessionhql.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_ADDITIONAL_INFORMATION WHERE sus_no = :sus_no and status =:status and year =:year")
					.setParameter("sus_no", roleSusNo).setParameter("status", 0).setParameter("year", currentYear);
			Long count = (Long) q0.uniqueResult();

			if (count > 0) {
				Query q1 = sessionhql.createQuery(
						"delete FROM TB_MISO_INSP_ADDITIONAL_INFORMATION WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}
			TB_MISO_INSP_ADDITIONAL_INFORMATION cr = new TB_MISO_INSP_ADDITIONAL_INFORMATION();

			int currentYear = LocalDate.now().getYear();
			String currentYearString = String.valueOf(currentYear);
			cr.setYear(currentYearString);

			cr.setState_public_Funds(publicFundsRating);
			cr.setState_discp(stateDiscpRating);
			cr.setState_complaints_petitions(stateComplaintsPetitionsRating);
			cr.setState_work_formation(stateWorkRating);
			cr.setPeculiar_aspect(anyPeculiarRating);

			cr.setCreated_by(username);
			cr.setSus_no(roleSusNo);
			cr.setCreated_date(date);
			cr.setStatus(0);

			int id = (int) sessionhql.save(cr);
			updateMainTblPaheiiData("additional_information", roleSusNo, roleType);
			msg = Integer.toString(id);
			if (roleType.equals("APP")) {
				msg = "approve";
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Error: " + e.getMessage(); // More descriptive error message
			} catch (RuntimeException rbe) {
				msg = "Error during rollback";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}

//////////////////////////////Additional Information ///////////////////////////////	

	@RequestMapping(value = "/admin/Change_of_cmts_on_partii", method = RequestMethod.POST)
	public @ResponseBody String Change_of_cmts_on_partii(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Date date = new Date();

		String username = session.getAttribute("username").toString();
		String roleSusNo = request.getParameter("sus_no");
		String roleType = session.getAttribute("roleType").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		String cmt = request.getParameter("cmt");
		String msg = "";
		try {
			Query q0 = sessionhql.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_COMMENTS_ON_PARTII WHERE sus_no = :sus_no and status =:status and year =:year")
					.setParameter("sus_no", roleSusNo).setParameter("status", 0).setParameter("year", currentYear);
			Long count = (Long) q0.uniqueResult();

			if (count > 0) {
				Query q1 = sessionhql.createQuery(
						"delete FROM TB_MISO_INSP_COMMENTS_ON_PARTII WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}
			TB_MISO_INSP_COMMENTS_ON_PARTII cr = new TB_MISO_INSP_COMMENTS_ON_PARTII();

			int currentYear = LocalDate.now().getYear();
			String currentYearString = String.valueOf(currentYear);
			cr.setYear(currentYearString);

			cr.setComments_on_partii(cmt);

			cr.setCreated_by(username);
			cr.setSus_no(roleSusNo);
			cr.setCreated_date(date);
			cr.setStatus(0);

			int id = (int) sessionhql.save(cr);
			updateMainTblPaheiiData("comments_insp_offr", roleSusNo, roleType);
			msg = Integer.toString(id);
			if (roleType.equals("APP")) {
				msg = "approve";
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Error: " + e.getMessage(); // More descriptive error message
			} catch (RuntimeException rbe) {
				msg = "Error during rollback";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}

	@RequestMapping(value = "/admin/Fetch_Form_Data", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> Fetch_Form_Data(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException, SQLException {

		String roleSusNo =request.getParameter("sus_no");
		String formType = request.getParameter("formType");

		Map<String, String> data = fetchDataForForm(formType, roleSusNo);

		Map<String, Object> response = new HashMap<>();
		if (data != null && !data.isEmpty()) {
			response.put("status", "success");
			response.put("data", data);
		} else {
			response.put("status", "error");
			response.put("message", "No data found");
		}

		return response;
	}

	public Map<String, String> fetchDataForForm(String formType, String roleSusNo) throws SQLException {
		Session session = null;
		Map<String, String> data = new HashMap<>();
		String currentYear = new SimpleDateFormat("yyyy").format(new Date());

		try {
			// Get Hibernate session
			session = HibernateUtil.getSessionFactory().openSession();

			// Query depends on form type
			String query = buildQueryForFormType(formType);

			if (query == null || query.isEmpty()) {
				System.err.println("Invalid form type: " + formType);
				return null; // Or throw an exception if that's more appropriate
			}

			// Using Hibernate's native SQL query
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setParameter(0, currentYear); 
			sqlQuery.setParameter(1, roleSusNo);

			List<Object[]> results = sqlQuery.list();

			if (!results.isEmpty()) {
				Object[] row = results.get(0);
				switch (formType) {
				case "#individual_training":
					if (row.length > 5 && row[5] != null)
						data.put("nightTrainingRating", row[5].toString());
					if (row.length > 6 && row[6] != null)
						data.put("officersTrainingRating", row[6].toString());
					if (row.length > 7 && row[7] != null)
						data.put("physicalTrainingRating", row[7].toString());
					if (row.length > 8 && row[8] != null)
						data.put("specialistTrainingRating", row[8].toString());
					if (row.length > 10 && row[10] != null)
						data.put("accCommissionTrainingRating", row[10].toString());
					if (row.length > 11 && row[11] != null)
						data.put("jcoNcoTrainingRating", row[11].toString());
					if (row.length > 12 && row[12] != null)
						data.put("weaponTrainingRating", row[12].toString());
					if (row.length > 13 && row[13] != null)
						data.put("weaponTrainingResultsRating", row[13].toString());
					if (row.length > 15 && row[15] != null)
						data.put("yosTrainingRating", row[15].toString());
					break;
				case "#collective_training":
					if (row.length > 1 && row[1] != null)
						data.put("fieldfiringRating", row[1].toString());
					if (row.length > 2 && row[2] != null)
						data.put("mobilisationRating", row[2].toString());
					if (row.length > 3 && row[3] != null)
						data.put("exerciseTrainingRating", row[3].toString());
					break;
				case "#management_training":
					if (row.length > 5 && row[5] != null)
						data.put("special_points", row[5].toString());
					if (row.length > 7 && row[7] != null)
						data.put("trainingAidsRating", row[7].toString());
					break;
				case "#personnel_management":
					if (row.length > 5 && row[5] != null)
						data.put("disciplineRating", row[5].toString());
					if (row.length > 6 && row[6] != null)
						data.put("HealthTroopsRating", row[6].toString());
					if (row.length > 7 && row[7] != null)
						data.put("personalDocumentationRating", row[7].toString());

					break;
				case "#interior_economy":
					if (row.length > 5 && row[5] != null)
						data.put("EquipmentRating", row[5].toString());
					if (row.length > 6 && row[6] != null)
						data.put("facilitiesRating", row[6].toString());
					if (row.length > 7 && row[7] != null)
						data.put("livingHabitatRating", row[7].toString());
					if (row.length > 8 && row[8] != null)
						data.put("livingConditionRating", row[8].toString());
					if (row.length > 9 && row[9] != null)
						data.put("modernEquipmentRating", row[9].toString());
					if (row.length > 10 && row[10] != null)
						data.put("individualDocumentationRating", row[10].toString());
					if (row.length > 11 && row[11] != null)
						data.put("personalClothingRating", row[11].toString());
					if (row.length > 13 && row[13] != null)
						data.put("timelyCorrectRating", row[13].toString());

					break;
				case "#morale_motivation":
					if (row.length > 5 && row[5] != null)
						data.put("disciplineStateRating", row[5].toString());
					if (row.length > 6 && row[6] != null)
						data.put("documentationRating", row[6].toString());
					if (row.length > 7 && row[7] != null)
						data.put("interiorEconomyRating", row[7].toString());
					if (row.length > 8 && row[8] != null)
						data.put("involvementRating", row[8].toString());
					if (row.length > 9 && row[9] != null)
						data.put("performanceCourseRating", row[9].toString());
					if (row.length > 10 && row[10] != null)
						data.put("performanceSportsRating", row[10].toString());
					if (row.length > 11 && row[11] != null)
						data.put("PerformanceTrgRating", row[11].toString());
					if (row.length > 12 && row[12] != null)
						data.put("persDisciplineRating", row[12].toString());
					if (row.length > 13 && row[13] != null)
						data.put("stateSickReportRating", row[13].toString());
					if (row.length > 14 && row[14] != null)
						data.put("stateEqptMaintRating", row[14].toString());
					if (row.length > 15 && row[15] != null)
						data.put("stateLeaveRating", row[15].toString());
					break;
				case "#material_management":
					if (row.length > 5 && row[5] != null)
						data.put("eqptIncludingRating", row[5].toString());
					if (row.length > 6 && row[6] != null)
						data.put("maintenanceAmnRating", row[6].toString());
					if (row.length > 7 && row[7] != null)
						data.put("maintenanceArmsRating", row[7].toString());
					if (row.length > 8 && row[8] != null)
						data.put("maintenanceOrdnanceStoresRating", row[8].toString());
					if (row.length > 9 && row[9] != null)
						data.put("PublicFundsRating", row[9].toString());
					if (row.length > 11 && row[11] != null)
						data.put("VehiclesRating", row[11].toString());
					break;
				case "#other_miscellaneous_aspects":
					if (row[0] != null)
						data.put("integrityRating", row[0].toString());
					if (row.length > 5 && row[5] != null)
						data.put("SecurityRating", row[5].toString());
					break;
				case "#measures_core_values":
					if (row[0] != null)
						data.put("integrityRating", row[0].toString());
					if (row.length > 1 && row[1] != null)
						data.put("loyaltyRating", row[1].toString());
					if (row.length > 2 && row[2] != null)
						data.put("dutyRating", row[2].toString());
					if (row.length > 3 && row[3] != null)
						data.put("respectRating", row[3].toString());
					if (row.length > 4 && row[4] != null)
						data.put("selflessRating", row[4].toString());
					if (row.length > 5 && row[5] != null)
						data.put("courageRating", row[5].toString());
					if (row.length > 6 && row[6] != null)
						data.put("honourRating", row[6].toString());
					break;

				case "#human_resource_developement":
					if (row[0] != null)
						if (row.length > 3 && row[3] != null)
							data.put("relatedRating", row[3].toString());
					if (row.length > 4 && row[4] != null)
						data.put("auditRating", row[4].toString());
					if (row.length > 7 && row[7] != null)
						data.put("enhancementRating", row[7].toString());
					if (row.length > 8 && row[8] != null)
						data.put("juniorRating", row[8].toString());
					if (row.length > 9 && row[9] != null)
						data.put("measuresRating", row[9].toString());
					if (row.length > 10 && row[10] != null)
						data.put("miscellaneousRating", row[10].toString());
					if (row.length > 11 && row[11] != null)
						data.put("complaintsRating", row[11].toString());
					if (row.length > 13 && row[13] != null)
						data.put("trainingRating", row[13].toString());
					if (row.length > 12 && row[12] != null)
						data.put("serviceLevelRating", row[14].toString());
					if (row.length > 15 && row[15] != null)
						data.put("welfareRating", row[15].toString());
					break;
				case "#additional_information":
					if (row[0] != null)
						if (row.length > 5 && row[5] != null)
							data.put("publicFundsRating", row[5].toString());
					if (row.length > 6 && row[6] != null)
						data.put("stateComplaintsPetitionsRating", row[6].toString());
					if (row.length > 7 && row[7] != null)
						data.put("stateDiscpRating", row[7].toString());
					if (row.length > 8 && row[8] != null)
						data.put("anyPeculiarRating", row[8].toString());
					if (row.length > 9 && row[9] != null)
						data.put("stateWorkRating", row[9].toString());
					break;
				case "#comments_insp_offr":
					if (row[0] != null)
						if (row.length > 5 && row[5] != null)
							data.put("state_ii", row[5].toString());
					break;
				default:
					System.err.println("Unknown form type: " + formType);
					return null;
				}

				return data;
			}

		} catch (Exception e) {
			System.err.println("Database error: " + e.getMessage());
			if (e instanceof SQLException) {
				throw (SQLException) e;
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return data;
	}

	private String buildQueryForFormType(String formType) {
		switch (formType) {
		case "#individual_training":
			return "SELECT * FROM TB_MISO_INSP_INDIVIDUAL_TRAINING WHERE year = ? and sus_no= ?";

		case "#collective_training":
			return "SELECT * FROM TB_MISO_INSP_COLLECTIVE_TRAINING WHERE year = ? and sus_no= ?";

		case "#management_training":
			return "SELECT * FROM TB_MISO_INSP_MANAGEMENT_TRAINING WHERE year = ? and sus_no= ?";

		case "#personnel_management":
			return "SELECT * FROM TB_MISO_INSP_PERSONNEL_MANAGEMENT WHERE year = ? and sus_no= ?";

		case "#interior_economy":
			return "SELECT * FROM TB_MISO_INSP_INTERIOR_ECONOMY WHERE year = ? and sus_no= ?";

		case "#morale_motivation":
			return "SELECT * FROM TB_MISO_INSP_MORALE_MOTIVATION WHERE year = ? and sus_no= ?";

		case "#material_management":
			return "SELECT * FROM TB_MISO_INSP_MATERIAL_MANAGEMENT WHERE year = ? and sus_no= ?";

		case "#other_miscellaneous_aspects":
			return "SELECT * FROM TB_MISO_INSP_OTHER_MISCELLANEOUS_ASPECTS WHERE year = ? and sus_no= ?";

		case "#measures_core_values":
			return "SELECT integrity, loyalty, duty, respect, " + "selfless_service, courage, honour "
					+ "FROM TB_MISO_INSP_ARMY_CORE_VALUES WHERE year = ? and sus_no= ?";

		case "#human_resource_developement":
			return "SELECT * FROM TB_MISO_INSP_HUMAN_RESOURCE_DEVELOPEMENT WHERE year = ? and sus_no= ?";

		case "#additional_information":
			return "SELECT * FROM TB_MISO_INSP_ADDITIONAL_INFORMATION WHERE year = ? and sus_no= ?";
		case "#comments_insp_offr":
			return "SELECT * FROM TB_MISO_INSP_COMMENTS_ON_PARTII WHERE year = ? and sus_no= ?";
		default:
			return "";
		}
	}

//approval

	@RequestMapping(value = "/admin/inspection_app_report_phaseiii", method = RequestMethod.POST)
	public ModelAndView inspection_app_report_phaseiii(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "susno4", required = false) String roleSusNo) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("inspection_app_report_phaseiii", roleid);

//		if (val == false) {
////		return new ModelAndView("AccessTiles");
//		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		// String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleSusNo != "") {
			Mmap.put("sus_no", roleSusNo);
		}
		LocalDate today = LocalDate.now();
		List<TB_MISO_INSP_MAIN_PHASE_III_TBL> reports = dao.getinsp_main_table_data_phaseiii(roleSusNo, currentYear);
		if (reports != null && !reports.isEmpty()) {
			TB_MISO_INSP_MAIN_PHASE_III_TBL report = reports.get(0);
			Mmap.addAttribute("report", report);

			boolean isDownloadable = report.getIndividual_training() == 1 && report.getCollective_training() == 1
					&& report.getManagement_training() == 1 && report.getPersonnel_management() == 1
					&& report.getInterior_economy() == 1 && report.getMorale_motivation() == 1
					&& report.getMaterial_management() == 1 && report.getOther_miscellaneous_aspects() == 1
					&& report.getHuman_resource_developement() == 1 && report.getAdditional_information() == 1
					&& report.getComments_insp_offr() == 1 && report.getMeasures_core_values() == 1;

			Mmap.put("isDownloadable", isDownloadable);
		}

		Mmap.addAttribute("today", today.toString());
		Mmap.put("msg", msg);

		return new ModelAndView("Inspection_report_app_phase3A_tiles");
	}

//phase 3B sahil jasani

	@RequestMapping(value = "/admin/Overall_Str_and_Challenges_report", method = RequestMethod.GET)
	public ModelAndView Overall_Str_and_Challenges_report(ModelMap Mmap, HttpSession session,
			HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg) {
//	String roleid = session.getAttribute("roleid").toString();
//	Boolean val = roledao.ScreenRedirect("inspection_report", roleid);
//	String roleSusNo = session.getAttribute("roleSusNo").toString();
//	if (val == false) {
//		return new ModelAndView("AccessTiles");
//	}
//	if (request.getHeader("Referer") == null) {
//		msg = "";
//	}

		return new ModelAndView("Overall_Str_and_Challenges_report_tiles");
	}

	@RequestMapping(value = "/get_Overall_Str_and_Challenges_data_url", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_Overall_Str_and_Challenges_data_url(String unit_no,
			HttpSession session, HttpServletRequest request) {
		String roleSusNo = request.getParameter("sus_no");
		List<Map<String, Object>> result = dao.getOverall_Str_and_Challengesdataurl(session, request,roleSusNo);
		return result;
	}

	public boolean Overall_Str_and_Challenges_itemAction(HttpSession session, HttpServletRequest request,String roleSusNo) {
		String roleType = (String) session.getAttribute("roleType");
		Session ses1 = null;
		Transaction t2 = null;
		boolean success = false;
		
		String username = session.getAttribute("username").toString();
		Integer status_rolewise = "APP".equals(roleType) ? 1 : ("DEO".equals(roleType) ? 0 : -1);

		try {
			ses1 = HibernateUtil.getSessionFactory().openSession();
			t2 = ses1.beginTransaction();

			Query q = ses1.createQuery(
					"SELECT COUNT(*) FROM TB_MISO_INSP_STR_CHALLENGES WHERE sus_no = :sus_no and status =:status and year =:year");
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("status", 0);
			q.setParameter("year", currentYear);
			Long count = (Long) q.uniqueResult();

			if (count > 0) {
				Query q1 = ses1.createQuery(
						"delete FROM TB_MISO_INSP_STR_CHALLENGES WHERE sus_no = :sus_no and status =:status and year =:year");
				q1.setParameter("sus_no", roleSusNo);
				q1.setParameter("status", 0);
				q1.setParameter("year", currentYear);
				q1.executeUpdate();

			}

			TB_MISO_INSP_STR_CHALLENGES SA = new TB_MISO_INSP_STR_CHALLENGES();
			SA.setStrengths(request.getParameter("strengths"));
			SA.setChallenges(request.getParameter("challenges"));
			SA.setAdvisories(request.getParameter("advisories"));
			SA.setCreate_by(username);
			SA.setCreate_date(new Date());
			SA.setStatus(status_rolewise);
			SA.setSus_no(roleSusNo);
			SA.setYear(currentYear);
			ses1.save(SA);
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

	@FunctionalInterface
	private interface SaveActionHandler3B {
		boolean execute() throws Exception;
	}

	
	
	@RequestMapping(value = "/formDataSave3BAction", produces = "application/json")
	@ResponseBody
	public Map<String, Object> formDataSaveAction(HttpServletRequest request,HttpSession session,
			@RequestParam("buttonContext") String buttonContext,
			@RequestParam(value = "inspData", required = false) String inspData) {
		String roleSusNo = request.getParameter("sus_no");
		String roleType = session.getAttribute("roleType").toString();
		Map<String, Object> response = new HashMap<>();
		try {
			Map<String, SaveActionHandler3B> handlers = new HashMap<>();
			//SAVE education_standards_item
			handlers.put("overall_str_and_challenges_items", () -> Overall_Str_and_Challenges_itemAction(session, request,roleSusNo));

			boolean save = false;
			if (handlers.containsKey(buttonContext)) {
				save = handlers.get(buttonContext).execute();
				updateMainTblPaheiiData("overall_strg_chall",roleSusNo,roleType);
			} else {
				throw new IllegalArgumentException("Unknown button context: " + buttonContext);
			}

			response.put("success", save);
			response.put("message", save ? "Data saved successfully" : "Data not saved successfully");

		} catch (Exception e) {

			response.put("success", false);
			response.put("message", "Error saving data: " + e.getMessage());
		}

		return response;
	}
	

	////---------------------------------------------------PART III B-------------------------------------------
	 
		@RequestMapping(value = "/admin/inspection_report_3B", method = RequestMethod.GET)
		public ModelAndView Overall_Str_and_Challenges_reportB(ModelMap Mmap, HttpSession session, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg) {


			return new ModelAndView("Overall_Str_and_Challenges_report_tiles");
		}

		
		
		 @RequestMapping(value = "/admin/inspection_app_report_3B", method = RequestMethod.POST)
		 public ModelAndView inspection_app_report_3B(ModelMap Mmap, HttpSession session, HttpServletRequest request,
		 @RequestParam(value = "msg", required = false) String msg,	@RequestParam(value = "susno5", required = false) String susNo) {
			 String roleSusNo = session.getAttribute("roleSusNo").toString();
				List<TB_MISO_INSP_MAIN_PHASE_III_TBL> reports = dao.getinsp_main_table_data_phaseiii(roleSusNo, currentYear);
				if (reports != null && !reports.isEmpty()) {
					TB_MISO_INSP_MAIN_PHASE_III_TBL report = reports.get(0);
					Mmap.addAttribute("report", report);
				}
				Mmap.addAttribute("sus_no", roleSusNo);
		 return new ModelAndView("Inspection_app_Report_3B_tiles");
		 }
		 
		@RequestMapping(value = "/admin/inspection_report_3B", method = RequestMethod.POST)
		public ModelAndView Overall_Str_and_Challenges_reportBPost(ModelMap Mmap, HttpSession session, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "susno2", required = false) String susNo) {

			Mmap.addAttribute("sus_no", susNo);
			return new ModelAndView("Overall_Str_and_Challenges_report_tiles");
		}
		@RequestMapping(value = "/Download_Form_part3B", method = RequestMethod.POST)
		public ModelAndView Download_Form_part3B(ModelMap Mmap, HttpSession session,HttpServletRequest request,
		@RequestParam(value = "sus_no6", required = false) String roleSusNo
		) throws ParseException {
		ArrayList<ArrayList<String>> list =     null;
		ArrayList<ArrayList<String>> list1 =   null;
		List<String> TH = new ArrayList<String>();
		String Heading = session.getAttribute("roleloginName").toString();
		
		

		List<Map<String, Object>> getOverall_Str_and_Challengesdataurl = dao.getOverall_Str_and_Challengesdataurl(session, request,roleSusNo);

		ModelAndView mav = new ModelAndView(
		              new PDF_ANUAL_REPORT_PART_3B("P", TH, Heading, list1), "userList", list );

		      mav.addObject("Overall_Str_and_Challengesdataurl", getOverall_Str_and_Challengesdataurl);
		    
		//return new ModelAndView(new PDF_ANUAL_REPORT_PART_1("P", TH, Heading, list1), "userList", list);
		      return mav;
		}
		
		@RequestMapping(value = "/Download_Form_part3A", method = RequestMethod.POST)
		public ModelAndView Download_Form_part3A(ModelMap Mmap, HttpSession session,HttpServletRequest request,
		@RequestParam(value = "sus_no7", required = false) String roleSusNo
		) throws ParseException {
		ArrayList<ArrayList<String>> list =     null;
		ArrayList<ArrayList<String>> list1 =   null;
		List<String> TH = new ArrayList<String>();
		String Heading = session.getAttribute("roleloginName").toString();
//		String roleSusNo = (String) session.getAttribute("roleSusNo");

		
		 List<TB_MISO_INSP_INDIVIDUAL_TRAINING> getIndividual_Training = getdatamodelwisePart3(roleSusNo,"TB_MISO_INSP_INDIVIDUAL_TRAINING");
		 List<TB_MISO_INSP_COLLECTIVE_TRAINING> getCollective_Training = getdatamodelwisePart3(roleSusNo,"TB_MISO_INSP_COLLECTIVE_TRAINING");
		 List<TB_MISO_INSP_MANAGEMENT_TRAINING> getManagement_of_Training = getdatamodelwisePart3(roleSusNo,"TB_MISO_INSP_MANAGEMENT_TRAINING");
		 List<TB_MISO_INSP_PERSONNEL_MANAGEMENT> getPersonnel_Management = getdatamodelwisePart3(roleSusNo,"TB_MISO_INSP_PERSONNEL_MANAGEMENT");
		 List<TB_MISO_INSP_INTERIOR_ECONOMY> getInterior_Economy = getdatamodelwisePart3(roleSusNo,"TB_MISO_INSP_INTERIOR_ECONOMY");
		 List<TB_MISO_INSP_MORALE_MOTIVATION> getMorale_and_Motivation = getdatamodelwisePart3(roleSusNo,"TB_MISO_INSP_MORALE_MOTIVATION");
		 List<TB_MISO_INSP_MATERIAL_MANAGEMENT> getMaterial_Management = getdatamodelwisePart3(roleSusNo,"TB_MISO_INSP_MATERIAL_MANAGEMENT");
		 List<TB_MISO_INSP_OTHER_MISCELLANEOUS_ASPECTS> getMiscellaneous_Aspects = getdatamodelwisePart3(roleSusNo,"TB_MISO_INSP_OTHER_MISCELLANEOUS_ASPECTS");
		 List<TB_MISO_INSP_ARMY_CORE_VALUES> getArmy_Core_Values = getdatamodelwisePart3(roleSusNo,"TB_MISO_INSP_ARMY_CORE_VALUES");
		 List<TB_MISO_INSP_HUMAN_RESOURCE_DEVELOPEMENT> getHuman_Resource_Developement = getdatamodelwisePart3(roleSusNo,"TB_MISO_INSP_HUMAN_RESOURCE_DEVELOPEMENT");
		 List<TB_MISO_INSP_ADDITIONAL_INFORMATION> getAdditional_Information = getdatamodelwisePart3(roleSusNo,"TB_MISO_INSP_ADDITIONAL_INFORMATION");
		 List<TB_MISO_INSP_COMMENTS_ON_PARTII> getComments_of_Insp = getdatamodelwisePart3(roleSusNo,"TB_MISO_INSP_COMMENTS_ON_PARTII");
		 
		ModelAndView mav = new ModelAndView(
		              new PDF_ANUAL_REPORT_PART_3A("P", TH, Heading, list1), "userList", list );

		      mav.addObject("Individual_Training", getIndividual_Training);
		      mav.addObject("Collective_Training", getCollective_Training);
		      mav.addObject("Management_of_Training", getManagement_of_Training);
		      mav.addObject("Personnel_Management", getPersonnel_Management);
		      mav.addObject("Interior_Economy", getInterior_Economy);
		      mav.addObject("Morale_and_Motivation", getMorale_and_Motivation);
		      mav.addObject("Material_Management", getMaterial_Management);
		      mav.addObject("Miscellaneous_Aspects", getMiscellaneous_Aspects);
		      mav.addObject("Army_Core_Values", getArmy_Core_Values);
		      mav.addObject("Human_Resource_Developement", getHuman_Resource_Developement);
		      mav.addObject("Additional_Information", getAdditional_Information);
		      mav.addObject("Comments_of_Insp", getComments_of_Insp);
		    
		      return mav;
		}
		
				public <T> List<T> getdatamodelwisePart3(String sus_no, String modelName) {
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    Transaction tx = session.beginTransaction();
		    
		   
		    String hql = "from " + modelName + " a where a.sus_no = :sus_no and a.year = :year";
		    
		    Query q = session.createQuery(hql);
		    q.setParameter("sus_no", sus_no);
		    q.setParameter("year", currentYear);
		    
		    List<T> list = q.list();
		    tx.commit();
		    session.close();
		    return list;
		}
		
		
}
