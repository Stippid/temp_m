package com.dao.psg.Jco_Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Re_call_jco_DAO {
	public ArrayList<ArrayList<String>> search_re_callJCO(String personnel_no1,
			String status, String unit_sus_no, String unit_name,
			 String cr_by,String cr_date,
			String roleSusNo, String roleType,String roleAccess);
	 
}
