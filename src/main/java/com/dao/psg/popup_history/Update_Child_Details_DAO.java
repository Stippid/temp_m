package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Update_Child_Details_DAO {
	public ArrayList<ArrayList<String>> update_child_dtl(BigInteger comm_id,int census_id);

}
