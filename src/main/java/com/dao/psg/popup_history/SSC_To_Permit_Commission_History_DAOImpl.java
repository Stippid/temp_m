package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

public class SSC_To_Permit_Commission_History_DAOImpl implements SSC_To_Permit_Commission_History_DAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	@Override
	public ArrayList<ArrayList<String>> ssc_to_permit_commission_dtl(BigInteger comm_id, int census_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			//26-01-1994
//			q="SELECT \r\n" + 
//					"	cc.id, cc.authority,\r\n" + 
//					"	ltrim(TO_CHAR(cc.date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority, cc.old_personal_no,cc.new_personal_no,\r\n" + 
//					"	tc.comn_name as previous_commission, tc.comn_name as type_of_commission, \r\n" + 
//					"	ltrim(TO_CHAR(cc.date_of_permanent_commission ,'DD-MON-YYYY'),'0') as date_of_permanent_commission,\r\n" + 
//					"	ltrim(TO_CHAR(cc.date_of_seniority ,'DD-MON-YYYY'),'0') as date_of_seniority, \r\n" + 
//					"	cc.created_by, \r\n" + 
//					"	ltrim(TO_CHAR(cc.created_date ,'DD-MON-YYYY'),'0') as created_date,\r\n" + 
//					"	cc.modified_by, ltrim(TO_CHAR(cc.modified_date ,'DD-MON-YYYY'),'0') as modified_date \r\n" + 
//					"	FROM tb_psg_change_of_comission cc\r\n" + 
//					"	inner join tb_psg_mstr_type_of_commission tc on tc.id=cc.type_of_commission_granted\r\n" + 
//					"    WHERE cc.status in (1,2) AND cast(comm_id as character varying) = ?  and  cc.id Not in (select id from tb_psg_change_of_comission order by id desc limit 1) ORDER BY cc.id" ;
			q="SELECT \r\n" + 
					"	cc.id, cc.authority,\r\n" + 
					"	ltrim(TO_CHAR(cc.date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority, cc.old_personal_no,cc.new_personal_no,\r\n" + 
					"	tc.comn_name as previous_commission, tc.comn_name as type_of_commission, \r\n" + 
					"	ltrim(TO_CHAR(cc.date_of_permanent_commission ,'DD-MON-YYYY'),'0') as date_of_permanent_commission,\r\n" + 
					"	ltrim(TO_CHAR(cc.date_of_seniority ,'DD-MON-YYYY'),'0') as date_of_seniority, \r\n" + 
					"	cc.created_by, \r\n" + 
					"	ltrim(TO_CHAR(cc.created_date ,'DD-MON-YYYY'),'0') as created_date,\r\n" + 
					"	cc.modified_by, ltrim(TO_CHAR(cc.modified_date ,'DD-MON-YYYY'),'0') as modified_date \r\n" + 
					"	FROM tb_psg_change_of_comission cc\r\n" + 
					"	inner join tb_psg_mstr_type_of_commission tc on tc.id=cc.type_of_commission_granted\r\n" + 
					"    WHERE cc.cancel_status=-1 and cc.status in (1,2) AND cast(comm_id as character varying) = ?  and  cc.id Not in (select id from tb_psg_change_of_comission WHERE cast(comm_id as character varying) = ? order by id limit 1) ORDER BY cc.id" ;
				
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,String.valueOf(comm_id));
				stmt.setString(2,String.valueOf(comm_id));
				 
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("authority"));//0
					list.add(rs.getString("date_of_authority"));//1
					list.add(rs.getString("new_personal_no"));//2
					list.add(rs.getString("previous_commission"));//3
					list.add(rs.getString("type_of_commission"));//4
					list.add(rs.getString("date_of_permanent_commission"));//5
					list.add(rs.getString("date_of_seniority"));//6
				    list.add(rs.getString("created_by"));//7
					list.add(rs.getString("created_date"));//8
					list.add(rs.getString("modified_by"));//9
					list.add(rs.getString("modified_date"));//10
					list.add(rs.getString("old_personal_no"));//11
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
