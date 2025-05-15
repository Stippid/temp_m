package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_BANK;
import com.models.psg.Master.TB_COURSE;

public interface CourseDAO {
	
	public ArrayList<ArrayList<String>> search_course_dtl(String course_name,String status);
	 public TB_BANK getbankByid(int id);
	 public TB_COURSE getCourseByid(int id);
	 public ArrayList<ArrayList<String>> search_course_Report();
}
