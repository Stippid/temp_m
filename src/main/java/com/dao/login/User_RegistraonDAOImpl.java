package com.dao.login;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class User_RegistraonDAOImpl implements User_RegistraionDAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
	}
	
	
	public boolean checkIsIntegerValue(String Search) {
		return Search.matches("[0-9]+");
	}
	public List<Map<String, Object>> DataTable_Cat_List(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			String ic_no,String status) {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,ic_no,status);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "SELECT a.user_name,a.unit_name,a.sus_no,a.army_no,a.rank,a.appointment,a.created_on,a.user_id,TO_CHAR(a.created_on,'DD-MM-YYYY') as creat,a.id " + 
					"FROM tb_miso_user_registration a "+SearchValue+" ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,ic_no);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int ser = startPage;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser",++ser);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String f = "";
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to View This Data  ?') ){viewData("+ rs.getInt("id")+")}else{ return false;}\"";
				f = "<i class='action_icons action_view'  " + Update + " title='View Data'></i>";
				columns.put("action", f);
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
	
	
	public long DataTable_Cat_TotalCount(String Search,String ic_no,String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,ic_no,status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="SELECT  count(*) \n " +
					"FROM tb_miso_user_registration a \r\n" + 
					""+SearchValue ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,ic_no);
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
	
	public String GenerateQueryWhereClause_SQL(String Search,String ic_no,String status) {
		String SearchValue ="";
		if(!Search.equals("")) { 
			Search = Search.toLowerCase();
			SearchValue =" where (lower(a.ic_no) like ? or lower(a.user_name) like ? or lower(a.unit_name) like ?  or lower(a.sus_no) like ? or lower(a.rank) like ?  or lower(a.mobile_no) like ? or lower(a.appointment) like ? or lower(a.user_id) like ? or TO_CHAR(a.created_on,'DD-MM-YYYY') like ? )";
		}
		if(!status.equals("")) {
			ic_no = ic_no.toLowerCase();
			if(SearchValue.contains("where")) {
				if(status.equals("0"))
					SearchValue +=" and user_id is null ";
				else if(status.equals("1"))
					SearchValue +=" and user_id is not null ";
			}else {
				if(status.equals("0"))
					SearchValue +=" where user_id is null ";
				else if(status.equals("1"))
					SearchValue +=" where user_id is not null ";
			}
		}
		if(!ic_no.equals("")) {
			ic_no = ic_no.toLowerCase();
			if(SearchValue.contains("where")) {
				SearchValue +=" and a.ic_no = ? ";
			}else {
				SearchValue +=" where a.ic_no = ? ";
			}
		}

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String ic_no) {
		int flag = 0;
		try {
			if(!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				
			}
			if(!ic_no.equals("")) {
				flag += 1;
				stmt.setString(flag, ic_no);
			}
		}catch (Exception e) {}
		return stmt;
	}

}
