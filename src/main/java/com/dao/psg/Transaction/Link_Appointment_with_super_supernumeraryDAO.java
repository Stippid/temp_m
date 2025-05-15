package com.dao.psg.Transaction;

import java.util.ArrayList;

import com.models.psg.Master.TB_NATIONALITY;
import com.models.psg.Transaction.TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY;

public interface Link_Appointment_with_super_supernumeraryDAO {
	public ArrayList<ArrayList<String>> search_appointment(String appointment,String status);
	public String updateAppointment(TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY obj);
	//public TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY getappointmentByid(int id);
}
