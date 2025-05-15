package com.dao.cue;

import java.util.List;
import java.util.Map;
import com.models.CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES;

public interface IncrementDecrementDAO {
	
	public  List<Map<String, Object>> getBase_autoDetail(String val,String item_code);
	public String setApprovedIncrementDecrement(int appid,String username);	
	public String setDeleteIncrement(int appid);	
	public CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES getCUE_TB_MISO_WEPE_WEAPON_FOOTNOTESByid(int id);	
	public String UpdateWepeMasterValue(CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES WepeMasterValue);	
	public  List<Map<String, Object>> AttributeReportSearchfootnote(String we_pe_no,String item_seq_no,String  status,String  roleType, String roleAccess);
	public  List<Map<String, Object>> AttributeReportSearchfootnote1(String  we_pe_no,String  amt_inc_dec,String  item_seq_no,String  status,String  roleType,String roleAccess);
	public  List<Map<String, Object>> AttributeReportSearchfootnote24(String we_pe_no,String status);

}
