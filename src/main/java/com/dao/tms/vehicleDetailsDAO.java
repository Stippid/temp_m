package com.dao.tms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.TB_TMS_IUT;

public interface vehicleDetailsDAO {
	public ArrayList<ArrayList<String>> vehiclestatusDetails_line_dte(String cmd,String mct_main,String prf_code,String type,String sus_no,String line_dte_sus1);
	
	public List<Map<String, Object>> vehiclestatusDetails_line_dte_coas(int startPage,String pageLength,String Search,String orderColunm,String orderType,String cmd,String mct_main,String prf_code ,String type,String sus_no_list,String line_dte_sus1);
	public long vehiclestatusDetails_line_dte_coas_Count(String Search,String cmd,String mct_main,String prf_code ,String type,String sus_no_list,String line_dte_sus1);
	
	/*public ArrayList<ArrayList<String>> AvehiclestatusDetails(String cmd,String mct_mainc,String prf_code);
	public ArrayList<ArrayList<String>> BvehiclestatusDetails(String cmd,String mct_main,String prf_code);
	public ArrayList<ArrayList<String>> CvehiclestatusDetails(String cmd,String mct_main,String prf_code);*/
	public List<Map<String, Object>> getMCtMain_from_Type_of_Veh(String prf_code,HttpSession session);
	  public ArrayList<List<String>> getAllPRFList();
	  public ArrayList<ArrayList<String>> transport_ue(String cmd,String mct_main,String prf_code,String sus_no,String line_dte_sus1,String type_force12);
		
	  


		


		public List<Map<String, Object>> transport_ue_summary(Integer startPage,String pageLength,String Search,String orderColunm,String orderType,
				String cmd,String prf_code,String sus_no,String line_dte_sus1,String type_force12,
				String eff_from12, String eff_to12, String modification12, String we_pe_no12, String table_title12, String std_nomclature12, String we_pe_no, String table_title, String std_nomclatureStr);


		public long transport_ue_summary_count(String Search, String cont_comd12, String cont_corps12, String cont_div12, String cont_bde12, String prf_code,String sus_no,String line_dte_sus1,String type_force12,
				String eff_from12, String eff_to12, String modification12, String we_pe_no12, String table_title12, String std_nomclature12);
		
		public List<Map<String, Object>> transport_ue(Integer startPage,String pageLength,String Search,String orderColunm,String orderType,
				String cont_comd12, String cont_corps12, String cont_div12, String cont_bde12, String prf_code,String sus_no,String line_dte_sus1,String type_force12,
				String eff_from12, String eff_to12, String modification12, String we_pe_no12, String table_title12, String std_nomclature12);


		public long transport_ue_count(String Search, String cont_comd12, String cont_corps12, String cont_div12, String cont_bde12, String prf_code,String sus_no,String line_dte_sus1,String type_force12,
				String eff_from12, String eff_to12, String modification12, String we_pe_no12, String table_title12, String std_nomclature12);
}
