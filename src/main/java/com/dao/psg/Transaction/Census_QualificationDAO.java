package com.dao.psg.Transaction;

import java.util.List;
import java.util.Map;

public interface Census_QualificationDAO {

	public List<Map<String, String>> getQualificationData(int cen_id,int status) ;
}
