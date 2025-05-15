package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Secondment_History_DAOImpl implements Secondment_History_DAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public ArrayList<ArrayList<String>> change_Secondment(BigInteger comm_id, int census_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			
			
			
			/*q="SELECT id, authority, census_id, comm_id, created_by,ltrim(TO_CHAR(created_date ,'DD-MON-YYYY'),'0') as created_date,ltrim(TO_CHAR(date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority, modified_by, ltrim(TO_CHAR(modified_date ,'DD-MON-YYYY'),'0') as modified_date, seconded_to, status,ltrim(TO_CHAR(secondment_effect ,'DD-MON-YYYY'),'0') as secondment_effect \n" +
					 "FROM public.tb_psg_census_secondment\n" +	
				        " WHERE status = 1 OR status = 2  AND comm_id = ? AND census_id = ?";	*/
			
			/*q="SELECT s.id, s.authority,ltrim(TO_CHAR(s.date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority,td.label as seconded_to,"
					+ "ltrim(TO_CHAR(s.secondment_effect ,'DD-MON-YYYY'),'0') as secondment_effect,"
					+ " s.created_by,ltrim(TO_CHAR(s.created_date ,'DD-MON-YYYY'),'0') as created_date, s.modified_by,"
					+ " ltrim(TO_CHAR(s.modified_date ,'DD-MON-YYYY'),'0') as modified_date\n"+
			  "FROM tb_psg_census_secondment s\n"+
		       "inner join t_domain_value td on cast(s.seconded_to as char)=td.codevalue and td.domainid='SECONDMENT'\n"+
	           "where s.census_id=? AND cast(s.comm_id as character varying) = ? and  s.status in (1,2) order by s.id";*/
			
			
			//sana-03-01-2023
			q="SELECT s.id, s.authority,ltrim(TO_CHAR(s.date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority,td.seconded_to,"
					+ "ltrim(TO_CHAR(s.secondment_effect ,'DD-MON-YYYY'),'0') as secondment_effect,"
					+ " s.created_by,ltrim(TO_CHAR(s.created_date ,'DD-MON-YYYY'),'0') as created_date, s.modified_by,"
					+ " ltrim(TO_CHAR(s.modified_date ,'DD-MON-YYYY'),'0') as modified_date\n"+
			  "FROM tb_psg_census_secondment s\n"+
		       "inner join tb_psg_mstr_seconded_to td on s.seconded_to=td.id\n"+
	           "where s.census_id=? AND cast(s.comm_id as character varying) = ? and  s.status in (1,2) order by s.id";
			
						stmt=conn.prepareStatement(q);
						
						stmt.setInt(1,census_id);
						stmt.setString(2,String.valueOf(comm_id));
						
						ResultSet rs = stmt.executeQuery(); 
						

						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("authority"));//1
							list.add(rs.getString("date_of_authority"));//2
							list.add(rs.getString("seconded_to"));//3
							list.add(rs.getString("secondment_effect"));//4
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
