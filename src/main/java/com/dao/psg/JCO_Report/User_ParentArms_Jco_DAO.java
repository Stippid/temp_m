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
public interface User_ParentArms_Jco_DAO {
	
	
	public List<Map<String, Object>> getUser_ParentArms_ReportDataListDetailsJco(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	public long getUser_ParentArms_TotalCountDtlJco(String Search);
	
	 public List<Map<String, Object>> getUser_ParentArms_reporttbl_ctpart2DetailsJco(int startPage,String pageLength,String Search,String orderColunm,String orderType,
				HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException ;
	 public long getUser_ParentArms_reporttbl_ctpart2_TotalCountDtlJco(String Search);

	public ArrayList<ArrayList<String>> pdf_getUser_ParentArms_ReportDataList_Jco();
	public ArrayList<ArrayList<String>> pdf_getUser_ParentArms_ReportDataListctpart2_Jco();

}
