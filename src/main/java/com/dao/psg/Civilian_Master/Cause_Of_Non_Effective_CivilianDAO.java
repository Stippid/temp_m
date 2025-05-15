package com.dao.psg.Civilian_Master;

import java.util.ArrayList;

import com.models.psg.Civilian_Master.TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN;

public interface Cause_Of_Non_Effective_CivilianDAO {

	public ArrayList<ArrayList<String>> search_Cause_Of_Non_Effe_civilian(String causes_name,String type_of_officer,String status,String type_of_regular_or_nonregular);
	public TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN getCauNonEffeCivilianByid(int id);
	public ArrayList<ArrayList<String>> Cause_Of_Noneff_civilian_report();
}
