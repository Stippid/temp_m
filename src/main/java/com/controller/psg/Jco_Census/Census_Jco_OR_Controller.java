package com.controller.psg.Jco_Census;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.Assets.interUnitTransf_DAO;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Jco_Census.Search_JCOsDao;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_SIBLINGS;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FAMILY_MARRIED_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_SPOUSE_QUALIFICATION_JCO;
import com.models.psg.Jco_Update_JcoData.TB_MEDICAL_CATEGORY_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Census_Jco_OR_Controller {

	
	ValidationController validation = new ValidationController();
	Psg_CommonController pcommon = new Psg_CommonController();
	psg_jco_CommonController jcom = new psg_jco_CommonController();
	//CommanController com = new CommanController();
	AllMethodsControllerOrbat com = new AllMethodsControllerOrbat();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private Search_JCOsDao SJDAO;

	@RequestMapping(value = "/admin/JCOS_ORURL",method = RequestMethod.GET)
	public ModelAndView JCOS_ORURL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("JCOS_ORURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");

		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("msg", msg);
		Mmap.put("getArmyType", pcommon.getArmyType());
		Mmap.put("getReligionList", pcommon.getReligionList());
		Mmap.put("getMarital_statusList", pcommon.getMarital_statusList());
		Mmap.put("getBloodList", pcommon.getBloodList());
		Mmap.put("getRelationList", pcommon.getRelationList_JCO());
		Mmap.put("getMedDistrictName", pcommon.getMedDistrictName("", session));
		Mmap.put("getMedStateName", pcommon.getMedStateName("", session));
		Mmap.put("getMedCountryName", pcommon.getMedCountryName("", session));
		Mmap.put("getNationalityList", pcommon.getNationalityList());
		Mmap.put("getMedCatList", pcommon.getMedCatList());
		Mmap.put("getParentArmList", pcommon.getParentArmList());
		Mmap.put("getMothertoungeList", pcommon.getMothertoungeList());
		Mmap.put("getHeight", pcommon.getHeight());
		Mmap.put("getFamily_siblings", pcommon.getFamily_siblings());
		Mmap.put("getMedCityName", pcommon.getMedCityName("", session));
		Mmap.put("getProfession", pcommon.getProfession());
		Mmap.put("getGenderList", pcommon.getGenderList());
		Mmap.put("getTypeofRankJcoList", pcommon.getTypeofRankJcoList());
		Mmap.put("getTypeofTradeList", pcommon.getTypeofTradeList());
		Mmap.put("getExservicemenList", pcommon.getExservicemenList());
		Mmap.put("getClass_payList", jcom.getClass_payList());
		Mmap.put("getPaygroupList", jcom.getPaygroupList());
		Mmap.put("getCategory_jCOList", jcom.getCategory_jCOList());
		Mmap.put("getClass_enrollList", jcom.getClass_enrollList());
		Mmap.put("getRankjcoList", jcom.getRankjcoList());
		Mmap.put("getTradeJcoList", jcom.getTradeJcoList());
		Mmap.put("roleSusNo", roleSusNo);
        Mmap.put("getQualificationTypeList", pcommon.getQualificationTypeList());
		Mmap.put("getLanguageList", pcommon.getLanguageList());
		Mmap.put("getSpecializationList", pcommon.getSpecializationList());
		Mmap.put("getDivclass", pcommon.getDivclass());
		Mmap.put("getSubject", pcommon.getSubject());
		Mmap.put("getCommInstitute", pcommon.getInstitute("1"));
		Mmap.put("getTrainingInstitute", pcommon.getInstitute("2"));
		Mmap.put("getRank_intakeList", jcom.getRank_intakeList());
		Mmap.put("getclass_domicileList", jcom.getclass_domicileList());
		// medical
		Mmap.put("getShapeStatusList", pcommon.getShapeStatusList());
		Mmap.put("getcCopeList", pcommon.getCopeValueList("C"));
		Mmap.put("getoCopeList", pcommon.getCopeValueList("O"));
		Mmap.put("getpCopeList", pcommon.getCopeValueList("P"));
		Mmap.put("geteCopeList", pcommon.getCopeValueList("E"));
		Mmap.put("getesubCopeList", pcommon.getCopeValueList("E Sub Value"));
		//end medical

		
		if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", com.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionUserId).get(0));
		}
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, sessionUserId));
		return new ModelAndView("JCOs_ORTiles");
	}

	@RequestMapping(value = "/jco_Personnel_Detail_action_old", method = RequestMethod.POST)
	public ModelAndView jco_Personnel_Detail_action_old(
			@ModelAttribute("jco_Personnel_DetailCMD") TB_CENSUS_JCO_OR_PARENT jco, BindingResult result,
			HttpServletRequest request, ModelMap Mmap, HttpSession session, String msg) throws ParseException {

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("JCOS_ORURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");

		}
		int siblings_count = Integer.parseInt(request.getParameter("siblings_count"));
		int siblingsOld_count = Integer.parseInt(request.getParameter("siblingsOld_count"));

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		String username = session.getAttribute("username").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Date sib_dt = null;
		Date tos_dt = null;
		Date birth_dt = null;
		Date atts_dt = null;
		Date dt_ser = null;
		//Date f_dt = null;
		//Date m_dt = null;
		Date en_dt = null;
		Date dt_rank = null;
		//String adhar_numbers = "";
		//String adhar_number = "";
		String date_attestation = request.getParameter("date_of_attestation");
		String birth_date = request.getParameter("date_of_birth");
		String enrl_date = request.getParameter("enroll_dt");
		String tos_date = request.getParameter("date_of_tos");
		String date_of_rank = request.getParameter("date_of_rank");
		String date_ser = request.getParameter("date_of_seniority");
		/*String f_dob = request.getParameter("father_dob");
		String m_dob = request.getParameter("mother_dob");*/
