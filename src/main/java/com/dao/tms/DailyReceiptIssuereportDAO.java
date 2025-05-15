package com.dao.tms;

import java.util.ArrayList;
import java.util.List;

public interface DailyReceiptIssuereportDAO {
	
	public Boolean ifExistSusAndSer(String sus_no, String drr_dir_ser_no);
	public Boolean ifExistSusAndSerDtl(String a, String ba_no);
	public Boolean ifExistMVCRBaNo(String ba_no);
	public Boolean ifExistSusAndSerAndBANo(String sus_no, String drr_dir_ser_no, String ba_no);
	public ArrayList<List<String>> getsearch_drr_b_Veh( String status, String sus_no, String from_date,String curr_date, String roleType,String ba_no);
	public  ArrayList<List<String>> getPendingBA_NoFromDRR(String viewSerNo,String viewStatus,String viewDate);
	 public Boolean ifExistC_VEHBaNo(String em_no);
		public Boolean ifExistA_VEHBaNo(String ba_no);
		public List<String> getUseridBysusfordepo(String sus_no);
		public List<String> getUseridBysusforlinedte(String sus_no);

}
