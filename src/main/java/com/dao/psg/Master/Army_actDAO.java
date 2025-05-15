package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_ARMY_ACT_SEC;


public interface Army_actDAO {
	public ArrayList<ArrayList<String>> search_Army_Act(String army_act_sec,String status);
	public TB_PSG_MSTR_ARMY_ACT_SEC getArmy_actByid(int id);
	public ArrayList<ArrayList<String>> search_Army_actReport();
 
}
