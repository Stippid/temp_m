package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Update_Firing_Standard_Popup_JCO_DAOImpl implements Update_Firing_Standard_Popup_JCO_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<ArrayList<String>> Update_Firing_Standard_JCO(int jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="select distinct bt.id,\r\n" + 
							"					case when tdb.firing_result='OTHERS' then bt.ot_firing_grade\r\n" + 
							"					else tdb.firing_result end as firing_grade, \r\n" + 
							"					td.firing_qt as firing_event_qe,bt.year,mo.unit_name,bt.created_by,ltrim(TO_CHAR(bt.created_date,'DD-MON-YYYY'),'0') as created_date,\r\n" + 
							"					ltrim(TO_CHAR(bt.modified_date,'DD-MON-YYYY'),'0') as modified_date,bt.modified_by\r\n" + 
							"					from tb_psg_census_jco_or_p p inner join \r\n" + 
							"					tb_psg_census_firing_standard_jco bt on p.id = bt.jco_id and p.status in ('1','5') \r\n" + 
							"					               inner join tb_miso_orbat_unt_dtl mo on mo.sus_no=bt.firing_unit_sus_no and mo.status_sus_no  = 'Active'\r\n" + 
							"									inner join tb_psg_mstr_firing_qt td on td.id::text=bt.firing_event_qe \r\n" + 
							"									inner join tb_psg_mstr_firing_result tdb on tdb.id::text=bt.firing_grade \r\n" + 
							"									where bt.jco_id=? and bt.status in (1,2) order by bt.id desc";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,jco_id);
				
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("firing_grade"));//0
					list.add(rs.getString("firing_event_qe"));//1
					list.add(rs.getString("year"));//2
					list.add(rs.getString("unit_name"));//3
					list.add(rs.getString("created_by"));//4
					list.add(rs.getString("created_date"));//5
					list.add(rs.getString("modified_by"));//6
					list.add(rs.getString("modified_date"));//7
					
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
