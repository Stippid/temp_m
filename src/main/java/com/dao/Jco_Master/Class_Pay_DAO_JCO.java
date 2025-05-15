package com.dao.Jco_Master;

import java.util.ArrayList;

import com.model.Jco_Master.TB_PSG_MSTR_CLASS_PAY_JCO;

public interface Class_Pay_DAO_JCO {

	public ArrayList<ArrayList<String>> search_Class_pay(String class_pay,String status);

	public ArrayList<ArrayList<String>> search_Class_pay_report();

	public TB_PSG_MSTR_CLASS_PAY_JCO getFieldAreaByid(int id);
}
