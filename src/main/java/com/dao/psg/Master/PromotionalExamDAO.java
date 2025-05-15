package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_MSTR_PROMOTIONAL_EXAM;


public interface PromotionalExamDAO {
	
	public ArrayList<ArrayList<String>> search_PromoExam(String promoExam,String status1);
	public TB_MSTR_PROMOTIONAL_EXAM getmtByid(int id);
	public ArrayList<ArrayList<String>> PromoExamReport();

}
