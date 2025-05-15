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

public class Age_Wise_DtlsDAOImpl implements Age_Wise_DtlsDAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public List<Map<String, Object>> getage_wise_dtls_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde,String unit_name, String unit_sus_no, String from_date, String to_date, String typeID, String posn_date, String servingID) 
	{
	 String SearchValue = GenerateQueryWhereClause_SQL(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no, from_date, to_date, typeID, posn_date);
	 String SearchValue1 = GenerateQueryWhereClause_SQLServing(search, servingID);
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
				q= "select  ser.unit_name,ser.unit_sus_no,ser.personnel_no,\r\n" + 
						"ser.description,ser.name,serving from (select distinct orb.unit_name,\r\n" + 
						"comm.unit_sus_no,comm.personnel_no,\r\n" + 
						"rk.description,comm.name,comm.status,comm.type_of_comm_granted,rk.id,\r\n" + 
						"fv.form_code_control as Command, fvm.form_code_control as Corps, div.form_code_control as Division,\r\n" + 
						"bde.form_code_control as Brigade,orb.sus_no,\r\n" + 
						"ltrim(TO_CHAR(comm.created_date,'DD-MON-YYYY'),'0') as posn_date,\r\n" + 
						" CASE\r\n" + 
						"WHEN (date_part('year',age(now(),comm.date_of_birth)) between 0 and 30) THEN 'below30'\r\n" + 
						"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 30 and 40) THEN '30to40years'\r\n" + 
						"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 41 and 45) THEN '40to45years'\r\n" + 
						"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 46 and 50) THEN 'below50'\r\n" + 
						"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 51 and 80) THEN 'above50' END AS serving\r\n" + 
						"from tb_psg_trans_proposed_comm_letter comm        \r\n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n" + 
						"INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
						"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
						"WHERE comm.status in ('1','5') AND type_of_comm_granted <> '20' and orb.status_sus_no='Active' \n"
						+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n"
						+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))"+SearchValue+" ) ser " +SearchValue1+ 
						"ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			if (typeID.equals("2")) {
				q ="select  ser.unit_name,ser.unit_sus_no,ser.army_no,\r\n" + 
						"ser.rank,ser.full_name,serving from (select orb.unit_name,\r\n" + 
						"comm.unit_sus_no,comm.army_no,rk.rank,comm.full_name,comm.status,rk.id,\r\n" + 
						"fv.form_code_control as Command, fvm.form_code_control as Corps, div.form_code_control as Division,\r\n" + 
						"bde.form_code_control as Brigade,orb.sus_no,\r\n" + 
						"ltrim(TO_CHAR(comm.created_date,'DD-MON-YYYY'),'0') as posn_date,\r\n" + 
						" CASE\r\n" + 
						"WHEN (date_part('year',age(now(),comm.date_of_birth)) between 0 and 30) THEN 'below30'\r\n" + 
						"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 30 and 40) THEN 'between30to40'\r\n" + 
						"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 41 and 45) THEN 'between40to45'\r\n" + 
						"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 46 and 50) THEN 'below50'\r\n" + 
						"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 51 and 80) THEN 'above50' END AS serving\r\n" + 
						"from tb_psg_census_jco_or_p comm        \r\n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n" + 
						"INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
						"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
						"WHERE comm.status in ('1','5') and orb.status_sus_no='Active' \n"
						+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n"
						+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))"+SearchValue+" ) ser " +SearchValue1+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,search, cont_comd, cont_corps,cont_div, cont_bde,unit_name, unit_sus_no, /*user_role_id, */from_date, to_date, typeID, posn_date, servingID);
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
	
	
	public long getage_wise_dtls_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,
			String cont_comd, String cont_corps, String cont_div, String cont_bde, String unit_name, String unit_sus_no, String from_date, String to_date, String typeID, String posn_date, String servingID) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps,cont_div, cont_bde, unit_name, unit_sus_no, from_date, to_date, typeID, posn_date);
		String SearchValue1 = GenerateQueryWhereClause_SQLServing(Search, servingID);
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				if (typeID.equals("1")) {
					q= "select count (app.*) from(select  ser.unit_name,ser.unit_sus_no,ser.personnel_no,\r\n" + 
							"ser.description,ser.name,serving from (select distinct orb.unit_name,\r\n" + 
							"comm.unit_sus_no,comm.personnel_no,\r\n" + 
							"rk.description,comm.name,comm.status,comm.type_of_comm_granted,rk.id,\r\n" + 
							"fv.form_code_control as Command, fvm.form_code_control as Corps, div.form_code_control as Division,\r\n" + 
							"bde.form_code_control as Brigade,orb.sus_no,\r\n" + 
							"ltrim(TO_CHAR(comm.created_date,'DD-MON-YYYY'),'0') as posn_date,\r\n" + 
							" CASE\r\n" + 
							"WHEN (date_part('year',age(now(),comm.date_of_birth)) between 0 and 30) THEN 'below30'\r\n" + 
							"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 30 and 40) THEN '30to40years'\r\n" + 
							"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 41 and 45) THEN '40to45years'\r\n" + 
							"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 46 and 50) THEN 'below50'\r\n" + 
							"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 51 and 80) THEN 'above50' END AS serving\r\n" + 
							"from tb_psg_trans_proposed_comm_letter comm        \r\n" + 
							"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n" + 
							"INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\r\n" + 
							"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
							"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
							"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
							"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
							"WHERE comm.status in ('1','5') AND type_of_comm_granted <> '20' and orb.status_sus_no='Active'\n "
							+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n"
							+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))"+SearchValue+" ) ser " +SearchValue1+ " ) app ";
				}
				if (typeID.equals("2")) {
					q ="select count (app.*) from(select  ser.unit_name,ser.unit_sus_no,ser.army_no,\r\n" + 
							"ser.rank,ser.full_name,serving from (select orb.unit_name,\r\n" + 
							"comm.unit_sus_no,comm.army_no,rk.rank,comm.full_name,comm.status,rk.id,\r\n" + 
							"fv.form_code_control as Command, fvm.form_code_control as Corps, div.form_code_control as Division,\r\n" + 
							"bde.form_code_control as Brigade,orb.sus_no,\r\n" + 
							"ltrim(TO_CHAR(comm.created_date,'DD-MON-YYYY'),'0') as posn_date,\r\n" + 
							" CASE\r\n" + 
							"WHEN (date_part('year',age(now(),comm.date_of_birth)) between 0 and 30) THEN 'below30'\r\n" + 
							"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 30 and 40) THEN 'between30to40'\r\n" + 
							"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 41 and 45) THEN 'between40to45'\r\n" + 
							"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 46 and 50) THEN 'below50'\r\n" + 
							"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 51 and 80) THEN 'above50' END AS serving\r\n" + 
							"from tb_psg_census_jco_or_p comm        \r\n" + 
							"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n" + 
							"INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\r\n" + 
							"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
							"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
							"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
							"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
							"WHERE comm.status in ('1','5') and orb.status_sus_no='Active'\n"
							+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n"
							+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))\n" +
							" "+SearchValue+" ) ser " +SearchValue1+ ") app  ";
				}
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name, unit_sus_no, from_date, to_date, typeID, posn_date, servingID);
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
 
 
 
 public List<Map<String, Object>> getage_wise_dtls_tablesum(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, String from_date, String to_date, String typeID, String posn_date, String servingID) 
	{
	 String SearchValue = GenerateQueryWhereClause_SQLsum(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no, from_date, to_date, typeID, posn_date);
	 String SearchValue1 = GenerateQueryWhereClause_SQLsumServing(search, servingID);
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
			q=" select ser.unit_name,ser.unit_sus_no,ser.serving,ser.officer,ser.total\r\n" + 
					"from(select distinct orb.unit_name,comm.unit_sus_no,\r\n" + 
					"CASE\r\n" + 
					"WHEN (date_part('year',age(now(),comm.date_of_birth)) between 0 and 30) THEN 'below30'\r\n" + 
					"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 30 and 40) THEN '30to40years'\r\n" + 
					"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 41 and 45) THEN '40to45years'\r\n" + 
					"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 46 and 50) THEN 'below50'\r\n" + 
					"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 51 and 80) THEN 'above50' END AS serving,\r\n" + 
					" count (comm.id) as officer,\r\n" + 
					" count (comm.id) as total\r\n" + 
					"from tb_psg_trans_proposed_comm_letter comm        \r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n" + 
					"INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\r\n" + 
					"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
					"WHERE comm.status in ('1','5')  AND type_of_comm_granted <> '20' and orb.status_sus_no='Active' \r\n"
					+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n"
					+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))\r\n" + 
					SearchValue+" group by 1,2,3 \r\n " +
					" ) ser " +SearchValue1+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			if (typeID.equals("2")) {
				q=" select ser.unit_name,ser.unit_sus_no,ser.serving,ser.jco,ser.ors,ser.total\r\n" + 
						"from(select distinct orb.unit_name,comm.unit_sus_no,\r\n" + 
						"CASE\r\n" + 
						"WHEN (date_part('year',age(now(),comm.date_of_birth)) between 0 and 30) THEN 'below30'\r\n" + 
						"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 30 and 40) THEN 'between30to40'\r\n" + 
						"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 41 and 45) THEN 'between40to45'\r\n" + 
						"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 46 and 50) THEN 'below50'\r\n" + 
						"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 51 and 80) THEN 'above50' END AS serving,\r\n" + 
						" count (comm.id) FILTER (where comm.category ='JCO') as jco,\r\n" + 
						"  count (comm.id) FILTER (where comm.category ='OR') as ors,\r\n" + 
						" count (comm.id) as total\r\n" + 
						"from tb_psg_census_jco_or_p comm        \r\n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n" + 
						"INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
						"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
						"WHERE comm.status in ('1','5') and orb.status_sus_no='Active' \r\n "
						+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n"
						+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))" +SearchValue+
						" group by 1,2,3 \r\n" +
						" ) ser " +SearchValue1+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			stmt=conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQLsum(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, from_date, to_date, typeID, posn_date, servingID);
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

 
 public long getage_wise_dtls_tablecountsum(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String from_date, String to_date, String typeID, String posn_date, String servingID) {
	 String SearchValue = GenerateQueryWhereClause_SQLsum(Search, cont_comd, cont_corps,cont_div, cont_bde,
				 unit_name, unit_sus_no, from_date, to_date, typeID, posn_date);
	 String SearchValue1 = GenerateQueryWhereClause_SQLsumServing(Search, servingID); 
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				if (typeID.equals("1")) {
			  q="select count(app.*) from(select ser.unit_name,ser.unit_sus_no,ser.serving,ser.officer,ser.total\r\n" + 
			  		"from(select distinct orb.unit_name,comm.unit_sus_no,\r\n" + 
			  		"CASE\r\n" + 
			  		"WHEN (date_part('year',age(now(),comm.date_of_birth)) between 0 and 30) THEN 'below30'\r\n" + 
			  		"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 30 and 40) THEN '30to40years'\r\n" + 
			  		"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 41 and 45) THEN '40to45years'\r\n" + 
			  		"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 46 and 50) THEN 'below50'\r\n" + 
			  		"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 51 and 80) THEN 'above50' END AS serving,\r\n" + 
			  		" count (comm.id) as officer,\r\n" + 
			  		" count (comm.id) as total\r\n" + 
			  		"from tb_psg_trans_proposed_comm_letter comm        \r\n" + 
			  		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n" + 
			  		"INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\r\n" + 
			  		"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
			  		"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
			  		"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
			  		"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
			  		"WHERE comm.status in ('1','5')  AND type_of_comm_granted <> '20' and orb.status_sus_no='Active' \r\n"
			  		+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n"
			  		+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))\r\n" + 
			  		" "+SearchValue+ " group by 1,2,3 ) ser " +SearchValue1+ " ) app";

				}
				if (typeID.equals("2")) {
					 q="select count(app.*) from(select ser.unit_name,ser.unit_sus_no,ser.serving,ser.jco,ser.ors,ser.total\r\n" + 
					 		"from(select distinct orb.unit_name,comm.unit_sus_no,\r\n" + 
					 		"CASE\r\n" + 
					 		"WHEN (date_part('year',age(now(),comm.date_of_birth)) between 0 and 30) THEN 'below30'\r\n" + 
					 		"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 30 and 40) THEN 'between30to40'\r\n" + 
					 		"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 41 and 45) THEN 'between40to45'\r\n" + 
					 		"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 46 and 50) THEN 'below50'\r\n" + 
					 		"WHEN(date_part('year',age(now(),comm.date_of_birth)) between 51 and 80) THEN 'above50' END AS serving,\r\n" + 
					 		" count (comm.id) FILTER (where comm.category ='JCO') as jco,\r\n" + 
					 		"  count (comm.id) FILTER (where comm.category ='OR') as ors,\r\n" + 
					 		" count (comm.id) as total\r\n" + 
					 		"from tb_psg_census_jco_or_p comm        \r\n" + 
					 		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n" + 
					 		"INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\r\n" + 
					 		"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					 		"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					 		"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					 		"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
					 		"WHERE comm.status in ('1','5') and orb.status_sus_no='Active' \r\n "
					 		+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n"
					 		+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))" + SearchValue +
					 		" group by 1,2,3 \r\n " +
						  	" ) ser " +SearchValue1+ " ) app " ;
				}
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQLsum(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, from_date, to_date, typeID, posn_date, servingID);
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
			String unit_name,String unit_sus_no,/*String user_role_id,*/String from_date, String to_date, String typeID, String posn_date) {
	 
	
		String SearchValue ="";
		
		if (typeID.equals("1")) {
		if(!Search.equals("")) { // for Input Filter
			SearchValue =" and ";
			SearchValue +="( upper(orb.unit_name) like ? or upper(comm.unit_sus_no) like ? or upper(comm.personnel_no) like ? "
					+ " or upper(rk.description) like ? or upper(comm.name) like ? ) ";
		}
		
		if( !cont_comd.equals("")) {
			if (SearchValue.contains("and")) {
				SearchValue += " and  fv.form_code_control like ?  ";	
			}
			else {
				SearchValue += " and fv.form_code_control like ? ";
			}
		}
//		if( !cont_corps.equals("0")) {
//			if (SearchValue.contains("where")) {
//				SearchValue += " and  fvm.form_code_control like ?  ";	
//			}
//			else {
//				SearchValue += " and  fvm.form_code_control like ? ";
//			}
//		}
//		if( !cont_div.equals("0")) {
//			if (SearchValue.contains("where")) {
//				SearchValue += " and  div.form_code_control like ?  ";	
//			}
//			else {
//				SearchValue += " and  div.form_code_control like ? ";
//			}
//		}
//		if( !cont_bde.equals("0")) {
//			if (SearchValue.contains("where")) {
//				SearchValue += " and  bde.form_code_control like ?  ";	
//			}
//			else {
//				SearchValue += " and  bde.form_code_control like ? ";
//			}
//		}
		
//		if( !unit_name.equals("")) {
//			if (SearchValue.contains("where")) {
//				SearchValue += " and  upper (orb.unit_name) like ? ";	
//			}
//			else {
//				SearchValue += " and upper (orb.unit_name) like ?";
//			}
//		}
		
		
		if( !unit_sus_no.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and comm.unit_sus_no = ? ";	
			}
			else {
				SearchValue += " and comm.unit_sus_no = ? ";
			}
		}
		
		if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY") && !to_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(comm.date_of_birth as date) >= cast(? as date) and cast(comm.date_of_birth as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(comm.date_of_birth as date) >= cast(? as date) and cast(comm.date_of_birth as date) <= cast(? as date)"; 
			  }
		}
		
		if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(comm.date_of_birth as date) >= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(comm.date_of_birth as date) >= cast(? as date)"; 
			  }
		}
		
		if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(comm.date_of_birth as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(comm.date_of_birth as date) <= cast(? as date)"; 
			  }
		}
