package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface Known_AllergyDAO {
	
	//public ArrayList<ArrayList<String>> known_allergy_history(int comm_id,int census_id);
	public List<Map<String, String>> known_allergy_history(BigInteger comm_id,int census_id) ;


}
