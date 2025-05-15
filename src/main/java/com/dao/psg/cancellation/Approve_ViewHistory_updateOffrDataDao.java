package com.dao.psg.cancellation;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Approve_ViewHistory_updateOffrDataDao {

	public String approveHisChangeOfRank(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisChangeOfName(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisChangeOfAppointment(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisIdentity_Card(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisChangeOfReligion(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisfamily_married(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date) ;
	public String approveHisSpouseQualification(BigInteger comm_id, List<Map<String, Object>> Lobj,String username,Date mod_date);
	public String approveHisChild(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisNokAddress(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date) ;
	public String approveHisAddress(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisContactDetails(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisLanguageDetails(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
//	public String approveHisForeignLang(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisQualification(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date) ;
	public String approveHisPromotionalExam(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisArmyCourse(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisBpetDetails(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisFiringDetails(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisAllergy(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisMedicalCategory(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisForeignCountry(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisAwardAndMedal(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisBattle_physical_casuality(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisDiscipline(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisSsc_to_permt_commission(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisInter_arm_service_transfer(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date) ;
	public String approveHisExtension_of_ssc(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisNon_effective(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisSecondment(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date) ;
	public String approveHisDeserter(BigInteger comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date) ;
	public String approveHisCSD(BigInteger parseInt, List<Map<String, Object>> Lobj, String username, Date mod_date);

}