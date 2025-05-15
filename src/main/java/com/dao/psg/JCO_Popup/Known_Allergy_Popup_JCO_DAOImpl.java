package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Known_Allergy_Popup_JCO_DAOImpl implements Known_Allergy_Popup_JCO_DAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> Known_Allergy_JCO(int jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="select ka.id,ka.medicine,ka.created_by,\r\n" + 
							"	        		ltrim(TO_CHAR(ka.created_date  ,'DD-MON-YYYY'),'0') as created_date,\r\n" + 
							"	        		ltrim(TO_CHAR(ka.modified_date  ,'DD-MON-YYYY'),'0') as modified_date,ka.modified_by\r\n" + 
							"					from tb_psg_census_jco_or_p p inner join \r\n" + 
							"					tb_psg_allergic_to_medicine_jco ka on p.id = ka.jco_id and p.status in ('1','5') \r\n" + 
							"	        		where ka.jco_id=? and ka.status in (1,2)  order by ka.id desc";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,jco_id);
				
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("medicine"));//0
					list.add(rs.getString("created_by"));//1
					list.add(rs.getString("created_date"));//2
					list.add(rs.getString("modified_by"));//3
					list.add(rs.getString("modified_date"));//4
					
					
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
