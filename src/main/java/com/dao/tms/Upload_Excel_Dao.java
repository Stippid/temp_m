package com.dao.tms;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Upload_Excel_Dao {
	
	ArrayList<ArrayList<String>> getexcel_print(String ba_no, String old_chasis_no, String old_eng_no,
			String new_chasis_no, String new_eng_no) throws Exception;
}
