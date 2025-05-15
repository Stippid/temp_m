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

public class Edn_Qual_Spouses_DaoImpl implements Edn_Qual_Spouses_Dao{
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    
    public List<Map<String, Object>> getsearch_qualification_spouses_table(int startPage, int pageLength, String search,
    		String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
    		String cont_div, String cont_bde, String unit_name, String unit_sus_no, String typeID, String examination) {
    	// TODO Auto-generated method stub) 

     String SearchValue = GenerateQueryWhere_SQL(search, cont_comd, cont_corps, cont_div, cont_bde,
    			 unit_name, unit_sus_no, typeID, examination);
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
    			
    		q= "select distinct orb.unit_name,\n"
    				+ "comm.unit_sus_no,comm.personnel_no,\n"
    				+ "rk.description,comm.name,fm.maiden_name,ep.examination_passed\n"
    				+ "from tb_psg_trans_proposed_comm_letter comm  \n"
    				+ "inner join tb_psg_census_family_married fm on fm.comm_id = comm.id \n"
    				+ "inner join tb_psg_census_spouse_qualification sq on sq.comm_id = comm.id\n"
    				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
    				+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
    				+ "inner join tb_psg_census_detail_p p on p.comm_id=comm.id and p.status ='1' \n"
    				+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
    				+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
    				+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
    				+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
    				+ "left join tb_psg_mstr_examination_passed ep on ep.id = sq.examination_pass  \n"
    				+ " WHERE comm.status in ('1','5') and fm.status='1' and fm.marital_status='8' and orb.status_sus_no='Active' and sq.status='1' "+SearchValue+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage  ;
    		
                        }
                         if (typeID.equals("2")) {
    	
                     		q ="select distinct   orb.unit_name,comm.unit_sus_no,comm.army_no,\n"
                     				+ "rk.rank,comm.full_name,fm.maiden_name,ep.examination_passed\n"
                     				+ "from tb_psg_census_jco_or_p comm    \n"
                     				+ "inner join tb_psg_census_family_married_jco fm on fm.jco_id = comm.id \n"
                     				+ "inner join tb_psg_census_spouse_qualification_jco sq on sq.jco_id = comm.id\n"
                     				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
                     				+ "INNER JOIN tb_psg_mstr_rank_jco  rk on comm.rank=rk.id\n"
                     				+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
                     				+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
                     				+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
                     				+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
                     				+ "left join tb_psg_mstr_examination_passed ep on ep.id = sq.examination_pass \n"
                     				+ "WHERE comm.status in ('1','5') and sq.status='1'and fm.status='1' and fm.marital_status='8' and orb.status_sus_no='Active' and sq.status='1' "+SearchValue+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage 	;
                                   
                         }
    		            
    		
    		
    		stmt=conn.prepareStatement(q);
    	    stmt = setQueryWhereClause_SQL1(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
    					 unit_name, unit_sus_no, typeID,examination);
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
    
    
public long getsearch_qualification_spouses_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
		String unit_name,String unit_sus_no, String typeID, String examination) {
 String SearchValue = GenerateQueryWhere_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde,
			 unit_name, unit_sus_no, typeID, examination); 
	int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		
			if (typeID.equals("1")) {	
		  q= "select count (app.*) from(select distinct orb.unit_name,\n"
		  		+ "comm.unit_sus_no,comm.personnel_no,\n"
		  		+ "rk.description,comm.name,fm.maiden_name,ep.examination_passed\n"
		  		+ "from tb_psg_trans_proposed_comm_letter comm  \n"
		  		+ "inner join tb_psg_census_family_married fm on fm.comm_id = comm.id \n"
		  		+ "inner join tb_psg_census_spouse_qualification sq on sq.comm_id = comm.id\n"
		  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
		  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
		  		+ "inner join tb_psg_census_detail_p p on p.comm_id=comm.id and p.status ='1' \n"
		  		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
		  		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
		  		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
		  		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
		  		+ "left join tb_psg_mstr_examination_passed ep on ep.id = sq.examination_pass  \n"
		  		+ " WHERE comm.status in ('1','5') and fm.status='1' and fm.marital_status='8' and orb.status_sus_no='Active' and sq.status='1' "+SearchValue+") app ";
			}
			if (typeID.equals("2")) {
			q ="select count (app.*) from(select distinct   orb.unit_name,comm.unit_sus_no,comm.army_no,\n"
					+ "rk.rank,comm.full_name,fm.maiden_name,ep.examination_passed\n"
					+ "from tb_psg_census_jco_or_p comm    \n"
					+ "inner join tb_psg_census_family_married_jco fm on fm.jco_id = comm.id\n"
					+ "inner join tb_psg_census_spouse_qualification_jco sq on sq.jco_id = comm.id \n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN tb_psg_mstr_rank_jco  rk on comm.rank=rk.id\n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ "left join tb_psg_mstr_examination_passed ep on ep.id = sq.examination_pass \n"
					+ "WHERE comm.status in ('1','5') and sq.status='1'and fm.status='1' and fm.marital_status='8' and orb.status_sus_no='Active' and sq.status='1' "+SearchValue+") app "	;
				
			}
				 
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, typeID, examination);
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



