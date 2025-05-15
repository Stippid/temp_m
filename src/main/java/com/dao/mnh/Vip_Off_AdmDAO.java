package com.dao.mnh;

import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_Daily_Hosp_Adm_Off_Vip;



public interface Vip_Off_AdmDAO {
	
	public Long checkExitstingvipExist(String sus_no1,String persnl_name,String dt_admission,String id);
	public Tb_Med_Daily_Hosp_Adm_Off_Vip getvipadm(int id);
	public String update_vip_adm(Tb_Med_Daily_Hosp_Adm_Off_Vip obj,String username);
	public List<Map<String, Object>> search_vip_adm(String sus1, String frm_dt1, String to_dt1,String adhar1,String contact1) ;

}
