package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_PURPOSEOF_VISIT;

public interface Purposeof_VisitDAO {
	
	public ArrayList<ArrayList<String>> search_visitPurposedtl(String visitPurpose,String status1);
	public TB_PSG_MSTR_PURPOSEOF_VISIT getmtByid(int id);
	public ArrayList<ArrayList<String>> search_visitPurposeReport();
}
