package com.controller.psg.Jco_Update_JcoData;

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
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Jco_Update_JcoData.Search_UpdatedJcoOr_DataDao;
import com.dao.psg.Master.Psg_CommanDAO;
import com.dao.psg.update_census_data.Search_UpdateOffDataDao;
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
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_ARMY_NO;
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
import com.models.psg.Jco_Update_JcoData.TB_MEDICAL_CATEGORY_HISTORY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_MEDICAL_CATEGORY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_NOK_JCO;
import com.models.psg.Jco_Update_JcoData.TB_NON_EFFECTIVE_JCO;
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;
import com.persistance.util.HibernateUtil;

@Controller

@RequestMapping(value = { "admin", "/", "user" })

public class Search_UpdateJCODataController {

	psg_jco_CommonController pjc = new psg_jco_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	
	@Autowired

	Search_UpdateOffDataDao UOD;

	@Autowired

	Psg_CommanDAO psg_com;


	Psg_CommonController mcommon = new Psg_CommonController();
	PsgDashboardController das = new PsgDashboardController();
	
	
	@Autowired
	Search_UpdatedJcoOr_DataDao UJD;

	ValidationController valid = new ValidationController();
//	Allergy_Controller al = new Allergy_Controller();

	@Autowired

	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/search_update_jco_data_url", method = RequestMethod.GET)

