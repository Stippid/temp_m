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

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class Pers_Service_Daoimpl implements Pers_Service_Dao{
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
    public List<Map<String, Object>> getsearch_service_table(int startPage, int pageLength, String search,
    		String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
    		String cont_div, String cont_bde, String unit_name, String unit_sus_no, String rankID, 
    		String rankJcoID, String serving, String typeID, String posn_date) {
    	// TODO Auto-generated method stub) 

     String SearchValue = GenerateQueryWhere_SQL(search, cont_comd, cont_corps, cont_div, cont_bde,
    			 unit_name, unit_sus_no, rankID, rankJcoID,  typeID, posn_date);
     String SearchValue1 = GenerateQueryWhere_SQLServing(search, serving);
     
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
    			
    		q= "select  ser.unit_name,ser.unit_sus_no,ser.personnel_no,\n"
    				+ "ser.description,ser.name,serving from (select distinct orb.unit_name,\n"
    				+ "comm.unit_sus_no,comm.personnel_no,\n"
    				+ "rk.description,comm.name,comm.status,comm.type_of_comm_granted,rk.id,\n"
    				+ "fv.form_code_control as Command, fvm.form_code_control as Corps, div.form_code_control as Division,\n"
    				+ "bde.form_code_control as Brigade,orb.sus_no,\n"
    				+ " CASE\n"
    				+ "  WHEN (date_part('year',age(now(),comm.date_of_commission)) between 0 and 10) \n"
    				+ "  THEN 'below10'\n"
    				+ "  WHEN (date_part('year',age(now(),comm.date_of_commission)) between 11 and 20)\n"
    				+ "  THEN '11to20years'\n"
    				+ "  WHEN (date_part('year',age(now(),comm.date_of_commission)) between 20 and 42) \n"
    				+ "  THEN 'above20'  END AS serving\n"
    				+ "from tb_psg_trans_proposed_comm_letter comm        \n"
    				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
    				+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
    				+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
    				+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
    				+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
    				+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
    				+ "WHERE comm.status in ('1','5') AND type_of_comm_granted <> '20' and orb.status_sus_no='Active' \n"
    				+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\n"
    				+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))" + SearchValue + " ) ser " + SearchValue1 + " ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
    		
                        }
                         if (typeID.equals("2")) {
    	
                     		q ="select  ser.unit_name,ser.unit_sus_no,ser.army_no,ser.rank,ser.full_name,serving \n"
                     				+ "from \n"
                     				+ "(select distinct   orb.unit_name,comm.unit_sus_no,comm.army_no,\n"
                     				+ "rk.rank,comm.full_name,rk.id ,comm.status,fv.form_code_control as Command, fvm.form_code_control Corps,\n"
                     				+ " div.form_code_control as Division, bde.form_code_control as Brigade,orb.sus_no,\n"
                     				+ " CASE\n"
                     				+ "  WHEN (date_part('year',age(now(),comm.enroll_dt)) between 0 and 10) THEN 'below10'\n"
                     				+ "  WHEN (date_part('year',age(now(),comm.enroll_dt)) between 11 and 20) THEN '11to20years'\n"
                     				+ "  WHEN (date_part('year',age(now(),comm.enroll_dt)) between 20 and 42) THEN 'above20'  END AS serving\n"
                     				+ "from tb_psg_census_jco_or_p comm        \n"
                     				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
                     				+ "INNER JOIN tb_psg_mstr_rank_jco  rk on comm.rank=rk.id\n"
                     				+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
                     				+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
                     				+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
                     				+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
                     				+ "WHERE comm.status in ('1','5') and orb.status_sus_no='Active'\n"
                     				+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\n"
                     				+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6')) " + SearchValue + " ) ser " + SearchValue1 + " ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
                     				
                                   
                         }
    		            
    		
    		
    		stmt=conn.prepareStatement(q);
    	    stmt = setQueryWhereClause_SQL1(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
    					 unit_name, unit_sus_no,rankID,  rankJcoID, serving, typeID,posn_date);
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
    

