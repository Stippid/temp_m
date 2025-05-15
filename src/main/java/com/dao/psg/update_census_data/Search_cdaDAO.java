package com.dao.psg.update_census_data;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_CDA_ACC_NO;

public interface Search_cdaDAO {

	public ArrayList<ArrayList<String>> Search_CDA(String personnel_no,
			String status, String unit_sus_no, String unit_name, String rank, String cr_by,String cr_date,
			String roleSusNo,
			String roleType);
	public ArrayList<ArrayList<String>> getcdaByid(String id);
	public TB_CDA_ACC_NO getSearch_CDAByid(int id);
	public ArrayList<ArrayList<String>> Popup_Change_of_CDA(BigInteger comm_id);
	public List<Map<String, Object>> getHisChangeOfCDA(BigInteger comm_id, int status, HttpSession sessionA);
	public String approveHisCDA(BigInteger parseInt, List<Map<String, Object>> changeOfCDA, String username,
			Date modified_date);
}
