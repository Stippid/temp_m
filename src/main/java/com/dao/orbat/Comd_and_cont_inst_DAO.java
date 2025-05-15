package com.dao.orbat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.TB_MISO_ORBAT_COMD_CONT;

public interface Comd_and_cont_inst_DAO {
	
	
	public ArrayList<ArrayList<String>> SearComdAndContInstDtlForDistribution(String status, String issue_date,
			String to_date, String arm, String sus_no, String command_name,
			HttpSession session); 
	
	public List<Map<String, Object>> getComdAndContInstDetails(int id);
	
	public List<Map<String, Object>> getSdMoveDtl(String sus_no);

}
