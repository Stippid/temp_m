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

public interface JCO_Arms_ServiceWise_DAO {
	
	
	public List<Map<String, Object>> getArmServices_Wise_JCOReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException,NoSuchPaddingException, InvalidKeySpecException, 
	                             InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long getArmServices_Wise_JCOTotalCountDtl(String Search);
	public ArrayList<ArrayList<String>> pdf_getArmServices_Wise_JCO_ReportDataList();

}
