package com.dao.psg.Enrollment_Reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


public class No_of_battle_Physical_Casualty_List_and_Total_DaoImpl implements No_of_battle_Physical_Casualty_List_and_Total_Dao{

	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public List<Map<String, Object>> No_of_battle_Physical_Casualty_List_and_TotalList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			String off_cat,String from_date,String to_date)
	{
	 String SearchValue = GenerateQueryWhereClause_SQL(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no, off_cat, from_date, to_date); 
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";
	
	String pageL = "";
    if(pageLength == -1){
		pageL = "ALL";
	}else {
		pageL = String.valueOf(pageLength);
		
	}

	try{	
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if (off_cat.equals("1")) {
				
			
			q="select distinct \n"
					+ "fv.unit_name as cmd_unit,\n"
					+ "fvm.unit_name as corp_unit,\n"
					+ "div.unit_name as div_unit,\n"
					+ "bde.unit_name as bde_unit,\n"
					+ "orb.unit_name,\n"
					+ "comm.unit_sus_no,\n"
					+ "comm.personnel_no,rk.description,comm.name,bp.classification_of_casuality,ltrim(TO_CHAR(bp.date_of_casuality ,'DD-MON-YYYY'),'0') as date_of_casuality\n"
					+ "from tb_psg_trans_proposed_comm_letter  comm\n"
					+ "inner join tb_psg_census_battle_physical_casuality bp  on bp.comm_id= comm.id \n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
					+ "left join tb_psg_census_detail_p p on p.comm_id= comm.id  and p.status='1' \n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'where bp.status='1' and comm.status in ('1','5') and orb.status_sus_no='Active' \n"
					+SearchValue +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage  ;
			}		
			if (off_cat.equals("2")) {
			
				q = " select distinct \n"
						+ "fv.unit_name as cmd_unit,\n"
						+ "fvm.unit_name as corp_unit,\n"
						+ "div.unit_name as div_unit,\n"
						+ "bde.unit_name as bde_unit,\n"
						+ "orb.unit_name,\n"
						+ "p.unit_sus_no,\n"
						+ "p.army_no,rk.rank,p.full_name,bp.classification_of_casuality,\n"
						+ "ltrim(TO_CHAR(bp.date_of_casuality ,'DD-MON-YYYY'),'0') as date_of_casuality\n"
						+ "from  tb_psg_census_jco_or_p p \n"
						+ "inner join tb_psg_census_battle_physical_casuality_jco bp  on bp.jco_id= p.id \n"
						+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no \n"
						+ "INNER JOIN tb_psg_mstr_rank_jco rk on p.rank=rk.id\n"
						+ "left join all_fmn_view fv  on orb.sus_no = p.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
						+ "left join all_fmn_view fvm  on orb.sus_no = p.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
						+ "left join all_fmn_view div  on orb.sus_no = p.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
						+ "left join all_fmn_view bde  on orb.sus_no = p.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
						+ "where bp.status='1' and p.status in ('1','5') and orb.status_sus_no='Active'\n"
				+SearchValue + " ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			
			stmt=conn.prepareStatement(q);
		
			stmt = setQueryWhereClause_SQL(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no,off_cat, from_date, to_date);
			ResultSet rs = stmt.executeQuery();  
	      ResultSetMetaData metaData = rs.getMetaData();
	     int k = startPage + 1;
	      int columnCount = metaData.getColumnCount();
	      
	  	while (rs.next()) {
	  		
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
			
			list.add(columns);
			k ++;
		}
	      rs.close();
	      stmt.close();
	      conn.close();
	      

	   }catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
			  }
			}
		}
		return list;
	}
	
	public long No_of_battle_Physical_Casualty_List_and_Total_Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no,String off_cat,String from_date,String to_date) {
	 String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps,cont_div, cont_bde,
				 unit_name, unit_sus_no, off_cat, from_date, to_date); 
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
			
				
				if (off_cat.equals("1")) {
			
				  q="select count(app.*) from(\r\n"
					  		+ "select distinct \n"
					  		+ "fv.unit_name as cmd_unit,\n"
					  		+ "fvm.unit_name as corp_unit,\n"
					  		+ "div.unit_name as div_unit,\n"
					  		+ "bde.unit_name as bde_unit,\n"
					  		+ "orb.unit_name,\n"
					  		+ "comm.unit_sus_no,\n"
					  		+ "comm.personnel_no,rk.description,comm.name,bp.classification_of_casuality,ltrim(TO_CHAR(bp.date_of_casuality ,'DD-MON-YYYY'),'0') as date_of_casuality\n"
					  		+ "from tb_psg_trans_proposed_comm_letter  comm\n"
					  		+ "inner join tb_psg_census_battle_physical_casuality bp  on bp.comm_id= comm.id \n"
					  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
					  		+ "left join tb_psg_census_detail_p p on p.comm_id= comm.id  and p.status='1' \n"
					  		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					  		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					  		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					  		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					  		+ "where bp.status='1' and comm.status in ('1','5') and orb.status_sus_no='Active'  \n"
					  		+ ""+SearchValue+ " ) app" ;
				}
				if (off_cat.equals("2")) {
					
					 q="select count(app.*) from(\r\n"
						  		+ "select distinct \n"
						  		+ "fv.unit_name as cmd_unit,\n"
						  		+ "fvm.unit_name as corp_unit,\n"
						  		+ "div.unit_name as div_unit,\n"
						  		+ "bde.unit_name as bde_unit,\n"
						  		+ "orb.unit_name,\n"
						  		+ "p.unit_sus_no,\n"
						  		+ "p.army_no,rk.rank,p.full_name,bp.classification_of_casuality,\n"
						  		+ "ltrim(TO_CHAR(bp.date_of_casuality ,'DD-MON-YYYY'),'0') as date_of_casuality\n"
						  		+ "from  tb_psg_census_jco_or_p p \n"
						  		+ "inner join tb_psg_census_battle_physical_casuality_jco bp  on bp.jco_id= p.id \n"
						  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no \n"
						  		+ "INNER JOIN tb_psg_mstr_rank_jco rk on p.rank=rk.id\n"
						  		+ "left join all_fmn_view fv  on orb.sus_no = p.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
						  		+ "left join all_fmn_view fvm  on orb.sus_no = p.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
						  		+ "left join all_fmn_view div  on orb.sus_no = p.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
						  		+ "left join all_fmn_view bde  on orb.sus_no = p.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n "
						  		+ "where bp.status='1' and p.status in ('1','5') and orb.status_sus_no='Active' \n"
						  		+ ""+SearchValue
						  				+ " ) app" ;
					
				}
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, off_cat, from_date, to_date);
			
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {  
					total = rs.getInt(1);
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
			return (long) total;
		}
	
	
	public List<Map<String, Object>> No_of_battle_Physical_Casualty_List_and_Total_Summary_List(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,String off_cat
			,String from_date,String to_date)
	{
	 String SearchValue = GenerateQueryWhereClause_SQL_Summary(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no,off_cat, from_date, to_date); 
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";
	
	String pageL = "";
 if(pageLength == -1){
		pageL = "ALL";
	}else {
		pageL = String.valueOf(pageLength);
		
	}

	try{	
		
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if (off_cat.equals("1")) {
				
			
			q="select distinct \n"
					+ "fv.unit_name as cmd_unit,\n"
					+ "fvm.unit_name as corp_unit,\n"
					+ "div.unit_name as div_unit,\n"
					+ "bde.unit_name as bde_unit,\n"
					+ "orb.unit_name,\n"
					+ "comm.unit_sus_no,\n"
					+ "count(bp.comm_id) as officer,\n"
					+ "count(bp.comm_id) filter (where bp.classification_of_casuality ='physical_casuality') as physical_casuality,\n"
					+ "count(bp.comm_id) filter (where bp.classification_of_casuality ='battle_casuality') as battle_casuality,\n"
					+ "count(bp.comm_id) as total\n"
					+ "from tb_psg_trans_proposed_comm_letter  comm\n"
					+ "inner join tb_psg_census_battle_physical_casuality bp  on bp.comm_id= comm.id \n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
					+ "inner join tb_psg_census_detail_p p on p.comm_id= comm.id and p.status='1' \n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ "where bp.status='1' and comm.status in ('1','5') and orb.status_sus_no='Active'  \n"
					+SearchValue + "group by 1,2,3,4,5,6  " +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}		
			if (off_cat.equals("2")) {
			
				q = "select distinct \n"
						+ "fv.unit_name as cmd_unit,\n"
						+ "fvm.unit_name as corp_unit,\n"
						+ "div.unit_name as div_unit,\n"
						+ "bde.unit_name as bde_unit,\n"
						+ "orb.unit_name,\n"
						+ "p.unit_sus_no,\n"
						+ "count(bp.jco_id) FILTER (where p.category='JCO'  )as jco,\n"
						+ "count(bp.jco_id) FILTER (where p.category='OR'  )as ors,\n"
						+ "count(bp.jco_id) filter (where bp.classification_of_casuality ='physical_casuality') as physical_casuality,\n"
						+ "count(bp.jco_id) filter (where bp.classification_of_casuality ='battle_casuality') as battle_casuality,\n"
						+ "count (bp.jco_id) as total\n"
						+ "from  tb_psg_census_jco_or_p p \n"
						+ "inner join tb_psg_census_battle_physical_casuality_jco bp  on bp.jco_id= p.id \n"
						+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no \n"
						+ "INNER JOIN tb_psg_mstr_rank_jco rk on p.rank=rk.id\n"
						+ "left join all_fmn_view fv  on orb.sus_no = p.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
						+ "left join all_fmn_view fvm  on orb.sus_no = p.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
						+ "left join all_fmn_view div  on orb.sus_no = p.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
						+ "left join all_fmn_view bde  on orb.sus_no = p.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
						+ "where bp.status='1' and p.status in ('1','5') and orb.status_sus_no='Active' \n"
						+SearchValue + "group by 1,2,3,4,5,6  " +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			
			stmt=conn.prepareStatement(q);
		
			stmt = setQueryWhereClause_SQL_Summary(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no,off_cat, from_date, to_date);
	      ResultSet rs = stmt.executeQuery();  
	      ResultSetMetaData metaData = rs.getMetaData();
	     int k = startPage + 1;
	      int columnCount = metaData.getColumnCount();
	      
	  	while (rs.next()) {
	  		
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
			
			list.add(columns);
			k ++;
		}
	      rs.close();
	      stmt.close();
	      conn.close();
	      

	   }catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
			  }
			}
		}
		return list;
	}
	
	 
	 public long No_of_battle_Physical_Casualty_List_and_Total_Summary_Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
				String unit_name,String unit_sus_no,String off_cat,String from_date,String to_date) {
		 String SearchValue = GenerateQueryWhereClause_SQL_Summary(Search, cont_comd, cont_corps,cont_div, cont_bde,
					 unit_name, unit_sus_no,off_cat, from_date, to_date); 
			int total = 0;
				String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
				String q = null;
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
				
					if (off_cat.equals("1")) {
				
					  q="select count(app.*) from(\r\n"
						  		+ "select distinct \n"
						  		+ "fv.unit_name as cmd_unit,\n"
						  		+ "fvm.unit_name as corp_unit,\n"
						  		+ "div.unit_name as div_unit,\n"
						  		+ "bde.unit_name as bde_unit,\n"
						  		+ "orb.unit_name,\n"
						  		+ "comm.unit_sus_no,\n"
						  		+ "count(bp.comm_id) as officer,\n"
						  		+ "count(bp.comm_id) filter (where bp.classification_of_casuality ='physical_casuality') as physical_casuality,\n"
						  		+ "count(bp.comm_id) filter (where bp.classification_of_casuality ='battle_casuality') as battle_casuality,\n"
						  		+ "count(bp.comm_id) as total\n"
						  		+ "from tb_psg_trans_proposed_comm_letter  comm\n"
						  		+ "inner join tb_psg_census_battle_physical_casuality bp  on bp.comm_id= comm.id \n"
						  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
						  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
						  		+ "inner join tb_psg_census_detail_p p on p.comm_id= comm.id and p.status='1' \n"
						  		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
						  		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
						  		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
						  		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
						  		+ "where bp.status='1' and comm.status in ('1','5') and orb.status_sus_no='Active'  \n"
						  		+ ""+ SearchValue +"group by 1,2,3,4,5,6\n"
						  				+ " ) app" ;
					}
					if (off_cat.equals("2")) {
						
						 q="select count(app.*) from(\r\n"
							  		+ "select distinct \n"
							  		+ "fv.unit_name as cmd_unit,\n"
							  		+ "fvm.unit_name as corp_unit,\n"
							  		+ "div.unit_name as div_unit,\n"
							  		+ "bde.unit_name as bde_unit,\n"
							  		+ "orb.unit_name,\n"
							  		+ "p.unit_sus_no,\n"
							  		+ "count(bp.jco_id) FILTER (where p.category='JCO'  )as jco,\n"
							  		+ "count(bp.jco_id) FILTER (where p.category='OR'  )as ors,\n"
							  		+ "count(bp.jco_id) filter (where bp.classification_of_casuality ='physical_casuality') as physical_casuality,\n"
							  		+ "count(bp.jco_id) filter (where bp.classification_of_casuality ='battle_casuality') as battle_casuality,\n"
							  		+ "count (bp.jco_id) as total\n"
							  		+ "from  tb_psg_census_jco_or_p p \n"
							  		+ "inner join tb_psg_census_battle_physical_casuality_jco bp  on bp.jco_id= p.id \n"
							  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no \n"
							  		+ "INNER JOIN tb_psg_mstr_rank_jco rk on p.rank=rk.id\n"
							  		+ "left join all_fmn_view fv  on orb.sus_no = p.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
							  		+ "left join all_fmn_view fvm  on orb.sus_no = p.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
							  		+ "left join all_fmn_view div  on orb.sus_no = p.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
							  		+ "left join all_fmn_view bde  on orb.sus_no = p.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
							  		+ "where bp.status='1' and p.status in ('1','5') and orb.status_sus_no='Active' \n"
							  		+ ""+SearchValue
							  		+"group by 1,2,3,4,5,6\n"
							  				+ " ) app" ;
						
					}
					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL_Summary(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
							 unit_name, unit_sus_no,off_cat, from_date, to_date);
					
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {  
						total = rs.getInt(1);
					}
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
				}
				return (long) total;
			}
	
	
	public String GenerateQueryWhereClause_SQL(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no,String off_cat,String from_date,String to_date) {
	 
	
		String SearchValue ="";
		
		if (off_cat.equals("1")) {
		if(!Search.equals("")) { // for Input Filter
			SearchValue =" and ";
			SearchValue +="( upper(fv.unit_name) like ? "
					+ " or upper(fvm.unit_name) like ? "
					+ " or upper(div.unit_name) like ? "
					+ " or upper(bde.unit_name) like ? "
					+ " or upper(orb.unit_name) like ?  "
					+ " or upper(comm.unit_sus_no) like ? "
					+ " or upper(comm.personnel_no) like ? " 
					+ " or upper(rk.description) like ? "
					+ " or upper(comm.name) like ? "
					+ " or upper(bp.classification_of_casuality) like ? "
					+ " or upper(ltrim(TO_CHAR(bp.date_of_casuality,'DD-MON-YYYY'),'0')) like ? "
					+ ") ";
		}
		
		if( !cont_comd.equals("")) {
			if (SearchValue.contains("and")) {
				SearchValue += " and  orb.form_code_control like ?  ";
			}
			else {
				SearchValue += " and orb.form_code_control like ? ";
			}
		}
		if( !cont_corps.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";	
			}
			else {
				SearchValue += " and  orb.form_code_control like ? ";
			}
		}
		if( !cont_div.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";	
			}
			else {
				SearchValue += " and  orb.form_code_control like ? ";
			}
		}
		
		if( !cont_bde.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";	
			}
			else {
				SearchValue += " and  orb.form_code_control like ? ";
			}
		}
		
