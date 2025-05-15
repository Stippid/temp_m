package com.dao.mnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_Eir;
import com.models.mnh.Tb_Med_History_Stay;


public interface SHO_FHO_EPID_MosquitoDao {

	public List<Map<String, Object>> search_mosqfecoair_ShoInput(String sus1,String unit1, String dis2,String service1,String frm_dt1, String to_dt1) ;
	public Tb_Med_Eir getmosquitoDetail(int id);
	public Tb_Med_History_Stay getmosquitoDetail2(int id);
	public String update_mosquito(Tb_Med_Eir obj,String username);
	public String update_feco(Tb_Med_Eir obj,String username);
	public String update_mosquitoAir(Tb_Med_History_Stay obj2,String username);
	public List<Map<String, Object>> get_mosq_print(Integer id3);
	 public String update_mosquito_travel(Tb_Med_History_Stay obj,String username,String id);
	 
		
}
