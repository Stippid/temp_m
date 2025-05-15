package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_BANK;

public interface BankDAO {
	
	public ArrayList<ArrayList<String>> search_bnk_dtl(String bank_name,String bank_abb,String ifsc,String bank_address,String bank_phone);
	 public TB_BANK getbankByid(int id);
	// public String updatebank_dtl(TB_BANK obj,String username,int id);

}
