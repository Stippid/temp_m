package com.dao.tms;

import java.util.ArrayList;
import java.util.List;

import com.models.TB_TMS_CONVERT_REQ;
import com.models.Tms_Banum_Req_Child;

public interface tmsConversionRequestGstoSplVehDAO {
	
	
	
	public Boolean ifExistBaNo(String old_ba_no);
	
	public String UpdateConvertms(TB_TMS_CONVERT_REQ ItemMasterValue,String fname1,String fname2,String fname3,String fname4);
	 public String setApprovedConvReqOfVeh(int appid,String newBa_no,String ba_no,String username,String date);

	 public String setRejectConvReqOfVeh(int appid);
	 public String setDeleteConvReqOfVeh(int appid);
	 
	 public TB_TMS_CONVERT_REQ getTB_TMS_CONVERT_REQid(int id);
	 public List<Tms_Banum_Req_Child> getImageFromBaNoChildReq(String ba_no);
	 public String getImagePath(String ba_no,String fildname);
	 public  ArrayList<List<String>> getReqToCovGsToSpcl(String status,String sus_no,String dte_of_reqst,String received_from,String ba_no,String roleType);
	  
}