public long getsearch_service_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
		String unit_name,String unit_sus_no, String rankID,	String rankJcoID, String serving, String typeID, String posn_date) {
 String SearchValue = GenerateQueryWhere_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde,
			 unit_name, unit_sus_no,rankID,  rankJcoID,  typeID, posn_date); 
 String SearchValue1 = GenerateQueryWhere_SQLServing(Search, serving);
 
	int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		
			if (typeID.equals("1")) {	
		  q= "select count (app.*) from(select  ser.unit_name,ser.unit_sus_no,ser.personnel_no,\n"
		  		+ "ser.description,ser.name,serving from (select distinct orb.unit_name,\n"
		  		+ "comm.unit_sus_no,comm.personnel_no,\n"
		  		+ "rk.description,comm.name,comm.status,comm.type_of_comm_granted,rk.id,\n"
		  		+ "fv.form_code_control as Command, fvm.form_code_control as Corps, div.form_code_control as Division,\n"
		  		+ "bde.form_code_control as Brigade, orb.sus_no,\n"
		  		+ " CASE\n"
		  		+ "  WHEN (date_part('year',age(now(),comm.date_of_commission)) between 0 and 10) \n"
		  		+ "  THEN 'below10'\n"
		  		+ "  WHEN (date_part('year',age(now(),comm.date_of_commission)) between 11 and 20)\n"
		  		+ "  THEN '11to20years'\n"
		  		+ "  WHEN (date_part('year',age(now(),comm.date_of_commission)) between 20 and 42) \n"
		  		+ "  THEN 'above20'  END AS serving\n"
		  		+ "from tb_psg_trans_proposed_comm_letter comm        \n"
		  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
		  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
		  		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
		  		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
		  		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
		  		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
		  		+ "WHERE comm.status in ('1','5') AND type_of_comm_granted <> '20' and orb.status_sus_no='Active' \n"
		  		+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\n"
		  		+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))" + SearchValue + " ) ser " + SearchValue1 + " ) app ";
		  
		
			}
			if (typeID.equals("2")) {
			q ="select count (app.*) from(select  ser.unit_name,ser.unit_sus_no,ser.army_no,ser.rank,ser.full_name,serving \n"
					+ "from \n"
					+ "(select distinct   orb.unit_name,comm.unit_sus_no,comm.army_no,\n"
					+ "rk.rank,comm.full_name,rk.id ,comm.status,fv.form_code_control as Command, fvm.form_code_control Corps,\n"
					+ " div.form_code_control as Division, bde.form_code_control as Brigade,orb.sus_no,\n"
					+ " CASE\n"
					+ "  WHEN (date_part('year',age(now(),comm.enroll_dt)) between 0 and 10) THEN 'below10'\n"
					+ "  WHEN (date_part('year',age(now(),comm.enroll_dt)) between 11 and 20) THEN '11to20years'\n"
					+ "  WHEN (date_part('year',age(now(),comm.enroll_dt)) between 20 and 42) THEN 'above20'  END AS serving\n"
					+ "from tb_psg_census_jco_or_p comm        \n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN tb_psg_mstr_rank_jco  rk on comm.rank=rk.id\n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ "WHERE comm.status in ('1','5') and orb.status_sus_no='Active' \n"
					+ " and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\n"
					+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))" + SearchValue + " ) ser " + SearchValue1 + " ) app  ";
				
			}
				 
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no,rankID, rankJcoID, serving,  typeID, posn_date);

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



