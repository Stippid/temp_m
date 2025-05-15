package com.dao.Jco_Master;

import java.util.ArrayList;

import com.model.Jco_Master.TB_PSG_MSTR_RECORD_OFFICE_JCO;

public interface Record_OfficeDAO_JCO {

	public ArrayList<ArrayList<String>> search_record_office(String record_office,String sus_no,String status);
	public TB_PSG_MSTR_RECORD_OFFICE_JCO getrecordoffByid(int id);
	public ArrayList<ArrayList<String>> search_Record_Office_report();
}
