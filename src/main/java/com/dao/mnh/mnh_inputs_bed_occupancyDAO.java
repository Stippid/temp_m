package com.dao.mnh;

import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_Bed_Ex_Servicemen;
import com.models.mnh.Tb_Med_Bed_Serving;



public interface mnh_inputs_bed_occupancyDAO {

	public List<Map<String, Object>> search_bed_occupancy_Input(String sus1, String type1, String qtr1, String yr1);
	public String delete_bed_occupancy_Input(int deleteid, String type);
	public Long checkExitstingbedocc(String sus_no1,String quater,int id,int yr);
	public Long checkExitstingbedocexserv(String sus_no1,String quater,int id,int yr);
	public String updatebedoccupancyserv(Tb_Med_Bed_Serving obj,String username);
	public String updatebedoccupancyxserv(Tb_Med_Bed_Ex_Servicemen obj1,String username);
	public Tb_Med_Bed_Ex_Servicemen getexservDetail(int id);
	public Tb_Med_Bed_Serving getservDetail(int id);
	 public Boolean getquaterAlreadyExist(String qtr,String sus,String c_id,String type,int yr);
	
}
