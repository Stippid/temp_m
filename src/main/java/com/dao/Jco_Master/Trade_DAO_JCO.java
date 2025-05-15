package com.dao.Jco_Master;
import java.util.ArrayList;

import com.model.Jco_Master.TB_PSG_MSTR_TRADE_JCO;

public interface Trade_DAO_JCO {

	public ArrayList<ArrayList<String>> Search_Trade(String trade,String status);
	public TB_PSG_MSTR_TRADE_JCO getTradeByid(int id);
	 public ArrayList<ArrayList<String>> Trade_report();
	
}
