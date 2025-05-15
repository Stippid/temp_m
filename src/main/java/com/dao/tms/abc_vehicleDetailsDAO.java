package com.dao.tms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.TB_TMS_IUT;

public interface abc_vehicleDetailsDAO {
	public ArrayList<ArrayList<String>> abc_vehiclestatusDetails_line_dte(String cmd,String mct_main,String prf_code,String type,String sus_no,String line_dte_sus1,int kms, int vintage, String type_of_force);
	
	public ArrayList<ArrayList<String>> abc_vehiclestatusDetails_nodal_dte(String cmd,String mct_main,String type,String sus_no,String line_dte_sus1,int kms, int vintage, String type_of_force);
	
	
	
}