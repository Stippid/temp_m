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

public class Change_Identity_History_DAOImpl implements Change_Identity_History_DAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	@Override
	public ArrayList<ArrayList<String>> change_in_identity_card_dtl(BigInteger comm_id, int census_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			q="SELECT distinct ic.id, ic.census_id,ic.comm_id,\n" + 
					"					ic.created_by, \n" + 
					"					ltrim(TO_CHAR(ic.created_date ,'DD-MON-YYYY'),'0') as created_date, \n" + 
					"					case when ec.eye_cl_name = 'OTHERS' then ic.hair_other\n" + 
					"					else ec.eye_cl_name end as eye_cl_name,\n" + 
					"					case when hc.hair_cl_name = 'OTHERS' then ic.hair_other\n" + 
					"					else hc.hair_cl_name end as hair_cl_name,\n" + 
					"					ic.id_marks, \n" + 
					"					ud.unit_name as issue_authority, \n" + 
					"					ltrim(TO_CHAR(ic.issue_dt ,'DD-MON-YYYY'),'0') as issue_dt,\n" + 
					"					ic.modified_by,\n" + 
					"					ltrim(TO_CHAR(ic.modified_date ,'DD-MON-YYYY'),'0') as modified_date,\n" + 
					"					ic.id_card_no\n" + 
					"					FROM public.tb_psg_identity_card ic\n" + 
					"				    LEFT JOIN tb_psg_mstr_hair_colour hc ON ic.hair_colour = hc.id\n" + 
					"					LEFT JOIN tb_psg_mstr_eye_colour ec ON ic.eye_colour = ec.id \n" +
					" inner join tb_miso_orbat_unt_dtl ud on ud.sus_no=ic.issue_authority and ud.status_sus_no in ('Active','Inactive')  \n"+
					"					WHERE (ic.status = 1 OR ic.status = 2) AND  cast(comm_id as character varying)=?  ORDER BY ic.id " ;
				
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,String.valueOf(comm_id));
				
				 				
				
				ResultSet rs = stmt.executeQuery();  
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("id_card_no"));//0
					list.add(rs.getString("eye_cl_name"));//1
					list.add(rs.getString("hair_cl_name"));//2
					list.add(rs.getString("id_marks"));//3
					list.add(rs.getString("issue_authority"));//4
					list.add(rs.getString("issue_dt"));//5
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
