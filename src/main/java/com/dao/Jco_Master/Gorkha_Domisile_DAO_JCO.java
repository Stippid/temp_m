package com.dao.Jco_Master;

import java.util.ArrayList;

import com.model.Jco_Master.TB_PSG_MSTR_CLASS_JCO;
import com.model.Jco_Master.TB_PSG_MSTR_GORKHA_DOMISILE_JCO;

public interface Gorkha_Domisile_DAO_JCO {

	public ArrayList<ArrayList<String>> search_Gorkha_Domisile(String domisile,String status);
	public TB_PSG_MSTR_GORKHA_DOMISILE_JCO getGorkhaDomisileByid(int id);
	public ArrayList<ArrayList<String>> Gorkha_Domisile_report();
	
}
