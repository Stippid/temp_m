package com.dao.mnh;

import java.util.ArrayList;

public interface FHO_SHO_PreventiveDAO {
	
	public ArrayList<ArrayList<String>> getmalariyadeatails(int id);
	public ArrayList<ArrayList<String>> getSTDdeatails(int id);
	public ArrayList<ArrayList<String>> get_Viral_Hepatitis_deatails(int id) ;
	public ArrayList<ArrayList<String>> get_h1n1_deatails(int id);
	public ArrayList<ArrayList<String>> get_injuries_nea_deatails(int id);
	public ArrayList<ArrayList<String>> get_pulmonary_tuberculosis_deatails(int id);
	public ArrayList<ArrayList<String>> get_milkquality_deatails(int id);
	public ArrayList<ArrayList<String>> gethealthedu_deatails(int id);
	public ArrayList<ArrayList<String>> getworkshop_deatails(int id);

	public ArrayList<ArrayList<String>> search_preventive_deatails(String sus_no,String month,String year);
	
	
	public String gethealthImagePath(String id);
	public String getworkImagePath(String id);

}
