package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Change_Name_History_DAOImpl implements Change_Name_History_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> change_name(BigInteger comm_id, int census_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			
			q="SELECT distinct id, census_id, comm_id, authority, ltrim(TO_CHAR(date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority ,"
					+ " name, ltrim(TO_CHAR(change_in_name_date ,'DD-MON-YYYY'),'0') as change_in_name_date,status, created_by, "
					+ "ltrim(TO_CHAR(created_date ,'DD-MON-YYYY'),'0') as created_date, "
					+ "modified_by,ltrim(TO_CHAR(modified_date ,'DD-MON-YYYY'),'0') as modified_date \n" + 
					"FROM tb_psg_change_name WHERE status in (1,2) AND cast(comm_id as character varying)=?  order by id ";
					
				
				stmt=conn.prepareStatement(q);
				stmt.setString(1,String.valueOf(comm_id));
				
				
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("name"));//3
					list.add(rs.getString("change_in_name_date"));
					list.add(rs.getString("created_by"));//4
					list.add(rs.getString("created_date"));//5
					list.add(rs.getString("modified_by"));//6
					list.add(rs.getString("modified_date"));//7
					
					/*String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Bank Details?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Bank Details?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
		
					list.add(f);
					list.add(f1);*/
					
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
