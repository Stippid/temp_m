package com.dao.psg.Enrollment_Reports;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Pers_Foreign_LanguageDAO {

	long getsearch_foreign_language_tablecount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String languageID, String stdID, String rankID, String rankjcoID, String typeID, String posn_date);

	List<Map<String, Object>> getsearch_foreign_language_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String languageID, String stdID,
			String rankID, String rankjcoID, String typeID, String posn_date);

	long getsearch_foreign_language_tablecountsum(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String languageID, String stdID,String rankID, String rankjcoID, String typeID, String posn_date);

	List<Map<String, Object>> getsearch_foreign_language_tablesum(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String languageID, String stdID,String rankID, String rankjcoID, String typeID, String posn_date);

}
