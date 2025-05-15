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

public class Command_Wise_STR_DAOImpl implements Command_Wise_STR_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}	
		 
		 public List<Map<String, Object>> getCommand_Wise_STR_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
				 HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			if(pageLength.equals("-1")){
	 			pageLength = "ALL";
			}
			String SearchValue = GenerateQueryWhereClause_SQL(Search);

	 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				
				q = "select\r\n" + 
						"	app.level_c,app.Male1,app.Female1,app.Male2,app.Female2,app.Total,app.auth \r\n" + 
						"	from (with held as (select v.cmd_name as level_c, count(*) filter (where p.gender = '6'and v.ct_part_i_ii ='CTPartI') as Male1  ,\r\n" + 
						"count(*) filter (where p.gender = '7'and v.ct_part_i_ii ='CTPartI') as Female1,\r\n" + 
						"count(*) filter (where p.gender = '6'and v.ct_part_i_ii ='CTPartII') as Male2,\r\n" + 
						"count(*) filter (where p.gender = '7'and v.ct_part_i_ii ='CTPartII') as Female2,\r\n" + 
						"count(p.*) filter (where p.type_of_comm_granted <> '20') as Total\r\n" + 
						"from tb_psg_trans_proposed_comm_letter p\r\n" + 
						"left join orbat_all_details_view  v on v.sus_no = p.unit_sus_no and   p.status in ('1','5') and  p.type_of_comm_granted <> '20'\r\n" + 
						"WHERE v.status_sus_no='Active'\r\n" + 
						"and p.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n" + 
						"and p.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))	\r\n" + 
						"group by level_c),\r\n" + 
						"auth as (select b.cmd_name as level_c, sum(a.base_auth + a.mod_auth + a.foot_auth)as auth\r\n" + 
						"from sus_pers_stdauth a \r\n" + 
						"left join orbat_all_details_view b ON b.sus_no = a.sus_no and b.status_sus_no = 'Active'\r\n" + 
						"left join tb_miso_orbat_arm_code e on e.arm_code = a.arm\r\n" + 
						"where b.status_sus_no = 'Active'\r\n" + 
						"and	a.rank_cat = '0'\r\n" + 
						"group by level_c)\r\n" + 
						"select held.*,auth.auth from held \r\n" + 
						"join auth on held.level_c =auth.level_c) app " + 
						"  " +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
				
				PreparedStatement stmt = conn.prepareStatement(q);
		
				stmt = setQueryWhereClause_SQL(stmt,Search);
				System.err.println("query command wise\n "+stmt.toString());
				
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
	 			SearchValue +="lower(app.level_c) like ? or cast(app.Male1 as character varying) = ? or cast(app.Female1 as character varying) = ? or "
	 					+ "cast(app.Male2 as character varying) = ?  or cast(app.Female2 as character varying) = ? or "
	 					+ "cast(app.Total as character varying) = ? "
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
	  
	  public long Command_Wise_STR_TotalCountDtl(String Search) {
			String SearchValue = GenerateQueryWhereClause_SQL(Search);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q ="\r\n" + 
						"select count(app1.*) from (select\r\n" + 
						"	app.level_c,app.Male1,app.Female1,app.Male2,app.Female2,app.Total,app.auth \r\n" + 
						"	from (with held as (select v.cmd_name as level_c, count(*) filter (where p.gender = '6'and v.ct_part_i_ii ='CTPartI') as Male1  ,\r\n" + 
						"count(*) filter (where p.gender = '7'and v.ct_part_i_ii ='CTPartI') as Female1,\r\n" + 
						"count(*) filter (where p.gender = '6'and v.ct_part_i_ii ='CTPartII') as Male2,\r\n" + 
						"count(*) filter (where p.gender = '7'and v.ct_part_i_ii ='CTPartII') as Female2,\r\n" + 
						"count(p.*) filter (where p.type_of_comm_granted <> '20') as Total\r\n" + 
						"from tb_psg_trans_proposed_comm_letter p\r\n" + 
						"left join orbat_all_details_view  v on v.sus_no = p.unit_sus_no and   p.status in ('1','5') and  p.type_of_comm_granted <> '20'\r\n" + 
						"WHERE v.status_sus_no='Active'\r\n" + 
						"and p.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n" + 
						"and p.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))	\r\n" + 
						"group by level_c),\r\n" + 
						"auth as (select b.cmd_name as level_c, sum(a.base_auth + a.mod_auth + a.foot_auth)as auth\r\n" + 
						"from sus_pers_stdauth a \r\n" + 
						"left join orbat_all_details_view b ON b.sus_no = a.sus_no and b.status_sus_no = 'Active'\r\n" + 
						"left join tb_miso_orbat_arm_code e on e.arm_code = a.arm\r\n" + 
						"where b.status_sus_no = 'Active'\r\n" + 
						"and	a.rank_cat = '0'\r\n" + 
						"group by level_c)\r\n" + 
						"select held.*,auth.auth from held \r\n" + 
						"join auth on held.level_c =auth.level_c) app  "  
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
	  
	  public ArrayList<ArrayList<String>> pdf_Command_Wise_STR_ReportDataList()
	  {
	  	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	  	Connection conn = null;
	  	String q="";
	  	String SearchValue="";
	  	try{	  
	  		conn = dataSource.getConnection();			 
	  		PreparedStatement stmt=null;
	  		
	  		
	  		q="select\r\n" + 
	  				"	app.level_c,app.Male1,app.Female1,app.Male2,app.Female2,app.Total,app.auth \r\n" + 
	  				"	from (with held as (select v.cmd_name as level_c, count(*) filter (where p.gender = '6'and v.ct_part_i_ii ='CTPartI') as Male1  ,\r\n" + 
	  				"count(*) filter (where p.gender = '7'and v.ct_part_i_ii ='CTPartI') as Female1,\r\n" + 
	  				"count(*) filter (where p.gender = '6'and v.ct_part_i_ii ='CTPartII') as Male2,\r\n" + 
	  				"count(*) filter (where p.gender = '7'and v.ct_part_i_ii ='CTPartII') as Female2,\r\n" + 
	  				"count(p.*) filter (where p.type_of_comm_granted <> '20') as Total\r\n" + 
	  				"from tb_psg_trans_proposed_comm_letter p\r\n" + 
	  				"left join orbat_all_details_view  v on v.sus_no = p.unit_sus_no and   p.status in ('1','5') and  p.type_of_comm_granted <> '20'\r\n" + 
	  				"WHERE v.status_sus_no='Active'\r\n" + 
	  				"and p.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n" + 
	  				"and p.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))	\r\n" + 
	  				"group by level_c),\r\n" + 
	  				"auth as (select b.cmd_name as level_c, sum(a.base_auth + a.mod_auth + a.foot_auth)as auth\r\n" + 
	  				"from sus_pers_stdauth a \r\n" + 
	  				"left join orbat_all_details_view b ON b.sus_no = a.sus_no and b.status_sus_no = 'Active'\r\n" + 
	  				"left join tb_miso_orbat_arm_code e on e.arm_code = a.arm\r\n" + 
	  				"where b.status_sus_no = 'Active'\r\n" + 
	  				"and	a.rank_cat = '0'\r\n" + 
	  				"group by level_c)\r\n" + 
	  				"select held.*,auth.auth from held \r\n" + 
	  				"join auth on held.level_c =auth.level_c) app  " 
	  				 + SearchValue ;
	  			stmt=conn.prepareStatement(q);
	  			
	  			ResultSet rs = stmt.executeQuery();   
	  			int i =1;  
	  			while (rs.next()) {
	  				
	  				ArrayList<String> list = new ArrayList<String>();
	  				list.add(String.valueOf(i++)); //0
	  				list.add(rs.getString("level_c"));//1
	  				list.add(rs.getString("auth"));//2
	  				list.add(rs.getString("Male1"));//3
	  				list.add(rs.getString("Female1"));//4
	  				list.add(rs.getString("Male2"));//5
	  				list.add(rs.getString("Female2"));//6
	  				list.add(rs.getString("Total"));//7
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
