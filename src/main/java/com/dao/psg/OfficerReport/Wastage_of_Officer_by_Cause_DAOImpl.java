package com.dao.psg.OfficerReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Wastage_of_Officer_by_Cause_DAOImpl implements Wastage_of_Officer_by_Cause_DAO {


	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> Search_wastage_officer_by_cause()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";	
		 try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;		
	

				q = "select d.causes_name as label,count(p.personnel_no) from tb_psg_non_effective n\n" + 
						"left join tb_psg_mstr_cause_of_non_effective d on d.id::text=n.cause_of_non_effective \n" + 
						"left join tb_psg_trans_proposed_comm_letter p on p.id=n.comm_id where p.status='1'\n" + 
						" group by 1";
			
			
		
			stmt = conn.prepareStatement(q);
			   
		      ResultSet rs = stmt.executeQuery();  
		     
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  	list.add(rs.getString("label"));
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

			q = "select d.causes_name as label ,count(p.personnel_no) from tb_psg_non_effective n\n" + 
					"left join tb_psg_mstr_cause_of_non_effective d on d.id::text=n.cause_of_non_effective \n" + 
					"left join tb_psg_trans_proposed_comm_letter p on p.id=n.comm_id where p.status='1'\n" + 
					" group by 1";
			
			stmt = conn.prepareStatement(q);
			

			ResultSet rs = stmt.executeQuery();
			int i=1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++));
				list.add(rs.getString("label"));
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
