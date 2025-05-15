package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public interface Change_Appointment_History_DAO {
	public ArrayList<ArrayList<String>> change_Appointment(BigInteger comm_id,int census_id);

	public ArrayList<ArrayList<String>> change_Appointment_xml(BigInteger appointment_comm_id);
	
	public ArrayList<ArrayList<String>> change_Appointment_3008(BigInteger comm_id);

}