//		if( !unit_name.equals("")) {
//			if (SearchValue.contains("where")) {
//				SearchValue += " and  orb.unit_name = ? ";	
//			}
//			else {
//				SearchValue += " and orb.unit_name like ?";
//			}
//		}
		
		
		if( !unit_sus_no.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and orb.sus_no = ? ";	
			}
			else {
				SearchValue += " and orb.sus_no = ? ";
			}
		}
		

		if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY") && !to_date.equals("DD/MM/YYYY")){ 
			  if (SearchValue.contains("where")) {
		  SearchValue +=  " and cast(bp.date_of_casuality as date) >= cast(? as date) and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
		  } 
			else { SearchValue += " and cast(bp.date_of_casuality as date) >= cast(? as date) and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
			}
		}
		
		if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
			  if (SearchValue.contains("where")) {
		  SearchValue +=  " and cast(bp.date_of_casuality as date) >= cast(? as date)"; 
		  } 
			else { SearchValue += " and cast(bp.date_of_casuality as date) >= cast(? as date)"; 
			}
		}
		
		if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
			  if (SearchValue.contains("where")) {
		  SearchValue +=  " and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
		  } 
			else { SearchValue += " and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
			}
		}
		
	}
		
		if (off_cat.equals("2")) {
			if(!Search.equals("")) { // for Input Filter
				SearchValue =" and ";
				SearchValue +="( upper(fv.unit_name) like ? "
						+ " or upper(fvm.unit_name) like ? "
						+ " or upper(div.unit_name) like ? "
						+ " or upper(bde.unit_name) like ? "
						+ " or upper(orb.unit_name) like ?  "
						+ " or upper(p.unit_sus_no) like ? "
						+ " or upper(p.army_no) like ? " 
						+ " or upper(rk.rank) like ? "
						+ " or upper(p.full_name) like ? "
						+ " or upper(bp.classification_of_casuality) like ? "
						+ " or upper(ltrim(TO_CHAR(bp.date_of_casuality,'DD-MON-YYYY'),'0')) like ? "
						+ ") ";
			}
			
			if( !cont_comd.equals("")) {
				if (SearchValue.contains("and")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and orb.form_code_control like ? ";
				}
			}
			if( !cont_corps.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and  orb.form_code_control like ? ";
				}
			}
			if( !cont_div.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and  orb.form_code_control like ? ";
				}
			}
			
			if( !cont_bde.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and  orb.form_code_control like ? ";
				}
			}
			
