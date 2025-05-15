package com.dao.Jco_Emoluments;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.Emoluments.TB_PSG_EMOLUMENT_PARENT_OFFCR;
import com.models.Emoluments_Jco.TB_PSG_EMOLUMENT_PARENT_OFFCR_JCO;

public interface EmolumentsJco_DAO {
	public ArrayList<ArrayList<String>> getsearch_Emolument_Jco(String personnel_no1,String cname1,String rank1, String from_date1,String ini_status1
			,String on_status1,String scase1,String cancel_status1);
	
	
	public TB_PSG_EMOLUMENT_PARENT_OFFCR_JCO getEmolumentsByid(int id) ;
	
    List<ArrayList<String>> Get_EmoulData_Jco(BigInteger comm_id);
    public List<Map<String, Object>>   GetPersonnelNoDataJcoEmoulCont(String personnel_no);
    public ArrayList<ArrayList<String>> approve_update_Jco_eno_update(BigInteger comm_id);
    
    
public ArrayList<ArrayList<String>> View_Emoluments_History_Jco(BigInteger comm_id) ;
    
    public ArrayList<ArrayList<String>> App_getm_emu_detailsData_Jco_list(BigInteger comm_id,String c_status,String i_status);
    public ArrayList<ArrayList<String>> App_getm_emu_Jco_detailsData_app_list(BigInteger comm_id);
    public ArrayList<ArrayList<String>> App_getm_emu_close_Jco_detailsData_list(BigInteger comm_id,String c_status,String i_status);
    
    public ArrayList<String> approve_update_eno_Jco_iteration(BigInteger comm_id);
    
    public ArrayList<ArrayList<String>> getm_emu_detailsDataListEdit_Jco(BigInteger comm_id);
    
    
    public ArrayList<ArrayList<String>> getm_emu_Jco_detailsDataListEdit_childs(BigInteger comm_id);
}


