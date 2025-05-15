package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Update_Visit_Foriegn_Details_History_DAO {
	public ArrayList<ArrayList<String>> update_visit_foriegn_details(BigInteger comm_id,int census_id);
}
