package com.dao.psg.update_census_data;

import java.util.ArrayList;


import com.models.psg.Transaction.TB_PSG_CANTEEN_CARD_DETAIL1;

public interface Search_Canteen_DAO {
	
	public ArrayList<ArrayList<String>> Search_canteen(String card_no,String status,String service);

	
	 public TB_PSG_CANTEEN_CARD_DETAIL1 getcanteen_detailsByid(int id);
}
