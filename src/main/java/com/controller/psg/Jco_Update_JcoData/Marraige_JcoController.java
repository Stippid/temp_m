package com.controller.psg.Jco_Update_JcoData;





import java.text.DateFormat;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;

import java.util.Locale;



import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;



import org.hibernate.Query;

import org.hibernate.Session;

import org.hibernate.Transaction;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;



import com.controller.psg.Jco_COMMON.psg_jco_CommonController;

import com.controller.psg.Master.Psg_CommonController;

import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;

import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FAMILY_MARRIED_JCO;

import com.models.psg.Jco_Update_JcoData.TB_CENSUS_SPOUSE_QUALIFICATION_JCO;

import com.persistance.util.HibernateUtil;



@Controller



@RequestMapping(value = { "admin", "/", "user" })



public class Marraige_JcoController {



	Psg_CommonController mcommon = new Psg_CommonController();



	private psg_jco_CommonController pjc = new psg_jco_CommonController();



	@RequestMapping(value = "/admin/update_family_marriage_JCOaction", method = RequestMethod.POST)



	public @ResponseBody String update_family_marriage_action(ModelMap Mmap, HttpSession session,



			HttpServletRequest request) throws ParseException {



		



		int final_mId = 0;



		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);



		Session sessionhql = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionhql.beginTransaction();



		Date date = new Date();



		Date marriage_date_birth = null;



		Date marriage_dt = null;



		Date divorce_dt = null;



		Date marriage_date_authority_com = null;



		String ser_radio = null;



		String spouse_service = "0";



		String spouseSer_other = null;



		String spouse_personal_no = null;



		String username = session.getAttribute("username").toString();



		String maiden_name = request.getParameter("maiden_name");



		String marriage_date_of_birth = request.getParameter("marriage_date_of_birth");



		Date enroll_date = format.parse(request.getParameter("enroll_date"));
 


		if (!marriage_date_of_birth.equals("") && !marriage_date_of_birth.equals("DD/MM/YYYY")) {



			marriage_date_birth = format.parse(marriage_date_of_birth);



		}



		String marriage_place_of_birth = request.getParameter("marriage_place_of_birth");



		String marriage_nationality = request.getParameter("marriage_nationality");



		String Nationality_other = null;



		String marriage_date = request.getParameter("marriage_date");



		if (!marriage_date.equals("") && !marriage_date.equals("DD/MM/YYYY")) {



			marriage_dt = format.parse(marriage_date);



		}



		String marr_ch_id = request.getParameter("marr_ch_id");



		String p_id = request.getParameter("p_id");



		String divorce_date = request.getParameter("divorce_date");



		if (!divorce_date.equals("") && !divorce_date.equals("DD/MM/YYYY")) {



			divorce_dt = format.parse(divorce_date);



		}



		String marriage_authority = request.getParameter("marriage_authority");



		String marriage_date_of_authority = request.getParameter("marriage_date_of_authority");



		if (!marriage_date_of_authority.equals("") && !marriage_date_of_authority.equals("DD/MM/YYYY")) {



			marriage_date_authority_com = format.parse(marriage_date_of_authority);



		}



		String marriage_type_of_event = request.getParameter("marital_event");



		String marital_status = request.getParameter("marital_status");



		String pan_card = request.getParameter("pan_card");



		String jco_id = request.getParameter("jco_id");



		String curr_marital_status = request.getParameter("curr_marital_status");



		String separated_from_dt = request.getParameter("separated_from_dt");



		String separated_to_dt = request.getParameter("separated_to_dt");



		String msg = "";



		String adhar_number = "";



		String adhar = request.getParameter("marriage_adhar_no");



		if (adhar != null && !adhar.equals("")) {



			adhar_number = adhar;



		}



		if (marriage_type_of_event == null || marriage_type_of_event.equals("0")) {



			return "Please Select The Marital Event";



		}



		if (marriage_authority == null || marriage_authority.equals("")) {



			return "Please Enter the Authority";



		}



		if (marriage_date_of_authority == null || marriage_date_of_authority.equals("null")



				|| marriage_date_of_authority.equals("DD/MM/YYYY") || marriage_date_of_authority.equals("")) {



			return "Please Select the Date of Authority";



		}



		if (mcommon.CompareDate(marriage_date_authority_com, enroll_date) == 0) {



			return "Authority Date should be Greater than Enrollment Date";



		}



