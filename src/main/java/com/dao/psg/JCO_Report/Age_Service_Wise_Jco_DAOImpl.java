package com.dao.psg.JCO_Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;



import javax.sql.DataSource;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class Age_Service_Wise_Jco_DAOImpl implements Age_Service_Wise_Jco_DAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}
	 
	 
	 public ArrayList<ArrayList<String>> Search_Age()
		{
		 
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";
		
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
					q="select \r\n" + 
							"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.date_of_birth::date) between 20 and 24)) as BT20TO24,\r\n" + 
							"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.date_of_birth::date) between 25 and 29)) as BT25TO29,\r\n" + 
							"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.date_of_birth::date) between 30 and 34)) as BT30TO34,\r\n" + 
							"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.date_of_birth::date) between 35 and 39)) as BT35TO39,\r\n" + 
							"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.date_of_birth::date) between 40 and 44)) as BT40TO44,\r\n" + 
							"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.date_of_birth::date) between 45 and 49)) as BT45TO49,\r\n" + 
							"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.date_of_birth::date) between 50 and 54)) as BT50TO54,\r\n" + 
							"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.date_of_birth::date) between 55 and 59)) as BT55TO59,\r\n" + 
							"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.date_of_birth::date) = 60)) as TO60,\r\n" + 
							"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.date_of_birth::date) between 20 and 60)) as total\r\n" + 
							"	from tb_psg_census_jco_or_p c  \r\n" + 
							"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active' and c.status in ('1','5') " ;
			
					
				
				stmt=conn.prepareStatement(q);
				System.err.println("query for Age Grp: \n" + stmt);
		      ResultSet rs = stmt.executeQuery();   
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(rs.getString("BT20TO24"));//0
		    	  list.add(rs.getString("BT25TO29"));//1
		    	  list.add(rs.getString("BT30TO34"));//2
		    	  list.add(rs.getString("BT35TO39"));//3
		    	  list.add(rs.getString("BT40TO44"));//4
		    	  list.add(rs.getString("BT45TO49"));//5
				  list.add(rs.getString("BT50TO54"));//6
				  list.add(rs.getString("BT55TO59"));//7
				  list.add(rs.getString("TO60"));//8
		    	  list.add(rs.getString("total"));//9
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
	 
	 
	 public ArrayList<ArrayList<String>> Search_Service()
		{
		 
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";
		
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
	q="select \r\n" + 
			"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.enroll_dt::date) < 1 )) as BT0TO1,\r\n" + 
			"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.enroll_dt::date) between 1 and 2)) as BT1TO2,\r\n" + 
			"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.enroll_dt::date) between 3 and 4)) as BT3TO4,\r\n" + 
			"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.enroll_dt::date) between 5 and 6)) as BT5TO6,\r\n" + 
			"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.enroll_dt::date) between 7 and 8)) as BT7TO8,\r\n" + 
			"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.enroll_dt::date) between 9 and 10)) as BT9TO10,\r\n" + 
			"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.enroll_dt::date) between 11 and 12)) as BT11TO12,\r\n" + 
			"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.enroll_dt::date) between 13 and 14)) as BT13TO14,\r\n" + 
			"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.enroll_dt::date) between 15 and 19)) as BT15TO19,\r\n" + 
			"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.enroll_dt::date) between 20 and 24)) as BT20TO24,\r\n" + 
			"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.enroll_dt::date) between 25 and 29)) as BT25TO29,\r\n" + 
			"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.enroll_dt::date) = 30)) as TO30,\r\n" + 
			"		count(c.*) FILTER (where (DATE_PART('year', now()::date) - DATE_PART('year',c.enroll_dt::date) between 0 and 30)) as total\r\n" + 
			"	from tb_psg_census_jco_or_p c  \r\n" + 
			"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active'  and c.status in ('1','5') " ;
			
				
				stmt=conn.prepareStatement(q);
				System.err.println("query for Service Grp: \n" + stmt);
		      ResultSet rs = stmt.executeQuery();   
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(rs.getString("BT0TO1"));//0
		    	  list.add(rs.getString("BT1TO2"));//1
		    	  list.add(rs.getString("BT3TO4"));//2
		    	  list.add(rs.getString("BT5TO6"));//3
		    	  list.add(rs.getString("BT7TO8"));//4
		    	  list.add(rs.getString("BT9TO10"));//5
				  list.add(rs.getString("BT11TO12"));//6
				  list.add(rs.getString("BT13TO14"));//7
				  list.add(rs.getString("BT15TO19"));//8
		    	  list.add(rs.getString("BT20TO24"));//9
		    	  list.add(rs.getString("BT25TO29"));//10
				  list.add(rs.getString("TO30"));//11
		    	  list.add(rs.getString("total"));//12
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
