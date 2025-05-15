package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_COMPANY;

public interface CompanyDAO {

	public ArrayList<ArrayList<String>> search_Company (String company_name, int bat_id, String status);
	public TB_COMPANY getCompanyByid(int id) ;
	public Long checkExitstingCompany(String company_name1,int id1, String status1); 
	public String Update_Company(TB_COMPANY obj,String username);
	public ArrayList<ArrayList<String>> search_CompanyReport();
}
