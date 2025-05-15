package com.controller.psg.update_offr_data;

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

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.update_census_data.TB_CENSUS_BATT_PHY_CASUALITY;
import com.models.psg.update_census_data.TB_NON_EFFECTIVE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Battle_and_Physical_Casuality_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();
	ValidationController validation = new ValidationController();
	/*@RequestMapping(value = "/admin/Battle_and_Physical_CasualityUrl", method = RequestMethod.GET)
	public ModelAndView Institute(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		Mmap.put("getCommandDetailsList", mcommon.getCommandDetailsList());
		Mmap.put("getMedCountryName", mcommon.getMedCountryName("", session));
		Mmap.put("getCauseOfCasualityList", mcommon.getCauseOfCasualityList());
		Mmap.put("getPhysicalDescList", mcommon.getPhysicalDescList());
		return new ModelAndView("Battle_and_Physical_CasualityTiles");
	}*/
	
	
	@RequestMapping(value = "/admin/Battle_and_Physical_Casuality_GetData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_BATT_PHY_CASUALITY> Battle_and_Physical_Casuality_GetData( HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("census_id"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CENSUS_BATT_PHY_CASUALITY where census_id=:census_id and comm_id=:comm_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_BATT_PHY_CASUALITY> list = (List<TB_CENSUS_BATT_PHY_CASUALITY>) query.list();
		tx.commit();
		sessionHQL.close();

		
		return list;
	}

	
	@RequestMapping(value = "/admin/battle_physical_casualityAction", method = RequestMethod.POST)
	public @ResponseBody String battle_physical_casualityAction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date date_casuality = null;
		Date batnpc_date_authority = null;
		Date date_informing = null;
		String username = session.getAttribute("username").toString();

		String classification_of_casuality = request.getParameter("classification_of_casuality");
		String nature_of_casuality = request.getParameter("nature_of_casuality");
		String name_of_operation = request.getParameter("name_of_operation");
		String date_of_casuality = request.getParameter("date_of_casuality");
		Date comm_date = format.parse(request.getParameter("comm_date"));
		
		String cause_of_casuality = request.getParameter("cause_of_casuality");
		name_of_operation = name_of_operation.replace("&#40;", "(");
		name_of_operation = name_of_operation.replace("&#41;", ")");
		String battle_desc = request.getParameter("battle_desc");
		String battle_desc_others = request.getParameter("battle_desc_others");
		String others_battle = request.getParameter("others_battle");
		String physical_desc = request.getParameter("physical_desc");
		String physical_desc_others = request.getParameter("physical_desc_others");
		String others_physical = request.getParameter("others_physical");
				
		String batnpc_diagnosis = request.getParameter("batnpc_diagnosis");
		String batnpc_exact_place = request.getParameter("batnpc_exact_place");
		String whether_on = request.getParameter("whether_on");
		
		String batnpc_command = request.getParameter("batnpc_command");
		String batnpc_corps_area = request.getParameter("batnpc_corps_area");
		String batnpc_div_subarea = request.getParameter("batnpc_div_subarea");
		String batnpc_bde = request.getParameter("batnpc_bde");
		String batnpc_unit = request.getParameter("app_unit_name");
		batnpc_unit = batnpc_unit.replace("&#40;", "(");
		batnpc_unit = batnpc_unit.replace("&#41;", ")");
		
		String batnpc_country = request.getParameter("batnpc_country");
		String batnpc_state = request.getParameter("batnpc_state");
		String batnpc_district = request.getParameter("batnpc_district");
		String batnpc_tehsil = request.getParameter("batnpc_tehsil");
		String batnpc_village = request.getParameter("batnpc_village");
		String batnpc_pin = request.getParameter("batnpc_pin");
		
		

		String batnpc_ch_id = request.getParameter("batnpc_ch_id");
		String census_id = request.getParameter("census_id");		
		String batnpc_authority = request.getParameter("batnpc_authority");
		String batnpc_date_of_authority = request.getParameter("batnpc_date_of_authority");
		
		String country_other = request.getParameter("country_other");
		String state_other = request.getParameter("state_other");
		String district_other = request.getParameter("district_other");
		String tehsil_other = request.getParameter("tehsil_other");
		///new added
		String time_of_casuality = request.getParameter("time_of_casuality");
		String missing_desc = request.getParameter("missing_desc");
		String onduty = request.getParameter("onduty");
		String onduityother = request.getParameter("onduityother");
		String mission_expedition = request.getParameter("mission_expedition");
		String area_post_town = request.getParameter("area_post_town");
		String Sector_bde = request.getParameter("Sector_bde");
		String btnpc_sector = request.getParameter("btnpc_sector");
		String field_services = request.getParameter("field_services");
		String hospital_name = request.getParameter("hospital_name");
		String hospital_location = request.getParameter("hospital_location");
		String cause_of_casuality_1 = request.getParameter("cause_of_casuality_1");
		String cause_of_casuality_2 = request.getParameter("cause_of_casuality_2");
		String cause_of_casuality_3 = request.getParameter("cause_of_casuality_3");
		String circumstances = request.getParameter("circumstances");
		String diagnosis_others = request.getParameter("diagnosis_others"); 
		String nok_informed = request.getParameter("nok_informed");
		String aid_to_civ = request.getParameter("aid_to_civ");
		String date_of_informing = request.getParameter("date_of_informing");
		if (!date_of_informing.equals("") && !date_of_informing.equals("DD/MM/YYYY")) {
			date_informing = format.parse(date_of_informing);
		}
		String time_of_informing = request.getParameter("time_of_informing");
		String methodofinforming = request.getParameter("methodofinforming");
		///////
		
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));					
		String msg = "";
		

		if(batnpc_authority == null || batnpc_authority.equals("")){ 
			return "Please Enter Authority";
		}
		if (!validation.isValidAuth(batnpc_authority)) {
			return validation.isValidAuthMSG + " Authority";	
		}
 		if (!validation.isvalidLength(batnpc_authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority " + validation.isValidLengthMSG;	
		}	
 		if (batnpc_date_of_authority == null || batnpc_date_of_authority.equals("null") || 
 				batnpc_date_of_authority.equals("DD/MM/YYYY") || batnpc_date_of_authority.equals("")) {
			return "Please Select Date of Authority";
		}
 		if (!validation.isValidDate(batnpc_date_of_authority)) {
			return  validation.isValidDateMSG + " of Authority";	
		}
 		if (!batnpc_date_of_authority.equals("") && !batnpc_date_of_authority.equals("DD/MM/YYYY")) {
			batnpc_date_authority = format.parse(batnpc_date_of_authority);
		}
 		if (mcommon.CompareDate(batnpc_date_authority,comm_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
		}
 		
 		if (date_of_casuality == null || date_of_casuality.equals("null") || 
 				date_of_casuality.equals("DD/MM/YYYY") || date_of_casuality.equals("")) {
			return "Please Select Date of Casuality";
		}
 		if (!validation.isValidDate(date_of_casuality)) {
			return  validation.isValidDateMSG + " of Casuality";	
		}
 		if (!date_of_casuality.equals("") && !date_of_casuality.equals("DD/MM/YYYY")) {
			date_casuality = format.parse(date_of_casuality);
		}
 		if (mcommon.CompareDate(date_casuality,comm_date) == 0) {
			return "Casuality Date should be Greater than Commission Date";
		}
 		if (batnpc_country == null || batnpc_country.equals("0")) {
			return "Please Select the Country";
		}
		if (batnpc_country.equals("OTHERS") && country_other.equals("") || batnpc_country == "OTHERS" && country_other == "") {
			return "Please Enter Country Other";
		}
	/*	if (batnpc_state == null || batnpc_state.equals("0")) {
			return "Please Select the State";
		}
		if (batnpc_state.equals("OTHERS") && state_other.equals("") || batnpc_state == "OTHERS" && state_other == "") {
			return "Please Enter State Other";
		}
		if (batnpc_district == null || batnpc_district.equals("0")) {
			return "Please Select the District";
		}
		if (batnpc_district.equals("OTHERS") && district_other.equals("") || batnpc_district == "OTHERS" && district_other == "") {
			return "Please Enter District Other";
		}
		if (batnpc_tehsil == null || batnpc_tehsil.equals("0")) {
			return "Please Select the Tehsil";
		}
		if (batnpc_tehsil.equals("OTHERS") && tehsil_other.equals("") || batnpc_tehsil == "OTHERS" && tehsil_other == "") {
			return "Please Enter Tehsil Other";
		}*/
		if(batnpc_country == "6" || batnpc_country.equals("6")) {
			if (batnpc_pin == null || batnpc_pin.equals("")) {
				return "Please Enter the Pin";
			}
		}
		if (validation.isOnlyNumer(batnpc_pin) == true) {
	 		return validation.isOnlyNumerMSG + " Pin";	
		}
		if (!validation.isvalidLength(batnpc_pin,validation.pin_noMax,validation.pin_noMin)) {
	 		return "Pin " + validation.isValidLengthMSG;	
		}
		if (batnpc_pin.equals("") || batnpc_pin == null ) {
			batnpc_pin="0";
		}
		
		if (classification_of_casuality == "physical_casuality" || classification_of_casuality.equals("physical_casuality")) {
			if (name_of_operation.equals("") || name_of_operation == null ) {
				return "Please Enter Name of Operation.";
			}
		}
		if (whether_on == null || batnpc_bde.equals("")) {
			return "Please Select whether On";
		}
		if (batnpc_bde == null || batnpc_bde.equals("0")) {
			return "Please Select BDE.";
		}
		if (batnpc_div_subarea == null || batnpc_div_subarea.equals("0")) {
			return "Please Select DIV/Sub Area";
		}
		if (batnpc_corps_area == null || batnpc_corps_area.equals("0")) {
			return "Please Select Corps/Area.";
		}
		if (batnpc_command == null || batnpc_command.equals("0")) {
			return "Please Select Command.";
		}
		if (nok_informed == null || nok_informed.equals("")) {
			return "Please Select Whether NOK Informed or Not.";
		}

	try {
		
		//for non_effective validation
//				Query qnon = sessionhql.createQuery("from TB_NON_EFFECTIVE where census_id=:census_id and comm_id=:comm_id and status=0  order by id");
//				qnon.setParameter("census_id", Integer.parseInt(census_id)).setParameter("comm_id",Integer.parseInt(comm_id));
//				List<TB_NON_EFFECTIVE> non_elist = (List<TB_NON_EFFECTIVE>) qnon.list();
//				int cause=0;
//				if(non_elist.size() > 0 ) {
//				 cause = Integer.parseInt(non_elist.get(0).getCause_of_non_effective());
//				}
//				String almsg ="";
//				if(Integer.parseInt(nature_of_casuality) ==1) {
//					 almsg = "KILLED IN ACTION";
//				}
//				else if(Integer.parseInt(nature_of_casuality) ==2) {
//					 almsg = "DIED";
//				}
//				if(non_elist.size() > 0 && cause != Integer.parseInt(nature_of_casuality) && (Integer.parseInt(nature_of_casuality) ==1 || Integer.parseInt(nature_of_casuality)==2) ){ 
//					return "Please Update Cause of Non Effective to "+almsg+" From NON EFFECTIVE STATUS!";
//				}
				
				
			if (Integer.parseInt(batnpc_ch_id) == 0) {
				TB_CENSUS_BATT_PHY_CASUALITY batnpc = new TB_CENSUS_BATT_PHY_CASUALITY();
				batnpc.setClassification_of_casuality(classification_of_casuality);
				batnpc.setNature_of_casuality(nature_of_casuality);
				batnpc.setName_of_operation(name_of_operation);
				batnpc.setCountry_other(country_other);
				batnpc.setState_other(state_other);
				batnpc.setDistrict_other(district_other);
				batnpc.setTehsil_other(tehsil_other);
				
				batnpc.setMissing_desc(missing_desc);
				batnpc.setOnduty(onduty);
				batnpc.setOnduityother(onduityother);
				batnpc.setMission_expedition(mission_expedition);
				batnpc.setArea_post_town(area_post_town);
				batnpc.setSector_bde(Sector_bde);
				batnpc.setSector(btnpc_sector);
				batnpc.setField_services(field_services);
				batnpc.setHospital_name(hospital_name);
				batnpc.setHospital_location(hospital_location);
				batnpc.setCircumstances(circumstances);
				batnpc.setDiagnosis_others(diagnosis_others);
				batnpc.setAid_to_civ(aid_to_civ);
				batnpc.setNok_informed(nok_informed);
				batnpc.setDate_of_informing(date_informing);
				batnpc.setMethodofinforming(methodofinforming);
				batnpc.setCause_of_casuality_1(cause_of_casuality_1);
				batnpc.setCause_of_casuality_2(cause_of_casuality_2);
				batnpc.setCause_of_casuality_3(cause_of_casuality_3);
				batnpc.setTime_of_casuality(time_of_casuality);
				batnpc.setTime_of_informing(time_of_informing);
			
				if (!date_of_casuality.equals("") && !date_of_casuality.equals("DD/MM/YYYY")) {
				batnpc.setDate_of_casuality(date_casuality);}
				batnpc.setCause_of_casuality(cause_of_casuality);
				
				if (classification_of_casuality.equals("physical_casuality") &&  !cause_of_casuality.equals("0")) {
					if (!cause_of_casuality.equals("OTHERS")) {
						batnpc.setDescription(physical_desc);
						if (physical_desc.equals("OTHERS")) {
							batnpc.setDesc_others(physical_desc_others);
							}
						}
					else {
						batnpc.setDescription(others_physical);
						}
					}
				
				if (classification_of_casuality.equals("battle_casuality") &&  !cause_of_casuality.equals("0")) {
					if (!cause_of_casuality.equals("OTHERS")) {
						batnpc.setDescription(battle_desc);
						if (battle_desc.equals("OTHERS")) {
							batnpc.setDesc_others(battle_desc_others);
							}
						}
					else {
						batnpc.setDescription(others_battle);
						}
					}
				
				batnpc.setDiagnosis(batnpc_diagnosis);
				batnpc.setExact_place(batnpc_exact_place);
				batnpc.setWhether_on(whether_on);
				batnpc.setCommand(batnpc_command);
				batnpc.setCorps_area(batnpc_corps_area);
				batnpc.setDiv_subarea(batnpc_div_subarea);
				batnpc.setBde(batnpc_bde);
				batnpc.setUnit(batnpc_unit);
				batnpc.setCountry(batnpc_country);
				batnpc.setState(batnpc_state);
				batnpc.setDistrict(batnpc_district);
				batnpc.setTehsil(batnpc_tehsil);
				batnpc.setVillage(batnpc_village);
				batnpc.setPin(Integer.parseInt(batnpc_pin));
				batnpc.setCensus_id(Integer.parseInt(census_id));
				batnpc.setCreated_by(username);
				batnpc.setCreated_on(date);
				batnpc.setComm_id(comm_id);
				batnpc.setAuthority(batnpc_authority);
					if (!batnpc_date_of_authority.equals("") && !batnpc_date_of_authority.equals("DD/MM/YYYY")) {
					batnpc.setDate_of_authority(batnpc_date_authority);}
				batnpc.setStatus(0);

				int id = (int) sessionhql.save(batnpc);
				msg = Integer.toString(id);
				
			} else {

				String hql = "update TB_CENSUS_BATT_PHY_CASUALITY set modify_by=:modify_by ,modify_on=:modify_on ,date_of_authority=:date_of_authority"
						+ ",authority=:authority,classification_of_casuality=:classification_of_casuality,nature_of_casuality=:nature_of_casuality"
						+ ",name_of_operation=:name_of_operation,date_of_casuality=:date_of_casuality,cause_of_casuality=:cause_of_casuality"
						+ ",description=:description,desc_others=:desc_others"
						+ ",diagnosis=:diagnosis,exact_place=:exact_place,whether_on=:whether_on,unit=:unit,bde=:bde,div_subarea=:div_subarea"
						+ ",corps_area=:corps_area,command=:command"
						+ ",country=:country,state=:state,district=:district,tehsil=:tehsil,village=:village,pin=:pin ,status=:status,country_other=:country_other,state_other=:state_other,district_other=:district_other,tehsil_other=:tehsil_other"
						+ ",missing_desc=:missing_desc,onduty=:onduty,onduityother=:onduityother,mission_expedition=:mission_expedition,area_post_town=:area_post_town,sector_bde=:sector_bde "
						+ ",sector=:sector,field_services=:field_services,hospital_name=:hospital_name,hospital_location=:hospital_location,circumstances=:circumstances,diagnosis_others=:diagnosis_others "
						+ ",aid_to_civ=:aid_to_civ,nok_informed=:nok_informed,date_of_informing=:date_of_informing,methodofinforming=:methodofinforming,cause_of_casuality_1=:cause_of_casuality_1 "
						+ ",cause_of_casuality_2=:cause_of_casuality_2,cause_of_casuality_3=:cause_of_casuality_3,time_of_casuality=:time_of_casuality,time_of_informing=:time_of_informing";
				
				hql+=" where  id=:id";
				Query query = sessionhql.createQuery(hql)						
						.setInteger("id", Integer.parseInt(batnpc_ch_id))						
						.setString("modify_by", username)
						.setTimestamp("modify_on", date)
						//.setTimestamp("date_of_authority", format.parse(batnpc_date_of_authority))						
						.setString("authority", batnpc_authority)
						.setString("classification_of_casuality", classification_of_casuality)
						.setString("nature_of_casuality", nature_of_casuality)
						.setString("name_of_operation", name_of_operation)
						//.setTimestamp("date_of_casuality", format.parse(date_of_casuality))	
						.setString("cause_of_casuality", cause_of_casuality)
						.setString("diagnosis", batnpc_diagnosis)
						.setString("exact_place", batnpc_exact_place)
						.setString("whether_on", whether_on)
						.setString("command", batnpc_command)
						.setString("corps_area", batnpc_corps_area)
						.setString("div_subarea", batnpc_div_subarea)
						.setString("bde", batnpc_bde)
						.setString("unit", batnpc_unit)
						.setString("country", batnpc_country)
						.setString("state", batnpc_state)
						.setString("district", batnpc_district)
						.setString("tehsil", batnpc_tehsil)
						.setString("village", batnpc_village)
						.setInteger("pin", Integer.parseInt(batnpc_pin))
						.setInteger("status", 0)
						.setString("country_other", country_other)
						.setString("state_other", state_other)
						.setString("district_other", district_other)
						.setString("tehsil_other", tehsil_other)
				
						.setString("missing_desc", missing_desc)
						.setString("onduty", onduty)
						.setString("onduityother", onduityother)
						.setString("mission_expedition", mission_expedition)
						.setString("area_post_town", area_post_town)
						.setString("sector_bde", Sector_bde)
						.setString("sector", btnpc_sector)
						.setString("field_services", field_services)
						.setString("hospital_name", hospital_name)
						.setString("hospital_location", hospital_location)
						.setString("circumstances", circumstances)
						.setString("diagnosis_others", diagnosis_others)
						.setString("aid_to_civ", aid_to_civ)
						.setString("nok_informed", nok_informed)
						.setString("methodofinforming", methodofinforming)
						.setString("cause_of_casuality_1", cause_of_casuality_1)
						.setString("cause_of_casuality_2", cause_of_casuality_2)
						.setString("cause_of_casuality_3", cause_of_casuality_3)
						.setString("time_of_casuality", time_of_casuality)
						.setString("time_of_informing", time_of_informing);
				
						
					if (!date_of_casuality.equals("") && !date_of_casuality.equals("DD/MM/YYYY")) {
					query.setTimestamp("date_of_casuality", date_casuality);
				}
				else
					query.setTimestamp("date_of_casuality", null);
				
					if (!batnpc_date_of_authority.equals("") && !batnpc_date_of_authority.equals("DD/MM/YYYY")) {
					query.setTimestamp("date_of_authority", batnpc_date_authority);
				}
				else
					query.setTimestamp("date_of_authority", null);
					
					if (!date_of_informing.equals("") && !date_of_informing.equals("DD/MM/YYYY")) {
						query.setTimestamp("date_of_informing", date_informing);
					}
					else
						query.setTimestamp("date_of_informing", null);
				
				
				if (classification_of_casuality.equals("physical_casuality") &&  !cause_of_casuality.equals("0")) {
					if (!cause_of_casuality.equals("OTHERS")) {
						query.setString("description",physical_desc);
						if (physical_desc.equals("OTHERS")) {
							query.setString("desc_others",physical_desc_others);
							}
						else
							query.setString("desc_others",null);
						}
					else {
						query.setString("desc_others",null);
						query.setString("description",others_physical);
						}
					}
				
				else if (classification_of_casuality.equals("battle_casuality") &&  !cause_of_casuality.equals("0")) {
					if (!cause_of_casuality.equals("OTHERS")) {
						query.setString("description",battle_desc);
						if (battle_desc.equals("OTHERS")) {
							query.setString("desc_others",battle_desc_others);
							}
						else
							query.setString("desc_others",null);
						}
					else {
						query.setString("description",others_battle);
						query.setString("desc_others",null);
						}
					}
				
				else {
					query.setString("description",null);
					query.setString("desc_others",null);
				}
				msg = query.executeUpdate() > 0 ? "update" : "0";
			}
			
			//for non_effective
//			if((Integer.parseInt(nature_of_casuality) == 1 || Integer.parseInt(nature_of_casuality) == 2 ) && non_elist.size() == 0) {
//				TB_NON_EFFECTIVE non =new TB_NON_EFFECTIVE();
//			    non.setCreated_by(username);
//			    non.setCreated_date(date);
//				non.setNon_ef_authority(batnpc_authority);
//					if (!batnpc_date_of_authority.equals("") && !batnpc_date_of_authority.equals("DD/MM/YYYY")) {
//					non.setDate_of_authority_non_ef(batnpc_date_authority);}
//				non.setCause_of_non_effective(nature_of_casuality);
//					if (!date_of_casuality.equals("") && !date_of_casuality.equals("DD/MM/YYYY")) {
//					non.setDate_of_non_effective(date_casuality);}
//				non.setComm_id(Integer.parseInt(comm_id));
//				non.setCensus_id(Integer.parseInt(census_id));
//				non.setStatus(0);
//				non.setCreated_by(username);
//				non.setCreated_date(date);
//				sessionhql.save(non);
//			}
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				
			}
			else
			{
				mcommon.update_offr_status(Integer.parseInt(census_id),username);
			}
			

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Data Not Updated Or Saved";
			} catch (RuntimeException rbe) {
				msg = "Data Not Updated Or Saved";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}

			
		return msg;
	}
	
	
