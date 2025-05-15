package com.dao.formation_report;


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

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.persistance.util.HibernateUtil;

public class Formation_reportsDAOImpl implements Formation_reportsDAO{

private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
//	--------------------1------------------------
	
	public long getArrivalDepartureTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		String whr="";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if(!Search.equals("")) { 
				whr =" and  ";
				whr +="( upper( a.unit_name) like ? or upper(a.frm_cmd_name) like ? or upper( a.\"frm_Location\") like ? "
						+ " or upper(a.to_cmd_name) like ? or upper(a.\"To_Location\") like ? "
						+ " or upper( to_char(a.nmb_date,'DD-MON-YYYY') ) like ?)";
			}

			q = "select count(app.*) from ( SELECT DISTINCT\r\n"
					+ "       a.unit_name,\r\n"
					+ "       a.frm_cmd_name,        \r\n"
					+ "       'test2' as frm_Location,\r\n"
					+ "       a.to_cmd_name,  \r\n"
					+ "       'test1' as to_Location,\r\n"
					+ "       to_char(a.nmb_date,'DD-MON-YYYY')  AS nmb_date,\r\n"
					+ "       '' as remark    \r\n"
					+ "  \r\n"
					+ "  FROM view_relief_report a\r\n"
					+ " WHERE a.sd_status = '1'\r\n"
					+ whr +" ORDER BY nmb_date\r\n"
//					+ "  AND cast(a.nmb_date AS date) >= cast('2024-01-01' AS date)\r\n"
//					+ "   AND cast(a.nmb_date AS date) <= cast('2024-01-18' AS date)  \r\n"
					+ ")app";


			PreparedStatement stmt = conn.prepareStatement(q);

			int flag = 0;
				if(!Search.equals("")) {						
				flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				
				flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				
				flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				
				flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				
				flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				

				flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				}			

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
	
	public List<Map<String, Object>> getArrivalDepartureTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";
	String whr="";

