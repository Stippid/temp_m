package com.controller.XMLReader;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.spi.XmlReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.Psg_CommanDAO;
import com.dao.psg.update_census_data.View_update_Dao;
import com.dao.psg.xml.ViewApproved_xmlDao;
import com.models.psg.Transaction.TB_CENSUS_ADDRESS;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;
import com.models.psg.Transaction.TB_CENSUS_FOREIGN_COUNTRY;
import com.models.psg.Transaction.TB_CENSUS_IDENTITY_CARD;
import com.models.psg.Transaction.TB_CENSUS_LANGUAGE;
import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;
import com.models.psg.Transaction.TB_CENSUS_NOK;
import com.models.psg.Transaction.TB_CENSUS_QUALIFICATION;
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_PSG_CANTEEN_CARD_DETAIL1;
import com.models.psg.Transaction.TB_PSG_CENSUS_ALLERGICTOMEDICINE;
import com.models.psg.update_census_data.TB_CDA_ACC_NO;
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
import com.models.psg.update_census_data.TB_CHANGE_TA_STATUS;
import com.models.psg.update_census_data.TB_DESERTER;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_NON_EFFECTIVE;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_EXTENSION_OF_COMISSION;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class ViewApprovedXml {
	XMLController xmlcontroller=new XMLController();

	
	
//	@Autowired
//	private RoleBaseMenuDAO roledao;

	@Autowired
	Psg_CommanDAO psg_com;
	
	

//	@Autowired
//	View_update_Dao UOD;
	
	@Autowired
	ViewApproved_xmlDao UOD;
	
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	Psg_CommonController mcommon = new Psg_CommonController();

	ValidationController valid = new ValidationController();

	@RequestMapping(value = "/admin/XMl_aproved", method = RequestMethod.GET)
	public ModelAndView XMl_aproved(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		return new ModelAndView("View_Approved_XML_DataTiles");
	}

	@RequestMapping(value = "/admin/Approved_xml_data_view", method = RequestMethod.POST)
	public ModelAndView Approved_xml_data_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "comm_id_view", required = false) String comm_id_s,
			@RequestParam(value = "date_modified", required = false) String modifiedDate,
			@RequestParam(value = "personnel_no5", required = false) String personnel_no5,
			HttpServletRequest request)
		throws ParseException {



//	BigInteger comm_id5 = BigInteger.ZERO;
//	if(comm_id5!=BigInteger.ZERO) {
//		comm_id5 = new BigInteger(comm_id51);
//	}
	

	int  updateid = xmlcontroller.getCensusid(new BigInteger(comm_id_s));



	BigInteger comm_id =new BigInteger(comm_id_s);



	String personnel_no = request.getParameter("ap_personnel_no");



	



	String roleSusNo = session.getAttribute("roleSusNo").toString();



	String roleid = session.getAttribute("roleid").toString();





	Mmap.put("personnel_no5", personnel_no5);


//
//	Mmap.put("status5", status5);
//
//
//
//	Mmap.put("rank5", rank);



	Mmap.put("comm_id5", comm_id);



//	Mmap.put("unit_name5", unit_name);
//
//
//
//	Mmap.put("unit_sus_no5", unit_sus_no);



//	Date modifiedDate = UOD.getParentModifiedDate(updateid, comm_id);



	List<TB_CHANGE_OF_RANK> ChangeOfRank = UOD.getChangeOfRankData(comm_id, modifiedDate);



	List<TB_CHANGE_NAME> ChangeOfName = UOD.getChangeOfNameData(



			comm_id, modifiedDate);



	List<TB_NON_EFFECTIVE> NonEffective = UOD.getNon_effective(



			comm_id, modifiedDate);

//	List<TB_CHANGE_TA_STATUS> ChangeOfTA = UOD.getChangeOfTAData(
//
//
//
//			comm_id, modifiedDate);


	List<TB_CENSUS_CHILDREN> ChildDetails = UOD.getm_children_detailsData(



			comm_id, modifiedDate);



	List<TB_CENSUS_CDA_ACCOUNT_NO> CDAaccount = UOD.cda_acnt_no_GetData(



			comm_id, modifiedDate);



	List<TB_CHANGE_RELIGION> religion = UOD.religion_GetData(comm_id,



			modifiedDate);



	List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfComm = UOD.getcocData(



			comm_id, modifiedDate);



	List<TB_PSG_EXTENSION_OF_COMISSION> ExtenComm = UOD.geteocData(



			comm_id, modifiedDate);



	List<TB_INTER_ARM_TRANSFER> InterArmTrans = UOD.getInterArm(



			comm_id, modifiedDate);
	
	List<TB_INTER_ARM_TRANSFER> InterArmTrans_old = UOD.getInterArm_old(



			comm_id, modifiedDate);



	List<TB_CENSUS_AWARDSNMEDAL> AwardsMedal = UOD.getawardsNmedalData(



			comm_id, modifiedDate);



	List<TB_CENSUS_LANGUAGE> Language = UOD.update_census_getlanguagedetailsData(



			comm_id, modifiedDate);



	List<TB_CENSUS_QUALIFICATION> Qualification = UOD.update_census_getQualificationData(



			comm_id, modifiedDate);



	List<TB_CENSUS_SECONDMENT> Secondment = UOD.get_Secondment(



			comm_id, modifiedDate);



	List<TB_CHANGE_OF_APPOINTMENT> ChngAppointment = UOD.get_Appointment(



			comm_id, modifiedDate);



	List<TB_CENSUS_FOREIGN_COUNTRY> ForeignCountry = UOD.getUPForeginCountryData(



			comm_id, modifiedDate);



//	List<TB_CENSUS_FAMILY_MRG> Marital = UOD.update_getfamily_marriageData(
//
//
//
//			comm_id, modifiedDate, Integer.parseInt(event));

	

	
//
//	List<TB_CENSUS_SPOUSE_QUALIFICATION> Spouse_Quali = UOD.getUpdatedSpouseQualificationData(comm_id, modifiedDate);
//


	List<TB_CENSUS_FIRING_STANDARD> FiringStan = UOD.getfire_detailsData(



			comm_id, modifiedDate);



	List<TB_CENSUS_NOK> NOK = UOD.nok_details_GetData( comm_id,



			modifiedDate);



	List<TB_CENSUS_BPET> BEPT = UOD.getbpet_Data( comm_id,



			modifiedDate);



	List<TB_CENSUS_IDENTITY_CARD> IdentityCard = UOD.Ide_card_getData(



			comm_id, modifiedDate);



	List<TB_CENSUS_ADDRESS> ChangeAdd = UOD.address_details_GetData(



			comm_id, modifiedDate);



	List<TB_CENSUS_BATT_PHY_CASUALITY> btel_cas = UOD.Battle_and_Physical_Casuality_GetData(



			 comm_id, modifiedDate);



	List<TB_CENSUS_DISCIPLINE> Discipline = UOD.get_Discipline(



			comm_id, modifiedDate);



	List<TB_CENSUS_PROMOTIONAL_EXAM> Promotional_Exam = UOD.update_census_promo_examData(



			comm_id, modifiedDate);



//	List<TB_CENSUS_ARMY_COURSE> Army_Course = UOD.update_census_army_courseData(
//
//
//
//			comm_id, modifiedDate);



//	List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> Allergy = UOD.update_census_allergicData(
//
//
//
//			comm_id, modifiedDate);



	List<TB_CENSUS_MEDICAL_CATEGORY> MedDetails = UOD.getUpdatedmadicalData(



			comm_id, modifiedDate);



	List<TB_DESERTER> deserter = UOD.get_Deserter(



			comm_id, modifiedDate);



	// keval



	List<TB_PSG_CANTEEN_CARD_DETAIL1> CSDDetails = UOD.getCSD_detailsData(



			comm_id, modifiedDate);


	List<TB_POSTING_IN_OUT> ChangeOfPosting= UOD.getposting_in_data1_xml(comm_id,modifiedDate);
	List<TB_CDA_ACC_NO> ChangeOfCDA=UOD.cda_GetData_xml (comm_id,modifiedDate);
	
	



//	Mmap.put("msg", msg);

	Mmap.put("ChangeOfPosting", ChangeOfPosting.size());


	Mmap.put("ChangeOfCDA", ChangeOfCDA.size());



	Mmap.put("personnel_no2", personnel_no5);



	Mmap.put("comm_id", comm_id);

	Mmap.put("census_id", updateid);
//	Mmap.put("ChangeOfTA", ChangeOfTA);

//	Mmap.put("event", event);

	// bisag v2 03072023(Observation)
	Mmap.put("getProfession", mcommon.getProfession());

	Mmap.put("ChangeOfRank", ChangeOfRank);
	Mmap.put("gettastatusList", mcommon.getstatusList());


	Mmap.put("ChangeOfName", ChangeOfName);



	Mmap.put("NonEffective", NonEffective);



	Mmap.put("ChildDetails", ChildDetails);



	Mmap.put("CDAaccount", CDAaccount);



	Mmap.put("religion", religion);



	Mmap.put("ChangeOfComm", ChangeOfComm);



	Mmap.put("ExtensionComm", ExtenComm);



	Mmap.put("InterArmTransfer", InterArmTrans);
	Mmap.put("InterArmTransfer_old", InterArmTrans_old);
	



	Mmap.put("AwardsMedal", AwardsMedal);



	Mmap.put("Language", Language);



	Mmap.put("Qualification", Qualification);



	Mmap.put("Secondment", Secondment);



	Mmap.put("ChngAppointment", ChngAppointment);



	Mmap.put("ForeignCountry", ForeignCountry);



	Mmap.put("FiringStan", FiringStan);



	Mmap.put("NOK", NOK);



	Mmap.put("BEPT", BEPT);



	Mmap.put("IdentityCard", IdentityCard);



	Mmap.put("ChangeAdd", ChangeAdd);



//	Mmap.put("Marital", Marital);

//	Mmap.put("Spouse_Quali", Spouse_Quali);

	

	



	Mmap.put("btel_cas", btel_cas);



	Mmap.put("Discipline", Discipline);



	Mmap.put("Promotional_Exam", Promotional_Exam);


//
//	Mmap.put("Army_Course", Army_Course);



//	Mmap.put("Allergy", Allergy);



	Mmap.put("MedDetails", MedDetails);



	Mmap.put("deserterV", deserter);



	Mmap.put("CSDDetails", CSDDetails);// kevalc



	Mmap.put("getCSDCategoryList", mcommon.getCSDCategoryList());






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



	// Mmap.put("getMedStateName", mcommon.getMedStateName("", session));



	// Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));



	// Mmap.put("getMedCityName", mcommon.getMedCityName("", session));



	// Mmap.put("getCountryList", mcommon.getCountryList());



	// Mmap.put("getMedStateName", mcommon.getMedStateName("", session));



	Mmap.put("getFamily_siblings", mcommon.getFamily_siblings());



	Mmap.put("getdisciplinetypeentry", mcommon.getdisciplinetypeentry());



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



	// Mmap.put("getStreamList", mcommon.getStreamList());



	Mmap.put("getCourseName", mcommon.getCourseName());



	Mmap.put("getFdService", mcommon.getFdService());



	Mmap.put("getCourseTypeList", mcommon.getCourseTypeList());



	Mmap.put("getExamList", mcommon.getExamList());



	Mmap.put("getSpecializationList", mcommon.getSpecializationList());



	Mmap.put("getDclredRcvrdList", mcommon.getDclredRcvrdList());



	Mmap.put("getCauseOfDeserter", mcommon.getCauseOfDeserter());



	Mmap.put("getARM_TYPE", mcommon.getARM_TYPE());



	Mmap.put("getExservicemenList", mcommon.getExservicemenList());



	Mmap.put("getMissingList", mcommon.getMissingList());



	Mmap.put("getCausesOfCasuality", mcommon.getCausesOfCasuality());
	Mmap.put("getArmyCourseInstitute", mcommon.getArmyCourseInstitute("1"));  


	// -----smit--------//



	Mmap.put("getDisc_Trialed", mcommon.getDisc_Trialed());



	Mmap.put("getArmyAct_Sec", mcommon.getArmyAct_Sec());



	Mmap.put("getSub_Clause", mcommon.getSub_Clause());
	
//kajal
	//Mmap.put("getBattleDescList", mcommon.getBattleDescList());
	

//	Mmap.put("msg", msg);



	return new ModelAndView("View_Approved_XML_DataTiles");





}
	@RequestMapping(value = "/admin/Approved_xml_data_view_pop_up", method = RequestMethod.POST)
	public ModelAndView Approved_xml_data_view_pop_up(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "comm_id_view", required = false) String comm_id_s,
			@RequestParam(value = "date_modified", required = false) String modifiedDate,
			@RequestParam(value = "personnel_no5", required = false) String personnel_no5,
			HttpServletRequest request)
		throws ParseException {



//	BigInteger comm_id5 = BigInteger.ZERO;
//	if(comm_id5!=BigInteger.ZERO) {
//		comm_id5 = new BigInteger(comm_id51);
//	}
	

	int  updateid = xmlcontroller.getCensusid(new BigInteger(comm_id_s));



	BigInteger comm_id =new BigInteger(comm_id_s);



	String personnel_no = request.getParameter("ap_personnel_no");



	



	String roleSusNo = session.getAttribute("roleSusNo").toString();



	String roleid = session.getAttribute("roleid").toString();





	Mmap.put("personnel_no5", personnel_no5);


//
//	Mmap.put("status5", status5);
//
//
//
//	Mmap.put("rank5", rank);



	Mmap.put("comm_id5", comm_id);



//	Mmap.put("unit_name5", unit_name);
//
//
//
//	Mmap.put("unit_sus_no5", unit_sus_no);



//	Date modifiedDate = UOD.getParentModifiedDate(updateid, comm_id);



	List<TB_CHANGE_OF_RANK> ChangeOfRank = UOD.getChangeOfRankData(comm_id, modifiedDate);



	List<TB_CHANGE_NAME> ChangeOfName = UOD.getChangeOfNameData(



			comm_id, modifiedDate);



	List<TB_NON_EFFECTIVE> NonEffective = UOD.getNon_effective(



			comm_id, modifiedDate);

//	List<TB_CHANGE_TA_STATUS> ChangeOfTA = UOD.getChangeOfTAData(
//
//
//
//			comm_id, modifiedDate);


	List<TB_CENSUS_CHILDREN> ChildDetails = UOD.getm_children_detailsData(



			comm_id, modifiedDate);



	List<TB_CENSUS_CDA_ACCOUNT_NO> CDAaccount = UOD.cda_acnt_no_GetData(



			comm_id, modifiedDate);



	List<TB_CHANGE_RELIGION> religion = UOD.religion_GetData(comm_id,



			modifiedDate);



	List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfComm = UOD.getcocData(



			comm_id, modifiedDate);



	List<TB_PSG_EXTENSION_OF_COMISSION> ExtenComm = UOD.geteocData(



			comm_id, modifiedDate);



	List<TB_INTER_ARM_TRANSFER> InterArmTrans = UOD.getInterArm(



			comm_id, modifiedDate);
	
	List<TB_INTER_ARM_TRANSFER> InterArmTrans_old = UOD.getInterArm_old(



			comm_id, modifiedDate);



	List<TB_CENSUS_AWARDSNMEDAL> AwardsMedal = UOD.getawardsNmedalData(



			comm_id, modifiedDate);



	List<TB_CENSUS_LANGUAGE> Language = UOD.update_census_getlanguagedetailsData(



			comm_id, modifiedDate);



	List<TB_CENSUS_QUALIFICATION> Qualification = UOD.update_census_getQualificationData(



			comm_id, modifiedDate);



	List<TB_CENSUS_SECONDMENT> Secondment = UOD.get_Secondment(



			comm_id, modifiedDate);



	List<TB_CHANGE_OF_APPOINTMENT> ChngAppointment = UOD.get_Appointment(



			comm_id, modifiedDate);



	List<TB_CENSUS_FOREIGN_COUNTRY> ForeignCountry = UOD.getUPForeginCountryData(



			comm_id, modifiedDate);



//	List<TB_CENSUS_FAMILY_MRG> Marital = UOD.update_getfamily_marriageData(
//
//
//
//			comm_id, modifiedDate, Integer.parseInt(event));

	

	
//
//	List<TB_CENSUS_SPOUSE_QUALIFICATION> Spouse_Quali = UOD.getUpdatedSpouseQualificationData(comm_id, modifiedDate);
//


	List<TB_CENSUS_FIRING_STANDARD> FiringStan = UOD.getfire_detailsData(



			comm_id, modifiedDate);



	List<TB_CENSUS_NOK> NOK = UOD.nok_details_GetData( comm_id,



			modifiedDate);



	List<TB_CENSUS_BPET> BEPT = UOD.getbpet_Data( comm_id,



			modifiedDate);



	List<TB_CENSUS_IDENTITY_CARD> IdentityCard = UOD.Ide_card_getData(



			comm_id, modifiedDate);



	List<TB_CENSUS_ADDRESS> ChangeAdd = UOD.address_details_GetData(



			comm_id, modifiedDate);



	List<TB_CENSUS_BATT_PHY_CASUALITY> btel_cas = UOD.Battle_and_Physical_Casuality_GetData(



			 comm_id, modifiedDate);



	List<TB_CENSUS_DISCIPLINE> Discipline = UOD.get_Discipline(



			comm_id, modifiedDate);



	List<TB_CENSUS_PROMOTIONAL_EXAM> Promotional_Exam = UOD.update_census_promo_examData(



			comm_id, modifiedDate);



//	List<TB_CENSUS_ARMY_COURSE> Army_Course = UOD.update_census_army_courseData(
//
//
//
//			comm_id, modifiedDate);



//	List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> Allergy = UOD.update_census_allergicData(
//
//
//
//			comm_id, modifiedDate);



	List<TB_CENSUS_MEDICAL_CATEGORY> MedDetails = UOD.getUpdatedmadicalData(



			comm_id, modifiedDate);



	List<TB_DESERTER> deserter = UOD.get_Deserter(



			comm_id, modifiedDate);



	// keval



	List<TB_PSG_CANTEEN_CARD_DETAIL1> CSDDetails = UOD.getCSD_detailsData(



			comm_id, modifiedDate);


	List<TB_POSTING_IN_OUT> ChangeOfPosting= UOD.getposting_in_data1_xml(comm_id,modifiedDate);
	List<TB_CDA_ACC_NO> ChangeOfCDA=UOD.cda_GetData_xml (comm_id,modifiedDate);
	
	



//	Mmap.put("msg", msg);

	Mmap.put("ChangeOfPosting", ChangeOfPosting.size());


	Mmap.put("ChangeOfCDA", ChangeOfCDA.size());



	Mmap.put("personnel_no2", personnel_no5);



	Mmap.put("comm_id", comm_id);

	Mmap.put("census_id", updateid);
//	Mmap.put("ChangeOfTA", ChangeOfTA);

//	Mmap.put("event", event);

	// bisag v2 03072023(Observation)
	Mmap.put("getProfession", mcommon.getProfession());

	Mmap.put("ChangeOfRank", ChangeOfRank);
	Mmap.put("gettastatusList", mcommon.getstatusList());


	Mmap.put("ChangeOfName", ChangeOfName);



	Mmap.put("NonEffective", NonEffective);



	Mmap.put("ChildDetails", ChildDetails);



	Mmap.put("CDAaccount", CDAaccount);



	Mmap.put("religion", religion);



	Mmap.put("ChangeOfComm", ChangeOfComm);



	Mmap.put("ExtensionComm", ExtenComm);



	Mmap.put("InterArmTransfer", InterArmTrans);
	Mmap.put("InterArmTransfer_old", InterArmTrans_old);
	



	Mmap.put("AwardsMedal", AwardsMedal);



	Mmap.put("Language", Language);



	Mmap.put("Qualification", Qualification);



	Mmap.put("Secondment", Secondment);



	Mmap.put("ChngAppointment", ChngAppointment);



	Mmap.put("ForeignCountry", ForeignCountry);



	Mmap.put("FiringStan", FiringStan);



	Mmap.put("NOK", NOK);



	Mmap.put("BEPT", BEPT);



	Mmap.put("IdentityCard", IdentityCard);



	Mmap.put("ChangeAdd", ChangeAdd);



//	Mmap.put("Marital", Marital);

//	Mmap.put("Spouse_Quali", Spouse_Quali);

	

	



	Mmap.put("btel_cas", btel_cas);



	Mmap.put("Discipline", Discipline);



	Mmap.put("Promotional_Exam", Promotional_Exam);


//
//	Mmap.put("Army_Course", Army_Course);



//	Mmap.put("Allergy", Allergy);



	Mmap.put("MedDetails", MedDetails);



	Mmap.put("deserterV", deserter);



	Mmap.put("CSDDetails", CSDDetails);// kevalc



	Mmap.put("getCSDCategoryList", mcommon.getCSDCategoryList());






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



	// Mmap.put("getMedStateName", mcommon.getMedStateName("", session));



	// Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));



	// Mmap.put("getMedCityName", mcommon.getMedCityName("", session));



	// Mmap.put("getCountryList", mcommon.getCountryList());



	// Mmap.put("getMedStateName", mcommon.getMedStateName("", session));



	Mmap.put("getFamily_siblings", mcommon.getFamily_siblings());



	Mmap.put("getdisciplinetypeentry", mcommon.getdisciplinetypeentry());



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



	// Mmap.put("getStreamList", mcommon.getStreamList());



	Mmap.put("getCourseName", mcommon.getCourseName());



	Mmap.put("getFdService", mcommon.getFdService());



	Mmap.put("getCourseTypeList", mcommon.getCourseTypeList());



	Mmap.put("getExamList", mcommon.getExamList());



	Mmap.put("getSpecializationList", mcommon.getSpecializationList());



	Mmap.put("getDclredRcvrdList", mcommon.getDclredRcvrdList());



	Mmap.put("getCauseOfDeserter", mcommon.getCauseOfDeserter());



	Mmap.put("getARM_TYPE", mcommon.getARM_TYPE());



	Mmap.put("getExservicemenList", mcommon.getExservicemenList());



	Mmap.put("getMissingList", mcommon.getMissingList());



	Mmap.put("getCausesOfCasuality", mcommon.getCausesOfCasuality());
	Mmap.put("getArmyCourseInstitute", mcommon.getArmyCourseInstitute("1"));  


	// -----smit--------//



	Mmap.put("getDisc_Trialed", mcommon.getDisc_Trialed());



	Mmap.put("getArmyAct_Sec", mcommon.getArmyAct_Sec());



	Mmap.put("getSub_Clause", mcommon.getSub_Clause());
	
//kajal
	//Mmap.put("getBattleDescList", mcommon.getBattleDescList());
	

//	Mmap.put("msg", msg);



	return new ModelAndView("Approve_UpadteOfficer_xml_popup_tiles");





}

}
