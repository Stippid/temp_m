package com.dao.mnh;
import java.util.List;
import java.util.Map;

import com.models.mnh.Tb_Med_Imb;





public interface InvalidmentDAO {
	
	public Long checkExitstingInvalidment(String sus_no,String persnl_no,int id);
	 public List<Map<String, Object>> search_invalidment_input(String sus1, String psn_no, String dt_origin1,
			 String dt_imb1,String adhar1,String contact1);
	 
	 public Tb_Med_Imb updateinvalid_Byid(int id);
	 public String updateinvlidment(Tb_Med_Imb request,String username);

}
