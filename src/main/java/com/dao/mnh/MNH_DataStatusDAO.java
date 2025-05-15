package com.dao.mnh;

import java.util.List;
import java.util.Map;

public interface MNH_DataStatusDAO {
	
	public List<Map<String, Object>> search_hospital_datastatus(String sus1,String cmd1,String yr1,String role,String userId);
	
	public List<Map<String, Object>> search_approval_datastatus(String cmd1,String user1,String mth_yr1,String para1,String sus1, String frm_dt1,String to_dt1);

	public List<Map<String, Object>> search_datastatus(String sus1,String frm_dt1,String to_dt1);
}
