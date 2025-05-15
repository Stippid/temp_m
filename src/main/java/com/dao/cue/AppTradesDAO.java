package com.dao.cue;

import java.util.List;
import java.util.Map;

import com.models.CUE_TB_PSG_RANK_APP_MASTER;

public interface AppTradesDAO {
	
	public String setApprovedAPPT(int appid,String username);
	public String setDeleteAPPT(int deleteid);
	public CUE_TB_PSG_RANK_APP_MASTER getCUE_TB_PSG_RANK_APP_MASTERByid(int id);
	public String UpdateAPTValue(CUE_TB_PSG_RANK_APP_MASTER APTValue, String username);

	public  List<Map<String, Object>> AttributeReportSearchRANKCategory(String status,String parent_code,String level_in_hierarchy,String description,String  roleType);
	public  List<Map<String, Object>> AttributeReportSearchRANKCategory1(String status,String parent_code,String level_in_hierarchy,String description,String roleType);

}
