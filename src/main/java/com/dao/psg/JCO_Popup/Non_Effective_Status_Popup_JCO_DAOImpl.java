package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Non_Effective_Status_Popup_JCO_DAOImpl implements Non_Effective_Status_Popup_JCO_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> Non_Effective_Status_JCO(int jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="SELECT ne.id, ne.non_ef_authority,ltrim(TO_CHAR(ne.date_of_authority_non_ef,'DD-MON-YYYY'),'0') as date_of_authority,td.causes_name as cause_of_non_effective, ltrim(TO_CHAR(ne.date_of_non_effective,'DD-MON-YYYY'),'0') as date_of_non_effective, ne.modified_by,ltrim(TO_CHAR(ne.modified_date,'DD-MON-YYYY'),'0') as modified_date,ne.created_by,ltrim(TO_CHAR(ne.created_date,'DD-MON-YYYY'),'0') as created_date \r\n" + 
							"FROM  tb_psg_census_jco_or_p p inner join \r\n" + 
							"tb_psg_non_effective_jco ne on p.id = ne.jco_id and p.status in ('1','5') \r\n" + 
							"			    		inner join tb_psg_mstr_cause_of_non_effective_jco td on ne.cause_of_non_effective=td.id::text \r\n" + 
							"			    		 where   jco_id=? and ne.status in (1,2) order by ne.id desc";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,jco_id);
				
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("non_ef_authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("cause_of_non_effective"));//3
					list.add(rs.getString("date_of_non_effective"));//4
					list.add(rs.getString("created_by"));//5
					list.add(rs.getString("created_date"));//6
					list.add(rs.getString("modified_by"));//7
					list.add(rs.getString("modified_date"));//8
					
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
