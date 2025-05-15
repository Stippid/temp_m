package com.dao.cue;

import java.util.List;
import java.util.Map;

import com.models.UploadAamendmentToDocument;
import com.models.cue_wepe;

public interface WEPEamendmentDAO {
	
	public String setApprovedWEPEamendment(int appid);
	public String setDeleteWEPEamendment(int appid);
	public UploadAamendmentToDocument getUploadAamendmentToDocumentByid(int id);
	public String UpdateWEPEamendmentValue(UploadAamendmentToDocument artAttValues);
	
	public List<UploadAamendmentToDocument> getAmendment_We_PeByid(int id);
	
	public List<cue_wepe> getAmendmentDetails_We_PeByid(String val);
	
	public  List<Map<String, Object>> searchinuploadamednment(String we_pe_no, String status, String roleType);
	
	public  List<Map<String, Object>> search_uploded_amendment_to_WEPEdocument1(String we_pe_no, String status, String roleType,String from_date,String to_date, String roleAccess);
}
