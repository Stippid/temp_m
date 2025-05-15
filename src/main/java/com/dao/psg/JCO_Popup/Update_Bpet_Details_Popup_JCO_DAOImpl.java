package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Update_Bpet_Details_Popup_JCO_DAOImpl implements Update_Bpet_Details_Popup_JCO_DAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<ArrayList<String>> Update_Bpet_Details_JCO(int jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="select distinct b.id,tdb.bpet_result as bept_result,\r\n" + 
							"			    		td.bpet_qt as bpet_qe,\r\n" + 
							"			    		b.bept_result_others,\r\n" + 
							"			    		b.year,\r\n" + 
							"			    		mo.unit_name,\r\n" + 
							"			    		b.created_by,\r\n" + 
							"			    		ltrim(TO_CHAR(b.created_date,'DD-MON-YYYY'),'0') as created_date,\r\n" + 
							"			    		b.modified_by,\r\n" + 
							"			    		ltrim(TO_CHAR(b.modified_date,'DD-MON-YYYY'),'0') as modified_date\r\n" + 
							"			    		from tb_psg_census_jco_or_p p inner join \r\n" + 
							"						tb_psg_census_bpet_jco b on p.id = b.jco_id and p.status in ('1','5') \r\n" + 
							"			    					inner join  tb_psg_mstr_bpet_qt td on td.id::text=b.bpet_qe \r\n" + 
							"									inner join tb_psg_mstr_bpet_result tdb on tdb.id::text=b.bpet_result  \r\n" + 
							"			    		inner join tb_miso_orbat_unt_dtl mo on mo.sus_no=b.unit_sus_no and mo.status_sus_no='Active' \r\n" + 
							"			    		where b.jco_id=? and b.status in (1,2) order by b.id desc";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,jco_id);
				
			
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
