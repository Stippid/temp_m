package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Change_Name_History_DAO {
	public ArrayList<ArrayList<String>> change_name(BigInteger comm_id,int census_id);

}
