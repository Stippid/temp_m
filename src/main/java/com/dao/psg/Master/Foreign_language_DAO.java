package com.dao.psg.Master;

import java.util.ArrayList;
import com.models.psg.Master.TB_FOREIGN_LANGUAGE;

public interface Foreign_language_DAO {
	
	public ArrayList<ArrayList<String>> GetSearch_Foreign_Language(String foreign_language,String status);
	public TB_FOREIGN_LANGUAGE getforeign_languageByid(int id);
	public ArrayList<ArrayList<String>> GetSearch_Foreign_LanguageReport();
}
