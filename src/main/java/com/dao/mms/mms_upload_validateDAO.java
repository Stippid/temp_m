package com.dao.mms;

import java.util.List;

public interface mms_upload_validateDAO {

	 //public List<String> uploadvalidatelist(String q);
	 
	 public String validationConfirm(String a);
	 
	 public String RegnNoGeneration1(String prf_code, String eqpt_regn_no);
	 
	 //public String validationConfirmNew(String a);
	 
	 public String GetPRFCodeByCensusNo(String census_no);
	
}
