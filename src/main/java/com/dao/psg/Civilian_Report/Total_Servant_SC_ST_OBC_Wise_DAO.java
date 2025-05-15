package com.dao.psg.Civilian_Report;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

public interface Total_Servant_SC_ST_OBC_Wise_DAO {

	 public List<Map<String, Object>> getPer_Temp_SC_ST_OBC_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String service_status,HttpSession session) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	 public long getPer_Temp_SC_ST_OBC_TotalCountDetail(String Search,String cont_comd,String cont_corps,String cont_div,
			 String cont_bde,String unit_name,String sus_no,String service_status);
	 
	 public List<Map<String, Object>> getNon_Regular_SC_ST_OBC_ReportDataListDetail(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,HttpSession session) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	 public long getNon_Regular_SC_ST_OBC_TotalCountDetail(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no);
	 
	 public ArrayList<ArrayList<String>> PDF_getPer_Temp_SC_ST_OBC_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String service_status);
	 
	 public ArrayList<ArrayList<String>> PDF_getNon_Regular_SC_ST_OBC_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no);
	 
}
