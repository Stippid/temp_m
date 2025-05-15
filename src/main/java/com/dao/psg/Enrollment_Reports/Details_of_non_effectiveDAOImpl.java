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


public class Details_of_non_effectiveDAOImpl implements Details_of_non_effectiveDAO{
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
    public List<Map<String, Object>> getsearch_non_effective_table (int startPage, int pageLength, String search,
    		String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
    		String cont_div, String cont_bde, String unit_name, String unit_sus_no, String typeID, String cause_of_non_effective,String from_date, String to_date) {
    	// TODO Auto-generated method stub) 

     String SearchValue = GenerateQueryWhere_SQL(search, cont_comd, cont_corps, cont_div, cont_bde,
    			 unit_name, unit_sus_no, typeID, cause_of_non_effective, from_date, to_date);
     
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
    			
    		q= "select distinct fv.unit_name as cmd_unit,\n"
    				+ "					  		fvm.unit_name as corp_unit,\n"
    				+ "					  		div.unit_name as div_unit,\n"
    				+ "					  		bde.unit_name as bde_unit,orb.unit_name,\n"
    				+ "comm.unit_sus_no,comm.personnel_no,\n"
    				+ "rk.description,comm.name,mne.causes_name,ltrim(TO_CHAR(ne.date_of_non_effective ,'DD-MON-YYYY'),'0') as date_of_non_effective\n"
    				+ "from tb_psg_trans_proposed_comm_letter comm        \n"
    				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
    				+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
    				+ "inner join tb_psg_non_effective ne on ne.comm_id=comm.id\n"
    				+ "inner join tb_psg_mstr_cause_of_non_effective mne on mne.id=cast(ne.cause_of_non_effective as int)\n"
    				+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
    				+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
    				+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
    				+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
    				+ "where comm.status in ('4') and ne.status =1 and orb.status_sus_no='Active' "+SearchValue+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
    		
                        }
                         if (typeID.equals("2")) {
    	
                     		q ="select distinct \n"
                     				+ "fv.unit_name as cmd_unit,\n"
                     				+ "fvm.unit_name as corp_unit,\n"
                     				+ "div.unit_name as div_unit,\n"
                     				+ "bde.unit_name as bde_unit,\n"
                     				+ "orb.unit_name,\n"
                     				+ "comm.unit_sus_no,\n"
                     				+ "comm.army_no,rk.rank,comm.full_name,mne.causes_name,\n"
                     				+ "ltrim(TO_CHAR(ne.date_of_non_effective ,'DD-MON-YYYY'),'0') as date_of_non_effective\n"
                     				+ "from  tb_psg_census_jco_or_p comm \n"
                     				+ "inner join tb_psg_non_effective_jco ne on ne.jco_id=comm.id\n"
                     				+ "inner join tb_psg_mstr_cause_of_non_effective mne on mne.id=cast(ne.cause_of_non_effective as int)\n"
                     				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
                     				+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\n"
                     				+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
                     				+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
                     				+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
                     				+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
                     				+ "where comm.status in ('4') and ne.status =1 and orb.status_sus_no='Active' "+SearchValue+ "ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
                     				
                                   
                         }
    		            
    		
    		
