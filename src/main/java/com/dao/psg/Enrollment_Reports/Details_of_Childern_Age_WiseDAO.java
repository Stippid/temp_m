package com.dao.psg.Enrollment_Reports;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Details_of_Childern_Age_WiseDAO {

	long getdetails_of_children_tablecount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String from_date, String to_date, String posn_date, String typeID, String gender);

	List<Map<String, Object>> getdetails_of_children_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String from_date, String to_date,
			String posn_date, String typeID, String gender);

	long getdetails_of_children_tablecountsum(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String from_date, String to_date, String posn_date, String typeID, String gender);

	List<Map<String, Object>> getdetails_of_children_tablesum(int startPage, int pageLength,
			String search, String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd,
			String cont_corps, String cont_div, String cont_bde, String unit_name, String unit_sus_no, String from_date,
			String to_date, String posn_date, String typeID, String gender);
	
	long getdetails_of_children_jco_tablecountsum(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String from_date, String to_date, String posn_date, String typeID, String gender);

	List<Map<String, Object>> getdetails_of_children_jco_tablesum(int startPage, int pageLength,
			String search, String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd,
			String cont_corps, String cont_div, String cont_bde, String unit_name, String unit_sus_no, String from_date,
			String to_date, String posn_date, String typeID, String gender);

}
