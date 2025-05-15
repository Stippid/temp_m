package com.dao.psg.Master;

import java.util.ArrayList;
import java.util.List;

import com.models.psg.Master.TB_EXAMINATION_PASSED;

public interface Examination_PassedDAO {
	

	public List<ArrayList<String>> search_ExaminationPassed( String qualification_type, String examination_passed,
			String status);
	
	public TB_EXAMINATION_PASSED getEXAMINATIONPASSEDByid(int id);
	public ArrayList<ArrayList<String>> search_ExaminationPassedReport();
}
