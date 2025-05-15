package com.dao.mnh;

import java.util.ArrayList;

import com.models.mnh.Tb_Med_HIV;

public interface mnh_input_controllimitDAO {
	public ArrayList<ArrayList<String>> contol_limit_report(String command1,String category1, 
			String disease_type1,String disease_principal1,String disease_subtype1,String disease_mmr1,String block_description1);
	public ArrayList<ArrayList<String>> contol_limit_report_cal(String command1, String category1, String disease_type1,
			String disease_principal1, String disease_subtype1, String disease_mmr1,String block_description1);
}
