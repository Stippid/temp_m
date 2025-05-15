package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Change_Marital_Status_DAO {
	public ArrayList<ArrayList<String>> change_in_marital_status_dtl(BigInteger comm_id,int census_id);

	List<Map<String, Object>> getSpouseQualification(BigInteger comm_id);
}
