package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Extension_of_SSC_History_DAOImpl implements Extension_of_SSC_History_DAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public ArrayList<ArrayList<String>> Extension_of_SSC(BigInteger comm_id, int census_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			
			q="SELECT id, authority,ltrim(TO_CHAR(date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority ,ltrim(TO_CHAR(fromdt ,'DD-MON-YYYY'),'0') as fromdt,ltrim(TO_CHAR(todt ,'DD-MON-YYYY'),'0') as todt,created_by,ltrim(TO_CHAR(created_date ,'DD-MON-YYYY'),'0') as created_date, modified_by, ltrim(TO_CHAR(modified_date ,'DD-MON-YYYY'),'0') as modified_date \n"+
			"FROM tb_psg_extension_of_comission\n"+
			" WHERE status in (1,2) AND cast(comm_id as character varying)=? AND census_id = ? order by id"; 
			
						
			            stmt=conn.prepareStatement(q);
						stmt.setString(1,String.valueOf(comm_id));
						stmt.setInt(2,census_id);
						
						
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
