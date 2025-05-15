package com.controller.psg.Jco_Update_Census;



import java.text.ParseException;

import java.util.Date;

import java.util.List;



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

import org.springframework.web.servlet.ModelAndView;




import com.controller.psg.Jco_COMMON.psg_jco_CommonController;

import com.controller.psg.Master.Psg_CommonController;

import com.dao.psg.JCO_Update_Census.Update_Census_Details_JCO_DAO;

import com.dao.psg.Transaction.Search_UpdateComm_Dao;

import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;

import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_SIBLINGS;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_AADHARNO_JCO;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_BIRTH_JCO;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_GENDER_JCO;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_HEIGHT_JCO;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO;

import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_PAN_NO_JCO;

import com.persistance.util.HibernateUtil;



@Controller

@RequestMapping(value = {"admin","/","user"})



public class Appove_Update_Census_Controller_JCO {

	

	

	Psg_CommonController p_comm = new Psg_CommonController();

	

	psg_jco_CommonController jcom =new psg_jco_CommonController();

	

	//CommanController m = new CommanController();

	

	Birth_UpdateController_JCO birth = new Birth_UpdateController_JCO();

	

	Gender_UpdateController_JCO gender = new Gender_UpdateController_JCO();

	

	Address_Birth_UpdateController_JCO addBirth = new Address_Birth_UpdateController_JCO();

	

	Nationality_UpdateController_JCO Natina = new Nationality_UpdateController_JCO();

	

	Mother_Tongue_UpdateController_JCO Moto = new Mother_Tongue_UpdateController_JCO();

	

	Blood_Group_UpdateController_JCO BG = new Blood_Group_UpdateController_JCO();

	

	Height_UpdateController_JCO HE = new Height_UpdateController_JCO();

	

	Aadhar_UpdateController_JCO ADN = new Aadhar_UpdateController_JCO(); 

	

	Pan_No_UpdateController_JCO PN = new Pan_No_UpdateController_JCO();

	

	Enrollment_Class_Update_Controller EC = new Enrollment_Class_Update_Controller();

	

	Rank_UpdateController RN = new Rank_UpdateController();

	

	Date_EnrollmentUpdateController DE = new Date_EnrollmentUpdateController();

	

	Date_AttestationUpdateController DA = new Date_AttestationUpdateController();

	

	Family_Details_UpdateController_JCO FD = new Family_Details_UpdateController_JCO();

	

	Sibling_UpdateController SB = new Sibling_UpdateController(); 

	

	@Autowired

	Search_UpdateComm_Dao UCO;

		

	@Autowired

	Search_UpdateComm_Dao scldao;

	

	@Autowired

	Update_Census_Details_JCO_DAO UCD;

	

	@RequestMapping(value = "/Approve_UpadteComm_DataUrl_JCO", method = RequestMethod.POST)

