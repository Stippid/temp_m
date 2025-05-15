package com.dao.mnh;

import java.util.ArrayList;


public interface SHO_FHO_SurveillanceDao {

	
//////search_Surve
	public ArrayList<ArrayList<String>> search_Surve(String sus,String date_from,String date_to,String type);

	

}
