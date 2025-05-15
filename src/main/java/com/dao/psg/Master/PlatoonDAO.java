package com.dao.psg.Master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.models.psg.Master.TB_PLATOON;

public interface PlatoonDAO {

	public List<Map<String, Object>> search_Platoon(String platoon_name,String status);
	public TB_PLATOON getPlatoonByid(int id) ;
	public Long checkExitstingPlatoon(String platoon_name1,int id1,String status1); 
	public String Update_Platoon(TB_PLATOON obj,String username);
	public ArrayList<ArrayList<String>> search_PlatoonReport();
}
