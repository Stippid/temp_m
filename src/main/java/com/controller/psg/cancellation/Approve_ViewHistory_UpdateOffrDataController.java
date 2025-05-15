package com.controller.psg.cancellation;

import java.math.BigInteger;
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
import com.dao.psg.update_census_data.View_update_Dao;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Approve_ViewHistory_UpdateOffrDataController {

	
	@Autowired
	View_update_Dao UOD;
	
	@Autowired
	ViewHistory_updateOffrDataDao hisDao;
	@Autowired
	Approve_ViewHistory_updateOffrDataDao appDao;
	
	@Autowired
	Psg_CommanDAO psg_com;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	
	
	
@RequestMapping(value = "/Approve_viewHistory_UpadteOfficerDataUrl", method = RequestMethod.POST)
		public ModelAndView Approve_viewHistory_UpadteOfficerDataUrl(@ModelAttribute("idcanhisV") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication, HttpServletRequest request,
				HttpSession sessionEdit,HttpSession session,
				@RequestParam(value = "personnel_no_editcanhis", required = false) String personnel_no,
				@RequestParam(value = "personnel_nocanhis", required = false) String personnel_no6,
				@RequestParam(value = "statuscanhis", required = false) String status6,
				@RequestParam(value = "rankcanhis", required = false) String rank6,
				@RequestParam(value = "census_idcanhis", required = false) String census_idHis,
				@RequestParam(value = "unit_namecanhis", required = false) String unit_name6,
				@RequestParam(value = "icstatuscanhis", required = false) String icstatus6,
			    @RequestParam(value = "unit_sus_nocanhis", required = false) String unit_sus_no6) throws NumberFormatException, ParseException  {
			
	      BigInteger comm_id6 = new BigInteger(request.getParameter("comm_idcanhis"));
	      String roleSusNo = session.getAttribute("roleSusNo").toString();
	      String roleid = session.getAttribute("roleid").toString();
	      Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	       

	      List<Map<String, Object>> ChangeOfRank = hisDao.getHisChangeOfRank(comm_id6,0,session);
	      List<Map<String, Object>> ChangeOfName =  hisDao.getHisChangeOfName(comm_id6,0,session);
	      List<Map<String, Object>> NonEffective = hisDao.getHisNon_effective(comm_id6,0,session);
	      List<Map<String, Object>> ChildDetails = hisDao.getHisChild(comm_id6,0,session);
	      List<Map<String, Object>> CDAaccount= hisDao.getHisContactDetails(comm_id6,0,session);
	      List<Map<String, Object>> religion = hisDao.getHisChangeOfReligion(comm_id6,0,session);
	      List<Map<String, Object>> ChangeOfComm = hisDao.getHisSsc_to_permt_commission(comm_id6,0,session);
	      List<Map<String, Object>> ExtenComm= hisDao.getHisExtension_of_ssc(comm_id6,0,session);  
	      List<Map<String, Object>> InterArmTrans = hisDao.getHisInter_arm_service_transfer(comm_id6,0,session); 
	      List<Map<String, Object>> AwardsMedal= hisDao.getHisAwardAndMedal(comm_id6,0,session); 
	      List<Map<String, Object>> Language = hisDao.getHisLanguageDetails(comm_id6,0,session); 
	      List<Map<String, Object>> FLanguage = hisDao.getHisForeignLang(comm_id6,0,session); 
	      List<Map<String, Object>> Qualification = hisDao.getHisQualification(comm_id6,0,session);
	      List<Map<String, Object>> Secondment = hisDao.getHisSecondment(comm_id6,0,session);
	      List<Map<String, Object>> ChngAppointment= hisDao.getHisChangeOfAppointment(comm_id6,0,session);
	      List<Map<String, Object>> ForeignCountry = hisDao.getHisForeignCountry(comm_id6,0,session);
	      List<Map<String, Object>> spouseQuali = hisDao.getHisPendingSpouseQualification(comm_id6,0,session);
	      List<Map<String, Object>> Marital = hisDao.getHisfamily_married(comm_id6,0,session);
	      List<Map<String, Object>> FiringStan = hisDao.getHisFiringDetails(comm_id6,0,session);
	      List<Map<String, Object>> NOK = hisDao.getHisNokAddress(comm_id6,0,session);
	      List<Map<String, Object>> BEPT =  hisDao.getHisBpetDetails(comm_id6,0,session);
	      List<Map<String, Object>> IdentityCard= hisDao.getHisIdentity_Card(comm_id6,0,session);
	      List<Map<String, Object>> ChangeAdd = hisDao.getHisAddress(comm_id6,0,session);
	      List<Map<String, Object>> btel_cas =  hisDao.getHisBattle_physical_casuality(comm_id6,0,session);
	      List<Map<String, Object>> Discipline = hisDao.getHisDiscipline(comm_id6,0,session);
	      List<Map<String, Object>> Promotional_Exam = hisDao.getHisPromotionalExam(comm_id6,0,session);
	      List<Map<String, Object>> Army_Course = hisDao.getHisArmyCourse(comm_id6,0,session);
	      List<Map<String, Object>> Allergy = hisDao.getHisAllergy(comm_id6,0,session);
		    
	      List<Map<String, Object>> MedDetails = hisDao.getHisMedicalCategory(comm_id6,0,session);				   
	      List<Map<String, Object>> deserter1 = hisDao.getHisDeserter(comm_id6,0,session);
	      List<Map<String, Object>> csddetails = hisDao.getHisCSD(comm_id6,0,session);
//		  List<Map<String, Object>> CSDDetails = CSD.CSD_GetData1(Integer.parseInt(updateid),Integer.parseInt(comm_id));//keval
		    
//		       Mmap.put("msg", msg);
//			   Mmap.put("census_id", updateid);
//			   Mmap.put("personnel_no2", personnel_no2);
//			   Mmap.put("comm_id", comm_id);
//			   Mmap.put("event", event);
			   
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
			Mmap.put("census_idHis", census_idHis);
			
			
			Mmap.put("personnel_no6", personnel_no6);
			Mmap.put("status6", status6);
			Mmap.put("rank6", rank6);
			Mmap.put("comm_id6", comm_id6);
			Mmap.put("unit_name6", unit_name6);
			Mmap.put("unit_sus_no6", unit_sus_no6);
			Mmap.put("icstatus6", icstatus6);
		return new ModelAndView("ApproveViewHistory_Update_Officer_DataTiles");
	 }

@RequestMapping(value = "/Approve_ViewHistoryofficer_DataAction", method = RequestMethod.POST)
public ModelAndView Approve_officer_DataAction( 
		
		HttpServletRequest request, ModelMap Mmap, HttpSession session,	@RequestParam(value = "msg", required = false) String msg1) throws ParseException {
	 String roleid = session.getAttribute("roleid").toString();
     Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", session.getAttribute("roleid").toString());
       if(val == false) {
               return new ModelAndView("AccessTiles");
       }

		if(request.getHeader("Referer") == null ) {
			msg1 = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	String username = session.getAttribute("username").toString();
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	BigInteger comm_id6=new BigInteger(request.getParameter("comm_idApp"));
	String spouse_id=request.getParameter("spouseApp");
	 List<Map<String, Object>> ChangeOfRank = hisDao.getHisChangeOfRank(comm_id6,0,session);
     List<Map<String, Object>> ChangeOfName =  hisDao.getHisChangeOfName(comm_id6,0,session);
     List<Map<String, Object>> NonEffective = hisDao.getHisNon_effective(comm_id6,0,session);
     List<Map<String, Object>> ChildDetails = hisDao.getHisChild(comm_id6,0,session);
     List<Map<String, Object>> CDAaccount= hisDao.getHisContactDetails(comm_id6,0,session);
     List<Map<String, Object>> religion = hisDao.getHisChangeOfReligion(comm_id6,0,session);
     List<Map<String, Object>> ChangeOfComm = hisDao.getHisSsc_to_permt_commission(comm_id6,0,session);
     List<Map<String, Object>> ExtenComm= hisDao.getHisExtension_of_ssc(comm_id6,0,session);  
     List<Map<String, Object>> InterArmTrans = hisDao.getHisInter_arm_service_transfer(comm_id6,0,session); 
     List<Map<String, Object>> AwardsMedal= hisDao.getHisAwardAndMedal(comm_id6,0,session); 
     List<Map<String, Object>> Language = hisDao.getHisLanguageDetails(comm_id6,0,session); 
     List<Map<String, Object>> FLanguage = hisDao.getHisForeignLang(comm_id6,0,session); 
     List<Map<String, Object>> Qualification = hisDao.getHisQualification(comm_id6,0,session);
     List<Map<String, Object>> Secondment = hisDao.getHisSecondment(comm_id6,0,session);
     List<Map<String, Object>> ChngAppointment= hisDao.getHisChangeOfAppointment(comm_id6,0,session);
     List<Map<String, Object>> ForeignCountry = hisDao.getHisForeignCountry(comm_id6,0,session);
	   
     List<Map<String, Object>> Marital = hisDao.getHisfamily_married(comm_id6,0,session);
     List<Map<String, Object>> spouseQuali = hisDao.getHisPendingSpouseQualification(comm_id6,0,session);
    
     List<Map<String, Object>> FiringStan = hisDao.getHisFiringDetails(comm_id6,0,session);
     List<Map<String, Object>> NOK = hisDao.getHisNokAddress(comm_id6,0,session);
     List<Map<String, Object>> BEPT =  hisDao.getHisBpetDetails(comm_id6,0,session);
     List<Map<String, Object>> IdentityCard= hisDao.getHisIdentity_Card(comm_id6,0,session);
     List<Map<String, Object>> ChangeAdd = hisDao.getHisAddress(comm_id6,0,session);
     List<Map<String, Object>> btel_cas =  hisDao.getHisBattle_physical_casuality(comm_id6,0,session);
     List<Map<String, Object>> Discipline = hisDao.getHisDiscipline(comm_id6,0,session);
     List<Map<String, Object>> Promotional_Exam = hisDao.getHisPromotionalExam(comm_id6,0,session);
     List<Map<String, Object>> Army_Course = hisDao.getHisArmyCourse(comm_id6,0,session);
     List<Map<String, Object>> Allergy = hisDao.getHisAllergy(comm_id6,0,session);
	    
     List<Map<String, Object>> MedDetails = hisDao.getHisMedicalCategory(comm_id6,0,session);				   
     List<Map<String, Object>> deserter1 = hisDao.getHisDeserter(comm_id6,0,session); 
     List<Map<String, Object>> csddetails = hisDao.getHisCSD(comm_id6,0,session);
		Date modified_date=new Date();
		String msg="1";
	
	try{
	
		 
		
    
      
      if (ChangeOfRank.size() > 0) {
		  
			
		
			
		msg=appDao.approveHisChangeOfRank(comm_id6, ChangeOfRank, username, modified_date);	
				
				
	 }
     if (ChangeOfName.size() > 0) {
        	 
    	 msg=appDao.approveHisChangeOfName(comm_id6, ChangeOfName, username, modified_date);	
	
        	   
	 }
     if (ChngAppointment.size() > 0) {
		  
    	 msg=appDao.approveHisChangeOfAppointment(comm_id6, ChngAppointment, username, modified_date);	
			
	 }
      if (IdentityCard.size() > 0) {
  	    	
    	  msg=appDao.approveHisIdentity_Card(comm_id6, IdentityCard, username, modified_date);	
    	   
      }
   
      if (religion.size() > 0) {

    	  msg=appDao.approveHisChangeOfReligion(comm_id6, religion, username, modified_date);	
        	
       }

       if (Marital.size() > 0) {
    	   msg=appDao.approveHisfamily_married(comm_id6, Marital, username, modified_date);	
	     
        }
		if(spouseQuali.size() > 0) {
		
			 msg=appDao.approveHisSpouseQualification(comm_id6, spouseQuali, username, modified_date);
	}
          if (ChildDetails.size() > 0) {
        	  msg=appDao.approveHisChild(comm_id6, ChildDetails, username, modified_date);	
  	            
          }
          if (NOK.size() > 0) {
			  
        	  msg=appDao.approveHisNokAddress(comm_id6, NOK, username, modified_date);	
			 
	     }
          if (ChangeAdd.size() > 0) {
			  
        	  msg=appDao.approveHisAddress(comm_id6, ChangeAdd, username, modified_date);	
   
	       }
          if (CDAaccount.size() > 0) {
            	
        	  msg=appDao.approveHisContactDetails(comm_id6, CDAaccount, username, modified_date);	
            	
          }
          if (Language.size() > 0) {
			  
        	  msg=appDao.approveHisLanguageDetails(comm_id6, Language, username, modified_date);	
		    	
	      }
          if (FLanguage.size() > 0) {
			  
        	  msg=appDao.approveHisLanguageDetails(comm_id6, FLanguage, username, modified_date);	
		    	
	      }
          if (BEPT.size() > 0) {
			  
        	  msg=appDao.approveHisBpetDetails(comm_id6, BEPT, username, modified_date);	
				 
	      }
          if (FiringStan.size() > 0) {
			  
        	  msg=appDao.approveHisFiringDetails(comm_id6, FiringStan, username, modified_date);	
				 
	      }
          if (ForeignCountry.size() > 0) {
			  
        	  msg=appDao.approveHisForeignCountry(comm_id6, ForeignCountry, username, modified_date);	
			  
		 }
          if (AwardsMedal.size() > 0) {
			  
        	  msg=appDao.approveHisAwardAndMedal(comm_id6, AwardsMedal, username, modified_date);	
		    	
	      }
          if (btel_cas.size() > 0) {
			  
        	  msg=appDao.approveHisBattle_physical_casuality(comm_id6, btel_cas, username, modified_date);	
    	      
		  }
          if (InterArmTrans.size() > 0) {
	  		  
        	  msg=appDao.approveHisInter_arm_service_transfer(comm_id6, InterArmTrans, username, modified_date);	
  		       
 				
  	       }
          if (ChangeOfComm.size() > 0) {
      	    	
        	  msg=appDao.approveHisSsc_to_permt_commission(comm_id6, ChangeOfComm, username, modified_date);	
      	      
         }
          if (ExtenComm.size() > 0) {
  			  
        	  msg=appDao.approveHisExtension_of_ssc(comm_id6, ExtenComm, username, modified_date);	
  			  
  		 }
          if (Secondment.size() > 0) {
			  
        	  msg=appDao.approveHisSecondment(comm_id6, Secondment, username, modified_date);	
				  
	      }
          if (NonEffective.size() > 0) {
            	 
        	  msg=appDao.approveHisNon_effective(comm_id6, NonEffective, username, modified_date);	
            	
			  }
   
         if (Qualification.size() > 0) {
        	 msg=appDao.approveHisQualification(comm_id6, Qualification, username, modified_date);	
				   }
         
         if (Promotional_Exam.size() > 0) {
			  
        	 msg=appDao.approveHisPromotionalExam(comm_id6, Promotional_Exam, username, modified_date);	

        	
	 }
         
     if (Army_Course.size() > 0) {
			  
    	 msg=appDao.approveHisArmyCourse(comm_id6, Army_Course, username, modified_date);	

        	
	 }
     
     if (Allergy.size() > 0) {
		  
    	 msg=appDao.approveHisAllergy(comm_id6, Allergy, username, modified_date);	

    	
 }
       
        
     if (MedDetails.size() > 0) {
		  
    	 msg=appDao.approveHisMedicalCategory(comm_id6, MedDetails, username, modified_date);	

    	
 } 
     
     if (Discipline.size() > 0) {
		  
    	 msg=appDao.approveHisDiscipline(comm_id6, Discipline, username, modified_date);	

    	
  }
     if (deserter1.size() > 0) {
    	 
    	 msg=appDao.approveHisDeserter(comm_id6, deserter1, username, modified_date);	

    	
    	
     }
if (csddetails.size() > 0) {
    	 
    	 msg=appDao.approveHisCSD(comm_id6, csddetails, username, modified_date);	

    	
    	
     }
     if(msg.equals("1")) {
    	 Mmap.put("msg", "Data Approved Successfully");
    	 msg=update_offr_cancel_status(comm_id6,0);
     }
     else {
    	 Mmap.put("msg", "Data Not Approved");
     }
    
		
	}catch (RuntimeException e) { 
		
			Mmap.put("msg", "Couldn?t roll back transaction " + e);
		
	} finally {
		
	}
	return new ModelAndView("redirect:Search_UpdateOfficerDataUrl");
}

@RequestMapping(value = "/admin/getHisPendingSpouseQualificationData", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getHisSpouseQualificationData(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	BigInteger comm_id=new BigInteger(request.getParameter("comm_id"));
	int status=Integer.parseInt(request.getParameter("cancel_status").toString());
	return hisDao.getHisPendingSpouseQualification(comm_id,status,session);
}


public  String update_offr_cancel_status(BigInteger comm_id,int status) throws ParseException {
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	
	try {
	String hql_n = "update TB_CENSUS_DETAIL_Parent set update_ofr_cancel_status=:update_ofr_cancel_status where  comm_id=:comm_id";
	Query query_n = sessionhql.createQuery(hql_n).setInteger("update_ofr_cancel_status", status)
			.setBigInteger("comm_id", comm_id);
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
