package com.dao.psg.Civilian_Master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.psg.Civilian_Master.TB_CATEGORY;

public interface CategoryDAO {

	public List<Map<String, Object>> search_category(String category,String status); 
	public TB_CATEGORY getCategortByid(int id);
	public ArrayList<ArrayList<String>> search_CategoryReport();
}
