package com.dao.psg.Jco_Service_Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface View_JCODao {
	public ArrayList<ArrayList<String>> Search_JCOData(String unit_name,String unit_sus_no,String personnel_no,String status,String rank,String roleSusNo,String roleType
			,String name,String y_comm,String y_dob,String p_arm,String comm_status);
	public List<Map<String, Object>> View_JCODataByid(int id,String jco_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> Sh_UpadteJCODataByid(int id,String jco_id,int comm_status);
	public List<Map<String, Object>> FM_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> Spouse_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> CHILD_JCOByid(int id,String jco_id,String roleSusNo) ;
	public List<Map<String, Object>> TENU_JCOByid(int id,String jco_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> Field_View_JCOByid(int id,String jco_id,String roleSusNo);
	public List<Map<String, Object>> ARM_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> PE_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> AQ_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> PTQ_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> ILan_View_JCOByid(String jco_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> FLan_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> View_JCOAWARD_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> JCORegimental_View_UpadteByid(int id,String jco_id,String roleSusNo);
	 public List<Map<String, Object>> JCOBA_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status) ;
	 public List<Map<String, Object>> FS_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> BA_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> F_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> JCOORM_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> JCOPDO_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> JCOPM_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> JCOPS_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> JCONOK_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> JCONA_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> TENU_Total_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> AR_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> JCORD_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> View_JCOAttachment_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status);
	
	public List<Map<String, Object>> GetSearch_JCOrecorddata(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
			 String unit_name,String unit_sus_no,String army_no,String full_name,
				String rank,String year_of_comm, String year_of_dob, String parent_arm, String statusA,String roleSusNo, String roleType) ;
	
	
	public long GetSearch_JCOrecordCountlist(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String unit_name,String unit_sus_no,String army_no,String full_name,
			String rank,String year_of_comm, String year_of_dob, String parent_arm, String statusA,String roleSusNo, String roleType);
}
