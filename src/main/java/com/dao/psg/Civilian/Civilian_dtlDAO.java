package com.dao.psg.Civilian;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.psg.Civilian.TB_CIVILIAN_DETAILS;


public interface Civilian_dtlDAO {
	public ArrayList<ArrayList<String>> Search_Civilian();
	public TB_CIVILIAN_DETAILS getTB_CIVILIAN_DETAILSByid(int id);
	public ArrayList<ArrayList<String>> Search_Civilian_non_regular(String roleSusNo,String roleType,String unit_sus_no,String unit_name,String first_name,int status,String personnel_no, String cr_by,String cr_date,HttpSession session);
	public ArrayList<ArrayList<String>> Search_Civilian_regular(String roleSusNo,String roleType,String unit_sus_no,String unit_name,String first_name,int status,String personnel_no, String cr_by,String cr_date,HttpSession session);
	public ArrayList<ArrayList<String>> Report_Civilian_regular(String unit_sus_no, String first_name);
	public List<Map<String, Object>> getApproveDataForviewnonregular(int id);
	public List<Map<String, Object>> getAllAppDataForviewregular(int id);
	public String getEmpNoAlreadyExits(String employee_no);
	public ArrayList<List<String>> GetEmployeeNoData(String employee_no);
	public Boolean getEmpNoAlreadyExist(String emp_no);
	
	public String getRegularIdentityImagePath(String id);
}
