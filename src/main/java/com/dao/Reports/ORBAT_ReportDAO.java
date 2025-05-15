package com.dao.Reports;

import java.util.ArrayList;

public interface ORBAT_ReportDAO {
	
	ArrayList<ArrayList<String>> search_conver_restruc_reorga_redes_actionlist(String report_type,String ddlYears,String month);
	ArrayList<ArrayList<String>> disbandmentReportaction(String report_type,String ddlYears);
	ArrayList<ArrayList<String>> search_movement_in_out_command_actionList(String report_type,String from_date, String to_date);
	
}
