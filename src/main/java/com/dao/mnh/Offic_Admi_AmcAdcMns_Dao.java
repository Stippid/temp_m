package com.dao.mnh;
import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_Daily_Hosp_Adm_Amc;



public interface Offic_Admi_AmcAdcMns_Dao {
	
	 public Long checkExitstingadmExistlNo(String sus_no1,String persnl_name,String dt_admission,int id);
		public List<Map<String, Object>> SearchOffi_Admi_AmcAdcMns(String sus_no, String frm_dt1, String to_dt1,String adhar1,String contact1);
	 public Tb_Med_Daily_Hosp_Adm_Amc getOffi_Admi_AmcAdcMnsByid(int id);
	 public String update_Offic_Admi_AmcAdcMns(Tb_Med_Daily_Hosp_Adm_Amc obj,String username);

}
