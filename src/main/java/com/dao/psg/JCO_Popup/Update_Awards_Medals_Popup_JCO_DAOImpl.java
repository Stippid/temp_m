package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Update_Awards_Medals_Popup_JCO_DAOImpl implements Update_Awards_Medals_Popup_JCO_DAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> Update_Awards_Medals_JCO(int jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="select distinct am.id,td.award_cat as category_8 ,mm.medal_name,am.unit,cm.cmd_name,co.coprs_name,di.div_name,bd.bde_name,\r\n" + 
							"					ltrim(TO_CHAR(am.date_of_award  ,'DD-MON-YYYY'),'0') AS date_of_award,\r\n" + 
							"					am.created_by,\r\n" + 
							"	        		ltrim(TO_CHAR(am.created_on  ,'DD-MON-YYYY'),'0') AS created_date,\r\n" + 
							"					am.authority,ltrim(TO_CHAR(am.date_of_authority  ,'DD-MON-YYYY'),'0') AS date_of_authority,\r\n" + 
							"					am.modify_by,\r\n" + 
							"	        		ltrim(TO_CHAR(am.modify_on  ,'DD-MON-YYYY'),'0') AS modify_on\r\n" + 
							"					,am.status from tb_psg_census_jco_or_p p inner join \r\n" + 
							"					tb_psg_census_awardsnmedal_jco am on p.id = am.jco_id and p.status in ('1','5') \r\n" + 
							"					inner join  orbat_view_cmd cm on cm.sus_no=am.command  \r\n" + 
							"					inner join orbat_view_corps co on co.sus_no=am.corps_area\r\n" + 
							"					inner join orbat_view_div di on di.sus_no=am.div_subarea\r\n" + 
							"					inner join orbat_view_bde bd on bd.sus_no=am.bde\r\n" + 
							"					inner join tb_psg_mstr_award_category td on td.id::text =am.category_8\r\n" + 
							"					inner join tb_psg_mstr_medal mm on mm.id=cast(am.select_desc as integer)\r\n" + 
							"					 where am.jco_id=? and am.status in (1,2) order by am.id desc";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,jco_id);
				
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("category_8"));//0
					list.add(rs.getString("medal_name"));//1
					list.add(rs.getString("date_of_award"));//2
					list.add(rs.getString("unit"));//3
					list.add(rs.getString("bde_name"));//4
					list.add(rs.getString("div_name"));//5
					list.add(rs.getString("coprs_name"));//6
					list.add(rs.getString("cmd_name"));//7
					list.add(rs.getString("authority"));//8
					list.add(rs.getString("date_of_authority"));//9
					list.add(rs.getString("created_by"));//10
					list.add(rs.getString("created_date"));//11
					list.add(rs.getString("modify_on"));//12
					list.add(rs.getString("modify_by"));//13
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
