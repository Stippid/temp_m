package com.dao.psg.JCO_Report;

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

public class JCO_Command_Wise_STR_DAOImpl implements JCO_Command_Wise_STR_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}	
		 
	 public List<Map<String, Object>> getCommand_Wise_STR_ReportJco_DataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,HttpSession session) 
				throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			if(pageLength.equals("-1")){
	 			pageLength = "ALL";
			}
			String SearchValue = GenerateQueryWhereClause_SQL(Search);

	 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				
				q = "select app.level_c,app.auth,app.Male1,app.Female1,app.Male2,app.Female2,app.Total from (select u.level_c ,sum(off_total_auth) as auth,\r\n" + 
						"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Male') and u.ct_part_i_ii ='CTPartI') as Male1,\r\n" + 
						"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Female') and u.ct_part_i_ii ='CTPartI') as Female1,\r\n" + 
						"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Male') and u.ct_part_i_ii ='CTPartII') as Male2,\r\n" + 
						"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Female') and u.ct_part_i_ii ='CTPartII') as Female2,\r\n" + 
						"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Male') or UPPER(g.gender_name) = UPPER('Female')) as Total\r\n" + 
						"from all_fmn_view fv  \r\n" + 
						"inner join tb_miso_orbat_unt_dtl u on SUBSTRING(u.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy='Command' and u.status_sus_no='Active' \r\n" + 
						"inner  join tb_psg_census_jco_or_p p on u.sus_no = p.unit_sus_no  \r\n" + 
						"inner join cue_psg_auth_held ah on u.sus_no = ah.unit_sus_no\r\n" + 
						"inner join tb_psg_mstr_gender g on g.id=p.gender \r\n" + 
						"group by u.level_c) app" + 
						"  " +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search);
				System.err.println("querry cmd wise \n " + stmt);
				
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
		
		public String GenerateQueryWhereClause_SQL(String Search) {
	 		String SearchValue ="";
	  		if(!Search.equals("")) {
				Search = Search.toLowerCase();
	  			SearchValue =" where ( ";
	 			SearchValue +="lower(app.level_c) like ? or cast(app.Male1 as character varying) = ? or cast(app.Female1 as character varying) = ? or cast(app.Male2 as character varying) = ?  or cast(app.Female2 as character varying) = ? or cast(app.Total as character varying) = ? "
	 					+")";
	 		}  		  	
	   return SearchValue;
	 }


	  public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search) {
	 		int flag = 0;
	 		try {
	    		if(!Search.equals("")) {
	 				flag += 1;
	 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
	 				flag += 1;
	 				stmt.setString(flag, Search);
	 				flag += 1;
	 				stmt.setString(flag, Search);
	 				flag += 1;
	 				stmt.setString(flag, Search);
	 				flag += 1;
	 				stmt.setString(flag, Search);
	 				flag += 1;
	 				stmt.setString(flag, Search);
	 				flag += 1;
	 				
	    		}		 			
	 		}catch (Exception e) {}
	 		return stmt;
	 	}
	  
	  public long Command_Wise_STR_Jco_TotalCountDtl(String Search) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String q = null;
		Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q ="select count(app1.*) from (select app.level_c,app.auth,app.Male1,app.Female1,app.Male2,app.Female2,app.Total from (select u.level_c ,sum(off_total_auth) as auth,\r\n" + 
						"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Male') and u.ct_part_i_ii ='CTPartI') as Male1,\r\n" + 
						"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Female') and u.ct_part_i_ii ='CTPartI') as Female1,\r\n" + 
						"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Male') and u.ct_part_i_ii ='CTPartII') as Male2,\r\n" + 
						"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Female') and u.ct_part_i_ii ='CTPartII') as Female2,\r\n" + 
						"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Male') or UPPER(g.gender_name) = UPPER('Female')) as Total\r\n" + 
						"from all_fmn_view fv  \r\n" + 
						"inner join tb_miso_orbat_unt_dtl u on SUBSTRING(u.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy='Command' and u.status_sus_no='Active' \r\n" + 
						"inner  join tb_psg_census_jco_or_p p on u.sus_no = p.unit_sus_no  \r\n" + 
						"inner join cue_psg_auth_held ah on u.sus_no = ah.unit_sus_no\r\n" + 
						"inner join tb_psg_mstr_gender g on g.id=p.gender \r\n" + 
						"group by u.level_c) app"  
						+SearchValue +") app1";
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search);
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
	  
	  public ArrayList<ArrayList<String>> pdf_Command_Wise_STR_Jco_ReportDataList()
	  {
	  	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	  	Connection conn = null;
	  	String q="";
	  	String SearchValue="";
	  	try{	  
	  		conn = dataSource.getConnection();			 
	  		PreparedStatement stmt=null;
	  		
	
	  		
	  		q="select app.level_c,app.auth,app.Male1,app.Female1,app.Male2,app.Female2,app.Total from (select u.level_c ,sum(off_total_auth) as auth,\r\n" + 
	  				"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Male') and u.ct_part_i_ii ='CTPartI') as Male1,\r\n" + 
	  				"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Female') and u.ct_part_i_ii ='CTPartI') as Female1,\r\n" + 
	  				"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Male') and u.ct_part_i_ii ='CTPartII') as Male2,\r\n" + 
	  				"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Female') and u.ct_part_i_ii ='CTPartII') as Female2,\r\n" + 
	  				"count(p.*) FILTER (where UPPER(g.gender_name) = UPPER('Male') or UPPER(g.gender_name) = UPPER('Female')) as Total\r\n" + 
	  				"from all_fmn_view fv  \r\n" + 
	  				"inner join tb_miso_orbat_unt_dtl u on SUBSTRING(u.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy='Command' and u.status_sus_no='Active' \r\n" + 
	  				"inner  join tb_psg_census_jco_or_p p on u.sus_no = p.unit_sus_no  \r\n" + 
	  				"inner join cue_psg_auth_held ah on u.sus_no = ah.unit_sus_no\r\n" + 
	  				"inner join tb_psg_mstr_gender g on g.id=p.gender \r\n" + 
	  				"group by u.level_c) app" 
	  				 + SearchValue ;
	  			stmt=conn.prepareStatement(q);
	  			
	  			ResultSet rs = stmt.executeQuery();   
	  			int i =1;  
	  			while (rs.next()) {
	  				
	  				ArrayList<String> list = new ArrayList<String>();
	  				list.add(String.valueOf(i++)); //0
	  				list.add(rs.getString("level_c"));//1
	  				list.add(rs.getString("auth"));//1
	  				list.add(rs.getString("Male1"));//2
	  				list.add(rs.getString("Female1"));//3
	  				list.add(rs.getString("Male2"));//4
	  				list.add(rs.getString("Female2"));//5
	  				list.add(rs.getString("Total"));//6
	  				alist.add(list);
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
	  	return alist;
	  }
}
