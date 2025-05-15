package com.dao.tms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AVehDailyReceiptIssuereportDAO {
	
	public Boolean ifExistSerAndSusDrr(String sus_no, String drr_dir_ser_no, Date creation_date);
	public Boolean ifExistSusAndSerDtNIssueType(String a, String ba_no,String issue_type);
    public Boolean ifExistSusAndSer(String sus_no, String drr_dir_ser_no);
	public Boolean ifExistSusAndSerDtl(String a, String ba_no);
	public Boolean ifExistCensusRetrnBaNo(String ba_no);
	public List<String> getACensusVehSusBaNoDtlListCase2AgaiIssue(String sus_no,String ba_no);
	public Boolean ifExistSusAndSerAndBANo(String sus_no, String drr_dir_ser_no, String ba_no,String issue_condition); 
	public  ArrayList<List<String>> getsearch_drr_a_Veh(String status,String sus_no,String from_date,String curr_date,String roleType);
	public  ArrayList<List<String>> getApprovedBA_NoFromDRRAVehicle(String viewSerNo,String viewStatus,String viewDate,String viewSus,String roleType);
	public List<Map<String, String>> get_A_vech_daily(String sus_no_aprove, String ser_no_approve) ;
}
