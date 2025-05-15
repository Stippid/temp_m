package com.dao.psg.popup_history;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
public class Update_Firing_Standard_History_DAOImpl implements Update_Firing_Standard_History_DAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public ArrayList<ArrayList<String>> update_firing_standard_details(BigInteger comm_id,int census_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{  	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			  
			q="select distinct bt.id,\n" + 
					"case when tdb.firing_result='OTHERS' then bt.ot_firing_grade\n" + 
					"else tdb.firing_result end as firing_grade, \n" + 
					"td.firing_qt as firing_event_qe,bt.year,mo.unit_name,bt.created_by,ltrim(TO_CHAR(bt.created_date,'DD-MON-YYYY'),'0') as created_date,"
					+ "ltrim(TO_CHAR(bt.modified_date,'DD-MON-YYYY'),'0') as modified_date,bt.modified_by\n" + 
					"from tb_psg_census_firing_standard bt\n" + 
					"                inner join tb_miso_orbat_unt_dtl mo on mo.sus_no=bt.firing_unit_sus_no and mo.status_sus_no  = 'Active'\n" + 
					"				inner join tb_psg_mstr_firing_qt td on td.id::text=bt.firing_event_qe \n" + 
					"				inner join tb_psg_mstr_firing_result tdb on tdb.id::text=bt.firing_grade \n" + 
					"				where cast(bt.comm_id as character varying)=? and bt.census_id=? and bt.status in (1,2) order by bt.id" ;
			   
			stmt=conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			stmt.setInt(2,census_id);
			
			ResultSet rs = stmt.executeQuery();      
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("firing_grade"));//1
				list.add(rs.getString("firing_event_qe"));//2
				list.add(rs.getString("year"));//3
				list.add(rs.getString("unit_name"));//4
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
