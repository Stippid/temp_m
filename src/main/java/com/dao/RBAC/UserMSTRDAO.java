package com.dao.RBAC;

import java.util.List;
import java.util.Map;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.UserLogin;

public interface UserMSTRDAO {
	public List<UserLogin> getFMN_WISE_USER_ID_LIST(String formCode);
	
	public DataSet<Map<String, Object>> DatatablesCriteriasUserReportFormationWise(DatatablesCriterias criterias, String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1);
}
