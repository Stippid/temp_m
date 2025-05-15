package com.dao.InspectionReports;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.model.InspectionReports.TB_MISO_INSP_CRITICAL_DEFI_MANPOWER;
import com.model.InspectionReports.TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL;
import com.model.InspectionReports.TB_MISO_INSP_MAIN_PHASE_II_TBL;
import com.model.InspectionReports.TB_MISO_INSP_STATEOFWEAPONS_EQU;

public interface InspectionReportPhaseIIDao {

	public List<TB_MISO_INSP_MAIN_PHASE_II_TBL> getinsp_main_table_data_2(String sus_no, String currenYear);
	public List<TB_MISO_INSP_STATEOFWEAPONS_EQU> getStateOfWeaponEqu(String sus_no) ;
	public List<TB_MISO_INSP_CRITICAL_DEFI_MANPOWER> getCriticalDefiManpower(String sus_no);
	public List<TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL> getDisciplineCourtMaritalDisposal(String sus_no) ;

	public Map<String, String> getphase2data(HttpSession session,String roleSusNo) ;
	public Map<String, String> getdataForDigitalDigitalSign(HttpSession session, String roleSusNo);
	public List<Map<String, String>> weaponDeficiencyList(HttpSession session, String roleSusNo);
	public List<Map<String, String>> getDisciplineCourtMaritalDisposallist(HttpSession session, String roleSusNo);
	public List<Map<String, String>> getciricaldefimanpowerlist(HttpSession session, String roleSusNo);

	public List<Map<String, Object>> getOpData(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getTrainingData(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getWeapData(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getPersData(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getAdminData(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getAspectData(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getInteData(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getMajorData(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getStrengthData(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getChallengesData(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getImproveData(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getAttentionData(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getMitigationData(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getPointsData(HttpSession session, HttpServletRequest request);
}
