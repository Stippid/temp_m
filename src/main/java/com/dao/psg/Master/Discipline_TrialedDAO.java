package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_DISC_TRIALED;

public interface Discipline_TrialedDAO {

	public ArrayList<ArrayList<String>> search_Disc_Trialed(String disc_trialed,String status);
	public TB_PSG_MSTR_DISC_TRIALED getDisc_TrByid(int id);
	 public ArrayList<ArrayList<String>> search_Disc_TrialedReport();
}
