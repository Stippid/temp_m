package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_LANG_PROOF;

public interface LanguageProfessionDAO {

	public ArrayList<ArrayList<String>> Search_LangProf_dtl(String lang,String status);
	 public TB_LANG_PROOF getLangStdByid(int id);
	 public ArrayList<ArrayList<String>> Search_LangProf_Report();
	
}
