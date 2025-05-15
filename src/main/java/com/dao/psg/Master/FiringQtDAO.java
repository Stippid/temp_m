package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_FIRING_QT;

public interface FiringQtDAO {
	
	public ArrayList<ArrayList<String>> search_FiringQt(String firing,String status1);
	public TB_PSG_MSTR_FIRING_QT getmtByid(int id);
	public ArrayList<ArrayList<String>> FiringQtReport();

}
