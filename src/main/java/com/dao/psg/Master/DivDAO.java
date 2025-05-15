package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_DIV_GRADE;

public interface DivDAO {

	public ArrayList<ArrayList<String>> search_div_details(String div,String status);
	 public TB_DIV_GRADE getdivdtlByid(int id);
	 public ArrayList<ArrayList<String>> search_divReport();
	
}
