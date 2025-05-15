package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.persistance.util.HibernateUtil;

public class Change_Religion_History_DAOImpl implements Change_Religion_History_DAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	@Override
	public ArrayList<ArrayList<String>> change_in_religion_dtl(BigInteger comm_id, int census_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			q="SELECT\n" + 
					"					cr.authority, \n" + 
					"					ltrim(TO_CHAR(cr.date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority,\n" + 
					"					case when mr.religion_name = 'OTHERS' then cr. other\n" + 
					"					else mr.religion_name end as religion_name"
					+ ",ltrim(TO_CHAR(cr.change_in_rel_date ,'DD-MON-YYYY'),'0') as change_in_rel_date,\n" + 
					"					cr.created_by,  \n" + 
					"					ltrim(TO_CHAR(cr.created_date ,'DD-MON-YYYY'),'0') as created_date, \n" + 
					"					cr.modified_by, \n" + 
					"					ltrim(TO_CHAR(cr.modified_date,'DD-MON-YYYY'),'0') as modified_date\n" + 
					"					FROM public.tb_psg_change_religion cr\n" + 
					"					LEFT JOIN tb_psg_mstr_religion mr ON cr.religion = mr.religion_id \n" + 
					"					WHERE (cr.status = 1 OR cr.status = 2) AND cr.census_id = ? AND  cast(cr.comm_id as character varying) = ? ORDER BY cr.id " ;
				stmt=conn.prepareStatement(q);
				
				stmt.setInt(1,census_id);
				stmt.setString(2,String.valueOf(comm_id));
				
				 				
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("authority"));//0
					list.add(rs.getString("date_of_authority"));//1
					list.add(rs.getString("religion_name"));//2
					list.add(rs.getString("change_in_rel_date"));
				    list.add(rs.getString("created_by"));//3
					list.add(rs.getString("created_date"));//4
					list.add(rs.getString("modified_by"));//5
					list.add(rs.getString("modified_date"));//6
					
					
					
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
