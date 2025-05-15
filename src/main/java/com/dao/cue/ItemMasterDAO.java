package com.dao.cue;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;

import com.models.CUE_TB_MISO_MMS_ITEM_MSTR;

public interface ItemMasterDAO {
	
	public String setApprovedItem(int appid);
	public String setRejectItem(int appid);
	public String setDeleteItem(int appid);
	public CUE_TB_MISO_MMS_ITEM_MSTR getCUE_TB_MISO_MMS_ITEM_MSTRid(int id);
	public String UpdateItemMasterValue(CUE_TB_MISO_MMS_ITEM_MSTR ItemMasterValue,String username);
	public List<Map<String, Object>> getAttributeFromItemMaster1(String prf_group,String  item_type,String  item_group,String  class_item,String  status, String roleType);
	public List<Map<String, Object>> getAttributeFromItemMaster(String prf_group, String item_type, String item_group, String class_item, String status, String roleType);

}