	public ModelAndView Approve_UpadteComm_DataUrl(@ModelAttribute("id2") String updateid,

			@ModelAttribute("army_no2") String army_no2,

			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,

			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {



		

		

		List<TB_PSG_UPDATE_CENSUS_BIRTH_JCO> ChangeOfdob = birth.getupdate_census_dobData2(Integer.parseInt(updateid));

		

		List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> ChangeOfgender = gender.getupdate_census_GenderData2(Integer.parseInt(updateid));

		

		List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> ChangeOfAddressBirth = addBirth.getupdate_census_ADDBirData2(Integer.parseInt(updateid));

		

		List<TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO> ChangeOfNationality = Natina.getupdate_census_NatinalityData2(Integer.parseInt(updateid));

		

		List<TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO> ChangeOfMotherTongue = Moto.getupdate_census_Mother_TongueData2(Integer.parseInt(updateid));

		

		List<TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO> ChangeOfBloodGroup = BG.getupdate_census_Blood_GroupData2(Integer.parseInt(updateid));

		

		List<TB_PSG_UPDATE_CENSUS_HEIGHT_JCO> ChangeOfHeight = HE.getupdate_census_HeightData2(Integer.parseInt(updateid));

		

		List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> ChangeOfAadhar = ADN.getupdate_census_AadharData2(Integer.parseInt(updateid));

		

		List<TB_PSG_UPDATE_CENSUS_PAN_NO_JCO> ChangeOfPanNo = PN.getupdate_census_PanNoData2(Integer.parseInt(updateid));

		

		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> ChangeOfClassEnroll = EC.getupdate_census_EnrollClassData2(Integer.parseInt(updateid));

		

//		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> ChangeOfRank = RN.getupdate_census_RankData2(Integer.parseInt(updateid));

		

		List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> ChangeOfDtofEnrolment = DE.getupdate_census_Dt_Of_EnrolmentData2(Integer.parseInt(updateid));

		

		List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> ChangeOfDtofAttestation = DA.getupdate_census_Dt_Of_AttesationData2(Integer.parseInt(updateid));

		

		List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> ChangeOfFamily = FD.getupdate_census_FamilyData2(Integer.parseInt(updateid));

		

		List<TB_CENSUS_JCO_OR_SIBLINGS> ChangeOfSibling = SB.getupdate_census_SiblingData2(Integer.parseInt(updateid));

		

		Mmap.put("msg", msg);

		Mmap.put("id", updateid);

		Mmap.put("army_no2", army_no2);

		Mmap.put("getCourseNameList", p_comm.getCourseNameList());

		Mmap.put("getGenderList", p_comm.getGenderList());

		Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());

		Mmap.put("getRegtList", p_comm.getRegtList(""));

		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());

		Mmap.put("getPersonalRemainder", p_comm.getPersonalRemainder());

		Mmap.put("getParentArmList", p_comm.getParentArmList());

		Mmap.put("getArmyType", p_comm.getArmyType());

		Mmap.put("getMedCountryName", p_comm.getMedCountryName("", session));

		Mmap.put("getMedStateName", p_comm.getMedStateName("", session));

		Mmap.put("getMedDistrictName", p_comm.getMedDistrictName("", session));

		Mmap.put("getNationalityList", p_comm.getNationalityList());

		Mmap.put("getMothertoungeList", p_comm.getMothertoungeList());

		Mmap.put("getBloodList", p_comm.getBloodList());

		Mmap.put("getHeight", p_comm.getHeight());

		Mmap.put("getClass_enrollList", jcom.getClass_enrollList());

		Mmap.put("getRankjcoList", jcom.getRankjcoList());

		Mmap.put("getExservicemenList", p_comm.getExservicemenList());

		Mmap.put("getProfession", p_comm.getProfession());

		Mmap.put("getFamily_siblings", p_comm.getFamily_siblings());

		Mmap.put("getRank_intakeList", jcom.getRank_intakeList());

		Mmap.put("getclass_domicileList", jcom.getclass_domicileList());

		

		Mmap.put("ChangeOfdob", ChangeOfdob.size());

		Mmap.put("ChangeOfgender", ChangeOfgender.size());

		Mmap.put("ChangeOfAddressBirth", ChangeOfAddressBirth.size());

		Mmap.put("ChangeOfNationality", ChangeOfNationality.size());

		Mmap.put("ChangeOfMotherTongue", ChangeOfMotherTongue.size());

		Mmap.put("ChangeOfBloodGroup", ChangeOfBloodGroup.size());

		Mmap.put("ChangeOfHeight", ChangeOfHeight.size());

		Mmap.put("ChangeOfAadhar", ChangeOfAadhar.size());

		Mmap.put("ChangeOfPanNo", ChangeOfPanNo.size());

		Mmap.put("ChangeOfClassEnroll", ChangeOfClassEnroll.size());

//		Mmap.put("ChangeOfRank", ChangeOfRank.size());

		Mmap.put("ChangeOfDtofEnrolment", ChangeOfDtofEnrolment.size());

		Mmap.put("ChangeOfDtofAttestation", ChangeOfDtofAttestation.size());

		Mmap.put("ChangeOfFamily", ChangeOfFamily.size());

		Mmap.put("ChangeOfSibling", ChangeOfSibling.size());

		

