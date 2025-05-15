package com.dao.mnh;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface SHO_FHO_strengthDao {
	public List<Map<String, Object>> search_sho_strength_input1(String sus1) throws SQLException ;
}
