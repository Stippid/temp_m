package com.dao.mnh;

import java.util.Date;
import java.util.List;

import com.models.mnh.Scrutiny_Search_Model;
import com.models.mnh.Tb_Med_BedOccupancy;

public interface MsAccessDataUploadDAO {
	//public int getCheckIfExits(String and_no,String sus_no);
	public List<Scrutiny_Search_Model> getCheckIfExits(String and_no,String sus_no);
	public List<Tb_Med_BedOccupancy> getCheckIfExitsBedOccup(String sus_no, Date BO_DATE, String CATEGORY,String WARD);
}
