package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_MEDAL_TYPE;

public interface Medal_TypeDAO {

	public ArrayList<ArrayList<String>> search_relation (String medal_type,String medal_name,String medal_abberivation,String status);
	public TB_MEDAL_TYPE getRelationByid(int id) ;
	public ArrayList<ArrayList<String>> search_MedalTypeReport();
}
