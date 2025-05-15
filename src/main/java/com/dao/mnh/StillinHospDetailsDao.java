package com.dao.mnh;

import java.util.List;
import java.util.Map;

public interface StillinHospDetailsDao {
	public  List<Map<String, Object>> Searchstillrpt(String sus1,String from_date1,String to_date1,String persnl_no1,String and_no1);

}
