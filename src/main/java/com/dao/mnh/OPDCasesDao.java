package com.dao.mnh;

import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_Opd_Cases;



public interface OPDCasesDao {
	public List<Map<String, Object>> CaptureOPDCase(String sus1,String qtr1, String yr1,String ward1); 
	 public Tb_Med_Opd_Cases getOPDCasesByid(int id);
	 public String Update_OPDCases(Tb_Med_Opd_Cases obj,String username);
	 public String delete_input_opd_cases(int deleteid);
	 public Long checkExitstingopdinput(String sus_no1,String quater,int year,int id,String ward); 
}
