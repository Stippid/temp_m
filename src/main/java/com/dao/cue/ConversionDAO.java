package com.dao.cue;

import java.util.List;
import java.util.Map;

public interface ConversionDAO {
    
	public List<String> getSusNoList();
	public List<String> getUnitsNameList();
	public List<String> getWeNoList();
	public List<String> getPeNoList();
	public List<String> getFeNoList();
	public List<String> getGoiNoList();
	public List<String> getWetNoList();
	public List<String> getPetNoList();
	public List<String> getFetNoList();
	public  List<Map<String, Object>> getSearchWetToWe(String we_pe,String we_pe_no,String wet_link_status ,String roleType);
	public  List<Map<String, Object>> getSearchWetToWe1(String we_pe,String we_pe_no,String wet_link_status ,String roleType,String roleAccess,String roleSusNo);
	public String DeleteWETToWEUrlAdd(int appid);
	public String DeleteWETToWE(int appid);
	 
}
