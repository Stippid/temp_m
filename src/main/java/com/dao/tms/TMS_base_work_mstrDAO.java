package com.dao.tms;

import java.util.ArrayList;
import java.util.List;

import com.models.TB_TMS_BASE_WORKSHOP_MASTER;
import com.models.TB_TMS_REPAIR_AGENCY_MASTER;

public interface TMS_base_work_mstrDAO {

	public ArrayList<ArrayList<String>> base_workshop_search_REPORT(String sus_no,
			String unit_name);
	public TB_TMS_BASE_WORKSHOP_MASTER getbase_workshopByid(Integer id);
	public String GetUpdatebase_workshop(TB_TMS_BASE_WORKSHOP_MASTER obj,String username);
	public Boolean getsus_noExist(String personnel_no,Integer c_id) ;
	public ArrayList<ArrayList<String>> repair_agency_search_REPORT(String sermct_main_nomen,
			String re_agency);
	public TB_TMS_REPAIR_AGENCY_MASTER getrepairByid(Integer id);
	public String GetUpdaterepairshop(TB_TMS_REPAIR_AGENCY_MASTER obj,String username);
}
