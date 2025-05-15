package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Change_In_Medicle_History_DAO {
	
	public ArrayList<ArrayList<String>> change_in_medicle(BigInteger comm_id,int cen_id);

}
