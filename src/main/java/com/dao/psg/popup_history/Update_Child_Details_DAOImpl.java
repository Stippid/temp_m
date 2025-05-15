package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Update_Child_Details_DAOImpl implements Update_Child_Details_DAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> update_child_dtl(BigInteger comm_id, int census_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			q=" select ch.id,ch.type,ch.name,PGP_SYM_DECRYPT(ch.aadhar_no ::bytea,current_setting('miso.version'))  as aadhar_no,ch.adoted,\n" + 
					" ltrim(TO_CHAR(ch.date_of_adpoted,'dd-MON-yyyy'),'0')  as date_of_adpoted,\n" + 
					" ltrim(TO_CHAR(ch.date_of_birth,'dd-MON-yyyy'),'0') as date_of_birth,ch.modified_by,\n" + 
					" ltrim(TO_CHAR(ch.modified_date,'dd-MON-yyyy'),'0') as modified_date,ch.created_by,\n" + 
					"  ltrim(TO_CHAR(ch.created_date,'dd-MON-yyyy'),'0') as created_date,\n" + 
					" PGP_SYM_DECRYPT(ch.pan_no ::bytea,current_setting('miso.version'))  as pan_no,td.label as relationship \n" + 
					" from tb_psg_census_children ch\n" + 
					"				inner join  t_domain_value td on td.codevalue=cast(ch.relationship as char) and td.domainid='CHILD_RELATIONSHIP'\n" + 
					"				where ch.cen_id =? and cast(ch.comm_id as character varying) = ? and ch.status in (1,2) order by ch.id";
			
				
				stmt=conn.prepareStatement(q);
	
				stmt.setInt(1,census_id);			
				stmt.setString(2,String.valueOf(comm_id));
				ResultSet rs = stmt.executeQuery();    
			
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("name"));
					list.add(rs.getString("date_of_birth"));
					list.add(rs.getString("relationship"));
					list.add(rs.getString("adoted"));
					list.add(rs.getString("date_of_adpoted"));
					list.add(rs.getString("aadhar_no"));
					list.add(rs.getString("pan_no"));
					list.add(rs.getString("created_by"));
					list.add(rs.getString("created_date"));
					list.add(rs.getString("modified_by"));
					list.add(rs.getString("modified_date"));
					
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
