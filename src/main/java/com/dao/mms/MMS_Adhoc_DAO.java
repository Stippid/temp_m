package com.dao.mms;

import java.util.ArrayList;


public interface MMS_Adhoc_DAO   {

	public ArrayList<ArrayList<String>> mms_adhoc_query();
/*	public ArrayList<ArrayList<String>> mms_ue_uh_Hirarlist(String type_of_hldg, String formcodeType, String formcode, String p_code,
			String d_from, String d_to);*/
	public ArrayList<ArrayList<String>> mms_ue_uh_Hirarlist(String type_of_hldg, String formcodeType, String formcode, String p_code,
			String d_from, String d_to);
	public ArrayList<ArrayList<String>> ObsList(String type_of_hldg, String formcodeType, String formcode, String p_code,
			String d_from, String d_to);
	public ArrayList<ArrayList<String>> BarrList(String type_of_hldg, String formcodeType, String formcode, String p_code,
			String d_from, String d_to);
	public ArrayList<ArrayList<String>> Hirarueuh(String type_of_hldg, String formcodeType, String formcode, String p_code,String p_i_code,
			String d_from, String d_to, String fl_control );
	public ArrayList<ArrayList<String>> CmdAih(String type_of_hldg, String formcodeType, String formcode, String p_code,String p_i_code,
			String d_from, String d_to, String fl_control );
	public ArrayList<ArrayList<String>> showObslist(String sus_no);
	public ArrayList<ArrayList<String>> wastreportlist();
	public ArrayList<ArrayList<String>> CmdTypewiseHld(String type_of_hldg, String formcodeType, String formcode, String p_code,String p_i_code,
			String d_from, String d_to, String fl_control );
}