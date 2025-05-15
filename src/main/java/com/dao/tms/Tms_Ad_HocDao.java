package com.dao.tms;

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

public interface Tms_Ad_HocDao {

	public List<Map<String, Object>> get_tms_catgory_List();
	public List<String> getTableNameList();
	//public ArrayList<ArrayList<String>> tms_get_categorywise_tabel_List(String t11);
	public ArrayList<ArrayList<String>> tms_get_categorywise_tabel_List();
	public List<String> tms_get_selected_field_List(String t2);
	public List<Map<String, Object>> tms_Search_Pers_det(String create_query);
	public ArrayList<ArrayList<String>> tms_getAdhocreport(String create_query);
	public List<Map<String, Object>> Arm_DescList();
	public List<Map<String, Object>> Type_of_ForceList();
	public List<String> Ad_Hoc_getaddsearchmctnoprfList(String prf_group,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	public List<String> gettmsEncList(List<String> l, HttpSession s1);
	
}
