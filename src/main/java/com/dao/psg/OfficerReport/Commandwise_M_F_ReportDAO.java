package com.dao.psg.OfficerReport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Commandwise_M_F_ReportDAO {
	
	
	public ArrayList<ArrayList<String>> Search_Commandwise(String command,String from_date,String to_date) ;
	public ArrayList<ArrayList<String>> Search_pdf(String level_c,String male,String female, String total);
	
}
