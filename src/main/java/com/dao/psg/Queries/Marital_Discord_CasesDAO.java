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

public interface Marital_Discord_CasesDAO {
	
	
	public List<Map<String, Object>> getMarital_Discord_Cases_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long getMarital_Discord_Cases_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to);


	
	public List<Map<String, Object>> getMarital_Discord_Cases_ReportDataListDetails_pers(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException; 
	public long getMarital_Discord_Cases_TotalCountDtl_pers(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to);

	
	public ArrayList<ArrayList<String>> pdf_getMarital_Discord_Cases_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to);
	public ArrayList<ArrayList<String>> pdf_getMarital_Discord_Cases_ReportDataList_pers(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to);
	
}
