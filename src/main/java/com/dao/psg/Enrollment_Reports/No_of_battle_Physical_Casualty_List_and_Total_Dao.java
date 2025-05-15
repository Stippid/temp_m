package com.dao.psg.Enrollment_Reports;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface No_of_battle_Physical_Casualty_List_and_Total_Dao {

	long No_of_battle_Physical_Casualty_List_and_Total_Count(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String off_cat,String from_date,String to_date);

	List<Map<String, Object>> No_of_battle_Physical_Casualty_List_and_TotalList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, 
			String off_cat,String from_date,String to_date);
	
	long No_of_battle_Physical_Casualty_List_and_Total_Summary_Count(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no,String off_cat,String from_date,String to_date);

	List<Map<String, Object>> No_of_battle_Physical_Casualty_List_and_Total_Summary_List(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,String off_cat
			,String from_date,String to_date);
	
}
