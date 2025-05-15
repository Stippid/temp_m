package com.dao.psg.Jco_Transaction;

import java.util.ArrayList;

import com.models.psg.Jco_Transaction.TB_PSG_CERTIFY_BC_PC_JCO;

public interface Certify_BCPC_DAO {
	
	public ArrayList<ArrayList<String>> Search_CertifyBCPC(String personnel_no,
			String status, String unit_sus_no, String unit_name,String rank,String cr_by,String cr_date,
			String roleSusNo,
			String roleType);
	
	public TB_PSG_CERTIFY_BC_PC_JCO getCertifyBCPC_Byid(int id);
	public ArrayList<ArrayList<String>> pdf_Certify_BCPC(String army_no);
}
