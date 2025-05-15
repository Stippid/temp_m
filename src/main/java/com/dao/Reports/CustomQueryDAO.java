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
public interface CustomQueryDAO {
	public List<Map<String, Object>> CustomQueryTPTList(int startPage,String pageLength,String Search,String orderColunm,String orderType,String cmd,String mct_main,String prf_code ,String type,String sus_no,String line_dte_sus1);
	public long CustomQueryTPTCount(String Search,String cmd,String mct_main,String prf_code ,String type,String sus_no,String line_dte_sus1);
}
