package com.dao.psg.Civilian;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Posting_In_Out_Civilian_DAO {

	public List<Map<String, Object>> GetCivilianOrCensusDataApprove(int civ_id) throws SQLException;
	public Boolean getCivilianPernoAlreadyExits(int civ_id);
	public ArrayList<ArrayList<String>> getPostTenureDateCivilian(int civ_id);
	public ArrayList<ArrayList<String>>Search_Postout_Civilian(String roleSusNo, String employee_no, String rank,
			String to_sus_no, String roleType, String status,String cr_by,String cr_date, String roleSusNo2);
	public ArrayList<ArrayList<String>>Search_PostIn_Civilian(String roleSusNo, String employee_no, String rank,
			String to_sus_no, String roleType, String status,String cr_by,String cr_date, String roleSusNo2);
	public  String setTenureDate_Civilian(int id,int cur_id);
	public List<Map<String, Object>> getLastPostInOutCivilian(int civ_id);
	public List<Map<String, Object>>GetPost_OutByid_Civilian(int id);
	public List<Map<String, Object>>GetPost_InByid_Civilian(int id);
	public List<Map<String, Object>> getLastPostInnoti_civ(int civ_id);
}
