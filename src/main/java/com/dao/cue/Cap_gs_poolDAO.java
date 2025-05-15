package com.dao.cue;

import java.util.List;
import java.util.Map;

import com.models.CUE_GS_POOL;

public interface Cap_gs_poolDAO {
	
	public String setApprovedGsPool(int appid);
	public String setDeleteGsPool(int appid);
	public CUE_GS_POOL getCUE_GS_POOLByid(int id);
	public String UpdateGsPoolValue(CUE_GS_POOL gsPoolValues);
	public List<Map<String, Object>> AttributeReportSearchRankCategory(String status,String month,String year,String arm,String rank_cat,String rank,String roleType );
	public List<Map<String, Object>> AttributeReportSearchRankCategory1(String month, String status,String year,String arm,String rank_cat,String rank,String scale,String roleType );
}
