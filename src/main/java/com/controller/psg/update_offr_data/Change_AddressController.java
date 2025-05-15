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

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Transaction.TB_CENSUS_ADDRESS;
import com.persistance.util.HibernateUtil;

@Controller

@RequestMapping(value = { "admin", "/", "user" })

public class Change_AddressController {

	

	Psg_CommonController mcommon = new Psg_CommonController();
	ValidationController validation = new ValidationController();

	

/*	@RequestMapping(value = "/admin/Update_address_DataUrl", method = RequestMethod.GET)

	public ModelAndView Update_address_DataUrl(ModelMap Mmap, HttpSession session,

			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Mmap.put("getReligionList", mcommon.getReligionList());

		Mmap.put("getMarital_statusList", mcommon.getMarital_statusList());

		Mmap.put("getBloodList", mcommon.getBloodList());

		Mmap.put("getRelationList", mcommon.getRelationList());

		Mmap.put("getPreCadetStatusList", mcommon.getPreCadetStatusList());

		Mmap.put("getNCCTypeList", mcommon.getNCCTypeList());

		Mmap.put("getLanguageSTDList", mcommon.getLanguageSTDList());

		Mmap.put("getLanguagePROOFList", mcommon.getLanguagePROOFList());

		Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));

		Mmap.put("getMedStateName", mcommon.getMedStateName("", session));

		Mmap.put("getMedCountryName", mcommon.getMedCountryName("", session));

		Mmap.put("getNationalityList", mcommon.getNationalityList());

		Mmap.put("getMedCatList", mcommon.getMedCatList());

		Mmap.put("getRHList", mcommon.getRHList());

		Mmap.put("getVillageList", mcommon.getVillageList());

		Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());

		Mmap.put("getInstituteNameList", mcommon.getInstituteNameList());

		Mmap.put("getLanguageList", mcommon.getLanguageList());

		Mmap.put("getMothertoungeList", mcommon.getMothertoungeList());

		Mmap.put("getMedCityName", mcommon.getMedCityName("", session));

		Mmap.put("getCountryList", mcommon.getCountryList());

		Mmap.put("msg", msg);

		return new ModelAndView("Update_address_DataTiles");

	}*/

	

	@RequestMapping(value = "/admin/changeaddress_details_action", method = RequestMethod.POST)
	public @ResponseBody String changeaddress_details_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String pre_domicile_state = request.getParameter("pre_domicile_state");
		String pre_country = request.getParameter("pre_country");
		String pre_domicile_district = request.getParameter("pre_domicile_district");
		String pre_domicile_tesil = request.getParameter("pre_domicile_tesil");
		String authority = request.getParameter("authority");
		String date_authority = request.getParameter("date_authority");
		String per_home_addr_country = request.getParameter("per_home_addr_country");
		String per_home_addr_village = request.getParameter("per_home_addr_village");
		String per_home_addr_tehsil = request.getParameter("per_home_addr_tehsil");
		String per_home_addr_district = request.getParameter("per_home_addr_district");
		String per_home_addr_state = request.getParameter("per_home_addr_state");
		String per_home_addr_pin = request.getParameter("per_home_addr_pin");
		String per_home_addr_rail = request.getParameter("per_home_addr_rail");
		String per_home_addr_rural_urban = request.getParameter("per_home_addr_rural_urban");
		String spouse_addr_rural_urban = request.getParameter("spouse_addr_rural_urban");
		String border_area = request.getParameter("border_area");
		String pers_addr_country = request.getParameter("pers_addr_country");
		String pers_addr_village = request.getParameter("pers_addr_village");
		String pers_addr_tehsil = request.getParameter("pers_addr_tehsil");
		String pers_addr_district = request.getParameter("pers_addr_district");
		String pers_addr_state = request.getParameter("pers_addr_state");
		String pers_addr_pin = request.getParameter("pers_addr_pin");
		String pers_addr_rail = request.getParameter("pers_addr_rail");
		String pers_addr_rural_urban = request.getParameter("pers_addr_rural_urban");
		
