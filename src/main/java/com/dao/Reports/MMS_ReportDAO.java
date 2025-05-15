package com.dao.Reports;

import java.util.ArrayList;

public interface MMS_ReportDAO {

	public ArrayList<ArrayList<String>> WAREHOUSE_REPORT(String from,String to);
	public ArrayList<ArrayList<String>> ALL_INDIA_HOLDING(String from,String to);
	
}
