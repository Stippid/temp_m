package com.controller.psg.Jco_cancellation;




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
import com.dao.psg.jco_cancellation.ViewHistory_updateJcoDataDao;
import com.dao.psg.update_census_data.View_update_Dao;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/","user"})

public class ViewHistory_UpdateJcoDataController {
	

	
	@Autowired
	ViewHistory_updateJcoDataDao hisDao;
	
	@Autowired
	Psg_CommanDAO psg_com;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	//CommanController m = new CommanController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	Psg_CommonController mcommon = new Psg_CommonController();
	Approve_ViewHistory_UpdateJcoDataController app_his = new Approve_ViewHistory_UpdateJcoDataController();
	
	
	
@RequestMapping(value = "/viewHistory_UpadteJCODataUrl", method = RequestMethod.POST)
		public ModelAndView viewHistory_UpadteJCODataUrl(@ModelAttribute("jco_idhis") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication, HttpServletRequest request,
				HttpSession sessionEdit,HttpSession session,@RequestParam(value = "msg", required = false) String msg1,
				@RequestParam(value = "personnel_no_edithis", required = false) String personnel_no,
				@RequestParam(value = "personnel_nohis", required = false) String personnel_no6,
				@RequestParam(value = "statushis", required = false) String status6,
				@RequestParam(value = "rankhis", required = false) String rank6,
				@RequestParam(value = "unit_namehis", required = false) String unit_name6,
				@RequestParam(value = "icstatushis", required = false) String icstatus6,
			    @RequestParam(value = "unit_sus_nohis", required = false) String unit_sus_no6) throws NumberFormatException, ParseException  {
	
	      String jco_id = request.getParameter("jco_idhis");
          String icstatus = request.getParameter("icstatushis");
	      String roleSusNo = session.getAttribute("roleSusNo").toString();
	      String roleid = session.getAttribute("roleid").toString();
	      
	 	
	     Boolean val = roledao.ScreenRedirect("search_update_jco_data_url", session.getAttribute("roleid").toString());
	       if(val == false) {
	               return new ModelAndView("AccessTiles");
	       }

			if(request.getHeader("Referer") == null ) {
				msg1 = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			if(icstatus.equals("0")) {
	      List<Map<String, Object>> ChangeOfRank = hisDao.getHisChangeOfRank(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> ChangeOfName =  hisDao.getHisChangeOfName(Integer.parseInt(jco_id),-1,session);
	    
	      List<Map<String, Object>> ChildDetails = hisDao.getHisChild(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> CDAaccount= hisDao.getHisContactDetails(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> religion = hisDao.getHisChangeOfReligion(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> InterArmTrans = hisDao.getHisInter_arm_service_transfer(Integer.parseInt(jco_id),-1,session); 
	      List<Map<String, Object>> AwardsMedal= hisDao.getHisAwardAndMedal(Integer.parseInt(jco_id),-1,session); 
	      List<Map<String, Object>> Language = hisDao.getHisLanguageDetails(Integer.parseInt(jco_id),-1,session); 
	      List<Map<String, Object>> FLanguage = hisDao.getHisForeignLang(Integer.parseInt(jco_id),-1,session); 
	      List<Map<String, Object>> Qualification = hisDao.getHisQualification(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> ChngAppointment= hisDao.getHisChangeOfAppointment(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> ForeignCountry = hisDao.getHisForeignCountry(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> spouseQuali = hisDao.getHisPendingSpouseQualification(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> Marital = hisDao.getHisfamily_married(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> FiringStan = hisDao.getHisFiringDetails(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> NOK = hisDao.getHisNokAddress(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> BEPT =  hisDao.getHisBpetDetails(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> IdentityCard= hisDao.getHisIdentity_Card(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> ChangeAdd = hisDao.getHisAddress(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> btel_cas =  hisDao.getHisBattle_physical_casuality(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> Discipline = hisDao.getHisDiscipline(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> Promotional_Exam = hisDao.getHisPromotionalExam(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> Army_Course = hisDao.getHisArmyCourse(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> Allergy = hisDao.getHisAllergy(Integer.parseInt(jco_id),-1,session);
		    
	      List<Map<String, Object>> MedDetails = hisDao.getHisMedicalCategory(Integer.parseInt(jco_id),-1,session);				   
	      List<Map<String, Object>> deserter1 = hisDao.getHisDeserter(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> csddetails = hisDao.getHisCSD(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> tradedetails = hisDao.getHisTrade(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> payClassdetails = hisDao.getHisPayClass(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> payGroupdetails = hisDao.getHisPayGroup(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>> seniorityDtdetails = hisDao.getHisSeniority(Integer.parseInt(jco_id),-1,session);
	      List<Map<String, Object>>  attachmentDetails= hisDao.getHisAttachmentDetails(Integer.parseInt(jco_id),-1,session);
	  	
	      Mmap.put("ChangeOfRank", ChangeOfRank.size());
		   Mmap.put("ChangeOfName", ChangeOfName.size());
		 
		   Mmap.put("ChildDetails", ChildDetails.size());
		   Mmap.put("CDAaccount", CDAaccount.size());
		   Mmap.put("religion", religion.size());
		   Mmap.put("InterArmTransfer", InterArmTrans.size());
		   Mmap.put("AwardsMedal", AwardsMedal.size());
		   Mmap.put("Language", Language.size());
		   Mmap.put("FLanguage", FLanguage.size());
		   Mmap.put("Qualification", Qualification.size());
		   Mmap.put("SpouseQualification", spouseQuali.size());
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
		   Mmap.put("tradedetails",tradedetails.size());
		   Mmap.put("payClassdetails",payClassdetails.size());
		   Mmap.put("payGroupdetails",payGroupdetails.size());
		   Mmap.put("seniorityDtdetails",seniorityDtdetails.size());
		   Mmap.put("attachmentDetails",attachmentDetails.size());
			}
			else {
				  List<Map<String, Object>> NonEffective = hisDao.getHisNon_effective(Integer.parseInt(jco_id),-1,session);
				  Mmap.put("NonEffective", NonEffective.size());
			}
			// CHILDERN DATA
			Mmap.put("getRelationList", mcommon.getRelationList_JCO());
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
			Mmap.put("jco_idhis", updateid);
			Mmap.put("icstatus", icstatus);
			
			
			Mmap.put("personnel_no6", personnel_no6);
			Mmap.put("status6", status6);
			Mmap.put("rank6", rank6);
			Mmap.put("jco_id", jco_id);
			Mmap.put("jco_id6", jco_id);
			Mmap.put("unit_name6", unit_name6);
			Mmap.put("unit_sus_no6", unit_sus_no6);
			Mmap.put("icstatus6", icstatus6);
		return new ModelAndView("ViewHistory_Update_Jco_DataTiles");
	 }

@RequestMapping(value = "/viewHistory_Inactive_Upadte_JCODataUrl", method = RequestMethod.POST)
public ModelAndView viewHistory_Inactive_Upadte_JCODataUrl(@ModelAttribute("jco_idhis") String updateid,ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, Authentication authentication, HttpServletRequest request,
		HttpSession sessionEdit,HttpSession session,@RequestParam(value = "msg", required = false) String msg1,
		@RequestParam(value = "personnel_no_edithisI", required = false) String personnel_no,
		@RequestParam(value = "personnel_nohisI", required = false) String personnel_no6,
		@RequestParam(value = "statushisI", required = false) String status6,
		@RequestParam(value = "rankhisI", required = false) String rank6,
		@RequestParam(value = "unit_namehisI", required = false) String unit_name6,
		@RequestParam(value = "icstatushisI", required = false) String icstatus6,
	    @RequestParam(value = "unit_sus_nohisI", required = false) String unit_sus_no6) throws NumberFormatException, ParseException  {

  String jco_id = request.getParameter("jco_idhisI");
  String icstatus = request.getParameter("icstatushisI");
  String roleSusNo = session.getAttribute("roleSusNo").toString();
  String roleid = session.getAttribute("roleid").toString();
  
	
     Boolean val = roledao.ScreenRedirect("search_update_jco_data_url", session.getAttribute("roleid").toString());
       if(val == false) {
               return new ModelAndView("AccessTiles");
       }

		if(request.getHeader("Referer") == null ) {
			msg1 = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

  List<Map<String, Object>> ChangeOfRank = hisDao.getHisChangeOfRank(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> ChangeOfName =  hisDao.getHisChangeOfName(Integer.parseInt(jco_id),-1,session);

  List<Map<String, Object>> ChildDetails = hisDao.getHisChild(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> CDAaccount= hisDao.getHisContactDetails(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> religion = hisDao.getHisChangeOfReligion(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> InterArmTrans = hisDao.getHisInter_arm_service_transfer(Integer.parseInt(jco_id),-1,session); 
  List<Map<String, Object>> AwardsMedal= hisDao.getHisAwardAndMedal(Integer.parseInt(jco_id),-1,session); 
  List<Map<String, Object>> Language = hisDao.getHisLanguageDetails(Integer.parseInt(jco_id),-1,session); 
  List<Map<String, Object>> FLanguage = hisDao.getHisForeignLang(Integer.parseInt(jco_id),-1,session); 
  List<Map<String, Object>> Qualification = hisDao.getHisQualification(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> ChngAppointment= hisDao.getHisChangeOfAppointment(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> ForeignCountry = hisDao.getHisForeignCountry(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> spouseQuali = hisDao.getHisPendingSpouseQualification(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> Marital = hisDao.getHisfamily_married(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> FiringStan = hisDao.getHisFiringDetails(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> NOK = hisDao.getHisNokAddress(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> BEPT =  hisDao.getHisBpetDetails(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> IdentityCard= hisDao.getHisIdentity_Card(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> ChangeAdd = hisDao.getHisAddress(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> btel_cas =  hisDao.getHisBattle_physical_casuality(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> Discipline = hisDao.getHisDiscipline(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> Promotional_Exam = hisDao.getHisPromotionalExam(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> Army_Course = hisDao.getHisArmyCourse(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> Allergy = hisDao.getHisAllergy(Integer.parseInt(jco_id),-1,session);
    
  List<Map<String, Object>> MedDetails = hisDao.getHisMedicalCategory(Integer.parseInt(jco_id),-1,session);				   
  List<Map<String, Object>> deserter1 = hisDao.getHisDeserter(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> csddetails = hisDao.getHisCSD(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> tradedetails = hisDao.getHisTrade(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> payClassdetails = hisDao.getHisPayClass(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> payGroupdetails = hisDao.getHisPayGroup(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>> seniorityDtdetails = hisDao.getHisSeniority(Integer.parseInt(jco_id),-1,session);
  List<Map<String, Object>>  attachmentDetails= hisDao.getHisAttachmentDetails(Integer.parseInt(jco_id),-1,session);
  Mmap.put("ChangeOfRank", ChangeOfRank.size());
   Mmap.put("ChangeOfName", ChangeOfName.size());
 
   Mmap.put("ChildDetails", ChildDetails.size());
   Mmap.put("CDAaccount", CDAaccount.size());
   Mmap.put("religion", religion.size());
   Mmap.put("InterArmTransfer", InterArmTrans.size());
   Mmap.put("AwardsMedal", AwardsMedal.size());
   Mmap.put("Language", Language.size());
   Mmap.put("FLanguage", FLanguage.size());
   Mmap.put("Qualification", Qualification.size());
   Mmap.put("SpouseQualification", spouseQuali.size());
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
   Mmap.put("tradedetails",tradedetails.size());
   Mmap.put("payClassdetails",payClassdetails.size());
   Mmap.put("payGroupdetails",payGroupdetails.size());
   Mmap.put("seniorityDtdetails",seniorityDtdetails.size());
   Mmap.put("attachmentDetails",attachmentDetails.size());

		  List<Map<String, Object>> NonEffective = hisDao.getHisNon_effective(Integer.parseInt(jco_id),-1,session);
		  Mmap.put("NonEffective", NonEffective.size());
	
	// CHILDERN DATA
	Mmap.put("getRelationList", mcommon.getRelationList_JCO());
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
	Mmap.put("jco_idhis", updateid);
	Mmap.put("icstatus", icstatus);
	
	
	Mmap.put("personnel_no6", personnel_no6);
	Mmap.put("status6", status6);
	Mmap.put("rank6", rank6);
	Mmap.put("jco_id", jco_id);
	Mmap.put("jco_id6", jco_id);
	Mmap.put("unit_name6", unit_name6);
	Mmap.put("unit_sus_no6", unit_sus_no6);
	Mmap.put("icstatus6", icstatus6);
return new ModelAndView("ViewHistory_Update_JCO_DataTiles");
}
//change Of Rank

@RequestMapping(value = "/admin/getHisChangeOfRankData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisChangeOfRankData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisChangeOfRank(jco_id,status,session);
}

@RequestMapping(value = "/admin/getHisChangeOfNameData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisChangeOfNameData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisChangeOfName(jco_id,status,session);
}

@RequestMapping(value = "/admin/getHisChangeOfAppointmentData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisChangeOfAppointmentData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisChangeOfAppointment(jco_id,status,session);
}

@RequestMapping(value = "/admin/getHisIdentity_CardData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisIdentity_CardData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisIdentity_Card(jco_id,status,session);
}

@RequestMapping(value = "/admin/getHisChangeOfReligionData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisChangeOfReligionData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisChangeOfReligion(jco_id,status,session);
}


@RequestMapping(value = "/admin/getHisfamily_marriedData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisfamily_marriedData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisfamily_married(jco_id,status,session);
}

@RequestMapping(value = "/admin/getHisSpouseQualificationData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisSpouseQualificationData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int spouse_id=Integer.parseInt(request.getParameter("spouse_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisSpouseQualification(spouse_id,status,session);
}

@RequestMapping(value = "/admin/getHisChildData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisChildData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisChild(jco_id,status,session);
	
}


@RequestMapping(value = "/admin/getHisNokAddressData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisNokAddressData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisNokAddress(jco_id,status,session);
	
}

@RequestMapping(value = "/admin/getHisAddressData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisAddressData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisAddress(jco_id,status,session);
	
}

@RequestMapping(value = "/admin/getHisContactDetailsData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisContactDetailsData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisContactDetails(jco_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisLanguageDetailsData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisLanguageDetailsData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisLanguageDetails(jco_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisForeignLangData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisForeignLangData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisForeignLang(jco_id,status,session);
	
}

@RequestMapping(value = "/admin/getHisQualificationData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisQualificationData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisQualification(jco_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisPromotionalExamData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisPromotionalExamData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisPromotionalExam(jco_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisArmyCourseData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisArmyCourseData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisArmyCourse(jco_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisBpetDetailsData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisBpetDetailsData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisBpetDetails(jco_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisFiringDetailsData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisFiringDetailsData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisFiringDetails(jco_id,status,session);
	
}

@RequestMapping(value = "/admin/getHisAllergyData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisAllergyData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisAllergy(jco_id,status,session);
	
}

@RequestMapping(value = "/admin/getHisMedicalCategoryData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisMedicalCategoryData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisMedicalCategory(jco_id,status,session);
	
}
@RequestMapping(value = "/admin/getHisForeignCountryData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisForeignCountryData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisForeignCountry(jco_id,status,session);
}
	
@RequestMapping(value = "/admin/getHisAwardAndMedalData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisAwardAndMedalData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisAwardAndMedal(jco_id,status,session);
}

@RequestMapping(value = "/admin/getHisBattle_physical_casualityData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisBattle_physical_casualityData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisBattle_physical_casuality(jco_id,status,session);
}

@RequestMapping(value = "/admin/getHisDisciplineData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisDisciplineData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisDiscipline(jco_id,status,session);
}


@RequestMapping(value = "/admin/getHisInter_arm_service_transferData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisInter_arm_service_transferData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisInter_arm_service_transfer(jco_id,status,session);
}


@RequestMapping(value = "/admin/getHisNon_effectiveData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisNon_effectiveData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisNon_effective(jco_id,status,session);
}


@RequestMapping(value = "/admin/getHisDeserterData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisDeserterData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisDeserter(jco_id,status,session);
}

@RequestMapping(value = "/admin/getHisCSDData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisCSDData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisCSD(jco_id,status,session);
}

@RequestMapping(value = "/admin/madical_cat_HisGetData_jco", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> madical_cat_HisGetData(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
String id=request.getParameter("id").toString();
return hisDao.madical_cat_HisGetData(id);
}

@RequestMapping(value = "/admin/getHisTradeData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisTradeData_jco(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisTrade(jco_id,status,session);
}

@RequestMapping(value = "/admin/getHisPayClassData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisPayClassData_jco(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisPayClass(jco_id,status,session);
}

@RequestMapping(value = "/admin/getHisPayGroupData_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisPayGroupData_jco(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisPayGroup(jco_id,status,session);
}

@RequestMapping(value = "/admin/getHisSeniorityDT_Data_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisSeniorityDT_Data_jco(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisSeniority(jco_id,status,session);
}

@RequestMapping(value = "/admin/getHisAttachmentDetails_jco", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisAttachmentDetails_jco(ModelMap Mmap, HttpSession session,
	HttpServletRequest request) throws ParseException {
int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
return hisDao.getHisAttachmentDetails(jco_id,status,session);
}

/////////cancel function/////

@RequestMapping(value = "/admin/CancelHisChangeOfRankData_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisChangeOfRankData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
		String hql_n1 = "update TB_CHANGE_ARMY_NO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
				+ " where  rank_id=:rank_id";
		Query query_n1 = sessionhql.createQuery(hql_n1).setInteger("status", status)
				.setInteger("rank_id", id).setString("modified_by", username)
				.setTimestamp("modified_date", new Date());
		msg = query_n1.executeUpdate() > 0 ? "1" : "0";
		
	String hql_n = "update TB_CHANGE_OF_RANK_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisChangeOfNameData_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisChangeOfNameData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CHANGE_NAME_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisChangeInAppointment_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisChangeInAppointment(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CHANGE_OF_APPOINTMENT_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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



@RequestMapping(value = "/admin/CancelHisChangeIdentityCard_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisChangeIdentityCard(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_IDENTITY_CARD_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisChangeInReligion_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisChangeInReligion(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CHANGE_RELIGION_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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



@RequestMapping(value = "/admin/CancelHisMarriage_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisMarriage(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_FAMILY_MARRIED_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	 Query q0 = sessionhql.createQuery("select count(id) from TB_CENSUS_SPOUSE_QUALIFICATION_JCO where spouse_id=:s_id ").setInteger("s_id", id);
		Long c = (Long) q0.uniqueResult();
		
		if(c==0) {}
		else {
			String hql_n1 = "update TB_CENSUS_SPOUSE_QUALIFICATION_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
					+ " where  spouse_id=:id";
			Query query_n1 = sessionhql.createQuery(hql_n1).setInteger("status", status)
					.setInteger("id", id).setString("modified_by", username)
					.setTimestamp("modified_date", new Date());
			msg = query_n1.executeUpdate() > 0 ? "1" : "0";
		}
		if(msg.equals("1")) {
	    	if(status==0) {
	    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
	    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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


@RequestMapping(value = "/admin/CancelHisSpouseQualification_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisSpouseQualification(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_SPOUSE_QUALIFICATION_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisChildDetails_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisChildDetails(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_CHILDREN_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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



@RequestMapping(value = "/admin/CancelHisNokDetails_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisNokDetails(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_NOK_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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


@RequestMapping(value = "/admin/CancelHisAddressDetails_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisAddressDetails(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_ADDRESS_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisQualification_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisQualification(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_QUALIFICATION_JCO set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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


@RequestMapping(value = "/admin/CancelHisLanguage_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisLanguae(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_LANGUAGE_JCO set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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


@RequestMapping(value = "/admin/CancelHisContactDetails_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisContactDetails(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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



@RequestMapping(value = "/admin/CancelHisPromotionalExam_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisPromotionalExamDetails(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_PROMO_EXAM_JCO set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisArmyCourse_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisArmyCourse(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_ARMY_COURSE_JCO set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisBPET_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisBPET(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_BPET_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisFiringStandard_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisFiringStandard(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_FIRING_STANDARD_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisAllergy_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisAllergy(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_ALLERGIC_TO_MEDICINE_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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


@RequestMapping(value = "/admin/CancelHisForeignCountry_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisForeignCountry(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_FOREIGN_COUNTRY_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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


@RequestMapping(value = "/admin/CancelHisAwardsNmedal_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisAwardsNmedal(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_AWARDSNMEDAL_JCO set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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


@RequestMapping(value = "/admin/CancelHisBatt_phy_casuality_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisBatt_phy_casuality(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
//	String con=request.getParameter("con").toString();
 
			
	try {
	String hql_n = "update TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	

	
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisDiscipline_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisDiscipline(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CENSUS_DISCIPLINE_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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



@RequestMapping(value = "/admin/CancelHisInter_arm_transfer_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisInter_arm_transfer(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_INTER_ARM_TRANSFER_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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






@RequestMapping(value = "/admin/CancelHisNon_effective_jco", method = RequestMethod.POST)
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
	String hql_n = "update TB_NON_EFFECTIVE_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	
	if(con.equals("1") || con.equals("2")) {
		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
		String hqlUpdate="select id from TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO where jco_id=:jco_id and status=1 and nature_of_casuality=:nature_of_casuality order by id desc ";
		Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setString("nature_of_casuality", con).setMaxResults(1);
		Integer c = (Integer)  query.uniqueResult();
		
		if(c>0) {
			int chang_id=c.intValue();
		
			String hql_n1 = "update TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO set modify_on=:modified_date ,modify_by=:modified_by ,cancel_status=:status "
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
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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



@RequestMapping(value = "/admin/CancelHisDeserter_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisDeserter(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_DESERTER_JCO set approved_date=:modified_date ,approved_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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


@RequestMapping(value = "/admin/CancelHisCSD_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisCSD(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CANTEEN_CARD_DETAILS1_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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


@RequestMapping(value = "/admin/CancelHisMedicalDetails_jco", method = RequestMethod.POST)
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
					String hql_n = "update TB_MEDICAL_CATEGORY_JCO set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:status "
					+ " where  id=:id";
			Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
					.setInteger("id",Integer.parseInt( inArr[j])).setString("modified_by", username)
					.setTimestamp("modified_date",mod_date);
			msg = query_n.executeUpdate() > 0 ? "1" : "0";
			
			 if(msg.equals("1")) {
		    	if(status==0) {
		    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
		    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisTrade_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisTrade_jco(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CHANGE_IN_TRADE_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisPayClass_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisPayClass_jco(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CHANGE_IN_CLASS_PAY_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisPayGroup_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisPayGroup_jco(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CHANGE_IN_PAY_GROUP_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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

@RequestMapping(value = "/admin/CancelHisSeniority_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisSeniority_jco(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_CHANGE_IN_DATE_OF_SENIORITY_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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


@RequestMapping(value = "/admin/CancelHisAttachmentDetails_jco", method = RequestMethod.POST)
public @ResponseBody String CancelHisAttachmentDetails_jco(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	String username = session.getAttribute("username").toString();	
	int id=Integer.parseInt(request.getParameter("id").toString());
	int status=Integer.parseInt(request.getParameter("set_status").toString());
	try {
	String hql_n = "update TB_ATTACHMENT_DETAILS_JCO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
			+ " where  id=:id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
			.setInteger("id", id).setString("modified_by", username)
			.setTimestamp("modified_date", new Date());
	msg = query_n.executeUpdate() > 0 ? "1" : "0";
	if(msg.equals("1")) {
    	if(status==0) {
    		int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());
    		msg=app_his.update_Jco_OR_cancel_status(jco_id,1);
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




@RequestMapping(value = "/GetJcoOrCensusDataCancelHistory", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> GetJcoOrCensusDataCancelHistory(int jco_id) { Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	
	List<Map<String, Object>> list = hisDao.GetJcoOrCensusDataCancelHistory(jco_id);

	tx.commit();
	return list;

}


}

