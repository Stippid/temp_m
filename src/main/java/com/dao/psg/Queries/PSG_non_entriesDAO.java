package com.dao.psg.Queries;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;

public interface PSG_non_entriesDAO {
 
	
	 
	public ArrayList<ArrayList<String>> search_officer_en_table(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no);
		public ArrayList<ArrayList<String>> search_jco_en_table(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no);
		public ArrayList<ArrayList<String>> search_civilian_en_table(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no);
		
	////bisag v2 200922(converted in data table )
		public List<Map<String, Object>> getsearch_officer_en_table_non(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
				String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date);
		 public long getsearch_officer_en_tablecount_non(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date);
		 
		////bisag v2 200922(converted in data table )
		 public List<Map<String, Object>> getsearch_jco_en_table_non(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date);
		 public long getsearch_jco_en_tablecount_non(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date);
		 
		 
		////bisag v2 200922(converted in data table )
		 public List<Map<String, Object>> getsearch_civilian_en_table_non(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date);
		 public long getsearch_civilian_en_tablecount_non(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date);

}




