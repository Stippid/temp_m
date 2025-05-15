package com.dao.itasset.Report;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Assests_Serviceablity_details_DAO {

	public ArrayList<ArrayList<String>> Search_Assets_Serviceablity_Details(String service_state, String asset_type,String unservice_state,HttpSession session);
	public ArrayList<ArrayList<String>> pdf_Computing_Assets_ReportDataList(HttpSession session,String service_state, String asset_type,String unservice_state);
	public ArrayList<ArrayList<String>> Search_Assets_Peripherals_Details(String service_state,String asset_type, String unservice_state,HttpSession session);

	public ArrayList<ArrayList<String>> pdf_Peripherals_Assets_ReportDataList(HttpSession session,String service_state, String asset_type,String unservice_state);
	public ArrayList<ArrayList<String>> excel_Computing_Assets_ReportDataList(HttpSession session,String service_state, String asset_type,String unservice_state);
	public ArrayList<ArrayList<String>> excel_Peripheral_Assets_ReportDataList(HttpSession session,String service_state, String asset_type,String unservice_state);

	//	public ArrayList<ArrayList<String>> Audit_Report(String asset_type, HttpSession session);
	List<Map<String, Object>> getAuditReportData(int startPage, int pageLength, String search, String orderColunm, String orderType, String asset_type, HttpSession sessionUserId);
	long getAuditReportCount(String search, String orderColunm, String orderType, String asset_type, HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> pdf_AuditReport(HttpSession session, String asset_type);
	public ArrayList<ArrayList<String>> excel_AuditReport(HttpSession session, String asset_type);
}
