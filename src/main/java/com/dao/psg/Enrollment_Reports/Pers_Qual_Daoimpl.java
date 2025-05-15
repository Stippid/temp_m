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

public class Pers_Qual_Daoimpl implements Pers_Qual_Dao{
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
    public List<Map<String, Object>> getsearch_qualification_table(int startPage, int pageLength, String search,
    		String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
    		String cont_div, String cont_bde, String unit_name, String unit_sus_no, 
    		String typeID, String army_course_name1, String army_course_div_grade1, String posn_date) {
    	// TODO Auto-generated method stub) 

     String SearchValue = GenerateQueryWhere_SQL(search, cont_comd, cont_corps, cont_div, cont_bde,
    			 unit_name, unit_sus_no, typeID, army_course_name1, army_course_div_grade1, posn_date);
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
    		
    		if (typeID.equals("1") ) {	
    			
    		q= "select distinct \n"
    				+ "fv.unit_name as cmd_unit,\n"
    				+ "                fvm.unit_name as corp_unit,\n"
    				+ "                div.unit_name as div_unit,\n"
    				+ "                bde.unit_name as bde_unit,\n"
    				+ "                orb.unit_name,\n"
    				+ "                comm.unit_sus_no,\n"
    				+ "comm.personnel_no,rk.description as rank,comm.name,la.course_name \n"
    				+ "as course,ms.div as grading,"
    				+ "ltrim(TO_CHAR(la.date_of_completion  ,'DD-MON-YYYY'),'0') as date_of_completion\n"
    				+ "from tb_psg_census_army_course la \n"
    				+ "inner join tb_psg_mstr_div_grade ms on la.div_grade= ms.id::text \n"
    				+ "inner join tb_psg_trans_proposed_comm_letter comm on la.comm_id= comm.id \n"
    				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
    				+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
    				+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
    				+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
    				+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
    				+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
    				+ "where la.div_grade !='0' and la.status in ('1','2') and comm.status in ('1','5') and orb.status_sus_no='Active' "+SearchValue+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
    		
                        }
                         if (typeID.equals("2")) {
    	
                     		q ="select distinct \n"
                     				+ "fv.unit_name as cmd_unit,\n"
                     				+ "                fvm.unit_name as corp_unit,\n"
                     				+ "                div.unit_name as div_unit,\n"
                     				+ "                bde.unit_name as bde_unit,\n"
                     				+ "                orb.unit_name,\n"
                     				+ "                comm.unit_sus_no,\n"
                     				+ "comm.army_no,rk.rank,comm.full_name as name,la.course_name as course,ms.div as grading,ltrim(TO_CHAR(la.date_of_completion ,'DD-Mon-YYYY'),'0') as date_of_completion\n"
                     				+ "from tb_psg_census_army_course_jco la \n"
                     				+ "inner join tb_psg_mstr_div_grade ms on la.div_grade= ms.id::text \n"
                     				+ "inner join tb_psg_census_jco_or_p comm on la.jco_id= comm.id \n"
                     				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
                     				+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\n"
                     				+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
                     				+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
                     				+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
                     				+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'         \n"
                     				+ "where la.div_grade !='0' and la.status in ('1','2') and comm.status in ('1','5') and orb.status_sus_no='Active' "+SearchValue+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage 	;
                                   
                         }
    		            
    		
    		
    		stmt=conn.prepareStatement(q);
    	    stmt = setQueryWhereClause_SQL1(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
    					 unit_name, unit_sus_no, typeID, army_course_name1,army_course_div_grade1,  posn_date);
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
    
    

public long getsearch_qualification_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
		String unit_name,String unit_sus_no, String typeID, String army_course_name1, String army_course_div_grade1, String posn_date) {
 String SearchValue = GenerateQueryWhere_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde,
			 unit_name, unit_sus_no, typeID, army_course_name1, army_course_div_grade1,  posn_date); 
	int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		
			if (typeID.equals("1")) {	
		  q= "select count (app.*) from(select distinct \n"
		  		+ "fv.unit_name as cmd_unit,\n"
		  		+ "                fvm.unit_name as corp_unit,\n"
		  		+ "                div.unit_name as div_unit,\n"
		  		+ "                bde.unit_name as bde_unit,\n"
		  		+ "                orb.unit_name,\n"
		  		+ "                comm.unit_sus_no,\n"
		  		+ "comm.personnel_no,rk.description as rank,comm.name,la.course_name \n"
		  		+ "as course,ms.div as grading,\n"
		  		+ "ltrim(TO_CHAR(la.date_of_completion  ,'DD-MON-YYYY'),'0') as date_of_completion\n"
		  		+ "from tb_psg_census_army_course la \n"
		  		+ "inner join tb_psg_mstr_div_grade ms on la.div_grade= ms.id::text \n"
		  		+ "inner join tb_psg_trans_proposed_comm_letter comm on la.comm_id= comm.id \n"
		  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
		  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
		  		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
		  		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
		  		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
		  		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
		  		+ "where la.div_grade !='0' and la.status in ('1','2') and comm.status in ('1','5') and orb.status_sus_no='Active' "+SearchValue+") app ";
			}
			if (typeID.equals("2")) {
			q ="select count (app.*) from(select distinct \n"
					+ "fv.unit_name as cmd_unit,\n"
					+ "                fvm.unit_name as corp_unit,\n"
					+ "                div.unit_name as div_unit,\n"
					+ "                bde.unit_name as bde_unit,\n"
					+ "                orb.unit_name,\n"
					+ "                comm.unit_sus_no,\n"
					+ "comm.army_no,rk.rank,comm.full_name as name,la.course_name as course,ms.div as grading,\n"
					+ "ltrim(TO_CHAR(la.date_of_completion  ,'DD-MON-YYYY'),'0') as date_of_completion\n"
					+ "from tb_psg_census_army_course_jco la \n"
					+ "inner join tb_psg_mstr_div_grade ms on la.div_grade= ms.id::text \n"
					+ "inner join tb_psg_census_jco_or_p comm on la.jco_id= comm.id \n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ "where la.div_grade !='0' and la.status in ('1','2') and comm.status in ('1','5') and orb.status_sus_no='Active' "+SearchValue+") app "	;
				
			}
				 
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, typeID, army_course_name1,army_course_div_grade1,  posn_date);
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


