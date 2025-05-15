package com.dao.mnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_RankCode;



public interface mstr_Rank_CodeDAO {
	
	public String updaterankcode(Tb_Med_RankCode obj);
	
	public ArrayList<ArrayList<String>> getSearchRankMaster(String service1,String category_code1,String rank_code1,String rank_desc1);
}
