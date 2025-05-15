package com.dao.mnh;

import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_Unusual_Occurrence;



public interface Unusual_Occurrence_DAO {

	public Long checkExitstingunusualExistlNo(String sus_no,String persnl_no,int id);
	 public List<Map<String, Object>> Search_Unusual_Occurrence_Detail(String sus_no, String frm_dt, String to_dt,String adhar1,String contact1) ;
	public Tb_Med_Unusual_Occurrence getUnusual_OccurrenceByid(int id);
	 public String updateunusualoccurence(Tb_Med_Unusual_Occurrence obj,String username);
}
