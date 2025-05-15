package com.dao.mnh;

import java.util.Map;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.mnh.Tb_Med_System_Code;


public interface mstr_System_Code_DAO {

	public Long checkExitstingsystemcode(String old_d1,String old_d2,int id);
	public String updateSystemCode(Tb_Med_System_Code obj);
	public DataSet<Map<String, Object>> DatatablesCriteriassyscode(DatatablesCriterias criterias ,String sys_code,String sys_code_value,String sys_code_desc);
	public String setdeletesyrvcodeURL(int deleteid);
}
