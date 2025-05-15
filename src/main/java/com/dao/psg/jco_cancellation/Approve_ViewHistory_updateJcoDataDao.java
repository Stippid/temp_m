package com.dao.psg.jco_cancellation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Approve_ViewHistory_updateJcoDataDao {

	public String approveHisChangeOfRank(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisChangeOfName(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisChangeOfAppointment(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisIdentity_Card(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisChangeOfReligion(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisfamily_married(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date) ;
	public String approveHisSpouseQualification(int comm_id, List<Map<String, Object>> Lobj,String username,Date mod_date);
	public String approveHisChild(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisNokAddress(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date) ;
	public String approveHisAddress(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisContactDetails(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisLanguageDetails(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
//	public String approveHisForeignLang(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisQualification(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date) ;
	public String approveHisPromotionalExam(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisArmyCourse(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisBpetDetails(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisFiringDetails(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisAllergy(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisMedicalCategory(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisForeignCountry(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisAwardAndMedal(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisBattle_physical_casuality(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisDiscipline(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisInter_arm_service_transfer(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date) ;
	public String approveHisNon_effective(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date);
	public String approveHisDeserter(int comm_id,List<Map<String, Object>> Lobj, String username,Date mod_date) ;
	public String approveHisCSD(int parseInt, List<Map<String, Object>> Lobj, String username, Date mod_date);
	public String approveHisTrade(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date);
	public String approveHisClassPay(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date);
	public String approveHisPayGroup(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date);
	public String approveHisSeniority(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date);
	public String approveHisAttachmentDetails(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date);
	
	public String approveHisChangeOfposting(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date);

}