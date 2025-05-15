package com.dao.Jco_Master;

import java.util.ArrayList;

import com.model.Jco_Master.TB_PSG_MSTR_PAY_GROUP_JCO;

public interface Pay_GroupDAO_JCO {

	public ArrayList<ArrayList<String>> search_Pay_Group(String pay_group,String status);
	public TB_PSG_MSTR_PAY_GROUP_JCO getPay_GroupByid(int id);
	public ArrayList<ArrayList<String>> Pay_Group_report();
	
}