public List<Map<String, Object>> getsearch_service_summery_table(int startPage, int pageLength, String search,
		String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
		String cont_div, String cont_bde, String unit_name, String unit_sus_no, String rankID, 
		String rankJcoID, String serving, String typeID, String posn_date) {
	// TODO Auto-generated method stub) 

 String SearchValue = GenerateQueryWhere_SQL_1(search, cont_comd, cont_corps, cont_div, cont_bde,
			 unit_name, unit_sus_no,rankID,rankJcoID,  typeID, posn_date);
 String SearchValue1 = GenerateQueryWhere_SQL_1Serving(search, serving);
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
		q= "select ser.unit_name,ser.unit_sus_no,ser.serving,ser.officer,ser.total\n" + 
				"from\n" + 
				"(select distinct orb.unit_name,comm.unit_sus_no,\n" + 
				"CASE\n" + 
				" WHEN (date_part('year',age(now(),comm.date_of_commission)) between 0 and 10) THEN 'below10'\n" + 
				"  WHEN (date_part('year',age(now(),comm.date_of_commission)) between 11 and 20) THEN '11to20years'\n" + 
				" WHEN (date_part('year',age(now(),comm.date_of_commission)) between 20 and 42) THEN 'above20'  END AS serving,\n" + 
				" count (comm.id) as officer,\n" + 
				" count (comm.id) as total\n" + 
				"from tb_psg_trans_proposed_comm_letter comm        \n" + 
				"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n" + 
				"INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n" + 
				"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n" + 
				"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n" + 
				"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n" + 
				"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n" + 
				"WHERE comm.status in ('1','5')  AND type_of_comm_granted <> '20' and orb.status_sus_no='Active' \n"
				+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\n"
				+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))" + SearchValue +
				" group by 1,2,3 \n" + 
				" ) ser " + SearchValue1 + " ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage  ;
		
                      }
             if (typeID.equals("2")) {
	
	    q= " select ser.unit_name,ser.unit_sus_no,ser.serving, ser.jco, ser.ors,total  from\n" + 
	    		" (select distinct   orb.unit_name,comm.unit_sus_no,\n" + 
	    		" CASE\n" + 
	    		"  WHEN (date_part('year',age(now(),comm.enroll_dt)) between 0 and 10) THEN 'below10'\n" + 
	    		"  WHEN (date_part('year',age(now(),comm.enroll_dt)) between 11 and 20) THEN '11to20years'\n" + 
	    		"  WHEN (date_part('year',age(now(),comm.enroll_dt)) between 20 and 42) THEN 'above20' END AS serving,\n" + 
	    		"  count (comm.id) FILTER (where comm.category ='JCO') as jco,\n" + 
	    		"  count (comm.id) FILTER (where comm.category ='OR') as ors,\n" + 
	    		" count (comm.id) as total\n" + 
	    		"from tb_psg_census_jco_or_p comm        \n" + 
	    		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n" + 
	    		"INNER JOIN tb_psg_mstr_rank_jco  rk on comm.rank=rk.id \n" + 
	    		"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n" + 
	    		"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n" + 
	    		"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n" + 
	    		"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n" + 
	    		" WHERE comm.status in ('1','5') and orb.status_sus_no='Active' and rk.status = 'active' \n "
	    		+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\n"
	    		+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))" + SearchValue +
	    		"  group by 1,2,3 \n" +
	    		" ) ser " + SearchValue1 + " ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage  ;
            	 
            	 
                     }
		
		stmt=conn.prepareStatement(q);
	    stmt = setQueryWhere_SQL(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, rankID, rankJcoID, serving, typeID, posn_date);
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