/*		if (tos_date != null && !tos_date.trim().equals("") && !tos_date.equals("DD/MM/YYYY")) {
			tos_dt = format.parse(tos_date);
		}
		if (birth_date != null && !birth_date.trim().equals("") && !birth_date.equals("DD/MM/YYYY")) {
			birth_dt = format.parse(birth_date);
		}

		if (date_attestation != null && !date_attestation.trim().equals("") && !date_attestation.equals("DD/MM/YYYY")) {
			atts_dt = format.parse(date_attestation);

		}
		if (enrl_date != null && !enrl_date.trim().equals("") && !enrl_date.equals("DD/MM/YYYY")) {
			en_dt = format.parse(enrl_date);
		}

		if (date_ser != null && !date_ser.trim().equals("") && !date_ser.equals("DD/MM/YYYY")) {
			dt_ser = format.parse(date_ser);
		}
		if (f_dob != null && !f_dob.trim().equals("") && !f_dob.equals("DD/MM/YYYY")) {
			f_dt = format.parse(f_dob);
		}
		if (m_dob != null && !m_dob.trim().equals("") && !m_dob.equals("DD/MM/YYYY")) {
			m_dt = format.parse(m_dob);
		}*/
		
		String class_pay = request.getParameter("class_pay");
		String pay_group = request.getParameter("pay_group");
		String unit_name = request.getParameter("unit_name");
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String merge_full = first_name + " " + middle_name + " " + last_name;
		/*String pl_birth = request.getParameter("place_of_birth");
		String d_birth = request.getParameter("district_of_birth_hid");
		String s_birth = request.getParameter("state_of_birth_hid");
		String coun_birth = request.getParameter("country_of_birth_hid");
		String nationality_hid = request.getParameter("nationality_hid");*/
		String religion_hid = request.getParameter("religion_hid");
		String mother_tongue_hid = request.getParameter("mother_tongue_hid");
		
		/*String district_of_birth = request.getParameter("district_of_birth");
		String state_of_birth = request.getParameter("state_of_birth");
		String country_of_birth = request.getParameter("country_of_birth");*/
		String gender_hid = request.getParameter("gender_hid");
		String gender_other = request.getParameter("gender_other");
		
		String rank = request.getParameter("rank");
		/*String nat = request.getParameter("nationality");*/
		String reli = request.getParameter("religion");
		String blood = request.getParameter("blood_group");
		String domicile = request.getParameter("domicile");
		/*String h = request.getParameter("height");*/
		//String mar_status = request.getParameter("marital_status");
		String mt = request.getParameter("mother_tongue");
		String trade = request.getParameter("trade");
		/*String pan_no = request.getParameter("pan_no");*/
		String arm_ser = request.getParameter("arm_service");
		String regiment = request.getParameter("regiment");
		String unit_sus_no = request.getParameter("unit_sus_no");
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (!roleAccess.equals("Unit")) {

		if(unit_sus_no == null || unit_sus_no.equals("null") || unit_sus_no.equals("")) {
				Mmap.put("msg", "Please Enter Unit Sus No.");
				return new ModelAndView("redirect:JCOS_ORURL");
			} 
		 if (!validation.isOnlyAlphabetNumeric(unit_sus_no)) {
             Mmap.put("msg", validation.isOnlyAlphabetNumericMSG + "Unit Sus No");
		     return new ModelAndView("redirect:JCOS_ORURL");
            }
		 if (!validation.SusNoLength(unit_sus_no)) {
             Mmap.put("msg", validation.SusNoMSG);
		     return new ModelAndView("redirect:JCOS_ORURL");
            }
		else {
				roleSusNo = unit_sus_no;
			}
		
		if(unit_name == null || unit_name.equals("null") || unit_name.equals("")) {
			Mmap.put("msg", "Please Enter Unit Name.");
			return new ModelAndView("redirect:JCOS_ORURL");
		} 

		}
		/*String adhar_number1 = request.getParameter("adhar_number1");
		String adhar_number2 = request.getParameter("adhar_number2");
		String adhar_number3 = request.getParameter("adhar_number3");
		String Aadhar = adhar_number1 + adhar_number2 + adhar_number3;*/
		/*if (Aadhar != null && !Aadhar.equals("")) {
			adhar_number = new BigInteger(Aadhar);
		}*/

		/*String isfatherInservice = request.getParameter("father_proff_radio");
		String ismotherInservice = request.getParameter("mother_proff_radio");
		String mother_personal_no = request.getParameter("mother_personal_no");
		String father_personal_no = request.getParameter("father_personal_no");
		String mother_other = request.getParameter("mother_other");
		String father_other = request.getParameter("father_other");
		String mother_service = request.getParameter("mother_service");
		String father_service = request.getParameter("father_service");
		String father_profession = request.getParameter("father_profession");
		String mother_profession = request.getParameter("mother_profession");
		String father_proffother = request.getParameter("father_proffother");
		String mother_proffother = request.getParameter("mother_proffother");*/
		
		String army_no1 = request.getParameter("army_no1");
		String army_no2 = request.getParameter("army_no2");
		String army_no3 = request.getParameter("army_no3");
		
		
		String country_other = request.getParameter("country_other");
		String state_other = request.getParameter("state_other");
		String district_other = request.getParameter("district_other");
		String nationality_other = request.getParameter("nationality_other");
		String religion_other = request.getParameter("religion_other");
		String mother_tongue_other = request.getParameter("mother_tongue_other");
		
		/*if (isfatherInservice.equals("yes")) {
			father_profession = "0";
		
			if (father_personal_no.trim().equals("")) {
				father_personal_no = null;
			}
			if (father_service.equals("9")) {
				father_personal_no = null;
			} else {
				father_other = null;
			}
		} else {
			
			father_personal_no = null;
			father_other = null;
			father_service = null;
			if (request.getParameter("father_profession").equals("OTHERS")) {
				father_other = father_proffother;
			}
		}
		
		
		if (ismotherInservice.equals("yes")) {
			mother_profession = "0";
			
			if (mother_personal_no.trim().equals("")) {
				mother_personal_no = null;
			}
			if (mother_service.equals("9")) {
				mother_personal_no = null;
			} else {
				mother_other = null;
			}
		} else {
			
			mother_personal_no = null;
			mother_other = null;
			mother_service = null;
			if (request.getParameter("mother_profession").equals("OTHERS")) {
				mother_other = mother_proffother;
			}
		}
		*/
		if (request.getParameter("category") == null || request.getParameter("category").trim().equals("0")) {
			Mmap.put("msg", "Please Select Category");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (request.getParameter("category").equals("JCO")) {
			if (army_no1 == null || army_no1.trim().equals("0")) {
				Mmap.put("msg", " Please Select Army No");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (army_no2 == null || army_no2.trim().equals("")) {
				Mmap.put("msg", " Please Enter Army No");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
		} else {
			if (army_no2 == null || army_no2.trim().equals("")) {
				Mmap.put("msg", " Please Enter Army No");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
		}
	
         if (validation.isOnlyNumer(army_no2) == true) {
			Mmap.put("msg", validation.isOnlyNumerMSG  + " Army No");
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		if (army_no2.length() < 1 || army_no2.length() > 9) {
			Mmap.put("msg", "Please Enter Valid Army No");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (first_name == null || first_name.trim().equals("")) {
			Mmap.put("msg", "Please Enter First Name" );
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (validation.isOnlyAlphabet(first_name) == false) {
			Mmap.put("msg", "First Name "+ validation.isOnlyAlphabetMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		if (!validation.isvalidLength(first_name,validation.nameMax,validation.nameMin)) {
			Mmap.put("msg", "First Name " + validation.isValidLengthMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
		}

		if (request.getParameter("gender") == null || request.getParameter("gender").trim().equals("0")) {
			Mmap.put("msg", "Please Select Gender");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (gender_hid != null && gender_hid.equals("OTHER")) {
			if (gender_other == null || gender_other.trim().equals("")) {
			
				Mmap.put("msg", "Please Enter Other Gender");
			
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (validation.isOnlyAlphabet(gender_other) == false) {
				Mmap.put("msg", "Other Gender "+ validation.isOnlyAlphabetMSG);
		
			    return new ModelAndView("redirect:JCOS_ORURL");
		     }
			if (!validation.isvalidLength(gender_other,validation.nameMax,validation.nameMin)) {
				Mmap.put("msg", "Other Gender " + validation.isValidLengthMSG);
				
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
			}
		if (request.getParameter("rank") == null || request.getParameter("rank").trim().equals("0")) {
			Mmap.put("msg", "Please Select Rank");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (request.getParameter("rank").equals("17")) {
			if (request.getParameter("rank_intake") == null || request.getParameter("rank_intake").trim().equals("0")) {
				Mmap.put("msg", "Please Select Rank Intake");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
		}
		
		if (birth_date == null || birth_date.trim().equals("") || birth_date.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Select Date of Birth");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
			else  if (!validation.isValidDate(birth_date)){
					Mmap.put("msg", validation.isValidDateMSG  + " of Birth");
				    return new ModelAndView("redirect:JCOS_ORURL");
			     }
		 else {
			birth_dt = format.parse(birth_date);
		}
		
	
		/*if (pl_birth == null || pl_birth.trim().equals("")) {
			Mmap.put("msg", "Please Enter Place of Birth");
			return new ModelAndView("redirect:JCOS_ORURL");
		}*/
		/*if (validation.isOnlyAlphabet(pl_birth) == false) {
			Mmap.put("msg","Place of Birth "+ validation.isOnlyAlphabetMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }*/
		/*if (!validation.isvalidLength(pl_birth,validation.nameMax,validation.nameMin)) {
			Mmap.put("msg", "Place of Birth " + validation.isValidLengthMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
		}*/
		/*if (country_of_birth == null || country_of_birth.equals("0")) {
			Mmap.put("msg", "Please Select Country of Birth");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (coun_birth != null && coun_birth.equals("OTHERS")) {
			if (country_other == null || country_other.trim().equals("")) {
			
				Mmap.put("msg", "Please Enter Other Country of Birth");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (validation.isOnlyAlphabet(country_other) == false) {
				Mmap.put("msg", "Other Country of Birth "+ validation.isOnlyAlphabetMSG);
			
			    return new ModelAndView("redirect:JCOS_ORURL");
		     }
			if (!validation.isvalidLength(country_other,validation.nameMax,validation.nameMin)) {
				Mmap.put("msg", "Other Country of Birth " + validation.isValidLengthMSG);
				
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
			}*/
		/*if (state_of_birth == null || state_of_birth.equals("0")) {
			Mmap.put("msg", "Please Select State of Birth");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		
		if (s_birth != null && s_birth.equals("OTHERS")) {
			if (state_other == null || state_other.trim().equals("")) {
			
				Mmap.put("msg", "Please Enter Other State of Birth");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (validation.isOnlyAlphabet(state_other) == false) {
				Mmap.put("msg", "Other State of Birth "+ validation.isOnlyAlphabetMSG);
			
			    return new ModelAndView("redirect:JCOS_ORURL");
		     }
			if (!validation.isvalidLength(state_other,validation.nameMax,validation.nameMin)) {
				Mmap.put("msg", "Other State of Birth " + validation.isValidLengthMSG);
				
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
			}
		if (district_of_birth == null || district_of_birth.equals("0")) {
			Mmap.put("msg", "Please Select District of Birth");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (d_birth != null && d_birth.equals("OTHERS")) {
			if (district_other == null || district_other.trim().equals("")) {
			
				Mmap.put("msg", "Please Enter Other District of Birth");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (validation.isOnlyAlphabet(district_other) == false) {
				Mmap.put("msg", "Other District of Birth "+ validation.isOnlyAlphabetMSG);
			
			    return new ModelAndView("redirect:JCOS_ORURL");
		     }
			if (!validation.isvalidLength(district_other,validation.nameMax,validation.nameMin)) {
				Mmap.put("msg", "Other District of Birth " + validation.isValidLengthMSG);
				
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
			}*/
		/*if (nat == null || nat.equals("0")) {
			Mmap.put("msg", "Please Select Nationality");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (nationality_hid != null && nationality_hid.equals("OTHERS")) {
			if (nationality_other == null || nationality_other.trim().equals("")) {
			
				Mmap.put("msg", "Please Enter Other Nationality");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (validation.isOnlyAlphabet(nationality_other) == false) {
				Mmap.put("msg", "Other Nationality "+ validation.isOnlyAlphabetMSG);
			    return new ModelAndView("redirect:JCOS_ORURL");
		     }
			if (!validation.isvalidLength(nationality_other,validation.nameMax,validation.nameMin)) {
				Mmap.put("msg", "Other Nationality " + validation.isValidLengthMSG);
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
			}*/
		if (reli == null || reli.equals("0")) {
			Mmap.put("msg", "Please Select Religion");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (religion_hid != null && religion_hid.equals("OTHERS")) {
			if (religion_other == null || religion_other.trim().equals("")) {
			
				Mmap.put("msg", "Please Enter Other Religion");
			
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (validation.isOnlyAlphabet(religion_other) == false) {
				Mmap.put("msg", "Other Religion "+ validation.isOnlyAlphabetMSG);
		
			    return new ModelAndView("redirect:JCOS_ORURL");
		     }
			if (!validation.isvalidLength(religion_other,validation.nameMax,validation.nameMin)) {
				Mmap.put("msg", "Other Religion " + validation.isValidLengthMSG);
				
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
			}
		if (mt == null || mt.equals("0")) {
			Mmap.put("msg", "Please Select Mother Tongue");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (mother_tongue_hid != null && mother_tongue_hid.equals("OTHERS")) {
			if (mother_tongue_other == null || mother_tongue_other.trim().equals("")) {
			
				Mmap.put("msg", "Please Enter Other Mother Tongue");
				
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (validation.isOnlyAlphabet(mother_tongue_other) == false) {
				Mmap.put("msg", "Other Mother Tongue "+ validation.isOnlyAlphabetMSG);
			
			    return new ModelAndView("redirect:JCOS_ORURL");
		     }
			if (!validation.isvalidLength(mother_tongue_other,validation.nameMax,validation.nameMin)) {
				Mmap.put("msg", "Other Mother Tongue " + validation.isValidLengthMSG);
				
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
			}
		/*if (mar_status == null || mar_status.equals("0")) {
			Mmap.put("msg", "Please Select Marital Status");
			return new ModelAndView("redirect:JCOS_ORURL");
		}*/
		if (blood == null || blood.equals("0")) {
			Mmap.put("msg", "Please Select Blood Group");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		/*if (h == null || h.equals("0")) {
			Mmap.put("msg", "Please Select Height");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (adhar_number1 == null || adhar_number1.trim().equals("")) {
			Mmap.put("msg", " Please Enter Aadhaar No");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (adhar_number2 == null || adhar_number2.trim().equals("")) {
			Mmap.put("msg", " Please Enter Aadhaar No");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (adhar_number3 == null || adhar_number3.trim().equals("")) {
			Mmap.put("msg", " Please Enter Aadhaar No");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (validation.isValidAadhar(Aadhar) == false) {
			Mmap.put("msg", validation.isValidAadharMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }*/
        /*	if (validation.isOnlyNumer(Aadhar) == true) {
			Mmap.put("msg", validation.isOnlyNumerMSG  + " Aadhar No");
		    return new ModelAndView("redirect:JCOS_ORURL");
		 }
	     else
	     {
	    	 adhar_number = Aadhar;
	     }
		/*if (pan_no == null || pan_no.trim().equals("")) {
			Mmap.put("msg", " Please Enter Pan No");
			return new ModelAndView("redirect:JCOS_ORURL");
		}

       if (validation.isValidPan(pan_no) == false) {
			Mmap.put("msg", validation.isValidPanMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }*/
	/*	if (request.getParameter("class_enroll") == null || request.getParameter("class_enroll").equals("0")) {
			Mmap.put("msg", "Please Select Class For Enrollment");
			return new ModelAndView("redirect:JCOS_ORURL");
		}*/
		if (class_pay == null || class_pay.equals("0")) {
			Mmap.put("msg", "Please Select Class(Pay)");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (pay_group == null || pay_group.equals("0")) {
			Mmap.put("msg", "Please Select Pay Group");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (request.getParameter("record_office_sus") == null || request.getParameter("record_office_sus").trim().equals("")) {
			Mmap.put("msg", "Please Enter Record Office(SUS No) ");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		 if (!validation.isOnlyAlphabetNumeric(request.getParameter("record_office_sus"))) {
             Mmap.put("msg", validation.isOnlyAlphabetNumericMSG + "Record Office Sus");
		     return new ModelAndView("redirect:JCOS_ORURL");
            }
		 if (!validation.SusNoLength(request.getParameter("record_office_sus"))) {
             Mmap.put("msg", validation.SusNoMSG);
		     return new ModelAndView("redirect:JCOS_ORURL");
            }
		/*if (request.getParameter("record_office") == null || request.getParameter("record_office").trim().equals("")) {
			Mmap.put("msg", "Please Enter Record Office");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
	*/
		if (enrl_date == null || enrl_date.trim().equals("") || enrl_date.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Select Date of Enrollment");
			return new ModelAndView("redirect:JCOS_ORURL");
		} 
		else  if (!validation.isValidDate(enrl_date)){
			Mmap.put("msg", validation.isValidDateMSG  + " of Enrollment");
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		else {
			en_dt = format.parse(enrl_date);
		}
		//260194
		
		if (!request.getParameter("rank").equals("17")) {
			if (date_attestation == null || date_attestation.trim().equals("") || date_attestation.equals("DD/MM/YYYY")) {
				Mmap.put("msg", "Please Select Date of Attestation");
				return new ModelAndView("redirect:JCOS_ORURL");
			} 
		}
		if (!date_attestation.trim().equals(null) && !date_attestation.trim().equals("") & !date_attestation.equals("DD/MM/YYYY")) { 
			 if (!validation.isValidDate(date_attestation)){
				    Mmap.put("msg", validation.isValidDateMSG  + " of Attestation");
				    return new ModelAndView("redirect:JCOS_ORURL");
			     }
			atts_dt = format.parse(date_attestation);
		}
		
		
			
		if (pcommon.calculate_age(birth_dt, en_dt) < 16) {
			Mmap.put("msg", "Please enter age above 16");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (date_ser == null || date_ser.trim().equals("") || date_ser.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Select Date of Seniority");
			return new ModelAndView("redirect:JCOS_ORURL");
		} 
		else  if (!validation.isValidDate(date_ser)){
			Mmap.put("msg", validation.isValidDateMSG  + " of Seniority");
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		else {
			dt_ser = format.parse(date_ser);
		}
		if (pcommon.calculate_age(birth_dt, dt_ser) < 16) {
			Mmap.put("msg", "Please enter age above 16");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (trade == null || trade.equals("0")) {
			Mmap.put("msg", " Please Enter Trade");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (tos_date == null || tos_date.trim().equals("") || tos_date.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Select Date of TOS");
			return new ModelAndView("redirect:JCOS_ORURL");
		} 
		else  if (!validation.isValidDate(tos_date)){
			Mmap.put("msg", validation.isValidDateMSG  + " of Tos");
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		else {
			tos_dt = format.parse(tos_date);
		}
		
		
		
		if (arm_ser == null || arm_ser.equals("0")) {
			Mmap.put("msg", "Please Select Arm/Services");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		
		if(arm_ser.equals("0700") || arm_ser.equals("0800")) {
			if(regiment==null || regiment.trim().equals("") || regiment.equals("0")) {
				Mmap.put("msg", "Please Select Regiment");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
		}

   
		if (request.getParameter("father_name") == null || request.getParameter("father_name").trim().equals("")) {
			Mmap.put("msg", "Please Select Father's Name");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (validation.isOnlyAlphabet(request.getParameter("father_name")) == false) {
			Mmap.put("msg","Father's Name "+ validation.isOnlyAlphabetMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		if (!validation.isvalidLength(request.getParameter("father_name"),validation.nameMax,validation.nameMin)) {
			Mmap.put("msg", "Father's Name " + validation.isValidLengthMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
		}
		/*if (f_dob == null || f_dob.trim().equals("") || f_dob.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Select Father's Date of Birth");
			return new ModelAndView("redirect:JCOS_ORURL");
		} 
		else  if (!validation.isValidDate(f_dob)){
			Mmap.put("msg", validation.isValidDateMSG  + " of Father DOB");
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		else {
			f_dt = format.parse(f_dob);
		}
		if (request.getParameter("father_place_birth") == null || request.getParameter("father_place_birth").equals("")) {
			Mmap.put("msg", "Please Select Father's Place of Birth");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (validation.isOnlyAlphabet(request.getParameter("father_place_birth")) == false) {
			Mmap.put("msg","Father's Place of Birth "+ validation.isOnlyAlphabetMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		if (!validation.isvalidLength(request.getParameter("father_place_birth"),validation.nameMax,validation.nameMin)) {
			Mmap.put("msg", "Father's Place of Birth " + validation.isValidLengthMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (isfatherInservice.equals("yes")) {	
			if (request.getParameter("father_service") == null || request.getParameter("father_service").equals("0")) {
				Mmap.put("msg", "Please Select Father's Services");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (!request.getParameter("father_service").equals("9")) {
			if (request.getParameter("father_personal_no") == null || request.getParameter("father_personal_no").equals("")) {
				Mmap.put("msg", "Please Select Father's Personal No");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (!validation.isOnlyAlphabetNumeric(request.getParameter("father_personal_no"))) {
		 		Mmap.put("msg", "Father's Personal No " + validation.isOnlyAlphabetNumericMSG);
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (!validation.isvalidLength(request.getParameter("father_personal_no"),validation.nameMax,validation.nameMin)) {
				Mmap.put("msg", "Father's Personal No " + validation.isValidLengthMSG);
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
		}
		}
		if (isfatherInservice.equals("no")) {	
			if (request.getParameter("father_profession") == null || request.getParameter("father_profession").equals("0")) {
				Mmap.put("msg", "Please Select Father's Profession");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
		}*/
		if (request.getParameter("mother_name") == null || request.getParameter("mother_name").trim().equals("")) {
			Mmap.put("msg", "Please Select Mother's Name");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (validation.isOnlyAlphabet(request.getParameter("mother_name")) == false) {
			Mmap.put("msg","Mother's Name "+ validation.isOnlyAlphabetMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		if (!validation.isvalidLength(request.getParameter("mother_name"),validation.nameMax,validation.nameMin)) {
			Mmap.put("msg", "Mother's Name " + validation.isValidLengthMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
		}
		/*if (m_dob == null || m_dob.trim().equals("") || m_dob.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Select Mother's Date of Birth");
			return new ModelAndView("redirect:JCOS_ORURL");
		} 
		else  if (!validation.isValidDate(m_dob)){
			Mmap.put("msg", validation.isValidDateMSG  + " of Mother DOB");
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }	
		else {
			m_dt = format.parse(m_dob);
		}*/
		/*if (request.getParameter("mother_place_birth") == null || request.getParameter("mother_place_birth").equals("")) {
			Mmap.put("msg", "Please Select Mother's Place of Birth");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (validation.isOnlyAlphabet(request.getParameter("mother_place_birth")) == false) {
			Mmap.put("msg","Mother's Place of Birth "+ validation.isOnlyAlphabetMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		if (!validation.isvalidLength(request.getParameter("mother_place_birth"),validation.nameMax,validation.nameMin)) {
			Mmap.put("msg", "Mother's Place of Birth" + validation.isValidLengthMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (ismotherInservice.equals("yes")) {	
			if (request.getParameter("mother_service") == null || request.getParameter("mother_service").equals("0")) {
				Mmap.put("msg", "Please Select Mother's Services");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (!request.getParameter("mother_service").equals("9")) {
			if (request.getParameter("mother_personal_no") == null || request.getParameter("mother_personal_no").equals("")) {
				Mmap.put("msg", "Please Select Mother's Personal No");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (!validation.isOnlyAlphabetNumeric(request.getParameter("mother_personal_no"))) {
		 		Mmap.put("msg", "Mother's Personal No " + validation.isOnlyAlphabetNumericMSG);
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (!validation.isvalidLength(request.getParameter("mother_personal_no"),validation.nameMax,validation.nameMin)) {
				Mmap.put("msg", "Mother's Personal No" + validation.isValidLengthMSG);
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
			
		}
		}
		if (ismotherInservice.equals("no")) {	
			if (request.getParameter("mother_profession") == null || request.getParameter("mother_profession").equals("0")) {
				Mmap.put("msg", "Please Select Mother's Profession");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
		}
	
		

		
	    }*/
		
		if (date_of_rank == null || date_of_rank.trim().equals("") || date_of_rank.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Select Date of Rank");
			return new ModelAndView("redirect:JCOS_ORURL");
		} 
		else  if (!validation.isValidDate(date_of_rank)){
			Mmap.put("msg", validation.isValidDateMSG  + " of Rank");
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		else {
			
			if(format.parse(date_of_rank).before(en_dt)) {
				Mmap.put("msg", "Please Select Date of Rank Greater than Enrollment Date");
			    return new ModelAndView("redirect:JCOS_ORURL");
			}else {
			dt_rank = format.parse(date_of_rank);
			}
		}
		
		int id = jco.getId() > 0 ? jco.getId() : 0;
		try {
			
			String armsuffix = generate_army_no(army_no2);
			
			/*if(!armsuffix.equals(army_no3)) {
	            Mmap.put("msg", "Please Enter Valid Army No.");
	            return new ModelAndView("redirect:JCOS_ORURL");
			}*/
			if(request.getParameter("category").equals("OR"))
			{
				army_no1="";
			}
			String army_no = army_no1 + army_no2 + armsuffix;
			
			Boolean d = SJDAO.getArmyNoAlreadyExist(army_no,String.valueOf(id),null);
			
			if (id == 0) {
				jco.setArmy_no(army_no);
				jco.setFirst_name(first_name);
				jco.setMiddle_name(middle_name);
				jco.setLast_name(last_name);
				jco.setPlace_of_birth("");
				jco.setDistrict_of_birth(0);
				jco.setState_of_birth(0);
				jco.setCountry_of_birth(0);
				jco.setNationality(0);
				jco.setReligion(Integer.parseInt(reli));
				jco.setMarital_status(10);
				jco.setBlood_group(Integer.parseInt(blood));
				jco.setRank(Integer.parseInt(rank));
				jco.setHeight(0);
				jco.setAadhar_no("");
				jco.setPan_no("");
				jco.setFull_name(merge_full);
				jco.setMother_tongue(Integer.parseInt(mt));
				jco.setTrade(Integer.parseInt(trade));
				jco.setFather_dob(null);
				jco.setMother_dob(null);
				jco.setDate_of_seniority(dt_ser);
				jco.setUnit_sus_no(roleSusNo);
				jco.setUnit_posted_to(unit_name);
				jco.setDate_of_birth(birth_dt);
				jco.setDate_of_attestation(atts_dt);
				jco.setEnroll_dt(en_dt);
				jco.setCreated_by(username);
				jco.setCreated_date(date);
				jco.setStatus(0);
				jco.setRegiment(regiment);
				jco.setDate_of_tos(tos_dt);
				jco.setDate_of_rank(dt_rank);
				jco.setDomicile(Integer.parseInt(domicile));
				jco.setUpdate_census_status(1);
				jco.setPay_group(Integer.parseInt(pay_group));
				jco.setClass_pay(Integer.parseInt(class_pay));
				jco.setUpdate_jco_status(1);
				jco.setUpdate_jco_cancel_status(1);
				jco.setUpdate_census_status(1);
				
				if (d) {
					
					sessionHQL.save(jco);
					sessionHQL.flush();
					sessionHQL.clear();
					
					Mmap.put("msg", "Data Saved Successfully.");
				}
				else {
					Mmap.put("msg", "Army No already Exist.");
				}
				}
			// Logic for INSERT
			int diffrence = siblings_count - siblingsOld_count;
			
			/*if (diffrence != 0) {
				TB_CENSUS_JCO_OR_SIBLINGS e = new TB_CENSUS_JCO_OR_SIBLINGS();
				for (int j = siblingsOld_count + 1; j <= siblings_count; j++) {

					
					String ser_ex = request.getParameter("ser-ex" + j);
					String other_sib_ser = request.getParameter("other_sibling_ser" + j);
					String sib_ser_text = request.getParameter("sibling" + j);
					int sibling_service = Integer.parseInt(request.getParameter("sibling_service" + j));
					String sibling_personal_no = request.getParameter("sibling_personal_no" + j);
					
					
					String sib_dob = request.getParameter("sib_date_of_birth" + j);
				
					if (sib_dob != null && !sib_dob.trim().equals("") && !sib_dob.equals("DD/MM/YYYY")) {
						sib_dt = format.parse(sib_dob);

					}
					String sib_name = request.getParameter("sib_name" + j);
					String sib_relationship = request.getParameter("sib_relationship" + j);

					String aadhar_no = request.getParameter("sib_adhar_number" + j);
					if (aadhar_no != null && !aadhar_no.equals("")) {
						adhar_numbers = Aadhar;
					}

					String pan_no2 = request.getParameter("sib_pan_no" + j);

					if (pan_no2 != null && !pan_no2.equals("")) {

						e.setPan_no(pan_no2);
					}
					e.setName(sib_name);
					e.setDate_of_birth(sib_dt);
					e.setRelationship(Integer.parseInt(sib_relationship));
					e.setJco_id(jco.getId());
					e.setAadhar_no("");
					e.setCreated_by(username);
					e.setCreated_date(new Date());
					e.setStatus(0);
					
					e.setIf_sibling_ser(ser_ex);
					
					e.setSibling_service(sibling_service);
					e.setSibling_personal_no(sibling_personal_no);
					e.setOther_sibling_ser(other_sib_ser);
					sessionHQL.save(e);
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}
*/
			
			
			//khushbu
			int spouse_count = Integer.parseInt(request.getParameter("spouse_count"));
			int spouseOld_count = Integer.parseInt(request.getParameter("spouseOld_count"));


			Date mrg_dt_of_birth = null;
			Date mrg_dt = null;

			String adhar_numbers_spouse = "";



			/*if(Integer.parseInt(mar_status) == 8 || Integer.parseInt(mar_status) == 13) {
				TB_CENSUS_FAMILY_MARRIED_JCO em = new TB_CENSUS_FAMILY_MARRIED_JCO();
					for (int j = spouseOld_count + 1; j <= spouse_count; j++) {

					
						String mar_status1 = request.getParameter("marital_status");
						
					String maiden_name = request.getParameter("maiden_name" + j);
					
					String marriage_date_of_birth = request.getParameter("marriage_date_of_birth" + j);
					if (marriage_date_of_birth != null && !marriage_date_of_birth.trim().equals("") && !marriage_date_of_birth.equals("DD/MM/YYYY")) {
						mrg_dt_of_birth = format.parse(marriage_date_of_birth);

					}
					
					String marriage_place_of_birth = request.getParameter("marriage_place_of_birth" + j);				
					String marriage_nationality = request.getParameter("marriage_nationality" + j);
					
					String marriage_date = request.getParameter("marriage_date" + j);
					if (marriage_date != null && !marriage_date.trim().equals("") && !marriage_date.equals("DD/MM/YYYY")) {
						mrg_dt = format.parse(marriage_date);

					}
					String pan_number = request.getParameter("spouse_pan_number" + j);
					String marriage_othernationality = request.getParameter("marriage_othernationality" + j);
					
					
					String adhar = request.getParameter("spouse_adhar_number" + j).replace("-", "");
					if (adhar != null && !adhar.equals("")) {
						adhar_numbers_spouse = new BigInteger(adhar);
					}
					
					String spo_ex = request.getParameter("spo-ex" + j);
					String spo_ser_text = request.getParameter("spouse" + j);
					int spouse_service = Integer.parseInt(request.getParameter("spouse_service" + j));
					String spouse_personal_no = request.getParameter("spouse_personal_no" + j);
					String other_spo_ser = request.getParameter("other_spouse_ser" + j);
					String sep_from_date = request.getParameter("sep_from_date" + j);
					Date sep_from_dt=null;
					String spo_ex1 = "";
					
					if (maiden_name == null || maiden_name.trim().equals("")) {
						Mmap.put("msg", "Please Enter  Name");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
		
		            if (validation.isOnlyAlphabet(maiden_name) == false) {
						Mmap.put("msg","Spouse Name "+ validation.isOnlyAlphabetMSG);
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
			         }
		            if (!validation.isvalidLength(maiden_name,validation.nameMax,validation.nameMin)) {
						Mmap.put("msg", "Spouse Name" + validation.isValidLengthMSG);
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
					}
					if (marriage_date_of_birth == null || marriage_date_of_birth.trim().equals("")
							|| marriage_date_of_birth.equals("DD/MM/YYYY")) {
						Mmap.put("msg", "Please Enter  Date of Birth");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					else  if (!validation.isValidDate(marriage_date_of_birth)){
						Mmap.put("msg", validation.isValidDateMSG  + " of Birth");
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
				     }
					else
					{
						mrg_dt_of_birth = format.parse(marriage_date_of_birth);
					}
					if (marriage_place_of_birth == null || marriage_place_of_birth.trim().equals("")) {
						Mmap.put("msg", "Please Enter  Place of Birth");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					if (validation.isOnlyAlphabet(marriage_place_of_birth) == false) {
						Mmap.put("msg","Place of Birth "+ validation.isOnlyAlphabetMSG);
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
			         }
					if (!validation.isvalidLength(marriage_place_of_birth,validation.nameMax,validation.nameMin)) {
						Mmap.put("msg", "Place of Birth" + validation.isValidLengthMSG);
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
					}
					if (marriage_nationality == null || marriage_nationality.equals("0")) {
						Mmap.put("msg", "Please Enter Nationality");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					if (marriage_date == null || marriage_date.trim().equals("") || marriage_date.equals("DD/MM/YYYY")) {
						Mmap.put("msg", "Please Enter Marriage Date");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					else  if (!validation.isValidDate(marriage_date)){
						Mmap.put("msg", validation.isValidDateMSG  + " of Marriage");
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
				     }
					else
					{
						mrg_dt = format.parse(marriage_date);
					}
					if (format.parse(marriage_date).compareTo(format.parse(marriage_date_of_birth)) <= 0) {
						Mmap.put("msg", "Marriage Date should be Greater than Date of Birth");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					if (pcommon.calculate_age(format.parse(marriage_date_of_birth), format.parse(marriage_date)) < 18) {
						Mmap.put("msg", "Spouse Age Should Be Above 18");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					if (adhar == null || adhar.trim().equals("")) {
						Mmap.put("msg", "Please Enter Aadhar No");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					if (validation.isValidAadhar(adhar) == false) {
						Mmap.put("msg", validation.isValidAadharMSG);
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
				     }
					else
					{
						adhar_numbers_spouse = adhar;
					}
					if(spo_ex == null) {
						spo_ex1 = "No";
					}else {
						spo_ex1 = "Yes";
					}
					
					if (spo_ex1.equals("Yes")) {
						if (spouse_service == 0) {
							Mmap.put("msg", "Please Select Spouse Service");
							tx.rollback();
							return new ModelAndView("redirect:JCOS_ORURL");
						}
						if (spouse_service == 1) {
							if (spouse_personal_no == null || spouse_personal_no.trim().equals("")) {
								Mmap.put("msg", "Please Enter Spouse Personal No");
								tx.rollback();
								return new ModelAndView("redirect:JCOS_ORURL");
							}
						}
						if (!spouse_personal_no.trim().equals("")) {
							if (spouse_personal_no.trim().length() < 5 || spouse_personal_no.trim().length() > 15) {
								Mmap.put("msg", "Please Enter Valid Spouse Personal No");
								tx.rollback();
								return new ModelAndView("redirect:JCOS_ORURL");
							}
						}
						if (spouse_personal_no.trim().equals("")) {
							spouse_personal_no = "";
						}
						if (spouse_service == 9) {
							if (other_spo_ser.trim().equals("")) {
								Mmap.put("msg", "Please Enter Other Spouse Service");
								tx.rollback();
								return new ModelAndView("redirect:JCOS_ORURL");
							}
						} 
						
						else {
							other_spo_ser = null;
						}
						
						
						
					}

					if(Integer.parseInt(mar_status) == 13) {
						
						if (sep_from_date == null || sep_from_date.trim().equals("")
								|| sep_from_date.equals("DD/MM/YYYY")) {
							Mmap.put("msg", "Please Enter  Date of Separation");
							tx.rollback();
							return new ModelAndView("redirect:JCOS_ORURL");
						}
						else  if (!validation.isValidDate(sep_from_date)){
							Mmap.put("msg", validation.isValidDateMSG  + " of Separation");
							tx.rollback();
						    return new ModelAndView("redirect:JCOS_ORURL");
					     }
						else
						{
							sep_from_dt = format.parse(sep_from_date);
						}
						
						if (format.parse(sep_from_date).compareTo(format.parse(marriage_date)) <= 0) {
							Mmap.put("msg", "Date of Separation should be Greater than Marriage Date");
							tx.rollback();
							return new ModelAndView("redirect:JCOS_ORURL");
						}
					}
					
						em.setMaiden_name(maiden_name);
						em.setDate_of_birth(mrg_dt_of_birth);
						em.setPlace_of_birth(marriage_place_of_birth);
						em.setNationality(Integer.parseInt(marriage_nationality));
						em.setMarriage_date(mrg_dt);
						em.setPan_card(pan_number);
						em.setOther_nationality(marriage_othernationality);
						em.setAdhar_number(adhar_numbers_spouse);
						em.setSpouse_service(spouse_service);
						em.setSpouse_personal_no(spouse_personal_no);
						em.setOther_spouse_ser(other_spo_ser);
						em.setIf_spouse_ser(spo_ex);
						em.setJco_id(jco.getId());
						em.setCreated_by(username);
						em.setCreated_date(date);
						em.setSeparated_from_dt(sep_from_dt);
						em.setSeparated_to_dt(null);
						em.setMarital_status(Integer.parseInt(mar_status));
						sessionHQL.save(em);
						sessionHQL.flush();
						sessionHQL.clear();
					
					}
					
				//}
			
			String spouse_quali_radio = request.getParameter("spouse_quali_radio");
			if(spouse_quali_radio.equals("yes")) {
			
				TB_CENSUS_SPOUSE_QUALIFICATION_JCO sq = new TB_CENSUS_SPOUSE_QUALIFICATION_JCO();				
				
				String type = request.getParameter("spouse_quali_type");
				String examination_pass = request.getParameter("spouse_quali");
				String passing_year = request.getParameter("spouse_yrOfPass");
				String div_class = request.getParameter("spouse_div_class");
				String subject = request.getParameter("subject_sp");
				String institute = request.getParameter("spouse_institute_place");
				String degree = request.getParameter("quali_degree_spouse");
				String specialization = request.getParameter("spouse_specialization");
				String exam_other_qua = request.getParameter("exam_otherSpouse");
				String class_other_qua = request.getParameter("class_otherSpouse");
				String degree_other = request.getParameter("quali_deg_otherSpouse");
				String spec_other = request.getParameter("quali_spec_otherSpouse");
				
			
			if (type == null || type.equals("0")) {
					Mmap.put("msg", "Please Select The Qualification Type");
					tx.rollback();
					return new ModelAndView("redirect:JCOS_ORURL");
				}
				if (examination_pass == null || examination_pass.equals("0")) {
					Mmap.put("msg", "Please Select The Examination Passed");
					tx.rollback();
					return new ModelAndView("redirect:JCOS_ORURL");
				}
				if (degree == null || degree.equals("0")) {
					Mmap.put("msg", "Please Select The Degree Acquried");
					tx.rollback();
					return new ModelAndView("redirect:JCOS_ORURL");
				}
				if (specialization == null || specialization.equals("0")) {
					Mmap.put("msg", "Please Select The Specialization");
					tx.rollback();
					return new ModelAndView("redirect:JCOS_ORURL");
				}
				if (passing_year == null || passing_year.trim().equals("")) {
					Mmap.put("msg", "Please Enter The Year of passing");
					tx.rollback();
					return new ModelAndView("redirect:JCOS_ORURL");
				}
				if (div_class == null || div_class.equals("0")) {
					Mmap.put("msg", "Please Select The Div/Grade/PCT(%)");
					tx.rollback();
					return new ModelAndView("redirect:JCOS_ORURL");
				}
		
				if (institute == null || institute.trim().equals("")) {
					Mmap.put("msg", "Please Enter The Institute & place");
					tx.rollback();
					return new ModelAndView("redirect:JCOS_ORURL");
				}

                 if (validation.isOnlyAlphabet(institute) == false) {
					Mmap.put("msg","Institute "+ validation.isOnlyAlphabetMSG);
					tx.rollback();
				    return new ModelAndView("redirect:JCOS_ORURL");
	             }
                 if (!validation.isvalidLength(institute,validation.nameMax,validation.nameMin)) {
					Mmap.put("msg", "Institute" + validation.isValidLengthMSG);
					tx.rollback();
					return new ModelAndView("redirect:JCOS_ORURL");
					}
				sq.setType(Integer.parseInt(type));
				sq.setExamination_pass(Integer.parseInt(examination_pass));
				sq.setPassing_year(Integer.parseInt(passing_year));
				sq.setDiv_class(div_class);
				sq.setSubject(subject);
				sq.setInstitute(institute);
				sq.setDegree(Integer.parseInt(degree));
				sq.setSpecialization(Integer.parseInt(specialization));
				sq.setExam_other(exam_other_qua);
				sq.setClass_other(class_other_qua);
				sq.setDegree_other(degree_other);
				sq.setSpecialization_other(spec_other);
				sq.setJco_id(jco.getId());
				sq.setSpouse_id(em.getId());
				sq.setCreated_by(username);
				sq.setCreated_date(date);
				sessionHQL.save(sq);
				sessionHQL.flush();
				sessionHQL.clear();
				
			}
		}*/
			int divorce_count = Integer.parseInt(request.getParameter("divorce_count"));
			int divorceOld_count = Integer.parseInt(request.getParameter("divorceOld_count"));
			

			Date date_of_birth_divo = null;
			Date mrg_dt_divo = null;
			Date divorce_dt = null;

			String adhar_numbers_divo = "";

		/*	if(Integer.parseInt(mar_status) == 9 || Integer.parseInt(mar_status) == 13|| Integer.parseInt(mar_status) == 11 || Integer.parseInt(mar_status) == 12 || Integer.parseInt(mar_status) == 8) {
					
					for (int j = divorceOld_count + 1; j <= divorce_count; j++) {

						TB_CENSUS_FAMILY_MARRIED_JCO fd = new TB_CENSUS_FAMILY_MARRIED_JCO();
					
						
						Date date_divorce = new Date();
						String username_divorce = session.getAttribute("username").toString();
						String spouse_name = request.getParameter("spouse_name" + j);
						String marriage_date = request.getParameter("divorce_marriage_date" + j);
						String divorce_date = request.getParameter("divorce_date" + j);
						String date_of_birth = request.getParameter("divorce_date_of_birth" + j);
						String place_of_birth = request.getParameter("divorce_place_of_birth" + j);
						String nationality = request.getParameter("divorce_nationality" + j);
						String adhar_no_divo = request.getParameter("divorce_spouse_adhar_number" + j).replace("-", "");
						String pan_number = request.getParameter("divorce_spouse_pan_number" + j);
						String other_nationality = request.getParameter("divorce_othernationality" + j);
						String marital_event = request.getParameter("marital_event" + j);
							
						
					if (marriage_date != null && !marriage_date.trim().equals("") && !marriage_date.equals("DD/MM/YYYY")) {
						mrg_dt_divo = format.parse(marriage_date);

					}
					if (divorce_date != null && !divorce_date.trim().equals("") && !divorce_date.equals("DD/MM/YYYY")) {
						divorce_dt = format.parse(divorce_date);

					}
					if (date_of_birth != null && !date_of_birth.trim().equals("") && !date_of_birth.equals("DD/MM/YYYY")) {
						date_of_birth_divo = format.parse(date_of_birth);

					}					
					if (adhar_no_divo != null && !adhar_no_divo.equals("")) {
						adhar_numbers_divo = new BigInteger(adhar_no_divo);
					}
					
					
					if(spouse_name != "")
					{
						if (marital_event == null || marital_event.equals("0")) {
							Mmap.put("msg", "Please Select Marital Event");
							tx.rollback();
							return new ModelAndView("redirect:JCOS_ORURL");
						}
						
						
					if (spouse_name == null || spouse_name.trim().equals("")) {
						Mmap.put("msg", "Please Enter  Name");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					if (validation.isOnlyAlphabet(spouse_name) == false) {
						Mmap.put("msg","Spouse Name "+ validation.isOnlyAlphabetMSG);
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
				     }
					 if (!validation.isvalidLength(spouse_name,validation.nameMax,validation.nameMin)) {
						Mmap.put("msg", "Spouse Name" + validation.isValidLengthMSG);
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
						}
					if (date_of_birth == null || date_of_birth.trim().equals("") || date_of_birth.equals("DD/MM/YYYY")) {
						Mmap.put("msg", "Please Enter Date of Birth");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					else  if (!validation.isValidDate(date_of_birth)){
						Mmap.put("msg", validation.isValidDateMSG  + " of Birth");
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
				     }
					else
					{
						date_of_birth_divo = format.parse(date_of_birth);
					}
					if (place_of_birth == null || place_of_birth.trim().equals("")) {
						Mmap.put("msg", "Please Enter Place of Birth");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					if (validation.isOnlyAlphabet(place_of_birth) == false) {
						Mmap.put("msg","Place of Birth "+ validation.isOnlyAlphabetMSG);
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
				     }
					 if (!validation.isvalidLength(place_of_birth,validation.nameMax,validation.nameMin)) {
							Mmap.put("msg", "Place of Birth" + validation.isValidLengthMSG);
							tx.rollback();
						    return new ModelAndView("redirect:JCOS_ORURL");
						}
					if (nationality == null || nationality.equals("0")) {
						Mmap.put("msg", "Please Enter Nationality");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					if (marriage_date == null || marriage_date.trim().equals("") || marriage_date.equals("DD/MM/YYYY")) {
						Mmap.put("msg", "Please Enter Marriage Date");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					else  if (!validation.isValidDate(marriage_date)){
						Mmap.put("msg", validation.isValidDateMSG  + " of Marriage");
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
				     }
					else
					{
						mrg_dt_divo = format.parse(marriage_date);
					}
					if (adhar_no_divo == null || adhar_no_divo.trim().equals("")) {
						Mmap.put("msg", "Please Enter Aadhar No");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					if (validation.isValidAadhar(adhar_no_divo) == false) {
						Mmap.put("msg", validation.isValidAadharMSG);
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
				     }
					else
					{
						adhar_numbers_divo = adhar_no_divo;
					}
					if (format.parse(marriage_date).compareTo(format.parse(date_of_birth)) <= 0) {
						Mmap.put("msg", "Marriage Date should be Greater than Date of Birth");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					if (pcommon.calculate_age(format.parse(date_of_birth), format.parse(marriage_date)) < 18) {
						Mmap.put("msg", "Spouse Age Should Be Above 18");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					if (divorce_date == null || divorce_date.trim().equals("") || divorce_date.equals("DD/MM/YYYY")) {
						Mmap.put("msg", "Please Enter Divorce/Widow/Widower Date");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					else  if (!validation.isValidDate(divorce_date)){
						Mmap.put("msg", validation.isValidDateMSG  + " of Divorce");
						tx.rollback();
					    return new ModelAndView("redirect:JCOS_ORURL");
				     }
					else
					{
						divorce_dt = format.parse(divorce_date);
					}
					if (format.parse(divorce_date).compareTo(format.parse(date_of_birth)) <= 0) {
						Mmap.put("msg", "Divorce Date should be Greater than Date of Birth");
						tx.rollback();
						return new ModelAndView("redirect:JCOS_ORURL");
					}
					
						fd.setCreated_date(date_divorce);
						fd.setCreated_by(username_divorce);
						fd.setMaiden_name(spouse_name);
						fd.setMarriage_date(mrg_dt_divo);
						fd.setDivorce_date(divorce_dt);
						fd.setDate_of_birth(date_of_birth_divo);
						fd.setPlace_of_birth(place_of_birth);
						fd.setNationality(Integer.parseInt(nationality));
						fd.setAdhar_number(adhar_numbers_divo);
						fd.setPan_card(pan_number);
						fd.setOther_nationality(other_nationality);
						fd.setJco_id(jco.getId());
						fd.setMarital_status(Integer.parseInt(marital_event));
						
						sessionHQL.save(fd);
						sessionHQL.flush();
						sessionHQL.clear();
					}
					
					}
					
				}*/

		   tx.commit();
			}
		 catch (RuntimeException e) {
			try {
				tx.rollback();
				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldn t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();

			}
		}

		return new ModelAndView("redirect:JCOS_ORURL");
	}
	
	
	
	

//////////////////////////

	@RequestMapping(value = "/GetArmyNoDataNoData", method = RequestMethod.POST)

	public @ResponseBody List<String> GetArmyNoDataNoData(String army_no) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q1 = sessionHQL.createQuery(

					" select c.id,c.army_no,c.full_name,c.unit_sus,c.unit_name,cu.description,c.marital_status,cu.id  "
							+ "from TB_CENSUS_JCO_OR_PARENT c" + ",CUE_TB_PSG_RANK_APP_MASTER cu  "
							+ "where army_no = :army_no and cu.id=c.rank and  cu.status_active = 'Active' "
							+ "order by army_no");

			q1.setParameter("army_no", army_no);

			@SuppressWarnings("unchecked")

			List<String> list = (List<String>) q1.list();

			tx.commit();

			return list;

		} catch (RuntimeException e) {

			return null;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

	}

	
	@RequestMapping(value = "/getArmyNoAlreadyExist", method = RequestMethod.POST)
	public @ResponseBody Boolean getArmyNoAlreadyExist(HttpServletRequest request, HttpSession sessionA, String army_no)
			throws HibernateException, ParseException {
		String jco_id=request.getParameter("jco_id");
		String army_id=request.getParameter("army_id");
		
		
		return SJDAO.getArmyNoAlreadyExist(army_no,jco_id,army_id);
	}
 public String generate_army_no(String data) {
         
         String suffix="";
         int summation=0;
        int len= data.length();
        if(len==1)
            summation =+2*(Integer.parseInt(data.substring(0,1)));
        if(len==2)
            summation =+3*(Integer.parseInt(data.substring(0,1)))+2*(Integer.parseInt(data.substring(1,2)));
        if(len==3)
            summation =+4*(Integer.parseInt(data.substring(0,1)))+3*(Integer.parseInt(data.substring(1,2)))+2*(Integer.parseInt(data.substring(2,3)));
        if(len==4)
            summation =+5*(Integer.parseInt(data.substring(0,1)))+4*(Integer.parseInt(data.substring(1,2)))+3*(Integer.parseInt(data.substring(2,3)))+2*(Integer.parseInt(data.substring(3,4)));
        if(len==5)
            summation =+6*(Integer.parseInt(data.substring(0,1)))+5*(Integer.parseInt(data.substring(1,2)))+4*(Integer.parseInt(data.substring(2,3)))+3*(Integer.parseInt(data.substring(3,4)))+2*(Integer.parseInt(data.substring(4,5)));
        if(len==6)
            summation =+7*(Integer.parseInt(data.substring(0,1)))+6*(Integer.parseInt(data.substring(1,2)))+5*(Integer.parseInt(data.substring(2,3)))+4*(Integer.parseInt(data.substring(3,4)))+3*(Integer.parseInt(data.substring(4,5)))+2*(Integer.parseInt(data.substring(5,6)));
        if(len==7)      
            summation =+8*(Integer.parseInt(data.substring(0,1)))+7*(Integer.parseInt(data.substring(1,2)))+6*(Integer.parseInt(data.substring(2,3)))+5*(Integer.parseInt(data.substring(3,4)))+4*(Integer.parseInt(data.substring(4,5)))+3*(Integer.parseInt(data.substring(5,6)))+2*(Integer.parseInt(data.substring(6,7)));
        if(len==8)      
             summation =+9*(Integer.parseInt(data.substring(0,1)))+8*(Integer.parseInt(data.substring(1,2)))+7*(Integer.parseInt(data.substring(2,3)))+6*(Integer.parseInt(data.substring(3,4)))+5*(Integer.parseInt(data.substring(4,5)))+4*(Integer.parseInt(data.substring(5,6)))+3*(Integer.parseInt(data.substring(6,7)))+2*(Integer.parseInt(data.substring(7,8)));
        if(len==9)      
             summation =+10*(Integer.parseInt(data.substring(0,1)))+9*(Integer.parseInt(data.substring(1,2)))+8*(Integer.parseInt(data.substring(2,3)))+7*(Integer.parseInt(data.substring(3,4)))+6*(Integer.parseInt(data.substring(4,5)))+5*(Integer.parseInt(data.substring(5,6)))+4*(Integer.parseInt(data.substring(6,7)))+3*(Integer.parseInt(data.substring(7,8)))+2*(Integer.parseInt(data.substring(8,9)));
 
            
                  summation =  summation % 11;
          
                  if(summation == 0)
                  {
                          suffix="A";
                  }
                  else if(summation == 1)
                  {
                          suffix="F";
                  }
                  else if(summation == 2)
                  {
                          suffix="H";
                  }
                  else if(summation == 3)
                  {
                          suffix="K";
                  }
                  else if(summation == 4)
                  {
                          suffix="L";
                  }
                  else if(summation == 5)
                  {
                          suffix="M";
                  }
                  else if(summation == 6)
                  {
                          suffix="N";
                  }
                  else if(summation == 7)
                  {
                          suffix="P";
                  }
                  else if(summation == 8)
                  {
                          suffix="W";
                  }
                  else if(summation == 9)
                  {
                          suffix="X";
                  }
                  else if(summation == 10)
                  {
                          suffix="Y";
                  }
       
         
    return suffix;

 
 }
 
 
//below method changes as per provided changes
	
	@RequestMapping(value = "/jco_Personnel_Detail_action", method = RequestMethod.POST)
	public ModelAndView jco_Personnel_Detail_action(
			@ModelAttribute("jco_Personnel_DetailCMD") TB_CENSUS_JCO_OR_PARENT jco, BindingResult result,
			HttpServletRequest request, ModelMap Mmap, HttpSession session, String msg) throws ParseException {

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("JCOS_ORURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");

		}		

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		String username = session.getAttribute("username").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
	
		Date tos_dt = null;
		Date birth_dt = null;
		Date atts_dt = null;
		Date dt_ser = null;		
		Date en_dt = null;
		Date dt_rank = null;	
		String date_attestation = request.getParameter("date_of_attestation");
		String birth_date = request.getParameter("date_of_birth");
		String enrl_date = request.getParameter("enroll_dt");
		String tos_date = request.getParameter("date_of_tos");
		String date_of_rank = request.getParameter("date_of_rank");
		String date_ser = request.getParameter("date_of_seniority");		
		String class_pay = request.getParameter("class_pay");
		String pay_group = request.getParameter("pay_group");
		String unit_name = request.getParameter("unit_name");
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String merge_full = first_name + " " + middle_name + " " + last_name;		
		String religion_hid = request.getParameter("religion_hid");
		String mother_tongue_hid = request.getParameter("mother_tongue_hid");	
		String gender_hid = request.getParameter("gender_hid");
		String gender_other = request.getParameter("gender_other");		
		String rank = request.getParameter("rank");	
		String reli = request.getParameter("religion");
		String blood = request.getParameter("blood_group");
		String domicile = request.getParameter("domicile");	
		String mt = request.getParameter("mother_tongue");
		String trade = request.getParameter("trade");	
		String arm_ser = request.getParameter("arm_service");
		String regiment = request.getParameter("regiment");
		String unit_sus_no = request.getParameter("unit_sus_no");
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (!roleAccess.equals("Unit")) {

		if(unit_sus_no == null || unit_sus_no.equals("null") || unit_sus_no.equals("")) {
				Mmap.put("msg", "Please Enter Unit Sus No.");
				return new ModelAndView("redirect:JCOS_ORURL");
			} 
		 if (!validation.isOnlyAlphabetNumeric(unit_sus_no)) {
           Mmap.put("msg", validation.isOnlyAlphabetNumericMSG + "Unit Sus No");
		     return new ModelAndView("redirect:JCOS_ORURL");
          }
		 if (!validation.SusNoLength(unit_sus_no)) {
           Mmap.put("msg", validation.SusNoMSG);
		     return new ModelAndView("redirect:JCOS_ORURL");
          }
		else {
				roleSusNo = unit_sus_no;
			}
		
		if(unit_name == null || unit_name.equals("null") || unit_name.equals("")) {
			Mmap.put("msg", "Please Enter Unit Name.");
			return new ModelAndView("redirect:JCOS_ORURL");
			} 

		}
		
		String army_no1 = request.getParameter("army_no1");
		String army_no2 = request.getParameter("army_no2");
		String army_no3 = request.getParameter("army_no3");	
		String religion_other = request.getParameter("religion_other");
		String mother_tongue_other = request.getParameter("mother_tongue_other");
	
		if (request.getParameter("category") == null || request.getParameter("category").trim().equals("0")) {
			Mmap.put("msg", "Please Select Category");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (request.getParameter("category").equals("JCO")) {
			if (army_no1 == null || army_no1.trim().equals("0")) {
				Mmap.put("msg", " Please Select Army No");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (army_no2 == null || army_no2.trim().equals("")) {
				Mmap.put("msg", " Please Enter Army No");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
		} else {
			if (army_no2 == null || army_no2.trim().equals("")) {
				Mmap.put("msg", " Please Enter Army No");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
		}
	
       if (validation.isOnlyNumer(army_no2) == true) {
			Mmap.put("msg", validation.isOnlyNumerMSG  + " Army No");
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		if (army_no2.length() < 1 || army_no2.length() > 9) {
			Mmap.put("msg", "Please Enter Valid Army No");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (first_name == null || first_name.trim().equals("")) {
			Mmap.put("msg", "Please Enter First Name" );
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (validation.isOnlyAlphabet(first_name) == false) {
			Mmap.put("msg", "First Name "+ validation.isOnlyAlphabetMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		if (!validation.isvalidLength(first_name,validation.nameMax,validation.nameMin)) {
			Mmap.put("msg", "First Name " + validation.isValidLengthMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
		}

		if (request.getParameter("gender") == null || request.getParameter("gender").trim().equals("0")) {
			Mmap.put("msg", "Please Select Gender");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (gender_hid != null && gender_hid.equals("OTHER")) {
			if (gender_other == null || gender_other.trim().equals("")) {
			
				Mmap.put("msg", "Please Enter Other Gender");
			
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (validation.isOnlyAlphabet(gender_other) == false) {
				Mmap.put("msg", "Other Gender "+ validation.isOnlyAlphabetMSG);
		
			    return new ModelAndView("redirect:JCOS_ORURL");
		     }
			if (!validation.isvalidLength(gender_other,validation.nameMax,validation.nameMin)) {
				Mmap.put("msg", "Other Gender " + validation.isValidLengthMSG);
				
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
			}
		if (request.getParameter("rank") == null || request.getParameter("rank").trim().equals("0")) {
			Mmap.put("msg", "Please Select Rank");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (request.getParameter("rank").equals("17")) {
			if (request.getParameter("rank_intake") == null || request.getParameter("rank_intake").trim().equals("0")) {
				Mmap.put("msg", "Please Select Rank Intake");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
		}
		
		if (birth_date == null || birth_date.trim().equals("") || birth_date.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Select Date of Birth");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
			else  if (!validation.isValidDate(birth_date)){
					Mmap.put("msg", validation.isValidDateMSG  + " of Birth");
				    return new ModelAndView("redirect:JCOS_ORURL");
			     }
		 else {
			birth_dt = format.parse(birth_date);
		}
		
		if (reli == null || reli.equals("0")) {
			Mmap.put("msg", "Please Select Religion");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (religion_hid != null && religion_hid.equals("OTHERS")) {
			if (religion_other == null || religion_other.trim().equals("")) {
			
				Mmap.put("msg", "Please Enter Other Religion");
			
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (validation.isOnlyAlphabet(religion_other) == false) {
				Mmap.put("msg", "Other Religion "+ validation.isOnlyAlphabetMSG);
		
			    return new ModelAndView("redirect:JCOS_ORURL");
		     }
			if (!validation.isvalidLength(religion_other,validation.nameMax,validation.nameMin)) {
				Mmap.put("msg", "Other Religion " + validation.isValidLengthMSG);
				
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
			}
		if (mt == null || mt.equals("0")) {
			Mmap.put("msg", "Please Select Mother Tongue");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (mother_tongue_hid != null && mother_tongue_hid.equals("OTHERS")) {
			if (mother_tongue_other == null || mother_tongue_other.trim().equals("")) {
			
				Mmap.put("msg", "Please Enter Other Mother Tongue");
				
				return new ModelAndView("redirect:JCOS_ORURL");
			}
			if (validation.isOnlyAlphabet(mother_tongue_other) == false) {
				Mmap.put("msg", "Other Mother Tongue "+ validation.isOnlyAlphabetMSG);
			
			    return new ModelAndView("redirect:JCOS_ORURL");
		     }
			if (!validation.isvalidLength(mother_tongue_other,validation.nameMax,validation.nameMin)) {
				Mmap.put("msg", "Other Mother Tongue " + validation.isValidLengthMSG);
				
			    return new ModelAndView("redirect:JCOS_ORURL");
			}
			}		
		if (blood == null || blood.equals("0")) {
			Mmap.put("msg", "Please Select Blood Group");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		
		if (class_pay == null || class_pay.equals("0")) {
			Mmap.put("msg", "Please Select Class(Pay)");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (pay_group == null || pay_group.equals("0")) {
			Mmap.put("msg", "Please Select Pay Group");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (request.getParameter("record_office_sus") == null || request.getParameter("record_office_sus").trim().equals("")) {
			Mmap.put("msg", "Please Enter Record Office(SUS No) ");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		 if (!validation.isOnlyAlphabetNumeric(request.getParameter("record_office_sus"))) {
           Mmap.put("msg", validation.isOnlyAlphabetNumericMSG + "Record Office Sus");
		     return new ModelAndView("redirect:JCOS_ORURL");
          }
		 if (!validation.SusNoLength(request.getParameter("record_office_sus"))) {
           Mmap.put("msg", validation.SusNoMSG);
		     return new ModelAndView("redirect:JCOS_ORURL");
          }		
		if (enrl_date == null || enrl_date.trim().equals("") || enrl_date.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Select Date of Enrollment");
			return new ModelAndView("redirect:JCOS_ORURL");
		} 
		else  if (!validation.isValidDate(enrl_date)){
			Mmap.put("msg", validation.isValidDateMSG  + " of Enrollment");
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		else {
			en_dt = format.parse(enrl_date);
		}	
		
		if (!request.getParameter("rank").equals("17")) {
			if (date_attestation == null || date_attestation.trim().equals("") || date_attestation.equals("DD/MM/YYYY")) {
				Mmap.put("msg", "Please Select Date of Attestation");
				return new ModelAndView("redirect:JCOS_ORURL");
			} 
		}
		if (!date_attestation.trim().equals(null) && !date_attestation.trim().equals("") & !date_attestation.equals("DD/MM/YYYY")) { 
			 if (!validation.isValidDate(date_attestation)){
				    Mmap.put("msg", validation.isValidDateMSG  + " of Attestation");
				    return new ModelAndView("redirect:JCOS_ORURL");
			     }
			atts_dt = format.parse(date_attestation);
		}
		
		
			
		if (pcommon.calculate_age(birth_dt, en_dt) < 16) {
			Mmap.put("msg", "Please enter age above 16");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (date_ser == null || date_ser.trim().equals("") || date_ser.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Select Date of Seniority");
			return new ModelAndView("redirect:JCOS_ORURL");
		} 
		else  if (!validation.isValidDate(date_ser)){
			Mmap.put("msg", validation.isValidDateMSG  + " of Seniority");
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		else {
			dt_ser = format.parse(date_ser);
		}
		if (pcommon.calculate_age(birth_dt, dt_ser) < 16) {
			Mmap.put("msg", "Please enter age above 16");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (trade == null || trade.equals("0")) {
			Mmap.put("msg", " Please Enter Trade");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (tos_date == null || tos_date.trim().equals("") || tos_date.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Select Date of TOS");
			return new ModelAndView("redirect:JCOS_ORURL");
		} 
		else  if (!validation.isValidDate(tos_date)){
			Mmap.put("msg", validation.isValidDateMSG  + " of Tos");
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		else {
			tos_dt = format.parse(tos_date);
		}	
		
		
		if (arm_ser == null || arm_ser.equals("0")) {
			Mmap.put("msg", "Please Select Arm/Services");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		
		if(arm_ser.equals("0700") || arm_ser.equals("0800")) {
			if(regiment==null || regiment.trim().equals("") || regiment.equals("0")) {
				Mmap.put("msg", "Please Select Regiment");
				return new ModelAndView("redirect:JCOS_ORURL");
			}
		}
 
		if (request.getParameter("father_name") == null || request.getParameter("father_name").trim().equals("")) {
			Mmap.put("msg", "Please Select Father's Name");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (validation.isOnlyAlphabet(request.getParameter("father_name")) == false) {
			Mmap.put("msg","Father's Name "+ validation.isOnlyAlphabetMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		if (!validation.isvalidLength(request.getParameter("father_name"),validation.nameMax,validation.nameMin)) {
			Mmap.put("msg", "Father's Name " + validation.isValidLengthMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
		}
		
		if (request.getParameter("mother_name") == null || request.getParameter("mother_name").trim().equals("")) {
			Mmap.put("msg", "Please Select Mother's Name");
			return new ModelAndView("redirect:JCOS_ORURL");
		}
		if (validation.isOnlyAlphabet(request.getParameter("mother_name")) == false) {
			Mmap.put("msg","Mother's Name "+ validation.isOnlyAlphabetMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		if (!validation.isvalidLength(request.getParameter("mother_name"),validation.nameMax,validation.nameMin)) {
			Mmap.put("msg", "Mother's Name " + validation.isValidLengthMSG);
		    return new ModelAndView("redirect:JCOS_ORURL");
		}
		
		if (date_of_rank == null || date_of_rank.trim().equals("") || date_of_rank.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Select Date of Rank");
			return new ModelAndView("redirect:JCOS_ORURL");
		} 
		else  if (!validation.isValidDate(date_of_rank)){
			Mmap.put("msg", validation.isValidDateMSG  + " of Rank");
		    return new ModelAndView("redirect:JCOS_ORURL");
	     }
		else {
			
			if(format.parse(date_of_rank).before(en_dt)) {
				Mmap.put("msg", "Please Select Date of Rank Greater than Enrollment Date");
			    return new ModelAndView("redirect:JCOS_ORURL");
			}else {
			dt_rank = format.parse(date_of_rank);
			}
		}
		
		int id = jco.getId() > 0 ? jco.getId() : 0;
		try {
			
			String armsuffix = generate_army_no(army_no2);		
			
			if(request.getParameter("category").equals("OR"))
			{
				army_no1="";
			}
			String army_no = army_no1 + army_no2 + armsuffix;
			
			Boolean d = SJDAO.getArmyNoAlreadyExist(army_no,String.valueOf(id),null);
			
			if (id == 0) {
				jco.setArmy_no(army_no);
				jco.setFirst_name(first_name);
				jco.setMiddle_name(middle_name);
				jco.setLast_name(last_name);
				jco.setPlace_of_birth("");
				jco.setDistrict_of_birth(0);
				jco.setState_of_birth(0);
				jco.setCountry_of_birth(0);
				jco.setNationality(0);
				jco.setReligion(Integer.parseInt(reli));
				jco.setMarital_status(10);
				jco.setBlood_group(Integer.parseInt(blood));
				jco.setRank(Integer.parseInt(rank));
				jco.setHeight(0);
				jco.setAadhar_no("");
				jco.setPan_no("");
				jco.setFull_name(merge_full);
				jco.setMother_tongue(Integer.parseInt(mt));
				jco.setTrade(Integer.parseInt(trade));
				jco.setFather_dob(null);
				jco.setMother_dob(null);
				jco.setDate_of_seniority(dt_ser);
				jco.setUnit_sus_no(roleSusNo);
				jco.setUnit_posted_to(unit_name);
				jco.setDate_of_birth(birth_dt);
				jco.setDate_of_attestation(atts_dt);
				jco.setEnroll_dt(en_dt);
				jco.setCreated_by(username);
				jco.setCreated_date(date);
				jco.setStatus(0);
				jco.setRegiment(regiment);
				jco.setDate_of_tos(tos_dt);
				jco.setDate_of_rank(dt_rank);
				jco.setDomicile(Integer.parseInt(domicile));
				jco.setUpdate_census_status(1);
				jco.setPay_group(Integer.parseInt(pay_group));
				jco.setClass_pay(Integer.parseInt(class_pay));
				jco.setUpdate_jco_status(1);
				jco.setUpdate_jco_cancel_status(1);
				jco.setUpdate_census_status(1);
				
				if (d) {
					
					String jcoid = sessionHQL.save(jco).toString();					
				String medicalStatus=medical_categoryAction_jco(Mmap,session,request,jcoid);
				if (!medicalStatus.equals("1")) {				 
					tx.rollback();				    
				    Mmap.put("msg", medicalStatus);
				    return new ModelAndView("redirect:JCOS_ORURL");
				}
					sessionHQL.flush();
					sessionHQL.clear();
					
					Mmap.put("msg", "Data Saved Successfully.");
				}
				else {
					Mmap.put("msg", "Army No already Exist.");
				}
				}	
	
		   tx.commit();
			}
		 catch (RuntimeException e) {
			try {
				tx.rollback();
				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldn t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();

			}
		}

		return new ModelAndView("redirect:JCOS_ORURL");
	}
	
	public @ResponseBody String medical_categoryAction_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request,String jco_id)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		Boolean validData=false;
		
		int sShape_count = Integer.parseInt(request.getParameter("sShape_count").toString()); //total count
		int sShapeOld_count = Integer.parseInt(request.getParameter("sShapeOld_count").toString());// oldcount
		
		int hShape_count = Integer.parseInt(request.getParameter("hShape_count").toString()); //total count
		int hShapeOld_count = Integer.parseInt(request.getParameter("hShapeOld_count").toString());// oldcount
		
		int aShape_count = Integer.parseInt(request.getParameter("aShape_count").toString()); //total count
		int aShapeOld_count = Integer.parseInt(request.getParameter("aShapeOld_count").toString());// oldcount
		
		int pShape_count = Integer.parseInt(request.getParameter("pShape_count").toString()); //total count
		int pShapeOld_count = Integer.parseInt(request.getParameter("pShapeOld_count").toString());// oldcount
		
		int eShape_count = Integer.parseInt(request.getParameter("eShape_count").toString()); //total count
		int eShapeOld_count = Integer.parseInt(request.getParameter("eShapeOld_count").toString());// oldcount
		
		int cCope_count = Integer.parseInt(request.getParameter("cCope_count").toString()); //total count
		int cCopeOld_count = Integer.parseInt(request.getParameter("cCopeOld_count").toString());// oldcount
		
		int oCope_count = Integer.parseInt(request.getParameter("oCope_count").toString()); //total count
		int oCopeOld_count = Integer.parseInt(request.getParameter("oCopeOld_count").toString());// oldcount
		
		int pCope_count = Integer.parseInt(request.getParameter("pCope_count").toString()); //total count
		int pCopeOld_count = Integer.parseInt(request.getParameter("pCopeOld_count").toString());// oldcount
		
		int eCope_count = Integer.parseInt(request.getParameter("eCope_count").toString()); //total count
		int eCopeOld_count = Integer.parseInt(request.getParameter("eCopeOld_count").toString());// oldcount
		
		String authority = request.getParameter("madical_authority");
		//String date_of_authority = request.getParameter("madical_date_of_authority");
		String mad_classification = request.getParameter("mad_classification_count");
		
		String checkbox_1bx = request.getParameter("check_1bx");
		String _1BX_from_date = request.getParameter("1bx_from_date");
		String _1BX_to_date = request.getParameter("1bx_to_date");
		String _1BX_diagnosis = request.getParameter("1bx_diagnosis_code");
		//Date enroll_date = format.parse(request.getParameter("enroll_date"));
		String msg = "";
		int diffrence;
		
		
		Session session3 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session3.beginTransaction();
//		if (authority == null || authority.equals("")) {
//			tx3.rollback();
//			msg="Please Enter The Authority ";			
//			return msg;
//		}
//		if (!validation.isValidAuth(authority)) {
//			msg= validation.isValidAuthMSG + " Authority";	
//			return msg;
//		}
// 		if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
// 			msg= "Authority " + validation.isValidLengthMSG;	
// 			return msg;
//		}	
//		if (date_of_authority == null || date_of_authority.equals("") || date_of_authority.equals("DD/MM/YYYY")) {
//			tx3.rollback();
//			msg="Please Enter  Date of Authority ";		
//			return msg;
//		}
//		if (!validation.isValidDate(date_of_authority)) {
//			msg=  validation.isValidDateMSG + " of Authority";	
//			return msg;
//		}	
//		if (pcommon.CompareDate(format.parse(date_of_authority),enroll_date) == 0) {
//			return "Authority Date should be Greater than Enroll Date";
//		}
		
		if (mad_classification == null || mad_classification.equals("0")) {
			tx3.rollback();
			msg="Please Select The Medical Classification ";			
			return msg;
		}

		try {
			
//			Query qc = session3.createQuery("select count(id) from TB_MEDICAL_CATEGORY_JCO where date_of_authority=:date_of_authority and jco_id=:jco_id and status in (1,2) and cancel_status!=1");
//			qc.setParameter("jco_id", Integer.parseInt(jco_id)).setTimestamp("date_of_authority", format.parse(date_of_authority));
//			long c = (Long) qc.uniqueResult();
//			
//			if (c>0) {
//				tx3.rollback();
//				msg="Data With Same Authority Date Already Exists!!";			
//				return msg;
//			}
			
			if(checkbox_1bx != null && checkbox_1bx.equals("1BX")) {
				
				if ((_1BX_from_date == null || _1BX_from_date.equals("") || _1BX_from_date.equals("DD/MM/YYYY")) || (_1BX_to_date == null || _1BX_to_date.equals("") || _1BX_to_date.equals("DD/MM/YYYY"))) {
					tx3.rollback();
					msg="Please Enter From Date And To Date For Shape 1BX";		
					return msg;
				}
				if (_1BX_from_date != null || _1BX_from_date.trim().equals("") || _1BX_from_date.equals("DD/MM/YYYY")) {
					if (!validation.isValidDate(_1BX_from_date)) {
						msg = validation.isValidDateMSG + " of From ";
	
						return msg;
					} 
				}
				if (_1BX_to_date != null || _1BX_to_date.trim().equals("") || _1BX_to_date.equals("DD/MM/YYYY")) {
					if (!validation.isValidDate(_1BX_to_date)) {
						msg = validation.isValidDateMSG + " of To ";
	
						return msg;
					}
				}
				if (format.parse(_1BX_to_date).compareTo(format.parse(_1BX_from_date)) < 0) {
					msg="To Date Should Be Greater Than Or Equal To From Date For Shape 1BX";		
					return msg;
				}
				
				if(_1BX_diagnosis.equals("") || _1BX_diagnosis == null){
					tx3.rollback();
					msg="Please  Enter The Diagnosis For Shape 1BX";				
					return msg;
				}
				if(!_1BX_diagnosis.equals("")) {
					 String[] arrOfStr = _1BX_diagnosis.split("-", 2); 
					 _1BX_diagnosis=arrOfStr[0];
				}
				String hqlUpdate = "delete from TB_MEDICAL_CATEGORY_JCO where jco_id=:id and status=0";
				int app = session3.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(jco_id)).executeUpdate();
				
				String li[] = {"S","H","A","P","E","C_C","C_O","C_P","C_E"};
				String li_id[] = {"sShape_ch_id1","hShape_ch_id1","aShape_ch_id1","pShape_ch_id1","eShape_ch_id1","cCope_ch_id1","oCope_ch_id1","pCope_ch_id1","eCope_ch_id1"};
				for(int i=0; i<li.length;i++) {
					String ch_id = request.getParameter(li_id[i]);
					TB_MEDICAL_CATEGORY_JCO Mad_cat;
//					if(Integer.parseInt(ch_id) == 0) {
						 Mad_cat = new TB_MEDICAL_CATEGORY_JCO();
//					}
//					else {
//						 Mad_cat =(TB_MEDICAL_CATEGORY_JCO)session3.get(TB_MEDICAL_CATEGORY_JCO.class, Integer.parseInt(ch_id));
//					}
					Mad_cat.setShape(li[i]);
					if (i==5 || i==8) {
						Mad_cat.setOther("");
					}
					if (i<5) {
						Mad_cat.setShape_status(1);
						Mad_cat.setShape_value("1");
					}
					else
						Mad_cat.setShape_value("0");
					
					if ((_1BX_from_date != null && !_1BX_from_date.equals("")) && (_1BX_to_date != null && !_1BX_to_date.equals(""))) {
						Mad_cat.setFrom_date_1bx(format.parse(_1BX_from_date));
						Mad_cat.setTo_date_1bx(format.parse(_1BX_to_date));
					}
					Mad_cat.setJco_id(Integer.parseInt(jco_id));
					Mad_cat.setDiagnosis_1bx(_1BX_diagnosis);
					Mad_cat.setClasification("NIL");
					Mad_cat.setAuthority(authority);
					////Mad_cat.setDate_of_authority(format.parse(date_of_authority));
					Mad_cat.setInitiated_from("u");
						
//					if(Integer.parseInt(ch_id) == 0) {
						Mad_cat.setCreated_by(username);
						Mad_cat.setCreated_on(date);
						
						session3.save(Mad_cat);
//					}
//					else {
//						Mad_cat.setModify_by(username);
//						Mad_cat.setModify_on(date);
//						session3.update(Mad_cat);
//					}
					
						
					
				}

				session3.flush();
				session3.clear();
				}
			
			else {	
		//////////////////////////////S SHAPE ////////////////////////////
		for (int i = 1; i <= sShapeOld_count; i++) {
			
			String shape_status = request.getParameter("s_status"+i);
			String shape_value = request.getParameter("sShape_value"+i);
			String from_date = request.getParameter("s_from_date"+i);
			String to_date = request.getParameter("s_to_date"+i);
			String diagnosis_1 = request.getParameter("_diagnosis_code1"+i);
	
			String shape_ch = request.getParameter("sShape_ch_id"+i);
			
		if(!diagnosis_1.equals("")) {
			 String[] arrOfStr = diagnosis_1.split("-", 2); 
			 diagnosis_1=arrOfStr[0];
		}

		

		
		if (shape_status == null || shape_status.equals("0")) {
			tx3.rollback();
			msg="Please Select The Shape Status "+i+" Row";			
			return msg;
		}
		if (shape_value == null || shape_value.equals("0")) {
			tx3.rollback();
			msg="Please Select The Shape Value "+i+" Row";			
			return msg;
		}
		//26-01-1994
//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
		if (!shape_status.equals("1")) {
			if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
				tx3.rollback();
				msg="Please Enter The From Date "+i+" Row";			
				return msg;
			}
			if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
				tx3.rollback();
				msg="Please Enter The To Date "+i+" Row";			
				return msg;
			}
			if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
				msg="To Date Should Be Greater Than Or Equal To From Date In Row "+i+"";		
				return msg;
			}
		}
		

			
		TB_MEDICAL_CATEGORY_JCO Mad_cat =(TB_MEDICAL_CATEGORY_JCO)session3.get(TB_MEDICAL_CATEGORY_JCO.class, Integer.parseInt(shape_ch));
		Mad_cat.setShape_status(Integer.parseInt(shape_status));
		Mad_cat.setShape_value(shape_value);
		
//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
		if (!shape_status.equals("1")) {
			Mad_cat.setFrom_date(format.parse(from_date));
			Mad_cat.setTo_date(format.parse(to_date));
		}
		else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
			Mad_cat.setFrom_date(format.parse(from_date));
		}
		
		Mad_cat.setFrom_date_1bx(null);
		Mad_cat.setTo_date_1bx(null);
		Mad_cat.setDiagnosis_1bx(null);
		Mad_cat.setDiagnosis(diagnosis_1);

		Mad_cat.setAuthority(authority);
	//	//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
		Mad_cat.setClasification(mad_classification);			
		Mad_cat.setModify_by(username);
		Mad_cat.setModify_on(date);	
		Mad_cat.setStatus(0);
		session3.update(Mad_cat);
		session3.flush();
		session3.clear();						

		}

		// Logic for INSERT

	
		
		 diffrence = sShape_count - sShapeOld_count;
		
		if (diffrence != 0) {
			TB_MEDICAL_CATEGORY_JCO Mad_cat = new TB_MEDICAL_CATEGORY_JCO();
			
			for (int i = sShapeOld_count + 1; i <= sShape_count; i++) {
				String shape_status = request.getParameter("s_status"+i);
				String shape_value = request.getParameter("sShape_value"+i);
				String from_date = request.getParameter("s_from_date"+i);
				String to_date = request.getParameter("s_to_date"+i);
				String diagnosis_1 = request.getParameter("_diagnosis_code1"+i);

				if(!diagnosis_1.equals("")) {
					 String[] arrOfStr = diagnosis_1.split("-", 2); 
					 diagnosis_1=arrOfStr[0];
				}

				

				
				if (shape_status == null || shape_status.equals("0")) {
					tx3.rollback();
					msg="Please Select The Shape Status "+i+" Row";			
					return msg;
				}
				if (shape_value == null || shape_value.equals("0")) {
					tx3.rollback();
					msg="Please Select The Shape Value "+i+" Row";			
					return msg;
				}
				
//				if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
				if (!shape_status.equals("1")) {
					if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
						tx3.rollback();
						msg="Please Enter The From Date "+i+" Row";			
						return msg;
					}
					if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
						tx3.rollback();
						msg="Please Enter The To Date "+i+" Row";			
						return msg;
					}
					if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
						msg="To Date Should Be Greater Than Or Equal To From Date In Row "+i+"";		
						return msg;
					}
				}
				
				
			
				
			Mad_cat.setShape("S");
			Mad_cat.setShape_status(Integer.parseInt(shape_status));
			Mad_cat.setShape_value(shape_value);
			
//			if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
			if (!shape_status.equals("1")) {
				Mad_cat.setFrom_date(format.parse(from_date));
				Mad_cat.setTo_date(format.parse(to_date));
			}
			else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
				Mad_cat.setFrom_date(format.parse(from_date));
			}
			
			Mad_cat.setDiagnosis(diagnosis_1);
			
			Mad_cat.setAuthority(authority);
			////Mad_cat.setDate_of_authority(format.parse(date_of_authority));
			Mad_cat.setClasification(mad_classification);
			Mad_cat.setJco_id(Integer.parseInt(jco_id));
			Mad_cat.setCreated_by(username);
			Mad_cat.setCreated_on(date);
			Mad_cat.setStatus(0);
			Mad_cat.setInitiated_from("u");
			 session3.save(Mad_cat);
				session3.flush();
				session3.clear();
			}
		}

		
		
		
//////////////////////////////H SHAPE ////////////////////////////
		
		
		for (int i = 1; i <= hShapeOld_count; i++) {

			String shape_status = request.getParameter("h_status"+i);
			String shape_value = request.getParameter("hShape_value"+i);
			String from_date = request.getParameter("h_from_date"+i);
			String to_date = request.getParameter("h_to_date"+i);
			String diagnosis_2 = request.getParameter("_diagnosis_code2"+i);
			String shape_ch = request.getParameter("hShape_ch_id"+i);
			
		if(!diagnosis_2.equals("")) {
			 String[] arrOfStr = diagnosis_2.split("-", 2); 
			 diagnosis_2=arrOfStr[0];
		}

		

		
		if (shape_status == null || shape_status.equals("0")) {
			tx3.rollback();
			msg="Please Select The Shape Status "+i+" Row";			
			return msg;
		}
		if (shape_value == null || shape_value.equals("0")) {
			tx3.rollback();
			msg="Please Select The Shape Value "+i+" Row";			
			return msg;
		}
		
//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
		if (!shape_status.equals("1")) {
			if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
				tx3.rollback();
				msg="Please Enter The From Date "+i+" Row";			
				return msg;
			}
			if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
				tx3.rollback();
				msg="Please Enter The To Date "+i+" Row";			
				return msg;
			}
			if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
				msg="To Date Should Be Greater Than Or Equal To From Date In Row "+i+"";		
				return msg;
			}
		}
		TB_MEDICAL_CATEGORY_JCO Mad_cat =(TB_MEDICAL_CATEGORY_JCO)session3.get(TB_MEDICAL_CATEGORY_JCO.class, Integer.parseInt(shape_ch));
		Mad_cat.setShape_status(Integer.parseInt(shape_status));
		Mad_cat.setShape_value(shape_value);
		
//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
		if (!shape_status.equals("1")) {
			Mad_cat.setFrom_date(format.parse(from_date));
			Mad_cat.setTo_date(format.parse(to_date));
		}
		else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
			Mad_cat.setFrom_date(format.parse(from_date));
		}
		Mad_cat.setFrom_date_1bx(null);
		Mad_cat.setTo_date_1bx(null);
		Mad_cat.setDiagnosis_1bx(null);
		Mad_cat.setDiagnosis(diagnosis_2);
		Mad_cat.setAuthority(authority);
		//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
		Mad_cat.setClasification(mad_classification);			
		Mad_cat.setModify_by(username);
		Mad_cat.setModify_on(date);	
		Mad_cat.setStatus(0);
		session3.update(Mad_cat);
		session3.flush();
		session3.clear();						

		}

		// Logic for INSERT

	
		
		 diffrence = hShape_count - hShapeOld_count;
		
		if (diffrence != 0) {
			TB_MEDICAL_CATEGORY_JCO Mad_cat = new TB_MEDICAL_CATEGORY_JCO();
			
			for (int i = hShapeOld_count + 1; i <= hShape_count; i++) {
				String shape_status = request.getParameter("h_status"+i);
				String shape_value = request.getParameter("hShape_value"+i);
				String from_date = request.getParameter("h_from_date"+i);
				String to_date = request.getParameter("h_to_date"+i);
				String diagnosis_2 = request.getParameter("_diagnosis_code2"+i);
				if(!diagnosis_2.equals("")) {
					 String[] arrOfStr = diagnosis_2.split("-", 2); 
					 diagnosis_2=arrOfStr[0];
				}

				

				
				if (shape_status == null || shape_status.equals("0")) {
					tx3.rollback();
					msg="Please Select The Shape Status "+i+" Row";			
					return msg;
				}
				if (shape_value == null || shape_value.equals("0")) {
					tx3.rollback();
					msg="Please Select The Shape Value "+i+" Row";			
					return msg;
				}
				
//				if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
				if (!shape_status.equals("1")) {
					if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
						tx3.rollback();
						msg="Please Enter The From Date "+i+" Row";			
						return msg;
					}
					if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
						tx3.rollback();
						msg="Please Enter The To Date "+i+" Row";			
						return msg;
					}
					if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
						msg="To Date Should Be Greater Than Or Equal To From Date In Row "+i+"";		
						return msg;
					}
				}
				
				
				
			
				
			Mad_cat.setShape("H");
			Mad_cat.setShape_status(Integer.parseInt(shape_status));
			Mad_cat.setShape_value(shape_value);
//			if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
			if (!shape_status.equals("1")) {
				Mad_cat.setFrom_date(format.parse(from_date));
				Mad_cat.setTo_date(format.parse(to_date));
			}
			else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
				Mad_cat.setFrom_date(format.parse(from_date));
			}
			Mad_cat.setDiagnosis(diagnosis_2);
			
			
			Mad_cat.setAuthority(authority);
			//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
			Mad_cat.setClasification(mad_classification);
			Mad_cat.setJco_id(Integer.parseInt(jco_id));
			Mad_cat.setCreated_by(username);
			Mad_cat.setCreated_on(date);
			Mad_cat.setStatus(0);
			Mad_cat.setInitiated_from("u");
			 session3.save(Mad_cat);
			
				session3.flush();
				session3.clear();
			}
		}
		
		
		
//		//////////////////////////////A SHAPE ////////////////////////////
		
		for (int i = 1; i <= aShapeOld_count; i++) {

			String shape_status = request.getParameter("a_status"+i);
			String shape_value = request.getParameter("aShape_value"+i);
			String from_date = request.getParameter("a_from_date"+i);
			String to_date = request.getParameter("a_to_date"+i);
			String diagnosis_3 = request.getParameter("_diagnosis_code3"+i);
			String shape_ch = request.getParameter("aShape_ch_id"+i);
			
		if(!diagnosis_3.equals("")) {
			 String[] arrOfStr = diagnosis_3.split("-", 2); 
			 diagnosis_3=arrOfStr[0];
		}

		

		
		if (shape_status == null || shape_status.equals("0")) {
			tx3.rollback();
			msg="Please Select The Shape Status "+i+" Row";			
			return msg;
		}
		if (shape_value == null || shape_value.equals("0")) {
			tx3.rollback();
			msg="Please Select The Shape Value "+i+" Row";			
			return msg;
		}
		
//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
		if (!shape_status.equals("1")) {
			if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
				tx3.rollback();
				msg="Please Enter The From Date "+i+" Row";			
				return msg;
			}
			if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
				tx3.rollback();
				msg="Please Enter The To Date "+i+" Row";			
				return msg;
			}
			if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
				msg="To Date Should Be Greater Than Or Equal To From Date In Row "+i+"";		
				return msg;
			}
		}
		
			
		TB_MEDICAL_CATEGORY_JCO Mad_cat =(TB_MEDICAL_CATEGORY_JCO)session3.get(TB_MEDICAL_CATEGORY_JCO.class, Integer.parseInt(shape_ch));
		Mad_cat.setShape_status(Integer.parseInt(shape_status));
		Mad_cat.setShape_value(shape_value);
//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("")) || (to_date != null && !to_date.equals(""))) {
		if (!shape_status.equals("1")) {
			Mad_cat.setFrom_date(format.parse(from_date));
			Mad_cat.setTo_date(format.parse(to_date));
		}
		else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
			Mad_cat.setFrom_date(format.parse(from_date));
		}
		Mad_cat.setFrom_date_1bx(null);
		Mad_cat.setTo_date_1bx(null);
		Mad_cat.setDiagnosis_1bx(null);
		Mad_cat.setDiagnosis(diagnosis_3);
		Mad_cat.setAuthority(authority);
		//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
		Mad_cat.setClasification(mad_classification);			
		Mad_cat.setModify_by(username);
		Mad_cat.setModify_on(date);	
		Mad_cat.setStatus(0);
		session3.update(Mad_cat);
		session3.flush();
		session3.clear();						

		}

		// Logic for INSERT

	
		
		 diffrence = aShape_count - aShapeOld_count;
		
		if (diffrence != 0) {
			TB_MEDICAL_CATEGORY_JCO Mad_cat = new TB_MEDICAL_CATEGORY_JCO();
			
			for (int i = aShapeOld_count + 1; i <= aShape_count; i++) {
				String shape_status = request.getParameter("a_status"+i);
				String shape_value = request.getParameter("aShape_value"+i);
				String from_date = request.getParameter("a_from_date"+i);
				String to_date = request.getParameter("a_to_date"+i);
				String diagnosis_3 = request.getParameter("_diagnosis_code3"+i);
		
				
				if(!diagnosis_3.equals("")) {
					 String[] arrOfStr = diagnosis_3.split("-", 2); 
					 diagnosis_3=arrOfStr[0];
				}

				
				if (shape_status == null || shape_status.equals("0")) {
					tx3.rollback();
					msg="Please Select The Shape Status "+i+" Row";			
					return msg;
				}
				if (shape_value == null || shape_value.equals("0")) {
					tx3.rollback();
					msg="Please Select The Shape Value "+i+" Row";			
					return msg;
				}
				
//				if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
				if (!shape_status.equals("1")) {
					if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
						tx3.rollback();
						msg="Please Enter The From Date "+i+" Row";			
						return msg;
					}
					if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
						tx3.rollback();
						msg="Please Enter The To Date "+i+" Row";			
						return msg;
					}
					if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
						msg="To Date Should Be Greater Than Or Equal To From Date In Row "+i+"";		
						return msg;
					}
				}
				
				
				
			
				
			Mad_cat.setShape("A");
			Mad_cat.setShape_status(Integer.parseInt(shape_status));
			Mad_cat.setShape_value(shape_value);
//			if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
			if (!shape_status.equals("1")) {
				Mad_cat.setFrom_date(format.parse(from_date));
				Mad_cat.setTo_date(format.parse(to_date));
			}
			else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
				Mad_cat.setFrom_date(format.parse(from_date));
			}
			Mad_cat.setDiagnosis(diagnosis_3);
			
			Mad_cat.setAuthority(authority);
			//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
			Mad_cat.setClasification(mad_classification);
			Mad_cat.setJco_id(Integer.parseInt(jco_id));
			Mad_cat.setCreated_by(username);
			Mad_cat.setCreated_on(date);
			Mad_cat.setStatus(0);
			Mad_cat.setInitiated_from("u");
			 session3.save(Mad_cat);
		
				session3.flush();
				session3.clear();
			}
		}
		
//		//////////////////////////////P SHAPE ////////////////////////////
		
		for (int i = 1; i <= pShapeOld_count; i++) {

			String shape_status = request.getParameter("p_status"+i);
			String shape_value = request.getParameter("pShape_value"+i);
			String from_date = request.getParameter("p_from_date"+i);
			String to_date = request.getParameter("p_to_date"+i);
			String diagnosis_4 = request.getParameter("_diagnosis_code4"+i);
			String shape_ch = request.getParameter("pShape_ch_id"+i);
			
		if(!diagnosis_4.equals("")) {
			 String[] arrOfStr = diagnosis_4.split("-", 2); 
			 diagnosis_4=arrOfStr[0];
		}

		

		
		if (shape_status == null || shape_status.equals("0")) {
			tx3.rollback();
			msg="Please Select The Shape Status "+i+" Row";			
			return msg;
		}
		if (shape_value == null || shape_value.equals("0")) {
			tx3.rollback();
			msg="Please Select The Shape Value "+i+" Row";			
			return msg;
		}
		
//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
		if (!shape_status.equals("1")) {
			if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
				tx3.rollback();
				msg="Please Enter The From Date "+i+" Row";			
				return msg;
			}
			if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
				tx3.rollback();
				msg="Please Enter The To Date "+i+" Row";			
				return msg;
			}
			if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
				msg="To Date Should Be Greater Than Or Equal To From Date In Row "+i+"";		
				return msg;
			}
		}
		
		TB_MEDICAL_CATEGORY_JCO Mad_cat =(TB_MEDICAL_CATEGORY_JCO)session3.get(TB_MEDICAL_CATEGORY_JCO.class, Integer.parseInt(shape_ch));
		Mad_cat.setShape_status(Integer.parseInt(shape_status));
		Mad_cat.setShape_value(shape_value);
//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
		if (!shape_status.equals("1")) {
			Mad_cat.setFrom_date(format.parse(from_date));
			Mad_cat.setTo_date(format.parse(to_date));
		}
		else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
			Mad_cat.setFrom_date(format.parse(from_date));
		}
		Mad_cat.setFrom_date_1bx(null);
		Mad_cat.setTo_date_1bx(null);
		Mad_cat.setDiagnosis_1bx(null);
		Mad_cat.setDiagnosis(diagnosis_4);
		Mad_cat.setAuthority(authority);
		//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
		Mad_cat.setClasification(mad_classification);			
		Mad_cat.setModify_by(username);
		Mad_cat.setModify_on(date);	
		Mad_cat.setStatus(0);
		session3.update(Mad_cat);
		session3.flush();
		session3.clear();						

		}
		
		
		
		 diffrence = pShape_count - pShapeOld_count;
		
			if (diffrence != 0) {
				TB_MEDICAL_CATEGORY_JCO Mad_cat = new TB_MEDICAL_CATEGORY_JCO();
				
				for (int i = pShapeOld_count + 1; i <= pShape_count; i++) {
					String shape_status = request.getParameter("p_status"+i);
					String shape_value = request.getParameter("pShape_value"+i);
					String from_date = request.getParameter("p_from_date"+i);
					String to_date = request.getParameter("p_to_date"+i);
					String diagnosis_4 = request.getParameter("_diagnosis_code4"+i);
					
					if(!diagnosis_4.equals("")) {
						 String[] arrOfStr = diagnosis_4.split("-", 2); 
						 diagnosis_4=arrOfStr[0];
					}

					

					
					if (shape_status == null || shape_status.equals("0")) {
						tx3.rollback();
						msg="Please Select The Shape Status "+i+" Row";			
						return msg;
					}
					if (shape_value == null || shape_value.equals("0")) {
						tx3.rollback();
						msg="Please Select The Shape Value "+i+" Row";			
						return msg;
					}
					
//					if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {
						if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
							tx3.rollback();
							msg="Please Enter The From Date "+i+" Row";			
							return msg;
						}
						if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
							tx3.rollback();
							msg="Please Enter The To Date "+i+" Row";			
							return msg;
						}
						if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
							msg="To Date Should Be Greater Than Or Equal To From Date In Row "+i+"";		
							return msg;
						}
					}
					
				Mad_cat.setShape("P");
				Mad_cat.setShape_status(Integer.parseInt(shape_status));
				Mad_cat.setShape_value(shape_value);
//				if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
				if (!shape_status.equals("1")) {
					Mad_cat.setFrom_date(format.parse(from_date));
					Mad_cat.setTo_date(format.parse(to_date));
				}
				else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
					Mad_cat.setFrom_date(format.parse(from_date));
				}
				Mad_cat.setDiagnosis(diagnosis_4);
				
				
				Mad_cat.setAuthority(authority);
				//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
				Mad_cat.setClasification(mad_classification);
				Mad_cat.setJco_id(Integer.parseInt(jco_id));
				Mad_cat.setCreated_by(username);
				Mad_cat.setCreated_on(date);
				Mad_cat.setStatus(0);
				Mad_cat.setInitiated_from("u");
				session3.save(Mad_cat);
					session3.flush();
					session3.clear();
				}
			}
			
//			//////////////////////////////E SHAPE ////////////////////////////
		
			for (int i = 1; i <= eShapeOld_count; i++) {

				String shape_status = request.getParameter("e_status"+i);
				String shape_value = request.getParameter("eShape_value"+i);
				String from_date = request.getParameter("e_from_date"+i);
				String to_date = request.getParameter("e_to_date"+i);
				String diagnosis_5 = request.getParameter("_diagnosis_code5"+i);
				String shape_ch = request.getParameter("eShape_ch_id"+i);
				
			if(!diagnosis_5.equals("")) {
				 String[] arrOfStr = diagnosis_5.split("-", 2); 
				 diagnosis_5=arrOfStr[0];
			}

			

			
			if (shape_status == null || shape_status.equals("0")) {
				tx3.rollback();
				msg="Please Select The Shape Status "+i+" Row";			
				return msg;
			}
			if (shape_value == null || shape_value.equals("0")) {
				tx3.rollback();
				msg="Please Select The Shape Value "+i+" Row";			
				return msg;
			}
			
//			if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
			if (!shape_status.equals("1")) {
				if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
					tx3.rollback();
					msg="Please Enter The From Date "+i+" Row";			
					return msg;
				}
				if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
					tx3.rollback();
					msg="Please Enter The To Date "+i+" Row";			
					return msg;
				}
				if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
					msg="To Date Should Be Greater Than Or Equal To From Date In Row "+i+"";		
					return msg;
				}
			}
			
				
			TB_MEDICAL_CATEGORY_JCO Mad_cat =(TB_MEDICAL_CATEGORY_JCO)session3.get(TB_MEDICAL_CATEGORY_JCO.class, Integer.parseInt(shape_ch));
			Mad_cat.setShape_status(Integer.parseInt(shape_status));
			Mad_cat.setShape_value(shape_value);
//			if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
			if (!shape_status.equals("1")) {
				Mad_cat.setFrom_date(format.parse(from_date));
				Mad_cat.setTo_date(format.parse(to_date));
			}else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
				Mad_cat.setFrom_date(format.parse(from_date));
			}
			Mad_cat.setFrom_date_1bx(null);
			Mad_cat.setTo_date_1bx(null);
			Mad_cat.setDiagnosis_1bx(null);
			Mad_cat.setDiagnosis(diagnosis_5);
			Mad_cat.setAuthority(authority);
			//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
			Mad_cat.setClasification(mad_classification);			
			Mad_cat.setModify_by(username);
			Mad_cat.setModify_on(date);	
			Mad_cat.setStatus(0);
			session3.update(Mad_cat);
			session3.flush();
			session3.clear();						

			}
			
			
			 diffrence = eShape_count - eShapeOld_count;
				
				if (diffrence != 0) {
					TB_MEDICAL_CATEGORY_JCO Mad_cat = new TB_MEDICAL_CATEGORY_JCO();
					
					for (int i = eShapeOld_count + 1; i <= eShape_count; i++) {
						String shape_status = request.getParameter("e_status"+i);
						String shape_value = request.getParameter("eShape_value"+i);
						String from_date = request.getParameter("e_from_date"+i);
						String to_date = request.getParameter("e_to_date"+i);
						String diagnosis_5 = request.getParameter("_diagnosis_code5"+i);
						if(!diagnosis_5.equals("")) {
							 String[] arrOfStr = diagnosis_5.split("-", 2); 
							 diagnosis_5=arrOfStr[0];
						}

						

						if (shape_status == null || shape_status.equals("0")) {
							tx3.rollback();
							msg="Please Select The Shape Status "+i+" Row";			
							return msg;
						}
						if (shape_value == null || shape_value.equals("0")) {
							tx3.rollback();
							msg="Please Select The Shape Value "+i+" Row";			
							return msg;
						}
						
//						if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {
							if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
								tx3.rollback();
								msg="Please Enter The From Date "+i+" Row";			
								return msg;
							}
							if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
								tx3.rollback();
								msg="Please Enter The To Date "+i+" Row";			
								return msg;
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
								msg="To Date Should Be Greater Than Or Equal To From Date In Row "+i+"";		
								return msg;
							}
						}
						
					
						
					Mad_cat.setShape("E");
					Mad_cat.setShape_status(Integer.parseInt(shape_status));
					Mad_cat.setShape_value(shape_value);
//					if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {
						Mad_cat.setFrom_date(format.parse(from_date));
						Mad_cat.setTo_date(format.parse(to_date));
					}
					else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
						Mad_cat.setFrom_date(format.parse(from_date));
					}
					Mad_cat.setDiagnosis(diagnosis_5);
					
					
					Mad_cat.setAuthority(authority);
					//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
					Mad_cat.setClasification(mad_classification);
					Mad_cat.setJco_id(Integer.parseInt(jco_id));
					Mad_cat.setCreated_by(username);
					Mad_cat.setCreated_on(date);
					Mad_cat.setStatus(0);
					Mad_cat.setInitiated_from("u");
				 session3.save(Mad_cat);
					
						session3.flush();
						session3.clear();
					}
				}
			
				
//				//////////////////////////////C COPE //////////////////////////////
				
				for (int i = 1; i <= cCopeOld_count; i++) {	
					String cope_ch = request.getParameter("cCope_ch_id"+i);					
					String cope_value = request.getParameter("c_cvalue"+i);							
					String cope_other = request.getParameter("c_cother"+i);	
					
					
					
					if(cope_value.equals("2 [c]") && cope_other.equals("")) {
						tx3.rollback();
						msg="Please Enter Other "+i+" Row";			
						return msg;
					}
					
				TB_MEDICAL_CATEGORY_JCO Mad_cat =(TB_MEDICAL_CATEGORY_JCO)session3.get(TB_MEDICAL_CATEGORY_JCO.class, Integer.parseInt(cope_ch));
				
				if(cope_value.equals("2 [c]")) {
					Mad_cat.setOther(cope_other);
				}
				else {
					Mad_cat.setOther("");
				}
				Mad_cat.setFrom_date_1bx(null);
				Mad_cat.setTo_date_1bx(null);
				Mad_cat.setDiagnosis_1bx(null);
				Mad_cat.setShape_value(cope_value);						
				Mad_cat.setAuthority(authority);
				////Mad_cat.setDate_of_authority(format.parse(date_of_authority));
				Mad_cat.setClasification(mad_classification);			
				Mad_cat.setModify_by(username);
				Mad_cat.setModify_on(date);	
				Mad_cat.setStatus(0);
				session3.update(Mad_cat);
				session3.flush();
				session3.clear();						

				}
				
				
				 diffrence = cCope_count - cCopeOld_count;
				
					if (diffrence != 0) {
						TB_MEDICAL_CATEGORY_JCO Mad_cat = new TB_MEDICAL_CATEGORY_JCO();
						
						for (int i = cCopeOld_count + 1; i <= cCope_count; i++) {						
							String cope_value = request.getParameter("c_cvalue"+i);							
							String cope_other = request.getParameter("c_cother"+i);				

							
							
							if(cope_value.equals("2 [c]") && cope_other.equals("")) {
								tx3.rollback();
								msg="Please Enter Other "+i+" Row";			
								return msg;
							}
						
							if(cope_value.equals("2 [c]")) {
								Mad_cat.setOther(cope_other);
							}
							else {
								Mad_cat.setOther(null);
							}
							Mad_cat.setShape("C_C");						
							Mad_cat.setShape_value(cope_value);						
							Mad_cat.setAuthority(authority);
							//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
							Mad_cat.setClasification(mad_classification);
							Mad_cat.setJco_id(Integer.parseInt(jco_id));
							Mad_cat.setCreated_by(username);
							Mad_cat.setCreated_on(date);
							Mad_cat.setStatus(0);
							Mad_cat.setInitiated_from("u");
							session3.save(Mad_cat);
						
							session3.flush();
							session3.clear();
						}
					}
				
//				//////////////////////////////O COPE //////////////////////////////
					
					for (int i = 1; i <= oCopeOld_count; i++) {	
						String cope_ch = request.getParameter("oCope_ch_id"+i);					
						String cope_value = request.getParameter("c_ovalue"+i);							


						
					TB_MEDICAL_CATEGORY_JCO Mad_cat =(TB_MEDICAL_CATEGORY_JCO)session3.get(TB_MEDICAL_CATEGORY_JCO.class, Integer.parseInt(cope_ch));
					Mad_cat.setFrom_date_1bx(null);
					Mad_cat.setTo_date_1bx(null);
					Mad_cat.setDiagnosis_1bx(null);
					Mad_cat.setShape_value(cope_value);						
					Mad_cat.setAuthority(authority);
					//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
					Mad_cat.setClasification(mad_classification);			
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);	
					Mad_cat.setStatus(0);
					session3.update(Mad_cat);
					session3.flush();
					session3.clear();						

					}
					
					
					 diffrence = oCope_count - oCopeOld_count;
					
						if (diffrence != 0) {
							TB_MEDICAL_CATEGORY_JCO Mad_cat = new TB_MEDICAL_CATEGORY_JCO();
							
							for (int i = oCopeOld_count + 1; i <= oCope_count; i++) {						
								String cope_value = request.getParameter("c_ovalue"+i);							

								
							Mad_cat.setShape("C_O");						
							Mad_cat.setShape_value(cope_value);						
							Mad_cat.setAuthority(authority);
							//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
							Mad_cat.setClasification(mad_classification);
							Mad_cat.setJco_id(Integer.parseInt(jco_id));
							Mad_cat.setCreated_by(username);
							Mad_cat.setCreated_on(date);
							Mad_cat.setStatus(0);
							Mad_cat.setInitiated_from("u");
						   session3.save(Mad_cat);
							
								session3.flush();
								session3.clear();
							}
						}
					
//				//////////////////////////////P COPE //////////////////////////////
		
				
						for (int i = 1; i <= pCopeOld_count; i++) {	
							String cope_ch = request.getParameter("pCope_ch_id"+i);					
							String cope_value = request.getParameter("c_pvalue"+i);							

							
						TB_MEDICAL_CATEGORY_JCO Mad_cat =(TB_MEDICAL_CATEGORY_JCO)session3.get(TB_MEDICAL_CATEGORY_JCO.class, Integer.parseInt(cope_ch));
						Mad_cat.setFrom_date_1bx(null);
						Mad_cat.setTo_date_1bx(null);
						Mad_cat.setDiagnosis_1bx(null);
						Mad_cat.setShape_value(cope_value);						
						Mad_cat.setAuthority(authority);
						//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
						Mad_cat.setClasification(mad_classification);			
						Mad_cat.setModify_by(username);
						Mad_cat.setModify_on(date);	
						Mad_cat.setStatus(0);
						session3.update(Mad_cat);
						session3.flush();
						session3.clear();						

						}
						
						
						 diffrence = pCope_count - pCopeOld_count;
						
							if (diffrence != 0) {
								TB_MEDICAL_CATEGORY_JCO Mad_cat = new TB_MEDICAL_CATEGORY_JCO();
								
								for (int i = pCopeOld_count + 1; i <= pCope_count; i++) {						
									String cope_value = request.getParameter("c_pvalue"+i);							

									
								Mad_cat.setShape("C_P");						
								Mad_cat.setShape_value(cope_value);						
								Mad_cat.setAuthority(authority);
								//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
								Mad_cat.setClasification(mad_classification);
								Mad_cat.setJco_id(Integer.parseInt(jco_id));
								Mad_cat.setCreated_by(username);
								Mad_cat.setCreated_on(date);
								Mad_cat.setStatus(0);
								Mad_cat.setInitiated_from("u");
							 session3.save(Mad_cat);
								
									session3.flush();
									session3.clear();
								}
							}
							
//				//////////////////////////////E COPE //////////////////////////////
				
							for (int i = 1; i <= eCopeOld_count; i++) {	
								String cope_ch = request.getParameter("eCope_ch_id"+i);					
								String cope_value = request.getParameter("c_evalue"+i);
								String cope_sub_value = request.getParameter("c_esubvalue"+i);
								String cope_other = request.getParameter("c_esubvalueother"+i);	

								
								if(cope_value.equals("1") && cope_sub_value.equals("0")) {
									tx3.rollback();
									msg="Please Select The Cope Sub Value "+i+" Row";			
									return msg;
								}
								
								if(cope_sub_value.equals("k") && cope_other.equals("")) {
									tx3.rollback();
									msg="Please Enter Other "+i+" Row";			
									return msg;
								}
							
							TB_MEDICAL_CATEGORY_JCO Mad_cat =(TB_MEDICAL_CATEGORY_JCO)session3.get(TB_MEDICAL_CATEGORY_JCO.class, Integer.parseInt(cope_ch));
							
							if(cope_value.equals("1")) {
								Mad_cat.setShape_sub_value(cope_sub_value);
							}
							else {
								Mad_cat.setShape_sub_value(null);
							}
							if(cope_sub_value.equals("k")) {
								Mad_cat.setOther(cope_other);
							}
							else {
								Mad_cat.setOther(null);
							}
							Mad_cat.setFrom_date_1bx(null);
							Mad_cat.setTo_date_1bx(null);
							Mad_cat.setDiagnosis_1bx(null);
							Mad_cat.setShape_value(cope_value);						
							Mad_cat.setAuthority(authority);
							//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
							Mad_cat.setClasification(mad_classification);			
							Mad_cat.setModify_by(username);
							Mad_cat.setModify_on(date);	
							Mad_cat.setStatus(0);
							session3.update(Mad_cat);
							session3.flush();
							session3.clear();						

							}
							
							
							 diffrence = eCope_count - eCopeOld_count;
							
								if (diffrence != 0) {
									TB_MEDICAL_CATEGORY_JCO Mad_cat = new TB_MEDICAL_CATEGORY_JCO();
									
									for (int i = eCopeOld_count + 1; i <= eCope_count; i++) {						
										String cope_value = request.getParameter("c_evalue"+i);
										String cope_sub_value = request.getParameter("c_esubvalue"+i);
										String cope_other = request.getParameter("c_esubvalueother"+i);				


										if(cope_value.equals("1") && cope_sub_value.equals("0")) {
											tx3.rollback();
											msg="Please Select The Cope Sub Value "+i+" Row";			
											return msg;
										}
										
										if(cope_sub_value.equals("k") && cope_other.equals("")) {
											tx3.rollback();
											msg="Please Enter Other "+i+" Row";			
											return msg;
										}
									
										if(cope_value.equals("1")) {
											Mad_cat.setShape_sub_value(cope_sub_value);
										}
										else {
											Mad_cat.setShape_sub_value("0");
										}
										
										if(cope_sub_value.equals("k")) {
											Mad_cat.setOther(cope_other);
										}
										else {
											Mad_cat.setOther("");
										}
										Mad_cat.setShape("C_E");						
										Mad_cat.setShape_value(cope_value);						
										Mad_cat.setAuthority(authority);
										//Mad_cat.setDate_of_authority(format.parse(date_of_authority));
										Mad_cat.setClasification(mad_classification);
										Mad_cat.setJco_id(Integer.parseInt(jco_id));
										Mad_cat.setCreated_by(username);
										Mad_cat.setCreated_on(date);
										Mad_cat.setStatus(0);
										Mad_cat.setInitiated_from("u");
										session3.save(Mad_cat);
									
										session3.flush();
										session3.clear();
									}
								}
			}	
			
	
				
					tx3.commit();
			
				
		
		msg="1";
		
		
		} catch (RuntimeException e) {
			try {
				tx3.rollback();
			
				msg = "Data not Save";
			} catch (RuntimeException rbe) {
				msg = "Data not Save";
				
			}
		
		} finally {
			if (session3 != null) {
				session3.close();
			}
		}	
		
		return msg;
	}


}
