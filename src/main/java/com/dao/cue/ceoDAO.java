package com.dao.cue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.controller.cue.ReportData;
import com.models.CUE_TB_MISO_CES;

public interface ceoDAO {

	public List<Map<String, Object>> getcceBySearch(String cce,String ces_cces_no,String ccename,String roleType);
	public List<Map<String, Object>> getcceBySearch1(String ces_cces,String ces_cces_no,String ccename,String roleType);

	public String setDeletecce(int appid);
	public CUE_TB_MISO_CES getcceDetailsByid(int id);
	public String UpdatecesDetails(CUE_TB_MISO_CES wetSuper,String username);


	public List<Map<String, Object>> getPersReportcountTable(int startPage, int pageLength, String search, String orderColunm, String orderType, 
			HttpSession sessionUserId, String appt_trade, String rank,String rank_cat, String cont_comd, String cont_corps, String cont_div, String cont_bde, 
			String sus_no, String we_pe_no, String eff_frm_date, String eff_to_date, String user_arm, String category_of_persn,
			String sponsor_dire, String training_capacity, String unit_category, String radio1, String type_force, String parent_arm1,String weperadio);

	public long getPersReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade, String rank,String rank_cat, 
			String cont_comd, String cont_corps, String cont_div, String cont_bde, String sus_no, String we_pe_no, String eff_frm_date, 
			String eff_to_date, String user_arm, String category_of_persn, String sponsor_dire, String training_capacity, String unit_category, 
			String radio1, String type_force, String parent_arm1, String weperadio);
	
	public ArrayList<ArrayList<String>> getPersExcelReportTable(String appt_trade, String rank, String cont_comd,
			String cont_corps, String cont_div, String cont_bde, String sus_no, String we_pe_no, String eff_frm_date,
			String eff_to_date, String user_arm, String category_of_persn, String sponsor_dire,
			String training_capacity, String unit_category, String ct_part_i_ii, String type_force, String parent_arm,String weperadio,String rank_cat);
	
	public List<Map<String, Object>> rankWiseStateStrReportCountTable(int startPage,int pageLength,String search,String orderColunm,String orderType, HttpSession sessionUserId,
			String appt_trade,String rank,String rank_cat,String sus_no,String we_pe_no,String user_arm,String category_of_persn,String parent_arm1);

	public long rankWiseStateStrReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade,
			String rank, String rank_cat, String sus_no, String we_pe_no, String user_arm, String category_of_persn, String parent_arm1);


	//ARMY AIR DEFENCE REPORT

//	public List<Map<String, Object>> armyAirDefenceReportTable(int startPage,int pageLength,String search,String orderColunm,String orderType, HttpSession sessionUserId,
//			String appt_trade,String rank,String rank_cat,String sus_no,String we_pe_no,String user_arm,String category_of_persn,String parent_arm1);
//	public long armyAirDefenceReportCount(String search, String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade,
//			String rank, String rank_cat, String sus_no, String we_pe_no, String user_arm, String category_of_persn, String parent_arm1);

