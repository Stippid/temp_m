package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Update_Visit_Foriegn_Details_History_DAOImpl implements Update_Visit_Foriegn_Details_History_DAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public ArrayList<ArrayList<String>> update_visit_foriegn_details(BigInteger comm_id,int census_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{  	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			  //26-01-1994
//			    q="select  pfc.id,\r\n" + 
//			    		"case when fc.name='Other' then pfc.other_country\r\n" + 
//			    		"else fc.name end as country,\r\n" + 
//			    		"case when td.label='OTHER' then pfc.other_purpose_visit\r\n" + 
//			    		"else  td.label end as purpose_visit,\r\n" + 
//			    		"pfc.period,ltrim(TO_CHAR(pfc.from_dt,'DD-MON-YYYY'),'0') as from_dt,"
//			    		+ "ltrim(TO_CHAR(pfc.to_dt,'DD-MON-YYYY'),'0') as to_dt,pfc.created_by,"
//			    		+ "ltrim(TO_CHAR(pfc.created_on,'DD-MON-YYYY'),'0') as created_on,"
//			    		+ "ltrim(TO_CHAR(pfc.modified_date,'DD-MON-YYYY'),'0') as modified_date,pfc.modified_by \r\n" + 
//			    		"from tb_psg_census_foreign_country pfc\r\n" + 
//			    		"inner join t_domain_value td on td.codevalue = cast(pfc.purpose_visit as char) and td.domainid='FOREIGN_COUNTRY'\r\n" + 
//			    		"inner join tb_psg_foreign_country fc on fc.id=pfc.country \r\n" +
//			    		"where cast(pfc.comm_id as character varying)=? and pfc.cen_id=? and pfc.status in (1,2) order by pfc.id";
			
			q="select  pfc.id,\r\n"
		    		+ "case when fc.name='Other' then pfc.other_country\r\n"
		    		+ "else fc.name end as country,\r\n"
		    		+ "case when td.visit_purpose='OTHER' then pfc.other_purpose_visit\r\n"
		    		+ "else  td.visit_purpose end as purpose_visit,\r\n"
		    		+ "pfc.period,ltrim(TO_CHAR(pfc.from_dt,'DD-MON-YYYY'),'0') as from_dt,ltrim(TO_CHAR(pfc.to_dt,'DD-MON-YYYY'),'0') as to_dt,pfc.created_by,ltrim(TO_CHAR(pfc.created_on,'DD-MON-YYYY'),'0') as created_on,ltrim(TO_CHAR(pfc.modified_date,'DD-MON-YYYY'),'0') as modified_date,pfc.modified_by \r\n"
		    		+ "from tb_psg_census_foreign_country pfc\r\n"
		    		+ "inner join tb_psg_mstr_purposeof_visit td on td.id = pfc.purpose_visit\r\n"
		    		+ "inner join tb_psg_foreign_country fc on fc.id=pfc.country " +
		    		"where cast(pfc.comm_id as character varying)=? and pfc.cen_id=? and pfc.status in (1,2) order by pfc.id";
			   
			stmt=conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			stmt.setInt(2,census_id);
			
			
			ResultSet rs = stmt.executeQuery();   
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("country"));//1
				list.add(rs.getString("from_dt"));//2purpose_visit
				list.add(rs.getString("to_dt"));//3period
				list.add(rs.getString("period"));//4
				list.add(rs.getString("purpose_visit"));//5
				list.add(rs.getString("created_by"));//6
				list.add(rs.getString("created_on"));//7
				list.add(rs.getString("modified_by"));//8
				list.add(rs.getString("modified_date"));//9modified_by
//				list.add(rs.getString("modified_date"));//10 
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
					} catch (SQLException e) {}
				}
			}
			
			return alist;
	}
}
