package com.controller.psg.update_offr_data;

import java.math.BigInteger;
import java.text.DateFormat;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import java.util.List;

import java.util.Locale;

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

import com.controller.Dashboard.PsgDashboardController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;

import com.controller.psg.update_offr_data.Canteen_card_DetailsController;

import com.controller.psg.update_offr_data.Contact_Details_Controller;

import com.controller.psg.update_offr_data.Change_NameController;

import com.controller.psg.update_offr_data.Change_Of_Rank_controller;

import com.controller.psg.update_offr_data.Non_Effective_Controller;

import com.controller.psg.update_offr_data.Religion_UpdateController;

import com.controller.psg.update_offr_data.Update_off_dataController;

import com.controller.validation.ValidationController;

import com.dao.login.RoleBaseMenuDAO;

import com.dao.psg.Master.Psg_CommanDAO;

import com.dao.psg.update_census_data.Search_UpdateOffDataDao;

import com.models.psg.Transaction.TB_PSG_CANTEEN_CARD_DETAIL1;

import com.models.psg.Transaction.TB_CENSUS_ADDRESS;

import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;

import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;

import com.models.psg.Transaction.TB_CENSUS_FAMILY_SIBLINGS;

import com.models.psg.Transaction.TB_CENSUS_FOREIGN_COUNTRY;

import com.models.psg.Transaction.TB_CENSUS_IDENTITY_CARD;

import com.models.psg.Transaction.TB_CENSUS_LANGUAGE;

import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;

import com.models.psg.Transaction.TB_CENSUS_NOK;

import com.models.psg.Transaction.TB_CENSUS_QUALIFICATION;

import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;

import com.models.psg.Transaction.TB_MEDICAL_CATEGORY_HISTORY;

import com.models.psg.Transaction.TB_PSG_CENSUS_ALLERGICTOMEDICINE;

import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;

import com.models.psg.update_census_data.TB_REEMPLOYMENT;

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

import com.persistance.util.HibernateUtil;

@Controller

@RequestMapping(value = { "admin", "/", "user" })

public class Search_UpdateOfficerDataController {

	ValidationController valid = new ValidationController();

	@Autowired

	Search_UpdateOffDataDao UOD;

	@Autowired

	Psg_CommanDAO psg_com;

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	Psg_CommonController mcommon = new Psg_CommonController();

	Allergy_Controller al = new Allergy_Controller();
	PsgDashboardController das = new PsgDashboardController();

	@Autowired

	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/Search_UpdateOfficerDataUrl", method = RequestMethod.GET)

	public ModelAndView Search_UpdateOfficerDataUrl(ModelMap Mmap, HttpSession session,

			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleType = session.getAttribute("roleType").toString();

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", roleid);

		if (val == false) {

			return new ModelAndView("AccessTiles");

		}

		if (request.getHeader("Referer") == null) {

			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");

		}

	

		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String roleAccess = session.getAttribute("roleAccess").toString();

		if (roleAccess.equals("Unit")) {

			Mmap.put("sus_no", roleSusNo);

			Mmap.put("unit_name",

					m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session)

							.get(0));

		}

