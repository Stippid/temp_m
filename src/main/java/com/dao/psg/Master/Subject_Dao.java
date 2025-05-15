package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Transaction.TB_PSG_CENSUS_SUBJECT;

public interface Subject_Dao {
	
	public ArrayList<ArrayList<String>> search_sub_details(String subject);
	 public TB_PSG_CENSUS_SUBJECT getsubdtlByid(int id) ;
	 public ArrayList<ArrayList<String>> search_SubReport();
}
