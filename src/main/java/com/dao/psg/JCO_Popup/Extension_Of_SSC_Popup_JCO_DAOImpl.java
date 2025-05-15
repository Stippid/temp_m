package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Extension_Of_SSC_Popup_JCO_DAOImpl implements Extension_Of_SSC_Popup_JCO_DAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> Extension_Of_SSC_JCO(int extension_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="select r.authority,\r\n" + 
							"							TO_CHAR(r.date_of_authority  ,'DD-MON-YYYY') as date_of_authority,\r\n" + 
							"							TO_CHAR(r.date_of_rank ,'DD-MON-YYYY')as date_of_rank,\r\n" + 
							"							ra.rank as rank,\r\n" + 
							"							r.created_by,\r\n" + 
							"							TO_CHAR(r.created_date,'DD-MON-YYYY') as created_date ,r.modified_by,\r\n" + 
							"							TO_CHAR(r.modified_date,'DD-MON-YYYY') as modified_date \r\n" + 
							"							from tb_psg_change_of_rank_jco r\r\n" + 
							"							 inner join tb_psg_mstr_rank_jco ra on ra.id = r.rank\r\n" + 
							"							 where r.status in ('1','2') and\r\n" + 
							"							 r.jco_id=? order by r.id desc";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,extension_jco_id);
				
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("authority"));//0
					list.add(rs.getString("date_of_authority"));//1
					list.add(rs.getString("created_by"));//2
					list.add(rs.getString("created_date"));//3
					list.add(rs.getString("modified_by"));//4
					list.add(rs.getString("modified_date"));//5
					list.add(rs.getString("fromdt"));//6
					list.add(rs.getString("todt"));//7
					
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
