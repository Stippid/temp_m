package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_QUALIFICATION;

public interface QualificationDao {

	
	public ArrayList<ArrayList<String>> search_qualification (String qualification_type,String examination_passed,String degree, String status);
	public TB_QUALIFICATION getqualificationByid(int id);
	public ArrayList<ArrayList<String>> search_qualificationReport();
}
