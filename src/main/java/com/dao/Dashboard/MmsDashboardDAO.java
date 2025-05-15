package com.dao.Dashboard;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.CUE_TB_MISO_PRF_Mst;
import com.models.MMS_TV_ADH_UNIT_STATUS;

public interface MmsDashboardDAO {

	
	 public Long getmmscensusTotalList();
	 public Long getmmsprfTotalnoList();
	 public Long getmmsunitTotalnoList();
	 public Long getmmsdepotTotalList();
	 public Long getmmssectorTotalList();
	 public Long getmmsloanTotalList();
	 public Long getmmsacsfpTotalList();
	 public Long getmmsengrTotalList();
	 
	 public List<CUE_TB_MISO_PRF_Mst> getmmsprfnamenoList();
	 public  ArrayList<ArrayList<String>> getcensusnostatustbl();
	 public  ArrayList<ArrayList<String>> getclofwpneqpttbl();
	 public  ArrayList<ArrayList<String>> getdefaulterunittbl();
	 public  ArrayList<ArrayList<String>> getunitwithmcrobsn();
	 public  ArrayList<ArrayList<String>> getmmsRoRioStatustbl();
	
    public ArrayList<List<String>> getchartdivPRFWiseList(String prf);
    public DataSet<MMS_TV_ADH_UNIT_STATUS> DatatablesCriteriasadhunit(DatatablesCriterias criterias);
    
    public List<Map<String, Object>> getTotalUnitStatusReport(int startPage,String pageLength,String Search,String orderColunm,String orderType);
    public long getTotalUnitStatusReportCount(String Search);
	
}
