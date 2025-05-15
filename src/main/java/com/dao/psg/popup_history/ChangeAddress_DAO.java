package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface ChangeAddress_DAO {
	public List<Map<String, Object>> change_address_details(BigInteger comm_id,int census_id);
}
