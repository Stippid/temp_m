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

public interface Capture_second_third_lineDAO {

	public List<Map<String, Object>> getReportListCapture_critical_store(int startPage, String pageLength,
			String Search, String orderColunm, String orderType, HttpSession session)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	public long getReportListCapture_critical_storeTotalCount(String Search);
	public String Deletecapture_second_third_store(String deleteid, HttpSession session);
	
	public List<Map<String, Object>> second_third_line_tpt_reportData(int startPage, String pageLength,String Search, String orderColunm, String orderType, HttpSession session); 
	public long second_third_line_tpt_reportCount(String Search);
}
