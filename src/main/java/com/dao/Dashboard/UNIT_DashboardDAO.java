package com.dao.Dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface UNIT_DashboardDAO  {
	public List<Map<String, Object>> Getcount_offHeld_data(String sus_no,String from_code_control,String formationtype);
	public List<Map<String, Object>> Getcount_jcoHeld_data(String sus_no,String from_code_control,String formationtype);
	public List<Map<String, Object>> Getcount_orHeld_data(String sus_no,String from_code_control,String formationtype);
	public List<Map<String, Object>> Getcount_civHeld_data(String sus_no,String from_code_control,String formationtype);
	public List<Map<String, Object>> Getcount_wpns_held(String sus_no,String from_code_control,String formationtype);

	
	public List<Map<String, Object>> Getcount_offAuth_data(String sus_no,String from_code_control,String formationtype);
	public List<Map<String, Object>> Getcount_jcoAuth_data(String sus_no,String from_code_control,String formationtype);
	public List<Map<String, Object>> Getcount_civAuth_data(String sus_no,String from_code_control,String formationtype);
	public List<Map<String, Object>> Getcount_orAuth_data(String sus_no,String from_code_control,String formationtype);
	
	public List<Map<String, Object>> Getcount_aAuth_data(String sus_no,String from_code_control,String formationtype);
	public List<Map<String, Object>> Getcount_bAuth_data(String sus_no,String from_code_control,String formationtype);
	public List<Map<String, Object>> Getcount_cAuth_data(String sus_no,String from_code_control,String formationtype);
	public List<Map<String, Object>> Getcount_equi_held(String sus_no,String from_code_control,String formationtype);
	
	public List<String> tmsBa_No( String roleSusNo,String type_of_veh,String broadcat,String type,String formationtype);
	
	
	
	public List<Map<String, Object>> getUnitReportPersOffrs(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo, String Access_by_susno );
	public long getUnitReportPersOffrscount(String search, String orderColunm, String orderType,
				HttpSession sessionUserId,String roleSusNo, String Access_by_susno );
	 
	public List<Map<String, Object>> getUnitReportPersJCo(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo,String name,String rank,String armyNo,String trade, String Access_by_susno);
	public long getUnitReportPersJCocount(String search, String orderColunm, String orderType,
				HttpSession sessionUserId,String roleSusNo,String name,String rank,String armyNo,String trade, String Access_by_susno);
	 
	public List<Map<String, Object>> getUnitReportPersCivs(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId);	
	public long getUnitReportPersCivscount(String search, String orderColunm, String orderType,
				HttpSession sessionUserId);
	 
	public List<Map<String, Object>> getUnitReportVeh(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId,String type_veh,String roleSusNo, String ba_no, String broad_cat,String veh_cat, String formationtype);	
	public long getUnitReportVehcount(String search, String orderColunm, String orderType,String type_veh,String roleSusNo,
				String ba_no, String broad_cat,String veh_cat, HttpSession sessionUserId, String formationtype);
	 
	public List<Map<String, Object>> getUnitReportWpnEqpt(int startPage, int pageLength, String search, 
				String orderColunm, String orderType, HttpSession sessionUserId,String type_mtrls,String roleSusNo, String prfgp, String nomenclature, String census_no, String service_status, String broadCat, String formationtype);	
	public  long getUnitReportWpnEqptcount(String search, String orderColunm, String orderType,
				HttpSession sessionUserId,String type_mtrls,String roleSusNo, String prfgp, String nomenclature, String census_no, String service_status, String broadCat, String formationtype);
	 
	public List<Map<String, Object>> getUnitReportIT_Assets(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId, String roleSusNo, String formationtype);	
	public	 long getUnitReportIT_Assetscount(String search, String orderColunm, String orderType,
				HttpSession sessionUserId, String roleSusNo, String formationtype);
	 public List<Map<String, Object>> getUnitReportPersOR(int startPage, int pageLength, String search,
	            String orderColunm, String orderType, String rank, String name, String trade, String armyNo, HttpSession sessionUserId, String roleSusNo, String Access_by_susno);
	    public long getUnitReportPersORcount(String search, String orderColunm, String orderType,
	            String rank, String name, String trade, String armyNo, HttpSession sessionUserId, String roleSusNo, String Access_by_susno);
	    public List<Map<String, Object>> getUnitReportPersCivs(int startPage, int pageLength, String search,
	            String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo, String Access_by_susno );    
	    public long getUnitReportPersCivscount(String search, String orderColunm, String orderType,
	            HttpSession sessionUserId,String roleSusNo, String Access_by_susno);
	    
	    public long getUnitReportPersOffrsAuthcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String roleSusNo, String Access_by_susno);
	    public List<Map<String, Object>> getUnitReportPersOffrsAuth(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo, String Access_by_susno);
	    
	    
	    public long getUnitReportPersJcoAuthcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String roleSusNo, String Access_by_susno );
	    public List<Map<String, Object>> getUnitReportPersJcoAuth(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo, String Access_by_susno );
	    
	    public long getUnitReportPersOrAuthcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String roleSusNo, String Access_by_susno);
	    public List<Map<String, Object>> getUnitReportPersOrAuth(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo, String Access_by_susno);
	    
	    public long getUnitReportPersCivAuthcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String roleSusNo, String Access_by_susno );
	    public List<Map<String, Object>> getUnitReportPersCivAuth(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId,String roleSusN, String Access_by_susno );
	    
		
		public List<Map<String, Object>> getUnitReportVehAuth(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId,String type_veh,String roleSusNo, String formationtype);	
	public long getUnitReportVehAuthcount(String search, String orderColunm, String orderType,String type_veh,String roleSusNo,
				HttpSession sessionUserId, String Access_by_susno);
	
	public List<Map<String, Object>> getUnitReportWpnEqptAuth(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId,String type_mtrls,String roleSusNo, String formationtype);	
public  long getUnitReportWpnEqptAuthcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId,String type_mtrls,String roleSusNo, String formationtype);

public List<Map<String, Object>> getUnitReportWpnEqptSummary(int startPage, int pageLength, String Search,
		String orderColunm, String orderType, HttpSession sessionUserId, String type_mtrls, String roleSusNo, String prfgp, String nomenclature, String census_no, String service_status, String broadCat, String formationtype);
public  long getUnitReportWpnEqptcountSummary(String search, String orderColunm, String orderType,
		HttpSession sessionUserId,String type_mtrls,String roleSusNo, String prfgp, String nomenclature, String census_no, String service_status, String broadCat, String formationtype);

public List<String> tmsBrodecat( String roleSusNo,String type_veh,String broadcat,String type,String formationtype);
public List<String> getnomeNclatureforUnitreport(String nomenclature, String type_mtrls, String roleSusNo,String formationtype);
public List<String> getnomeCensusnoforUnitreport(String census_no, String type_mtrls, String roleSusNo,String formationtype);
public List<String> getPRF_Grp(String prfgp, String type_mtrls, String roleSusNo,String formationtype);
public List<String> getbroadCatUnitReort(String broadCat, String type_mtrls, String roleSusNo,String formationtype);
public List<String> tmsveh_type( String roleSusNo,String type_of_veh,String broadcat,String type,String formationtype);

//pranay 15.07 bde dashboard 

public long Get3008monthlyavg(String sus_no, HttpSession sessionUserId);
public long Get3009monthlyavg(String sus_no, HttpSession sessionUserId);
public long itassetcount(String sus_no, HttpSession sessionUserId);
public long mcravg(String sus_no, HttpSession sessionUserId);
public long GetAvehmonthlyavg(String sus_no,HttpSession sessionUserId);
public long GetBvehmonthlyavg(String sus_no,HttpSession sessionUserId);
public long GetCvehmonthlyavg(String sus_no,HttpSession sessionUserId);



public List<Map<String, Object>> get3008CurrentMonthlist(int startPage, int pageLength, String Search,	String orderColunm, String orderType, HttpSession sessionUserId,String rolesusno);
public long get3008CurrentMonthlistCount(String Search, String orderColunm, String orderType,HttpSession sessionUserId, String roleSusNo);
public List<Map<String, Object>> get3009CurrentMonthlist(int startPage, int pageLength, String Search,	String orderColunm, String orderType, HttpSession sessionUserId,String rolesusno);
public long get3009CurrentMonthlistCount(String Search, String orderColunm, String orderType,HttpSession sessionUserId, String roleSusNo);
public List<Map<String, Object>> getMcrCurrentMonthlist(int startPage, int pageLength, String Search,	String orderColunm, String orderType, HttpSession sessionUserId,String rolesusno);
public long getMcrCurrentMonthlistCount(String Search, String orderColunm, String orderType,HttpSession sessionUserId, String roleSusNo);

public List<Map<String, Object>> IT_Assets_holding_as_on_dateList(int startPage, int pageLength, String Search,String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo);
public long IT_Assets_holding_as_on_dateCount(String Search, String orderColunm, String orderType,HttpSession sessionUserId, String roleSusNo);
public List<Map<String, Object>> getAvehcurrentMonthList(int startPage, int pageLength, String Search,String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo);
public long getAvehcurrentMonthCount(String Search, String orderColunm, String orderType,HttpSession sessionUserId, String roleSusNo) ;
public List<Map<String, Object>> getBvehcurrentMonthList(int startPage, int pageLength, String Search,String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo);
public long getBvehcurrentMonthCount(String Search, String orderColunm, String orderType,HttpSession sessionUserId, String roleSusNo) ;
public List<Map<String, Object>> getCvehcurrentMonthList(int startPage, int pageLength, String Search,String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo);
public long getCvehcurrentMonthCount(String Search, String orderColunm, String orderType,HttpSession sessionUserId, String roleSusNo) ;
public List<Map<String, Object>> getAllCasualtyList(int startPage, int pageLength, String Search,String orderColunm, String orderType, HttpSession sessionUserId,String roleSusNo);
public long getAllCasualtyCount(String Search, String orderColunm, String orderType,HttpSession sessionUserId, String roleSusNo) ;


public List<Map<String, Object>> getLastUpdateDate(HttpSession session,String roleSusNo);

public int getPostCount(HttpSession session,String formCodeControl);
public int getPostCount_in(HttpSession session,String formCodeControl);
public int getPostCount_in_jco(HttpSession session,String formCodeControl);
public int getPostCount_out_jco(HttpSession session,String formCodeControl);
public int getPostCount_in_civ(HttpSession session,String formCodeControl);
public int getPostCount_out_civ(HttpSession session,String formCodeControl);


}
