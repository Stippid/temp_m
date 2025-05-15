package com.dao.psg.Jco_Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Posting_In_Out_JCO_DAO {

	public ArrayList<ArrayList<String>>Search_Postout_JCO(String roleSusNo, String personnel_no, String rank,
			String to_sus_no, String roleType, String status, String cr_by,String cr_date, String roleSusNo2);
	public ArrayList<ArrayList<String>>Search_PostIn_JCO(String roleSusNo, String personnel_no, String rank,
			String to_sus_no, String roleType, String status, String cr_by,String cr_date, String roleSusNo2);
	public List<Map<String, Object>> getLastPostInOutJCO(int jco_id);
	public  String setTenureDateJCO(int id,int cur_id);
	public Boolean getJcopernoAlreadyExits(int jco_id);
	public ArrayList<ArrayList<String>> getPostTenureDateJCO(int jco_id);
	public List<Map<String, Object>>GetPost_OutByid(int id);
	public List<Map<String, Object>>GetPost_InByid(int id);
}
