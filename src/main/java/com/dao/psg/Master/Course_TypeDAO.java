package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_COURSE_TYPE;

public interface Course_TypeDAO {
	
	public ArrayList<ArrayList<String>> search_Course_type(String rank_type,String course_name,String course_gp,String status);
	 public TB_COURSE_TYPE getcourse_typeByid(int id);
	// public String updatecourse_type(TB_COURSE_TYPE obj,String username,int id);
	 public ArrayList<ArrayList<String>> search_Course_typeReport();
}
