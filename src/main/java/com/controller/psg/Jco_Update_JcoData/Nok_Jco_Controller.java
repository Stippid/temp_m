package com.controller.psg.Jco_Update_JcoData;

import java.math.BigInteger;

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

import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Jco_Update_JcoData.TB_NOK_JCO;
import com.models.psg.Transaction.TB_CENSUS_ADDRESS;

import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;

import com.models.psg.Transaction.TB_CENSUS_FOREIGN_COUNTRY;

import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;

import com.persistance.util.HibernateUtil;

@Controller

@RequestMapping(value = { "admin", "/", "user" })

public class Nok_Jco_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();

	private psg_jco_CommonController pjc = new psg_jco_CommonController();

	
	ValidationController validation = new ValidationController();
	/////////// ********************************change

	/////////// address*****************************

	// ************************************START NOK

	// DETAIL*******************************************

	/*
	 * 
	 * @RequestMapping(value = "/admin/NokUrl", method = RequestMethod.GET) public
	 * 
	 * ModelAndView NokUrl(ModelMap Mmap, HttpSession session,
	 * 
	 * 
	 * 
	 * @RequestParam(value = "msg", required = false) String msg, HttpServletRequest
	 * 
	 * request) { Mmap.put("getRelationList", mcommon.getRelationList());
	 * 
	 * Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));
	 * 
	 * Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
	 * 
	 * Mmap.put("getMedCountryName", mcommon.getMedCountryName("", session));
	 * 
	 * Mmap.put("getVillageList", mcommon.getVillageList());
	 * 
	 * Mmap.put("getTehsilList", mcommon.getTehsilList());
	 * 
	 * Mmap.put("getMedCityName", mcommon.getMedCityName("", session));
	 * 
	 * Mmap.put("msg", msg); return new ModelAndView("nokTiles"); }
	 * 
	 */

	@RequestMapping(value = "/admin/nok_details_action_jco", method = RequestMethod.POST)

	public @ResponseBody String nok_details_action_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request)

			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		String authority = request.getParameter("authority");

		String date_authority = request.getParameter("date_authority");

		String nok_name = request.getParameter("nok_name");

		String nok_relation = request.getParameter("nok_relation");

		String nok_village = request.getParameter("nok_village");

		String nok_country = request.getParameter("nok_country");

		String nok_tehsil = request.getParameter("nok_tehsil");

		String nok_district = request.getParameter("nok_district");

		String ctry_other = request.getParameter("ctry_other");

		String st_other = request.getParameter("st_other");

		String dist_other = request.getParameter("dist_other");

		String tehsil_other = request.getParameter("nok_tehsil_other");

		String nok_state = request.getParameter("nok_state");

		String nok_pin = request.getParameter("nok_pin");

		String nok_rail = request.getParameter("nok_rail");

		String nok_id = request.getParameter("nok_id");

		String jco_id = request.getParameter("jco_id");
		
		String nok_country_text = request.getParameter("nok_country_text");
		
		/*BigInteger nok_mobile_no = BigInteger.ZERO;

		if (!request.getParameter("nok_mobile_no").equals("")) {

			nok_mobile_no = new BigInteger(request.getParameter("nok_mobile_no"));

		}*/

		String nok_mobile_no = request.getParameter("nok_mobile_no");
		String nok_rural_urban = request.getParameter("nok_rural_urban");

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		String msg = "";

		Date auth_dt = null;

//		if (authority == null || authority.equals("")) {

//			return "Please Enter the Authority";

//		}

//

//		if (date_authority == null || date_authority.equals("null") || date_authority.equals("DD/MM/YYYY") || date_authority.equals("")) {

//			return "Please Select Date of Authority";

