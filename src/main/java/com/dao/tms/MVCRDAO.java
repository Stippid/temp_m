package com.dao.tms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.TB_TMS_MVCR_PARTA_DTL;
import com.models.TB_TMS_MVCR_PARTA_MAIN;

public interface MVCRDAO {
	
	public DataSet<TB_TMS_MVCR_PARTA_DTL> mvcrWithDatatablesCriterias(DatatablesCriterias criterias , String qry);
	public String setApprovedmvcr(String sus_no,String username);
	public String setRejectmvcr(String sus_no);
	public  ArrayList<ArrayList<String>> search_unit_holding_detailsList(String sus_no);
	public ArrayList<ArrayList<String>> generate_baNo_presentCost(String sus_no,String mct);
	public  ArrayList<List<String>> getSearchAttributeReportMvcr(String qry,String status,String roleType);
	public  List<String> getBA_noFromIssueType(String sus_no,String mct,String issue_type);
	
	
/*Part A List*/
	public List<Map<String, Object>> getFormationDetailsFromSusNo(String sus_no);
	public  ArrayList<List<String>> getMVCR_PartA_List(String sus_no);
	
/*Part B List*/
	public  ArrayList<List<String>> getMVCR_PartB_DETAILS_UE_UH_List(String sus_no);
	public  ArrayList<ArrayList<String>> getAttributeFromMCTwiseMvcr(String sus_no);
	public  ArrayList<ArrayList<String>> getAttributeprfwiseMvcr(String sus_no);
/*Part C List*/
	public List<String> getMvcrpartCList(String sus_no,String issue_type);
/*Part E-Asset List*/
	public ArrayList<ArrayList<String>> getAttributeFromMVCR_E_Asset(String sus_no);
	
/*Common Methods MVCR*/	
	public @ResponseBody List<TB_TMS_MVCR_PARTA_MAIN> getApproveDate(String sus_no);
	public @ResponseBody List<String> getwepeno(String sus_no);
	public  ArrayList<List<String>> getMVCRReportListPending(String sus_no,String roleType);
	public  ArrayList<List<String>> UpdategetMVCRReportListPending(String qry,String sus_no,String roleType,String roleAccess);
	public ArrayList<List<String>> getMVCRReportListForApproval(String sus_no, String roleType);

public ArrayList<List<String>> getMVCRList(String sus_no,String roleAccess);
public ArrayList<List<String>> getFullMVCRList(String sus_no,String roleAccess);
	public boolean update_mvcr_history_tb(String sus_no);
}
