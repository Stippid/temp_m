package com.dao.psg.Jco_Update_JcoData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Search_UpdatedJcoOr_DataDao 
{
	public List<Map<String, Object>> GetJcoOrCensusDataApprove(int jco_id);

	public ArrayList<ArrayList<String>> GetServingStatusJCO(int jco_id);

	List<Map<String, String>> getQualificationJCOData(int jco_id, int status);
	public ArrayList<ArrayList<String>> AppSearch_UpdateJCOData(String personnel_no, String status, String rank, String unit_sus_no,
			String unit_name,String cr_by,String cr_date, String roleSusNo, String roleType,String icStatus);
	
	////common////
	public ArrayList<ArrayList<String>> getShapeData(String shape,int p_id,int status);
	public ArrayList<ArrayList<String>> getSelfMotFatName(String comm_id);
	public ArrayList<ArrayList<String>> getSpouseName(String comm_id);
	public ArrayList<ArrayList<String>> getChildName(String comm_id,String rela);
	public ArrayList<ArrayList<String>> getChilddob(String  id,String jco_id);

	public String getJcocensusIdentityImagePath(String id);
	public List<Map<String, Object>> getLastPostInnoti_jco(String jco_id);
}