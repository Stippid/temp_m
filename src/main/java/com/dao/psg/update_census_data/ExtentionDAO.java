package com.dao.psg.update_census_data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface ExtentionDAO {
	public ArrayList<ArrayList<String>> search_extention(String unit_name,String unit_sus_no,String personnel_no1 ,String status, String cr_by,String cr_date,
		    String roleSusNo,String roleType);
	
	

	public List<Map<String, Object>> getExtn_data(int census_id, BigInteger comm_id);



	public String approveHisExtension(int remp_id, BigInteger comm_id, List<Map<String, Object>> changeOfExtention,
			String username, Date modified_date);



	public List<Map<String, Object>> getHisOfExtension(int remp_id, BigInteger comm_id, int status, HttpSession session);
}
