package com.dao.tms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.TB_TMS_MCT_MASTER;
import com.models.TB_TMS_MCT_NODAL_DIR_MASTER;
import com.models.TB_TMS_RIO_VEHICLE_DTLS;
import com.models.TB_TMS_RO_MAIN;
import com.models.TB_TMS_RO_VEHICLE_DTLS;

public interface Capture_rel_orderDAO {
	public List<TB_TMS_MCT_MASTER> getMctNoDetailList(String type_of_vehicle,String mct);
	public List<TB_TMS_MCT_MASTER> getStdNomenclatureListFromVeh_cat(String type_of_vehicle);
	public List<String> getStdNomenclature(BigInteger mct);
	public List<TB_TMS_RO_MAIN> getRONoDetailList();
	public List<Miso_Orbat_Unt_Dtl> getSUSNoDetailList();
	public List<String> getUnitNameFromSus(String sus_no);
	public List<String> getComdSusNoFromSus(String sus_no);
	public List<String> getCorpSusNoFromSus(String sus_no);
	public List<String> getNRSFromSus(String sus_no);
	public List<String> getsdReliefFromSus(String sus_no);
	public List<TB_TMS_MCT_NODAL_DIR_MASTER> getDepotSUSFromDepotName(String unit_name);
	public List<TB_TMS_MCT_NODAL_DIR_MASTER> getDepotNameFromDepotSusNo(String sus_no);
	public Object setApprovedItem(int appid,String ro_no,String user_id,String type_of_veh);
	public Object setRejectItem(int rejectid);
	public Object setDeleteItem(int deleteid);
	public TB_TMS_RO_VEHICLE_DTLS getRIOVehDtls(int id);
	public TB_TMS_RIO_VEHICLE_DTLS getRIOVehAllDtls(int id);
	public List<String> getUHFromMCT(BigInteger mct, String sus_no);
	public List<String> getPrfUHFromMCT(BigInteger mct, String sus_no);
	public List<String> getPrfUEFromMCT(BigInteger mct, String sus_no);
	public ArrayList<ArrayList<String>> SearchgetAttributeFromROMainAndVehDtl(String status,String issue_date,String to_date,String type_of_issue,String type_of_ro,String sus_no,String depot_sus_no,HttpSession session);
	
	public String getUEFromMCT_SUSNO(String mct, String sus_no);
	public List<Map<String, Object>> getRODetails(int id);
	public List<Map<String, Object>> getCancelRO(int id, String ro_no, String type_veh, String username, Date cur_date, String remarks) ;
	/*NITIN V4 16/11/2022  */
	public ArrayList<ArrayList<String>> SearchgetAttributeFromROMainAndVehDtl_excel(String status, String issue_date,
			String to_date, String type_of_issue, String type_of_ro, String sus_no,String depot_sus_no, HttpSession session);
	//Added by Mitesh17-10
	public List<Map<String, Object>> updateRomain(String ro_no, String extended_date) ;
}