		///pratiksha///
		String pre_country_other = request.getParameter("pre_country_other");
		String pre_domicile_state_other = request.getParameter("pre_domicile_state_other");
		String pre_domicile_district_other = request.getParameter("pre_domicile_district_other");
		String pre_domicile_tesil_other = request.getParameter("pre_domicile_tesil_other");
		String per_home_addr_country_other = request.getParameter("per_home_addr_country_others");
		String per_home_addr_state_other = request.getParameter("per_home_addr_state_others");
		String per_home_addr_district_other = request.getParameter("per_home_addr_district_others");
		String per_home_addr_tehsil_other = request.getParameter("per_home_addr_tehsil_others");
		String pers_addr_country_other = request.getParameter("pers_addr_country_other");
		String pers_addr_state_other = request.getParameter("pers_addr_state_other");
		String pers_addr_district_other = request.getParameter("pers_addr_district_other");
		String pers_addr_tehsil_other = request.getParameter("pers_addr_tehsil_other");
		String spouse_addr_district_other = request.getParameter("spouse_addr_district_other");
		String spouse_addr_state_other = request.getParameter("spouse_addr_state_other");
		String spouse_addr_country_other = request.getParameter("spouse_addr_country_other");
		String spouse_addr_tehsil_other = request.getParameter("spouse_addr_tehsil_other");
		
	   BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		
		
	   

