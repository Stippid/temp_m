package com.dao.psg.Civilian_Master;

import java.util.ArrayList;

import com.models.psg.Civilian_Master.TB_EX_SERVICEMEN;

public interface ExservicemenDAO {
	public ArrayList<ArrayList<String>> search_exservicemen_details(String ex_servicemen,String status);
	 public TB_EX_SERVICEMEN getexservicemendtlByid(int id);
	 //public String updategender(TB_GENDER obj,String username,int id);
	 public ArrayList<ArrayList<String>> search_exservicemenReport();
}
