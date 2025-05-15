package com.dao.Jco_Master;

import java.util.ArrayList;

import com.model.Jco_Master.TB_PSG_MSTR_RANK_JCO;

public interface RankDAO {
	
	public ArrayList<ArrayList<String>> search_Rank(String appointment,String category,String status);
	public TB_PSG_MSTR_RANK_JCO getRankByid(int id);
	 public ArrayList<ArrayList<String>> Rank_Report();

}
