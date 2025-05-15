package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Inter_Arm_Service_Transfer_History_DAO {
	public ArrayList<ArrayList<String>> Inter_Arm_Service_Transfer(BigInteger comm_id,int census_id);
}