//			if( !unit_name.equals("")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  orb.unit_name = ? ";	
//				}
//				else {
//					SearchValue += " and orb.unit_name like ?";
//				}
//			}
//			
			
			if( !unit_sus_no.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and orb.sus_no = ? ";	
				}
				else {
					SearchValue += " and orb.sus_no = ? ";
				}
			}
			
			if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY") && !to_date.equals("DD/MM/YYYY")){ 
				  if (SearchValue.contains("where")) {
			  SearchValue +=  " and cast(bp.date_of_casuality as date) >= cast(? as date) and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
			  } 
				else { SearchValue += " and cast(bp.date_of_casuality as date) >= cast(? as date) and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
				}
			}
			
			if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
				  if (SearchValue.contains("where")) {
			  SearchValue +=  " and cast(bp.date_of_casuality as date) >= cast(? as date)"; 
			  } 
				else { SearchValue += " and cast(bp.date_of_casuality as date) >= cast(? as date)"; 
				}
			}
			
			if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
				  if (SearchValue.contains("where")) {
			  SearchValue +=  " and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
			  } 
				else { SearchValue += " and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
				}
			}
			
			
		}
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement
			  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
				String unit_name,String unit_sus_no,String off_cat,String from_date,String to_date){
		int flag = 0;
		try {
			if(!Search.equals("")) {			
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				

				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				

				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");

				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");

			}

			if(!cont_comd.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_comd.toUpperCase()+"%");
				
			}
			if(!cont_corps.equals("0")) {
				flag += 1;
				stmt.setString(flag, cont_corps.toUpperCase()+"%");
				
			}
			if(!cont_div.equals("0")) {
				flag += 1;
				stmt.setString(flag, cont_div.toUpperCase()+"%");
				
			}
			if(!cont_bde.equals("0")) {
				flag += 1;
				stmt.setString(flag, cont_bde.toUpperCase()+"%");
				
			}
//			if(!unit_name.equals("")) {
//				flag += 1;
//				stmt.setString(flag, unit_name.toUpperCase());
//				
//			}
			if(!unit_sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_sus_no.toUpperCase());
				
			}
			if(!from_date.equals("")) {
				flag += 1;
				stmt.setString(flag, from_date);
				
			}
			if(!to_date.equals("")) {
				flag += 1;
				stmt.setString(flag, to_date);
				
			}
			if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY")  && !to_date.equals("DD/MM/YYYY")) { 
				  flag += 1; 	
				  stmt.setString(flag , from_date);
				  flag += 1;	
	              stmt.setString(flag, to_date);
			  }

		}catch (Exception e) {}
		
		return stmt;
		
	}

	  
		
	 
	 
	 
	 public String GenerateQueryWhereClause_SQL_Summary(String Search,String cont_comd,String cont_corps,
			 String cont_div,String cont_bde,String unit_name,String unit_sus_no,String off_cat
			 ,String from_date,String to_date) {
		 
		
			String SearchValue ="";
			if (off_cat.equals("1")) {
			if(!Search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				SearchValue +="( upper(fv.unit_name) like ? or upper(fvm.unit_name) like ? or upper(div.unit_name) like ? "
						+ " or upper(bde.unit_name) like ? or upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ?)";
			}
			
			if( !cont_comd.equals("")) {
				if (SearchValue.contains("and")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and orb.form_code_control like ? ";
				}
			}
			if( !cont_corps.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and  orb.form_code_control like ? ";
				}
			}
			if( !cont_div.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and  orb.form_code_control like ? ";
				}
			}
			if( !cont_bde.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and  orb.form_code_control like ? ";
				}
			}
			
