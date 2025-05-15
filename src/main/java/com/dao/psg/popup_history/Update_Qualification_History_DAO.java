package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Update_Qualification_History_DAO {
	
	public List<Map<String, Object>> update_qualification(BigInteger comm_id,int cen_id);

}