//		if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
//			if (SearchValue.contains("where")) {
//				  SearchValue +=  " and cast(ser.posn_date as date) <= cast(? as date)"; 
//			  } 
//			else { 
//				  SearchValue += " and cast(ser.posn_date as date) <= cast(? as date)"; 
//			  }
//		}
		
		
		
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
				SearchValue =" and ";
				SearchValue +="( upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ? or upper(comm.army_no) like ? "
						+ " or upper(rk.rank) like ? or upper(comm.full_name) like ? ) ";
			}
			
			if( !cont_comd.equals("")) {
				if (SearchValue.contains("and")) {
					SearchValue += " and  fv.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and fv.form_code_control like ? ";
				}
			}
//			if( !cont_corps.equals("0")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  fvm.form_code_control like ?  ";	
//				}
//				else {
//					SearchValue += " and  fvm.form_code_control like ? ";
//				}
//			}
//			if( !cont_div.equals("0")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  div.form_code_control like ?  ";	
//				}
//				else {
//					SearchValue += " and  div.form_code_control like ? ";
//				}
//			}
//			if( !cont_bde.equals("0")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  bde.form_code_control like ?  ";	
//				}
//				else {
//					SearchValue += " and  bde.form_code_control like ? ";
//				}
//			}
//			if( !unit_name.equals("")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  upper (orb.unit_name) = ? ";	
//				}
//				else {
//					SearchValue += " and upper (orb.unit_name) like ?";
//				}
//			}
			
			
			if( !unit_sus_no.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and comm.unit_sus_no = ? ";	
				}
				else {
					SearchValue += " and comm.unit_sus_no = ? ";
				}
			}
			
			
			if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY") && !to_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(comm.date_of_birth as date) >= cast(? as date) and cast(comm.date_of_birth as date) <= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(comm.date_of_birth as date) >= cast(? as date) and cast(comm.date_of_birth as date) <= cast(? as date)"; 
					}
			}
			
			if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(comm.date_of_birth as date) >= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(comm.date_of_birth as date) >= cast(? as date)"; 
					}
			}
			
			if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(comm.date_of_birth as date) <= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(comm.date_of_birth as date) <= cast(? as date)"; 
					}
			}
