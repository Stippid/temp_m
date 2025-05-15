package com.dao.psg.Jco_Transaction;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.models.psg.Jco_Transaction.TB_FIELD_SERVICE_CH_JCO;
import com.models.psg.Jco_Transaction.TB_FIELD_SERVICE_P_JCO;
import com.models.psg.Transaction.TB_FIELD_SERVICE_CH;
import com.models.psg.Transaction.TB_FIELD_SERVICE_P;

public interface Field_serviceJcoDAO {
	public ArrayList<ArrayList<String>> GetSearch_field_service_data_Jco(String roleSusNo,String personnel_no,String statusA,String fd_service,String unit_location,String operation,String unit_name,String unit_sus_no, String cr_by,String cr_date,HttpSession session);
	public ArrayList<String> getFieldCombination_Jco(String fd_service);
	public int getFieldServicesCount_Jco(String personnel_no, String fd_service, String from_date, String to_date,int ch_id);
	public List<TB_FIELD_SERVICE_P_JCO> getfield_servicerByid_Jco(int id);

	public List<TB_FIELD_SERVICE_CH_JCO> getfield_servicerChByid_Jco(int id, String status);

}
