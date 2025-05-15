package com.dao.psg.Report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface search_formation_wise_dataDao {
	
	public List<Map<String, Object>> getallformationbattlecass(int startPage,int pageLength,String Search,String orderColunm,String orderType,String personnel_no,
			String rank,HttpSession sessionUserId,
			String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) throws SQLException;
	public long getallformationbattlecassCountList(String Search,String orderColunm,String orderType,String personnel_no,String rank,HttpSession sessionUserId,
			 String cont_comd,String cont_corps,
				String cont_div,String cont_bde,String unit_name,String sus_no)throws SQLException;
	
}
