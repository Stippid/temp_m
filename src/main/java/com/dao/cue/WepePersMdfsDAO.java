package com.dao.cue;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;

import com.models.CUE_TB_MISO_WEPE_PERS_MDFS;

public interface WepePersMdfsDAO {

	public String setApprovedWepe(int appid,String usernam);
	public String setDeleteWepe(int appid);
	public CUE_TB_MISO_WEPE_PERS_MDFS getCUE_TB_MISO_WEPE_PERS_MDFSid(int id);
	public String UpdateWepeMasterValue(CUE_TB_MISO_WEPE_PERS_MDFS WepeMasterValue);
	
	public  List<Map<String, Object>> getSearchWEPEReportDetail(String status,String we_pe_no,String modification,String cat_per, String rank_cat,String appt_trade,String arm_code, String rank, String roleType, String roleAccess);
	public List<Map<String, Object>> getSearchWEPEReportDetail1(String we_pe_no, String status,String modification,String cat_per,String rank_cat,String appt_trade,String arm_code,String rank,String roleType,String roleAccess );
	public List<String>  updaterejectactionqrywepers(String model,String remarks,int id,String letter_no);
	public List<String> updaterejectactionqrywepers2(String string, String remarks, int id, String letter_no,
			String username);
	public List<String> updaterejectactionqrywepers1(String string, String remarks, int id, String letter_no,
			String username);
	
}
