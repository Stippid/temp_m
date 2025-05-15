package com.dao.mnh;

import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_Daily_Bed_Occupancy;



public interface Daily_morning_bed_stateDAO {
	
	 public Long checkExitstingbedExistlNo(String sus_no1,String dt,int id);
	 public List<Map<String, Object>> search_morning_bed_status_details(String sus1, String frm_dt1, String to_dt1);
	 public Tb_Med_Daily_Bed_Occupancy updatemorning_bed_statusByid(int id);
	 public String updatemornibedstate(Tb_Med_Daily_Bed_Occupancy obj,String username);

}
