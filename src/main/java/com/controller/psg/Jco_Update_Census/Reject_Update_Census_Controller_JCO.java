package com.controller.psg.Jco_Update_Census;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_SIBLINGS;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_AADHARNO_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_BIRTH_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_GENDER_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_HEIGHT_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_PAN_NO_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Reject_Update_Census_Controller_JCO {
	
	Psg_CommonController p_comm = new Psg_CommonController();
	
	psg_jco_CommonController jcom =new psg_jco_CommonController();
	
	
	Birth_UpdateController_JCO DB = new Birth_UpdateController_JCO();
	
	Gender_UpdateController_JCO GU = new Gender_UpdateController_JCO();
	
	Address_Birth_UpdateController_JCO AB = new Address_Birth_UpdateController_JCO();
	
	Nationality_UpdateController_JCO NT = new Nationality_UpdateController_JCO();
	
	Mother_Tongue_UpdateController_JCO MT = new Mother_Tongue_UpdateController_JCO();
	
	Blood_Group_UpdateController_JCO BG = new Blood_Group_UpdateController_JCO();
	
	Height_UpdateController_JCO HE = new Height_UpdateController_JCO();
	
	Aadhar_UpdateController_JCO ADN = new Aadhar_UpdateController_JCO(); 
	
	Pan_No_UpdateController_JCO PN = new Pan_No_UpdateController_JCO();
	
	Enrollment_Class_Update_Controller EC = new Enrollment_Class_Update_Controller();
	
	Rank_UpdateController RN = new Rank_UpdateController();
	
	Date_EnrollmentUpdateController DE = new Date_EnrollmentUpdateController();
	
	Date_AttestationUpdateController DA = new Date_AttestationUpdateController();
	
	Family_Details_UpdateController_JCO FD = new Family_Details_UpdateController_JCO();
	
	Sibling_UpdateController SB = new Sibling_UpdateController(); 
	
	String event1 = "";
	
	@RequestMapping(value = "/admin/Reject_Upadte_CensusJCODataUrl", method = RequestMethod.POST)
 	 public ModelAndView Reject_Upadte_CensusJCODataUrl(ModelMap Mmap, HttpSession session,
 			@ModelAttribute("personnel_no3") String personnel_no2,
 			
			@ModelAttribute("jco_id3") String jco_id,
			
			@ModelAttribute("event3") String event,

			@ModelAttribute("army_no7") String army_no7, 
			
			@ModelAttribute("status7") String status7, 
			
			@ModelAttribute("rank7") String rank7, 
			
			@ModelAttribute("jco_id7") String jco_id7, 
			
			@ModelAttribute("unit_name7") String unit_name7, 
			
			@ModelAttribute("unit_sus_no7") String unit_sus_no7,
			
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
 		 
			List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> ChangeOfGender = GU.getgenderDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_BIRTH_JCO> ChangeOfDOB = DB.getDOBDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> ChangeOfAddressBirth = AB.getAddBirthDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO> ChangeOfNationality = NT.getNationalityDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO> ChangeOfMotherTongue = MT.getMother_TongDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO> ChangeOfBloodGroup = BG.getBlood_GroupDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_HEIGHT_JCO> ChangeOfHeight = HE.getHeightDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> ChangeOfAadhar = ADN.getAdd_NoDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_PAN_NO_JCO> ChangeOfPanNo = PN.getPan_NoDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> ChangeOfClassEnroll = EC.getClass_EnrollDataJCO2(Integer.parseInt(jco_id));
			
//			List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> ChangeOfRank = RN.getRankDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> ChangeOfDtofEnrolment = DE.getDt_of_EnrollDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> ChangeOfDtofAttestation = DA.getDT_Of_AttesDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> ChangeOfFamily = FD.getFamilyDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_CENSUS_JCO_OR_SIBLINGS> ChangeOfSibling = SB.getSiblingJCO2(Integer.parseInt(jco_id));
			
 		 
			Mmap.put("msg", msg);			
			Mmap.put("personnel_no2", personnel_no2);
			Mmap.put("jco_id", jco_id);
			Mmap.put("event", event);
		   
			 event1 = event;
			
			Mmap.put("msg", msg);
			Mmap.put("getCourseNameList", p_comm.getCourseNameList());
			Mmap.put("getGenderList", p_comm.getGenderList());
			Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());
			Mmap.put("getRegtList", p_comm.getRegtList(""));
			Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
			Mmap.put("getPersonalRemainder", p_comm.getPersonalRemainder());
			Mmap.put("getParentArmList", p_comm.getParentArmList());
			Mmap.put("getArmyType", p_comm.getArmyType());
			Mmap.put("getMedCountryName", p_comm.getMedCountryName("", session));
			Mmap.put("getMedStateName", p_comm.getMedStateName("", session));
			Mmap.put("getMedDistrictName", p_comm.getMedDistrictName("", session));
			Mmap.put("getNationalityList", p_comm.getNationalityList());
			Mmap.put("getMothertoungeList", p_comm.getMothertoungeList());
			Mmap.put("getBloodList", p_comm.getBloodList());
			Mmap.put("getHeight", p_comm.getHeight());
			Mmap.put("getClass_enrollList", jcom.getClass_enrollList());
			Mmap.put("getRankjcoList", jcom.getRankjcoList());
			Mmap.put("getExservicemenList", p_comm.getExservicemenList());
			Mmap.put("getProfession", p_comm.getProfession());
			Mmap.put("getFamily_siblings", p_comm.getFamily_siblings());
			Mmap.put("getRank_intakeList", jcom.getRank_intakeList());
			Mmap.put("getclass_domicileList", jcom.getclass_domicileList());
			
			Date date = new Date();

			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

			Mmap.put("date", date1);
			
			Mmap.put("army_no7", army_no7);
			Mmap.put("status7", status7);
			Mmap.put("rank7", rank7);
			Mmap.put("jco_id7", jco_id7);
			Mmap.put("unit_name7", unit_name7);
			Mmap.put("unit_sus_no7", unit_sus_no7);
			
			Mmap.put("ChangeOfDOB", ChangeOfDOB.size());
			Mmap.put("ChangeOfGender", ChangeOfGender.size());
			Mmap.put("ChangeOfAddressBirth", ChangeOfAddressBirth.size());
			Mmap.put("ChangeOfNationality", ChangeOfNationality.size());
			Mmap.put("ChangeOfMotherTongue", ChangeOfMotherTongue.size());
			Mmap.put("ChangeOfBloodGroup", ChangeOfBloodGroup.size());
			Mmap.put("ChangeOfHeight", ChangeOfHeight.size());
			Mmap.put("ChangeOfAadhar", ChangeOfAadhar.size());
			Mmap.put("ChangeOfPanNo", ChangeOfPanNo.size());
			Mmap.put("ChangeOfClassEnroll", ChangeOfClassEnroll.size());
//			Mmap.put("ChangeOfRank", ChangeOfRank.size());
			Mmap.put("ChangeOfDtofEnrolment", ChangeOfDtofEnrolment.size());
			Mmap.put("ChangeOfDtofAttestation", ChangeOfDtofAttestation.size());
			Mmap.put("ChangeOfFamily", ChangeOfFamily.size());
			Mmap.put("ChangeOfSibling", ChangeOfSibling.size());
 		 
 		 return new ModelAndView("Reject_Update_CensusJCO_DataTiles");
 	 }
	
	
	@RequestMapping(value = "/Submit_Reject_Upadte_Census_JCODataUrl" , method = RequestMethod.POST)
	public @ResponseBody String Submit_Reject_Upadte_Census_JCODataUrl(String jco_id,
		
			@RequestParam(value = "msg", required = false) String msg,
			Authentication authentication,HttpServletRequest request,ModelMap Mmap,HttpSession session)   {
	
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String username = session.getAttribute("username").toString();
	 
		    List<TB_PSG_UPDATE_CENSUS_GENDER_JCO> ChangeOfGender =  GU.getgenderDataJCO2(Integer.parseInt(jco_id));
		    
		    List<TB_PSG_UPDATE_CENSUS_BIRTH_JCO> ChangeOfDOB =  DB.getDOBDataJCO2(Integer.parseInt(jco_id));
		    
			List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> ChangeOfAddressBirth = AB.getAddBirthDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO> ChangeOfNationality = NT.getNationalityDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO> ChangeOfMotherTongue = MT.getMother_TongDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO> ChangeOfBloodGroup = BG.getBlood_GroupDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_HEIGHT_JCO> ChangeOfHeight = HE.getHeightDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> ChangeOfAadhar = ADN.getAdd_NoDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_PAN_NO_JCO> ChangeOfPanNo = PN.getPan_NoDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO> ChangeOfClassEnroll = EC.getClass_EnrollDataJCO2(Integer.parseInt(jco_id));
			
//			List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> ChangeOfRank = RN.getRankDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO> ChangeOfDtofEnrolment = DE.getDt_of_EnrollDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> ChangeOfDtofAttestation = DA.getDT_Of_AttesDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> ChangeOfFamily = FD.getFamilyDataJCO2(Integer.parseInt(jco_id));
			
			List<TB_CENSUS_JCO_OR_SIBLINGS> ChangeOfSibling = SB.getSiblingJCO2(Integer.parseInt(jco_id));
		  
	   if(ChangeOfGender.size() > 0) {
		   
		   String hql = "update TB_PSG_UPDATE_CENSUS_GENDER_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  jco_id=:id and status='3'";
			Query query = sessionhql.createQuery(hql)
					.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);

			msg = query.executeUpdate() > 0 ? "update" : "0";
	   }
	   
	   if(ChangeOfDOB.size() > 0) {
		   
		   String hql = "update TB_PSG_UPDATE_CENSUS_BIRTH_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  jco_id=:id and status='3'";
			Query query = sessionhql.createQuery(hql)
					.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);

			msg = query.executeUpdate() > 0 ? "update" : "0";
	   }
	   
	 if(ChangeOfAddressBirth.size() > 0) {
			   
			   String hql = "update TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);
	
				msg = query.executeUpdate() > 0 ? "update" : "0";
	 }
 
	 if(ChangeOfNationality.size() > 0) {
		   
		   String hql = "update TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  jco_id=:id and status='3'";
			Query query = sessionhql.createQuery(hql)
					.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);
	
			msg = query.executeUpdate() > 0 ? "update" : "0";
	 }
 
	 if(ChangeOfMotherTongue.size() > 0) {
		   
		   String hql = "update TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  jco_id=:id and status='3'";
			Query query = sessionhql.createQuery(hql)
					.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);
	
			msg = query.executeUpdate() > 0 ? "update" : "0";
	 }
 
	 if(ChangeOfBloodGroup.size() > 0) {
		   
		   String hql = "update TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  jco_id=:id and status='3'";
			Query query = sessionhql.createQuery(hql)
					.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);
	
			msg = query.executeUpdate() > 0 ? "update" : "0";
	 }
 
	 if(ChangeOfHeight.size() > 0) {
		   
		   String hql = "update TB_PSG_UPDATE_CENSUS_HEIGHT_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  jco_id=:id and status='3'";
			Query query = sessionhql.createQuery(hql)
					.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);
	
			msg = query.executeUpdate() > 0 ? "update" : "0";
	 }
 
	 if(ChangeOfAadhar.size() > 0) {
		   
		   String hql = "update TB_PSG_UPDATE_CENSUS_AADHARNO_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  jco_id=:id and status='3'";
			Query query = sessionhql.createQuery(hql)
					.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);
	
			msg = query.executeUpdate() > 0 ? "update" : "0";
	 }
	   
	   if(ChangeOfPanNo.size() > 0) {
		   
		   String hql = "update TB_PSG_UPDATE_CENSUS_PAN_NO_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  jco_id=:id and status='3'";
			Query query = sessionhql.createQuery(hql)
					.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);

			msg = query.executeUpdate() > 0 ? "update" : "0";
	   }
	   
	 if(ChangeOfClassEnroll.size() > 0) {
			   
			   String hql = "update TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where  jco_id=:id and status='3'";
				Query query = sessionhql.createQuery(hql)
						.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);
	
				msg = query.executeUpdate() > 0 ? "update" : "0";
	 }