		if (marriage_type_of_event.equals("1") || marriage_type_of_event.equals("3")) {



			if (curr_marital_status.equals("13")) {



				if (separated_from_dt == null || separated_from_dt.equals("null")



						|| separated_from_dt.equals("DD/MM/YYYY") || separated_from_dt.equals("")) {



					return "Please Enter Date of Separation";



				}



				if (marriage_date != "" & separated_from_dt != ""



						|| marriage_date != "DD/MM/YYY" & separated_from_dt != "DD/MM/YYY") {



					if (mcommon.CompareDate(format.parse(separated_from_dt), format.parse(marriage_date)) == 0) {



						return " Date of Separation should not be before Marriage Date";



					}



				}



				if (separated_to_dt == null || separated_to_dt.equals("null") || separated_to_dt.equals("DD/MM/YYYY")



						|| separated_to_dt.equals("")) {



					separated_to_dt = null;



				} else {



					if (marriage_date != "" & separated_to_dt != ""



							|| marriage_date != "DD/MM/YYY" & separated_to_dt != "DD/MM/YYY") {



						if (mcommon.CompareDate(format.parse(separated_to_dt), format.parse(separated_from_dt)) == 0) {



							return " Separation To Date should not be before Date of Separation";



						}



					}



				}



			} else {



				if (maiden_name == null || maiden_name.equals("")) {



					return "Please Enter The Spouse Name";



				}



				if (marriage_date == null || marriage_date.equals("null") || marriage_date.equals("DD/MM/YYYY")



						|| marriage_date.equals("")) {



					return "Please Enter The Date of Marriage";



				}



				if (marriage_place_of_birth == null || marriage_place_of_birth.equals("")) {



					return "Please Enter The Place of Birth";



				}



				if (marriage_nationality == null || marriage_nationality.equals("0")) {



					return "Please Select The Nationality";



				}



				if (request.getParameter("marriage_nationality_other") != null



						&& !request.getParameter("marriage_nationality_other").equals("")) {



					Nationality_other = request.getParameter("marriage_nationality_other");



				}



				if (marriage_date_of_birth == null || marriage_date_of_birth.equals("null")



						|| marriage_date_of_birth.equals("DD/MM/YYYY") || marriage_date_of_birth.equals("")) {



					return "Please Enter The Date of Birth";



				}



				if (adhar_number == null || adhar_number.equals("")) {



					return "Please Enter The Adhar Number";



				}



				if (request.getParameter("ser_radio") == null || request.getParameter("ser_radio").equals("")) {



					return "Please Check Spouse In Service";



				} else {



					ser_radio = request.getParameter("ser_radio");



				}



				if (request.getParameter("ser_radio").equals("Yes")) {



					if (request.getParameter("spouse_service") == null



							|| request.getParameter("spouse_service").equals("")) {



						return "Please Select Spouse In Service";



					} else {



						spouse_service = request.getParameter("spouse_service");



					}



					if (request.getParameter("spouse_service").equals("9")) {



						if (request.getParameter("spouseSer_other") == null



								|| request.getParameter("spouseSer_other").equals("")) {



							return "Please Enter Other Service";



						} else {



							spouseSer_other = request.getParameter("spouseSer_other");



						}



					} else {



						if (request.getParameter("spouse_personal_no") != null



								&& !request.getParameter("spouse_personal_no").equals("")) {



							spouse_personal_no = request.getParameter("spouse_personal_no");



						}



					}



				}



			}



		}



	



		if (marriage_type_of_event.equals("2") || marriage_type_of_event.equals("5")



				|| marriage_type_of_event.equals("6")) {



			String d_ww_dt = "";



			if (marriage_type_of_event.equals("2")) {



				d_ww_dt = "Divorce";



			} else {



				d_ww_dt = "Death";



			}



			if (divorce_date == null || divorce_date.equals("null") || divorce_date.equals("DD/MM/YYYY")



					|| divorce_date.equals("")) {



				return "Please Enter The " + d_ww_dt + " Date";



			}



			if (marriage_date != "" & divorce_date != ""



					|| marriage_date != "DD/MM/YYY" & divorce_date != "DD/MM/YYY") {



			



				if (mcommon.CompareDate(format.parse(divorce_date), format.parse(marriage_date)) == 0) {



					



					return d_ww_dt + " Date should not be before Marriage Date";



				}



			}



		} else {



			divorce_dt = null;



		}



		if (marriage_type_of_event.equals("4")) {



			if (curr_marital_status.equals("8")) {



				if (separated_from_dt == null || separated_from_dt.equals("null")



						|| separated_from_dt.equals("DD/MM/YYYY") || separated_from_dt.equals("")) {



					return "Please Enter From Date";



				}



				if (marriage_date != "" & separated_from_dt != ""



						|| marriage_date != "DD/MM/YYY" & separated_from_dt != "DD/MM/YYY") {



					if (mcommon.CompareDate(format.parse(separated_from_dt), format.parse(marriage_date)) == 0) {



						return " From Date should not be before Marriage Date";



					}



				}



			}



			if (curr_marital_status.equals("13")) {



				if (separated_from_dt == null || separated_from_dt.equals("null")



						|| separated_from_dt.equals("DD/MM/YYYY") || separated_from_dt.equals("")) {



					return "Please Enter From Date";



				}



				if (marriage_date != "" & separated_from_dt != ""



						|| marriage_date != "DD/MM/YYY" & separated_from_dt != "DD/MM/YYY") {



					if (mcommon.CompareDate(format.parse(separated_from_dt), format.parse(marriage_date)) == 0) {



						return " From Date should not be before Marriage Date";



					}



				}



				if (separated_to_dt == null || separated_to_dt.equals("null") || separated_to_dt.equals("DD/MM/YYYY")



						|| separated_to_dt.equals("")) {



					return "Please Enter To Date";



				}



				if (marriage_date != "" & separated_to_dt != ""



						|| marriage_date != "DD/MM/YYY" & separated_to_dt != "DD/MM/YYY") {



					if (mcommon.CompareDate(format.parse(separated_to_dt), format.parse(separated_from_dt)) == 0) {



						return " To Date should not be before From Date";



					}



				}



			}



		}



