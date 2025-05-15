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

public class ArmServices_RankWise_DAOImpl implements ArmServices_RankWise_DAO{
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	
	public List<Map<String, Object>> getArmServices_RankWise_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,
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
			
			q = "with data as (select app.parent_arm,app.fm,app.cds,app.gen,app.ltgen,app.majgen,app.brig,app.col,app.colts,app.ltcol,app.maj,app.capt,app.lt,app.total,app.percentage\r\n" + 
					"from (\r\n" + 
					"	select hld.parent_arm,hld.fm,hld.cds,hld.gen,hld.ltgen,hld.majgen,hld.brig,hld.col,hld.colts,hld.ltcol,hld.maj,hld.capt,hld.lt,hld.total,\r\n" + 
					"	 case when sum(hld.total) = 0 then 0\r\n" + 
					"	 else\r\n" + 
					"	 CAST(ROUND(SUM(hld.total) * 100.0 / SUM(SUM(hld.total)) OVER () , 2) AS NUMERIC(12,2)) end as percentage\r\n" + 
					"	from (\r\n" + 
					"			select\r\n" + 
					"		CASE WHEN c.PARENT_ARM IN ('0700','4600') THEN '0800' ELSE c.PARENT_ARM  END as PARENT_ARM, \r\n" + 
					"		count(c.rank) filter (where r.description in ('FD MARSHAL','FD MARSHAL','CDS','GEN','LT GEN','MAJ GEN','BRIG','COL','COL [TS]','LT COL','MAJ','CAPT','LT')) as total,\r\n" + 
					"		count(*) filter (where r.description = 'FD MARSHAL') as fm,\r\n" + 
					"		count(*) filter (where r.description = 'CDS') as cds,\r\n" + 
					"		count(*) filter (where r.description = 'GEN') as gen,\r\n" + 
					"		count(*) filter (where r.description = 'LT GEN') as ltgen,\r\n" + 
					"		count(*) filter (where r.description = 'MAJ GEN') as majgen,\r\n" + 
					"		count(*) filter (where r.description = 'BRIG') as brig,\r\n" + 
					"		count(*) filter (where r.description = 'COL') as col,\r\n" + 
					"		count(*) filter (where r.description = 'COL [TS]') as colts,\r\n" + 
					"		count(*) filter (where r.description = 'LT COL') as ltcol,\r\n" + 
					"		count(*) filter (where r.description = 'MAJ') as maj,\r\n" + 
					"		count(*) filter (where r.description = 'CAPT') as capt,\r\n" + 
					"		count(*) filter (where r.description = 'LT') as lt\r\n" + 
					"		from tb_psg_trans_proposed_comm_letter c\r\n" + 
					"		left join cue_tb_psg_rank_app_master r on c.rank=r.id and c.status in ('1','5') and  c.type_of_comm_granted <> '20'\r\n" + 
					"		and upper(r.level_in_hierarchy) = 'RANK' where  c.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2'\r\n" + 
					"		and c.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))	) group by 1) hld \r\n" + 
					"group by hld.parent_arm,hld.fm,hld.cds,hld.gen,hld.ltgen,hld.majgen,hld.brig,hld.col,hld.colts,hld.ltcol,hld.maj,hld.capt,hld.lt,hld.total ) app  ORDER BY 1 asc  \r\n" + 
					"	 )\r\n" + 
					"	 \r\n" + 
					"select d.parent_arm,a.arm_desc,	d.fm,d.cds,d.gen,d.ltgen,d.majgen,d.brig,d.col,	d.colts,d.ltcol,d.maj,d.capt,d.lt,d.total,d.percentage\r\n" + 
					"from data d\r\n" + 
					"left join tb_miso_orbat_arm_code a on a.arm_code = d.parent_arm" +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt,Search);
			System.err.println("query rakwise" + stmt);
		
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

	public long getArmServices_RankWise_TotalCountDtl(String Search) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = " select count(app1.*) from\r\n" + 
					"	(with data as (select app.parent_arm,app.fm,app.cds,app.gen,app.ltgen,app.majgen,app.brig,app.col,app.colts,app.ltcol,app.maj,app.capt,app.lt,app.total,app.percentage\r\n" + 
					"from (\r\n" + 
					"	select hld.parent_arm,hld.fm,hld.cds,hld.gen,hld.ltgen,hld.majgen,hld.brig,hld.col,hld.colts,hld.ltcol,hld.maj,hld.capt,hld.lt,hld.total,\r\n" + 
					"	 case when sum(hld.total) = 0 then 0\r\n" + 
					"	 else\r\n" + 
					"	 CAST(ROUND(SUM(hld.total) * 100.0 / SUM(SUM(hld.total)) OVER () , 2) AS NUMERIC(12,2)) end as percentage\r\n" + 
					"	from (\r\n" + 
					"			select\r\n" + 
					"		CASE WHEN c.PARENT_ARM IN ('0700','4600') THEN '0800' ELSE c.PARENT_ARM  END as PARENT_ARM, \r\n" + 
					"		count(c.rank) filter (where r.description in ('FD MARSHAL','FD MARSHAL','CDS','GEN','LT GEN','MAJ GEN','BRIG','COL','COL [TS]','LT COL','MAJ','CAPT','LT')) as total,\r\n" + 
					"		count(*) filter (where r.description = 'FD MARSHAL') as fm,\r\n" + 
					"		count(*) filter (where r.description = 'CDS') as cds,\r\n" + 
					"		count(*) filter (where r.description = 'GEN') as gen,\r\n" + 
					"		count(*) filter (where r.description = 'LT GEN') as ltgen,\r\n" + 
					"		count(*) filter (where r.description = 'MAJ GEN') as majgen,\r\n" + 
					"		count(*) filter (where r.description = 'BRIG') as brig,\r\n" + 
					"		count(*) filter (where r.description = 'COL') as col,\r\n" + 
					"		count(*) filter (where r.description = 'COL [TS]') as colts,\r\n" + 
					"		count(*) filter (where r.description = 'LT COL') as ltcol,\r\n" + 
					"		count(*) filter (where r.description = 'MAJ') as maj,\r\n" + 
					"		count(*) filter (where r.description = 'CAPT') as capt,\r\n" + 
					"		count(*) filter (where r.description = 'LT') as lt\r\n" + 
					"		from tb_psg_trans_proposed_comm_letter c\r\n" + 
					"		left join cue_tb_psg_rank_app_master r on c.rank=r.id and c.status in ('1','5') and  c.type_of_comm_granted <> '20'\r\n" + 
					"		and upper(r.level_in_hierarchy) = 'RANK' where  c.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2'\r\n" + 
					"		and c.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))	) group by 1) hld \r\n" + 
					"group by hld.parent_arm,hld.fm,hld.cds,hld.gen,hld.ltgen,hld.majgen,hld.brig,hld.col,hld.colts,hld.ltcol,hld.maj,hld.capt,hld.lt,hld.total ) app  ORDER BY 1 asc  \r\n" + 
					"	 )\r\n" + 
					"	 \r\n" + 
					"select d.parent_arm,a.arm_desc,	d.fm,d.cds,d.gen,d.ltgen,d.majgen,d.brig,d.col,	d.colts,d.ltcol,d.maj,d.capt,d.lt,d.total,d.percentage\r\n" + 
					"from data d\r\n" + 
					"left join tb_miso_orbat_arm_code a on a.arm_code = d.parent_arm " +SearchValue +") app1";
			
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
 			SearchValue +="app.parent_arm = ? or lower(app.arm_desc) like ?  or  cast(app.fm as character varying) = ? or  "
 					+ "	cast(app.cds as character varying) = ? or cast(app.gen as character varying) = ? or cast(app.ltgen as character varying) = ? or "
 					+ "	cast(app.majgen as character varying) = ? or cast(app.brig as character varying) = ? or cast(app.col as character varying) = ? or "
 					+ " cast(app.colts as character varying) = ? or cast(app.ltcol as character varying) = ? or cast(app.maj as character varying) = ? or "
 					+ "	cast(app.capt as character varying) = ? or cast(app.lt as character varying) = ? or "
 					+ " cast(app.total as character varying) = ? or cast(app.percentage as character varying) = ? )";
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
  
  
  

