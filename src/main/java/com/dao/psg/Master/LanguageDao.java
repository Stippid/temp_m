package com.dao.psg.Master;

import java.util.List;
import java.util.Map;

import com.models.psg.Master.TB_LANGUAGE;

public interface LanguageDao {

	public List<Map<String, Object>>search_LanguageRepo(String language1);
	public TB_LANGUAGE getLanguageByid(int id) ;
	public Long checkExitstingLanguage(String language1,int id1) ;
	public String Update_Language(TB_LANGUAGE obj,String username);
}
