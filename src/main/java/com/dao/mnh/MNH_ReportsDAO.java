package com.dao.mnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MNH_ReportsDAO {
	
	public List<Map<String, Object>> search_opd_bed_strength_report(String cmd1,String qtr1,String yr1,String para1);
	
	public List<Map<String, Object>> search_imb_reports(String command,String category1,String from_date,String to_date,
			String disease_principal1,String disease_type1,String disease_subtype1,String block_description1);
	public List<Map<String, Object>> search_mortality_reports(String command,String category1,String from_date,String to_date,
			String disease_principal1,String disease_type1,String disease_subtype1,String block_description1) ;
	public List<Map<String, Object>> getsearch_hiv_aids_rep(String command12,String category1,String service1,String relation1,
			String disease_principal1,String disease_type1, String disease_subtype1,String block_description1, String from_dt1,String to_dt1);
	public List<Map<String, Object>> getdailyuserreport(String username1,String from_dt1,String to_dt1,String unit_name1,String sus_no1);
	public List<Map<String, Object>> search_opd_spl_proc_report(String cmd1,String qtr1,String yr1);
	public String getQuarter(String month);
	 public Boolean Chk_qtr_strength(String quarter,String year, String command);
	 
	 public List<Map<String, Object>> getsearch_mmr_report(String cmd1,String mth_yr1,String disease_principal1,String disease_mmr1,String disease_aso1,String block_description1,String hdicd_code);

	 public ArrayList<String> getdis_principal_mmr_dao(String value,String col_name);
}
