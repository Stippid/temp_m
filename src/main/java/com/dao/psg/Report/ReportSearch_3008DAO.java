package com.dao.psg.Report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface ReportSearch_3008DAO {
	
	
	public List<Map<String, Object>> getServing3008(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String LDate,String sus_no) throws SQLException;
	public long getServing3008CountList(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String FDate, String LDate,String sus_no)throws SQLException;
	
	public List<Map<String, Object>> getSupernum3008(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String LDate,String sus_no) throws SQLException;
	public long getSupernum3008Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String FDate, String LDate,String sus_no)throws SQLException;
	
	public List<Map<String, Object>> getReEmployee3008(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String LDate,String sus_no) throws SQLException;
	public long getReEmployee3008Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String FDate, String LDate,String sus_no)throws SQLException;
	
	public List<Map<String, Object>> getDeserter3008(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String LDate,String sus_no) throws SQLException;
	public long getDeserter3008Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String FDate, String LDate,String sus_no)throws SQLException;
	
	public List<Map<String, Object>> getAuthHeld3008(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String LDate,String sus_no) throws SQLException;
	public long getAuthHeld3008Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String FDate, String LDate,String sus_no)throws SQLException;
	
	// bisag v2 290822 (split auth and held)
	public List<Map<String, Object>> getAuth3008(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String LDate,String sus_no) throws SQLException;
	public long getAuth3008Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String FDate, String LDate,String sus_no)throws SQLException;
	
	public List<Map<String, Object>> getHeld3008(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String LDate,String sus_no) throws SQLException;
	public long getHeld3008Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String FDate, String LDate,String sus_no)throws SQLException;
	// bisag v2 290822 (split auth and held) ends

	
}
