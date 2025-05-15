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

public interface JCO_Regular_ArmyPersonnel_DAO {
	
	
	public List<Map<String, Object>> getRegular_ArmyGorkha_ReportDataListDetails_jco(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			  HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long getRegular_ArmyGorkha_TotalCountDtl_jco(String Search);


	
	public List<Map<String, Object>> getRegular_ArmyInfantary_ReportDataListDetails_pers_jco(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			  HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException; 
	public long getRegular_ArmyInfantary_TotalCountDtl_pers_jco(String Search);

	public ArrayList<ArrayList<String>> pdf_getRegular_ArmyGorkha_ReportDataList_jco();
	public ArrayList<ArrayList<String>> pdf_getRegular_ArmyInfantary_ReportDataList_pers_jco();

}
