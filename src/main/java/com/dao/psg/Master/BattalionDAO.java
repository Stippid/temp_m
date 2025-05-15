package com.dao.psg.Master;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.psg.Master.TB_BATTALION;



public interface BattalionDAO {

	public List<Map<String, Object>> search_Battalion(String battalion_name,String status); 
	public TB_BATTALION getBattalionByid(int id) ;
	public Long checkExitstingBattalion(String battalion_name1,int id1,String status1); 
	public String Update_Battalion(TB_BATTALION obj,String username);
	public ArrayList<ArrayList<String>> search_BattalionReport();
	
}
