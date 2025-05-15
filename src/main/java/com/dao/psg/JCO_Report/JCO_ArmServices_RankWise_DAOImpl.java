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

public class JCO_ArmServices_RankWise_DAOImpl implements JCO_ArmServices_RankWise_DAO{
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	
	public List<Map<String, Object>> getArmServices_RankWise_Report_JCODataListDetails(int startPage,String pageLength,String Search,String orderColunm,
			String orderType,HttpSession session) 
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, 
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = "select app.arm_service,app.arm_desc,app.warrant_officer,app.sub_maj,app.sub,\r\n" + 
					" app.nb_sub,app.hav,app.nk,app.sep,app.rects,app.total,\r\n" + 
					" case when sum(app.total) = 0 then 0\r\n" + 
					" else\r\n" + 
					" CAST(ROUND(SUM(app.total) * 100.0 / SUM(SUM(app.total)) OVER () , 2) AS NUMERIC(12,2)) end as percentage\r\n" + 
					"from\r\n" + 
					"(select hld.arm_service,hld.arm_desc,hld.warrant_officer,hld.sub_maj,hld.sub,\r\n" + 
					" hld.nb_sub,hld.hav,hld.nk,hld.sep,hld.rects,hld.total\r\n" + 
					"	from (\r\n" + 
					"		select c.arm_service,a.arm_desc,\r\n" + 
					"	count(r.rank) filter \r\n" + 
					"		(where r.rank in ('WARRANT OFFICER','SUB MAJ / EQUIVALENT','SUB / EQUIVALENT',\r\n" + 
					"						  'NB SUB / EQUIVALENT','HAV / EQUIVALENT','NK / EQUIVALENT',\r\n" + 
					"						  'SEP / EQUIVALENT','RECTS')) as total,\r\n" + 
					"	count(*) filter (where r.rank = 'WARRANT OFFICER') as warrant_officer,\r\n" + 
					"	count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sub_maj,\r\n" + 
					"	count(*) filter (where r.rank = 'SUB / EQUIVALENT') as sub,\r\n" + 
					"	count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub,\r\n" + 
					"	count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav,\r\n" + 
					"	count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,\r\n" + 
					"	count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep,\r\n" + 
					"	count(*) filter (where r.rank = 'RECTS') as rects\r\n" + 
					"	from tb_psg_census_jco_or_p c\r\n" + 
					"	inner join tb_miso_orbat_arm_code a on a.arm_code = c.arm_service\r\n" + 
					"	inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status in ('1','5') group by 1,2) \r\n" + 
					"	 hld ) app " +SearchValue +"  group by app.arm_service,app.arm_desc,app.warrant_officer,app.sub_maj,app.sub,\r\n" + 
							" app.nb_sub,app.hav,app.nk,app.sep,app.rects,app.total ORDER BY " 
					+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search);
			System.err.println("query held arm/ser and rank \n " + stmt);
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

	public long getArmServices_RankWise_JCOTotalCountDtl(String Search) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = "select count(app1.*) from\r\n" + 
					"(select app.arm_service,app.arm_desc,app.warrant_officer,app.sub_maj,app.sub,\r\n" + 
					" app.nb_sub,app.hav,app.nk,app.sep,app.rects,app.total,\r\n" + 
					" case when sum(app.total) = 0 then 0\r\n" + 
					" else\r\n" + 
					" CAST(ROUND(SUM(app.total) * 100.0 / SUM(SUM(app.total)) OVER () , 2) AS NUMERIC(12,2)) end as percentage\r\n" + 
					"from\r\n" + 
					"(select hld.arm_service,hld.arm_desc,hld.warrant_officer,hld.sub_maj,hld.sub,\r\n" + 
					" hld.nb_sub,hld.hav,hld.nk,hld.sep,hld.rects,hld.total\r\n" + 
					"	from (\r\n" + 
					"		select c.arm_service,a.arm_desc,\r\n" + 
					"	count(r.rank) filter \r\n" + 
					"		(where r.rank in ('WARRANT OFFICER','SUB MAJ / EQUIVALENT','SUB / EQUIVALENT',\r\n" + 
					"						  'NB SUB / EQUIVALENT','HAV / EQUIVALENT','NK / EQUIVALENT',\r\n" + 
					"						  'SEP / EQUIVALENT','RECTS')) as total,\r\n" + 
					"	count(*) filter (where r.rank = 'WARRANT OFFICER') as warrant_officer,\r\n" + 
					"	count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sub_maj,\r\n" + 
					"	count(*) filter (where r.rank = 'SUB / EQUIVALENT') as sub,\r\n" + 
					"	count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub,\r\n" + 
					"	count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav,\r\n" + 
					"	count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,\r\n" + 
					"	count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep,\r\n" + 
					"	count(*) filter (where r.rank = 'RECTS') as rects\r\n" + 
					"	from tb_psg_census_jco_or_p c\r\n" + 
					"	inner join tb_miso_orbat_arm_code a on a.arm_code = c.arm_service\r\n" + 
					"	inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status in ('1','5') group by 1,2) \r\n" + 
					"	 hld ) app" +SearchValue +" group by app.arm_service,app.arm_desc,app.warrant_officer,app.sub_maj,app.sub,\r\n" + 
							" app.nb_sub,app.hav,app.nk,app.sep,app.rects,app.total ) app1";
			
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
 			SearchValue +="app.arm_service = ? or lower(app.arm_desc) like ?  or  cast(app.warrant_officer as character varying) = ? or  "
 					+ "	cast(app.sub_maj as character varying) = ? or cast(app.sub as character varying) = ? or cast(app.nb_sub as character varying) = ? or "
 					+ "	cast(app.hav as character varying) = ? or cast(app.nk as character varying) = ? or cast(app.sep as character varying) = ? or "
 					+ " cast(app.rects as character varying) = ? or  cast(app.total as character varying) = ? )";
 		}
  		
  	
    	
   return SearchValue;
 }


  public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search) {
 		int flag = 0;
 		try {
    		if(!Search.equals("")) {
 				flag += 1;
 				stmt.setString(flag,Search);
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
  
  
  

public ArrayList<ArrayList<String>> pdf_getArmServices_RankWise_JCO_ReportDataList()
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
		
		q="select app.arm_service,app.arm_desc,app.warrant_officer,app.sub_maj,app.sub,\r\n" + 
				" app.nb_sub,app.hav,app.nk,app.sep,app.rects,app.total,\r\n" + 
				" case when sum(app.total) = 0 then 0\r\n" + 
				" else\r\n" + 
				" CAST(ROUND(SUM(app.total) * 100.0 / SUM(SUM(app.total)) OVER () , 2) AS NUMERIC(12,2)) end as percentage\r\n" + 
				"from\r\n" + 
				"(select hld.arm_service,hld.arm_desc,hld.warrant_officer,hld.sub_maj,hld.sub,\r\n" + 
				" hld.nb_sub,hld.hav,hld.nk,hld.sep,hld.rects,hld.total\r\n" + 
				"	from (\r\n" + 
				"		select c.arm_service,a.arm_desc,\r\n" + 
				"	count(r.rank) filter \r\n" + 
				"		(where r.rank in ('WARRANT OFFICER','SUB MAJ / EQUIVALENT','SUB / EQUIVALENT',\r\n" + 
				"						  'NB SUB / EQUIVALENT','HAV / EQUIVALENT','NK / EQUIVALENT',\r\n" + 
				"						  'SEP / EQUIVALENT','RECTS')) as total,\r\n" + 
				"	count(*) filter (where r.rank = 'WARRANT OFFICER') as warrant_officer,\r\n" + 
				"	count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sub_maj,\r\n" + 
				"	count(*) filter (where r.rank = 'SUB / EQUIVALENT') as sub,\r\n" + 
				"	count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub,\r\n" + 
				"	count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav,\r\n" + 
				"	count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,\r\n" + 
				"	count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep,\r\n" + 
				"	count(*) filter (where r.rank = 'RECTS') as rects\r\n" + 
				"	from tb_psg_census_jco_or_p c\r\n" + 
				"	inner join tb_miso_orbat_arm_code a on a.arm_code = c.arm_service\r\n" + 
				"	inner join tb_psg_mstr_rank_jco r on c.rank=r.id and c.status in ('1','5') group by 1,2) \r\n" + 
				"	 hld ) app  group by app.arm_service,app.arm_desc,app.warrant_officer,app.sub_maj,app.sub,\r\n" + 
				" app.nb_sub,app.hav,app.nk,app.sep,app.rects,app.total" ;
		
			stmt=conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();   
			int i =1;  
			while (rs.next()) {
				
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				
				list.add(rs.getString("arm_service"));//1
				list.add(rs.getString("arm_desc"));//2
				list.add(rs.getString("warrant_officer"));//3
				list.add(rs.getString("sub_maj"));//4
				list.add(rs.getString("sub"));//5
				
				list.add(rs.getString("nb_sub"));//6
				list.add(rs.getString("hav"));//7
				list.add(rs.getString("nk"));//8
				list.add(rs.getString("sep"));//9
				list.add(rs.getString("rects"));//10
				
				list.add(rs.getString("total"));//11
				list.add(rs.getString("percentage"));//12
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
