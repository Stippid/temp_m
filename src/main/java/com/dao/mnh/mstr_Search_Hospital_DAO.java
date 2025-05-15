package com.dao.mnh;

import java.util.ArrayList;

public interface mstr_Search_Hospital_DAO {

	public ArrayList<ArrayList<String>>gethospitalassignList(String username, String sus_no);
	public Long checkExitstinghosp(String sus_no,int id);
}
