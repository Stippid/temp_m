package com.dao.psg.Enrollment_Reports;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Age_Wise_DtlsDAO {

	long getage_wise_dtls_tablecount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId,  String cont_comd, String cont_corps, String cont_div, String cont_bde, 
			String unit_name, String unit_sus_no, String from_date, String to_date, String typeID, String posn_date, String servingID);

	List<Map<String, Object>> getage_wise_dtls_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId,  String cont_comd, String cont_corps, 
			String cont_div, String cont_bde,String unit_name, String unit_sus_no, String from_date, String to_date, String typeID, String posn_date, String servingID);

	long getage_wise_dtls_tablecountsum(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String from_date, String to_date, String typeID, String posn_date, String servingID);

	List<Map<String, Object>> getage_wise_dtls_tablesum(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String from_date, String to_date, String typeID, String posn_date, String servingID);
	
}
