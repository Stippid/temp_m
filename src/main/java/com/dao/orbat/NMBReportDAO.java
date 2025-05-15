	package com.dao.orbat;
	
	import java.util.ArrayList;
	
	public interface NMBReportDAO {
	
		public ArrayList<ArrayList<String>> getReliefReportList(String period_from,String period_to,String status);
	
	}
