package com.dao.psg.Civilian_Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Arm_Service_Wise_RegularEst_Dao {
	public ArrayList<ArrayList<String>> Search_Arm_Service_Wise_Regular();
	public ArrayList<ArrayList<String>> getLocation_Trn(String to_sus_no);
	 public ArrayList<ArrayList<String>> getCivcommand(String roleSusNo);
	 public List<Map<String, Object>> getLastPostInOutCIVILIAN(int civ_id);
}
