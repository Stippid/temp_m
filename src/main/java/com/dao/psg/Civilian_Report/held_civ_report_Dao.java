package com.dao.psg.Civilian_Report;

import java.util.ArrayList;

public interface held_civ_report_Dao {
	public ArrayList<ArrayList<String>> Report_civ_nominalroll(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String civilian_status);
	 public  ArrayList<ArrayList<String>> Report_auth_civ(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String civilian_status);
	 public  ArrayList<ArrayList<String>> Report_posted_civ(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String civilian_status);	
	 public  ArrayList<ArrayList<String>> Report_MainDetails_civ(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no);
	 

	 public  ArrayList<ArrayList<String>> Report_summary_civ(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
				 ,String unit_sus_no,String civilian_status);
	 public  ArrayList<ArrayList<String>> Report_non_regular_civ(String month, String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			 ,String unit_sus_no,String civilian_status);
}