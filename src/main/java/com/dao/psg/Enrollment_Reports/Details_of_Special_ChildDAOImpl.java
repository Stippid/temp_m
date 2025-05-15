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

public class Details_of_Special_ChildDAOImpl implements Details_of_Special_ChildDAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public List<Map<String, Object>> getspecial_child_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde, 
			String unit_name, String unit_sus_no, String from_date, String to_date, String posn_date, String typeID, String rankID, String rankjcoID) 
	{
	 String SearchValue = GenerateQueryWhereClause_SQL(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, rankID, rankjcoID); 
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
			q="select orb.unit_name,comm.unit_sus_no,\r\n"
					+ "comm.personnel_no,rk.description,comm.name,cen.name as child_name,\r\n"
					+ "date_part('year', age(cen.date_of_birth)) as age,\r\n"
					+ " CASE WHEN (cen.relationship  = '1')  THEN '7'\r\n"
					+ "  WHEN (cen.relationship  = '2')  THEN '6' END AS Gender,g.gender_name\r\n"
					+ "from tb_psg_trans_proposed_comm_letter comm\r\n"
					+ "inner join tb_psg_census_children cen on cen.comm_id=comm.id \r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id \r\n"
					+ "inner join tb_psg_mstr_gender g on case  WHEN (cen.relationship   = '1')   THEN   g.id = '7'\r\n"
					+ " WHEN (cen.relationship   = '2')   THEN   g.id = '6' END \r\n"
					+ " where comm.status in ('1','5') and cen.type='Yes' and orb.status_sus_no='Active' \r\n" 
					+ SearchValue+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+ " OFFSET "+startPage+"";
			}
			if (typeID.equals("2")) {
				q="select  orb.unit_name,comm.unit_sus_no,\r\n"
						+ "comm.army_no,rk.rank,comm.full_name,cen.name,\r\n"
						+ "date_part('year', age(cen.date_of_birth)) as age,\r\n"
						+ "CASE WHEN (cen.relationship  = '1')  THEN '7'\r\n"
						+ "WHEN (cen.relationship  = '2')  THEN '6' END AS Gender,g.gender_name\r\n"
						+ "from tb_psg_census_jco_or_p comm\r\n"
						+ "inner join tb_psg_census_children_jco cen  on cen.jco_id=comm.id \r\n"
						+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
						+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id \r\n"
						+ "inner join tb_psg_mstr_gender g on case  WHEN (cen.relationship   = '1')   THEN   g.id = '7'\r\n"
						+ " WHEN (cen.relationship   = '2')   THEN   g.id = '6' END \r\n"
						+ "where comm.status in ('1','5') and cen.type='Yes' and orb.status_sus_no='Active' \r\n" 
						+ SearchValue+ " ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			stmt=conn.prepareStatement(q);
		
			stmt = setQueryWhereClause_SQL(stmt,search, cont_comd, cont_corps,cont_div, cont_bde,unit_name, unit_sus_no, 
					/*user_role_id, */from_date, to_date, posn_date, typeID, rankID, rankjcoID);
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
	
	
	public long getspecial_child_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,
			String cont_comd, String cont_corps, String cont_div, String cont_bde,String unit_name,String unit_sus_no, String from_date, 
			String to_date, String posn_date, String typeID, String rankID, String rankjcoID) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps,cont_div, cont_bde,
				 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, rankID, rankjcoID); 
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				if (typeID.equals("1")) {
			  q="select count(app.*) from(select orb.unit_name,comm.unit_sus_no,\r\n"
			  		+ "comm.personnel_no,rk.description,comm.name,cen.name as child_name,\r\n"
			  		+ "date_part('year', age(cen.date_of_birth)) as age,\r\n"
			  		+ " CASE WHEN (cen.relationship  = '1')  THEN '7'\r\n"
			  		+ "  WHEN (cen.relationship  = '2')  THEN '6' END AS Gender,g.gender_name\r\n"
			  		+ "from tb_psg_trans_proposed_comm_letter comm\r\n"
			  		+ "inner join tb_psg_census_children cen on cen.comm_id=comm.id \r\n"
			  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
			  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id \r\n"
			  		+ "inner join tb_psg_mstr_gender g on case  WHEN (cen.relationship   = '1')   THEN   g.id = '7'\r\n"
			  		+ " WHEN (cen.relationship   = '2')   THEN   g.id = '6' END \r\n"
			  		+ " where comm.status in ('1','5') and cen.type='Yes' and orb.status_sus_no='Active' "+SearchValue+" ) app" ;

				}
				if (typeID.equals("2")) {
					 q="select count(app.*) from(select  orb.unit_name,comm.unit_sus_no,\r\n"
					 		+ "comm.army_no,rk.rank,comm.full_name,cen.name,\r\n"
					 		+ "date_part('year', age(cen.date_of_birth)) as age,\r\n"
					 		+ "CASE WHEN (cen.relationship  = '1')  THEN '7'\r\n"
					 		+ "WHEN (cen.relationship  = '2')  THEN '6' END AS Gender,g.gender_name\r\n"
					 		+ "from tb_psg_census_jco_or_p comm\r\n"
					 		+ "inner join tb_psg_census_children_jco cen  on cen.jco_id=comm.id \r\n"
					 		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
					 		+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id \r\n"
					 		+ "inner join tb_psg_mstr_gender g on case  WHEN (cen.relationship   = '1')   THEN   g.id = '7'\r\n"
					 		+ " WHEN (cen.relationship   = '2')   THEN   g.id = '6' END \r\n"
					 		+ "where comm.status in ('1','5') and cen.type='Yes' and orb.status_sus_no='Active' \r\n"
								+ " "+SearchValue+" ) app"  ;
				}
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, rankID, rankjcoID);
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
	
	
	public List<Map<String, Object>> getspecial_child_tablesum(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String from_date, String to_date, String posn_date, String typeID, String rankID, String rankjcoID) 
	{
	 String SearchValue = GenerateQueryWhereClause_SQLsum(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, rankID, rankjcoID); 
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
			q=" select distinct orb.unit_name, comm.unit_sus_no,\r\n"
					+ "CASE\r\n"
					+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 0 and 5) THEN '0 - 5 years'\r\n"
					+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 6 and 10) THEN '6 - 10 years'\r\n"
					+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 11 and 15) THEN '11 - 15 years'\r\n"
					+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 16 and 20) THEN '16 - 20 years'\r\n"
					+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 21 and 25) THEN '21 - 25 years'\r\n"
					+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) > 25) THEN 'above 25' END AS Age_wise,\r\n"
					+ "count(comm.id)  FILTER (where cen.relationship='2') as officer_male,\r\n"
					+ "count(comm.id)  FILTER (where cen.relationship='1')  as officer_female,\r\n"
					+ "count(cen.comm_id) as total\r\n"
					+ "from tb_psg_trans_proposed_comm_letter comm\r\n"
					+ "inner join tb_psg_census_children cen on cen.comm_id=comm.id \r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id \r\n"
					+ "  where comm.status in ('1','5') and cen.type='Yes' and orb.status_sus_no='Active' \r\n"
					+ "  "+SearchValue+" group by 1,2,3  "+ " ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			
			stmt=conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQLsum(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, rankID, rankjcoID);
			 
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
	
	
	
	public long getspecial_child_tablecountsum(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String from_date, String to_date, String posn_date, String typeID, String rankID, String rankjcoID) {
	 String SearchValue = GenerateQueryWhereClause_SQLsum(Search, cont_comd, cont_corps,cont_div, cont_bde,
				 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, rankID, rankjcoID); 
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				if (typeID.equals("1")) {
			  q="select count(app.*) from(select distinct orb.unit_name, comm.unit_sus_no,\r\n"
			  		+ "CASE\r\n"
			  		+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 0 and 5) THEN '0 - 5 years'\r\n"
			  		+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 6 and 10) THEN '6 - 10 years'\r\n"
			  		+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 11 and 15) THEN '11 - 15 years'\r\n"
			  		+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 16 and 20) THEN '16 - 20 years'\r\n"
			  		+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 21 and 25) THEN '21 - 25 years'\r\n"
			  		+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) > 25) THEN 'above 25' END AS Age_wise,\r\n"
			  		+ "count(comm.id)  FILTER (where cen.relationship='2') as officer_male,\r\n"
			  		+ "count(comm.id)  FILTER (where cen.relationship='1')  as officer_female,\r\n"
			  		+ "count(cen.comm_id) as total\r\n"
			  		+ "from tb_psg_trans_proposed_comm_letter comm\r\n"
			  		+ "inner join tb_psg_census_children cen on cen.comm_id=comm.id \r\n"
			  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
			  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id \r\n"
			  		+ "  where comm.status in ('1','5') and cen.type='Yes' and orb.status_sus_no='Active' "+SearchValue+"  group by 1,2,3\r\n"
			  		+  ") app";

				}
				
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQLsum(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, rankID, rankjcoID);
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
	
	
	
	public List<Map<String, Object>> getspecial_child_jco_tablesum(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String from_date, String to_date, String posn_date, String typeID, String rankID, String rankjcoID) 
	{
		 
	 String SearchValue = GenerateQueryWhereClause_SQLsum(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, rankID, rankjcoID); 
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
			
			
			if (typeID.equals("2")) {
				q=" select distinct orb.unit_name, comm.unit_sus_no,\r\n"
						+ "CASE\r\n"
						+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 0 and 5) THEN '0 - 5 years'\r\n"
						+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 6 and 10) THEN '6 - 10 years'\r\n"
						+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 11 and 15) THEN '11 - 15 years'\r\n"
						+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 16 and 20) THEN '16 - 20 years'\r\n"
						+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 21 and 25) THEN '21 - 25 years'\r\n"
						+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) > 25) THEN 'above 25' END AS Age_wise,\r\n"
						+ "count(comm.id)  FILTER (where comm.category='JCO'and cen.relationship='2') as  jco_male,\r\n"
						+ "count(comm.id)  FILTER (where comm.category='JCO'and cen.relationship='1') as  jco_female,\r\n"
						+ "count(comm.id)  FILTER (where comm.category='OR' and cen.relationship='2') as  ors_male,\r\n"
						+ "count(comm.id)  FILTER (where comm.category='OR' and cen.relationship='1') as  ors_female,\r\n"
						+ "count(cen.jco_id) as total\r\n"
						+ "from tb_psg_census_jco_or_p comm\r\n"
						+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \r\n"
						+ "inner join tb_psg_census_children_jco cen  on cen.jco_id=comm.id \r\n"
						+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id \r\n"
						+ "  where comm.status in ('1','5') and orb.status_sus_no='Active' and cen.type='Yes' \r\n"
						+SearchValue+" group by 1,2,3  " +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			stmt=conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQLsum(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, rankID, rankjcoID);
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
	
	
	
	public long getspecial_child_jco_tablecountsum(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no, String from_date, String to_date, String posn_date, String typeID, String rankID, String rankjcoID) {
	 String SearchValue = GenerateQueryWhereClause_SQLsum(Search, cont_comd, cont_corps,cont_div, cont_bde,
				 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, rankID, rankjcoID); 
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				
				if (typeID.equals("2")) {
					 q="select count(app.*) from(select distinct orb.unit_name, comm.unit_sus_no,\r\n"
					 		+ "CASE\r\n"
					 		+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 0 and 5) THEN '0 - 5 years'\r\n"
					 		+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 6 and 10) THEN '6 - 10 years'\r\n"
					 		+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 11 and 15) THEN '11 - 15 years'\r\n"
					 		+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 16 and 20) THEN '16 - 20 years'\r\n"
					 		+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) between 21 and 25) THEN '21 - 25 years'\r\n"
					 		+ "WHEN (date_part('year',age(now(),cen.date_of_birth)) > 25) THEN 'above 25' END AS Age_wise,\r\n"
					 		+ "count(comm.id)  FILTER (where comm.category='JCO'and cen.relationship='2') as  jco_male,\r\n"
					 		+ "count(comm.id)  FILTER (where comm.category='JCO'and cen.relationship='1') as  jco_female,\r\n"
					 		+ "count(comm.id)  FILTER (where comm.category='OR' and cen.relationship='2') as  ors_male,\r\n"
					 		+ "count(comm.id)  FILTER (where comm.category='OR' and cen.relationship='1') as  ors_female,\r\n"
					 		+ "count(cen.jco_id) as total\r\n"
					 		+ "from tb_psg_census_jco_or_p comm\r\n"
					 		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no and orb.status_sus_no='Active'\r\n"
					 		+ "inner join tb_psg_census_children_jco cen  on cen.jco_id=comm.id and cen.type='Yes'\r\n"
					 		+ "INNER JOIN tb_psg_mstr_rank_jco rk on comm.rank=rk.id \r\n"
					 		+ "  where comm.status in ('1','5')   "+SearchValue+" group by 1,2,3 ) app" ;
				}
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQLsum(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, rankID, rankjcoID);
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
			String unit_name,String unit_sus_no,/*String user_role_id,*/String from_date, String to_date, String posn_date,  String typeID, String rankID, String rankjcoID) {
	 
	
		String SearchValue ="";
		
		if (typeID.equals("1")) {
		if(!Search.equals("")) { // for Input Filter
			SearchValue ="and";
			SearchValue +="( upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ? or upper(comm.personnel_no) like ?"
					+ " or upper(rk.description) like ?  or upper(comm.name) like ? or upper(cen.name) like ? or date_part('year', age(cen.date_of_birth))::text like ? "
					+ "or upper(g.gender_name) like ?) ";
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
				  SearchValue +=  " and cast(cen.date_of_birth as date) >= cast(? as date) and cast(cen.date_of_birth as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(cen.date_of_birth as date) >= cast(? as date) and cast(cen.date_of_birth as date) <= cast(? as date)"; 
			  }
		}
		
		if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(cen.date_of_birth as date) >= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(cen.date_of_birth as date) >= cast(? as date)"; 
			  }
		}
		
		if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(cen.date_of_birth as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(cen.date_of_birth as date) <= cast(? as date)"; 
			  }
		}
		
		
		if( !rankID.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and rk.id::text = ? ";	
			}
			else {
				SearchValue += " and rk.id::text = ? ";
			}
		}
		
		}
		if (typeID.equals("2")) {
			if(!Search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				SearchValue +="( upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ? or upper(comm.army_no) like ? "
						+ " or upper(rk.rank) like ?  or upper(comm.full_name) like ? or upper(cen.name) like ?"
						+ " or date_part('year', age(cen.date_of_birth))::text like ? or upper(g.gender_name) like ?) ";
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
					  SearchValue +=  " and cast(cen.date_of_birth as date) >= cast(? as date) and cast(cen.date_of_birth as date) <= cast(? as date)"; 
				}
				else { SearchValue += " and cast(cen.date_of_birth as date) >= cast(? as date) and cast(cen.date_of_birth as date) <= cast(? as date)"; 
				}
			}
			
			if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(cen.date_of_birth as date) >= cast(? as date)"; 
				}
				else { SearchValue += " and cast(cen.date_of_birth as date) >= cast(? as date)"; 
				}
			}
			
			if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(cen.date_of_birth as date) <= cast(? as date)"; 
				}
				else { SearchValue += " and cast(cen.date_of_birth as date) <= cast(? as date)"; 
				}
			}
