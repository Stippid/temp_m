package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_AWARD_CAT;

public interface AwardCatDAO {
	
	public ArrayList<ArrayList<String>> search_AwardCat(String awardCat,String status1);
	public TB_PSG_MSTR_AWARD_CAT getmtByid(int id);
	public ArrayList<ArrayList<String>> AwardCatReport();
}
