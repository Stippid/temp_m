package com.dao.avation;

import java.util.ArrayList;
import java.util.List;

public interface AVNDRRDAO {
	
	public  ArrayList<List<String>> getsearch_drr_avn(String status,String from_date,String curr_date,String roleType);
	public  ArrayList<List<String>> getApprovedTail_NoFromDRRAVN(String viewSerNo,String viewStatus,String viewDate,String viewSus,String roleType);
	public boolean ifExistDRRNo(String drr_ser_no);
}
