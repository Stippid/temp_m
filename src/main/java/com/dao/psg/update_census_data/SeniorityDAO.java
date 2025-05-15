package com.dao.psg.update_census_data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;


public interface SeniorityDAO {

	public ArrayList<ArrayList<String>> Search_Seniority(String personnel_no,
			String status, String unit_sus_no, String unit_name,String rank,String cr_by,String cr_date,
			String roleSusNo,
			String roleTypee);
	public TB_PSG_CHANGE_OF_SENIORITY getSearch_SeniorityByid(int id);
	
	public ArrayList<ArrayList<String>> getseniorityData(BigInteger comm_id);
	public ArrayList<ArrayList<String>> getoldseniorityDate(BigInteger comm_id);
	public ArrayList<ArrayList<String>> Popup_Change_of_seniority(BigInteger comm_id);
	public List<Map<String, Object>> getHisChangeOfSeniority(BigInteger comm_id, int status, HttpSession sessionA);
	public String approveHisseniority(BigInteger parseInt, List<Map<String, Object>> changeOfseniority, String username,
			Date modified_date);
	
	public ArrayList<ArrayList<String>> Popup_Change_of_tnaino(BigInteger comm_id);
}
