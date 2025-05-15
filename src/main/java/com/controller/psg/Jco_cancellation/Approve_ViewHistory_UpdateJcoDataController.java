package com.controller.psg.Jco_cancellation;



import java.text.DateFormat;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;

import java.util.Locale;

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

import org.springframework.validation.BindingResult;

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

import com.dao.psg.cancellation.Approve_ViewHistory_updateOffrDataDao;

import com.dao.psg.cancellation.ViewHistory_updateOffrDataDao;

import com.dao.psg.jco_cancellation.Approve_ViewHistory_updateJcoDataDao;

import com.dao.psg.jco_cancellation.ViewHistory_updateJcoDataDao;

import com.dao.psg.update_census_data.View_update_Dao;

import com.persistance.util.HibernateUtil;



@Controller

@RequestMapping(value = {"admin","/","user"})

public class Approve_ViewHistory_UpdateJcoDataController {



	

	@Autowired

	View_update_Dao UOD;

	

	@Autowired

	ViewHistory_updateJcoDataDao hisDao;

	@Autowired

	Approve_ViewHistory_updateJcoDataDao appDao;

	

	@Autowired

	Psg_CommanDAO psg_com;

	

	@Autowired

	private RoleBaseMenuDAO roledao;

	

	//CommanController m = new CommanController();

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	Psg_CommonController mcommon = new Psg_CommonController();

	

	

	

@RequestMapping(value = "/Approve_viewHistory_UpadteJCODataUrl", method = RequestMethod.POST)