		String spouse_addr_village = request.getParameter("spouse_addr_village");
		String spouse_addr_tehsil = request.getParameter("spouse_addr_tehsil");
		String spouse_addr_district = request.getParameter("spouse_addr_district");
		String spouse_addr_state = request.getParameter("spouse_addr_state");
		String spouse_addr_Country = request.getParameter("spouse_addr_Country");
		String spouse_addr_pin = request.getParameter("spouse_addr_pin");
		String spouse_addr_rail = request.getParameter("spouse_addr_rail");
		String Stn_HQ_sus_no = request.getParameter("Stn_HQ_sus_no");
		String check_spouse_addr = request.getParameter("check_spouse_address");
		String marital_status = request.getParameter("marital_status");
		

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date comm_date = format.parse(request.getParameter("comm_date"));
		String addr_ch_id = request.getParameter("addr_ch_id");
		String census_id = request.getParameter("census_id");
		String msg = "";
		Date auth_dt = null;
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
		if (mcommon.CompareDate(auth_dt,comm_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
		} 		
	
		if (pre_country == null || pre_country.equals("0")) {
			return "Please Select the Country";
		}
		if (pre_country_other != null && !pre_country_other.trim().equals("")) {
			if (!validation.isOnlyAlphabet(pre_country_other)) {
				return " Country Other " + validation.isOnlyAlphabetMSG;
			}
		    if (!validation.isvalidLength(pre_country_other, validation.nameMax, validation.nameMin)) {
		    	return " Country Other " + validation.isValidLengthMSG;
			}
		} 
		if (pre_domicile_state == null || pre_domicile_state.equals("0")) {
			return "Please Select the State";
		}
		if (pre_domicile_state_other != null && !pre_domicile_state_other.trim().equals("")) {
			if (!validation.isOnlyAlphabet(pre_domicile_state_other)) {
				return " State Other " + validation.isOnlyAlphabetMSG;
			}
		    if (!validation.isvalidLength(pre_domicile_state_other, validation.nameMax, validation.nameMin)) {
		    	return " State Other " + validation.isValidLengthMSG;
			}
		} 
		if (pre_domicile_district == null || pre_domicile_district.equals("0")) {
			return "Please Select the District";
		}
		if (pre_domicile_district_other != null && !pre_domicile_district_other.trim().equals("")) {
			if (!validation.isOnlyAlphabet(pre_domicile_district_other)) {
				return " District Other " + validation.isOnlyAlphabetMSG;
			}
		    if (!validation.isvalidLength(pre_domicile_district_other, validation.nameMax, validation.nameMin)) {
		    	return " District Other " + validation.isValidLengthMSG;
			}
		} 
		if (pre_domicile_tesil == null || pre_domicile_tesil.equals("0")) {
			return "Please Select the Tehsil";
		}
		if (pre_domicile_tesil_other != null && !pre_domicile_tesil_other.trim().equals("")) {
			if (!validation.isOnlyAlphabet(pre_domicile_tesil_other)) {
				return " Tehsil Other " + validation.isOnlyAlphabetMSG;
			}
		    if (!validation.isvalidLength(pre_domicile_tesil_other, validation.nameMax, validation.nameMin)) {
		    	return " Tehsil Other " + validation.isValidLengthMSG;
			}
		} 
		
		
		if (per_home_addr_country == null || per_home_addr_country.equals("0")) {
			return "Please Select the Country";
		}
		if (per_home_addr_country_other != null && !per_home_addr_country_other.trim().equals("")) {
			if (!validation.isOnlyAlphabet(per_home_addr_country_other)) {
				return " Country Other " + validation.isOnlyAlphabetMSG;
			}
		    if (!validation.isvalidLength(per_home_addr_country_other, validation.nameMax, validation.nameMin)) {
		    	return " Country Other " + validation.isValidLengthMSG;
			}
		} 
		if (per_home_addr_state == null || per_home_addr_state.equals("0")) {
			return "Please Select the State";
		}
		if (per_home_addr_state_other != null && !per_home_addr_state_other.trim().equals("")) {
			if (!validation.isOnlyAlphabet(per_home_addr_state_other)) {
				return " State Other " + validation.isOnlyAlphabetMSG;
			}
		    if (!validation.isvalidLength(per_home_addr_state_other, validation.nameMax, validation.nameMin)) {
		    	return " State Other " + validation.isValidLengthMSG;
			}
		} 
		if (per_home_addr_district == null || per_home_addr_district.equals("0")) {
			return "Please Select the District";
		}
		if (per_home_addr_district_other != null && !per_home_addr_district_other.trim().equals("")) {
			if (!validation.isOnlyAlphabet(per_home_addr_district_other)) {
				return " District Other " + validation.isOnlyAlphabetMSG;
			}
		    if (!validation.isvalidLength(per_home_addr_district_other, validation.nameMax, validation.nameMin)) {
		    	return " District Other " + validation.isValidLengthMSG;
			}
		} 
		if (per_home_addr_tehsil == null || per_home_addr_tehsil.equals("0")) {
			return "Please Select the Tehsil";
		}
		if (per_home_addr_tehsil_other != null && !per_home_addr_tehsil_other.trim().equals("")) {
			if (!validation.isOnlyAlphabet(per_home_addr_tehsil_other)) {
				return " Tehsil Other " + validation.isOnlyAlphabetMSG;
			}
		    if (!validation.isvalidLength(per_home_addr_tehsil_other, validation.nameMax, validation.nameMin)) {
		    	return " Tehsil Other " + validation.isValidLengthMSG;
			}
		} 
		if (per_home_addr_village == null || per_home_addr_village.equals("")) {
			return "Please Enter the Village/Town/City";
		}
		if (!validation.isOnlyAlphabet(per_home_addr_village)) {
	 		return validation.isOnlyAlphabetMSG + " Village/Town/City";	
		}
		if (!validation.isvalidLength(per_home_addr_village,validation.nameMax,validation.nameMin)) {
	 		return "Village/Town/City " + validation.isValidLengthMSG;	
		}	
		if (per_home_addr_pin == null || per_home_addr_pin.equals("")) {
			return "Please Enter the Pin";
		}
		if (validation.isOnlyNumer(per_home_addr_pin) == true) {
	 		return validation.isOnlyNumerMSG + " Pin";	
		}
		if (!validation.isvalidLength(per_home_addr_pin,validation.pin_noMax,validation.pin_noMin)) {
	 		return "Pin " + validation.isValidLengthMSG;	
		}
		if (per_home_addr_rail == null || per_home_addr_rail.equals("")) {
			return "Please Enter the Nearest Railway Station";
		}		
		if (!validation.isOnlyAlphabetNumeric(per_home_addr_rail)) {
	 		return validation.isOnlyAlphabetNumericMSG + " Nearest Railway Station";	
		}
		if (!validation.isvalidLength(per_home_addr_rail,validation.authorityMax,validation.authorityMin)) {
	 		return "Nearest Railway Station " + validation.isValidLengthMSG;	
		} 
		
		
		if (pers_addr_country == null || pers_addr_country.equals("0")) {
			return "Please Select the Country";
		}
		if (pers_addr_country_other != null && !pers_addr_country_other.trim().equals("")) {
			if (!validation.isOnlyAlphabet(pers_addr_country_other)) {
				return " Country Other " + validation.isOnlyAlphabetMSG;
			}
		    if (!validation.isvalidLength(pers_addr_country_other, validation.nameMax, validation.nameMin)) {
		    	return " Country Other " + validation.isValidLengthMSG;
			}
		} 
		if (pers_addr_state == null || pers_addr_state.equals("0")) {
			return "Please Select the State";
		}
		if (pers_addr_state_other != null && !pers_addr_state_other.trim().equals("")) {
			if (!validation.isOnlyAlphabet(pers_addr_state_other)) {
				return " State Other " + validation.isOnlyAlphabetMSG;
			}
		    if (!validation.isvalidLength(pers_addr_state_other, validation.nameMax, validation.nameMin)) {
		    	return " State Other " + validation.isValidLengthMSG;
			}
		} 
		if (pers_addr_district == null || pers_addr_district.equals("0")) {
			return "Please Select the District";
		}
		if (pers_addr_district_other != null && !pers_addr_district_other.trim().equals("")) {
			if (!validation.isOnlyAlphabet(pers_addr_district_other)) {
				return " District Other " + validation.isOnlyAlphabetMSG;
			}
		    if (!validation.isvalidLength(pers_addr_district_other, validation.nameMax, validation.nameMin)) {
		    	return " District Other " + validation.isValidLengthMSG;
			}
		} 
		if (pers_addr_tehsil == null || pers_addr_tehsil.equals("0")) {
			return "Please Select the   Tehsil";
		}
		if (pers_addr_tehsil_other != null && !pers_addr_tehsil_other.trim().equals("")) {
			if (!validation.isOnlyAlphabet(pers_addr_tehsil_other)) {
				return " Tehsil Other " + validation.isOnlyAlphabetMSG;
			}
		    if (!validation.isvalidLength(pers_addr_tehsil_other, validation.nameMax, validation.nameMin)) {
		    	return " Tehsil Other " + validation.isValidLengthMSG;
			}
		} 
		if (pers_addr_village == null || pers_addr_village.equals("")) {
			return "Please Enter the  Village/Town/City";
		}
		if (!validation.isOnlyAlphabet(pers_addr_village)) {
	 		return validation.isOnlyAlphabetMSG + " Village/Town/City";	
		}
		if (!validation.isvalidLength(pers_addr_village,validation.nameMax,validation.nameMin)) {
	 		return "Village/Town/City " + validation.isValidLengthMSG;	
		}		
		if (pers_addr_pin == null || pers_addr_pin.equals("")) {
			return "Please Enter the Pin";
		}
		if (validation.isOnlyNumer(pers_addr_pin) == true) {
	 		return validation.isOnlyNumerMSG + " Pin";	
		}
		if (!validation.isvalidLength(pers_addr_pin,validation.pin_noMax,validation.pin_noMin)) {
	 		return "Pin " + validation.isValidLengthMSG;	
		}		
		if (pers_addr_rail == null || pers_addr_rail.equals("")) {
			return "Please Enter the  Nearest Railway Station";
		}
		if (!validation.isOnlyAlphabetNumeric(pers_addr_rail)) {
	 		return validation.isOnlyAlphabetNumericMSG + " Nearest Railway Station";	
		}
		if (!validation.isvalidLength(pers_addr_rail,validation.authorityMax,validation.authorityMin)) {
	 		return "Nearest Railway Station " + validation.isValidLengthMSG;	
		} 
		
		if(marital_status!=null && marital_status.equals("8") && check_spouse_addr!=null && check_spouse_addr.equals("on")) {
			if (spouse_addr_Country == null || spouse_addr_Country.equals("0")) {
				return "Please Select The Country";
			}
			if (spouse_addr_country_other != null && !spouse_addr_country_other.trim().equals("")) {
				if (!validation.isOnlyAlphabet(spouse_addr_country_other)) {
					return " Country Other " + validation.isOnlyAlphabetMSG;
				}
			    if (!validation.isvalidLength(spouse_addr_country_other, validation.nameMax, validation.nameMin)) {
			    	return " Country Other " + validation.isValidLengthMSG;
				}
			} 
			if (spouse_addr_state == null || spouse_addr_state.equals("0")) {
				return "Please Select the State";
			}
			if (spouse_addr_state_other != null && !spouse_addr_state_other.trim().equals("")) {
				if (!validation.isOnlyAlphabet(spouse_addr_state_other)) {
					return " State Other " + validation.isOnlyAlphabetMSG;
				}
			    if (!validation.isvalidLength(spouse_addr_state_other, validation.nameMax, validation.nameMin)) {
			    	return " State Other " + validation.isValidLengthMSG;
				}
			} 
			if (spouse_addr_district == null || spouse_addr_district.equals("0")) {
				return "Please Select the District";
			}
			if (spouse_addr_district_other != null && !spouse_addr_district_other.trim().equals("")) {
				if (!validation.isOnlyAlphabet(spouse_addr_district_other)) {
					return " District Other " + validation.isOnlyAlphabetMSG;
				}
			    if (!validation.isvalidLength(spouse_addr_district_other, validation.nameMax, validation.nameMin)) {
			    	return " District Other " + validation.isValidLengthMSG;
				}
			} 
			if (spouse_addr_tehsil == null || spouse_addr_tehsil.equals("0")) {
				return "Please Select the   Tehsil";
			}
			if (spouse_addr_tehsil_other != null && !spouse_addr_tehsil_other.trim().equals("")) {
				if (!validation.isOnlyAlphabet(spouse_addr_tehsil_other)) {
					return " Tehsil Other " + validation.isOnlyAlphabetMSG;
				}
			    if (!validation.isvalidLength(spouse_addr_tehsil_other, validation.nameMax, validation.nameMin)) {
			    	return " Tehsil Other " + validation.isValidLengthMSG;
				}
			} 
			if (spouse_addr_village == null || spouse_addr_village.equals("")) {
				return "Please Enter the  Village/Town/City";
			}
			if (!validation.isOnlyAlphabet(spouse_addr_village)) {
		 		return validation.isOnlyAlphabetMSG + " Village/Town/City";	
			}
			if (!validation.isvalidLength(spouse_addr_village,validation.nameMax,validation.nameMin)) {
		 		return "Village/Town/City " + validation.isValidLengthMSG;	
			}			
			if (spouse_addr_pin == null || spouse_addr_pin.equals("") || spouse_addr_pin.length() < 6) {
				return "Please Enter Valid Pin";
			}
			if (validation.isOnlyNumer(spouse_addr_pin) == true) {
		 		return validation.isOnlyNumerMSG + " Pin";	
			}
			if (!validation.isvalidLength(spouse_addr_pin,validation.pin_noMax,validation.pin_noMin)) {
		 		return "Pin " + validation.isValidLengthMSG;	
			}			
			if (spouse_addr_rail == null || spouse_addr_rail.equals("")) {
				return "Please Enter the  Nearest Railway Station";
			}
			if (!validation.isOnlyAlphabetNumeric(spouse_addr_rail)) {
		 		return validation.isOnlyAlphabetNumericMSG + " Nearest Railway Station";	
			}
			if (!validation.isvalidLength(spouse_addr_rail,validation.authorityMax,validation.authorityMin)) {
		 		return "Nearest Railway Station " + validation.isValidLengthMSG;	
			} 
			if (Stn_HQ_sus_no == null || Stn_HQ_sus_no.equals("")) {
				return "Please Enter the  Stn HQ SUS No";
			}
			if (!validation.isOnlyAlphabetNumeric(Stn_HQ_sus_no)) {
			 	return validation.isOnlyAlphabetNumericMSG + " Stn HQ SUS No";	
			 }
			 if (!validation.SusNoLength(Stn_HQ_sus_no)) {
				 return validation.SusNoMSG;	
			 }
	}


	else {
		spouse_addr_Country="0";
		spouse_addr_state="0";
		spouse_addr_district="0";
		spouse_addr_tehsil="0";
		spouse_addr_village=null;
		spouse_addr_pin="0";
		spouse_addr_rail=null;
		Stn_HQ_sus_no=null;
		spouse_addr_rural_urban=null;
	}

		//try {
			if (Integer.parseInt(addr_ch_id) == 0) {

				//TB_CENSUS_ADDRESS addr = (TB_CENSUS_ADDRESS)sessionhql.get(TB_CENSUS_ADDRESS.class, addr_ch_id);

				TB_CENSUS_ADDRESS addr = new TB_CENSUS_ADDRESS();

				addr.setPre_country(Integer.parseInt(pre_country));

				addr.setPre_state(Integer.parseInt(pre_domicile_state));

				addr.setPre_district(Integer.parseInt(pre_domicile_district));

				addr.setPre_tesil(Integer.parseInt(pre_domicile_tesil));

				addr.setAuthority(authority);

				addr.setDate_authority(auth_dt);

				

				addr.setPermanent_country(Integer.parseInt(per_home_addr_country));

				addr.setPermanent_district(Integer.parseInt(per_home_addr_district));

				addr.setPermanent_state(Integer.parseInt(per_home_addr_state));

				addr.setPermanent_tehsil(Integer.parseInt(per_home_addr_tehsil));

				addr.setPermanent_village(per_home_addr_village);

				addr.setPermanent_pin_code(Integer.parseInt(per_home_addr_pin));

				addr.setPermanent_near_railway_station(per_home_addr_rail);

				addr.setPermanent_rural_urban_semi(per_home_addr_rural_urban);

				addr.setPermanent_border_area(border_area);

				//pratiksha//
				addr.setPre_country_other(pre_country_other);
				addr.setPre_domicile_state_other(pre_domicile_state_other);
				addr.setPre_domicile_district_other(pre_domicile_district_other);
				addr.setPre_domicile_tesil_other(pre_domicile_tesil_other);
				addr.setPer_home_addr_country_other(per_home_addr_country_other);
				addr.setPer_home_addr_state_other(per_home_addr_state_other);
				addr.setPer_home_addr_district_other(per_home_addr_district_other);
				addr.setPer_home_addr_tehsil_other(per_home_addr_tehsil_other);
				addr.setPers_addr_country_other(pers_addr_country_other);
				addr.setPers_addr_state_other(pers_addr_state_other);
				addr.setPers_addr_district_other(pers_addr_district_other);
				addr.setPers_addr_tehsil_other(pers_addr_tehsil_other);
				addr.setPresent_country(Integer.parseInt(pers_addr_country));
				addr.setSpouse_addr_district_other(spouse_addr_district_other);
				addr.setSpouse_addr_state_other(spouse_addr_state_other);
				addr.setSpouse_addr_country_other(spouse_addr_country_other);
				addr.setSpouse_addr_tehsil_other(spouse_addr_tehsil_other);
				addr.setSpouse_rural_urban_semi(spouse_addr_rural_urban);
				addr.setPresent_district(Integer.parseInt(pers_addr_district));

				addr.setPresent_state(Integer.parseInt(pers_addr_state));

				addr.setPresent_tehsil(Integer.parseInt(pers_addr_tehsil));

				addr.setPresent_village(pers_addr_village);

				addr.setPresent_pin_code(Integer.parseInt(pers_addr_pin));

				addr.setPresent_near_railway_station(pers_addr_rail);

				addr.setPresent_rural_urban_semi(pers_addr_rural_urban);

              addr.setSpouse_country(Integer.parseInt(spouse_addr_Country));

				addr.setSpouse_district(Integer.parseInt(spouse_addr_district));

				addr.setSpouse_state(Integer.parseInt(spouse_addr_state));

				addr.setSpouse_tehsil(Integer.parseInt(spouse_addr_tehsil));

				addr.setSpouse_village(spouse_addr_village);

				addr.setSpouse_pin_code(Integer.parseInt(spouse_addr_pin));

				addr.setSpouse_near_railway_station(spouse_addr_rail);
				addr.setStn_hq_sus_no(Stn_HQ_sus_no);
				addr.setCreated_by(username);
				addr.setCreated_date(new Date());
				

				addr.setComm_id(comm_id);

				addr.setCen_id(Integer.parseInt(census_id));

				addr.setStatus(0);

				

				int id = (int) sessionhql.save(addr);

				msg = Integer.toString(id);

			} else {

				



				

				String hql = " update TB_CENSUS_ADDRESS set pre_country=:pre_country ,comm_id=:comm_id ,pre_state=:pre_state,"
                        + "pre_district=:pre_district,pre_tesil=:pre_tesil,authority=:authority,date_authority=:date_authority,"
                        + "permanent_country=:permanent_country ,permanent_district=:permanent_district ,permanent_state=:permanent_state,"
                        +  "permanent_tehsil=:permanent_tehsil,permanent_village=:permanent_village,permanent_pin_code=:permanent_pin_code,"
                        + "permanent_near_railway_station=:permanent_near_railway_station,permanent_rural_urban_semi=:permanent_rural_urban_semi,Permanent_border_area=:Permanent_border_area,"
                        + "present_country=:present_country ,present_district=:present_district ,present_state=:present_state,"
                        +  "present_tehsil=:present_tehsil,present_village=:present_village,present_pin_code=:present_pin_code,"
                        + "present_near_railway_station=:present_near_railway_station,present_rural_urban_semi=:present_rural_urban_semi,"
						+ "per_home_addr_country_other=:per_home_addr_country_other,per_home_addr_state_other=:per_home_addr_state_other,per_home_addr_district_other=:per_home_addr_district_other,per_home_addr_tehsil_other=:per_home_addr_tehsil_other,"
						+ "pre_country_other=:pre_country_other,pre_domicile_state_other=:pre_domicile_state_other,pre_domicile_district_other=:pre_domicile_district_other,pre_domicile_tesil_other=:pre_domicile_tesil_other,"
				        + "pers_addr_country_other=:pers_addr_country_other,pers_addr_state_other=:pers_addr_state_other,pers_addr_district_other=:pers_addr_district_other,pers_addr_tehsil_other=:pers_addr_tehsil_other,"
				        + "spouse_addr_district_other=:spouse_addr_district_other,spouse_addr_state_other=:spouse_addr_state_other,spouse_addr_country_other=:spouse_addr_country_other,spouse_addr_tehsil_other=:spouse_addr_tehsil_other";
				
				
				
				if(marital_status!=null && marital_status.equals("8") && check_spouse_addr!=null && check_spouse_addr.equals("on")) {

					hql += ",spouse_rural_urban_semi=:spouse_rural_urban_semi,spouse_state=:spouse_state,spouse_district=:spouse_district,spouse_village=:spouse_village,spouse_pin_code=:spouse_pin_code" 

							+",spouse_near_railway_station=:spouse_near_railway_station, stn_hq_sus_no=:stn_hq_sus_no, spouse_tehsil=:spouse_tehsil,spouse_country=:spouse_country";

				}
				
           hql += " ,modified_by=:modified_by,modified_date=:modified_date,status=:status"

						+ " where  id=:id";

				Query query = sessionhql.createQuery(hql).setInteger("pre_tesil", Integer.parseInt(pre_domicile_tesil))

						.setInteger("pre_country", Integer.parseInt(pre_country))

						.setInteger("pre_state", Integer.parseInt(pre_domicile_state))

						.setInteger("pre_district", Integer.parseInt(pre_domicile_district))

						.setTimestamp("date_authority", auth_dt)

						.setString("authority", authority)

						.setBigInteger("comm_id",new  BigInteger(request.getParameter("comm_id")))

						.setInteger("permanent_country", Integer.parseInt(per_home_addr_country))

						.setInteger("permanent_state", Integer.parseInt(per_home_addr_state))

						.setInteger("permanent_district", Integer.parseInt(per_home_addr_district))

						.setString("permanent_village", per_home_addr_village)

						.setInteger("permanent_pin_code", Integer.parseInt(per_home_addr_pin))

						.setString("permanent_near_railway_station", per_home_addr_rail)

						.setString("permanent_rural_urban_semi", per_home_addr_rural_urban)

						.setString("Permanent_border_area", border_area)

						.setInteger("permanent_tehsil",Integer.parseInt(per_home_addr_tehsil))

						.setInteger("present_country", Integer.parseInt(pers_addr_country))

						.setInteger("present_state", Integer.parseInt(pers_addr_state))

						.setInteger("present_district", Integer.parseInt(pers_addr_district))

						.setString("present_village", pers_addr_village)

						.setInteger("present_tehsil",Integer.parseInt( pers_addr_tehsil))

						.setInteger("present_pin_code", Integer.parseInt(pers_addr_pin))
						.setString("present_near_railway_station", pers_addr_rail)
						.setString("present_rural_urban_semi", pers_addr_rural_urban)
						.setString("per_home_addr_country_other", per_home_addr_country_other)
						.setString("per_home_addr_state_other", per_home_addr_state_other)
						.setString("per_home_addr_district_other", per_home_addr_district_other)
						.setString("per_home_addr_tehsil_other", per_home_addr_tehsil_other)
						.setString("pre_country_other", pre_country_other)
						.setString("pre_domicile_state_other", pre_domicile_state_other)
						.setString("pre_domicile_district_other", pre_domicile_district_other)
						.setString("pre_domicile_tesil_other", pre_domicile_tesil_other)
						.setString("pers_addr_country_other", pers_addr_country_other)
						.setString("pers_addr_state_other", pers_addr_state_other)
						.setString("pers_addr_district_other", pers_addr_district_other)
						.setString("pers_addr_tehsil_other", pers_addr_tehsil_other)
						.setString("spouse_addr_district_other", spouse_addr_district_other)
						.setString("spouse_addr_state_other", spouse_addr_state_other)
						.setString("spouse_addr_country_other", spouse_addr_country_other)
						.setString("spouse_addr_tehsil_other", spouse_addr_tehsil_other)
						
						.setString("modified_by", username)

						.setTimestamp("modified_date", new Date())

						.setInteger("status", 0)

						.setInteger("id",Integer.parseInt(request.getParameter("addr_ch_id")));

				

				if(marital_status!=null && marital_status.equals("8") && check_spouse_addr!=null && check_spouse_addr.equals("on")) {

					query.setInteger("spouse_state", Integer.parseInt(spouse_addr_state))

					.setInteger("spouse_district", Integer.parseInt(spouse_addr_district))

					.setString("spouse_village", spouse_addr_village)

					.setInteger("spouse_pin_code", Integer.parseInt(spouse_addr_pin))

					.setString("spouse_near_railway_station", spouse_addr_rail)
					.setString("stn_hq_sus_no", Stn_HQ_sus_no)

					.setInteger("spouse_tehsil", Integer.parseInt(spouse_addr_tehsil))

					.setInteger("spouse_country", Integer.parseInt(spouse_addr_Country))
					.setString("spouse_rural_urban_semi", spouse_addr_rural_urban);

				}

									

				msg = query.executeUpdate() > 0 ? "update" : "0";

				//Mmap.put("msg", "Data Updated Successfully");



			}
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				
			}
			else
			{
				mcommon.update_offr_status(Integer.parseInt(census_id),username);
			}
			

