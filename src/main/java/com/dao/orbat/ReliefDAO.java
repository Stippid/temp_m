package com.dao.orbat;

import java.util.ArrayList;
import java.util.List;

import com.models.Miso_Orbat_Relief_Prgm;

public interface ReliefDAO {
	public List<Miso_Orbat_Relief_Prgm> getReliefReportList(String period_from,String period_to,String status,String sus_no);
	public List<Miso_Orbat_Relief_Prgm> getSearchReliefReportList(String auth_letter,String status);
	public List<String> getSearchReliefReportList(String auth_letter,String ser_no,String status);
	public String approvedSdReliefDetails(String auth_letter,String status,String username,String date);
	public String rejectedSdReliefDetails(String auth_letter, String ser_no, String status,  String username,String date);
	public List<Miso_Orbat_Relief_Prgm> getsearchMainBodyReportList(String sus_no,String status,String unit_name);
	public String approvedUnitReliefDetails(int id,String status,String username,String date,String sus_no);
	public String approvedSdReliefDetails(String auth_letter, String status, String ser_no1, String username, String date);
	
	public ArrayList<List<String>> getAmendmentReportList();
	public Miso_Orbat_Relief_Prgm getLatLon(int id);
	public String deleteReliefPro(int entryId); 
	
	public Miso_Orbat_Relief_Prgm editamend(int id);
	public Miso_Orbat_Relief_Prgm UpdateDataEntry(Miso_Orbat_Relief_Prgm rp);
	public Miso_Orbat_Relief_Prgm UpdateAmendment(Miso_Orbat_Relief_Prgm rp);
	public List<Miso_Orbat_Relief_Prgm> getRelief_ProgByid(int id);
	
	public List<Miso_Orbat_Relief_Prgm> getReliefNReportList(String period_from,String period_to,String status,String sus_no);
	

}
