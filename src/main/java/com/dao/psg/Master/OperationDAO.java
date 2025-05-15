package com.dao.psg.Master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.models.psg.Master.TB_OPERATION;


public interface OperationDAO {

	public List<Map<String, Object>> search_Operation(String operation_name,String status); 
	public TB_OPERATION getOperationByid(int id) ;
	public Long checkExitstingOperation(String operation_name1,int id1,String status1); 
	public String Update_Operation(TB_OPERATION obj,String username);
	public ArrayList<ArrayList<String>> search_OperaionReport();
}