public ArrayList<ArrayList<String>> pdf_getArmServices_RankWise_ReportDataList()
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
		
		q = "with data as (select app.parent_arm,app.fm,app.cds,app.gen,app.ltgen,app.majgen,app.brig,app.col,app.colts,app.ltcol,app.maj,app.capt,app.lt,app.total,app.percentage\r\n" + 
				"from (\r\n" + 
				"	select hld.parent_arm,hld.fm,hld.cds,hld.gen,hld.ltgen,hld.majgen,hld.brig,hld.col,hld.colts,hld.ltcol,hld.maj,hld.capt,hld.lt,hld.total,\r\n" + 
				"	 case when sum(hld.total) = 0 then 0\r\n" + 
				"	 else\r\n" + 
				"	 CAST(ROUND(SUM(hld.total) * 100.0 / SUM(SUM(hld.total)) OVER () , 2) AS NUMERIC(12,2)) end as percentage\r\n" + 
				"	from (\r\n" + 
				"			select\r\n" + 
				"		CASE WHEN c.PARENT_ARM IN ('0700','4600') THEN '0800' ELSE c.PARENT_ARM  END as PARENT_ARM, \r\n" + 
				"		count(c.rank) filter (where r.description in ('FD MARSHAL','FD MARSHAL','CDS','GEN','LT GEN','MAJ GEN','BRIG','COL','COL [TS]','LT COL','MAJ','CAPT','LT')) as total,\r\n" + 
				"		count(*) filter (where r.description = 'FD MARSHAL') as fm,\r\n" + 
				"		count(*) filter (where r.description = 'CDS') as cds,\r\n" + 
				"		count(*) filter (where r.description = 'GEN') as gen,\r\n" + 
				"		count(*) filter (where r.description = 'LT GEN') as ltgen,\r\n" + 
				"		count(*) filter (where r.description = 'MAJ GEN') as majgen,\r\n" + 
				"		count(*) filter (where r.description = 'BRIG') as brig,\r\n" + 
				"		count(*) filter (where r.description = 'COL') as col,\r\n" + 
				"		count(*) filter (where r.description = 'COL [TS]') as colts,\r\n" + 
				"		count(*) filter (where r.description = 'LT COL') as ltcol,\r\n" + 
				"		count(*) filter (where r.description = 'MAJ') as maj,\r\n" + 
				"		count(*) filter (where r.description = 'CAPT') as capt,\r\n" + 
				"		count(*) filter (where r.description = 'LT') as lt\r\n" + 
				"		from tb_psg_trans_proposed_comm_letter c\r\n" + 
				"		left join cue_tb_psg_rank_app_master r on c.rank=r.id and c.status in ('1','5') and  c.type_of_comm_granted <> '20'\r\n" + 
				"		and upper(r.level_in_hierarchy) = 'RANK' where  c.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2'\r\n" + 
				"		and c.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))	) group by 1) hld \r\n" + 
				"group by hld.parent_arm,hld.fm,hld.cds,hld.gen,hld.ltgen,hld.majgen,hld.brig,hld.col,hld.colts,hld.ltcol,hld.maj,hld.capt,hld.lt,hld.total ) app  ORDER BY 1 asc  \r\n" + 
				"	 )\r\n" + 
				"	 \r\n" + 
				"select d.parent_arm,a.arm_desc,	d.fm,d.cds,d.gen,d.ltgen,d.majgen,d.brig,d.col,	d.colts,d.ltcol,d.maj,d.capt,d.lt,d.total,d.percentage\r\n" + 
				"from data d\r\n" + 
				"left join tb_miso_orbat_arm_code a on a.arm_code = d.parent_arm"  ;
			stmt=conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();   
			int i =1;  
			while (rs.next()) {
				
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				
				list.add(rs.getString("parent_arm"));//7
				list.add(rs.getString("arm_desc"));//8
				list.add(rs.getString("fm"));//9
				list.add(rs.getString("cds"));//10
				list.add(rs.getString("gen"));//11
				
				list.add(rs.getString("ltgen"));//12
				list.add(rs.getString("majgen"));//13
				list.add(rs.getString("brig"));//14
				list.add(rs.getString("col"));//15
				list.add(rs.getString("colts"));//16
				
				list.add(rs.getString("ltcol"));//17
				list.add(rs.getString("maj"));//18
				list.add(rs.getString("capt"));//19
				list.add(rs.getString("lt"));//20
				list.add(rs.getString("total"));//22
				list.add(rs.getString("percentage"));//21
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
