package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_MOTHERTOUNGE;

public interface Mother_TongueDAO {
	
	public ArrayList<ArrayList<String>> search_M_T_dtl(String mothertounge,String status1);
//	 public TB_BANK getbankByid(int id);
	 public TB_MOTHERTOUNGE getmtByid(int id);
	 public ArrayList<ArrayList<String>> search_M_T_Report();
}
