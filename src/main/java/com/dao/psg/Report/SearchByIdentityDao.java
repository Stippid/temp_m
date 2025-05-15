package com.dao.psg.Report;

import java.util.List;
import java.util.Map;

public interface SearchByIdentityDao {

	List<Map<String, Object>> search_identity(String cat, String id_cardno, String pr_no);

}