    		stmt=conn.prepareStatement(q);
    	    stmt = setQueryWhereClause_SQL1(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
    					 unit_name, unit_sus_no, typeID, cause_of_non_effective, from_date, to_date);
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
    

public long getsearch_non_effective_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
		String unit_name,String unit_sus_no, String typeID, String cause_of_non_effective,String from_date, String to_date) {
 String SearchValue = GenerateQueryWhere_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde,
			 unit_name, unit_sus_no, typeID,cause_of_non_effective, from_date, to_date); 

 
	int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		
			if (typeID.equals("1")) {	
		  q= "select count (app.*) from(select distinct fv.unit_name as cmd_unit,\n"
		  		+ "					  		fvm.unit_name as corp_unit,\n"
		  		+ "					  		div.unit_name as div_unit,\n"
		  		+ "					  		bde.unit_name as bde_unit,orb.unit_name,\n"
		  		+ "comm.unit_sus_no,comm.personnel_no,\n"
		  		+ "rk.description,comm.name,mne.causes_name,ltrim(TO_CHAR(ne.date_of_non_effective ,'DD-MON-YYYY'),'0') as date_of_non_effective\n"
		  		+ "from tb_psg_trans_proposed_comm_letter comm        \n"
		  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
		  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
		  		+ "inner join tb_psg_non_effective ne on ne.comm_id=comm.id\n"
		  		+ "inner join tb_psg_mstr_cause_of_non_effective mne on mne.id=cast(ne.cause_of_non_effective as int)\n"
		  		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
		  		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
		  		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
		  		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
		  		+ "WHERE comm.status in ('4') and ne.status =1 and orb.status_sus_no='Active' "+SearchValue+" ) app ";
		  
		
			}
			if (typeID.equals("2")) {
			q ="select count (app.*) from(select distinct \n"
					+ "fv.unit_name as cmd_unit,\n"
					+ "fvm.unit_name as corp_unit,\n"
					+ "div.unit_name as div_unit,\n"
					+ "bde.unit_name as bde_unit,\n"
					+ "orb.unit_name,\n"
					+ "comm.unit_sus_no,\n"
					+ "comm.army_no,rk.rank,comm.full_name,mne.causes_name,\n"
					+ "ltrim(TO_CHAR(ne.date_of_non_effective ,'DD-MON-YYYY'),'0') as date_of_non_effective\n"
					+ "from  tb_psg_census_jco_or_p comm \n"
					+ "inner join tb_psg_non_effective_jco ne on ne.jco_id=comm.id\n"
					+ "inner join tb_psg_mstr_cause_of_non_effective mne on mne.id=cast(ne.cause_of_non_effective as int)\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ "where comm.status in ('4') and ne.status =1 and orb.status_sus_no='Active' "+SearchValue+" ) app  ";
				
			}
				 
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, typeID, cause_of_non_effective, from_date, to_date);
			
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


public List<Map<String, Object>> getsearch_non_effective_summery_table(int startPage, int pageLength, String search,
		String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
		String cont_div, String cont_bde, String unit_name, String unit_sus_no,String typeID, String cause_of_non_effective,String from_date, String to_date) {
 

 String SearchValue = GenerateQueryWhere_SQL_1(search, cont_comd, cont_corps, cont_div, cont_bde,
			 unit_name, unit_sus_no,typeID, cause_of_non_effective, from_date, to_date);
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
		q= "select distinct fv.unit_name as cmd_unit,\n"
				+ "					  		fvm.unit_name as corp_unit,\n"
				+ "					  		div.unit_name as div_unit,bde.unit_name as bde_unit,orb.unit_name,comm.unit_sus_no,mne.causes_name,\n"
				+ " count (comm.id) as officer,\n"
				+ "count (ne.id) as total\n"
				+ "from tb_psg_trans_proposed_comm_letter comm        \n"
				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
				+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
				+ "inner join tb_psg_non_effective ne on ne.comm_id=comm.id\n"
				+ "inner join tb_psg_mstr_cause_of_non_effective mne on mne.id=cast(ne.cause_of_non_effective as int)\n"
				+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
				+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
				+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
				+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
				+ "where comm.status in ('4') and ne.status =1 and orb.status_sus_no='Active' "+SearchValue+" group by 1,2,3,4,5,6,7" +"ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
		
                      }
             if (typeID.equals("2")) {
	
	    q= " select distinct \n"
	    		+ "fv.unit_name as cmd_unit,\n"
	    		+ "fvm.unit_name as corp_unit,\n"
	    		+ "div.unit_name as div_unit,\n"
	    		+ "bde.unit_name as bde_unit,\n"
	    		+ "orb.unit_name,\n"
	    		+ "comm.unit_sus_no,mne.causes_name,\n"
	    		+ "count (comm.id) FILTER (where comm.category ='JCO') as jco,\n"
	    		+ "count (comm.id) FILTER (where comm.category ='OR') as ors,\n"
	    		+ "count (ne.id) as total\n"
	    		+ "from  tb_psg_census_jco_or_p comm \n"
	    		+ "inner join tb_psg_non_effective_jco ne on ne.jco_id=comm.id\n"
	    		+ "inner join tb_psg_mstr_cause_of_non_effective mne on mne.id=cast(ne.cause_of_non_effective as int)\n"
	    		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
	    		+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\n"
	    		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
	    		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
	    		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
	    		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
	    		+ "where comm.status in ('4') and ne.status =1 and orb.status_sus_no='Active' \n"
	    		+ ""+SearchValue+" group by 1, 2,3,4,5,6,7 "+"ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage  ;
            	 
            	 
                     }
		
		stmt=conn.prepareStatement(q);
	    stmt = setQueryWhere_SQL(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, typeID, cause_of_non_effective, from_date, to_date);
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


