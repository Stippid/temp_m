package com.dao.cue;

import java.util.List;
import java.util.Map;

import com.models.CUE_TB_MISO_MMS_WET_PET_DET;
import com.models.CUE_TB_MISO_MMS_WET_PET_MAST_DET;

public interface Wet_Pet_Fet_SuperDAO {
	public List<Map<String, Object>>  getWetPetSuperReportDetailUrl(String status,String wet_pet,String wet_pet_no,String unit_type,String arm,String sponsor_dire,String uploaded_wetpet,String roleType,String roleAccess);
	public List<Map<String, Object>>  getWetPetSuperReportDetailUrl1(String wet_pet,String wet_pet_no,String unit_type,String arm,String sponsor_dire,String uploaded_wetpet,String roleType);
	public  List<Map<String, Object>> getWetPetEquipmentReportDetail1(String wet_pet_no,String item_seq_no,String authorization,String roleType);
	public String setDeleteWetPetSuper(int appid);
	public CUE_TB_MISO_MMS_WET_PET_MAST_DET getWetPetSuperDetailsByid(int id);
	public String UpdateWetPetSuperDetails(CUE_TB_MISO_MMS_WET_PET_MAST_DET wetSuper,String username);
	public String setDeleteWetPetAmendments(int appid);
	public CUE_TB_MISO_MMS_WET_PET_DET getWetPetAmendmentsDetailsByid(int id);
	public String UpdateWetPetAmendmentsDetails(CUE_TB_MISO_MMS_WET_PET_DET wetSuper, String username);
	public List<String> getWetPetAmendmentsDetailsByEditId(int editid);
	public String COPYWetPetFetDetail(String wet_pet_no_copy,String wet_pet_no,String unit_type,int roleid);
	public String setApproveduploadwetpet(int appid,int roleid,String username,String roleType);
	public String setApprovedWet_pet_amendmentdetails(int appid);
	public  List<Map<String, Object>> getWetPetEquipmentReportDetail(String wet_pet_no,String item_seq_no,String authorization,String status,String roleType, String roleAccess);
	public Object updatecapdatawetpet(String wet_pet_no, String superno,int roleid,String username,String roleType,String pstatus);
}
