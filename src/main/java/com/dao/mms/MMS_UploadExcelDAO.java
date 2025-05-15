package com.dao.mms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

public interface MMS_UploadExcelDAO {
	
	public ArrayList<ArrayList<String>> uploadvalidatelist(String sus_no,String para,String m1_from,String m1_to);
	
	public String validationConfirmNew(String a);
	
	public int checkDataExits(String cencus_no, String month,String depot_sus_no,String table_name);
	
	public String[] getregno(String regn_no, String ro_no, String census_no, String depot_sus_no,
	        String unit_sus_no, HttpSession session);
	
	public String[] getrgnoqntymorethanone(String regn_no, String ro_no, String census_no, String depot_sus_no,
	        String unit_sus_no, int seq,HttpSession session);

}
