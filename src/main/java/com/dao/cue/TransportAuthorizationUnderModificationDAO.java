package com.dao.cue;
import java.util.List;
import java.util.Map;

import com.models.CUE_TB_MISO_WEPE_TRANSPORT_MDFS;

public interface TransportAuthorizationUnderModificationDAO {
	
	public String setApprovedMdfs(int appid,String username);
	public String setDeleteMdfs(int appid);
	public CUE_TB_MISO_WEPE_TRANSPORT_MDFS getCUE_TB_MISO_WEPE_TRANSPORT_MDFSRid(int id);	
	public  List<Map<String, Object>> getAttributeFromTransportModification(String we_pe_no,String modification,String mct_no,String status, String roleType, String roleAccess,String roleSusNo);
	public  List<Map<String, Object>> getAttributeFromTransportModification1(String we_pe_no,String modification,String mct_no, String status, String roleType);
	
}