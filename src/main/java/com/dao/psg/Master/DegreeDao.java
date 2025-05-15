package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_DEGREE;

public interface DegreeDao {

	public ArrayList<ArrayList<String>> search_Degree(String degree, String status); 
	public TB_DEGREE getdegreeByid(int id);
	public ArrayList<ArrayList<String>> search_DegreeReport();
}
