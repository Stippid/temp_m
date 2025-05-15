package com.dao.cue;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.CUE_TB_MISO_WEPE_PERS_DET;
import com.models.CUE_TB_WEPE_link_sus_perstransweapon;

public interface Personnel_EntitlementDAO {
		
	
	public  List<Map<String, Object>> getSearchPersonnelEntitlementReport(String we_pe_no,String status, String roleType ,String category_of_persn,String rank,String roleAccess); 
	 public  List<Map<String, Object>> getSearchPersonnelEntitlementReport1(String we_pe_no,String category_of_persn,String rank,String rank_cat,String status, String roleType,String roleAccess);
	 public String setApprovedPersonal_EntitalARM(int appid,String username, String we_pe_no);
	public String setDeletePersonal_EntitalARM(int appid);

	public CUE_TB_MISO_WEPE_PERS_DET getCUE_TB_MISO_WEPE_PERS_DETByid(int id);
	public String setUpdatePersonal_EntitalARM(CUE_TB_MISO_WEPE_PERS_DET ac,String username);
	
	///////////////////
	 public  List<Map<String, Object>> getSummaryPersonnelEntitlementReport(String we_pe_no,String status);
	 public  List<Map<String, Object>> getSummaryPersonnelEntitlementReport1(String we_pe_no,String status);
	 public CUE_TB_WEPE_link_sus_perstransweapon getSusnopers(String we_pe_no);
}
