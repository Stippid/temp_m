package com.dao.Animal;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public interface Animal_Str_incr_decrDAO {
	
	public long GetSearch_Incr_Decr_count(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId, String type, String army_no, String from_sus_no,
			String to_sus_no, String status);

	public List<Map<String, Object>> GetSearch_Incr_Decr_List(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String type, String army_no,
			String from_sus_no, String to_sus_no, String status);
	
	public ArrayList<ArrayList<String>> getPostTenureDateAnimal(int census_id_animal);
	public Boolean getAnimalpernoAlreadyExits(int census_id);
	
	public ArrayList<ArrayList<String>> GetCommDataApprove(int census_id);
	
	public List<Map<String, Object>> getAnml_Str_IncrByid(int parseInt);
	
	public List<Map<String, Object>> getAnml_Str_DecrByid(int id);
	
	

}
