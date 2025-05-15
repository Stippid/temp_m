package com.dao.psg.Master;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;



public interface Psg_CommanDAO {
	
	/*public List<String> getParentArmList(String arm_desc);*/
	public ArrayList<List<String>> getCorpsList(String fcode);
	public ArrayList<List<String>> getPSG_BdeList();
	public ArrayList<List<String>> getPSG_DivList();
	public ArrayList<List<String>> getPSG_CorpsList();
	public ArrayList<List<String>> getPSG_CommandList();
	public List<String> getMNHEncList(List<String> l, HttpSession s1);
	public List<String> getIssuingAuthorityList(String unit_name);
	public ArrayList<ArrayList<String>> getShapeData(String shape, int status, String comm_id);
	public ArrayList<ArrayList<String>> getforiegnCountry();
	String getCmdSusFromUnitSus(String sus_no);
	
	public ArrayList<ArrayList<String>> getSelfMotFatName(BigInteger comm_id);
	public ArrayList<ArrayList<String>> getSpouseName(BigInteger comm_id);
	public ArrayList<ArrayList<String>> getChildName(BigInteger comm_id,String rela);
	public ArrayList<ArrayList<String>> getChilddob(String  id,BigInteger comm_id);
	public ArrayList<List<String>> getPersonnelNofromcomm(BigInteger comm_id);
	public ArrayList<List<String>> getArmyNofromJco(int jco_id);
	public List<String> getColumnList();
	public int checkMedicalDtlFillIn3008(String comm_id);
	
	public List<String> getIssuingAuthorityListIdCard(String unit_name);
	public List<String> getIssuingAuthoritySusListIdCard(String sus_no);
	
	

}
