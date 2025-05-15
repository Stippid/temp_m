package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_NCC_TYPE_ENTRY;

public interface TypeofEntryDao {

	
	public ArrayList<ArrayList<String>> search_TypeEntrydtl(String entrylabel,String status1);
	public TB_NCC_TYPE_ENTRY getmtByid(int id);
	public ArrayList<ArrayList<String>> search_TypeEntryReport();
}
