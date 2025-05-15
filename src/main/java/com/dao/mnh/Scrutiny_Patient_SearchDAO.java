package com.dao.mnh;

import java.util.List;
import java.util.Map;

public interface Scrutiny_Patient_SearchDAO {

	
	public List<Map<String, Object>> search_patient_details_datascrutiny(String sus1,String name1,String rk1,String andno1,String pno1,String pname1,String frm_dt1,String to_dt1);
}
