package com.dao.mnh;

import java.util.ArrayList;

import com.models.mnh.Tb_Med_Daily_Surv_Disease_Mstr;




public interface mstr_Daily_Disease_SVLDao {
	
	public String update_daily_dSurve(Tb_Med_Daily_Surv_Disease_Mstr obj);
	public ArrayList<ArrayList<String>> SearchDailyDiseaseSvl(String disease_name,String disease_type,String status);
	

}
