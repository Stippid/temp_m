package com.dao.psg.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;


public class NotificationDaoImpl implements NotificationDao{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public int getRejectNotification(String susNo) {
    	int cont = 0;
		Connection conn = null;
		try{	  
    		conn = dataSource.getConnection();
    		String sql= "select count(cp.status) as total from tb_psg_census_detail_p cp \r\n" + 
    				"inner join tb_psg_trans_proposed_comm_letter comm on cp.comm_id=comm.id\r\n" + 
    				"where comm.unit_sus_no=? and cp.status=3";
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, susNo);
			
			ResultSet rs1 = stmt.executeQuery();   
			rs1.next();
			cont = rs1.getInt("total");
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return cont;
    }
	public int getcountNotification(String susNo) {
    	int cont = 0;
		Connection conn = null;
		try{	  
    		conn = dataSource.getConnection();
    		String sql= "select count(c.personnel_no) as total  from tb_psg_posting_in_out p\r\n" + 
    				"inner join tb_psg_trans_proposed_comm_letter c\r\n" + 
    				"on c.id =p.comm_id where p.to_sus_no=? and p.status=1 and c.status !='4' and p.tenure_date  is null  and p.notification_status=0 " ;
    		
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, susNo);
			
			ResultSet rs1 = stmt.executeQuery();   
			rs1.next();
			cont = rs1.getInt("total");
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		
		return cont;
    }
	
	
	
	public ArrayList<ArrayList<String>> getpersonalNotification(String susNo)
	{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String qry="";

	try{
			conn = dataSource.getConnection();
			PreparedStatement stmt=null;
			if( !susNo.equals("")) {
				qry += "  and p.to_sus_no like ? ";
			}

			
			 

			q= "	select c.personnel_no,c.name ,ran.description,case when orb.unit_name is null then 'Newly Commissioned' else  orb.unit_name  end as unit_name\r\n" + 
					"	from \r\n" + 
					"	tb_psg_posting_in_out p \r\n" + 
					"	inner join tb_psg_trans_proposed_comm_letter c  on c.id =p.comm_id  \r\n" + 
					"	left join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.from_sus_no and status_sus_no='Active'\r\n" + 
					"	inner join cue_tb_psg_rank_app_master ran on ran.id = c.rank and\r\n" + 
					"	upper(ran.level_in_hierarchy) = 'RANK' \r\n" + 
					"	where p.status=1 and c.status !='4' and p.tenure_date  is null  and p.notification_status=0 " +qry+ " ";
					 
			stmt=conn.prepareStatement(q);
			if(!qry.equals(""))
			{  int j =1;
				if( !susNo.equals("")) {
					stmt.setString(j, susNo);
					j += 1;
				}
			}
	      ResultSet rs = stmt.executeQuery();
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      while (rs.next()) {
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  list.add(rs.getString("personnel_no"));
	    	  list.add(rs.getString("name"));
	    	  list.add(rs.getString("description"));
	    	  list.add(rs.getString("unit_name"));

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
