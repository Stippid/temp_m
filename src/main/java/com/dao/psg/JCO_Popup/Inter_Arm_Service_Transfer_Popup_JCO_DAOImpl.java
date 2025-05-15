package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Inter_Arm_Service_Transfer_Popup_JCO_DAOImpl implements Inter_Arm_Service_Transfer_Popup_JCO_DAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> Inter_Arm_Service_Transfer_PopUp_JCO(int inter_arm_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
			
				
				q="SELECT \r\n" + 
						"iat.id,\r\n" + 
						"iat.authority, \r\n" + 
						"ltrim(TO_CHAR(iat.date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority,\r\n" + 
						"ac.arm_desc as parent_arm_service, \r\n"+
						 "iat.record_office_unit," + 
						"re.arm_desc as regt,  \r\n" + 
						"ltrim(TO_CHAR(iat.with_effect_from ,'DD-MON-YYYY'),'0') as with_effect_from,  \r\n" + 
						"iat.created_by, \r\n" + 
						"ltrim(TO_CHAR(iat.created_date ,'DD-MON-YYYY'),'0') as created_date,\r\n" + 
						"iat.modified_by,\r\n" + 
						"ltrim(TO_CHAR(iat.modified_date ,'DD-MON-YYYY'),'0') as modified_date  \r\n" + 
						"FROM tb_psg_inter_arm_transfer_jco iat\r\n" + 
						"inner join tb_miso_orbat_arm_code ac on ac.arm_code=iat.parent_arm_service \r\n" + 
						"left join tb_miso_orbat_arm_code re on re.arm_code=iat.regt\r\n" + 
						"inner join tb_psg_census_jco_or_p pc on pc.id=iat.jco_id and pc.status in ('1','5') \r\n" + 
						"WHERE   jco_id =? and iat.status in (1,2) order by id desc";
						
	
					stmt=conn.prepareStatement(q);
					stmt.setInt(1,inter_arm_jco_id);
					
					
					
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("authority"));//0
						list.add(rs.getString("date_of_authority"));//1
						//list.add(rs.getString("old_arm_service"));//2
						//list.add(rs.getString("old_regt"));//3
						list.add(rs.getString("parent_arm_service"));//2
						list.add(rs.getString("regt"));//3
						list.add(rs.getString("record_office_unit"));//4
						list.add(rs.getString("with_effect_from"));//5
						list.add(rs.getString("created_by"));//6
						list.add(rs.getString("created_date"));//7
						list.add(rs.getString("modified_by"));//8
						list.add(rs.getString("modified_date"));//9
						
						
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
