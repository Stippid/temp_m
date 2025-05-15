package com.dao.psg.Master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.psg.Master.TB_SPECIALIZATION;

public interface SpecializationDAO {

	
	public List<Map<String, Object>> search_Specialization(String specialization,String status); 
	public TB_SPECIALIZATION getSpecializationByid(int id);
	public Long checkExitstingSpecialization(String specialization1,int id1,String status1); 
	public String Update_Specialization(TB_SPECIALIZATION obj,String username);
	public ArrayList<ArrayList<String>> search_SpecializationReport();
	
}
