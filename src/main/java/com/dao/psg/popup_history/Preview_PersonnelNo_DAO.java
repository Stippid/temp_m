package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Preview_PersonnelNo_DAO {
	
	public ArrayList<ArrayList<String>> preview_personnelNo(String personnel_no);
	public ArrayList<ArrayList<String>> preview_cadetNo(BigInteger comm_id,String personnel_no);//
	public ArrayList<ArrayList<String>> preview_courseNo(BigInteger comm_id,String personnel_no);//
	public ArrayList<ArrayList<String>> preview_gender(BigInteger comm_id,String personnel_no);//
	public ArrayList<ArrayList<String>> preview_commission(BigInteger comm_id,String personnel_no);//
	public ArrayList<ArrayList<String>> preview_dateofbirth(BigInteger comm_id,String personnel_no);//
	public ArrayList<ArrayList<String>> preview_armservice(BigInteger comm_id);
	public ArrayList<ArrayList<String>> preview_unit(BigInteger comm_id,String personnel_no);
	public ArrayList<ArrayList<String>> preview_rankNo(BigInteger comm_id);
}
