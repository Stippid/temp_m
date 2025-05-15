package com.dao.cue;

import java.util.List;

import com.models.cue_wepe;
import com.models.cue_wet_pet;

public interface Cue_WE_PE_UploadDAO {
	
	public String setApprovedARM(int appid, String username);
	public String setDeleteARM(int appid);
	public cue_wepe getcue_wepeByid(int id);
	public String UpdateArtAttValue(cue_wepe updateid, String username,String from_date,String to_date,String table_title);

	public List<cue_wepe> getWe_Pe_DownloadByid(int id);	
	
}
