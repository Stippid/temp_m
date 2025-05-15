
package com.dao.cue;
import java.util.List;
import java.util.Map;

import com.models.cue_wepe;
import com.models.cue_wet_pet;

public interface Cue_wetpet_validuptoDAO {	
	
	public  List<Map<String, Object>> getAttributeFromCategorylist1(String wet_pet,String wet_pet_no,String sponsor_dire,String arm,String doc_type,String status,String roleType,String from_date,String to_date,String roleAccess);
		
		public List<cue_wet_pet> getWET_PET_FET_listid(int id);

}