public List<Map<String, Object>> getsearch_qualification_spouses_summary_table(int startPage, int pageLength, String search,
		String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
		String cont_div, String cont_bde, String unit_name, String unit_sus_no, String typeID, String examination) {
	// TODO Auto-generated method stub) 

 String SearchValue = GenerateQueryWhere_SQL_1(search, cont_comd, cont_corps, cont_div, cont_bde,
			 unit_name, unit_sus_no, typeID, examination);
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
		q= "select distinct orb.unit_name,comm.unit_sus_no,ep.examination_passed,\n"
				+ " count (comm.id) as officer,\n"
				+ " count (comm.id) as total\n"
				+ "from tb_psg_trans_proposed_comm_letter comm      \n"
				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
				+ "inner join tb_psg_census_family_married fm on fm.comm_id = comm.id \n"
				+ "inner join tb_psg_census_spouse_qualification sq on sq.comm_id = comm.id\n"
				+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
				+ "inner join tb_psg_census_detail_p p on p.comm_id=comm.id and p.status ='1'\n"
				+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
				+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
				+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
				+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
				+ "left join tb_psg_mstr_examination_passed ep on ep.id = sq.examination_pass \n"
				+ "WHERE comm.status in ('1','5') and fm.status='1' and orb.status_sus_no='Active' and fm.marital_status='8' and sq.status='1'\n"
				+ ""+SearchValue+" group by 1,2,3"+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
				
                      }
             if (typeID.equals("2")) {
	
	    q= " select distinct   orb.unit_name,comm.unit_sus_no,ep.examination_passed,\n"
	    		+ "count (sq.jco_id) FILTER (where comm.category ='JCO') as jco,\n"
	    		+ "  count (sq.jco_id) FILTER (where comm.category ='OR') as ors,\n"
	    		+ " count (comm.id) as total\n"
	    		+ "from tb_psg_census_jco_or_p comm \n"
	    		+ "inner join tb_psg_census_family_married_jco fm on fm.jco_id = comm.id \n"
	    		+ "inner join tb_psg_census_spouse_qualification_jco sq on sq.jco_id = comm.id\n"
	    		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
	    		+ "INNER JOIN tb_psg_mstr_rank_jco  rk on comm.rank=rk.id\n"
	    		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
	    		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
	    		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
	    		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
	    		+ "left join tb_psg_mstr_examination_passed ep on ep.id = sq.examination_pass \n"
	    		+ "WHERE comm.status in ('1','5') and fm.marital_status='8' and orb.status_sus_no='Active' and sq.status='1'\n"
	    		+ "and fm.status='1' "+SearchValue+" group by  1,2,3"+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage  ;
            	 
            	 
                     }
		
		stmt=conn.prepareStatement(q);
	    stmt = setQueryWhere_SQL(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, typeID, examination);
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


