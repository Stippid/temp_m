package com.dao.psg.Queries;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

public interface IAFF_3008DAO {

	public List<Map<String, Object>>getIAFF_3008_Serving_Report_ListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year,
			 HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, 
			 InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	public long getIAFF_3008_Serving_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,
			String cont_bde,String unit_name,String sus_no,String month,String year);
	
	public List<Map<String, Object>>getIAFF_3008_Supernumerary_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year,
			 HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, 
			 InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	public long getIAFF_3008_Supernumerary_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,
			String cont_bde,String unit_name,String sus_no,String month,String year);
	
	public List<Map<String, Object>>getIAFF_3008_Deserter_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year,
			 HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, 
			 InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	public long getIAFF_3008_Deserter_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,
			String cont_bde,String unit_name,String sus_no,String month,String year);
	
	public List<Map<String, Object>>getIAFF_3008_Re_Employeement_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year,
			 HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, 
			 InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	public long getIAFF_3008_Re_Employeement_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,
			String cont_bde,String unit_name,String sus_no,String month,String year);
}
