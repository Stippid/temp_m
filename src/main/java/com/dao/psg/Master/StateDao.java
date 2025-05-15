package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_STATE;



public interface StateDao {

	public ArrayList<ArrayList<String>> search_State_name(int country_id,String state_name,String status);
	 public TB_STATE getstateByid(int id);
	 public ArrayList<ArrayList<String>> search_StateReport();
}
