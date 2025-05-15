package com.dao.mms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.MMS_RIO_Generation;
import com.models.MMS_RO_Generation;

public interface MMS_RO_RIO_GenerationDAO {
	public String getmaxroCode();
	public ArrayList<ArrayList<String>> RoApproverScreen(String formcodeType,String formcode,String p_code,String d_from,String d_to,String ro_status);
	public ArrayList<ArrayList<String>> CmndGnrtScreen(String q);
	public ArrayList<ArrayList<String>> CmndRoApproverScreen(String formcodeType,String formcode,String p_code,String d_from,String d_to,String ro_status);
	public String approveRORqst(String ro_no,String ro_type,String para,String username);
	public String Extendro_date(String ro_no,String para,int ro_id);
	public String ROCancelApprover(String ro_no,String para,int ro_id);
	public MMS_RO_Generation viewMMSCMNDRo(int ro_no);
	public ArrayList<ArrayList<String>> RoPrintScreen(String ro_no);
	public List<MMS_RO_Generation> printMMSRo(String ro_no);
	public List<MMS_RIO_Generation> printMMSRio(String rio_no);
	public List<String> GetRioGenerationFromRO(int id,String t_name);
	public ArrayList<ArrayList<String>> RioRequestScreen();
	public String getmaxrioCode();
	public ArrayList<ArrayList<String>> RioApproverScreen(String formcodeType,String formcode,String p_code,String d_from,String d_to,String rio_status); 
	public ArrayList<ArrayList<String>> RioPrintScreen(String rio_no);
	public List<String> ifExistRORIONo(String ro_no,String to_sus_no);
	
	public List<Map<String, Object>> getUnit_ue_uh(String unit_id, String prfcode) throws SQLException;
	public List<Map<String, Object>> getprfgroup_wise_depo(String prfcode) throws SQLException;
	public List<Map<String, Object>> getdept_wiseitem(String prfcode,String search) throws SQLException;
	
	public List<Map<String, Object>> unitanditemcode_wise_census(String itemcode, String unit_sus,String type_of_eqpt ) throws SQLException;
	public List<Map<String, Object>> get_corps(String fomationcode) throws SQLException;
	public List<Map<String, Object>> get_division(String fomationcode) throws SQLException;
	public List<Map<String, Object>> get_bde(String fomationcode) throws SQLException;
	public List<Map<String, Object>> get_unit(String fomationcode) throws SQLException ;
	public List<Map<String, Object>> getdepot_item(String prfcode,String depotsus) throws SQLException;
	
	public ArrayList<ArrayList<String> >  getROReport(String sus_no,String fdate ,String tdate ,Integer status,String roleType,String depot_sus_no,HttpSession sessionA);
	public boolean delete_reg_no(int id,int yet_to_collect);

}