	public ModelAndView Search_UpdateJCODataUrl(ModelMap Mmap, HttpSession session,

			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleType = session.getAttribute("roleType").toString();

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("search_update_jco_data_url", roleid);

		if (val == false) {

			return new ModelAndView("AccessTiles");

		}

		if (request.getHeader("Referer") == null) {

			msg = "";

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

		Mmap.put("getTypeofRankList", pjc.getRankjcoList());
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		

		Mmap.put("list", UJD.AppSearch_UpdateJCOData("", "0", "", "", "","","", roleSusNo, roleType, "0"));

		return new ModelAndView("SearchUpdate_JCO_DataTiles");

	}

	@RequestMapping(value = "/admin/GetSearch_UpdateJCOData", method = RequestMethod.POST)

	public ModelAndView GetSearch_UpdateJCOData(ModelMap Mmap, HttpSession session,

			@RequestParam(value = "msg", required = false) String msg,

			@RequestParam(value = "personnel_no1", required = false) String personnel_no,

			@RequestParam(value = "status1", required = false) String status,

			@RequestParam(value = "rank1", required = false) String rank,

			@RequestParam(value = "unit_name1", required = false) String unit_name,

			@RequestParam(value = "icstatus", required = false) String icstatus,

			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "cr_by1", required = false) String cr_by,
		    @RequestParam(value = "cr_date1", required = false) String cr_date) {

		
		unit_name = unit_name.replace("&#40;", "(");

		unit_name = unit_name.replace("&#41;", ")");

		String roleType = session.getAttribute("roleType").toString();

		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String roleAccess = session.getAttribute("roleAccess").toString();


		
		if(unit_sus_no!="") {
            if (!valid.SusNoLength(unit_sus_no)) {
                              Mmap.put("msg", valid.SusNoMSG);
                              return new ModelAndView("redirect:search_update_jco_data_url");
                      }
            if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
				return new ModelAndView("redirect:search_update_jco_data_url");
			}
           }
    
       
//		 if(unit_name!="" || !unit_name.equals("")) {
//	            if (!valid.isUnit(unit_name)) {
//	                              Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
//	                              return new ModelAndView("redirect:search_update_jco_data_url");
//	                      }
//
//	                      if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//	                              Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//	                              return new ModelAndView("redirect:search_update_jco_data_url");
//	                      }
//	    }
        if(personnel_no!="") {
            if (!valid.ArmyNoLength(personnel_no)) {
                                   Mmap.put("msg", valid.ArmyNoMSG );
                                   return new ModelAndView("redirect:search_update_jco_data_url");
                           }
            if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No ");
				return new ModelAndView("redirect:search_update_jco_data_url");
			}
                 if (personnel_no.length() < 2 || personnel_no.length() > 12) {
                                   Mmap.put("msg", "Army No No Should Contain Maximum 12 Character");
                                   return new ModelAndView("redirect:search_update_jco_data_url");
                           }
        } 

		
		
		
		if (roleAccess.equals("Unit")) {

			Mmap.put("sus_no", roleSusNo);

			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));

		}

		ArrayList<ArrayList<String>> list = UJD.AppSearch_UpdateJCOData(personnel_no, status, rank, unit_sus_no,
				unit_name,cr_by,cr_date, roleSusNo, roleType, icstatus);

		Mmap.put("list", list);

		Mmap.put("size", list.size());

		Mmap.put("personnel_no1", personnel_no);

		Mmap.put("rank1", rank);

		Mmap.put("status1", status);

		Mmap.put("unit_name1", unit_name);

		Mmap.put("icstatus", icstatus);

		Mmap.put("unit_sus_no1", unit_sus_no);

		Mmap.put("getTypeofRankList", pjc.getRankjcoList());
		 
		   Mmap.put("cr_date1", cr_date);
		Mmap.put("cr_by1", cr_by);
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		return new ModelAndView("SearchUpdate_JCO_DataTiles", "Search_Update_JCO_Data_CMD",
				new TB_CENSUS_JCO_OR_PARENT());

	}

	/*--------------------- For Approval Update Officer ----------------------------------*/
	//Change_postingJCOController changepost =  new Change_postingJCOController();
	Change_Of_Rank_JCOcontroller ChngRnk = new Change_Of_Rank_JCOcontroller();

	Change_Name_JCOController ChngName = new Change_Name_JCOController();
	Change_postingJCOController Chngpost = new Change_postingJCOController();
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

	@RequestMapping(value = "/Approve_UpadteJcoDataUrl", method = RequestMethod.POST)

	public ModelAndView Approve_UpadteJcoDataUrl(@ModelAttribute("id2") String updateid,
			@ModelAttribute("personnel_no2") String personnel_no2,

			@ModelAttribute("jco_id1") String jco_id, @ModelAttribute("event1") String event,
			@RequestParam(value = "msg", required = false) String msg,

			Authentication authentication, HttpServletRequest request, ModelMap Mmap, HttpSession session)
			throws ParseException {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("search_update_jco_data_url", roleid);

		if (val == false) {

			return new ModelAndView("AccessTiles");

		}

		if (request.getHeader("Referer") == null) {

			msg = "";

		}

		List<TB_CHANGE_OF_RANK_JCO> ChangeOfRank = ChngRnk.getChangeOfRankDataJCO1(Integer.parseInt(jco_id));

		List<TB_CHANGE_NAME_JCO> ChangeOfName = ChngName.getChangeOfNameJCOData1(Integer.parseInt(jco_id));

		List<TB_CHANGE_TYPE_OF_POSTING_JCO> ChangeOfposting= Chngpost.getChangeOfPostingJCOData1(Integer.parseInt(jco_id));
		
		List<TB_NON_EFFECTIVE_JCO> NonEffective = NonEffec.getNon_effective1(Integer.parseInt(jco_id));

		List<TB_CENSUS_CHILDREN_JCO> ChildDetails = Up.getm_children_detailsData1(Integer.parseInt(jco_id));

		List<TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO> CDAaccount = CDA.cda_acnt_no_GetData1(Integer.parseInt(jco_id));

		List<TB_CHANGE_RELIGION_JCO> religion = Rel.religion_GetData1(Integer.parseInt(jco_id));

		List<TB_INTER_ARM_TRANSFER_JCO> InterArmTrans = IntArmTran.getInterArm1(Integer.parseInt(jco_id));

		List<TB_CENSUS_AWARDSNMEDAL_JCO> AwardsMedal = AwrMed.getawardsNmedalData1(Integer.parseInt(jco_id));

		List<TB_CENSUS_LANGUAGE_JCO> Language = lan.update_census_getlanguagedetailsData1(Integer.parseInt(jco_id));

		List<TB_CENSUS_QUALIFICATION_JCO> Qualification = QUA
				.update_census_getQualificationData1(Integer.parseInt(jco_id));

		List<TB_CHANGE_OF_APPOINTMENT_JCO> ChngAppointment = Ap.get_AppointmentJCO1(Integer.parseInt(jco_id));
		
		List<TB_CHANGE_TA_STATUS_JCO> ChangeOfTA = ChngTA.ta_status_jco_GetData1(Integer.parseInt(jco_id));

		List<TB_CENSUS_FOREIGN_COUNTRY_JCO> ForeignCountry = ForCon.getUPForeginCountryData1(Integer.parseInt(jco_id));

		List<TB_CENSUS_FAMILY_MARRIED_JCO> Marital = Mrg.update_getfamily_marriageData1(Integer.parseInt(jco_id),
				Integer.parseInt(event));
		
		List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> Spouse_Quali = Mrg.getSpouseQualificationJCOData(Integer.parseInt(jco_id), 0);

		List<TB_CENSUS_FIRING_STANDARD_JCO> FiringStan = FirSta.getfire_detailsData1(Integer.parseInt(jco_id));

		List<TB_NOK_JCO> NOK = NOKc.nok_details_GetData1(Integer.parseInt(jco_id));

		List<TB_CENSUS_BPET_JCO> BEPT = BPET.getbpet_Data1(Integer.parseInt(jco_id));

		List<TB_IDENTITY_CARD_JCO> IdentityCard = IdeCard.Ide_card_getDataJCO1(Integer.parseInt(jco_id));

		List<TB_CENSUS_ADDRESS_JCO> ChangeAdd = Chngadd.address_details_GetData1(Integer.parseInt(jco_id));

		List<TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO> btel_cas = Btel
				.Battle_and_Physical_Casuality_GetData1(Integer.parseInt(jco_id));

		List<TB_CENSUS_DISCIPLINE_JCO> Discipline = Dis.get_Discipline1(Integer.parseInt(jco_id));

		List<TB_CENSUS_PROMO_EXAM_JCO> Promotional_Exam = PEC.update_census_promo_examData1(Integer.parseInt(jco_id));

		List<TB_CENSUS_ARMY_COURSE_JCO> Army_Course = ACC.update_census_army_courseData1(Integer.parseInt(jco_id));

		List<TB_ALLERGIC_TO_MEDICINE_JCO> Allergy = aler.update_census_allergicData1(Integer.parseInt(jco_id));

		List<TB_MEDICAL_CATEGORY_JCO> MedDetails = c_mad.getUpdatedmadicalData(Integer.parseInt(jco_id));

		List<TB_DESERTER_JCO> deserter1 = deserter.deserter_GetData1(Integer.parseInt(jco_id));

		List<TB_CANTEEN_CARD_DETAILS1_JCO> CSDDetails = CSD.CSD_GetData1(Integer.parseInt(jco_id));// keval
		

       List<TB_CHANGE_IN_TRADE_JCO> TradeDetails = trad.TRADE_GetData1(Integer.parseInt(jco_id));
		
		List<TB_CHANGE_IN_CLASS_PAY_JCO> PayDetails = pay.PayClass_GetData1(Integer.parseInt(jco_id));
		
		List<TB_CHANGE_IN_PAY_GROUP_JCO> GroupDetails = gp.PayGroup_GetData1(Integer.parseInt(jco_id));
		
		List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> SeniorityDetails = se.Seniority_GetData1(Integer.parseInt(jco_id));
		
		List<TB_ATTACHMENT_DETAILS_JCO> AttachmentDetails = att.Attachment_GetData1(Integer.parseInt(jco_id));

		Mmap.put("msg", msg);

		Mmap.put("census_id", updateid);

		Mmap.put("personnel_no2", personnel_no2);

		Mmap.put("jco_id", jco_id);

		Mmap.put("event", event);

		Mmap.put("ChangeOfRank", ChangeOfRank.size());

		Mmap.put("ChangeOfName", ChangeOfName.size());
		Mmap.put("ChangeOfposting", ChangeOfposting.size());

		Mmap.put("NonEffective", NonEffective.size());

		Mmap.put("ChildDetails", ChildDetails.size());
		
		Mmap.put("CDAaccount", CDAaccount.size());

		Mmap.put("religion", religion.size());

		Mmap.put("InterArmTransfer", InterArmTrans.size());

		Mmap.put("AwardsMedal", AwardsMedal.size());

		Mmap.put("Language", Language.size());

		Mmap.put("Qualification", Qualification.size());

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
		
		Mmap.put("TradeDetails", TradeDetails.size());       
		
		Mmap.put("PayDetails", PayDetails.size()); 

		Mmap.put("GroupDetails", GroupDetails.size());        
		
		Mmap.put("SeniorityDetails", SeniorityDetails.size()); 
		
		Mmap.put("AttachmentDetails", AttachmentDetails.size());




		Mmap.put("getCSDCategoryList", mcommon.getCSDCategoryList());


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

		Mmap.put("gettastatusList", mcommon.getstatusList());

		Mmap.put("getCourseName", mcommon.getCourseName());

		Mmap.put("getFdService", mcommon.getFdService());

		Mmap.put("getCourseTypeList", mcommon.getCourseTypeList());

		Mmap.put("getExamList", pjc.getExamList_jco());

		Mmap.put("getdisciplinetypeentry", mcommon.getdisciplinetypeentry());

		Mmap.put("getExservicemenList", mcommon.getExservicemenList());

		Mmap.put("getDclredRcvrdList", mcommon.getDclredRcvrdList());

		Mmap.put("getCauseOfDeserter", mcommon.getCauseOfDeserter());

		Mmap.put("getARM_TYPE", mcommon.getARM_TYPE());
		Mmap.put("getArmyCourseInstitute", mcommon.getArmyCourseInstitute("2"));
		Mmap.put("getMissingList", mcommon.getMissingList());

		Mmap.put("getCausesOfCasuality", mcommon.getCausesOfCasuality());

	
		Mmap.put("getDisc_Trialed", mcommon.getDisc_Trialed());
		Mmap.put("getArmyAct_Sec", mcommon.getArmyAct_Sec());
		Mmap.put("getSub_Clause", mcommon.getSub_Clause());
		Mmap.put("getTradeList", pjc.getTradeList());
		 Mmap.put("gettype_of_postingList", pjc.gettype_of_postingList());
		Mmap.put("getClassPayList", pjc.getClassPayList());         
		Mmap.put("getPayGroupList", pjc.getPayGroupList());

		return new ModelAndView("App_Update_JCO_DataTiles");

	}

	@RequestMapping(value = "/Approve_JCO_DataAction", method = RequestMethod.POST)

	public ModelAndView Approve_JCO_DataAction(@ModelAttribute("Approve_JCO_DataCMD") TB_CENSUS_JCO_OR_PARENT P,
			BindingResult result,

			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {

		String username = session.getAttribute("username").toString();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		List<TB_CHANGE_OF_RANK_JCO> ChangeOfRank = ChngRnk.getChangeOfRankDataJCO1(jco_id);

		List<TB_CHANGE_NAME_JCO> ChangeOfName = ChngName.getChangeOfNameJCOData1(jco_id);
		List<TB_CHANGE_TYPE_OF_POSTING_JCO> ChangeOfposting = Chngpost.getChangeOfPostingJCOData1(jco_id);

		List<TB_NON_EFFECTIVE_JCO> NonEffective = NonEffec.getNon_effective1(jco_id);

		List<TB_CENSUS_CHILDREN_JCO> ChildDetails = Up.getm_children_detailsData1(jco_id);

		List<TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO> CDAaccount = CDA.cda_acnt_no_GetData1(jco_id);

		List<TB_CHANGE_RELIGION_JCO> religion = Rel.religion_GetData1(jco_id);

		List<TB_INTER_ARM_TRANSFER_JCO> InterArmTrans = IntArmTran.getInterArm1(jco_id);

		List<TB_CENSUS_AWARDSNMEDAL_JCO> AwardsMedal = AwrMed.getawardsNmedalData1(jco_id);

		List<TB_CENSUS_LANGUAGE_JCO> Language = lan.update_census_getlanguagedetailsData1(jco_id);

		List<TB_CENSUS_QUALIFICATION_JCO> Qualification = QUA.update_census_getQualificationData1(jco_id);

		List<TB_CHANGE_OF_APPOINTMENT_JCO> ChngAppointment = Ap.get_AppointmentJCO1(jco_id);
		
		List<TB_CHANGE_TA_STATUS_JCO> ChangeOfTA = ChngTA.ta_status_jco_GetData1(jco_id);

		List<TB_CENSUS_FOREIGN_COUNTRY_JCO> ForeignCountry = ForCon.getUPForeginCountryData1(jco_id);

		List<TB_CENSUS_FAMILY_MARRIED_JCO> Marital = Mrg.update_getfamily_marriageData1(jco_id,
				Integer.parseInt(request.getParameter("event")));
		
		List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> Spouse_Quali = Mrg.getSpouseQualificationJCOData(jco_id, 0);
		
		List<TB_CENSUS_FIRING_STANDARD_JCO> FiringStan = FirSta.getfire_detailsData1(jco_id);

		List<TB_NOK_JCO> NOK = NOKc.nok_details_GetData1(jco_id);

		List<TB_CENSUS_BPET_JCO> BEPT = BPET.getbpet_Data1(jco_id);

		List<TB_IDENTITY_CARD_JCO> IdentityCard = IdeCard.Ide_card_getDataJCO1(jco_id);

		List<TB_CENSUS_ADDRESS_JCO> ChangeAdd = Chngadd.address_details_GetData1(jco_id);

		List<TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO> btel_cas = Btel.Battle_and_Physical_Casuality_GetData1(jco_id);

		List<TB_CENSUS_DISCIPLINE_JCO> Discipline = Dis.get_Discipline1(jco_id);

		List<TB_CENSUS_PROMO_EXAM_JCO> Promotional_Exam = PEC.update_census_promo_examData1(jco_id);

		List<TB_CENSUS_ARMY_COURSE_JCO> Army_Course = ACC.update_census_army_courseData1(jco_id);

		List<TB_ALLERGIC_TO_MEDICINE_JCO> Allergy = aler.update_census_allergicData1(jco_id);

		List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> SpouseDeatils = Mrg.update_census_Spouse(jco_id);

		List<TB_MEDICAL_CATEGORY_JCO> MedDetails = c_mad.getUpdatedmadicalData(jco_id);

		List<TB_DESERTER_JCO> deserter1 = deserter.deserter_GetData1(jco_id);

		List<TB_CANTEEN_CARD_DETAILS1_JCO> CSDDetails = CSD.CSD_GetData1(jco_id);// keval
		
		List<TB_CHANGE_IN_TRADE_JCO> TradeDetails = trad.TRADE_GetData1(jco_id);
		
		List<TB_CHANGE_IN_CLASS_PAY_JCO> PayDetails = pay.PayClass_GetData1(jco_id);
		
		List<TB_CHANGE_IN_PAY_GROUP_JCO> GroupDetails = gp.PayGroup_GetData1(jco_id);
		
		List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> SeniorityDetails = se.Seniority_GetData1(jco_id);
		
		List<TB_ATTACHMENT_DETAILS_JCO> AttachmentDetails = att.Attachment_GetData1(jco_id);

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		Date modified_date = new Date();

		try {

			P.setId(Integer.parseInt(request.getParameter("jco_id")));

			P.setModified_date(modified_date);

//				TB_TRANS_PROPOSED_COMM_LETTER Comm = new TB_TRANS_PROPOSED_COMM_LETTER();
//
//				Comm.setId(Integer.parseInt(request.getParameter("jco_id")));

			if (CSDDetails.size() > 0) {// keval

				TB_CANTEEN_CARD_DETAILS1_JCO ChangeCSD = new TB_CANTEEN_CARD_DETAILS1_JCO();

				ChangeCSD.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeCSD.setModified_date(modified_date);

				Mmap.put("msg", CSD.Update_CSD_details(ChangeCSD, username));

			}

			if (ChangeOfRank.size() > 0) {

				TB_CHANGE_OF_RANK_JCO ChangeRank = new TB_CHANGE_OF_RANK_JCO();

				ChangeRank.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChangeRank.setModified_date(modified_date);
				int rank_id=ChangeOfRank.get(0).getId();
				List<TB_CHANGE_ARMY_NO> armynolist = ChngRnk.getChangeOfArmyNoDataJCO1(rank_id,jco_id);
				if(armynolist.size()>0) {
					P.setArmy_no(armynolist.get(0).getArmy_no());
				}

				Mmap.put("msg", ChngRnk.Update_Change_of_Rank(ChangeRank, username));

				Date dt_rank = null;

				String date_of_rank = request.getParameter("dt_rank");

				if (date_of_rank != "") {

					dt_rank = format.parse(date_of_rank);

				}

				P.setRank(ChangeOfRank.get(0).getRank());


				P.setEnroll_dt(ChangeOfRank.get(0).getDate_of_rank());
				if (ChangeOfRank.get(0).getDate_of_attestation() != null) {
					P.setDate_of_attestation(ChangeOfRank.get(0).getDate_of_attestation());
				}

				P.setModified_date(modified_date);
				P.setCategory(ChangeOfRank.get(0).getCategory());
				
				
				ChngRnk.Update_Census_Rank(P, username);

			}

			if (ChangeOfName.size() > 0) {

				TB_CHANGE_NAME_JCO ChangeName = new TB_CHANGE_NAME_JCO();
				ChangeName.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
				ChangeName.setModified_date(modified_date);

				Mmap.put("msg", ChngName.Update_Change_of_NameJCO(ChangeName, username));

				// Comm.setName(request.getParameter("name_v"));

				P.setFull_name(ChangeOfName.get(0).getFull_name());
				P.setFirst_name(ChangeOfName.get(0).getFirst_name());
				P.setMiddle_name(ChangeOfName.get(0).getMiddle_name());
				P.setLast_name(ChangeOfName.get(0).getLast_name());
				ChngName.Update_Comm_NameJCO(P, username);

			}
			
			if (ChangeOfposting.size() > 0) {

				TB_CHANGE_TYPE_OF_POSTING_JCO Changeposting = new TB_CHANGE_TYPE_OF_POSTING_JCO();

				Changeposting.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				Changeposting.setModified_date(modified_date);

				Mmap.put("msg", Chngpost.Update_Change_of_postingJCO(Changeposting, username));

				

			}
			if (ChngAppointment.size() > 0) {

				TB_CHANGE_OF_APPOINTMENT_JCO ChngAppo = new TB_CHANGE_OF_APPOINTMENT_JCO();

				ChngAppo.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChngAppo.setModified_date(modified_date);

				Mmap.put("msg", Ap.Update_Change_of_Appointment(ChngAppo, username));

			}

			if (IdentityCard.size() > 0) {

				TB_IDENTITY_CARD_JCO IdeCardApp = new TB_IDENTITY_CARD_JCO();

				IdeCardApp.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				IdeCardApp.setModified_date(modified_date);

				Mmap.put("msg", IdeCard.Update_Ide_cardData(IdeCardApp, username));

			}
			
			if (ChangeOfTA.size() > 0) {

				TB_CHANGE_TA_STATUS_JCO ChngofTA = new TB_CHANGE_TA_STATUS_JCO();

				ChngofTA.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ChngofTA.setModified_date(modified_date);

				Mmap.put("msg", ChngTA.Update_TA_Status_jco(ChngofTA, username));

			}

			if (religion.size() > 0) {

				TB_CHANGE_RELIGION_JCO reli = new TB_CHANGE_RELIGION_JCO();

				reli.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				reli.setModified_date(modified_date);

				Mmap.put("msg", Rel.Update_Religion(reli, username));

				P.setReligion(religion.get(0).getReligion());

				Rel.Update_Census_Religion(P, username);

			}

			if (Marital.size() > 0) {

				TB_CENSUS_FAMILY_MARRIED_JCO MrgS = new TB_CENSUS_FAMILY_MARRIED_JCO();

				MrgS.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				MrgS.setModified_date(modified_date);
				 MrgS.setSeparated_to_dt(Marital.get(0).getSeparated_to_dt());
				Mmap.put("msg", Mrg.Update_Marital_Status(MrgS, username));

				P.setMarital_status(Marital.get(0).getMarital_status());

				Mrg.Update_Census_Marital(P, username);
			}

				if (SpouseDeatils.size() > 0) {

					TB_CENSUS_SPOUSE_QUALIFICATION_JCO sp = new TB_CENSUS_SPOUSE_QUALIFICATION_JCO();

					sp.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

					sp.setModified_date(modified_date);

					Mmap.put("msg", Mrg.Update_SposeQuali_Details(sp, username));

				}

			

			if (ChildDetails.size() > 0) {

				TB_CENSUS_CHILDREN_JCO chil = new TB_CENSUS_CHILDREN_JCO();

				chil.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				chil.setModified_date(modified_date);

				Mmap.put("msg", Up.Update_Children_Details(chil, username));

			}

			if (NOK.size() > 0) {

				TB_NOK_JCO NOKco = new TB_NOK_JCO();

				NOKco.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				NOKco.setModified_date(modified_date);

				Mmap.put("msg", NOKc.Update_NOK(NOKco, username));

			}

			if (ChangeAdd.size() > 0) {

				TB_CENSUS_ADDRESS_JCO Chngaddr = new TB_CENSUS_ADDRESS_JCO();

				Chngaddr.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				Chngaddr.setModified_date(modified_date);

				Mmap.put("msg", Chngadd.Update_Change_Add(Chngaddr, username));

			}

			if (CDAaccount.size() > 0) {

				TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO CDAacc = new TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO();

				CDAacc.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				CDAacc.setModified_date(modified_date);

				Mmap.put("msg", CDA.Update_CDA_Account_No(CDAacc, username));

			}

			if (Language.size() > 0) {

				TB_CENSUS_LANGUAGE_JCO Lan = new TB_CENSUS_LANGUAGE_JCO();

				Lan.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				Lan.setModify_on(modified_date);

				Mmap.put("msg", lan.Update_Language(Lan, username));

			}

			if (BEPT.size() > 0) {

				TB_CENSUS_BPET_JCO Bept1 = new TB_CENSUS_BPET_JCO();

				Bept1.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				Bept1.setModified_date(modified_date);

				Mmap.put("msg", BPET.Update_BEPT(Bept1, username));

			}

			if (FiringStan.size() > 0) {

				TB_CENSUS_FIRING_STANDARD_JCO FirStad = new TB_CENSUS_FIRING_STANDARD_JCO();

				FirStad.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				FirStad.setModified_date(modified_date);

				Mmap.put("msg", FirSta.Update_FiringStandard(FirStad, username));

			}

			if (ForeignCountry.size() > 0) {

				TB_CENSUS_FOREIGN_COUNTRY_JCO ForeCoun = new TB_CENSUS_FOREIGN_COUNTRY_JCO();

				ForeCoun.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ForeCoun.setModified_date(modified_date);

				Mmap.put("msg", ForCon.Update_ForeignContry(ForeCoun, username));

			}

			if (AwardsMedal.size() > 0) {

				TB_CENSUS_AWARDSNMEDAL_JCO AwardMedal = new TB_CENSUS_AWARDSNMEDAL_JCO();

				AwardMedal.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				AwardMedal.setModify_on(modified_date);

				Mmap.put("msg", AwrMed.Update_Awards_Medals(AwardMedal, username));

			}

			if (btel_cas.size() > 0) {

				TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO Bat = new TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO();

				Bat.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				Bat.setModify_on(modified_date);

				Mmap.put("msg", Btel.Update_Btle_Phy_Casuality(Bat, username));

			}

			if (InterArmTrans.size() > 0) {

				TB_INTER_ARM_TRANSFER_JCO InArmTran = new TB_INTER_ARM_TRANSFER_JCO();

				InArmTran.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				InArmTran.setModified_date(modified_date);

				Mmap.put("msg", IntArmTran.Update_InterArmTransfer(InArmTran, username));

				P.setArm_service(InterArmTrans.get(0).getParent_arm_service());

				P.setRegiment(InterArmTrans.get(0).getRegt());
				P.setRecord_office(InterArmTrans.get(0).getRecord_office_unit());
				P.setRecord_office_sus(InterArmTrans.get(0).getRecord_office_sus());

				IntArmTran.Update_Census_InterArm(P, username);

			}

			if (NonEffective.size() > 0) {

				TB_NON_EFFECTIVE_JCO Non_effc = new TB_NON_EFFECTIVE_JCO();

				Non_effc.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				Non_effc.setModified_date(modified_date);

				Mmap.put("msg", NonEffec.Update_Non_Effective(Non_effc, username));

				Mmap.put("msg", NonEffec.Update_JCO_Census_Data(P, username));

			}

			if (Qualification.size() > 0) {

				TB_CENSUS_QUALIFICATION_JCO Qua = new TB_CENSUS_QUALIFICATION_JCO();

				Qua.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				Qua.setModify_on(modified_date);

				Mmap.put("msg", QUA.Update_Qualification(Qua, username));

			}

			if (Promotional_Exam.size() > 0) {

				TB_CENSUS_PROMO_EXAM_JCO prop = new TB_CENSUS_PROMO_EXAM_JCO();

				prop.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				prop.setModify_on(modified_date);

				Mmap.put("msg", PEC.Update_Prop_Exam(prop, username));

			}

			if (Army_Course.size() > 0) {

				TB_CENSUS_ARMY_COURSE_JCO army = new TB_CENSUS_ARMY_COURSE_JCO();

				army.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				army.setModify_on(modified_date);

				Mmap.put("msg", ACC.Update_Army_Course(army, username));

			}

			if (Allergy.size() > 0) {

				TB_ALLERGIC_TO_MEDICINE_JCO ale = new TB_ALLERGIC_TO_MEDICINE_JCO();

				ale.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				ale.setModified_date(modified_date);

				Mmap.put("msg", aler.Update_Alergy(ale, username));

			}

			if (MedDetails.size() > 0) {

				TB_MEDICAL_CATEGORY_JCO med = new TB_MEDICAL_CATEGORY_JCO();

				med.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				med.setModify_on(modified_date);

				Mmap.put("msg", c_mad.Update_medicalCategory(med, username));
				String sShape="S ";
				String hShape="H ";
				String aShape="A ";
				String pShape="P ";
				String eShape="E ";
				
				
				String cCope="C ";
				String oCope="O ";
				String pCope="P ";
				String eCope="E ";
				
				int lmc=0;				
				Date med_auth_date=MedDetails.get(0).getDate_of_authority();
				for(int i=0;i<MedDetails.size();i++) {
				
					int newLmc=MedDetails.get(i).getShape_status();
					if(newLmc>lmc) {
						lmc=newLmc;
					}				
					String shape=MedDetails.get(i).getShape();
					if(shape.equals("S")) {
						 if(!sShape.equals("S "))
							 sShape+=",";						 
						 sShape+=MedDetails.get(i).getShape_value();
					}
					else if(shape.equals("H"))
					{
						 if(!hShape.equals("H "))
							 hShape+=",";						 
						 hShape+=MedDetails.get(i).getShape_value();
					}
					else if(shape.equals("A"))
					{
						 if(!aShape.equals("A "))
							 aShape+=",";						 
						 aShape+=MedDetails.get(i).getShape_value();
					}
					else if(shape.equals("P"))
					{
						 if(!pShape.equals("P "))
							 pShape+=",";						 
						 pShape+=MedDetails.get(i).getShape_value();
					}
					else if(shape.equals("E"))
					{
						 if(!eShape.equals("E "))
							 eShape+=",";						 
						 eShape+=MedDetails.get(i).getShape_value();
					}
					else if(shape.equals("C_C"))
					{
						 if(!cCope.equals("C "))
							 cCope+=",";						 
						 cCope+=MedDetails.get(i).getShape_value();
					}
					else if(shape.equals("C_O"))
					{
						 if(!oCope.equals("O "))
							 oCope+=",";						 
						 oCope+=MedDetails.get(i).getShape_value();
					}
					else if(shape.equals("C_P"))
					{
						 if(!pCope.equals("P "))
							 pCope+=",";						 
						 pCope+=MedDetails.get(i).getShape_value();
					}
					else if(shape.equals("C_E"))
					{
						 if(!eCope.equals("E "))
							 eCope+=",";						 
						 eCope+=MedDetails.get(i).getShape_value();
					}
				}
				
			String	Fshape=sShape+";"+hShape+";"+aShape+";"+pShape+";"+eShape;
			String	Fcope=cCope+";"+oCope+";"+pCope+";"+eCope;
			TB_MEDICAL_CATEGORY_HISTORY_JCO Mobj=new TB_MEDICAL_CATEGORY_HISTORY_JCO();
			Mobj.setJco_id(jco_id);
			Mobj.setShape(Fshape);
			Mobj.setCope(Fcope);
			Mobj.setStatus(1);
			Mobj.setDate_of_authority(med_auth_date);
			
			if(lmc==1) {
				Mobj.setMed_classification_lmc("FIT");
			}
			else if(lmc==2) {
				Mobj.setMed_classification_lmc("PERMANENT");
			}
			else if(lmc==3) {
				Mobj.setMed_classification_lmc("TEMPORARY");
			}
			
			Mmap.put("msg", c_mad.save_MedicalJCOHistory(Mobj));


			}

			if (Discipline.size() > 0) {

				TB_CENSUS_DISCIPLINE_JCO dis = new TB_CENSUS_DISCIPLINE_JCO();
				dis.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
				dis.setModified_date(modified_date);
				Mmap.put("msg", Dis.Update_Discipline(dis, username));

			}

			if (deserter1.size() > 0) {

				TB_DESERTER_JCO De = new TB_DESERTER_JCO();

				De.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				De.setApproved_date(modified_date);

				if (deserter1.get(0).getDt_recovered() == null || deserter1.get(0).getDt_recovered().equals("null")
						|| deserter1.get(0).getDt_recovered().equals("")) {


					deserter.Update_Census_deserterStatusForOut(P, username);

				} else {


					deserter.Update_Census_deserterStatusForIn(P, username);

				}

				Mmap.put("msg", deserter.Update_Deserter(De, username));

			

			}
			
			if (TradeDetails.size() > 0) {

				TB_CHANGE_IN_TRADE_JCO tr = new TB_CHANGE_IN_TRADE_JCO();

				tr.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				tr.setModified_date(modified_date);

				Mmap.put("msg", trad.Update_TradeData(tr, username));
				
				P.setTrade(TradeDetails.get(0).getTrade());

				trad.Update_Trade_JCO(P, username);

			}
			
			if (PayDetails.size() > 0) {

				TB_CHANGE_IN_CLASS_PAY_JCO p = new TB_CHANGE_IN_CLASS_PAY_JCO();

				p.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				p.setModified_date(modified_date);

				Mmap.put("msg", pay.Update_ClassPayData(p, username));
				
				P.setClass_pay(PayDetails.get(0).getCla_class());

				pay.Update_PAY_Class_JCO(P, username);

			}
			
			if (GroupDetails.size() > 0) {

				TB_CHANGE_IN_PAY_GROUP_JCO g = new TB_CHANGE_IN_PAY_GROUP_JCO();

				g.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				g.setModified_date(modified_date);

				Mmap.put("msg", gp.Update_GoupPayData(g, username));
				
				P.setPay_group(GroupDetails.get(0).getGp_group());

				gp.Update_PAY_Group_JCO(P, username);

			}
			
			if (SeniorityDetails.size() > 0) {

				TB_CHANGE_IN_DATE_OF_SENIORITY_JCO s = new TB_CHANGE_IN_DATE_OF_SENIORITY_JCO();

				s.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				s.setModified_date(modified_date);
				
				P.setDate_of_seniority(SeniorityDetails.get(0).getDate_of_seniority());

				Mmap.put("msg", se.Update_SeniorityData(s, username));
				se.Update_Seniority_JCO(P, username);
				

			}
			
			if (AttachmentDetails.size() > 0) {

				TB_ATTACHMENT_DETAILS_JCO s = new TB_ATTACHMENT_DETAILS_JCO();

				s.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

				s.setModified_date(modified_date);
				
				//P.setDate_of_seniority(SeniorityDetails.get(0).getDate_of_seniority());

				Mmap.put("msg", att.Update_AttachmentData(s, username));
				//se.Update_Seniority_JCO(P, username);
				

			}


			if (check_Update_JcoOr_DataStatus(jco_id, Integer.parseInt(request.getParameter("event")))) {

				Mmap.put("msg", Update_JcoOr_Data(P, username, 3));

			}

			else {

				Mmap.put("msg", Update_JcoOr_Data(P, username, 1));

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

		return new ModelAndView("redirect:search_update_jco_data_url");

	}

	public String Update_JcoOr_Data(TB_CENSUS_JCO_OR_PARENT obj, String username, int update_jco_status) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_CENSUS_JCO_OR_PARENT set modified_by=:modified_by,modified_date=:modified_date,"

					+ "update_jco_status=:update_jco_status where id=:id  and status in (1,4,5) and update_jco_status != 3 ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date())

					.setInteger("update_jco_status", update_jco_status).setInteger("id", obj.getId());

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

	public Boolean check_Update_JcoOr_DataStatus(int jco_id, int event) {

		List<TB_CHANGE_OF_RANK_JCO> ChangeOfRank = ChngRnk.getChangeOfRankDataJCO2(jco_id);

		if (ChangeOfRank.size() > 0) {

			return true;

		}

		List<TB_CHANGE_NAME_JCO> ChangeOfName = ChngName.getChangeOfNameJCOData2(jco_id);

		if (ChangeOfName.size() > 0) {

			return true;

		}
		List<TB_CHANGE_TYPE_OF_POSTING_JCO> ChangeOfpost = Chngpost.getChangeOfpostJCOData2(jco_id);

		if (ChangeOfpost.size() > 0) {

			return true;

		}
		
		List<TB_CHANGE_OF_APPOINTMENT_JCO> ChngAppointment = Ap.get_AppointmentJCO2(jco_id);

		if (ChngAppointment.size() > 0) {

			return true;

		}

		List<TB_IDENTITY_CARD_JCO> IdentityCard = IdeCard.Ide_card_getDataJCO2(jco_id);

		if (IdentityCard.size() > 0) {

			return true;

		}
		
		List<TB_CHANGE_TA_STATUS_JCO> ChangeOfTA = ChngTA.ta_status_jco_GetData2(jco_id);

		if (ChangeOfTA.size() > 0) {

			return true;

		}


		List<TB_CHANGE_RELIGION_JCO> religion = Rel.religion_GetData2(jco_id);

		if (religion.size() > 0) {

			return true;

		}

		List<TB_CENSUS_FAMILY_MARRIED_JCO> Marital = Mrg.update_getfamily_marriageData2(jco_id, event);

		if (Marital.size() > 0) {

			return true;

		}


		List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> Spouse_Quali = Mrg.getSpouseQualificationJCOData(jco_id, 3);
		if (Spouse_Quali.size() > 0) {

			return true;

		}
		List<TB_CENSUS_CHILDREN_JCO> ChildDetails = Up.getm_children_detailsData2(jco_id);

		if (ChildDetails.size() > 0) {

			return true;

		}

		List<TB_NOK_JCO> NOK = NOKc.nok_details_GetData2(jco_id);

		if (NOK.size() > 0) {

			return true;

		}

		List<TB_CENSUS_ADDRESS_JCO> ChangeAdd = Chngadd.address_details_GetData2(jco_id);

		if (ChangeAdd.size() > 0) {

			return true;

		}

		List<TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO> CDAaccount = CDA.cda_acnt_no_GetData2(jco_id);

		if (CDAaccount.size() > 0) {

			return true;

		}

		List<TB_CENSUS_LANGUAGE_JCO> Language = lan.update_census_getlanguagedetailsData2(jco_id);

		if (Language.size() > 0) {

			return true;

		}

		/////////////

		List<TB_CENSUS_QUALIFICATION_JCO> Qualification = QUA.getChangeOfQualification2(jco_id);

		if (Qualification.size() > 0) {

			return true;

		}

		List<TB_CENSUS_PROMO_EXAM_JCO> Promotional_Exam = PEC.getChangeOfPromotionExam2(jco_id);

		if (Promotional_Exam.size() > 0) {

			return true;

		}

		// List<TB_CENSUS_ARMY_COURSE> Army_Course =
		// UP_QUA.update_census_army_courseData1(jco_id);

		List<TB_CENSUS_ARMY_COURSE_JCO> Army_Course = ACC.getChangeOfArmyCourse2(jco_id);

		if (Army_Course.size() > 0) {

			return true;

		}

		List<TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO> btel_cas = Btel.getChangeBattleCasulity2(jco_id);

		if (btel_cas.size() > 0) {

			return true;

		}

		List<TB_NON_EFFECTIVE_JCO> NonEffective = NonEffec.getNonEffective2(jco_id);

		if (NonEffective.size() > 0) {

			return true;

		}

		List<TB_MEDICAL_CATEGORY_JCO> MedDetails = c_mad.getChangeMedical2(jco_id);

		if (MedDetails.size() > 0) {

			return true;

		}

		////////////

		List<TB_CENSUS_BPET_JCO> BEPT = BPET.getbpet_Data2(jco_id);

		if (BEPT.size() > 0) {

			return true;

		}

		List<TB_CENSUS_FIRING_STANDARD_JCO> FiringStan = FirSta.getfire_detailsData2(jco_id);

		if (FiringStan.size() > 0) {

			return true;

		}

		List<TB_ALLERGIC_TO_MEDICINE_JCO> ChangeOfAllergy = aler.getChangeOfAllergy2(jco_id);

		if (ChangeOfAllergy.size() > 0) {

			return true;

		}

		List<TB_CENSUS_FOREIGN_COUNTRY_JCO> ForeignCountry = ForCon.getChangeOfForeignCountry2(jco_id);

		if (ForeignCountry.size() > 0) {

			return true;

		}

		List<TB_CENSUS_AWARDSNMEDAL_JCO> AwardsMedal = AwrMed.getChangeOfAward2(jco_id);

		if (AwardsMedal.size() > 0) {

			return true;

		}

		List<TB_CENSUS_DISCIPLINE_JCO> ChangeOfDiscipline = Dis.getChangeOfDiscipline2(jco_id);

		if (ChangeOfDiscipline.size() > 0) {

			return true;

		}

		List<TB_INTER_ARM_TRANSFER_JCO> InterArmTrans = IntArmTran.getChangeOfInterArm2(jco_id);

		if (InterArmTrans.size() > 0) {

			return true;

		}

		List<TB_DESERTER_JCO> deserter1 = deserter.deserter_GetData2(jco_id);

		if (deserter1.size() > 0) {

			return true;

		}

		List<TB_CANTEEN_CARD_DETAILS1_JCO> csdde = CSD.getCSD_detailsData2(jco_id);

		if (csdde.size() > 0) {

			return true;

		}
		
		List<TB_CHANGE_IN_TRADE_JCO> tra = trad.Trade_getDataJCO2(jco_id);

		if (tra.size() > 0) {
			return true;
		}
		
		
		List<TB_CHANGE_IN_CLASS_PAY_JCO> cl = pay.class_getDataJCO2(jco_id);

		if (cl.size() > 0) {
			return true;
		}
		
		
		List<TB_CHANGE_IN_PAY_GROUP_JCO> gu = gp.group_getDataJCO2(jco_id);

		if (gu.size() > 0) {
			return true;
		}
		
		List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> s = se.seniority_getDataJCO2(jco_id);

		if (s.size() > 0) {
			return true;
		}
		
		List<TB_ATTACHMENT_DETAILS_JCO> a = att.attachment_getDataJCO2(jco_id);

		if (a.size() > 0) {
			return true;
		}

	
		return false;

	}
	
	public Boolean check_Update_JCO_DataStatusForPending(int jco_id,int event ) throws ParseException{

		
        List<TB_CHANGE_OF_RANK_JCO> ChangeOfRank = ChngRnk.getChangeOfRankDataJCO1(jco_id);

            if(ChangeOfRank.size()>0) {

			 return true;

		 }


	List<TB_CHANGE_NAME_JCO> ChangeOfName = ChngName.getChangeOfNameJCOData1(jco_id);
             if(ChangeOfName.size()>0) {

			 return true;

		 }


	List<TB_NON_EFFECTIVE_JCO> NonEffective = NonEffec.getNon_effective1(jco_id);
             if(NonEffective.size()>0) {

			 return true;

		 }

	List<TB_CENSUS_CHILDREN_JCO> ChildDetails = Up.getm_children_detailsData1(jco_id);
              if(ChildDetails.size()>0) {

			 return true;

		 }

	List<TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO> CDAaccount = CDA.cda_acnt_no_GetData1(jco_id);
              
            if(CDAaccount.size()>0) {

			 return true;

		 }
	List<TB_CHANGE_RELIGION_JCO> religion = Rel.religion_GetData1(jco_id);
              if(religion.size()>0) {

			 return true;

		 }

	List<TB_INTER_ARM_TRANSFER_JCO> InterArmTrans = IntArmTran.getInterArm1(jco_id);
                 if(InterArmTrans.size()>0) {

			 return true;

		 } 

	List<TB_CENSUS_AWARDSNMEDAL_JCO> AwardsMedal = AwrMed.getawardsNmedalData1(jco_id);
                    if(AwardsMedal.size()>0) {

			 return true;

		 } 

	List<TB_CENSUS_LANGUAGE_JCO> Language = lan.update_census_getlanguagedetailsData1(jco_id);
                if(Language.size()>0) {

			 return true;

		   } 

	List<TB_CENSUS_QUALIFICATION_JCO> Qualification = QUA.update_census_getQualificationData1(jco_id);
                 if(Qualification.size()>0) {

			 return true;

		   }  

	List<TB_CHANGE_OF_APPOINTMENT_JCO> ChngAppointment = Ap.get_AppointmentJCO1(jco_id);
                    if(ChngAppointment.size()>0) {

			 return true;

		   }  

	List<TB_CENSUS_FOREIGN_COUNTRY_JCO> ForeignCountry = ForCon.getUPForeginCountryData1(jco_id);
                     if(ForeignCountry.size()>0) {

			 return true;

		   }
                    
     List<TB_CHANGE_TA_STATUS_JCO> ChangeOfTA = ChngTA.ta_status_jco_GetData1(jco_id);
                    if(ChangeOfTA.size()>0) {

 			 return true;

 		   }

	List<TB_CENSUS_FAMILY_MARRIED_JCO> Marital = Mrg.update_getfamily_marriageData1(jco_id,event);

	 if(Marital.size()>0) {

		 return true;

	   } 
		List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> SpouseDeatils = Mrg.update_census_Spouse(jco_id);
        if(SpouseDeatils.size()>0) {

	 return true;

   } 

	List<TB_CENSUS_FIRING_STANDARD_JCO> FiringStan = FirSta.getfire_detailsData1(jco_id);
                     if(FiringStan.size()>0) {

			 return true;

		     } 

	List<TB_NOK_JCO> NOK = NOKc.nok_details_GetData1(jco_id);
                      if(NOK.size()>0) {

			 return true;

		     } 

	List<TB_CENSUS_BPET_JCO> BEPT = BPET.getbpet_Data1(jco_id);
                      if(BEPT.size()>0) {

			 return true;

		     } 

	List<TB_IDENTITY_CARD_JCO> IdentityCard = IdeCard.Ide_card_getDataJCO1(jco_id);
                       if(IdentityCard.size()>0) {

			 return true;

		     } 

	List<TB_CENSUS_ADDRESS_JCO> ChangeAdd = Chngadd.address_details_GetData1(jco_id);
                     if(ChangeAdd.size()>0) {

			 return true;

		     } 

	List<TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO> btel_cas = Btel.Battle_and_Physical_Casuality_GetData1(jco_id);

                      if(btel_cas.size()>0) {

			 return true;

		     } 
	List<TB_CENSUS_DISCIPLINE_JCO> Discipline = Dis.get_Discipline1(jco_id);
                      if(Discipline.size()>0) {

			 return true;

		     } 

	List<TB_CENSUS_PROMO_EXAM_JCO> Promotional_Exam = PEC.update_census_promo_examData1(jco_id);
                       if(Promotional_Exam.size()>0) {

			 return true;

		     } 

	List<TB_CENSUS_ARMY_COURSE_JCO> Army_Course = ACC.update_census_army_courseData1(jco_id);
                    if(Army_Course.size()>0) {

			 return true;

		     } 

	List<TB_ALLERGIC_TO_MEDICINE_JCO> Allergy = aler.update_census_allergicData1(jco_id);
                 if(Allergy.size()>0) {

			 return true;

		     } 





	List<TB_MEDICAL_CATEGORY_JCO> MedDetails = c_mad.getUpdatedmadicalData(jco_id);
                 if(MedDetails.size()>0) {

			 return true;

		     } 


	List<TB_DESERTER_JCO> deserter1 = deserter.deserter_GetData1(jco_id);
                  if(deserter1.size()>0) {

			 return true;

		     } 


	List<TB_CANTEEN_CARD_DETAILS1_JCO> CSDDetails = CSD.CSD_GetData1(jco_id);
               if(CSDDetails.size()>0) {

			 return true;

		     } 
	
	List<TB_CHANGE_IN_TRADE_JCO> TradeDetails = trad.TRADE_GetData1(jco_id);
               if(TradeDetails.size()>0) {

			 return true;

		     }
	
	List<TB_CHANGE_IN_CLASS_PAY_JCO> PayDetails = pay.PayClass_GetData1(jco_id);
                 if(PayDetails.size()>0) {

			 return true;

		     }
	
	
	List<TB_CHANGE_IN_PAY_GROUP_JCO> GroupDetails = gp.PayGroup_GetData1(jco_id);
                if(GroupDetails.size()>0) {

			 return true;

		     }
	
	List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> SeniorityDetails = se.Seniority_GetData1(jco_id);
                if(SeniorityDetails.size()>0) {

			 return true;

		     }
	
	List<TB_ATTACHMENT_DETAILS_JCO> AttachmentDetails = att.Attachment_GetData1(jco_id);
	   if(AttachmentDetails.size()>0) {

			 return true;

		     }
	   
	   List<TB_CHANGE_TYPE_OF_POSTING_JCO> ChangeOfposting = Chngpost.getChangeOfPostingJCOData1(jco_id);
	   if(ChangeOfposting.size()>0) {

			 return true;

		     }
		return false;
}


}
