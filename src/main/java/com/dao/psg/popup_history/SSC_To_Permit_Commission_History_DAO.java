package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface SSC_To_Permit_Commission_History_DAO {

	public ArrayList<ArrayList<String>> ssc_to_permit_commission_dtl(BigInteger comm_id,int census_id);
}
