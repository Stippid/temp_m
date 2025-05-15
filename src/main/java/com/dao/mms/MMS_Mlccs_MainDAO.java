package com.dao.mms;

import java.util.ArrayList;
import java.util.List;

public interface MMS_Mlccs_MainDAO {
	public ArrayList<ArrayList<String>> MlccsView(String nParaValue, String nPara, String domainid,String m1_class_category);
	public ArrayList<ArrayList<String>> NewReqEqptAppList(String q);
	public List<String> updatereject_mms_new_eqpt_qry(String remarks,String letter_no,Integer id);
}