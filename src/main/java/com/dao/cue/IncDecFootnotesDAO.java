package com.dao.cue;

import java.util.List;
import java.util.Map;

import com.models.CUE_TB_MISO_WEPE_PERS_FOOTNOTES;

public interface IncDecFootnotesDAO {
	
	public String setApprovedINC(int appid,String username);
	public String setDeleteINC(int appid);
	public CUE_TB_MISO_WEPE_PERS_FOOTNOTES getCUE_TB_MISO_WEPE_PERS_FOOTNOTESbyid(int id);
	public List<Map<String, Object>> AttributeReportSearchFootnotes(String we_pe,String status,String we_pe_no,String category_of_personnel,String rank_cat,String appt_trade,String rank,String roleType, String roleAccess);
    public List<Map<String, Object>> AttributeReportSearchFootnotes1(String we_pe_no,String category_of_personnel,String rank_cat,String appt_trade,String rank,String status,String roleType,String roleAccess );
	public String updatepersonfootnotesvalue(CUE_TB_MISO_WEPE_PERS_FOOTNOTES footnotes);
}
