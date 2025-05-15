package com.dao.psg.popup_history;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
public class Update_BPETDetails_History_DAOImpl implements Update_BPETDetails_History_DAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<ArrayList<String>> update_bpet_details(BigInteger comm_id,int census_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{  	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			  
			    q="select distinct b.id,tdb.bpet_result as bept_result,"
			    		+ "td.bpet_qt as bpet_qe,"
			    		+ "b.bept_result_others,"
			    		+ "b.year,"
			    		+ "mo.unit_name,"
			    		+ "b.created_by,"
			    		+ "ltrim(TO_CHAR(b.created_date,'DD-MON-YYYY'),'0') as created_date,"
			    		+ "b.modified_by,"
			    		+ "ltrim(TO_CHAR(b.modified_date,'DD-MON-YYYY'),'0') as modified_date\n" +
			    		 " from tb_psg_census_bpet b\n" + 
			    			"				inner join  tb_psg_mstr_bpet_qt td on td.id::text=b.bpet_qe \n" + 
							"				inner join tb_psg_mstr_bpet_result tdb on tdb.id::text=b.bpet_result " + 
			    		"inner join tb_miso_orbat_unt_dtl mo on mo.sus_no=b.unit_sus_no and mo.status_sus_no='Active' \n"
			    		+ "where cast(b.comm_id as character varying)=? and b.census_id=? and b.status in (1,2) order by b.id";
			   
			   
			stmt=conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			stmt.setInt(2,census_id);
			ResultSet rs = stmt.executeQuery();      
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("bept_result"));//1
				list.add(rs.getString("bept_result_others"));//2
				list.add(rs.getString("bpet_qe"));//3
				list.add(rs.getString("year"));//4
				list.add(rs.getString("unit_name"));//5
				list.add(rs.getString("created_by"));//6
				list.add(rs.getString("created_date"));//7
				list.add(rs.getString("modified_by"));//8
				list.add(rs.getString("modified_date"));//9
				
				
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
					} catch (SQLException e) {}
				}
			}
			
			return alist;
	}
}
