package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_CIVILIAN_TRADE;



public interface Civilian_TradeDAO {

	public ArrayList<ArrayList<String>> search_Civilian_Trade(String civilian_trade,String status);
	public TB_PSG_MSTR_CIVILIAN_TRADE getCivilian_tradeByid(int id);
	public ArrayList<ArrayList<String>> search_Civilian_Trade_Report();
}
