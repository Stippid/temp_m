package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_CITY;

public interface City_DAO {
	
	public ArrayList<ArrayList<String>> search_city_name(int country_id,int state_id,int district_id,String city_name,String status);
	public TB_CITY getTB_CITYByid(int id);
	public ArrayList<ArrayList<String>> search_cityReport();

}
