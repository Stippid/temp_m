package com.controller.psg.Jco_Update_JcoData;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.update_offr_data.Search_UpdateOfficerDataController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.Psg_CommanDAO;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Update_JcoData.TB_ALLERGIC_TO_MEDICINE_JCO;
import com.models.psg.Jco_Update_JcoData.TB_ATTACHMENT_DETAILS_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CANTEEN_CARD_DETAILS1_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_ADDRESS_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_ARMY_COURSE_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_AWARDSNMEDAL_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_BPET_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_CHILDREN_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_DISCIPLINE_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FAMILY_MARRIED_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FIRING_STANDARD_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FOREIGN_COUNTRY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_LANGUAGE_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_PROMO_EXAM_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_QUALIFICATION_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_SPOUSE_QUALIFICATION_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_CLASS_PAY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_DATE_OF_SENIORITY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_PAY_GROUP_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_TRADE_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_NAME_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_OF_APPOINTMENT_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_OF_RANK_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_RELIGION_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_TA_STATUS_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_TYPE_OF_POSTING_JCO;
import com.models.psg.Jco_Update_JcoData.TB_DESERTER_JCO;
import com.models.psg.Jco_Update_JcoData.TB_IDENTITY_CARD_JCO;
import com.models.psg.Jco_Update_JcoData.TB_INTER_ARM_TRANSFER_JCO;
import com.models.psg.Jco_Update_JcoData.TB_MEDICAL_CATEGORY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_NOK_JCO;
import com.models.psg.Jco_Update_JcoData.TB_NON_EFFECTIVE_JCO;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;
import com.persistance.util.HibernateUtil;



@Controller
@RequestMapping(value = {"admin","/","user"})
public class Reject_JCO_Data_Controller {

	psg_jco_CommonController pjc = new psg_jco_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	@Autowired
	Psg_CommanDAO psg_com;
	
	String u_id = "";
	String event1 = "";
	
	Psg_CommonController mcommon = new Psg_CommonController();

	Search_UpdateJCODataController search_UpJCO = new Search_UpdateJCODataController();
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	/*--------------------- For Approval Update Officer ----------------------------------*/
	
	Change_postingJCOController  Chngpost = new Change_postingJCOController();
	 
	Change_Of_Rank_JCOcontroller ChngRnk = new Change_Of_Rank_JCOcontroller();

	Change_Name_JCOController ChngName = new Change_Name_JCOController();
	
	TA_Status_JCOController ChngTA = new TA_Status_JCOController();

	Non_Effective_JCOController NonEffec = new Non_Effective_JCOController();

	Update_JcoOr_DataController Up = new Update_JcoOr_DataController();

	Contact_Details_Jco_Controller CDA = new Contact_Details_Jco_Controller();

	Religion_Update_jcoOr_Controller Rel = new Religion_Update_jcoOr_Controller();

	Inter_Arm_Tranfer_JCOController IntArmTran = new Inter_Arm_Tranfer_JCOController();

	AwardsNmedal_Jco_Controller AwrMed = new AwardsNmedal_Jco_Controller();

	Update_Language_Jco_Controller lan = new Update_Language_Jco_Controller();

	Qualification_JCOController QUA = new Qualification_JCOController();
	Promo_Exam_Jco_Controller PEC = new Promo_Exam_Jco_Controller();
	Army_courseJcoController ACC = new Army_courseJcoController();
	Appointment_JCOController Ap = new Appointment_JCOController();

	Foreign_Contry_Jco_Controller ForCon = new Foreign_Contry_Jco_Controller();

	Marraige_JcoController Mrg = new Marraige_JcoController();

	Fire_Std_Jco_Controller FirSta = new Fire_Std_Jco_Controller();

	Nok_Jco_Controller NOKc = new Nok_Jco_Controller();

	BPET_JCOController BPET = new BPET_JCOController();

	Identity_Card_JCOController IdeCard = new Identity_Card_JCOController();

	Change_AddressJcoController Chngadd = new Change_AddressJcoController();

	Battle_and_Physical_Casuality_Jco_Controller Btel = new Battle_and_Physical_Casuality_Jco_Controller();

	Discipline_Jco_Controller Dis = new Discipline_Jco_Controller();

	Allergy_Jco_Controller aler = new Allergy_Jco_Controller();

	Census_madicalCat_Jco_Controller c_mad = new Census_madicalCat_Jco_Controller();

