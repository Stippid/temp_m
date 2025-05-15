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

public class JCO_Arms_ServiceWise_DAOImpl implements JCO_Arms_ServiceWise_DAO{
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	
	public List<Map<String, Object>> getArmServices_Wise_JCOReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
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
			q = "select app.arm_service,app.arm_desc,\r\n" + 
					"app.jan,app.feb,app.mar,app.apr,app.may,app.jun,app.jul,app.aug,app.sep,app.oct,app.nov,app.dec,app.total               \r\n" + 
					"from \r\n" + 
					"(select  \r\n" + 
					"	 c.arm_service,\r\n" + 
					"	 a.arm_desc,\r\n" + 
					" 	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '01') as jan,  \r\n" + 
					"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '02') as feb,  \r\n" + 
					" 	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '03') as mar,  \r\n" + 
					" 	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '04') as apr,  \r\n" + 
					"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '05') as may,  \r\n" + 
					"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '06') as jun,  \r\n" + 
					"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '07') as jul,  \r\n" + 
					"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '08')as aug,  \r\n" + 
					"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '09') as sep,  \r\n" + 
					"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '10') as oct,  \r\n" + 
					"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '11') as nov,  \r\n" + 
					"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '12') as dec,  \r\n" + 
					"	 count(op.dt_of_tos) as total\r\n" + 
					"from tb_psg_census_jco_or_p c \r\n" + 
					"left join tb_psg_posting_in_out_jco op on c.id=op.jco_id and c.status in ('1','5') and op.status=1 and op.tenure_date is null and c.unit_sus_no= op.to_sus_no\r\n" + 
					"inner join tb_miso_orbat_arm_code a on a.arm_code = c.arm_service \r\n" + 
					"group by  c.arm_service, a.arm_desc\r\n" + 
					") app " +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search);
			System.err.println("query held arm/ser \n " + stmt);
			
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
	  public long getArmServices_Wise_JCOTotalCountDtl(String Search) {
			String SearchValue = GenerateQueryWhereClause_SQL(Search);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q ="select count(app1.*) from (\r\n" + 
						" select app.arm_service,app.arm_desc, \r\n" + 
						"app.jan,app.feb,app.mar,app.apr,app.may,app.jun,app.jul,app.aug,app.sep,app.oct,app.nov,app.dec,app.total               \r\n" + 
						"from \r\n" + 
						"(select  \r\n" + 
						"	 c.arm_service,\r\n" + 
						"	 a.arm_desc,\r\n" + 
						" 	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '01') as jan,  \r\n" + 
						"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '02') as feb,  \r\n" + 
						" 	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '03') as mar,  \r\n" + 
						" 	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '04') as apr,  \r\n" + 
						"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '05') as may,  \r\n" + 
						"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '06') as jun,  \r\n" + 
						"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '07') as jul,  \r\n" + 
						"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '08')as aug,  \r\n" + 
						"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '09') as sep,  \r\n" + 
						"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '10') as oct,  \r\n" + 
						"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '11') as nov,  \r\n" + 
						"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '12') as dec,  \r\n" + 
						"	 count(op.dt_of_tos) as total\r\n" + 
						"from tb_psg_census_jco_or_p c \r\n" + 
						"left join tb_psg_posting_in_out_jco op on c.id=op.jco_id and c.status in ('1','5') and op.status=1 and op.tenure_date is null and c.unit_sus_no= op.to_sus_no\r\n" + 
						"inner join tb_miso_orbat_arm_code a on a.arm_code = c.arm_service \r\n" + 
					
						"group by  c.arm_service, a.arm_desc\r\n" + 
						") app " +SearchValue +") app1";
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
	  
	public String GenerateQueryWhereClause_SQL(String Search) {
 		String SearchValue ="";
  		if(!Search.equals("")) {
			Search = Search.toLowerCase();
  			SearchValue =" where ( ";
 			SearchValue += " lower(app.arm_desc) like ? or lower(app.parent_arm) like ?  or  "
 					+ " cast(app.jan as character varying) = ? or cast(app.feb as character varying) = ? or cast(app.mar as character varying) = ? or cast(app.apr as character varying) = ? or cast(app.may as character varying) = ? or "
 					+ " cast(app.jun as character varying) = ? or cast(app.jul as character varying) = ? or cast(app.aug as character varying) = ? or cast(app.sep as character varying) = ? or cast(app.oct as character varying) = ? or "
 					+ " cast(app.nov as character varying) = ? or cast(app.dec as character varying) = ? )";
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
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				
 				flag += 1;
 				stmt.setString(flag,Search);
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
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				
 				
    		}	
			
 		}catch (Exception e) {}
 		return stmt;
 	}
  

  

public ArrayList<ArrayList<String>> pdf_getArmServices_Wise_JCO_ReportDataList()
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
		q="select app.arm_service,app.arm_desc,\r\n" + 
				"app.jan,app.feb,app.mar,app.apr,app.may,app.jun,app.jul,app.aug,app.sep,app.oct,app.nov,app.dec,app.total               \r\n" + 
				"from \r\n" + 
				"(select  \r\n" + 
				"	 c.arm_service,\r\n" + 
				"	 a.arm_desc,\r\n" + 
				" 	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '01') as jan,  \r\n" + 
				"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '02') as feb,  \r\n" + 
				" 	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '03') as mar,  \r\n" + 
				" 	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '04') as apr,  \r\n" + 
				"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '05') as may,  \r\n" + 
				"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '06') as jun,  \r\n" + 
				"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '07') as jul,  \r\n" + 
				"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '08')as aug,  \r\n" + 
				"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '09') as sep,  \r\n" + 
				"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '10') as oct,  \r\n" + 
				"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '11') as nov,  \r\n" + 
				"	 count(op.dt_of_tos) filter (where split_part(op.dt_of_tos::TEXT,'-', 2) = '12') as dec,  \r\n" + 
				"	 count(op.dt_of_tos) as total\r\n" + 
				"from tb_psg_census_jco_or_p c \r\n" + 
				"left join tb_psg_posting_in_out_jco op on c.id=op.jco_id and c.status in ('1','5') and op.status=1 and op.tenure_date is null and c.unit_sus_no= op.to_sus_no\r\n" + 
				"inner join tb_miso_orbat_arm_code a on a.arm_code = c.arm_service \r\n" + 
				"group by  c.arm_service, a.arm_desc\r\n" + 
				") app " + SearchValue ;
			stmt=conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();   
			int i =1;  
			while (rs.next()) {
				
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("arm_service"));//1
				list.add(rs.getString("arm_desc"));//2			
				list.add(rs.getString("jan"));//3
				list.add(rs.getString("feb"));//4
				list.add(rs.getString("mar"));//5
				list.add(rs.getString("apr"));//6
				list.add(rs.getString("may"));//7				
				list.add(rs.getString("jun"));//8
				list.add(rs.getString("jul"));//9
				list.add(rs.getString("aug"));//10
				list.add(rs.getString("sep"));//11
				list.add(rs.getString("oct"));//12				
				list.add(rs.getString("nov"));//13
				list.add(rs.getString("dec"));//14							
				list.add(rs.getString("total"));//15
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
