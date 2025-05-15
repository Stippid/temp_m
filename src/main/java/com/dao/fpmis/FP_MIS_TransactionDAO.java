package com.dao.fpmis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;

public interface FP_MIS_TransactionDAO {
	public @ResponseBody ArrayList<ArrayList<String>> getunitlist(String enc,HttpSession s1,String nParaValue);
	public @ResponseBody ArrayList<ArrayList<String>> getHeadlist(String enc,HttpSession s1,String nParaValue);
	public @ResponseBody ArrayList<ArrayList<String>> FindUnitList(String enc,HttpSession s1,String nParaValue);
	public @ResponseBody ArrayList<ArrayList<String>> FindFinYear(String enc,HttpSession s1,String nParaValue);
	public @ResponseBody String getUnitType(String enc,HttpSession s1,String nParaValue);
	public @ResponseBody String fp_fund_tfr_confirm(String m1_trhead,String m1_frunt,String m1_blamt,String m1_tramt,String m1_tount,String m1_tohead, String m1_trtype,String m1_tryear,String m1_trrem,String m1_pid,String usrid,String n1_trAltType,String n1_expType,String expId);
	public @ResponseBody ArrayList<ArrayList<String>> getHeadPreflist(String enc,HttpSession s1,String nParaValue);
	public @ResponseBody ArrayList<ArrayList<String>> getUnitPrefList(String enc,HttpSession s1,String nParaValue);
	public @ResponseBody ArrayList<ArrayList<String>> getHeadAssignPreflist(String enc,HttpSession s1,String nParaValue,String userid2);
	public @ResponseBody ArrayList<ArrayList<String>> getUnitAssignPreflist(String enc,HttpSession s1,String nParaValue,String nParaValue1, String userid2);
	public List<String> getAllFormationList(String a1, String a2);
	public List<String> getAllFormationListAuto(String a1);
	public List<String> getSusIncrList(String a1,String a2);
	public @ResponseBody ArrayList<ArrayList<String>> getunitBuglist(String enc,HttpSession s1,String nParaValue);
	public @ResponseBody ArrayList<ArrayList<String>> getHeadBuglist(String enc,HttpSession s1,String nParaValue);
	public ArrayList<ArrayList<String>> getSearchPrj(String fp_finYr,String est_type);
	public String nGetAdmFinYear(String nPara);
	public @ResponseBody ArrayList<ArrayList<String>> FindProjList(String enc,HttpSession s1,String rolesus);	
	public @ResponseBody ArrayList<ArrayList<String>> getProjectionList(String enc,HttpSession s1,String nParaValue);
	public @ResponseBody ArrayList<ArrayList<String>> FindFundList(String enc,HttpSession s1,String rolesus,String finYr);
	public @ResponseBody ArrayList<ArrayList<String>> getFundYrList(String enc,HttpSession s1,String nParaValue);
	public @ResponseBody boolean checkHeadBuglist(String sus_no,String head);
	public @ResponseBody boolean checkBudgetHead(String sus_no, String head);
	public @ResponseBody boolean checkBudgetHolder(String sus_no, String chl_sus_no);
	public @ResponseBody boolean checkBudgetHolderWithHead(String sus_no, String chl_sus_no,String head);
	public ArrayList<ArrayList<String>> getAllBudgetHolderWithHead_old(String sus_no);
	public ArrayList<String> getAllBudgetHolderWithHead(String sus_no);
	public @ResponseBody String fp_fund_tfr_confirm_id(String m1_trhead, String m1_tramt,String m1_trtype, String m1_tryear);
	public ArrayList<ArrayList<String>> getAllBranchList();
	public String nGetUnitNodal(String nPara);
	public @ResponseBody ArrayList<ArrayList<String>> getUnitPrefDetl(String enc, HttpSession s1, String nParaValue);
	public @ResponseBody ArrayList<ArrayList<String>> getUnitHirarlist(String enc, HttpSession s1, String nParaValue);
	public @ResponseBody ArrayList<ArrayList<String>> getHirarwithFplist(String enc, HttpSession s1, String nParaValue);
	public void refeshMViews(String nPara);
	public List<Map<String, Object>> getExpFundsData(String chead,String csus, HttpSession session);
}
