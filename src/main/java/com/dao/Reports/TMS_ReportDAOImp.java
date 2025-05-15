package com.dao.Reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

public class TMS_ReportDAOImp implements TMS_ReportDAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	public List<String> getYearList() {
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
		int s_year = 2001;	
		List<String> alist = new ArrayList<>();
		for(int y=s_year; y<=year; y++)
		{
			alist.add(String.valueOf(y));
		}
		return alist;
	}
	
	
	// TMS

	// 1.

	public ArrayList<ArrayList<String>> ue_mms_hldngofbvehrprt(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("user_arm_name"));
				list.add(rs1.getString("parent_arm_name"));
				list.add(rs1.getString("rank"));
				list.add(rs1.getString("appt_trade"));
				list.add(rs1.getString("total_ue"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 1. END

	// 2.
		 
		 public ArrayList<ArrayList<String>> ue_mms_hldngofbvehrprt1(String from,String to) {
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				try{	  
					conn = dataSource.getConnection();
					PreparedStatement stmt =null;
					String sql1="";
						sql1 = "";

						stmt = conn.prepareStatement(sql1);
						stmt.setString(1, from);
						stmt.setString(2, to);
						ResultSet rs1 = stmt.executeQuery(); 
					    while(rs1.next()){
					    	  ArrayList<String> list = new ArrayList<String>();
					    	  list.add(rs1.getString("sus_no"));
					    	  list.add(rs1.getString("user_arm_name"));
					    	  list.add(rs1.getString("parent_arm_name"));
					    	  list.add(rs1.getString("rank"));
					    	  list.add(rs1.getString("appt_trade"));
					    	  list.add(rs1.getString("total_ue"));
					    	  alist.add(list);
				        }
					    rs1.close();

					stmt.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				} 
				return alist;
			}
		 
	// 2. END

	// 3.
		 
		 public ArrayList<ArrayList<String>> ue_mms_bvehcmndwiserprt(String from,String to) {
				
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				try{	  
					conn = dataSource.getConnection();
					PreparedStatement stmt =null;
					String sql1="";
						sql1 = "";

						
						stmt = conn.prepareStatement(sql1);
						stmt.setString(1, from);
						stmt.setString(2, to);
						ResultSet rs1 = stmt.executeQuery(); 
					    while(rs1.next()){
					    	  ArrayList<String> list = new ArrayList<String>();
					    	  list.add(rs1.getString("sus_no"));
					    	  list.add(rs1.getString("user_arm_name"));
					    	  list.add(rs1.getString("parent_arm_name"));
					    	  list.add(rs1.getString("rank"));
					    	  list.add(rs1.getString("appt_trade"));
					    	  list.add(rs1.getString("total_ue"));
					    	  alist.add(list);
				        }
					    rs1.close();

					stmt.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				} 
				return alist;
			}
		 
	// 3. END

	// 4.
		 
		 public ArrayList<ArrayList<String>> ALL_INDIA_HOLDING_OF_B_VEHICLES_BY_CLASSIFICATION_WITH_ARMY_UNITSDEPOT_2report(String from,String to) {
				
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				try{	  
					conn = dataSource.getConnection();
					PreparedStatement stmt =null;
					String sql1="";
						sql1 = "";
						
						stmt = conn.prepareStatement(sql1);
						stmt.setString(1, from);
						stmt.setString(2, to);
						ResultSet rs1 = stmt.executeQuery(); 
					    while(rs1.next()){
					    	  ArrayList<String> list = new ArrayList<String>();
					    	  list.add(rs1.getString("sus_no"));
					    	  list.add(rs1.getString("unit_name"));
					    	  list.add(rs1.getString("training_capacity"));
					    	 
					    	  alist.add(list);
				        }
					    rs1.close();

					stmt.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				} 
				return alist;
			}
		 
	// 4. END

	// 5.
		 
		 public ArrayList<ArrayList<String>> ALL_INDIA_HOLDING_OF_B_VEHS_COMMAND_AND_DEPOTS_Excl_Cl_5_6_REPORT(String from,String to) {
				
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				try{	  
					conn = dataSource.getConnection();
					PreparedStatement stmt =null;
					String sql1="";
						sql1 = "";
						
						stmt = conn.prepareStatement(sql1);
						stmt.setString(1, from);
						stmt.setString(2, to);
						ResultSet rs1 = stmt.executeQuery(); 
					    while(rs1.next()){
					    	  ArrayList<String> list = new ArrayList<String>();
					    	  list.add(rs1.getString("sus_no"));
					    	  list.add(rs1.getString("unit_name"));
					    	  list.add(rs1.getString("training_capacity"));
					    	 
					    	  alist.add(list);
				        }
					    rs1.close();

					stmt.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				} 
				return alist;
			}
		
	// 5. END

	// 13.
		 public ArrayList<ArrayList<String>> AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_BY_SECOUND_THIRD_LINE_UNITS_STATE_AS_ON_31_MAR_2019report(String from,String to) {
				
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				try{	  
					conn = dataSource.getConnection();
					PreparedStatement stmt =null;
					String sql1="";
						sql1 = "select\r\n" + 
								"	b.prf_code,\r\n" + 
								"	m.group_name,\r\n" + 
								"	sum(case when o.arm_desc='ARMY SERVICE CORPS' then b.ue else 0 end) as second_ue,\r\n" + 
								"	sum(case when o.arm_desc='ARMY MEDICAL CORPS' then b.ue else 0 end) as third_ue,\r\n" + 
								"	sum(case when o.arm_desc='ARMY SERVICE CORPS' and b.ud='UNIT' then 1 else 0 end) as second_uh,\r\n" + 
								"	sum(case when o.arm_desc='ARMY MEDICAL CORPS' and b.ud='UNIT' then 1 else 0 end) as third_uh\r\n" + 
								"from tms_b_veh_all_details b\r\n" + 
								"inner join tms_b_veh_master_all_details m on b.ba_no=m.ba_no and b.mct_main=m.mct_main\r\n" + 
								"inner join orbat_all_details_view o on b.unit_sus=o.sus_no \r\n" + 
								"where cast(b.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
								"group by 1,2\r\n" + 
								"order by 1";
						stmt = conn.prepareStatement(sql1);
						stmt.setString(1, from);
						stmt.setString(2, to);
						ResultSet rs1 = stmt.executeQuery(); 
					    while(rs1.next()){
					    	  ArrayList<String> list = new ArrayList<String>();
					    	  list.add(rs1.getString("group_name"));
					    	  list.add(rs1.getString("second_ue"));
					    	  list.add(rs1.getString("second_uh"));
					    	  list.add(rs1.getString("third_ue"));
					    	  list.add(rs1.getString("third_uh"));
					    	  alist.add(list);
				        }
					    
					    rs1.close();

					stmt.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				} 
				return alist;
			}
	// 13. END

	// 14.

	public ArrayList<ArrayList<String>> AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_STATE_AS_ON_31_MAR_2019report(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	b.prf_code,\r\n" + 
					"	m.group_name,\r\n" + 
					"	sum(case when b.ue!=0 then b.ue else 0 end) as total_ue,\r\n" + 
					"	sum(case when b.ud='UNIT' then 1 else 0 end) as total_uh,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no then b.ue else 0 end) as sc_ue1,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sc_uh1,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no then b.ue else 0 end) as ec_ue2,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as ec_uh2,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no then b.ue else 0 end) as wc_ue3,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as wc_uh3,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no then b.ue else 0 end) as cc_ue4,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as cc_uh4,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no then b.ue else 0 end) as nc_ue5,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as nc_uh5,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no then b.ue else 0 end) as atc_ue6,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as atc_uh6,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no then b.ue else 0 end) as anc_ue7,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as anc_uh7,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no then b.ue else 0 end) as swc_ue8,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as swc_uh8,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no then b.ue else 0 end) as un_ue9,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as un_uh9,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no then b.ue else 0 end) as sfc_uea,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sfc_uha,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no then b.ue else 0 end) as mod_ue0,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as mod_uh0\r\n" + 
					"from tms_b_veh_all_details b\r\n" + 
					"inner join tms_b_veh_master_all_details m on b.ba_no=m.ba_no and b.mct_main=m.mct_main\r\n" + 
					"inner join orbat_cmd c on substr(b.fco,1,1)=c.cmd_code and b.unit_sus=c.sus_no\r\n" + 
					"where cast(b.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2\r\n" + 
					"order by 2";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("sc_ue1"));
				list.add(rs1.getString("sc_uh1"));
				list.add(rs1.getString("ec_ue2"));
				list.add(rs1.getString("ec_uh2"));
				list.add(rs1.getString("wc_ue3"));
				list.add(rs1.getString("wc_uh3"));
				list.add(rs1.getString("cc_ue4"));
				list.add(rs1.getString("cc_uh4"));
				list.add(rs1.getString("nc_ue5"));
				list.add(rs1.getString("nc_uh5"));
				list.add(rs1.getString("atc_ue6"));
				list.add(rs1.getString("atc_uh6"));
				list.add(rs1.getString("anc_ue7"));
				list.add(rs1.getString("anc_uh7"));
				list.add(rs1.getString("swc_ue8"));
				list.add(rs1.getString("swc_uh8"));
				list.add(rs1.getString("un_ue9"));
				list.add(rs1.getString("un_uh9"));
				list.add(rs1.getString("sfc_uea"));
				list.add(rs1.getString("sfc_uha"));
				list.add(rs1.getString("mod_ue0"));
				list.add(rs1.getString("mod_uh0"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 14. END

	// 14A.
		 public ArrayList<ArrayList<String>> AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_COMMANDWISE_EXCLUSIVELY_FOR_CAT_A_ESTB_STATE_AS_ON_31_MAR_2019report(String from,String to) {
				
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();					 
				Connection conn = null;
				try{	  
					conn = dataSource.getConnection();
					PreparedStatement stmt =null;
					String sql1="";
					sql1 = "select \r\n" + 
							"	b.prf_code,\r\n" + 
							"	m.group_name,\r\n" + 
							"	sum(case when b.ue!=0 then b.ue else 0 end) as total_ue,\r\n" + 
							"	sum(case when b.ud='UNIT' then 1 else 0 end) as total_uh,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no then b.ue else 0 end) as sc_ue1,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sc_uh1,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no then b.ue else 0 end) as ec_ue2,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as ec_uh2,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no then b.ue else 0 end) as wc_ue3,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as wc_uh3,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no then b.ue else 0 end) as cc_ue4,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as cc_uh4,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no then b.ue else 0 end) as nc_ue5,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as nc_uh5,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no then b.ue else 0 end) as atc_ue6,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as atc_uh6,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no then b.ue else 0 end) as anc_ue7,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as anc_uh7,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no then b.ue else 0 end) as swc_ue8,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as swc_uh8,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no then b.ue else 0 end) as un_ue9,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as un_uh9,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no then b.ue else 0 end) as sfc_uea,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sfc_uha,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no then b.ue else 0 end) as mod_ue0,\r\n" + 
							"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as mod_uh0\r\n" + 
							"from tms_b_veh_all_details b\r\n" + 
							"inner join tms_b_veh_master_all_details m on b.ba_no=m.ba_no and b.mct_main=m.mct_main\r\n" + 
							"inner join orbat_cmd c on substr(b.fco,1,1)=c.cmd_code and b.unit_sus=c.sus_no\r\n" + 
							"inner join orbat_all_details_view o on b.unit_sus=o.sus_no \r\n" + 
							"where o.arm_desc='CAT-A' and cast(b.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
							"group by 1,2"
							+ " order by 2";				
					stmt = conn.prepareStatement(sql1);
					stmt.setString(1, from);
					stmt.setString(2, to);
					ResultSet rs1 = stmt.executeQuery(); 
				    while(rs1.next()){
				    	  ArrayList<String> list = new ArrayList<String>();
				    	  list.add(rs1.getString("group_name"));
							list.add(rs1.getString("sc_ue1"));
							list.add(rs1.getString("sc_uh1"));
							list.add(rs1.getString("ec_ue2"));
							list.add(rs1.getString("ec_uh2"));
							list.add(rs1.getString("wc_ue3"));
							list.add(rs1.getString("wc_uh3"));
							list.add(rs1.getString("cc_ue4"));
							list.add(rs1.getString("cc_uh4"));
							list.add(rs1.getString("nc_ue5"));
							list.add(rs1.getString("nc_uh5"));
							list.add(rs1.getString("atc_ue6"));
							list.add(rs1.getString("atc_uh6"));
							list.add(rs1.getString("anc_ue7"));
							list.add(rs1.getString("anc_uh7"));
							list.add(rs1.getString("swc_ue8"));
							list.add(rs1.getString("swc_uh8"));
							list.add(rs1.getString("un_ue9"));
							list.add(rs1.getString("un_uh9"));
							list.add(rs1.getString("sfc_uea"));
							list.add(rs1.getString("sfc_uha"));
							list.add(rs1.getString("mod_ue0"));
							list.add(rs1.getString("mod_uh0"));
							alist.add(list);
			        }
				    rs1.close();

					stmt.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				} 
				return alist;
			}

	// 14A. END

	// 16.
	public ArrayList<ArrayList<String>> AUTHORISATION_AND_HOLDING_OF_EME_SPECIALIST_VEHICLES_WITH_ARMY_UNITS_STATE_AS_ON_31_MAR_19_REPORT(
			String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "\r\n" + 
					"select \r\n" + 
					"	b.prf_code,\r\n" + 
					"	m.group_name,\r\n" + 
					"	sum(case when b.ue!=0 then b.ue else 0 end) as total_ue,\r\n" + 
					"	sum(case when b.ud='UNIT' then 1 else 0 end) as total_uh,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no then b.ue else 0 end) as sc_ue1,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sc_uh1,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no then b.ue else 0 end) as ec_ue2,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as ec_uh2,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no then b.ue else 0 end) as wc_ue3,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as wc_uh3,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no then b.ue else 0 end) as cc_ue4,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as cc_uh4,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no then b.ue else 0 end) as nc_ue5,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as nc_uh5,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no then b.ue else 0 end) as atc_ue6,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as atc_uh6,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no then b.ue else 0 end) as anc_ue7,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as anc_uh7,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no then b.ue else 0 end) as swc_ue8,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as swc_uh8,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no then b.ue else 0 end) as un_ue9,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as un_uh9,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no then b.ue else 0 end) as sfc_uea,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sfc_uha,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no then b.ue else 0 end) as mod_ue0,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as mod_uh0\r\n" + 
					"from tms_b_veh_all_details b\r\n" + 
					"inner join tms_b_veh_master_all_details m on b.ba_no=m.ba_no and b.mct_main=m.mct_main\r\n" + 
					"inner join orbat_cmd c on substr(b.fco,1,1)=c.cmd_code and b.unit_sus=c.sus_no\r\n" + 
					"inner join orbat_all_details_view o on b.unit_sus=o.sus_no and o.arm_desc='ELECTRONICS AND MECHANICAL ENGINEERS'\r\n" + 
					"where b.gs_spl='SPL' and cast(b.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2"
					+ " order by 2";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("sc_ue1"));
				list.add(rs1.getString("sc_uh1"));
				list.add(rs1.getString("ec_ue2"));
				list.add(rs1.getString("ec_uh2"));
				list.add(rs1.getString("wc_ue3"));
				list.add(rs1.getString("wc_uh3"));
				list.add(rs1.getString("cc_ue4"));
				list.add(rs1.getString("cc_uh4"));
				list.add(rs1.getString("nc_ue5"));
				list.add(rs1.getString("nc_uh5"));
				list.add(rs1.getString("atc_ue6"));
				list.add(rs1.getString("atc_uh6"));
				list.add(rs1.getString("anc_ue7"));
				list.add(rs1.getString("anc_uh7"));
				list.add(rs1.getString("swc_ue8"));
				list.add(rs1.getString("swc_uh8"));
				list.add(rs1.getString("un_ue9"));
				list.add(rs1.getString("un_uh9"));
				list.add(rs1.getString("sfc_uea"));
				list.add(rs1.getString("sfc_uha"));
				list.add(rs1.getString("mod_ue0"));
				list.add(rs1.getString("mod_uh0"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 16. END

	// 17.
	public ArrayList<ArrayList<String>> AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_WITH_ENGINEER_UNITS_BY_COMMANDS_STATE_AS_ON_31_MAR_19_REPORT(
			String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	b.prf_code,\r\n" + 
					"	m.group_name,\r\n" + 
					"	sum(case when b.ue!=0 then b.ue else 0 end) as total_ue,\r\n" + 
					"	sum(case when b.ud='UNIT' then 1 else 0 end) as total_uh,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no then b.ue else 0 end) as sc_ue1,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sc_uh1,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no then b.ue else 0 end) as ec_ue2,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as ec_uh2,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no then b.ue else 0 end) as wc_ue3,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as wc_uh3,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no then b.ue else 0 end) as cc_ue4,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as cc_uh4,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no then b.ue else 0 end) as nc_ue5,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as nc_uh5,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no then b.ue else 0 end) as atc_ue6,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as atc_uh6,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no then b.ue else 0 end) as anc_ue7,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as anc_uh7,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no then b.ue else 0 end) as swc_ue8,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as swc_uh8,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no then b.ue else 0 end) as un_ue9,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as un_uh9,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no then b.ue else 0 end) as sfc_uea,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sfc_uha,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no then b.ue else 0 end) as mod_ue0,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as mod_uh0\r\n" + 
					"from tms_b_veh_all_details b\r\n" + 
					"inner join tms_b_veh_master_all_details m on b.ba_no=m.ba_no and b.mct_main=m.mct_main\r\n" + 
					"inner join orbat_cmd c on substr(b.fco,1,1)=c.cmd_code and b.unit_sus=c.sus_no\r\n" + 
					"inner join orbat_all_details_view o on b.unit_sus=o.sus_no and o.arm_desc='ENGINEERS'\r\n" + 
					"where cast(b.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2"
					+ " order by 2";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("sc_ue1"));
				list.add(rs1.getString("sc_uh1"));
				list.add(rs1.getString("ec_ue2"));
				list.add(rs1.getString("ec_uh2"));
				list.add(rs1.getString("wc_ue3"));
				list.add(rs1.getString("wc_uh3"));
				list.add(rs1.getString("cc_ue4"));
				list.add(rs1.getString("cc_uh4"));
				list.add(rs1.getString("nc_ue5"));
				list.add(rs1.getString("nc_uh5"));
				list.add(rs1.getString("atc_ue6"));
				list.add(rs1.getString("atc_uh6"));
				list.add(rs1.getString("anc_ue7"));
				list.add(rs1.getString("anc_uh7"));
				list.add(rs1.getString("swc_ue8"));
				list.add(rs1.getString("swc_uh8"));
				list.add(rs1.getString("un_ue9"));
				list.add(rs1.getString("un_uh9"));
				list.add(rs1.getString("sfc_uea"));
				list.add(rs1.getString("sfc_uha"));
				list.add(rs1.getString("mod_ue0"));
				list.add(rs1.getString("mod_uh0"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 17. END

	// 18.
	public ArrayList<ArrayList<String>> authorisation_and_holding_commandsrprt(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	o.cmd_name,\r\n" + 
					"	(case when o.arm_desc in ('MILITRY HOSPITAL','BASE HOSPITAL','GENERAL HOSPITAL') then 'MH/BH/GH' else 'FD AMB UNIT' end) as type_of_unit,\r\n" + 
					"	sum(case when m.group_name='LT AMBULANCE' then b.ue else 0 end) as lt_ue,\r\n" + 
					"	sum(case when m.group_name='LT AMBULANCE' then 1 else 0 end) as lt_uh,\r\n" + 
					"	sum(case when m.group_name='FD AMBULANCE' then b.ue else 0 end) as ft_ue,\r\n" + 
					"	sum(case when m.group_name='FD AMBULANCE' then 1 else 0 end) as ft_uh,\r\n" + 
					"	sum((case when m.group_name='LT AMBULANCE' then b.ue else 0 end)+(case when m.group_name='FD AMBULANCE' then b.ue else 0 end)) as total_ue,\r\n" + 
					"	sum((case when m.group_name='LT AMBULANCE' then 1 else 0 end)+(case when m.group_name='FD AMBULANCE' then 1 else 0 end)) as total_uh\r\n" + 
					"from tms_b_veh_all_details b\r\n" + 
					"inner join tms_b_veh_master_all_details m on b.ba_no=m.ba_no and b.mct_main=m.mct_main\r\n" + 
					"inner join orbat_all_details_view o on b.unit_sus=o.sus_no and o.arm_desc in ('MILITRY HOSPITAL','BASE HOSPITAL','GENERAL HOSPITAL','FIELD AMBULANCE UNIT')\r\n" + 
					"where cast(b.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("cmd_name"));
				list.add(rs1.getString("type_of_unit"));
				list.add(rs1.getString("lt_ue"));
				list.add(rs1.getString("lt_uh"));
				list.add(rs1.getString("ft_ue"));
				list.add(rs1.getString("ft_uh"));
				list.add(rs1.getString("total_ue"));
				list.add(rs1.getString("total_uh"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 18. END

	// 19.

	public ArrayList<ArrayList<String>> authorisation_and_holding_veharmyrprt(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	b.prf_code,\r\n" + 
					"	m.group_name,\r\n" + 
					"	sum(case when b.ue!=0 then b.ue else 0 end) as total_ue,\r\n" + 
					"	sum(case when b.ud='UNIT' then 1 else 0 end) as total_uh,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no then b.ue else 0 end) as sc_ue1,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sc_uh1,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no then b.ue else 0 end) as ec_ue2,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as ec_uh2,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no then b.ue else 0 end) as wc_ue3,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as wc_uh3,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no then b.ue else 0 end) as cc_ue4,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as cc_uh4,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no then b.ue else 0 end) as nc_ue5,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as nc_uh5,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no then b.ue else 0 end) as atc_ue6,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as atc_uh6,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no then b.ue else 0 end) as anc_ue7,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as anc_uh7,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no then b.ue else 0 end) as swc_ue8,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as swc_uh8,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no then b.ue else 0 end) as un_ue9,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as un_uh9,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no then b.ue else 0 end) as sfc_uea,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sfc_uha,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no then b.ue else 0 end) as mod_ue0,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as mod_uh0\r\n" + 
					"from tms_b_veh_all_details b\r\n" + 
					"inner join tms_b_veh_master_all_details m on b.ba_no=m.ba_no and b.mct_main=m.mct_main\r\n" + 
					"inner join orbat_cmd c on substr(b.fco,1,1)=c.cmd_code and b.unit_sus=c.sus_no\r\n" + 
					"inner join orbat_all_details_view o on b.unit_sus=o.sus_no and o.arm_desc!='SIGNALS'\r\n" + 
					"where b.gs_spl='SPL' and cast(b.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2"
					+ " order by 2";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("sc_ue1"));
				list.add(rs1.getString("sc_uh1"));
				list.add(rs1.getString("ec_ue2"));
				list.add(rs1.getString("ec_uh2"));
				list.add(rs1.getString("wc_ue3"));
				list.add(rs1.getString("wc_uh3"));
				list.add(rs1.getString("cc_ue4"));
				list.add(rs1.getString("cc_uh4"));
				list.add(rs1.getString("nc_ue5"));
				list.add(rs1.getString("nc_uh5"));
				list.add(rs1.getString("atc_ue6"));
				list.add(rs1.getString("atc_uh6"));
				list.add(rs1.getString("anc_ue7"));
				list.add(rs1.getString("anc_uh7"));
				list.add(rs1.getString("swc_ue8"));
				list.add(rs1.getString("swc_uh8"));
				list.add(rs1.getString("un_ue9"));
				list.add(rs1.getString("un_uh9"));
				list.add(rs1.getString("sfc_uea"));
				list.add(rs1.getString("sfc_uha"));
				list.add(rs1.getString("mod_ue0"));
				list.add(rs1.getString("mod_uh0"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 19. END

	// 20.
	public ArrayList<ArrayList<String>> authorisation_and_holding_vehunitrprt(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	b.prf_code,\r\n" + 
					"	m.group_name,\r\n" + 
					"	sum(case when b.ue!=0 then b.ue else 0 end) as total_ue,\r\n" + 
					"	sum(case when b.ud='UNIT' then 1 else 0 end) as total_uh,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no then b.ue else 0 end) as sc_ue1,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sc_uh1,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no then b.ue else 0 end) as ec_ue2,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as ec_uh2,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no then b.ue else 0 end) as wc_ue3,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as wc_uh3,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no then b.ue else 0 end) as cc_ue4,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as cc_uh4,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no then b.ue else 0 end) as nc_ue5,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as nc_uh5,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no then b.ue else 0 end) as atc_ue6,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as atc_uh6,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no then b.ue else 0 end) as anc_ue7,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as anc_uh7,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no then b.ue else 0 end) as swc_ue8,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as swc_uh8,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no then b.ue else 0 end) as un_ue9,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as un_uh9,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no then b.ue else 0 end) as sfc_uea,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sfc_uha,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no then b.ue else 0 end) as mod_ue0,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as mod_uh0\r\n" + 
					"from tms_b_veh_all_details b\r\n" + 
					"inner join tms_b_veh_master_all_details m on b.ba_no=m.ba_no and b.mct_main=m.mct_main\r\n" + 
					"inner join orbat_cmd c on substr(b.fco,1,1)=c.cmd_code and b.unit_sus=c.sus_no\r\n" + 
					"inner join orbat_all_details_view o on b.unit_sus=o.sus_no \r\n" + 
					"where b.gs_spl='SPL' and cast(b.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2"
					+ "order by 2";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("sc_ue1"));
				list.add(rs1.getString("sc_uh1"));
				list.add(rs1.getString("ec_ue2"));
				list.add(rs1.getString("ec_uh2"));
				list.add(rs1.getString("wc_ue3"));
				list.add(rs1.getString("wc_uh3"));
				list.add(rs1.getString("cc_ue4"));
				list.add(rs1.getString("cc_uh4"));
				list.add(rs1.getString("nc_ue5"));
				list.add(rs1.getString("nc_uh5"));
				list.add(rs1.getString("atc_ue6"));
				list.add(rs1.getString("atc_uh6"));
				list.add(rs1.getString("anc_ue7"));
				list.add(rs1.getString("anc_uh7"));
				list.add(rs1.getString("swc_ue8"));
				list.add(rs1.getString("swc_uh8"));
				list.add(rs1.getString("un_ue9"));
				list.add(rs1.getString("un_uh9"));
				list.add(rs1.getString("sfc_uea"));
				list.add(rs1.getString("sfc_uha"));
				list.add(rs1.getString("mod_ue0"));
				list.add(rs1.getString("mod_uh0"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 20. END
  
	// B_VEHCL
	
	// 1.

	public ArrayList<ArrayList<String>> PRF_WISE_ALL_INDIA_DEFI_SURPLUS_US_OF_B_VEHICLE_STATUS_REPORT(String from,
			String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select\r\n" + 
					"	prf_code,\r\n" + 
					"	group_name,\r\n" + 
					"	reserve,\r\n" + 
					"	ue,\r\n" + 
					"	uh_upto_cl_iv,\r\n" + 
					"	(case when ue!=0 then cast((ue+reserve/100) as integer) else 0 end) as ue_with_rsrv,\r\n" + 
					"	(case when ue!=0 then ue-uh else 0 end) as sur_defi_withoutrsrv,\r\n" + 
					"	(case when ue!=0 then cast((ue+reserve/100) as integer)-uh else 0 end) as sur_defi_withrsrv,\r\n" + 
					"	(case when ue!=0 then cast(uh/ue*100 as integer) else 0 end) as sur_defi\r\n" + 
					"from\r\n" + 
					"(select\r\n" + 
					"	b.prf_code,\r\n" + 
					"	m.group_name,\r\n" + 
					"	(case when b.reserve != 0 then b.reserve else 0 end) as reserve,\r\n" + 
					"	sum(case when b.ue!=0 then b.ue else 0 end) as ue,\r\n" + 
					"	count(case when b.classification in ('1','2','3','4') and b.qfd is null then 1 else 0 end) as uh_upto_cl_iv,\r\n" + 
					"	count(b.ba_no) as uh\r\n" + 
					"from tms_b_veh_all_details b\r\n" + 
					"inner join tms_b_veh_master_all_details m on b.ba_no=m.ba_no and b.mct_main=m.mct_main\r\n" + 
					"where cast(b.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3"
					+ "order by 1) as foo";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("prf_code"));
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("reserve"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh_upto_cl_iv"));
				list.add(rs1.getString("ue_with_rsrv"));
				list.add(rs1.getString("sur_defi_withoutrsrv"));
				list.add(rs1.getString("sur_defi_withrsrv"));
				list.add(rs1.getString("sur_defi"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 1. END
	
	// 2.

	public ArrayList<ArrayList<String>> ALL_INDIA_HOLDING_OF_B_VEH_POSNCURL_REPORT(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select\r\n" + 
					"	b.prf_code,\r\n" + 
					"	m.group_name,\r\n" + 
					"	b.mct_main,\r\n" + 
					"	substr(cast(b.mct as varchar),5,3) as make,\r\n" + 
					"	substr(cast(b.mct as varchar),8,3) as model,\r\n" + 
					"	b.ud,\r\n" + 
					"	m.std_nomclature,\r\n" + 
					"	sum(case when b.classification='1' then 1 else 0 end) as cl_i,\r\n" + 
					"	sum(case when b.classification='2' then 1 else 0 end) as cl_ii,\r\n" + 
					"	sum(case when b.classification='3' then 1 else 0 end) as cl_iii,\r\n" + 
					"	sum(case when b.classification='4' then 1 else 0 end) as cl_iv,\r\n" + 
					"	sum(case when b.classification in ('1','2','3','4') then 1 else 0 end) as total,\r\n" + 
					"	sum(case when b.classification='5' then 1 else 0 end) as cl_v,\r\n" + 
					"	sum(case when b.classification in ('1','2','3','4','5') then 1 else 0 end) as grand_total\r\n" + 
					"from tms_b_veh_all_details b\r\n" + 
					"inner join tms_b_veh_master_all_details m on b.ba_no=m.ba_no and b.mct_main=m.mct_main \r\n" + 
					"where cast(b.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4,5,6,7\r\n" + 
					"order by 1";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("prf_code"));
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("mct_main"));
				list.add(rs1.getString("make"));
				list.add(rs1.getString("model"));
				list.add(rs1.getString("ud"));
				list.add(rs1.getString("std_nomclature"));
				list.add(rs1.getString("cl_i"));
				list.add(rs1.getString("cl_ii"));
				list.add(rs1.getString("cl_iii"));
				list.add(rs1.getString("cl_iv"));
				list.add(rs1.getString("total"));
				list.add(rs1.getString("cl_v"));
				list.add(rs1.getString("grand_total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 2. END
		 
	// 3.

	public ArrayList<ArrayList<String>> TABLE_SHOWING_ANTICIPATED_DISCARD_FOR_NEXT_4_5_YEARSCURL_REPORT(String from,String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		
		/*Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				 * list.add(rs1.getString("sus_no")); list.add(rs1.getString("user_arm_name"));
				 * list.add(rs1.getString("parent_arm_name")); list.add(rs1.getString("rank"));
				 * list.add(rs1.getString("appt_trade")); list.add(rs1.getString("total_ue"));
				 
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return alist;
	}
	// 3. END

	// 4.

	public ArrayList<ArrayList<String>> RECONCILIATION_STATEMENT_OF_B_VEHICLES_FOR_THE_PERIOD_REPORT(String from,
			String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		/*Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("user_arm_name"));
				list.add(rs1.getString("parent_arm_name"));
				list.add(rs1.getString("rank"));
				list.add(rs1.getString("appt_trade"));
				list.add(rs1.getString("total_ue"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return alist;
	}
	// 4. END
				 
	// 5.

	public ArrayList<ArrayList<String>> COMMAND_SUMMARY_OF_GENERAL_B_VEHICLES_POSN_REPORT(String from, String to,String comd_name) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select\r\n" + 
					"	b.mct_main,\r\n" + 
					"	m.mct_nomen,\r\n" + 
					"	o.coprs_name,\r\n" + 
					"	(case when o.div_name is null then o.bde_name else o.div_name end) as formation,\r\n" + 
					"	sum(case when b.ue!=0 then b.ue else 0 end) as ue,\r\n" + 
					"	sum(case when b.ud='UNIT' then 1 else 0 end) as uh,\r\n" + 
					"	sum(case when b.ud in ('UNIT','DEPOT') then 1 else 0 end) as total,\r\n" + 
					"	(case when cast(sum(case when b.ud='UNIT' then 1 end)/sum(case when b.ue!= '0' then b.ue end)*100 as integer)!=0 then cast(sum(case when b.ud='UNIT' then 1 end)/sum(case when b.ue!= '0' then b.ue end)*100 as integer) else 0 end)  as pers_holding\r\n" + 
					"from tms_b_veh_all_details b\r\n" + 
					"inner join tms_b_veh_master_all_details m on b.ba_no=m.ba_no and b.mct_main=m.mct_main\r\n" + 
					"inner join orbat_all_details_view o on b.unit_sus=o.sus_no and m.unit_sus=o.sus_no\r\n" + 
					"where b.gs_spl='GS' and o.cmd_name=? and cast(b.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4\r\n" + 
					"order by 1";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, comd_name);
			stmt.setString(2, from);
			stmt.setString(3, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("mct_main"));
				list.add(rs1.getString("mct_nomen"));
				list.add(rs1.getString("coprs_name"));
				list.add(rs1.getString("formation"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh"));
				list.add(rs1.getString("total"));
				list.add(rs1.getString("pers_holding"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 5. END
					 
	// 6.

	public ArrayList<ArrayList<String>> CORPS_SUMMARY_OF_GENERAL_B_VEHICLES_POSN_REPORT(String from, String to,
			String corps_name) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select\r\n" + 
					"	b.mct_main,\r\n" + 
					"	m.mct_nomen,\r\n" + 
					"	(case when o.div_name is null then o.bde_name else o.div_name end) as formation,\r\n" + 
					"	sum(case when b.ue!=0 then b.ue else 0 end) as ue,\r\n" + 
					"	sum(case when b.ud='UNIT' then 1 else 0 end) as uh,\r\n" + 
					"	sum(case when b.ud in ('UNIT','DEPOT') then 1 else 0 end) as total,\r\n" + 
					"	(case when cast(sum(case when b.ud='UNIT' then 1 end)/sum(case when b.ue!= '0' then b.ue end)*100 as integer)!=0 then cast(sum(case when b.ud='UNIT' then 1 end)/sum(case when b.ue!= '0' then b.ue end)*100 as integer) else 0 end)  as pers_holding\r\n" + 
					"from tms_b_veh_all_details b\r\n" + 
					"inner join tms_b_veh_master_all_details m on b.ba_no=m.ba_no and b.mct_main=m.mct_main\r\n" + 
					"inner join orbat_all_details_view o on b.unit_sus=o.sus_no and m.unit_sus=o.sus_no\r\n" + 
					"where b.gs_spl='GS' and o.coprs_name=? and cast(b.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3\r\n" + 
					"order by 1";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, corps_name);
			stmt.setString(2, from);
			stmt.setString(3, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("mct_main"));
				list.add(rs1.getString("mct_nomen"));
				list.add(rs1.getString("formation"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh"));
				list.add(rs1.getString("total"));
				list.add(rs1.getString("pers_holding"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 6. END
	
	// A VEH

	public ArrayList<ArrayList<String>> command_wise_usage_meteage_analysis_of_A_vehs_cl_3_POSN_TANKS_APCsrprt(
			String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	a.prf_code,\r\n" + 
					"	b.group_name,\r\n" + 
					"	o.cmd_name,\r\n" + 
					"	sum(case when a.kms_run between 0 and 1000 and a.classification in ('1','2','3') then 1 else 0 end) as upto_1000,\r\n" + 
					"	sum(case when a.kms_run between 1001 and 2000 and a.classification in ('1','2','3') then 1 else 0 end) as upto_2000,\r\n" + 
					"	sum(case when a.kms_run between 2001 and 2500 and a.classification in ('1','2','3') then 1 else 0 end) as upto_2500,\r\n" + 
					"	sum(case when a.kms_run between 2501 and 3500 and a.classification in ('1','2','3') then 1 else 0 end) as upto_3500,\r\n" + 
					"	sum(case when a.kms_run between 3501 and 4500 and a.classification in ('1','2','3') then 1 else 0 end) as upto_4500,\r\n" + 
					"	sum(case when a.kms_run between 4501 and 5000 and a.classification in ('1','2','3') then 1 else 0 end) as upto_5000,\r\n" + 
					"	sum(case when a.kms_run>5000 and a.classification in ('1','2','3') then 1 else 0 end) as above_5000,\r\n" + 
					"	sum(case when a.kms_run>=0 and a.classification in ('1','2','3') then 1 else 0 end) as total,\r\n" + 
					"	sum(case when a.classification in ('4') then 1 else 0 end) as cl_iv,\r\n" + 
					"	sum(case when a.kms_run>=0 then 1 else 0 end) as grand_total\r\n" + 
					"from tms_a_veh_all_details a\r\n" + 
					"inner join tms_a_veh_master_all_details b on a.ba_no=b.ba_no and a.mct_main=b.mct_main\r\n" + 
					"inner join orbat_all_details_view o on b.unit_sus=o.sus_no\r\n" + 
					"where cast(a.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3\r\n" + 
					"order by 2";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("cmd_name"));
				list.add(rs1.getString("upto_1000"));
				list.add(rs1.getString("upto_2000"));
				list.add(rs1.getString("upto_2500"));
				list.add(rs1.getString("upto_3500"));
				list.add(rs1.getString("upto_4500"));
				list.add(rs1.getString("upto_5000"));
				list.add(rs1.getString("above_5000"));
				list.add(rs1.getString("total"));
				list.add(rs1.getString("cl_iv"));
				list.add(rs1.getString("grand_total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

//===============================================================================================================================================================================


	public ArrayList<ArrayList<String>> holding_of_tanks_unit_wise_posn_mech_BN_APCsrprt(String from,String to,String arm_name,String prf_name,String force_type, String unit_name) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	a.unit_sus,\r\n" + 
					"	o.unit_name,\r\n" + 
					"	a.mct_main,\r\n" + 
					"	count(a.mct_main) as count_mct\r\n" + 
					"from tms_a_veh_all_details a\r\n" + 
					"inner join orbat_all_details_view o on a.unit_sus=o.sus_no\r\n" + 
					"where a.ud='UNIT' and o.arm_code=? and a.prf_code=? and o.type_of_force=? and o.unit_name=? and cast(a.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3\r\n" + 
					"order by 1";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, arm_name);
			stmt.setString(2, prf_name);
			stmt.setString(3, force_type);
			stmt.setString(4, unit_name);
			stmt.setString(5, from);
			stmt.setString(6, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("unit_sus"));
				list.add(rs1.getString("unit_name"));
				list.add(rs1.getString("mct_main"));
				list.add(rs1.getString("count_mct"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

//=====================================================================================================================================================================================

	public ArrayList<ArrayList<String>> holding_state_of_A_vehs_mct_and_classification_wise_depot_wise_holdingsrprt(
			String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	a.mct_main,\r\n" + 
					"	b.mct_nomen,\r\n" + 
					"	o.unit_name as depot,\r\n" + 
					"	a.country_of_origin,\r\n" + 
					"	sum(case when a.classification='1' then 1 else 0 end) as cl_i,\r\n" + 
					"	sum(case when a.classification='2' then 1 else 0 end) as cl_ii,\r\n" + 
					"	sum(case when a.classification='3' then 1 else 0 end) as cl_iii,\r\n" + 
					"	sum(case when a.classification='4' then 1 else 0 end) as cl_iv,\r\n" + 
					"	sum(case when a.classification='5' then 1 else 0 end) as cl_v,\r\n" + 
					"	sum(case when a.classification='6' then 1 else 0 end) as cl_vi,\r\n" + 
					"	sum(case when a.classification='7' then 1 else 0 end) as cl_vii,\r\n" + 
					"	sum(case when a.classification in ('1','2','3','4','5','6','7') then 1 else 0 end) as total\r\n" + 
					"from tms_a_veh_all_details a\r\n" + 
					"inner join tms_a_veh_master_all_details b on a.ba_no=b.ba_no and a.mct_main=b.mct_main\r\n" + 
					"inner join orbat_all_details_view o on b.unit_sus=o.sus_no\r\n" + 
					"where a.ud='DEPOT' and cast(a.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4\r\n" + 
					"order by 1";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("mct_main"));
				list.add(rs1.getString("mct_nomen"));
				list.add(rs1.getString("depot"));
				list.add(rs1.getString("country_of_origin"));
				list.add(rs1.getString("cl_i"));
				list.add(rs1.getString("cl_ii"));
				list.add(rs1.getString("cl_iii"));
				list.add(rs1.getString("cl_iv"));
				list.add(rs1.getString("cl_v"));
				list.add(rs1.getString("cl_vi"));
				list.add(rs1.getString("cl_vii"));
				list.add(rs1.getString("total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

//========================================================================================================================================================================================

	public ArrayList<ArrayList<String>> holding_of_A_vehs_mct_and_classification_wise_posnrprt(String from, String to,String comd_name, String force_type) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	b.mct_nomen,\r\n" + 
					"	a.mct_main,\r\n" + 
					"	a.ud,\r\n" + 
					"	a.country_of_origin,\r\n" + 
					"	sum(case when a.classification='1' then 1 else 0 end) as cl_i,\r\n" + 
					"	sum(case when a.classification='2' then 1 else 0 end) as cl_ii,\r\n" + 
					"	sum(case when a.classification='3' then 1 else 0 end) as cl_iii,\r\n" + 
					"	sum(case when a.classification='4' then 1 else 0 end) as cl_iv,\r\n" + 
					"	sum(case when a.classification='5' then 1 else 0 end) as cl_v,\r\n" + 
					"	sum(case when a.classification='6' then 1 else 0 end) as cl_vi,\r\n" + 
					"	sum(case when a.classification='7' then 1 else 0 end) as cl_vii,\r\n" + 
					"	sum(case when a.classification in ('1','2','3','4','5','6','7') then 1 else 0 end) as total_upto_cl_v\r\n" + 
					"from tms_a_veh_all_details a\r\n" + 
					"inner join tms_a_veh_master_all_details b on a.ba_no=b.ba_no and a.mct_main=b.mct_main\r\n" + 
					"inner join orbat_all_details_view o on b.unit_sus=o.sus_no\r\n" + 
					"where o.type_of_force=? and o.cmd_name=? and cast(a.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4\r\n" + 
					"order by mct_main";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, force_type);
			stmt.setString(2, comd_name);
			stmt.setString(3, from);
			stmt.setString(4, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("mct_nomen"));
				list.add(rs1.getString("mct_main"));
				list.add(rs1.getString("ud"));
				list.add(rs1.getString("country_of_origin"));
				list.add(rs1.getString("cl_i"));
				list.add(rs1.getString("cl_ii"));
				list.add(rs1.getString("cl_iii"));
				list.add(rs1.getString("cl_iv"));
				list.add(rs1.getString("cl_v"));
				list.add(rs1.getString("cl_vi"));
				list.add(rs1.getString("cl_vii"));
				list.add(rs1.getString("total_upto_cl_v"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

//=========================================================================================================================================================================================

	public ArrayList<ArrayList<String>> HOLDING_OF_A_VEHICALES_COMMAND_AND_DEPOT_WISE_POSNrprt(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	a.mct_main,\r\n" + 
					"	b.mct_nomen,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no then a.ue else 0 end) as sc_ue1,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='1' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sc_uh1,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no then a.ue else 0 end) as ec_ue2,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='2' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as ec_uh2,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no then a.ue else 0 end) as wc_ue3,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='3' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as wc_uh3,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no then a.ue else 0 end) as cc_ue4,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='4' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as cc_uh4,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no then a.ue else 0 end) as nc_ue5,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='5' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as nc_uh5,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no then a.ue else 0 end) as atc_ue6,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='6' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as atc_uh6,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no then a.ue else 0 end) as anc_ue7,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='7' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as anc_uh7,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no then a.ue else 0 end) as swc_ue8,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='8' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as swc_uh8,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no then a.ue else 0 end) as un_ue9,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='9' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as un_uh9,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no then a.ue else 0 end) as sfc_uea,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='A' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as sfc_uha,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no then a.ue else 0 end) as mod_ue0,\r\n" + 
					"	sum(case when substr(b.fco,1,1)='0' and b.unit_sus=c.sus_no and b.ud='UNIT' then 1 else 0 end) as mod_uh0,\r\n" + 
					"	sum(case when a.ue!=0 then a.ue else 0 end) as total_ue,\r\n" + 
					"	sum(case when b.ud='UNIT' then 1 else 0 end) as total_uh\r\n" + 
					"from tms_a_veh_all_details a\r\n" + 
					"inner join tms_a_veh_master_all_details b on a.ba_no=b.ba_no and a.mct_main=b.mct_main\r\n" + 
					"inner join orbat_cmd c on substr(a.fco,1,1)=c.cmd_code and a.unit_sus=c.sus_no\r\n" + 
					"inner join orbat_all_details_view o on b.unit_sus=o.sus_no\r\n" + 
					"where cast(a.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2\r\n" + 
					"order by 1";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("mct_main"));
				list.add(rs1.getString("mct_nomen"));
				list.add(rs1.getString("sc_ue1"));
				list.add(rs1.getString("sc_uh1"));
				list.add(rs1.getString("ec_ue2"));
				list.add(rs1.getString("ec_uh2"));
				list.add(rs1.getString("wc_ue3"));
				list.add(rs1.getString("wc_uh3"));
				list.add(rs1.getString("cc_ue4"));
				list.add(rs1.getString("cc_uh4"));
				list.add(rs1.getString("nc_ue5"));
				list.add(rs1.getString("nc_uh5"));
				list.add(rs1.getString("atc_ue6"));
				list.add(rs1.getString("atc_uh6"));
				list.add(rs1.getString("anc_ue7"));
				list.add(rs1.getString("anc_uh7"));
				list.add(rs1.getString("swc_ue8"));
				list.add(rs1.getString("swc_uh8"));
				list.add(rs1.getString("un_ue9"));
				list.add(rs1.getString("un_uh9"));
				list.add(rs1.getString("sfc_uea"));
				list.add(rs1.getString("sfc_uha"));
				list.add(rs1.getString("mod_ue0"));
				list.add(rs1.getString("mod_uh0"));
				list.add(rs1.getString("total_ue"));
				list.add(rs1.getString("total_uh"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

//======================================================================================================================================================================================

	public ArrayList<ArrayList<String>> holding_of_A_veh_southern_command_nonfield_forcesrprt(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	b.mct_nomen,\r\n" + 
					"	a.mct_main,\r\n" + 
					"	(case when a.ud='UNIT' then a.ud else a.unit_sus end) as unit_depot,\r\n" + 
					"	a.country_of_origin,\r\n" + 
					"	sum(case when a.classification='1' then 1 else 0 end) as cl_i,\r\n" + 
					"	sum(case when a.classification='2' then 1 else 0 end) as cl_ii,\r\n" + 
					"	sum(case when a.classification='3' then 1 else 0 end) as cl_iii,\r\n" + 
					"	sum(case when a.classification='4' then 1 else 0 end) as cl_iv,\r\n" + 
					"	sum(case when a.classification='5' then 1 else 0 end) as cl_v,\r\n" + 
					"	sum(case when a.classification='6' then 1 else 0 end) as cl_vi,\r\n" + 
					"	sum(case when a.classification='7' then 1 else 0 end) as cl_vii,\r\n" + 
					"	sum(case when a.classification in ('1','2','3','4','5','6','7') then 1 else 0 end) as total\r\n" + 
					"from tms_a_veh_all_details a\r\n" + 
					"inner join tms_a_veh_master_all_details b on a.ba_no=b.ba_no and a.mct_main=b.mct_main\r\n" + 
					"where cast(a.insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4\r\n" + 
					"order by 2";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("mct_nomen"));
				list.add(rs1.getString("mct_main"));
				list.add(rs1.getString("unit_depot"));
				list.add(rs1.getString("country_of_origin"));
				list.add(rs1.getString("cl_i"));
				list.add(rs1.getString("cl_ii"));
				list.add(rs1.getString("cl_iii"));
				list.add(rs1.getString("cl_iv"));
				list.add(rs1.getString("cl_v"));
				list.add(rs1.getString("cl_vi"));
				list.add(rs1.getString("cl_vii"));
				list.add(rs1.getString("total"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
}
