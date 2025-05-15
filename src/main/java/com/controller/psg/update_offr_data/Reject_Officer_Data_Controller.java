package com.controller.psg.update_offr_data;



import java.math.BigInteger;
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

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;

import com.dao.login.RoleBaseMenuDAO;

import com.dao.psg.Master.Psg_CommanDAO;

import com.models.psg.Transaction.TB_CENSUS_ADDRESS;

import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;

import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;

import com.models.psg.Transaction.TB_CENSUS_FOREIGN_COUNTRY;

import com.models.psg.Transaction.TB_CENSUS_IDENTITY_CARD;

import com.models.psg.Transaction.TB_CENSUS_LANGUAGE;

import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;

import com.models.psg.Transaction.TB_CENSUS_NOK;

import com.models.psg.Transaction.TB_CENSUS_QUALIFICATION;

import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;

import com.models.psg.Transaction.TB_PSG_CANTEEN_CARD_DETAIL1;

import com.models.psg.Transaction.TB_PSG_CENSUS_ALLERGICTOMEDICINE;

import com.models.psg.update_census_data.TB_CENSUS_ARMY_COURSE;

import com.models.psg.update_census_data.TB_CENSUS_AWARDSNMEDAL;

import com.models.psg.update_census_data.TB_CENSUS_BATT_PHY_CASUALITY;

import com.models.psg.update_census_data.TB_CENSUS_BPET;

import com.models.psg.update_census_data.TB_CENSUS_CDA_ACCOUNT_NO;

import com.models.psg.update_census_data.TB_CENSUS_CHILDREN;

import com.models.psg.update_census_data.TB_CENSUS_DISCIPLINE;

import com.models.psg.update_census_data.TB_CENSUS_FIRING_STANDARD;

import com.models.psg.update_census_data.TB_CENSUS_PROMOTIONAL_EXAM;

import com.models.psg.update_census_data.TB_CENSUS_SECONDMENT;

import com.models.psg.update_census_data.TB_CHANGE_NAME;

import com.models.psg.update_census_data.TB_CHANGE_OF_APPOINTMENT;

import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;

import com.models.psg.update_census_data.TB_CHANGE_RELIGION;

import com.models.psg.update_census_data.TB_DESERTER;

import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;

import com.models.psg.update_census_data.TB_NON_EFFECTIVE;

import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;

import com.models.psg.update_census_data.TB_PSG_EXTENSION_OF_COMISSION;

import com.persistance.util.HibernateUtil;







@Controller

@RequestMapping(value = {"admin","/","user"})

public class Reject_Officer_Data_Controller {



	@Autowired

	Psg_CommanDAO psg_com;

	

	String u_id = "";

	String event1 = "";

	

	Search_UpdateOfficerDataController search_UpOffr = new Search_UpdateOfficerDataController();

	Psg_CommonController mcommon = new Psg_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	

	@Autowired

	private RoleBaseMenuDAO roledao;

	


	

	/*--------------------- For Approval Update Officer ----------------------------------*/

	 

	 Change_Of_Rank_controller ChngRnk = new Change_Of_Rank_controller();

	 Change_NameController ChngName = new Change_NameController();

	 Non_Effective_Controller NonEffec = new Non_Effective_Controller();

	 Update_off_dataController Up = new Update_off_dataController();

	 Contact_Details_Controller CDA = new Contact_Details_Controller();

	 Religion_UpdateController Rel = new Religion_UpdateController();

	 Change_Of_Commision_Controller ChngComm = new Change_Of_Commision_Controller();

	 Extension_Of_Commision_Controller ExteComm = new Extension_Of_Commision_Controller();

     Inter_Arm_Tranfer_Controller IntArmTran = new Inter_Arm_Tranfer_Controller();

     AwardsNmedal_Controller AwrMed = new AwardsNmedal_Controller();

     Update_Language_Controller lan = new Update_Language_Controller();

	// Qualification_Controller QUA = new Qualification_Controller();

	 Secondment_Controller Sec = new Secondment_Controller();

	 Appointment_Controller Ap = new Appointment_Controller();

	 Foreign_ContryController ForCon = new Foreign_ContryController();

	 Marraige_Controller Mrg = new Marraige_Controller();

