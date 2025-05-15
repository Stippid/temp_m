package com.dao.mnh;


import java.util.ArrayList;







public interface Daily_Morning_bed_state_ReportDAO {
	
	public ArrayList<ArrayList<String>> getsearch_morning_bed_Report(String sus1,String unit1,String cmd1,String frm_dt1,String to_dt1);
	
	public ArrayList<ArrayList<String>> getview_morning_bed_Report(String sus1);
}