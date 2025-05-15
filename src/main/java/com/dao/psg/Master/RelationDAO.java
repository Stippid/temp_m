package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_RELATION;

public interface RelationDAO {

	public ArrayList<ArrayList<String>> search_relation (String relation_name,String status);
	public TB_RELATION getRelationByid(int id);
	public ArrayList<ArrayList<String>> search_relationReport();
}
