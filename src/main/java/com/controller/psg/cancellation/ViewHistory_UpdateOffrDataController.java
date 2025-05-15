package com.controller.psg.cancellation;




import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.dao.psg.cancellation.ViewHistory_updateOffrDataDao;
import com.dao.psg.update_census_data.View_update_Dao;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/","user"})

public class ViewHistory_UpdateOffrDataController {
	
	@Autowired
	View_update_Dao UOD;
	
	@Autowired
	ViewHistory_updateOffrDataDao hisDao;
	
	@Autowired
	Psg_CommanDAO psg_com;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	
	Psg_CommonController mcommon = new Psg_CommonController();
	Approve_ViewHistory_UpdateOffrDataController app_his = new Approve_ViewHistory_UpdateOffrDataController();
	
	
	
@RequestMapping(value = "/viewHistory_UpadteOfficerDataUrl", method = RequestMethod.POST)
		public ModelAndView viewHistory_UpadteOfficerDataUrl(@ModelAttribute("idhis") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication, HttpServletRequest request,
				HttpSession sessionEdit,HttpSession session,
				@RequestParam(value = "personnel_no_edithis", required = false) String personnel_no,
				@RequestParam(value = "personnel_nohis", required = false) String personnel_no6,
				@RequestParam(value = "statushis", required = false) String status6,
				@RequestParam(value = "rankhis", required = false) String rank6,
				@RequestParam(value = "census_idHis", required = false) String census_idHis,
				@RequestParam(value = "unit_namehis", required = false) String unit_name6,
				@RequestParam(value = "icstatushis", required = false) String icstatus6,
			    @RequestParam(value = "unit_sus_nohis", required = false) String unit_sus_no6) throws NumberFormatException, ParseException  {
	
	      BigInteger comm_id6 = new BigInteger(request.getParameter("comm_idhis"));
          String icstatus = request.getParameter("icstatushis");
	      String roleSusNo = session.getAttribute("roleSusNo").toString();
	    
	      String roleid = session.getAttribute("roleid").toString();

			Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", roleid);

			if (val == false) {

				return new ModelAndView("AccessTiles");

			}

			if (request.getHeader("Referer") == null) {

				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");

			}

	    
			if(icstatus.equals("0")) {
	      List<Map<String, Object>> ChangeOfRank = hisDao.getHisChangeOfRank(comm_id6,-1,session);
	      List<Map<String, Object>> ChangeOfName =  hisDao.getHisChangeOfName(comm_id6,-1,session);
	    
	      List<Map<String, Object>> ChildDetails = hisDao.getHisChild(comm_id6,-1,session);
	      List<Map<String, Object>> CDAaccount= hisDao.getHisContactDetails(comm_id6,-1,session);
	      List<Map<String, Object>> religion = hisDao.getHisChangeOfReligion(comm_id6,-1,session);
	      List<Map<String, Object>> ChangeOfComm = hisDao.getHisSsc_to_permt_commission(comm_id6,-1,session);
	      List<Map<String, Object>> ExtenComm= hisDao.getHisExtension_of_ssc(comm_id6,-1,session);  
	      List<Map<String, Object>> InterArmTrans = hisDao.getHisInter_arm_service_transfer(comm_id6,-1,session); 
	      List<Map<String, Object>> AwardsMedal= hisDao.getHisAwardAndMedal(comm_id6,-1,session); 
	      List<Map<String, Object>> Language = hisDao.getHisLanguageDetails(comm_id6,-1,session); 
	      List<Map<String, Object>> FLanguage = hisDao.getHisForeignLang(comm_id6,-1,session); 
	      List<Map<String, Object>> Qualification = hisDao.getHisQualification(comm_id6,-1,session);
	      List<Map<String, Object>> Secondment = hisDao.getHisSecondment(comm_id6,-1,session);
	      List<Map<String, Object>> ChngAppointment= hisDao.getHisChangeOfAppointment(comm_id6,-1,session);
	      List<Map<String, Object>> ForeignCountry = hisDao.getHisForeignCountry(comm_id6,-1,session);
	      List<Map<String, Object>> spouseQuali = hisDao.getHisPendingSpouseQualification(comm_id6,-1,session);
	      List<Map<String, Object>> Marital = hisDao.getHisfamily_married(comm_id6,-1,session);
	      List<Map<String, Object>> FiringStan = hisDao.getHisFiringDetails(comm_id6,-1,session);
	      List<Map<String, Object>> NOK = hisDao.getHisNokAddress(comm_id6,-1,session);
	      List<Map<String, Object>> BEPT =  hisDao.getHisBpetDetails(comm_id6,-1,session);
	      List<Map<String, Object>> IdentityCard= hisDao.getHisIdentity_Card(comm_id6,-1,session);
	      List<Map<String, Object>> ChangeAdd = hisDao.getHisAddress(comm_id6,-1,session);
	      List<Map<String, Object>> btel_cas =  hisDao.getHisBattle_physical_casuality(comm_id6,-1,session);
	      List<Map<String, Object>> Discipline = hisDao.getHisDiscipline(comm_id6,-1,session);
	      List<Map<String, Object>> Promotional_Exam = hisDao.getHisPromotionalExam(comm_id6,-1,session);
	      List<Map<String, Object>> Army_Course = hisDao.getHisArmyCourse(comm_id6,-1,session);
	      List<Map<String, Object>> Allergy = hisDao.getHisAllergy(comm_id6,-1,session);
		    
	      List<Map<String, Object>> MedDetails = hisDao.getHisMedicalCategory(comm_id6,-1,session);				   
	      List<Map<String, Object>> deserter1 = hisDao.getHisDeserter(comm_id6,-1,session);
	      List<Map<String, Object>> csddetails = hisDao.getHisCSD(comm_id6,-1,session);
	      Mmap.put("ChangeOfRank", ChangeOfRank.size());
		   Mmap.put("ChangeOfName", ChangeOfName.size());
		 
		   Mmap.put("ChildDetails", ChildDetails.size());
		   Mmap.put("CDAaccount", CDAaccount.size());
		   Mmap.put("religion", religion.size());
		   Mmap.put("ChangeOfComm", ChangeOfComm.size());
		   Mmap.put("ExtensionComm", ExtenComm.size());
		   Mmap.put("InterArmTransfer", InterArmTrans.size());
		   Mmap.put("AwardsMedal", AwardsMedal.size());
		   Mmap.put("Language", Language.size());
		   Mmap.put("FLanguage", FLanguage.size());
		   Mmap.put("Qualification", Qualification.size());
		   Mmap.put("SpouseQualification", spouseQuali.size());
		   Mmap.put("Secondment", Secondment.size());
		   Mmap.put("ChngAppointment", ChngAppointment.size());
		   Mmap.put("ForeignCountry", ForeignCountry.size());
		   Mmap.put("FiringStan", FiringStan.size());
		   Mmap.put("NOK", NOK.size());
		   Mmap.put("BEPT", BEPT.size());
		   Mmap.put("IdentityCard",IdentityCard.size());
		   Mmap.put("ChangeAdd",ChangeAdd.size());
		   Mmap.put("Marital",Marital.size());
		   Mmap.put("btel_cas",btel_cas.size());
		   Mmap.put("Discipline",Discipline.size());
		   Mmap.put("Promotional_Exam", Promotional_Exam.size());
		   Mmap.put("Army_Course", Army_Course.size());
		   Mmap.put("Allergy",Allergy.size());
		   Mmap.put("MedDetails",MedDetails.size());
		   Mmap.put("deserter1",deserter1.size());
		   Mmap.put("csddetails",csddetails.size());
			}
			else {
				  List<Map<String, Object>> NonEffective = hisDao.getHisNon_effective(comm_id6,-1,session);
				  Mmap.put("NonEffective", NonEffective.size());
			}
			// CHILDERN DATA
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
			//Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
			//Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));
			//Mmap.put("getMedCityName", mcommon.getMedCityName("", session));
			//Mmap.put("getCountryList", mcommon.getCountryList());
			//Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
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
			//Mmap.put("getStreamList", mcommon.getStreamList());
			Mmap.put("getCourseName", mcommon.getCourseName());
			Mmap.put("getFdService", mcommon.getFdService());
			Mmap.put("getCourseTypeList", mcommon.getCourseTypeList());
			Mmap.put("getExamList", mcommon.getExamList());
			Mmap.put("getdisciplinetypeentry", mcommon.getdisciplinetypeentry());
			
			 Mmap.put("getDclredRcvrdList", mcommon.getDclredRcvrdList());
			 Mmap.put("getCauseOfDeserter", mcommon.getCauseOfDeserter());
			 Mmap.put("getARM_TYPE", mcommon.getARM_TYPE());
			
			// END
			Mmap.put("msg", msg);
			Date date = new Date();
			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
			Mmap.put("date", date1);
			Mmap.put("personnel_no_edit", personnel_no);
			Mmap.put("census_idHis", census_idHis);
			Mmap.put("icstatus", icstatus);
			
			
			Mmap.put("personnel_no6", personnel_no6);
			Mmap.put("status6", status6);
			Mmap.put("rank6", rank6);
			Mmap.put("comm_id6", comm_id6);
			Mmap.put("unit_name6", unit_name6);
			Mmap.put("unit_sus_no6", unit_sus_no6);
			Mmap.put("icstatus6", icstatus6);
		return new ModelAndView("ViewHistory_Update_Officer_DataTiles");
	 }

@RequestMapping(value = "/viewHistory_Inactive_UpadteOfficerDataUrl", method = RequestMethod.POST)
public ModelAndView viewHistory_Inactive_UpadteOfficerDataUrl(@ModelAttribute("idcanhisI") String updateid,ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, Authentication authentication, HttpServletRequest request,
		HttpSession sessionEdit,HttpSession session,
		@RequestParam(value = "personnel_no_edithisI", required = false) String personnel_no,
		@RequestParam(value = "personnel_nohisI", required = false) String personnel_no6,
		@RequestParam(value = "statushisI", required = false) String status6,
		@RequestParam(value = "rankhisI", required = false) String rank6,
		@RequestParam(value = "census_idHisI", required = false) String census_idHis,
		@RequestParam(value = "unit_namehisI", required = false) String unit_name6,
		@RequestParam(value = "icstatushisI", required = false) String icstatus6,
	    @RequestParam(value = "unit_sus_nohisI", required = false) String unit_sus_no6) throws NumberFormatException, ParseException  {

  BigInteger comm_id6 = new BigInteger(request.getParameter("comm_idhisI"));
  String icstatus = request.getParameter("icstatushisI");
  String roleSusNo = session.getAttribute("roleSusNo").toString();
  String roleid = session.getAttribute("roleid").toString();

	Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", roleid);

	if (val == false) {

		return new ModelAndView("AccessTiles");

	}

	if (request.getHeader("Referer") == null) {

		msg = "";
		return new ModelAndView("redirect:bodyParameterNotAllow");

	}

	
  List<Map<String, Object>> ChangeOfRank = hisDao.getHisChangeOfRank(comm_id6,-1,session);
  List<Map<String, Object>> ChangeOfName =  hisDao.getHisChangeOfName(comm_id6,-1,session);

  List<Map<String, Object>> ChildDetails = hisDao.getHisChild(comm_id6,-1,session);
  List<Map<String, Object>> CDAaccount= hisDao.getHisContactDetails(comm_id6,-1,session);
  List<Map<String, Object>> religion = hisDao.getHisChangeOfReligion(comm_id6,-1,session);
  List<Map<String, Object>> ChangeOfComm = hisDao.getHisSsc_to_permt_commission(comm_id6,-1,session);
  List<Map<String, Object>> ExtenComm= hisDao.getHisExtension_of_ssc(comm_id6,-1,session);  
  List<Map<String, Object>> InterArmTrans = hisDao.getHisInter_arm_service_transfer(comm_id6,-1,session); 
  List<Map<String, Object>> AwardsMedal= hisDao.getHisAwardAndMedal(comm_id6,-1,session); 
  List<Map<String, Object>> Language = hisDao.getHisLanguageDetails(comm_id6,-1,session); 
  List<Map<String, Object>> FLanguage = hisDao.getHisForeignLang(comm_id6,-1,session); 
  List<Map<String, Object>> Qualification = hisDao.getHisQualification(comm_id6,-1,session);
  List<Map<String, Object>> Secondment = hisDao.getHisSecondment(comm_id6,-1,session);
  List<Map<String, Object>> ChngAppointment= hisDao.getHisChangeOfAppointment(comm_id6,-1,session);
  List<Map<String, Object>> ForeignCountry = hisDao.getHisForeignCountry(comm_id6,-1,session);
  List<Map<String, Object>> spouseQuali = hisDao.getHisPendingSpouseQualification(comm_id6,-1,session);
  List<Map<String, Object>> Marital = hisDao.getHisfamily_married(comm_id6,-1,session);
  List<Map<String, Object>> FiringStan = hisDao.getHisFiringDetails(comm_id6,-1,session);
  List<Map<String, Object>> NOK = hisDao.getHisNokAddress(comm_id6,-1,session);
  List<Map<String, Object>> BEPT =  hisDao.getHisBpetDetails(comm_id6,-1,session);
  List<Map<String, Object>> IdentityCard= hisDao.getHisIdentity_Card(comm_id6,-1,session);
  List<Map<String, Object>> ChangeAdd = hisDao.getHisAddress(comm_id6,-1,session);
  List<Map<String, Object>> btel_cas =  hisDao.getHisBattle_physical_casuality(comm_id6,-1,session);
  List<Map<String, Object>> Discipline = hisDao.getHisDiscipline(comm_id6,-1,session);
  List<Map<String, Object>> Promotional_Exam = hisDao.getHisPromotionalExam(comm_id6,-1,session);
  List<Map<String, Object>> Army_Course = hisDao.getHisArmyCourse(comm_id6,-1,session);
  List<Map<String, Object>> Allergy = hisDao.getHisAllergy(comm_id6,-1,session);
    
  List<Map<String, Object>> MedDetails = hisDao.getHisMedicalCategory(comm_id6,-1,session);				   
  List<Map<String, Object>> deserter1 = hisDao.getHisDeserter(comm_id6,-1,session);
  List<Map<String, Object>> csddetails = hisDao.getHisCSD(comm_id6,-1,session);
  Mmap.put("ChangeOfRank", ChangeOfRank.size());
   Mmap.put("ChangeOfName", ChangeOfName.size());
 
   Mmap.put("ChildDetails", ChildDetails.size());
   Mmap.put("CDAaccount", CDAaccount.size());
   Mmap.put("religion", religion.size());
   Mmap.put("ChangeOfComm", ChangeOfComm.size());
   Mmap.put("ExtensionComm", ExtenComm.size());
   Mmap.put("InterArmTransfer", InterArmTrans.size());
   Mmap.put("AwardsMedal", AwardsMedal.size());
   Mmap.put("Language", Language.size());
   Mmap.put("FLanguage", FLanguage.size());
   Mmap.put("Qualification", Qualification.size());
   Mmap.put("SpouseQualification", spouseQuali.size());
   Mmap.put("Secondment", Secondment.size());
   Mmap.put("ChngAppointment", ChngAppointment.size());
   Mmap.put("ForeignCountry", ForeignCountry.size());
   Mmap.put("FiringStan", FiringStan.size());
   Mmap.put("NOK", NOK.size());
   Mmap.put("BEPT", BEPT.size());
   Mmap.put("IdentityCard",IdentityCard.size());
   Mmap.put("ChangeAdd",ChangeAdd.size());
   Mmap.put("Marital",Marital.size());
   Mmap.put("btel_cas",btel_cas.size());
   Mmap.put("Discipline",Discipline.size());
   Mmap.put("Promotional_Exam", Promotional_Exam.size());
   Mmap.put("Army_Course", Army_Course.size());
   Mmap.put("Allergy",Allergy.size());
   Mmap.put("MedDetails",MedDetails.size());
   Mmap.put("deserter1",deserter1.size());
   Mmap.put("csddetails",csddetails.size());
	
	
		  List<Map<String, Object>> NonEffective = hisDao.getHisNon_effective(comm_id6,-1,session);
		  Mmap.put("NonEffective", NonEffective.size());
	
	// CHILDERN DATA
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
	//Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
	//Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));
	//Mmap.put("getMedCityName", mcommon.getMedCityName("", session));
	//Mmap.put("getCountryList", mcommon.getCountryList());
	//Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
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
	//Mmap.put("getStreamList", mcommon.getStreamList());
	Mmap.put("getCourseName", mcommon.getCourseName());
	Mmap.put("getFdService", mcommon.getFdService());
	Mmap.put("getCourseTypeList", mcommon.getCourseTypeList());
	Mmap.put("getExamList", mcommon.getExamList());
	Mmap.put("getdisciplinetypeentry", mcommon.getdisciplinetypeentry());
	
	 Mmap.put("getDclredRcvrdList", mcommon.getDclredRcvrdList());
	 Mmap.put("getCauseOfDeserter", mcommon.getCauseOfDeserter());
	 Mmap.put("getARM_TYPE", mcommon.getARM_TYPE());
	
	// END
	Mmap.put("msg", msg);
	Date date = new Date();
	String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
	Mmap.put("date", date1);
	Mmap.put("personnel_no_edit", personnel_no);
	Mmap.put("census_idHis", census_idHis);
	Mmap.put("icstatus", icstatus);
	
	
	Mmap.put("personnel_no6", personnel_no6);
	Mmap.put("status6", status6);
	Mmap.put("rank6", rank6);
	Mmap.put("comm_id6", comm_id6);
	Mmap.put("unit_name6", unit_name6);
	Mmap.put("unit_sus_no6", unit_sus_no6);
	Mmap.put("icstatus6", icstatus6);
return new ModelAndView("ViewHistory_Inactive_Update_Officer_DataTiles");
}
//change Of Rank

@RequestMapping(value = "/admin/getHisChangeOfRankData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisChangeOfRankData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisChangeOfRank(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisChangeOfNameData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisChangeOfNameData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisChangeOfName(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisChangeOfAppointmentData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisChangeOfAppointmentData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisChangeOfAppointment(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisIdentity_CardData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisIdentity_CardData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisIdentity_Card(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisChangeOfReligionData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisChangeOfReligionData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisChangeOfReligion(comm_id,status,session);
}


@RequestMapping(value = "/admin/getHisfamily_marriedData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisfamily_marriedData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisfamily_married(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisSpouseQualificationData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisSpouseQualificationData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisSpouseQualification(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisChildData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisChildData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisChild(comm_id,status,session);
	
}


@RequestMapping(value = "/admin/getHisNokAddressData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisNokAddressData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisNokAddress(comm_id,status,session);
	
}

@RequestMapping(value = "/admin/getHisAddressData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisAddressData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisAddress(comm_id,status,session);
	
}

@RequestMapping(value = "/admin/getHisContactDetailsData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisContactDetailsData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisContactDetails(comm_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisLanguageDetailsData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisLanguageDetailsData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisLanguageDetails(comm_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisForeignLangData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisForeignLangData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisForeignLang(comm_id,status,session);
	
}

@RequestMapping(value = "/admin/getHisQualificationData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisQualificationData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisQualification(comm_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisPromotionalExamData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisPromotionalExamData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisPromotionalExam(comm_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisArmyCourseData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisArmyCourseData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisArmyCourse(comm_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisBpetDetailsData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisBpetDetailsData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisBpetDetails(comm_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisFiringDetailsData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisFiringDetailsData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisFiringDetails(comm_id,status,session);
	
}

@RequestMapping(value = "/admin/getHisAllergyData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisAllergyData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisAllergy(comm_id,status,session);
	
}

@RequestMapping(value = "/admin/getHisMedicalCategoryData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisMedicalCategoryData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisMedicalCategory(comm_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisForeignCountryData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisForeignCountryData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisForeignCountry(comm_id,status,session);
}
	
@RequestMapping(value = "/admin/getHisAwardAndMedalData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisAwardAndMedalData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisAwardAndMedal(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisBattle_physical_casualityData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisBattle_physical_casualityData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisBattle_physical_casuality(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisDisciplineData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisDisciplineData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisDiscipline(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisSsc_to_permt_commissionData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisSsc_to_permt_commissionData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisSsc_to_permt_commission(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisInter_arm_service_transferData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisInter_arm_service_transferData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisInter_arm_service_transfer(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisExtension_of_sscData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisExtension_of_sscData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisExtension_of_ssc(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisNon_effectiveData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisNon_effectiveData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisNon_effective(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisSecondmentData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisSecondmentData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisSecondment(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisDeserterData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisDeserterData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisDeserter(comm_id,status,session);
}

@RequestMapping(value = "/admin/getHisCSDData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisCSDData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisCSD(comm_id,status,session);
}

@RequestMapping(value = "/admin/madical_cat_HisGetData", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> madical_cat_HisGetData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
String id=request.getParameter("id").toString();
return hisDao.madical_cat_HisGetData(id);
}


/////////cancel function/////

@RequestMapping(value = "/admin/CancelHisChangeOfRankData", method = RequestMethod.POST)
public @ResponseBody String CancelHisChangeOfRankData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CHANGE_OF_RANK set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}

