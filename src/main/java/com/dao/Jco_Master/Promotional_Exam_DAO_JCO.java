package com.dao.Jco_Master;

import java.util.ArrayList;

import com.model.Jco_Master.TB_MSTR_PROMOTIONAL_EXAM_JCO;

public interface Promotional_Exam_DAO_JCO {
	
	public ArrayList<ArrayList<String>> search_PromoExamJCO(String promoExam,String status1);
	public TB_MSTR_PROMOTIONAL_EXAM_JCO getmtByidJCO(int id);
	public ArrayList<ArrayList<String>> PromoExamReportJCO();

}
