package com.dao.psg.Master;


import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_SUB_CLAUSE;

public interface Sub_clauseDAO {
	public ArrayList<ArrayList<String>> search_Sub_Clause(String sub_clause,String status);
	public TB_PSG_MSTR_SUB_CLAUSE getSub_clauseByid(int id);
	public ArrayList<ArrayList<String>> search_Sub_ClauseReport();
}
