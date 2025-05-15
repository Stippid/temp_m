	package com.dao.tms;
	
	import java.util.ArrayList;
	
	public interface AnimalEquinesDAO {
	
		public ArrayList<ArrayList<String>> Equinesreportlist(String sus_no,String fdate,String tdate);
	
		public ArrayList<ArrayList<String>> Dogreportlist(String sus_no,String fdate,String tdate);
	
	}
