package com.dao.cue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.web.servlet.ModelAndView;

import com.models.CUE_TB_MISO_WEPECONDITIONS;

public interface Cue_wepe_conditionDAO {
	public List<Map<String, Object>> AttributeReportSearchWePecondition(String we_pe,String we_pe_no ,String uploaded_wepe,String sponsor_dire,String arm,String doc_type,String getroleid, String status, String roleType,String roleAccess);

	public List<Map<String, Object>> AttributeReportSearchWePecondition1(String uploaded_wepe,String we_pe_no,String we_pe,String sponsor_dire,String arm,String doc_type,String status, String setTypeOnclick);
	public String setApprovedwepecondition(int appid,int roleid,String username,String newRoleid,String setTypeOnclick);
	public String setDeleteARM(int appid);
	public CUE_TB_MISO_WEPECONDITIONS getcue_wepeByid(int id);
	public String UpdateArtAttValue(CUE_TB_MISO_WEPECONDITIONS artAttValues, String username,String arm_val);
	
	public  List<Map<String, Object>> getSearch_inlieu_foot_transportReport(String status,String we_pe_no,String mct_no,String in_lieu_mct, String roleType);
	public  List<Map<String, Object>> getSearch_inlieu_foot_transportReport1(String we_pe_no,String mct_no,String in_lieu_mct, String status, String roleType); 
	public  List<Map<String, Object>> getAttributeFromCategorySearchWePe(String we_pe,String we_pe_no,String sponsor_dire,String arm,String doc_type, String status, String roleType) ;
	public List<Map<String, Object>> getAttributeFromCategorySearchWePe1(String we_pe, String we_pe_no, String doc_type,
            String arm, String sponsor_dire, String status, String roleType, String from_date, String to_date,String roleAccess);
	public  List<Map<String, Object>> getWEPElinkwithWETPET(String we_pe,String we_pe_no,String roleType,String wet_link_status) ;
	public  List<Map<String, Object>> getWEPElinkwithWETPET1(String we_pe,String we_pe_no,String wet_link_status,String roleType,String roleAccess,String roleSusNo);
	public  List<Map<String, Object>> getAttributeFromCategorySearchWetPet(String wet_pet,String wet_pet_no,String status,String roleType,String from_date,String to_date, String roleAccess,String roleSusNo) ;


	public Object updatecapdatawepe(String we_pe_no, String superno, int roleid, String username, String roleType,String nwepe,String type,String Pstatus);

	public boolean check_data(String superno,String tablename);

	public ArrayList<ArrayList<String>> getWepeData(String sus_no);

	public String updatePersDetails(String we_pe_no, String type, String username, String we_pe);


	public boolean iswe_pe_exits(String we_pe_no, String type);

	public void updateVettingDtl(String we_pe_no, String string);

		
}
