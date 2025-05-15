package com.dao.cue;
import com.models.cue_wepe;

public interface link_we_wetDAO {
	
	public String setApprovedWELinkwithWET(int appid, String username);
	public String setRejectWELinkwithWET(int appid);
	public String setDeleteWELinkwithWET(int appid);
	public cue_wepe getWELinkwithWETByid(int id);
	public String UpdateWELinkwithWET(cue_wepe artAttValues, String username);

}