public long getsearch_non_effective_summery_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
		String unit_name,String unit_sus_no, String typeID, String cause_of_non_effective,String from_date, String to_date) {
 String SearchValue = GenerateQueryWhere_SQL_1(Search, cont_comd, cont_corps, cont_div, cont_bde,
			 unit_name, unit_sus_no,typeID, cause_of_non_effective, from_date, to_date); 
	int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		
			 if (typeID.equals("1")) {
				
		  q= "select count (app.*) from (select distinct fv.unit_name as cmd_unit,\n"
		  		+ "					  		fvm.unit_name as corp_unit,\n"
		  		+ "					  		div.unit_name as div_unit,bde.unit_name as bde_unit,orb.unit_name,comm.unit_sus_no,mne.causes_name,\n"
		  		+ "count (comm.id) as officer,\n "
		  		+ "count (ne.id) as total\n"
		  		+ "from tb_psg_trans_proposed_comm_letter comm        \n"
		  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
		  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
		  		+ "inner join tb_psg_non_effective ne on ne.comm_id=comm.id\n"
		  		+ "inner join tb_psg_mstr_cause_of_non_effective mne on mne.id=cast(ne.cause_of_non_effective as int)\n"
		  		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
		  		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
		  		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
		  		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
		  		+ "where comm.status in ('4') and ne.status =1 and orb.status_sus_no='Active' "+SearchValue+" group by 1,2,3,4,5,6,7) app ";
				 
		 
		}
		if (typeID.equals("2")) {
			
			q= "select count (app.*) from (select distinct \n"
					+ "fv.unit_name as cmd_unit,\n"
					+ "fvm.unit_name as corp_unit,\n"
					+ "div.unit_name as div_unit,\n"
					+ "bde.unit_name as bde_unit,\n"
					+ "orb.unit_name,\n"
					+ "comm.unit_sus_no,mne.causes_name,\n"
					+ "count (comm.id) FILTER (where comm.category ='JCO') as jco,\n"
					+ "count (comm.id) FILTER (where comm.category ='OR') as ors,\n"
					+ "count (ne.id) as total\n"
					+ "from  tb_psg_census_jco_or_p comm \n"
					+ "inner join tb_psg_non_effective_jco ne on ne.jco_id=comm.id\n"
					+ "inner join tb_psg_mstr_cause_of_non_effective mne on mne.id=cast(ne.cause_of_non_effective as int)\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ "where comm.status in ('4') and ne.status =1 and orb.status_sus_no='Active' \n"
					+ ""+SearchValue+" group by 1, 2,3,4,5,6,7) app" ;
			
			
		}
				 
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhere_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no,typeID, cause_of_non_effective, from_date, to_date);
			
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
		String unit_name,String unit_sus_no, String typeID, String cause_of_non_effective,String from_date, String to_date) {
 

	String SearchValue ="";
	
	
	 
	 if (typeID.equals("1")) {
		 
	 
	 
	if(!Search.equals("")) { // for Input Filter
		SearchValue =" and  ";
		
		SearchValue +="( upper(fv.unit_name) like ? or upper(fvm.unit_name) like ? or upper(div.unit_name) like ? or upper(bde.unit_name) like ? "
				+ "or upper(orb.unit_name) like ? or upper( comm.unit_sus_no) like ? or upper(comm.personnel_no) like ? "
				+ " or upper(rk.description) like ? or upper(comm.name) like ?  or upper(mne.causes_name) like ?"
				+ "or upper(ltrim(TO_CHAR(ne.date_of_non_effective,'DD-MON-YYYY'),'0')) like ?"
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
			SearchValue += " and orb.sus_no = ? ";	
		}
		else {
			SearchValue += " and orb.sus_no = ? ";
		}
	}

	if( !cause_of_non_effective.equals("0")) {
		if (SearchValue.contains("where")) {
			SearchValue += " and mne.id::text = ? ";	
		}
		else {
			SearchValue += " and mne.id::text = ? ";
		}
	}
	
	if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY") && !to_date.equals("DD/MM/YYYY")){ 
		  if (SearchValue.contains("where")) {
	  SearchValue +=  " and cast(ne.date_of_non_effective as date) >= cast(? as date) and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
	  } 
		else { SearchValue += " and cast(ne.date_of_non_effective as date) >= cast(? as date) and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
		}
	}
	
	if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
		  if (SearchValue.contains("where")) {
	  SearchValue +=  " and cast(ne.date_of_non_effective as date) >= cast(? as date)"; 
	  } 
		else { SearchValue += " and cast(ne.date_of_non_effective as date) >= cast(? as date)"; 
		}
	}
	
	if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
		  if (SearchValue.contains("where")) {
	  SearchValue +=  " and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
	  } 
		else { SearchValue += " and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
		}
	}
	
	 }
	 if (typeID.equals("2")) {
		 
		 
		 

			if(!Search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				
				SearchValue +="( upper(fv.unit_name) like ? or upper(fvm.unit_name) like ? or upper(div.unit_name) like ? "
						+ "or upper(bde.unit_name) like ? or upper(orb.unit_name) like ? or upper( comm.unit_sus_no) like ? or upper(comm.army_no) like ? "
						+ "		or upper(rk.rank) like ? or upper(comm.full_name) like ?  or upper(mne.causes_name) like ?"
						+ "or upper(ltrim(TO_CHAR(ne.date_of_non_effective,'DD-MON-YYYY'),'0')) like ?"
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
			
			
			if( !unit_sus_no.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and orb.sus_no = ? ";	
				}
				else {
					SearchValue += " and orb.sus_no = ? ";
				}
			}
			
			if( !cause_of_non_effective.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and mne.id::text = ? ";	
				}
				else {
					SearchValue += " and mne.id::text = ? ";
				}
			}
			
			if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY") && !to_date.equals("DD/MM/YYYY")){ 
				  if (SearchValue.contains("where")) {
			  SearchValue +=  " and cast(ne.date_of_non_effective as date) >= cast(? as date) and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
			  } 
				else { SearchValue += " and cast(ne.date_of_non_effective as date) >= cast(? as date) and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
				}
			}
			
			if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
				  if (SearchValue.contains("where")) {
			  SearchValue +=  " and cast(ne.date_of_non_effective as date) >= cast(? as date)"; 
			  } 
				else { SearchValue += " and cast(ne.date_of_non_effective as date) >= cast(? as date)"; 
				}
			}
			
			if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
				  if (SearchValue.contains("where")) {
			  SearchValue +=  " and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
			  } 
				else { SearchValue += " and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
				}
			}
			
	 }
	
	
	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement
		  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no,String typeID, String cause_of_non_effective,String from_date, String to_date) {
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
		 if (typeID.equals("1")) {
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
//		if(!unit_name.equals("")) {
//			flag += 1;
//			stmt.setString(flag, unit_name.toUpperCase());
//			
//		}
		if(!unit_sus_no.equals("")) {
			flag += 1;
			stmt.setString(flag, unit_sus_no.toUpperCase());
			
		}
		if(!cause_of_non_effective.equals("0")) {
			flag += 1;
			stmt.setString(flag, cause_of_non_effective.toUpperCase());
			
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
		
		
		
		
		 }
		 
		 if (typeID.equals("2")) {
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
				if(!cause_of_non_effective.equals("0")) {
					flag += 1;
					stmt.setString(flag, cause_of_non_effective.toUpperCase());
					
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
				
				
				 }	 

	}catch (Exception e) {}
	
	return stmt;
	
}



public String GenerateQueryWhere_SQL_1(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
		String unit_name,String unit_sus_no,String typeID, String cause_of_non_effective,String from_date, String to_date) {
 

	String SearchValue ="";
	if(!Search.equals("")) { // for Input Filter
		SearchValue =" and  ";
		
		SearchValue +="(upper(fv.unit_name) like ? or upper(fvm.unit_name) like ? or upper(div.unit_name) like ? "
				+ "		or upper(bde.unit_name) like ? or upper(orb.unit_name) like ? or upper( comm.unit_sus_no) like ? or upper(mne.causes_name) like ?) ";
	}
	 if (typeID.equals("1")) {
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
			SearchValue += " and orb.sus_no = ? ";	
		}
		else {
			SearchValue += " and orb.sus_no = ? ";
		}
	}
	
	if( !cause_of_non_effective.equals("0")) {
		if (SearchValue.contains("where")) {
			SearchValue += " and mne.id::text = ? ";	
		}
		else {
			SearchValue += " and mne.id::text = ? ";
		}
	}
	
	if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY") && !to_date.equals("DD/MM/YYYY")){ 
		  if (SearchValue.contains("where")) {
	  SearchValue +=  " and cast(ne.date_of_non_effective as date) >= cast(? as date) and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
	  } 
		else { SearchValue += " and cast(ne.date_of_non_effective as date) >= cast(? as date) and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
		}
	}
	
	if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
		  if (SearchValue.contains("where")) {
	  SearchValue +=  " and cast(ne.date_of_non_effective as date) >= cast(? as date)"; 
	  } 
		else { SearchValue += " and cast(ne.date_of_non_effective as date) >= cast(? as date)"; 
		}
	}
	
	if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
		  if (SearchValue.contains("where")) {
	  SearchValue +=  " and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
	  } 
		else { SearchValue += " and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
		}
	}
	
 }
	 if (typeID.equals("2")) {
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
//					SearchValue += " and  orb.unit_name like ? ";	
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
			
			if( !cause_of_non_effective.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and mne.id::text = ? ";	
				}
				else {
					SearchValue += " and mne.id::text = ? ";
				}
			}
			
			if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY") && !to_date.equals("DD/MM/YYYY")){ 
				  if (SearchValue.contains("where")) {
			  SearchValue +=  " and cast(ne.date_of_non_effective as date) >= cast(? as date) and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
			  } 
				else { SearchValue += " and cast(ne.date_of_non_effective as date) >= cast(? as date) and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
				}
			}
			
			if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
				  if (SearchValue.contains("where")) {
			  SearchValue +=  " and cast(ne.date_of_non_effective as date) >= cast(? as date)"; 
			  } 
				else { SearchValue += " and cast(ne.date_of_non_effective as date) >= cast(? as date)"; 
				}
			}
			
			if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
				  if (SearchValue.contains("where")) {
			  SearchValue +=  " and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
			  } 
				else { SearchValue += " and cast(ne.date_of_non_effective as date) <= cast(? as date)"; 
				}
			}
			
	 }
		
		

	return SearchValue;
}

