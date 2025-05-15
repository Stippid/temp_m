package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Update_Discipline_History_DAO {

	public ArrayList<ArrayList<String>> update_discipline(BigInteger comm_id,int census_id);
}
