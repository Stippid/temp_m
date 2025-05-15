package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_GENDER;

public interface GenderDAO {
	
	public ArrayList<ArrayList<String>> search_gender_details(String gender_name,String status);
	 public TB_GENDER getgenderdtlByid(int id);
	 //public String updategender(TB_GENDER obj,String username,int id);
	 public ArrayList<ArrayList<String>> search_genderReport();
}
