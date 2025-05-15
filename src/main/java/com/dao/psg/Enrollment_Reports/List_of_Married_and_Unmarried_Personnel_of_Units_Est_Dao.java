package com.dao.psg.Enrollment_Reports;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface List_of_Married_and_Unmarried_Personnel_of_Units_Est_Dao {

	long List_of_Married_and_Unmarried_Personnel_of_Units_Est_Count(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no,String off_cat,String marital_status, String posn_date);

	List<Map<String, Object>> List_of_Married_and_Unmarried_Personnel_of_Units_EstList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, 
			String off_cat,String marital_status, String posn_date);
	
	long List_of_Married_and_Unmarried_Personnel_of_Units_Est_Summary_Count(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no,String off_cat,String marital_status, String posn_date);

	List<Map<String, Object>> List_of_Married_and_Unmarried_Personnel_of_Units_Est_Summary_List(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			String off_cat,String marital_status, String posn_date);
	
}