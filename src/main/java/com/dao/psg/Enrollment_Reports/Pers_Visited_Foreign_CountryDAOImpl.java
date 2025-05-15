package com.dao.psg.Enrollment_Reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class Pers_Visited_Foreign_CountryDAOImpl implements Pers_Visited_Foreign_CountryDAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public List<Map<String, Object>> getpers_foreign_visited_country_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde,String unit_name, String unit_sus_no, String from_date, String to_date, String typeID) 
	{
	 String SearchValue = GenerateQueryWhereClause_SQL(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no, from_date, to_date, typeID); 
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";


	try{	
		String pageL = "";
        
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if (typeID.equals("1")) {
			q="select   orb.unit_name, comm.unit_sus_no,\r\n"
					+ "comm.personnel_no,rk.description,comm.name,ms.visit_purpose as type_of_un_miss,ltrim(TO_CHAR(ch.from_dt,'DD-MON-YYYY'),'0') as from_dt,\r\n"
					+ "ltrim(TO_CHAR(ch.to_dt,'DD-MON-YYYY'),'0') as to_dt\r\n"
					+ "from tb_psg_trans_proposed_comm_letter comm\r\n"
					+ "inner join tb_psg_census_foreign_country ch   on comm.id=ch.comm_id\r\n"
					+ "inner join tb_psg_mstr_purposeof_visit ms on ms.id= ch.purpose_visit\r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id \r\n"
					+ "left join tb_psg_census_detail_p cen on cen.comm_id=comm.id and cen.status='1'\r\n"
					+ "where ch.purpose_visit !='0' and ch.status in (1,2) and comm.status in ('1','5') and orb.status_sus_no='Active'\r\n"
					+ SearchValue+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			if (typeID.equals("2")) {
				q="select  orb.unit_name,\r\n" + 
						"            comm.unit_sus_no,\r\n" + 
						"comm.army_no,rk.rank,comm.full_name,ms.visit_purpose as type_of_un_miss,ltrim(TO_CHAR(ch.from_dt,'DD-MON-YYYY'),'0') as from_dt,\r\n" + 
						"ltrim(TO_CHAR(ch.to_dt,'DD-MON-YYYY'),'0') as to_dt\r\n" + 
						"from tb_psg_census_jco_or_p comm\r\n" + 
						"inner join tb_psg_census_foreign_country_jco ch  on ch.jco_id=comm.id\r\n" + 
						"inner join tb_psg_mstr_purposeof_visit ms on ms.id= ch.purpose_visit\r\n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n" + 
						"INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id \r\n" + 
						" where ch.purpose_visit !='0' and ch.status in (1,2) and comm.status in ('1','5') and orb.status_sus_no='Active' \r\n" + 
						 SearchValue +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,search, cont_comd, cont_corps,cont_div, cont_bde,unit_name, unit_sus_no, /*user_role_id, */from_date, to_date, typeID);
			ResultSet rs = stmt.executeQuery();   
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      
	  	while (rs.next()) {
	  		
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
			
			list.add(columns);
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
	
	
	public long getpers_foreign_visited_country_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,
			String cont_comd, String cont_corps, String cont_div, String cont_bde,String unit_name,String unit_sus_no, String from_date, String to_date, String typeID) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps,cont_div, cont_bde,unit_name, unit_sus_no, from_date, to_date, typeID); 
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				if (typeID.equals("1")) {
			  q="select count(app.*) from(select   orb.unit_name, comm.unit_sus_no,\r\n"
			  		+ "comm.personnel_no,rk.description,comm.name,ms.visit_purpose as type_of_un_miss,ltrim(TO_CHAR(ch.from_dt,'DD-MON-YYYY'),'0') as from_dt,\r\n"
			  		+ "ltrim(TO_CHAR(ch.to_dt,'DD-MON-YYYY'),'0') as to_dt\r\n"
			  		+ "from tb_psg_trans_proposed_comm_letter comm\r\n"
			  		+ "inner join tb_psg_census_foreign_country ch   on comm.id=ch.comm_id\r\n"
			  		+ "inner join tb_psg_mstr_purposeof_visit ms on ms.id= ch.purpose_visit\r\n"
			  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
			  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id \r\n"
			  		+ "left join tb_psg_census_detail_p cen on cen.comm_id=comm.id and cen.status='1'\r\n"
			  		+ "where ch.purpose_visit !='0' and ch.status in (1,2) and comm.status in ('1','5') and orb.status_sus_no='Active'\n "
			  		+SearchValue+" ) app" ;
				}
				if (typeID.equals("2")) {
					q="select count(app.*) from(select  orb.unit_name,\r\n" + 
							"            comm.unit_sus_no,\r\n" + 
							"comm.army_no,rk.rank,comm.full_name,ms.visit_purpose as type_of_un_miss,ltrim(TO_CHAR(ch.from_dt,'DD-MON-YYYY'),'0') as from_dt,\r\n" + 
							"ltrim(TO_CHAR(ch.to_dt,'DD-MON-YYYY'),'0') as to_dt\r\n" + 
							"from tb_psg_census_jco_or_p comm\r\n" + 
							"inner join tb_psg_census_foreign_country_jco ch  on ch.jco_id=comm.id\r\n" + 
							"inner join tb_psg_mstr_purposeof_visit ms on ms.id= ch.purpose_visit\r\n" + 
							"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n" + 
							"INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id \r\n" + 
							" where ch.purpose_visit !='0' and ch.status in (1,2) and comm.status in ('1','5') and orb.status_sus_no='Active' \r\n" + 
					  		SearchValue+" ) app" ;
				}
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,unit_name, unit_sus_no, from_date, to_date, typeID);
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
 
 
 
 public List<Map<String, Object>> getpers_foreign_visited_country_tablesum(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String from_date, String to_date, String typeID) 
	{
	 String SearchValue = GenerateQueryWhereClause_SQLsum(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no, from_date, to_date, typeID); 
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";


	try{	
		String pageL = "";
        
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if (typeID.equals("1")) {
			q=" select distinct \r\n"
					+ "fv.unit_name as cmd_unit,\r\n"
					+ "                fvm.unit_name as corp_unit,\r\n"
					+ "                div.unit_name as div_unit,\r\n"
					+ "                bde.unit_name as bde_unit,\r\n"
					+ "                orb.unit_name,\r\n"
					+ "                comm.unit_sus_no,\r\n"
					+ "count(ch.comm_id)  as officer,\r\n"
					+ "count(ch.comm_id) as total\r\n"
					+ " from tb_psg_trans_proposed_comm_letter comm\r\n"
					+ "inner join tb_psg_census_foreign_country ch  on comm.id=ch.comm_id\r\n"
					+ "inner join tb_psg_mstr_purposeof_visit ms on ms.id= ch.purpose_visit\r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id \r\n"
					+ "left join tb_psg_census_detail_p cen on cen.comm_id=comm.id and cen.status='1' \r\n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n"
					+ "  where ch.purpose_visit !='0' and ch.status in (1,2) and comm.status in ('1','5') and orb.status_sus_no='Active'  \r\n " 
					+SearchValue+" group by 1,2,3,4,5,6 "+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			if (typeID.equals("2")) {
				q=" select distinct \r\n" + 
						"fv.unit_name as cmd_unit,\r\n" + 
						"                fvm.unit_name as corp_unit,\r\n" + 
						"                div.unit_name as div_unit,\r\n" + 
						"                bde.unit_name as bde_unit,\r\n" + 
						"                orb.unit_name,\r\n" + 
						"                comm.unit_sus_no,\r\n" + 
						"count(ch.jco_id)  FILTER (where comm.category='JCO') as  jco,\r\n" + 
						"count(ch.jco_id)  FILTER (where comm.category='OR') as  ors," +
						"count(ch.jco_id) as total\r\n" + 
						" from tb_psg_census_jco_or_p comm\r\n" + 
						"inner join tb_psg_census_foreign_country_jco ch  on ch.jco_id=comm.id\r\n" + 
						"inner join tb_psg_mstr_purposeof_visit ms on ms.id= ch.purpose_visit\r\n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n" + 
						"INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id \r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
						"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
						"  where ch.purpose_visit !='0' and ch.status in (1,2) and comm.status in ('1','5') and orb.status_sus_no='Active' \r\n"
						+SearchValue+" group by 1,2,3,4,5,6  " +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQLsum(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, from_date, to_date, typeID);
	      ResultSet rs = stmt.executeQuery();
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      
	  	while (rs.next()) {
	  		
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
			
			list.add(columns);
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

 
 public long getpers_foreign_visited_country_tablecountsum(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String from_date, String to_date, String typeID) {
	 String SearchValue = GenerateQueryWhereClause_SQLsum(Search, cont_comd, cont_corps,cont_div, cont_bde,
				 unit_name, unit_sus_no, from_date, to_date, typeID); 
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				if (typeID.equals("1")) {
			  q="select count(app.*) from(select distinct \r\n"
			  		+ "fv.unit_name as cmd_unit,\r\n"
			  		+ "                fvm.unit_name as corp_unit,\r\n"
			  		+ "                div.unit_name as div_unit,\r\n"
			  		+ "                bde.unit_name as bde_unit,\r\n"
			  		+ "                orb.unit_name,\r\n"
			  		+ "                comm.unit_sus_no,\r\n"
			  		+ "count(ch.comm_id)  as officer,\r\n"
			  		+ "count(ch.comm_id) as total\r\n"
			  		+ " from tb_psg_trans_proposed_comm_letter comm\r\n"
			  		+ "inner join tb_psg_census_foreign_country ch  on comm.id=ch.comm_id\r\n"
			  		+ "inner join tb_psg_mstr_purposeof_visit ms on ms.id= ch.purpose_visit\r\n"
			  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
			  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id \r\n"
			  		+ "left join tb_psg_census_detail_p cen on cen.comm_id=comm.id and cen.status='1' \r\n"
			  		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
			  		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n"
			  		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n"
			  		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n"
			  		+ "  where ch.purpose_visit !='0' and ch.status in (1,2) and comm.status in ('1','5') and orb.status_sus_no='Active' "+SearchValue+"  group by 1,2,3,4,5,6 \r\n"
			  		+  ") app";

				}
				if (typeID.equals("2")) {
					 q="select count(app.*) from(select distinct \r\n" + 
					 		"fv.unit_name as cmd_unit,\r\n" + 
					 		"                fvm.unit_name as corp_unit,\r\n" + 
					 		"                div.unit_name as div_unit,\r\n" + 
					 		"                bde.unit_name as bde_unit,\r\n" + 
					 		"                orb.unit_name,\r\n" + 
					 		"                comm.unit_sus_no,\r\n" + 
					 		"count(ch.jco_id)  FILTER (where comm.category='JCO') as  jco,\r\n" + 
					 		"count(ch.jco_id)  FILTER (where comm.category='OR') as  ors," +
					 		"count(ch.jco_id) as total\r\n" + 
					 		" from tb_psg_census_jco_or_p comm\r\n" + 
					 		"inner join tb_psg_census_foreign_country_jco ch  on ch.jco_id=comm.id\r\n" + 
					 		"inner join tb_psg_mstr_purposeof_visit ms on ms.id= ch.purpose_visit\r\n" + 
					 		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n" + 
					 		"INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id \r\n" + 
					 		"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					 		"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					 		"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					 		"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
					 		"  where ch.purpose_visit !='0' and ch.status in (1,2) and comm.status in ('1','5') and orb.status_sus_no='Active' \r\n" +
						  	SearchValue+" group by 1,2,3,4,5,6 ) app" ;
				}
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQLsum(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, from_date, to_date, typeID);
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
	
 
 public String GenerateQueryWhereClause_SQL(String Search, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name,String unit_sus_no,/*String user_role_id,*/String from_date, String to_date, String typeID) {
	 
	
		String SearchValue ="";
		
		if (typeID.equals("1")) {
		if(!Search.equals("")) { // for Input Filter
			SearchValue ="and";
			SearchValue +="( upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ? or upper(comm.personnel_no) like ?"
					+ " or upper(rk.description) like ?  or upper(comm.name) like ? or upper(ms.visit_purpose) like ? "
					+ " or upper(ltrim(TO_CHAR(ch.from_dt,'DD-MON-YYYY'),'0')) like ? "
					+ " or upper(ltrim(TO_CHAR(ch.to_dt,'DD-MON-YYYY'),'0')) like ? ) ";
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
				  SearchValue +=  " and cast(ch.from_dt as date) >= cast(? as date) and cast(ch.to_dt as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(ch.from_dt as date) >= cast(? as date) and cast(ch.to_dt as date) <= cast(? as date)"; 
			  }
		}
		
		if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(ch.from_dt as date) >= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(ch.from_dt as date) >= cast(? as date)"; 
			  }
		}
		if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(ch.to_dt as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(ch.to_dt as date) <= cast(? as date)"; 
			  }
		}
		/*
		 * if( !to_date.equals("")) { if (SearchValue.contains("where")) { SearchValue
		 * +=
		 * " and cast(p.created_date as date) >= cast(? as date) and cast(p.created_date as date) <= cast(? as date)"
		 * ; } else { SearchValue +=
		 * " where cast(p.created_date as date) >= cast(? as date) and cast(p.created_date as date) <= cast(? as date)"
		 * ; }
		 * 
		 * }
		 */
		/*if( !user_role_id.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and cast(lm.userId as character varying) = ? ";	
			}
			else {
				SearchValue += " where cast(lm.userId as character varying) = ? ";
			}
		}*/
		}
		if (typeID.equals("2")) {
			if(!Search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				SearchValue +="( upper(orb.unit_name) like ? or upper(orb.unit_name) like ? or upper(orb.unit_name) like ? "
						+ " or upper(orb.unit_name) like ? or upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ? "
						+ " or upper(comm.army_no) like ? or upper(rk.rank) like ? or upper(comm.full_name) like ? or upper(ms.visit_purpose) like ? "
						+ " or upper(ltrim(TO_CHAR(ch.from_dt,'DD-MON-YYYY'),'0')) like ?"
						+ " or upper(ltrim(TO_CHAR(ch.to_dt,'DD-MON-YYYY'),'0')) like ?) ";
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
					  SearchValue +=  " and cast(ch.from_dt as date) >= cast(? as date) and cast(ch.to_dt as date) <= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(ch.from_dt as date) >= cast(? as date) and cast(ch.to_dt as date) <= cast(? as date)"; 
					}
			}
			
			if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(ch.from_dt as date) >= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(ch.from_dt as date) >= cast(? as date)"; 
					}
			}
			
			if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(ch.to_dt as date) <= cast(? as date)"; 
				  }
				else { SearchValue += "  and cast(ch.to_dt as date) <= cast(? as date)"; 
					}
			}
		}
		
		String prf_whr = "";
		String prf_whr1 = ""; 
		
		return SearchValue;
	}
	
	
	
	
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement
			  stmt,String Search, String cont_comd, String cont_corps, String cont_div, String cont_bde,String unit_name,String unit_sus_no, String from_date, String to_date, String typeID) {
		int flag = 0;
		try {
			if(!Search.equals("")) {			
				
				if (typeID.equals("1")) {
				
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
				if (typeID.equals("2")) {
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
					
					
					flag += 1;
					stmt.setString(flag, Search.toUpperCase()+"%");
				}
				
				
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
			/*if(!user_role_id.equals("")) {
				flag += 1;
				stmt.setString(flag, user_role_id);
				
			}*/
			

		}catch (Exception e) {}
		
		return stmt;
		
	}
	
	
	
	
	public String GenerateQueryWhereClause_SQLsum(String Search, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name,String unit_sus_no,/*String user_role_id,*/String from_date, String to_date, String typeID) {
	 
	
		String SearchValue ="";
		
		if (typeID.equals("1")) {
		if(!Search.equals("")) { // for Input Filter
			SearchValue ="and";
			SearchValue +="( upper(fv.unit_name) like ?  or upper(fvm.unit_name) like ?  or upper(div.unit_name) like ? or "
					+ " upper(bde.unit_name) like ?  or upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ?	) ";
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
//		
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
				  SearchValue +=  " and cast(ch.from_dt as date) >= cast(? as date) and cast(ch.to_dt as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(ch.from_dt as date) >= cast(? as date) and cast(ch.to_dt as date) <= cast(? as date)"; 
			  }
		}
		
		if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(ch.from_dt as date) >= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(ch.from_dt as date) >= cast(? as date)"; 
			  }
		}
		
		if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(ch.to_dt as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(ch.to_dt as date) <= cast(? as date)"; 
			  }
		}
		/*
		 * if( !to_date.equals("")) { if (SearchValue.contains("where")) { SearchValue
		 * +=
		 * " and cast(p.created_date as date) >= cast(? as date) and cast(p.created_date as date) <= cast(? as date)"
		 * ; } else { SearchValue +=
		 * " where cast(p.created_date as date) >= cast(? as date) and cast(p.created_date as date) <= cast(? as date)"
		 * ; }
		 * 
		 * }
		 */
		/*if( !user_role_id.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and cast(lm.userId as character varying) = ? ";	
			}
			else {
				SearchValue += " where cast(lm.userId as character varying) = ? ";
			}
		}*/
		}
		if (typeID.equals("2")) {
			if(!Search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				SearchValue +="( upper(fv.unit_name) like ? or upper(fvm.unit_name) like ? or upper(div.unit_name) like ? "
						+ " or upper(bde.unit_name) like ? or upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ? "
						+ " or upper(ltrim(TO_CHAR(ch.from_dt,'DD-MON-YYYY'),'0')) like ?"
						+ " or upper(ltrim(TO_CHAR(ch.to_dt,'DD-MON-YYYY'),'0')) like ?) ";
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
					  SearchValue +=  " and cast(ch.from_dt as date) >= cast(? as date) and cast(ch.to_dt as date) <= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(ch.from_dt as date) >= cast(? as date) and cast(ch.to_dt as date) <= cast(? as date)"; 
					}
			}
			
			if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(ch.from_dt as date) >= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(ch.from_dt as date) >= cast(? as date)"; 
					}
			}
			
			if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(ch.to_dt as date) <= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(ch.to_dt as date) <= cast(? as date)"; 
					}
			}
		}
		
		String prf_whr = "";
		String prf_whr1 = ""; 
		
		return SearchValue;
	}
	
	
	
	
	
	public PreparedStatement setQueryWhereClause_SQLsum(PreparedStatement
			  stmt,String Search, String cont_comd, String cont_corps, String cont_div, String cont_bde,String unit_name,String unit_sus_no, String from_date, String to_date, String typeID) {
		int flag = 0;
		try {
			if(!Search.equals("")) {			
				
				if (typeID.equals("1")) {

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
				if (typeID.equals("2")) {
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
			/*if(!user_role_id.equals("")) {
				flag += 1;
				stmt.setString(flag, user_role_id);
				
			}*/
			

		}catch (Exception e) {}
		
		return stmt;
		
	}
	
	
}

