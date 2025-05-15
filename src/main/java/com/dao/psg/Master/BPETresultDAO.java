package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_BPET_RESULT;

public interface BPETresultDAO {
	
	public ArrayList<ArrayList<String>> search_BPETResult(String bpetresult,String status1);
	public TB_PSG_MSTR_BPET_RESULT getmtByid(int id);
	public ArrayList<ArrayList<String>> BPETReport();

}
