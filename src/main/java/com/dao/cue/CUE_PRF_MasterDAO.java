package com.dao.cue;

import java.util.List;
import java.util.Map;

import com.models.CUE_TB_MISO_PRF_Mst;
import com.models.Tb_Miso_Orabt_Arm_Code;
import com.models.Tb_Miso_Orbat_Code;

public interface CUE_PRF_MasterDAO {

	public String setApprovedprfMst(int appid);
	public String setRejectprfMst(int appid);
	public String setDeleteprfMst(int appid);
	 
	public CUE_TB_MISO_PRF_Mst getCUE_TB_MISO_PRF_MstByid(int id);
	public String setUpdateprfMst(CUE_TB_MISO_PRF_Mst ac,String username,String modifydate);
	public  List<Map<String, Object>> getAttributeFromCategory2(String prf_group,String coss_section,String nodal_dte,String status, String roleType);
	public  List<Map<String, Object>> getAttributeFromCategory21(String prf_group,String coss_section,String nodal_dte,String status, String roleType);

}