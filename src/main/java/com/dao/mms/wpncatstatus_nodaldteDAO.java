package com.dao.mms;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface wpncatstatus_nodaldteDAO {
	public List<Map<String, Object>> getwpncatnodal_dte(HttpSession session,String line_dte_sus);
	public List<Map<String, Object>> getPRFListbyItemGroup_nodal_dte(String type, HttpSession session);
	//public List<Map<String, Object>> wep_eqpt_statusList_nodal_dte(int startPage,String pageLength,String orderColunm,String orderType,
		//	String Search,String line_dte_sus,String type_wep_eqpt,String item_no_list,	String cmd,String corps,String div,String bde,String sus_no);

	public List<Map<String, Object>> wep_eqpt_statusList_nodal_dte(int startPage,String pageLength,String orderColunm,String orderType,
			String Search,String line_dte_sus,String type_wep_eqpt,String item_no_list,String cmd,String corps,String div,String bde,String sus_no);
			

}
