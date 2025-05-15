package com.dao.tms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.TB_TMS_IUT;

public interface vehiclestatus_nodaldteDAO {
	public ArrayList<ArrayList<String>> vehiclestatusDetails_nodal_dte(String cmd,String mct_main,String prf_code,String type,String sus_no,String line_dte_sus1);
	
	public List<Map<String, Object>> getMCtMain_from_prf_codenodal_dte(String type_veh,HttpSession session,String line_dte_sus);
}
