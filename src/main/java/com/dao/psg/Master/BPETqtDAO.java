package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_BPET_QT;


public interface BPETqtDAO {
	
	public ArrayList<ArrayList<String>> search_BPETqt(String bpetqt,String status1);
	public TB_PSG_MSTR_BPET_QT getmtByid(int id);
	public ArrayList<ArrayList<String>> BPETReport();

}