	 Fire_Std_Controller FirSta = new Fire_Std_Controller();

	 Nok_Controller NOKc = new Nok_Controller();

	 BPET_Controller BPET = new BPET_Controller();

	 Identity_Card_Controller IdeCard = new Identity_Card_Controller();

	 Change_AddressController Chngadd = new Change_AddressController();

	 Battle_and_Physical_Casuality_Controller Btel = new Battle_and_Physical_Casuality_Controller();

	 DisciplineController Discipline = new  DisciplineController();

	 Allergy_Controller Alle = new Allergy_Controller();

	 Census_madicalCatController c_mad=new Census_madicalCatController();

	 DeserterController deserter =new DeserterController();

	 Canteen_card_DetailsController csdc = new Canteen_card_DetailsController();

	 Update_Qualification_Controller UP_QUA = new Update_Qualification_Controller();

	 

	 @RequestMapping(value = "/Reject_UpadteOfficerDataUrl", method = RequestMethod.POST)

		public ModelAndView Reject_UpadteOfficerDataUrl(@ModelAttribute("id3") String updateid,

				@ModelAttribute("personnel_no3") String personnel_no2,

				@ModelAttribute("comm_id3") String comm_id,

				@ModelAttribute("event3") String event,

				@ModelAttribute("personnel_no7") String personnel_no7, 

				@ModelAttribute("status7") String status7, 

				@ModelAttribute("rank7") String rank7, 

				@ModelAttribute("comm_id7") String comm_id7, 

				@ModelAttribute("unit_name7") String unit_name7, 

				@ModelAttribute("unit_sus_no7") String unit_sus_no7,

			

				@RequestParam(value = "msg", required = false) String msg,

				Authentication authentication,HttpServletRequest request,ModelMap Mmap,HttpSession session)   {

		

//		    String roleSusNo = session.getAttribute("roleSusNo").toString();

		    String roleid = session.getAttribute("roleid").toString();

			Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", roleid);

			if (val == false) {

				return new ModelAndView("AccessTiles");

			}

			if (request.getHeader("Referer") == null) {

				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");

			}

			

			Mmap.put("personnel_no7", personnel_no7);

			Mmap.put("status7", status7);

			Mmap.put("rank7", rank7);

			Mmap.put("comm_id7", comm_id7);

			Mmap.put("unit_name7", unit_name7);

			Mmap.put("unit_sus_no7", unit_sus_no7);



			

			

		 

		    List<TB_CHANGE_OF_RANK> ChangeOfRank = ChngRnk.getChangeOfRankData2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CHANGE_NAME> ChangeOfName =  ChngName.getChangeOfNameData2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CHANGE_OF_APPOINTMENT> ChngAppointment= Ap.get_Appointment2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CENSUS_IDENTITY_CARD> IdentityCard= IdeCard.Ide_card_getData2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CHANGE_RELIGION> religion = Rel.religion_GetData2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CENSUS_FAMILY_MRG> Marital = Mrg.update_getfamily_marriageData2(Integer.parseInt(updateid),new BigInteger(comm_id),Integer.parseInt(event));

		    List<TB_CENSUS_SPOUSE_QUALIFICATION> Spouse_Quali = Mrg.getUpdatedSpouseQualificationData(new BigInteger(comm_id), 3);

		    List<TB_CENSUS_CHILDREN> ChildDetails = Up.getm_children_detailsData2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CENSUS_NOK> NOK = NOKc.nok_details_GetData2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CENSUS_ADDRESS> ChangeAdd = Chngadd.address_details_GetData2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CENSUS_CDA_ACCOUNT_NO> CDAaccount= CDA.cda_acnt_no_GetData2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CENSUS_LANGUAGE> Language = lan.update_census_getlanguagedetailsData2(Integer.parseInt(updateid),new BigInteger(comm_id)); 

		 

		    /////////////

		    List<TB_CENSUS_QUALIFICATION> Qualification = UP_QUA.getChangeOfQualification2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CENSUS_PROMOTIONAL_EXAM> Promotional_Exam = UP_QUA.getChangeOfPromotionExam2(Integer.parseInt(updateid),new BigInteger(comm_id)); 

		    //List<TB_CENSUS_ARMY_COURSE> Army_Course = UP_QUA.update_census_army_courseData1(Integer.parseInt(updateid),Integer.parseInt(comm_id));

		    List<TB_CENSUS_ARMY_COURSE> Army_Course = UP_QUA.getChangeOfArmyCourse2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CENSUS_BATT_PHY_CASUALITY> btel_cas =  Btel.getChangeBattleCasulity2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_NON_EFFECTIVE> NonEffective = NonEffec.getNonEffective2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CENSUS_MEDICAL_CATEGORY> MedDetails = c_mad.getChangeMedical2(Integer.parseInt(updateid),new BigInteger(comm_id));				   



////////////

		    

		    List<TB_CENSUS_BPET> BEPT =  BPET.getbpet_Data2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CENSUS_FIRING_STANDARD> FiringStan = FirSta.getfire_detailsData2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> ChangeOfAllergy = Alle.getChangeOfAllergy2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CENSUS_FOREIGN_COUNTRY> ForeignCountry = ForCon.getChangeOfForeignCountry2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_CENSUS_AWARDSNMEDAL> AwardsMedal= AwrMed.getChangeOfAward2(Integer.parseInt(updateid),new BigInteger(comm_id)); 

		    List<TB_CENSUS_DISCIPLINE> ChangeOfDiscipline = Discipline.getChangeOfDiscipline2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_INTER_ARM_TRANSFER> InterArmTrans = IntArmTran.getChangeOfInterArm2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfComm = ChngComm.getChangeOfCoc2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_PSG_EXTENSION_OF_COMISSION> ExtenComm= ExteComm.getChangeOfEoc2(Integer.parseInt(updateid),new BigInteger(comm_id)); 

		    List<TB_CENSUS_SECONDMENT> ChangeOfSecondment = Sec.getChangeOfSecondment2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_DESERTER> deserter1 = deserter.deserter_GetData2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    List<TB_PSG_CANTEEN_CARD_DETAIL1> csdde = csdc.getCSD_detailsData2(Integer.parseInt(updateid),new BigInteger(comm_id));

		    

		    

		  //  List<TB_NON_EFFECTIVE> NonEffective = NonEffec.getNon_effective1(Integer.parseInt(updateid),Integer.parseInt(comm_id));

		  //  List<TB_CENSUS_BATT_PHY_CASUALITY> btel_cas =  Btel.Battle_and_Physical_Casuality_GetData1(Integer.parseInt(updateid),Integer.parseInt(comm_id));

		    

		    

		       Mmap.put("msg", msg);

			   Mmap.put("census_id", updateid);

			   Mmap.put("personnel_no2", personnel_no2);

			   Mmap.put("comm_id", comm_id);

			   Mmap.put("event", event);

			   

			   u_id = updateid;

			   event1 = event;

			   

			   Mmap.put("ChangeOfRank", ChangeOfRank.size());

			   Mmap.put("ChangeOfName", ChangeOfName.size());

			   Mmap.put("ChngAppointment", ChngAppointment.size());

			   Mmap.put("IdentityCard",IdentityCard.size());

			   Mmap.put("religion", religion.size());

			   Mmap.put("Marital",Marital.size());

			   Mmap.put("Spouse_Quali", Spouse_Quali.size());

			   Mmap.put("ChildDetails", ChildDetails.size());

			   Mmap.put("NOK", NOK.size());

			   Mmap.put("ChangeAdd",ChangeAdd.size());

			   Mmap.put("CDAaccount", CDAaccount.size());

			   Mmap.put("Language", Language.size());

			   

			   Mmap.put("Qualification", Qualification.size());

			   Mmap.put("Promotional_Exam", Promotional_Exam.size());

			   Mmap.put("Army_Course", Army_Course.size());

			   Mmap.put("MedDetails",MedDetails.size());

			    

			    

			   Mmap.put("BEPT", BEPT.size());

			   Mmap.put("FiringStan", FiringStan.size());

			   Mmap.put("Allergy", ChangeOfAllergy.size());

			   Mmap.put("ForeignCountry", ForeignCountry.size());

			   Mmap.put("AwardsMedal", AwardsMedal.size());

			   Mmap.put("ChangeOfDiscipline", ChangeOfDiscipline.size());

			   Mmap.put("InterArmTransfer", InterArmTrans.size());

			   Mmap.put("ChangeOfComm", ChangeOfComm.size());

			   Mmap.put("ExtensionComm", ExtenComm.size());

			   Mmap.put("Secondment", ChangeOfSecondment.size());

			   

			   Mmap.put("NonEffective", NonEffective.size());

			   Mmap.put("btel_cas",btel_cas.size());

			   Mmap.put("deserter1",deserter1.size());

			   Mmap.put("csdde",csdde.size());//keval

			   

			 //CHILDERN DATA

				Mmap.put("getRelationList", mcommon.getRelationList());

				Mmap.put("getReligionList", mcommon.getReligionList());

				Mmap.put("getSeconded_To", mcommon.getSeconded_To());

				Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());

