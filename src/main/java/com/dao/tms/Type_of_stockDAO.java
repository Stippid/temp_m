package com.dao.tms;

import java.util.ArrayList;
import java.util.List;

public interface Type_of_stockDAO {
	
	public  ArrayList<List<String>> getSearchStock(String sus_no, String mct, String type_of_stock, String roleType);
	
}
