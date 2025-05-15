package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_COURSE_INSTITUTE;



public interface Course_InstituteDAO {

	public ArrayList<ArrayList<String>> search_Course_Institute(String category,String course_institute,String status);
	public TB_PSG_MSTR_COURSE_INSTITUTE getCourse_InstituteByid(int id);
	public ArrayList<ArrayList<String>> search_Course_Institute_Report();
}
