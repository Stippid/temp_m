package com.dao.mnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_Strength;

public interface mnh_input_strengthDetailsDAO {
	
	 public List<Map<String, Object>> search_strength_details_input(String cmd1, String qtr1, String yr1);
	 public String updatestrength(Tb_Med_Strength obj,String username);
	 public Long checkstrengthExistlNo(String cmd,String qtr,int year,int id);
	 public Tb_Med_Strength updatestrength_detailsByid(int id);
	 
	 public ArrayList<ArrayList<String>> GetAlloffDetails(String ef) ;
	 public ArrayList<ArrayList<String>> GetAlljcoDetails_j(String ef) ;
		public ArrayList<ArrayList<String>> GetAll_or_Details_o(String ef);

	 
}
