package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Update_Battle_And_Physical_Casuality_History_DAO {
	public ArrayList<ArrayList<String>> update_battle_and_physical_casuality(BigInteger comm_id,int census_id);
}
