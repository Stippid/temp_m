package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface Update_Awards_Medal_History_DAO {
	public List<Map<String, String>> update_award_medal_history(BigInteger comm_id,int census_id) ;

}
