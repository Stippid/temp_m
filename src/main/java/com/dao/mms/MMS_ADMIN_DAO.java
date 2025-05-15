package com.dao.mms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MMS_ADMIN_DAO {
	public ArrayList<ArrayList<String>> UnitMcrStatusList(String deo, String stat, String mnth,String unit_name1,String unit_sus_no1,String cont_comd1,String cont_corps1,String cont_div1,String cont_bde1);
	public ArrayList<ArrayList<String>> UnitObsnStatusList(String deo, String stat, String mnth,String m7_sus);
	public ArrayList<ArrayList<String>> GetObsnData(int id);
	public ArrayList<ArrayList<String>> getallotedDEOList(String deo);
	public String mms_update_deoandeqptreqd(String b_reqd, String oper, String b_sus_no);
	
	
	public List<Map<String, Object>> mms_unit_all_obsn_statusList(int startPage,String pageLength,String Search,String orderColunm,String orderType,String sus_no,String month,String yr,String status);
	public long mms_unit_all_obsn_statusCount(String Search,String sus_no,String month,String yr,String status);
}