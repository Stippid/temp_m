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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.Daily_Disease_serveillenceDaoImpl;
import com.dao.mnh.MNH_Common_DAO;
import com.models.mnh.Tb_Med_Daily_Disease_Surv_Details;
import com.models.mnh.Tb_Med_Daily_Surv_Disease_Mstr;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class dgms_input_Daily_Disease_ServeilleanceController {
	@Autowired
	private Daily_Disease_serveillenceDaoImpl dg;

	@Autowired
	private RoleBaseMenuDAO roledao;

	MNH_CommonController mcommon = new MNH_CommonController();

	MNH_ValidationController validation = new MNH_ValidationController();

	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();

	// *******************Note::open url daily
	// disease*****************************************//

	@RequestMapping(value = "/mnh_daily_disease", method = RequestMethod.GET)
	public ModelAndView mnh_daily_disease(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		Boolean val = roledao.ScreenRedirect("mnh_daily_disease", session.getAttribute("roleid").toString());
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

		Mmap.put("msg", msg);
		Mmap.put("date", date1);
		Mmap.put("ml_5", mcommon.getMedrelationList("", session));
		Mmap.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		Mmap.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		Mmap.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("getMedDiseaseName", mcommon.getMedDiseaseName("", "", session));
		return new ModelAndView("mnh_daily_diseaseTiles");
	}

	// *******************Note::End*****************************************//

	// *******************Note::save daily
	// disease*****************************************//
	@RequestMapping(value = "/daily_disease_sureveAction", method = RequestMethod.POST)
	public ModelAndView daily_disease_sureveAction(
			@ModelAttribute("daily_disease_sureveCMD") Tb_Med_Daily_Disease_Surv_Details rs, HttpServletRequest request,
			ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg)
			throws ParseException {

//			       Boolean val = roledao.ScreenRedirect("mnh_daily_disease", session.getAttribute("roleid").toString());
//		               if(val == false) {
//		                 return new ModelAndView("AccessTiles");
//		                }
//		              if(request.getHeader("Referer") == null ) {
//		                  msg = "";
//		                  return new ModelAndView("redirect:bodyParameterNotAllow");
//		                }

		String username = session.getAttribute("username").toString();
		int id = rs.getId() > 0 ? rs.getId() : 0;
		Date date = new Date();
		Date date_time_incident_i = null;
		Date date_time_admission_i = null;
		Date date_time_report_i = null;
		Date date_of_birth_i = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

		DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

		DateFormat formattern = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

		String unit_name1 = request.getParameter("unit_name1");
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String sus_no1 = request.getParameter("sus_no1");
		if (roleAccess.equals("Unit")) {
			model.put("sus_no", roleSusNo);
			model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			sus_no1 = roleSusNo;

		}

		String service = request.getParameter("service");
	
		String type = request.getParameter("type");
		String dt_report = request.getParameter("dt_report1");
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

		String persnl_name = request.getParameter("persnl_name");
		String relation = request.getParameter("relation");
		String sex = request.getParameter("sex");
		String appointment = request.getParameter("appointment");
		String date_of_birth = request.getParameter("date_of_birth1");
		
		String age_year1 = request.getParameter("age_year");
		String persnl_unit = request.getParameter("persnl_unit");
		String dt_admission = request.getParameter("dt_admission");
	
		String dt_incident = request.getParameter("dt_incident");
		
		String remarks = request.getParameter("remarks");

		BigInteger contact_no = BigInteger.ZERO;
		if (!request.getParameter("contact_no1").equals("")) {
			contact_no = new BigInteger(request.getParameter("contact_no1"));
		}

		BigInteger adhar_card_no = BigInteger.ZERO;
		if (!request.getParameter("adhar_card_no1").equals("")) {
			adhar_card_no = new BigInteger(request.getParameter("adhar_card_no1"));
		}

		if (!dt_incident.equals("")) {
			date_time_incident_i = formattern.parse(dt_incident);
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
		if (btn_hid.equals("1") || btn_hid_exit.equals("1")) {

			if (sus_no1 == null || sus_no1.equals("")) {
				model.put("msg", "Please Enter the SUS No");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (service == null || service.equals("") || service.equals("-1") || service == "-1") {
				model.put("msg", "Please Select the Service");
				return new ModelAndView("redirect:mnh_daily_disease");
			}

			if (type.equals("-1") || type == "-1" || type.equals("") || type == null) {
				model.put("msg", "Please Enter the Service Status");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (dt_report == null || dt_report.equals("")) {
				model.put("msg", "Please Select the Date of Report");
				return new ModelAndView("redirect:mnh_daily_disease");
			}

			if ((!service.equalsIgnoreCase("ARMY") && !service.equalsIgnoreCase("OTHERS"))
					&& (persnl_no2 == null || persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1"))) {

				model.put("msg", "Please Select the Personnel No.");
				return new ModelAndView("redirect:mnh_daily_disease");
			}

			if (category.equals("-1") && service.equalsIgnoreCase("OTHERS")) {
				category = "";
			}
			if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
				model.put("msg", "Please Select the Category");
				return new ModelAndView("redirect:mnh_daily_disease");
			}

			if (persnl_name == null || persnl_name.equals("")) {
				model.put("msg", "Please Select the  Personnel Name");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (persnl_name.equals("") && !service.equalsIgnoreCase("OTHERS")) {
				model.put("msg", "Please Enter the Personnal Name");
				return new ModelAndView("redirect:mnh_daily_disease");
			}

			if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
				model.put("msg", "Please Select the Category");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (sex.equals("-1") || sex == "-1" || sex.equals("") || sex == null) {
				model.put("msg", "Please Enter the Gender");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (relation.equals("-1") || relation == "-1" || relation.equals("") || relation == null) {
				model.put("msg", "Please Enter the Relation");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if ((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF")
					|| relation.equals("SISTEROF")) && !sex.equalsIgnoreCase("Female")) {
				model.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:mnh_daily_disease");
			}

			if ((relation.equals("BROTHEROF") || relation.equals("HUSBANDOF") || relation.equals("FATHEROF")
					|| relation.equals("SONOF")) && !sex.equalsIgnoreCase("Male")) {
				model.put("msg", "Gender should be Male Here");
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (category.equals("MNS") && !sex.equalsIgnoreCase("Female")) {
				model.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:mnh_daily_disease");
			}

			if (dt_admission == null || dt_admission.equals("")) {
				model.put("msg", "Please Select the Date of Admission");
				return new ModelAndView("redirect:mnh_daily_disease");
			}

			if (rs.getDiagnosis().getId() == -1) {
				model.put("msg", "Please Select the Diagnosis");
				return new ModelAndView("redirect:mnh_daily_disease");
			}

			//////// LENGTH VALIDATION///////

			if (validation.sus_noLength(sus_no1) == false) {
				model.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			String adhar_card_no1 = request.getParameter("adhar_card_no1");
			if (validation.adhar_noLength(adhar_card_no1) == false) {
				model.put("msg", validation.adharnoMSG);
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (validation.persnl_noLength(persnl_no2) == false) {
				model.put("msg", validation.persnl_no2MSG);
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (validation.DiseasemmrLength(persnl_name) == false) {
				model.put("msg", validation.persnl_nameMSG);
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (validation.DiseaseFamilyLength(appointment) == false) {
				model.put("msg", validation.appointmentMSG);
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (validation.MessageLength(persnl_unit) == false) {
				model.put("msg", validation.persl_unitMSG);
				return new ModelAndView("redirect:mnh_daily_disease");
			}
			if (validation.MessageLength(remarks) == false) {
				model.put("msg", validation.remarkMSG);
				return new ModelAndView("redirect:mnh_daily_disease");

			}

		}

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session1.beginTransaction();

		try {

			// Long bedE = dg.checkExitstingSURExistlNo(sus_no1, persnl_no, dt_report, id);

			if (id == 0) {
				rs.setCreated_by(username);
				rs.setCreated_on(new Date());
				rs.setPersnl_no(persnl_no);
//				if (persnl_no.equals("-2")) {
//					rs.setCategory("-2");
//				} else {
				rs.setCategory(category);
//				}

				rs.setSus_no(sus_no1);
				rs.setType(type);
				rs.setAge_year(age_year);
				rs.setAdhar_card_no(adhar_card_no);
				rs.setDate_time_incident(date_time_incident_i);
				rs.setDate_time_admission(date_time_admission_i);
				rs.setDt_report(date_time_report_i);
				rs.setDate_of_birth(date_of_birth_i);
				rs.setPersnl_unit(persnl_unit);
				rs.setContact_no(contact_no);

				// if (bedE == 0 ) {
				Long bedE1 = dg.checkExitstingBasicData(sus_no1, dt_report, id);
				Long bedE2 = dg.checkExitstingNilData(sus_no1, "-2", dt_report, id);

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
				// }
//					if (bedE > 0)
//
//					{
//						model.put("msg", "Data already Exist.");
//					}

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

		/*
		 * session1.save(rs); tx.commit(); model.put("msg", "Data Saved Successfully.");
		 */
		/*
		 * if (d == 0) { session1.save(rs); tx.commit(); model.put("msg",
		 * "Data Saved Successfully."); } if (d > 0)
		 * 
		 * { model.put("msg", "Data already Exist."); }
		 */

		/*
		 * } catch (RuntimeException e) { try { tx.rollback(); model.put("msg",
		 * "roll back transaction"); } catch (RuntimeException rbe) { model.put("msg",
		 * "Couldn�t roll back transaction " + rbe); } throw e;
		 * 
		 * } finally { if (session1 != null) { session1.close(); } }
		 */
		if (btn_hid.equals("1"))

		{
			return new ModelAndView("redirect:mnh_daily_disease");

		} else if (btn_hid_exit.equals("1")) {
			return new ModelAndView("redirect:commonDashboard");
			// return new ModelAndView("commanDashboardTiles");
		} else {
			return new ModelAndView("redirect:mnh_daily_disease");
		}

	}

	@RequestMapping(value = "/admin/Change_daily_disease_sureve_action", method = RequestMethod.POST)
	public @ResponseBody String Change_daily_disease_sureve_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Date dt_authority = null;
		Date dt_rank = null;
		String username = session.getAttribute("username").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		Date date_time_incident_i = null;
		Date date_time_admission_i = null;
		Date date_time_report_i = null;
		Date date_of_birth_i = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

		DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

		DateFormat formattern = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

		String unit_name1 = request.getParameter("unit_name1");
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String sus_no1 = request.getParameter("sus_no1");
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			sus_no1 = roleSusNo;

		}

		String diagnosis = request.getParameter("diagnosis");
		
		String service = request.getParameter("service");
		
		String type = request.getParameter("type");
		String dt_report = request.getParameter("dt_report1");
		String persnl_no1 = request.getParameter("persnl_no1");
		
		String persnl_no2 = request.getParameter("persnl_no2");
	
		String persnl_no3 = request.getParameter("persnl_no3");
	
		String persnl_no = null;
		if (persnl_no1.equalsIgnoreCase("-1") && !service.equalsIgnoreCase("OTHERS")) {
		
			persnl_no = persnl_no2 + persnl_no3;
		} else if (!persnl_no1.equalsIgnoreCase("-1") && !persnl_no2.equalsIgnoreCase("")
				&& !persnl_no3.equalsIgnoreCase("-1") && service.equalsIgnoreCase("ARMY")) {
			
			persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
		} else if (service.equalsIgnoreCase("OTHERS")) {
			
			persnl_no = "-1";
		}
		String category = request.getParameter("category");

		String persnl_name = request.getParameter("persnl_name");
		String relation = request.getParameter("relation");
		String sex = request.getParameter("sex");
		String appointment = request.getParameter("appointment");
		String date_of_birth = request.getParameter("date_of_birth1");
		
		String age_year1 = request.getParameter("age_year");
		String persnl_unit = request.getParameter("persnl_unit");
		String dt_admission = request.getParameter("dt_admission");
		String dt_incident = request.getParameter("dt_incident");
		
		String remarks = request.getParameter("remarks");
		String ch_r_id = request.getParameter("ch_r_id");
		BigInteger contact_no = BigInteger.ZERO;
		if (!request.getParameter("contact_no").equals("")) {
			contact_no = new BigInteger(request.getParameter("contact_no"));
		}

		BigInteger adhar_card_no = BigInteger.ZERO;
		if (!request.getParameter("adhar_card_no1").equals("")) {
			adhar_card_no = new BigInteger(request.getParameter("adhar_card_no1"));
		}

		if (!dt_incident.equals("")) {
			date_time_incident_i = formattern.parse(dt_incident);
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

		String msg = "";
		// try {
		/*
		 * Query q0 = sessionhql.
		 * createQuery("select count(id) from TB_CHANGE_OF_RANK where date_of_rank=:date_of_rank and comm_id=:comm_id and  id!=:id and status!=-1"
		 * ).setTimestamp("date_of_rank", dt_rank) .setParameter("id",
		 * Integer.parseInt(ch_r_id)).setParameter("comm_id", comm_id);
		 */
		// Long d = dg.checkExitstingSURExistlNo_nil(sus_no1,
		// Integer.parseInt(ch_r_id));
		// Long c = (Long) q0.uniqueResult();
		// if(d>0) {
		// return "Date Of Rank Already Exists";
		// }

		if (Integer.parseInt(ch_r_id) == 0) {
			Tb_Med_Daily_Disease_Surv_Details rs = new Tb_Med_Daily_Disease_Surv_Details();
			rs.setCreated_by(username);
			rs.setCreated_on(new Date());
			rs.setPersnl_no(persnl_no);
			rs.setCategory(category);

			rs.setSus_no(sus_no1);
			rs.setType(type);
			rs.setAge_year(age_year);
			rs.setAdhar_card_no(adhar_card_no);
			rs.setDate_time_incident(date_time_incident_i);
			rs.setDate_time_admission(date_time_admission_i);
			rs.setDt_report(date_time_report_i);
			rs.setDate_of_birth(date_of_birth_i);
			rs.setContact_no(contact_no);

			int id = (int) sessionhql.save(rs);
			msg = Integer.toString(id);
		}
		/*
		 * else {
		 * 
		 * String hql =
		 * "update TB_CHANGE_OF_RANK set authority=:authority,date_of_authority=:date_of_authority,rank_type=:rank_type,"
		 * +
		 * "rank=:rank,date_of_rank=:date_of_rank,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
		 * + " where  id=:id"; Query query =
		 * sessionhql.createQuery(hql).setString("authority", authority)
		 * .setDate("date_of_authority", dt_authority).setInteger("rank_type",
		 * Integer.parseInt(rank_type)) .setInteger("rank",
		 * Integer.parseInt(rank)).setDate("date_of_rank", dt_rank) .setInteger("id",
		 * Integer.parseInt(ch_r_id)).setString("modified_by", username)
		 * .setTimestamp("modified_date", new Date()).setInteger("status", 0);
		 * 
		 * msg = query.executeUpdate() > 0 ? "update" : "0";
		 * 
		 * }
		 */
		tx.commit();
		/*
		 * } catch (RuntimeException e) { try { tx.rollback(); msg = "0"; } catch
		 * (RuntimeException rbe) { msg = "0"; }
		 * 
		 * } finally { if (sessionhql != null) { sessionhql.close(); } }
		 */

		return msg;
	}

	@RequestMapping(value = "/mnh_daily_disease_search", method = RequestMethod.GET)
	public ModelAndView mnh_daily_disease_search(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		Boolean val = roledao.ScreenRedirect("mnh_daily_disease_search", session.getAttribute("roleid").toString());
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

		int userid = (Integer) session.getAttribute("userId");
		Mmap.put("msg", msg);
		Mmap.put("date", date1);
		Mmap.put("to_date", to_date);
		Mmap.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
		Mmap.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
		Mmap.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("getMedDiseaseName", mcommon.getMedDiseaseName("", "", session));
		return new ModelAndView("mnh_daily_disease_searchTiles");
	}

	// *******************Note::search daily disease
	// serveillence*****************************************//

	@RequestMapping(value = "/search_daily_disease", method = RequestMethod.POST)
	public ModelAndView search_daily_disease(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam(value = "sus1", required = false) String sus1, String frm_dt1, String to_dt1, String unit1,
			String adhar1, String contact1) {
		Boolean val = roledao.ScreenRedirect("mnh_daily_disease_search", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		if (sus1 == null || sus1.equals("")) {
			Mmap.put("msg", "Please Enter the SUS No");
			return new ModelAndView("redirect:mnh_daily_disease_search");
		}
		if (validation.DiseasemmrLength(unit1) == false) {
			Mmap.put("msg", validation.hospital_nameMSG);
			return new ModelAndView("redirect:mnh_daily_disease_search");
		}
		if (validation.sus_noLength(sus1) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:mnh_daily_disease_search");
		}
		if (validation.adhar_noLength(adhar1) == false) {
			Mmap.put("msg", validation.adharnoMSG);
			return new ModelAndView("redirect:mnh_daily_disease_search");
		}
		try {
			Date to_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(to_dt1);
			Date from_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(frm_dt1);
			if (to_date1.before(from_date1)) {
				Mmap.put("msg", "To date must be greater than from date");
				return new ModelAndView("redirect:mnh_daily_disease_search");
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
		List<Map<String, Object>> list = dg.search_daily_activity_data(f_sus_no, frm_dt1, to_dt1, adhar1, contact1);
		Mmap.put("list", list);
		Mmap.put("size", list.size());

		return new ModelAndView("mnh_daily_disease_searchTiles");

	}

	// *******************Note::END*****************************************//

	// *******************Note::delete daily disease
	// servill*****************************************//
	@RequestMapping(value = "/delete_daily_disease_servill", method = RequestMethod.POST)
	public ModelAndView delete_daily_disease_servill(@ModelAttribute("id1") int id, BindingResult result,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, ModelMap model,
			HttpSession session1) {
		List<String> liststr = new ArrayList<String>();
		Boolean val = roledao.ScreenRedirect("mnh_daily_disease_search", session1.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		try {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from Tb_Med_Daily_Disease_Surv_Details where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			session.close();
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
		return new ModelAndView("mnh_daily_disease_searchTiles");
	}

	// *******************Note::END*****************************************//

	// *******************Note::update daily disease servill
	// *****************************************//
	@RequestMapping(value = "/updatedaily_disease", method = RequestMethod.POST)
	public ModelAndView updatedaily_disease(@ModelAttribute("updateid") String updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_daily_disease_search", roleid);
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

		Tb_Med_Daily_Disease_Surv_Details dailyDetails = dg.updatedaily_diseaseByid(Integer.parseInt(updateid));

		model.put("edit_daily_disease_sureveCMD", dailyDetails);
		model.put("msg", msg);

		model.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", sessionEdit));
		model.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", sessionEdit));
		model.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", sessionEdit));
		model.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", sessionEdit));
		model.put("getMedDiseaseName", mcommon.getMedDiseaseName("", "", sessionEdit));
		model.put("ml_5", mcommon.getMedrelationList("", sessionEdit));
		return new ModelAndView("edit_mnh_DA_daily_disease_surveTiles");
	}

	@RequestMapping(value = "/edit_daily_disease_sureveAction", method = RequestMethod.POST)
	public ModelAndView edit_daily_disease_sureveAction(
			@ModelAttribute("edit_daily_disease_sureveCMD") Tb_Med_Daily_Disease_Surv_Details lm,
			HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg, ModelMap Mmap,
			HttpSession session) throws ParseException {
		Boolean val = roledao.ScreenRedirect("mnh_daily_disease_search", session.getAttribute("roleid").toString());
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

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String sus_no1 = request.getParameter("sus_no");
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			sus_no1 = roleSusNo;

		}
		String service = request.getParameter("service");
		String adhar_card_no1 = request.getParameter("adhar_card_no");
		String type = request.getParameter("type");
		String dt_report = request.getParameter("dt_report1");
		String dt_report1Original = request.getParameter("dt_report1Original");
		String persnl_no_Original = request.getParameter("persnl_no_Original");

		String persnl_no1 = request.getParameter("persnl_no1");
		String persnl_no2 = request.getParameter("persnl_no");
		String persnl_no3 = request.getParameter("persnl_no3");
		String persnl_no = null;
		String category = request.getParameter("category");
		String sex = request.getParameter("sex");
		String relation = request.getParameter("relation");
		String persnl_name = request.getParameter("persnl_name");
		String appointment = request.getParameter("appointment");
		String date_of_birth = request.getParameter("date_of_birth1");
		String age_year1 = request.getParameter("age_year");
		String persnl_unit = request.getParameter("persnl_unit");
		String dt_admission = request.getParameter("dt_admission1");
		String dt_incident = request.getParameter("dt_incident1");
		String diagnosis = request.getParameter("diagnosis");
		String remarks = request.getParameter("remarks");

		BigInteger contact_no = BigInteger.ZERO;
		if (!request.getParameter("contact_no1").equals("")) {
			contact_no = new BigInteger(request.getParameter("contact_no1"));
		}

		BigInteger adhar_card_no = BigInteger.ZERO;
		if (adhar_card_no1 != null && !request.getParameter("adhar_card_no").equals("")) {
			adhar_card_no = new BigInteger(adhar_card_no1);
		}

//String nillFlag = request.getParameter("nillFlag");

		// String persnl_no = null;
		if (persnl_no_Original.equalsIgnoreCase("-2") && service.equalsIgnoreCase("-1")) {
			persnl_no = "-2";
		} else if (persnl_no1.equalsIgnoreCase("-1") && !service.equalsIgnoreCase("OTHERS")) {
			persnl_no = persnl_no2 + persnl_no3;
		} else if (!persnl_no1.equalsIgnoreCase("-1") && !persnl_no2.equalsIgnoreCase("")
				&& !persnl_no3.equalsIgnoreCase("-1") && service.equalsIgnoreCase("ARMY")) {
			persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
		} else if (service.equalsIgnoreCase("OTHERS")) {
			persnl_no = null;
		} else if (persnl_no1.equalsIgnoreCase("-2")) {
			persnl_no = persnl_no1;
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

		Integer age_year = 0;
		if (!date_of_birth.equals("")) {
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

		if (sus_no1 == null || sus_no1.equals("")) {
			Mmap.put("msg", "Please Enter the SUS No");
			return new ModelAndView("redirect:updatedaily_disease");
		}

		if ((service.equalsIgnoreCase("ARMY") && !category.equalsIgnoreCase("OR")) && (persnl_no1.equalsIgnoreCase("-1")
				|| persnl_no2 == null || persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1"))) {
			Mmap.put("msg", "Please Select the Personnel No.");
			return new ModelAndView("redirect:updatedaily_disease");
		}
		if (dt_report == null || dt_report.equals("")) {
			Mmap.put("msg", "Please Select the Date of Report");
			return new ModelAndView("redirect:updatedaily_disease");
		}
		if (!category.equals("-1")) {
			if ((!service.equalsIgnoreCase("ARMY") && !service.equalsIgnoreCase("OTHERS"))
					&& (persnl_no2 == null || persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1"))) {

				Mmap.put("msg", "Please Select the Personnel No.");
				return new ModelAndView("redirect:updatedaily_disease");
			}

			if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Select the category");
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if (category == null || category.equals("")) {
				Mmap.put("msg", "Please Select the category");
				return new ModelAndView("redirect:updatedaily_disease");
			}

			if (persnl_name == null || persnl_name.equals("")) {
				Mmap.put("msg", "Please Select the  Personnel Name");
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if (persnl_name.equals("") && !service.equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Enter the Personnal Name");
				return new ModelAndView("redirect:updatedaily_disease");
			}

			if (category.equals("-1") && service.equalsIgnoreCase("OTHERS")) {
				category = "";
			}
			if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Select the Category");
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if (type.equals("-1") || type == "-1" || type.equals("") || type == null) {
				Mmap.put("msg", "Please Enter the Service Status");
				return new ModelAndView("redirect:updatedaily_disease");
			}

			if ((!service.equalsIgnoreCase("ARMY") && !service.equalsIgnoreCase("OTHERS"))
					&& (persnl_no2 == null || persnl_no2.equals("") || persnl_no3 == null || persnl_no3.equals("-1"))) {

				Mmap.put("msg", "Please Select the Personnel No.");
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if (category.equals("-1") && service.equalsIgnoreCase("OTHERS")) {
				category = "";
			}
			/*
			 * if (category.equals("-1") || category == "-1" || category == null ||
			 * category.equals("")) { Mmap.put("msg", "Please Select the category"); return
			 * new ModelAndView("redirect:updatedaily_disease"); } if (lm.getRank().getId()
			 * == -1) { Mmap.put("msg", "Please Select the rank"); return new
			 * ModelAndView("redirect:updatedaily_disease"); }
			 */
			if (persnl_name == null || persnl_name.equals("")) {
				Mmap.put("msg", "Please Select the  Personnel Name");
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if (persnl_name.equals("") && !service.equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Enter the Personnal Name");
				return new ModelAndView("redirect:updatedaily_disease");
			}

			if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Select the Category");
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if (sex.equals("-1") || sex == "-1" || sex.equals("") || sex == null) {
				Mmap.put("msg", "Please Enter the Gender");
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if (relation.equals("-1") || relation == "-1" || relation.equals("") || relation == null) {
				Mmap.put("msg", "Please Enter the Relation");
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if ((relation.equals("WIFEOF") || relation.equals("MOTHEROF") || relation.equals("DAUGHTEROF")
					|| relation.equals("SISTEROF")) && !sex.equalsIgnoreCase("Female")) {
				Mmap.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:updatedaily_disease");
			}

			if ((relation.equals("BROTHEROF") || relation.equals("HUSBANDOF") || relation.equals("FATHEROF")
					|| relation.equals("SONOF")) && !sex.equalsIgnoreCase("Male")) {
				Mmap.put("msg", "Gender should be Male Here");
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if (category.equals("MNS") && !sex.equals("Female")) {
				Mmap.put("msg", "Gender should be Female Here");
				return new ModelAndView("redirect:updatedaily_disease");
			}

			if (lm.getRank().getId() == -1 && !service.equalsIgnoreCase("OTHERS")) {
				Mmap.put("msg", "Please Select the Rank");
				return new ModelAndView("redirect:updatedaily_disease");
			}

			if (dt_admission == null || dt_admission.equals("")) {
				Mmap.put("msg", "Please Select the Date of Admission");
				return new ModelAndView("redirect:updatedaily_disease");
			}

			if (lm.getDiagnosis().getId() == -1) {
				Mmap.put("msg", "Please Select the Diagnosis");
				return new ModelAndView("redirect:updatedaily_disease");
			}

			///////// LENGTH VALIDATION/////////

			if (validation.sus_noLength(sus_no1) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:updatedaily_disease");
			}

			if (validation.adhar_noLength(adhar_card_no1) == false) {
				Mmap.put("msg", validation.adharnoMSG);
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if (validation.persnl_noLength(persnl_no2) == false) {
				Mmap.put("msg", validation.persnl_no2MSG);
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if (validation.DiseasemmrLength(persnl_name) == false) {
				Mmap.put("msg", validation.persnl_nameMSG);
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if (validation.DiseaseFamilyLength(appointment) == false) {
				Mmap.put("msg", validation.appointmentMSG);
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if (validation.MessageLength(persnl_unit) == false) {
				Mmap.put("msg", validation.persl_unitMSG);
				return new ModelAndView("redirect:updatedaily_disease");
			}
			if (validation.MessageLength(remarks) == false) {
				Mmap.put("msg", validation.remarkMSG);
				return new ModelAndView("redirect:updatedaily_disease");
			}
		}

		// try {
		Long d = dg.checkExitstingSURExistlNo(sus_no1, persnl_no, dt_report, lm.getId());

		lm.setModified_by(username);
		lm.setModified_on(date);
		lm.setPersnl_no(persnl_no);
		lm.setDt_report(date_time_report_i);
		lm.setDate_of_birth(date_of_birth_i);
		lm.setContact_no(contact_no);
		lm.setDate_time_admission(date_time_admission_i);
		lm.setDate_time_incident(date_time_incident_i);
		lm.setAge_year(age_year);

		if (d == 0) {
			Long bedE2 = dg.checkExitstingNilData(sus_no1, "-2", dt_report, 0);
			if (persnl_no == null) {
				if (dt_report1Original.equals(dt_report) ) {
					if (bedE2 == 0 || bedE2 == 1) {
						Mmap.put("msg", dg.updatedailysdisease(lm, username));
					} else if (bedE2 > 1) {
						Mmap.put("msg", "Data already Exist.");
					}
				} else {
					if (bedE2 == 0) {
						Mmap.put("msg", dg.updatedailysdisease(lm, username));
					} else if (bedE2 > 0) {
						Mmap.put("msg", "Data already Exist.");
					}
				}
			} else if (!persnl_no.equals("-2")) {
				if (dt_report1Original.equals(dt_report) ) {
					if (bedE2 == 0 || bedE2 == 1) {
						Mmap.put("msg", dg.updatedailysdisease(lm, username));
					} else if (bedE2 > 1) {
						Mmap.put("msg", "Data already Exist.");
					}
				} else {
					if (bedE2 == 0) {
						Mmap.put("msg", dg.updatedailysdisease(lm, username));
					} else if (bedE2 > 0) {
						Mmap.put("msg", "Data already Exist.");
					}

				}

			} else if (persnl_no.equals("-2")) {
				Long bedE1 = dg.checkExitstingBasicData(sus_no1, dt_report, 0);
//				if (bedE1 == 0) {
//					Mmap.put("msg", dg.updatedailysdisease(lm, username));
//				} else if (bedE1 > 0) {
//					Mmap.put("msg", "Data already Exist.");
//				}

				if (dt_report1Original.equals(dt_report)) {
					if (bedE1 == 0 || bedE1 == 1) {
						Mmap.put("msg", dg.updatedailysdisease(lm, username));
					} else if (bedE1 > 1) {
						Mmap.put("msg", "Data already Exist.");
					}
				} else {
					if (bedE1 == 0) {
						Mmap.put("msg", dg.updatedailysdisease(lm, username));
					} else if (bedE1 > 0) {
						Mmap.put("msg", "Data already Exist.");
					}

				}

			}

		}
		if (d > 0) {
			Mmap.put("msg", "Data already Exist.");
		}

		/*
		 * } catch (RuntimeException e) { try {
		 * 
		 * Mmap.put("msg", "roll back transaction"); } catch (RuntimeException rbe) {
		 * Mmap.put("msg", "Couldn�t roll back transaction " + rbe); } throw e; }
		 * finally {
		 * 
		 * }
		 */
		return new ModelAndView("mnh_daily_disease_searchTiles");
	}

	// *******************Note::END*****************************************//

	@RequestMapping(value = "/updatedaily_disease_h", method = RequestMethod.POST)
	public ModelAndView updatedaily_disease_h(@ModelAttribute("unit_name_h") String unit_name_h, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_daily_disease_search", roleid);

		
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
		model.put("unit_name", unit_name_h);

		model.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", sessionEdit));
		model.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", sessionEdit));
		model.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", sessionEdit));
		model.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", sessionEdit));
		model.put("getMedDiseaseName", mcommon.getMedDiseaseName("", "", sessionEdit));
		model.put("ml_5", mcommon.getMedrelationList("", sessionEdit));
		return new ModelAndView("mnh_daily_diseaseTiles");
	}

}
