package com.dao.psg.Enrollment_Reports;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Edn_Qual_Spouses_Dao {
	public List<Map<String, Object>> getsearch_qualification_spouses_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String typeID, String examination);
	
	public long getsearch_qualification_spouses_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String typeID, String examination);
	
	public List<Map<String, Object>> getsearch_qualification_spouses_summary_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String typeID, String examination);
	
	public long getsearch_qualification_spouses_summary_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String typeID, String examination );
		
	 
}