public long getsearch_service_summery_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
		String unit_name,String unit_sus_no, String rankID, String rankJcoID, String serving, String typeID, String posn_date) {
 String SearchValue = GenerateQueryWhere_SQL_1(Search, cont_comd, cont_corps, cont_div, cont_bde,
			 unit_name, unit_sus_no,rankID, rankJcoID,  typeID, posn_date);
 String SearchValue1 = GenerateQueryWhere_SQL_1Serving(Search, serving);
	int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		
			 if (typeID.equals("1")) {
				
		  q= "select count (app.*) from( select ser.unit_name,ser.unit_sus_no,ser.serving,ser.officer,ser.total\n" + 
		  		"from\n" + 
		  		"(select distinct orb.unit_name,comm.unit_sus_no,\n" + 
		  		"CASE\n" + 
		  		" WHEN (date_part('year',age(now(),comm.date_of_commission)) between 0 and 10) THEN 'below10'\n" + 
		  		"  WHEN (date_part('year',age(now(),comm.date_of_commission)) between 11 and 20) THEN '11to20years'\n" + 
		  		" WHEN (date_part('year',age(now(),comm.date_of_commission)) between 20 and 42) THEN 'above20'  END AS serving,\n" + 
		  		" count (comm.id) as officer,\n" + 
		  		" count (comm.id) as total\n" + 
		  		"from tb_psg_trans_proposed_comm_letter comm        \n" + 
		  		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n" + 
		  		"INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n" + 
		  		"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n" + 
		  		"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n" + 
		  		"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n" + 
		  		"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n" + 
		  		"WHERE comm.status in ('1','5')  AND type_of_comm_granted <> '20' and orb.status_sus_no='Active' \n"
		  		+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\n"
		  		+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))" + SearchValue +
		  		" group by 1,2,3\n" + 
		  		" ) ser " + SearchValue1 + " ) app ";
				 
		 
		}
		if (typeID.equals("2")) {
			
			q= "select count (app.*) from (select ser.unit_name,ser.unit_sus_no,ser.serving, ser.jco, ser.ors,total  from\n" + 
					"(select distinct   orb.unit_name,comm.unit_sus_no,\n" + 
					" CASE\n" + 
					"  WHEN (date_part('year',age(now(),comm.enroll_dt)) between 0 and 10) THEN 'below10'\n" + 
					"  WHEN (date_part('year',age(now(),comm.enroll_dt)) between 11 and 20) THEN '11to20years'\n" + 
					"  WHEN (date_part('year',age(now(),comm.enroll_dt)) between 20 and 42) THEN 'above20' END AS serving,\n" + 
					"  count (comm.id) FILTER (where comm.category ='JCO') as jco,\n" + 
					"  count (comm.id) FILTER (where comm.category ='OR') as ors,\n" + 
					" count (comm.id) as total\n" + 
					"from tb_psg_census_jco_or_p comm        \n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n" + 
					"INNER JOIN tb_psg_mstr_rank_jco  rk on comm.rank=rk.id and rk.status = 'active'\n" + 
					"left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n" + 
					"left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n" + 
					"left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n" + 
					"left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n" + 
					" WHERE comm.status in ('1','5') and orb.status_sus_no='Active' \n "
					+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\n"
					+ "and comm.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))" + SearchValue +
					"  group by 1,2,3 \n" +
					" ) ser " + SearchValue1 + " ) app" ;
			
		}
				 
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhere_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no,rankID, rankJcoID, serving, typeID, posn_date);
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

