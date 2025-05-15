package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_CIVILIAN_DESIGNATION;

public interface Civilian_DesignationDAO {

	public ArrayList<ArrayList<String>> search_Civilian_designation(String designation,String status,String civilian_trade,
			String classification_services,String c_group);
	public TB_PSG_MSTR_CIVILIAN_DESIGNATION getCivilianDesByid(int id);
	public ArrayList<ArrayList<String>> Civilian_DesignationReport();
	
}