	Deserter_Jco_Controller deserter = new Deserter_Jco_Controller();

	Canteen_card_Details_Jco_Controller CSD = new Canteen_card_Details_Jco_Controller();
	
    Change_In_Trade_JCOController trad = new Change_In_Trade_JCOController();
	
	Change_In_Class_Pay_JCOController pay = new Change_In_Class_Pay_JCOController();
	
	Change_In_Pay_Group_JCOController gp = new Change_In_Pay_Group_JCOController();
	
	Change_In_Seniority_JCOController se = new Change_In_Seniority_JCOController();
	
	Attachment_Details_JCOController att = new Attachment_Details_JCOController();
	 
	 @RequestMapping(value = "/Reject_UpadteJCODataUrl" , method = RequestMethod.POST)
		public ModelAndView Reject_UpadteJCODataUrl(
				@ModelAttribute("personnel_no3") String personnel_no2,
				@ModelAttribute("jco_id3") String jco_id,
				@ModelAttribute("event3") String event,
				

				@ModelAttribute("personnel_no7") String personnel_no7, 
				@ModelAttribute("status7") String status7, 
				@ModelAttribute("rank7") String rank7, 
				@ModelAttribute("jco_id7") String jco_id7, 
				@ModelAttribute("unit_name7") String unit_name7, 
				@ModelAttribute("unit_sus_no7") String unit_sus_no7,
			
				@RequestParam(value = "msg", required = false) String msg,
				Authentication authentication,HttpServletRequest request,ModelMap Mmap,HttpSession session)   {
		
//		    String roleSusNo = session.getAttribute("roleSusNo").toString();
		    String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("update_jco_data_url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			
			Mmap.put("personnel_no7", personnel_no7);
			Mmap.put("status7", status7);
			Mmap.put("rank7", rank7);
			Mmap.put("jco_id7", jco_id7);
			Mmap.put("unit_name7", unit_name7);
			Mmap.put("unit_sus_no7", unit_sus_no7);

			
			
		 
		    List<TB_CHANGE_OF_RANK_JCO> ChangeOfRank = ChngRnk.getChangeOfRankDataJCO2(Integer.parseInt(jco_id));
		    List<TB_CHANGE_NAME_JCO> ChangeOfName =  ChngName.getChangeOfNameJCOData2(Integer.parseInt(jco_id));
		    List<TB_CHANGE_TA_STATUS_JCO> ChangeOfTA =  ChngTA.ta_status_jco_GetData2(Integer.parseInt(jco_id));
		    List<TB_CHANGE_OF_APPOINTMENT_JCO> ChngAppointment= Ap.get_AppointmentJCO2(Integer.parseInt(jco_id));
		    List<TB_IDENTITY_CARD_JCO> IdentityCard= IdeCard.Ide_card_getDataJCO2(Integer.parseInt(jco_id));
		    List<TB_CHANGE_RELIGION_JCO> religion = Rel.religion_GetData2(Integer.parseInt(jco_id));
		    List<TB_CENSUS_FAMILY_MARRIED_JCO> Marital = Mrg.update_getfamily_marriageData2(Integer.parseInt(jco_id),Integer.parseInt(event));
		    List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> Spouse_Quali = Mrg.getSpouseQualificationJCOData(Integer.parseInt(jco_id), 3);
		    List<TB_CENSUS_CHILDREN_JCO> ChildDetails = Up.getm_children_detailsData2(Integer.parseInt(jco_id));
		    List<TB_NOK_JCO> NOK = NOKc.nok_details_GetData2(Integer.parseInt(jco_id));
		    List<TB_CENSUS_ADDRESS_JCO> ChangeAdd = Chngadd.address_details_GetData2(Integer.parseInt(jco_id));
		    List<TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO> CDAaccount= CDA.cda_acnt_no_GetData2(Integer.parseInt(jco_id));
		    List<TB_CENSUS_LANGUAGE_JCO> Language = lan.update_census_getlanguagedetailsData2(Integer.parseInt(jco_id)); 
		 
		    /////////////
		    List<TB_CENSUS_QUALIFICATION_JCO> Qualification = QUA.getChangeOfQualification2(Integer.parseInt(jco_id));
		    List<TB_CENSUS_PROMO_EXAM_JCO> Promotional_Exam = PEC.getChangeOfPromotionExam2(Integer.parseInt(jco_id)); 
		 
		    List<TB_CENSUS_ARMY_COURSE_JCO> Army_Course = ACC.getChangeOfArmyCourse2(Integer.parseInt(jco_id));
		    List<TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO> btel_cas =  Btel.getChangeBattleCasulity2(Integer.parseInt(jco_id));
		    List<TB_NON_EFFECTIVE_JCO> NonEffective = NonEffec.getNonEffective2(Integer.parseInt(jco_id));
		    List<TB_MEDICAL_CATEGORY_JCO> MedDetails = c_mad.getChangeMedical2(Integer.parseInt(jco_id));				   

////////////
		    
		    List<TB_CENSUS_BPET_JCO> BEPT =  BPET.getbpet_Data2(Integer.parseInt(jco_id));
		    List<TB_CENSUS_FIRING_STANDARD_JCO> FiringStan = FirSta.getfire_detailsData2(Integer.parseInt(jco_id));
		    List<TB_ALLERGIC_TO_MEDICINE_JCO> ChangeOfAllergy = aler.getChangeOfAllergy2(Integer.parseInt(jco_id));
		    List<TB_CENSUS_FOREIGN_COUNTRY_JCO> ForeignCountry = ForCon.getChangeOfForeignCountry2(Integer.parseInt(jco_id));
		    List<TB_CENSUS_AWARDSNMEDAL_JCO> AwardsMedal= AwrMed.getChangeOfAward2(Integer.parseInt(jco_id)); 
		    List<TB_CENSUS_DISCIPLINE_JCO> ChangeOfDiscipline = Dis.getChangeOfDiscipline2(Integer.parseInt(jco_id));
		    List<TB_INTER_ARM_TRANSFER_JCO> InterArmTrans = IntArmTran.getChangeOfInterArm2(Integer.parseInt(jco_id));
		     List<TB_DESERTER_JCO> deserter1 = deserter.deserter_GetData2(Integer.parseInt(jco_id));
		    List<TB_CANTEEN_CARD_DETAILS1_JCO> csdde = CSD.getCSD_detailsData2(Integer.parseInt(jco_id));
		    
		    List<TB_CHANGE_IN_TRADE_JCO> TradeDetails = trad.Trade_getDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_CHANGE_IN_CLASS_PAY_JCO> PayDetails = pay.class_getDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_CHANGE_IN_PAY_GROUP_JCO> GroupDetails = gp.group_getDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> SeniorityDetails = se.seniority_getDataJCO2(Integer.parseInt(jco_id));
		    
			List<TB_ATTACHMENT_DETAILS_JCO> AttachmentDetails = att.attachment_getDataJCO2(Integer.parseInt(jco_id));
			List<TB_CHANGE_TYPE_OF_POSTING_JCO> changeofposting = Chngpost.getChangeOfpostJCOData2(Integer.parseInt(jco_id));
		    
		  //  List<TB_NON_EFFECTIVE> NonEffective = NonEffec.getNon_effective1(Integer.parseInt(updateid),Integer.parseInt(jco_id));
		  //  List<TB_CENSUS_BATT_PHY_CASUALITY> btel_cas =  Btel.Battle_and_Physical_Casuality_GetData1(Integer.parseInt(updateid),Integer.parseInt(jco_id));
		    
		    
		       Mmap.put("msg", msg);			
			   Mmap.put("personnel_no2", personnel_no2);
			   Mmap.put("jco_id", jco_id);
			   Mmap.put("event", event);
			   
			 
			   event1 = event;
			 
			   Mmap.put("changeofposting", changeofposting.size());
			   Mmap.put("ChangeOfRank", ChangeOfRank.size());
			   Mmap.put("ChangeOfName", ChangeOfName.size());
			   Mmap.put("ChangeOfTA", ChangeOfTA.size());
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
			 
			   
			   Mmap.put("NonEffective", NonEffective.size());
			   Mmap.put("btel_cas",btel_cas.size());
			   Mmap.put("deserter1",deserter1.size());
			   Mmap.put("csdde",csdde.size());//keval
			   Mmap.put("TradeDetails", TradeDetails.size());       
				Mmap.put("PayDetails", PayDetails.size()); 
				Mmap.put("GroupDetails", GroupDetails.size());        
				Mmap.put("SeniorityDetails", SeniorityDetails.size());
				Mmap.put("AttachmentDetails", AttachmentDetails.size()); 
			   
			 //CHILDERN DATA
			   Mmap.put("getArmyType", mcommon.getArmyType());
				Mmap.put("getRelationList", mcommon.getRelationList_JCO());
				Mmap.put("getReligionList", mcommon.getReligionList());
				Mmap.put("getSeconded_To", mcommon.getSeconded_To());
				Mmap.put("getTypeofRankList", pjc.getRankjcoList());
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
				Mmap.put("getTypeofAppointementJCOList", pjc.getTypeofAppointementListJCO());
				Mmap.put("getMarital_eventList", mcommon.getMarital_eventList());
				Mmap.put("getMarital_statusList", mcommon.getMarital_statusList());
				
				Mmap.put("getNationalityList", mcommon.getNationalityList());
				Mmap.put("getFiring_event_qe", mcommon.getFiring_event_qe());
				Mmap.put("getBPET_event_qe", mcommon.getBPET_event_qe());
				Mmap.put("getFiring_grade", mcommon.getFiring_grade());
				Mmap.put("getBPET_result", mcommon.getBPET_result());
				Mmap.put("getHair_ColourList", mcommon.getHair_ColourList());
				Mmap.put("getEye_ColourList", mcommon.getEye_ColourList());
				
				Mmap.put("getFamily_siblings", mcommon.getFamily_siblings());
				Mmap.put("getArmyCourseInstitute", mcommon.getArmyCourseInstitute("2"));
				Mmap.put("getIndianLanguageList", mcommon.getIndianLanguageList());
				Mmap.put("getForiegnLangugeList", mcommon.getForiegnLangugeList());
				Mmap.put("getJcoOr_Non_EffectiveList", pjc.getJcoOr_Non_EffectiveList(""));
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
		
				Mmap.put("getCourseName", mcommon.getCourseName());
				Mmap.put("getFdService", mcommon.getFdService());
				Mmap.put("getCourseTypeList", mcommon.getCourseTypeList());
				Mmap.put("getExamList", pjc.getExamList_jco());
				Mmap.put("getdisciplinetypeentry", mcommon.getdisciplinetypeentry());
				Mmap.put("getDclredRcvrdList", mcommon.getDclredRcvrdList());
				 Mmap.put("getCauseOfDeserter", mcommon.getCauseOfDeserter());
				 Mmap.put("getCSDCategoryList", mcommon.getCSDCategoryList());//keval
				 Mmap.put("getExservicemenList", mcommon.getExservicemenList());
				 Mmap.put("getARM_TYPE", mcommon.getARM_TYPE());
				 Mmap.put("getMissingList", mcommon.getMissingList());
				 Mmap.put("getstatusList", mcommon.getstatusList());
				 Mmap.put("getCausesOfCasuality", mcommon.getCausesOfCasuality());
				
				 //-----smit--------//
				 Mmap.put("getDisc_Trialed", mcommon.getDisc_Trialed());
				 Mmap.put("getArmyAct_Sec", mcommon.getArmyAct_Sec());
				 Mmap.put("getSub_Clause", mcommon.getSub_Clause());
				
				 Mmap.put("gettype_of_postingList", pjc.gettype_of_postingList());
				 Mmap.put("getTradeList", pjc.getTradeList());
					Mmap.put("getClassPayList", pjc.getClassPayList());         
					Mmap.put("getPayGroupList", pjc.getPayGroupList());
				 
				 Mmap.put("app_status", 3);
				//END
		return new ModelAndView("Reject_JCO_DataTiles");
	 }
	 
	 
	 @RequestMapping(value = "/Submit_Reject_UpadteJCODataUrl" , method = RequestMethod.POST)
		public @ResponseBody String Submit_Reject_UpadteJCODataUrl(String jco_id,
			
				@RequestParam(value = "msg", required = false) String msg,
				Authentication authentication,HttpServletRequest request,ModelMap Mmap,HttpSession session) throws NumberFormatException, ParseException   {
		
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			String username = session.getAttribute("username").toString();
		
			try {
			   List<TB_CHANGE_OF_RANK_JCO> ChangeOfRank = ChngRnk.getChangeOfRankDataJCO2(Integer.parseInt(jco_id));
			    List<TB_CHANGE_NAME_JCO> ChangeOfName =  ChngName.getChangeOfNameJCOData2(Integer.parseInt(jco_id));
			    List<TB_CHANGE_TA_STATUS_JCO> ChangeOfTA =  ChngTA.ta_status_jco_GetData2(Integer.parseInt(jco_id));
			    List<TB_CHANGE_OF_APPOINTMENT_JCO> ChngAppointment= Ap.get_AppointmentJCO2(Integer.parseInt(jco_id));
			    List<TB_IDENTITY_CARD_JCO> IdentityCard= IdeCard.Ide_card_getDataJCO2(Integer.parseInt(jco_id));
			    List<TB_CHANGE_RELIGION_JCO> religion = Rel.religion_GetData2(Integer.parseInt(jco_id));
			    List<TB_CENSUS_FAMILY_MARRIED_JCO> Marital = Mrg.update_getfamily_marriageData2(Integer.parseInt(jco_id),Integer.parseInt(request.getParameter("event")));
			    List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> Spouse_Quali = Mrg.getSpouseQualificationJCOData(Integer.parseInt(jco_id), 3);
			    List<TB_CENSUS_CHILDREN_JCO> ChildDetails = Up.getm_children_detailsData2(Integer.parseInt(jco_id));
			    List<TB_NOK_JCO> NOK = NOKc.nok_details_GetData2(Integer.parseInt(jco_id));
			    List<TB_CENSUS_ADDRESS_JCO> ChangeAdd = Chngadd.address_details_GetData2(Integer.parseInt(jco_id));
			    List<TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO> CDAaccount= CDA.cda_acnt_no_GetData2(Integer.parseInt(jco_id));
			    List<TB_CENSUS_LANGUAGE_JCO> Language = lan.update_census_getlanguagedetailsData2(Integer.parseInt(jco_id)); 
			 
			    /////////////
			    List<TB_CENSUS_QUALIFICATION_JCO> Qualification = QUA.getChangeOfQualification2(Integer.parseInt(jco_id));
			    List<TB_CENSUS_PROMO_EXAM_JCO> Promotional_Exam = PEC.getChangeOfPromotionExam2(Integer.parseInt(jco_id)); 
			 
			    List<TB_CENSUS_ARMY_COURSE_JCO> Army_Course = ACC.getChangeOfArmyCourse2(Integer.parseInt(jco_id));
			    List<TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO> btel_cas =  Btel.getChangeBattleCasulity2(Integer.parseInt(jco_id));
			    List<TB_NON_EFFECTIVE_JCO> NonEffective = NonEffec.getNonEffective2(Integer.parseInt(jco_id));
			    List<TB_MEDICAL_CATEGORY_JCO> MedDetails = c_mad.getChangeMedical2(Integer.parseInt(jco_id));				   

	////////////
			    
			    List<TB_CENSUS_BPET_JCO> BEPT =  BPET.getbpet_Data2(Integer.parseInt(jco_id));
			    List<TB_CENSUS_FIRING_STANDARD_JCO> FiringStan = FirSta.getfire_detailsData2(Integer.parseInt(jco_id));
			    List<TB_ALLERGIC_TO_MEDICINE_JCO> ChangeOfAllergy = aler.getChangeOfAllergy2(Integer.parseInt(jco_id));
			    List<TB_CENSUS_FOREIGN_COUNTRY_JCO> ForeignCountry = ForCon.getChangeOfForeignCountry2(Integer.parseInt(jco_id));
			    List<TB_CENSUS_AWARDSNMEDAL_JCO> AwardsMedal= AwrMed.getChangeOfAward2(Integer.parseInt(jco_id)); 
			    List<TB_CENSUS_DISCIPLINE_JCO> ChangeOfDiscipline = Dis.getChangeOfDiscipline2(Integer.parseInt(jco_id));
			    List<TB_INTER_ARM_TRANSFER_JCO> InterArmTrans = IntArmTran.getChangeOfInterArm2(Integer.parseInt(jco_id));
			     List<TB_DESERTER_JCO> deserter1 = deserter.deserter_GetData2(Integer.parseInt(jco_id));
			    List<TB_CANTEEN_CARD_DETAILS1_JCO> csdde = CSD.getCSD_detailsData2(Integer.parseInt(jco_id));
			    
			    List<TB_CHANGE_IN_TRADE_JCO> TradeDetails = trad.Trade_getDataJCO2(Integer.parseInt(jco_id));
				
				List<TB_CHANGE_IN_CLASS_PAY_JCO> PayDetails = pay.class_getDataJCO2(Integer.parseInt(jco_id));
				
				List<TB_CHANGE_IN_PAY_GROUP_JCO> GroupDetails = gp.group_getDataJCO2(Integer.parseInt(jco_id));
				
				List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> SeniorityDetails = se.seniority_getDataJCO2(Integer.parseInt(jco_id));
			    
				List<TB_ATTACHMENT_DETAILS_JCO> AttachmentDetails = att.attachment_getDataJCO2(Integer.parseInt(jco_id));
				List<TB_CHANGE_TYPE_OF_POSTING_JCO> changeofposting = Chngpost.getChangeOfpostJCOData2(Integer.parseInt(jco_id));
		   
		   if(ChangeOfRank.size() > 0) {
			   
			   String hql = "update TB_CHANGE_OF_RANK_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
				
				   String hql1 = "update TB_CHANGE_ARMY_NO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query1 = sessionhql.createQuery(hql1)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query1.executeUpdate() > 0 ? "update" : "0";
					
				
			
		   }
		   if(ChangeOfName.size() > 0) {
			   
			   String hql = "update TB_CHANGE_NAME_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   }
		   
		   if(ChngAppointment.size() > 0) {
			   
			   String hql = "update TB_CHANGE_OF_APPOINTMENT_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
			
		   }
		   if(IdentityCard.size() > 0) {
			   
			   String hql = "update TB_IDENTITY_CARD_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   }
		   if(ChangeOfTA.size() > 0) {
			   
			   String hql = "update TB_CHANGE_TA_STATUS_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   }
		   if(religion.size() > 0) {
			   
			   String hql = "update TB_CHANGE_RELIGION_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   }
		   if(Marital.size() > 0) {
			   
			   String hql = "update TB_CENSUS_FAMILY_MARRIED_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
		   }
		 if(Spouse_Quali.size() > 0) {
				  String hql1 = "update TB_CENSUS_SPOUSE_QUALIFICATION_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query1 = sessionhql.createQuery(hql1)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query1.executeUpdate() > 0 ? "update" : "0";
					
				
		   }
		   if(ChildDetails.size() > 0) {
			   
			   String hql = "update TB_CENSUS_CHILDREN_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date  "
						+ " where  jco_id=:id and status=3";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   }
		   if(NOK.size() > 0) {
			   
			   String hql = "update TB_NOK_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   }
		   if(ChangeAdd.size() > 0) {
			   
			   String hql = "update TB_CENSUS_ADDRESS_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
			
		   }
		   if(CDAaccount.size() > 0) {
			   
			   String hql = "update TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   }
		   if(Language.size() > 0) {
			   
			   String hql = "update TB_CENSUS_LANGUAGE_JCO set status=:status,modify_by=:modified_by,modify_on=:modified_date  "
						+ " where  jco_id=:id and status=3";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   }
		   if(Qualification.size() > 0) {
			   
			   String hql = "update TB_CENSUS_QUALIFICATION_JCO set status=:status,modify_by=:modified_by,modify_on=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   }
		   
		   if(Promotional_Exam.size() > 0) {
			   
			   String hql = "update TB_CENSUS_PROMO_EXAM_JCO set status=:status,modify_by=:modified_by,modify_on=:modified_date  "
						+ " where  jco_id=:id and status=3";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   } 
		   if(Army_Course.size() > 0) {
			   
			   String hql = "update TB_CENSUS_ARMY_COURSE_JCO set status=:status,modify_by=:modified_by,modify_on=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   }
		   if(btel_cas.size() > 0) {
			   
			   String hql = "update TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO set status=:status,modify_by=:modified_by,modify_on=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   } 
		   if(NonEffective.size() > 0) {
			   
			   String hql = "update TB_NON_EFFECTIVE_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   }
		   if(MedDetails.size() > 0) {
			   
			   String hql = "update TB_MEDICAL_CATEGORY_JCO set status=:status,modify_by=:modified_by,modify_on=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
			
		   }
		   if(BEPT.size() > 0) {
			   
			   String hql = "update TB_CENSUS_BPET_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				
		   }
		   if(FiringStan.size() > 0) {
			   
			   String hql = "update TB_CENSUS_FIRING_STANDARD_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
			
		   }
		   	if(ChangeOfAllergy.size() > 0) {
			   
			   String hql = "update TB_ALLERGIC_TO_MEDICINE_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";
			
		   }
		   	if(ForeignCountry.size() > 0) {
				   
				   String hql = "update TB_CENSUS_FOREIGN_COUNTRY_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query = sessionhql.createQuery(hql)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query.executeUpdate() > 0 ? "update" : "0";
					
			   }
		   	if(AwardsMedal.size() > 0) {
				   
				   String hql = "update TB_CENSUS_AWARDSNMEDAL_JCO set status=:status,modify_by=:modified_by,modify_on=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query = sessionhql.createQuery(hql)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query.executeUpdate() > 0 ? "update" : "0";
					
			   }
		   	if(ChangeOfDiscipline.size() > 0) {
				   
				   String hql = "update TB_CENSUS_DISCIPLINE_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query = sessionhql.createQuery(hql)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query.executeUpdate() > 0 ? "update" : "0";
					
			   }
		   	if(InterArmTrans.size() > 0) {
				   
				   String hql = "update TB_INTER_ARM_TRANSFER_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query = sessionhql.createQuery(hql)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query.executeUpdate() > 0 ? "update" : "0";
				
			   }
		  
		   	if(deserter1.size() > 0) {
				   
				   String hql = "update TB_DESERTER_JCO set status=:status,approved_by=:modified_by,approved_date=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query = sessionhql.createQuery(hql)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query.executeUpdate() > 0 ? "update" : "0";
				
			   }
		   	if(csdde.size() > 0) {
				   
				   String hql = "update TB_CANTEEN_CARD_DETAILS1_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query = sessionhql.createQuery(hql)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query.executeUpdate() > 0 ? "update" : "0";
				
			   }
		   	
		   	if(TradeDetails.size() > 0) {
				   String hql = "update TB_CHANGE_IN_TRADE_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query = sessionhql.createQuery(hql)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query.executeUpdate() > 0 ? "update" : "0";
			   }
		   
		    if(PayDetails.size() > 0) {
				   String hql = "update TB_CHANGE_IN_CLASS_PAY_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query = sessionhql.createQuery(hql)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query.executeUpdate() > 0 ? "update" : "0";
			   }
		    
		   
		    if(GroupDetails.size() > 0) {
				   String hql = "update TB_CHANGE_IN_PAY_GROUP_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query = sessionhql.createQuery(hql)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query.executeUpdate() > 0 ? "update" : "0";
			   }
		 
		    if(SeniorityDetails.size() > 0) {
				   String hql = "update TB_CHANGE_IN_DATE_OF_SENIORITY_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query = sessionhql.createQuery(hql)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query.executeUpdate() > 0 ? "update" : "0";
			   }
		    
		    if(AttachmentDetails.size() > 0) {
				   String hql = "update TB_ATTACHMENT_DETAILS_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query = sessionhql.createQuery(hql)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query.executeUpdate() > 0 ? "update" : "0";
			   }
		    if(changeofposting.size() > 0) {
				   String hql = "update TB_CHANGE_TYPE_OF_POSTING_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
							+ " where  jco_id=:id and status='3'";
					Query query = sessionhql.createQuery(hql)
							.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 0);

					msg = query.executeUpdate() > 0 ? "update" : "0";
			   }
		    
		   //msg = pjc.update_JcoOr_status(Integer.parseInt(jco_id),username);
		    
		    tx.commit();
		    
		  	if(search_UpJCO.check_Update_JCO_DataStatusForPending(Integer.parseInt(jco_id),Integer.parseInt(request.getParameter("event")))) {

		   	 	msg = pjc.update_JcoOr_status(Integer.parseInt(jco_id),username);
		   	 	if (msg.equals("Data Not Updated")) {
			   	 	tx.rollback();
					}


		   	}else {

		   		String id="";
				TB_CENSUS_JCO_OR_PARENT P=(TB_CENSUS_JCO_OR_PARENT)sessionhql.get(TB_CENSUS_JCO_OR_PARENT.class,Integer.parseInt(jco_id));

		   		msg=change_Update_JCO_Data(Integer.parseInt(jco_id),1);
		   		if (msg.equals("Data Not Updated")) {
			   	 	tx.rollback();
					}


		   	}

		  
			}
			 catch (Exception e) {
	          	 msg = "Data Not Updated";

					tx.rollback();
	           }
		  
		    
		return msg;
	 }
	 
	 
	 public String change_Update_JCO_Data(int id, int update_jco_status) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionHQL.beginTransaction();

			String msg = "";
           
			try {
				String hql = "update TB_CENSUS_JCO_OR_PARENT set "

						+ "update_jco_status=:update_jco_status where id=:id and status = '1'" ;

				Query query = sessionHQL.createQuery(hql).setInteger("update_jco_status", update_jco_status)
						.setInteger("id", id);

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
}
