package com.dao.tms;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.TB_TMS_MCT_MASTER;

public interface AddMctNoDAO {
	
	public Boolean ifExistMctNo(BigInteger mct);
	public DataSet<TB_TMS_MCT_MASTER> findMctNoReportWithDatatablesCriterias(DatatablesCriterias criterias , String mct1,String type_of_vehicle1,String prf_group1,String mct_main_id1);
	public TB_TMS_MCT_MASTER getTB_TMS_MCT_MASTERByid(int id);
	public String UpdateMctNo(TB_TMS_MCT_MASTER mctmas,String mct_main_id, String mct_nomen,String make_id,String desc_make,String model_id,String desc_model,String username, Date date);
	public String setDeleteMctNo(int id);


	

	public List<Map<String, Object>> getdata_oh(int startPage, int pageLength, String Search,String type_of_veh,String line_dte, String sub_cat,String orderColunm,
			String orderType, HttpSession sessionUserId);
	public long getdatacount_oh(String Search,String type_of_veh, String line_dte,String sub_cat,String orderColunm, String orderType, HttpSession sessionUserId);

	
	
	
}
