package com.dao.psg.xml;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.w3c.dom.NodeList;

import com.model.psg.xml.XML_FILE_UPLOAD;

public interface XMLDao {
	public long getUploadedFiles_count(int Status, String army, String name,String frm_dt1,
			String to_dt1,String Search, String orderColunm, String orderType, String present_p2_no,
			String selected_id,String unit_no,String unit_name,HttpSession sessionUserId);
	public ArrayList<ArrayList<String>> getShapeData_xml(String shape,BigInteger comm_id,int status);
	public List<Map<String, Object>> getUploadedFiles(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId, String name, String army,String frm_dt1,String to_dt1,
			int status,String present_p2_no,String selected_id,String unit_no,String unit_name);
	public List<Map<String, String>> update_award_medal_history_xml(BigInteger comm_id) ;
	public ArrayList<ArrayList<String>> change_nok_history_dtl_xml(BigInteger comm_id);
	public ArrayList<ArrayList<String>> update_child_dtl_XML(BigInteger comm_id);
	public ArrayList<ArrayList<String>> change_Contact_xml(BigInteger comm_id);
	public ArrayList<ArrayList<String>> GetServingStatus_xml(BigInteger comm_id);
	public ArrayList<ArrayList<String>> update_non_effective_status_details_xml(BigInteger comm_id);
	public ArrayList<ArrayList<String>> GetCensusDataApprove_xml(BigInteger comm_id);
	public List<Map<String, Object>> getPost_in_data(BigInteger comm_id,int cat,int status);

	public long getcasualitycount(int Status,String Search, 
			String orderColunm, String orderType, String comm_id_casualty,String upload_id_casualty,HttpSession sessionUserId);
	public List<Map<String, Object>> getcasualitydata(int startPage, int pageLength, String search, 
			String orderColunm,String orderType, HttpSession sessionUserId, int status,String comm_id_casualty,String upload_id_casualty);
	public String  insertCasualty( NodeList casualtyNodes, int status_xml, String personnel_no,XML_FILE_UPLOAD xml_upload);
	public List <String> getcolumname();
	///public String deletexml(String xml_file_upload_id, String casualty_no);
}
