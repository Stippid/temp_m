package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Update_Visit_To_Foreign_Country_Popup_JCO_DAOImpl implements Update_Visit_To_Foreign_Country_Popup_JCO_DAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> Update_Visit_To_Foreign_Country_JCO(int jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="select  distinct pfc.id,\r\n" + 
							"			    		case when fc.name='Other' then pfc.other_country\r\n" + 
							"			    		else fc.name end as country,\r\n" + 
							"			    		case when td.label='OTHER' then pfc.other_purpose_visit\r\n" + 
							"			    		else  td.label end as purpose_visit,\r\n" + 
							"			    		pfc.period,ltrim(TO_CHAR(pfc.from_dt,'DD-MON-YYYY'),'0') as from_dt,\r\n" + 
							"			    		ltrim(TO_CHAR(pfc.to_dt,'DD-MON-YYYY'),'0') as to_dt,pfc.created_by,\r\n" + 
							"			    		ltrim(TO_CHAR(pfc.created_on,'DD-MON-YYYY'),'0') as created_on,\r\n" + 
							"			    		ltrim(TO_CHAR(pfc.modified_date,'DD-MON-YYYY'),'0') as modified_date,pfc.modified_by\r\n" + 
							"			    		from  tb_psg_census_jco_or_p p inner join \r\n" + 
							"						tb_psg_census_foreign_country_jco pfc on p.id = pfc.jco_id and p.status in ('1','5') \r\n" + 
							"			    		inner join t_domain_value td on td.codevalue = cast(pfc.purpose_visit as char) and td.domainid='FOREIGN_COUNTRY'\r\n" + 
							"			    		inner join tb_psg_foreign_country fc on fc.id=pfc.country \r\n" + 
							"			    		where pfc.jco_id=? and pfc.status in (1,2) order by pfc.id desc";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,jco_id);
				
			
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
