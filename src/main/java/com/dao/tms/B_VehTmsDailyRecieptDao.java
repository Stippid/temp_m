package com.dao.tms;

import java.util.List;
import java.util.Map;

public interface B_VehTmsDailyRecieptDao   {
	public List<Map<String, String>> get_B_vech_daily(String sus_no_aprove,String ser_no_approve);
}
