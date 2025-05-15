package com.dao.psg.OfficerReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Parent_Arm_DAOImpl implements Parent_ArmDAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> Search_arm_desc()
	{
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";		
		 try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;		
	
				q = "select a.arm_desc,count(p.personnel_no) as str  from tb_miso_orbat_arm_code a \n" + 
						"left join tb_miso_orbat_unt_dtl u on SUBSTR(u.arm_code,1,2)|| '0' = SUBSTR(a.arm_code,1,2)|| '0' and u.status_sus_no='Active' \n" + 
						"left join tb_psg_trans_proposed_comm_letter p on u.sus_no = p.unit_sus_no  \n" + 
						"where SUBSTR(a.arm_code,3,4)= '00' and (SUBSTR(a.arm_code,1,1)= '0' or SUBSTR(a.arm_code,1,1) = '1')\n" + 
						"group by 1";

		
			stmt = conn.prepareStatement(q);

			  int i = 1;
		      ResultSet rs = stmt.executeQuery();  
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(String.valueOf(i++));
		    	  	list.add(rs.getString("arm_desc"));
					list.add(rs.getString("str"));
					
					alist.add(list);	
					i++;
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
	
	public ArrayList<ArrayList<String>> Search_gorkha()
	{
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";		
		 try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;		
	
				q = "select a.arm_desc,count(p.personnel_no) as str  from tb_miso_orbat_arm_code a \n" + 
						"left join tb_miso_orbat_unt_dtl u on SUBSTR(u.arm_code,1,2)|| '0' = SUBSTR(a.arm_code,1,2)|| '0' and u.status_sus_no='Active' \n" + 
						"left join tb_psg_trans_proposed_comm_letter p on u.sus_no = p.unit_sus_no  \n" + 
						"where SUBSTR(a.arm_code,1,2)= '07' AND a.arm_code != '0700'\n" + 
						"group by 1";

		int i = 1;
			stmt = conn.prepareStatement(q);

	
		      ResultSet rs = stmt.executeQuery();  
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(String.valueOf(i++));
		    	  	list.add(rs.getString("arm_desc"));
					list.add(rs.getString("str"));
					
					
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
	
	public ArrayList<ArrayList<String>> Search_infantry()
	{
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";		
		 try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;		
	
				q = "select a.arm_desc,count(p.personnel_no) as str  from tb_miso_orbat_arm_code a \n" + 
						"left join tb_miso_orbat_unt_dtl u on SUBSTR(u.arm_code,1,2)|| '0' = SUBSTR(a.arm_code,1,2)|| '0' and u.status_sus_no='Active' \n" + 
						"left join tb_psg_trans_proposed_comm_letter p on u.sus_no = p.unit_sus_no  \n" + 
						"where SUBSTR(a.arm_code,1,2)= '08' AND a.arm_code != '0800'\n" + 
						"group by 1";

		
			stmt = conn.prepareStatement(q);

	
		      ResultSet rs = stmt.executeQuery();  
		      int i = 1;
		     
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(String.valueOf(i++));
		    	  	list.add(rs.getString("arm_desc"));
					list.add(rs.getString("str"));
					
					
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

			q = "select a.arm_desc,count(p.personnel_no) as str  from tb_miso_orbat_arm_code a \n" + 
					"left join tb_miso_orbat_unt_dtl u on SUBSTR(u.arm_code,1,2)|| '0' = SUBSTR(a.arm_code,1,2)|| '0' and u.status_sus_no='Active' \n" + 
					"left join tb_psg_trans_proposed_comm_letter p on u.sus_no = p.unit_sus_no  \n" + 
					"where SUBSTR(a.arm_code,3,4)= '00' and (SUBSTR(a.arm_code,1,1)= '0' or SUBSTR(a.arm_code,1,1) = '1')\n" + 
					"group by 1";
			
			stmt = conn.prepareStatement(q);

//			stmt.setInt(1, Integer.parseInt(search_id));
//			stmt.setInt(2, Integer.parseInt(Mod_id));
			

			ResultSet rs = stmt.executeQuery();
			int i=1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				 list.add(String.valueOf(i++));
				list.add(rs.getString("arm_desc"));
			    list.add(rs.getString("str"));
		
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

