package com.dao.orbat;

import java.util.ArrayList;

public interface Create_Unit_moveDAO {
	public ArrayList<String> getAllBodyDetailsList(String sus_no);
	
	public ArrayList<ArrayList<String>> getInstrList(String nPara);
	
	public ArrayList<ArrayList<String>> getSearchNReliefReportList(String auth_letter,String status);
	
	public ArrayList<ArrayList<String>> getSDreliefreport(String auth_letter);
}
