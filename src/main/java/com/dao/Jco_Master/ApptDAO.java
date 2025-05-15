package com.dao.Jco_Master;

import java.util.ArrayList;

import com.model.Jco_Master.TB_PSG_MSTR_APPOINTMENT_JCO;


public interface ApptDAO {
	public ArrayList<ArrayList<String>> search_Appt(String appointment,String category,String status);
	public TB_PSG_MSTR_APPOINTMENT_JCO getApptByid(int id);
	 public ArrayList<ArrayList<String>> Appt_Report();
}
