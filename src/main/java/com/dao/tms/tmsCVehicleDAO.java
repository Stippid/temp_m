package com.dao.tms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.TB_TMS_EMAR_REPORT;

public interface tmsCVehicleDAO {
	public List<Map<String, Object>> getReportSearchConvertREQ(String sus_no,String status);
	public TB_TMS_EMAR_REPORT getTB_TMS_EMAR_REPORTid(int id);
	public ArrayList<ArrayList<String>> UpdateReportSearchConvertREQ(String sus_no,String roleType,String roleAccess);
	public ArrayList<List<String>> getFMVCRList(String sus_no,String roleAccess);
	public int checkDataExists(String em_no, String month, String roleSusNo, String table_name);
	public ArrayList<List<String>> getFullFMVCRList(String sus_no, String roleAccess);
}
