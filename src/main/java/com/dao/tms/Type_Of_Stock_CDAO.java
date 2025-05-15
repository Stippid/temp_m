package com.dao.tms;

import java.util.ArrayList;
import java.util.List;

public interface Type_Of_Stock_CDAO {

	public  ArrayList<List<String>> getSearchStockC(String sus_no, String mct, String type_of_stock, String roleType);
	
}
