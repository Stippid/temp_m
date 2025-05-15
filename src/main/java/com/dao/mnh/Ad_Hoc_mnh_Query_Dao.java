package com.dao.mnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Ad_Hoc_mnh_Query_Dao {

	//  public List<String> getTableNameList();
		public List<String> get_table1_field_List(String t1);
		public List<String> get_table2_field_List(String t2);
    	public List<Map<String, Object>> Search_mnh_Pers_det(String create_query);
		public List<String> get_selected_field_List(String t2);
	    public List<Map<String, Object>> get_operator_List();
		public List<Map<String, Object>> get_table_name_List_mnh();
        public ArrayList<ArrayList<String>> getAdhoc_mnh_qryreport(String create_query);
	    public List<Map<String, Object>> get_group_by_List();
//		public List<Map<String, Object>> get_catgory_List();
		public ArrayList<ArrayList<String>> get_categorywise_tabel_List(String t11);

}
