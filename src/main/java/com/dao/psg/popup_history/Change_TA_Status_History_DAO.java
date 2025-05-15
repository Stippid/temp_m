package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Change_TA_Status_History_DAO {
	
	public ArrayList<ArrayList<String>> change_ta(BigInteger comm_id,int census_id);
	
}
