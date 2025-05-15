package com.dao.mnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Ad_Hoc_mnh_Dao {
	


	 public List<Map<String, Object>> search_adhoc_dtl( String gender1, String unit_name2, String category1,String rank1,String relation1,String admsn_type1,String disposal1,String command1);


}
