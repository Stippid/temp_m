package com.controller.mnh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.persistance.util.HibernateUtil;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.Daily_amc_adc_mns_ReportDAOImpl;
import com.dao.mnh.Offic_Admi_AmcAdcMns_Dao;
import com.models.mnh.Tb_Med_Daily_Hosp_Adm_Amc;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class dgms_input_Offic_Admi_AmcAdcMnsController {

	MNH_CommonController mcommon = new MNH_CommonController();
	MNH_ValidationController validation = new MNH_ValidationController();
	@Autowired
	private Daily_amc_adc_mns_ReportDAOImpl dg;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private Offic_Admi_AmcAdcMns_Dao Oa;

	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();

	@RequestMapping(value = "/admin/mnh_adm_officersDA", method = RequestMethod.GET)
	public ModelAndView mnh_adm_officersDA(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mnh_adm_officersDA", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));

		}
		Mmap.put("ml_5", mcommon.getMedrelationList("", session));
		Mmap.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		Mmap.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		Mmap.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("service_type", mcommon.getMedSystemCodeQua("Service_Type", "", session));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_adm_officersDATiles");
	}

	/////////// ADM/AMC SAVE START///////////////////
	@SuppressWarnings("unused")
	@RequestMapping(value = "/Offic_Admi_AmcAdcMnsAction", method = RequestMethod.POST)
	public ModelAndView Offic_Admi_AmcAdcMnsAction(
			@ModelAttribute("Offic_Admi_AmcAdcMnsCMD") Tb_Med_Daily_Hosp_Adm_Amc rs4, BindingResult result,
			HttpServletRequest request, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		Boolean val = roledao.ScreenRedirect("mnh_adm_officersDA", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		int id = rs4.getId() > 0 ? rs4.getId() : 0;
		String username = session.getAttribute("username").toString();
		Date date = new Date();
		Date date_time_admission_i = null;
		Date date_time_report_i = null;
		Date date_of_birth_i = null;

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String sus_no1 = request.getParameter("sus_no");
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			sus_no1 = roleSusNo;

		}

		BigInteger contact_no = BigInteger.ZERO;
		if (!request.getParameter("contact_no1").equals("")) {
			contact_no = new BigInteger(request.getParameter("contact_no1"));
		}
		String service = request.getParameter("service");
		String dt_report = request.getParameter("dt_report");
		String persnl_no1 = request.getParameter("persnl_no1");
		String persnl_no2 = request.getParameter("persnl_no2");
		String persnl_no3 = request.getParameter("persnl_no3");
		String persnl_no = null;
		String nillFlag = request.getParameter("nillFlag");

		if (nillFlag.equalsIgnoreCase("-2")) {
			persnl_no = nillFlag;
		} else if (persnl_no1.equalsIgnoreCase("-1") && !service.equalsIgnoreCase("OTHERS")) {
			persnl_no = persnl_no2 + persnl_no3;
		} else if (!persnl_no1.equalsIgnoreCase("-1") && !persnl_no2.equalsIgnoreCase("")
				&& !persnl_no3.equalsIgnoreCase("-1") && service.equalsIgnoreCase("ARMY")) {
			persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
		} else if (service.equalsIgnoreCase("OTHERS")) {
			persnl_no = "-1";
		}
		String category = request.getParameter("category");
		String persnl_name = request.getParameter("persnl_name");
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		String date_of_birth = request.getParameter("date_of_birth");
		String dt_admission = request.getParameter("date_time_admission");
		String age_year1 = request.getParameter("age_year");
		String sex = request.getParameter("sex");
		String relation = request.getParameter("relation");
		if (!dt_admission.equals("")) {
			date_time_admission_i = formatter1.parse(dt_admission);
		}
		if (!dt_report.equals("")) {
			date_time_report_i = formatter2.parse(dt_report);
		}
		if (!date_of_birth.equals("")) {
			date_of_birth_i = formatter2.parse(date_of_birth);
		}

		Integer age_year = 0;
		if (!date_of_birth.equals("") && date_of_birth != null) {
			SimpleDateFormat YY = new SimpleDateFormat("yyyy");
			SimpleDateFormat MM = new SimpleDateFormat("MM");
			SimpleDateFormat dd = new SimpleDateFormat("dd");
			int year = Integer.parseInt(YY.format(date_of_birth_i));
			int MM1 = Integer.parseInt(MM.format(date_of_birth_i));
			int Dd = Integer.parseInt(dd.format(date_of_birth_i));
			LocalDate today = LocalDate.now(); // Today's date
			LocalDate birthday = LocalDate.of(year, MM1, Dd);// Birth date
			Period p = Period.between(birthday, today);

			if (age_year1 != "" && !age_year1.equals("")) {
				age_year = p.getYears();
			}
		}

		BigInteger adhar_card_no = BigInteger.ZERO;
		if (!request.getParameter("adhar_card_no").equals("")) {
			adhar_card_no = new BigInteger(request.getParameter("adhar_card_no"));
		}

		String diagnosis_dtl[] = null;

		String btn_hid = request.getParameter("btn_hid");
		String btn_hid_exit = request.getParameter("btn_hid_exit");
		if (btn_hid.equals("1") || btn_hid_exit.equals("1")) {
			if (request.getParameter("unit_name") == null || request.getParameter("unit_name").equals("")) {
				Mmap.put("msg", "Please Enter the Unit Name");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (request.getParameter("sus_no") == null || request.getParameter("sus_no").equals("")) {
				Mmap.put("msg", "Please Enter the SUS No");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (request.getParameter("service") == null || request.getParameter("service").equals("")
					|| request.getParameter("service").equals("-1") || request.getParameter("service") == "-1") {
				Mmap.put("msg", "Please Select the Service");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (request.getParameter("type").equals("-1") || request.getParameter("type") == "-1"
					|| request.getParameter("type").equals("") || request.getParameter("type") == null) {
				Mmap.put("msg", "Please Enter the Service Status");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (dt_report == null || dt_report.equals("")) {
				Mmap.put("msg", "Please Select the Date of Report");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if ((service.equalsIgnoreCase("ARMY") && !category.equalsIgnoreCase("OR"))
					&& (persnl_no1.equalsIgnoreCase("-1") || persnl_no2 == null || persnl_no2.equals("")
							|| persnl_no3 == null || persnl_no3.equals("-1"))) {
				Mmap.put("msg", "Please Select the Personnel No.");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if ((!service.equalsIgnoreCase("ARMY") && !service.equalsIgnoreCase("OTHERS"))
					&& (persnl_no2 == null || persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1"))) {

				Mmap.put("msg", "Please Select the Personnel No.");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}

			if (persnl_name.equals("") && !request.getParameter("service").equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Enter the Personnal Name");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}

			if (category.equals("-1") && service.equalsIgnoreCase("OTHERS")) {
				category = "";
			}
			if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Select the Category");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (rs4.getA_rank().getId() == -1 && !service.equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Select the Rank");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (request.getParameter("category").equals("-1")
					&& request.getParameter("service").equalsIgnoreCase("OTHERS")) {
				category = "";
			}

			if (sex.equals("-1") || sex == "-1" || sex.equals("") || sex == null) {
				Mmap.put("msg", "Please Enter the Gender");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (relation.equals("-1") || relation == "-1" || relation.equals("") || relation == null) {
				Mmap.put("msg", "Please Enter the Relation");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if ((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF")
					|| relation.equals("SISTEROF")) && !sex.equalsIgnoreCase("Female")) {
				Mmap.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}

			if ((relation.equals("BROTHEROF") || relation.equals("HUSBANDOF") || relation.equals("FATHEROF")
					|| relation.equals("SONOF")) && !sex.equalsIgnoreCase("Male")) {
				Mmap.put("msg", "Gender should be Male Here");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (category.equals("MNS") && !sex.equals("Female")) {
				Mmap.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (persnl_name == null || persnl_name.equals("")) {
				Mmap.put("msg", "Please Select the  Personnel Name");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}

			if (dt_admission == null || dt_admission.equals("")) {
				Mmap.put("msg", "Please Select the Date of Admission");
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}

			/////// length validation/////////
			if (validation.sus_noLength(sus_no1) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			String adhar_card_no1 = request.getParameter("adhar_card_no");
			if (validation.adhar_noLength(adhar_card_no1) == false) {
				Mmap.put("msg", validation.adharnoMSG);
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (validation.persnl_noLength(persnl_no2) == false) {
				Mmap.put("msg", validation.persnl_no2MSG);
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (validation.DiseasemmrLength(persnl_name) == false) {
				Mmap.put("msg", validation.persnl_nameMSG);
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (validation.DiseaseFamilyLength(request.getParameter("appointment")) == false) {
				Mmap.put("msg", validation.appointmentMSG);
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (validation.MessageLength(request.getParameter("persnl_unit")) == false) {
				Mmap.put("msg", validation.persl_unitMSG);
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
			if (validation.MessageLength(request.getParameter("remarks")) == false) {
				Mmap.put("msg", validation.remarkMSG);
				return new ModelAndView("redirect:mnh_adm_officersDA");
			}
		}
		String diagnosis_code1d = request.getParameter("diagnosis_code1d");
		String icd_cause_code1d = request.getParameter("icd_cause_code1d");
		String d_code_new = "";
		String d_code_p = "";
		String b_1[] = null;
		String b_2 = "";
		if (diagnosis_code1d != null && !diagnosis_code1d.equals("")) {
			d_code_new = diagnosis_code1d.substring(0, 1).toUpperCase();
			d_code_p = diagnosis_code1d.substring(0, 3).toUpperCase();

			b_1 = diagnosis_code1d.split("-");
			b_2 = b_1[0].substring(0, 1).toUpperCase();
		}

		String d_code_all[] = null;
		String icd_cause_all[] = null;
		String d_code_all1[] = null;
		String icd_cause_all1[] = null;
		if (diagnosis_code1d == null) {
			diagnosis_code1d = "";
		}

		if (icd_cause_code1d == null) {
			icd_cause_code1d = "";
		}

		if (!diagnosis_code1d.equals("")) {
			d_code_all = diagnosis_code1d.split("-");

		}

		if (!icd_cause_code1d.equals("")) {
			icd_cause_all = icd_cause_code1d.split("-");

		}
		/*
		 * Long admExist_cond=
		 * Oa.checkExitstingadmExistlNo(sus_no1,persnl_name,dt_admission,id);
		 * if(admExist_cond != (long)0) { Mmap.put("msg",
		 * "Hospital already exists against Personnel Name & Time of Admission"); return
		 * new ModelAndView("redirect:mnh_adm_officersDA"); }
		 */
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			if (!diagnosis_code1d.equals("")) {
				rs4.setDiagnosis_code1d(d_code_all[0]);
			} else {
				rs4.setDiagnosis_code1d(diagnosis_code1d);
			}

			if (!icd_cause_code1d.equals("")) {
				rs4.setIcd_cause_code1d(icd_cause_all[0]);

			} else {

				rs4.setIcd_cause_code1d(icd_cause_code1d);
			}
			rs4.setCreated_by(username);
			rs4.setCreated_on(new Date());
			rs4.setPersnl_no(persnl_no);
			rs4.setCategory(category);
			rs4.setDt_report(date_time_report_i);
			rs4.setDate_time_admission(date_time_admission_i);
			rs4.setDate_of_birth(date_of_birth_i);
			rs4.setAge_year(age_year);
			rs4.setAdhar_card_no(adhar_card_no);

			rs4.setContact_no(contact_no);

//			Long bedE1 = dg.checkExitstingBasicDataForAMCADCMNS(sus_no1, dt_report, 0);
//			Long bedE2 = dg.checkExitstingNilDataForAMCADCMNS(sus_no1, "-1", dt_report, 0);
//			if (!persnl_no.equals("-1")) {
//				if (bedE2 == 0) {
//					sessionHQL.save(rs4);
//					tx.commit();
//					Mmap.put("msg", "Data saved Successfully");
//				} else if (bedE2 > 0) {
//					Mmap.put("msg", "Data already Exist.");
//				}
//
//			}
//
//			else if (persnl_no.equals("-1")) {
//				if (bedE1 > 0) {
//					Mmap.put("msg", "Data already Exist.");
//				}
//				if (bedE1 == 0) {
//					sessionHQL.save(rs4);
//					tx.commit();
//					Mmap.put("msg", "Data saved Successfully");
//				}
//
//			}

			Long bedE1 = dg.checkExitstingBasicDataForAMCADCMNS(sus_no1, dt_report, id);
			Long bedE2 = dg.checkExitstingNilDataForAMCADCMNS(sus_no1, "-2", dt_report, id);

			if (persnl_no == null) {
				if (bedE2 == 0) {
					sessionHQL.save(rs4);
					tx.commit();
					Mmap.put("msg", "Data saved Successfully");
				} else if (bedE2 > 0) {
					Mmap.put("msg", "Data already Exist.");
				}
			} else if (!persnl_no.equals("-2")) {
				if (bedE2 == 0) {
					sessionHQL.save(rs4);
					tx.commit();
					Mmap.put("msg", "Data saved Successfully");
				} else if (bedE2 > 0) {
					Mmap.put("msg", "Data already Exist.");
				}

			}

			else if (persnl_no.equals("-2")) {
				if (bedE1 > 0) {
					Mmap.put("msg", "Data already Exist.");
				}
				if (bedE1 == 0) {
					sessionHQL.save(rs4);
					tx.commit();
					Mmap.put("msg", "Data saved Successfully");
				}

			}

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		if (btn_hid.equals("1")) {
			return new ModelAndView("redirect:mnh_adm_officersDA");

		} else if (btn_hid_exit.equals("1")) {
			return new ModelAndView("redirect:commonDashboard");
			// return new ModelAndView("commanDashboardTiles");
		} else {
			return new ModelAndView("redirect:mnh_adm_officersDA");
		}
	}
	/////////// ADM/AMC SAVE END///////////////////

	/////////// ADM/AMC SEARCH START///////////////////
	@RequestMapping(value = "/admin/mnh_search_adm_officersDA", method = RequestMethod.GET)
	public ModelAndView mnh_search_adm_officersDA(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		Boolean val = roledao.ScreenRedirect("mnh_search_adm_officersDA", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));

		}

		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String yyyy = new SimpleDateFormat("yyyy").format(date);
		String to_date = yyyy + "-01-01";
		List<Map<String, Object>> list = Oa.SearchOffi_Admi_AmcAdcMns("", "", "", "", "");
		Mmap.put("list", list);
		Mmap.put("size", list.size());
		Mmap.put("date", date1);
		Mmap.put("to_date", to_date);
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_adm_officer_searchsDA3Tiles");

	}

	@RequestMapping(value = "/SearchOffi_Admi_AmcAdcMns", method = RequestMethod.POST)
	public ModelAndView SearchOffi_Admi_AmcAdcMns(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus1", required = false) String sus_no,
			@RequestParam(value = "frm_dt1", required = false) String frm_dt1,
			@RequestParam(value = "to_dt1", required = false) String to_dt1,
			@RequestParam(value = "unit1", required = false) String unit1, String adhar1, String contact1) {
		Boolean val = roledao.ScreenRedirect("mnh_search_adm_officersDA", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		if (validation.DiseasemmrLength(unit1) == false) {
			Mmap.put("msg", validation.hospital_nameMSG);
			return new ModelAndView("redirect:mnh_search_adm_officersDA");
		}
		if (validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:mnh_search_adm_officersDA");
		}

		if (validation.adhar_noLength(adhar1) == false) {
			Mmap.put("msg", validation.adharnoMSG);
			return new ModelAndView("redirect:mnh_search_adm_officersDA");
		}
		try {
			Date to_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(to_dt1);
			Date from_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(frm_dt1);
			if (to_date1.before(from_date1)) {
				Mmap.put("msg", "To date must be greater than from date");
				return new ModelAndView("redirect:mnh_search_adm_officersDA");
			}
		} catch (ParseException e) {
			// e.printStackTrace();
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String f_sus_no = sus_no;
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			f_sus_no = roleSusNo;

		}

		Mmap.put("date", date1);
		Mmap.put("sus1", f_sus_no);
		Mmap.put("unit1", unit1);
		Mmap.put("frm_dt1", frm_dt1);
		Mmap.put("to_dt1", to_dt1);
		Mmap.put("adhar1", adhar1);
		Mmap.put("contact1", contact1);

		List<Map<String, Object>> list = Oa.SearchOffi_Admi_AmcAdcMns(f_sus_no, frm_dt1, to_dt1, adhar1, contact1);

		Mmap.put("list", list);

		return new ModelAndView("mnh_adm_officer_searchsDA3Tiles");

	}

	/////////// ADM/AMC SEARCH END///////////////////

	/////////// ADM/AMC DELETE START///////////////////
	@RequestMapping(value = "/deleteOffi_Admi_AmcAdcMns", method = RequestMethod.POST)
	public @ResponseBody ModelAndView deleteOffi_Admi_AmcAdcMns(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		Boolean val = roledao.ScreenRedirect("mnh_search_adm_officersDA", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String hqlUpdate = "delete from Tb_Med_Daily_Hosp_Adm_Amc where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			model.put("msg", liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:mnh_search_adm_officersDA");
	}
	/////////// ADM/AMC DELETE END///////////////////

	////////// ADM/AMC EDIT START///////////////////

	@RequestMapping(value = "/editOffi_Admi_AmcAdcMns", method = RequestMethod.POST)
	public ModelAndView editOffi_Admi_AmcAdcMns(@ModelAttribute("updateid") String updateid, ModelMap model,
			HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_search_adm_officersDA", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		model.put("date", date1);

		Tb_Med_Daily_Hosp_Adm_Amc Details = Oa.getOffi_Admi_AmcAdcMnsByid(Integer.parseInt(updateid));
		model.put("Edit_Offic_Admi_AmcAdcMnsCMD", Details);

		// model.put("icd_code_id", Details.getAdm_icd_code().getId());
		// model.put("icd_code_desc", Details.getAdm_icd_code().getIcd_code() + "-"+
		// Details.getAdm_icd_code().getIcd_description());

		model.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", sessionEdit));
		model.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", sessionEdit));
		model.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", sessionEdit));
		model.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", sessionEdit));
		model.put("service_type", mcommon.getMedSystemCodeQua("Service_Type", "", sessionEdit));
		model.put("ml_5", mcommon.getMedrelationList("", sessionEdit));

		model.put("msg", msg);
		return new ModelAndView("EditOffi_Admi_AmcAdcMnsTiles");
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/Edit_Offic_Admi_AmcAdcMnsAction", method = RequestMethod.POST)
	public ModelAndView Edit_Offic_Admi_AmcAdcMnsAction(
			@ModelAttribute("Edit_Offic_Admi_AmcAdcMnsCMD") Tb_Med_Daily_Hosp_Adm_Amc lm, HttpServletRequest request,
			ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg)
			throws ParseException {

		Boolean val = roledao.ScreenRedirect("mnh_search_adm_officersDA", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Mmap.put("updateid", lm.getId());
		Date date = new Date();
		String username = session.getAttribute("username").toString();

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String sus_no1 = request.getParameter("sus_no");
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			sus_no1 = roleSusNo;

		}
		BigInteger contact_no = BigInteger.ZERO;
		if (!request.getParameter("contact_no1").equals("")) {
			contact_no = new BigInteger(request.getParameter("contact_no1"));
		}

		String service = request.getParameter("service");
		String dt_report = request.getParameter("dt_report1");
		String persnl_no1 = request.getParameter("persnl_no1");
		String persnl_no2 = request.getParameter("persnl_no");
		String persnl_no3 = request.getParameter("persnl_no3");
		String persnl_no = null;
		String dt_report1Original = request.getParameter("dt_report1Original");
		String persnl_no_Original = request.getParameter("persnl_no_Original");

		if (persnl_no_Original.equalsIgnoreCase("-2") && service.equalsIgnoreCase("-1")) {
			persnl_no = "-2";
		} else if (persnl_no1.equalsIgnoreCase("-1") && !service.equalsIgnoreCase("OTHERS")) {
			persnl_no = persnl_no2 + persnl_no3;
		} else if (!persnl_no1.equalsIgnoreCase("-1") && !persnl_no2.equalsIgnoreCase("")
				&& !persnl_no3.equalsIgnoreCase("-1") && service.equalsIgnoreCase("ARMY")) {
			persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
		} else if (service.equalsIgnoreCase("OTHERS")) {
			persnl_no = null;
		}
		String category = request.getParameter("category");
		String persnl_name = request.getParameter("persnl_name");
		String date_of_birth = request.getParameter("date_of_birth1");
		String age_year1 = request.getParameter("age_year");
		String dt_admission = request.getParameter("date_time_admission1");
		String sex = request.getParameter("sex");
		String relation = request.getParameter("relation");
		Date date_time_admission_i = null;
		Date date_time_report_i = null;
		Date date_of_birth_i = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

		if (!dt_admission.equals("")) {
			date_time_admission_i = formatter1.parse(dt_admission);
		}
		if (!dt_report.equals("")) {
			date_time_report_i = formatter2.parse(dt_report);
		}
		if (!date_of_birth.equals("")) {
			date_of_birth_i = formatter2.parse(date_of_birth);
		}

		Integer age_year = 0;
		if (!date_of_birth.equals("") && date_of_birth != null) {
			SimpleDateFormat YY = new SimpleDateFormat("yyyy");
			SimpleDateFormat MM = new SimpleDateFormat("MM");
			SimpleDateFormat dd = new SimpleDateFormat("dd");
			int year = Integer.parseInt(YY.format(date_of_birth_i));
			int MM1 = Integer.parseInt(MM.format(date_of_birth_i));
			int Dd = Integer.parseInt(dd.format(date_of_birth_i));
			LocalDate today = LocalDate.now(); // Today's date
			LocalDate birthday = LocalDate.of(year, MM1, Dd);// Birth date
			Period p = Period.between(birthday, today);

			if (age_year1 != "" && !age_year1.equals("")) {
				age_year = p.getYears();
			}
		}

		BigInteger adhar_card_no = BigInteger.ZERO;
		if (!request.getParameter("adhar_card_no").equals("")) {
			adhar_card_no = new BigInteger(request.getParameter("adhar_card_no"));
		}

		if (request.getParameter("unit_name") == null || request.getParameter("unit_name").equals("")) {
			Mmap.put("msg", "Please Enter the Unit Name");
			return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
		}
		if (sus_no1 == null || sus_no1.equals("")) {
			Mmap.put("msg", "Please Enter the SUS No");
			return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
		}
		
		
		if (dt_report == null || dt_report.equals("")) {
			Mmap.put("msg", "Please Select the Date of Report");
			return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
		}
		if ((service.equalsIgnoreCase("ARMY") && !category.equalsIgnoreCase("OR")) && (persnl_no1.equalsIgnoreCase("-1")
				|| persnl_no2 == null || persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1"))) {
			Mmap.put("msg", "Please Select the Personnel No.");
			return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
		}
		if (!category.equals("-1")) {
			if (request.getParameter("type").equals("-1") || request.getParameter("type") == "-1"
					|| request.getParameter("type").equals("") || request.getParameter("type") == null) {
				Mmap.put("msg", "Please Enter the Service Status");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			if (request.getParameter("service") == null || request.getParameter("service").equals("")
					|| request.getParameter("service").equals("-1") || request.getParameter("service") == "-1") {
				Mmap.put("msg", "Please Select the Service");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			
			if ((!service.equalsIgnoreCase("ARMY") && !service.equalsIgnoreCase("OTHERS"))
					&& (persnl_no2 == null || persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1"))) {

				Mmap.put("msg", "Please Select the Personnel No.");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}

			if (category == null || category.equals("")) {
				Mmap.put("msg", "Please Select the category ");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Select the Category ");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			if (persnl_name.equals("") && !request.getParameter("service").equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Enter the Personnal Name");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			if (category.equals("-1") && !request.getParameter("service").equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Select the Category");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}

			if (lm.getA_rank().getId() == -1 && !request.getParameter("service").equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Select the Rank");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
//			if (request.getParameter("category").equals("-1") || request.getParameter("category") == "-1"
//					|| request.getParameter("category") == null || request.getParameter("category").equals("")) {
//				Mmap.put("msg", "Please Select the category");
//				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
//			}
			/*
			 * if (lm.getA_rank().getId()== -1) { Mmap.put("msg", "Please Select the rank");
			 * return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns"); }
			 */
			if (sex.equals("-1") || sex == "-1" || sex.equals("") || sex == null) {
				Mmap.put("msg", "Please Enter the Gender");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			if (relation.equals("-1") || relation == "-1" || relation.equals("") || relation == null) {
				Mmap.put("msg", "Please Enter the Relation");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			if ((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF")
					|| relation.equals("SISTEROF")) && !sex.equalsIgnoreCase("Female")) {
				Mmap.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}

			if ((relation.equals("BROTHEROF") || relation.equals("HUSBANDOF") || relation.equals("FATHEROF")
					|| relation.equals("SONOF")) && !sex.equalsIgnoreCase("Male")) {
				Mmap.put("msg", "Gender should be Male Here");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			if (category.equals("MNS") && !sex.equalsIgnoreCase("Female")) {
				Mmap.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			if (persnl_name == null || persnl_name.equals("")) {
				Mmap.put("msg", "Please Select the  Personnel Name");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}

			if (dt_admission == null || dt_admission.equals("")) {
				Mmap.put("msg", "Please Select the Date of Admission");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}

			/*
			 * if (lm.getAdm_icd_code().getId() == -1) { Mmap.put("msg",
			 * "Please Select the Diagnosis"); return new
			 * ModelAndView("redirect:editOffi_Admi_AmcAdcMns"); }
			 */
			/////////// length validation///////
			if (validation.sus_noLength(sus_no1) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			String adhar_card_no1 = request.getParameter("adhar_card_no");
			if (validation.adhar_noLength(adhar_card_no1) == false) {
				Mmap.put("msg", validation.adharnoMSG);
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}

			if (validation.persnl_noLength(persnl_no2) == false) {
				Mmap.put("msg", validation.persnl_no2MSG);
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			if (validation.DiseasemmrLength(persnl_name) == false) {
				Mmap.put("msg", validation.persnl_nameMSG);
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			if (validation.DiseaseFamilyLength(request.getParameter("appointment")) == false) {
				Mmap.put("msg", validation.appointmentMSG);
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			if (validation.MessageLength(request.getParameter("persnl_unit")) == false) {
				Mmap.put("msg", validation.persl_unitMSG);
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			if (validation.MessageLength(request.getParameter("remarks")) == false) {
				Mmap.put("msg", validation.remarkMSG);
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
		}

		String diagnosis_code1d = request.getParameter("diagnosis_code1d");
		String icd_cause_code1d = request.getParameter("icd_cause_code1d");
		String d_code_new = "";
		String d_code_p = "";
		String b_1[] = null;
		String b_2 = "";
		if (diagnosis_code1d != null && !diagnosis_code1d.equals("")) {
			d_code_new = diagnosis_code1d.substring(0, 1).toUpperCase();
			d_code_p = diagnosis_code1d.substring(0, 3).toUpperCase();

			b_1 = diagnosis_code1d.split("-");
			b_2 = b_1[0].substring(0, 1).toUpperCase();
		}

		String d_code_all[] = null;
		String icd_cause_all[] = null;
		String d_code_all1[] = null;
		String icd_cause_all1[] = null;
		if (diagnosis_code1d == null) {
			diagnosis_code1d = "";
		}

		if (icd_cause_code1d == null) {
			icd_cause_code1d = "";
		}

		if (!diagnosis_code1d.equals("")) {
			d_code_all = diagnosis_code1d.split("-");

		}

		if (!icd_cause_code1d.equals("")) {
			icd_cause_all = icd_cause_code1d.split("-");

		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Long admExist_cond = Oa.checkExitstingadmExistlNo(sus_no1, persnl_name, dt_report, lm.getId());
			if (admExist_cond != (long) 0) {
				Mmap.put("msg", "Hospital already exists against Personnel Name & Time of Admission");
				return new ModelAndView("redirect:editOffi_Admi_AmcAdcMns");
			}
			if (!diagnosis_code1d.equals("")) {
				lm.setDiagnosis_code1d(d_code_all[0]);
			} else {
				lm.setDiagnosis_code1d(diagnosis_code1d);
			}

			if (!icd_cause_code1d.equals("")) {
				lm.setIcd_cause_code1d(icd_cause_all[0]);

			} else {

				lm.setIcd_cause_code1d(icd_cause_code1d);
			}
			lm.setModified_by(username);
			lm.setModified_on(date);
			lm.setPersnl_no(persnl_no);
			lm.setDt_report(date_time_report_i);
			lm.setDate_of_birth(date_of_birth_i);
			lm.setDate_time_admission(date_time_admission_i);

			lm.setAge_year(age_year);
			lm.setContact_no(contact_no);

//			Long bedE2 = dg.checkExitstingNilDataForAMCADCMNS(sus_no1, "-1", dt_report, 0);
//			if (!persnl_no.equals("-1")) {
//				if (bedE2 == 0) {
//					Mmap.put("msg", Oa.update_Offic_Admi_AmcAdcMns(lm, username));
//				} else if (bedE2 > 0) {
//					Mmap.put("msg", "Data already Exist.");
//				}
//
//			} else if (persnl_no.equals("-1")) {
//				Long bedE1 = dg.checkExitstingBasicDataForAMCADCMNS(sus_no1, dt_report, 0);
//				if (bedE1 == 0) {
//					Mmap.put("msg", Oa.update_Offic_Admi_AmcAdcMns(lm, username));
//				} else if (bedE1 > 0) {
//					Mmap.put("msg", "Data already Exist.");
//				}
//
//			}
			// Long d = dg.checkExitstingSURExistlNo(sus_no1, persnl_no, dt_report,
			// lm.getId());

			// if (d == 0) {
			Long bedE2 = dg.checkExitstingNilDataForAMCADCMNS(sus_no1, "-2", dt_report, 0);
			if (persnl_no == null) {
				if (dt_report1Original.equals(dt_report)) {
					if (bedE2 == 0 || bedE2 == 1) {
						Mmap.put("msg", Oa.update_Offic_Admi_AmcAdcMns(lm, username));
					} else if (bedE2 > 1) {
						Mmap.put("msg", "Data already Exist.");
					}
				} else {
					if (bedE2 == 0) {
						Mmap.put("msg", Oa.update_Offic_Admi_AmcAdcMns(lm, username));
					} else if (bedE2 > 0) {
						Mmap.put("msg", "Data already Exist.");
					}
				}
			} else if (!persnl_no.equals("-2")) {
				if (dt_report1Original.equals(dt_report)) {
					if (bedE2 == 0 || bedE2 == 1) {
						Mmap.put("msg", Oa.update_Offic_Admi_AmcAdcMns(lm, username));
					} else if (bedE2 > 1) {
						Mmap.put("msg", "Data already Exist.");
					}
				} else {
					if (bedE2 == 0) {
						Mmap.put("msg", Oa.update_Offic_Admi_AmcAdcMns(lm, username));
					} else if (bedE2 > 0) {
						Mmap.put("msg", "Data already Exist.");
					}

				}

			} else if (persnl_no.equals("-2")) {
				Long bedE1 = dg.checkExitstingBasicDataForAMCADCMNS(sus_no1, dt_report, 0);
//					if (bedE1 == 0) {
//						Mmap.put("msg", Oa.update_Offic_Admi_AmcAdcMns(lm, username));
//					} else if (bedE1 > 0) {
//						Mmap.put("msg", "Data already Exist.");
//					}

				if (dt_report1Original.equals(dt_report)) {
					if (bedE1 == 0 || bedE1 == 1) {
						Mmap.put("msg", Oa.update_Offic_Admi_AmcAdcMns(lm, username));
					} else if (bedE1 > 1) {
						Mmap.put("msg", "Data already Exist.");
					}
				} else {
					if (bedE1 == 0) {
						Mmap.put("msg", Oa.update_Offic_Admi_AmcAdcMns(lm, username));
					} else if (bedE1 > 0) {
						Mmap.put("msg", "Data already Exist.");
					}

				}

			}

//			}
//			if (d > 0) {
//				Mmap.put("msg", "Data already Exist.");
//			}

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:mnh_search_adm_officersDA");
	}

	/////////// ADM/AMC EDIT END///////////////////

	@RequestMapping(value = "/update_adm_amc_h", method = RequestMethod.POST)
	public ModelAndView update_adm_amc_h(@ModelAttribute("adm_amc_h") String adm_amc_h, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_search_adm_officersDA", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		model.put("date", date1);

		/*
		 * Tb_Med_Daily_Bed_Occupancy dailyDetails = dg.update_dailydByid(unit_name_h);
		 * 
		 * model.put("edit_daily_disease_sureveCMD", dailyDetails);
		 */
		model.put("msg", msg);
		model.put("unit_name", adm_amc_h);

		model.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", sessionEdit));
		model.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", sessionEdit));
		model.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", sessionEdit));
		model.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", sessionEdit));
		model.put("getMedDiseaseName", mcommon.getMedDiseaseName("", "", sessionEdit));
		model.put("ml_5", mcommon.getMedrelationList("", sessionEdit));
		return new ModelAndView("mnh_adm_officersDATiles");
	}

}
