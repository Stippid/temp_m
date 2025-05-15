package com.dao.psg.OfficerReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Commandwise_M_F_ReportDAOImpl implements Commandwise_M_F_ReportDAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> Search_Commandwise(String command,String from_date,String to_date)	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";		
		 try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;		
	
			if (command != "") {
				qry += " where u.level_c like  ? ";
			}
			 if(!from_date.equals("") && !to_date.equals("")){
				 qry += " where cast(p.created_date as date) >= cast(? as date) and cast(p.created_date as date) <= cast(? as date)";
		  }

		/*		
			if (command.equals("")) {

				q = "select u.level_c ,COUNT(CASE WHEN UPPER(g.gender_name) = UPPER('Male') THEN 1 END) Male,\n" + 
						"COUNT(CASE WHEN UPPER(g.gender_name) = UPPER('Female') THEN 1 END) Female,\n" + 
						"COUNT(CASE WHEN UPPER(g.gender_name) = UPPER('OTHER') THEN 1 END) Other,count(p.*) as Total\n" + 
						"from all_fmn_view fv  \n" + 
						"inner join tb_miso_orbat_unt_dtl u on SUBSTRING(u.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy='Command'\n" + 
						"inner  join tb_psg_trans_proposed_comm_letter p on u.sus_no = p.unit_sus_no  \n" + 
						"inner join tb_psg_mstr_gender g on g.id=p.gender\n" + 
						"group by u.level_c ";
			} else {*/

				q = "select u.level_c ,COUNT(CASE WHEN UPPER(g.gender_name) = UPPER('Male') THEN 1 END) Male,\n" + 
						"COUNT(CASE WHEN UPPER(g.gender_name) = UPPER('Female') THEN 1 END) Female,\n" + 
						"COUNT(CASE WHEN UPPER(g.gender_name) = UPPER('OTHER') THEN 1 END) Other,count(p.*) as Total\n" + 
						"from all_fmn_view fv  \n" + 
						"inner join tb_miso_orbat_unt_dtl u on SUBSTRING(u.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy='Command' and u.status_sus_no='Active' \n" + 
						"inner  join tb_psg_trans_proposed_comm_letter p on u.sus_no = p.unit_sus_no  \n" + 
						"inner join tb_psg_mstr_gender g on g.id=p.gender " +  qry + " group by u.level_c ";
		//	}
		
			stmt = conn.prepareStatement(q);
			System.out.println(stmt);
			int j = 1;
			if (!command.equals("")) {
				stmt.setString(j, "%" + command + "%");
				j+=1;
			}
			   
			  if(!from_date.equals("") && !to_date.equals("")){
				   stmt.setString(j, from_date); 
				   j++;
					stmt.setString(j, to_date); 
					
			   }
			   
		      ResultSet rs = stmt.executeQuery();  
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  	list.add(rs.getString("level_c"));
					list.add(rs.getString("Male"));
					list.add(rs.getString("Female"));					
					list.add(rs.getString("Other"));
					list.add(rs.getString("Total"));
					
					
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
	
 public ArrayList<ArrayList<String>> Search_pdf(String level_c,String male,String female, String total)  {

		
	
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select u.level_c ,COUNT(CASE WHEN UPPER(g.gender_name) = UPPER('Male') THEN 1 END) Male,\n" + 
					"COUNT(CASE WHEN UPPER(g.gender_name) = UPPER('Female') THEN 1 END) Female,\n" + 
					"COUNT(CASE WHEN UPPER(g.gender_name) = UPPER('OTHER') THEN 1 END) Other,count(p.*) as Total\n" + 
					"from all_fmn_view fv  \n" + 
					"inner join tb_miso_orbat_unt_dtl u on SUBSTRING(u.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy='Command' and u.status_sus_no='Active' \n" + 
					"inner  join tb_psg_trans_proposed_comm_letter p on u.sus_no = p.unit_sus_no  \n" + 
					"inner join tb_psg_mstr_gender g on g.id=p.gender\n" + 
					"group by u.level_c ";
			
			stmt = conn.prepareStatement(q);

//			stmt.setInt(1, Integer.parseInt(search_id));
//			stmt.setInt(2, Integer.parseInt(Mod_id));
			

			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++));
				list.add(rs.getString("level_c"));
			    list.add(rs.getString("Male"));
			    list.add(rs.getString("Female"));
			    list.add(rs.getString("Other"));
			    list.add(rs.getString("Total"));
		
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