/*--------------------- For Approval ----------------------------------*/
	
	public @ResponseBody List<TB_CENSUS_BATT_PHY_CASUALITY> Battle_and_Physical_Casuality_GetData1(int id,BigInteger comm_id){
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_BATT_PHY_CASUALITY where census_id=:census_id and comm_id=:comm_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_BATT_PHY_CASUALITY> list = (List<TB_CENSUS_BATT_PHY_CASUALITY>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public String Update_Btle_Phy_Casuality(TB_CENSUS_BATT_PHY_CASUALITY obj,String username){
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = sessionHQL.beginTransaction();
	  
		  String msg = "";
		  //String msg1 = "";
		  try{
			    /*String hql1 = "update TB_CENSUS_BATT_PHY_CASUALITY set status=:status where census_id=:census_id and comm_id=:comm_id and status != '0' ";
			  
				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("census_id", obj.getCensus_id())
						.setInteger("comm_id",obj.getComm_id());
				msg1 = query1.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
				 */
		
				  
			       String hql = "update TB_CENSUS_BATT_PHY_CASUALITY set modify_by=:modified_by,modify_on=:modified_date,status=:status  "
						+ " where census_id=:census_id and comm_id=:comm_id and status = '0'";
			  
				   Query query = sessionHQL.createQuery(hql)
						
						.setString("modified_by", username).setTimestamp("modified_date", obj.getModify_on())
						.setInteger("status", 1).setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id",obj.getComm_id());
			
				   msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			     
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
	
	/*--------------------- For REJECT  Battle and Casulity----------------------------------*/
	
	@RequestMapping(value = "/admin/getBattleCasulity_Reject", method = RequestMethod.POST)
	public @ResponseBody String getBattleCasulity_Reject(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		     String username = session.getAttribute("username").toString();
		     TB_CENSUS_BATT_PHY_CASUALITY ChangeBat = new TB_CENSUS_BATT_PHY_CASUALITY();
		     ChangeBat.setCensus_id(Integer.parseInt(request.getParameter("ch_id")));
		     ChangeBat.setComm_id(new BigInteger(request.getParameter("comm_id")));
		     ChangeBat.setReject_remarks(request.getParameter("reject_remarks"));
			 String msg1 = Update_BattleCasulityReject(ChangeBat, username);
				
			  return msg1;
	      
	}

	public String Update_BattleCasulityReject(TB_CENSUS_BATT_PHY_CASUALITY ChangeBat,String username){
		
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = sessionHQL.beginTransaction();
	  
		  String msg = "";
		  String msg1= "";
		 try{
//			      String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//						+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//			  
//				   Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username).setTimestamp("modified_date", new Date())
//						.setInteger("update_ofr_status", 3).setInteger("census_id", ChangeBat.getCensus_id()).setInteger("comm_id",ChangeBat.getComm_id());
//						
//				   msg1 = query1.executeUpdate() > 0 ? "Data Rejected Successfully." :"Data Not Rejected. ";
		     
			      String hql = "update TB_CENSUS_BATT_PHY_CASUALITY set modify_by=:modify_by,modify_on=:modify_on,status=:status,reject_remarks=:reject_remarks  "
						+ " where census_id=:census_id and comm_id=:comm_id and status = '0' ";
			  
				  Query query = sessionHQL.createQuery(hql).setString("modify_by", username).setTimestamp("modify_on", new Date())
						  .setString("reject_remarks", ChangeBat.getReject_remarks())
						.setInteger("status", 3).setInteger("census_id", ChangeBat.getCensus_id()).setBigInteger("comm_id",ChangeBat.getComm_id())
						;
			
		          msg = query.executeUpdate() > 0 ? "1" :"0";
				 	 
		          tx.commit();
		  
		  }catch (Exception e) {
		          msg = "Data Not Rejected.";
		          tx.rollback();
		  }
		  finally {
		          sessionHQL.close();
		  }
		  return msg;

	}

	

		public @ResponseBody List<TB_CENSUS_BATT_PHY_CASUALITY> getChangeBattleCasulity2(int id,BigInteger comm_id){
		
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_BATT_PHY_CASUALITY where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_BATT_PHY_CASUALITY> list = (List<TB_CENSUS_BATT_PHY_CASUALITY>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
		}

		@RequestMapping(value = "/admin/get_BattleCasulity3", method = RequestMethod.POST)
		public @ResponseBody List<TB_CENSUS_BATT_PHY_CASUALITY> get_BattleCasulity3(ModelMap Mmap, HttpSession session,
		            HttpServletRequest request) throws ParseException {
		    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		    Transaction tx = sessionHQL.beginTransaction();
		    int id = Integer.parseInt(request.getParameter("census_id"));
		    BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		    String hqlUpdate = " from TB_CENSUS_BATT_PHY_CASUALITY where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		    Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id",comm_id);
		    @SuppressWarnings("unchecked")
		    List<TB_CENSUS_BATT_PHY_CASUALITY> list = (List<TB_CENSUS_BATT_PHY_CASUALITY>) query.list();
		     tx.commit();
		    sessionHQL.close();
		    return list;
		}
	
		/*--------------------- For REJECT Battle and Casulity  END----------------------------------*/
		 @RequestMapping(value = "/admin/batnpc_delete_action", method = RequestMethod.POST)
			public @ResponseBody String batnpc_delete_action(ModelMap Mmap, HttpSession session,
					HttpServletRequest request) throws ParseException {
				String msg = "";
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				int id = Integer.parseInt(request.getParameter("id"));
				try {
					String hqlUpdate = "delete from TB_CENSUS_BATT_PHY_CASUALITY where id=:id";
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
		 
		 
		 
		 @RequestMapping(value = "/admin/PSG_DATE_Battle_and_Physical_Casuality_GetData", method = RequestMethod.POST)
			public @ResponseBody List<String> PSG_DATE_Battle_and_Physical_Casuality_GetData( HttpSession session,
					HttpServletRequest request) throws ParseException {
				String msg = "";
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				
				BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

				String hqlUpdate = "select date_of_casuality from TB_CENSUS_BATT_PHY_CASUALITY where  comm_id=:comm_id and status=1";
				Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
				List<String> list = (List<String>) query.list();
				tx.commit();
				sessionHQL.close();

			
				return list;
			}
	
}
