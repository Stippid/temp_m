package com.dao.psg.popup_history;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
public class Promotional_Exam_History_DAOImpl implements Promotional_Exam_History_DAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<ArrayList<String>> promotional_exam(BigInteger comm_id,int census_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{  	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			  
			    q="select p.authority,"
			    		+ "ltrim(TO_CHAR(p.date_of_authority,'DD-MON-YYYY'),'0') as date_of_authority,"
			    		+ "p.date_of_passing,"
			    		+ "p.exam_other ,"
			    		+ "t.label,"
			    		+ "p.created_by,"
			    		+ "ltrim(TO_CHAR(p.created_on,'DD-MON-YYYY'),'0') as created_on,"
			    		+ "p.modify_by,"
			    		+ "ltrim(TO_CHAR(p.modify_on,'DD-MON-YYYY'),'0') as modify_on\n" + 
			    		"from tb_psg_census_promo_exam p\n" + 
			    		"left join t_domain_value t on t.codevalue=p.exam and t.domainid='PROMOTIONAL_EXAM' "
			    		+ "where  p.status in (1,2) and cast(p.comm_id as character varying)=? and p.census_id=? order by p.id";
			   
			stmt=conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			stmt.setInt(2,census_id);
			
			ResultSet rs = stmt.executeQuery();      
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("authority"));//1
				list.add(rs.getString("date_of_authority"));//2
				list.add(rs.getString("label"));//3
				list.add(rs.getString("exam_other"));//4
				list.add(rs.getString("date_of_passing"));//5
				list.add(rs.getString("created_by"));//6
				list.add(rs.getString("created_on"));//7
				list.add(rs.getString("modify_by"));//8
				list.add(rs.getString("modify_on"));//9
				
				
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

















