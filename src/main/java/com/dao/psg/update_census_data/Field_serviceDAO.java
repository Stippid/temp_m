package com.dao.psg.update_census_data;



import java.util.ArrayList;

import java.util.Date;

import java.util.List;



import javax.servlet.http.HttpSession;



import com.models.psg.Transaction.TB_FIELD_SERVICE_CH;

import com.models.psg.Transaction.TB_FIELD_SERVICE_P;



public interface Field_serviceDAO {

	

public ArrayList<ArrayList<String>> GetSearch_field_service_data(String roleSusNo,String personnel_no,String statusA,String fd_service,String unit_location,String operation,String unit_name,String unit_sus_no,int service_category, String cr_by,String cr_date,  HttpSession session);

public List<TB_FIELD_SERVICE_P> getfield_servicerByid(int id);

public List<TB_FIELD_SERVICE_CH> getfield_servicerChByid(int id, String status);

public ArrayList<String> getFieldCombination(String fd_service);

public int getFieldServicesCount(String personnel_no, String fd_service, String from_date, String to_date, int ch_id);

}

