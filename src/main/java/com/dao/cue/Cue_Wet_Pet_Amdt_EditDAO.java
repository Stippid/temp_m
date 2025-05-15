package com.dao.cue;

import java.util.List;
import java.util.Map;

import com.models.UploadWetPetAamendmentToDocument;
import com.models.cue_wepe;
import com.models.cue_wet_pet;

public interface Cue_Wet_Pet_Amdt_EditDAO {	

	public String setApprovedWetPetAdmtEdit(int appid);
	public String setDeleteWetPetAdmtEdit(int appid);
	public UploadWetPetAamendmentToDocument getUploadWetPetAamendmentToDocumentByid(int id);
	public String UpdateWetPetAdmtEdit(UploadWetPetAamendmentToDocument artAttValues,String username,String modifydate);
	
	public List<UploadWetPetAamendmentToDocument> getWet_Pet_DownloadByid(int id);	
	public List<cue_wet_pet> getEdit_Wet_Pet_DownloadByid(int id);	
	public List<cue_wet_pet> getAmendmentDetails_Wet_PetByid(String val);
	public List<Map<String, Object>> getAttributeFromCategorySearchWetPetAmendment( String status,String wet_pet_no, String roleType);
	
}

