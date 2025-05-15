package com.dao.HTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface sdReliefProgramDAO {

	List<Map<String, Object>> getVehDeyailsTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, String sus_no, String veh_type, HttpSession sessionUserId);

	long getVehDeyailsTableCount(String search, String orderColunm, String orderType, String sus_no,String veh_type,
			HttpSession sessionUserId);



	List<Map<String, Object>> getPersonnelDetailsTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, String sus_no, HttpSession sessionUserId);

	long getPersonnelDetailsCount(String search, String orderColunm, String orderType, String sus_no,
			HttpSession sessionUserId);
	
	List<Map<String, Object>> getOCPrepareTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, String sus_no, String veh_type, String voucher_status, HttpSession sessionUserId);

	long getOCPrepareCount(String search, String orderColunm, String orderType, String sus_no,String veh_type,
			String voucher_status, HttpSession sessionUserId);

	
	ArrayList<ArrayList<String>> getViewVoucherUnits(String sus_no, HttpSession sessionUserId);

	ArrayList<ArrayList<String>> getViewVoucherData(String sus_no, String ba_no, HttpSession sessionUserId);
	

}
