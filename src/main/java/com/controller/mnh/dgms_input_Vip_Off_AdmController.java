package com.controller.mnh;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.SystemOutLogger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;

import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.Daily_Disease_serveillenceDaoImpl;
import com.dao.mnh.Daily_amc_adc_mns_ReportDAOImpl;
import com.dao.mnh.Vip_Off_AdmDAO;
import com.models.mnh.Tb_Med_Daily_Hosp_Adm_Off_Vip;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class dgms_input_Vip_Off_AdmController {

	@Autowired
	private Vip_Off_AdmDAO mnh3_Dao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	MNH_CommonController mcommon = new MNH_CommonController();

	MNH_ValidationController validation = new MNH_ValidationController();

	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();

	@Autowired
	private Daily_amc_adc_mns_ReportDAOImpl dg;

	@RequestMapping(value = "/admin/mnh_vip_officersDA", method = RequestMethod.GET)
	public ModelAndView mnh_vip_officersDA(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("mnh_vip_officersDA", roleid);
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

		Mmap.put("getMedSystemCode_PERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		Mmap.put("ml_5", mcommon.getMedrelationList("", session));

		Mmap.put("getMedSystemCode_PERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		Mmap.put("getMedSystemCode_SERVICE", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("getMedSystemCode_CATEGORY", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("getMedSystemCode_TYPE", mcommon.getMedSystemCode("TYPE", "", session));
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("vip_off_admTiles");
	}

	/////////////// VIP SAVE START////
	@RequestMapping(value = "/vip_off_admAction", method = RequestMethod.POST)
	public ModelAndView vip_off_admAction(@ModelAttribute("vip_off_admCmd") Tb_Med_Daily_Hosp_Adm_Off_Vip rs3,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("mnh_vip_officersDA", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String username = session.getAttribute("username").toString();

		Date date = new Date();
		Date date_time_admission_i = null;
		Date date_time_report_i = null;
		Date date_of_birth_i = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

		String unit_name1 = request.getParameter("unit_name1");
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String sus_no1 = request.getParameter("sus_no");
		if (roleAccess.equals("Unit")) {
			model.put("sus_no", roleSusNo);
			model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			sus_no1 = roleSusNo;

		}

		BigInteger contact_no = BigInteger.ZERO;
		if (!request.getParameter("contact_no1").equals("")) {
			contact_no = new BigInteger(request.getParameter("contact_no1"));
		}

		String service = request.getParameter("service");
		String type = request.getParameter("type");
		String dt_report = request.getParameter("dt_report1");
		String diagnosis_dtl[] = null;
		String persnl_no1 = request.getParameter("persnl_no1");
		String persnl_no2 = request.getParameter("persnl_no2");
		String persnl_no3 = request.getParameter("persnl_no3");
		String nillFlag = request.getParameter("nillFlag");

		String persnl_no = null;
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
		String sex = request.getParameter("sex");
		String relation = request.getParameter("relation");

		String persnl_name = request.getParameter("persnl_name");
		String date_of_birth = request.getParameter("date_of_birth1");
		String age_year1 = request.getParameter("age_year");
		String persnl_unit = request.getParameter("persnl_unit");

		String appointment = request.getParameter("appointment");
		String dt_admission = request.getParameter("dt_admission");

		String remarks = request.getParameter("remarks");

		if (!date_of_birth.equals("") && date_of_birth != null) {
			date_of_birth_i = formatter2.parse(date_of_birth);
		}
		if (!dt_admission.equals("")) {
			date_time_admission_i = formatter1.parse(dt_admission);
		}
		if (!dt_report.equals("")) {
			date_time_report_i = formatter2.parse(dt_report);
		}

		String adhar = request.getParameter("adhar_card_no");
		BigInteger adhar_card_no1 = BigInteger.ZERO;
		if (!adhar.equals("")) {
			adhar_card_no1 = new BigInteger(request.getParameter("adhar_card_no"));
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
		String btn_hid = request.getParameter("btn_hid");
		String btn_hid_exit = request.getParameter("btn_hid_exit");
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();

		try {

			if (btn_hid.equals("1") || btn_hid_exit.equals("1")) {

				if (sus_no1.equals("")) {
					model.put("msg", "Please Enter the SUS No");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if (service.equals("-1")) {
					model.put("msg", "Please Select the Service");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if (type.equals("-1")) {
					model.put("msg", "Please Select the Type");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if (dt_report.equals("")) {
					model.put("msg", "Please Select the Date of Report");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if (!dt_report.equals("")) {
					date_time_report_i = formatter2.parse(dt_report);
				}

				if ((service.equalsIgnoreCase("ARMY") && !category.equalsIgnoreCase("OR"))
						&& (persnl_no1.equalsIgnoreCase("-1") || persnl_no2 == null || persnl_no2.equals("")
								|| persnl_no3 == null || persnl_no3.equals("-1"))) {
					model.put("msg", "Please Select the Personnel No.");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}

				if (category.equals("-1") && service.equalsIgnoreCase("OTHERS")) {
					category = "";
				}
				if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
					model.put("msg", "Please Select the Category");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if (rs3.getV_rank().getId() == -1 && !service.equalsIgnoreCase("OTHERS")) {
					model.put("msg", "Please Select the Rank");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if (sex.equals("-1") || sex == "-1" || sex.equals("") || sex == null) {
					model.put("msg", "Please Enter the Gender");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if (relation.equals("-1") || relation == "-1" || relation.equals("") || relation == null) {
					model.put("msg", "Please Enter the Relation");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if ((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF")
						|| relation.equals("SISTEROF")) && !sex.equalsIgnoreCase("Female")) {
					model.put("msg", "Gender should be Female Here");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}

				if ((relation.equals("BROTHEROF") || relation.equals("HUSBANDOF") || relation.equals("FATHEROF")
						|| relation.equals("SONOF")) && !sex.equalsIgnoreCase("Male")) {
					model.put("msg", "Gender should be Male Here");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if (category.equals("MNS") && !sex.equalsIgnoreCase("Female")) {
					model.put("msg", "Gender should be Female Here");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}

				if (persnl_name.equals("") && !service.equalsIgnoreCase("OTHERS")) {
					model.put("msg", "Please Enter the Personnal Name");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}

				if (dt_admission.equals("")) {
					model.put("msg", "Please Select the Date of Admission");
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}

				if (validation.sus_noLength(sus_no1) == false) {
					model.put("msg", validation.sus_noMSG);
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}

				if (validation.adhar_noLength(adhar) == false) {
					model.put("msg", validation.adharnoMSG);
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if (validation.persnl_noLength(request.getParameter("persnl_no2")) == false) {
					model.put("msg", validation.persnl_no2MSG);
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if (validation.DiseasemmrLength(rs3.getPersnl_name()) == false) {
					model.put("msg", validation.persnl_nameMSG);
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if (validation.DiseaseFamilyLength(rs3.getAppointment()) == false) {
					model.put("msg", validation.appointmentMSG);
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if (validation.MessageLength(rs3.getPersnl_unit()) == false) {
					model.put("msg", validation.persl_unitMSG);
					return new ModelAndView("redirect:mnh_vip_officersDA");
				}
				if (validation.MessageLength(rs3.getRemarks()) == false) {
					model.put("msg", validation.remarkMSG);
					return new ModelAndView("redirect:mnh_vip_officersDA");
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
			// Long vipExist_cond = mnh3_Dao.checkExitstingvipExist(sus_no1, persnl_name,
			// dt_admission, "0");
			// if (vipExist_cond != (long) 0) {
			// model.put("msg", "Hospital already exists against Personnel Name & Time of
			// Admission");

			// }
			rs3.setCreated_by(username);
			rs3.setCreated_on(new Date());
			rs3.setPersnl_no(persnl_no);
			if (!diagnosis_code1d.equals("")) {
				rs3.setDiagnosis_code1d(d_code_all[0]);
			} else {
				rs3.setDiagnosis_code1d(diagnosis_code1d);
			}

			if (!icd_cause_code1d.equals("")) {
				rs3.setIcd_cause_code1d(icd_cause_all[0]);

			} else {

				rs3.setIcd_cause_code1d(icd_cause_code1d);
			}

			rs3.setCategory(category);
			rs3.setAppointment(appointment);
			rs3.setPersnl_name(persnl_name);
			rs3.setPersnl_unit(persnl_unit);
			rs3.setRemarks(remarks);
			rs3.setSus_no(sus_no1);
			rs3.setService(service);
			rs3.setType(type);
			rs3.setAge_year(age_year);
			rs3.setDate_time_admission(date_time_admission_i);
			rs3.setDt_report(date_time_report_i);
			rs3.setDate_of_birth(date_of_birth_i);
			rs3.setAdhar_card_no(adhar_card_no1);

			// rs3.setIcd_code(icd_code_id);

			rs3.setContact_no(contact_no);

//			Long bedE1 = dg.checkExitstingBasicDataForADM(sus_no1,dt_report, 0);
//			Long bedE2 = dg.checkExitstingNilDataForADM(sus_no1,"-1", dt_report, 0);
//			if (!persnl_no.equals("-1")) { 
//				if (bedE2 == 0) {
//					session1.save(rs3);
//					tx.commit();
//					model.put("msg", "Data Saved Successfully.");
//				} else if (bedE2 > 0) {
//					model.put("msg", "Data already Exist.");
//				}
//
//			}
//
//			else if (persnl_no.equals("-1")) {
//				if (bedE1 > 0) {
//					model.put("msg", "Data already Exist.");
//				}
//				if (bedE1 == 0) {
//					session1.save(rs3);
//					tx.commit();
//					model.put("msg", "Data Saved Successfully.");
//				}
//
//			}

			Long bedE1 = dg.checkExitstingBasicDataForADM(sus_no1, dt_report, 0);
			Long bedE2 = dg.checkExitstingNilDataForADM(sus_no1, "-2", dt_report, 0);

			if (persnl_no == null) {
				if (bedE2 == 0) {
					session1.save(rs3);
					tx.commit();
					model.put("msg", "Data Saved Successfully.");
				} else if (bedE2 > 0) {
					model.put("msg", "Data already Exist.");
				}
			} else if (!persnl_no.equals("-2")) {
				if (bedE2 == 0) {
					session1.save(rs3);
					tx.commit();
					model.put("msg", "Data Saved Successfully.");
				} else if (bedE2 > 0) {
					model.put("msg", "Data already Exist.");
				}

			}

			else if (persnl_no.equals("-2")) {
				if (bedE1 > 0) {
					model.put("msg", "Data already Exist.");
				}
				if (bedE1 == 0) {
					session1.save(rs3);
					tx.commit();
					model.put("msg", "Data Saved Successfully.");
				}

			}

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (session1 != null) {
				session1.close();
			}
		}

		if (btn_hid.equals("1")) {
			return new ModelAndView("redirect:mnh_vip_officersDA");

		} else if (btn_hid_exit.equals("1")) {
			return new ModelAndView("redirect:commonDashboard");
			// return new ModelAndView("commanDashboardTiles");
		} else {
			return new ModelAndView("redirect:mnh_vip_officersDA");
		}

	}

	////////////// VIP SAVE END///////////

	////////////// SEARCH VIP START //////////////
	@RequestMapping(value = "/admin/mnh_search_vip_officersDA", method = RequestMethod.GET)
	public ModelAndView mnh_search_vip_officersDA(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("mnh_search_vip_officersDA", roleid);
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
		Mmap.put("date", date1);
		Mmap.put("to_date", to_date);
		Mmap.put("msg", msg);
		return new ModelAndView("vip_off_adm_searchTiles");
	}

	@RequestMapping(value = "/vip_off_adm_searchAction", method = RequestMethod.POST)
	public ModelAndView vip_off_adm_searchAction(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam(value = "sus1", required = false) String sus1, String frm_dt1, String to_dt1, String unit1,
			String adhar1, String contact1) {

		Boolean val = roledao.ScreenRedirect("mnh_search_vip_officersDA", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		if (validation.DiseasemmrLength(unit1) == false) {
			Mmap.put("msg", validation.hospital_nameMSG);
			return new ModelAndView("redirect:mnh_search_vip_officersDA");
		}
		if (validation.sus_noLength(sus1) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:mnh_search_vip_officersDA");
		}
		if (validation.adhar_noLength(adhar1) == false) {
			Mmap.put("msg", validation.adharnoMSG);
			return new ModelAndView("redirect:mnh_search_vip_officersDA");
		}
		try {
			Date to_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(to_dt1);
			Date from_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(frm_dt1);
			if (to_date1.before(from_date1)) {
				Mmap.put("msg", "To date must be greater than from date");
				return new ModelAndView("redirect:mnh_search_vip_officersDA");
			}
		} catch (ParseException e) {
			// e.printStackTrace();
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String f_sus_no = sus1;
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

		List<Map<String, Object>> list = mnh3_Dao.search_vip_adm(f_sus_no, frm_dt1, to_dt1, adhar1, contact1);
		Mmap.put("list", list);
		Mmap.put("size", list.size());
		return new ModelAndView("vip_off_adm_searchTiles");

	}

	//////// SEARCH VIP END////////////

	///////////// DELETE VIP START///
	@RequestMapping(value = "/vip_off_adm_deleteAction", method = RequestMethod.POST)
	public ModelAndView vip_off_adm_deleteAction(@ModelAttribute("id2") int id, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session1,
			@RequestParam(value = "msg", required = false) String msg) {
		List<String> liststr = new ArrayList<String>();

		String roleid = session1.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("mnh_search_vip_officersDA", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			String hqlUpdate = "delete from Tb_Med_Daily_Hosp_Adm_Off_Vip where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
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
		return new ModelAndView("redirect:mnh_search_vip_officersDA");
	}
	////////// DELETE VIP END/////

	///////// EDIT VIP START///////
	@RequestMapping(value = "/vip_off_adm_editUrl", method = RequestMethod.POST)
	public ModelAndView vip_off_adm_editUrl(@ModelAttribute("id1") String updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_search_vip_officersDA", roleid);
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

		Tb_Med_Daily_Hosp_Adm_Off_Vip adm_Details = mnh3_Dao.getvipadm(Integer.parseInt(updateid));
		model.put("vip_off_adm_editCmd", adm_Details);

		// model.put("icd_code_id", adm_Details.getV_icd_code().getId());
		// .put("icd_code_desc", adm_Details.getV_icd_code().getIcd_code() + "-"+
		// adm_Details.getV_icd_code().getIcd_description());

		model.put("getMedSystemCode_PERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", sessionEdit));
		model.put("getMedSystemCode_PERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", sessionEdit));
		model.put("getMedSystemCode_SERVICE", mcommon.getMedSystemCode("SERVICE", "", sessionEdit));
		model.put("getMedSystemCode_CATEGORY", mcommon.getMedSystemCode("CATEGORY", "", sessionEdit));
		model.put("getMedSystemCode_TYPE", mcommon.getMedSystemCode("TYPE", "", sessionEdit));
		model.put("ml_5", mcommon.getMedrelationList("", sessionEdit));
		/*
		 * String icd_diagnosis =
		 * mcommon.getMNHDistinctICD_Diagnosis(adm_Details.getV_icd_code().getId());
		 * model.put("icd_diagnosis", icd_diagnosis);
		 */

		model.put("msg", msg);
		return new ModelAndView("vip_off_adm_editTiles");
	}

	@RequestMapping(value = "/vip_off_adm_editAction", method = RequestMethod.POST)
	public ModelAndView vip_off_adm_editAction(@ModelAttribute("vip_off_adm_editCmd") Tb_Med_Daily_Hosp_Adm_Off_Vip lm,
			HttpServletRequest request, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_search_vip_officersDA", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		int id = lm.getId() > 0 ? lm.getId() : 0;
		Mmap.put("id1", lm.getId());

		Date date = new Date();
		String username = session.getAttribute("username").toString();

		Date date_time_admission_i = null;
		Date date_time_report_i = null;
		Date date_of_birth_i = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

		BigInteger contact_no = BigInteger.ZERO;
		if (!request.getParameter("contact_no1").equals("")) {
			contact_no = new BigInteger(request.getParameter("contact_no1"));
		}

		String adhar = request.getParameter("adhar_card_no");
		BigInteger adhar_card_no1 = BigInteger.ZERO;
		if (!adhar.equals("")) {
			adhar_card_no1 = new BigInteger(request.getParameter("adhar_card_no"));
		}

		String persnl_unit = request.getParameter("persnl_unit");
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String sus_no1 = request.getParameter("sus_no");
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			sus_no1 = roleSusNo;

		}
		String service = request.getParameter("service");
		// String adhar_card_no1 = request.getParameter("adhar_card_no");
		String type = request.getParameter("type");
		String dt_report = request.getParameter("dt_report1");
		String persnl_no1 = request.getParameter("persnl_no1");
		String persnl_no2 = request.getParameter("persnl_no");
		String persnl_no3 = request.getParameter("persnl_no3");
		String dt_report1Original = request.getParameter("dt_report1Original");
		String persnl_no_Original = request.getParameter("persnl_no_Original");

		String persnl_no = null;

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
		// String rank = request.getParameter("rank");
		String persnl_name = request.getParameter("persnl_name");
		String date_of_birth = request.getParameter("date_of_birth1");
		String age_year1 = request.getParameter("age_year");
		String dt_admission = request.getParameter("date_time_admission1");
		String icd_code = request.getParameter("icd_code1");
		String appointment = request.getParameter("appointment");
		String remarks = request.getParameter("remarks");

		String sex = request.getParameter("sex");
		String relation = request.getParameter("relation");

		if (!date_of_birth.equals("")) {
			date_of_birth_i = formatter2.parse(date_of_birth);
		}
		if (!dt_admission.equals("")) {
			date_time_admission_i = formatter1.parse(dt_admission);
		}
		if (!dt_report.equals("")) {
			date_time_report_i = formatter2.parse(dt_report);
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

		String diagnosis_dtl[] = null;

		try {
			if (sus_no1 == null || sus_no1.equals("")) {
				Mmap.put("msg", "Please Enter the SUS No");
				return new ModelAndView("redirect:vip_off_adm_editUrl");
			}
			if (service == null || service.equals("")) {
				Mmap.put("msg", "Please Select the Service");
				return new ModelAndView("redirect:vip_off_adm_editUrl");
			}
			if (type == null || type.equals("")) {
				Mmap.put("msg", "Please Enter the Service Status");
				return new ModelAndView("redirect:vip_off_adm_editUrl");
			}
			if (dt_report == null || dt_report.equals("")) {
				Mmap.put("msg", "Please Select the Date of Report");
				return new ModelAndView("redirect:vip_off_adm_editUrl");
			}

			if ((service.equalsIgnoreCase("ARMY") && !category.equalsIgnoreCase("OR"))
					&& (persnl_no1.equalsIgnoreCase("-1") || persnl_no2 == null || persnl_no2.equals("")
							|| persnl_no3 == null || persnl_no3.equals("-1"))) {
				Mmap.put("msg", "Please Select the Personnel No.");
				return new ModelAndView("redirect:vip_off_adm_editUrl");
			}

			if (!category.equals("-1")) {

				if ((!service.equalsIgnoreCase("ARMY") && !service.equalsIgnoreCase("OTHERS")) && (persnl_no2 == null
						|| persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1"))) {

					Mmap.put("msg", "Please Select the Personnel No.");
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}

				if (validation.sus_noLength(sus_no1) == false) {
					Mmap.put("msg", validation.sus_noMSG);
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}

				if (validation.adhar_noLength(adhar) == false) {
					Mmap.put("msg", validation.adharnoMSG);
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if (category == null || category.equals("")) {
					Mmap.put("msg", "Please Select the category ");
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
					Mmap.put("msg", "Please Select the Category ");
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}

				if (lm.getV_rank().getId() == -1 && !service.equalsIgnoreCase("OTHERS")) {
					Mmap.put("msg", "Please Select the Rank");
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if (sex.equals("-1") || sex == "-1" || sex.equals("") || sex == null) {
					Mmap.put("msg", "Please Enter the Gender");
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if (relation.equals("-1") || relation == "-1" || relation.equals("") || relation == null) {
					Mmap.put("msg", "Please Enter the Relation");
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if ((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF")
						|| relation.equals("SISTEROF")) && !sex.equalsIgnoreCase("Female")) {
					Mmap.put("msg", "Gender should be Female Here");
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}

				if ((relation.equals("BROTHEROF") || relation.equals("HUSBANDOF") || relation.equals("FATHEROF")
						|| relation.equals("SONOF")) && !sex.equalsIgnoreCase("Male")) {
					Mmap.put("msg", "Gender should be Male Here");
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if (category.equals("MNS") && !sex.equalsIgnoreCase("Female")) {
					Mmap.put("msg", "Gender should be Female Here");
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if (persnl_name == null || persnl_name.equals("")) {
					Mmap.put("msg", "Please Select the  Personnel Name");
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if (persnl_name.equals("") && !service.equalsIgnoreCase("OTHERS")) {
					Mmap.put("msg", "Please Enter the Personnal Name");
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}

				if (dt_admission == null || dt_admission.equals("")) {
					Mmap.put("msg", "Please Select the Date of Admission");
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}

				////// length validation////////
				if (validation.sus_noLength(sus_no1) == false) {
					Mmap.put("msg", validation.sus_noMSG);
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if (!adhar_card_no1.equals("0") && !adhar_card_no1.equals("") && adhar_card_no1 != null
						&& validation.adhar_noLength(request.getParameter("adhar_card_no")) == false) {
					Mmap.put("msg", validation.adharnoMSG);
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if (validation.persnl_noLength(persnl_no2) == false) {
					Mmap.put("msg", validation.persnl_no2MSG);
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if (validation.DiseasemmrLength(persnl_name) == false) {
					Mmap.put("msg", validation.persnl_nameMSG);
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if (validation.DiseaseFamilyLength(appointment) == false) {
					Mmap.put("msg", validation.appointmentMSG);
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if (validation.MessageLength(persnl_unit) == false) {
					Mmap.put("msg", validation.persl_unitMSG);
					return new ModelAndView("redirect:vip_off_adm_editUrl");
				}
				if (validation.MessageLength(remarks) == false) {
					Mmap.put("msg", validation.remarkMSG);
					return new ModelAndView("redirect:vip_off_adm_editUrl");
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
			}
			Long d = mnh3_Dao.checkExitstingvipExist(sus_no1, persnl_no, dt_report, String.valueOf(lm.getId()));
			lm.setModified_by(username);
			lm.setModified_on(date);
			lm.setPersnl_no(persnl_no);
			lm.setDt_report(date_time_report_i);
			lm.setDate_of_birth(date_of_birth_i);
			lm.setDate_time_admission(date_time_admission_i);
			lm.setAge_year(age_year);
			lm.setContact_no(contact_no);

//			if (d == 0) {
//
//				
//				
//				
//				Long bedE2 = dg.checkExitstingNilDataForADM(sus_no1,"-1", dt_report, 0);
//				if(!persnl_no.equals("-1") )
//				{
//					if(bedE2 == 0)
//					{
//						Mmap.put("msg", mnh3_Dao.update_vip_adm(lm, username));
//						
//					}
//					else if(bedE2 > 0 )
//					{
//						Mmap.put("msg", "Data already Exist.");
//					}
//				
//				}
//				else if(persnl_no.equals("-1") ) {
//					Long bedE1 = dg.checkExitstingBasicDataForADM(sus_no1,dt_report, 0);
//					if(bedE1 == 0)
//					{
//						Mmap.put("msg", mnh3_Dao.update_vip_adm(lm, username));
//						
//					}
//					else if(bedE1 > 0)
//					{
//						Mmap.put("msg", "Data already Exist.");
//					}
//					
//				}
//				
//			}
//			if (d > 0)
//
//			{
//				Mmap.put("msg", "Data already Exist.");
//			}

			if (d == 0) {
				Long bedE2 = dg.checkExitstingNilDataForADM(sus_no1, "-2", dt_report, 0);
				if (persnl_no == null) {
					if (dt_report1Original.equals(dt_report)) {
						if (bedE2 == 0 || bedE2 == 1) {
							Mmap.put("msg", mnh3_Dao.update_vip_adm(lm, username));
						} else if (bedE2 > 1) {
							Mmap.put("msg", "Data already Exist.");
						}
					} else {
						if (bedE2 == 0) {
							Mmap.put("msg", mnh3_Dao.update_vip_adm(lm, username));
						} else if (bedE2 > 0) {
							Mmap.put("msg", "Data already Exist.");
						}
					}
				} else if (!persnl_no.equals("-2")) {
					if (dt_report1Original.equals(dt_report)) {
						if (bedE2 == 0 || bedE2 == 1) {
							Mmap.put("msg", mnh3_Dao.update_vip_adm(lm, username));
						} else if (bedE2 > 1) {
							Mmap.put("msg", "Data already Exist.");
						}
					} else {
						if (bedE2 == 0) {
							Mmap.put("msg", mnh3_Dao.update_vip_adm(lm, username));
						} else if (bedE2 > 0) {
							Mmap.put("msg", "Data already Exist.");
						}

					}

				} else if (persnl_no.equals("-2")) {
					Long bedE1 = dg.checkExitstingBasicDataForADM(sus_no1, dt_report, 0);
//					if (bedE1 == 0) {
//						Mmap.put("msg", dg.updatedailysdisease(lm, username));
//					} else if (bedE1 > 0) {
//						Mmap.put("msg", "Data already Exist.");
//					}

					if (dt_report1Original.equals(dt_report)) {
						if (bedE1 == 0 || bedE1 == 1) {
							Mmap.put("msg", mnh3_Dao.update_vip_adm(lm, username));
						} else if (bedE1 > 1) {
							Mmap.put("msg", "Data already Exist.");
						}
					} else {
						if (bedE1 == 0) {
							Mmap.put("msg", mnh3_Dao.update_vip_adm(lm, username));
						} else if (bedE1 > 0) {
							Mmap.put("msg", "Data already Exist.");
						}

					}

				}

			}
			if (d > 0) {
				Mmap.put("msg", "Data already Exist.");
			}

		} catch (RuntimeException e) {
			try {

				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {

		}
		return new ModelAndView("vip_off_adm_searchTiles");
	}

	/////////// EDIT VIP END////////////

	@RequestMapping(value = "/update_vip_h", method = RequestMethod.POST)
	public ModelAndView update_vip_h(@ModelAttribute("vip_h") String vip_h, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_search_vip_officersDA", roleid);

		
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
		model.put("unit_name", vip_h);
		model.put("getMedSystemCode_SERVICE", mcommon.getMedSystemCode("SERVICE", "", sessionEdit));
		model.put("ml_5", mcommon.getMedrelationList("", sessionEdit));
		model.put("getMedSystemCode_PERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", sessionEdit));
		model.put("getMedSystemCode_PERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", sessionEdit));
		model.put("getMedSystemCode_CATEGORY", mcommon.getMedSystemCode("CATEGORY", "", sessionEdit));
		model.put("getMedSystemCode_TYPE", mcommon.getMedSystemCode("TYPE", "", sessionEdit));
		model.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", sessionEdit));
		model.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", sessionEdit));
		model.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", sessionEdit));
		model.put("getMedDiseaseName", mcommon.getMedDiseaseName("", "", sessionEdit));

		return new ModelAndView("vip_off_admTiles");
	}

}
