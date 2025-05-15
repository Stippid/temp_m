package com.dao.Dashboard;

import java.util.ArrayList;

public interface UNIT_Dashboard_Reports_DAO {

	public ArrayList<ArrayList<String>> getUnitReportPersOffrs(String roleSusNo,String formationtype);

	public ArrayList<ArrayList<String>> getUnitReportPersOffrsAuth(String roleSusNo,String formationtype);

	public ArrayList<ArrayList<String>> getUnitReportPersOR(String roleSusNo,String formationtype);

	public ArrayList<ArrayList<String>> getUnitReportPersJCo(String roleSusNo,String formationtype);

	public ArrayList<ArrayList<String>> getUnitReportPersCivAuth(String roleSusNo,String formationtype);

	public ArrayList<ArrayList<String>> getUnitReportPersCivs(String roleSusNo,String formationtype);

	public ArrayList<ArrayList<String>> getUnitReportPersJcoAuth(String roleSusNo,String formationtype);

	public ArrayList<ArrayList<String>> getUnitReportPersOrAuth(String roleSusNo,String formationtype);

	public ArrayList<ArrayList<String>> getUnitReportVehAuth(String type_veh, String roleSusNo,String formationtype);

	public ArrayList<ArrayList<String>> getUnitReportVeh(String type_veh, String roleSusNo,String formationtype);

	public ArrayList<ArrayList<String>> getUnitReportWpnEqptAuth(String type_mtrls, String roleSusNo,String formationtype);

	public ArrayList<ArrayList<String>> getUnitReportWpnEqpt(String type_mtrls, String roleSusNo,String formationtype);

	public ArrayList<ArrayList<String>> getUnitReportIT_Assets(String roleSusNo,String formationtype);
	public ArrayList<ArrayList<String>> getUnitReportWpnEqptSummary(String type_mtrls, String roleSusNo,String formationtype);
	
//	 pranay 15.07 bde dashboard 
	public ArrayList<ArrayList<String>> get3008CurrentMonthReport(String roleSusNo);
	public ArrayList<ArrayList<String>> get3009CurrentMonthReport(String roleSusNo);
	public ArrayList<ArrayList<String>> getMcrCurrentMonthReport(String roleSusNo);
	public ArrayList<ArrayList<String>> getUnitWiseItAssetReport(String roleSusNo);
	public ArrayList<ArrayList<String>> getAvehCurrentMonthReport(String roleSusNo);
	public ArrayList<ArrayList<String>> getBvehCurrentMonthReport(String roleSusNo);
	public ArrayList<ArrayList<String>> getCvehCurrentMonthReport(String roleSusNo);
	public ArrayList<ArrayList<String>> getAllCasualtyCurrentMonth(String roleSusNo);
}