public String GenerateQueryWhere_SQL(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
		String unit_name,String unit_sus_no,String rankID,  String rankJcoID, String typeID, String posn_date) {
 

	String SearchValue ="";
	

	 
	 if (typeID.equals("1")) {
		 
	 
	 
	if(!Search.equals("")) { // for Input Filter
		SearchValue =" and  ";
		
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
//	if( !cont_corps.equals("0")) {
//		if (SearchValue.contains("where")) {
//			SearchValue += " and  fvm.form_code_control like ?  ";	
//		}
//		else {
//			SearchValue += " and  fvm.form_code_control like ? ";
//		}
//	}
//	if( !cont_div.equals("0")) {
//		if (SearchValue.contains("where")) {
//			SearchValue += " and  div.form_code_control like ?  ";	
//		}
//		else {
//			SearchValue += " and  div.form_code_control like ? ";
//		}
//	}
//	if( !cont_bde.equals("0")) {
//		if (SearchValue.contains("where")) {
//			SearchValue += " and  bde.form_code_control like ?  ";	
//		}
//		else {
//			SearchValue += " and  bde.form_code_control like ? ";
//		}
//	}
	
//	if( !unit_name.equals("")) {
//		if (SearchValue.contains("where")) {
//			SearchValue += " and  orb.unit_name like ? ";	
//		}
//		else {
//			SearchValue += " and orb.unit_name like ?";
//		}
//	}
	
	
	if( !unit_sus_no.equals("")) {
		if (SearchValue.contains("where")) {
			SearchValue += " and comm.unit_sus_no = ? ";	
		}
		else {
			SearchValue += " and comm.unit_sus_no = ? ";
		}
	}
	
	if( !rankID.equals("0")) {
		if (SearchValue.contains("where")) {
			SearchValue += " and  rk.id::text = ?  ";	
		}
		else {
			SearchValue += " and  rk.id::text = ? ";
		}
	}
	  if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(comm.date_of_tos as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(comm.date_of_tos as date) <= cast(? as date)"; 
			  }
		}
	
	  
	 
	
	 }
	 if (typeID.equals("2")) {
		 
		 
		 

			if(!Search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				
				SearchValue +="( upper(orb.unit_name) like ? or upper(comm.unit_sus_no) like ? or upper(comm.army_no) like ? "
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
//					SearchValue += " and  orb.unit_name = ? ";	
//				}
//				else {
//					SearchValue += " and orb.unit_name like ?";
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
			
			if( !rankJcoID.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  rk.id::text = ?  ";	
				}
				else {
					SearchValue += " and  rk.id::text = ? ";
				}
			}
			
			  if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
					if (SearchValue.contains("where")) {
						  SearchValue +=  " and cast(comm.date_of_tos as date) <= cast(? as date)"; 
					  } 
					else { 
						  SearchValue += " and cast(comm.date_of_tos as date) <= cast(? as date)"; 
					  }
				}
	 }
	
	
	return SearchValue;
}



public String GenerateQueryWhere_SQLServing(String Search, String serving) {
 

	String SearchValue1 ="";
	
//	if(!Search.equals("")) { // for Input Filter
//		SearchValue1 =" where  ";
//		
//		SearchValue1 +="( upper(ser.serving) like ?) ";
//	}
	
	
	

	if( !serving.equals("0")) { 
		  if (SearchValue1.contains(" where ")) {
			  SearchValue1 +=  " or ser.serving = ? "; 
		  }
		  else { 
			  SearchValue1 += " where ser.serving = ?"; 
		  } 
	}
	 
	
	
	return SearchValue1;
}




public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement
		  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no,String rankID, String rankJcoID, String serving, String typeID, String posn_date) {
	int flag = 0;
	try {
		if (typeID.equals("1")) {
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
					 }
					
					if (typeID.equals("2")) {
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
					}
		 if (typeID.equals("1")) {
		if(!cont_comd.equals("")) {
			flag += 1;
			stmt.setString(flag, cont_comd.toUpperCase()+"%");
			
		}
//		if(!cont_corps.equals("0")) {
//			flag += 1;
//			stmt.setString(flag, cont_corps.toUpperCase()+"%");
//			
//		}
//		if(!cont_div.equals("0")) {
//			flag += 1;
//			stmt.setString(flag, cont_div.toUpperCase()+"%");
//			
//		}
//		if(!cont_bde.equals("0")) {
//			flag += 1;
//			stmt.setString(flag, cont_bde.toUpperCase()+"%");
//			
//		}
//		if(!unit_name.equals("")) {
//			flag += 1;
//			stmt.setString(flag, unit_name.toUpperCase());
//			
//		}
		if(!unit_sus_no.equals("")) {
			flag += 1;
			stmt.setString(flag, unit_sus_no.toUpperCase());
			
		}
		if(!rankID.equals("0")) {
			flag += 1;
			stmt.setString(flag, rankID.toUpperCase());
			
		}
		
		if(!posn_date.equals("")) {
			flag += 1;
			stmt.setString(flag, posn_date);
			
		}
		
		if(!serving.equals("0")) {
			flag += 1;
			stmt.setString(flag, serving);
			
		}
		
		 }
		 
		 if (typeID.equals("2")) {
				if(!cont_comd.equals("")) {
					flag += 1;
					stmt.setString(flag, cont_comd.toUpperCase()+"%");
					
				}
//				if(!cont_corps.equals("0")) {
//					flag += 1;
//					stmt.setString(flag, cont_corps.toUpperCase()+"%");
//					
//				}
//				if(!cont_div.equals("0")) {
//					flag += 1;
//					stmt.setString(flag, cont_div.toUpperCase()+"%");
//					
//				}
//				if(!cont_bde.equals("0")) {
//					flag += 1;
//					stmt.setString(flag, cont_bde.toUpperCase()+"%");
//					
//				}
//				if(!unit_name.equals("")) {
//					flag += 1;
//					stmt.setString(flag, unit_name.toUpperCase());
//					
//				}
				if(!unit_sus_no.equals("")) {
					flag += 1;
					stmt.setString(flag, unit_sus_no.toUpperCase());
					
				}
				if(!rankJcoID.equals("0")) {
					flag += 1;
					stmt.setString(flag, rankJcoID.toUpperCase());
					
				}
				
				
				if(!posn_date.equals("")) {
					flag += 1;
					stmt.setString(flag, posn_date);
					
				}
				if(!serving.equals("0")) {
					flag += 1;
					stmt.setString(flag, serving);
					
				}
				
				 }	 

	}catch (Exception e) {}
	
	return stmt;
	
}



