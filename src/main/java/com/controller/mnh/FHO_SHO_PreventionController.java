package com.controller.mnh;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.FHO_SHO_PreventiveDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.models.T_Domain_Value;
import com.models.mnh.Tb_Med_Ch_Malaria;
import com.models.mnh.Tb_Med_Ch_STD;
import com.models.mnh.Tb_Med_Ch_Viral_Hepatitis;
import com.models.mnh.Tb_Med_Ch_h1n1_influenza;
import com.models.mnh.Tb_Med_Ch_health_education;
import com.models.mnh.Tb_Med_Ch_injuries_nea;
import com.models.mnh.Tb_Med_Ch_milk_quality;
import com.models.mnh.Tb_Med_Ch_pulmonary_tuberculosis;
import com.models.mnh.Tb_Med_Ch_workshops;
import com.models.mnh.Tb_Med_Pr_Preventive_Details;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class FHO_SHO_PreventionController {

	@Autowired
	RoleBaseMenuDAO roledao;

	@Autowired
	MNH_Common_DAO mnh1_Dao;
	@Autowired
	FHO_SHO_PreventiveDAO fho_sho_preventivedao;

	MNH_ValidationController validation = new MNH_ValidationController();

	MNH_CommonController mcommon = new MNH_CommonController();

	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();

	@RequestMapping(value = "/admin/mnh_input_preventivedetails", method = RequestMethod.GET)
	public ModelAndView mnh_search_preventivedetails(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		
		Boolean val = roledao.ScreenRedirect("mnh_input_hiv", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
         if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
       
        
		Mmap.put("msg", msg);
		Mmap.put("mo_1", mcommon.getMedSystemCode("MONTH", "", session));
		return new ModelAndView("mnh_search_preventiveTiles");
	}

	@RequestMapping(value = "/admin/search_preventiveurl", method = RequestMethod.POST)
	public ModelAndView search_preventiveurl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		Boolean val = roledao.ScreenRedirect("mnh_input_hiv", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
         if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		Mmap.put("date", date1);
		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		Mmap.put("ml_2", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		Mmap.put("ml_3", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		Mmap.put("ml_1", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("mo_1", mcommon.getMedSystemCode("MONTH", "", session));

		String SHO_NAME = request.getParameter("sho_name").toString();
		String SUS_NO = request.getParameter("sus_no1").toString();
		String MONTH = request.getParameter("month").toString();
		int YEAR = Integer.parseInt(request.getParameter("year").toString());
		int id = 0;

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		Long c = (Long) session1.createQuery(
				"select count(id) from  Tb_Med_Pr_Preventive_Details  where upper(sho_name)=:sho_name and sus_no=:sus_no and month=:month and  year=:year")
				.setParameter("sho_name", SHO_NAME.toUpperCase()).setParameter("sus_no", SUS_NO.toUpperCase())
				.setParameter("month", MONTH).setParameter("year", YEAR).uniqueResult();
		Tb_Med_Pr_Preventive_Details uf = new Tb_Med_Pr_Preventive_Details();
		if (c > 0) {

			id = (Integer) session1.createQuery(
					"select id from  Tb_Med_Pr_Preventive_Details  where upper(sho_name)=:sho_name and sus_no=:sus_no and month=:month and  year=:year")
					.setParameter("sho_name", SHO_NAME.toUpperCase()).setParameter("sus_no", SUS_NO.toUpperCase())
					.setParameter("month", MONTH).setParameter("year", YEAR).uniqueResult();
			uf = (Tb_Med_Pr_Preventive_Details) session1.get(Tb_Med_Pr_Preventive_Details.class, id);

			Mmap.put("update", "update");

		} else {
			uf.setSho_name(SHO_NAME);
			uf.setSus_no(SUS_NO);
			uf.setMonth(MONTH);
			uf.setYear(YEAR);
			uf.setAntilarval_measure_undertaken("yes");
			uf.setCreated_date(new Date());
			uf.setCreated_by(username);
			session1.save(uf);
			session1.clear();
			tx.commit();
			session1.close();
			String pr_id = String.valueOf(uf.getId());
			uf.setId(Integer.parseInt(pr_id));

		}

		ArrayList<ArrayList<String>> milk_list = fho_sho_preventivedao.get_milkquality_deatails(id);

		String specific_gravity = "";
		String fat_content = "";
		String total_solids = "";

		for (int i = 0; i < milk_list.size(); i++) {
			int x = i + 1;
			specific_gravity += "$('select#milk_spec_gravity" + x + "').val('" + milk_list.get(i).get(6) + "') ;\n";
			fat_content += "$('select#milk_fat_con" + x + "').val('" + milk_list.get(i).get(7) + "') ;\n";
			total_solids += "$('select#milk_tot_solid" + x + "').val('" + milk_list.get(i).get(8) + "') ;\n";
		}
		Mmap.put("specific_gravity", specific_gravity);
		Mmap.put("fat_content", fat_content);
		Mmap.put("total_solids", total_solids);
		Mmap.put("listMilkQualityDetails", milk_list);

		ArrayList<ArrayList<String>> health_list = fho_sho_preventivedao.gethealthedu_deatails(id);

		String per_category = "";
		for (int i = 0; i < health_list.size(); i++) {
			int x = i + 1;
			per_category += "$('select#h_personnel_category" + x + "').val('" + health_list.get(i).get(8) + "') ;\n";

		}
		Mmap.put("listhealtheduDetails", health_list);
		Mmap.put("per_category", per_category);

		ArrayList<ArrayList<String>> workshop_list = fho_sho_preventivedao.getworkshop_deatails(id);

		String total_group = "";

		for (int i = 0; i < workshop_list.size(); i++) {
			int x = i + 1;
			total_group += "$('select#target_group" + x + "').val('" + workshop_list.get(i).get(8) + "') ;\n";

		}
		Mmap.put("listworkshopDetails", workshop_list);
		Mmap.put("total_group", total_group);

		Mmap.put("listMalariyaDetails", fho_sho_preventivedao.getmalariyadeatails(id));
		Mmap.put("listStdDetails", fho_sho_preventivedao.getSTDdeatails(id));
		Mmap.put("listviral_hepatitisDetails", fho_sho_preventivedao.get_Viral_Hepatitis_deatails(id));
		Mmap.put("listh1n1Details", fho_sho_preventivedao.get_h1n1_deatails(id));
		ArrayList<ArrayList<String>> nea_list = fho_sho_preventivedao.get_injuries_nea_deatails(id);
		String mode_of_injuries = "";

		for (int i = 0; i < nea_list.size(); i++) {
			int x = i + 1;
			mode_of_injuries += "$('select#mode_of_injury" + x + "').val('" + nea_list.get(i).get(5) + "') ;\n";
		}

		Mmap.put("listneaDetails", nea_list);
		Mmap.put("mode_of_injuries", mode_of_injuries);
		Mmap.put("PREVENTION_INJURIESList", getPREVENTION_INJURIESList());

		ArrayList<ArrayList<String>> pul_tub_list = fho_sho_preventivedao.get_pulmonary_tuberculosis_deatails(id);

		String pers_no1 = "";
		String pers_no3 = "";
		String category = "";
		String rank = "";

		for (int i = 0; i < pul_tub_list.size(); i++) {
			int x = i + 1;
			pers_no1 += "$('select#personnel_number1" + x + "').val('" + pul_tub_list.get(i).get(9) + "') ;\n";
			pers_no3 += "$('select#personnel_number3" + x + "').val('" + pul_tub_list.get(i).get(10) + "') ;\n";
			category += "$('select#category" + x + "').val('" + pul_tub_list.get(i).get(11) + "') ;\n";
			rank += "$('select#rank" + x + "').val('" + pul_tub_list.get(i).get(12) + "') ;\n";

		}

		Mmap.put("listpultubDetails", pul_tub_list);
		Mmap.put("pers_no1", pers_no1);
		Mmap.put("pers_no3", pers_no3);
		Mmap.put("category", category);
		Mmap.put("rank", rank);
		return new ModelAndView("mnh_input_preventiveTiles", "add_prvnt_details_cmnd", uf);
	}

	@RequestMapping(value = "/admin/add_prvnt_details_act", method = RequestMethod.POST)
	public @ResponseBody String add_prvnt_details_act(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");
		String msg1 = "";

		Tb_Med_Pr_Preventive_Details pr = new Tb_Med_Pr_Preventive_Details();
		int id = (request.getParameter("id") == "") ? 0 : Integer.parseInt(request.getParameter("id"));
		// Integer.parseInt(request.getParameter("id"));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date_from = null;
		Date date_to = null;

		String sho_name = request.getParameter("sho_name");
		pr.setSho_name(sho_name);
		String sus_no = request.getParameter("sus_no");
		pr.setSus_no(sus_no);
		String month = request.getParameter("month");
		pr.setMonth(month);
		int year = Integer.parseInt(request.getParameter("year"));
		pr.setYear(year);
		String location = request.getParameter("location");
		pr.setLocation(location);

		String date_from1 = request.getParameter("date_from").toString();
		String date_to1 = request.getParameter("date_to").toString();
		if (date_from1 != null && date_from1 != "") {
			date_from = format.parse(date_from1);
		}
		pr.setDate_from(date_from);
		if (date_to1 != null && date_to1 != "") {
			date_to = format.parse(date_to1);
		}
		pr.setDate_to(date_to);

		String antilarval_measure_undertaken = request.getParameter("antilarval_measure_undertaken");
		pr.setAntilarval_measure_undertaken(antilarval_measure_undertaken);
		int residual_spray_done = (request.getParameter("residual_spray_done") == "") ? 0
				: Integer.parseInt(request.getParameter("residual_spray_done"));
		pr.setResidual_spray_done(residual_spray_done);
		int entomonological_survey_done = (request.getParameter("entomonological_survey_done") == "") ? 0
				: Integer.parseInt(request.getParameter("entomonological_survey_done"));
		pr.setEntomonological_survey_done(entomonological_survey_done);
		int units_inspected = (request.getParameter("units_inspected") == "") ? 0
				: Integer.parseInt(request.getParameter("units_inspected"));
		pr.setUnits_inspected(units_inspected);
		int sanitary_fittings_checked = (request.getParameter("sanitary_fittings_checked") == "") ? 0
				: Integer.parseInt(request.getParameter("sanitary_fittings_checked"));
		pr.setSanitary_fittings_checked(sanitary_fittings_checked);
		int number_of_free_chlorine_ckeck = (request.getParameter("number_of_free_chlorine_ckeck") == "") ? 0
				: Integer.parseInt(request.getParameter("number_of_free_chlorine_ckeck"));
		pr.setNumber_of_free_chlorine_ckeck(number_of_free_chlorine_ckeck);
		int chl_unsatisfactory = (request.getParameter("chl_unsatisfactory") == "") ? 0
				: Integer.parseInt(request.getParameter("chl_unsatisfactory"));
		pr.setChl_unsatisfactory(chl_unsatisfactory);
		int number_of_water_samples_sent_for_bacteriological_exam = (request
				.getParameter("number_of_water_samples_sent_for_bacteriological_exam") == "") ? 0
						: Integer.parseInt(
								request.getParameter("number_of_water_samples_sent_for_bacteriological_exam"));
		pr.setNumber_of_water_samples_sent_for_bacteriological_exam(
				number_of_water_samples_sent_for_bacteriological_exam);
		int bact_unsatisfactory = (request.getParameter("bact_unsatisfactory") == "") ? 0
				: Integer.parseInt(request.getParameter("bact_unsatisfactory"));
		pr.setBact_unsatisfactory(bact_unsatisfactory);
		int initial_inspection = (request.getParameter("initial_inspection") == "") ? 0
				: Integer.parseInt(request.getParameter("initial_inspection"));
		pr.setInitial_inspection(initial_inspection);
		int periodic_inspection = (request.getParameter("periodic_inspection") == "") ? 0
				: Integer.parseInt(request.getParameter("periodic_inspection"));
		pr.setPeriodic_inspection(periodic_inspection);

		int malaria_number_of_slides_checked = (request.getParameter("malaria_number_of_slides_checked") == "") ? 0
				: Integer.parseInt(request.getParameter("malaria_number_of_slides_checked"));
		pr.setMalaria_number_of_slides_checked(malaria_number_of_slides_checked);
		int malaria_p_vivax_officers = (request.getParameter("malaria_p_vivax_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("malaria_p_vivax_officers"));
		pr.setMalaria_p_vivax_officers(malaria_p_vivax_officers);
		int malaria_p_vivax_jcos = (request.getParameter("malaria_p_vivax_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("malaria_p_vivax_jcos"));
		pr.setMalaria_p_vivax_jcos(malaria_p_vivax_jcos);
		int malaria_p_vivax_ors = (request.getParameter("malaria_p_vivax_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("malaria_p_vivax_ors"));
		pr.setMalaria_p_vivax_ors(malaria_p_vivax_ors);
		int malaria_p_vivax_family = (request.getParameter("malaria_p_vivax_family") == "") ? 0
				: Integer.parseInt(request.getParameter("malaria_p_vivax_family"));
		pr.setMalaria_p_vivax_family(malaria_p_vivax_family);
		int malaria_p_falciparum_officers = (request.getParameter("malaria_p_falciparum_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("malaria_p_falciparum_officers"));
		pr.setMalaria_p_falciparum_officers(malaria_p_falciparum_officers);
		int malaria_p_falciparum_jcos = (request.getParameter("malaria_p_falciparum_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("malaria_p_falciparum_jcos"));
		pr.setMalaria_p_falciparum_jcos(malaria_p_falciparum_jcos);
		int malaria_p_falciparum_ors = (request.getParameter("malaria_p_falciparum_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("malaria_p_falciparum_ors"));
		pr.setMalaria_p_falciparum_ors(malaria_p_falciparum_ors);
		int malaria_p_falciparum_family = (request.getParameter("malaria_p_falciparum_family") == "") ? 0
				: Integer.parseInt(request.getParameter("malaria_p_falciparum_family"));
		pr.setMalaria_p_falciparum_family(malaria_p_falciparum_family);

		String filaria_number_of_slides_checked = request.getParameter("filaria_number_of_slides_checked");
		pr.setFilaria_number_of_slides_checked(filaria_number_of_slides_checked);
		int filaria_officers = (request.getParameter("filaria_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("filaria_officers"));
		pr.setFilaria_officers(filaria_officers);
		int filaria_jcos = (request.getParameter("filaria_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("filaria_jcos"));
		pr.setFilaria_jcos(filaria_jcos);
		int filaria_ors = (request.getParameter("filaria_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("filaria_ors"));
		pr.setFilaria_ors(filaria_ors);
		int filaria_family = (request.getParameter("filaria_family") == "") ? 0
				: Integer.parseInt(request.getParameter("filaria_family"));
		pr.setFilaria_family(filaria_family);

		int immunisation_bcg = (request.getParameter("immunisation_bcg") == "") ? 0
				: Integer.parseInt(request.getParameter("immunisation_bcg"));
		pr.setImmunisation_bcg(immunisation_bcg);
		int immunisation_polio = (request.getParameter("immunisation_polio") == "") ? 0
				: Integer.parseInt(request.getParameter("immunisation_polio"));
		pr.setImmunisation_polio(immunisation_polio);
		int immunisation_measles = (request.getParameter("immunisation_measles") == "") ? 0
				: Integer.parseInt(request.getParameter("immunisation_measles"));
		pr.setImmunisation_measles(immunisation_measles);
		int immunisation_dpt = (request.getParameter("immunisation_dpt") == "") ? 0
				: Integer.parseInt(request.getParameter("immunisation_dpt"));
		pr.setImmunisation_dpt(immunisation_dpt);
		int immunisation_hepatitis_b = (request.getParameter("immunisation_hepatitis_b") == "") ? 0
				: Integer.parseInt(request.getParameter("immunisation_hepatitis_b"));
		pr.setImmunisation_hepatitis_b(immunisation_hepatitis_b);
		int immunisation_dt = (request.getParameter("immunisation_dt") == "") ? 0
				: Integer.parseInt(request.getParameter("immunisation_dt"));
		pr.setImmunisation_dt(immunisation_dt);
		int immunisation_tt = (request.getParameter("immunisation_tt") == "") ? 0
				: Integer.parseInt(request.getParameter("immunisation_tt"));
		pr.setImmunisation_tt(immunisation_tt);

		int special_pulse_polio = (request.getParameter("special_pulse_polio") == "") ? 0
				: Integer.parseInt(request.getParameter("special_pulse_polio"));
		pr.setSpecial_pulse_polio(special_pulse_polio);
		int special_number_vaccinated = (request.getParameter("special_number_vaccinated") == "") ? 0
				: Integer.parseInt(request.getParameter("special_number_vaccinated"));
		pr.setSpecial_number_vaccinated(special_number_vaccinated);
		int special_other_campaigns = (request.getParameter("special_other_campaigns") == "") ? 0
				: Integer.parseInt(request.getParameter("special_other_campaigns"));
		pr.setSpecial_other_campaigns(special_other_campaigns);
		String special_details = request.getParameter("special_details");
		pr.setSpecial_details(special_details);

		int cources_hygiene_and_sanitation = (request.getParameter("cources_hygiene_and_sanitation") == "") ? 0
				: Integer.parseInt(request.getParameter("cources_hygiene_and_sanitation"));
		pr.setCources_hygiene_and_sanitation(cources_hygiene_and_sanitation);
		int cources_vector_control = (request.getParameter("cources_vector_control") == "") ? 0
				: Integer.parseInt(request.getParameter("cources_vector_control"));
		pr.setCources_vector_control(cources_vector_control);
		int cources_water_supply = (request.getParameter("cources_water_supply") == "") ? 0
				: Integer.parseInt(request.getParameter("cources_water_supply"));
		pr.setCources_water_supply(cources_water_supply);

		int training_post_graduate_training = (request.getParameter("training_post_graduate_training") == "") ? 0
				: Integer.parseInt(request.getParameter("training_post_graduate_training"));
		pr.setTraining_post_graduate_training(training_post_graduate_training);
		int training_internee_officer_training = (request.getParameter("training_internee_officer_training") == "") ? 0
				: Integer.parseInt(request.getParameter("training_internee_officer_training"));
		pr.setTraining_internee_officer_training(training_internee_officer_training);
		int training_health_assistance_training = (request.getParameter("training_health_assistance_training") == "")
				? 0
				: Integer.parseInt(request.getParameter("training_health_assistance_training"));
		pr.setTraining_health_assistance_training(training_health_assistance_training);
		int training_hygiene_sanitation_squad_training = (request
				.getParameter("training_hygiene_sanitation_squad_training") == "") ? 0
						: Integer.parseInt(request.getParameter("training_hygiene_sanitation_squad_training"));
		pr.setTraining_hygiene_sanitation_squad_training(training_hygiene_sanitation_squad_training);

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		try {
			if (id == 0) {
				pr.setCreated_date(new Date());
				pr.setCreated_by(username);
				session1.save(pr);
				// session1.flush();
				session1.clear();
				tx.commit();
				String pr_id = String.valueOf(pr.getId());
				msg = "Data saved Successfully";
				return pr_id;
			} else {

				String hql = "update Tb_Med_Pr_Preventive_Details set sho_name=:sho_name,"
						+ "sus_no=:sus_no,month=:month,year=:year,location=:location,date_from=:date_from,date_to=:date_to,"
						+ "antilarval_measure_undertaken=:antilarval_measure_undertaken,residual_spray_done=:residual_spray_done,"
						+ "entomonological_survey_done=:entomonological_survey_done,units_inspected=:units_inspected,"
						+ "sanitary_fittings_checked=:sanitary_fittings_checked,number_of_free_chlorine_ckeck=:number_of_free_chlorine_ckeck,"
						+ "chl_unsatisfactory=:chl_unsatisfactory,number_of_water_samples_sent_for_bacteriological_exam=:number_of_water_samples_sent_for_bacteriological_exam,"
						+ "bact_unsatisfactory=:bact_unsatisfactory,initial_inspection=:initial_inspection,periodic_inspection=:periodic_inspection,"
						+ "malaria_number_of_slides_checked=:malaria_number_of_slides_checked,malaria_p_vivax_officers=:malaria_p_vivax_officers,"
						+ "malaria_p_vivax_jcos=:malaria_p_vivax_jcos,malaria_p_vivax_ors=:malaria_p_vivax_ors,malaria_p_vivax_family=:malaria_p_vivax_family,"
						+ "malaria_p_falciparum_officers=:malaria_p_falciparum_officers,malaria_p_falciparum_jcos=:malaria_p_falciparum_jcos,"
						+ "malaria_p_falciparum_ors=:malaria_p_falciparum_ors,malaria_p_falciparum_family=:malaria_p_falciparum_family,filaria_number_of_slides_checked=:filaria_number_of_slides_checked,"
						+ "filaria_officers=:filaria_officers,filaria_jcos=:filaria_jcos,filaria_ors=:filaria_ors,filaria_family=:filaria_family,immunisation_bcg=:immunisation_bcg,"
						+ "immunisation_polio=:immunisation_polio,immunisation_measles=:immunisation_measles,immunisation_dpt=:immunisation_dpt,immunisation_hepatitis_b=:immunisation_hepatitis_b,"
						+ "immunisation_dt=:immunisation_dt,immunisation_tt=:immunisation_tt,special_pulse_polio=:special_pulse_polio,special_number_vaccinated=:special_number_vaccinated,"
						+ "special_other_campaigns=:special_other_campaigns,special_details=:special_details,cources_hygiene_and_sanitation=:cources_hygiene_and_sanitation,"
						+ "cources_vector_control=:cources_vector_control,cources_water_supply=:cources_water_supply,training_post_graduate_training=:training_post_graduate_training,"
						+ "training_internee_officer_training=:training_internee_officer_training,training_health_assistance_training=:training_health_assistance_training,"
						+ "training_hygiene_sanitation_squad_training=:training_hygiene_sanitation_squad_training "
						+ "where id=:id";

				Query query = session1.createQuery(hql).setParameter("sho_name", sho_name)
						.setParameter("sus_no", sus_no).setParameter("month", month).setParameter("year", year)
						.setParameter("location", location).setParameter("date_from", date_from)
						.setParameter("date_to", date_to)
						.setParameter("antilarval_measure_undertaken", antilarval_measure_undertaken)
						.setParameter("residual_spray_done", residual_spray_done)
						.setParameter("entomonological_survey_done", entomonological_survey_done)
						.setParameter("units_inspected", units_inspected)
						.setParameter("sanitary_fittings_checked", sanitary_fittings_checked)
						.setParameter("number_of_free_chlorine_ckeck", number_of_free_chlorine_ckeck)
						.setParameter("chl_unsatisfactory", chl_unsatisfactory)
						.setParameter("number_of_water_samples_sent_for_bacteriological_exam",
								number_of_water_samples_sent_for_bacteriological_exam)
						.setParameter("bact_unsatisfactory", bact_unsatisfactory)
						.setParameter("initial_inspection", initial_inspection)
						.setParameter("periodic_inspection", periodic_inspection)
						.setParameter("malaria_number_of_slides_checked", malaria_number_of_slides_checked)
						.setParameter("malaria_p_vivax_officers", malaria_p_vivax_officers)
						.setParameter("malaria_p_vivax_jcos", malaria_p_vivax_jcos)
						.setParameter("malaria_p_vivax_ors", malaria_p_vivax_ors)
						.setParameter("malaria_p_vivax_family", malaria_p_vivax_family)
						.setParameter("malaria_p_falciparum_officers", malaria_p_falciparum_officers)
						.setParameter("malaria_p_falciparum_jcos", malaria_p_falciparum_jcos)
						.setParameter("malaria_p_falciparum_ors", malaria_p_falciparum_ors)
						.setParameter("malaria_p_falciparum_family", malaria_p_falciparum_family)
						.setParameter("filaria_number_of_slides_checked", filaria_number_of_slides_checked)
						.setParameter("filaria_officers", filaria_officers).setParameter("filaria_jcos", filaria_jcos)
						.setParameter("filaria_ors", filaria_ors).setParameter("filaria_family", filaria_family)
						.setParameter("immunisation_bcg", immunisation_bcg)
						.setParameter("immunisation_polio", immunisation_polio)
						.setParameter("immunisation_measles", immunisation_measles)
						.setParameter("immunisation_dpt", immunisation_dpt)
						.setParameter("immunisation_hepatitis_b", immunisation_hepatitis_b)
						.setParameter("immunisation_dt", immunisation_dt)
						.setParameter("immunisation_tt", immunisation_tt)
						.setParameter("special_pulse_polio", special_pulse_polio)
						.setParameter("special_number_vaccinated", special_number_vaccinated)
						.setParameter("special_other_campaigns", special_other_campaigns)
						.setParameter("special_details", special_details)
						.setParameter("cources_hygiene_and_sanitation", cources_hygiene_and_sanitation)
						.setParameter("cources_vector_control", cources_vector_control)
						.setParameter("cources_water_supply", cources_water_supply)
						.setParameter("training_post_graduate_training", training_post_graduate_training)
						.setParameter("training_internee_officer_training", training_internee_officer_training)
						.setParameter("training_health_assistance_training", training_health_assistance_training)
						.setParameter("training_hygiene_sanitation_squad_training",
								training_hygiene_sanitation_squad_training)
						.setParameter("id", id);

				int rowCount = query.executeUpdate();
				tx.commit();
				// session1.close();

				if (rowCount > 0) {
					msg1 = "Data Updated Successfully";
				} else {
					msg1 = "Data Not Updated Successfully";
				}
			}
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg1 = "roll back transaction";
			} catch (RuntimeException rbe) {
				msg1 = "Couldn�t roll back transaction " + rbe;
			}
			throw e;
		} finally {
			if (session1 != null) {
				session1.close();
			}
		}

		return msg1;
	}

	@RequestMapping(value = "/admin/add_prvnt_details_act1", method = RequestMethod.POST)
	public @ResponseBody String add_prvnt_details_act1(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");
		String msg1 = "";
		Tb_Med_Pr_Preventive_Details pr = new Tb_Med_Pr_Preventive_Details();
		int id = Integer.parseInt(request.getParameter("id"));

		int causes_number_officers = (request.getParameter("causes_number_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("causes_number_officers"));
		int causes_number_jcos = (request.getParameter("causes_number_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("causes_number_jcos"));
		int causes_number_ors = (request.getParameter("causes_number_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("causes_number_ors"));
		int causes_rate_officers = (request.getParameter("causes_rate_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("causes_rate_officers"));
		int causes_rate_jcos = (request.getParameter("causes_rate_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("causes_rate_jcos"));
		int causes_rate_ors = (request.getParameter("causes_rate_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("causes_rate_ors"));

		int dengue_number_officers = (request.getParameter("dengue_number_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("dengue_number_officers"));
		int dengue_number_jcos = (request.getParameter("dengue_number_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("dengue_number_jcos"));
		int dengue_number_ors = (request.getParameter("dengue_number_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("dengue_number_ors"));
		int dengue_rate_officers = (request.getParameter("dengue_rate_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("dengue_rate_officers"));
		int dengue_rate_jcos = (request.getParameter("dengue_rate_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("dengue_rate_jcos"));
		int dengue_rate_ors = (request.getParameter("dengue_rate_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("dengue_rate_ors"));
		String dengue_unit_name = request.getParameter("dengue_unit_name");

		int chikungunya_number_officers = (request.getParameter("chikungunya_number_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("chikungunya_number_officers"));
		int chikungunya_number_jcos = (request.getParameter("chikungunya_number_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("chikungunya_number_jcos"));
		int chikungunya_number_ors = (request.getParameter("chikungunya_number_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("chikungunya_number_ors"));
		int chikungunya_rate_officers = (request.getParameter("chikungunya_rate_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("chikungunya_rate_officers"));
		int chikungunya_rate_jcos = (request.getParameter("chikungunya_rate_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("chikungunya_rate_jcos"));
		int chikungunya_rate_ors = (request.getParameter("chikungunya_rate_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("chikungunya_rate_ors"));
		String chikungunya_unit_name = request.getParameter("chikungunya_unit_name");

		int immune_surveillance_number_officers = (request.getParameter("immune_surveillance_number_officers") == "")
				? 0
				: Integer.parseInt(request.getParameter("immune_surveillance_number_officers"));
		int immune_surveillance_number_jcos = (request.getParameter("immune_surveillance_number_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("immune_surveillance_number_jcos"));
		int immune_surveillance_number_ors = (request.getParameter("immune_surveillance_number_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("immune_surveillance_number_ors"));
		int immune_surveillance_rate_officers = (request.getParameter("immune_surveillance_rate_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("immune_surveillance_rate_officers"));
		int immune_surveillance_rate_jcos = (request.getParameter("immune_surveillance_rate_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("immune_surveillance_rate_jcos"));
		int immune_surveillance_rate_ors = (request.getParameter("immune_surveillance_rate_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("immune_surveillance_rate_ors"));

		int diarrhoea_number_officers = (request.getParameter("diarrhoea_number_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("diarrhoea_number_officers"));
		int diarrhoea_number_jcos = (request.getParameter("diarrhoea_number_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("diarrhoea_number_jcos"));
		int diarrhoea_number_ors = (request.getParameter("diarrhoea_number_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("diarrhoea_number_ors"));
		int diarrhoea_rate_officers = (request.getParameter("diarrhoea_rate_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("diarrhoea_rate_officers"));
		int diarrhoea_rate_jcos = (request.getParameter("diarrhoea_rate_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("diarrhoea_rate_jcos"));
		int diarrhoea_rate_ors = (request.getParameter("diarrhoea_rate_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("diarrhoea_rate_ors"));

		int respiratory_number_officers = (request.getParameter("respiratory_number_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("respiratory_number_officers"));
		int respiratory_number_jcos = (request.getParameter("respiratory_number_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("respiratory_number_jcos"));
		int respiratory_number_ors = (request.getParameter("respiratory_number_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("respiratory_number_ors"));
		int respiratory_rate_officers = (request.getParameter("respiratory_rate_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("respiratory_rate_officers"));
		int respiratory_rate_jcos = (request.getParameter("respiratory_rate_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("respiratory_rate_jcos"));
		int respiratory_rate_ors = (request.getParameter("respiratory_rate_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("respiratory_rate_ors"));

		int skin_disease_number_officers = (request.getParameter("skin_disease_number_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("skin_disease_number_officers"));
		int skin_disease_number_jcos = (request.getParameter("skin_disease_number_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("skin_disease_number_jcos"));
		int skin_disease_number_ors = (request.getParameter("skin_disease_number_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("skin_disease_number_ors"));
		int skin_disease_rate_officers = (request.getParameter("skin_disease_rate_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("skin_disease_rate_officers"));
		int skin_disease_rate_jcos = (request.getParameter("skin_disease_rate_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("skin_disease_rate_jcos"));
		int skin_disease_rate_ors = (request.getParameter("skin_disease_rate_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("skin_disease_rate_ors"));

		int effect_of_heat_number_officers = (request.getParameter("effect_of_heat_number_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_heat_number_officers"));
		int effect_of_heat_number_jcos = (request.getParameter("effect_of_heat_number_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_heat_number_jcos"));
		int effect_of_heat_number_ors = (request.getParameter("effect_of_heat_number_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_heat_number_ors"));
		int effect_of_heat_rate_officers = (request.getParameter("effect_of_heat_rate_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_heat_rate_officers"));
		int effect_of_heat_rate_jcos = (request.getParameter("effect_of_heat_rate_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_heat_rate_jcos"));
		int effect_of_heat_rate_ors = (request.getParameter("effect_of_heat_rate_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_heat_rate_ors"));

		int effect_of_cold_number_officers = (request.getParameter("effect_of_cold_number_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_cold_number_officers"));
		int effect_of_cold_number_jcos = (request.getParameter("effect_of_cold_number_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_cold_number_jcos"));
		int effect_of_cold_number_ors = (request.getParameter("effect_of_cold_number_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_cold_number_ors"));
		int effect_of_cold_rate_officers = (request.getParameter("effect_of_cold_rate_officers") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_cold_rate_officers"));
		int effect_of_cold_rate_jcos = (request.getParameter("effect_of_cold_rate_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_cold_rate_jcos"));
		int effect_of_cold_rate_ors = (request.getParameter("effect_of_cold_rate_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_cold_rate_ors"));

		int effect_of_high_altitude_number_officers = (request
				.getParameter("effect_of_high_altitude_number_officers") == "") ? 0
						: Integer.parseInt(request.getParameter("effect_of_high_altitude_number_officers"));
		int effect_of_high_altitude_number_jcos = (request.getParameter("effect_of_high_altitude_number_jcos") == "")
				? 0
				: Integer.parseInt(request.getParameter("effect_of_high_altitude_number_jcos"));
		int effect_of_high_altitude_number_ors = (request.getParameter("effect_of_high_altitude_number_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_high_altitude_number_ors"));
		int effect_of_high_altitude_rate_officers = (request
				.getParameter("effect_of_high_altitude_rate_officers") == "") ? 0
						: Integer.parseInt(request.getParameter("effect_of_high_altitude_rate_officers"));
		int effect_of_high_altitude_rate_jcos = (request.getParameter("effect_of_high_altitude_rate_jcos") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_high_altitude_rate_jcos"));
		int effect_of_high_altitude_rate_ors = (request.getParameter("effect_of_high_altitude_rate_ors") == "") ? 0
				: Integer.parseInt(request.getParameter("effect_of_high_altitude_rate_ors"));

		int number_of_dog_bites = (request.getParameter("number_of_dog_bites") == "") ? 0
				: Integer.parseInt(request.getParameter("number_of_dog_bites"));
		int number_of_rabies_cases = (request.getParameter("number_of_rabies_cases") == "") ? 0
				: Integer.parseInt(request.getParameter("number_of_rabies_cases"));
		int number_of_snake_bite_cases = (request.getParameter("number_of_snake_bite_cases") == "") ? 0
				: Integer.parseInt(request.getParameter("number_of_snake_bite_cases"));

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		try {
			if (id != 0) {
				String hql = "update Tb_Med_Pr_Preventive_Details set causes_number_officers=:causes_number_officers,"
						+ "causes_number_jcos=:causes_number_jcos,causes_number_ors=:causes_number_ors,causes_rate_officers=:causes_rate_officers,causes_rate_jcos=:causes_rate_jcos,"
						+ "causes_rate_ors=:causes_rate_ors,dengue_number_officers=:dengue_number_officers,"
						+ "dengue_number_jcos=:dengue_number_jcos,dengue_number_ors=:dengue_number_ors,"
						+ "dengue_rate_officers=:dengue_rate_officers,dengue_rate_jcos=:dengue_rate_jcos,"
						+ "dengue_rate_ors=:dengue_rate_ors,dengue_unit_name=:dengue_unit_name,chikungunya_number_officers=:chikungunya_number_officers,"
						+ "chikungunya_number_jcos=:chikungunya_number_jcos,chikungunya_number_ors=:chikungunya_number_ors,chikungunya_rate_officers=:chikungunya_rate_officers,"
						+ "chikungunya_rate_jcos=:chikungunya_rate_jcos,chikungunya_rate_ors=:chikungunya_rate_ors,chikungunya_unit_name=:chikungunya_unit_name,"

						+ "immune_surveillance_number_officers=:immune_surveillance_number_officers,immune_surveillance_number_jcos=:immune_surveillance_number_jcos,"
						+ "immune_surveillance_number_ors=:immune_surveillance_number_ors,immune_surveillance_rate_officers=:immune_surveillance_rate_officers,"
						+ "immune_surveillance_rate_jcos=:immune_surveillance_rate_jcos,immune_surveillance_rate_ors=:immune_surveillance_rate_ors,"

						+ "diarrhoea_number_officers=:diarrhoea_number_officers,diarrhoea_number_jcos=:diarrhoea_number_jcos,diarrhoea_number_ors=:diarrhoea_number_ors,"
						+ "diarrhoea_rate_officers=:diarrhoea_rate_officers,diarrhoea_rate_jcos=:diarrhoea_rate_jcos,diarrhoea_rate_ors=:diarrhoea_rate_ors,"
						+ "respiratory_number_officers=:respiratory_number_officers,respiratory_number_jcos=:respiratory_number_jcos,respiratory_number_ors=:respiratory_number_ors,"
						+ "respiratory_rate_officers=:respiratory_rate_officers,respiratory_rate_jcos=:respiratory_rate_jcos,respiratory_rate_ors=:respiratory_rate_ors,"
						+ "skin_disease_number_officers=:skin_disease_number_officers,skin_disease_number_jcos=:skin_disease_number_jcos,skin_disease_number_ors=:skin_disease_number_ors,"
						+ "skin_disease_rate_officers=:skin_disease_rate_officers,skin_disease_rate_jcos=:skin_disease_rate_jcos,skin_disease_rate_ors=:skin_disease_rate_ors,"
						+ "effect_of_heat_number_officers=:effect_of_heat_number_officers,effect_of_heat_number_jcos=:effect_of_heat_number_jcos,effect_of_heat_number_ors=:effect_of_heat_number_ors,"
						+ "effect_of_heat_rate_officers=:effect_of_heat_rate_officers,effect_of_heat_rate_jcos=:effect_of_heat_rate_jcos,effect_of_heat_rate_ors=:effect_of_heat_rate_ors,"

						+ "effect_of_cold_number_officers=:effect_of_cold_number_officers,effect_of_cold_number_jcos=:effect_of_cold_number_jcos,effect_of_cold_number_ors=:effect_of_cold_number_ors,"
						+ "effect_of_cold_rate_officers=:effect_of_cold_rate_officers,effect_of_cold_rate_jcos=:effect_of_cold_rate_jcos,effect_of_cold_rate_ors=:effect_of_cold_rate_ors,"
						+ "effect_of_high_altitude_number_officers=:effect_of_high_altitude_number_officers,effect_of_high_altitude_number_jcos=:effect_of_high_altitude_number_jcos,"

						+ "effect_of_high_altitude_number_ors=:effect_of_high_altitude_number_ors,effect_of_high_altitude_rate_officers=:effect_of_high_altitude_rate_officers,"
						+ "effect_of_high_altitude_rate_jcos=:effect_of_high_altitude_rate_jcos,effect_of_high_altitude_rate_ors=:effect_of_high_altitude_rate_ors,"
						+ "number_of_dog_bites=:number_of_dog_bites,number_of_rabies_cases=:number_of_rabies_cases,number_of_snake_bite_cases=:number_of_snake_bite_cases "
						+ "where id=:id";

				Query query = session1.createQuery(hql).setParameter("causes_number_officers", causes_number_officers)
						.setParameter("causes_number_jcos", causes_number_jcos)
						.setParameter("causes_number_ors", causes_number_ors)
						.setParameter("causes_rate_officers", causes_rate_officers)
						.setParameter("causes_rate_jcos", causes_rate_jcos)
						.setParameter("causes_rate_ors", causes_rate_ors)
						.setParameter("dengue_number_officers", dengue_number_officers)
						.setParameter("dengue_number_jcos", dengue_number_jcos)
						.setParameter("dengue_number_ors", dengue_number_ors)
						.setParameter("dengue_rate_officers", dengue_rate_officers)
						.setParameter("dengue_rate_jcos", dengue_rate_jcos)
						.setParameter("dengue_rate_ors", dengue_rate_ors)
						.setParameter("dengue_unit_name", dengue_unit_name)
						.setParameter("chikungunya_number_officers", chikungunya_number_officers)
						.setParameter("chikungunya_number_jcos", chikungunya_number_jcos)
						.setParameter("chikungunya_number_ors", chikungunya_number_ors)
						.setParameter("chikungunya_rate_officers", chikungunya_rate_officers)
						.setParameter("chikungunya_rate_jcos", chikungunya_rate_jcos)
						.setParameter("chikungunya_rate_ors", chikungunya_rate_ors)
						.setParameter("chikungunya_unit_name", chikungunya_unit_name)

						.setParameter("immune_surveillance_number_officers", immune_surveillance_number_officers)
						.setParameter("immune_surveillance_number_jcos", immune_surveillance_number_jcos)
						.setParameter("immune_surveillance_number_ors", immune_surveillance_number_ors)
						.setParameter("immune_surveillance_rate_officers", immune_surveillance_rate_officers)
						.setParameter("immune_surveillance_rate_jcos", immune_surveillance_rate_jcos)
						.setParameter("immune_surveillance_rate_ors", immune_surveillance_rate_ors)

						.setParameter("diarrhoea_number_officers", diarrhoea_number_officers)
						.setParameter("diarrhoea_number_jcos", diarrhoea_number_jcos)
						.setParameter("diarrhoea_number_ors", diarrhoea_number_ors)
						.setParameter("diarrhoea_rate_officers", diarrhoea_rate_officers)
						.setParameter("diarrhoea_rate_jcos", diarrhoea_rate_jcos)
						.setParameter("diarrhoea_rate_ors", diarrhoea_rate_ors)
						.setParameter("respiratory_number_officers", respiratory_number_officers)
						.setParameter("respiratory_number_jcos", respiratory_number_jcos)
						.setParameter("respiratory_number_ors", respiratory_number_ors)
						.setParameter("respiratory_rate_officers", respiratory_rate_officers)
						.setParameter("respiratory_rate_jcos", respiratory_rate_jcos)
						.setParameter("respiratory_rate_ors", respiratory_rate_ors)
						.setParameter("skin_disease_number_officers", skin_disease_number_officers)
						.setParameter("skin_disease_number_jcos", skin_disease_number_jcos)
						.setParameter("skin_disease_number_ors", skin_disease_number_ors)
						.setParameter("skin_disease_rate_officers", skin_disease_rate_officers)
						.setParameter("skin_disease_rate_jcos", skin_disease_rate_jcos)
						.setParameter("skin_disease_rate_ors", skin_disease_rate_ors)
						.setParameter("effect_of_heat_number_officers", effect_of_heat_number_officers)
						.setParameter("effect_of_heat_number_jcos", effect_of_heat_number_jcos)
						.setParameter("effect_of_heat_number_ors", effect_of_heat_number_ors)
						.setParameter("effect_of_heat_rate_officers", effect_of_heat_rate_officers)
						.setParameter("effect_of_heat_rate_jcos", effect_of_heat_rate_jcos)
						.setParameter("effect_of_heat_rate_ors", effect_of_heat_rate_ors)
						.setParameter("effect_of_cold_number_officers", effect_of_cold_number_officers)
						.setParameter("effect_of_cold_number_jcos", effect_of_cold_number_jcos)
						.setParameter("effect_of_cold_number_ors", effect_of_cold_number_ors)
						.setParameter("effect_of_cold_rate_officers", effect_of_cold_rate_officers)
						.setParameter("effect_of_cold_rate_jcos", effect_of_cold_rate_jcos)
						.setParameter("effect_of_cold_rate_ors", effect_of_cold_rate_ors)
						.setParameter("effect_of_high_altitude_number_officers",
								effect_of_high_altitude_number_officers)
						.setParameter("effect_of_high_altitude_number_jcos", effect_of_high_altitude_number_jcos)
						.setParameter("effect_of_high_altitude_number_ors", effect_of_high_altitude_number_ors)
						.setParameter("effect_of_high_altitude_rate_officers", effect_of_high_altitude_rate_officers)
						.setParameter("effect_of_high_altitude_rate_jcos", effect_of_high_altitude_rate_jcos)
						.setParameter("effect_of_high_altitude_rate_ors", effect_of_high_altitude_rate_ors)
						.setParameter("number_of_dog_bites", number_of_dog_bites)
						.setParameter("number_of_rabies_cases", number_of_rabies_cases)
						.setParameter("number_of_snake_bite_cases", number_of_snake_bite_cases).setParameter("id", id);

				int rowCount = query.executeUpdate();
				tx.commit();
				// session1.close();
				if (rowCount > 0) {
					msg1 = "Data Updated Successfully";
				} else {
					msg1 = "Data Not Updated Successfully";
				}
			}
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg1 = "roll back transaction";
			} catch (RuntimeException rbe) {
				msg1 = "Couldn�t roll back transaction " + rbe;
			}
			throw e;
		} finally {
			if (session1 != null) {
				session1.close();
			}
		}

		return msg1;
	}

	@RequestMapping(value = "admin/updateMalaria", method = RequestMethod.POST)
	@ResponseBody
	public String updateMalaria(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String msg = "";
		int count = Integer.parseInt(request.getParameter("malariaoldcount").toString());
		int newcount = Integer.parseInt(request.getParameter("malaria_count").toString());

		Session session3 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session3.beginTransaction();

		try {
			String hql2 = "update Tb_Med_Pr_Preventive_Details set modified_by=:modified_by ,modified_date=:modified_date ,malaria_number_officers=:malaria_number_officers, malaria_number_jcos=:malaria_number_jcos,malaria_number_ors=:malaria_number_ors,malaria_rate_officers=:malaria_rate_officers,malaria_rate_jcos=:malaria_rate_jcos,malaria_rate_ors=:malaria_rate_ors  where  id=:id";
			Query query2 = session3.createQuery(hql2)
					.setInteger("malaria_number_officers",
							Integer.parseInt(request.getParameter("malaria_number_officers")))
					.setInteger("malaria_number_jcos", Integer.parseInt(request.getParameter("malaria_number_jcos")))
					.setInteger("malaria_number_ors", Integer.parseInt(request.getParameter("malaria_number_ors")))
					.setInteger("malaria_rate_officers",
							Integer.parseInt(request.getParameter("malaria_rate_officers")))
					.setInteger("malaria_rate_ors", Integer.parseInt(request.getParameter("malaria_rate_ors")))
					.setInteger("id", Integer.parseInt(request.getParameter("p_id")))
					.setInteger("malaria_rate_jcos", Integer.parseInt(request.getParameter("malaria_rate_jcos")))
					.setString("modified_by", username).setTimestamp("modified_date", new Date());

			msg = query2.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";

			for (int i = 1; i <= count; i++) {
				String hql = "update Tb_Med_Ch_Malaria set modified_by=:modified_by ,modified_date=:modified_date , unit_name=:unit_name, bt=:bt , mt=:mt,fl=:fl ,fi=:fi,p_id=:p_id  where  id=:id";
				Query query = session3.createQuery(hql)
						.setInteger("fi", Integer.parseInt(request.getParameter("fi" + i)))
						.setInteger("fl", Integer.parseInt(request.getParameter("fl" + i)))
						.setInteger("mt", Integer.parseInt(request.getParameter("mt" + i)))
						.setInteger("bt", Integer.parseInt(request.getParameter("bt" + i)))
						.setString("unit_name", request.getParameter("unit_name" + i))
						.setInteger("p_id", Integer.parseInt(request.getParameter("p_id")))
						.setInteger("id", Integer.parseInt(request.getParameter("c_malaria_id" + i)))
						.setString("modified_by", username).setTimestamp("modified_date", new Date());

				msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			}

			// Logic for INSERT

			int diffrence = newcount - count;
			if (diffrence != 0) {
				Tb_Med_Ch_Malaria e = new Tb_Med_Ch_Malaria();
				for (int j = count + 1; j <= newcount; j++) {
					Session session2 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx2 = session2.beginTransaction();
					String unit_name = request.getParameter("unit_name" + j);
					int p_id = Integer.parseInt(request.getParameter("p_id"));
					int bt = Integer.parseInt(request.getParameter("bt" + j));
					int mt = Integer.parseInt(request.getParameter("mt" + j));
					int fl = Integer.parseInt(request.getParameter("fl" + j));
					int fi = Integer.parseInt(request.getParameter("fi" + j));

					e.setP_id(p_id);
					e.setFi(fi);
					e.setFl(fl);
					e.setMt(mt);
					e.setBt(bt);
					e.setUnit_name(unit_name);

					e.setCreated_by(username);
					e.setCreated_date(new Date());
					session2.save(e);
					tx2.commit();
					session2.close();
					msg = "Data Updated Successfully.";
				}
			}

			// END LOGIC OF INSERT
			tx3.commit();

		} catch (Exception e) {
			tx3.rollback();
		} finally {
			session3.close();
		}

		return msg;
	}

	@RequestMapping(value = "admin/updateSTD", method = RequestMethod.POST)
	@ResponseBody
	public String updateSTD(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String msg = "";
		int newcount = Integer.parseInt(request.getParameter("std_count").toString());
		int count = Integer.parseInt(request.getParameter("stdoldcount").toString());
		Session session3 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session3.beginTransaction();
		try {

			String hql2 = "update Tb_Med_Pr_Preventive_Details set modified_by=:modified_by ,modified_date=:modified_date ,std_number_officers=:std_number_officers, std_number_jcos=:std_number_jcos,std_number_ors=:std_number_ors,std_rate_officers=:std_rate_officers,std_rate_jcos=:std_rate_jcos, std_rate_ors=:std_rate_ors  where  id=:id";
			Query query2 = session3.createQuery(hql2)
					.setInteger("std_number_officers", Integer.parseInt(request.getParameter("std_number_officers")))
					.setInteger("std_number_jcos", Integer.parseInt(request.getParameter("std_number_jcos")))
					.setInteger("std_number_ors", Integer.parseInt(request.getParameter("std_number_ors")))
					.setInteger("std_rate_officers", Integer.parseInt(request.getParameter("std_rate_officers")))
					.setInteger("std_rate_ors", Integer.parseInt(request.getParameter("std_rate_ors")))
					.setInteger("id", Integer.parseInt(request.getParameter("p_id")))
					.setInteger("std_rate_jcos", Integer.parseInt(request.getParameter("std_rate_jcos")))
					.setString("modified_by", username).setTimestamp("modified_date", new Date());

			msg = query2.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			for (int i = 1; i <= count; i++) {
				String hql = "update Tb_Med_Ch_STD set modified_by=:modified_by ,modified_date=:modified_date , unit_name=:unit_name, fl=:fl ,fi=:fi,p_id=:p_id  where  id=:id";
				Query query = session3.createQuery(hql)
						.setInteger("fi", Integer.parseInt(request.getParameter("s_fi" + i)))
						.setInteger("fl", Integer.parseInt(request.getParameter("s_fl" + i)))
						.setString("unit_name", request.getParameter("unit_name" + i))
						.setInteger("p_id", Integer.parseInt(request.getParameter("p_id")))
						.setInteger("id", Integer.parseInt(request.getParameter("c_std_id" + i)))
						.setString("modified_by", username).setTimestamp("modified_date", new Date());

				msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			}

			// Logic for INSERT

			int diffrence = newcount - count;
			if (diffrence != 0) {
				Tb_Med_Ch_STD e = new Tb_Med_Ch_STD();

				for (int j = count + 1; j <= newcount; j++) {
					Session session2 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx2 = session2.beginTransaction();
					String unit_name = request.getParameter("unit_name" + j);
					int p_id = Integer.parseInt(request.getParameter("p_id"));

					int fl = Integer.parseInt(request.getParameter("s_fl" + j));
					int fi = Integer.parseInt(request.getParameter("s_fi" + j));

					e.setP_id(p_id);
					e.setFi(fi);
					e.setFl(fl);
					e.setUnit_name(unit_name);
					e.setCreated_by(username);
					e.setCreated_date(new Date());
					session2.save(e);
					tx2.commit();
					session2.close();
					msg = "Data Updated Successfully.";
				}
			}

			// END LOGIC OF INSERT
			tx3.commit();

		} catch (Exception e) {
			tx3.rollback();
		} finally {
			session3.close();
		}

		return msg;
	}

	@RequestMapping(value = "admin/updateviral_hepatitis", method = RequestMethod.POST)
	@ResponseBody
	public String updateviral_hepatitis(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String msg = "";
		int newcount = Integer.parseInt(request.getParameter("viral_hepatitis_count").toString());
		int count = Integer.parseInt(request.getParameter("viral_hepatitis_oldcount").toString());
		Session session3 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session3.beginTransaction();

		try {

			String hql2 = "update Tb_Med_Pr_Preventive_Details set modified_by=:modified_by ,modified_date=:modified_date ,viral_hepatitis_number_officers=:viral_hepatitis_number_officers, viral_hepatitis_number_jcos=:viral_hepatitis_number_jcos,viral_hepatitis_number_ors=:viral_hepatitis_number_ors,viral_hepatitis_rate_officers=:viral_hepatitis_rate_officers,viral_hepatitis_rate_jcos=:viral_hepatitis_rate_jcos, viral_hepatitis_rate_ors=:viral_hepatitis_rate_ors  where  id=:id";
			Query query2 = session3.createQuery(hql2)
					.setInteger("viral_hepatitis_number_officers",
							Integer.parseInt(request.getParameter("viral_hepatitis_number_officers")))
					.setInteger("viral_hepatitis_number_jcos",
							Integer.parseInt(request.getParameter("viral_hepatitis_number_jcos")))
					.setInteger("viral_hepatitis_number_ors",
							Integer.parseInt(request.getParameter("viral_hepatitis_number_ors")))
					.setInteger("viral_hepatitis_rate_officers",
							Integer.parseInt(request.getParameter("viral_hepatitis_rate_officers")))
					.setInteger("viral_hepatitis_rate_ors",
							Integer.parseInt(request.getParameter("viral_hepatitis_rate_ors")))
					.setInteger("id", Integer.parseInt(request.getParameter("p_id")))
					.setInteger("viral_hepatitis_rate_jcos",
							Integer.parseInt(request.getParameter("viral_hepatitis_rate_jcos")))
					.setString("modified_by", username).setTimestamp("modified_date", new Date());

			msg = query2.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";

			for (int i = 1; i <= count; i++) {
				String hql = "update Tb_Med_Ch_Viral_Hepatitis set modified_by=:modified_by ,modified_date=:modified_date , unit_name=:unit_name, fl=:fl ,fi=:fi,p_id=:p_id  where  id=:id";
				Query query = session3.createQuery(hql)
						.setInteger("fi", Integer.parseInt(request.getParameter("h_fi" + i)))
						.setInteger("fl", Integer.parseInt(request.getParameter("h_fl" + i)))
						.setString("unit_name", request.getParameter("unit_name" + i))
						.setInteger("p_id", Integer.parseInt(request.getParameter("p_id")))
						.setInteger("id", Integer.parseInt(request.getParameter("c_viral_hepatitis_id" + i)))
						.setString("modified_by", username).setTimestamp("modified_date", new Date());

				msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			}

			// Logic for INSERT
			int diffrence = newcount - count;
			if (diffrence != 0) {
				Tb_Med_Ch_Viral_Hepatitis e = new Tb_Med_Ch_Viral_Hepatitis();
				for (int j = count + 1; j <= newcount; j++) {
					Session session2 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx2 = session2.beginTransaction();
					String unit_name = request.getParameter("unit_name" + j);
					int p_id = Integer.parseInt(request.getParameter("p_id"));

					int fl = Integer.parseInt(request.getParameter("h_fl" + j));
					int fi = Integer.parseInt(request.getParameter("h_fi" + j));

					e.setP_id(p_id);
					e.setFi(fi);
					e.setFl(fl);

					e.setUnit_name(unit_name);

					e.setCreated_by(username);
					e.setCreated_date(new Date());
					session2.save(e);
					tx2.commit();
					session2.close();
					msg = "Data Updated Successfully.";
				}
			}

			// END LOGIC OF INSERT
			tx3.commit();

		} catch (Exception e) {
			tx3.rollback();
		} finally {
			session3.close();
		}

		return msg;
	}

	@RequestMapping(value = "admin/updateinjuries_nea", method = RequestMethod.POST)
	@ResponseBody
	public String updateinjuries_nea(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String msg = "";
		int newcount = Integer.parseInt(request.getParameter("injuries_nea_count").toString());
		int count = Integer.parseInt(request.getParameter("injuries_nea_oldcount").toString());
		Session session3 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session3.beginTransaction();

		try {

			String hql2 = "update Tb_Med_Pr_Preventive_Details set modified_by=:modified_by ,modified_date=:modified_date ,injuries_nea_number_officers=:injuries_nea_number_officers, injuries_nea_number_jcos=:injuries_nea_number_jcos,injuries_nea_number_ors=:injuries_nea_number_ors,injuries_nea_rate_officers=:injuries_nea_rate_officers,injuries_nea_rate_jcos=:injuries_nea_rate_jcos, injuries_nea_rate_ors=:injuries_nea_rate_ors  where  id=:id";
			Query query2 = session3.createQuery(hql2)
					.setInteger("injuries_nea_number_officers",
							Integer.parseInt(request.getParameter("injuries_nea_number_officers")))
					.setInteger("injuries_nea_number_jcos",
							Integer.parseInt(request.getParameter("injuries_nea_number_jcos")))
					.setInteger("injuries_nea_number_ors",
							Integer.parseInt(request.getParameter("injuries_nea_number_ors")))
					.setInteger("injuries_nea_rate_officers",
							Integer.parseInt(request.getParameter("injuries_nea_rate_officers")))
					.setInteger("injuries_nea_rate_ors",
							Integer.parseInt(request.getParameter("injuries_nea_rate_ors")))
					.setInteger("id", Integer.parseInt(request.getParameter("p_id")))
					.setInteger("injuries_nea_rate_jcos",
							Integer.parseInt(request.getParameter("injuries_nea_rate_jcos")))
					.setString("modified_by", username).setTimestamp("modified_date", new Date());

			msg = query2.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";

			for (int i = 1; i <= count; i++) {
				String hql = "update Tb_Med_Ch_injuries_nea set modified_by=:modified_by ,modified_date=:modified_date , unit_name=:unit_name,diagnosis=:diagnosis, mode_of_injuries=:mode_of_injuries,p_id=:p_id  where  id=:id";
				Query query = session3.createQuery(hql)
						.setInteger("mode_of_injuries", Integer.parseInt(request.getParameter("mode_of_injury" + i)))
						.setString("unit_name", request.getParameter("unit_name" + i))
						.setString("diagnosis", request.getParameter("diagnosis" + i))
						.setInteger("p_id", Integer.parseInt(request.getParameter("p_id")))
						.setInteger("id", Integer.parseInt(request.getParameter("c_nea_id" + i)))
						.setString("modified_by", username).setTimestamp("modified_date", new Date());

				msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			}

			// Logic for INSERT

			int diffrence = newcount - count;
			if (diffrence != 0) {
				Tb_Med_Ch_injuries_nea e1 = new Tb_Med_Ch_injuries_nea();
				for (int j = count + 1; j <= newcount; j++) {
					Session session2 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx2 = session2.beginTransaction();
					String unit_name = request.getParameter("unit_name" + j);
					String diagnosis = request.getParameter("diagnosis" + j);
					int mode_of_injuries = Integer.parseInt(request.getParameter("mode_of_injury" + j));
					int p_id = Integer.parseInt(request.getParameter("p_id"));

					e1.setP_id(p_id);
					e1.setMode_of_injuries(mode_of_injuries);
					e1.setDiagnosis(diagnosis);
					e1.setUnit_name(unit_name);

					e1.setCreated_by(username);
					e1.setCreated_date(new Date());
					session2.save(e1);
					tx2.commit();
					session2.close();
					msg = "Data Updated Successfully.";
				}
			}
			tx3.commit();
		} catch (Exception e) {
			tx3.rollback();
		} finally {
			session3.close();
		}
		return msg;
	}

	@RequestMapping(value = "admin/updateh1n1", method = RequestMethod.POST)
	@ResponseBody
	public String updateh1n1(HttpSession session, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");

		String msg = "";
		int newcount = Integer.parseInt(request.getParameter("h1n1_count").toString());
		int count = Integer.parseInt(request.getParameter("h1n1_oldcount").toString());
		Session session3 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session3.beginTransaction();
		try {
			String hql2 = "update Tb_Med_Pr_Preventive_Details set modified_by=:modified_by ,modified_date=:modified_date ,h1n1_influenza_number_officers=:h1n1_influenza_number_officers, h1n1_influenza_number_jcos=:h1n1_influenza_number_jcos,h1n1_influenza_number_ors=:h1n1_influenza_number_ors,h1n1_influenza_rate_officers=:h1n1_influenza_rate_officers,h1n1_influenza_rate_jcos=:h1n1_influenza_rate_jcos, h1n1_influenza_rate_ors=:h1n1_influenza_rate_ors  where  id=:id";
			Query query2 = session3.createQuery(hql2)
					.setInteger("h1n1_influenza_number_officers",
							Integer.parseInt(request.getParameter("h1n1_influenza_number_officers")))
					.setInteger("h1n1_influenza_number_jcos",
							Integer.parseInt(request.getParameter("h1n1_influenza_number_jcos")))
					.setInteger("h1n1_influenza_number_ors",
							Integer.parseInt(request.getParameter("h1n1_influenza_number_ors")))
					.setInteger("h1n1_influenza_rate_officers",
							Integer.parseInt(request.getParameter("h1n1_influenza_rate_officers")))
					.setInteger("h1n1_influenza_rate_ors",
							Integer.parseInt(request.getParameter("h1n1_influenza_rate_ors")))
					.setInteger("id", Integer.parseInt(request.getParameter("p_id")))
					.setInteger("h1n1_influenza_rate_jcos",
							Integer.parseInt(request.getParameter("h1n1_influenza_rate_jcos")))
					.setString("modified_by", username).setTimestamp("modified_date", new Date());
			msg = query2.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";

			for (int i = 1; i <= count; i++) {
				String hql = "update Tb_Med_Ch_h1n1_influenza set modified_by=:modified_by ,modified_date=:modified_date , unit_name=:unit_name, no_of_cases=:no_of_cases,p_id=:p_id  where  id=:id";
				Query query = session3.createQuery(hql)
						.setInteger("no_of_cases", Integer.parseInt(request.getParameter("no_cases" + i)))
						.setString("unit_name", request.getParameter("unit_name" + i))
						.setInteger("p_id", Integer.parseInt(request.getParameter("p_id")))
						.setInteger("id", Integer.parseInt(request.getParameter("c_h1n1_id" + i)))
						.setString("modified_by", username).setTimestamp("modified_date", new Date());

				msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			}

			// Logic for INSERT

			int diffrence = newcount - count;
			if (diffrence != 0) {
				Tb_Med_Ch_h1n1_influenza e = new Tb_Med_Ch_h1n1_influenza();
				for (int j = count + 1; j <= newcount; j++) {
					Session session2 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx2 = session2.beginTransaction();
					String unit_name = request.getParameter("unit_name" + j);
					int p_id = Integer.parseInt(request.getParameter("p_id"));

					int no_of_cases = Integer.parseInt(request.getParameter("no_cases" + j));

					e.setP_id(p_id);
					e.setNo_of_cases(no_of_cases);
					e.setUnit_name(unit_name);
					e.setCreated_by(username);
					e.setCreated_date(new Date());
					session2.save(e);
					tx2.commit();
					session2.close();
					msg = "Data Updated Successfully.";
				}
			}
			// END LOGIC OF INSERT
			tx3.commit();
		} catch (Exception e) {
			tx3.rollback();
		} finally {
			session3.close();
		}
		return msg;
	}

	@RequestMapping(value = "admin/updatepulmonary_tuberculosis", method = RequestMethod.POST)
	@ResponseBody
	public String updatepulmonary_tuberculosis(HttpSession session, HttpServletRequest request) {
		String username = (String) session.getAttribute("username");
		String msg = "";
		int newcount = Integer.parseInt(request.getParameter("count").toString());
		int count = Integer.parseInt(request.getParameter("oldcount").toString());
		Session session3 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session3.beginTransaction();
		try {
			String hql2 = "update Tb_Med_Pr_Preventive_Details set modified_by=:modified_by ,modified_date=:modified_date ,pulmonary_tuberculosis_number_officers=:pulmonary_tuberculosis_number_officers, pulmonary_tuberculosis_number_jcos=:pulmonary_tuberculosis_number_jcos,pulmonary_tuberculosis_number_ors=:pulmonary_tuberculosis_number_ors,pulmonary_tuberculosis_rate_officer=:pulmonary_tuberculosis_rate_officers,pulmonary_tuberculosis_rate_jcos=:pulmonary_tuberculosis_rate_jcos, pulmonary_tuberculosis_rate_ors=:pulmonary_tuberculosis_rate_ors  where  id=:id";
			Query query2 = session3.createQuery(hql2)
					.setInteger("pulmonary_tuberculosis_number_officers",
							Integer.parseInt(request.getParameter("pulmonary_tuberculosis_number_officers")))
					.setInteger("pulmonary_tuberculosis_number_jcos",
							Integer.parseInt(request.getParameter("pulmonary_tuberculosis_number_jcos")))
					.setInteger("pulmonary_tuberculosis_number_ors",
							Integer.parseInt(request.getParameter("pulmonary_tuberculosis_number_ors")))
					.setInteger("pulmonary_tuberculosis_rate_officers",
							Integer.parseInt(request.getParameter("pulmonary_tuberculosis_rate_officer")))
					.setInteger("pulmonary_tuberculosis_rate_ors",
							Integer.parseInt(request.getParameter("pulmonary_tuberculosis_rate_ors")))
					.setInteger("id", Integer.parseInt(request.getParameter("p_id")))
					.setInteger("pulmonary_tuberculosis_rate_jcos",
							Integer.parseInt(request.getParameter("pulmonary_tuberculosis_rate_jcos")))
					.setString("modified_by", username).setTimestamp("modified_date", new Date());

			msg = query2.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";

			for (int i = 1; i <= count; i++) {
				String hql = "update Tb_Med_Ch_pulmonary_tuberculosis set modified_by=:modified_by ,modified_date=:modified_date , unit_name=:unit_name, category=:category,personnel_no=:personnel_no,personnel_name=:personnel_name,rank=:rank,p_id=:p_id  where  id=:id";
				Query query = session3.createQuery(hql).setString("personnel_no",
						request.getParameter("personnel_number1" + i) + request.getParameter("personnel_number2" + i)
								+ request.getParameter("personnel_number3" + i))
						.setString("personnel_name", request.getParameter("personnel_name" + i))
						.setString("unit_name", request.getParameter("unit_name" + i))
						.setString("category", request.getParameter("category" + i))
						.setInteger("rank", Integer.parseInt(request.getParameter("rank" + i)))
						.setInteger("p_id", Integer.parseInt(request.getParameter("p_id")))
						.setInteger("id", Integer.parseInt(request.getParameter("c_pul_tub_id" + i)))
						.setString("modified_by", username).setTimestamp("modified_date", new Date());

				msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			}

			// Logic for INSERT
			int diffrence = newcount - count;
			if (diffrence != 0) {
				Tb_Med_Ch_pulmonary_tuberculosis e = new Tb_Med_Ch_pulmonary_tuberculosis();
				for (int j = count + 1; j <= newcount; j++) {
					Session session2 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx2 = session2.beginTransaction();
					String unit_name = request.getParameter("unit_name" + j);
					String personnel_no = request.getParameter("personnel_number1" + j)
							+ request.getParameter("personnel_number2" + j)
							+ request.getParameter("personnel_number3" + j);
					String personnel_name = request.getParameter("personnel_name" + j);
					String category = request.getParameter("category" + j);
					int p_id = Integer.parseInt(request.getParameter("p_id"));

					int rank = Integer.parseInt(request.getParameter("rank" + j));

					e.setP_id(p_id);
					e.setPersonnel_name(personnel_name);
					e.setPersonnel_no(personnel_no);
					e.setCategory(category);
					e.setRank(rank);
					e.setUnit_name(unit_name);
					e.setCreated_by(username);
					e.setCreated_date(new Date());
					session2.save(e);
					tx2.commit();
					session2.close();
					msg = "Data Updated Successfully.";
				}
			}
			tx3.commit();
		} catch (Exception e) {
			tx3.rollback();
		} finally {
			session3.close();
		}
		return msg;
	}

	@RequestMapping(value = "admin/updatemilk_quality", method = RequestMethod.POST)
	@ResponseBody
	public String updatemilk_quality(HttpSession session, HttpServletRequest request) {
		String username = (String) session.getAttribute("username");
		String msg = "";
		int count = Integer.parseInt(request.getParameter("milk_oldcount").toString());
		int newcount = Integer.parseInt(request.getParameter("milk_count").toString());
		Session session3 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session3.beginTransaction();
		try {
			for (int i = 1; i <= count; i++) {
				String hql = "update Tb_Med_Ch_milk_quality set modified_by=:modified_by ,modified_date=:modified_date , sample_number=:sample_number, specific_gravity=:specific_gravity , fat_content=:fat_content,total_solids=:total_solids   where  id=:id";
				Query query = session3.createQuery(hql)
						.setInteger("specific_gravity", Integer.parseInt(request.getParameter("milk_spec_gravity" + i)))
						.setInteger("fat_content", Integer.parseInt(request.getParameter("milk_fat_con" + i)))
						.setInteger("total_solids", Integer.parseInt(request.getParameter("milk_tot_solid" + i)))
						.setString("sample_number", request.getParameter("milk_samplenum" + i))
						.setInteger("id", Integer.parseInt(request.getParameter("c_milk_id" + i)))
						.setString("modified_by", username).setTimestamp("modified_date", new Date());

				msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			}

			int diffrence = newcount - count;
			if (diffrence != 0) {
				Tb_Med_Ch_milk_quality e = new Tb_Med_Ch_milk_quality();
				for (int j = count + 1; j <= newcount; j++) {
					Session session2 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx2 = session2.beginTransaction();
					String sample_number = request.getParameter("milk_samplenum" + j);
					int p_id = Integer.parseInt(request.getParameter("p_id"));
					int specific_gravity = Integer.parseInt(request.getParameter("milk_spec_gravity" + j));
					int fat_content = Integer.parseInt(request.getParameter("milk_fat_con" + j));
					int total_solids = Integer.parseInt(request.getParameter("milk_tot_solid" + j));

					e.setP_id(p_id);
					e.setFat_content(fat_content);
					e.setSpecific_gravity(specific_gravity);
					e.setSample_number(sample_number);
					e.setTotal_solids(total_solids);
					e.setCreated_by(username);
					e.setCreated_date(new Date());
					session2.save(e);
					tx2.commit();
					session2.close();
					msg = "Data Updated Successfully.";
				}
			}
			tx3.commit();
		} catch (Exception e) {
			tx3.rollback();
		} finally {
			session3.close();
		}
		return msg;
	}

	@RequestMapping(value = "/get_Malaria_data", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> get_Malaria_data(int p_id) {
		List<ArrayList<String>> malariyadetails = fho_sho_preventivedao.getmalariyadeatails(p_id);
		return malariyadetails;
	}

	@RequestMapping(value = "/get_Std_data", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> get_Std_data(int p_id) {
		List<ArrayList<String>> stddata = fho_sho_preventivedao.getSTDdeatails(p_id);
		return stddata;
	}

	@RequestMapping(value = "/get_Viral_Hepatitis_data", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> get_Viral_Hepatitis_data(int p_id) {
		List<ArrayList<String>> vhdata = fho_sho_preventivedao.get_Viral_Hepatitis_deatails(p_id);
		return vhdata;
	}

	@RequestMapping(value = "/get_h1n1_data", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> get_h1n1_data(int p_id) {
		List<ArrayList<String>> h1n1 = fho_sho_preventivedao.get_h1n1_deatails(p_id);
		return h1n1;
	}

	@RequestMapping(value = "/get_injuries_nea_data", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> get_injuries_nea_data(int p_id) {

		List<ArrayList<String>> nea = fho_sho_preventivedao.get_injuries_nea_deatails(p_id);

		return nea;

	}

	@RequestMapping(value = "/get_pulmonary_tuberculosis_data", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> get_pulmonary_tuberculosis_data(int p_id) {

		List<ArrayList<String>> pultub = fho_sho_preventivedao.get_pulmonary_tuberculosis_deatails(p_id);

		return pultub;

	}

	@RequestMapping(value = "/get_milk_quality", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> get_milk_quality(int p_id) {

		List<ArrayList<String>> milkql = fho_sho_preventivedao.get_milkquality_deatails(p_id);

		return milkql;

	}

	@RequestMapping(value = "/get_healthedu", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> get_healthedu(int p_id) {

		List<ArrayList<String>> healthedu = fho_sho_preventivedao.gethealthedu_deatails(p_id);

		return healthedu;

	}

	@RequestMapping(value = "/get_workshopdetails", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> get_workshopdetails(int p_id) {

		List<ArrayList<String>> wrkdetails = fho_sho_preventivedao.getworkshop_deatails(p_id);

		return wrkdetails;

	}

	public List<T_Domain_Value> getPREVENTION_INJURIESList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select codevalue,label from T_Domain_Value where domainid=:domainid");
		q.setParameter("domainid", "PREVENTION_INJURIES");
		List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/updatehealthquality", method = RequestMethod.POST)
	@ResponseBody
	public String updatehealthquality(HttpSession session, HttpServletRequest request, MultipartHttpServletRequest mul)
			throws IOException {
		// @RequestParam(value = "doc", required = true) MultipartFile doc;
		String username = (String) session.getAttribute("username");
		String msg = "";
		int count = Integer.parseInt(request.getParameter("health_oldcount").toString());
		int newcount = Integer.parseInt(request.getParameter("health_count").toString());
		String extension = "";
		String fname = "";
		Session session3 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session3.beginTransaction();
		try {

			for (int i = 1; i <= count; i++) {

				MultipartFile file = mul.getFile("hea_img" + i);

				if (!file.getOriginalFilename().isEmpty()) {
					// try {
					byte[] bytes = file.getBytes();
					String mnhFilePath = session.getAttribute("mnhFilePath").toString() + "/Health_Education";
					File dir = new File(mnhFilePath);
					if (!dir.exists())
						dir.mkdirs();
					String filename = file.getOriginalFilename();

					int j = filename.lastIndexOf('.');
					if (j >= 0) {
						extension = filename.substring(j + 1);
					}
					java.util.Date date1 = new java.util.Date();
					fname = dir.getAbsolutePath() + File.separator
							+ (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".")
									.toString().replace(" ", "").toString().replace("-", "").toString()
							+ "MNH_Doc." + extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					// }
					// catch (Exception ex) {
					// }
				}

				if (!fname.equals("")) {
					String hql = "update Tb_Med_Ch_health_education set modified_by=:modified_by ,modified_date=:modified_date , topic=:topic,photographs=:photographs, unit_name=:unit_name ,"
							+ " personal_category=:personal_category,number_present=:number_present,remarks=:remarks   where  id=:id";
					Query query = session3.createQuery(hql).setString("topic", request.getParameter("health_topic" + i))
							.setString("unit_name", request.getParameter("health_unit_name" + i))
							.setString("personal_category", request.getParameter("h_personnel_category" + i))
							.setString("photographs", fname).setString("remarks", request.getParameter("h_remarks" + i))
							.setInteger("number_present",
									Integer.parseInt(request.getParameter("h_number_present" + i)))
							.setInteger("id", Integer.parseInt(request.getParameter("c_health_id" + i)))
							.setString("modified_by", username).setTimestamp("modified_date", new Date());

					msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
				} else {
					String hql = "update Tb_Med_Ch_health_education set modified_by=:modified_by ,modified_date=:modified_date , topic=:topic, unit_name=:unit_name ,"
							+ " personal_category=:personal_category,number_present=:number_present,remarks=:remarks   where  id=:id";
					Query query = session3.createQuery(hql).setString("topic", request.getParameter("health_topic" + i))
							.setString("unit_name", request.getParameter("health_unit_name" + i))
							.setString("personal_category", request.getParameter("h_personnel_category" + i))
							.setString("remarks", request.getParameter("h_remarks" + i))
							.setInteger("number_present",
									Integer.parseInt(request.getParameter("h_number_present" + i)))
							.setInteger("id", Integer.parseInt(request.getParameter("c_health_id" + i)))
							.setString("modified_by", username).setTimestamp("modified_date", new Date());

					msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
				}
				fname = "";
			}

			int diffrence = newcount - count;
			if (diffrence != 0) {
				Tb_Med_Ch_health_education e = new Tb_Med_Ch_health_education();
				for (int j = count + 1; j <= newcount; j++) {
					Session session2 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx2 = session2.beginTransaction();
					MultipartFile file = mul.getFile("hea_img" + j);
					if (!file.getOriginalFilename().isEmpty()) {
						byte[] bytes = file.getBytes();
						String mnhFilePath = session.getAttribute("mnhFilePath").toString() + "/Health_Education";
						File dir = new File(mnhFilePath);
						if (!dir.exists())
							dir.mkdirs();
						String filename = file.getOriginalFilename();

						int i = filename.lastIndexOf('.');
						if (i >= 0) {
							extension = filename.substring(i + 1);
						}
						java.util.Date date1 = new java.util.Date();
						fname = dir.getAbsolutePath() + File.separator
								+ (new Timestamp(date1.getTime())).toString().replace(":", "").toString()
										.replace(".", ".").toString().replace(" ", "").toString().replace("-", "")
										.toString()
								+ "MNH_Doc." + extension;
						File serverFile = new File(fname);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						e.setPhotographs(fname);
					}

					String topic = request.getParameter("health_topic" + j);
					String unit_name = request.getParameter("health_unit_name" + j);
					String personal_category = request.getParameter("h_personnel_category" + j);
					String remarks = request.getParameter("h_remarks" + j);
					int p_id = Integer.parseInt(request.getParameter("p_id"));
					int number_present = Integer.parseInt(request.getParameter("h_number_present" + j));

					e.setP_id(p_id);
					e.setTopic(topic);
					e.setUnit_name(unit_name);
					e.setPersonal_category(personal_category);
					e.setRemarks(remarks);
					e.setNumber_present(number_present);
					e.setCreated_by(username);
					e.setCreated_date(new Date());
					session2.save(e);
					tx2.commit();
					msg = "Data Updated Successfully.";
				}
			}
			tx3.commit();
		} catch (Exception e) {
			tx3.rollback();
		} finally {
			session3.close();
		}

		return msg;
	}

	// workshop

	@RequestMapping(value = "/updateworkshopdetails", method = RequestMethod.POST)
	@ResponseBody
	public String updateworkshopdetails(HttpSession session, HttpServletRequest request,
			MultipartHttpServletRequest mul) throws IOException {
		String username = (String) session.getAttribute("username");
		String msg = "";
		int count = Integer.parseInt(request.getParameter("workshop_oldcount").toString());
		int newcount = Integer.parseInt(request.getParameter("workshop_count").toString());
		String extension = "";
		String fname = "";
		Session session3 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session3.beginTransaction();

		try {

			for (int i = 1; i <= count; i++) {

				MultipartFile file = mul.getFile("work_img" + i);

				if (!file.getOriginalFilename().isEmpty()) {
					// try {
					byte[] bytes = file.getBytes();
					String mnhFilePath = session.getAttribute("mnhFilePath").toString() + "/workshop";
					File dir = new File(mnhFilePath);
					if (!dir.exists())
						dir.mkdirs();
					String filename = file.getOriginalFilename();

					int j = filename.lastIndexOf('.');
					if (j >= 0) {
						extension = filename.substring(j + 1);
					}
					java.util.Date date1 = new java.util.Date();
					fname = dir.getAbsolutePath() + File.separator
							+ (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".")
									.toString().replace(" ", "").toString().replace("-", "").toString()
							+ "MNH_Doc." + extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

				}

				if (!fname.equals("")) {
					String hql = "update Tb_Med_Ch_workshops set modified_by=:modified_by ,modified_date=:modified_date ,photographs=:photographs, topic=:topic, unit_name=:unit_name ,"
							+ " target_group=:target_group,number_present=:number_present,remarks=:remarks   where  id=:id";
					Query query = session3.createQuery(hql).setString("topic", request.getParameter("topic" + i))
							.setString("unit_name", request.getParameter("unit_name" + i))
							.setInteger("target_group", Integer.parseInt(request.getParameter("target_group" + i)))
							.setString("photographs", fname).setString("remarks", request.getParameter("remarks" + i))
							.setInteger("number_present", Integer.parseInt(request.getParameter("number_present" + i)))
							.setInteger("id", Integer.parseInt(request.getParameter("c_workshop_id" + i)))
							.setString("modified_by", username).setTimestamp("modified_date", new Date());

					msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
				} else {

					String hql = "update Tb_Med_Ch_workshops set modified_by=:modified_by ,modified_date=:modified_date , topic=:topic, unit_name=:unit_name ,"
							+ " target_group=:target_group,number_present=:number_present,remarks=:remarks   where  id=:id";
					Query query = session3.createQuery(hql).setString("topic", request.getParameter("topic" + i))
							.setString("unit_name", request.getParameter("unit_name" + i))
							.setInteger("target_group", Integer.parseInt(request.getParameter("target_group" + i)))
							// .setString("photographs", request.getParameter("photographs" + i))
							// photographs=:photographs,
							.setString("remarks", request.getParameter("remarks" + i))
							.setInteger("number_present", Integer.parseInt(request.getParameter("number_present" + i)))
							.setInteger("id", Integer.parseInt(request.getParameter("c_workshop_id" + i)))
							.setString("modified_by", username).setTimestamp("modified_date", new Date());

					msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
				}
				fname = "";
			}

			// Logic for INSERT

			int diffrence = newcount - count;
			if (diffrence != 0) {
				Tb_Med_Ch_workshops e = new Tb_Med_Ch_workshops();
				for (int j = count + 1; j <= newcount; j++) {
					Session session2 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx2 = session2.beginTransaction();
					MultipartFile file = mul.getFile("work_img" + j);

					if (!file.getOriginalFilename().isEmpty()) {
						// try {
						byte[] bytes = file.getBytes();
						String mnhFilePath = session.getAttribute("mnhFilePath").toString() + "/workshop";
						File dir = new File(mnhFilePath);
						if (!dir.exists())
							dir.mkdirs();
						String filename = file.getOriginalFilename();

						int i = filename.lastIndexOf('.');
						if (i >= 0) {
							extension = filename.substring(i + 1);
						}
						java.util.Date date1 = new java.util.Date();
						fname = dir.getAbsolutePath() + File.separator
								+ (new Timestamp(date1.getTime())).toString().replace(":", "").toString()
										.replace(".", ".").toString().replace(" ", "").toString().replace("-", "")
										.toString()
								+ "MNH_Doc." + extension;
						File serverFile = new File(fname);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						e.setPhotographs(fname);

						// catch (Exception ex) {

					}

					String topic = request.getParameter("topic" + j);
					String unit_name = request.getParameter("unit_name" + j);
					String target_group = request.getParameter("target_group" + j);
					String remarks = request.getParameter("remarks" + j);
					int p_id = Integer.parseInt(request.getParameter("p_id"));
					int number_present = Integer.parseInt(request.getParameter("number_present" + j));

					e.setP_id(p_id);
					e.setTopic(topic);
					e.setUnit_name(unit_name);
					e.setTarget_group(Integer.parseInt(target_group));
					e.setRemarks(remarks);
					e.setNumber_present(number_present);
					e.setCreated_by(username);
					e.setCreated_date(new Date());
					session2.save(e);
					tx2.commit();
					msg = "Data Updated Successfully.";
				}
			}
			tx3.commit();
		} catch (Exception e) {
			tx3.rollback();
		} finally {
			session3.close();
		}
		return msg;
	}

	@RequestMapping(value = "/search_preventiveurlAction", method = RequestMethod.POST)
	public ModelAndView search_daily_disease(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus1", required = false) String sus1,
			@RequestParam(value = "unit1", required = false) String unit1,
			@RequestParam(value = "month1", required = false) String month1,
			@RequestParam(value = "year1", required = false) String year1) {

		if (sus1 == null || sus1.equals("")) {
			Mmap.put("msg", "Please Enter the SUS No");
			return new ModelAndView("redirect:mnh_daily_disease_search");
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String f_sus_no = sus1;
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			f_sus_no = roleSusNo;

		}

		Mmap.put("mo_1", mcommon.getMedSystemCode("MONTH", "", session));
		Mmap.put("sus1", f_sus_no);
		Mmap.put("unit1", unit1);
		Mmap.put("month1", month1);
		Mmap.put("year1", year1);

		ArrayList<ArrayList<String>> list = fho_sho_preventivedao.search_preventive_deatails(f_sus_no, month1, year1);
		Mmap.put("list", list);
		Mmap.put("size", list.size());

		return new ModelAndView("mnh_search_preventiveTiles");

	}

	@RequestMapping(value = "/delete_preventive", method = RequestMethod.POST)
	public ModelAndView delete_preventive(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		List<String> liststr = new ArrayList<String>();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			String chH1N1hql = "delete from Tb_Med_Ch_h1n1_influenza where p_id=:id";
			int chH1N1app = sessionHQL.createQuery(chH1N1hql).setInteger("id", id).executeUpdate();
			String chInjhql = "delete from Tb_Med_Ch_injuries_nea where p_id=:id";
			int chInjapp = sessionHQL.createQuery(chInjhql).setInteger("id", id).executeUpdate();
			String chMalhql = "delete from Tb_Med_Ch_Malaria where p_id=:id";
			int chMalapp = sessionHQL.createQuery(chMalhql).setInteger("id", id).executeUpdate();
			String chPulhql = "delete from Tb_Med_Ch_pulmonary_tuberculosis where p_id=:id";
			int chPulapp = sessionHQL.createQuery(chPulhql).setInteger("id", id).executeUpdate();

			String chSTDhql = "delete from Tb_Med_Ch_STD where p_id=:id";
			int chSTDapp = sessionHQL.createQuery(chSTDhql).setInteger("id", id).executeUpdate();

			String chViralhql = "delete from Tb_Med_Ch_Viral_Hepatitis where p_id=:id";
			int chViralapp = sessionHQL.createQuery(chViralhql).setInteger("id", id).executeUpdate();

			String Prhql = "delete from Tb_Med_Pr_Preventive_Details where id=:id";
			int app = sessionHQL.createQuery(Prhql).setInteger("id", id).executeUpdate();

			tx.commit();
			sessionHQL.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			model.put("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:mnh_input_preventivedetails");
	}

	@RequestMapping(value = "/healthEduConvertpath")
	public void healthEduConvertpath(@ModelAttribute("healtheduid") String health_ch_id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		final int BUFFER_SIZE = 4096;
		String h_id = health_ch_id;
		String filePath = fho_sho_preventivedao.gethealthImagePath(h_id);
		model.put("filePath", filePath);
		ServletContext context = request.getSession().getServletContext();
		try {
			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
				String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_Image.jpg";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);

				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());

				OutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			} else {
				String fullPath = filePath;
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());

				OutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			}
		} catch (Exception ex) {
			String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_Image.jpg";
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());

			OutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		}
	}

	@RequestMapping(value = "/workshopConvertpath")
	public void workshopConvertpath(@ModelAttribute("workid") String work_ch_id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		final int BUFFER_SIZE = 4096;
		String w_id = work_ch_id;
		String filePath = fho_sho_preventivedao.getworkImagePath(w_id);
		model.put("filePath", filePath);
		ServletContext context = request.getSession().getServletContext();
		try {
			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
				String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_Image.jpg";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);

				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());

				OutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			} else {
				String fullPath = filePath;
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());

				OutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			}
		} catch (Exception ex) {
			String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_Image.jpg";
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());

			OutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		}
	}
}