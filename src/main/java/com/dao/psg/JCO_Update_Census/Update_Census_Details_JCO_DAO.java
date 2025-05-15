package com.dao.psg.JCO_Update_Census;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;



public interface Update_Census_Details_JCO_DAO {

	

	

	public ArrayList<ArrayList<String>> AppSearch_UpdateCensusJCOData(String army_no, String status, String rank,

			String unit_sus_no, String unit_name, String roleSusNo, String roleType, String icStatus);

	

	public List<Map<String, Object>> GetJcoOrUpdateCensusDataApprove(int jco_id);
	/// bisag v2 010922  (converted to Datatable)
	
	
		 public List<Map<String, Object>> Getcensus_detail_data_jco(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
				 String unit_sus_no,String unit_name,String personnel_no,
					String rank,String status,String icstatus,String roleType,String cr_by,String cr_date) ;


	public long Getcensus_detail_data_count_jco(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String unit_sus_no,String unit_name,String personnel_no,
			String rank,String status,String icstatus,String roleType,String cr_by,String cr_date);

	


}

