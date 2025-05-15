package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_CASUALITY1;
import com.models.psg.Master.TB_PSG_MSTR_CASUALITY2;
import com.models.psg.Master.TB_PSG_MSTR_CASUALITY3;

public interface Casuality_Dao {
	
	//----CASUALITY 1 ------
	
	public ArrayList<ArrayList<String>> search_Casuality(String casuality_v1,String status);
	public TB_PSG_MSTR_CASUALITY1 getCasuality1Byid(int id);
	public ArrayList<ArrayList<String>> Report_Casuality1();
	
	
	//----CASUALITY 2 ------
	
	 public ArrayList<ArrayList<String>> Search_Casuality2(int casuality1_id,String casuality2_v2,String status);
	 public TB_PSG_MSTR_CASUALITY2 getCasuality2Byid(int id);
	 public ArrayList<ArrayList<String>> Search_Casuality2Report();
	 
	 
	 //------CASUALITY 3 ------
	 
	 public ArrayList<ArrayList<String>> Search_Casuality3(int casuality1_id, int casuality2_id,String casuality3_v1, String status);
	 public TB_PSG_MSTR_CASUALITY3 getCasuality3Byid(int id);
	 public ArrayList<ArrayList<String>> Search_Casuality3_Report();
}
