package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Date_Of_Seniority_JCO_DAOImpl implements Date_Of_Seniority_JCO_DAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> Date_Of_Seniority_JCO(int date_of_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{  	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
			    q="SELECT distinct ne.id,ne.reason_for_change,ne.se_authority,ltrim(TO_CHAR(ne.se_date_authority,'DD-MON-YYYY'),'0') as date_of_authority,ltrim(TO_CHAR(ne.date_of_seniority,'DD-MON-YYYY'),'0') as date_of_seniority, ne.modified_by,ltrim(TO_CHAR(ne.modified_date,'DD-MON-YYYY'),'0') as modified_date,ne.created_by,ltrim(TO_CHAR(ne.created_date,'DD-MON-YYYY'),'0') as created_date \r\n" + 
			    		"FROM  tb_psg_census_jco_or_p p inner join \r\n" + 
			    		"tb_psg_census_change_in_date_of_seniority_jco ne on p.id = ne.jco_id and p.status in ('1','5') \r\n" + 
			    		"where  jco_id=? and ne.status in (1,2) order by ne.id desc \r\n" + 
			    		"";
			   
			    
			stmt=conn.prepareStatement(q);
			stmt.setInt(1,date_of_jco_id);
			ResultSet rs = stmt.executeQuery();      
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("se_authority"));//0
				list.add(rs.getString("date_of_authority"));//1
				list.add(rs.getString("date_of_seniority"));//2
				list.add(rs.getString("reason_for_change"));//3
				list.add(rs.getString("created_by"));//4
				list.add(rs.getString("created_date"));//5
				list.add(rs.getString("modified_by"));//6
				list.add(rs.getString("modified_date"));//7
				
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
