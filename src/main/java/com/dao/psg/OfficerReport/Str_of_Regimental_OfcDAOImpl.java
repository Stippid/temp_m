package com.dao.psg.OfficerReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Str_of_Regimental_OfcDAOImpl implements Str_of_Regimental_OfcDAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> Search_str_of_regimental_ofc()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		
		 try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;		

				q = "select u.sus_no,u.unit_name,count(p.unit_sus_no) from tb_miso_orbat_unt_dtl u\n" + 
						"inner join tb_psg_trans_proposed_comm_letter p on p.unit_sus_no=u.sus_no\n" + 
						"where  u.status_sus_no='Active'  group by 1,2";
		
		
			stmt = conn.prepareStatement(q);
			   
		      ResultSet rs = stmt.executeQuery();  
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  	list.add(rs.getString("sus_no"));
					list.add(rs.getString("unit_name"));
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
	
public ArrayList<ArrayList<String>> Search_pdf()  {

	
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q = "select u.sus_no,u.unit_name,count(p.unit_sus_no) from tb_miso_orbat_unt_dtl u\n" + 
					"inner join tb_psg_trans_proposed_comm_letter p on p.unit_sus_no=u.sus_no\n" + 
					"where  u.status_sus_no='Active'  group by 1,2";
			
			stmt = conn.prepareStatement(q);
			

			ResultSet rs = stmt.executeQuery();
			int i=1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++));
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));
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
