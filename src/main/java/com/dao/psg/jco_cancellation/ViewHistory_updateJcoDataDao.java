package com.dao.psg.jco_cancellation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface ViewHistory_updateJcoDataDao {
	public List<Map<String, Object>> getHisChangeOfRank(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisChangeOfName(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisChangeOfAppointment(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisIdentity_Card(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisChangeOfReligion(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisfamily_married(int jco_id,int status,HttpSession session) ;
	public List<Map<String, Object>> getHisSpouseQualification(int spouse_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisPendingSpouseQualification(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisChild(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisNokAddress(int jco_id,int status,HttpSession session) ;
	public List<Map<String, Object>> getHisAddress(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisContactDetails(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisLanguageDetails(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisForeignLang(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisQualification(int jco_id,int status,HttpSession session) ;
	public List<Map<String, Object>> getHisPromotionalExam(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisArmyCourse(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisBpetDetails(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisFiringDetails(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisAllergy(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisMedicalCategory(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisForeignCountry(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisAwardAndMedal(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisBattle_physical_casuality(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisDiscipline(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisInter_arm_service_transfer(int jco_id,int status,HttpSession session) ;
	public List<Map<String, Object>> getHisNon_effective(int jco_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisDeserter(int jco_id,int status,HttpSession session) ;
	public ArrayList<ArrayList<String>> madical_cat_HisGetData(String id);
	public List<Map<String, Object>> getHisCSD(int jco_id, int status, HttpSession session);
	public List<Map<String, Object>> getHisSeniority(int jco_id, int status, HttpSession session);
	public List<Map<String, Object>> getHisPayGroup(int jco_id, int status, HttpSession session);
	public List<Map<String, Object>> getHisPayClass(int jco_id, int status, HttpSession session);
	public List<Map<String, Object>> getHisTrade(int jco_id, int status, HttpSession session);
	public List<Map<String, Object>> getHisAttachmentDetails(int jco_id, int status, HttpSession session);
	public List<Map<String, Object>> GetJcoOrCensusDataCancelHistory(int jco_id);
	public List<Map<String, Object>> getHisChangeOfpost(int jco_id,int status,HttpSession session);
	
	
}