		try {



			if (Integer.parseInt(marr_ch_id) == 0) {



				TB_CENSUS_FAMILY_MARRIED_JCO fam_marr = new TB_CENSUS_FAMILY_MARRIED_JCO();



				fam_marr.setMaiden_name(maiden_name);



				if (marriage_type_of_event.equals("1") || marriage_type_of_event.equals("3")) {



					fam_marr.setDate_of_birth(marriage_date_birth);



					fam_marr.setMarriage_date(marriage_dt);



					fam_marr.setPlace_of_birth(marriage_place_of_birth);



					fam_marr.setNationality(Integer.parseInt(marriage_nationality));



					fam_marr.setAdhar_number(adhar_number);



					fam_marr.setAuthority(marriage_authority);



					fam_marr.setDate_of_authority(marriage_date_authority_com);



					fam_marr.setPan_card(pan_card);



					fam_marr.setIf_spouse_ser(ser_radio);



					fam_marr.setSpouse_service(Integer.parseInt(spouse_service));



					fam_marr.setOther_spouse_ser(spouseSer_other);



					fam_marr.setSpouse_personal_no(spouse_personal_no);



					fam_marr.setOther_nationality(Nationality_other);



				}



			



				fam_marr.setCreated_by(username);



				fam_marr.setCreated_date(date);



				fam_marr.setJco_id(Integer.parseInt(jco_id));



				fam_marr.setType_of_event(Integer.parseInt(marriage_type_of_event));



				fam_marr.setMarital_status(Integer.parseInt(marital_status));



				fam_marr.setStatus(0);

				fam_marr.setInitiated_from("u");

				final_mId = (int) sessionhql.save(fam_marr);



				// msg = Integer.toString(id);



			} else {



				TB_CENSUS_FAMILY_MARRIED_JCO oOBJ = (TB_CENSUS_FAMILY_MARRIED_JCO) sessionhql.get(TB_CENSUS_FAMILY_MARRIED_JCO.class,



						Integer.parseInt(marr_ch_id));



				if ((oOBJ.getStatus() == 0 || oOBJ.getStatus() == 3)



						&& (oOBJ.getType_of_event() == 1 || oOBJ.getType_of_event() == 3)



						&& !curr_marital_status.equals("13")) {



					oOBJ.setDate_of_birth(marriage_date_birth);



					oOBJ.setMaiden_name(maiden_name);



					oOBJ.setMarriage_date(marriage_dt);



					oOBJ.setPlace_of_birth(marriage_place_of_birth);



					oOBJ.setNationality(Integer.parseInt(marriage_nationality));



					oOBJ.setAdhar_number(adhar_number);



					oOBJ.setAuthority(marriage_authority);



					oOBJ.setDate_of_authority(marriage_date_authority_com);



					oOBJ.setPan_card(pan_card);



					oOBJ.setIf_spouse_ser(ser_radio);



					oOBJ.setSpouse_service(Integer.parseInt(spouse_service));



					oOBJ.setOther_spouse_ser(spouseSer_other);



					oOBJ.setSpouse_personal_no(spouse_personal_no);



					oOBJ.setOther_nationality(Nationality_other);



					oOBJ.setModified_by(username);



					oOBJ.setModified_date(date);



					oOBJ.setStatus(0);



					sessionhql.update(oOBJ);



					msg = "update";



				} else if ((oOBJ.getStatus() == 0 || oOBJ.getStatus() == 3)



						&& oOBJ.getType_of_event() == Integer.parseInt(marriage_type_of_event)) {



					oOBJ.setModified_by(username);



					oOBJ.setModified_date(date);



					oOBJ.setAuthority(marriage_authority);



					oOBJ.setDate_of_authority(marriage_date_authority_com);



				



					if (oOBJ.getType_of_event() == 2 || oOBJ.getType_of_event() == 5 || oOBJ.getType_of_event() == 6) {



						oOBJ.setDivorce_date(divorce_dt);



					} else if (oOBJ.getType_of_event() == 4 && curr_marital_status.equals("8")) {



						oOBJ.setDivorce_date(null);



						oOBJ.setSeparated_from_dt(format.parse(separated_from_dt));



						oOBJ.setSeparated_to_dt(null);



					} else if (oOBJ.getType_of_event() == 1 && curr_marital_status.equals("13")) {



						oOBJ.setDivorce_date(null);



						if (separated_to_dt != null)



							oOBJ.setSeparated_to_dt(format.parse(separated_to_dt));



						else



							oOBJ.setSeparated_to_dt(null);



					}



					oOBJ.setStatus(0);



					sessionhql.update(oOBJ);



					msg = "update";



				} else if ((oOBJ.getStatus() == 0 || oOBJ.getStatus() == 3)



						&& oOBJ.getType_of_event() != Integer.parseInt(marriage_type_of_event)) {



					String hqlUpdate = "delete from TB_CENSUS_FAMILY_MARRIED_JCO where jco_id=:jco_id and status=0";



					int app = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", oOBJ.getJco_id())

							.executeUpdate();



					



					TB_CENSUS_FAMILY_MARRIED_JCO nOBJ = new TB_CENSUS_FAMILY_MARRIED_JCO();



					nOBJ = setMarriageData(nOBJ, oOBJ);



					nOBJ.setAuthority(marriage_authority);



					nOBJ.setDate_of_authority(marriage_date_authority_com);



					nOBJ.setCreated_by(username);



					nOBJ.setCreated_date(date);



					nOBJ.setType_of_event(Integer.parseInt(marriage_type_of_event));



					nOBJ.setMarital_status(Integer.parseInt(marital_status));



					if (nOBJ.getType_of_event() == 2 || nOBJ.getType_of_event() == 5 || nOBJ.getType_of_event() == 6) {



						nOBJ.setDivorce_date(divorce_dt);



					} else if (nOBJ.getType_of_event() == 4 && curr_marital_status.equals("8")) {



						nOBJ.setDivorce_date(null);



						nOBJ.setSeparated_from_dt(format.parse(separated_from_dt));



						nOBJ.setSeparated_to_dt(null);



					} else if (nOBJ.getType_of_event() == 1 && curr_marital_status.equals("13")) {



						nOBJ.setDivorce_date(null);



						if (separated_to_dt != null)



							nOBJ.setSeparated_to_dt(format.parse(separated_to_dt));



						else



							nOBJ.setSeparated_to_dt(null);



					}



					nOBJ.setStatus(0);



					final_mId = (int) sessionhql.save(nOBJ);



				} else if (oOBJ.getStatus() == 1 && Integer.parseInt(marriage_type_of_event) == 4



						&& Integer.parseInt(curr_marital_status) == 8) {



				



					String hqlUpdate = "delete from TB_CENSUS_FAMILY_MARRIED_JCO where jco_id=:jco_id and status=0";



					int app = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", oOBJ.getJco_id())

							.executeUpdate();



				



					TB_CENSUS_FAMILY_MARRIED_JCO nOBJ = new TB_CENSUS_FAMILY_MARRIED_JCO();



					nOBJ = setMarriageData(nOBJ, oOBJ);



					nOBJ.setAuthority(marriage_authority);



					nOBJ.setDate_of_authority(marriage_date_authority_com);



					nOBJ.setCreated_by(username);



					nOBJ.setCreated_date(date);



					nOBJ.setType_of_event(Integer.parseInt(marriage_type_of_event));



					nOBJ.setMarital_status(Integer.parseInt(marital_status));



					nOBJ.setDivorce_date(null);



					nOBJ.setSeparated_to_dt(null);



					nOBJ.setSeparated_from_dt(format.parse(separated_from_dt));



					nOBJ.setStatus(0);



					final_mId = (int) sessionhql.save(nOBJ);



				} else if (oOBJ.getStatus() == 1 && Integer.parseInt(marriage_type_of_event) == 1



						&& Integer.parseInt(curr_marital_status) == 13) {



				



					String hqlUpdate = "delete from TB_CENSUS_FAMILY_MARRIED_JCO where jco_id=:jco_id and status=0";



					int app = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", oOBJ.getJco_id())

							.executeUpdate();



				



					TB_CENSUS_FAMILY_MARRIED_JCO nOBJ = new TB_CENSUS_FAMILY_MARRIED_JCO();



					nOBJ = setMarriageData(nOBJ, oOBJ);



					nOBJ.setAuthority(marriage_authority);



					nOBJ.setDate_of_authority(marriage_date_authority_com);



					nOBJ.setSeparated_from_dt(oOBJ.getSeparated_from_dt());



					nOBJ.setCreated_by(username);



					nOBJ.setCreated_date(date);



					nOBJ.setType_of_event(Integer.parseInt(marriage_type_of_event));



					nOBJ.setMarital_status(Integer.parseInt(marital_status));



					nOBJ.setDivorce_date(null);



					if (separated_to_dt != null)



						nOBJ.setSeparated_to_dt(format.parse(separated_to_dt));



					else



						nOBJ.setSeparated_to_dt(null);



					nOBJ.setStatus(0);



					final_mId = (int) sessionhql.save(nOBJ);



				} else if (oOBJ.getStatus() == 1



						&& (Integer.parseInt(marriage_type_of_event) == 5

								|| Integer.parseInt(marriage_type_of_event) == 6



								|| Integer.parseInt(marriage_type_of_event) == 2)) {



					String hqlUpdate = "delete from TB_CENSUS_FAMILY_MARRIED_JCO where jco_id=:jco_id and status=0";



					int app = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", oOBJ.getJco_id())

							.executeUpdate();



					



					TB_CENSUS_FAMILY_MARRIED_JCO nOBJ = new TB_CENSUS_FAMILY_MARRIED_JCO();



					nOBJ = setMarriageData(nOBJ, oOBJ);



					nOBJ.setAuthority(marriage_authority);



					nOBJ.setDate_of_authority(marriage_date_authority_com);



					nOBJ.setCreated_by(username);



					nOBJ.setCreated_date(date);



					nOBJ.setType_of_event(Integer.parseInt(marriage_type_of_event));



					nOBJ.setMarital_status(Integer.parseInt(marital_status));



					nOBJ.setDivorce_date(divorce_dt);



					nOBJ.setStatus(0);



					final_mId = (int) sessionhql.save(nOBJ);



				}



			}



			String approoval_status = request.getParameter("app_status");



			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {



				


			} else {



				pjc.update_JcoOr_status(Integer.parseInt(jco_id),username);



			}



			boolean flag = true;



			if (final_mId != 0) {



				msg = String.valueOf(final_mId);



			} else if (final_mId == 0 && !msg.equals("update")) {



				tx.rollback();



				flag = false;



			}



			if (flag)



				tx.commit();



		} catch (RuntimeException e) {



			try {



				tx.rollback();



				msg = "0";



			} catch (RuntimeException rbe) {



				msg = "0";



			}



		} finally {



			if (sessionhql != null) {



				sessionhql.close();



			}



		}



