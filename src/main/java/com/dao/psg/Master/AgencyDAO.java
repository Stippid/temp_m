package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_COUNTRY;
import com.models.psg.Master.TB_PSG_AGENCY_MASTER;

public interface AgencyDAO {
	
	public ArrayList<ArrayList<String>> search_Agency_name(String agency_name,String status);
	public TB_PSG_AGENCY_MASTER getAgencyByid(int id);

}
