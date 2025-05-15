package com.dao.cue;

import java.util.List;
import java.util.Map;

import com.models.cue_wepe;


public interface Cue_wepe_validuptoDAO {
	
	public List<Map<String, Object>> getvaliduptowepefegsl(String we_pe, String we_pe_no,
          String doc_type,String arm, String sponsor_dire, String status, String roleType,
          String from_date, String to_date,String roleAccess);
	
	public List<cue_wepe> getWe_Pe_DownloadByid(int id);
	
		
}
