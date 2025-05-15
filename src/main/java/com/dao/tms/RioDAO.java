package com.dao.tms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.models.TB_TMS_MVCR_PARTA_MAIN;
import com.models.TB_TMS_RIO_VEHICLE_DTLS;
import com.models.TB_TMS_RO_VEHICLE_DTLS;



public interface RioDAO {
	
	public List<TB_TMS_RO_VEHICLE_DTLS> getReportList1();
	public TB_TMS_RIO_VEHICLE_DTLS viewRio( int id);
	public List<TB_TMS_MVCR_PARTA_MAIN> getLastUpdateDateUnit(String sus_no);
	public ArrayList<ArrayList<String> >  getRIOReport(String sus_no,String fdate ,String tdate ,String status,String roleType,String depot_sus_no,HttpSession sessionA);
	public TB_TMS_RIO_VEHICLE_DTLS UpdateRIO(TB_TMS_RIO_VEHICLE_DTLS rio);
	public String UpdateValidUpto(int id,String ValidUptoVal);
	
	public List<String> getFreeStockBANoList(String d_sus_no,BigInteger mct,String type_of_vehicle);
	public int getFreeStockBANo_and_ro_rio_pending_qty(String d_sus_no,BigInteger mct,String type_of_vehicle);
	/*   NITIN V4 16/11/2022  */
	public ArrayList<ArrayList<String>> getRIOReport_excel(String sus_no,String fdate ,String tdate ,String status,String roleType,String depot_sus_no,HttpSession session);
}