	try{	
		String pageL = "";
        
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if(!Search.equals("")) { 
				whr =" and  ";
				whr +="( upper( a.unit_name) like ? or upper(a.frm_cmd_name) like ? or upper( a.\"frm_Location\") like ? "
						+ " or upper(a.to_cmd_name) like ? or upper(a.\"To_Location\") like ? "
						+ " or upper( to_char(a.nmb_date,'DD-MON-YYYY') ) like ?)";
			}
		
			q="SELECT DISTINCT\r\n"
					+ "       a.unit_name,\r\n"
					+ "       a.frm_cmd_name,        \r\n"
					+ "       a.\"frm_Location\" as frm_Location,\r\n"
					+ "       a.to_cmd_name,  \r\n"
					+ "       a.\"To_Location\" as to_Location,\r\n"
					+ "       to_char(a.nmb_date,'DD-MON-YYYY') AS nmb_date,\r\n"
					+ "       '' as remark\r\n"
					+ "     \r\n"
					+ "    --  a.\"to_Location\"\r\n"
					+ "  FROM view_relief_report a\r\n"
					+ " WHERE a.sd_status = '1'\r\n"
//					+ "  AND cast(a.nmb_date AS date) >= cast('2024-01-01' AS date)\r\n"
//					+ "   AND cast(a.nmb_date AS date) <= cast('2024-01-18' AS date)\r\n"
//					+ "   AND cast(a.nmb_date AS date) >= cast('2024-01-01' AS date)\r\n"
//					+ "   AND cast(a.nmb_date AS date) <= cast('2024-01-18' AS date)\r\n"
					+ whr +" ORDER BY nmb_date"
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			int flag = 0;
			if(!Search.equals("")) {						
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			

			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			}	
			
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
	
	
//	-------------------2------------------------
	public long getreorg_conv_disdTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if(!Search.equals("")) { 
				whr =" where   ";
				whr +="( upper(app.unit_name) like ? or upper(app.arm_desc) like ? or upper(app.from_unit) like ? or upper(app.to_unit) like ? "
						+ "or upper(app.type) like ? or upper(app.remarks) like ?"
						
						+ " )";
			}
			
			q = "select count(app.*) from (select\r\n"
					+ "  u.unit_name as unit_name,\r\n"
					+ "  u.unit_name as from_unit,\r\n"
					+ "	a.arm_desc,\r\n"
					+ "	u12.unit_name as to_unit,\r\n"
					+ "  'Reorg' as type,\r\n"
					+ "  '' as remarks\r\n"
					+ "from tb_miso_orbat_unt_dtl u\r\n"
					+ "inner join tb_miso_orbat_arm_code a on u.arm_code=a.arm_code\r\n"
					+ "inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id and s.type_of_letter in ('1','2','3','11')\r\n"
					+ "inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' \r\n"
					+ "left join tb_miso_orbat_unt_dtl u12 on u.sus_no_for_comb_disint = u12.sus_no and   upper(u12.status_sus_no) = upper('Inactive')\r\n"
					+ "left join (select\r\n"
					+ "		u1.unit_name as command,\r\n"
					+ "		substr(u1.form_code_control,1,1) as command_code\r\n"
					+ "	from tb_miso_orbat_unt_dtl u1\r\n"
					+ "	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and upper(u1.status_sus_no)=upper('Active')) c on substr(u.form_code_control,1,1)=c.command_code\r\n"
					+ "where upper(u.status_sus_no)=upper('Active')\r\n"
					+ "union all \r\n"
					+ "\r\n"
					+ "select 	\r\n"
					+ "	u.unit_name as unit_name,\r\n"
					+ "  u.unit_name as from_unit,	\r\n"
					+ "	a.arm_desc,\r\n"
					+ "  '' as to_unit,\r\n"
					+ "  'Disbandment' as type,\r\n"
					+ "  '' as remarks\r\n"
					+ "from tb_miso_orbat_unt_dtl u\r\n"
					+ "inner join tb_miso_orbat_arm_code a on u.arm_code=a.arm_code\r\n"
					+ "inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id and s.type_of_letter in ('4')\r\n"
					+ "inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' \r\n"
					+ "left join (select\r\n"
					+ "		u1.unit_name as command,\r\n"
					+ "		substr(u1.form_code_control,1,1) as command_code\r\n"
					+ "	from tb_miso_orbat_unt_dtl u1\r\n"
					+ "	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and upper(u1.status_sus_no)=upper('Active')) c on substr(u.form_code_control,1,1)=c.command_code\r\n"
					+ "where upper(u.status_sus_no)=upper('Inactive')) app   "
					+ whr;


			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 1;
			if(!Search.equals("")) {						
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			}

			ResultSet rs = stmt.executeQuery();
//			System.out.println("2nd:- " + stmt);
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
	
	public List<Map<String, Object>> getreorg_conv_disdTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";
	String whr="";


	try{	
		String pageL = "";
        
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(!Search.equals("")) { 
				whr =" where   ";
				whr +="( upper(app.unit_name) like ? or upper(app.arm_desc) like ? or upper(app.from_unit) like ? or upper(app.to_unit) like ? "
						+ "or upper(app.type) like ? or upper(app.remarks) like ?"
						
						+ " )";
			}
			
			q="select * from (select\r\n"
					+ "  u.unit_name as unit_name,\r\n"
					+ "  u.unit_name as from_unit,\r\n"
					+ "	a.arm_desc,\r\n"
					+ "	u12.unit_name as to_unit,\r\n"
					+ "  'Reorg' as type,\r\n"
					+ "  '' as remarks\r\n"
					+ "from tb_miso_orbat_unt_dtl u\r\n"
					+ "inner join tb_miso_orbat_arm_code a on u.arm_code=a.arm_code\r\n"
					+ "inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id and s.type_of_letter in ('1','2','3','11')\r\n"
					+ "inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' \r\n"
					+ "left join tb_miso_orbat_unt_dtl u12 on u.sus_no_for_comb_disint = u12.sus_no and   upper(u12.status_sus_no) = upper('Inactive')\r\n"
					+ "left join (select\r\n"
					+ "		u1.unit_name as command,\r\n"
					+ "		substr(u1.form_code_control,1,1) as command_code\r\n"
					+ "	from tb_miso_orbat_unt_dtl u1\r\n"
					+ "	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and upper(u1.status_sus_no)=upper('Active')) c on substr(u.form_code_control,1,1)=c.command_code\r\n"
					+ "where upper(u.status_sus_no)=upper('Active')\r\n"
					+ "union all \r\n"
					+ "\r\n"
					+ "select 	\r\n"
					+ "	u.unit_name as unit_name,\r\n"
					+ "  u.unit_name as from_unit,	\r\n"
					+ "	a.arm_desc,\r\n"
					+ "  '' as to_unit,\r\n"
					+ "  'Disbandment' as type,\r\n"
					+ "  '' as remarks\r\n"
					+ "from tb_miso_orbat_unt_dtl u\r\n"
					+ "inner join tb_miso_orbat_arm_code a on u.arm_code=a.arm_code\r\n"
					+ "inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id and s.type_of_letter in ('4')\r\n"
					+ "inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' \r\n"
					+ "left join (select\r\n"
					+ "		u1.unit_name as command,\r\n"
					+ "		substr(u1.form_code_control,1,1) as command_code\r\n"
					+ "	from tb_miso_orbat_unt_dtl u1\r\n"
					+ "	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and upper(u1.status_sus_no)=upper('Active')) c on substr(u.form_code_control,1,1)=c.command_code\r\n"
					+ "where upper(u.status_sus_no)=upper('Inactive') ) app "
					+whr
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			int flag = 1;

			if(!Search.equals("")) {						
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			}
			
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
	
	/*-------------- New rel----------- */
	public long getreorg_new_relTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from ( select\r\n"
					+ "  u.unit_name as unit_name,\r\n"
					+ "  u.unit_name as from_unit,\r\n"
					+ "	a.arm_desc,\r\n"
					+ "	u12.unit_name as to_unit,\r\n"
					+ "  'Reorg' as type,\r\n"
					+ "  '' as remarks\r\n"
					+ "from tb_miso_orbat_unt_dtl u\r\n"
					+ "inner join tb_miso_orbat_arm_code a on u.arm_code=a.arm_code\r\n"
					+ "inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id and s.type_of_letter in ('1','2','3','11')\r\n"
					+ "inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' \r\n"
					+ "left join tb_miso_orbat_unt_dtl u12 on u.sus_no_for_comb_disint = u12.sus_no and  u12.status_sus_no in ('Inactive')\r\n"
					+ "left join (select\r\n"
					+ "		u1.unit_name as command,\r\n"
					+ "		substr(u1.form_code_control,1,1) as command_code\r\n"
					+ "	from tb_miso_orbat_unt_dtl u1\r\n"
					+ "	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and u1.status_sus_no='Active') c on substr(u.form_code_control,1,1)=c.command_code\r\n"
					+ "where u.status_sus_no='Active'\r\n"
					+ "\r\n"
					+ "union all \r\n"
					+ "\r\n"
					+ "select 	\r\n"
					+ "	u.unit_name as unit_name,\r\n"
					+ "  u.unit_name as from_unit,	\r\n"
					+ "	a.arm_desc,\r\n"
					+ "  '' as to_unit,\r\n"
					+ "  'Disbandment' as type,\r\n"
					+ "  '' as remarks\r\n"
					+ "from tb_miso_orbat_unt_dtl u\r\n"
					+ "inner join tb_miso_orbat_arm_code a on u.arm_code=a.arm_code\r\n"
					+ "inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id and s.type_of_letter in ('4')\r\n"
					+ "inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' \r\n"
					+ "left join (select\r\n"
					+ "		u1.unit_name as command,\r\n"
					+ "		substr(u1.form_code_control,1,1) as command_code\r\n"
					+ "	from tb_miso_orbat_unt_dtl u1\r\n"
					+ "	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and u1.status_sus_no='Active') c on substr(u.form_code_control,1,1)=c.command_code\r\n"
					+ "where u.status_sus_no='Inactive') app";


			PreparedStatement stmt = conn.prepareStatement(q);
			

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
	
	public List<Map<String, Object>> getreorg_new_relTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
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
			
		
			q="SELECT r.id,\r\n"
					+ "   ltrim(to_char(r.issue_date,'dd-mm-yyyy'),'0') AS issue_date,\r\n"
					+ "       r.type_of_vehicle,\r\n"
					+ "        u1.unit_name,\r\n"
					+ "         d1.unit_name AS depot,\r\n"
					+ "          r.quantity,\r\n"
					+ "       r.rio_no AS rio,\r\n"
					+ "       r.ro_no,    \r\n"
					+ "       cmd.cmd_name AS command,\r\n"
					+ "       m.std_nomclature AS mctnomen,\r\n"
					+ "       r.mct AS mct,    \r\n"
					+ "  \r\n"
					+ "      \r\n"
					+ "       (\r\n"
					+ "        SELECT label\r\n"
					+ "          FROM t_domain_value\r\n"
					+ "         WHERE domainid = 'VEHICLEISSUETYPE'\r\n"
					+ "           AND codevalue = r.type_of_issue\r\n"
					+ "         LIMIT 1\r\n"
					+ "       ) AS type_of_issue,\r\n"
					+ "    \r\n"
					+ "       ltrim(to_char(r.valid_upto,'dd-mm-yyyy'),'0') AS validity\r\n"
					+ "  FROM tb_tms_rio_vehicle_dtls r\r\n"
					+ " INNER JOIN tb_miso_orbat_unt_dtl u1\r\n"
					+ "    ON u1.sus_no = r.sus_no\r\n"
					+ "   AND u1.status_sus_no = 'Active'\r\n"
					+ "  LEFT JOIN orbat_view_cmd cmd\r\n"
					+ "    ON cmd.cmd_code = substr(u1.form_code_control,1,1)\r\n"
					+ "  LEFT JOIN tb_tms_mct_master m\r\n"
					+ "    ON m.mct = r.mct\r\n"
					+ "  LEFT JOIN tb_miso_orbat_unt_dtl d1\r\n"
					+ "    ON d1.sus_no = r.depot_sus_no\r\n"
					+ "   AND d1.status_sus_no = 'Active'\r\n"
					+ " WHERE\r\n"
					+ "   r.status = '1'e'"
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			
			
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


	/*--------------------3------ */
	public long get_revision_amdtTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if(!Search.equals("")) { 
				whr ="  where   ";
				whr +="( upper( app.unit_name) like ? or upper(app.we_pe_wet_pet) like ? or upper(app.fmn) like ? "
						+ " or upper(app.arm_desc) like ?) ";
//						+ " concat(extract(YEAR FROM age(CURRENT_DATE, to_date(e.valid_till, 'DD-MM-YYYY'))), ' years ', extract(MONTH FROM age(CURRENT_DATE, to_date(e.valid_till, 'DD-MM-YYYY'))), ' months ', extract(DAY FROM age(CURRENT_DATE, to_date(e.valid_till, 'DD-MM-YYYY'))), ' days') like ?)";
			}
			
			q = "select count(app.*) from ("
					+ "SELECT DISTINCT orb.unit_name,\r\n" + 
					"       d.wet_pet_no as we_pe_wet_pet,\r\n" + 
					"       hq.unit_name as fmn, armc.arm_desc,\r\n" + 
					"        concat(extract(YEAR FROM age(CURRENT_DATE, to_date(e.valid_till, 'DD-MM-YYYY'))), ' years ', extract(MONTH FROM age(CURRENT_DATE, to_date(e.valid_till, 'DD-MM-YYYY'))), ' months ', extract(DAY FROM age(CURRENT_DATE, to_date(e.valid_till, 'DD-MM-YYYY'))), ' days') AS due_by\r\n" + 
					"  FROM cue_tb_wepe_link_sus_perstransweapon a\r\n" + 
					"  LEFT JOIN cue_tb_miso_wepeconditions d\r\n" + 
					"    ON d.we_pe_no = a.wepe_weapon_no\r\n" + 
					"   AND d.type = '3'\r\n" + 
					"  LEFT JOIN tb_miso_orbat_unt_dtl orb\r\n" + 
					"    ON orb.sus_no = a.sus_no\r\n" + 
					"   AND status_sus_no = 'Active'\r\n" + 
					"  LEFT JOIN tb_miso_orbat_arm_code armc\r\n" + 
					"    ON left(orb.arm_code, 2) = left(armc.arm_code, 2)\r\n" + 
					"  LEFT JOIN cue_tb_miso_mms_wet_pet_mast_det e\r\n" + 
					"    ON e.wet_pet_no = d.wet_pet_no\r\n" + 
					"	  LEFT JOIN nrv_hq hq on hq.form_code_control = orb.form_code_control\r\n" + 
					"    \r\n" + 
					"UNION ALL \r\n" + 
					"\r\n" + 
					"SELECT DISTINCT orb.unit_name,\r\n" + 
					"       d.we_pe_no as we_pe_wet_pet, armc.arm_desc,\r\n" + 
					"      hq.unit_name as fmn,\r\n" + 
					"        concat(extract(YEAR FROM age(CURRENT_DATE, to_date(d.eff_to_date, 'DD-MM-YYYY'))), ' years ', extract(MONTH FROM age(CURRENT_DATE, to_date(d.eff_to_date, 'DD-MM-YYYY'))), ' months ', extract(DAY FROM age(CURRENT_DATE, to_date(d.eff_to_date, 'DD-MM-YYYY'))), ' days') AS due_by\r\n" + 
					"  FROM cue_tb_wepe_link_sus_perstransweapon a\r\n" + 
					"  LEFT JOIN cue_tb_miso_wepeconditions d\r\n" + 
					"    ON d.we_pe_no = a.wepe_pers_no\r\n" + 
					"   AND d.type = '1'\r\n" + 
					"  LEFT JOIN tb_miso_orbat_unt_dtl orb\r\n" + 
					"    ON orb.sus_no = a.sus_no\r\n" + 
					"   AND status_sus_no = 'Active'\r\n" + 
					"  LEFT JOIN tb_miso_orbat_arm_code armc\r\n" + 
					"    ON left(orb.arm_code, 2) = left(armc.arm_code, 2)\r\n" + 
					"	LEFT JOIN nrv_hq hq on hq.form_code_control = orb.form_code_control"
					
					+ ") app" +whr;


			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!Search.equals("")) {						
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");		
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
//			flag += 1;
//				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			}
		
			

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
	
	public List<Map<String, Object>> get_revision_amdtTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";
	String whr="";

	try{	
		String pageL = "";
        
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if(!Search.equals("")) { 
				whr ="  where   ";
				whr +="( upper( app.unit_name) like ? or upper(app.we_pe_wet_pet) like ? or upper(app.fmn) like ? "
						+ " or upper(app.arm_desc) like ?) ";
//						+ " concat(extract(YEAR FROM age(CURRENT_DATE, to_date(e.valid_till, 'DD-MM-YYYY'))), ' years ', extract(MONTH FROM age(CURRENT_DATE, to_date(e.valid_till, 'DD-MM-YYYY'))), ' months ', extract(DAY FROM age(CURRENT_DATE, to_date(e.valid_till, 'DD-MM-YYYY'))), ' days') like ?)";
			}
			
		
			q="select * from (SELECT DISTINCT orb.unit_name,\r\n" + 
					"       d.wet_pet_no as we_pe_wet_pet,\r\n" + 
					"       hq.unit_name as fmn, armc.arm_desc,\r\n" + 
					"        concat(extract(YEAR FROM age(CURRENT_DATE, to_date(e.valid_till, 'DD-MM-YYYY'))), ' years ', extract(MONTH FROM age(CURRENT_DATE, to_date(e.valid_till, 'DD-MM-YYYY'))), ' months ', extract(DAY FROM age(CURRENT_DATE, to_date(e.valid_till, 'DD-MM-YYYY'))), ' days') AS due_by\r\n" + 
					"  FROM cue_tb_wepe_link_sus_perstransweapon a\r\n" + 
					"  LEFT JOIN cue_tb_miso_wepeconditions d\r\n" + 
					"    ON d.we_pe_no = a.wepe_weapon_no\r\n" + 
					"   AND d.type = '3'\r\n" + 
					"  LEFT JOIN tb_miso_orbat_unt_dtl orb\r\n" + 
					"    ON orb.sus_no = a.sus_no\r\n" + 
					"   AND status_sus_no = 'Active'\r\n" + 
					"  LEFT JOIN tb_miso_orbat_arm_code armc\r\n" + 
					"    ON left(orb.arm_code, 2) = left(armc.arm_code, 2)\r\n" + 
					"  LEFT JOIN cue_tb_miso_mms_wet_pet_mast_det e\r\n" + 
					"    ON e.wet_pet_no = d.wet_pet_no\r\n" + 
					"	  LEFT JOIN nrv_hq hq on hq.form_code_control = orb.form_code_control\r\n" + 
					"    \r\n" + 
					"UNION ALL \r\n" + 
					"\r\n" + 
					"SELECT DISTINCT orb.unit_name,\r\n" + 
					"       d.we_pe_no as we_pe_wet_pet,\r\n" + 
					"      hq.unit_name as fmn, armc.arm_desc,\r\n" + 
					"        concat(extract(YEAR FROM age(CURRENT_DATE, to_date(d.eff_to_date, 'DD-MM-YYYY'))), ' years ', extract(MONTH FROM age(CURRENT_DATE, to_date(d.eff_to_date, 'DD-MM-YYYY'))), ' months ', extract(DAY FROM age(CURRENT_DATE, to_date(d.eff_to_date, 'DD-MM-YYYY'))), ' days') AS due_by\r\n" + 
					"  FROM cue_tb_wepe_link_sus_perstransweapon a\r\n" + 
					"  LEFT JOIN cue_tb_miso_wepeconditions d\r\n" + 
					"    ON d.we_pe_no = a.wepe_pers_no\r\n" + 
					"   AND d.type = '1'\r\n" + 
					"  LEFT JOIN tb_miso_orbat_unt_dtl orb\r\n" + 
					"    ON orb.sus_no = a.sus_no\r\n" + 
					"   AND status_sus_no = 'Active'\r\n" + 
					"  LEFT JOIN tb_miso_orbat_arm_code armc\r\n" + 
					"    ON left(orb.arm_code, 2) = left(armc.arm_code, 2)\r\n" + 
					"	LEFT JOIN nrv_hq hq on hq.form_code_control = orb.form_code_control) app"
			
					+whr
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
//			System.out.println("3rd:- " + stmt);
			int flag = 0;
			if(!Search.equals("")) {						
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");		
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
//			flag += 1;
//				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			}
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

	/*-----------4--------------- */
	public long get_tms_new_relTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		String whr="";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if(!Search.equals("")) { 
				whr =" and  ";
				whr +="( upper(r.type_of_vehicle) like ? or upper(d1.unit_name) like ? or upper(u1.unit_name) like ? "
						+ " or upper(ltrim(TO_CHAR(r.issue_date,'DD-MON-YYYY'),'0')) like ?)";
			}
		
			q = "select count(app.*) from (select  ltrim(TO_CHAR( r.issue_date,'dd-mm-yyyy'),'0') as dt,\r\n" + 
					"r.type_of_vehicle,\r\n" + 
					"d1.unit_name as rel_from,\r\n" + 
					"u1.unit_name as rel_to,\r\n" + 
					"r.quantity,\r\n" + 
					"'' as remarks\r\n" + 
					"from tb_tms_rio_vehicle_dtls r \r\n" + 
					" inner join tb_miso_orbat_unt_dtl u1 on u1.sus_no = r.sus_no and u1.status_sus_no = 'Active'  \r\n" + 
					"left join tb_miso_orbat_unt_dtl d1 on d1.sus_no = r.depot_sus_no and d1.status_sus_no = 'Active'   where  r.status = '1'"+whr+ "order by r.id) app";


			PreparedStatement stmt = conn.prepareStatement(q);
//			System.out.println("4th:- " + stmt);
			int flag = 0;
			if(!Search.equals("")) {						
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			

			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			}

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
	
	public List<Map<String, Object>> get_tms_new_relTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";
	String whr="";

	try{	
		String pageL = "";
        
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(!Search.equals("")) { 
				whr =" and  ";
				whr +="( upper(r.type_of_vehicle) like ? or upper(d1.unit_name) like ? or upper(u1.unit_name) like ? "
						+ " or upper(ltrim(TO_CHAR(r.issue_date,'DD-MON-YYYY'),'0')) like ?)";
			}
		
			q="select  ltrim(TO_CHAR( r.issue_date,'dd-mm-yyyy'),'0') as dt,\r\n" + 
					"r.type_of_vehicle,\r\n" + 
					"d1.unit_name as rel_from,\r\n" + 
					"u1.unit_name as rel_to,\r\n" + 
					"r.quantity,\r\n" + 
					"'' as remarks\r\n" + 
					"from tb_tms_rio_vehicle_dtls r \r\n" + 
					" inner join tb_miso_orbat_unt_dtl u1 on u1.sus_no = r.sus_no and u1.status_sus_no = 'Active'  \r\n" + 
					"left join tb_miso_orbat_unt_dtl d1 on d1.sus_no = r.depot_sus_no and d1.status_sus_no = 'Active'   where  r.status = '1'"+whr+" order by r.id"
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			int flag = 0;
			if(!Search.equals("")) {						
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}
			
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
	
	/*----------5--------------- */
	public long get_veh_meeting_discard_conTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		String whr="";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if(!Search.equals("")) { 
				whr =" and  ";
				whr +="( upper(bd.vehtype) like ? or upper(b.mct_nomen) like ? or upper(bd.bano) like ? "
						+ "or upper(bd.vintage_criteria) like ? or upper(bd.cl) like ?)";
			}
			q = "select count(app.*) from (SELECT DISTINCT  on (part.ba_no) part.ba_no,\r\n" + 
					" bd.veh_cat,b.mct_main_id,\r\n" + 
					"    b.mct_nomen AS nomen_four_digit,\r\n" + 
					"	s.std_nomclature AS nomen_ten_digit,\r\n" + 
					"    part.vehcl_classfctn as classification,\r\n" + 
					"    part.vehcl_kms_run AS kms_run,\r\n" + 
					"    \"right\"(((100 + to_char(now(), 'yy'::text)::integer - substr(part.ba_no::text, 1, 2)::integer)::character varying)::text, 2)::integer + 1 + 0 AS veh_vintage,\r\n" + 
					"	CASE WHEN part.ser_status = '0' THEN 'SERV' ELSE 'UNSV' END AS serv_unsv\r\n" + 
					"FROM tb_tms_census_retrn part\r\n" + 
					"    inner JOIN tb_tms_banum_dirctry bd ON part.ba_no::text = bd.ba_no::text\r\n" + 
					"  inner JOIN tb_tms_mct_main_master b ON substr(bd.mct::character varying::text, 1, 4) = b.mct_main_id\r\n" + 
					" inner join tb_tms_mct_discard_condition dis on dis.mct_main_id = b.mct_main_id and  part.vehcl_kms_run >= dis.km\r\n" + 
					" and \"right\"(((100 + to_char(now(), 'yy'::text)::integer - substr(part.ba_no::text, 1, 2)::integer)::character varying)::text, 2)::integer + 1 + 0 >=dis.vintage\r\n" + 
					"      LEFT JOIN tb_tms_mct_master s ON substr(s.mct::character varying::text, 1, 4) = b.mct_main_id\r\n" + 
					"  WHERE part.vehcl_classfctn::text <> ''::text \r\n" + 
					"  union all\r\n" + 
					"  SELECT DISTINCT on (part.ba_no) part.ba_no,\r\n" + 
					" bd.veh_cat,b.mct_main_id,\r\n" + 
					"    b.mct_nomen AS nomen_four_digit,\r\n" + 
					"	s.std_nomclature AS nomen_ten_digit,\r\n" + 
					"    part.classification,\r\n" + 
					"    part.kms_run AS kms_run,\r\n" + 
					"    \"right\"(((100 + to_char(now(), 'yy'::text)::integer - substr(part.ba_no::text, 1, 2)::integer)::character varying)::text, 2)::integer + 1 + 0 AS veh_vintage,\r\n" + 
					"	CASE WHEN part.ser_status = '0' THEN 'SERV' ELSE 'UNSV' END AS serv_unsv\r\n" + 
					" FROM tb_tms_mvcr_parta_dtl part\r\n" + 
					"    inner JOIN tb_tms_banum_dirctry bd ON part.ba_no::text = bd.ba_no::text\r\n" + 
					"  inner JOIN tb_tms_mct_main_master b ON substr(bd.mct::character varying::text, 1, 4) = b.mct_main_id\r\n" + 
					" inner join tb_tms_mct_discard_condition dis on dis.mct_main_id = b.mct_main_id and  part.kms_run >= dis.km \r\n" + 
					"  and \"right\"(((100 + to_char(now(), 'yy'::text)::integer - substr(part.ba_no::text, 1, 2)::integer)::character varying)::text, 2)::integer + 1 + 0 >=dis.vintage\r\n" + 
					"      LEFT JOIN tb_tms_mct_master s ON substr(s.mct::character varying::text, 1, 4) = b.mct_main_id\r\n" + 
					"  WHERE part.classification::text <> ''::text  \r\n" + 
					"     union all\r\n" + 
					"	SELECT DISTINCT on (part.em_no) part.em_no,\r\n" + 
					" bd.veh_cat,b.mct_main_id,\r\n" + 
					"    b.mct_nomen AS nomen_four_digit,\r\n" + 
					"	s.std_nomclature AS nomen_ten_digit,\r\n" + 
					"    part.classification,\r\n" + 
					"    part.current_km_run AS kms_run,\r\n" + 
					"    \"right\"(((100 + to_char(now(), 'yy'::text)::integer - substr(part.em_no::text, 1, 2)::integer)::character varying)::text, 2)::integer + 1 + 0 AS vintage,\r\n" + 
					"	CASE WHEN part.serviceable = 'Yes' THEN 'SERV' ELSE 'UNSV' END AS serv_unsv\r\n" + 
					" FROM tb_tms_emar_report part\r\n" + 
					"    inner JOIN tb_tms_banum_dirctry bd ON part.em_no::text = bd.ba_no::text\r\n" + 
					"  inner JOIN tb_tms_mct_main_master b ON substr(bd.mct::character varying::text, 1, 4) = b.mct_main_id\r\n" + 
					" inner join tb_tms_mct_discard_condition dis on dis.mct_main_id = b.mct_main_id and  part.current_km_run >= dis.km \r\n" + 
					" and   \"right\"(((100 + to_char(now(), 'yy'::text)::integer - substr(part.em_no::text, 1, 2)::integer)::character varying)::text, 2)::integer + 1 + 0 >=dis.vintage\r\n" + 
					"      LEFT JOIN tb_tms_mct_master s ON substr(s.mct::character varying::text, 1, 4) = b.mct_main_id\r\n" + 
					"  WHERE part.classification::text <> ''::text\r\n" + 
					"  \r\n" + 
					"  \r\n" + 
					""
					+whr
					+ ") app";


			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!Search.equals("")) {						
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}

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
	
	public List<Map<String, Object>> get_veh_meeting_discard_conTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";
	String whr="";


	try{	
		String pageL = "";
        
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if(!Search.equals("")) { 
				whr =" where  ";
				whr +="( upper(bd.vehtype) like ? or upper(b.mct_nomen) like ? or upper(bd.bano) like ? "
						+ "or upper(bd.vintage_criteria) like ? or upper(bd.cl) like ?)";
			}
		
			q="SELECT DISTINCT  on (part.ba_no) part.ba_no,\r\n" + 
					" bd.veh_cat,b.mct_main_id,\r\n" + 
					"    b.mct_nomen AS nomen_four_digit,\r\n" + 
					"	s.std_nomclature AS nomen_ten_digit,\r\n" + 
					"    part.vehcl_classfctn as classification,\r\n" + 
					"    part.vehcl_kms_run AS kms_run,\r\n" + 
					"    \"right\"(((100 + to_char(now(), 'yy'::text)::integer - substr(part.ba_no::text, 1, 2)::integer)::character varying)::text, 2)::integer + 1 + 0 AS vintage,\r\n" + 
					"	CASE WHEN part.ser_status = '0' THEN 'SERV' ELSE 'UNSV' END AS serv_unsv\r\n" + 
					"FROM tb_tms_census_retrn part\r\n" + 
					"    inner JOIN tb_tms_banum_dirctry bd ON part.ba_no::text = bd.ba_no::text\r\n" + 
					"  inner JOIN tb_tms_mct_main_master b ON substr(bd.mct::character varying::text, 1, 4) = b.mct_main_id\r\n" + 
					" inner join tb_tms_mct_discard_condition dis on dis.mct_main_id = b.mct_main_id and  part.vehcl_kms_run >= dis.km\r\n" + 
					" and \"right\"(((100 + to_char(now(), 'yy'::text)::integer - substr(part.ba_no::text, 1, 2)::integer)::character varying)::text, 2)::integer + 1 + 0 >=dis.vintage\r\n" + 
					"      LEFT JOIN tb_tms_mct_master s ON substr(s.mct::character varying::text, 1, 4) = b.mct_main_id\r\n" + 
					"  WHERE part.vehcl_classfctn::text <> ''::text \r\n" + 
					"  union all\r\n" + 
					"  SELECT DISTINCT on (part.ba_no) part.ba_no,\r\n" + 
					" bd.veh_cat,b.mct_main_id,\r\n" + 
					"    b.mct_nomen AS nomen_four_digit,\r\n" + 
					"	s.std_nomclature AS nomen_ten_digit,\r\n" + 
					"    part.classification,\r\n" + 
					"    part.kms_run AS kms_run,\r\n" + 
					"    \"right\"(((100 + to_char(now(), 'yy'::text)::integer - substr(part.ba_no::text, 1, 2)::integer)::character varying)::text, 2)::integer + 1 + 0 AS vintage,\r\n" + 
					"	CASE WHEN part.ser_status = '0' THEN 'SERV' ELSE 'UNSV' END AS serv_unsv\r\n" + 
					" FROM tb_tms_mvcr_parta_dtl part\r\n" + 
					"    inner JOIN tb_tms_banum_dirctry bd ON part.ba_no::text = bd.ba_no::text\r\n" + 
					"  inner JOIN tb_tms_mct_main_master b ON substr(bd.mct::character varying::text, 1, 4) = b.mct_main_id\r\n" + 
					" inner join tb_tms_mct_discard_condition dis on dis.mct_main_id = b.mct_main_id and  part.kms_run >= dis.km \r\n" + 
					"  and \"right\"(((100 + to_char(now(), 'yy'::text)::integer - substr(part.ba_no::text, 1, 2)::integer)::character varying)::text, 2)::integer + 1 + 0 >=dis.vintage\r\n" + 
					"      LEFT JOIN tb_tms_mct_master s ON substr(s.mct::character varying::text, 1, 4) = b.mct_main_id\r\n" + 
					"  WHERE part.classification::text <> ''::text  \r\n" + 
					"     union all\r\n" + 
					"	SELECT DISTINCT on (part.em_no) part.em_no,\r\n" + 
					" bd.veh_cat,b.mct_main_id,\r\n" + 
					"    b.mct_nomen AS nomen_four_digit,\r\n" + 
					"	s.std_nomclature AS nomen_ten_digit,\r\n" + 
					"    part.classification,\r\n" + 
					"    part.current_km_run AS kms_run,\r\n" + 
					"    \"right\"(((100 + to_char(now(), 'yy'::text)::integer - substr(part.em_no::text, 1, 2)::integer)::character varying)::text, 2)::integer + 1 + 0 AS vintage,\r\n" + 
					"	CASE WHEN part.serviceable = 'Yes' THEN 'SERV' ELSE 'UNSV' END AS serv_unsv\r\n" + 
					" FROM tb_tms_emar_report part\r\n" + 
					"    inner JOIN tb_tms_banum_dirctry bd ON part.em_no::text = bd.ba_no::text\r\n" + 
					"  inner JOIN tb_tms_mct_main_master b ON substr(bd.mct::character varying::text, 1, 4) = b.mct_main_id\r\n" + 
					" inner join tb_tms_mct_discard_condition dis on dis.mct_main_id = b.mct_main_id and  part.current_km_run >= dis.km \r\n" + 
					" and   \"right\"(((100 + to_char(now(), 'yy'::text)::integer - substr(part.em_no::text, 1, 2)::integer)::character varying)::text, 2)::integer + 1 + 0 >=dis.vintage\r\n" + 
					"      LEFT JOIN tb_tms_mct_master s ON substr(s.mct::character varying::text, 1, 4) = b.mct_main_id\r\n" + 
					"  WHERE part.classification::text <> ''::text\r\n" + 
					"  "+whr
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			
			int flag = 0;
			if(!Search.equals("")) {						
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}
			System.out.println(stmt+"========");
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
	
	/*----------6--------------- */
	public long get_tms_iut_dtlTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		String whr="";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if(!Search.equals("")) { 
				whr =" and  ";
				whr +="( upper( a.vehical_type) like ? or upper(b.unit_name) like ? or upper(c.unit_name) like ? or upper(a.ba_no) like ?"
						+ " or upper(ltrim(TO_CHAR(a.created_on,'DD-MON-YYYY'),'0')) like ?)";
			}
			q = "select count(app.*) from (SELECT DISTINCT ltrim(to_char(a.created_on,'dd-MON-yyyy'),'0') AS dt,\r\n" + 
					"       a.vehical_type,\r\n" + 
					"       b.unit_name AS from_unit,\r\n" + 
					"       c.unit_name AS unit_to,\r\n" + 
					"       a.ba_no,    \r\n" + 
					"       LENGTH(a.ba_no) - LENGTH(REPLACE(a.ba_no, ',', '')) + 1 AS qty\r\n" + 
					"  FROM tb_tms_iut a\r\n" + 
					"  LEFT JOIN tb_miso_orbat_unt_dtl b\r\n" + 
					"    ON b.sus_no = a.source_sus_no\r\n" + 
					"   AND b.status_sus_no = 'Active'\r\n" + 
					"  LEFT JOIN tb_miso_orbat_unt_dtl c\r\n" + 
					"    ON c.sus_no = a.target_sus_no\r\n" + 
					"   AND c.status_sus_no = 'Active'\r\n" + 
					" WHERE a.status = '1' "+whr+"\r\n" + 
					" GROUP BY 1,2,3,4,5) app";


			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!Search.equals("")) {						
			
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}

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
	
	public List<Map<String, Object>> get_tms_iut_dtlTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";


	try{	
		String pageL = "";
		String whr = "";
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(!Search.equals("")) { 
				whr =" and  ";
				whr +="( upper( a.vehical_type) like ? or upper(b.unit_name) like ? or upper(c.unit_name) like ? or upper(a.ba_no) like ?"
						+ " or upper(ltrim(TO_CHAR(a.created_on,'DD-MON-YYYY'),'0')) like ?)";
			}
			q="SELECT DISTINCT ltrim(to_char(a.created_on,'dd-MON-yyyy'),'0') AS dt,\r\n" + 
					"       a.vehical_type,\r\n" + 
					"       b.unit_name AS from_unit,\r\n" + 
					"       c.unit_name AS unit_to,\r\n" + 
					"       a.ba_no,    \r\n" + 
					"       LENGTH(a.ba_no) - LENGTH(REPLACE(a.ba_no, ',', '')) + 1 AS qty\r\n" + 
					"  FROM tb_tms_iut a\r\n" + 
					"  LEFT JOIN tb_miso_orbat_unt_dtl b\r\n" + 
					"    ON b.sus_no = a.source_sus_no\r\n" + 
					"   AND b.status_sus_no = 'Active'\r\n" + 
					"  LEFT JOIN tb_miso_orbat_unt_dtl c\r\n" + 
					"    ON c.sus_no = a.target_sus_no\r\n" + 
					"   AND c.status_sus_no = 'Active'\r\n" + 
					" WHERE a.status = '1' "+whr+"\r\n" + 
					" GROUP BY 1,2,3,4,5 "
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			int flag = 0;
			if(!Search.equals("")) {						
			
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}

			
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
	
	/*----------7-------------- */
	public long get_tms_bkld_veh_dtlTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		String whr="";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if(!Search.equals("")) { 
				whr =" where  ";
				whr +="( upper(to_char(to_date(app.creation_date, 'DD-MON-YYYY'), 'DD-MON-YYYY')) like  ? or upper(app.ba_no) like ? or upper(app.unit_name) like ? "
						+ " )";
			}
			q = " select   count(app.*) from (select distinct on (a.ba_no) ltrim(TO_CHAR( a.creation_date,'DD-MON-YYYY'),'0') as   creation_date,a.ba_no,'A' as veh_type,b.unit_name   \r\n" + 
					"                        ,b1.unit_name as depot_name,'1' as qty from tb_tms_census_drr_dir_dtl a   \r\n" + 
					"                        inner join tb_miso_orbat_unt_dtl b on a.unit_sus_no = b.sus_no and b.status_sus_no='Active'   \r\n" + 
					"                        inner join tb_miso_orbat_unt_dtl b1 on a.sus_no = b1.sus_no and b1.status_sus_no='Active'   \r\n" + 
					"                        where a.issue_condition ='3'   \r\n" + 
					"                    union all     \r\n" + 
					"                    select distinct on (a.ba_no) ltrim(TO_CHAR( a.creation_date,'dd-MON-yyyy'),'0') as   creation_date,a.ba_no,'B' as veh_type,b.unit_name   \r\n" + 
					"                        ,b1.unit_name as depot_name,'1' as qty from TB_TMS_DRR_DIR_DTL a   \r\n" + 
					"                        inner join tb_miso_orbat_unt_dtl b on a.unit_sus_no = b.sus_no and b.status_sus_no='Active'   \r\n" + 
					"                        inner join tb_miso_orbat_unt_dtl b1 on a.sus_no = b1.sus_no and b1.status_sus_no='Active'   \r\n" + 
					"                        where a.typ_of_retrn ='0' and type_of_issue='0'   \r\n" + 
					"                        union all     \r\n" + 
					"                        select distinct on (a.em_no) ltrim(TO_CHAR( a.creation_date,'dd-MON-yyyy'),'0') as   creation_date,a.em_no as ba_no,'C' as veh_type,b.unit_name   \r\n" + 
					"                        ,b1.unit_name as depot_name,'1' as qty from tb_tms_emar_drr_dir_dtl a   \r\n" + 
					"                        inner join tb_miso_orbat_unt_dtl b on a.unit_sus_no = b.sus_no and b.status_sus_no='Active'   \r\n" + 
					"                        inner join tb_miso_orbat_unt_dtl b1 on a.sus_no = b1.sus_no and b1.status_sus_no='Active'   \r\n" + 
					"                        where a.issue_condition ='3' ) app\r\n" + 
					""+whr+"";


			PreparedStatement stmt = conn.prepareStatement(q);
			
			int flag = 0;
			if(!Search.equals("")) {						
			
			flag += 1;
			stmt.setString(flag,"%"+ Search.toUpperCase()+"%");
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			}
		
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
	
	public List<Map<String, Object>> get_tms_bkld_veh_dtlTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";
	String whr="";


	try{	
		String pageL = "";
        
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if(!Search.equals("")) { 
				whr =" where  ";
				whr +="( upper(to_char(to_date(app.creation_date, 'DD-MON-YYYY'), 'DD-MON-YYYY')) like  ? or upper(app.ba_no) like ? or upper(app.unit_name) like ? "
						+ " )";
			}
		
			q="select   * from (select distinct on (a.ba_no) ltrim(TO_CHAR( a.creation_date,'DD-MON-YYYY'),'0') as   creation_date,a.ba_no,'A' as veh_type,b.unit_name   \r\n" + 
					"                        ,b1.unit_name as depot_name,'1' as qty from tb_tms_census_drr_dir_dtl a   \r\n" + 
					"                        inner join tb_miso_orbat_unt_dtl b on a.unit_sus_no = b.sus_no and b.status_sus_no='Active'   \r\n" + 
					"                        inner join tb_miso_orbat_unt_dtl b1 on a.sus_no = b1.sus_no and b1.status_sus_no='Active'   \r\n" + 
					"                        where a.issue_condition ='3'   \r\n" + 
					"                    union all     \r\n" + 
					"                    select distinct on (a.ba_no) ltrim(TO_CHAR( a.creation_date,'dd-MON-yyyy'),'0') as   creation_date,a.ba_no,'B' as veh_type,b.unit_name   \r\n" + 
					"                        ,b1.unit_name as depot_name,'1' as qty from TB_TMS_DRR_DIR_DTL a   \r\n" + 
					"                        inner join tb_miso_orbat_unt_dtl b on a.unit_sus_no = b.sus_no and b.status_sus_no='Active'   \r\n" + 
					"                        inner join tb_miso_orbat_unt_dtl b1 on a.sus_no = b1.sus_no and b1.status_sus_no='Active'   \r\n" + 
					"                        where a.typ_of_retrn ='0' and type_of_issue='0'   \r\n" + 
					"                        union all     \r\n" + 
					"                        select distinct on (a.em_no) ltrim(TO_CHAR( a.creation_date,'dd-MON-yyyy'),'0') as   creation_date,a.em_no as ba_no,'C' as veh_type,b.unit_name   \r\n" + 
					"                        ,b1.unit_name as depot_name,'1' as qty from tb_tms_emar_drr_dir_dtl a   \r\n" + 
					"                        inner join tb_miso_orbat_unt_dtl b on a.unit_sus_no = b.sus_no and b.status_sus_no='Active'   \r\n" + 
					"                        inner join tb_miso_orbat_unt_dtl b1 on a.sus_no = b1.sus_no and b1.status_sus_no='Active'   \r\n" + 
					"                        where a.issue_condition ='3' ) app\r\n" + 
					" "+whr+""
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			int flag = 0;
			if(!Search.equals("")) {						
			
				flag += 1;
				stmt.setString(flag,"%"+ Search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
//			flag += 1;
//				stmt.setString(flag, "%"+Search.toUpperCase()+"%");	
			}
		
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
	
	/*---------8------------ */
	public long get_tms_updation_stateTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String searchval="";
			if(!Search.equals(""))
			{
				searchval=" where (   upper(dt.unit_name) like ?    or upper(t4.label) like   ? or "
						+ "upper(hq.unit_name) like   ? or  upper(dt.ct_part_i_ii) like   ?  or upper(hub.cat_type) like   ? "
						+"  )  ";
			}
			q = "select count(app.*) from (SELECT\r\n" + 
					"    dt.unit_name,\r\n" + 
					"    t4.label AS force,\r\n" + 
					"    hq.unit_name AS fmn,\r\n" + 
					"    dt.ct_part_i_ii,\r\n" + 
					"    hub.cat_type,\r\n" + 
					"    SUM(hub.UPDATED) AS total_updated,\r\n" + 
					"    SUM(hub.YET_TO_UPDATE) AS total_yet_to_update\r\n" + 
					"FROM (\r\n" + 
					"    SELECT \r\n" + 
					"        COUNT(DISTINCT CASE WHEN ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) = ltrim(to_char(c.approve_date, 'mm-yyyy'::text), '0'::text) THEN c.sus_no END) AS UPDATED,\r\n" + 
					"        COUNT(DISTINCT CASE WHEN ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) != ltrim(to_char(c.approve_date, 'mm-yyyy'::text), '0'::text) THEN c.sus_no END) AS YET_TO_UPDATE,\r\n" + 
					"        a.sus_no,\r\n" + 
					"        'A' AS cat_type\r\n" + 
					"    FROM \r\n" + 
					"        tb_tms_census_retrn_main c \r\n" + 
					"        JOIN tb_tms_census_retrn cr ON c.sus_no::text = cr.sus_no::text \r\n" + 
					"        JOIN tb_miso_orbat_unt_dtl a ON a.sus_no = c.sus_no AND a.status_sus_no = 'Active'\r\n" + 
					"    GROUP BY 3\r\n" + 
					"    UNION ALL \r\n" + 
					"    SELECT \r\n" + 
					"        COUNT(DISTINCT CASE WHEN ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) = ltrim(to_char(c.approve_date, 'mm-yyyy'::text), '0'::text) THEN c.sus_no END) AS UPDATED,\r\n" + 
					"        COUNT(DISTINCT CASE WHEN ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) != ltrim(to_char(c.approve_date, 'mm-yyyy'::text), '0'::text) THEN c.sus_no END) AS YET_TO_UPDATE,\r\n" + 
					"        a.sus_no,\r\n" + 
					"        'B' AS cat_type\r\n" + 
					"    FROM \r\n" + 
					"        tb_tms_mvcr_parta_main c \r\n" + 
					"        JOIN tb_tms_mvcr_parta_dtl cr ON c.sus_no::text = cr.sus_no::text \r\n" + 
					"        JOIN tb_miso_orbat_unt_dtl a ON a.sus_no = c.sus_no AND a.status_sus_no = 'Active'\r\n" + 
					"    GROUP BY 3\r\n" + 
					"    UNION ALL \r\n" + 
					"    SELECT \r\n" + 
					"        COUNT(DISTINCT CASE WHEN ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) = ltrim(to_char(c.approve_date::date::timestamp with time zone, 'mm-yyyy'::text), '0'::text) THEN c.sus_no END) AS UPDATED,\r\n" + 
					"        COUNT(DISTINCT CASE WHEN ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) != ltrim(to_char(c.approve_date::date::timestamp with time zone, 'mm-yyyy'::text), '0'::text) THEN c.sus_no END) AS YET_TO_UPDATE,\r\n" + 
					"        a.sus_no,\r\n" + 
					"        'C' AS cat_type\r\n" + 
					"    FROM \r\n" + 
					"        tb_tms_emar_report_main c \r\n" + 
					"        JOIN tb_tms_emar_report cr ON c.sus_no::text = cr.sus_no::text \r\n" + 
					"        JOIN tb_miso_orbat_unt_dtl a ON a.sus_no = c.sus_no AND a.status_sus_no = 'Active'\r\n" + 
					"    GROUP BY 3\r\n" + 
					") hub\r\n" + 
					"INNER JOIN tb_miso_orbat_unt_dtl dt ON dt.sus_no = hub.sus_no AND dt.status_sus_no = 'Active'    \r\n" + 
					"INNER JOIN t_domain_value t4 ON t4.codevalue = dt.type_force AND t4.domainid = 'TYPEOFFORCE'  \r\n" + 
					"LEFT JOIN nrv_hq hq ON hq.form_code_control = dt.form_code_control\r\n" + searchval+
					"GROUP BY\r\n" + 
					"    dt.unit_name,\r\n" + 
					"    t4.label,\r\n" + 
					"    hq.unit_name,\r\n" + 
					"    dt.ct_part_i_ii,\r\n" + 
					"    hub.cat_type) app";


			PreparedStatement stmt = conn.prepareStatement(q);
			

			
			int flag=1;
		if(!Search.equals(""))
		{
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			
		}
		
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
	
	public List<Map<String, Object>> get_tms_updation_stateTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
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
			String searchval="";
			if(!Search.equals(""))
			{
				searchval=" where (   upper(dt.unit_name) like ?    or upper(t4.label) like   ? or "
						+ "upper(hq.unit_name) like   ? or  upper(dt.ct_part_i_ii) like   ?  or upper(hub.cat_type) like   ? "
						+"  )  ";
			}
				
		
			q="SELECT\r\n" + 
					"    dt.unit_name,\r\n" + 
					"    t4.label AS force,\r\n" + 
					"    hq.unit_name AS fmn,\r\n" + 
					"    dt.ct_part_i_ii,\r\n" + 
					"    hub.cat_type,\r\n" + 
					"    SUM(hub.UPDATED) AS total_updated,\r\n" + 
					"    SUM(hub.YET_TO_UPDATE) AS total_yet_to_update\r\n" + 
					"FROM (\r\n" + 
					"    SELECT \r\n" + 
					"        COUNT(DISTINCT CASE WHEN ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) = ltrim(to_char(c.approve_date, 'mm-yyyy'::text), '0'::text) THEN c.sus_no END) AS UPDATED,\r\n" + 
					"        COUNT(DISTINCT CASE WHEN ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) != ltrim(to_char(c.approve_date, 'mm-yyyy'::text), '0'::text) THEN c.sus_no END) AS YET_TO_UPDATE,\r\n" + 
					"        a.sus_no,\r\n" + 
					"        'A' AS cat_type\r\n" + 
					"    FROM \r\n" + 
					"        tb_tms_census_retrn_main c \r\n" + 
					"        JOIN tb_tms_census_retrn cr ON c.sus_no::text = cr.sus_no::text \r\n" + 
					"        JOIN tb_miso_orbat_unt_dtl a ON a.sus_no = c.sus_no AND a.status_sus_no = 'Active'\r\n" + 
					"    GROUP BY 3\r\n" + 
					"    UNION ALL \r\n" + 
					"    SELECT \r\n" + 
					"        COUNT(DISTINCT CASE WHEN ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) = ltrim(to_char(c.approve_date, 'mm-yyyy'::text), '0'::text) THEN c.sus_no END) AS UPDATED,\r\n" + 
					"        COUNT(DISTINCT CASE WHEN ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) != ltrim(to_char(c.approve_date, 'mm-yyyy'::text), '0'::text) THEN c.sus_no END) AS YET_TO_UPDATE,\r\n" + 
					"        a.sus_no,\r\n" + 
					"        'B' AS cat_type\r\n" + 
					"    FROM \r\n" + 
					"        tb_tms_mvcr_parta_main c \r\n" + 
					"        JOIN tb_tms_mvcr_parta_dtl cr ON c.sus_no::text = cr.sus_no::text \r\n" + 
					"        JOIN tb_miso_orbat_unt_dtl a ON a.sus_no = c.sus_no AND a.status_sus_no = 'Active'\r\n" + 
					"    GROUP BY 3\r\n" + 
					"    UNION ALL \r\n" + 
					"    SELECT \r\n" + 
					"        COUNT(DISTINCT CASE WHEN ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) = ltrim(to_char(c.approve_date::date::timestamp with time zone, 'mm-yyyy'::text), '0'::text) THEN c.sus_no END) AS UPDATED,\r\n" + 
					"        COUNT(DISTINCT CASE WHEN ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) != ltrim(to_char(c.approve_date::date::timestamp with time zone, 'mm-yyyy'::text), '0'::text) THEN c.sus_no END) AS YET_TO_UPDATE,\r\n" + 
					"        a.sus_no,\r\n" + 
					"        'C' AS cat_type\r\n" + 
					"    FROM \r\n" + 
					"        tb_tms_emar_report_main c \r\n" + 
					"        JOIN tb_tms_emar_report cr ON c.sus_no::text = cr.sus_no::text \r\n" + 
					"        JOIN tb_miso_orbat_unt_dtl a ON a.sus_no = c.sus_no AND a.status_sus_no = 'Active'\r\n" + 
					"    GROUP BY 3\r\n" + 
					") hub\r\n" + 
					"INNER JOIN tb_miso_orbat_unt_dtl dt ON dt.sus_no = hub.sus_no AND dt.status_sus_no = 'Active'    \r\n" + 
					"INNER JOIN t_domain_value t4 ON t4.codevalue = dt.type_force AND t4.domainid = 'TYPEOFFORCE'  \r\n" + 
					"LEFT JOIN nrv_hq hq ON hq.form_code_control = dt.form_code_control\r\n" + 
					searchval+
					
					
					"GROUP BY\r\n" + 
					"    dt.unit_name,\r\n" + 
					"    t4.label,\r\n" + 
					"    hq.unit_name,\r\n" + 
					"    dt.ct_part_i_ii,\r\n" + 
					"    hub.cat_type"
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			int flag=1;
			if(!Search.equals(""))
			{
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				
			}
			
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
	
	/*--------9---------------- */
	public long get_tms_hld_defiTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String searchval="";
			if(!Search.equals(""))
			{
				
				searchval=" where (   upper(q.fmn) like ?    or upper(q.force) like   ? or  " 
						+ "upper(q.ct_part_i_ii) like   ? or  upper(q.unitname) like   ?  "
						+"  )  ";
			}
			q = "select count(app.*) from (select * from (  \r\n" + 
					"select * , CASE WHEN q.ue != '0' THEN cast(cast(q.total_uh as numeric) - CAST(q.ue  AS NUMERIC) as text) else '0' END as defi,\r\n" + 
					"    CASE WHEN q.ue = '0' THEN q.total_uh else '0' END as sur\r\n" + 
					"	\r\n" + 
					"	\r\n" + 
					"	from (select 	distinct m.mct_nomen,\r\n" + 
					"	 d.mct_main_id as mct_main_id,'B' as veh_cat,\r\n" + 
					"	cast( coalesce((SELECT sum(a.auth_amt) AS total\r\n" + 
					"			   FROM (SELECT b_1.sus_no,a_1.we_pe_no,a_1.mct_no,a_1.auth_amt,' '::text AS condition,'BASEAUTH'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_miso_wepe_transport_det a_1\r\n" + 
					"					JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"					where a_1.mct_no =d.mct_main_id and b_1.sus_no =d.sus_no\r\n" + 
					"					UNION\r\n" + 
					"					SELECT a_1.sus_no,a_1.we_pe_no,b_1.mct_no,sum(b_1.amt_inc_dec) AS sum,''::text AS condition,'MOD'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n" + 
					"					JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n" + 
					"					WHERE a_1.status::text = '1'::text and b_1.mct_no =d.mct_main_id and a_1.sus_no =d.sus_no\r\n" + 
					"					GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no\r\n" + 
					"					UNION\r\n" + 
					"					SELECT a_1.sus_no,b_1.we_pe_no,b_1.in_lieu_mct,b_1.actual_inlieu_auth,b_1.condition,'INLIEU'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"					JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"					JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"					JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"					where b_1.in_lieu_mct =d.mct_main_id and a_1.sus_no =d.sus_no\r\n" + 
					"					UNION\r\n" + 
					"					SELECT a_1.sus_no,b_1.we_pe_no,b_1.mct_no,b_1.amt_inc_dec,b_1.condition,'INCDEC'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"					JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n" + 
					"					JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"					where b_1.mct_no =d.mct_main_id and a_1.sus_no =d.sus_no\r\n" + 
					"					UNION\r\n" + 
					"					SELECT DISTINCT a_1.sus_no,b_1.we_pe_no,b_1.mct_no,sum(b_1.actual_inlieu_auth) * '-1'::integer,' '::text AS condition,'REDUCEDUEINLIEU'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"					JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"					JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n" + 
					"					JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"				where b_1.mct_no =d.mct_main_id and a_1.sus_no =d.sus_no	 \r\n" + 
					"				GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no\r\n" + 
					"			) a\r\n" + 
					"			JOIN tb_tms_mct_main_master b ON a.mct_no::text = b.mct_main_id::text\r\n" + 
					"			GROUP BY a.sus_no, a.we_pe_no, a.mct_no  ORDER BY a.we_pe_no ),0) as text) as ue,\r\n" + 
					"	\r\n" + 
					"	(select cast(count(p.issue_type)as text) from tb_tms_mvcr_parta_dtl p  where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id) and (issue_type='5' or issue_type='3' or issue_type='0' or issue_type='9' or issue_type='10' or issue_type='F') and p.sus_no = d.sus_no) as total_uh \r\n" + 
					", dt.unit_name as unitname,\r\n" + 
					"    t4.label AS force,\r\n" + 
					"    hq.unit_name AS fmn,\r\n" + 
					"    dt.ct_part_i_ii AS ct_part_i_ii,\r\n" + 
					"	\r\n" + 
					"  (SELECT \r\n" + 
					"    COUNT(CASE WHEN ser_status IS NULL OR ser_status = '0' THEN 1 END) AS serviceable_count\r\n" + 
					"FROM tb_tms_mvcr_parta_dtl\r\n" + 
					"WHERE \r\n" + 
					"    ba_no IN (SELECT ba_no FROM tb_tms_banum_dirctry WHERE SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id and veh_cat='B'  ) AND\r\n" + 
					"    sus_no = d.sus_no),\r\n" + 
					"	\r\n" + 
					"	(SELECT \r\n" + 
					"    COUNT(CASE WHEN ser_status IS NOT NULL AND ser_status <> '0' THEN 1 END) AS unserviceable_count\r\n" + 
					"FROM tb_tms_mvcr_parta_dtl\r\n" + 
					"WHERE \r\n" + 
					"    ba_no IN (SELECT ba_no FROM tb_tms_banum_dirctry WHERE SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id and veh_cat='B' ) AND\r\n" + 
					"    sus_no = d.sus_no)\r\n" + 
					"	\r\n" + 
					"\r\n" + 
					"  \r\n" + 
					"	\r\n" + 
					"	from \r\n" + 
					"(select distinct a.mct_main_id,a.sus_no,sum(a.total) from \r\n" + 
					"	(select distinct SUBSTR(cast(d.mct as character varying),1,4) as mct_main_id,part.sus_no,count(d.ba_no) as total from tb_tms_mvcr_parta_dtl part \r\n" + 
					"		inner join tb_tms_banum_dirctry d on d.ba_no = part.ba_no \r\n" + 
					"		left join tb_tms_mct_main_master m on  m.mct_main_id = SUBSTRING(cast(d.mct as character varying), 1, 4) \r\n" + 
					"		\r\n" + 
					"	 group by 1,2\r\n" + 
					"	UNION\r\n" + 
					"	SELECT a_1.mct_no as mct_main_id,b_1.sus_no,a_1.auth_amt  as total\r\n" + 
					"	   FROM cue_tb_miso_wepe_transport_det a_1\r\n" + 
					"	   JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"	\r\n" + 
					"	UNION\r\n" + 
					"	SELECT b_1.mct_no as mct_main_id,a_1.sus_no,sum(b_1.amt_inc_dec)  as total\r\n" + 
					"		FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n" + 
					"		JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n" + 
					"	  WHERE a_1.status::text = '1'::text  \r\n" + 
					"	  GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no        \r\n" + 
					"	UNION\r\n" + 
					"	SELECT b_1.in_lieu_mct as mct_main_id,a_1.sus_no,b_1.actual_inlieu_auth as total\r\n" + 
					"	   FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"		 JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"		 JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"		 JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		\r\n" + 
					"	UNION\r\n" + 
					"	SELECT b_1.mct_no as mct_main_id,a_1.sus_no,b_1.amt_inc_dec as total\r\n" + 
					"		FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"		JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n" + 
					"		JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"	\r\n" + 
					"	UNION\r\n" + 
					"	SELECT b_1.mct_no as mct_main_id,a_1.sus_no,sum(b_1.actual_inlieu_auth) * '-1'::integer as total\r\n" + 
					"		FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"		JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"		JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n" + 
					"		JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		\r\n" + 
					"		GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no) as a\r\n" + 
					" 		where a.total > 0\r\n" + 
					"		group by 1,2 ) as d\r\n" + 
					"		\r\n" + 
					"		inner join tb_miso_orbat_unt_dtl dt  on d.sus_no = dt.sus_no\r\n" + 
					"INNER JOIN t_domain_value t4 ON t4.codevalue = dt.type_force AND t4.domainid = 'TYPEOFFORCE'  and dt.status_sus_no = 'Active'    \r\n" + 
					"LEFT JOIN nrv_hq hq ON hq.form_code_control = dt.form_code_control\r\n" + 
					"		left join tb_tms_mct_main_master m on  m.mct_main_id = d.mct_main_id) as q\r\n" + 
					"\r\n" + 
					"	\r\n" + 
					"union all\r\n" + 
					"select op.* ,CASE WHEN op.ue != '0' THEN cast(cast(op.total_uh as numeric) - CAST(op.ue  AS NUMERIC) as text)  else '0' END as defi,\r\n" + 
					"    CASE WHEN op.ue = '0' THEN cast(op.total_uh as text) else '0' END as sur from \r\n" + 
					"(\r\n" + 
					"select \r\n" + 
					"	distinct cast(m.mct_nomen as text),\r\n" + 
					"	 SUBSTR(cast(d.mct as character varying),1,4) as mct_main_id,d.veh_cat,\r\n" + 
					"	cast(coalesce((SELECT \r\n" + 
					"	sum(a.auth_amt) AS total\r\n" + 
					"   	FROM ( SELECT b_1.sus_no, a_1.we_pe_no, a_1.mct_no, a_1.auth_amt, ' '::text AS condition,'BASEAUTH'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_miso_wepe_transport_det a_1\r\n" + 
					"            JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"		 	where a_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and b_1.sus_no =part.sus_no\r\n" + 
					"      UNION\r\n" + 
					"         SELECT a_1.sus_no,a_1.we_pe_no,b_1.mct_no,sum(b_1.amt_inc_dec) AS sum,''::text AS condition,'MOD'::text AS typeofauth\r\n" + 
					"			FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n" + 
					"			JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n" + 
					"          WHERE a_1.status::text = '1'::text and b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no\r\n" + 
					"          GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT a_1.sus_no,b_1.we_pe_no,b_1.in_lieu_mct, b_1.actual_inlieu_auth,b_1.condition, 'INLIEU'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"            JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"            JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"            JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		 	where b_1.in_lieu_mct =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT a_1.sus_no, b_1.we_pe_no,b_1.mct_no,b_1.amt_inc_dec,b_1.condition,'INCDEC'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"           JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n" + 
					"           JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		 where b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT DISTINCT a_1.sus_no,b_1.we_pe_no,b_1.mct_no,sum(b_1.actual_inlieu_auth) * '-1'::integer,  ' '::text AS condition,'REDUCEDUEINLIEU'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"            JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"            JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n" + 
					"            JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"         where b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no	 \r\n" + 
					"		 GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no\r\n" + 
					"		) a\r\n" + 
					"     JOIN tb_tms_mct_main_master b ON a.mct_no::text = b.mct_main_id::text\r\n" + 
					"  GROUP BY a.sus_no, a.we_pe_no, a.mct_no\r\n" + 
					"  ORDER BY a.we_pe_no ),0) as text)as ue,\r\n" + 
					"	(select cast(count(p.ba_no)as text) from tb_tms_census_retrn p  where p.ba_no in \r\n" + 
					"	 (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(d.mct  AS varchar),1,4)) and p.sus_no = part.sus_no) as total_uh ,\r\n" + 
					"	 dt.unit_name as unitname,\r\n" + 
					"    t4.label AS force,\r\n" + 
					"    hq.unit_name AS fmn,\r\n" + 
					"    dt.ct_part_i_ii AS ct_part_i_ii\r\n" + 
					"	,(SELECT \r\n" + 
					"    COUNT(CASE WHEN ser_status IS NULL OR ser_status = '0' THEN 1 END) AS serviceable_count\r\n" + 
					"FROM tb_tms_census_retrn\r\n" + 
					"WHERE \r\n" + 
					"    ba_no IN (SELECT ba_no FROM tb_tms_banum_dirctry WHERE SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(d.mct  AS varchar),1,4)  and veh_cat='A') AND\r\n" + 
					"    sus_no = part.sus_no\r\n" + 
					"	)\r\n" + 
					"	,\r\n" + 
					"	(SELECT \r\n" + 
					"    COUNT(CASE WHEN ser_status IS NOT NULL AND ser_status <> '0' THEN 1 END) AS unserviceable_count\r\n" + 
					"FROM tb_tms_census_retrn\r\n" + 
					"WHERE \r\n" + 
					"    ba_no IN (SELECT ba_no FROM tb_tms_banum_dirctry WHERE SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(d.mct  AS varchar),1,4) and veh_cat='A') AND\r\n" + 
					"    sus_no = part.sus_no\r\n" + 
					"	)\r\n" + 
					"\r\n" + 
					"from \r\n" + 
					"tb_tms_census_retrn part \r\n" + 
					"inner join tb_tms_banum_dirctry d on d.ba_no = part.ba_no \r\n" + 
					"left join tb_tms_mct_main_master m on  m.mct_main_id = SUBSTRING(cast(d.mct as character varying), 1, 4) \r\n" + 
					"inner join tb_miso_orbat_unt_dtl dt  on part.sus_no = dt.sus_no\r\n" + 
					"INNER JOIN t_domain_value t4 ON t4.codevalue = dt.type_force AND t4.domainid = 'TYPEOFFORCE'  and dt.status_sus_no = 'Active'    \r\n" + 
					"LEFT JOIN nrv_hq hq ON hq.form_code_control = dt.form_code_control\r\n" + 
					") as op\r\n" + 
					"	\r\n" + 
					"union all \r\n" + 
					"	\r\n" + 
					"	select distinct cast(mct.mct_nomen as text) ,mct.mct ,cat_veh ,\r\n" + 
					"	cast (mct.ue as text),\r\n" + 
					"	cast(sum(we+opwks+ascfp+other)as text) as total_uh  ,unitname,force ,fmn,ct_part_i_ii ,serviceable_counts,unserviceable_counts,\r\n" + 
					"	\r\n" + 
					"	CASE WHEN mct.ue != 0 THEN CAST(SUM(we + opwks + ascfp + other) - mct.ue AS TEXT)\r\n" + 
					"         ELSE '0'\r\n" + 
					"    END AS defi,\r\n" + 
					"    CASE WHEN mct.ue = 0 THEN CAST(SUM(we + opwks + ascfp + other) AS TEXT)\r\n" + 
					"         ELSE '0'\r\n" + 
					"    END AS sur\r\n" + 
					"	\r\n" + 
					"	\r\n" + 
					"from (select \r\n" + 
					"		distinct substr(d.mct::varchar,1,4) as mct,d.veh_cat as cat_veh,\r\n" + 
					"		coalesce((SELECT \r\n" + 
					"	sum(a.auth_amt) AS total\r\n" + 
					"   	FROM ( SELECT b_1.sus_no,\r\n" + 
					"            a_1.we_pe_no,\r\n" + 
					"            a_1.mct_no,\r\n" + 
					"            a_1.auth_amt,\r\n" + 
					"            ' '::text AS condition,\r\n" + 
					"            'BASEAUTH'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_miso_wepe_transport_det a_1\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON \r\n" + 
					"		 	a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND \r\n" + 
					"		 	a_1.status::text = '1'::text\r\n" + 
					"		 	where a_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and b_1.sus_no =e.sus_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT a_1.sus_no,\r\n" + 
					"            a_1.we_pe_no,\r\n" + 
					"            b_1.mct_no,\r\n" + 
					"            sum(b_1.amt_inc_dec) AS sum,\r\n" + 
					"            ''::text AS condition,\r\n" + 
					"            'MOD'::text AS typeofauth\r\n" + 
					"			FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n" + 
					"			JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text \r\n" + 
					"		 		AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n" + 
					"          WHERE a_1.status::text = '1'::text and b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =e.sus_no\r\n" + 
					"          GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT a_1.sus_no,\r\n" + 
					"            b_1.we_pe_no,\r\n" + 
					"            b_1.in_lieu_mct,\r\n" + 
					"            b_1.actual_inlieu_auth,\r\n" + 
					"            b_1.condition,\r\n" + 
					"            'INLIEU'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"             JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		 	where b_1.in_lieu_mct =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =e.sus_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT a_1.sus_no,\r\n" + 
					"            b_1.we_pe_no,\r\n" + 
					"            b_1.mct_no,\r\n" + 
					"            b_1.amt_inc_dec,\r\n" + 
					"            b_1.condition,\r\n" + 
					"            'INCDEC'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		 	where b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =e.sus_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT DISTINCT a_1.sus_no,\r\n" + 
					"            b_1.we_pe_no,\r\n" + 
					"            b_1.mct_no,\r\n" + 
					"            sum(b_1.actual_inlieu_auth) * '-1'::integer,\r\n" + 
					"            ' '::text AS condition,\r\n" + 
					"            'REDUCEDUEINLIEU'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"             JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"         where b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =e.sus_no	 \r\n" + 
					"		 GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no\r\n" + 
					"		) a\r\n" + 
					"     JOIN tb_tms_mct_main_master b ON a.mct_no::text = b.mct_main_id::text\r\n" + 
					"  GROUP BY a.sus_no, a.we_pe_no, a.mct_no\r\n" + 
					"  ORDER BY a.we_pe_no ),0) as ue,\r\n" + 
					"	  \r\n" + 
					"		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) = upper('we') and  p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no ) as we,\r\n" + 
					"		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) =  upper('opwks') and p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and  p.sus_no = e.sus_no ) as opwks,\r\n" + 
					"		(select  count(p.proc_from) from tb_tms_banum_dirctry b ,tb_tms_emar_report p where  upper(p.proc_from) = upper('acsfp') and p.em_no=b.ba_no  and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no ) as ascfp,\r\n" + 
					"		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) = upper('others') and p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and  p.sus_no = e.sus_no ) as other,\r\n" + 
					"		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where p.em_no=e.em_no and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no ) as Total_UH\r\n" + 
					"		,m.mct_nomen,dt.unit_name as unitname,\r\n" + 
					"    t4.label AS force,\r\n" + 
					"    hq.unit_name AS fmn,\r\n" + 
					"    dt.ct_part_i_ii AS ct_part_i_ii,\r\n" + 
					"	  (SELECT \r\n" + 
					"    COUNT(CASE WHEN serviceable IS NULL OR serviceable = 'Yes' THEN 1 END) AS serviceable_count\r\n" + 
					"FROM tb_tms_emar_report\r\n" + 
					"WHERE \r\n" + 
					"    em_no IN (SELECT ba_no FROM tb_tms_banum_dirctry WHERE SUBSTRING(CAST(mct AS varchar),1,4)=m.mct_main_id and veh_cat='C') AND\r\n" + 
					"    sus_no =e.sus_no) AS serviceable_counts,\r\n" + 
					"	\r\n" + 
					"(SELECT \r\n" + 
					"    COUNT(CASE WHEN serviceable IS NOT NULL AND serviceable <> 'No' THEN 1 END) AS unserviceable_count\r\n" + 
					"FROM tb_tms_emar_report\r\n" + 
					"WHERE \r\n" + 
					"    em_no IN (SELECT ba_no FROM tb_tms_banum_dirctry WHERE SUBSTRING(CAST(mct AS varchar),1,4) = m.mct_main_id and veh_cat='C') AND\r\n" + 
					"    sus_no =e.sus_no) AS unserviceable_counts\r\n" + 
					"	  \r\n" + 
					"from tb_tms_emar_report e   \r\n" + 
					"inner join tb_tms_banum_dirctry d on  d.ba_no = e.em_no \r\n" + 
					"inner join tb_tms_mct_main_master m on  m.mct_main_id = SUBSTRING(cast(d.mct as character varying), 1, 4) \r\n" + 
					"inner join tb_miso_orbat_unt_dtl dt  on e.sus_no = dt.sus_no\r\n" + 
					"INNER JOIN t_domain_value t4 ON t4.codevalue = dt.type_force AND t4.domainid = 'TYPEOFFORCE'  and dt.status_sus_no = 'Active'    \r\n" + 
					"LEFT JOIN nrv_hq hq ON hq.form_code_control = dt.form_code_control\r\n" + 
					"where   e.proc_from != '' ) as mct \r\n" + 
					"group by  mct.mct,mct.ue,mct.mct_nomen,fmn,force,unitname,ct_part_i_ii,cat_veh,serviceable_counts,unserviceable_counts\r\n" + 
					"\r\n" + 
					" ) as q"
				+searchval
					+ "\r\n" + 
					" ) app";


			PreparedStatement stmt = conn.prepareStatement(q);
			
			int flag=1;
			if(!Search.equals(""))
			{
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}
			
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
	
	public List<Map<String, Object>> get_tms_hld_defiTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
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
			String searchval="";
			if(!Search.equals(""))
			{
				
				searchval=" where (   upper(q.fmn) like ?    or upper(q.force) like   ? or  " 
						+ "upper(q.ct_part_i_ii) like   ? or  upper(q.unitname) like   ?  "
						+"  )  ";
			}
		
			q="select * from (  \r\n" + 
					"select * , CASE WHEN q.ue != '0' THEN cast(cast(q.total_uh as numeric) - CAST(q.ue  AS NUMERIC) as text) else '0' END as defi,\r\n" + 
					"    CASE WHEN q.ue = '0' THEN q.total_uh else '0' END as sur\r\n" + 
					"	\r\n" + 
					"	\r\n" + 
					"	from (select 	distinct m.mct_nomen,\r\n" + 
					"	 d.mct_main_id as mct_main_id,'B' as veh_cat,\r\n" + 
					"	cast( coalesce((SELECT sum(a.auth_amt) AS total\r\n" + 
					"			   FROM (SELECT b_1.sus_no,a_1.we_pe_no,a_1.mct_no,a_1.auth_amt,' '::text AS condition,'BASEAUTH'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_miso_wepe_transport_det a_1\r\n" + 
					"					JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"					where a_1.mct_no =d.mct_main_id and b_1.sus_no =d.sus_no\r\n" + 
					"					UNION\r\n" + 
					"					SELECT a_1.sus_no,a_1.we_pe_no,b_1.mct_no,sum(b_1.amt_inc_dec) AS sum,''::text AS condition,'MOD'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n" + 
					"					JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n" + 
					"					WHERE a_1.status::text = '1'::text and b_1.mct_no =d.mct_main_id and a_1.sus_no =d.sus_no\r\n" + 
					"					GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no\r\n" + 
					"					UNION\r\n" + 
					"					SELECT a_1.sus_no,b_1.we_pe_no,b_1.in_lieu_mct,b_1.actual_inlieu_auth,b_1.condition,'INLIEU'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"					JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"					JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"					JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"					where b_1.in_lieu_mct =d.mct_main_id and a_1.sus_no =d.sus_no\r\n" + 
					"					UNION\r\n" + 
					"					SELECT a_1.sus_no,b_1.we_pe_no,b_1.mct_no,b_1.amt_inc_dec,b_1.condition,'INCDEC'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"					JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n" + 
					"					JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"					where b_1.mct_no =d.mct_main_id and a_1.sus_no =d.sus_no\r\n" + 
					"					UNION\r\n" + 
					"					SELECT DISTINCT a_1.sus_no,b_1.we_pe_no,b_1.mct_no,sum(b_1.actual_inlieu_auth) * '-1'::integer,' '::text AS condition,'REDUCEDUEINLIEU'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"					JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"					JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n" + 
					"					JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"				where b_1.mct_no =d.mct_main_id and a_1.sus_no =d.sus_no	 \r\n" + 
					"				GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no\r\n" + 
					"			) a\r\n" + 
					"			JOIN tb_tms_mct_main_master b ON a.mct_no::text = b.mct_main_id::text\r\n" + 
					"			GROUP BY a.sus_no, a.we_pe_no, a.mct_no  ORDER BY a.we_pe_no ),0) as text) as ue,\r\n" + 
					"	\r\n" + 
					"	(select cast(count(p.issue_type)as text) from tb_tms_mvcr_parta_dtl p  where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id) and (issue_type='5' or issue_type='3' or issue_type='0' or issue_type='9' or issue_type='10' or issue_type='F') and p.sus_no = d.sus_no) as total_uh \r\n" + 
					", dt.unit_name as unitname,\r\n" + 
					"    t4.label AS force,\r\n" + 
					"    hq.unit_name AS fmn,\r\n" + 
					"    dt.ct_part_i_ii AS ct_part_i_ii,\r\n" + 
					"	\r\n" + 
					"  (SELECT \r\n" + 
					"    COUNT(CASE WHEN ser_status IS NULL OR ser_status = '0' THEN 1 END) AS serviceable_count\r\n" + 
					"FROM tb_tms_mvcr_parta_dtl\r\n" + 
					"WHERE \r\n" + 
					"    ba_no IN (SELECT ba_no FROM tb_tms_banum_dirctry WHERE SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id and veh_cat='B'  ) AND\r\n" + 
					"    sus_no = d.sus_no),\r\n" + 
					"	\r\n" + 
					"	(SELECT \r\n" + 
					"    COUNT(CASE WHEN ser_status IS NOT NULL AND ser_status <> '0' THEN 1 END) AS unserviceable_count\r\n" + 
					"FROM tb_tms_mvcr_parta_dtl\r\n" + 
					"WHERE \r\n" + 
					"    ba_no IN (SELECT ba_no FROM tb_tms_banum_dirctry WHERE SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id and veh_cat='B' ) AND\r\n" + 
					"    sus_no = d.sus_no)\r\n" + 
					"	\r\n" + 
					"\r\n" + 
					"  \r\n" + 
					"	\r\n" + 
					"	from \r\n" + 
					"(select distinct a.mct_main_id,a.sus_no,sum(a.total) from \r\n" + 
					"	(select distinct SUBSTR(cast(d.mct as character varying),1,4) as mct_main_id,part.sus_no,count(d.ba_no) as total from tb_tms_mvcr_parta_dtl part \r\n" + 
					"		inner join tb_tms_banum_dirctry d on d.ba_no = part.ba_no \r\n" + 
					"		left join tb_tms_mct_main_master m on  m.mct_main_id = SUBSTRING(cast(d.mct as character varying), 1, 4) \r\n" + 
					"		\r\n" + 
					"	 group by 1,2\r\n" + 
					"	UNION\r\n" + 
					"	SELECT a_1.mct_no as mct_main_id,b_1.sus_no,a_1.auth_amt  as total\r\n" + 
					"	   FROM cue_tb_miso_wepe_transport_det a_1\r\n" + 
					"	   JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"	\r\n" + 
					"	UNION\r\n" + 
					"	SELECT b_1.mct_no as mct_main_id,a_1.sus_no,sum(b_1.amt_inc_dec)  as total\r\n" + 
					"		FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n" + 
					"		JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n" + 
					"	  WHERE a_1.status::text = '1'::text  \r\n" + 
					"	  GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no        \r\n" + 
					"	UNION\r\n" + 
					"	SELECT b_1.in_lieu_mct as mct_main_id,a_1.sus_no,b_1.actual_inlieu_auth as total\r\n" + 
					"	   FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"		 JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"		 JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"		 JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		\r\n" + 
					"	UNION\r\n" + 
					"	SELECT b_1.mct_no as mct_main_id,a_1.sus_no,b_1.amt_inc_dec as total\r\n" + 
					"		FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"		JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n" + 
					"		JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"	\r\n" + 
					"	UNION\r\n" + 
					"	SELECT b_1.mct_no as mct_main_id,a_1.sus_no,sum(b_1.actual_inlieu_auth) * '-1'::integer as total\r\n" + 
					"		FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"		JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"		JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n" + 
					"		JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		\r\n" + 
					"		GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no) as a\r\n" + 
					" 		where a.total > 0\r\n" + 
					"		group by 1,2 ) as d\r\n" + 
					"		\r\n" + 
					"		inner join tb_miso_orbat_unt_dtl dt  on d.sus_no = dt.sus_no\r\n" + 
					"INNER JOIN t_domain_value t4 ON t4.codevalue = dt.type_force AND t4.domainid = 'TYPEOFFORCE'  and dt.status_sus_no = 'Active'    \r\n" + 
					"LEFT JOIN nrv_hq hq ON hq.form_code_control = dt.form_code_control\r\n" + 
					"		left join tb_tms_mct_main_master m on  m.mct_main_id = d.mct_main_id) as q\r\n" + 
					"\r\n" + 
					"	\r\n" + 
					"union all\r\n" + 
					"select op.* ,CASE WHEN op.ue != '0' THEN cast(cast(op.total_uh as numeric) - CAST(op.ue  AS NUMERIC) as text)  else '0' END as defi,\r\n" + 
					"    CASE WHEN op.ue = '0' THEN cast(op.total_uh as text) else '0' END as sur from \r\n" + 
					"(\r\n" + 
					"select \r\n" + 
					"	distinct cast(m.mct_nomen as text),\r\n" + 
					"	 SUBSTR(cast(d.mct as character varying),1,4) as mct_main_id,d.veh_cat,\r\n" + 
					"	cast(coalesce((SELECT \r\n" + 
					"	sum(a.auth_amt) AS total\r\n" + 
					"   	FROM ( SELECT b_1.sus_no, a_1.we_pe_no, a_1.mct_no, a_1.auth_amt, ' '::text AS condition,'BASEAUTH'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_miso_wepe_transport_det a_1\r\n" + 
					"            JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"		 	where a_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and b_1.sus_no =part.sus_no\r\n" + 
					"      UNION\r\n" + 
					"         SELECT a_1.sus_no,a_1.we_pe_no,b_1.mct_no,sum(b_1.amt_inc_dec) AS sum,''::text AS condition,'MOD'::text AS typeofauth\r\n" + 
					"			FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n" + 
					"			JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n" + 
					"          WHERE a_1.status::text = '1'::text and b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no\r\n" + 
					"          GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT a_1.sus_no,b_1.we_pe_no,b_1.in_lieu_mct, b_1.actual_inlieu_auth,b_1.condition, 'INLIEU'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"            JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"            JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"            JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		 	where b_1.in_lieu_mct =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT a_1.sus_no, b_1.we_pe_no,b_1.mct_no,b_1.amt_inc_dec,b_1.condition,'INCDEC'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"           JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n" + 
					"           JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		 where b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT DISTINCT a_1.sus_no,b_1.we_pe_no,b_1.mct_no,sum(b_1.actual_inlieu_auth) * '-1'::integer,  ' '::text AS condition,'REDUCEDUEINLIEU'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"            JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"            JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n" + 
					"            JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"         where b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no	 \r\n" + 
					"		 GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no\r\n" + 
					"		) a\r\n" + 
					"     JOIN tb_tms_mct_main_master b ON a.mct_no::text = b.mct_main_id::text\r\n" + 
					"  GROUP BY a.sus_no, a.we_pe_no, a.mct_no\r\n" + 
					"  ORDER BY a.we_pe_no ),0) as text)as ue,\r\n" + 
					"	(select cast(count(p.ba_no)as text) from tb_tms_census_retrn p  where p.ba_no in \r\n" + 
					"	 (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(d.mct  AS varchar),1,4)) and p.sus_no = part.sus_no) as total_uh ,\r\n" + 
					"	 dt.unit_name as unitname,\r\n" + 
					"    t4.label AS force,\r\n" + 
					"    hq.unit_name AS fmn,\r\n" + 
					"    dt.ct_part_i_ii AS ct_part_i_ii\r\n" + 
					"	,(SELECT \r\n" + 
					"    COUNT(CASE WHEN ser_status IS NULL OR ser_status = '0' THEN 1 END) AS serviceable_count\r\n" + 
					"FROM tb_tms_census_retrn\r\n" + 
					"WHERE \r\n" + 
					"    ba_no IN (SELECT ba_no FROM tb_tms_banum_dirctry WHERE SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(d.mct  AS varchar),1,4)  and veh_cat='A') AND\r\n" + 
					"    sus_no = part.sus_no\r\n" + 
					"	)\r\n" + 
					"	,\r\n" + 
					"	(SELECT \r\n" + 
					"    COUNT(CASE WHEN ser_status IS NOT NULL AND ser_status <> '0' THEN 1 END) AS unserviceable_count\r\n" + 
					"FROM tb_tms_census_retrn\r\n" + 
					"WHERE \r\n" + 
					"    ba_no IN (SELECT ba_no FROM tb_tms_banum_dirctry WHERE SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(d.mct  AS varchar),1,4) and veh_cat='A') AND\r\n" + 
					"    sus_no = part.sus_no\r\n" + 
					"	)\r\n" + 
					"\r\n" + 
					"from \r\n" + 
					"tb_tms_census_retrn part \r\n" + 
					"inner join tb_tms_banum_dirctry d on d.ba_no = part.ba_no \r\n" + 
					"left join tb_tms_mct_main_master m on  m.mct_main_id = SUBSTRING(cast(d.mct as character varying), 1, 4) \r\n" + 
					"inner join tb_miso_orbat_unt_dtl dt  on part.sus_no = dt.sus_no\r\n" + 
					"INNER JOIN t_domain_value t4 ON t4.codevalue = dt.type_force AND t4.domainid = 'TYPEOFFORCE'  and dt.status_sus_no = 'Active'    \r\n" + 
					"LEFT JOIN nrv_hq hq ON hq.form_code_control = dt.form_code_control\r\n" + 
					") as op\r\n" + 
					"	\r\n" + 
					"union all \r\n" + 
					"	\r\n" + 
					"	select distinct cast(mct.mct_nomen as text) ,mct.mct ,cat_veh ,\r\n" + 
					"	cast (mct.ue as text),\r\n" + 
					"	cast(sum(we+opwks+ascfp+other)as text) as total_uh  ,unitname,force ,fmn,ct_part_i_ii ,serviceable_counts,unserviceable_counts,\r\n" + 
					"	\r\n" + 
					"	CASE WHEN mct.ue != 0 THEN CAST(SUM(we + opwks + ascfp + other) - mct.ue AS TEXT)\r\n" + 
					"         ELSE '0'\r\n" + 
					"    END AS defi,\r\n" + 
					"    CASE WHEN mct.ue = 0 THEN CAST(SUM(we + opwks + ascfp + other) AS TEXT)\r\n" + 
					"         ELSE '0'\r\n" + 
					"    END AS sur\r\n" + 
					"	\r\n" + 
					"	\r\n" + 
					"from (select \r\n" + 
					"		distinct substr(d.mct::varchar,1,4) as mct,d.veh_cat as cat_veh,\r\n" + 
					"		coalesce((SELECT \r\n" + 
					"	sum(a.auth_amt) AS total\r\n" + 
					"   	FROM ( SELECT b_1.sus_no,\r\n" + 
					"            a_1.we_pe_no,\r\n" + 
					"            a_1.mct_no,\r\n" + 
					"            a_1.auth_amt,\r\n" + 
					"            ' '::text AS condition,\r\n" + 
					"            'BASEAUTH'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_miso_wepe_transport_det a_1\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON \r\n" + 
					"		 	a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND \r\n" + 
					"		 	a_1.status::text = '1'::text\r\n" + 
					"		 	where a_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and b_1.sus_no =e.sus_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT a_1.sus_no,\r\n" + 
					"            a_1.we_pe_no,\r\n" + 
					"            b_1.mct_no,\r\n" + 
					"            sum(b_1.amt_inc_dec) AS sum,\r\n" + 
					"            ''::text AS condition,\r\n" + 
					"            'MOD'::text AS typeofauth\r\n" + 
					"			FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n" + 
					"			JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text \r\n" + 
					"		 		AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n" + 
					"          WHERE a_1.status::text = '1'::text and b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =e.sus_no\r\n" + 
					"          GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT a_1.sus_no,\r\n" + 
					"            b_1.we_pe_no,\r\n" + 
					"            b_1.in_lieu_mct,\r\n" + 
					"            b_1.actual_inlieu_auth,\r\n" + 
					"            b_1.condition,\r\n" + 
					"            'INLIEU'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"             JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		 	where b_1.in_lieu_mct =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =e.sus_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT a_1.sus_no,\r\n" + 
					"            b_1.we_pe_no,\r\n" + 
					"            b_1.mct_no,\r\n" + 
					"            b_1.amt_inc_dec,\r\n" + 
					"            b_1.condition,\r\n" + 
					"            'INCDEC'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		 	where b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =e.sus_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT DISTINCT a_1.sus_no,\r\n" + 
					"            b_1.we_pe_no,\r\n" + 
					"            b_1.mct_no,\r\n" + 
					"            sum(b_1.actual_inlieu_auth) * '-1'::integer,\r\n" + 
					"            ' '::text AS condition,\r\n" + 
					"            'REDUCEDUEINLIEU'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"             JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"         where b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =e.sus_no	 \r\n" + 
					"		 GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no\r\n" + 
					"		) a\r\n" + 
					"     JOIN tb_tms_mct_main_master b ON a.mct_no::text = b.mct_main_id::text\r\n" + 
					"  GROUP BY a.sus_no, a.we_pe_no, a.mct_no\r\n" + 
					"  ORDER BY a.we_pe_no ),0) as ue,\r\n" + 
					"	  \r\n" + 
					"		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) = upper('we') and  p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no ) as we,\r\n" + 
					"		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) =  upper('opwks') and p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and  p.sus_no = e.sus_no ) as opwks,\r\n" + 
					"		(select  count(p.proc_from) from tb_tms_banum_dirctry b ,tb_tms_emar_report p where  upper(p.proc_from) = upper('acsfp') and p.em_no=b.ba_no  and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no ) as ascfp,\r\n" + 
					"		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) = upper('others') and p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and  p.sus_no = e.sus_no ) as other,\r\n" + 
					"		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where p.em_no=e.em_no and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no ) as Total_UH\r\n" + 
					"		,m.mct_nomen,dt.unit_name as unitname,\r\n" + 
					"    t4.label AS force,\r\n" + 
					"    hq.unit_name AS fmn,\r\n" + 
					"    dt.ct_part_i_ii AS ct_part_i_ii,\r\n" + 
					"	  (SELECT \r\n" + 
					"    COUNT(CASE WHEN serviceable IS NULL OR serviceable = 'Yes' THEN 1 END) AS serviceable_count\r\n" + 
					"FROM tb_tms_emar_report\r\n" + 
					"WHERE \r\n" + 
					"    em_no IN (SELECT ba_no FROM tb_tms_banum_dirctry WHERE SUBSTRING(CAST(mct AS varchar),1,4)=m.mct_main_id and veh_cat='C') AND\r\n" + 
					"    sus_no =e.sus_no) AS serviceable_counts,\r\n" + 
					"	\r\n" + 
					"(SELECT \r\n" + 
					"    COUNT(CASE WHEN serviceable IS NOT NULL AND serviceable <> 'No' THEN 1 END) AS unserviceable_count\r\n" + 
					"FROM tb_tms_emar_report\r\n" + 
					"WHERE \r\n" + 
					"    em_no IN (SELECT ba_no FROM tb_tms_banum_dirctry WHERE SUBSTRING(CAST(mct AS varchar),1,4) = m.mct_main_id and veh_cat='C') AND\r\n" + 
					"    sus_no =e.sus_no) AS unserviceable_counts\r\n" + 
					"	  \r\n" + 
					"from tb_tms_emar_report e   \r\n" + 
					"inner join tb_tms_banum_dirctry d on  d.ba_no = e.em_no \r\n" + 
					"inner join tb_tms_mct_main_master m on  m.mct_main_id = SUBSTRING(cast(d.mct as character varying), 1, 4) \r\n" + 
					"inner join tb_miso_orbat_unt_dtl dt  on e.sus_no = dt.sus_no\r\n" + 
					"INNER JOIN t_domain_value t4 ON t4.codevalue = dt.type_force AND t4.domainid = 'TYPEOFFORCE'  and dt.status_sus_no = 'Active'    \r\n" + 
					"LEFT JOIN nrv_hq hq ON hq.form_code_control = dt.form_code_control\r\n" + 
					"where   e.proc_from != '' ) as mct \r\n" + 
					"group by  mct.mct,mct.ue,mct.mct_nomen,fmn,force,unitname,ct_part_i_ii,cat_veh,serviceable_counts,unserviceable_counts\r\n" + 
					"\r\n" + 
					" ) as q \r\n" + searchval   
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			
			int flag=1;
			if(!Search.equals(""))
			{
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}
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
	
	/*-------10--------------- */
	public long get_tms_oh_mr_due_stateTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from (SELECT  distinct on (part.ba_no) \r\n" + 
					"c.short_form as comd,\r\n" + 
					"corps.coprs_name as corps,\r\n" + 
					"div.div_name as div,\r\n" + 
					"bde.bde_name as bde,\r\n" + 
					"        u.unit_name,    u1.line_dte_name as line_dte,\r\n" + 
					"        part.sus_no,\r\n" + 
					"        m.mct_nomen,\r\n" + 
					"		part.ba_no,\r\n" + 
					"round(part.vehcl_kms_run+((part.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) \r\n" + 
					"as kms_run ,        \r\n" + 
					"cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage ,\r\n" + 
					"part.vehcl_classfctn as classification, '' AS approve_date,oh_cal.km1,oh_cal.vintage1,oh_cal.km2,oh_cal.vintage2,oh_cal.km3,oh_cal.vintage3\r\n" + 
					"	 , LEAST(\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage1 AS INTEGER)-1,\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(oh_cal.km1 AS INTEGER) / (CAST(part.vehcl_kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh1_due_date\r\n" + 
					",LEAST(\r\n" + 
					"     CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage2 AS INTEGER)-1,\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km2 AS INTEGER) / (CAST(part.vehcl_kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh2_due_date\r\n" + 
					",\r\n" + 
					" LEAST(\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage3 AS INTEGER)-1,\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km3 AS INTEGER) / (CAST(part.vehcl_kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh3_due_date\r\n" + 
					" FROM tb_tms_census_retrn part\r\n" + 
					" JOIN tb_tms_banum_dirctry d ON d.ba_no::text = part.ba_no::text\r\n" + 
					" JOIN tb_tms_mct_main_master m ON m.mct_main_id = substr(d.mct::character varying::text, 1, 4) AND m.type_of_veh::text = 'A'::text\r\n" + 
					" join tb_miso_orbat_unt_dtl u on part.sus_no = u.sus_no and u.status_sus_no = 'Active' \r\n" + 
					" left JOIN \r\n" + 
					"        (\r\n" + 
					"        SELECT \r\n" + 
					"            mct_main_id,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '2' THEN km END) AS km1,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '2' THEN CAST(vintage AS INTEGER) END) AS vintage1,\r\n" + 
					"			 MAX(CASE WHEN oh_mr = '4' THEN km END) AS km2,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '4' THEN CAST(vintage AS INTEGER) END) AS vintage2,\r\n" + 
					"      MAX(CASE WHEN oh_mr = '6' THEN km END) AS km3,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '6' THEN CAST(vintage AS INTEGER) END) AS vintage3\r\n" + 
					"        FROM \r\n" + 
					"            tb_tms_mct_main_oh_mr\r\n" + 
					"        GROUP BY \r\n" + 
					"            mct_main_id\r\n" + 
					"        ) AS oh_cal  ON oh_cal.mct_main_id = m.mct_main_id \r\n" + 
					"left join tb_miso_orbat_line_dte u1 on  u.arm_code = u1.arm_code left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n" + 
					"left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n" + 
					"left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n" + 
					"left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n" + 
					"inner  join (\r\n" + 
					"	select part2.ba_no,\r\n" + 
					"   CASE\r\n" + 
					"        WHEN SUBSTRING(part2.ba_no, 1, 2) >= '00' AND SUBSTRING(part2.ba_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.ba_no, 1, 2)\r\n" + 
					"        WHEN SUBSTRING(part2.ba_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.ba_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.ba_no, 1, 2)\r\n" + 
					"    END AS extracted_year\r\n" + 
					"	 from tb_tms_census_retrn part2\r\n" + 
					") as ba_registraion on ba_registraion.ba_no = part.ba_no  \r\n" + 
					" where  part.ba_no not in (select ba_no from tb_tms_cin cin where  cin.ba_no =part.ba_no and status='0' and   type_of_veh='A') \r\n" + 
					" UNION all\r\n" + 
					" SELECT distinct on (part.ba_no) \r\n" + 
					"c.short_form as comd,\r\n" + 
					"corps.coprs_name as corps,\r\n" + 
					"div.div_name as div,\r\n" + 
					"bde.bde_name as bde,\r\n" + 
					"        u.unit_name,    u1.line_dte_name as line_dte,\r\n" + 
					"        part.sus_no,\r\n" + 
					"        m.mct_nomen,\r\n" + 
					"		part.ba_no,\r\n" + 
					"round(part.kms_run+((part.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) \r\n" + 
					"as kms_run ,        \r\n" + 
					"cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage ,\r\n" + 
					"part.classification as classification, '' AS approve_date,oh_cal.km1,oh_cal.vintage1,oh_cal.km2,oh_cal.vintage2,oh_cal.km3,oh_cal.vintage3\r\n" + 
					"	 , LEAST(\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage1 AS INTEGER)-1,\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(oh_cal.km1 AS INTEGER) / (CAST(part.kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh1_due_date\r\n" + 
					", LEAST(\r\n" + 
					"     CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage2 AS INTEGER)-1,\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km2 AS INTEGER) / (CAST(part.kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh2_due_date\r\n" + 
					",\r\n" + 
					" LEAST(\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage3 AS INTEGER)-1,\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km3 AS INTEGER) / (CAST(part.kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh3_due_date\r\n" + 
					"FROM tb_tms_mvcr_parta_dtl part\r\n" + 
					" JOIN tb_tms_banum_dirctry d ON d.ba_no::text = part.ba_no::text\r\n" + 
					" JOIN tb_tms_mct_main_master m ON m.mct_main_id = substr(d.mct::character varying::text, 1, 4) AND m.type_of_veh::text = 'A'::text\r\n" + 
					" join tb_miso_orbat_unt_dtl u on part.sus_no = u.sus_no and u.status_sus_no = 'Active' \r\n" + 
					" left JOIN \r\n" + 
					"        (\r\n" + 
					"        SELECT \r\n" + 
					"            mct_main_id,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '2' THEN km END) AS km1,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '2' THEN CAST(vintage AS INTEGER) END) AS vintage1,\r\n" + 
					"			 MAX(CASE WHEN oh_mr = '4' THEN km END) AS km2,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '4' THEN CAST(vintage AS INTEGER) END) AS vintage2,\r\n" + 
					"      MAX(CASE WHEN oh_mr = '6' THEN km END) AS km3,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '6' THEN CAST(vintage AS INTEGER) END) AS vintage3\r\n" + 
					"        FROM \r\n" + 
					"            tb_tms_mct_main_oh_mr\r\n" + 
					"        GROUP BY \r\n" + 
					"            mct_main_id\r\n" + 
					"        ) AS oh_cal  ON oh_cal.mct_main_id = m.mct_main_id \r\n" + 
					"left join tb_miso_orbat_line_dte u1 on  u.arm_code = u1.arm_code left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n" + 
					"left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n" + 
					"left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n" + 
					"left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n" + 
					"inner  join (\r\n" + 
					"	select part2.ba_no,\r\n" + 
					"   CASE\r\n" + 
					"        WHEN SUBSTRING(part2.ba_no, 1, 2) >= '00' AND SUBSTRING(part2.ba_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.ba_no, 1, 2)\r\n" + 
					"        WHEN SUBSTRING(part2.ba_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.ba_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.ba_no, 1, 2)\r\n" + 
					"    END AS extracted_year\r\n" + 
					"	 from tb_tms_mvcr_parta_dtl part2\r\n" + 
					") as ba_registraion on ba_registraion.ba_no = part.ba_no where  \r\n" + 
					"part.ba_no not in (select ba_no from tb_tms_cin cin where  cin.ba_no =part.ba_no and status='0'  and type_of_veh='B' ) \r\n" + 
					"union all\r\n" + 
					"SELECT distinct on (ba_no)\r\n" + 
					"c.short_form as comd,\r\n" + 
					"        corps.coprs_name as corps,\r\n" + 
					"        div.div_name as div,\r\n" + 
					"        bde.bde_name as bde,\r\n" + 
					"        u.unit_name,u1.line_dte_name as line_dte,\r\n" + 
					"        part.sus_no,\r\n" + 
					"        m.mct_nomen,\r\n" + 
					"		part.em_no as ba_no,\r\n" + 
					" round(part.current_km_run+((part.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run ,\r\n" + 
					"cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage ,\r\n" + 
					"part.classification as classification,'' AS approve_date\r\n" + 
					",oh_cal.km1,oh_cal.vintage1,oh_cal.km2,oh_cal.vintage2,oh_cal.km3,oh_cal.vintage3\r\n" + 
					"\r\n" + 
					",LEAST(\r\n" + 
					"        CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage1 AS INTEGER)-1,\r\n" + 
					"        CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(oh_cal.km1 AS INTEGER) / (CAST(part.current_km_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh1_due_date\r\n" + 
					",\r\n" + 
					"  LEAST(\r\n" + 
					"          CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage2 AS INTEGER)-1,\r\n" + 
					"        CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km2 AS INTEGER) / (CAST(part.current_km_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh2_due_date\r\n" + 
					",\r\n" + 
					"  LEAST(\r\n" + 
					"        CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage3 AS INTEGER)-1,\r\n" + 
					"        CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km3 AS INTEGER) / (CAST(part.current_km_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh3_due_date\r\n" + 
					"FROM tb_tms_emar_report part\r\n" + 
					" JOIN tb_tms_banum_dirctry d ON d.ba_no::text = part.em_no::text\r\n" + 
					" JOIN tb_tms_mct_main_master m ON m.mct_main_id = substr(d.mct::character varying::text, 1, 4) AND m.type_of_veh::text = 'C'::text\r\n" + 
					" join tb_miso_orbat_unt_dtl u on part.sus_no = u.sus_no and u.status_sus_no = 'Active' \r\n" + 
					" inner   join (\r\n" + 
					"    select part2.em_no,\r\n" + 
					"      CASE\r\n" + 
					"                WHEN SUBSTRING(part2.em_no, 1, 2) >= '00' AND SUBSTRING(part2.em_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.em_no, 1, 2)\r\n" + 
					"                WHEN SUBSTRING(part2.em_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.em_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.em_no, 1, 2)\r\n" + 
					"        END AS extracted_year\r\n" + 
					"     from tb_tms_emar_report part2\r\n" + 
					") as ba_registraion on ba_registraion.em_no = part.em_no   \r\n" + 
					"   left JOIN  (SELECT  mct_main_id,MAX(CASE WHEN oh_mr = '2' THEN km END) AS km1, MAX(CASE WHEN oh_mr = '2' THEN CAST(vintage AS INTEGER) END) AS vintage1,\r\n" + 
					"                           \r\n" + 
					"             MAX(CASE WHEN oh_mr = '4' THEN km END) AS km2, MAX(CASE WHEN oh_mr = '4' THEN CAST(vintage AS INTEGER) END) AS vintage2,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '6' THEN km END) AS km3,MAX(CASE WHEN oh_mr = '6' THEN CAST(vintage AS INTEGER) END) AS vintage3\r\n" + 
					"                FROM \r\n" + 
					"                        tb_tms_mct_main_oh_mr\r\n" + 
					"                GROUP BY \r\n" + 
					"                        mct_main_id\r\n" + 
					"                ) AS oh_cal   ON oh_cal.mct_main_id = m.mct_main_id \r\n" + 
					" left join tb_miso_orbat_line_dte u1 on  u.arm_code = u1.arm_code     left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n" + 
					" left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n" + 
					"left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n" + 
					"left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n" + 
					"where\r\n" + 
					"  part.em_no not in (select ba_no from tb_tms_cin cin where  cin.ba_no =part.em_no and status='0' and type_of_veh='C') \r\n" + 
					" ) app";


			PreparedStatement stmt = conn.prepareStatement(q);
			

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
	
	public List<Map<String, Object>> get_tms_oh_mr_due_stateTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
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
			
		
			q="SELECT  distinct on (part.ba_no) \r\n" + 
					"c.short_form as comd,\r\n" + 
					"corps.coprs_name as corps,\r\n" + 
					"div.div_name as div,\r\n" + 
					"bde.bde_name as bde,\r\n" + 
					"        u.unit_name,    u1.line_dte_name as line_dte,\r\n" + 
					"        part.sus_no,\r\n" + 
					"        m.mct_nomen,\r\n" + 
					"		part.ba_no,\r\n" + 
					"round(part.vehcl_kms_run+((part.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) \r\n" + 
					"as kms_run ,        \r\n" + 
					"cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage ,\r\n" + 
					"part.vehcl_classfctn as classification, '' AS approve_date,oh_cal.km1,oh_cal.vintage1,oh_cal.km2,oh_cal.vintage2,oh_cal.km3,oh_cal.vintage3\r\n" + 
					"	 , LEAST(\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage1 AS INTEGER)-1,\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(oh_cal.km1 AS INTEGER) / (CAST(part.vehcl_kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh1_due_date\r\n" + 
					",LEAST(\r\n" + 
					"     CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage2 AS INTEGER)-1,\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km2 AS INTEGER) / (CAST(part.vehcl_kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh2_due_date\r\n" + 
					",\r\n" + 
					" LEAST(\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage3 AS INTEGER)-1,\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km3 AS INTEGER) / (CAST(part.vehcl_kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh3_due_date\r\n" + 
					" FROM tb_tms_census_retrn part\r\n" + 
					" JOIN tb_tms_banum_dirctry d ON d.ba_no::text = part.ba_no::text\r\n" + 
					" JOIN tb_tms_mct_main_master m ON m.mct_main_id = substr(d.mct::character varying::text, 1, 4) AND m.type_of_veh::text = 'A'::text\r\n" + 
					" join tb_miso_orbat_unt_dtl u on part.sus_no = u.sus_no and u.status_sus_no = 'Active' \r\n" + 
					" left JOIN \r\n" + 
					"        (\r\n" + 
					"        SELECT \r\n" + 
					"            mct_main_id,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '2' THEN km END) AS km1,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '2' THEN CAST(vintage AS INTEGER) END) AS vintage1,\r\n" + 
					"			 MAX(CASE WHEN oh_mr = '4' THEN km END) AS km2,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '4' THEN CAST(vintage AS INTEGER) END) AS vintage2,\r\n" + 
					"      MAX(CASE WHEN oh_mr = '6' THEN km END) AS km3,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '6' THEN CAST(vintage AS INTEGER) END) AS vintage3\r\n" + 
					"        FROM \r\n" + 
					"            tb_tms_mct_main_oh_mr\r\n" + 
					"        GROUP BY \r\n" + 
					"            mct_main_id\r\n" + 
					"        ) AS oh_cal  ON oh_cal.mct_main_id = m.mct_main_id \r\n" + 
					"left join tb_miso_orbat_line_dte u1 on  u.arm_code = u1.arm_code left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n" + 
					"left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n" + 
					"left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n" + 
					"left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n" + 
					"inner  join (\r\n" + 
					"	select part2.ba_no,\r\n" + 
					"   CASE\r\n" + 
					"        WHEN SUBSTRING(part2.ba_no, 1, 2) >= '00' AND SUBSTRING(part2.ba_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.ba_no, 1, 2)\r\n" + 
					"        WHEN SUBSTRING(part2.ba_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.ba_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.ba_no, 1, 2)\r\n" + 
					"    END AS extracted_year\r\n" + 
					"	 from tb_tms_census_retrn part2\r\n" + 
					") as ba_registraion on ba_registraion.ba_no = part.ba_no  \r\n" + 
					" where  part.ba_no not in (select ba_no from tb_tms_cin cin where  cin.ba_no =part.ba_no and status='0' and   type_of_veh='A') \r\n" + 
					" UNION all\r\n" + 
					" SELECT distinct on (part.ba_no) \r\n" + 
					"c.short_form as comd,\r\n" + 
					"corps.coprs_name as corps,\r\n" + 
					"div.div_name as div,\r\n" + 
					"bde.bde_name as bde,\r\n" + 
					"        u.unit_name,    u1.line_dte_name as line_dte,\r\n" + 
					"        part.sus_no,\r\n" + 
					"        m.mct_nomen,\r\n" + 
					"		part.ba_no,\r\n" + 
					"round(part.kms_run+((part.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) \r\n" + 
					"as kms_run ,        \r\n" + 
					"cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage ,\r\n" + 
					"part.classification as classification, '' AS approve_date,oh_cal.km1,oh_cal.vintage1,oh_cal.km2,oh_cal.vintage2,oh_cal.km3,oh_cal.vintage3\r\n" + 
					"	 , LEAST(\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage1 AS INTEGER)-1,\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(oh_cal.km1 AS INTEGER) / (CAST(part.kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh1_due_date\r\n" + 
					", LEAST(\r\n" + 
					"     CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage2 AS INTEGER)-1,\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km2 AS INTEGER) / (CAST(part.kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh2_due_date\r\n" + 
					",\r\n" + 
					" LEAST(\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage3 AS INTEGER)-1,\r\n" + 
					"    CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km3 AS INTEGER) / (CAST(part.kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh3_due_date\r\n" + 
					"FROM tb_tms_mvcr_parta_dtl part\r\n" + 
					" JOIN tb_tms_banum_dirctry d ON d.ba_no::text = part.ba_no::text\r\n" + 
					" JOIN tb_tms_mct_main_master m ON m.mct_main_id = substr(d.mct::character varying::text, 1, 4) AND m.type_of_veh::text = 'A'::text\r\n" + 
					" join tb_miso_orbat_unt_dtl u on part.sus_no = u.sus_no and u.status_sus_no = 'Active' \r\n" + 
					" left JOIN \r\n" + 
					"        (\r\n" + 
					"        SELECT \r\n" + 
					"            mct_main_id,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '2' THEN km END) AS km1,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '2' THEN CAST(vintage AS INTEGER) END) AS vintage1,\r\n" + 
					"			 MAX(CASE WHEN oh_mr = '4' THEN km END) AS km2,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '4' THEN CAST(vintage AS INTEGER) END) AS vintage2,\r\n" + 
					"      MAX(CASE WHEN oh_mr = '6' THEN km END) AS km3,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '6' THEN CAST(vintage AS INTEGER) END) AS vintage3\r\n" + 
					"        FROM \r\n" + 
					"            tb_tms_mct_main_oh_mr\r\n" + 
					"        GROUP BY \r\n" + 
					"            mct_main_id\r\n" + 
					"        ) AS oh_cal  ON oh_cal.mct_main_id = m.mct_main_id \r\n" + 
					"left join tb_miso_orbat_line_dte u1 on  u.arm_code = u1.arm_code left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n" + 
					"left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n" + 
					"left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n" + 
					"left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n" + 
					"inner  join (\r\n" + 
					"	select part2.ba_no,\r\n" + 
					"   CASE\r\n" + 
					"        WHEN SUBSTRING(part2.ba_no, 1, 2) >= '00' AND SUBSTRING(part2.ba_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.ba_no, 1, 2)\r\n" + 
					"        WHEN SUBSTRING(part2.ba_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.ba_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.ba_no, 1, 2)\r\n" + 
					"    END AS extracted_year\r\n" + 
					"	 from tb_tms_mvcr_parta_dtl part2\r\n" + 
					") as ba_registraion on ba_registraion.ba_no = part.ba_no where  \r\n" + 
					"part.ba_no not in (select ba_no from tb_tms_cin cin where  cin.ba_no =part.ba_no and status='0'  and type_of_veh='B' ) \r\n" + 
					"union all\r\n" + 
					"SELECT distinct on (ba_no)\r\n" + 
					"c.short_form as comd,\r\n" + 
					"        corps.coprs_name as corps,\r\n" + 
					"        div.div_name as div,\r\n" + 
					"        bde.bde_name as bde,\r\n" + 
					"        u.unit_name,u1.line_dte_name as line_dte,\r\n" + 
					"        part.sus_no,\r\n" + 
					"        m.mct_nomen,\r\n" + 
					"		part.em_no as ba_no,\r\n" + 
					" round(part.current_km_run+((part.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run ,\r\n" + 
					"cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage ,\r\n" + 
					"part.classification as classification,'' AS approve_date\r\n" + 
					",oh_cal.km1,oh_cal.vintage1,oh_cal.km2,oh_cal.vintage2,oh_cal.km3,oh_cal.vintage3\r\n" + 
					"\r\n" + 
					",LEAST(\r\n" + 
					"        CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage1 AS INTEGER)-1,\r\n" + 
					"        CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(oh_cal.km1 AS INTEGER) / (CAST(part.current_km_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh1_due_date\r\n" + 
					",\r\n" + 
					"  LEAST(\r\n" + 
					"          CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage2 AS INTEGER)-1,\r\n" + 
					"        CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km2 AS INTEGER) / (CAST(part.current_km_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh2_due_date\r\n" + 
					",\r\n" + 
					"  LEAST(\r\n" + 
					"        CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage3 AS INTEGER)-1,\r\n" + 
					"        CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km3 AS INTEGER) / (CAST(part.current_km_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n" + 
					") AS oh3_due_date\r\n" + 
					"FROM tb_tms_emar_report part\r\n" + 
					" JOIN tb_tms_banum_dirctry d ON d.ba_no::text = part.em_no::text\r\n" + 
					" JOIN tb_tms_mct_main_master m ON m.mct_main_id = substr(d.mct::character varying::text, 1, 4) AND m.type_of_veh::text = 'C'::text\r\n" + 
					" join tb_miso_orbat_unt_dtl u on part.sus_no = u.sus_no and u.status_sus_no = 'Active' \r\n" + 
					" inner   join (\r\n" + 
					"    select part2.em_no,\r\n" + 
					"      CASE\r\n" + 
					"                WHEN SUBSTRING(part2.em_no, 1, 2) >= '00' AND SUBSTRING(part2.em_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.em_no, 1, 2)\r\n" + 
					"                WHEN SUBSTRING(part2.em_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.em_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.em_no, 1, 2)\r\n" + 
					"        END AS extracted_year\r\n" + 
					"     from tb_tms_emar_report part2\r\n" + 
					") as ba_registraion on ba_registraion.em_no = part.em_no   \r\n" + 
					"   left JOIN  (SELECT  mct_main_id,MAX(CASE WHEN oh_mr = '2' THEN km END) AS km1, MAX(CASE WHEN oh_mr = '2' THEN CAST(vintage AS INTEGER) END) AS vintage1,\r\n" + 
					"                           \r\n" + 
					"             MAX(CASE WHEN oh_mr = '4' THEN km END) AS km2, MAX(CASE WHEN oh_mr = '4' THEN CAST(vintage AS INTEGER) END) AS vintage2,\r\n" + 
					"            MAX(CASE WHEN oh_mr = '6' THEN km END) AS km3,MAX(CASE WHEN oh_mr = '6' THEN CAST(vintage AS INTEGER) END) AS vintage3\r\n" + 
					"                FROM \r\n" + 
					"                        tb_tms_mct_main_oh_mr\r\n" + 
					"                GROUP BY \r\n" + 
					"                        mct_main_id\r\n" + 
					"                ) AS oh_cal   ON oh_cal.mct_main_id = m.mct_main_id \r\n" + 
					" left join tb_miso_orbat_line_dte u1 on  u.arm_code = u1.arm_code     left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n" + 
					" left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n" + 
					"left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n" + 
					"left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n" + 
					"where\r\n" + 
					"  part.em_no not in (select ba_no from tb_tms_cin cin where  cin.ba_no =part.em_no and status='0' and type_of_veh='C') \r\n" + 
					" "
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			
			
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
	
	/*-------11--------------- */
	public long get_mms_new_reltable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String searchval="";
			if(!Search.equals(""))
			{
				searchval=" where (   upper(to_char(to_date(a.ro_date, 'DD-MM-YYYY'), 'DD-MON-YYYY')) like ?    or  upper(t.label) like   ? or  "
						+ "upper(a1.unit_name) like   ?  or  upper(a2.unit_name) like   ?   "
						+"  )  ";
			}
			
			
			
			
			q = "select count(app.*) from (select to_char(to_date(a.ro_date, 'DD-MM-YYYY'), 'DD-MON-YYYY')  as ro_date,t.label as ro_type,a1.unit_name as from_unit,a2.unit_name as to_unit,a.ro_qty from mms_tb_ro_mstr a inner join\r\n" + 
					"tb_miso_orbat_unt_dtl a1 on a.rel_depot_sus = a1.sus_no and a1.status_sus_no='Active'\r\n" + 
					"inner join tb_miso_orbat_unt_dtl a2 on a.to_sus_no = a2.sus_no and a2.status_sus_no='Active'\r\n" + 
					"inner join mms_domain_values t on a.ro_type = t.codevalue  and  t.domainid='MMSRO'"
					+searchval
					+ ") app";


			PreparedStatement stmt = conn.prepareStatement(q);
			
			int flag=1;
			if(!Search.equals(""))
			{
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
				
			}
		
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
	
	public List<Map<String, Object>> get_mms_new_reltable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
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
			String searchval="";
			if(!Search.equals(""))
			{
				searchval=" where (   upper(to_char(to_date(a.ro_date, 'DD-MM-YYYY'), 'DD-MON-YYYY')) like ?    or upper(t.label) like   ? or "
						+ "upper(a1.unit_name) like   ? or  upper(a2.unit_name) like   ?   "
						+"  )  ";
			}
		
			q="select to_char(to_date(a.ro_date, 'DD-MM-YYYY'), 'DD-MON-YYYY') as ro_date,t.label as ro_type,a1.unit_name as from_unit,a2.unit_name as to_unit,a.ro_qty from mms_tb_ro_mstr a inner join\r\n" + 
					"tb_miso_orbat_unt_dtl a1 on a.rel_depot_sus = a1.sus_no and a1.status_sus_no='Active'\r\n" + 
					"inner join tb_miso_orbat_unt_dtl a2 on a.to_sus_no = a2.sus_no and a2.status_sus_no='Active'\r\n" + 
					"inner join mms_domain_values t on a.ro_type = t.codevalue  and  t.domainid='MMSRO'"
					+searchval
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);

			int flag=1;
		if(!Search.equals(""))
		{
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
		
			
		}
			
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
	
	/*-------12------------------- */
	public long get_cont_store_srvc_conTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String searchval="";
			if(!Search.equals(""))
			{
				searchval=" where (  upper(ltrim(TO_CHAR(a.upd_date,'DD-MON-YYYY'),'0')) like ?    or  upper(m.census_no) like   ?  or  "
						+ "upper(m.nomen) like   ?  or  upper(ms.eqpt_regn_no) like   ?  or upper(c.unit_name) like   ?  or  "
						+ "upper(b.unit_name) like   ?  "
						+"  )  ";
			}
			
			
			q = "select count(app.*) from (select ltrim(to_char(a.upd_date,'DD-Mon-YYYY'),'0') as dt,m.census_no as cont_store_type,\r\n" + 
					"	m.nomen,ms.eqpt_regn_no,\r\n" + 
					"	c.unit_name as hq_name,b.unit_name,case WHEN a.service_status = '0' then 'serviceable'\r\n" + 
					"	WHEN a.service_status = '1' then 'unserviceable'  end as service_status from mms_tv_regn_mstr a\r\n" + 
					"	inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no\r\n" + 
					"    inner join mms_tb_regn_mstr_detl ms on ms.census_no=m.census_no\r\n" + 
					"	inner join tb_miso_orbat_unt_dtl b\r\n" + 
					
					"	on a.sus_no=b.sus_no  left join nrv_hq c on b.form_code_control=c.form_code_control "
					+ searchval
					+ ") app";


			PreparedStatement stmt = conn.prepareStatement(q);
			int flag=1;
			if(!Search.equals(""))
			{
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}
			

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
	
	public List<Map<String, Object>> get_cont_store_srvc_conTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
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
			
			String searchval="";
			if(!Search.equals(""))
			{
				searchval=" where (   upper(ltrim(TO_CHAR(a.upd_date,'DD-MON-YYYY'),'0')) like ?    or  upper(m.census_no) like   ? or  "
						+ " upper(m.nomen) like   ?  or   upper(ms.eqpt_regn_no) like   ?  or  upper(c.unit_name) like   ?  or  "
						+ " upper(b.unit_name) like   ?  "
						+"  )  ";
			}
			
			q="select ltrim(to_char(a.upd_date,'DD-Mon-YYYY'),'0') as dt,m.census_no as cont_store_type,\r\n" + 
					"	m.nomen,ms.eqpt_regn_no,\r\n" + 
					"	c.unit_name as hq_name,b.unit_name,case WHEN a.service_status = '0' then 'serviceable'\r\n" + 
					"	WHEN a.service_status = '1' then 'unserviceable'  end as service_status from mms_tv_regn_mstr a\r\n" + 
					"	inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no\r\n" + 
					"    inner join mms_tb_regn_mstr_detl ms on ms.census_no=m.census_no\r\n" + 
					"	inner join tb_miso_orbat_unt_dtl b\r\n" + 
					"	on a.sus_no=b.sus_no  left join nrv_hq c on b.form_code_control=c.form_code_control  "
					+searchval
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			int flag=1;
			if(!Search.equals(""))
			{
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}
			
			
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
	
	/*------13-------------------- */
	public long get_mms_iut_dtlTbale_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String searchval="";
			if(!Search.equals(""))
			{

				searchval=" and ( upper(ltrim(TO_CHAR(TO_DATE(a.rv_date, 'YYYY-MM-DD'), 'DD-MON-YYYY')))  like ? or upper(prf.prf_group) like   ? or  "
						+ " upper(b.unit_name) like   ?  or   upper(c.unit_name) like   ?  or  upper(a.eqpt_regn_no) like   ?  )  ";
			}
			
			q = "select count(app.*) from (SELECT distinct on (a.id) ltrim(TO_CHAR(TO_DATE(a.rv_date, 'YYYY-MM-DD'), 'DD-MON-YYYY')) as date,prf.prf_group as wep_cat,\r\n"
					+ "						        b.unit_name AS source_unit,\r\n"
					+ "						        c.unit_name AS target_unit,\r\n"
					+ "						         REPLACE( a.eqpt_regn_no, ':', ',') as  regn_no,\r\n"
					+ "						       CASE\r\n"
					+ "								WHEN d.SERVICE_STATUS = '0' THEN 'unserviceable'\r\n"
					+ "								ELSE 'serviceable'\r\n"
					+ "								END AS  pstatus,\r\n"
					+ "							  array_length(string_to_array(a.eqpt_regn_no, ':'), 1) as qty\r\n"
					+ "						   FROM mms_tb_child_regn_mstr_detl a\r\n"
					+ "						   LEFT JOIN tb_miso_orbat_unt_dtl b\r\n"
					+ "						     ON b.sus_no = a.from_sus_no\r\n"
					+ "						   LEFT JOIN tb_miso_orbat_unt_dtl c\r\n"
					+ "						     ON c.sus_no = a.to_sus_no\r\n"
					+ "							 inner join cue_tb_miso_prf_group_mst prf\r\n"
					+ "							 on prf.prf_group_code=a.prf_code\r\n"
					+ "							 inner join MMS_TV_REGN_MSTR d\r\n"
					+ "							 on d.census_no=a.census_no\r\n"
					+ "							 where a.status in (1,0) "+searchval+") app";


			PreparedStatement stmt = conn.prepareStatement(q);
			int flag=1;
			if(!Search.equals(""))
			{
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				
			}

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
	
	public List<Map<String, Object>> get_mms_iut_dtlTbale_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
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
			
			String searchval="";
			if(!Search.equals(""))
			{
				searchval=" and ( upper(ltrim(TO_CHAR(TO_DATE(a.rv_date, 'YYYY-MM-DD'), 'DD-MON-YYYY')))  like ? or  upper(prf.prf_group) like   ? or  "
						+ " upper(b.unit_name) like   ?  or   upper(c.unit_name) like   ?  or  upper(a.eqpt_regn_no) like   ?  )  ";
			}
			
			q="SELECT distinct on (a.id) ltrim(TO_CHAR(TO_DATE(a.rv_date, 'YYYY-MM-DD'), 'DD-MON-YYYY')) as date,prf.prf_group as wep_cat,\r\n"
					+ "						        b.unit_name AS source_unit,\r\n"
					+ "						        c.unit_name AS target_unit,\r\n"
					+ "						         REPLACE( a.eqpt_regn_no, ':', ',') as  regn_no,\r\n"
					+ "						       CASE\r\n"
					+ "								WHEN d.SERVICE_STATUS = '0' THEN 'unserviceable'\r\n"
					+ "								ELSE 'serviceable'\r\n"
					+ "								END AS  pstatus,\r\n"
					+ "							  array_length(string_to_array(a.eqpt_regn_no, ':'), 1) as qty\r\n"
					+ "						   FROM mms_tb_child_regn_mstr_detl a\r\n"
					+ "						   LEFT JOIN tb_miso_orbat_unt_dtl b\r\n"
					+ "						     ON b.sus_no = a.from_sus_no\r\n"
					+ "						   LEFT JOIN tb_miso_orbat_unt_dtl c\r\n"
					+ "						     ON c.sus_no = a.to_sus_no\r\n"
					+ "							 inner join cue_tb_miso_prf_group_mst prf\r\n"
					+ "							 on prf.prf_group_code=a.prf_code\r\n"
					+ "							 inner join MMS_TV_REGN_MSTR d\r\n"
					+ "							 on d.census_no=a.census_no\r\n"
					+ "							 where a.status in (1,0) "+ searchval
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			
			int flag=1;
			if(!Search.equals(""))
			{
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				
			}
	      ResultSet rs = stmt.executeQuery();   
//	      System.out.println("13: " + stmt);
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
	
	/*-------14------------------- */
	public long get_mms_bkld_dtlTbale_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			
			String searchval="";
			if(!Search.equals(""))
			{
				searchval=" where (   upper(ltrim(TO_CHAR(a.rv_date,'DD-MON-YYYY'),'0')) like ?    or upper(b.unit_name) like   ? or "
						+ "upper(a.deposit_depot_name) like   ? or  upper(dt.eqpt_regn_no) like   ?  or upper(t2.label) like   ? "
						+"  )  ";
			}
			
			
			

			
			q = "select count(app.*) from ( select DISTINCT ltrim(to_char(a.rv_date,'DD-Mon-YYYY'),'0') AS rv_date,b.unit_name as fromunit,a.deposit_depot_name as depo,"
					+ "dt.eqpt_regn_no, t2.label as type_of_eqpt_n,1 as qty\r\n" + 
					"	  from mms_tb_imp_drr a INNER join tb_miso_orbat_unt_dtl b on a.recv_sus_no=b.sus_no\r\n" + 
					"      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.recv_sus_no \r\n" + 
					"       inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' "
					+ searchval
					+ " ) app";


			PreparedStatement stmt = conn.prepareStatement(q);
			

			int flag=1;
		if(!Search.equals(""))
		{
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
		
			
		}
		
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
	
	public List<Map<String, Object>> get_mms_bkld_dtlTbale_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
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
			String searchval="";
			if(!Search.equals(""))
			{
				searchval=" where (   upper(ltrim(TO_CHAR(a.rv_date,'DD-MON-YYYY'),'0')) like ?    or upper(b.unit_name) like   ? or "
						+ "upper(a.deposit_depot_name) like   ? or  upper(dt.eqpt_regn_no) like   ?  or upper(t2.label) like   ? "
						+"  )  ";
			}
			
			
			
		
			q=" select DISTINCT ltrim(to_char(a.rv_date,'DD-Mon-YYYY'),'0') AS rv_date,b.unit_name as fromunit,a.deposit_depot_name as depo,dt.eqpt_regn_no, t2.label as type_of_eqpt_n,1 as qty\r\n" + 
					"	  from mms_tb_imp_drr a INNER join tb_miso_orbat_unt_dtl b on a.recv_sus_no=b.sus_no\r\n" + 
					"      inner join mms_tb_regn_mstr_detl dt on dt.to_sus_no=a.recv_sus_no \r\n" + 
					"       inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' "
					+searchval
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			
			int flag=1;
			if(!Search.equals(""))
			{
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
				
			}
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
	
	/*------15-------------------- */
	public long get_mms_updation_stateTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String searchval="";
			if(!Search.equals(""))
			{
				searchval=" and  ( upper(C.UNIT_NAME) like ?    or upper(A.sus_no) like   ? or "
						+ "upper(B.unit_name) like   ? or  upper(B.ct_part_i_ii) like   ?  or upper(T5.label) like   ? "
						+ "  or upper(PRF.prf_group) like     ? )  ";
			}
			q = "select count(app.*) from (SELECT \r\n" + 
					"	C.UNIT_NAME AS HQ_NAME,\r\n" + 
					"	A.SUS_NO,\r\n" + 
					"	B.UNIT_NAME,\r\n" + 
					"	B.ct_part_i_ii,\r\n" + 
					"	T5.label as typeofforce,\r\n" + 
					"	PRF.PRF_GROUP,\r\n" + 
					"	count(distinct a.sus_no) as UPDATED,	\r\n" + 
					"	count(distinct b1.sus_no) as yet_to_UPDATED	\r\n" + 
					"	FROM MMS_TV_REGN_MSTR A\r\n" + 
					"LEFT JOIN MMS_TB_MLCCS_MSTR_DETL M ON A.CENSUS_NO = M.CENSUS_NO\r\n" + 
					"LEFT JOIN CUE_TB_MISO_PRF_GROUP_MST PRF ON M.PRF_CODE = PRF.PRF_GROUP_CODE\r\n" + 
					"LEFT JOIN TB_MISO_ORBAT_UNT_DTL B ON A.SUS_NO = B.SUS_NO\r\n" + 
					"LEFT JOIN NRV_HQ C ON B.FORM_CODE_CONTROL = C.FORM_CODE_CONTROL\r\n" + 
					"LEFT JOIN t_domain_value T5 ON b.type_force = t5.CODEVALUE AND T5.DOMAINID = 'TYPEOFFORCE'\r\n" + 
					"left join mms_tb_unit_upload_document doc on   doc.sus_no = b.sus_no AND b.status_sus_no = 'Active' \r\n" + 
					"and to_char(now(), 'mm-yyyy'::text) = to_char(doc.created_on, 'mm-yyyy'::text)\r\n" + 
					"LEFT JOIN TB_MISO_ORBAT_UNT_DTL B1 ON A.SUS_NO = B1.SUS_NO\r\n" + 
					"left join mms_tb_unit_upload_document doc1 on   doc1.sus_no = b1.sus_no AND b1.status_sus_no = 'Active' \r\n" + 
					"						and to_char(now(), 'mm-yyyy'::text) != to_char(doc1.created_on, 'mm-yyyy'::text)						\r\n" + 
					"\r\n" + 
					"WHERE B.STATUS_SUS_NO = 'Active'\r\n" + searchval+
					"GROUP BY 1,2,\r\n" + 
					"	3,4,\r\n" + 
					"	5,6\r\n" + 
					"	\r\n" + 
					"ORDER BY \r\n" + 
					"	A.SUS_NO) app";


			PreparedStatement stmt = conn.prepareStatement(q);

			int flag=1;
		if(!Search.equals(""))
		{
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			flag++;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
		}
		

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
	
	public List<Map<String, Object>> get_mms_updation_stateTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
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
			String searchval="";
			if(!Search.equals(""))
			{
				searchval=" and  ( upper(C.UNIT_NAME) like ?    or upper(A.sus_no) like   ? or "
						+ "upper(B.unit_name) like   ?  or  upper(B.ct_part_i_ii) like   ?  or upper(T5.label) like   ? "
						+ "  or upper(PRF.prf_group) like     ? )  ";
			}
			
		
			q="SELECT \r\n" + 
					"	C.UNIT_NAME AS hq_name,\r\n" + 
					"	A.sus_no,\r\n" + 
					"	B.unit_name,\r\n" + 
					"	B.ct_part_i_ii,\r\n" + 
					"	T5.label as typeofforce,\r\n" + 
					"	PRF.prf_group,\r\n" + 
					"	count(distinct a.sus_no) as updated,	\r\n" + 
					"	count(distinct b1.sus_no) as yet_to_up	\r\n" + 
					"	FROM MMS_TV_REGN_MSTR A\r\n" + 
					"LEFT JOIN MMS_TB_MLCCS_MSTR_DETL M ON A.CENSUS_NO = M.CENSUS_NO\r\n" + 
					"LEFT JOIN CUE_TB_MISO_PRF_GROUP_MST PRF ON M.PRF_CODE = PRF.PRF_GROUP_CODE\r\n" + 
					"LEFT JOIN TB_MISO_ORBAT_UNT_DTL B ON A.SUS_NO = B.SUS_NO\r\n" + 
					"LEFT JOIN NRV_HQ C ON B.FORM_CODE_CONTROL = C.FORM_CODE_CONTROL\r\n" + 
					"LEFT JOIN t_domain_value T5 ON b.type_force = t5.CODEVALUE AND T5.DOMAINID = 'TYPEOFFORCE'\r\n" + 
					"left join mms_tb_unit_upload_document doc on   doc.sus_no = b.sus_no AND b.status_sus_no = 'Active' \r\n" + 
					"and to_char(now(), 'mm-yyyy'::text) = to_char(doc.created_on, 'mm-yyyy'::text)\r\n" + 
					"LEFT JOIN TB_MISO_ORBAT_UNT_DTL B1 ON A.SUS_NO = B1.SUS_NO\r\n" + 
					"left join mms_tb_unit_upload_document doc1 on   doc1.sus_no = b1.sus_no AND b1.status_sus_no = 'Active' \r\n" + 
					"						and to_char(now(), 'mm-yyyy'::text) != to_char(doc1.created_on, 'mm-yyyy'::text)						\r\n" + 
					"\r\n" + 
					"WHERE B.STATUS_SUS_NO = 'Active'\r\n" +  searchval+
					"GROUP BY 1,2,\r\n" + 
					"	3,4,\r\n" + 
					"	5,6\r\n" + 
					"	\r\n" + 
					"ORDER BY \r\n" + 
					"	A.SUS_NO"
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			int flag=1;
			if(!Search.equals(""))
			{
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}
			
			
	      ResultSet rs = stmt.executeQuery();   
//	      System.out.println("15: "+stmt);
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
	
	/*-----16--------- get_mms_defi_stateTable----16----------- */
	public long get_mms_defi_stateTable_count(String Search,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException {
		//String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String searchval="";
			if(!Search.equals(""))
			{
				searchval=" where ( upper(i.item_type) like ?    or upper(pr.prf_group) like   ? or "
						+ "upper(t1.label) like   ? or  upper(t2.label) like   ?  or upper(r.unit_name) like   ? "
						+ "  or upper(hq.unit_name) like   ?  or upper(t4.label) like   ? )  ";
			}
			q = "select count(app.*) from (select \r\n" + 
					"	pr.prf_group,\r\n" + 
					"	i.item_type as item_nomen,\r\n" + 
					"	t1.label as type_of_hldg_n,\r\n" + 
					"	t2.label as type_of_eqpt_n,\r\n" + 
					"	cast(r.totue as varchar) as totue,\r\n" + 
					"	r.totuh,\r\n" + 
					"	TO_CHAR(r.upd_date, 'dd-mm-yyyy') as upd_date ,\r\n" + 
					"	r.unit_name,hq.unit_name as fmn,t4.label as force\r\n" + 
					"from (\r\n" + 
					"	select p.prf_code,\r\n" + 
					"			p.item_code,\r\n" + 
					"			'A0' as type_of_hldg,\r\n" + 
					"			p.type_of_eqpt,\r\n" + 
					"			sum(ueqty) as totue,\r\n" + 
					"			sum(uhqty) as totuh,\r\n" + 
					"			max(upd_date) as upd_date,p.unit_name ,p.form_code_control,p.sus_no\r\n" + 
					"	from (select 	b.prf_code,\r\n" + 
					"					b.item_code,\r\n" + 
					"					a.census_no,\r\n" + 
					"					a.type_of_eqpt,\r\n" + 
					"					0 as ueqty,\r\n" + 
					"					a.tothol as uhqty,\r\n" + 
					"					a.data_app_date as upd_date,\r\n" + 
					"		  a.unit_name,a.form_code_control,a.sus_no\r\n" + 
					"		  from (\r\n" + 
					"				select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1  as tothol ,\r\n" + 
					"				data_app_date, u.unit_name,u.form_code_control,u.sus_no\r\n" + 
					"				from mms_tb_regn_mstr_detl a \r\n" + 
					"				LEFT JOIN tb_miso_orbat_unt_dtl u ON a.sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n" + 
					"				where   a.op_status='1' and a.type_of_hldg='A0'\r\n" + 
					"			union All\r\n" + 
					"			select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1 as tothol ,\r\n" + 
					"				data_app_date, u.unit_name,u.form_code_control,u.sus_no\r\n" + 
					"				from mms_tb_depot_regn_mstr_detl a \r\n" + 
					"				LEFT JOIN tb_miso_orbat_unt_dtl u ON a.sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n" + 
					"				where a.op_status='1' and a.type_of_hldg='A0'\r\n" + 
					"			union All\r\n" + 
					"			select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1 as uh ,\r\n" + 
					"				data_app_date, u.unit_name,u.form_code_control,u.sus_no\r\n" + 
					"				from mms_tb_regn_oth_mstr a \r\n" + 
					"				LEFT JOIN tb_miso_orbat_unt_dtl u ON a.to_sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n" + 
					"				where  a.op_status='1' and  a.type_of_hldg='A0'\r\n" + 
					"		  ) as a\r\n" + 
					"				inner join mms_tb_mlccs_mstr_detl b on a.census_no=b.census_no  \r\n" + 
					"		union all   \r\n" + 
					"			select 	v.prf_group_code as prf_code,\r\n" + 
					"					v.item_code,\r\n" + 
					"					'' as census_no,\r\n" + 
					"					(case when upper(v.type)='CES' then '2' else '1' end) as type_of_eqpt,\r\n" + 
					"					v.total_ue_qty as ueqty,\r\n" + 
					"					0 as uhqty,\r\n" + 
					"					null as upd_date,\r\n" + 
					"		  u.unit_name,u.form_code_control,u.sus_no\r\n" + 
					"			from mms_ue_mview  v\r\n" + 
					"		LEFT JOIN tb_miso_orbat_unt_dtl u ON v.sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text ) p  \r\n" + 
					"	group by p.prf_code,p.item_code,p.type_of_eqpt, p.unit_name,p.form_code_control,p.sus_no\r\n" + 
					"	) r  \r\n" + 
					"	left join cue_tb_miso_mms_item_mstr i on r.item_code=i.item_code  \r\n" + 
					"	left join cue_tb_miso_prf_group_mst pr on r.prf_code=pr.prf_group_code  \r\n" + 
					"	left join mms_domain_values t1 on r.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'  \r\n" + 
					"	left join mms_domain_values t2 on r.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'  \r\n" + 
					"	LEFT JOIN tb_miso_orbat_unt_dtl dt on  r.sus_no=dt.sus_no\r\n" + 
					"	LEFT JOIN t_domain_value  t4 on  t4.codevalue=dt.type_force and  t4.domainid='TYPEOFFORCE'  \r\n" + 
					"	left join nrv_hq hq on hq.form_code_control =r.form_code_control\r\n" + 
					searchval+
					"	order by pr.prf_group,r.item_code,r.type_of_hldg,r.type_of_eqpt) app";


			PreparedStatement stmt = conn.prepareStatement(q);
			
			int flag=1;
			if(!Search.equals(""))
			{
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			}
			
			
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
	
	public List<Map<String, Object>> get_mms_defi_stateTable_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String TDate) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	//String SearchValue = GenerateQueryWhereClause_SQL(Search);
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
        String searchval="";
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if(!Search.equals(""))
			{
				searchval=" where ( upper(i.item_type) like ?    or upper(pr.prf_group) like   ? or "
						+ "upper(t1.label) like   ? or  upper(t2.label) like   ?  or upper(r.unit_name) like   ? "
						+ "  or upper(hq.unit_name) like   ?  or upper(t4.label) like   ? )  ";
			}
			
			
			
		
			q="select \r\n" + 
					"	pr.prf_group,\r\n" + 
					"	i.item_type as item_nomen,\r\n" + 
					"	t1.label as type_of_hldg_n,\r\n" + 
					"	t2.label as type_of_eqpt_n,\r\n" + 
					"	cast(r.totue as varchar) as totue,\r\n" + 
					"	r.totuh,\r\n" + 
					"	TO_CHAR(r.upd_date, 'dd-mm-yyyy') as upd_date ,\r\n" + 
					"	r.unit_name,hq.unit_name as fmn,t4.label as force\r\n" + 
					"from (\r\n" + 
					"	select p.prf_code,\r\n" + 
					"			p.item_code,\r\n" + 
					"			'A0' as type_of_hldg,\r\n" + 
					"			p.type_of_eqpt,\r\n" + 
					"			sum(ueqty) as totue,\r\n" + 
					"			sum(uhqty) as totuh,\r\n" + 
					"			max(upd_date) as upd_date,p.unit_name ,p.form_code_control,p.sus_no\r\n" + 
					"	from (select 	b.prf_code,\r\n" + 
					"					b.item_code,\r\n" + 
					"					a.census_no,\r\n" + 
					"					a.type_of_eqpt,\r\n" + 
					"					0 as ueqty,\r\n" + 
					"					a.tothol as uhqty,\r\n" + 
					"					a.data_app_date as upd_date,\r\n" + 
					"		  a.unit_name,a.form_code_control,a.sus_no\r\n" + 
					"		  from (\r\n" + 
					"				select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1  as tothol ,\r\n" + 
					"				data_app_date, u.unit_name,u.form_code_control,u.sus_no\r\n" + 
					"				from mms_tb_regn_mstr_detl a \r\n" + 
					"				LEFT JOIN tb_miso_orbat_unt_dtl u ON a.sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n" + 
					"				where  a.op_status='1' and a.type_of_hldg='A0'\r\n" + 
					"			union All\r\n" + 
					"			select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1 as tothol ,\r\n" + 
					"				data_app_date, u.unit_name,u.form_code_control,u.sus_no\r\n" + 
					"				from mms_tb_depot_regn_mstr_detl a \r\n" + 
					"				LEFT JOIN tb_miso_orbat_unt_dtl u ON a.sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n" + 
					"				where  a.op_status='1' and a.type_of_hldg='A0'\r\n" + 
					"			union All\r\n" + 
					"			select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1 as uh ,\r\n" + 
					"				data_app_date, u.unit_name,u.form_code_control,u.sus_no\r\n" + 
					"				from mms_tb_regn_oth_mstr a \r\n" + 
					"				LEFT JOIN tb_miso_orbat_unt_dtl u ON a.to_sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n" + 
					"				where  a.op_status='1' and  a.type_of_hldg='A0'\r\n" + 
					"		  ) as a\r\n" + 
					"				inner join mms_tb_mlccs_mstr_detl b on a.census_no=b.census_no  \r\n" + 
					"		union all   \r\n" + 
					"			select 	v.prf_group_code as prf_code,\r\n" + 
					"					v.item_code,\r\n" + 
					"					'' as census_no,\r\n" + 
					"					(case when upper(v.type)='CES' then '2' else '1' end) as type_of_eqpt,\r\n" + 
					"					v.total_ue_qty as ueqty,\r\n" + 
					"					0 as uhqty,\r\n" + 
					"					null as upd_date,\r\n" + 
					"		  u.unit_name,u.form_code_control,u.sus_no\r\n" + 
					"			from mms_ue_mview  v\r\n" + 
					"		LEFT JOIN tb_miso_orbat_unt_dtl u ON v.sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text ) p  \r\n" + 
					"	group by p.prf_code,p.item_code,p.type_of_eqpt, p.unit_name,p.form_code_control,p.sus_no\r\n" + 
					"	) r  \r\n" + 
					"	left join cue_tb_miso_mms_item_mstr i on r.item_code=i.item_code  \r\n" + 
					"	left join cue_tb_miso_prf_group_mst pr on r.prf_code=pr.prf_group_code  \r\n" + 
					"	left join mms_domain_values t1 on r.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'  \r\n" + 
					"	left join mms_domain_values t2 on r.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'  \r\n" + 
					"	LEFT JOIN tb_miso_orbat_unt_dtl dt on  r.sus_no=dt.sus_no\r\n" + 
					"	LEFT JOIN t_domain_value  t4 on  t4.codevalue=dt.type_force and  t4.domainid='TYPEOFFORCE'  \r\n" + 
					"	left join nrv_hq hq on hq.form_code_control =r.form_code_control\r\n" + 
					searchval+
					"	order by pr.prf_group,r.item_code,r.type_of_hldg,r.type_of_eqpt"
					+"   limit " +pageL+" OFFSET "+startPage+"";
			
		
		
			stmt=conn.prepareStatement(q);
			int flag=1;
			if(!Search.equals(""))
			{
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag++;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			}
			
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

	
	
	
}