//			if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){
//				if (SearchValue.contains("where")) {
//					  SearchValue +=  " and  cast(ser.posn_date as date) <= cast(? as date)"; 
//				  }
//				else { SearchValue += " and  cast(ser.posn_date as date) <= cast(? as date)"; 
//					}
//		}
//			
			
			
		}
		
		String prf_whr = "";
		String prf_whr1 = ""; 
		
		return SearchValue;
	}
 
 	
 
 public String GenerateQueryWhereClause_SQLServing(String Search, String servingID) {
	 
	
		String SearchValue1 ="";
		

//		if(!Search.equals("")) { // for Input Filter
//			SearchValue1 ="where";
//			SearchValue1 +="( upper(ser.serving) like ? ) ";
//		}
		
		
		
		if( !servingID.equals("0")) { 
			  if (SearchValue1.contains("and")) {
				  SearchValue1 +=  " where  ser.serving = ? "; 
			  }
			  else {  
				  SearchValue1 += " where ser.serving = ?"; 
			  } 
		}
		
		
		return SearchValue1;
	}
	
	
	
	
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement
			  stmt,String Search, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			  String unit_name,String unit_sus_no, String from_date, String to_date, String typeID, String posn_date, String servingID) {
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
				
				
			}
			if(!cont_comd.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_comd.toUpperCase()+"%");
				
			}
