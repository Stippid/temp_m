package com.dao.psg.popup_history;
import java.math.BigInteger;
import java.util.ArrayList;
public interface Update_BPETDetails_History_DAO {
	public ArrayList<ArrayList<String>> update_bpet_details(BigInteger comm_id,int census_id);

}