//	public ArrayList<ArrayList<String>> armyAirDefenceReportTable(HttpSession session);
	public ArrayList<ReportData> armyAirDefenceReportTable(HttpSession session);
	
	public List<Map<String, Object>> armWiseStateStrReportTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade, String rank,
			String rank_cat, String sus_no, String we_pe_no, String user_arm, String category_of_persn,
			String parent_arm1);
	public long armWiseStateStrReportcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String appt_trade, String rank, String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn, String parent_arm1);

	public List<Map<String, Object>> estStrReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId, String appt_trade, String rank, String rank_cat, String sus_no,
			String we_pe_no, String user_arm, String category_of_persn, String parent_arm1);
	public long estStrReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId,
			String appt_trade, String rank, String rank_cat, String sus_no, String we_pe_no, String user_arm,
			String category_of_persn, String parent_arm1);
	public ArrayList<ArrayList<String>> sdFormatOneReportTableExcel(String category_of_persn, String parent_arm);
	
	
	public List<Map<String, Object>> armWiseStrCTiReportTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade, String rank,
			String rank_cat, String sus_no, String we_pe_no, String user_arm, String category_of_persn,
			String parent_arm1);
	public long armWiseStrCTiReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId,
			String appt_trade, String rank, String rank_cat, String sus_no, String we_pe_no, String user_arm,
			String category_of_persn, String parent_arm1);
	public ArrayList<ArrayList<String>> armWiseStrCTiReportTableExcel(String category_of_persn, String parent_arm);

	public ArrayList<ArrayList<String>> rankWiseStateStrReportTableExcel(String appt_trade, String rank, String sus_no,
			String we_pe_no, String user_arm, String category_of_persn, String parent_arm);

	public ArrayList<ArrayList<String>> armWiseStateStrReportTableExcel(String category_of_persn, String parent_arm);

	

	public List<Map<String, Object>> armWiseStrCTiiReportTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade, String rank,
			String rank_cat, String sus_no, String we_pe_no, String user_arm, String category_of_persn,
			String parent_arm1);
	public long armWiseStrCTiiReportcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String appt_trade, String rank, String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn, String parent_arm1);
	public ArrayList<ArrayList<String>> armWiseStrCTiiReportTableExcel(String category_of_persn, String parent_arm);

	public List<Map<String, Object>> dteWiseStrReportTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade, String rank,
			String rank_cat, String sus_no, String we_pe_no, String user_arm, String category_of_persn,
			String parent_arm1);
	public long dteWiseStrReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId,
			String appt_trade, String rank, String rank_cat, String sus_no, String we_pe_no, String user_arm,String category_of_persn, String parent_arm1);
	public ArrayList<ArrayList<String>> detWiseStrReportExcel(String category_of_persn, String parent_arm);

	public List<Map<String, Object>> designCapReportTable(int startPage, int pageLength, String search,String orderColunm, String orderType, 
			HttpSession sessionUserId);
	public long designCapReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> designCapReportExcel(String category_of_persn, String parent_arm);

	public long dtlsOfAllUnitsReportcount(String search, String orderColunm, String orderType,HttpSession sessionUserId);
	public List<Map<String, Object>> dtlsOfAllUnitsReportTable(int startPage, int pageLength, String search,String orderColunm, String orderType, HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> dtlsOfAllUnitsReportExcel(String category_of_persn, String parent_arm);

	public List<Map<String, Object>> authStrOfManpowerReportTable(int startPage, int pageLength, String search,String orderColunm, String orderType, HttpSession sessionUserId);
	public long authStrOfManpowerReportcount(String search, String orderColunm, String orderType,HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> authStrOfManpowerReportExcel(String category_of_persn, String parent_arm);

	public List<Map<String, Object>> authStrOfClrkInIAReportTable(int startPage, int pageLength, String search,String orderColunm, String orderType, HttpSession sessionUserId);
	public long authStrOfClrkInIAReportcount(String search, String orderColunm, String orderType,HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> authStrOfClrkInIAReportExcel(String category_of_persn, String parent_arm);

	public List<Map<String, Object>> authStrOfManpwerInIAReportTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId);
	public long authStrOfManpwerInIAReportcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> authStrOfManpwerInIAReportExcel(String category_of_persn, String parent_arm);
	
	public List<Map<String, Object>> formationWiseStrInIAReportTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId);
	public long formationWiseStrInIAReportcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> formationWiseStrInIAReportExcel(String category_of_persn, String parent_arm);
	
	
	public List<Map<String, Object>> authStrOfOffrByRkReportTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId);
	public long authStrOfOffrByRkReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> authStrOfOffrByRkReportExcel(String category_of_persn, String parent_arm);
	
	public List<Map<String, Object>> authStrInIAReportTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId);
	public long authStrInIAReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> authStrInIAReportExcel(String category_of_persn, String parent_arm);
	
	public List<Map<String, Object>> majMinUnitsReportTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId);
	public long majMinUnitsReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> majMinUnitsReportExcel(String category_of_persn, String parent_arm);
	
	
	public List<Map<String, Object>> authStrOfTradeReportTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId);
	public long authStrOfTradeReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> authStrOfTradeReportExcel(String category_of_persn, String parent_arm);
	
	
	public List<Map<String, Object>> authStrinIADtlReportTable(int startPage, int pageLength, String search,String orderColunm, String orderType, HttpSession sessionUserId);
	public long authStrinIADtlReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> authStrinIADtlReportExcel(String category_of_persn, String parent_arm);
	
	
	public List<Map<String, Object>> authStrNonRegReportTable(int startPage, int pageLength, String search, String orderColunm, String orderType, HttpSession sessionUserId);
	public long authStrNonRegReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> authStrNonRegReportExcel(String category_of_persn, String parent_arm);
	
	
	public List<Map<String, Object>> authStrOfMnsOffrReportTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId);
	public long authStrOfMnsOffrReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> authStrOfMnsOffrReportExcel(String category_of_persn, String parent_arm);
	

}
