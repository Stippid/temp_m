package com.dao.mnh;

import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_HIV;



public interface mnh_hivDAO {

	public Long checkExitstinghivinput(String sus_no1,String type,String persnl_no,String id1);
	public List<Map<String, Object>> search_hiv_Input(String sus1,String unit1, String psn_no, String frm_dt1, String to_dt1,String adhar1,String contact1,String relation1);
	 public Tb_Med_HIV gethivDetail(int id);
	 public String update_hiv(Tb_Med_HIV obj,String username);
	 
}
