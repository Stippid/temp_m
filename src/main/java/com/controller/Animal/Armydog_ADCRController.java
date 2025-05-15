package com.controller.Animal;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.cellprocessor.ParseInt;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.HelpDeskController.helpController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.tms.Animals_DetailsController;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.Animal.ADCRDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.model.Animal.TB_ANIMAL_ADCR_DTLS_HISTORY;
import com.model.Animal.TB_ANIMAL_AWARD_HISTORY;
import com.model.Animal.TB_ANIMAL_DRUG_HISTORY;
import com.model.Animal.TB_ANIMAL_EMPLOYMENT_HISTORY;
import com.model.Animal.TB_ANIMAL_HOSP_HISTORY;
import com.model.Animal.TB_ANIMAL_TRAINING_HISTORY;
import com.model.Animal.TB_ANIMAL_UTILIZATION_HISTORY;
import com.model.Animal.TB_ANIMAL_VACCINATION_HISTORY;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@SuppressWarnings("unused")
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Armydog_ADCRController {

	@Autowired
	ADCRDAO dao;

	ValidationController validation = new ValidationController();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	Animals_DetailsController ani = new Animals_DetailsController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	helpController helpcntl = new helpController();

	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/adcr", method = RequestMethod.GET)
	public ModelAndView mcr(ModelMap Mmap, HttpSession session, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcr", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));

			ArrayList<List<String>> getadcrReportList = dao.getADCRReportList(roleSusNo, roleType);
			if (getadcrReportList.size() == 0) {
				Mmap.put("msg", "Data Not Available.");
				Mmap.put("getadcrReportList", getadcrReportList);
			} else {
				Mmap.put("list", getadcrReportList);
			}
		}
		Mmap.put("msg", msg);
		return new ModelAndView("adcrTiles");
	}

	@RequestMapping(value = "/admin/getadcrRList", method = RequestMethod.POST)
	public ModelAndView getadcrReportList(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no3", required = false) String sus_no,
			@RequestParam(value = "unit_name3", required = false) String unit_name) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("adcr", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		if (sus_no.equals("") || sus_no.equals(null) || sus_no == "" || sus_no == null) {
			Mmap.put("msg", "Please Enter SUS No or Unit Name.");

			return new ModelAndView("adcrTiles");
		} else {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:adcr");
			}
			if (validation.checkUnit_nameLength(unit_name) == false) {
				Mmap.put("msg", validation.unit_nameMSG);
				return new ModelAndView("redirect:adcr");
			}

			Mmap.put("sus_no", sus_no);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
		}
		System.err.println("Sus--" + roleSusNo);
		String roleType = session.getAttribute("roleType").toString();
		ArrayList<List<String>> list = dao.getADCRReportList(sus_no, roleType);

		Mmap.put("roleType", roleType);
		System.err.println("Befire put val " + sus_no);
		Mmap.put("sus_no33", sus_no);
		Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
		Mmap.put("list", list);

		return new ModelAndView("adcrTiles");
	}

	@RequestMapping(value = "/admin/getADCRReport")
	public ModelAndView getADCRReport(@ModelAttribute("sus_no1") String sus_no,
			@ModelAttribute("unit_name1") String unit_name, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionA) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("adcr", roleid);
		

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		model.put("sus_no", sus_no);
		model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		model.put("msg", msg);
		model.put("GetHelpTopic", helpcntl.GetHelpTopicARMDOG());
		ArrayList<List<String>> list = dao.getarmydogList(sus_no, roleAccess);
		if (list.size() == 0) {
			model.put("msg", "Data Not Available.");
		} else {
			model.put("list", list);

		}

		return new ModelAndView("REadcrTiles");
	}
	
	@RequestMapping(value = "/admin/getBackADCRReport")
	public ModelAndView getBackADCRReport(@ModelAttribute("sus_no1") String sus_no,
			@ModelAttribute("unit_name1") String unit_name, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionA) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("adcr", roleid);
		
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		model.put("sus_no", sus_no);
		model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		model.put("msg", msg);
		model.put("GetHelpTopic", helpcntl.GetHelpTopicARMDOG());
		ArrayList<List<String>> list = dao.getarmydogList(sus_no, roleAccess);
		if (list.size() == 0) {
			model.put("msg", "Data Not Available.");
		} else {
			model.put("list", list);

		}

		return new ModelAndView("REadcrTiles");
	}

	@RequestMapping(value = "/viewadcr", method = RequestMethod.POST)
	public ModelAndView viewadcr(@Valid @Validated @ModelAttribute("editArmy_no") String ArmyNo, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {

		List<String> animalTypes = dao.getAnimalTypesByArmyNo(ArmyNo);

		if (animalTypes != null && !animalTypes.isEmpty()) {

			String firstAnimalType = animalTypes.get(0);

			model.put("animalTypes", animalTypes);

			model.put("getclrList", ani.getclrList(firstAnimalType, sessionEdit));
			model.put("getbreedList", ani.getbreedList(firstAnimalType, sessionEdit));
			model.put("getFitnessStatusList", ani.getFitnessStatusList(firstAnimalType, sessionEdit));
			model.put("getSourceList", ani.getSourceList(firstAnimalType, sessionEdit));
			model.put("getAwardList", ani.getAwardList(firstAnimalType, sessionEdit));
			model.put("getHospList", ani.getHospList(firstAnimalType, sessionEdit));
			model.put("getvaccineList", ani.getvaccineList(firstAnimalType, sessionEdit));
			model.put("getempList", ani.getempList(firstAnimalType, sessionEdit));
		}

		model.put("edit_animal_census_cmnd", dao.editadcr(ArmyNo));
		model.put("msg", msg);

		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		model.put("date", date1);

		model.put("getTypeOfDogList", ani.getTypeOfDogList(sessionEdit));
		model.put("getsplzList", ani.getsplzList(sessionEdit));

		return new ModelAndView("ADCRViewtile");
	}

	@RequestMapping(value = "/saveDogData", method = RequestMethod.POST)
	public @ResponseBody String saveDogData(@RequestParam String ason,@RequestParam String sus_no, @RequestParam String unit_name,
			@RequestParam String army_num, @RequestParam String type_dog, @RequestParam String name_of_dog,
			@RequestParam String specialization, @RequestParam String breed, @RequestParam String colour,
			@RequestParam String sex, @RequestParam String source, @RequestParam String date_of_birth,
			@RequestParam String microchip_no, @RequestParam String tos, ModelMap model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		Date date = new Date(System.currentTimeMillis());

		Session sessionGet = null;
		Transaction tx = null;

		try {

			sessionGet = HibernateUtilNA.getSessionFactory().openSession();
			tx = sessionGet.beginTransaction();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date ason1 = null;
			Date date_of_birth1 = null;
			Date tos1 = null;
			
			if (ason != null && !ason.isEmpty()) {
				ason1 = new Date(dateFormat.parse(ason).getTime());
			}

			if (date_of_birth != null && !date_of_birth.isEmpty()) {
				date_of_birth1 = new Date(dateFormat.parse(date_of_birth).getTime());
			}

			if (tos != null && !tos.isEmpty()) {
				tos1 = new Date(dateFormat.parse(tos).getTime());
			}
			List<String> censusId = dao.getAnimalCensusIDByArmyNo(army_num);
			String firstcensusId = censusId.get(0);

			Query q0 = sessionGet.createSQLQuery(
					"select count(id) from tb_animal_adcr_dtls_history where census_id=:census_id and cast(ason_date as date)=:ason_date and status=:status");
			q0.setParameter("census_id", Integer.parseInt(firstcensusId));
			q0.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP);
			q0.setParameter("status", 0);

			BigInteger result = (BigInteger) q0.uniqueResult();
			Long c = result.longValue(); // Convert BigInteger to Long

			if (c == 0) {

				TB_ANIMAL_ADCR_DTLS_HISTORY rs = new TB_ANIMAL_ADCR_DTLS_HISTORY();
				rs.setSus_no(sus_no);
				rs.setCreated_by(username);
				rs.setCreated_date(date);
				rs.setStatus(0);
				rs.setAnimal_type("Army Dog");
				rs.setArmy_no(army_num);
				rs.setAson_Date(ason1);
				rs.setBreed(Integer.parseInt(breed));
				rs.setColor(Integer.parseInt(colour));
				rs.setDate_of_birth(date_of_birth1);
				rs.setDate_of_tos(tos1);
				rs.setGender(sex);
				rs.setMicrochip_no(microchip_no);
				rs.setName(name_of_dog);
				rs.setSource(Integer.parseInt(source));
				rs.setSpecialization(Integer.parseInt(specialization));
				rs.setType_of_dog(Integer.parseInt(type_dog));
				rs.setCensus_id(Integer.parseInt(firstcensusId));

				sessionGet.save(rs);
			} else {

				String hqlUpdatePrevious = "UPDATE tb_animal_adcr_dtls_history "
						+ "SET army_no=:army_no, ason_date=:ason_date, breed=:breed, "
						+ "color=:color, created_by=:created_by, created_date=:created_date, "
						+ "date_of_birth=:date_of_birth, date_of_tos=:date_of_tos, "
						+ "gender=:gender, microchip_no=:microchip_no, name=:name, "
						+ "source=:source, specialization=:specialization, type_of_dog=:type_of_dog "
						+ "WHERE census_id=:census_id AND cast(ason_date as date)=:ason_date AND status=0";

				Query updatePrevious = sessionGet.createSQLQuery(hqlUpdatePrevious);
				updatePrevious.setParameter("army_no", army_num);
				updatePrevious.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP); // Should be java.util.Date object
				updatePrevious.setParameter("breed", Integer.parseInt(breed));
				updatePrevious.setParameter("color", Integer.parseInt(colour));
				updatePrevious.setParameter("created_by", username);
				updatePrevious.setParameter("created_date", date);
				updatePrevious.setParameter("date_of_birth", date_of_birth1,
						org.hibernate.type.StandardBasicTypes.TIMESTAMP);
				updatePrevious.setParameter("date_of_tos", tos1, org.hibernate.type.StandardBasicTypes.TIMESTAMP);
				updatePrevious.setParameter("gender", sex);
				updatePrevious.setParameter("microchip_no", microchip_no);
				updatePrevious.setParameter("name", name_of_dog);
				updatePrevious.setParameter("source", Integer.parseInt(source));
				updatePrevious.setParameter("specialization", Integer.parseInt(specialization));
				updatePrevious.setParameter("type_of_dog", Integer.parseInt(type_dog));
				updatePrevious.setParameter("census_id", Integer.parseInt(firstcensusId));
				updatePrevious.executeUpdate();
				
			}
	
			tx.commit();

			return "Data Updated Successfully.";

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return "Error updating data: " + e.getMessage();

		} finally {

			if (sessionGet != null) {
				sessionGet.close();
			}
		}
	}
	
	///
	@RequestMapping(value = "/saveDepData", method = RequestMethod.POST)
	public @ResponseBody String saveDepData(@RequestParam String ason,@RequestParam String sus_no, @RequestParam String unit_name,
			@RequestParam String army_num,@RequestParam String fitness_status,
			@RequestParam(required = false) String date_admitted,@RequestParam(required = false) String employment, 
			@RequestParam(required = false) String empsus,@RequestParam(required = false) String empno, 
			@RequestParam(required = false) String empname,@RequestParam(required = false) String loc,
			@RequestParam(required = false) String date_unfit,@RequestParam(required = false) String remarks, 
			ModelMap model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		Date date = new Date(System.currentTimeMillis());

		Session sessionGet = null;
		Transaction tx = null;

		try {

			sessionGet = HibernateUtilNA.getSessionFactory().openSession();
			tx = sessionGet.beginTransaction();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date ason1 = null;
			Date date_admitted1 = null;
			Date date_unfit1 = null;
			
			if (ason != null && !ason.isEmpty()) {
				ason1 = new Date(dateFormat.parse(ason).getTime());
			}

			if (date_admitted != null && !date_admitted.isEmpty()) {
				date_admitted1 = new Date(dateFormat.parse(date_admitted).getTime());
			}

			if (date_unfit != null && !date_unfit.isEmpty()) {
				date_unfit1 = new Date(dateFormat.parse(date_unfit).getTime());
			}
			
			List<String> censusId = dao.getAnimalCensusIDByArmyNo(army_num);
			String firstcensusId = censusId.get(0);

			Query q1 = sessionGet.createSQLQuery(
					"select count(id) from tb_animal_employment_history where census_id=:census_id and cast(ason_date as date)=:ason_date and status=:status");
			q1.setParameter("census_id", Integer.parseInt(firstcensusId));
			q1.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP);
			q1.setParameter("status", 0);

			BigInteger result1 = (BigInteger) q1.uniqueResult();
			Long c1 = result1.longValue();
			if (c1 == 0) {
				TB_ANIMAL_EMPLOYMENT_HISTORY es = new TB_ANIMAL_EMPLOYMENT_HISTORY();
				es.setSus_no(sus_no);
				es.setAson_Date(ason1);
				es.setCreated_by(username);
				es.setCreated_date(date);
				es.setEmployment(Integer.parseInt(employment));
				es.setEmp_name(empname);
				es.setEmp_no(empno);
				es.setEmp_sus(empsus);
				es.setLoc(loc);
				es.setDate_of_admited(date_admitted1);
				es.setFit_status(Integer.parseInt(fitness_status));
				es.setStatus(0);
				es.setCensus_id(Integer.parseInt(firstcensusId));
				es.setDate_of_unfit(date_unfit1);
				es.setRemarks(remarks);
				sessionGet.save(es);
			} else {
				String hqlUpdatePrevious10 = "UPDATE tb_animal_employment_history "
						+ "SET ason_date=:ason_date, employment=:employment, emp_sus=:emp_sus, emp_name=:emp_name, emp_no=:emp_no, loc=:loc, fit_status=:fit_status, date_of_admited=:date_of_admited, remarks=:remarks, date_of_unfit=:date_of_unfit "
						+ "WHERE census_id=:census_id AND cast(ason_date as date)=:ason_date AND status=0";

				Query updatePrevious10 = sessionGet.createSQLQuery(hqlUpdatePrevious10);

				updatePrevious10.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP); // Should be java.util.Date object
				updatePrevious10.setParameter("employment", Integer.parseInt(employment));
				updatePrevious10.setParameter("emp_sus", empsus);
				updatePrevious10.setParameter("emp_name", empname);
				updatePrevious10.setParameter("emp_no", empno);
				updatePrevious10.setParameter("loc", loc);
				updatePrevious10.setParameter("fit_status", Integer.parseInt(fitness_status));
				updatePrevious10.setParameter("date_of_admited", date_admitted1, org.hibernate.type.StandardBasicTypes.TIMESTAMP);
				updatePrevious10.setParameter("remarks", remarks);
				updatePrevious10.setParameter("date_of_unfit", date_unfit1, org.hibernate.type.StandardBasicTypes.TIMESTAMP);
				updatePrevious10.setParameter("census_id", Integer.parseInt(firstcensusId));
				updatePrevious10.executeUpdate();
				
			}
	
			tx.commit();

			return "Data Updated Successfully.";

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return "Error updating data: " + e.getMessage();

		} finally {

			if (sessionGet != null) {
				sessionGet.close();
			}
		}
	}
	////
	@RequestMapping(value = "/saveUtData", method = RequestMethod.POST)
	public @ResponseBody String saveUtData(@RequestParam String ason,@RequestParam String sus_no, @RequestParam String unit_name,
			@RequestParam String army_num,@RequestParam(required = false) String rop,
			@RequestParam(required = false) String veh, @RequestParam(required = false) String sanitisation,
			@RequestParam(required = false) String vduties, @RequestParam(required = false) String explosive,
			@RequestParam(required = false) String article, @RequestParam(required = false) String hideEnemy,
			@RequestParam(required = false) String arms, @RequestParam(required = false) String shbo,
			@RequestParam(required = false) String reftrg, @RequestParam(required = false) String ptrkm,
			@RequestParam(required = false) String mine, @RequestParam(required = false) String guard,
			@RequestParam(required = false) String areakms, @RequestParam(required = false) String debris,
			@RequestParam(required = false) String avalnches, @RequestParam(required = false) String room,
			@RequestParam(required = false) String remakrs, @RequestParam(required = false) String killed,
			@RequestParam(required = false) String apprehended, @RequestParam(required = false) String escape,
			@RequestParam(required = false) String esitrepstatus, @RequestParam(required = false) String esitrepnum,
			@RequestParam(required = false) MultipartFile uploadfile,
		     ModelMap model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		Date date = new Date(System.currentTimeMillis());

		Session sessionGet = null;
		Transaction tx = null;

		try {

			sessionGet = HibernateUtilNA.getSessionFactory().openSession();
			tx = sessionGet.beginTransaction();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date ason1 = null;
			
			if (ason != null && !ason.isEmpty()) {
				ason1 = new Date(dateFormat.parse(ason).getTime());
			}
			
			if (uploadfile.getSize() >  2 * 1024 * 1024) {
				return "File size should be less then 2 MB.";
			}
			
			String fname = "";
			DateWithTimeStampController timestamp = new DateWithTimeStampController();
			
				byte[] bytes = uploadfile.getBytes();
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if (fileValidation.check_RAR_File(bytes) || fileValidation.check_ZIP_File(bytes)) {
					String anlFilePath = session.getAttribute("animalFilePath").toString();
					File dir = new File(anlFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = uploadfile.getOriginalFilename();
					String extension = "";
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
				
					 String sus_no_name = sus_no.replace("/","-");
				
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_" + "_AMLDOC." + extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

			List<String> censusId = dao.getAnimalCensusIDByArmyNo(army_num);
			String firstcensusId = censusId.get(0);

			Query q2 = sessionGet.createSQLQuery(
					"select count(id) from tb_animal_utilization_history where census_id=:census_id and cast(ason_date as date)=:ason_date and status=:status");
			q2.setParameter("census_id", Integer.parseInt(firstcensusId));
			q2.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP);
			q2.setParameter("status", 0);

			BigInteger result2 = (BigInteger) q2.uniqueResult();
			Long c2 = result2.longValue();
			if (c2 == 0) {
				TB_ANIMAL_UTILIZATION_HISTORY us = new TB_ANIMAL_UTILIZATION_HISTORY();
				us.setSus_no(sus_no);
				us.setAson_Date(ason1);
				us.setCreated_by(username);
				us.setCreated_date(date);
				us.setStatus(0);
				us.setArea_kms(Double.parseDouble(areakms));
				us.setDetected_avalnches(Integer.parseInt(avalnches));
				us.setDetected_debris(Integer.parseInt(debris));
				us.setDetected_escape(Integer.parseInt(escape));
				us.setDetected_explosive(Integer.parseInt(explosive));
				us.setDetected_mine(Integer.parseInt(mine));
				us.setEsitrep_num(esitrepnum);
				us.setEsitrep_status(Integer.parseInt(esitrepstatus));
				us.setHideout_Enemy(Integer.parseInt(hideEnemy));
				us.setNo_of_arms(Integer.parseInt(arms));
				us.setNo_of_article(Integer.parseInt(article));
				us.setNo_of_reftrg(Integer.parseInt(reftrg));
				us.setNo_of_shbo(Integer.parseInt(shbo));
				us.setNo_of_veh(Integer.parseInt(veh));
				us.setOps_apprehended(Integer.parseInt(apprehended));
				us.setOps_killed(Integer.parseInt(killed));
				us.setOps_room(Integer.parseInt(room));
				us.setPtr_km(Integer.parseInt(ptrkm));
				us.setRemarks(remakrs);
				us.setSanitisation_duty(Integer.parseInt(sanitisation));
				us.setTotal_guard(Integer.parseInt(guard));
				us.setTotal_rop(Double.parseDouble(rop));
				us.setV_duties(Integer.parseInt(vduties));
				us.setCensus_id(Integer.parseInt(firstcensusId));
				us.setDoc_path(fname);
				sessionGet.save(us);
			} else {
				
				String hqlUpdatePrevious1 = "UPDATE tb_animal_utilization_history " +
				        "SET area_kms=:area_kms, " +
				        "detected_avalnches=:detected_avalnches, " +
				        "detected_debris=:detected_debris, " +
				        "detected_escape=:detected_escape, " +
				        "detected_explosive=:detected_explosive, " +
				        "detected_mine=:detected_mine, " +
				        "esitrep_num=:esitrep_num, " +
				        "esitrep_status=:esitrep_status, " +
				        "hideout_enemy=:hideout_enemy, " +
				        "no_of_arms=:no_of_arms, " +
				        "no_of_article=:no_of_article, " +
				        "no_of_reftrg=:no_of_reftrg, " +
				        "no_of_shbo=:no_of_shbo, " +
				        "no_of_veh=:no_of_veh, " +
				        "ops_apprehended=:ops_apprehended, " +
				        "ops_killed=:ops_killed, " +
				        "ops_room=:ops_room, " +
				        "ptr_km=:ptr_km, " +
				        "remarks=:remarks, " +
				        "sanitisation_duty=:sanitisation_duty, " +
				        "total_guard=:total_guard, " +
				        "total_rop=:total_rop, " +
				        "doc_path=:doc_path, " +
				        "v_duties=:v_duties " +
				        "WHERE census_id=:census_id " +
				        "AND cast(ason_date as date)=:ason_date " +
				        "AND status=0";

				Query updatePrevious1 = sessionGet.createSQLQuery(hqlUpdatePrevious1);

				// Setting parameters
				updatePrevious1.setParameter("area_kms", Double.parseDouble(areakms));
				updatePrevious1.setParameter("detected_avalnches", Integer.parseInt(avalnches));
				updatePrevious1.setParameter("detected_debris", Integer.parseInt(debris));
				updatePrevious1.setParameter("detected_escape", Integer.parseInt(escape));
				updatePrevious1.setParameter("detected_explosive", Integer.parseInt(explosive));
				updatePrevious1.setParameter("detected_mine", Integer.parseInt(mine));
				updatePrevious1.setParameter("esitrep_num", esitrepnum);
				updatePrevious1.setParameter("esitrep_status", Integer.parseInt(esitrepstatus));
				updatePrevious1.setParameter("hideout_enemy", Integer.parseInt(hideEnemy));
				updatePrevious1.setParameter("no_of_arms", Integer.parseInt(arms));
				updatePrevious1.setParameter("no_of_article", Integer.parseInt(article));
				updatePrevious1.setParameter("no_of_reftrg", Integer.parseInt(reftrg));
				updatePrevious1.setParameter("no_of_shbo", Integer.parseInt(shbo));
				updatePrevious1.setParameter("no_of_veh", Integer.parseInt(veh));
				updatePrevious1.setParameter("ops_apprehended", Integer.parseInt(apprehended));
				updatePrevious1.setParameter("ops_killed", Integer.parseInt(killed));
				updatePrevious1.setParameter("ops_room", Integer.parseInt(room));
				updatePrevious1.setParameter("ptr_km", Integer.parseInt(ptrkm));
				updatePrevious1.setParameter("remarks", remakrs);
				updatePrevious1.setParameter("sanitisation_duty", Integer.parseInt(sanitisation));
				updatePrevious1.setParameter("total_guard", Integer.parseInt(guard));
				updatePrevious1.setParameter("total_rop", Double.parseDouble(rop));
				updatePrevious1.setParameter("doc_path", fname);
				updatePrevious1.setParameter("v_duties", Integer.parseInt(vduties));
				updatePrevious1.setParameter("census_id", Integer.parseInt(firstcensusId));
				updatePrevious1.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP); // Should be java.util.Date object

				updatePrevious1.executeUpdate();

				
			}
				}
			tx.commit();

			return "Data Updated Successfully.";

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return "Error updating data: " + e.getMessage();

		} finally {

			if (sessionGet != null) {
				sessionGet.close();
			}
		}
	}
	////
	@RequestMapping(value = "/saveHospData", method = RequestMethod.POST)
	public @ResponseBody String saveHospData(@RequestParam String ason,@RequestParam String sus_no, @RequestParam String unit_name,
			@RequestParam String army_num,
			@RequestParam(required = false) String hospname, @RequestParam(required = false) String hemoglobin,
			@RequestParam(required = false) String weight, @RequestParam(required = false) String tlc,
			@RequestParam(required = false) String dlc, @RequestParam(required = false) String trbc,
			@RequestParam(required = false) String pcv, @RequestParam(required = false) String platelet,
			@RequestParam(required = false) String sgot, @RequestParam(required = false) String bill,
			@RequestParam(required = false) String urine, @RequestParam(required = false) String protein,
			@RequestParam(required = false) String creatinine, @RequestParam(required = false) String albun,
			@RequestParam(required = false) String agr, @RequestParam(required = false) String bun,
			@RequestParam(required = false) String stool, @RequestParam(required = false) String sstatus, 
			@RequestParam(required = false) String ustatus, @RequestParam String medason, ModelMap model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		Date date = new Date(System.currentTimeMillis());

		Session sessionGet = null;
		Transaction tx = null;

		try {

			sessionGet = HibernateUtilNA.getSessionFactory().openSession();
			tx = sessionGet.beginTransaction();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date ason1 = null;
			Date medason1 = null;
			
			if (ason != null && !ason.isEmpty()) {
				ason1 = new Date(dateFormat.parse(ason).getTime());
			}
			
			if (medason != null && !medason.isEmpty()) {
				medason1 = new Date(dateFormat.parse(medason).getTime());
			}

			List<String> censusId = dao.getAnimalCensusIDByArmyNo(army_num);
			String firstcensusId = censusId.get(0);

			Query q3 = sessionGet.createSQLQuery(
					"select count(id) from tb_animal_hosp_history where census_id=:census_id and cast(ason_date as date)=:ason_date and status=:status");
			q3.setParameter("census_id", Integer.parseInt(firstcensusId));
			q3.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP);
			q3.setParameter("status", 0);

			BigInteger result3 = (BigInteger) q3.uniqueResult();
			Long c3 = result3.longValue();
			if (c3 == 0) {
				TB_ANIMAL_HOSP_HISTORY hs = new TB_ANIMAL_HOSP_HISTORY();
				hs.setSus_no(sus_no);
				hs.setAgr(Double.parseDouble(agr));
				hs.setAlbun(Double.parseDouble(albun));
				hs.setAson_Date(ason1);
				hs.setBill(Double.parseDouble(bill));
				hs.setBun(Double.parseDouble(bun));
				hs.setCreated_by(username);
				hs.setCreated_date(date);
				hs.setCreatinine(Double.parseDouble(creatinine));
				hs.setDlc(Double.parseDouble(dlc));
				hs.setHemoglobin(Double.parseDouble(hemoglobin));
				hs.setHosp_name(Integer.parseInt(hospname));
				hs.setPcv(Double.parseDouble(pcv));
				hs.setPlatelet(Double.parseDouble(platelet));
				hs.setProtein(Double.parseDouble(protein));
				hs.setSgot(Double.parseDouble(sgot));
				hs.setStatus(0);
				hs.setStool(Double.parseDouble(sstatus));
				hs.setTlc(Double.parseDouble(tlc));
				hs.setTrbc(Double.parseDouble(trbc));
				hs.setUrine(Double.parseDouble(ustatus));
				hs.setWeight(Double.parseDouble(weight));
				hs.setCensus_id(Integer.parseInt(firstcensusId));
				hs.setStool_remaks(stool);
				hs.setUrine_remakrs(urine);
				hs.setMed_date(medason1);
				sessionGet.save(hs);
			} else {
				String hqlUpdatePrevious2 = "UPDATE tb_animal_hosp_history\r\n"
						+ "	SET agr=:agr, ason_date=:ason_date, albun=:albun,  bill=:bill, bun=:bun, creatinine=:creatinine, \r\n"
						+ "	dlc=:dlc, hemoglobin=:hemoglobin, hosp_name=:hosp_name, pcv=:pcv, platelet=:platelet, protein=:protein, sgot=:sgot, \r\n"
						+ "	stool=:stool, tlc=:tlc, trbc=:trbc, urine=:urine, weight=:weight, stool_remaks=:stool_remaks, urine_remakrs=:urine_remakrs, med_date=:med_date \r\n"
						+ "	WHERE census_id=:census_id AND cast(ason_date as date)=:ason_date AND status=0";

				Query updatePrevious2 = sessionGet.createSQLQuery(hqlUpdatePrevious2);

				updatePrevious2.setParameter("agr", Double.parseDouble(agr));
				updatePrevious2.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP); // Should be java.util.Date object
				updatePrevious2.setParameter("albun", Double.parseDouble(albun));
				updatePrevious2.setParameter("bill", Double.parseDouble(bill));
				updatePrevious2.setParameter("bun", Double.parseDouble(bun));
				updatePrevious2.setParameter("creatinine", Double.parseDouble(creatinine));
				updatePrevious2.setParameter("dlc", Double.parseDouble(dlc));
				updatePrevious2.setParameter("hemoglobin", Double.parseDouble(hemoglobin));
				updatePrevious2.setParameter("hosp_name", Integer.parseInt(hospname));
				updatePrevious2.setParameter("pcv", Double.parseDouble(pcv));
				updatePrevious2.setParameter("platelet", Double.parseDouble(platelet));
				updatePrevious2.setParameter("protein", Double.parseDouble(protein));
				updatePrevious2.setParameter("sgot", Double.parseDouble(sgot));
				updatePrevious2.setParameter("stool", Double.parseDouble(sstatus));
				updatePrevious2.setParameter("tlc", Double.parseDouble(tlc));
				updatePrevious2.setParameter("trbc", Double.parseDouble(trbc));
				updatePrevious2.setParameter("urine", Double.parseDouble(ustatus));
				updatePrevious2.setParameter("weight", Double.parseDouble(weight));
				updatePrevious2.setParameter("census_id", Integer.parseInt(firstcensusId));
				updatePrevious2.setParameter("urine_remakrs", urine);
				updatePrevious2.setParameter("stool_remaks", stool);
				updatePrevious2.setParameter("med_date", medason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP); 
				updatePrevious2.executeUpdate();
				
			}

			tx.commit();

			return "Data Updated Successfully.";

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return "Error updating data: " + e.getMessage();

		} finally {

			if (sessionGet != null) {
				sessionGet.close();
			}
		}
	}
	////
	@RequestMapping(value = "/saveVcData", method = RequestMethod.POST)
	public @ResponseBody String saveVcData(@RequestParam String ason,@RequestParam String sus_no, @RequestParam String unit_name,
			@RequestParam String army_num,@RequestParam(required = false) String vaccine,
			@RequestParam(required = false) String astatus, @RequestParam(required = false) String allergy,
			ModelMap model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		Date date = new Date(System.currentTimeMillis());

		Session sessionGet = null;
		Transaction tx = null;

		try {

			sessionGet = HibernateUtilNA.getSessionFactory().openSession();
			tx = sessionGet.beginTransaction();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date ason1 = null;
			Date date_of_birth1 = null;
			Date tos1 = null;
			Date date_admitted1 = null;
			Date trainingfromdate1 = null;
			Date trainingtodate1 = null;
			
			if (ason != null && !ason.isEmpty()) {
				ason1 = new Date(dateFormat.parse(ason).getTime());
			}

			List<String> censusId = dao.getAnimalCensusIDByArmyNo(army_num);
			String firstcensusId = censusId.get(0);

			Query q4 = sessionGet.createSQLQuery(
					"select count(id) from tb_animal_vaccination_history where census_id=:census_id and cast(ason_date as date)=:ason_date and status=:status");
			q4.setParameter("census_id", Integer.parseInt(firstcensusId));
			q4.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP);
			q4.setParameter("status", 0);

			BigInteger result4 = (BigInteger) q4.uniqueResult();
			Long c4 = result4.longValue();
			if (c4 == 0) {
				TB_ANIMAL_VACCINATION_HISTORY vs = new TB_ANIMAL_VACCINATION_HISTORY();
				vs.setSus_no(sus_no);
				vs.setAson_Date(ason1);
				vs.setCreated_by(username);
				vs.setCreated_date(date);
				vs.setVaccinet(Integer.parseInt(vaccine));
				vs.setAllergy_status(Integer.parseInt(astatus));
				vs.setAllergy(allergy);
				vs.setStatus(0);
				vs.setCensus_id(Integer.parseInt(firstcensusId));
				sessionGet.save(vs);
			} else {
				String hqlUpdatePrevious3 = "UPDATE tb_animal_vaccination_history "
						+ "SET ason_date=:ason_date, vaccinet=:vaccinet, allergy_status=:allergy_status, allergy=:allergy "
						+ "WHERE census_id=:census_id AND cast(ason_date as date)=:ason_date AND status=0";

				Query updatePrevious3 = sessionGet.createSQLQuery(hqlUpdatePrevious3);

				updatePrevious3.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP); // Should be java.util.Date object
				updatePrevious3.setParameter("vaccinet", Integer.parseInt(vaccine));
				updatePrevious3.setParameter("allergy_status", Integer.parseInt(astatus));
				updatePrevious3.setParameter("allergy", allergy);
				updatePrevious3.setParameter("census_id", Integer.parseInt(firstcensusId));
				updatePrevious3.executeUpdate();
				
			
			}

			tx.commit();

			return "Data Updated Successfully.";

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return "Error updating data: " + e.getMessage();

		} finally {

			if (sessionGet != null) {
				sessionGet.close();
			}
		}
	}
	//
	@RequestMapping(value = "/saveTrgData", method = RequestMethod.POST)
	public @ResponseBody String saveTrgData(@RequestParam String ason,@RequestParam String sus_no, @RequestParam String unit_name,
			@RequestParam String army_num,
			@RequestParam(required = false) String trainersus, @RequestParam(required = false) String trainerunit,
			@RequestParam(required = false) String trainerno, @RequestParam(required = false) String trainername ,
			@RequestParam(required = false) String trainerrank, @RequestParam(required = false) String performance,
			@RequestParam(required = false) String trainermobno, @RequestParam(required = false) String trainingfromdate,
			@RequestParam(required = false) String trainingtodate, @RequestParam(required = false) String testersus,
			@RequestParam(required = false) String testerunit, @RequestParam(required = false) String testerno,
			@RequestParam(required = false) String testername, @RequestParam(required = false) String testerrank,
			@RequestParam(required = false) String examstatus, @RequestParam(required = false) String exmremarks,
			ModelMap model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		Date date = new Date(System.currentTimeMillis());

		Session sessionGet = null;
		Transaction tx = null;

		try {

			sessionGet = HibernateUtilNA.getSessionFactory().openSession();
			tx = sessionGet.beginTransaction();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date ason1 = null;
			Date trainingfromdate1 = null;
			Date trainingtodate1 = null;
			
			if (ason != null && !ason.isEmpty()) {
				ason1 = new Date(dateFormat.parse(ason).getTime());
			}
			
			if (trainingfromdate != null && !trainingfromdate.isEmpty()) {
				trainingfromdate1 = new Date(dateFormat.parse(trainingfromdate).getTime());
			}

			if (trainingtodate != null && !trainingtodate.isEmpty()) {
				trainingtodate1 = new Date(dateFormat.parse(trainingtodate).getTime());
				
			}

			List<String> censusId = dao.getAnimalCensusIDByArmyNo(army_num);
			String firstcensusId = censusId.get(0);

			Query q5 = sessionGet.createSQLQuery(
					"select count(id) from tb_animal_training_history where census_id=:census_id and cast(ason_date as date)=:ason_date and status=:status");
			q5.setParameter("census_id", Integer.parseInt(firstcensusId));
			q5.setParameter("ason_date", ason1,org.hibernate.type.StandardBasicTypes.TIMESTAMP);
			q5.setParameter("status", 0);

			BigInteger result5 = (BigInteger) q5.uniqueResult();
			Long c5 = result5.longValue();
			if (c5 == 0) {
				TB_ANIMAL_TRAINING_HISTORY ts = new TB_ANIMAL_TRAINING_HISTORY();
				ts.setSus_no(sus_no);
				ts.setAson_Date(ason1);
				ts.setCreated_by(username);
				ts.setCreated_date(date);
				ts.setExam_status(Integer.parseInt(examstatus));
				ts.setTrg_status(Integer.parseInt(performance));
				ts.setTester_name(testername);
				ts.setTester_perno(testerno);
				ts.setTester_rank(testerrank);
				ts.setTester_remark(exmremarks);
				ts.setTester_sus(testersus);
				ts.setTrainer_mobno(Long.parseLong(trainermobno));
				ts.setStatus(0);
				ts.setTraining_from_date(trainingfromdate1);
				ts.setTraining_to_date(trainingtodate1);
				ts.setTrainer_name(trainername);
				ts.setTrainer_perno(trainerno);
				ts.setTrainer_rank(trainerrank);
				ts.setTrainer_sus(trainersus);
				ts.setValidate_date(ason1);
				ts.setCensus_id(Integer.parseInt(firstcensusId));
				sessionGet.save(ts);
			} else {

				String hqlUpdatePrevious4 = "UPDATE tb_animal_training_history " +
				        "SET exam_status=:exam_status, " +
				        "trg_status=:trg_status, " +
				        "tester_name=:tester_name, " +
				        "tester_perno=:tester_perno, " +
				        "tester_rank=:tester_rank, " +
				        "tester_remark=:tester_remark, " +
				        "tester_sus=:tester_sus, " +
				        "trainer_mobno=:trainer_mobno, " +
				        "training_from_date=:training_from_date, " +
				        "training_to_date=:training_to_date, " +
				        "trainer_name=:trainer_name, " +
				        "trainer_perno=:trainer_perno, " +
				        "trainer_rank=:trainer_rank, " +
				        "trainer_sus=:trainer_sus, " +
				        "validate_date=:validate_date " +
				        "WHERE census_id=:census_id AND cast(ason_date as date)=:ason_date AND status=0";

				Query updatePrevious4 = sessionGet.createSQLQuery(hqlUpdatePrevious4);

				// Setting parameters for the update query
				updatePrevious4.setParameter("exam_status", Integer.parseInt(examstatus));
				updatePrevious4.setParameter("trg_status", Integer.parseInt(performance));
				updatePrevious4.setParameter("tester_name", testername);
				updatePrevious4.setParameter("tester_perno", testerno);
				updatePrevious4.setParameter("tester_rank", testerrank);
				updatePrevious4.setParameter("tester_remark", exmremarks);
				updatePrevious4.setParameter("tester_sus", testersus);
				updatePrevious4.setParameter("trainer_mobno", Long.parseLong(trainermobno));
				updatePrevious4.setParameter("training_from_date", trainingfromdate1, org.hibernate.type.StandardBasicTypes.TIMESTAMP);
				updatePrevious4.setParameter("training_to_date", trainingtodate1, org.hibernate.type.StandardBasicTypes.TIMESTAMP);
				updatePrevious4.setParameter("trainer_name", trainername);
				updatePrevious4.setParameter("trainer_perno", trainerno);
				updatePrevious4.setParameter("trainer_rank", trainerrank);
				updatePrevious4.setParameter("trainer_sus", trainersus);
				updatePrevious4.setParameter("validate_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP);
				updatePrevious4.setParameter("census_id", Integer.parseInt(firstcensusId));
				updatePrevious4.setParameter("ason_date",ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP); // Should be java.util.Date object

				updatePrevious4.executeUpdate();

				
				
			}

			tx.commit();

			return "Data Updated Successfully.";

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return "Error updating data: " + e.getMessage();

		} finally {

			if (sessionGet != null) {
				sessionGet.close();
			}
		}
	}
	///
	@RequestMapping(value = "/saveDewData", method = RequestMethod.POST)
	public @ResponseBody String saveDewData(@RequestParam String ason,@RequestParam String sus_no, @RequestParam String unit_name,
			@RequestParam String army_num,
			@RequestParam(required = false) String drug, @RequestParam(required = false) String astatus1,
			@RequestParam(required = false) String allergy1, ModelMap model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		Date date = new Date(System.currentTimeMillis());

		Session sessionGet = null;
		Transaction tx = null;

		try {

			sessionGet = HibernateUtilNA.getSessionFactory().openSession();
			tx = sessionGet.beginTransaction();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date ason1 = null;
			
			if (ason != null && !ason.isEmpty()) {
				ason1 = new Date(dateFormat.parse(ason).getTime());
			}

			List<String> censusId = dao.getAnimalCensusIDByArmyNo(army_num);
			String firstcensusId = censusId.get(0);

			Query q7 = sessionGet.createSQLQuery(
					"select count(id) from tb_animal_drug_history where census_id=:census_id and cast(ason_date as date)=:ason_date and status=:status");
			q7.setParameter("census_id", Integer.parseInt(firstcensusId));
			q7.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP);
			q7.setParameter("status", 0);

			BigInteger result7 = (BigInteger) q7.uniqueResult();
			Long c7 = result7.longValue();
			if (c7 == 0) {
				TB_ANIMAL_DRUG_HISTORY ds = new TB_ANIMAL_DRUG_HISTORY();
				ds.setSus_no(sus_no);
				ds.setAson_Date(ason1);
				ds.setCreated_by(username);
				ds.setCreated_date(date);
				ds.setUsed_drug(drug);
				ds.setAllergy_status(Integer.parseInt(astatus1));
				ds.setAllergy(allergy1);
				ds.setStatus(0);
				ds.setCensus_id(Integer.parseInt(firstcensusId));
				sessionGet.save(ds);
			} else {
				String hqlUpdatePrevious6 = "UPDATE tb_animal_drug_history "
						+ "SET ason_date=:ason_date, used_drug=:used_drug, allergy_status=:allergy_status, allergy=:allergy "
						+ "WHERE census_id=:census_id AND cast(ason_date as date)=:ason_date AND status=0";

				Query updatePrevious6 = sessionGet.createSQLQuery(hqlUpdatePrevious6);

				updatePrevious6.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP); // Should be java.util.Date object
				updatePrevious6.setParameter("used_drug", drug);
				updatePrevious6.setParameter("allergy", allergy1);
				updatePrevious6.setParameter("allergy_status", Integer.parseInt(astatus1));
				updatePrevious6.setParameter("census_id", Integer.parseInt(firstcensusId));
				updatePrevious6.executeUpdate();
				
				
			}
			
			tx.commit();

			return "Data Updated Successfully.";

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return "Error updating data: " + e.getMessage();

		} finally {

			if (sessionGet != null) {
				sessionGet.close();
			}
		}
	}
	
	
	@RequestMapping(value = "/saveAwData", method = RequestMethod.POST)
	public @ResponseBody String saveAwData(@RequestParam String ason,@RequestParam String sus_no, @RequestParam String unit_name,
			@RequestParam String army_num, @RequestParam(required = false) String award,
			@RequestParam(required = false) String remarks1, @RequestParam(required = false) MultipartFile uploadfile, ModelMap model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		Date date = new Date(System.currentTimeMillis());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());

		Session sessionGet = null;
		Transaction tx = null;

		try {

			sessionGet = HibernateUtilNA.getSessionFactory().openSession();
			tx = sessionGet.beginTransaction();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date ason1 = null;
			
			
			if (ason != null && !ason.isEmpty()) {
				ason1 = new Date(dateFormat.parse(ason).getTime());
			}
			
			if (uploadfile.getSize() >  2 * 1024 * 1024) {
				return "File size should be less then 2 MB.";
			}
			
			String fname = "";
			DateWithTimeStampController timestamp = new DateWithTimeStampController();
			
				byte[] bytes = uploadfile.getBytes();
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if (fileValidation.check_RAR_File(bytes) || fileValidation.check_ZIP_File(bytes)) {
					String anlFilePath = session.getAttribute("animalFilePath").toString();
					File dir = new File(anlFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = uploadfile.getOriginalFilename();
					String extension = "";
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
				
					 String sus_no_name = sus_no.replace("/","-");
				
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_" + userid + "_AMLDOC." + extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				


			List<String> censusId = dao.getAnimalCensusIDByArmyNo(army_num);
			String firstcensusId = censusId.get(0);

			Query q8 = sessionGet.createSQLQuery(
					"select count(id) from tb_animal_award_history where census_id=:census_id and cast(ason_date as date)=:ason_date and status=:status");
			q8.setParameter("census_id", Integer.parseInt(firstcensusId));
			q8.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP);
			q8.setParameter("status", 0);

			BigInteger result8 = (BigInteger) q8.uniqueResult();
			Long c8 = result8.longValue();
			if (c8 == 0) {
				TB_ANIMAL_AWARD_HISTORY as = new TB_ANIMAL_AWARD_HISTORY();
				as.setSus_no(sus_no);
				as.setAson_Date(ason1);
				as.setCreated_by(username);
				as.setCreated_date(date);
				as.setStatus(0);
				as.setAward(Integer.parseInt(award));
				as.setRemarks(remarks1);
				as.setDoc_path(fname);
				as.setCensus_id(Integer.parseInt(firstcensusId));
				sessionGet.save(as);
			} else {
				String hqlUpdatePrevious7 = "UPDATE tb_animal_award_history "
						+ "SET ason_date=:ason_date, award=:award, remarks=:remarks "
						+ "WHERE census_id=:census_id AND cast(ason_date as date)=:ason_date AND status=0";

				Query updatePrevious7 = sessionGet.createSQLQuery(hqlUpdatePrevious7);

				updatePrevious7.setParameter("ason_date", ason1, org.hibernate.type.StandardBasicTypes.TIMESTAMP); // Should be java.util.Date object
				updatePrevious7.setParameter("award", Integer.parseInt(award));
				updatePrevious7.setParameter("remarks", remarks1);
				updatePrevious7.setParameter("census_id", Integer.parseInt(firstcensusId));
				updatePrevious7.executeUpdate();
				
				
			}
				}
			
			tx.commit();

			return "Data Updated Successfully.";

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return "Error updating data: " + e.getMessage();

		} finally {

			if (sessionGet != null) {
				sessionGet.close();
			}
		}
	}


	///////////////////////SEARCH ADCR//////////////////////////////
	
	
	@RequestMapping(value = "/admin/searchadcr", method = RequestMethod.GET)
	public ModelAndView searchadcr(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchadcr", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("roleAccess", roleAccess);
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		Mmap.put("msg", msg);
		return new ModelAndView("searchadcrTiles");
		
	}
	
	@RequestMapping(value = "/admin/SearchAttributeReportAdcr", method = RequestMethod.POST)
	public ModelAndView SearchAttributeReportAdcr(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_noSer", required = false) String sus_no,
			@RequestParam(value = "statusSer", required = false) String status,
			@RequestParam(value = "unit_nameSer", required = false) String unit_name,
			@RequestParam(value = "issue_dateSer", required = false) String issue_date)
	{
		
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchadcr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		if(status != "") {
			Mmap.put("status", status);
		}
		if(!sus_no.equals("") & sus_no.length() == 8) {
			Mmap.put("sus_no", sus_no);
			
			if(all.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).size()>0)
			{
				Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).get(0));
			}
			
			
			ArrayList<List<String>> list = dao.getSearchAttributeReportAdcr(status,sus_no,roleType,issue_date);	
			if(list.size() == 0)
			{
				Mmap.put("list", list);
			}
			else
			{
				Mmap.put("roleType", roleType);
				Mmap.put("list", list);
				Mmap.put("unit_name12", list.get(0).get(1));
				Mmap.put("issue_date12", list.get(0).get(3));
				Mmap.put("ap_date12", list.get(0).get(2));
				System.err.println( list.get(0).get(3));
			}
			
		}
		else if(sus_no.equals("") || sus_no.equals(null)|| sus_no == "" || sus_no == null){
			Mmap.put("msg", "Please Select SUS No.");
	    } 
		else if(validation.sus_noLength(sus_no) == false){
			Mmap.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:searchadcr");
		}	
		if(validation.checkUnit_nameLength(unit_name) == false){
			Mmap.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:searchadcr");
		}
		return new ModelAndView("searchadcrTiles");
	}
	
	@RequestMapping(value = "/admin/ViewADCRUrl",method = RequestMethod.POST)
	public ModelAndView ViewADCRUrl(@ModelAttribute("sus_noV") String sus_no,String unit_nameV, @ModelAttribute("issue_date1") String issue_date, ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA,Authentication authentication){
	
//		String  roleid = sessionA.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("searchadcr", roleid);	
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		if(!sus_no.equals("")) {
			model.put("sus_no", sus_no);
		}
		if(!unit_nameV.equals("")) {
			model.put("unit_name", unit_nameV);
		}
		
		if(!issue_date.equals("")) {
			model.put("issue_date", issue_date);
		}
		System.err.println("ason_dateV--"+issue_date);
		ArrayList<List<String>> getADCRReportList = dao.getADCRReportListForApproval1(sus_no, roleType, issue_date);
		 if (!getADCRReportList.isEmpty()) {
		        String ason = "";
		        
		        
		        for (List<String> record : getADCRReportList) {
		          
		            String asonDate = record.get(12);

		            
		            if (asonDate != null && !asonDate.isEmpty()) {
		            	ason = asonDate;
		            }
		        }
		        model.put("asonDate", ason);
		    }
		model.put("getADCRReportList", getADCRReportList);
		return new ModelAndView("view_search_adcrTile");
	}
	
	@RequestMapping(value = "/admin/ApprovedAdcrUrl",method = RequestMethod.POST)
	public ModelAndView ApprovedAdcrUrl(@ModelAttribute("sus_no2") String sus_no,@ModelAttribute("unit_name1") String unit_name,@ModelAttribute("issue_date1") String issue_date,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		/*String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchadcr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		String roleType = sessionA.getAttribute("roleType").toString();
        if(!roleType.equals("APP") & !roleType.equals("ALL")) {
                return new ModelAndView("AccessTiles");
        }
		model.put("sus_no", sus_no);
		model.put("unit_name", unit_name);
		if(!issue_date.equals("")) {
			model.put("issue_date12", issue_date);
			model.addAttribute("issue_date12", issue_date);
		}
		String  username = sessionA.getAttribute("username").toString();
		model.put("msg", dao.setApprovedadcr(sus_no,username,issue_date));	
		return new ModelAndView("searchadcrTiles");
	}
	
	@RequestMapping(value = "/admin/SeenADCRUrl",method = RequestMethod.POST)
	public ModelAndView SeenADCRUrl(@ModelAttribute("sus_noM") String sus_no, @ModelAttribute("issue_dateM") String issue_date, @ModelAttribute("ason_dateM") String ason_date,String unit_nameM,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA,Authentication authentication){
	
//		String  roleid = sessionA.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("searchadcr", roleid);	
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		if(!sus_no.equals("")) {
			model.put("sus_no", sus_no);
		}
		if(!unit_nameM.equals("")) {
			model.put("unit_name", unit_nameM);
		}
		if(!issue_date.equals("")) {
			model.put("issue_date12", issue_date);
			model.addAttribute("issue_date12", issue_date);
		}
		if(!ason_date.equals("")) {
			model.put("ap_date12", ason_date);
			model.addAttribute("ap_date12", ason_date);
			
			Date asonDate = null;
			String timeZone = "Asia/Kolkata";
			
			 asonDate = parseDateWithTimeZone(issue_date, "dd-MM-yyyy", timeZone);
			 
			   Date asonDate1 = null;
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(asonDate); 
				
				calendar.add(Calendar.DAY_OF_YEAR, 1);
			    Date validUpto = new Date(calendar.getTimeInMillis());
				 asonDate1 = validUpto;
				 String date1 = new SimpleDateFormat("dd-MM-yyyy").format(asonDate1);
				 model.put("ap_date", date1);
					model.addAttribute("ap_date", date1);
				 System.err.println("dateM--"+date1); 
			
		}
		
		List<Map<String, Object>> formation = dao.getFormationDetailsFromSusNo(sus_no);
		if(formation.size() > 0) {
			model.put("unit_name",formation.get(0).get("unit_name"));
			model.put("modification",formation.get(0).get("mod"));
			model.put("fmn",formation.get(0).get("cmd_name")+"/"+formation.get(0).get("coprs_name")+"/"+formation.get(0).get("div_name")+"/"+formation.get(0).get("bde_name"));
			model.put("loc_nrs",formation.get(0).get("loc")+"("+formation.get(0).get("nrs")+")");
		}
		List<String> wepe = dao.getwepeno(sus_no);
		if(wepe.size() != 0) {
			model.put("wep", dao.getwepeno(sus_no).get(0));
		}
		
		
		ArrayList<List<String>> getADCRReportList = dao.getADCRReportListForApproval(sus_no, roleType,issue_date);
		
		model.put("getADCRReportList", getADCRReportList);
		return new ModelAndView("seen_adcrTile");
	}
	
	 private Date parseDateWithTimeZone(String dateString, String format, String timeZone) {
         try {
             SimpleDateFormat dateFormat = new SimpleDateFormat(format);
             dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone)); 
             return dateFormat.parse(dateString); 
         } catch (ParseException e) {
             e.printStackTrace();
             return null; 
         }
     }
	 //Pending
	 @RequestMapping(value = "/getbasicDaTaTablePending", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getbasicdataTablePending(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getbasicdataTablePending(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getBasicdataTableCountPending", method = RequestMethod.POST)
		public @ResponseBody long getBasicdataTableCountPending(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getbasicdataTableCountPending(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 
	 @RequestMapping(value = "/getDepDaTaTablePending", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getDepDaTaTablePending(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getdeploumentdataTablePending(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getDepDaTaTableCountPending", method = RequestMethod.POST)
		public @ResponseBody long getDepDaTaTableCountPending(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getdeploymentdataTableCountPending(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 
	 @RequestMapping(value = "/getHMDaTaTablePending", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getHMDaTaTablePending(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getHMdataTablePending(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getHMDaTaTableCountPending", method = RequestMethod.POST)
		public @ResponseBody long getHMDaTaTableCountPending(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getHMdataTableCountPending(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 
	 @RequestMapping(value = "/getVCDaTaTablePending", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getVCDaTaTablePending(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getVCdataTablePending(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getVCDaTaTableCountPending", method = RequestMethod.POST)
		public @ResponseBody long getVCDaTaTableCountPending(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getVCdataTableCountPending(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 
	 @RequestMapping(value = "/getTRGDaTaTablePending", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getTRGDaTaTablePending(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getTRGdataTablePending(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getTRGDaTaTableCountPending", method = RequestMethod.POST)
		public @ResponseBody long getTRGDaTaTableCountPending(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getTRGdataTableCountPending(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 @RequestMapping(value = "/getVDDaTaTablePending", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getVDDaTaTablePending(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getVDdataTablePending(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getVDDaTaTableCountPending", method = RequestMethod.POST)
		public @ResponseBody long getVDDaTaTableCountPending(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getVDdataTableCountPending(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 @RequestMapping(value = "/getDWDaTaTablePending", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getDWDaTaTablePending(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getDWdataTablePending(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getDWDaTaTableCountPending", method = RequestMethod.POST)
		public @ResponseBody long getDWDaTaTableCountPending(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getDWdataTableCountPending(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 
	 @RequestMapping(value = "/getawDaTaTablePending", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getawDaTaTablePending(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getawdataTablePending(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getawDaTaTableCountPending", method = RequestMethod.POST)
		public @ResponseBody long getawDaTaTableCountPending(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getawdataTableCountPending(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 
	 @RequestMapping(value = "/getutDaTaTablePending", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getutDaTaTablePending(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname,String spl, String asondate, HttpSession sessionUserId) throws SQLException {
	     System.err.println("spl--" + spl);  // Log the received value
	     return dao.getutdataTablePending(startPage, pageLength, orderColunm, orderType, sus_no, unitname,spl, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getutDaTaTableCountPending", method = RequestMethod.POST)
		public @ResponseBody long getutDaTaTableCountPending(String orderColunm,String orderType,String sus_no,String unitname, String spl, String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getutdataTableCountPending(orderColunm, orderType,sus_no,unitname,spl,asondate,sessionUserId);
		}

	 
	 //Approved
	 @RequestMapping(value = "/getbasicDaTaTable", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getbasicdataTable(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getbasicdataTable(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getBasicdataTableCount", method = RequestMethod.POST)
		public @ResponseBody long getBasicdataTableCount(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getbasicdataTableCount(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 
	 @RequestMapping(value = "/getDepDaTaTable", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getDepDaTaTable(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getdeploumentdataTable(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getDepDaTaTableCount", method = RequestMethod.POST)
		public @ResponseBody long getDepDaTaTableCount(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getdeploymentdataTableCount(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 
	 @RequestMapping(value = "/getHMDaTaTable", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getHMDaTaTable(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getHMdataTable(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getHMDaTaTableCount", method = RequestMethod.POST)
		public @ResponseBody long getHMDaTaTableCount(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getHMdataTableCount(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 
	 @RequestMapping(value = "/getVCDaTaTable", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getVCDaTaTable(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getVCdataTable(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getVCDaTaTableCount", method = RequestMethod.POST)
		public @ResponseBody long getVCDaTaTableCount(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getVCdataTableCount(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 
	 @RequestMapping(value = "/getTRGDaTaTable", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getTRGDaTaTable(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getTRGdataTable(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getTRGDaTaTableCount", method = RequestMethod.POST)
		public @ResponseBody long getTRGDaTaTableCount(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getTRGdataTableCount(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 @RequestMapping(value = "/getVDDaTaTable", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getVDDaTaTable(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getVDdataTable(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getVDDaTaTableCount", method = RequestMethod.POST)
		public @ResponseBody long getVDDaTaTableCount(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getVDdataTableCount(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 @RequestMapping(value = "/getDWDaTaTable", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getDWDaTaTable(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getDWdataTable(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getDWDaTaTableCount", method = RequestMethod.POST)
		public @ResponseBody long getDWDaTaTableCount(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getDWdataTableCount(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 
	 @RequestMapping(value = "/getawDaTaTable", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getawDaTaTable(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) throws SQLException {
	     
	     return dao.getawdataTable(startPage, pageLength, orderColunm, orderType, sus_no, unitname, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getawDaTaTableCount", method = RequestMethod.POST)
		public @ResponseBody long getawDaTaTableCount(String orderColunm,String orderType,String sus_no,String unitname,String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getawdataTableCount(orderColunm, orderType,sus_no,unitname,asondate,sessionUserId);
		}
	 
	 @RequestMapping(value = "/getutDaTaTable", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getutDaTaTable(int startPage, int pageLength, String orderColunm,
	                                                                    String orderType, String sus_no, String unitname,String spl, String asondate, HttpSession sessionUserId) throws SQLException {
	     System.err.println("spl--" + spl);  // Log the received value
	     return dao.getutdataTable(startPage, pageLength, orderColunm, orderType, sus_no, unitname,spl, asondate, sessionUserId);
	 }

	 @RequestMapping(value = "/getutDaTaTableCount", method = RequestMethod.POST)
		public @ResponseBody long getutDaTaTableCount(String orderColunm,String orderType,String sus_no,String unitname, String spl, String asondate,HttpSession sessionUserId,String msg) throws SQLException {
			return dao.getutdataTableCount(orderColunm, orderType,sus_no,unitname,spl,asondate,sessionUserId);
		}

}
