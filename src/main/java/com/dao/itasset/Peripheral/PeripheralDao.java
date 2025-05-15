package com.dao.itasset.Peripheral;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface PeripheralDao {
	
	public List<Map<String, Object>> Search_Peripheral(int startPage,int pageLength,String Search,String orderColunm,
			String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,String model_no,
			String from_date,String to_date,HttpSession sessionUserId);
	
	public long getPeripheralCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String from_date,String to_date,String model_no,HttpSession sessionUserId);
	public String approve_AssetsData(String a,String user_sus, String status,String username);
	
	public List<Map<String, Object>> getAppPeripheralList(int startPage,int pageLength,String Search,String orderColunm,String orderType,String status,
			String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String model_no,String unit_sus_no,String unit_name,String from_date,String to_date,HttpSession sessionUserId) throws SQLException;
	
	public long getAppPeripheralCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String model_no,String unit_sus_no,String unit_name,String from_date,String to_date,HttpSession sessionUserId)throws SQLException;
	
	
	public List<Map<String, Object>> getAppPeripheralChildList(String p_id);
	public List<Map<String, Object>> getPeriChildStatus (int id );
	public List<Map<String, Object>> getAppChildPeriList(String p_id);
	public List<Map<String, Object>> GetdataPeri(int id) throws SQLException;
	
	public ArrayList<ArrayList<String>> getcomaseptext_network_feature(int id);
	public ArrayList<ArrayList<String>> getcomaseptext_hw_interface(int id);
	
	// added by dev 18-12-24
	
	public List<Map<String, Object>> getDeletedPeripheralData(int startPage,int pageLength,String Search,String orderColunm,
			String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,String model_no,
			String from_date,String to_date,String s_state,String unit_sus_no,String unit_name,HttpSession sessionUserId);

	public long getDeletedPeripheralCount(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String from_date,String to_date,String model_no,String s_state,String unit_sus_no,String unit_name,HttpSession sessionUserId);
	
}
