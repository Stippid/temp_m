package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class IdentityCard_Popup_JCO_DAOImpl implements IdentityCard_Popup_JCO_DAO {
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> IdentityCard_PopUp_JCO(int identitycard_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				
				q="SELECT ic.id, \r\n" + 
						"ic.created_by, \r\n" + 
						"TO_CHAR(ic.created_date ,'DD-MON-YYYY') as created_date, \r\n" + 
						"case when ec.eye_cl_name = 'OTHERS' then ic.hair_other\r\n" + 
						"else ec.eye_cl_name end as eye_cl_name,\r\n" + 
						"case when hc.hair_cl_name = 'OTHERS' then ic.hair_other\r\n" + 
						"else hc.hair_cl_name end as hair_cl_name,\r\n" + 
						"ic.id_marks, \r\n" + 
						"ud.unit_name as issue_authority,\r\n" + 
						"TO_CHAR(ic.issue_dt ,'DD-MON-YYYY') as issue_dt,\r\n" + 
						"ic.modified_by,\r\n" + 
						"TO_CHAR(ic.modified_date ,'DD-MON-YYYY') as modified_date,\r\n" + 
						"ic.id_card_no\r\n" + 
						"FROM public.tb_psg_identity_card_jco ic\r\n" + 
						"LEFT JOIN tb_psg_mstr_hair_colour hc ON ic.hair_colour = hc.id \r\n" + 
						"LEFT JOIN tb_psg_mstr_eye_colour ec ON ic.eye_colour = ec.id \r\n" + 
						"inner join tb_miso_orbat_unt_dtl ud on ud.sus_no=ic.issue_authority and ud.status_sus_no in ('Active','Inactive') \r\n" + 
						"WHERE (ic.status = 1 OR ic.status = 2) AND  \r\n" + 
						"jco_id = ?  ORDER BY ic.id DESC";
						
	
					stmt=conn.prepareStatement(q);
					stmt.setInt(1,identitycard_jco_id);
					
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
						
						/*String f = "";
						String f1 = "";
						String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Bank Details?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
						f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Bank Details?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
						f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
			
						list.add(f);
						list.add(f1);*/
						
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
