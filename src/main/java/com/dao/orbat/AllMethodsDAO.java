package com.dao.orbat;

import java.util.ArrayList;
import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Miso_Orbat_Shdul_Detl;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.Tbl_CodesForm;

public interface AllMethodsDAO {
	
	public ArrayList<List<String>> getCorpsList(String fcode);
	public ArrayList<ArrayList<String>> getLOC_NRS_TypeLOC_TrnType(String locCode);
	public int getSusVersion(String sus_no);
	
	List<String> check_sus_hierarchy(String sus_no);

	public ArrayList<List<String>> getCorpsListadm(String fcode);
	public ArrayList<List<String>> getCorpsListop(String fcode);

}
