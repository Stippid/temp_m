package com.dao.psg.Queries;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class Regular_ArmyPersonnel_DAOImpl implements Regular_ArmyPersonnel_DAO{
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	
	public String GenerateQueryWhereClause_SQL(String Search ) {
 		String SearchValue ="";
  		if(!Search.equals("")) {
			Search = Search.toLowerCase();
  			SearchValue =" where ( ";
 			SearchValue +="lower(app.arm_desc) like ? or "
 					+ " cast(app.auth as character varying) = ? or "
 					+ " cast(app.total as character varying) = ? "
 					+ " )";
 		}
    	
   return SearchValue;
 }


  public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search ) {
 		int flag = 0;
 		try {
    		if(!Search.equals("")) {
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");				
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
    		}	
						
 		}catch (Exception e) {}
 		return stmt;
 	}
  
  
  public List<Map<String, Object>> getRegular_ArmyInfantary_ReportDataListDetails_pers(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL_pers(Search );

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select app.arm_desc,app.total,app.auth\r\n" + 
					"from\r\n" + 
					"(select distinct a.arm_desc , COALESCE(sum(o.base_auth + o.mod_auth + o.foot_auth)::integer,0) as auth ,count(p.id) as total     \r\n" + 
					"from  tb_psg_trans_proposed_comm_letter p\r\n" + 
					"inner join tb_miso_orbat_arm_code a on  p.regiment = a.arm_code and p.status in ('1','5') \r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					"left join sus_pers_stdauth o on o.sus_no = orb.sus_no\r\n" + 
					"where p.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n" + 
					"and p.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))\r\n" + 
					"and SUBSTR(a.arm_code,1,3) IN ('071','072','073','074','075','076','077','081','082','083') and a.arm_code not in ('0700','0800')\r\n" + 
					"and    p.type_of_comm_granted != 20  \r\n" +  //ADDED ON 13 JULY 2023 AS PER MISO INSTR
					"group by a.arm_code, a.arm_desc) app " +SearchValue + " ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_pers(stmt,Search );
			System.err.println("query regular arm \n "+stmt.toString());
			
			
			
			
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
	return list;
}

public long getRegular_ArmyInfantary_TotalCountDtl_pers(String Search ) {
		String SearchValue = GenerateQueryWhereClause_SQL_pers(Search);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(app1.*) from (\r\n" + 
					"select app.arm_desc,app.total,app.auth\r\n" + 
					"from\r\n" + 
					"(select distinct a.arm_desc , COALESCE(sum(o.base_auth + o.mod_auth + o.foot_auth)::integer,0) as auth ,count(p.id) as total     \r\n" + 
					"from  tb_psg_trans_proposed_comm_letter p\r\n" + 
					"inner join tb_miso_orbat_arm_code a on  p.regiment = a.arm_code and p.status in ('1','5') \r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					"left join sus_pers_stdauth o on o.sus_no = orb.sus_no\r\n" + 
					"where p.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n" + 
					"and p.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))\r\n" + 
					"and SUBSTR(a.arm_code,1,3) IN ('071','072','073','074','075','076','077','081','082','083') and a.arm_code not in ('0700','0800')\r\n" + 
					"group by a.arm_code, a.arm_desc ) app " + 
					"" + SearchValue +
					") app1  " ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_pers(stmt,Search);
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


	public String GenerateQueryWhereClause_SQL_pers(String Search ) {
		String SearchValue ="";
		if(!Search.equals("")) {
			Search = Search.toLowerCase();
			SearchValue =" where ( ";
			SearchValue +=" lower(app.arm_desc) like ? or "
					+ " cast(app.auth as character varying) = ? or "
					+ " cast(app.total as character varying) = ? "
					+ ")";
			
		}		
return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL_pers(PreparedStatement stmt,String Search ) {
		int flag = 0;
		try {
		if(!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");			
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
		}	
					
		}catch (Exception e) {}
		return stmt;
	}

public ArrayList<ArrayList<String>> pdf_getRegular_ArmyGorkha_ReportDataList()
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
		q="select app.arm_desc,app.total,app.auth\r\n" + 
				"from\r\n" + 
				"(select distinct a.arm_desc , COALESCE(sum(o.base_auth + o.mod_auth + o.foot_auth)::integer,0) as auth ,count(p.id) as total     \r\n" + 
				"from  tb_psg_trans_proposed_comm_letter p\r\n" + 
				"inner join tb_miso_orbat_arm_code a on  p.regiment = a.arm_code and p.status in ('1','5') \r\n" + 
				"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
				"left join sus_pers_stdauth o on o.sus_no = orb.sus_no\r\n" + 
				"where p.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n" + 
				"and p.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))\r\n" + 
				"and SUBSTR(a.arm_code,1,3) IN ('071','072','073','074','075','076','077','081','082','083') and a.arm_code not in ('0700','0800')\r\n" + 
				"group by a.arm_code, a.arm_desc) app " + SearchValue ;
			stmt=conn.prepareStatement(q);
		
			ResultSet rs = stmt.executeQuery();   
			int i =1;  
			while (rs.next()) {
				
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("arm_desc"));//1
				list.add(rs.getString("auth"));//2
				list.add(rs.getString("total"));//3
				alist.add(list);
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
			  }
			}
		}
	return alist;
}
	
public ArrayList<ArrayList<String>> pdf_getRegular_ArmyInfantary_ReportDataList_pers()
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
					
		q="select app.arm_desc,app.total,app.auth\r\n" + 
				"from\r\n" + 
				"(select distinct a.arm_desc , COALESCE(sum(o.base_auth + o.mod_auth + o.foot_auth)::integer,0) as auth ,count(p.id) as total     \r\n" + 
				"from  tb_psg_trans_proposed_comm_letter p\r\n" + 
				"inner join tb_miso_orbat_arm_code a on  p.regiment = a.arm_code and p.status in ('1','5') \r\n" + 
				"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
				"left join sus_pers_stdauth o on o.sus_no = orb.sus_no\r\n" + 
				"where p.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n" + 
				"and p.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))\r\n" + 
				"and SUBSTR(a.arm_code,1,3) IN ('071','072','073','074','075','076','077','081','082','083') and a.arm_code not in ('0700','0800')\r\n" + 
				"group by a.arm_code, a.arm_desc) app " + SearchValue ;
			stmt=conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery(); 
			int i =1;    
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("arm_desc"));//1
				list.add(rs.getString("auth"));//2
				list.add(rs.getString("total"));//3
				alist.add(list);
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
			  }
			}
		}
	return alist;
}

}
