package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_LANG_STD;

public interface LanguageStandardDAO {

	
	public ArrayList<ArrayList<String>> Search_LangSTD_dtl(String lang,String status);
	 public TB_LANG_STD getLangStdByid(int id);
	 public ArrayList<ArrayList<String>> Search_LangSTD_Report();
}
