package com.dao.psg.OfficerReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ctpart_i_unit_fmnsDaoImpl  implements ctpart_i_unit_fmnsDao{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>> Search_Ctparti()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		
		 try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;		

				q = "select a.arm_code,a.arm_desc,count(a.arm_code) as count  from tb_miso_orbat_arm_code a \r\n" + 
						"left join tb_miso_orbat_unt_dtl u on a.arm_code = u.arm_code and u.ct_part_i_ii ='CTPartI' and  u.ct_part_i_ii != '' and u.status_sus_no='Active' \r\n" + 
						"left join tb_psg_trans_proposed_comm_letter p on u.sus_no = p.unit_sus_no  \r\n" + 
						"where (SUBSTR(a.arm_code,3,4)= '00' or SUBSTR(a.arm_code,1,2)= '00') AND a.arm_code != '0000'  \r\n" + 
						"group by 1,2 \r\n" + 
						"";
		
		
			stmt = conn.prepareStatement(q);
			   
		      ResultSet rs = stmt.executeQuery();  
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  	list.add(rs.getString("arm_code"));
					list.add(rs.getString("arm_desc"));
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
public ArrayList<ArrayList<String>> Search_CTparti_pdf()  {

		
	
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q = "select a.arm_code,a.arm_desc,count(a.arm_code) as count  from tb_miso_orbat_arm_code a \r\n" + 
					"left join tb_miso_orbat_unt_dtl u on a.arm_code = u.arm_code and u.ct_part_i_ii ='CTPartI' and  u.ct_part_i_ii != '' and u.status_sus_no='Active' \r\n" + 
					"left join tb_psg_trans_proposed_comm_letter p on u.sus_no = p.unit_sus_no  \r\n" + 
					"where (SUBSTR(a.arm_code,3,4)= '00' or SUBSTR(a.arm_code,1,2)= '00') AND a.arm_code != '0000'  \r\n" + 
					"group by 1,2 ";
			
			stmt = conn.prepareStatement(q);
			

			ResultSet rs = stmt.executeQuery();
			int i=1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++));
				list.add(rs.getString("arm_code"));
				list.add(rs.getString("arm_desc"));
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
