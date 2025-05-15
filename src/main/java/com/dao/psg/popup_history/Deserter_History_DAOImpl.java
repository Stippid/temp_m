package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Deserter_History_DAOImpl implements Deserter_History_DAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public ArrayList<ArrayList<String>> change_Deserter(BigInteger comm_id, int census_id) {
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			
			
			/*q="SELECT d.id,td.label as arm_type,d.arm_type_weapon,d.dt_desertion, d.dt_recovered, td1.label as desertion_cause,\n"
					+ "ot_desertion_cause, d.approved_by, d.approved_date\n"
					+ "  FROM tb_psg_deserter d\n" + 
					"inner join t_domain_value td on d.arm_type=td.codevalue and td.domainid='ARM_TYPE'\n" + 
					"inner join t_domain_value td1 on d.desertion_cause=td1.codevalue and td1.domainid='CAUSE_OF_DESRTION'\n" + 
					"where census_id=? AND comm_id = ? and status in (1,2)";
			*/
			q=" SELECT d.id,td.label as arm_type,d.arm_type_weapon,ltrim(TO_CHAR(d.dt_desertion ,'DD-MON-YYYY'),'0') as dt_desertion,ltrim(TO_CHAR(d.dt_recovered ,'DD-MON-YYYY'),'0') as dt_recovered , td1.label as desertion_cause,\r\n" + 
					"					ot_desertion_cause, d.approved_by,ltrim(TO_CHAR(d.approved_date ,'DD-MON-YYYY'),'0') as approved_date,d.created_by,ltrim(TO_CHAR(d.created_date ,'DD-MON-YYYY'),'0') as created_date\r\n" + 
					"					 FROM tb_psg_deserter d\r\n" + 
					"					inner join t_domain_value td on d.arm_type=td.codevalue and td.domainid='ARM_TYPE'\r\n" + 
					"					inner join t_domain_value td1 on d.desertion_cause=td1.codevalue and td1.domainid='CAUSE_OF_DESRTION'\r\n" + 
					"					where census_id=? AND cast(comm_id as character varying)=? and status in (1,2) order  by d.id";
						stmt=conn.prepareStatement(q);
						stmt.setInt(1,census_id);
						stmt.setString(2,String.valueOf(comm_id));
						
						
						
						ResultSet rs = stmt.executeQuery(); 
					

						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							/*list.add(rs.getString("deserter"));//1
*/							list.add(rs.getString("arm_type"));//0
                            list.add(rs.getString("dt_desertion"));//3
							list.add(rs.getString("arm_type_weapon"));//2
							list.add(rs.getString("dt_recovered"));//4
							list.add(rs.getString("desertion_cause"));//5
							list.add(rs.getString("ot_desertion_cause"));//6
							list.add(rs.getString("created_by"));//7
							list.add(rs.getString("created_date"));//8
							list.add(rs.getString("approved_by"));//9
							list.add(rs.getString("approved_date"));//10
							
							
							
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
