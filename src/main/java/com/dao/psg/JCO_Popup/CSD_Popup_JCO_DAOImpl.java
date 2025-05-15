package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class CSD_Popup_JCO_DAOImpl implements CSD_Popup_JCO_DAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> CSD_JCO(int jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="select cd.id,\r\n" + 
							"	        			t.label,\r\n" + 
							"	        			cd.name,\r\n" + 
							"	        			ltrim(TO_CHAR(cd.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" + 
							"	        			cd.type_of_card,\r\n" + 
							"	        			cd.card_no,\r\n" + 
							"	        			cd.created_by,\r\n" + 
							"	        			ltrim(TO_CHAR(cd.created_date  ,'DD-MON-YYYY'),'0') as created_date, \r\n" + 
							"	        			cd.modified_by,\r\n" + 
							"	        			ltrim(TO_CHAR(cd.modified_date  ,'DD-MON-YYYY'),'0') as modified_date \r\n" + 
							"	        			from tb_psg_census_jco_or_p p inner join \r\n" + 
							"						tb_psg_canteen_card_details1_jco cd on p.id = cd.jco_id and p.status in ('1','5') \r\n" + 
							"	        			inner join t_domain_value t on t.codevalue = cast(cd.relation as char) and t.domainid='CSD_CARD' \r\n" + 
							"	        			where cd.jco_id=? and cd.status in ('1','2') order by cd.id desc ";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,jco_id);
				
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("label"));//0
					list.add(rs.getString("name"));//1
					list.add(rs.getString("date_of_birth"));//2
					list.add(rs.getString("type_of_card"));//3
					list.add(rs.getString("card_no"));//4
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
