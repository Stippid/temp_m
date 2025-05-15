package com.dao.itasset.masters;
import java.util.ArrayList;
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


public interface Asset_mstrDAO {

public List<Map<String, Object>> getReportListAsset_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType, String assets_name,String category,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListAsset_mstrTotalCount(String Search, String assets_name,String category);
public String Deleteasset_mstr(String deleteid,HttpSession session);

public ArrayList<ArrayList<String>> Report_DataTableMakeList(String assets_name,String category);
}
