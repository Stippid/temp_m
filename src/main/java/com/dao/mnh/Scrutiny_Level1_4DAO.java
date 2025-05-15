package com.dao.mnh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Scrutiny_Level1_4DAO {

	public List<Map<String, Object>> search_unit_fmn_datascrutiny(String sus1, String cmd1, String frm_dt1,
			String to_dt1, String stat1, String para1, String p_diag1, String icd_remarks_a1, String icd_remarks_d1,
			String relation1);

	public String approve_unit_fmn_datascrutiny(String a, String para, String user);

	public String reject_unit_fmn_datascrutiny(String a, String r, String user);

	public List<Map<String, Object>> get_patient_details_ds(Integer id2);

	public String update_patient_details_ds(ArrayList<String> data, String user, String status) throws SQLException;

	public String update_patient_discharge_details_ds(ArrayList<String> data);

	public String update_unit_fmn_datascrutiny(String a, String icdcode, String icdcause, String para, String username);
}
