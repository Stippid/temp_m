package com.dao.mnh;

import java.util.List;
import java.util.Map;

public interface Daily_vip_officer_ReportDAO {
	public List<Map<String, Object>> getsearch_Daily_vip_Report(String sus1,String unit1,String cmd1,String frm_dt1,String to_dt1, String serv1);

}
