package com.dao.psg.Enrollment_Reports;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Arm_Service_Dao {
	public List<Map<String, Object>> getsearch_arm_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String typeID, String from_date, String to_date);
	public long getsearch_arm_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,
			String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String typeID, String from_date, String to_date);
	
	public List<Map<String, Object>> getsearch_arm_summery_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,String typeID, String from_date, String to_date);
	public long getsearch_arm_summery_tablecount(String Search,String orderColunm,String orderType,
			HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no,  String typeID, String from_date, String to_date);
		
	 
}