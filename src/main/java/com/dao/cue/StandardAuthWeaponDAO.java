package com.dao.cue;

import java.util.List;
import java.util.Map;

import com.models.CUE_TB_MISO_WEPE_WEAPON_DET;
import com.models.CUE_TB_WEPE_link_sus_perstransweapon;

public interface StandardAuthWeaponDAO {
	
	public String setApprovedSAW(int appid, String username, String we_pe_no);
	public CUE_TB_WEPE_link_sus_perstransweapon getSusno(String we_pe_no);
	public String setDeleteSAW(int deleteid);
	public CUE_TB_MISO_WEPE_WEAPON_DET getCUE_TB_MISO_WEPE_WEAPON_DETByid(int id);
	public String UpdateAllValue(CUE_TB_MISO_WEPE_WEAPON_DET Values, String username);
	
	public CUE_TB_MISO_WEPE_WEAPON_DET getCUE_TB_MISO_WEPE_WEAPON_DETByidLoc(int id);
	public  List<Map<String, Object>> AttributeReportSearchStdAuthWpn(Double auth_weapon,String we_pe_no,String item_seq_no ,String status, String roleType, String roleAccess ) ;
	public  List<Map<String, Object>> AttributeReportSearchStdAuthWpn1(String we_pe,String auth_weapon,String we_pe_no,String item_seq_no,String status, String roleType,String roleAccess ) ;

}