			tx.commit();

		/*	}catch (RuntimeException e) {

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
*/
			///sessionhql.close();



		return msg;

	}







	@RequestMapping(value = "/admin/address_details_GetData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_ADDRESS> address_details_GetData( HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("census_id"));

		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

	

		String hqlUpdate = " from TB_CENSUS_ADDRESS where cen_id=:census_id and comm_id=:comm_id and  status = '0'";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);

		List<TB_CENSUS_ADDRESS> list = (List<TB_CENSUS_ADDRESS>) query.list();

		tx.commit();

		sessionHQL.close();

		

		return list;

	}

	

	  /*--------------------- For Approval ----------------------------------*/

	

	public @ResponseBody List<TB_CENSUS_ADDRESS> address_details_GetData1(int id,BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

	

		String hqlUpdate = " from TB_CENSUS_ADDRESS where cen_id=:census_id and comm_id=:comm_id and  status = '0'";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);

		List<TB_CENSUS_ADDRESS> list = (List<TB_CENSUS_ADDRESS>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	

	public String Update_Change_Add(TB_CENSUS_ADDRESS obj,String username){

	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

	    Transaction tx = sessionHQL.beginTransaction();

	  

		  String msg = "";

		  String msg1 = "";

		  try{

			    String hql1 = "update TB_CENSUS_ADDRESS set status=:status where cen_id=:census_id and comm_id=:comm_id and (status != '0' and status != '-1') ";

			  

				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("census_id", obj.getCen_id())

						.setBigInteger("comm_id",obj.getComm_id());

						

				msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

				 

			

				  

			       String hql = "update TB_CENSUS_ADDRESS set modified_by=:modified_by,modified_date=:modified_date,status=:status  "

						+ " where cen_id=:census_id and comm_id=:comm_id and status = '0'";

			  

				   Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())

						.setInteger("status", 1).setInteger("census_id", obj.getCen_id()).setBigInteger("comm_id",obj.getComm_id());

			

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
 /*--------------------- For REJECT ----------------------------------*/
 	
    @RequestMapping(value = "/admin/getChangeAddressReject", method = RequestMethod.POST)
    public @ResponseBody String getChangeAddressReject(ModelMap Mmap, HttpSession session,HttpServletRequest request,
    		@RequestParam(value = "msg", required = false) String msg) throws ParseException {
 	   
 	 
    	     String username = session.getAttribute("username").toString();
    	     TB_CENSUS_ADDRESS Chngaddr = new TB_CENSUS_ADDRESS();
    	     Chngaddr.setCen_id(Integer.parseInt(request.getParameter("census_id")));
    	     Chngaddr.setComm_id(new BigInteger(request.getParameter("comm_id")));
    	     Chngaddr.setId(Integer.parseInt(request.getParameter("addr_ch_id")));
    	     Chngaddr.setReject_remarks(request.getParameter("reject_remarks"));
    			String msg1 = Change_Address_Reject(Chngaddr, username);
    			
    		  return msg1;
          
    }

public String Change_Address_Reject(TB_CENSUS_ADDRESS obj,String username){
	
  Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
  Transaction tx = sessionHQL.beginTransaction();

	  String msg = "";
	 try{
//		      String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//		  
//			   Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username).setTimestamp("modified_date", new Date())
//					.setInteger("update_ofr_status", 3).setInteger("census_id", obj.getCen_id()).setInteger("comm_id",obj.getComm_id());
//					
//			   msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." :"Data Not Rejected.";
			 
	  	 
		      String hql = "update TB_CENSUS_ADDRESS set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks    "
					+ " where cen_id=:census_id and comm_id=:comm_id and status = '0' and id=:id ";
		  
			  Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setTimestamp("modified_date", new Date())
					.setInteger("status", 3).setInteger("census_id", obj.getCen_id()).setBigInteger("comm_id",obj.getComm_id())
					.setString("reject_remarks", obj.getReject_remarks()).setInteger("id", obj.getId());
		
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
	public @ResponseBody List<TB_CENSUS_ADDRESS> address_details_GetData2(int id,BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String hqlUpdate = " from TB_CENSUS_ADDRESS where cen_id=:census_id and comm_id=:comm_id and  status = '3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_ADDRESS> list = (List<TB_CENSUS_ADDRESS>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/address_details_GetData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_ADDRESS> address_details_GetData3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
	
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("census_id"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_ADDRESS where cen_id=:census_id and comm_id=:comm_id and  status = '3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_ADDRESS> list = (List<TB_CENSUS_ADDRESS>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	 @RequestMapping(value = "/admin/Changeaddress_details_delete_action", method = RequestMethod.POST)
		public @ResponseBody String Changeaddress_details_delete_action(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				String hqlUpdate = "delete from TB_CENSUS_ADDRESS where id=:id";
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

