package com.dao.psg.Enrollment_Reports;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Pers_Visited_Foreign_CountryDAO {

	long getpers_foreign_visited_country_tablecount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId,  String cont_comd, String cont_corps, String cont_div, String cont_bde, 
			String unit_name, String unit_sus_no, String from_date, String to_date, String typeID);

	List<Map<String, Object>> getpers_foreign_visited_country_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId,  String cont_comd, String cont_corps, 
			String cont_div, String cont_bde,String unit_name, String unit_sus_no, String from_date, String to_date, String typeID);

	long getpers_foreign_visited_country_tablecountsum(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String from_date, String to_date, String typeID);

	List<Map<String, Object>> getpers_foreign_visited_country_tablesum(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String from_date, String to_date, String typeID);
	
}
