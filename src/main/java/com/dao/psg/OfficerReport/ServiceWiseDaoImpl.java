package com.dao.psg.OfficerReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ServiceWiseDaoImpl implements ServiceWiseDao {

	
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>> Search_Servicewise()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		
		 try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;		

				q = "select u.arm_code,a.arm_desc,count(unit_sus_no) from tb_miso_orbat_unt_dtl u\n" + 
						"inner join tb_miso_orbat_arm_code a on a.arm_code=u.arm_code\n" + 
						"left join tb_psg_trans_proposed_comm_letter p on p.unit_sus_no = u.sus_no\n" + 
						"where  u.status_sus_no='Active'  group by 1,2";
		
		
			stmt = conn.prepareStatement(q);
			   
		      ResultSet rs = stmt.executeQuery();  
		   
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  	list.add(rs.getString("arm_code"));
					list.add(rs.getString("arm_desc"));
					list.add(rs.getString("count"));				
				
				
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
					} catch (SQLException e) {
				  }
				}
			}
			return alist;
		}	
public ArrayList<ArrayList<String>> Search_Servicewise_pdf()  {

		
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q = "select u.arm_code,a.arm_desc,count(unit_sus_no) from tb_miso_orbat_unt_dtl u\n" + 
					"inner join tb_miso_orbat_arm_code a on a.arm_code=u.arm_code\n" + 
					"left join tb_psg_trans_proposed_comm_letter p on p.unit_sus_no = u.sus_no\n" + 
					"where  u.status_sus_no='Active'  group by 1,2";
			
			stmt = conn.prepareStatement(q);
			

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("arm_code"));
				list.add(rs.getString("arm_desc"));
				list.add(rs.getString("count"));
		
				alist.add(list);
				
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
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
