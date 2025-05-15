package com.dao.psg.update_census_data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Re_EmploymentDAO {

	public ArrayList<ArrayList<String>> search_re_employment(String unit_name,String unit_sus_no,String personnel_no1 ,String status,String cr_by,String cr_date,String type_radio,String roleSusNo,String roleType);


	public ArrayList<ArrayList<String>> search_re_call(String personnel_no1,
			String status, String unit_sus_no, String unit_name,
			String cr_by,String cr_date,
			String roleSusNo, String roleType,String roleAccess);

	//public Boolean getre_emp_call_data(String personnel_no);

	public List<Map<String, Object>> getre_emp_call_data(String personnel_no);

	public ArrayList<ArrayList<String>> gettenuredate(BigInteger comm_id);

}
