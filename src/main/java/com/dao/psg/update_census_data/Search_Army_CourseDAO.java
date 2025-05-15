package com.dao.psg.update_census_data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.psg.update_census_data.TB_CENSUS_ARMY_COURSE;

public interface Search_Army_CourseDAO {

	ArrayList<ArrayList<String>> Search_ArmyCourseList(String personnel_no, String status, String unit_sus_no, String unit_name, String rank,
			String cr_by, String cr_date, String roleSusNo, String roleType);

	public List<Map<String, Object>> getHisChangeOfArmyCourse(BigInteger comm_id, int status, HttpSession sessionA);
	public TB_CENSUS_ARMY_COURSE getseracharmyid(int id);

	
		// pranay 25.09 
	public ArrayList<ArrayList<String>> Popup_Change_of_armycourse(BigInteger comm_id);

	
	
	

}
