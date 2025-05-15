package com.dao.psg.Civilian_Master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.psg.Civilian_Master.TB_PAY_HEAD;

public interface Pay_HeadDAO {
	
	public List<Map<String, Object>> search_pay_head(String pay_head,String status); 
	public TB_PAY_HEAD getpay_headByid(int id);
	public ArrayList<ArrayList<String>> search_pay_headReport();

}
