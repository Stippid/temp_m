package com.controller.tms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Tms_AnimalDao;
import com.dao.tms.Tms_AnimalDaoImpl;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.TB_TMS_ANIMAL_HISTORY_DETAILS;
import com.models.Tms_animals_details;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Animal_Status_Controller_Search {

	@Autowired
	Tms_AnimalDao anmlsDao = new Tms_AnimalDaoImpl();

	@Autowired
	private RoleBaseMenuDAO roledao;

	AllMethodsControllerTMS alltms = new AllMethodsControllerTMS();
	Animals_DetailsController ab = new Animals_DetailsController();
	AllMethodsControllerOrbat orbt = new AllMethodsControllerOrbat();
	ValidationController validation = new ValidationController();

	////////////////////// Search Animal Status ////////////////////////

	@RequestMapping(value = "/Search_Animal_Status", method = RequestMethod.GET)
	public ModelAndView Search_Animal_Status(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Animal_Status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("search_status_animalsTiles");
	}

	@RequestMapping(value = "/search_st", method = RequestMethod.POST)
	public ModelAndView search_st12(ModelMap Mmap, HttpSession sessionA, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no01", required = false) String sus_no,
			@RequestParam(value = "unit_name01", required = false) String unit_name,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "from_date1", required = false) String fdate,
			@RequestParam(value = "to_date1", required = false) String tdate) throws ParseException {
     
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Animal_Status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		String fdt = request.getParameter("from_date1");
		String tdt = request.getParameter("to_date1");
		Date today_dt = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		if (fdt != "") {
			if (format.parse(fdt).compareTo(today_dt) > 0 && format.parse(fdt).compareTo(today_dt) != 0) {
				Mmap.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:Search_Animal_Status");
			}
		}
		if (tdt != "") {
			if (format.parse(tdt).compareTo(today_dt) > 0 && format.parse(tdt).compareTo(today_dt) != 0) {
				Mmap.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:Search_Animal_Status");
			}
		}
		if (fdt != "" && tdt != "") {
			if (alltms.CompareDate(fdt, tdt) == 0) {
				Mmap.put("msg", "To Date should not be less than From Date");
				return new ModelAndView("redirect:Search_Animal_Status");
			}
		}

		ArrayList<ArrayList<String>> list = anmlsDao.search_st(sus_no, unit_name, status, fdate, tdate, roleType);
		if (status.equals("")) {
			Mmap.put("msg", "Please Select Status");
		} else {
			Mmap.put("sus_no01", sus_no);
			if (sus_no != "") {
				if (validation.sus_noLength(sus_no) == false) {
					Mmap.put("msg", validation.sus_noMSG);
					return new ModelAndView("redirect:Search_Animal_Status");
				}
				Mmap.put("unit_name01", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
			}
			Mmap.put("status1", status);
			Mmap.put("from_date1", fdate);
			Mmap.put("to_date1", tdate);
			Mmap.put("list", list);
			Mmap.put("roleType", roleType);
		}
		return new ModelAndView("search_status_animalsTiles");
	}

	@RequestMapping(value = "/ViewSearchAnimal", method = RequestMethod.POST)
	public ModelAndView ViewSerachanimal(@ModelAttribute("sus_no12") String sus_no12, HttpSession sessionA,
			@ModelAttribute("unit_name12") String unit_name12, ModelMap model,
			@ModelAttribute("status12") String status, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Animal_Status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no12 = roleSusNo;
		}

		ArrayList<ArrayList<String>> list = anmlsDao.getpendinglist(sus_no12, roleType);
		if (list.size() == 0) {
			model.put("msg", "Data Not Available");
		} else {
			model.put("sus_no012", sus_no12);
			if (sus_no12 != "") {
				model.put("unit_name012", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no12, sessionA).get(0));
			}
			model.put("status12", status);
			model.put("roleType", roleType);
			model.put("list", list);
		}
		return new ModelAndView("View_animalsTiles");
	}

	@RequestMapping(value = "/admin/ViewSearchAnimalaprv", method = RequestMethod.POST)
	public ModelAndView ViewSearchAnimalaprv(@ModelAttribute("sus_no13") String sus_no13, HttpSession sessionA,
			@ModelAttribute("unit_name13") String unit_name13, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Animal_Status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no13 = roleSusNo;
		}

		ArrayList<ArrayList<String>> list = anmlsDao.getAnimalReportListappro(sus_no13);
		if (list.size() == 0) {
			model.put("msg", "Data Not Available");
		} else {
			model.put("sus_no", sus_no13);
			if (sus_no13 != "") {
				model.put("unit_name", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no13, sessionA).get(0));
			}
			model.put("roleType", roleType);
			model.put("list", list);
		}
		return new ModelAndView("View_animalsaprvTiles");
	}

	@RequestMapping(value = "/Show_Animal_Details", method = RequestMethod.POST)
	public ModelAndView Show_Animal_Details(@ModelAttribute("editId") int editId, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Animal_Status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Tms_animals_details animals_details = anmlsDao.gettms_animals_detailsByid(editId);
		

		Object anml_type = animals_details.getAnml_type();
		String sus_no = animals_details.getSus_no();
		if (sus_no != "" & orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).size() > 0) {
			model.put("unit_name", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		}
		

		// model.put("show_animal_details_cmnd",
		// anmlsDao.gettms_animals_detailsByid(editId));
		model.put("show_animal_details_cmnd", animals_details);

		if (anml_type.equals("Army Dog")) {
			model.put("getTypeOfDogList", ab.getTypeOfDogList(sessionA));
			model.put("getsplzList", ab.getsplzList(sessionA));
			model.put("getclrList", ab.getclrList(anml_type, sessionA));
			model.put("getbreedList", ab.getbreedList(anml_type, sessionA));
			model.put("getFitnessStatusList", ab.getFitnessStatusList(anml_type, sessionA));
			model.put("getSourceList", ab.getSourceList(anml_type, sessionA));
		}

		else {
			model.put("getAnimalTypeList", ab.getAnimalTypeList(sessionA));
			model.put("getclrList", ab.getclrList(anml_type, sessionA));
			model.put("getbreedList", ab.getbreedList(anml_type, sessionA));
			model.put("getFitnessStatusList", ab.getFitnessStatusList(anml_type, sessionA));
			model.put("getSourceList", ab.getSourceList(anml_type, sessionA));
		}
		return new ModelAndView("Show_animalsTiles");
	}

	@RequestMapping(value = "/ApprovedanmlUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedanmlUrl(@ModelAttribute("sus3") String sus_no1, HttpSession sessionA,
			@ModelAttribute("unit_name1") String unit_name, ModelMap model, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("APP") && !roleType.equals("ALL")) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no1 = roleSusNo;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from Tms_animals_details where sus_no=:sus_no and status='0'");
		q.setParameter("sus_no", sus_no1);
		@SuppressWarnings("unchecked")
		List<Tms_animals_details> list = (List<Tms_animals_details>) q.list();
		tx.commit();
		session.close();

		for (int i = 0; i < list.size(); i++) {
			TB_TMS_ANIMAL_HISTORY_DETAILS b = new TB_TMS_ANIMAL_HISTORY_DETAILS();

			b.setAnml_type(list.get(i).getAnml_type());
			b.setSus_no(list.get(i).getSus_no());
			// b.setUnit_name(list.get(i).getUnit_name());
			b.setType_dog(list.get(i).getType_dog());
			b.setSpecialization(list.get(i).getSpecialization());
			b.setMedals_desc_details(list.get(i).getMedals_desc_details());
			b.setAuthority(list.get(i).getAuthority());
			b.setAward_date(list.get(i).getAward_date());
			b.setUnit_where_awarded(list.get(i).getUnit_where_awarded());
			b.setArmy_num(list.get(i).getArmy_num());
			b.setRemount_no(list.get(i).getRemount_no());
			b.setDate_of_death(list.get(i).getDate_of_death());
			b.setSex(list.get(i).getSex());
			b.setDate_of_birth(list.get(i).getDate_of_birth());
			b.setImage(list.get(i).getImage());
			b.setMicrochip_no(list.get(i).getMicrochip_no());
			b.setDetails_of_dam(list.get(i).getDetails_of_dam());
			b.setDetails_of_sire(list.get(i).getDetails_of_sire());
			b.setAward_date(list.get(i).getAward_date());
			b.setAnimal_purchase_cost(list.get(i).getAnimal_purchase_cost());
			b.setAnimal_present_cost(list.get(i).getAnimal_present_cost());
			b.setAge(list.get(i).getAge());
			b.setTos(list.get(i).getTos());
			b.setTors(list.get(i).getTors());
			b.setSors(list.get(i).getSors());
			b.setSos(list.get(i).getSos());
			b.setDate_admitted(list.get(i).getDate_admitted());
			b.setYear(list.get(i).getYear());
			b.setAwared_remarks(list.get(i).getAwared_remarks());
			b.setDisposal_remarks(list.get(i).getDisposal_remarks());
			b.setFitness_deployment(list.get(i).getFitness_deployment());
			b.setFitness_status(list.get(i).getFitness_status());
			b.setType_equines(list.get(i).getFitness_status());
			b.setName_of_dog(list.get(i).getName_of_dog());
			b.setBreed(list.get(i).getBreed());
			b.setColour(list.get(i).getColour());
			b.setSource(list.get(i).getSource());
			b.setDisposal(list.get(i).getDisposal());
			b.setIssue_to_unit_name(list.get(i).getIssue_to_unit_name());
			b.setDisptrans(list.get(i).getDisptrans());
			b.setComd_sus_no(list.get(i).getComd_sus_no());
			// b.setComd_unit_name(list.get(i).getComd_unit_name());
			b.setDis_date(list.get(i).getDis_date());
			b.setIssue_date(list.get(i).getIssue_date());
			b.setRecord_status("Approved");

			String death = list.get(i).getDate_of_death();
			if (death != "" && !death.equals("")) {
				b.setRecord_status("Delete");
				anmlsDao.setDeleteAnimal(list.get(i).getId());
			}

			else {
				b.setRecord_status("Approved");
			}
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			sessionHQL.beginTransaction();
			sessionHQL.save(b);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();
		}
		String msg1 = anmlsDao.setApprovedAnimal(sus_no1);
		model.put("msg", msg1);
		ArrayList<ArrayList<String>> list1 = anmlsDao.getAnimalReportListappro(sus_no1);
		model.put("sus_no", sus_no1);
		if (sus_no1 != "") {
			model.put("unit_name", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no1, sessionA).get(0));
		}
		model.put("list1", list1);
		return new ModelAndView("search_status_animalsTiles");
	}

	@RequestMapping(value = "/rejectanmlUrl", method = RequestMethod.POST)
	public ModelAndView rejectanmlUrl(@ModelAttribute("sus") String sus_no, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionA) {

		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("APP") && !roleType.equals("ALL")) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from Tms_animals_details where sus_no=:sus_no and status='0'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<Tms_animals_details> list = (List<Tms_animals_details>) q.list();
		tx.commit();
		session.close();

		for (int i = 0; i < list.size(); i++) {
			TB_TMS_ANIMAL_HISTORY_DETAILS b = new TB_TMS_ANIMAL_HISTORY_DETAILS();

			b.setAnml_type(list.get(i).getAnml_type());
			b.setSus_no(list.get(i).getSus_no());
			// b.setUnit_name(list.get(i).getUnit_name());
			b.setType_dog(list.get(i).getType_dog());
			b.setSpecialization(list.get(i).getSpecialization());
			b.setMedals_desc_details(list.get(i).getMedals_desc_details());
			b.setAuthority(list.get(i).getAuthority());
			b.setAward_date(list.get(i).getAward_date());
			b.setUnit_where_awarded(list.get(i).getUnit_where_awarded());
			b.setArmy_num(list.get(i).getArmy_num());
			b.setRemount_no(list.get(i).getRemount_no());
			b.setDate_of_death(list.get(i).getDate_of_death());
			b.setSex(list.get(i).getSex());
			b.setDate_of_birth(list.get(i).getDate_of_birth());
			b.setImage(list.get(i).getImage());
			b.setMicrochip_no(list.get(i).getMicrochip_no());
			b.setDetails_of_dam(list.get(i).getDetails_of_dam());
			b.setDetails_of_sire(list.get(i).getDetails_of_sire());
			b.setAward_date(list.get(i).getAward_date());
			b.setAnimal_purchase_cost(list.get(i).getAnimal_purchase_cost());
			b.setAnimal_present_cost(list.get(i).getAnimal_present_cost());
			b.setAge(list.get(i).getAge());
			b.setTos(list.get(i).getTos());
			b.setTors(list.get(i).getTors());
			b.setSors(list.get(i).getSors());
			b.setSos(list.get(i).getSos());
			b.setDate_admitted(list.get(i).getDate_admitted());
			b.setYear(list.get(i).getYear());
			b.setAwared_remarks(list.get(i).getAwared_remarks());
			b.setDisposal_remarks(list.get(i).getDisposal_remarks());
			b.setFitness_deployment(list.get(i).getFitness_deployment());
			b.setFitness_status(list.get(i).getFitness_status());
			b.setType_equines(list.get(i).getFitness_status());
			b.setName_of_dog(list.get(i).getName_of_dog());
			b.setBreed(list.get(i).getBreed());
			b.setColour(list.get(i).getColour());
			b.setSource(list.get(i).getSource());
			b.setDisposal(list.get(i).getDisposal());
			b.setIssue_to_unit_name(list.get(i).getIssue_to_unit_name());
			b.setDisptrans(list.get(i).getDisptrans());
			b.setComd_sus_no(list.get(i).getComd_sus_no());
			// b.setComd_unit_name(list.get(i).getComd_unit_name());
			b.setDis_date(list.get(i).getDis_date());
			b.setIssue_date(list.get(i).getIssue_date());
			b.setRecord_status("Rejected");

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			sessionHQL.beginTransaction();
			sessionHQL.save(b);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();
		}
		String msg1 = anmlsDao.setRejectAnimal(sus_no);
		model.put("msg", msg1);
		return new ModelAndView("search_status_animalsTiles");
	}

	/////////////////////////////////////////////////////

	///////////////// Search Loc ////////////////////////////////

	@RequestMapping(value = "/search_animal_loc", method = RequestMethod.GET)
	public ModelAndView Search_animal_loc(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_animal_loc", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_animal_locTiles", "search_animal_loc_CMD", new Tms_animals_details());
	}

	@RequestMapping(value = "/search_loc_dog", method = RequestMethod.POST)
	public ModelAndView search_loc_dog(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "anml_type3", required = false) String anml_type,
			@RequestParam(value = "army_num1", required = false) String army_num,
			@RequestParam(value = "microchip_no4", required = false) String microchip_no) {

		String roleType = session.getAttribute("roleType").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_animal_loc", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.check20Length(army_num) == false) {
			Mmap.put("msg", validation.army_numMSG);
			return new ModelAndView("redirect:search_animal_loc");
		} else if (validation.check20Length(microchip_no) == false) {
			Mmap.put("msg", validation.microchip_noMSG);
			return new ModelAndView("redirect:search_animal_loc");
		}

		ArrayList<ArrayList<String>> listdog = anmlsDao.getanimallocstatus(anml_type, army_num, microchip_no, roleType);

		Mmap.put("anml_type3", anml_type);
		Mmap.put("army_num1", army_num);
		Mmap.put("microchip_no4", microchip_no);
		Mmap.put("listdog", listdog);
		Mmap.put("roleType", roleType);
		Mmap.put("msg", msg);
		return new ModelAndView("search_animal_locTiles");
	}

	@RequestMapping(value = "/search_loc_equ", method = RequestMethod.POST)
	public ModelAndView search_loc_equ(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "anml_type4", required = false) String anml_type,
			@RequestParam(value = "remount_no1", required = false) String remount_no,
			@RequestParam(value = "microchip_no5", required = false) String microchip_no) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_animal_loc", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.checkRemountNoLength(remount_no) == false) {
			Mmap.put("msg", validation.remount_noMSG);
			return new ModelAndView("redirect:search_animal_loc");
		}

		else if (validation.check20Length(microchip_no) == false) {
			Mmap.put("msg", validation.microchip_noMSG);
			return new ModelAndView("redirect:search_animal_loc");
		}

		String roleType = session.getAttribute("roleType").toString();

		ArrayList<ArrayList<String>> listequ = anmlsDao.getanimallocstatusequ(anml_type, remount_no, microchip_no,
				roleType);
		Mmap.put("anml_type4", anml_type);
		Mmap.put("remount_no1", remount_no);
		Mmap.put("microchip_no5", microchip_no);
		Mmap.put("listequ", listequ);
		Mmap.put("roleType", roleType);
		Mmap.put("msg", msg);
		return new ModelAndView("search_animal_locTiles");
	}

	@RequestMapping(value = "/admin/ViewSearchAnimalapprove", method = RequestMethod.POST)
	public ModelAndView ViewSerachanimal1(@ModelAttribute("army_num8") String army_num, HttpSession session,
			@ModelAttribute("microchip_no8") String microchip_no, ModelMap model,
			@ModelAttribute("anml_type8") String anml_type, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_animal_loc", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		model.put("army_num8", army_num);
		model.put("microchip_no8", microchip_no);
		model.put("anml_type8", anml_type);

		String roleType = session.getAttribute("roleType").toString();

		ArrayList<ArrayList<String>> list = anmlsDao.getAnimalapprovelocReportList(army_num, microchip_no, roleType);

		if (list.size() == 0) {
			model.put("msg", "Data Not Available ");
		} else {
			model.put("roleType", roleType);
			model.put("list", list);
		}
		return new ModelAndView("animal_loc_detailsTiles");
	}

	@RequestMapping(value = "/admin/ViewSearchAnimalequ", method = RequestMethod.POST)
	public ModelAndView ViewSearchAnimalequ(@ModelAttribute("remount_no9") String remount_no, HttpSession session,
			@ModelAttribute("microchip_no9") String microchip_no, ModelMap model,
			@ModelAttribute("anml_type9") String anml_type, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_animal_loc", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleType = session.getAttribute("roleType").toString();

		ArrayList<ArrayList<String>> list = anmlsDao.getAnimalReportListequ(remount_no, microchip_no, roleType);

		if (list.size() == 0) {
			model.put("msg", "Data Not Available ");
		} else {
			model.put("remount_no9", remount_no);
			model.put("microchip_no9", microchip_no);
			model.put("anml_type9", anml_type);
			model.put("roleType", roleType);
			model.put("list", list);
		}
		return new ModelAndView("animal_loc_detailsTilesequ");
	}

	@RequestMapping(value = "/Show_Animal_loc_Details", method = RequestMethod.POST)
	public ModelAndView Show_Animal_loc_Details(@Valid @Validated @ModelAttribute("editId") int entryId, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "anml_type13", required = false) String anml_type, Authentication authentication,
			HttpSession sessionA) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_animal_loc", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		model.put("anml_type13", anml_type);
		model.put("getAnimalTypeList", ab.getAnimalTypeList(sessionA));
		model.put("getTypeOfDogList", ab.getTypeOfDogList(sessionA));
		model.put("getsplzList", ab.getsplzList(sessionA));
		model.put("getclrList", ab.getclrList(anml_type, sessionA));
		model.put("getbreedList", ab.getbreedList(anml_type, sessionA));
		model.put("getFitnessStatusList", ab.getFitnessStatusList(anml_type, sessionA));
		model.put("getSourceList", ab.getSourceList(anml_type, sessionA));
		
	    Tms_animals_details animals_details = anmlsDao.gettms_animals_detailsByid(entryId);
		model.put("show_animal_details_loc_cmnd", animals_details);
		
		String sus_no = animals_details.getSus_no();
		if (sus_no != "" & orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).size() > 0) {
			model.put("unit_name", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		}
		
		return new ModelAndView("Show_animals_loc_statusTiles");
	}

	@RequestMapping(value = "/Show_Animal_loc_equDetails", method = RequestMethod.POST)
	public ModelAndView Show_Animal_loc_equDetails(@Valid @Validated @ModelAttribute("editId") int entryId,
			HttpSession session, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "anml_type14", required = false) String anml_type, Authentication authentication) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_animal_loc", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		model.put("anml_type13", anml_type);
		model.put("getAnimalTypeList", ab.getAnimalTypeList(session));
		model.put("getTypeOfDogList", ab.getTypeOfDogList(session));
		model.put("getsplzList", ab.getsplzList(session));
		model.put("getclrList", ab.getclrList(anml_type, session));
		model.put("getbreedList", ab.getbreedList(anml_type, session));
		model.put("getFitnessStatusList", ab.getFitnessStatusList(anml_type, session));
		model.put("getSourceList", ab.getSourceList(anml_type, session));
		
		Tms_animals_details animals_details = anmlsDao.gettms_animals_detailsByid(entryId);
		model.put("show_animal_details_loc_cmnd_equ", animals_details);
			
			String sus_no = animals_details.getSus_no();
			if (sus_no != "" & orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).size() > 0) {
				model.put("unit_name", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
			}
		return new ModelAndView("Show_animals_loc_equstatusTiles");
	}

	//////////////////////////////////////////////

	////////////////////// Summary of Animal Unit Wise /////////////////////////

	@RequestMapping(value = "/sum_of_anml_untwise", method = RequestMethod.GET)
	public ModelAndView sum_of_anml_untwise(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("sum_of_anml_untwise", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Summary_of_Animal_UnitwiseTiles");
	}

	@RequestMapping(value = "/dog_sum_list", method = RequestMethod.POST)
	public ModelAndView dog_sum_list(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "anml_type1", required = false) String anml_type) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("sum_of_anml_untwise", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		ArrayList<ArrayList<String>> list = anmlsDao.dog_sum_list(sus_no, unit_name, anml_type);
		Mmap.put("list", list);
		int c1 = 0;
		int c2 = 0;
		int c3 = 0;
		int c4 = 0;
		int c5 = 0;
		int c6 = 0;
		int c7 = 0;
		int c8 = 0;
		int c9 = 0;
		int c10 = 0;
		int c11 = 0;
		int c12 = 0;
		int c13 = 0;
		int c14 = 0;
		for (int i = 0; i < list.size(); i++) {
			c1 = c1 + Integer.parseInt(list.get(i).get(2));
			c2 = c2 + Integer.parseInt(list.get(i).get(3));
			c3 = c3 + Integer.parseInt(list.get(i).get(4));
			c4 = c4 + Integer.parseInt(list.get(i).get(5));
			c5 = c5 + Integer.parseInt(list.get(i).get(6));
			c6 = c6 + Integer.parseInt(list.get(i).get(7));
			c7 = c7 + Integer.parseInt(list.get(i).get(8));
			c8 = c8 + Integer.parseInt(list.get(i).get(9));
			c9 = c9 + Integer.parseInt(list.get(i).get(10));
			c10 = c10 + Integer.parseInt(list.get(i).get(11));
			c11 = c11 + Integer.parseInt(list.get(i).get(12));
			c12 = c12 + Integer.parseInt(list.get(i).get(13));
			c13 = c13 + Integer.parseInt(list.get(i).get(14));
			c14 = c14 + Integer.parseInt(list.get(i).get(15));
		}
		if (sus_no == "") {
			Mmap.put("msg", "Please Select SUS No");
		} else if (unit_name == "") {
			Mmap.put("msg", "Please Select Unit Name");
		} else if (anml_type.equals("")) {
			Mmap.put("msg", "Please Select Animal Type");
		} else {
			Mmap.put("sus_no1", sus_no);
			if (sus_no != "") {
				if (validation.sus_noLength(sus_no) == false) {
					Mmap.put("msg", validation.sus_noMSG);
					return new ModelAndView("redirect:sum_of_anml_untwise");
				}
				Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
			}
			Mmap.put("anml_type1", anml_type);
			Mmap.put("c1", c1);
			Mmap.put("c2", c2);
			Mmap.put("c3", c3);
			Mmap.put("c4", c4);
			Mmap.put("c5", c5);
			Mmap.put("c6", c6);
			Mmap.put("c7", c7);
			Mmap.put("c8", c8);
			Mmap.put("c9", c9);
			Mmap.put("c10", c10);
			Mmap.put("c11", c11);
			Mmap.put("c12", c12);
			Mmap.put("c13", c13);
			Mmap.put("c14", c14);
			Mmap.put("roleType", roleType);
			Mmap.put("msg", msg);
		}
		return new ModelAndView("Summary_of_Animal_UnitwiseTiles");
	}

	@RequestMapping(value = "/equ_sum_list", method = RequestMethod.POST)
	public ModelAndView equ_sum_list(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no2", required = false) String sus_no,
			@RequestParam(value = "unit_name2", required = false) String unit_name,
			@RequestParam(value = "anml_type2", required = false) String anml_type) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("sum_of_anml_untwise", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		ArrayList<ArrayList<String>> listequ = anmlsDao.equ_sum_list(sus_no, unit_name, anml_type);
		Mmap.put("listequ", listequ);
		int c1 = 0;
		int c2 = 0;
		int c3 = 0;
		int c4 = 0;
		int c5 = 0;
		int c6 = 0;
		int c7 = 0;
		int c8 = 0;
		int c9 = 0;
		int c10 = 0;
		int c11 = 0;
		int c12 = 0;
		int c13 = 0;
		int c14 = 0;
		int c15 = 0;
		int c16 = 0;
		int c17 = 0;
		int c18 = 0;
		int c19 = 0;
		int c20 = 0;
		int c21 = 0;
		int c22 = 0;
		int c23 = 0;
		int c24 = 0;
		int totalall = 0;
		int totalall1 = 0;
		for (int i = 0; i < listequ.size(); i++) {
			c1 = c1 + Integer.parseInt(listequ.get(i).get(2));
			c2 = c2 + Integer.parseInt(listequ.get(i).get(3));
			c3 = c3 + Integer.parseInt(listequ.get(i).get(4));
			c4 = c4 + Integer.parseInt(listequ.get(i).get(5));
			c5 = c5 + Integer.parseInt(listequ.get(i).get(6));
			c6 = c6 + Integer.parseInt(listequ.get(i).get(7));
			c7 = c7 + Integer.parseInt(listequ.get(i).get(8));
			c8 = c8 + Integer.parseInt(listequ.get(i).get(9));
			c9 = c9 + Integer.parseInt(listequ.get(i).get(10));
			c10 = c10 + Integer.parseInt(listequ.get(i).get(11));
			c11 = c11 + Integer.parseInt(listequ.get(i).get(12));
			c12 = c12 + Integer.parseInt(listequ.get(i).get(13));
			c13 = c13 + Integer.parseInt(listequ.get(i).get(14));
			c14 = c14 + Integer.parseInt(listequ.get(i).get(15));
			c15 = c15 + Integer.parseInt(listequ.get(i).get(16));
			c16 = c16 + Integer.parseInt(listequ.get(i).get(17));
			c17 = c17 + Integer.parseInt(listequ.get(i).get(18));
			c18 = c18 + Integer.parseInt(listequ.get(i).get(19));
			c19 = c19 + Integer.parseInt(listequ.get(i).get(20));
			c20 = c20 + Integer.parseInt(listequ.get(i).get(21));
			c21 = c21 + Integer.parseInt(listequ.get(i).get(22));
			c22 = c22 + Integer.parseInt(listequ.get(i).get(23));
			c23 = c23 + Integer.parseInt(listequ.get(i).get(24));
			c24 = c24 + Integer.parseInt(listequ.get(i).get(25));
		}
		if (sus_no == "") {
			Mmap.put("msg", "Please Select SUS No");
		} else if (unit_name == "") {
			Mmap.put("msg", "Please Select Unit Name");
		} else if (anml_type.equals("")) {
			Mmap.put("msg", "Please Select Animal Type");
		} else {
			Mmap.put("sus_no2", sus_no);
			if (sus_no != "") {
				if (validation.sus_noLength(sus_no) == false) {
					Mmap.put("msg", validation.sus_noMSG);
					return new ModelAndView("redirect:sum_of_anml_untwise");
				}
				Mmap.put("unit_name2", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
			}
			Mmap.put("anml_type2", anml_type);
			Mmap.put("c1", c1);
			Mmap.put("c2", c2);
			Mmap.put("c3", c3);
			Mmap.put("c4", c4);
			Mmap.put("c5", c5);
			Mmap.put("c6", c6);
			Mmap.put("c7", c7);
			Mmap.put("c8", c8);
			Mmap.put("c9", c9);
			Mmap.put("c10", c10);
			Mmap.put("c11", c11);
			Mmap.put("c12", c12);
			Mmap.put("c13", c13);
			Mmap.put("c14", c14);
			Mmap.put("c15", c15);
			Mmap.put("c16", c16);
			Mmap.put("c17", c17);
			Mmap.put("c18", c18);
			Mmap.put("c19", c19);
			Mmap.put("c20", c20);
			Mmap.put("c21", c21);
			Mmap.put("c22", c22);
			Mmap.put("c23", c23);
			Mmap.put("c24", c24);
			Mmap.put("totalall", totalall);
			Mmap.put("totalall1", totalall1);
			Mmap.put("roleType", roleType);
		}
		return new ModelAndView("Summary_of_Animal_UnitwiseTiles");
	}

	public List<String> getComdSusNoFromSusWithoutEnc(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select sus_no from Tbl_CodesForm where level_in_hierarchy='Command' and formation_code in (select substring(form_code_operation,1,1) from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and sus_no=:sus_no)");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<Miso_Orbat_Unt_Dtl> getUnitNameFromSusWithoutEnc(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no = 'Active'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		tx.commit();
		session.close();
		return list;
	}
}