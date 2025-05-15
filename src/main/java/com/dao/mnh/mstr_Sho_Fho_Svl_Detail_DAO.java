package com.dao.mnh;

import java.util.ArrayList;

import com.models.mnh.Tb_Med_Surv_Master;



public interface mstr_Sho_Fho_Svl_Detail_DAO {

	public ArrayList<ArrayList<String>>search_surv_detaillist(String target_diseases,String target_diseases_sub,String investigation);
	public String updateSho_Fho(Tb_Med_Surv_Master obj);
}
