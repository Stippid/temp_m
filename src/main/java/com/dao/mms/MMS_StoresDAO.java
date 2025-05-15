package com.dao.mms;

import java.util.ArrayList;
import java.util.List;

public interface MMS_StoresDAO {
	
	
	public ArrayList<ArrayList<String>> mms_holdings_list(String type_of_hldg,String formcodeType,String formcode,String p_code,String d_from,String d_to);
	public ArrayList<ArrayList<String>> mms_expire_list(String type_of_hldg,String formcodeType,String formcode,String p_code,String d_from,String d_to);
	public Boolean checkIfExisteqpt_regn_no(String eqpt_regn_no);
	public String RegnNoGeneration(String prf_code,String eqpt_regn_no);
	public String SaveHldgChange(String r,String eq,String fromSUS,String currentSUS,String from_prf,String current_prf,String fromCensusNo,String currentCensusNo,String Holding_type,String eqpt_type,String Tran_type,String auth_letter_no,String auth_letter_date,String op_status,int qt);
	public ArrayList<ArrayList<String>> mms_holdings_summ(String type_of_hldg, String formcodeType, String formcode, String p_code,String d_from, String d_to);
	public ArrayList<ArrayList<String>> mms_holdings_list_print(String type_of_hldg, String formcodeType, String formcode, String p_code,
			String d_from, String d_to);


}
