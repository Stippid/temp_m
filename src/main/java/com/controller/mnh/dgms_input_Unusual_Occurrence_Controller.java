package com.controller.mnh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
import java.util.Locale;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.persistance.util.HibernateUtil;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.Daily_Disease_serveillenceDaoImpl;
import com.dao.mnh.Daily_UnusuaLOccurrenceReportDAOImpl;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.Unusual_Occurrence_DAO;
import com.models.mnh.Tb_Med_Unusual_Occurrence;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class dgms_input_Unusual_Occurrence_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	@Autowired
	private Unusual_Occurrence_DAO UO_Dao;

	@Autowired
	private Daily_UnusuaLOccurrenceReportDAOImpl dg;

	MNH_CommonController mcommon = new MNH_CommonController();
	MNH_ValidationController validation = new MNH_ValidationController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	// *******************Note::Open Url Unusual
	// Occurence*****************************************//

	@RequestMapping(value = "/admin/mnh_unusual_occurrencesDA", method = RequestMethod.GET)
	public ModelAndView mnh_unusual_occurrencesDA(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_unusual_occurrencesDA", roleid);
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
		int userid = (Integer) session.getAttribute("userId");

		Mmap.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		Mmap.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		Mmap.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("msg", msg);
		Mmap.put("ml_5", mcommon.getMedrelationList("", session));
		return new ModelAndView("mnh_unusual_occDA4Tiles");
	}

	// *******************Note::Save Unusual
	// Occurence*****************************************//
	@RequestMapping(value = "/unusual_occurrenceAction", method = RequestMethod.POST)
	public ModelAndView unusual_occurrenceAction(@ModelAttribute("unusual_occurrenceCMD") Tb_Med_Unusual_Occurrence rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_unusual_occurrencesDA", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String username = session.getAttribute("username").toString();
		int id = rs.getId() > 0 ? rs.getId() : 0;

		Date date = new Date();
		Date date_time_incident_i = null;
		Date date_time_admission_i = null;
		Date date_time_report_i = null;
		Date date_of_birth_i = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

		String unit_name = request.getParameter("unit_name");
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		BigInteger contact_no = BigInteger.ZERO;
		if (!request.getParameter("contact_no1").equals("")) {
			contact_no = new BigInteger(request.getParameter("contact_no1"));
		}

		String sus_no = request.getParameter("sus_no");
		if (roleAccess.equals("Unit")) {
			model.put("sus_no", roleSusNo);
			model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			sus_no = roleSusNo;

		}
		String service = request.getParameter("service");
		String persnl_name = request.getParameter("persnl_name");
		String date_of_birth1 = request.getParameter("date_of_birth1");
		String age_year1 = request.getParameter("age_year");
		String dt_incident1 = request.getParameter("dt_incident1");
		String dt_admission1 = request.getParameter("dt_admission1");
		String dt_report1 = request.getParameter("dt_report1");
		String persnl_unit = request.getParameter("persnl_unit");
		String diagnosis = request.getParameter("diagnosis");
		String type = request.getParameter("type");
		String sex = request.getParameter("sex");
		String relation = request.getParameter("relation");

		if (!dt_incident1.equals("")) {
			date_time_incident_i = formatter1.parse(dt_incident1);
		}
		if (!dt_admission1.equals("")) {
			date_time_admission_i = formatter1.parse(dt_admission1);
		}
		if (!dt_report1.equals("")) {
			date_time_report_i = formatter2.parse(dt_report1);
		}
		if (!date_of_birth1.equals("")) {
			date_of_birth_i = formatter2.parse(date_of_birth1);
		}

		Integer age_year = 0;
		if (!date_of_birth1.equals("") && date_of_birth1 != null) {
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
		String appointment = request.getParameter("appointment");
		String remarks = request.getParameter("remarks");

		if (category.equals("-1") && service.equalsIgnoreCase("OTHERS")) {
			category = "";
		}

		String btn_hid = request.getParameter("btn_hid");
		String btn_hid_exit = request.getParameter("btn_hid_exit");
		if (btn_hid.equals("1") || btn_hid_exit.equals("1")) {

			if (unit_name == null || unit_name.equals("")) {
				model.put("msg", "Please Enter the Unit Name");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (sus_no == null || sus_no.equals("")) {
				model.put("msg", "Please Enter the SUS No");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (service == null || service.equals("-1")) {
				model.put("msg", "Please Select the Service");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (type == null || type.equals("-1")) {
				model.put("msg", "Please Enter the Service Status");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (dt_report1 == null || dt_report1.equals("")) {
				model.put("msg", "Please Select the Date of Report");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if ((service.equalsIgnoreCase("ARMY") && !category.equalsIgnoreCase("OR"))
					&& (persnl_no1.equalsIgnoreCase("-1") || persnl_no2 == null || persnl_no2.equals("")
							|| persnl_no3 == null || persnl_no3.equals("-1"))) {
				model.put("msg", "Please Select the Personnel No.");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if ((!service.equalsIgnoreCase("ARMY") && !service.equalsIgnoreCase("OTHERS"))
					&& (persnl_no2 == null || persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1"))) {

				model.put("msg", "Please Select the Personnel No.");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}

			if (persnl_name.equals("") && !service.equalsIgnoreCase("OTHERS")) {
				model.put("msg", "Please Enter the Personnal Name");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}

			if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
				model.put("msg", "Please Select the Category");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}

			if (rs.getUo_rank().getId() == -1 && !rs.getService().equalsIgnoreCase("OTHERS")) {
				model.put("msg", "Please Select the Rank");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (category == null || category.equals("-1")) {
				model.put("msg", "Please Select the category");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (sex.equals("-1") || sex == "-1" || sex.equals("") || sex == null) {
				model.put("msg", "Please Enter the Gender");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (relation.equals("-1") || relation == "-1" || relation.equals("") || relation == null) {
				model.put("msg", "Please Enter the Relation");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if ((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF")
					|| relation.equals("SISTEROF")) && !sex.equalsIgnoreCase("Female")) {
				model.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}

			if ((relation.equals("BROTHEROF") || relation.equals("HUSBANDOF") || relation.equals("FATHEROF")
					|| relation.equals("SONOF")) && !sex.equalsIgnoreCase("Male")) {
				model.put("msg", "Gender should be Male Here");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (category.equals("MNS") && !sex.equals("Female")) {
				model.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}

			/*
			 * if( rs.getUo_rank().getId() == -1){ model.put("msg",
			 * "Please Select the rank"); return new
			 * ModelAndView("redirect:mnh_unusual_occurrencesDA"); }
			 */
			if (persnl_name == null || persnl_name.equals("")) {
				model.put("msg", "Please Select the  Personnel Name");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}

			if (dt_admission1 == null || dt_admission1.equals("")) {
				model.put("msg", "Please Select the Date of Admission");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (diagnosis == null || diagnosis.equals("")) {
				model.put("msg", "Please Enter the Diagnosis");
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}

			/////// Length Validation/////////
			if (validation.sus_noLength(sus_no) == false) {
				model.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (validation.adhar_noLength(request.getParameter("adhar_card_no")) == false) {
				model.put("msg", validation.adharnoMSG);
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (validation.persnl_noLength(persnl_no2) == false) {
				model.put("msg", validation.persnl_no2MSG);
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (validation.DiseasemmrLength(persnl_name) == false) {
				model.put("msg", validation.persnl_nameMSG);
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (validation.DiseaseFamilyLength(appointment) == false) {
				model.put("msg", validation.appointmentMSG);
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (validation.MessageLength(persnl_unit) == false) {
				model.put("msg", validation.persl_unitMSG);
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
			if (validation.MessageLength(remarks) == false) {
				model.put("msg", validation.remarkMSG);
				return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
			}
		}

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		try {
			/* Long d= UO_Dao.checkExitstingunusualExistlNo(sus_no,persnl_name,id); */

			if (id == 0) {
				rs.setCreated_by(username);
				rs.setCreated_on(new Date());
				rs.setPersnl_no(persnl_no);

				rs.setCategory(category);
				rs.setAppointment(appointment);
				rs.setPersnl_name(persnl_name);
				rs.setPersnl_unit(persnl_unit);
				rs.setDiagnosis(diagnosis);
				rs.setRemarks(remarks);
				rs.setSus_no(sus_no);
				rs.setService(service);
				rs.setType(type);
				rs.setDt_report(date_time_report_i);
				rs.setDate_of_birth(date_of_birth_i);
				rs.setDate_time_admission(date_time_admission_i);
				rs.setDate_time_incident(date_time_incident_i);
				rs.setAge_year(Integer.parseInt(age_year1));
				rs.setAdhar_card_no(adhar_card_no);
				rs.setContact_no(contact_no);
				/* if (d == 0) { */
//					
//					Long bedE1 = dg.checkExitstingBasicData(sus_no,dt_report1, 0);
//					Long bedE2 = dg.checkExitstingNilData(sus_no,"-1", dt_report1, 0);
//					if (!persnl_no.equals("-1")) {
//						if (bedE2 == 0) {
//							session1.save(rs);
//							tx.commit();
//							model.put("msg", "Data Saved Successfully.");
//						} else if (bedE2 > 0) {
//							model.put("msg", "Data already Exist.");
//						}
//
//					}
//
//					else if (persnl_no.equals("-1")) {
//						if (bedE1 > 0) {
//							model.put("msg", "Data already Exist.");
//						}
//						if (bedE1 == 0) {
//							session1.save(rs);
//							tx.commit();
//							model.put("msg", "Data Saved Successfully.");
//						}
//
//					}

				Long bedE1 = dg.checkExitstingBasicData(sus_no, dt_report1, id);
				Long bedE2 = dg.checkExitstingNilData(sus_no, "-2", dt_report1, id);

				if (persnl_no == null) {
					if (bedE2 == 0) {
						session1.save(rs);
						tx.commit();
						model.put("msg", "Data Saved Successfully.");
					} else if (bedE2 > 0) {
						model.put("msg", "Data already Exist.");
					}
				} else if (!persnl_no.equals("-2")) {
					if (bedE2 == 0) {
						session1.save(rs);
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
						session1.save(rs);
						tx.commit();
						model.put("msg", "Data Saved Successfully.");
					}

				}

//				}
//				if (d > 0)
//
//				{
//					model.put("msg", "Data already Exist.");
//				}
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
			return new ModelAndView("redirect:mnh_unusual_occurrencesDA");

		} else if (btn_hid_exit.equals("1")) {
			return new ModelAndView("redirect:commonDashboard");
			// return new ModelAndView("commanDashboardTiles");
		} else {
			return new ModelAndView("redirect:mnh_unusual_occurrencesDA");
		}
	}

	// *******************Note::Search Unusual
	// Occurence*****************************************//

	@RequestMapping(value = "/admin/mnh_search_unusual_occurrencesDA", method = RequestMethod.GET)
	public ModelAndView mnh_search_unusual_occurrencesDA(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("mnh_search_unusual_occurrencesDA", roleid);
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
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_unusual_occ_searchDA4Tiles");
	}

	@RequestMapping(value = "/Search_Unusual_Occurrence", method = RequestMethod.POST)
	public ModelAndView Search_Unusual_Occurrence(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus1", required = false) String sus_no, HttpServletRequest request,
			@RequestParam(value = "frm_dt1", required = false) String frm_dt,
			@RequestParam(value = "to_dt1", required = false) String to_dt,
			@RequestParam(value = "unit1", required = false) String unit_name, String adhar1, String contact1) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("mnh_search_unusual_occurrencesDA", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String username = (String) session.getAttribute("username");

		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		if (validation.DiseasemmrLength(unit_name) == false) {
			Mmap.put("msg", validation.hospital_nameMSG);
			return new ModelAndView("redirect:mnh_search_unusual_occurrencesDA");

		}
		if (validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:mnh_search_unusual_occurrencesDA");

		}

		if (validation.adhar_noLength(adhar1) == false) {
			Mmap.put("msg", validation.adharnoMSG);
			return new ModelAndView("redirect:mnh_search_unusual_occurrencesDA");
		}
		try {
			Date to_dt1 = new SimpleDateFormat("dd-MM-yyyy").parse(to_dt);
			Date frm_dt1 = new SimpleDateFormat("dd-MM-yyyy").parse(frm_dt);
			if (to_dt1.before(frm_dt1)) {
				Mmap.put("msg", "To date must be greater than from date");
				return new ModelAndView("redirect:mnh_search_unusual_occurrencesDA");
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
		Mmap.put("unit1", unit_name);
		Mmap.put("frm_dt1", frm_dt);
		Mmap.put("to_dt1", to_dt);
		Mmap.put("adhar1", adhar1);
		Mmap.put("contact1", contact1);
		// List<Map<String, Object>> list = dg.search_daily_activity_data(f_sus_no,
		// frm_dt1, to_dt1,adhar1,contact1);

		List<Map<String, Object>> list = UO_Dao.Search_Unusual_Occurrence_Detail(f_sus_no, frm_dt, to_dt, adhar1,
				contact1);

		Mmap.put("list", list);

		return new ModelAndView("mnh_unusual_occ_searchDA4Tiles");

	}

	// ********************Note::Delete Unusual
	// Occurence*****************************************//

	@RequestMapping(value = "/deleteUnusual_Occurrence", method = RequestMethod.POST)
	public @ResponseBody ModelAndView deleteUnusual_Occurrence(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		String roleid = sessionA.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("mnh_search_unusual_occurrencesDA", roleid);
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

			String hqlUpdate = "delete from Tb_Med_Unusual_Occurrence where id=:id";
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
		return new ModelAndView("redirect:mnh_search_unusual_occurrencesDA");
	}

	// ********************Note::Update Unusual
	// Occurence*****************************************//

	@RequestMapping(value = "/updateUnusual_Occurrence", method = RequestMethod.POST)
	public ModelAndView updateUnusual_Occurrence(@ModelAttribute("updateid") String updateid, ModelMap model,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication, HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_unusual_occurrencesDA", roleid);
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

		Tb_Med_Unusual_Occurrence dailyDetails = UO_Dao.getUnusual_OccurrenceByid(Integer.parseInt(updateid));
		model.put("edit_unusual_occurrenceCMD", dailyDetails);
		model.put("msg", msg);

		model.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", sessionEdit));
		model.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", sessionEdit));
		model.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", sessionEdit));
		model.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", sessionEdit));
		model.put("ml_5", mcommon.getMedrelationList("", sessionEdit));
		return new ModelAndView("mnh_edit_unusual_occDA4Tiles");
	}

	// *******************Note::Edit Unusual
	// Occurence*****************************************//

	@RequestMapping(value = "/edit_unusual_occurrenceAction", method = RequestMethod.POST)
	public ModelAndView edit_unusual_occurrenceAction(
			@ModelAttribute("edit_unusual_occurrenceCMD") Tb_Med_Unusual_Occurrence lm, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, ModelMap Mmap, HttpSession session)
			throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_unusual_occurrencesDA", roleid);
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
		Date date_time_incident_i = null;
		Date date_time_admission_i = null;
		Date date_time_report_i = null;
		Date date_of_birth_i = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

		String unit_name = request.getParameter("unit_name");
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String sus_no = request.getParameter("sus_no");
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			sus_no = roleSusNo;

		}
		String service = request.getParameter("service");
		String persnl_name = request.getParameter("persnl_name");
		String date_of_birth = request.getParameter("date_of_birth1");
		String age_year1 = request.getParameter("age_year");
		String adhar_card_no1 = request.getParameter("adhar_card_no");
		String persnl_unit = request.getParameter("persnl_unit");
		String dt_incident = request.getParameter("dt_incident1");
		String dt_admission = request.getParameter("dt_admission1");
		String dt_report = request.getParameter("dt_report1");
		String diagnosis = request.getParameter("diagnosis");
		String sex = request.getParameter("sex");
		String relation = request.getParameter("relation");
		String type = request.getParameter("type");
		String persnl_no1 = request.getParameter("persnl_no1");
	
		String persnl_no2 = request.getParameter("persnl_no2");
	
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
		String appointment = request.getParameter("appointment");
		String remarks = request.getParameter("remarks");

		if (!date_of_birth.equals("")) {
			date_of_birth_i = formatter2.parse(date_of_birth);
		}

		BigInteger contact_no = BigInteger.ZERO;
		if (!request.getParameter("contact_no1").equals("")) {
			contact_no = new BigInteger(request.getParameter("contact_no1"));
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
		if (adhar_card_no1 != null && !request.getParameter("adhar_card_no").equals("")) {
			adhar_card_no = new BigInteger(adhar_card_no1);
		}

		if (!dt_incident.equals("")) {
			date_time_incident_i = formatter1.parse(dt_incident);
		}
		if (!dt_admission.equals("")) {
			date_time_admission_i = formatter1.parse(dt_admission);
		}
		if (!dt_report.equals("")) {
			date_time_report_i = formatter2.parse(dt_report);
		}
		if (!date_of_birth.equals("")) {
			date_of_birth_i = formatter2.parse(date_of_birth);
		}
		if (category.equals("-1") && service.equalsIgnoreCase("OTHERS")) {
			category = "";
		}

		if (unit_name == null || unit_name.equals("")) {
			Mmap.put("msg", "Please Enter the Unit Name");
			return new ModelAndView("redirect:updateUnusual_Occurrence");
		}
		if (sus_no == null || sus_no.equals("")) {
			Mmap.put("msg", "Please Enter the SUS No");
			return new ModelAndView("redirect:updateUnusual_Occurrence");
		}

		if (dt_report == null || dt_report.equals("")) {
			Mmap.put("msg", "Please Select the Date of Report");
			return new ModelAndView("redirect:updateUnusual_Occurrence");
		}
		/*
		 * if ((service.equalsIgnoreCase("ARMY") && !category.equalsIgnoreCase("OR")) &&
		 * (persnl_no1.equalsIgnoreCase("-1") || persnl_no2 == null ||
		 * persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1"))) {
		 * Mmap.put("msg", "Please Select the Personnel No."); return new
		 * ModelAndView("redirect:updateUnusual_Occurrence"); }
		 */
		/*
		 * if((!service.equalsIgnoreCase("ARMY") && !service.equalsIgnoreCase("OTHERS"))
		 * && (persnl_no2 == null || persnl_no2.equals("") || persnl_no3 == null ||
		 * persnl_no3.equals("-1"))) {
		 * 
		 * Mmap.put("msg", "Please Select the Personnel No."); return new
		 * ModelAndView("redirect:updateUnusual_Occurrence"); }
		 */

		if (!category.equals("-1")) {

			if (service == null || service.equals("-1")) {
				Mmap.put("msg", "Please Select the Service");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}
			if (type == null || type.equals("-1")) {
				Mmap.put("msg", "Please Enter the Service Status");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}

			if (persnl_name.equals("") && !service.equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Enter the Personnal Name");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}

			if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Select the Category");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}

			if (lm.getUo_rank().getId() == -1 && !service.equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Select the Rank");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}
			if (category == null || category.equals("-1")) {
				Mmap.put("msg", "Please Select the category");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}
			/*
			 * if( lm.getUo_rank().getId()==-1){ Mmap.put("msg", "Please Select the rank");
			 * return new ModelAndView("redirect:updateUnusual_Occurrence"); }
			 */
			if (sex.equals("-1") || sex == "-1" || sex.equals("") || sex == null) {
				Mmap.put("msg", "Please Enter the Gender");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}
			if (relation.equals("-1") || relation == "-1" || relation.equals("") || relation == null) {
				Mmap.put("msg", "Please Enter the Relation");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}
			if ((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF")
					|| relation.equals("SISTEROF")) && !sex.equalsIgnoreCase("Female")) {
				Mmap.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}

			if ((relation.equals("BROTHEROF") || relation.equals("HUSBANDOF") || relation.equals("FATHEROF")
					|| relation.equals("SONOF")) && !sex.equalsIgnoreCase("Male")) {
				Mmap.put("msg", "Gender should be Male Here");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}
			if (category.equals("MNS") && !sex.equals("Female")) {
				Mmap.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}
			if (persnl_name == null || persnl_name.equals("")) {
				Mmap.put("msg", "Please Select the  Personnel Name");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}

			if (dt_admission == null || dt_admission.equals("")) {
				Mmap.put("msg", "Please Select the Date of Admission");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}
			if (diagnosis == null || diagnosis.equals("")) {
				Mmap.put("msg", "Please Enter the Diagnosis");
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}

			///////// length validation///////
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}

			if (validation.adhar_noLength(adhar_card_no1) == false) {
				Mmap.put("msg", validation.adharnoMSG);
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}
			/*
			 * if(validation.persnl_noLength(persnl_no2) == false){
			 * Mmap.put("msg",validation.persnl_no2MSG); return new
			 * ModelAndView("redirect:updateUnusual_Occurrence"); }
			 */
			if (validation.DiseasemmrLength(persnl_name) == false) {
				Mmap.put("msg", validation.persnl_nameMSG);
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}
			if (validation.DiseaseFamilyLength(appointment) == false) {
				Mmap.put("msg", validation.appointmentMSG);
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}
			if (validation.MessageLength(persnl_unit) == false) {
				Mmap.put("msg", validation.persl_unitMSG);
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}
			if (validation.MessageLength(remarks) == false) {
				Mmap.put("msg", validation.remarkMSG);
				return new ModelAndView("redirect:updateUnusual_Occurrence");
			}

		}
		try {
			Long d = UO_Dao.checkExitstingunusualExistlNo(sus_no, persnl_no, lm.getId());
			lm.setModified_by(username);
			lm.setModified_on(date);
			lm.setPersnl_no(persnl_no);
			lm.setDt_report(date_time_report_i);
			lm.setDate_of_birth(date_of_birth_i);
			lm.setDate_time_admission(date_time_admission_i);
			lm.setDate_time_incident(date_time_incident_i);
			lm.setContact_no(contact_no);
//			if (d == 0) {
//				Long bedE2 = dg.checkExitstingNilData(sus_no, "-1", dt_report, 0);
//				if (!persnl_no.equals("-1")) {
//					if (bedE2 == 0) {
//						Mmap.put("msg", UO_Dao.updateunusualoccurence(lm, username));
//					} else if (bedE2 > 0) {
//						Mmap.put("msg", "Data already Exist.");
//					}
//
//				} else if (persnl_no.equals("-1")) {
//					Long bedE1 = dg.checkExitstingBasicData(sus_no, dt_report, 0);
//					if (bedE1 == 0) {
//						Mmap.put("msg", UO_Dao.updateunusualoccurence(lm, username));
//					} else if (bedE1 > 0) {
//						Mmap.put("msg", "Data already Exist.");
//					}
//
//				}
//
//			}
			
			if (d == 0) {
				Long bedE2 = dg.checkExitstingNilData(sus_no, "-2", dt_report, 0);
				if (persnl_no == null) {
					if (dt_report1Original.equals(dt_report) ) {
						if (bedE2 == 0 || bedE2 == 1) {
							Mmap.put("msg", UO_Dao.updateunusualoccurence(lm, username));
						} else if (bedE2 > 1) {
							Mmap.put("msg", "Data already Exist.");
						}
					} else {
						if (bedE2 == 0) {
							Mmap.put("msg", UO_Dao.updateunusualoccurence(lm, username));
						} else if (bedE2 > 0) {
							Mmap.put("msg", "Data already Exist.");
						}
					}
				} else if (!persnl_no.equals("-2")) {
					if (dt_report1Original.equals(dt_report) ) {
						if (bedE2 == 0 || bedE2 == 1) {
							Mmap.put("msg", UO_Dao.updateunusualoccurence(lm, username));
						} else if (bedE2 > 1) {
							Mmap.put("msg", "Data already Exist.");
						}
					} else {
						if (bedE2 == 0) {
							Mmap.put("msg", UO_Dao.updateunusualoccurence(lm, username));
						} else if (bedE2 > 0) {
							Mmap.put("msg", "Data already Exist.");
						}

					}

				} else if (persnl_no.equals("-2")) {
					Long bedE1 = dg.checkExitstingBasicData(sus_no, dt_report, 0);
//					if (bedE1 == 0) {
//						Mmap.put("msg", UO_Dao.updateunusualoccurence(lm, username));
//					} else if (bedE1 > 0) {
//						Mmap.put("msg", "Data already Exist.");
//					}

					if (dt_report1Original.equals(dt_report)) {
						if (bedE1 == 0 || bedE1 == 1) {
							Mmap.put("msg", UO_Dao.updateunusualoccurence(lm, username));
						} else if (bedE1 > 1) {
							Mmap.put("msg", "Data already Exist.");
						}
					} else {
						if (bedE1 == 0) {
							Mmap.put("msg", UO_Dao.updateunusualoccurence(lm, username));
						} else if (bedE1 > 0) {
							Mmap.put("msg", "Data already Exist.");
						}

					}

				}

			}
			
			if (d > 0)

			{
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
		return new ModelAndView("mnh_unusual_occ_searchDA4Tiles");
	}

	@RequestMapping(value = "/update_unusual_h", method = RequestMethod.POST)
	public ModelAndView update_unusual_h(@ModelAttribute("unusual_h") String unusual_h, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_search_unusual_occurrencesDA", roleid);

		
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
		model.put("unit_name", unusual_h);

		model.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", sessionEdit));
		model.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", sessionEdit));
		model.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", sessionEdit));
		model.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", sessionEdit));
		model.put("getMedDiseaseName", mcommon.getMedDiseaseName("", "", sessionEdit));
		model.put("ml_5", mcommon.getMedrelationList("", sessionEdit));
		return new ModelAndView("mnh_unusual_occDA4Tiles");
	}

}
