package com.dao.psg.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Search_CensusDao {
	public ArrayList<ArrayList<String>> Search_census(String roleSusNo,String personnel_no,String status,String rank,String unit_name,String unit_sus_no, HttpSession session);
	public List<Map<String, Object>> View_censusByid(int id);
	public String approve_censusData(String a,String user_sus,String status,String username);
	/// bisag v2 230822  (converted to Datatable)


	public long GetSearch_censusCount(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String unit_sus_no,String unit_name,String personnel_no,
				String rank,String status, String cr_by,String cr_date,String roletype,Boolean IsMns);
		
		
		 public List<Map<String, Object>> GetSearch_censusdata(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
				 String unit_sus_no,String unit_name,String personnel_no,
					String rank,String status, String cr_by,String cr_date,String roleType,Boolean IsMns);
}
