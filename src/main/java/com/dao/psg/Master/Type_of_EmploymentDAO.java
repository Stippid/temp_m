package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_TYPE_OF_EMPLOYMENT;

public interface Type_of_EmploymentDAO {
	
	public ArrayList<ArrayList<String>> search_type_of_employment (String name,String status);
	public TB_TYPE_OF_EMPLOYMENT getTypeofemploymentByid (int id);
	public ArrayList<ArrayList<String>> search_type_of_employmentReport();
}
