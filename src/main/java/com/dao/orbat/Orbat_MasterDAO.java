package com.dao.orbat;

import java.util.List;

import com.models.Tb_Miso_Orabt_Arm_Code;
import com.models.Tb_Miso_Orbat_Code;
import com.models.Tb_Miso_Orbat_Mast_Fmn;

public interface Orbat_MasterDAO {
	
	

	public String setApprovedARM(int appid);
	public String setRejectARM(int appid);
	public String setDeleteARM(int appid);
	public Tb_Miso_Orabt_Arm_Code getTb_Miso_Orabt_Arm_CodeByid(int id);
	public String Updatearmcode(Tb_Miso_Orabt_Arm_Code tb_miso,String username);
	 
	public String setApprovedTypeOfARM(int appid,String username);
	public String setRejectTypeOfARM(int appid);
	public String setDeleteTypeOfARM(int appid);
	public Tb_Miso_Orbat_Code getTb_Miso_Orbat_CodeByid(int id);
	public String setUpdateTypeOfARM(Tb_Miso_Orbat_Code ac);
	
	
	
	public String setApprovedPARM(int appid,String username);
	public String setRejectPARM(int appid);
	public String setDeletePARM(int appid);
	public String UpdateParentARM(Tb_Miso_Orbat_Code artAttValues);
	
	
	public String setApprovedLOC_NRS(int appid,String username);
	public String setRejectLOC_NRS(int appid);
	public String setDeleteLOC_NRS(int appid);
	public String UpdateLocAndNRS(Tb_Miso_Orbat_Code artAttValues,String username, String status_record);

	//if Exist Methods 
	
	public Boolean ifExistCodeValue( String code_value,String code_type);
	public Boolean ifExistCode( String code_value);
	public Boolean ifExistCodeValueLocation( String code_value,int id, String code);
	public Boolean ifExistCodeLocation( String code_value);
	
	
	public Boolean ifExistArmCode_name(String a_name,String code,int id);
	
	public List<String> getmaxprantCode(String p_code);
	
	//for CTII unit 
	
	public String setApprovedFMN(int appid,String username);
	public String setRejectFMN(int appid,String username);
	public String setDeleteFMN(int appid);
	public Tb_Miso_Orbat_Mast_Fmn getTb_Miso_Orbat_Mast_FmnByid(int id);
	public String UpdateFMN(Tb_Miso_Orbat_Mast_Fmn artAttValues,String username,String level_1,String level_2,String level_3,String level_4,
			String level_5,String level_6,String level_7,String level_8,String level_9,String level_10,String arm_code,String fmn_code,String fmn_name);
	
	
	//fmn code already exist
	
	public Boolean ifExistfmncode( String fmn_code);
	public Boolean ifExistfmnname( String fmn_name);
	
}
