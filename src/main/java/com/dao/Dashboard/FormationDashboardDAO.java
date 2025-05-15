package com.dao.Dashboard;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface FormationDashboardDAO {
	
	public List<Map<String, Object>> getTptSummaryInPrfWithTypeVehData(HttpSession sessionA,int type,String prf_code);
	
	public List<Map<String, Object>> getVehWiseAvgKMSData(int type,String form_code,String prf_code,HttpSession sessionA);
	
	///////////////////////
	public List<Map<String, Object>> getCommWiseUnitMovFormation(String roleFormationNo,Date fromDate,Date toDate,HttpSession sessionA);
	public List<Map<String, Object>> getUnitMovReport(String roleFormationNo,int startPage,String pageLength,String Search,String orderColunm,String orderType,
			HttpSession sessionA, String cont_comd, String cont_corps, String cont_div,
			String cont_bde, String to_cont_comd, String to_cont_corps, String to_cont_div, String to_cont_bde,
			String fromDate, String toDate);
	
	public List<Map<String, Object>> getUnitMovReportcount(String roleFormationNo, String search,
			 HttpSession sessionA, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String to_cont_comd, String to_cont_corps, String to_cont_div,
			String to_cont_bde, String fromDate, String toDate);
	public List<Map<String, Object>> getTptSummaryData(String roleAccess,String roleSubAccess,String roleFormationNo,int type);
	
	public List<Map<String, Object>> getTptSummaryInPRFList_nodal_dte(int type,HttpSession session,String line_dte_sus);
	public List<Map<String, Object>> getTptSummaryInPRFList(int type,HttpSession session);
	public List<Map<String, Object>> getPRFWiseTptClassData(HttpSession sessionA,int type,String prf);
	
	public List<Map<String, Object>> getUEManpowerData(HttpSession sessionA);
	public List<Map<String, Object>> getHoldingStateData(HttpSession sessionA,String type);
	
	//MNH
	public List<Map<String, Object>> Hospital_Admissions_Col_Above(String form_code);
	public List<Map<String, Object>> AMC_ADC_MNS_Admissions(String form_code);
	public List<Map<String, Object>> Unusual_Occurrence(String form_code);
	
	public List<Map<String, Object>> getComputerData(HttpSession sessionA );

	public long getUnitReportWpnEqptAuthcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String type_mtrls, String roleSusNo);

	public List<Map<String, Object>> getUnitReportWpnEqptAuth(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String type_mtrls, String roleSusNo);
	
	//peripheral 
	public List<Map<String, Object>> getperipheralData(HttpSession sessionA );

	public long getperipheralcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String type_mtrls, String roleSusNo);

	public List<Map<String, Object>> getperipheral(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String type_mtrls, String roleSusNo);
	public  ArrayList<ArrayList<String>> getunitwithmcrobsn(String formation) ;
	public  ArrayList<ArrayList<String>> getunititobsn(String formation) ;
}