public List<Map<String, Object>> getsearch_qualification_course_table(int startPage, int pageLength, String search,
		String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
		String cont_div, String cont_bde, String unit_name, String unit_sus_no, String typeID,
		String army_course_name1, String army_course_div_grade1, String posn_date) {
	// TODO Auto-generated method stub) 

 String SearchValue = GenerateQueryWhere_SQL_1(search, cont_comd, cont_corps, cont_div, cont_bde,
			 unit_name, unit_sus_no, typeID, army_course_name1, army_course_div_grade1,  posn_date);
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
		q= "select distinct \n"
				+ "fv.unit_name as cmd_unit,\n"
				+ "                fvm.unit_name as corp_unit,\n"
				+ "                div.unit_name as div_unit,\n"
				+ "                bde.unit_name as bde_unit,\n"
				+ "                orb.unit_name,\n"
				+ "                comm.unit_sus_no,\n"
				+ "la.course_name as name_of_course,\n"
				+ "count(la.*) as officer,\n"
				+ "count(la.*) as Total\n"
				+ "from tb_psg_census_army_course la \n"
				+ "inner join tb_psg_mstr_div_grade ms on la.div_grade= ms.id::text \n"
				+ "inner join tb_psg_trans_proposed_comm_letter comm on la.comm_id= comm.id \n"
				+ "inner join tb_psg_census_detail_p p  on p.comm_id= comm.id \n"
				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
				+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
				+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
				+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
				+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
				+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
				+ "where la.div_grade !='0' and p.status='1' and la.status in ('1','2') and comm.status in ('1','5') and orb.status_sus_no='Active' \n"
				+ ""+SearchValue+" group by 1,2,3,4,5,6,7 "
				+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
				
                      }
             if (typeID.equals("2")) {
	
	    q= "  select  fv.unit_name as cmd_unit,\n"
	    		+ "                fvm.unit_name as corp_unit,\n"
	    		+ "                div.unit_name as div_unit,\n"
	    		+ "                bde.unit_name as bde_unit,\n"
	    		+ "                orb.unit_name,\n"
	    		+ "                comm.unit_sus_no,  \n"
	    		+ "count(comm.*)FILTER (where comm.status in ('1','5')  and comm.category ='JCO') as JCO,\n"
	    		+ "count(comm.*)FILTER (where comm.status in ('1','5')  and comm.category ='OR') as ors,\n"
	    		+ "count(comm.*)FILTER (where comm.status in ('1','5') ) as Total,\n"
	    		+ "la.course_name as name_of_course\n"
	    		+ "from tb_psg_census_jco_or_p comm\n"
	    		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
	    		+ "inner join tb_psg_census_army_course_jco la on  comm.id = la.jco_id\n"
	    		+ "inner join tb_psg_mstr_div_grade ms on la.div_grade= ms.id::text\n"
	    		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
	    		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
	    		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
	    		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
	    		+ " where   la.div_grade !='0' and la.status in ('1','2') and  comm.status in ('1','5') and orb.status_sus_no='Active' \n"
	    		+ " "+SearchValue+" group by  1,2,3,4,5,6,10" 
	    		+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
            	 
            	 
                     }
		
		stmt=conn.prepareStatement(q);
	    stmt = setQueryWhere_SQL(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, typeID, army_course_name1, army_course_div_grade1,  posn_date);
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


