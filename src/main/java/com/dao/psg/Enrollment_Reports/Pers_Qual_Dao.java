package com.dao.psg.Enrollment_Reports;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Pers_Qual_Dao {
	public List<Map<String, Object>> getsearch_qualification_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String typeID, String army_course_name1, String army_course_div_grade1, String posn_date);
	public long getsearch_qualification_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String typeID, String army_course_name1, String army_course_div_grade1, String posn_date);
	
	public List<Map<String, Object>> getsearch_qualification_course_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String typeID, String army_course_name1, String army_course_div_grade1, String posn_date);
	public long getsearch_qualification_course_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String typeID, String army_course_name1, String army_course_div_grade1, String posn_date);
		
	 
}