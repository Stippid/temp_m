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

public interface Held_str_jco_JCO_DAO {
	
	
	 public ArrayList<ArrayList<String>> Search_Report_auth(String unit_sus_no,String unit_name,String month_year,HttpSession session);

	 
	 public ArrayList<ArrayList<String>> Search_Report_held_str(String unit_sus_no,String unit_name,String FDate,HttpSession session);
	 
	 
	 public ArrayList<ArrayList<String>> Search_Report_Deserter(String unit_sus_no,String unit_name,String FDate,HttpSession session);
	 
	 
	 public ArrayList<ArrayList<String>> Search_Report_attachment(String unit_sus_no,String unit_name,String FDate,HttpSession session);
	 public ArrayList<ArrayList<String>> Search_Report_Serving(String unit_sus_no,String unit_name,String month_year,HttpSession session); //Serving
	 public ArrayList<ArrayList<String>> GetdaoSearch_Report_jco_holding1111( String month, String year, String comd_sus,
				String corp_sus, String div_sus, String bde_sus, String unit_sus_no,String unit_name,HttpSession session); //Serving
			 
		 public ArrayList<ArrayList<String>> pdf_Download_Search_Report_jco_holding1111( String hd_cmd_sus,
						String corp_sus, String div_sus, String bde_sus, String unit_name,String sus_no,HttpSession session);
		 //bisag v1 2208022  (formation wise get data with datatable)			 
		 
		 public long Getsearch_heldthstrCountCount(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no);
		 
		 
		 public List<Map<String, Object>> Getsearch_heldthstrdata(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
				 String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no) ;
		 
		 	//bisag v2 0509022  (formation wise get data with datatable)			 
		 
		 
		 public long Search_Report_held_strCount_b(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String Fdate) ;
		 
		 
		 public List<Map<String, Object>> Search_Report_held_strdata_b(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
				 String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String FDate) ;
		 
		 
		 public long Search_Report_Desertercount_c(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String FDate);
		 
		 
		 
		 
		 public List<Map<String, Object>> Search_Report_attachment_d(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
				 String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String FDate) ;
		 
		 
		 
		 public long Search_Report_attachmentCount_d(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String FDate);
		 
		 
		 
		 
		 public List<Map<String, Object>> Search_Report_Deserter_c(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
				 String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String FDate) ;
		 
		 
		 
		 ///
		 public long Search_Report_auth_count_a(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String FDate);
		 
		 
		 public List<Map<String, Object>> Search_Report_auth_a(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
				 String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String FDate);
		 
		//bisag v2 0509022  (formation wise get data with datatable) end		
			
}
