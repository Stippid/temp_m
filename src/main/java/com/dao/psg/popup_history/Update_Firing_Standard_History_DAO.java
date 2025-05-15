package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Update_Firing_Standard_History_DAO {
	public ArrayList<ArrayList<String>> update_firing_standard_details(BigInteger comm_id,int census_id);

}
