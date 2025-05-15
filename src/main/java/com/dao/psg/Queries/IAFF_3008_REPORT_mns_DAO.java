package com.dao.psg.Queries;

import java.util.ArrayList;

public interface IAFF_3008_REPORT_mns_DAO {
	 public  ArrayList<ArrayList<String>> iaff3008_Report_Serving(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String rank);
	 public  ArrayList<ArrayList<String>> iaff3008_Report_SuperNum(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String rank);
	 public  ArrayList<ArrayList<String>> iaff3008_Report_Re_Empl(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String rank);
	 public  ArrayList<ArrayList<String>> iaff3008_Report_Deserter(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String rank);
	 public  ArrayList<ArrayList<String>> iaff3008_Report_MainDetails(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no);
	 
	 
	 public  ArrayList<ArrayList<String>> iaff_Report_3008_Auth_Held(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String rank);
	 public  ArrayList<ArrayList<String>> iaff_Report_3008_Auth(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String rank);
	 public  ArrayList<ArrayList<String>> iaff_Report_3008_Held(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String rank);
	 
	 public  ArrayList<ArrayList<String>> get3008Report_mns(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String line_dte_sus);
	 public ArrayList<ArrayList<String>> getRemarks(String month, String year, String unitSusNo);
	 
	 
	 
	 
	 
}