public long getsearch_qualification_course_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
		String unit_name,String unit_sus_no, String typeID, String army_course_name1, String army_course_div_grade1, String posn_date) {
 String SearchValue = GenerateQueryWhere_SQL_1(Search, cont_comd, cont_corps, cont_div, cont_bde,
			 unit_name, unit_sus_no, typeID, army_course_name1, army_course_div_grade1,  posn_date); 
	int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		
			 if (typeID.equals("1")) {
				
		  q= "select count (app.*) from( select distinct \n"
		  		+ "fv.unit_name as cmd_unit,\n"
		  		+ "                fvm.unit_name as corp_unit,\n"
		  		+ "                div.unit_name as div_unit,\n"
		  		+ "                bde.unit_name as bde_unit,\n"
		  		+ "                orb.unit_name,\n"
		  		+ "                comm.unit_sus_no,\n"
		  		+ "la.course_name as name_of_course,\n"
		  		+ "count(la.*) as officer,\n"
		  		+ "count(la.*) as Total\n"
		  		+ "from tb_psg_census_army_course la \n"
		  		+ "inner join tb_psg_mstr_div_grade ms on la.div_grade= ms.id::text \n"
		  		+ "inner join tb_psg_trans_proposed_comm_letter comm on la.comm_id= comm.id \n"
		  		+ "inner join tb_psg_census_detail_p p  on p.comm_id= comm.id \n"
		  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
		  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
		  		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
		  		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
		  		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
		  		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
		  		+ "where la.div_grade !='0' and p.status='1' and la.status in ('1','2') and comm.status in ('1','5') and orb.status_sus_no='Active' \n"
		  		+ ""+SearchValue+" group by 1,2,3,4,5,6,7) app ";
				 
		 
		}
		if (typeID.equals("2")) {
			
			q= " select count (app.*) from(  select  fv.unit_name as cmd_unit,\n"
					+ "                fvm.unit_name as corp_unit,\n"
					+ "                div.unit_name as div_unit,\n"
					+ "                bde.unit_name as bde_unit,\n"
					+ "                orb.unit_name,\n"
					+ "                comm.unit_sus_no,  \n"
					+ "count(comm.*)FILTER (where comm.status in ('1','5')  and comm.category ='JCO') as JCO,\n"
					+ "count(comm.*)FILTER (where comm.status in ('1','5')  and comm.category ='OR') as ors,\n"
					+ "count(comm.*)FILTER (where comm.status in ('1','5') ) as Total,\n"
					+ "la.course_name as name_of_course\n"
					+ "from tb_psg_census_jco_or_p comm\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "inner join tb_psg_census_army_course_jco la on  comm.id = la.jco_id\n"
					+ "inner join tb_psg_mstr_div_grade ms on la.div_grade= ms.id::text\n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ " where   la.div_grade !='0' and la.status in ('1','2') and  comm.status in ('1','5') and orb.status_sus_no='Active' \n"
					+ ""+SearchValue+" group by  1,2,3,4,5,6,10) app" ;
			
			
		}
				 
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhere_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, typeID, army_course_name1, army_course_div_grade1,  posn_date);
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
		String unit_name,String unit_sus_no, String typeID, String army_course_name1,String army_course_div_grade1, String posn_date) {
 
	String SearchValue ="";
	 
	 if (typeID.equals("1")) {
	 
	if(!Search.equals("")) { // for Input Filter
		SearchValue =" and  ";
		
		SearchValue +="( upper(fv.unit_name) like ? or upper(fvm.unit_name) like ? or upper(div.unit_name) like ? "
				+ " or upper(bde.unit_name) like ? or upper(orb.unit_name) like ? or upper(comm.unit_sus_no) like ? or upper(comm.personnel_no) like ?"
				+ "	or upper(rk.description) like ? or upper(comm.name) like ? or upper(la.course_name) like ?"
				+ "	or upper(ms.div) like ? "
				+"or upper(ltrim(TO_CHAR(la.date_of_completion,'DD-MON-YYYY'),'0')) like ? )";
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
	
	  if( !army_course_name1.equals("")) { 
		  if (SearchValue.contains("where"))  {
			  SearchValue += " and  la.course_name = ? ";
			  } else { 
				  SearchValue += " and la.course_name = ?"; 
				  
			  } 
		  }
	  
	  
	  if( !army_course_div_grade1.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  ms.id::text = ?  ";	
			}
			else {
				SearchValue += " and  ms.id::text = ? ";
			}
		}
	  if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(la.date_of_completion as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(la.date_of_completion as date) <= cast(? as date)"; 
			  }
		}
	  
	 }
	 if (typeID.equals("2")) {
		 if(!Search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				
				SearchValue +="( upper(fv.unit_name) like ? or upper(fvm.unit_name) like ? or upper(div.unit_name) like ? "
						+ " or upper(bde.unit_name) like ? or upper(orb.unit_name) like ? or upper(comm.unit_sus_no) like ? or upper(comm.army_no) like ?"
						+ "	or upper(rk.rank) like ? or upper(comm.full_name) like ? or upper(la.course_name) like ?"
						+ "	or upper(ms.div) like ?  or upper(ltrim(TO_CHAR(la.date_of_completion,'DD-MON-YYYY'),'0')) like ? ) ";
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
			
			
			 if( !army_course_name1.equals("")) { 
				  if (SearchValue.contains("where"))  {
					  SearchValue += " and  la.course_name = ? ";
					  } else { 
						  SearchValue += " and la.course_name = ?"; 
						  
					  } 
				  }
			 
			 if( !army_course_div_grade1.equals("0")) {
				 if (SearchValue.contains("where")) {
						SearchValue += " and  ms.id::text = ?  ";	
					}
					else {
						SearchValue += " and  ms.id::text = ? ";
					}
				}
			 if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
					if (SearchValue.contains("where")) {
						  SearchValue +=  " and cast(la.date_of_completion as date) <= cast(? as date)"; 
					  } 
					else { 
						  SearchValue += " and cast(la.date_of_completion as date) <= cast(? as date)"; 
					  }
				}
	 }
	 
	
	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement
		  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String typeID, String army_course_name1, String army_course_div_grade1, String posn_date) {
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
//		if(!unit_name.equals("")) {
//			flag += 1;
//			stmt.setString(flag, unit_name.toUpperCase());
//			
//		}
		if(!unit_sus_no.equals("")) {
			flag += 1;
			stmt.setString(flag, unit_sus_no.toUpperCase());
			
		}
		
		if(!army_course_name1.equals("")) {
			flag += 1;
			stmt.setString(flag, army_course_name1);
			
		}
		
		if(!army_course_div_grade1.equals("0")) {
			flag += 1;
			stmt.setString(flag, army_course_div_grade1.toUpperCase());
			
		}
		if(!posn_date.equals("")) {
			flag += 1;
			stmt.setString(flag, posn_date);
			
		}
		

	}catch (Exception e) {}
	
	return stmt;
	
}






