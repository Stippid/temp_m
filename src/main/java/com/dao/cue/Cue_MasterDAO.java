package com.dao.cue;

import java.util.List;
import java.util.Map;

import com.models.cue_wepe;
import com.models.cue_wet_pet;

public interface Cue_MasterDAO {	

	public String setApprovedDocument(int appid,String username); 
	public String setRejectDocument(int appid, String username);
	public String setDeleteDocument(int appid);
	public cue_wet_pet getcue_wet_petByid(int id);
	public String UpdateDocAttValue(cue_wet_pet DocAttValues,String username,String modifydate);
	public List<cue_wet_pet> getWET_PET_FET_DOCid(int id);
	public  List<Map<String, Object>> getAttributeFromCategory1(String wet_pet,String wet_pet_no,String sponsor_dire,String arm,String doc_type,String status,String roleType,String from_date,String to_date,String roleAccess);
	public  List<Map<String, Object>> upload_WET_PET1(String status,String wet_pet,String wet_pet_no,String sponsor_dire,String  arm,String doc_type,String roleType,String roleAccess);
}
