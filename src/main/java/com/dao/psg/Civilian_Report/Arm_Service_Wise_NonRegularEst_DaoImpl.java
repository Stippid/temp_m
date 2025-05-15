package com.dao.psg.Civilian_Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Arm_Service_Wise_NonRegularEst_DaoImpl implements Arm_Service_Wise_NonRegularEst_Dao{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	 
	 public ArrayList<ArrayList<String>> Search_Arm_Service_Wise_NonRegularEst()
		{
		 
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";
		
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
					q="select a.arm_desc,\r\n" + 
							" count(*) filter (where c.pay_level not in ('4')) as total,\r\n" + 
							" count(*) filter (where c.pay_level = '3' and c.civ_type = '2') as reg_pay_nonin,\r\n" + 
							" count(*) filter (where c.pay_level = '3' and c.civ_type = '1') as reg_pay_in,\r\n" + 
							" count(*) filter (where c.pay_level = '1' and c.civ_type = '2') as cont_nonin,\r\n" + 
							" count(*) filter (where c.pay_level = '1' and c.civ_type = '1') as cont_in,\r\n" + 
							" count(*) filter (where c.pay_level = '2' and c.civ_type = '2') as work_per_nonin,\r\n" + 
							" count(*) filter (where c.pay_level = '2' and c.civ_type = '1') as work_per_in\r\n" + 
							" from tb_psg_civilian_dtl_main c\r\n" + 
							"inner join tb_psg_mstr_pay_head t on c.pay_level = t.id\r\n" + 
							"inner join cue_psg_auth_held ah on c.sus_no = ah.unit_sus_no\r\n" + 
							"inner join tb_miso_orbat_arm_code a on a.arm_code = ah.arm_code\r\n" + 
							" where c.civilian_status = 'NR' and c.status = '1' group by a.arm_desc" ;
			
				
				stmt=conn.prepareStatement(q);
				System.err.println("query for agewise: \n" + stmt);
		      ResultSet rs = stmt.executeQuery();   
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      int i = 0;
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  i++;
		    	  list.add(String.valueOf(i));//0
		    	  list.add(rs.getString("arm_desc"));//0
		    	  list.add(rs.getString("reg_pay_nonin"));//1
		    	  list.add(rs.getString("reg_pay_in"));//2
		    	  list.add(rs.getString("cont_nonin"));//3
		    	  list.add(rs.getString("cont_in"));//4
		    	  list.add(rs.getString("work_per_nonin"));//5
				  list.add(rs.getString("work_per_in"));//6
		    	  list.add(rs.getString("total"));//7
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
