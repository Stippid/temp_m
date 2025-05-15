package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_SECONDED_TO;


public interface SecondedToDAO {
	
	public ArrayList<ArrayList<String>> search_secondedTo(String secondedTo,String status1);
	public TB_PSG_MSTR_SECONDED_TO getmtByid(int id);
	public ArrayList<ArrayList<String>> SecondedToReport();

}
