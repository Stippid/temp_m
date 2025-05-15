package com.dao.psg.popup_history;

import java.util.ArrayList;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.persistance.util.HibernateUtil;
public class Inter_Arm_Service_Transfer_History_DAOImpl implements Inter_Arm_Service_Transfer_History_DAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	@Override
	public ArrayList<ArrayList<String>> Inter_Arm_Service_Transfer(BigInteger comm_id, int census_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			q="SELECT \r\n" + 
					"		iat.id,\r\n" + 
					"		iat.authority, \r\n" + 
					"		ltrim(TO_CHAR(iat.date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority,\r\n" + 
					"		iat.old_arm_service, \r\n" + 
					"		iat.old_regt,\r\n" + 				
					"		ac.arm_desc as parent_arm_service, \r\n" + 
					"		re.arm_desc as regt, \r\n" + 
					"		ltrim(TO_CHAR(iat.with_effect_from ,'DD-MON-YYYY'),'0') as with_effect_from, \r\n" + 
					"		iat.created_by, \r\n" + 
					"		ltrim(TO_CHAR(iat.created_date ,'DD-MON-YYYY'),'0') as created_date,\r\n" + 
					"		iat.modified_by, \r\n" + 
					"		ltrim(TO_CHAR(iat.modified_date ,'DD-MON-YYYY'),'0') as modified_date \r\n" + 
					"FROM tb_psg_inter_arm_transfer iat\r\n" + 
					"inner join tb_miso_orbat_arm_code ac on ac.arm_code=iat.parent_arm_service\r\n" + 
					"left join tb_miso_orbat_arm_code re on re.arm_code=iat.regt\r\n" + 
					"where iat.status in (1,2) AND  cast(iat.comm_id as character varying)=? ORDER BY iat.id  " ;
			
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,String.valueOf(comm_id));
//				stmt.setInt(2,census_id);
				 				
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("authority"));//0
					list.add(rs.getString("date_of_authority"));//1					
					list.add(rs.getString("parent_arm_service"));//4
					list.add(rs.getString("regt"));//5
					list.add(rs.getString("with_effect_from"));//6
					list.add(rs.getString("created_by"));//7
					list.add(rs.getString("created_date"));//8
					list.add(rs.getString("modified_by"));//9
					list.add(rs.getString("modified_date"));//10
				
					alist.add(list);
					
	 	        }
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch (SQLException e) {
				//throw new RuntimeException(e);
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
				  }
				}
			}
		return alist;
	}

}
