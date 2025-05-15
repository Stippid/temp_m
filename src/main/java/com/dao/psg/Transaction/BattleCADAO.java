package com.dao.psg.Transaction;

import java.util.ArrayList;

import com.models.psg.Transaction.TB_PSG_DECLARED_BA_CA;

public interface BattleCADAO {

	public ArrayList<ArrayList<String>> Search_BattleCA(String personnel_no,String status, String unit_sus_no, String unit_name,String rank,String cr_by,String cr_date,String roleSusNo,
			String roleType);
	public TB_PSG_DECLARED_BA_CA getSearch_BattleCAByid(int id);
	public ArrayList<ArrayList<String>> pdf_bettal(String personnel_no);
}
