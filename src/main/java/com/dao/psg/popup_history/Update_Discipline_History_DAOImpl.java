package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Update_Discipline_History_DAOImpl implements Update_Discipline_History_DAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	@Override
	public ArrayList<ArrayList<String>> update_discipline(BigInteger comm_id, int census_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			q="SELECT d.id, \r\n" + 
					"		d.disi_authority, \r\n" + 
					"		ltrim(TO_CHAR(d.disi_authority_date ,'DD-MON-YYYY'),'0') as disi_authority_date, \r\n" + 
					"		ac.army_act_sec, \r\n" + 
					"		sc.sub_clause,\r\n" + 
					"		tb.disc_trialed,\r\n" + 
					"		d.description, \r\n" + 
					"		case when td.label = 'OTHER' then d.type_of_entry_other \r\n" + 
					"		else td.label  end as type_of_entry ,\r\n" + 
					"		ltrim(TO_CHAR(d.award_dt ,'DD-MON-YYYY'),'0') as award_dt, \r\n" + 
					"		ud.unit_name,\r\n" + 
					"		d.created_by,  \r\n" + 
					"		ltrim(TO_CHAR(d.created_date ,'DD-MON-YYYY'),'0') as created_date, \r\n" + 
					"		d.modified_by, \r\n" + 
					"		ltrim(TO_CHAR(d.modified_date ,'DD-MON-YYYY'),'0') as modified_date\r\n" + 
					"FROM tb_psg_census_discipline d \r\n" + 
					"inner join t_domain_value td on cast(d.type_of_entry as char)=td.codevalue and td.domainid='DISCIPLINE' \r\n" + 
					"inner join tb_miso_orbat_unt_dtl ud on ud.sus_no=d.unit_name and ud.status_sus_no='Active' \r\n" + 
					"inner join tb_psg_mstr_army_act_sec ac on ac.id = d.army_act_sec\r\n" + 
					"inner join tb_psg_mstr_sub_clause sc on sc.id = d.sub_clause\r\n" + 
					"inner join tb_psg_mstr_disc_trialed tb on tb.id = d.trialed_by\r\n" + 
					"where d.status in (1,2)  AND  cast(d.comm_id as character varying)= ?  AND d.census_id  = ? ORDER BY d.id " ;
				
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,String.valueOf(comm_id));
				stmt.setInt(2,census_id);
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("disi_authority"));//0
					list.add(rs.getString("disi_authority_date"));//1
					list.add(rs.getString("army_act_sec"));//2
					list.add(rs.getString("sub_clause"));//3
					list.add(rs.getString("disc_trialed"));//4
					list.add(rs.getString("description"));//5
					list.add(rs.getString("type_of_entry"));//6
					list.add(rs.getString("award_dt"));//7
					list.add(rs.getString("unit_name"));//8
					list.add(rs.getString("created_by"));//9
					list.add(rs.getString("created_date"));//10
					list.add(rs.getString("modified_by"));//11
					list.add(rs.getString("modified_date"));//12
				
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
