package com.dao.psg.cancellation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface ViewHistory_updateOffrDataDao {
	public List<Map<String, Object>> getHisChangeOfRank(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisChangeOfName(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisChangeOfAppointment(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisIdentity_Card(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisChangeOfReligion(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisfamily_married(BigInteger comm_id,int status,HttpSession session) ;
	public List<Map<String, Object>> getHisSpouseQualification(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisPendingSpouseQualification(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisChild(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisNokAddress(BigInteger comm_id,int status,HttpSession session) ;
	public List<Map<String, Object>> getHisAddress(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisContactDetails(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisLanguageDetails(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisForeignLang(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisQualification(BigInteger comm_id,int status,HttpSession session) ;
	public List<Map<String, Object>> getHisPromotionalExam(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisArmyCourse(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisBpetDetails(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisFiringDetails(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisAllergy(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisMedicalCategory(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisForeignCountry(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisAwardAndMedal(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisBattle_physical_casuality(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisDiscipline(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisSsc_to_permt_commission(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisInter_arm_service_transfer(BigInteger comm_id,int status,HttpSession session) ;
	public List<Map<String, Object>> getHisExtension_of_ssc(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisNon_effective(BigInteger comm_id,int status,HttpSession session);
	public List<Map<String, Object>> getHisSecondment(BigInteger comm_id,int status,HttpSession session) ;
	public List<Map<String, Object>> getHisDeserter(BigInteger comm_id,int status,HttpSession session) ;
	public ArrayList<ArrayList<String>> madical_cat_HisGetData(String id);
	public List<Map<String, Object>> getHisCSD(BigInteger comm_id, int status, HttpSession session);
}
