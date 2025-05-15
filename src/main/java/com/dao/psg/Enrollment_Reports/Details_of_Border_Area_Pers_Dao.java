package com.dao.psg.Enrollment_Reports;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Details_of_Border_Area_Pers_Dao {

	long Details_of_Border_Area_Pers_Count(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String state_id,String district_id,String off_cat);

	List<Map<String, Object>> Details_of_Border_Area_PersList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, 
			String state_id,String district_id,String off_cat);
	
	long Details_of_Border_Area_Pers_Summary_Count(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no,String state_id,String district_id,String off_cat);

	List<Map<String, Object>> Details_of_Border_Area_Pers_Summary_List(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,String state_id,String district_id,
			String off_cat);
	
}
