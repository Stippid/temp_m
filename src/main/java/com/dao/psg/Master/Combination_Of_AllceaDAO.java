package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_COMBINATION_OF_ALLCEA;

public interface Combination_Of_AllceaDAO {

	public ArrayList<ArrayList<String>> search_Combination_of_allcea(String fd1,String fd2,String status);
	public TB_PSG_MSTR_COMBINATION_OF_ALLCEA getCombinationByid(int id);
	public ArrayList<ArrayList<String>> search_Combination_Of_allcea_report();
}
