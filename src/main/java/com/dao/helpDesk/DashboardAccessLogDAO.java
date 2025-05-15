package com.dao.helpDesk;

import java.util.Map;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

public interface DashboardAccessLogDAO {
	public DataSet<Map<String, Object>> findDashboardAccessLogListWithDatatablesCriterias(DatatablesCriterias criterias);
}