//	 if(ChangeOfRank.size() > 0) {
//		   
//		   String hql = "update TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
//					+ " where  jco_id=:id and status='3'";
//			Query query = sessionhql.createQuery(hql)
//					.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
//					.setTimestamp("modified_date", new Date()).setInteger("status", 0);
//	
//			msg = query.executeUpdate() > 0 ? "update" : "0";
//	 }

	 if(ChangeOfDtofEnrolment.size() > 0) {
		   
		   String hql = "update TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  jco_id=:id and status='3'";
			Query query = sessionhql.createQuery(hql)
					.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);
	
			msg = query.executeUpdate() > 0 ? "update" : "0";
	 }

	 if(ChangeOfDtofAttestation.size() > 0) {
		   
		   String hql = "update TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  jco_id=:id and status='3'";
			Query query = sessionhql.createQuery(hql)
					.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);
	
			msg = query.executeUpdate() > 0 ? "update" : "0";
	 }

	 if(ChangeOfFamily.size() > 0) {
		   
		   String hql = "update TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  jco_id=:id and status='3'";
			Query query = sessionhql.createQuery(hql)
					.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);
	
			msg = query.executeUpdate() > 0 ? "update" : "0";
	 }

	 if(ChangeOfSibling.size() > 0) {
		   
		   String hql = "update TB_CENSUS_JCO_OR_SIBLINGS set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  jco_id=:id and status='3'";
			Query query = sessionhql.createQuery(hql)
					.setInteger("id", Integer.parseInt(jco_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);
	
			msg = query.executeUpdate() > 0 ? "update" : "0";
	 }
	   	
	   	msg = jcom.update_JcoOr_Census_status(Integer.parseInt(jco_id),username);
	   tx.commit();
	    
	return msg;
 }

}
