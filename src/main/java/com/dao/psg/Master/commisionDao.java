package com.dao.psg.Master;

import java.util.ArrayList;


import com.models.psg.Master.TB_COMMISSION_TYPE;

public interface commisionDao {
	public ArrayList<ArrayList<String>> search_commision(String comn_name,String status);
	public TB_COMMISSION_TYPE updatecommision_Byid(int comn_id);
	/*public String updatecommission(TB_COMMISSION_TYPE imb,String username);*/
	 public Long checkExitstingcommission(String comn_name1,int id,String status1);
	 public ArrayList<ArrayList<String>> search_commisionReport();
}
