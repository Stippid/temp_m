package com.dao.mnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface MNH_Common_DAO {
	
	public List<String> getMNHEncList(List<String> l,HttpSession s1);
	
	public List<String> getMNHHirarNameBySUS(String FindWhat, String nSUSNo);
	
	public List<String> getMNHDistinctHirarName(String nHirar, String nCnd, String codelevel);
	
	public List<String> getMNHRank(String a1,String a2);
	
	public ArrayList<ArrayList<String>> getMNHRData(int userid);
	
	public List<Map<String, String>> GetPersonnelNoDatamnh(String personnel_no) ;
	 public List<String> getMNHCommandName() ;
}