//			if( !unit_name.equals("")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  orb.unit_name = ? ";	
//				}
//				else {
//					SearchValue += " and orb.unit_name like ?";
//				}
//			}
			
			
			if( !unit_sus_no.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and orb.sus_no = ? ";	
				}
				else {
					SearchValue += " and orb.sus_no = ? ";
				}
			}
			
			if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY") && !to_date.equals("DD/MM/YYYY")){ 
				  if (SearchValue.contains("where")) {
			  SearchValue +=  " and cast(bp.date_of_casuality as date) >= cast(? as date) and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
			  } 
				else { SearchValue += " and cast(bp.date_of_casuality as date) >= cast(? as date) and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
				}
			}
			
			if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
				  if (SearchValue.contains("where")) {
			  SearchValue +=  " and cast(bp.date_of_casuality as date) >= cast(? as date)"; 
			  } 
				else { SearchValue += " and cast(bp.date_of_casuality as date) >= cast(? as date)"; 
				}
			}
			
			if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
				  if (SearchValue.contains("where")) {
			  SearchValue +=  " and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
			  } 
				else { SearchValue += " and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
				}
			}

			}
			
			if (off_cat.equals("2")) {
				if(!Search.equals("")) { // for Input Filter
					SearchValue =" and  ";
					SearchValue +="( upper(fv.unit_name) like ? "
							+ " or upper(fvm.unit_name) like ? "
							+ " or upper(div.unit_name) like ? "
							+ " or upper(bde.unit_name) like ? "
							+ " or upper(orb.unit_name) like ?  "
							+ " or upper(p.unit_sus_no) like ?)";
				}
				
				if( !cont_comd.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  orb.form_code_control like ?  ";	
					}
					else {
						SearchValue += " and orb.form_code_control like ? ";
					}
				}
				if( !cont_corps.equals("0")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  orb.form_code_control like ?  ";	
					}
					else {
						SearchValue += " and  orb.form_code_control like ? ";
					}
				}
				if( !cont_div.equals("0")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  orb.form_code_control like ?  ";	
					}
					else {
						SearchValue += " and  orb.form_code_control like ? ";
					}
				}
				if( !cont_bde.equals("0")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  orb.form_code_control like ?  ";	
					}
					else {
						SearchValue += " and  orb.form_code_control like ? ";
					}
				}
				
