package com.dao.mnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.mnh.TB_Med_Authorisation_All;
import com.models.mnh.Tb_Med_Death;

public interface mstr_bed_authoDAO {

	
	 public Long checkExitstingbauth(String sus_no1,int id1);
	 public List<Map<String, Object>> getSearchbauthoMaster(String sus1, String total_all1);
	 public TB_Med_Authorisation_All getbedauthByid(int id);
	 public String updatebedauth(TB_Med_Authorisation_All rs,String username,int id);
}