		return msg;





	}



	@RequestMapping(value = "/admin/update_family_marriage_delete_JCOaction", method = RequestMethod.POST)



	public @ResponseBody String update_family_marriage_delete_action(ModelMap Mmap, HttpSession session,



			HttpServletRequest request) throws ParseException {



		String msg = "";



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionHQL.beginTransaction();



		int id = Integer.parseInt(request.getParameter("marr_ch_id"));



		int cur_merital_event = 0;

		if (request.getParameter("cur_merital_event") != null && !request.getParameter("cur_merital_event").equals(""))

			cur_merital_event = Integer.parseInt(request.getParameter("cur_merital_event"));



		try {

			if (cur_merital_event == 1 || cur_merital_event == 3) {

				String hqlUpdate1 = "delete from TB_CENSUS_SPOUSE_QUALIFICATION_JCO where spouse_id=:id";

				int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("id", id).executeUpdate();



				String hqlUpdate = "delete from TB_CENSUS_FAMILY_MARRIED_JCO where id=:id";



				int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();



				tx.commit();



				sessionHQL.close();



				if (app > 0) {



					msg = "1";



				} else {



					msg = "0";



				}

			} else {

				msg = "2";

			}



		} catch (Exception e) {



		}



		return msg;



	}



	@RequestMapping(value = "/admin/update_getfamily_marriageJCOData", method = RequestMethod.POST)



	public @ResponseBody List<TB_CENSUS_FAMILY_MARRIED_JCO> update_getfamily_marriageData(ModelMap Mmap,

			HttpSession session,



			HttpServletRequest request) throws ParseException {



		String msg = "";



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionHQL.beginTransaction();



		int event = Integer.parseInt(request.getParameter("marital_event"));



		int jco_id = Integer.parseInt(request.getParameter("jco_id"));



		List<TB_CENSUS_FAMILY_MARRIED_JCO> list = null;



		boolean flag = true;



		String hqlUpdate = " from TB_CENSUS_FAMILY_MARRIED_JCO where jco_id=:jco_id  ";



		if (event != 0) {



			String hqlUpdate1 = "from TB_CENSUS_FAMILY_MARRIED_JCO where jco_id=:jco_id and status=0 and type_of_event=:type_of_event";



			Query query = sessionHQL.createQuery(hqlUpdate1).setInteger("jco_id", jco_id)



					.setInteger("type_of_event", event);



			list = (List<TB_CENSUS_FAMILY_MARRIED_JCO>) query.list();



		



			if (list.size() > 0) {



				flag = false;



			} else {



				hqlUpdate += " and status=1 and (marital_status=8 OR marital_status=13)";



			}



		} else {



			hqlUpdate += " and status=0 ";



		}



		if (flag) {



			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);



			list = (List<TB_CENSUS_FAMILY_MARRIED_JCO>) query.list();



		}



		tx.commit();



		sessionHQL.close();



	



		return list;



	}



	/*--------------------- For Approval ----------------------------------*/

	@RequestMapping(value = "/admin/update_getfamily_marriageJCOData1", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_FAMILY_MARRIED_JCO> update_getfamily_marriageData1(int jco_id, int event) {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionHQL.beginTransaction();



		String hqlUpdate = " from TB_CENSUS_FAMILY_MARRIED_JCO where  jco_id=:jco_id";



//		if (event == 2) {

//

//			hqlUpdate += " and status=1 and (marital_status=8 OR marital_status=13) ";

//

//		}

//

//		else if (event == 4) {

//

//			hqlUpdate += " and status=1 and (marital_status=8) ";

//

//		}

//

//		else {



			hqlUpdate += " and status=0 ";



//		}



		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);



		@SuppressWarnings("unchecked")



		List<TB_CENSUS_FAMILY_MARRIED_JCO> list = (List<TB_CENSUS_FAMILY_MARRIED_JCO>) query.list();



		tx.commit();



		sessionHQL.close();



		return list;



	}



	public String Update_Marital_Status(TB_CENSUS_FAMILY_MARRIED_JCO obj, String username) {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionHQL.beginTransaction();






		String msg = "";



		// String msg1 = "";



		try {

			String hql3 = "update TB_CENSUS_FAMILY_MARRIED_JCO set modified_by=:modified_by,modified_date=:modified_date,separated_to_dt=:separated_to_dt "



					+ " where  jco_id=:jco_id and status = '1' and marital_status=13  ";



			Query query3 = sessionHQL.createQuery(hql3).setString("modified_by", username)



					.setTimestamp("modified_date", obj.getModified_date())



					.setTimestamp("separated_to_dt", obj.getSeparated_to_dt())



					.setInteger("jco_id", obj.getJco_id());



			msg = query3.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";



			String hql1 = "update TB_CENSUS_FAMILY_MARRIED_JCO set status=:status where  jco_id=:jco_id and "



					+ " (status != '0' and status != '-1') ";



			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)



					.setInteger("jco_id", obj.getJco_id());



			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";



			String hql = "update TB_CENSUS_FAMILY_MARRIED_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "



					+ " where  jco_id=:jco_id and status = '0' ";



			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)

					.setTimestamp("modified_date", obj.getModified_date())



					.setInteger("status", 1)



					.setInteger("jco_id", obj.getJco_id());



			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";






			tx.commit();



		}



		catch (Exception e) {



			msg = "Data Not Approve Successfully.";



			tx.rollback();



		}



		finally {



			sessionHQL.close();



		}



		return msg;



	}



	public String Update_Census_Marital(TB_CENSUS_JCO_OR_PARENT obj, String username) {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionHQL.beginTransaction();



		String msg = "";



		try {



			String hql = "update TB_CENSUS_JCO_OR_PARENT set marital_status=:marital_status "



					+ " where  id=:jco_id and status in (1,5) ";



			Query query = sessionHQL.createQuery(hql).setInteger("jco_id", obj.getId())



					.setInteger("marital_status", obj.getMarital_status());



			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";



			tx.commit();



		}



		catch (Exception e) {



			msg = "Data Not Updated.";



			tx.rollback();



		}



		finally {



			sessionHQL.close();



		}



		return msg;



	}



	/*--------------------- For REJECT ----------------------------------*/



	@RequestMapping(value = "/admin/getMaritalJCOReject", method = RequestMethod.POST)



	public @ResponseBody String getMaritalJCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,



			@RequestParam(value = "msg", required = false) String msg) throws ParseException {



		String reject_remarks = request.getParameter("reject_remarks");



		String username = session.getAttribute("username").toString();



		TB_CENSUS_FAMILY_MARRIED_JCO MrgS = new TB_CENSUS_FAMILY_MARRIED_JCO();



		MrgS.setJco_id(Integer.parseInt(request.getParameter("jco_id")));



		MrgS.setId(Integer.parseInt(request.getParameter("marr_ch_id")));

		MrgS.setReject_remarks(reject_remarks);



		/*

		 * MrgS.setType_of_event(Integer.parseInt(request.getParameter("marital_event"))

		 * );

		 */



		String msg1 = Marital_Status_Reject(MrgS, username);



		return msg1;



	}



	public String Marital_Status_Reject(TB_CENSUS_FAMILY_MARRIED_JCO obj, String username) {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionHQL.beginTransaction();



		String msg = "";



		int msg1 = 0;



		int msg2 = 0;



		try {



			String hql = "update TB_CENSUS_FAMILY_MARRIED_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "



					+ " where  jco_id=:jco_id and status = '0' and id=:id";



			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)

					.setTimestamp("modified_date", new Date())



					.setInteger("status", 3).setInteger("jco_id", obj.getJco_id())

					.setString("reject_remarks", obj.getReject_remarks())



					.setInteger("id", obj.getId());



			msg1 = query.executeUpdate() > 0 ? 1 : 0;



			String hql2 = "update TB_CENSUS_SPOUSE_QUALIFICATION_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "



					+ " where  jco_id=:jco_id and status = '0'";



			Query query2 = sessionHQL.createQuery(hql2).setString("modified_by", username)

					.setTimestamp("modified_date", new Date())



					.setInteger("status", 3).setInteger("jco_id", obj.getJco_id());



			msg2 = query2.executeUpdate() > 0 ? 1 : 0;

			if (msg1 > 0 || msg2 > 0) {

				msg = "1";

			}





			tx.commit();



		} catch (Exception e) {



			msg = "Data Not Rejected.";



			tx.rollback();



		}



		finally {



			sessionHQL.close();



		}



		return msg;



	}



	public @ResponseBody List<TB_CENSUS_FAMILY_MARRIED_JCO> update_getfamily_marriageData2(int jco_id, int event) {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionHQL.beginTransaction();



		String hqlUpdate = " from TB_CENSUS_FAMILY_MARRIED_JCO where  jco_id=:jco_id";



//		if (event == 2) {

//

//			hqlUpdate += " and status=1 and (marital_status=8 OR marital_status=13) ";

//

//		}

//

//		else if (event == 4) {

//

//			hqlUpdate += " and status=1 and (marital_status=8) ";

//

//		}

//

//		else {



			hqlUpdate += " and status='3' ";



//		}



		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);



		@SuppressWarnings("unchecked")



		List<TB_CENSUS_FAMILY_MARRIED_JCO> list = (List<TB_CENSUS_FAMILY_MARRIED_JCO>) query.list();



		tx.commit();



		sessionHQL.close();



		return list;



	}



	@RequestMapping(value = "/admin/update_getfamily_marriageDataJCO3", method = RequestMethod.POST)



	public @ResponseBody List<TB_CENSUS_FAMILY_MARRIED_JCO> update_getfamily_marriageData3(ModelMap Mmap,

			HttpSession session,



			HttpServletRequest request) throws ParseException {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionHQL.beginTransaction();



		int event = Integer.parseInt(request.getParameter("marital_event"));



		int jco_id = Integer.parseInt(request.getParameter("jco_id"));



		String hqlUpdate = " from TB_CENSUS_FAMILY_MARRIED_JCO where  jco_id=:jco_id";

//

//		if (event == 2) {

//

//			hqlUpdate += " and status=1 and (marital_status=8 OR marital_status=13) ";

//

//		}

//

//		else if (event == 4) {

//

//			hqlUpdate += " and status=1 and (marital_status=8) ";

//

//		}

//

//		else {



			hqlUpdate += " and status='3' ";



//		}



		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);



		List<TB_CENSUS_FAMILY_MARRIED_JCO> list = (List<TB_CENSUS_FAMILY_MARRIED_JCO>) query.list();



		tx.commit();



		sessionHQL.close();



		

		return list;



	}



	///// Spouse



	@RequestMapping(value = "/admin/getSpouseQualificationDataJCO3", method = RequestMethod.POST)



	public @ResponseBody List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> getSpouseQualificationData3(ModelMap Mmap,

			HttpSession session,



			HttpServletRequest request) throws ParseException {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionHQL.beginTransaction();



		int id = Integer.parseInt(request.getParameter("q_id"));



		int jco_id = Integer.parseInt(request.getParameter("jco_id"));



		String hqlUpdate = " from TB_CENSUS_SPOUSE_QUALIFICATION_JCO where  status='3' and jco_id=:jco_id ";



		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);



		List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> list = (List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO>) query.list();



		tx.commit();



		sessionHQL.close();



		return list;



	}



	public @ResponseBody List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> update_census_Spouse(int jco_id) {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionHQL.beginTransaction();



		String hqlUpdate = " from TB_CENSUS_SPOUSE_QUALIFICATION_JCO where  jco_id=:jco_id and status='0'";



		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);



		@SuppressWarnings("unchecked")



		List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> list = (List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO>) query.list();



		tx.commit();



		sessionHQL.close();



		return list;



	}



	public String Update_SposeQuali_Details(TB_CENSUS_SPOUSE_QUALIFICATION_JCO obj, String username) {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionHQL.beginTransaction();



		String msg = "";



		// String msg1 = "";



		try {



			String hql1 = "update TB_CENSUS_SPOUSE_QUALIFICATION_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "



					+ " where  jco_id=:jco_id and status = '1'";



			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)



					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 2)



					.setInteger("jco_id", obj.getJco_id());



			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			

			String hql = "update TB_CENSUS_SPOUSE_QUALIFICATION_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "



					+ " where  jco_id=:jco_id and status = '0'";



			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)



					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)



					.setInteger("jco_id", obj.getJco_id());



			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";



			tx.commit();



		} catch (Exception e) {



			msg = "Data Not Approve Successfully.";



			tx.rollback();



		} finally {



			sessionHQL.close();



		}



		return msg;



	}



	@RequestMapping(value = "/admin/spouse_qualification_JCOaction", method = RequestMethod.POST)

	public @ResponseBody String spouse_qualification_JCOaction(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		int currentYear = date.getYear() + 1900;

		String username = session.getAttribute("username").toString();

		String type = request.getParameter("type");

		String examination_pass = request.getParameter("examination_pass");

		String passing_year = request.getParameter("passing_year");

		String div_class = request.getParameter("div_class");

		String subject = request.getParameter("subject");

		String institute = request.getParameter("institute");

		String degree = request.getParameter("degree");

		String specialization = request.getParameter("specialization");

		String exam_other_qua = request.getParameter("exam_other_qua");

		String class_other_qua = request.getParameter("class_other_qua");

		String degree_other = request.getParameter("degree_other");

		String spec_other = request.getParameter("spec_other");

		String qualification_ch_id = request.getParameter("qualification_ch_id");



		String jco_id = request.getParameter("jco_id");

		String dateofBirthyear = request.getParameter("dateofBirthyear");

		String spouse_id = request.getParameter("spouse_id");

		String rvalue = "";

		

		if (exam_other_qua.trim().equals("")) {

			exam_other_qua = null;

		}

		if (class_other_qua.trim().equals("")) {

			class_other_qua = null;

		}

		if (degree_other.trim().equals("")) {

			degree_other = null;

		}

		if (spec_other.trim().equals("")) {

			spec_other = null;

		}

		if (type == null || type.equals("0")) {

			return "Please Select The Type";

		}

		if (examination_pass == null || examination_pass.equals("0")) {

			return "Please Select The Examination Passed";

		}

		if (degree == null || degree.equals("0")) {

			return "Please Select The Degree Acquried";

		}

		if (specialization == null || specialization.equals("0")) {

			return "Please Select The Specialization";

		}



		try {



			if (spouse_id == null || spouse_id.equals("") || spouse_id.equals("0")) {



				String hql_ = "FROM TB_CENSUS_FAMILY_MARRIED_JCO where jco_id=:jco_id and status=1 and marital_status=8";



				Query query_ = sessionhql.createQuery(hql_);



				query_.setInteger("jco_id", Integer.parseInt(jco_id));



				List<TB_CENSUS_FAMILY_MARRIED_JCO> results = query_.list();



				if (results.size() > 0) {



					spouse_id = String.valueOf(results.get(0).getId());



					dateofBirthyear = String.valueOf(results.get(0).getDate_of_birth().getYear() + 1900);



				} else {



					return "Somthing Went Wrong";



				}



			}



		} catch (Exception e) {



			// TODO: handle exception



		}



		if (passing_year == null || passing_year.trim().equals("")) {

			return "Please Enter The Year of passing";

		}

		if (!passing_year.trim().equals("")) {

			if (Integer.parseInt(passing_year) <= Integer.parseInt(dateofBirthyear)

					|| Integer.parseInt(passing_year) > currentYear) {

				return "Please Enter The Valid Year of Passing";

			}

		}

		if (div_class == null || div_class.equals("0")) {

			return "Please Select The Div/Grade/PCT(%)";

		}

		if (subject == null || subject.trim().equals("")) {

			return "Please Select The Subjects";

		}

		if (institute == null || institute.trim().equals("")) {

			return "Please Enter The Institute & place";

		}

		TB_CENSUS_SPOUSE_QUALIFICATION_JCO q = new TB_CENSUS_SPOUSE_QUALIFICATION_JCO();

		try {

			if (Integer.parseInt(qualification_ch_id) == 0) {

		

				q.setType(Integer.parseInt(type));

				if (examination_pass != null && !examination_pass.equals("0"))

					q.setExamination_pass(Integer.parseInt(examination_pass));

				if (degree != null && !degree.equals("0"))

					q.setDegree(Integer.parseInt(degree));

				if (specialization != null && !specialization.equals("0"))

					q.setSpecialization(Integer.parseInt(specialization));

				q.setPassing_year(Integer.parseInt(passing_year));

				q.setDiv_class(div_class);

				q.setSubject(subject);

				q.setInstitute(institute);



				q.setJco_id(Integer.parseInt(jco_id));

				q.setSpouse_id(Integer.parseInt(spouse_id));

				q.setCreated_by(username);

				q.setCreated_date(date);

				q.setExam_other(exam_other_qua);

				q.setClass_other(class_other_qua);

				q.setDegree_other(degree_other);

				q.setSpecialization_other(spec_other);

				q.setInitiated_from("u");

				int id = (int) sessionhql.save(q);

				rvalue = Integer.toString(id);

			

			} else {

				String hql = "update TB_CENSUS_SPOUSE_QUALIFICATION_JCO set modified_by=:modify_by ,modified_date=:modify_on ,type=:type,"

						+ " passing_year=:passing_year,div_class=:div_class,subject=:subject,institute=:institute";

				hql += ",examination_pass=:examination_pass,exam_other=:exam_other,class_other=:class_other ,specialization_other=:specialization_other ,degree_other=:degree_other ";

				hql += ",specialization=:specialization,degree=:degree,status=0 ";

				hql += " where  id=:id";

				Query query = sessionhql.createQuery(hql).setInteger("type", Integer.parseInt(type))

						.setInteger("passing_year", Integer.parseInt(passing_year)).setString("div_class", div_class)

						.setString("subject", subject).setString("institute", institute)

						.setInteger("id", Integer.parseInt(qualification_ch_id)).setString("modify_by", username)

						.setTimestamp("modify_on", date).setString("exam_other", exam_other_qua)

						.setString("class_other", class_other_qua).setString("specialization_other", spec_other)

						.setString("degree_other", degree_other);

				if (examination_pass != null && !examination_pass.equals("0"))

					query.setInteger("examination_pass", Integer.parseInt(examination_pass));

				if (degree != null && !degree.equals("0"))

					query.setInteger("degree", Integer.parseInt(degree));

				if (specialization != null && !specialization.equals("0"))

					query.setInteger("specialization", Integer.parseInt(specialization));

				rvalue = query.executeUpdate() > 0 ? "Data Updated Successfully" : "Data Not Updated";

			}

			

			String approoval_status = request.getParameter("app_status");



			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {



			



			} else {



				pjc.update_JcoOr_status(Integer.parseInt(jco_id),username);



			}

			

			tx.commit();

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				rvalue = "Data Not Updated";

			} catch (RuntimeException rbe) {

				rvalue = "Data Not Updated";

			}

		} finally {

			if (sessionhql != null) {

				sessionhql.close();

			}

		}

		return rvalue;

	}



	@RequestMapping(value = "/admin/getSpouseQualificationJCOData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> getSpouseQualificationJCOData(int jco_id,

			int app_status)  {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_CENSUS_SPOUSE_QUALIFICATION_JCO where jco_id=:jco_id and status=:status";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setInteger("status", app_status);

		List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> list = (List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}



	@RequestMapping(value = "/admin/updated_family_marriage_delete_JCOaction", method = RequestMethod.POST)



	public @ResponseBody String updated_family_marriage_delete_JCOaction(ModelMap Mmap, HttpSession session,



			HttpServletRequest request) throws ParseException {



		String msg = "";



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionHQL.beginTransaction();



		int id = Integer.parseInt(request.getParameter("jco_id"));



		String quali_delete = request.getParameter("qali_status");



		try {



			String hqlUpdate = "delete from TB_CENSUS_SPOUSE_QUALIFICATION_JCO where status in (3,0) and jco_id=:id";



			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();



			int papp = 0;

			if (quali_delete == null || quali_delete.equals("")) {

				hqlUpdate = "delete from TB_CENSUS_FAMILY_MARRIED_JCO where status in (3,0) and jco_id=:id";

				papp = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();

			}



			tx.commit();



			if (papp > 0 || app > 0) {



				msg = "1";



			} else {



				msg = "0";



			}



		} catch (Exception e) {



			tx.rollback();



		} finally {



			sessionHQL.close();



		}



		return msg;



	}

	

	public TB_CENSUS_FAMILY_MARRIED_JCO setMarriageData(TB_CENSUS_FAMILY_MARRIED_JCO newOBJ, TB_CENSUS_FAMILY_MARRIED_JCO oldOBJ) {



		newOBJ.setDate_of_birth(oldOBJ.getDate_of_birth());



		newOBJ.setMaiden_name(oldOBJ.getMaiden_name());



		newOBJ.setMarriage_date(oldOBJ.getMarriage_date());



		newOBJ.setPlace_of_birth(oldOBJ.getPlace_of_birth());



		newOBJ.setNationality(oldOBJ.getNationality());



		newOBJ.setAdhar_number(oldOBJ.getAdhar_number());



		newOBJ.setPan_card(oldOBJ.getPan_card());



		newOBJ.setIf_spouse_ser(oldOBJ.getIf_spouse_ser());



		newOBJ.setSpouse_service(oldOBJ.getSpouse_service());



		newOBJ.setOther_spouse_ser(oldOBJ.getOther_spouse_ser());



		newOBJ.setSpouse_personal_no(oldOBJ.getSpouse_personal_no());



		newOBJ.setOther_nationality(oldOBJ.getOther_nationality());



		



		newOBJ.setJco_id(oldOBJ.getJco_id());



		newOBJ.setStatus(0);

		newOBJ.setInitiated_from("u");

		return newOBJ;



	}

}

