package com.controller.psg.Jco_Update_JcoData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.psg.update_census_data.Search_UpdateOffDataDao;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_ADDRESS_JCO;
import com.models.psg.Jco_Update_JcoData.TB_NON_EFFECTIVE_JCO;

import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_CENSUS_BATT_PHY_CASUALITY;

import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Non_Effective_JCOController {

	@Autowired
	Search_UpdateOffDataDao UOD;
	
	
	private Psg_CommonController com = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	ValidationController validation = new ValidationController();
	// ====================================save/edit================================//

	@RequestMapping(value = "/admin/non_effectiveJCOAction", method = RequestMethod.POST)
	public @ResponseBody  Map<String, String> non_effectiveJCOAction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		Map<String, String> data = new HashMap<>();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();
		Date date_of_authority_non_ef = null;
		Date date_of_non_effective = null;
		String username = session.getAttribute("username").toString();

          Date enroll_date = format.parse(request.getParameter("enroll_date"));

		String non_ef_authority = request.getParameter("non_ef_authority");
		
		String date_of_authority = request.getParameter("date_of_authority_non_ef");
		String date_of_non_ef= request.getParameter("date_of_non_effective");
			if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
			date_of_authority_non_ef = format.parse(date_of_authority);
		}
		 
		String cause_of_non_effective = request.getParameter("cause_of_non_effective");
			if (!date_of_non_ef.equals("") && !date_of_non_ef.equals("DD/MM/YYYY")) {
			date_of_non_effective = format.parse(date_of_non_ef);
		}
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));


		int nf_id = Integer.parseInt(request.getParameter("nf_id"));
		int non_add_id = Integer.parseInt(request.getParameter("non_addr_ch_id"));
		
		String msg = "";

		String per_home_addr_country = request.getParameter("per_home_addr_country");
		String per_home_addr_village = request.getParameter("per_home_addr_village");
		String per_home_addr_tehsil = request.getParameter("per_home_addr_tehsil");
		String per_home_addr_district = request.getParameter("per_home_addr_district");
		String per_home_addr_state = request.getParameter("per_home_addr_state");
		String per_home_addr_pin = request.getParameter("per_home_addr_pin");
		String per_home_addr_rail = request.getParameter("per_home_addr_rail");
		String per_home_addr_rural_urban = request.getParameter("per_home_addr_rural_urban");
		String border_area = request.getParameter("border_area");
		
		String per_home_addr_country_other = request.getParameter("per_home_addr_country_other");
		String per_home_addr_state_other = request.getParameter("per_home_addr_state_other");
		String per_home_addr_district_other = request.getParameter("per_home_addr_district_other");
		String per_home_addr_tehsil_other = request.getParameter("per_home_addr_tehsil_other");
		String perm_home_addr_country_text = request.getParameter("perm_home_addr_country_text");
		
		if (non_ef_authority == null || non_ef_authority.equals("") || non_ef_authority == "") {
			
			data.put("error", "Please Enter Authority");
			return data;
		}
		if (!validation.isValidAuth(non_ef_authority)) {
			data.put("error", validation.isValidAuthMSG + " Authority");
			return data;
		}
 		if (!validation.isvalidLength(non_ef_authority,validation.authorityMax,validation.authorityMin)) {
 			data.put("error", "Authority " + validation.isValidLengthMSG);	
 			return data;
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
			data.put("error", "Please Select Date of Authority");
			return data;
		}
	      if (!validation.isValidDate(date_of_authority)) {
	    	  data.put("error",  validation.isValidDateMSG + " of Authority");	
	    	  return data;
		}
		if (com.CompareDate(format.parse(date_of_authority),enroll_date) == 0) {
			data.put("error", "Authority Date should be Greater than Commission Date");
			return data;
		}
		 if (!validation.isValidDate(date_of_authority)) {
	    	  data.put("error",  validation.isValidDateMSG + " of Authority");	
	    	  return data;
		}
		
		if (cause_of_non_effective == null || cause_of_non_effective.equals("0") || cause_of_non_effective == "0") {
			data.put("error", "Please Select Cause of Non Effective");
			return data;
		}
		
		 if (date_of_non_ef == null || date_of_non_ef.equals("null") || date_of_non_ef.equals("DD/MM/YYYY") || date_of_non_ef.equals("")) {
			data.put("error", "Please Select Date of Non Effective ");
			return data;
		}
		 if (!validation.isValidDate(date_of_non_ef)) {
	    	  data.put("error",  validation.isValidDateMSG + " Non Effective ");	
	    	  return data;
		}
		
		if (per_home_addr_country == null || per_home_addr_country.equals("0") || per_home_addr_country == "0") {
			data.put("error", "Please select The Country");
			return data;
		}
		
		if (per_home_addr_state == null || per_home_addr_state.equals("0") || per_home_addr_state == "0") {
			data.put("error", "Please Select State");
			return data;
		}
		
		if (per_home_addr_district == null || per_home_addr_district.equals("0") || per_home_addr_district =="0") {
			data.put("error", "Please Select the District");
			return data;
		}
		if(!perm_home_addr_country_text.equals("BHUTAN")) {
			if (per_home_addr_tehsil == null || per_home_addr_tehsil.equals("0") || per_home_addr_tehsil =="0") {
				data.put("error", "Please Select the Tehsil");
				return data;
			}
		}
		else {
			per_home_addr_tehsil="0";
			per_home_addr_tehsil_other="";
		}
		if (per_home_addr_village == null || per_home_addr_village.equals("") || per_home_addr_village == "") {
			data.put("error", "Please Enter the Village/Town/City");
			return data;
		}
		if (!validation.isOnlyAlphabet(per_home_addr_village)) {
			data.put("error",  validation.isOnlyAlphabetMSG + " Village/Town/City");	
			return data;
		}
		if (!validation.isvalidLength(per_home_addr_village,validation.nameMax,validation.nameMin)) {
			data.put("error", "Village/Town/City " + validation.isValidLengthMSG);	
			return data;
		}
		if (per_home_addr_pin == null || per_home_addr_pin.equals("") || per_home_addr_pin == "") {
			data.put("error", "Please Enter the Pin");
			return data;
		}
		if (validation.isOnlyNumer(per_home_addr_pin) == true) {
			data.put("error",  validation.isOnlyNumerMSG + " Pin");	
			return data;
		}
		if (!validation.isvalidLength(per_home_addr_pin,validation.pin_noMax,validation.pin_noMin)) {
			data.put("error",  "Pin " + validation.isValidLengthMSG);	
			return data;
		}
		if (per_home_addr_rail == null || per_home_addr_rail.equals("")) {
			data.put("error", "Please Enter the Nearest Railway Station");
			return data;
		}
		if (!validation.isOnlyAlphabetNumeric(per_home_addr_rail)) {
			data.put("error", validation.isOnlyAlphabetNumericMSG + " Nearest Railway Station");
			return data;
		}
		if (!validation.isvalidLength(per_home_addr_rail,validation.authorityMax,validation.authorityMin)) {
			data.put("error", "Nearest Railway Station " + validation.isValidLengthMSG);	
			return data;
		}
		if (per_home_addr_rural_urban == null || per_home_addr_rural_urban.equals("") || per_home_addr_rural_urban =="") {
			data.put("error", "Please Select Is  Rural /Urban/Semi Urban");
			return data;
		}
		if (border_area == null || border_area.equals("") || border_area =="") {
			data.put("error", "Please Select Border Area");
			return data;
		}
		

		try {

			// for battle_physical_casuality validation
//			Query qnon = sessionhql.createQuery(
//					"from TB_CENSUS_BATT_PHY_CASUALITY where  jco_id=:jco_id and status=0 order by id");
//			qnon.setParameter("census_id", census_id).setParameter("jco_id", jco_id);
//			List<TB_CENSUS_BATT_PHY_CASUALITY> non_elist = (List<TB_CENSUS_BATT_PHY_CASUALITY>) qnon.list();
//			int cause = 0;
//			if (non_elist.size() > 0) {
//				cause = Integer.parseInt(non_elist.get(0).getNature_of_casuality());
//			}

			if (nf_id == 0) {
				List<TB_CENSUS_ADDRESS_JCO> addr2= getPerAddressDataStatus1(session,request);
				if(addr2.size()==0) {
					data.put("error", "Approved Data of Address Doesn't Exits So Please Fill Up The Address Data And Approve It");
					return data;
				}
				Change_AddressJcoController add_c=new Change_AddressJcoController();
				List<TB_CENSUS_ADDRESS_JCO> add_pen=add_c.address_details_GetData(session,request); 
				if(add_pen.size()>0) {
					data.put("error", "Please, First Approve The Pending Data of Address");
					return data;
				}
				TB_NON_EFFECTIVE_JCO non = new TB_NON_EFFECTIVE_JCO();
				non.setCreated_by(username);
				non.setCreated_date(date);
				non.setNon_ef_authority(non_ef_authority);
				non.setDate_of_authority_non_ef(date_of_authority_non_ef);
				non.setCause_of_non_effective(cause_of_non_effective);
				non.setDate_of_non_effective(date_of_non_effective);
				non.setJco_id(jco_id);
			
				non.setStatus(0);
				non.setCreated_by(username);
				non.setCreated_date(date);
				non.setInitiated_from("u");
				int id = (int) sessionhql.save(non);
				msg = Integer.toString(id);
				data.put("nf_id", msg);

				/*
				 * if(non_add_id == 0) {
				 * 
				 * }
				 */

				if(non_add_id == 0) {
//					List<TB_CENSUS_ADDRESS_JCO> addr2= getPerAddressDataStatus1(session,request);
					TB_CENSUS_ADDRESS_JCO addr = new TB_CENSUS_ADDRESS_JCO();
					addr.setAuthority(addr2.get(0).getAuthority());
					addr.setDate_authority(addr2.get(0).getDate_authority());
					addr.setPre_state(addr2.get(0).getPre_state());
					addr.setPre_tesil(addr2.get(0).getPre_tesil());
					addr.setPre_country(addr2.get(0).getPre_country());
					addr.setPre_district(addr2.get(0).getPre_district());
					addr.setPresent_state(addr2.get(0).getPresent_state());
					addr.setPresent_country(addr2.get(0).getPresent_country());
					addr.setPresent_district(addr2.get(0).getPresent_district());
					addr.setPresent_village(addr2.get(0).getPresent_village());
					addr.setPresent_pin_code(addr2.get(0).getPresent_pin_code());
					addr.setPresent_near_railway_station(addr2.get(0).getPresent_near_railway_station());
					addr.setPresent_rural_urban_semi(addr2.get(0).getPresent_rural_urban_semi());
					addr.setPresent_tehsil(addr2.get(0).getPresent_tehsil());
					addr.setPermanent_country(Integer.parseInt(per_home_addr_country));
					addr.setPermanent_district(Integer.parseInt(per_home_addr_district));
					addr.setPermanent_state(Integer.parseInt(per_home_addr_state));
					addr.setPermanent_tehsil(Integer.parseInt(per_home_addr_tehsil));
					addr.setPermanent_village(per_home_addr_village);
					addr.setPermanent_pin_code(Integer.parseInt(per_home_addr_pin));
					addr.setPermanent_near_railway_station(per_home_addr_rail);
					addr.setPermanent_rural_urban_semi(per_home_addr_rural_urban);
					addr.setPermanent_border_area(border_area);
					addr.setJco_id(jco_id);
					addr.setInitiated_from("u");
					addr.setStatus(0);
					
					addr.setPers_addr_country_other(addr2.get(0).getPers_addr_country_other());
					addr.setPers_addr_district_other(addr2.get(0).getPers_addr_district_other());
					addr.setPers_addr_state_other(addr2.get(0).getPers_addr_state_other());
					addr.setPers_addr_tehsil_other(addr2.get(0).getPers_addr_tehsil_other());
					
					addr.setSpouse_addr_country_other(addr2.get(0).getSpouse_addr_country_other());
					addr.setSpouse_addr_district_other(addr2.get(0).getSpouse_addr_district_other());
					addr.setSpouse_addr_state_other(addr2.get(0).getSpouse_addr_state_other());
					addr.setSpouse_addr_tehsil_other(addr2.get(0).getSpouse_addr_tehsil_other());
					addr.setSpouse_rural_urban_semi(addr2.get(0).getSpouse_rural_urban_semi());
					addr.setSpouse_country(addr2.get(0).getSpouse_country());
					addr.setSpouse_state(addr2.get(0).getSpouse_state());
					addr.setSpouse_district(addr2.get(0).getSpouse_district());
					addr.setSpouse_tehsil(addr2.get(0).getSpouse_tehsil());
					addr.setSpouse_village(addr2.get(0).getSpouse_village());
					addr.setSpouse_near_railway_station(addr2.get(0).getSpouse_near_railway_station());
					addr.setSpouse_pin_code(addr2.get(0).getSpouse_pin_code());
					addr.setStn_hq_sus_no(addr2.get(0).getStn_hq_sus_no());
					
					addr.setPer_home_addr_country_other(per_home_addr_country_other);
					addr.setPer_home_addr_state_other(per_home_addr_state_other);
					addr.setPer_home_addr_district_other(per_home_addr_district_other);
					addr.setPer_home_addr_tehsil_other(per_home_addr_tehsil_other);
					
					addr.setPre_country_other(addr2.get(0).getPre_country_other());
					addr.setPre_domicile_state_other(addr2.get(0).getPre_domicile_state_other());
					addr.setPre_domicile_district_other(addr2.get(0).getPre_domicile_district_other());
					addr.setPre_domicile_tesil_other(addr2.get(0).getPre_domicile_tesil_other());
					 addr.setCreated_by(username);
					 addr.setCreated_date(new Date());
					int id1 = (int) sessionhql.save(addr);
					data.put("add_id", Integer.toString(id1));
				}
				else {
					String hql1 = "update TB_CENSUS_ADDRESS_JCO set permanent_country=:permanent_country ,permanent_district=:permanent_district ,permanent_state=:permanent_state,"
							+ "permanent_tehsil=:permanent_tehsil,permanent_village=:permanent_village,permanent_pin_code=:permanent_pin_code,"
							+ "permanent_near_railway_station=:permanent_near_railway_station,permanent_rural_urban_semi=:permanent_rural_urban_semi,permanent_border_area=:permanent_border_area,"
							+ "modified_by=:modified_by, modified_date=:modified_date,status=:status,"
							+ "per_home_addr_country_other=:per_home_addr_country_other,"
							+ "per_home_addr_state_other=:per_home_addr_state_other,per_home_addr_district_other=:per_home_addr_district_other,per_home_addr_tehsil_other=:per_home_addr_tehsil_other"
							+ " where  id=:id";
					Query query1 = sessionhql.createQuery(hql1)
							.setInteger("permanent_country", Integer.parseInt(per_home_addr_country))
							.setInteger("permanent_district", Integer.parseInt(per_home_addr_district))
							.setInteger("permanent_state", Integer.parseInt(per_home_addr_state))
							.setInteger("permanent_tehsil", Integer.parseInt(per_home_addr_tehsil))
							.setString("permanent_village", per_home_addr_village)
							.setInteger("permanent_pin_code", Integer.parseInt(per_home_addr_pin))
							.setString("permanent_near_railway_station", per_home_addr_rail)
							.setString("permanent_rural_urban_semi", per_home_addr_rural_urban)
							.setString("permanent_border_area", border_area).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0).setInteger("id", non_add_id)
							.setString("per_home_addr_country_other", per_home_addr_country_other)
							.setString("per_home_addr_state_other", per_home_addr_state_other)
							.setString("per_home_addr_district_other", per_home_addr_district_other)
							.setString("per_home_addr_tehsil_other", per_home_addr_tehsil_other);
					msg = query1.executeUpdate() > 0 ? "update" : "0";
					if (!msg.equals("update")) {				
						data.put("error", "Data Not Updated Successfully");
						tx.rollback();
						return data;
					}
					data.put("add_id", Integer.toString(non_add_id));
				}
				
				

				
			} else {
				

				String hql = "update TB_NON_EFFECTIVE_JCO set non_ef_authority=:non_ef_authority, date_of_authority_non_ef=:date_of_authority_non_ef, cause_of_non_effective=:cause_of_non_effective, "
						+ "date_of_non_effective=:date_of_non_effective, "
						+ "modified_by=:modified_by, modified_date=:modified_date,status=:status where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("non_ef_authority", non_ef_authority)
						.setTimestamp("date_of_authority_non_ef", date_of_authority_non_ef)
						.setString("cause_of_non_effective", cause_of_non_effective)
						.setTimestamp("date_of_non_effective", date_of_non_effective).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0).setInteger("id", nf_id);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				if (!msg.equals("update")) {				
					data.put("error", "Data Not Updated Successfully");
					tx.rollback();
					return data;
				}
				data.put("nf_id", Integer.toString(nf_id));

				String hql1 = "update TB_CENSUS_ADDRESS_JCO set permanent_country=:permanent_country ,permanent_district=:permanent_district ,permanent_state=:permanent_state,"
						+ "permanent_tehsil=:permanent_tehsil,permanent_village=:permanent_village,permanent_pin_code=:permanent_pin_code,"
						+ "permanent_near_railway_station=:permanent_near_railway_station,permanent_rural_urban_semi=:permanent_rural_urban_semi,permanent_border_area=:permanent_border_area,"
						+ "modified_by=:modified_by, modified_date=:modified_date,status=:status,"
						+ "per_home_addr_country_other=:per_home_addr_country_other,"
						+ "per_home_addr_state_other=:per_home_addr_state_other,per_home_addr_district_other=:per_home_addr_district_other,per_home_addr_tehsil_other=:per_home_addr_tehsil_other"
						+ " where  id=:id";
				Query query1 = sessionhql.createQuery(hql1)
						.setInteger("permanent_country", Integer.parseInt(per_home_addr_country))
						.setInteger("permanent_district", Integer.parseInt(per_home_addr_district))
						.setInteger("permanent_state", Integer.parseInt(per_home_addr_state))
						.setInteger("permanent_tehsil", Integer.parseInt(per_home_addr_tehsil))
						.setString("permanent_village", per_home_addr_village)
						.setInteger("permanent_pin_code", Integer.parseInt(per_home_addr_pin))
						.setString("permanent_near_railway_station", per_home_addr_rail)
						.setString("permanent_rural_urban_semi", per_home_addr_rural_urban)
						.setString("permanent_border_area", border_area).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0).setInteger("id", non_add_id)
						.setString("per_home_addr_country_other", per_home_addr_country_other)
						.setString("per_home_addr_state_other", per_home_addr_state_other)
						.setString("per_home_addr_district_other", per_home_addr_district_other)
						.setString("per_home_addr_tehsil_other", per_home_addr_tehsil_other);
				msg = query1.executeUpdate() > 0 ? "update" : "0";
				if (!msg.equals("update")) {				
					data.put("error", "Data Not Updated Successfully");	
					tx.rollback();
					return data;
				}
				data.put("add_id", Integer.toString(non_add_id));
				// Mmap.put("msg", "Data Updated Successfully");
			}
			


			pjc.update_JcoOr_status(jco_id,username);
			tx.commit();

			

