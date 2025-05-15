package com.dao.mnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MNH_RemindersDAO {
	
	public List<Map<String, Object>> search_reminder_input(String dt_frm,String dt_to,String qtr,String tbl_name,String cmd1,String yr1);
	
	public List<Map<String, Object>> search_reminder_discharge(String sus1,String cmd1,String dt_frm,String dt_to);
	
	//==
	public List<Map<String, Object>> search_scrutiny_discharge(String dschrg_date1,String disposal1,String diagnosis_code1a1,String icd_cause_code1a1,String discharge_remarks1);
	public ArrayList<ArrayList<String>> Search_discharge(String sus1, String cmd1, String dt_frm, String dt_to);


}
