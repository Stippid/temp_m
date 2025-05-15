package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_RELIGION;


public interface Religion_DAO {
	
	 public ArrayList<ArrayList<String>> search_religion_name(String religion_name,String status);
	 public TB_RELIGION getreligionByid(int id);
	 public ArrayList<ArrayList<String>> search_religionReport();
}
