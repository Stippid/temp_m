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

public class Married_Pers_Without_ChildDAOImpl implements Married_Pers_Without_ChildDAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public List<Map<String, Object>> getmarried_pers_without_child_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde, 
			String unit_name, String unit_sus_no, String posn_date, String typeID, String rankID, String rankjcoID) 
	{
	 String SearchValue = GenerateQueryWhereClause_SQL(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no,posn_date, typeID, rankID, rankjcoID); 
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
			q="select distinct orb.unit_name, comm.unit_sus_no,\r\n"
					+ "comm.personnel_no,rk.description,comm.name,\r\n"
					+ "date_part('year', age(comm.date_of_birth)) as age,\r\n"
					+ "ltrim(TO_CHAR(ch.marriage_date,'DD-MON-YYYY'),'0') as mar_date\r\n"
					+ "from tb_psg_trans_proposed_comm_letter comm\r\n"
					+ "inner join tb_psg_census_family_married ch  on comm.id = ch.comm_id \r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id \r\n"
					+ "left join tb_psg_census_detail_p cen on cen.comm_id=comm.id \r\n"
					+ " where ch.status in ('1','2') and comm.status in ('1','5') and cen.status='1'\r\n"
					+ "and cen.marital_status='8' and ch.status='1' and orb.status_sus_no='Active' \r\n"
					+ "and comm.id not in(select comm_id from tb_psg_census_children)\r\n"
					+ SearchValue+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			if (typeID.equals("2")) {
				q="select distinct orb.unit_name,\r\n" + 
						"            jco.unit_sus_no,\r\n" + 
						"jco.army_no,rk.rank,jco.full_name,\r\n" + 
						"date_part('year', age(jco.date_of_birth)) as age,\r\n" + 
						"ltrim(TO_CHAR(ch.marriage_date,'DD-MON-YYYY'),'0') as mar_date\r\n" + 
						"from tb_psg_census_jco_or_p jco\r\n" + 
						"inner join tb_psg_census_family_married_jco ch  on ch.jco_id=jco.id  \r\n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = jco.unit_sus_no \r\n" + 
						"INNER JOIN tb_psg_mstr_rank_jco rk on jco.rank=rk.id \r\n" + 
						" where ch.status in ('1','2') and jco.status in ('1','5') and ch.status='1' and orb.status_sus_no='Active' " +SearchValue+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			stmt=conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt,search, cont_comd, cont_corps,cont_div, cont_bde,unit_name, unit_sus_no, 
					/*user_role_id, */posn_date, typeID, rankID, rankjcoID);
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
	
	
	public long getmarried_pers_without_child_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,
			String cont_comd, String cont_corps, String cont_div, String cont_bde,String unit_name,String unit_sus_no, String posn_date, String typeID, String rankID, String rankjcoID) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps,cont_div, cont_bde, unit_name, unit_sus_no, 
				 posn_date, typeID, rankID, rankjcoID); 
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				if (typeID.equals("1")) {
			  q="select count(app.*) from(select distinct orb.unit_name, comm.unit_sus_no,\r\n"
			  		+ "comm.personnel_no,rk.description,comm.name,\r\n"
			  		+ "date_part('year', age(comm.date_of_birth)) as age,\r\n"
			  		+ "ltrim(TO_CHAR(ch.marriage_date,'DD-MON-YYYY'),'0') as mar_date\r\n"
			  		+ "from tb_psg_trans_proposed_comm_letter comm\r\n"
			  		+ "inner join tb_psg_census_family_married ch  on comm.id = ch.comm_id \r\n"
			  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
			  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id \r\n"
			  		+ "left join tb_psg_census_detail_p cen on cen.comm_id=comm.id \r\n"
			  		+ " where ch.status in ('1','2') and comm.status in ('1','5') and cen.status='1'\r\n"
			  		+ "and cen.marital_status='8' and ch.status='1' and orb.status_sus_no='Active' \r\n"
			  		+ "and comm.id not in(select comm_id from tb_psg_census_children)\r\n "+SearchValue+" ) app" ;
				}
				if (typeID.equals("2")) {
					q="select count(app.*) from(select distinct orb.unit_name,\r\n" + 
							"            jco.unit_sus_no,\r\n" + 
							"jco.army_no,rk.rank,jco.full_name,\r\n" + 
							"date_part('year', age(jco.date_of_birth)) as age,\r\n" + 
							"ltrim(TO_CHAR(ch.marriage_date,'DD-MON-YYYY'),'0') as mar_date\r\n" + 
							"from tb_psg_census_jco_or_p jco\r\n" + 
							"inner join tb_psg_census_family_married_jco ch  on ch.jco_id=jco.id  \r\n" + 
							"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = jco.unit_sus_no \r\n" + 
							"INNER JOIN tb_psg_mstr_rank_jco rk on jco.rank=rk.id \r\n" + 
							" where ch.status in ('1','2') and jco.status in ('1','5') and ch.status='1' and orb.status_sus_no='Active' \r\n" + 
					  		SearchValue+" ) app" ;
				}
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name, unit_sus_no, 
						 posn_date, typeID, rankID, rankjcoID);
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
 
 
 
 public List<Map<String, Object>> getmarried_pers_without_child_tablesum(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String posn_date, String typeID, String rankID, String rankjcoID) 
	{
	 String SearchValue = GenerateQueryWhereClause_SQLsum(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no, posn_date, typeID, rankID, rankjcoID); 
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
					+ "from tb_psg_trans_proposed_comm_letter comm\r\n"
					+ "inner join tb_psg_census_family_married ch  on comm.id = ch.comm_id\r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id \r\n"
					+ "left join tb_psg_census_detail_p cen on cen.comm_id=comm.id and cen.status='1' \r\n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n"
					+ "  where ch.status in ('1','2') and comm.status in ('1','5') and cen.marital_status='8' and ch.status='1' and orb.status_sus_no='Active' \r\n"
					+ "and ch.cen_id not in(select cen_id from tb_psg_census_children)\r\n " 
					+SearchValue+" group by 1,2,3,4,5,6  " +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			if (typeID.equals("2")) {
				q=" select distinct \r\n" + 
						"fv.unit_name as cmd_unit,\r\n" + 
						"                fvm.unit_name as corp_unit,\r\n" + 
						"                div.unit_name as div_unit,\r\n" + 
						"                bde.unit_name as bde_unit,\r\n" + 
						"                orb.unit_name,\r\n" + 
						"                jco.unit_sus_no,\r\n" + 
						"count(ch.jco_id)  FILTER (where jco.category='JCO') as  jco,\r\n" + 
						"count(ch.jco_id)  FILTER (where jco.category='OR') as  ors,count(ch.jco_id) as total\r\n" + 
						"from tb_psg_census_jco_or_p jco\r\n" + 
						"inner join tb_psg_census_family_married_jco ch  on ch.jco_id=jco.id  \r\n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = jco.unit_sus_no \r\n" + 
						"INNER JOIN tb_psg_mstr_rank_jco rk on jco.rank=rk.id \r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = jco.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = jco.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
						"left join all_fmn_view div  on orb.sus_no = jco.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = jco.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
						"  where ch.status in ('1','2') and jco.status in ('1','5') and ch.status='1' and orb.status_sus_no='Active' \r\n"
						+SearchValue+" group by 1,2,3,4,5,6  " +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQLsum(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, posn_date, typeID, rankID, rankjcoID);
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

 
 public long getmarried_pers_without_child_tablecountsum(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String posn_date, String typeID, String rankID, String rankjcoID) {
	 String SearchValue = GenerateQueryWhereClause_SQLsum(Search, cont_comd, cont_corps,cont_div, cont_bde,
				 unit_name, unit_sus_no,posn_date, typeID, rankID, rankjcoID); 
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
			  		+ "from tb_psg_trans_proposed_comm_letter comm\r\n"
			  		+ "inner join tb_psg_census_family_married ch  on comm.id = ch.comm_id\r\n"
			  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
			  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id \r\n"
			  		+ "left join tb_psg_census_detail_p cen on cen.comm_id=comm.id and cen.status='1' \r\n"
			  		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
			  		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n"
			  		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n"
			  		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n"
			  		+ "  where ch.status in ('1','2') and comm.status in ('1','5') and cen.marital_status='8' and ch.status='1' and orb.status_sus_no='Active' \r\n"
			  		+ "and ch.cen_id not in(select cen_id from tb_psg_census_children)\r\n "+SearchValue+"  group by 1,2,3,4,5,6 \r\n"
			  		+  ") app";

				}
				if (typeID.equals("2")) {
					 q="select count(app.*) from(select distinct \r\n" + 
					 		"fv.unit_name as cmd_unit,\r\n" + 
					 		"                fvm.unit_name as corp_unit,\r\n" + 
					 		"                div.unit_name as div_unit,\r\n" + 
					 		"                bde.unit_name as bde_unit,\r\n" + 
					 		"                orb.unit_name,\r\n" + 
					 		"                jco.unit_sus_no,\r\n" + 
					 		"count(ch.jco_id)  FILTER (where jco.category='JCO') as  jco,\r\n" + 
					 		"count(ch.jco_id)  FILTER (where jco.category='OR') as  ors,count(ch.jco_id) as total\r\n" + 
					 		"from tb_psg_census_jco_or_p jco\r\n" + 
					 		"inner join tb_psg_census_family_married_jco ch  on ch.jco_id=jco.id  \r\n" + 
					 		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = jco.unit_sus_no \r\n" + 
					 		"INNER JOIN tb_psg_mstr_rank_jco rk on jco.rank=rk.id \r\n" + 
					 		"left join all_fmn_view fv  on orb.sus_no = jco.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					 		"left join all_fmn_view fvm  on orb.sus_no = jco.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					 		"left join all_fmn_view div  on orb.sus_no = jco.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					 		"left join all_fmn_view bde  on orb.sus_no = jco.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
					 		"  where ch.status in ('1','2') and jco.status in ('1','5') and ch.status='1' and orb.status_sus_no='Active' \r\n" +
						  	SearchValue+" group by 1,2,3,4,5,6 ) app" ;
				}
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQLsum(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, posn_date, typeID, rankID, rankjcoID);
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
			String unit_name,String unit_sus_no,/*String user_role_id,*/ String posn_date,  String typeID, String rankID, String rankjcoID) {
	 
	
		String SearchValue ="";
		
		if (typeID.equals("1")) {
		if(!Search.equals("")) { // for Input Filter
			SearchValue ="and";
			SearchValue +="( upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ? or upper(comm.personnel_no) like ?"
					+ " or upper(rk.description) like ?  or upper(comm.name) like ?"
					+ " or date_part('year', age(comm.date_of_birth))::text like ? or ltrim(TO_CHAR(ch.marriage_date,'DD-MON-YYYY'),'0')::text like ?) ";
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
		
	
//		if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
//			if (SearchValue.contains("where")) {
//				  SearchValue +=  " and cast(ch.created_date as date) <= cast(? as date)"; 
//			  } 
//			else { 
//				  SearchValue += " and cast(ch.created_date as date) <= cast(? as date)"; 
//			  }
//		}
		
		if( !rankID.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and rk.id::text = ? ";	
			}
			else {
				SearchValue += " and rk.id::text = ? ";
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
				SearchValue +="( upper(orb.unit_name) like ?  or upper(jco.unit_sus_no) like ? "
						+ " or upper(jco.army_no) like ? or upper(rk.rank) like ? or upper(jco.full_name) like ? "
						+ "or date_part('year', age(jco.date_of_birth))::text like ? or ltrim(TO_CHAR(ch.marriage_date,'DD-MON-YYYY'),'0')::text like ?) ";
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
			
	
			
//			if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
//				if (SearchValue.contains("where")) {
//					  SearchValue +=  " and cast(ch.created_date as date) <= cast(? as date)"; 
//				  } 
//				else { 
//					  SearchValue += " and cast(ch.created_date as date) <= cast(? as date)"; 
//				  }
//			}
			
			if( !rankjcoID.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and rk.id::text = ? ";	
				}
				else {
					SearchValue += " and rk.id::text = ? ";
				}
			}
		}
		
		String prf_whr = "";
		String prf_whr1 = ""; 
		
		return SearchValue;
	}
	
	
	
	
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement
			  stmt,String Search, String cont_comd, String cont_corps, String cont_div, String cont_bde,String unit_name,String unit_sus_no, 
			  String posn_date, String typeID, String rankID, String rankjcoID) {
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
//			if(!posn_date.equals("")) {
//				flag += 1;
//				stmt.setString(flag, posn_date);
//				
//			}
			
			if(!rankID.equals("0")) {
				flag += 1;
				stmt.setString(flag, rankID.toUpperCase());
				
			}
			
			if(!rankjcoID.equals("0")) {
				flag += 1;
				stmt.setString(flag, rankjcoID.toUpperCase());
			}
			/*if(!user_role_id.equals("")) {
				flag += 1;
				stmt.setString(flag, user_role_id);
				
			}*/
			

		}catch (Exception e) {}
		
		return stmt;
		
	}
	
	
	
	
	public String GenerateQueryWhereClause_SQLsum(String Search, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name,String unit_sus_no,/*String user_role_id,*/ String posn_date, String typeID, String rankID, String rankjcoID) {
	 
	
		String SearchValue ="";
		
		if (typeID.equals("1")) {
		if(!Search.equals("")) { // for Input Filter
			SearchValue ="and";
			SearchValue +="( upper(fv.unit_name) like ?  or upper(fvm.unit_name) like ?  or upper(div.unit_name) like ? or "
					+ " upper(bde.unit_name) like ?  or upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ?) ";
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
		
		
		
//		if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
//			if (SearchValue.contains("where")) {
//				  SearchValue +=  " and cast(ch.created_date as date) <= cast(? as date)"; 
//			  } 
//			else { 
//				  SearchValue += " and cast(ch.created_date as date) <= cast(? as date)"; 
//			  }
//		}
		
		if( !rankID.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and rk.id::text = ? ";	
			}
			else {
				SearchValue += " and rk.id::text = ? ";
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
						+ " or upper(bde.unit_name) like ? or upper(orb.unit_name) like ?  or upper(jco.unit_sus_no) like ? ) ";
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
			
			
			
//			if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
//				if (SearchValue.contains("where")) {
//					  SearchValue +=  " and cast(ch.created_date as date) <= cast(? as date)"; 
//				  } 
//				else { 
//					  SearchValue += " and cast(ch.created_date as date) <= cast(? as date)"; 
//				  }
//			}
			
			if( !rankjcoID.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and rk.id::text = ? ";	
				}
				else {
					SearchValue += " and rk.id::text = ? ";
				}
			}
		}
		
		String prf_whr = "";
		String prf_whr1 = ""; 
		
		return SearchValue;
	}
	
	
	
	
	
	public PreparedStatement setQueryWhereClause_SQLsum(PreparedStatement
			  stmt,String Search, String cont_comd, String cont_corps, String cont_div, String cont_bde, String unit_name, String unit_sus_no, 
			 String posn_date, String typeID, String rankID, String rankjcoID) {
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
//			if(!unit_name.equals("")) {
//				flag += 1;
//				stmt.setString(flag, unit_name.toUpperCase());
//				
//			}
			if(!unit_sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_sus_no.toUpperCase());
				
			}
//			if(!posn_date.equals("")) {
//				flag += 1;
//				stmt.setString(flag, posn_date);
//				
//			}
			
			if(!rankID.equals("0")) {
				flag += 1;
				stmt.setString(flag, rankID.toUpperCase());
				
			}
			
			if(!rankjcoID.equals("0")) {
				flag += 1;
				stmt.setString(flag, rankjcoID.toUpperCase());
			}
			/*if(!user_role_id.equals("")) {
				flag += 1;
				stmt.setString(flag, user_role_id);
				
			}*/
			

		}catch (Exception e) {}
		
		return stmt;
		
	}
	
}
