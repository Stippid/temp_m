package com.dao.cue;

import java.util.List;
import java.util.Map;

import com.models.CUE_TB_WEPE_link_sus_perstransweapon;

public interface WEPELinkSusDAO {
	
	public List<Map<String, Object>> getwepepersModiDetailsReport(String we_pe_no ,String tbl);
	 public List<Map<String, Object>> getwepepersModiDetailsFootnotesReport(String we_pe_no );
	 public Boolean isSus_noExist(String sus_no);
	 public List<Map<String, Object>> getwepetransModiDetailsFootnotesReport(String we_pe_no );
	 public List<Map<String, Object>> getwepeweaponModiDetailsFootnotesReport(String we_pe_no );
	 public List<Map<String, Object>> getViewmoreModipersDetailsReport(String we_pe_no,String mod);
	 public List<Map<String, Object>> getViewmoreModiTransDetailsReport(String we_pe_no,String mod);
	 
	 public List<Map<String, Object>> getViewmoreModiWepDetailsReport(String we_pe_no,String mod);
	 
	 public List<Map<String, Object>> getFootNotesPersDetails(String sus_no,String wepe_no);
	 public  List<Map<String, Object>> getSearchlinkWEPEPersReportDetail(String we_pe_no,String status,String unit_sus_no,String roleType);
	 public List<Map<String, Object>>getSearchlinkWEPEPersReportDetail1(String status_pers,String wepe_pers_no,String unit_sus_no,String unit_name, String roleType, String roleAccess, String roleSusNo);
	 public  List<Map<String, Object>> getSearchlinkWEPETransReportDetail(String qry);
	 public  List<Map<String, Object>> getSearchlinkWEPEWepReportDetail(String qry);
	 public String setApprovedWepeLink(int appid,String sus,String wepe, String username, String creadtedate);
	 public String setRejectWepeLink(int appid,String sus,String wepe);
	
	 public CUE_TB_WEPE_link_sus_perstransweapon getCUE_TB_MISO_WEPE_PERS_MDFSidLink(int id);
	 public String setApprovedWepeLinkTrans(int appid,String sus,String wepe, String username, String creadtedate);
	 public String setRejectWepeLinkTrans(int appid,String sus,String wepe);

	 public String setApprovedWepeLinkWep(int appid,String sus,String wepe, String username, String creadtedate);
	 public String setRejectWepeLinkWep(int appid,String sus,String wepe);

	 public List<String> getLink_sus_permdf(String sus_no,String we_pe_no);
	 public List<String> getLink_sus_perfoot(String sus_no,String we_pe_no);
	 public List<String> getLink_sus_transmdf(String sus_no,String we_pe_no);
	 public List<String> getLink_sus_transfoot(String sus_no,String we_pe_no);
	 public List<String> getLink_sus_wepmdf(String sus_no,String we_pe_no);
	 public List<String> getLink_sus_wepfoot(String sus_no,String we_pe_no);
	 
	 public String setDeleteWepeLink(int appid,String sus,String wepe);
	 public String setDelinkapprvWepeLink(int appid,String sus,String wepe);
	 public String setDelinkrejectWepeLink(int appid);
	 
	 public String setDeleteWepeLinkTrans(int appid,String sus,String wepe);
	 public String setDelinkapprvWepeLinkTrans(int appid,String sus,String wepe);
	 public String setDelinkrejectWepeLinkTrans(int appid);
	 
	 public String setDeleteWepeLinkWeap(int appid,String sus,String wepe);
	 public String setDelinkapprvWepeLinkWeap(int appid,String sus,String wepe);
	 public String setDelinkrejectWepeLinkWeap(int appid);	 
	 
	 public List<Map<String, Object>> getSearchlinkWEPETransReportDetail2(String wepe_trans_no, String unit_sus_no_trans,
				String status_trans, String roleType,String roleAccess,String roleSusNo);
	 public  List<Map<String, Object>> getSearchlinkWEPETransReportDetail1(String status_trans,String wepe_trans_no,String unit_sus_no_trans, String roleType, String roleAccess, String roleSusNo);
	 public List<Map<String, Object>> getFootNotesTransDetails(String sus_no,String wepe_no);
	 public List<Map<String, Object>> getSearchlinkWEPEWepReportDetail2(String wepe_weapon_no,String unit_sus_no_wep, String status_weap,String roleType) ;
	 public List<Map<String, Object>> getSearchlinkWEPEWepReportDetail1(String status, String wepe_wep_no,
				String unit_sus_no_wep, String roleType,String roleAccess,String roleArmCode,String roleSusNo)  ;

	 public List<Map<String, Object>> getOrbatDetails(String unit_sus_no) ;
	 
	 public List<Map<String, Object>> getSearchlinkWEPEWepfootnoteReportDetail(String unit_sus_no_wep);
	 public List<Map<String, Object>> getFootNotesWeaponDetails(String sus_no,String wepe_no);
	 

		public List<String> check_locationWeap(String wepe_wep_no,String sus_no);
		public List<String> check_locationWeapTrans(String wepe_wep_no,String sus_no);
		public List<String> check_locationWeappers(String wepe_trans_no, String unit_sus_no);
		
}