		Mmap.put("msg", msg);

		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());

		// ArrayList<ArrayList<String>> list =

		// UOD.Search_UpdateOffData(personnel_no,status,rank,roleSusNo,roleType,unit_name,unit_sus_no

		// );
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		Mmap.put("list", UOD.Search_UpdateOffData("", "0", "", "", "", "","",roleSusNo, roleType, "0"));
		
		return new ModelAndView("SearchUpdate_OFF_DataTiles");

	}

	@RequestMapping(value = "/admin/GetSearch_UpdateOfficerData", method = RequestMethod.POST)

	public ModelAndView GetSearch_UpdateOfficerData(ModelMap Mmap, HttpSession session,HttpServletRequest request,

			@RequestParam(value = "msg", required = false) String msg,

			@RequestParam(value = "personnel_no1", required = false) String personnel_no,

			@RequestParam(value = "status1", required = false) String status,

			@RequestParam(value = "rank1", required = false) String rank,

			@RequestParam(value = "unit_name1", required = false) String unit_name,

			@RequestParam(value = "icstatus", required = false) String icstatus,

			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,@RequestParam(value = "cr_by1", required = false) String cr_by,
		    @RequestParam(value = "cr_date1", required = false) String cr_date) {
		
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", roleid);

		if (val == false) {

			return new ModelAndView("AccessTiles");

		}

		if (request.getHeader("Referer") == null) {

			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");

		}
		

		unit_name = unit_name.replace("&#40;", "(");

		unit_name = unit_name.replace("&#41;", ")");

		String roleType = session.getAttribute("roleType").toString();

		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String roleAccess = session.getAttribute("roleAccess").toString();


		if (unit_sus_no != "") {

			if (!valid.SusNoLength(unit_sus_no)) {

				Mmap.put("msg", valid.SusNoMSG);

				return new ModelAndView("redirect:Search_UpdateOfficerDataUrl");

			}

			if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {

				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");

				return new ModelAndView("redirect:Search_UpdateOfficerDataUrl");

			}

		}

		if (unit_name != "") {

			if (!valid.isUnit(unit_name)) {

				Mmap.put("msg", " Unit Name " + valid.isUnitMSG);

				return new ModelAndView("redirect:Search_UpdateOfficerDataUrl");

			}

//			if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//
//				Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//
//				return new ModelAndView("redirect:Search_UpdateOfficerDataUrl");
//
//			}

		}

		if (personnel_no != "") {

			if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {

				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");

				return new ModelAndView("redirect:Search_UpdateOfficerDataUrl");

			}

			if (personnel_no.length() < 7 || personnel_no.length() > 9) {

				Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");

				return new ModelAndView("redirect:Search_UpdateOfficerDataUrl");

			}

		}

		if (roleAccess.equals("Unit")) {

			Mmap.put("sus_no", roleSusNo);

			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));

		}

		ArrayList<ArrayList<String>> list = UOD.AppSearch_UpdateOffData(personnel_no, status, rank, unit_sus_no,

				unit_name ,cr_by,cr_date, roleSusNo, roleType, icstatus);

		Mmap.put("list", list);

		Mmap.put("size", list.size());

		Mmap.put("personnel_no1", personnel_no);

		Mmap.put("rank1", rank);

		Mmap.put("status1", status);

		Mmap.put("unit_name1", unit_name);

		Mmap.put("icstatus", icstatus);

		Mmap.put("unit_sus_no1", unit_sus_no);

		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());

		Mmap.put("cr_date1", cr_date);
		Mmap.put("cr_by1", cr_by);
	
		
	

		Mmap.put("list", list);

		return new ModelAndView("SearchUpdate_OFF_DataTiles", "Search_Update_Off_Data_CMD",

				new TB_CENSUS_DETAIL_Parent());

	}

	/*--------------------- For Approval Update Officer ----------------------------------*/

	Change_Of_Rank_controller ChngRnk = new Change_Of_Rank_controller();

	Change_NameController ChngName = new Change_NameController();
	
	TA_UpdateController ChngTA = new TA_UpdateController();

	Non_Effective_Controller NonEffec = new Non_Effective_Controller();

	Update_off_dataController Up = new Update_off_dataController();

	Contact_Details_Controller CDA = new Contact_Details_Controller();

	Religion_UpdateController Rel = new Religion_UpdateController();

	Change_Of_Commision_Controller ChngComm = new Change_Of_Commision_Controller();

	Extension_Of_Commision_Controller ExteComm = new Extension_Of_Commision_Controller();

	Inter_Arm_Tranfer_Controller IntArmTran = new Inter_Arm_Tranfer_Controller();

	AwardsNmedal_Controller AwrMed = new AwardsNmedal_Controller();

	Update_Language_Controller lan = new Update_Language_Controller();

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

	DisciplineController Dis = new DisciplineController();

	Update_Qualification_Controller UP_QUA = new Update_Qualification_Controller();

	Allergy_Controller aler = new Allergy_Controller();

	Census_madicalCatController c_mad = new Census_madicalCatController();

	DeserterController deserter = new DeserterController();

	Canteen_card_DetailsController CSD = new Canteen_card_DetailsController();// keval

	@RequestMapping(value = "/Approve_UpadteOfficerDataUrl", method = RequestMethod.POST)

	public ModelAndView Approve_UpadteOfficerDataUrl(@ModelAttribute("id2") String updateid,

			@ModelAttribute("personnel_no2") String personnel_no2,

			@ModelAttribute("comm_id1") String comm_id, @ModelAttribute("event1") String event,

			@RequestParam(value = "msg", required = false) String msg,

			Authentication authentication, HttpServletRequest request, ModelMap Mmap, HttpSession session)

			throws ParseException {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", roleid);

		if (val == false) {

			return new ModelAndView("AccessTiles");

		}

		if (request.getHeader("Referer") == null) {

			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");

		}

		List<TB_CHANGE_OF_RANK> ChangeOfRank = ChngRnk.getChangeOfRankData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CHANGE_NAME> ChangeOfName = ChngName.getChangeOfNameData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_NON_EFFECTIVE> NonEffective = NonEffec.getNon_effective1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CENSUS_CHILDREN> ChildDetails = Up.getm_children_detailsData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CENSUS_CDA_ACCOUNT_NO> CDAaccount = CDA.cda_acnt_no_GetData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CHANGE_RELIGION> religion = Rel.religion_GetData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfComm = ChngComm.getcocData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_PSG_EXTENSION_OF_COMISSION> ExtenComm = ExteComm.geteocData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_INTER_ARM_TRANSFER> InterArmTrans = IntArmTran.getInterArm1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CENSUS_AWARDSNMEDAL> AwardsMedal = AwrMed.getawardsNmedalData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CENSUS_LANGUAGE> Language = lan.update_census_getlanguagedetailsData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CENSUS_QUALIFICATION> Qualification = UP_QUA

				.update_census_getQualificationData1(Integer.parseInt(updateid), new BigInteger(comm_id));

		List<TB_CENSUS_SECONDMENT> Secondment = Sec.get_Secondment1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CHANGE_OF_APPOINTMENT> ChngAppointment = Ap.get_Appointment1(Integer.parseInt(updateid),

				new BigInteger(comm_id));
		
		List<TB_CHANGE_TA_STATUS> ChangeOfTA = ChngTA.ta_status_GetData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CENSUS_FOREIGN_COUNTRY> ForeignCountry = ForCon.getUPForeginCountryData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CENSUS_FAMILY_MRG> Marital = Mrg.update_getfamily_marriageData1(Integer.parseInt(updateid),

				new BigInteger(comm_id), Integer.parseInt(event));

		List<TB_CENSUS_SPOUSE_QUALIFICATION> Spouse_Quali = Mrg.getUpdatedSpouseQualificationData(new BigInteger(comm_id), 0);

		List<TB_CENSUS_FIRING_STANDARD> FiringStan = FirSta.getfire_detailsData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CENSUS_NOK> NOK = NOKc.nok_details_GetData1(Integer.parseInt(updateid), new BigInteger(comm_id));

		List<TB_CENSUS_BPET> BEPT = BPET.getbpet_Data1(Integer.parseInt(updateid), new BigInteger(comm_id));

		List<TB_CENSUS_IDENTITY_CARD> IdentityCard = IdeCard.Ide_card_getData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CENSUS_ADDRESS> ChangeAdd = Chngadd.address_details_GetData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CENSUS_BATT_PHY_CASUALITY> btel_cas = Btel

				.Battle_and_Physical_Casuality_GetData1(Integer.parseInt(updateid), new BigInteger(comm_id));

		List<TB_CENSUS_DISCIPLINE> Discipline = Dis.get_Discipline1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CENSUS_PROMOTIONAL_EXAM> Promotional_Exam = UP_QUA

				.update_census_promo_examData1(Integer.parseInt(updateid), new BigInteger(comm_id));

		List<TB_CENSUS_ARMY_COURSE> Army_Course = UP_QUA.update_census_army_courseData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> Allergy = aler.update_census_allergicData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_CENSUS_MEDICAL_CATEGORY> MedDetails = c_mad.getUpdatedmadicalData(Integer.parseInt(updateid),

				new BigInteger(comm_id));

		List<TB_DESERTER> deserter1 = deserter.deserter_GetData1(Integer.parseInt(updateid), new BigInteger(comm_id));

		List<TB_PSG_CANTEEN_CARD_DETAIL1> CSDDetails = CSD.CSD_GetData1(Integer.parseInt(updateid),

				new BigInteger(comm_id));// keval

		Mmap.put("msg", msg);

		Mmap.put("census_id", updateid);

		Mmap.put("personnel_no2", personnel_no2);

		Mmap.put("comm_id", comm_id);

		Mmap.put("event", event);

		Mmap.put("ChangeOfRank", ChangeOfRank.size());

		Mmap.put("ChangeOfName", ChangeOfName.size());

		Mmap.put("NonEffective", NonEffective.size());

		Mmap.put("ChildDetails", ChildDetails.size());

		Mmap.put("CDAaccount", CDAaccount.size());

		Mmap.put("religion", religion.size());

		Mmap.put("ChangeOfComm", ChangeOfComm.size());

		Mmap.put("ExtensionComm", ExtenComm.size());

		Mmap.put("InterArmTransfer", InterArmTrans.size());

		Mmap.put("AwardsMedal", AwardsMedal.size());

		Mmap.put("Language", Language.size());

		Mmap.put("Qualification", Qualification.size());

		Mmap.put("Secondment", Secondment.size());

		Mmap.put("ChngAppointment", ChngAppointment.size());
		
		Mmap.put("ChangeOfTA", ChangeOfTA.size());

		Mmap.put("ForeignCountry", ForeignCountry.size());

		Mmap.put("FiringStan", FiringStan.size());

		Mmap.put("NOK", NOK.size());

		Mmap.put("BEPT", BEPT.size());

		Mmap.put("IdentityCard", IdentityCard.size());

		Mmap.put("ChangeAdd", ChangeAdd.size());

		Mmap.put("Marital", Marital.size());

		Mmap.put("Spouse_Quali", Spouse_Quali.size());

		

		Mmap.put("btel_cas_size", btel_cas.size());

		Mmap.put("btel_cas", btel_cas);

		Mmap.put("Discipline", Discipline.size());

		Mmap.put("Promotional_Exam", Promotional_Exam.size());

		Mmap.put("Army_Course", Army_Course.size());

		Mmap.put("Allergy", Allergy.size());

		Mmap.put("MedDetails", MedDetails.size());

		Mmap.put("deserter1", deserter1.size());

		Mmap.put("CSDDetails", CSDDetails.size());// keval

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

		// Mmap.put("getMedStateName", mcommon.getMedStateName("", session));

		// Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));

		// Mmap.put("getMedCityName", mcommon.getMedCityName("", session));

		// Mmap.put("getCountryList", mcommon.getCountryList());

		// Mmap.put("getMedStateName", mcommon.getMedStateName("", session));

		Mmap.put("getFamily_siblings", mcommon.getFamily_siblings());

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

		Mmap.put("getdisciplinetypeentry", mcommon.getdisciplinetypeentry());

		Mmap.put("getExservicemenList", mcommon.getExservicemenList());

		Mmap.put("getDclredRcvrdList", mcommon.getDclredRcvrdList());

		Mmap.put("getCauseOfDeserter", mcommon.getCauseOfDeserter());

		Mmap.put("getARM_TYPE", mcommon.getARM_TYPE());

		Mmap.put("getArmyCourseInstitute", mcommon.getArmyCourseInstitute("1"));

		Mmap.put("getMissingList", mcommon.getMissingList());

		Mmap.put("getCausesOfCasuality", mcommon.getCausesOfCasuality());

		// -----smit--------//

		Mmap.put("getDisc_Trialed", mcommon.getDisc_Trialed());

		Mmap.put("getArmyAct_Sec", mcommon.getArmyAct_Sec());

		Mmap.put("getSub_Clause", mcommon.getSub_Clause());
		
		Mmap.put("gettastatusList", mcommon.getstatusList());
		Mmap.put("getProfession", mcommon.getProfession());

		return new ModelAndView("App_Update_Officer_DataTiles");

	}

	@RequestMapping(value = "/Approve_officer_DataAction", method = RequestMethod.POST)

	public ModelAndView Approve_officer_DataAction(@ModelAttribute("Approve_officer_DataCMD") TB_CENSUS_DETAIL_Parent P,

			BindingResult result, @RequestParam(value = "msg", required = false) String msg,

			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {
		
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", roleid);

		if (val == false) {

			return new ModelAndView("AccessTiles");

		}

		if (request.getHeader("Referer") == null) {

			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");

		}

		String username = session.getAttribute("username").toString();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		List<TB_CHANGE_OF_RANK> ChangeOfRank = ChngRnk.getChangeOfRankData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_DISCIPLINE> Discipline = Dis.get_Discipline1(Integer.parseInt(request.getParameter("census_id")),

				new BigInteger(request.getParameter("comm_id")));

		List<TB_CHANGE_NAME> ChangeOfName = ChngName.getChangeOfNameData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_NON_EFFECTIVE> NonEffective = NonEffec.getNon_effective1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_CHILDREN> ChildDetail = Up.getm_children_detailsData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_CDA_ACCOUNT_NO> CDAaccount = CDA.cda_acnt_no_GetData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CHANGE_RELIGION> religion = Rel.religion_GetData1(Integer.parseInt(request.getParameter("census_id")),

				new BigInteger(request.getParameter("comm_id")));

		List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfComm = ChngComm.getcocData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_PSG_EXTENSION_OF_COMISSION> ExtensionComm = ExteComm.geteocData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_INTER_ARM_TRANSFER> InterArmTransfer = IntArmTran.getInterArm1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_AWARDSNMEDAL> AwardsMedals = AwrMed.getawardsNmedalData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_LANGUAGE> Language = lan.update_census_getlanguagedetailsData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_QUALIFICATION> Qualification = UP_QUA.update_census_getQualificationData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_SECONDMENT> Secondment = Sec.get_Secondment1(Integer.parseInt(request.getParameter("census_id")),

				new BigInteger(request.getParameter("comm_id")));

		List<TB_CHANGE_OF_APPOINTMENT> ChngOfAppointment = Ap.get_Appointment1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));
		
		List<TB_CHANGE_TA_STATUS> ChangeOfTA = ChngTA.ta_status_GetData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_FOREIGN_COUNTRY> ForeignCountry = ForCon.getUPForeginCountryData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_FIRING_STANDARD> FiringStan = FirSta.getfire_detailsData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_NOK> NOK = NOKc.nok_details_GetData1(Integer.parseInt(request.getParameter("census_id")),

				new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_BPET> BPET2 = BPET.getbpet_Data1(Integer.parseInt(request.getParameter("census_id")),

				new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_IDENTITY_CARD> IdentityCard = IdeCard.Ide_card_getData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_ADDRESS> ChangeAdd = Chngadd.address_details_GetData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_FAMILY_MRG> Marital = Mrg.update_getfamily_marriageData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")),

				Integer.parseInt(request.getParameter("event")));

		List<TB_CENSUS_BATT_PHY_CASUALITY> btel_cas = Btel.Battle_and_Physical_Casuality_GetData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_PROMOTIONAL_EXAM> prop_exam = UP_QUA.update_census_promo_examData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_ARMY_COURSE> army_course = UP_QUA.update_census_army_courseData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> alerg = al.update_census_allergicData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_MEDICAL_CATEGORY> MedDetails = c_mad.getUpdatedmadicalData(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_CENSUS_SPOUSE_QUALIFICATION> SpouseDeatils = Mrg.update_census_Spouse(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));

		List<TB_DESERTER> deserter1 = deserter.deserter_GetData1(Integer.parseInt(request.getParameter("census_id")),

				new BigInteger(request.getParameter("comm_id")));

		List<TB_PSG_CANTEEN_CARD_DETAIL1> CSDDetails = CSD.CSD_GetData1(

				Integer.parseInt(request.getParameter("census_id")), new BigInteger(request.getParameter("comm_id")));// keval

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		Date modified_date = new Date();

		try {

			P.setId(Integer.parseInt(request.getParameter("census_id")));

			P.setComm_id(new BigInteger(request.getParameter("comm_id")));

			P.setModified_date(modified_date);

			TB_TRANS_PROPOSED_COMM_LETTER Comm = new TB_TRANS_PROPOSED_COMM_LETTER();

			Comm.setId(new BigInteger(request.getParameter("comm_id")));

			if (CSDDetails.size() > 0) {// keval

				TB_PSG_CANTEEN_CARD_DETAIL1 ChangeCSD = new TB_PSG_CANTEEN_CARD_DETAIL1();

				ChangeCSD.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				ChangeCSD.setComm_id(new BigInteger(request.getParameter("comm_id")));

				ChangeCSD.setModified_date(modified_date);

				Mmap.put("msg", CSD.Update_CSD_details(ChangeCSD, username));

			}

			if (ChangeOfRank.size() > 0) {

				TB_CHANGE_OF_RANK ChangeRank = new TB_CHANGE_OF_RANK();

				ChangeRank.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				ChangeRank.setComm_id(new BigInteger(request.getParameter("comm_id")));

				ChangeRank.setModified_date(modified_date);

				Mmap.put("msg", ChngRnk.Update_Change_of_Rank(ChangeRank, username));

				Date dt_rank = null;

				String date_of_rank = request.getParameter("dt_rank");

				if (date_of_rank != "") {

					dt_rank = format.parse(date_of_rank);

				}

				// Comm.setRank(Integer.parseInt(request.getParameter("rank_v")));

				// Comm.setDate_of_rank(dt_rank);

				Comm.setRank(ChangeOfRank.get(0).getRank());


				Comm.setDate_of_rank(ChangeOfRank.get(0).getDate_of_rank());

				Comm.setModified_date(modified_date);

				ChngRnk.Update_Comm_Rank(Comm, username);

			}

			if (ChangeOfName.size() > 0) {

				TB_CHANGE_NAME ChangeName = new TB_CHANGE_NAME();

				ChangeName.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				ChangeName.setComm_id(new BigInteger(request.getParameter("comm_id")));

				ChangeName.setModified_date(modified_date);

				Mmap.put("msg", ChngName.Update_Change_of_Name(ChangeName, username));

				// Comm.setName(request.getParameter("name_v"));

				Comm.setName(ChangeOfName.get(0).getName());

				ChngName.Update_Comm_Name(Comm, username);

			}
			
			if (ChangeOfTA.size() > 0) {

				TB_CHANGE_TA_STATUS ChangeTA = new TB_CHANGE_TA_STATUS();

				ChangeTA.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				ChangeTA.setComm_id(new BigInteger(request.getParameter("comm_id")));

				ChangeTA.setModified_date(modified_date);

				Mmap.put("msg", ChngTA.Update_TA_Status(ChangeTA, username));

				// Comm.setName(request.getParameter("name_v"));

//				Comm.setTa_status(ChangeOfTA.get(0).getTa_status());
//
//				ChngTA.Update_TA_Status(Comm, username);

			}

			if (ChngOfAppointment.size() > 0) {

				TB_CHANGE_OF_APPOINTMENT ChngAppo = new TB_CHANGE_OF_APPOINTMENT();

				ChngAppo.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				ChngAppo.setComm_id(new BigInteger(request.getParameter("comm_id")));

				ChngAppo.setModified_date(modified_date);

				Mmap.put("msg", Ap.Update_Change_of_Appointment(ChngAppo, username));

			}

			if (IdentityCard.size() > 0) {

				TB_CENSUS_IDENTITY_CARD IdeCardApp = new TB_CENSUS_IDENTITY_CARD();

				IdeCardApp.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				IdeCardApp.setComm_id(new BigInteger(request.getParameter("comm_id")));

				IdeCardApp.setModified_date(modified_date);

				Mmap.put("msg", IdeCard.Update_Ide_cardData(IdeCardApp, username));

			}

			if (religion.size() > 0) {

				TB_CHANGE_RELIGION reli = new TB_CHANGE_RELIGION();

				reli.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				reli.setComm_id(new BigInteger(request.getParameter("comm_id")));

				reli.setModified_date(modified_date);

				Mmap.put("msg", Rel.Update_Religion(reli, username));

				if (request.getParameter("relign") != "") {

					P.setReligion(Integer.parseInt(request.getParameter("relign")));

					Rel.Update_Census_Religion(P, username);

				}

			}

			if (Marital.size() > 0) {

    	        TB_CENSUS_FAMILY_MRG MrgS = new TB_CENSUS_FAMILY_MRG();

    	        MrgS.setCen_id(Integer.parseInt(request.getParameter("census_id")));

    	        MrgS.setComm_id(new BigInteger(request.getParameter("comm_id")));

    	        MrgS.setSeparated_to_dt(Marital.get(0).getSeparated_to_dt());

    	        MrgS.setModified_date(modified_date);

    	        Mmap.put("msg",Mrg.Update_Marital_Status(MrgS, username));

				if(request.getParameter("m_status") != "") {

					P.setMarital_status(Integer.parseInt(request.getParameter("m_status")));

					Mrg.Update_Census_Marital(P, username);

				}

					

		        }

			if(SpouseDeatils.size() > 0) {

				TB_CENSUS_SPOUSE_QUALIFICATION sp = new TB_CENSUS_SPOUSE_QUALIFICATION();

				sp.setCen_id(Integer.parseInt(request.getParameter("census_id")));

				sp.setComm_id(new BigInteger(request.getParameter("comm_id")));

				sp.setModified_date(modified_date);

				 Mmap.put("msg",Mrg.Update_SposeQuali_Details(sp, username));

			}

			

			if (ChildDetail.size() > 0) {

				TB_CENSUS_CHILDREN chil = new TB_CENSUS_CHILDREN();

				chil.setCen_id(Integer.parseInt(request.getParameter("census_id")));

				chil.setComm_id(new BigInteger(request.getParameter("comm_id")));

				chil.setModified_date(modified_date);

				Mmap.put("msg", Up.Update_Children_Details(chil, username));

			}

			if (NOK.size() > 0) {

				TB_CENSUS_NOK NOKco = new TB_CENSUS_NOK();

				NOKco.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				NOKco.setComm_id(new BigInteger(request.getParameter("comm_id")));

				NOKco.setModified_date(modified_date);

				Mmap.put("msg", NOKc.Update_NOK(NOKco, username));

			}

			if (ChangeAdd.size() > 0) {

				TB_CENSUS_ADDRESS Chngaddr = new TB_CENSUS_ADDRESS();

				Chngaddr.setCen_id(Integer.parseInt(request.getParameter("census_id")));

				Chngaddr.setComm_id(new BigInteger(request.getParameter("comm_id")));

				Chngaddr.setModified_date(modified_date);

				Mmap.put("msg", Chngadd.Update_Change_Add(Chngaddr, username));

			}

			if (CDAaccount.size() > 0) {

				TB_CENSUS_CDA_ACCOUNT_NO CDAacc = new TB_CENSUS_CDA_ACCOUNT_NO();

				CDAacc.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				CDAacc.setComm_id(new BigInteger(request.getParameter("comm_id")));

				CDAacc.setModified_date(modified_date);

				Mmap.put("msg", CDA.Update_CDA_Account_No(CDAacc, username));

			}

			if (Language.size() > 0) {

				TB_CENSUS_LANGUAGE Lan = new TB_CENSUS_LANGUAGE();

				Lan.setCen_id(Integer.parseInt(request.getParameter("census_id")));

				Lan.setComm_id(new BigInteger(request.getParameter("comm_id")));

				Lan.setModify_on(modified_date);

				Mmap.put("msg", lan.Update_Language(Lan, username));

			}

			if (BPET2.size() > 0) {

				TB_CENSUS_BPET Bept1 = new TB_CENSUS_BPET();

				Bept1.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				Bept1.setComm_id(new BigInteger(request.getParameter("comm_id")));

				Bept1.setModified_date(modified_date);

				Mmap.put("msg", BPET.Update_BEPT(Bept1, username));

			}

			if (FiringStan.size() > 0) {

				TB_CENSUS_FIRING_STANDARD FirStad = new TB_CENSUS_FIRING_STANDARD();

				FirStad.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				FirStad.setComm_id(new BigInteger(request.getParameter("comm_id")));

				FirStad.setModified_date(modified_date);

				Mmap.put("msg", FirSta.Update_FiringStandard(FirStad, username));

			}

			if (ForeignCountry.size() > 0) {

				TB_CENSUS_FOREIGN_COUNTRY ForeCoun = new TB_CENSUS_FOREIGN_COUNTRY();

				ForeCoun.setCen_id(Integer.parseInt(request.getParameter("census_id")));

				ForeCoun.setComm_id(new BigInteger(request.getParameter("comm_id")));

				ForeCoun.setModified_date(modified_date);

				Mmap.put("msg", ForCon.Update_ForeignContry(ForeCoun, username));

			}

			if (AwardsMedals.size() > 0) {

				TB_CENSUS_AWARDSNMEDAL AwardMedal = new TB_CENSUS_AWARDSNMEDAL();

				AwardMedal.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				AwardMedal.setComm_id(new BigInteger(request.getParameter("comm_id")));

				AwardMedal.setModify_on(modified_date);

				Mmap.put("msg", AwrMed.Update_Awards_Medals(AwardMedal, username));

			}

			if (btel_cas.size() > 0) {

				TB_CENSUS_BATT_PHY_CASUALITY Bat = new TB_CENSUS_BATT_PHY_CASUALITY();

				Bat.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				Bat.setComm_id(new BigInteger(request.getParameter("comm_id")));

				Bat.setModify_on(modified_date);

				Mmap.put("msg", Btel.Update_Btle_Phy_Casuality(Bat, username));

			}

			if (InterArmTransfer.size() > 0) {

				TB_INTER_ARM_TRANSFER InArmTran = new TB_INTER_ARM_TRANSFER();

				InArmTran.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				InArmTran.setComm_id(new BigInteger(request.getParameter("comm_id")));

				InArmTran.setModified_date(modified_date);

				Mmap.put("msg", IntArmTran.Update_InterArmTransfer(InArmTran, username));

				if (request.getParameter("p_arm") != "" && request.getParameter("p_arm") != "0"

						&& request.getParameter("regn") != "") {

					// Comm.setParent_arm(request.getParameter("p_arm"));

					// Comm.setRegiment(request.getParameter("regn"));

					Comm.setParent_arm(InterArmTransfer.get(0).getParent_arm_service());

					Comm.setRegiment(InterArmTransfer.get(0).getRegt());

					IntArmTran.Update_Comm_InterArm(Comm, username);

				}

			}

			if (ChangeOfComm.size() > 0) {

				TB_PSG_CHANGE_OF_COMISSION Chngcom = new TB_PSG_CHANGE_OF_COMISSION();

				Chngcom.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				Chngcom.setComm_id(new BigInteger(request.getParameter("comm_id")));

				Chngcom.setModified_date(modified_date);
				
				/*List<TB_PSG_CHANGE_OF_COMISSION> ChangeCom = ChngComm.getData(

						Integer.parseInt(request.getParameter("comm_id")));*/
				
				Chngcom.setOld_personal_no(request.getParameter("per_no"));
				
				Chngcom.setPrevious_commission(Integer.parseInt(request.getParameter("type_of_comm_granted")));
				

				Mmap.put("msg", ChngComm.Update_ChangeOfCommision(Chngcom, username));

				Date date_of_seniority_dt = null;

				String date_of_seniority = request.getParameter("dt_seniority");

				if (date_of_seniority != "") {

					date_of_seniority_dt = format.parse(date_of_seniority);

				}

				Date date_of_permanent_commission_dt = null;

				String date_of_permanent_commission = request.getParameter("dt_comm");

				if (date_of_permanent_commission != "") {

					date_of_permanent_commission_dt = format.parse(date_of_permanent_commission);

				}

				if (request.getParameter("type_of_comm_granted") != "" && request.getParameter("per_no") != ""

						&& date_of_seniority_dt != null && date_of_permanent_commission_dt != null) {

					// Comm.setDate_of_seniority(date_of_seniority_dt);

					// Comm.setDate_of_commission(date_of_permanent_commission_dt);

					// Comm.setType_of_comm_granted(Integer.parseInt(request.getParameter("type_of_comm_granted")));

					// Comm.setPersonnel_no(request.getParameter("per_no"));

					Comm.setDate_of_seniority(ChangeOfComm.get(0).getDate_of_seniority());

					/*

					 * Comm.setDate_of_commission(ChangeOfComm.get(0).

					 * getDate_of_permanent_commission());

					 */

					Comm.setType_of_comm_granted(ChangeOfComm.get(0).getType_of_commission_granted());

					Comm.setPersonnel_no(ChangeOfComm.get(0).getNew_personal_no());

					ChngComm.Update_Comm_Details(Comm, username);

				}

			}

			if (ExtensionComm.size() > 0) {

				TB_PSG_EXTENSION_OF_COMISSION ExtComm = new TB_PSG_EXTENSION_OF_COMISSION();

				ExtComm.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				ExtComm.setComm_id(new BigInteger(request.getParameter("comm_id")));

				ExtComm.setModified_date(modified_date);

				Mmap.put("msg", ExteComm.Update_ExtensionCommision(ExtComm, username));

			}

			if (Secondment.size() > 0) {

				TB_CENSUS_SECONDMENT Seco = new TB_CENSUS_SECONDMENT();

				Seco.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				Seco.setComm_id(new BigInteger(request.getParameter("comm_id")));

				Seco.setModified_date(modified_date);

				Mmap.put("msg", Sec.Update_Secondment(Seco, username));

			}

			if (NonEffective.size() > 0) {

				TB_NON_EFFECTIVE Non_effc = new TB_NON_EFFECTIVE();

				Non_effc.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				Non_effc.setComm_id(new BigInteger(request.getParameter("comm_id")));

				Non_effc.setModified_date(modified_date);

				Mmap.put("msg", NonEffec.Update_Non_Effective(Non_effc, username));

				Mmap.put("msg", NonEffec.Update_Officer_Commisning_Data(Comm, username));

			}

			if (Qualification.size() > 0) {

				TB_CENSUS_QUALIFICATION Qua = new TB_CENSUS_QUALIFICATION();

				Qua.setCen_id(Integer.parseInt(request.getParameter("census_id")));

				Qua.setComm_id(new BigInteger(request.getParameter("comm_id")));

				Qua.setModify_on(modified_date);

				Mmap.put("msg", UP_QUA.Update_Qualification(Qua, username));

			}

			if (prop_exam.size() > 0) {

				TB_CENSUS_PROMOTIONAL_EXAM prop = new TB_CENSUS_PROMOTIONAL_EXAM();

				prop.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				prop.setComm_id(new BigInteger(request.getParameter("comm_id")));

				prop.setModify_on(modified_date);

				Mmap.put("msg", UP_QUA.Update_Prop_Exam(prop, username));

			}

			if (army_course.size() > 0) {

				TB_CENSUS_ARMY_COURSE army = new TB_CENSUS_ARMY_COURSE();

				army.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				army.setComm_id(new BigInteger(request.getParameter("comm_id")));

				army.setModify_on(modified_date);

				Mmap.put("msg", UP_QUA.Update_Army_Course(army, username));

			}

			if (alerg.size() > 0) {

				TB_PSG_CENSUS_ALLERGICTOMEDICINE ale = new TB_PSG_CENSUS_ALLERGICTOMEDICINE();

				ale.setCen_id(Integer.parseInt(request.getParameter("census_id")));

				ale.setComm_id(new BigInteger(request.getParameter("comm_id")));

				ale.setModified_date(modified_date);

				Mmap.put("msg", al.Update_Alergy(ale, username));

			}

			if (MedDetails.size() > 0) {

				TB_CENSUS_MEDICAL_CATEGORY med = new TB_CENSUS_MEDICAL_CATEGORY();

				med.setCen_id(Integer.parseInt(request.getParameter("census_id")));

				med.setComm_id(new BigInteger(request.getParameter("comm_id")));

				med.setModify_on(modified_date);

				Mmap.put("msg", c_mad.Update_medicalCategory(med, username));

				String sShape = "S ";

				String hShape = "H ";

				String aShape = "A ";

				String pShape = "P ";

				String eShape = "E ";

				String cCope = "C ";

				String oCope = "O ";

				String pCope = "P ";

				String eCope = "E ";

				int lmc = 0;

				Date med_auth_date = MedDetails.get(0).getDate_of_authority();

				for (int i = 0; i < MedDetails.size(); i++) {

					int newLmc = MedDetails.get(i).getShape_status();

					if (newLmc > lmc) {

						lmc = newLmc;

					}

					String shape = MedDetails.get(i).getShape();

					if (shape.equals("S")) {

						if (!sShape.equals("S "))

							sShape += ",";

						sShape += MedDetails.get(i).getShape_value();

					} else if (shape.equals("H")) {

						if (!hShape.equals("H "))

							hShape += ",";

						hShape += MedDetails.get(i).getShape_value();

					} else if (shape.equals("A")) {

						if (!aShape.equals("A "))

							aShape += ",";

						aShape += MedDetails.get(i).getShape_value();

					} else if (shape.equals("P")) {

						if (!pShape.equals("P "))

							pShape += ",";

						pShape += MedDetails.get(i).getShape_value();

					} else if (shape.equals("E")) {

						if (!eShape.equals("E "))

							eShape += ",";

						eShape += MedDetails.get(i).getShape_value();

					} else if (shape.equals("C_C")) {

						if (!cCope.equals("C "))

							cCope += ",";

						cCope += MedDetails.get(i).getShape_value();

					} else if (shape.equals("C_O")) {

						if (!oCope.equals("O "))

							oCope += ",";

						oCope += MedDetails.get(i).getShape_value();

					} else if (shape.equals("C_P")) {

						if (!pCope.equals("P "))

							pCope += ",";

						pCope += MedDetails.get(i).getShape_value();

					} else if (shape.equals("C_E")) {

						if (!eCope.equals("E "))

							eCope += ",";

						eCope += MedDetails.get(i).getShape_value();

					}

				}

				String Fshape = sShape + ";" + hShape + ";" + aShape + ";" + pShape + ";" + eShape;

				String Fcope = cCope + ";" + oCope + ";" + pCope + ";" + eCope;

				TB_MEDICAL_CATEGORY_HISTORY Mobj = new TB_MEDICAL_CATEGORY_HISTORY();

				Mobj.setComm_id(new BigInteger(request.getParameter("comm_id")));

				Mobj.setShape(Fshape);

				Mobj.setCope(Fcope);

				Mobj.setStatus(1);

				Mobj.setDate_of_authority(med_auth_date);

				if (lmc == 1) {

					Mobj.setMed_classification_lmc("FIT");

				} else if (lmc == 2) {

					Mobj.setMed_classification_lmc("PERMANENT");

				} else if (lmc == 3) {

					Mobj.setMed_classification_lmc("TEMPORARY");

				}

				Mmap.put("msg", c_mad.save_MedicalHistory(Mobj));

			}

			if (Discipline.size() > 0) {

				TB_CENSUS_DISCIPLINE dis = new TB_CENSUS_DISCIPLINE();

				dis.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				dis.setComm_id(new BigInteger(request.getParameter("comm_id")));

				dis.setModified_date(modified_date);

				Mmap.put("msg", Dis.Update_Discipline(dis, username));

			}

			if (deserter1.size() > 0) {

				TB_DESERTER De = new TB_DESERTER();

				De.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				De.setComm_id(new BigInteger(request.getParameter("comm_id")));

				De.setApproved_date(modified_date);

				if (deserter1.get(0).getDt_recovered() == null || deserter1.get(0).getDt_recovered().equals("null")

						|| deserter1.get(0).getDt_recovered().equals("")) {


					deserter.Update_Comm_deserterStatusForOut(Comm, username);

				} else {


					deserter.Update_Comm_deserterStatusForIn(Comm, username);

				}

				Mmap.put("msg", deserter.Update_Deserter(De, username));

			}

			if (check_Update_Officer_DataStatus(Integer.parseInt(request.getParameter("census_id")),

					new BigInteger(request.getParameter("comm_id")),

					Integer.parseInt(request.getParameter("event")))) {

				Mmap.put("msg", Update_Officer_Data(P, username, 3));

			}

			else {

				Mmap.put("msg", Update_Officer_Data(P, username, 1));

			}

			tx.commit();

		} catch (RuntimeException e) {

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

		return new ModelAndView("redirect:Search_UpdateOfficerDataUrl");

	}

/*	public String Update_Officer_Data(TB_CENSUS_DETAIL_Parent obj, String username, int update_offr_status) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"

					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status != 3 ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)

					.setTimestamp("modified_date", obj.getModified_date())

					.setInteger("update_ofr_status", update_offr_status).setInteger("census_id", obj.getId())

					.setBigInteger("comm_id", obj.getComm_id());

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
*/
	
	////change by yogesh
	
	public String Update_Officer_Data(TB_CENSUS_DETAIL_Parent obj, String username, int update_offr_status) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {
			  String hql_xml = "update XML_FILE_UPLOAD set status=:status , modified_by=:modified_by,modified_date=:modified_date"

					+ " where  comm_id=:comm_id and status = '0' ";

			Query query_xml = sessionHQL.createQuery(hql_xml)
					.setInteger("status", update_offr_status).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date())
					.setBigInteger("comm_id", obj.getComm_id());
			msg = query_xml.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			
			String hql = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"

					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status != 3 ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)

					.setTimestamp("modified_date", obj.getModified_date())

					.setInteger("update_ofr_status", update_offr_status).setInteger("census_id", obj.getId())

					.setBigInteger("comm_id", obj.getComm_id());

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
	
	
	public Boolean check_Update_Officer_DataStatus(int census_id, BigInteger  comm_id, int event) {

		List<TB_CHANGE_OF_RANK> ChangeOfRank = ChngRnk.getChangeOfRankData2(census_id, comm_id);

		if (ChangeOfRank.size() > 0) {

			return true;

		}

		List<TB_CHANGE_NAME> ChangeOfName = ChngName.getChangeOfNameData2(census_id, comm_id);

		if (ChangeOfName.size() > 0) {

			return true;

		}

		List<TB_CHANGE_OF_APPOINTMENT> ChngAppointment = Ap.get_Appointment2(census_id, comm_id);

		if (ChngAppointment.size() > 0) {

			return true;

		}

		List<TB_CENSUS_IDENTITY_CARD> IdentityCard = IdeCard.Ide_card_getData2(census_id, comm_id);

		if (IdentityCard.size() > 0) {

			return true;

		}
		
		List<TB_CHANGE_TA_STATUS> ChangeOfTA = ChngTA.ta_status_GetData2(census_id, comm_id);

		if (ChangeOfTA.size() > 0) {

			return true;

		}

		List<TB_CHANGE_RELIGION> religion = Rel.religion_GetData2(census_id, comm_id);

		if (religion.size() > 0) {

			return true;

		}

		List<TB_CENSUS_FAMILY_MRG> Marital = Mrg.update_getfamily_marriageData2(census_id, comm_id, event);

		if (Marital.size() > 0) {

			return true;

		}

		

		List<TB_CENSUS_SPOUSE_QUALIFICATION> Spouse_Quali = Mrg.getUpdatedSpouseQualificationData(comm_id, 3);

		

		if (Spouse_Quali.size() > 0) {

			return true;

		}

		

		List<TB_CENSUS_CHILDREN> ChildDetails = Up.getm_children_detailsData2(census_id, comm_id);

		if (ChildDetails.size() > 0) {

			return true;

		}

		List<TB_CENSUS_NOK> NOK = NOKc.nok_details_GetData2(census_id, comm_id);

		if (NOK.size() > 0) {

			return true;

		}

		List<TB_CENSUS_ADDRESS> ChangeAdd = Chngadd.address_details_GetData2(census_id, comm_id);

		if (ChangeAdd.size() > 0) {

			return true;

		}

		List<TB_CENSUS_CDA_ACCOUNT_NO> CDAaccount = CDA.cda_acnt_no_GetData2(census_id, comm_id);

		if (CDAaccount.size() > 0) {

			return true;

		}

		List<TB_CENSUS_LANGUAGE> Language = lan.update_census_getlanguagedetailsData2(census_id, comm_id);

		if (Language.size() > 0) {

			return true;

		}

		/////////////

		List<TB_CENSUS_QUALIFICATION> Qualification = UP_QUA.getChangeOfQualification2(census_id, comm_id);

		if (Qualification.size() > 0) {

			return true;

		}

		List<TB_CENSUS_PROMOTIONAL_EXAM> Promotional_Exam = UP_QUA.getChangeOfPromotionExam2(census_id, comm_id);

		if (Promotional_Exam.size() > 0) {

			return true;

		}

		// List<TB_CENSUS_ARMY_COURSE> Army_Course =

		// UP_QUA.update_census_army_courseData1(census_id,comm_id);

		List<TB_CENSUS_ARMY_COURSE> Army_Course = UP_QUA.getChangeOfArmyCourse2(census_id, comm_id);

		if (Army_Course.size() > 0) {

			return true;

		}

		List<TB_CENSUS_BATT_PHY_CASUALITY> btel_cas = Btel.getChangeBattleCasulity2(census_id, comm_id);

		if (btel_cas.size() > 0) {

			return true;

		}

		List<TB_NON_EFFECTIVE> NonEffective = NonEffec.getNonEffective2(census_id, comm_id);

		if (NonEffective.size() > 0) {

			return true;

		}

		List<TB_CENSUS_MEDICAL_CATEGORY> MedDetails = c_mad.getChangeMedical2(census_id, comm_id);

		if (MedDetails.size() > 0) {

			return true;

		}

		////////////

		List<TB_CENSUS_BPET> BEPT = BPET.getbpet_Data2(census_id, comm_id);

		if (BEPT.size() > 0) {

			return true;

		}

		List<TB_CENSUS_FIRING_STANDARD> FiringStan = FirSta.getfire_detailsData2(census_id, comm_id);

		if (FiringStan.size() > 0) {

			return true;

		}

		List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> ChangeOfAllergy = al.getChangeOfAllergy2(census_id, comm_id);

		if (ChangeOfAllergy.size() > 0) {

			return true;

		}

		List<TB_CENSUS_FOREIGN_COUNTRY> ForeignCountry = ForCon.getChangeOfForeignCountry2(census_id, comm_id);

		if (ForeignCountry.size() > 0) {

			return true;

		}

		List<TB_CENSUS_AWARDSNMEDAL> AwardsMedal = AwrMed.getChangeOfAward2(census_id, comm_id);

		if (AwardsMedal.size() > 0) {

			return true;

		}

		List<TB_CENSUS_DISCIPLINE> ChangeOfDiscipline = Dis.getChangeOfDiscipline2(census_id, comm_id);

		if (ChangeOfDiscipline.size() > 0) {

			return true;

		}

		List<TB_INTER_ARM_TRANSFER> InterArmTrans = IntArmTran.getChangeOfInterArm2(census_id, comm_id);

		if (InterArmTrans.size() > 0) {

			return true;

		}

		List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfComm = ChngComm.getChangeOfCoc2(census_id, comm_id);

		if (ChangeOfComm.size() > 0) {

			return true;

		}

		List<TB_PSG_EXTENSION_OF_COMISSION> ExtenComm = ExteComm.getChangeOfEoc2(census_id, comm_id);

		if (ExtenComm.size() > 0) {

			return true;

		}

		List<TB_CENSUS_SECONDMENT> ChangeOfSecondment = Sec.getChangeOfSecondment2(census_id, comm_id);

		if (ChangeOfSecondment.size() > 0) {

			return true;

		}

		List<TB_DESERTER> deserter1 = deserter.deserter_GetData2(census_id, comm_id);

		if (deserter1.size() > 0) {

			return true;

		}

		List<TB_PSG_CANTEEN_CARD_DETAIL1> csdde = CSD.getCSD_detailsData2(census_id, comm_id);

		if (csdde.size() > 0) {

			return true;

		}

		return false;

	}

	public Boolean check_Update_Officer_DataStatusForPending(int census_id, BigInteger comm_id, int event) {

		List<TB_CHANGE_OF_RANK> ChangeOfRank = ChngRnk.getChangeOfRankData1(census_id, comm_id);

		if (ChangeOfRank.size() > 0) {

			return true;

		}

		List<TB_CHANGE_NAME> ChangeOfName = ChngName.getChangeOfNameData1(census_id, comm_id);

		if (ChangeOfName.size() > 0) {

			return true;

		}

		List<TB_CHANGE_OF_APPOINTMENT> ChngAppointment = Ap.get_Appointment1(census_id, comm_id);

		if (ChngAppointment.size() > 0) {

			return true;

		}

		List<TB_CENSUS_IDENTITY_CARD> IdentityCard = IdeCard.Ide_card_getData1(census_id, comm_id);

		if (IdentityCard.size() > 0) {

			return true;

		}
		
		List<TB_CHANGE_TA_STATUS> ChangeOfTA = ChngTA.ta_status_GetData1(census_id, comm_id);

		if (ChangeOfTA.size() > 0) {

			return true;

		}

		List<TB_CHANGE_RELIGION> religion = Rel.religion_GetData1(census_id, comm_id);

		if (religion.size() > 0) {

			return true;

		}

		List<TB_CENSUS_FAMILY_MRG> Marital = Mrg.update_getfamily_marriageData1(census_id, comm_id, event);

		if (Marital.size() > 0) {

			return true;

		}

		

		List<TB_CENSUS_SPOUSE_QUALIFICATION> Spouse_Quali = Mrg.getUpdatedSpouseQualificationData(comm_id, 0);

		

		if (Spouse_Quali.size() > 0) {

			return true;

		}

		

		List<TB_CENSUS_CHILDREN> ChildDetails = Up.getm_children_detailsData1(census_id, comm_id);

		if (ChildDetails.size() > 0) {

			return true;

		}

		List<TB_CENSUS_NOK> NOK = NOKc.nok_details_GetData1(census_id, comm_id);

		if (NOK.size() > 0) {

			return true;

		}

		List<TB_CENSUS_ADDRESS> ChangeAdd = Chngadd.address_details_GetData1(census_id, comm_id);

		if (ChangeAdd.size() > 0) {

			return true;

		}

		List<TB_CENSUS_CDA_ACCOUNT_NO> CDAaccount = CDA.cda_acnt_no_GetData1(census_id, comm_id);

		if (CDAaccount.size() > 0) {

			return true;

		}

		List<TB_CENSUS_LANGUAGE> Language = lan.update_census_getlanguagedetailsData1(census_id, comm_id);

		if (Language.size() > 0) {

			return true;

		}

		/////////////

		List<TB_CENSUS_QUALIFICATION> Qualification = UP_QUA.update_census_getQualificationData1(census_id, comm_id);

		if (Qualification.size() > 0) {

			return true;

		}

		List<TB_CENSUS_PROMOTIONAL_EXAM> Promotional_Exam = UP_QUA.update_census_promo_examData1(census_id, comm_id);

		if (Promotional_Exam.size() > 0) {

			return true;

		}

		// List<TB_CENSUS_ARMY_COURSE> Army_Course =

		// UP_QUA.update_census_army_courseData1(census_id,comm_id);

		List<TB_CENSUS_ARMY_COURSE> Army_Course = UP_QUA.update_census_army_courseData1(census_id, comm_id);

		if (Army_Course.size() > 0) {

			return true;

		}

		List<TB_CENSUS_BATT_PHY_CASUALITY> btel_cas = Btel.Battle_and_Physical_Casuality_GetData1(census_id, comm_id);

		if (btel_cas.size() > 0) {

			return true;

		}

		List<TB_NON_EFFECTIVE> NonEffective = NonEffec.getNon_effective1(census_id, comm_id);

		if (NonEffective.size() > 0) {

			return true;

		}

		List<TB_CENSUS_MEDICAL_CATEGORY> MedDetails = c_mad.getUpdatedmadicalData(census_id, comm_id);

		if (MedDetails.size() > 0) {

			return true;

		}

////////////

		List<TB_CENSUS_BPET> BEPT = BPET.getbpet_Data1(census_id, comm_id);

		if (BEPT.size() > 0) {

			return true;

		}

		List<TB_CENSUS_FIRING_STANDARD> FiringStan = FirSta.getfire_detailsData1(census_id, comm_id);

		if (FiringStan.size() > 0) {

			return true;

		}

		List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> ChangeOfAllergy = al.update_census_allergicData1(census_id, comm_id);

		if (ChangeOfAllergy.size() > 0) {

			return true;

		}

		List<TB_CENSUS_FOREIGN_COUNTRY> ForeignCountry = ForCon.getUPForeginCountryData1(census_id, comm_id);

		if (ForeignCountry.size() > 0) {

			return true;

		}

		List<TB_CENSUS_AWARDSNMEDAL> AwardsMedal = AwrMed.getawardsNmedalData1(census_id, comm_id);

		if (AwardsMedal.size() > 0) {

			return true;

		}

		List<TB_CENSUS_DISCIPLINE> ChangeOfDiscipline = Dis.get_Discipline1(census_id, comm_id);

		if (ChangeOfDiscipline.size() > 0) {

			return true;

		}

		List<TB_INTER_ARM_TRANSFER> InterArmTrans = IntArmTran.getInterArm1(census_id, comm_id);

		if (InterArmTrans.size() > 0) {

			return true;

		}

		List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfComm = ChngComm.getcocData1(census_id, comm_id);

		if (ChangeOfComm.size() > 0) {

			return true;

		}

		List<TB_PSG_EXTENSION_OF_COMISSION> ExtenComm = ExteComm.geteocData1(census_id, comm_id);

		if (ExtenComm.size() > 0) {

			return true;

		}

		List<TB_CENSUS_SECONDMENT> ChangeOfSecondment = Sec.get_Secondment1(census_id, comm_id);

		if (ChangeOfSecondment.size() > 0) {

			return true;

		}

		List<TB_DESERTER> deserter1 = deserter.deserter_GetData1(census_id, comm_id);

		if (deserter1.size() > 0) {

			return true;

		}

		List<TB_PSG_CANTEEN_CARD_DETAIL1> csdde = CSD.CSD_GetData1(census_id, comm_id);

		if (csdde.size() > 0) {

			return true;

		}

		return false;

	}

}

