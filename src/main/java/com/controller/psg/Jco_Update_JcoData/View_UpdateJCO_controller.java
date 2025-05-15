package com.controller.psg.Jco_Update_JcoData;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Jco_Update_JcoData.View_Update_JCODao;
import com.dao.psg.Master.Psg_CommanDAO;
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
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;


@Controller
@RequestMapping(value = {"admin","/","user"})

public class View_UpdateJCO_controller {
	
	@Autowired
	View_Update_JCODao UOD;
	
	@Autowired
	Psg_CommanDAO psg_com;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	Psg_CommonController mcommon = new Psg_CommonController();
	psg_jco_CommonController pjc=new psg_jco_CommonController();
	
	
@RequestMapping(value = "/view_ApproveUpadteJCODataUrl", method = RequestMethod.POST)
public ModelAndView view_ApproveUpadteJCODataUrl(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
		HttpServletRequest request, HttpSession sessionEdit, HttpSession session,
		
		@ModelAttribute("personnel_no5") String personnel_no5, 
		@ModelAttribute("status5") String status5, 
		@ModelAttribute("rank5") String rank, 
		@ModelAttribute("jco_id5") String jco_id5, 
		@ModelAttribute("unit_name5") String unit_name, 
		@ModelAttribute("unit_sus_no5") String unit_sus_no)
		throws NumberFormatException, ParseException {

	String updateid = request.getParameter("ap_id");
	String jco_id = request.getParameter("ap_jco_id");
	String personnel_no = request.getParameter("ap_personnel_no");
	String event = request.getParameter("ap_event");

	String roleSusNo = session.getAttribute("roleSusNo").toString();
	String roleid = session.getAttribute("roleid").toString();

	Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", roleid);
	if (val == false) {
		return new ModelAndView("AccessTiles");
	}
	if (request.getHeader("Referer") == null) {
		msg = "";
	}

	Mmap.put("personnel_no5", personnel_no5);
	Mmap.put("status5", status5);
	Mmap.put("rank5", rank);
	Mmap.put("jco_id5", jco_id5);
	Mmap.put("unit_name5", unit_name);
	Mmap.put("unit_sus_no5", unit_sus_no);
	
	
	
	Date modifiedDate = UOD.getParentModifiedDate(Integer.parseInt(jco_id));
	List<TB_CHANGE_OF_RANK_JCO> ChangeOfRank = UOD.getChangeOfRankData(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CHANGE_NAME_JCO> ChangeOfName = UOD.getChangeOfNameData(Integer.parseInt(jco_id), modifiedDate);

	List<TB_CHANGE_TYPE_OF_POSTING_JCO> ChangeOfposting = UOD.getChangeOfpostingviewData(Integer.parseInt(jco_id), modifiedDate);
	List<TB_NON_EFFECTIVE_JCO> NonEffective = UOD.getNon_effective(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CENSUS_CHILDREN_JCO> ChildDetails = UOD.getm_children_detailsData(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO> CDAaccount = UOD.cda_acnt_no_GetData(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CHANGE_RELIGION_JCO> religion = UOD.religion_GetData(Integer.parseInt(jco_id),
			modifiedDate);

	List<TB_INTER_ARM_TRANSFER_JCO> InterArmTrans = UOD.getInterArm(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CENSUS_AWARDSNMEDAL_JCO> AwardsMedal = UOD.getawardsNmedalData(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CENSUS_LANGUAGE_JCO> Language = UOD.update_census_getlanguagedetailsData(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CENSUS_QUALIFICATION_JCO> Qualification = UOD.update_census_getQualificationData(
			Integer.parseInt(jco_id), modifiedDate);

	List<TB_CHANGE_OF_APPOINTMENT_JCO> ChngAppointment = UOD.get_Appointment(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CENSUS_FOREIGN_COUNTRY_JCO> ForeignCountry = UOD.getUPForeginCountryData(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CHANGE_TA_STATUS_JCO> ChangeOfTA = UOD.getChangeOfTAStatusData(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CENSUS_FAMILY_MARRIED_JCO> Marital = UOD.update_getfamily_marriageData(
			Integer.parseInt(jco_id), modifiedDate, Integer.parseInt(event));
	List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> Spouse_Quali = UOD.getUpdatedSpouseQualificationData(Integer.parseInt(jco_id), modifiedDate);

	List<TB_CENSUS_FIRING_STANDARD_JCO> FiringStan = UOD.getfire_detailsData(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_NOK_JCO> NOK = UOD.nok_details_GetData(Integer.parseInt(jco_id),
			modifiedDate);
	List<TB_CENSUS_BPET_JCO> BEPT = UOD.getbpet_Data(Integer.parseInt(jco_id),
			modifiedDate);
	List<TB_IDENTITY_CARD_JCO> IdentityCard = UOD.Ide_card_getData(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CENSUS_ADDRESS_JCO> ChangeAdd = UOD.address_details_GetData(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO> btel_cas = UOD.Battle_and_Physical_Casuality_GetData(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CENSUS_DISCIPLINE_JCO> Discipline = UOD.get_Discipline(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CENSUS_PROMO_EXAM_JCO> Promotional_Exam = UOD.update_census_promo_examData(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_CENSUS_ARMY_COURSE_JCO> Army_Course = UOD.update_census_army_courseData(
			Integer.parseInt(jco_id), modifiedDate);
	List<TB_ALLERGIC_TO_MEDICINE_JCO> Allergy = UOD.update_census_allergicData(
			Integer.parseInt(jco_id), modifiedDate);

	List<TB_MEDICAL_CATEGORY_JCO> MedDetails = UOD.getUpdatedmadicalData(
			Integer.parseInt(jco_id), modifiedDate);
	
	 List<TB_DESERTER_JCO>  deserter = UOD.get_Deserter(
				Integer.parseInt(jco_id), modifiedDate);
	 //keval
	 List<TB_CANTEEN_CARD_DETAILS1_JCO> CSDDetails = UOD.getCSD_detailsData(
				Integer.parseInt(jco_id), modifiedDate);
	 
	 List<TB_CHANGE_IN_TRADE_JCO> trade = UOD.Trade_getData(
				Integer.parseInt(jco_id), modifiedDate);
	 
	 List<TB_CHANGE_IN_CLASS_PAY_JCO> cl = UOD.Class_Pay_getData(
				Integer.parseInt(jco_id), modifiedDate);
	 
	 List<TB_CHANGE_IN_PAY_GROUP_JCO> group = UOD.Gorup_Pay_getData(
				Integer.parseInt(jco_id), modifiedDate);
	 
	 List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> seniority = UOD.Seniority_getData(
				Integer.parseInt(jco_id), modifiedDate);
	 
	 List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> old_seniority = UOD.old_Seniority_getData(
				Integer.parseInt(jco_id), modifiedDate);
	 
	 List<TB_ATTACHMENT_DETAILS_JCO> attachment = UOD.Attachment_getData(
				Integer.parseInt(jco_id), modifiedDate);

	Mmap.put("msg", msg);
	Mmap.put("census_id", updateid);
	Mmap.put("personnel_no2", personnel_no);
	Mmap.put("jco_id", jco_id);
	Mmap.put("event", event);

	Mmap.put("ChangeOfRank", ChangeOfRank);
	Mmap.put("ChangeOfName", ChangeOfName);
	Mmap.put("NonEffective", NonEffective);
	Mmap.put("ChildDetails", ChildDetails);
	Mmap.put("CDAaccount", CDAaccount);
	Mmap.put("religion", religion);
	Mmap.put("ChangeOfTA", ChangeOfTA);
	
	Mmap.put("InterArmTransfer", InterArmTrans);
	Mmap.put("AwardsMedal", AwardsMedal);
	Mmap.put("Language", Language);
	Mmap.put("Qualification", Qualification);

	Mmap.put("ChngAppointment", ChngAppointment);
	Mmap.put("ForeignCountry", ForeignCountry);
	Mmap.put("FiringStan", FiringStan);
	Mmap.put("NOK", NOK);
	Mmap.put("BEPT", BEPT);
	Mmap.put("IdentityCard", IdentityCard);
	Mmap.put("ChangeAdd", ChangeAdd);
	Mmap.put("Marital", Marital);
	Mmap.put("Spouse_Quali", Spouse_Quali);
	Mmap.put("btel_cas", btel_cas);
	Mmap.put("Discipline", Discipline);
	Mmap.put("Promotional_Exam", Promotional_Exam);
	Mmap.put("Army_Course", Army_Course);
	Mmap.put("Allergy", Allergy);
	Mmap.put("MedDetails", MedDetails);
	Mmap.put("deserterV", deserter);
	Mmap.put("CSDDetails", CSDDetails);//kevalc
	Mmap.put("getCSDCategoryList", mcommon.getCSDCategoryList());
	Mmap.put("gettastatusList", mcommon.getstatusList());
    Mmap.put("ChangeOfposting1", ChangeOfposting);
	Mmap.put("trade1", trade);
	Mmap.put("cl1", cl);
	Mmap.put("group1", group);
	Mmap.put("seniority1", seniority);
	Mmap.put("seniority1_old", old_seniority);
	Mmap.put("attachment1", attachment);
	
	Mmap.put("getArmyCourseInstitute", mcommon.getArmyCourseInstitute("2"));
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
	Mmap.put("getArmyType", mcommon.getArmyType());
	Mmap.put("getTypeOfCommissionList", mcommon.getTypeOfCommissionList());
	Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());
	Mmap.put("getInstituteNameList", mcommon.getInstituteNameList());
	Mmap.put("getDivclass", mcommon.getDivclass());
	Mmap.put("getSubject", mcommon.getSubject());
	Mmap.put("getExamination", mcommon.getExamination());
	Mmap.put("getCommandDetailsList", m.getCommandDetailsList());
	Mmap.put("getMedalList", mcommon.getMedalList());
	Mmap.put("getTypeofAppointementList", pjc.getTypeofAppointementListJCO());

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
	Mmap.put("getdisciplinetypeentry", mcommon.getdisciplinetypeentry());
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
	//Mmap.put("getStreamList", mcommon.getStreamList());
	Mmap.put("getCourseName", mcommon.getCourseName());
	Mmap.put("getFdService", mcommon.getFdService());
	Mmap.put("getCourseTypeList", mcommon.getCourseTypeList());
	Mmap.put("getExamList", pjc.getExamList_jco());
	 Mmap.put("getSpecializationList", mcommon.getSpecializationList());
	 Mmap.put("getDclredRcvrdList", mcommon.getDclredRcvrdList());
	 Mmap.put("getCauseOfDeserter", mcommon.getCauseOfDeserter());
	 Mmap.put("getARM_TYPE", mcommon.getARM_TYPE());
	 Mmap.put("getExservicemenList", mcommon.getExservicemenList());
	 Mmap.put("getMissingList", mcommon.getMissingList());

	 Mmap.put("getCausesOfCasuality", mcommon.getCausesOfCasuality());
	 
	 
	 //-----smit--------//
	 Mmap.put("getDisc_Trialed", mcommon.getDisc_Trialed());
	 Mmap.put("getArmyAct_Sec", mcommon.getArmyAct_Sec());
	 Mmap.put("getSub_Clause", mcommon.getSub_Clause());

	 Mmap.put("gettype_of_postingList", pjc.gettype_of_postingList());
	 Mmap.put("getTradeList", pjc.getTradeList());
		Mmap.put("getClassPayList", pjc.getClassPayList());
		Mmap.put("getPayGroupList", pjc.getPayGroupList());

	 
	Mmap.put("msg", msg);
	return new ModelAndView("View_ApproveUpdateJCODataTiles");
}


}