package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_INDIAN_LANGUAGE;

public interface Indian_languageDAO {
	public ArrayList<ArrayList<String>> search_indian_language (String ind_language,String status);
	public TB_INDIAN_LANGUAGE getindianlanByid(int id);
	public ArrayList<ArrayList<String>> search_indian_languageReport();
}
