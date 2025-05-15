package com.dao.psg.JCO_Report;

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

public interface JCO_Command_Wise_STR_DAO {

	public List<Map<String, Object>> getCommand_Wise_STR_ReportJco_DataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,HttpSession session) 
		throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	
	public long Command_Wise_STR_Jco_TotalCountDtl(String Search);
	
	public ArrayList<ArrayList<String>> pdf_Command_Wise_STR_Jco_ReportDataList();
	
}
