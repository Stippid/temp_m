package com.dao.tms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;

import com.models.TB_TMS_CENSUS_RETURN;
import com.models.TB_TMS_CENSUS_RETURN_MAIN;
import com.models.TB_TMS_UPLOAD_DOC_MCR;

public interface MCRDAO {

	public TB_TMS_CENSUS_RETURN editmcrA(int id);
	public TB_TMS_CENSUS_RETURN UpdateMCR(TB_TMS_CENSUS_RETURN cp);
	public TB_TMS_CENSUS_RETURN Serachmcr(int id);
	public  ArrayList<List<String>> getReportMcr(String status,String sus_no,String unit_name,String roleType);
	public TB_TMS_CENSUS_RETURN mcrSearchedit(int id);
	public String setRejectmcr(String sus_no);
	public String setApprovedmcr(String sus_no,String username, String mct, String roleType) ;
	public List<TB_TMS_UPLOAD_DOC_MCR> getdownload(String sus_no);
	public ArrayList<ArrayList<String>> getdetails_ue_uhMvcrlist(String sus_no);
	public ArrayList<ArrayList<String>> getdetailsE_AssetsMvclist(String sus_no);
	public  ArrayList<List<String>> getmcrReportList(String sus_no,String roleType);
	public  ArrayList<List<String>> getmcrReportListpartA(String sus_no);
    public ArrayList<List<String>> getMCRList(String sus_no,String roleAccess);
    public ArrayList<List<String>> getFMCRList(String sus_no,String roleAccess);
	 public  ArrayList<List<String>> getRepairReport(String sus_no);

	   
	public  ArrayList<List<String>> getMCRReportList(String sus_no,String roleType);
	public ArrayList<List<String>> getFullFMCRList(String sus_no,String roleAccess);
	public int checkDataExists(String ba_no, String month, String roleSusNo, String table_name);

	
	

}
