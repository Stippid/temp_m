package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_NATIONALITY;



public interface Nationality_DAO {
	
	public ArrayList<ArrayList<String>> search_nationlity(int country_id,String nationality_name,String status);
	public TB_NATIONALITY getnationalityByid(int id);
	public ArrayList<ArrayList<String>> search_nationlityReport();
}
