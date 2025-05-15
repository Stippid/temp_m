package com.dao.orbat;



import java.util.List;
import java.util.Map;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

import com.models.Miso_Orbat_Unt_Dtl;

public interface DisbandmentDAO {
	
	public DataSet<Map<String, Object>> findraisingdisbadmentWithDatatablesCriterias(DatatablesCriterias criterias,String status_sus_no,String roleType,String scenario,String unit_name,String comm_depart_date,String compltn_arrv_date);
	public String rejectSearchDisbandmentDetails(int rid,String username,String date);
	public String deletedSearchDisbandmentDetails(int rid,String scenarioD,String sus_noD);
//	public String approvedSearchDisbandmentDetails(int rid,String username,String date);
	public List<String> approvedSearchDisbandmentDetails(int rid,String username,String date);

public List<String> getUserIdForNotification(Integer module_id);
	
}
