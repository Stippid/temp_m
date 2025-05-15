package com.dao.fpmis;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;

import com.models.fpmis.FP_MIS_Master_Model;
import com.models.fpmis.fp_tb_admin_control_model;
import com.models.fpmis.fp_tb_norbat_dtl_model;
import com.models.fpmis.fp_tb_proj_detl_model;   

public interface FP_MIS_MasterDAO {     
	public List<String> getMNHEncList(List<String> l,HttpSession s1);
	public String updatefpheadcode(FP_MIS_Master_Model obj);
	public String RegdDataTransfer(String tr_head, String tr_level,String major_head,String minor_head,String sub_head, String head_desc,String status,String head_code,String username);
	public ArrayList<ArrayList<String>> getSearchHeadMaster(String head_code1,String head_desc1,String major_head1,String minor_head1,String sub_head11);
	public ArrayList<ArrayList<String>> FindDomainList(String enc,HttpSession s1,String nParaValue);
	public boolean createProjection(fp_tb_proj_detl_model m);
	public String UnitDataTransfer(String sus_no, String form_code_control,String unit_name,String status_sus_no,String remarks,String hq_type,String username,String psus_no);
	public String updateUnitProfilee(fp_tb_norbat_dtl_model obj);
	public boolean saveAdminControl(fp_tb_admin_control_model m);
	public boolean isWindowOpened(String est_type);
	
	public ArrayList<ArrayList<String>> getSearchCDAFunds(String fp_finYr1,String roleSusNo);
	public List<String> getSearchCDABook(String id, String roleSusNo);
	public String FwdDataTransfer(String sus_no, String fwd_amt, String fwd_ref, String fwd_date, String fwdid2,String username, String tr_head_to12, String finyr2,String unitcd2,String cdaunit2);
	public String BkdDataTransfer(String sus_no, Double bkd_amt, String bkd_ref, String bkd_remarks, String bkd_date,int exp_id, int bkd_id, String username);
	public boolean checkProjectionExists(String sus_no,String est_type,String tr_head);	
	
	public String DomDataTransfer(String domainid, String codevalue,String label,String lable_s,String disp_order);
	public ArrayList<ArrayList<String>> getCheckDomain(String codevalue);
	
	public ArrayList<ArrayList<String>> getSearchFinYr(String year1);
	public ArrayList<ArrayList<String>> getSearchTable(String year2);
	public ArrayList<ArrayList<String>> getCheckTable(String year3);
	public String getCreateTableF1111(String year3);
	public ArrayList<ArrayList<String>> search_upd_datay(String est_type2,String fin_year2,String sus1);
	public String updatefpupload(String updid, String cur_allot2);
	public ArrayList<ArrayList<String>> getCheckUpdData(String conc_flag,String app_flag,String submit_flag);
	public boolean nSaveAlertMsg(String nMsg,String nPara,String nParaValue);	
	public boolean getCheckDataUpdation(String conc_flag, String app_flag, String submit_flag);
	public @ResponseBody ArrayList<ArrayList<String>> getCDAFwdBookingDetails(String finYr, String sus_no, int expID);
	public String createBudgetHolder(String sus_no,String psus_no, String form_code_control, String unit_name, String status_sus_no,String remarks, String hq_type,String username);
	public String deletecdaupload(String crs_dt);
	public @ResponseBody ArrayList<ArrayList<String>> getCDAUploadedSheet(String expID);
	public @ResponseBody ArrayList<ArrayList<String>> getexpBookingDetails(String finYr, String sus_no, int expID);
	public String delExpData(String sus_no,	String del_remarks, String exp_id, String bkd_id, String username);
	public ArrayList<ArrayList<String>> getSearchCDAFunds1(String fp_finYr1, String roleSusNo);
	public boolean getCreateTableF(String year);
	public @ResponseBody ArrayList<ArrayList<String>> CDAOffList(String enc, HttpSession s1, String nParaValue);
}
