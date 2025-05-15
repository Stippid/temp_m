package com.dao.psg.Civilian_Master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.psg.Civilian_Master.TB_DISABILITY;

public interface DisabilityDAO {

	public List<Map<String, Object>> search_disability(String disability,String status); 
	public TB_DISABILITY getDisabilityByid(int id);
	public ArrayList<ArrayList<String>> search_DisabilityReport();
}