		public ModelAndView Approve_viewHistory_UpadteJCODataUrl(@ModelAttribute("idcanhisV") String updateid,ModelMap Mmap,

				@RequestParam(value = "msg", required = false) String msg, Authentication authentication, HttpServletRequest request,

				HttpSession sessionEdit,HttpSession session,

				@RequestParam(value = "personnel_no_editcanhis", required = false) String personnel_no,

				@RequestParam(value = "personnel_nocanhis", required = false) String personnel_no6,

				@RequestParam(value = "statuscanhis", required = false) String status6,

				@RequestParam(value = "rankcanhis", required = false) String rank6,

				@RequestParam(value = "unit_namecanhis", required = false) String unit_name6,

				@RequestParam(value = "icstatuscanhis", required = false) String icstatus6,

			    @RequestParam(value = "unit_sus_nocanhis", required = false) String unit_sus_no6) throws NumberFormatException, ParseException  {

			
	 String roleid = session.getAttribute("roleid").toString();
     Boolean val = roledao.ScreenRedirect("search_update_jco_data_url", session.getAttribute("roleid").toString());
       if(val == false) {
               return new ModelAndView("AccessTiles");
       }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	      String jco_id6 = request.getParameter("jco_idcanhis");

	      String roleSusNo = session.getAttribute("roleSusNo").toString();

	      

	      List<Map<String, Object>> ChangeOfRank = hisDao.getHisChangeOfRank(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> ChangeOfposting = hisDao.getHisChangeOfpost(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> ChangeOfName =  hisDao.getHisChangeOfName(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> NonEffective = hisDao.getHisNon_effective(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> ChildDetails = hisDao.getHisChild(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> CDAaccount= hisDao.getHisContactDetails(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> religion = hisDao.getHisChangeOfReligion(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> InterArmTrans = hisDao.getHisInter_arm_service_transfer(Integer.parseInt(jco_id6),0,session); 

	      List<Map<String, Object>> AwardsMedal= hisDao.getHisAwardAndMedal(Integer.parseInt(jco_id6),0,session); 

	      List<Map<String, Object>> Language = hisDao.getHisLanguageDetails(Integer.parseInt(jco_id6),0,session); 

	      List<Map<String, Object>> FLanguage = hisDao.getHisForeignLang(Integer.parseInt(jco_id6),0,session); 

	      List<Map<String, Object>> Qualification = hisDao.getHisQualification(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> ChngAppointment= hisDao.getHisChangeOfAppointment(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> ForeignCountry = hisDao.getHisForeignCountry(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> spouseQuali = hisDao.getHisPendingSpouseQualification(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> Marital = hisDao.getHisfamily_married(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> FiringStan = hisDao.getHisFiringDetails(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> NOK = hisDao.getHisNokAddress(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> BEPT =  hisDao.getHisBpetDetails(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> IdentityCard= hisDao.getHisIdentity_Card(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> ChangeAdd = hisDao.getHisAddress(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> btel_cas =  hisDao.getHisBattle_physical_casuality(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> Discipline = hisDao.getHisDiscipline(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> Promotional_Exam = hisDao.getHisPromotionalExam(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> Army_Course = hisDao.getHisArmyCourse(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> Allergy = hisDao.getHisAllergy(Integer.parseInt(jco_id6),0,session);

		    

	      List<Map<String, Object>> MedDetails = hisDao.getHisMedicalCategory(Integer.parseInt(jco_id6),0,session);				   

	      List<Map<String, Object>> deserter1 = hisDao.getHisDeserter(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> csddetails = hisDao.getHisCSD(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> tradedetails = hisDao.getHisTrade(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> payClassdetails = hisDao.getHisPayClass(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> payGroupdetails = hisDao.getHisPayGroup(Integer.parseInt(jco_id6),0,session);

	      List<Map<String, Object>> seniorityDtdetails = hisDao.getHisSeniority(Integer.parseInt(jco_id6),0,session);	    

	      List<Map<String, Object>>  attachmentDetails= hisDao.getHisAttachmentDetails(Integer.parseInt(jco_id6),0,session);

		    

//		       Mmap.put("msg", msg);

//			   Mmap.put("census_id", updateid);

//			   Mmap.put("personnel_no2", personnel_no2);

//			   Mmap.put("jco_id", jco_id);

//			   Mmap.put("event", event);

			   
	      
			   Mmap.put("ChangeOfRank", ChangeOfRank.size());
	      
	        Mmap.put("ChangeOfposting", ChangeOfposting.size());

			   Mmap.put("ChangeOfName", ChangeOfName.size());

			   Mmap.put("NonEffective", NonEffective.size());

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

			Mmap.put("getesubCopeList", mcommon.getCopeValueList("E SUb Value"));

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

			

			

			Mmap.put("personnel_no6", personnel_no6);

			Mmap.put("status6", status6);

			Mmap.put("rank6", rank6);

			Mmap.put("jco_id", jco_id6);

			Mmap.put("jco_id6", jco_id6);

			Mmap.put("unit_name6", unit_name6);

			Mmap.put("unit_sus_no6", unit_sus_no6);

			Mmap.put("icstatus6", icstatus6);

		return new ModelAndView("ApproveViewHistory_Update_Jco_DataTiles");

	 }



@RequestMapping(value = "/Approve_ViewHistoryJco_DataAction", method = RequestMethod.POST)

public ModelAndView Approve_ViewHistoryJco_DataAction( 

		@RequestParam(value = "msg", required = false) String msg1,

		HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {

	
	 String roleid = session.getAttribute("roleid").toString();
     Boolean val = roledao.ScreenRedirect("search_update_jco_data_url", session.getAttribute("roleid").toString());
       if(val == false) {
               return new ModelAndView("AccessTiles");
       }

		if(request.getHeader("Referer") == null ) {
			msg1 = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	String username = session.getAttribute("username").toString();

	DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	String jco_id6=request.getParameter("jco_idApp");

	String spouse_id=request.getParameter("spouseApp");

	 List<Map<String, Object>> ChangeOfRank = hisDao.getHisChangeOfRank(Integer.parseInt(jco_id6),0,session);
	 List<Map<String, Object>> ChangeOfposting = hisDao.getHisChangeOfpost(Integer.parseInt(jco_id6),0,session);
	 
	 

     List<Map<String, Object>> ChangeOfName =  hisDao.getHisChangeOfName(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> NonEffective = hisDao.getHisNon_effective(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> ChildDetails = hisDao.getHisChild(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> CDAaccount= hisDao.getHisContactDetails(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> religion = hisDao.getHisChangeOfReligion(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> InterArmTrans = hisDao.getHisInter_arm_service_transfer(Integer.parseInt(jco_id6),0,session); 

     List<Map<String, Object>> AwardsMedal= hisDao.getHisAwardAndMedal(Integer.parseInt(jco_id6),0,session); 

     List<Map<String, Object>> Language = hisDao.getHisLanguageDetails(Integer.parseInt(jco_id6),0,session); 

     List<Map<String, Object>> FLanguage = hisDao.getHisForeignLang(Integer.parseInt(jco_id6),0,session); 

     List<Map<String, Object>> Qualification = hisDao.getHisQualification(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> ChngAppointment= hisDao.getHisChangeOfAppointment(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> ForeignCountry = hisDao.getHisForeignCountry(Integer.parseInt(jco_id6),0,session);

	   

     List<Map<String, Object>> Marital = hisDao.getHisfamily_married(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> spouseQuali = hisDao.getHisPendingSpouseQualification(Integer.parseInt(jco_id6),0,session);

    

     List<Map<String, Object>> FiringStan = hisDao.getHisFiringDetails(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> NOK = hisDao.getHisNokAddress(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> BEPT =  hisDao.getHisBpetDetails(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> IdentityCard= hisDao.getHisIdentity_Card(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> ChangeAdd = hisDao.getHisAddress(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> btel_cas =  hisDao.getHisBattle_physical_casuality(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> Discipline = hisDao.getHisDiscipline(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> Promotional_Exam = hisDao.getHisPromotionalExam(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> Army_Course = hisDao.getHisArmyCourse(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> Allergy = hisDao.getHisAllergy(Integer.parseInt(jco_id6),0,session);

	    

     List<Map<String, Object>> MedDetails = hisDao.getHisMedicalCategory(Integer.parseInt(jco_id6),0,session);				   

     List<Map<String, Object>> deserter1 = hisDao.getHisDeserter(Integer.parseInt(jco_id6),0,session); 

     List<Map<String, Object>> csddetails = hisDao.getHisCSD(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> tradedetails = hisDao.getHisTrade(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> payClassdetails = hisDao.getHisPayClass(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> payGroupdetails = hisDao.getHisPayGroup(Integer.parseInt(jco_id6),0,session);

     List<Map<String, Object>> seniorityDtdetails = hisDao.getHisSeniority(Integer.parseInt(jco_id6),0,session);	    

     List<Map<String, Object>>  attachmentDetails= hisDao.getHisAttachmentDetails(Integer.parseInt(jco_id6),0,session);

		Date modified_date=new Date();

		String msg="1";

	

	try{

	

		 

		

    

      

      if (ChangeOfRank.size() > 0) {

		  

			

		

			

		msg=appDao.approveHisChangeOfRank(Integer.parseInt(jco_id6), ChangeOfRank, username, modified_date);	

				

				

	 }

     if (ChangeOfName.size() > 0) {

        	 

    	 msg=appDao.approveHisChangeOfName(Integer.parseInt(jco_id6), ChangeOfName, username, modified_date);	

	

        	   

	 }

     if (ChngAppointment.size() > 0) {

		  

    	 msg=appDao.approveHisChangeOfAppointment(Integer.parseInt(jco_id6), ChngAppointment, username, modified_date);	

			

	 }

      if (IdentityCard.size() > 0) {

  	    	

    	  msg=appDao.approveHisIdentity_Card(Integer.parseInt(jco_id6), IdentityCard, username, modified_date);	

    	   

      }

   

      if (religion.size() > 0) {



    	  msg=appDao.approveHisChangeOfReligion(Integer.parseInt(jco_id6), religion, username, modified_date);	

        	

       }



       if (Marital.size() > 0) {

    	   msg=appDao.approveHisfamily_married(Integer.parseInt(jco_id6), Marital, username, modified_date);	

	     

        }

		if(spouseQuali.size() > 0) {

		

			 msg=appDao.approveHisSpouseQualification(Integer.parseInt(jco_id6), spouseQuali, username, modified_date);

	}

          if (ChildDetails.size() > 0) {

        	  msg=appDao.approveHisChild(Integer.parseInt(jco_id6), ChildDetails, username, modified_date);	

  	            

          }

          if (NOK.size() > 0) {

			  

        	  msg=appDao.approveHisNokAddress(Integer.parseInt(jco_id6), NOK, username, modified_date);	

			 

	     }

          if (ChangeAdd.size() > 0) {

			  

        	  msg=appDao.approveHisAddress(Integer.parseInt(jco_id6), ChangeAdd, username, modified_date);	

   

	       }

          if (CDAaccount.size() > 0) {

            	

        	  msg=appDao.approveHisContactDetails(Integer.parseInt(jco_id6), CDAaccount, username, modified_date);	

            	

          }

          if (Language.size() > 0) {

			  

        	  msg=appDao.approveHisLanguageDetails(Integer.parseInt(jco_id6), Language, username, modified_date);	

		    	

	      }

          if (FLanguage.size() > 0) {

			  

        	  msg=appDao.approveHisLanguageDetails(Integer.parseInt(jco_id6), FLanguage, username, modified_date);	

		    	

	      }

          if (BEPT.size() > 0) {

			  

        	  msg=appDao.approveHisBpetDetails(Integer.parseInt(jco_id6), BEPT, username, modified_date);	

				 

	      }

          if (FiringStan.size() > 0) {

			  

        	  msg=appDao.approveHisFiringDetails(Integer.parseInt(jco_id6), FiringStan, username, modified_date);	

				 

	      }

          if (ForeignCountry.size() > 0) {

			  

        	  msg=appDao.approveHisForeignCountry(Integer.parseInt(jco_id6), ForeignCountry, username, modified_date);	

			  

		 }

          if (AwardsMedal.size() > 0) {

			  

        	  msg=appDao.approveHisAwardAndMedal(Integer.parseInt(jco_id6), AwardsMedal, username, modified_date);	

		    	

	      }

          if (btel_cas.size() > 0) {

			  

        	  msg=appDao.approveHisBattle_physical_casuality(Integer.parseInt(jco_id6), btel_cas, username, modified_date);	

    	      

		  }

          if (InterArmTrans.size() > 0) {

	  		  

        	  msg=appDao.approveHisInter_arm_service_transfer(Integer.parseInt(jco_id6), InterArmTrans, username, modified_date);	

  		       

 				

  	       }

          if (NonEffective.size() > 0) {

            	 

        	  msg=appDao.approveHisNon_effective(Integer.parseInt(jco_id6), NonEffective, username, modified_date);	

            	

			  }

   

         if (Qualification.size() > 0) {

        	 msg=appDao.approveHisQualification(Integer.parseInt(jco_id6), Qualification, username, modified_date);	

				   }

         

         if (Promotional_Exam.size() > 0) {

			  

        	 msg=appDao.approveHisPromotionalExam(Integer.parseInt(jco_id6), Promotional_Exam, username, modified_date);	



        	

	 }

         

     if (Army_Course.size() > 0) {

			  

    	 msg=appDao.approveHisArmyCourse(Integer.parseInt(jco_id6), Army_Course, username, modified_date);	



        	

	 }

     

     if (Allergy.size() > 0) {

		  

    	 msg=appDao.approveHisAllergy(Integer.parseInt(jco_id6), Allergy, username, modified_date);	



    	

 }

       

        

     if (MedDetails.size() > 0) {

		  

    	 msg=appDao.approveHisMedicalCategory(Integer.parseInt(jco_id6), MedDetails, username, modified_date);	



    	

 } 

     

     if (Discipline.size() > 0) {

		  

    	 msg=appDao.approveHisDiscipline(Integer.parseInt(jco_id6), Discipline, username, modified_date);	



    	

  }

     if (deserter1.size() > 0) {

    	 

    	 msg=appDao.approveHisDeserter(Integer.parseInt(jco_id6), deserter1, username, modified_date);	



    	

    	

     }

if (csddetails.size() > 0) {

    	 

    	 msg=appDao.approveHisCSD(Integer.parseInt(jco_id6), csddetails, username, modified_date);	



     }



if (tradedetails.size() > 0) {

	 

	 msg=appDao.approveHisTrade(Integer.parseInt(jco_id6), tradedetails, username, modified_date);		

}



if (payClassdetails.size() > 0) {

	 

	 msg=appDao.approveHisClassPay(Integer.parseInt(jco_id6), payClassdetails, username, modified_date);		

}



if (payGroupdetails.size() > 0) {

	 

	 msg=appDao.approveHisPayGroup(Integer.parseInt(jco_id6), payGroupdetails, username, modified_date);		

}





if (seniorityDtdetails.size() > 0) {

	 

	 msg=appDao.approveHisSeniority(Integer.parseInt(jco_id6), seniorityDtdetails, username, modified_date);		

}



if (attachmentDetails.size() > 0) {

	 

	 msg=appDao.approveHisAttachmentDetails(Integer.parseInt(jco_id6), attachmentDetails, username, modified_date);		

}


if (ChangeOfposting.size() > 0) {

	 

	 msg=appDao.approveHisChangeOfposting(Integer.parseInt(jco_id6), ChangeOfposting, username, modified_date);		

}




     if(msg.equals("1")) {

    	 Mmap.put("msg", "Data Approved Successfully");

    	 msg=update_Jco_OR_cancel_status(Integer.parseInt(jco_id6),0);

     }

     else {

    	 Mmap.put("msg", "Data Not Approved");

     }

    

		

	}catch (RuntimeException e) { 

		

			Mmap.put("msg", "Couldn?t roll back transaction " + e);

		

	} finally {

		

	}

	return new ModelAndView("redirect:search_update_jco_data_url");

}



@RequestMapping(value = "/admin/getHisPendingSpouseQualificationData_jco", method = RequestMethod.POST)

public @ResponseBody List<Map<String, Object>> getHisSpouseQualificationData(ModelMap Mmap, HttpSession session,

		HttpServletRequest request) throws ParseException {

	int jco_id=Integer.parseInt(request.getParameter("jco_id").toString());

	int status=Integer.parseInt(request.getParameter("cancel_status").toString());

	return hisDao.getHisPendingSpouseQualification(jco_id,status,session);

}





public  String update_Jco_OR_cancel_status(int jco_id,int status) throws ParseException {

	Session sessionhql = HibernateUtil.getSessionFactory().openSession();

	Transaction tx = sessionhql.beginTransaction();

	String msg = "0";

	

	try {

	String hql_n = "update TB_CENSUS_JCO_OR_PARENT set update_jco_cancel_status=:update_jco_cancel_status where  id=:id";

	Query query_n = sessionhql.createQuery(hql_n).setInteger("update_jco_cancel_status", status)

			.setInteger("id", jco_id);

	msg = query_n.executeUpdate() > 0 ? "1" : "0";

	tx.commit();

	}catch (Exception e) {

		// TODO: handle exception

		tx.rollback();

		return "0";

	}

	return msg;

}



}

