package com.dao.mnh;


import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_Opd_Sp_Department;
import com.models.mnh.Tb_Med_Opd_Sp_Procedure_master;
import com.models.mnh.Tb_Med_Opd_Sp_Subprocedure;



public interface mstr_OpdSplDepartmentDAO {
	public String updatedept(Tb_Med_Opd_Sp_Department obj,String username);
	public List<Map<String, Object>> getOPDSpDepartListJdbc(String dept_name, String status);
	public Long checkExitstingSpProcedureMaster(Integer old_dept,String old_proc,int id);
	public String update_opd_sp_proc_name(Tb_Med_Opd_Sp_Procedure_master obj);
	public List<Map<String, Object>> getspprocedureListJdbc(Integer dept_id1,String proc_name1,String stat);
	public Long checkExitstingSpsubProcedureMaster(Integer old_dept,String old_sub,int id);
	public String update_opd_sp_subproc_name(Tb_Med_Opd_Sp_Subprocedure obj);
	public List<Map<String, Object>> getspsubprocedureListJdbc(Integer dept_id1,Integer proc_id1,String subproc_name1,String stat);
}
