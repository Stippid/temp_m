package com.dao.psg.Enrollment_Reports;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Pers_Service_Dao {
	public List<Map<String, Object>> getsearch_service_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String rankID, 
			String rankJcoID, String serving, String typeID, String posn_date);
	public long getsearch_service_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,
			String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String rankID, 
			String rankJcoID, String serving, String typeID,String posn_date);
	
	public List<Map<String, Object>> getsearch_service_summery_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String rankID, 
			String rankJcoID, String serving, String typeID, String posn_date);
	public long getsearch_service_summery_tablecount(String Search,String orderColunm,String orderType,
			HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String rankID, 
			String rankJcoID,String serving, String typeID, String posn_date);
		
	 
}