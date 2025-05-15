package com.dao.psg.Jco_Census;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_SIBLINGS;

public interface Search_JCOsDao {

	public ArrayList<ArrayList<String>> Search_JCOs(String roleSusNo,String army_no,String status,String rank,String unit_name,String unit_sus_no, HttpSession session);
	//public List<Map<String, Object>> View_jcoByid(int id);
	public TB_CENSUS_JCO_OR_PARENT getJCOsByid(int id);
	
	public ArrayList<ArrayList<String>> getShapeData_jco(String shape,int jco_id,int status);
	public Boolean getArmyNoAlreadyExist(String army_no,String jco_id,String army_id);
	public String getjco_IdentityImagePath(String id);
	public TB_CENSUS_JCO_OR_SIBLINGS getJCOSiblingByid(int jco_id);
	public ArrayList<ArrayList<String>> GetAllJCO_OR_Details(int id);
	public ArrayList<ArrayList<String>> getSiblings_Value(int jco_id);
	public ArrayList<ArrayList<String>> getSpouse_Value(int jco_id);
	public ArrayList<ArrayList<String>> getDIV_WID_Value(int jco_id);
	public ArrayList<ArrayList<String>> getSpouse_Education_Value(int jco_id);
	public List<Map<String, String>> getQualificationDataSpouse(int jco_id,int status);
	
	public ArrayList<ArrayList<String>> getcommand_JCO(String roleSusNo);
	
	
	public ArrayList<ArrayList<String>> View_GetAllJCO_OR_Details(int id) ;
	
	
	/// bisag v2 260822  (converted to Datatable)
    public long GetSearch_censusCount_jco(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String unit_sus_no,String unit_name,String personnel_no,
				String rank,String status,String cr_by,String cr_date,String roleType);
	 public List<Map<String, Object>> GetSearch_censusdata_jco(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
			 String unit_sus_no,String unit_name,String personnel_no,
				String rank,String status,String cr_by,String cr_date,String roleType) ;
}
