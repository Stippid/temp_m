package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_DIV_GRADE;



public interface RhDao {

	public ArrayList<ArrayList<String>> search_Rh(String rh);
	public TB_DIV_GRADE getrhByid(int id);

}
