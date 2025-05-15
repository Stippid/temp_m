package com.dao.Dashboard;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.View_TMS_BVeh_Command_Wise_TrnsportUE_UH;
import com.models.View_TMS_Bveh_Mvcr_Update_Status_Details;
import com.models.View_TMS_Cveh_Mvcr_Update_Status_Details;
import com.models.View_Tms_BVEH_Unit_Wise_Issue_Type_UH;
import com.models.Prf_Wise_TrnsportUE_UH;
import com.models.View_TMS_Aveh_Mvcr_Update_Status_Details;

public interface TmsDashboardDAO {

	  public ArrayList<List<String>> gettypeOfVehicleList();
	  public  List<String> getPendingApprovedStatusList();
	  public  List<List<String>> getPendingApprovedRoRioStatusList();
	  public Long getmvcrunitList();
	  public Long getNoOfPrfList();
	  public Long getMCTdesList();
	  public Long getMAKEList();
	  public Long getMODELList();
	  
	  public Long getAvgKMs();
	  public Long getAvgYears();
	  
	  public Long getUNITList();
	  public Long getDEPOTList();
	  public DataSet<View_TMS_BVeh_Command_Wise_TrnsportUE_UH> DatatablesCriteriasFormationWiseueuh(DatatablesCriterias criterias);
	  public DataSet<Prf_Wise_TrnsportUE_UH> DatatablesCriteriasPrfWiseueuh(DatatablesCriterias criterias);
	  
	  //public DataSet<View_TMS_Bveh_Mvcr_Update_Status_Details> DatatablesCriteriasMvcrstatus(DatatablesCriterias criterias,String whr);
	  
	  public DataSet<Map<String, Object>> DatatablesCriteriasMvcrstatus(DatatablesCriterias criterias,String whr);
	  
	  public DataSet<View_TMS_Aveh_Mvcr_Update_Status_Details> DatatablesCriteriasAMvcrstatus(DatatablesCriterias criterias,String whr);
		public DataSet<View_TMS_Cveh_Mvcr_Update_Status_Details> DatatablesCriteriasCMvcrstatus(DatatablesCriterias criterias,String whr);
	  
	  public DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> DatatablesCriteriasLoanStore(DatatablesCriterias criterias);
	  public DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> DatatablesCriteriasSectorStore(DatatablesCriterias criterias);
	  public DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> DatatablesCriteriasACSFPStore(DatatablesCriterias criterias);
	  public DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> DatatablesCriteriasOverUeStore(DatatablesCriterias criterias);
	  
	  public String getCommandWiseUE_UH_B_VEH_List();
	  public ArrayList<List<String>> getCorpsWiseUE_UH_B_VEH_List(String comnd);
	  public ArrayList<List<String>>  getDivWiseUE_UH_B_VEH_List(String Corps);
	  public ArrayList<List<String>>  getBDEWiseUE_UH_B_VEH_List(String Div);
	  public String getCommandWiseUE_UH_A_VEH_List();
	  public ArrayList<List<String>> getCorpsWiseUE_UH_A_VEH_List(String comnd);
	  public ArrayList<List<String>>  getDivWiseUE_UH_A_VEH_List(String Corps);
	  public ArrayList<List<String>>  getBDEWiseUE_UH_A_VEH_List(String Div);
	  public String getCommandWiseUE_UH_C_VEH_List();
	  public ArrayList<List<String>> getCorpsWiseUE_UH_C_VEH_List(String comnd);
	  public ArrayList<List<String>>  getDivWiseUE_UH_C_VEH_List(String Corps);
	  public ArrayList<List<String>>  getBDEWiseUE_UH_C_VEH_List(String Div);
	  
	  public Long loanStoreTotal();
	  public Long sectoreStoreTotal();
	  public Long acsfpTotal();
	  public Long overUeTotal();
	  
	  public List<Map<String, Object>> exportDashCSV_A(int startPage,String pageLength,String Search,String orderColunm,String orderType,String whr);
	  public List<Map<String, Object>> exportDashCSV_B(int startPage,String pageLength,String Search,String orderColunm,String orderType,String whr);
	  public List<Map<String, Object>> exportDashCSV_C(int startPage,String pageLength,String Search,String orderColunm,String orderType,String whr);
}
