package com.dao.mnh;

import java.util.Map;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.mnh.Tb_Med_Disease_Cause;


public interface mstr_Icd_CodeDAO {
	
	public Long checkExitstingIcdCode(String icd_code,int id);
	public String updateicd_code(Tb_Med_Disease_Cause obj);
	public DataSet<Map<String, Object>> DatatablesCriteriasicdcode(DatatablesCriterias criterias , String icd_code,String icd_description); 
	
}
