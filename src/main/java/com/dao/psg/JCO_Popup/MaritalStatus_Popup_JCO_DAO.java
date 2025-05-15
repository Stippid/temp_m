package com.dao.psg.JCO_Popup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MaritalStatus_Popup_JCO_DAO {
	public ArrayList<ArrayList<String>> MaritalStatus_PopUp_JCO(int maritalstatus_jco_id);

	List<Map<String, Object>> getSpouseQualificationJco(int jco_id);
}
