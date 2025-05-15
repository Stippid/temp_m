package com.dao.Reports;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.TB_TMS_MCT_MASTER;

public interface CUE_ReportDAO {

	// PERSONNEL

	public ArrayList<ArrayList<String>> ue_persnl_appntrnkwiserprt(String from,String to,String sus);
	public ArrayList<ArrayList<String>> ue_pers_data_officersreport(String from,String to,String rank_cat,String sus);
	public ArrayList<ArrayList<String>> design_training_capreport(String from,String to);
	public ArrayList<ArrayList<String>> auth_str_alluntreport(String from,String to);
	public ArrayList<ArrayList<String>> allontmnt_of_parntarmreport(String from,String to);
	public ArrayList<ArrayList<String>> allontmnt_of_vancreport(String from,String to);
	public ArrayList<ArrayList<String>> TOTAL_AUTH_RANK_WISEreport(String from,String to,String sus_no);
	public ArrayList<ArrayList<String>> AUTH_ESTABLISHMENT_REGUALR_ARMY_JCO_OR_RANKS_AND_PARENT_ARMreport(String from,String to);
	public ArrayList<ArrayList<String>> AUTH_ESTABLISHMENT_OFFICERS_OF_CT_TYPE_REG_ERE_UNSPreport(String from,String to);
	public ArrayList<ArrayList<String>> AUTH_ESTABLISHMENT_REG_ARMY_OFF_BY_RANKS_AND_PARENT_ARMreport(String from,String to);
	public ArrayList<ArrayList<String>> TOTAL_AUTH_RANK_WISE_REPORT1(String from,String to,String sus_no);
	public ArrayList<ArrayList<String>> TOTAL_AUTH_RANK_WISE_OARENT_ARM_WISEreport(String from,String to,String sus_no);
	public ArrayList<ArrayList<String>> AUTH_USER_ARM_WISEreport(String from,String to);
	public ArrayList<ArrayList<String>> AUTH_EST_REG_ARMY_PERS_CIVIL_FORMATIONreport(String from,String to);
	public ArrayList<ArrayList<String>> AUTH_ESTB_JCO_OR(String from,String to);
	public ArrayList<ArrayList<String>> AUTH_OF_OFF_UNIT_WISEreport(String from,String to);
	public ArrayList<ArrayList<String>> MAJ_MIN_UNIT_CMD_WISEreport(String from,String to,String sus_no);
	public ArrayList<ArrayList<String>> MAJ_MIN_UNITSreport(String from,String to);
	public ArrayList<ArrayList<String>> TOTAL_AUTH_OF_CIVILIANS_IN_THE_ARMY_BY_USER_ARMSreport(String from,String to);
	public ArrayList<ArrayList<String>> ALLOTMENT_OF_VACANCIES_BY_PARENT_ARMreport(String from,String to);
	
	//TRANSPORT

	public ArrayList<ArrayList<String>> all_india_auth_B_veh_rprt(String from,String to);
	public ArrayList<ArrayList<String>> all_india_auth_A_veh_rprt(String from,String to);
	public ArrayList<ArrayList<String>> all_india_ff_nff_B_vehreport(String from,String to);
	public ArrayList<ArrayList<String>> all_india_ff_nff_A_vehreport(String from,String to);
	public ArrayList<ArrayList<String>> ALL_INDIA_UNIT_ENT_OF_B_VEH_RESERVErprt(String from,String to);
	public ArrayList<ArrayList<String>> ALL_INDIA_UNIT_ENTI_OF_B_VEH_WITHreport(String from,String to);
	public ArrayList<ArrayList<String>> ALL_INDIA_UNIT_ENT_OF_B_VEH_INC_DEC(String from,String to);
	public ArrayList<ArrayList<String>> DATA_VERIFICATIONreport(String from,String to);
	public ArrayList<ArrayList<String>> ALL_INDIA_AUTH_A_VEHreport(String from,String to);
	public ArrayList<ArrayList<String>> ALL_INDIA_AUTH_A_VEHCOMD(String from,String to);
	
	/* WEAPON */

	public ArrayList<ArrayList<String>> item_card_report(String from,String to);
	public ArrayList<ArrayList<String>> unit_card_report(String from,String to);
	public ArrayList<ArrayList<String>> comd_wise_report(String from,String to,String comd_name);
	public ArrayList<ArrayList<String>> comd_wise_ue_report(String from,String to);
	public ArrayList<ArrayList<String>> item_card_comd_report(String from,String to,String item_type);
	public ArrayList<ArrayList<String>> unit_card_comd_report(String from,String to,String unit_name);
	public ArrayList<ArrayList<String>> all_india_ue_report(String from,String to);
	
}
