package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE;




public interface Cause_Of_Non_EffectiveDAO {

	public ArrayList<ArrayList<String>> search_Cause_Of_Non_Effe(String causes_name,String type_of_officer,String status);
	public TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE getCauNonEffeByid(int id);
	public ArrayList<ArrayList<String>> Cause_Of_Noneff_report();
}