public String GenerateQueryWhere_SQL_1(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
		String unit_name,String unit_sus_no,String rankID, String rankJcoID, String typeID, String posn_date) {
 

	String SearchValue ="";

	if(!Search.equals("")) { // for Input Filter
		SearchValue =" and  ";
		
		SearchValue +="( upper(orb.unit_name) like ? or upper(comm.unit_sus_no) like ? ) ";
	}
	 if (typeID.equals("1")) {
	if( !cont_comd.equals("")) {
		if (SearchValue.contains("and")) {
			SearchValue += " and  fv.form_code_control like ?  ";	
		}
		else {
			SearchValue += " and fv.form_code_control like ? ";
		}
	}
//	if( !cont_corps.equals("0")) {
//		if (SearchValue.contains("where")) {
//			SearchValue += " and  fvm.form_code_control like ?  ";	
//		}
//		else {
//			SearchValue += " and  fvm.form_code_control like ? ";
//		}
//	}
//	if( !cont_div.equals("0")) {
//		if (SearchValue.contains("where")) {
//			SearchValue += " and  div.form_code_control like ?  ";	
//		}
//		else {
//			SearchValue += " and  div.form_code_control like ? ";
//		}
//	}
//	if( !cont_bde.equals("0")) {
//		if (SearchValue.contains("where")) {
//			SearchValue += " and  bde.form_code_control like ?  ";	
//		}
//		else {
//			SearchValue += " and  bde.form_code_control like ? ";
//		}
//	}
	
//	if( !unit_name.equals("")) {
//		if (SearchValue.contains("where")) {
//			SearchValue += " and  orb.unit_name like ? ";	
//		}
//		else {
//			SearchValue += " and orb.unit_name like ?";
//		}
//	}
	
	
	if( !unit_sus_no.equals("")) {
		if (SearchValue.contains("where")) {
			SearchValue += " and comm.unit_sus_no = ? ";	
		}
		else {
			SearchValue += " and comm.unit_sus_no = ? ";
		}
	}

	if( !rankID.equals("0")) {
		if (SearchValue.contains("where")) {
			SearchValue += " and  rk.id::text = ?  ";	
		}
		else {
			SearchValue += " and  rk.id::text = ? ";
		}
	}
	 if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(comm.date_of_tos as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(comm.date_of_tos as date) <= cast(? as date)"; 
			  }
		}
	
 }
	 if (typeID.equals("2")) {
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
//			
//			if( !unit_name.equals("")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  orb.unit_name like ? ";	
//				}
//				else {
//					SearchValue += " and orb.unit_name like ?";
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
			

			if( !rankJcoID.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  rk.id::text = ?  ";	
				}
				else {
					SearchValue += " and  rk.id::text = ? ";
				}
			}
			
			 if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
					if (SearchValue.contains("where")) {
						  SearchValue +=  " and cast(comm.date_of_tos as date) <= cast(? as date)"; 
					  } 
					else { 
						  SearchValue += " and cast(comm.date_of_tos as date) <= cast(? as date)"; 
					  }
				}
			
	 }
		
		

	return SearchValue;
}



