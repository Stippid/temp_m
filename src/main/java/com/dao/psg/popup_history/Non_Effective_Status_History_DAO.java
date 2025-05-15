package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Non_Effective_Status_History_DAO {
	public ArrayList<ArrayList<String>> update_non_effective_status_details(BigInteger comm_id,int census_id);
}
