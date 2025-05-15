package com.dao.mnh;

import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_Death;
import com.models.psg.update_census_data.TB_CENSUS_BATT_PHY_CASUALITY;



public interface mnh_input_mortalityDAO {

	
	
	 public Long checkExitstingMortality(String sus_no,String persnl_no,int id);
	 public List<Map<String, Object>> search_mortality_input(String sus1, String unit1, String psn_no, String cat1,String from_dt1,
			 String to_dt1,String adhar1,String contact1);
	// public TB_CENSUS_BATT_PHY_CASUALITY getMorbalityByid(int id);
	 public Tb_Med_Death getMorbalityByid(int id);
	 public String updatemortality(Tb_Med_Death rs,String username);
	// public List<Map<String, Object>> get_phy_battle_mortality_details_ds(Integer id2) ;
	 
}
