package com.dao.psg.Queries;

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

public interface Contact_Detailsof_PersDAO {
	
	
	public List<Map<String, Object>> getContact_Detailsof_Pers_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,HttpSession session) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	public long getContact_Detailsof_Pers_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no);


	
	public List<Map<String, Object>> getSummaryof_Total_Number_ReportDataListDetails_pers(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,HttpSession session) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException; 
	public long getSummaryof_Total_Number_TotalCountDtl_pers(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no);

	
	
	public ArrayList<ArrayList<String>> pdf_getContact_Detailsof_Pers_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no);
	public ArrayList<ArrayList<String>> pdf_getSummaryof_Total_Number_ReportDataList_pers(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no);
	

}