@RequestMapping(value = "/admin/CancelHisChangeOfNameData", method = RequestMethod.POST)
public @ResponseBody String CancelHisChangeOfNameData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CHANGE_NAME set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}

@RequestMapping(value = "/admin/CancelHisChangeInAppointment", method = RequestMethod.POST)
public @ResponseBody String CancelHisChangeInAppointment(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CHANGE_OF_APPOINTMENT set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}



@RequestMapping(value = "/admin/CancelHisChangeIdentityCard", method = RequestMethod.POST)
public @ResponseBody String CancelHisChangeIdentityCard(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_IDENTITY_CARD set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}

@RequestMapping(value = "/admin/CancelHisChangeInReligion", method = RequestMethod.POST)
public @ResponseBody String CancelHisChangeInReligion(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CHANGE_RELIGION set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}



@RequestMapping(value = "/admin/CancelHisMarriage", method = RequestMethod.POST)
public @ResponseBody String CancelHisMarriage(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_FAMILY_MRG set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	 Query q0 = sessionhql.createQuery("select count(id) from TB_CENSUS_SPOUSE_QUALIFICATION where spouse_id=:s_id ").setInteger("s_id", id);
		Long c = (Long) q0.uniqueResult();
		
		if(c==0) {}
		else {
			String hql_n1 = "update TB_CENSUS_SPOUSE_QUALIFICATION set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
					+ " where  spouse_id=:id";
			Query query_n1 = sessionhql.createQuery(hql_n1).setInteger("status", status)
					.setInteger("id", id).setString("modified_by", username)
					.setTimestamp("modified_date", new Date());
			msg = query_n1.executeUpdate() > 0 ? "1" : "0";
		}
		if(msg.equals("1")) {
	    	if(status==0) {
	    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	    		msg=app_his.update_offr_cancel_status(comm_id,1);
	    	}
	     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}


@RequestMapping(value = "/admin/CancelHisSpouseQualification", method = RequestMethod.POST)
public @ResponseBody String CancelHisSpouseQualification(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_SPOUSE_QUALIFICATION set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}

@RequestMapping(value = "/admin/CancelHisChildDetails", method = RequestMethod.POST)
public @ResponseBody String CancelHisChildDetails(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
//	try {
	String hql_n = "update TB_CENSUS_CHILDREN set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
//	}catch (Exception e) {
//		// TODO: handle exception
//		tx.rollback();
//		return "0";
//	}
	return msg;
}



@RequestMapping(value = "/admin/CancelHisNokDetails", method = RequestMethod.POST)
public @ResponseBody String CancelHisNokDetails(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_NOK set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}


@RequestMapping(value = "/admin/CancelHisAddressDetails", method = RequestMethod.POST)
public @ResponseBody String CancelHisAddressDetails(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_ADDRESS set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}

@RequestMapping(value = "/admin/CancelHisQualification", method = RequestMethod.POST)
public @ResponseBody String CancelHisQualification(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_QUALIFICATION set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}


@RequestMapping(value = "/admin/CancelHisLanguage", method = RequestMethod.POST)
public @ResponseBody String CancelHisLanguae(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_LANGUAGE set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}


@RequestMapping(value = "/admin/CancelHisContactDetails", method = RequestMethod.POST)
public @ResponseBody String CancelHisContactDetails(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_CDA_ACCOUNT_NO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}



@RequestMapping(value = "/admin/CancelHisPromotionalExam", method = RequestMethod.POST)
public @ResponseBody String CancelHisPromotionalExamDetails(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_PROMOTIONAL_EXAM set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}

@RequestMapping(value = "/admin/CancelHisArmyCourse", method = RequestMethod.POST)
public @ResponseBody String CancelHisArmyCourse(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_ARMY_COURSE set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}

@RequestMapping(value = "/admin/CancelHisBPET", method = RequestMethod.POST)
public @ResponseBody String CancelHisBPET(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_BPET set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}

@RequestMapping(value = "/admin/CancelHisFiringStandard", method = RequestMethod.POST)
public @ResponseBody String CancelHisFiringStandard(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_FIRING_STANDARD set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}

@RequestMapping(value = "/admin/CancelHisAllergy", method = RequestMethod.POST)
public @ResponseBody String CancelHisAllergy(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_PSG_CENSUS_ALLERGICTOMEDICINE set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}


@RequestMapping(value = "/admin/CancelHisForeignCountry", method = RequestMethod.POST)
public @ResponseBody String CancelHisForeignCountry(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_FOREIGN_COUNTRY set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}


@RequestMapping(value = "/admin/CancelHisAwardsNmedal", method = RequestMethod.POST)
public @ResponseBody String CancelHisAwardsNmedal(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_AWARDSNMEDAL set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}


@RequestMapping(value = "/admin/CancelHisBatt_phy_casuality", method = RequestMethod.POST)
public @ResponseBody String CancelHisBatt_phy_casuality(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	String con=request.getParameter("con").toString();
 
			
	try {
	String hql_n = "update TB_CENSUS_BATT_PHY_CASUALITY set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	
	if(con.equals("1") || con.equals("2")) {
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate="select id from TB_NON_EFFECTIVE where comm_id=:comm_id and status=1 and cause_of_non_effective=:cause_of_non_effective order by id desc ";
		Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("cause_of_non_effective", con).setMaxResults(1);
		Integer c = (Integer)  query.uniqueResult();
		
		//26-01-1994
		if(c!=null && c>0) {
			int chang_id=c.intValue();
			
			String hql_n1 = "update TB_NON_EFFECTIVE set modified_date=:modified_date ,modified_date=:modified_by ,cancel_status=:status "
					+ " where  id=:id";
			Query query_n1 = sessionhql.createQuery(hql_n1).setInteger("status", status)
					.setInteger("id", chang_id).setString("modified_by", username)
					.setTimestamp("modified_date", new Date());
			msg = query_n1.executeUpdate() > 0 ? "1" : "0";
			sessionhql.flush();
			sessionhql.clear();
			
		
		}
	}
	
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}

@RequestMapping(value = "/admin/CancelHisDiscipline", method = RequestMethod.POST)
public @ResponseBody String CancelHisDiscipline(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_DISCIPLINE set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}



@RequestMapping(value = "/admin/CancelHisInter_arm_transfer", method = RequestMethod.POST)
public @ResponseBody String CancelHisInter_arm_transfer(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_INTER_ARM_TRANSFER set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}


@RequestMapping(value = "/admin/CancelHisChange_of_commission", method = RequestMethod.POST)
public @ResponseBody String CancelHisChange_of_commission(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_PSG_CHANGE_OF_COMISSION set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}


@RequestMapping(value = "/admin/CancelHisExtension_of_comission", method = RequestMethod.POST)
public @ResponseBody String CancelHisExtension_of_comission(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_PSG_EXTENSION_OF_COMISSION set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}

@RequestMapping(value = "/admin/CancelHisSecondment", method = RequestMethod.POST)
public @ResponseBody String CancelHisSecondment(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_SECONDMENT set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}

@RequestMapping(value = "/admin/CancelHisNon_effective", method = RequestMethod.POST)
public @ResponseBody String CancelHisNon_effective(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	String con=request.getParameter("con").toString();
	try {
	String hql_n = "update TB_NON_EFFECTIVE set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	
	if(con.equals("1") || con.equals("2")) {
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate="select id from TB_CENSUS_BATT_PHY_CASUALITY where comm_id=:comm_id and status=1 and nature_of_casuality=:nature_of_casuality order by id desc ";
		Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("nature_of_casuality", con).setMaxResults(1);
		Integer c = (Integer)  query.uniqueResult();
		
		if(c>0) {
			int chang_id=c.intValue();
		
			String hql_n1 = "update TB_CENSUS_BATT_PHY_CASUALITY set modify_on=:modified_date ,modify_by=:modified_by ,cancel_status=:status "
					+ " where  id=:id";
			Query query_n1 = sessionhql.createQuery(hql_n1).setInteger("status", status)
					.setInteger("id", chang_id).setString("modified_by", username)
					.setTimestamp("modified_date", new Date());
			msg = query_n1.executeUpdate() > 0 ? "1" : "0";
			sessionhql.flush();
			sessionhql.clear();
			
		
		}
	}
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}



@RequestMapping(value = "/admin/CancelHisDeserter", method = RequestMethod.POST)
public @ResponseBody String CancelHisDeserter(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_DESERTER set approved_date=:modified_date ,approved_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}


@RequestMapping(value = "/admin/CancelHisCSD", method = RequestMethod.POST)
public @ResponseBody String CancelHisCSD(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_PSG_CANTEEN_CARD_DETAIL1 set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
    		msg=app_his.update_offr_cancel_status(comm_id,1);
    	}
     }
	tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}


@RequestMapping(value = "/admin/CancelHisMedicalDetails", method = RequestMethod.POST)
public @ResponseBody String CancelHisMedicalDetails(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	String id=request.getParameter("id").toString();
	Date mod_date=new Date();
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
		
		  String[] outArr=id.split(",");
		
		  for(int i=0;i<outArr.length;i++) {
			  String[] inArr=outArr[i].split("I");
			  for(int j=0;j<inArr.length;j++) {
					String hql_n = "update TB_CENSUS_MEDICAL_CATEGORY set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
					+ " where  id=:id";
			Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
					.setInteger("id",Integer.parseInt( inArr[j])).setString("modified_by", username)
					.setTimestamp("modified_date",mod_date);
			msg = query_n.executeUpdate() > 0 ? "1" : "0";
			
			 if(msg.equals("1")) {
		    	if(status==0) {
		    		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		    		msg=app_his.update_offr_cancel_status(comm_id,1);
		    	}
		     }
			  }
		  }
		  tx.commit();
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
		return "0";
	}
	return msg;
}


@RequestMapping(value = "/GetCensusDataApprove_noneffective", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> GetCensusDataApprove_noneffective(BigInteger comm_id) { Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	
	ArrayList<ArrayList<String>> list = UOD.GetDataApprove_noneffective(comm_id);

	tx.commit();
	return list;

}


}

