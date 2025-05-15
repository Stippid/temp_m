package com.dao.psg.Queries;

import java.util.ArrayList;

public interface IAFF_3009_REPORT_DAO {
	 public  ArrayList<ArrayList<String>> iaff3009_Report_auth_str_jco_or(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String rank);
	 public  ArrayList<ArrayList<String>> iaff3009_Report_auth_civ(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String rank);
	 public  ArrayList<ArrayList<String>> iaff3009_Report_posted_offrs_jco_or(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String rank);
	 public  ArrayList<ArrayList<String>> iaff3009_Report_posted_civ(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String rank);
	 public  ArrayList<ArrayList<String>> iaff3009_Report_rank_and_trade_wise_jco_or(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String rank); 
	 public  ArrayList<ArrayList<String>> iaff3009_Report_religious_denomination(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String rank);
	 public  ArrayList<ArrayList<String>> iaff3009_Report_MainDetails(String month, String year, String version,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no);
	 

	 public  ArrayList<ArrayList<String>> iaff3009_Report_summary(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
				 ,String unit_sus_no);
	 
	 public ArrayList<ArrayList<String>> get3009Report(String month, String year, String comd_sus,
				String corp_sus, String div_sus, String bde_sus, String unit_sus_no,String line_dte_sus);
	 
	 public ArrayList<ArrayList<String>> iaff3009_Report_getremarks(String month, String year, String version,String comd_sus,
				String corp_sus, String div_sus, String bde_sus, String unit_sus_no);
 
}
