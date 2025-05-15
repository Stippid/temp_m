package com.dao.psg.Transaction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Search_UpdateComm_Dao {
	
	public ArrayList<ArrayList<String>> Search_S_C_LData(String personnel_no, String status, String rank,
			String unit_sus_no, String unit_name, String cr_by, String cr_date, String roleSusNo, String roleType,String roleAccess,Boolean IsMns) ;
	public ArrayList<ArrayList<String>> GetCommissionDataApprove(BigInteger comm_id);
	public ArrayList<ArrayList<String>> gettenuredate(BigInteger comm_id);


}
