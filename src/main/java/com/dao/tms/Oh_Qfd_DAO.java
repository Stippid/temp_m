package com.dao.tms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface  Oh_Qfd_DAO {
	
	public ArrayList<ArrayList<String>> search_veh_name(String type_veh,
			//String prf_code,
			String mct_main,String line_dte_sus,int kms,int vintag,int py,String type_force,String checkVal,String cmd,String line_dte_sus_main);

	public ArrayList<ArrayList<String>> base_work_shopList();

	public List<Map<String, Object>> getMCtMain_from_prf_codenodal_dte4(String type_veh, HttpSession sessionA,
			String line_dte_sus);
	
	public List<Map<String, Object>> getMCtMain_discard(String type_veh, HttpSession sessionA);
}
