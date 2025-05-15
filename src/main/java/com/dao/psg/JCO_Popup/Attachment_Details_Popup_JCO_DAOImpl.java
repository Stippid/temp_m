package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Attachment_Details_Popup_JCO_DAOImpl implements Attachment_Details_Popup_JCO_DAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<ArrayList<String>> Attachment_Details_JCO(int attachment_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="select ad.att_authority,ltrim(TO_CHAR(ad.att_date_authority  ,'DD-MON-YYYY'),'0') as date_of_authority,\r\n" +  
							"ad.att_movement_number,ltrim(TO_CHAR(ad.att_date_of_move  ,'DD-MON-YYYY'),'0') as date_of_move,ad.att_sus_no,\r\n" +
							"ad.att_place,ltrim(TO_CHAR(ad.att_from  ,'DD-MON-YYYY'),'0') as from_date,ltrim(TO_CHAR(ad.att_to  ,'DD-MON-YYYY'),'0') as to_date,ut.unit_name,\r\n" + 
							"ad.att_duration,ad.att_reasons,ad.modified_by,ltrim(TO_CHAR(ad.modified_date,'DD-MON-YYYY'),'0') as modified_date,\r\n" + 
							"ad.created_by,ltrim(TO_CHAR(ad.created_date,'DD-MON-YYYY'),'0') as created_date\r\n" +
							"from  tb_psg_census_attachment_details_jco ad\r\n"+ 
							"left join tb_miso_orbat_unt_dtl ut on  ad.att_sus_no=ut.sus_no\r\n"+ 
							"where ad.jco_id=? and ad.status in (1,2) order by ad.id desc";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,attachment_jco_id);
				
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("att_authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("att_movement_number"));//3
					list.add(rs.getString("date_of_move"));//4
					list.add(rs.getString("att_sus_no"));//5
					list.add(rs.getString("unit_name"));//6
					list.add(rs.getString("att_place"));//7
					list.add(rs.getString("from_date"));//8
					list.add(rs.getString("to_date"));//9
					list.add(rs.getString("att_duration"));//10
					list.add(rs.getString("att_reasons"));//11
					list.add(rs.getString("created_by"));//12
					list.add(rs.getString("created_date"));//13
					list.add(rs.getString("modified_by"));//14
					list.add(rs.getString("modified_date"));//15
					
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