//			
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
			  String from_date, String to_date, String posn_date, String typeID, String rankID, String rankjcoID) {
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
			if(!rankID.equals("0")) {
				flag += 1;
				stmt.setString(flag, rankID.toUpperCase());
				
			}
			
			if(!rankjcoID.equals("0")) {
				flag += 1;
				stmt.setString(flag, rankjcoID.toUpperCase());
			}
			
		}catch (Exception e) {}
		
		return stmt;
		
	}
	
	
	
	
	public String GenerateQueryWhereClause_SQLsum(String Search, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name,String unit_sus_no,/*String user_role_id,*/String from_date, String to_date, String posn_date, String typeID, String rankID, String rankjcoID) {
	 
	
		String SearchValue ="";
		
		if (typeID.equals("1")) {
		if(!Search.equals("")) { // for Input Filter
			SearchValue ="and";
			SearchValue +="( upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ?  ) ";
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
				  SearchValue +=  " and cast(cen.date_of_birth as date) >= cast(? as date) and cast(cen.date_of_birth as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(cen.date_of_birth as date) >= cast(? as date) and cast(cen.date_of_birth as date) <= cast(? as date)"; 
			  }
		}
		
		if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(cen.date_of_birth as date) >= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(cen.date_of_birth as date) >= cast(? as date)"; 
			  }
		}
		
		if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and cast(cen.date_of_birth as date) <= cast(? as date)"; 
			  } 
			else { 
				  SearchValue += " and cast(cen.date_of_birth as date) <= cast(? as date)"; 
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
		
	
		}
		if (typeID.equals("2")) {
			if(!Search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				SearchValue +="( upper(orb.unit_name) like ? or upper(comm.unit_sus_no) like ? ) ";
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
					  SearchValue +=  " and cast(cen.date_of_birth as date) >= cast(? as date) and cast(cen.date_of_birth as date) <= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(cen.date_of_birth as date) >= cast(? as date) and cast(cen.date_of_birth as date) <= cast(? as date)"; 
					}
			}
			
			if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(cen.date_of_birth as date) >= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(cen.date_of_birth as date) >= cast(? as date)"; 
					}
			}
			
			if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")){
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(cen.date_of_birth as date) <= cast(? as date)"; 
				  }
				else { SearchValue += " and cast(cen.date_of_birth as date) <= cast(? as date)"; 
					}
			}
			
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
			  String from_date, String to_date, String posn_date, String typeID, String rankID, String rankjcoID) {
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
			if(!rankID.equals("0")) {
				flag += 1;
				stmt.setString(flag, rankID.toUpperCase());
				
			}
			
			if(!rankjcoID.equals("0")) {
				flag += 1;
				stmt.setString(flag, rankjcoID.toUpperCase());
			}
		
			

		}catch (Exception e) {}
		
		return stmt;
		
	}
	
	
	
	
}
