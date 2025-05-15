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

public interface Cause_of_wastage_DAO {
	
	public ArrayList<ArrayList<String>> getCause_of_wastage();
	public long getwestegeReportDataListcountDtl(String Search) ;
	 public List<Map<String, Object>> getwestegeReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	
}
