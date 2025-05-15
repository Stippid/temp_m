package com.dao.tms;

import java.util.ArrayList;

public interface  Qfd_DAO {
	
	public ArrayList<ArrayList<String>> search_Qfd(String type_veh,
			//String prf_code,
			String mct_main,String line_dte_sus,int kms,int vintag,int py,String type_force,String checkVal,String cmd,String line_dte_sus_main);

}
