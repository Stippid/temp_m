package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Change_Contact_History_DAO {
	public ArrayList<ArrayList<String>> change_Contact(BigInteger comm_id,int census_id);

}
