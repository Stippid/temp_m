package com.dao.psg.miso_olap;

public interface JDBCMISO_OLAPDAO {
	public void iaff3008_MainDetails_olap();
	public void iaff3008_Serving_olap();
	public void iaff3008_SuperNumarary_olap();
	public void iaff3008_Re_EmoloyeeMent_olap();
	public void iaff3008_Deserter_olap();
	
	
	//bisag 300523 v2 (3009)
	public void iaff3009_MainDetails_olap();
	public void iaff3009_authoffrs_olap();
	public void iaff3009_authciv_olap();
	public void iaff3009_postedoffrs_olap();
	public void iaff3009_postedciv_olap();
	public void iaff3009_rank_trad_olap();
	public void iaff3009_rel_denomination_olap();
	public void iaff3009_summary_olap();
	public void iaff3009_authoffrs_olap_new();
	public void iaff3009_postedoffrs_olap_new();
	public void iaff3009_rank_trad_olap_new();
	public void iaff3009_rel_denomination_olap_new();
	public void iaff3009_summary_olap_new();
	
	// for mns 3008 pranay 13.06.24
		public void iaff3008_MainDetails_mns_olap();
		public void iaff3008_Serving_olap_mns();
		public void iaff3008_SuperNumarary_olap_mns();
		public void iaff3008_Deserter_olap_mns();
	
	
	
	
	
		

}
