package com.dao.avation;

import java.util.ArrayList;

public interface ActSlotDAO {

	
//	public String getmaxABCCodeID();

	public String getmaxACTMainID(String from,String to);
	public Boolean checkIfExistMainIDAviation(String mainId);
	public Boolean checkIfExistACTRange(int code_from,int code_to);
	public String getmaxABCCodeID();
	public ArrayList<ArrayList<String>> search_act_main(String act_slot,String abc_code,String sus_no, String type_of_aircraft);
	
	
	
	
	
}
