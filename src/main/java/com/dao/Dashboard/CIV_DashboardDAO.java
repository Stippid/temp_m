package com.dao.Dashboard;

import java.sql.SQLException;

public interface CIV_DashboardDAO {

	public String Getrk_gazelist(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) throws SQLException ;
	
	public String Getrk_gen_regular(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) throws SQLException ;

	public String Getrk_cmdlist2(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1, String rank1,
			String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1, String regs1,
			String parent_arm1, String unit_name1) throws SQLException;
	
	
	
}
