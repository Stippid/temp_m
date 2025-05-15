package com.dao.avation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;

import com.models.TB_AVIATION_DAILY_BASIS_HISTORY;

public interface DACRDAO {

	public ArrayList<List<String>> getDACRReportListPending(String sus_no, String roleType);

	public ArrayList<List<String>> UpdategetDACRReportListPending(String qry, String sus_no, String roleType,
			String roleAccess);

	public ArrayList<List<String>> getSearchAttributeReportDacr(String status, String sus_no, String roleType,
			String issue_date);

	public ArrayList<List<String>> getDACRReportListForApproval(String sus_no, String roleType, String issue_date);

	public ArrayList<List<String>> getDACRReportListForApproval1(String sus_no, String roleType, String issue_date);

	public String setApproveddacr(String sus_no, String username, String issue_date);

	public String setRejectdacr(String sus_no, String username);

	public @ResponseBody List<TB_AVIATION_DAILY_BASIS_HISTORY> getApproveDate(String sus_no);

	public List<Map<String, Object>> getFormationDetailsFromSusNo(String sus_no);

	public @ResponseBody List<String> getwepeno(String sus_no);

	// New added by Mitesh 03-12-2024
	public ArrayList<List<String>> UpdategetDACRReportchtlListPending(String qry, String sus_no, String roleType,
			String roleAccess);

	// New added by Mitesh 04-12-2024
	public ArrayList<List<String>> getSearchChtlReportDacr(String status, String sus_no, String roleType,
			String issue_date);

	public ArrayList<List<String>> getChtlDACRReportListForApproval1(String sus_no, String roleType, String issue_date);

	public String setApprovedchtldacr(String sus_no, String username, String issue_date);

	public String setRejectchtldacr(String sus_no, String username);

	public ArrayList<List<String>> getchtlDACRReportListForApproval(String sus_no, String roleType, String issue_date);

	// New added by Mitesh 12-12-24
	public ArrayList<List<String>> UpdategetDACRReportrpasListPending(String qry, String sus_no, String roleType,
			String roleAccess);

	// New added by Mitesh 16-12-2024
	public ArrayList<List<String>> getSearchRpasReportDacr(String status, String sus_no, String roleType,
			String issue_date);

	public ArrayList<List<String>> getRpasDACRReportListForApproval1(String sus_no, String roleType, String issue_date);

	public String setApprovedrpasdacr(String sus_no, String username, String issue_date);

	public String setRejectrpasdacr(String sus_no, String username);

	public ArrayList<List<String>> getrpasDACRReportListForApproval(String sus_no, String roleType, String issue_date);

	// New Added by Mitesh 08-01-2025 For MR & SOW STATE
	public ArrayList<List<String>> UpdategetDACRReportList(String qry1, String sus_no, String roleType,
			String roleAccess);

	public ArrayList<List<String>> getDACRReportMRList(String sus_no, String roleType, String issue_date);

	public ArrayList<List<String>> getDACRReportOTHERListForApproval(String sus_no, String roleType, String issue_date);

	public ArrayList<List<String>> getDACRReportMRListForApproval(String sus_no, String roleType, String issue_date);

	// ue_uh
	public ArrayList<ArrayList<String>> getdetails_ue_uhDacrlist(String sus_no, String issue_date);
	public ArrayList<ArrayList<String>> getdetails_ue_uhchtlDacrlist(String sus_no, String issue_date);
	public ArrayList<ArrayList<String>> getdetails_ue_uhrpasDacrlist(String sus_no, String issue_date);
	
	public  List<String> gettail_noFromStatus(String sus_no,String asonDate1,String issue_states);
	public List<String> getchtltail_no(String sus_no, String asonDate, String issue_type);
	public List<String> getrpastail_no(String sus_no, String asonDate, String issue_type);
	
	public List<Map<String, Object>> getTptSummaryInABCList(int type,HttpSession session);
	public List<Map<String, Object>> getactMain_from_Type_of_aircraft(String abc_code,HttpSession session);
	
	public ArrayList<ArrayList<String>> avn_assetstatusDetails_linedte(String cmd,String act_main_list,String abc_code ,String sus_no,String line_dte_sus1, String status, String ason_date);
	public ArrayList<ArrayList<String>> avn_assetstatusDetails_linedtechtl(String cmd,String act_main_list,String abc_code ,String sus_no,String line_dte_sus1, String status, String ason_date);
	public List<Map<String, Object>> getactMain_from_Type_of_aircraftRPAS(String abc_code,HttpSession session);
	public ArrayList<ArrayList<String>> avn_assetstatusDetails_linedterpas(String cmd,String act_main_list,String abc_code ,String sus_no,String line_dte_sus1, String status, String ason_date);
	public ArrayList<ArrayList<String>> avn_assetstatus_linedte(String cmd,String act_main_list,String abc_code ,String sus_no,String line_dte_sus1, String ason_date);
	public ArrayList<ArrayList<String>> avn_assetstatus_linedtechtl(String cmd,String act_main_list,String abc_code ,String sus_no,String line_dte_sus1, String ason_date);
	public ArrayList<ArrayList<String>> avn_assetstatus_linedterpas(String cmd,String act_main_list,String abc_code ,String sus_no,String line_dte_sus1, String ason_date);	
	public List<String[]> fetchLocList();
	
	
}
