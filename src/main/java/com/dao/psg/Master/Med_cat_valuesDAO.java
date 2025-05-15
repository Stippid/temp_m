package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_MED_CAT_VALUES;;

public interface Med_cat_valuesDAO {
	
	public ArrayList<ArrayList<String>> search_med_cat_values (String shape_status, String values ,String status);
	public TB_MED_CAT_VALUES get_med_cat_valuesByid(int id);	
	public ArrayList<ArrayList<String>> search_med_cat_valuesReport();
}
