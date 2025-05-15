package com.dao.psg.ad_hoc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AdHocDAO {
	
	
	//public List<Map<String, Object>> Search_Pers_det(String hd_column,String filter,String sort);
	public List<Map<String, Object>> Search_Pers_det(String create_query);
	public List<String> getTableNameList();
	public List<String> get_table1_field_List(String t1);
	public List<String> get_table2_field_List(String t2);
	public List<String> get_select_field_List(String t2);
	//public List<String> get_operator_List();
	public List<Map<String, Object>> get_operator_List();
	public List<Map<String, Object>> get_table_name_List();
	public List<String> get_selected_field_List(String t2);
	public ArrayList<ArrayList<String>> getAdhocreport(String create_query);
	public List<Map<String, Object>> get_group_by_List();
	public List<Map<String, Object>> get_catgory_List();
	public ArrayList<ArrayList<String>> get_categorywise_tabel_List(String t11);
}