//			if(!cont_corps.equals("0")) {
//				flag += 1;
//				stmt.setString(flag, cont_corps.toUpperCase()+"%");
//				
//			}
//			if(!cont_div.equals("0")) {
//				flag += 1;
//				stmt.setString(flag, cont_div.toUpperCase()+"%");
//				
//			}
//			if(!cont_bde.equals("0")) {
//				flag += 1;
//				stmt.setString(flag, cont_bde.toUpperCase()+"%");
//				
//			}
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
			/*
			 * if(!posn_date.equals("")) { flag += 1; stmt.setString(flag, posn_date);
			 * 
			 * }
			 */
			if(!servingID.equals("0")) {
				flag += 1;
				stmt.setString(flag, servingID);
				
			}
			/*if(!user_role_id.equals("")) {
				flag += 1;
				stmt.setString(flag, user_role_id);
				
			}*/
			

		}catch (Exception e) {}
		
		return stmt;
		
	}
	
	
	
	
	public String GenerateQueryWhereClause_SQLsum(String Search, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name,String unit_sus_no,/*String user_role_id,*/String from_date, String to_date, String typeID, String posn_date) {
	 
	
		String SearchValue ="";
		
		if (typeID.equals("1")) {
		if(!Search.equals("")) { // for Input Filter
			SearchValue =" and ";
			SearchValue +="(  upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ? ) ";
		}
		
		if( !cont_comd.equals("")) {
			if (SearchValue.contains("and")) {
				SearchValue += " and  fv.form_code_control like ?  ";	
			}
			else {
				SearchValue += " and fv.form_code_control like ? ";
			}
		}
		
//		if( !cont_corps.equals("0")) {
//			if (SearchValue.contains("where")) {
//				SearchValue += " and  fvm.form_code_control like ?  ";	
//			}
//			else {
//				SearchValue += " and  fvm.form_code_control like ? ";
//			}
//		}
//		
//		if( !cont_div.equals("0")) {
//			if (SearchValue.contains("where")) {
//				SearchValue += " and  div.form_code_control like ?  ";	
//			}
//			else {
//				SearchValue += " and  div.form_code_control like ? ";
//			}
//		}
//		
//		if( !cont_bde.equals("0")) {
//			if (SearchValue.contains("where")) {
//				SearchValue += " and  bde.form_code_control like ?  ";	
//			}
//			else {
//				SearchValue += " and  bde.form_code_control like ? ";
//			}
//		}
//		
//		if( !unit_name.equals("")) {
//			if (SearchValue.contains("where")) {
//				SearchValue += " and  upper (orb.unit_name) = ? ";	
//			}
//			else {
//				SearchValue += " and upper (orb.unit_name) like ?";
//			}
//		}
		
		
		if( !unit_sus_no.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and comm.unit_sus_no = ? ";	
			}
			else {
				SearchValue += " and comm.unit_sus_no = ? ";
			}
		}
		
		
		if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY") && !to_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(comm.date_of_birth as date) >= cast(? as date) and cast(comm.date_of_birth as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(comm.date_of_birth as date) >= cast(? as date) and cast(comm.date_of_birth as date) <= cast(? as date)"; 
			  }
		}
		
		if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(comm.date_of_birth as date) >= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(comm.date_of_birth as date) >= cast(? as date)"; 
			  }
		}
		
		if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(comm.date_of_birth as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(comm.date_of_birth as date) <= cast(? as date)"; 
			  }
		}
