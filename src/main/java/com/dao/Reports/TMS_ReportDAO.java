package com.dao.Reports;

import java.util.ArrayList;
import java.util.List;

public interface TMS_ReportDAO {

	public List<String> getYearList();
	// TMS

	public ArrayList<ArrayList<String>> ue_mms_hldngofbvehrprt(String from,String to);
	public ArrayList<ArrayList<String>> ue_mms_hldngofbvehrprt1(String from,String to);
	public ArrayList<ArrayList<String>> ue_mms_bvehcmndwiserprt(String from,String to);
	public ArrayList<ArrayList<String>> ALL_INDIA_HOLDING_OF_B_VEHICLES_BY_CLASSIFICATION_WITH_ARMY_UNITSDEPOT_2report(String from,String to);
	public ArrayList<ArrayList<String>> ALL_INDIA_HOLDING_OF_B_VEHS_COMMAND_AND_DEPOTS_Excl_Cl_5_6_REPORT(String from,String to);
	
	// 13 to 20 B VEHICLES
	
	public ArrayList<ArrayList<String>> AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_BY_SECOUND_THIRD_LINE_UNITS_STATE_AS_ON_31_MAR_2019report(String from,String to);
	public ArrayList<ArrayList<String>> AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_STATE_AS_ON_31_MAR_2019report(String from,String to);
	public ArrayList<ArrayList<String>> AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_COMMANDWISE_EXCLUSIVELY_FOR_CAT_A_ESTB_STATE_AS_ON_31_MAR_2019report(String from,String to);
	public ArrayList<ArrayList<String>> AUTHORISATION_AND_HOLDING_OF_EME_SPECIALIST_VEHICLES_WITH_ARMY_UNITS_STATE_AS_ON_31_MAR_19_REPORT(String from,String to);
	public ArrayList<ArrayList<String>> AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_WITH_ENGINEER_UNITS_BY_COMMANDS_STATE_AS_ON_31_MAR_19_REPORT(String from,String to);
	public ArrayList<ArrayList<String>> authorisation_and_holding_commandsrprt(String from,String to);
	public ArrayList<ArrayList<String>> authorisation_and_holding_veharmyrprt(String from,String to);
	public ArrayList<ArrayList<String>> authorisation_and_holding_vehunitrprt(String from,String to);
	
	// B_VEHs
		public ArrayList<ArrayList<String>> PRF_WISE_ALL_INDIA_DEFI_SURPLUS_US_OF_B_VEHICLE_STATUS_REPORT(String from,String to);
		public ArrayList<ArrayList<String>> ALL_INDIA_HOLDING_OF_B_VEH_POSNCURL_REPORT(String from,String to);
		public ArrayList<ArrayList<String>> TABLE_SHOWING_ANTICIPATED_DISCARD_FOR_NEXT_4_5_YEARSCURL_REPORT(String from,String to);
		public ArrayList<ArrayList<String>> RECONCILIATION_STATEMENT_OF_B_VEHICLES_FOR_THE_PERIOD_REPORT(String from,String to);
		public ArrayList<ArrayList<String>> COMMAND_SUMMARY_OF_GENERAL_B_VEHICLES_POSN_REPORT(String from,String to,String comd_name);
		public ArrayList<ArrayList<String>> CORPS_SUMMARY_OF_GENERAL_B_VEHICLES_POSN_REPORT(String from,String to,String corps_name);
		
		// A_VEHs
		public ArrayList<ArrayList<String>> command_wise_usage_meteage_analysis_of_A_vehs_cl_3_POSN_TANKS_APCsrprt(String from,String to);
		public ArrayList<ArrayList<String>> holding_of_tanks_unit_wise_posn_mech_BN_APCsrprt(String from,String to,String arm_name,String prf_name,String force_type, String unit_name);
		public ArrayList<ArrayList<String>> holding_state_of_A_vehs_mct_and_classification_wise_depot_wise_holdingsrprt(String from,String to);
		public ArrayList<ArrayList<String>> holding_of_A_vehs_mct_and_classification_wise_posnrprt(String from,String to,String comd_name, String force_type);
		public ArrayList<ArrayList<String>> HOLDING_OF_A_VEHICALES_COMMAND_AND_DEPOT_WISE_POSNrprt(String from,String to);
		public ArrayList<ArrayList<String>> holding_of_A_veh_southern_command_nonfield_forcesrprt(String from,String to);
}
