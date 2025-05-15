package com.dao.formation_report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Formation_reportsDAO {
	
	
	public List<Map<String, Object>> getArrivalDepartureTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException;
	
	public long getArrivalDepartureTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException;
	
	public long getreorg_conv_disdTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException ;
	
	public List<Map<String, Object>> getreorg_conv_disdTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException ;

	public long get_revision_amdtTable_count(String search, HttpSession sessionUserId, String fromDate,
			String fromDate2) throws SQLException;

	public List<Map<String, Object>> get_revision_amdtTable_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate) throws SQLException;

	public long get_tms_new_relTable_count(String search, HttpSession sessionUserId, String fromDate, String fromDate2) throws SQLException;

	public List<Map<String, Object>> get_tms_new_relTable_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate) throws SQLException;

	public long get_veh_meeting_discard_conTable_count(String search, HttpSession sessionUserId, String fromDate,
			String fromDate2) throws SQLException;

	public List<Map<String, Object>> get_veh_meeting_discard_conTable_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate)throws SQLException;

	public List<Map<String, Object>> get_tms_iut_dtlTable_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate)throws SQLException;
	public long get_tms_iut_dtlTable_count(String search, HttpSession sessionUserId, String fromDate, String fromDate2) throws SQLException;

	public long get_tms_bkld_veh_dtlTable_count(String search, HttpSession sessionUserId, String fromDate,
			String fromDate2)throws SQLException;

	public List<Map<String, Object>> get_tms_bkld_veh_dtlTable_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate)throws SQLException;

	public long get_tms_updation_stateTable_count(String search, HttpSession sessionUserId, String fromDate,
			String fromDate2)throws SQLException;

	public List<Map<String, Object>> get_tms_updation_stateTable_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate)throws SQLException;

	public long get_tms_hld_defiTable_count(String search, HttpSession sessionUserId, String fromDate,
			String fromDate2)throws SQLException;

	public List<Map<String, Object>> get_tms_hld_defiTable_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate)throws SQLException;

	public long get_tms_oh_mr_due_stateTable_count(String search, HttpSession sessionUserId, String fromDate,
			String fromDate2)throws SQLException;

	public List<Map<String, Object>> get_tms_oh_mr_due_stateTable_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate)throws SQLException;

	public long get_mms_new_reltable_count(String search, HttpSession sessionUserId, String fromDate, String fromDate2) throws SQLException;

	public List<Map<String, Object>> get_mms_new_reltable_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate)throws SQLException;

	public long get_cont_store_srvc_conTable_count(String search, HttpSession sessionUserId, String fromDate,
			String fromDate2)throws SQLException;

	public List<Map<String, Object>> get_cont_store_srvc_conTable_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate)throws SQLException;

	public long get_mms_iut_dtlTbale_count(String search, HttpSession sessionUserId, String fromDate, String fromDate2) throws SQLException;

	public List<Map<String, Object>> get_mms_iut_dtlTbale_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate)throws SQLException;

	public long get_mms_bkld_dtlTbale_count(String search, HttpSession sessionUserId, String fromDate,
			String fromDate2)throws SQLException;

	public List<Map<String, Object>> get_mms_bkld_dtlTbale_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate)throws SQLException;

	public long get_mms_updation_stateTable_count(String search, HttpSession sessionUserId, String fromDate,
			String fromDate2)throws SQLException;

	public List<Map<String, Object>> get_mms_updation_stateTable_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate)throws SQLException;

	public long get_mms_defi_stateTable_count(String search, HttpSession sessionUserId, String fromDate,
			String fromDate2)throws SQLException;

	public List<Map<String, Object>> get_mms_defi_stateTable_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate)throws SQLException;


	
}
