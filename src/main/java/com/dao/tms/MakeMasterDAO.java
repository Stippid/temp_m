package com.dao.tms;

public interface MakeMasterDAO {
    
	public String getmaxMakeNo(String mctSlotId);
	public Boolean ifExistMakeNo(String mct_slot_id, String make_no);
	 
}
