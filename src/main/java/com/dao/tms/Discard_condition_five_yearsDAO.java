package com.dao.tms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface  Discard_condition_five_yearsDAO {
	
	public ArrayList<ArrayList<String>> search_veh_name(String type_veh,
			String month,String ddlYears,String checkVal);


}