//				if( !unit_name.equals("")) {
//					if (SearchValue.contains("where")) {
//						SearchValue += " and  orb.unit_name = ? ";	
//					}
//					else {
//						SearchValue += " and orb.unit_name like ?";
//					}
//				}
				
				
				if( !unit_sus_no.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and orb.sus_no = ? ";	
					}
					else {
						SearchValue += " and orb.sus_no = ? ";
					}
				}
				
				if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY") && !to_date.equals("DD/MM/YYYY")){ 
					  if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(bp.date_of_casuality as date) >= cast(? as date) and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
				  } 
					else { SearchValue += " and cast(bp.date_of_casuality as date) >= cast(? as date) and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
					}
				}
				
				if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
					  if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(bp.date_of_casuality as date) >= cast(? as date)"; 
				  } 
					else { SearchValue += " and cast(bp.date_of_casuality as date) >= cast(? as date)"; 
					}
				}
				
				if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
					  if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
				  } 
					else { SearchValue += " and cast(bp.date_of_casuality as date) <= cast(? as date)"; 
					}
				}

				}
			
			return SearchValue;
		}
		
		public PreparedStatement setQueryWhereClause_SQL_Summary(PreparedStatement
				  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String off_cat
					,String from_date,String to_date) {
			int flag = 0;
			try {
				if(!Search.equals("")) {			
					
					flag += 1;
					stmt.setString(flag, Search.toUpperCase()+"%");
					
					
					flag += 1;
					stmt.setString(flag, Search.toUpperCase()+"%");
					

					flag += 1;
					stmt.setString(flag, Search.toUpperCase()+"%");
					
					
					flag += 1;
					stmt.setString(flag, Search.toUpperCase()+"%");
					
					
					flag += 1;
					stmt.setString(flag, Search.toUpperCase()+"%");
					
					
					flag += 1;
					stmt.setString(flag, Search.toUpperCase()+"%");
					
					

				}

				if(!cont_comd.equals("")) {
					flag += 1;
					stmt.setString(flag, cont_comd.toUpperCase()+"%");
					
				}
				if(!cont_corps.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_corps.toUpperCase()+"%");
					
				}
				if(!cont_div.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_div.toUpperCase()+"%");
					
				}
				if(!cont_bde.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_bde.toUpperCase()+"%");
					
				}
//				if(!unit_name.equals("")) {
//					flag += 1;
//					stmt.setString(flag, unit_name.toUpperCase());
//					
//				}
				if(!unit_sus_no.equals("")) {
					flag += 1;
					stmt.setString(flag, unit_sus_no.toUpperCase());
					
				}
				if(!from_date.equals("")) {
					flag += 1;
					stmt.setString(flag, from_date);
					
				}
				if(!to_date.equals("")) {
					flag += 1;
					stmt.setString(flag, to_date);
					
				}
				if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY")  && !to_date.equals("DD/MM/YYYY")) { 
					  flag += 1; 	
					   stmt.setString(flag , from_date);
					   flag += 1;	
		                stmt.setString(flag, to_date);
		                
				  }
				

			}catch (Exception e) {}
			
			return stmt;
			
		}

	 }
