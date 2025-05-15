
package com.dao.Dashboard;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;

public interface JCO_ORDashboardDAO {

//	public List<String> getpsgirarNameBySUS(String FindWhat, String nSUSNo);
//
//	public List<String> getpsgEncList(List<String> l, HttpSession s1);
//
//	public ArrayList<ArrayList<String>> search_officer_en_table(String cont_comd, String cont_corps, String cont_div,
//			String cont_bde, String unit_name, String sus_no, String user_role, String sub_quali);
//
//	public ArrayList<ArrayList<String>> search_jco_en_table(String cont_comd, String cont_corps, String cont_div,
//			String cont_bde, String unit_name, String sus_no, String user_role, String sub_quali);
//
//	public ArrayList<ArrayList<String>> search_civilian_en_table(String cont_comd, String cont_corps, String cont_div,
//			String cont_bde, String unit_name, String sus_no, String user_role, String sub_quali);
//
//	public ArrayList<ArrayList<String>> search_officer_en_tablecen(String cont_comd, String cont_corps, String cont_div,
//			String cont_bde, String unit_name, String sus_no, String user_role, String sub_quali);
//
//	//// bisag v2 200922(converted in data table )
//	public List<Map<String, Object>> getsearch_officer_en_table(int startPage, int pageLength, String Search,
//			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
//			String cont_div, String cont_bde, String unit_name, String unit_sus_no/* ,String user_role_id */,
//			String from_date, String to_date, String user_role, String sub_quali);
//
//	public long getsearch_officer_en_tablecount(String Search, String orderColunm, String orderType,
//			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
//			String unit_name, String unit_sus_no, /* String user_role_id, */String from_date, String to_date,
//			String user_role, String sub_quali);
//
//	///////////////////////////////////////////////// OFFICER CENSUS/////////////
//	public List<Map<String, Object>> getsearch_officer_en_table1(int startPage, int pageLength, String Search,
//			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
//			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
//			/* String user_role_id, */String from_date, String to_date, String user_role, String sub_quali);
//
//	public long getsearch_officer_en_tablecount1(String Search, String orderColunm, String orderType,
//			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
//			String unit_name, String unit_sus_no, /* String user_role_id, */String from_date, String to_date,
//			String user_role, String sub_quali);

	////////////////////////////////////////////////////////////////// END//////////////////
	//// bisag v2 200922(converted in data table )
	public List<Map<String, Object>> getsearch_jco_en_table(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			/* String user_role_id, */String from_date, String to_date, String user_role, String sub_quali);

	public long getsearch_jco_en_tablecount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, /* String user_role_id, */String from_date, String to_date,
			String user_role, String sub_quali);

	//// bisag v2 200922(converted in data table )
	public List<Map<String, Object>> getsearch_civilian_en_table(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			/* String user_role_id, */String from_date, String to_date, String user_role, String sub_quali);

	public long getsearch_civilian_en_tablecount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, /* String user_role_id, */String from_date, String to_date,
			String user_role, String sub_quali);

	public ArrayList<ArrayList<String>> Excel_Record_Service_Report1();

	public ArrayList<ArrayList<String>> Excel_Record_Service_Report2();

	public ArrayList<ArrayList<String>> Excel_Record_Service_Report3();

	public ArrayList<ArrayList<String>> Excel_Record_Service_Report4();

	public ArrayList<ArrayList<String>> Excel_Record_Service_Report5();

	public ArrayList<ArrayList<String>> Excel_Record_Service_Report6();

	public List<Map<String, Object>> getrankwise();

	public List<Map<String, Object>> getheldvsrk_jco_ordashboard();

	public int Getcount_no_Jco_OrData();

	public int Getcount_no_unitJcoData();
   // public List<Map<String, Object>>Getrk_medcatlist_jco_orst();

//rk

	public String Getrk_medcatlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1);

	public String Getrk_heldlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1);

	public String Getrk_regimentlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1);

	public String Getrk_parent_armtlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1);

	public String Getrk_state_armtlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1);

	public String Getrk_blood_grouplist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1);

	public String Getrk_commandlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1);

	public String Getrk_loclist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1, String rank1,
			String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1, String regs1,String parent_arm1,String unit_name1);

	public String Getrk_mothertonguelist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1);

	public String Getrk_userarmlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1);

	public String Getrk_religionlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1);

	public String Getrk_genderlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1);

	public String Getrk_marital_statuslist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1);

	public String Getrk_borderlist(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1);

	public String Getrk_doslist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1, String rank1,
			String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1, String regs1,String parent_arm1,String unit_name1);

	public ArrayList<ArrayList<String>> Getcount_no_unitData_para(String cont_comd1, String cont_corps1,
			String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String div1,
			String corps1, String bdes1, String regs1,String unit_name1);
	
	public ArrayList<ArrayList<String>> Getcount_no_offData_para(String cont_comd1, String cont_corps1,
			String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String div1,
			String corps1, String bdes1, String regs1,String unit_name1);

	public DataSet<TB_CENSUS_JCO_OR_PARENT> DatatablesCriteriasFormationWisejco(DatatablesCriterias criterias) throws SQLException;

	public DataSet<TB_CENSUS_JCO_OR_PARENT> DatatablesCriteriasUnitWiseoff(DatatablesCriterias criterias);

	public int FindJCOcount(String search, String cont_comd4, String cont_corps4, String cont_div4, String cont_bde4,
			String rank4, String arm4, String parm4, String cmd4, String div4, String corps4, String bdes4,
			String regs4, String parent_arm4, String unit_name4);

	public ArrayList<ArrayList<String>> FindJCOtable(int parseInt, int parseInt2, String search, String cont_comd4,
			String cont_corps4, String cont_div4, String cont_bde4, String rank4, String arm4, String parm4,
			String cmd4, String div4, String corps4, String bdes4, String regs4, String parent_arm4, String unit_name4);

	public ArrayList<ArrayList<String>> Findunittable_jco(int parseInt, int parseInt2, String search, String cont_comd4,
			String cont_corps4, String cont_div4, String cont_bde4, String rank4, String arm4, String parm4,
			String cmd4, String div4, String corps4, String bdes4, String regs4, String parent_arm4, String unit_name4);

	public int Findunittable_joc_count(String search, String cont_comd4, String cont_corps4, String cont_div4,
			String cont_bde4, String rank4, String arm4, String parm4, String cmd4, String div4, String corps4,
			String bdes4, String regs4, String parent_arm4, String unit_name4);


}