public String GenerateQueryWhere_SQL_1(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
		String unit_name,String unit_sus_no, String typeID, String army_course_name1, String army_course_div_grade1, String posn_date) {
 

	String SearchValue ="";
	
	if(!Search.equals("")) { // for Input Filter
		SearchValue =" and  ";
		
		SearchValue +="( upper(fv.unit_name) like ? or upper(fvm.unit_name) like ? or upper(div.unit_name) like ? "
				+ " or upper(bde.unit_name) like ? or upper(orb.unit_name) like ? or upper(comm.unit_sus_no) like ?"
				+ " or upper(la.course_name) like ?  ) ";
	}
	
	if( !cont_comd.equals("")) {
		if (SearchValue.contains("and")) {
			SearchValue += " and orb.form_code_control like ?  ";	
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
//			SearchValue += " and  orb.unit_name = ? ";	
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

	 if( !army_course_name1.equals("")) { 
		  if (SearchValue.contains("where"))  {
			  SearchValue += " and  la.course_name = ? ";
			  } else { 
				  SearchValue += " and la.course_name = ?"; 
				  
			  } 
		  }
	 
	 if( !army_course_div_grade1.equals("0")) {
		 if (SearchValue.contains("where")) {
				SearchValue += " and  ms.id::text = ?  ";	
			}
			else {
				SearchValue += " and  ms.id::text = ? ";
			}
		}
	 if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(la.date_of_completion as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(la.date_of_completion as date) <= cast(? as date)"; 
			  }
		}

	return SearchValue;
}

public PreparedStatement setQueryWhere_SQL(PreparedStatement
		  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String typeID, String army_course_name1, String army_course_div_grade1, String posn_date) {
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
//		if(!unit_name.equals("")) {
//			flag += 1;
//			stmt.setString(flag, unit_name.toUpperCase());
//			
//		}
		if(!unit_sus_no.equals("")) {
			flag += 1;
			stmt.setString(flag, unit_sus_no.toUpperCase());
			
		}
		if(!army_course_name1.equals("")) {
			flag += 1;
			stmt.setString(flag, army_course_name1);
			
		}
		
		if(!army_course_div_grade1.equals("0")) {
			flag += 1;
			stmt.setString(flag, army_course_div_grade1.toUpperCase());
			
		}
		if(!posn_date.equals("")) {
			flag += 1;
			stmt.setString(flag, posn_date);
			
		}

	}catch (Exception e) {}
	
	return stmt;
	
}


}



