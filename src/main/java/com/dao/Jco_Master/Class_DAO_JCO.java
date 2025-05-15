package com.dao.Jco_Master;

import java.util.ArrayList;

import com.model.Jco_Master.TB_PSG_MSTR_CLASS_JCO;

public interface Class_DAO_JCO {

	public ArrayList<ArrayList<String>> search_Class(String class_e,String status);
	public TB_PSG_MSTR_CLASS_JCO getClassByid(int id);
	public ArrayList<ArrayList<String>> Class_report();
	
}
