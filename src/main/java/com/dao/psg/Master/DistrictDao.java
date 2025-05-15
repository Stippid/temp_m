package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_DISTRICT;



public interface DistrictDao {

	public ArrayList<ArrayList<String>> search_District_name(int country_id,int state_id,String district_name,String status);
	 public TB_DISTRICT getDistrictByid(int id) ;
	 public ArrayList<ArrayList<String>> search_DistrictReport(); 
}
