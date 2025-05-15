package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_BENEFITS_MASTER;

public interface BenefitsDAO {
	public ArrayList<ArrayList<String>> search_Benefits_name(int agency_id,String benefits_name,String status);
	 public TB_PSG_BENEFITS_MASTER getBenefitsByid(int id);
}
