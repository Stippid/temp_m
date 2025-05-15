package com.dao.mnh;

import java.util.ArrayList;

import com.models.mnh.Tb_Med_Food_Master;




public interface mstr_Food_Detail_DAO {

	public ArrayList<ArrayList<String>>search_food_detaillist(String food);
	public String updatefood(Tb_Med_Food_Master obj);
}
