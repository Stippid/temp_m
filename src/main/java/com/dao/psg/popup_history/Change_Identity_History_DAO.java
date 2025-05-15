package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;



public interface Change_Identity_History_DAO {
	public ArrayList<ArrayList<String>> change_in_identity_card_dtl(BigInteger comm_id,int census_id);


}
