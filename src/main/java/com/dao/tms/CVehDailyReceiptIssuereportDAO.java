package com.dao.tms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CVehDailyReceiptIssuereportDAO {
	public Boolean ifExistSusAndSer(String sus_no, String drr_dir_ser_no);
	public Boolean ifExistSusAndSerDtl(String a, String em_no);
	public Boolean ifExistEmarEMNo(String em_no);
	public Boolean ifExistSusAndSerAndEMNo(String sus_no, String drr_dir_ser_no, String em_no,String iss_cond); 
	public  ArrayList<List<String>> getsearch_drr_c_Veh(String status,String sus_no,String from_date,String curr_date,String roleType);
	public List<String> getCEmarVehSusBaNoDtlListCase2AgaiIssue(String sus_no);
	public Boolean ifExistSusAndSerDtNIssueType(String a, String ba_no,String issue_type);
	public  ArrayList<List<String>> getApprovedBA_NoFromDRRCVehicle(String viewSerNo,String viewStatus,String viewDate,String viewSus,String roleType);
	public List<Map<String, String>> get_C_vech_daily(String sus_no_aprove, String ser_no_approve); 
}