public String GenerateQueryWhere_SQL_1Serving(String Search, String serving) {
 

	String SearchValue1 ="";

//	if(!Search.equals("")) { // for Input Filter
//		SearchValue1 =" where  ";
//		SearchValue1 +="( upper(ser.serving) like ? ) ";
//	}
	
	
	if( !serving.equals("0")) { 
		  if (SearchValue1.contains("where")) {
			  SearchValue1 +=  " or ser.serving = ? "; 
		  }
		  else { 
			  SearchValue1 += " where ser.serving = ?"; 
		  } 
	}

	return SearchValue1;
}



public PreparedStatement setQueryWhere_SQL(PreparedStatement
		  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no,String rankID, String rankJcoID, String serving, String typeID, String posn_date) {
	int flag = 0;
	try {
		if(!Search.equals("")) {			
			
			flag += 1;
			stmt.setString(flag, Search.toUpperCase()+"%");
			
			flag += 1;
			stmt.setString(flag, Search.toUpperCase()+"%");

			
		}
 if (typeID.equals("1")) {
		if(!cont_comd.equals("")) {
			flag += 1;
			stmt.setString(flag, cont_comd.toUpperCase()+"%");
			
		}
//		if(!cont_corps.equals("0")) {
//			flag += 1;
//			stmt.setString(flag, cont_corps.toUpperCase()+"%");
//			
//		}
//		if(!cont_div.equals("0")) {
//			flag += 1;
//			stmt.setString(flag, cont_div.toUpperCase()+"%");
//			
//		}
//		if(!cont_bde.equals("0")) {
//			flag += 1;
//			stmt.setString(flag, cont_bde.toUpperCase()+"%");
//			
//		}
//		if(!unit_name.equals("")) {
//			flag += 1;
//			stmt.setString(flag, unit_name.toUpperCase());
//			
//		}
		if(!unit_sus_no.equals("")) {
			flag += 1;
			stmt.setString(flag, unit_sus_no.toUpperCase());
			
		}	
		if(!rankID.equals("0")) {
			flag += 1;
			stmt.setString(flag, rankID.toUpperCase());
			
		}
		
		if(!posn_date.equals("")) {
			flag += 1;
			stmt.setString(flag, posn_date);
			
		}
		if(!serving.equals("0")) {
			flag += 1;
			stmt.setString(flag, serving);
			
		}
		
 }
 if (typeID.equals("2")) {
		if(!cont_comd.equals("")) {
			flag += 1;
			stmt.setString(flag, cont_comd.toUpperCase()+"%");
			
		}
//		if(!cont_corps.equals("0")) {
//			flag += 1;
//			stmt.setString(flag, cont_corps.toUpperCase()+"%");
//			
//		}
//		if(!cont_div.equals("0")) {
//			flag += 1;
//			stmt.setString(flag, cont_div.toUpperCase()+"%");
//			
//		}
//		if(!cont_bde.equals("0")) {
//			flag += 1;
//			stmt.setString(flag, cont_bde.toUpperCase()+"%");
//			
//		}
//		if(!unit_name.equals("")) {
//			flag += 1;
//			stmt.setString(flag, unit_name.toUpperCase());
//			
//		}
		if(!unit_sus_no.equals("")) {
			flag += 1;
			stmt.setString(flag, unit_sus_no.toUpperCase());
			
		}
		if(!rankJcoID.equals("0")) {
			flag += 1;
			stmt.setString(flag, rankJcoID.toUpperCase());
			
		}
		
		if(!posn_date.equals("")) {
			flag += 1;
			stmt.setString(flag, posn_date);
			
		}
		if(!serving.equals("0")) {
			flag += 1;
			stmt.setString(flag, serving);
			
		}
		
}	

	}catch (Exception e) {}
	
	return stmt;
	
}


}

