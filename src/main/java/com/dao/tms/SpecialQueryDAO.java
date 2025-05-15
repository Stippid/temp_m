package com.dao.tms;

import javax.servlet.http.HttpSession;

public interface SpecialQueryDAO {
	
	public Boolean ifExistbano(String ba_no);
	public Boolean ifExistbanoChild(String ba_no);
	public Boolean ifExistbanoMVCR(String ba_no);
	public Boolean ifExistbanoDRRDIR(String ba_no);
	public Boolean ifExistbano(String vehicle_category, String ba_no);
	public Boolean RevertBaNoDetails(String vehicle_category, String ba_no);
	public String baNoGeneration(int serialNo,String veh_code);
	public String getvehcatfromBANo(String ba_no,HttpSession session);
}
