package com.dao.psg.Master;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.models.psg.Master.TB_PROFESSION;
public interface ProfessionDAO {
	public List<Map<String, Object>> search_Profession(String profession_name,String status); 
	public TB_PROFESSION getProfessionByid(int id) ;
	public Long checkExitstingProfession(String profession_name1,int id1,String status1); 
	public String Update_Profession(TB_PROFESSION obj,String username);
	public ArrayList<ArrayList<String>> search_ProffesionReport();
}
