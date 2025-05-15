package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Change_Religion_History_DAO {
	public ArrayList<ArrayList<String>> change_in_religion_dtl(BigInteger comm_id,int census_id);
}
