package com.dao.tms;

import java.util.ArrayList;
import java.util.List;

public interface Type_Of_Stock_ADAO {
	
	public  ArrayList<List<String>> getSearchStockA(String sus_no, String mct, String type_of_stock, String roleType);
	
}
