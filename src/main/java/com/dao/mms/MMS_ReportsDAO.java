package com.dao.mms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface MMS_ReportsDAO {
	public ArrayList<ArrayList<String>> mmsaih(String n_c1,String n_c2,String n_c3);
	public ArrayList<ArrayList<String>> mms_list_e_assets_reg(String type_of_hldg,String formcodeType,String formcode,String p_code,String d_from,String d_to);
	public ArrayList<ArrayList<String>> mms_ue_uh_list(String type_of_hldg, String formcodeType, String formcode,
			String p_code, String d_from, String d_to);
	public ArrayList<ArrayList<String>> mms_ue_uh_summ(String type_of_hldg,String formcodeType,String formcode,String p_code,String d_from,String d_to);
	public List<String> mmsereg(String nParaValue);
	
	public ArrayList<ArrayList<String>> wep_eqpt_status_details(String command, String type_wep_eqpt, String prf,String line_dte_sus);
	public List<Map<String, Object>> getPRFListbyItemGroup(String type,HttpSession session);
	
	public List<Map<String, Object>>  wep_eqpt_statusList(String command, String type_wep_eqpt, String prf_code,String line_dte_sus,String sus_no_list);

	//New Add
		public ArrayList<ArrayList<String>> cue_ue_list(String formcodeType, String formcode, String item_type, String m4_p_code,String arm_code);
		public ArrayList<ArrayList<String>> cue_summ_old(String formcodeType,String formcode,String sm4_p_code, String sm4_item_type);
		public ArrayList<ArrayList<String>> cue_summ(String formcodeType, String formcode, String item_type, String m4_p_code,String arm_code);
		
		


}