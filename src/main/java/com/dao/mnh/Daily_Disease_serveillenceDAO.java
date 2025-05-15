package com.dao.mnh;


import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_Daily_Disease_Surv_Details;


public interface Daily_Disease_serveillenceDAO {

	 public Long checkExitstingSURExistlNo(String sus_no1,String persnl_no,String dt_admission,int id);
	 public List<Map<String, Object>> search_daily_activity_data(String sus1, String frm_dt1, String to_dt1,String adhar1,String contact1) ;
	 public Tb_Med_Daily_Disease_Surv_Details updatedaily_diseaseByid(int id);
	 public String updatedailysdisease(Tb_Med_Daily_Disease_Surv_Details obj,String username);
		public Long checkExitstingSURExistlNo_nil(String sus_no1,int id);
}