public PreparedStatement setQueryWhere_SQL(PreparedStatement
		  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no,String typeID, String cause_of_non_effective,String from_date, String to_date) {
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
 if (typeID.equals("1")) {
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
//		if(!unit_name.equals("")) {
//			flag += 1;
//			stmt.setString(flag, unit_name.toUpperCase());
//			
//		}
		if(!unit_sus_no.equals("")) {
			flag += 1;
			stmt.setString(flag, unit_sus_no.toUpperCase());
			
		}
		
		if(!cause_of_non_effective.equals("0")) {
			flag += 1;
			stmt.setString(flag, cause_of_non_effective.toUpperCase());
			
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
		
		
 }
 if (typeID.equals("2")) {
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
//		if(!unit_name.equals("")) {
//			flag += 1;
//			stmt.setString(flag, unit_name.toUpperCase());
//			
//		}
		if(!unit_sus_no.equals("")) {
			flag += 1;
			stmt.setString(flag, unit_sus_no.toUpperCase());
			
		}
	
		
		if(!cause_of_non_effective.equals("0")) {
			flag += 1;
			stmt.setString(flag, cause_of_non_effective.toUpperCase());
			
		}
		
		if(!from_date.equals("") && !to_date.equals("") && !from_date.equals("DD/MM/YYYY")  && !to_date.equals("DD/MM/YYYY")) { 
			  flag += 1; 	
			   stmt.setString(flag , from_date);
			   flag += 1;	
              stmt.setString(flag, to_date);
              
		  }
		
		if(!from_date.equals("")) {
			flag += 1;
			stmt.setString(flag, from_date);
			
		}
		if(!to_date.equals("")) {
			flag += 1;
			stmt.setString(flag, to_date);
			
		}
}	

	}catch (Exception e) {}
	
	return stmt;
	
}




}