//		if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
//			if (SearchValue.contains("where")) {
//				  SearchValue +=  " and cast(ser.posn_date as date) <= cast(? as date)"; 
//			  } 
//			else { 
//				  SearchValue += " and cast(ser.posn_date as date) <= cast(? as date)"; 
//			  }
//		}
		
		
		
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
				SearchValue =" and ";
				SearchValue +="( upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ? ) ";
			}
			
			if( !cont_comd.equals("")) {
				if (SearchValue.contains("and")) {
					SearchValue += " and  fv.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and fv.form_code_control like ? ";
				}
			}
//			if( !cont_corps.equals("0")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  fvm.form_code_control like ?  ";	
//				}
//				else {
//					SearchValue += " and  fvm.form_code_control like ? ";
//				}
//			}
//			if( !cont_div.equals("0")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  div.form_code_control like ?  ";	
//				}
//				else {
//					SearchValue += " and  div.form_code_control like ? ";
//				}
//			}
//			if( !cont_bde.equals("0")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  bde.form_code_control like ?  ";	
//				}
//				else {
//					SearchValue += " and  bde.form_code_control like ? ";
//				}
//			}
//			if( !unit_name.equals("")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  upper (orb.unit_name) = ? ";	
//				}
//				else {
//					SearchValue += " and upper (orb.unit_name) like ?";
//				}
//			}
			if( !unit_sus_no.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and comm.unit_sus_no = ? ";	
				}
				else {
					SearchValue += " and comm.unit_sus_no = ? ";
				}
			}
			
			
			if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY") && !to_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(comm.date_of_birth as date) >= cast(? as date) and cast(comm.date_of_birth as date) <= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(comm.date_of_birth as date) >= cast(? as date) and cast(comm.date_of_birth as date) <= cast(? as date)"; 
					}
			}
			
			if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(comm.date_of_birth as date) >= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(comm.date_of_birth as date) >= cast(? as date)"; 
					}
			}
			
			if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(comm.date_of_birth as date) <= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(comm.date_of_birth as date) <= cast(? as date)"; 
					}
			}
			
