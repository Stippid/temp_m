package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_MARITAL_STATUS;

public interface MaritialDAO {
	
	public ArrayList<ArrayList<String>> search_maritial_name(String marital_code,String marital_name,String status);
	 public TB_MARITAL_STATUS getmaritialByid(int id);
	 public ArrayList<ArrayList<String>> search_maritialReport();
}
