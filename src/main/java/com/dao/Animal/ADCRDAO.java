package com.dao.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Animal.TB_ANIMAL_CENSUS_DTLS;

public interface ADCRDAO {
	
	public ArrayList<List<String>> getADCRReportList(String sus_no, String roleType);
	public ArrayList<List<String>> getarmydogList(String sus_no,String roleAccess);
	public TB_ANIMAL_CENSUS_DTLS editadcr(String ArmyNo);
	public List<String> getAnimalTypesByArmyNo(String armyNo);
	public List<String> getAnimalCensusIDByArmyNo(String army_num);
	public ArrayList<List<String>> getSearchAttributeReportAdcr(String status, String sus_no, String roleType,
			String issue_date);
	public ArrayList<List<String>> getADCRReportListForApproval1(String sus_no, String roleType, String issue_date);
	public String setApprovedadcr(String sus_no, String username, String issue_date);
	public @ResponseBody List<String> getwepeno(String sus_no);
	public List<Map<String, Object>> getFormationDetailsFromSusNo(String sus_no);
	public ArrayList<List<String>> getADCRReportListForApproval(String sus_no, String roleType, String issue_date);
	public List<Map<String, Object>> getbasicdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getbasicdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId);
	public List<Map<String, Object>> getdeploumentdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getdeploymentdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId);
	public List<Map<String, Object>> getHMdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getHMdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId);
	public List<Map<String, Object>> getVCdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getVCdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId);
	public List<Map<String, Object>> getTRGdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getTRGdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId);
	public long getVDdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId);
	public List<Map<String, Object>> getVDdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getDWdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) ;
	public List<Map<String, Object>> getDWdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getawdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) ;
	public List<Map<String, Object>> getawdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public List<Map<String, Object>> getutdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String spl, String asondate, HttpSession sessionUserId);
	public long getutdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String spl, String asondate,
			HttpSession sessionUserId);
	
	public List<Map<String, Object>> getbasicdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getbasicdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId);
	public List<Map<String, Object>> getdeploumentdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getdeploymentdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId);
	public List<Map<String, Object>> getHMdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getHMdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId);
	public List<Map<String, Object>> getVCdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getVCdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId);
	public List<Map<String, Object>> getTRGdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getTRGdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId);
	public long getVDdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId);
	public List<Map<String, Object>> getVDdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getDWdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) ;
	public List<Map<String, Object>> getDWdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public long getawdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) ;
	public List<Map<String, Object>> getawdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId);
	public List<Map<String, Object>> getutdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String spl, String asondate, HttpSession sessionUserId);
	public long getutdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String spl, String asondate,
			HttpSession sessionUserId);

}
