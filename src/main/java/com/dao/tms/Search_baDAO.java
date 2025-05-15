package com.dao.tms;

import java.util.ArrayList;
import java.util.List;

import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.TB_TMS_CONVERT_REQ;
import com.models.TB_TMS_RELIEF_PROG_HISTORY;

public interface Search_baDAO {
	
	public  ArrayList<ArrayList<String>> getBaNoCurrentStatus(String ba_no,String veh_cat);
	public List<TB_TMS_BANUM_DIRCTRY> getFreestockDetails(String ba_no,String veh_cat);
	public  List<TB_TMS_BANUM_DIRCTRY> getInitialIssueUnit(String ba_no,String veh_cat);
	public  List<TB_TMS_CONVERT_REQ> getConvertGsToSPLList(String ba_no,String veh_cat);
	public  List<TB_TMS_RELIEF_PROG_HISTORY> getTransferofVehicle(String ba_no,String veh_cat);
	public  List<TB_TMS_BANUM_DIRCTRY> getBackLoadDetails(String ba_no,String veh_cat);
	public  List<TB_TMS_BANUM_DIRCTRY> getAuctionedDetail(String ba_no,String veh_cat);
	public Boolean ifExistbano(String ba_no);
	

}
