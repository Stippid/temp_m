package com.dao.mms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface MMS_Unit_hldgDAO {
	public ArrayList<ArrayList<String>> UnitMcrList(String nParaValue);
	public List<Map<String, Object>> getTrasctionUnitWiseList(String sus_no);
	public String veryfyTransaction_MCR(String sus_no);
	public String updateMisoReply(String miso_reply,int miso_reply_id,String username);
	public List<Map<String, Object>> getUnitLastUpdatedOn(String sus_no);
	public ArrayList<ArrayList<String>> mms_ue_uh_summ(String nSUSNo, String nPara); 
	public ArrayList<ArrayList<String>> unitCorrectCertilist(String susno,String mth,String yr);
	public ArrayList<ArrayList<String>> getUnitUploadedDocDetails(String sus_no,String month,String yr,String roleAccess);
	public List<String> getEqptData(String r1,String r2);
	public Boolean ifExistEqptRegNo(String e,HttpSession session);
	public String RegnNoGeneration1_UNIT(String prf_code,String eqpt_regn_no);
	public String RegdTransLog(String regnSeqno, String regnno, String nFSUSNo,String nFPRF,String nFCensus,String nFMNo,String nFHldType,String nFEqptType,String nOldval,String nTSUSNo,String nTMNo,String nTHldType,String nTEqptType,String nNewV,String nAuthNo, String nAuthDate, String nAuthType,String nOStatus, String nPara,String fname);
	public List<String> mms_list_regn_no(String sus_no,String census_no,String type_of_hldg,String regn_seq_no);
	public String mms_update_regnno(String regn_no,String regn_seq_no, String nFileName);
	public String RegdTransUpdate(String regnSeqno, String regnno, String nFSUSNo,String nTSUSNo,String nTHldType,String nTEqptType,String nOStatus, String nTfrStatus, String nUpBy, String nPara, String nFileName);
	public ArrayList<ArrayList<String>> UnitccReglist(int id);
	

	public ArrayList<ArrayList<String>> UnitRegnMcrList(String nParaValue);
	
	public List<String> checkIfExistUnitHoldingeqpt_regn_no(String eqpt_regn_no);
	public ArrayList<ArrayList<String>> trackiutstatus_mms(String sus_no,String role_type,String roleAccess,Integer id);
	public String getsus_no_list (String sus_no);
}