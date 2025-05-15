package com.dao.login;

import java.util.List;
import java.util.Map;


public interface User_RegistraionDAO {
	public List<Map<String, Object>> DataTable_Cat_List(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			String ic_no,String status);
	public long DataTable_Cat_TotalCount(String Search,String ic_no,String status);
}
