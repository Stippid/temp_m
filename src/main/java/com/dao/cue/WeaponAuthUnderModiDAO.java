package com.dao.cue;

import java.util.List;
import java.util.Map;


import com.models.CUE_TB_MISO_WEPE_WEAPONS_MDFS;

public interface WeaponAuthUnderModiDAO {	
	public String setApprovedWeaponModi(int appid, String username);	
	public String setDeleteWeaponModi(int appid);
	public CUE_TB_MISO_WEPE_WEAPONS_MDFS getCUE_TB_MISO_WEPE_WEAPONS_MDFSByid(int id);	
    public String UpdateWepeMasterValue(CUE_TB_MISO_WEPE_WEAPONS_MDFS WepeMasterValue);	
    public  List<Map<String, Object>> AttributeReportSearchMdfs(String status,String we_pe_no,String item_seq_no,String modification, String roleType, String roleAccess) ;
    public  List<Map<String, Object>> AttributeReportSearchMdfs1(String we_pe_no,String item_seq_no,String modification,String status, String roleType,String roleAccess);	

}