//			if (non_elist.size() > 0 && cause != Integer.parseInt(cause_of_non_effective)
//					&& (cause == 1 || cause == 2)) {
//				data.put("msg","\nPlease Make Sure that Cause of Non Effective Matches Nature Of Casuality Of Battle And Physical Casuality!");
//			}

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				data.put("error", "Data Not Updated Successfully");
			} catch (RuntimeException rbe) {
				data.put("error", "Data Not Updated Successfully");
			}
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		
		return data;
	}

	// ========================== Get Method =============================//
	
	@RequestMapping(value = "/getNon_effective_JCOdate", method = RequestMethod.POST)

	public @ResponseBody ArrayList<ArrayList<String>> getNon_effective_JCOdate(int jco_id,String hd_rank) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		/*
		 * Query q1 = sessionHQL
		 * 
		 * .createQuery("FROM TB_CENSUS_DETAIL_Parent c where jco_id = :jco_id and status in (-1,0)"
		 * );
		 * 
		 * q1.setParameter("jco_id", jco_id);
		 * 
		 * @SuppressWarnings("unchecked")
		 */

		ArrayList<ArrayList<String>> list = UOD.getNon_effective_date(jco_id,hd_rank);
	

		tx.commit();

		return list;

	}

	@RequestMapping(value = "/admin/getNon_JCOeffective", method = RequestMethod.POST)
	public @ResponseBody List<TB_NON_EFFECTIVE_JCO> getNon_JCOeffective(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
	
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_NON_EFFECTIVE_JCO where  status='0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_NON_EFFECTIVE_JCO> list = (List<TB_NON_EFFECTIVE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/getPerAddressDataStatusJCO1", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_ADDRESS_JCO> getPerAddressDataStatus1( HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
	
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		
		String hqlUpdate = " from TB_CENSUS_ADDRESS_JCO where  jco_id=:jco_id and  status = '1' order by id desc";
		Query query = sessionHQL.createQuery(hqlUpdate).setMaxResults(1)
				.setInteger("jco_id", jco_id);
		List<TB_CENSUS_ADDRESS_JCO> list = (List<TB_CENSUS_ADDRESS_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
	
		return list;
	}

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_NON_EFFECTIVE_JCO> getNon_effective1( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_NON_EFFECTIVE_JCO where  status='0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_NON_EFFECTIVE_JCO> list = (List<TB_NON_EFFECTIVE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Non_Effective(TB_NON_EFFECTIVE_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		// String msg1 = "";
		try {
			String hql1 = "update TB_NON_EFFECTIVE_JCO set status=:status where  jco_id=:jco_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setInteger("jco_id", obj.getJco_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_NON_EFFECTIVE_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  jco_id=:jco_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("jco_id", obj.getJco_id());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			sessionHQL.flush();
			sessionHQL.clear();
			String hqlUpdate_s="from TB_NON_EFFECTIVE_JCO where jco_id=:jco_id and status=1 order by id desc ";
			Query query_s = sessionHQL.createQuery(hqlUpdate_s).setInteger("jco_id", obj.getJco_id()).setMaxResults(1);
			List<TB_NON_EFFECTIVE_JCO> cget = (List<TB_NON_EFFECTIVE_JCO>)  query_s.list();
			
			if(cget.size()==0) {
				tx.rollback();
				return "Data Not Approve Successfully.";
			}
			
			TB_CENSUS_JCO_OR_PARENT cl=(TB_CENSUS_JCO_OR_PARENT)sessionHQL.get(TB_CENSUS_JCO_OR_PARENT.class,  obj.getJco_id());
			String hqlUpdate3="select id from TB_POSTING_IN_OUT_JCO where jco_id=:jco_id and status=1 and to_sus_no=:to_sus_no order by id desc ";
			Query query3 = sessionHQL.createQuery(hqlUpdate3).setInteger("jco_id", cl.getId()).setString("to_sus_no", cl.getUnit_sus_no()).setMaxResults(1);
			Integer c = (Integer)  query3.uniqueResult();
			
			if(c!=null && c>0) {
				int chang_id=c.intValue();
				String hql2 = "update TB_POSTING_IN_OUT_JCO set modified_by=:modified_by,modified_date=:modified_date,tenure_date=:tenure_date "
						+ " where id=:id ";
			
				Query query2 = sessionHQL.createQuery(hql2).setString("modified_by", username)
						.setTimestamp("modified_date", obj.getModified_date()).setTimestamp("tenure_date",cget.get(0).getDate_of_non_effective() )
				.setInteger("id", chang_id);
				msg = query2.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			}
			
			
			
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	public String Update_JCO_Census_Data(TB_CENSUS_JCO_OR_PARENT obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {
			String hql = "update TB_CENSUS_JCO_OR_PARENT set modified_by=:modified_by,modified_date=:modified_date,"
					+ "status=:status where id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 4).setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";

			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	/*--------------------- For REJECT NON EFFECTIVE ----------------------------------*/

	@RequestMapping(value = "/admin/getNonEffective_JCOReject", method = RequestMethod.POST)
	public @ResponseBody String getNonEffective_JCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String reject_remarks = request.getParameter("reject_remarks");

		
		String username = session.getAttribute("username").toString();
		TB_NON_EFFECTIVE_JCO ChangeNon = new TB_NON_EFFECTIVE_JCO();
		
		ChangeNon.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		ChangeNon.setReject_remarks(reject_remarks);
		String msg1 = Update_NonEffectiveReject(ChangeNon, username);

		return msg1;

	}

	public String Update_NonEffectiveReject(TB_NON_EFFECTIVE_JCO ChangeNon, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		
		try {


		String hql2 = "update TB_CENSUS_ADDRESS_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
				+ " where  jco_id=:jco_id and status = '0' ";

		Query query2 = sessionHQL.createQuery(hql2).setString("modified_by", username)
				.setTimestamp("modified_date", new Date()).setInteger("status", 3)
				.setInteger("jco_id", ChangeNon.getJco_id());

		msg = query2.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected. ";
		
		String hql = "update TB_NON_EFFECTIVE_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
				+ " where  jco_id=:jco_id and status = '0' ";

		Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
				.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", ChangeNon.getReject_remarks())
				.setInteger("jco_id", ChangeNon.getJco_id());
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

	public @ResponseBody List<TB_NON_EFFECTIVE_JCO> getNonEffective2( int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_NON_EFFECTIVE_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_NON_EFFECTIVE_JCO> list = (List<TB_NON_EFFECTIVE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_NonEffectiveJCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_NON_EFFECTIVE_JCO> get_NonEffective3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_NON_EFFECTIVE_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_NON_EFFECTIVE_JCO> list = (List<TB_NON_EFFECTIVE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	/*--------------------- For REJECT NON EFFECTIVE  END----------------------------------*/
	 @RequestMapping(value = "/admin/non_effect_delete_JCOaction", method = RequestMethod.POST)
		public @ResponseBody String non_effect_delete_action(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("id"));
			int addr_id = Integer.parseInt(request.getParameter("addr_id"));
			try {
				String hqlUpdate1 = "delete from TB_CENSUS_ADDRESS_JCO where id=:id";
				int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("id", addr_id).executeUpdate();
				
				String hqlUpdate = "delete from TB_NON_EFFECTIVE_JCO where id=:id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
				if (app > 0 && app1> 0) {
					msg = "1";
				} else {
					tx.rollback();
					msg = "0";
				}
				tx.commit();
				sessionHQL.close();
			} catch (Exception e) {
			}
			return msg;
		}
}
