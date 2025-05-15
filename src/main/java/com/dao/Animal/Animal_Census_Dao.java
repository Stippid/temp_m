package com.dao.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.model.Animal.TB_ANIMAL_CENSUS_DTLS;


public interface Animal_Census_Dao {

	
	public long GetSearch_censusCount_animal(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String unit_sus_no,String unit_name,String personnel_no,
			String status,String roleType);
 
	 public List<Map<String, Object>> GetSearch_censusdata_animal(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
			 String unit_sus_no,String unit_name,String personnel_no,
				String status,String roleType);
		
	 public TB_ANIMAL_CENSUS_DTLS getdogByid(int id);
	 public ArrayList<ArrayList<String>> GetAllcensus_Details(int id);
	 public String getanimalIdentityImagePath(String id,String fildname);
	

}
