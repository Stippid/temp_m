package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_FIRING_RESULT;

public interface FiringResultDAO {

	public ArrayList<ArrayList<String>> search_FiringResult(String firing,String status1);
	public TB_PSG_MSTR_FIRING_RESULT getmtByid(int id);
	public ArrayList<ArrayList<String>> FiringReport();
	
}