//			if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){
//				if (SearchValue.contains("where")) {
//					  SearchValue +=  " and cast(ser.posn_date as date) <= cast(? as date)"; 
//				  }
//				else { SearchValue += " and cast(ser.posn_date as date) <= cast(? as date)"; 
//					}
//		}
			
			
			
		}
		
		String prf_whr = "";
		String prf_whr1 = ""; 
		
		return SearchValue;
	}
	
	
	public String GenerateQueryWhereClause_SQLsumServing(String Search, String servingID) {
	 
	
		String SearchValue1 ="";
		
//		if(!Search.equals("")) { // for Input Filter
//			SearchValue1 ="where";
//			SearchValue1 +="(  upper(ser.serving) like ? ) ";
//		}
		
		if( !servingID.equals("0")) { 
			  if (SearchValue1.contains("and")) {
				  SearchValue1 +=  " where ser.serving = ? "; 
			  }
			  else {  
				  SearchValue1 += " where ser.serving = ? "; 
			  } 
		}
		
		
		return SearchValue1;

	}
	
	public PreparedStatement setQueryWhereClause_SQLsum(PreparedStatement
			  stmt,String Search, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			  String unit_name,String unit_sus_no, String from_date, String to_date, String typeID, String posn_date, String servingID) {
		int flag = 0;
		try {
			if(!Search.equals("")) {			
				
				

				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
			}
			if(!cont_comd.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_comd.toUpperCase()+"%");
				
			}
//			if(!cont_corps.equals("0")) {
//				flag += 1;
//				stmt.setString(flag, cont_corps.toUpperCase()+"%");
//				
//			}
//			if(!cont_div.equals("0")) {
//				flag += 1;
//				stmt.setString(flag, cont_div.toUpperCase()+"%");
//				
//			}
//			if(!cont_bde.equals("0")) {
//				flag += 1;
//				stmt.setString(flag, cont_bde.toUpperCase()+"%");
//				
//			}
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
//			if(!posn_date.equals("")) {
//				flag += 1;
//				stmt.setString(flag, posn_date);
//				
//			}
			if(!servingID.equals("0")) {
				flag += 1;
				stmt.setString(flag, servingID);
				
			}
			/*if(!user_role_id.equals("")) {
				flag += 1;
				stmt.setString(flag, user_role_id);
				
			}*/
			

		}catch (Exception e) {}
		
		return stmt;
		
	}
	
}