				Mmap.put("getNon_EffectiveList", mcommon.getNon_EffectiveList());

				Mmap.put("getLanguageList", mcommon.getLanguageList());

				Mmap.put("getMothertoungeList", mcommon.getMothertoungeList());

				Mmap.put("getLanguageSTDList", mcommon.getLanguageSTDList());

				Mmap.put("getLanguagePROOFList", mcommon.getLanguagePROOFList());

				Mmap.put("getMedCountryName", mcommon.getMedCountryName("", session));

				Mmap.put("getParentArmList", mcommon.getParentArmList());

				Mmap.put("getRegtList", mcommon.getRegtList(""));

				Mmap.put("getForeign_country", psg_com.getforiegnCountry());

				Mmap.put("getForiegnCountryList", mcommon.getForeign_country());

				Mmap.put("getSpecializationList", mcommon.getSpecializationList());

				Mmap.put("getTypeOfCommissionList", mcommon.getTypeOfCommissionList());

				Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());

				Mmap.put("getInstituteNameList", mcommon.getInstituteNameList());

				Mmap.put("getDivclass", mcommon.getDivclass());

				Mmap.put("getSubject", mcommon.getSubject());

				Mmap.put("getExamination", mcommon.getExamination());

				Mmap.put("getCommandDetailsList", m.getCommandDetailsList());

				Mmap.put("getMedalList", mcommon.getMedalList());

				Mmap.put("getTypeofAppointementList", mcommon.getTypeofAppointementList());

				Mmap.put("getMarital_eventList", mcommon.getMarital_eventList());

				Mmap.put("getMarital_statusList", mcommon.getMarital_statusList());

				// Mmap.put("getVillageList", mcommon.getVillageList());

				Mmap.put("getNationalityList", mcommon.getNationalityList());

				Mmap.put("getFiring_event_qe", mcommon.getFiring_event_qe());

				Mmap.put("getBPET_event_qe", mcommon.getBPET_event_qe());

				Mmap.put("getFiring_grade", mcommon.getFiring_grade());

				Mmap.put("getBPET_result", mcommon.getBPET_result());

				Mmap.put("getHair_ColourList", mcommon.getHair_ColourList());

				Mmap.put("getEye_ColourList", mcommon.getEye_ColourList());

				//Mmap.put("getMedStateName", mcommon.getMedStateName("", session));

				//Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));

				//Mmap.put("getMedCityName", mcommon.getMedCityName("", session));

				//Mmap.put("getCountryList", mcommon.getCountryList());

				//Mmap.put("getMedStateName", mcommon.getMedStateName("", session));

				Mmap.put("getFamily_siblings", mcommon.getFamily_siblings());

				Mmap.put("getArmyCourseInstitute", mcommon.getArmyCourseInstitute("1"));

				Mmap.put("getIndianLanguageList", mcommon.getIndianLanguageList());

				Mmap.put("getForiegnLangugeList", mcommon.getForiegnLangugeList());

				Mmap.put("getOFFR_Non_EffectiveList", mcommon.getOFFR_Non_EffectiveList(""));

				Mmap.put("getPSG_CommandList", psg_com.getPSG_CommandList());

				Mmap.put("getPSG_CorpsList", psg_com.getPSG_CorpsList());

				Mmap.put("getPSG_DivList", psg_com.getPSG_DivList());

				Mmap.put("getPSG_BdeList", psg_com.getPSG_BdeList());

				Mmap.put("getPersonalType", mcommon.getPersonalType());

				Mmap.put("getPersonalRemainder", mcommon.getPersonalRemainder());

				Mmap.put("getOFFTypeofRankList", mcommon.getOFFTypeofRankList());

				Mmap.put("getChild_RelationshipList", mcommon.getChild_RelationshipList());

				Mmap.put("getChild_TypeList", mcommon.getChild_TypeList());

				Mmap.put("getShapeStatusList", mcommon.getShapeStatusList());

				Mmap.put("getcCopeList", mcommon.getCopeValueList("C"));

				Mmap.put("getoCopeList", mcommon.getCopeValueList("O"));

				Mmap.put("getpCopeList", mcommon.getCopeValueList("P"));

				Mmap.put("geteCopeList", mcommon.getCopeValueList("E"));

				Mmap.put("getesubCopeList", mcommon.getCopeValueList("E Sub Value"));

				Mmap.put("getOprationList", mcommon.getOprationList());

				Mmap.put("getDivclass", mcommon.getDivclass());

				Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());

				Mmap.put("getExaminationTypeList", mcommon.getExaminationTypeList());

			//	Mmap.put("getStreamList", mcommon.getStreamList());

				Mmap.put("getCourseName", mcommon.getCourseName());

				Mmap.put("getFdService", mcommon.getFdService());

				Mmap.put("getCourseTypeList", mcommon.getCourseTypeList());

				Mmap.put("getExamList", mcommon.getExamList());

				Mmap.put("getdisciplinetypeentry", mcommon.getdisciplinetypeentry());

				Mmap.put("getDclredRcvrdList", mcommon.getDclredRcvrdList());

				 Mmap.put("getCauseOfDeserter", mcommon.getCauseOfDeserter());

				 Mmap.put("getCSDCategoryList", mcommon.getCSDCategoryList());//keval

				 Mmap.put("getExservicemenList", mcommon.getExservicemenList());

				 Mmap.put("getARM_TYPE", mcommon.getARM_TYPE());

				 Mmap.put("getMissingList", mcommon.getMissingList());



				 Mmap.put("getCausesOfCasuality", mcommon.getCausesOfCasuality());

				

				 //-----smit--------//

				 Mmap.put("getDisc_Trialed", mcommon.getDisc_Trialed());

				 Mmap.put("getArmyAct_Sec", mcommon.getArmyAct_Sec());

				 Mmap.put("getSub_Clause", mcommon.getSub_Clause());

				 

				 

				 Mmap.put("app_status", 3);

				//END

		return new ModelAndView("Reject_Officer_DataTiles");

	 }

	 

	 

	 @RequestMapping(value = "/Submit_Reject_UpadteOfficerDataUrl",method = RequestMethod.POST)

		public @ResponseBody String Submit_Reject_UpadteOfficerDataUrl(String comm_id,

			

				@RequestParam(value = "msg", required = false) String msg,

				Authentication authentication,HttpServletRequest request,ModelMap Mmap,HttpSession session)   {

		

			if (request.getHeader("Referer") == null) {

				msg = "";
				

			}

			Session sessionhql = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionhql.beginTransaction();

			String username = session.getAttribute("username").toString();

		
		 

		    List<TB_CHANGE_OF_RANK> ChangeOfRank = ChngRnk.getChangeOfRankData2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CHANGE_NAME> ChangeOfName =  ChngName.getChangeOfNameData2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CHANGE_OF_APPOINTMENT> ChngAppointment= Ap.get_Appointment2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CENSUS_IDENTITY_CARD> IdentityCard= IdeCard.Ide_card_getData2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CHANGE_RELIGION> religion = Rel.religion_GetData2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CENSUS_FAMILY_MRG> Marital = Mrg.update_getfamily_marriageData2(Integer.parseInt(u_id),new BigInteger(comm_id),Integer.parseInt(request.getParameter("event")));

		    List<TB_CENSUS_SPOUSE_QUALIFICATION> Spouse_Quali = Mrg.getUpdatedSpouseQualificationData(new BigInteger(comm_id), 3);

		    List<TB_CENSUS_CHILDREN> ChildDetails = Up.getm_children_detailsData2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CENSUS_NOK> NOK = NOKc.nok_details_GetData2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CENSUS_ADDRESS> ChangeAdd = Chngadd.address_details_GetData2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CENSUS_CDA_ACCOUNT_NO> CDAaccount= CDA.cda_acnt_no_GetData2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CENSUS_LANGUAGE> Language = lan.update_census_getlanguagedetailsData2(Integer.parseInt(u_id),new BigInteger(comm_id)); 

		 

		    List<TB_CENSUS_QUALIFICATION> Qualification = UP_QUA.getChangeOfQualification2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CENSUS_PROMOTIONAL_EXAM> Promotional_Exam = UP_QUA.getChangeOfPromotionExam2(Integer.parseInt(u_id),new BigInteger(comm_id)); 

		    List<TB_CENSUS_ARMY_COURSE> Army_Course = UP_QUA.getChangeOfArmyCourse2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CENSUS_BATT_PHY_CASUALITY> btel_cas =  Btel.getChangeBattleCasulity2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_NON_EFFECTIVE> NonEffective = NonEffec.getNonEffective2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CENSUS_MEDICAL_CATEGORY> MedDetails = c_mad.getChangeMedical2(Integer.parseInt(u_id),new BigInteger(comm_id));				   



		    

		    List<TB_CENSUS_BPET> BEPT =  BPET.getbpet_Data2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CENSUS_FIRING_STANDARD> FiringStan = FirSta.getfire_detailsData2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> ChangeOfAllergy = Alle.getChangeOfAllergy2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CENSUS_FOREIGN_COUNTRY> ForeignCountry = ForCon.getChangeOfForeignCountry2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_CENSUS_AWARDSNMEDAL> AwardsMedal= AwrMed.getChangeOfAward2(Integer.parseInt(u_id),new BigInteger(comm_id)); 

		    List<TB_CENSUS_DISCIPLINE> ChangeOfDiscipline = Discipline.getChangeOfDiscipline2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_INTER_ARM_TRANSFER> InterArmTrans = IntArmTran.getChangeOfInterArm2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfComm = ChngComm.getChangeOfCoc2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_PSG_EXTENSION_OF_COMISSION> ExtenComm= ExteComm.getChangeOfEoc2(Integer.parseInt(u_id),new BigInteger(comm_id)); 

		    List<TB_CENSUS_SECONDMENT> ChangeOfSecondment = Sec.getChangeOfSecondment2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_DESERTER> deserter1 = deserter.deserter_GetData2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    List<TB_PSG_CANTEEN_CARD_DETAIL1> csdde = csdc.getCSD_detailsData2(Integer.parseInt(u_id),new BigInteger(comm_id));

		    

		    

		    

		

		   try {

		   if(ChangeOfRank.size() > 0) {

			   

			   String hql = "update TB_CHANGE_OF_RANK set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

			

		   }

		   if(ChangeOfName.size() > 0) {

			   

			   String hql = "update TB_CHANGE_NAME set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

			

		   }

		   if(ChngAppointment.size() > 0) {

			   

			   String hql = "update TB_CHANGE_OF_APPOINTMENT set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

			

		   }

		   if(IdentityCard.size() > 0) {

			   

			   String hql = "update TB_CENSUS_IDENTITY_CARD set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

				

		   }

		   if(religion.size() > 0) {

			   

			   String hql = "update TB_CHANGE_RELIGION set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

			

		   }

		   if(Marital.size() > 0) {

			   

			   String hql = "update TB_CENSUS_FAMILY_MRG set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

				

		   }

		   

		  if(Spouse_Quali.size() > 0) {

			   

			   String hql = "update TB_CENSUS_SPOUSE_QUALIFICATION set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

			

		   }

		   

		   

		   

		   if(ChildDetails.size() > 0) {

			   

			   String hql = "update TB_CENSUS_CHILDREN set status=:status,modified_by=:modified_by,modified_date=:modified_date  "

						+ " where  comm_id=:id and status=3";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

				

		   }

		   if(NOK.size() > 0) {

			   

			   String hql = "update TB_CENSUS_NOK set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

				

		   }

		   if(ChangeAdd.size() > 0) {

			   

			   String hql = "update TB_CENSUS_ADDRESS set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

				
		   }

		   if(CDAaccount.size() > 0) {

			   

			   String hql = "update TB_CENSUS_CDA_ACCOUNT_NO set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

			

		   }

		   if(Language.size() > 0) {

			   

			   String hql = "update TB_CENSUS_LANGUAGE set status=:status,modify_by=:modified_by,modify_on=:modified_date  "

						+ " where  comm_id=:id and status=3";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

			

		   }

		   if(Qualification.size() > 0) {

			   

			   String hql = "update TB_CENSUS_QUALIFICATION set status=:status,modify_by=:modified_by,modify_on=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

				

		   }

		   

		   if(Promotional_Exam.size() > 0) {

			   

			   String hql = "update TB_CENSUS_PROMOTIONAL_EXAM set status=:status,modify_by=:modified_by,modify_on=:modified_date  "

						+ " where  comm_id=:id and status=3";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

			

		   } 

		   if(Army_Course.size() > 0) {

			   

			   String hql = "update TB_CENSUS_ARMY_COURSE set status=:status,modify_by=:modified_by,modify_on=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

				

		   }

		   if(btel_cas.size() > 0) {

			   

			   String hql = "update TB_CENSUS_BATT_PHY_CASUALITY set status=:status,modify_by=:modified_by,modify_on=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

			

		   } 

		   if(NonEffective.size() > 0) {

			   

			   String hql = "update TB_NON_EFFECTIVE set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

				

		   }

		   if(MedDetails.size() > 0) {

			   

			   String hql = "update TB_CENSUS_MEDICAL_CATEGORY set status=:status,modify_by=:modified_by,modify_on=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

				

		   }

		   if(BEPT.size() > 0) {

			   

			   String hql = "update TB_CENSUS_BPET set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

				

		   }

		   if(FiringStan.size() > 0) {

			   

			   String hql = "update TB_CENSUS_FIRING_STANDARD set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

				

		   }

		   	if(ChangeOfAllergy.size() > 0) {

			   

			   String hql = "update TB_PSG_CENSUS_ALLERGICTOMEDICINE set status=:status,modified_by=:modified_by,modified_date=:modified_date "

						+ " where  comm_id=:id and status='3'";

				Query query = sessionhql.createQuery(hql)

						.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 0);



				msg = query.executeUpdate() > 0 ? "update" : "0";

				

		   }

		   	if(ForeignCountry.size() > 0) {

				   

				   String hql = "update TB_CENSUS_FOREIGN_COUNTRY set status=:status,modified_by=:modified_by,modified_date=:modified_date "

							+ " where  comm_id=:id and status='3'";

					Query query = sessionhql.createQuery(hql)

							.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

							.setTimestamp("modified_date", new Date()).setInteger("status", 0);



					msg = query.executeUpdate() > 0 ? "update" : "0";

					

			   }

		   	if(AwardsMedal.size() > 0) {

				   

				   String hql = "update TB_CENSUS_AWARDSNMEDAL set status=:status,modify_by=:modified_by,modify_date=:modified_date "

							+ " where  comm_id=:id and status='3'";

					Query query = sessionhql.createQuery(hql)

							.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

							.setTimestamp("modified_date", new Date()).setInteger("status", 0);



					msg = query.executeUpdate() > 0 ? "update" : "0";

				

			   }

		   	if(ChangeOfDiscipline.size() > 0) {

				   

				   String hql = "update TB_CENSUS_DISCIPLINE set status=:status,modified_by=:modified_by,modified_date=:modified_date "

							+ " where  comm_id=:id and status='3'";

					Query query = sessionhql.createQuery(hql)

							.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

							.setTimestamp("modified_date", new Date()).setInteger("status", 0);



					msg = query.executeUpdate() > 0 ? "update" : "0";

				

			   }

		   	if(InterArmTrans.size() > 0) {

				   

				   String hql = "update TB_INTER_ARM_TRANSFER set status=:status,modified_by=:modified_by,modified_date=:modified_date "

							+ " where  comm_id=:id and status='3'";

					Query query = sessionhql.createQuery(hql)

							.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

							.setTimestamp("modified_date", new Date()).setInteger("status", 0);



					msg = query.executeUpdate() > 0 ? "update" : "0";

					

			   }

		   	if(ChangeOfComm.size() > 0) {

				   

				   String hql = "update TB_PSG_CHANGE_OF_COMISSION set status=:status,modified_by=:modified_by,modified_date=:modified_date "

							+ " where  comm_id=:id and status='3'";

					Query query = sessionhql.createQuery(hql)

							.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

							.setTimestamp("modified_date", new Date()).setInteger("status", 0);



					msg = query.executeUpdate() > 0 ? "update" : "0";

					

			   }

		   	if(ExtenComm.size() > 0) {

				   

				   String hql = "update TB_PSG_EXTENSION_OF_COMISSION set status=:status,modified_by=:modified_by,modified_date=:modified_date "

							+ " where  comm_id=:id and status='3'";

					Query query = sessionhql.createQuery(hql)

							.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

							.setTimestamp("modified_date", new Date()).setInteger("status", 0);



					msg = query.executeUpdate() > 0 ? "update" : "0";

					

			   }

		   	if(ChangeOfSecondment.size() > 0) {

				   

				   String hql = "update TB_CENSUS_SECONDMENT set status=:status,modified_by=:modified_by,modified_date=:modified_date "

							+ " where  comm_id=:id and status='3'";

					Query query = sessionhql.createQuery(hql)

							.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

							.setTimestamp("modified_date", new Date()).setInteger("status", 0);



					msg = query.executeUpdate() > 0 ? "update" : "0";

					

			   }

		   	

		   	if(deserter1.size() > 0) {

				   

				   String hql = "update TB_DESERTER set status=:status,approved_by=:modified_by,approved_date=:modified_date "

							+ " where  comm_id=:id and status='3'";

					Query query = sessionhql.createQuery(hql)

							.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

							.setTimestamp("modified_date", new Date()).setInteger("status", 0);



					msg = query.executeUpdate() > 0 ? "update" : "0";

				

			   }

		   	if(csdde.size() > 0) {

				   

				   String hql = "update TB_PSG_CANTEEN_CARD_DETAIL1 set status=:status,modified_by=:modified_by,modified_date=:modified_date "

							+ " where  comm_id=:id and status='3'";

					Query query = sessionhql.createQuery(hql)

							.setInteger("id", Integer.parseInt(comm_id)).setString("modified_by", username)

							.setTimestamp("modified_date", new Date()).setInteger("status", 0);



					msg = query.executeUpdate() > 0 ? "update" : "0";

					

			   }

		    tx.commit();

		   	if(search_UpOffr.check_Update_Officer_DataStatusForPending(Integer.parseInt(u_id),new BigInteger(comm_id),Integer.parseInt(request.getParameter("event")))) {

		   	 	msg = mcommon.update_offr_status(Integer.parseInt(u_id),username);
		   	 	if (msg.equals("Data Not Updated")) {
		   	 	tx.rollback();
				}

		   	}else {

		   		TB_CENSUS_DETAIL_Parent P=(TB_CENSUS_DETAIL_Parent)sessionhql.get(TB_CENSUS_DETAIL_Parent.class,Integer.parseInt(u_id) );
		   	/// bisag 241122 V2 (change comm_id int to big int)
			
		   		msg=change_Update_Officer_Data(new BigInteger(comm_id),1);
		   		if (msg.equals("Data Not Updated")) {
			   	 	tx.rollback();
					}
		   	}
		   	
		   } catch (Exception e) {
          	 msg = "Data Not Updated";

				tx.rollback();
           }

		  

		  

		    

		return msg;

	 }

	/// bisag 241122 V2 (change comm_id int to big int)
		
	 public String change_Update_Officer_Data(BigInteger comm_id, int update_offr_status) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionHQL.beginTransaction();

			String msg = "";

			try {
				String hql = "update TB_CENSUS_DETAIL_Parent set "

						+ "update_ofr_status=:update_ofr_status where comm_id=:comm_id and status = '1'" ;

				Query query = sessionHQL.createQuery(hql).setInteger("update_ofr_status", update_offr_status)
						.setBigInteger("comm_id", comm_id);

				msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

				tx.commit();

			}

			catch (Exception e) {

				msg = "Data Not Updated";

				tx.rollback();

			}

			finally {

				sessionHQL.close();

			}

			return msg;

		}
	

}

