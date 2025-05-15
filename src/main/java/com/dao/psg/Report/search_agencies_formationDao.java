package com.dao.psg.Report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface search_agencies_formationDao {
	
	public List<Map<String, Object>> getallSearchagencies(int startPage,int pageLength,String Search,String orderColunm,String orderType,String personnel_no,
			String rank,HttpSession sessionUserId,
			String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String dt_of_casuality,String scase,String agency_id,String type_of_benefits_id) throws SQLException;
	public long getSearchagenciesCount(String Search,String orderColunm,String orderType,String personnel_no,String rank,HttpSession sessionUserId,
			 String cont_comd,String cont_corps,
				String cont_div,String cont_bde,String unit_name,String sus_no,String dt_of_casuality,String scase,String agency_id,String type_of_benefits_id)throws SQLException;
	
	/////
	/*public ArrayList<ArrayList<String>> View_Emoluments_History(int comm_id);
	public ArrayList<ArrayList<String>> View_Emoluments_History_formation(int comm_id);*/
}
