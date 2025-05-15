package com.dao.tms;

import java.util.ArrayList;

public interface MctSlotDAO {
	
	public String getmaxMCTMainID(String from,String to);
	public Boolean checkIfExistMainID(String mainId);
	public Boolean checkIfExistMCTRange(int code_from,int code_to);
	public String getmaxPRFCodeID();
	public ArrayList<ArrayList<String>> search_mct_main(String mct_slot,String prf_code,String type_of_veh,String sus_no);

}
