package com.dao.orbat;
import java.util.List;
import java.util.Map;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

public interface ReportsDAO {
	public DataSet<Map<String, Object>> findActiveOrbatDetailsWithDatatablesCriterias(DatatablesCriterias criterias,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121);
	public DataSet<Map<String, Object>> findFormationwiseReportWithDatatablesCriterias(DatatablesCriterias criterias , String status_sus_no12,String sus_no12,String unit_name12,String cont_bde12,String cont_div12,String cont_corps12,String action12,String approved_rejected_on12,String approved_rejected_on22,String cont_comd12);
	
	public  List<Map<String, Object>> getAllFormationList();
	public List<Map<String, Object>> getsus_noorbat(int id);
	public List<Map<String, Object>> getKLP_MATRIXList(String formation,String station);
	public List<Map<String, Object>> gettyp_ltr(int aid);
	
	public List<Map<String, Object>> getformationorbat(String sus_no);
	public String getlinedte_susno(String sus_no);
	
	//28.10.2024
	
	//cii report
		public DataSet<Map<String, Object>> findActiveOrbatDetailsWithDatatablesCriterias_cii(DatatablesCriterias criterias,String sus_no1211,String unit_name1211,String level1_1211,String level2_1211,String level3_1211,String level4_1211,String level5_1211,String level6_1211,String level7_1211,String level8_1211,String level9_1211,String levell0_1211,String line_dte_sus1211,String location1211);
		
		//orbat publications
		
		public DataSet<Map<String, Object>> findActiveOrbatDetailsWithDatatablesCriterias_Pub(DatatablesCriterias criterias,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121);
		public DataSet<Map<String, Object>> findActiveOrbatDetailsWithDatatablesCriterias_Pub_CTPARTII(DatatablesCriterias criterias,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121);
		public DataSet<Map<String, Object>> findActiveOrbatDetailsWithDatatablesCriterias_Pub_CTPARTI_A(DatatablesCriterias criterias,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121);
		public DataSet<Map<String, Object>> findActiveOrbatDetailsWithDatatablesCriterias_Pub_CTPARTI_B(DatatablesCriterias criterias,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121);

		//AUTHORITY VIEW
		
		public DataSet<Map<String, Object>> findAuthorityReportWithDatatablesCriterias(DatatablesCriterias criterias , String status_sus_no12,String sus_no12,String unit_name12,String cont_bde12,String cont_div12,String cont_corps12,String action12,String approved_rejected_on12,String approved_rejected_on22,String cont_comd12,String letter_no);


}
