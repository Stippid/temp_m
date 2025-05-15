package com.dao.tms;


import java.util.ArrayList;

public interface ExportDataDAO {
	
	public ArrayList<ArrayList<String>> getAttributeFromMctMainMaster(String table_name);
	
}
