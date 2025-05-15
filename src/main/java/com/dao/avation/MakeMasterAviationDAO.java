package com.dao.avation;

public interface MakeMasterAviationDAO {
    
	public String getmaxMakeNo(String actSlotId);
	public Boolean ifExistMakeNo(String act_slot_id, String make_no);
	 
}
