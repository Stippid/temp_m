/*package com.dao.mnh_Dashboard;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

import com.models.Miso_Orbat_Unt_Dtl;
import com.models.mnh.Med_principle_dis;
import com.models.mnh.Medical_Cmd_Corp_Disease_Details_View;
import com.models.mnh.Scrutiny_Search_Model;
import com.models.mnh.Tb_Med_Daily_Surv_Disease_Mstr;
import com.models.mnh.Tb_Med_Hosp_Assign;


public interface MnhDashboardDAO {

	  public ArrayList<List<String>> dataserutinitystatusList();
	  public ArrayList<List<String>> morbidityTblList();
	  public ArrayList<List<String>> topprincipalcausestblList();
	  public ArrayList<List<String>> datastatushospitalwiseTblList();
	  public String getMorbidityList();
	  public String getMortalityList();
	  public String getInvalidmentList();
	  public String getDatastatusList();
	  /////des 2//////////
	  public ArrayList<List<String>> datauploadstatusTblList();
	  public ArrayList<List<String>> commadwiseTblList();
	  public List<String> getihdList(String yr);
	  public List<String> gethyperList(String yr);
	  public List<String> getdmList(String yr);
	  public List<String> getobesList(String yr);
	  public List<String> getinjurisList(String yr);
	  public List<String> getadmList(String yr);
	  public String getmonthwiseList();
	  public String getchartsimpleList();
	  public String getcategoryList();
	  public List<Map<String, Object>> getmonthwiseListJdbc(String qry);
	  public DataSet<Med_principle_dis> DatatablesCriteriasprinciple(DatatablesCriterias criterias,String whr);
	  public DataSet<Tb_Med_Hosp_Assign> DatatablesCriteriasdataupload(DatatablesCriterias criterias,String whr);
	  
	  //Sandeep Added
	  public List<Med_principle_dis> getmnhPrincipalList();
	  public List<Miso_Orbat_Unt_Dtl> getmnhCommandList();
	  public String getChart1List();
	  public String getChart2List();
	  public String getChart3List();
	  public String getChart4List();
	  public List<String> getADSList(String yr);
	  public List<Map<String, Object>> getAllReportListJdbc(String qry);
	  public ArrayList<List<String>> BlockDescTblList();
	  //public ArrayList<List<String>> BlockDescTblList1();
	  //public String BlockDescTblList1();
	  public ArrayList<List<String>> UnitInfoTblList();
	  public DataSet<Scrutiny_Search_Model> DatatablesCriteriasUnitInfo(DatatablesCriterias criterias,String whr);
	  public DataSet<Scrutiny_Search_Model> DatatablesCriteriasdataupload1(DatatablesCriterias criterias,String whr);
	  //public List<Map<String, Object>> getAllReportListJdbc1(String qry);
	  //Sandeep Added
	  
	  public List<Tb_Med_Daily_Surv_Disease_Mstr> getmnhDailyDiseaseList();
	  public String getDash41List();
	  public String getDash42List();
	  public String getDash43List();
	  public String getLMCList();
	  
	  //Graph - 5
	  public String getChart51List();
	  public ArrayList<List<String>> getCorpsWise_Count_List(String Command);
	  public ArrayList<List<String>> getDivsWise_Count_List(String Command);
	  public ArrayList<List<String>> getBdesWise_Count_List(String Command);
	  
	  public ArrayList<List<String>> DiseasePrincipalTblList();
	  public DataSet<Medical_Cmd_Corp_Disease_Details_View> DatatablesCriteriasCmdCorps(DatatablesCriterias criterias,String whr);
	  public String getChart1ExList();
	  public String getChart4PCList();
	  
	  public ArrayList<ArrayList<String>> getMMSRData(int userid);
	  
	  //LMC Graphs
	  public String getLMCChart1List();
	  public ArrayList<List<String>> dataappstatusList();
	  public ArrayList<List<String>> datauploadstatusList();
}
*/