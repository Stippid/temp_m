package com.dao.psg.Transaction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Search_PostOutDao {

	public ArrayList<ArrayList<String>> search_postout(String roleSusNo, String personnel_no, String rank,
			String to_sus_no, String cr_by, String cr_date, String roleType, String status, String roleSusNo2);

	public List<Map<String, Object>> getPost_OutByid(int id, int cat);

	public List<Map<String, Object>> getPost_InByid(int id, int cat);

	public Boolean getpernoAlreadyExits(BigInteger comm_id);

	public ArrayList<ArrayList<String>> search_postin(String roleSusNo, String personnel_no, String rank,
			String to_sus_no, String cr_by, String cr_date, String roleType, String status, String roleSusNo2);

	ArrayList<ArrayList<String>> search_jcopostout(String roleSusNo, String personnel_no, String rank, String to_sus_no,
			String roleType, String status);

	ArrayList<ArrayList<String>> search_jcopostin(String roleSusNo, String personnel_no, String rank, String to_sus_no,
			String roleType, String status);

	public ArrayList<ArrayList<String>> getLocation_Trn(String to_sus_no);

	public ArrayList<ArrayList<String>> getPosttenuredate(BigInteger comm_id);

	public ArrayList<ArrayList<String>> GetCommDataApprove(BigInteger comm_id);

	public ArrayList<ArrayList<String>> search_postoutMns(String roleSusNo, String personnel_no, String rank,
			String to_sus_no, String cr_by, String cr_date, String roleType, String status, String roleSusNo2);

	public ArrayList<ArrayList<String>> search_postinMns(String roleSusNo, String personnel_no, String rank,
			String to_sus_no, String cr_by, String cr_date, String roleType, String status, String roleSusNo2);
}
