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


public class List_of_Married_and_Unmarried_Personnel_of_Units_Est_DaoImpl implements List_of_Married_and_Unmarried_Personnel_of_Units_Est_Dao{

	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public List<Map<String, Object>> List_of_Married_and_Unmarried_Personnel_of_Units_EstList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,String off_cat,String marital_status, String posn_date)
	{
	
	 String SearchValue = GenerateQueryWhereClause_SQL(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no,off_cat, marital_status, posn_date); 
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
				
			
			q="select distinct orb.unit_name,comm.unit_sus_no,\n"
					+ "comm.personnel_no,rk.description,comm.name,m.marital_name,\n"
					+ "date_part('year', age(comm.date_of_birth)) as age\n"
					+ "from  tb_psg_trans_proposed_comm_letter comm \n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
					+ "left join tb_psg_census_detail_p p on comm.id=p.comm_id\n"
					+ "inner join tb_psg_mstr_marital_status m on m.marital_id=p.marital_status\n"
					+ "left join all_fmn_view fv   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					+ "left join all_fmn_view fvm   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					+ "left join all_fmn_view bde   on orb.sus_no = comm.unit_sus_no   and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ "where comm.status in ('1','5') and  p.status = 1 and orb.status_sus_no='Active' \n"
					+SearchValue +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage  ;
			}		
			if (off_cat.equals("2")) {
				
				q = "select orb.unit_name,comm.unit_sus_no,\n"
						+ "comm.army_no,rk.rank,comm.full_name,m.marital_name,\n"
						+ "date_part('year', age(comm.date_of_birth)) as age\n"
						+ "from  tb_psg_census_jco_or_p comm \n"
						+ "inner join tb_psg_mstr_marital_status m on m.marital_id=comm.marital_status\n"
						+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
						+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\n"
						+ "left join all_fmn_view fv   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
						+ "left join all_fmn_view fvm   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
						+ "left join all_fmn_view div   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
						+ "left join all_fmn_view bde   on orb.sus_no = comm.unit_sus_no   and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
						+ "where comm.status in ('1','5') and  comm.status = 1 and orb.status_sus_no='Active' \n"
				+SearchValue + " ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage  ;
			
			}
			
			stmt=conn.prepareStatement(q);
		
			stmt = setQueryWhereClause_SQL(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no,off_cat, marital_status, posn_date);
			
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
	
	
	public long List_of_Married_and_Unmarried_Personnel_of_Units_Est_Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no,String off_cat,String marital_status, String posn_date) {
	 String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps,cont_div, cont_bde,
				 unit_name, unit_sus_no,off_cat,marital_status, posn_date); 
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
			
				
				if (off_cat.equals("1")) {
			
				  q="select count(app.*) from(\r\n"
					  		+ "select distinct orb.unit_name,comm.unit_sus_no,\n"
					  		+ "comm.personnel_no,rk.description,comm.name,m.marital_name,\n"
					  		+ "date_part('year', age(comm.date_of_birth)) as age\n"
					  		+ "from  tb_psg_trans_proposed_comm_letter comm \n"
					  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
					  		+ "left join tb_psg_census_detail_p p on comm.id=p.comm_id\n"
					  		+ "inner join tb_psg_mstr_marital_status m on m.marital_id=p.marital_status\n"
					  		+ "left join all_fmn_view fv   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					  		+ "left join all_fmn_view fvm   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					  		+ "left join all_fmn_view div   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					  		+ "left join all_fmn_view bde   on orb.sus_no = comm.unit_sus_no   and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					  		+ "where comm.status in ('1','5') and  p.status = 1 and orb.status_sus_no='Active' \n"
					  		+SearchValue+" ) app" ;
				}
				if (off_cat.equals("2")) {
					
					 q="select count(app.*) from(\r\n"
						  		+ "select fv.unit_name as cmd_unit, fvm.unit_name as corp_unit,\n"
						  		+ "div.unit_name as div_unit,bde.unit_name as bde_unit,orb.unit_name,comm.unit_sus_no,\n"
						  		+ "comm.army_no,rk.rank,comm.full_name,m.marital_name,\n"
								+ "date_part('year', age(comm.date_of_birth)) as age\n"
						  		+ "from  tb_psg_census_jco_or_p comm \n"
						  		+ "inner join tb_psg_mstr_marital_status m on m.marital_id=comm.marital_status\n"
						  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
						  		+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\n"
						  		+ "left join all_fmn_view fv   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
						  		+ "left join all_fmn_view fvm   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
						  		+ "left join all_fmn_view div   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
						  		+ "left join all_fmn_view bde   on orb.sus_no = comm.unit_sus_no   and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
						  		+ "where comm.status in ('1','5') and  comm.status = 1 and orb.status_sus_no='Active' \n"
						  		+SearchValue+" ) app" ;
					
				}
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no,off_cat, marital_status, posn_date);
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
	
	
	public List<Map<String, Object>> List_of_Married_and_Unmarried_Personnel_of_Units_Est_Summary_List(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			String off_cat,String marital_status, String posn_date)
	{
	 String SearchValue = GenerateQueryWhereClause_SQL_Summary(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no,off_cat, marital_status, posn_date); 
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
				
			
			q="select distinct fv.unit_name as cmd_unit,\n"
					+ "fvm.unit_name as corp_unit,\n"
					+ "div.unit_name as div_unit,\n"
					+ "bde.unit_name as bde_unit, orb.unit_name,comm.unit_sus_no,m.marital_name,\n"
					+ "count(p.comm_id) as officer,\n"
					+ "count(p.comm_id) as total\n"
					+ "from  tb_psg_trans_proposed_comm_letter comm \n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
					+ "left join tb_psg_census_detail_p p on comm.id=p.comm_id and  p.status = 1\n"
					+ "inner join tb_psg_mstr_marital_status m on m.marital_id=p.marital_status\n"
					+ "left join all_fmn_view fv   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					+ "left join all_fmn_view fvm   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					+ "left join all_fmn_view bde   on orb.sus_no = comm.unit_sus_no   and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ "where comm.status in ('1','5') and orb.status_sus_no='Active' \n"
					+SearchValue + "group by 1,2,3,4,5,6,7  "+ " ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}		
			if (off_cat.equals("2")) {
			
				q = " select distinct \n"
						+ "fv.unit_name as cmd_unit,\n"
						+ "fvm.unit_name as corp_unit,\n"
						+ "div.unit_name as div_unit,\n"
						+ "bde.unit_name as bde_unit,orb.unit_name,comm.unit_sus_no,m.marital_name,\n"
						+ "count(comm.id) FILTER (where comm.category='JCO'  )as jco,\n"
						+ "count(comm.id) FILTER (where comm.category='OR'  )as ors,\n"
						+ "count(comm.id) as total\n"
						+ "from  tb_psg_census_jco_or_p comm \n"
						+ "inner join tb_psg_mstr_marital_status m on m.marital_id=comm.marital_status\n"
						+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
						+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\n"
						+ "left join all_fmn_view fv   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
						+ "left join all_fmn_view fvm   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
						+ "left join all_fmn_view div   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
						+ "left join all_fmn_view bde   on orb.sus_no = comm.unit_sus_no   and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
						+ "where comm.status in ('1','5') and  comm.status = 1 and orb.status_sus_no='Active' \n"
				+SearchValue + "group by 1,2,3,4,5,6,7  " +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage  ;
			}
			
			stmt=conn.prepareStatement(q);
		
			stmt = setQueryWhereClause_SQL_Summary(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no,off_cat, marital_status, posn_date);
			
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
	
	 
	 public long List_of_Married_and_Unmarried_Personnel_of_Units_Est_Summary_Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
				String unit_name,String unit_sus_no,String off_cat,String marital_status, String posn_date) {
		 
		 String SearchValue = GenerateQueryWhereClause_SQL_Summary(Search, cont_comd, cont_corps,cont_div, cont_bde,
					 unit_name, unit_sus_no,off_cat, marital_status, posn_date); 
			int total = 0;
				String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
				String q = null;
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
				
					
					if (off_cat.equals("1")) {
				
				q="select count(app.*) from(\r\n"
					+ "select distinct fv.unit_name as cmd_unit,\n"
					+ "fvm.unit_name as corp_unit,\n"
					+ "div.unit_name as div_unit,\n"
					+ "bde.unit_name as bde_unit, orb.unit_name,comm.unit_sus_no,m.marital_name,\n"
					+ "count(p.comm_id) as officer,\n"
					+ "count(p.comm_id) as total\n"
					+ "from  tb_psg_trans_proposed_comm_letter comm \n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
					+ "left join tb_psg_census_detail_p p on comm.id=p.comm_id and  p.status = 1 \n"
					+ "inner join tb_psg_mstr_marital_status m on m.marital_id=p.marital_status\n"
					+ "left join all_fmn_view fv   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					+ "left join all_fmn_view fvm   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					+ "left join all_fmn_view bde   on orb.sus_no = comm.unit_sus_no   and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ "where comm.status in ('1','5') and orb.status_sus_no='Active' " + SearchValue +"\n" 
			  		+"group by 1,2,3,4,5,6,7\n"
						  				+ " ) app" ;
					}
					if (off_cat.equals("2")) {
						
					 q="select count(app.*) from(\r\n"
				  		+ "select distinct \n"
				  		+ "fv.unit_name as cmd_unit,\n"
				  		+ "fvm.unit_name as corp_unit,\n"
				  		+ "div.unit_name as div_unit,\n"
				  		+ "bde.unit_name as bde_unit,orb.unit_name,comm.unit_sus_no,m.marital_name,\n"
				  		+ "count(comm.id) FILTER (where comm.category='JCO'  )as jco,\n"
				  		+ "count(comm.id) FILTER (where comm.category='OR'  )as ors,\n"
				  		+ "count(comm.id) as total\n"
				  		+ "from  tb_psg_census_jco_or_p comm \n"
				  		+ "inner join tb_psg_mstr_marital_status m on m.marital_id=comm.marital_status\n"
				  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
				  		+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id\n"
				  		+ "left join all_fmn_view fv   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
				  		+ "left join all_fmn_view fvm   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
				  		+ "left join all_fmn_view div   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
				  		+ "left join all_fmn_view bde   on orb.sus_no = comm.unit_sus_no   and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
				  		+ "where comm.status in ('1','5') and  comm.status = 1 and orb.status_sus_no='Active' "+SearchValue+" \n"
				  		+"group by 1,2,3,4,5,6,7 "
				  				+ " ) app" ;
						
					}
					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL_Summary(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
							 unit_name, unit_sus_no,off_cat, marital_status, posn_date);
					
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
			String unit_name,String unit_sus_no,String off_cat,String marital_status, String posn_date) {
	 
	
		String SearchValue ="";
		
		if (off_cat.equals("1")) {
		if(!Search.equals("")) { // for Input Filter 7
			SearchValue =" and  ";
			SearchValue +="( upper(orb.unit_name) like ? "
					+ " or upper(comm.unit_sus_no) like ? "
					+ " or upper(comm.personnel_no) like ? "
					+ " or upper(comm.name) like ? "
					+ " or upper(rk.description) like ? "
					+ " or upper(m.marital_name) like ? "
					+ " or date_part('year', age(comm.date_of_birth))::text like ?) ";
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
		if( !marital_status.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and m.marital_id::text = ?  ";	
			}
			else {
				SearchValue += " and  m.marital_id::text = ? ";
			}
		}
		
		if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(p.created_date as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(p.created_date as date) <= cast(? as date)"; 
			  }
		}
		
		}
		if (off_cat.equals("2")) {
			if(!Search.equals("")) { // for Input Filter 7
				SearchValue =" and  ";
				SearchValue +="( upper(orb.unit_name) like ? "
						+ " or upper(comm.unit_sus_no) like ? "
						+ " or upper(comm.army_no) like ?"
						+ " or upper(comm.full_name) like ? "
						+ " or upper(rk.rank) like ? "
						+ " or upper(m.marital_name) like ? "
						+ " or date_part('year', age(comm.date_of_birth))::text like ? ) ";
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
			
			if( !marital_status.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and m.marital_id::text = ?  ";	
				}
				else {
					SearchValue += " and  m.marital_id::text = ? ";
				}
			}
			
			if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(comm.created_date as date) <= cast(? as date)"; 
				  } 
				else { 
					  SearchValue += " and cast(comm.created_date as date) <= cast(? as date)"; 
				  }
			}
			
		}
			
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement
			  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
				String unit_name,String unit_sus_no,String off_cat,String marital_status, String posn_date){
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
				stmt.setString(flag, Search+"%");
				
//				flag += 1;
//				stmt.setString(flag, Search.toUpperCase()+"%");
				
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
//			}
			if(!unit_sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_sus_no.toUpperCase());
			}
			if(!marital_status.equals("0")) {
				flag += 1;
				stmt.setString(flag, marital_status);
			}
			if(!posn_date.equals("")) {
				flag += 1;
				stmt.setString(flag, posn_date);
				
			}
			

		}catch (Exception e) {}
		
		return stmt;
		
	}

	  
		
	 
	 
	 
	 public String GenerateQueryWhereClause_SQL_Summary(String Search,String cont_comd,String cont_corps,
			 String cont_div,String cont_bde,String unit_name,String unit_sus_no,String off_cat,
			 String marital_status, String posn_date) {
		 
		
			String SearchValue ="";
			
			if (off_cat.equals("1")) {
			if(!Search.equals("")) { // for Input Filter 6
				SearchValue =" and  ";
				SearchValue +="( upper(fv.unit_name) like ? "
						+ " or upper(fvm.unit_name) like ? "
						+ " or upper(div.unit_name) like ? "
						+ " or upper(bde.unit_name) like ? "
						+ " or upper(orb.unit_name) like ? "
						+ " or upper(comm.unit_sus_no) like ?"
						+ " or upper(m.marital_name) like ? )";
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
			
			if( !marital_status.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and m.marital_id::text = ?  ";	
				}
				else {
					SearchValue += " and  m.marital_id::text = ? ";
				}
			}
			
			if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(comm.created_date as date) <= cast(? as date)"; 
				  } 
				else { 
					  SearchValue += " and cast(comm.created_date as date) <= cast(? as date)"; 
				  }
			}
			
			}
			
			if (off_cat.equals("2")) {
				if(!Search.equals("")) { // for Input Filter 6
					SearchValue =" and  ";
					SearchValue +="( upper(fv.unit_name) like ?"
							+ " or upper(fvm.unit_name) like ? "
							+ " or upper(div.unit_name) like ? "
							+ " or upper(bde.unit_name) like ? "
							+ " or upper(orb.unit_name) like ?  "
							+ " or upper(comm.unit_sus_no) like ?"
							+ " or upper(m.marital_name) like ? )";
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
				
				if( !marital_status.equals("0")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and m.marital_id::text = ?  ";	
					}
					else {
						SearchValue += " and  m.marital_id::text = ? ";
					}
				}
				
				if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
					if (SearchValue.contains("where")) {
						  SearchValue +=  " and cast(comm.created_date as date) <= cast(? as date)"; 
					  } 
					else { 
						  SearchValue += " and cast(comm.created_date as date) <= cast(? as date)"; 
					  }
				}
				
				}
			
			return SearchValue;
		}
		
		public PreparedStatement setQueryWhereClause_SQL_Summary(PreparedStatement
				  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String off_cat,String marital_status, String posn_date) {
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
//				if(!unit_name.equals("")) {
//					flag += 1;
//					stmt.setString(flag, unit_name.toUpperCase());
//					
//				}
				if(!unit_sus_no.equals("")) {
					flag += 1;
					stmt.setString(flag, unit_sus_no.toUpperCase());
					
				}
				if(!marital_status.equals("0")) {
					flag += 1;
					stmt.setString(flag, marital_status);
				}
				if(!posn_date.equals("")) {
					flag += 1;
					stmt.setString(flag, posn_date);
					
				}

			}catch (Exception e) {}
			
			return stmt;
			
		}

	 }