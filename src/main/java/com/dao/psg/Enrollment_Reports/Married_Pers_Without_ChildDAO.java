package com.dao.psg.Enrollment_Reports;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Married_Pers_Without_ChildDAO {

	long getmarried_pers_without_child_tablecount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String posn_date, String typeID, String rankID, String rankjcoID);

	List<Map<String, Object>> getmarried_pers_without_child_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String posn_date, String typeID, String rankID, String rankjcoID);

	long getmarried_pers_without_child_tablecountsum(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String posn_date, String typeID, String rankID, String rankjcoID);

	List<Map<String, Object>> getmarried_pers_without_child_tablesum(int startPage, int pageLength,
			String search, String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd,
			String cont_corps, String cont_div, String cont_bde, String unit_name, String unit_sus_no, String posn_date, String typeID, String rankID, String rankjcoID);

}
