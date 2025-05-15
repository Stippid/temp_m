package com.dao.tms;

import java.util.ArrayList;

public interface Veh_cat_link_DAO {
	
	public ArrayList<ArrayList<String>> search_veh_name(String type_veh,String prf_code,String mct_main,String line_dte_sus);

}
