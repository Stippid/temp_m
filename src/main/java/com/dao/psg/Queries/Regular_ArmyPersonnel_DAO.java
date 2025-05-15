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

public interface Regular_ArmyPersonnel_DAO {
	// bisag 121222 v2 (perentarm changed report1 one)
	
/*	public List<Map<String, Object>> getRegular_ArmyGorkha_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			  HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long getRegular_ArmyGorkha_TotalCountDtl(String Search);
*/

	
	public List<Map<String, Object>> getRegular_ArmyInfantary_ReportDataListDetails_pers(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			  HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException; 
	public long getRegular_ArmyInfantary_TotalCountDtl_pers(String Search);

	public ArrayList<ArrayList<String>> pdf_getRegular_ArmyGorkha_ReportDataList();
	public ArrayList<ArrayList<String>> pdf_getRegular_ArmyInfantary_ReportDataList_pers();

}
