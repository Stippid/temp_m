package com.dao.psg.Master;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.psg.Master.TB_BLOOD_GROUP;
public interface BloodDao {
	
	public List<Map<String, Object>> search_BloodRepo(String blood_desc1,String status); 
	public TB_BLOOD_GROUP getBloodByid(int id) ;
	public Long checkExitstingBlood(String blood_desc1,int id1,String status1); 
	public String Update_Bood(TB_BLOOD_GROUP obj,String username);
	public ArrayList<ArrayList<String>> search_BloodGroupReport();
}
