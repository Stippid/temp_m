package com.dao.Reports;

import java.util.List;
import java.util.Map;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import javax.servlet.http.HttpSession;

public interface Capture_critical_storeDAO {

	public List<Map<String, Object>> getReportListCapture_critical_store(int startPage, String pageLength,
			String Search, String orderColunm, String orderType, HttpSession session)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long getReportListCapture_critical_storeTotalCount(String Search);

	public String Deletecapture_critical_store(String deleteid, HttpSession session);

	public List<Map<String, Object>> getReport_by_typelist(int startPage, String pageLength, String Search,
			String orderColunm, String orderType, HttpSession session, String c_type)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long getReportListBy_typeTotalCount(String Search, String c_type);
	
	public List<Map<String, Object>> getAllCapture_critical_storeItems(String c_type,String code);

}