		return new ModelAndView("JCO_App_Update_Comm_Data_Tiles");

	}



	 /****************************************** Approval Action ************************************************/ 

	

	@RequestMapping(value = "/Approve_Update_Census_DataAction_JCO", method = RequestMethod.POST)

	public ModelAndView Approve_Update_Census_DataAction_JCO(@ModelAttribute("Approve_Update_Census_DataCMD_JCO") TB_CENSUS_JCO_OR_PARENT P, BindingResult result,

			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {

		

		String username = session.getAttribute("username").toString();

		

		

		

		List<TB_PSG_UPDATE_CENSUS_BIRTH_JCO> ChangeOfdob = birth.getupdate_census_dobData2(Integer.parseInt(request.getParameter("jco_id")));

		

		List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> ChangeOfgender = gender.getupdate_census_GenderData2(Integer.parseInt(request.getParameter("jco_id")));

		

		List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> ChangeOfAddressBirth = addBirth.getupdate_census_ADDBirData2(Integer.parseInt(request.getParameter("jco_id")));

		

		List<TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO> ChangeOfNationality = Natina.getupdate_census_NatinalityData2(Integer.parseInt(request.getParameter("jco_id")));

		

		List<TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO> ChangeOfMotherTongue = Moto.getupdate_census_Mother_TongueData2(Integer.parseInt(request.getParameter("jco_id")));

		

		List<TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO> ChangeOfBloodGroup = BG.getupdate_census_Blood_GroupData2(Integer.parseInt(request.getParameter("jco_id")));

		

		List<TB_PSG_UPDATE_CENSUS_HEIGHT_JCO> ChangeOfHeight = HE.getupdate_census_HeightData2(Integer.parseInt(request.getParameter("jco_id")));

		

		List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> ChangeOfAadhar = ADN.getupdate_census_AadharData2(Integer.parseInt(request.getParameter("jco_id")));

		

		List<TB_PSG_UPDATE_CENSUS_PAN_NO_JCO> ChangeOfPanNo = PN.getupdate_census_PanNoData2(Integer.parseInt(request.getParameter("jco_id")));

		

		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> ChangeOfClassEnroll = EC.getupdate_census_EnrollClassData2(Integer.parseInt(request.getParameter("jco_id")));

		

//		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> ChangeOfRank = RN.getupdate_census_RankData2(Integer.parseInt(request.getParameter("jco_id")));

		

		List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> ChangeOfDtofEnrolment = DE.getupdate_census_Dt_Of_EnrolmentData2(Integer.parseInt(request.getParameter("jco_id")));

		

		List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> ChangeOfDtofAttestation = DA.getupdate_census_Dt_Of_AttesationData2(Integer.parseInt(request.getParameter("jco_id")));

		

		List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> ChangeOfFamily = FD.getupdate_census_FamilyData2(Integer.parseInt(request.getParameter("jco_id")));

		

		List<TB_CENSUS_JCO_OR_SIBLINGS> ChangeOfSibling = SB.getupdate_census_SiblingData2(Integer.parseInt(request.getParameter("jco_id")));

		

		

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		Date modified_date = new Date();

		

		try {

			

			TB_CENSUS_JCO_OR_PARENT Comm = new TB_CENSUS_JCO_OR_PARENT();

			Comm.setId(Integer.parseInt(request.getParameter("jco_id")));

			P.setId(Integer.parseInt(request.getParameter("jco_id")));

			P.setModified_date(modified_date);

			P.setUpdate_census_status(1);

			

			

			

			Mmap.put("msg",Update_Census_Data_Status_JCo(P, username));

			

			if (ChangeOfdob.size() > 0) {

				P.setDate_of_birth(ChangeOfdob.get(0).getDate_of_birth());

				TB_PSG_UPDATE_CENSUS_BIRTH_JCO Changebirth = new TB_PSG_UPDATE_CENSUS_BIRTH_JCO();

				Changebirth.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				Changebirth.setModified_date(modified_date);

				Mmap.put("msg", birth.Update_birth_census_details_JCO(P, username));

				Mmap.put("msg", birth.Update_Birth_JCO(Changebirth, username));

			}

			

			if (ChangeOfgender.size() > 0) {

				P.setGender(ChangeOfgender.get(0).getGender());

				TB_PSG_UPDATE_CENSUS_GENDER_JCO ChangeOfGender = new TB_PSG_UPDATE_CENSUS_GENDER_JCO();

				ChangeOfGender.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeOfGender.setModified_date(modified_date);

				Mmap.put("msg", gender.Update_Gender_census_details_JCO(P, username));

				Mmap.put("msg", gender.Update_Gender_JCO(ChangeOfGender, username));

			}

			

			if (ChangeOfAddressBirth.size() > 0) {

				P.setPlace_of_birth(ChangeOfAddressBirth.get(0).getPlace_of_birth());

				P.setCountry_of_birth(ChangeOfAddressBirth.get(0).getCountry_of_birth());

				P.setState_of_birth(ChangeOfAddressBirth.get(0).getState_of_birth());

				P.setDistrict_of_birth(ChangeOfAddressBirth.get(0).getDistrict_of_birth());

				P.setCountry_other(ChangeOfAddressBirth.get(0).getCountry_other());

				P.setState_other(ChangeOfAddressBirth.get(0).getState_other());

				P.setDistrict_other(ChangeOfAddressBirth.get(0).getDistrict_other());

				TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO ChangeOfAddBirth = new TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO();

				ChangeOfAddBirth.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeOfAddBirth.setModified_date(modified_date);

				Mmap.put("msg", addBirth.Update_addbirth_census_details_JCO(P, username));

				Mmap.put("msg", addBirth.Update_addbirth_JCO(ChangeOfAddBirth, username));

			}

			

			if (ChangeOfNationality.size() > 0) {

				P.setNationality(ChangeOfNationality.get(0).getNationality());

				P.setNationality_other(ChangeOfNationality.get(0).getNationality_other());

				TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO ChangeOfNat = new TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO();

				ChangeOfNat.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeOfNat.setModified_date(modified_date);

				Mmap.put("msg", Natina.Update_Nationality_census_details_JCO(P, username));

				Mmap.put("msg", Natina.Update_Nationality_JCO(ChangeOfNat, username));

			}

			

			if (ChangeOfMotherTongue.size() > 0) {

				P.setMother_tongue(ChangeOfMotherTongue.get(0).getMother_tounge());

				P.setMother_tongue_other(ChangeOfMotherTongue.get(0).getMother_tongue_other());

				TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO ChangeOfMoto = new TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO();

				ChangeOfMoto.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeOfMoto.setModified_date(modified_date);

				Mmap.put("msg", Moto.Update_Mother_Tongue_census_details_JCO(P, username));

				Mmap.put("msg", Moto.Update_Mother_Tongue_JCO(ChangeOfMoto, username));

			}

			

			if (ChangeOfBloodGroup.size() > 0) {

				P.setBlood_group(ChangeOfBloodGroup.get(0).getBlood_group());

				TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO ChangeOfBG = new TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO();

				ChangeOfBG.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeOfBG.setModified_date(modified_date);

				Mmap.put("msg", BG.Update_Blood_Group_census_details_JCO(P, username));

				Mmap.put("msg", BG.Update_Blood_Group_JCO(ChangeOfBG, username));

			}

			

			if (ChangeOfHeight.size() > 0) {

				P.setHeight(ChangeOfHeight.get(0).getHeight());

				TB_PSG_UPDATE_CENSUS_HEIGHT_JCO ChangeOfHei = new TB_PSG_UPDATE_CENSUS_HEIGHT_JCO();

				ChangeOfHei.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeOfHei.setModified_date(modified_date);

				Mmap.put("msg", HE.Update_Height_details_JCO(P, username));

				Mmap.put("msg", HE.Update_HeightJCO(ChangeOfHei, username));

			}

			

			if (ChangeOfAadhar.size() > 0) {

				P.setAadhar_no(ChangeOfAadhar.get(0).getAadhar_no());

				TB_PSG_UPDATE_CENSUS_AADHARNO_JCO ChangeOfAadn = new TB_PSG_UPDATE_CENSUS_AADHARNO_JCO();

				ChangeOfAadn.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeOfAadn.setModified_date(modified_date);

				Mmap.put("msg", ADN.Update_Aadhar_details_JCO(P, username));

				Mmap.put("msg", ADN.Update_AadharJCO(ChangeOfAadn, username));

			}

			

			if (ChangeOfPanNo.size() > 0) {

				P.setPan_no(ChangeOfPanNo.get(0).getPan_no());

				TB_PSG_UPDATE_CENSUS_PAN_NO_JCO ChangeOfPN = new TB_PSG_UPDATE_CENSUS_PAN_NO_JCO();

				ChangeOfPN.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeOfPN.setModified_date(modified_date);

				Mmap.put("msg", PN.Update_PanNo_details_JCO(P, username));

				Mmap.put("msg", PN.Update_PanNoJCO(ChangeOfPN, username));

			}

			

			if (ChangeOfClassEnroll.size() > 0) {

				P.setClass_enroll(ChangeOfClassEnroll.get(0).getClass_enroll());

				P.setDomicile(ChangeOfClassEnroll.get(0).getDomicile());

				TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO ClassEnrolli = new TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO();

				ClassEnrolli.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ClassEnrolli.setModified_date(modified_date);

				Mmap.put("msg", EC.Update_EnrollClass_details_JCO(P, username));

				Mmap.put("msg", EC.Update_EnrollClassJCO(ClassEnrolli, username));

			}

			

//			if (ChangeOfRank.size() > 0) {

//				P.setRank(ChangeOfRank.get(0).getRank());

//				P.setRank_intake(ChangeOfRank.get(0).getRank_intake());

//				TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO ChangeOfRN = new TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO();

//				ChangeOfRN.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

//				ChangeOfRN.setModified_date(modified_date);

//				Mmap.put("msg", RN.Update_Rank_details_JCO(P, username));

//				Mmap.put("msg", RN.Update_RankJCO(ChangeOfRN, username));

//			}

			

			if (ChangeOfDtofEnrolment.size() > 0) {

				P.setEnroll_dt(ChangeOfDtofEnrolment.get(0).getDate_of_enrollment());

				TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO ChangeOfDE = new TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO();

				ChangeOfDE.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeOfDE.setModified_date(modified_date);

				Mmap.put("msg", DE.Update_Dt_of_Enrolment_details_JCO(P, username));

				Mmap.put("msg", DE.Update_Dt_Of_EnrolmentJCO(ChangeOfDE, username));

			}

			

			if (ChangeOfDtofAttestation.size() > 0) {

				P.setDate_of_attestation(ChangeOfDtofAttestation.get(0).getDate_of_attestation());

				TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO ChangeOfDA = new TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO();

				ChangeOfDA.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeOfDA.setModified_date(modified_date);

				Mmap.put("msg", DA.Update_Dt_of_Attestation_details_JCO(P, username));

				Mmap.put("msg", DA.Update_Dt_Of_AttestationJCO(ChangeOfDA, username));

			}

			

			if (ChangeOfFamily.size() > 0) {

				P.setFather_dob(ChangeOfFamily.get(0).getFather_dob());

				P.setFather_name(ChangeOfFamily.get(0).getFather_name());

				P.setFather_place_birth(ChangeOfFamily.get(0).getFather_place_birth());

				P.setFather_service(ChangeOfFamily.get(0).getFather_service());

				P.setFather_profession(ChangeOfFamily.get(0).getFather_profession());

				P.setMother_name(ChangeOfFamily.get(0).getMother_name());

				P.setMother_place_birth(ChangeOfFamily.get(0).getMother_place_birth());

				P.setMother_profession(ChangeOfFamily.get(0).getMother_profession());

				P.setMother_service(ChangeOfFamily.get(0).getMother_service());

				P.setMother_dob(ChangeOfFamily.get(0).getMother_dob());

				P.setFather_other(ChangeOfFamily.get(0).getFather_other());

				P.setMother_other(ChangeOfFamily.get(0).getMother_other());

				P.setFather_proffother(ChangeOfFamily.get(0).getFather_proffother());

				P.setMother_proffother(ChangeOfFamily.get(0).getMother_proffother());

				TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO ChangeOfFD = new TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO();

				ChangeOfFD.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeOfFD.setModified_date(modified_date);

				Mmap.put("msg", FD.Update_Family_details_JCO(P, username));

				Mmap.put("msg", FD.Update_FamilyJCO(ChangeOfFD, username));

			}

			

			if (ChangeOfSibling.size() > 0) {

				TB_CENSUS_JCO_OR_SIBLINGS ChangeOfSB = new TB_CENSUS_JCO_OR_SIBLINGS();

				ChangeOfSB.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeOfSB.setModified_date(modified_date);

				Mmap.put("msg", SB.Update_sibling_details_JCO(ChangeOfSB,username));

				Mmap.put("msg", SB.Update_SiblingJCO(ChangeOfSB, username));

			}

			

			

			   if(check_Update_Census_DataStatus(Integer.parseInt(request.getParameter("jco_id")))) {

			       	  Mmap.put("msg",  Update_Census_JCo(P, username,3));

			         }

			         else {

			        	 Mmap.put("msg",  Update_Census_JCo(P, username,1));

			         }

			   

			tx.commit();

		

		} catch (RuntimeException e) {

			e.printStackTrace();

			try {

				tx.rollback();

				Mmap.put("msg", "roll back transaction");

			} catch (RuntimeException rbe) {

				Mmap.put("msg", "Couldn?t roll back transaction " + rbe);

			}

			throw e;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

		return new ModelAndView("redirect:Search_Update_Census_Url_JCO");

	}

	

	public String Update_Census_Data_Status_JCo(TB_CENSUS_JCO_OR_PARENT obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_CENSUS_JCO_OR_PARENT set update_census_status=:update_census_status,modified_by=:modified_by,modified_date=:modified_date "

					+ "where id=:jco_id and status = '1'  and update_census_status != 3  ";

			Query query = sessionHQL.createQuery(hql).setInteger("update_census_status",1)

					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())

					.setInteger("jco_id", obj.getId());

			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			

			tx.commit();

		} catch (Exception e) {

			e.printStackTrace();

			msg = "Data Not Updated.";

			tx.rollback();

		} finally {

			sessionHQL.close();

		}

		return msg;

	}

	

	

	public Boolean check_Update_Census_DataStatus(int jco_id ) throws ParseException{

			

			

			List<TB_PSG_UPDATE_CENSUS_BIRTH_JCO> ChangeOfdob = birth.getdobData_JCO3(jco_id);

			

			List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> ChangeOfgender = gender.getGenderData_JCO3(jco_id);

			

			List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> ChangeOfAddressBirth = addBirth.getAddBirData_JCO3(jco_id);

			

			List<TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO> ChangeOfNationality = Natina.getNationalityData_JCO3(jco_id);

			

			List<TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO> ChangeOfMotherTongue = Moto.getMother_TongueData_JCO3(jco_id);

			

			List<TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO> ChangeOfBloodGroup = BG.getBlood_GroupData_JCO3(jco_id);

			

			List<TB_PSG_UPDATE_CENSUS_HEIGHT_JCO> ChangeOfHeight = HE.getHeightData_JCO3(jco_id);

			

			List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> ChangeOfAadhar = ADN.getAadharData_JCO3(jco_id);

			

			List<TB_PSG_UPDATE_CENSUS_PAN_NO_JCO> ChangeOfPanNo = PN.getPanNoData_JCO3(jco_id);

			

			List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> ChangeOfClassEnroll = EC.getEnrollClassData_JCO3(jco_id);

			

//			List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> ChangeOfRank = RN.getRankData_JCO3(jco_id);

			

			List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> ChangeOfDtofEnrolment = DE.getDt_of_EnrolmentData_JCO3(jco_id);

			

			List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> ChangeOfDtofAttestation = DA.getDt_of_Attestation_JCO3(jco_id);

			

			List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> ChangeOfFamily = FD.getFamily_JCO3(jco_id);

			

			List<TB_CENSUS_JCO_OR_SIBLINGS> ChangeOfSibling = SB.getSibling_JCO3(jco_id);

			

			

	

		    if(ChangeOfdob.size()>0) {

				 return true;

			 }

		    if(ChangeOfgender.size()>0) {

				 return true;

			 }

		    if(ChangeOfAddressBirth.size()>0) {

				 return true;

			 }

		    if(ChangeOfNationality.size()>0) {

				 return true;

			 }

		    if(ChangeOfMotherTongue.size()>0) {

				 return true;

			 }

		    if(ChangeOfBloodGroup.size()>0) {

				 return true;

			 }

		    if(ChangeOfHeight.size()>0) {

				 return true;

			 }

		    if(ChangeOfAadhar.size()>0) {

				 return true;

			 }

		    if(ChangeOfClassEnroll.size()>0) {

				 return true;

			 }

		    if(ChangeOfPanNo.size()>0) {

				 return true;

			 }

//		    if(ChangeOfRank.size()>0) {

//				 return true;

//			 }

		    if(ChangeOfDtofEnrolment.size()>0) {

				 return true;

			 }

		    if(ChangeOfDtofAttestation.size()>0) {

				 return true;

			 }

		    if(ChangeOfFamily.size()>0) {

				 return true;

			 }

		    if(ChangeOfSibling.size()>0) {

				 return true;

			 }

		    

	

		    return false;

	}





	public String Update_Census_JCo(TB_CENSUS_JCO_OR_PARENT obj,String username,int update_census_status){

	

	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

	    Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

	

		  try{

			  String hql1 = "update TB_CENSUS_JCO_OR_PARENT set modified_by=:modified_by,modified_date=:modified_date,"

						+ "update_census_status=:update_census_status where id=:jco_id and status in ('1','5') and update_census_status != '3' ";

	

				Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)

						.setTimestamp("modified_date", obj.getModified_date()).setInteger("update_census_status", update_census_status)

						.setInteger("jco_id", obj.getId());

		 

				   msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

		          tx.commit();


		 }

		  catch (Exception e) {

			  e.printStackTrace();

		          msg = "Data Not Updated.";

		          tx.rollback();

		  }

		  finally {

		          sessionHQL.close();

		  }

	

		  return msg;

	}

	

	

