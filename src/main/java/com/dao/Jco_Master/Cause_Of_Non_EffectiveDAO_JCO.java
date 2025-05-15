package com.dao.Jco_Master;

import java.util.ArrayList;

import com.model.Jco_Master.TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_JCO;

public interface Cause_Of_Non_EffectiveDAO_JCO {

	public ArrayList<ArrayList<String>> search_Cause_Of_Non_Effe(String causes_name,String type_of_officer,String status);
	public TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_JCO getCauNonEffeByid(int id);
	public ArrayList<ArrayList<String>> Cause_Of_Noneff_report();
}
