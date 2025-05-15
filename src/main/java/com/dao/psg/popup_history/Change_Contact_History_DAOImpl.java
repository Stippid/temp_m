package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Change_Contact_History_DAOImpl implements Change_Contact_History_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public ArrayList<ArrayList<String>> change_Contact(BigInteger comm_id, int census_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
//			q="SELECT id, census_id, created_by, to_char(created_date,'DD/MON/YYYY') as created_date, PGP_SYM_DECRYPT(gmail ::bytea,current_setting('miso.version'))  as gmail, PGP_SYM_DECRYPT(mobile_no ::bytea,current_setting('miso.version'))  as mobile_no, modified_by, to_char(modified_date,'DD/MON/YYYY') as modified_date, PGP_SYM_DECRYPT(nic_mail ::bytea,current_setting('miso.version'))  as nic_mail, status, comm_id \n" +
//					"FROM public.tb_psg_census_contact_cda_account_details\n" +
//					" WHERE status in (1,2)  AND cast(comm_id as character varying)=? AND census_id = ? order by id";
			
			q="SELECT id, census_id, created_by, to_char(created_date,'DD/MON/YYYY') as created_date, PGP_SYM_DECRYPT(gmail ::bytea,current_setting('miso.version'))  as gmail,  CASE"
					+" WHEN PGP_SYM_DECRYPT(mobile_no::bytea, current_setting('miso.version')) = '0' THEN '' "
					+ "        ELSE PGP_SYM_DECRYPT(mobile_no::bytea, current_setting('miso.version'))"
					+ "  END as mobile_no , modified_by, to_char(modified_date,'DD/MON/YYYY') as modified_date, PGP_SYM_DECRYPT(nic_mail ::bytea,current_setting('miso.version'))  as nic_mail, status, comm_id \n" +
					"FROM public.tb_psg_census_contact_cda_account_details\n" +
					" WHERE status in (1,2)  AND cast(comm_id as character varying)=? AND census_id = ? order by id";
			
				stmt=conn.prepareStatement(q);
				stmt.setString(1,String.valueOf(comm_id));
				stmt.setInt(2,census_id);
				
				
				ResultSet rs = stmt.executeQuery(); 

				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					//list.add(rs.getString("id"));//0
					list.add(rs.getString("gmail"));//1
					list.add(rs.getString("nic_mail"));//2
					list.add(rs.getString("mobile_no"));//3
					
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