//		}

		Date enroll_date = format.parse(request.getParameter("enroll_date"));
		
		if (authority == null || authority.equals("")) {
			return "Please Enter Authority";
		}
		if (!validation.isValidAuth(authority)) {
	 		return validation.isValidAuthMSG + " Authority";	
		}
 		if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority " + validation.isValidLengthMSG;	
		}
		
 		if (date_authority == null || date_authority.equals("null") || date_authority.equals("DD/MM/YYYY") || date_authority.equals("")) {
			msg = "Please Select Date of Authority ";
			return msg;
		}
		else if (!validation.isValidDate(date_authority)) {
 			return validation.isValidDateMSG + " of Authority";	
		}	
		else {
			auth_dt = format.parse(date_authority);
		}			
		if (mcommon.CompareDate(auth_dt,enroll_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
		}

		if (nok_name == null || nok_name.equals("")) {

			return "Please Enter the Nok Name";

		}
		if (!validation.isOnlyAlphabet(nok_name)) {
	 		return validation.isOnlyAlphabetMSG + " Nok Name";	
		}
		if (!validation.isvalidLength(nok_name,validation.nameMax,validation.nameMin)) {
	 		return "Nok Name " + validation.isValidLengthMSG;	
		}
		if (nok_relation == null || nok_relation.equals("")) {

			return "Please Enter the Nok Relation";

		}

		if (nok_village == null || nok_village.equals("")) {

			return "Please Select the Village/Town/City";

		}
		if (!validation.isOnlyAlphabet(nok_village)) {
	 		return validation.isOnlyAlphabetMSG + " Village/Town/City";	
		}
		if (!validation.isvalidLength(nok_village,validation.nameMax,validation.nameMin)) {
	 		return "Village/Town/City " + validation.isValidLengthMSG;	
		}

		if(!nok_country_text.equals("BHUTAN")) {
			if (nok_tehsil == null || nok_tehsil.equals("0")) {
	
				return "Please Select the Tehsil";
	
			}
		}else {
			nok_tehsil="0";
			tehsil_other="";
		}

		if (nok_district == null || nok_district.equals("0")) {

			return "Please Select the District";

		}

		if (nok_state == null || nok_state.equals("0")) {

			return "Please Select the State";

		}

		if (nok_pin == null || nok_pin.equals("")) {

			return "Please Enter the Pin";

		}
		if (validation.isOnlyNumer(nok_pin) == true) {
	 		return validation.isOnlyNumerMSG + " Pin";	
		}
		if (!validation.isvalidLength(nok_pin,validation.pin_noMax,validation.pin_noMin)) {
	 		return "Pin " + validation.isValidLengthMSG;	
		}
		if (nok_rail == null || nok_rail.equals("")) {

			return "Please Enter the Nearest Railway Station";

		}
		if (!validation.isOnlyAlphabetNumeric(nok_rail)) {
	 		return validation.isOnlyAlphabetNumericMSG + " Nearest Railway Station";	
		}
		if (!validation.isvalidLength(nok_rail,validation.authorityMax,validation.authorityMin)) {
	 		return "Nearest Railway Station " + validation.isValidLengthMSG;	
		}
		try {

			if (Integer.parseInt(nok_id) == 0) {

				TB_NOK_JCO addr = new TB_NOK_JCO();

				addr.setAuthority(authority);

				addr.setDate_authority(auth_dt);

				addr.setNok_name(nok_name);

				addr.setNok_relation(Integer.parseInt(nok_relation));

				addr.setNok_village(nok_village);

				addr.setNok_country(Integer.parseInt(nok_country));

				addr.setNok_tehsil(Integer.parseInt(nok_tehsil));

				addr.setNok_district(Integer.parseInt(nok_district));

				addr.setNok_state(Integer.parseInt(nok_state));

				addr.setNok_pin(Integer.parseInt(nok_pin));

				addr.setNok_near_railway_station(nok_rail);

				addr.setNok_rural_urban_semi(nok_rural_urban);

				addr.setCtry_other(ctry_other);

				addr.setSt_other(st_other);

				addr.setDist_other(dist_other);

				addr.setTehsil_other(tehsil_other);

				addr.setNok_mobile_no(nok_mobile_no);

				addr.setJco_id(Integer.parseInt(jco_id));

				addr.setCreated_by(username);

				addr.setCreated_date(new Date());

				addr.setStatus(0);
				addr.setInitiated_from("u");
				int id = (int) sessionhql.save(addr);

				msg = Integer.toString(id);

			} else {

				/*--S---REJECT----*/

				/*
				 * TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				 * 
				 * CC.setUpdate_ofr_status(0);
				 * 
				 * sessionhql.save(CC);
				 */

				/*---E--REJECT----*/

				String hql = " update TB_NOK_JCO set authority=:authority ,jco_id=:jco_id ,date_authority=:date_authority,"

						+ "nok_name=:nok_name,nok_relation=:nok_relation,nok_village=:nok_village,nok_country=:nok_country,"

						+ "nok_tehsil=:nok_tehsil ,nok_district=:nok_district ,nok_state=:nok_state,"

						+ "ctry_other=:ctry_other,"

						+ "st_other=:st_other ,dist_other=:dist_other ,tehsil_other=:tehsil_other,"

						+ "nok_pin=:nok_pin,nok_near_railway_station=:nok_near_railway_station,"

						+ "nok_rural_urban_semi=:nok_rural_urban_semi,nok_mobile_no=pgp_sym_encrypt(:nok_mobile_no,current_setting('miso.version')),"

						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status" + " where  id=:id";

				Query query = sessionhql.createQuery(hql).setTimestamp("date_authority", auth_dt)

						.setString("authority", authority)

						.setInteger("jco_id", Integer.parseInt(request.getParameter("jco_id")))

						.setString("nok_name", nok_name).setInteger("nok_relation", Integer.parseInt(nok_relation))

						.setInteger("nok_country", Integer.parseInt(nok_country))

						.setInteger("nok_state", Integer.parseInt(nok_state))

						.setString("ctry_other", ctry_other)

						.setString("st_other", st_other)

						.setString("dist_other", dist_other)

						.setString("tehsil_other", tehsil_other)

						.setInteger("nok_district", Integer.parseInt(nok_district))

						.setString("nok_village", nok_village).setInteger("nok_tehsil", Integer.parseInt(nok_tehsil))

						.setString("nok_mobile_no", nok_mobile_no).setInteger("nok_pin", Integer.parseInt(nok_pin))

						.setString("nok_near_railway_station", nok_rail)

						.setString("nok_rural_urban_semi", nok_rural_urban)

						.setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0)

						.setInteger("id", Integer.parseInt(request.getParameter("nok_id")));

				msg = query.executeUpdate() > 0 ? "update" : "0";

				// Mmap.put("msg", "Data Updated Successfully");

			}

			String approoval_status = request.getParameter("app_status");

			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {

		

			}

			else

			{

				pjc.update_JcoOr_status(Integer.parseInt(jco_id), username);

			}

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

	@RequestMapping(value = "/admin/nok_details_GetData_jco", method = RequestMethod.POST)

	public @ResponseBody List<TB_NOK_JCO> nok_details_GetData_jco(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_NOK_JCO where  jco_id=:jco_id and  status = '0'";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		List<TB_NOK_JCO> list = (List<TB_NOK_JCO>) query.list();

	

		tx.commit();

		sessionHQL.close();

		

		return list;

	}

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_NOK_JCO> nok_details_GetData1(int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_NOK_JCO where  jco_id=:jco_id and  status = '0'";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		@SuppressWarnings("unchecked")

		List<TB_NOK_JCO> list = (List<TB_NOK_JCO>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	public String Update_NOK(TB_NOK_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		// String msg1 = "";

		try {

			String hql1 = "update TB_NOK_JCO set status=:status where jco_id=:jco_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)

					.setInteger("jco_id", obj.getJco_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_NOK_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "

					+ " where jco_id=:jco_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql)

					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())

					.setInteger("status", 1)

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

	/*--------------------- For REJECT ----------------------------------*/

	@RequestMapping(value = "/admin/getNOKReject_jco", method = RequestMethod.POST)

	public @ResponseBody String getNOKReject_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request,

			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		
		String reject_remarks = request.getParameter("reject_remarks");

		
		String username = session.getAttribute("username").toString();

		TB_NOK_JCO NOKco = new TB_NOK_JCO();

		NOKco.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

		NOKco.setId(Integer.parseInt(request.getParameter("nok_id")));

		NOKco.setReject_remarks(reject_remarks);
		
		String msg1 = NOK_Reject(NOKco, username);

		return msg1;

	}

	public String NOK_Reject(TB_NOK_JCO obj, String username) {


		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		String msg1 = "";

		try {



			String hql = "update TB_NOK_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "

					+ " where  jco_id=:jco_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)

					.setTimestamp("modified_date", new Date()).setInteger("status", 3)

					.setInteger("jco_id", obj.getJco_id()).setString("reject_remarks", obj.getReject_remarks())

					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "1" : "0";

			tx.commit();

		} catch (Exception e) {

			msg = "Data Not Rejected.";

			tx.rollback();

		} finally {

			sessionHQL.close();

		}

		return msg;

	}

	public @ResponseBody List<TB_NOK_JCO> nok_details_GetData2( int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_NOK_JCO where  jco_id=:jco_id and  status = '3'";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		@SuppressWarnings("unchecked")

		List<TB_NOK_JCO> list = (List<TB_NOK_JCO>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	@RequestMapping(value = "/admin/nok_details_GetData3_jco", method = RequestMethod.POST)

	public @ResponseBody List<TB_NOK_JCO> nok_details_GetData3_jco(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_NOK_JCO where  jco_id=:jco_id and  status = '3'";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		List<TB_NOK_JCO> list = (List<TB_NOK_JCO>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	@RequestMapping(value = "/admin/NOK_delete_action_jco", method = RequestMethod.POST)
	public @ResponseBody String NOK_delete_action_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_NOK_JCO where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();
			if (app > 0) {
				msg = "1";
			} else {
				msg = "0";
			}
		} catch (Exception e) {
		}
		return msg;
	}

}
