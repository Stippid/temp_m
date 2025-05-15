
package com.dao.psg.update_census_data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Search_UpdateOffDataDao {
	
	public ArrayList<ArrayList<String>> Search_UpdateOffData(
			String personnel_no, String status, String rank, String unit_name,
			String unit_sus_no,String cr_by,String cr_date, String roleSusNo, String roleType,String icStatus);
	public ArrayList<ArrayList<String>> AppSearch_UpdateOffData(String personnel_no, String status, String rank, String unit_sus_no,
			String unit_name,String cr_by,String cr_date, String roleSusNo, String roleType,String icStatus);
	
	
	public List<Map<String, Object>> View_UpadteOfficerDataByid(int id,String comm_id);
	public List<Map<String, Object>> Spouce_View_UpadteByid(int id,String comm_id) ;
	public List<Map<String, Object>> AWARD_View_UpadteByid(int id,String comm_id);
	public List<Map<String, Object>> AQ_View_UpadteByid(int id,String comm_id);
	public List<Map<String, Object>> Lan_View_UpadteByid(int id,String comm_id);
	public List<Map<String, Object>> F_View_UpadteByid(int id,String comm_id);
	public List<Map<String, Object>> B_View_UpadteByid(int id,String comm_id) ;
	public List<Map<String, Object>> FS_View_UpadteByid(int id,String comm_id) ;
	public List<Map<String, Object>> BA_View_UpadteByid(int id,String comm_id);
	public List<Map<String, Object>> SC_View_UpadteByid(int id,String comm_id);
	
	public List<Map<String, Object>> ORM_View_UpadteByid(int id,String comm_id);
	public List<Map<String, Object>> PM_View_UpadteByid(int id,String comm_id);
	public List<Map<String, Object>> PS_View_UpadteByid(int id,String comm_id);
	public List<Map<String, Object>> NOK_View_UpadteByid(int id,String comm_id);
	public List<Map<String, Object>> NA_View_UpadteByid(int id,String comm_id);
	public List<Map<String, Object>> FM_View_UpadteByid(int id,String comm_id) ;
	public ArrayList<ArrayList<String>> GetCensusDataApprove(BigInteger comm_id);
	public List<Map<String, Object>> getLastPostInOut(BigInteger comm_id);
	public  String setTenureDate(int id,int cur_id);
	public ArrayList<ArrayList<String>> GetServingStatus(BigInteger comm_id);
	public ArrayList<ArrayList<String>> getNon_effective_date(int comm_id,String hd_rank);
	public List<Map<String, Object>> getPostInOut1(int comm_id);
	public  String posrting_setTenureDate(int id,int cur_id);
	public ArrayList<ArrayList<String>> GetCensusDataApprove_postin_out(BigInteger comm_id);
	public List<Map<String, Object>> getunitdet(String from_sus);
	public List<Map<String, Object>> getLastPostInnoti(BigInteger comm_id);
	public String setTenureDate_3008(int id, Date tos);
	public ArrayList<ArrayList<String>> GetpostinoutDataForTos(BigInteger comm_id);
	public ArrayList<ArrayList<String>> GetpostinoutPrevioustDateofTos(BigInteger comm_id);
	
	
	public ArrayList<ArrayList<String>> Search_UpdateMnsOffData(String personnel_no, String status, String rank,
			String unit_sus_no,	String unit_name, String cr_by, String cr_date, String roleSusNo, String roleType, String icStatus);
	public ArrayList<ArrayList<String>> AppSearch_UpdateMnsOffData(String personnel_no, String status, String rank,
			String unit_sus_no,String unit_name, String cr_by, String cr_date, String roleSusNo, String roleType, String icStatus);
}
