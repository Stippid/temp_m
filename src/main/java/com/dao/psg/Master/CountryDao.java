package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_COUNTRY;



public interface CountryDao {

	public ArrayList<ArrayList<String>> search_Country_name(String name,String status);
	 public TB_COUNTRY getCountryByid(int id);
	 public ArrayList<ArrayList<String>> search_Country_report();
}
