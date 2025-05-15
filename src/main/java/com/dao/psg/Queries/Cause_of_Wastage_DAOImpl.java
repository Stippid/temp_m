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

public class Cause_of_Wastage_DAOImpl implements Cause_of_wastage_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	  public ArrayList<ArrayList<String>> getCause_of_wastage()
	  {
		  
	  	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	  	Connection conn = null;
	  	String q="";
	  	
	  	try{	  
	  		conn = dataSource.getConnection();			 
	  		PreparedStatement stmt=null;
	  			  		
	  		q = "  select  DISTINCT coalesce(d.causes_name,'TOTAL') as Cause_of_Wastage, count (n.comm_id ) as no_of_offrs\r\n" + 
	  				"from tb_psg_non_effective n \r\n" + 
	  				"inner join tb_psg_trans_proposed_comm_letter p on p.id=n.comm_id \r\n" + 
	  				"inner join tb_psg_mstr_cause_of_non_effective d on d.id::text=n.cause_of_non_effective and type_Of_officer ='1' \r\n" + 
	  				" where n.status = 1 and p.status='4'\r\n" + 
	  				"AND n.date_of_non_effective between date_trunc('year', now()) and now()::date \r\n" + 
	  				" group  by d.causes_name\r\n" + 
	  				"  union all\r\n" + 
	  				" select DISTINCT  coalesce(d.causes_name,'TOTAL') as Cause_of_Wastage , count (n.comm_id ) as no_of_offrs\r\n" + 
	  				" from tb_psg_non_effective n \r\n" + 
	  				"   inner join tb_psg_trans_proposed_comm_letter p on p.id=n.comm_id \r\n" + 
	  				"   inner join tb_psg_mstr_cause_of_non_effective d on d.id::text=n.cause_of_non_effective and type_Of_officer ='2' \r\n" + 
	  				"   where n.status = 1 and p.status='4' AND n.date_of_non_effective between date_trunc('year', now()) and now()::date \r\n" + 
	  				"   group  by d.causes_name\r\n" + 
	  				"   order by 1 	";
	  		
	  			stmt=conn.prepareStatement(q);
  			
	  			ResultSet rs = stmt.executeQuery();   
	  			
	  			
	  			int i =1;  
	  			while (rs.next()) {
	  				
	  				ArrayList<String> list = new ArrayList<String>();
	  				list.add(rs.getString("Cause_of_Wastage"));//1
	  				list.add(rs.getString("no_of_offrs"));//0
	  				
	  				/*list.add(rs.getString("Premature_Retirement"));//2
	  				list.add(rs.getString("Relinquished_Commission_Release_SSC"));//3
	  				list.add(rs.getString("Retired"));//4
	  				list.add(rs.getString("total"));//5
*/	  				alist.add(list);
	  					  				
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
	  public List<Map<String, Object>> getwestegeReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
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
				
				q = "  select  DISTINCT coalesce(d.causes_name,'TOTAL') as cause_of_wastage, count (n.comm_id ) as no_of_offrs\r\n" + 
		  				"from tb_psg_non_effective n \r\n" + 
		  				"inner join tb_psg_trans_proposed_comm_letter p on p.id=n.comm_id \r\n" + 
		  				"inner join tb_psg_mstr_cause_of_non_effective d on d.id::text=n.cause_of_non_effective and type_Of_officer ='1' \r\n" + 
		  				" where n.status = 1 and p.status='4'\r\n" + 
		  				"AND n.date_of_non_effective between date_trunc('year', now()) and now()::date \r\n" + 
		  				" group  by d.causes_name\r\n" + 
		  				"  union all\r\n" + 
		  				" select DISTINCT  coalesce(d.causes_name,'TOTAL') as Cause_of_Wastage , count (n.comm_id ) as no_of_offrs\r\n" + 
		  				" from tb_psg_non_effective n \r\n" + 
		  				"   inner join tb_psg_trans_proposed_comm_letter p on p.id=n.comm_id \r\n" + 
		  				"   inner join tb_psg_mstr_cause_of_non_effective d on d.id::text=n.cause_of_non_effective and type_Of_officer ='2' \r\n" + 
		  				"   where n.status = 1 and p.status='4' AND n.date_of_non_effective between date_trunc('year', now()) and now()::date \r\n " + 
						"  " +SearchValue +" GROUP BY 1" + " ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
				
				PreparedStatement stmt = conn.prepareStatement(q);
		
				stmt = setQueryWhereClause_SQL(stmt,Search);
				System.err.println("query "+stmt.toString());
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
	 			SearchValue +="Cause_of_Wastage = ? or no_of_offrs = ?  "
	 					+")";
	 		}
	  		
	  			    	
	   return SearchValue;
	 }


	  public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search) {
	 		int flag = 0;
	 		try {
	    		if(!Search.equals("")) {
	 				flag += 1;
	 				stmt.setString(flag, Search);
	 				flag += 1;
	 				stmt.setString(flag, Search);
	 				flag += 1;
	 				

	 				
	    		}	
						
	 			
	 		}catch (Exception e) {}
	 		return stmt;
	 	}
	  
	  public long getwestegeReportDataListcountDtl(String Search) {
			String SearchValue = GenerateQueryWhereClause_SQL(Search);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q = "  select count (app1.*) from (select  DISTINCT coalesce(d.causes_name,'TOTAL') as cause_of_wastage, count (n.comm_id ) as no_of_offrs\r\n" + 
		  				"from tb_psg_non_effective n \r\n" + 
		  				"inner join tb_psg_trans_proposed_comm_letter p on p.id=n.comm_id \r\n" + 
		  				"inner join tb_psg_mstr_cause_of_non_effective d on d.id::text=n.cause_of_non_effective and type_Of_officer ='1' \r\n" + 
		  				" where n.status = 1 and p.status='4'\r\n" + 
		  				"AND n.date_of_non_effective between date_trunc('year', now()) and now()::date \r\n" + 
		  				" group  by d.causes_name\r\n" + 
		  				"  union all\r\n" + 
		  				" select DISTINCT  coalesce(d.causes_name,'TOTAL') as Cause_of_Wastage , count (n.comm_id ) as no_of_offrs\r\n" + 
		  				" from tb_psg_non_effective n \r\n" + 
		  				"   inner join tb_psg_trans_proposed_comm_letter p on p.id=n.comm_id \r\n" + 
		  				"   inner join tb_psg_mstr_cause_of_non_effective d on d.id::text=n.cause_of_non_effective and type_Of_officer ='2' \r\n" + 
		  				"   where n.status = 1 and p.status='4' AND n.date_of_non_effective between date_trunc('year', now()) and now()::date \r\n " + 
						"  " +SearchValue +" GROUP BY 1 ) app1 ";
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
	  
	
}