/********************************************** APPROVE VIEW START ****************************************************************/

	

	@RequestMapping(value = "/View_ApproveUpadteCensusDataUrl_JCO", method = RequestMethod.POST)

	public ModelAndView View_ApproveUpadteCensusDataUrl_JCO(@ModelAttribute("ap_id") String updateid,

			@ModelAttribute("ap_army_no2") String ap_army_no2,

			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,

			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {

		

		Date modifiedDate = getParentModifiedDate_Update_census(Integer.parseInt(updateid));

		List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> ChangeOfgender = gender.ViewgenderDataCensus(Integer.parseInt(updateid),modifiedDate);

		List<TB_PSG_UPDATE_CENSUS_BIRTH_JCO> ChangeOfdob = birth.ViewBirthDataCensus(Integer.parseInt(updateid),modifiedDate);

		List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> ChangeOfAddressBirth = addBirth.ViewAddressBirthDataCensus(Integer.parseInt(updateid),modifiedDate);

		List<TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO> ChangeOfNationality = Natina.View_Nationality_DataCensus(Integer.parseInt(updateid),modifiedDate);

		List<TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO> ChangeOfMotherTongue = Moto.View_MotherTounge_DataCensus(Integer.parseInt(updateid),modifiedDate);

		List<TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO> ChangeOfBloodGroup = BG.View_BloodGroup_DataCensus(Integer.parseInt(updateid),modifiedDate);

		List<TB_PSG_UPDATE_CENSUS_HEIGHT_JCO> ChangeOfHeight = HE.View_Height_DataCensus(Integer.parseInt(updateid),modifiedDate);

		List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> ChangeOfAadhar = ADN.View_AAdhar_DataCensus(Integer.parseInt(updateid),modifiedDate);

		List<TB_PSG_UPDATE_CENSUS_PAN_NO_JCO> ChangeOfPanNo = PN.View_Pan_DataCensus(Integer.parseInt(updateid),modifiedDate);

		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> ClassEnroll = EC.View_Class_Enrol_DataCensus(Integer.parseInt(updateid),modifiedDate);

		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> ChangeOfRank = RN.View_Rank_DataCensus(Integer.parseInt(updateid),modifiedDate);

		List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> ChangeOfDtofEnrolment = DE.View_Date_Enrollment_DataCensus(Integer.parseInt(updateid),modifiedDate);

		List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> ChangeOfDtofAttestation = DA.View_Date_Attestation_DataCensus(Integer.parseInt(updateid),modifiedDate);

		List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> ChangeOfFamily = FD.View_Family_Details_DataCensus(Integer.parseInt(updateid),modifiedDate);

		

		List<TB_CENSUS_JCO_OR_SIBLINGS> ChangeOfSibling = SB.View_Sibling_Details_DataCensus(Integer.parseInt(updateid),modifiedDate);

		

		

		Mmap.put("msg", msg);

		Mmap.put("id", updateid);

		Mmap.put("ap_army_no2", ap_army_no2);

		Mmap.put("getCourseNameList", p_comm.getCourseNameList());

		Mmap.put("getGenderList", p_comm.getGenderList());

		Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());

		Mmap.put("getRegtList", p_comm.getRegtList(""));

		Mmap.put("getPersonalRemainder", p_comm.getPersonalRemainder());

		Mmap.put("getParentArmList", p_comm.getParentArmList());

		Mmap.put("getArmyType", p_comm.getArmyType());

		Mmap.put("getMedCountryName", p_comm.getMedCountryName("", session));

		Mmap.put("getMedStateName", p_comm.getMedStateName("", session));

		Mmap.put("getMedDistrictName", p_comm.getMedDistrictName("", session));

		Mmap.put("getNationalityList", p_comm.getNationalityList());

		Mmap.put("getMothertoungeList", p_comm.getMothertoungeList());

		Mmap.put("getBloodList", p_comm.getBloodList());

		Mmap.put("getHeight", p_comm.getHeight());

		Mmap.put("getClass_enrollList", jcom.getClass_enrollList());

		Mmap.put("getRankjcoList", jcom.getRankjcoList());

		Mmap.put("getExservicemenList", p_comm.getExservicemenList());

		Mmap.put("getProfession", p_comm.getProfession());

		Mmap.put("getFamily_siblings", p_comm.getFamily_siblings());

		Mmap.put("getRank_intakeList", jcom.getRank_intakeList());

		Mmap.put("getclass_domicileList", jcom.getclass_domicileList());

		Mmap.put("ChangeOfgender", ChangeOfgender);

		Mmap.put("ChangeOfdob", ChangeOfdob);

		Mmap.put("ChangeOfAddressBirth", ChangeOfAddressBirth);

		Mmap.put("ChangeOfNationality", ChangeOfNationality);

		Mmap.put("ChangeOfMotherTongue", ChangeOfMotherTongue);

		Mmap.put("ChangeOfBloodGroup", ChangeOfBloodGroup);

		Mmap.put("ChangeOfHeight", ChangeOfHeight);

		Mmap.put("ChangeOfAadhar", ChangeOfAadhar);

		Mmap.put("ChangeOfPanNo", ChangeOfPanNo);

		Mmap.put("ClassEnroll", ClassEnroll);

		Mmap.put("ChangeOfRank", ChangeOfRank);

		Mmap.put("ChangeOfDtofEnrolment", ChangeOfDtofEnrolment);

		Mmap.put("ChangeOfDtofAttestation", ChangeOfDtofAttestation);

		Mmap.put("ChangeOfFamily", ChangeOfFamily);

		Mmap.put("ChangeOfSibling", ChangeOfSibling);

		

		return new ModelAndView("JCO_View_App_Update_Census_Data_Tiles");

		

	}

	

	public Date getParentModifiedDate_Update_census(int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_CENSUS_JCO_OR_PARENT where id=:jco_id and status = '1'  ";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

		@SuppressWarnings("unchecked")

		List<TB_CENSUS_JCO_OR_PARENT> list = (List<TB_CENSUS_JCO_OR_PARENT>) query.list();

		tx.commit();

		sessionHQL.close();

		return list.get(0).getModified_date();

	}



}

