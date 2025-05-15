package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Change_Appointment_History_DAOImpl implements Change_Appointment_History_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public ArrayList<ArrayList<String>> change_Appointment(BigInteger comm_id, int census_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			
			
					
			q="SELECT ap.id, ap.appt_authority,\n" + 
					"ltrim(TO_CHAR(ap.appt_date_of_authority ,'DD-MON-YYYY'),'0') as appt_date_of_authority,\n" + 
					"cue.description as appointment ,ltrim(TO_CHAR(ap.date_of_appointment ,'DD-MON-YYYY'),'0') as date_of_appointment, \n" + 
					"ap.comm_id, ap.census_id,\n" + 
					"ap.status,\n" + 
					"--ud.unit_name as x_list_sus_no, ap.x_list_loc,\n" + 
					"ap.created_by,\n" + 
					"ltrim(TO_CHAR(ap.created_date ,'DD-MON-YYYY'),'0') as created_date, ap.modified_by,\n" + 
					"ltrim(TO_CHAR(ap.modified_date ,'DD-MON-YYYY'),'0') as modified_date\n" + 
					"FROM tb_psg_change_of_appointment ap\n" + 
					"inner join  cue_tb_psg_rank_app_master cue on cue.id=ap.appointment and upper(cue.level_in_hierarchy) = 'APPOINTMENT' and parent_code ='0' \n" + 
					"WHERE ap.status in (1,2)  AND cast(ap.comm_id as character varying) = ? "
					//+ "AND ap.census_id = ? "
					+ "order by ap.id";
			
				stmt=conn.prepareStatement(q);
				stmt.setString(1,String.valueOf(comm_id));
				//stmt.setInt(2,census_id);
				
				System.err.println("\n \n popup change_Appointment \n" + stmt);
				ResultSet rs = stmt.executeQuery(); 
			

				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("appt_authority"));//1
					list.add(rs.getString("appt_date_of_authority"));//2
					list.add(rs.getString("appointment"));//3
					list.add(rs.getString("date_of_appointment"));//4
					//list.add(rs.getString("x_list_sus_no"));//5
					//list.add(rs.getString("x_list_loc"));//6
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
	
	
	@Override
	public ArrayList<ArrayList<String>> change_Appointment_xml(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;

					
			q="SELECT ap.id, ap.appt_authority,\n" + 
					"ltrim(TO_CHAR(ap.appt_date_of_authority ,'DD-MON-YYYY'),'0') as appt_date_of_authority,\n" + 
					"cue.description as appointment ,ltrim(TO_CHAR(ap.date_of_appointment ,'DD-MON-YYYY'),'0') as date_of_appointment, \n" + 
					"ap.comm_id, ap.census_id,\n" + 
					"ap.status,\n" + 
					"--ud.unit_name as x_list_sus_no, ap.x_list_loc,\n" + 
					"ap.created_by,\n" + 
					"ltrim(TO_CHAR(ap.created_date ,'DD-MON-YYYY'),'0') as created_date, ap.modified_by,\n" + 
					"ltrim(TO_CHAR(ap.modified_date ,'DD-MON-YYYY'),'0') as modified_date\n" + 
					"FROM tb_psg_change_of_appointment ap\n" + 
					"inner join  cue_tb_psg_rank_app_master cue on cue.id=ap.appointment and upper(cue.level_in_hierarchy) = 'APPOINTMENT' and parent_code ='0' \n" + 
					"WHERE ap.status in (1,2)  AND cast(ap.comm_id as character varying) = ? order by ap.id";
			
				stmt=conn.prepareStatement(q);
				stmt.setString(1,String.valueOf(comm_id));
		
				ResultSet rs = stmt.executeQuery(); 
			
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("appt_authority"));//1
					list.add(rs.getString("appt_date_of_authority"));//2
					list.add(rs.getString("appointment"));//3
					list.add(rs.getString("date_of_appointment"));//4
					//list.add(rs.getString("x_list_sus_no"));//5
					//list.add(rs.getString("x_list_loc"));//6
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
	@Override
	public ArrayList<ArrayList<String>> change_Appointment_3008(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			
			
					
			q="SELECT ap.id, ap.appt_authority,\n" + 
					"ltrim(TO_CHAR(ap.appt_date_of_authority ,'DD-MON-YYYY'),'0') as appt_date_of_authority,\n" + 
					"cue.description as appointment ,ltrim(TO_CHAR(ap.date_of_appointment ,'DD-MON-YYYY'),'0') as date_of_appointment, \n" + 
					"ap.comm_id, ap.census_id,\n" + 
					"ap.status,\n" + 
					"--ud.unit_name as x_list_sus_no, ap.x_list_loc,\n" + 
					"ap.created_by,\n" + 
					"ltrim(TO_CHAR(ap.created_date ,'DD-MON-YYYY'),'0') as created_date, ap.modified_by,\n" + 
					"ltrim(TO_CHAR(ap.modified_date ,'DD-MON-YYYY'),'0') as modified_date\n" + 
					"FROM tb_psg_change_of_appointment ap\n" + 
					"inner join  cue_tb_psg_rank_app_master cue on cue.id=ap.appointment and upper(cue.level_in_hierarchy) = 'APPOINTMENT' and parent_code ='0' \n" + 
					"WHERE ap.status in (1,2)  AND cast(ap.comm_id as character varying) = ? order by ap.id";
			
				stmt=conn.prepareStatement(q);
				stmt.setString(1,String.valueOf(comm_id));
		
				
				
				ResultSet rs = stmt.executeQuery(); 
			

				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("appt_authority"));//1
					list.add(rs.getString("appt_date_of_authority"));//2
					list.add(rs.getString("appointment"));//3
					list.add(rs.getString("date_of_appointment"));//4
					//list.add(rs.getString("x_list_sus_no"));//5
					//list.add(rs.getString("x_list_loc"));//6
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
