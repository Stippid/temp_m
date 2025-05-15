package com.dao.tms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.TB_TMS_MCT_MAIN_MASTER;
import com.models.Tms_Banum_Req_Child;
import com.models.Tms_Banum_Req_Prnt;



public interface Search_Ba_AllocDAO {
	public  ArrayList<List<String>> getSearchBA_Alloc(String status,String sus_no,String type_of_req,String date,String veh_cat,String ba_no_type,String role,String roleAccess,String vehicle_clas_code);
	public  ArrayList<ArrayList<String>> getSearchBA_AllocPrint(String status,String sus_no,String type_of_req,String date,String veh_cat,String ba_no_type,String roleAccess,String vehicle_clas_code);
	public Tms_Banum_Req_Child getTms_Banum_Req_Childid(int id);
	public Tms_Banum_Req_Prnt getTms_Banum_Req_Prntid(int parent_req_id);
	public String getImagePath(int id,String fildname);
	public String updateReqChild(Tms_Banum_Req_Child updateid);
	public String setDeleteprfMst(int appid);
	public List<Tms_Banum_Req_Child> getTms_Banum_Req_engin_chasisid(int parent_req_id);
	public String getTms_Banum_approve_upadte(int id,int prnt_id,Date date,String ba_no,int id_child,String username);
	public void insertDIR_DRR_DTL_Main_B_veh(TB_TMS_BANUM_DIRCTRY im,String username,String ba_no);
	public void insertCencusDIR_DRR_DTL_main_A_veh(TB_TMS_BANUM_DIRCTRY im,String username,String ba_no);
	public void insertEMAR_DRR_DIR_DTL_main_C_veh(TB_TMS_BANUM_DIRCTRY im,String username,String ba_no);
	public ArrayList<List<String>> getAttributeDataSearchallotmentofbanum1(String  mct,String f_date,String t_date);
	
	 public List<TB_TMS_MCT_MAIN_MASTER> getTms_Banum_Req_engin_chasisid(String mct_main_id);
	 
	 public Object editReqChild(Tms_Banum_Req_Child tb_child);

    public String rejectReqChild(Tms_Banum_Req_Child updateid);

}
