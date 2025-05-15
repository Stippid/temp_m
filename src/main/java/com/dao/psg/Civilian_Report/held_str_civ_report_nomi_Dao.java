package com.dao.psg.Civilian_Report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.psg.Report.TB_PSG_APPX_A_B_CIV_MAIN;
import com.models.psg.Report.TB_PSG_CIV_MAIN;

public interface held_str_civ_report_nomi_Dao {



	public long Getsearch_nominrollcount_civ(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String civilian_status )throws SQLException;
	public List<Map<String, Object>> Getsearch_search_nominrollciv(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String civilian_status ) throws SQLException;
	public List<Map<String, Object>> Getsearch_search_authciv(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String civilian_status ) throws SQLException;
	public long Getsearch_authcountciv(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String civilian_status  )throws SQLException;
	public List<Map<String, Object>> Getsearch_heldstrcivciv(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String FDate,String LDate,String civilian_status ) throws SQLException;
	public long Getsearch_heldstrcivcountciv(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String FDate,String LDate ,String civilian_status )throws SQLException ;
	public List<Map<String, Object>> Get_total_nonregular_civ(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String FDate,String LDate,String civilian_status ) throws SQLException;
	public long Get_total_nonregular_civCount(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String FDate,String LDate,String civilian_status ) throws SQLException;

	public ArrayList<ArrayList<String>> Search_civ_Report_Version(String month, String year, HttpSession session,
			String status, String unit_sus_no);
	public ArrayList<ArrayList<String>> getldate(String LDate);

	public ArrayList<ArrayList<String>> Search_Report_civnominalroll(String month, String year , HttpSession session,String version ,String unit_sus_no,String civilian_status);
	public ArrayList<ArrayList<String>> Search_Report_auth_civ(String month, String year , HttpSession session,String version ,String unit_sus_no);
	public ArrayList<ArrayList<String>> Search_Report_posted_civ(String month, String year , HttpSession session,String version ,String unit_sus_no);
	public ArrayList<ArrayList<String>> Search_Report_summary_civ(String month, String year,HttpSession session, String version, String unit_sus_no);
	public ArrayList<ArrayList<String>> non_regular_civlist(String month, String year,HttpSession session, String version, String unit_sus_no);


	public Boolean Insert_Report_civ(String username,String roleSusNo,String FDate ,String month, String year ,String userId,String present_return_no,String last_return_no ,String present_return_dt,String last_return_dt,String observation,String LDate,String civilian_status) throws SQLException ;
	public Boolean Approve_Report_civ(String username,String roleSusNo,String month, String year,String version,String civilian_status) throws SQLException;
	public Boolean Delete_Report_civ(String username,String roleSusNo,String month, String year,String version,String civilian_status) throws SQLException;

	public  List<TB_PSG_CIV_MAIN> Get_civ_VersionData(String month, String year ,String roleSusNo, String civilian_status);
	public ArrayList<String> getSusNoListForciv();
	public String getCivilianStatus(String sus_no);


	public List<Map<String, Object>> GetHeldStrCivNominalrollDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String sus_no,  String comd,String corps,String div,String bde)
					throws SQLException;

	public long GetHeldStrCivNominalrollDataCount(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String sus_no,  String comd,String corps,String div,String bde)
					throws SQLException;

	public ArrayList<ArrayList<String>> pdf_Download_held_str_civ(String comd, String corps,
			String div, String bde,String sus_no);



	//		======================== for appx A and B ===================
	public ArrayList<ArrayList<String>> getCiviliadnAuth(String unit_sus_no);
	public Map<String, String> getRegEstAppxAData(String unit_sus_no, String year, String month);
	public Map<String, String> getNonRegularCivList(String unit_sus_no, String year, String month);
	public Map<String, String> getCivSummaryList(String unit_sus_no, String year, String month);
	public String getCommandName(String sus_no);
	public Boolean insertAppxAandBCiv(String username, String roleSusNo, String month,
			String year,String observation) throws SQLException;
	public ArrayList<ArrayList<String>> Search_civ_appxAandB_Version(String month, String year, HttpSession session,
			String status, String unit_sus_no);

	public List<TB_PSG_APPX_A_B_CIV_MAIN> Get_appx_a_bciv_VersionData(String month, String year, String roleSusNo);
	public ArrayList<ArrayList<String>> Search_appxAandB_auth_civ(String month, String year,String version, String unit_sus_no,String status);
	public Map<String, String> Search_appxAandB_summary_civ(String month, String year, String version, String unit_sus_no,String status);
	public Map<String, String> Search_appxAandB_sectionB_civ(String month, String year, String version, String unit_sus_no,String status) ;
	public Map<String, String> Search_appxAandB_sectionC_civ(String month, String year, String version, String unit_sus_no,String status);
	public String Search_appxAandB_observation_civ(String month, String year, String version, String unit_sus_no,String status);

	public Boolean Delete_Report_appxAandB_civ(String username, String roleSusNo, String month, String year, String version)
			throws SQLException ;

	public Boolean approve_Report_appxAandB_civ(String username, String roleSusNo, String month, String year, String version)
			throws SQLException;

	public ArrayList<ArrayList<String>> getappxAandBList(String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no,String line_dte_sus);

	public  HashMap<String,ArrayList<ArrayList<String>>> pdf_AppxABReport(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus ,String unit_sus_no,String rank, String version);
}
