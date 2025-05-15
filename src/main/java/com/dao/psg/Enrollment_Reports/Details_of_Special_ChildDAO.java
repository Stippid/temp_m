package com.dao.psg.Enrollment_Reports;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Details_of_Special_ChildDAO {

	long getspecial_child_tablecount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String from_date, String to_date, String posn_date, String typeID, String rankID, String rankjcoID);

	List<Map<String, Object>> getspecial_child_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String from_date, String to_date,
			String posn_date, String typeID, String rankID, String rankjcoID);

	long getspecial_child_tablecountsum(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String from_date, String to_date, String posn_date, String typeID, String rankID, String rankjcoID);

	List<Map<String, Object>> getspecial_child_tablesum(int startPage, int pageLength,
			String search, String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd,
			String cont_corps, String cont_div, String cont_bde, String unit_name, String unit_sus_no, String from_date,
			String to_date, String posn_date, String typeID, String rankID, String rankjcoID);
	
	long getspecial_child_jco_tablecountsum(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String from_date, String to_date, String posn_date, String typeID, String rankID, String rankjcoID);

	List<Map<String, Object>> getspecial_child_jco_tablesum(int startPage, int pageLength,
			String search, String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd,
			String cont_corps, String cont_div, String cont_bde, String unit_name, String unit_sus_no, String from_date,
			String to_date, String posn_date, String typeID, String rankID, String rankjcoID);

}
