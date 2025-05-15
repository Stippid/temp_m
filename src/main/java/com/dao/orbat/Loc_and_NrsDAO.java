package com.dao.orbat;

import java.util.ArrayList;
import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Miso_Orbat_Shdul_Detl;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.Tbl_CodesForm;

public interface Loc_and_NrsDAO {
	
	 public ArrayList<ArrayList<String>> getLoc_And_NrsList(String code_value,String code,String status_record,String roleType);
}
