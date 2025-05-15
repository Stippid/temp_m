package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Posting_Popup_JCO_DAOImpl implements Posting_Popup_JCO_DAO{
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> posting_PopUp_JCO(int jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
				q="select \r\n" + 
						"ra.type_of_post ,\r\n" + 
						"r.created_by,\r\n" + 
						"TO_CHAR(r.created_date,'DD-MON-YYYY') as created_date ,r.modified_by,\r\n" + 
						"TO_CHAR(r.modified_date,'DD-MON-YYYY') as modified_date \r\n" + 
						"from tb_psg_census_jco_or_p p \r\n" + 
						"inner join tb_psg_change_type_of_posting_jco r on p.id = r.jco_id \r\n" + 
						"inner join tb_psg_mstr_type_of_post ra on ra.id = r.type_of_post\r\n" + 
						"\r\n" + 
						"and p.status in ('1','5') \r\n" + 
						"where r.jco_id =? and r.status in (1,2) order by p.id desc";
			
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,jco_id);
				
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					
					list.add(rs.getString("type_of_post"));//0
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
