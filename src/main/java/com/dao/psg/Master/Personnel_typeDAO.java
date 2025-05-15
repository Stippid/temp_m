package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PERSONNEL_TYPE;

public interface Personnel_typeDAO {
	
	 public ArrayList<ArrayList<String>> search_Personnel_type_dtl(String personnel_name , String status);
	 public TB_PERSONNEL_TYPE getPersonnel_typeByid(int id);
	// public String updatePersonnel_type(TB_PERSONNEL_TYPE obj,String username,int id);
	 public ArrayList<ArrayList<String>> search_Personnel_type_Report();
}
