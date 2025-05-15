package com.dao.tms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.TB_TMS_IUT;
import com.models.TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER;

public interface IUTDAO {
	
	public List<Map<String, Object>> getMCtMain_Id(String type_of_veh,HttpSession session,String sus_no);
	public List<Map<String, Object>> getIUTBANoList(String source_sus_no,String type_of_veh,String mct_main,HttpSession session);
	public TB_TMS_IUT getsusnoByid(int id);
	public ArrayList<ArrayList<String>> trackiutstatus(String username, String unit_sus_no, String unit_name, String cont_comd, String cont_corps, String cont_div, String cont_bde, String line_dte1,String roleSubAccess, String roleSusNo, String roleAccess);
	public ArrayList<ArrayList<String>> trackiutstatus_a_b(String username, String unit_sus_no, String unit_name,String cont_comd, String cont_corps, String cont_div, String cont_bde, String line_dte1,String roleSubAccess, String roleSusNo, String roleAccess);
	public List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> getIutDocument(String ba_num,String target_sus_no ,HttpSession sessionA);

}
