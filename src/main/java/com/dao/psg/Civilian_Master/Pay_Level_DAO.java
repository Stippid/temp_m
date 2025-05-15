package com.dao.psg.Civilian_Master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.psg.Civilian_Master.TB_PSG_MSTR_PAY_LEVEL;

public interface Pay_Level_DAO {
	
	public List<Map<String, Object>> search_pay_level(String pay_level,String status); 
	public TB_PSG_MSTR_PAY_LEVEL getPay_levelByid(int id);
	public ArrayList<ArrayList<String>> search_Pay_LevelReport();

}