public long getsearch_qualification_spouses_summary_tablecount(String Search, String orderColunm, String orderType,
		HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
		String unit_name, String unit_sus_no, String typeID, String examination) {
 String SearchValue = GenerateQueryWhere_SQL_1(Search, cont_comd, cont_corps, cont_div, cont_bde,
			 unit_name, unit_sus_no, typeID,examination); 
	int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		
			 if (typeID.equals("1")) {
				
		  q= "select count (app.*) from(select distinct orb.unit_name,comm.unit_sus_no,ep.examination_passed,\n"
		  		+ " count (comm.id) as officer,\n"
		  		+ " count (comm.id) as total\n"
		  		+ "from tb_psg_trans_proposed_comm_letter comm      \n"
		  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
		  		+ "inner join tb_psg_census_family_married fm on fm.comm_id = comm.id \n"
		  		+ "inner join tb_psg_census_spouse_qualification sq on sq.comm_id = comm.id\n"
		  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
		  		+ "inner join tb_psg_census_detail_p p on p.comm_id=comm.id and p.status ='1'\n"
		  		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
		  		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
		  		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
		  		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
		  		+ "left join tb_psg_mstr_examination_passed ep on ep.id = sq.examination_pass \n"
		  		+ "WHERE comm.status in ('1','5') and fm.status='1' and orb.status_sus_no='Active' and fm.marital_status='8' and sq.status='1'\n"
		  		+ ""+SearchValue+" group by 1,2,3) app ";
				 
		 
		}
		if (typeID.equals("2")) {
			
			q= " select count (app.*) from( select distinct   orb.unit_name,comm.unit_sus_no,ep.examination_passed,\n"
					+ "count (sq.jco_id) FILTER (where comm.category ='JCO') as jco,\n"
					+ "  count (sq.jco_id) FILTER (where comm.category ='OR') as ors,\n"
					+ " count (comm.id) as total\n"
					+ "from tb_psg_census_jco_or_p comm \n"
					+ "inner join tb_psg_census_family_married_jco fm on fm.jco_id = comm.id \n"
					+ "inner join tb_psg_census_spouse_qualification_jco sq on sq.jco_id = comm.id\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN tb_psg_mstr_rank_jco  rk on comm.rank=rk.id\n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ "left join tb_psg_mstr_examination_passed ep on ep.id = sq.examination_pass \n"
					+ "WHERE comm.status in ('1','5') and fm.marital_status='8' and orb.status_sus_no='Active' and sq.status='1'\n"
					+ "and fm.status='1' "+SearchValue+" group by  1,2,3) app" ;
			
			
		}
				 
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhere_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, typeID, examination);
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
		String unit_name,String unit_sus_no, String typeID,String examination) {
 
	
	String SearchValue ="";
	 
	 if (typeID.equals("1")) {
	 
	if(!Search.equals("")) { // for Input Filter
		SearchValue =" and  ";
		
		SearchValue +="( upper(orb.unit_name) like ? or upper(comm.unit_sus_no) like ? or upper(comm.personnel_no) like ? "
				+ " or upper(rk.description) like ? or upper(comm.name) like ? or upper(fm.maiden_name) like ? or upper(ep.examination_passed) like ? )";
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
	
	if( !examination.equals("0")) {
		if (SearchValue.contains("where")) {
			SearchValue += " and  ep.id::text = ?  ";	
		}
		else {
			SearchValue += " and  ep.id::text = ? ";
		}
	}
	  
	  
	  
	  
	 }
	 if (typeID.equals("2")) {
		 if(!Search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				
				SearchValue +="( upper(orb.unit_name) like ? or upper(comm.unit_sus_no) like ? or upper(comm.army_no) like ? "
						+ "	or upper(rk.rank) like ? or upper(comm.full_name) like ? or upper(fm.maiden_name) like ? or upper(ep.examination_passed) like ? ) ";
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
			
			if( !examination.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  ep.id::text = ?  ";	
				}
				else {
					SearchValue += " and  ep.id::text = ? ";
				}
			}
			
			 
	 }
	 
	
	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement
		  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String typeID, String examination) {
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
		if(!examination.equals("")) {
			flag += 1;
			stmt.setString(flag, examination.toUpperCase());
			
		}

		
		

	}catch (Exception e) {}
	
	return stmt;
	
}











public String GenerateQueryWhere_SQL_1(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
		String unit_name,String unit_sus_no, String typeID, String examination) {
 

	String SearchValue ="";
	
	if(!Search.equals("")) { // for Input Filter
		SearchValue =" and  ";
		
		SearchValue +="( upper(orb.unit_name) like ? or upper(comm.unit_sus_no) like ? or upper(ep.examination_passed) like ?  ) ";
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

	if( !examination.equals("0")) {
		if (SearchValue.contains("where")) {
			SearchValue += " and  ep.id::text = ?  ";	
		}
		else {
			SearchValue += " and  ep.id::text = ? ";
		}
	}
	

	return SearchValue;
}

public PreparedStatement setQueryWhere_SQL(PreparedStatement
		  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String typeID, String examination) {
	int flag = 0;
	try {
		if(!Search.equals("")) {			
			
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
		if(!examination.equals("")) {
			flag += 1;
			stmt.setString(flag, examination.toUpperCase());
			
		}

	}catch (Exception e) {}
	
	return stmt;
	
}



}


