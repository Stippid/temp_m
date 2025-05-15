package com.dao.Dashboard;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.mnh.Med_principle_dis;
import com.models.mnh.Medical_Cmd_Corp_Disease_Details_View;

public interface MNHDashboardFinalDAO {
	
	public ArrayList<List<String>> getCountAllImportantDis(int yr);
	
	/*public List<String> getihdList(String yr);
	public List<String> gethyperList(String yr);
	public List<String> getdmList(String yr);
	public List<String> getobesList(String yr);
	public List<String> getinjurisList(String yr);
	public List<String> getadmList(String yr);
	public List<String> getADSList(String yr);*/
 
	public String getCommand_wise_count_List(int year);
	public ArrayList<List<String>> getCommand_wise_count_List1(int yr);
	public ArrayList<List<String>> getCorpsWise_Count_List(String Command);
	public ArrayList<List<String>> getDivsWise_Count_List(String Command);
	public ArrayList<List<String>> getBdesWise_Count_List(String Command);
	
	//Refresh graph command
	public ArrayList<List<String>> getCorpsWise_Count_List1(String command,String relationship,String yr);
	public ArrayList<List<String>> getDivsWise_Count_List1(String command,String relationship,String yr);
	public ArrayList<List<String>> getBdesWise_Count_List1(String command,String relationship,String yr);
	
	public String getChart1List();
	
	public String getMorbility_with_Array(int year);
	public ArrayList<List<String>> getMorbility(int year);
	
	public String getChart1ExList();
	public String getTop10PCList();
	//public String getLMCList();
	
	public String getBED_STATE_ARMY_List();
	public ArrayList<List<String>> getLMCChart1ListWithParameter(String from_date,String to_date);
	
	public  ArrayList<List<String>> dataappstatusList();
	public  ArrayList<List<String>> datauploadstatusList();
	
	public  ArrayList<List<String>> UnitInfoTblList();
	
	
	public  ArrayList<List<String>> DiseasePrincipalTblList();
	public ArrayList<List<String>>  getChartRelationship(String relationship, String yr);
	public ArrayList<List<String>>  getChartESMRelationship(String relationship, String yr);
	public List<Med_principle_dis> getmnhPrincipalList();
	public  ArrayList<List<String>> hospital_datastatus(int year);
	public ArrayList<List<String>> getlowmadicalchartWithParameter(String from_date,String to_date);
	//public ArrayList<List<String>> getChartCOMMANDRelationship(String yr);
	public ArrayList<List<String>> getChartTopPrincipal(String relationship,String yr);
	public List<String> getMNHCorps(String a1);
	public List<Map<String, Object>> getAllReportListJdbc(String qry);
	
	public DataSet<Medical_Cmd_Corp_Disease_Details_View> DatatablesCriteriasCmdCorps(DatatablesCriterias criterias,String whr);
	public ArrayList<List<String>> getBedCorpsWise_Count_List(String Command);
	
	
	
	public ArrayList<List<String>> daily_unusual_occurence();
	public ArrayList<List<String>> daily_vip_admission();
	public ArrayList<List<String>> daily_amc_adm_admission();
	//public List<Map<String, Object>> daily_notifiable_disease();
	public String daily_notifiable_disease() ;
	public List<Map<String, Object>>daily_bed_defaulterlist();
	//public ArrayList<ArrayList<String>>daily_bed_defaulterlist();
	public String daily_bed_occupied();
	public String notifiable_diseaselist(String yr);
	public ArrayList<List<String>> getdiseaseRelationship(String relationship, String yr) ;
	public String suicides_list() ;
	public ArrayList<List<String>> daily_disease_surve();
	public ArrayList<List<String>> daily_bed_statelist();
	
	
	public ArrayList<List<String>> daily_bed_statelist_army();
	public ArrayList<List<String>> daily_vip_admission_army();
	public ArrayList<List<String>> daily_infection_disease_army();
	public String daily_vip_admission_army_bar();
	public String daily_bed_occupied_army();
	
	public String daily_vip_admission_army_dgms();
	public String daily_adm_amc_army_dgms();
	public String daily_unusual_occur_dgms();
	public String daily_disease_surv_dtls_dgms();
	
	public String Getsuicide_caseslist(String relationship,String yr);
	
}
