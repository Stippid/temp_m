package com.dao.psg.Report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.psg.Report.TB_IAFF_3009_MAIN;
import com.models.psg.Report.TB_PSG_IAFF_3009_MAIN;

public interface ReportSearch_3009DAO {
	
	
	public List<Map<String, Object>> getauthdetails3009(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no) throws SQLException;
	public List<Map<String, Object>> getpostedServing3009(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId	,String sus_no,String FDate, String LDate) throws SQLException;
	public List<Map<String, Object>> gettradeServing3009(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String FDate,String LDate) throws SQLException;
	public List<Map<String, Object>> getreligiousServing3009(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String FDate,String LDate) throws SQLException;
	public List<Map<String, Object>> getauthciv3009(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no) throws SQLException;
	public List<Map<String, Object>> getpostciv3009(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String FDate,String LDate) throws SQLException;
	
	
	
	 public long getauthdetails3009CountList(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no )throws SQLException;
     public long getpostedServing3009CountList(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String FDate,String LDate)throws SQLException;
	 public long gettradeServing3009Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String FDate,String LDate )throws SQLException;
	 public long getreligiousServing3009Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String FDate,String LDate)throws SQLException;
     public long getauthciv3009CountList(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no )throws SQLException;
     //public  List<TB_PSG_IAFF_3009_MAIN> Get_3009_VersionData(String month, String year ,String roleSusNo);
    // public Boolean Insert_Report_3009(String username,String roleSusNo,String FDate ,String month, String year ,String userId,String present_return_no,String present_return_dt,String observation,String LDate) throws SQLException ;
	
     public Boolean Insert_Report_3009(String username,String roleSusNo,String FDate ,String month, String year ,String userId,String present_return_no,String present_return_dt,String remarks,String distlist,String LDate,
 			String offf_auth_count,String offf_posted_count,String offf_defi_count,String offf_sur_count,String officerauth,String officerauthno,
 			String jco_auth_count,String jco_posted_count,String jco_defi_count,String jco_sur_count,String jcoauth,String jcoauthno,
 			String or_auth_count,String or_posted_count,String or_defi_count,String or_sur_count,String orauth,String orauthno,
 			String civ_auth_count,String civ_posted_count,String civ_defi_count,String civ_sur_count,String civauth,String civauthno,
 			String offf_mns_auth_count,String offf_mns_posted_count,String offf_mns_defi_count,String offf_mns_sur_count,String officermnsauth,String officermnsauthno,boolean iscronsheduling)
 			throws SQLException ;
     public long getpostciv3009CountList(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String sus_no,String FDate,String LDate )throws SQLException ;
	 
	 public  List<TB_PSG_IAFF_3009_MAIN> Get_3009_VersionData(String month, String year ,String roleSusNo);
	 
	
	public ArrayList<ArrayList<String>> Search_3009_Report_Version(String month, String year, HttpSession session,
			String status, String unit_sus_no);
	public Boolean Delete_Report_3009(String username,String roleSusNo,String month, String year,String version) throws SQLException; 
	
	
	public ArrayList<ArrayList<String>> getldate(String LDate);
	public ArrayList<ArrayList<String>> Search_Report_auth_str_jco_or_3009(String month, String year , HttpSession session,String version ,String unit_sus_no);
	public ArrayList<ArrayList<String>> Search_Report_auth_civ_3009(String month, String year , HttpSession session,String version ,String unit_sus_no);
	public ArrayList<ArrayList<String>> Search_Report_posted_offrs_jco_or_3009(String month, String year , HttpSession session,String version ,String unit_sus_no);
	public ArrayList<ArrayList<String>> Search_Report_posted_civ_300(String month, String year , HttpSession session,String version ,String unit_sus_no);
	public ArrayList<ArrayList<String>> Search_Report_rank_and_trade_wise_jco_or_3009(String month, String year , HttpSession session,String version ,String unit_sus_no);
	public ArrayList<ArrayList<String>> Search_Report_religious_denomination_3009(String month, String year , HttpSession session,String version ,String unit_sus_no);
	
	public Boolean Approve_Report_3009(String username,String roleSusNo,String month, String year,String version) throws SQLException;
	
	public ArrayList<String> getSusNoListForIAFF3009();
	public ArrayList<ArrayList<String>> Search_Report_summary(String month, String year,
			HttpSession session, String version, String unit_sus_no);
	//public ArrayList<ArrayList<String>> getcommand_3009(String roleSusNo);
}
