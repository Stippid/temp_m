package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_COPECODE;


public interface CopeCodeDAO {
	
	public ArrayList<ArrayList<String>> search_Copecode(String copdecode,String status1,String value,String description);
	public TB_PSG_MSTR_COPECODE getmtByid(int id);
	public ArrayList<ArrayList<String>> CopecodeReport();

}
