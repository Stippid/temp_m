package com.dao.psg.Report;

import java.util.ArrayList;

public interface Report_3008DAO {
	
	public ArrayList<ArrayList<String>> Search_Report_3008_Serving(String roleSusNo,String FDate);
	//public ArrayList<ArrayList<String>> Search_Report_3008_Serving(String roleSusNo,String month,String year, Object[] ah);

	//public ArrayList<ArrayList<String>> Search_Report_3008_ReEmployed(String roleSusNo,String month,String year,Object[] sp);

	
	public ArrayList<ArrayList<String>> Pdf_Search_Report_3008_Serving(String roleSusNo,String month,String year, Object[] pdf_ah);
	public ArrayList<ArrayList<String>> Pdf_Search_Report_3008_Super(String roleSusNo,String month,String year);
	public ArrayList<ArrayList<String>> Pdf_Search_Report_3008_ReEmployed(String roleSusNo,String month,String year,Object[] pdf_sp);
	public ArrayList<ArrayList<String>> Pdf_Search_Report_3008_Auth_Held(String roleSusNo,String month,String year,Object[] pdf_cp);
	
	public ArrayList<ArrayList<String>> getEstablishNo(String roleSusNo);
	public ArrayList<ArrayList<String>> getReport_Data(String roleSusNo);
	public ArrayList<ArrayList<String>> getcommand(String roleSusNo);
	public ArrayList<ArrayList<String>> getcommandpost_inout(String roleSusNo);
	public boolean refreshIAF_3008MaterializedViews();


	
}
